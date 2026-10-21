package com.example.data.exercise

import com.example.data.math.GeoGebraGraphConfig
import com.example.data.math.GeoGebraPoint

/**
 * Banco especializado de exercícios analíticos e geométricos integrados ao GeoGebra.
 * Cada exercício possui um gráfico interativo renderizado dinamicamente pelo GeoGebra API,
 * abordando Geometria Plana, Geometria Analítica, Funções, Formas Geométricas,
 * Retas Tangentes, Assíntotas e Áreas de Integração.
 */
object GeoGebraGeometryExercises {

    val exercises: List<UniversalExercise> = listOf(
        // =========================================================================
        // 1. GEOMETRIA ANALÍTICA & ÁLGEBRA: VÉRTICE E ALTURA MÁXIMA DA PARÁBOLA
        // =========================================================================
        UniversalExercise(
            id = "geo_01",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria Analítica & Gráficos",
            difficulty = "Mediano",
            source = "ENEM / Cinemática & Geometria Analítica",
            problemStatement = """O lançamento oblíquo de um projétil é modelado no plano cartesiano pela parábola f(x) = -0,5x² + 3x, onde x é a distância horizontal percorrida (em metros) e f(x) é a altitude correspondente (em metros). Observe a representação gráfica no GeoGebra acima e determine a altitude máxima atingida pelo projétil e a distância horizontal total do alcance.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Trajetória Parabólica do Projétil: f(x) = -0.5x² + 3x",
                functionExpression = "-0.5*x^2 + 3*x",
                commands = listOf(
                    "f(x) = -0.5*x^2 + 3*x",
                    "V = (3, 4.5)",
                    "SetPointSize(V, 6)",
                    "SetColor(V, 234, 179, 8)",
                    "R1 = (0, 0)",
                    "R2 = (6, 0)",
                    "SetColor(R1, 56, 189, 248)",
                    "SetColor(R2, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 8.0,
                yMin = -1.0,
                yMax = 6.0,
                didacticExplanation = "O vértice da parábola com a < 0 representa o ponto de altura máxima. As raízes x = 0 e x = 6 delimitam o ponto de disparo e o ponto de impacto no solo (alcance total).",
                mathInsight = "Vértice: x_v = -b/(2a) = -3/(2 * -0,5) = 3 m => y_v = f(3) = 4,5 m. Alcance: x = 6 m.",
                analysisPoints = listOf(
                    GeoGebraPoint("V", 3.0, 4.5, "Vértice Máximo (3; 4,5m)", isOpenCircle = false, colorHex = "#EAB308"),
                    GeoGebraPoint("R1", 0.0, 0.0, "Origem (0, 0)", isOpenCircle = false, colorHex = "#38BDF8"),
                    GeoGebraPoint("R2", 6.0, 0.0, "Alcance (6m, 0)", isOpenCircle = false, colorHex = "#38BDF8")
                ),
                presetFunctions = listOf("-0.5*x^2 + 3*x", "-x^2 + 4*x", "-0.2*x^2 + 2*x")
            ),
            options = listOf(
                "Altitude máxima de 3,0 m e alcance de 4,5 m",
                "Altitude máxima de 4,5 m e alcance de 6,0 m",
                "Altitude máxima de 5,0 m e alcance de 6,0 m",
                "Altitude máxima de 4,5 m e alcance de 3,0 m",
                "Altitude máxima de 6,0 m e alcance de 9,0 m"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A abscissa do vértice de uma parábola f(x) = ax² + bx + c é calculada por x_v = -b / (2a).",
                "Passo 2: Com a = -0,5 e b = 3: x_v = -3 / (2 × -0,5) = -3 / -1 = 3 metros.",
                "Passo 3: A altura máxima é a ordenada correspondente: y_v = f(3) = -0,5(3)² + 3(3) = -0,5(9) + 9 = -4,5 + 9 = 4,5 metros.",
                "Passo 4: O alcance horizontal ocorre quando o projétil atinge o solo (f(x) = 0): -0,5x² + 3x = 0 => x(-0,5x + 3) = 0 => x = 0 ou x = 6 metros.",
                "Conclusão: A altura máxima atingida é 4,5 metros e o alcance total é 6,0 metros."
            ),
            pedagogicalAlert = "Não confunda a abscissa do vértice (instante/distância em que ocorre a altura máxima, x = 3) com o valor da altura máxima em si (y = 4,5).",
            keyMethod = "Vértice: x_v = -b/(2a) = 3 => y_v = f(3) = 4,5; Raízes: x = 0 e x = 6.",
            xpReward = 35
        ),

        // =========================================================================
        // 2. GEOMETRIA PLANA NO PLANO CARTESIANO: ÁREA DE TRIÂNGULO POR VÉRTICES
        // =========================================================================
        UniversalExercise(
            id = "geo_02",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria: Áreas e Volumes",
            difficulty = "Fácil",
            source = "ENEM / Geometria Cartesiana",
            problemStatement = """Um terreno triangular no plano cartesiano tem seus vértices localizados nos pontos A(1, 1), B(7, 1) e C(4, 5), onde cada unidade de coordenada representa 10 metros na realidade. Utilizando o gráfico interativo do GeoGebra, calcule a área real desse terreno em metros quadrados.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Triângulo Cartesiano com Vértices A(1,1), B(7,1) e C(4,5)",
                functionExpression = "0*x",
                commands = listOf(
                    "A = (1, 1)",
                    "B = (7, 1)",
                    "C = (4, 5)",
                    "poly1 = Polygon(A, B, C)",
                    "SetColor(poly1, 16, 185, 129)",
                    "h = Segment((4, 1), C)",
                    "SetLineStyle(h, 2)",
                    "SetColor(h, 234, 179, 8)"
                ),
                xMin = 0.0,
                xMax = 8.0,
                yMin = 0.0,
                yMax = 6.0,
                didacticExplanation = "A base AB é horizontal ao longo de y = 1 com comprimento 7 - 1 = 6 unidades (60 m). A altura vertical do vértice C(4, 5) até a base é 5 - 1 = 4 unidades (40 m).",
                mathInsight = "Área cartesiana = (Base × Altura)/2 = (6 × 4)/2 = 12 unidades quadradas. Como 1 un = 10 m, 1 un² = 100 m² => Área real = 1.200 m².",
                analysisPoints = listOf(
                    GeoGebraPoint("A", 1.0, 1.0, "Vértice A(1, 1)", colorHex = "#10B981"),
                    GeoGebraPoint("B", 7.0, 1.0, "Vértice B(7, 1)", colorHex = "#10B981"),
                    GeoGebraPoint("C", 4.0, 5.0, "Vértice C(4, 5)", colorHex = "#10B981")
                )
            ),
            options = listOf(
                "600 m²",
                "1.200 m²",
                "2.400 m²",
                "1.800 m²",
                "3.600 m²"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Como os pontos A(1, 1) e B(7, 1) possuem a mesma ordenada y = 1, o segmento AB forma uma base horizontal perfeitamente alinhada.",
                "Passo 2: Comprimento da base no plano cartesiano: b = 7 - 1 = 6 unidades.",
                "Passo 3: A altura relativa a essa base é a distância vertical do ponto C(4, 5) até a reta y = 1: h = 5 - 1 = 4 unidades.",
                "Passo 4: Área no gráfico: A_grafico = (b × h) / 2 = (6 × 4) / 2 = 12 unidades quadradas.",
                "Passo 5: Como cada unidade linear equivale a 10 metros, cada unidade de área equivale a 10 m × 10 m = 100 m².",
                "Passo 6: Área real = 12 × 100 m² = 1.200 m²."
            ),
            pedagogicalAlert = "Ao converter unidades de área a partir de escalas lineares, lembre-se de elevar a razão ao quadrado: (10 m / 1 un)² = 100 m²/un².",
            keyMethod = "Base b = 6 un, Altura h = 4 un => Área cartesiana = 12 un² => 12 × 100 m² = 1.200 m².",
            xpReward = 30
        ),

        // =========================================================================
        // 3. GEOMETRIA ANALÍTICA: CIRCUNFERÊNCIA E RETA TANGENTE NO PLANO
        // =========================================================================
        UniversalExercise(
            id = "geo_03",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Mediano",
            source = "Geometria Analítica & Cálculo",
            problemStatement = """Considere a circunferência de equação x² + y² = 25 centrada na origem. No ponto P(3, 4) pertencente a essa curva, traça-se uma reta tangente t. Analise o gráfico construído no GeoGebra e determine o coeficiente angular da reta tangente t e a equação reduzida dessa reta.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Circunferência x² + y² = 25 e Reta Tangente no Ponto P(3, 4)",
                functionExpression = "sqrt(25 - x^2)",
                commands = listOf(
                    "c: x^2 + y^2 = 25",
                    "P = (3, 4)",
                    "SetPointSize(P, 6)",
                    "SetColor(P, 239, 68, 68)",
                    "r = Segment((0, 0), P)",
                    "SetColor(r, 59, 130, 246)",
                    "t = Tangent(P, c)",
                    "SetColor(t, 234, 179, 8)",
                    "SetLineThickness(t, 2)"
                ),
                xMin = -6.0,
                xMax = 8.0,
                yMin = -6.0,
                yMax = 8.0,
                didacticExplanation = "O raio que liga a origem ao ponto P(3, 4) tem inclinação m_raio = 4/3. Como a reta tangente é estritamente perpendicular ao raio no ponto de contato, m_tangente = -1/m_raio = -3/4.",
                mathInsight = "m_t = -3/4 = -0,75. Equação: y - 4 = -3/4(x - 3) => y = -3/4 x + 25/4 = -0,75x + 6,25.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 3.0, 4.0, "Ponto de Tangência (3, 4)", colorHex = "#EF4444"),
                    GeoGebraPoint("O", 0.0, 0.0, "Centro (0, 0)", colorHex = "#3B82F6")
                )
            ),
            options = listOf(
                "m = 4/3 e y = (4/3)x",
                "m = -3/4 e y = -0,75x + 6,25",
                "m = 3/4 e y = 0,75x + 1,75",
                "m = -4/3 e y = -1,33x + 8",
                "m = -1 e y = -x + 7"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: O raio que liga o centro (0, 0) ao ponto P(3, 4) tem coeficiente angular: m_raio = (4 - 0) / (3 - 0) = 4/3.",
                "Passo 2: Pela propriedade fundamental da geometria euclidiana, a reta tangente a uma circunferência é perpendicular ao raio no ponto de tangência.",
                "Passo 3: A condição de perpendicularidade é m_tangente × m_raio = -1 => m_tangente = -1 / (4/3) = -3/4 = -0,75.",
                "Passo 4: Aplicando a equação fundamental da reta que passa por (3, 4): y - y₀ = m(x - x₀) => y - 4 = -3/4(x - 3).",
                "Passo 5: Multiplicando e isolando y: y = -3/4 x + 9/4 + 4 = -3/4 x + 25/4 = -0,75x + 6,25."
            ),
            pedagogicalAlert = "Em cálculo diferencial, a mesma resposta pode ser obtida por derivação implícita: 2x + 2y y' = 0 => y' = -x/y => y'(3, 4) = -3/4.",
            keyMethod = "m_raio = 4/3 => m_tangente = -3/4 => y - 4 = -3/4(x - 3) => y = -0,75x + 6,25.",
            xpReward = 35
        ),

        // =========================================================================
        // 4. GEOMETRIA PLANA: TRAPÉZIO ISÓSCELES NO PLANO CARTESIANO
        // =========================================================================
        UniversalExercise(
            id = "geo_04",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria e Volumes",
            difficulty = "Mediano",
            source = "ENEM / Geometria Plana",
            problemStatement = """Um arquiteto projetou uma claraboia com o formato de um trapézio isósceles posicionado no plano cartesiano com os vértices A(0, 0), B(8, 0), C(6, 4) e D(2, 4). Com base no gráfico do GeoGebra acima, calcule o perímetro total e a área dessa claraboia (adote √20 ≈ 4,47).""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Claraboia Trapezoidal Isósceles: Vértices (0,0), (8,0), (6,4), (2,4)",
                functionExpression = "0*x",
                commands = listOf(
                    "A = (0, 0)",
                    "B = (8, 0)",
                    "C = (6, 4)",
                    "D = (2, 4)",
                    "trap = Polygon(A, B, C, D)",
                    "SetColor(trap, 168, 85, 247)",
                    "h1 = Segment((2, 0), D)",
                    "SetLineStyle(h1, 2)",
                    "SetColor(h1, 234, 179, 8)",
                    "h2 = Segment((6, 0), C)",
                    "SetLineStyle(h2, 2)",
                    "SetColor(h2, 234, 179, 8)"
                ),
                xMin = -1.0,
                xMax = 9.0,
                yMin = -1.0,
                yMax = 5.5,
                didacticExplanation = "A base maior AB mede 8. A base menor CD mede 6 - 2 = 4. A altura h é 4. Cada lado oblíquo AD e BC é a hipotenusa de um triângulo retângulo de catetos 2 e 4: L = √(2² + 4²) = √20 ≈ 4,47.",
                mathInsight = "Área = (B + b)*h / 2 = (8 + 4)*4 / 2 = 24 un². Perímetro = 8 + 4 + 2*√20 ≈ 12 + 8,94 = 20,94.",
                analysisPoints = listOf(
                    GeoGebraPoint("A", 0.0, 0.0, "A(0, 0)", colorHex = "#A855F7"),
                    GeoGebraPoint("B", 8.0, 0.0, "B(8, 0)", colorHex = "#A855F7"),
                    GeoGebraPoint("C", 6.0, 4.0, "C(6, 4)", colorHex = "#A855F7"),
                    GeoGebraPoint("D", 2.0, 4.0, "D(2, 4)", colorHex = "#A855F7")
                )
            ),
            options = listOf(
                "Área = 24 un² e Perímetro ≈ 20,94 un",
                "Área = 32 un² e Perímetro ≈ 24,00 un",
                "Área = 20 un² e Perímetro ≈ 18,50 un",
                "Área = 24 un² e Perímetro ≈ 16,00 un",
                "Área = 28 un² e Perímetro ≈ 22,40 un"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Identificar o comprimento da base maior (segmento AB sobre y = 0): B = 8 - 0 = 8 unidades.",
                "Passo 2: Identificar o comprimento da base menor (segmento CD sobre y = 4): b = 6 - 2 = 4 unidades.",
                "Passo 3: A altura vertical h entre as retas paralelas y = 0 e y = 4 é h = 4 unidades.",
                "Passo 4: Cálculo da área do trapézio: A = [(B + b) × h] / 2 = [(8 + 4) × 4] / 2 = [12 × 4] / 2 = 24 unidades quadradas.",
                "Passo 5: O lado oblíquo AD tem projeção horizontal dx = 2 - 0 = 2 e vertical dy = 4 - 0 = 4. Pelo Teorema de Pitágoras: AD = √(2² + 4²) = √(4 + 16) = √20 ≈ 4,47.",
                "Passo 6: Como o trapézio é isósceles, BC = AD = √20 ≈ 4,47.",
                "Passo 7: Perímetro = Base maior + Base menor + 2 × (Lado oblíquo) = 8 + 4 + 2(4,47) = 12 + 8,94 = 20,94 unidades."
            ),
            pedagogicalAlert = "Não esqueça de somar os quatro lados para obter o perímetro (duas bases e dois lados oblíquos iguais).",
            keyMethod = "Área = (8 + 4) × 4 / 2 = 24 un²; Lado = √20 ≈ 4,47 => Perímetro = 8 + 4 + 2(4,47) ≈ 20,94 un.",
            xpReward = 30
        ),

        // =========================================================================
        // 5. CÁLCULO I & GEOMETRIA: ÁREA SOB A PARÁBOLA (INTEGRAL DEFINIDA)
        // =========================================================================
        UniversalExercise(
            id = "geo_05",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Mediano",
            source = "Teorema Fundamental do Cálculo & GeoGebra",
            problemStatement = """Observe no GeoGebra acima a região delimitada pela curva f(x) = 4 - x², pelo eixo das abscissas (y = 0) e pelas retas verticais x = 0 e x = 2. Utilizando o Teorema Fundamental do Cálculo, determine a área exata dessa região sombreada.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Área sob a Parábola f(x) = 4 - x² no Intervalo [0, 2]",
                functionExpression = "4 - x^2",
                commands = listOf(
                    "f(x) = 4 - x^2",
                    "A = (0, 0)",
                    "B = (2, 0)",
                    "C = (2, 0)",
                    "D = (0, 4)",
                    "reg = Integral(f, 0, 2)",
                    "SetColor(reg, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 3.5,
                yMin = -1.0,
                yMax = 5.0,
                didacticExplanation = "A área exata sob a curva é dada pela integral definida ∫₀² (4 - x²) dx. A primitiva é F(x) = 4x - x³/3. Pelo TFC: F(2) - F(0) = (8 - 8/3) - 0 = 16/3 ≈ 5,33 unidades de área.",
                mathInsight = "Área = ∫₀² (4 - x²) dx = [4x - x³/3]₀² = (8 - 8/3) = 16/3 un².",
                showAreaUnderCurve = true,
                integralA = 0.0,
                integralB = 2.0,
                analysisPoints = listOf(
                    GeoGebraPoint("V", 0.0, 4.0, "Vértice (0, 4)", colorHex = "#EAB308"),
                    GeoGebraPoint("R", 2.0, 0.0, "Raiz (2, 0)", colorHex = "#38BDF8")
                )
            ),
            options = listOf(
                "16/3 un² (≈ 5,33 un²)",
                "8 un²",
                "14/3 un² (≈ 4,67 un²)",
                "16 un²",
                "20/3 un² (≈ 6,67 un²)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Pelo Teorema Fundamental do Cálculo, a área da região sob f(x) ≥ 0 é A = ∫₀² (4 - x²) dx.",
                "Passo 2: Encontrar a função primitiva F(x): ∫ 4 dx = 4x; ∫ x² dx = x³/3 => F(x) = 4x - x³/3.",
                "Passo 3: Avaliar nos limites superior (b = 2) e inferior (a = 0):",
                "Passo 4: F(2) = 4(2) - (2)³/3 = 8 - 8/3 = (24 - 8)/3 = 16/3.",
                "Passo 5: F(0) = 4(0) - (0)³/3 = 0.",
                "Passo 6: Área = F(2) - F(0) = 16/3 unidades quadradas (aproximadamente 5,33 un²)."
            ),
            pedagogicalAlert = "Observe como a área sob a parábola de Arquimedes corresponde a exatamente 2/3 da área do retângulo circunscrito de base 2 e altura 4 (2 × 4 = 8; 2/3 × 8 = 16/3).",
            keyMethod = "∫₀² (4 - x²) dx = [4x - x³/3]₀² = 8 - 8/3 = 16/3 un².",
            xpReward = 35
        ),

        // =========================================================================
        // 6. GEOMETRIA & TRIGONOMETRIA NO GEOGEBRA: FUNÇÃO SENOIDAL E AMPLITUDE
        // =========================================================================
        UniversalExercise(
            id = "geo_06",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Domínio & Gráficos",
            difficulty = "Fácil",
            source = "ENEM / Ondulatória & Funções Trigonométricas",
            problemStatement = """Uma onda mecânica em propagação é modelada pela função f(x) = 3 sen(2x). Com base no gráfico trigonométrico interativo fornecido no GeoGebra, determine a amplitude da onda, seu período T e o valor máximo atingido pela função.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Onda Harmônica f(x) = 3 sen(2x)",
                functionExpression = "3*sin(2*x)",
                commands = listOf(
                    "f(x) = 3*sin(2*x)",
                    "P1 = (pi/4, 3)",
                    "SetPointSize(P1, 6)",
                    "SetColor(P1, 234, 179, 8)",
                    "P2 = (3*pi/4, -3)",
                    "SetPointSize(P2, 6)",
                    "SetColor(P2, 239, 68, 68)",
                    "P3 = (pi, 0)",
                    "SetColor(P3, 56, 189, 248)"
                ),
                xMin = -1.0,
                xMax = 4.0,
                yMin = -4.0,
                yMax = 4.0,
                didacticExplanation = "Para f(x) = A sen(B x), a amplitude é |A| = 3 e o período fundamental é T = 2π / B = 2π / 2 = π. O valor máximo da crista é +3.",
                mathInsight = "Amplitude = 3; Período T = 2π/2 = π rad; Máximo = 3.",
                analysisPoints = listOf(
                    GeoGebraPoint("P1", 0.785, 3.0, "Crista Máxima (π/4, 3)", colorHex = "#EAB308"),
                    GeoGebraPoint("P2", 2.356, -3.0, "Vale Mínimo (3π/4, -3)", colorHex = "#EF4444")
                )
            ),
            options = listOf(
                "Amplitude = 3, Período = π e Máximo = 3",
                "Amplitude = 6, Período = 2π e Máximo = 6",
                "Amplitude = 3, Período = 2π e Máximo = 3",
                "Amplitude = 2, Período = 3π e Máximo = 2",
                "Amplitude = 1,5, Período = π/2 e Máximo = 1,5"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Na expressão geral f(x) = A sen(Bx + C) + D, o coeficiente A define a amplitude (deslocamento vertical da crista a partir do eixo médio). Logo, Amplitude = |3| = 3.",
                "Passo 2: O coeficiente B multiplica a variável independente x e define a frequência angular. A fórmula do período de uma senóide é T = 2π / |B|.",
                "Passo 3: Com B = 2: T = 2π / 2 = π radianos.",
                "Passo 4: O valor máximo da função ocorre quando o seno atinge seu pico (+1): f_max = 3 × (+1) = 3."
            ),
            pedagogicalAlert = "Cuidado para não confundir o multiplicador da frequência (B = 2) com o período. O período T é inversamente proporcional a B (T = 2π/B).",
            keyMethod = "f(x) = 3 sen(2x) => Amplitude = 3; Período T = 2π/2 = π; f_max = 3.",
            xpReward = 30
        ),

        // =========================================================================
        // 7. CÁLCULO I: PONTO CRÍTICO, MÁXIMO E MÍNIMO LOCAL NO GEOGEBRA
        // =========================================================================
        UniversalExercise(
            id = "geo_07",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização",
            difficulty = "Difícil",
            source = "Cálculo Diferencial & GeoGebra",
            problemStatement = """Analise a função polinomial cúbica f(x) = x³ - 3x plotada no GeoGebra. Calcule as coordenadas exatas do ponto de máximo local e do ponto de mínimo local dessa curva utilizando o teste da primeira derivada.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Curva Cúbica f(x) = x³ - 3x e Pontos Críticos",
                functionExpression = "x^3 - 3*x",
                commands = listOf(
                    "f(x) = x^3 - 3*x",
                    "MaxPt = (-1, 2)",
                    "SetPointSize(MaxPt, 6)",
                    "SetColor(MaxPt, 16, 185, 129)",
                    "MinPt = (1, -2)",
                    "SetPointSize(MinPt, 6)",
                    "SetColor(MinPt, 239, 68, 68)",
                    "t1 = Line((-2, 2), (1, 2))",
                    "SetLineStyle(t1, 2)",
                    "SetColor(t1, 16, 185, 129)",
                    "t2 = Line((-1, -2), (2, -2))",
                    "SetLineStyle(t2, 2)",
                    "SetColor(t2, 239, 68, 68)"
                ),
                xMin = -3.0,
                xMax = 3.0,
                yMin = -4.0,
                yMax = 4.0,
                didacticExplanation = "A derivada f'(x) = 3x² - 3 anula-se em x = -1 e x = +1. Em x = -1 a inclinação passa de positiva para negativa (Máximo Local em (-1, 2)). Em x = +1 passa de negativa para positiva (Mínimo Local em (1, -2)).",
                mathInsight = "f'(x) = 3(x² - 1) = 0 => x = ±1. f(-1) = 2 (Máx); f(1) = -2 (Mín).",
                analysisPoints = listOf(
                    GeoGebraPoint("MaxPt", -1.0, 2.0, "Máximo Local (-1, 2)", colorHex = "#10B981"),
                    GeoGebraPoint("MinPt", 1.0, -2.0, "Mínimo Local (1, -2)", colorHex = "#EF4444")
                )
            ),
            options = listOf(
                "Máximo em (-1, 2) e Mínimo em (1, -2)",
                "Máximo em (1, 2) e Mínimo em (-1, -2)",
                "Máximo em (0, 0) e Mínimo em (2, 2)",
                "Máximo em (-2, 2) e Mínimo em (2, -2)",
                "Máximo em (-1, 4) e Mínimo em (1, -4)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Calcular a primeira derivada da função: f'(x) = d/dx(x³ - 3x) = 3x² - 3.",
                "Passo 2: Determinar os pontos críticos igualando a primeira derivada a zero: 3x² - 3 = 0 => 3x² = 3 => x² = 1 => x = -1 ou x = +1.",
                "Passo 3: Testar o sinal da derivada ao redor dos pontos críticos:",
                "• Para x < -1 (ex: x = -2): f'(-2) = 3(4) - 3 = 9 > 0 (função crescente).",
                "• Para -1 < x < 1 (ex: x = 0): f'(0) = -3 < 0 (função decrescente).",
                "Portanto, em x = -1 ocorre um MÁXIMO LOCAL: f(-1) = (-1)³ - 3(-1) = -1 + 3 = 2 => Ponto (-1, 2).",
                "• Para x > 1 (ex: x = 2): f'(2) = 3(4) - 3 = 9 > 0 (função crescente).",
                "Portanto, em x = 1 ocorre um MÍNIMO LOCAL: f(1) = (1)³ - 3(1) = 1 - 3 = -2 => Ponto (1, -2)."
            ),
            pedagogicalAlert = "As retas tangentes horizontais mostradas no GeoGebra têm coeficiente angular m = 0 em ambos os pontos críticos.",
            keyMethod = "f'(x) = 3x² - 3 = 0 => x = ±1 => Máximo (-1, 2) e Mínimo (1, -2).",
            xpReward = 40
        ),

        // =========================================================================
        // 8. GEOMETRIA ANALÍTICA: ELIPSE E DISTÂNCIA FOCAL NO GEOGEBRA
        // =========================================================================
        UniversalExercise(
            id = "geo_08",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria e Volumes",
            difficulty = "Difícil",
            source = "ENEM / Cônicas & Geometria Analítica",
            problemStatement = """Uma praça tem o formato elíptico modelado pela equação cartesiana x²/25 + y²/9 = 1, com medidas em dezenas de metros. Com o auxílio da visualização geométrica do GeoGebra, determine os comprimentos dos semieixos maior (a) e menor (b), e a distância focal 2c entre os dois focos dessa elipse.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Elipse Canônica x²/25 + y²/9 = 1 e Focos F₁(-4, 0) e F₂(4, 0)",
                functionExpression = "3*sqrt(1 - (x^2)/25)",
                commands = listOf(
                    "el: x^2/25 + y^2/9 = 1",
                    "F1 = (-4, 0)",
                    "SetPointSize(F1, 6)",
                    "SetColor(F1, 239, 68, 68)",
                    "F2 = (4, 0)",
                    "SetPointSize(F2, 6)",
                    "SetColor(F2, 239, 68, 68)",
                    "A1 = (-5, 0)",
                    "A2 = (5, 0)",
                    "B1 = (0, -3)",
                    "B2 = (0, 3)"
                ),
                xMin = -7.0,
                xMax = 7.0,
                yMin = -5.0,
                yMax = 5.0,
                didacticExplanation = "Na elipse horizontal com centro na origem, a² = 25 (a = 5) e b² = 9 (b = 3). Pela relação fundamental pitagórica a² = b² + c², c² = 25 - 9 = 16 => c = 4. A distância entre os focos é 2c = 8.",
                mathInsight = "a = 5, b = 3 => c = √(25 - 9) = 4 => Distância focal = 2c = 8.",
                analysisPoints = listOf(
                    GeoGebraPoint("F1", -4.0, 0.0, "Foco F₁(-4, 0)", colorHex = "#EF4444"),
                    GeoGebraPoint("F2", 4.0, 0.0, "Foco F₂(4, 0)", colorHex = "#EF4444"),
                    GeoGebraPoint("A2", 5.0, 0.0, "Vértice Maior (5, 0)", colorHex = "#38BDF8")
                )
            ),
            options = listOf(
                "a = 5, b = 3 e distância focal = 8",
                "a = 25, b = 9 e distância focal = 16",
                "a = 5, b = 3 e distância focal = 4",
                "a = 10, b = 6 e distância focal = 8",
                "a = 4, b = 3 e distância focal = 5"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A equação reduzida da elipse com centro na origem é x²/a² + y²/b² = 1.",
                "Passo 2: Comparando os denominadores: a² = 25 => a = 5 (semieixo maior horizontal); b² = 9 => b = 3 (semieixo menor vertical).",
                "Passo 3: A relação fundamental pitagórica das elipses estabelece que a² = b² + c², onde c é a semidistância focal.",
                "Passo 4: Isolando c²: c² = a² - b² = 25 - 9 = 16 => c = √16 = 4.",
                "Passo 5: A distância focal total entre os dois focos F₁(-4, 0) e F₂(4, 0) é 2c = 2 × 4 = 8 unidades (80 metros na escala do problema)."
            ),
            pedagogicalAlert = "Lembre-se que 'c' é a semidistância focal (da origem até um foco). A distância focal total é a distância entre os dois focos, ou seja, 2c.",
            keyMethod = "a = 5, b = 3 => c² = 25 - 9 = 16 => c = 4 => Distância focal = 2c = 8.",
            xpReward = 40
        ),

        // =========================================================================
        // 9. CÁLCULO I: ASSÍNTOTAS VERTICAL E HORIZONTAL NO GEOGEBRA
        // =========================================================================
        UniversalExercise(
            id = "geo_09",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Mediano",
            source = "Limites no Infinito & GeoGebra",
            problemStatement = """Observe a função racional f(x) = (2x + 1) / (x - 3) plotada no GeoGebra. Identifique as equações das suas duas retas assíntotas (uma vertical e uma horizontal) com base nos conceitos de limites infinitos e limites no infinito.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Função Racional f(x) = (2x + 1)/(x - 3) e Assíntotas",
                functionExpression = "(2*x + 1)/(x - 3)",
                commands = listOf(
                    "f(x) = (2*x + 1)/(x - 3)",
                    "av = Line((3, -10), (3, 10))",
                    "SetLineStyle(av, 2)",
                    "SetColor(av, 239, 68, 68)",
                    "ah = Line((-10, 2), (10, 2))",
                    "SetLineStyle(ah, 2)",
                    "SetColor(ah, 234, 179, 8)"
                ),
                xMin = -6.0,
                xMax = 12.0,
                yMin = -8.0,
                yMax = 10.0,
                didacticExplanation = "O denominador anula-se em x = 3 com numerador diferente de zero (2(3)+1 = 7 ≠ 0), gerando a assíntota vertical x = 3. O limite quando x → ±∞ de (2x+1)/(x-3) é 2, gerando a assíntota horizontal y = 2.",
                mathInsight = "Assíntota Vertical: x = 3; Assíntota Horizontal: y = 2.",
                showAsymptotes = true,
                analysisPoints = listOf(
                    GeoGebraPoint("V", 3.0, 0.0, "Assíntota Vertical x = 3", colorHex = "#EF4444"),
                    GeoGebraPoint("H", 0.0, 2.0, "Assíntota Horizontal y = 2", colorHex = "#EAB308")
                )
            ),
            options = listOf(
                "Assíntota Vertical x = 3 e Assíntota Horizontal y = 2",
                "Assíntota Vertical x = -3 e Assíntota Horizontal y = 1",
                "Assíntota Vertical x = 2 e Assíntota Horizontal y = 3",
                "Assíntota Vertical x = 0 e Assíntota Horizontal y = 2",
                "Assíntota Vertical x = 3 e Assíntota Horizontal y = 0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Assíntota Vertical ocorre onde o denominador se anula e o numerador é não-nulo: x - 3 = 0 => x = 3.",
                "Verificando o numerador em x = 3: 2(3) + 1 = 7 ≠ 0. Portanto, lim_{x → 3} f(x) = ±∞, confirmando a reta vertical x = 3 como assíntota vertical.",
                "Passo 2: Assíntota Horizontal é obtida pelo limite no infinito: lim_{x → ±∞} (2x + 1) / (x - 3).",
                "Passo 3: Dividindo numerador e denominador pela maior potência de x (grau 1):",
                "lim_{x → ±∞} (2 + 1/x) / (1 - 3/x) = (2 + 0) / (1 - 0) = 2.",
                "Conclusão: A assíntota horizontal é a reta y = 2."
            ),
            pedagogicalAlert = "Para funções racionais onde o grau do numerador é igual ao grau do denominador, a assíntota horizontal é simplesmente a razão entre os coeficientes líderes: 2 / 1 = 2.",
            keyMethod = "Denominador = 0 => x = 3 (Assíntota Vertical); Razão dos coeficientes líderes: 2/1 => y = 2 (Assíntota Horizontal).",
            xpReward = 35
        ),

        // =========================================================================
        // 10. GEOMETRIA ANALÍTICA: DISTÂNCIA DE PONTO A RETA NO PLANO
        // =========================================================================
        UniversalExercise(
            id = "geo_10",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria Analítica & Gráficos",
            difficulty = "Mediano",
            source = "ENEM / Geometria Cartesiana & Otimização",
            problemStatement = """Um duto de gás subterrâneo segue a reta de equação 3x + 4y - 12 = 0. Uma estação de controle deve ser instalada no ponto P(6, 6). Utilizando o gráfico analítico do GeoGebra, calcule a distância perpendicular mínima que ligará a estação diretamente ao duto.""",
            geoGebraConfig = GeoGebraGraphConfig(
                title = "Distância Mínima do Ponto P(6, 6) à Reta 3x + 4y - 12 = 0",
                functionExpression = "(12 - 3*x)/4",
                commands = listOf(
                    "r: 3*x + 4*y - 12 = 0",
                    "P = (6, 6)",
                    "SetPointSize(P, 6)",
                    "SetColor(P, 239, 68, 68)",
                    "Q = (3.6, 2.8)",
                    "SetPointSize(Q, 5)",
                    "SetColor(Q, 56, 189, 248)",
                    "d = Segment(P, Q)",
                    "SetLineThickness(d, 3)",
                    "SetColor(d, 234, 179, 8)"
                ),
                xMin = -2.0,
                xMax = 10.0,
                yMin = -2.0,
                yMax = 9.0,
                didacticExplanation = "A menor distância entre um ponto e uma reta é o segmento ortogonal (perpendicular). Aplicando a fórmula d = |Ax₀ + By₀ + C| / √(A² + B²): d = |3(6) + 4(6) - 12| / √(9 + 16) = |18 + 24 - 12| / 5 = 30 / 5 = 6 unidades.",
                mathInsight = "d = |3(6) + 4(6) - 12| / √(3² + 4²) = 30 / 5 = 6 unidades.",
                analysisPoints = listOf(
                    GeoGebraPoint("P", 6.0, 6.0, "Estação P(6, 6)", colorHex = "#EF4444"),
                    GeoGebraPoint("Q", 3.6, 2.8, "Ponto Ortogonal mais Próximo (3.6, 2.8)", colorHex = "#38BDF8")
                )
            ),
            options = listOf(
                "4,5 unidades",
                "5,0 unidades",
                "6,0 unidades",
                "7,2 unidades",
                "8,0 unidades"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: A fórmula da distância de um ponto P(x₀, y₀) a uma reta r: Ax + By + C = 0 é:",
                "d = |A x₀ + B y₀ + C| / √(A² + B²).",
                "Passo 2: Identificar os coeficientes da reta: A = 3, B = 4, C = -12.",
                "Passo 3: Identificar as coordenadas do ponto: x₀ = 6, y₀ = 6.",
                "Passo 4: Calcular o numerador em módulo: |3(6) + 4(6) - 12| = |18 + 24 - 12| = |30| = 30.",
                "Passo 5: Calcular o denominador: √(3² + 4²) = √(9 + 16) = √25 = 5.",
                "Passo 6: Divisão final: d = 30 / 5 = 6,0 unidades."
            ),
            pedagogicalAlert = "O segmento que realiza a menor distância forma sempre um ângulo reto (90°) com a reta dada.",
            keyMethod = "d = |3(6) + 4(6) - 12| / √(3² + 4²) = 30 / 5 = 6,0.",
            xpReward = 35
        )
    )
}
