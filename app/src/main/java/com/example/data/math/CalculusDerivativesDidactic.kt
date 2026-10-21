package com.example.data.math

import com.example.R
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado do Módulo 2 de Cálculo I: Derivadas e Aplicações (Aulas 11 a 18).
 * Baseado no roteiro visual das provas P1 e P2 com gráficos em alta resolução.
 */
object CalculusDerivativesDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "calc_l11" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    A derivada é a ferramenta matemática que mede a TAXA DE VARIAÇÃO INSTANTÂNEA de uma grandeza em relação a outra.
                    
                    • Na Física Clássica: Se a função s(t) representa a posição de um carro ao longo do tempo, a derivada s'(t) representa a velocidade instantânea indicada no velocímetro naquele exato milissegundo!
                    • Na Geometria Analítica: A derivada f'(x₀) representa o coeficiente angular (inclinação m) da reta tangente à curva no ponto (x₀, f(x₀)).
                    
                    Geometricamente, traçamos uma reta secante ligando dois pontos P(x, f(x)) e Q(x+h, f(x+h)). Quando a distância horizontal h encolhe e tende a zero, a reta secante gira suavemente até se transformar na reta tangente no ponto de contato.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Definição Formal da Derivada por Limite",
                        syntax = "f'(x) = lim_{h -> 0} [f(x + h) - f(x)] / h",
                        description = "Calcula a inclinação instantânea como o limite da inclinação das retas secantes quando a distância h tende a zero.",
                        parametersInfo = "h: incremento horizontal; f(x+h) - f(x): variação vertical Δy; [f(x+h)-f(x)]/h: razão incremental Δy/Δx."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação da Reta Tangente no Ponto (x₀, y₀)",
                        syntax = "y - y₀ = f'(x₀) · (x - x₀)",
                        description = "Equação fundamental da reta tangente à curva no ponto onde x = x₀ e y₀ = f(x₀).",
                        parametersInfo = "m = f'(x₀): coeficiente angular da reta tangente; (x₀, y₀): ponto de tangência pertencente à curva."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo 1: Derivando f(x) = x² pela Definição",
                        codeOrFormula = "Calcular f'(x) para f(x) = x² usando limites",
                        explanation = """
                            Passo 1: Escrevemos a fórmula da definição:
                            f'(x) = lim_{h -> 0} [f(x + h) - f(x)] / h
                            Passo 2: Substituímos f(x + h) = (x + h)² e f(x) = x²:
                            f'(x) = lim_{h -> 0} [(x + h)² - x²] / h
                            Passo 3: Expandimos o quadrado da soma: (x + h)² = x² + 2xh + h²:
                            f'(x) = lim_{h -> 0} [x² + 2xh + h² - x²] / h
                            Passo 4: Cancelamos x² e colocamos h em evidência no numerador:
                            f'(x) = lim_{h -> 0} [h(2x + h)] / h = lim_{h -> 0} (2x + h) = 2x.
                            Resultado: A derivada de x² é exatamente 2x!
                        """.trimIndent()
                    ),
                    LessonExample(
                        title = "Exemplo 2: Encontrando a Reta Tangente no Ponto P(1, 1)",
                        codeOrFormula = "Equação da reta tangente a f(x) = x² em x = 1",
                        explanation = """
                            Passo 1: Encontrar o ponto: x₀ = 1, y₀ = f(1) = 1² = 1. Ponto: (1, 1).
                            Passo 2: Encontrar o coeficiente angular: m = f'(1) = 2(1) = 2.
                            Passo 3: Aplicar na equação da reta: y - 1 = 2(x - 1).
                            Passo 4: Isolar y: y - 1 = 2x - 2 => y = 2x - 1.
                            Conclusão: A reta y = 2x - 1 tangencia a parábola perfeitamente em (1, 1).
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_reta_tangente,
                goldTip = "Veja o gráfico de reta tangente no painel para ver como as retas secantes verdes vão girando até virarem a reta tangente vermelha!"
            )

            "calc_l12" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Derivadas Imediatas (Tabela Fundamental da P1 e P2)

                    Antes de combinar funções por produto, quociente ou composição, memorize as derivadas elementares:

                    ### A. Potências e Raízes
                    • **Constante:** \( \frac{d}{dx}[c] = 0 \)
                    • **Regra do Tombo (Potência):** \( \frac{d}{dx}[x^n] = n x^{n-1} \)
                    • **Linear:** \( \frac{d}{dx}[x] = 1 \)
                    • **Raiz Quadrada:** \( \frac{d}{dx}[\sqrt{x}] = \frac{d}{dx}[x^{1/2}] = \frac{1}{2}x^{-1/2} = \frac{1}{2\sqrt{x}} \)
                    • **Inverso:** \( \frac{d}{dx}\left[\frac{1}{x}\right] = \frac{d}{dx}[x^{-1}] = -x^{-2} = -\frac{1}{x^2} \)

                    ### B. Exponenciais
                    • \( \frac{d}{dx}[e^x] = e^x \)
                    • \( \frac{d}{dx}[a^x] = a^x \ln(a) \quad (a > 0, a \neq 1) \)

                    ### C. Logaritmos
                    • \( \frac{d}{dx}[\ln(x)] = \frac{1}{x} \)
                    • \( \frac{d}{dx}[\log_a(x)] = \frac{1}{x \ln(a)} \)

                    ### D. Trigonométricas Fundamentais
                    • \( \frac{d}{dx}[\sin(x)] = \cos(x) \)
                    • \( \frac{d}{dx}[\cos(x)] = -\sin(x) \)
                    • \( \frac{d}{dx}[\tan(x)] = \sec^2(x) \)
                    • \( \frac{d}{dx}[\sec(x)] = \sec(x)\tan(x) \)
                    • \( \frac{d}{dx}[\csc(x)] = -\csc(x)\cot(x) \)
                    • \( \frac{d}{dx}[\cot(x)] = -\csc^2(x) \)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra do Tombo Universal",
                        syntax = "\\frac{d}{dx}[x^n] = n x^{n-1} \\quad (\\forall n \\in \\mathbb{R})",
                        description = "Válida para qualquer expoente real positivo, negativo ou fracionário.",
                        parametersInfo = "Exemplo: (x⁻³)' = -3x⁻⁴; (x^{3/4})' = (3/4)x^{-1/4}."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Tabela Trigonométrica & Exponencial",
                        syntax = "(\\sin x)' = \\cos x, \\; (\\cos x)' = -\\sin x, \\; (\\tan x)' = \\sec^2 x, \\; (e^x)' = e^x, \\; (\\ln x)' = 1/x",
                        description = "As relações fundamentais imediatas do cálculo diferencial.",
                        parametersInfo = "Para base genérica a > 0: (aˣ)' = aˣ ln(a)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Derivando com a Tabela Imediata",
                        codeOrFormula = "y = 5x^4 - 3\\sqrt{x} + \\frac{2}{x} - 7e^x + \\tan(x)",
                        explanation = """
                            Passo 1: Termo a termo aplicando linearidade:
                            Passo 2: (5x⁴)' = 5(4x³) = 20x³.
                            Passo 3: (-3√x)' = -3 · [1/(2√x)] = -3/(2√x).
                            Passo 4: (2/x)' = 2 · (-1/x²) = -2/x².
                            Passo 5: (-7eˣ)' = -7eˣ.
                            Passo 6: (tan x)' = sec²(x).
                            Resultado Final: y' = 20x³ - 3/(2√x) - 2/x² - 7eˣ + sec²(x).
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "Sempre reescreva raízes como expoentes fracionários (ex: ∛x = x^{1/3}) e denominadores como expoentes negativos (ex: 1/x² = x⁻²) antes de aplicar a Regra do Tombo!"
            )

            "calc_l13" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Quando duas funções se multiplicam ou se dividem, NUNCA podemos simplesmente derivar cada uma separadamente!
                    
                    Por exemplo, a derivada de f · g NÃO É f' · g'. Para essas situações essenciais da P1, usamos a Regra do Produto e a Regra do Quociente.
                    
                    • Regra do Produto: 'Deriva o primeiro, copia o segundo MAIS copia o primeiro, deriva o segundo!'
                    • Regra do Quociente: 'Deriva o de cima vezes o de baixo MENOS o de cima vezes a derivada do de baixo, TUDO SOBRE o de baixo ao quadrado!'
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra do Produto (Multiplicação)",
                        syntax = "(f · g)' = f' · g + f · g'",
                        description = "Aplicada quando duas funções de x estão sendo multiplicadas.",
                        parametersInfo = "f e g são funções diferenciáveis. A ordem dos termos na soma não altera o resultado."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra do Quociente (Divisão)",
                        syntax = "(f / g)' = (f' · g - f · g') / g²",
                        description = "Aplicada para frações e divisões de funções.",
                        parametersInfo = "O sinal do numerador é estritamente NEGATIVO (-). Nunca inverta para fg' - f'g."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo 1: Regra do Produto em y = x² · sen(x)",
                        codeOrFormula = "Derivar y = x² · sen(x)",
                        explanation = """
                            Passo 1: Identificar f e g:
                            f(x) = x²     =>  f'(x) = 2x
                            g(x) = sen(x) =>  g'(x) = cos(x)
                            Passo 2: Aplicar a fórmula (f' · g + f · g'):
                            y' = (2x) · sen(x) + (x²) · cos(x).
                        """.trimIndent()
                    ),
                    LessonExample(
                        title = "Exemplo 2: Regra do Quociente em y = eˣ / x",
                        codeOrFormula = "Derivar y = eˣ / x",
                        explanation = """
                            Passo 1: Identificar numerador e denominador:
                            f = eˣ  =>  f' = eˣ
                            g = x   =>  g' = 1
                            Passo 2: Aplicar a fórmula (f'g - fg') / g²:
                            y' = [(eˣ)(x) - (eˣ)(1)] / x² = [eˣ(x - 1)] / x².
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "Na Regra do Quociente, nunca esqueça de elevar o denominador ao quadrado no final: não precisa expandir o denominador g², basta deixá-lo faturado como (g)²!"
            )

            "calc_l14" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    A Regra da Cadeia é a regra mais cobrada em qualquer prova de Cálculo da face da Terra! Ela serve para derivar funções COMPOSTAS, ou seja, quando temos uma função dentro de outra função: f(g(x)).
                    
                    A analogia perfeita é uma cebola: para chegar ao miolo, você precisa descascar camada por camada, de fora para dentro!
                    
                    A Regra de Ouro:
                    'Deriva a função de FORA mantendo a de dentro intacta, e MULTIPLICA pela derivada da de DENTRO!'
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra da Cadeia (Notação de Lagrange)",
                        syntax = "[f(g(x))]' = f'(g(x)) · g'(x)",
                        description = "Multiplicação da derivada da camada externa pela derivada da camada interna.",
                        parametersInfo = "f é a função externa e g é a função interna."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Notação de Leibniz (Diferenciais)",
                        syntax = "dy/dx = (dy/du) · (du/dx)",
                        description = "Demonstra como as taxas instantâneas se encadeiam como engrenagens de um relógio.",
                        parametersInfo = "u = g(x) funciona como variável intermediária de ligação."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo 1: Cadeia com Trigonométrica e Potência",
                        codeOrFormula = "Derivar y = sen(x³)",
                        explanation = """
                            Passo 1: Identificar a função de fora e a de dentro:
                            • Fora: sen(algo) => a derivada do seno é cos(algo).
                            • Dentro: u = x³  => a derivada de x³ é 3x².
                            Passo 2: Multiplicar a derivada de fora pela de dentro:
                            y' = cos(x³) · 3x² = 3x² · cos(x³).
                        """.trimIndent()
                    ),
                    LessonExample(
                        title = "Exemplo 2: Cadeia com Exponencial Composta",
                        codeOrFormula = "Derivar y = e^{(5x² - 2x)}",
                        explanation = """
                            Passo 1: Fora: e^(u) => derivada é o próprio e^(u).
                            Passo 2: Dentro: u = 5x² - 2x => derivada u' = 10x - 2.
                            Passo 3: Juntar pelo produto:
                            y' = e^{(5x² - 2x)} · (10x - 2) = (10x - 2) e^{(5x² - 2x)}.
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "Ao aplicar a Regra da Cadeia, a função de dentro NUNCA muda na primeira etapa! Você só deriva ela na multiplicação seguinte!"
            )

            "calc_l15" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Nem sempre uma equação vem com a variável y isolada na forma explícita y = f(x). Muitas curvas geométricas importantes (como o círculo x² + y² = 25 ou o fólio de Descartes) possuem x e y totalmente embaralhados!
                    
                    • DERIVAÇÃO IMPLÍCITA: Em vez de tentar isolar y algebricamente (o que muitas vezes é impossível), derivamos ambos os lados da equação termo a termo em relação a x. Pela Regra da Cadeia, sempre que derivarmos qualquer termo contendo y, devemos multiplicá-lo por y' (ou dy/dx)! Depois, isolamos y'.
                    
                    • DERIVAÇÃO LOGARÍTMICA: Quando a variável está na base E no expoente ao mesmo tempo (como em y = xˣ), as regras usuais falham. O truque de gênio é aplicar o logaritmo natural (ln) em ambos os lados antes de derivar, 'derrubando' o expoente para a frente!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra Fundamental da Derivação Implícita",
                        syntax = "d/dx [yⁿ] = n · yⁿ⁻¹ · y'",
                        description = "Toda derivada de termo em y gera um fator y' multiplicativo decorrente da Regra da Cadeia.",
                        parametersInfo = "Após derivar todos os termos, coloque todos os que possuem y' do lado esquerdo e fatore y'."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Técnica da Derivação Logarítmica",
                        syntax = "y = [f(x)]^{g(x)}  =>  ln(y) = g(x) · ln(f(x))",
                        description = "Transforma potências variáveis e produtos extensos em somas simples via propriedades de logaritmo.",
                        parametersInfo = "Ao derivar ln(y), o lado esquerdo sempre se torna (1/y) · y'."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo 1: Derivação Implícita do Círculo x² + y² = 25",
                        codeOrFormula = "Encontrar y' para a equação x² + y² = 25",
                        explanation = """
                            Passo 1: Derivar ambos os lados em relação a x:
                            d/dx [x²] + d/dx [y²] = d/dx [25]
                            2x + 2y · y' = 0
                            Passo 2: Isolar o termo com y':
                            2y · y' = -2x
                            Passo 3: Dividir por 2y:
                            y' = -2x / (2y) = -x / y.
                            Conclusão: A inclinação em qualquer ponto (x, y) do círculo é -x/y!
                        """.trimIndent()
                    ),
                    LessonExample(
                        title = "Exemplo 2: Derivação Logarítmica de y = xˣ",
                        codeOrFormula = "Derivar a função y = xˣ",
                        explanation = """
                            Passo 1: Aplicar ln nos dois lados:
                            ln(y) = ln(xˣ) = x · ln(x)
                            Passo 2: Derivar ambos os lados (usando implícita no lado esquerdo e produto no direito):
                            (1/y) · y' = (1) · ln(x) + x · (1/x)
                            (1/y) · y' = ln(x) + 1
                            Passo 3: Passar y multiplicando de volta:
                            y' = y · [ln(x) + 1]
                            Passo 4: Substituir y pela função original y = xˣ:
                            y' = xˣ · (ln(x) + 1).
                        """.trimIndent()
                    )
                ),
                imageResId = null,
                goldTip = "Em derivação logarítmica, NUNCA se esqueça do último passo: multiplicar de volta pela função original y!"
            )

            "calc_l16" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Calcular valores exatos em curvas complexas (como √4.05 ou sen(0.02)) de cabeça ou no papel é extremamente trabalhoso. No entanto, retas são muito simples e fáceis de calcular!
                    
                    A Aproximação Linear (ou Linearização) utiliza o gráfico da reta tangente em um ponto de contato conhecido x = a para aproximar a curva da função em pontos vizinhos bem próximos.
                    
                    Se x estiver pertinho de a, o erro da aproximação linear é minúsculo, permitindo calcular valores com precisão surpreendente sem depender de calculadora científica.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fórmula da Linearização L(x)",
                        syntax = "L(x) = f(a) + f'(a) · (x - a)",
                        description = "Representa a reta tangente à curva no ponto x = a usada como aproximação linear de f(x).",
                        parametersInfo = "f(a): valor exato no ponto base; f'(a): inclinação da reta no ponto; (x - a): variação Δx."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Diferencial dy",
                        syntax = "dy = f'(x) · dx",
                        description = "Mede a variação estimada na reta tangente provocada por uma pequena variação dx na variável independente.",
                        parametersInfo = "dy aproxima o acréscimo real Δy = f(x + dx) - f(x)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Prático: Estimando √4.05 sem Calculadora",
                        codeOrFormula = "Aproximar √4.05 usando linearização em a = 4",
                        explanation = """
                            Passo 1: Escolher a função e o ponto base conveniente próximo de 4.05:
                            f(x) = √x  e  a = 4 (pois √4 = 2 é conhecido e exato).
                            Passo 2: Derivar f(x):
                            f'(x) = 1 / (2√x).
                            Passo 3: Avaliar f(a) e f'(a) em a = 4:
                            f(4) = √4 = 2.
                            f'(4) = 1 / (2√4) = 1 / (2 · 2) = 1/4 = 0.25.
                            Passo 4: Montar a linearização L(x):
                            L(x) = 2 + 0.25(x - 4).
                            Passo 5: Calcular para x = 4.05:
                            L(4.05) = 2 + 0.25(4.05 - 4) = 2 + 0.25(0.05) = 2 + 0.0125 = 2.0125.
                            Nota: O valor real da calculadora é 2.01246... Erro menor que 0.002%!
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_aproximacao_linear,
                goldTip = "Abra o gráfico de aproximação linear para ver como a reta L(x) toca na curva e se sobrepõe a ela pertinho do ponto de contato!"
            )

            "calc_l17" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    O Estudo Completo de Funções une a 1ª Derivada e a 2ª Derivada para traçar com precisão o comportamento gráfico de qualquer função.
                    
                    • 1ª Derivada f'(x): Informa o crescimento e os extremos (máximos e mínimos).
                      - Onde f'(x) > 0, o gráfico SOBE (função crescente).
                      - Onde f'(x) < 0, o gráfico DESCE (função decrescente).
                      - Onde f'(x) = 0, a reta tangente é horizontal (ponto crítico, candidato a topo ou vale).
                      
                    • 2ª Derivada f''(x): Informa a curvatura ou concavidade.
                      - f''(x) > 0: Concavidade para CIMA (formato de copo / carinha feliz).
                      - f''(x) < 0: Concavidade para BAIXO (formato de guarda-chuva / carinha triste).
                      - Ponto de Inflexão: onde a curva muda de concavidade e f''(x) = 0.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Pontos Críticos",
                        syntax = "f'(x) = 0   ou   f'(x) não existe",
                        description = "Candidatos a máximos e mínimos locais da função.",
                        parametersInfo = "Resolva a equação f'(x) = 0 para encontrar as coordenadas x dos pontos críticos."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Teste da 2ª Derivada para Extremos",
                        syntax = "Se f'(c) = 0 e f''(c) < 0 => Máximo Local 🏔️\nSe f'(c) = 0 e f''(c) > 0 => Mínimo Local 🕳️",
                        description = "Permite classificar um ponto crítico sem precisar analisar o quadro de sinais da 1ª derivada.",
                        parametersInfo = "Se f''(c) = 0, o teste é inconclusivo e deve-se recorrer ao Teste da 1ª Derivada."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Ponto de Inflexão",
                        syntax = "f''(x) = 0  com mudança de sinal de f''(x)",
                        description = "Ponto onde a função transita de côncava para cima para côncava para baixo (ou vice-versa).",
                        parametersInfo = "Não basta f''(x) = 0; a concavidade DEVE mudar de sentido nas vizinhanças do ponto."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo: Estudo Completo de f(x) = x³ - 3x",
                        codeOrFormula = "Encontrar máximos, mínimos e inflexão de f(x) = x³ - 3x",
                        explanation = """
                            Passo 1: Calcular a 1ª derivada:
                            f'(x) = 3x² - 3 = 3(x² - 1) = 3(x - 1)(x + 1).
                            Passo 2: Achar pontos críticos (f'(x) = 0):
                            3(x² - 1) = 0 => x = 1 e x = -1.
                            Passo 3: Calcular a 2ª derivada:
                            f''(x) = 6x.
                            Passo 4: Classificar os pontos críticos pela 2ª derivada:
                            • Em x = -1: f''(-1) = 6(-1) = -6 < 0 => Máximo Local no ponto (-1, 2) 🏔️
                            • Em x = 1: f''(1) = 6(1) = 6 > 0 => Mínimo Local no ponto (1, -2) 🕳️
                            Passo 5: Achar ponto de inflexão:
                            f''(x) = 6x = 0 => x = 0. Como 6x muda de sinal em x = 0, temos Inflexão em (0, 0).
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_estudo_funcao_max_min,
                goldTip = "Visualize no gráfico de estudo de funções como o topo da colina (máximo) tem concavidade para baixo e o fundo do poço (mínimo) tem concavidade para cima!"
            )

            "calc_l18" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    Nesta aula final de Derivadas, juntamos duas aplicações supremas que fecham a P2 com chave de ouro:
                    
                    • REGRA DE L'HÔPITAL: O atalho definitivo para limites com indeterminação 0/0 ou ∞/∞. Em vez de fazer malabarismos de fatoração ou racionalização, L'Hôpital nos autoriza a simplesmente derivar o numerador e o denominador SEPARADAMENTE até a indeterminação sumir!
                    
                    • PROBLEMAS DE OTIMIZAÇÃO: A aplicação mais famosa e valiosa do Cálculo no mundo real e na engenharia! Como construir uma caixa de volume máximo usando o menor gasto de papelão? Como minimizar o custo de transporte de energia?
                    O procedimento é sempre: 1. Escrever a função a otimizar; 2. Usar as restrições para deixá-la em função de UMA só variável; 3. Derivar, igualar a zero (ponto crítico) e classificar!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Regra de L'Hôpital (Shortcut de Limites)",
                        syntax = "lim_{x -> a} [f(x) / g(x)] = lim_{x -> a} [f'(x) / g'(x)]",
                        description = "Válida exclusivamente para indeterminações do tipo 0/0 ou ±∞/±∞.",
                        parametersInfo = "Cuidado: derive o de cima sozinho e o de baixo sozinho! NÃO aplique a Regra do Quociente aqui!"
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Receita Universal de Otimização",
                        syntax = "1. Função Objetivo f(x)  |  2. Derivar f'(x) = 0  |  3. Teste da 2ª Derivada",
                        description = "Encontra a dimensão, preço ou quantidade que produz o máximo ou mínimo global do problema.",
                        parametersInfo = "Se f''(x) < 0 no ponto crítico, temos garantia de MÁXIMO GLOBAL."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo 1: Regra de L'Hôpital em lim_{x -> 0} [sen(x) / x]",
                        codeOrFormula = "Resolver o limite fundamental usando L'Hôpital",
                        explanation = """
                            Passo 1: Testar o limite direto: sen(0)/0 = 0/0 (indeterminação autorizada).
                            Passo 2: Derivar numerador: (sen x)' = cos x.
                            Passo 3: Derivar denominador: (x)' = 1.
                            Passo 4: Avaliar o novo limite:
                            lim_{x -> 0} [cos(x) / 1] = cos(0) / 1 = 1 / 1 = 1. Resolvido em 2 linhas!
                        """.trimIndent()
                    ),
                    LessonExample(
                        title = "Exemplo 2: Otimização de Área com Perímetro Fixo",
                        codeOrFormula = "Encontrar o retângulo de perímetro 100m com maior área possível",
                        explanation = """
                            Passo 1: Equação do perímetro (restrição): 2x + 2y = 100 => x + y = 50 => y = 50 - x.
                            Passo 2: Função Objetivo (Área): A(x) = x · y = x(50 - x) = 50x - x².
                            Passo 3: Derivar para achar o ponto crítico:
                            A'(x) = 50 - 2x = 0 => 2x = 50 => x = 25m.
                            Passo 4: Encontrar y: y = 50 - 25 = 25m.
                            Passo 5: Verificar concavidade: A''(x) = -2 < 0 (garante área MÁXIMA).
                            Conclusão: O retângulo de maior área é um quadrado de 25m × 25m, com área de 625 m²!
                        """.trimIndent()
                    )
                ),
                imageResId = R.drawable.grafico_estudo_funcao_max_min,
                goldTip = "Na Regra de L'Hôpital, sempre confira se deu 0/0 ou ∞/∞ antes de derivar! Se não for indeterminação, aplicar L'Hôpital produzirá uma resposta errada!"
            )

            else -> null
        }
    }
}
