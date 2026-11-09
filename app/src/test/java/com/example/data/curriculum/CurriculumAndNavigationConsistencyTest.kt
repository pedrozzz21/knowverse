package com.example.data.curriculum

import com.example.data.assessment.BankQuestion
import com.example.data.assessment.Feedback
import com.example.data.assessment.LessonRef
import com.example.data.assessment.QDifficulty
import com.example.data.assessment.Question
import com.example.data.assessment.QuestionFormat
import com.example.data.assessment.UserAnswer
import com.example.data.assessment.Verdict
import com.example.data.biology.EnemBiologyDidacticProvider
import com.example.data.chemistry.EnemChemistryDidacticProvider
import com.example.data.geography.EnemGeographyCurriculum
import com.example.data.geography.EnemGeographyDidacticProvider
import com.example.data.history.EnemHistoryDidacticProvider
import com.example.data.languages.EnemLanguagesDidacticProvider
import com.example.data.local.assets.CurriculumAssetLoader
import com.example.data.math.CalculusDidacticContent
import com.example.data.math.DidacticContentStatus
import com.example.data.math.EnemDidacticProvider
import com.example.data.math.GeoGebraCommandParser
import com.example.data.math.GeoGebraCommandPrimitive
import com.example.data.model.UserProgress
import com.example.data.physics.EnemPhysicsDidacticProvider
import com.example.data.python.PythonDidacticContent
import com.example.ui.screens.SimuladoSessionStore
import com.example.ui.screens.resolveContinueLearningTarget
import com.example.ui.screens.resolveLessonById
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Test

class CurriculumAndNavigationConsistencyTest {

    private fun buildSubjectPairs() = listOf(
        "Python" to CurriculumAssetLoader.loadAllUnitsForSubject(null, "python").flatMap { it.lessons },
        "Matemática" to (
            CurriculumAssetLoader.loadAllUnitsForSubject(null, "basic_math").flatMap { it.lessons } +
                CurriculumAssetLoader.loadAllUnitsForSubject(null, "enem_math").flatMap { it.lessons } +
                CurriculumAssetLoader.loadAllUnitsForSubject(null, "calculus").flatMap { it.lessons }
            ),
        "Física" to CurriculumAssetLoader.loadAllUnitsForSubject(null, "physics").flatMap { it.lessons },
        "Biologia" to CurriculumAssetLoader.loadAllUnitsForSubject(null, "biology").flatMap { it.lessons },
        "Química" to CurriculumAssetLoader.loadAllUnitsForSubject(null, "chemistry").flatMap { it.lessons },
        "Humanas" to (
            CurriculumAssetLoader.loadAllUnitsForSubject(null, "history").flatMap { it.lessons } +
                CurriculumAssetLoader.loadAllUnitsForSubject(null, "geography").flatMap { it.lessons }
            ),
        "Linguagens" to CurriculumAssetLoader.loadAllUnitsForSubject(null, "languages").flatMap { it.lessons }
    )

    @Test
    fun continueLearning_preservesActiveSubjectAcrossAllDisciplines() {
        val subjectPairs = buildSubjectPairs()

        // 1. Estudante abriu Geografia (geo_fis_01 ainda não concluída)
        val geoInProgress = UserProgress(
            currentLessonId = "geo_fis_01",
            favoriteAreaId = "humanities",
            completedLessonIds = emptySet()
        )
        val (geoNext1, geoSubj1, _) = resolveContinueLearningTarget(geoInProgress, subjectPairs)
        assertEquals("geo_fis_01", geoNext1.id)
        assertEquals("Humanas", geoSubj1)

        // 2. Estudante concluiu geo_fis_01 -> deve avançar para geo_fis_02 (nunca voltar para Python)
        val geoCompletedFirst = UserProgress(
            currentLessonId = "geo_fis_01",
            favoriteAreaId = "humanities",
            completedLessonIds = setOf("geo_fis_01")
        )
        val (geoNext2, geoSubj2, geoMap2) = resolveContinueLearningTarget(geoCompletedFirst, subjectPairs)
        assertEquals("geo_fis_02", geoNext2.id)
        assertEquals("Humanas", geoSubj2)
        assertEquals(1, geoMap2["Humanas"]?.first)

        // 3. Mesmo que currentLessonId ainda esteja no default "py_b1_l1" (ex.: estado legado),
        // se o estudante já concluiu aulas de Física, continua em Física!
        val legacyPhysicsProgress = UserProgress(
            currentLessonId = "py_b1_l1",
            favoriteAreaId = "",
            completedLessonIds = setOf("fis_m1_a1")
        )
        val (physNext, physSubj, _) = resolveContinueLearningTarget(legacyPhysicsProgress, subjectPairs)
        assertEquals("fis_m1_a2", physNext.id)
        assertEquals("Física", physSubj)

        // 4. Verifica todos os 10 currículos (Química, Biologia, Matemática Básica, ENEM, Cálculo, História, Geografia, Linguagens, Física e Python)
        val subjectCases = listOf(
            Triple("quim_ger_01", "quim_ger_02", "Química"),
            Triple("bio_eco_01", "bio_eco_02", "Biologia"),
            Triple("mb_l1", "mb_l2", "Matemática"),
            Triple("enem_m1_a1", "enem_m1_a2", "Matemática"),
            Triple("calc_l1", "calc_l2", "Matemática"),
            Triple("hist_col_01", "hist_col_02", "Humanas"),
            Triple("geo_fis_01", "geo_fis_02", "Humanas"),
            Triple("ling_sem_01", "ling_sem_02", "Linguagens"),
            Triple("fis_m1_a1", "fis_m1_a2", "Física"),
            Triple("py_b1_l1", "py_b1_l2", "Python")
        )
        for ((firstId, secondId, expectedSubj) in subjectCases) {
            val afterFirst = UserProgress(
                currentLessonId = firstId,
                completedLessonIds = setOf(firstId)
            )
            val (nextLesson, activeSubj, map) = resolveContinueLearningTarget(afterFirst, subjectPairs)
            assertEquals("Expected $secondId after completing $firstId", secondId, nextLesson.id)
            assertEquals(expectedSubj, activeSubj)
            assertEquals(1, map[expectedSubj]?.first)
        }
    }

    @Test
    fun geographyAndEnemProviders_rejectUnknownIdsAndLoadExactModuleContent() {
        // IDs desconhecidos nunca devem retornar aula genérica de Geografia Física, Química Geral ou Brasil Colônia
        assertNull(EnemGeographyDidacticProvider.getBundleForLesson("geo_inexistente_99"))
        assertNull(EnemGeographyDidacticProvider.getBundleForLesson("geo_xyz_01"))
        assertTrue(EnemGeographyDidacticProvider.getExercisesForLesson("geo_inexistente_99").isEmpty())

        assertNull(EnemChemistryDidacticProvider.getDidacticBundle("quim_inexistente_99"))
        assertTrue(EnemChemistryDidacticProvider.getExercisesForLesson("quim_inexistente_99").isEmpty())

        assertNull(EnemHistoryDidacticProvider.getDidacticBundle("hist_inexistente_99"))
        assertTrue(EnemHistoryDidacticProvider.getExercisesForLesson("hist_inexistente_99").isEmpty())

        assertNull(EnemLanguagesDidacticProvider.getDidacticBundle("ling_inexistente_99"))
        assertTrue(EnemLanguagesDidacticProvider.getExercisesForLesson("ling_inexistente_99").isEmpty())

        // Todas as 21 aulas de Geografia devem carregar conteúdo revisado específico de seu módulo
        val geoLessons = CurriculumAssetLoader.loadAllUnitsForSubject(null, "geography").flatMap { it.lessons }
        assertEquals(21, geoLessons.size)
        for (lesson in geoLessons) {
            val bundle = EnemGeographyDidacticProvider.getBundleForLesson(lesson.id)
            assertNotNull("Bundle nulo para aula de Geografia ${lesson.id}", bundle)
            assertEquals(DidacticContentStatus.REVIEWED, bundle!!.status)
            assertTrue(
                "Teoria muito curta para ${lesson.id}",
                bundle.theoryMarkdown.length > 200
            )
            val exercises = EnemGeographyDidacticProvider.getExercisesForLesson(lesson.id)
            assertTrue("Aula ${lesson.id} deve ter exercícios específicos", exercises.isNotEmpty())
        }
    }

    @Test
    fun geoGebraCommandParser_lineIsDashedOnlyWhenSetLineStyleIsPositive() {
        val solidCommands = listOf(
            "A = (0, 0)",
            "B = (2, 4)",
            "C = (0, 4)",
            "r = Line(A, B)",
            "v = Line(A, C)"
        )
        val solidElements = GeoGebraCommandParser.parse(solidCommands, "")
        val solidOblique = solidElements.filterIsInstance<GeoGebraCommandPrimitive.GeneralLine>().first { it.id == "r" }
        val solidVertical = solidElements.filterIsInstance<GeoGebraCommandPrimitive.VerticalLine>().first { it.id == "v" }
        assertFalse("Linha oblíqua sem SetLineStyle não deve ser tracejada", solidOblique.isDashed)
        assertFalse("Linha vertical sem SetLineStyle não deve ser tracejada", solidVertical.isDashed)

        val dashedCommands = listOf(
            "A = (0, 0)",
            "B = (2, 4)",
            "C = (0, 4)",
            "r = Line(A, B)",
            "v = Line(A, C)",
            "SetLineStyle(r, 1)",
            "SetLineStyle(v, 2)"
        )
        val dashedElements = GeoGebraCommandParser.parse(dashedCommands, "")
        val dashedOblique = dashedElements.filterIsInstance<GeoGebraCommandPrimitive.GeneralLine>().first { it.id == "r" }
        val dashedVertical = dashedElements.filterIsInstance<GeoGebraCommandPrimitive.VerticalLine>().first { it.id == "v" }
        assertTrue("Linha oblíqua com SetLineStyle(r, 1) deve ser tracejada", dashedOblique.isDashed)
        assertTrue("Linha vertical com SetLineStyle(v, 2) deve ser tracejada", dashedVertical.isDashed)
    }

    @Test
    fun all284LessonsAcrossAll10Curricula_resolveSpecificContentAndExercisesWithoutFallback() {
        val subjects = listOf(
            "python",
            "basic_math",
            "enem_math",
            "calculus",
            "physics",
            "chemistry",
            "biology",
            "history",
            "geography",
            "languages"
        )
        var totalLessonsCount = 0

        for (subject in subjects) {
            val units = CurriculumAssetLoader.loadAllUnitsForSubject(null, subject)
            assertTrue("Nenhuma unidade carregada para $subject", units.isNotEmpty())

            for (unit in units) {
                for (lesson in unit.lessons) {
                    totalLessonsCount++

                    // 1. Resolução de navegação por ID (incluindo mb_, geo_, calc_, py_, etc.)
                    val resolved = resolveLessonById(lesson.id)
                    assertNotNull("resolveLessonById falhou para ${lesson.id} ($subject)", resolved)
                    assertEquals(lesson.id, resolved!!.id)

                    // 2. Conteúdo didático específico e exercícios sem cair em fallback genérico (PENDING_CURATION)
                    if (lesson.id.startsWith("py_")) {
                        assertTrue(
                            "Aula Python ${lesson.id} deve ter testCases ou mathExercises",
                            lesson.testCases.isNotEmpty() || lesson.mathExercises.isNotEmpty()
                        )
                        val pyBundle = PythonDidacticContent.getContentForLesson(lesson.id)
                        assertTrue(
                            "Teoria Python muito curta em ${lesson.id}",
                            pyBundle.theoryMarkdown.length > 100
                        )
                        assertTrue(
                            "Fórmulas/sintaxe vazias em ${lesson.id}",
                            pyBundle.functionsAndSyntax.isNotEmpty()
                        )
                        assertTrue(
                            "Exemplos de código vazios em ${lesson.id}",
                            pyBundle.codeExamples.isNotEmpty()
                        )
                    } else {
                        assertTrue(
                            "Aula ${lesson.id} ($subject) não possui exercícios",
                            lesson.mathExercises.isNotEmpty()
                        )
                        for (ex in lesson.mathExercises) {
                            assertTrue(
                                "Enunciado vazio em ${ex.id} da aula ${lesson.id}",
                                ex.problemStatement.isNotBlank()
                            )
                            assertTrue(
                                "Alternativas insuficientes em ${ex.id} da aula ${lesson.id}",
                                ex.options.size >= 4
                            )
                            assertTrue(
                                "correctIndex inválido em ${ex.id} da aula ${lesson.id}",
                                ex.correctIndex in ex.options.indices
                            )
                            assertTrue(
                                "Solução comentada vazia em ${ex.id} da aula ${lesson.id}",
                                ex.stepByStepSolution.isNotBlank()
                            )
                        }

                        val bundle = when {
                            lesson.id.startsWith("quim_") -> EnemChemistryDidacticProvider.getDidacticBundle(lesson.id)
                            lesson.id.startsWith("geo_") -> EnemGeographyDidacticProvider.getBundleForLesson(lesson.id)
                            lesson.id.startsWith("hist_") -> EnemHistoryDidacticProvider.getDidacticBundle(lesson.id)
                            lesson.id.startsWith("ling_") -> EnemLanguagesDidacticProvider.getDidacticBundle(lesson.id)
                            lesson.id.startsWith("bio_") -> EnemBiologyDidacticProvider.getDidacticBundle(lesson.id)
                            lesson.id.startsWith("fis_") -> EnemPhysicsDidacticProvider.getDidacticBundle(lesson.id)
                            lesson.id.startsWith("enem_") -> EnemDidacticProvider.getDidacticBundle(lesson.id)
                            else -> CalculusDidacticContent.getContentForLesson(lesson.id)
                        }
                        assertNotNull("Bundle nulo para aula ${lesson.id} ($subject)", bundle)
                        assertEquals(
                            "Aula ${lesson.id} ($subject) caiu no fallback genérico!",
                            DidacticContentStatus.REVIEWED,
                            bundle!!.status
                        )
                        assertTrue(
                            "Teoria muito curta em ${lesson.id}",
                            bundle.theoryMarkdown.length > 120
                        )
                        assertTrue(
                            "Fórmulas vazias em ${lesson.id}",
                            bundle.formulas.isNotEmpty()
                        )
                        assertTrue(
                            "Exemplos resolvidos vazios em ${lesson.id}",
                            bundle.solvedExamples.isNotEmpty()
                        )
                    }
                }
            }
        }

        assertEquals("O KnowVerse deve possuir exatamente 284 aulas ativas nos 10 currículos", 284, totalLessonsCount)
    }

    @Test
    fun simuladoSessionStore_persistsAndFinalizesDraftAnswersWithoutLoss() {
        val ref = LessonRef(
            lessonId = "fis_m1_a1",
            subject = "Física",
            moduleId = "fis_m1",
            moduleTitle = "Mecânica",
            title = "Velocidade Média",
            subtopic = "Cinemática Escalar",
            order = 1
        )
        val q1 = BankQuestion(
            question = Question(
                id = "sim_q1",
                lessonId = "fis_m1_a1",
                format = QuestionFormat.MULTIPLE_CHOICE,
                difficulty = QDifficulty.EASY,
                skill = "Cinemática",
                concept = "Velocidade Média",
                statement = "Qual é a velocidade média de 100 m em 10 s?",
                options = listOf("5 m/s", "10 m/s", "15 m/s", "20 m/s", "25 m/s"),
                answerIndex = 1,
                explanation = "v = 100 / 10 = 10 m/s.",
                retryGuidance = "Divida Δs por Δt."
            ),
            lesson = ref
        )
        val q2 = BankQuestion(
            question = Question(
                id = "sim_q2",
                lessonId = "fis_m1_a1",
                format = QuestionFormat.TRUE_FALSE,
                difficulty = QDifficulty.MEDIUM,
                skill = "MRU",
                concept = "Movimento Uniforme",
                statement = "No MRU a aceleração escalar é nula.",
                answerBool = true,
                explanation = "Velocidade constante implica aceleração zero.",
                retryGuidance = "Lembre-se de que no MRU v é constante."
            ),
            lesson = ref
        )
        val q3 = BankQuestion(
            question = Question(
                id = "sim_q3",
                lessonId = "fis_m1_a1",
                format = QuestionFormat.NUMERIC,
                difficulty = QDifficulty.HARD,
                skill = "Cálculo",
                concept = "Produto",
                statement = "Calcule 3 * 7",
                answerNumber = 21.0,
                tolerance = 0.01,
                explanation = "3 * 7 = 21.",
                retryGuidance = "Multiplique 3 por 7."
            ),
            lesson = ref
        )
        val questions = listOf(q1, q2, q3)

        SimuladoSessionStore.generatedQuestionIds = questions.map { it.id }
        SimuladoSessionStore.currentQuestionIdx = 2
        SimuladoSessionStore.examElapsedSeconds = 145
        SimuladoSessionStore.answerMap.clear()
        SimuladoSessionStore.feedbackMap.clear()

        // O usuário marcou respostas nas 3 questões sem clicar em "Verificar" individualmente em q2 e q3:
        SimuladoSessionStore.answerMap["sim_q1"] = UserAnswer.Choice(1)
        SimuladoSessionStore.feedbackMap["sim_q1"] = Feedback(
            verdict = Verdict.CORRECT,
            score = 1.0,
            headline = "Correto!",
            reasoning = "v = 100 / 10 = 10 m/s."
        )
        SimuladoSessionStore.answerMap["sim_q2"] = UserAnswer.Bool(true)
        SimuladoSessionStore.answerMap["sim_q3"] = UserAnswer.Text("99") // errada em rascunho

        // Testa serialização JSON round-trip (proteção contra perda ao alternar abas/processo)
        val json = SimuladoSessionStore.toJsonString()
        SimuladoSessionStore.answerMap.clear()
        SimuladoSessionStore.feedbackMap.clear()
        SimuladoSessionStore.currentQuestionIdx = 0

        SimuladoSessionStore.restoreFromJsonString(json)
        assertEquals(2, SimuladoSessionStore.currentQuestionIdx)
        assertEquals(145, SimuladoSessionStore.examElapsedSeconds)
        assertEquals(3, SimuladoSessionStore.answerMap.size)
        assertEquals(UserAnswer.Choice(1), SimuladoSessionStore.answerMap["sim_q1"])
        assertEquals(UserAnswer.Bool(true), SimuladoSessionStore.answerMap["sim_q2"])
        assertEquals(UserAnswer.Text("99"), SimuladoSessionStore.answerMap["sim_q3"])

        // Ao concluir o simulado, todas as respostas em rascunho devem ser avaliadas automaticamente (zero perda)
        SimuladoSessionStore.finalizePendingAnswers(
            questions = questions,
            answers = SimuladoSessionStore.answerMap,
            feedbacks = SimuladoSessionStore.feedbackMap
        )
        assertEquals(3, SimuladoSessionStore.feedbackMap.size)
        assertTrue(SimuladoSessionStore.feedbackMap["sim_q1"]!!.isCorrect)
        assertTrue(SimuladoSessionStore.feedbackMap["sim_q2"]!!.isCorrect)
        assertFalse(SimuladoSessionStore.feedbackMap["sim_q3"]!!.isCorrect)

        SimuladoSessionStore.answerMap.clear()
        SimuladoSessionStore.feedbackMap.clear()
    }
}
