package com.example.data.languages

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

/**
 * Provedor Central e Fachada Didática da Trilha de Linguagens ENEM.
 * Roteia as requisições para os módulos específicos:
 * - Módulo 1 (ling_sem_...): Interpretação de Texto e Semântica
 * - Módulo 2 (ling_com_...): Linguística e Comunicação
 * - Módulo 3 (ling_lit_...): Literatura Brasileira
 * - Módulo 4 (ling_gram_...): Gramática Aplicada ao Texto
 * - Módulo 5 (ling_art_...): Artes e Expressão Corporal
 */
object EnemLanguagesDidacticProvider {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        val lesson = EnemLanguagesCurriculum.findLesson(lessonId) ?: return null
        return when {
            lessonId.startsWith("ling_sem_") -> LanguagesInterpretationDidactic.buildBundle(lesson)
            lessonId.startsWith("ling_com_") -> LanguagesLinguisticsDidactic.buildBundle(lesson)
            lessonId.startsWith("ling_lit_") -> LanguagesLiteratureDidactic.buildBundle(lesson)
            lessonId.startsWith("ling_gram_") -> LanguagesGrammarDidactic.buildBundle(lesson)
            lessonId.startsWith("ling_art_") -> LanguagesArtsBodyDidactic.buildBundle(lesson)
            else -> null
        }
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise> {
        val lesson = EnemLanguagesCurriculum.findLesson(lessonId) ?: return emptyList()
        val exercises = when {
            lessonId.startsWith("ling_sem_") -> LanguagesInterpretationDidactic.buildExercises(lesson)
            lessonId.startsWith("ling_com_") -> LanguagesLinguisticsDidactic.buildExercises(lesson)
            lessonId.startsWith("ling_lit_") -> LanguagesLiteratureDidactic.buildExercises(lesson)
            lessonId.startsWith("ling_gram_") -> LanguagesGrammarDidactic.buildExercises(lesson)
            lessonId.startsWith("ling_art_") -> LanguagesArtsBodyDidactic.buildExercises(lesson)
            else -> emptyList()
        }
        return exercises.map { it.randomized() }
    }
}
