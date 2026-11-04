package com.example.data.math.didactic.calculus

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Cálculo I — Módulo 2: Derivadas e Aplicações (Parte 2: Aulas 15 a 18).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em James Stewart, Guidorizzi e OpenStax Calculus Vol 1.
 */
object CalculusDerivativesPart2 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "calc_l15" -> bundleCalcL15
            "calc_l16" -> bundleCalcL16
            "calc_l17" -> bundleCalcL17
            "calc_l18" -> bundleCalcL18
            else -> null
        }
    }

    // =========================================================================
    // AULA 15: Aproximação Linear e Diferenciais
    // =========================================================================
    private val bundleCalcL15 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como calcular o valor aproximado de \( \sqrt{4,05} \) ou \( \sin(0,02) \) sem uma calculadora científica? No cerne do Cálculo reside um princípio de simplicidade revolucionária: qualquer curva suave, quando observada sob um microscópio em uma escala suficientemente pequena, assemelha-se a uma reta. A reta tangente deixa de ser apenas uma construção geométrica e passa a atuar como a **Linearização Local** da função, permitindo estimar valores e controlar a propagação de erros experimentais através de **diferenciais**.

## 2. Objetivos de Aprendizagem
- Construir a função linearização \( L(x) = f(a) + f'(a)(x - a) \) ao redor de um ponto de referência \( a \).
- Estimar valores numéricos de funções complexas com alto grau de precisão analítica.
- Definir e calcular diferenciais \( dy = f'(x) dx \) e distinguir geometricamente \( dy \) da variação real \( \Delta y \).
- Quantificar erros de medição experimentais: erro absoluto, erro relativo e erro percentual.

## 3. Pré-requisitos
- Equação cartesiana da reta tangente e coeficiente angular (Aulas 10 e 11).
- Regras básicas de derivação (Aula 12).
- Noções de estimativa numérica e ordens de grandeza.

## 4. Explicação Teórica Aprofundada
### A Função Linearização
A equação da reta tangente a uma função diferenciável \( f \) no ponto \( (a, f(a)) \) é dada por:
\[ y - f(a) = f'(a)(x - a) \implies y = f(a) + f'(a)(x - a) \]
Chamamos a função afim:
\[ L(x) = f(a) + f'(a)(x - a) \]
de **linearização de \( f \) em \( a \)**. A aproximação:
\[ f(x) \approx L(x) = f(a) + f'(a)(x - a) \]
é denominada a **aproximação linear** (ou aproximação por reta tangente) de \( f \) em torno de \( a \). Ela é extraordinariamente precisa para valores de \( x \) muito próximos de \( a \).

### O Conceito de Diferencial
Sejam \( x \) a variável independente e \( y = f(x) \) uma função diferenciável:
- O **diferencial \( dx \)** é definido como uma variável independente qualquer (representando uma pequena perturbação \( \Delta x \)).
- O **diferencial \( dy \)** é definido pela relação operacional:
  \[ dy = f'(x) dx \]
*Interpretação Geométrica:*
Enquanto \( \Delta y = f(x + \Delta x) - f(x) \) representa a variação vertical **real** percorrida ao longo da curva, \( dy \) representa a variação vertical percorrida **ao longo da reta tangente**. Para acréscimos infinitesimais \( \Delta x \to 0 \), a diferença entre \( \Delta y \) e \( dy \) torna-se desprezível.

### Propagação de Erros em Medições de Engenharia
Em experimentos reais, nunca medimos grandezas com exatidão infinita; sempre há uma incerteza instrumental \( dx \). Se calculamos uma grandeza derivada \( y = f(x) \):
- **Erro Absoluto:** \( |dy| \approx |f'(x) dx| \)
- **Erro Relativo:** \( \frac{dy}{y} = \frac{f'(x) dx}{f(x)} \)
- **Erro Percentual:** \( \text{Erro Relativo} \times 100\% \)

## 5. Definições e Conceitos
- **Linearização Local:** Substituição de uma função não-linear pela sua reta tangente em uma vizinhança restrita.
- **Diferencial:** Variação linear estimada de uma grandeza, base do cálculo infinitesimal e de integrais.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Estimativa de Raiz Quadrada
Utilize a aproximação linear para estimar o valor numérico de \( \sqrt{4,05} \).
- **Passo 1 (Escolher a função e o ponto de ancoragem):**
  A função é \( f(x) = \sqrt{x} \). O ponto de ancoragem \( a \) deve ser um número muito próximo de 4,05 cuja raiz exata seja trivial: escolhemos \( a = 4 \).
- **Passo 2 (Calcular \( f(a) \) e \( f'(a) \)):**
  \( f(4) = \sqrt{4} = 2 \).
  A derivada de \( \sqrt{x} = x^{1/2} \) é \( f'(x) = \frac{1}{2\sqrt{x}} \).
  No ponto \( a = 4 \): \( f'(4) = \frac{1}{2\sqrt{4}} = \frac{1}{4} = 0,25 \).
- **Passo 3 (Construir a linearização \( L(x) \)):**
  \[ L(x) = f(4) + f'(4)(x - 4) = 2 + 0,25(x - 4) \]
- **Passo 4 (Avaliar no ponto desejado \( x = 4,05 \)):**
  \( x - 4 = 4,05 - 4 = 0,05 \).
  \[ L(4,05) = 2 + 0,25(0,05) = 2 + 0,0125 = 2,0125 \]
- *(Nota: O valor real com calculadora é 2,01246... O erro é de apenas 0,002%!)*

### Exemplo 2: Propagação de Erro no Volume de uma Esfera
O raio de uma esfera metálica é medido como sendo \( r = 10 \text{ cm} \), com um erro máximo possível de \( \pm 0,05 \text{ cm} \). Estime o erro máximo na medição do volume da esfera e determine o erro percentual.
- **Passo 1 (Equação da grandeza):**
  \( V(r) = \frac{4}{3} \pi r^3 \).
- **Passo 2 (Calcular o diferencial \( dV \)):**
  \( dV = V'(r) dr = \left(4 \pi r^2\right) dr \).
- **Passo 3 (Substituir os valores):**
  \( r = 10 \) e \( dr = 0,05 \):
  \[ dV = 4 \pi (10)^2 (0,05) = 4 \pi (100) (0,05) = 20\pi \approx 62,83 \text{ cm}^3 \]
- **Passo 4 (Erro relativo e percentual):**
  \[ \frac{dV}{V} = \frac{4\pi r^2 dr}{\frac{4}{3}\pi r^3} = 3 \frac{dr}{r} \]
  Como o erro relativo no raio é \( \frac{dr}{r} = \frac{0,05}{10} = 0,005 = 0,5\% \), o erro no volume é:
  \[ 3 \times 0,5\% = 1,5\% \]

## 7. Aplicações Práticas e Modelagem
- **Projeto de Tolerância Mecânica:** Na usinagem CNC de peças aeronáuticas, os diferenciais determinam a faixa de tolerância milimétrica admissível para garantir o encaixe térmico de turbinas.
- **Sensibilidade de Sensores Biomédicos:** O desvio de calibração em biossensores de glicose é calibrado através da linearização de Taylor de primeira ordem da resposta eletroquímica.

## 8. Exercícios de Fixação
1. Encontre a linearização da função \( f(x) = x^3 \) em torno de \( a = 2 \) e use-a para aproximar \( (2,01)^3 \).
2. Determine o diferencial \( dy \) para a função \( y = x^2 \sin(3x) \).
3. Se o lado de um quadrado é medido com um erro percentual de \( 1\% \), qual é o erro percentual aproximado na determinação de sua área?

## 9. Correção Comentada dos Exercícios
1. \( f(2) = 8 \). \( f'(x) = 3x^2 \implies f'(2) = 12 \). \( L(x) = 8 + 12(x - 2) \).
   Para \( x = 2,01 \): \( L(2,01) = 8 + 12(0,01) = 8 + 0,12 = 8,12 \).
2. Pela regra do produto e cadeia: \( dy = [2x \sin(3x) + 3x^2 \cos(3x)] dx \).
3. Área \( A = x^2 \implies dA = 2x dx \). O erro relativo na área é \( \frac{dA}{A} = \frac{2x dx}{x^2} = 2 \frac{dx}{x} \).
   Como o erro percentual no lado é \( 1\% \), o erro percentual na área é \( 2 \times 1\% = 2\% \).

## 10. Desafio de Domínio
Deduza a fórmula geral de aproximação linear para raízes de pequenas variações: \( (1 + x)^k \approx 1 + kx \) para \( x \approx 0 \), e use-a para estimar \( \frac{1}{\sqrt{0,98}} \).

## 11. Resumo e Pontos-Chave
- A linearização \( L(x) = f(a) + f'(a)(x-a) \) usa a reta tangente como substituta aproximada da curva.
- O diferencial \( dy = f'(x)dx \) mede a variação linear instantânea.
- Em potências \( y = x^k \), o erro percentual relativo é simplesmente \( k \) vezes o erro relativo da base (\( \frac{dy}{y} = k \frac{dx}{x} \)).

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 3.10: "Aprovações Lineares e Diferenciais".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 7: "Diferenciais".
- STRANG, Gilbert; HERMAN, Edwin. *Calculus Volume 1*. OpenStax, 2016. Seção 4.2: "Linear Approximations and Differentials".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Função Linearização Local",
                syntax = "L(x) = f(a) + f'(a)(x - a)",
                description = "Equação da reta tangente usada como estimador funcional.",
                parametersInfo = "Válido com alta precisão na vizinhança imediata de a."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Equação do Diferencial",
                syntax = "dy = f'(x) dx",
                description = "Relação linear entre a variação diferencial de x e y.",
                parametersInfo = "dx representa o erro instrumental ou variação marginal independente."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Propagação de Erro Relativo em Potências",
                syntax = """y = x^k \\implies \\frac{dy}{y} = k \\frac{dx}{x}""",
                description = "O erro relativo é amplificado pelo expoente k.",
                parametersInfo = "Deduzido por diferenciação logarítmica."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Aproximação Linear de Raiz Quadrada",
                codeOrFormula = """L(4,05) = \\sqrt{4} + \\frac{1}{2\\sqrt{4}}(4,05 - 4) = 2 + 0,25(0,05) = 2,0125""",
                explanation = "A reta tangente ancorada no ponto exato a=4 estimou a raiz com precisão de 4 casas decimais."
            )
        ),
        goldTip = "Para calcular aproximações de cabeça em exames, utilize a fórmula rápida do diferencial: f(a + Δx) ≈ f(a) + f'(a)·Δx. É a ferramenta mais eficiente para economizar tempo sem usar calculadora!"
    )

    // =========================================================================
    // AULA 16: Estudo Completo da Função e Esboço de Gráficos
    // =========================================================================
    private val bundleCalcL16 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como desenhar com precisão cirúrgica o gráfico de uma função complexa sem precisar plotar centenas de pontos aleatórios? O Cálculo Diferencial nos fornece um raio-X completo das curvas: a primeira derivada revela a inclinação, o crescimento e os pontos de máximo e mínimo locais; a segunda derivada revela a concavidade e os pontos de inflexão onde a curva muda de curvatura. Combinando esses testes ao estudo de assíntotas e simetrias, somos capazes de decifrar toda a topologia de uma função.

## 2. Objetivos de Aprendizagem
- Definir pontos críticos: pontos onde \( f'(c) = 0 \) ou onde \( f'(c) \) não existe.
- Aplicar o Teste da Primeira Derivada para classificar intervalos de crescimento e decrescimento e extremos relativos.
- Definir concavidade e aplicar o Teste da Segunda Derivada para classificação rápida de máximos e mínimos locais.
- Identificar e calcular pontos de inflexão onde a concavidade da curva se inverte.

## 3. Pré-requisitos
- Regras de derivação de 1ª e 2ª ordens (Aulas 12 e 13).
- Estudo de sinais de funções polinomiais e racionais.
- Assíntotas verticais e horizontais (Aulas 7 e 8).

## 4. Explicação Teórica Aprofundada
### Pontos Críticos (ou Estacionários)
Um **ponto crítico** de uma função \( f \) é um número \( c \) pertencente ao domínio \( D_f \) tal que:
\[ f'(c) = 0 \quad \text{ou} \quad f'(c) \text{ não existe} \]
Pelo **Teorema de Fermat**, se \( f \) atinge um máximo ou mínimo local em \( c \), e se \( f'(c) \) existe, então necessariamente \( f'(c) = 0 \). Portanto, os pontos críticos são os únicos candidatos possíveis a abrigarem extremos locais!

### O Teste da Primeira Derivada (Crescimento e Extremos)
Seja \( f \) contínua em um intervalo e diferenciável:
1. Se \( f'(x) > 0 \) em um intervalo, a função é **estritamente crescente**.
2. Se \( f'(x) < 0 \) em um intervalo, a função é **estritamente decrescente**.
**Classificação do Ponto Crítico \( c \):**
- **Máximo Local:** Se \( f'(x) \) muda de positivo (\( + \)) para negativo (\( - \)) ao passar por \( c \).
- **Mínimo Local:** Se \( f'(x) \) muda de negativo (\( - \)) para positivo (\( + \)) ao passar por \( c \).
- **Nem Máximo Nem Mínimo (Ponto de Sela):** Se \( f'(x) \) mantém o mesmo sinal em ambos os lados de \( c \) (ex: \( y = x^3 \) na origem).

### Concavidade e o Teste da Segunda Derivada
A segunda derivada \( f''(x) = (f'(x))' \) mede a taxa de variação da inclinação:
- Se \( f''(x) > 0 \) em um intervalo: a inclinação está aumentando. O gráfico está com a **concavidade voltada para cima** (formato de bacia ou sorriso \( \cup \)). As retas tangentes ficam **abaixo** da curva.
- Se \( f''(x) < 0 \) em um intervalo: a inclinação está diminuindo. O gráfico está com a **concavidade voltada para baixo** (formato de cúpula \( \cap \)). As retas tangentes ficam **acima** da curva.
**O Teste da Segunda Derivada:**
Suponha que \( f'(c) = 0 \):
- Se \( f''(c) < 0 \implies c \) é um **Máximo Local** (tangente horizontal no topo de uma curva côncava para baixo).
- Se \( f''(c) > 0 \implies c \) é um **Mínimo Local** (tangente horizontal no fundo de uma curva côncava para cima).
- Se \( f''(c) = 0 \implies \) O teste é **inconclusivo** (deve-se recorrer ao teste da primeira derivada).

### Ponto de Inflexão
Um ponto \( P(c, f(c)) \) é chamado de **ponto de inflexão** se a curva é contínua nesse ponto e a **concavidade se inverte** (de para cima para para baixo ou vice-versa). Nos pontos de inflexão onde \( f'' \) existe e é contínua, temos necessariamente \( f''(c) = 0 \).

## 5. Definições e Conceitos
- **Teorema de Fermat:** Condição necessária para extremos no interior do domínio.
- **Ponto de Inflexão:** Ponto de transição de concavidade onde a reta tangente atravessa a curva.
- **Esboço Completo:** Roteiro que integra domínio, simetrias, assíntotas, intervalos de monotonia, extremos e concavidade.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Estudo Completo de um Polinômio Cúbico
Realize o estudo de extremos e concavidade de \( f(x) = x^3 - 3x^2 - 9x + 5 \).
- **Passo 1 (Primeira derivada e pontos críticos):**
  \[ f'(x) = 3x^2 - 6x - 9 \]
  Fazendo \( f'(x) = 0 \):
  \[ 3(x^2 - 2x - 3) = 0 \implies (x - 3)(x + 1) = 0 \]
  Pontos críticos: \( x = -1 \) e \( x = 3 \).
- **Passo 2 (Segunda derivada):**
  \[ f''(x) = 6x - 6 \]
- **Passo 3 (Aplicação do Teste da Segunda Derivada):**
  - Para \( x = -1 \): \( f''(-1) = 6(-1) - 6 = -12 < 0 \). Concavidade para baixo: \( x = -1 \) é um **Máximo Local**.
    O valor máximo é \( f(-1) = (-1)^3 - 3(-1)^2 - 9(-1) + 5 = -1 - 3 + 9 + 5 = 10 \).
  - Para \( x = 3 \): \( f''(3) = 6(3) - 6 = 18 - 6 = 12 > 0 \). Concavidade para cima: \( x = 3 \) é um **Mínimo Local**.
    O valor mínimo é \( f(3) = 27 - 3(9) - 9(3) + 5 = 27 - 27 - 27 + 5 = -22 \).
- **Passo 4 (Ponto de Inflexão):**
  Fazendo \( f''(x) = 0 \implies 6x - 6 = 0 \implies x = 1 \).
  Para \( x < 1 \), \( f''(x) < 0 \) (côncava para baixo); para \( x > 1 \), \( f''(x) > 0 \) (côncava para cima).
  Como houve mudança de concavidade, o ponto \( (1, f(1)) = (1, -6) \) é um **Ponto de Inflexão**.

## 7. Aplicações Práticas e Modelagem
- **Curva de Rendimentos Decrescentes em Economia:** Na função de produção total, o ponto de inflexão representa a transição onde o produto marginal começa a declinar.
- **Curva de Contágio Epidemiológico:** No modelo de infecção logística, o pico diário de novos contágios coincide exatamente com o ponto de inflexão da curva cumulativa.

## 8. Exercícios de Fixação
1. Encontre os pontos críticos da função \( f(x) = 2x^3 - 3x^2 - 12x + 1 \).
2. Determine os intervalos onde a função \( g(x) = x^4 - 4x^3 \) é côncava para cima e côncava para baixo.
3. Se \( f'(2) = 0 \) e \( f''(2) = -5 \), o que se pode afirmar sobre o ponto \( x = 2 \)?

## 9. Correção Comentada dos Exercícios
1. \( f'(x) = 6x^2 - 6x - 12 = 6(x^2 - x - 2) = 6(x - 2)(x + 1) = 0 \). Os pontos críticos são \( x = 2 \) e \( x = -1 \).
2. \( g'(x) = 4x^3 - 12x^2 \). \( g''(x) = 12x^2 - 24x = 12x(x - 2) \).
   Zeros da 2ª derivada: \( x = 0 \) e \( x = 2 \).
   - Para \( x < 0 \): \( g''(x) > 0 \) (côncava para cima).
   - Para \( 0 < x < 2 \): \( g''(x) < 0 \) (côncava para baixo).
   - Para \( x > 2 \): \( g''(x) > 0 \) (côncava para cima).
   Pontos de inflexão ocorrem em \( x = 0 \) e \( x = 2 \).
3. Pelo Teste da Segunda Derivada, como a primeira derivada é nula e a segunda é estritamente negativa (\( -5 < 0 \)), a função atinge um **máximo local** em \( x = 2 \).

## 10. Desafio de Domínio
Construa o esboço completo da curva racional \( f(x) = \frac{x^2}{x^2 - 1} \), determinando assíntotas, intervalos de crescimento e concavidade.

## 11. Resumo e Pontos-Chave
- Pontos críticos ocorrem onde \( f'(x) = 0 \) ou inexiste.
- Primeira derivada positiva indica crescimento; negativa indica decrescimento.
- Segunda derivada positiva indica concavidade para cima (\( \cup \), mínimo); negativa indica concavidade para baixo (\( \cap \), máximo).
- Pontos de inflexão marcam a inversão de concavidade onde \( f''(x) = 0 \).

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seções 4.1: "Valores Máximos e Mínimos", 4.3: "Como as Derivadas Afetam a Forma do Gráfico" e 4.5: "Resumo do Esboço de Curvas".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 9: "Estudo da Variação das Funções".
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seções 4.3 e 4.5.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Condição de Ponto Crítico",
                syntax = """f'(c) = 0 \\quad \\text{ou} \\quad f'(c) \\;\\nexists""",
                description = "Candidatos fundamentais a extremos locais.",
                parametersInfo = "O ponto c deve pertencer ao domínio de f."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Teste da Segunda Derivada",
                syntax = """f'(c) = 0 \\implies \\begin{cases} f''(c) < 0 \\implies \\text{Máximo Local} \\\\ f''(c) > 0 \\implies \\text{Mínimo Local} \\\\ f''(c) = 0 \\implies \\text{Inconclusivo} \\end{cases}""",
                description = "Critério rápido para classificar pontos críticos por concavidade.",
                parametersInfo = "Se f''(c) = 0, utilize a análise de sinal da primeira derivada."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Classificação de Extremos por Segunda Derivada",
                codeOrFormula = """f'(x) = 3x^2 - 6x - 9 = 0 \\implies x \\in \\{-1, 3\\}; \\quad f''(-1) = -12 < 0 \\text{ (Máx)}, \\quad f''(3) = 12 > 0 \\text{ (Mín)}""",
                explanation = "A concavidade negativa no ponto x=-1 confirma o topo da curva, enquanto a positiva em x=3 indica o fundo da curva."
            )
        ),
        goldTip = "Para nunca mais confundir concavidade com máximo/mínimo, visualize uma bacia: f'' > 0 é uma bacia segurando água (formato em U), logo o fundo é um MÍNIMO. f'' < 0 é uma cúpula tombando água (formato em arco), logo o topo é um MÁXIMO!"
    )

    // =========================================================================
    // AULA 17: Regra de L'Hôpital (O Shortcut de Ouro)
    // =========================================================================
    private val bundleCalcL17 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
No Módulo 1, quando nos deparávamos com formas indeterminadas como \( \frac{0}{0} \) ou \( \frac{\infty}{\infty} \), éramos forçados a recorrer a longas manipulações algébricas, divisões por Briot-Ruffini, racionalizações duplas ou limites trigonométricos geométricos. Mas e se a fração envolver exponenciais misturadas com polinômios ou logaritmos, onde nenhuma fatoração é viável? A resposta definitiva é a lendária **Regra de L'Hôpital**, o atalho diferencial mais poderoso de todo o Cálculo I.

## 2. Objetivos de Aprendizagem
- Enunciar as hipóteses teóricas rigorosas da Regra de L'Hôpital baseadas no Teorema do Valor Médio de Cauchy.
- Aplicar a regra diretamente às formas indeterminadas clássicas \( \frac{0}{0} \) e \( \frac{\infty}{\infty} \).
- Converter indeterminações do produto \( 0 \cdot \infty \) e de diferenças \( \infty - \infty \) em quocientes tratáveis.
- Resolver potências indeterminadas (\( 0^0 \), \( \infty^0 \), \( 1^\infty \)) combinando a Regra de L'Hôpital com a diferenciação logarítmica.

## 3. Pré-requisitos
- Regras básicas de derivação (potência, exponencial, logaritmo, trigonométricas) (Aulas 12 e 13).
- Identificação de formas indeterminadas (Aula 4).
- Continuidade e Teorema do Valor Médio.

## 4. Explicação Teórica Aprofundada
### O Enunciado Teórico de L'Hôpital
Suponha que \( f \) e \( g \) sejam diferenciáveis em um intervalo aberto contendo \( a \) (exceto possivelmente no próprio \( a \)), e que \( g'(x) \neq 0 \) na vizinhança perfurada de \( a \).
Se:
\[ \lim_{x \to a} \frac{f(x)}{g(x)} \quad \text{resultar na forma indeterminada } \frac{0}{0} \quad \text{ou} \quad \frac{\pm\infty}{\pm\infty} \]
Então:
\[ \lim_{x \to a} \frac{f(x)}{g(x)} = \lim_{x \to a} \frac{f'(x)}{g'(x)} \]
desde que o limite da razão entre as derivadas exista (seja um número real finito) ou seja \( \pm\infty \).
*Aviso Capital de Sobrevivência:* **NUNCA use a Regra do Quociente na Regra de L'Hôpital!** Deriva-se o numerador isoladamente (\( f'(x) \)) e o denominador isoladamente (\( g'(x) \)).

### A Justificativa pelo Teorema de Cauchy
Pelo Teorema do Valor Médio Generalizado de Cauchy, se \( f(a) = g(a) = 0 \), existe um número \( c \) entre \( a \) e \( x \) tal que:
\[ \frac{f(x)}{g(x)} = \frac{f(x) - f(a)}{g(x) - g(a)} = \frac{f'(c)}{g'(c)} \]
Quando \( x \to a \), o ponto intermediário \( c \) é forçado a convergir para \( a \) (\( c \to a \)), provando que \( \lim \frac{f(x)}{g(x)} = \lim \frac{f'(x)}{g'(x)} \).

### Transformação de Outras Formas Indeterminadas
1. **Produto Indeterminado (\( 0 \cdot \infty \)):**
   Se \( \lim f(x) = 0 \) e \( \lim g(x) = \infty \), inverta um dos fatores para o denominador:
   \[ f \cdot g = \frac{f}{1/g} \quad \left(\text{forma } \frac{0}{0}\right) \qquad \text{ou} \qquad f \cdot g = \frac{g}{1/f} \quad \left(\text{forma } \frac{\infty}{\infty}\right) \]
2. **Diferença Indeterminada (\( \infty - \infty \)):**
   Geralmente resolvida encontrando o mínimo denominador comum de frações algébricas para unificar a expressão em uma única fração.
3. **Potências Indeterminadas (\( 0^0 \), \( \infty^0 \), \( 1^\infty \)):**
   Tome \( y = [f(x)]^{g(x)} \), aplique o logaritmo \( \ln y = g(x) \ln f(x) \) para gerar a forma \( 0 \cdot \infty \), aplique L'Hôpital para achar \( L = \lim \ln y \), e finalmente calcule o limite original por \( e^L \).

## 5. Definições e Conceitos
- **Aplicação Repetida:** Se a primeira aplicação de L'Hôpital continuar gerando \( \frac{0}{0} \) ou \( \frac{\infty}{\infty} \), a regra pode ser aplicada sucessivamente quantas vezes forem necessárias.
- **Hierarquia de Crescimento:** O comportamento de L'Hôpital comprova formalmente que exponenciais crescem mais rápido que qualquer polinômio, que por sua vez crescem mais rápido que logaritmos: \( \ln x \ll x^n \ll e^x \).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Aplicação Direta em Indeterminação Clássica
Calcule \( \lim_{x \to 0} \frac{e^x - 1 - x}{x^2} \).
- **Passo 1 (Substituição direta):**
  Numerador: \( e^0 - 1 - 0 = 1 - 1 - 0 = 0 \). Denominador: \( 0^2 = 0 \). Forma \( \frac{0}{0} \).
- **Passo 2 (Primeira aplicação de L'Hôpital):**
  Derivando numerador e denominador:
  \[ \lim_{x \to 0} \frac{\frac{d}{dx}[e^x - 1 - x]}{\frac{d}{dx}[x^2]} = \lim_{x \to 0} \frac{e^x - 1}{2x} \]
- **Passo 3 (Verificação do novo limite):**
  Em \( x = 0 \): \( \frac{e^0 - 1}{2(0)} = \frac{0}{0} \). A indeterminação persiste!
- **Passo 4 (Segunda aplicação de L'Hôpital):**
  \[ \lim_{x \to 0} \frac{\frac{d}{dx}[e^x - 1]}{\frac{d}{dx}[2x]} = \lim_{x \to 0} \frac{e^x}{2} \]
- **Passo 5 (Avaliação final):**
  \[ \frac{e^0}{2} = \frac{1}{2} \]

### Exemplo 2: Produto Indeterminado \( 0 \cdot \infty \)
Calcule \( \lim_{x \to 0^+} x \ln x \).
- **Passo 1 (Diagnóstico):** Quando \( x \to 0^+ \), \( x \to 0 \) e \( \ln x \to -\infty \). Forma \( 0 \cdot (-\infty) \).
- **Passo 2 (Reescrever como quociente):**
  Invertemos a variável \( x \): \( x = \frac{1}{1/x} \):
  \[ \lim_{x \to 0^+} \frac{\ln x}{\frac{1}{x}} \]
  Agora temos a forma \( \frac{-\infty}{+\infty} \).
- **Passo 3 (Aplicar L'Hôpital):**
  Numerador derivado: \( (\ln x)' = \frac{1}{x} \).
  Denominador derivado: \( \left(\frac{1}{x}\right)' = -\frac{1}{x^2} \).
  \[ \lim_{x \to 0^+} \frac{\frac{1}{x}}{-\frac{1}{x^2}} = \lim_{x \to 0^+} \left( \frac{1}{x} \cdot (-x^2) \right) = \lim_{x \to 0^+} (-x) = 0 \]
- **Conclusão:** O limite vale 0.

## 7. Aplicações Práticas e Modelagem
- **Termodinâmica Estatística:** O limite de alta temperatura para a capacidade calorífica de Einstein dos sólidos quânticos recai na regra de L'Hôpital para recuperar a lei clássica de Dulong-Petit.
- **Teoria da Informação:** A definição da entropia de Shannon \( H = -\sum p_i \log_2 p_i \) depende do limite \( \lim_{p \to 0} p \log p = 0 \) para eventos de probabilidade nula.

## 8. Exercícios de Fixação
1. Calcule \( \lim_{x \to 0} \frac{\sin x - x}{x^3} \).
2. Determine \( \lim_{x \to \infty} \frac{\ln x}{\sqrt{x}} \).
3. Avalie \( \lim_{x \to 0^+} (1 + 2x)^{1/x} \).

## 9. Correção Comentada dos Exercícios
1. Forma \( \frac{0}{0} \). L'Hôpital 1: \( \frac{\cos x - 1}{3x^2} \) (\( \frac{0}{0} \)). L'Hôpital 2: \( \frac{-\sin x}{6x} \) (\( \frac{0}{0} \)). L'Hôpital 3: \( \frac{-\cos x}{6} \). Para \( x \to 0 \): \( \frac{-1}{6} \).
2. Forma \( \frac{\infty}{\infty} \). Derivando: \( \frac{1/x}{\frac{1}{2\sqrt{x}}} = \frac{2\sqrt{x}}{x} = \frac{2}{\sqrt{x}} \to 0 \) quando \( x \to \infty \). O logaritmo perde para a raiz.
3. Forma \( 1^\infty \). Seja \( y = (1+2x)^{1/x} \implies \ln y = \frac{\ln(1+2x)}{x} \). Aplicando L'Hôpital: \( \lim_{x \to 0^+} \frac{\frac{2}{1+2x}}{1} = 2 \). Como \( \lim \ln y = 2 \), o limite original é \( e^2 \).

## 10. Desafio de Domínio
Explique por que aplicar cegamente a regra de L'Hôpital para calcular \( \lim_{x \to 0} \frac{\cos x}{x + 1} \) produz uma resposta errada e qual condição obrigatória do teorema foi violada.

## 11. Resumo e Pontos-Chave
- A Regra de L'Hôpital SÓ pode ser aplicada se houver estritamente a forma \( \frac{0}{0} \) ou \( \frac{\infty}{\infty} \).
- Diferencie o numerador sozinho e o denominador sozinho.
- Potências indeterminadas exigem a conversão logarítmica antes da diferenciação.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 4.4: "Formas Indeterminadas e Regra de L'Hôpital".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 9: Seção 9.4.
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seção 4.8: "L'Hôpital's Rule".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema de L'Hôpital",
                syntax = """\\lim_{x \\to a} \\frac{f(x)}{g(x)} = \\lim_{x \\to a} \\frac{f'(x)}{g'(x)} \\quad \\left(\\text{se } \\frac{0}{0} \\text{ ou } \\frac{\\infty}{\\infty}\\right)""",
                description = "Cálculo de limite por razão de derivadas.",
                parametersInfo = "Verifique SEMPRE se a indeterminação é legítima antes de aplicar."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Potência Indeterminada para L'Hôpital",
                syntax = """y = f(x)^{g(x)} \\implies \\lim \\ln y = \\lim \\frac{\\ln f(x)}{1/g(x)} = L \\implies \\lim y = e^L""",
                description = "Método da exponenciação para formas 1^infinito, 0^0 e infinito^0.",
                parametersInfo = "Lembre-se de retornar ao exponencial no passo final."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Dupla Aplicação de L'Hôpital",
                codeOrFormula = """\\lim_{x \\to 0} \\frac{e^x - 1 - x}{x^2} \\stackrel{L'H}{=} \\lim_{x \\to 0} \\frac{e^x - 1}{2x} \\stackrel{L'H}{=} \\lim_{x \\to 0} \\frac{e^x}{2} = \\frac{1}{2}""",
                explanation = "A diferenciação sucessiva eliminou o fator de ordem quadrática do denominador de forma imediata."
            )
        ),
        goldTip = "O erro mais punido pelos professores em provas é o 'L'Hôpital ilegal': usar a regra em frações onde o denominador NÃO tende a zero! Se substituir x e der 3/5, pare e coloque 3/5. L'Hôpital só entra em campo se for 0/0 ou infinito/infinito!"
    )

    // =========================================================================
    // AULA 18: Problemas de Otimização (A Aplicação Suprema)
    // =========================================================================
    private val bundleCalcL18 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Se alguém perguntasse qual é a ferramenta mais valiosa que o Cálculo I entrega à humanidade, a resposta unânime de engenheiros, economistas, cientistas de dados e biólogos seria: **Problemas de Otimização**. No mundo real, os recursos são finitos. Desejamos minimizar o custo de um oleoduto, maximizar a capacidade de carga de uma viga, maximizar o lucro de uma empresa ou minimizar o tempo de deslocamento da luz. O Cálculo transforma esses desafios estratégicos em problemas determinísticos resolvidos pela busca de extremos absolutos.

## 2. Objetivos de Aprendizagem
- Dominar o Método Geral em 6 Passos para Resolução de Problemas de Otimização.
- Modelar equações de vínculo (restrições) para reduzir funções de várias variáveis a uma única variável livre.
- Aplicar o Método do Intervalo Fechado (Teorema de Weierstrass) comparando pontos críticos com os extremos da fronteira.
- Resolver problemas clássicos de geometria (caixa de volume máximo, menor área superficial para volume fixo) e logística de transporte.

## 3. Pré-requisitos
- Teste da Primeira e da Segunda Derivada para determinação de extremos locais (Aula 16).
- Geometria espacial e plana: fórmulas de perímetro, área e volume de figuras elementares.
- Domínio de funções e desigualdades de restrição física (variáveis não-negativas).

## 4. Explicação Teórica Aprofundada
### O Protocolo Estruturado em 6 Passos
1. **Compreensão e Desenho:** Leia o problema, faça um esboço geométrico claro e atribua variáveis a todas as grandezas envolvidas.
2. **Equação Objetivo:** Identifique a grandeza a ser maximizada ou minimizada (ex: Área \( A \), Custo \( C \), Volume \( V \)) e escreva a sua fórmula.
3. **Equação de Vínculo (Restrição):** Identifique a limitação física do problema (ex: comprimento fixo de cerca, volume estipulado, orçamento).
4. **Redução a Uma Única Variável:** Use a restrição para isolar uma variável secundária e substitua-a na equação objetivo, obtendo uma função de uma única variável: \( f(x) \).
5. **Determinação do Domínio Físico:** Identifique o intervalo admissível da variável (ex: \( x \in [0, L] \)), que deve respeitar as leis da física (comprimentos positivos).
6. **Busca e Confirmação dos Extremos:**
   - Se o domínio for um **intervalo fechado \( [a, b] \)**: Pelo **Teorema do Valor Extremo de Weierstrass**, a função contínua atinge garantidamente máximo e mínimo absolutos. Avalie \( f(x) \) nos pontos críticos internos e nos extremos \( a \) e \( b \); o maior valor é o máximo absoluto e o menor é o mínimo absoluto.
   - Se o domínio for um **intervalo aberto \( (0, \infty) \)**: Use o Teste da Primeira ou Segunda Derivada para comprovar que o ponto crítico é um extremo global.

## 5. Definições e Conceitos
- **Função Objetivo:** A expressão matemática da grandeza que se almeja otimizar.
- **Restrição de Vínculo:** A condição de contorno que acopla as dimensões do sistema.
- **Extremo Absoluto (ou Global):** O maior (ou menor) valor assumido pela função em todo o domínio considerado.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Cercamento de Área Máxima
Um fazendeiro possui 100 metros de cerca de arame e deseja cercar um campo retangular adjacente a um rio retilíneo, não necessitando de cerca ao longo da margem do rio. Quais dimensões maximizam a área do campo cercado?
- **Passo 1 (Variáveis e Esboço):**
  Sejam \( x \) o comprimento dos dois lados perpendiculares ao rio e \( y \) o comprimento do lado paralelo ao rio.
- **Passo 2 (Função Objetivo):**
  Queremos maximizar a área:
  \[ A = x \cdot y \]
- **Passo 3 (Restrição):**
  O comprimento total de cerca disponível é 100 metros:
  \[ 2x + y = 100 \]
- **Passo 4 (Redução a uma variável):**
  Isolamos \( y \): \( y = 100 - 2x \).
  Substituindo na área:
  \[ A(x) = x(100 - 2x) = 100x - 2x^2 \]
- **Passo 5 (Domínio Físico):**
  Como os comprimentos devem ser positivos: \( x \ge 0 \) e \( y \ge 0 \implies 100 - 2x \ge 0 \implies x \le 50 \).
  O domínio é o intervalo fechado \( [0, 50] \).
- **Passo 6 (Derivada e Ponto Crítico):**
  \[ A'(x) = 100 - 4x \]
  Fazendo \( A'(x) = 0 \implies 100 - 4x = 0 \implies x = 25 \text{ metros} \).
  Avaliando a área nos extremos e no ponto crítico:
  - \( A(0) = 0 \)
  - \( A(50) = 0 \)
  - \( A(25) = 100(25) - 2(25)^2 = 2500 - 1250 = 1250 \text{ m}^2 \)
- **Conclusão:** A área máxima é \( 1250 \text{ m}^2 \), obtida com lados perpendiculares de \( x = 25 \text{ m} \) e lado paralelo de \( y = 100 - 2(25) = 50 \text{ m} \).

### Exemplo 2: A Lata Cilíndrica Econômica
Deseja-se fabricar uma lata cilíndrica fechada com volume fixo de \( 1000 \text{ cm}^3 \) (1 litro) utilizando a menor quantidade possível de folha de metal (minimizando a área superficial total). Determine a relação ideal entre a altura \( h \) e o raio \( r \).
- **Passo 1 (Objetivo):**
  Área total = 2 tampas circulares + superfície lateral:
  \[ A = 2\pi r^2 + 2\pi r h \]
- **Passo 2 (Restrição de Volume):**
  \[ V = \pi r^2 h = 1000 \implies h = \frac{1000}{\pi r^2} \]
- **Passo 3 (Substituição):**
  \[ A(r) = 2\pi r^2 + 2\pi r \left(\frac{1000}{\pi r^2}\right) = 2\pi r^2 + \frac{2000}{r} \quad (r > 0) \]
- **Passo 4 (Derivada para mínimo):**
  \[ A'(r) = 4\pi r - \frac{2000}{r^2} = 0 \implies 4\pi r = \frac{2000}{r^2} \implies r^3 = \frac{2000}{4\pi} = \frac{500}{\pi} \]
  \[ r = \sqrt[3]{\frac{500}{\pi}} \approx 5,42 \text{ cm} \]
- **Passo 5 (Relação entre altura e raio):**
  \[ h = \frac{1000}{\pi r^2} = \frac{2 \left(\frac{500}{\pi}\right)}{r^2} = \frac{2 r^3}{r^2} = 2r \]
- **Conclusão Teórica Espetacular:** Para minimizar o material de qualquer lata cilíndrica fechada, a altura deve ser exatamente igual ao diâmetro (\( h = 2r \))!

## 7. Aplicações Práticas e Modelagem
- **Design de Latas e Embalagens Industriais:** Empresas de bebidas reduzem milhões de dólares em custos de alumínio ajustando a razão dimensional \( h/2r \) de suas latas para o valor ótimo.
- **Princípio de Fermat do Menor Tempo em Óptica:** A Lei de Snell da refração luminosa \( \frac{\sin \theta_1}{v_1} = \frac{\sin \theta_2}{v_2} \) é deduzida diretamente minimizando a função de tempo de trânsito da luz entre dois meios.

## 8. Exercícios de Fixação
1. Dois números positivos somam 20. Determine-os para que o produto entre eles seja máximo.
2. Deseja-se construir uma caixa aberta a partir de uma folha de papelão quadrada de 12 cm de lado, cortando quadrados iguais de lado \( x \) nos quatro cantos e dobrando as abas. Calcule o valor de \( x \) que maximiza o volume da caixa.
3. Encontre as dimensões do retângulo de maior área que pode ser inscrito em uma circunferência de raio \( R \).

## 9. Correção Comentada dos Exercícios
1. \( x + y = 20 \implies y = 20 - x \). Produto \( P(x) = x(20 - x) = 20x - x^2 \). \( P'(x) = 20 - 2x = 0 \implies x = 10 \). Logo \( y = 10 \). O produto máximo é 100 (números iguais geram o maior produto).
2. As dimensões da base são \( (12 - 2x) \times (12 - 2x) \) e a altura é \( x \). Volume: \( V(x) = x(12 - 2x)^2 = x(144 - 48x + 4x^2) = 4x^3 - 48x^2 + 144x \).
   Derivada: \( V'(x) = 12x^2 - 96x + 144 = 12(x^2 - 8x + 12) = 12(x - 2)(x - 6) = 0 \).
   Como o domínio físico é \( x \in [0, 6] \), o valor \( x = 6 \) anula a caixa. O ponto de máximo é \( x = 2 \text{ cm} \). O volume máximo é \( 2(8)^2 = 128 \text{ cm}^3 \).
3. Seja o retângulo de lados \( 2x \) e \( 2y \). Pelo Teorema de Pitágoras no triângulo com o raio: \( x^2 + y^2 = R^2 \). A área é \( A = 4xy \). Elevando ao quadrado para simplificar a derivada: \( S = A^2 = 16 x^2 (R^2 - x^2) = 16 R^2 x^2 - 16 x^4 \). Derivando em \( x^2 \): \( 16 R^2 - 32 x^2 = 0 \implies x^2 = R^2/2 \implies x = R/\sqrt{2} \). Analogamente, \( y = R/\sqrt{2} \). Como \( x = y \), o retângulo ótimo é obrigatoriamente um quadrado!

## 10. Desafio de Domínio
Um salva-vidas na praia avista um banhista se afogando a uma distância diagonal no mar. Sabendo que a velocidade de corrida na areia é o dobro da velocidade de nado na água, formule o problema de otimização do trajeto pelo Princípio de Fermat e determine o ponto ótimo de entrada na água.

## 11. Resumo e Pontos-Chave
- Problemas de otimização exigem: Função Objetivo + Vínculo = Função de 1 variável.
- Em intervalos fechados, o Teorema de Weierstrass garante que o ótimo está nos pontos críticos ou nas bordas.
- Otimização é a principal ferramenta de conexão do Cálculo com as decisões práticas de engenharia e economia.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 4.7: "Problemas de Otimização".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 9: Seção 9.5: "Problemas de Máximos e Mínimos".
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seção 4.7: "Applied Optimization Problems".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação de Otimização Univariada",
                syntax = """\\frac{d}{dx}[f(x)] = 0 \\implies x^* \\text{ (ponto crítico ótimo)}""",
                description = "Busca dos pontos estacionários da grandeza após substituição do vínculo.",
                parametersInfo = "Verifique a segunda derivada f''(x*) para confirmar máximo ou mínimo."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema de Weierstrass de Extremos Absolutos",
                syntax = """f \\text{ contínua em } [a, b] \\implies \\exists x_\\text{mín}, x_\\text{máx} \\in [a, b]""",
                description = "Garante a existência de extremos globais em conjuntos compactos.",
                parametersInfo = "Compare os valores f(c) nos pontos críticos com f(a) e f(b)."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Maximização de Área Cercada",
                codeOrFormula = """A(x) = x(100 - 2x) = 100x - 2x^2 \\implies A'(x) = 100 - 4x = 0 \\implies x = 25 \\text{ m}, \\; y = 50 \\text{ m}""",
                explanation = "A restrição 2x + y = 100 reduziu a área a uma parábola com vértice no ponto ótimo x=25m."
            )
        ),
        goldTip = "O segredo de ouro de problemas de otimização em provas: nunca tente derivar antes de ter uma única variável! Use a restrição dada no enunciado para eliminar a segunda variável; a partir daí, derivar e igualar a zero é puro reflexo mecânico!"
    )
}

