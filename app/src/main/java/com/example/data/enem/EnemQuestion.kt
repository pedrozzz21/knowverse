package com.example.data.enem

import com.example.data.exercise.ExerciseDiscipline
import com.example.data.exercise.UniversalExercise

/**
 * Modelo de dados para questão do ENEM (compatível com a API enem-api).
 */
data class EnemQuestion(
    val id: String,
    val year: Int,
    val topic: String,       // Tópico principal (ex: "Matemática Básica")
    val subtopic: String,    // Subtópico exato da taxonomia
    val title: String,       // Enunciado completo da questão
    val alternatives: List<String>,
    val correctAnswer: String, // "A", "B", "C", "D", "E"
    val explanation: String = ""
) {
    /**
     * Retorna o índice numérico (0 a 4) correspondente à alternativa correta.
     */
    val correctIndex: Int
        get() {
            val letter = correctAnswer.trim().uppercase()
            return when {
                letter.startsWith("A") -> 0
                letter.startsWith("B") -> 1
                letter.startsWith("C") -> 2
                letter.startsWith("D") -> 3
                letter.startsWith("E") -> 4
                else -> {
                    val idx = alternatives.indexOfFirst {
                        it.trim().equals(correctAnswer.trim(), ignoreCase = true)
                    }
                    if (idx >= 0) idx else 0
                }
            }
        }

    /**
     * Converte o EnemQuestion para o formato UniversalExercise do Knowverse,
     * permitindo que ele seja resolvido na UI unificada de exercícios.
     */
    fun toUniversalExercise(): UniversalExercise {
        return UniversalExercise(
            id = id,
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "ENEM $year - $topic",
            subtopic = subtopic,
            difficulty = when (year) {
                2024, 2023 -> "Mediano"
                2022 -> "Fácil"
                else -> "Desafiador"
            },
            source = "ENEM $year / Prova Oficial",
            problemStatement = title,
            codeSnippet = null,
            visualDrawableRes = null,
            visualTable = null,
            terminalOutput = null,
            geoGebraConfig = null,
            options = alternatives,
            correctIndex = correctIndex,
            solutionSteps = listOf(
                "Análise do Enunciado: Identificar os dados fornecidos e a pergunta principal.",
                "Aplicação Conceitual: Utilizar os conceitos e propriedades de $subtopic.",
                "Gabarito Oficial: Alternativa correta é a letra ${correctAnswer.uppercase()}."
            ),
            pedagogicalAlert = "Questão autêntica do ENEM $year no tópico de $topic.",
            keyMethod = "Taxonomia ENEM: $subtopic",
            xpReward = 45
        )
    }
}
