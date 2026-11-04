package com.example.data.math.didactic.calculus

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Cálculo I — Módulo 2: Derivadas e Aplicações (Parte 1: Aulas 11 a 14).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em James Stewart, Guidorizzi e OpenStax Calculus Vol 1.
 */
object CalculusDerivativesPart1 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "calc_l11" -> bundleCalcL11
            "calc_l12" -> bundleCalcL12
            "calc_l13" -> bundleCalcL13
            "calc_l14" -> bundleCalcL14
            else -> null
        }
    }

    // =========================================================================
    // AULA 11: O que é a Derivada (Na Prática)?
    // =========================================================================
    private val bundleCalcL11 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
No Módulo 1, compreendemos o limite e testemunhamos como a reta secante se transforma na reta tangente quando o acréscimo temporal ou espacial converge a zero. Agora, damos o passo definitivo: a derivada deixa de ser apenas a inclinação estática de uma reta em um ponto fixo e se transforma em uma **nova função** \( f'(x) \). Esta função opera como um verdadeiro "velocímetro infinitesimal", revelando a taxa de variação instantânea para qualquer ponto do domínio.

## 2. Objetivos de Aprendizagem
- Definir formalmente a derivada como uma função de variável real via Quociente de Newton.
- Compreender o Teorema Fundamental: "Se uma função é diferenciável em um ponto, então ela é necessariamente contínua nesse ponto" e entender por que a recíproca é falsa.
- Identificar geometricamente pontos onde a derivada não existe: bicos/pontos angulosos, cúspides e retas tangentes verticais.
- Dominar as diferentes notações históricas da diferenciação (Leibniz: \( \frac{df}{dx} \), Lagrange: \( f'(x) \), Cauchy: \( D f(x) \)).

## 3. Pré-requisitos
- Limite bilateral e Quociente de Newton (Aula 10).
- Continuidade de funções e limites laterais (Aulas 2 e 6).
- Equação da reta e coeficiente angular.

## 4. Explicação Teórica Aprofundada
### A Função Derivada
A derivada de uma função \( f \) em relação à variável independente \( x \) é a função cujo valor em cada ponto \( x \) é dado pelo limite:
\[ f'(x) = \lim_{h \to 0} \frac{f(x + h) - f(x)}{h} \]
desde que esse limite exista e seja um número real finito. O domínio de \( f' \), denotado por \( D_{f'} \), é o conjunto de todos os pontos onde esse limite existe, sendo sempre um subconjunto do domínio de \( f \) (\( D_{f'} \subseteq D_f \)).

### Diferenciabilidade Implica Continuidade
Um dos pilares teóricos mais importantes da análise matemática afirma:
\[ f \text{ é diferenciável em } a \implies f \text{ é contínua em } a \]
*Demonstração Intuitiva:*
Queremos provar que \( \lim_{x \to a} [f(x) - f(a)] = 0 \). Podemos reescrever para \( x \neq a \):
\[ f(x) - f(a) = \frac{f(x) - f(a)}{x - a} \cdot (x - a) \]
Tomando o limite com \( x \to a \) e aplicando a Lei do Produto:
\[ \lim_{x \to a} [f(x) - f(a)] = \left(\lim_{x \to a} \frac{f(x) - f(a)}{x - a}\right) \cdot \left(\lim_{x \to a} (x - a)\right) = f'(a) \cdot 0 = 0 \]
Logo, \( \lim_{x \to a} f(x) = f(a) \), provando a continuidade.

### A Recíproca é Falsa: O Clássico Ponto Anguloso
A continuidade é condição necessária, mas **NÃO suficiente** para a diferenciabilidade.
O contraexemplo supremo é a função modular \( f(x) = |x| \) em \( x = 0 \):
- A função é evidentemente contínua na origem (\( f(0) = 0 = \lim_{x \to 0} |x| \)).
- No entanto, investigando os limites laterais do quociente de Newton:
  \[ \lim_{h \to 0^+} \frac{|0 + h| - |0|}{h} = \lim_{h \to 0^+} \frac{h}{h} = 1 \]
  \[ \lim_{h \to 0^-} \frac{|0 + h| - |0|}{h} = \lim_{h \to 0^-} \frac{-h}{h} = -1 \]
Como os limites laterais diferem (\( 1 \neq -1 \)), a derivada bilateral **não existe em \( x = 0 \)**. O gráfico possui uma quina abrupta ("bico"), impedindo a existência de uma reta tangente única.

## 5. Definições e Conceitos
- **Diferenciabilidade:** Existência de reta tangente única e não-vertical no ponto.
- **Ponto Anguloso / Bico:** Ponto onde as derivadas laterais existem mas são distintas.
- **Ponto de Tangência Vertical:** Ponto onde o quociente de diferenças diverge para \( \pm\infty \), como em \( f(x) = \sqrt[3]{x} \) na origem.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Dedução da Derivada de \( f(x) = x^2 \) por Limite
Determine a função derivada \( f'(x) \) para \( f(x) = x^2 \).
- **Passo 1 (Montagem do quociente):**
  \[ f(x + h) = (x + h)^2 = x^2 + 2xh + h^2 \]
  \[ f(x + h) - f(x) = (x^2 + 2xh + h^2) - x^2 = 2xh + h^2 \]
- **Passo 2 (Fatoração e cancelamento):**
  \[ \frac{f(x + h) - f(x)}{h} = \frac{h(2x + h)}{h} = 2x + h \quad (h \neq 0) \]
- **Passo 3 (Passagem ao limite):**
  \[ f'(x) = \lim_{h \to 0} (2x + h) = 2x + 0 = 2x \]
- **Conclusão:** Para qualquer \( x \in \mathbb{R} \), a inclinação instantânea da parábola é dada por \( f'(x) = 2x \). No ponto \( x = 3 \), a inclinação vale \( 2(3) = 6 \).

### Exemplo 2: Derivada da Função Recíproca \( f(x) = \frac{1}{x} \)
- **Passo 1:**
  \[ f(x + h) - f(x) = \frac{1}{x + h} - \frac{1}{x} = \frac{x - (x + h)}{x(x + h)} = \frac{-h}{x(x + h)} \]
- **Passo 2:** Dividindo por \( h \):
  \[ \frac{f(x + h) - f(x)}{h} = \frac{-h}{x(x + h) \cdot h} = \frac{-1}{x(x + h)} \]
- **Passo 3:** Tomando \( h \to 0 \):
  \[ f'(x) = \lim_{h \to 0} \frac{-1}{x(x + h)} = \frac{-1}{x^2} \]
- **Conclusão:** \( \left(\frac{1}{x}\right)' = -\frac{1}{x^2} \) para todo \( x \neq 0 \).

## 7. Aplicações Práticas e Modelagem
- **Velocidade de Reação Química:** A velocidade de consumo de um reagente em função do tempo é a derivada temporal da concentração: \( v = -\frac{d[A]}{dt} \).
- **Corrente Elétrica Instantânea:** A corrente elétrica que atravessa um condutor é a derivada temporal da carga que cruza a seção transversal: \( i(t) = \frac{dq}{dt} \).

## 8. Exercícios de Fixação
1. Calcule pela definição com limite a derivada de \( f(x) = 5x - 7 \).
2. Mostre que a função \( f(x) = \sqrt[3]{x} \) não é diferenciável em \( x = 0 \).
3. Se \( f \) é diferenciável em \( x = 4 \) e \( f'(4) = -3 \), quanto vale \( \lim_{x \to 4} f(x) \)?

## 9. Correção Comentada dos Exercícios
1. \( \frac{f(x+h) - f(x)}{h} = \frac{[5(x+h) - 7] - (5x - 7)}{h} = \frac{5h}{h} = 5 \). O limite quando \( h \to 0 \) é 5. A derivada de qualquer reta \( y = mx + b \) é constante e igual ao seu coeficiente angular \( m \).
2. Quociente na origem: \( \lim_{h \to 0} \frac{\sqrt[3]{0+h} - 0}{h} = \lim_{h \to 0} \frac{h^{1/3}}{h} = \lim_{h \to 0} \frac{1}{h^{2/3}} = \frac{1}{0^+} = +\infty \). Como o limite diverge para infinito, a reta tangente é perfeitamente vertical e a função não possui derivada finita em 0.
3. Como \( f \) é diferenciável em 4, ela é obrigatoriamente contínua em 4. Pela definição de continuidade: \( \lim_{x \to 4} f(x) = f(4) \).

## 10. Desafio de Domínio
Dada a função \( f(x) = \begin{cases} x^2 \sin(1/x), & x \neq 0 \\ 0, & x = 0 \end{cases} \), use a definição por limite para provar que \( f \) é diferenciável em \( x = 0 \) e calcule \( f'(0) \).

## 11. Resumo e Pontos-Chave
- A derivada é uma nova função obtida pelo limite do Quociente de Newton: \( f'(x) = \lim_{h \to 0} \frac{f(x+h)-f(x)}{h} \).
- Diferenciabilidade é mais exigente que continuidade: toda função diferenciável é contínua, mas funções contínuas podem ter quinas ou tangentes verticais.
- A derivada mede a taxa de variação instantânea de qualquer grandeza científica.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 2.8: "A Derivada como uma Função".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 7: "A Derivada".
- STRANG, Gilbert; HERMAN, Edwin. *Calculus Volume 1*. OpenStax, 2016. Seção 3.2: "The Derivative as a Function".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Definição Geral da Função Derivada",
                syntax = "f'(x) = \\lim_{h \\to 0} \\frac{f(x + h) - f(x)}{h}",
                description = "Limite do quociente de Newton para uma abscissa genérica x.",
                parametersInfo = "Gera uma nova função f'(x) que mapeia cada ponto à sua inclinação."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Notação de Leibniz para Derivada",
                syntax = "\\frac{df}{dx} = \\frac{d}{dx}[f(x)]",
                description = "Expressa a razão infinitesimal entre o diferencial df e dx.",
                parametersInfo = "Ideal para cálculos de taxa de variação e regra da cadeia."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Demonstração da Falha de Diferenciabilidade em |x|",
                codeOrFormula = "\\lim_{h \\to 0^+} \\frac{h}{h} = 1 \\quad \\neq \\quad \\lim_{h \\to 0^-} \\frac{-h}{h} = -1",
                explanation = "As derivadas laterais à direita e à esquerda discordam, provando que bicos não admitem derivada."
            )
        ),
        goldTip = "Lembre-se da máxima fundamental do Cálculo: 'Diferenciabilidade implica continuidade, mas continuidade NÃO garante diferenciabilidade'. Guarde o gráfico em V do módulo como seu contraexemplo eterno!"
    )

    // =========================================================================
    // AULA 12: As Regras Rápidas de Derivação
    // =========================================================================
    private val bundleCalcL12 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Calcular a derivada de uma função pela definição com limite \( \lim_{h \to 0} \frac{f(x+h) - f(x)}{h} \) é matematicamente rigoroso, mas extremamente lento e tedioso para expressões complexas. Para que o Cálculo se tornasse a linguagem operacional de toda a física e engenharia, os pioneiros do cálculo desenvolveram **regras de derivação direta**. Dominando essas fórmulas fundamentais, você será capaz de diferenciar funções polinomiais, trigonométricas e exponenciais em questão de segundos.

## 2. Objetivos de Aprendizagem
- Enunciar e aplicar a Regra da Potência (Regra do Tombo) para expoentes inteiros, fracionários e negativos.
- Compreender por que a derivada de uma constante é identicamente nula.
- Memorizar e utilizar as derivadas das funções transcendentais elementares: exponencial natural (\( e^x \)), logaritmo natural (\( \ln x \)), seno (\( \sin x \)) e cosseno (\( \cos x \)).
- Aplicar a propriedade da linearidade da derivada (combinação linear).

## 3. Pré-requisitos
- Definição da função derivada via limite (Aula 11).
- Propriedades de expoentes, potenciação e radiciação (\( \sqrt[n]{x^m} = x^{m/n} \)).
- Limite trigonométrico fundamental (Aula 9).

## 4. Explicação Teórica Aprofundada
### A Regra da Função Constante
Se \( f(x) = c \) (uma constante real fixa), o gráfico é uma reta horizontal paralela ao eixo \( x \). Como o valor não se altera (\( \Delta y = 0 \)), a inclinação é nula em qualquer ponto:
\[ \frac{d}{dx}[c] = 0 \]

### A Regra da Potência (Regra do Tombo)
Para qualquer número real \( n \):
\[ \frac{d}{dx}[x^n] = n x^{n-1} \]
*Demonstração para \( n \) inteiro positivo via Binômio de Newton:*
Expandindo \( (x + h)^n = x^n + n x^{n-1} h + \frac{n(n-1)}{2} x^{n-2} h^2 + \dots + h^n \):
\[ \frac{(x + h)^n - x^n}{h} = n x^{n-1} + \frac{n(n-1)}{2} x^{n-2} h + \dots + h^{n-1} \]
Ao tomarmos o limite com \( h \to 0 \), todos os termos multiplicados por \( h \) extinguem-se, restando exclusivamente:
\[ \lim_{h \to 0} \frac{(x + h)^n - x^n}{h} = n x^{n-1} \]
Essa regra aplica-se igualmente para frações e potências negativas:
- Raiz quadrada: \( \frac{d}{dx}[\sqrt{x}] = \frac{d}{dx}[x^{1/2}] = \frac{1}{2} x^{-1/2} = \frac{1}{2\sqrt{x}} \).
- Fração inversa: \( \frac{d}{dx}\left[\frac{1}{x}\right] = \frac{d}{dx}[x^{-1}] = -1 x^{-2} = -\frac{1}{x^2} \).

### As Funções Transcendentais Canônicas
1. **Exponencial Natural:** A função \( f(x) = e^x \) é a única função não-trivial cuja taxa de variação instantânea é rigorosamente igual ao próprio valor da função em cada ponto:
   \[ \frac{d}{dx}[e^x] = e^x \]
2. **Logaritmo Natural:**
   \[ \frac{d}{dx}[\ln x] = \frac{1}{x} \quad (x > 0) \]
3. **Funções Trigonométricas:**
   \[ \frac{d}{dx}[\sin x] = \cos x \qquad \frac{d}{dx}[\cos x] = -\sin x \]
   *(Atenção: A derivada do cosseno introduz obrigatoriamente um sinal negativo!)*

### Linearidade do Operador Diferencial
Para quaisquer constantes reais \( \alpha, \beta \) e funções diferenciáveis \( f, g \):
\[ \frac{d}{dx}[\alpha f(x) + \beta g(x)] = \alpha \frac{df}{dx} + \beta \frac{dg}{dx} \]

## 5. Definições e Conceitos
- **Regra do Tombo:** Mnemônica para \( \frac{d}{dx}[x^n] \): o expoente "tomba" para a frente multiplicando e reduz-se uma unidade do expoente.
- **Operador Linear:** Operador que preserva as operações de adição vetorial e multiplicação por escalar.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Diferenciação de Polinômio por Linearidade
Calcule a derivada de \( P(x) = 4x^5 - 7x^3 + 2x - 9 \).
- **Passo 1 (Aplicar termo a termo):**
  - Para \( 4x^5 \): o 5 tomba multiplicando o 4: \( 4 \cdot (5 x^4) = 20x^4 \).
  - Para \( -7x^3 \): o 3 tomba: \( -7 \cdot (3 x^2) = -21x^2 \).
  - Para \( 2x \) (onde o expoente é 1): \( 2 \cdot (1 x^0) = 2(1) = 2 \).
  - Para a constante \( -9 \): a derivada é 0.
- **Passo 2 (Combinar os resultados):**
  \[ P'(x) = 20x^4 - 21x^2 + 2 \]

### Exemplo 2: Função com Radicais e Denominadores
Calcule a derivada de \( g(x) = \frac{3}{\sqrt{x}} + 2 e^x - 5 \sin x \).
- **Passo 1 (Reescrever o radical como potência):**
  \( \frac{3}{\sqrt{x}} = 3 x^{-1/2} \).
- **Passo 2 (Diferenciar cada parcela):**
  - \( \frac{d}{dx}[3 x^{-1/2}] = 3 \cdot \left(-\frac{1}{2}\right) x^{-1/2 - 1} = -\frac{3}{2} x^{-3/2} = -\frac{3}{2\sqrt{x^3}} \).
  - \( \frac{d}{dx}[2 e^x] = 2 e^x \).
  - \( \frac{d}{dx}[-5 \sin x] = -5 \cos x \).
- **Passo 3 (Expressão final):**
  \[ g'(x) = -\frac{3}{2x\sqrt{x}} + 2e^x - 5\cos x \]

## 7. Aplicações Práticas e Modelagem
- **Crescimento Populacional Malthusiano:** No modelo \( N(t) = N_0 e^{rt} \), a taxa instantânea de crescimento biológico \( \frac{dN}{dt} = r N_0 e^{rt} = r N(t) \) é proporcional à população atual.
- **Oscilador Harmônico Simples:** Em um pêndulo com posição angular \( \theta(t) = A \cos(\omega t) \), a velocidade angular é obtida diretamente pela diferenciação da função cosseno.

## 8. Exercícios de Fixação
1. Calcule a derivada de \( f(x) = \frac{1}{3} x^6 - \frac{5}{2} x^4 + 8x \).
2. Determine a derivada de \( y = 4\sqrt[3]{x^2} \).
3. Encontre a inclinação da reta tangente a \( y = 3\sin x + 2\cos x \) em \( x = 0 \).

## 9. Correção Comentada dos Exercícios
1. \( f'(x) = \frac{1}{3}(6x^5) - \frac{5}{2}(4x^3) + 8 = 2x^5 - 10x^3 + 8 \).
2. Reescrevendo: \( y = 4 x^{2/3} \). Derivada: \( y' = 4 \cdot \left(\frac{2}{3}\right) x^{2/3 - 1} = \frac{8}{3} x^{-1/3} = \frac{8}{3\sqrt[3]{x}} \).
3. \( y' = 3\cos x - 2\sin x \). Em \( x = 0 \): \( y'(0) = 3\cos(0) - 2\sin(0) = 3(1) - 2(0) = 3 \). A inclinação da reta tangente é 3.

## 10. Desafio de Domínio
Deduza a derivada da função \( f(x) = a^x \) (para uma base arbitrária \( a > 0, a \neq 1 \)) utilizando a identidade \( a^x = e^{x \ln a} \).

## 11. Resumo e Pontos-Chave
- Regra da potência: \( (x^n)' = n x^{n-1} \) vale para todo expoente real.
- Constantes aditivas somem na derivada (\( c' = 0 \)); constantes multiplicativas permanecem preservadas (\( [c f]' = c f' \)).
- Derivada de \( e^x \) é o próprio \( e^x \); derivada de \( \ln x \) é \( 1/x \).
- \( (\sin x)' = \cos x \) e \( (\cos x)' = -\sin x \).

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 3.1: "Derivadas de Polinômios e Funções Exponenciais" e Seção 3.3: "Derivadas de Funções Trigonométricas".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 7: "Regras Básicas de Derivação".
- STRANG, Gilbert; HERMAN, Edwin. *Calculus Volume 1*. OpenStax, 2016. Seção 3.3: "Differentiation Rules".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Regra da Potência (Regra do Tombo)",
                syntax = "\\frac{d}{dx}[x^n] = n x^{n-1} \\quad (n \\in \\mathbb{R})",
                description = "Derivada direta de qualquer potência de x.",
                parametersInfo = "Converta raízes e frações em potências fracionárias ou negativas antes de aplicar."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Derivadas Trigonométricas Básicas",
                syntax = "\\frac{d}{dx}[\\sin x] = \\cos x \\qquad \\frac{d}{dx}[\\cos x] = -\\sin x",
                description = "Derivadas fundamentais das funções circulares.",
                parametersInfo = "Lembre-se sempre do sinal negativo na derivada do cosseno."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Derivadas Exponencial e Logarítmica",
                syntax = "\\frac{d}{dx}[e^x] = e^x \\qquad \\frac{d}{dx}[\\ln x] = \\frac{1}{x}",
                description = "Comportamento diferencial de base e.",
                parametersInfo = "Para ln x, supõe-se x > 0."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Derivada Rápida de Expressão Algébrica",
                codeOrFormula = "\\frac{d}{dx}[4x^5 - 7x^3 + 2x - 9] = 20x^4 - 21x^2 + 2",
                explanation = "Aplicou-se a regra da potência e a linearidade termo a termo sem necessidade de limites."
            )
        ),
        goldTip = "Antes de derivar radicais ou frações algébricas como 1/x³ ou raiz quadrada de x, reescreva-os SEMPRE como potências puras: x^(-3) e x^(1/2). Isso transforma expressões assustadoras em simples aplicações da Regra do Tombo!"
    )

    // =========================================================================
    // AULA 13: As 3 Regras de Ouro para Operações
    // =========================================================================
    private val bundleCalcL13 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Na aula anterior aprendemos a derivar funções atômicas isoladas. No entanto, no mundo real e nas aplicações de engenharia, as funções raramente aparecem puras: elas se multiplicam, se dividem e se encaixam uma dentro da outra como bonecas russas (funções compostas). A derivada do produto NÃO é o produto das derivadas, e a derivada da divisão NÃO é a divisão das derivadas. Para operar com essas estruturas complexas, a matemática dispõe de três ferramentas incontornáveis: a **Regra do Produto**, a **Regra do Quociente** e a soberana de todo o Cálculo: a **Regra da Cadeia**.

## 2. Objetivos de Aprendizagem
- Enunciar e aplicar rigorosamente a Regra do Produto de Leibniz: \( (u \cdot v)' = u'v + uv' \).
- Enunciar e aplicar a Regra do Quociente: \( \left(\frac{u}{v}\right)' = \frac{u'v - uv'}{v^2} \).
- Compreender e executar a Regra da Cadeia para funções compostas: \( [f(g(x))]' = f'(g(x)) \cdot g'(x) \).
- Derivar as outras quatro funções trigonométricas (\( \tan x, \sec x, \cot x, \csc x \)) via regra do quociente.

## 3. Pré-requisitos
- Regras básicas de derivação direta: potência, expoentes, seno, cosseno e exponencial (Aula 12).
- Conceito de função composta \( (f \circ g)(x) = f(g(x)) \).
- Álgebra elementar de frações e simplificação de polinômios.

## 4. Explicação Teórica Aprofundada
### A Regra do Produto (Regra de Leibniz)
Se \( u(x) \) e \( v(x) \) forem diferenciáveis, a taxa de variação de seu produto é:
\[ \frac{d}{dx}[u(x) \cdot v(x)] = u'(x) \cdot v(x) + u(x) \cdot v'(x) \]
*Justificativa Geométrica:* Imagine um retângulo de lados \( u \) e \( v \). Sua área é \( A = u \cdot v \). Se os lados aumentam em acréscimos infinitesimais \( du \) e \( dv \), a nova área será \( (u + du)(v + dv) = uv + u \cdot dv + v \cdot du + du \cdot dv \). Desprezando a quantidade de segunda ordem \( du \cdot dv \), o acréscimo de área é \( dA = u \cdot dv + v \cdot du \). Dividindo por \( dx \), obtemos a fórmula exata.

### A Regra do Quociente
Se \( u(x) \) e \( v(x) \) forem diferenciáveis e \( v(x) \neq 0 \):
\[ \frac{d}{dx}\left[\frac{u(x)}{v(x)}\right] = \frac{u'(x) \cdot v(x) - u(x) \cdot v'(x)}{[v(x)]^2} \]
*(Mnemônica Clássica: "Derivada de cima vezes o de baixo, MENOS o de cima vezes a derivada do de baixo, sobre o quadrado do de baixo".)*
**Aplicação Canônica: A Derivada da Tangente**
Como \( \tan x = \frac{\sin x}{\cos x} \):
\[ \frac{d}{dx}[\tan x] = \frac{(\sin x)' \cos x - \sin x (\cos x)'}{\cos^2 x} = \frac{\cos x \cdot \cos x - \sin x (-\sin x)}{\cos^2 x} = \frac{\cos^2 x + \sin^2 x}{\cos^2 x} \]
Pela identidade trigonométrica fundamental \( \cos^2 x + \sin^2 x = 1 \):
\[ \frac{d}{dx}[\tan x] = \frac{1}{\cos^2 x} = \sec^2 x \]

### A Regra da Cadeia (Diferenciação de Compostas)
Se \( g \) for diferenciável em \( x \) e \( f \) for diferenciável em \( g(x) \), a função composta \( F(x) = f(g(x)) \) é diferenciável em \( x \), e sua derivada é:
\[ F'(x) = f'(g(x)) \cdot g'(x) \]
Em notação de Leibniz, se \( y = f(u) \) e \( u = g(x) \):
\[ \frac{dy}{dx} = \frac{dy}{du} \cdot \frac{du}{dx} \]
A regra nos ensina a derivar "de fora para dentro": deriva-se a função externa \( f \) mantendo a interna \( g(x) \) intacta, e multiplica-se o resultado final pela derivada da função interna \( g'(x) \).

## 5. Definições e Conceitos
- **Função Interna (Miolo):** O argumento \( u = g(x) \) sobre o qual a função externa opera.
- **Função Externa (Casca):** A regra que envolve o miolo, diferenciada primeiro avaliada no argumento original.
- **Linearidade Estendida:** Derivação em cadeia recursiva para três ou mais funções acopladas: \( [f(g(h(x)))]' = f'(g(h(x))) \cdot g'(h(x)) \cdot h'(x) \).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Regra do Produto com Exponencial
Calcule a derivada de \( f(x) = x^3 e^x \).
- **Passo 1 (Identificar os fatores):**
  \( u(x) = x^3 \implies u'(x) = 3x^2 \).
  \( v(x) = e^x \implies v'(x) = e^x \).
- **Passo 2 (Montagem pela regra):**
  \[ f'(x) = u'v + uv' = (3x^2)(e^x) + (x^3)(e^x) \]
- **Passo 3 (Fatoração para forma elegante):**
  Colocando \( x^2 e^x \) em evidência:
  \[ f'(x) = x^2 e^x (3 + x) \]

### Exemplo 2: Aplicação da Regra da Cadeia
Calcule a derivada de \( y = \sin(4x^2 + 7) \).
- **Passo 1 (Identificar interna e externa):**
  Função interna (miolo): \( u = 4x^2 + 7 \implies \frac{du}{dx} = 8x \).
  Função externa (casca): \( y = \sin(u) \implies \frac{dy}{du} = \cos(u) \).
- **Passo 2 (Aplicar a cadeia):**
  \[ \frac{dy}{dx} = \frac{dy}{du} \cdot \frac{du}{dx} = \cos(u) \cdot 8x \]
- **Passo 3 (Restaurar o miolo original):**
  \[ \frac{dy}{dx} = 8x \cos(4x^2 + 7) \]

### Exemplo 3: Regra do Quociente com Cadeia Embutida
Calcule a derivada de \( g(x) = \frac{e^{2x}}{x^2 + 1} \).
- **Passo 1:** Numerador \( u = e^{2x} \implies u' = 2e^{2x} \) (pela cadeia). Denominador \( v = x^2 + 1 \implies v' = 2x \).
- **Passo 2:** Aplicando a regra do quociente:
  \[ g'(x) = \frac{(2e^{2x})(x^2 + 1) - (e^{2x})(2x)}{(x^2 + 1)^2} = \frac{2e^{2x}(x^2 - x + 1)}{(x^2 + 1)^2} \]

## 7. Aplicações Práticas e Modelagem
- **Termodinâmica e Gases Ideais:** Ao variar a temperatura e o volume simultaneamente em \( P(T, V) = \frac{nRT}{V} \), a taxa de variação da pressão no tempo combina as regras do produto e do quociente.
- **Teoria Eletromagnética:** A propagação de pulsos gaussianos em fibras ópticas \( E(z, t) = E_0 e^{-(t - z/v)^2 / \tau^2} \) é inteiramente governada pela aplicação repetida da regra da cadeia.

## 8. Exercícios de Fixação
1. Calcule a derivada de \( f(x) = (x^2 + 3x) \cos x \).
2. Determine a derivada de \( y = \frac{2x - 5}{3x + 4} \).
3. Calcule a derivada de \( h(x) = (5x^3 - 2x)^4 \).

## 9. Correção Comentada dos Exercícios
1. \( u = x^2 + 3x \implies u' = 2x + 3 \). \( v = \cos x \implies v' = -\sin x \).
   \( f'(x) = (2x + 3)\cos x + (x^2 + 3x)(-\sin x) = (2x + 3)\cos x - (x^2 + 3x)\sin x \).
2. \( u = 2x - 5 \implies u' = 2 \). \( v = 3x + 4 \implies v' = 3 \).
   \( y' = \frac{2(3x + 4) - (2x - 5)(3)}{(3x + 4)^2} = \frac{6x + 8 - 6x + 15}{(3x + 4)^2} = \frac{23}{(3x + 4)^2} \).
3. Regra da cadeia com potência externa: o 4 tomba multiplicando a base mantida com expoente 3, multiplicando pela derivada do miolo:
   \( h'(x) = 4(5x^3 - 2x)^3 \cdot (15x^2 - 2) \).

## 10. Desafio de Domínio
Deduza as regras de derivação para \( \sec x \) e \( \csc x \) expressando-as como quocientes das funções cosseno e seno fundamentais.

## 11. Resumo e Pontos-Chave
- Regra do Produto: \( (uv)' = u'v + uv' \) (nunca cometa o erro de fazer apenas \( u'v' \)).
- Regra do Quociente: \( (u/v)' = \frac{u'v - uv'}{v^2} \). O sinal negativo no numerador é obrigatório.
- Regra da Cadeia: Derivada da de fora vezes derivada da de dentro. É a regra mais utilizada em todo o Cálculo avançado.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 3.2: "As Regras do Produto e do Quociente" e Seção 3.4: "A Regra da Cadeia".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 7: Seções 7.3 e 7.4.
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seções 3.3 e 3.6.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Regra do Produto de Leibniz",
                syntax = "(u \\cdot v)' = u' \\cdot v + u \\cdot v'",
                description = "Derivada do produto entre duas funções.",
                parametersInfo = "Ambas as funções devem ser diferenciáveis."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Regra do Quociente",
                syntax = "\\left(\\frac{u}{v}\\right)' = \\frac{u' \\cdot v - u \\cdot v'}{v^2}",
                description = "Derivada da divisão entre duas funções.",
                parametersInfo = "Exige que o denominador v(x) seja diferente de zero."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Regra da Cadeia (Composição)",
                syntax = "\\frac{d}{dx}[f(g(x))] = f'(g(x)) \\cdot g'(x)",
                description = "Derivação de fora para dentro.",
                parametersInfo = "Multiplica-se a derivada externa pela derivada da função interna."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Diferenciação por Regra da Cadeia",
                codeOrFormula = "\\frac{d}{dx}[\\sin(4x^2 + 7)] = \\cos(4x^2 + 7) \\cdot \\frac{d}{dx}[4x^2 + 7] = 8x \\cos(4x^2 + 7)",
                explanation = "A derivada externa é cos(u) e a derivada do miolo u=4x²+7 é 8x."
            )
        ),
        goldTip = "O erro nº 1 de quem estuda Cálculo é esquecer de multiplicar pela derivada do miolo na Regra da Cadeia! Sempre que houver algo diferente de um simples 'x' dentro de uma função, pergunte a si mesmo: 'eu já derivei o que está lá dentro?'"
    )

    // =========================================================================
    // AULA 14: Técnicas Especiais: Derivação Implícita e Logarítmica
    // =========================================================================
    private val bundleCalcL14 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Até este momento, todas as nossas equações apareciam na forma **explícita**: a variável dependente \( y \) estava perfeitamente isolada de um lado da igualdade em termos puramente de \( x \) (\( y = f(x) \)). No entanto, a maioria das curvas que moldam o universo geométrico — como elipses planetárias, órbitas orbitais e curvas termodinâmicas — são dadas na forma **implícita**: \( F(x, y) = c \), onde isolar \( y \) é algebraicamente impossível ou divide a curva em múltiplos ramos feios. Para resolver este desafio com elegância suprema, utilizamos a **Derivação Implícita** e a **Diferenciação Logarítmica**.

## 2. Objetivos de Aprendizagem
- Diferenciar relações implícitas \( F(x, y) = 0 \) aplicando a Regra da Cadeia e tratando \( y \) como função desconhecida de \( x \).
- Isolar algebricamente a derivada \( \frac{dy}{dx} \) (ou \( y' \)) em termos de ambas as coordenadas \( x \) e \( y \).
- Determinar equações de retas tangentes a curvas implícitas clássicas (círculos, elipses, hipérboles e o Folium de Descartes).
- Dominar a técnica da Diferenciação Logarítmica para derivar funções com potências variáveis no expoente e na base: \( [f(x)]^{g(x)} \).

## 3. Pré-requisitos
- Regras do produto, quociente e cadeia (Aula 13).
- Propriedades operatórias de logaritmos: \( \ln(a \cdot b) = \ln a + \ln b \), \( \ln(a/b) = \ln a - \ln b \) e \( \ln(a^b) = b \ln a \).
- Derivada do logaritmo natural \( (\ln x)' = 1/x \).

## 4. Explicação Teórica Aprofundada
### O Mecanismo da Derivação Implícita
Na derivação implícita, derivamos ambos os lados da equação em relação a \( x \), lembrando constantemente que **\( y \) não é uma variável independente, mas sim uma função implícita \( y = y(x) \)**.
Consequentemente, sempre que derivamos um termo que contenha a variável \( y \), devemos aplicar a **Regra da Cadeia**, multiplicando pelo fator \( \frac{dy}{dx} \) (ou \( y' \)):
- Para derivar \( y^2 \): \( \frac{d}{dx}[y^2] = 2y \cdot \frac{dy}{dx} \).
- Para derivar \( \sin(y) \): \( \frac{d}{dx}[\sin y] = \cos(y) \cdot \frac{dy}{dx} \).
- Para derivar um produto cruzado \( x \cdot y \): pela regra do produto, \( \frac{d}{dx}[x \cdot y] = (1) \cdot y + x \cdot y' = y + x y' \).
Após diferenciar todos os termos, agrupamos todos os termos contendo \( y' \) em um dos membros da equação, colocamos \( y' \) em evidência e isolamos a derivada final.

### A Técnica da Diferenciação Logarítmica
Como calcular a derivada de uma função onde **tanto a base quanto o expoente são variáveis**, como \( y = x^x \)?
- A regra da potência \( (x^n)' = n x^{n-1} \) não serve (pois o expoente varia).
- A regra da exponencial \( (a^x)' = a^x \ln a \) não serve (pois a base varia).
O procedimento padrão em 4 passos:
1. Aplica-se o logaritmo natural (\( \ln \)) em ambos os lados da equação: \( \ln y = \ln[x^x] \).
2. Usa-se a propriedade do expoente para transformar a potência em multiplicação: \( \ln y = x \ln x \).
3. Deriva-se implicitamente ambos os membros em relação a \( x \):
   \[ \frac{1}{y} \cdot y' = (1) \cdot \ln x + x \cdot \left(\frac{1}{x}\right) = \ln x + 1 \]
4. Multiplica-se toda a equação por \( y \), restaurando a expressão original:
   \[ y' = y (\ln x + 1) = x^x (\ln x + 1) \]

## 5. Definições e Conceitos
- **Forma Implícita:** Equação da forma \( F(x, y) = 0 \) onde \( y \) não está isolado.
- **Diferenciação Logarítmica:** Estratégia de linearização multiplicativa por meio de logaritmos antes da aplicação da derivada.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Reta Tangente ao Círculo Unitário
Determine a equação da reta tangente ao círculo \( x^2 + y^2 = 25 \) no ponto \( P(3, 4) \).
- **Passo 1 (Derivar implicitamente em relação a \( x \)):**
  \[ \frac{d}{dx}[x^2] + \frac{d}{dx}[y^2] = \frac{d}{dx}[25] \]
  \[ 2x + 2y \cdot y' = 0 \]
- **Passo 2 (Isolar \( y' \)):**
  \[ 2y \cdot y' = -2x \implies y' = -\frac{2x}{2y} = -\frac{x}{y} \]
- **Passo 3 (Calcular a inclinação no ponto \( P(3, 4) \)):**
  \[ m = y'(3, 4) = -\frac{3}{4} \]
- **Passo 4 (Equação da reta tangente):**
  \[ y - 4 = -\frac{3}{4}(x - 3) \implies 4(y - 4) = -3(x - 3) \implies 4y - 16 = -3x + 9 \implies 3x + 4y = 25 \]
- *(Nota de consistência geométrica: o raio que vai da origem a (3,4) tem inclinação 4/3. Como a tangente é perpendicular ao raio, sua inclinação é exatamente o inverso oposto: -3/4!)*

### Exemplo 2: Diferenciação Implícita com Produto Cruzado
Encontre \( y' \) para a curva \( x^3 + y^3 = 6xy \).
- **Passo 1:** Derivando termo a termo:
  \( 3x^2 + 3y^2 y' = 6(y + x y') = 6y + 6x y' \).
- **Passo 2:** Agrupando os termos em \( y' \) à esquerda:
  \( 3y^2 y' - 6x y' = 6y - 3x^2 \).
- **Passo 3:** Colocando \( y' \) em evidência:
  \( y'(3y^2 - 6x) = 6y - 3x^2 \).
- **Passo 4:** Isolando \( y' \):
  \[ y' = \frac{6y - 3x^2}{3y^2 - 6x} = \frac{2y - x^2}{y^2 - 2x} \]

## 7. Aplicações Práticas e Modelagem
- **Economia Microclássica:** As curvas de indiferença e curvas de nível de utilidade \( U(x_1, x_2) = k \) relacionam duas mercadorias de forma implícita. A taxa marginal de substituição é a derivada implícita \( -\frac{dx_2}{dx_1} \).
- **Dinâmica Orbital de Satélites:** A equação de trajetória das órbitas elípticas de Kepler é implícita; as forças e correções de aceleração orbital usam a derivação implícita das equações de cônica.

## 8. Exercícios de Fixação
1. Calcule \( y' \) pela derivação implícita para a elipse \( 4x^2 + 9y^2 = 36 \).
2. Encontre a inclinação da reta tangente a \( y^2 = x^3(2 - x) \) no ponto \( (1, 1) \).
3. Utilize a diferenciação logarítmica para calcular a derivada de \( y = x^{\sin x} \).

## 9. Correção Comentada dos Exercícios
1. \( 8x + 18y \cdot y' = 0 \implies 18y \cdot y' = -8x \implies y' = -\frac{8x}{18y} = -\frac{4x}{9y} \).
2. Derivando \( y^2 = 2x^3 - x^4 \): \( 2y y' = 6x^2 - 4x^3 \). No ponto \( (1, 1) \): \( 2(1) y' = 6(1)^2 - 4(1)^3 = 2 \implies y' = 1 \). A inclinação é 1.
3. \( \ln y = \sin x \cdot \ln x \). Derivando implicitamente:
   \( \frac{y'}{y} = \cos x \cdot \ln x + \sin x \cdot \frac{1}{x} \).
   Multiplicando por \( y \): \( y' = x^{\sin x} \left(\cos x \ln x + \frac{\sin x}{x}\right) \).

## 10. Desafio de Domínio
Dada a relação implícita \( x^2 - xy + y^2 = 3 \), determine os pontos da curva onde a reta tangente é perfeitamente horizontal (\( y' = 0 \)) e onde ela é vertical (\( y' \to \infty \)).

## 11. Resumo e Pontos-Chave
- Na derivação implícita, trata-se \( y \) como função de \( x \): cada derivada de \( y \) gera o fator multiplicativo \( y' \) pela regra da cadeia.
- A derivada implícita resulta geralmente em uma fórmula dependente de ambas as coordenadas \( (x, y) \).
- Diferenciação logarítmica transforma potências intratáveis do tipo \( u(x)^{v(x)} \) em produtos simples diferenciáveis.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 3.5: "Derivação Implícita" e Seção 3.6: "Derivadas de Funções Logarítmicas".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 8: "Derivação Implícita".
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seção 3.8: "Implicit Differentiation".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Princípio da Derivação Implícita",
                syntax = "\\frac{d}{dx}[g(y)] = g'(y) \\cdot \\frac{dy}{dx}",
                description = "Aplicação da regra da cadeia a termos dependentes de y.",
                parametersInfo = "Sempre que derivar um termo em y, multiplique por dy/dx."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Diferenciação Logarítmica para Potências Gerais",
                syntax = "y = [f(x)]^{g(x)} \\implies \\ln y = g(x) \\ln f(x) \\implies y' = y \\left[g'(x) \\ln f(x) + \\frac{g(x)f'(x)}{f(x)}\\right]",
                description = "Fórmula geral para derivação de potências onde base e expoente variam.",
                parametersInfo = "Exige que f(x) > 0 no domínio considerado."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Derivação Implícita no Círculo",
                codeOrFormula = "x^2 + y^2 = 25 \\implies 2x + 2y y' = 0 \\implies y' = -\\frac{x}{y}",
                explanation = "A diferenciação membro a membro preservou a relação sem precisar dividir o círculo em metades superior e inferior."
            )
        ),
        goldTip = "Toda vez que você vir x e y misturados sem estarem isolados, não tente isolar y! Diferencie imediatamente os dois lados respeitando a regra da cadeia em y e isole y' no final!"
    )
}

