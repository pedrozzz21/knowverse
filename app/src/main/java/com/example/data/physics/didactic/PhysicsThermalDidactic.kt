package com.example.data.physics.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Física ENEM — Módulo 2: Termologia e Termodinâmica (Aulas fis_m3_a1 a fis_m3_a5).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em Halliday & Resnick, Sears & Zemansky, Tipler e Matriz ENEM / INEP.
 */
object PhysicsThermalDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "fis_m3_a1" -> bundleFisM3A1
            "fis_m3_a2" -> bundleFisM3A2
            "fis_m3_a3" -> bundleFisM3A3
            "fis_m3_a4" -> bundleFisM3A4
            "fis_m3_a5" -> bundleFisM3A5
            else -> null
        }
    }

    // =========================================================================
    // AULA fis_m3_a1: Termometria e Escalas Termométricas
    // =========================================================================
    private val bundleFisM3A1 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A Termologia é o ramo da Física dedicado à compreensão dos fenômenos térmicos, regidos pelo comportamento microscópico da matéria. O primeiro passo fundamental nesse domínio é a **Termometria**, que estuda a medição quantitativa da temperatura e a calibração de instrumentos de medida. Cotidianamente, as noções subjetivas de "quente" ou "frio" são sensações térmicas enganosas influenciadas pela condutividade dos materiais. A física requer uma grandeza objetiva e mensurável para quantificar o estado de agitação atômica e molecular.

## 2. Objetivos de Aprendizagem
- Diferenciar com exatidão científica os conceitos de **Temperatura**, **Calor** e **Energia Térmica**.
- Compreender o conceito de **Equilíbrio Térmico** e a **Lei Zero da Termodinâmica**.
- Analisar o funcionamento de grandezas termométricas e substâncias termométricas.
- Deduzir a relação de conversão entre as três principais escalas termométricas: Celsius (°C), Fahrenheit (°F) e Kelvin (K).
- Compreender o conceito termodinâmico de **Zero Absoluto** (\( 0\text{ K} \)) como o limite inferior da energia cinética molecular.
- Construir e calibrar uma escala termométrica arbitrária a partir de pontos fixos fundamentais.

## 3. Pré-requisitos
- Proporções e semelhança de segmentos em Geometria Básica (Matemática Básica, Aula mb_l06).
- Equações do 1º grau e funções afins (Matemática Básica, Aula mb_l07).

## 4. Explicação Teórica Aprofundada

### 1. Definições Termodinâmicas Fundamentais
- **Temperatura (\( T \)):** É a grandeza física escalar microscópica que mede o grau médio de agitação térmica (energia cinética translacional média) das partículas constituintes de um sistema físico. Quanto maior a velocidade média com que átomos e moléculas vibram, transladam e rotacionam, maior é a sua temperatura:
  \[ E_{c,\text{média}} = \frac{3}{2} k_B T \]
  onde \( k_B = 1{,}38 \times 10^{-23}\text{ J/K} \) é a constante de Boltzmann.
- **Calor (\( Q \)):** É a **energia térmica em trânsito espontâneo** entre dois ou mais corpos exclusivamente motivada por uma diferença de temperatura entre eles. Corpos **não possuem calor**; corpos possuem energia interna. O calor cessa assim que as temperaturas se igualam.
- **Lei Zero da Termodinâmica:** Se dois corpos A e B estão, individualmente, em equilíbrio térmico com um terceiro corpo C, então A e B estão em equilíbrio térmico mútuo entre si (\( T_A = T_B \)). Essa lei fundamenta o uso dos termômetros.

### 2. Escalas Termométricas e Pontos Fixos
Para calibrar uma escala, adotam-se tradicionalmente dois pontos físicos de mudança de fase da água pura sob pressão normal de \( 1\text{ atm} \) (\( 101{,}3\text{ kPa} \)):
1. **Ponto do Gelo (Fusão do Gelo):**
   - Celsius: \( 0^\circ\text{C} \)
   - Fahrenheit: \( 32^\circ\text{F} \)
   - Kelvin: \( 273{,}15\text{ K} \)
2. **Ponto do Vapor (Ebulição da Água):**
   - Celsius: \( 100^\circ\text{C} \)
   - Fahrenheit: \( 212^\circ\text{F} \)
   - Kelvin: \( 373{,}15\text{ K} \)

### 3. Dedução da Equação Geral de Conversão
Considerando uma mesma coluna de mercúrio submetida a uma temperatura qualquer, estabelecemos a proporção linear entre os segmentos geométricos:
\[ \frac{\theta_C - 0}{100 - 0} = \frac{\theta_F - 32}{212 - 32} = \frac{T_K - 273}{373 - 273} \]
Simplificando os denominadores dividindo todos por 20:
\[ \frac{\theta_C}{5} = \frac{\theta_F - 32}{9} = \frac{T_K - 273}{5} \]

### 4. Relação entre Variações de Temperatura (\( \Delta T \))
Um detalhe recorrente e armadilha clássica no ENEM:
\[ \Delta \theta_C = \Delta T_K \]
\[ \frac{\Delta \theta_C}{5} = \frac{\Delta \theta_F}{9} \implies \Delta \theta_F = 1{,}8 \cdot \Delta \theta_C \]
Uma variação de \( 1^\circ\text{C} \) equivale exatamente a uma variação de \( 1\text{ K} \), mas equivale a uma variação de \( 1{,}8^\circ\text{F} \).

### 5. O Zero Absoluto (\( 0\text{ K} \))
A escala Kelvin é uma **escala termodinâmica absoluta**. O valor \( 0\text{ K} \) (\( -273{,}15^\circ\text{C} \)) representa o estado hipotético em que a energia cinética clássica das partículas seria nula (pela mecânica quântica, subsiste a energia de ponto zero). É fisicamente impossível atingir o zero absoluto ou temperaturas negativas na escala Kelvin.

## 5. Definições e Conceitos
- **Termoscópio:** Dispositivo qualitativo que indica variações térmicas sem calibração numérica formal (inventado por Galileu).
- **Substância Termométrica:** Material cuja propriedade física varia linearmente com a temperatura (ex.: volume do mercúrio/álcool, resistência de uma termorresistência Pt100).
- **Ponto Triplo da Água:** Estado termodinâmico único em que coexistem gelo, água líquida e vapor em equilíbrio (\( T = 273{,}16\text{ K} \), \( P = 611{,}65\text{ Pa} \)), adotado no SI como padrão de referência.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Conversão e Temperatura de Coincidência
Qual é a temperatura cujo valor numérico na escala Fahrenheit é exatamente igual ao valor na escala Celsius?
- **Resolução:**
  1. Condição do enunciado: \( \theta_F = \theta_C = x \).
  2. Substituindo na fórmula de conversão:
     \[ \frac{x}{5} = \frac{x - 32}{9} \]
  3. Multiplicação cruzada:
     \[ 9x = 5(x - 32) \implies 9x = 5x - 160 \]
     \[ 4x = -160 \implies x = -40 \]
  - Resposta: \( -40^\circ\text{C} = -40^\circ\text{F} \).

### Exemplo 2: Variação Climática e Escala Fahrenheit
Em uma cidade serrana, os termômetros registraram \( 10^\circ\text{C} \) pela manhã e \( 35^\circ\text{C} \) à tarde. Qual foi a elevação térmica dessa cidade expressa na escala Fahrenheit?
- **Resolução:**
  1. Variação em Celsius: \( \Delta \theta_C = 35 - 10 = 25^\circ\text{C} \).
  2. Relação para variações:
     \[ \frac{\Delta \theta_C}{5} = \frac{\Delta \theta_F}{9} \implies \frac{25}{5} = \frac{\Delta \theta_F}{9} \]
     \[ 5 = \frac{\Delta \theta_F}{9} \implies \Delta \theta_F = 45^\circ\text{F} \]
  - Resposta: A variação foi de \( 45^\circ\text{F} \).

## 7. Aplicações Práticas e Modelagem
- **Termometria Clínica Digital:** Utiliza termistores (semicondutores cuja resistência elétrica cai drasticamente com a temperatura), processados por microcontroladores.
- **Pirômetros Ópticos e Sensores Infravermelhos:** Medem a radiação térmica emitida pelo corpo sem necessidade de contato físico, amplamente usados em triagens hospitalares e siderurgia.
- **Meteorologia Internacional:** Previsões do tempo nos países anglo-saxões (EUA) utilizam Fahrenheit, exigindo conversão direta por viajantes e modelos de aviação civil.

## 8. Exercícios de Fixação
1. Um turista brasileiro em Chicago consulta a previsão do tempo e observa a marca de \( 50^\circ\text{F} \). Converta esse valor para graus Celsius.
2. Em um laboratório criogênico, uma amostra de hélio líquido está a \( 4\text{ K} \). Qual o valor correspondente na escala Celsius?
3. Um cientista cria uma escala arbitrária \( X \), onde o ponto de fusão do gelo marca \( -10^\circ\text{X} \) e o de ebulição da água marca \( 140^\circ\text{X} \). Se um termômetro marcar \( 20^\circ\text{C} \), quanto registrará na escala \( X \)?

## 9. Correção Comentada dos Exercícios
1. \( \frac{\theta_C}{5} = \frac{50 - 32}{9} = \frac{18}{9} = 2 \implies \theta_C = 10^\circ\text{C} \).
2. \( T_K = \theta_C + 273 \implies 4 = \theta_C + 273 \implies \theta_C = -269^\circ\text{C} \).
3. Segmentos proporcionais: \( \frac{\theta_X - (-10)}{140 - (-10)} = \frac{\theta_C - 0}{100 - 0} \implies \frac{\theta_X + 10}{150} = \frac{20}{100} = \frac{1}{5} \).
   Multiplicando: \( \theta_X + 10 = 30 \implies \theta_X = 20^\circ\text{X} \).

## 10. Desafio de Domínio
Dois termômetros de vidro idênticos, um preenchido com álcool tingido e outro com mercúrio, são calibrados rigorosamente nos pontos fixos \( 0^\circ\text{C} \) e \( 100^\circ\text{C} \). À temperatura intermediária de \( 50^\circ\text{C} \), eles apresentarão leituras rigorosamente idênticas?
*Pista:* A dilatação térmica dos líquidos obedece a equações estritamente lineares ou existem coeficientes de ordem superior? O mercúrio tem comportamento muito mais linear que o álcool.

## 11. Resumo e Pontos-Chave
- Temperatura = grau de agitação molecular médio.
- Calor = energia térmica em trânsito motivada por \( \Delta T \).
- Equação de conversão: \( \frac{\theta_C}{5} = \frac{\theta_F - 32}{9} = \frac{T_K - 273}{5} \).
- Relação de variações: \( \Delta \theta_C = \Delta T_K \) e \( \Delta \theta_F = 1{,}8 \cdot \Delta \theta_C \).
- Zero Absoluto: \( 0\text{ K} = -273{,}15^\circ\text{C} \).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 1. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
- INEP. *Matriz de Referência do ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Conversão Celsius-Fahrenheit-Kelvin",
                syntax = """\\frac{\\theta_C}{5} = \\frac{\\theta_F - 32}{9} = \\frac{T_K - 273}{5}""",
                description = "Relação fundamental de equivalência entre as três principais escalas termométricas baseadas nos pontos do gelo e do vapor.",
                parametersInfo = """\\theta_C: \\text{temp. em } ^\\circ\\text{C}; \\quad \\theta_F: \\text{temp. em } ^\\circ\\text{F}; \\quad T_K: \\text{temp. absoluta em Kelvin}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Relação entre Variações Térmicas",
                syntax = """\\Delta \\theta_C = \\Delta T_K = \\frac{\\Delta \\theta_F}{1{,}8}""",
                description = "Conversão de amplitudes térmicas: a variação de um grau Celsius tem a mesma dimensão de um Kelvin e equivale a 1,8 graus Fahrenheit.",
                parametersInfo = """\\Delta \\theta_C, \\Delta T_K, \\Delta \\theta_F: \\text{variações de temperatura nas respectivas escalas}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Energia Cinética Translacional Média",
                syntax = """E_{c,\\text{méd}} = \\frac{3}{2} k_B T""",
                description = "Interpretação microscópica da temperatura baseada no modelo cinético dos gases ideais.",
                parametersInfo = """k_B = 1{,}38 \\times 10^{-23}\\text{ J/K} \\text{ (Boltzmann)}; \\quad T: \\text{temperatura absoluta (K)}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Conversão Direta entre Escalas com Ponto de Fusão Corpóreo",
                codeOrFormula = """\\frac{36{,}5}{5} = \\frac{\\theta_F - 32}{9} \\implies 7{,}3 \\cdot 9 = \\theta_F - 32 \\implies \\theta_F = 97{,}7^\\circ\\text{F}""",
                explanation = "A temperatura média basal do corpo humano de 36,5 °C corresponde a 97,7 °F nos países que adotam a escala anglo-saxônica."
            ),
            LessonExample(
                title = "Construção de Escala Arbitrária Linear",
                codeOrFormula = """\\frac{Y - Y_{\\text{gelo}}}{Y_{\\text{vapor}} - Y_{\\text{gelo}}} = \\frac{\\theta_C}{100}""",
                explanation = "A determinação de qualquer escala desconhecida baseia-se na constância da razão linear entre segmentos de dilatação."
            )
        ),
        goldTip = "Cuidado com variações de temperatura no ENEM: se o enunciado diz que a temperatura de um reator aumentou em 50 °C, a variação na escala Kelvin foi de 50 K (e NÃO 50 + 273 = 323 K!). Só some 273 para temperaturas pontuais, nunca para intervalos de variação!"
    )

    // =========================================================================
    // AULA fis_m3_a2: Dilatação Térmica dos Sólidos e Líquidos
    // =========================================================================
    private val bundleFisM3A2 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Quase todas as substâncias puras e compostas na natureza expandem seu volume quando sua temperatura aumenta e se contraem quando resfriadas. Esse fenômeno, denominado **Dilatação Térmica**, é a consequência direta do aumento da amplitude média de vibração dos átomos em torno de suas posições de equilíbrio na rede cristalina. Na Engenharia e no cotidiano cobrado no ENEM, a dilatação explica a existência de juntas de dilatação em pontes e trilhos de trens, a curvatura de lâminas bimetálicas em termostatos de ferros de passar e o surpreendente comportamento anômalo da água, responsável pela manutenção da vida aquática em lagos congelados durante invernos rigorosos.

## 2. Objetivos de Aprendizagem
- Compreender a origem microscópica da dilatação a partir da assimetria do poço de energia potencial interatômica.
- Deduzir e calcular a **Dilatação Linear** dos sólidos: \( \Delta L = L_0 \cdot \alpha \cdot \Delta T \).
- Deduzir e aplicar a **Dilatação Superficial** (\( \Delta A = A_0 \cdot \beta \cdot \Delta T \)) e a **Dilatação Volumétrica** (\( \Delta V = V_0 \cdot \gamma \cdot \Delta T \)).
- Compreender a relação proporcional entre os coeficientes de dilatação: \( \frac{\alpha}{1} = \frac{\beta}{2} = \frac{\gamma}{3} \).
- Compreender o comportamento das cavidades e orifícios em placas aquecidas (dilatação de orifícios).
- Analisar a dilatação aparente de líquidos em recipientes sólidos (\( \gamma_{\text{real}} = \gamma_{\text{aparente}} + \gamma_{\text{recipiente}} \)).
- Dominar com profundidade a **Dilatação Anômala da Água** entre \( 0^\circ\text{C} \) e \( 4^\circ\text{C} \) e suas implicações biológicas e climáticas.

## 3. Pré-requisitos
- Termometria e escalas termométricas (Física, Aula fis_m3_a1).
- Geometria Espacial: cálculo de áreas e volumes (Matemática Básica, Aula mb_l11).

## 4. Explicação Teórica Aprofundada

### 1. Origem Microscópica da Dilatação
Os átomos de um sólido estão ligados por forças interatômicas análogas a molas microscópicas. A curva de energia potencial interatômica \( U(r) \) em função da distância \( r \) **não é simétrica**: à medida que a energia térmica aumenta, a distância média entre os átomos se desloca progressivamente para valores maiores. Se a curva fosse perfeitamente simétrica (como em um oscilador harmônico ideal simples), o corpo aquecido vibraria com maior amplitude, mas o comprimento médio permaneceria constante. A assimetria do potencial interatômico é a causa física real da dilatação térmica.

### 2. Dilatação Linear dos Sólidos
Quando uma dimensão (comprimento) predomina sobre as outras duas (como em fios, barras, cabos e trilhos):
\[ \Delta L = L_0 \cdot \alpha \cdot \Delta T \]
\[ L(T) = L_0 (1 + \alpha \cdot \Delta T) \]
- \( L_0 \): comprimento inicial (\( \text{m} \)).
- \( \alpha \): coeficiente de dilatação térmica linear do material (\( ^\circ\text{C}^{-1} \) ou \( \text{K}^{-1} \)).
- \( \Delta T \): variação de temperatura (\( T - T_0 \)).

### 3. Dilatação Superficial e Volumétrica
- **Superficial (placas, chapas):**
  \[ \Delta A = A_0 \cdot \beta \cdot \Delta T \quad \text{onde } \beta \approx 2\alpha \]
- **Volumétrica (blocos sólidos, esferas e líquidos):**
  \[ \Delta V = V_0 \cdot \gamma \cdot \Delta T \quad \text{onde } \gamma \approx 3\alpha \]

### 4. Dilatação de Cavidades e Orifícios
Uma regra mandatória da termodinâmica: **um orifício ou cavidade em um corpo sólido dilata-se exatamente como se estivesse preenchido pelo mesmo material da parede circunvizinha.**
Se uma chapa de aço com um furo circular no centro for aquecida, a área do furo **aumenta**. O orifício não é "estrangulado" pela dilatação da chapa; ele se expande proporcionalmente à chapa inteira.

### 5. Lâmina Bimetálica
Formada por duas tiras metálicas de coeficientes de dilatação distintos (\( \alpha_A > \alpha_B \)) rebitadas ou soldadas face a face.
- Ao ser aquecida, a lâmina de maior coeficiente (\( A \)) expande-se mais que a de menor (\( B \)), forçando o conjunto a **curvar-se para o lado de \( B \)** (o material de maior coeficiente fica na face convexa, externa).
- Ao ser resfriada, \( A \) contrai-se mais rapidamente, curvando a lâmina para o lado de \( A \).
- Aplicação no ENEM: termostatos, relés térmicos e disjuntores bimetálicos.

### 6. Dilatação Real vs. Dilatação Aparente dos Líquidos
Como líquidos não possuem forma própria, precisam estar contidos em recipientes sólidos. Ao aquecer o conjunto:
1. O recipiente sólido dilata (\( \Delta V_{\text{rec}} = V_0 \cdot \gamma_{\text{rec}} \cdot \Delta T \)).
2. O líquido dilata mais intensamente que o sólido (\( \Delta V_{\text{real}} = V_0 \cdot \gamma_{\text{líq}} \cdot \Delta T \)).
3. O volume transbordado mede a **dilatação aparente**:
   \[ \Delta V_{\text{real}} = \Delta V_{\text{aparente}} + \Delta V_{\text{recipiente}} \]
   \[ \gamma_{\text{real}} = \gamma_{\text{aparente}} + \gamma_{\text{recipiente}} \]

### 7. Comportamento Anômalo da Água
Entre \( 0^\circ\text{C} \) e \( 4^\circ\text{C} \), a água apresenta uma notável exceção ao padrão da matéria:
- Ao ser aquecida de \( 0^\circ\text{C} \) a \( 4^\circ\text{C} \), o volume da água **diminui** e sua densidade **aumenta**.
- A \( 4^\circ\text{C} \), a água pura sob \( 1\text{ atm} \) atinge seu **volume mínimo** e sua **densidade máxima** (\( d \approx 1{,}000\text{ g/cm}^3 \)).
- Acima de \( 4^\circ\text{C} \), volta a dilatar-se normalmente.
- **Explicação molecular:** No gelo (\( 0^\circ\text{C} \)), as moléculas formam uma rede hexagonal aberta por ligações de hidrogênio com grandes espaços vazios. Ao fundir e aquecer até \( 4^\circ\text{C} \), essas ligações hexagonais colapsam parcialmente, permitindo que as moléculas se aproximem mais densamente antes que a agitação térmica supere essa aproximação.
- **Consequência biológica:** Em lagos temperados e polares no inverno, a água resfriada a \( 4^\circ\text{C} \) torna-se mais densa e afunda. Quando a superfície atinge \( 0^\circ\text{C} \), congela, formando uma camada de gelo isolante térmica na superfície. O fundo do lago permanece líquido a \( 4^\circ\text{C} \), preservando a fauna e flora aquática.

## 5. Definições e Conceitos
- **Junta de Dilatação:** Espaçamento calculado deixado entre blocos de concreto ou seções de trilhos para evitar flambagem mecânica decorrente da expansão térmica.
- **Tensão Térmica:** Tensão mecânica (\( \sigma = E \cdot \alpha \cdot \Delta T \)) originada quando um material é impedido rigidamente de dilatar ou contrair.
- **Vidro Pirex (Borossilicato):** Vidro especial cujo coeficiente de dilatação térmica linear é cerca de 3 vezes menor que o vidro comum, tornando-o imune a choques térmicos mecânicos.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Cálculo de Dilatação Linear de Trilho
Um trilho de aço de \( 20\text{ m} \) de comprimento é instalado a uma temperatura de \( 10^\circ\text{C} \). Durante o pico do verão, o trilho atinge \( 50^\circ\text{C} \). Sabendo que \( \alpha_{\text{aço}} = 1{,}2 \times 10^{-5}\ ^\circ\text{C}^{-1} \), qual foi a dilatação sofrida pelo trilho?
- **Resolução:**
  1. Identificação dos dados: \( L_0 = 20\text{ m} \), \( T_0 = 10^\circ\text{C} \), \( T = 50^\circ\text{C} \implies \Delta T = 40^\circ\text{C} \).
  2. Fórmula: \( \Delta L = L_0 \cdot \alpha \cdot \Delta T \).
  3. Cálculo:
     \[ \Delta L = 20 \cdot (1{,}2 \times 10^{-5}) \cdot 40 = 20 \cdot 40 \cdot 1{,}2 \times 10^{-5} \]
     \[ \Delta L = 800 \cdot 1{,}2 \times 10^{-5} = 960 \times 10^{-5}\text{ m} = 9{,}6 \times 10^{-3}\text{ m} = 9{,}6\text{ mm} \]
  - Resposta: O trilho expandiu-se \( 9{,}6\text{ mm} \).

### Exemplo 2: Dilatação de um Recipiente e Transbordamento
Um frasco de vidro de volume interno \( 500\text{ cm}^3 \) está completamente cheio de álcool a \( 20^\circ\text{C} \). O conjunto é aquecido até \( 70^\circ\text{C} \). Dados: \( \gamma_{\text{álcool}} = 1{,}1 \times 10^{-3}\ ^\circ\text{C}^{-1} \) e \( \gamma_{\text{vidro}} = 3 \times 10^{-5}\ ^\circ\text{C}^{-1} \). Qual o volume de álcool que transborda?
- **Resolução:**
  1. Variação de temperatura: \( \Delta T = 70 - 20 = 50^\circ\text{C} \).
  2. O coeficiente aparente de dilatação do álcool é:
     \[ \gamma_{\text{ap}} = \gamma_{\text{álcool}} - \gamma_{\text{vidro}} = 1{,}1 \times 10^{-3} - 0{,}03 \times 10^{-3} = 1{,}07 \times 10^{-3}\ ^\circ\text{C}^{-1} \]
  3. O volume transbordado é a dilatação aparente:
     \[ \Delta V_{\text{transb}} = V_0 \cdot \gamma_{\text{ap}} \cdot \Delta T = 500 \cdot (1{,}07 \times 10^{-3}) \cdot 50 \]
     \[ \Delta V_{\text{transb}} = 25000 \cdot 1{,}07 \times 10^{-3} = 26{,}75\text{ cm}^3 \]
  - Resposta: Transbordam \( 26{,}75\text{ cm}^3 \) de álcool.

## 7. Aplicações Práticas e Modelagem
- **Pontes e Viadutos de Concreto:** Construídos com dentes metálicos móveis intercalados com elastômeros para absorver variações térmicas diárias de dezenas de centímetros.
- **Obturações Odontológicas:** As resinas e amálgamas odontológicos devem possuir coeficiente de dilatação rigorosamente idêntico ao do esmalte dentário para evitar microfraturas ou descolamentos causados por ingestão de café quente ou sorvete gelado.
- **Engrenagens e Eixos (Ajuste por Interferência Térmica):** Para montar um rolamento em um eixo com aperto absoluto, aquece-se o anel externo e resfria-se o eixo em nitrogênio líquido antes do encaixe.

## 8. Exercícios de Fixação
1. Uma chapa quadrada de alumínio de lado \( 1\text{ m} \) possui um furo circular de raio \( 10\text{ cm} \) no centro. Ao ser aquecida em \( 50^\circ\text{C} \), a área do orifício irá: (Aumentar / Diminuir / Permanecer inalterada)? Justifique quantitativamente.
2. Sabendo que o coeficiente de dilatação linear do cobre é \( 17 \times 10^{-6}\ ^\circ\text{C}^{-1} \), determine o seu coeficiente de dilatação superficial (\( \beta \)) e volumétrico (\( \gamma \)).
3. Um frasco cheio até a borda com mercúrio transborda \( 2\text{ cm}^3 \) quando aquecido. Sabendo que o recipiente dilatou \( 0{,}5\text{ cm}^3 \), qual foi a dilatação real absoluta sofrida pelo mercúrio?

## 9. Correção Comentada dos Exercícios
1. A área do orifício **aumenta**. O orifício dilata exatamente como se fosse uma chapa de alumínio sólida de mesmo diâmetro: \( \Delta A_{\text{furo}} = A_{0,\text{furo}} \cdot \beta \cdot \Delta T > 0 \).
2. \( \beta = 2\alpha = 2 \cdot 17 \times 10^{-6} = 34 \times 10^{-6}\ ^\circ\text{C}^{-1} \); \( \gamma = 3\alpha = 3 \cdot 17 \times 10^{-6} = 51 \times 10^{-6}\ ^\circ\text{C}^{-1} \).
3. Pela conservação volumétrica: \( \Delta V_{\text{real}} = \Delta V_{\text{aparente}} + \Delta V_{\text{recipiente}} = 2 + 0{,}5 = 2{,}5\text{ cm}^3 \).

## 10. Desafio de Domínio
Considere um anel metálico de bronze com um coeficiente de dilatação linear \( \alpha_{\text{bronze}} = 19 \times 10^{-6}\ ^\circ\text{C}^{-1} \) e uma esfera de aço de coeficiente \( \alpha_{\text{aço}} = 12 \times 10^{-6}\ ^\circ\text{C}^{-1} \). À temperatura ambiente de \( 20^\circ\text{C} \), a esfera não passa através do orifício do anel por uma diferença micrométrica. Para fazer a esfera atravessar o anel, devemos aquecer ambos à mesma temperatura ou resfriar ambos? Demonstre matematicamente.
*Pista:* Avalie a taxa de variação do diâmetro interno do anel em comparação ao diâmetro da esfera em função de \( \Delta T \).

## 11. Resumo e Pontos-Chave
- Dilatação Linear: \( \Delta L = L_0 \alpha \Delta T \).
- Dilatação Superficial: \( \Delta A = A_0 \beta \Delta T \) (\( \beta = 2\alpha \)).
- Dilatação Volumétrica: \( \Delta V = V_0 \gamma \Delta T \) (\( \gamma = 3\alpha \)).
- Orifícios dilatam-se como se fossem feitos do mesmo material sólido que os envolve.
- Dilatação de líquidos: \( \gamma_{\text{real}} = \gamma_{\text{aparente}} + \gamma_{\text{recipiente}} \).
- Água anômala: entre \( 0^\circ\text{C} \) e \( 4^\circ\text{C} \) contrai ao aquecer; densidade máxima a \( 4^\circ\text{C} \).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
- NUSSENZVEIG, H. Moysés. *Curso de Física Básica: Fluidos, Ondas e Calor*. Vol. 2. 5. ed. Blucher, 2014.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Dilatação Térmica Linear",
                syntax = """\\Delta L = L_0 \\cdot \\alpha \\cdot \\Delta T""",
                description = "Expressão fundamental da expansão unidimensional de sólidos isotrópicos sob variação de temperatura.",
                parametersInfo = """L_0: \\text{comprimento inicial (m)}; \\quad \\alpha: \\text{coeficiente linear } (^\\circ\\text{C}^{-1}); \\quad \\Delta T: \\text{variação térmica } (^\\circ\\text{C})"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Relação Proporcional entre Coeficientes",
                syntax = """\\frac{\\alpha}{1} = \\frac{\\beta}{2} = \\frac{\\gamma}{3}""",
                description = "Isotropia geométrica relacionando os coeficientes linear, superficial e volumétrico.",
                parametersInfo = """\\alpha: \\text{linear}; \\quad \\beta = 2\\alpha: \\text{superficial}; \\quad \\gamma = 3\\alpha: \\text{volumétrico}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Dilatação Real de Líquidos",
                syntax = """\\Delta V_{\\text{real}} = \\Delta V_{\\text{aparente}} + \\Delta V_{\\text{recipiente}}""",
                description = "Balanço volumétrico corrigindo a expansão do líquido pelo transbordamento somado à dilatação do frasco.",
                parametersInfo = """\\Delta V_{\\text{ap}}: \\text{volume que transborda}; \\quad \\Delta V_{\\text{rec}}: \\text{aumento do volume interno do recipiente}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Dilatação de Placa Metálica com Furo Central",
                codeOrFormula = """\\Delta A_{\\text{orifício}} = A_{0,\\text{orifício}} \\cdot \\beta \\cdot \\Delta T""",
                explanation = "A cavidade interna expande na mesma proporção como se fosse preenchida pelo próprio metal da chapa circundante."
            ),
            LessonExample(
                title = "Comportamento da Lâmina Bimetálica",
                codeOrFormula = """\\alpha_A > \\alpha_B \\implies \\Delta L_A > \\Delta L_B \\implies \\text{curvatura côncava voltada para } B""",
                explanation = "O metal de maior expansão alonga mais e fica na face externa convexa, fazendo a lâmina encurvar em direção ao metal de menor expansão."
            )
        ),
        goldTip = "No ENEM, guarde com clareza o comportamento da água: a densidade máxima da água líquida ocorre a 4 °C. Se uma massa de água a 0 °C for aquecida até 4 °C, seu volume diminui e sua densidade aumenta. Se for aquecida de 4 °C até 20 °C, seu volume aumenta e sua densidade diminui!"
    )

    // =========================================================================
    // AULA fis_m3_a3: Calorimetria - Calor Sensível e Calor Latente
    // =========================================================================
    private val bundleFisM3A3 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A Calorimetria é a área da Termologia encarregada de quantificar e medir as trocas de calor entre corpos e sistemas físicos, associadas tanto a alterações de temperatura quanto a transições de fase da matéria. Trata-se de um dos temas de maior peso histórico e frequência na prova de Ciências da Natureza do ENEM. Da eficiência de aquecedores solares e chuveiros elétricos, ao suor humano como mecanismo termorregulador por evaporação da água, a calorimetria fundamenta o princípio da conservação da energia aplicada a sistemas térmicos isolados.

## 2. Objetivos de Aprendizagem
- Diferenciar conceitualmente **Calor Sensível** (mudança de temperatura) de **Calor Latente** (mudança de estado físico).
- Definir **Capacidade Térmica** (\( C \)) e **Calor Específico Sensível** (\( c \)), interpretando unidades de medida (\( \text{cal}/(\text{g}\cdot^\circ\text{C}) \) e \( \text{J}/(\text{kg}\cdot\text{K}) \)).
- Aplicar a Equação Fundamental da Calorimetria: \( Q = m \cdot c \cdot \Delta T \).
- Deduzir e aplicar a equação do Calor Latente: \( Q = m \cdot L \).
- Interpretar com rigor analítico as **Curvas de Aquecimento e Resfriamento** de substâncias puras (patamares isotérmicos de mudança de fase).
- Resolver problemas de balanço térmico em **Calorímetros Ideais e Reais**: \( \sum Q = 0 \).
- Analisar a relação entre Potência Térmica, Calor e Tempo: \( P = \frac{Q}{\Delta t} \).

## 3. Pré-requisitos
- Termometria e escalas termométricas (Física, Aula fis_m3_a1).
- Unidades de energia: relação entre Caloria e Joule (\( 1\text{ cal} \approx 4{,}18\text{ J} \)) (Mecânica, Aula fis_m1_a6).

## 4. Explicação Teórica Aprofundada

### 1. Calor Específico Sensível e Capacidade Térmica
- **Calor Específico Sensível (\( c \)):** É a quantidade de calor necessária para que uma unidade de massa (\( 1\text{ g} \) ou \( 1\text{ kg} \)) de uma substância altere sua temperatura em \( 1^\circ\text{C} \) (ou \( 1\text{ K} \)):
  - Água líquida: \( c_{\text{água}} = 1{,}0\text{ cal}/(\text{g}\cdot^\circ\text{C}) \approx 4180\text{ J}/(\text{kg}\cdot\text{K}) \) (um dos maiores valores na natureza).
  - Areia/Solos: \( c_{\text{areia}} \approx 0{,}20\text{ cal}/(\text{g}\cdot^\circ\text{C}) \).
  - Metais (ferro, cobre): \( c \approx 0{,}09\text{ a } 0{,}11\text{ cal}/(\text{g}\cdot^\circ\text{C}) \).
- **Implicação climática (Maritimidade vs. Continentalidade):** O altíssimo calor específico da água faz dos oceanos grandes reguladores térmicos. Regiões litorâneas apresentam amplitude térmica diária reduzida (a água aquece e resfria devagar); desertos apresentam calor extremo de dia e frio intenso à noite.
- **Capacidade Térmica (\( C \)):** É uma propriedade do corpo como um todo (depende da massa e da substância):
  \[ C = \frac{Q}{\Delta T} = m \cdot c \]

### 2. Equação Fundamental da Calorimetria (Calor Sensível)
Quando um corpo recebe ou cede calor alterando sua temperatura sem sofrer mudança de estado físico:
\[ Q = m \cdot c \cdot \Delta T \]
- Se \( \Delta T > 0 \implies Q > 0 \) (calor recebido/absorvido, processo endotérmico).
- Se \( \Delta T < 0 \implies Q < 0 \) (calor cedido/liberado, processo exotérmico).

### 3. Calor Latente e Mudanças de Fase
Durante a mudança de estado físico de uma substância pura sob pressão constante, **a temperatura permanece estritamente invariável** (patamar isotérmico). O calor transferido atua exclusivamente na quebra ou formação de ligações intermoleculares:
\[ Q = m \cdot L \]
- \( L_F \): Calor latente de fusão (para a água: \( +80\text{ cal/g} \); solidificação: \( -80\text{ cal/g} \)).
- \( L_V \): Calor latente de vaporização (para a água: \( +540\text{ cal/g} \); condensação: \( -540\text{ cal/g} \)).
- Note que evaporar \( 1\text{ g} \) de água exige \( 540\text{ cal} \), enquanto aquecer \( 1\text{ g} \) de água de \( 0^\circ\text{C} \) a \( 100^\circ\text{C} \) exige apenas \( 100\text{ cal} \). Essa enorme energia latente explica por que o suor é o mecanismo de resfriamento corporal mais eficiente do ser humano.

### 4. Princípio de Trocas de Calor em Sistemas Termicamente Isolados
Em um calorímetro ideal (que não troca calor com o meio externo e tem capacidade térmica desprezível):
\[ \sum Q = 0 \implies Q_1 + Q_2 + \dots + Q_n = 0 \]
Se o calorímetro for **real**, sua própria parede interna absorve calor:
\[ Q_{\text{calorímetro}} = C_{\text{cal}} \cdot (T_{\text{eq}} - T_0) \]
onde \( C_{\text{cal}} \) é a capacidade térmica do calorímetro (frequentemente expressa pelo seu "equivalente em água").

### 5. Potência Térmica
A taxa temporal de fluxo de calor:
\[ P = \frac{Q}{\Delta t} \]
No SI, expressa em Watts (\( \text{W} = \text{J/s} \)). Ao cruzar com a eletrodinâmica (chuveiros, cafeteiras elétricas):
\[ P = U \cdot i = \frac{m \cdot c \cdot \Delta T}{\Delta t} \]

## 5. Definições e Conceitos
- **Caloria (cal):** Historicamente definida como a quantidade de calor para elevar a temperatura de \( 1\text{ g} \) de água pura de \( 14{,}5^\circ\text{C} \) a \( 15{,}5^\circ\text{C} \) a \( 1\text{ atm} \). \( 1\text{ cal} = 4{,}186\text{ J} \).
- **Calorímetro:** Recipiente com paredes adiabáticas (isolantes térmicas perfeitas) utilizado para medir trocas de calor entre substâncias.
- **Sublimação:** Passagem direta do estado sólido para o gasoso sem passar pelo líquido (ex.: gelo seco / \( \text{CO}_2 \) sólido, naftalina).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Equilíbrio Térmico sem Mudança de Fase
Um bloco de cobre de \( 200\text{ g} \) a \( 100^\circ\text{C} \) é colocado em um calorímetro ideal que contém \( 300\text{ g} \) de água líquida a \( 20^\circ\text{C} \). Dados: \( c_{\text{água}} = 1{,}0\text{ cal}/(\text{g}\cdot^\circ\text{C}) \) e \( c_{\text{Cu}} = 0{,}09\text{ cal}/(\text{g}\cdot^\circ\text{C}) \). Determine a temperatura final de equilíbrio térmico \( T_{\text{eq}} \).
- **Resolução:**
  1. Sistema isolado: \( Q_{\text{cobre}} + Q_{\text{água}} = 0 \).
  2. \( Q_{\text{cobre}} = m_{\text{Cu}} \cdot c_{\text{Cu}} \cdot (T_{\text{eq}} - 100) = 200 \cdot 0{,}09 \cdot (T_{\text{eq}} - 100) = 18(T_{\text{eq}} - 100) = 18 T_{\text{eq}} - 1800 \).
  3. \( Q_{\text{água}} = m_{\text{água}} \cdot c_{\text{água}} \cdot (T_{\text{eq}} - 20) = 300 \cdot 1{,}0 \cdot (T_{\text{eq}} - 20) = 300 T_{\text{eq}} - 6000 \).
  4. Somando as equações:
     \[ (18 T_{\text{eq}} - 1800) + (300 T_{\text{eq}} - 6000) = 0 \]
     \[ 318 T_{\text{eq}} = 7800 \implies T_{\text{eq}} = \frac{7800}{318} \approx 24{,}5^\circ\text{C} \]
  - Resposta: A temperatura de equilíbrio é de aproximadamente \( 24{,}5^\circ\text{C} \). Observe a pequena variação na temperatura da água (\( +4{,}5^\circ\text{C} \)) frente ao grande resfriamento do cobre (\( -75{,}5^\circ\text{C} \)), decorrente do alto calor específico da água!

### Exemplo 2: Potência de Aquecedor Elétrico e Fusão de Gelo
Um aquecedor de \( 840\text{ W} \) de potência opera com rendimento de \( 100\% \) sobre um bloco de gelo de \( 500\text{ g} \) a \( 0^\circ\text{C} \). Sabendo que \( L_F = 80\text{ cal/g} \) e adotando \( 1\text{ cal} = 4{,}2\text{ J} \), quanto tempo o aquecedor levará para fundir completamente todo o gelo?
- **Resolução:**
  1. Calor necessário para fundir o gelo:
     \[ Q = m \cdot L_F = 500\text{ g} \cdot 80\text{ cal/g} = 40000\text{ cal} \]
  2. Convertendo para Joules:
     \[ Q = 40000 \cdot 4{,}2\text{ J} = 168000\text{ J} \]
  3. Relacionando com a potência:
     \[ P = \frac{Q}{\Delta t} \implies 840 = \frac{168000}{\Delta t} \implies \Delta t = \frac{168000}{840} = 200\text{ segundos} \]
  - Resposta: O aquecedor levará \( 200\text{ s} \) (3 minutos e 20 segundos) para fundir o gelo.

## 7. Aplicações Práticas e Modelagem
- **Termorregulação Humana:** O suor dissipa cerca de \( 540\text{ cal} \) para cada grama de água que evapora da pele. Em dias de alta umidade relativa do ar, o suor não evapora com facilidade, provocando sensação térmica de abafamento e risco de hipertermia.
- **Brisa Marítima e Terrestre:** A diferença de calor específico entre o solo e o oceano cria gradientes térmicos que movem as massas de ar nas praias durante o dia e a noite.
- **Dimensionamento de Chuveiros Elétricos:** No modo "inverno", a resistência elétrica diminui para aumentar a corrente e a potência dissipada, garantindo elevação de temperatura suficiente para a vazão de água fria da tubulação.

## 8. Exercícios de Fixação
1. Calcule a quantidade de calor total necessária para transformar \( 100\text{ g} \) de gelo a \( -10^\circ\text{C} \) em vapor de água a \( 100^\circ\text{C} \). (Dados: \( c_{\text{gelo}} = 0{,}5\text{ cal}/(\text{g}\cdot^\circ\text{C}) \), \( L_F = 80\text{ cal/g} \), \( c_{\text{água}} = 1{,}0\text{ cal}/(\text{g}\cdot^\circ\text{C}) \), \( L_V = 540\text{ cal/g} \)).
2. Um calorímetro de capacidade térmica \( C = 50\text{ cal/}^\circ\text{C} \) contém \( 200\text{ g} \) de água a \( 25^\circ\text{C} \). Um corpo de massa \( 100\text{ g} \) a \( 80^\circ\text{C} \) é inserido no calorímetro, atingindo o equilíbrio a \( 30^\circ\text{C} \). Qual o calor específico do corpo?
3. Por que a queimadura causada por vapor d'água a \( 100^\circ\text{C} \) costuma provocar lesões teciduais muito mais severas do que a queimadura por água líquida fervente a \( 100^\circ\text{C} \)?

## 9. Correção Comentada dos Exercícios
1. Etapas do processo:
   - Aquecer gelo de \( -10^\circ\text{C} \) a \( 0^\circ\text{C} \): \( Q_1 = 100 \cdot 0{,}5 \cdot 10 = 500\text{ cal} \).
   - Fundir gelo a \( 0^\circ\text{C} \): \( Q_2 = 100 \cdot 80 = 8000\text{ cal} \).
   - Aquecer água de \( 0^\circ\text{C} \) a \( 100^\circ\text{C} \): \( Q_3 = 100 \cdot 1{,}0 \cdot 100 = 10000\text{ cal} \).
   - Vaporizar água a \( 100^\circ\text{C} \): \( Q_4 = 100 \cdot 540 = 54000\text{ cal} \).
   - Total: \( Q_{\text{total}} = 500 + 8000 + 10000 + 54000 = 72500\text{ cal} \).
2. Balanço: \( Q_{\text{água}} + Q_{\text{cal}} + Q_{\text{corpo}} = 0 \).
   \( 200 \cdot 1 \cdot (30 - 25) + 50 \cdot (30 - 25) + 100 \cdot c \cdot (30 - 80) = 0 \).
   \( 1000 + 250 - 5000 c = 0 \implies 5000 c = 1250 \implies c = 0{,}25\text{ cal}/(\text{g}\cdot^\circ\text{C}) \).
3. Porque o vapor a \( 100^\circ\text{C} \), ao entrar em contato com a pele mais fria, primeiro se condensa liberando todo o seu imenso calor latente (\( 540\text{ cal/g} \)) mantendo-se a \( 100^\circ\text{C} \), e somente após condensado resfria-se como água líquida, transferindo uma quantidade de energia drasticamente superior à da água líquida pré-existente.

## 10. Desafio de Domínio
Em um calorímetro ideal misturam-se \( 200\text{ g} \) de gelo a \( 0^\circ\text{C} \) com \( 100\text{ g} \) de vapor d'água a \( 100^\circ\text{C} \). Qual será a composição final do sistema no equilíbrio térmico e sua temperatura final?
*Pista:* Verifique se o calor liberado pela condensação de todo o vapor é maior ou menor que o calor necessário para fundir o gelo e aquecê-lo até \( 100^\circ\text{C} \).

## 11. Resumo e Pontos-Chave
- Calor Sensível: \( Q = m \cdot c \cdot \Delta T \) (provoca variação de temperatura).
- Calor Latente: \( Q = m \cdot L \) (provoca mudança de estado sem variar a temperatura).
- Água: alto calor específico (\( c = 1\text{ cal}/(\text{g}\cdot^\circ\text{C}) \)), agindo como moderadora térmica (maritimidade).
- Balanço térmico em sistemas isolados: \( \sum Q = 0 \).
- Potência: \( P = \frac{Q}{\Delta t} \) (\( \text{W} = \text{J/s} \)); conversão: \( 1\text{ cal} \approx 4{,}18\text{ J} \).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 1. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Fundamental da Calorimetria",
                syntax = """Q = m \\cdot c \\cdot \\Delta T""",
                description = "Quantidade de calor sensível absorvida ou cedida por um corpo associada a uma variação de temperatura.",
                parametersInfo = """m: \\text{massa (g ou kg)}; \\quad c: \\text{calor específico}; \\quad \\Delta T: \\text{variação térmica}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Equação do Calor Latente",
                syntax = """Q = m \\cdot L""",
                description = "Calor transferido durante uma transição de fase isotérmica sob pressão constante.",
                parametersInfo = """m: \\text{massa que muda de fase}; \\quad L: \\text{calor latente específico (cal/g ou J/kg)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Conservação de Energia em Calorímetros",
                syntax = """\\sum Q = 0 \\implies Q_1 + Q_2 + \\dots + Q_n = 0""",
                description = "Princípio da conservação de calor em sistemas termicamente isolados do meio ambiente.",
                parametersInfo = """Q_k: \\text{calor trocado por cada participante (positivo se absorvido, negativo se cedido)}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Aquecimento de Chuveiro Elétrico e Vazão",
                codeOrFormula = """P = \\frac{m \\cdot c \\cdot \\Delta T}{\\Delta t} \\implies P = Z \\cdot d \\cdot c \\cdot \\Delta T""",
                explanation = "A potência elétrica dissipada no resistor do chuveiro relaciona-se diretamente com a vazão volumétrica Z da água e o ganho térmico obtido."
            ),
            LessonExample(
                title = "Determinação do Calor Específico por Mistura",
                codeOrFormula = """m_1 c_1 (T_{\\text{eq}} - T_1) + m_2 c_2 (T_{\\text{eq}} - T_2) = 0""",
                explanation = "Ao inserir um metal quente em água fria, o calor cedido pelo metal é integralmente absorvido pela água, revelando o calor específico desconhecido."
            )
        ),
        goldTip = "Nas questões do ENEM que envolvem chuveiros ou aquecedores com potência em Watts (W) e calorias (cal), NUNCA misture as unidades sem converter! Lembre-se: 1 W = 1 J/s. Converta calorias para Joules multiplicando pelo fator fornecido (geralmente 1 cal = 4,2 J ou 4,18 J) antes de igualar com a potência!"
    )

    // =========================================================================
    // AULA fis_m3_a4: Processos de Transmissão de Calor e Equilíbrio Térmico
    // =========================================================================
    private val bundleFisM3A4 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O calor nunca se propaga no vácuo ou na matéria de forma estática; ele se transfere ativamente impulsionado por gradientes de temperatura. O estudo dos **Processos de Transmissão de Calor** (propagação térmica) é um dos pilares mais aplicados da Física no cotidiano humano e um dos favoritos absolutos nas questões contextualizadas do ENEM. Ele explica desde o isolamento térmico de edifícios sustentáveis, a brisa que sopra no litoral, o funcionamento de painéis solares e garrafas térmicas (frascos de Dewar), até o aquecimento global impulsionado pela intensificação do **Efeito Estufa**.

## 2. Objetivos de Aprendizagem
- Identificar e diferenciar com clareza os três mecanismos fundamentais de propagação do calor: **Condução**, **Convecção** e **Irradiação**.
- Compreender e aplicar a **Lei de Fourier** para a condução térmica unidimensional em regime estacionário: \( \Phi = \frac{k \cdot A \cdot \Delta T}{L} \).
- Analisar a convecção térmica como transporte macroscópico de matéria impulsionado por diferenças de densidade em fluidos.
- Compreender a irradiação térmica como o único processo capaz de propagar energia térmica através do vácuo via ondas eletromagnéticas (radiação infravermelha).
- Dominar a Lei de Stefan-Boltzmann e o conceito de corpo negro.
- Explicar os elementos estruturais de uma **Garrafa Térmica (Frasco de Dewar)** e como cada barreira bloqueia um processo de propagação específico.
- Analisar o **Efeito Estufa Natural** vs. sua intensificação antrópica sob a ótica dos espectros eletromagnéticos e absorção molecular.

## 3. Pré-requisitos
- Conceito de Calor e Temperatura (Física, Aula fis_m3_a1).
- Ondas eletromagnéticas e espectro de frequências (Física, Aula fis_m4_a1).

## 4. Explicação Teórica Aprofundada

### 1. Condução Térmica
- **Definição:** É a transferência de energia térmica átomo a átomo, molécula a molécula, através de colisões e transferência de energia cinética vibracional, **sem que haja transporte macroscópico de matéria**.
- **Ocorrência:** Predominante em sólidos, especialmente metais (onde os elétrons livres atuam como condutores térmicos ultrarrápidos). É desprezível em gases e impossível no vácuo.
- **Lei de Fourier:** A taxa de fluxo de calor (\( \Phi = \frac{Q}{\Delta t} \)) através de uma parede plana de espessura \( L \) e área transversal \( A \) sob uma diferença de temperatura \( \Delta T \) é dada por:
  \[ \Phi = \frac{k \cdot A \cdot \Delta T}{L} \]
  onde \( k \) é a **condutividade térmica** do material (\( \text{W}/(\text{m}\cdot\text{K}) \)). Materiais com alto \( k \) são condutores térmicos (metais); com baixo \( k \) são isolantes térmicos (madeira, isopor, lã de vidro, ar aprisionado).

### 2. Convecção Térmica
- **Definição:** É a transferência de calor provocada pelo **movimento macroscópico de fluidos** (líquidos e gases), originado por gradientes de densidade criados pelo aquecimento não uniforme.
- **Mecanismo:** Ao ser aquecido, o fluido dilata-se, sua densidade diminui e ele ascende (empuxo favorável). O fluido mais frio das camadas superiores, mais denso, desce ocupando o lugar vago, estabelecendo as chamadas **correntes de convecção**.
- **Ocorrência:** Exclusiva de fluidos. **Não ocorre em sólidos** (moléculas fixas) e **não ocorre no vácuo** (ausência de matéria).
- **Aplicações no ENEM:**
  - *Instalação de ar-condicionado:* No alto das paredes, para que o ar frio (mais denso) desça por gravidade refrigerando a sala inteira.
  - *Congelador de refrigeradores convencionais:* Fica no topo para criar correntes descendentes de ar gelado.
  - *Brisas marítimas e terrestres:* De dia, o sol aquece o solo mais rápido que a água (menor calor específico da terra); o ar sobre a terra sobe e o ar fresco do mar sopra em direção à terra (brisa marítima). À noite, o solo resfria mais rápido que o mar; o ar sobe sobre a água e sopra da terra para o mar (brisa terrestre).

### 3. Irradiação Térmica (Radiação Térmica)
- **Definição:** É a transmissão de calor através de **ondas eletromagnéticas** (principalmente na faixa do infravermelho), emitidas por todo corpo cuja temperatura seja superior ao zero absoluto (\( T > 0\text{ K} \)).
- **Diferencial Único:** **É o único processo que se propaga no vácuo** (é assim que a energia solar viaja através do espaço cósmico até atingir a Terra).
- **Superfícies e Emissividade:** Corpos escuros e foscos são excelentes absorvedores e excelentes emissores de radiação térmica; superfícies claras e polidas/espelhadas são excelentes refletores e péssimos emissores.
- **Lei de Stefan-Boltzmann:** A potência irradiada por unidade de área de um corpo negro é proporcional à quarta potência de sua temperatura absoluta:
  \[ \frac{P}{A} = \sigma \cdot T^4 \]
  onde \( \sigma = 5{,}67 \times 10^{-8}\text{ W}/(\text{m}^2\cdot\text{K}^4) \).

### 4. A Física da Garrafa Térmica (Frasco de Dewar)
Projetada para minimizar todas as três formas de perda ou ganho de calor:
1. **Parede dupla de vidro com vácuo intermediário:** O vácuo impede a **condução** e a **convecção** (pois não há matéria condutora).
2. **Paredes internas e externas espelhadas:** Refletem a radiação infravermelha de volta para o interior (ou para fora), minimizando a **irradiação**.
3. **Tampa de plástico/cortiça vedada:** O isolamento e a vedação impedem que o ar escape, bloqueando a **convecção** com o exterior.

### 5. O Mecanismo Físico do Efeito Estufa
1. A radiação solar incidente é composta majoritariamente por ondas curtas (luz visível e ultravioleta), para as quais os gases da atmosfera terrestre (\( \text{N}_2 \), \( \text{O}_2 \), \( \text{CO}_2 \), vapor d'água) são amplamente transparentes.
2. A superfície da Terra absorve essa radiação visível e se aquece até temperaturas moderadas (cerca de \( 15^\circ\text{C} = 288\text{ K} \)).
3. A Terra reemite essa energia na forma de ondas longas de baixa frequência: **radiação infravermelha térmica**.
4. Certos gases estufa (\( \text{CO}_2 \), \( \text{CH}_4 \), \( \text{H}_2\text{O} \), \( \text{N}_2\text{O} \)) possuem modos vibracionais moleculares que **absorvem intensamente a radiação infravermelha**, reemitindo-a de volta para a superfície em todas as direções, retendo calor na troposfera.

## 5. Definições e Conceitos
- **Condutividade Térmica (\( k \)):** Propriedade intrínseca do material que quantifica a facilidade com que o calor se propaga por condução.
- **Corpo Negro:** Absorvedor e emissor ideal de radiação térmica, cuja absortividade é unitária (\( \alpha = 1 \)).
- **Isolante Térmico:** Material caracterizado por aprisionar microbolhas de ar estático (ar imóvel é um excelente isolante térmico por condução).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Aplicação da Lei de Fourier em Parede de Edifício
Uma parede de tijolos de condutividade térmica \( k = 0{,}8\text{ W}/(\text{m}\cdot^\circ\text{C}) \), espessura de \( 20\text{ cm} \) (\( 0{,}2\text{ m} \)) e área de \( 15\text{ m}^2 \) separa uma sala com temperatura interna mantida a \( 22^\circ\text{C} \) do ambiente externo a \( 37^\circ\text{C} \). Qual é a taxa de fluxo de calor que atravessa a parede?
- **Resolução:**
  1. Identificação dos dados: \( k = 0{,}8\text{ W}/(\text{m}\cdot^\circ\text{C}) \), \( A = 15\text{ m}^2 \), \( L = 0{,}2\text{ m} \), \( \Delta T = 37 - 22 = 15^\circ\text{C} \).
  2. Fórmula da Lei de Fourier:
     \[ \Phi = \frac{k \cdot A \cdot \Delta T}{L} \]
  3. Cálculo:
     \[ \Phi = \frac{0{,}8 \cdot 15 \cdot 15}{0{,}2} = \frac{180}{0{,}2} = 900\text{ W} \]
  - Resposta: A taxa de calor conduzida através da parede é de \( 900\text{ W} \) (\( 900\text{ Joules por segundo} \)).

### Exemplo 2: Mecanismo de Cobertura Térmica de Emergência
Em resgates nas montanhas, equipes médicas cobrem vítimas de hipotermia com mantas aluminizadas reflexivas finíssimas. Qual o princípio físico predominante envolvido?
- **Resolução:**
  - A manta de mylar aluminizado é extremamente espelhada. Ao envolver o paciente, ela reflete a radiação infravermelha emitida pelo próprio corpo da vítima de volta para a sua pele, bloqueando a perda de calor por **irradiação térmica**, que representa mais de \( 60\% \) das perdas térmicas do corpo humano descoberto em ambientes frios.

## 7. Aplicações Práticas e Modelagem
- **Painéis Solares Térmicos:** Pintados de preto fosco na base e cobertos por placas de vidro transparentes, criando um mini efeito estufa que impede a fuga do calor para aquecer água residencial.
- **Lã de Vidro e Poliuretano Expandido:** Utilizados nas paredes de geladeiras e fornos; seu isolamento não decorre da matéria sólida em si, mas do confinamento de ar estático em microcélulas fechadas que anulam a convecção.
- **Roupas de Inverno:** Agasalhos de lã não "geram calor"; eles aprisionam ar aquecido junto ao corpo, impedindo a convecção e reduzindo a condução para o exterior.

## 8. Exercícios de Fixação
1. Explique por que, ao pisar descalço sobre um piso de cerâmica e sobre um tapete de lã no mesmo quarto a \( 20^\circ\text{C} \), temos a sensação nítida de que a cerâmica está "mais fria".
2. Em qual dos seguintes processos é estritamente indispensável a presença de um meio material líquido ou gasoso para que a troca de calor ocorra? (Condução / Convecção / Irradiação).
3. Uma garrafa térmica perde sua capacidade de conservação ao sofrer uma pequena queda que quebra o vácuo entre suas paredes de vidro, embora o vidro interno permaneça intacto. Qual processo de transmissão de calor passou a atuar?

## 9. Correção Comentada dos Exercícios
1. A temperatura de ambos é rigorosamente a mesma (\( 20^\circ\text{C} \), em equilíbrio com o quarto). A sensação térmica decorre da **condutividade térmica**: a cerâmica conduz calor para longe da planta do pé com taxa muito superior à da lã, drenando a energia térmica do corpo rapidamente.
2. Na **Convecção**, pois ela se baseia no deslocamento físico de massas de fluidos com diferentes densidades.
3. Ao entrar ar no espaço intermediário, a transmissão de calor por **condução** e por **convecção** através do ar passa a ocorrer livremente, acelerando as trocas térmicas com o ambiente.

## 10. Desafio de Domínio
Dois cubos de gelo idênticos a \( 0^\circ\text{C} \) são colocados sobre duas placas: uma de alumínio maciço e outra de madeira, ambas mantidas a \( 25^\circ\text{C} \). Em qual das placas o cubo de gelo derreterá mais rapidamente e por quê?
*Pista:* Relacione a Lei de Fourier e a condutividade térmica dos metais frente aos isolantes orgânicos.

## 11. Resumo e Pontos-Chave
- Condução: de partícula a partícula, sem transporte de matéria (predomina em sólidos; Lei de Fourier).
- Convecção: transporte coletivo de fluido motivado por diferença de densidade (exclusiva de líquidos e gases).
- Irradiação: propagação via ondas eletromagnéticas (infravermelho); único processo que ocorre no vácuo.
- Garrafa térmica: vácuo barra condução e convecção; espelhamento barra irradiação; tampa veda convecção.
- Efeito estufa: atmosfera transparente à luz solar visível, mas opaca à radiação infravermelha reemitida pela Terra.

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 1. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Lei de Fourier da Condução Térmica",
                syntax = """\\Phi = \\frac{Q}{\\Delta t} = \\frac{k \\cdot A \\cdot \\Delta T}{L}""",
                description = "Taxa de fluxo de calor estacionário por condução através de uma parede plana homogênea.",
                parametersInfo = """k: \\text{condutividade térmica}; \\quad A: \\text{área superficial}; \\quad \\Delta T: \\text{diferença de temperatura}; \\quad L: \\text{espessura}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Lei de Stefan-Boltzmann da Radiação",
                syntax = """P = e \\cdot \\sigma \\cdot A \\cdot T^4""",
                description = "Potência total de energia térmica irradiada por um corpo em função da quarta potência de sua temperatura absoluta.",
                parametersInfo = """e: \\text{emissividade (0 a 1)}; \\quad \\sigma = 5{,}67 \\times 10^{-8}\\text{ W/(m}^2\\text{K}^4); \\quad T: \\text{temperatura em Kelvin}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Isolamento Térmico com Janela de Vidro Duplo",
                codeOrFormula = """\\Phi = \\frac{A \\cdot \\Delta T}{\\frac{L_1}{k_1} + \\frac{L_{\\text{ar}}}{k_{\\text{ar}}} + \\frac{L_2}{k_2}}""",
                explanation = "Janelas com camada central de ar estático reduzem brutalmente a perda de calor porque o ar tem condutividade térmica dezenas de vezes menor que o vidro."
            ),
            LessonExample(
                title = "Direção das Brisas Marítimas e Terrestres",
                codeOrFormula = """c_{\\text{terra}} < c_{\\text{água}} \\implies T_{\\text{terra, dia}} > T_{\\text{mar, dia}} \\implies \\text{vento do mar para a terra}""",
                explanation = "O aquecimento mais veloz do solo diurno eleva o ar continental e faz a brisa marítima fresca soprar para o continente."
            )
        ),
        goldTip = "O ENEM adora a garrafa térmica! Guarde o papel exato de cada componente: (1) Paredes espelhadas evitam trocas por IRRADIAÇÃO; (2) Vácuo entre as paredes evita CONDUÇÃO e CONVECÇÃO; (3) Tampa plástica fechada evita CONVECÇÃO com o ar exterior."
    )

    // =========================================================================
    // AULA fis_m3_a5: Termodinâmica, Gases Ideais e Máquinas Térmicas
    // =========================================================================
    private val bundleFisM3A5 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A Termodinâmica é a ciência que investiga as relações entre calor, trabalho mecânico e energia interna em sistemas macroscópicos. Nascida no contexto da Revolução Industrial para otimizar o rendimento das primeiras máquinas a vapor (Newcomen, Watt), a termodinâmica estabeleceu limites teóricos inquebrantáveis para a conversão de calor em trabalho útil. No ENEM, este módulo coroa a Termologia ao integrar as leis dos gases ideais, a 1ª Lei da Termodinâmica (conservação irrestrita da energia), a 2ª Lei da Termodinâmica (irreversibilidade e entropia) e o célebre **Ciclo de Carnot**.

## 2. Objetivos de Aprendizagem
- Modelar o comportamento dos gases ideais através da **Equação de Clapeyron**: \( P \cdot V = n \cdot R \cdot T \).
- Analisar as transformações gasosas fundamentais: **Isotérmica** (Boyle), **Isobárica** (Charles/Gay-Lussac), **Isocórica/Isovolumétrica** e **Adiabática**.
- Calcular o trabalho mecânico exercido por ou sobre um gás: \( W = P \cdot \Delta V \) (isobárica) e área sob o diagrama \( P \times V \).
- Dominar a **1ª Lei da Termodinâmica**: \( \Delta U = Q - W \), calculando a variação da energia interna para gases monoatômicos (\( \Delta U = \frac{3}{2} n R \Delta T \)).
- Compreender a **2ª Lei da Termodinâmica** segundo os enunciados históricos de Clausius e Kelvin-Planck.
- Analisar máquinas térmicas cíclicas e calcular rendimento: \( \eta = \frac{W}{Q_Q} = 1 - \frac{|Q_F|}{Q_Q} \).
- Compreender o **Ciclo de Carnot** como o limite superior teórico de rendimento para qualquer máquina térmica: \( \eta_C = 1 - \frac{T_F}{T_Q} \).

## 3. Pré-requisitos
- Trabalho de uma Força e Conservação da Energia Mecânica (Física, Aula fis_m1_a6).
- Calorimetria e conservação de energia térmica (Física, Aula fis_m3_a3).

## 4. Explicação Teórica Aprofundada

### 1. Modelo do Gás Ideal e Equação de Estado
Um gás real comporta-se como ideal (perfeito) sob condições de **baixas pressões e altas temperaturas** (onde as interações intermoleculares atrativas tornam-se desprezíveis e o volume próprio das moléculas é desprezível perante o volume do recipiente).
- **Equação de Clapeyron:**
  \[ P \cdot V = n \cdot R \cdot T \]
  onde \( P \) é a pressão absoluta (\( \text{N/m}^2 \) ou \( \text{Pa} \)), \( V \) é o volume (\( \text{m}^3 \)), \( n \) é o número de mols, \( T \) é a temperatura em Kelvin e \( R = 8{,}31\text{ J}/(\text{mol}\cdot\text{K}) = 0{,}082\text{ atm}\cdot\text{L}/(\text{mol}\cdot\text{K}) \).
- **Lei Geral dos Gases:** Para uma massa fixa de gás (\( n = \text{constante} \)):
  \[ \frac{P_1 \cdot V_1}{T_1} = \frac{P_2 \cdot V_2}{T_2} \]

### 2. Transformações Gasosas Especiais
1. **Isotérmica (\( T = \text{constante} \)):**
   - Lei de Boyle: \( P_1 V_1 = P_2 V_2 \). Curva hiperbólica equilátera no gráfico \( P \times V \).
   - Como \( T \) é constante, a variação de energia interna é nula: \( \Delta U = 0 \implies Q = W \).
2. **Isobárica (\( P = \text{constante} \)):**
   - \( \frac{V_1}{T_1} = \frac{V_2}{T_2} \). O trabalho é dado diretamente por: \( W = P(V_2 - V_1) = P \Delta V \).
3. **Isocórica / Isovolumétrica (\( V = \text{constante} \)):**
   - \( \frac{P_1}{T_1} = \frac{P_2}{T_2} \). Não há deslocamento do êmbolo, logo o trabalho mecânico é estritamente nulo: \( W = 0 \implies \Delta U = Q \).
4. **Adiabática (\( Q = 0 \)):**
   - Transformação ultrarrápida ou em recipiente termicamente isolado onde não há troca de calor com o meio externo:
     \[ \Delta U = -W \]
   - Se o gás sofre **expansão adiabática** (\( W > 0 \)), sua energia interna diminui (\( \Delta U < 0 \)) e **o gás se resfria**. (Exemplo: gás saindo de um desodorante aerossol).
   - Se o gás sofre **compressão adiabática** (\( W < 0 \)), sua energia interna aumenta (\( \Delta U > 0 \)) e **o gás se aquece**. (Exemplo: bomba manual de encher pneu de bicicleta aquecendo o bico).

### 3. Primeira Lei da Termodinâmica
Representa o princípio universal da conservação da energia:
\[ \Delta U = Q - W \]
- Convencionalidade de sinais:
  - \( Q > 0 \): sistema recebe calor; \( Q < 0 \): sistema cede calor.
  - \( W > 0 \): gás expande (realiza trabalho sobre o meio); \( W < 0 \): gás é comprimido (recebe trabalho do meio).
  - \( \Delta U > 0 \): temperatura sobe; \( \Delta U < 0 \): temperatura cai.
- Para um gás ideal monoatômico:
  \[ U = \frac{3}{2} n R T \implies \Delta U = \frac{3}{2} n R \Delta T \]

### 4. Ciclos Termodinâmicos e Trabalho Líquido
Em um ciclo fechado no diagrama \( P \times V \), o estado inicial coincide com o final, logo:
\[ \Delta U_{\text{ciclo}} = 0 \implies W_{\text{líquido}} = Q_{\text{líquido}} \]
- O trabalho líquido útil corresponde geometricamente à **área interna delimitada pelo ciclo**.
- Se o ciclo for percorrido no sentido **horário**: \( W > 0 \) (Máquina Térmica — converte calor em trabalho).
- Se o ciclo for percorrido no sentido **anti-horário**: \( W < 0 \) (Refrigerador / Bomba de Calor — consome trabalho para retirar calor da fonte fria).

### 5. Segunda Lei da Termodinâmica
A 1ª Lei garante a conservação quantitativa, mas não estabelece o sentido espontâneo dos processos. A 2ª Lei impõe restrições direcionais:
- **Enunciado de Clausius:** "É impossível construir um dispositivo que opere em ciclo e cujo único efeito seja transferir calor espontaneamente de um corpo frio para um corpo quente." (Para transferir calor do frio para o quente, exige-se trabalho externo, como no compressor de uma geladeira).
- **Enunciado de Kelvin-Planck:** "É impossível construir uma máquina térmica cíclica cujo único efeito seja converter integralmente em trabalho todo o calor recebido de uma única fonte térmica." (É impossível atingir rendimento de \( 100\% \); toda máquina térmica obrigatoriamente rejeita calor para uma fonte fria).
- **Entropia (\( S \)):** Em todo processo espontâneo em um sistema isolado, a entropia total (medida da degradação da energia e desordem molecular) sempre aumenta: \( \Delta S_{\text{universo}} \ge 0 \).

### 6. Rendimento de Máquinas Térmicas e Ciclo de Carnot
- **Rendimento Geral de qualquer máquina térmica:**
  \[ \eta = \frac{W}{Q_Q} = \frac{Q_Q - |Q_F|}{Q_Q} = 1 - \frac{|Q_F|}{Q_Q} \]
- **Ciclo de Carnot (Nicolas Léonard Sadi Carnot, 1824):**
  Ciclo ideal reversível composto por duas transformações isotérmicas e duas adiabáticas alternadas. Nenhuma máquina real operando entre as temperaturas absolutas \( T_F \) (fonte fria) e \( T_Q \) (fonte quente) pode ter rendimento superior ao de Carnot:
  \[ \eta_{\text{Carnot}} = 1 - \frac{T_F}{T_Q} \]
  onde \( T_F \) e \( T_Q \) devem estar **obrigatoriamente em Kelvin**.

## 5. Definições e Conceitos
- **Máquina Térmica:** Dispositivo cíclico que absorve calor de uma fonte de alta temperatura (\( Q_Q \)), realiza trabalho mecânico útil (\( W \)) e rejeita calor residual para uma fonte de baixa temperatura (\( Q_F \)).
- **Refrigerador:** Máquina térmica operada em ciclo reverso que consome energia mecânica/elétrica para extrair calor da fonte fria interna e rejeitá-lo para a fonte quente externa.
- **Processo Reversível:** Processo idealizado que pode ser revertido sem deixar qualquer alteração residual no sistema ou no universo.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Aplicação da 1ª Lei em Transformação Isobárica
Um mol de gás ideal sob pressão constante de \( 2 \times 10^5\text{ N/m}^2 \) expande-se de um volume de \( 0{,}01\text{ m}^3 \) para \( 0{,}03\text{ m}^3 \). Durante o processo, o gás absorve \( 7000\text{ J} \) de calor. Determine o trabalho realizado pelo gás e a variação de sua energia interna.
- **Resolução:**
  1. Como a pressão é constante, o trabalho é:
     \[ W = P \cdot \Delta V = (2 \times 10^5) \cdot (0{,}03 - 0{,}01) = (2 \times 10^5) \cdot 0{,}02 = 4000\text{ J} \]
  2. Pela 1ª Lei da Termodinâmica:
     \[ \Delta U = Q - W \]
     \[ \Delta U = 7000 - 4000 = 3000\text{ J} \]
  - Resposta: O trabalho realizado foi de \( 4000\text{ J} \) e a energia interna aumentou em \( 3000\text{ J} \).

### Exemplo 2: Rendimento Máximo de Carnot
Uma usina termelétrica opera com vapor superaquecido a \( 327^\circ\text{C} \) (fonte quente) e descarta água de refrigeração em um rio a \( 27^\circ\text{C} \) (fonte fria). Qual é o rendimento teórico máximo que essa usina pode atingir?
- **Resolução:**
  1. Conversão obrigatória para a escala Kelvin:
     \[ T_Q = 327 + 273 = 600\text{ K} \]
     \[ T_F = 27 + 273 = 300\text{ K} \]
  2. Fórmula do rendimento de Carnot:
     \[ \eta_C = 1 - \frac{T_F}{T_Q} = 1 - \frac{300}{600} = 1 - 0{,}5 = 0{,}5 = 50\% \]
  - Resposta: O rendimento máximo absoluto é de \( 50\% \). Metade da energia térmica gerada no combustível é necessariamente descartada como calor residual no meio ambiente.

## 7. Aplicações Práticas e Modelagem
- **Motores de Combustão Interna (Ciclo Otto e Diesel):** Os motores de automóveis convertem a queima de gasolina ou diesel em movimento dos pistões com rendimentos reais típicos entre \( 25\% \) e \( 35\% \).
- **Usinas Nucleares e Termelétricas:** Utilizam turbinas a vapor para girar alternadores elétricos, limitadas pelo Ciclo de Carnot das temperaturas da caldeira e dos condensadores fluviais.
- **Refrigeração Doméstica:** O gás refrigerante (como o R-134a) sofre expansão adiabática no evaporador interno (resfriando-se bruscamente) e compressão mecânica pelo motor externo (aquecendo-se para rejeitar calor pela serpentina traseira preta).

## 8. Exercícios de Fixação
1. Um gás ideal sofre uma compressão adiabática rápida. A sua temperatura aumentou, diminuiu ou permaneceu constante? Justifique com base na 1ª Lei.
2. Uma máquina térmica recebe \( 1200\text{ J} \) de calor da fonte quente a cada ciclo e realiza \( 360\text{ J} \) de trabalho mecânico útil. Determine o seu rendimento percentual e a quantidade de calor descartada para a fonte fria.
3. É possível inventar uma máquina térmica que absorva \( 500\text{ J} \) de calor de uma caldeira a carvão e execute \( 500\text{ J} \) de trabalho útil sem aquecer o ar circunvizinho? Qual lei física proíbe tal invento?

## 9. Correção Comentada dos Exercícios
1. A temperatura **aumentou**. Em um processo adiabático, \( Q = 0 \). Na compressão, \( W < 0 \). Pela 1ª Lei: \( \Delta U = 0 - W = -W > 0 \). Sendo a energia interna função direta da temperatura (\( \Delta U \propto \Delta T \)), o aumento de \( U \) acarreta elevação na temperatura do gás.
2. Rendimento: \( \eta = \frac{W}{Q_Q} = \frac{360}{1200} = 0{,}30 = 30\% \). Calor rejeitado: \( |Q_F| = Q_Q - W = 1200 - 360 = 840\text{ J} \).
3. **Não, é absolutamente impossível**. O enunciado de Kelvin-Planck da Segunda Lei da Termodinâmica proíbe a conversão integral de calor em trabalho em processos cíclicos. Rejeitar uma fração de calor para a fonte fria é uma exigência entrópica mandatória.

## 10. Desafio de Domínio
Demonstre por que, para obter um rendimento de Carnot de \( 100\% \) (\( \eta = 1 \)), seria indispensável que a fonte fria estivesse na temperatura inalcançável do Zero Absoluto (\( T_F = 0\text{ K} \)) ou a fonte quente em temperatura infinita (\( T_Q \to \infty \)).
*Pista:* Analise a expressão \( \eta_C = 1 - \frac{T_F}{T_Q} \) sob o limite matemático e a Terceira Lei da Termodinâmica.

## 11. Resumo e Pontos-Chave
- Equação de Clapeyron: \( P \cdot V = n \cdot R \cdot T \).
- 1ª Lei: \( \Delta U = Q - W \) (Conservação da Energia).
- Isotérmica (\( \Delta U = 0 \)), Isobárica (\( W = P\Delta V \)), Isocórica (\( W = 0 \)), Adiabática (\( Q = 0 \implies \Delta U = -W \)).
- Em ciclos fechados: \( \Delta U = 0 \implies W = Q_{\text{líq}} \) (área do diagrama \( P \times V \)).
- 2ª Lei proíbe rendimento de 100% (Kelvin-Planck) e fluxo espontâneo do frio para o quente (Clausius).
- Ciclo de Carnot: \( \eta_C = 1 - \frac{T_F}{T_Q} \) (temperaturas obrigatoriamente em Kelvin).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
- MORAN, Michael J.; SHAPIRO, Howard N. *Princípios de Termodinâmica para Engenharia*. 7. ed. LTC, 2013.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação de Estado dos Gases Ideais",
                syntax = """P \\cdot V = n \\cdot R \\cdot T""",
                description = "Equação de Clapeyron relacionando as variáveis de estado de uma amostra de gás ideal.",
                parametersInfo = """P: \\text{pressão (Pa)}; \\quad V: \\text{volume (m}^3); \\quad n: \\text{mols}; \\quad R = 8{,}31\\text{ J/(mol}\\cdot\\text{K)}; \\quad T: \\text{Kelvin}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Primeira Lei da Termodinâmica",
                syntax = """\\Delta U = Q - W""",
                description = "Balanço energético onde a variação da energia interna de um sistema gasoso iguala o calor trocado menos o trabalho realizado.",
                parametersInfo = """\\Delta U: \\text{variação de energia interna (J)}; \\quad Q: \\text{calor (J)}; \\quad W: \\text{trabalho mecânico (J)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Rendimento Máximo do Ciclo de Carnot",
                syntax = """\\eta_C = 1 - \\frac{T_F}{T_Q}""",
                description = "Limite teórico máximo de rendimento para qualquer máquina térmica cíclica operando entre dois reservatórios térmicos.",
                parametersInfo = """T_F: \\text{temperatura absoluta da fonte fria (K)}; \\quad T_Q: \\text{temperatura da fonte quente (K)}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Trabalho Geométrico em Ciclo Termodinâmico Retangular",
                codeOrFormula = """W_{\\text{ciclo}} = (P_{\\text{máx}} - P_{\\text{mín}}) \\cdot (V_{\\text{máx}} - V_{\\text{mín}})""",
                explanation = "A área interna delimitada no diagrama PxV fornece diretamente o trabalho mecânico líquido realizado pelo gás durante uma volta completa no ciclo."
            ),
            LessonExample(
                title = "Rendimento Real de Motor a Gasolina",
                codeOrFormula = """\\eta = \\frac{W}{Q_Q} = \\frac{P_{\\text{mecânica}}}{\\text{Consumo de Combustível} \\cdot \\text{Poder Calorífico}}""",
                explanation = "Determinação do rendimento real comparando a potência entregue no eixo do veículo com a taxa de combustão química liberada por segundo."
            )
        ),
        goldTip = "Em questões do Ciclo de Carnot no ENEM, converta IMEDIATAMENTE as temperaturas para Kelvin somando 273! Se você calcular com as temperaturas em Celsius, o cálculo dará completamente errado e certamente haverá um distrator esperando por esse erro clássico nas alternativas!"
    )
}

