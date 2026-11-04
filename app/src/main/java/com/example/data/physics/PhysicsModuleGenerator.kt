package com.example.data.physics

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Gerador Dinâmico e Didático de Física ENEM para todas as aulas
 * dos 6 módulos essenciais: Mecânica, Eletricidade, Termologia, Ondulatória, Óptica e Física Moderna.
 */
object PhysicsModuleGenerator {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        val formulas = when {
            lesson.moduleId == "mod_fis_01_mecanica" -> listOf(
                FormulaOrSyntaxItem("Função Horária do MRUV", "S = S₀ + v₀·t + (a·t²)/2", "Equação do movimento uniformemente variado"),
                FormulaOrSyntaxItem("Equação de Torricelli", "v² = v₀² + 2·a·ΔS", "Cálculo sem variável de tempo"),
                FormulaOrSyntaxItem("2ª Lei de Newton (Princípio Fundamental)", "F_res = m · a", "Força resultante e aceleração")
            )
            lesson.moduleId == "mod_fis_02_eletricidade" -> listOf(
                FormulaOrSyntaxItem("1ª Lei de Ohm", "U = R · i", "Tensão (V), Resistência (Ω) e Corrente (A)"),
                FormulaOrSyntaxItem("Potência Elétrica e Consumo", "P = U · i = R · i² = U² / R", "E = P · Δt em kWh"),
                FormulaOrSyntaxItem("Associação de Resistores", "Série: R_eq = ΣR_i  |  Paralelo: 1/R_eq = Σ(1/R_i)", "Divisão de tensão vs corrente")
            )
            lesson.moduleId == "mod_fis_03_termologia" -> listOf(
                FormulaOrSyntaxItem("Calor Sensível (Equação Fundamental)", "Q = m · c · Δθ", "Variação térmica sem mudança de fase"),
                FormulaOrSyntaxItem("Calor Latente de Mudança de Fase", "Q = m · L", "Fusão e vaporização a temperatura constante"),
                FormulaOrSyntaxItem("1ª Lei da Termodinâmica", "ΔU = Q - W", "Conservação da energia térmica em trabalho mecânico")
            )
            lesson.moduleId == "mod_fis_04_ondulatoria" -> listOf(
                FormulaOrSyntaxItem("Equação Fundamental da Ondulatória", "v = λ · f", "Velocidade, comprimento de onda e frequência"),
                FormulaOrSyntaxItem("Período e Frequência", "f = 1 / T", "Frequência constante determinada exclusivamente pela fonte"),
                FormulaOrSyntaxItem("Efeito Doppler", "f_obs = f_fonte · (v ± v_obs) / (v ∓ v_fonte)", "Variação aparente de frequência com aproximação/afastamento")
            )
            lesson.moduleId == "mod_fis_05_optica" -> listOf(
                FormulaOrSyntaxItem("Lei de Snell-Descartes da Refração", "n₁ · sen(θ₁) = n₂ · sen(θ₂)", "Desvio da trajetória da luz entre meios"),
                FormulaOrSyntaxItem("Equação de Gauss para Espelhos e Lentes", "1/f = 1/p + 1/p'", "Foco, distância do objeto e distância da imagem"),
                FormulaOrSyntaxItem("Índice de Refração Absoluto", "n = c / v", "Razão entre velocidade no vácuo e no meio")
            )
            else -> listOf(
                FormulaOrSyntaxItem("Efeito Fotoelétrico de Einstein", "E_fóton = h · f = Φ + E_cin", "Dualidade onda-partícula e energia do fóton"),
                FormulaOrSyntaxItem("Equivalência Massa-Energia de Einstein", "E = m · c²", "Relação fundamental da Relatividade Restrita")
            )
        }

        val assetImage = when (lesson.moduleId) {
            "mod_fis_01_mecanica" -> when {
                lesson.id.contains("a1") || lesson.id.contains("a2") -> "images/fisica/cinematica.png"
                lesson.id.contains("a3") -> "images/fisica/dinamica.png"
                lesson.id.contains("a4") -> "images/fisica/energia.png"
                lesson.id.contains("a5") -> "images/fisica/colisoes.png"
                lesson.id.contains("a6") -> "images/fisica/estatica.png"
                lesson.id.contains("a7") -> "images/fisica/gravitacao.png"
                lesson.id.contains("a8") -> "images/fisica/hidrostatica.png"
                else -> "images/fisica/cinematica.png"
            }
            else -> null
        }

        return CalculusDidacticBundle(
            theoryMarkdown = """
                # Física ENEM: ${lesson.title}
                
                ### Subtópico: ${lesson.subtopic}
                
                No ENEM, a prova de Física prioriza a compreensão conceitual dos fenômenos cotidianos, a leitura crítica de grandezas físicas e a modelagem matemática de situações práticas.
                
                ### Fundamentação Teórica
                A física estuda as leis fundamentais que regem a matéria, a energia, o espaço e o tempo. Para dominar este tema no ENEM:
                • Identifique sempre as grandezas fornecidas no Sistema Internacional de Unidades (SI).
                • Verifique se há conversão de unidades necessária (ex: km/h para m/s dividindo por 3,6; kWh para Joules multiplicando por 3,6 × 10⁶).
                • Analise o princípio de conservação aplicável (Conservação da Energia, Conservação da Carga ou Conservação da Quantidade de Movimento).
            """.trimIndent(),
            formulas = formulas,
            solvedExamples = listOf(
                LessonExample(
                    title = "Aplicação Direta no Padrão ENEM",
                    codeOrFormula = "Identificação das variáveis e resolução passo a passo.",
                    explanation = "Isola-se a incógnita desejada, substituem-se os dados em unidades homogêneas do SI e calcula-se a magnitude com atenção às ordens de grandeza."
                )
            ),
            goldTip = "No ENEM de Física, a maior armadilha são as unidades de medida! Confira sempre se o tempo está em segundos ou horas antes de calcular.",
            assetImagePath = assetImage
        )
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        val q1 = MathExercise(
            id = "${lesson.id}_q1",
            title = "Aplicação ENEM: ${lesson.subtopic}",
            problemStatement = "Em um experimento de física aplicada relacionado a ${lesson.subtopic}, um estudante analisa o comportamento das grandezas envolvidas. A relação correta e consistente com as leis físicas estudadas é:",
            options = listOf(
                "a energia mecânica total em um sistema conservativo é sempre variável a cada instante de tempo.",
                "a variação das grandezas físicas respeita os princípios fundamentais de conservação de energia e momento.",
                "a velocidade da luz no vácuo diminui linearmente quando a frequência da onda eletromagnética aumenta.",
                "a resistência elétrica de um condutor independe de sua resistividade e de seu comprimento físico.",
                "o calor sempre flui espontaneamente de corpos de menor temperatura para corpos de maior temperatura."
            ),
            correctIndex = 1,
            hint = "Lembre-se dos princípios fundamentais de conservação da física.",
            stepByStepSolution = "Alternativa correta é a letra B.\n\n" +
                    "Análise Passo a Passo:\n" +
                    "• Alternativa A está incorreta: Em sistemas conservativos (sem forças dissipativas como atrito ou resistência do ar), a energia mecânica total permanece rigorosamente constante.\n" +
                    "• Alternativa B está correta: Os princípios de conservação da energia e da quantidade de movimento são pilares universais de todas as transformações físicas.\n" +
                    "• Alternativa C está incorreta: A velocidade da luz no vácuo é uma constante universal (c ≈ 3 × 10⁸ m/s) invariante para qualquer frequência.\n" +
                    "• Alternativa D está incorreta: Pela 2ª Lei de Ohm (R = ρ·L/A), a resistência depende diretamente da resistividade ρ e do comprimento L.\n" +
                    "• Alternativa E está incorreta: Pela 2ª Lei da Termodinâmica, o fluxo espontâneo de calor ocorre exclusivamente do corpo mais quente para o mais frio."
        )

        return listOf(q1)
    }
}
