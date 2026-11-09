package com.example.data.transpetro

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

object TranspetroAdmDidactic {

    fun getBundle(lessonId: String): CalculusDidacticBundle? {
        return TranspetroAdmPart1Didactic.getBundle(lessonId)
            ?: TranspetroAdmPart2Didactic.getBundle(lessonId)
    }

    fun getExercises(lessonId: String): List<MathExercise> {
        val p1 = TranspetroAdmPart1Didactic.getExercises(lessonId)
        if (p1.isNotEmpty()) return p1
        return TranspetroAdmPart2Didactic.getExercises(lessonId)
    }
}


