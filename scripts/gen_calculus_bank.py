#!/usr/bin/env python3
"""
Gerador do banco de questões de Cálculo I (26 aulas x 10 questões = 260 questões).
Cobre MULTIPLE_CHOICE, TRUE_FALSE, MATCHING, FILL_BLANK, NUMERIC (com verify), CHART (com verify) e ESSAY.
"""
import json
import os
from calc_data_1 import CALC_DATA_1
from calc_data_2 import CALC_DATA_2

ROOT = os.path.dirname(os.path.dirname(os.path.abspath(__file__)))
OUT = os.path.join(ROOT, "app", "src", "main", "assets", "assessment", "q_calculus.json")


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
        "skill": f"Julgar proposições de Cálculo I sobre {sub}",
        "concept": concept,
        "statement": tf_stmt,
        "explanation": f"A afirmação é verdadeira: {tf_stmt} Esse resultado é essencial no estudo de {sub}.",
        "retryGuidance": f"Revise a definição formal e as propriedades de {sub}.",
        "source": "AUTORAL",
        "answerBool": True,
    })

    # Q2: FILL_BLANK (EASY)
    out.append({
        "id": f"{lid}_n02",
        "lessonId": lid,
        "format": "FILL_BLANK",
        "difficulty": diffs[1],
        "skill": f"Identificar o termo ou expressão chave em {sub}",
        "concept": concept,
        "statement": f"No estudo de {sub} ({concept}), o termo, expressão ou propriedade chave destacada na aula é ___.",
        "explanation": f"O preenchimento correto é '{fill_term}', elemento estruturante da aula sobre {sub}.",
        "retryGuidance": f"Consulte as definições e fórmulas principais da aula sobre {sub}.",
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
            f"{target_left}: Propriedade inválida que contraria os teoremas do Cálculo Diferencial e Integral",
        ]
        raw_dist = [
            "",
            f"Confundiu '{target_left}' com '{[p[0] for i,p in enumerate(match_pairs) if i!=p_idx][0]}'",
            f"Confundiu '{target_left}' com '{[p[0] for i,p in enumerate(match_pairs) if i!=p_idx][1]}'",
            f"Confundiu '{target_left}' com '{[p[0] for i,p in enumerate(match_pairs) if i!=p_idx][2]}'",
            f"Desconsiderou as hipóteses e regras operatórias de '{target_left}'",
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
            "skill": f"Aplicar teoremas e regras operatórias de {sub}",
            "concept": f"{target_left} em {sub}",
            "statement": f"Considerando os conceitos de {sub}, assinale a alternativa que descreve corretamente '{target_left}':",
            "explanation": f"A alternativa correta relaciona '{target_left}' a: {target_right}. As demais opções referem-se a outros itens da aula.",
            "retryGuidance": f"Compare a definição e a regra de '{target_left}' com os outros tópicos de {sub}.",
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
        "skill": f"Associar fórmulas, teoremas e interpretações geométricas de {sub}",
        "concept": concept,
        "statement": f"Associe cada expressão, regra ou teorema de {sub} ao seu respectivo significado ou resultado:",
        "explanation": "Conectar a representação algébrica ao significado geométrico consolida o domínio de Limites, Derivadas e Integrais.",
        "retryGuidance": f"Verifique cada linha do resumo de fórmulas e teoremas da aula sobre {sub}.",
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
        "skill": f"Calcular limites, derivadas ou integrais em {sub}",
        "concept": concept,
        "statement": n1_stmt,
        "explanation": f"Efetuando o cálculo passo a passo ({n1_ver}), obtemos o valor exato de {n1_val} {n1_unit}.",
        "retryGuidance": f"Aplique a propriedade ou regra da aula e verifique a expressão {n1_ver}.",
        "source": "AUTORAL",
        "answerNumber": n1_val,
        "tolerance": 0.01,
        "unit": n1_unit,
        "verify": n1_ver,
    })

    # Q8: CHART (MEDIUM/HARD)
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
        "skill": f"Interpretar tabelas de aproximação e comportamento de funções em {sub}",
        "concept": concept,
        "statement": c_stmt,
        "explanation": f"Analisando os dados de '{c_title}' ({c_ver}), conclui-se corretamente: {c_opts[0]}.",
        "retryGuidance": "Observe os valores numéricos das colunas e relacione-os com a expressão analítica da função.",
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
        "skill": f"Resolver problema analítico ou aplicado de {sub}",
        "concept": concept,
        "statement": n2_stmt,
        "explanation": f"Aplicando a técnica de {sub} ({n2_ver}), chegamos ao resultado {n2_val} {n2_unit}.",
        "retryGuidance": f"Confira os sinais, expoentes e constantes na expressão {n2_ver}.",
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
        "skill": f"Demonstrar e justificar conceitualmente resultados de {sub}",
        "concept": concept,
        "statement": es_stmt,
        "explanation": f"Síntese conceitual e analítica esperada pela rubrica: {es_model}",
        "retryGuidance": f"Estruture sua resposta enunciando a definição/teorema de {sub}, o procedimento algébrico e a interpretação geométrica.",
        "source": "AUTORAL",
        "modelAnswer": es_model,
        "passScore": 0.6,
        "rubric": [
            {"criterion": f"Enuncia o conceito, teorema ou regra central de {sub}", "points": 4, "keywords": [es_kw[0]]},
            {"criterion": "Desenvolve ou explica corretamente a etapa algébrica/operatória", "points": 3, "keywords": [es_kw[1]]},
            {"criterion": "Apresenta a conclusão ou interpretação geométrica/aplicada", "points": 3, "keywords": [es_kw[2]]},
        ],
    })

    return out


if __name__ == "__main__":
    all_rows = CALC_DATA_1 + CALC_DATA_2
    assert len(all_rows) == 26, f"Esperado 26 aulas, obtido {len(all_rows)}"
    questions = []
    for row in all_rows:
        questions.extend(make_lesson_questions(row))
    os.makedirs(os.path.dirname(OUT), exist_ok=True)
    with open(OUT, "w", encoding="utf-8") as f:
        json.dump(questions, f, ensure_ascii=False, indent=2)
    print(f"Geradas {len(questions)} questões em {OUT}")
