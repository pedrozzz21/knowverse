package com.example.data.history

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

/**
 * Provedor Central e Fachada Didática da Trilha de História e Ciências Humanas do ENEM.
 * Roteia as requisições para os 6 módulos temáticos:
 * - Módulo 1 (hist_col_...): História do Brasil Colônia
 * - Módulo 2 (hist_imp_...): História do Brasil Império
 * - Módulo 3 (hist_rep_...): História do Brasil República
 * - Módulo 4 (hist_ant_...): História Geral Antiguidade e Idade Média
 * - Módulo 5 (hist_mod_...): História Geral Idade Moderna
 * - Módulo 6 (hist_cont_...): História Geral Idade Contemporânea
 */
object EnemHistoryDidacticProvider {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        if (!lessonId.startsWith("hist_")) return null
        val lesson = EnemHistoryCurriculum.findLesson(lessonId) ?: return null
        return when {
            lessonId.startsWith("hist_col_") -> HistoryColonialDidactic.buildBundle(lesson)
            lessonId.startsWith("hist_imp_") -> HistoryEmpireDidactic.buildBundle(lesson)
            lessonId.startsWith("hist_rep_") -> HistoryRepublicDidactic.buildBundle(lesson)
            lessonId.startsWith("hist_ant_") -> HistoryAncientMedievalDidactic.buildBundle(lesson)
            lessonId.startsWith("hist_mod_") -> HistoryModernDidactic.buildBundle(lesson)
            lessonId.startsWith("hist_cont_") -> HistoryContemporaryDidactic.buildBundle(lesson)
            else -> null
        }
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise> {
        if (!lessonId.startsWith("hist_")) return emptyList()
        val lesson = EnemHistoryCurriculum.findLesson(lessonId) ?: return emptyList()
        val exercises = when {
            lessonId.startsWith("hist_col_") -> HistoryColonialDidactic.buildExercises(lesson)
            lessonId.startsWith("hist_imp_") -> HistoryEmpireDidactic.buildExercises(lesson)
            lessonId.startsWith("hist_rep_") -> HistoryRepublicDidactic.buildExercises(lesson)
            lessonId.startsWith("hist_ant_") -> HistoryAncientMedievalDidactic.buildExercises(lesson)
            lessonId.startsWith("hist_mod_") -> HistoryModernDidactic.buildExercises(lesson)
            lessonId.startsWith("hist_cont_") -> HistoryContemporaryDidactic.buildExercises(lesson)
            else -> emptyList()
        }
        return exercises.map { it.randomized() }
    }
}
