package com.example.data.python

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

data class PythonDidacticBundle(
    val theoryMarkdown: String,
    val functionsAndSyntax: List<FormulaOrSyntaxItem>,
    val codeExamples: List<LessonExample>,
    val goldTip: String
)

object PythonDidacticContent {

    private val fallbackBundle = PythonDidacticBundle(
        theoryMarkdown = """
            Nesta lição de Python, exploramos conceitos de lógica, manipulação de dados e programação moderna.
            
            Aprender a sintaxe correta, entender os tipos de dados e praticar no terminal permite que você construa aplicações reais com clareza e alto desempenho.
        """.trimIndent(),
        functionsAndSyntax = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Sintaxe do Python",
                syntax = "comando(argumentos)",
                description = "Execução de instruções nativas e manipulação de objetos em memória.",
                parametersInfo = "Siga a indentação correta de 4 espaços para todos os blocos internos."
            )
        ),
        codeExamples = listOf(
            LessonExample(
                title = "Exemplo Didático",
                codeOrFormula = "# Estrutura típica em Python\nprint('Executando instrução...')",
                explanation = "O interpretador avalia a expressão e exibe o resultado no console."
            )
        ),
        goldTip = "Escreva códigos legíveis e siga os princípios do Zen do Python (import this): 'Simples é melhor que complexo'!"
    )

    fun getContentForLesson(lessonId: String): PythonDidacticBundle {
        return PythonBasicDidactic.get(lessonId)
            ?: PythonIntermediateDidactic.get(lessonId)
            ?: PythonAdvancedDidactic.get(lessonId)
            ?: fallbackBundle
    }
}
