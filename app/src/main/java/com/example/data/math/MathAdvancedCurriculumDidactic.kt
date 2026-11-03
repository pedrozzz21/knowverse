package com.example.data.math

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor didático avançado para a Trilha de Matemática do ENEM.
 * Cobre:
 * - Módulo 1 (Aulas 7 a 10): Grandezas, Regra de Três Composta, Escalas, Variações Sucessivas
 * - Módulo 2 (Aula 3): Sistemas de Amortização (Price e SAC)
 * - Módulo 10 (Aulas 1 a 3): Sequências, Progressão Aritmética e Progressão Geométrica
 * - Módulo 12 (Aulas 1 a 5): Trigonometria no Triângulo Retângulo, Ângulos Notáveis, Lei dos Senos/Cossenos, Ciclo Trigonométrico e Funções Seno/Cosseno
 * - Módulo 13 (Aulas 1 a 6): Geometria Analítica (Distância, Ponto Médio, Alinhamento, Equações da Reta, Paralelismo/Perpendicularismo, Circunferência)
 * - Módulo 14 (Aulas 1 a 3): Matrizes, Determinantes e Sistemas Lineares
 */
object MathAdvancedCurriculumDidactic {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        val entry = didacticRegistry[lessonId] ?: return null
        return CalculusDidacticBundle(
            theoryMarkdown = entry.theoryMarkdown,
            formulas = entry.formulas,
            solvedExamples = entry.solvedExamples,
            imageResId = null,
            goldTip = entry.goldTip
        )
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise>? {
        return exerciseRegistry[lessonId]
    }

    data class LessonDidacticEntry(
        val theoryMarkdown: String,
        val formulas: List<FormulaOrSyntaxItem>,
        val solvedExamples: List<LessonExample>,
        val goldTip: String
    )

    private val didacticRegistry: Map<String, LessonDidacticEntry> by lazy {
        mapOf(
            // ==========================================
            // MÓDULO 1 (Aulas 7 a 10)
            // ==========================================
            "enem_m1_a7" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Grandezas Diretamente e Inversamente Proporcionais
                    
                    No ENEM, o reconhecimento do tipo de proporcionalidade entre grandezas físicas ou econômicas é uma das habilidades mais testadas (H11 e H12).
                    
                    ### Grandezas Diretamente Proporcionais (GDP)
                    Duas grandezas \(x\) e \(y\) são diretamente proporcionais quando o quociente entre elas é constante:
                    \[ \frac{y}{x} = k \iff y = k \cdot x \]
                    Se uma grandeza dobra, a outra também dobra. Graficamente, é uma reta que passa pela origem \((0,0)\).
                    
                    ### Grandezas Inversamente Proporcionais (GIP)
                    Duas grandezas \(x\) e \(y\) são inversamente proporcionais quando o produto entre elas é constante:
                    \[ x \cdot y = k \iff y = \frac{k}{x} \]
                    Se uma grandeza dobra, a outra é reduzida pela metade. Graficamente, representa um ramo de hipérbole equilátera.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Constante de Proporcionalidade Direta",
                        syntax = "\\frac{y_1}{x_1} = \\frac{y_2}{x_2} = k",
                        description = "Razão constante entre duas grandezas proporcionais.",
                        parametersInfo = "Ex: distância percorrida e consumo de combustível a velocidade constante."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Constante de Proporcionalidade Inversa",
                        syntax = "x_1 \\cdot y_1 = x_2 \\cdot y_2 = k",
                        description = "Produto constante entre duas grandezas inversas.",
                        parametersInfo = "Ex: velocidade média e tempo de viagem para um percurso fixo."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Divisão em Partes Inversamente Proporcionais",
                        codeOrFormula = "k/2 + k/3 + k/6 = 1200 \\implies k = 1200",
                        explanation = "Um bônus de R$ 1.200,00 é dividido entre 3 operários de modo inversamente proporcional às faltas cometidas (2, 3 e 6 faltas). O operário com menos faltas (2) recebe k/2 = R$ 600,00."
                    )
                ),
                goldTip = "Ao ler o problema, faça o teste mental: se dobrar a primeira grandeza, a segunda dobra (direta) ou é dividida por 2 (inversa)?"
            ),

            "enem_m1_a8" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Regra de Três Composta
                    
                    A regra de três composta envolve três ou mais grandezas interconectadas por relações de proporcionalidade direta ou inversa.
                    
                    ### Método Prático de Resolução (Processo vs Produto)
                    1. Fixe a grandeza que contém a incógnita \(x\).
                    2. Compare individualmente cada uma das outras grandezas com a grandeza da incógnita, supondo todas as demais constantes.
                    3. Se for **direta**, mantenha a razão na mesma ordem. Se for **inversa**, inverta a razão.
                    4. Monte a equação: a razão da incógnita é igual ao produto de todas as demais razões ajustadas.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação Fundamental da Regra de Três Composta",
                        syntax = "\\frac{x_1}{x_2} = \\left(\\frac{a_1}{a_2}\\right)^{\\pm 1} \\cdot \\left(\\frac{b_1}{b_2}\\right)^{\\pm 1} \\dots",
                        description = "Produto das razões diretas ou invertidas igualado à razão da variável procurada.",
                        parametersInfo = "Expoente 1 se diretamente proporcional; -1 se inversamente proporcional."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Construção de Muro",
                        codeOrFormula = "\\frac{15}{x} = \\frac{10}{12} \\cdot \\frac{8}{6} \\cdot \\frac{200}{300} \\implies x = 20 \\text{ dias}",
                        explanation = "10 operários trabalhando 8 h/dia constroem 200 m de muro em 15 dias. Quantos dias levarão 12 operários trabalhando 6 h/dia para fazer 300 m?"
                    )
                ),
                goldTip = "O método do 'Processo x Produto' agrupa trabalhadores, tempo e rendimento no Processo, e a obra/serviço no Produto: (Processo 1)/(Produto 1) = (Processo 2)/(Produto 2)."
            ),

            "enem_m1_a9" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Escalas Cartográficas, Lineares, Superficiais e Volumétricas
                    
                    Escala é a razão constante entre a medida gráfica no desenho (d) e a medida real correspondente (D), expressas na **mesma unidade de comprimento**:
                    \[ E = \frac{d}{D} \]
                    
                    ### Relações Dimensionais de Escala (Pegadinha clássica do ENEM!)
                    • **Escala Linear (comprimento):** \( E = \frac{d}{D} \)
                    • **Escala de Área (superfície):** \( E_{\text{área}} = E^2 = \frac{a_{\text{desenho}}}{A_{\text{real}}} \)
                    • **Escala de Volume (capacidade):** \( E_{\text{volume}} = E^3 = \frac{v_{\text{desenho}}}{V_{\text{real}}} \)
                    
                    Se a maquete de um prédio é feita na escala \( 1:100 \), a área do piso na maquete é \( 1:10.000 \) da área real, e o volume é \( 1:1.000.000 \) do volume real!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Escala Linear",
                        syntax = "E = \\frac{d}{D}",
                        description = "Razão entre distância no mapa e distância real na mesma unidade.",
                        parametersInfo = "1 cm no mapa 1:50.000 equivale a 50.000 cm = 500 m = 0,5 km."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Escala de Superfície",
                        syntax = "E^2 = \\frac{A_{\\text{mapa}}}{A_{\\text{real}}}",
                        description = "A razão de áreas é o quadrado da escala linear.",
                        parametersInfo = "Fundamental para calcular áreas territoriais em mapas temáticos no ENEM."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Área em Planta Baixa",
                        codeOrFormula = "A_{\\text{real}} = 24 \\text{ cm}^2 \\times (50)^2 = 60.000 \\text{ cm}^2 = 6 \\text{ m}^2",
                        explanation = "Uma sala retangular representada em escala 1:50 ocupa 24 cm² na planta. A área real da sala é 24 * 2500 = 60.000 cm² = 6 m²."
                    )
                ),
                goldTip = "Sempre converta todas as medidas para a mesma unidade (geralmente centímetros) antes de aplicar a escala!"
            ),

            "enem_m1_a10" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Porcentagem e Fatores de Variação Sucessiva
                    
                    Trabalhar com fatores multiplicativos agiliza a resolução de provas do ENEM e evita cálculos intermediários redundantes.
                    
                    ### Fator de Aumento e Fator de Desconto
                    • Aumento de \( p\% \): multiplica-se por \( F = 1 + \frac{p}{100} \)
                    • Desconto de \( p\% \): multiplica-se por \( F = 1 - \frac{p}{100} \)
                    
                    ### Variações Acumuladas
                    Quando um produto sofre múltiplos aumentos ou descontos sucessivos, os fatores são **multiplicados** entre si (e nunca somados!):
                    \[ F_{\text{total}} = F_1 \cdot F_2 \cdot F_3 \dots \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fator Multiplicativo de Aumento",
                        syntax = "F_A = 1 + i",
                        description = "Multiplicador para acréscimo percentual.",
                        parametersInfo = "Aumento de 15% -> F = 1,15."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fator Multiplicativo de Desconto",
                        syntax = "F_D = 1 - i",
                        description = "Multiplicador para redução percentual.",
                        parametersInfo = "Desconto de 20% -> F = 0,80."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Desconto após Aumento",
                        codeOrFormula = "F_{\\text{final}} = 1,20 \\cdot 0,80 = 0,96 \\implies \\text{Redução de } 4\\%",
                        explanation = "Um produto que sobe 20% e depois cai 20% não volta ao preço original; ele fica 4% mais barato do que o valor inicial."
                    )
                ),
                goldTip = "Aumentar 10% e depois reduzir 10% nunca dá zero! O resultado é 1,10 * 0,90 = 0,99, ou seja, perda de 1%."
            ),

            // ==========================================
            // MÓDULO 2 (Aula 3: Amortização)
            // ==========================================
            "enem_m2_a3" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Sistemas de Amortização: Tabela Price e SAC
                    
                    Financiamentos imobiliários e bancários no Brasil utilizam predominantemente dois sistemas de amortização:
                    
                    ### 1. Sistema de Amortização Constante (SAC)
                    • A cota de **amortização é fixa**: \( A = \frac{\text{Saldo Devedor Inicial}}{n} \).
                    • Os juros decrescem a cada período, pois incidem sobre o saldo devedor remanescente.
                    • As **prestações são decrescentes**: \( P_k = A + J_k \).
                    
                    ### 2. Tabela Price (Sistema Francês de Amortização)
                    • As **prestações são iguais e constantes** ao longo de todo o contrato.
                    • No início, a maior parte da prestação é juro; com o tempo, a parcela de amortização cresce e os juros diminuem.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Cálculo da Amortização no SAC",
                        syntax = "A = \\frac{SD_0}{n}",
                        description = "Amortização constante obtida pela divisão do capital pelo número de parcelas.",
                        parametersInfo = "Ex: empréstimo de 120.000 em 120 meses tem amortização mensal de R$ 1.000,00."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Composição da Prestação",
                        syntax = "P_k = A_k + J_k",
                        description = "Cada parcela paga quita uma fatia da dívida principal e os juros do mês.",
                        parametersInfo = "Juros do período incidem sempre sobre o saldo devedor do mês anterior."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: 2ª Prestação no SAC",
                        codeOrFormula = "A = 600, J_1 = 120, SD_1 = 11400, J_2 = 114, P_2 = 714",
                        explanation = "Dívida de R$ 12.000,00 em 20 parcelas com juros de 1% a.m. Amortização = 12000/20 = R$ 600. Após a 1ª parcela, o saldo devedor passa a ser 11.400. Juros do 2º mês = 1% de 11.400 = R$ 114. A 2ª prestação é R$ 600 + R$ 114 = R$ 714,00."
                    )
                ),
                goldTip = "Memorize a diferença-chave: SAC tem amortização constante e prestação decrescente; Price tem prestação constante e juros decrescentes."
            ),

            // ==========================================
            // MÓDULO 10: PROGRESSÕES (PA e PG)
            // ==========================================
            "enem_m10_a1" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Sequências Numéricas, Padrões e Regularidades
                    
                    O ENEM frequentemente propõe problemas de contagem de palitos, ladrilhos em mosaicos ou disposições de cadeiras que formam sequências lógicas.
                    
                    ### Lei de Formação de uma Sequência
                    Uma sequência pode ser definida por:
                    1. **Fórmula do termo geral:** \( a_n = f(n) \), que calcula o valor diretamente a partir de sua posição \(n\).
                    2. **Relação de recorrência:** que define cada termo a partir do anterior, como \( a_{n} = a_{n-1} + r \).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Termo Geral de Sequência Linear",
                        syntax = "a_n = c_1 \\cdot n + c_0",
                        description = "Padrão de crescimento constante por passo.",
                        parametersInfo = "Corresponde algebricamente a uma Progressão Aritmética."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Padrão de Figuras com Palitos",
                        codeOrFormula = "a_n = 3n + 1 \\implies a_{20} = 3(20) + 1 = 61 \\text{ palitos}",
                        explanation = "A figura 1 tem 4 palitos (quadrado). Cada nova figura contígua acrescenta 3 palitos. Para a figura de ordem 20, temos a_20 = 4 + (20 - 1)*3 = 61 palitos."
                    )
                ),
                goldTip = "Descubra quanto a figura cresce a cada passo: essa taxa de variação é o coeficiente de n na expressão do termo geral!"
            ),

            "enem_m10_a2" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Progressão Aritmética (PA): Termo Geral e Soma dos Termos
                    
                    Uma Progressão Aritmética é uma sequência em que cada termo, a partir do segundo, é obtido somando-se ao anterior uma constante \(r\) (razão da PA).
                    
                    ### Classificação da PA
                    • **Crescente:** \( r > 0 \)
                    • **Decrescente:** \( r < 0 \)
                    • **Constante:** \( r = 0 \)
                    
                    ### Termo Geral e Soma de Gauss
                    • Termo geral: \( a_n = a_1 + (n - 1) \cdot r \)
                    • Soma dos \(n\) primeiros termos: \( S_n = \frac{(a_1 + a_n) \cdot n}{2} \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Termo Geral da PA",
                        syntax = "a_n = a_1 + (n - 1) \\cdot r",
                        description = "Calcula qualquer termo a partir do primeiro e da razão.",
                        parametersInfo = "Pode ser adaptado para quaisquer posições: a_n = a_k + (n - k)*r."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Soma dos Termos da PA",
                        syntax = "S_n = \\frac{(a_1 + a_n) \\cdot n}{2}",
                        description = "Soma dos extremos multiplicada pela metade do número de termos.",
                        parametersInfo = "Descoberta pelo jovem Carl Friedrich Gauss."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Fileiras de um Teatro",
                        codeOrFormula = "a_1 = 15, r = 2, n = 20 \\implies a_{20} = 53 \\implies S_{20} = \\frac{(15 + 53) \\cdot 20}{2} = 680",
                        explanation = "Um anfiteatro possui 20 fileiras. A 1ª tem 15 cadeiras e cada fileira seguinte tem 2 a mais. O total de lugares é a soma da PA: S_20 = 680 cadeiras."
                    )
                ),
                goldTip = "Se o problema falar em 3 termos consecutivos de uma PA, chame-os de (x - r), x e (x + r). A soma deles elimina o r imediatamente!"
            ),

            "enem_m10_a3" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Progressão Geométrica (PG): Termo Geral e Soma Finita e Infinita
                    
                    Uma Progressão Geométrica é uma sequência em que cada termo é obtido multiplicando o anterior por uma razão fixa \(q\).
                    
                    ### Propriedades da PG
                    • Termo geral: \( a_n = a_1 \cdot q^{n-1} \)
                    • Soma dos \(n\) termos de uma PG finita (\(q \ne 1\)):
                    \[ S_n = \frac{a_1 (q^n - 1)}{q - 1} \]
                    • Soma de uma PG infinita convergente (\(|q| < 1\)):
                    \[ S_\infty = \frac{a_1}{1 - q} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Termo Geral da PG",
                        syntax = "a_n = a_1 \\cdot q^{n - 1}",
                        description = "Expressa o crescimento exponencial discreto da sequência.",
                        parametersInfo = "Usado em replicação celular, contaminação viral e frações sucessivas."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Soma da PG Infinita",
                        syntax = "S_\\infty = \\frac{a_1}{1 - q} \\quad (-1 < q < 1)",
                        description = "Limite da soma de infinitos termos decrescentes.",
                        parametersInfo = "Frequentemente cobrado em geratrizes de dízimas periódicas e fractais no ENEM."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Rebote de Bola",
                        codeOrFormula = "a_1 = 4, q = 1/2 \\implies S_\\infty = \\frac{4}{1 - 1/2} = 8 \\text{ metros}",
                        explanation = "Uma bola solta de 4 metros sobe sempre metade da altura do ressalto anterior. A soma de todas as alturas subidas nos ressaltos tende a 4 / (1 - 0,5) = 8 m."
                    )
                ),
                goldTip = "Se |q| >= 1, a PG diverge para infinito e a fórmula da soma infinita não tem significado físico ou matemático."
            ),

            // ==========================================
            // MÓDULO 12: TRIGONOMETRIA
            // ==========================================
            "enem_m12_a1" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Razões Trigonométricas no Triângulo Retângulo
                    
                    Para um ângulo agudo \(\alpha\) em um triângulo retângulo:
                    • **Seno (\(\sin \alpha\)):** \(\frac{\text{Cateto Oposto}}{\text{Hipotenusa}}\)
                    • **Cosseno (\(\cos \alpha\)):** \(\frac{\text{Cateto Adjacente}}{\text{Hipotenusa}}\)
                    • **Tangente (\(\tan \alpha\)):** \(\frac{\text{Cateto Oposto}}{\text{Cateto Adjacente}} = \frac{\sin \alpha}{\cos \alpha}\)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Seno, Cosseno e Tangente",
                        syntax = "\\sin\\alpha = \\frac{CO}{H}, \\quad \\cos\\alpha = \\frac{CA}{H}, \\quad \\tan\\alpha = \\frac{CO}{CA}",
                        description = "Definições fundamentais de trigonometria plana.",
                        parametersInfo = "Mnemônico popular: SOH - CAH - TOA."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Altura de uma Torre de Transmissão",
                        codeOrFormula = "\\tan(30^\\circ) = \\frac{h}{60} \\implies h = 60 \\cdot \\frac{\\sqrt{3}}{3} = 20\\sqrt{3} \\approx 34,64 \\text{ m}",
                        explanation = "A 60 m da base de uma torre, o topo é avistado sob ângulo de 30°. Usando a tangente, encontramos a altura vertical h."
                    )
                ),
                goldTip = "Em problemas com teodolitos, nunca esqueça de somar a altura dos olhos do observador ao resultado final da trigonometria!"
            ),

            "enem_m12_a2" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Ângulos Notáveis e Relação Fundamental
                    
                    Os ângulos de 30°, 45° e 60° aparecem constantemente em provas e devem ser memorizados:
                    
                    | Ângulo | Seno | Cosseno | Tangente |
                    |---|---|---|---|
                    | **30°** | 1/2 | \(\frac{\sqrt{3}}{2}\) | \(\frac{\sqrt{3}}{3}\) |
                    | **45°** | \(\frac{\sqrt{2}}{2}\) | \(\frac{\sqrt{2}}{2}\) | 1 |
                    | **60°** | \(\frac{\sqrt{3}}{2}\) | 1/2 | \(\sqrt{3}\) |
                    
                    ### Relação Fundamental da Trigonometria
                    Para qualquer ângulo real \(x\):
                    \[ \sin^2(x) + \cos^2(x) = 1 \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Identidade Fundamental",
                        syntax = "\\sin^2(x) + \\cos^2(x) = 1",
                        description = "Decorrente direta do Teorema de Pitágoras no ciclo unitário.",
                        parametersInfo = "Permite calcular o cosseno a partir do seno e vice-versa."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Cosseno no 1º Quadrante",
                        codeOrFormula = "\\sin(x) = 0,6 \\implies \\cos(x) = \\sqrt{1 - 0,6^2} = \\sqrt{0,64} = 0,8",
                        explanation = "A relação fundamental permite encontrar as demais razões quando conhecemos uma delas."
                    )
                ),
                goldTip = "Lembre-se da cantiga dos ângulos notáveis: '1, 2, 3... 3, 2, 1... todos sobre dois, raiz onde não tem um'!"
            ),

            "enem_m12_a3" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Lei dos Senos e Lei dos Cossenos em Triângulos Quaisquer
                    
                    Quando o triângulo **não é retângulo**, utilizamos as leis gerais para relacionar lados e ângulos.
                    
                    ### Lei dos Cossenos (Pitágoras Generalizado)
                    Em qualquer triângulo com lados \(a, b, c\) e ângulo \(\theta\) oposto a \(a\):
                    \[ a^2 = b^2 + c^2 - 2bc \cos(\theta) \]
                    • **Quando usar:** quando você conhece **dois lados e o ângulo entre eles**, ou **os três lados**.
                    
                    ### Lei dos Senos
                    Os lados são proporcionais aos senos dos ângulos opostos:
                    \[ \frac{a}{\sin \hat{A}} = \frac{b}{\sin \hat{B}} = \frac{c}{\sin \hat{C}} = 2R \]
                    (onde \(R\) é o raio da circunferência circunscrita ao triângulo).
                    • **Quando usar:** quando você conhece **dois ângulos e um lado**, ou um lado e o raio circunscrito.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Lei dos Cossenos",
                        syntax = "a^2 = b^2 + c^2 - 2bc\\cos\\hat{A}",
                        description = "Relação métrica geral para qualquer triângulo plano.",
                        parametersInfo = "Se o ângulo for 90°, cos(90°) = 0 e recai exatamente no Teorema de Pitágoras."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Lei dos Senos",
                        syntax = "\\frac{a}{\\sin\\hat{A}} = \\frac{b}{\\sin\\hat{B}} = 2R",
                        description = "Proporção entre lados e senos dos vértices opostos.",
                        parametersInfo = "Permite calcular o raio da circunferência circunscrita R."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Distância entre dois barcos",
                        codeOrFormula = "d^2 = 5^2 + 8^2 - 2(5)(8)\\cos(60^\\circ) = 25 + 64 - 40 = 49 \\implies d = 7 \\text{ km}",
                        explanation = "Dois barcos partem do mesmo porto formando ângulo de 60°. Um anda 5 km e o outro 8 km. Pela Lei dos Cossenos, a distância direta entre eles é 7 km."
                    )
                ),
                goldTip = "Regra de ouro: se tem 2 lados e 1 ângulo entre eles, use Lei dos Cossenos; se tem 2 ângulos e 1 lado, use Lei dos Senos."
            ),

            "enem_m12_a4" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Ciclo Trigonométrico, Arcos e Radianos
                    
                    O ciclo trigonométrico é uma circunferência de raio unitário \(R = 1\) orientada no sentido anti-horário com centro na origem \((0,0)\).
                    
                    ### Eixos Coordenados no Ciclo
                    • O eixo das abscissas (\(x\)) corresponde ao **cosseno** (\(\cos\)).
                    • O eixo das ordenadas (\(y\)) corresponde ao **seno** (\(\sin\)).
                    
                    ### Conversão Graus - Radianos
                    Por definição, \( 180^\circ = \pi \text{ radianos} \).
                    Portanto, \( 360^\circ = 2\pi \text{ rad} \), \( 90^\circ = \frac{\pi}{2} \text{ rad} \), \( 60^\circ = \frac{\pi}{3} \text{ rad} \).
                    Comprimento de um arco com ângulo central \(\theta\) (em radianos):
                    \[ s = \theta \cdot R \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Conversão de Unidades Angulares",
                        syntax = "\\frac{\\alpha^\\circ}{180^\\circ} = \\frac{\\theta \\text{ rad}}{\\pi}",
                        description = "Regra de três simples para converter graus em radianos.",
                        parametersInfo = "Essencial em física ondulatória e no movimento circular uniforme."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Sinais no Ciclo Trigonométrico",
                        syntax = "\\sin: (+, +, -, -) \\quad \\cos: (+, -, -, +)",
                        description = "Distribuição de sinais pelos quadrantes 1º, 2º, 3º e 4º.",
                        parametersInfo = "1ºQ: todos +, 2ºQ: sen +, 3ºQ: tan +, 4ºQ: cos +."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Pêndulo Simples",
                        codeOrFormula = "s = \\theta \\cdot R = \\frac{\\pi}{6} \\cdot 1,2 = 0,2\\pi \\approx 0,628 \\text{ m}",
                        explanation = "Um pêndulo de 1,2 m oscila com amplitude angular de 30° (pi/6 rad). O arco percorrido pela extremidade é s = (pi/6) * 1,2 = 0,2 pi m."
                    )
                ),
                goldTip = "Antes de multiplicar o raio pelo ângulo para calcular arco de curva, certifique-se de que o ângulo está em radianos!"
            ),

            "enem_m12_a5" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Funções Trigonométricas Seno e Cosseno
                    
                    No ENEM, funções trigonométricas modelam fenômenos periódicos naturais e tecnológicos: batimentos cardíacos, variação das marés, temperaturas médias ao longo dos meses e ondas sonoras.
                    
                    ### Parâmetros da Função Harmônica Geral
                    Dada a função:
                    \[ f(t) = A + B \cdot \sin(C \cdot t + D) \]
                    • **Eixo Médio / Deslocamento Vertical (\(A\)):** média entre valor máximo e mínimo: \( A = \frac{Y_{\max} + Y_{\min}}{2} \).
                    • **Amplitude (\(|B|\)):** metade da oscilação total: \( |B| = \frac{Y_{\max} - Y_{\min}}{2} \).
                    • **Período (\(P\)):** tempo para completar um ciclo completo: \( P = \frac{2\pi}{|C|} \).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Período da Onda Senoidal",
                        syntax = "T = \\frac{2\\pi}{|C|}",
                        description = "Determina o intervalo temporal ou espacial de uma repetição.",
                        parametersInfo = "Se C dobra, a frequência dobra e o período cai pela metade."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Imagem da Função Seno/Cosseno",
                        syntax = "\\text{Im}(f) = [A - |B|, A + |B|]",
                        description = "Intervalo fechado entre o valor mínimo e o máximo da função.",
                        parametersInfo = "Como -1 <= sin(x) <= 1, f varia entre A - B e A + B."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Altura da Maré Oceânica",
                        codeOrFormula = "h(t) = 3 + 2\\cos\\left(\\frac{\\pi t}{6}\\right) \\implies P = \\frac{2\\pi}{\\pi/6} = 12 \\text{ horas}",
                        explanation = "A altura máxima da maré é 3 + 2 = 5 metros; a mínima é 3 - 2 = 1 metro. O período entre duas marés altas consecutivas é 12 horas."
                    )
                ),
                goldTip = "Para achar o valor máximo de A + B*sen(x), substitua sen(x) por 1; para achar o mínimo, substitua sen(x) por -1 (se B for positivo)."
            ),

            // ==========================================
            // MÓDULO 13: GEOMETRIA ANALÍTICA
            // ==========================================
            "enem_m13_a1" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Plano Cartesiano e Distância entre Dois Pontos
                    
                    A Geometria Analítica conecta a álgebra à geometria através do sistema de coordenadas cartesianas retangulares.
                    
                    ### Distância Euclidiana entre Pontos
                    A distância entre \(A(x_A, y_A)\) e \(B(x_B, y_B)\) é calculada aplicando o Teorema de Pitágoras no triângulo retângulo formado pelas variações \(\Delta x\) e \(\Delta y\):
                    \[ d_{AB} = \sqrt{(x_B - x_A)^2 + (y_B - y_A)^2} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fórmula da Distância entre Dois Pontos",
                        syntax = "d = \\sqrt{(x_2 - x_1)^2 + (y_2 - y_1)^2}",
                        description = "Comprimento do segmento retilíneo unindo dois pontos no plano.",
                        parametersInfo = "Independe da ordem dos pontos, pois o quadrado elimina sinais negativos."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Antena de Telecomunicações",
                        codeOrFormula = "d = \\sqrt{(5 - 1)^2 + (7 - 4)^2} = \\sqrt{4^2 + 3^2} = \\sqrt{25} = 5 \\text{ km}",
                        explanation = "A distância entre uma torre na posição (1, 4) e uma residência na posição (5, 7) é exatamente 5 km (triângulo pitagórico 3-4-5)."
                    )
                ),
                goldTip = "Fique de olho nas trincas pitagóricas clássicas: (3, 4, 5) e (5, 12, 13) frequentemente simplificam contas de distância no ENEM!"
            ),

            "enem_m13_a2" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Ponto Médio e Baricentro de um Triângulo
                    
                    ### Coordenadas do Ponto Médio
                    O ponto médio \(M\) do segmento \(AB\) é a média aritmética simples das coordenadas das extremidades:
                    \[ x_M = \frac{x_A + x_B}{2}, \quad y_M = \frac{y_A + y_B}{2} \]
                    
                    ### Baricentro de um Triângulo (\(G\))
                    O baricentro é o ponto de encontro das medianas e o centro de gravidade (massa uniforme) do triângulo:
                    \[ x_G = \frac{x_A + x_B + x_C}{3}, \quad y_G = \frac{y_A + y_B + y_C}{3} \]
                    O baricentro divide cada mediana na proporção \(2:1\) a partir do vértice.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Ponto Médio de Segmento",
                        syntax = "M = \\left(\\frac{x_1 + x_2}{2}, \\frac{y_1 + y_2}{2}\\right)",
                        description = "Equidistante de ambos os extremos.",
                        parametersInfo = "Usado em problemas de rotas de entrega e posicionamento de centros de distribuição."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Baricentro do Triângulo",
                        syntax = "G = \\left(\\frac{x_A + x_B + x_C}{3}, \\frac{y_A + y_B + y_C}{3}\\right)",
                        description = "Média das coordenadas dos três vértices.",
                        parametersInfo = "Centro de equilíbrio mecânico da placa triangular."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Ponto de Encontro em Centroide",
                        codeOrFormula = "x_G = \\frac{2 + 6 + 4}{3} = 4, \\quad y_G = \\frac{1 + 3 + 8}{3} = 4 \\implies G(4, 4)",
                        explanation = "Três cidades localizadas nas coordenadas (2, 1), (6, 3) e (4, 8) desejam instalar um hospital no baricentro da região: a coordenada exata é (4, 4)."
                    )
                ),
                goldTip = "Para o ponto médio divide por 2; para o baricentro de 3 vértices divide por 3. Não confunda os denominadores!"
            ),

            "enem_m13_a3" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Condição de Alinhamento de Três Pontos
                    
                    Três pontos distintos \(A(x_A, y_A)\), \(B(x_B, y_B)\) e \(C(x_C, y_C)\) são **colineares** (pertencem à mesma reta) se, e somente se, o determinante de suas coordenadas for nulo:
                    \[
                    \begin{vmatrix}
                    x_A & y_A & 1 \\
                    x_B & y_B & 1 \\
                    x_C & y_C & 1
                    \end{vmatrix} = 0
                    \]
                    
                    ### Cálculo da Área de um Triângulo no Plano
                    Se os pontos não forem colineares, eles formam os vértices de um triângulo cuja área é dada pela metade do módulo desse determinante:
                    \[ \text{Área} = \frac{1}{2} \cdot |D| \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Critério do Determinante para Colinearidade",
                        syntax = "D = x_A(y_B - y_C) + x_B(y_C - y_A) + x_C(y_A - y_B) = 0",
                        description = "Condição necessária e suficiente para que 3 pontos pertençam a uma mesma reta.",
                        parametersInfo = "Equivalente a dizer que o coeficiente angular m_AB = m_BC."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Área de Triângulo por Coordenadas",
                        syntax = "A = \\frac{|D|}{2}",
                        description = "Calcula a área de qualquer polígono triangular conhecendo apenas os vértices.",
                        parametersInfo = "Evita necessidade de calcular base e altura com Pitágoras."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Verificação de Alinhamento",
                        codeOrFormula = "m_{AB} = \\frac{5 - 1}{4 - 2} = 2, \\quad m_{BC} = \\frac{9 - 5}{6 - 4} = 2 \\implies \\text{Alinhados}",
                        explanation = "Os pontos (2, 1), (4, 5) e (6, 9) possuem a mesma inclinação m = 2, logo são estritamente colineares (D = 0)."
                    )
                ),
                goldTip = "Calcular os coeficientes angulares m_AB = Delta y / Delta x e m_BC é muitas vezes mais rápido do que montar o determinante 3x3 completo!"
            ),

            "enem_m13_a4" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Equações da Reta: Reduzida e Geral
                    
                    ### 1. Equação Reduzida da Reta
                    \[ y = m \cdot x + q \]
                    • \(m\): **coeficiente angular** (declividade ou taxa de inclinação):
                    \[ m = \tan(\theta) = \frac{y_B - y_A}{x_B - x_A} \]
                    • \(q\): **coeficiente linear** (ponto de intercepto com o eixo das ordenadas: \((0, q)\)).
                    
                    ### 2. Equação Geral da Reta
                    \[ A \cdot x + B \cdot y + C = 0 \]
                    Onde o coeficiente angular é \( m = -\frac{A}{B} \) (com \(B \ne 0\)).
                    
                    ### Ponto e Inclinação (Equação Fundamental)
                    Conhecendo um ponto \(P_0(x_0, y_0)\) e a inclinação \(m\):
                    \[ y - y_0 = m \cdot (x - x_0) \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação Fundamental da Reta",
                        syntax = "y - y_0 = m(x - x_0)",
                        description = "Monta a reta a partir de um ponto e do coeficiente angular.",
                        parametersInfo = "Mnemônico popular: 'Yoiô mixoxô'."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Coeficiente Angular (Declividade)",
                        syntax = "m = \\frac{\\Delta y}{\\Delta x} = \\frac{y_2 - y_1}{x_2 - x_1}",
                        description = "Taxa de variação média constante da reta.",
                        parametersInfo = "Se m > 0 a reta é crescente; se m < 0 é decrescente; se m = 0 é horizontal."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Reta de Tarifa de Táxi",
                        codeOrFormula = "y - 5 = 2,5(x - 0) \\implies y = 2,5x + 5",
                        explanation = "A bandeirada fixa é R$ 5,00 (intercepto linear q = 5) e cada km rodado custa R$ 2,50 (inclinação m = 2,5). A equação da reta é y = 2,5x + 5."
                    )
                ),
                goldTip = "Em gráficos lineares do ENEM, o coeficiente linear é onde a reta corta o eixo vertical y, e a inclinação é 'quanto sobe dividido por quanto avança'!"
            ),

            "enem_m13_a5" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Posições Relativas de Retas: Paralelismo e Perpendicularismo
                    
                    Dadas duas retas \(r\) e \(s\) com coeficientes angulares \(m_r\) e \(m_s\):
                    
                    ### 1. Retas Paralelas (\(r \parallel s\))
                    Possuem a mesma inclinação e nunca se cruzam (ou coincidem):
                    \[ m_r = m_s \]
                    
                    ### 2. Retas Perpendiculares (\(r \perp s\))
                    Cruzam-se formando um ângulo reto de 90°. Suas declividades são opostas e inversas:
                    \[ m_r \cdot m_s = -1 \iff m_s = -\frac{1}{m_r} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Condição de Paralelismo",
                        syntax = "m_r = m_s \\quad (q_r \\ne q_s)",
                        description = "Mesma inclinação e interceptos lineares distintos.",
                        parametersInfo = "Sistemas lineares com retas paralelas distintas são impossíveis (sem solução comum)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Condição de Perpendicularismo",
                        syntax = "m_r \\cdot m_s = -1",
                        description = "O produto das inclinações de retas perpendiculares é sempre -1.",
                        parametersInfo = "Se a reta r tem m = 2, a reta perpendicular tem m = -1/2."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Reta Perpendicular",
                        codeOrFormula = "r: y = 3x - 1 \\implies m_r = 3 \\implies m_s = -1/3 \\implies y - 2 = -1/3(x - 6)",
                        explanation = "A reta perpendicular à reta y = 3x - 1 passando por (6, 2) tem m = -1/3. Logo: y - 2 = -x/3 + 2 => y = -x/3 + 4."
                    )
                ),
                goldTip = "Para achar a inclinação da reta perpendicular, inverta a fração e troque o sinal: de 2/3 vira -3/2!"
            ),

            "enem_m13_a6" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Equação da Circunferência: Reduzida e Geral
                    
                    Uma circunferência é o lugar geométrico de todos os pontos \(P(x, y)\) do plano cuja distância ao centro fixo \(C(a, b)\) é constante e igual ao raio \(R\).
                    
                    ### 1. Equação Reduzida
                    \[ (x - a)^2 + (y - b)^2 = R^2 \]
                    
                    ### 2. Equação Geral
                    Desenvolvendo os produtos notáveis:
                    \[ x^2 + y^2 - 2ax - 2by + (a^2 + b^2 - R^2) = 0 \]
                    
                    Para obter o centro a partir da equação geral, divida os coeficientes de \(x\) e de \(y\) por \(-2\).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação Reduzida da Circunferência",
                        syntax = "(x - a)^2 + (y - b)^2 = R^2",
                        description = "Representação imediata do centro C(a, b) e do raio R.",
                        parametersInfo = "Se o centro estiver na origem (0, 0), simplifica para x^2 + y^2 = R^2."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Identificação de Centro e Raio na Forma Geral",
                        syntax = "a = -\\frac{D}{2}, \\quad b = -\\frac{E}{2}, \\quad R = \\sqrt{a^2 + b^2 - F}",
                        description = "Técnica de completamento de quadrados para voltar à reduzida.",
                        parametersInfo = "Para existir circunferência real, a^2 + b^2 - F > 0."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Alcance de Torre de Celular",
                        codeOrFormula = "(x - 3)^2 + (y - 4)^2 = 25 \\implies C(3, 4) \\text{ e } R = 5 \\text{ km}",
                        explanation = "A área de cobertura de uma antena de celular tem limite dado por (x - 3)^2 + (y - 4)^2 = 25. O centro é (3, 4) e o raio de alcance máximo é 5 km."
                    )
                ),
                goldTip = "Cuidado com o sinal na equação reduzida: (x + 2)^2 significa que a coordenada x do centro é -2!"
            ),

            // ==========================================
            // MÓDULO 14: MATRIZES, DETERMINANTES E SISTEMAS
            // ==========================================
            "enem_m14_a1" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Matrizes: Representação e Operações
                    
                    No ENEM, matrizes são utilizadas para tabular dados econômicos, rotas logísticas, notas escolares e estoques de lojas.
                    
                    ### Notação Matricial
                    Uma matriz \(A\) de ordem \(m \times n\) possui \(m\) linhas e \(n\) colunas, com elementos genéricos \(a_{ij}\) (onde \(i\) indica a linha e \(j\) a coluna).
                    
                    ### Operações Básicas
                    • **Adição e Subtração:** só podem ser realizadas entre matrizes de mesma ordem, somando-se os elementos correspondentes.
                    • **Multiplicação por Escalar:** multiplica-se cada entrada da matriz pelo número real.
                    • **Multiplicação de Matrizes (\(A_{m \times p} \cdot B_{p \times n} = C_{m \times n}\)):** a multiplicação só é possível se o **número de colunas de A for igual ao número de linhas de B**. O elemento \(c_{ij}\) é a soma dos produtos da linha \(i\) de A pela coluna \(j\) de B.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Condição de Existência do Produto Matricial",
                        syntax = "A_{m \\times p} \\cdot B_{p \\times n} = C_{m \\times n}",
                        description = "O número de colunas da 1ª deve coincidir com o número de linhas da 2ª.",
                        parametersInfo = "A multiplicação de matrizes NÃO é comutativa em geral: A*B != B*A."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Matriz Transposta",
                        syntax = "(A^t)_{ij} = a_{ji}",
                        description = "Linhas viram colunas e colunas viram linhas.",
                        parametersInfo = "Se A = A^t, a matriz é simétrica."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Faturamento de Lojas",
                        codeOrFormula = "\\begin{bmatrix} 10 & 20 \\end{bmatrix} \\cdot \\begin{bmatrix} 50 \\\\ 30 \\end{bmatrix} = 10(50) + 20(30) = 500 + 600 = R$ 1.100,00",
                        explanation = "A linha representa as quantidades vendidas dos produtos A e B (10 e 20 unidades) e a coluna representa os preços unitários (50 e 30 reais). O produto matricial calcula o faturamento total."
                    )
                ),
                goldTip = "Lembre-se sempre: Linha por Coluna! Na multiplicação matricial, você 'deita' a linha da 1ª matriz sobre a coluna da 2ª."
            ),

            "enem_m14_a2" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Determinantes de 2ª e 3ª Ordem (Regra de Sarrus)
                    
                    O determinante é um número real associado a uma matriz quadrada.
                    
                    ### Determinante de Ordem 2x2
                    \[ \det \begin{bmatrix} a & b \\ c & d \end{bmatrix} = a \cdot d - b \cdot c \]
                    (Produto da diagonal principal menos produto da diagonal secundária).
                    
                    ### Determinante de Ordem 3x3 (Regra de Sarrus)
                    Repetem-se as duas primeiras colunas à direita da matriz:
                    1. Somam-se os três produtos das diagonais no sentido principal.
                    2. Subtraem-se os três produtos das diagonais no sentido secundário.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Determinante 2x2",
                        syntax = "\\det(A) = a_{11}a_{22} - a_{12}a_{21}",
                        description = "Diferença entre diagonal principal e secundária.",
                        parametersInfo = "Determinante nulo indica que as linhas são linearmente dependentes."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra de Sarrus (Ordem 3)",
                        syntax = "\\det(A) = (aei + bfg + cdh) - (ceg + afh + bdi)",
                        description = "Algoritmo prático exclusivo para matrizes quadradas de ordem 3.",
                        parametersInfo = "Não pode ser aplicado em matrizes de ordem 4 ou superior."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Determinante de Matriz 2x2",
                        codeOrFormula = "\\det \\begin{bmatrix} 4 & 3 \\\\ 2 & 5 \\end{bmatrix} = (4)(5) - (3)(2) = 20 - 6 = 14",
                        explanation = "Calcula-se o produto dos elementos da diagonal principal (4 * 5 = 20) e subtrai-se o produto da diagonal secundária (3 * 2 = 6)."
                    )
                ),
                goldTip = "Se uma matriz possui uma linha ou coluna inteira de zeros, ou duas linhas proporcionais, o determinante é imediatamente ZERO sem fazer contas!"
            ),

            "enem_m14_a3" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Sistemas Lineares: Classificação, Escalonamento e Cramer
                    
                    Um sistema linear de equações expressa o cruzamento de múltiplas restrições algébricas.
                    
                    ### Classificação dos Sistemas Lineares
                    1. **SPD (Sistema Possível e Determinado):** admite **solução única**. As retas/planos se cruzam em um único ponto (\(D \ne 0\)).
                    2. **SPI (Sistema Possível e Indeterminado):** admite **infinitas soluções**. As retas são coincidentes (\(D = D_x = D_y = 0\)).
                    3. **SI (Sistema Impossível):** **não admite nenhuma solução**. As retas são paralelas e distintas (\(D = 0\) e pelo menos um \(D_i \ne 0\)).
                    
                    ### Regra de Cramer (para sistemas \(n \times n\))
                    \[ x = \frac{D_x}{D}, \quad y = \frac{D_y}{D}, \quad z = \frac{D_z}{D} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra de Cramer",
                        syntax = "x_i = \\frac{D_{x_i}}{D} \\quad (D \\ne 0)",
                        description = "Solução de sistemas com mesma quantidade de equações e incógnitas.",
                        parametersInfo = "D é o determinante da matriz dos coeficientes das incógnitas."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Critério de Resolução SPD",
                        syntax = "D \\ne 0 \\iff \\text{Solução Única}",
                        description = "Garante que as equações são linearmente independentes.",
                        parametersInfo = "Fundamental para discutir sistemas lineares com parâmetros no ENEM."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Compra de Cadernos e Canetas",
                        codeOrFormula = "\\begin{cases} 2x + 3y = 28 \\\\ 4x + y = 26 \\end{cases} \\implies x = 5, \\quad y = 6",
                        explanation = "x é o preço do caderno e y o da caneta. Multiplicando a 2ª equação por -3 e somando: -10x = -50 => x = R$ 5,00 e y = R$ 6,00."
                    )
                ),
                goldTip = "Em provas de múltipla escolha como o ENEM, testar as alternativas nas equações muitas vezes é o caminho mais rápido para encontrar a solução!"
            )
        )
    }

    private val exerciseRegistry: Map<String, List<MathExercise>> by lazy {
        mapOf(
            // MÓDULO 1
            "enem_m1_a7" to listOf(
                MathExercise(
                    id = "ex_m1_a7_1",
                    title = "ENEM: Grandezas Inversamente Proporcionais",
                    problemStatement = "Dois amigos abriram uma empresa e combinaram que a distribuição do lucro anual de R$ 90.000,00 seria inversamente proporcional ao número de dias que cada um esteve ausente da administração: o sócio A faltou 2 dias e o sócio B faltou 3 dias. Qual foi o valor do lucro recebido pelo sócio A?",
                    options = listOf("R$ 36.000,00", "R$ 45.000,00", "R$ 54.000,00", "R$ 60.000,00", "R$ 72.000,00"),
                    correctIndex = 2,
                    hint = "Se é inversamente proporcional, A recebe k/2 e B recebe k/3.",
                    stepByStepSolution = "k/2 + k/3 = 90.000 -> 5k/6 = 90.000 -> 5k = 540.000 -> k = 108.000.\nSócio A = 108.000 / 2 = R$ 54.000,00."
                )
            ),

            "enem_m1_a8" to listOf(
                MathExercise(
                    id = "ex_m1_a8_1",
                    title = "ENEM: Pavimentação de Rodovia (Regra de Três Composta)",
                    problemStatement = "Uma equipe de 12 operários, trabalhando 8 horas por dia, pavimenta 400 metros de rodovia em 10 dias. Quantos dias serão necessários para que uma equipe de 16 operários, trabalhando 6 horas por dia com a mesma produtividade, pavimente 600 metros dessa mesma rodovia?",
                    options = listOf("10 dias", "12 dias", "14 dias", "15 dias", "18 dias"),
                    correctIndex = 3,
                    hint = "Operários e horas/dia são grandezas inversas aos dias; extensão da rodovia é grandeza direta.",
                    stepByStepSolution = "10/x = (16/12) * (6/8) * (400/600)\n10/x = (4/3) * (3/4) * (2/3) = 1 * (2/3) = 2/3\n2x = 30 -> x = 15 dias."
                )
            ),

            "enem_m1_a9" to listOf(
                MathExercise(
                    id = "ex_m1_a9_1",
                    title = "ENEM: Terreno e Escala Cartográfica de Superfície",
                    problemStatement = "Em um mapa confeccionado na escala linear 1:20.000, uma reserva ecológica retangular é representada com 3 cm de largura por 5 cm de comprimento. A área real dessa reserva, em quilômetros quadrados, é igual a:",
                    options = listOf("0,6 km²", "3,0 km²", "6,0 km²", "12,0 km²", "60,0 km²"),
                    correctIndex = 2,
                    hint = "Na escala linear 1:20.000, 1 cm no mapa vale 20.000 cm = 200 m = 0,2 km no terreno real.",
                    stepByStepSolution = "Largura real = 3 cm * 0,2 km/cm = 0,6 km.\nComprimento real = 5 cm * 0,2 km/cm = 1,0 km.\nÁrea real = 0,6 km * 1,0 km = 6,0 km² (Alternativa C)."
                )
            ),

            "enem_m1_a10" to listOf(
                MathExercise(
                    id = "ex_m1_a10_1",
                    title = "ENEM: Inflação e Reajustes Sucessivos",
                    problemStatement = "O aluguel de um imóvel sofreu um reajuste de 15% no início do primeiro semestre e, devido à inflação acumulada, um novo aumento de 10% no final do ano sobre o valor já corrigido. Em relação ao valor inicial do início do ano, qual foi o aumento percentual total acumulado?",
                    options = listOf("25,0%", "26,5%", "27,0%", "28,5%", "30,0%"),
                    correctIndex = 1,
                    hint = "Multiplique os fatores 1,15 e 1,10.",
                    stepByStepSolution = "Fator total = 1,15 * 1,10 = 1,265.\n1,265 - 1 = 0,265 = 26,5% de aumento acumulado (e não 25%)."
                )
            ),

            // MÓDULO 2
            "enem_m2_a3" to listOf(
                MathExercise(
                    id = "ex_m2_a3_1",
                    title = "ENEM: Financiamento Imobiliário no Sistema SAC",
                    problemStatement = "Um cidadão financiou R$ 240.000,00 pelo Sistema de Amortização Constante (SAC) em 120 parcelas mensais, à taxa de juros de 1% ao mês sobre o saldo devedor. Qual será o valor da primeira prestação paga por esse mutuário?",
                    options = listOf("R$ 2.400,00", "R$ 3.800,00", "R$ 4.000,00", "R$ 4.400,00", "R$ 4.800,00"),
                    correctIndex = 3,
                    hint = "A amortização mensal é fixa: A = Capital / n. A prestação é a soma da amortização com os juros do primeiro mês.",
                    stepByStepSolution = "Amortização A = 240.000 / 120 = R$ 2.000,00.\nJuros do 1º mês = 1% de 240.000 = R$ 2.400,00.\nPrimeira prestação P_1 = 2.000 + 2.400 = R$ 4.400,00."
                )
            ),

            // MÓDULO 10
            "enem_m10_a1" to listOf(
                MathExercise(
                    id = "ex_m10_a1_1",
                    title = "ENEM: Padrão Geométrico em Azulejos",
                    problemStatement = "Uma sequência de mosaicos é construída com azulejos pretos e brancos. O 1º mosaico tem 8 azulejos brancos; o 2º tem 11; o 3º tem 14, mantendo sempre o mesmo padrão aditivo regular. Quantos azulejos brancos terá o 25º mosaico?",
                    options = listOf("72", "76", "80", "84", "88"),
                    correctIndex = 2,
                    hint = "A sequência é uma PA com a_1 = 8 e razão r = 3.",
                    stepByStepSolution = "a_25 = a_1 + (25 - 1) * r = 8 + 24 * 3 = 8 + 72 = 80 azulejos."
                )
            ),

            "enem_m10_a2" to listOf(
                MathExercise(
                    id = "ex_m10_a2_1",
                    title = "ENEM: Economia Progressiva em Cofre (Soma da PA)",
                    problemStatement = "Uma pessoa planejou guardar dinheiro durante 30 dias: guardou R$ 5,00 no 1º dia, R$ 7,00 no 2º dia, R$ 9,00 no 3º dia, e assim sucessivamente até o trigésimo dia. O valor total acumulado no cofre ao final dos 30 dias foi de:",
                    options = listOf("R$ 960,00", "R$ 1.020,00", "R$ 1.050,00", "R$ 1.080,00", "R$ 1.120,00"),
                    correctIndex = 1,
                    hint = "Calcule o 30º termo da PA (razão 2) e depois a soma de Gauss dos 30 termos.",
                    stepByStepSolution = "a_30 = 5 + (30 - 1)*2 = 5 + 58 = R$ 63,00.\nS_30 = [(a_1 + a_30) * n] / 2 = [(5 + 63) * 30] / 2 = 68 * 15 = R$ 1.020,00."
                )
            ),

            "enem_m10_a3" to listOf(
                MathExercise(
                    id = "ex_m10_a3_1",
                    title = "ENEM: Depreciação de Equipamento e PG",
                    problemStatement = "Uma máquina industrial avaliada hoje em R$ 80.000,00 sofre uma depreciação anual de 20% sobre o seu valor no ano imediatamente anterior. Qual será o valor estimado dessa máquina após 3 anos de uso contínuo?",
                    options = listOf("R$ 32.000,00", "R$ 38.400,00", "R$ 40.960,00", "R$ 48.000,00", "R$ 51.200,00"),
                    correctIndex = 2,
                    hint = "A cada ano o valor da máquina passa a ser 80% (fator 0,8) do ano anterior.",
                    stepByStepSolution = "Valor = 80.000 * (0,80)^3 = 80.000 * 0,512 = R$ 40.960,00."
                )
            ),

            // MÓDULO 12
            "enem_m12_a1" to listOf(
                MathExercise(
                    id = "ex_m12_a1_1",
                    title = "ENEM: Rampa de Acessibilidade e Seno",
                    problemStatement = "Uma rampa de acessibilidade plana deve vencer um desnível vertical de 1,5 metro entre a calçada e a porta de um hospital. A norma técnica exige que o ângulo de inclinação da rampa com o solo seja de exatamente 30°. Qual deve ser o comprimento total da superfície inclinada da rampa?",
                    options = listOf("2,0 m", "2,5 m", "3,0 m", "3,5 m", "4,0 m"),
                    correctIndex = 2,
                    hint = "O desnível vertical é o cateto oposto ao ângulo de 30° e o comprimento da rampa é a hipotenusa. sen(30°) = 1/2.",
                    stepByStepSolution = "sen(30°) = Cateto Oposto / Hipotenusa\n1/2 = 1,5 / L -> L = 1,5 * 2 = 3,0 metros."
                )
            ),

            "enem_m12_a2" to listOf(
                MathExercise(
                    id = "ex_m12_a2_1",
                    title = "ENEM: Sombra de Poste e Ângulos Notáveis",
                    problemStatement = "Em um dia ensolarado, um poste vertical projeta no chão plano uma sombra horizontal de comprimento igual a 4√3 metros. Sabendo que os raios solares incidem sobre o solo formando um ângulo de 60°, qual é a altura desse poste?",
                    options = listOf("6 metros", "8 metros", "10 metros", "12 metros", "14 metros"),
                    correctIndex = 3,
                    hint = "Use a tangente de 60° = √3.",
                    stepByStepSolution = "tan(60°) = Altura / Sombra\n√3 = h / (4√3) -> h = 4√3 * √3 = 4 * 3 = 12 metros."
                )
            ),

            "enem_m12_a3" to listOf(
                MathExercise(
                    id = "ex_m12_a3_1",
                    title = "ENEM: Navegação Marítima e Lei dos Cossenos",
                    problemStatement = "Uma lancha parte de um porto O e navega 6 milhas náuticas em linha reta até o ponto A. Ao mesmo tempo, um veleiro parte do mesmo porto O e navega 10 milhas em linha reta até o ponto B. Sabendo que o ângulo formado entre as duas rotas (AOB) foi de 60°, a distância em linha reta entre os dois barcos nos pontos A e B é de:",
                    options = listOf("2√19 milhas", "14 milhas", "8 milhas", "4√7 milhas", "2√31 milhas"),
                    correctIndex = 0,
                    hint = "Aplique a Lei dos Cossenos: d² = a² + b² - 2ab cos(60°). Note que cos(60°) = 0,5.",
                    stepByStepSolution = "d² = 6² + 10² - 2 * 6 * 10 * cos(60°)\nd² = 36 + 100 - 120 * 0,5 = 136 - 60 = 76.\nd = √76 = √(4 * 19) = 2√19 milhas náuticas."
                )
            ),

            "enem_m12_a4" to listOf(
                MathExercise(
                    id = "ex_m12_a4_1",
                    title = "ENEM: Roda Gigante e Comprimento de Arco",
                    problemStatement = "Uma roda gigante com raio de 18 metros gira no sentido anti-horário. Uma cabine que se encontrava no ponto mais baixo sobe descrevendo um ângulo central de 2π/3 radianos. O comprimento do arco percorrido pela cabine nessa rotação foi de:",
                    options = listOf("6π metros", "9π metros", "12π metros", "15π metros", "18π metros"),
                    correctIndex = 2,
                    hint = "O comprimento do arco é dado por s = θ * R (com θ em radianos).",
                    stepByStepSolution = "s = θ * R = (2π/3) * 18 = 2π * 6 = 12π metros."
                )
            ),

            "enem_m12_a5" to listOf(
                MathExercise(
                    id = "ex_m12_a5_1",
                    title = "ENEM: Temperatura Média e Modelo Senoidal",
                    problemStatement = "A temperatura média diária T (em °C) em uma cidade serrana ao longo dos meses do ano t (onde t = 1 representa janeiro) é modelada pela função periódica:\nT(t) = 18 + 6 \\cdot \\cos\\left(\\frac{\\pi(t - 1)}{6}\\right)\nQual é a temperatura máxima prevista para essa cidade e em qual mês do ano ela ocorre?",
                    options = listOf("24 °C em janeiro", "24 °C em julho", "22 °C em março", "18 °C em abril", "12 °C em outubro"),
                    correctIndex = 0,
                    hint = "O valor máximo do cosseno é +1, que ocorre quando o argumento é 0 (t = 1).",
                    stepByStepSolution = "T_max = 18 + 6 * 1 = 24 °C. Para cos = 1, π(t - 1)/6 = 0 -> t = 1 (mês de janeiro)."
                )
            ),

            // MÓDULO 13
            "enem_m13_a1" to listOf(
                MathExercise(
                    id = "ex_m13_a1_1",
                    title = "ENEM: Distância entre Cidades em Malha Viária",
                    problemStatement = "Em uma representação cartográfica com coordenadas em quilômetros, a sede de um município A fica no ponto (-2, 1) e a sede de outro município B fica no ponto (6, 7). A distância em linha reta entre essas duas sedes municipais é de:",
                    options = listOf("8 km", "9 km", "10 km", "12 km", "14 km"),
                    correctIndex = 2,
                    hint = "Calcule d = √[(Δx)² + (Δy)²].",
                    stepByStepSolution = "Δx = 6 - (-2) = 8; Δy = 7 - 1 = 6.\nd = √(8² + 6²) = √(64 + 36) = √100 = 10 km."
                )
            ),

            "enem_m13_a2" to listOf(
                MathExercise(
                    id = "ex_m13_a2_1",
                    title = "ENEM: Centro de Distribuição e Ponto Médio",
                    problemStatement = "Uma rede de supermercados deseja construir um centro logístico de distribuição exatamente no ponto médio entre duas filiais localizadas nas posições (-4, 8) e (10, 2). As coordenadas cartesianas do centro logístico serão:",
                    options = listOf("(3, 5)", "(6, 6)", "(3, 3)", "(5, 3)", "(2, 4)"),
                    correctIndex = 0,
                    hint = "M = ((x_1 + x_2)/2, (y_1 + y_2)/2).",
                    stepByStepSolution = "x_M = (-4 + 10)/2 = 6/2 = 3.\ny_M = (8 + 2)/2 = 10/2 = 5.\nPonto Médio = (3, 5)."
                )
            ),

            "enem_m13_a3" to listOf(
                MathExercise(
                    id = "ex_m13_a3_1",
                    title = "ENEM: Área de Propriedade Triangular no Plano",
                    problemStatement = "Um sítio possui formato triangular com vértices demarcados nos pontos A(0, 0), B(6, 0) e C(4, 5), com coordenadas em centenas de metros. A área total desse sítio é igual a:",
                    options = listOf("10 centenas de m²", "12 centenas de m²", "15 centenas de m²", "20 centenas de m²", "30 centenas de m²"),
                    correctIndex = 2,
                    hint = "Área = (base * altura) / 2 ou pelo determinante 3x3.",
                    stepByStepSolution = "Como os pontos A e B estão sobre o eixo x, a base mede 6 unidades (de 0 a 6) e a altura vertical é a ordenada de C (y = 5). Área = (6 * 5) / 2 = 15 unidades de área."
                )
            ),

            "enem_m13_a4" to listOf(
                MathExercise(
                    id = "ex_m13_a4_1",
                    title = "ENEM: Equação da Reta da Rota Aérea",
                    problemStatement = "Um avião comercial mantém rota retilínea descrita no plano cartesiano passando pelos pontos P(2, 3) e Q(4, 7). A equação reduzida que descreve essa trajetória é:",
                    options = listOf("y = 2x - 1", "y = 2x + 1", "y = 3x - 3", "y = 4x - 5", "y = x + 3"),
                    correctIndex = 0,
                    hint = "Calcule a declividade m = (7 - 3)/(4 - 2) e utilize y - y_0 = m(x - x_0).",
                    stepByStepSolution = "m = (7 - 3) / (4 - 2) = 4 / 2 = 2.\ny - 3 = 2(x - 2) -> y - 3 = 2x - 4 -> y = 2x - 1."
                )
            ),

            "enem_m13_a5" to listOf(
                MathExercise(
                    id = "ex_m13_a5_1",
                    title = "ENEM: Retas Perpendiculares em Traçado Urbano",
                    problemStatement = "Em um projeto de loteamento urbano, a Avenida Principal tem equação da reta dada por 2x - y + 4 = 0. Uma rua transversal deve ser projetada de forma estritamente perpendicular à Avenida Principal, passando pelo cruzamento (4, 1). Qual é a equação da reta dessa rua transversal?",
                    options = listOf("x + 2y - 6 = 0", "2x + y - 9 = 0", "x - 2y - 2 = 0", "x + 2y + 6 = 0", "2x - y - 7 = 0"),
                    correctIndex = 0,
                    hint = "A reta original tem m_1 = 2. A reta perpendicular deve ter m_2 = -1/2.",
                    stepByStepSolution = "m_1 = 2 -> m_perp = -1/2.\ny - 1 = -1/2 (x - 4) -> 2y - 2 = -x + 4 -> x + 2y - 6 = 0."
                )
            ),

            "enem_m13_a6" to listOf(
                MathExercise(
                    id = "ex_m13_a6_1",
                    title = "ENEM: Radar Meteorológico e Equação da Circunferência",
                    problemStatement = "Um radar meteorológico instalado no ponto (2, -3) monitora tempestades em um raio de 4 km. A equação reduzida da circunferência que delimita o alcance máximo de monitoramento desse radar é:",
                    options = listOf("(x - 2)² + (y + 3)² = 16", "(x + 2)² + (y - 3)² = 16", "(x - 2)² + (y - 3)² = 4", "(x + 2)² + (y + 3)² = 4", "(x - 2)² + (y + 3)² = 8"),
                    correctIndex = 0,
                    hint = "(x - a)² + (y - b)² = R² com a = 2, b = -3 e R = 4.",
                    stepByStepSolution = "(x - 2)² + (y - (-3))² = 4² -> (x - 2)² + (y + 3)² = 16."
                )
            ),

            // MÓDULO 14
            "enem_m14_a1" to listOf(
                MathExercise(
                    id = "ex_m14_a1_1",
                    title = "ENEM: Matriz de Pontuação de Atletas",
                    problemStatement = "Em um torneio, o número de vitórias (3 pontos), empates (1 ponto) e derrotas (0 ponto) de duas equipes é representado pela matriz 2x3:\n\\[ M = \\begin{bmatrix} 5 & 2 & 1 \\\\ 4 & 4 & 0 \\end{bmatrix} \\]\nMultiplicando M pela matriz coluna de pontuação P = [3, 1, 0]^t, qual é a pontuação final obtida pelas duas equipes, respectivamente?",
                    options = listOf("17 e 16 pontos", "15 e 12 pontos", "16 e 16 pontos", "17 e 12 pontos", "18 e 16 pontos"),
                    correctIndex = 0,
                    hint = "Faça a multiplicação linha por coluna: 5*3 + 2*1 + 1*0 para a 1ª equipe, e 4*3 + 4*1 + 0*0 para a 2ª.",
                    stepByStepSolution = "Equipe 1 = 5*3 + 2*1 + 1*0 = 15 + 2 + 0 = 17 pontos.\nEquipe 2 = 4*3 + 4*1 + 0*0 = 12 + 4 + 0 = 16 pontos."
                )
            ),

            "enem_m14_a2" to listOf(
                MathExercise(
                    id = "ex_m14_a2_1",
                    title = "ENEM: Determinante e Parâmetro Linear",
                    problemStatement = "Para que a matriz quadrada \\( A = \\begin{bmatrix} k & 6 \\\\ 2 & 3 \\end{bmatrix} \\) NÃO seja invertível (ou seja, tenha determinante igual a zero), o valor real da constante k deve ser:",
                    options = listOf("1", "2", "3", "4", "6"),
                    correctIndex = 3,
                    hint = "det(A) = 3k - 12 = 0.",
                    stepByStepSolution = "det(A) = k * 3 - 6 * 2 = 3k - 12.\nPara det = 0: 3k = 12 -> k = 4."
                )
            ),

            "enem_m14_a3" to listOf(
                MathExercise(
                    id = "ex_m14_a3_1",
                    title = "ENEM: Sistema Linear de Mistura de Combustíveis",
                    problemStatement = "Um posto de combustíveis vendeu em um determinado dia um total de 1.000 litros entre etanol (R$ 3,50 o litro) e gasolina (R$ 5,50 o litro), totalizando um faturamento diário de R$ 4.700,00. Quantos litros de gasolina foram vendidos nesse dia?",
                    options = listOf("400 litros", "500 litros", "600 litros", "700 litros", "800 litros"),
                    correctIndex = 2,
                    hint = "Monte o sistema: E + G = 1000 e 3,5E + 5,5G = 4700.",
                    stepByStepSolution = "Multiplicando a 1ª equação por -3,5:\n-3,5E - 3,5G = -3500\nSomando com a 2ª (3,5E + 5,5G = 4700):\n2G = 1200 -> G = 600 litros de gasolina (e 400 de etanol)."
                )
            )
        )
    }
}
