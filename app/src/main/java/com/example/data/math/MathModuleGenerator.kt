package com.example.data.math

import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Gerador de Conteúdo Didático e Exercícios Práticos para a Trilha de Matemática ENEM (81 aulas).
 */
object MathModuleGenerator {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        val formulas = when {
            lesson.moduleId.contains("financeira") -> listOf(
                FormulaOrSyntaxItem("Juros Simples", "J = C · i · t", "Rendimento linear"),
                FormulaOrSyntaxItem("Juros Compostos", "M = C · (1 + i)^t", "Crescimento exponencial sobre o montante acumulado")
            )
            lesson.moduleId.contains("estatistica") -> listOf(
                FormulaOrSyntaxItem("Média Aritmética", "\\bar{x} = \\frac{\\sum x_i}{n}", "Soma dos valores dividida pela quantidade"),
                FormulaOrSyntaxItem("Mediana e Moda", "Mediana: valor central | Moda: valor mais frequente", "Medidas de tendência central")
            )
            lesson.moduleId.contains("geometria_plana") -> listOf(
                FormulaOrSyntaxItem("Área de Figuras Planas", "Triângulo: (b·h)/2 | Círculo: π·r² | Trapézio: (B+b)·h/2", "Cálculo de superfícies bidimensionais"),
                FormulaOrSyntaxItem("Teorema de Pitágoras", "a² = b² + c²", "Relação métrica no triângulo retângulo")
            )
            lesson.moduleId.contains("geometria_espacial") -> listOf(
                FormulaOrSyntaxItem("Volume de Prismas e Cilindros", "V = A_base · h", "Área da base multiplicada pela altura"),
                FormulaOrSyntaxItem("Volume de Pirâmides e Cones", "V = \\frac{A_base \\cdot h}{3}", "Um terço do volume do prisma correspondente")
            )
            lesson.moduleId.contains("funcoes") -> listOf(
                FormulaOrSyntaxItem("Função Afim (1º Grau)", "f(x) = a·x + b", "Reta com taxa de variação constante a e coeficiente linear b"),
                FormulaOrSyntaxItem("Vértice da Parábola", "x_v = -b/(2a)  |  y_v = -Δ/(4a)", "Ponto de máximo ou mínimo da função quadrática")
            )
            lesson.moduleId.contains("combinatoria") -> listOf(
                FormulaOrSyntaxItem("Princípio Fundamental da Contagem (PFC)", "N = n₁ · n₂ · ... · n_k", "Multiplicação das decisões sucessivas"),
                FormulaOrSyntaxItem("Combinação Simples", "C(n, p) = \\frac{n!}{p!(n-p)!}", "A ordem dos elementos não importa")
            )
            lesson.moduleId.contains("probabilidade") -> listOf(
                FormulaOrSyntaxItem("Probabilidade Clássica", "P(A) = \\frac{n(A)}{n(\\Omega)}", "Casos favoráveis sobre casos possíveis"),
                FormulaOrSyntaxItem("Probabilidade do Complementar", "P(A^c) = 1 - P(A)", "Útil em problemas com 'pelo menos um'")
            )
            else -> listOf(
                FormulaOrSyntaxItem("Razão e Proporção", "\\frac{a}{b} = \\frac{c}{d} \\iff a \\cdot d = b \\cdot c", "Propriedade fundamental das proporções"),
                FormulaOrSyntaxItem("Regra de Três", "Grandezas diretas multiplicam cruzado | Inversas multiplicam em linha", "Resolução padrão de proporções")
            )
        }

        return CalculusDidacticBundle(
            theoryMarkdown = """
                # Matemática ENEM: ${lesson.title}
                
                ### Subtópico: ${lesson.subtopic}
                
                Na prova de Matemática do ENEM, mais de 70% das questões exigem competências de modelagem de problemas reais, proporcionalidade, geometria e interpretação de dados.
                
                ### Estratégia de Resolução
                • **Leitura com Foco no Comando:** Leia a última frase da questão primeiro para saber exatamente o que está sendo solicitado (tempo, valor monetário, volume, área ou probabilidade).
                • **Organização dos Dados:** Anote os valores conhecidos e suas unidades de medida.
                • **Aplicação da Relação Matemática:** Escolha a fórmula mais direta para evitar cálculos extensos desnecessários.
            """.trimIndent(),
            formulas = formulas,
            solvedExamples = listOf(
                LessonExample(
                    title = "Exemplo Prático Resolvido",
                    codeOrFormula = "Modelagem da situação problema e cálculo passo a passo.",
                    explanation = "Identificam-se os dados numéricos, aplica-se a proporção ou relação algébrica adequada e simplifica-se antes de multiplicar."
                )
            ),
            goldTip = "No ENEM: Simplifique frações antes de multiplicar! Isso economiza tempo precioso na hora da prova."
        )
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        val q1 = MathExercise(
            id = "${lesson.id}_q1",
            title = "Exercício de Fixação: ${lesson.subtopic}",
            problemStatement = "Em uma situação prática envolvendo ${lesson.subtopic}, um estudante precisa determinar a relação correta entre as variáveis dadas para tomar uma decisão econômica eficiente. A afirmação correta correspondente é:",
            options = listOf(
                "duas grandezas são diretamente proporcionais quando o produto entre elas é constante.",
                "ao dobrar uma grandeza diretamente proporcional, a grandeza correspondente também dobra seu valor.",
                "grandezas inversamente proporcionais variam sempre no mesmo sentido numérico.",
                "o cálculo de probabilidades pode assumir valores negativos em experimentos compostos.",
                "a soma dos ângulos internos de qualquer polígono regular convexo é sempre 180 graus."
            ),
            correctIndex = 1,
            hint = "Pense na definição de grandezas diretamente proporcionais: se uma dobra, a outra dobra.",
            stepByStepSolution = "Alternativa correta é a letra B.\n\n" +
                    "Análise Passo a Passo:\n" +
                    "• Alternativa A está incorreta: O produto constante define grandezas inversamente proporcionais (x · y = k).\n" +
                    "• Alternativa B está correta: Por definição, grandezas diretamente proporcionais mantêm razão constante (y/x = k), logo multiplicar uma por 2 multiplica a outra pelo mesmo fator 2.\n" +
                    "• Alternativa C está incorreta: Grandezas inversas variam em sentidos opostos (uma cresce e a outra decresce).\n" +
                    "• Alternativa D está incorreta: Probabilidade varia estritamente no intervalo [0, 1] ou [0%, 100%], nunca sendo negativa.\n" +
                    "• Alternativa E está incorreta: A soma de 180° vale apenas para triângulos. Para polígonos em geral, S = (n - 2) · 180°."
        )

        return listOf(q1)
    }
}
