package com.example.data.assessment

/**
 * Modelo unificado de questões do Knowverse (Etapa 5).
 *
 * Toda questão pertence a UMA aula (`lessonId`) e herda disciplina/módulo/subtópico do índice
 * de aulas ([LessonCatalog]). Os 8 formatos usam o mesmo contêiner [Question].
 */
enum class QuestionFormat(val label: String) {
    MULTIPLE_CHOICE("Múltipla escolha"),
    TRUE_FALSE("Verdadeiro ou falso"),
    MATCHING("Associação"),
    FILL_BLANK("Preenchimento"),
    NUMERIC("Resolução numérica"),
    CHART("Interpretação de gráfico"),
    ESSAY("Discursiva"),
    CODE("Programação prática")
}

enum class QDifficulty(val label: String, val order: Int) {
    EASY("Fácil", 0),
    MEDIUM("Média", 1),
    HARD("Difícil", 2)
}

enum class QuestionSource(val label: String) {
    /** Escrita pela equipe Knowverse (estilo ENEM, mas NÃO é questão oficial). */
    AUTORAL("Autoral Knowverse"),

    /** Exercício do banco anterior do app (mantido; também autoral, sem gabarito oficial). */
    LEGACY("Banco anterior Knowverse"),

    /** Questão de prova e gabarito oficial do INEP/ENEM. */
    INEP_ENEM("Oficial INEP / ENEM")
}

data class CodeTestCase(val call: String, val expected: String, val hidden: Boolean = false)

data class CodeSpec(val starter: String, val reference: String, val tests: List<CodeTestCase>)

data class RubricCriterion(
    val criterion: String,
    val points: Int,
    /** Cada grupo é uma lista de sinônimos; o critério vale quando TODOS os grupos têm ao menos um termo no texto. */
    val keywords: List<List<String>>
)

data class ChartSeries(val name: String, val values: List<Double>)

data class ChartData(
    val type: String, // bar | line | table | pie
    val title: String,
    val labels: List<String>,
    val series: List<ChartSeries>,
    val unit: String = ""
)

data class Question(
    val id: String,
    val lessonId: String,
    val format: QuestionFormat,
    val difficulty: QDifficulty,
    val skill: String,
    val concept: String,
    val statement: String,
    val explanation: String,
    val retryGuidance: String,
    val source: QuestionSource = QuestionSource.AUTORAL,
    // MULTIPLE_CHOICE / CHART
    val options: List<String> = emptyList(),
    val answerIndex: Int = -1,
    val distractorConcepts: List<String> = emptyList(),
    // TRUE_FALSE
    val answerBool: Boolean? = null,
    // MATCHING (esquerda -> direita correta)
    val pairs: List<Pair<String, String>> = emptyList(),
    // FILL_BLANK
    val answers: List<String> = emptyList(),
    // NUMERIC
    val answerNumber: Double? = null,
    val tolerance: Double = 0.0,
    val unit: String = "",
    // CHART
    val chart: ChartData? = null,
    // ESSAY
    val rubric: List<RubricCriterion> = emptyList(),
    val modelAnswer: String = "",
    val passScore: Double = 0.6,
    // CODE
    val code: CodeSpec? = null
) {
    val isLegacy: Boolean get() = source == QuestionSource.LEGACY
}

/** Uma aula do catálogo (derivada de `assessment/lesson_index.json`). */
data class LessonRef(
    val lessonId: String,
    val subject: String,
    val moduleId: String,
    val moduleTitle: String,
    val title: String,
    val subtopic: String,
    /** Posição global da aula na trilha da disciplina (ordem do currículo). */
    val order: Int
)

/** Questão enriquecida com os metadados da aula/módulo/disciplina. */
data class BankQuestion(val question: Question, val lesson: LessonRef) {
    val id: String get() = question.id
    val subject: String get() = lesson.subject
}

// ---------------------------------------------------------------------------------------------
// Respostas e feedback
// ---------------------------------------------------------------------------------------------

sealed class UserAnswer {
    data class Choice(val index: Int) : UserAnswer()
    data class Bool(val value: Boolean) : UserAnswer()

    /** left -> right escolhido pelo aluno. */
    data class Pairs(val mapping: Map<String, String>) : UserAnswer()

    /** Preenchimento, numérica e discursiva. */
    data class Text(val value: String) : UserAnswer()
}

enum class Verdict { CORRECT, PARTIAL, INCORRECT, UNANSWERED }

/**
 * Feedback pedagógico: nunca apenas "certo/errado".
 *  - [reasoning]: raciocínio correto.
 *  - [misunderstoodConcept]: conceito que provavelmente não foi compreendido (quando errou).
 *  - [retryTip]: orientação para tentar de novo.
 */
data class Feedback(
    val verdict: Verdict,
    val score: Double,
    val headline: String,
    val reasoning: String,
    val misunderstoodConcept: String? = null,
    val retryTip: String? = null,
    val details: List<String> = emptyList()
) {
    val isCorrect: Boolean get() = verdict == Verdict.CORRECT
}

