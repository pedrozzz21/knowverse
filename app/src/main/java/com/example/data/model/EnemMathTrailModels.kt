package com.example.data.model

/**
 * =====================================================================
 * ESTRUTURA DE DADOS & TAXONOMIA: TRILHA DE MATEMÁTICA PARA O ENEM
 * Hierarquia: Disciplina -> Módulo (Tópico) -> Aula Sequencial (Subtópico)
 *
 * ESPECIFICAÇÃO DE TIPAGEM (TYPESCRIPT EQUIVALENT):
 * ```typescript
 * export interface EnemTrailDiscipline {
 *   id: string; // "matematica_enem"
 *   name: string; // "Matemática ENEM"
 *   description: string;
 *   totalModules: number; // 14
 *   totalLessons: number; // 81
 *   modules: EnemTrailModule[];
 * }
 *
 * export interface EnemTrailModule {
 *   id: string; // "enem_mat_mod_1"
 *   moduleNumber: number; // 1..14
 *   title: string; // "Matemática Básica"
 *   summary: string;
 *   accentColorHex: string; // "#00E676" (Verde Neon)
 *   lessons: EnemTrailLesson[];
 *   finalSimulatedExam: EnemTrailSimulado;
 * }
 *
 * export interface EnemTrailLesson {
 *   id: string; // "enem_m1_a1"
 *   moduleId: string;
 *   lessonIndex: number; // 1..10
 *   title: string; // "Aula 1: Operações com números inteiros, fracionários e decimais"
 *   subtopic: string;
 *   xpReward: number; // 30
 *   isUnlocked: boolean;
 *   isCompleted: boolean;
 *   // Estrutura reservada para inserção passo a passo de conteúdo futuro:
 *   content?: {
 *     theoryTextMarkdown: string;
 *     keyFormulas: string[];
 *     stepByStepExamples: Array<{ title: string; explanation: string; mathExpression: string }>;
 *     exerciseIds: string[];
 *   };
 * }
 *
 * export interface EnemTrailSimulado {
 *   id: string;
 *   moduleId: string;
 *   title: string; // "Simulado Final"
 *   lockNotice: string; // "Bloqueado: Conclua todas as aulas do módulo para liberar"
 *   isLocked: boolean;
 *   requiredLessonIds: string[];
 *   xpReward: number; // 100
 *   questionCount: number;
 * }
 * ```
 * =====================================================================
 */

data class EnemTrailDiscipline(
    val id: String = "matematica_enem",
    val name: String = "Matemática ENEM",
    val description: String = "Trilha completa e sequencial de Matemática para o ENEM com 14 módulos fundamentais e simulados diagnósticos.",
    val modules: List<EnemTrailModule>
)

data class EnemTrailModule(
    val id: String,
    val moduleNumber: Int,
    val title: String,
    val summary: String,
    val lessons: List<EnemTrailLesson>,
    val finalSimulado: EnemTrailSimulado
) {
    val totalLessons: Int get() = lessons.size
}

data class EnemTrailLesson(
    val id: String,
    val moduleId: String,
    val lessonIndex: Int,
    val title: String,
    val subtopic: String,
    val xpReward: Int = 30,
    val isUnlocked: Boolean = false,
    val isCompleted: Boolean = false,
    // Campo reservado para receber o conteúdo das aulas quando enviado pelo usuário:
    val theoryMarkdown: String = "",
    val exercises: List<MathExercise> = emptyList(),
    val isContentPending: Boolean = true
) {
    /**
     * Mapeador de compatibilidade para o sistema de execução de aulas existente.
     */
    fun toAppLesson(): Lesson {
        return Lesson(
            id = id,
            unitId = moduleId,
            title = title,
            conceptTitle = subtopic,
            explanation = if (theoryMarkdown.isNotBlank()) theoryMarkdown
            else "Aula da Trilha Oficial de Matemática para o ENEM.\n\nSubtópico: $subtopic.\n\nO conteúdo didático detalhado e os exercícios estão sendo integrados nesta aula.",
            lessonType = LessonType.LEARN,
            initialCode = "",
            hints = listOf("Revise os conceitos fundamentais deste subtópico.", "Aplique os métodos matemáticos recomendados para o ENEM."),
            solutionExplanation = "Conceito dominado com sucesso!",
            xpReward = xpReward,
            mathExercises = exercises
        )
    }
}

data class EnemTrailSimulado(
    val id: String,
    val moduleId: String,
    val title: String = "Simulado Final",
    val lockNotice: String = "Bloqueado: Conclua todas as aulas do módulo para liberar",
    val isLocked: Boolean = true,
    val xpReward: Int = 100,
    val questionCount: Int = 10
) {
    /**
     * Mapeador de compatibilidade para o executor de simulados / testes finais.
     */
    fun toKahootModule(moduleTitle: String): KahootModule {
        return KahootModule(
            id = id,
            tier = DifficultyTier.BASIC,
            title = "Simulado Final: $moduleTitle",
            description = "Avaliação diagnóstica de consolidação do $moduleTitle.",
            questions = emptyList(),
            category = "Matemática",
            subject = "Matemática ENEM"
        )
    }
}
