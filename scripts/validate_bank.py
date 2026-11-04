#!/usr/bin/env python3
"""
Validador do banco de questões do Knowverse (app/src/main/assets/assessment/q_*.json).

Uso:
    python scripts/validate_bank.py                  # valida todos os arquivos q_*.json
    python scripts/validate_bank.py q_math_01.json   # valida apenas arquivos informados (nomes em assets/assessment)

Mecanismos de validação (nenhuma resposta é aprovada por critério frágil):
  * Estrutura: campos obrigatórios por formato, ids únicos, aula existente, enums válidos.
  * Matemática/Física/Química: campo `verify` (expressão Python) é AVALIADO e comparado com a
    resposta marcada (NUMERIC -> answerNumber; MC/CHART -> primeiro número da alternativa correta).
    Nenhuma outra alternativa numérica pode coincidir com o valor verificado.
  * Python (CODE): a solução de referência é EXECUTADA em subprocesso contra todos os testes
    (call -> expected) e o código inicial (starter) NÃO pode passar nos testes.
  * Discursivas: rubricas explícitas (critérios com pontos + grupos de palavras-chave); a resposta
    modelo precisa atingir 100% da rubrica e uma resposta vazia 0%.
  * Rótulo: nenhuma questão autoral pode se passar por questão oficial (ENEM/INEP).
Código de saída: 0 = ok, 1 = erros.
"""
import json
import math
import os
import re
import subprocess
import sys
import tempfile
import unicodedata

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
ASSETS = os.path.join(ROOT, "app", "src", "main", "assets", "assessment")
LESSON_INDEX = os.path.join(ROOT, "scripts", "lesson_index.json")

FORMATS = {"MULTIPLE_CHOICE", "TRUE_FALSE", "MATCHING", "FILL_BLANK", "NUMERIC", "CHART", "ESSAY", "CODE"}
DIFFS = {"EASY", "MEDIUM", "HARD"}

SAFE_ENV = {k: getattr(math, k) for k in dir(math) if not k.startswith("_")}
SAFE_ENV.update({"abs": abs, "round": round, "min": min, "max": max, "sum": sum, "pow": pow,
                 "range": range, "len": len, "int": int, "float": float, "sorted": sorted})


def norm(s):
    s = unicodedata.normalize("NFD", str(s).lower())
    s = "".join(c for c in s if unicodedata.category(c) != "Mn")
    return re.sub(r"\s+", " ", s).strip()


def first_number(text):
    t = str(text).replace("−", "-").replace("\u2212", "-")
    m = re.search(r"-?\d[\d\.]*(?:,\d+)?|-?\d+(?:\.\d+)?", t)
    if not m:
        return None
    tok = m.group(0)
    # formato brasileiro: 1.234,56 ou 12,5 ; formato simples: 12.5
    if "," in tok:
        tok = tok.replace(".", "").replace(",", ".")
    elif tok.count(".") > 1:
        tok = tok.replace(".", "")
    elif re.fullmatch(r"-?\d{1,3}\.\d{3}", tok):
        tok = tok.replace(".", "")  # 1.000 -> 1000 (milhar)
    try:
        return float(tok)
    except ValueError:
        return None


def essay_score(q, text):
    """Mesma lógica usada pelo AnswerEvaluator.kt para discursivas."""
    t = norm(text)
    total = got = 0
    for c in q["rubric"]:
        total += c["points"]
        for group in c["keywords"]:
            if any(norm(k) in t for k in group):
                continue
            break
        else:
            got += c["points"]
    return got, total


def run_python(code, timeout=10):
    with tempfile.NamedTemporaryFile("w", suffix=".py", delete=False, encoding="utf-8") as f:
        f.write(code)
        path = f.name
    try:
        p = subprocess.run([sys.executable, path], capture_output=True, text=True, timeout=timeout, encoding="utf-8")
        return p.returncode, p.stdout, p.stderr
    except subprocess.TimeoutExpired:
        return -1, "", "timeout"
    finally:
        os.unlink(path)


def build_harness(user_code, tests):
    lines = [user_code, "", "import json as _j"]
    lines.append("_res = []")
    for t in tests:
        lines.append("try:\n    _res.append(repr(%s))\nexcept Exception as _e:\n    _res.append('EXC:' + type(_e).__name__)" % t["call"])
    lines.append("print(_j.dumps(_res))")
    return "\n".join(lines)


def check_code(q, err):
    c = q["code"]
    tests = c["tests"]
    expected = []
    for t in tests:
        try:
            expected.append(repr(eval(t["expected"], {"__builtins__": {}}, {"inf": float("inf")})))
        except Exception as e:
            err(f"expected inválido '{t['expected']}': {e}")
            return
    rc, out, serr = run_python(build_harness(c["reference"], tests))
    if rc != 0:
        err(f"solução de referência falhou: {serr.strip()[-300:]}")
        return
    try:
        res = json.loads(out.strip().splitlines()[-1])
    except Exception:
        err(f"saída inesperada da referência: {out[-200:]}")
        return
    for i, (r, e) in enumerate(zip(res, expected)):
        if r != e:
            err(f"teste {i + 1} ({tests[i]['call']}): referência produziu {r}, esperado {e}")
    # o código inicial não pode passar em todos os testes
    rc, out, serr = run_python(build_harness(c["starter"], tests))
    if rc == 0:
        try:
            res2 = json.loads(out.strip().splitlines()[-1])
            if res2 == expected:
                err("o código inicial (starter) já passa em todos os testes")
        except Exception:
            pass


def validate_question(q, lessons, err):
    qid = q.get("id", "?")

    def E(msg):
        err(f"{qid}: {msg}")

    for k in ("id", "lessonId", "format", "difficulty", "skill", "concept", "statement", "explanation",
              "retryGuidance", "source"):
        if not isinstance(q.get(k), str) or not q[k].strip():
            E(f"campo obrigatório ausente/vazio: {k}")
    if q.get("lessonId") not in lessons:
        E(f"lessonId inexistente: {q.get('lessonId')}")
        return
    if not re.fullmatch(re.escape(q["lessonId"]) + r"_n\d{2,3}", q["id"]):
        E("id deve seguir <lessonId>_nNN")
    fmt = q.get("format")
    if fmt not in FORMATS:
        E(f"format inválido: {fmt}")
        return
    if q.get("difficulty") not in DIFFS:
        E("difficulty inválida")
    if q.get("source") != "AUTORAL":
        E("source deve ser 'AUTORAL' (questões autorais não podem se passar por oficiais)")
    blob = " ".join(str(q.get(k, "")) for k in ("statement", "explanation", "skill"))
    if re.search(r"\(\s*ENEM[^)]*\)|ENEM\s*(de\s*)?(19|20)\d\d|\bINEP\b|prova oficial|quest[aã]o oficial", blob, re.I):
        E("texto sugere questão oficial (ENEM/INEP); use apenas 'estilo ENEM' se necessário")
    if len(q.get("explanation", "")) < 40:
        E("explanation muito curta")
    if len(q.get("statement", "")) < 15:
        E("statement muito curto")

    if fmt in ("MULTIPLE_CHOICE", "CHART"):
        opts = q.get("options")
        if not isinstance(opts, list) or not 4 <= len(opts) <= 5:
            E("options deve ter 4 ou 5 itens")
            return
        if len({norm(o) for o in opts}) != len(opts):
            E("alternativas duplicadas")
        ai = q.get("answerIndex")
        if not isinstance(ai, int) or not 0 <= ai < len(opts):
            E("answerIndex inválido")
            return
        dc = q.get("distractorConcepts")
        if not isinstance(dc, list) or len(dc) != len(opts):
            E("distractorConcepts deve ter o mesmo tamanho de options ('' na correta)")
        else:
            for i, c in enumerate(dc):
                if i != ai and not str(c).strip():
                    E(f"distractorConcepts[{i}] vazio (conceito não compreendido que leva a esse erro)")
        if fmt == "CHART":
            ch = q.get("chart")
            if not isinstance(ch, dict) or ch.get("type") not in ("bar", "line", "table", "pie") \
                    or not ch.get("labels") or not ch.get("series"):
                E("chart inválido (type bar|line|table|pie, labels, series)")
            else:
                for s in ch["series"]:
                    if len(s.get("values", [])) != len(ch["labels"]):
                        E("chart: series.values deve ter o tamanho de labels")
        if "verify" in q:
            try:
                val = float(eval(q["verify"], {"__builtins__": {}}, SAFE_ENV))
            except Exception as e:
                E(f"verify inválido: {e}")
                return
            got = first_number(opts[ai])
            if got is None or not math.isclose(got, val, rel_tol=0.006, abs_tol=0.006):
                E(f"verify={val} não confere com alternativa correta '{opts[ai]}'")
            for i, o in enumerate(opts):
                if i != ai:
                    n = first_number(o)
                    if n is not None and math.isclose(n, val, rel_tol=0.006, abs_tol=0.006):
                        E(f"alternativa errada [{i}] '{o}' também coincide com o valor verificado")
    elif fmt == "TRUE_FALSE":
        if not isinstance(q.get("answerBool"), bool):
            E("answerBool deve ser booleano")
        if "verify" in q:
            try:
                if bool(eval(q["verify"], {"__builtins__": {}}, SAFE_ENV)) != q.get("answerBool"):
                    E("verify (booleano) diverge de answerBool")
            except Exception as e:
                E(f"verify inválido: {e}")
    elif fmt == "MATCHING":
        pr = q.get("pairs")
        if not isinstance(pr, list) or not 3 <= len(pr) <= 6 or any(not isinstance(p, list) or len(p) != 2 for p in pr):
            E("pairs deve ter 3 a 6 pares [esquerda, direita]")
        else:
            if len({norm(p[0]) for p in pr}) != len(pr) or len({norm(p[1]) for p in pr}) != len(pr):
                E("pares com itens repetidos")
    elif fmt == "FILL_BLANK":
        if "___" not in q.get("statement", ""):
            E("statement deve conter '___' marcando a lacuna")
        an = q.get("answers")
        if not isinstance(an, list) or not an or any(not str(a).strip() for a in an):
            E("answers deve ser lista não vazia de respostas aceitas")
    elif fmt == "NUMERIC":
        if not isinstance(q.get("answerNumber"), (int, float)) or isinstance(q.get("answerNumber"), bool):
            E("answerNumber numérico obrigatório")
            return
        if not isinstance(q.get("tolerance"), (int, float)) or q["tolerance"] < 0:
            E("tolerance >= 0 obrigatória")
        if "verify" not in q:
            E("NUMERIC exige 'verify' (expressão Python que calcula a resposta)")
            return
        try:
            val = float(eval(q["verify"], {"__builtins__": {}}, SAFE_ENV))
        except Exception as e:
            E(f"verify inválido: {e}")
            return
        if not math.isclose(val, q["answerNumber"], rel_tol=1e-6, abs_tol=max(1e-9, q.get("tolerance", 0))):
            E(f"verify={val} diverge de answerNumber={q['answerNumber']}")
    elif fmt == "ESSAY":
        rb = q.get("rubric")
        if not isinstance(q.get("modelAnswer"), str) or len(q["modelAnswer"]) < 40:
            E("modelAnswer obrigatória")
        if not isinstance(rb, list) or len(rb) < 3:
            E("rubric deve ter >= 3 critérios")
            return
        for c in rb:
            if not str(c.get("criterion", "")).strip() or not isinstance(c.get("points"), int) or c["points"] <= 0:
                E("critério da rubrica inválido (criterion, points>0)")
                return
            kw = c.get("keywords")
            if not isinstance(kw, list) or not kw or any(not isinstance(g, list) or not g for g in kw):
                E("keywords deve ser lista de grupos (cada grupo: lista de sinônimos)")
                return
        if not isinstance(q.get("passScore"), (int, float)) or not 0 < q["passScore"] <= 1:
            E("passScore (0..1] obrigatório")
        got, total = essay_score(q, q.get("modelAnswer", ""))
        if got != total:
            E(f"a resposta modelo obtém {got}/{total} na rubrica (deve obter 100%)")
        if essay_score(q, "")[0] != 0:
            E("resposta vazia pontua na rubrica")
    elif fmt == "CODE":
        c = q.get("code")
        if not isinstance(c, dict) or not all(isinstance(c.get(k), str) and c[k].strip() for k in ("starter", "reference")):
            E("code.starter e code.reference obrigatórios")
            return
        tests = c.get("tests")
        if not isinstance(tests, list) or len(tests) < 3 or any(not t.get("call") or "expected" not in t for t in tests):
            E("code.tests deve ter >= 3 itens {call, expected}")
            return
        if not any(t.get("hidden") for t in tests):
            E("pelo menos um teste deve ser oculto (hidden: true)")
        check_code(q, lambda m: E(m))


def main():
    with open(LESSON_INDEX, encoding="utf-8") as f:
        lessons = {o["lessonId"]: o for o in json.load(f)}
    files = sys.argv[1:] or sorted(x for x in os.listdir(ASSETS) if x.startswith("q_") and x.endswith(".json"))
    errors, seen, total = [], set(), 0
    per_lesson = {}
    for fn in files:
        path = fn if os.path.isabs(fn) else os.path.join(ASSETS, fn)
        try:
            with open(path, encoding="utf-8-sig") as f:
                data = json.load(f)
        except Exception as e:
            errors.append(f"{fn}: JSON inválido: {e}")
            continue
        if not isinstance(data, list):
            errors.append(f"{fn}: raiz deve ser array")
            continue
        for q in data:
            total += 1
            if q.get("id") in seen:
                errors.append(f"{q.get('id')}: id duplicado")
            seen.add(q.get("id"))
            validate_question(q, lessons, lambda m, fn=fn: errors.append(f"[{fn}] {m}"))
            per_lesson.setdefault(q.get("lessonId"), []).append(q)
    # distribuição por aula (aviso) para arquivos validados
    warns = []
    for lid, qs in sorted(per_lesson.items()):
        if len(qs) < 8:
            warns.append(f"{lid}: apenas {len(qs)} questões novas (meta >= 8)")
        d = {x: sum(1 for q in qs if q.get("difficulty") == x) for x in DIFFS}
        if len(qs) >= 8 and (d["HARD"] < 1 or d["EASY"] < 1):
            warns.append(f"{lid}: distribuição de dificuldade {d}")
        nf = len({q.get("format") for q in qs})
        if len(qs) >= 8 and nf < 3:
            warns.append(f"{lid}: apenas {nf} formatos distintos")
    print(f"{total} questões em {len(files)} arquivo(s); {len(errors)} erro(s); {len(warns)} aviso(s)")
    for w in warns:
        print("  AVISO", w)
    for e in errors:
        print("  ERRO ", e)
    sys.exit(1 if errors else 0)


if __name__ == "__main__":
    sys.stdout.reconfigure(encoding="utf-8")
    main()
