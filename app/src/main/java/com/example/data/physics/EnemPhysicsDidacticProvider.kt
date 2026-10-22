package com.example.data.physics

import com.example.R
import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor de Conteúdo Didático Completo de Física ENEM.
 * Contém a teoria detalhada, fórmulas-chave, exemplos explicados e ilustrações
 * para todas as aulas do módulo de Física.
 */
object EnemPhysicsDidacticProvider {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        val entry = didacticMap[lessonId] ?: return null
        return CalculusDidacticBundle(
            theoryMarkdown = entry.theoryMarkdown,
            formulas = entry.formulas,
            solvedExamples = entry.solvedExamples,
            imageResId = entry.imageResId,
            goldTip = entry.goldTip
        )
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise> {
        return exercisesMap[lessonId] ?: emptyList()
    }

    private val exercisesMap: Map<String, List<MathExercise>> by lazy {
        mapOf(
            "fis_m1_a1" to listOf(
                MathExercise(
                    id = "fis_mec_01_q1",
                    title = "Alcance Relativo no MRU",
                    problemStatement = "Dois veículos, A e B, trafegam em uma rodovia retilínea no mesmo sentido. O veículo A move-se com velocidade constante de 72 km/h. O veículo B, inicialmente a 200 metros atrás de A, desloca-se com velocidade constante de 108 km/h. Em quantos segundos o veículo B alcançará o veículo A?",
                    options = listOf("10 segundos", "20 segundos", "30 segundos", "40 segundos", "50 segundos"),
                    correctIndex = 1,
                    hint = "Converta km/h para m/s dividindo por 3,6 e encontre a velocidade relativa de aproximação.",
                    stepByStepSolution = "vA = 72/3,6 = 20 m/s; vB = 108/3,6 = 30 m/s. v_rel = 30 - 20 = 10 m/s. Δt = ΔS / v_rel = 200 / 10 = 20 segundos."
                ),
                MathExercise(
                    id = "fis_mec_01_q2",
                    title = "Distância de Frenagem com Torricelli",
                    problemStatement = "Um motorista trafega a 90 km/h (25 m/s) quando avista um obstáculo e aciona os freios, provocando uma desaceleração constante de módulo 5,0 m/s² até a parada total. A distância percorrida pelo veículo durante a frenagem é igual a:",
                    options = listOf("50,0 m", "62,5 m", "75,0 m", "80,0 m", "100,0 m"),
                    correctIndex = 1,
                    hint = "Como o tempo não foi fornecido, aplique a Equação de Torricelli: v² = v₀² + 2·a·ΔS.",
                    stepByStepSolution = "0 = 25² + 2(-5)·ΔS => 0 = 625 - 10·ΔS => 10·ΔS = 625 => ΔS = 62,5 metros."
                ),
                MathExercise(
                    id = "fis_mec_01_q3",
                    title = "Área sob o Gráfico de Velocidade",
                    problemStatement = "Um carro parte do repouso e, nos primeiros 10 s, atinge 30 m/s com aceleração constante. Nos 10 s seguintes, mantém 30 m/s constante. A distância total percorrida nesses 20 s é de:",
                    options = listOf("300 m", "400 m", "450 m", "500 m", "600 m"),
                    correctIndex = 2,
                    hint = "No gráfico v × t, calcule a área do trapézio: [(B + b) · h] / 2.",
                    stepByStepSolution = "Base maior = 20 s, base menor = 10 s, altura = 30 m/s. Área = [(20 + 10) · 30] / 2 = 900 / 2 = 450 m."
                )
            ),
            "fis_m1_a2" to listOf(
                MathExercise(
                    id = "fis_mec_02_q1",
                    title = "Atrito Estático e Tendência de Movimento",
                    problemStatement = "Um bloco de massa 10 kg repousa sobre uma mesa horizontal (μe = 0,4; μc = 0,3). Uma força horizontal de 35 N é aplicada. Adotando g = 10 m/s², o valor da força de atrito atuante é:",
                    options = listOf("0 N", "30 N", "35 N", "40 N", "70 N"),
                    correctIndex = 2,
                    hint = "Calcule Fat_máx = μe · N. Se a força aplicada for menor que o atrito máximo, o bloco fica em repouso e Fat = F.",
                    stepByStepSolution = "N = 10 · 10 = 100 N. Fat_máx = 0,4 · 100 = 40 N. Como 35 N < 40 N, o bloco não se move e Fat = 35 N."
                ),
                MathExercise(
                    id = "fis_mec_02_q2",
                    title = "Indicação de Balança em Elevador",
                    problemStatement = "Uma pessoa de 70 kg está sobre uma balança dentro de um elevador que desce freando com desaceleração de 2 m/s². Com g = 10 m/s², a balança indicará:",
                    options = listOf("560 N", "700 N", "840 N", "980 N", "140 N"),
                    correctIndex = 2,
                    hint = "Descer freando significa aceleração para CIMA: N - P = m·a.",
                    stepByStepSolution = "N = m(g + a) = 70 · (10 + 2) = 70 · 12 = 840 N."
                )
            )
        )
    }

    data class PhysicsDidacticEntry(
        val theoryMarkdown: String,
        val formulas: List<FormulaOrSyntaxItem>,
        val solvedExamples: List<LessonExample>,
        val imageResId: Int? = null,
        val goldTip: String
    )

    private val didacticMap: Map<String, PhysicsDidacticEntry> by lazy {
        mapOf(
            // ========================================================
            // MECÂNICA
            // ========================================================
            "fis_m1_a1" to PhysicsDidacticEntry(
                theoryMarkdown = """
                    # Cinemática Escalar: MRU, MRUV e Funções Horárias
                    
                    A Cinemática descreve o movimento dos corpos no espaço e no tempo sem investigar as forças que o causam. No ENEM, este é o conteúdo de entrada fundamental para entender tráfego urbano, distância de frenagem e tempo de reação de motoristas.
                    
                    ### 1. Movimento Retilíneo Uniforme (MRU)
                    No MRU, a velocidade escalar permanece estritamente constante (\( v = \text{constante} \neq 0 \)), o que implica que a aceleração escalar é nula (\( a = 0 \)). O móvel percorre distâncias iguais em intervalos de tempo iguais.
                    
                    ### 2. Movimento Retilíneo Uniformemente Variado (MRUV)
                    No MRUV, a aceleração escalar é constante e não-nula (\( a = \text{constante} \neq 0 \)), fazendo com que a velocidade varie de maneira linear com o tempo.
                    
                    ### 3. A Equação de Torricelli
                    Quando o enunciado do problema **não informa e não pede o tempo (\( \Delta t \))**, a Equação de Torricelli é a ferramenta mais direta e rápida para encontrar velocidades ou distâncias percorridas!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Função Horária do Espaço no MRU",
                        syntax = "S = S_0 + v \\cdot t",
                        description = "Posição final em função da posição inicial e velocidade constante.",
                        parametersInfo = "S em metros (m), t em segundos (s), v em m/s."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Função Horária da Velocidade no MRUV",
                        syntax = "v = v_0 + a \\cdot t",
                        description = "Velocidade instantânea em função do tempo.",
                        parametersInfo = "v_0 = velocidade inicial, a = aceleração."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação de Torricelli",
                        syntax = "v^2 = v_0^2 + 2 \\cdot a \\cdot \\Delta S",
                        description = "Relaciona velocidade e deslocamento sem necessidade da variável tempo.",
                        parametersInfo = "Essencial em colisões e frenagens no trânsito."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Distância de Parada (Frenagem)",
                        codeOrFormula = "v^2 = v_0^2 + 2 \\cdot a \\cdot \\Delta S",
                        explanation = "Um carro a 72 km/h (20 m/s) freia com desaceleração de 4 m/s². Para parar (v = 0): 0 = 20² + 2(-4)·ΔS => 0 = 400 - 8·ΔS => 8·ΔS = 400 => ΔS = 50 metros."
                    )
                ),
                imageResId = R.drawable.grafico_fisica_lancamento,
                goldTip = "Sempre converta km/h para m/s dividindo por 3,6 antes de aplicar qualquer fórmula no ENEM!"
            ),

            "fis_m1_a2" to PhysicsDidacticEntry(
                theoryMarkdown = """
                    # Análise Gráfica, Vetores e Lançamentos
                    
                    A leitura de gráficos físicos é uma das habilidades mais cobradas na prova de Ciências da Natureza.
                    
                    ### Propriedades Gráficas Fundamentais
                    • **Gráfico Velocidade × Tempo (\( v \times t \)):** A área sob a curva é numericamente igual ao deslocamento escalar (\( \Delta S \)). A inclinação da reta representa a aceleração.
                    • **Gráfico Posição × Tempo (\( S \times t \)):** A inclinação da reta tangente em qualquer ponto fornece a velocidade instantânea do móvel.
                    
                    ### Lançamento Oblíquo no Vácuo (Composição de Galileu)
                    O movimento parabólico é composto por dois movimentos simultâneos e independentes:
                    1. **No eixo horizontal (X):** Movimento Retilíneo Uniforme com velocidade constante \( v_x = v_0 \\cos(\\theta) \).
                    2. **No eixo vertical (Y):** Movimento Retilíneo Uniformemente Variado sob aceleração da gravidade \( g \), com velocidade inicial \( v_y = v_0 \\sin(\\theta) \).
                    No ponto mais alto da trajetória (altura máxima \( H_{\\text{max}} \)), a componente vertical anula-se momentaneamente: \( v_y = 0 \).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Componentes da Velocidade Inicial",
                        syntax = "v_{0x} = v_0 \\cdot \\cos(\\theta) \\quad \\text{e} \\quad v_{0y} = v_0 \\cdot \\sin(\\theta)",
                        description = "Decomposição ortogonal do vetor velocidade de lançamento.",
                        parametersInfo = "θ é o ângulo de tiro em relação ao solo horizontal."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Alcance Máximo Horizontal",
                        syntax = "A = \\frac{v_0^2 \\cdot \\sin(2\\theta)}{g}",
                        description = "Distância horizontal total percorrida pelo projétil.",
                        parametersInfo = "O alcance é máximo quando o ângulo de lançamento é θ = 45°."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Tempo de Subida e Altura Máxima",
                        codeOrFormula = "v_y = v_{0y} - g \\cdot t_{sub}",
                        explanation = "No topo da parábola, v_y = 0. Logo, t_sub = v_{0y} / g. O tempo total de voo é o dobro do tempo de subida (t_total = 2 · t_sub)."
                    )
                ),
                imageResId = R.drawable.grafico_fisica_lancamento,
                goldTip = "Lembre-se: no ponto mais alto de um lançamento oblíquo, a velocidade NÃO é zero! A velocidade no topo é igual a Vx!"
            ),

            // ========================================================
            // ELETRICIDADE
            // ========================================================
            "fis_m2_a1" to PhysicsDidacticEntry(
                theoryMarkdown = """
                    # Eletrodinâmica: Corrente, Tensão e Potência Elétrica
                    
                    Eletrodinâmica é historicamente o assunto de maior recorrência em Física no ENEM (aparecendo em 100% das edições).
                    
                    ### Corrente Elétrica (\( I \))
                    A corrente elétrica é o movimento ordenado de portadores de carga (geralmente elétrons livres em condutores metálicos) sob a ação de uma diferença de potencial (ddp ou tensão \( U \)).
                    
                    ### Consumo de Energia Elétrica Residencial (\( E \))
                    As contas de luz cobram a energia gasta em quilowatt-hora (**kWh**):
                    \[ E = P \\cdot \\Delta t \]
                    onde a potência deve ser convertida para quilowatts (\( 1 \\text{ kW} = 1000 \\text{ W} \)) e o tempo em horas (\( \\text{h} \)).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Intensidade de Corrente Elétrica",
                        syntax = "I = \\frac{\\Delta Q}{\\Delta t} = \\frac{n \\cdot e}{\\Delta t}",
                        description = "Quantidade de carga líquida que atravessa a seção reta por unidade de tempo.",
                        parametersInfo = "1 Ampère (A) = 1 Coulomb por segundo (C/s)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Potência Elétrica (Fórmula do PUI)",
                        syntax = "P = U \\cdot I = R \\cdot I^2 = \\frac{U^2}{R}",
                        description = "Taxa de conversão de energia elétrica em trabalho ou calor por segundo.",
                        parametersInfo = "P em Watts (W), U em Volts (V), I em Ampères (A)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Cálculo de Consumo de Chuveiro Elétrico",
                        codeOrFormula = "E = P(kW) \\cdot \\Delta t(h)",
                        explanation = "Um chuveiro de 5500 W (5,5 kW) usado por 30 minutos (0,5 h) ao dia durante 30 dias consome: E = 5,5 × 0,5 × 30 = 82,5 kWh no mês."
                    )
                ),
                imageResId = R.drawable.grafico_circuito_eletrico,
                goldTip = "Em chuveiros e lâmpadas, quando mudamos de 'Verão' para 'Inverno', a ddp (110V ou 220V) não muda: a resistência diminui para a potência aumentar!"
            ),

            // ========================================================
            // ONDULATÓRIA
            // ========================================================
            "fis_m4_a1" to PhysicsDidacticEntry(
                theoryMarkdown = """
                    # Fundamentos da Ondulatória e a Equação Fundamental
                    
                    Uma onda é uma perturbação periódica que **transporta energia e quantidade de movimento, mas NUNCA transporta matéria**.
                    
                    ### Classificação das Ondas
                    1. **Quanto à Natureza:**
                       • **Ondas Mecânicas:** precisam de meio material para se propagar (ex: som, ondas no mar, terremotos). Não se propagam no vácuo!
                       • **Ondas Eletromagnéticas:** resultam da oscilação de campos elétricos e magnéticos perpendiculares. Propagam-se no vácuo com velocidade \( c \\approx 300.000 \\text{ km/s} \) (ex: luz visível, micro-ondas, raio X, rádio, Wi-Fi).
                    2. **Quanto à Direção de Vibração:**
                       • **Transversais:** vibração perpendicular à direção de propagação (ex: luz, corda).
                       • **Longitudinais:** vibração paralela à propagação (ex: som no ar).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação Fundamental da Ondulatória",
                        syntax = "v = \\lambda \\cdot f",
                        description = "Relaciona a velocidade da onda, seu comprimento e sua frequência.",
                        parametersInfo = "v em m/s, λ em metros, f em Hertz (Hz = 1/s)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Relação Período e Frequência",
                        syntax = "f = \\frac{1}{T} \\quad \\iff \\quad T = \\frac{1}{f}",
                        description = "Período T é o tempo de um ciclo; frequência f é a quantidade de ciclos por segundo.",
                        parametersInfo = "A frequência depende exclusivamente da FONTE emissora da onda!"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Cálculo de Comprimento de Onda de Rádio FM",
                        codeOrFormula = "v = \\lambda \\cdot f \\implies \\lambda = v / f",
                        explanation = "Uma emissora emite em 100 MHz (100 × 10⁶ Hz) no vácuo (v = 3 × 10⁸ m/s). O comprimento é: λ = (3 × 10⁸) / (100 × 10⁶) = 3 metros."
                    )
                ),
                imageResId = R.drawable.grafico_onda_senoidal,
                goldTip = "Quando uma onda muda de meio (Refração), a sua velocidade e o seu comprimento de onda mudam, mas a FREQUÊNCIA PERMANECE SEMPRE A MESMA!"
            )
        )
    }
}
