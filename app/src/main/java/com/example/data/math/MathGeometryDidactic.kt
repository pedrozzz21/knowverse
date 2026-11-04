package com.example.data.math

import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor Didático Especializado de Geometria Plana e Espacial para o ENEM.
 * Cobre Módulo 4 (enem_m4_a1 até enem_m4_a9) e Módulo 5 (enem_m5_a1 até enem_m5_a9).
 */
object MathGeometryDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            // ==========================================
            // MÓDULO 4: GEOMETRIA PLANA
            // ==========================================
            "enem_m4_a1" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Fundamentos da Geometria Plana e Ângulos no ENEM
                    
                    A Geometria Plana no ENEM é predominantemente contextualizada em projetos arquitetônicos, cortes de tecidos, azulejos e rotas de navegação.
                    
                    ### 1. Elementos Primitivos e Ângulos
                    • **Retas Paralelas Cortadas por Transversal:** Formam pares de ângulos correspondentes, alternos internos/externos (que são congruentes) e colaterais internos/externos (que são suplementares, somando 180°).
                    • **Bissetriz:** Semirreta interna que divide um ângulo em duas medidas rigorosamente congruentes.
                    • **Classificação de Ângulos:** Agudo (< 90°), Reto (= 90°), Obtuso (> 90° e < 180°) e Raso (= 180°).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Ângulos Suplementares", "α + β = 180°", "Dois ângulos cuja soma forma meia-volta"),
                    FormulaOrSyntaxItem("Ângulos Complementares", "α + β = 90°", "Dois ângulos que formam um canto reto")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Paralelismo em Ruas",
                        codeOrFormula = "Ruas paralelas cortadas por uma avenida transversal transversal.",
                        explanation = "Se o ângulo agudo em um cruzamento mede 65°, o ângulo obtuso colateral interno mede 180° - 65° = 115°."
                    )
                ),
                goldTip = "No ENEM, procure sempre o 'Z' de retas paralelas: os ângulos alternos internos nas dobras do Z são sempre iguais!"
            )

            "enem_m4_a2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Polígonos, Diagonais e Ângulos Internos
                    
                    Polígonos regulares são a base para ladrilhamento de pisos e cálculo de armações metálicas.
                    
                    ### 1. Soma dos Ângulos
                    • **Soma dos Ângulos Internos:** Divide-se o polígono de \( n \) lados em \( (n - 2) \) triângulos a partir de um vértice: \( S_i = (n - 2) \cdot 180^\circ \).
                    • **Soma dos Ângulos Externos:** Vale invariavelmente \( 360^\circ \) para qualquer polígono convexo, independentemente do número de lados!
                    • **Ladrilhamento do Plano:** Apenas triângulos equiláteros (60°), quadrados (90°) e hexágonos regulares (120°) ladrilham o plano sem deixar frestas nem sobreposições, pois seus ângulos internos dividem 360°.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Soma dos Ângulos Internos", "S_i = (n - 2) \\cdot 180°", "Soma de todos os ângulos internos de um polígono convexo de n lados"),
                    FormulaOrSyntaxItem("Número de Diagonais", "d = \\frac{n \\cdot (n - 3)}{2}", "Total de segmentos ligando vértices não consecutivos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Ângulo Interno do Hexágono Regular",
                        codeOrFormula = """S_i = (6 - 2) \\cdot 180° = 720° -> a_i = 720° / 6 = 120°""",
                        explanation = "Como cada ângulo mede 120°, 3 hexágonos juntos somam exatamente 360°, explicando por que as abelhas constroem favos hexagonais econômicos."
                    )
                ),
                goldTip = "Para achar o ângulo interno de um polígono regular, calcule primeiro o externo: a_e = 360° / n. Depois, faça a_i = 180° - a_e. É muito mais rápido!"
            )

            "enem_m4_a3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Triângulos, Pontos Notáveis e Desigualdade Triangular
                    
                    O triângulo é a estrutura rígida mais indeformável da natureza, largamente explorada na engenharia civil.
                    
                    ### 1. Condição de Existência (Desigualdade Triangular)
                    Para que três segmentos de reta \( a, b, c \) formem um triângulo, o maior lado deve ser estritamente menor que a soma dos outros dois:
                    \( |b - c| < a < b + c \).
                    
                    ### 2. Pontos Notáveis
                    • **Baricentro (G):** Ponto de encontro das medianas. É o centro de gravidade do triângulo e divide cada mediana na razão 2:1.
                    • **Ortocentro (H):** Ponto de encontro das retas suportes das alturas.
                    • **Incentro (I):** Encontro das bissetrizes internas (centro da circunferência inscrita).
                    • **Circuncentro (O):** Encontro das mediatrizes dos lados (centro da circunferência circunscrita).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Condição de Existência", "a < b + c", "O lado maior deve ser menor que a soma dos outros dois lados"),
                    FormulaOrSyntaxItem("Propriedade do Baricentro", "AG = 2 \\cdot GM", "O baricentro divide a mediana em partes proporcionais a 2 e 1")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Segmentos de Medidas 5, 8 e 14",
                        codeOrFormula = "14 < 5 + 8? 14 < 13 é FALSO.",
                        explanation = "Não é possível construir um triângulo com lados 5, 8 e 14 cm, pois as pontas de 5 e 8 não se tocam quando abertas."
                    )
                ),
                goldTip = "Em todo triângulo retângulo, o circuncentro fica exatamente no ponto médio da hipotenusa, e a mediana relativa à hipotenusa mede metade dela!"
            )

            "enem_m4_a4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Semelhança de Triângulos e Razão entre Áreas
                    
                    Dois triângulos são semelhantes se possuem seus três ângulos correspondentes congruentes e lados homólogos proporcionais.
                    
                    ### 1. Razão Linear e Razão Quadrática
                    Se a razão de semelhança linear entre comprimentos é \( k = \frac{a_1}{a_2} \), então:
                    • A razão entre perímetros é \( k \).
                    • A razão entre alturas correspondentes é \( k \).
                    • **A razão entre áreas é \( k^2 \)**.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Razão entre Lados", "\\frac{a}{a'} = \\frac{b}{b'} = \\frac{c}{c'} = k", "Constante de proporcionalidade linear"),
                    FormulaOrSyntaxItem("Razão entre Áreas", "\\frac{A_1}{A_2} = k^2", "A área cresce com o quadrado da escala linear")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Sombra de um Prédio e de uma Estaca",
                        codeOrFormula = "H / h = S / s -> H / 1,5 = 30 / 2 -> H = 22,5 m",
                        explanation = "Pelos raios solares paralelos, os triângulos formados pelas alturas e sombras são semelhantes pelo caso Ângulo-Ângulo (AA)."
                    )
                ),
                goldTip = "Se uma maquete está na escala 1:100, sua área no chão real será 100² = 10.000 vezes maior, e seu volume 100³ = 1.000.000 de vezes maior!"
            )

            "enem_m4_a5" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Teorema de Tales e Divisão Proporcional
                    
                    O Teorema de Tales estabelece que um feixe de retas paralelas determina sobre duas retas transversais segmentos proporcionais.
                    
                    ### Aplicação Prática no ENEM
                    Muito cobrado no cálculo de frentes de lotes urbanos em terrenos triangulares ou trapézios cortados por ruas paralelas.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Teorema de Tales", "\\frac{AB}{BC} = \\frac{DE}{EF}", "Segmentos correspondentes em transversais interceptadas por paralelas"),
                    FormulaOrSyntaxItem("Teorema da Bissetriz Interna", "\\frac{c}{b} = \\frac{x}{y}", "A bissetriz divide o lado oposto em segmentos proporcionais aos lados adjacentes")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Divisão de Lotes Urbanos",
                        codeOrFormula = "x / 20 = 30 / 15 -> x = 40 m",
                        explanation = "A frente do lote X mede 40 metros pela proporção direta entre os segmentos paralelos das divisas."
                    )
                ),
                goldTip = "Identifique sempre as paralelas e monte as frações no mesmo sentido da transversal para não inverter os dados!"
            )

            "enem_m4_a6" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Teorema de Pitágoras e Relações Métricas no Triângulo Retângulo
                    
                    O teorema mais famoso da matemática é onipresente no ENEM em diagonais, cabos de sustentação e distâncias de trajetos.
                    
                    ### 1. Relações Métricas Fundamentais
                    Seja \( a \) a hipotenusa, \( b, c \) os catetos, \( h \) a altura relativa à hipotenusa e \( m, n \) as projeções dos catetos:
                    • \( a^2 = b^2 + c^2 \) (Pitágoras)
                    • \( b^2 = a \cdot m \quad e \quad c^2 = a \cdot n \)
                    • \( h^2 = m \cdot n \)
                    • \( b \cdot c = a \cdot h \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Teorema de Pitágoras", "a^2 = b^2 + c^2", "O quadrado da hipotenusa é a soma dos quadrados dos catetos"),
                    FormulaOrSyntaxItem("Produto dos Catetos", "b \\cdot c = a \\cdot h", "Área duplicada expressa de duas formas")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Ternos Pitagóricos Comuns",
                        codeOrFormula = "(3, 4, 5), (5, 12, 13), (8, 15, 17)",
                        explanation = "Se um triângulo tem catetos 6 e 8, ele é o dobro do terno (3, 4, 5), logo a hipotenusa vale imediatamente 10."
                    )
                ),
                goldTip = "Memorize os ternos (3, 4, 5) e (5, 12, 13). Mais de 80% das questões do ENEM usam múltiplos desses triângulos para agilizar as contas!"
            )

            "enem_m4_a7" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Perímetros de Figuras Planas e Cercamentos
                    
                    Perímetro é a medida do contorno linear de uma figura bidimensional.
                    
                    ### Aplicações no ENEM
                    • Quantidade de mourões e voltas de arame farpado para cercar pastos.
                    • Molduras de quadros, rodapés e pistas de atletismo compostas por trechos retos e semicírculos.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Comprimento da Circunferência", "C = 2 \\cdot \\pi \\cdot r = \\pi \\cdot d", "Perímetro da borda circular de raio r"),
                    FormulaOrSyntaxItem("Perímetro de Polígono Regular", "P = n \\cdot L", "Número de lados vezes a medida do lado")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Cercamento com 4 Fios de Arame",
                        codeOrFormula = """P = 2 \\cdot (50 + 30) = 160 m -> Total = 160 \\cdot 4 = 640 m""",
                        explanation = "Para cercar um terreno retangular de 50m por 30m com 4 voltas de arame, são necessários 640 metros."
                    )
                ),
                goldTip = "Em pistas de atletismo com duas retas e duas curvas semicirculares, as duas curvas juntas formam exatamente uma circunferência inteira C = 2πr!"
            )

            "enem_m4_a8" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Cálculo de Áreas Planas (Triângulos, Quadriláteros e Polígonos)
                    
                    O cálculo de áreas é o segundo assunto de geometria mais cobrado em toda a história do ENEM.
                    
                    ### 1. Fórmulas de Área Essenciais
                    • **Retângulo e Paralelogramo:** \( A = b \cdot h \)
                    • **Triângulo Geral:** \( A = \frac{b \cdot h}{2} \)
                    • **Triângulo Equilátero:** \( A = \frac{L^2 \sqrt{3}}{4} \)
                    • **Trapézio:** \( A = \frac{(B + b) \cdot h}{2} \)
                    • **Losango:** \( A = \frac{D \cdot d}{2} \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Área do Triângulo Equilátero", "A = \\frac{L^2 \\sqrt{3}}{4}", "Fórmula direta em função do lado L"),
                    FormulaOrSyntaxItem("Área do Trapézio", "A = \\frac{(B + b) \\cdot h}{2}", "Média das bases multiplicada pela altura")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Área de Terreno em Forma de Trapézio",
                        codeOrFormula = "B = 25m, b = 15m, h = 10m -> A = (25 + 15) * 10 / 2 = 200 m²",
                        explanation = "A soma das bases resulta em 40, multiplicada por 10 resulta em 400, que dividida por 2 fornece 200 m²."
                    )
                ),
                goldTip = "Se a figura for irregular, divida-a em triângulos e retângulos menores e some as áreas individuais!"
            )

            "enem_m4_a9" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Círculo, Setor Circular e Coroa Circular
                    
                    Círculo é a região interna delimitada pela circunferência.
                    
                    ### 1. Áreas Circulares
                    • **Área do Círculo:** \( A = \pi \cdot r^2 \)
                    • **Setor Circular de ângulo \( \alpha \):** \( A_{setor} = \frac{\alpha}{360^\circ} \cdot \pi \cdot r^2 \)
                    • **Coroa Circular (entre dois raios R e r):** \( A_{coroa} = \pi \cdot (R^2 - r^2) \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Área do Círculo", "A = \\pi \\cdot r^2", "Superfície total de raio r"),
                    FormulaOrSyntaxItem("Área da Coroa Circular", "A = \\pi \\cdot (R^2 - r^2)", "Diferença entre o círculo maior e o menor")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Alcance de Irrigador Giratório de 60°",
                        codeOrFormula = "Setor de 60° com raio 6m: A = (60/360) * π * 6² = (1/6) * 36π = 6π m²",
                        explanation = "Como 60° é 1/6 de 360°, a área regada é exatamente 1/6 da área do círculo completo."
                    )
                ),
                goldTip = "O ENEM geralmente pede para adotar π = 3 ou π = 3,14 no enunciado. Siga rigorosamente a instrução para não errar a alternativa!"
            )

            // ==========================================
            // MÓDULO 5: GEOMETRIA ESPACIAL
            // ==========================================
            "enem_m5_a1" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Visão Espacial e Projeções Ortogonais no ENEM
                    
                    Projeção ortogonal é a 'sombra' perpendicular que um objeto tridimensional projeta sobre um plano de referência iluminado por raios paralelos de luz.
                    
                    ### Dicas para o ENEM
                    • A projeção de um segmento paralelo ao plano mantém o mesmo comprimento.
                    • A projeção de um segmento perpendicular ao plano é reduzida a um único **ponto**.
                    • Vistas ortogonais principais: Frontal (elevação), Superior (planta baixa) e Lateral.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Projeção Ortogonal", "Ponto P(x, y, z) -> P'(x, y, 0)", "Anulação da coordenada ortogonal ao plano de projeção")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Sombra de uma Hélice Giratória no Chão",
                        codeOrFormula = "Hélice circular vertical girando sob o sol do meio-dia.",
                        explanation = "A projeção ortogonal no solo plano horizontal é simplesmente um segmento de reta de comprimento igual ao diâmetro da hélice."
                    )
                ),
                goldTip = "Imagine-se olhando para o objeto infinitamente de cima (planta) ou infinitamente de frente. Não há perspectiva nem fuga nos traços!"
            )

            "enem_m5_a2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Planificação de Sólidos Geométricos e Embalagens
                    
                    Planificação consiste em 'abrir' a superfície externa de um sólido 3D em uma superfície bidimensional contínua sem rasgar nem sobrepor faces.
                    
                    ### Planificações Frequentes no ENEM
                    • **Cubo:** Possui 11 planificações possíveis de 6 quadrados.
                    • **Cilindro Reto:** Um retângulo de base \( 2\pi r \) e altura \( h \), acompanhado de dois círculos de raio \( r \).
                    • **Cone Reto:** Um setor circular de raio \( g \) (geratriz) com comprimento de arco \( 2\pi r \), mais o círculo da base.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Ângulo do Setor do Cone Planificado", "\\theta = \\frac{r}{g} \\cdot 360°", "Ângulo central da superfície lateral do cone aberto")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Caixa de Papelão Aberta",
                        codeOrFormula = "Planificação de paralelepípedo com abas de colagem.",
                        explanation = "A área total de papelão necessária corresponde à soma das áreas dos 6 retângulos que compõem as faces mais as abas."
                    )
                ),
                goldTip = "Em dados de jogos cúbicos planificados, faces opostas nunca podem compartilhar uma aresta em comum!"
            )

            "enem_m5_a3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Poliedros Convexos e Teorema de Euler
                    
                    Poliedros são sólidos geométricos limitados exclusivamente por polígonos planos chamados faces.
                    
                    ### 1. Relação de Euler
                    Para todo poliedro convexo fechado com \( V \) vértices, \( A \) arestas e \( F \) faces:
                    \( V - A + F = 2 \) (ou "Vamos Fazer Amor a Dois": \( V + F = A + 2 \)).
                    
                    ### 2. Contagem de Arestas por Faces
                    Como cada aresta é compartilhada por exatamente duas faces:
                    \( 2A = 3F_3 + 4F_4 + 5F_5 + \dots \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Relação de Euler", "V + F = A + 2", "Vértices + Faces = Arestas + 2"),
                    FormulaOrSyntaxItem("Contagem Dupla de Arestas", "2A = \\sum n \\cdot F_n", "Cada aresta pertence a exatamente duas faces vizinhas")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Bola de Futebol (Icosaedro Truncado)",
                        codeOrFormula = "12 pentágonos e 20 hexágonos -> 2A = 12*5 + 20*6 = 180 -> A = 90. V = 90 + 2 - 32 = 60.",
                        explanation = "A bola possui 32 faces, 90 arestas e 60 vértices costurados."
                    )
                ),
                goldTip = "Memorize a mnemônica 'V + F = A + 2' (Você e a Família = Amigos + 2) para nunca esquecer os sinais!"
            )

            "enem_m5_a4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Prismas: Área Superficial e Volume
                    
                    Prismas são sólidos com duas bases poligonais paralelas e congruentes, e faces laterais paralelogrâmicas (ou retangulares nos prismas retos).
                    
                    ### 1. Fórmulas Fundamentais
                    • **Volume de Qualquer Prisma:** \( V = A_{base} \cdot h \)
                    • **Paralelepípedo Retângulo (\( a, b, c \)):**
                      - Volume: \( V = a \cdot b \cdot c \)
                      - Diagonal interna: \( D = \sqrt{a^2 + b^2 + c^2} \)
                      - Área total: \( A_t = 2(ab + ac + bc) \)
                    • **Cubo de aresta \( a \):** \( V = a^3 \) e diagonal \( D = a\sqrt{3} \).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Volume do Prisma", "V = A_{base} \\cdot h", "Área da base multiplicada pela altura"),
                    FormulaOrSyntaxItem("Diagonal do Paralelepípedo", "D = \\sqrt{a^2 + b^2 + c^2}", "Comprimento da maior vareta que cabe dentro da caixa")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Capacidade de uma Caixa-d'Água",
                        codeOrFormula = "2m x 1,5m x 1m -> V = 3 m³ = 3.000 litros",
                        explanation = "Como 1 m³ corresponde a exatamente 1.000 litros, o reservatório comporta 3.000 L de água."
                    )
                ),
                goldTip = "Conversão obrigatória no ENEM: 1 m³ = 1.000 litros | 1 dm³ = 1 litro | 1 cm³ = 1 mL!"
            )

            "enem_m5_a5" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Cilindros: Superfície e Volume no Cotidiano
                    
                    O cilindro reto (ou cilindro de revolução) é gerado pela rotação de um retângulo em torno de um de seus lados.
                    
                    ### Fórmulas Essenciais
                    • **Área da Base:** \( A_b = \pi \cdot r^2 \)
                    • **Área Lateral:** \( A_l = 2\pi r \cdot h \)
                    • **Área Total:** \( A_t = 2\pi r(r + h) \)
                    • **Volume:** \( V = \pi \cdot r^2 \cdot h \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Volume do Cilindro", "V = \\pi \\cdot r^2 \\cdot h", "Área da base circular multiplicada pela altura"),
                    FormulaOrSyntaxItem("Área Lateral do Cilindro", "A_l = 2\\pi \\cdot r \\cdot h", "Retângulo de base igual ao comprimento da circunferência 2πr")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Lata de Refrigerante",
                        codeOrFormula = "Raio 3cm, Altura 12cm, π = 3,14 -> V = 3,14 * 3² * 12 = 339,12 cm³ ≈ 340 mL",
                        explanation = "O volume em cm³ equivale diretamente à capacidade em mililitros (mL)."
                    )
                ),
                goldTip = "Cuidado com o DIÂMETRO dado no enunciado: sempre divida por 2 antes de elevar o raio ao quadrado!"
            )

            "enem_m5_a6" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Pirâmides: Elementos, Apótema e Volume
                    
                    Uma pirâmide possui uma base poligonal qualquer e um vértice superior comum fora do plano da base.
                    
                    ### 1. Volume de Qualquer Pirâmide
                    O volume de uma pirâmide é rigorosamente um terço do volume do prisma de mesma base e mesma altura:
                    \( V = \frac{A_{base} \cdot h}{3} \).
                    
                    ### 2. Relação no Triângulo Retângulo Interno
                    Em uma pirâmide regular:
                    \( g^2 = h^2 + m^2 \), onde \( g \) é o apótema da pirâmide (altura da face triangular) e \( m \) é o apótema da base.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Volume da Pirâmide", "V = \\frac{A_{base} \\cdot h}{3}", "Um terço do produto da base pela altura"),
                    FormulaOrSyntaxItem("Relação Pitagórica da Pirâmide", "g^2 = h^2 + m^2", "Apótema da pirâmide, altura e apótema da base")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Pirâmide de Base Quadrada",
                        codeOrFormula = "Lado da base 6m, Altura 4m -> A_base = 36m² -> V = 36 * 4 / 3 = 48 m³",
                        explanation = "Calcula-se a área do quadrado da base e aplica-se o fator 1/3."
                    )
                ),
                goldTip = "Não confunda a altura h da pirâmide com o apótema g da face lateral. O apótema é a hipotenusa do triângulo retângulo interno com h!"
            )

            "enem_m5_a7" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Cones: Geratriz, Área Lateral e Volume
                    
                    O cone reto (ou cone de revolução) é o análogo circular da pirâmide, gerado pela rotação de um triângulo retângulo em torno de um cateto.
                    
                    ### Fórmulas Centrais
                    • **Relação Pitagórica:** \( g^2 = h^2 + r^2 \) (geratriz \( g \), altura \( h \), raio \( r \))
                    • **Área Lateral:** \( A_l = \pi \cdot r \cdot g \)
                    • **Volume do Cone:** \( V = \frac{\pi \cdot r^2 \cdot h}{3} \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Volume do Cone", "V = \\frac{\\pi \\cdot r^2 \\cdot h}{3}", "Um terço do volume do cilindro correspondente"),
                    FormulaOrSyntaxItem("Relação da Geratriz", "g^2 = h^2 + r^2", "Hipotenusa do triângulo com raio e altura")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Casquinha de Sorvete Cônica",
                        codeOrFormula = "Raio 4cm, altura 3cm -> g = 5cm -> V = π * 16 * 3 / 3 = 16π cm³",
                        explanation = "A geratriz vale 5cm pelo terno pitagórico (3, 4, 5) e o volume equivale a 16π cm³."
                    )
                ),
                goldTip = "Casquinhas de sorvete, taças de champanhe e funis no ENEM quase sempre pedem volume de cone com fator 1/3!"
            )

            "enem_m5_a8" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Esferas: Área da Superfície e Volume
                    
                    A esfera é o sólido perfeito formado pelo conjunto de pontos do espaço a uma distância menor ou igual a \( R \) do centro.
                    
                    ### Fórmulas Arquimedianas
                    • **Área da Superfície Esférica:** \( A = 4 \cdot \pi \cdot R^2 \) (equivale à área de 4 círculos máximos!)
                    • **Volume da Esfera:** \( V = \frac{4}{3} \cdot \pi \cdot R^3 \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Volume da Esfera", "V = \\frac{4}{3} \\cdot \\pi \\cdot R^3", "Quatro terços de pi r ao cubo"),
                    FormulaOrSyntaxItem("Área da Superfície Esférica", "A = 4\\pi \\cdot R^2", "Superfície externa de quatro círculos máximos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Bolinho de Chuva Esférico",
                        codeOrFormula = "Raio duplicado: R -> 2R -> V' = (4/3)π(2R)³ = 8V",
                        explanation = "Se o raio de uma esfera dobra, seu volume é multiplicado por 2³ = 8 vezes!"
                    )
                ),
                goldTip = "Preste muita atenção ao expoente: área usa R² e volume usa R³! Se o raio triplica, o volume multiplica por 27!"
            )

            "enem_m5_a9" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Troncos de Pirâmide e Cone (Baldes e Vasos)
                    
                    Cortando-se uma pirâmide ou cone por um plano paralelo à base, obtém-se um sólido menor semelhante no topo e um **tronco** na parte inferior.
                    
                    ### 1. Razão de Semelhança em Sólidos
                    Se a razão entre as alturas é \( k = \frac{h_{menor}}{H_{total}} \):
                    • Razão entre áreas das bases: \( \frac{A_b}{A_B} = k^2 \)
                    • Razão entre volumes: \( \frac{V_{menor}}{V_{total}} = k^3 \)
                    
                    ### 2. Volume do Tronco de Cone por Subtração
                    \( V_{tronco} = V_{total} - V_{topo} \) (muito mais rápido e intuitivo que a fórmula direta).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Razão Volumétrica de Semelhança", "\\frac{V_1}{V_2} = k^3", "Volumes variam com o cubo da razão linear de semelhança"),
                    FormulaOrSyntaxItem("Volume do Tronco de Cone", "V = \\frac{\\pi \\cdot h}{3} \\cdot (R^2 + R\\cdot r + r^2)", "Fórmula analítica direta para troncos regulares")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Vaso Cônico Padrão ENEM",
                        codeOrFormula = "Vaso cortado na metade da altura: V_topo = (1/2)³ * V_total = 1/8 V_total -> V_tronco = 7/8 V_total",
                        explanation = "O tronco retém 7/8 do volume total enquanto o pequeno cone superior descartado continha apenas 1/8."
                    )
                ),
                goldTip = "Em problemas de troncos no ENEM (baldes, vasos e formas de bolo), prefira completar o cone/pirâmide e calcular por subtração V_total - V_topo!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Geometria ENEM\nConceitos de formas, medidas e projeções.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Desenhe sempre um esboço das figuras com as medidas indicadas no enunciado."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "enem_m4_a1" -> listOf(
                MathExercise(
                    id = "ex_m4_a1_1",
                    title = "ENEM: Ângulos em Estruturas Metálicas",
                    problemStatement = "Uma treliça metálica é construída com duas vigas paralelas cortadas por uma barra transversal de reforço. Se o menor ângulo formado entre a transversal e uma das vigas mede 38°, o ângulo obtuso colateral interno formado na outra viga mede:",
                    options = listOf("38°", "52°", "128°", "142°", "152°"),
                    correctIndex = 3,
                    hint = "Ângulos colaterais internos entre retas paralelas são suplementares (somam 180°).",
                    stepByStepSolution = "Alternativa correta: D (142°).\n\nComo as vigas são paralelas e os ângulos colaterais internos somam 180°:\nθ = 180° - 38° = 142°."
                )
            )

            "enem_m4_a2" -> listOf(
                MathExercise(
                    id = "ex_m4_a2_1",
                    title = "ENEM: Ladrilhamento com Polígonos Regulares",
                    problemStatement = "Um arquiteto deseja revestir um piso plano utilizando exclusivamente ladrilhos cerâmicos que sejam polígonos regulares congruentes, sem que haja sobreposições ou frestas entre as peças. Dentre as opções a seguir, o único polígono regular que permite esse ladrilhamento monoédrico é o:",
                    options = listOf("Pentágono regular", "Octógono regular", "Hexágono regular", "Decágono regular", "Heptágono regular"),
                    correctIndex = 2,
                    hint = "Para que os vértices se juntem sem frestas, o ângulo interno do polígono regular deve ser divisor exato de 360°.",
                    stepByStepSolution = "Alternativa correta: C (Hexágono regular).\n\nO ângulo interno do hexágono regular vale (6 - 2)*180° / 6 = 720° / 6 = 120°. Como 360° / 120° = 3 (número inteiro exato), três hexágonos se encontram perfeitamente em torno de cada vértice."
                )
            )

            "enem_m4_a3" -> listOf(
                MathExercise(
                    id = "ex_m4_a3_1",
                    title = "ENEM: Desigualdade Triangular e Varetas",
                    problemStatement = "Um marceneiro dispõe de varetas de madeira com comprimentos de 7 cm, 11 cm e 19 cm. Ao tentar unir as extremidades dessas três varetas para formar uma moldura triangular rígida, ele conclui corretamente que:",
                    options = listOf(
                        "é possível construir um triângulo obtusângulo.",
                        "não é possível construir um triângulo, pois 19 >= 7 + 11.",
                        "é possível construir um triângulo retângulo pelo teorema de Pitágoras.",
                        "é possível construir um triângulo acutângulo.",
                        "é possível construir um triângulo isósceles."
                    ),
                    correctIndex = 1,
                    hint = "Pela desigualdade triangular, o maior lado deve ser estritamente menor que a soma dos outros dois lados.",
                    stepByStepSolution = "Alternativa correta: B.\n\nA condição de existência exige que o maior lado (19 cm) seja menor que a soma dos outros dois (7 + 11 = 18 cm). Como 19 > 18, as varetas menores não conseguem se encontrar nas extremidades, tornando impossível fechar o triângulo."
                )
            )

            "enem_m4_a4" -> listOf(
                MathExercise(
                    id = "ex_m4_a4_1",
                    title = "ENEM: Razão entre Áreas de Terrenos Semelhantes",
                    problemStatement = "Dois terrenos triangulares semelhantes possuem perímetros de 60 metros e 180 metros, respectivamente. Se o terreno menor possui uma área de 150 m², a área do terreno maior é igual a:",
                    options = listOf("300 m²", "450 m²", "600 m²", "900 m²", "1.350 m²"),
                    correctIndex = 4,
                    hint = "A razão entre áreas de figuras semelhantes é o quadrado da razão entre seus comprimentos (perímetros).",
                    stepByStepSolution = "Alternativa correta: E (1.350 m²).\n\nRazão linear: k = 180 / 60 = 3.\nRazão entre áreas: k² = 3² = 9.\nÁrea maior = 150 * 9 = 1.350 m²."
                )
            )

            "enem_m4_a5" -> listOf(
                MathExercise(
                    id = "ex_m4_a5_1",
                    title = "ENEM: Teorema de Tales em Loteamento",
                    problemStatement = "Três lotes urbanos paralelos possuem frentes voltadas para a Rua A e fundos para a Rua B. As divisas laterais dos lotes são perpendiculares à Rua A e paralelas entre si. A frente do lote 1 mede 15 m na Rua A e 20 m na Rua B. Se a frente do lote 2 mede 30 m na Rua A, qual é a medida de sua frente voltada para a Rua B?",
                    options = listOf("35 m", "40 m", "45 m", "50 m", "60 m"),
                    correctIndex = 1,
                    hint = "Pelo Teorema de Tales: 15 / 20 = 30 / x.",
                    stepByStepSolution = "Alternativa correta: B (40 m).\n\n15 / 20 = 30 / x -> 15x = 600 -> x = 600 / 15 = 40 metros."
                )
            )

            "enem_m4_a6" -> listOf(
                MathExercise(
                    id = "ex_m4_a6_1",
                    title = "ENEM: Cabo de Sustentação de Antena (Pitágoras)",
                    problemStatement = "Uma antena transmissora de rádio vertical de 24 metros de altura deve ser fixada no solo plano por um cabo de aço retilíneo esticado, preso do topo da antena até um ponto de ancoragem no chão situado a 7 metros de distância da base da torre. O comprimento mínimo desse cabo de aço é de:",
                    options = listOf("25 metros", "26 metros", "28 metros", "30 metros", "31 metros"),
                    correctIndex = 0,
                    hint = "Aplique o Teorema de Pitágoras com catetos 24 m e 7 m.",
                    stepByStepSolution = "Alternativa correta: A (25 metros).\n\nC² = 24² + 7² = 576 + 49 = 625 -> C = √625 = 25 metros."
                )
            )

            "enem_m4_a7" -> listOf(
                MathExercise(
                    id = "ex_m4_a7_1",
                    title = "ENEM: Pista de Atletismo Circular",
                    problemStatement = "Uma praça circular tem raio de 50 metros. Um corredor treina completando exatamente 10 voltas ao redor do contorno externo dessa praça. Adotando π = 3,14, a distância total percorrida pelo atleta é de:",
                    options = listOf("1.570 m", "3.140 m", "4.710 m", "6.280 m", "7.850 m"),
                    correctIndex = 1,
                    hint = "O comprimento de 1 volta é C = 2 * π * r. Multiplique por 10.",
                    stepByStepSolution = "Alternativa correta: B (3.140 m).\n\nC = 2 * 3,14 * 50 = 314 metros por volta.\nEm 10 voltas: 314 * 10 = 3.140 metros."
                )
            )

            "enem_m4_a8" -> listOf(
                MathExercise(
                    id = "ex_m4_a8_1",
                    title = "ENEM: Custo de Grama em Terreno Trapezoidal",
                    problemStatement = "Um jardim tem a forma de um trapézio de bases paralelas medindo 18 metros e 12 metros, com altura de 10 metros. Sabendo que o metro quadrado de grama custa R$ 15,00, o valor total gasto para cobrir todo o jardim é:",
                    options = listOf("R$ 1.800,00", "R$ 2.250,00", "R$ 2.700,00", "R$ 3.000,00", "R$ 4.500,00"),
                    correctIndex = 1,
                    hint = "Calcule a área do trapézio: A = (B + b) * h / 2 e multiplique pelo preço do m².",
                    stepByStepSolution = "Alternativa correta: B (R$ 2.250,00).\n\nÁrea = (18 + 12) * 10 / 2 = 30 * 10 / 2 = 150 m².\nCusto = 150 * 15 = R$ 2.250,00."
                )
            )

            "enem_m4_a9" -> listOf(
                MathExercise(
                    id = "ex_m4_a9_1",
                    title = "ENEM: Área de Pista em Coroa Circular",
                    problemStatement = "Uma pista de caminhada foi construída em formato de coroa circular ao redor de um lago. O raio interno do lago é de 20 metros e o raio externo da pista é de 30 metros. Adotando π = 3, a área pavimentada dessa pista é igual a:",
                    options = listOf("750 m²", "1.200 m²", "1.500 m²", "1.800 m²", "2.700 m²"),
                    correctIndex = 2,
                    hint = "Área da coroa circular = π * (R² - r²).",
                    stepByStepSolution = "Alternativa correta: C (1.500 m²).\n\nA = 3 * (30² - 20²) = 3 * (900 - 400) = 3 * 500 = 1.500 m²."
                )
            )

            // Exercícios Módulo 5 (Espacial)
            "enem_m5_a1" -> listOf(
                MathExercise(
                    id = "ex_m5_a1_1",
                    title = "ENEM: Projeção Ortogonal de Trajetória Espiral",
                    problemStatement = "Uma formiga sobe ao longo do corrimão helicoidal de uma escada caracol perfeitamente vertical em direção ao andar superior. Sob a iluminação do sol no zênite (raios solares estritamente verticais), a sombra projetada pelo movimento da formiga no chão plano da sala descreve uma:",
                    options = listOf("Reta oblíqua", "Parábola", "Circunferência", "Elipse aberta", "Hipérbole"),
                    correctIndex = 2,
                    hint = "Uma hélice cilíndrica projetada verticalmente em seu eixo coincide com a base circular do cilindro.",
                    stepByStepSolution = "Alternativa correta: C (Circunferência).\n\nA hélice circular de raio constante R, ao ser projetada ortogonalmente sobre o plano horizontal perpendicular ao seu eixo, resulta em uma circunferência de mesmo raio R."
                )
            )

            "enem_m5_a2" -> listOf(
                MathExercise(
                    id = "ex_m5_a2_1",
                    title = "ENEM: Planificação da Superfície Lateral do Cilindro",
                    problemStatement = "Um rótulo retangular adesivo envolve exatamente toda a superfície lateral de uma lata cilíndrica de tinta de raio 5 cm e altura 20 cm, sem haver folgas ou sobreposições. Adotando π = 3,14, as dimensões desse rótulo retangular são de aproximadamente:",
                    options = listOf("15,7 cm x 20 cm", "31,4 cm x 20 cm", "25 cm x 20 cm", "62,8 cm x 20 cm", "78,5 cm x 20 cm"),
                    correctIndex = 1,
                    hint = "A base do retângulo planificado é o comprimento da circunferência 2 * π * r.",
                    stepByStepSolution = "Alternativa correta: B (31,4 cm x 20 cm).\n\nComprimento da base: C = 2 * 3,14 * 5 = 31,4 cm.\nAltura: h = 20 cm."
                )
            )

            "enem_m5_a3" -> listOf(
                MathExercise(
                    id = "ex_m5_a3_1",
                    title = "ENEM: Poliedro e Teorema de Euler",
                    problemStatement = "Um poliedro convexo possui 8 faces triangulares e 6 faces quadrangulares. O número de vértices desse poliedro é igual a:",
                    options = listOf("10", "12", "14", "16", "24"),
                    correctIndex = 1,
                    hint = "Primeiro calcule o total de arestas: 2A = 8*3 + 6*4. Depois use V + F = A + 2.",
                    stepByStepSolution = "Alternativa correta: B (12 vértices).\n\nTotal de faces: F = 8 + 6 = 14.\nArestas: 2A = 8*3 + 6*4 = 24 + 24 = 48 -> A = 24.\nEuler: V + 14 = 24 + 2 -> V = 26 - 14 = 12."
                )
            )

            "enem_m5_a4" -> listOf(
                MathExercise(
                    id = "ex_m5_a4_1",
                    title = "ENEM: Volume de Caixa d'Água Paralelepipédica",
                    problemStatement = "Um condomínio construiu uma caixa-d'água em formato de paralelepípedo reto retângulo com dimensões internas de 3 metros de comprimento, 2 metros de largura e 1,5 metro de profundidade. A capacidade máxima de armazenamento de água desse reservatório, em litros, é de:",
                    options = listOf("4.500 litros", "6.000 litros", "7.500 litros", "9.000 litros", "12.000 litros"),
                    correctIndex = 3,
                    hint = "Calcule o volume em metros cúbicos multiplicando comprimento x largura x altura e lembre-se que 1 m³ = 1.000 litros.",
                    stepByStepSolution = "Alternativa correta: D (9.000 litros).\n\nV = 3 * 2 * 1,5 = 9 m³.\nComo 1 m³ = 1.000 L -> 9 * 1.000 = 9.000 litros."
                )
            )

            "enem_m5_a5" -> listOf(
                MathExercise(
                    id = "ex_m5_a5_1",
                    title = "ENEM: Tanque de Combustível Cilíndrico",
                    problemStatement = "Um posto de combustíveis armazena etanol em um tanque subterrâneo com formato cilíndrico circular reto de raio interno 2 metros e comprimento 5 metros. Adotando π = 3, o volume máximo comportado por esse tanque é de:",
                    options = listOf("30 m³ (30.000 L)", "40 m³ (40.000 L)", "50 m³ (50.000 L)", "60 m³ (60.000 L)", "80 m³ (80.000 L)"),
                    correctIndex = 3,
                    hint = "V = π * r² * h.",
                    stepByStepSolution = "Alternativa correta: D (60 m³ ou 60.000 L).\n\nV = 3 * (2²) * 5 = 3 * 4 * 5 = 60 m³ = 60.000 litros."
                )
            )

            "enem_m5_a6" -> listOf(
                MathExercise(
                    id = "ex_m5_a6_1",
                    title = "ENEM: Volume de Pirâmide Regular",
                    problemStatement = "Uma estufa de plantas tem o formato de uma pirâmide regular de base quadrada com lado de 6 metros e altura vertical de 4 metros. O volume de ar contido no interior dessa estufa é:",
                    options = listOf("24 m³", "36 m³", "48 m³", "72 m³", "144 m³"),
                    correctIndex = 2,
                    hint = "Volume da pirâmide = (Área da Base * altura) / 3.",
                    stepByStepSolution = "Alternativa correta: C (48 m³).\n\nÁrea da base = 6 * 6 = 36 m².\nVolume = (36 * 4) / 3 = 144 / 3 = 48 m³."
                )
            )

            "enem_m5_a7" -> listOf(
                MathExercise(
                    id = "ex_m5_a7_1",
                    title = "ENEM: Volume de Cone de Sinalização",
                    problemStatement = "Um cone de trânsito maciço tem raio da base de 10 cm e altura de 30 cm. Adotando π = 3,14, o volume de material plástico utilizado em sua fabricação é de:",
                    options = listOf("1.046 cm³", "1.570 cm³", "3.140 cm³", "4.710 cm³", "9.420 cm³"),
                    correctIndex = 2,
                    hint = "V = (π * r² * h) / 3.",
                    stepByStepSolution = "Alternativa correta: C (3.140 cm³).\n\nV = (3,14 * 10² * 30) / 3 = (3,14 * 100 * 30) / 3 = 9.420 / 3 = 3.140 cm³."
                )
            )

            "enem_m5_a8" -> listOf(
                MathExercise(
                    id = "ex_m5_a8_1",
                    title = "ENEM: Capacidade de Esfera de Armazenamento",
                    problemStatement = "Um reservatório esférico de gás liquefeito tem raio de 3 metros. Adotando π = 3, o volume interno desse reservatório esférico é de:",
                    options = listOf("36 m³", "54 m³", "108 m³", "144 m³", "216 m³"),
                    correctIndex = 2,
                    hint = "V = (4/3) * π * R³.",
                    stepByStepSolution = "Alternativa correta: C (108 m³).\n\nV = (4/3) * 3 * (3³) = 4 * 27 = 108 m³."
                )
            )

            "enem_m5_a9" -> listOf(
                MathExercise(
                    id = "ex_m5_a9_1",
                    title = "ENEM: Vaso com Formato de Tronco de Cone",
                    problemStatement = "Um vaso decorativo com formato de cone reto de altura total 40 cm e volume total de 800 mL foi seccionado por um plano horizontal a meia altura (20 cm da base). O volume do pequeno cone superior que foi removido equivale a:",
                    options = listOf("50 mL", "100 mL", "200 mL", "400 mL", "600 mL"),
                    correctIndex = 1,
                    hint = "A razão linear é k = 20 / 40 = 1/2. O volume varia com k³.",
                    stepByStepSolution = "Alternativa correta: B (100 mL).\n\nRazão de semelhança linear k = 20/40 = 1/2.\nRazão de volumes = k³ = (1/2)³ = 1/8.\nVolume do cone superior = 800 * (1/8) = 100 mL."
                )
            )

            else -> emptyList()
        }
    }
}
