package com.example.data.math

import com.example.R
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

object CalculusIntegralsDidactic {

    private val bundles = mapOf(
        "calc_l19" to CalculusDidacticBundle(
            theoryMarkdown = """
                # O que significa "Integrar"?

                Integrar uma função é, essencialmente, **fazer o caminho inverso da derivada** (por isso o processo também se chama **antiderivação** ou **primitivação**).
                Se você já sabe derivar, você já conhece metade da integração.

                Quando dizemos que:
                \[ \frac{d}{dx}[F(x)] = f(x) \]

                A operação inversa é expressa como:
                \[ \int f(x) \, dx = F(x) + C \]

                • **O símbolo \(\int\):** É o sinal de integral (um "S" estilizado para representar uma soma contínua de infinitos retângulos de base infinitesimal).
                • **\(f(x)\):** É o **integrando** (a função que você deseja integrar).
                • **\(dx\):** Indica que a variável de integração é \(x\) (o elemento infinitesimal de largura).
                • **\(F(x)\):** É uma **primitiva** (antiderivada) de \(f(x)\).
                • **\(+ C\):** É a **constante de integração**. Como a derivada de qualquer constante pura é zero (\(\frac{d}{dx}[5] = 0\)), existem infinitas primitivas que diferem apenas por uma constante.
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Equação Fundamental da Antiderivada",
                    syntax = "\\int f(x) \\, dx = F(x) + C \\iff F'(x) = f(x)",
                    description = "A integral indefinida representa a família de todas as primitivas possíveis de f(x).",
                    parametersInfo = "C é um número real arbitrário chamado constante de integração."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Identificação de Primitiva Imediata",
                    codeOrFormula = "\\int 2x \\, dx = x^2 + C",
                    explanation = "A derivada de x² é 2x. A derivada de (x² + 7) também é 2x. Logo, a antiderivada geral é x² + C."
                )
            ),
            imageResId = R.drawable.grafico_integral_area,
            goldTip = "Ao terminar qualquer integral, derive sua resposta: o resultado DEVE ser exatamente o integrando original!"
        ),

        "calc_l20" to CalculusDidacticBundle(
            theoryMarkdown = """
                # A Mecânica Básica: Regra da Potência

                Assim como na derivação existem regras diretas (regra do tombo), na integração temos as regras imediatas que desfazem o processo.

                ### Regra da Potência (o inverso da "regra do tombo")
                Na derivada, você multiplica pelo expoente e subtrai 1. Na integral, você **soma 1 ao expoente e divide pelo novo expoente**:

                \[ \int x^n \, dx = \frac{x^{n+1}}{n+1} + C \quad (n \neq -1) \]

                • **Exemplo 1:**
                \[ \int x^3 \, dx = \frac{x^{3+1}}{3+1} + C = \frac{x^4}{4} + C \]

                • **Exemplo 2 (Constante pura 1):**
                \[ \int 1 \, dx = \int x^0 \, dx = \frac{x^1}{1} + C = x + C \]

                • **Exemplo 3 (com radical/raiz):**
                \[ \int \sqrt{x} \, dx = \int x^{1/2} \, dx = \frac{x^{1/2 + 1}}{\frac{1}{2} + 1} + C = \frac{x^{3/2}}{3/2} + C = \frac{2}{3}x^{3/2} + C \]
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Regra da Potência",
                    syntax = "\\int x^n \\, dx = \\frac{x^{n+1}}{n+1} + C \\quad (n \\neq -1)",
                    description = "Válida para qualquer expoente real positivo, negativo ou fracionário, exceto n = -1.",
                    parametersInfo = "Para raízes, converta em expoente fracionário x^(p/q) antes de integrar."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Integral de Expoente Negativo",
                    codeOrFormula = "\\int \\frac{1}{x^3} \\, dx = \\int x^{-3} \\, dx = \\frac{x^{-2}}{-2} + C = -\\frac{1}{2x^2} + C",
                    explanation = "Reescreva a fração com potência negativa x^(-3), some 1 ao expoente (-2) e divida por -2."
                )
            ),
            imageResId = R.drawable.grafico_integral_area,
            goldTip = "Cuidado com expoentes fracionários: dividir por 3/2 é a mesma coisa que multiplicar por 2/3!"
        ),

        "calc_l21" to CalculusDidacticBundle(
            theoryMarkdown = """
                # Exponenciais, Logaritmos e Funções Trigonométricas

                ### A. O caso particular n = -1 (Logaritmo Natural)
                Se \(n = -1\), teríamos uma divisão por zero na regra da potência. A função cuja derivada é \(1/x\) é o logaritmo natural:
                \[ \int \frac{1}{x} \, dx = \ln|x| + C \]

                ### B. Funções Exponenciais
                \[ \int e^x \, dx = e^x + C \]
                \[ \int a^x \, dx = \frac{a^x}{\ln(a)} + C \quad (a > 0, a \neq 1) \]

                ### C. Funções Trigonométricas Imediatas
                Lembrando que derivamos \(\sin(x) \to \cos(x)\) e \(\cos(x) \to -\sin(x)\):
                • \(\int \cos(x) \, dx = \sin(x) + C\)
                • \(\int \sin(x) \, dx = -\cos(x) + C\) *(atenção redobrada ao sinal negativo!)*
                • \(\int \sec^2(x) \, dx = \tan(x) + C\)
                • \(\int \sec(x)\tan(x) \, dx = \sec(x) + C\)
                • \(\int \csc^2(x) \, dx = -\cot(x) + C\)
                • \(\int \csc(x)\cot(x) \, dx = -\csc(x) + C\)

                ### D. Inversas Trigonométricas Fundamentais
                • \(\int \frac{1}{1 + x^2} \, dx = \arctan(x) + C\)
                • \(\int \frac{1}{\sqrt{1 - x^2}} \, dx = \arcsin(x) + C\)
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Integrais Trigonométricas",
                    syntax = "\\int \\cos(x) \\, dx = \\sin(x) + C, \\quad \\int \\sin(x) \\, dx = -\\cos(x) + C",
                    description = "A integral do seno gera cosseno com sinal trocado.",
                    parametersInfo = "Verifique o sinal derivando: a derivada de -cos(x) é -(-sin(x)) = sin(x)."
                ),
                FormulaOrSyntaxItem(
                    termOrFunction = "Arco-Tangente",
                    syntax = "\\int \\frac{1}{1 + x^2} \\, dx = \\arctan(x) + C",
                    description = "Forma fundamental que surge frequentemente em frações parciais.",
                    parametersInfo = "Denominador quadrático irredutível 1 + x²."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Integral de Exponencial de Base Geral",
                    codeOrFormula = "\\int 2^x \\, dx = \\frac{2^x}{\\ln(2)} + C",
                    explanation = "Ao derivar 2ˣ surge o fator multiplicativo ln(2). Para integrar, dividimos por ln(2)."
                )
            ),
            imageResId = R.drawable.grafico_integral_area,
            goldTip = "O erro mais comum em provas é esquecer o sinal negativo na integral de sin(x): ∫ sin(x) dx = -cos(x) + C!"
        ),

        "calc_l22" to CalculusDidacticBundle(
            theoryMarkdown = """
                # Propriedades de Linearidade da Integral

                Você pode separar termos somados e "tirar constantes para fora" da integral:

                1. **Multiplicação por constante:**
                \[ \int k \cdot f(x) \, dx = k \int f(x) \, dx \]

                2. **Soma e subtração termo a termo:**
                \[ \int [f(x) \pm g(x)] \, dx = \int f(x) \, dx \pm \int g(x) \, dx \]

                ### Exemplo Prático Combinando as Regras:
                Calcule \(\int \left(4x^3 - 3\cos(x) + \frac{5}{x}\right) \, dx\):

                \[ = 4 \int x^3 \, dx - 3 \int \cos(x) \, dx + 5 \int \frac{1}{x} \, dx \]
                \[ = 4\left(\frac{x^4}{4}\right) - 3(\sin(x)) + 5(\ln|x|) + C \]
                \[ = x^4 - 3\sin(x) + 5\ln|x| + C \]
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Operador Linear",
                    syntax = "\\int [\\alpha f(x) + \\beta g(x)] \\, dx = \\alpha \\int f(x) \\, dx + \\beta \\int g(x) \\, dx",
                    description = "A integração é uma transformação linear no espaço das funções integráveis.",
                    parametersInfo = "Permite decompor polinômios complexos em parcelas elementares imediatas."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Polinômio Completo",
                    codeOrFormula = "\\int (6x^2 - 4x + 7) \\, dx = 6\\left(\\frac{x^3}{3}\\right) - 4\\left(\\frac{x^2}{2}\\right) + 7x + C = 2x^3 - 2x^2 + 7x + C",
                    explanation = "Aplique a linearidade tirando os coeficientes e integrando cada potência de x individualmente."
                )
            ),
            imageResId = R.drawable.grafico_integral_area,
            goldTip = "Mesmo tendo várias parcelas somadas, você só precisa escrever um único '+ C' no final!"
        ),

        "calc_l23" to CalculusDidacticBundle(
            theoryMarkdown = """
                # Método 1: Substituição (u-substituição)

                > **Ideia central:** É a Regra da Cadeia ao contrário. Procure no integrando uma expressão "de dentro" \(u = g(x)\) cuja derivada \(g'(x)\) também esteja multiplicando o \(dx\).

                ### Passo a Passo Rigoroso:
                Calcule \(\int 2x \cos(x^2) \, dx\):

                1. **Escolha \(u\):** Note que a derivada de \(x^2\) é \(2x\). Então faça \(u = x^2\).
                2. **Diferencie:** \(du = 2x \, dx\).
                3. **Substitua na integral original:**
                \[ \int \cos(\underbrace{x^2}_{u}) \cdot \underbrace{2x \, dx}_{du} = \int \cos(u) \, du \]
                4. **Resolva a integral simples na variável \(u\):**
                \[ \int \cos(u) \, du = \sin(u) + C \]
                5. **Volte para a variável original \(x\):**
                \[ = \sin(x^2) + C \]
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Teorema da Substituição",
                    syntax = "\\int f(g(x)) g'(x) \\, dx = \\int f(u) \\, du, \\quad \\text{onde } u = g(x)",
                    description = "Inversão formal da regra da cadeia para derivadas de funções compostas.",
                    parametersInfo = "Se faltar uma constante numérica multiplicativa, ajuste multiplicando e dividindo por essa constante."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Substituição com Ajuste de Constante",
                    codeOrFormula = "\\int x \\sqrt{x^2 + 1} \\, dx = \\frac{1}{2} \\int \\sqrt{u} \\, du = \\frac{1}{2}\\left(\\frac{2}{3}u^{3/2}\\right) + C = \\frac{1}{3}(x^2+1)^{3/2} + C",
                    explanation = "Faça u = x² + 1, logo du = 2x dx => x dx = du/2. Substitua e aplique a regra da potência."
                )
            ),
            imageResId = R.drawable.grafico_integral_area,
            goldTip = "O segredo da u-substituição é identificar a função interna g(x) e conferir se a sua derivada aparece multiplicando o dx!"
        ),

        "calc_l24" to CalculusDidacticBundle(
            theoryMarkdown = """
                # Método 2: Integração por Partes

                > **Ideia central:** Desfaz a Regra do Produto da derivada. Use quando tiver o produto de duas funções de naturezas diferentes (ex: polinômio vezes exponencial, ou logaritmo sozinho).

                ### A Fórmula de Integração por Partes:
                \[ \int u \, dv = u \cdot v - \int v \, du \]

                *Mnemônica clássica: "Um Dia Vi Uma Vaca Vestida De Uniforme"*

                ### A Regra de Prioridade LIATE para Escolher \(u\):
                1. **L** - Logaritmos (\(\ln x, \log x\))
                2. **I** - Inversas Trigonométricas (\(\arcsin x, \arctan x\))
                3. **A** - Algébricas / Polinomiais (\(x, x^2, x^3\))
                4. **T** - Trigonométricas (\(\sin x, \cos x\))
                5. **E** - Exponenciais (\(e^x, 2^x\))

                ### Exemplo Prático: \(\int x e^x \, dx\)
                1. Pela regra LIATE, a algébrica \(x\) vem antes da exponencial \(e^x\):
                • \(u = x \implies du = dx\)
                • \(dv = e^x \, dx \implies v = \int e^x \, dx = e^x\)
                2. Aplicando a fórmula:
                \[ \int x e^x \, dx = (x)(e^x) - \int (e^x) \, dx \]
                \[ = x e^x - e^x + C = e^x(x - 1) + C \]
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Fórmula por Partes",
                    syntax = "\\int u \\, dv = u \\cdot v - \\int v \\, du",
                    description = "Transforma a integral de um produto complexo em uma integral mais simples.",
                    parametersInfo = "u é diferenciado (du) e dv é integrado (v)."
                ),
                FormulaOrSyntaxItem(
                    termOrFunction = "Ordem de Escolha LIATE",
                    syntax = "\\text{Logarítmica} > \\text{Inversa} > \\text{Algébrica} > \\text{Trigonométrica} > \\text{Exponencial}",
                    description = "Prioridade decrescente para a escolha da função u.",
                    parametersInfo = "A função restante associada ao dx torna-se dv."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Integral de Logaritmo Natural",
                    codeOrFormula = "\\int \\ln(x) \\, dx = x\\ln(x) - \\int x \\cdot \\frac{1}{x} \\, dx = x\\ln(x) - x + C",
                    explanation = "Escolha u = ln(x) => du = 1/x dx, e dv = dx => v = x. Aplique a fórmula u*v - ∫ v du."
                )
            ),
            imageResId = R.drawable.grafico_integral_area,
            goldTip = "Ao integrar ln(x) ou arctan(x) sozinhos, use dv = dx! Isso transforma uma função transcendental em uma fração algébrica tratável."
        ),

        "calc_l25" to CalculusDidacticBundle(
            theoryMarkdown = """
                # Método 3: Frações Parciais

                > **Ideia central:** Desmontar uma fração polinomial complexa em uma soma de frações simples cujas integrais resultam em logaritmos naturais ou arco-tangentes.

                ### Exemplo: Calcule \(\int \frac{1}{x^2 - 1} \, dx\)

                1. **Fatore o denominador:**
                \[ x^2 - 1 = (x - 1)(x + 1) \]

                2. **Monte a decomposição em frações simples:**
                \[ \frac{1}{(x-1)(x+1)} = \frac{A}{x-1} + \frac{B}{x+1} \]

                3. **Iguale os numeradores:**
                \[ 1 = A(x + 1) + B(x - 1) \]
                • Se \(x = 1\): \(1 = A(2) \implies A = \frac{1}{2}\)
                • Se \(x = -1\): \(1 = B(-2) \implies B = -\frac{1}{2}\)

                4. **Substitua e integre termo a termo:**
                \[ \int \left( \frac{1/2}{x - 1} - \frac{1/2}{x + 1} \right) dx = \frac{1}{2} \ln|x - 1| - \frac{1}{2} \ln|x + 1| + C \]
                \[ = \frac{1}{2} \ln\left|\frac{x-1}{x+1}\right| + C \]
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Decomposição em Frações Parciais",
                    syntax = "\\frac{P(x)}{(x - r_1)(x - r_2)} = \\frac{A}{x - r_1} + \\frac{B}{x - r_2}",
                    description = "Válida quando o grau do numerador é estritamente menor que o grau do denominador.",
                    parametersInfo = "Se o grau do numerador for maior ou igual, realize primeiro a divisão euclidiana de polinômios."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Integração Resultando em Logaritmos",
                    codeOrFormula = "\\int \\frac{5x - 3}{(x+1)(x-3)} \\, dx = 2\\ln|x+1| + 3\\ln|x-3| + C",
                    explanation = "Decomponha 5x-3 = A(x-3) + B(x+1). Para x=3, B=3; para x=-1, A=2. Integre cada fração."
                )
            ),
            imageResId = R.drawable.grafico_integral_area,
            goldTip = "Sempre confira se o grau do numerador é menor que o do denominador. Se não for, divida os polinômios antes de decompor!"
        ),

        "calc_l26" to CalculusDidacticBundle(
            theoryMarkdown = """
                # A Integral Definida e o Teorema Fundamental do Cálculo

                Para calcular uma integral com limites definidos de \(x = a\) até \(x = b\) (como \(\int_{0}^{2} 3x^2 \, dx\)), o processo tem 2 passos:

                1. **Ache uma primitiva \(F(x)\)** (não precisa colocar o \(+C\), pois ele se cancela na subtração):
                \[ F(x) = \int 3x^2 \, dx = 3 \cdot \frac{x^3}{3} = x^3 \]

                2. **Aplique o Teorema Fundamental do Cálculo (TFC)** \([F(b) - F(a)]\):
                \[ \int_{0}^{2} 3x^2 \, dx = \Big[ x^3 \Big]_{0}^{2} = (2)^3 - (0)^3 = 8 - 0 = 8 \]

                ### Interpretação Geométrica:
                Esse valor numérico final representa a **área líquida** sob a parábola \(y = 3x^2\) delimitada pelo eixo \(x\) entre \(x = 0\) e \(x = 2\).
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem(
                    termOrFunction = "Teorema Fundamental do Cálculo (TFC Parte 2)",
                    syntax = "\\int_{a}^{b} f(x) \\, dx = F(b) - F(a) = \\Big[ F(x) \\Big]_{a}^{b}",
                    description = "Conecta a taxa de variação (derivada) ao acúmulo total (integral de área).",
                    parametersInfo = "f deve ser contínua em todo o intervalo fechado [a, b]."
                )
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Cálculo de Área sob Reta",
                    codeOrFormula = "\\int_{1}^{3} (2x + 1) \\, dx = \\Big[ x^2 + x \\Big]_{1}^{3} = (3^2 + 3) - (1^2 + 1) = 12 - 2 = 10",
                    explanation = "Calcule a primitiva F(x) = x² + x, avalie no limite superior x=3 e subtraia a avaliação no limite inferior x=1."
                )
            ),
            imageResId = R.drawable.grafico_integral_area,
            goldTip = "A constante + C não é necessária na integral definida: (F(b) + C) - (F(a) + C) = F(b) - F(a)!"
        )
    )

    fun get(lessonId: String): CalculusDidacticBundle? = bundles[lessonId]
}
