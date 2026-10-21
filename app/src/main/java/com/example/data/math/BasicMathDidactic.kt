package com.example.data.math

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado do Ciclo de Matemática Básica (ENEM).
 * Módulo: Matemática Básica
 * Submódulos:
 *  1. Operações com Frações e Decimais (mb_l1)
 *  2. MMC e MDC - Múltiplos e Divisores (mb_l2)
 *  3. Potenciação e Radiciação (mb_l3)
 *  4. Notação Científica e Ordem de Grandeza (mb_l4)
 *  5. Conversão de Unidades (mb_l5)
 *  6. Razão, Proporção e Grandezas (mb_l6)
 *  7. Regra de Três Simples e Composta (mb_l7)
 *  8. Escalas Cartográficas (mb_l8)
 *  9. Porcentagem - Aumentos e Descontos Sucessivos (mb_l9)
 */
object BasicMathDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "mb_l1" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 1. Operações com Frações e Decimais
                    Este módulo consolida a manipulação aritmética essencial para provas e problemas aplicados do cotidiano.
                    
                    ### A. Soma e Subtração de Frações
                    • **Denominadores Iguais:** Mantém-se o denominador e somam-se ou subtraem-se os numeradores diretamente.
                    • **Denominadores Diferentes:** É obrigatório encontrar o Mínimo Múltiplo Comum (MMC) dos denominadores para unificar a base antes de operar.
                    
                    ### B. Multiplicação e Divisão de Frações
                    • **Multiplicação:** Multiplica-se numerador por numerador e denominador por denominador: \( \frac{a}{b} \times \frac{c}{d} = \frac{a \cdot c}{b \cdot d} \).
                    • **Divisão:** Conserva-se a primeira fração e multiplica-se pelo inverso da segunda fração: \( \frac{a}{b} \div \frac{c}{d} = \frac{a}{b} \times \frac{d}{c} \).
                    
                    ### C. Operações com Números Decimais
                    • **Adição e Subtração:** Alinhar estritamente vírgula sob vírgula.
                    • **Multiplicação:** Realizar a conta ignorando as vírgulas e, no resultado final, posicionar a vírgula somando a quantidade total de casas decimais dos fatores.
                    • **Divisão:** Igualar o número de casas decimais do dividendo e do divisor adicionando zeros e, em seguida, eliminar a vírgula para prosseguir com a divisão normal.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Soma/Subtração com MMC",
                        syntax = "\\frac{a}{b} \\pm \\frac{c}{d} = \\frac{(MMC/b)\\cdot a \\pm (MMC/d)\\cdot c}{MMC}",
                        description = "Unificação de denominadores diferentes através do Mínimo Múltiplo Comum.",
                        parametersInfo = "b ≠ 0 e d ≠ 0"
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Divisão de Frações",
                        syntax = "\\frac{a}{b} \\div \\frac{c}{d} = \\frac{a}{b} \\times \\frac{d}{c} = \\frac{a \\cdot d}{b \\cdot c}",
                        description = "Conservar a primeira fração e multiplicar pelo inverso multiplicativo da segunda.",
                        parametersInfo = "c ≠ 0 e d ≠ 0"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Volume de Água no Recipiente",
                        codeOrFormula = "2,75 - \\frac{3}{4} + 1,2 = 3,20\\text{ litros}",
                        explanation = """
                            Enunciado: Um recipiente continha 2,75 litros de água. Foram retirados 3/4 de litro e adicionou-se 1,2 litro ao volume restante. Qual é o volume final?
                            
                            Passo 1: Converter a fração 3/4 para número decimal para igualar a unidade:
                            3/4 = 3 ÷ 4 = 0,75 litro.
                            
                            Passo 2: Subtrair a quantidade retirada do volume inicial:
                            2,75 - 0,75 = 2,00 litros.
                            
                            Passo 3: Adicionar o volume inserido posteriormente:
                            2,00 + 1,20 = 3,20 litros.
                            
                            Resultado Final: O volume final no recipiente é de 3,2 litros.
                        """.trimIndent()
                    )
                ),
                goldTip = "Em problemas mistos com frações e decimais, converta frações simples (1/2 = 0,5; 1/4 = 0,25; 3/4 = 0,75) para decimais para ganhar agilidade de cálculo!"
            )

            "mb_l2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 2. MMC e MDC (Múltiplos e Divisores)
                    Fundamentos de divisibilidade e fatoração aplicados à resolução de problemas práticos.
                    
                    ### A. Mínimo Múltiplo Comum (MMC)
                    • **Conceito:** Representa o menor número inteiro positivo (diferente de zero) que é múltiplo simultâneo de dois ou mais números.
                    • **Aplicação Prática:** Problemas de sincronicidade, ciclos que se repetem periodicamente ou encontros futuros (ex: semáforos piscando juntos, remédios tomados em horários simultâneos, corredores em pista circular).
                    
                    ### B. Máximo Divisor Comum (MDC)
                    • **Conceito:** Representa o maior número inteiro que divide exatamente dois ou mais números dados sem deixar resto.
                    • **Aplicação Prática:** Problemas de repartição, cortes em partes iguais com o maior tamanho possível e sem sobras (ex: corte de tecidos, divisão de grupos de alunos, organização de kits).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Cálculo do MMC",
                        syntax = "\\text{MMC}(a, b) = \\prod p_i^{\\max(\\alpha_i, \\beta_i)}",
                        description = "Produto de todos os fatores primos (comuns e não comuns) elevados aos seus maiores expoentes.",
                        parametersInfo = "Utilizado para determinar períodos de repetição e encontros simultâneos."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Cálculo do MDC",
                        syntax = "\\text{MDC}(a, b) = \\prod p_i^{\\min(\\alpha_i, \\beta_i)}",
                        description = "Produto apenas dos fatores primos comuns elevados aos seus menores expoentes.",
                        parametersInfo = "Utilizado para repartições de máxima dimensão sem desperdício."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático (MMC): Pista Circular e Horário de Encontro",
                        codeOrFormula = "\\text{MMC}(12, 18) = 36\\text{ min} \\implies 08h00 + 36\\text{ min} = 08h36",
                        explanation = """
                            Enunciado: O corredor A completa uma volta em 12 minutos e o corredor B em 18 minutos. Partindo juntos às 08h00, a que horas se encontrarão novamente?
                            
                            Passo 1: Decomposição simultânea em fatores primos:
                            12, 18 | 2
                             6,  9 | 2
                             3,  9 | 3
                             1,  3 | 3
                             1,  1 | MMC = 2² × 3² = 4 × 9 = 36 minutos.
                            
                            Passo 2: Somar os 36 minutos ao horário inicial:
                            08h00 + 36 min = 08h36.
                            
                            Resultado Final: Os corredores se encontrarão às 08h36.
                        """.trimIndent()
                    ),
                    LessonExample(
                        title = "Exemplo Prático (MDC): Corte Máximo de Rolos de Fita",
                        codeOrFormula = "\\text{MDC}(120, 180, 240) = 60\\text{ cm} \\implies 2 + 3 + 4 = 9\\text{ pedaços}",
                        explanation = """
                            Enunciado: Fitas de 120 cm, 180 cm e 240 cm serão cortadas em pedaços iguais de tamanho máximo sem sobras. Qual o tamanho e o total de pedaços?
                            
                            Passo 1: Fatoração:
                            120 = 2³ × 3 × 5
                            180 = 2² × 3² × 5
                            240 = 2⁴ × 3 × 5
                            MDC = 2² × 3¹ × 5¹ = 4 × 3 × 5 = 60 cm.
                            
                            Passo 2: Total de pedaços por rolo:
                            Fita 1: 120 ÷ 60 = 2 pedaços
                            Fita 2: 180 ÷ 60 = 3 pedaços
                            Fita 3: 240 ÷ 60 = 4 pedaços
                            Total = 2 + 3 + 4 = 9 pedaços.
                            
                            Resultado Final: Cada pedaço medirá 60 cm e serão obtidos 9 pedaços no total.
                        """.trimIndent()
                    )
                ),
                goldTip = "Decore para o ENEM: se a pergunta fala em 'de quanto em quanto tempo vai acontecer de novo', calcule MMC. Se fala em 'dividir no maior tamanho possível sem sobras', calcule MDC."
            )

            "mb_l3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 3. Potenciação e Radiciação
                    Regras operatórias fundamentais para simplificação de expressões algébricas e numéricas.
                    
                    ### A. Propriedades de Potências
                    • **Multiplicação de Mesma Base:** Conserva a base e soma os expoentes: \( a^m \times a^n = a^{m+n} \).
                    • **Divisão de Mesma Base:** Conserva a base e subtrai os expoentes: \( a^m \div a^n = a^{m-n} \).
                    • **Potência de Potência:** Conserva a base e multiplica os expoentes: \( (a^m)^n = a^{m \cdot n} \).
                    • **Expoente Negativo:** Inverte a base e torna o expoente positivo: \( a^{-n} = \frac{1}{a^n} \).
                    • **Expoente Fracionário (Raiz):** Conversão entre potência e radical: \( a^{\frac{m}{n}} = \sqrt[n]{a^m} \).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra do Expoente Fracionário",
                        syntax = "a^{\\frac{m}{n}} = \\sqrt[n]{a^m}",
                        description = "O numerador vira potência interna e o denominador vira o índice da raiz (quem está na sombra vai para o sol).",
                        parametersInfo = "a > 0 e n inteiro positivo"
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Inversão com Expoente Negativo",
                        syntax = "a^{-n} = \\frac{1}{a^n} \\quad \\text{e} \\quad \\left(\\frac{a}{b}\\right)^{-n} = \\left(\\frac{b}{a}\\right)^n",
                        description = "Inverter a fração para trocar o sinal do expoente.",
                        parametersInfo = "a ≠ 0 e b ≠ 0"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Simplificação na Base 2",
                        codeOrFormula = "E = \\frac{2^5 \\times 8^{-2}}{4^{-3}} = 2^5 = 32",
                        explanation = """
                            Enunciado: Simplifique a expressão numérica E = (2⁵ × 8⁻²) / 4⁻³ em uma única potência de base 2.
                            
                            Passo 1: Reescrever 8 e 4 como potências de base 2:
                            8 = 2³ => 8⁻² = (2³)⁻² = 2⁻⁶.
                            4 = 2² => 4⁻³ = (2²)⁻³ = 2⁻⁶.
                            
                            Passo 2: Substituir na expressão original:
                            E = (2⁵ × 2⁻⁶) / 2⁻⁶.
                            
                            Passo 3: Simplificar o numerador somando os expoentes:
                            2⁵ × 2⁻⁶ = 2^(5 - 6) = 2⁻¹.
                            
                            Passo 4: Dividir subtraindo os expoentes:
                            E = 2⁻¹ / 2⁻⁶ = 2^(-1 - (-6)) = 2^(-1 + 6) = 2⁵ = 32.
                            
                            Resultado Final: A expressão equivale a 2⁵ (ou 32).
                        """.trimIndent()
                    )
                ),
                goldTip = "Ao simplificar expressões de potências com bases diferentes, converta todas as bases para seus fatores primos (4 -> 2², 8 -> 2³, 9 -> 3², 27 -> 3³)."
            )

            "mb_l4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 4. Notação Científica e Ordem de Grandeza
                    Ferramenta matemática indispensável para expressar valores astronômicos ou microscópicos.
                    
                    ### A. Notação Científica
                    Um número real positivo está em notação científica quando escrito na forma:
                    \[ a \times 10^n \]
                    Onde:
                    • **Mantissa (\(a\)):** Número real tal que \( 1 \le a < 10 \).
                    • **Ordem (\(n\)):** Número inteiro (\( n \in \mathbb{Z} \)).
                    
                    • **Deslocamento da vírgula:**
                      - Deslocar para a **esquerda**: o expoente \(n\) **aumenta**.
                      - Deslocar para a **direita**: o expoente \(n\) **diminui**.
                    
                    ### B. Ordem de Grandeza
                    Representa a potência de 10 mais próxima da grandeza:
                    • Compara-se a mantissa \(a\) com a média geométrica dos extremos: \( \sqrt{10} \approx 3,16 \).
                    • Se \( a < 3,16 \): a ordem de grandeza é \( 10^n \).
                    • Se \( a \ge 3,16 \): a ordem de grandeza é \( 10^{n+1} \).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Critério de Ordem de Grandeza",
                        syntax = "OG = 10^n \\; (a < 3,16) \\quad \\text{ou} \\quad OG = 10^{n+1} \\; (a \\ge 3,16)",
                        description = "Comparação da mantissa com a raiz de 10 para arredondamento exponencial da magnitude.",
                        parametersInfo = "√10 ≈ 3,16227..."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Distância Terra-Sol e Diâmetro do Átomo",
                        codeOrFormula = "1,496 \\times 10^8\\text{ km} \\quad \\text{e} \\quad 1,06 \\times 10^{-10}\\text{ m} \\implies OG = 10^{-10}\\text{ m}",
                        explanation = """
                            Enunciado: Escreva 149.600.000 km e 0,000000000106 m em notação científica e determine a ordem de grandeza do diâmetro do átomo.
                            
                            Passo 1: Distância Terra-Sol (149.600.000 km):
                            Deslocar a vírgula 8 casas para a esquerda para obter mantissa 1,496:
                            1,496 × 10⁸ km.
                            
                            Passo 2: Diâmetro do átomo (0,000000000106 m):
                            Deslocar a vírgula 10 casas para a direita para obter mantissa 1,06:
                            1,06 × 10⁻¹⁰ m.
                            
                            Passo 3: Ordem de grandeza do diâmetro:
                            A mantissa é a = 1,06. Como 1,06 < 3,16, mantém-se 10⁻¹⁰ m.
                            
                            Resultado Final: 1,496 × 10⁸ km e 1,06 × 10⁻¹⁰ m; Ordem de grandeza = 10⁻¹⁰ m.
                        """.trimIndent()
                    )
                ),
                goldTip = "Cuidado com o valor de corte! No Brasil e no ENEM, o critério oficial adota √10 ≈ 3,16 como limite para somar 1 ao expoente."
            )

            "mb_l5" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 5. Conversão de Unidades
                    O domínio da mudança de unidades é um dos tópicos de maior incidência no ENEM e vestibulares.
                    
                    ### A. Sistema Métrico Linear
                    \[ \text{km} \leftrightarrow \text{hm} \leftrightarrow \text{dam} \leftrightarrow \text{m} \leftrightarrow \text{dm} \leftrightarrow \text{cm} \leftrightarrow \text{mm} \]
                    • Cada salto para a direita multiplica por 10.
                    • Cada salto para a esquerda divide por 10.
                    
                    ### B. Área e Volume
                    • **Área (\(\text{m}^2\)):** Cada salto multiplica ou divide por \( 100 \) (\(10^2\)).
                    • **Volume (\(\text{m}^3\)):** Cada salto multiplica ou divide por \( 1.000 \) (\(10^3\)).
                    
                    ### C. Relações Fundamentais de Capacidade
                    • \( 1\text{ dm}^3 = 1\text{ litro} \)
                    • \( 1\text{ m}^3 = 1.000\text{ litros} \)
                    • \( 1\text{ cm}^3 = 1\text{ mL} \)
                    
                    ### D. Conversão de Velocidade
                    • De \(\text{km/h}\) para \(\text{m/s}\): **divide por 3,6**.
                    • De \(\text{m/s}\) para \(\text{km/h}\): **multiplica por 3,6**.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equivalências de Volume e Litros",
                        syntax = "1\\text{ m}^3 = 1.000\\text{ L}, \\quad 1\\text{ dm}^3 = 1\\text{ L}, \\quad 1\\text{ cm}^3 = 1\\text{ mL}",
                        description = "Relações fundamentais entre geometria espacial e medidas de capacidade.",
                        parametersInfo = "1.000 L equivalem a uma caixa de 1 metro de lado."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Conversão de Velocidade",
                        syntax = "v_{(\\text{m/s})} = \\frac{v_{(\\text{km/h})}}{3,6} \\iff v_{(\\text{km/h})} = v_{(\\text{m/s})} \\times 3,6",
                        description = "Fator 3,6 originado de 3.600 s / 1.000 m.",
                        parametersInfo = "72 km/h = 20 m/s; 90 km/h = 25 m/s."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Volume de Caixa d'Água e Vazamento",
                        codeOrFormula = "\\frac{15.000\\text{ L}}{1.000} = 15\\text{ m}^3 \\quad \\text{e} \\quad \\frac{18\\text{ km/h}}{3,6} = 5\\text{ m/s}",
                        explanation = """
                            Enunciado: Uma caixa tem 15.000 L. Qual o volume em m³ e qual a velocidade equivalente em m/s de um vazamento a 18 km/h?
                            
                            Passo 1: Conversão de litros para m³:
                            Sabendo que 1 m³ = 1.000 L, dividimos:
                            15.000 L ÷ 1.000 = 15 m³.
                            
                            Passo 2: Conversão de km/h para m/s:
                            Divide-se por 3,6:
                            18 km/h ÷ 3,6 = 5 m/s.
                            
                            Resultado Final: O volume é 15 m³ e a velocidade é 5 m/s.
                        """.trimIndent()
                    )
                ),
                goldTip = "Em provas de física e matemática, velocidade em km/h quase sempre precisará ser convertida para m/s antes de calcular tempo ou distância!"
            )

            "mb_l6" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 6. Razão, Proporção e Grandezas
                    Relações quantitativas fundamentais para divisão de lucros, misturas químicas e taxas.
                    
                    ### A. Razão e Proporção
                    • **Razão:** Quociente entre dois números \( a \) e \( b \) (\( b \neq 0 \)), representado por \( \frac{a}{b} \).
                    • **Proporção:** Igualdade entre duas razões equivalentes:
                    \[ \frac{a}{b} = \frac{c}{d} \iff a \cdot d = b \cdot c \]
                    
                    ### B. Grandezas Diretamente Proporcionais
                    Quando o aumento de uma grandeza provoca o aumento da outra na mesma razão (se uma dobra, a outra dobra). A razão permanece constante:
                    \[ \frac{A}{B} = k \]
                    
                    ### C. Grandezas Inversamente Proporcionais
                    Quando o aumento de uma grandeza provoca a redução da outra na mesma proporção (se uma dobra, a outra cai pela metade). O produto permanece constante:
                    \[ A \cdot B = k \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Divisão Diretamente Proporcional",
                        syntax = "A = p_1 \\cdot k, \\quad B = p_2 \\cdot k \\implies k = \\frac{\\text{Total}}{p_1 + p_2}",
                        description = "Distribuição proporcional de um total utilizando a constante k de proporcionalidade.",
                        parametersInfo = "p1 e p2 são os pesos de cada parte."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Divisão de Lucro Proporcional ao Investimento",
                        codeOrFormula = "2k + 3k = 50.000 \\implies k = 10.000 \\implies \\text{Carlos: R$ 20.000 e Eduardo: R$ 30.000}",
                        explanation = """
                            Enunciado: Carlos investiu R$ 20.000 e Eduardo R$ 30.000. O lucro de R$ 50.000 será dividido proporcionalmente ao investimento. Quanto cabe a cada um?
                            
                            Passo 1: Razão entre os capitais:
                            20.000 / 30.000 = 2 / 3.
                            
                            Passo 2: Definir a constante k:
                            Carlos = 2k; Eduardo = 3k.
                            
                            Passo 3: Igualar a soma ao lucro total:
                            2k + 3k = 50.000 => 5k = 50.000 => k = 10.000.
                            
                            Passo 4: Calcular os valores:
                            Carlos: 2 × 10.000 = R$ 20.000,00.
                            Eduardo: 3 × 10.000 = R$ 30.000,00.
                            
                            Resultado Final: Carlos receberá R$ 20.000,00 e Eduardo R$ 30.000,00.
                        """.trimIndent()
                    )
                ),
                goldTip = "O método da constante k é o mais rápido e seguro para o ENEM: some os coeficientes (2 + 3 = 5), divida o total por 5 para achar k, e multiplique por cada peso!"
            )

            "mb_l7" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 7. Regra de Três (Simples e Composta)
                    Procedimento analítico para relacionar grandezas proporcionais diretas e inversas.
                    
                    ### A. Passo a Passo da Regra de Três
                    1. Identificar e listar as grandezas em colunas distintas.
                    2. Fixar a grandeza que contém a incógnita (\(x\)).
                    3. Comparar cada uma das outras grandezas com a grandeza do \(x\) para avaliar se são diretamente ou inversamente proporcionais (análise de setas).
                    4. Montar a equação mantendo a fração do \(x\) isolada de um lado e multiplicando as frações das demais grandezas do outro lado (invertendo as frações que forem inversamente proporcionais).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação Geral da Regra de Três Composta",
                        syntax = "\\frac{x_1}{x_2} = \\left(\\frac{A_1}{A_2}\\right)^{\\pm 1} \\times \\left(\\frac{B_1}{B_2}\\right)^{\\pm 1}",
                        description = "Montagem de proporção onde o expoente -1 inverte as grandezas inversamente proporcionais.",
                        parametersInfo = "Mantenha a fração da incógnita isolada no primeiro membro."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático (Simples Inversa): Velocidade e Tempo",
                        codeOrFormula = "\\frac{120}{80} = \\frac{3}{x} \\implies 120x = 240 \\implies x = 2\\text{ horas}",
                        explanation = """
                            Enunciado: A 80 km/h o percurso leva 3 horas. Quanto tempo levará a 120 km/h?
                            
                            Passo 1: Analisar proporcionalidade:
                            Aumentar a velocidade reduz o tempo gasto => Inversamente proporcional!
                            
                            Passo 2: Inverter a fração da velocidade:
                            120 / 80 = 3 / x.
                            
                            Passo 3: Simplificar e resolver:
                            3/2 = 3/x => 3x = 6 => x = 2 horas.
                            
                            Resultado Final: O tempo gasto será de 2 horas.
                        """.trimIndent()
                    ),
                    LessonExample(
                        title = "Exemplo Prático (Composta): Máquinas, Dias e Peças",
                        codeOrFormula = "\\frac{1200}{x} = \\frac{6}{10} \\times \\frac{8}{6} = \\frac{8}{10} \\implies 4x = 6000 \\implies x = 1500\\text{ peças}",
                        explanation = """
                            Enunciado: 6 máquinas em 8 dias produzem 1.200 peças. Quantas peças 10 máquinas produzem em 6 dias?
                            
                            Passo 1: Alinhar dados e comparar com Peças (x):
                            • Mais máquinas => Mais peças (Direta).
                            • Mais dias => Mais peças (Direta).
                            
                            Passo 2: Montar equação:
                            1200 / x = (6 / 10) × (8 / 6).
                            
                            Passo 3: Simplificar o 6:
                            1200 / x = 8 / 10 = 4 / 5.
                            4x = 1200 × 5 => 4x = 6000 => x = 1500 peças.
                            
                            Resultado Final: Serão produzidas 1.500 peças.
                        """.trimIndent()
                    )
                ),
                goldTip = "Pergunta mental para verificar proporcionalidade: 'Se eu DOBRAR a grandeza A, a grandeza com x vai dobrar (direta) ou cair pela metade (inversa)?'"
            )

            "mb_l8" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 8. Escalas Cartográficas
                    A escala (\(E\)) representa a razão constante entre a medida linear no desenho/mapa (\(d\)) e a medida real correspondente (\(D\)):
                    \[ E = \frac{d}{D} \]
                    
                    ### A. Regras Fundamentais
                    • **Unidades Idênticas:** O numerador (\(d\)) e o denominador (\(D\)) devem obrigatoriamente estar na mesma unidade de medida antes da simplificação (geralmente em centímetros).
                    • **Escala de Área:** \( E^2 = \frac{\text{Área no desenho}}{\text{Área real}} \).
                    • **Escala de Volume:** \( E^3 = \frac{\text{Volume no desenho}}{\text{Volume real}} \).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fórmula da Escala Linear",
                        syntax = "E = \\frac{d}{D} \\implies D = \\frac{d}{E}",
                        description = "Razão entre distância no mapa e distância real na mesma unidade.",
                        parametersInfo = "1 : 100.000 significa que 1 cm no mapa equivale a 100.000 cm = 1 km real."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Distância Real no Mapa 1 : 250.000",
                        codeOrFormula = "D = 8 \\times 250.000 = 2.000.000\\text{ cm} = 20.000\\text{ m} = 20\\text{ km}",
                        explanation = """
                            Enunciado: No mapa de escala 1 : 250.000, duas cidades distam 8 cm. Qual a distância real em km?
                            
                            Passo 1: Aplicar a fórmula da escala:
                            1 / 250.000 = 8 cm / D => D = 8 × 250.000 = 2.000.000 cm.
                            
                            Passo 2: Converter cm para metros (divide por 100):
                            2.000.000 ÷ 100 = 20.000 m.
                            
                            Passo 3: Converter metros para quilômetros (divide por 1.000):
                            20.000 ÷ 1.000 = 20 km.
                            
                            Resultado Final: A distância real entre as cidades é de 20 km.
                        """.trimIndent()
                    )
                ),
                goldTip = "Para passar de centímetros para quilômetros rapidamente, basta cortar 5 zeros (ou andar 5 casas decimais para a esquerda). 2.000.000 cm -> 20 km!"
            )

            "mb_l9" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 9. Porcentagem (Aumentos e Descontos Sucessivos)
                    Cálculo percentual e fatores multiplicativos acumulados.
                    
                    ### A. Conceito e Fatores de Variação
                    • **Representação:** \( p\% = \frac{p}{100} \).
                    • **Fator de Aumento:** Para aumentar um valor em uma taxa \(i\) (decimal), multiplica-se por \( (1 + i) \).
                    • **Fator de Desconto:** Para diminuir um valor em uma taxa \(i\) (decimal), multiplica-se por \( (1 - i) \).
                    
                    ### B. Aumentos e Descontos Sucessivos
                    Multiplica-se o valor inicial pelo encadeamento dos fatores:
                    \[ V_{\text{final}} = V_{\text{inicial}} \times (1 \pm i_1) \times (1 \pm i_2) \times \dots \]
                    O efeito percentual real NUNCA é a soma direta das porcentagens, pois incide sobre bases já reajustadas!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fator Multiplicativo Acumulado",
                        syntax = "F_{\\text{total}} = (1 \\pm i_1) \\times (1 \\pm i_2) \\times \\dots \\times (1 \\pm i_n)",
                        description = "Produto dos fatores individuais de aumento (+) ou desconto (-).",
                        parametersInfo = "A variação final percentual é (F_total - 1) × 100%."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Aumento de 10% seguido de Desconto de 20%",
                        codeOrFormula = "F = 1,10 \\times 0,80 = 0,88 \\implies V = 200 \\times 0,88 = R$ 176,00 (-12%)",
                        explanation = """
                            Enunciado: Produto de R$ 200,00 sofre aumento de 10% e depois desconto de 20%. Qual o preço final e a variação total?
                            
                            Passo 1: Fator de aumento de 10%:
                            (1 + 0,10) = 1,10.
                            
                            Passo 2: Fator de desconto de 20%:
                            (1 - 0,20) = 0,80.
                            
                            Passo 3: Fator total acumulado:
                            F = 1,10 × 0,80 = 0,88.
                            
                            Passo 4: Preço final:
                            V_final = R$ 200,00 × 0,88 = R$ 176,00.
                            
                            Passo 5: Variação percentual líquida:
                            0,88 - 1 = -0,12 = -12% (desconto total de 12%).
                            
                            Resultado Final: O preço final é R$ 176,00, correspondendo a um desconto de 12% sobre o valor inicial.
                        """.trimIndent()
                    )
                ),
                goldTip = "Jamais some porcentagens sucessivas! (+10% e -20% NÃO dá -10%). Calcule sempre pelo produto dos fatores (1,10 × 0,80 = 0,88 -> desconto real de 12%)."
            )

            "mb_l10" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 10. Equações de 1º e 2º Grau e Sistemas Lineares
                    Modelagem algébrica de problemas práticos do cotidiano e do ENEM.
                    
                    ### A. Equação do 1º Grau e Sistemas Lineares 2x2
                    • **Forma Geral:** \( ax + b = 0 \implies x = -\frac{b}{a} \).
                    • **Sistemas Lineares:** Conjunto de duas equações com duas incógnitas (\(x\) e \(y\)). Resolvemos pelo **Método da Substituição** (isolar uma variável) ou pelo **Método da Adição** (somar as equações para cancelar uma variável).
                    
                    ### B. Equação do 2º Grau (Fórmula de Bhaskara & Soma e Produto)
                    • **Forma Geral:** \( ax^2 + bx + c = 0 \), com \( a \neq 0 \).
                    • **Discriminante (\(\Delta\)):** \( \Delta = b^2 - 4ac \).
                    • **Raízes:** \( x = \frac{-b \pm \sqrt{\Delta}}{2a} \).
                    • **Relações de Girard (Soma e Produto):**
                      \[ S = x_1 + x_2 = -\frac{b}{a} \quad \text{e} \quad P = x_1 \cdot x_2 = \frac{c}{a} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fórmula de Bhaskara",
                        syntax = "\\Delta = b^2 - 4ac \\quad \\implies \\quad x = \\frac{-b \\pm \\sqrt{\\Delta}}{2a}",
                        description = "Determina as raízes reais de qualquer equação quadrática ax² + bx + c = 0.",
                        parametersInfo = "Se Δ > 0: 2 raízes reais distintas; Δ = 0: 1 raiz dupla; Δ < 0: sem raízes reais."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Soma e Produto das Raízes",
                        syntax = "S = -\\frac{b}{a}, \\quad P = \\frac{c}{a}",
                        description = "Atalho rápido para encontrar raízes inteiras mentalmente.",
                        parametersInfo = "Para x² - Sx + P = 0, basta achar dois números que somados dão S e multiplicados dão P."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Sistema Linear de Ingressos",
                        codeOrFormula = "x + y = 100 \\text{ e } 20x + 10y = 1600 \\implies x = 60, y = 40",
                        explanation = """
                            Enunciado: Em um evento foram vendidos 100 ingressos entre inteiras (R$ 20,00) e meias (R$ 10,00), arrecadando R$ 1.600,00. Quantas inteiras foram vendidas?
                            
                            Passo 1: Equacionar o problema:
                            x + y = 100  (Eq. 1: total de ingressos)
                            20x + 10y = 1600  (Eq. 2: arrecadação total)
                            
                            Passo 2: Dividir a Eq. 2 por 10 para simplificar:
                            2x + y = 160
                            
                            Passo 3: Subtrair a Eq. 1 da Eq. 2 simplificada:
                            (2x + y) - (x + y) = 160 - 100
                            x = 60 ingressos inteiros e y = 40 meias-entradas.
                            
                            Resultado Final: Foram vendidos 60 ingressos inteiros.
                        """.trimIndent()
                    )
                ),
                goldTip = "Em problemas de sistemas lineares no ENEM, simplifique as equações dividindo todos os termos por um fator comum antes de isolar variáveis!"
            )

            "mb_l11" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 11. Estatística Básica: Média, Mediana e Moda
                    Medidas de tendência central mais cobradas nas provas do ENEM.
                    
                    ### A. Média Aritmética Simples e Ponderada
                    • **Média Simples (\(\bar{x}\)):** Soma de todos os valores dividida pela quantidade total de termos \(n\).
                    • **Média Ponderada (\(\bar{x}_p\)):** Cada valor \(x_i\) é multiplicado pelo seu respectivo peso ou frequência \(p_i\), dividindo-se pela soma dos pesos.
                    
                    ### B. Mediana e Moda
                    • **Mediana (\(Md\)):** É o valor central de uma amostra **obrigatoriamente ordenada** (crescente ou decrescente):
                      - Se \(n\) é **ímpar**: termo exatamente no meio, posição \(\frac{n+1}{2}\).
                      - Se \(n\) é **par**: média aritmética dos dois termos centrais.
                    • **Moda (\(Mo\)):** É o valor mais frequente (que mais se repete) no conjunto de dados.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Média Aritmética Ponderada",
                        syntax = "\\bar{x}_p = \\frac{x_1 \\cdot p_1 + x_2 \\cdot p_2 + \\dots + x_n \\cdot p_n}{p_1 + p_2 + \\dots + p_n}",
                        description = "Utilizada quando cada nota ou medida possui um peso ou frequência distinta.",
                        parametersInfo = "O denominador é sempre a soma dos pesos (e não a quantidade de itens)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Cálculo da Mediana com Quantidade Par",
                        codeOrFormula = "Dados: [18, 12, 25, 10, 14, 20] \\implies Ordenado: [10, 12, 14, 18, 20, 25] \\implies Md = 16",
                        explanation = """
                            Enunciado: Calcule a mediana das idades: 18, 12, 25, 10, 14, 20.
                            
                            Passo 1: ORDENAR os valores em ordem crescente (passo obrigatório!):
                            10, 12, 14, 18, 20, 25.
                            
                            Passo 2: Como há 6 termos (quantidade par), identificamos os dois termos centrais (3º e 4º):
                            14 e 18.
                            
                            Passo 3: Tirar a média aritmética dos dois termos centrais:
                            Md = (14 + 18) ÷ 2 = 32 ÷ 2 = 16.
                            
                            Resultado Final: A mediana é 16.
                        """.trimIndent()
                    )
                ),
                goldTip = "Pegadinha clássica do ENEM: NUNCA calcule a mediana sem antes colocar todos os números em ordem crescente!"
            )

            "mb_l12" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 12. Geometria Plana e Espacial Essencial
                    Cálculo de perímetros, áreas de figuras planas, Teorema de Pitágoras e volumes de sólidos.
                    
                    ### A. Áreas Planas e Teorema de Pitágoras
                    • **Retângulo:** \( A = b \times h \).
                    • **Triângulo:** \( A = \frac{b \times h}{2} \). Em triângulo equilátero de lado \(L\): \( A = \frac{L^2\sqrt{3}}{4} \).
                    • **Círculo:** \( A = \pi R^2 \) e Comprimento da circunferência \( C = 2\pi R \).
                    • **Teorema de Pitágoras (Triângulo Retângulo):** \( a^2 = b^2 + c^2 \) (ternos pitagóricos clássicos: \(3, 4, 5\) e \(5, 12, 13\)).
                    
                    ### B. Volumes de Sólidos Geométricos
                    • **Prisma e Cilindro:** \( V = A_{\text{base}} \times h \). No cilindro circular: \( V = \pi R^2 h \).
                    • **Pirâmide e Cone:** \( V = \frac{1}{3} A_{\text{base}} \times h \).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Volume de Cilindros e Prismas",
                        syntax = "V_{\\text{cilindro}} = \\pi R^2 \\cdot h \\quad | \\quad V_{\\text{bloco}} = a \\cdot b \\cdot c",
                        description = "Sólidos sem ponta têm volume igual à área da base multiplicada pela altura.",
                        parametersInfo = "Sólidos com ponta (cone e pirâmide) dividem o produto por 3."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Capacidade de Reservatório Cilíndrico",
                        codeOrFormula = "R = 2\\text{ m}, h = 5\\text{ m}, \\pi \\approx 3 \\implies V = 3 \\times 2^2 \\times 5 = 60\\text{ m}^3 = 60.000\\text{ L}",
                        explanation = """
                            Enunciado: Uma cisterna cilíndrica possui raio da base R = 2 m e altura h = 5 m. Adotando π = 3, qual é sua capacidade máxima em litros?
                            
                            Passo 1: Calcular o volume em metros cúbicos:
                            V = π × R² × h = 3 × (2)² × 5 = 3 × 4 × 5 = 60 m³.
                            
                            Passo 2: Converter metros cúbicos para litros (1 m³ = 1.000 L):
                            Capacidade = 60 × 1.000 = 60.000 litros.
                            
                            Resultado Final: 60.000 litros.
                        """.trimIndent()
                    )
                ),
                goldTip = "Tem ponta (cone/pirâmide)? Divide por 3! Não tem ponta (prisma/cilindro)? Área da base vezes a altura!"
            )

            else -> null
        }
    }
}
