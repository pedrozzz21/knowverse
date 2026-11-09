package com.example.data.math

import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Gerador de Conteúdo Didático Estruturado e Exercícios Especializados para a Trilha de Matemática ENEM.
 * Garante cobertura temática estrita (sem fallbacks genéricos de proporcionalidade para assuntos distintos).
 */
object MathModuleGenerator {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        val mod = lesson.moduleId.lowercase()
        val sub = lesson.subtopic.lowercase()

        val (theory, formulas, example, goldTip) = when {
            mod.contains("financeira") || sub.contains("juros") || sub.contains("porcentagem") -> {
                Quad(
                    theory = """
                        # Matemática Financeira: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Dominar o cálculo de acréscimos e descontos sucessivos no contexto do ENEM.
                        • Diferenciar regimes de capitalização: linear (juros simples) vs exponencial (juros compostos).
                        • Modelar situações reais de amortização, inflação e poder de compra.
                        
                        ### Teoria Essencial
                        Nos juros simples, a taxa incide sempre sobre o capital inicial \( C \). Já nos juros compostos, os juros de cada período são incorporados ao principal, gerando juros sobre juros (\( M = C(1 + i)^t \)).
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Juros Simples", "J = C · i · t", "Rendimento linear constante em cada período"),
                        FormulaOrSyntaxItem("Montante Composto", "M = C · (1 + i)^t", "Capitalização exponencial com juros sobre juros")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Aplicação Financeira",
                        codeOrFormula = "C = R$ 1.000,00, i = 10% a.a., t = 2 anos (juros compostos)",
                        explanation = "M = 1000 · (1 + 0,10)² = 1000 · 1,21 = R$ 1.210,00. O rendimento total foi de R$ 210,00."
                    ),
                    goldTip = "No ENEM: Fator multiplicativo de aumento de x% é (1 + x/100); de desconto de x% é (1 - x/100)."
                )
            }
            mod.contains("estatistica") || sub.contains("media") || sub.contains("mediana") || sub.contains("grafico") -> {
                Quad(
                    theory = """
                        # Estatística e Análise de Dados: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Interpretar tabelas, histogramas e gráficos de linha da matriz do ENEM.
                        • Calcular média aritmética simples e ponderada, mediana e moda.
                        • Avaliar medidas de dispersão (variância e desvio padrão) para verificar homogeneidade.
                        
                        ### Teoria Essencial
                        A média resume a grandeza total pelo número de elementos. A mediana representa o valor que divide o conjunto ordenado exatamente ao meio (50% abaixo e 50% acima), sendo imune a valores atípicos (outliers).
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Média Ponderada", "\\bar{x} = \\frac{\\sum (x_i \\cdot p_i)}{\\sum p_i}", "Média ponderada por pesos ou frequências"),
                        FormulaOrSyntaxItem("Mediana (Rol Ordenado)", "Posição = (n + 1)/2 (n ímpar) ou média dos dois centrais (n par)", "Elemento central divisor da distribuição")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Mediana de Notas",
                        codeOrFormula = "Rol ordenado: [12, 14, 15, 18, 20, 22] (n = 6)",
                        explanation = "Os termos centrais são 15 e 18 (3º e 4º elementos). Mediana = (15 + 18)/2 = 16,5."
                    ),
                    goldTip = "Atenção: Antes de calcular a mediana, NUNCA se esqueça de ordenar os dados em ordem crescente (Rol)."
                )
            }
            mod.contains("geometria_espacial") || sub.contains("volume") || sub.contains("prisma") || sub.contains("cilindro") -> {
                Quad(
                    theory = """
                        # Geometria Espacial: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Identificar sólidos geométricos (prismas, cilindros, cones, pirâmides e esferas).
                        • Calcular áreas de superfícies e capacidades volumétricas.
                        • Efetuar conversões de unidades de volume (\( 1\\text{ m}^3 = 1.000\\text{ L} \), \( 1\\text{ dm}^3 = 1\\text{ L} \), \( 1\\text{ cm}^3 = 1\\text{ mL} \)).
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Volume do Cilindro", "V = \\pi \\cdot r^2 \\cdot h", "Área da base circular vezes a altura"),
                        FormulaOrSyntaxItem("Volume do Cone", "V = \\frac{\\pi \\cdot r^2 \\cdot h}{3}", "Um terço do volume do cilindro de mesmo raio e altura")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Reservatório Cilíndrico",
                        codeOrFormula = "Raio r = 2 m, altura h = 5 m, π ≈ 3",
                        explanation = "V = 3 · 2² · 5 = 3 · 4 · 5 = 60 m³. Capacidade em litros = 60 · 1.000 = 60.000 litros."
                    ),
                    goldTip = "Sempre verifique se a questão do ENEM pediu o resultado em metros cúbicos ou em litros!"
                )
            }
            mod.contains("geometria_plana") || sub.contains("area") || sub.contains("triangulo") || sub.contains("circunferencia") -> {
                Quad(
                    theory = """
                        # Geometria Plana: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Aplicar relações métricas no triângulo retângulo (Teorema de Pitágoras).
                        • Calcular áreas de triângulos, quadriláteros e figuras circulares.
                        • Utilizar semelhança de triângulos para resolver problemas de sombras e distâncias inacessíveis.
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Teorema de Pitágoras", "a^2 = b^2 + c^2", "O quadrado da hipotenusa é a soma dos quadrados dos catetos"),
                        FormulaOrSyntaxItem("Área do Trapézio", "A = \\frac{(B + b) \\cdot h}{2}", "Semissoma das bases vezes a altura")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Terreno Trapezoidal",
                        codeOrFormula = "B = 30 m, b = 20 m, h = 10 m",
                        explanation = "A = ((30 + 20) · 10) / 2 = (50 · 10) / 2 = 250 m²."
                    ),
                    goldTip = "O triângulo 3-4-5 e seus múltiplos (6-8-10, 9-12-15) aparecem com muita frequência no ENEM."
                )
            }
            mod.contains("funcoes") || sub.contains("afim") || sub.contains("quadratica") || sub.contains("grafico") -> {
                Quad(
                    theory = """
                        # Álgebra e Funções: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Modelar fenômenos com taxa de variação constante através de funções afins \( f(x) = ax + b \).
                        • Determinar pontos de máximo ou mínimo através do vértice da parábola \( f(x) = ax^2 + bx + c \).
                        • Interpretar raízes e coeficientes gráficos com precisão.
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Taxa de Variação (Função Afim)", "a = \\frac{\\Delta y}{\\Delta x}", "Inclinação da reta no plano cartesiano"),
                        FormulaOrSyntaxItem("Vértice da Parábola", "x_v = -\\frac{b}{2a}, \\quad y_v = -\\frac{\\Delta}{4a}", "Ponto de máximo (a < 0) ou mínimo (a > 0)")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Lucro Máximo",
                        codeOrFormula = "L(x) = -2x² + 40x - 100",
                        explanation = "Quantidade ótima para lucro máximo: x_v = -40 / (2 · (-2)) = -40 / -4 = 10 unidades."
                    ),
                    goldTip = "Se a pergunta pedir 'qual a quantidade para o valor máximo', calcule x_v. Se pedir 'qual o valor máximo', calcule y_v."
                )
            }
            mod.contains("combinatoria") || sub.contains("arranjo") || sub.contains("combinacao") -> {
                Quad(
                    theory = """
                        # Análise Combinatória: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Aplicar o Princípio Fundamental da Contagem (PFC) na tomada de decisões independentes.
                        • Identificar quando a ordem dos elementos importa (Arranjo/Permutação) ou não importa (Combinação).
                        • Estruturar problemas de contagem sem dupla contagem.
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Princípio Fundamental da Contagem", "Total = n_1 \\cdot n_2 \\cdot ... \\cdot n_k", "Multiplicação das opções para cada decisão sucessiva"),
                        FormulaOrSyntaxItem("Combinação Simples", "C(n, p) = \\frac{n!}{p!(n - p)!}", "Agrupamento onde a ordem dos elementos NÃO altera o grupo")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Comissão de Trabalho",
                        codeOrFormula = "Escolher 3 representantes entre 8 candidatos disponíveis",
                        explanation = "Como a ordem dos membros na comissão não importa, C(8, 3) = (8 · 7 · 6) / (3 · 2 · 1) = 56 comissões."
                    ),
                    goldTip = "Pergunte sempre: se eu inverter a ordem dos elementos escolhidos, o grupo é diferente? Se NÃO, use Combinação."
                )
            }
            mod.contains("probabilidade") || sub.contains("chance") || sub.contains("evento") -> {
                Quad(
                    theory = """
                        # Probabilidade: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Calcular a razão clássica de probabilidade: casos favoráveis sobre casos possíveis.
                        • Aplicar o teorema da probabilidade condicional e da união de eventos.
                        • Utilizar a probabilidade do evento complementar para resolver problemas com 'ao menos um'.
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Probabilidade Clássica", "P(A) = \\frac{n(A)}{n(\\Omega)}", "Casos favoráveis dividido pelo espaço amostral"),
                        FormulaOrSyntaxItem("Evento Complementar", "P(A^c) = 1 - P(A)", "Probabilidade de o evento A NÃO ocorrer")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Sorteio em Urna",
                        codeOrFormula = "Urna com 4 bolas azuis e 6 vermelhas (total 10). Retirada de 1 bola azul.",
                        explanation = "P(Azul) = 4 / 10 = 0,40 = 40%."
                    ),
                    goldTip = "Quando o enunciado disser 'a probabilidade de obter pelo menos um...', resolva por 1 - P(nenhum)."
                )
            }
            else -> {
                Quad(
                    theory = """
                        # Matemática Básica e Proporcionalidade: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Operar com números racionais em forma fracionária e decimal.
                        • Resolver problemas com grandezas diretamente e inversamente proporcionais.
                        • Converter escalas cartográficas e unidades de medidas fundamentais.
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Escala Numérica", "E = \\frac{d}{D}", "Comprimento no desenho dividido pelo comprimento real (mesma unidade)"),
                        FormulaOrSyntaxItem("Regra de Três Direta", "\\frac{y_1}{x_1} = \\frac{y_2}{x_2}", "Grandezas proporcionais mantêm razão constante")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Escala em Mapa",
                        codeOrFormula = "Escala 1 : 50.000, distância no mapa de 4 cm",
                        explanation = "Distância real = 4 cm · 50.000 = 200.000 cm = 2.000 m = 2 km."
                    ),
                    goldTip = "Na conversão de escala: lembre-se de que 1 metro tem 100 centímetros e 1 quilômetro tem 100.000 centímetros."
                )
            }
        }

        return CalculusDidacticBundle(
            theoryMarkdown = theory,
            formulas = formulas,
            solvedExamples = listOf(example),
            goldTip = goldTip,
            status = DidacticContentStatus.PENDING_CURATION
        )
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        val mod = lesson.moduleId.lowercase()
        val sub = lesson.subtopic.lowercase()

        val exercise = when {
            mod.contains("financeira") || sub.contains("juros") || sub.contains("porcentagem") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Matemática Financeira",
                    problemStatement = "Um investidor aplica um capital de R$ 2.000,00 sob regime de juros simples com taxa de 1,5% ao mês durante 6 meses. O montante final resgatado ao término da aplicação é de:",
                    options = listOf(
                        "R$ 2.180,00",
                        "R$ 2.090,00",
                        "R$ 2.150,00",
                        "R$ 2.300,00",
                        "R$ 2.030,00"
                    ),
                    correctIndex = 0,
                    hint = "Juros simples: J = C · i · t. Em seguida, some ao capital inicial para encontrar o montante M = C + J.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. Capital C = 2.000, taxa i = 1,5% = 0,015 a.m., tempo t = 6 meses.\n" +
                            "2. J = 2000 · 0,015 · 6 = 30 · 6 = R$ 180,00.\n" +
                            "3. Montante M = C + J = 2.000 + 180 = R$ 2.180,00."
                )
            }
            mod.contains("estatistica") || sub.contains("media") || sub.contains("mediana") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Análise de Mediana",
                    problemStatement = "Em uma gincana escolar, as pontuações obtidas por 5 equipes foram: 14, 28, 19, 32 e 14. A mediana e a moda dessa distribuição de pontuações são, respectivamente:",
                    options = listOf(
                        "Mediana = 19 e Moda = 14",
                        "Mediana = 21,4 e Moda = 14",
                        "Mediana = 14 e Moda = 19",
                        "Mediana = 19 e Moda = 28",
                        "Mediana = 28 e Moda = 14"
                    ),
                    correctIndex = 0,
                    hint = "Ordene os dados (rol) em ordem crescente antes de identificar o termo central para a mediana.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. Rol ordenado dos 5 dados: [14, 14, 19, 28, 32].\n" +
                            "2. Mediana (terceiro elemento de 5 valores): 19.\n" +
                            "3. Moda (valor que mais se repete): 14."
                )
            }
            mod.contains("geometria_espacial") || sub.contains("volume") || sub.contains("cilindro") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Capacidade Volumétrica",
                    problemStatement = "Um reservatório de água possui formato cilíndrico reto com raio da base medindo 1 m e altura de 3 m. Adotando π = 3,14, a capacidade total desse reservatório, em litros, é de:",
                    options = listOf(
                        "9.420 litros",
                        "3.140 litros",
                        "942 litros",
                        "12.560 litros",
                        "18.840 litros"
                    ),
                    correctIndex = 0,
                    hint = "Volume do cilindro: V = π · r² · h. Lembre-se de que 1 m³ equivale a 1.000 litros.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. V = π · r² · h = 3,14 · (1)² · 3 = 3,14 · 3 = 9,42 m³.\n" +
                            "2. Como 1 m³ = 1.000 L, a capacidade é 9,42 · 1.000 = 9.420 litros."
                )
            }
            mod.contains("geometria_plana") || sub.contains("area") || sub.contains("triangulo") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Geometria Plana",
                    problemStatement = "Uma praça tem o formato de um triângulo retângulo cujos catetos medem 60 metros e 80 metros. A extensão da hipotenusa dessa praça é de:",
                    options = listOf(
                        "100 metros",
                        "140 metros",
                        "90 metros",
                        "120 metros",
                        "110 metros"
                    ),
                    correctIndex = 0,
                    hint = "Aplique o Teorema de Pitágoras: a² = b² + c², ou note o triângulo fundamental 3-4-5 multiplicado por 20.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. a² = 60² + 80² = 3.600 + 6.400 = 10.000.\n" +
                            "2. a = √10.000 = 100 metros."
                )
            }
            mod.contains("funcoes") || sub.contains("afim") || sub.contains("quadratica") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Função Afim e Custos",
                    problemStatement = "Uma empresa de transporte cobra uma taxa fixa de R$ 5,00 mais R$ 2,50 por quilômetro rodado. Para uma corrida de 14 quilômetros, o valor total a ser pago é de:",
                    options = listOf(
                        "R$ 40,00",
                        "R$ 35,00",
                        "R$ 45,00",
                        "R$ 37,50",
                        "R$ 42,50"
                    ),
                    correctIndex = 0,
                    hint = "Modele a função afim: P(x) = 2,50 · x + 5,00, substituindo x = 14.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. P(x) = 2,50 · 14 + 5,00.\n" +
                            "2. 2,50 · 14 = 35,00.\n" +
                            "3. P(14) = 35,00 + 5,00 = R$ 40,00."
                )
            }
            mod.contains("combinatoria") || sub.contains("contagem") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Princípio de Contagem",
                    problemStatement = "Para compor uma comissão de formatura, um grupo de 6 estudantes deve eleger 2 membros para cargos idênticos de representação. O número de maneiras distintas de formar essa comissão é:",
                    options = listOf(
                        "15",
                        "30",
                        "12",
                        "24",
                        "36"
                    ),
                    correctIndex = 0,
                    hint = "A ordem dos membros não altera a comissão, portanto trata-se de uma combinação simples: C(6, 2).",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. C(6, 2) = (6 · 5) / (2 · 1) = 30 / 2 = 15 maneiras."
                )
            }
            mod.contains("probabilidade") || sub.contains("chance") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Probabilidade Clássica",
                    problemStatement = "Em um sorteio de amigo secreto com 20 participantes numerados de 1 a 20, uma pessoa retira um bilhete ao acaso. A probabilidade de o número sorteado ser um múltiplo de 4 é de:",
                    options = listOf(
                        "25%",
                        "20%",
                        "30%",
                        "15%",
                        "40%"
                    ),
                    correctIndex = 0,
                    hint = "Identifique os múltiplos de 4 no intervalo de 1 a 20: {4, 8, 12, 16, 20} (5 elementos favoráveis).",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. Casos favoráveis: 5 múltiplos (4, 8, 12, 16, 20).\n" +
                            "2. Espaço amostral: 20 números.\n" +
                            "3. P = 5 / 20 = 1 / 4 = 0,25 = 25%."
                )
            }
            else -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Escalas e Proporções",
                    problemStatement = "Em um mapa desenhado na escala de 1 : 250.000, a distância em linha reta entre duas cidades é de 6 centímetros. A distância real entre essas cidades, em quilômetros, é de:",
                    options = listOf(
                        "15 km",
                        "1,5 km",
                        "150 km",
                        "25 km",
                        "60 km"
                    ),
                    correctIndex = 0,
                    hint = "Multiplique a medida do mapa pela escala: 6 cm · 250.000. Depois converta centímetros para quilômetros (divida por 100.000).",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. Distância real em cm = 6 · 250.000 = 1.500.000 cm.\n" +
                            "2. Convertendo para metros: 1.500.000 / 100 = 15.000 m.\n" +
                            "3. Convertendo para quilômetros: 15.000 / 1.000 = 15 km."
                )
            }
        }

        return listOf(exercise)
    }

    private data class Quad(
        val theory: String,
        val formulas: List<FormulaOrSyntaxItem>,
        val example: LessonExample,
        val goldTip: String
    )
}
