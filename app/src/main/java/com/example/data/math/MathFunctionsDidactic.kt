package com.example.data.math

import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor Didático Especializado de Funções, Álgebra e Logaritmos para o ENEM.
 * Cobre Módulo 6 (enem_m6_a1 a enem_m6_a6), Módulo 7 (enem_m7_a1 a enem_m7_a5) e Módulo 11 (enem_m11_a1 a enem_m11_a3).
 */
object MathFunctionsDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            // Módulo 6: Funções
            "enem_m6_a1" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Plano Cartesiano e Pares Ordenados
                    
                    O plano cartesiano permite a representação geométrica de grandezas numéricas correlacionadas, como tempo e velocidade, custo e produção.
                    
                    ### Estrutura
                    • Eixo horizontal das abscissas (\( x \)) e eixo vertical das ordenadas (\( y \)).
                    • Quatro quadrantes anti-horários numerados de I a IV.
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Par Ordenado", "(x, y)", "Localização unívoca de um ponto no plano")),
                solvedExamples = listOf(LessonExample("Localização de Ponto", "P(-3, 4)", "3 unidades à esquerda da origem e 4 unidades acima (II Quadrante).")),
                goldTip = "O primeiro número do par sempre anda no eixo horizontal (x); o segundo anda no vertical (y)!"
            )

            "enem_m6_a2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Conceito de Função: Domínio, Contradomínio e Imagem
                    
                    Uma função associa cada elemento de um conjunto de partida (domínio) a um único elemento no conjunto de chegada.
                    
                    ### Leitura de Gráficos no ENEM
                    Para verificar se uma curva representa uma função, traçam-se retas verticais: cada reta vertical deve cruzar a curva em no máximo um ponto.
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Notação de Função", "y = f(x)", "A variável dependente y é função da variável independente x")),
                solvedExamples = listOf(LessonExample("Custo em Função da Produção", "C(x) = 5x + 20", "Produzir 10 unidades custa C(10) = 50 + 20 = R$ 70,00.")),
                goldTip = "Domínio é a projeção da curva no eixo X; Imagem é a projeção no eixo Y!"
            )

            "enem_m6_a3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Função Afim (1º Grau): Taxa de Variação e Gráficos
                    
                    A função afim modela processos de crescimento ou decrescimento constante, como tarifas de táxi (bandeirada fixa + valor por km).
                    
                    ### Equação da Reta
                    \( f(x) = a \cdot x + b \)
                    • **Coeficiente Angular (\( a \)):** Taxa constante de variação \( a = \frac{\Delta y}{\Delta x} \).
                    • **Coeficiente Linear (\( b \)):** Valor inicial fixo, onde a reta corta o eixo \( y \) (ponto \( (0, b) \)).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Função Afim", "f(x) = a\\cdot x + b", "Reta de inclinação a e intercepto b"),
                    FormulaOrSyntaxItem("Taxa de Variação", "a = \\frac{y_2 - y_1}{x_2 - x_1}", "Variação vertical dividida pela horizontal")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Corrida de Aplicativo",
                        codeOrFormula = "C(x) = 2,50x + 5,00",
                        explanation = "Bandeirada fixa de R$ 5,00 e custo variável de R$ 2,50 por quilômetro rodado."
                    )
                ),
                goldTip = "Se a reta sobe, a > 0 (função crescente). Se desce, a < 0 (decrescente). O ponto onde corta o eixo Y é sempre o termo independente b!"
            )

            "enem_m6_a4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Função Quadrática: Parábola, Bhaskara e Vértices
                    
                    A função do 2º grau modela trajetórias balísticas e problemas de **lucro máximo**, **receita máxima** ou **custo mínimo**.
                    
                    ### 1. Vértice da Parábola (Máximo e Mínimo)
                    • Coordenada \( x \) que maximiza/minimiza: \( x_v = -\frac{b}{2a} \)
                    • Valor máximo/mínimo atingido: \( y_v = -\frac{\Delta}{4a} \)
                    • Se \( a < 0 \), a parábola tem concavidade para baixo e possui ponto de **máximo**.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Vértice da Parábola", "x_v = -\\frac{b}{2a} \\quad | \\quad y_v = -\\frac{\\Delta}{4a}", "Ponto de inflexão extrema da parábola"),
                    FormulaOrSyntaxItem("Fórmula de Bhaskara", "x = \\frac{-b \\pm \\sqrt{\\Delta}}{2a}", "Raízes onde f(x) = 0 com Delta = b² - 4ac")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Lucro Máximo de uma Empresa",
                        codeOrFormula = "L(x) = -2x² + 40x - 50 -> x_v = -40 / (2 * -2) = 10",
                        explanation = "A empresa atinge o lucro máximo quando comercializa exatamente 10 unidades do produto."
                    )
                ),
                goldTip = "Atenção à pergunta: se o ENEM pede 'qual quantidade produzida para o lucro máximo', calcule x_v. Se pede 'qual o valor do lucro máximo', calcule y_v!"
            )

            "enem_m6_a5" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Função Exponencial: Crescimento Bacteriano e Decaimento
                    
                    A função exponencial \( f(x) = a \cdot b^x \) modela situações em que uma grandeza se multiplica por um fator constante a intervalos regulares de tempo (crescimento de vírus, juros compostos ou desintegração radioativa).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Equação Exponencial", "N(t) = N_0 \\cdot k^t", "População inicial N0 multiplicada pelo fator k a cada período t")),
                solvedExamples = listOf(LessonExample("Cultura Bacteriana Duplicando a cada hora", "N(t) = 500 * 2^t", "Após 4 horas: N(4) = 500 * 16 = 8.000 bactérias.")),
                goldTip = "Crescimento afim adiciona uma quantia fixa; crescimento exponencial MULTIPLICA por um fator fixo a cada passo!"
            )

            "enem_m6_a6" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Função Logarítmica e Escalas Científicas
                    
                    A função logarítmica \( f(x) = \log_b(x) \) é a inversa da exponencial. Ela converte grandezas com variações astronômicas em escalas compactas e manipuláveis (Escala Richter para terremotos, decibéis sonoros e pH químico).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Definição de Logaritmo", "\\log_b(a) = c \\iff b^c = a", "A base b elevada ao expoente c resulta no logaritmando a")),
                solvedExamples = listOf(LessonExample("Magnitude de Terremoto", "M = log(A / A0)", "Uma variação de 1 unidade na magnitude Richter significa que a amplitude do tremor é 10 vezes maior.")),
                goldTip = "Em escalas logarítmicas de base 10, subir 2 graus (de magnitude 4 para 6) significa multiplicar a intensidade por 10² = 100 vezes!"
            )

            // Módulo 7: Álgebra e Equações
            "enem_m7_a1" -> CalculusDidacticBundle(
                theoryMarkdown = "# Expressões Algébricas e Substituição Numérica\nCálculo de valor numérico em fórmulas de física, economia e medicina.",
                formulas = listOf(FormulaOrSyntaxItem("Valor Numérico", "f(x) em x = c", "Substituição direta da incógnita pelo valor fornecido")),
                solvedExamples = listOf(LessonExample("Cálculo de IMC", "IMC = peso / altura²", "Pessoa com 80 kg e 2 m: IMC = 80 / 4 = 20 kg/m².")),
                goldTip = "Cuidado com parênteses ao substituir números negativos em potências: (-3)² = +9, mas -3² = -9!"
            )

            "enem_m7_a2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Produtos Notáveis e Fatoração Algébrica
                    
                    Identidades que poupam tempo em simplificações de frações algébricas.
                    • Quadrado da soma: \( (a + b)^2 = a^2 + 2ab + b^2 \)
                    • Quadrado da diferença: \( (a - b)^2 = a^2 - 2ab + b^2 \)
                    • Produto da soma pela diferença: \( (a + b)(a - b) = a^2 - b^2 \)
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Diferença de Quadrados", "a^2 - b^2 = (a + b)(a - b)", "Fatoração essencial no ENEM")),
                solvedExamples = listOf(LessonExample("Cálculo Rápido", "101² - 99² = (101 + 99)(101 - 99) = 200 * 2 = 400", "Muito mais rápido que calcular os quadrados.")),
                goldTip = "Nunca escreva (a + b)² = a² + b²! Falta o termo médio 2ab!"
            )

            "enem_m7_a3" -> CalculusDidacticBundle(
                theoryMarkdown = "# Equações e Inequações do 1º Grau\nIsolamento de incógnitas e interpretação de restrições de orçamento no ENEM.",
                formulas = listOf(FormulaOrSyntaxItem("Equação do 1º Grau", "ax + b = 0 -> x = -b/a", "Solução única")),
                solvedExamples = listOf(LessonExample("Plano de Celular", "30 + 0,50x <= 50 -> 0,50x <= 20 -> x <= 40 minutos", "Limite de uso dentro do saldo.")),
                goldTip = "Ao multiplicar ou dividir uma inequação por um número negativo, INVERTA o sentido da desigualdade!"
            )

            "enem_m7_a4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Equações do 2º Grau e Relações de Girard
                    Resolução por Bhaskara ou Soma e Produto (\( S = -b/a \) e \( P = c/a \)).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Soma e Produto", "x_1 + x_2 = -b/a \\quad | \\quad x_1 \\cdot x_2 = c/a", "Raízes diretas")),
                solvedExamples = listOf(LessonExample("x² - 5x + 6 = 0", "Soma 5 e Produto 6 -> raízes 2 e 3", "Solução instantânea sem Bhaskara.")),
                goldTip = "Quando a = 1, procure dois números que multiplicados dão c e somados dão -b!"
            )

            "enem_m7_a5" -> CalculusDidacticBundle(
                theoryMarkdown = "# Sistemas Lineares 2x2 no Cotidiano\nProblemas clássicos de mistura de ingressos (adultos e crianças) ou contagem de rodas de veículos.",
                formulas = listOf(FormulaOrSyntaxItem("Sistema Linear", "a1 x + b1 y = c1 e a2 x + b2 y = c2", "Método da adição ou substituição")),
                solvedExamples = listOf(LessonExample("Carros e Motos em Estacionamento", "x + y = 20 e 4x + 2y = 60 -> x = 10 carros e y = 10 motos", "Multiplicação da primeira equação por -2.")),
                goldTip = "Multiplique uma das equações para que os coeficientes de uma das variáveis fiquem opostos (ex: +2y e -2y), somando as linhas em seguida!"
            )

            // Módulo 11: Logaritmos
            "enem_m11_a1" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Definição e Condições de Existência de Logaritmos
                    
                    Para que \( \log_b(a) \) exista nos reais:
                    • O logaritmando deve ser estritamente positivo: \( a > 0 \).
                    • A base deve ser positiva e diferente de 1: \( b > 0 \) e \( b \neq 1 \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Condição de Existência", "log_b(a) -> a > 0, b > 0 e b != 1", "Restrições de domínio")),
                solvedExamples = listOf(LessonExample("Cálculo Simples", "log_2(32) = 5 porque 2^5 = 32", "Encontrar a potência necessária.")),
                goldTip = "Logaritmo nada mais é do que uma pergunta: 'A que expoente devo elevar a base para chegar ao resultado?'!"
            )

            "enem_m11_a2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Propriedades Operatórias dos Logaritmos
                    
                    As três propriedades capitais cobradas no ENEM:
                    1. **Logaritmo do Produto:** \( \log(a \cdot c) = \log(a) + \log(c) \)
                    2. **Logaritmo do Quociente:** \( \log(a / c) = \log(a) - \log(c) \)
                    3. **Logaritmo da Potência (Regra do Tombo):** \( \log(a^k) = k \cdot \log(a) \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Regra do Tombo", "\\log(a^k) = k \\cdot \\log(a)", "O expoente escorrega multiplicando na frente"),
                    FormulaOrSyntaxItem("Mudança de Base", "\\log_b(a) = \\frac{\\log_c(a)}{\\log_c(b)}", "Permite calcular em calculadoras decimais")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Dado log 2 = 0,30 e log 3 = 0,48, calcule log 6",
                        codeOrFormula = "log 6 = log(2 * 3) = log 2 + log 3 = 0,30 + 0,48 = 0,78",
                        explanation = "A decomposição em fatores primos permite calcular o logaritmo por adição."
                    )
                ),
                goldTip = "O truque mais frequente no ENEM: log 5 = log(10 / 2) = log 10 - log 2 = 1 - log 2 = 1 - 0,30 = 0,70!"
            )

            "enem_m11_a3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Resolução de Equações Exponenciais com Logaritmos
                    
                    Quando as bases de uma equação exponencial não podem ser igualadas (ex: \( 2^x = 7 \)), aplica-se o logaritmo em ambos os lados e usa-se a regra do tombo:
                    \( x \cdot \log(2) = \log(7) \implies x = \frac{\log(7)}{\log(2)} \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Linearização Exponencial", "a^x = b -> x = log(b) / log(a)", "Uso da regra do tombo para descer o expoente x")),
                solvedExamples = listOf(LessonExample("Meia-vida de Substância", "(1/2)^t = 0,10 -> t * log(0,5) = log(0,10)", "Determinação do tempo decorrido.")),
                goldTip = "Aplique logaritmo dos dois lados da igualdade e 'tombe' o expoente para a frente da expressão!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Funções e Álgebra ENEM\nRelações matemáticas e modelagem.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Interprete sempre as variáveis em seu contexto real."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "enem_m6_a1" -> listOf(
                MathExercise(
                    id = "ex_m6_a1_1",
                    title = "ENEM: Localização de Ponto no Plano",
                    problemStatement = "Em um sistema de coordenadas cartesianas de monitoramento de tráfego, uma viatura está estacionada no ponto P de coordenadas x = -4 km e y = -6 km. O quadrante em que essa viatura se encontra é o:",
                    options = listOf("Primeiro quadrante", "Segundo quadrante", "Terceiro quadrante", "Quarto quadrante", "Eixo das ordenadas"),
                    correctIndex = 2,
                    hint = "Quando tanto a abscissa (x) quanto a ordenada (y) são negativas, o ponto localiza-se no terceiro quadrante.",
                    stepByStepSolution = "Alternativa correta: C (Terceiro quadrante).\n\nNo plano cartesiano:\nI: (+, +) | II: (-, +) | III: (-, -) | IV: (+, -).\nComo P tem x < 0 e y < 0, ele pertence ao III Quadrante."
                )
            )

            "enem_m6_a2" -> listOf(
                MathExercise(
                    id = "ex_m6_a2_1",
                    title = "ENEM: Identificação de Função pelo Teste da Reta Vertical",
                    problemStatement = "Dentre os gráficos a seguir que relacionam grandezas econômicas, aquele que NÃO representa uma função y = f(x) é uma curva em que:",
                    options = listOf(
                        "para cada valor de x existe um único valor correspondente de y.",
                        "uma reta vertical intercepta o gráfico em mais de um ponto distinto.",
                        "a reta é estritamente crescente em todo o domínio.",
                        "o gráfico intercepta o eixo horizontal em uma única raiz.",
                        "a imagem coincide com o conjunto dos números reais positivos."
                    ),
                    correctIndex = 1,
                    hint = "Pela definição de função, um elemento do domínio (x) não pode ter mais de uma imagem (y).",
                    stepByStepSolution = "Alternativa correta: B.\n\nPelo teste da reta vertical, se uma reta perpendicular ao eixo x cruza a curva em dois ou mais pontos, significa que um mesmo valor de x possui múltiplas saídas y, violando a definição unívoca de função."
                )
            )

            "enem_m6_a3" -> listOf(
                MathExercise(
                    id = "ex_m6_a3_1",
                    title = "ENEM: Tarifa de Táxi e Função Afim",
                    problemStatement = "O valor cobrado por uma corrida de táxi é composto por uma taxa fixa de bandeirada no valor de R$ 6,00 mais R$ 2,50 por quilômetro rodado. Se um passageiro pagou R$ 41,00 pela corrida, a distância percorrida pelo veículo foi de:",
                    options = listOf("12 km", "14 km", "16 km", "18 km", "20 km"),
                    correctIndex = 1,
                    hint = "Monte a equação: 2,50x + 6,00 = 41,00 e isole x.",
                    stepByStepSolution = "Alternativa correta: B (14 km).\n\n2,50x + 6 = 41 -> 2,50x = 35 -> x = 35 / 2,50 = 14 km."
                )
            )

            "enem_m6_a4" -> listOf(
                MathExercise(
                    id = "ex_m6_a4_1",
                    title = "ENEM: Ponto de Máximo e Lucro em Função Quadrática",
                    problemStatement = "Uma empresa calculou que seu lucro mensal L (em milhares de reais) varia com a quantidade produzida x (em centenas de peças) segundo a função quadrática L(x) = -x² + 12x - 20. A quantidade de peças que a empresa deve produzir para maximizar seu lucro mensal é de:",
                    options = listOf("400 peças", "600 peças", "800 peças", "1.000 peças", "1.200 peças"),
                    correctIndex = 1,
                    hint = "O ponto de máximo de uma parábola ocorre no vértice x_v = -b / (2a).",
                    stepByStepSolution = "Alternativa correta: B (600 peças).\n\nx_v = -12 / (2 * (-1)) = -12 / -2 = 6 centenas = 600 peças."
                )
            )

            "enem_m6_a5" -> listOf(
                MathExercise(
                    id = "ex_m6_a5_1",
                    title = "ENEM: Crescimento Exponencial de Bactérias",
                    problemStatement = "Uma colônia de bactérias possui inicialmente 200 indivíduos e duplica sua população a cada 3 horas. O número total de bactérias presentes nessa cultura após decorridas 12 horas é igual a:",
                    options = listOf("800", "1.600", "2.400", "3.200", "6.400"),
                    correctIndex = 3,
                    hint = "Em 12 horas ocorrem 12 / 3 = 4 ciclos de duplicação. N = 200 * 2^4.",
                    stepByStepSolution = "Alternativa correta: D (3.200 bactérias).\n\nNúmero de períodos: 12 / 3 = 4 ciclos.\nN = 200 * 2⁴ = 200 * 16 = 3.200 bactérias."
                )
            )

            "enem_m6_a6" -> listOf(
                MathExercise(
                    id = "ex_m6_a6_1",
                    title = "ENEM: Comparação na Escala Richter",
                    problemStatement = "A energia liberada por um terremoto de magnitude M na escala Richter relaciona-se com a amplitude das ondas pela expressão M = log₁₀(A / A₀). Se um terremoto na cidade A teve magnitude 7 e outro na cidade B teve magnitude 5, a amplitude das ondas sísmicas em A foi quantas vezes superior à amplitude em B?",
                    options = listOf("2 vezes", "20 vezes", "100 vezes", "200 vezes", "1.000 vezes"),
                    correctIndex = 2,
                    hint = "Como a base do logaritmo é 10, a diferença de 2 unidades na magnitude equivale a uma razão de 10².",
                    stepByStepSolution = "Alternativa correta: C (100 vezes).\n\nΔM = 7 - 5 = 2.\nA_A / A_B = 10^(ΔM) = 10² = 100 vezes mais intenso."
                )
            )

            "enem_m7_a1" -> listOf(
                MathExercise(
                    id = "ex_m7_a1_1",
                    title = "ENEM: Valor Numérico de Fórmula Pediátrica",
                    problemStatement = "A dose diária recomendada de um medicamento para crianças é calculada pela fórmula D = (Idade * 12) / (Idade + 12) mg. Para uma criança de 4 anos de idade, a dose diária calculada é de:",
                    options = listOf("2 mg", "3 mg", "4 mg", "6 mg", "8 mg"),
                    correctIndex = 1,
                    hint = "Substitua Idade = 4 na fórmula.",
                    stepByStepSolution = "Alternativa correta: B (3 mg).\n\nD = (4 * 12) / (4 + 12) = 48 / 16 = 3 mg."
                )
            )

            "enem_m7_a2" -> listOf(
                MathExercise(
                    id = "ex_m7_a2_1",
                    title = "ENEM: Diferença de Quadrados em Terrenos",
                    problemStatement = "Um proprietário possuía um terreno quadrado de lado 53 metros e vendeu dele uma área quadrada menor de lado 47 metros. A área de terreno restante que ainda lhe pertence mede:",
                    options = listOf("300 m²", "400 m²", "500 m²", "600 m²", "700 m²"),
                    correctIndex = 3,
                    hint = "Use a fatoração da diferença de quadrados: a² - b² = (a + b)(a - b).",
                    stepByStepSolution = "Alternativa correta: D (600 m²).\n\n53² - 47² = (53 + 47) * (53 - 47) = 100 * 6 = 600 m²."
                )
            )

            "enem_m7_a3" -> listOf(
                MathExercise(
                    id = "ex_m7_a3_1",
                    title = "ENEM: Inequação de Orçamento em Compras",
                    problemStatement = "Um estudante dispõe de R$ 60,00 para comprar canetas que custam R$ 4,50 cada, após já ter pago R$ 15,00 por um caderno. O número máximo de canetas que ele pode comprar é:",
                    options = listOf("8", "9", "10", "11", "12"),
                    correctIndex = 2,
                    hint = "Monte a inequação: 4,50x + 15 <= 60.",
                    stepByStepSolution = "Alternativa correta: C (10 canetas).\n\n4,50x <= 60 - 15 = 45 -> x <= 45 / 4,50 = 10 canetas."
                )
            )

            "enem_m7_a4" -> listOf(
                MathExercise(
                    id = "ex_m7_a4_1",
                    title = "ENEM: Dimensões de Campo Retangular (Bhaskara)",
                    problemStatement = "Um campo de futebol retangular tem área de 300 m² e seu comprimento excede a largura em 5 metros. A largura desse campo mede:",
                    options = listOf("12 metros", "15 metros", "18 metros", "20 metros", "25 metros"),
                    correctIndex = 1,
                    hint = "Monte x * (x + 5) = 300 -> x² + 5x - 300 = 0.",
                    stepByStepSolution = "Alternativa correta: B (15 metros).\n\nDois números com diferença 5 e produto 300 são 15 e 20 (15 * 20 = 300). Logo, a largura mede 15 m."
                )
            )

            "enem_m7_a5" -> listOf(
                MathExercise(
                    id = "ex_m7_a5_1",
                    title = "ENEM: Ingressos de Cinema (Sistema 2x2)",
                    problemStatement = "Em uma sessão de cinema, foram vendidos 120 ingressos entre inteiras (R$ 30,00) e meias-entradas (R$ 15,00), totalizando uma arrecadação de R$ 2.700,00. Quantas meias-entradas foram vendidas?",
                    options = listOf("40", "50", "60", "70", "80"),
                    correctIndex = 2,
                    hint = "Sistema: x + y = 120 e 30x + 15y = 2.700.",
                    stepByStepSolution = "Alternativa correta: C (60 meias-entradas).\n\nx + y = 120 -> x = 120 - y.\n30(120 - y) + 15y = 2.700 -> 3.600 - 15y = 2.700 -> 15y = 900 -> y = 60 meias."
                )
            )

            "enem_m11_a1" -> listOf(
                MathExercise(
                    id = "ex_m11_a1_1",
                    title = "ENEM: Definição de Logaritmo",
                    problemStatement = "Sabendo que a intensidade do som em decibéis é dada por N = 10 * log₁₀(I / I₀), se uma fonte sonora emite com intensidade tal que I / I₀ = 100.000, o nível sonoro em decibéis é de:",
                    options = listOf("30 dB", "40 dB", "50 dB", "60 dB", "70 dB"),
                    correctIndex = 2,
                    hint = "100.000 = 10⁵. Logo log₁₀(10⁵) = 5.",
                    stepByStepSolution = "Alternativa correta: C (50 dB).\n\nN = 10 * log₁₀(10⁵) = 10 * 5 = 50 dB."
                )
            )

            "enem_m11_a2" -> listOf(
                MathExercise(
                    id = "ex_m11_a2_1",
                    title = "ENEM: Propriedade do Produto de Logaritmos",
                    problemStatement = "Considerando log 2 = 0,30 e log 3 = 0,48, o valor de log 18 é igual a:",
                    options = listOf("0,78", "0,96", "1,08", "1,26", "1,44"),
                    correctIndex = 3,
                    hint = "Fatore 18 = 2 * 3² -> log 18 = log 2 + 2 * log 3.",
                    stepByStepSolution = "Alternativa correta: D (1,26).\n\nlog 18 = log(2 * 3²) = log 2 + 2 * log 3 = 0,30 + 2 * 0,48 = 0,30 + 0,96 = 1,26."
                )
            )

            "enem_m11_a3" -> listOf(
                MathExercise(
                    id = "ex_m11_a3_1",
                    title = "ENEM: Equação Exponencial com Logaritmo",
                    problemStatement = "O número de infectados por uma nova variante em um hospital cresce segundo f(t) = 10 * 2^t, com t em semanas. Para estimar em quantas semanas o número atingirá 1.000 infectados, adotando log 2 = 0,30, deve-se resolver a equação obtendo aproximadamente:",
                    options = listOf("3,3 semanas", "5,2 semanas", "6,7 semanas", "8,1 semanas", "10,0 semanas"),
                    correctIndex = 2,
                    hint = "10 * 2^t = 1.000 -> 2^t = 100 -> t * log 2 = log 100 = 2.",
                    stepByStepSolution = "Alternativa correta: C (6,7 semanas).\n\n2^t = 100 -> t * log 2 = 2 -> t * 0,30 = 2 -> t = 2 / 0,30 ≈ 6,67 semanas."
                )
            )

            else -> emptyList()
        }
    }
}
