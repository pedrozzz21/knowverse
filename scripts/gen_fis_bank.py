#!/usr/bin/env python3
"""
Gerador do banco de questões de Física ENEM (34 aulas x 10 questões = 340 questões).
Cobre MULTIPLE_CHOICE, TRUE_FALSE, MATCHING, FILL_BLANK, NUMERIC (com verify), CHART (com verify) e ESSAY.
"""
import json
import os
from fis_data_1 import FIS_DATA_1
from fis_data_2 import FIS_DATA_2

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT = os.path.join(ROOT, "app", "src", "main", "assets", "assessment", "q_enem_fis.json")


def make_lesson_questions(row):
    lid, order, sub, concept, fill_term, tf_stmt, match_pairs, num1, num2, chart_q, essay_q = row
    diffs = (
        ["EASY", "EASY", "EASY", "MEDIUM", "MEDIUM", "MEDIUM", "MEDIUM", "MEDIUM", "HARD", "HARD"]
        if order % 2 == 0
        else ["EASY", "EASY", "EASY", "MEDIUM", "MEDIUM", "MEDIUM", "MEDIUM", "HARD", "HARD", "HARD"]
    )
    out = []

    # Q1: TRUE_FALSE (EASY)
    out.append({
        "id": f"{lid}_n01",
        "lessonId": lid,
        "format": "TRUE_FALSE",
        "difficulty": diffs[0],
        "skill": f"Julgar proposições físicas sobre {sub}",
        "concept": concept,
        "statement": tf_stmt,
        "explanation": f"A afirmação é verdadeira: {tf_stmt} Esse princípio é fundamental em {sub}.",
        "retryGuidance": f"Revise as leis físicas e relações matemáticas de {sub}.",
        "source": "AUTORAL",
        "answerBool": True,
    })

    # Q2: FILL_BLANK (EASY)
    out.append({
        "id": f"{lid}_n02",
        "lessonId": lid,
        "format": "FILL_BLANK",
        "difficulty": diffs[1],
        "skill": f"Identificar o conceito ou lei física em {sub}",
        "concept": concept,
        "statement": f"No estudo de {sub} ({concept}), o termo, unidade ou grandeza física central destacada na aula é ___.",
        "explanation": f"O termo correto para completar a lacuna é '{fill_term}', elemento essencial de {sub}.",
        "retryGuidance": f"Consulte o quadro de conceitos e fórmulas de {sub} e verifique a grafia do termo principal.",
        "source": "AUTORAL",
        "answers": list({fill_term, fill_term.lower()}),
    })

    # Q3, Q5, Q6: 3 MULTIPLE_CHOICE
    for mc_idx, (qnum, d_slot, p_idx) in enumerate([(3, 2, 0), (5, 4, 2), (6, 5, 3)]):
        target_left, target_right = match_pairs[p_idx]
        other_rights = [p[1] for i, p in enumerate(match_pairs) if i != p_idx]
        raw_opts = [
            f"{target_left}: {target_right}",
            f"{target_left}: {other_rights[0]}",
            f"{target_left}: {other_rights[1]}",
            f"{target_left}: {other_rights[2]}",
            f"{target_left}: Grandeza nula que independe de qualquer variável física",
        ]
        raw_dist = [
            "",
            f"Confundiu '{target_left}' com '{[p[0] for i,p in enumerate(match_pairs) if i!=p_idx][0]}'",
            f"Confundiu '{target_left}' com '{[p[0] for i,p in enumerate(match_pairs) if i!=p_idx][1]}'",
            f"Confundiu '{target_left}' com '{[p[0] for i,p in enumerate(match_pairs) if i!=p_idx][2]}'",
            f"Desconsiderou a lei física aplicável a '{target_left}' em {sub}",
        ]
        shift = (order + mc_idx * 2) % 5
        opts = raw_opts[shift:] + raw_opts[:shift]
        dist = raw_dist[shift:] + raw_dist[:shift]
        ans = (0 - shift) % 5
        out.append({
            "id": f"{lid}_n{qnum:02d}",
            "lessonId": lid,
            "format": "MULTIPLE_CHOICE",
            "difficulty": diffs[d_slot],
            "skill": f"Relacionar leis físicas e equações em {sub}",
            "concept": f"{target_left} em {sub}",
            "statement": f"Considerando os princípios de {sub}, assinale a alternativa que caracteriza corretamente '{target_left}':",
            "explanation": f"A alternativa correta relaciona '{target_left}' a: {target_right}. As demais opções descrevem outros conceitos da aula.",
            "retryGuidance": f"Compare a definição e a fórmula de '{target_left}' com os demais tópicos de {sub}.",
            "source": "AUTORAL",
            "options": opts,
            "answerIndex": ans,
            "distractorConcepts": dist,
        })

    # Q4: MATCHING (MEDIUM)
    out.append({
        "id": f"{lid}_n04",
        "lessonId": lid,
        "format": "MATCHING",
        "difficulty": diffs[3],
        "skill": f"Associar grandezas, leis e aplicações de {sub}",
        "concept": concept,
        "statement": f"Associe cada grandeza, lei ou fenômeno de {sub} à sua respectiva propriedade física:",
        "explanation": "Relacionar cada lei física à sua expressão matemática e aplicação prática é indispensável para resolver problemas contextualizados.",
        "retryGuidance": f"Analise as unidades e relações de proporcionalidade de cada item de {sub}.",
        "source": "AUTORAL",
        "pairs": match_pairs,
    })

    # Q7: NUMERIC 1 (MEDIUM)
    n1_stmt, n1_val, n1_ver, n1_unit = num1
    out.append({
        "id": f"{lid}_n07",
        "lessonId": lid,
        "format": "NUMERIC",
        "difficulty": diffs[6],
        "skill": f"Resolver problema numérico de {sub}",
        "concept": concept,
        "statement": n1_stmt,
        "explanation": f"Aplicando a equação de {sub} ({n1_ver}), obtemos o resultado exato de {n1_val} {n1_unit}.",
        "retryGuidance": f"Identifique os dados no Sistema Internacional e aplique a relação {n1_ver}.",
        "source": "AUTORAL",
        "answerNumber": n1_val,
        "tolerance": 0.01,
        "unit": n1_unit,
        "verify": n1_ver,
    })

    # Q8: CHART (MEDIUM)
    c_title, c_labels, c_sname, c_vals, c_stmt, c_opts, c_ver, c_dist = chart_q
    shift_c = (order + 1) % 5
    opts_c = c_opts[shift_c:] + c_opts[:shift_c]
    dist_c = c_dist[shift_c:] + c_dist[:shift_c]
    ans_c = (0 - shift_c) % 5
    out.append({
        "id": f"{lid}_n08",
        "lessonId": lid,
        "format": "CHART",
        "difficulty": diffs[7],
        "skill": f"Interpretar gráficos e tabelas de {sub}",
        "concept": concept,
        "statement": c_stmt,
        "explanation": f"Analisando os dados de '{c_title}' ({c_ver}), conclui-se corretamente: {c_opts[0]}.",
        "retryGuidance": "Extraia os valores diretamente dos eixos/colunas antes de substituir na equação física.",
        "source": "AUTORAL",
        "options": opts_c,
        "answerIndex": ans_c,
        "distractorConcepts": dist_c,
        "verify": c_ver,
        "chart": {
            "type": "bar",
            "title": c_title,
            "labels": c_labels,
            "series": [{"name": c_sname, "values": c_vals}],
            "unit": "",
        },
    })

    # Q9: NUMERIC 2 (HARD)
    n2_stmt, n2_val, n2_ver, n2_unit = num2
    out.append({
        "id": f"{lid}_n09",
        "lessonId": lid,
        "format": "NUMERIC",
        "difficulty": diffs[8],
        "skill": f"Modelar e calcular situação física em {sub}",
        "concept": concept,
        "statement": n2_stmt,
        "explanation": f"Substituindo os valores na relação física ({n2_ver}), encontramos {n2_val} {n2_unit}.",
        "retryGuidance": f"Confira se todas as grandezas estão nas unidades pedidas e aplique {n2_ver}.",
        "source": "AUTORAL",
        "answerNumber": n2_val,
        "tolerance": 0.01,
        "unit": n2_unit,
        "verify": n2_ver,
    })

    # Q10: ESSAY (HARD)
    es_stmt, es_model, es_kw = essay_q
    out.append({
        "id": f"{lid}_n10",
        "lessonId": lid,
        "format": "ESSAY",
        "difficulty": diffs[9],
        "skill": f"Explicar fenômenos cotidianos e tecnológicos usando {sub}",
        "concept": concept,
        "statement": es_stmt,
        "explanation": f"Síntese conceitual esperada pela rubrica: {es_model}",
        "retryGuidance": f"Estruture sua resposta mencionando a lei física de {sub}, a relação entre as grandezas e a aplicação prática.",
        "source": "AUTORAL",
        "modelAnswer": es_model,
        "passScore": 0.6,
        "rubric": [
            {"criterion": f"Enuncia o princípio ou lei física central de {sub}", "points": 4, "keywords": [es_kw[0]]},
            {"criterion": "Relaciona corretamente as grandezas físicas envolvidas", "points": 3, "keywords": [es_kw[1]]},
            {"criterion": "Explica a aplicação prática ou consequência física", "points": 3, "keywords": [es_kw[2]]},
        ],
    })

    return out


if __name__ == "__main__":
    all_q = []
    for r in FIS_DATA_1 + FIS_DATA_2:
        all_q.extend(make_lesson_questions(r))
    os.makedirs(os.path.dirname(OUT), exist_ok=True)
    with open(OUT, "w", encoding="utf-8") as f:
        json.dump(all_q, f, ensure_ascii=False, indent=2)
    print(f"Geradas {len(all_q)} questões em {OUT}")
