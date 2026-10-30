package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.UserProgressRepository
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

    @Test
    fun `read string from context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val appName = context.getString(R.string.app_name)
        assertEquals("Knowverse", appName)
    }

    @Test
    fun `test user progress persistence with context`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repository = UserProgressRepository(context)
        val initialProgress = repository.progressState.value
        assertNotNull(initialProgress)
        assertEquals(0, initialProgress.currentStreak)

        repository.completeLesson("py_b1_l1", 20)
        val updated = repository.progressState.value
        assertEquals(20, updated.totalXp)
        assertEquals(true, updated.completedLessonIds.contains("py_b1_l1"))
        assertEquals(1, updated.currentStreak)
    }

    @Test
    fun `test enem question repository maps and filters by discipline and module`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = com.example.data.enem.EnemQuestionRepository.getInstance(context)
        val allQuestions = repo.getAllQuestions()
        
        // Assert loaded questions from all areas
        assertEquals(true, allQuestions.isNotEmpty())

        // Test filtering by History module
        val colonialQuestions = repo.getQuestionsByModule("mod_hist_01_colonia")
        assertEquals(true, colonialQuestions.isNotEmpty())
        assertEquals(true, colonialQuestions.all { it.moduleId == "mod_hist_01_colonia" })

        // Test filtering by Languages module
        val lingQuestions = repo.getQuestionsByModule("mod_ling_01_interpretacao")
        assertEquals(true, lingQuestions.isNotEmpty())

        // Test filtering by Natureza (Física and Biologia)
        val fisQuestions = repo.getQuestionsByModule("mod_fis_01_mecanica")
        assertEquals(true, fisQuestions.isNotEmpty())
        val bioQuestions = repo.getQuestionsByModule("mod_bio_01_ecologia")
        assertEquals(true, bioQuestions.isNotEmpty())

        // Test filtering by Math
        val matQuestions = repo.getQuestionsByModule("mod_mat_01_basica")
        assertEquals(true, matQuestions.isNotEmpty())

        // Test conversion to UniversalExercise with appropriate discipline
        val sampleHistExercise = colonialQuestions.first().toUniversalExercise()
        assertEquals(com.example.data.exercise.ExerciseDiscipline.HISTORY, sampleHistExercise.discipline)
    }

    @Test
    fun `test all geography and other discipline lessons load without crash`() {
        val geoModules = com.example.data.geography.EnemGeographyCurriculum.getAllModules()
        assertEquals(5, geoModules.size)
        for (module in geoModules) {
            for (lesson in module.lessons) {
                val appLesson = lesson.toAppLesson()
                assertNotNull("AppLesson should not be null for ${lesson.id}", appLesson)
                val bundle = com.example.data.geography.EnemGeographyDidacticProvider.getBundleForLesson(lesson.id)
                assertNotNull("Bundle should not be null for ${lesson.id}", bundle)
                val content = com.example.data.math.CalculusDidacticContent.getContentForLesson(lesson.id)
                assertNotNull("Content should not be null for ${lesson.id}", content)
            }
        }
    }
}
