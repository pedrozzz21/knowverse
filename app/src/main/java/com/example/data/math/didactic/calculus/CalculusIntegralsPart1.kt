package com.example.data.math.didactic.calculus

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Cálculo I — Módulo 3: Integrais e o Teorema Fundamental (Parte 1: Aulas 19 a 22).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em James Stewart, Guidorizzi e OpenStax Calculus Vol 1.
 */
object CalculusIntegralsPart1 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "calc_l19" -> bundleCalcL19
            "calc_l20" -> bundleCalcL20
            "calc_l21" -> bundleCalcL21
            "calc_l22" -> bundleCalcL22
            else -> null
        }
    }

    // =========================================================================
    // AULA 19: O que significa 'Integrar'? (Primitivas e Antiderivadas)
    // =========================================================================
    private val bundleCalcL19 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Se a diferenciação decompõe um movimento contínuo em suas taxas de variação microscópicas e instantâneas, o processo inverso — a **Integração** — reúne esses fragmentos infinitesimais para reconstruir o todo acumulado. Como recuperar a distância percorrida por um veículo se conhecemos apenas o gráfico de seu velocímetro ao longo do tempo? Como calcular o trabalho exercido por uma força variável ou encontrar a área sob uma parábola? Integrar é o ato de acumular e reverter a diferenciação, introduzindo o conceito de **Primitiva (ou Antiderivada)**.

## 2. Objetivos de Aprendizagem
- Definir analiticamente uma primitiva (antiderivada) de uma função contínua.
- Compreender a necessidade e o significado geométrico da constante arbitrária de integração \( C \).
- Distinguir uma antiderivada específica de uma família de curvas integrais paralelas.
- Resolver problemas de valor inicial (condições de contorno) para determinar constantes particulares.

## 3. Pré-requisitos
- Todas as regras fundamentais de derivação (Aulas 11 a 13).
- Conceito de família de curvas no plano cartesiano.
- Noções intuitivas de velocidade, aceleração e posição em física.

## 4. Explicação Teórica Aprofundada
### O Conceito de Antiderivada (Primitiva)
Uma função \( F \) é chamada de **primitiva (ou antiderivada)** de uma função \( f \) em um intervalo \( I \) se:
\[ F'(x) = f(x) \quad \text{para todo } x \in I \]
Por exemplo, se \( f(x) = 2x \), sabemos da derivação que \( (x^2)' = 2x \). Portanto, \( F(x) = x^2 \) é uma primitiva de \( 2x \).
No entanto, observe que:
\[ (x^2 + 5)' = 2x, \qquad (x^2 - 100)' = 2x, \qquad (x^2 + \pi)' = 2x \]
Como a derivada de qualquer constante real é zero (\( \frac{d}{dx}[C] = 0 \)), se \( F(x) \) é uma primitiva de \( f(x) \), então qualquer função da forma:
\[ G(x) = F(x) + C \]
também é uma primitiva de \( f(x) \).

### O Teorema da Unicidade a Menos de Constante
Pelo Teorema do Valor Médio, se duas funções possuem a mesma derivada em um intervalo, elas diferem no máximo por uma constante aditiva. Portanto, a expressão:
\[ \int f(x) \, dx = F(x) + C \]
é denominada a **integral indefinida** de \( f(x) \), representando a **família completa de todas as primitivas possíveis** de \( f \).
Geometricamente, a constante \( C \) corresponde a um deslocamento vertical rígido do gráfico: são infinitas cópias da mesma curva deslizadas verticalmente ao longo do eixo \( y \), todas compartilhando exatamente a mesma inclinação de reta tangente para qualquer valor de \( x \).

### Problemas de Valor Inicial (PVI)
Em física e engenharia, raramente desejamos a família inteira com \( C \) indeterminado; precisamos da trajetória real específica percorrida pelo corpo. Para determinar o valor numérico exato de \( C \), o problema deve fornecer uma **condição inicial**: um ponto conhecido por onde a curva passa, como \( y(x_0) = y_0 \).

## 5. Definições e Conceitos
- **Integral Indefinida:** O operador \( \int \dots dx \) que denota a busca pela família de antiderivadas.
- **Integrando:** A função \( f(x) \) que se encontra dentro do símbolo de integral.
- **Variável de Integração:** A grandeza indicada pelo diferencial \( dx \).
- **Constante de Integração \( C \):** Parâmetro arbitrário que parametriza a família vertical de curvas.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Determinação de Família de Primitivas
Encontre a integral indefinida \( \int (3x^2 + 4x - 5) \, dx \).
- **Passo 1 (Pensar de trás para frente):**
  - Qual função tem como derivada \( 3x^2 \)? Sabemos que \( (x^3)' = 3x^2 \).
  - Qual função tem como derivada \( 4x \)? Sabemos que \( (2x^2)' = 4x \).
  - Qual função tem como derivada \( -5 \)? Sabemos que \( (-5x)' = -5 \).
- **Passo 2 (Adicionar a constante obrigatória):**
  \[ \int (3x^2 + 4x - 5) \, dx = x^3 + 2x^2 - 5x + C \]
- **Passo 3 (Verificação por derivação):**
  \[ \frac{d}{dx}[x^3 + 2x^2 - 5x + C] = 3x^2 + 4x - 5 + 0 = 3x^2 + 4x - 5 \]
  A resposta está 100% correta.

### Exemplo 2: Resolução de PVI em Física
Uma partícula parte do repouso na posição \( s(0) = 10 \text{ metros} \) com velocidade dada por \( v(t) = 6t^2 - 4t \). Determine a função horária da posição \( s(t) \).
- **Passo 1 (Relação diferencial):**
  A velocidade é a derivada da posição: \( \frac{ds}{dt} = v(t) \implies s(t) = \int v(t) \, dt \).
- **Passo 2 (Cálculo da integral indefinida):**
  \[ s(t) = \int (6t^2 - 4t) \, dt = 2t^3 - 2t^2 + C \]
- **Passo 3 (Aplicação da condição inicial \( s(0) = 10 \)):**
  \[ s(0) = 2(0)^3 - 2(0)^2 + C = 10 \implies C = 10 \]
- **Passo 4 (Função horária definitiva):**
  \[ s(t) = 2t^3 - 2t^2 + 10 \text{ metros} \]

## 7. Aplicações Práticas e Modelagem
- **Balística e Trajetória de Projéteis:** A partir da aceleração gravitacional constante \( a(t) = -g \), integra-se sucessivamente duas vezes para deduzir a velocidade \( v(t) = -gt + v_0 \) e a posição vertical clássica \( y(t) = y_0 + v_0 t - \frac{1}{2}gt^2 \).
- **Carga Acumulada em Baterias:** A carga total fornecida a uma bateria de lítio é obtida integrando a corrente elétrica variável no tempo: \( Q = \int i(t) \, dt \).

## 8. Exercícios de Fixação
1. Determine a integral indefinida \( \int (4x^3 - 6x) \, dx \).
2. Se \( f'(x) = \cos x \) e \( f(0) = 3 \), encontre a função \( f(x) \).
3. Qual é a primitiva de \( f(x) = e^x \) que passa pelo ponto \( (0, 5) \)?

## 9. Correção Comentada dos Exercícios
1. Antiderivada de \( 4x^3 \) é \( x^4 \); antiderivada de \( -6x \) é \( -3x^2 \). Adicionando a constante: \( x^4 - 3x^2 + C \).
2. Primitiva de \( \cos x \) é \( \sin x + C \). Condição \( f(0) = 3 \implies \sin(0) + C = 3 \implies 0 + C = 3 \implies C = 3 \). Logo, \( f(x) = \sin x + 3 \).
3. Primitiva geral: \( e^x + C \). Condição: \( e^0 + C = 5 \implies 1 + C = 5 \implies C = 4 \). Resposta: \( f(x) = e^x + 4 \).

## 10. Desafio de Domínio
Dada uma partícula sob aceleração constante \( a \), deduza a equação de Torricelli \( v^2 = v_0^2 + 2a \Delta s \) utilizando a integração da relação de derivada com a regra da cadeia \( a = v \frac{dv}{ds} \).

## 11. Resumo e Pontos-Chave
- Integrar é o processo inverso de diferenciar: \( \int f(x)dx = F(x) + C \iff F'(x) = f(x) \).
- O termo \( + C \) é indispensável na integral indefinida para representar todas as soluções possíveis.
- Condições iniciais fixam o valor de \( C \), selecionando uma curva única da família.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 4.9: "Antiderivadas".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 10: "A Integral Indefinida".
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seção 4.10: "Antiderivatives".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Definição de Integral Indefinida",
                syntax = """\\int f(x) \\, dx = F(x) + C \\iff F'(x) = f(x)""",
                description = "Operador de antiderivação para funções contínuas.",
                parametersInfo = "C é a constante arbitrária real de integração."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Problema de Valor Inicial (PVI)",
                syntax = """F(x_0) + C = y_0 \\implies C = y_0 - F(x_0)""",
                description = "Determinação da constante particular a partir de um ponto prescrito.",
                parametersInfo = "Garante solução única para a equação diferencial dy/dx = f(x)."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cálculo de Primitiva com Condição de Contorno",
                codeOrFormula = """\\int (6t - 4) dt = 3t^2 - 4t + C; \\quad s(0) = 10 \\implies C = 10 \\implies s(t) = 3t^2 - 4t + 10""",
                explanation = "A integração reconstruiu a posição a partir da velocidade e fixou o valor da constante com os dados iniciais."
            )
        ),
        goldTip = "O erro clássico número 1 em provas de Cálculo é esquecer de escrever o '+ C' na integral indefinida! O professor corta nota sem hesitar. Lembre-se: sem limites de integração, o '+ C' é obrigatório!"
    )

    // =========================================================================
    // AULA 20: A Mecânica Básica: Regra da Potência
    // =========================================================================
    private val bundleCalcL20 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Assim como na diferenciação deduzimos a Regra do Tombo para não precisar recorrer a limites a cada polinômio, na integração desenvolvemos a **Regra da Potência Inversa**. Enquanto a derivada reduz uma unidade do expoente e multiplica pelo valor antigo, a integral deve realizar a operação rigorosamente oposta: adicionar uma unidade ao expoente e dividir pelo novo valor. Dominar essa mecânica é o primeiro grande passo para a fluência no cálculo integral.

## 2. Objetivos de Aprendizagem
- Enunciar e aplicar a Regra da Potência para integração: \( \int x^n dx = \frac{x^{n+1}}{n+1} + C \).
- Compreender por que a regra falha catastroficamente para \( n = -1 \) (o caso especial do logaritmo).
- Integrar potências fracionárias (radicais) e potências negativas (frações algébricas).
- Integrar constantes e utilizar a propriedade multiplicativa por escalar.

## 3. Pré-requisitos
- Regra da potência para derivadas: \( (x^k)' = k x^{k-1} \) (Aula 12).
- Conceito de primitiva e constante de integração (Aula 19).
- Propriedades de frações e potenciação: \( \frac{a}{b/c} = \frac{a \cdot c}{b} \).

## 4. Explicação Teórica Aprofundada
### A Dedução da Regra da Potência
Queremos encontrar uma função cuja derivada seja \( x^n \).
Sabemos que, ao derivar \( x^{n+1} \), o expoente desce multiplicando:
\[ \frac{d}{dx}[x^{n+1}] = (n + 1) x^n \]
Dividindo ambos os membros pela constante \( (n + 1) \) (desde que \( n + 1 \neq 0 \)):
\[ \frac{d}{dx}\left[\frac{x^{n+1}}{n+1}\right] = x^n \]
Integrando ambos os lados em relação a \( x \), obtemos a fórmula canônica:
\[ \int x^n \, dx = \frac{x^{n+1}}{n+1} + C \quad (n \neq -1) \]

### Por que \( n \neq -1 \)?
Se tentássemos aplicar a fórmula cegamente para \( n = -1 \) (\( f(x) = \frac{1}{x} \)):
\[ \int x^{-1} \, dx = \frac{x^{-1+1}}{-1+1} + C = \frac{x^0}{0} + C = \frac{1}{0} \quad \text{(Divisão impossível!)} \]
O caso \( n = -1 \) corresponde à função recíproca \( \frac{1}{x} \), cuja primitiva é o logaritmo natural \( \ln|x| + C \), estudado na Aula 21.

### A Mecânica para Radicais e Expoentes Negativos
- **Para expoentes negativos:** \( \int \frac{1}{x^3} \, dx = \int x^{-3} \, dx = \frac{x^{-3+1}}{-3+1} + C = \frac{x^{-2}}{-2} + C = -\frac{1}{2x^2} + C \).
- **Para radicais fracionários:** \( \int \sqrt{x} \, dx = \int x^{1/2} \, dx = \frac{x^{1/2 + 1}}{\frac{1}{2} + 1} + C = \frac{x^{3/2}}{\frac{3}{2}} + C = \frac{2}{3} \sqrt{x^3} + C \).
*Dica operacional para frações:* Em vez de dividir por \( \frac{p}{q} \), multiplique diretamente pelo recíproco \( \frac{q}{p} \)!

## 5. Definições e Conceitos
- **Regra da Potência de Integração:** O algoritmo inverso da derivação: "Soma 1 no expoente e divide pelo novo expoente".
- **Integral de uma Constante:** \( \int k \, dx = kx + C \) (pois a constante pode ser vista como \( k x^0 \)).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Polinômio Geral
Calcule \( \int (5x^4 - 8x^3 + 6x - 7) \, dx \).
- **Passo 1 (Integrar termo a termo):**
  - Para \( 5x^4 \): \( 5 \cdot \frac{x^5}{5} = x^5 \).
  - Para \( -8x^3 \): \( -8 \cdot \frac{x^4}{4} = -2x^4 \).
  - Para \( 6x \) (onde expoente é 1): \( 6 \cdot \frac{x^2}{2} = 3x^2 \).
  - Para \( -7 \): \( -7x \).
- **Passo 2 (Adicionar constante):**
  \[ \int (5x^4 - 8x^3 + 6x - 7) \, dx = x^5 - 2x^4 + 3x^2 - 7x + C \]

### Exemplo 2: Raiz Cúbica e Fração Algébrica
Calcule \( \int \left(\sqrt[3]{x} + \frac{2}{x^4}\right) \, dx \).
- **Passo 1 (Reescrever tudo como potências puras):**
  \( \sqrt[3]{x} = x^{1/3} \) e \( \frac{2}{x^4} = 2x^{-4} \).
- **Passo 2 (Aplicar a regra):**
  - \( \int x^{1/3} \, dx = \frac{x^{1/3 + 1}}{\frac{4}{3}} = \frac{3}{4} x^{4/3} = \frac{3}{4} \sqrt[3]{x^4} \).
  - \( \int 2x^{-4} \, dx = 2 \cdot \frac{x^{-4+1}}{-3} = -\frac{2}{3} x^{-3} = -\frac{2}{3x^3} \).
- **Passo 3 (Expressão final):**
  \[ \frac{3}{4} x \sqrt[3]{x} - \frac{2}{3x^3} + C \]

## 7. Aplicações Práticas e Modelagem
- **Momento de Inércia de Barras Estruturais:** Em engenharia civil, o momento de inércia de vigas com densidade linear variável \( \lambda(x) = kx^n \) é calculado integrando potências de \( x \).
- **Trabalho em Molas Não-Lineares:** Se a força elástica varia com potência \( F(x) = k x^3 \), o trabalho acumulado é obtido pela integral da potência \( W = \int k x^3 dx = \frac{1}{4} k x^4 \).

## 8. Exercícios de Fixação
1. Calcule \( \int (9x^2 - 4x + 1) \, dx \).
2. Determine \( \int \frac{1}{\sqrt{x}} \, dx \).
3. Calcule \( \int \frac{x^3 + 5x}{x} \, dx \).

## 9. Correção Comentada dos Exercícios
1. \( 9 \frac{x^3}{3} - 4 \frac{x^2}{2} + x + C = 3x^3 - 2x^2 + x + C \).
2. Reescrevendo: \( x^{-1/2} \). Integral: \( \frac{x^{-1/2 + 1}}{-1/2 + 1} = \frac{x^{1/2}}{1/2} = 2\sqrt{x} + C \).
3. Simplificando a fração algébrica antes de integrar: \( \frac{x^3 + 5x}{x} = x^2 + 5 \). Integrando: \( \frac{x^3}{3} + 5x + C \).

## 10. Desafio de Domínio
Calcule \( \int \frac{(x + 1)^3}{\sqrt{x}} \, dx \) expandindo o binômio e dividindo termo a termo antes de aplicar a regra da potência.

## 11. Resumo e Pontos-Chave
- Fórmula: \( \int x^n dx = \frac{x^{n+1}}{n+1} + C \) para todo \( n \neq -1 \).
- Sempre converta radicais e denominadores para a forma \( x^{m/n} \) e \( x^{-p} \).
- Simplifique produtos e divisões antes de tentar integrar.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 5.4: "Integrais Indefinidas e o Teorema da Variação Total".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 10.
- STRANG, Gilbert; HERMAN, Edwin. *Calculus Volume 1*. OpenStax, 2016. Seção 5.3: "Indefinite Integrals".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Regra da Potência de Integração",
                syntax = """\\int x^n \\, dx = \\frac{x^{n+1}}{n+1} + C \\quad (n \\neq -1)""",
                description = "Fórmula direta para potências reais.",
                parametersInfo = "Se n = -1, o resultado é ln|x| + C."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Integral de Constante",
                syntax = """\\int k \\, dx = kx + C""",
                description = "Reconstituição da função afim a partir de uma taxa constante.",
                parametersInfo = "k é uma constante real."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Integração de Radical Fracionário",
                codeOrFormula = """\\int \\sqrt{x} \\, dx = \\int x^{1/2} dx = \\frac{x^{3/2}}{3/2} + C = \\frac{2}{3}x\\sqrt{x} + C""",
                explanation = "Somou-se 1 ao expoente 1/2 resultando em 3/2 e inverteu-se a fração multiplicando por 2/3."
            )
        ),
        goldTip = "Ao integrar potências fracionárias x^(a/b), o novo expoente é simplesmente (a+b)/b. Para achar o coeficiente na frente, basta inverter essa fração para b/(a+b). Economize 3 linhas de conta em cada exercício!"
    )

    // =========================================================================
    // AULA 21: Exponenciais, Logaritmos e Funções Trigonométricas
    // =========================================================================
    private val bundleCalcL21 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Agora que dominamos a álgebra das funções polinomiais e potências, avançamos para o coração das **funções transcendentais**: exponenciais que regem decaimentos radioativos, logaritmos que modelam escalas de pH e terremotos, e funções trigonométricas que descrevem ondas sonoras e eletromagnéticas. A integração dessas funções é direta uma vez compreendida a inversão das regras de diferenciação deduzidas no Módulo 2.

## 2. Objetivos de Aprendizagem
- Resolver a integral da função recíproca \( \int \frac{1}{x} dx = \ln|x| + C \), compreendendo o papel crucial do valor absoluto.
- Integrar a função exponencial natural \( \int e^x dx = e^x + C \) e de base arbitrária \( \int a^x dx = \frac{a^x}{\ln a} + C \).
- Memorizar e aplicar as integrais imediatas das funções circulares: seno, cosseno, secante ao quadrado e secante vezes tangente.
- Resolver integrais que recaem em funções trigonométricas inversas (\( \arcsin x \) e \( \arctan x \)).

## 3. Pré-requisitos
- Derivadas das funções exponencial, logarítmica e trigonométricas (Aula 12).
- Derivada da tangente e secante (Aula 13).
- Propriedades de logaritmos reais.

## 4. Explicação Teórica Aprofundada
### O Resgate de \( n = -1 \): O Logaritmo Natural
Vimos na aula anterior que a regra da potência falha para \( \int \frac{1}{x} dx \).
Lembrando que \( \frac{d}{dx}[\ln x] = \frac{1}{x} \) para \( x > 0 \).
E para \( x < 0 \)? Se \( x < 0 \), temos \( -x > 0 \). Pela regra da cadeia:
\[ \frac{d}{dx}[\ln(-x)] = \frac{1}{-x} \cdot (-1) = \frac{1}{x} \]
Unificando ambos os casos em uma única fórmula válida para qualquer \( x \neq 0 \):
\[ \int \frac{1}{x} \, dx = \ln|x| + C \]
O módulo é mandatório, pois o domínio da função integrada é \( \mathbb{R} \setminus \{0\} \), enquanto o logaritmo só aceita argumentos estritamente positivos.

### Integrais Exponenciais
A função exponencial é sua própria primitiva:
\[ \int e^x \, dx = e^x + C \]
Para uma base arbitrária \( a > 0, a \neq 1 \), como \( \frac{d}{dx}[a^x] = a^x \ln a \), dividimos pela constante \( \ln a \):
\[ \int a^x \, dx = \frac{a^x}{\ln a} + C \]

### Tabela Fundamental das Primitivas Trigonométricas
Como a integral desfaz a derivada:
1. \( \int \cos x \, dx = \sin x + C \) *(pois \( (\sin x)' = \cos x \))*
2. \( \int \sin x \, dx = -\cos x + C \) *(atenção ao sinal negativo, pois \( (-\cos x)' = \sin x \))*
3. \( \int \sec^2 x \, dx = \tan x + C \) *(pois \( (\tan x)' = \sec^2 x \))*
4. \( \int \sec x \tan x \, dx = \sec x + C \) *(pois \( (\sec x)' = \sec x \tan x \))*
5. \( \int \csc^2 x \, dx = -\cot x + C \)
6. \( \int \csc x \cot x \, dx = -\csc x + C \)

### Primitivas que Geram Funções Trigonométricas Inversas
Frequentemente integrais de expressões algébricas puras resultam em funções transcendentes:
\[ \int \frac{1}{\sqrt{1 - x^2}} \, dx = \arcsin(x) + C \]
\[ \int \frac{1}{1 + x^2} \, dx = \arctan(x) + C \]

## 5. Definições e Conceitos
- **Módulo no Logaritmo:** Proteção analítica para garantir que a integral cubra tanto a semi-reta positiva quanto a negativa.
- **Família Transcendental:** Conjunto de primitivas fundamentais que não podem ser expressas por operações algébricas finitas de soma e potência.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Combinação Exponencial e Trigonométrica
Calcule \( \int (4 e^x + 3\sin x - 2\cos x) \, dx \).
- **Passo 1:** Pela linearidade, integramos termo a termo:
  - \( \int 4 e^x dx = 4 e^x \).
  - \( \int 3 \sin x dx = 3 (-\cos x) = -3 \cos x \).
  - \( \int -2 \cos x dx = -2 (\sin x) = -2 \sin x \).
- **Passo 2:** Unindo as parcelas com a constante:
  \[ 4e^x - 3\cos x - 2\sin x + C \]

### Exemplo 2: Fração com Separação de Termos
Calcule \( \int \frac{3x^2 + 5}{x} \, dx \).
- **Passo 1 (Dividir a fração em duas):**
  \[ \frac{3x^2 + 5}{x} = \frac{3x^2}{x} + \frac{5}{x} = 3x + 5 \cdot \frac{1}{x} \]
- **Passo 2 (Integrar cada parcela):**
  - \( \int 3x \, dx = \frac{3}{2} x^2 \).
  - \( \int 5 \cdot \frac{1}{x} \, dx = 5 \ln|x| \).
- **Passo 3 (Resultado):**
  \[ \frac{3}{2} x^2 + 5 \ln|x| + C \]

## 7. Aplicações Práticas e Modelagem
- **Circuito RC em Eletrônica:** A descarga de um capacitor através de um resistor segue a equação diferencial \( \frac{dq}{dt} = -\frac{q}{RC} \). Ao separar variáveis e integrar \( \int \frac{dq}{q} \), obtém-se a curva clássica de decaimento logarítmico \( \ln q = -\frac{t}{RC} + C \implies q(t) = Q_0 e^{-t/RC} \).
- **Acústica e Música:** A energia média de ondas harmônicas em cordas vibrantes é calculada pela integral de funções seno e cosseno ao quadrado.

## 8. Exercícios de Fixação
1. Calcule \( \int (2^x + 5\sec^2 x) \, dx \).
2. Determine \( \int \left(e^x + \frac{3}{1 + x^2}\right) \, dx \).
3. Calcule \( \int \left(\frac{4}{x} - 2\sin x\right) \, dx \).

## 9. Correção Comentada dos Exercícios
1. \( \int 2^x dx = \frac{2^x}{\ln 2} \). \( \int 5\sec^2 x dx = 5\tan x \). Resposta: \( \frac{2^x}{\ln 2} + 5\tan x + C \).
2. Primitiva de \( e^x \) é \( e^x \); primitiva de \( \frac{1}{1+x^2} \) é \( \arctan x \). Resposta: \( e^x + 3\arctan x + C \).
3. \( \int \frac{4}{x} dx = 4\ln|x| \). \( \int -2\sin x dx = -2(-\cos x) = +2\cos x \). Resposta: \( 4\ln|x| + 2\cos x + C \).

## 10. Desafio de Domínio
Utilize identidades trigonométricas de arco duplo (\( \cos(2x) = 1 - 2\sin^2 x \)) para calcular a integral de \( \int \sin^2 x \, dx \).

## 11. Resumo e Pontos-Chave
- \( \int \frac{1}{x} dx = \ln|x| + C \) (sempre com módulo).
- \( \int e^x dx = e^x + C \).
- Cuidado com o sinal na integral do seno: \( \int \sin x dx = -\cos x + C \).
- \( \int \frac{1}{1+x^2} dx = \arctan x + C \).

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 5.4.
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 10.
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seção 5.3.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Integral da Função Recíproca",
                syntax = """\\int \\frac{1}{x} \\, dx = \\ln|x| + C""",
                description = "Antiderivada fundamental que gera o logaritmo natural.",
                parametersInfo = "O módulo |x| garante validade em todo o domínio não-nulo."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Integrais Trigonométricas Canônicas",
                syntax = """\\int \\cos x \\, dx = \\sin x + C \\qquad \\int \\sin x \\, dx = -\\cos x + C""",
                description = "Primitivas das funções circulares básicas.",
                parametersInfo = "Atenção ao sinal na integral do seno."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Primitiva do Arco-Tangente",
                syntax = """\\int \\frac{1}{1 + x^2} \\, dx = \\arctan(x) + C""",
                description = "Integral algébrica que gera função transcendental inversa.",
                parametersInfo = "Base para integração de frações parciais irredutíveis."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Integral de Fração Decomposta",
                codeOrFormula = """\\int \\frac{3x^2 + 5}{x} dx = \\int (3x + 5/x) dx = \\frac{3}{2}x^2 + 5\\ln|x| + C""",
                explanation = "Separou-se a fração em duas parcelas independentes antes de aplicar a regra da potência e do logaritmo."
            )
        ),
        goldTip = "Cuidado com o jogo de sinais invertido! Ao derivar seno vira cosseno e cosseno vira -seno. Na integral é o contrário: integral de cosseno é +seno e integral de seno é -cosseno!"
    )

    // =========================================================================
    // AULA 22: Propriedades da Linearidade na Integral
    // =========================================================================
    private val bundleCalcL22 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Na prática matemática e nas ciências aplicadas, os integrandos raramente surgem como uma única função tabelada isolada; eles aparecem como combinações de forças, sobreposições de sinais elétricos e polinômios extensos. Por que temos a garantia matemática de que podemos integrar cada parcela individualmente e colocar constantes multiplicativas para fora do símbolo de integral? Esta legitimidade decorre da propriedade mais fundamental do cálculo infinitesimal: a **Linearidade do Operador Integral**.

## 2. Objetivos de Aprendizagem
- Enunciar e aplicar o Teorema da Linearidade para integrais indefinidas.
- Manipular expressões algébricas por expansão binomial e fatoração para viabilizar a integração termo a termo.
- Decompor frações racionais complexas em somas de frações simples tratáveis.
- Reconhecer e evitar armadilhas comuns, como tentar distribuir a integral sobre produtos ou divisões.

## 3. Pré-requisitos
- Regra da potência e integrais transcendentais fundamentais (Aulas 20 e 21).
- Produtos notáveis: quadrado da soma \( (a+b)^2 \), cubo da soma e diferença.
- Propriedades operatórias de frações e distributividade.

## 4. Explicação Teórica Aprofundada
### O Teorema da Linearidade da Integral
A integral indefinida é um **operador linear**. Isso significa que ela preserva as duas operações basilares da álgebra linear:
1. **Adição de Funções (Regra da Soma):** A integral da soma é a soma das integrais:
   \[ \int [f(x) + g(x)] \, dx = \int f(x) \, dx + \int g(x) \, dx \]
2. **Multiplicação por Escalar:** Uma constante multiplicativa pode ser "extraída" para fora da integral:
   \[ \int [c \cdot f(x)] \, dx = c \int f(x) \, dx \quad (c \in \mathbb{R}) \]
Combinando ambas em uma única fórmula geral para quaisquer constantes \( \alpha, \beta \):
\[ \int [\alpha f(x) + \beta g(x)] \, dx = \alpha \int f(x) \, dx + \beta \int g(x) \, dx \]

### Demonstração Rigorosa a Partir das Derivadas
Sejam \( F(x) \) e \( G(x) \) primitivas de \( f(x) \) e \( g(x) \), ou seja, \( F'(x) = f(x) \) e \( G'(x) = g(x) \).
Pela linearidade da diferenciação já comprovada no Módulo 2:
\[ \frac{d}{dx}[\alpha F(x) + \beta G(x)] = \alpha F'(x) + \beta G'(x) = \alpha f(x) + \beta g(x) \]
Portanto, por definição de antiderivada, \( \alpha F(x) + \beta G(x) \) é a primitiva da expressão combinada, demonstrando o teorema.

### O Que a Linearidade NÃO Permite (Erros Graves)
A linearidade aplica-se estritamente à **soma e à multiplicação por constantes**. Portanto:
- \( \int [f(x) \cdot g(x)] \, dx \neq \int f(x) \, dx \cdot \int g(x) \, dx \) *(A integral do produto NÃO é o produto das integrais! Para isso existe a Integração por Partes, Aula 24).*
- \( \int \frac{f(x)}{g(x)} \, dx \neq \frac{\int f(x) \, dx}{\int g(x) \, dx} \) *(A integral da divisão NÃO é a divisão das integrais!)*
- **Constantes podem sair, variáveis NUNCA:** \( \int x f(x) dx \neq x \int f(x) dx \). Qualquer termo dependente de \( x \) deve permanecer sob o integrando.

## 5. Definições e Conceitos
- **Operador Linear:** Operador matemático que satisfaz a aditividade e a homogeneidade.
- **Simplificação Prévia:** O processo indispensável de reescrever algebricamente uma expressão complexa na forma de uma soma polinomial antes de executar o cálculo da integral.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Expansão de Produto Notável
Calcule \( \int (2x - 3)^2 \, dx \).
- **Passo 1 (Erro a evitar):** Nunca tente integrar o interior e elevar ao quadrado! A integral de um produto não é o produto.
- **Passo 2 (Expandir o quadrado da diferença):**
  \[ (2x - 3)^2 = (2x)^2 - 2(2x)(3) + 3^2 = 4x^2 - 12x + 9 \]
- **Passo 3 (Integrar termo a termo por linearidade):**
  \[ \int (4x^2 - 12x + 9) \, dx = 4 \int x^2 dx - 12 \int x dx + 9 \int 1 dx \]
  \[ = 4 \left(\frac{x^3}{3}\right) - 12 \left(\frac{x^2}{2}\right) + 9x + C = \frac{4}{3} x^3 - 6x^2 + 9x + C \]

### Exemplo 2: Divisão de Polinômio por Monômio
Calcule \( \int \frac{x^4 - 3x^2 + 5}{\sqrt{x}} \, dx \).
- **Passo 1 (Dividir cada termo do numerador pelo denominador):**
  Como \( \sqrt{x} = x^{1/2} \), dividimos as potências de mesma base subtraindo os expoentes:
  - \( \frac{x^4}{x^{1/2}} = x^{4 - 1/2} = x^{7/2} \).
  - \( \frac{-3x^2}{x^{1/2}} = -3 x^{2 - 1/2} = -3 x^{3/2} \).
  - \( \frac{5}{x^{1/2}} = 5 x^{-1/2} \).
- **Passo 2 (Integrar cada parcela resultante):**
  - \( \int x^{7/2} dx = \frac{x^{9/2}}{9/2} = \frac{2}{9} x^{9/2} = \frac{2}{9} x^4 \sqrt{x} \).
  - \( \int -3 x^{3/2} dx = -3 \frac{x^{5/2}}{5/2} = -3 \cdot \frac{2}{5} x^{5/2} = -\frac{6}{5} x^2 \sqrt{x} \).
  - \( \int 5 x^{-1/2} dx = 5 \frac{x^{1/2}}{1/2} = 5 \cdot 2 x^{1/2} = 10\sqrt{x} \).
- **Passo 3 (Resultado unificado):**
  \[ \frac{2}{9} x^4 \sqrt{x} - \frac{6}{5} x^2 \sqrt{x} + 10\sqrt{x} + C \]

## 7. Aplicações Práticas e Modelagem
- **Princípio da Superposição em Circuitos e Física Linear:** Em sistemas mecânicos acoplados e circuitos RLC lineares com múltiplas fontes independentes de excitação \( v(t) = v_1(t) + v_2(t) \), a resposta total é a soma das integrais de cada fonte separada.
- **Cálculo de Centróides de Massa:** Ao calcular a massa total de uma placa com densidade descrita por várias parcelas de materiais compósitos, a integral da densidade é calculada termo a termo.

## 8. Exercícios de Fixação
1. Calcule \( \int (3\sqrt{x} - 2e^x + \frac{5}{x}) \, dx \).
2. Calcule \( \int (x + 2)(x - 3) \, dx \).
3. Determine \( \int \frac{2x^3 - 4x^2 + 1}{x^2} \, dx \).

## 9. Correção Comentada dos Exercícios
1. \( 3 \frac{x^{3/2}}{3/2} - 2e^x + 5\ln|x| + C = 2x\sqrt{x} - 2e^x + 5\ln|x| + C \).
2. Expandindo o produto: \( x^2 - 3x + 2x - 6 = x^2 - x - 6 \). Integrando: \( \frac{x^3}{3} - \frac{x^2}{2} - 6x + C \).
3. Separando os termos: \( \frac{2x^3}{x^2} - \frac{4x^2}{x^2} + \frac{1}{x^2} = 2x - 4 + x^{-2} \). Integrando: \( x^2 - 4x - \frac{1}{x} + C \).

## 10. Desafio de Domínio
Explique detalhadamente por que \( \int x \cos x \, dx \) NÃO pode ser resolvido como \( \frac{x^2}{2} \sin x + C \), conferindo o resultado por derivação da suposta resposta.

## 11. Resumo e Pontos-Chave
- A integral preserva a soma e a multiplicação por constantes reais: \( \int (\alpha f + \beta g)dx = \alpha \int f dx + \beta \int g dx \).
- Não existe propriedade de linearidade para produto ou divisão de funções.
- Diante de produtos ou quocientes com denominador simples, expanda e divida tudo antes de integrar.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 5.4: "Tabela de Integrais Indefinidas".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 10.
- STRANG, Gilbert; HERMAN, Edwin. *Calculus Volume 1*. OpenStax, 2016. Seção 5.3.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Propriedade da Linearidade da Integral",
                syntax = """\\int [\\alpha f(x) + \\beta g(x)] \\, dx = \\alpha \\int f(x) \\, dx + \\beta \\int g(x) \\, dx""",
                description = "Preservação da soma vetorial e multiplicação escalar.",
                parametersInfo = "α e β são constantes reais independentes de x."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Regra de Ouro da Não-Fatoração",
                syntax = """\\int f(x) g(x) dx \\neq \\left(\\int f dx\\right) \\cdot \\left(\\int g dx\\right)""",
                description = "Alerta capital: produto sob a integral requer métodos avançados.",
                parametersInfo = "Nunca distribua a integral sobre multiplicações de variáveis."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Integração por Expansão de Binômio",
                codeOrFormula = """\\int (2x - 3)^2 dx = \\int (4x^2 - 12x + 9) dx = \\frac{4}{3}x^3 - 6x^2 + 9x + C""",
                explanation = "A expansão prévia do produto notável transformou a expressão em uma soma trivial de potências."
            )
        ),
        goldTip = "Sempre que você vir uma fração onde o denominador é um único monômio (como raiz de x ou x²), distribua imediatamente esse denominador para cada termo do numerador! Isso desmonta o problema em simples somas da Regra da Potência!"
    )
}

