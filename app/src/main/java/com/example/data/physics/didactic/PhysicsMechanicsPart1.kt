package com.example.data.physics.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Física ENEM — Módulo 1: Mecânica (Parte 1: Aulas fis_m1_a1 a fis_m1_a5).
 * Estrutura curricular rigorosa em 12 seções por aula, fundamentada em Halliday & Resnick, Sears & Zemansky, OpenStax University Physics e Matriz de Referência do ENEM / INEP.
 */
object PhysicsMechanicsPart1 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "fis_m1_a1" -> bundleFisM1A1
            "fis_m1_a2" -> bundleFisM1A2
            "fis_m1_a3" -> bundleFisM1A3
            "fis_m1_a4" -> bundleFisM1A4
            "fis_m1_a5" -> bundleFisM1A5
            else -> null
        }
    }

    // =========================================================================
    // AULA fis_m1_a1: Cinemática Escalar (MRU, MRUV e Funções Horárias)
    // =========================================================================
    private val bundleFisM1A1 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O estudo da Mecânica clássica inaugura-se com a **Cinemática**, o ramo da Física dedicado à descrição matemática pura do movimento dos corpos através do espaço e do tempo, sem se preocupar em um primeiro momento com as forças causadoras desse movimento. No cotidiano e no ENEM, a cinemática é a ferramenta que explica desde o cálculo da velocidade média de um veículo em uma rodovia pedagiada, o tempo de reação de um motorista durante uma frenagem de emergência, até a estimativa de ordens de grandeza em viagens espaciais ou micrométricas.

## 2. Objetivos de Aprendizagem
- Compreender a relatividade do movimento e a dependência fundamental da escolha do **Referencial**.
- Diferenciar com rigor conceitual Ponto Material (partícula) de Corpo Extenso, e Distância Total Percorrida de Deslocamento Escalar (\( \Delta S \)).
- Definir e calcular Velocidade Escalar Média (\( v_m \)) e Aceleração Escalar Média (\( a_m \)), dominando a conversão de unidades (\( 1\text{ m/s} = 3{,}6\text{ km/h} \)).
- Deduzir e aplicar a Função Horária da Posição no Movimento Retilíneo Uniforme (MRU): \( S(t) = S_0 + v \cdot t \).
- Dominar as equações do Movimento Retilíneo Uniformemente Variado (MRUV): Função da Velocidade (\( v = v_0 + a \cdot t \)), Função da Posição (\( S = S_0 + v_0 \cdot t + \frac{1}{2} a \cdot t^2 \)) e a célebre **Equação de Torricelli** (\( v^2 = v_0^2 + 2 a \Delta S \)).
- Determinar Ordens de Grandeza através da notação científica e regra de arredondamento semi-geométrica.

## 3. Pré-requisitos
- Operações fundamentais com notação científica e potências de 10 (Matemática Básica, Aula mb_l04).
- Resolução de equações do 1º e 2º grau e Teorema de Pitágoras (Matemática Básica, Aulas mb_l07 e mb_l09).

## 4. Explicação Teórica Aprofundada

### 1. Fundamentos: Referencial, Posição e Deslocamento
- **Referencial:** Um corpo está em repouso se sua posição em relação ao referencial não varia com o tempo; está em movimento se sua posição varia. Não existe repouso ou movimento absoluto no universo newtoniano.
- **Ponto Material vs. Corpo Extenso:** Um automóvel de 4 metros em uma viagem rodoviária de 500 km é modelado perfeitamente como um ponto material; o mesmo automóvel tentando manobrar em uma vaga de garagem de 4,5 metros deve ser tratado como corpo extenso.
- **Deslocamento Escalar (\( \Delta S \)):** Diferença vetorial/escalar entre a posição final e inicial:
  \[ \Delta S = S - S_0 \]
  Diferencia-se da distância total efetivamente percorrida (se um atleta der uma volta completa em uma pista circular de 400 m, a distância percorrida é 400 m, mas seu deslocamento escalar \( \Delta S = 0 \)).

### 2. Movimento Retilíneo Uniforme (MRU)
No MRU, o vetor velocidade permanece estritamente constante e diferente de zero em todos os instantes. Consequentemente, a aceleração escalar é nula (\( a = 0 \)) e o móvel percorre distâncias iguais em intervalos de tempo iguais:
- **Função Horária da Posição:**
  \[ S(t) = S_0 + v \cdot t \]
- **Classificação:** Se \( v > 0 \), o móvel caminha no sentido da trajetória (movimento progressivo); se \( v < 0 \), caminha contra a orientação da trajetória (movimento retrógrado).
- **Velocidade Relativa:** Se dois móveis trafegam na mesma direção e mesmo sentido: \( v_{\text{rel}} = |v_A - v_B| \). Se trafegam em sentidos opostos (ao encontro um do outro): \( v_{\text{rel}} = |v_A| + |v_B| \).

### 3. Movimento Retilíneo Uniformemente Variado (MRUV)
No MRUV, a velocidade varia linearmente com o tempo, o que significa que a **aceleração escalar (\( a \)) é constante e não nula**:
\[ a = \frac{\Delta v}{\Delta t} = \text{constante} \neq 0 \]
- **Função Horária da Velocidade:**
  \[ v(t) = v_0 + a \cdot t \]
- **Função Horária da Posição (Equação do "Sorvetão"):**
  \[ S(t) = S_0 + v_0 \cdot t + \frac{1}{2} a \cdot t^2 \]
- **Equação de Torricelli:** Obtida eliminando algebricamente a variável tempo (\( t \)) entre as duas equações anteriores. É a equação de sobrevivência indispensável quando o enunciado **NÃO fornece e NÃO pede o tempo de trânsito**:
  \[ v^2 = v_0^2 + 2 \cdot a \cdot \Delta S \]
- **Classificação Dinâmica:**
  - *Movimento Acelerado:* Os módulos da velocidade aumentam com o tempo. A velocidade e a aceleração possuem o mesmo sinal algébrico (\( v \cdot a > 0 \)).
  - *Movimento Retardado (Frenagem):* Os módulos da velocidade diminuem com o tempo. A velocidade e a aceleração possuem sinais opostos (\( v \cdot a < 0 \)).

### 4. Ordem de Grandeza
Para encontrar a ordem de grandeza de uma medida:
1. Escreva o número em notação científica: \( N = M \times 10^p \), com \( 1 \le M < 10 \).
2. Compare a mantissa \( M \) com o valor médio geométrico \( \sqrt{10} \approx 3{,}162 \):
   - Se \( M < \sqrt{10} \approx 3{,}16 \implies \text{Ordem de Grandeza} = 10^p \).
   - Se \( M \ge \sqrt{10} \approx 3{,}16 \implies \text{Ordem de Grandeza} = 10^{p+1} \).

## 5. Definições e Conceitos
- **Velocidade Escalar Instantânea:** Limite da razão \( \Delta S / \Delta t \) quando o intervalo de tempo tende a zero (derivada temporal da posição).
- **Aceleração da Gravidade (\( g \)):** Aceleração média de queda livre nas proximidades da superfície da Terra, adotada no ENEM tipicamente como \( g = 9{,}8\text{ m/s}^2 \) ou aproximada para \( g = 10\text{ m/s}^2 \).
- **Tempo de Reação:** Intervalo de tempo entre o estímulo visual ou sonoro percebido pelo motorista e o início efetivo do acionamento mecânico dos freios (durante esse intervalo, o carro se move em puro MRU).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Distância Total de Parada de Emergência no ENEM
Um automóvel trafega em uma via reta a \( 72\text{ km/h} \). O motorista vê uma faixa de pedestres ocupada e aciona os freios. O tempo de reação fisiológica do motorista é de \( 0{,}7\text{ s} \) e a desaceleração máxima constante gerada pelos freios é de \( 5\text{ m/s}^2 \). Calcule a distância total percorrida pelo veículo desde o instante do avistamento até a parada completa.
- **Passo 1 (Conversão de Unidades):**
  \[ v_0 = \frac{72}{3{,}6} = 20\text{ m/s} \]
- **Passo 2 (Fase 1: Distância de Reação em MRU):** Durante o tempo de reação \( \Delta t_{\text{reação}} = 0{,}7\text{ s} \), o motorista ainda não tocou no pedal de freio; o veículo desloca-se com velocidade constante de \( 20\text{ m/s} \):
  \[ \Delta S_1 = v_0 \cdot \Delta t_{\text{reação}} = 20 \times 0{,}7 = 14\text{ metros} \]
- **Passo 3 (Fase 2: Distância de Frenagem em MRUV):** Quando os freios são acionados, o movimento passa a ser retardado com aceleração \( a = -5\text{ m/s}^2 \) até a velocidade final \( v = 0 \). Como não temos o tempo de frenagem, aplicamos Torricelli:
  \[ v^2 = v_0^2 + 2 a \Delta S_2 \implies 0^2 = 20^2 + 2(-5)\Delta S_2 \implies 0 = 400 - 10 \Delta S_2 \]
  \[ 10 \Delta S_2 = 400 \implies \Delta S_2 = 40\text{ metros} \]
- **Passo 4 (Distância Total de Parada):**
  \[ \Delta S_{\text{total}} = \Delta S_1 + \Delta S_2 = 14 + 40 = 54\text{ metros} \]

## 7. Aplicações Práticas e Modelagem
- **Radares de Velocidade Média em Rodovias:** Sistemas inteligentes de pedágio que calculam a velocidade média dividindo a distância entre dois pórticos pelo tempo cronometrado de passagem do leitor de placas. Se \( v_m > v_{\text{limite}} \), a multa é emitida mesmo que o motorista tenha freado exatamente sob a câmera.
- **Sistemas de Freios ABS (*Anti-lock Braking System*):** Ao evitar o travamento total das rodas, o sistema mantém o atrito entre o pneu e o asfalto no regime estático máximo (em vez do atrito cinético de deslizamento), encurtando significativamente a distância de frenagem \( \Delta S_2 \).

## 8. Exercícios de Fixação
1. Converta \( 108\text{ km/h} \) para \( \text{m/s} \) e \( 15\text{ m/s} \) para \( \text{km/h} \).
2. Um móvel parte do repouso com aceleração constante de \( 3\text{ m/s}^2 \). Qual será a sua velocidade após percorrer \( 54\text{ metros} \)?
3. Qual é a ordem de grandeza do número de segundos contidos em um ano terrestre comum de 365 dias?

## 9. Correção Comentada dos Exercícios
1. \( 108 / 3{,}6 = 30\text{ m/s} \); e \( 15 \times 3{,}6 = 54\text{ km/h} \).
2. Como não temos o tempo, usamos Torricelli: \( v^2 = v_0^2 + 2 a \Delta S \implies v^2 = 0^2 + 2(3)(54) = 324 \implies v = \sqrt{324} = 18\text{ m/s} \).
3. \( \Delta t = 365 \times 24 \times 3600 = 31.536.000\text{ s} = 3{,}1536 \times 10^7\text{ s} \). Como a mantissa \( 3{,}1536 < 3{,}162 \) (está abaixo de \( \sqrt{10} \)), a ordem de grandeza é \( 10^7\text{ segundos} \).

## 10. Desafio de Domínio
Dois trens de 100 metros de comprimento cada movem-se em trilhos paralelos em sentidos contrários. O trem A move-se a \( 72\text{ km/h} \) e o trem B a \( 108\text{ km/h} \). Quanto tempo dura o cruzamento completo dos dois trens a partir do momento em que suas frentes se emparelham?

## 11. Resumo e Pontos-Chave
- Conversão fundamental: \( \text{km/h} \xrightarrow{\div 3{,}6} \text{m/s} \) e \( \text{m/s} \xrightarrow{\times 3{,}6} \text{km/h} \).
- MRU: \( a = 0 \), \( v = \text{constante} \), função \( S = S_0 + v \cdot t \).
- MRUV: \( a = \text{constante} \neq 0 \), funções \( v = v_0 + a \cdot t \) e \( S = S_0 + v_0 t + \frac{1}{2} a t^2 \).
- Torricelli: \( v^2 = v_0^2 + 2 a \Delta S \) (use sempre quando o tempo \( t \) for omitido).
- Ordem de grandeza: Mantissa \( < \sqrt{10} \approx 3{,}16 \implies 10^p \); Mantissa \( \ge 3{,}16 \implies 10^{p+1} \).

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Mecânica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- SEARS, F.; ZEMANSKY, M.; YOUNG, H. D.; FREEDMAN, R. A. *Física I: Mecânica*. 14ª ed. São Paulo: Pearson, 2016.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação de Torricelli",
                syntax = "v^2 = v_0^2 + 2 \\cdot a \\cdot \\Delta S",
                description = "Relaciona a velocidade inicial, velocidade final, aceleração e deslocamento sem depender da variável tempo.",
                parametersInfo = "v é velocidade final (m/s), v0 é inicial (m/s), a é aceleração (m/s²) e ΔS é deslocamento (m)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Função Horária da Posição no MRUV",
                syntax = "S(t) = S_0 + v_0 \\cdot t + \\frac{1}{2} a \\cdot t^2",
                description = "Equação parabólica que define a posição escalar em função do tempo decorrido.",
                parametersInfo = "S0 é posição inicial, v0 é velocidade inicial e a é a taxa constante de variação da velocidade."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Frenagem de Emergência com Torricelli",
                codeOrFormula = "v^2 = v_0^2 + 2a\\Delta S \\implies 0 = 20^2 + 2(-5)\\Delta S \\implies \\Delta S = 40\\text{ m}",
                explanation = "Aplicando Torricelli diretamente para uma desaceleração de 5 m/s² a partir de 20 m/s (72 km/h) até a parada total."
            )
        ),
        goldTip = "O mandamento sagrado da Cinemática no ENEM: 'Não tem tempo, não quer o tempo? Use Torricelli!'"
    )

    // =========================================================================
    // AULA fis_m1_a2: Análise Gráfica, Vetores e Lançamentos
    // =========================================================================
    private val bundleFisM1A2 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A linguagem dos gráficos e a álgebra vetorial constituem a espinha dorsal da modelagem física moderna. No ENEM, uma parcela expressiva das questões de física não exige a aplicação mecânica de fórmulas, mas sim a capacidade analítica de extrair grandezas físicas a partir da inclinação de retas ou do cálculo da área sob curvas em diagramas \( S \times t \), \( v \times t \) e \( a \times t \). Além disso, a independência dos movimentos simultâneos postulada por Galileu Galilei permite decompor os espetaculares movimentos bidimensionais — como o chute de uma bola de futebol, o lançamento de projéteis ou o salto de atletas — em dois movimentos lineares ortogonais simples e independentes.

## 2. Objetivos de Aprendizagem
- Interpretar geometricamente as propriedades operatórias dos gráficos da cinemática: declividade (inclinação) e área sob a curva.
- Reconhecer que no gráfico \( v \times t \), a **área numérica equivale ao Deslocamento** (\( \Delta S \)) e a **inclinação da reta equivale à Aceleração** (\( a \)).
- Dominar a decomposição ortogonal de vetores em componentes cartesianas: \( v_x = v \cdot \cos\theta \) e \( v_y = v \cdot \sin\theta \).
- Aplicar o **Princípio da Independência dos Movimentos de Galileu**.
- Modelar o Lançamento Vertical e Queda Livre sob aceleração da gravidade (\( g \)).
- Resolver problemas de Lançamento Horizontal e Lançamento Oblíquo (tempo de subida, altura máxima e alcance horizontal máximo).

## 3. Pré-requisitos
- Funções horárias do MRU e MRUV (Aula fis_m1_a1).
- Trigonometria básica do triângulo retângulo: seno, cosseno e Teorema de Pitágoras.

## 4. Explicação Teórica Aprofundada

### 1. Propriedades Operatórias dos Gráficos da Cinemática
A geometria analítica dos gráficos cinematográficos guarda dois operadores fundamentais:
1. **Propriedade da Declividade (Inclinação / Derivada):**
   - No gráfico Posição vs. Tempo (\( S \times t \)): a inclinação da reta tangente (\( \tan\alpha \)) representa numericamente a **Velocidade Escalar**:
     \[ \text{Declividade } (S \times t) = \frac{\Delta S}{\Delta t} \equiv v \]
     *(Reta ascendente: \( v > 0 \); Reta horizontal: \( v = 0 \); Parábola com concavidade para cima: \( a > 0 \))*.
   - No gráfico Velocidade vs. Tempo (\( v \times t \)): a inclinação da reta tangente representa a **Aceleração Escalar**:
     \[ \text{Declividade } (v \times t) = \frac{\Delta v}{\Delta t} \equiv a \]
2. **Propriedade da Área (Integral Numérica):**
   - No gráfico \( v \times t \): a área delimitada entre a curva e o eixo do tempo é **numericamente igual ao Deslocamento Escalar** (\( \Delta S \)):
     \[ \text{Área sob a curva } (v \times t) \equiv \Delta S \]
     *(Cálculo prático através de áreas de figuras planas: triângulos, retângulos e trapézios)*.
   - No gráfico \( a \times t \): a área sob a curva representa numericamente a **Variação da Velocidade** (\( \Delta v \)).

### 2. Vetores no Plano e Decomposição Cartesiana
Uma grandeza vetorial (deslocamento, velocidade, aceleração, força) exige módulo, direção e sentido.
- Dado um vetor velocidade \( \vec{v} \) inclinado de um ângulo \( \theta \) com a horizontal:
  \[ v_x = v \cdot \cos\theta \quad (\text{componente horizontal}) \]
  \[ v_y = v \cdot \sin\theta \quad (\text{componente vertical}) \]
  \[ |\vec{v}| = \sqrt{v_x^2 + v_y^2} \]

### 3. O Princípio de Galileu e o Lançamento Oblíquo
Galileu Galilei provou que um movimento bidimensional composto pode ser decomposto em dois movimentos unidimensionais simultâneos que ocorrem **de forma totalmente independente**:
- **Eixo Horizontal (\( x \)):** Desprezando a resistência do ar, não há forças atuando no eixo horizontal (\( a_x = 0 \)). O projétil executa um **MRU**:
  \[ v_x = v_0 \cdot \cos\theta = \text{constante} \]
  \[ x(t) = v_x \cdot t \]
- **Eixo Vertical (\( y \)):** O projétil está sujeito à aceleração constante da gravidade orientada para baixo (\( a_y = -g \)). O projétil executa um **MRUV**:
  \[ v_y(t) = v_{0y} - g \cdot t = (v_0 \cdot \sin\theta) - g \cdot t \]
  \[ y(t) = y_0 + v_{0y} \cdot t - \frac{1}{2} g \cdot t^2 \]
  \[ v_y^2 = v_{0y}^2 - 2 g \Delta y \]

### 4. Grandezas Notáveis do Lançamento Oblíquo
1. **Ponto Mais Alto (Vértice da Parábola):** No ponto de altura máxima, o projétil **não para totalmente**: sua velocidade vertical anula-se momentaneamente (\( v_y = 0 \)), mas sua velocidade horizontal permanece viva (\( v_{\text{topo}} = v_x \neq 0 \)).
2. **Tempo de Subida (\( t_{\text{subida}} \)):**
   \[ 0 = v_{0y} - g \cdot t_{\text{subida}} \implies t_{\text{subida}} = \frac{v_0 \cdot \sin\theta}{g} \]
   O tempo total de voo (se o lançamento e pouso ocorrerem na mesma cota altimétrica) é o dobro: \( t_{\text{total}} = 2 \cdot t_{\text{subida}} \).
3. **Altura Máxima (\( H_{\text{máx}} \)):**
   \[ H_{\text{máx}} = \frac{v_{0y}^2}{2g} = \frac{(v_0 \cdot \sin\theta)^2}{2g} \]
4. **Alcance Horizontal Máximo (\( A \)):**
   \[ A = v_x \cdot t_{\text{total}} = \frac{v_0^2 \cdot \sin(2\theta)}{g} \]
   - **Ângulo de Alcance Máximo:** Como a função seno tem valor máximo em \( 90^\circ \), \( \sin(2\theta) = 1 \implies 2\theta = 90^\circ \implies \theta = 45^\circ \). Para um mesmo módulo de velocidade de disparo, o alcance horizontal no vácuo é máximo para o ângulo de **\( 45^\circ \)**.
   - **Ângulos Complementares:** Dois disparos com ângulos complementares (cuja soma seja \( 90^\circ \), como \( 30^\circ \) e \( 60^\circ \)) atingem exatamente o **mesmo alcance horizontal**.

## 5. Definições e Conceitos
- **Queda Livre:** Movimento vertical puramente acelerado exclusivamente pela gravidade a partir do repouso (\( v_0 = 0 \)).
- **Lançamento Horizontal:** Movimento balístico onde a velocidade inicial é puramente horizontal (\( \theta = 0^\circ \implies v_{0x} = v_0 \) e \( v_{0y} = 0 \)). O tempo de queda depende unicamente da altura e é exatamente igual ao tempo de uma queda livre da mesma cota.
- **Trajetória Parabólica:** Lugar geométrico gerado pela combinação de uma função linear no eixo horizontal com uma função quadrática no eixo vertical.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: O Teste do Pacote Abandonado por Avião no ENEM
Um avião cargueiro voa horizontalmente a uma altitude constante de \( 500\text{ metros} \) com velocidade constante de \( 360\text{ km/h} \) (\( 100\text{ m/s} \)). Ele abandona uma caixa de suprimentos médicos para atingir um alvo no solo. Desprezando a resistência do ar e adotando \( g = 10\text{ m/s}^2 \):
a) Quanto tempo o pacote demora para tocar o chão?
b) A que distância horizontal antes do alvo o piloto deve soltar a carga?
- **Passo 1 (Análise Vertical para Achar o Tempo):** O pacote parte com velocidade vertical inicial nula (\( v_{0y} = 0 \)) porque o avião voava em linha horizontal. O tempo de descida é regido pela queda livre:
  \[ \Delta y = \frac{1}{2} g \cdot t^2 \implies 500 = \frac{1}{2}(10) t^2 \implies 500 = 5 t^2 \implies t^2 = 100 \implies t = 10\text{ segundos} \]
- **Passo 2 (Análise Horizontal para Achar a Distância):** O pacote conserva a inércia horizontal do avião (\( v_x = 100\text{ m/s} \)) durante todo o voo de 10 segundos:
  \[ \Delta x = v_x \cdot t = 100\text{ m/s} \times 10\text{ s} = 1.000\text{ metros} \]
- **Passo 3 (Conclusão):** O piloto deve soltar o fardo exatamente \( 1\text{ km} \) (1.000 m) antes da vertical do alvo. Para quem olha de dentro do avião, a carga cai em linha reta vertical logo abaixo da fuselagem; para quem está parado na terra, a carga descreve um arco de parábola.

## 7. Aplicações Práticas e Modelagem
- **Balística Policial e Esportiva:** O cálculo de lançamento oblíquo determina trajetórias de projéteis e o ângulo de elevação de canhões de artilharia e chutes no futebol.
- **Leitura de Telemetria Automotiva:** Engenheiros de Fórmula 1 integram a área sob o gráfico de telemetria \( v \times t \) da volta para aferir exatamente onde o carro ganhou ou perdeu metros preciosos em relação ao concorrente.

## 8. Exercícios de Fixação
1. Em um gráfico de velocidade por tempo (\( v \times t \)), qual o significado físico da área geométrica abaixo da curva?
2. Em um lançamento oblíquo no vácuo, qual é a velocidade do projétil exatamente no ponto mais alto de sua trajetória?
3. Se um projétil atinge alcance máximo com ângulo de \( 45^\circ \), qual outro ângulo atingirá o mesmo alcance que um disparo a \( 25^\circ \)?

## 9. Correção Comentada dos Exercícios
1. A área sob o gráfico \( v \times t \) representa o **Deslocamento Escalar total** (\( \Delta S \)) percorrido pelo corpo no intervalo de tempo considerado.
2. A velocidade não é zero! No ponto mais alto, apenas a velocidade vertical se anula (\( v_y = 0 \)); o corpo continua se movendo para a frente com sua velocidade horizontal constante \( v_x = v_0 \cdot \cos\theta \).
3. O ângulo complementar: \( 90^\circ - 25^\circ = 65^\circ \). Ângulos complementares possuem alcances horizontais idênticos no vácuo.

## 10. Desafio de Domínio
Uma esfera é solta da borda de uma mesa horizontal de altura \( H \) ao mesmo tempo em que outra esfera idêntica é disparada horizontalmente da mesma altura com velocidade de \( 20\text{ m/s} \). Qual das duas esferas tocará o solo primeiro? Demonstre algebricamente com o Princípio de Galileu.

## 11. Resumo e Pontos-Chave
- Gráfico \( S \times t \): Inclinação = Velocidade. Gráfico \( v \times t \): Inclinação = Aceleração; Área = Deslocamento (\( \Delta S \)).
- Decomposição vetorial: \( v_x = v \cos\theta \), \( v_y = v \sin\theta \).
- Lançamento de Projéteis: Eixo \( x \) é MRU (velocidade constante); Eixo \( y \) é MRUV (aceleração \( g \)).
- No topo da trajetória oblíqua: \( v_y = 0 \), mas \( v_x \neq 0 \) (velocidade total é mínima, mas não nula).
- Alcance máximo no plano horizontal ocorre a \( \theta = 45^\circ \). Ângulos complementares têm mesmo alcance.

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Mecânica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- NUSSENZVEIG, H. M. *Curso de Física Básica 1: Mecânica*. 5ª ed. São Paulo: Blucher, 2013.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Propriedade da Área no Gráfico v x t",
                syntax = "\\Delta S \\stackrel{\\text{num}}{=} \\text{Área sob a curva do gráfico } v(t)",
                description = "O cálculo da área de figuras geométricas (retângulos, trapézios) no diagrama v x t fornece o deslocamento escalar.",
                parametersInfo = "Válido universalmente para qualquer tipo de movimento."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Decomposição do Lançamento Oblíquo",
                syntax = "v_x = v_0 \\cos\\theta \\quad (\\text{MRU}) \\quad | \\quad v_y(t) = v_0 \\sin\\theta - g \\cdot t \\quad (\\text{MRUV})",
                description = "Separação do movimento parabólico em um eixo inercial e um eixo uniformemente acelerado pela gravidade.",
                parametersInfo = "Galileu postulou que os movimentos ortogonais não interferem na dinâmica um do outro."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cálculo de Deslocamento por Área de Trapézio",
                codeOrFormula = "\\Delta S = \\frac{(B + b) \\cdot h}{2} = \\frac{(20 + 10) \\cdot 30}{2} = 450\\text{ m}",
                explanation = "Determinação da distância percorrida por um veículo que acelerou até 30 m/s em 10 s e manteve-se constante por mais 10 s."
            )
        ),
        goldTip = "Pegadinha clássica do ENEM: No ponto mais alto do lançamento oblíquo, a velocidade do projétil NÃO é zero! Ela vale exatamente a componente horizontal vx!"
    )

    // =========================================================================
    // AULA fis_m1_a3: Cinemática Angular e Movimento Circular Uniforme (MCU)
    // =========================================================================
    private val bundleFisM1A3 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Nem todos os movimentos na natureza ocorrem em linha reta. O giro das pás de uma turbina eólica que gera eletricidade limpa para o país, as engrenagens e correntes da bicicleta do ciclista, o tambor de centrifugação de uma máquina de lavar e a própria rotação do planeta Terra ao redor do seu eixo são manifestações do **Movimento Circular**. No ENEM, a cinemática angular e a transmissão de movimento circular entre polias e engrenagens conectadas por correntes ou eixos comuns são questões recorrentes e de alto valor pedagógico.

## 2. Objetivos de Aprendizagem
- Definir com clareza matemática **Período** (\( T \)) e **Frequência** (\( f \)), dominando a relação fundamental \( f = 1/T \) e a conversão de unidades (\( 1\text{ Hz} = 60\text{ rpm} \)).
- Compreender o conceito de radiano e a conversão entre grandezas lineares e angulares: arco \( \Delta s = \Delta\theta \cdot R \) e velocidade \( v = \omega \cdot R \).
- Calcular a Velocidade Angular (\( \omega = 2\pi / T = 2\pi f \)) no Movimento Circular Uniforme (MCU).
- Explicar por que o MCU possui **Aceleração Centrípeta** (\( a_{cp} = v^2 / R = \omega^2 R \)) mesmo tendo velocidade escalar de módulo constante.
- Dominar os dois modos de transmissão de movimento circular: **Por Eixo Comum** (mesma velocidade angular \( \omega_A = \omega_B \)) versus **Por Contato/Correia/Corrente** (mesma velocidade linear \( v_A = v_B \)).

## 3. Pré-requisitos
- Movimento Retilíneo Uniforme (Aula fis_m1_a1).
- Geometria do círculo: raio, diâmetro, comprimento da circunferência (\( C = 2\pi R \)) e noções de ângulos em radianos.

## 4. Explicação Teórica Aprofundada

### 1. Período e Frequência
- **Período (\( T \)):** É o intervalo de tempo necessário para que o corpo complete uma volta (ciclo) completa. No Sistema Internacional (SI), é medido em **segundos (s)**.
- **Frequência (\( f \)):** É o número de voltas ou ciclos completados por unidade de tempo:
  \[ f = \frac{n}{\Delta t} = \frac{1}{T} \]
  - Unidade SI: **Hertz (Hz)**, que significa 1 rotação por segundo (\( \text{s}^{-1} \)).
  - Unidade Usual: **Rotações por Minuto (rpm)**:
    \[ 1\text{ Hz} = 60\text{ rpm} \iff 1\text{ rpm} = \frac{1}{60}\text{ Hz} \]

### 2. Grandezas Angulares e Relação com Grandezas Lineares
Ao percorrer um arco de circunferência de comprimento \( \Delta s \) e raio \( R \), o móvel varre um ângulo central \( \Delta\theta \) (em radianos):
\[ \Delta s = \Delta\theta \cdot R \]
Dividindo ambos os lados pelo intervalo de tempo \( \Delta t \):
\[ \frac{\Delta s}{\Delta t} = \frac{\Delta\theta}{\Delta t} \cdot R \implies v = \omega \cdot R \]
- **Velocidade Angular (\( \omega \)):** Taxa de variação do ângulo central com o tempo (em radianos por segundo - rad/s). Como em uma volta completa o móvel varre \( 2\pi\text{ rad} \) no tempo \( T \):
  \[ \omega = \frac{2\pi}{T} = 2\pi \cdot f \]

### 3. Aceleração Centrípeta: Por que há Aceleração no MCU?
Esta é uma das pegadinhas conceituais mais clássicas:
- No MCU, o **módulo (valor numérico)** da velocidade linear é constante.
- No entanto, a **direção do vetor velocidade \( \vec{v} \) muda continuamente** a cada microssegundo para acompanhar a curva!
- Pela definição vetorial, qualquer mudança na direção de um vetor velocidade exige a presença de uma aceleração: a **Aceleração Centrípeta (\( \vec{a}_{cp} \))**:
  - *Direção:* Sempre radial (aponta na linha do raio).
  - *Sentido:* Sempre voltado para o centro da curvatura.
  - *Módulo:*
    \[ a_{cp} = \frac{v^2}{R} = \omega^2 \cdot R \]
- Como a velocidade escalar tangencial é constante, a aceleração tangencial é nula (\( a_t = 0 \)). A aceleração vetorial total do MCU é puramente a aceleração centrípeta.

### 4. Transmissão de Movimento Circular no ENEM
A transmissão mecânica de rotação opera segundo dois princípios mecânicos fundamentais:

#### A. Transmissão por Correia, Corrente ou Contato Tangencial (Ex.: Coroa e Catraca da Bicicleta)
As duas rodas estão conectadas por uma correia/corrente inextensível que não escorrega, ou engrenam dente a dente:
- **Regra Fundamental:** Os pontos periféricos movem-se com a **mesma velocidade linear**:
  \[ v_A = v_B \implies \omega_A \cdot R_A = \omega_B \cdot R_B \implies 2\pi f_A \cdot R_A = 2\pi f_B \cdot R_B \]
  \[ f_A \cdot R_A = f_B \cdot R_B \iff \frac{f_A}{f_B} = \frac{R_B}{R_A} \]
- **Conclusão:** A frequência de giro é **inversamente proporcional ao raio**! A engrenagem menor gira mais vezes por segundo do que a engrenagem maior. No caso de engrenagens com dentes (\( N \)): \( f_A \cdot N_A = f_B \cdot N_B \).
- *Sentido de Giro:* Por correia aberta, giram no mesmo sentido. Por engrenamento direto de dentes, giram em sentidos opostos.

#### B. Transmissão por Eixo Coaxial Comum (Ex.: Catraca Fixa na Roda Traseira da Bicicleta)
As duas rodas estão presas rigidamente ao mesmo eixo central giratório:
- **Regra Fundamental:** Como giram juntas presas ao mesmo eixo, completam uma volta ao mesmo tempo, possuindo o **mesmo período e a mesma velocidade angular**:
  \[ \omega_A = \omega_B \iff f_A = f_B \]
- **Relação Linear:**
  \[ \frac{v_A}{R_A} = \frac{v_B}{R_B} \implies v_A = \frac{R_A}{R_B} \cdot v_B \]
- **Conclusão:** A velocidade linear periférica é **diretamente proporcional ao raio**. Quem está mais longe do centro precisa andar mais rápido para completar a volta no mesmo tempo.

## 5. Definições e Conceitos
- **Movimento Circular Uniforme (MCU):** Trajetória circular percorrida com velocidade escalar constante e aceleração exclusivamente centrípeta.
- **Radiano:** Medida de ângulo central subtendido por um arco de circunferência cujo comprimento é exatamente igual ao raio dessa circunferência (\( 1\text{ rad} \approx 57{,}3^\circ \); \( 360^\circ = 2\pi\text{ rad} \)).
- **Catraca e Coroa:** A coroa é a engrenagem dentada dianteira acionada pelos pedais; a catraca é a engrenagem traseira acoplada ao eixo da roda da bicicleta.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Marchas de Bicicleta no ENEM
Um ciclista pedala em uma bicicleta cuja coroa dianteira tem raio \( R_{\text{coroa}} = 15\text{ cm} \) e está ligada por corrente a uma catraca traseira de raio \( R_{\text{catraca}} = 5\text{ cm} \). A catraca está coaxialmente presa à roda traseira, cujo raio é \( R_{\text{roda}} = 40\text{ cm} \). Se o ciclista pedala a uma frequência constante de \( 2\text{ rotações por segundo} \) (\( f_{\text{pedal}} = 2\text{ Hz} \)), calcule a velocidade linear de deslocamento da bicicleta. (Adote \( \pi = 3 \)).
- **Passo 1 (Transmissão Coroa -> Catraca por Corrente):** Como a corrente não desliza, a velocidade linear na periferia é idêntica:
  \[ f_{\text{coroa}} \cdot R_{\text{coroa}} = f_{\text{catraca}} \cdot R_{\text{catraca}} \]
  \[ 2 \times 15 = f_{\text{catraca}} \times 5 \implies 30 = 5 f_{\text{catraca}} \implies f_{\text{catraca}} = 6\text{ Hz} \]
  (A catraca dá 6 giros por segundo).
- **Passo 2 (Transmissão Catraca -> Roda por Eixo Comum):** Como a catraca e a roda traseira compartilham o mesmo eixo físico, giram com a mesma frequência:
  \[ f_{\text{roda}} = f_{\text{catraca}} = 6\text{ Hz} \]
- **Passo 3 (Velocidade Linear da Bicicleta):** A velocidade do ciclista no solo é a velocidade tangencial da periferia da roda:
  \[ v = \omega_{\text{roda}} \cdot R_{\text{roda}} = (2\pi f_{\text{roda}}) \cdot R_{\text{roda}} \]
  \[ v = 2 \times 3 \times 6\text{ s}^{-1} \times 0{,}4\text{ m} = 36 \times 0{,}4 = 14{,}4\text{ m/s} \]
- **Passo 4 (Conversão para km/h):**
  \[ v = 14{,}4 \times 3{,}6 = 51{,}84\text{ km/h} \]

## 7. Aplicações Práticas e Modelagem
- **Centrífugas de Laboratório:** Utilizam frequências de rotação ultrarrápidas (\( 10.000\text{ rpm} \)) para gerar acelerações centrípetas milhares de vezes superiores à gravidade da Terra, sedimentando em minutos plaquetas, hemácias e macromoléculas de DNA no fundo de tubos de ensaio.
- **Relação de Marchas no Ciclismo de Estrada:** Para subir ladeiras íngremes com menor esforço muscular, o ciclista seleciona a menor coroa dianteira e a maior catraca traseira (reduzindo a velocidade, mas multiplicando o torque mecânico).

## 8. Exercícios de Fixação
1. Um motor elétrico gira com frequência de \( 1.800\text{ rpm} \). Qual é a sua frequência em Hertz (Hz) e qual o seu período em segundos?
2. Por que um corpo em Movimento Circular Uniforme possui aceleração se o velocímetro do carro marca sempre 60 km/h constantes durante a curva?
3. Duas polias de raios \( 10\text{ cm} \) e \( 30\text{ cm} \) estão ligadas por uma correia. Se a polia menor gira a \( 12\text{ Hz} \), qual é a frequência da polia maior?

## 9. Correção Comentada dos Exercícios
1. \( f = 1.800 / 60 = 30\text{ Hz} \). O período é \( T = 1/f = 1/30 \approx 0{,}033\text{ segundos} \).
2. Porque a aceleração é uma grandeza vetorial. Embora o módulo numérico permaneça constante em 60 km/h, a direção do vetor velocidade está continuamente virando no espaço; essa taxa de variação de direção é dada pela aceleração centrípeta.
3. Transmissão por correia: \( f_1 \cdot R_1 = f_2 \cdot R_2 \implies 12 \times 10 = f_2 \times 30 \implies 120 = 30 f_2 \implies f_2 = 4\text{ Hz} \).

## 10. Desafio de Domínio
A Terra realiza uma rotação completa sobre seu próprio eixo em aproximadamente 24 horas. Considerando o raio da Terra de \( 6.400\text{ km} \), compare a velocidade angular e a velocidade linear de uma pessoa no equador com uma pessoa localizada em uma base científica no Polo Sul geográfico.

## 11. Resumo e Pontos-Chave
- \( f = 1/T \); Unidades: \( 1\text{ Hz} = 60\text{ rpm} \).
- Velocidade angular: \( \omega = 2\pi / T = 2\pi f \). Velocidade linear: \( v = \omega \cdot R \).
- Aceleração Centrípeta: \( a_{cp} = v^2 / R = \omega^2 R \) (aponta sempre para o centro).
- Correia / Corrente / Dentes: Mesma velocidade linear (\( v_A = v_B \implies f_A R_A = f_B R_B \)).
- Eixo Coaxial Comum: Mesma velocidade angular e frequência (\( \omega_A = \omega_B \implies f_A = f_B \)).

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Mecânica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- TIPLER, P. A.; MOSCA, G. *Física para Cientistas e Engenheiros: Volume 1*. 6ª ed. Rio de Janeiro: LTC, 2009.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Aceleração Centrípeta",
                syntax = "a_{cp} = \\frac{v^2}{R} = \\omega^2 \\cdot R",
                description = "Aceleração perpendicular à velocidade responsável por alterar a direção da trajetória curva.",
                parametersInfo = "v é velocidade linear (m/s), ω é velocidade angular (rad/s) e R é o raio da trajetória (m)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Transmissão por Correia ou Engrenagem",
                syntax = "v_1 = v_2 \\implies f_1 \\cdot R_1 = f_2 \\cdot R_2 \\iff f_1 \\cdot N_1 = f_2 \\cdot N_2",
                description = "A velocidade linear na borda é comum, resultando em frequências inversamente proporcionais aos raios ou números de dentes.",
                parametersInfo = "A roda menor sempre gira com maior frequência que a roda maior."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Acoplamento de Catraca e Coroa de Bicicleta",
                codeOrFormula = "f_{\\text{coroa}} R_{\\text{coroa}} = f_{\\text{catraca}} R_{\\text{catraca}} \\implies 2 \\times 15 = f_{\\text{cat}} \\times 5 \\implies f_{\\text{cat}} = 6\\text{ Hz}",
                explanation = "A corrente transmite a mesma velocidade linear periférica, multiplicando a rotação da catraca traseira."
            )
        ),
        goldTip = "Regra prática para engrenagens no ENEM: Por correia/corrente, a menor gira MAIS rápido (mesmo v). Presas no mesmo ferro/eixo, giram JUNTAS no mesmo tempo (mesmo f e ω)!"
    )

    // =========================================================================
    // AULA fis_m1_a4: Dinâmica e Leis de Newton
    // =========================================================================
    private val bundleFisM1A4 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Se a cinemática nos ensinou a descrever como os corpos se movem, a **Dinâmica** responde à pergunta fundamental: *por que os corpos se movem?* Formuladas por Sir Isaac Newton em 1687 em sua monumental obra *Philosophiae Naturalis Principia Mathematica*, as Três Leis de Newton constituem os pilares da mecânica clássica. No ENEM, compreender a inércia, a relação causal entre força resultante e aceleração, o par indissociável de ação e reação, e dominar as forças de atrito estático e dinâmico é pré-requisito indispensável para qualquer questão de engenharia, trânsito ou biomecânica humana.

## 2. Objetivos de Aprendizagem
- Enunciar e aplicar a **Primeira Lei de Newton (Lei da Inércia)** e sua relação com a massa e cintos de segurança.
- Dominar a **Segunda Lei de Newton (Princípio Fundamental da Dinâmica)**: \( \vec{F}_{\text{res}} = m \cdot \vec{a} \).
- Compreender a **Terceira Lei de Newton (Ação e Reação)**: forças ocorrem em pares de mesmo módulo, mesma direção, sentidos opostos, atuando em **corpos diferentes** (nunca se anulam mutuamente).
- Construir Diagramas de Corpo Livre (isolamento de forças: Peso, Normal, Tração, Atrito e Elástica).
- Diferenciar com exatidão física o **Atrito Estático Máximo** (\( f_{at,e} \le \mu_e \cdot N \)) do **Atrito Cinético** (\( f_{at,c} = \mu_c \cdot N \)).
- Compreender a Lei de Hooke para molas e forças elásticas (\( F_{\text{el}} = k \cdot x \)).
- Identificar a Força Resultante Centrípeta (\( F_{cp} = m \cdot v^2 / R \)) em curvas de rodovias e montanhas-russas.

## 3. Pré-requisitos
- Cinemática escalar e vetorial (Aulas fis_m1_a1 e fis_m1_a2).
- Operações de soma e decomposição de vetores.

## 4. Explicação Teórica Aprofundada

### 1. As Três Leis Fundamentais de Newton
1. **1ª Lei (Princípio da Inércia):**
   > *"Todo corpo persevera em seu estado de repouso ou de movimento retilíneo e uniforme, a menos que seja compelido a mudar seu estado por forças impressas sobre ele."*
   - Em termos modernos: se a Força Resultante sobre uma partícula for nula (\( \vec{F}_{\text{res}} = \vec{0} \)), sua aceleração é zero (\( \vec{a} = \vec{0} \)). Ela permanece em repouso se já estava parada, ou mantém MRU eterno se já estava em movimento.
   - **Massa é a medida quantitativa da Inércia:** Quanto maior a massa de um corpo, mais difícil é alterar sua velocidade.
2. **2ª Lei (Princípio Fundamental da Dinâmica):**
   > *"A mudança do movimento é proporcional à força motriz impressa e se faz segundo a linha reta pela qual essa força é impressa."*
   \[ \vec{F}_{\text{res}} = m \cdot \vec{a} \]
   - No SI: Força em **Newtons (N)**, Massa em **quilogramas (kg)** e Aceleração em **\( \text{m/s}^2 \)** (\( 1\text{ N} = 1\text{ kg}\cdot\text{m/s}^2 \)).
   - A aceleração possui **sempre a mesma direção e o mesmo sentido da força resultante**.
3. **3ª Lei (Princípio da Ação e Reação):**
   > *"A toda ação opõe-se sempre uma reação de igual intensidade e direção, mas de sentido contrário."*
   - Se o corpo A aplica uma força sobre o corpo B (\( \vec{F}_{A \to B} \)), o corpo B aplica simultaneamente sobre A uma força (\( \vec{F}_{B \to A} \)) tal que:
     \[ \vec{F}_{A \to B} = -\vec{F}_{B \to A} \quad (|\vec{F}_{A \to B}| = |\vec{F}_{B \to A}|) \]
   - **REGRA DE OURO DO ENEM:** As forças do par ação-reação **NUNCA SE ANULAM**, pois são aplicadas em **CORPOS DISTINTOS**! (Ex.: A Terra puxa a maçã para baixo com a Força Peso; a maçã puxa o centro da Terra para cima com uma força de mesmo módulo e sentido oposto).

### 2. O Catálogo das Forças Mecânicas
- **Força Peso (\( \vec{P} \)):** Força de atração gravitacional exercida pelo planeta Terra:
  \[ P = m \cdot g \]
- **Força Normal (\( \vec{N} \)):** Força de reação perpendicular exercida por uma superfície quando comprimida. **Atenção:** Normal e Peso **NÃO formam um par de ação e reação**! Ambas atuam sobre o mesmo corpo (a Normal é aplicada pela superfície; o Peso é aplicado pela Terra).
- **Força de Tração (\( \vec{T} \)):** Força exercida através de fios, cordas ou cabos ideais inextensíveis.
- **Força Elástica (Lei de Hooke):**
  \[ F_{\text{el}} = k \cdot x \]
  Onde \( k \) é a constante elástica da mola (em N/m) e \( x \) é a deformação (alongamento ou compressão).

### 3. A Dinâmica do Atrito: Estático vs. Cinético
O atrito decorre das microrrugosidades entre as superfícies de contato:
- **Atrito Estático (\( f_{at,e} \)):** Atua quando as superfícies em contato **NÃO deslizam** entre si (há tendência de movimento). É uma força auto-ajustável que equilibra a força externa aplicada até um teto máximo de destaque:
  \[ f_{at,e} \le f_{at,e}^{\text{máx}} = \mu_e \cdot N \]
- **Atrito Cinético ou Dinâmico (\( f_{at,c} \)):** Atua a partir do momento em que as superfícies entram em **escorregamento relativo mútuo**:
  \[ f_{at,c} = \mu_c \cdot N \]
- **Propriedade Fundamental:** Como é sempre mais fácil manter um móvel escorregando do que tirá-lo do repouso inicial, o coeficiente de atrito estático é rigorosamente maior que o cinético (\( \mu_e > \mu_c \)).

### 4. Força Resultante Centrípeta
A força centrípeta não é uma força nova que surge do nada na natureza; ela é simplesmente o **papel dinâmico da força resultante na direção perpendicular (radial)**:
\[ F_{\text{res, radial}} = F_{cp} = m \cdot a_{cp} = \frac{m \cdot v^2}{R} \]
- Em um carro fazendo curva horizontal em pista plana: o **atrito estático dos pneus** desempenha o papel de força centrípeta (\( f_{at} = m v^2 / R \)).
- Em um satélite em órbita: a **gravidade** desempenha o papel de força centrípeta.
- No ponto mais baixo de uma montanha-russa: a resultante é \( N - P = m v^2 / R \implies N = P + \frac{m v^2}{R} \) (o passageiro sente-se mais pesado, sensação de força g positiva).

## 5. Definições e Conceitos
- **Inércia:** Propriedade intrínseca da matéria de resistir a variações em seu estado de repouso ou velocidade vetorial.
- **Diagrama de Corpo Livre:** Esquema gráfico isolando o corpo e desenhando todos os vetores de força externos que atuam exclusivamente sobre ele.
- **Coeficiente de Atrito (\( \mu \)):** Número adimensional que quantifica a rugosidade de interação entre dois materiais específicos.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Indicação da Balança no Elevador no ENEM
Uma pessoa de massa \( m = 70\text{ kg} \) está de pé sobre uma balança de mola graduada em Newtons no piso de um elevador. Adotando \( g = 10\text{ m/s}^2 \), determine a leitura indicada pela balança quando:
a) O elevador sobe acelerando com \( a = 2\text{ m/s}^2 \).
b) O elevador desce freando com desaceleração de \( 2\text{ m/s}^2 \).
c) O cabo do elevador se rompe e ele entra em queda livre.
- **Passo 1 (Princípio da Balança):** A balança não mede o peso \( P \); a balança mede a compressão sobre seu prato, isto é, a **Força Normal (\( N \))** que ela exerce sobre os pés da pessoa.
- **Passo 2 (Caso A - Sobe Acelerando):** A aceleração aponta para cima (\( \vec{a} \uparrow \)). Logo, a força para cima deve vencer a para baixo:
  \[ F_{\text{res}} = N - P = m \cdot a \implies N = P + m \cdot a = m(g + a) \]
  \[ N = 70 \cdot (10 + 2) = 70 \times 12 = 840\text{ Newtons} \]
  (A pessoa sente-se mais pesada que seu peso real de 700 N).
- **Passo 3 (Caso B - Desce Freando):** O elevador está se deslocando para baixo, mas como está freando, o vetor aceleração aponta **para cima** (\( \vec{a} \uparrow \))!
  \[ F_{\text{res}} = N - P = m \cdot a \implies N = 70 \times (10 + 2) = 840\text{ Newtons} \]
  (Exatamente a mesma leitura do caso A!).
- **Passo 4 (Caso C - Queda Livre):** O elevador e a pessoa caem juntos com aceleração \( a = g \) para baixo:
  \[ P - N = m \cdot g \implies m \cdot g - N = m \cdot g \implies N = 0\text{ Newtons} \]
  (Estado de **Imponderabilidade**: a pessoa flutua e a balança marca zero).

## 7. Aplicações Práticas e Modelagem
- **Cinto de Segurança e Airbag:** O cinto aplica força externa para desacelerar o corpo do passageiro junto com o chassi, impedindo que ele continue seu movimento em linha reta por pura **inércia** contra o para-brisa. O airbag infla em milissegundos para amortecer o impacto e prolongar o tempo de parada.
- **Curvas com Pista Inclinada (Superelevação):** Em pistas de corrida e ferrovias, a pista é inclinada com um ângulo \( \theta \) de modo que uma componente da Força Normal (\( N \cdot \sin\theta \)) forneça a força centrípeta necessária para o veículo fazer a curva em alta velocidade mesmo na ausência de atrito (pista com gelo ou óleo).

## 8. Exercícios de Fixação
1. Por que o par de forças de Ação e Reação jamais pode se anular em um cálculo dinâmico?
2. Um bloco de 10 kg está sobre uma mesa horizontal com atrito estático \( \mu_e = 0{,}4 \) e cinético \( \mu_c = 0{,}3 \). Se aplicarmos uma força horizontal de 30 N, qual será o valor da força de atrito e qual a aceleração do bloco? (Adote \( g = 10\text{ m/s}^2 \)).
3. Qual é a força exercida pela Terra sobre a Lua em comparação com a força exercida pela Lua sobre a Terra?

## 9. Correção Comentada dos Exercícios
1. Porque para duas forças se anularem mutuamente por soma vetorial, elas precisam ser aplicadas simultaneamente sobre o **mesmo corpo**. O par de ação e reação é aplicado em **corpos diferentes** (um atua no corpo A, o outro no corpo B).
2. Força Normal \( N = P = 10 \times 10 = 100\text{ N} \). Força de atrito estático máxima: \( f_{at,e}^{\text{máx}} = \mu_e \cdot N = 0{,}4 \times 100 = 40\text{ N} \). Como a força aplicada (30 N) é MENOR que o atrito máximo de destaque (40 N), o bloco **NÃO sai do lugar**: aceleração \( a = 0 \) e o atrito estático ajusta-se exatamente igual à força externa: \( f_{at} = 30\text{ N} \).
3. Pela Terceira Lei de Newton, ambas as forças possuem exatamente o **mesmo módulo**, a mesma direção e sentidos opostos. A atração que a Terra exerce na Lua é rigorosamente igual à atração que a Lua exerce na Terra.

## 10. Desafio de Domínio
Analise as forças que atuam sobre um automóvel com tração nas rodas dianteiras (tração dianteira) que acelera para a frente em uma rodovia seca. Indique a direção e o sentido das forças de atrito estático exercidas pelo asfalto sobre os pneus dianteiros e sobre os pneus traseiros.

## 11. Resumo e Pontos-Chave
- 1ª Lei (Inércia): Sem força resultante (\( F_{\text{res}} = 0 \)), o corpo fica em repouso ou MRU.
- 2ª Lei: \( \vec{F}_{\text{res}} = m \cdot \vec{a} \) (aceleração na direção da força líquida).
- 3ª Lei: Ação e Reação têm mesmo módulo, mesma direção, sentidos opostos e atuam em corpos distintos (NUNCA se anulam).
- Normal e Peso NÃO são ação e reação (ambas atuam sobre o mesmo corpo).
- Atrito Estático Máximo: \( f_{at,e}^{\text{máx}} = \mu_e \cdot N \). Atrito Cinético: \( f_{at,c} = \mu_c \cdot N \) (\( \mu_e > \mu_c \)).
- Força Centrípeta: Papel resultante para o centro da curva (\( F_{cp} = m v^2 / R \)).

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Mecânica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- SEARS, F.; ZEMANSKY, M.; YOUNG, H. D.; FREEDMAN, R. A. *Física I: Mecânica*. 14ª ed. São Paulo: Pearson, 2016.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Segunda Lei de Newton",
                syntax = "\\vec{F}_{\\text{res}} = \\sum \\vec{F} = m \\cdot \\vec{a}",
                description = "Princípio fundamental que relaciona a aceleração vetorial adquirida pela massa à força resultante aplicada.",
                parametersInfo = "No SI: Força em Newtons (N), massa em kg e aceleração em m/s²."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Leis do Atrito Sólido",
                syntax = "f_{at,e} \\le \\mu_e \\cdot N \\quad | \\quad f_{at,c} = \\mu_c \\cdot N \\quad (\\mu_e > \\mu_c)",
                description = "O atrito estático equilibra a solicitação externa até o valor máximo de destaque; o atrito cinético é constante durante o deslizamento.",
                parametersInfo = "N é a força de compressão normal entre as superfícies e μ é o coeficiente de atrito."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Bloco com Força Abaixo do Atrito de Destaque",
                codeOrFormula = "f_{at,e}^{\\text{máx}} = 0{,}4 \\times 100 = 40\\text{ N} \\implies F_{\\text{aplicada}} = 30\\text{ N} < 40\\text{ N} \\implies f_{at} = 30\\text{ N} \\text{ e } a = 0",
                explanation = "Se a força aplicada não ultrapassa o atrito de destaque máximo, o corpo não se move e o atrito estático assume exatamente o valor da força aplicada."
            )
        ),
        goldTip = "Pegadinha de ouro do ENEM: Ação e Reação NUNCA se anulam porque atuam em corpos diferentes! E a força Normal NUNCA é a reação do Peso!"
    )

    // =========================================================================
    // AULA fis_m1_a5: Máquinas Simples e Aplicações
    // =========================================================================
    private val bundleFisM1A5 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Desde as grandes obras da antiguidade clássica até as modernas gruas de construção civil de arranha-céus, o ser humano desenvolveu dispositivos engenhosos capazes de multiplicar forças e facilitar a realização de trabalho: as **Máquinas Simples**. No ENEM, a física das máquinas simples — compreendendo roldanas fixas e móveis, talhas exponenciais, o plano inclinado e as alavancas do corpo humano — é cobrada com foco na **Vantagem Mecânica**, no equilíbrio estático e na constatação irrevogável da conservação da energia: *uma máquina simples pode economizar força, mas jamais economiza energia ou trabalho*.

## 2. Objetivos de Aprendizagem
- Definir o conceito de **Vantagem Mecânica Teórica e Real** (\( VM = F_R / F_P \)).
- Compreender o funcionamento de **Roldanas Fixas** (apenas mudam a direção e o sentido da força, \( VM = 1 \)) e **Roldanas Móveis** (dividem o esforço pela metade, \( F = P / 2 \)).
- Deduzir e aplicar a equação da **Talha Exponencial** com \( n \) polias móveis: \( F = P / 2^n \).
- Decompor as forças atuantes no **Plano Inclinado**: força tangencial \( P_x = P \cdot \sin\theta \) e força normal \( P_y = P \cdot \cos\theta \).
- Classificar e resolver as **Alavancas**: Interfixas (ex.: tesoura, gangorra), Inter-resistentes (ex.: carrinho de mão, quebra-nozes) e Interpotentes (ex.: pinça, antebraço humano).
- Compreender a compensação geométrica entre Força e Deslocamento ditada pela conservação do trabalho.

## 3. Pré-requisitos
- Leis de Newton e decomposição vetorial de forças (Aula fis_m1_a4).
- Trigonometria básica e conceito de torque (momento de uma força).

## 4. Explicação Teórica Aprofundada

### 1. O Princípio Áureo da Conservação da Energia nas Máquinas
Uma máquina simples ideal (livre de perdas por atrito e com cabos inextensíveis de massa desprezível) não altera o Trabalho total (\( W \)) necessário para erguer uma carga:
\[ W = F_{\text{potente}} \cdot d_{\text{potente}} = F_{\text{resistente}} \cdot d_{\text{resistente}} \]
- Se uma máquina simples divide a força requerida por 4 (\( F = P/4 \)), a mão do operador precisará puxar uma distância **4 vezes maior de corda** (\( d = 4h \))!
- **Conclusão:** Ganha-se em conforto biomecânico (menor esforço muscular instantâneo), mas paga-se exatamente a mesma quantidade de joules de energia puxando mais comprimento.

### 2. Roldanas (Polias)
1. **Roldana Fixa:** O eixo central da polia é preso a uma estrutura fixa (teto, viga).
   - O cabo passa pela roldana e o módulo da força potente aplicada é **exatamente igual ao peso da carga**:
     \[ F = P \]
   - *Utilidade:* Não multiplica força (\( VM = 1 \)), mas altera a direção e o sentido da aplicação (puxar uma corda para baixo aproveitando o peso do próprio corpo do operário é muito mais ergonômico do que puxar para cima).
2. **Roldana Móvel:** O eixo central da polia é acoplado diretamente à carga móvel.
   - Como a carga está sustentada por duas pernas de corda paralelas e a tração é a mesma ao longo de todo o fio ideal, cada ramo de corda sustenta metade da carga:
     \[ F = \frac{P}{2} \]
   - *Vantagem Mecânica:* Divide a força necessária por 2 (\( VM = 2 \)). Em contrapartida, para elevar a carga de uma altura \( h \), o operador precisa puxar um comprimento \( 2h \) de corda.
3. **Talha Exponencial:** Arranjo composto por 1 roldana fixa e uma série de \( n \) roldanas móveis associadas sucessivamente:
   \[ F = \frac{P}{2^n} \]
   - Com 1 polia móvel: \( F = P/2 \).
   - Com 3 polias móveis: \( F = P/2^3 = P/8 \) (reduz a força a um oitavo!).
   - Com 5 polias móveis: \( F = P/2^5 = P/32 \).
   - *Comprimento de corda puxado:* \( d = 2^n \cdot h \).

### 3. O Plano Inclinado
O plano inclinado permite elevar uma carga pesada até uma altura \( h \) aplicando uma rampa de comprimento \( L \):
- Decomposição do Peso na rampa inclinada de ângulo \( \theta \) com o solo:
  - Componente paralela à rampa (tende a fazer o corpo descer):
    \[ P_x = P \cdot \sin\theta = m \cdot g \cdot \sin\theta \]
  - Componente perpendicular à rampa (comprime a superfície):
    \[ P_y = P \cdot \cos\theta = m \cdot g \cdot \cos\theta \implies N = P_y = m \cdot g \cdot \cos\theta \]
- **Sem atrito:** A força potente mínima necessária para empurrar a carga rampa acima com velocidade constante é:
  \[ F = P_x = P \cdot \sin\theta = P \cdot \frac{h}{L} \]
  Como \( L > h \), a força necessária é menor do que erguer verticalmente.

### 4. Alavancas e o Princípio de Arquimedes
Uma barra rígida que pode girar ao redor de um Ponto de Apoio (Fulcro - \( O \)):
- *Força Potente (\( F_P \)):* Força aplicada pelo operador com braço de alavanca \( b_P \).
- *Força Resistente (\( F_R \)):* Peso da carga ou resistência a ser superada com braço \( b_R \).
- *Condição de Equilíbrio dos Momentos (Torque):*
  \[ F_P \cdot b_P = F_R \cdot b_R \implies F_P = F_R \cdot \frac{b_R}{b_P} \]
- **Classificação das Alavancas (Memorização essencial):**
  1. **Interfixa:** O Ponto de Apoio fica **no meio**, entre a força potente e a resistente (ex.: tesoura, alicate, gangorra, articulação da cabeça sobre a coluna cervical).
  2. **Inter-resistente:** A Força Resistente (a carga) fica **no meio**, entre o apoio e a força potente (ex.: carrinho de mão, quebra-nozes, abridor de garrafas, movimento de ficar na ponta dos pés pelo tendão de Aquiles). Sempre possui \( b_P > b_R \), logo **SEMPRE multiplica a força** (\( VM > 1 \)).
  3. **Interpotente:** A Força Potente fica **no meio**, entre o apoio e a resistência (ex.: pinça, pegador de gelo, vassoura, o músculo bíceps braquial flexionando o antebraço). Como \( b_P < b_R \), a força muscular precisa ser maior que o peso erguido (\( VM < 1 \)), mas ganha-se em amplitude e velocidade de movimento!

## 5. Definições e Conceitos
- **Vantagem Mecânica (\( VM \)):** Razão matemática entre a força resistente superada e a força potente efetivamente aplicada (\( VM = F_R / F_P \)).
- **Torque ou Momento de uma Força:** Tendência de rotação que uma força produz sobre um corpo ao redor de um eixo (\( M = F \cdot d \cdot \sin\theta \)).
- **Braço de Alavanca:** Distância perpendicular do eixo de rotação até a linha de ação da força.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Talha Exponencial no Canteiro de Obras
Um operário precisa erguer um bloco de concreto de massa \( m = 240\text{ kg} \) até o segundo andar de um edifício. Ele dispõe de um sistema de talha exponencial contendo 3 roldanas móveis e 1 roldana fixa. Adotando \( g = 10\text{ m/s}^2 \), determine:
a) A força que o operário precisa exercer para erguer o bloco com velocidade constante.
b) O comprimento de corda que ele precisa puxar para que o bloco suba uma altura de \( 2\text{ metros} \).
- **Passo 1 (Cálculo do Peso da Carga):**
  \[ P = m \cdot g = 240\text{ kg} \times 10\text{ m/s}^2 = 2.400\text{ Newtons} \]
- **Passo 2 (Aplicação da Fórmula da Talha Exponencial):** Como há \( n = 3 \) roldanas móveis:
  \[ F = \frac{P}{2^n} = \frac{2.400}{2^3} = \frac{2.400}{8} = 300\text{ Newtons} \]
  (O operário faz um esforço correspondente a erguer apenas 30 kg, uma multiplicação de força de 8 vezes!).
- **Passo 3 (Comprimento de Corda Puxado):** A conservação do trabalho exige que o deslocamento seja multiplicado na mesma proporção em que a força foi dividida:
  \[ d_{\text{corda}} = 2^n \cdot h = 2^3 \times 2\text{ m} = 8 \times 2 = 16\text{ metros de corda} \]
  O operário puxa 16 metros de corda fazendo força suave de 300 N, realizando trabalho idêntico ao de puxar 2.400 N por 2 metros (\( W = 300 \times 16 = 2.400 \times 2 = 4.800\text{ Joules} \)).

## 7. Aplicações Práticas e Modelagem
- **Biomecânica do Corpo Humano:** O sistema locomotor é uma impressionante coleção de alavancas anatômicas. O músculo bíceps é uma alavanca interpotente que se insere a poucos centímetros do cotovelo (apoio) para segurar uma carga na mão a 30 cm de distância, exigindo tensões musculares colossais.
- **Rampas de Acessibilidade (Norma ABNT NBR 9050):** A inclinação suave das rampas para cadeirantes diminui a componente \( P_x = P \cdot \sin\theta \), permitindo que uma pessoa cadeirante suba com seu próprio esforço braçal.

## 8. Exercícios de Fixação
1. Qual a diferença fundamental de funcionamento entre uma roldana fixa e uma roldana móvel?
2. Em um sistema com 4 roldanas móveis associadas em talha exponencial, qual força é necessária para suspender uma carga de 1.600 N?
3. Classifique o tipo de alavanca de: a) um alicate comum; b) um quebra-nozes; c) uma pinça de sobrancelha.

## 9. Correção Comentada dos Exercícios
1. A roldana fixa não multiplica a força (\( F = P \)), apenas altera a direção e o sentido da tração. A roldana móvel divide a força requerida pela metade (\( F = P/2 \)), exigindo puxar o dobro do comprimento de corda.
2. Fórmula da talha exponencial: \( F = P / 2^n = 1.600 / 2^4 = 1.600 / 16 = 100\text{ Newtons} \).
3. a) Alicate: **Interfixa** (apoio central entre os dedos e a lâmina); b) Quebra-nozes: **Inter-resistente** (a noz resistente fica entre o apoio frontal e a mão); c) Pinça: **Interpotente** (a força dos dedos é aplicada no meio da haste flexível, entre o ponto de união e a ponta).

## 10. Desafio de Domínio
Analise um carrinho de mão de construção civil carregado com 100 kg de terra. O centro de gravidade da terra dista 50 cm da roda dianteira (apoio), e as mãos do pedreiro seguram os cabos a 1,50 m da roda. Qual a força vertical que o pedreiro precisa exercer para levantar o carrinho? Demonstre o cálculo do torque estático.

## 11. Resumo e Pontos-Chave
- Máquinas simples multiplicam forças, mas JAMAIS economizam trabalho ou energia (\( W = F \cdot d = \text{constante} \)).
- Roldana Fixa: \( F = P \); Roldana Móvel: \( F = P/2 \).
- Talha Exponencial: \( F = P / 2^n \) (onde \( n \) é o número de polias móveis).
- Plano Inclinado: \( P_x = P \sin\theta \), \( N = P_y = P \cos\theta \).
- Alavancas:
  - *Interfixa:* Apoio no meio (tesoura, gangorra).
  - *Inter-resistente:* Carga no meio (carrinho de mão, quebra-nozes) -> sempre multiplica força.
  - *Interpotente:* Força potente no meio (pinça, bíceps) -> ganha velocidade, mas exige mais força.

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Mecânica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- SEARS, F.; ZEMANSKY, M.; YOUNG, H. D.; FREEDMAN, R. A. *Física I: Mecânica*. 14ª ed. São Paulo: Pearson, 2016.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação da Talha Exponencial",
                syntax = "F_{\\text{potente}} = \\frac{P_{\\text{carga}}}{2^n} \\quad | \\quad d_{\\text{corda}} = 2^n \\cdot h",
                description = "Cada polia móvel adicional divide a força necessária pela metade, dobrando o percurso de corda tracionado.",
                parametersInfo = "n é a quantidade de roldanas móveis presentes no dispositivo mecânico."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Equilíbrio Estático de Alavancas (Torque)",
                syntax = "F_P \\cdot b_P = F_R \\cdot b_R",
                description = "Condição de equilíbrio rotacional onde a soma dos momentos potentes anula a soma dos momentos resistentes.",
                parametersInfo = "bP e bR são as distâncias perpendiculares da força potente e resistente ao ponto de apoio."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Alívio de Esforço com Talha de 3 Polias Móveis",
                codeOrFormula = "F = \\frac{2.400\\text{ N}}{2^3} = \\frac{2.400}{8} = 300\\text{ N} \\quad (d = 2^3 \\times 2 = 16\\text{ m})",
                explanation = "A carga de 2.400 N é içada aplicando apenas 300 N, demandando 16 metros de corda esticada pelo operador."
            )
        ),
        goldTip = "Regra mnemônica de ouro para Alavancas: 'O que está no meio define o nome!' Apoio no meio = Inter-FIXA. Resistência no meio = Inter-RESISTENTE. Potência (mão) no meio = Inter-POTENTE!"
    )
}

