package com.example.data.geography

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

/**
 * Provedor Central e Fachada Didática da Trilha de Geografia para o ENEM.
 * Despacha os conteúdos e exercícios de cada aula para o leitor universal.
 */
object EnemGeographyDidacticProvider {

    fun getBundleForLesson(lessonId: String): CalculusDidacticBundle? {
        if (!lessonId.startsWith("geo_")) return null
        val lesson = EnemGeographyCurriculum.findLesson(lessonId) ?: return null
        return when {
            lessonId.startsWith("geo_fis_") -> GeographyPhysicalDidactic.buildBundle(lesson)
            lessonId.startsWith("geo_agr_") -> GeographyAgrarianDidactic.buildBundle(lesson)
            lessonId.startsWith("geo_urb_") -> GeographyUrbanDemographyDidactic.buildBundle(lesson)
            lessonId.startsWith("geo_eco_") -> GeographyEconomicDidactic.buildBundle(lesson)
            lessonId.startsWith("geo_pol_") -> GeographyGeopoliticsDidactic.buildBundle(lesson)
            else -> null
        }
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise> {
        if (!lessonId.startsWith("geo_")) return emptyList()
        val lesson = EnemGeographyCurriculum.findLesson(lessonId) ?: return emptyList()
        val exercises = when {
            lessonId.startsWith("geo_fis_") -> GeographyPhysicalDidactic.buildExercises(lesson)
            lessonId.startsWith("geo_agr_") -> GeographyAgrarianDidactic.buildExercises(lesson)
            lessonId.startsWith("geo_urb_") -> GeographyUrbanDemographyDidactic.buildExercises(lesson)
            lessonId.startsWith("geo_eco_") -> GeographyEconomicDidactic.buildExercises(lesson)
            lessonId.startsWith("geo_pol_") -> GeographyGeopoliticsDidactic.buildExercises(lesson)
            else -> emptyList()
        }
        return exercises.map { it.randomized() }
    }
}
