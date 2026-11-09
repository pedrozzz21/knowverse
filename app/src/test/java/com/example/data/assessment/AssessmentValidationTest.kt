package com.example.data.assessment

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class AssessmentValidationTest {

    @Test
    fun testEveryLessonHasValidatedQuestionsAndDifficultyBalance() {
        val lessons = QuestionBankRepository.getAllLessons()
        assertEquals("Expected all 193 curriculum lessons", 193, lessons.size)

        val allNew = QuestionBankRepository.getAuthoredQuestions()
        assertEquals("Expected 1930 per-lesson questions across 6 subjects", 1930, allNew.size)

        lessons.forEach { lesson ->
            val perLesson = QuestionBankRepository.getQuestionsForLesson(
                lessonId = lesson.lessonId,
                includeLegacy = false
            )
            assertTrue(
                "Lesson ${lesson.lessonId} should have 8..15 questions, got ${perLesson.size}",
                perLesson.size in 8..15
            )
            val easyCount = perLesson.count { it.question.difficulty == QDifficulty.EASY }
            val medCount = perLesson.count { it.question.difficulty == QDifficulty.MEDIUM }
            val hardCount = perLesson.count { it.question.difficulty == QDifficulty.HARD }
            assertTrue("Lesson ${lesson.lessonId} should have EASY questions", easyCount >= 2)
            assertTrue("Lesson ${lesson.lessonId} should have MEDIUM questions", medCount >= 3)
            assertTrue("Lesson ${lesson.lessonId} should have HARD questions", hardCount >= 2)
        }

        val totalEasy = allNew.count { it.question.difficulty == QDifficulty.EASY }
        val totalMedium = allNew.count { it.question.difficulty == QDifficulty.MEDIUM }
        val totalHard = allNew.count { it.question.difficulty == QDifficulty.HARD }

        val easyPct = totalEasy.toDouble() / allNew.size * 100.0
        val medPct = totalMedium.toDouble() / allNew.size * 100.0
        val hardPct = totalHard.toDouble() / allNew.size * 100.0

        assertEquals(30.0, easyPct, 3.0)
        assertEquals(45.0, medPct, 3.0)
        assertEquals(25.0, hardPct, 3.0)
    }

    @Test
    fun testAllEightQuestionFormatsAndQualityFields() {
        val allNew = QuestionBankRepository.getAuthoredQuestions()
        val formats = allNew.map { it.question.format }.toSet()
        assertEquals(
            "All 8 formats must be present",
            QuestionFormat.entries.toSet(),
            formats
        )

        allNew.forEach { bq ->
            val q = bq.question
            assertTrue("Question id must not be blank", q.id.isNotBlank())
            assertTrue("Question statement must not be blank (${q.id})", q.statement.length >= 20)
            assertTrue("Question explanation must not be blank (${q.id})", q.explanation.length >= 20)
            assertTrue("Question skill must not be blank (${q.id})", q.skill.isNotBlank())
            assertTrue("Question lessonId must not be blank (${q.id})", q.lessonId.isNotBlank())
            assertTrue("Question retryGuidance must not be blank (${q.id})", q.retryGuidance.isNotBlank())
            assertTrue(
                "Authored question ${q.id} must never be marked as official INEP_ENEM",
                q.source != QuestionSource.INEP_ENEM
            )
        }
    }

    @Test
    fun testEvaluatorValidatesAllFormatsAndIntelligentFeedback() {
        val allNew = QuestionBankRepository.getAuthoredQuestions()

        // 1. Multiple Choice
        val mc = allNew.first { it.question.format == QuestionFormat.MULTIPLE_CHOICE }.question
        val mcCorrect = AnswerEvaluator.evaluate(mc, UserAnswer.Choice(mc.answerIndex))
        assertTrue(mcCorrect.isCorrect)
        assertTrue(mcCorrect.reasoning.length >= 20)

        val wrongIdx = (mc.answerIndex + 1) % mc.options.size
        val mcWrong = AnswerEvaluator.evaluate(mc, UserAnswer.Choice(wrongIdx))
        assertFalse(mcWrong.isCorrect)
        assertNotNull("Wrong answer must identify misunderstood concept", mcWrong.misunderstoodConcept)
        assertTrue(mcWrong.misunderstoodConcept!!.isNotBlank())
        assertTrue("Wrong answer must provide retryTip", !mcWrong.retryTip.isNullOrBlank())

        // 2. True/False
        val tf = allNew.first { it.question.format == QuestionFormat.TRUE_FALSE }.question
        val tfExpected = tf.answerBool ?: true
        val tfCorrect = AnswerEvaluator.evaluate(tf, UserAnswer.Bool(tfExpected))
        assertTrue(tfCorrect.isCorrect)
        val tfFlipped = AnswerEvaluator.evaluate(tf, UserAnswer.Bool(!tfExpected))
        assertFalse(tfFlipped.isCorrect)
        assertNotNull(tfFlipped.misunderstoodConcept)

        // 3. Matching
        val matchQ = allNew.first { it.question.format == QuestionFormat.MATCHING }.question
        val correctPairsMap = matchQ.pairs.toMap()
        val matchCorrect = AnswerEvaluator.evaluate(matchQ, UserAnswer.Pairs(correctPairsMap))
        assertTrue(matchCorrect.isCorrect)

        // 4. Fill Blank
        val fillQ = allNew.first { it.question.format == QuestionFormat.FILL_BLANK }.question
        val fillCorrect = AnswerEvaluator.evaluate(fillQ, UserAnswer.Text(fillQ.answers.first()))
        assertTrue(fillCorrect.isCorrect)
        val fillWrong = AnswerEvaluator.evaluate(fillQ, UserAnswer.Text("resposta_invalida_xyz"))
        assertFalse(fillWrong.isCorrect)
        assertNotNull(fillWrong.misunderstoodConcept)

        // 5. Numeric
        val numQ = allNew.first { it.question.format == QuestionFormat.NUMERIC }.question
        val ansVal = numQ.answerNumber ?: 0.0
        val numCorrect = AnswerEvaluator.evaluate(numQ, UserAnswer.Text(ansVal.toString()))
        assertTrue(numCorrect.isCorrect)
        val numWrong = AnswerEvaluator.evaluate(numQ, UserAnswer.Text((ansVal + 999.0).toString()))
        assertFalse(numWrong.isCorrect)
        assertNotNull(numWrong.misunderstoodConcept)

        // 6. Chart
        val chartQ = allNew.first { it.question.format == QuestionFormat.CHART }.question
        assertNotNull(chartQ.chart)
        val chartCorrect = AnswerEvaluator.evaluate(chartQ, UserAnswer.Choice(chartQ.answerIndex))
        assertTrue(chartCorrect.isCorrect)

        // 7. Essay (Rubric validation + anti-fragile check)
        val essayQ = allNew.first { it.question.format == QuestionFormat.ESSAY }.question
        val essayModel = AnswerEvaluator.evaluate(essayQ, UserAnswer.Text(essayQ.modelAnswer))
        assertTrue("Model answer should pass essay rubric (${essayQ.id})", essayModel.isCorrect)
        val essayFragile = AnswerEvaluator.evaluate(essayQ, UserAnswer.Text("sim porque sim"))
        assertFalse("Fragile short text must be rejected", essayFragile.isCorrect)

        // 8. Python Code (Automated test suite + anti-fragile check)
        val codeQ = allNew.first { it.question.format == QuestionFormat.CODE }.question
        val spec = codeQ.code!!
        val pyCmd = if (java.io.File("/usr/bin/python").exists()) "python" else "python3"
        val pyRunner: suspend (String) -> AnswerEvaluator.CodeRunResult = { script ->
            val proc = ProcessBuilder(pyCmd, "-X", "utf8", "-c", script)
                .redirectErrorStream(false)
                .start()
            val out = proc.inputStream.bufferedReader(Charsets.UTF_8).readText()
            val err = proc.errorStream.bufferedReader(Charsets.UTF_8).readText()
            val code = proc.waitFor()
            AnswerEvaluator.CodeRunResult(code == 0, out, err.ifBlank { null })
        }
        val codePassStub = kotlinx.coroutines.runBlocking {
            AnswerEvaluator.evaluateCode(codeQ, spec.starter, pyRunner)
        }
        assertFalse("Starter stub with pass must fail automated tests", codePassStub.isCorrect)
        val codeSolution = kotlinx.coroutines.runBlocking {
            AnswerEvaluator.evaluateCode(codeQ, spec.reference, pyRunner)
        }
        assertTrue(
            "Reference solution for ${codeQ.id} must pass all automated tests: ${codeSolution.reasoning}",
            codeSolution.isCorrect
        )
    }

    @Test
    fun testAllPythonCodeQuestionsPassAutomatedTests() {
        val codeQuestions = QuestionBankRepository.getAuthoredQuestions()
            .map { it.question }
            .filter { it.format == QuestionFormat.CODE }
        assertEquals(68, codeQuestions.size)

        val pyCmd = if (java.io.File("/usr/bin/python").exists()) "python" else "python3"
        val pyRunner: suspend (String) -> AnswerEvaluator.CodeRunResult = { script ->
            val proc = ProcessBuilder(pyCmd, "-X", "utf8", "-c", script)
                .redirectErrorStream(false)
                .start()
            val out = proc.inputStream.bufferedReader(Charsets.UTF_8).readText()
            val err = proc.errorStream.bufferedReader(Charsets.UTF_8).readText()
            val code = proc.waitFor()
            AnswerEvaluator.CodeRunResult(code == 0, out, err.ifBlank { null })
        }

        codeQuestions.forEach { q ->
            val spec = q.code
            assertNotNull("CodeSpec required for ${q.id}", spec)
            val fb = kotlinx.coroutines.runBlocking {
                AnswerEvaluator.evaluateCode(q, spec!!.reference, pyRunner)
            }
            assertTrue(
                "Python reference solution failed for ${q.id}: ${fb.headline} (${fb.details})",
                fb.isCorrect
            )
        }
    }

    @Test
    fun testSimuladoGeneratorEnforcesMax45AndCumulativeRetrieval() {
        // Max 45 cap even if 100 requested
        val bigConfig = SimuladoConfig(
            subject = "Matemática",
            mode = SimuladoMode.BY_SUBJECT,
            questionCount = 100,
            includeLegacy = false
        )
        val cappedSimulado = QuestionBankRepository.generateSimulado(bigConfig, random = kotlin.random.Random(42L))
        assertEquals(QuestionBankRepository.MAX_SIMULADO_QUESTIONS, cappedSimulado.size)

        // Module assessment mixes lessons inside the chosen module
        val fisModules = QuestionBankRepository.getModulesForSubject("Física")
        val targetFisMod = fisModules[1].moduleId
        val modConfig = SimuladoConfig(
            subject = "Física",
            mode = SimuladoMode.BY_MODULE,
            moduleId = targetFisMod,
            questionCount = 20,
            includeLegacy = false
        )
        val modSimulado = QuestionBankRepository.generateSimulado(modConfig, random = kotlin.random.Random(7L))
        assertEquals(20, modSimulado.size)
        assertTrue(modSimulado.all { it.lesson.moduleId == targetFisMod })
        assertTrue("Should mix multiple lessons from $targetFisMod", modSimulado.map { it.question.lessonId }.distinct().size >= 3)

        // Cumulative assessment requires retrieval of previous modules, not just repeating the last module
        val bioModules = QuestionBankRepository.getModulesForSubject("Biologia")
        val fourthBioMod = bioModules[3]
        val cumConfig = SimuladoConfig(
            subject = "Biologia",
            mode = SimuladoMode.CUMULATIVE,
            moduleId = fourthBioMod.moduleId,
            questionCount = 25,
            includeLegacy = false
        )
        val cumSimulado = QuestionBankRepository.generateSimulado(cumConfig, random = kotlin.random.Random(99L))
        assertEquals(25, cumSimulado.size)
        val modulesCovered = cumSimulado.map { it.lesson.moduleId }.toSet()
        assertTrue("Cumulative simulado up to module 4 must include questions from earlier modules", modulesCovered.size >= 2)
        assertTrue("Cumulative simulado up to module 4 must include target module", modulesCovered.contains(fourthBioMod.moduleId))
        assertTrue("Cumulative simulado must not include lessons after target module maxOrder", cumSimulado.all { it.lesson.order <= fourthBioMod.maxOrder })

        // Subtopic filtering
        val subtopics = QuestionBankRepository.getSubtopicsForSubject("Matemática")
        assertTrue(subtopics.isNotEmpty())
        val chosenSub = subtopics.first()
        val filtered = QuestionBankRepository.filterQuestions(subject = "Matemática", subtopic = chosenSub, includeLegacy = false)
        assertTrue(filtered.isNotEmpty())
        assertTrue(filtered.all { it.lesson.subtopic.equals(chosenSub, ignoreCase = true) })
    }

    @Test
    fun testValidateQuestionIntegrityCatchesInvalidQuestions() {
        val validMc = Question(
            id = "q_valid_1",
            lessonId = "enem_m1_l1",
            format = QuestionFormat.MULTIPLE_CHOICE,
            difficulty = QDifficulty.MEDIUM,
            skill = "Resolver proporções",
            concept = "Razão e Proporção",
            statement = "Em uma escala 1:100, 5 cm equivalem a quantos metros?",
            explanation = "5 cm * 100 = 500 cm = 5 m.",
            retryGuidance = "Converta centímetros para metros dividindo por 100.",
            options = listOf("0,5 m", "5 m", "50 m", "500 m"),
            answerIndex = 1
        )
        assertEquals(null, QuestionBankRepository.validateQuestionIntegrity(validMc))

        // Alternativas duplicadas devem ser rejeitadas
        val duplicateOpts = validMc.copy(options = listOf("5 m", "5 m", "50 m", "500 m"))
        assertNotNull(QuestionBankRepository.validateQuestionIntegrity(duplicateOpts))

        // Gabarito fora do intervalo deve ser rejeitado
        val badIndex = validMc.copy(answerIndex = 5)
        assertNotNull(QuestionBankRepository.validateQuestionIntegrity(badIndex))

        // Explicação vazia deve ser rejeitada
        val noExplanation = validMc.copy(explanation = "   ")
        assertNotNull(QuestionBankRepository.validateQuestionIntegrity(noExplanation))
    }

    @Test
    fun testSpecificExamPresetsEnemDia1Dia2AndTranspetro() {
        // 1. ENEM Dia 1 Oficial (90 questões): 45 Linguagens + 45 Ciências Humanas (História, Geografia, Filosofia e Sociologia)
        val dia1Config = SimuladoConfig(
            questionCount = 90,
            preset = SpecificExamPreset.ENEM_DIA_1
        )
        val dia1Simulado1 = QuestionBankRepository.generateSimulado(dia1Config, random = kotlin.random.Random(101L))
        assertEquals(90, dia1Simulado1.size)
        assertTrue(
            "Todas as questões do ENEM Dia 1 devem manter o formato oficial Múltipla Escolha",
            dia1Simulado1.all { it.question.format == QuestionFormat.MULTIPLE_CHOICE }
        )
        val dia1Subjects = dia1Simulado1.map { it.subject }.toSet()
        assertTrue("ENEM Dia 1 deve conter Linguagens", dia1Subjects.contains("Linguagens"))
        assertTrue("ENEM Dia 1 deve conter História", dia1Subjects.contains("História"))
        assertTrue("ENEM Dia 1 deve conter Geografia", dia1Subjects.contains("Geografia"))
        assertTrue("ENEM Dia 1 deve conter Filosofia", dia1Subjects.contains("Filosofia"))
        assertTrue("ENEM Dia 1 deve conter Sociologia", dia1Subjects.contains("Sociologia"))
        assertEquals(45, dia1Simulado1.count { it.subject == "Linguagens" })
        assertEquals(
            45,
            dia1Simulado1.count { it.subject in setOf("História", "Geografia", "Filosofia", "Sociologia") }
        )

        // Verifica controle de semelhança entre simulados seguidos (evita repetir o mesmo padrão exato ou >= 90% igual)
        val dia1Simulado2 = QuestionBankRepository.generateSimulado(dia1Config, random = kotlin.random.Random(202L))
        assertEquals(90, dia1Simulado2.size)
        val ids1 = dia1Simulado1.map { it.id }.toSet()
        val ids2 = dia1Simulado2.map { it.id }.toSet()
        val overlapDia1 = ids1.intersect(ids2).size
        assertTrue(
            "Simulados seguidos do ENEM Dia 1 não devem repetir o mesmo padrão (>35% de sobreposição): overlap=$overlapDia1/90",
            overlapDia1 <= 31
        )

        // 2. ENEM Dia 2 Oficial (90 questões): 45 Ciências da Natureza (15 Biologia, 15 Física, 15 Química) + 45 Matemática
        val dia2Config = SimuladoConfig(
            questionCount = 90,
            preset = SpecificExamPreset.ENEM_DIA_2
        )
        val dia2Questions = QuestionBankRepository.generateSimulado(dia2Config, random = kotlin.random.Random(303L))
        assertEquals(90, dia2Questions.size)
        assertTrue(
            "Todas as questões do ENEM Dia 2 devem manter o formato oficial Múltipla Escolha",
            dia2Questions.all { it.question.format == QuestionFormat.MULTIPLE_CHOICE }
        )
        assertEquals(15, dia2Questions.count { it.subject == "Biologia" })
        assertEquals(15, dia2Questions.count { it.subject == "Física" })
        assertEquals(15, dia2Questions.count { it.subject == "Química" })
        assertEquals(45, dia2Questions.count { it.subject in setOf("Matemática", "Matemática Básica") })

        // 3. Transpetro Oficial Cesgranrio (60 questões): 20 Básicos (10 Português + 10 Matemática) + 40 Específicos
        val tpConfig = SimuladoConfig(
            questionCount = 60,
            preset = SpecificExamPreset.TRANSPETRO
        )
        val tpQuestions = QuestionBankRepository.generateSimulado(tpConfig, random = kotlin.random.Random(404L))
        assertEquals(60, tpQuestions.size)
        assertTrue(
            "Todas as questões do simulado Transpetro devem pertencer ao currículo Transpetro (tp_) e formato Múltipla Escolha",
            tpQuestions.all { it.lesson.lessonId.startsWith("tp_") && it.question.format == QuestionFormat.MULTIPLE_CHOICE }
        )
        assertEquals(10, tpQuestions.count { it.lesson.lessonId.startsWith("tp_por_") })
        assertEquals(10, tpQuestions.count { it.lesson.lessonId.startsWith("tp_mat_") })
        assertEquals(
            40,
            tpQuestions.count {
                it.lesson.lessonId.startsWith("tp_adm_") ||
                    it.lesson.lessonId.startsWith("tp_fin_") ||
                    it.lesson.lessonId.startsWith("tp_log_") ||
                    it.lesson.lessonId.startsWith("tp_inf_")
            }
        )
    }
}

