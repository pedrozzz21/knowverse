package com.example.data.math

/**
 * Catálogo completo de configurações e visualizações matemáticas GeoGebra (GGBApplet API)
 * para todas as aulas da trilha de Cálculo I.
 * Substitui ilustrações estáticas por gráficos analíticos vivos e precisos com funções,
 * buracos de descontinuidade, retas tangentes, assíntotas e áreas de integração.
 */
object GeoGebraCurriculumPresets {

    fun get(lessonId: String): GeoGebraGraphConfig? {
        return when (lessonId) {
            // ==========================================
            // MÓDULO 1: LIMITES E CONTINUIDADE
            // ==========================================
            "calc_l1" -> GeoGebraGraphConfig(
                title = "Comportamento ao Redor do Ponto: Buraco (0/0)",
                functionExpression = "(x^2 - 4) / (x - 2)",
                commands = listOf(
                    "f(x) = (x^2 - 4) / (x - 2)",
                    "A = (2, 4)",
                    "SetPointSize(A, 6)",
                    "SetColor(A, 239, 68, 68)",
                    "SetPointStyle(A, 2)"
                ),
                xMin = -3.0,
                xMax = 6.0,
                yMin = -1.0,
                yMax = 8.0,
                didacticExplanation = "A função f(x) = (x²-4)/(x-2) simplifica para x+2 para todo x ≠ 2. No ponto crítico x = 2 ocorre uma indeterminação 0/0 (buraco aberto em vermelho), mas o limite bilateral quando x se aproxima de 2 converge com precisão cirúrgica para 4.",
                analysisPoints = listOf(
                    GeoGebraPoint("A", 2.0, 4.0, "Buraco Removível (2, 4)", isOpenCircle = true, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("(x^2 - 4)/(x - 2)", "(x^2 - 1)/(x - 1)", "(x^3 - 8)/(x - 2)", "x + 2")
            )

            "calc_l2" -> GeoGebraGraphConfig(
                title = "Limites Laterais e Salto Descontínuo (|x|/x)",
                functionExpression = "abs(x) / x",
                commands = listOf(
                    "f(x) = abs(x) / x",
                    "P1 = (0, -1)",
                    "SetPointStyle(P1, 2)",
                    "SetColor(P1, 239, 68, 68)",
                    "P2 = (0, 1)",
                    "SetPointStyle(P2, 2)",
                    "SetColor(P2, 59, 130, 246)"
                ),
                xMin = -4.0,
                xMax = 4.0,
                yMin = -3.0,
                yMax = 3.0,
                didacticExplanation = "Pela esquerda (x -> 0⁻), f(x) = -1. Pela direita (x -> 0⁺), f(x) = +1. Como L₁ ≠ L₂, os limites laterais não coincidem e o limite bilateral NÃO existe!",
                analysisPoints = listOf(
                    GeoGebraPoint("P1", 0.0, -1.0, "lim (x -> 0⁻) = -1", isOpenCircle = true, colorHex = "#EF4444"),
                    GeoGebraPoint("P2", 0.0, 1.0, "lim (x -> 0⁺) = +1", isOpenCircle = true, colorHex = "#3B82F6")
                ),
                presetFunctions = listOf("abs(x) / x", "sign(x)", "If(x < 0, -1, 1)")
            )

            "calc_l3" -> GeoGebraGraphConfig(
                title = "Comportamento no Infinito e Assíntota Horizontal",
                functionExpression = "(2*x + 1) / (x - 1)",
                commands = listOf(
                    "f(x) = (2*x + 1) / (x - 1)",
                    "h: y = 2",
                    "SetColor(h, 234, 179, 8)",
                    "SetLineStyle(h, 1)"
                ),
                xMin = -8.0,
                xMax = 12.0,
                yMin = -5.0,
                yMax = 9.0,
                showAsymptotes = true,
                didacticExplanation = "Conforme x -> +∞ ou x -> -∞, a razão entre os termos líderes converge para 2/1 = 2. A reta tracejada em amarelo y = 2 é a assíntota horizontal.",
                analysisPoints = listOf(
                    GeoGebraPoint("H", 0.0, 2.0, "Assíntota y = 2", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("(2*x + 1)/(x - 1)", "(5*x^3 - 2*x + 1)/(2*x^3 + 7*x^2 - 4)", "1 / x")
            )

            "calc_l4" -> GeoGebraGraphConfig(
                title = "Limites Infinitos e Assíntota Vertical (1/(x - 3))",
                functionExpression = "1 / (x - 3)",
                commands = listOf(
                    "f(x) = 1 / (x - 3)",
                    "v: x = 3",
                    "SetColor(v, 239, 68, 68)",
                    "SetLineStyle(v, 1)"
                ),
                xMin = -3.0,
                xMax = 9.0,
                yMin = -8.0,
                yMax = 8.0,
                showAsymptotes = true,
                didacticExplanation = "Pela direita (3⁺), o denominador é positivo e f(x) -> +∞. Pela esquerda (3⁻), o denominador é negativo e f(x) -> -∞. A reta vertical x = 3 é a assíntota vertical.",
                analysisPoints = listOf(
                    GeoGebraPoint("V", 3.0, 0.0, "Assíntota Vertical x = 3", isOpenCircle = false, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("1 / (x - 3)", "1 / (x - 2)^2", "1 / x")
            )

            "calc_l5" -> GeoGebraGraphConfig(
                title = "Fatoração e Cancelamento de Raízes Comuns (0/0)",
                functionExpression = "(x^3 - 8) / (x^2 - 5*x + 6)",
                commands = listOf(
                    "f(x) = (x^3 - 8) / (x^2 - 5*x + 6)",
                    "H = (2, -12)",
                    "SetPointSize(H, 6)",
                    "SetColor(H, 239, 68, 68)",
                    "SetPointStyle(H, 2)",
                    "v: x = 3",
                    "SetColor(v, 148, 163, 184)",
                    "SetLineStyle(v, 1)"
                ),
                xMin = -2.0,
                xMax = 7.0,
                yMin = -20.0,
                yMax = 10.0,
                didacticExplanation = "Fatorando (x - 2)(x² + 2x + 4) sobre (x - 2)(x - 3), simplificamos o fator nulo (x - 2). No ponto x = 2 o limite é (4+4+4)/(-1) = -12, enquanto em x = 3 existe uma assíntota vertical.",
                analysisPoints = listOf(
                    GeoGebraPoint("H", 2.0, -12.0, "Buraco (2, -12)", isOpenCircle = true, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("(x^3 - 8)/(x^2 - 5*x + 6)", "(x^2 - 9)/(x - 3)", "(x^2 - 4)/(x - 2)")
            )

            "calc_l6" -> GeoGebraGraphConfig(
                title = "Racionalização de Expressões com Raízes",
                functionExpression = "(sqrt(x + 4) - 2) / x",
                commands = listOf(
                    "f(x) = (sqrt(x + 4) - 2) / x",
                    "H = (0, 0.25)",
                    "SetPointSize(H, 6)",
                    "SetColor(H, 56, 189, 248)",
                    "SetPointStyle(H, 2)"
                ),
                xMin = -4.0,
                xMax = 6.0,
                yMin = -0.5,
                yMax = 1.0,
                didacticExplanation = "Multiplicando numerador e denominador pelo conjugado [√(x+4) + 2], eliminamos o radical no numerador. Conforme x -> 0, a curva tende suavemente ao valor 1/4 = 0.25.",
                analysisPoints = listOf(
                    GeoGebraPoint("H", 0.0, 0.25, "lim x->0 = 1/4 (0.25)", isOpenCircle = true, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("(sqrt(x + 4) - 2) / x", "(sqrt(x + 1) - 1) / x", "(sqrt(x + 9) - 3) / x")
            )

            "calc_l7" -> GeoGebraGraphConfig(
                title = "Limite Fundamental Trigonométrico: sen(x)/x -> 1",
                functionExpression = "sin(x) / x",
                commands = listOf(
                    "f(x) = sin(x) / x",
                    "g(x) = cos(x)",
                    "h(x) = 1",
                    "SetColor(g, 148, 163, 184)",
                    "SetLineStyle(g, 1)",
                    "SetColor(h, 234, 179, 8)",
                    "SetLineStyle(h, 1)",
                    "P = (0, 1)",
                    "SetPointStyle(P, 2)",
                    "SetColor(P, 239, 68, 68)"
                ),
                xMin = -7.0,
                xMax = 7.0,
                yMin = -0.5,
                yMax = 1.5,
                didacticExplanation = "O Teorema do Confronto comprime sin(x)/x entre cos(x) e 1. Conforme x -> 0, as duas curvas de fronteira encontram-se em 1, forçando o limite fundamental a ser exatamente 1!",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 0.0, 1.0, "Pico Fundamental (0, 1)", isOpenCircle = true, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("sin(x) / x", "tan(x) / x", "(1 - cos(x)) / x")
            )

            "calc_l8" -> GeoGebraGraphConfig(
                title = "Limite Fundamental Exponencial: (1 + 1/x)^x -> e",
                functionExpression = "(1 + 1/x)^x",
                commands = listOf(
                    "f(x) = (1 + 1/x)^x",
                    "eLine: y = 2.71828",
                    "SetColor(eLine, 234, 179, 8)",
                    "SetLineStyle(eLine, 1)"
                ),
                xMin = -10.0,
                xMax = 25.0,
                yMin = -1.0,
                yMax = 5.0,
                showAsymptotes = true,
                didacticExplanation = "A constante de Euler e ≈ 2.71828 é o limite supremo do crescimento composto contínuo modelado por (1 + 1/x)^x conforme x tende ao infinito.",
                analysisPoints = listOf(
                    GeoGebraPoint("E", 0.0, 2.71828, "Assíntota y = e ≈ 2.718", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("(1 + 1/x)^x", "(1 + 2/x)^x", "(1 + 3/x)^x")
            )

            "calc_l9" -> GeoGebraGraphConfig(
                title = "Continuidade e Teorema do Valor Intermediário (TVI)",
                functionExpression = "x^3 - x - 1",
                commands = listOf(
                    "f(x) = x^3 - x - 1",
                    "A = (1, -1)",
                    "B = (2, 5)",
                    "SetColor(A, 239, 68, 68)",
                    "SetColor(B, 59, 130, 246)",
                    "C = (1.3247, 0)",
                    "SetColor(C, 52, 211, 153)",
                    "SetPointSize(C, 6)"
                ),
                xMin = -1.0,
                xMax = 3.0,
                yMin = -3.0,
                yMax = 7.0,
                didacticExplanation = "Como f(1) = -1 < 0 e f(2) = 5 > 0, e f é contínua no intervalo [1, 2], o TVI garante que a curva cruza obrigatoriamente o eixo X na raiz c ≈ 1.3247 onde f(c) = 0.",
                analysisPoints = listOf(
                    GeoGebraPoint("A", 1.0, -1.0, "f(1) = -1", isOpenCircle = false, colorHex = "#EF4444"),
                    GeoGebraPoint("B", 2.0, 5.0, "f(2) = 5", isOpenCircle = false, colorHex = "#3B82F6"),
                    GeoGebraPoint("C", 1.32, 0.0, "Raiz c ≈ 1.3247", isOpenCircle = false, colorHex = "#34D399")
                ),
                presetFunctions = listOf("x^3 - x - 1", "x^3 - 3*x + 1", "cos(x) - x")
            )

            "calc_l10" -> GeoGebraGraphConfig(
                title = "A Derivada como Limite da Secante (Quociente de Newton)",
                functionExpression = "x^2",
                commands = listOf(
                    "f(x) = x^2",
                    "T = (1, 1)",
                    "t = Tangent(T, f)",
                    "SetColor(t, 234, 179, 8)"
                ),
                xMin = -3.0,
                xMax = 4.0,
                yMin = -1.0,
                yMax = 9.0,
                showTangentSlider = true,
                didacticExplanation = "A reta secante conecta (x₀, f(x₀)) a (x₀+h, f(x₀+h)). Quando h tende a 0, a secante estabiliza-se na RETA TANGENTE, cuja inclinação é a derivada m = f'(x₀) = 2x₀.",
                analysisPoints = listOf(
                    GeoGebraPoint("T", 1.0, 1.0, "Ponto de Tangência (1, 1)", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("x^2", "x^3 - 3*x", "sin(x)", "sqrt(x)")
            )

            // ==========================================
            // MÓDULO 2: DERIVADAS E APLICAÇÕES
            // ==========================================
            "calc_l11" -> GeoGebraGraphConfig(
                title = "Reta Tangente e Inclinação Instantânea",
                functionExpression = "x^2",
                commands = listOf(
                    "f(x) = x^2",
                    "P = (1, 1)",
                    "t = Tangent(P, f)",
                    "SetColor(t, 234, 179, 8)"
                ),
                xMin = -3.0,
                xMax = 4.0,
                yMin = -1.0,
                yMax = 9.0,
                showTangentSlider = true,
                didacticExplanation = "Em x = 1, o coeficiente angular da reta tangente é f'(1) = 2(1) = 2. A equação da reta tangente é y - 1 = 2(x - 1) => y = 2x - 1.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 1.0, 1.0, "Ponto de Tangência (1, 1)", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("x^2", "x^3 - 3*x", "sin(x)", "exp(x)")
            )

            "calc_l16" -> GeoGebraGraphConfig(
                title = "Aproximação Linear e Diferenciais",
                functionExpression = "sqrt(x)",
                commands = listOf(
                    "f(x) = sqrt(x)",
                    "P = (4, 2)",
                    "L = Tangent(P, f)",
                    "SetColor(L, 234, 179, 8)"
                ),
                xMin = 0.0,
                xMax = 9.0,
                yMin = 0.0,
                yMax = 4.0,
                showTangentSlider = true,
                didacticExplanation = "A linearização L(x) = f(a) + f'(a)(x - a) aproxima a curva por sua reta tangente nas proximidades do ponto de base a = 4.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 4.0, 2.0, "Ponto de Base (4, 2)", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("sqrt(x)", "sin(x)", "exp(x)", "1/x")
            )

            "calc_l17", "calc_l18" -> GeoGebraGraphConfig(
                title = "Estudo de Funções: Máximos, Mínimos e Pontos Críticos",
                functionExpression = "x^3 - 3*x",
                commands = listOf(
                    "f(x) = x^3 - 3*x",
                    "MaxPt = (-1, 2)",
                    "MinPt = (1, -2)",
                    "SetColor(MaxPt, 52, 211, 153)",
                    "SetColor(MinPt, 239, 68, 68)",
                    "InflectionPt = (0, 0)",
                    "SetColor(InflectionPt, 234, 179, 8)"
                ),
                xMin = -3.0,
                xMax = 3.0,
                yMin = -4.0,
                yMax = 4.0,
                didacticExplanation = "A primeira derivada f'(x) = 3x² - 3 anula-se nos pontos críticos x = -1 (máximo local em (-1, 2)) e x = 1 (mínimo local em (1, -2)). O ponto (0, 0) é um ponto de inflexão.",
                analysisPoints = listOf(
                    GeoGebraPoint("MaxPt", -1.0, 2.0, "Máximo Local (-1, 2)", isOpenCircle = false, colorHex = "#34D399"),
                    GeoGebraPoint("MinPt", 1.0, -2.0, "Mínimo Local (1, -2)", isOpenCircle = false, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("x^3 - 3*x", "x^4 - 4*x^2", "-x^2 + 4*x", "x*exp(-x)")
            )

            // ==========================================
            // MÓDULO 3: INTEGRAIS E ÁREAS
            // ==========================================
            "calc_l19", "calc_l20", "calc_l22", "calc_l23" -> GeoGebraGraphConfig(
                title = "Integral Definida e Área sob a Curva (Soma de Riemann)",
                functionExpression = "x^2",
                commands = listOf(
                    "f(x) = x^2",
                    "A = Integral(f, 0, 2)",
                    "SetColor(A, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 3.0,
                yMin = -0.5,
                yMax = 5.0,
                showAreaUnderCurve = true,
                integralA = 0.0,
                integralB = 2.0,
                didacticExplanation = "Pelo Teorema Fundamental do Cálculo, a integral definida ∫₀² x² dx calcula a área exata sob a parábola de x = 0 a x = 2: [x³/3]₀² = 8/3 ≈ 2.667 u.a.",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 1.0, 1.0, "Área = 8/3 ≈ 2.67", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("x^2", "sin(x)", "2*x + 1", "sqrt(x)")
            )

            else -> null
        }
    }
}
