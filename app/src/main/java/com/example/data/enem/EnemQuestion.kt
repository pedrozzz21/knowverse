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
    val explanation: String = "",
    val moduleId: String = "",
    val moduleTitle: String = ""
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
        val resolvedDiscipline = when {
            topic.contains("Física", ignoreCase = true) || subtopic.contains("Física", ignoreCase = true) -> ExerciseDiscipline.PHYSICS
            topic.contains("Biologia", ignoreCase = true) || subtopic.contains("Biologia", ignoreCase = true) -> ExerciseDiscipline.BIOLOGY
            topic.contains("Natureza", ignoreCase = true) -> {
                val text = (title + " " + alternatives.joinToString(" ")).lowercase()
                if (text.contains("força") || text.contains("velocidade") || text.contains("aceleração") ||
                    text.contains("energia") || text.contains("calor") || text.contains("onda") ||
                    text.contains("circuito") || text.contains("corrente") || text.contains("potência") ||
                    text.contains("tensão") || text.contains("óptica") || text.contains("cinemática") ||
                    text.contains("elétrica") || text.contains("gravitacional")
                ) {
                    ExerciseDiscipline.PHYSICS
                } else {
                    ExerciseDiscipline.BIOLOGY
                }
            }
            else -> ExerciseDiscipline.BASIC_MATH
        }

        // Embaralha as alternativas aleatoriamente (Fisher-Yates) mantendo a correspondência do gabarito
        val cleanAlternatives = alternatives.map { alt ->
            alt.replace(Regex("^(\\([A-E]\\)|[A-E]\\)|\\b[A-E]\\s*[-–:]\\s*)", RegexOption.IGNORE_CASE), "").trim()
        }
        val indexed = cleanAlternatives.mapIndexed { idx, text ->
            Pair(text, idx == correctIndex)
        }.shuffled()

        val shuffledAlternatives = indexed.mapIndexed { idx, pair ->
            val letter = ('A' + idx)
            "$letter) ${pair.first}"
        }
        val newCorrectIndex = indexed.indexOfFirst { it.second }.let { if (it >= 0) it else 0 }
        val newCorrectLetter = ('A' + newCorrectIndex).toString()

        val effectiveSubtopic = when {
            moduleTitle.isNotBlank() -> moduleTitle
            subtopic.isNotBlank() -> subtopic
            else -> topic
        }

        return UniversalExercise(
            id = id,
            discipline = resolvedDiscipline,
            category = "ENEM $year - ${if (moduleTitle.isNotBlank()) moduleTitle else topic}",
            subtopic = effectiveSubtopic,
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
            options = shuffledAlternatives,
            correctIndex = newCorrectIndex,
            solutionSteps = listOf(
                "Análise do Enunciado: Identificar os dados fornecidos e a pergunta principal.",
                "Aplicação Conceitual: Utilizar os conceitos e propriedades de $effectiveSubtopic.",
                "Gabarito Oficial: Alternativa correta é a letra $newCorrectLetter."
            ),
            pedagogicalAlert = if (moduleTitle.isNotBlank()) "Módulo: $moduleTitle | ENEM $year" else "Questão autêntica do ENEM $year no tópico de $topic.",
            keyMethod = "Módulo ENEM: $effectiveSubtopic",
            xpReward = 45
        )
    }
}
