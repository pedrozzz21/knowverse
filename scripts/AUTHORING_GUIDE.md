# Guia de autoria do banco de questões (Knowverse)

Arquivos: `app/src/main/assets/assessment/q_<lote>.json` — cada arquivo é um **array JSON** (UTF-8, com acentos reais).
Índice de aulas: `scripts/lesson_index.json` (lessonId, subject, moduleTitle, subtopic, title).
Validação (obrigatória, deve terminar com 0 erros): `python scripts/validate_bank.py q_<lote>.json`

## Regras de conteúdo
- **8 questões NOVAS por aula** (ids `<lessonId>_n01` … `_n08`). Não mexa em questões existentes (as antigas ficam nos providers Kotlin).
- Dificuldade por aula (8 questões): `EASY` x2–3, `MEDIUM` x3–4, `HARD` x2 (≈30/45/25).
- Formatos por aula (varie conforme a natureza do conteúdo; **mínimo 4 formatos distintos**, no máximo 3 `MULTIPLE_CHOICE`):
  `MULTIPLE_CHOICE`, `TRUE_FALSE`, `MATCHING`, `FILL_BLANK`, `NUMERIC`, `CHART`, `ESSAY`, `CODE`.
  - Matemática/Física/Cálculo: inclua `NUMERIC` (com `verify`) e pelo menos um `CHART` (interpretação de gráfico/tabela) quando o tema permitir.
  - Biologia: use `MATCHING`, `TRUE_FALSE`, `CHART` (dados/gráfico de experimento ou ecologia) e 1 `ESSAY` com rubrica.
  - Python: ao menos 3 `CODE` (programação prática com testes) + `MULTIPLE_CHOICE` de "qual a saída" + `FILL_BLANK`/`TRUE_FALSE`.
- Alinhe cada questão ao conteúdo ensinado na aula (leia a teoria no provider: `app/src/main/java/com/example/data/{math,biology,physics,python}/…Didactic…kt`; aulas `enem_*` → `math/EnemDidacticProvider.kt` e `math/didactic/`, `bio_*` → `biology/EnemBiologyDidacticProvider.kt`, `fis_*` → `physics/EnemPhysicsDidacticProvider.kt`).
- Estilo ENEM: contextualizado, interpretação de texto/dados, mas **autoral**. É PROIBIDO escrever "(ENEM)", "ENEM 2019", "INEP", "prova oficial" no texto — o validador rejeita. `source` é sempre `"AUTORAL"`.
- Correção científica rigorosa: confira cada resposta. Distratores devem ser erros plausíveis e **distintos** (um conceito errado por distrator).
- Matemática nas strings: use LaTeX entre `\\( ... \\)` (em JSON, barra invertida dobrada) apenas quando necessário; para valores numéricos use texto simples com unidade (ex.: `"R$ 258,00"`, `"62,5 m"`). Números com vírgula decimal (pt-BR).
- Posição da alternativa correta (`answerIndex`) deve variar entre 0 e 4.

## Campos comuns (todos obrigatórios)
```json
{
  "id": "enem_m1_a1_n01",
  "lessonId": "enem_m1_a1",
  "format": "MULTIPLE_CHOICE",
  "difficulty": "EASY",
  "skill": "Habilidade avaliada (ex.: Resolver problema com frações em contexto)",
  "concept": "Conceito central cobrado (ex.: Soma de frações com denominadores diferentes)",
  "statement": "Enunciado…",
  "explanation": "Explicação do raciocínio correto, passo a passo (≥ 40 caracteres).",
  "retryGuidance": "Orientação para resolver de novo se errar (o que revisar/como começar).",
  "source": "AUTORAL"
}
```

## Campos por formato
**MULTIPLE_CHOICE** (4–5 alternativas):
`"options": [...]`, `"answerIndex": 2`, `"distractorConcepts": ["conceito mal compreendido que leva a esta opção", "", ...]` (mesmo tamanho de options; `""` na correta; **obrigatório e específico** nas erradas, ex.: "Confundiu MMC com MDC").
Opcional `"verify": "480/12*5.6+34"` (expressão Python; o validador compara com o 1º número da alternativa correta e exige que nenhuma errada coincida). **Use `verify` em todo MC numérico de Matemática/Física.**

**TRUE_FALSE**: `"answerBool": true`; o enunciado é uma afirmação. A explicação deve dizer por que é V/F. Opcional `"verify"` booleano.

**MATCHING**: `"pairs": [["esquerda","direita correta"], ...]` (3–6 pares, itens únicos). O app embaralha o lado direito.

**FILL_BLANK**: enunciado com `___` e `"answers": ["resposta","sinônimo aceito"]` (comparação sem acentos/caixa/espaços extras; para números, liste formatos aceitos, ex. `["12,5","12.5"]`).

**NUMERIC**: `"answerNumber": 62.5`, `"tolerance": 0.01`, `"unit": "m"` (opcional) e **`"verify": "25**2/(2*5)"`** (obrigatório; expressão Python com `math` — o validador calcula e compara).

**CHART** (interpretação de gráfico): igual a MULTIPLE_CHOICE + `"chart": {"type":"bar|line|table|pie","title":"…","labels":["2019","2020"],"series":[{"name":"Vendas","values":[10,25]}], "unit":"mil"}` (`values` com o mesmo tamanho de `labels`). O app desenha o gráfico/tabela; a questão deve depender dos dados.

**ESSAY** (discursiva com rubrica explícita): `"modelAnswer": "…"`, `"passScore": 0.6`,
`"rubric": [{"criterion":"Identifica X","points":3,"keywords":[["fotossíntese","fotossintese"],["luz","energia luminosa"]]}, ...]` (≥ 3 critérios). Um critério é atendido se **todos os grupos** de `keywords` tiverem pelo menos um termo presente (ignora acentos/caixa). A `modelAnswer` DEVE obter 100% e texto vazio 0% (validador confere). Use radicais/termos robustos e sinônimos.

**CODE** (Python, programação prática; a solução do aluno define uma função):
```json
"code": {
  "starter": "def soma(a, b):\n    # complete\n    pass",
  "reference": "def soma(a, b):\n    return a + b",
  "tests": [
    {"call": "soma(2, 3)", "expected": "5"},
    {"call": "soma(-1, 1)", "expected": "0"},
    {"call": "soma(10, 5)", "expected": "15", "hidden": true}
  ]
}
```
`call` é expressão Python; `expected` é literal Python (comparado por `repr`). ≥ 3 testes, ao menos 1 `"hidden": true`, cobrindo casos de borda. O `starter` não pode passar nos testes. Use apenas biblioteca padrão. A função deve ser pura (sem input()).

