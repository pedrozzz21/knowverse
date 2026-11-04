package com.example.data.math.didactic.calculus

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Cálculo I — Módulo 1: Limites e Continuidade (Parte 1: Aulas 1 a 5).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em James Stewart, Guidorizzi e OpenStax Calculus Vol 1.
 */
object CalculusLimitsPart1 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "calc_l1" -> bundleCalcL1
            "calc_l2" -> bundleCalcL2
            "calc_l3" -> bundleCalcL3
            "calc_l4" -> bundleCalcL4
            "calc_l5" -> bundleCalcL5
            else -> null
        }
    }

    // =========================================================================
    // AULA 1: O que é o Limite? (A Ideia Básica e a Vizinhança)
    // =========================================================================
    private val bundleCalcL1 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O conceito de limite é a pedra angular sobre a qual todo o edifício do Cálculo Diferencial e Integral é erguido. Enquanto a álgebra e a geometria clássica operam com estados estáticos e valores pontuais, o Cálculo estuda o comportamento dinâmico: a transição contínua, a aproximação infinitesimal e a tendência de grandezas físicas quando parâmetros se aproximam de valores críticos. Nesta aula inaugural, você compreenderá a intuição geométrica e analítica de aproximar-se indefinidamente de um ponto sem jamais precisar coincidir com ele.

## 2. Objetivos de Aprendizagem
- Compreender intuitiva e geometricamente o significado da notação \( \lim_{x \to a} f(x) = L \).
- Distinguir com clareza o comportamento da função na vizinhança perfurada de \( a \) do valor pontual \( f(a) \).
- Analisar tabelas de aproximação numérica e gráficos cartesianos para inferir tendências de aproximação.
- Reconhecer funções bem comportadas onde a aproximação converge para o valor numérico da imagem e casos onde \( f(a) \) é indefinido.

## 3. Pré-requisitos
- Conceito de função real de variável real: domínio, contradomínio e conjunto imagem.
- Noções de reta real, distância euclidiana entre pontos e intervalos abertos \( (a - \delta, a + \delta) \).
- Operações com expressões algébricas e fatoração elementar.

## 4. Explicação Teórica Aprofundada
### O Dilema Histórico da Divisão por Zero
Historicamente, matemáticos como Newton e Leibniz lidaram com o paradoxo das "quantidades evanescentes": grandezas que se tornam infinitamente pequenas, mas não são estritamente nulas. Se você deseja calcular a velocidade instantânea de um objeto em movimento, a razão fundamental é \( \frac{\Delta s}{\Delta t} \). No instante exato \( \Delta t = 0 \), a distância percorrida também é \( \Delta s = 0 \), gerando a fração indefinida \( \frac{0}{0} \). A genialidade do limite está em não perguntar o que ocorre *em* \( \Delta t = 0 \), mas sim o que ocorre quando \( \Delta t \) *tende* a zero (\( \Delta t \to 0 \)).

### A Vizinhança Perfurada
Dizer que \( x \to a \) ("x tende a a") significa que escolhemos valores de \( x \) pertencentes a um intervalo aberto ao redor de \( a \), excluindo o próprio \( a \). Em notação de teoria de conjuntos, consideramos a vizinhança perfurada:
\[ 0 < |x - a| < \delta \]
A condição \( |x - a| < \delta \) estabelece que a distância entre \( x \) e \( a \) é menor que um número real positivo \( \delta \). A desigualdade estrita \( 0 < |x - a| \) garante que \( x \neq a \).

### O Valor Limite \( L \)
Afirmar que:
\[ \lim_{x \to a} f(x) = L \]
significa que podemos tornar os valores de \( f(x) \) tão próximos do número real \( L \) quanto desejarmos, bastando para isso escolher valores de \( x \) suficientemente próximos de \( a \), mas diferentes de \( a \).
Observe que:
1. \( f(a) \) pode existir e ser igual a \( L \).
2. \( f(a) \) pode existir e ser diferente de \( L \) (gráfico com ponto isolado deslocado).
3. \( f(a) \) pode nem sequer existir no domínio da função (gráfico com uma "descontinuidade pontual ou furo").
Em qualquer um dos três cenários, o limite \( \lim_{x \to a} f(x) \) pode existir perfeitamente e ser igual a \( L \).

## 5. Definições e Conceitos
- **Limite Bilateral:** O número \( L \) para o qual \( f(x) \) converge quando \( x \) se aproxima de \( a \) tanto por valores menores (\( x < a \)) quanto por valores maiores (\( x > a \)).
- **Vizinhança Perfurada de Centro \( a \):** Conjunto \( (a - \delta, a + \delta) \setminus \{a\} \).
- **Indeterminação Inicial:** Situação em que a substituição cega resulta em formas do tipo \( \frac{0}{0} \), exigindo investigação analítica da vizinhança.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Função com Furo no Gráfico
Considere a função racional \( f(x) = \frac{x^2 - 1}{x - 1} \). Determine \( \lim_{x \to 1} f(x) \).
- **Passo 1 (Tentativa de substituição pontual):** Para \( x = 1 \), o denominador anula-se: \( f(1) = \frac{1^2 - 1}{1 - 1} = \frac{0}{0} \) (indefinido em \( \mathbb{R} \)). O domínio é \( D_f = \mathbb{R} \setminus \{1\} \).
- **Passo 2 (Investigação na vizinhança perfurada):** Como estamos avaliando o limite com \( x \to 1 \), temos \( x \neq 1 \). Portanto, o fator \( (x - 1) \) é diferente de zero e podemos simplificar algebricamente a expressão usando a diferença de quadrados:
\[ x^2 - 1 = (x - 1)(x + 1) \]
\[ f(x) = \frac{(x - 1)(x + 1)}{x - 1} = x + 1, \quad \forall x \neq 1 \]
- **Passo 3 (Cálculo do limite):** Conforme \( x \) se aproxima de 1, a expressão equivalente \( x + 1 \) aproxima-se de \( 1 + 1 = 2 \):
\[ \lim_{x \to 1} \frac{x^2 - 1}{x - 1} = \lim_{x \to 1} (x + 1) = 2 \]
- **Conclusão:** O limite existe e vale 2, muito embora a função não esteja definida no ponto \( x = 1 \).

### Exemplo 2: Função com Ponto Deslocado
Seja \( g(x) = \begin{cases} 2x + 1, & x \neq 3 \\ 10, & x = 3 \end{cases} \). Calcule \( \lim_{x \to 3} g(x) \).
- **Resolução:** O cálculo do limite quando \( x \to 3 \) ignora completamente o valor isolado \( g(3) = 10 \). Na vizinhança perfurada onde \( x \neq 3 \), a lei que rege a função é \( g(x) = 2x + 1 \). Portanto:
\[ \lim_{x \to 3} g(x) = \lim_{x \to 3} (2x + 1) = 2(3) + 1 = 7 \]
- O limite vale 7, revelando que a função tende a 7, mesmo que seu valor registrado em \( x = 3 \) seja 10.

## 7. Aplicações Práticas e Modelagem
- **Velocidade Instantânea em Física:** O velocímetro de um automóvel indica a velocidade instantânea \( v(t) = \lim_{\Delta t \to 0} \frac{s(t + \Delta t) - s(t)}{\Delta t} \).
- **Resistência em Circuitos Elétricos:** Na análise de redes com componentes não-lineares, a corrente limite através de um diodo quando a tensão de limiar é atingida é modelada por aproximação assintótica.

## 8. Exercícios de Fixação
1. Calcule \( \lim_{x \to 2} (3x^2 - 5x + 4) \).
2. Determine \( \lim_{x \to 4} \frac{x^2 - 16}{x - 4} \).
3. Se \( f(x) = 5 \) para todo \( x \neq 0 \) e \( f(0) = 0 \), qual é o valor de \( \lim_{x \to 0} f(x) \)?

## 9. Correção Comentada dos Exercícios
1. A função é polinomial e contínua em todo \( \mathbb{R} \). Pela substituição direta: \( 3(2)^2 - 5(2) + 4 = 3(4) - 10 + 4 = 12 - 10 + 4 = 6 \).
2. Substituição direta fornece \( \frac{0}{0} \). Fatorando o numerador pela diferença de quadrados: \( \frac{(x - 4)(x + 4)}{x - 4} \). Para \( x \neq 4 \), cancelamos o fator comum, restando \( \lim_{x \to 4} (x + 4) = 4 + 4 = 8 \).
3. Na vizinhança perfurada de zero, a função assume identicamente o valor 5. Logo, \( \lim_{x \to 0} f(x) = 5 \). O fato de \( f(0) = 0 \) não interfere no limite.

## 10. Desafio de Domínio
Considere a função \( h(x) = \frac{|x|}{x} \). Avalie o que ocorre com os valores de \( h(x) \) quando \( x \) se aproxima de 0 por valores positivos e por valores negativos. É possível afirmar que existe um único número real \( L \) para o qual \( h(x) \) tende quando \( x \to 0 \)?
*(Dica: Esta investigação prepara a mente para o conceito de limites laterais da Aula 2).*

## 11. Resumo e Pontos-Chave
- Limite estuda a tendência local na vizinhança perfurada \( 0 < |x - a| < \delta \).
- O valor \( f(a) \) pode existir ou não; ele não altera o valor de \( \lim_{x \to a} f(x) \).
- Expressões que geram \( \frac{0}{0} \) sob substituição ingênua frequentemente revelam limites finitos após simplificação algébrica.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. São Paulo: Cengage Learning, 2016. Capítulo 2: "Limites e Derivadas".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. Rio de Janeiro: LTC, 2001. Capítulo 3: "Limite e Continuidade".
- STRANG, Gilbert; HERMAN, Edwin. *Calculus Volume 1*. OpenStax, Rice University, 2016. Seção 2.1: "A Preview of Calculus" e 2.2: "The Limit of a Function".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Definição Intuitiva de Limite",
                syntax = """\\lim_{x \\to a} f(x) = L""",
                description = "Convergência dos valores de f(x) para L na vizinhança perfurada de a.",
                parametersInfo = "Exige que x se aproxime de a por ambos os lados (pela esquerda e pela direita)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Vizinhança Perfurada",
                syntax = """0 < |x - a| < \\delta""",
                description = "Intervalo aberto centrado em 'a', excluindo o ponto central x = a.",
                parametersInfo = """\\delta > 0 representa o raio da vizinhança no eixo das abscissas."""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Simplificação de Fator Nulo em Racionais",
                codeOrFormula = """\\lim_{x \\to 1} \\frac{x^2 - 1}{x - 1} = \\lim_{x \\to 1} (x + 1) = 2""",
                explanation = "Fatora-se o numerador (x-1)(x+1) e cancela-se o termo nulo (x-1) que gera a indefinição 0/0."
            )
        ),
        goldTip = "Regra de Ouro do Limite: Nunca confunda 'calcular f(a)' com 'calcular o limite quando x tende a a'. O limite é sobre a viagem, não sobre o destino final!"
    )

    // =========================================================================
    // AULA 2: Limites Laterais e Existência
    // =========================================================================
    private val bundleCalcL2 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Na aula anterior, aprendemos que o limite bilateral \( \lim_{x \to a} f(x) = L \) exige convergência uniforme quando nos aproximamos de \( a \). No entanto, o que acontece quando uma função se comporta de maneiras radicalmente distintas dependendo da direção pela qual nos aproximamos? Seja em degraus de potencial elétrico, tarifas com faixas de consumo ou colisões mecânicas, o sentido do movimento importa. Os limites laterais são os instrumentos matemáticos que decompõem a vizinhança em duas direções: pela esquerda e pela direita.

## 2. Objetivos de Aprendizagem
- Definir e calcular formalmente o limite lateral à esquerda \( \lim_{x \to a^-} f(x) \) e à direita \( \lim_{x \to a^+} f(x) \).
- Compreender e aplicar o Teorema Fundamental da Existência do Limite Bilateral.
- Analisar funções definidas por várias sentenças (funções por partes) e funções modulares.
- Identificar descontinuidades de salto em gráficos cartesianos.

## 3. Pré-requisitos
- Compreensão intuitiva de limite e vizinhança perfurada (Aula 1).
- Função modular e funções definidas por partes.
- Relações de ordem na reta real (\( x < a \) e \( x > a \)).

## 4. Explicação Teórica Aprofundada
### Aproximação Pela Esquerda (\( x \to a^- \))
Quando escrevemos \( x \to a^- \), estamos restringindo \( x \) a valores estritamente menores que \( a \) (\( x < a \)). Analisamos o comportamento da função à medida que caminhamos da esquerda para a direita ao longo do eixo das abscissas:
\[ \lim_{x \to a^-} f(x) = L_1 \]

### Aproximação Pela Direita (\( x \to a^+ \))
Quando escrevemos \( x \to a^+ \), estamos restringindo \( x \) a valores estritamente maiores que \( a \) (\( x > a \)). Analisamos a função caminhando da direita para a esquerda:
\[ \lim_{x \to a^+} f(x) = L_2 \]

### Teorema da Existência do Limite Bilateral
Um dos teoremas mais fundamentais do Cálculo I estabelece a condição necessária e suficiente para a existência do limite ordinário:
\[ \lim_{x \to a} f(x) = L \iff \lim_{x \to a^-} f(x) = L \quad \text{e} \quad \lim_{x \to a^+} f(x) = L \]
Se os limites laterais forem ambos finitos, mas distintos (\( L_1 \neq L_2 \)), ou se pelo menos um deles não existir, concluímos categoricamente que:
\[ \lim_{x \to a} f(x) \quad \text{NÃO EXISTE (\(\nexists\))} \]

## 5. Definições e Conceitos
- **Limite Lateral à Esquerda:** Tendência de \( f(x) \) quando \( x \) se aproxima de \( a \) com \( x \in (a - \delta, a) \).
- **Limite Lateral à Direita:** Tendência de \( f(x) \) quando \( x \) se aproxima de \( a \) com \( x \in (a, a + \delta) \).
- **Salto Finito:** Diferença \( |L_2 - L_1| \) observada no gráfico quando os limites laterais são diferentes.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: A Função Sinal / Quociente Modular
Analise a existência de \( \lim_{x \to 0} \frac{|x|}{x} \).
- **Passo 1 (Definição da função modular):**
  Pela definição do módulo:
  \[ |x| = \begin{cases} x, & \text{se } x \ge 0 \\ -x, & \text{se } x < 0 \end{cases} \]
- **Passo 2 (Limite lateral à direita, \( x \to 0^+ \)):**
  Para \( x > 0 \), temos \( |x| = x \). Logo:
  \[ \lim_{x \to 0^+} \frac{|x|}{x} = \lim_{x \to 0^+} \frac{x}{x} = \lim_{x \to 0^+} (1) = 1 \]
- **Passo 3 (Limite lateral à esquerda, \( x \to 0^- \)):**
  Para \( x < 0 \), temos \( |x| = -x \). Logo:
  \[ \lim_{x \to 0^-} \frac{|x|}{x} = \lim_{x \to 0^-} \frac{-x}{x} = \lim_{x \to 0^-} (-1) = -1 \]
- **Passo 4 (Aplicação do Teorema):**
  Como \( \lim_{x \to 0^+} \frac{|x|}{x} = 1 \) e \( \lim_{x \to 0^-} \frac{|x|}{x} = -1 \), os limites laterais são diferentes (\( 1 \neq -1 \)).
- **Conclusão:** O limite bilateral \( \lim_{x \to 0} \frac{|x|}{x} \) **não existe**.

### Exemplo 2: Função por Partes com Parâmetro
Encontre o valor da constante \( k \) para que exista \( \lim_{x \to 2} f(x) \), sendo:
\[ f(x) = \begin{cases} kx^2 + 1, & \text{se } x \le 2 \\ 3x - 1, & \text{se } x > 2 \end{cases} \]
- **Passo 1 (Limite à esquerda):** Para \( x \to 2^- \), usamos a sentença \( kx^2 + 1 \):
  \[ \lim_{x \to 2^-} f(x) = k(2)^2 + 1 = 4k + 1 \]
- **Passo 2 (Limite à direita):** Para \( x \to 2^+ \), usamos a sentença \( 3x - 1 \):
  \[ \lim_{x \to 2^+} f(x) = 3(2) - 1 = 6 - 1 = 5 \]
- **Passo 3 (Igualar os limites laterais):**
  Para que o limite bilateral exista, devemos impor:
  \[ 4k + 1 = 5 \implies 4k = 4 \implies k = 1 \]
- **Conclusão:** Para \( k = 1 \), o limite bilateral existe e é igual a 5.

## 7. Aplicações Práticas e Modelagem
- **Chaveamento em Circuitos Eletrônicos:** A função degrau unitário de Heaviside \( H(t) \) modela a transição instantânea de tensão de 0V para 5V em circuitos lógicos quando um interruptor é acionado em \( t = 0 \).
- **Tarifação Postal e Imposto de Renda:** Sistemas tributários e postais utilizam faixas descontínuas onde o valor muda abruptamente ao ultrapassar um peso limite ou faixa salarial.

## 8. Exercícios de Fixação
1. Seja \( f(x) = \begin{cases} x + 3, & x < 1 \\ 2x + 2, & x \ge 1 \end{cases} \). Calcule \( \lim_{x \to 1^-} f(x) \), \( \lim_{x \to 1^+} f(x) \) e verifique se \( \lim_{x \to 1} f(x) \) existe.
2. Determine \( \lim_{x \to 3^+} \frac{x - 3}{|x - 3|} \) e \( \lim_{x \to 3^-} \frac{x - 3}{|x - 3|} \).
3. Se \( \lim_{x \to 5^-} g(x) = 4 \) e \( \lim_{x \to 5^+} g(x) = 4 \), podemos afirmar que \( g(5) = 4 \)? Justifique.

## 9. Correção Comentada dos Exercícios
1. \( \lim_{x \to 1^-} f(x) = 1 + 3 = 4 \). \( \lim_{x \to 1^+} f(x) = 2(1) + 2 = 4 \). Como os limites laterais são iguais a 4, o limite bilateral \( \lim_{x \to 1} f(x) \) existe e vale 4.
2. Para \( x \to 3^+ \), \( x - 3 > 0 \implies |x - 3| = x - 3 \), portanto o limite é \( \frac{x-3}{x-3} = 1 \). Para \( x \to 3^- \), \( x - 3 < 0 \implies |x - 3| = -(x - 3) \), gerando \( \frac{x-3}{-(x-3)} = -1 \).
3. Não necessariamente. O limite nos informa apenas a tendência na vizinhança perfurada. O valor pontual \( g(5) \) pode ser indefinido ou qualquer outro valor real. Para garantir que \( g(5) = 4 \), a função precisaria ser contínua em \( x = 5 \).

## 10. Desafio de Domínio
Considere a função maior inteiro (ou função piso) \( f(x) = \lfloor x \rfloor \), que retorna o maior número inteiro menor ou igual a \( x \). Calcule \( \lim_{x \to 2^-} \lfloor x \rfloor \) e \( \lim_{x \to 2^+} \lfloor x \rfloor \). Em quais pontos da reta real o limite \( \lim_{x \to a} \lfloor x \rfloor \) deixa de existir?

## 11. Resumo e Pontos-Chave
- \( \lim_{x \to a^-} f(x) \) analisa a aproximação pela esquerda (\( x < a \)).
- \( \lim_{x \to a^+} f(x) \) analisa a aproximação pela direita (\( x > a \)).
- O limite bilateral existe se, e somente se, ambos os limites laterais existirem e forem idênticos.
- Funções com módulos ou definidas por sentenças frequentemente exigem a verificação dos dois lados.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. São Paulo: Cengage Learning, 2016. Seção 2.2: "O Limite de uma Função".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. Rio de Janeiro: LTC, 2001. Seção 3.2: "Limites Laterais".
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seção 2.2: "One-Sided Limits".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema de Existência do Limite Bilateral",
                syntax = """\\lim_{x \\to a} f(x) = L \\iff \\lim_{x \\to a^-} f(x) = L \\text{ e } \\lim_{x \\to a^+} f(x) = L""",
                description = "Condição necessária e suficiente para a existência do limite comum.",
                parametersInfo = "Se os limites laterais divergirem entre si, o limite bilateral não existe."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Limite Lateral à Esquerda",
                syntax = """\\lim_{x \\to a^-} f(x) = L_1""",
                description = "Aproximação considerando apenas x < a.",
                parametersInfo = "Utilize a sentença correspondente ao intervalo à esquerda de a."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Limite Lateral à Direita",
                syntax = """\\lim_{x \\to a^+} f(x) = L_2""",
                description = "Aproximação considerando apenas x > a.",
                parametersInfo = "Utilize a sentença correspondente ao intervalo à direita de a."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Verificação do Limite de |x| / x na Origem",
                codeOrFormula = """\\lim_{x \\to 0^+} \\frac{x}{x} = 1 \\quad \\neq \\quad \\lim_{x \\to 0^-} \\frac{-x}{x} = -1""",
                explanation = "Como os limites pela direita (+1) e pela esquerda (-1) diferem, o limite global não existe."
            )
        ),
        goldTip = "Ao se deparar com funções definidas por ramos ou que contenham módulos, nunca substitua diretamente: calcule SEMPRE os limites laterais separadamente!"
    )

    // =========================================================================
    // AULA 3: Propriedades e Substituição Direta
    // =========================================================================
    private val bundleCalcL3 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Calcular limites por meio de tabelas de valores ou gráficos cartesianos fornece intuição visual, mas é um processo trabalhoso e sujeito a erros numéricos. Para desenvolver o rigor e a eficiência analítica indispensáveis no Cálculo I, necessitamos de uma álgebra de limites: um conjunto de teoremas operatórios que nos permitam decompor limites de funções complexas na soma, diferença, produto ou quociente de limites elementares imediatos.

## 2. Objetivos de Aprendizagem
- Enunciar e aplicar rigorosamente as Leis dos Limites (soma, produto, quociente, potência e raiz).
- Identificar as classes de funções que gozam da Propriedade da Substituição Direta.
- Calcular limites de polinômios e funções racionais com denominadores não-nulos de forma imediata.
- Compreender as condições de validade da lei do quociente (\( \lim g(x) \neq 0 \)).

## 3. Pré-requisitos
- Definição intuitiva de limite e limites laterais (Aulas 1 e 2).
- Operações elementares com polinômios e funções racionais.
- Fatoração e produtos notáveis fundamentais.

## 4. Explicação Teórica Aprofundada
### As Leis dos Limites
Suponha que \( c \) seja uma constante e que os limites \( \lim_{x \to a} f(x) = L \) e \( \lim_{x \to a} g(x) = M \) existam e sejam números reais finitos. Então:
1. **Lei da Soma:** \( \lim_{x \to a} [f(x) + g(x)] = \lim_{x \to a} f(x) + \lim_{x \to a} g(x) = L + M \)
2. **Lei da Diferença:** \( \lim_{x \to a} [f(x) - g(x)] = \lim_{x \to a} f(x) - \lim_{x \to a} g(x) = L - M \)
3. **Lei da Multiplicação por Constante:** \( \lim_{x \to a} [c \cdot f(x)] = c \cdot \lim_{x \to a} f(x) = c \cdot L \)
4. **Lei do Produto:** \( \lim_{x \to a} [f(x) \cdot g(x)] = \left(\lim_{x \to a} f(x)\right) \cdot \left(\lim_{x \to a} g(x)\right) = L \cdot M \)
5. **Lei do Quociente:** \( \lim_{x \to a} \frac{f(x)}{g(x)} = \frac{\lim_{x \to a} f(x)}{\lim_{x \to a} g(x)} = \frac{L}{M} \), **desde que \( M \neq 0 \)**.
6. **Lei da Potência:** \( \lim_{x \to a} [f(x)]^n = \left[\lim_{x \to a} f(x)\right]^n = L^n \) (onde \( n \in \mathbb{Z}^+ \)).
7. **Lei da Raiz:** \( \lim_{x \to a} \sqrt[n]{f(x)} = \sqrt[n]{\lim_{x \to a} f(x)} = \sqrt[n]{L} \) (se \( n \) for par, supomos que \( L > 0 \)).

### A Propriedade da Substituição Direta
Dizemos que uma função \( f \) possui a **Propriedade da Substituição Direta** no ponto \( a \) se:
\[ \lim_{x \to a} f(x) = f(a) \]
Funções que atendem a essa condição em todos os pontos de seus domínios são chamadas de **funções contínuas**. Destacam-se:
- **Polinômios:** \( P(x) = a_n x^n + \dots + a_1 x + a_0 \). Para qualquer número real \( a \), temos \( \lim_{x \to a} P(x) = P(a) \).
- **Funções Racionais:** \( R(x) = \frac{P(x)}{Q(x)} \). Para todo \( a \) tal que \( Q(a) \neq 0 \), temos \( \lim_{x \to a} R(x) = \frac{P(a)}{Q(a)} \).

## 5. Definições e Conceitos
- **Linearidade do Operador Limite:** O limite da combinação linear é a combinação linear dos limites: \( \lim [c_1 f + c_2 g] = c_1 \lim f + c_2 \lim g \).
- **Substituição Direta:** Método primário de avaliação de limites: se a função for contínua no ponto e não gerar divisões por zero, basta avaliar \( f(a) \).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Aplicação das Leis em Polinômio
Calcule \( \lim_{x \to 2} (4x^3 - 3x^2 + 7x - 5) \).
- **Resolução Detalhada:**
  Por ser um polinômio, aplicamos a propriedade da substituição direta:
  \[ \lim_{x \to 2} (4x^3 - 3x^2 + 7x - 5) = 4(2)^3 - 3(2)^2 + 7(2) - 5 \]
  \[ = 4(8) - 3(4) + 14 - 5 = 32 - 12 + 14 - 5 = 29 \]

### Exemplo 2: Função Racional com Denominador Não-Nulo
Calcule \( \lim_{x \to -1} \frac{x^3 + 2x^2 - 1}{5 - 3x} \).
- **Passo 1 (Avaliar o denominador):** \( \lim_{x \to -1} (5 - 3x) = 5 - 3(-1) = 5 + 3 = 8 \neq 0 \).
- **Passo 2 (Avaliar o numerador):** \( \lim_{x \to -1} (x^3 + 2x^2 - 1) = (-1)^3 + 2(-1)^2 - 1 = -1 + 2(1) - 1 = 0 \).
- **Passo 3 (Aplicar a Lei do Quociente):**
  \[ \lim_{x \to -1} \frac{x^3 + 2x^2 - 1}{5 - 3x} = \frac{0}{8} = 0 \]
- *Atenção:* Zero no numerador com denominador diferente de zero não é indeterminação: é simplesmente igual a 0!

## 7. Aplicações Práticas e Modelagem
- **Cálculo de Cargas Térmicas em Engenharia:** Em transferências de calor em regime estacionário, as equações de condução são algébricas e polinomiais; seus limites no equilíbrio térmico são calculados por avaliação direta.
- **Cinemática de Partículas:** O cálculo da posição final sob aceleração constante \( s(t) = s_0 + v_0 t + \frac{1}{2} a t^2 \) para \( t \to t_f \) é resolvido imediatamente por substituição direta.

## 8. Exercícios de Fixação
1. Calcule \( \lim_{x \to 3} \sqrt{2x^2 + 7} \).
2. Calcule \( \lim_{x \to -2} \frac{x^2 + 4x}{x - 2} \).
3. Se \( \lim_{x \to 1} f(x) = 6 \) e \( \lim_{x \to 1} g(x) = -2 \), calcule \( \lim_{x \to 1} \frac{[f(x)]^2}{g(x) + 8} \).

## 9. Correção Comentada dos Exercícios
1. A função sob a raiz é polinomial. Avaliando \( 2(3)^2 + 7 = 2(9) + 7 = 18 + 7 = 25 \). Pela Lei da Raiz: \( \sqrt{25} = 5 \).
2. O denominador vale \( -2 - 2 = -4 \neq 0 \). O numerador vale \( (-2)^2 + 4(-2) = 4 - 8 = -4 \). Logo: \( \frac{-4}{-4} = 1 \).
3. Pela Lei da Potência e do Quociente: \( \frac{6^2}{-2 + 8} = \frac{36}{6} = 6 \).

## 10. Desafio de Domínio
Explique por que a lei do quociente NÃO pode ser aplicada para resolver \( \lim_{x \to 2} \frac{x - 2}{x^2 - 4} \), e qual procedimento algébrico deve preceder o cálculo desse limite.

## 11. Resumo e Pontos-Chave
- As propriedades operatórias transferem as operações de soma, produto e divisão diretamente aos valores dos limites.
- Polinômios e funções racionais são avaliadas por substituição direta quando o denominador não se anula.
- Divisão com numerador zero e denominador não-nulo resulta em zero.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 2.3: "Calculando Limites Usando as Propriedades dos Limites".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Seção 3.3: "Propriedades Operatórias".
- STRANG, Gilbert; HERMAN, Edwin. *Calculus Volume 1*. OpenStax, 2016. Seção 2.3: "The Limit Laws".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Propriedade da Substituição Direta",
                syntax = """\\lim_{x \\to a} P(x) = P(a)""",
                description = "Válida para todo polinômio e para funções contínuas em seu domínio.",
                parametersInfo = "Se o denominador for diferente de zero, substitua x diretamente por a."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Lei do Quociente de Limites",
                syntax = """\\lim_{x \\to a} \\frac{f(x)}{g(x)} = \\frac{\\lim f(x)}{\\lim g(x)}""",
                description = "O limite da divisão é a divisão dos limites.",
                parametersInfo = "Válido estritamente se o limite do denominador for diferente de 0."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cálculo de Polinômio por Substituição Direta",
                codeOrFormula = """\\lim_{x \\to 2} (4x^3 - 3x^2 + 7x - 5) = 4(8) - 3(4) + 14 - 5 = 29""",
                explanation = "Polinômios são contínuos em toda a reta real; portanto, o limite coincide com o valor numérico f(2)."
            )
        ),
        goldTip = "O primeiro passo obrigatório ao calcular qualquer limite é tentar a substituição direta. Se resultar em um número real definido, a questão está resolvida em 5 segundos!"
    )

    // =========================================================================
    // AULA 4: Indeterminação 0/0 e o Furo Removível
    // =========================================================================
    private val bundleCalcL4 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Quando a substituição direta falha gerando o símbolo \( \frac{0}{0} \), muitos estudantes acreditam erroneamente que o cálculo é impossível ou que a resposta é zero ou infinito. No jargão matemático, \( \frac{0}{0} \) é denominado uma **forma indeterminada**. Ela não representa um número real nem significa inexistência de limite; indica unicamente que as ferramentas da aritmética falharam e que as estruturas algébricas da função devem ser investigadas para revelar a geometria subjacente: uma **descontinuidade removível** (ou furo no gráfico).

## 2. Objetivos de Aprendizagem
- Compreender o significado analítico da forma indeterminada \( \frac{0}{0} \).
- Aplicar o Teorema do Fator de D'Alembert para identificar e cancelar o fator nulo \( (x - a) \).
- Manipular produtos notáveis (diferença de quadrados, trinômio do 2º grau e cubos) para eliminar indeterminações.
- Interpretar geometricamente o limite como o preenchimento ideal do buraco na curva.

## 3. Pré-requisitos
- Propriedades básicas de limites e substituição direta (Aula 3).
- Fatoração de polinômios: fator comum, agrupamento, diferença de quadrados \( a^2 - b^2 = (a-b)(a+b) \) e trinômio quadrado perfeito.
- Divisão de polinômios elementar.

## 4. Explicação Teórica Aprofundada
### O que Significa \( \frac{0}{0} \)?
Se ao avaliarmos \( \lim_{x \to a} \frac{f(x)}{g(x)} \) encontramos \( f(a) = 0 \) e \( g(a) = 0 \), estamos diante de duas grandezas que estão se extinguindo simultaneamente à medida que \( x \to a \). O valor do limite dependerá da **taxa relativa** com que \( f(x) \) e \( g(x) \) aproximam-se de zero:
- Se \( f(x) \) e \( g(x) \) convergem para zero na mesma ordem de grandeza, o limite será uma constante real não-nula finita.
- O símbolo \( \frac{0}{0} \) é um convite à simplificação algébrica: existe um fator comum \( (x - a) \) "escondido" no numerador e no denominador responsável por anular ambos.

### O Teorema do Fator
Se \( P(x) \) é um polinômio e \( P(a) = 0 \), então \( (x - a) \) é obrigatoriamente um divisor exato de \( P(x) \). Ou seja:
\[ P(x) = (x - a) \cdot Q(x) \]
onde \( Q(x) \) é um polinômio de grau uma unidade inferior ao de \( P(x) \).
Portanto, quando encontramos \( \lim_{x \to a} \frac{P(x)}{R(x)} = \frac{0}{0} \):
1. Fatoramos \( P(x) = (x - a) P_1(x) \).
2. Fatoramos \( R(x) = (x - a) R_1(x) \).
3. Como \( x \to a \) implica \( x \neq a \), temos \( (x - a) \neq 0 \). Podemos cancelar com total rigor:
\[ \frac{P(x)}{R(x)} = \frac{(x - a) P_1(x)}{(x - a) R_1(x)} = \frac{P_1(x)}{R_1(x)} \quad \forall x \neq a \]
4. Calculamos o limite da nova expressão simplificada \( \frac{P_1(a)}{R_1(a)} \).

## 5. Definições e Conceitos
- **Forma Indeterminada:** Expressão matemática cujo resultado não pode ser estabelecido apenas com os limites individuais dos termos.
- **Fator Nulo Causal:** O termo \( (x - a) \) que provoca o cancelamento das parcelas em \( x = a \).
- **Descontinuidade Removível:** Ponto \( a \) onde \( \lim_{x \to a} f(x) \) existe e é finito, mas a função não está definida ou possui valor diferente de \( L \).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Trinômio do 2º Grau sobre Diferença de Quadrados
Calcule \( \lim_{x \to 2} \frac{x^2 + x - 6}{x^2 - 4} \).
- **Passo 1 (Substituição direta):**
  Numerador: \( 2^2 + 2 - 6 = 4 + 2 - 6 = 0 \).
  Denominador: \( 2^2 - 4 = 4 - 4 = 0 \).
  Forma Indeterminada \( \frac{0}{0} \). Fator nulo a ser cancelado: \( (x - 2) \).
- **Passo 2 (Fatoração do numerador):**
  As raízes de \( x^2 + x - 6 = 0 \) são \( x = 2 \) e \( x = -3 \) (por soma \( S = -1 \) e produto \( P = -6 \)).
  Logo, \( x^2 + x - 6 = (x - 2)(x + 3) \).
- **Passo 3 (Fatoração do denominador):**
  Diferença de quadrados: \( x^2 - 4 = (x - 2)(x + 2) \).
- **Passo 4 (Cancelamento e cálculo do limite):**
  \[ \lim_{x \to 2} \frac{(x - 2)(x + 3)}{(x - 2)(x + 2)} = \lim_{x \to 2} \frac{x + 3}{x + 2} = \frac{2 + 3}{2 + 2} = \frac{5}{4} \]

### Exemplo 2: Diferença de Cubos
Calcule \( \lim_{x \to 1} \frac{x^3 - 1}{x^2 - 1} \).
- **Passo 1 (Diagnóstico):** \( \frac{1 - 1}{1 - 1} = \frac{0}{0} \). Fator causal: \( (x - 1) \).
- **Passo 2 (Fatoração):**
  Numerador (diferença de cubos \( a^3 - b^3 = (a - b)(a^2 + ab + b^2) \)):
  \( x^3 - 1 = (x - 1)(x^2 + x + 1) \).
  Denominador: \( x^2 - 1 = (x - 1)(x + 1) \).
- **Passo 3 (Simplificação):**
  \[ \lim_{x \to 1} \frac{(x - 1)(x^2 + x + 1)}{(x - 1)(x + 1)} = \lim_{x \to 1} \frac{x^2 + x + 1}{x + 1} = \frac{1 + 1 + 1}{1 + 1} = \frac{3}{2} \]

## 7. Aplicações Práticas e Modelagem
- **Definição da Derivada:** Toda a teoria de diferenciação fundamenta-se no limite do quociente de Newton: \( \lim_{h \to 0} \frac{f(x+h) - f(x)}{h} \). Em \( h = 0 \), este quociente é sempre uma indeterminação \( \frac{0}{0} \), sendo resolvido exatamente pelo cancelamento de \( h \).
- **Economia Marginal:** O custo marginal é definido pela variação do custo total dividido pela variação marginal da produção, recaindo em quocientes indeterminados resolvidos analiticamente.

## 8. Exercícios de Fixação
1. Calcule \( \lim_{x \to -3} \frac{x^2 - 9}{x + 3} \).
2. Calcule \( \lim_{x \to 3} \frac{x^2 - 5x + 6}{x^2 - 9} \).
3. Determine \( \lim_{x \to 0} \frac{2x^2 + 5x}{3x^3 - x} \).

## 9. Correção Comentada dos Exercícios
1. Substituição direta: \( \frac{(-3)^2 - 9}{-3 + 3} = \frac{0}{0} \). Fatorando o numerador: \( \frac{(x-3)(x+3)}{x+3} = x - 3 \). Limite: \( -3 - 3 = -6 \).
2. Substituição em \( x = 3 \): \( \frac{9 - 15 + 6}{9 - 9} = \frac{0}{0} \). Fatorando: numerador \( (x - 3)(x - 2) \); denominador \( (x - 3)(x + 3) \). Simplificando: \( \frac{x - 2}{x + 3} \to \frac{3 - 2}{3 + 3} = \frac{1}{6} \).
3. Em \( x = 0 \): \( \frac{0}{0} \). Colocando \( x \) em evidência: \( \frac{x(2x + 5)}{x(3x^2 - 1)} \). Cancelando \( x \neq 0 \): \( \frac{2(0) + 5}{3(0)^2 - 1} = \frac{5}{-1} = -5 \).

## 10. Desafio de Domínio
Calcule \( \lim_{x \to 1} \frac{x^4 - 1}{x^3 - 1} \) fatorando completamente as duas expressões através de identidades polinomiais.

## 11. Resumo e Pontos-Chave
- \( \frac{0}{0} \) é uma indeterminação, não um resultado numérico.
- O Teorema do Fator assegura que se \( x = a \) anula um polinômio, \( (x - a) \) é fator comum.
- O cancelamento algébrico é legitimado pela condição de limite \( x \neq a \).

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Capítulo 2: "Limites e Derivadas".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Seção 3.4: "Indeterminações Algébricas".
- LEITHOLD, Louis. *O Cálculo com Geometria Analítica*. 3ª ed. Harbra, 1994. Seção 2.3.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema do Fator Polinomial",
                syntax = """P(a) = 0 \\implies P(x) = (x - a) \\cdot Q(x)""",
                description = "Se 'a' é raiz do polinômio, (x - a) fatora a expressão.",
                parametersInfo = "Permite identificar e cancelar o termo nulo responsável por 0/0."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Diferença de Quadrados e Cubos",
                syntax = """a^2 - b^2 = (a-b)(a+b); \\quad a^3 - b^3 = (a-b)(a^2 + ab + b^2)""",
                description = "Identidades algébricas fundamentais para cancelamento de fatores nulos.",
                parametersInfo = "Substitua a por x e b pela raiz correspondente."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cancelamento do Fator Nulo em 0/0",
                codeOrFormula = """\\lim_{x \\to 2} \\frac{x^2 + x - 6}{x^2 - 4} = \\lim_{x \\to 2} \\frac{(x-2)(x+3)}{(x-2)(x+2)} = \\frac{5}{4}""",
                explanation = "Ambos os termos se anulavam em x=2. O fator comum (x-2) foi cancelado rigorosamente pois x ≠ 2."
            )
        ),
        goldTip = "Ao ver 0/0 com polinômios, nunca entre em pânico: seu único trabalho é encontrar o fator (x - a) no andar de cima e no andar de baixo e eliminá-lo!"
    )

    // =========================================================================
    // AULA 5: Ferramentas Algébricas (Fatoração e Racionalização)
    // =========================================================================
    private val bundleCalcL5 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Nem todas as indeterminações do tipo \( \frac{0}{0} \) envolvem simples polinômios fatoráveis por produtos notáveis óbvios. Frequentemente encontramos expressões envolvendo raízes quadradas ou cúbicas onde o fator causal \( (x - a) \) está "preso" sob o sinal de radical. Nestes cenários, a técnica rainha da álgebra é a **racionalização por conjugado**, que liberta o fator nulo através de identidades notáveis, permitindo sua eliminação limpa e a resolução do limite.

## 2. Objetivos de Aprendizagem
- Reconhecer quando aplicar a técnica da racionalização multiplicando pelo conjugado.
- Utilizar a identidade \( (u - v)(u + v) = u^2 - v^2 \) para eliminar raízes quadradas no numerador ou denominador.
- Generalizar a racionalização para raízes cúbicas usando a identidade de soma e diferença de cubos.
- Aplicar o algoritmo de Briot-Ruffini para fatoração de polinômios de graus elevados (\( n \ge 3 \)).

## 3. Pré-requisitos
- Resolução de indeterminações \( \frac{0}{0} \) por fatoração básica (Aula 4).
- Operações com radicais e propriedades de potências fracionárias.
- Produtos notáveis: \( (a - b)(a + b) = a^2 - b^2 \) e \( (a - b)(a^2 + ab + b^2) = a^3 - b^3 \).

## 4. Explicação Teórica Aprofundada
### O Princípio da Racionalização pelo Conjugado
Quando temos uma expressão do tipo \( \sqrt{A(x)} - B(x) \) que se anula em \( x = a \), a raiz quadrada impede a divisão algébrica direta. Para eliminar o radical sem alterar o valor numérico da fração, multiplicamos o numerador e o denominador pelo **conjugado**:
\[ \text{Conjugado de } (\sqrt{A} - B) = (\sqrt{A} + B) \]
Ao efetuar o produto notável da soma pela diferença:
\[ (\sqrt{A} - B)(\sqrt{A} + B) = (\sqrt{A})^2 - B^2 = A - B^2 \]
O radical desaparece no termo operado! Em contrapartida, surge o termo \( (\sqrt{A} + B) \) no outro membro da fração, mas esse novo termo é absolutamente inofensivo, pois em \( x = a \) ele avalia para um valor estritamente positivo (diferente de zero).

### Racionalização de Raízes Cúbicas
Para expressões envolvendo \( \sqrt[3]{A} - \sqrt[3]{B} \), o conjugado quadrático não elimina a raiz. Devemos recorrer à identidade da diferença de cubos:
\[ (u - v)(u^2 + uv + v^2) = u^3 - v^3 \]
Fazendo \( u = \sqrt[3]{A} \) e \( v = \sqrt[3]{B} \), o fator racionalizante adequado é:
\[ (\sqrt[3]{A^2} + \sqrt[3]{AB} + \sqrt[3]{B^2}) \]
ao ser multiplicado, produzirá \( (\sqrt[3]{A})^3 - (\sqrt[3]{B})^3 = A - B \).

## 5. Definições e Conceitos
- **Conjugado Algébrico:** Expressão obtida pela inversão do sinal entre dois termos contendo radicais.
- **Fator Racionalizante:** Multiplicador unitário \( \frac{C(x)}{C(x)} = 1 \) que transforma uma expressão irracional em racional.
- **Dispositivo Prático de Briot-Ruffini:** Algoritmo rápido para efetuar a divisão de um polinômio \( P(x) \) pelo binômio \( (x - a) \).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Racionalização com Raiz Quadrada
Calcule \( \lim_{x \to 0} \frac{\sqrt{x + 4} - 2}{x} \).
- **Passo 1 (Substituição direta):**
  \( \frac{\sqrt{0 + 4} - 2}{0} = \frac{2 - 2}{0} = \frac{0}{0} \). Indeterminação com radical.
- **Passo 2 (Multiplicação pelo conjugado):**
  O conjugado do numerador \( (\sqrt{x + 4} - 2) \) é \( (\sqrt{x + 4} + 2) \). Multiplicamos em cima e embaixo:
  \[ \lim_{x \to 0} \frac{(\sqrt{x + 4} - 2)(\sqrt{x + 4} + 2)}{x (\sqrt{x + 4} + 2)} \]
- **Passo 3 (Desenvolvimento do produto notável):**
  Numerador: \( (\sqrt{x + 4})^2 - 2^2 = (x + 4) - 4 = x \).
  A expressão torna-se:
  \[ \lim_{x \to 0} \frac{x}{x (\sqrt{x + 4} + 2)} \]
- **Passo 4 (Cancelamento do fator nulo \( x \)):**
  Como \( x \to 0 \implies x \neq 0 \), cancelamos \( x \):
  \[ \lim_{x \to 0} \frac{1}{\sqrt{x + 4} + 2} \]
- **Passo 5 (Substituição final):**
  \[ \frac{1}{\sqrt{0 + 4} + 2} = \frac{1}{2 + 2} = \frac{1}{4} \]

### Exemplo 2: Limite com Briot-Ruffini
Calcule \( \lim_{x \to 1} \frac{x^3 - 3x + 2}{x^2 - 1} \).
- **Passo 1 (Diagnóstico):** \( \frac{1 - 3 + 2}{1 - 1} = \frac{0}{0} \). Fator nulo: \( (x - 1) \).
- **Passo 2 (Divisão de \( x^3 - 3x + 2 \) por \( x - 1 \)):**
  Coeficientes: 1, 0, -3, 2 com raiz 1.
  Descendo o 1: \( 1 \cdot 1 + 0 = 1 \); \( 1 \cdot 1 + (-3) = -2 \); \( -2 \cdot 1 + 2 = 0 \).
  Quociente: \( x^2 + x - 2 \).
  Logo, \( x^3 - 3x + 2 = (x - 1)(x^2 + x - 2) \).
- **Passo 3 (Simplificação):**
  \[ \lim_{x \to 1} \frac{(x - 1)(x^2 + x - 2)}{(x - 1)(x + 1)} = \lim_{x \to 1} \frac{x^2 + x - 2}{x + 1} = \frac{1 + 1 - 2}{1 + 1} = \frac{0}{2} = 0 \]

## 7. Aplicações Práticas e Modelagem
- **Derivada da Função Raiz:** A taxa de variação da função \( f(x) = \sqrt{x} \) provém do limite \( \lim_{h \to 0} \frac{\sqrt{x+h} - \sqrt{x}}{h} \), calculado obrigatoriamente por conjugado, resultando na famosa derivada \( \frac{1}{2\sqrt{x}} \).
- **Relatividade Restrita:** Na física relativística, o fator de Lorentz \( \gamma = \frac{1}{\sqrt{1 - v^2/c^2}} \) gera limites irracionais para velocidades próximas à da luz (\( v \to c \)).

## 8. Exercícios de Fixação
1. Calcule \( \lim_{x \to 9} \frac{\sqrt{x} - 3}{x - 9} \).
2. Determine \( \lim_{x \to 7} \frac{\sqrt{x + 2} - 3}{x - 7} \).
3. Calcule \( \lim_{x \to 0} \frac{x}{\sqrt{1 + x} - 1} \).

## 9. Correção Comentada dos Exercícios
1. Multiplicando pelo conjugado \( \sqrt{x} + 3 \): numerador vira \( (\sqrt{x})^2 - 9 = x - 9 \). Cancelando com o denominador: \( \lim_{x \to 9} \frac{1}{\sqrt{x} + 3} = \frac{1}{3 + 3} = \frac{1}{6} \).
2. Conjugado \( \sqrt{x+2} + 3 \). Numerador vira \( (x + 2) - 9 = x - 7 \). Cancelando \( (x - 7) \): \( \lim_{x \to 7} \frac{1}{\sqrt{x+2} + 3} = \frac{1}{3 + 3} = \frac{1}{6} \).
3. O radical está no denominador. Multiplicando por \( \sqrt{1+x} + 1 \) em cima e embaixo: denominador vira \( (1+x) - 1 = x \). Expressão simplificada: \( \lim_{x \to 0} (\sqrt{1+x} + 1) = 1 + 1 = 2 \).

## 10. Desafio de Domínio
Calcule o limite irracional duplo: \( \lim_{x \to 4} \frac{\sqrt{2x + 1} - 3}{\sqrt{x - 2} - \sqrt{2}} \) aplicando a técnica da dupla racionalização.

## 11. Resumo e Pontos-Chave
- A presença de raízes em indeterminações 0/0 requer multiplicação pelo conjugado.
- Multiplicar numerador e denominador pelo mesmo termo preserva a equivalência analítica da função.
- O produto notável \( (a - b)(a + b) = a^2 - b^2 \) destrói a raiz quadrada, liberando o fator nulo.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Capítulo 2: Seção 2.3.
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Seção 3.4.
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seção 2.3: "Evaluating Limits Algebraically".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Racionalização por Conjugado Quadrático",
                syntax = """(\\sqrt{A} - B)(\\sqrt{A} + B) = A - B^2""",
                description = "Elimina o radical que aprisiona a indeterminação no numerador ou denominador.",
                parametersInfo = "Multiplique sempre numerador e denominador pela mesma expressão conjugada."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Conjugado para Raízes Cúbicas",
                syntax = """(\\sqrt[3]{A} - \\sqrt[3]{B})(\\sqrt[3]{A^2} + \\sqrt[3]{AB} + \\sqrt[3]{B^2}) = A - B""",
                description = "Identidade notável para eliminar raízes cúbicas em limites indeterminados.",
                parametersInfo = "Utilizado quando o índice do radical for 3."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Racionalização com Radicais Quadrados",
                codeOrFormula = """\\lim_{x \\to 0} \\frac{\\sqrt{x + 4} - 2}{x} = \\lim_{x \\to 0} \\frac{(x+4) - 4}{x(\\sqrt{x+4}+2)} = \\frac{1}{4}""",
                explanation = "A multiplicação pelo conjugado (sqrt(x+4)+2) desfez a raiz e gerou o fator x que cancelou o denominador."
            )
        ),
        goldTip = "Identificou raiz quadrada na fração gerando 0/0? Não pense duas vezes: aplique imediatamente o conjugado com sinal invertido e veja a mágica da simplificação acontecer!"
    )
}

