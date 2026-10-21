package com.example.data.model

import com.example.R

data class UserProgress(
    val totalXp: Int = 0,
    val currentStreak: Int = 1,
    val lastActiveDate: String = "",
    val activeStudyDates: Set<String> = emptySet(),
    val lastCheckInDate: String = "",
    val verifiedCheckInDate: String = "",
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
        get() = maxOf(1, currentStreak, activeStudyDates.size)

    val completedTestScores: Map<String, Int>
        get() = completedKahootScores

    fun hasCheckedInToday(): Boolean {
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        return verifiedCheckInDate == today
    }

    fun hasStudiedToday(): Boolean {
        val today = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date())
        return verifiedCheckInDate == today
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
    val activeStudyDaysCount: Int = 1,
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
            description = "Dominou o Ciclo de Matemática Básica (ENEM): frações, MMC/MDC, potências, escalas, porcentagem, estatística e geometria.",
            trackTitle = "Matemática",
            moduleTitle = "Matemática • Ciclo de Matemática Básica",
            xpBonus = 250,
            drawableRes = R.drawable.ic_badge_calculus_limits,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_calculus_limits",
            title = "Mestre dos Limites",
            description = "Completou a seção de Limites, dominou indeterminações e superou o Teste Final de Cálculo I.",
            trackTitle = "Matemática",
            moduleTitle = "Cálculo I • Módulo 1: Limites",
            xpBonus = 300,
            drawableRes = R.drawable.ic_badge_calculus_limits,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_calculus_derivatives",
            title = "Mestre das Taxas",
            description = "Dominou regras de derivação (tombo, produto, quociente, cadeia), aproximação linear e otimização.",
            trackTitle = "Matemática",
            moduleTitle = "Cálculo I • Módulo 2: Derivadas",
            xpBonus = 400,
            drawableRes = R.drawable.ic_badge_calculus_derivatives,
            isAvailable = true
        ),
        CourseBadge(
            id = "badge_calculus_integrals",
            title = "Soberano das Áreas",
            description = "Dominou Somas de Riemann, integrais definidas, substituição, integração por partes e o Teorema Fundamental do Cálculo.",
            trackTitle = "Matemática",
            moduleTitle = "Cálculo I • Módulo 3: Integrais",
            xpBonus = 450,
            drawableRes = R.drawable.ic_badge_calculus_integrals,
            isAvailable = true
        )
    )

    fun findBadge(id: String): CourseBadge? = ALL_BADGES.find { it.id == id }
}
