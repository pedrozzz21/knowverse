package com.example.data.math

import com.example.R
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado do Módulo 1 de Cálculo I: Limites e Continuidade (Aulas 1 a 10).
 * Estrutura: Matéria (Conceito Intuitivo) -> Fórmulas (Rigor Analítico) -> Exemplos Resolvidos Passo a Passo.
 */
object CalculusLimitsDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "calc_l1" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # 1. Revisão das Principais Funções (O Ponto de Partida da P1)

                    Antes de calcular limites, é indispensável conhecer o comportamento, o domínio e os gráficos das funções fundamentais:

                    ### A. Polinomiais e Racionais
                    • **Polinômio:** \( f(x) = a_n x^n + \dots + a_1 x + a_0 \). Definida em toda a reta real (\(\mathbb{R}\)), sem saltos nem interrupções.
                    • **Racional:** \( f(x) = \frac{P(x)}{Q(x)} \). O domínio exclui os pontos onde \( Q(x) = 0 \), locais típicos de assíntotas verticais ou "buracos" no gráfico.

                    ### B. Funções Exponencial e Logarítmica
                    São funções inversas uma da outra.
                    • **Exponencial (\(y = a^x\), com \(a > 0, a \neq 1\)):**
                      - Domínio: \(\mathbb{R}\); Imagem: \((0, +\infty)\).
                      - Se \(a > 1\) (como \(e^x\)): estritamente crescente. Conforme \(x \to +\infty\), \(e^x \to +\infty\); conforme \(x \to -\infty\), \(e^x \to 0\).
                    • **Logarítmica (\(y = \log_a(x)\) ou \(\ln(x)\)):**
                      - Domínio: \((0, +\infty)\); Imagem: \(\mathbb{R}\).
                      - Conforme \(x \to +\infty\), \(\ln(x) \to +\infty\).
                      - Conforme \(x \to 0^+\) (pela direita), \(\ln(x) \to -\infty\).

                    ### C. Funções Trigonométricas
                    • **Seno (\(\sin x\)) e Cosseno (\(\cos x\)):** Periódicas de período \(2\pi\), oscilam sempre no intervalo limitado \([-1, 1]\).
                    • **Tangente (\(\tan x = \frac{\sin x}{\cos x}\)):** Possui assíntotas verticais onde \(\cos x = 0\), isto é, em \(x = \frac{\pi}{2} + k\pi\) (\(k \in \mathbb{Z}\)).

                    ### D. Função Composta e Função Inversa
                    • **Composta \((f \circ g)(x) = f(g(x))\):** Aplica-se primeiro a regra de \(g\), e a saída torna-se a entrada de \(f\).
                    • **Inversa (\(f^{-1}(x)\)):** Existe se a função for bijetora (injetora e sobrejetora). Cumpre \(f(f^{-1}(x)) = x\) e seu gráfico é a reflexão do gráfico de \(f(x)\) em relação à reta \(y = x\).

                    ---

                    # 2. A Ideia Intuitiva de Limite
                    Dizer que:
                    \[ \lim_{x \to a} f(x) = L \]
                    Significa que à medida que \(x\) se aproxima do valor \(a\) (tanto por valores ligeiramente menores quanto maiores), os valores de \(f(x)\) ficam arbitrariamente próximos do número real \(L\).

                    > **Ponto fundamental:** O limite analisa o que acontece **ao redor** de \(a\), e não necessariamente no ponto \(a\) em si. O valor \(f(a)\) pode ser diferente de \(L\) ou sequer existir.

                    ---

                    # 3. Propriedades Operatórias dos Limites
                    Se \(\lim_{x \to a} f(x) = L\) e \(\lim_{x \to a} g(x) = M\):
                    1. \(\lim_{x \to a} [c \cdot f(x)] = c \cdot L\)
                    2. \(\lim_{x \to a} [f(x) \pm g(x)] = L \pm M\)
                    3. \(\lim_{x \to a} [f(x) \cdot g(x)] = L \cdot M\)
                    4. \(\lim_{x \to a} \left[\frac{f(x)}{g(x)}\right] = \frac{L}{M}\), desde que \(M \neq 0\)
                    5. \(\lim_{x \to a} [f(x)]^n = L^n\)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Notação Fundamental do Limite",
                        syntax = "\\lim_{x \\to a} f(x) = L",
                        description = "Indica convergência na vizinhança perfurada: x se aproxima de 'a' por ambos os lados.",
                        parametersInfo = "x ≠ a na vizinhança; f(a) não precisa estar definida para o limite existir."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Propriedades Operatórias",
                        syntax = "\\lim [c \\cdot f] = cL, \\quad \\lim [f \\pm g] = L \\pm M, \\quad \\lim [f \\cdot g] = LM, \\quad \\lim [f/g] = L/M \\; (M \\neq 0)",
                        description = "O limite preserva todas as operações aritméticas elementares desde que os limites individuais existam.",
                        parametersInfo = "Potência: lim [f(x)]ⁿ = Lⁿ."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Clássico: Fatoração com Indeterminação 0/0",
                        codeOrFormula = "\\lim_{x \\to 2} \\frac{x^2 - 4}{x - 2} = 4",
                        explanation = """
                            Passo 1: Substituição direta: (2² - 4)/(2 - 2) = 0/0 (indeterminação).
                            Passo 2: Fatorar a diferença de quadrados no numerador: x² - 4 = (x - 2)(x + 2).
                            Passo 3: Cancelar o fator (x - 2) para todo x ≠ 2 na vizinhança perfurada:
                            lim_{x -> 2} (x + 2).
                            Passo 4: Substituir x = 2: 2 + 2 = 4.
                            Resultado Final: O limite bilateral converge para 4.
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_furo_indeterminacao,
                goldTip = "O limite analisa exclusivamente o que acontece AO REDOR do ponto 'a', e não no ponto em si: f(a) pode ter um buraco e o limite valer um número real perfeito!"
            )

            "calc_l2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Uma função pode se comportar de maneiras completamente diferentes dependendo da direção pela qual nos aproximamos de um ponto. Pense em um degrau ou em uma chave elétrica liga/desliga: antes do clique o circuito está aberto (0V), depois do clique está fechado (12V).
                    
                    Os limites laterais investigam separadamente a aproximação pela esquerda (valores estritamente menores que a) e pela direita (valores estritamente maiores que a).
                    
                    O Teorema da Existência estabelece que o limite bilateral só existirá se, e somente se, os dois limites laterais existirem e forem exatamente iguais!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Limite Lateral pela Esquerda",
                        syntax = "lim_{x -> a⁻} f(x) = L₁",
                        description = "Avalia a convergência de f(x) considerando apenas valores de x que se aproximam de 'a' sendo estritamente menores que 'a' (x < a).",
                        parametersInfo = "O expoente negativo (-) indica aproximação pela esquerda."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Limite Lateral pela Direita",
                        syntax = "lim_{x -> a⁺} f(x) = L₂",
                        description = "Avalia a convergência de f(x) considerando apenas valores de x que se aproximam de 'a' sendo estritamente maiores que 'a' (x > a).",
                        parametersInfo = "O expoente positivo (+) indica aproximação pela direita."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Teorema Fundamental da Existência",
                        syntax = "lim_{x -> a} f(x) = L  <=>  lim_{x -> a⁻} f(x) = lim_{x -> a⁺} f(x) = L",
                        description = "Se os limites laterais divergirem (L₁ ≠ L₂), o limite bilateral NÃO EXISTE no ponto a.",
                        parametersInfo = "Condição necessária e suficiente para existência do limite."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Função Modular e Salto Descontínuo",
                        codeOrFormula = "Analisar lim_{x -> 0} [|x| / x]",
                        explanation = """
                            Passo 1: Lembrar da definição de módulo: |x| = x se x > 0, e |x| = -x se x < 0.
                            Passo 2: Calcular o limite pela direita (x -> 0⁺, onde x > 0):
                            lim_{x -> 0⁺} [x / x] = lim_{x -> 0⁺} (1) = 1.
                            Passo 3: Calcular o limite pela esquerda (x -> 0⁻, onde x < 0):
                            lim_{x -> 0⁻} [-x / x] = lim_{x -> 0⁻} (-1) = -1.
                            Passo 4: Comparar os resultados: L₁ = -1 e L₂ = 1.
                            Como -1 ≠ 1, concluímos que lim_{x -> 0} (|x|/x) NÃO EXISTE!
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_limites_laterais,
                goldTip = "Em funções definidas por partes ou funções com módulo, SEMPRE calcule os limites laterais separadamente!"
            )

            "calc_l3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    O que acontece com uma função quando a variável x cresce sem limites (x -> +∞) ou decresce infinitamente (x -> -∞)?
                    
                    Na engenharia e na física, isso representa o comportamento de longo prazo ou estado estacionário de um sistema (por exemplo, a temperatura de um motor após horas de funcionamento ou a velocidade terminal de um paraquedista).
                    
                    Se os valores de f(x) se estabilizarem em torno de um número fixo L conforme x tende ao infinito, a reta horizontal y = L é chamada de ASSÍNTOTA HORIZONTAL do gráfico.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Limite no Infinito & Assíntota Horizontal",
                        syntax = "lim_{x -> ±∞} f(x) = L  =>  Reta y = L é assíntota horizontal",
                        description = "Mede a tendência assintótica da função nos extremos da reta real.",
                        parametersInfo = "L deve ser um número real finito. Uma função pode ter no máximo duas assíntotas horizontais (uma para +∞ e outra para -∞)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra do Termo Dominante (Polinômios)",
                        syntax = "lim_{x -> ±∞} [a_n x^n + ... + a_0] / [b_m x^m + ... + b_0] = lim_{x -> ±∞} [a_n x^n] / [b_m x^m]",
                        description = "Para x tendendo ao infinito, os termos de maior grau ditam completamente o comportamento da fração.",
                        parametersInfo = "Se n < m: limite = 0; se n = m: limite = a_n / b_m; se n > m: limite = ±∞."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Quociente de Polinômios com Grau Igual",
                        codeOrFormula = "Calcular lim_{x -> ∞} [(5x³ - 2x + 1) / (2x³ + 7x² - 4)]",
                        explanation = """
                            Passo 1: Identificar os termos dominantes (maior potência de x no numerador e denominador):
                            Numerador: 5x³; Denominador: 2x³.
                            Passo 2: Dividir todos os termos por x³ ou isolar os termos líderes:
                            lim_{x -> ∞} [5x³ / 2x³] = 5/2.
                            Passo 3: Os termos menores (-2x/x³, 1/x³, etc.) tendem todos a 0 quando x -> ∞.
                            Conclusão: O limite é 5/2 e a reta y = 2.5 é uma assíntota horizontal!
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_assintotas,
                goldTip = "Ao calcular limites no infinito de frações polinomiais, ignore os termos de menor grau e foque exclusivamente no termo de maior potência!"
            )

            "calc_l4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Quando o denominador de uma fração se aproxima de zero enquanto o numerador permanece diferente de zero, a divisão resulta em números absurdamente grandes em magnitude.
                    
                    Dizemos que o limite é INFINITO (+∞ ou -∞). Isso significa que a função cresce descontroladamente para cima ou despenca para baixo nas proximidades daquele ponto.
                    
                    A reta vertical x = a onde f(x) explode para ±∞ é chamada de ASSÍNTOTA VERTICAL. É uma barreira invisível que a função nunca cruza verticalmente.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Definição de Limite Infinito",
                        syntax = "lim_{x -> a⁺} f(x) = ±∞   ou   lim_{x -> a⁻} f(x) = ±∞",
                        description = "Indica que os valores de f(x) crescem além de qualquer cota arbitrária próximo de x = a.",
                        parametersInfo = "Se pelo menos um dos limites laterais for infinito, a reta x = a é uma Assíntota Vertical."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra do Sinal na Divisão por Zero",
                        syntax = "Constante / (0⁺) = +∞ (se const > 0)   |   Constante / (0⁻) = -∞ (se const > 0)",
                        description = "Identificar se o denominador se aproxima de zero por valores positivos (0⁺) ou negativos (0⁻) determina o sinal do infinito.",
                        parametersInfo = "Atenção crucial aos sinais dos fatores no numerador e no denominador!"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Assíntota Vertical de f(x) = 1 / (x - 3)",
                        codeOrFormula = "Analisar os limites laterais quando x -> 3",
                        explanation = """
                            Passo 1: Avaliar x -> 3⁺ (valores como 3.01):
                            Denominador: 3.01 - 3 = +0.01 (positivo, tende a 0⁺).
                            lim_{x -> 3⁺} [1 / (x - 3)] = 1 / 0⁺ = +∞.
                            Passo 2: Avaliar x -> 3⁻ (valores como 2.99):
                            Denominador: 2.99 - 3 = -0.01 (negativo, tende a 0⁻).
                            lim_{x -> 3⁻} [1 / (x - 3)] = 1 / 0⁻ = -∞.
                            Conclusão: A reta vertical x = 3 é uma assíntota vertical; a curva explode para cima pela direita e despenca pela esquerda.
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_assintotas,
                goldTip = "Nunca escreva que um número sobre zero é 'impossível' no cálculo: avalie se o denominador é 0⁺ ou 0⁻ para encontrar se o limite é +∞ ou -∞!"
            )

            "calc_l5" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    A forma 0/0 é conhecida como INDETERMINAÇÃO MATEMÁTICA. Ela não significa que o limite não existe e nem que o resultado é zero! Significa simplesmente que a informação está oculta e precisamos de uma manipulação algébrica para revelar o verdadeiro valor.
                    
                    Se tanto o numerador quanto o denominador resultam em 0 quando x = a, isso significa (pelo Teorema de D'Alembert) que o termo (x - a) é uma raiz de ambos os polinômios!
                    
                    Portanto, sempre podemos FATORAR o numerador e o denominador para simplificar o fator (x - a) que está causando o zero compartilhado.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Diferença de Quadrados & Trinômio Quadrado Perfeito",
                        syntax = "a² - b² = (a - b)(a + b)   |   a² ± 2ab + b² = (a ± b)²",
                        description = "Produtos notáveis mais frequentes em provas de Cálculo para fatorar polinômios de 2º grau.",
                        parametersInfo = "Exemplo: x² - 9 = (x - 3)(x + 3)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Soma e Diferença de Cubos",
                        syntax = "a³ - b³ = (a - b)(a² + ab + b²)   |   a³ + b³ = (a + b)(a² - ab + b²)",
                        description = "Fatoração indispensável para expressões cúbicas.",
                        parametersInfo = "Exemplo: x³ - 8 = (x - 2)(x² + 2x + 4)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Dispositivo Prático de Briot-Ruffini",
                        syntax = "P(x) = (x - a) · Q(x)",
                        description = "Algoritmo veloz para dividir qualquer polinômio P(x) por (x - a) quando sabemos que a é raiz.",
                        parametersInfo = "Reduz o grau do polinômio diretamente."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Resolvendo Indeterminação 0/0 com Briot-Ruffini",
                        codeOrFormula = "Calcular lim_{x -> 2} [(x³ - 8) / (x² - 5x + 6)]",
                        explanation = """
                            Passo 1: Substituindo x = 2: (8 - 8)/(4 - 10 + 6) = 0/0.
                            Passo 2: Fatorar numerador: x³ - 8 = (x - 2)(x² + 2x + 4).
                            Passo 3: Fatorar denominador: x² - 5x + 6 = (x - 2)(x - 3).
                            Passo 4: Cancelar o termo comum (x - 2) para x ≠ 2:
                            lim_{x -> 2} [(x² + 2x + 4) / (x - 3)]
                            Passo 5: Substituir x = 2 agora:
                            (2² + 2(2) + 4) / (2 - 3) = (4 + 4 + 4) / (-1) = 12 / (-1) = -12.
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_furo_indeterminacao,
                goldTip = "Se deu 0/0 em polinômios, o termo (x - a) é garantidamente fator comum! Fatore e corte esse termo imediatamente!"
            )

            "calc_l6" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Quando a indeterminação 0/0 envolve raízes quadradas ou cúbicas, a fatoração direta por produtos notáveis simples não é evidente.
                    
                    A técnica mestra para eliminar radicais é a RACIONALIZAÇÃO, que consiste em multiplicar o numerador e o denominador pelo CONJUGADO da expressão radical.
                    
                    O conjugado de (A - B) é (A + B). Ao multiplicá-los, obtemos a diferença de quadrados (A - B)(A + B) = A² - B², o que destrói a raiz quadrada instantaneamente!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Conjugado para Raiz Quadrada",
                        syntax = "(√A - B) · (√A + B) = (√A)² - B² = A - B²",
                        description = "Elimina a raiz quadrada transformando o binômio em uma diferença de quadrados.",
                        parametersInfo = "Multiplique sempre numerador e denominador pela mesma expressão para não alterar o valor da fração."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Conjugado para Raiz Cúbica",
                        syntax = "(∛A - B) · (∛A² + B∛A + B²) = A - B³",
                        description = "Baseia-se na fatoração da diferença de cubos para eliminar raízes cúbicas.",
                        parametersInfo = "Útil quando aparecem radicais de índice 3 na forma 0/0."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Clássico de Racionalização",
                        codeOrFormula = "Calcular lim_{x -> 0} [(√(x + 4) - 2) / x]",
                        explanation = """
                            Passo 1: Substituição direta: (√4 - 2)/0 = (2 - 2)/0 = 0/0.
                            Passo 2: Multiplicar numerador e denominador pelo conjugado [√(x + 4) + 2]:
                            [ (√(x+4) - 2)(√(x+4) + 2) ] / [ x · (√(x+4) + 2) ]
                            Passo 3: No numerador, aplicar (a - b)(a + b) = a² - b²:
                            (x + 4) - 4 = x.
                            Passo 4: A expressão fica: x / [ x · (√(x+4) + 2) ].
                            Passo 5: Simplificar o fator x (para x ≠ 0):
                            lim_{x -> 0} [ 1 / (√(x+4) + 2) ].
                            Passo 6: Substituir x = 0: 1 / (√4 + 2) = 1 / (2 + 2) = 1/4.
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "Nunca expanda o produto do denominador ao racionalizar! Mantenha-o faturado para cortar o fator x que vai surgir do numerador!"
            )

            "calc_l7" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Ao tentar calcular lim_{x -> 0} [sen(x) / x], a substituição direta produz sen(0)/0 = 0/0. Não podemos fatorar polinômios aqui porque o seno é uma função transcendental!
                    
                    Este é o PRIMEIRO LIMITE FUNDAMENTAL DO CÁLCULO. Geometricamente, no círculo trigonométrico, quando o ângulo x (em radianos) fica muito pequeno, o comprimento do arco x e o segmento vertical sen(x) tornam-se virtualmente idênticos, de modo que sua razão converge exatamente para 1.
                    
                    A partir deste resultado, deduzem-se todas as fórmulas de derivadas das funções trigonométricas (como (sen x)' = cos x).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Limite Fundamental Trigonométrico",
                        syntax = "lim_{u -> 0} [sen(u) / u] = 1",
                        description = "O argumento dentro do seno e o denominador devem tender a zero e ser EXATAMENTE IGUAIS.",
                        parametersInfo = "u pode ser qualquer expressão em x (ex: 5x, x², etc.), desde que u -> 0."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Forma Inversa & Variação da Tangente",
                        syntax = "lim_{u -> 0} [u / sen(u)] = 1   e   lim_{u -> 0} [tg(u) / u] = 1",
                        description = "Consequências imediatas do limite fundamental trigonométrico.",
                        parametersInfo = "tg(u)/u = [sen(u)/u] · [1/cos(u)] = 1 · 1 = 1."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Ajuste de Coeficientes com sen(kx)",
                        codeOrFormula = "Calcular lim_{x -> 0} [sen(5x) / (3x)]",
                        explanation = """
                            Passo 1: O argumento do seno é 5x, mas o denominador é 3x.
                            Passo 2: Tiramos a constante 1/3 para fora do limite:
                            (1/3) · lim_{x -> 0} [sen(5x) / x].
                            Passo 3: Multiplicamos numerador e denominador por 5 para criar 5x embaixo:
                            (1/3) · lim_{x -> 0} [5 · sen(5x) / (5x)] = (5/3) · lim_{x -> 0} [sen(5x) / (5x)].
                            Passo 4: Fazendo u = 5x (quando x -> 0, u -> 0):
                            lim_{u -> 0} [sen(u) / u] = 1.
                            Resultado final: (5/3) · 1 = 5/3.
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "Em provas de Cálculo, para qualquer lim_{x->0} [sen(ax)/sen(bx)] ou [sen(ax)/(bx)], o resultado é sempre a/b!"
            )

            "calc_l8" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Imagine que você investe R$ 1,00 a uma taxa de juros de 100% ao ano. Se os juros renderem uma vez por ano, você terá R$ 2,00. Se renderem a cada semestre, terá (1 + 1/2)² = R$ 2,25. Se renderem diariamente, terá (1 + 1/365)³⁶⁵ ≈ R$ 2,7145.
                    
                    Se os juros forem capitalizados continuamente a cada instante infinitesimal (n -> ∞), o montante não cresce até o infinito: ele atinge o teto universal e = 2,71828... (o Número de Euler).
                    
                    Esse é o SEGUNDO LIMITE FUNDAMENTAL, e surge em decaimento radioativo, crescimento populacional, juros contínuos e redes neurais.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Limite Fundamental Exponencial (Forma 1)",
                        syntax = "lim_{x -> ±∞} (1 + 1/x)ˣ = e ≈ 2.71828...",
                        description = "Representa o crescimento composto contínuo no infinito.",
                        parametersInfo = "Indeterminação do tipo 1^∞. A base tende a 1 e o expoente explode para o infinito."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Limite Fundamental Exponencial (Forma 2)",
                        syntax = "lim_{u -> 0} (1 + u)^{1/u} = e",
                        description = "Formulação equivalente obtida pela substituição u = 1/x.",
                        parametersInfo = "Muito útil para expressões que tendem a zero na base."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Generalização para Taxas (Regra Prática)",
                        syntax = "lim_{x -> ∞} (1 + k/x)ˣ = eᵏ",
                        description = "Permite resolver limites exponenciais com coeficientes diretamente.",
                        parametersInfo = "k é qualquer constante real."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Resolvendo Indeterminação 1^∞",
                        codeOrFormula = "Calcular lim_{x -> ∞} [(x + 3) / x]²ˣ",
                        explanation = """
                            Passo 1: Separar a fração na base: (x + 3)/x = 1 + 3/x.
                            Passo 2: A expressão torna-se: lim_{x -> ∞} (1 + 3/x)²ˣ.
                            Passo 3: Usar propriedades de potência para isolar o termo fundamental:
                            [ (1 + 3/x)ˣ ]².
                            Passo 4: Sabemos que lim_{x -> ∞} (1 + 3/x)ˣ = e³.
                            Passo 5: Elevando ao expoente exterior 2:
                            (e³)² = e^{3 · 2} = e⁶.
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "Cuidado: 1 elevado a infinito (1^∞) NÃO É 1! É uma indeterminação clássica cujo resultado geralmente envolve potências da base 'e'!"
            )

            "calc_l9" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    O Teorema do Confronto (popularmente conhecido como Teorema do Sanduíche ou Squeeze Theorem) é uma das armas mais elegantes do Cálculo para resolver limites de funções oscilatórias que não convergem sozinhas (como sen(1/x) ou cos(1/x)).
                    
                    A ideia intuitiva é simples: imagine que dois policiais estão conduzindo um suspeito entre eles. Se ambos os policiais entram na mesma porta de uma delegacia, o suspeito no meio é obrigado a entrar na mesma porta!
                    
                    Se f(x) está 'espremida' entre g(x) e h(x) (ou seja, g(x) ≤ f(x) ≤ h(x)) para todos os pontos próximos de 'a', e se g(x) e h(x) convergem para o mesmo valor L, então f(x) obrigatoriamente converge para L.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Enunciado do Teorema do Confronto",
                        syntax = "Se g(x) ≤ f(x) ≤ h(x)  e  lim g(x) = lim h(x) = L  =>  lim f(x) = L",
                        description = "Garante a convergência da função intermediária sem precisar calcular seu limite diretamente.",
                        parametersInfo = "A desigualdade deve valer em uma vizinhança perfurada de 'a'."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Corolário: Função Limitada vezes Função que Tende a Zero",
                        syntax = "Se |f(x)| ≤ M  e  lim_{x -> a} g(x) = 0  =>  lim_{x -> a} [f(x) · g(x)] = 0",
                        description = "O produto de uma grandeza que encolhe até zero por qualquer grandeza limitada (como seno ou cosseno) é sempre ZERO.",
                        parametersInfo = "Como -1 ≤ sen(u) ≤ 1, o seno é sempre limitado por M = 1."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Clássico: O Sanduíche de x² · sen(1/x)",
                        codeOrFormula = "Calcular lim_{x -> 0} [x² · sen(1/x)]",
                        explanation = """
                            Passo 1: Se tentarmos substituir x = 0, sen(1/0) oscila loucamente entre -1 e +1 sem convergir.
                            Passo 2: Porém, a função seno é estritamente limitada para qualquer argumento real:
                            -1 ≤ sen(1/x) ≤ 1.
                            Passo 3: Como x² > 0 para todo x ≠ 0, multiplicamos toda a inequação por x²:
                            -x² ≤ x² · sen(1/x) ≤ x².
                            Passo 4: Calculamos o limite das 'fatias do pão' quando x -> 0:
                            lim_{x -> 0} (-x²) = 0   e   lim_{x -> 0} (x²) = 0.
                            Passo 5: Pelo Teorema do Confronto, a função no meio é espremida para zero:
                            lim_{x -> 0} [x² · sen(1/x)] = 0!
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "Sempre que vir xⁿ multiplicado por sen(...) ou cos(...) com x tendendo a 0, aplique imediatamente o Teorema do Sanduíche!"
            )

            "calc_l10" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Intuitivamente, uma função é dita CONTÍNUA se você puder desenhar todo o seu gráfico sem tirar a ponta da caneta do papel: sem furos, sem saltos e sem quebras.
                    
                    No Cálculo, a continuidade formal exige 3 condições simultâneas no ponto x = a:
                    1. f(a) existe (o ponto está preenchido no domínio).
                    2. lim_{x -> a} f(x) existe (os limites laterais convergem para o mesmo valor).
                    3. lim_{x -> a} f(x) = f(a) (a tendência coincide exatamente com o valor real do ponto).
                    
                    O grande poder prático da continuidade é o TEOREMA DO VALOR INTERMEDIÁRIO (TVI), que garante que para ir de uma altitude negativa para uma altitude positiva em uma trajetória contínua, você OBRIGATORIAMENTE cruzou o nível do mar (f(c) = 0). Isso é usado por calculadoras para encontrar raízes de equações complexas!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "As 3 Condições de Continuidade em x = a",
                        syntax = "1. f(a) ∈ ℝ   |   2. lim_{x -> a} f(x) existe   |   3. lim_{x -> a} f(x) = f(a)",
                        description = "Se qualquer uma das 3 condições falhar, a função é DESCONTÍNUA no ponto x = a.",
                        parametersInfo = "Tipos de descontinuidade: Removível (apenas um furo), Salto (degrau) ou Essencial/Infinita."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Teorema do Valor Intermediário (TVI)",
                        syntax = "Se f é contínua em [a, b] e f(a) · f(b) < 0 => existe c ∈ (a, b) tal que f(c) = 0",
                        description = "Garante a existência de pelo menos uma raiz real no intervalo se houver troca de sinal nos extremos.",
                        parametersInfo = "Também conhecido como Teorema de Bolzano."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo do TVI: Provando a Existência de Raiz",
                        codeOrFormula = "Mostrar que a equação x³ - 3x - 1 = 0 tem uma raiz real no intervalo [1, 2]",
                        explanation = """
                            Passo 1: Definir a função f(x) = x³ - 3x - 1. Como é um polinômio, é contínua em todo ℝ, e portanto contínua em [1, 2].
                            Passo 2: Avaliar f(1) no extremo esquerdo:
                            f(1) = 1³ - 3(1) - 1 = 1 - 3 - 1 = -3 (sinal NEGATIVO).
                            Passo 3: Avaliar f(2) no extremo direito:
                            f(2) = 2³ - 3(2) - 1 = 8 - 6 - 1 = +1 (sinal POSITIVO).
                            Passo 4: Como f(1) < 0 e f(2) > 0, e a função é contínua, pelo Teorema do Valor Intermediário existe pelo menos um número c ∈ (1, 2) tal que f(c) = 0!
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "O TVI é a ferramenta favorita dos professores para perguntas teóricas de prova do tipo: 'Mostre que a equação possui pelo menos uma solução real'!"
            )

            else -> null
        }
    }
}
