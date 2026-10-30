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
    val moduleTitle: String = "",
    val difficulty: String = ""
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
            moduleId.startsWith("mod_hist_") || topic.contains("História", ignoreCase = true) ||
                subtopic.contains("História", ignoreCase = true) || moduleTitle.contains("História", ignoreCase = true) ||
                topic.contains("Humanas", ignoreCase = true) || moduleTitle.contains("Colônia", ignoreCase = true) ||
                moduleTitle.contains("Império", ignoreCase = true) || moduleTitle.contains("República", ignoreCase = true) -> ExerciseDiscipline.HISTORY

            moduleId.startsWith("mod_ling_") || topic.contains("Linguagens", ignoreCase = true) ||
                subtopic.contains("Linguagens", ignoreCase = true) || moduleTitle.contains("Linguagens", ignoreCase = true) ||
                moduleTitle.contains("Interpretação", ignoreCase = true) || moduleTitle.contains("Linguística", ignoreCase = true) ||
                moduleTitle.contains("Literatura", ignoreCase = true) || moduleTitle.contains("Gramática", ignoreCase = true) ||
                moduleTitle.contains("Artes", ignoreCase = true) -> ExerciseDiscipline.LANGUAGES

            moduleId.startsWith("mod_fis_") || topic.contains("Física", ignoreCase = true) ||
                subtopic.contains("Física", ignoreCase = true) || moduleTitle.contains("Física", ignoreCase = true) ||
                moduleTitle.contains("Mecânica", ignoreCase = true) || moduleTitle.contains("Eletricidade", ignoreCase = true) ||
                moduleTitle.contains("Termologia", ignoreCase = true) || moduleTitle.contains("Ondulatória", ignoreCase = true) ||
                moduleTitle.contains("Óptica", ignoreCase = true) || moduleTitle.contains("Moderna", ignoreCase = true) -> ExerciseDiscipline.PHYSICS

            moduleId.startsWith("mod_bio_") || topic.contains("Biologia", ignoreCase = true) ||
                subtopic.contains("Biologia", ignoreCase = true) || moduleTitle.contains("Biologia", ignoreCase = true) ||
                moduleTitle.contains("Ecologia", ignoreCase = true) || moduleTitle.contains("Fisiologia", ignoreCase = true) ||
                moduleTitle.contains("Genética", ignoreCase = true) || moduleTitle.contains("Citologia", ignoreCase = true) ||
                moduleTitle.contains("Evolução", ignoreCase = true) -> ExerciseDiscipline.BIOLOGY

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
            difficulty = resolvePedagogicalDifficulty(
                explicitDifficulty = difficulty,
                statement = title,
                topic = topic,
                subtopic = effectiveSubtopic
            ),
            source = "ENEM $year / Prova Oficial",
            problemStatement = title,
            codeSnippet = null,
            visualDrawableRes = null,
            visualTable = null,
            terminalOutput = null,
            geoGebraConfig = null,
            options = shuffledAlternatives,
            correctIndex = newCorrectIndex,
            solutionSteps = when {
                resolvedDiscipline == ExerciseDiscipline.BIOLOGY -> {
                    com.example.data.biology.BiologyAlternativeAnalyzer.generateBiologyDeepAnalysis(
                        statement = title,
                        options = shuffledAlternatives,
                        correctIndex = newCorrectIndex,
                        subtopic = effectiveSubtopic
                    )
                }
                explanation.isNotBlank() -> {
                    listOf(
                        "Análise Pedagógica: $explanation",
                        "Aplicação dos Conceitos: Exame focado em competências de $effectiveSubtopic.",
                        "Gabarito Oficial: Alternativa correta é a letra $newCorrectLetter."
                    )
                }
                else -> {
                    listOf(
                        "Análise do Enunciado: Identificar os dados fornecidos e a pergunta principal.",
                        "Aplicação Conceitual: Utilizar os conceitos e propriedades de $effectiveSubtopic.",
                        "Gabarito Oficial: Alternativa correta é a letra $newCorrectLetter."
                    )
                }
            },
            pedagogicalAlert = if (moduleTitle.isNotBlank()) "Módulo: $moduleTitle | ENEM $year" else "Questão autêntica do ENEM $year no tópico de $topic.",
            keyMethod = "Módulo ENEM: $effectiveSubtopic",
            xpReward = 45
        )
    }

    companion object {
        fun normalizeDifficulty(diff: String): String {
            return when (diff.trim().lowercase()) {
                "fácil", "facil", "básico", "basico", "iniciante", "1" -> "Fácil"
                "difícil", "dificil", "desafiador", "avançado", "avancado", "expert", "3" -> "Difícil"
                else -> "Média"
            }
        }

        fun resolvePedagogicalDifficulty(
            explicitDifficulty: String,
            statement: String,
            topic: String,
            subtopic: String
        ): String {
            if (explicitDifficulty.isNotBlank()) {
                return normalizeDifficulty(explicitDifficulty)
            }
            return classifyPedagogicalDifficulty(statement, topic, subtopic)
        }

        fun classifyPedagogicalDifficulty(statement: String, topic: String, subtopic: String): String {
            val text = (statement + " " + topic + " " + subtopic).lowercase()
            val length = statement.length

            val hardConcepts = listOf(
                "desvio padrão", "variância", "juros compostos", "amortização", "tabela price",
                "probabilidade condicional", "análise combinatória", "permutação", "combinação",
                "tronco de pirâmide", "tronco de cone", "esfera", "indução eletromagnética",
                "lei de lenz", "lei de faraday", "efeito fotoelétrico", "relatividade restrita",
                "dilatação temporal", "fissão nuclear", "fusão nuclear", "ponte de wheatstone",
                "empuxo de arquimedes", "linkage", "ligação gênica", "crossing-over", "eletroforese",
                "dna recombinante", "termodinâmica de carnot", "ciclo de carnot", "hidrostática"
            )

            val easyConcepts = listOf(
                "gráfico de colunas", "gráfico de barras", "título", "legenda", "pictograma",
                "leitura direta", "unidade de medida", "porcentagem simples", "média aritmética simples",
                "moda", "mediana de rol", "habitat", "nicho", "consumidor primário", "produtor",
                "decomposição", "mutualismo", "comensalismo", "inquilinismo", "teoria celular",
                "ribossomo", "parede celular", "órgão vestigial", "fóssil", "vacina", "soro"
            )

            val hasHard = hardConcepts.any { text.contains(it) }
            val hasEasy = easyConcepts.any { text.contains(it) }

            var score = 0
            if (hasHard) score += 3
            if (hasEasy) score -= 2

            val numbers = Regex("""\b\d+(?:[.,]\d+)?\b""").findAll(statement).count()
            val mathOps = Regex("""[=+\-*/^√%]|10\^""").findAll(statement).count()
            val calcDensity = numbers + (mathOps * 1.5)

            if (calcDensity > 18) score += 2 else if (calcDensity < 4 && !hasHard) score -= 1
            if (length > 1300) score += 1 else if (length < 550 && !hasHard) score -= 1

            return when {
                score <= -1 -> "Fácil"
                score >= 2 -> "Difícil"
                else -> "Média"
            }
        }
    }
}
