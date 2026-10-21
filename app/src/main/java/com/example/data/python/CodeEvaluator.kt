package com.example.data.python

import com.example.data.model.Lesson
import com.example.data.model.TestCase
import com.example.data.model.ValidationType

data class EvaluationReport(
    val isPassed: Boolean,
    val feedbackTitle: String,
    val feedbackMessage: String,
    val stdout: String,
    val executionTimeMs: Long,
    val errorLine: Int = -1,
    val diagnostic: PythonErrorDiagnostic? = null,
    val xpEarned: Int = 0,
    val testsPassed: Int = 0,
    val totalTests: Int = 0
)

class CodeEvaluator(private val engine: PythonEngine) {

    suspend fun evaluate(lesson: Lesson, userCode: String): EvaluationReport {
        if (userCode.isBlank()) {
            return EvaluationReport(
                isPassed = false,
                feedbackTitle = "Código Vazio",
                feedbackMessage = "Por favor, digite o código da sua solução antes de executar ou avaliar.",
                stdout = "",
                executionTimeMs = 0
            )
        }

        // 1. Run the user's code in Python engine
        val execResult = engine.executeCode(userCode)
        if (!execResult.isSuccess) {
            val errorMsg = execResult.friendlyError ?: execResult.rawError ?: "Erro desconhecido"
            return EvaluationReport(
                isPassed = false,
                feedbackTitle = if (errorMsg.contains("Sintaxe")) "Erro de Sintaxe" else "Erro de Execução",
                feedbackMessage = errorMsg,
                stdout = execResult.stdout,
                executionTimeMs = execResult.executionTimeMs,
                errorLine = execResult.errorLine,
                diagnostic = execResult.diagnostic
            )
        }

        val actualOutput = execResult.stdout.trim()

        // 2. Validate against lesson test cases
        if (lesson.testCases.isEmpty()) {
            return EvaluationReport(
                isPassed = true,
                feedbackTitle = "Execução Concluída com Sucesso",
                feedbackMessage = lesson.solutionExplanation.ifEmpty { "Seu código foi executado perfeitamente pelo interpretador Python!" },
                stdout = actualOutput,
                executionTimeMs = execResult.executionTimeMs,
                xpEarned = lesson.xpReward,
                testsPassed = 1,
                totalTests = 1
            )
        }

        var passedCount = 0
        val totalTests = lesson.testCases.size
        var failureReason = ""

        for (test in lesson.testCases) {
            val testPassed = validateOutput(actualOutput, test)
            if (testPassed) {
                passedCount++
            } else {
                failureReason = buildFailureMessage(actualOutput, test)
                break
            }
        }

        val allPassed = (passedCount == totalTests)

        return if (allPassed) {
            EvaluationReport(
                isPassed = true,
                feedbackTitle = "Excelente! Desafio Concluído",
                feedbackMessage = lesson.solutionExplanation,
                stdout = actualOutput,
                executionTimeMs = execResult.executionTimeMs,
                xpEarned = lesson.xpReward,
                testsPassed = passedCount,
                totalTests = totalTests
            )
        } else {
            EvaluationReport(
                isPassed = false,
                feedbackTitle = "Resultado Divergente",
                feedbackMessage = failureReason,
                stdout = actualOutput,
                executionTimeMs = execResult.executionTimeMs,
                testsPassed = passedCount,
                totalTests = totalTests
            )
        }
    }

    private fun validateOutput(actual: String, test: TestCase): Boolean {
        val expected = test.expectedOutput.trim()
        val normalizedActual = actual.replace("\r\n", "\n").trim()
        val normalizedExpected = expected.replace("\r\n", "\n").trim()

        return when (test.validationType) {
            ValidationType.EXACT_OUTPUT -> {
                normalizedActual.equals(normalizedExpected, ignoreCase = false)
            }
            ValidationType.CONTAINS_OUTPUT -> {
                normalizedActual.contains(normalizedExpected, ignoreCase = true)
            }
            ValidationType.NUMERICAL_RESULT -> {
                val actualNum = extractNumber(normalizedActual)
                val expectedNum = extractNumber(normalizedExpected)
                actualNum != null && expectedNum != null && Math.abs(actualNum - expectedNum) < 0.0001
            }
            ValidationType.FUNCTION_RESULT -> {
                normalizedActual.endsWith(normalizedExpected)
            }
        }
    }

    private fun extractNumber(str: String): Double? {
        val regex = Regex("""[-+]?[0-9]*\.?[0-9]+""")
        val match = regex.find(str)
        return match?.value?.toDoubleOrNull()
    }

    private fun buildFailureMessage(actual: String, test: TestCase): String {
        return buildString {
            append("A saída do seu programa não atendeu ao critério do teste '${test.name}'.\n\n")
            append("• Saída esperada:\n\"${test.expectedOutput}\"\n\n")
            if (actual.isEmpty()) {
                append("• Saída produzida: (Nenhum texto impresso pelo programa)\n\n")
                append("Pista: Lembre-se de utilizar a função print() para exibir o resultado no terminal!")
            } else {
                append("• Saída produzida:\n\"${actual}\"\n\n")
                append("Pista: Verifique se há diferenças de maiúsculas/minúsculas, espaços adicionais ou formatação.")
            }
        }
    }
}
