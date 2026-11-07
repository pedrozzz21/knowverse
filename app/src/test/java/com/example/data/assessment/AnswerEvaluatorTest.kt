package com.example.data.assessment

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class AnswerEvaluatorTest {

    private fun dummyNumericQuestion(expected: Double, tol: Double = 0.0): Question {
        return Question(
            id = "test_num_1",
            lessonId = "lesson_test",
            format = QuestionFormat.NUMERIC,
            difficulty = QDifficulty.MEDIUM,
            skill = "Habilidade de Cálculo",
            concept = "Cálculo Numérico",
            statement = "Calcule o valor da expressão.",
            explanation = "O valor correto é $expected.",
            retryGuidance = "Refaça os cálculos com atenção.",
            source = QuestionSource.AUTORAL,
            answerNumber = expected,
            tolerance = tol
        )
    }

    @Test
    fun testDecimalSeparatorsCommaAndDot() {
        val q = dummyNumericQuestion(expected = 0.5)

        val resComma = AnswerEvaluator.evaluate(q, UserAnswer.Text("0,5"))
        assertEquals("0,5 deve ser aceito como 0.5", Verdict.CORRECT, resComma.verdict)

        val resDot = AnswerEvaluator.evaluate(q, UserAnswer.Text("0.5"))
        assertEquals("0.5 deve ser aceito como 0.5", Verdict.CORRECT, resDot.verdict)
    }

    @Test
    fun testEquivalentFractions() {
        val q = dummyNumericQuestion(expected = 0.5)

        val resHalf = AnswerEvaluator.evaluate(q, UserAnswer.Text("1/2"))
        assertEquals("1/2 deve ser avaliado como 0.5 e aprovado", Verdict.CORRECT, resHalf.verdict)

        val resTwoFourths = AnswerEvaluator.evaluate(q, UserAnswer.Text("2/4"))
        assertEquals("2/4 deve ser simplificado para 0.5 e aprovado", Verdict.CORRECT, resTwoFourths.verdict)

        val resSpaced = AnswerEvaluator.evaluate(q, UserAnswer.Text(" 3 / 6 "))
        assertEquals("3 / 6 deve ser simplificado para 0.5 e aprovado", Verdict.CORRECT, resSpaced.verdict)
    }

    @Test
    fun testNegativeNumbersAndFractions() {
        val q = dummyNumericQuestion(expected = -3.5)

        val resDirect = AnswerEvaluator.evaluate(q, UserAnswer.Text("-3,5"))
        assertEquals("-3,5 deve ser aprovado", Verdict.CORRECT, resDirect.verdict)

        val resFraction = AnswerEvaluator.evaluate(q, UserAnswer.Text("-7/2"))
        assertEquals("-7/2 deve ser aprovado como -3.5", Verdict.CORRECT, resFraction.verdict)
    }

    @Test
    fun testScientificNotation() {
        val q = dummyNumericQuestion(expected = 0.0015)

        val resSci1 = AnswerEvaluator.evaluate(q, UserAnswer.Text("1.5e-3"))
        assertEquals("1.5e-3 deve ser aceito", Verdict.CORRECT, resSci1.verdict)

        val resSci2 = AnswerEvaluator.evaluate(q, UserAnswer.Text("1,5 * 10^-3"))
        assertEquals("1,5 * 10^-3 deve ser aceito", Verdict.CORRECT, resSci2.verdict)

        val qBig = dummyNumericQuestion(expected = 300000000.0)
        val resSci3 = AnswerEvaluator.evaluate(qBig, UserAnswer.Text("3e8"))
        assertEquals("3e8 deve ser aceito", Verdict.CORRECT, resSci3.verdict)
    }

    @Test
    fun testCloseResultsStrictlyRejectedOutsideTolerance() {
        val q = dummyNumericQuestion(expected = 0.50, tol = 0.0) // Usa tolerância relativa padrão de 1% (0,005)

        // 0.48 difere em 4% do valor esperado (0.50), devendo ser REJEITADO
        val resOutside = AnswerEvaluator.evaluate(q, UserAnswer.Text("0,48"))
        assertEquals("0,48 difere em 4% de 0,50 e deve ser rejeitado", Verdict.INCORRECT, resOutside.verdict)

        // 0.498 difere em 0,4% de 0,50 (dentro da margem de 1%), devendo ser aceito
        val resInside = AnswerEvaluator.evaluate(q, UserAnswer.Text("0,498"))
        assertEquals("0,498 está dentro da margem de 1% e deve ser aprovado", Verdict.CORRECT, resInside.verdict)
    }

    @Test
    fun testDivisionByZeroAndInvalidInputs() {
        val q = dummyNumericQuestion(expected = 1.0)

        // Divisão por zero não pode avaliar para 1.0 ou causar crash
        val resDivZero = AnswerEvaluator.evaluate(q, UserAnswer.Text("1/0"))
        assertEquals("1/0 é divisão por zero e deve ser rejeitado", Verdict.INCORRECT, resDivZero.verdict)

        val resText = AnswerEvaluator.evaluate(q, UserAnswer.Text("não sei"))
        assertEquals("Texto arbitrário deve ser rejeitado", Verdict.INCORRECT, resText.verdict)

        val resEmpty = AnswerEvaluator.evaluate(q, UserAnswer.Text(""))
        assertEquals("Resposta vazia deve ser marcada como sem resposta", Verdict.UNANSWERED, resEmpty.verdict)
    }

    @Test
    fun testQuestionBankQuarantineIntegrity() {
        // Assegura que o repositório é carregado
        QuestionBankRepository.ensureLoaded(null)
        val quarantined = QuestionBankRepository.getQuarantinedQuestions()
        
        // Verifica que se houver questões em quarentena, todos os metadados requeridos estão preenchidos
        quarantined.forEach { q ->
            assertTrue("ID não pode estar em branco", q.questionId.isNotBlank())
            assertTrue("Disciplina não pode estar em branco", q.discipline.isNotBlank())
            assertTrue("Motivo da quarentena não pode estar em branco", q.reason.isNotBlank())
            assertTrue("Timestamp deve ser válido", q.timestamp > 0)
            assertTrue("Origem deve estar registrada", q.origin.isNotBlank())
        }

        // Assegura que nenhuma questão em quarentena está presente na lista de questões ativas dos simulados
        val activeIds = QuestionBankRepository.getAllQuestions(null, includeLegacy = true).map { it.question.id }.toSet()
        val quarantinedLegacyIds = quarantined.map { "legacy_${it.questionId}" }.toSet()
        val intersection = activeIds.intersect(quarantinedLegacyIds)
        assertTrue("Questões em quarentena jamais podem aparecer nos simulados ativos: $intersection", intersection.isEmpty())
    }
}
