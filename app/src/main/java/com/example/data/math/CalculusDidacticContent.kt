package com.example.data.math

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

data class CalculusDidacticBundle(
    val theoryMarkdown: String,
    val formulas: List<FormulaOrSyntaxItem>,
    val solvedExamples: List<LessonExample>,
    val imageResId: Int? = null,
    val goldTip: String,
    val geoGebraConfig: GeoGebraGraphConfig? = null,
    val assetImagePath: String? = null
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
        val bundle = com.example.data.geography.EnemGeographyDidacticProvider.getBundleForLesson(lessonId)
            ?: com.example.data.history.EnemHistoryDidacticProvider.getDidacticBundle(lessonId)
            ?: com.example.data.languages.EnemLanguagesDidacticProvider.getDidacticBundle(lessonId)
            ?: com.example.data.biology.EnemBiologyDidacticProvider.getDidacticBundle(lessonId)
            ?: com.example.data.physics.EnemPhysicsDidacticProvider.getDidacticBundle(lessonId)
            ?: EnemDidacticProvider.getDidacticBundle(lessonId)
            ?: BasicMathDidactic.get(lessonId)
            ?: CalculusLimitsDidactic.get(lessonId)
            ?: CalculusDerivativesDidactic.get(lessonId)
            ?: CalculusIntegralsDidactic.get(lessonId)
            ?: getContextualFallback(lessonId)

        val ggbConfig = GeoGebraCurriculumPresets.get(lessonId)
        return if (ggbConfig != null && bundle.geoGebraConfig == null) {
            bundle.copy(geoGebraConfig = ggbConfig)
        } else {
            bundle
        }
    }

    private fun getContextualFallback(lessonId: String): CalculusDidacticBundle {
        val subject = when {
            lessonId.startsWith("geo_") -> "Geografia • Ciências Humanas ENEM"
            lessonId.startsWith("hist_") -> "História • Ciências Humanas ENEM"
            lessonId.startsWith("ling_") -> "Linguagens e Códigos ENEM"
            lessonId.startsWith("bio_") -> "Biologia ENEM"
            lessonId.startsWith("fis_") -> "Física ENEM"
            lessonId.startsWith("enem_") -> "Matemática ENEM"
            lessonId.startsWith("mb_") -> "Matemática Básica"
            else -> "Cálculo I"
        }

        return CalculusDidacticBundle(
            theoryMarkdown = """
                # Aula Didática: $subject
                
                Nesta aula, exploramos a fundamentação conceitual, as relações analíticas e a aplicação prática necessária para resolver problemas estilo ENEM e vestibulares nacionais.
                
                Compreender os princípios teóricos transforma o aprendizado em uma ferramenta sólida de interpretação e raciocínio crítico.
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Princípio Fundamental",
                    syntax = "Relação Teórica e Operatória",
                    description = "Aplicação dos conceitos essenciais de $subject.",
                    parametersInfo = "Verifique as condições do enunciado e os dados fornecidos."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Aplicação Prática",
                    codeOrFormula = "Resolução Passo a Passo",
                    explanation = "Identifique as variáveis do problema, aplique os conceitos adequados e simplifique antes de assinalar a resposta."
                )
            ),
            imageResId = null,
            goldTip = "Revise sempre o enunciado com atenção e elimine as alternativas com contradições evidentes!"
        )
    }
}
