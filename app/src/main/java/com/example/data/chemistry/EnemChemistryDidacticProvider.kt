package com.example.data.chemistry

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.MathExercise

/**
 * Provedor Central e Fachada Didática da Trilha de Química para o ENEM (Ciências da Natureza).
 * Roteia as requisições para os 4 módulos temáticos:
 * - Módulo 1 (quim_ger_...): Química Geral e Estrutura da Matéria
 * - Módulo 2 (quim_fis_...): Físico-Química (A Matemática da Química)
 * - Módulo 3 (quim_org_...): Química Orgânica
 * - Módulo 4 (quim_amb_...): Química Ambiental e do Cotidiano
 */
object EnemChemistryDidacticProvider {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        if (!lessonId.startsWith("quim_")) return null
        val lesson = EnemChemistryCurriculum.findLesson(lessonId) ?: return null
        return when {
            lessonId.startsWith("quim_ger_") -> ChemistryGeneralDidactic.buildBundle(lesson)
            lessonId.startsWith("quim_fis_") -> ChemistryPhysicalDidactic.buildBundle(lesson)
            lessonId.startsWith("quim_org_") -> ChemistryOrganicDidactic.buildBundle(lesson)
            lessonId.startsWith("quim_amb_") -> ChemistryEnvironmentalDidactic.buildBundle(lesson)
            else -> null
        }
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise> {
        if (!lessonId.startsWith("quim_")) return emptyList()
        val lesson = EnemChemistryCurriculum.findLesson(lessonId) ?: return emptyList()
        val exercises = when {
            lessonId.startsWith("quim_ger_") -> ChemistryGeneralDidactic.buildExercises(lesson)
            lessonId.startsWith("quim_fis_") -> ChemistryPhysicalDidactic.buildExercises(lesson)
            lessonId.startsWith("quim_org_") -> ChemistryOrganicDidactic.buildExercises(lesson)
            lessonId.startsWith("quim_amb_") -> ChemistryEnvironmentalDidactic.buildExercises(lesson)
            else -> emptyList()
        }
        return exercises.map { it.randomized() }
    }
}
