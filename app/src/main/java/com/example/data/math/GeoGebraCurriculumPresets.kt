package com.example.data.math

/**
 * Catálogo completo de configurações e visualizações matemáticas interativas nativas do Knowverse
 * para todas as 26 aulas da trilha de Cálculo I (Limites, Derivadas e Integrais).
 *
 * Fundamentado nas diretrizes pedagógicas de James Stewart (Calculus, 8ª ed.),
 * Hamilton Guidorizzi (Um Curso de Cálculo, Vol 1) e OpenStax (Calculus Volume 1).
 *
 * Substitui ilustrações estáticas por gráficos analíticos vivos e precisos com funções dinâmicas,
 * buracos de descontinuidade removível (círculos abertos), descontinuidades de salto,
 * retas tangentes com slider, assíntotas verticais e horizontais, e áreas sob a curva (Riemann).
 */
object GeoGebraCurriculumPresets {

    fun get(lessonId: String): GeoGebraGraphConfig? {
        return when (lessonId) {
            // =================================================================
            // MÓDULO 1: LIMITES E CONTINUIDADE (AULAS 1 A 10)
            // =================================================================

            // Aula 1: O que é o Limite? (A Ideia Básica e a Vizinhança)
            "calc_l1" -> GeoGebraGraphConfig(
                title = "Aproximação na Vizinhança e Furo Removível (0/0)",
                functionExpression = "(x^2 - 1) / (x - 1)",
                commands = listOf(
                    "f(x) = (x^2 - 1) / (x - 1)",
                    "A = (1, 2)",
                    "SetPointSize(A, 6)",
                    "SetColor(A, 239, 68, 68)",
                    "SetPointStyle(A, 2)",
                    "L: y = x + 1",
                    "SetColor(L, 56, 189, 248)",
                    "SetLineStyle(L, 1)"
                ),
                xMin = -3.0,
                xMax = 5.0,
                yMin = -2.0,
                yMax = 6.0,
                didacticExplanation = "A função f(x) = (x² - 1)/(x - 1) simplifica algebricamente para x + 1 para todo x ≠ 1. No ponto crítico x = 1 há uma indeterminação 0/0 (círculo aberto em vermelho), mas o limite bilateral quando x se aproxima de 1 converge com exatidão cirúrgica para 2.",
                mathInsight = "lim_{x->1} (x² - 1)/(x - 1) = lim_{x->1} (x + 1) = 2. O limite investiga a vizinhança perfurada 0 < |x - 1| < δ, sem depender do valor pontual estático f(1)!",
                analysisPoints = listOf(
                    GeoGebraPoint("A", 1.0, 2.0, "Buraco Removível (1, 2)", isOpenCircle = true, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("(x^2 - 1) / (x - 1)", "(x^2 - 4) / (x - 2)", "(x^3 - 1) / (x - 1)", "x + 1")
            )

            // Aula 2: Limites Laterais e Existência
            "calc_l2" -> GeoGebraGraphConfig(
                title = "Limites Laterais e Descontinuidade de Salto: |x|/x",
                functionExpression = "abs(x) / x",
                commands = listOf(
                    "f(x) = abs(x) / x",
                    "P1 = (0, -1)",
                    "SetColor(P1, 239, 68, 68)",
                    "SetPointStyle(P1, 2)",
                    "P2 = (0, 1)",
                    "SetColor(P2, 16, 185, 129)",
                    "SetPointStyle(P2, 2)"
                ),
                xMin = -4.0,
                xMax = 4.0,
                yMin = -2.5,
                yMax = 2.5,
                didacticExplanation = "Pela esquerda (x -> 0⁻), f(x) = -1. Pela direita (x -> 0⁺), f(x) = +1. Como os limites laterais convergem para alturas distintas (L₁ = -1 ≠ L₂ = 1), o gráfico sofre uma quebra vertical com salto |1 - (-1)| = 2 e o limite bilateral NÃO EXISTE!",
                mathInsight = "Teorema Fundamental: lim_{x->a} f(x) = L se e somente se lim_{x->a⁻} f(x) = lim_{x->a⁺} f(x) = L. Se os caminhos não se encontram, o limite bilateral deixa de existir.",
                analysisPoints = listOf(
                    GeoGebraPoint("P1", 0.0, -1.0, "lim (x -> 0⁻) = -1", isOpenCircle = true, colorHex = "#EF4444"),
                    GeoGebraPoint("P2", 0.0, 1.0, "lim (x -> 0⁺) = +1", isOpenCircle = true, colorHex = "#10B981")
                ),
                presetFunctions = listOf("abs(x) / x", "abs(x - 2) / (x - 2)", "sign(x)")
            )

            // Aula 3: Propriedades e Substituição Direta
            "calc_l3" -> GeoGebraGraphConfig(
                title = "Substituição Direta em Funções Contínuas: f(a) = L",
                functionExpression = "x^2 - 2*x + 2",
                commands = listOf(
                    "f(x) = x^2 - 2*x + 2",
                    "P = (2, 2)",
                    "SetColor(P, 16, 185, 129)",
                    "SetPointSize(P, 6)",
                    "h: y = 2",
                    "SetColor(h, 234, 179, 8)",
                    "SetLineStyle(h, 1)",
                    "v: x = 2",
                    "SetColor(v, 56, 189, 248)",
                    "SetLineStyle(v, 1)"
                ),
                xMin = -1.0,
                xMax = 5.0,
                yMin = 0.0,
                yMax = 8.0,
                didacticExplanation = "Para funções polinomiais e racionais bem comportadas (onde o denominador não se anula), vale a Propriedade da Substituição Direta: o limite é exatamente o valor numérico f(a). Em x = 2, f(2) = 2² - 2(2) + 2 = 2.",
                mathInsight = "lim_{x->a} f(x) = f(a) para toda função contínua. Sem indeterminações ou singularidades no domínio, a aproximação é suave e imediata.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 2.0, 2.0, "Substituição Direta f(2) = 2", isOpenCircle = false, colorHex = "#10B981")
                ),
                presetFunctions = listOf("x^2 - 2*x + 2", "2*x + 1", "x^3 - x + 1", "cos(x)")
            )

            // Aula 4: Indeterminação 0/0 e o Furo Removível
            "calc_l4" -> GeoGebraGraphConfig(
                title = "Cancelamento Algébrico da Forma Indeterminada 0/0",
                functionExpression = "(x^2 - 4) / (x - 2)",
                commands = listOf(
                    "f(x) = (x^2 - 4) / (x - 2)",
                    "H = (2, 4)",
                    "SetPointSize(H, 6)",
                    "SetColor(H, 239, 68, 68)",
                    "SetPointStyle(H, 2)",
                    "line: y = x + 2",
                    "SetColor(line, 56, 189, 248)",
                    "SetLineStyle(line, 1)"
                ),
                xMin = -2.0,
                xMax = 6.0,
                yMin = -1.0,
                yMax = 8.0,
                didacticExplanation = "A indeterminação 0/0 ocorre porque numerador e denominador compartilham o fator nulo (x - 2). Fatorando pela diferença de quadrados: (x - 2)(x + 2)/(x - 2) = x + 2 para x ≠ 2. O furo em (2, 4) é removível e o limite vale 4.",
                mathInsight = "Fatoração algébrica destrava a indeterminação: 0/0 não significa que o limite inexiste, mas sim que devemos cancelar o fator que zera o denominador.",
                analysisPoints = listOf(
                    GeoGebraPoint("H", 2.0, 4.0, "Furo Removível (2, 4)", isOpenCircle = true, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("(x^2 - 4) / (x - 2)", "(x^2 - 9) / (x - 3)", "(x^3 - 8) / (x - 2)", "x + 2")
            )

            // Aula 5: Ferramentas Algébricas (Fatoração e Racionalização)
            "calc_l5" -> GeoGebraGraphConfig(
                title = "Racionalização de Expressões com Radicais",
                functionExpression = "(sqrt(x + 4) - 2) / x",
                commands = listOf(
                    "f(x) = (sqrt(x + 4) - 2) / x",
                    "H = (0, 0.25)",
                    "SetPointSize(H, 6)",
                    "SetColor(H, 56, 189, 248)",
                    "SetPointStyle(H, 2)",
                    "h: y = 0.25",
                    "SetColor(h, 234, 179, 8)",
                    "SetLineStyle(h, 1)"
                ),
                xMin = -3.5,
                xMax = 6.0,
                yMin = -0.2,
                yMax = 0.8,
                didacticExplanation = "Substituir x = 0 produz (√4 - 2)/0 = 0/0. Multiplicando pelo conjugado [√(x+4) + 2], eliminamos a raiz do numerador: 1 / [√(x+4) + 2]. Quando x -> 0, o limite é 1/(2 + 2) = 1/4 = 0.25.",
                mathInsight = "O produto notável (A - B)(A + B) = A² - B² remove o radical do numerador, permitindo o cancelamento do termo nulo x.",
                analysisPoints = listOf(
                    GeoGebraPoint("H", 0.0, 0.25, "lim x->0 = 1/4 = 0.25", isOpenCircle = true, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("(sqrt(x + 4) - 2) / x", "(sqrt(x + 1) - 1) / x", "(sqrt(x + 9) - 3) / x")
            )

            // Aula 6: Continuidade e Matriz Diagnóstica (TVI)
            "calc_l6" -> GeoGebraGraphConfig(
                title = "Continuidade e Teorema do Valor Intermediário (TVI)",
                functionExpression = "x^3 - x - 1",
                commands = listOf(
                    "f(x) = x^3 - x - 1",
                    "A = (1, -1)",
                    "SetColor(A, 239, 68, 68)",
                    "B = (2, 5)",
                    "SetColor(B, 59, 130, 246)",
                    "C = (1.3247, 0)",
                    "SetColor(C, 16, 185, 129)",
                    "SetPointSize(C, 6)"
                ),
                xMin = -1.0,
                xMax = 3.0,
                yMin = -3.0,
                yMax = 7.0,
                didacticExplanation = "Como f(1) = -1 < 0 e f(2) = 5 > 0, e a função polinomial f é contínua no intervalo [1, 2], o Teorema do Valor Intermediário (TVI) garante que a curva cruza obrigatoriamente o eixo X na raiz c ≈ 1.325 onde f(c) = 0.",
                mathInsight = "Se f é contínua em [a, b] e f(a) e f(b) têm sinais opostos, existe ao menos um c tal que f(c) = 0 (Teorema de Bolzano).",
                analysisPoints = listOf(
                    GeoGebraPoint("A", 1.0, -1.0, "f(1) = -1 < 0", isOpenCircle = false, colorHex = "#EF4444"),
                    GeoGebraPoint("B", 2.0, 5.0, "f(2) = 5 > 0", isOpenCircle = false, colorHex = "#3B82F6"),
                    GeoGebraPoint("C", 1.325, 0.0, "Raiz TVI c ≈ 1.325", isOpenCircle = false, colorHex = "#10B981")
                ),
                presetFunctions = listOf("x^3 - x - 1", "x^3 - 3*x + 1", "x^2 - 2")
            )

            // Aula 7: Limites Infinitos e Assíntotas Verticais
            "calc_l7" -> GeoGebraGraphConfig(
                title = "Limites Infinitos e Assíntota Vertical: 1/(x - 2)^2",
                functionExpression = "1 / ((x - 2)^2)",
                commands = listOf(
                    "f(x) = 1 / ((x - 2)^2)",
                    "v: x = 2",
                    "SetColor(v, 239, 68, 68)",
                    "SetLineStyle(v, 1)"
                ),
                xMin = -2.0,
                xMax = 6.0,
                yMin = -1.0,
                yMax = 12.0,
                showAsymptotes = true,
                didacticExplanation = "Conforme x se aproxima de 2, o numerador permanece constante (1) e o denominador tende a zero permanecendo estritamente positivo ((x - 2)² > 0). Logo, f(x) diverge para +∞ em ambos os lados. A reta vertical x = 2 é a assíntota vertical.",
                mathInsight = "Constante / (Quase Zero Positivo) = +∞. A curva empina e cresce sem limite conforme x se aproxima da reta x = 2.",
                analysisPoints = listOf(
                    GeoGebraPoint("V", 2.0, 0.0, "Assíntota Vertical x = 2", isOpenCircle = false, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("1 / ((x - 2)^2)", "1 / (x - 2)", "1 / (x^2 - 1)")
            )

            // Aula 8: Limites no Infinito e Assíntotas Horizontais
            "calc_l8" -> GeoGebraGraphConfig(
                title = "Comportamento no Infinito e Assíntota Horizontal: y = 2",
                functionExpression = "(2*x^2 + 1) / (x^2 + 1)",
                commands = listOf(
                    "f(x) = (2*x^2 + 1) / (x^2 + 1)",
                    "h: y = 2",
                    "SetColor(h, 234, 179, 8)",
                    "SetLineStyle(h, 1)"
                ),
                xMin = -8.0,
                xMax = 8.0,
                yMin = -0.5,
                yMax = 3.5,
                showAsymptotes = true,
                didacticExplanation = "Quando x cresce sem limites (x -> ±∞), os termos constantes perdem relevância e a razão entre os termos líderes 2x²/x² converge para 2. A reta horizontal y = 2 é a assíntota horizontal da função.",
                mathInsight = "lim_{x->±∞} f(x) = L determina o comportamento assintótico de longo prazo da função.",
                analysisPoints = listOf(
                    GeoGebraPoint("H", 0.0, 2.0, "Assíntota Horizontal y = 2", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("(2*x^2 + 1) / (x^2 + 1)", "(2*x + 1) / (x - 1)", "(1 + 1/x)^x", "1 / x")
            )

            // Aula 9: Teorema do Sanduíche e Limite Trigonométrico
            "calc_l9" -> GeoGebraGraphConfig(
                title = "Teorema do Confronto (Sanduíche): sen(x)/x -> 1",
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
                xMin = -6.5,
                xMax = 6.5,
                yMin = -0.5,
                yMax = 1.6,
                didacticExplanation = "Pelo Teorema do Confronto (Sanduíche), vale cos(x) ≤ sen(x)/x ≤ 1 na vizinhança de zero. Como tanto a curva inferior cos(x) quanto a superior 1 convergem para 1 quando x -> 0, a curva central sen(x)/x é 'espremida' exatamente contra 1!",
                mathInsight = "cos(x) ≤ sen(x)/x ≤ 1 => lim_{x->0} sen(x)/x = 1. Este limite é a pedra angular da diferenciação de funções trigonométricas.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 0.0, 1.0, "Pico Fundamental (0, 1)", isOpenCircle = true, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("sin(x) / x", "cos(x)", "1", "(1 - cos(x)) / x")
            )

            // Aula 10: O Elo Fundamental (Da Secante à Tangente)
            "calc_l10" -> GeoGebraGraphConfig(
                title = "Da Reta Secante à Reta Tangente (Quociente de Newton)",
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
                didacticExplanation = "A reta secante conecta (x₀, f(x₀)) a (x₀+h, f(x₀+h)) com inclinação Δy/Δx. Conforme h tende a zero, a secante gira e estabiliza-se na RETA TANGENTE, cuja inclinação m = f'(x₀) = 2(1) = 2 é a derivada instantânea.",
                mathInsight = "f'(x₀) = lim_{h->0} [f(x₀+h) - f(x₀)] / h. A derivada é o limite geométrico supremo da rotação da secante.",
                analysisPoints = listOf(
                    GeoGebraPoint("T", 1.0, 1.0, "Ponto de Tangência (1, 1)", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("x^2", "x^3 - 3*x", "sin(x)", "sqrt(x)")
            )

            // =================================================================
            // MÓDULO 2: DERIVADAS E APLICAÇÕES (AULAS 11 A 18)
            // =================================================================

            // Aula 11: O que é a Derivada (Na Prática)?
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
                didacticExplanation = "Em x = 1, o coeficiente angular da reta tangente é f'(1) = 2(1) = 2. A equação da reta tangente é y - 1 = 2(x - 1) => y = 2x - 1. Arraste o slider para ver a reta tangente deslizar suavemente ao longo da curva.",
                mathInsight = "A derivada f'(x) mede a velocidade instantânea de subida ou descida da função em cada ponto.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 1.0, 1.0, "Ponto de Tangência (1, 1)", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("x^2", "x^3 - 3*x", "sin(x)", "exp(x)")
            )

            // Aula 12: As Regras Rápidas de Derivação (Regra da Potência)
            "calc_l12" -> GeoGebraGraphConfig(
                title = "Regra da Potência e Polinômios: d/dx(x³ - 3x)",
                functionExpression = "x^3 - 3*x",
                commands = listOf(
                    "f(x) = x^3 - 3*x",
                    "P = (1, -2)",
                    "t = Tangent(P, f)",
                    "SetColor(t, 234, 179, 8)"
                ),
                xMin = -3.0,
                xMax = 3.0,
                yMin = -4.0,
                yMax = 4.0,
                showTangentSlider = true,
                didacticExplanation = "Pela regra da potência: d/dx(x³) = 3x² e d/dx(-3x) = -3. Logo f'(x) = 3x² - 3. No ponto x = 1, a tangente é horizontal f'(1) = 0, revelando um ponto crítico de mínimo local.",
                mathInsight = "d/dx(xⁿ) = n·xⁿ⁻¹. Onde a derivada se anula, a reta tangente torna-se perfeitamente horizontal.",
                analysisPoints = listOf(
                    GeoGebraPoint("Max", -1.0, 2.0, "Máximo Relativo (-1, 2)", isOpenCircle = false, colorHex = "#34D399"),
                    GeoGebraPoint("Min", 1.0, -2.0, "Mínimo Relativo (1, -2)", isOpenCircle = false, colorHex = "#EF4444")
                ),
                presetFunctions = listOf("x^3 - 3*x", "x^2 - 4*x + 3", "2*x^3 - 6*x", "x^4 - 2*x^2")
            )

            // Aula 13: As 3 Regras de Ouro para Operações (Produto e Quociente)
            "calc_l13" -> GeoGebraGraphConfig(
                title = "Regra do Produto e Exponenciais: d/dx(x · e⁻ˣ)",
                functionExpression = "x * exp(-x)",
                commands = listOf(
                    "f(x) = x * exp(-x)",
                    "M = (1, 0.3679)",
                    "SetColor(M, 52, 211, 153)",
                    "SetPointSize(M, 6)"
                ),
                xMin = -1.0,
                xMax = 5.0,
                yMin = -0.5,
                yMax = 0.8,
                showTangentSlider = true,
                didacticExplanation = "Pela regra do produto (u·v)' = u'v + uv': para f(x) = x·e⁻ˣ, temos f'(x) = 1·e⁻ˣ + x(-e⁻ˣ) = (1 - x)e⁻ˣ. O ponto x = 1 é o ápice da curva (máximo absoluto em y = 1/e ≈ 0.368).",
                mathInsight = "A regra do produto preserva a interação entre crescimento linear x e amortecimento exponencial e⁻ˣ.",
                analysisPoints = listOf(
                    GeoGebraPoint("M", 1.0, 0.368, "Pico Global (1, 1/e ≈ 0.368)", isOpenCircle = false, colorHex = "#34D399")
                ),
                presetFunctions = listOf("x * exp(-x)", "x * sin(x)", "exp(x) / x", "x^2 * exp(-x)")
            )

            // Aula 14: Técnicas Especiais: Derivação Implícita e Logarítmica
            "calc_l14" -> GeoGebraGraphConfig(
                title = "Derivação Implícita e o Círculo: x² + y² = 25",
                functionExpression = "sqrt(25 - x^2)",
                commands = listOf(
                    "f(x) = sqrt(25 - x^2)",
                    "P = (3, 4)",
                    "t = Tangent(P, f)",
                    "SetColor(t, 234, 179, 8)"
                ),
                xMin = -6.0,
                xMax = 6.0,
                yMin = -1.0,
                yMax = 7.0,
                showTangentSlider = true,
                didacticExplanation = "Diferenciando x² + y² = 25 implicitamente em relação a x: 2x + 2y(dy/dx) = 0 => dy/dx = -x/y. No ponto (3, 4), a inclinação da reta tangente é m = -3/4 = -0.75.",
                mathInsight = "A derivação implícita trata y como função de x mesmo quando não é prático isolar y explicitamente.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 3.0, 4.0, "Tangência (3, 4) m = -0.75", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("sqrt(25 - x^2)", "sqrt(16 - x^2)", "x^(1/3)", "ln(x)")
            )

            // Aula 15: Aproximação Linear e Diferenciais
            "calc_l15" -> GeoGebraGraphConfig(
                title = "Aproximação Linear e Diferenciais: L(x) ≈ √x",
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
                didacticExplanation = "A linearização L(x) = f(a) + f'(a)(x - a) aproxima a curva pela reta tangente em torno de a = 4: L(x) = 2 + 0.25(x - 4). Para x = 4.08, L(4.08) = 2 + 0.25(0.08) = 2.02 ≈ √4.08.",
                mathInsight = "A reta tangente é a melhor aproximação de 1º grau de uma curva suave nas proximidades do ponto de contato.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 4.0, 2.0, "Ponto Base a = 4, f(4) = 2", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("sqrt(x)", "sin(x)", "exp(x)", "1 / x")
            )

            // Aula 16: Estudo Completo da Função e Esboço de Gráficos
            "calc_l16" -> GeoGebraGraphConfig(
                title = "Estudo Completo da Função: Máximos, Mínimos e Inflexão",
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
                showExtrema = true,
                didacticExplanation = "A 1ª derivada f'(x) = 3x² - 3 anula-se nos pontos críticos x = -1 (máximo local em (-1, 2)) e x = 1 (mínimo local em (1, -2)). A 2ª derivada f''(x) = 6x anula-se em x = 0, que é o ponto de inflexão de concavidade.",
                mathInsight = "f'(x) rege crescimento e pontos críticos; f''(x) rege concavidade e pontos de inflexão.",
                analysisPoints = listOf(
                    GeoGebraPoint("MaxPt", -1.0, 2.0, "Máximo Local (-1, 2)", isOpenCircle = false, colorHex = "#34D399"),
                    GeoGebraPoint("MinPt", 1.0, -2.0, "Mínimo Local (1, -2)", isOpenCircle = false, colorHex = "#EF4444"),
                    GeoGebraPoint("InfPt", 0.0, 0.0, "Ponto de Inflexão (0, 0)", isOpenCircle = false, colorHex = "#EAB308")
                ),
                presetFunctions = listOf("x^3 - 3*x", "x^4 - 4*x^2", "-x^2 + 4*x", "x*exp(-x)")
            )

            // Aula 17: Regra de L'Hôpital (O Shortcut de Ouro)
            "calc_l17" -> GeoGebraGraphConfig(
                title = "Regra de L'Hôpital: (eˣ - 1)/x -> 1",
                functionExpression = "(exp(x) - 1) / x",
                commands = listOf(
                    "f(x) = (exp(x) - 1) / x",
                    "H = (0, 1)",
                    "SetPointSize(H, 6)",
                    "SetColor(H, 56, 189, 248)",
                    "SetPointStyle(H, 2)"
                ),
                xMin = -4.0,
                xMax = 4.0,
                yMin = -0.5,
                yMax = 3.5,
                didacticExplanation = "A substituição direta em x = 0 produz a forma indeterminada 0/0. Pela Regra de L'Hôpital, derivamos numerador e denominador separadamente: d/dx(eˣ - 1) / d/dx(x) = eˣ / 1. Conforme x -> 0, o limite é e⁰ = 1.",
                mathInsight = "L'Hôpital: lim [f(x)/g(x)] = lim [f'(x)/g'(x)] para indeterminações do tipo 0/0 ou ∞/∞ com funções diferenciáveis.",
                analysisPoints = listOf(
                    GeoGebraPoint("H", 0.0, 1.0, "lim x->0 = 1 (L'Hôpital)", isOpenCircle = true, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("(exp(x) - 1) / x", "(sin(x)) / x", "(ln(x)) / (x - 1)", "(1 - cos(x)) / (x^2)")
            )

            // Aula 18: Problemas de Otimização (A Aplicação Suprema)
            "calc_l18" -> GeoGebraGraphConfig(
                title = "Otimização: Vértice e Máximo Global: -x² + 6x",
                functionExpression = "-x^2 + 6*x",
                commands = listOf(
                    "f(x) = -x^2 + 6*x",
                    "V = (3, 9)",
                    "SetColor(V, 52, 211, 153)",
                    "SetPointSize(V, 7)"
                ),
                xMin = -1.0,
                xMax = 7.0,
                yMin = -2.0,
                yMax = 11.0,
                showExtrema = true,
                didacticExplanation = "Problemas de dimensionamento, lucro máximo e custo mínimo traduzem-se em encontrar extremos de funções. Derivando f(x) = -x² + 6x: f'(x) = -2x + 6 = 0 => x = 3. O ponto V(3, 9) é o máximo global absoluto.",
                mathInsight = "f'(x) = 0 identifica o dimensionamento ótimo; f''(x) < 0 confirma que o ponto crítico é um pico de rendimento máximo.",
                analysisPoints = listOf(
                    GeoGebraPoint("V", 3.0, 9.0, "Vértice / Máximo Global (3, 9)", isOpenCircle = false, colorHex = "#34D399")
                ),
                presetFunctions = listOf("-x^2 + 6*x", "-2*x^2 + 8*x", "4*x - x^2", "x*(12 - 2*x)")
            )

            // =================================================================
            // MÓDULO 3: INTEGRAIS E ÁREAS (AULAS 19 A 26)
            // =================================================================

            // Aula 19: O que significa 'Integrar'? (Primitivas e Antiderivadas)
            "calc_l19" -> GeoGebraGraphConfig(
                title = "O Significado da Integral: Área sob a Curva f(x) = x²",
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
                didacticExplanation = "A integração é a operação inversa da diferenciação (antiderivada) e calcula o acúmulo contínuo de área sob a curva. Para f(x) = x², a área de x = 0 a x = 2 é [x³/3]₀² = 8/3 ≈ 2.667 u.a.",
                mathInsight = "A integral conecta taxas instantâneas locais ao volume total acumulado no intervalo.",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 1.0, 1.0, "Área = 8/3 ≈ 2.67", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("x^2", "2*x", "x + 1", "sqrt(x)")
            )

            // Aula 20: A Mecânica Básica: Regra da Potência
            "calc_l20" -> GeoGebraGraphConfig(
                title = "Regra da Potência na Integral: ∫ 3x² dx = x³ + C",
                functionExpression = "3*x^2",
                commands = listOf(
                    "f(x) = 3*x^2",
                    "A = Integral(f, 0, 2)",
                    "SetColor(A, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 3.0,
                yMin = -1.0,
                yMax = 14.0,
                showAreaUnderCurve = true,
                integralA = 0.0,
                integralB = 2.0,
                didacticExplanation = "Pela regra da potência: ∫ xⁿ dx = [xⁿ⁺¹ / (n + 1)] + C. Para f(x) = 3x², a primitiva é F(x) = 3(x³/3) = x³. A integral definida de 0 a 2 é F(2) - F(0) = 2³ - 0 = 8.",
                mathInsight = "A regra da potência na integral 'desfaz' a derivada da potência, somando 1 ao expoente e dividindo pelo novo expoente.",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 1.5, 3.0, "Área Exata = 8.0 u.a.", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("3*x^2", "x^3", "4*x^3", "x^2")
            )

            // Aula 21: Exponenciais, Logaritmos e Funções Trigonométricas
            "calc_l21" -> GeoGebraGraphConfig(
                title = "Integrais Trigonométricas: ∫ cos(x) dx = sen(x)",
                functionExpression = "cos(x)",
                commands = listOf(
                    "f(x) = cos(x)",
                    "A = Integral(f, 0, 1.5708)",
                    "SetColor(A, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 4.0,
                yMin = -1.5,
                yMax = 1.5,
                showAreaUnderCurve = true,
                integralA = 0.0,
                integralB = 1.5708,
                didacticExplanation = "A área sob o arco de cosseno de x = 0 a x = π/2 ≈ 1.571 é exatamente [sen(x)]₀^(π/2) = sen(π/2) - sen(0) = 1 - 0 = 1 unidade de área exata.",
                mathInsight = "∫₀^(π/2) cos(x) dx = 1. A geometria das ondas e oscilações harmônicas possui áreas perfeitamente inteiras!",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 0.785, 0.5, "Área sob o Arco = 1.0", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("cos(x)", "sin(x)", "exp(x)", "1 / x")
            )

            // Aula 22: Propriedades da Linearidade na Integral
            "calc_l22" -> GeoGebraGraphConfig(
                title = "Linearidade da Integral: ∫ (2x + 1) dx",
                functionExpression = "2*x + 1",
                commands = listOf(
                    "f(x) = 2*x + 1",
                    "A = Integral(f, 0, 3)",
                    "SetColor(A, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 4.0,
                yMin = -1.0,
                yMax = 9.0,
                showAreaUnderCurve = true,
                integralA = 0.0,
                integralB = 3.0,
                didacticExplanation = "Pela linearidade: ∫ [2x + 1] dx = 2∫x dx + ∫1 dx = x² + x. De x = 0 a x = 3, a área é (3² + 3) - 0 = 9 + 3 = 12 unidades de área (área do trapézio de bases 1 e 7 e altura 3).",
                mathInsight = "A linearidade permite decompor problemas complexos na soma de integrais elementares imediatas.",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 1.5, 2.0, "Área do Trapézio = 12.0", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("2*x + 1", "3*x + 2", "x^2 + 2*x", "4 - x")
            )

            // Aula 23: Técnica de Substituição (Regra da Cadeia ao Contrário)
            "calc_l23" -> GeoGebraGraphConfig(
                title = "Substituição Simbólica: ∫ 2x · cos(x²) dx",
                functionExpression = "2*x * cos(x^2)",
                commands = listOf(
                    "f(x) = 2*x * cos(x^2)",
                    "A = Integral(f, 0, 1.2533)",
                    "SetColor(A, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 3.0,
                yMin = -3.0,
                yMax = 3.0,
                showAreaUnderCurve = true,
                integralA = 0.0,
                integralB = 1.2533,
                didacticExplanation = "Seja u = x², logo du = 2x dx. A integral converte-se em ∫ cos(u) du = sen(u) + C = sen(x²) + C. A substituição transforma um produto de funções em uma integral imediata simples.",
                mathInsight = "A substituição por variável u identifica o padrão f(g(x))g'(x) dx para reverter a Regra da Cadeia.",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 0.6, 0.5, "Substituição u = x²", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("2*x * cos(x^2)", "2*x * exp(x^2)", "x / (x^2 + 1)", "3*x^2 * (x^3 + 1)^2")
            )

            // Aula 24: Integração por Partes (Regra do Produto ao Contrário)
            "calc_l24" -> GeoGebraGraphConfig(
                title = "Integração por Partes: ∫ x · e⁻ˣ dx",
                functionExpression = "x * exp(-x)",
                commands = listOf(
                    "f(x) = x * exp(-x)",
                    "A = Integral(f, 0, 3)",
                    "SetColor(A, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 5.0,
                yMin = -0.3,
                yMax = 0.6,
                showAreaUnderCurve = true,
                integralA = 0.0,
                integralB = 3.0,
                didacticExplanation = "Pela fórmula da integração por partes: ∫ u dv = uv - ∫ v du. Escolhendo u = x (logo du = dx) e dv = e⁻ˣ dx (logo v = -e⁻ˣ): ∫ x e⁻ˣ dx = -x e⁻ˣ - ∫ (-e⁻ˣ) dx = -(x + 1)e⁻ˣ + C.",
                mathInsight = "Regra LIATE guia a escolha de u para derivar e simplificar a expressão restante.",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 1.0, 0.2, "Integração por Partes", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("x * exp(-x)", "x * sin(x)", "x * cos(x)", "ln(x)")
            )

            // Aula 25: Frações Parciais e Substituição Trigonométrica
            "calc_l25" -> GeoGebraGraphConfig(
                title = "Decomposição em Frações Parciais: ∫ 1/(x² + 1) dx",
                functionExpression = "1 / (x^2 + 1)",
                commands = listOf(
                    "f(x) = 1 / (x^2 + 1)",
                    "A = Integral(f, 0, 1)",
                    "SetColor(A, 56, 189, 248)"
                ),
                xMin = -3.0,
                xMax = 3.0,
                yMin = -0.2,
                yMax = 1.3,
                showAreaUnderCurve = true,
                integralA = 0.0,
                integralB = 1.0,
                didacticExplanation = "A função racional f(x) = 1/(x² + 1) integra diretamente na função inversa da tangente: ∫₀¹ [1/(x² + 1)] dx = [arctan(x)]₀¹ = arctan(1) - arctan(0) = π/4 ≈ 0.785.",
                mathInsight = "Frações racionais com denominadores irredutíveis conectam a álgebra à geometria dos ângulos via arctan.",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 0.5, 0.5, "Área = π/4 ≈ 0.785", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("1 / (x^2 + 1)", "1 / (x^2 - 1)", "2 / (x^2 - 4)", "x / (x^2 + 1)")
            )

            // Aula 26: A Integral Definida e o Teorema Fundamental (TFC)
            "calc_l26" -> GeoGebraGraphConfig(
                title = "Integral Definida, Riemann e TFC: ∫₀² x² dx",
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
                showRiemannSum = true,
                riemannPartitions = 8,
                integralA = 0.0,
                integralB = 2.0,
                didacticExplanation = "Pelo Teorema Fundamental do Cálculo (TFC), a soma infinita dos retângulos infinitesimais de Riemann colapsa na avaliação da antiderivada nos limites de integração: ∫₀² x² dx = [x³/3]₀² = 8/3 ≈ 2.667 u.a.",
                mathInsight = "TFC: d/dx ∫_a^x f(t) dt = f(x) e ∫_a^b f(x) dx = F(b) - F(a). A conexão definitiva entre derivação e integração!",
                analysisPoints = listOf(
                    GeoGebraPoint("I", 1.0, 1.0, "Área Exata = 8/3 ≈ 2.67", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("x^2", "x^3", "sin(x)", "sqrt(x)")
            )

            else -> null
        }
    }
}
