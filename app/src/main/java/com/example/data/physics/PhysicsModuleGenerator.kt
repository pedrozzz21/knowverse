package com.example.data.physics

import com.example.data.math.CalculusDidacticBundle
import com.example.data.math.DidacticContentStatus
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Gerador de Conteúdo Didático e Exercícios Especializados para a Trilha de Física ENEM.
 * Fornece fundamentação teórica, fórmulas do SI e exercícios contextualizados por grande área da Física.
 */
object PhysicsModuleGenerator {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        val mod = lesson.moduleId.lowercase()
        val sub = lesson.subtopic.lowercase()

        val (theory, formulas, example, goldTip) = when {
            mod.contains("eletro") || sub.contains("corrente") || sub.contains("circuito") || sub.contains("resistor") -> {
                Quad(
                    theory = """
                        # Eletrodinâmica ENEM: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Compreender os conceitos de corrente elétrica (\( i \)), diferença de potencial (\( U \)) e resistência (\( R \)).
                        • Aplicar as Leis de Ohm e a conservação de energia em circuitos simples e mistos.
                        • Calcular consumo de energia elétrica em kWh para aparelhos residenciais.
                        
                        ### Fundamentação Teórica
                        A eletrodinâmica é o tema mais frequente da física no ENEM. A 1ª Lei de Ohm estabelece que \( U = R \cdot i \). A potência elétrica dissipada ou consumida é dada por \( P = U \cdot i \). O consumo em kWh resulta de \( E = P(\text{kW}) \cdot \Delta t(\text{h}) \).
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("1ª Lei de Ohm", "U = R · i", "Tensão (V) é igual à resistência (Ω) vezes corrente (A)"),
                        FormulaOrSyntaxItem("Potência Elétrica", "P = U · i = R · i²", "Potência em Watts no circuito elétrico")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Consumo do Chuveiro",
                        codeOrFormula = "Potência P = 5.500 W = 5,5 kW, uso diário de 30 min (0,5 h) por 30 dias",
                        explanation = "Energia mensal = 5,5 kW · (0,5 · 30) h = 5,5 · 15 = 82,5 kWh."
                    ),
                    goldTip = "Para calcular a conta de luz no ENEM: converta Watts para kW (divida por 1.000) e tempo para horas!"
                )
            }
            mod.contains("onda") || sub.contains("onda") || sub.contains("som") || sub.contains("frequencia") -> {
                Quad(
                    theory = """
                        # Ondulatória e Acústica ENEM: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Caracterizar ondas mecânicas e eletromagnéticas quanto à natureza e direção de propagação.
                        • Aplicar a equação fundamental da ondulatória: \( v = \lambda \cdot f \).
                        • Diferenciar fenômenos ondulatórios: reflexão, refração, difração, interferência, ressonância e polarização.
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Equação Fundamental", "v = \\lambda · f", "Velocidade de propagação = comprimento de onda · frequência"),
                        FormulaOrSyntaxItem("Período e Frequência", "T = \\frac{1}{f}", "Relação inversa entre período (s) e frequência (Hz)")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Onda Sonora",
                        codeOrFormula = "Frequência f = 680 Hz no ar com v = 340 m/s",
                        explanation = "\\lambda = v / f = 340 / 680 = 0,5 metros = 50 centímetros."
                    ),
                    goldTip = "Lembre-se: quando uma onda muda de meio (refração), sua velocidade e comprimento de onda mudam, mas a FREQUÊNCIA permanece constante!"
                )
            }
            mod.contains("termo") || sub.contains("calor") || sub.contains("temperatura") || sub.contains("gas") -> {
                Quad(
                    theory = """
                        # Termologia e Calorimetria ENEM: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Diferenciar temperatura (agitação térmica média) de calor (energia térmica em trânsito).
                        • Calcular calor sensível (\( Q = m \cdot c \cdot \Delta T \)) e calor latente de mudança de estado (\( Q = m \cdot L \)).
                        • Analisar os três processos de transmissão de calor: condução, convecção e irradiação.
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Equação Fundamental da Calorimetria", "Q = m · c · \\Delta T", "Calor sensível para variação de temperatura sem mudança de fase"),
                        FormulaOrSyntaxItem("Calor Latente", "Q = m · L", "Calor trocado durante a transição de fase com temperatura constante")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Aquecimento de Água",
                        codeOrFormula = "m = 500 g de água, c = 1 cal/(g·°C), de 20 °C a 70 °C (ΔT = 50 °C)",
                        explanation = "Q = 500 · 1 · 50 = 25.000 calorias = 25 kcal."
                    ),
                    goldTip = "Irradiação térmica por ondas infravermelhas é o único processo de transferência de calor que ocorre no vácuo (como o calor do Sol que atinge a Terra)."
                )
            }
            mod.contains("optica") || sub.contains("luz") || sub.contains("espelho") || sub.contains("lente") -> {
                Quad(
                    theory = """
                        # Óptica Geométrica ENEM: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Compreender os princípios da propagação retilínea, independência e reversibilidade da luz.
                        • Aplicar as leis de reflexão em espelhos planos e esféricos (côncavos e convexos).
                        • Interpretar a Lei de Snell-Descartes da refração e o fenômeno da reflexão interna total (fibras ópticas).
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("Índice de Refração", "n = \\frac{c}{v}", "Razão entre a velocidade da luz no vácuo e no meio"),
                        FormulaOrSyntaxItem("Lei de Snell-Descartes", "n_1 · \\sin(\\theta_1) = n_2 · \\sin(\\theta_2)", "Relação angular entre o raio incidente e o refratado")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Reflexão Total",
                        codeOrFormula = "Luz passando do meio mais refringente (vidro) para o menos refringente (ar)",
                        explanation = "Ocorre reflexão total quando o ângulo de incidência é superior ao ângulo limite L, princípio base das telecomunicações por fibra óptica."
                    ),
                    goldTip = "Espelhos convexos e lentes divergentes SEMPRE formam imagens virtuais, direitas e menores que o objeto."
                )
            }
            else -> {
                Quad(
                    theory = """
                        # Mecânica Clássica ENEM: ${lesson.title}
                        
                        ### Objetivos Pedagógicos
                        • Descrever movimentos através de gráficos posição-tempo e velocidade-tempo.
                        • Aplicar as Leis de Newton para determinar forças resultantes e acelerações.
                        • Utilizar o Teorema da Conservação da Energia Mecânica em sistemas livres de atrito dissipativo.
                    """.trimIndent(),
                    formulas = listOf(
                        FormulaOrSyntaxItem("2ª Lei de Newton", "F_R = m · a", "A força resultante produz aceleração proporcional à massa do corpo"),
                        FormulaOrSyntaxItem("Energia Cinética", "E_c = \\frac{m · v^2}{2}", "Energia associada à velocidade de um corpo em movimento")
                    ),
                    example = LessonExample(
                        title = "Exemplo ENEM: Conservação da Energia",
                        codeOrFormula = "Objeto de m = 2 kg solto do repouso de uma altura h = 5 m (g = 10 m/s²)",
                        explanation = "E_p = m · g · h = 2 · 10 · 5 = 100 J. Ao atingir o solo, toda energia potencial converteu-se em cinética: v = √(2 · g · h) = √(100) = 10 m/s."
                    ),
                    goldTip = "Em gráficos de velocidade versus tempo (v × t), a área sob a curva representa numericamente a distância percorrida (ΔS)."
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
            mod.contains("eletro") || sub.contains("corrente") || sub.contains("circuito") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Eletrodinâmica",
                    problemStatement = "Um resistor ôhmico de resistência constante igual a 20 Ω é submetido a uma diferença de potencial de 120 V. A intensidade da corrente elétrica que o atravessa e a potência dissipada por efeito Joule são, respectivamente:",
                    options = listOf(
                        "6 A e 720 W",
                        "6 A e 2.400 W",
                        "0,16 A e 720 W",
                        "12 A e 1.440 W",
                        "2,4 A e 288 W"
                    ),
                    correctIndex = 0,
                    hint = "Aplique a 1ª Lei de Ohm: i = U / R. Para a potência: P = U · i.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. Pela 1ª Lei de Ohm: i = U / R = 120 V / 20 Ω = 6 A.\n" +
                            "2. Potência dissipada: P = U · i = 120 V · 6 A = 720 W (ou P = R · i² = 20 · 36 = 720 W)."
                )
            }
            mod.contains("onda") || sub.contains("onda") || sub.contains("som") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Ondulatória",
                    problemStatement = "Uma estação de rádio FM transmite um sinal eletromagnético com frequência de 100 MHz (100 × 10⁶ Hz). Sabendo que as ondas eletromagnéticas se propagam com velocidade c = 3 × 10⁸ m/s, o comprimento de onda emitido é de:",
                    options = listOf(
                        "3,0 metros",
                        "0,3 metros",
                        "30 metros",
                        "300 metros",
                        "0,03 metros"
                    ),
                    correctIndex = 0,
                    hint = "Utilize a equação fundamental da ondulatória: v = λ · f, isolando λ = v / f.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. v = λ · f ⇒ λ = v / f.\n" +
                            "2. λ = (3 × 10⁸ m/s) / (100 × 10⁶ Hz) = (3 × 10⁸) / (1 × 10⁸) = 3,0 metros."
                )
            }
            mod.contains("termo") || sub.contains("calor") || sub.contains("temperatura") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Calorimetria",
                    problemStatement = "Para aquecer 200 g de água líquida (calor específico c = 1,0 cal/(g·°C)) de 25 °C para 75 °C, a quantidade de calor que deve ser fornecida ao sistema é de:",
                    options = listOf(
                        "10.000 calorias (10 kcal)",
                        "15.000 calorias (15 kcal)",
                        "5.000 calorias (5 kcal)",
                        "20.000 calorias (20 kcal)",
                        "50.000 calorias (50 kcal)"
                    ),
                    correctIndex = 0,
                    hint = "Use a equação fundamental do calor sensível: Q = m · c · ΔT, onde ΔT = 75 - 25 = 50 °C.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. Variação de temperatura: ΔT = 75 °C - 25 °C = 50 °C.\n" +
                            "2. Q = m · c · ΔT = 200 g · 1,0 cal/(g·°C) · 50 °C = 10.000 calorias = 10 kcal."
                )
            }
            mod.contains("optica") || sub.contains("luz") || sub.contains("espelho") -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Óptica e Refração",
                    problemStatement = "Um feixe de luz monocromática passa do ar (índice de refração n₁ = 1,0) para um bloco de vidro com velocidade v = 2 × 10⁸ m/s. Sendo a velocidade da luz no vácuo c = 3 × 10⁸ m/s, o índice de refração do vidro é:",
                    options = listOf(
                        "1,5",
                        "1,33",
                        "2,0",
                        "0,67",
                        "1,2"
                    ),
                    correctIndex = 0,
                    hint = "O índice de refração absoluto é a razão entre a velocidade da luz no vácuo e no meio: n = c / v.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. n = c / v = (3 × 10⁸ m/s) / (2 × 10⁸ m/s) = 3 / 2 = 1,5."
                )
            }
            else -> {
                MathExercise(
                    id = "${lesson.id}_q1",
                    title = "Aplicação ENEM: Cinemática e Movimento",
                    problemStatement = "Um veículo trafega em uma rodovia com velocidade constante de 72 km/h. Convertendo para unidades do Sistema Internacional (SI), a distância percorrida por esse veículo em 10 segundos é de:",
                    options = listOf(
                        "200 metros",
                        "720 metros",
                        "150 metros",
                        "250 metros",
                        "180 metros"
                    ),
                    correctIndex = 0,
                    hint = "Converta km/h para m/s dividindo por 3,6 (72 / 3,6 = 20 m/s). Em seguida aplique ΔS = v · Δt.",
                    stepByStepSolution = "Alternativa correta: Letra A.\n\n" +
                            "Cálculo Passo a Passo:\n" +
                            "1. Conversão para o SI: v = 72 km/h ÷ 3,6 = 20 m/s.\n" +
                            "2. Distância percorrida em MRU: ΔS = v · Δt = 20 m/s · 10 s = 200 metros."
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
