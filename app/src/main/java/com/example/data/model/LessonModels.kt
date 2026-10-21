package com.example.data.model

enum class LessonType {
    LEARN,     // Concept explanation, commented example, execute & explore
    PRACTICE,  // Clear task, initial code, user writes code, validation
    CHALLENGE  // Real-world scenario, multi-step problem solving, automated test cases
}

enum class ValidationType {
    EXACT_OUTPUT,       // Trimmed stdout matches expected
    CONTAINS_OUTPUT,    // stdout contains expected string or line
    NUMERICAL_RESULT,   // Evaluates numerical answer in output
    FUNCTION_RESULT     // Verifies function behavior with multiple test runs
}

enum class DifficultyTier {
    BASIC,          // Fundamentos completos, estruturas de dados, laços e funções
    INTERMEDIATE,   // Manipulação de strings, cifras, lambdas, compreensões, exceções e algoritmos
    ADVANCED        // POO completa, herança, dunder methods, geradores, regex, estruturas avançadas
}

data class TestCase(
    val name: String,
    val stdinInput: String = "",
    val expectedOutput: String,
    val validationType: ValidationType = ValidationType.EXACT_OUTPUT,
    val isHidden: Boolean = false
)

data class MathExercise(
    val id: String,
    val title: String,
    val problemStatement: String,
    val options: List<String>,
    val correctIndex: Int,
    val hint: String,
    val stepByStepSolution: String,
    val geoGebraConfig: com.example.data.math.GeoGebraGraphConfig? = null
) {
    /**
     * Gera uma cópia do exercício com as alternativas embaralhadas aleatoriamente,
     * garantindo que a resposta correta não fique fixa na primeira posição.
     */
    fun randomized(): MathExercise {
        if (options.size < 2) return this
        val correctValue = options.getOrNull(correctIndex) ?: options[0]
        val shuffledOptions = options.shuffled()
        val newCorrectIndex = shuffledOptions.indexOf(correctValue)
        return copy(
            options = shuffledOptions,
            correctIndex = if (newCorrectIndex >= 0) newCorrectIndex else 0
        )
    }
}

data class LessonExample(
    val title: String,
    val codeOrFormula: String,
    val explanation: String
)

data class FormulaOrSyntaxItem(
    val termOrFunction: String,
    val syntax: String,
    val description: String,
    val parametersInfo: String = ""
)

data class Lesson(
    val id: String,
    val unitId: String,
    val title: String,
    val conceptTitle: String,
    val explanation: String,
    val lessonType: LessonType,
    val initialCode: String,
    val hints: List<String>,
    val solutionExplanation: String,
    val xpReward: Int = 25,
    val testCases: List<TestCase> = emptyList(),
    val imageResId: Int? = null,
    val mathExercises: List<MathExercise> = emptyList(),
    val theorySection: String = "",
    val formulasOrSyntax: List<FormulaOrSyntaxItem> = emptyList(),
    val examples: List<LessonExample> = emptyList()
)

data class KahootQuestion(
    val id: String,
    val question: String,
    val codeSnippet: String? = null,
    val options: List<String>, // Exactly 4 options
    val correctIndex: Int,      // 0..3
    val explanation: String,
    val conceptTag: String = "Conceito Geral",
    val recommendedLessonId: String? = null,
    val recommendedLessonTitle: String? = null
)

// Alias for clear semantics
typealias FinalTestQuestion = KahootQuestion

data class KahootModule(
    val id: String,
    val tier: DifficultyTier,
    val title: String,
    val description: String,
    val questions: List<KahootQuestion>,
    val category: String = "Programação",
    val subject: String = "Python"
)

typealias FinalTestModule = KahootModule

data class LearningUnit(
    val id: String,
    val unitNumber: Int,
    val tier: DifficultyTier = DifficultyTier.BASIC,
    val title: String,
    val description: String,
    val lessons: List<Lesson>,
    val kahootModule: KahootModule? = null,
    val category: String = "Programação",
    val subject: String = "Python"
)

data class KnowledgeArea(
    val id: String,
    val title: String,
    val description: String,
    val iconKey: String, // "PYTHON", "MATH", "AI", "DATA"
    val isAvailable: Boolean,
    val totalLessons: Int,
    val estimatedHours: String,
    val category: String
)
