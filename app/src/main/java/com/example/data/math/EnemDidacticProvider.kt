package com.example.data.math

import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor de Conteúdo Didático Completo para a Trilha Matemática ENEM.
 * Cada aula contém:
 * 1. Explicação Teórica detalhada (Didactic Theory)
 * 2. Fórmulas e Regras Operatórias fundamentais (Key Formulas)
 * 3. Exemplos Resolvidos Passo a Passo (Solved Examples)
 * 4. Exercícios de Fixação com alternativas, dicas e resoluções completas (Exercises)
 */
object EnemDidacticProvider {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        val content = didacticRegistry[lessonId] ?: return null
        return CalculusDidacticBundle(
            theoryMarkdown = content.theoryMarkdown,
            formulas = content.formulas,
            solvedExamples = content.solvedExamples,
            imageResId = null,
            goldTip = content.goldTip
        )
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise> {
        return exerciseRegistry[lessonId] ?: emptyList()
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
            // MÓDULO 1: MATEMÁTICA BÁSICA
            // ==========================================
            "enem_m1_a1" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Operações Básicas: Adição, Subtração, Multiplicação e Divisão
                    
                    As quatro operações fundamentais são o alicerce de mais de 35% de todas as questões do ENEM. 
                    No ENEM, raramente as operações aparecem puras; elas estão inseridas em problemas de contabilidade doméstica, consumo de combustível, leitura de relógios de energia e divisão de heranças.
                    
                    ### Hierarquia das Operações (Regra PEMDAS)
                    Em expressões matemáticas com múltiplos operadores, a prioridade absoluta de execução é:
                    1. Parênteses, Colchetes e Chaves: `( )`, `[ ]`, `{ }`
                    2. Expoentes e Raízes: \( a^n \) e \( \sqrt{a} \)
                    3. Multiplicações e Divisões: na ordem em que aparecem (da esquerda para a direita).
                    4. Adições e Subtrações: na ordem em que aparecem.
                    
                    ### Regras de Sinais
                    • Na multiplicação e divisão: sinais iguais resultam em positivo (`+`), sinais diferentes resultam em negativo (`-`).
                    • Na adição e subtração: sinais iguais somam e mantêm o sinal; sinais diferentes subtraem e mantêm o sinal do número de maior valor absoluto.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Propriedade Distributiva",
                        syntax = "a \\cdot (b + c) = a \\cdot b + a \\cdot c",
                        description = "Permite multiplicar um fator por cada termo dentro dos parênteses.",
                        parametersInfo = "Essencial para simplificar expressões sem calcular o interior primeiro."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Divisão Euclidiana (Algoritmo da Divisão)",
                        syntax = "D = d \\cdot q + r \\quad (0 \\le r < d)",
                        description = "Dividendo (D) é igual ao divisor (d) vezes o quociente (q) mais o resto (r).",
                        parametersInfo = "Usado em problemas de ciclos e calendários periódicos no ENEM."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Orçamento Familiar",
                        codeOrFormula = "2500 - (3 \\cdot 450 + 4 \\cdot 85 + 210) = R$ 600,00",
                        explanation = "Uma família tem renda de R$ 2.500,00 e gasta 3 parcelas de R$ 450,00, 4 compras de supermercado de R$ 85,00 e R$ 210,00 de luz. O saldo restante é calculado aplicando a prioridade das multiplicações antes da subtração final."
                    )
                ),
                goldTip = "No ENEM, fique atento à unidade de medida solicitada na resposta: se a pergunta pede em gramas e os dados estão em quilogramas, converta antes de realizar a divisão!"
            ),

            "enem_m1_a2" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Frações e Números Decimais
                    
                    Frações representam partes de um todo, proporções e divisões. No ENEM, frações surgem em dosagens de medicamentos, receitas, mistura de tintas e cálculo de rendimentos.
                    
                    ### Operações com Frações
                    • **Adição/Subtração com denominadores diferentes:** Encontra-se o MMC dos denominadores para criar frações equivalentes com base comum.
                    • **Multiplicação:** Multiplica-se numerador por numerador e denominador por denominador: \( \frac{a}{b} \cdot \frac{c}{d} = \frac{a \cdot c}{b \cdot d} \).
                    • **Divisão:** Multiplica-se a primeira fração pelo inverso da segunda: \( \frac{a}{b} \div \frac{c}{d} = \frac{a}{b} \cdot \frac{d}{c} \).
                    
                    ### Conversão Decimal-Fração
                    • `0,25` = 25/100 = 1/4
                    • `0,5` = 5/10 = 1/2
                    • `0,75` = 75/100 = 3/4
                    • `0,125` = 125/1000 = 1/8
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Soma de Frações",
                        syntax = "\\frac{a}{b} + \\frac{c}{d} = \\frac{a \\cdot d + b \\cdot c}{b \\cdot d}",
                        description = "Método da 'borboleta' para soma rápida de frações com dois termos.",
                        parametersInfo = "b ≠ 0, d ≠ 0"
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Inverso de Fração",
                        syntax = "\\left(\\frac{a}{b}\\right)^{-1} = \\frac{b}{a}",
                        description = "Inversão do numerador pelo denominador para efetuar divisões fracionárias.",
                        parametersInfo = "a ≠ 0, b ≠ 0"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Mistura de Tintas",
                        codeOrFormula = "\\frac{2}{5} + \\frac{1}{3} = \\frac{6 + 5}{15} = \\frac{11}{15}",
                        explanation = "Um pintor misturou 2/5 de lata de tinta azul com 1/3 de lata de tinta branca. Para encontrar o volume combinado, encontra-se o MMC(5, 3) = 15, resultando em 11/15 de lata preenchida."
                    )
                ),
                goldTip = "Simplifique frações sempre que possível antes de multiplicar numeradores grandes. Cortar fatores comuns economiza tempo precioso na prova."
            ),

            "enem_m1_a3" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Múltiplos e Divisores: MMC e MDC
                    
                    Problemas de encontros periódicos e divisão máxima em partes iguais são clássicos absolutos do ENEM.
                    
                    ### Mínimo Múltiplo Comum (MMC)
                    O menor número positivo que é múltiplo simultâneo de dois ou mais números. 
                    **Palavras-chave no ENEM:** *Quando se encontrarão novamente?*, *Repetição simultânea*, *Coincidência temporal*.
                    
                    ### Máximo Divisor Comum (MDC)
                    O maior número que divide exatamente dois ou mais números sem deixar resto.
                    **Palavras-chave no ENEM:** *Dividir em pedaços iguais de maior tamanho possível*, *Sem sobrar nada*, *Maior comprimento comum*.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fatoração Simultânea (MMC)",
                        syntax = "\\text{MMC}(a, b) = \\text{Produto de todos os fatores primos elevados aos maiores expoentes}",
                        description = "Fatoração de números até que todos cheguem a 1.",
                        parametersInfo = "Útil para horários de remédios, linhas de ônibus e semáforos."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Relação Fundamental entre MMC e MDC",
                        syntax = "\\text{MMC}(a, b) \\cdot \\text{MDC}(a, b) = a \\cdot b",
                        description = "O produto do MMC pelo MDC de dois números é igual ao produto dos próprios números.",
                        parametersInfo = "Válido para quaisquer dois inteiros positivos a e b."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Partida de Ônibus Simultânea",
                        codeOrFormula = "\\text{MMC}(12, 18, 30) = 180\\text{ minutos} = 3\\text{ horas}",
                        explanation = "Linhas de ônibus que saem a cada 12, 18 e 30 minutos a partir das 6h da manhã sairão juntas novamente após o MMC dos tempos: 180 minutos (3 horas), isto é, exatamente às 9h."
                    )
                ),
                goldTip = "Se a pergunta falar em 'maior comprimento possível sem sobras', é MDC. Se falar em 'quando voltarão a acontecer juntos', é MMC!"
            ),

            "enem_m1_a4" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Razão, Proporção e Escalas
                    
                    Razão é a comparação entre duas grandezas por meio de uma divisão: \( R = \frac{a}{b} \).
                    Uma proporção é a igualdade entre duas razões: \( \frac{a}{b} = \frac{c}{d} \).
                    
                    ### Escalas Cartográficas
                    A escala de um mapa ou maquete compara a medida no desenho (`d`) com a medida real (`D`):
                    \( E = \frac{d}{D} \).
                    
                    **Atenção Crítica no ENEM:**
                    • Escala linear (comprimento): \( E = \frac{d}{D} \)
                    • Escala de área (superfície): \( E^2 = \frac{A_{\text{mapa}}}{A_{\text{real}}} \)
                    • Escala volumétrica (capacidade): \( E^3 = \frac{V_{\text{maquete}}}{V_{\text{real}}} \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Propriedade Fundamental da Proporção",
                        syntax = "a \\cdot d = b \\cdot c \\quad \\Longleftrightarrow \\quad \\frac{a}{b} = \\frac{c}{d}",
                        description = "O produto dos extremos é igual ao produto dos meios (multiplicação cruzada).",
                        parametersInfo = "b ≠ 0, d ≠ 0"
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Escala de Superfície (Área)",
                        syntax = "\\frac{A_{\\text{desenho}}}{A_{\\text{real}}} = \\left(\\frac{1}{k}\\right)^2 = \\frac{1}{k^2}",
                        description = "Se a escala linear é 1:k, a escala de área é 1:k².",
                        parametersInfo = "Erro clássico no ENEM: esquecer de elevar a escala ao quadrado ao calcular áreas!"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Mapa e Distância Real",
                        codeOrFormula = "D = 5\\text{ cm} \\times 500.000 = 2.500.000\\text{ cm} = 25\\text{ km}",
                        explanation = "Em um mapa de escala 1:500.000, dois municípios distam 5 cm. A distância real é 5 × 500.000 cm = 2.500.000 cm = 25.000 m = 25 km."
                    )
                ),
                goldTip = "Para converter cm para km rapidamente: corte 5 zeros! (1 km = 1.000 m = 100.000 cm)."
            ),

            "enem_m1_a5" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Regra de Três Simples e Composta
                    
                    A regra de três permite encontrar um valor desconhecido a partir de grandezas proporcionais.
                    
                    ### Grandezas Diretamente Proporcionais (GDP)
                    Quando o aumento de uma grandeza provoca o aumento proporcional da outra.
                    *Exemplo:* Quantidade de combustível e distância percorrida por um veículo.
                    *Cálculo:* Multiplica-se em cruz (`X`).
                    
                    ### Grandezas Inversamente Proporcionais (GIP)
                    Quando o aumento de uma grandeza provoca a redução proporcional da outra.
                    *Exemplo:* Velocidade e tempo de viagem; número de operários e dias para concluir uma obra.
                    *Cálculo:* Multiplica-se em linha reta (`=`).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra de Três Composta",
                        syntax = "\\frac{G_1}{G_2} = \\frac{A_1}{A_2} \\cdot \\frac{B_2}{B_1}",
                        description = "Compara a grandeza principal com cada uma das demais, invertendo as razões das grandezas inversamente proporcionais.",
                        parametersInfo = "Isole a grandeza com 'x' e monte a igualdade com o produto das outras razões."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Velocidade e Tempo de Viagem",
                        codeOrFormula = "60 \\cdot 4 = 80 \\cdot t \\implies t = \\frac{240}{80} = 3\\text{ horas}",
                        explanation = "A 60 km/h uma viagem dura 4 horas. A 80 km/h (velocidade maior, tempo menor: grandeza inversamente proporcional), o tempo será de 3 horas."
                    )
                ),
                goldTip = "Sempre teste o sentido da seta: pergunte 'se eu aumentar essa grandeza, a incógnita precisa aumentar ou diminuir?'. Se diminuir, inverta a fração antes de multiplicar!"
            ),

            "enem_m1_a6" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Porcentagem e Fatores de Multiplicação
                    
                    A porcentagem é uma razão centesimal \( \frac{p}{100} \). No ENEM, dominar os **fatores de multiplicação** elimina etapas de contas longas.
                    
                    ### Fator de Aumento
                    Para aplicar um aumento de `i%`, multiplica-se diretamente por:
                    \( F = 1 + \\frac{i}{100} \)
                    • Aumento de 15%: multiplica por `1,15`
                    • Aumento de 40%: multiplica por `1,40`
                    • Aumento de 5%: multiplica por `1,05`
                    
                    ### Fator de Desconto
                    Para aplicar uma redução de `i%`, multiplica-se diretamente por:
                    \( F = 1 - \\frac{i}{100} \)
                    • Desconto de 20%: multiplica por `0,80`
                    • Desconto de 35%: multiplica por `0,65`
                    • Desconto de 8%: multiplica por `0,92`
                    
                    ### Aumentos e Descontos Sucessivos
                    Multiplicam-se os fatores correspondentes:
                    \( F_{\\text{final}} = F_1 \\cdot F_2 \\dots \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Variação Percentual Relativa",
                        syntax = "\\Delta\\% = \\frac{V_{\\text{final}} - V_{\\text{inicial}}}{V_{\\text{inicial}}} \\cdot 100",
                        description = "Calcula o percentual de crescimento ou queda em relação ao valor original.",
                        parametersInfo = "O denominador é SEMPRE o valor de referência inicial!"
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Aumentos Sucessivos",
                        syntax = "V_{\\text{final}} = V_0 \\cdot (1 + i_1) \\cdot (1 + i_2)",
                        description = "Um aumento de 10% seguido de 20% NÃO é 30%, mas sim 1,10 × 1,20 = 1,32 (aumento real de 32%).",
                        parametersInfo = "Regra de ouro contra pegadinhas clássicas do ENEM."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Desconto e Aumento Consecutivos",
                        codeOrFormula = "100 \\times 1,20 \\times 0,80 = 96\\text{ (Prejuízo de 4%)}",
                        explanation = "Um produto que sobre aumento de 20% e depois desconto de 20% não volta ao valor inicial: 1,20 × 0,80 = 0,96, ou seja, fica 4% mais barato que o preço original."
                    )
                ),
                goldTip = "Nunca some porcentagens de bases diferentes! 10% de R$ 100 não é a mesma coisa que 10% de R$ 110."
            ),

            // ==========================================
            // MÓDULO 2: MATEMÁTICA FINANCEIRA
            // ==========================================
            "enem_m2_a1" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Juros Simples
                    
                    No regime de juros simples, os juros são calculados exclusivamente sobre o capital inicial em todos os períodos.
                    O crescimento do montante ao longo do tempo é estritamente linear (representado graficamente por uma reta).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fórmula dos Juros Simples",
                        syntax = "J = C \\cdot i \\cdot t",
                        description = "J = Juros, C = Capital Inicial, i = Taxa unitária, t = Tempo.",
                        parametersInfo = "A taxa (i) e o tempo (t) DEVEM estar na mesma unidade de tempo (ex: ao mês e meses)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Montante Simples",
                        syntax = "M = C + J = C \\cdot (1 + i \\cdot t)",
                        description = "Montante total acumulado no final da aplicação.",
                        parametersInfo = "Representa uma função afim de 1º grau M(t) = C + (C·i)t."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Aplicação em Juros Simples",
                        codeOrFormula = "J = 5000 \\cdot 0,02 \\cdot 6 = R$ 600,00 \\implies M = R$ 5.600,00",
                        explanation = "R$ 5.000,00 aplicados a 2% ao mês por 6 meses geram R$ 600,00 de juros, totalizando um montante de R$ 5.600,00."
                    )
                ),
                goldTip = "Converta sempre a porcentagem em decimal dividindo por 100: 2,5% ao mês = 0,025 nas contas."
            ),

            "enem_m2_a2" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Juros Compostos
                    
                    No regime de juros compostos (regime padrão do mercado financeiro, poupança, cartões de crédito e investimentos), os juros de cada período são incorporados ao capital para render nos períodos seguintes ('juros sobre juros').
                    O crescimento do montante é exponencial.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Montante em Juros Compostos",
                        syntax = "M = C \\cdot (1 + i)^t",
                        description = "O capital é multiplicado repetidamente pelo fator de aumento (1 + i) a cada período t.",
                        parametersInfo = "No ENEM, se t for elevado, a prova geralmente fornece aproximações como (1,02)^12 = 1,268."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Dívida de Cartão de Crédito",
                        codeOrFormula = "M = 1000 \\cdot (1 + 0,10)^3 = 1000 \\cdot 1,331 = R$ 1.331,00",
                        explanation = "Uma dívida inicial de R$ 1.000,00 sob taxa de 10% ao mês acumulada por 3 meses resulta em R$ 1.331,00."
                    )
                ),
                goldTip = "Em gráficos comparativos no ENEM: a curva que começa devagar e dispara para cima é de juros compostos (exponencial); a linha reta é de juros simples."
            ),

            "enem_m2_a3" to LessonDidacticEntry(
                theoryMarkdown = """
                    # Sistemas de Amortização
                    
                    Amortização é o processo de pagamento gradual de uma dívida através de prestações periódicas.
                    
                    ### Tabela SAC (Sistema de Amortização Constante)
                    • A **amortização da dívida** é rigorosamente igual em todas as parcelas: \( A = \frac{\text{Dívida Total}}{n} \).
                    • Como a dívida diminui a cada mês, os juros também diminuem.
                    • Consequência: as parcelas são **decrescentes** (a primeira prestação é a mais cara, a última é a mais barata).
                    
                    ### Tabela PRICE (Sistema Francês)
                    • O valor da **prestação é constante** durante todo o financiamento.
                    • No início, paga-se muito juro e pouca amortização; com o tempo, a amortização cresce e os juros caem.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Composição da Prestação",
                        syntax = "P = A + J",
                        description = "Prestação (P) é a soma da Amortização do saldo devedor (A) com os Juros do período (J).",
                        parametersInfo = "Juros incidem SEMPRE sobre o saldo devedor remanescente do mês anterior."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Tabela SAC: Primeira e Última Parcela",
                        codeOrFormula = "A = \\frac{120.000}{120} = 1.000\\text{/mês} \\implies P_1 = 1000 + 1200 = 2.200",
                        explanation = "Num financiamento SAC de R$ 120.000 em 120 meses a 1% ao mês, a amortização é fixa de R$ 1.000. No 1º mês, os juros são de 1% sobre 120 mil (R$ 1.200), gerando parcela de R$ 2.200."
                    )
                ),
                goldTip = "Lembre-se: no SAC as parcelas caem mês a mês. Na PRICE todas as parcelas têm exatamente o mesmo valor nominal."
            )
        )
    }

    private val exerciseRegistry: Map<String, List<MathExercise>> by lazy {
        mapOf(
            "enem_m1_a1" to listOf(
                MathExercise(
                    id = "ex_m1_a1_1",
                    title = "ENEM: Economia e Combustível",
                    problemStatement = "Um motorista realizou uma viagem de 480 km. O carro tem consumo médio de 12 km por litro de gasolina. Sabendo que o preço do litro de gasolina é R$ 5,60 e que foram pagos R$ 34,00 em pedágios, qual foi o custo total com combustível e pedágios nessa viagem?",
                    options = listOf("R$ 224,00", "R$ 258,00", "R$ 268,00", "R$ 280,00", "R$ 310,00"),
                    correctIndex = 1,
                    hint = "Primeiro calcule a quantidade de litros dividindo a distância pelo consumo médio.",
                    stepByStepSolution = "1. Litros consumidos: 480 / 12 = 40 litros.\n2. Custo com gasolina: 40 * 5,60 = R$ 224,00.\n3. Custo total com pedágio: 224,00 + 34,00 = R$ 258,00."
                ),
                MathExercise(
                    id = "ex_m1_a1_2",
                    title = "Expressão Numérica Aplicada",
                    problemStatement = "Em uma promoção, um produto que custava R$ 180,00 teve um desconto de R$ 25,00 e o valor restante foi parcelado em 5 prestações iguais. Qual é o valor de cada prestação?",
                    options = listOf("R$ 29,00", "R$ 31,00", "R$ 32,50", "R$ 35,00", "R$ 36,00"),
                    correctIndex = 1,
                    hint = "Subtraia o desconto do valor original e depois divida pelo número de parcelas.",
                    stepByStepSolution = "1. Valor pós-desconto: 180 - 25 = 155.\n2. Cada parcela: 155 / 5 = R$ 31,00."
                )
            ),

            "enem_m1_a2" to listOf(
                MathExercise(
                    id = "ex_m1_a2_1",
                    title = "ENEM: Soma de Frações e Volume",
                    problemStatement = "Uma cozinheira utilizou 3/4 de xícara de açúcar para uma massa e 2/5 de xícara para o recheio. Qual foi o total de açúcar utilizado em número decimal?",
                    options = listOf("1,05 xícaras", "1,15 xícaras", "1,20 xícaras", "1,25 xícaras", "1,35 xícaras"),
                    correctIndex = 1,
                    hint = "Converta 3/4 e 2/5 para decimais ou encontre o MMC(4, 5).",
                    stepByStepSolution = "3/4 = 0,75. 2/5 = 0,40. Total = 0,75 + 0,40 = 1,15 xícaras."
                )
            ),

            "enem_m1_a3" to listOf(
                MathExercise(
                    id = "ex_m1_a3_1",
                    title = "ENEM: Plantão Médico e MMC",
                    problemStatement = "Dois médicos dão plantão em um hospital de emergência. O médico A dá plantão a cada 6 dias e o médico B a cada 8 dias. Se ambos deram plantão juntos no dia 1º de março, quantos dias depois voltarão a dar plantão juntos?",
                    options = listOf("12 dias", "16 dias", "24 dias", "36 dias", "48 dias"),
                    correctIndex = 2,
                    hint = "Encontre o Menor Múltiplo Comum entre 6 e 8.",
                    stepByStepSolution = "Fatorando 6 e 8 simultaneamente:\n6, 8 | 2\n3, 4 | 2\n3, 2 | 2\n3, 1 | 3\n1, 1 | MMC = 2³ * 3 = 24 dias."
                )
            ),

            "enem_m1_a4" to listOf(
                MathExercise(
                    id = "ex_m1_a4_1",
                    title = "ENEM: Escala Cartográfica",
                    problemStatement = "Em uma planta arquitetônica desenhada na escala 1:50, a sala de um apartamento possui 8 cm de comprimento por 6 cm de largura. As dimensões reais dessa sala são:",
                    options = listOf("4 m x 3 m", "4,5 m x 3,5 m", "5 m x 4 m", "8 m x 6 m", "40 m x 30 m"),
                    correctIndex = 0,
                    hint = "Multiplique cada dimensão por 50 e converta centímetros para metros.",
                    stepByStepSolution = "Comprimento: 8 cm * 50 = 400 cm = 4 metros.\nLargura: 6 cm * 50 = 300 cm = 3 metros."
                )
            ),

            "enem_m1_a5" to listOf(
                MathExercise(
                    id = "ex_m1_a5_1",
                    title = "ENEM: Equipe de Operários (Regra de Três Inversa)",
                    problemStatement = "Uma obra é realizada por 6 operários em 20 dias de trabalho contínuo. Se a equipe for reforçada, passando a contar com 8 operários com a mesma produtividade, em quantos dias a mesma obra será concluída?",
                    options = listOf("12 dias", "14 dias", "15 dias", "16 dias", "18 dias"),
                    correctIndex = 2,
                    hint = "Mais operários realizam a obra em MENOS dias (inversamente proporcional). Multiplique em linha.",
                    stepByStepSolution = "6 * 20 = 8 * x -> 120 = 8x -> x = 120 / 8 = 15 dias."
                )
            ),

            "enem_m1_a6" to listOf(
                MathExercise(
                    id = "ex_m1_a6_1",
                    title = "ENEM: Aumento Sucessivo",
                    problemStatement = "Uma mercadoria teve seu preço reajustado em 10% no mês de janeiro e, sobre o novo preço, recebeu outro aumento de 20% em fevereiro. O aumento percentual acumulado em relação ao preço inicial foi de:",
                    options = listOf("30%", "31%", "32%", "33%", "34%"),
                    correctIndex = 2,
                    hint = "Multiplique os fatores 1,10 e 1,20.",
                    stepByStepSolution = "Fator total = 1,10 * 1,20 = 1,32 -> Aumento real de 32% (e não 30%)."
                )
            ),

            "enem_m2_a1" to listOf(
                MathExercise(
                    id = "ex_m2_a1_1",
                    title = "ENEM: Juros Simples e Renda",
                    problemStatement = "Um investidor aplicou R$ 4.000,00 a uma taxa de juros simples de 1,5% ao mês durante 8 meses. Qual é o valor dos juros e o montante final resgatado?",
                    options = listOf("J = R$ 480,00 e M = R$ 4.480,00", "J = R$ 520,00 e M = R$ 4.520,00", "J = R$ 600,00 e M = R$ 4.600,00", "J = R$ 640,00 e M = R$ 4.640,00", "J = R$ 720,00 e M = R$ 4.720,00"),
                    correctIndex = 0,
                    hint = "Aplique J = C * i * t com taxa em formato decimal (0,015).",
                    stepByStepSolution = "J = 4000 * 0,015 * 8 = 480,00.\nMontante = 4000 + 480 = R$ 4.480,00."
                )
            ),

            "enem_m2_a2" to listOf(
                MathExercise(
                    id = "ex_m2_a2_1",
                    title = "ENEM: Montante Composto",
                    problemStatement = "Um capital de R$ 2.000,00 foi aplicado a juros compostos com taxa de 10% ao ano durante 2 anos. O montante ao final da aplicação foi de:",
                    options = listOf("R$ 2.200,00", "R$ 2.400,00", "R$ 2.420,00", "R$ 2.440,00", "R$ 2.500,00"),
                    correctIndex = 2,
                    hint = "M = C * (1 + i)^t -> M = 2000 * (1,10)^2.",
                    stepByStepSolution = "1,10^2 = 1,21. M = 2000 * 1,21 = R$ 2.420,00."
                )
            )
        )
    }
}
