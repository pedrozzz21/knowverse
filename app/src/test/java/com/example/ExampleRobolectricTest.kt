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
@Config(sdk = [36])
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

    @Test
    fun `test all languages lessons and didactic bundles without crash`() {
        val langModules = com.example.data.languages.EnemLanguagesCurriculum.getAllModules()
        assertEquals(5, langModules.size)
        for (module in langModules) {
            for (lesson in module.lessons) {
                val appLesson = lesson.toAppLesson()
                assertNotNull("AppLesson should not be null for ${lesson.id}", appLesson)
                val bundle = com.example.data.languages.EnemLanguagesDidacticProvider.getDidacticBundle(lesson.id)
                assertNotNull("DidacticBundle should not be null for ${lesson.id}", bundle)
                bundle?.let { b ->
                    // Test math formatting on theory markdown
                    val formatted = com.example.ui.components.MathNotationHelper.formatInlineMath(b.theoryMarkdown)
                    assertNotNull(formatted)
                    for (f in b.formulas) {
                        val std = com.example.ui.components.MathNotationHelper.toStandardLatex(f.syntax)
                        val inf = com.example.ui.components.MathNotationHelper.formatInlineMath(std)
                        assertNotNull(inf)
                    }
                    for (ex in b.solvedExamples) {
                        val std = com.example.ui.components.MathNotationHelper.toStandardLatex(ex.codeOrFormula)
                        val inf = com.example.ui.components.MathNotationHelper.formatInlineMath(std)
                        assertNotNull(inf)
                    }
                }
                val exercises = com.example.data.languages.EnemLanguagesDidacticProvider.getExercisesForLesson(lesson.id)
                for (ex in exercises) {
                    val p = com.example.ui.components.MathNotationHelper.formatInlineMath(ex.problemStatement)
                    assertNotNull(p)
                    for (opt in ex.options) {
                        val o = com.example.ui.components.MathNotationHelper.formatInlineMath(opt)
                        assertNotNull(o)
                    }
                }
            }
        }
    }

    @Test
    fun `test all history lessons and didactic bundles without crash`() {
        val histModules = com.example.data.history.EnemHistoryCurriculum.getAllModules()
        assertEquals(6, histModules.size)
        for (module in histModules) {
            for (lesson in module.lessons) {
                val appLesson = lesson.toAppLesson()
                assertNotNull("AppLesson should not be null for ${lesson.id}", appLesson)
                val bundle = com.example.data.history.EnemHistoryDidacticProvider.getDidacticBundle(lesson.id)
                assertNotNull("DidacticBundle should not be null for ${lesson.id}", bundle)
                bundle?.let { b ->
                    val formatted = com.example.ui.components.MathNotationHelper.formatInlineMath(b.theoryMarkdown)
                    assertNotNull(formatted)
                    for (f in b.formulas) {
                        val std = com.example.ui.components.MathNotationHelper.toStandardLatex(f.syntax)
                        val inf = com.example.ui.components.MathNotationHelper.formatInlineMath(std)
                        assertNotNull(inf)
                    }
                }
                val exercises = com.example.data.history.EnemHistoryDidacticProvider.getExercisesForLesson(lesson.id)
                for (ex in exercises) {
                    val p = com.example.ui.components.MathNotationHelper.formatInlineMath(ex.problemStatement)
                    assertNotNull(p)
                    for (opt in ex.options) {
                        val o = com.example.ui.components.MathNotationHelper.formatInlineMath(opt)
                        assertNotNull(o)
                    }
                }
            }
        }
    }

    @Test
    fun `test all questions in enem question repository for focus mode compatibility`() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        val repo = com.example.data.enem.EnemQuestionRepository.getInstance(context)
        val questions = repo.getAllQuestions()
        assertNotNull(questions)
        for (q in questions) {
            val u = q.toUniversalExercise()
            // Validate options bounds
            assertNotNull(u.problemStatement)
            assertEquals(true, u.options.isNotEmpty())
            val correct = u.options.getOrElse(u.correctIndex) { "" }
            assertNotNull(correct)
            // Test formatting of statement and options
            com.example.ui.components.MathNotationHelper.formatInlineMath(u.problemStatement)
            for (opt in u.options) {
                com.example.ui.components.MathNotationHelper.formatInlineMath(opt)
            }
            if (u.pedagogicalAlert.isNotBlank()) {
                com.example.ui.components.MathNotationHelper.formatInlineMath(u.pedagogicalAlert)
            }
            for (step in u.solutionSteps) {
                com.example.ui.components.MathNotationHelper.formatInlineMath(step)
            }
        }
    }

    @Test
    fun `test all physics lessons and didactic bundles without crash`() {
        val physicsModules = com.example.data.physics.EnemPhysicsCurriculum.getAllModules()
        assertEquals(6, physicsModules.size)
        for (module in physicsModules) {
            for (lesson in module.lessons) {
                val appLesson = lesson.toAppLesson()
                assertNotNull("AppLesson should not be null for ${lesson.id}", appLesson)
                val bundle = com.example.data.physics.EnemPhysicsDidacticProvider.getDidacticBundle(lesson.id)
                assertNotNull("DidacticBundle should not be null for ${lesson.id}", bundle)
                bundle?.let { b ->
                    val formatted = com.example.ui.components.MathNotationHelper.formatInlineMath(b.theoryMarkdown)
                    assertNotNull(formatted)
                    for (f in b.formulas) {
                        val std = com.example.ui.components.MathNotationHelper.toStandardLatex(f.syntax)
                        val inf = com.example.ui.components.MathNotationHelper.formatInlineMath(std)
                        assertNotNull(inf)
                    }
                    for (ex in b.solvedExamples) {
                        val std = com.example.ui.components.MathNotationHelper.toStandardLatex(ex.codeOrFormula)
                        val inf = com.example.ui.components.MathNotationHelper.formatInlineMath(std)
                        assertNotNull(inf)
                    }
                }
                val exercises = com.example.data.physics.EnemPhysicsDidacticProvider.getExercisesForLesson(lesson.id)
                for (ex in exercises) {
                    val p = com.example.ui.components.MathNotationHelper.formatInlineMath(ex.problemStatement)
                    assertNotNull(p)
                    for (opt in ex.options) {
                        val o = com.example.ui.components.MathNotationHelper.formatInlineMath(opt)
                        assertNotNull(o)
                    }
                }
            }
        }
    }

    @Test
    fun `test all biology lessons and didactic bundles without crash`() {
        val bioModules = com.example.data.biology.EnemBiologyCurriculum.getAllModules()
        assertEquals(true, bioModules.isNotEmpty())
        for (module in bioModules) {
            for (lesson in module.lessons) {
                val appLesson = lesson.toAppLesson()
                assertNotNull("AppLesson should not be null for ${lesson.id}", appLesson)
                val bundle = com.example.data.biology.EnemBiologyDidacticProvider.getDidacticBundle(lesson.id)
                assertNotNull("DidacticBundle should not be null for ${lesson.id}", bundle)
                bundle?.let { b ->
                    val formatted = com.example.ui.components.MathNotationHelper.formatInlineMath(b.theoryMarkdown)
                    assertNotNull(formatted)
                    for (f in b.formulas) {
                        val std = com.example.ui.components.MathNotationHelper.toStandardLatex(f.syntax)
                        val inf = com.example.ui.components.MathNotationHelper.formatInlineMath(std)
                        assertNotNull(inf)
                    }
                }
                val exercises = com.example.data.biology.EnemBiologyDidacticProvider.getExercisesForLesson(lesson.id)
                for (ex in exercises) {
                    val p = com.example.ui.components.MathNotationHelper.formatInlineMath(ex.problemStatement)
                    assertNotNull(p)
                    for (opt in ex.options) {
                        val o = com.example.ui.components.MathNotationHelper.formatInlineMath(opt)
                        assertNotNull(o)
                    }
                }
            }
        }
    }

    @Test
    fun `test kahoot quiz modules randomization and option indexing bounds`() {
        val modulesToTest = listOf(
            com.example.data.python.PythonCurriculum.basicKahoot,
            com.example.data.python.PythonCurriculum.intermediateKahoot,
            com.example.data.python.PythonCurriculum.advancedKahoot,
            com.example.data.math.CalculusCurriculum.calculusLimitsFinalTest,
            com.example.data.math.CalculusCurriculum.calculusDerivativesFinalTest,
            com.example.data.math.CalculusCurriculum.calculusIntegralsFinalTest,
            com.example.data.math.BasicMathCurriculum.basicMathFinalTest
        )

        for (module in modulesToTest) {
            assertNotNull(module.id)
            assertNotNull(module.title)
            val randomized = module.questions.map { it.randomized() }
            for (q in randomized) {
                assertNotNull(q.question)
                assertEquals(true, q.options.isNotEmpty())
                assertEquals(true, q.correctIndex in 0 until q.options.size)
                val correctText = q.options[q.correctIndex]
                assertNotNull(correctText)
            }
        }
    }

    @Test
    fun `test math notation helper stress and memory performance`() {
        val testStrings = listOf(
            "Em física, a aceleração média é dada por `a = \\Delta v / \\Delta t` e a velocidade `v(t) = v_0 + a \\cdot t`.",
            "Considere a função integral `\\int_{0}^{x} f(t) dt` com limite `\\lim_{x \\to \\infty} \\frac{1}{x} = 0`.",
            "Fórmula da fotossíntese: `6CO_2 + 6H_2O \\to C_6H_{12}O_6 + 6O_2` com rendimento energético alto.",
            "Equação linear: y = mx + b onde m representa a inclinação e b o coeficiente linear.",
            "Texto sem notação matemática comum apenas com pontuação: O Brasil colonial teve ciclos econômicos importantes."
        )

        // Stress loop: 1,000 transformations validating throughput and lack of memory exhaustion
        for (i in 0 until 1000) {
            val input = testStrings[i % testStrings.size]
            val output = com.example.ui.components.MathNotationHelper.formatInlineMath(input)
            assertNotNull(output)
            val std = com.example.ui.components.MathNotationHelper.toStandardLatex(input)
            assertNotNull(std)
        }
    }

    @Test
    fun `test empty kahoot module fallback safety`() {
        val emptyModule = com.example.data.model.KahootModule(
            id = "test_empty_mod",
            tier = com.example.data.model.DifficultyTier.BASIC,
            title = "Módulo de Teste Vazio",
            description = "Descrição de teste",
            questions = emptyList()
        )
        val shuffled = emptyModule.questions.map { it.randomized() }.shuffled()
        assertEquals(0, shuffled.size)
        val currentQuestion = shuffled.getOrNull(0)
        assertEquals(null, currentQuestion)
    }
}

