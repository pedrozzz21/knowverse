package com.example.data.math

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

data class CalculusDidacticBundle(
    val theoryMarkdown: String,
    val formulas: List<FormulaOrSyntaxItem>,
    val solvedExamples: List<LessonExample>,
    val imageResId: Int? = null,
    val goldTip: String,
    val geoGebraConfig: GeoGebraGraphConfig? = null
)

object CalculusDidacticContent {

    private val fallbackBundle = CalculusDidacticBundle(
        theoryMarkdown = """
            Nesta aula de Cálculo I, exploramos o rigor analítico e a intuição geométrica necessários para resolver problemas práticos de engenharia e ciências exatas.
            
            Compreender o conceito teórico e a fundamentação matemática por trás de cada fórmula transforma o cálculo de um mero conjunto de regras mecânicas em uma poderosa linguagem de modelagem da realidade.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Relação Fundamental",
                syntax = "lim_{x -> a} f(x)   ou   f'(x) = dy/dx",
                description = "Aplicação das regras analíticas e operatórias do Cálculo.",
                parametersInfo = "Verifique as condições de diferenciabilidade e continuidade no domínio."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Exemplo Prático de Aplicação",
                codeOrFormula = "Resolução Passo a Passo",
                explanation = "Identifique os dados do problema, aplique a fórmula adequada e verifique as hipóteses teóricas antes de simplificar algebricamente."
            )
        ),
        imageResId = null,
        goldTip = "Siga sempre o ciclo: 1. Entender a Teoria -> 2. Dominar a Fórmula -> 3. Praticar no Exercício!"
    )

    fun getContentForLesson(lessonId: String): CalculusDidacticBundle {
        val bundle = BasicMathDidactic.get(lessonId)
            ?: CalculusLimitsDidactic.get(lessonId)
            ?: CalculusDerivativesDidactic.get(lessonId)
            ?: CalculusIntegralsDidactic.get(lessonId)
            ?: fallbackBundle

        val ggbConfig = GeoGebraCurriculumPresets.get(lessonId)
        return if (ggbConfig != null && bundle.geoGebraConfig == null) {
            bundle.copy(geoGebraConfig = ggbConfig)
        } else {
            bundle
        }
    }
}
