package com.example.data.transpetro

import com.example.data.math.CalculusDidacticBundle
import com.example.data.math.DidacticContentStatus
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

object TranspetroDidacticProvider {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        return when {
            lessonId.startsWith("tp_adm_") -> TranspetroAdmDidactic.getBundle(lessonId)
            lessonId.startsWith("tp_fin_") -> TranspetroFinDidactic.getBundle(lessonId)
            lessonId.startsWith("tp_log_") -> TranspetroLogDidactic.getBundle(lessonId)
            lessonId.startsWith("tp_inf_") -> TranspetroInfDidactic.getBundle(lessonId)
            lessonId.startsWith("tp_por_") -> TranspetroPorDidactic.getBundle(lessonId)
            lessonId.startsWith("tp_mat_") -> TranspetroMatDidactic.getBundle(lessonId)
            else -> null
        }
    }

    fun getSupplementaryExercisesForLesson(lessonId: String): List<MathExercise> {
        return when {
            lessonId.startsWith("tp_adm_") -> TranspetroAdmDidactic.getExercises(lessonId)
            lessonId.startsWith("tp_fin_") -> TranspetroFinDidactic.getExercises(lessonId)
            lessonId.startsWith("tp_log_") -> TranspetroLogDidactic.getExercises(lessonId)
            lessonId.startsWith("tp_inf_") -> TranspetroInfDidactic.getExercises(lessonId)
            lessonId.startsWith("tp_por_") -> TranspetroPorDidactic.getExercises(lessonId)
            lessonId.startsWith("tp_mat_") -> TranspetroMatDidactic.getExercises(lessonId)
            else -> emptyList()
        }
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise> {
        if (!lessonId.startsWith("tp_")) return emptyList()
        val fromBank = TranspetroQuestionBankLoader.getExercisesForLesson(lessonId)
        val supplementary = getSupplementaryExercisesForLesson(lessonId)
        return (fromBank + supplementary).distinctBy { it.id }
    }

    fun buildBundle(
        lessonId: String,
        intro: String,
        theory: String,
        practicalApplication: String,
        summary: String,
        formulaName: String,
        formulaSyntax: String,
        formulaTerms: List<String>,
        formulaWhenToUse: String,
        exampleTitle: String,
        exampleProblem: String,
        exampleSteps: List<String>,
        exampleAnswer: String,
        goldTip: String
    ): CalculusDidacticBundle {
        val fullExplanation = buildString {
            appendLine("1. INTRODUÇÃO")
            appendLine(intro.trim())
            appendLine()
            appendLine("2. EXPLICAÇÃO TEÓRICA")
            appendLine(theory.trim())
            appendLine()
            appendLine("3. EXEMPLOS PRÁTICOS NA ROTINA ADMINISTRATIVA E OPERACIONAL")
            appendLine(practicalApplication.trim())
            appendLine()
            appendLine("4. RESUMO PARA REVISÃO")
            append(summary.trim())
        }
        val formulaItem = FormulaOrSyntaxItem(
            termOrFunction = formulaName,
            syntax = formulaSyntax,
            description = formulaWhenToUse,
            parametersInfo = formulaTerms.joinToString(" | "),
            name = formulaName,
            syntaxOrFormula = formulaSyntax,
            explanation = formulaWhenToUse
        )
        val solvedExample = LessonExample(
            title = exampleTitle,
            codeOrFormula = exampleProblem,
            explanation = exampleSteps.joinToString("\n") + "\n\nResposta: $exampleAnswer",
            problem = exampleProblem
        )
        return CalculusDidacticBundle(
            theoryMarkdown = fullExplanation,
            formulas = listOf(formulaItem),
            formulasAndSyntax = listOf(formulaItem),
            solvedExamples = listOf(solvedExample),
            practicalExamples = listOf(solvedExample),
            goldTip = goldTip,
            status = DidacticContentStatus.REVIEWED
        )
    }

    fun buildExercise(
        id: String,
        lessonId: String,
        statement: String,
        options: List<String>,
        correctIndex: Int,
        steps: List<String>,
        finalComment: String
    ): MathExercise {
        return MathExercise(
            id = id,
            title = "Questão $id",
            problemStatement = statement,
            options = options,
            correctIndex = correctIndex,
            hint = "Dica: atente para o padrão da Fundação Cesgranrio e os conceitos da aula.",
            stepByStepSolution = steps.joinToString("\n") + "\n\n$finalComment"
        )
    }
}
