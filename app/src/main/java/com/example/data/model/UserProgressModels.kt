package com.example.data.model

import com.example.R

data class UserProgress(
    val totalXp: Int = 0,
    val currentStreak: Int = 0,
    val lastActiveDate: String = "",
    val activeStudyDates: Set<String> = emptySet(),
    val lastCheckInDate: String = "",
    val verifiedCheckInDate: String = "",
    val lastQualifyingStudyDate: String = "",
    val completedLessonIds: Set<String> = emptySet(),
    val completedKahootScores: Map<String, Int> = emptyMap(),
    val perfectTestModules: Set<String> = emptySet(),
    val unlockedBadges: Set<String> = emptySet(),
    val currentLessonId: String = "py_b1_l1",
    val favoriteAreaId: String = "python",
    val reviewLessonIds: Set<String> = emptySet(),
    val dailyXp: Int = 0,
    val dailyGoalXp: Int = 50,
    val totalExecutions: Int = 0,
    val totalSolvedExercises: Int = 0
) {
    val level: Int
        get() = (totalXp / 100) + 1

    val currentLevelProgress: Float
        get() = ((totalXp % 100).toFloat() / 100f).coerceIn(0f, 1f)

    val xpToNextLevel: Int
        get() = 100 - (totalXp % 100)

    val activeStudyDaysCount: Int
        get() {
            if (totalXp == 0 && completedLessonIds.isEmpty() && totalSolvedExercises == 0) return 0
            return maxOf(0, if (lastQualifyingStudyDate.isNotBlank()) currentStreak else 0, activeStudyDates.size)
        }

    val completedTestScores: Map<String, Int>
        get() = completedKahootScores

    fun hasCheckedInToday(): Boolean {
        val today = com.example.data.time.NetworkTimeProvider.getTodayGmt3()
        return lastQualifyingStudyDate == today
    }

    fun hasStudiedToday(): Boolean {
        val today = com.example.data.time.NetworkTimeProvider.getTodayGmt3()
        return lastQualifyingStudyDate == today
    }

    /**
     * Tier gating rule with full backward compatibility for users from older app versions:
     * - BASIC is always unlocked.
     * - INTERMEDIATE is unlocked if user completed basic final test (100% or legacy score/badge) or already started intermediate lessons.
     * - ADVANCED is unlocked if user completed intermediate final test (100% or legacy score/badge) or already started advanced lessons.
     */
    fun isTierUnlocked(tier: DifficultyTier): Boolean {
        return when (tier) {
            DifficultyTier.BASIC -> true
            DifficultyTier.INTERMEDIATE -> {
                perfectTestModules.contains("kahoot_basic") ||
                        perfectTestModules.contains("test_basic") ||
                        unlockedBadges.contains("badge_python_basic") ||
                        (completedKahootScores["kahoot_basic"] ?: 0) >= 80 ||
                        (completedKahootScores["test_basic"] ?: 0) >= 80 ||
                        completedLessonIds.count { it.startsWith("py_b") } >= 8 ||
                        completedLessonIds.any { it.startsWith("py_i") || it.startsWith("py_a") }
            }
            DifficultyTier.ADVANCED -> {
                (isTierUnlocked(DifficultyTier.INTERMEDIATE) && (
                    perfectTestModules.contains("kahoot_intermediate") ||
                            perfectTestModules.contains("test_intermediate") ||
                            unlockedBadges.contains("badge_python_intermediate") ||
                            (completedKahootScores["kahoot_intermediate"] ?: 0) >= 80 ||
                            (completedKahootScores["test_intermediate"] ?: 0) >= 80 ||
                            completedLessonIds.count { it.startsWith("py_i") } >= 8
                )) || unlockedBadges.contains("badge_python_advanced") ||
                        completedLessonIds.any { it.startsWith("py_a") }
            }
        }
    }
}

data class CheckInResult(
    val isNewCheckInToday: Boolean,
    val streak: Int,
    val totalActiveDays: Int,
    val xpAwarded: Int = 0
)

data class UserProgressAdminRecord(
    val userId: String,
    val email: String,
    val displayName: String,
    val photoUrl: String? = null,
    val totalXp: Int,
    val level: Int,
    val currentStreak: Int,
    val activeStudyDaysCount: Int = 0,
    val completedLessonsCount: Int,
    val completedLessons: List<String>,
    val kahootScores: Map<String, Int> = emptyMap(),
    val testScores: Map<String, Int> = emptyMap(),
    val badges: List<String> = emptyList(),
    val lastActiveDate: String,
    val isDev: Boolean = false
)

data class Achievement(
    val id: String,
    val title: String,
    val description: String,
    val xpBonus: Int,
    val isUnlocked: Boolean,
    val progress: Float = 0f,
    val iconKey: String = "TROPHY"
)

data class CourseBadge(
    val id: String,
    val title: String,
    val description: String,
    val trackTitle: String,
    val moduleTitle: String,
    val xpBonus: Int,
    val drawableRes: Int,
    val isAvailable: Boolean = true
)

object TrackBadgesCatalog {
    val ALL_BADGES = listOf(
        CourseBadge(
            id = "badge_python_basic",
            title = "Pioneiro Python",
            description = "Concluiu todas as aulas e gabaritou o Teste Final do Módulo 1 (Introdução).",
            trackTitle = "Programação",
            moduleTitle = "Python • Módulo 1: Introdução",
            xpBonus = 250,
            drawableRes = R.drawable.ic_badge_python_basic,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_python_intermediate",
            title = "Alquimista Python",
            description = "Dominou lambdas, compreensões de listas, tratamento de erros e gabaritou o Teste Final Intermediário.",
            trackTitle = "Programação",
            moduleTitle = "Python • Módulo 2: Intermediário",
            xpBonus = 350,
            drawableRes = R.drawable.ic_badge_python_intermediate,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_python_advanced",
            title = "Arquiteto Python",
            description = "Alcançou o ápice da Orientação a Objetos, geradores, decorators e gabaritou o Teste Final Avançado.",
            trackTitle = "Programação",
            moduleTitle = "Python • Módulo 3: Avançado",
            xpBonus = 500,
            drawableRes = R.drawable.ic_badge_python_advanced,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_math_basic",
            title = "Alicerce da Matemática",
            description = "Dominou o Ciclo de Matemática Básica (ENEM): frações, MMC/MDC, potências, escalas, porcentagem e geometria.",
            trackTitle = "Matemática",
            moduleTitle = "Matemática • Ciclo de Matemática Básica",
            xpBonus = 250,
            drawableRes = R.drawable.ic_badge_enem_math,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_enem_math",
            title = "Estrategista dos Números",
            description = "Dominou a Trilha Oficial Completa de Matemática ENEM (Funções, Trigonometria, Estatística e Espacial).",
            trackTitle = "Matemática ENEM",
            moduleTitle = "Matemática ENEM • Conclusão Integral",
            xpBonus = 500,
            drawableRes = R.drawable.ic_badge_enem_math,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_calculus_limits",
            title = "Mestre dos Limites",
            description = "Completou a seção de Limites, dominou indeterminações e superou o Teste Final de Cálculo I.",
            trackTitle = "Matemática Superior",
            moduleTitle = "Cálculo I • Módulo 1: Limites",
            xpBonus = 300,
            drawableRes = R.drawable.ic_badge_calculus_limits,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_calculus_derivatives",
            title = "Mestre das Taxas",
            description = "Dominou regras de derivação (tombo, produto, quociente, cadeia), aproximação linear e otimização.",
            trackTitle = "Matemática Superior",
            moduleTitle = "Cálculo I • Módulo 2: Derivadas",
            xpBonus = 400,
            drawableRes = R.drawable.ic_badge_calculus_derivatives,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_calculus_integrals",
            title = "Soberano das Áreas",
            description = "Dominou Somas de Riemann, integrais definidas, substituição, integração por partes e o TFC.",
            trackTitle = "Matemática Superior",
            moduleTitle = "Cálculo I • Módulo 3: Integrais",
            xpBonus = 450,
            drawableRes = R.drawable.ic_badge_calculus_integrals,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_enem_chemistry",
            title = "Alquimista do ENEM",
            description = "Dominou Química Geral, Físico-Química, Química Orgânica e Meio Ambiente com resolução de questões oficiais.",
            trackTitle = "Ciências da Natureza",
            moduleTitle = "Química • Trilha Oficial ENEM",
            xpBonus = 400,
            drawableRes = R.drawable.ic_badge_enem_chemistry,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_enem_physics",
            title = "Newtoniano Moderno",
            description = "Compreendeu Mecânica, Termologia, Ondulatória, Óptica e Eletricidade aplicadas à prova de Natureza do ENEM.",
            trackTitle = "Ciências da Natureza",
            moduleTitle = "Física • Trilha Oficial ENEM",
            xpBonus = 400,
            drawableRes = R.drawable.ic_badge_enem_physics,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_enem_biology",
            title = "Naturalista da Vida",
            description = "Gabaritou Ecologia, Citologia, Genética, Evolução e Fisiologia Humana no padrão ENEM.",
            trackTitle = "Ciências da Natureza",
            moduleTitle = "Biologia • Trilha Oficial ENEM",
            xpBonus = 400,
            drawableRes = R.drawable.ic_badge_enem_biology,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_enem_geography",
            title = "Mapeador Global",
            description = "Dominou Geografia Física, Espaço Agrário, Urbanização, Geopolítica e Meio Ambiente do ENEM.",
            trackTitle = "Ciências Humanas",
            moduleTitle = "Geografia • Trilha Oficial ENEM",
            xpBonus = 350,
            drawableRes = R.drawable.ic_badge_enem_geography,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_enem_history",
            title = "Cronista das Eras",
            description = "Dominou a linha do tempo de História do Brasil (Colônia a República) e História Geral do ENEM.",
            trackTitle = "Ciências Humanas",
            moduleTitle = "História • Trilha Oficial ENEM",
            xpBonus = 350,
            drawableRes = R.drawable.ic_badge_enem_history,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_enem_languages",
            title = "Mestre dos Discursos",
            description = "Alcançou excelência em Interpretação Textual, Figuras de Linguagem, Funções da Linguagem e Vanguardas.",
            trackTitle = "Linguagens e Códigos",
            moduleTitle = "Linguagens • Trilha Oficial ENEM",
            xpBonus = 350,
            drawableRes = R.drawable.ic_badge_enem_languages,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_streak_3",
            title = "Chama Sagrada",
            description = "Manteve 3 dias consecutivos de estudos diários completando lições e exercícios até as 00h00.",
            trackTitle = "Sequência de Foco",
            moduleTitle = "Sequência Diária • 3 Dias Consecutivos",
            xpBonus = 150,
            drawableRes = R.drawable.ic_badge_streak_3,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_streak_7",
            title = "Fogo Eterno",
            description = "Completou 7 dias ininterruptos de foco absoluto, mantendo a chama acesa semana adentro.",
            trackTitle = "Sequência de Foco",
            moduleTitle = "Sequência Diária • Semana de Ouro (7 Dias)",
            xpBonus = 300,
            drawableRes = R.drawable.ic_badge_streak_7,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_streak_14",
            title = "Guardião da Disciplina",
            description = "Alcançou 14 dias seguidos de foco no Knowverse, forjando um hábito indestrutível de estudos.",
            trackTitle = "Sequência de Foco",
            moduleTitle = "Sequência Diária • 14 Dias Consecutivos",
            xpBonus = 500,
            drawableRes = R.drawable.ic_badge_streak_14,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_streak_30",
            title = "Centurião do Foco",
            description = "30 dias consecutivos de estudos! Conquistou o respeito eterno da comunidade Knowverse.",
            trackTitle = "Sequência de Foco",
            moduleTitle = "Sequência Diária • 30 Dias de Maestria",
            xpBonus = 1000,
            drawableRes = R.drawable.ic_badge_streak_30,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_exercises_10",
            title = "Atirador de Elite",
            description = "Resolveu e acertou 10 questões no Banco Universal de Exercícios e Simulados.",
            trackTitle = "Banco de Exercícios",
            moduleTitle = "Exercícios • 10 Questões Resolvidas",
            xpBonus = 150,
            drawableRes = R.drawable.ic_badge_exercises_10,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_exercises_25",
            title = "Desafiador Destemido",
            description = "Superou 25 questões oficiais do ENEM e vestibulares com consistência.",
            trackTitle = "Banco de Exercícios",
            moduleTitle = "Exercícios • 25 Questões Gabaritadas",
            xpBonus = 250,
            drawableRes = R.drawable.ic_badge_exercises_25,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_exercises_50",
            title = "Gabaritador Implacável",
            description = "Resolveu e acertou 50 questões no Banco Universal de Exercícios e Simulados.",
            trackTitle = "Banco de Exercícios",
            moduleTitle = "Exercícios • 50 Questões Gabaritadas",
            xpBonus = 450,
            drawableRes = R.drawable.ic_badge_exercises_50,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_exercises_100",
            title = "Centurião das Questões",
            description = "Atingiu a marca lendária de 100 exercícios resolvidos no Knowverse!",
            trackTitle = "Banco de Exercícios",
            moduleTitle = "Exercícios • 100 Questões Conquistadas",
            xpBonus = 1000,
            drawableRes = R.drawable.ic_badge_exercises_100,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_exercises_250",
            title = "Máquina de Resolver",
            description = "Superou 250 questões com rigor analítico, dominando a prova com velocidade e precisão.",
            trackTitle = "Banco de Exercícios",
            moduleTitle = "Exercícios • 250 Questões Gabaritadas",
            xpBonus = 1500,
            drawableRes = R.drawable.ic_badge_exercises_250,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_exercises_500",
            title = "Lenda dos 500 Acertos",
            description = "Alcançou o marco monumental de 500 exercícios resolvidos. Uma verdadeira força dos estudos!",
            trackTitle = "Banco de Exercícios",
            moduleTitle = "Exercícios • Meio Milhar de Questões",
            xpBonus = 2500,
            drawableRes = R.drawable.ic_badge_exercises_500,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_exercises_1000",
            title = "O Imperador do ENEM",
            description = "1.000 questões dominadas! Conquistou o patamar supremo de aprovação e maestria definitiva.",
            trackTitle = "Banco de Exercícios",
            moduleTitle = "Exercícios • 1.000 Questões Supremas",
            xpBonus = 5000,
            drawableRes = R.drawable.ic_badge_exercises_1000,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_exercises_2000",
            title = "Soberano Supremo",
            description = "2.000 questões superadas! Esgotou o banco de dados e atingiu a onisciência acadêmica.",
            trackTitle = "Banco de Exercícios",
            moduleTitle = "Exercícios • 2.000 Questões Lendárias",
            xpBonus = 10000,
            drawableRes = R.drawable.ic_badge_exercises_2000,
            isAvailable = true
        )
    )

    fun findBadge(id: String): CourseBadge? = ALL_BADGES.find { it.id == id }
}
