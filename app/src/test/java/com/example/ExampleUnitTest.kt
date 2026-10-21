package com.example

import com.example.data.model.UserProgress
import com.example.data.python.PythonCurriculum
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testCurriculumUnitsAndLessons() {
        val units = PythonCurriculum.pythonUnits
        assertEquals("Should have 8 pedagogical units", 8, units.size)

        val allLessons = PythonCurriculum.getAllLessons()
        assertTrue("Should have multiple structured lessons", allLessons.size >= 15)

        val firstLesson = PythonCurriculum.findLesson("py_b1_l1")
        assertNotNull("First lesson should exist", firstLesson)
        assertEquals("Primeiros Passos com print()", firstLesson?.title)
        assertTrue(firstLesson?.hints?.isNotEmpty() == true)

        val nextLesson = PythonCurriculum.getNextLesson("py_b1_l1")
        assertNotNull("Next lesson should exist", nextLesson)
        assertEquals("py_b1_l2", nextLesson?.id)
    }

    @Test
    fun testKnowledgeAreasCatalog() {
        val areas = PythonCurriculum.knowledgeAreas
        assertTrue(areas.any { it.id == "python" && it.isAvailable })
        assertTrue(areas.any { it.id == "math" && it.isAvailable })
        assertTrue(areas.any { it.id == "ai" && !it.isAvailable })
        assertTrue(areas.any { it.id == "data" && !it.isAvailable })
    }

    @Test
    fun testUserProgressLevelCalculation() {
        val progressInitial = UserProgress(totalXp = 0)
        assertEquals(1, progressInitial.level)
        assertEquals(0f, progressInitial.currentLevelProgress, 0.01f)
        assertEquals(100, progressInitial.xpToNextLevel)

        val progressLevel2 = UserProgress(totalXp = 150)
        assertEquals(2, progressLevel2.level)
        assertEquals(0.5f, progressLevel2.currentLevelProgress, 0.01f)
        assertEquals(50, progressLevel2.xpToNextLevel)

        val progressLevel5 = UserProgress(totalXp = 480)
        assertEquals(5, progressLevel5.level)
        assertEquals(0.8f, progressLevel5.currentLevelProgress, 0.01f)
        assertEquals(20, progressLevel5.xpToNextLevel)
    }

    @Test
    fun testLessonTestCasesIntegrity() {
        val lessons = PythonCurriculum.getAllLessons()
        for (lesson in lessons) {
            assertTrue("Lesson title should not be blank", lesson.title.isNotBlank())
            assertTrue("Explanation should not be blank", lesson.explanation.isNotBlank())
            assertTrue("XP reward should be positive", lesson.xpReward > 0)
        }
    }

    @Test
    fun testExerciseBankComprehensiveDistribution() {
        val allExercises = com.example.data.exercise.UniversalExerciseBank.getAllExercises()
        assertTrue("Total exercises should be substantial (> 100)", allExercises.size >= 120)

        // Check each discipline is populated
        val pyExercises = allExercises.filter { it.discipline == com.example.data.exercise.ExerciseDiscipline.PROGRAMMING }
        val calcExercises = allExercises.filter { it.discipline == com.example.data.exercise.ExerciseDiscipline.CALCULUS }
        val mbExercises = allExercises.filter { it.discipline == com.example.data.exercise.ExerciseDiscipline.BASIC_MATH }

        assertTrue("Python should have at least 42 exercises", pyExercises.size >= 42)
        assertTrue("Calculus should have at least 42 exercises", calcExercises.size >= 42)
        assertTrue("Basic Math should have at least 42 exercises", mbExercises.size >= 42)

        // Verify difficulty categories exist in each discipline
        for (disc in listOf(pyExercises, calcExercises, mbExercises)) {
            val faciles = disc.filter { it.difficulty == "Fácil" }
            val medianos = disc.filter { it.difficulty == "Mediano" }
            val dificeis = disc.filter { it.difficulty == "Difícil" }

            assertTrue("Should have Fácil questions", faciles.isNotEmpty())
            assertTrue("Should have Mediano questions", medianos.isNotEmpty())
            assertTrue("Should have Difícil questions", dificeis.isNotEmpty())
        }
    }

    @Test
    fun testExerciseIntegrity() {
        val allExercises = com.example.data.exercise.UniversalExerciseBank.getAllExercises()
        val seenIds = mutableSetOf<String>()

        for (ex in allExercises) {
            assertTrue("Exercise ID should not be blank", ex.id.isNotBlank())
            assertTrue("Duplicate exercise ID found: ${ex.id}", seenIds.add(ex.id))
            assertTrue("Exercise problemStatement should not be blank: ${ex.id}", ex.problemStatement.isNotBlank())
            assertTrue("Exercise should have at least 2 options: ${ex.id}", ex.options.size >= 2)
            assertTrue("correctIndex out of bounds for ${ex.id}: ${ex.correctIndex} vs size ${ex.options.size}",
                ex.correctIndex in 0 until ex.options.size)
            assertTrue("KeyMethod should not be blank: ${ex.id}", ex.keyMethod.isNotBlank())
            assertTrue("XP reward should be positive for ${ex.id}", ex.xpReward > 0)
        }
    }
}
