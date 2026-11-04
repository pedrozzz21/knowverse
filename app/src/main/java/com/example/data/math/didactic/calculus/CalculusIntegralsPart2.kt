package com.example.data.math.didactic.calculus

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Cálculo I — Módulo 3: Integrais e o Teorema Fundamental (Parte 2: Aulas 23 a 26).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em James Stewart, Guidorizzi e OpenStax Calculus Vol 1.
 */
object CalculusIntegralsPart2 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "calc_l23" -> bundleCalcL23
            "calc_l24" -> bundleCalcL24
            "calc_l25" -> bundleCalcL25
            "calc_l26" -> bundleCalcL26
            else -> null
        }
    }

    // =========================================================================
    // AULA 23: Técnica de Substituição (Regra da Cadeia ao Contrário)
    // =========================================================================
    private val bundleCalcL23 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como calcular a integral de uma função composta como \( \int 2x \cos(x^2) dx \) ou \( \int x \sqrt{x^2 + 5} dx \)? Nenhuma das regras da potência ou tabelas imediatas se aplica diretamente, pois há um produto de funções. No entanto, observe que o fator \( 2x \) é a derivada exata da função interna \( x^2 \). O método da **Integração por Substituição (ou Mudança de Variável)** é a contrapartida exata da Regra da Cadeia, transformando expressões compostas intimidantes em integrais elementares triviais através de uma nova variável \( u \).

## 2. Objetivos de Aprendizagem
- Identificar quando uma integral apresenta a estrutura da Regra da Cadeia: \( \int f(g(x)) g'(x) dx \).
- Escolher a substituição adequada \( u = g(x) \) e computar o diferencial \( du = g'(x) dx \).
- Ajustar coeficientes numéricos multiplicando e dividindo por constantes de balanceamento.
- Reverter a substituição ao final para expressar a primitiva na variável original \( x \).

## 3. Pré-requisitos
- Regra da Cadeia para diferenciação (Aula 13).
- Diferenciais \( dy = f'(x) dx \) (Aula 15).
- Tabela de integrais imediatas elementares (Aulas 20 e 21).

## 4. Explicação Teórica Aprofundada
### O Princípio da Substituição
Pela Regra da Cadeia, se \( F \) é uma primitiva de \( f \) (ou seja, \( F' = f \)), a derivada da função composta \( F(g(x)) \) é dada por:
\[ \frac{d}{dx}[F(g(x))] = F'(g(x)) \cdot g'(x) = f(g(x)) \cdot g'(x) \]
Integrando ambos os lados em relação a \( x \):
\[ \int f(g(x)) \cdot g'(x) \, dx = F(g(x)) + C \]
Se introduzirmos a mudança formal de variável:
\[ u = g(x) \implies du = g'(x) \, dx \]
a integral na variável \( x \) converte-se com total rigor analítico em uma integral simples na variável \( u \):
\[ \int f(g(x)) \cdot g'(x) \, dx = \int f(u) \, du = F(u) + C = F(g(x)) + C \]

### O Algoritmo Prático em 4 Passos
1. **Identificar o Miolo \( u \):** Procure uma expressão interna \( g(x) \) (geralmente sob um radical, no denominador, como expoente de \( e \) ou argumento de função trigonométrica) cuja derivada \( g'(x) \) apareça multiplicando o restante da integral.
2. **Calcular o Diferencial \( du \):** Derive \( u \) em relação a \( x \) e isole \( du = g'(x) dx \).
3. **Substituir Todas as Ocorrências de \( x \):** A nova integral deve depender **exclusivamente de \( u \)**. Se sobrar qualquer termo com \( x \), a substituição foi incompleta ou inadequada!
4. **Integrar em \( u \) e Retornar a \( x \):** Resolva a integral elementar em \( u \) e substitua \( u \) de volta pela sua expressão original \( g(x) \).

### Ajuste de Constantes de Balanceamento
Se a derivada de \( g(x) \) diferir do termo presente na integral apenas por uma constante multiplicativa (ex: precisamos de \( 3x^2 dx \), mas temos apenas \( x^2 dx \)), podemos multiplicar o integrando por 3 e balancear dividindo a integral por 3 fora do operador:
\[ \int x^2 \sqrt{x^3 + 1} \, dx = \frac{1}{3} \int (x^3 + 1)^{1/2} \cdot (3x^2 \, dx) = \frac{1}{3} \int u^{1/2} \, du \]
*(Atenção: Constantes numéricas podem entrar e sair da integral à vontade; variáveis NUNCA podem ser balanceadas dessa maneira!)*

## 5. Definições e Conceitos
- **Mudança de Variável:** Técnica de mapeamento bijetor que simplifica o integrando.
- **Fator Diferencial:** O bloco \( g'(x) dx \) que é absorvido diretamente pelo símbolo \( du \).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Substituição com Radical
Calcule \( \int 2x \sqrt{x^2 + 9} \, dx \).
- **Passo 1 (Escolha do miolo):**
  A expressão dentro da raiz é \( x^2 + 9 \). Sua derivada é \( 2x \), que já está presente multiplicando na integral!
  Seja \( u = x^2 + 9 \).
- **Passo 2 (Calcular \( du \)):**
  \( du = 2x \, dx \).
- **Passo 3 (Substituir tudo na integral):**
  O termo \( \sqrt{x^2 + 9} \) vira \( \sqrt{u} = u^{1/2} \).
  O bloco \( 2x \, dx \) vira exatamente \( du \).
  \[ \int 2x \sqrt{x^2 + 9} \, dx = \int u^{1/2} \, du \]
- **Passo 4 (Integrar na variável \( u \)):**
  \[ \int u^{1/2} \, du = \frac{u^{3/2}}{3/2} + C = \frac{2}{3} u^{3/2} + C \]
- **Passo 5 (Retornar para a variável \( x \)):**
  Substituindo \( u = x^2 + 9 \):
  \[ \frac{2}{3} (x^2 + 9)^{3/2} + C = \frac{2}{3} \sqrt{(x^2 + 9)^3} + C \]

### Exemplo 2: Substituição com Função Tangente
Calcule \( \int \tan x \, dx \).
- **Passo 1:** Reescrevemos a tangente como razão: \( \int \frac{\sin x}{\cos x} \, dx \).
- **Passo 2:** Escolhemos o denominador como miolo: \( u = \cos x \).
  Derivada: \( du = -\sin x \, dx \implies \sin x \, dx = -du \).
- **Passo 3:** Substituindo:
  \[ \int \frac{\sin x \, dx}{\cos x} = \int \frac{-du}{u} = -\int \frac{1}{u} \, du \]
- **Passo 4:** A integral de \( 1/u \) é o logaritmo natural:
  \[ -\ln|u| + C = -\ln|\cos x| + C \]
- **Passo 5 (Forma elegante com secante):**
  Usando a propriedade do logaritmo \( -\ln(\cos x) = \ln((\cos x)^{-1}) = \ln(\sec x) \):
  \[ \int \tan x \, dx = \ln|\sec x| + C \]

## 7. Aplicações Práticas e Modelagem
- **Distribuição Normal em Estatística e IA:** No cálculo de probabilidades gaussianas truncadas, integrais do tipo \( \int x e^{-x^2 / 2\sigma^2} dx \) são resolvidas instantaneamente fazendo a substituição \( u = x^2 \).
- **Potencial Eletrostático de Distribuições Cilíndricas:** A integral do campo elétrico ao redor de um cilindro carregado recai em substituições simples gerando potenciais logarítmicos.

## 8. Exercícios de Fixação
1. Calcule \( \int x^2 (x^3 + 4)^5 \, dx \).
2. Determine \( \int \frac{\cos x}{\sin^3 x} \, dx \).
3. Calcule \( \int \frac{e^{\sqrt{x}}}{\sqrt{x}} \, dx \).

## 9. Correção Comentada dos Exercícios
1. \( u = x^3 + 4 \implies du = 3x^2 dx \implies x^2 dx = \frac{du}{3} \).
   Integral: \( \frac{1}{3} \int u^5 du = \frac{1}{3} \frac{u^6}{6} + C = \frac{1}{18}(x^3 + 4)^6 + C \).
2. \( u = \sin x \implies du = \cos x dx \).
   Integral: \( \int \frac{du}{u^3} = \int u^{-3} du = \frac{u^{-2}}{-2} + C = -\frac{1}{2\sin^2 x} + C = -\frac{1}{2}\csc^2 x + C \).
3. \( u = \sqrt{x} \implies du = \frac{1}{2\sqrt{x}} dx \implies \frac{dx}{\sqrt{x}} = 2 du \).
   Integral: \( \int e^u (2 du) = 2 e^u + C = 2 e^{\sqrt{x}} + C \).

## 10. Desafio de Domínio
Calcule a integral \( \int \frac{x}{\sqrt{x + 1}} \, dx \) utilizando a substituição \( u = x + 1 \) (onde \( x = u - 1 \)) e compare com a resolução por partes.

## 11. Resumo e Pontos-Chave
- Método da substituição é a Regra da Cadeia ao contrário: \( \int f(g(x))g'(x)dx = \int f(u)du \).
- Procure o termo mais interno e verifique se sua derivada está presente multiplicando.
- Pode-se balancear qualquer constante numérica multiplicativa, mas nunca variáveis.
- Lembre-se de retornar sempre à variável original no final.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 5.5: "A Regra da Substituição".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 10: Seção 10.2: "Mudança de Variável".
- OPENSTAX. *Calculus Volume 1*. Rice University, 2016. Seção 5.5: "Substitution".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema da Substituição na Integral",
                syntax = "\\int f(g(x)) g'(x) \\, dx = \\int f(u) \\, du \\quad [u = g(x), \\; du = g'(x)dx]",
                description = "Transformação da integral composta em integral simples.",
                parametersInfo = "A nova variável u deve eliminar completamente a variável x."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Integral da Tangente",
                syntax = "\\int \\tan x \\, dx = \\ln|\\sec x| + C",
                description = "Deduzida pela substituição u = cos(x).",
                parametersInfo = "Também expressa como -ln|cos(x)| + C."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Integração por Substituição com Balanceamento Numérico",
                codeOrFormula = "\\int x^2 (x^3 + 4)^5 dx = \\frac{1}{3} \\int u^5 du = \\frac{1}{18}(x^3 + 4)^6 + C",
                explanation = "A derivada do miolo u=x³+4 exigia o fator 3, balanceado multiplicando por 1/3 fora da integral."
            )
        ),
        goldTip = "O teste mental para a substituição leva 2 segundos: olhe para o que está 'dentro' da função mais feia e derive na cabeça. Se a derivada for muito parecida com o que está 'fora' (a menos de um número multiplicando), a substituição é garantida!"
    )

    // =========================================================================
    // AULA 24: Integração por Partes (Regra do Produto ao Contrário)
    // =========================================================================
    private val bundleCalcL24 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O que fazer quando temos uma integral onde duas funções de naturezas completamente distintas estão se multiplicando — como um polinômio e uma exponencial (\( \int x e^x dx \)), um polinômio e uma função trigonométrica (\( \int x \sin x dx \)) ou um logaritmo puro (\( \int \ln x dx \)) — e a técnica da substituição falha porque uma não é a derivada da outra? A ferramenta mestra para esses cenários é a **Integração por Partes**, a contrapartida analítica exata da Regra do Produto de Leibniz.

## 2. Objetivos de Aprendizagem
- Deduzir rigorosamente a fórmula de integração por partes: \( \int u \, dv = uv - \int v \, du \).
- Dominar a regra mnemônica **LIATE** para escolha infalível da função \( u \).
- Resolver integrais que exigem aplicação repetida do método (integração por partes tabular).
- Solucionar **integrais cíclicas** (como \( \int e^x \cos x dx \)) através de equacionamento algébrico.

## 3. Pré-requisitos
- Regra do produto para derivação (Aula 13).
- Diferenciais e integrais imediatas elementares (Aulas 15, 20 e 21).
- Resolução de equações algébricas lineares.

## 4. Explicação Teórica Aprofundada
### Dedução da Fórmula Fundamental
Sejam \( u(x) \) e \( v(x) \) funções diferenciáveis. Pela Regra do Produto:
\[ \frac{d}{dx}[u(x) \cdot v(x)] = u'(x) \cdot v(x) + u(x) \cdot v'(x) \]
Em termos de diferenciais, integrando ambos os lados em relação a \( x \):
\[ \int \frac{d}{dx}[u \cdot v] \, dx = \int v \cdot u'(x) \, dx + \int u \cdot v'(x) \, dx \]
\[ u \cdot v = \int v \, du + \int u \, dv \]
Reorganizando para isolar a integral original \( \int u \, dv \):
\[ \int u \, dv = u \cdot v - \int v \, du \]
*(Mnemônica Popular de Memorização: "Um Dia Vi Uma Vaca Vestida De Uniforme".)*

### A Estratégia de Escolha: A Regra LIATE
O sucesso da integração por partes reside inteiramente em saber decompor o integrando em duas metades: a função \( u \) (que será **derivada** para virar \( du \)) e o diferencial \( dv \) (que será **integrado** para virar \( v \)).
O objetivo supremo é fazer com que a nova integral \( \int v du \) seja muito mais simples que a original!
Para escolher quem deve ser o \( u \), consulte a lista prioritária **LIATE** (escolha quem aparecer primeiro):
1. **L** - **Logarítmicas:** \( \ln x, \log_a x \) *(sempre devem ser \( u \), pois derivam para funções algébricas simples)*.
2. **I** - **Inversas Trigonométricas:** \( \arcsin x, \arctan x \).
3. **A** - **Algébricas / Polinomiais:** \( x^n, 3x^2, x+1 \) *(suas derivadas reduzem o grau)*.
4. **T** - **Trigonométricas:** \( \sin x, \cos x \).
5. **E** - **Exponenciais:** \( e^x, 2^x \) *(são perfeitas para ser \( dv \), pois integram facilmente sem alterar a forma)*.

### Integrais Cíclicas
Quando combinamos exponenciais com funções trigonométricas (ex: \( \int e^x \sin x dx \)), a derivada de \( \sin x \) vira \( \cos x \), e a derivada de \( \cos x \) volta a ser \( -\sin x \). Após aplicar a integração por partes duas vezes consecutivas, a integral original reaparece no membro direito com sinal trocado. Basta somá-la algebricamente ao lado esquerdo e isolá-la dividindo pelo coeficiente!

## 5. Definições e Conceitos
- **Fórmula de Partes:** Ferramenta de transferência diferencial onde trocamos o peso do operador de \( dv \) para \( u \).
- **Integral de Logaritmo:** Resolvida por partes escolhendo \( u = \ln x \) e \( dv = dx \).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Polinômio vezes Exponencial
Calcule \( \int x e^x \, dx \).
- **Passo 1 (Escolha por LIATE):**
  Temos \( x \) (Algébrica) e \( e^x \) (Exponencial). Pela ordem LIATE, a Algébrica vem antes da Exponencial:
  - \( u = x \implies du = dx \) (derivamos).
  - \( dv = e^x dx \implies v = \int e^x dx = e^x \) (integramos).
- **Passo 2 (Montar a fórmula \( uv - \int v du \)):**
  \[ \int x e^x \, dx = (x)(e^x) - \int e^x \, dx \]
- **Passo 3 (Calcular a nova integral restante):**
  A nova integral \( \int e^x dx \) é trivial e vale \( e^x \):
  \[ x e^x - e^x + C = e^x (x - 1) + C \]
- **Passo 4 (Conferir por derivação):**
  \( [e^x(x - 1)]' = e^x(x - 1) + e^x(1) = x e^x - e^x + e^x = x e^x \). Perfeito!

### Exemplo 2: A Integral de \( \ln x \)
Calcule \( \int \ln x \, dx \).
- **Passo 1 (Artifício genial):** Parece haver apenas uma função, mas na verdade temos o produto de \( \ln x \) pelo diferencial \( 1 \cdot dx \)!
  - \( u = \ln x \implies du = \frac{1}{x} dx \).
  - \( dv = dx \implies v = x \).
- **Passo 2 (Aplicar a fórmula):**
  \[ \int \ln x \, dx = u \cdot v - \int v \, du = (\ln x)(x) - \int x \cdot \left(\frac{1}{x}\right) \, dx \]
- **Passo 3 (Simplificar e integrar):**
  Como \( x \cdot \frac{1}{x} = 1 \):
  \[ x \ln x - \int 1 \, dx = x \ln x - x + C = x(\ln x - 1) + C \]

## 7. Aplicações Práticas e Modelagem
- **Transformada de Laplace em Engenharia de Controle:** A definição da Transformada de Laplace \( \mathcal{L}\{f(t)\} = \int_0^\infty e^{-st} f(t) dt \) é calculada quase que universalmente por integração por partes.
- **Teoria Quântica e Valores Médios:** O cálculo do valor esperado de posição e momento da função de onda de Schrödinger \( \langle x \rangle = \int \psi^* x \psi \, dx \) exige integração por partes nos estados fundamentais.

## 8. Exercícios de Fixação
1. Calcule \( \int x \cos x \, dx \).
2. Determine \( \int x^2 e^x \, dx \).
3. Calcule \( \int \arctan x \, dx \).

## 9. Correção Comentada dos Exercícios
1. \( u = x \implies du = dx \); \( dv = \cos x dx \implies v = \sin x \).
   \( \int x \cos x dx = x \sin x - \int \sin x dx = x \sin x - (-\cos x) + C = x \sin x + \cos x + C \).
2. Partes 1: \( u = x^2 \implies du = 2x dx \); \( dv = e^x dx \implies v = e^x \).
   Integral vira \( x^2 e^x - 2 \int x e^x dx \).
   Partes 2 na nova integral: já vimos no Exemplo 1 que \( \int x e^x dx = e^x(x - 1) \).
   Resultado: \( x^2 e^x - 2 e^x (x - 1) + C = e^x (x^2 - 2x + 2) + C \).
3. \( u = \arctan x \implies du = \frac{1}{1+x^2} dx \); \( dv = dx \implies v = x \).
   \( \int \arctan x dx = x \arctan x - \int \frac{x}{1+x^2} dx \).
   A segunda integral resolve-se por substituição simples (\( w = 1+x^2 \implies dw = 2x dx \)), gerando \( \frac{1}{2} \ln(1+x^2) \).
   Resultado final: \( x \arctan x - \frac{1}{2} \ln(1 + x^2) + C \).

## 10. Desafio de Domínio
Calcule a integral cíclica clássica \( I = \int e^{2x} \sin x \, dx \) aplicando integração por partes duas vezes e isolando a incógnita \( I \).

## 11. Resumo e Pontos-Chave
- Fórmula: \( \int u \, dv = uv - \int v \, du \).
- Ordem de preferência para \( u \): LIATE (Logarítmica, Inversa, Algébrica, Trigonométrica, Exponencial).
- Funções como \( \ln x \) e \( \arctan x \) integram-se por partes fazendo \( dv = dx \).
- Se a integral original reaparecer com sinal invertido, isole-a algebricamente.

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 7.1: "Integração por Partes".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 11: Seção 11.1: "Integração por Partes".
- OPENSTAX. *Calculus Volume 2*. Rice University, 2016. Seção 3.1: "Integration by Parts".
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Fórmula de Integração por Partes",
                syntax = "\\int u \\, dv = u \\cdot v - \\int v \\, du",
                description = "Inversão analítica da regra do produto para integrais.",
                parametersInfo = "u é diferenciada (du = u'dx) e dv é integrada (v = ∫ dv)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Regra Mnemônica LIATE",
                syntax = "\\text{L} \\to \\text{I} \\to \\text{A} \\to \\text{T} \\to \\text{E}",
                description = "Critério de prioridade para seleção da função u.",
                parametersInfo = "Logarítmica, Inversa trigonométrica, Algébrica, Trigonométrica, Exponencial."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Integração por Partes Padrão",
                codeOrFormula = "\\int x e^x dx = x e^x - \\int e^x dx = e^x(x - 1) + C",
                explanation = "A escolha u=x rebaixou o polinômio para du=dx, deixando uma integral imediata em v."
            )
        ),
        goldTip = "Toda vez que você tiver que calcular a integral de uma função 'órfã' cuja derivada você conhece mas a integral não (como ln x, arcsen x ou arctan x), adote dv = dx! Isso obriga a função a ser derivada no du e resolve a questão imediatamente!"
    )

    // =========================================================================
    // AULA 25: Frações Parciais e Substituição Trigonométrica
    // =========================================================================
    private val bundleCalcL25 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como integrar quocientes de polinômios onde o numerador não é a derivada do denominador, como \( \int \frac{1}{x^2 - 5x + 6} dx \), ou integrais com raízes que descrevem áreas de círculos como \( \int \sqrt{9 - x^2} dx \)? Para esses grandes desafios do Cálculo I, aprendemos duas técnicas especializadas de precisão cirúrgica: o **Método das Frações Parciais** (que desmonta frações complicadas em somas de logaritmos) e a **Substituição Trigonométrica** (que usa a geometria do triângulo retângulo para destruir radicais).

## 2. Objetivos de Aprendizagem
- Fatorar polinômios em denominadores e decompor frações racionais próprias em Frações Parciais.
- Determinar os coeficientes constantes pelo Método dos Valores Convenientes e por Sistema Linear.
- Identificar os três casos clássicos de Substituição Trigonométrica (\( \sqrt{a^2 - x^2} \), \( \sqrt{a^2 + x^2} \), \( \sqrt{x^2 - a^2} \)).
- Utilizar triângulos retângulos auxiliares para retornar da variável angular \( \theta \) para a variável cartesiana \( x \).

## 3. Pré-requisitos
- Fatoração de polinômios do 2º grau e raízes reais.
- Integrais da função recíproca (\( \ln|x| \)) e arco-tangente (Aula 21).
- Relações fundamentais da trigonometria (\( \sin^2 \theta + \cos^2 \theta = 1 \) e \( 1 + \tan^2 \theta = \sec^2 \theta \)).

## 4. Explicação Teórica Aprofundada
### O Método das Frações Parciais
Qualquer função racional \( \frac{P(x)}{Q(x)} \) onde o grau de \( P \) é menor que o grau de \( Q \) (fração própria) pode ser decomposta em uma soma de frações mais simples:
1. **Fatores Lineares Distintos:** Se \( Q(x) = (x - r_1)(x - r_2) \dots (x - r_k) \):
   \[ \frac{P(x)}{Q(x)} = \frac{A_1}{x - r_1} + \frac{A_2}{x - r_2} + \dots + \frac{A_k}{x - r_k} \]
   Cada uma dessas frações individuais integra diretamente em um logaritmo natural: \( \int \frac{A}{x - r} dx = A \ln|x - r| + C \).
2. **Fatores Lineares Repetidos:** Se \( Q(x) \) possui \( (x - r)^m \), a expansão requer potências crescentes:
   \[ \frac{A_1}{x - r} + \frac{A_2}{(x - r)^2} + \dots + \frac{A_m}{(x - r)^m} \]

### A Substituição Trigonométrica
Esta técnica elimina radicais quadráticos indesejados explorando as identidades pitagóricas fundamentais:
- **Caso 1: Forma \( \sqrt{a^2 - x^2} \)**
  - Adote a substituição: \( x = a \sin \theta \implies dx = a \cos \theta \, d\theta \).
  - O radical simplifica-se: \( \sqrt{a^2 - a^2 \sin^2 \theta} = \sqrt{a^2(1 - \sin^2 \theta)} = a \cos \theta \).
- **Caso 2: Forma \( \sqrt{a^2 + x^2} \)**
  - Adote: \( x = a \tan \theta \implies dx = a \sec^2 \theta \, d\theta \).
  - O radical vira: \( \sqrt{a^2(1 + \tan^2 \theta)} = a \sec \theta \).
- **Caso 3: Forma \( \sqrt{x^2 - a^2} \)**
  - Adote: \( x = a \sec \theta \implies dx = a \sec \theta \tan \theta \, d\theta \).
  - O radical vira: \( \sqrt{a^2(\sec^2 \theta - 1)} = a \tan \theta \).

### O Triângulo Retângulo de Retorno
Após calcular a integral em \( \theta \), desenhe um triângulo retângulo onde as definições de seno, cosseno ou tangente correspondam à substituição adotada para ler diretamente as funções trigonométricas restantes em termos de \( x \).

## 5. Definições e Conceitos
- **Fração Própria:** Fração onde o grau do numerador é estritamente menor que o do denominador (se for maior ou igual, deve-se efetuar a divisão de polinômios primeiro).
- **Identidade Pitagórica:** Relação que conecta o quadrado de funções circulares a constantes, destruindo raízes quadradas.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Frações Parciais com Fatores Lineares
Calcule \( \int \frac{5x - 4}{x^2 - x - 2} \, dx \).
- **Passo 1 (Fatorar o denominador):**
  As raízes de \( x^2 - x - 2 = 0 \) são \( x = 2 \) e \( x = -1 \).
  Portanto, \( x^2 - x - 2 = (x - 2)(x + 1) \).
- **Passo 2 (Montar a decomposição):**
  \[ \frac{5x - 4}{(x - 2)(x + 1)} = \frac{A}{x - 2} + \frac{B}{x + 1} \]
  Multiplicando pelo denominador comum:
  \[ 5x - 4 = A(x + 1) + B(x - 2) \]
- **Passo 3 (Determinar as constantes por valores convenientes):**
  - Escolhendo \( x = 2 \): \( 5(2) - 4 = A(2 + 1) + B(0) \implies 6 = 3A \implies A = 2 \).
  - Escolhendo \( x = -1 \): \( 5(-1) - 4 = A(0) + B(-1 - 2) \implies -9 = -3B \implies B = 3 \).
- **Passo 4 (Integrar as frações simples):**
  \[ \int \left( \frac{2}{x - 2} + \frac{3}{x + 1} \right) \, dx = 2\ln|x - 2| + 3\ln|x + 1| + C \]

### Exemplo 2: Substituição Trigonométrica na Área do Círculo
Calcule \( \int \sqrt{4 - x^2} \, dx \).
- **Passo 1 (Identificar o caso):** Temos \( a^2 = 4 \implies a = 2 \). Forma \( \sqrt{a^2 - x^2} \).
  Substituição: \( x = 2\sin \theta \implies dx = 2\cos \theta \, d\theta \).
- **Passo 2 (Simplificar o radical):**
  \( \sqrt{4 - x^2} = \sqrt{4 - 4\sin^2 \theta} = 2\cos \theta \).
- **Passo 3 (Montar a integral):**
  \[ \int (2\cos \theta)(2\cos \theta \, d\theta) = 4 \int \cos^2 \theta \, d\theta \]
- **Passo 4 (Usar identidade de arco duplo \( \cos^2 \theta = \frac{1 + \cos(2\theta)}{2} \)):**
  \[ 4 \int \frac{1 + \cos(2\theta)}{2} \, d\theta = 2 \int (1 + \cos(2\theta)) \, d\theta = 2 \left( \theta + \frac{\sin(2\theta)}{2} \right) = 2\theta + \sin(2\theta) \]
  Lembrando que \( \sin(2\theta) = 2\sin \theta \cos \theta \):
  \[ 2\theta + 2\sin \theta \cos \theta + C \]
- **Passo 5 (Retorno a \( x \)):**
  Como \( \sin \theta = \frac{x}{2} \implies \theta = \arcsin\left(\frac{x}{2}\right) \) e \( \cos \theta = \frac{\sqrt{4 - x^2}}{2} \):
  \[ 2\arcsin\left(\frac{x}{2}\right) + 2\left(\frac{x}{2}\right)\left(\frac{\sqrt{4 - x^2}}{2}\right) + C = 2\arcsin\left(\frac{x}{2}\right) + \frac{x\sqrt{4 - x^2}}{2} + C \]

## 7. Aplicações Práticas e Modelagem
- **Cinética de Reações Químicas de Segunda Ordem:** A velocidade de reações bimoleculares \( \frac{dx}{dt} = k(a - x)(b - x) \) é integrada pelo método de frações parciais para determinar o tempo de meia-vida.
- **Relatividade e Trajetórias Geodésicas:** O cálculo do desvio gravitacional da luz ao redor de buracos negros de Schwarzschild exige integrais elípticas simplificadas por substituição trigonométrica.

## 8. Exercícios de Fixação
1. Decomponha em frações parciais e integre: \( \int \frac{1}{x^2 - 4} \, dx \).
2. Calcule \( \int \frac{1}{\sqrt{9 + x^2}} \, dx \) usando \( x = 3\tan \theta \).
3. Determine a forma da decomposição em frações parciais de \( \frac{x + 7}{(x - 1)(x + 2)^2} \).

## 9. Correção Comentada dos Exercícios
1. \( x^2 - 4 = (x-2)(x+2) \). \( \frac{1}{(x-2)(x+2)} = \frac{A}{x-2} + \frac{B}{x+2} \implies 1 = A(x+2) + B(x-2) \).
   Para \( x = 2 \implies A = 1/4 \); para \( x = -2 \implies B = -1/4 \).
   Integral: \( \frac{1}{4}\ln|x-2| - \frac{1}{4}\ln|x+2| + C = \frac{1}{4}\ln\left|\frac{x-2}{x+2}\right| + C \).
2. \( x = 3\tan \theta \implies dx = 3\sec^2 \theta d\theta \); \( \sqrt{9+x^2} = 3\sec \theta \).
   Integral: \( \int \frac{3\sec^2 \theta d\theta}{3\sec \theta} = \int \sec \theta d\theta = \ln|\sec \theta + \tan \theta| + C \).
   Retornando ao triângulo com \( \tan \theta = x/3 \) e \( \sec \theta = \frac{\sqrt{9+x^2}}{3} \):
   \( \ln\left|\frac{\sqrt{9+x^2} + x}{3}\right| + C = \ln|\sqrt{9+x^2} + x| + C_1 \).
3. Pela regra de fatores lineares repetidos: \( \frac{A}{x - 1} + \frac{B}{x + 2} + \frac{C}{(x + 2)^2} \).

## 10. Desafio de Domínio
Calcule \( \int \frac{x^3 + 2}{x^2 - 1} \, dx \) realizando a divisão prévia de polinômios antes de aplicar as frações parciais.

## 11. Resumo e Pontos-Chave
- Frações Parciais: Decompõe funções racionais em somas elementares de termos da forma \( \frac{A}{x - r} \).
- Use valores convenientes que anulam os denominadores para achar as constantes instantaneamente.
- Substituições Trigonométricas:
  - \( \sqrt{a^2 - x^2} \implies x = a \sin \theta \)
  - \( \sqrt{a^2 + x^2} \implies x = a \tan \theta \)
  - \( \sqrt{x^2 - a^2} \implies x = a \sec \theta \)

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seção 7.4: "Integração de Funções Racionais por Frações Parciais" e Seção 7.3: "Substituição Trigonométrica".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 11: Seções 11.2 e 11.3.
- OPENSTAX. *Calculus Volume 2*. Rice University, 2016. Seções 3.3 e 3.4.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Decomposição em Frações Parciais",
                syntax = "\\frac{P(x)}{(x - r_1)(x - r_2)} = \\frac{A}{x - r_1} + \\frac{B}{x - r_2}",
                description = "Separação de razões racionais em termos logarítmicos.",
                parametersInfo = "Exige que o grau do numerador seja estritamente menor que o do denominador."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Tríade de Substituições Trigonométricas",
                syntax = "\\sqrt{a^2 - x^2} \\to x = a\\sin\\theta; \\; \\sqrt{a^2 + x^2} \\to x = a\\tan\\theta; \\; \\sqrt{x^2 - a^2} \\to x = a\\sec\\theta",
                description = "Mapeamento geométrico para eliminação de radicais quadráticos.",
                parametersInfo = "Utilize um triângulo retângulo auxiliar para reverter à variável x."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Decomposição por Frações Parciais",
                codeOrFormula = "\\int \\frac{5x-4}{x^2-x-2} dx = \\int \\left(\\frac{2}{x-2} + \\frac{3}{x+1}\\right) dx = 2\\ln|x-2| + 3\\ln|x+1| + C",
                explanation = "A determinação dos coeficientes A=2 e B=3 converteu uma fração racional em dois logaritmos imediatos."
            )
        ),
        goldTip = "Antes de tentar frações parciais, confira sempre os graus: se o grau do numerador for MAIOR ou IGUAL ao do denominador, divida os polinômios primeiro! Só aplique frações parciais no resto da divisão!"
    )

    // =========================================================================
    // AULA 26: A Integral Definida e o Teorema Fundamental (TFC)
    // =========================================================================
    private val bundleCalcL26 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Chegamos ao ápice intelectual de todo o curso de Cálculo I: o **Teorema Fundamental do Cálculo (TFC)**. Durante quase dois milênios, a humanidade considerou o Problema da Reta Tangente (calcular inclinações instantâneas) e o Problema da Área (medir regiões sob curvas curvas) como dois mistérios geométricos completamente desconectados. O TFC é a coroação da matemática moderna porque prova que a **Diferenciação e a Integração são operações estritamente inversas uma da outra**, unificando todo o Cálculo e permitindo calcular áreas exatas sem jamais precisar somar infinitos retângulos.

## 2. Objetivos de Aprendizagem
- Construir a Integral Definida formalmente como o limite das Somas de Riemann: \( \int_a^b f(x) dx = \lim_{n \to \infty} \sum_{i=1}^n f(x_i^*) \Delta x \).
- Enunciar e aplicar a Parte 1 do TFC (Derivada do Acumulador): \( \frac{d}{dx} \left[ \int_a^x f(t) \, dt \right] = f(x) \).
- Enunciar e aplicar a Parte 2 do TFC (Fórmula de Newton-Leibniz): \( \int_a^b f(x) \, dx = F(b) - F(a) \).
- Calcular áreas planas de regiões delimitadas por curvas e eixos cartesianos.

## 3. Pré-requisitos
- Todas as técnicas de integração indefinida (Aulas 19 a 25).
- Somatórios, limites no infinito e partições de intervalos reais.
- Interpretação geométrica da área cartesiana.

## 4. Explicação Teórica Aprofundada
### A Construção da Soma de Riemann
Como medir a área exata \( A \) sob o gráfico de uma função positiva e contínua \( f(x) \) entre \( x = a \) e \( x = b \)?
1. Dividimos o intervalo \( [a, b] \) em \( n \) subintervalos de largura idêntica \( \Delta x = \frac{b - a}{n} \).
2. Em cada subintervalo \( [x_{i-1}, x_i] \), escolhemos um ponto de amostragem \( x_i^* \) e erguemos um retângulo de base \( \Delta x \) e altura \( f(x_i^*) \).
3. A soma das áreas dos \( n \) retângulos é a **Soma de Riemann**:
   \[ S_n = \sum_{i=1}^n f(x_i^*) \Delta x \]
4. Tomando o limite quando a quantidade de retângulos tende ao infinito (\( n \to \infty \)) e a largura tende a zero (\( \Delta x \to 0 \)):
   \[ \int_a^b f(x) \, dx = \lim_{n \to \infty} \sum_{i=1}^n f(x_i^*) \Delta x \]
Este valor limite finito é a **Integral Definida** de \( a \) até \( b \).
*Diferença Crucial:* A integral indefinida \( \int f(x) dx \) é uma **família de funções** (com \( + C \)). A integral definida \( \int_a^b f(x) dx \) é um **número real fixo** que representa a área líquida com sinal.

### O Teorema Fundamental do Cálculo — Parte 1
Se \( f \) for contínua em \( [a, b] \), então a função acumuladora \( g(x) \) definida por:
\[ g(x) = \int_a^x f(t) \, dt \quad (a \le x \le b) \]
é contínua em \( [a, b] \), diferenciável em \( (a, b) \), e sua derivada é exatamente:
\[ g'(x) = \frac{d}{dx} \left[ \int_a^x f(t) \, dt \right] = f(x) \]
*Significado:* O ato de derivar a função acumuladora devolve o integrando original intacto! Se o limite superior for uma função composta \( u(x) \), aplicamos a Regra da Cadeia:
\[ \frac{d}{dx} \left[ \int_a^{u(x)} f(t) \, dt \right] = f(u(x)) \cdot u'(x) \]

### O Teorema Fundamental do Cálculo — Parte 2 (Avaliação)
Se \( f \) for contínua em \( [a, b] \) e \( F \) for qualquer primitiva de \( f \) em \( [a, b] \) (isto é, \( F' = f \)), então:
\[ \int_a^b f(x) \, dx = [F(x)]_a^b = F(b) - F(a) \]
Essa fórmula milagrosa afirma que, para encontrar a área exata sob qualquer curva suave, não precisamos somar infinitos retângulos: basta encontrar uma primitiva elementar \( F(x) \) e subtrair o valor no extremo final pelo valor no extremo inicial! A constante \( C \) cancela-se: \( [F(b) + C] - [F(a) + C] = F(b) - F(a) \).

## 5. Definições e Conceitos
- **Teorema Fundamental do Cálculo:** O elo analítico que estabelece a diferenciação e a integração como processos recíprocos.
- **Área Líquida com Sinal:** Regiões onde o gráfico está acima do eixo \( x \) geram áreas positivas; regiões onde está abaixo geram áreas negativas.
- **Integral Definida:** Operador que mapeia uma função e um intervalo a um número real escalar.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Cálculo de Área sob uma Parábola
Calcule a área exata sob a curva \( f(x) = x^2 \) no intervalo \( [0, 3] \).
- **Passo 1 (Escrever a integral definida):**
  \[ A = \int_0^3 x^2 \, dx \]
- **Passo 2 (Encontrar uma primitiva \( F(x) \)):**
  Pela regra da potência: \( F(x) = \frac{x^3}{3} \).
- **Passo 3 (Aplicar o TFC Parte 2):**
  \[ \int_0^3 x^2 \, dx = \left[ \frac{x^3}{3} \right]_0^3 = \frac{3^3}{3} - \frac{0^3}{3} = \frac{27}{3} - 0 = 9 \]
- **Conclusão:** A área sob a parábola é exatamente 9 unidades de área.

### Exemplo 2: Derivação de Acumulador com Cadeia (TFC 1)
Determine \( \frac{d}{dx} \left[ \int_1^{x^4} \sec(t) \, dt \right] \).
- **Passo 1 (Identificar o limite superior composto):**
  O limite superior é \( u(x) = x^4 \implies u'(x) = 4x^3 \).
- **Passo 2 (Aplicar TFC Parte 1 com Regra da Cadeia):**
  \[ \frac{d}{dx} \left[ \int_1^{u(x)} \sec(t) \, dt \right] = \sec(u(x)) \cdot u'(x) \]
- **Passo 3 (Substituir \( u(x) \)):**
  \[ \sec(x^4) \cdot (4x^3) = 4x^3 \sec(x^4) \]
- *(Observe que resolvemos a derivada sem precisar saber integrar a função secante!)*

### Exemplo 3: Área Entre Duas Curvas
Determine a área da região delimitada pelas parábolas \( y = x^2 \) e \( y = 2x - x^2 \).
- **Passo 1 (Encontrar os pontos de interseção):**
  \[ x^2 = 2x - x^2 \implies 2x^2 - 2x = 0 \implies 2x(x - 1) = 0 \implies x = 0 \quad \text{e} \quad x = 1 \]
- **Passo 2 (Identificar a curva superior e inferior):**
  No intervalo \( (0, 1) \), para \( x = 0,5 \): \( 2(0,5) - (0,5)^2 = 1 - 0,25 = 0,75 \), enquanto \( (0,5)^2 = 0,25 \).
  Curva de cima: \( y_1 = 2x - x^2 \). Curva de baixo: \( y_2 = x^2 \).
- **Passo 3 (Montar a integral da diferença):**
  \[ A = \int_0^1 [(2x - x^2) - x^2] \, dx = \int_0^1 (2x - 2x^2) \, dx \]
- **Passo 4 (Calcular pelo TFC):**
  \[ \left[ x^2 - \frac{2x^3}{3} \right]_0^1 = \left(1^2 - \frac{2(1)^3}{3}\right) - (0) = 1 - \frac{2}{3} = \frac{1}{3} \]
- **Conclusão:** A área entre as parábolas é \( \frac{1}{3} \).

## 7. Aplicações Práticas e Modelagem
- **Trabalho Mecânico de Forças Variáveis:** O trabalho total realizado por uma força variável é a integral definida \( W = \int_{x_1}^{x_2} F(x) dx \).
- **Excedente do Consumidor e do Produtor:** Em microeconomia analítica, o bem-estar econômico de um mercado é medido pela área entre as curvas de oferta, demanda e o preço de equilíbrio via integrais definidas.

## 8. Exercícios de Fixação
1. Calcule \( \int_1^4 (3x^2 - 2x) \, dx \).
2. Determine \( \int_0^\pi \sin x \, dx \).
3. Calcule \( \frac{d}{dx} \left[ \int_0^x \sqrt{1 + t^3} \, dt \right] \).

## 9. Correção Comentada dos Exercícios
1. Primitiva: \( x^3 - x^2 \). Avaliando: \( [4^3 - 4^2] - [1^3 - 1^2] = (64 - 16) - (1 - 1) = 48 - 0 = 48 \).
2. Primitiva: \( -\cos x \). Avaliando de 0 a \( \pi \): \( [-\cos(\pi)] - [-\cos(0)] = [-(-1)] - [-(1)] = 1 + 1 = 2 \). A área de um arco completo de onda senoidal vale exatamente 2!
3. Pelo TFC Parte 1: a derivada da integral com limite superior \( x \) é o próprio integrando avaliado em \( x \): \( \sqrt{1 + x^3} \).

## 10. Desafio de Domínio
Utilize o TFC Parte 1 e a propriedade de inversão de limites de integração (\( \int_b^a = -\int_a^b \)) para calcular a derivada de \( F(x) = \int_{x^2}^{\sin x} \frac{1}{1 + t^2} \, dt \).

## 11. Resumo e Pontos-Chave
- A Integral Definida \( \int_a^b f(x) dx \) é o limite da soma infinita de retângulos infinitesimais de Riemann.
- TFC Parte 1: \( \frac{d}{dx}\int_a^x f(t)dt = f(x) \) (Integração e Derivação são processos inversos).
- TFC Parte 2: \( \int_a^b f(x) dx = F(b) - F(a) \), eliminando a necessidade de somatórios infinitos.
- Área entre curvas: \( \int_a^b [\text{Curva Superior} - \text{Curva Inferior}] dx \).

## 12. Referências Bibliográficas
- STEWART, James. *Cálculo, Volume 1*. 8ª ed. Cengage Learning, 2016. Seções 5.1: "Áreas e Distâncias", 5.2: "A Integral Definida", 5.3: "O Teorema Fundamental do Cálculo" e 6.1: "Áreas entre Curvas".
- GUIDORIZZI, Hamilton Luiz. *Um Curso de Cálculo, Volume 1*. 5ª ed. LTC, 2001. Capítulo 12: "A Integral de Riemann e o Teorema Fundamental".
- STRANG, Gilbert; HERMAN, Edwin. *Calculus Volume 1*. OpenStax, 2016. Seções 5.2 e 5.4.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema Fundamental do Cálculo (Parte 1)",
                syntax = "\\frac{d}{dx} \\left[ \\int_a^x f(t) \\, dt \\right] = f(x)",
                description = "Demonstra que a derivação desfaz a acumulação integral.",
                parametersInfo = "Se o limite for u(x), multiplique por u'(x) pela regra da cadeia."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema Fundamental do Cálculo (Parte 2)",
                syntax = "\\int_a^b f(x) \\, dx = F(b) - F(a)",
                description = "Fórmula de Newton-Leibniz para cálculo exato de integrais definidas.",
                parametersInfo = "F é qualquer primitiva contínua tal que F' = f."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Cálculo de Área Entre Curvas",
                syntax = "A = \\int_a^b [f(x) - g(x)] \\, dx \\quad (f(x) \\ge g(x))",
                description = "Área da região delimitada pela curva superior f e inferior g.",
                parametersInfo = "Os limites a e b são obtidos pelos pontos de interseção das curvas."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cálculo de Área por Primitiva com TFC 2",
                codeOrFormula = "\\int_0^3 x^2 dx = \\left[ \\frac{x^3}{3} \\right]_0^3 = \\frac{27}{3} - 0 = 9",
                explanation = "A avaliação da primitiva nos extremos substituiu completamente a soma infinita de limites de Riemann."
            )
        ),
        goldTip = "Ao calcular integrais definidas, NUNCA gaste tempo escrevendo '+ C', pois ao fazer F(b) - F(a) o '+ C' sempre cancela! E atenção redobrada: se o resultado de uma área geométrica der negativo, você integrou a curva de baixo menos a de cima; inverta a ordem das funções!"
    )
}

