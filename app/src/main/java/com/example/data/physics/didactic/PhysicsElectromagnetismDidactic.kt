package com.example.data.physics.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Física ENEM — Módulo 5: Eletricidade e Magnetismo (Aulas fis_m2_a1 a fis_m2_a6).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em Halliday & Resnick, Sears & Zemansky, Tipler e Matriz ENEM / INEP.
 */
object PhysicsElectromagnetismDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "fis_m2_a1" -> bundleFisM2A1
            "fis_m2_a2" -> bundleFisM2A2
            "fis_m2_a3" -> bundleFisM2A3
            "fis_m2_a4" -> bundleFisM2A4
            "fis_m2_a5" -> bundleFisM2A5
            "fis_m2_a6" -> bundleFisM2A6
            else -> null
        }
    }

    // =========================================================================
    // AULA fis_m2_a1: Eletrodinâmica - Corrente, Tensão e Potência Elétrica
    // =========================================================================
    private val bundleFisM2A1 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A **Eletrodinâmica** é o campeão histórico absoluto de incidência em Física no ENEM, respondendo por mais de 20% de todas as questões da disciplina nas duas últimas décadas. Nenhuma outra área conecta com tanta força a teoria física fundamental à vida prática do cidadão contemporâneo: do consumo em quilowatts-hora (\( \text{kWh} \)) na fatura de energia elétrica residencial, à segurança no uso de chuveiros, ar-condicionados e carregadores ultrarrápidos de smartphones. Esta aula inaugura o estudo do movimento ordenado de portadores de carga elétrica e o transporte de energia eletromagnética.

## 2. Objetivos de Aprendizagem
- Definir a **Carga Elétrica Elementar** (\( e = 1{,}6 \times 10^{-19}\text{ C} \)) e a quantização da carga (\( Q = n \cdot e \)).
- Definir **Corrente Elétrica Média** (\( i = \frac{\Delta Q}{\Delta t} \)) na unidade Ampère (\( \text{A} = \text{C/s} \)).
- Diferenciar o **sentido real dos elétrons** do **sentido convencional da corrente**.
- Compreender o conceito de **Diferença de Potencial Elétrico (ddp)** ou **Tensão Elétrica** (\( U \)) em Volts (\( \text{V} = \text{J/C} \)).
- Deduzir e aplicar as equações fundamentais da **Potência Elétrica**: \( P = U \cdot i \).
- Calcular o **Consumo de Energia Elétrica** (\( E = P \cdot \Delta t \)) em Joules e em Quilowatts-hora (\( \text{kWh} \)), projetando o custo financeiro na conta de luz.
- Interpretar dados técnicos e placas de identificação de aparelhos eletrodomésticos.

## 3. Pré-requisitos
- Trabalho mecânico e potência (Física, Aula fis_m1_a6).
- Operações com potências de 10 e unidades do Sistema Internacional (Matemática Básica, Aula mb_l04).

## 4. Explicação Teórica Aprofundada

### 1. Carga Elétrica e Quantização
A matéria ordinária é composta por átomos cujos constituintes elétricos estáveis são os prótons (carga positiva \( +e \)) e os elétrons (carga negativa \( -e \)):
- **Carga Elementar:**
  \[ e \approx 1{,}6 \times 10^{-19}\text{ C} \]
- **Princípio da Quantização:** A carga elétrica líquida de qualquer corpo macroscópico é sempre um múltiplo inteiro da carga elementar:
  \[ Q = n \cdot e \]
  onde \( n \) é o número em excesso ou falta de elétrons. Um Coulomb (\( 1\text{ C} \)) corresponde a um pacote colossal de \( \approx 6{,}25 \times 10^{18} \) elétrons!

### 2. Corrente Elétrica (\( i \))
Em um condutor metálico isolado, os elétrons livres executam um movimento térmico caótico desordenado com velocidades altíssimas (\( \sim 10^6\text{ m/s} \)), sem fluxo líquido em nenhuma direção. Ao aplicar uma diferença de potencial elétrico (\( \Delta V = U \)) entre as extremidades do fio, estabelece-se um campo elétrico interno que impulsiona os elétrons em um **movimento ordenado coletivo** com velocidade de deriva lenta (\( v_d \sim 1\text{ mm/s} \)): esse fluxo ordenado é a **Corrente Elétrica**.
- **Intensidade de Corrente Elétrica:** Mede a quantidade líquida de carga que atravessa a seção reta transversal do condutor por unidade de tempo:
  \[ i = \frac{\Delta Q}{\Delta t} = \frac{n \cdot e}{\Delta t} \]
  No SI: \( 1\text{ Ampère (A)} = 1\text{ Coulomb por segundo (C/s)} \).
- **Sentido Real vs. Sentido Convencional:**
  - *Sentido Real:* Os elétrons livres (cargas negativas) movem-se do potencial menor (-) para o potencial maior (+).
  - *Sentido Convencional:* Adotado historicamente antes da descoberta do elétron, define o sentido da corrente como o fluxo hipotético de cargas positivas, fluindo **do maior potencial (+) para o menor potencial (-)**. Todos os circuitos na física clássica utilizam o sentido convencional.

### 3. Tensão Elétrica ou Diferença de Potencial (ddp - \( U \))
Para que haja corrente, é indispensável que uma força eletrostática realize trabalho sobre as cargas. A **Tensão Elétrica (\( U \))** é o trabalho exercido por unidade de carga:
\[ U = \frac{W_{\text{elétrico}}}{Q} \quad (\text{Volts} = \text{Joules/Coulomb}) \]
Uma tomada de \( 127\text{ V} \) significa que a rede elétrica fornece \( 127\text{ Joules} \) de energia para cada \( 1\text{ Coulomb} \) de carga que flui através do aparelho conectado.

### 4. Potência Elétrica (\( P \))
É a taxa com que a energia elétrica é convertida em outra forma de energia (térmica, mecânica, luminosa) por segundo:
\[ P = \frac{\Delta E}{\Delta t} = \frac{Q \cdot U}{\Delta t} = U \cdot i \]
No SI: \( 1\text{ Watt (W)} = 1\text{ Volt} \times 1\text{ Ampère} = 1\text{ Joule/segundo} \).

### 5. Consumo de Energia Elétrica e a Unidade Quilowatt-hora (\( \text{kWh} \))
As distribuidoras de energia medem o consumo residencial na unidade prática de trabalho e energia: o **quilowatt-hora (\( \text{kWh} \))**.
\[ E = P \cdot \Delta t \]
- Se a potência \( P \) for dada em **Quilowatts (\( \text{kW} = 1000\text{ W} \))** e o tempo \( \Delta t \) em **Horas (\( \text{h} \))**, o produto resulta diretamente em \( \text{kWh} \).
- **Conversão para Joules:**
  \[ 1\text{ kWh} = 1000\text{ W} \times 3600\text{ s} = 3{,}6 \times 10^6\text{ J} = 3{,}6\text{ MJ} \]
- **Custo Financeiro na Fatura:**
  \[ \text{Custo (R\$)} = E_{\text{consumida}}(\text{kWh}) \times \text{Tarifa}(\text{R\$/kWh}) \]

## 5. Definições e Conceitos
- **Corrente Contínua (CC ou DC):** Os portadores de carga fluem em um único sentido constante no tempo (baterias, pilhas, portas USB e painéis fotovoltaicos).
- **Corrente Alternada (CA ou AC):** O sentido do fluxo inverte-se periodicamente no tempo (rede elétrica das tomadas, com frequência de 60 Hz no Brasil, alternando o sentido 120 vezes por segundo).
- **Selo Procel de Eficiência:** Etiqueta obrigatória do Inmetro que classifica refrigeradores, condicionadores de ar e máquinas de lavar de A (mais eficiente) a G (menos eficiente).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Cálculo de Corrente em Seção Reta de Fio
Pela seção transversal de um condutor metálico passa uma carga de \( 360\text{ C} \) durante um intervalo de tempo de \( 2\text{ minutos} \). Determine a intensidade de corrente elétrica e quantos elétrons atravessaram a seção.
- **Resolução:**
  1. Conversão do tempo para segundos: \( \Delta t = 2 \times 60 = 120\text{ s} \).
  2. Intensidade de corrente:
     \[ i = \frac{\Delta Q}{\Delta t} = \frac{360}{120} = 3{,}0\text{ A} \]
  3. Número de elétrons:
     \[ Q = n \cdot e \implies 360 = n \cdot (1{,}6 \times 10^{-19}) \]
     \[ n = \frac{360}{1{,}6 \times 10^{-19}} = 225 \times 10^{19} = 2{,}25 \times 10^{21}\text{ elétrons} \]
  - Resposta: A corrente é de \( 3{,}0\text{ A} \), correspondendo à passagem de \( 2{,}25 \times 10^{21}\text{ elétrons} \).

### Exemplo 2: O Chuveiro Elétrico e a Conta de Luz
Um chuveiro elétrico de potência \( 5500\text{ W} \) opera na posição "inverno" e é utilizado diariamente por quatro pessoas em uma residência, onde cada pessoa toma um banho de \( 15\text{ minutos} \). Sabendo que a tarifa da concessionária de energia é de \( \text{R\$\ } 0{,}80 \) por \( \text{kWh} \), qual é o custo mensal (30 dias) desse chuveiro na conta de luz?
- **Resolução:**
  1. Potência em kW: \( P = \frac{5500}{1000} = 5{,}5\text{ kW} \).
  2. Tempo diário total: \( 4 \text{ banhos} \times 15\text{ min} = 60\text{ min} = 1\text{ hora/dia} \).
  3. Tempo total no mês: \( \Delta t = 1\text{ h/dia} \times 30\text{ dias} = 30\text{ horas} \).
  4. Energia elétrica mensal consumida:
     \[ E = P \cdot \Delta t = 5{,}5\text{ kW} \times 30\text{ h} = 165\text{ kWh} \]
  5. Custo financeiro:
     \[ \text{Custo} = 165\text{ kWh} \times \text{R\$\ } 0{,}80 = \text{R\$\ } 132{,}00 \]
  - Resposta: O custo exclusivo do chuveiro será de \( \text{R\$\ } 132{,}00 \) por mês.

## 7. Aplicações Práticas e Modelagem
- **Carregadores de Celular "Fast Charge":** Para carregar a bateria rapidamente mantendo fios finos e seguros, utilizam tensões elevadas de 9V, 12V ou 20V (padrão USB-PD) em vez dos tradicionais 5V, elevando a potência para 30W a 120W sem exigir correntes perigosamente altas que fundiriam os cabos.
- **Transmissão de Energia em Alta Tensão:** As linhas de transmissão transportam energia de Itaipu ou Belo Monte a centenas de milhares de Volts (\( 500\text{ kV} \)) para reduzir a corrente elétrica (\( i = P/U \)) e minimizar as perdas por efeito Joule na extensão dos cabos.

## 8. Exercícios de Fixação
1. Um ferro elétrico de passar de \( 1200\text{ W} \) é ligado a uma tomada de \( 120\text{ V} \). Qual é a intensidade de corrente elétrica que percorre o aparelho?
2. Uma lâmpada LED de \( 10\text{ W} \) permanece ligada ininterruptamente durante as 24 horas do dia ao longo de um mês de 30 dias. Calcule o consumo total de energia dessa lâmpada expresso em \( \text{kWh} \).
3. Se um resistor for percorrido por uma corrente de \( 2\text{ A} \) durante \( 5\text{ minutos} \), qual a quantidade total de carga que atravessa o condutor?

## 9. Correção Comentada dos Exercícios
1. \( P = U \cdot i \implies 1200 = 120 \cdot i \implies i = \frac{1200}{120} = 10\text{ A} \).
2. Tempo total: \( \Delta t = 24 \times 30 = 720\text{ horas} \). Potência: \( P = \frac{10}{1000} = 0{,}01\text{ kW} \).
   Consumo: \( E = 0{,}01\text{ kW} \times 720\text{ h} = 7{,}2\text{ kWh} \).
3. Tempo em segundos: \( \Delta t = 5 \times 60 = 300\text{ s} \).
   Carga: \( \Delta Q = i \cdot \Delta t = 2\text{ A} \times 300\text{ s} = 600\text{ Coulombs} \).

## 10. Desafio de Domínio
Por que ligar simultaneamente um micro-ondas, uma torradeira e uma fritadeira elétrica tipo air fryer na mesma tomada através de um adaptador comum (benjamim/"tê") costuma provocar aquecimento excessivo dos fios e o desligamento do disjuntor da residência? Explique em termos de corrente total e conservação da potência.
*Pista:* Como a tensão da tomada é fixa em 127V, o que acontece com a corrente total consumida ao somar as potências de múltiplos aparelhos em paralelo?

## 11. Resumo e Pontos-Chave
- Carga quantizada: \( Q = n \cdot e \) (\( e = 1{,}6 \times 10^{-19}\text{ C} \)).
- Corrente elétrica: \( i = \frac{\Delta Q}{\Delta t} \) em Ampères (\( \text{A} = \text{C/s} \)).
- Sentido convencional: do maior para o menor potencial (+ para -).
- Tensão elétrica: \( U = \frac{W}{Q} \) em Volts (\( \text{V} = \text{J/C} \)).
- Potência elétrica: \( P = U \cdot i \) em Watts (\( \text{W} \)).
- Consumo: \( E = P(\text{kW}) \cdot \Delta t(\text{h}) \) em \( \text{kWh} \). Conversão: \( 1\text{ kWh} = 3{,}6 \times 10^6\text{ J} \).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Eletromagnetismo*. Vol. 3. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física III: Eletromagnetismo*. 14. ed. Pearson, 2016.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Intensidade de Corrente Elétrica Média",
                syntax = """i = \\frac{\\Delta Q}{\\Delta t} = \\frac{n \\cdot e}{\\Delta t}""",
                description = "Taxa de transporte de carga elétrica que flui através de uma seção transversal de condutor por segundo.",
                parametersInfo = """i: \\text{corrente (A)}; \\quad \\Delta Q: \\text{carga (C)}; \\quad n: \\text{número de elétrons}; \\quad e = 1{,}6 \\times 10^{-19}\\text{ C}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Potência Elétrica Fundamental",
                syntax = """P = U \\cdot i""",
                description = "Relação universal conectando a taxa de conversão energética com a ddp e a corrente circulante.",
                parametersInfo = """P: \\text{potência em Watts (W)}; \\quad U: \\text{tensão em Volts (V)}; \\quad i: \\text{corrente em Ampères (A)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Energia Elétrica em Quilowatt-hora",
                syntax = """E_{\\text{kWh}} = \\frac{P_{\\text{Watts}}}{1000} \\cdot \\Delta t_{\\text{horas}}""",
                description = "Cálculo direto da energia faturada em concessionárias de abastecimento residencial.",
                parametersInfo = """E: \\text{energia em kWh}; \\quad 1\\text{ kWh} = 3{,}6 \\times 10^6\\text{ Joules}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Consumo Mensal de Geladeira Frost-Free",
                codeOrFormula = """E = P \\cdot \\Delta t \\implies E = 0{,}2\\text{ kW} \\cdot (10\\text{ h/dia} \\cdot 30\\text{ dias}) = 60\\text{ kWh}""",
                explanation = "Considerando o fator de ciclo do compressor termostático que opera cerca de 10 horas ativas por dia."
            ),
            LessonExample(
                title = "Corrente Nominal de Chuveiro em 127V vs 220V",
                codeOrFormula = """i_{127} = \\frac{5500}{127} \\approx 43{,}3\\text{ A} \\quad \\text{vs} \\quad i_{220} = \\frac{5500}{220} = 25\\text{ A}""",
                explanation = "Instalações em 220V exigem menor corrente para a mesma potência, permitindo condutores elétricos de fiação mais fina e menor aquecimento nos cabos."
            )
        ),
        goldTip = "Em questões de consumo de energia e conta de luz no ENEM, divida a potência em Watts por 1000 para achar kW ANTES de multiplicar pelas horas! Lembre-se: E (kWh) = [P (W) / 1000] * tempo (horas). Se multiplicar Watts diretamente por horas, seu valor sairá mil vezes maior do que o correto!"
    )

    // =========================================================================
    // AULA fis_m2_a2: 1ª e 2ª Leis de Ohm e Resistores
    // =========================================================================
    private val bundleFisM2A2 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Quando uma corrente de elétrons flui pelo interior de um condutor, as cargas colidem incessantemente contra os núcleos atômicos fixos da rede cristalina. Essa oposição microscópica ao fluxo ordenado de carga é a **Resistência Elétrica**. Investigada experimentalmente pelo físico alemão Georg Simon Ohm no século XIX, a resistência é a base de funcionamento de todos os resistores — componentes fundamentais da eletrônica que convertem energia elétrica em calor pelo **Efeito Joule**. No ENEM, o domínio das Leis de Ohm e das associações de resistores em série e em paralelo é a ferramenta indispensável para resolver circuitos elétricos cotidianos.

## 2. Objetivos de Aprendizagem
- Definir Resistência Elétrica (\( R \)) e sua unidade no SI: Ohm (\( \Omega = \text{V/A} \)).
- Compreender e aplicar a **Primeira Lei de Ohm**: \( U = R \cdot i \), identificando condutores ôhmicos através do gráfico linear \( U \times i \).
- Analisar o **Efeito Joule** e deduzir as fórmulas derivadas da potência dissipada: \( P = R \cdot i^2 \) e \( P = \frac{U^2}{R} \).
- Compreender e aplicar a **Segunda Lei de Ohm**: \( R = \rho \cdot \frac{L}{A} \), analisando a influência do comprimento, área de seção reta, resistividade do material e temperatura.
- Resolver e associar resistores em **Série**: \( R_{\text{eq}} = \sum R_i \), corrente comum e divisão de tensão.
- Resolver e associar resistores em **Paralelo**: \( \frac{1}{R_{\text{eq}}} = \sum \frac{1}{R_i} \), tensão comum e divisão de corrente.
- Resolver associações mistas de resistores em circuitos complexos.

## 3. Pré-requisitos
- Corrente, Tensão e Potência Elétrica (Física, Aula fis_m2_a1).
- Operações com frações e equações lineares (Matemática Básica, Aulas mb_l03 e mb_l07).

## 4. Explicação Teórica Aprofundada

### 1. Primeira Lei de Ohm e Condutores Ôhmicos
A resistência elétrica de um dispositivo é a razão constante entre a ddp aplicada e a corrente resultante:
\[ R = \frac{U}{i} \implies U = R \cdot i \]
- **Condutor Ôhmico:** Mantém sua resistência rigorosamente constante sob temperatura constante, independentemente da tensão aplicada. Seu gráfico característico \( U \times i \) é uma reta que passa pela origem; a inclinação da reta mede o valor de \( R \) (\( R = \tan\theta \)).
- **Condutor Não-Ôhmico:** A resistência varia com a tensão ou corrente (ex.: lâmpada incandescente comum cujo filamento de tungstênio se aquece dramaticamente, diodos semicondutores e termistores).

### 2. O Efeito Joule e Potência Dissipada
A contínua colisão inelástica dos elétrons acelerados contra a rede cristalina do metal transfere energia cinética microscópica para a rede, manifestando-se macroscopicamente como **calor**: esse fenômeno é o **Efeito Joule**.
Combinando \( P = U \cdot i \) com a 1ª Lei de Ohm (\( U = R \cdot i \)):
1. Substituindo \( U \):
   \[ P = (R \cdot i) \cdot i = R \cdot i^2 \]
   (Fórmula excelente para análise de componentes em **Série**, onde a corrente \( i \) é constante).
2. Substituindo \( i = U/R \):
   \[ P = U \cdot \left(\frac{U}{R}\right) = \frac{U^2}{R} \]
   (Fórmula excelente para aparelhos ligados à rede das tomadas em **Paralelo**, onde a tensão \( U \) é constante).
- **Aplicações do Efeito Joule:** Chuveiros elétricos, secadores de cabelo, ferros de passar, aquecedores, torradores de pão e fusíveis de proteção térmica.

### 3. Segunda Lei de Ohm (Fatores Geométricos e Resistividade)
A resistência elétrica de um fio condutor cilíndrico homogêneo depende estritamente de suas dimensões geométricas e do material que o constitui:
\[ R = \rho \cdot \frac{L}{A} \]
- \( \rho \) (**Resistividade Elétrica**): Propriedade intrínseca do material (\( \Omega \cdot \text{m} \)). O cobre e a prata possuem baixíssima resistividade (ótimos condutores); o níquel-cromo (nicromo) possui resistividade alta (ótimo para filamentos aquecedores).
- \( L \) (**Comprimento do Fio**): Quanto mais longo o condutor, mais barreiras atômicas os elétrons encontram: \( R \propto L \). Se você dobrar o comprimento do fio, sua resistência dobra!
- \( A \) (**Área de Seção Transversal / Bitola**): Quanto mais grosso o fio (maior área), mais espaço há para os elétrons fluírem sem colisão: \( R \propto \frac{1}{A} \). Fios grossos têm menor resistência; fios finos têm alta resistência.
- **Cálculo da Chave Verão/Inverno do Chuveiro no ENEM:**
  A tomada mantém a tensão constante (\( U \approx 220\text{ V} \)). Para que o chuveiro esquente mais na posição **Inverno** (maior potência \( P = \frac{U^2}{R} \)), a resistência \( R \) deve ser **MENOR**. Pela 2ª Lei (\( R = \rho \frac{L}{A} \)), para diminuir a resistência o contato interno seleciona um pedaço **MAIS CURTO** do fio condutor!

### 4. Associação de Resistores em Série
Os resistores estão conectados um após o outro ao longo de um mesmo ramo de fio (sem ramificações):
1. **A corrente é rigorosamente a mesma** através de todos os resistores:
   \[ i_{\text{total}} = i_1 = i_2 = \dots = i_n \]
2. **A tensão total divide-se** entre eles:
   \[ U_{\text{total}} = U_1 + U_2 + \dots + U_n \]
3. **Resistência Equivalente:**
   \[ R_{\text{eq}} = R_1 + R_2 + \dots + R_n \]
- A resistência equivalente é sempre **maior que qualquer uma das resistências individuais**. Se um resistor da série queimar ou for desconectado, **o circuito se abre e todos os outros apagam imediatamente** (como os pisca-piscas de Natal antigos).

### 5. Associação de Resistores em Paralelo
Os terminais de todos os resistores estão ligados aos mesmos dois pontos em comum (nós do circuito):
1. **A tensão é rigorosamente a mesma** sobre todos os resistores:
   \[ U_{\text{total}} = U_1 = U_2 = \dots = U_n \]
2. **A corrente total divide-se** pelos ramos:
   \[ i_{\text{total}} = i_1 + i_2 + \dots + i_n \]
3. **Resistência Equivalente:**
   \[ \frac{1}{R_{\text{eq}}} = \frac{1}{R_1} + \frac{1}{R_2} + \dots + \frac{1}{R_n} \]
- **Caso Particular de 2 Resistores em Paralelo (Produto pela Soma):**
  \[ R_{\text{eq}} = \frac{R_1 \cdot R_2}{R_1 + R_2} \]
- **Caso Particular de \( n \) Resistores Idênticos de valor \( R \) em Paralelo:**
  \[ R_{\text{eq}} = \frac{R}{n} \]
- A resistência equivalente em paralelo é sempre **menor que a menor das resistências associadas**. As instalações elétricas prediais e residenciais são montadas inteiramente em paralelo para que todos os eletrodomésticos recebam a mesma tensão (\( 127\text{ V} \) ou \( 220\text{ V} \)) e possam ser ligados e desligados de forma independente sem interferir uns nos outros!

## 5. Definições e Conceitos
- **Supercondutividade:** Fenômeno quântico em que certos materiais perdem 100% de sua resistência elétrica (\( R = 0 \)) abaixo de uma temperatura crítica ultrabaixa.
- **Potenciômetro / Reostato:** Resistor de resistência variável ajustável mecanicamente através de um cursor deslizante (ex.: controle de volume rotativo e pedais de aceleração).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Segunda Lei de Ohm com Deformação Geométrica
Um fio cilíndrico de cobre de comprimento \( L \) e área de seção reta \( A \) tem resistência elétrica de \( 4\text{ }\Omega \). Esse fio é esticado mecanicamente por um trefilador de modo que seu comprimento dobre para \( 2L \), mantendo a densidade e o volume do metal constantes. Qual será a nova resistência elétrica do fio?
- **Resolução:**
  1. O volume de um cilindro é \( V = A \cdot L = \text{constante} \).
  2. Se o comprimento dobra (\( L' = 2L \)), a área de seção reta cai necessariamente pela metade (\( A' = \frac{A}{2} \)).
  3. Aplicando a 2ª Lei de Ohm à nova configuração:
     \[ R' = \rho \cdot \frac{L'}{A'} = \rho \cdot \frac{2L}{A/2} = \rho \cdot \frac{4L}{A} = 4 \cdot \left(\rho \frac{L}{A}\right) = 4 \cdot R \]
  4. Substituindo a resistência original (\( R = 4\text{ }\Omega \)):
     \[ R' = 4 \cdot 4\text{ }\Omega = 16\text{ }\Omega \]
  - Resposta: A resistência aumentou quatro vezes, passando para \( 16\text{ }\Omega \).

### Exemplo 2: Associação Mista de Resistores
Três resistores de valores \( R_1 = 6\text{ }\Omega \), \( R_2 = 3\text{ }\Omega \) e \( R_3 = 8\text{ }\Omega \) estão montados de modo que \( R_1 \) e \( R_2 \) estão em paralelo entre si, e essa combinação está conectada em série com \( R_3 \). O circuito é alimentado por uma ddp total de \( 20\text{ V} \). Determine a resistência equivalente do circuito e a corrente total fornecida pela fonte.
- **Resolução:**
  1. Resistência equivalente da associação em paralelo (\( R_{12} \)):
     \[ R_{12} = \frac{R_1 \cdot R_2}{R_1 + R_2} = \frac{6 \cdot 3}{6 + 3} = \frac{18}{9} = 2\text{ }\Omega \]
  2. Resistência equivalente total somando em série com \( R_3 \):
     \[ R_{\text{eq}} = R_{12} + R_3 = 2 + 8 = 10\text{ }\Omega \]
  3. Corrente total pela 1ª Lei de Ohm:
     \[ i_{\text{total}} = \frac{U_{\text{total}}}{R_{\text{eq}}} = \frac{20\text{ V}}{10\text{ }\Omega} = 2\text{ A} \]
  - Resposta: A resistência equivalente é de \( 10\text{ }\Omega \) e a corrente total é de \( 2\text{ A} \).

## 7. Aplicações Práticas e Modelagem
- **Desembaçador de Vidro Traseiro de Automóveis:** Filetes metálicos finos impressos sobre o vidro atuam como resistores; a passagem de corrente elétrica dissipa calor por efeito Joule, evaporando a condensação e restaurando a visibilidade traseira.
- **Fusíveis de Cartucho:** Fios de chumbo/estanho com baixo ponto de fusão calculados para derreter por efeito Joule caso a corrente ultrapasse o limite seguro, interrompendo o circuito e protegendo eletroeletrônicos caros.

## 8. Exercícios de Fixação
1. Um condutor ôhmico é submetido a uma ddp de \( 60\text{ V} \) e percorrido por uma corrente de \( 5\text{ A} \). Se a tensão for reduzida para \( 24\text{ V} \), qual será a nova corrente circulante?
2. Para aquecer a água do banho com maior intensidade em um chuveiro elétrico residencial mantido em \( 220\text{ V} \), deve-se aumentar ou diminuir o comprimento útil do resistor interno? Justifique com fórmulas.
3. Dois resistores idênticos de \( 20\text{ }\Omega \) cada são associados em paralelo. Qual é a resistência equivalente dessa combinação?

## 9. Correção Comentada dos Exercícios
1. Por ser ôhmico, a resistência é constante: \( R = \frac{U_1}{i_1} = \frac{60}{5} = 12\text{ }\Omega \).
   Com a nova tensão: \( i_2 = \frac{U_2}{R} = \frac{24}{12} = 2\text{ A} \).
2. Deve-se **DIMINUIR o comprimento do resistor**. Pela fórmula \( P = \frac{U^2}{R} \), como a tensão \( U \) é constante na tomada, para aumentar a potência dissipada \( P \) (aquecer mais a água) a resistência \( R \) deve ser menor. Pela 2ª Lei \( R = \rho \frac{L}{A} \), menor resistência exige menor comprimento \( L \).
3. Para dois resistores iguais em paralelo: \( R_{\text{eq}} = \frac{R}{n} = \frac{20}{2} = 10\text{ }\Omega \).

## 10. Desafio de Domínio
Considere um cubo metálico com doze resistores idênticos de resistência \( R \) instalados em cada uma de suas doze arestas. Se conectarmos uma fonte de tensão entre dois vértices opostos através da diagonal principal do cubo, qual será a resistência equivalente do circuito completo?
*Pista:* Utilize argumentos de simetria para determinar a divisão de corrente nos três ramos que saem do primeiro vértice (\( i/3 \)) e nos seis ramos centrais (\( i/6 \)).

## 11. Resumo e Pontos-Chave
- 1ª Lei de Ohm: \( U = R \cdot i \) (reta no gráfico \( U \times i \) para ôhmicos).
- Efeito Joule: conversão de energia elétrica em calor. Potência: \( P = U \cdot i = R \cdot i^2 = \frac{U^2}{R} \).
- 2ª Lei de Ohm: \( R = \rho \cdot \frac{L}{A} \) (fios mais curtos e grossos têm menor resistência).
- Associação em Série: corrente constante (\( i_1 = i_2 \)), divide tensão, \( R_{\text{eq}} = \sum R_i \).
- Associação em Paralelo: tensão constante (\( U_1 = U_2 \)), divide corrente, \( \frac{1}{R_{\text{eq}}} = \sum \frac{1}{R_i} \). Produto pela soma: \( R_{\text{eq}} = \frac{R_1 R_2}{R_1 + R_2} \).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Eletromagnetismo*. Vol. 3. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física III: Eletromagnetismo*. 14. ed. Pearson, 2016.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Primeira Lei de Ohm",
                syntax = """U = R \\cdot i""",
                description = "Proporcionalidade linear direta entre a tensão aplicada e a intensidade de corrente elétrica em condutores ôhmicos.",
                parametersInfo = """U: \\text{diferença de potencial (V)}; \\quad R: \\text{resistência (}\\Omega\\text{)}; \\quad i: \\text{corrente (A)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Segunda Lei de Ohm",
                syntax = """R = \\rho \\cdot \\frac{L}{A}""",
                description = "Dependência geométrica e material da resistência elétrica de condutores cilíndricos.",
                parametersInfo = """\\rho: \\text{resistividade (}\\Omega\\cdot\\text{m)}; \\quad L: \\text{comprimento (m)}; \\quad A: \\text{área da seção reta (m}^2)"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Associação em Paralelo (Produto pela Soma)",
                syntax = """R_{\\text{eq}} = \\frac{R_1 \\cdot R_2}{R_1 + R_2}""",
                description = "Expressão simplificada da resistência equivalente para dois resistores ôhmicos associados em paralelo.",
                parametersInfo = """R_1, R_2: \\text{resistências individuais em Ohms}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "O Paradoxo da Chave do Chuveiro",
                codeOrFormula = """\\text{Chave Inverno} \\implies \\text{Maior Potência } P = \\frac{U^2}{R} \\implies \\text{Menor Resistência } R \\implies \\text{Fio Mais Curto}""",
                explanation = "Na posição inverno, o chuveiro seleciona um trecho menor de resistor para diminuir R e aumentar a potência térmica dissipada sob tensão constante."
            ),
            LessonExample(
                title = "Três Lâmpadas em Paralelo na Residência",
                codeOrFormula = """U_{\\text{lâmpada}} = 127\\text{ V (constante)}; \\quad i_{\\text{total}} = i_1 + i_2 + i_3""",
                explanation = "A conexão em paralelo garante que cada lâmpada receba a tensão total da rede e mantenha seu brilho inalterado caso outra queime."
            )
        ),
        goldTip = "O ENEM adora a pegadinha da chave do chuveiro! Grave isto na mente: na posição INVERNO, queremos MAIOR potência para esquentar mais a água. Como a tomada tem tensão U fixa, pela fórmula P = U² / R, para a potência ser MAIOR, a resistência tem que ser MENOR! Logo, no modo inverno o chuveiro usa a parte mais CURTA do resistor!"
    )

    // =========================================================================
    // AULA fis_m2_a3: Circuitos Elétricos, Curtos-Circuitos e Dimensionamento
    // =========================================================================
    private val bundleFisM2A3 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Um circuito elétrico fechado é um ecossistema complexo onde fontes geradoras de energia, condutores, receptores, dispositivos de controle e instrumentos de medição interagem em perfeito equilíbrio eletrodinâmico. Quando ocorre uma falha isolante e dois fios de potenciais distintos entram em contato direto de resistência nula, surge o temido **Curto-Circuito**, causador de faíscas, derretimento de cabos e grandes incêndios estruturais. Nesta aula, dissecamos os mecanismos de proteção predial (fusíveis e disjuntores), a modelagem de geradores reais, as regras de conexão de amperímetros e voltímetros e a aplicação das **Leis de Kirchhoff**.

## 2. Objetivos de Aprendizagem
- Compreender a definição e as consequências físicas de um **Curto-Circuito** (\( R \to 0 \implies i \to \infty \)).
- Analisar o funcionamento e dimensionamento de dispositivos de segurança: **Fusíveis** e **Disjuntores Termomagnéticos**.
- Modelar um **Gerador Real** através de sua Força Eletromotriz (\( \mathcal{E} \)) e Resistência Interna (\( r \)): Equação do Gerador \( U = \mathcal{E} - r \cdot i \).
- Interpretar a curva característica do gerador real, determinando a corrente de curto-circuito (\( i_{\text{cc}} = \mathcal{E}/r \)) e o rendimento (\( \eta = U/\mathcal{E} \)).
- Compreender e conectar corretamente instrumentos de medição ideais:
  - **Amperímetro:** ligado **em série**, resistência interna nula (\( R_A = 0 \)).
  - **Voltímetro:** ligado **em paralelo**, resistência interna infinita (\( R_V \to \infty \)).
- Aplicar a **Lei dos Nós** (conservação da carga) e a **Lei das Malhas** (conservação da energia) de Kirchhoff.

## 3. Pré-requisitos
- Potência Elétrica, Consumo e Efeito Joule (Física, Aula fis_m2_a1).
- 1ª e 2ª Leis de Ohm e Associação de Resistores (Física, Aula fis_m2_a2).

## 4. Explicação Teórica Aprofundada

### 1. A Física do Curto-Circuito
Ocorre quando dois nós com grande diferença de potencial são conectados por uma via condutora de resistência praticamente nula (\( R_{\text{via}} \approx 0 \)):
- Pela 1ª Lei de Ohm (\( i = \frac{U}{R} \)), quando \( R \to 0 \), a corrente elétrica se eleva a **valores astronômicos**, limitada apenas pela resistência interna dos cabos e da fonte.
- Pelo Efeito Joule (\( P = R \cdot i^2 \)), correntes altíssimas liberam calor em frações de segundo, fundindo o cobre da tubulação, carbonizando isolamentos plásticos e deflagrando incêndios elétricos.
- Qualquer resistor conectado em paralelo com um trecho em curto-circuito é **ignorado pela corrente** (a corrente escolhe integralmente o caminho de resistência nula) e fica desativado/apagado.

### 2. Dispositivos de Proteção: Fusíveis e Disjuntores
1. **Fusível:** Contém um pequeno filamento metálico calibrado com ponto de fusão preciso. Quando a corrente ultrapassa o valor nominal (ex.: 10A), o calor gerado por efeito Joule funde o filamento, interrompendo o circuito. É descartável (precisa ser substituído após queimar).
2. **Disjuntor Termomagnético:** Dispositivo reutilizável eletromecânico que protege contra dois eventos distintos:
   - *Sobrecarga contínua:* Uma lâmina bimetálica aquece e se encurva lentamente desarmando o contato mecânico antes que os fios da parede derretam.
   - *Curto-Circuito violento:* Um eletroímã interno aciona instantaneamente uma trava mecânica ao detectar pulsos de corrente colossais, desarmando o circuito em milissegundos.
   - **Regra de Ouro do Dimensionamento:** O disjuntor deve ser escolhido para proteger a fiação! Se os cabos de um circuito suportam com segurança até \( 20\text{ A} \), o disjuntor deve ser de no máximo \( 20\text{ A} \). Trocar um disjuntor que vive caindo por um de \( 40\text{ A} \) sem engrossar a fiação é a causa número um de incêndios residenciais!

### 3. Geradores Elétricos Reais
Um gerador não "cria" cargas; ele converte outras formas de energia (química nas pilhas/baterias, mecânica nos alternadores) em energia elétrica, fornecendo trabalho às cargas elétricas.
- **Força Eletromotriz (\( \mathcal{E} \)):** É a energia total convertida por unidade de carga gerada no interior da fonte (medida em Volts). É a tensão nos terminais quando o gerador está em circuito aberto (sem fornecer corrente).
- **Resistência Interna (\( r \)):** Oposição intrínseca ao fluxo de elétrons oferecida pelos eletrólitos ou bobinas do próprio gerador, dissipando parte da energia interna como calor.
- **Equação do Gerador:** A tensão útil \( U \) fornecida ao circuito externo é a fem total menos a queda interna:
  \[ U = \mathcal{E} - r \cdot i \]
- **Corrente de Curto-Circuito (\( i_{\text{cc}} \)):** Obtida ligando os terminais positivo e negativo do gerador por um fio ideal (\( U = 0 \)):
  \[ 0 = \mathcal{E} - r \cdot i_{\text{cc}} \implies i_{\text{cc}} = \frac{\mathcal{E}}{r} \]
- **Rendimento do Gerador (\( \eta \)):**
  \[ \eta = \frac{P_{\text{útil}}}{P_{\text{total}}} = \frac{U \cdot i}{\mathcal{E} \cdot i} = \frac{U}{\mathcal{E}} \]

### 4. Instrumentos de Medição Elétrica Ideais
1. **Amperímetro:**
   - Mede a intensidade de corrente elétrica que flui através de um ramo.
   - Deve ser conectado **estritamente EM SÉRIE** com o componente a ser medido.
   - Para não alterar a corrente do circuito original, sua resistência interna deve ser **idealmente NULA (\( R_A = 0 \))**.
   - *Atenção:* Se você conectar um amperímetro em paralelo com uma tomada ou lâmpada, você criará um **curto-circuito destrutivo**, explodindo o fusível do aparelho!
2. **Voltímetro:**
   - Mede a diferença de potencial elétrico entre dois pontos.
   - Deve ser conectado **estritamente EM PARALELO** com o componente analisado.
   - Para não desviar corrente do circuito original, sua resistência interna deve ser **idealmente INFINITA (\( R_V \to \infty \))**.
   - Se for conectado em série por engano, sua gigantesca resistência bloqueará praticamente toda a corrente do ramo, fazendo o circuito parar de funcionar.

### 5. Leis de Kirchhoff
1. **Primeira Lei (Lei dos Nós / Conservação da Carga):** A soma das correntes que chegam a um nó é rigorosamente igual à soma das correntes que saem dele:
   \[ \sum i_{\text{chegam}} = \sum i_{\text{saem}} \]
2. **Segunda Lei (Lei das Malhas / Conservação da Energia):** Ao percorrer uma malha fechada em um sentido escolhido, a soma algébrica das diferenças de potencial é nula:
   \[ \sum \Delta V = 0 \]

## 5. Definições e Conceitos
- **Nó:** Ponto de confluência do circuito onde três ou mais condutores elétricos se encontram.
- **Ramo:** Trecho de circuito compreendido entre dois nós consecutivos percorrido pela mesma corrente.
- **Malha:** Qualquer circuito fechado simples formado por ramos que começa e termina no mesmo nó.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Equação do Gerador e Rendimento
Uma bateria de automóvel possui força eletromotriz \( \mathcal{E} = 12\text{ V} \) e resistência interna \( r = 0{,}1\text{ }\Omega \). Ao ligar o motor de arranque, a bateria passa a fornecer uma corrente de \( 40\text{ A} \). Determine a tensão disponível nos terminais da bateria durante a partida e o seu rendimento elétrico.
- **Resolução:**
  1. Aplicação da Equação do Gerador:
     \[ U = \mathcal{E} - r \cdot i = 12 - (0{,}1 \cdot 40) = 12 - 4 = 8\text{ V} \]
  2. Rendimento elétrico:
     \[ \eta = \frac{U}{\mathcal{E}} = \frac{8}{12} = \frac{2}{3} \approx 66{,}7\% \]
  - Resposta: A tensão disponível cai para \( 8\text{ V} \) e o rendimento da bateria é de \( 66{,}7\% \).

### Exemplo 2: Conexão de Instrumentos e Identificação de Erros
Um aluno monta um circuito com uma bateria de \( 9\text{ V} \) e uma lâmpada. Desejando medir a corrente e a tensão na lâmpada, conecta o aparelho A em série com a lâmpada e o aparelho B em paralelo com ela. Identifique os instrumentos e preveja o que ocorreria se a ligação de A e B fosse invertida.
- **Resolução:**
  - O aparelho A é um **Amperímetro** (ligado em série, \( R_A = 0 \)) e o aparelho B é um **Voltímetro** (ligado em paralelo, \( R_B \to \infty \)).
  - Se invertidos: o voltímetro em série interromperia o fluxo de corrente devido à sua altíssima resistência interna, apagando a lâmpada; e o amperímetro em paralelo colocaria a lâmpada em curto-circuito, podendo queimar o instrumento ou a fonte.

## 7. Aplicações Práticas e Modelagem
- **Instalação do Fio Terra e Dispositivo DR (Diferencial Residual):** O DR detecta fugas microscópicas de corrente (\( \ge 30\text{ mA} \)) decorrentes de choques elétricos em pessoas ou contato de fases energizadas com carcaças metálicas de geladeiras, desarmando o circuito em 30 milissegundos para salvar vidas.
- **Baterias de Lítio em Celulares (Sistema BMS):** Microchips de proteção monitoram continuamente a temperatura, corrente e tensão das células de íon-lítio para desligá-las instantaneamente em caso de curto-circuito interno, evitando explosões térmicas.

## 8. Exercícios de Fixação
1. Uma pilha alcalina de fem \( \mathcal{E} = 1{,}5\text{ V} \) tem resistência interna \( r = 0{,}5\text{ }\Omega \). Qual é a sua corrente máxima de curto-circuito?
2. Em um nó de um circuito elétrico convergem quatro condutores: chegam correntes de \( 4\text{ A} \) e \( 7\text{ A} \), e saem uma corrente de \( 5\text{ A} \) e uma corrente \( i_4 \). Calcule o valor da corrente \( i_4 \).
3. Qual deve ser a resistência interna de um amperímetro ideal e de um voltímetro ideal?

## 9. Correção Comentada dos Exercícios
1. \( i_{\text{cc}} = \frac{\mathcal{E}}{r} = \frac{1{,}5}{0{,}5} = 3{,}0\text{ A} \).
2. Pela Lei dos Nós: \( \sum i_{\text{chegam}} = \sum i_{\text{saem}} \implies 4 + 7 = 5 + i_4 \implies 11 = 5 + i_4 \implies i_4 = 6\text{ A} \).
3. Amperímetro ideal: resistência interna **nula (\( R_A = 0 \))**. Voltímetro ideal: resistência interna **infinita (\( R_V \to \infty \))**.

## 10. Desafio de Domínio
Demonstre através do cálculo diferencial de potência (\( P(i) = \mathcal{E} \cdot i - r \cdot i^2 \)) por que a máxima transferência de potência de um gerador para um circuito externo ocorre quando a resistência da carga externa for exatamente igual à resistência interna do próprio gerador (\( R_{\text{carga}} = r \)).
*Pista:* Derive \( P \) em relação a \( i \) e iguale a zero para encontrar o ponto crítico de máximo.

## 11. Resumo e Pontos-Chave
- Curto-circuito: resistência nula (\( R \to 0 \)), corrente dispara e circuito paralelo é ignorado.
- Disjuntor protege a fiação contra sobrecarga térmica e curto-circuito.
- Gerador Real: \( U = \mathcal{E} - r \cdot i \). Corrente de curto: \( i_{\text{cc}} = \mathcal{E}/r \). Rendimento: \( \eta = U/\mathcal{E} \).
- Amperímetro: ligado EM SÉRIE, resistência ideal NULA (\( R_A = 0 \)).
- Voltímetro: ligado EM PARALELO, resistência ideal INFINITA (\( R_V \to \infty \)).
- 1ª Lei de Kirchhoff (Nós): soma das correntes que entram = soma das correntes que saem.
- 2ª Lei de Kirchhoff (Malhas): soma das diferenças de potencial em uma malha é zero.

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Eletromagnetismo*. Vol. 3. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física III: Eletromagnetismo*. 14. ed. Pearson, 2016.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação do Gerador Elétrico Real",
                syntax = """U = \\mathcal{E} - r \\cdot i""",
                description = "Tensão útil terminal entregue à carga subtraindo a perda ôhmica na resistência interna.",
                parametersInfo = """U: \\text{tensão nos terminais}; \\quad \\mathcal{E}: \\text{força eletromotriz}; \\quad r: \\text{resistência interna}; \\quad i: \\text{corrente}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Primeira Lei de Kirchhoff (Lei dos Nós)",
                syntax = """\\sum i_{\\text{entrada}} = \\sum i_{\\text{saída}}""",
                description = "Princípio da conservação contínua da carga elétrica em qualquer junção ou nó de circuito.",
                parametersInfo = """i_{\\text{in}}, i_{\\text{out}}: \\text{intensidades das correntes circulantes no nó}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Corrente de Curto-Circuito do Gerador",
                syntax = """i_{\\text{cc}} = \\frac{\\mathcal{E}}{r}""",
                description = "Máxima corrente teórica drenada pelo gerador quando os terminais são conectados em resistência nula.",
                parametersInfo = """\\mathcal{E}: \\text{fem em Volts}; \\quad r: \\text{resistência interna em Ohms}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Posicionamento Correto de Multímetros",
                codeOrFormula = """\\text{Amperímetro } (R=0) \\to \\text{SÉRIE}; \\quad \\text{Voltímetro } (R=\\infty) \\to \\text{PARALELO}""",
                explanation = "A inserção serial do amperímetro mede todo o fluxo de elétrons, enquanto o paralelismo do voltímetro mede a ddp sem desviar corrente."
            ),
            LessonExample(
                title = "Dimensionamento de Disjuntor Residencial",
                codeOrFormula = """i_{\\text{nominal}} = \\frac{P_{\\text{total}}}{U} = \\frac{6600\\text{ W}}{220\\text{ V}} = 30\\text{ A} \\implies \\text{Disjuntor de } 32\\text{ A}""",
                explanation = "O disjuntor deve ser ligeiramente superior à corrente nominal para evitar disparos falsos, mas inferior ao limite térmico do condutor de cobre."
            )
        ),
        goldTip = "Para o ENEM, memorize de olhos fechados: AMPERÍMETRO é ligado EM SÉRIE e tem resistência NULA (R = 0). VOLTÍMETRO é ligado EM PARALELO e tem resistência INFINITA (R = infinito). Se ligar amperímetro em paralelo, dá curto-circuito! Se ligar voltímetro em série, ele bloqueia a corrente do circuito!"
    )

    // =========================================================================
    // AULA fis_m2_a4: Eletrostática - Cargas, Processos de Eletrização e Lei de Coulomb
    // =========================================================================
    private val bundleFisM2A4 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A **Eletrostática** é o ramo da Física dedicado à investigação das propriedades, forças e comportamentos das cargas elétricas em estado de repouso relativo. Conhecida desde a Grécia Antiga, quando Tales de Mileto observou que o âmbar (*elektron*) atritado com pele de animal adquiria a propriedade de atrair pequenos pedaços de palha, a eletrostática é a raiz de todas as interações elétricas da natureza. No ENEM, este módulo é fundamental para compreender os processos de eletrização, a força elétrica intermolecular que sustenta toda a matéria química e fenômenos atmosféricos como descargas elétricas e raios.

## 2. Objetivos de Aprendizagem
- Compreender os dois postulados fundamentais da Eletrostática: **Princípio da Atração e Repulsão** e **Princípio da Conservação das Cargas Elétricas**.
- Dominar os três **Processos de Eletrização**:
  1. **Eletrização por Atrito** (série triboelétrica, corpos adquirem cargas de sinais opostos e módulos iguais).
  2. **Eletrização por Contato** (corpos condutores adquirem cargas de mesmo sinal; conservação e divisão proporcional de cargas).
  3. **Eletrização por Indução Eletrostática** (indutor, induzido neutro, polarização e aterramento; o induzido adquire carga de sinal oposto ao indutor).
- Deduzir e aplicar a **Lei de Coulomb** para a força eletrostática entre duas cargas puntiformes: \( F_e = \frac{k_0 \cdot |q_1 \cdot q_2|}{d^2} \).
- Analisar o equilíbrio de forças combinando a força de Coulomb com a gravidade e tensões mecânicas em pêndulos eletrostáticos.

## 3. Pré-requisitos
- Carga elementar e quantização da carga (\( Q = n \cdot e \)) (Física, Aula fis_m2_a1).
- Vetores, equilíbrio de forças e 3ª Lei de Newton (Mecânica, Aulas fis_m1_a2 e fis_m1_a4).

## 4. Explicação Teórica Aprofundada

### 1. Princípios Fundamentais da Eletrostática
1. **Princípio de Dufay (Atração e Repulsão):** Cargas de mesmo sinal repelem-se mutuamente; cargas de sinais contrários atraem-se mutuamente.
2. **Princípio da Conservação da Carga Elétrica:** Em um sistema eletricamente isolado, a soma algébrica das cargas elétricas permanece estritamente constante no tempo:
   \[ \sum Q_{\text{inicial}} = \sum Q_{\text{final}} \]
   Cargas não podem ser criadas nem destruídas do nada; elétrons são apenas transferidos de um corpo para outro.

### 2. Processos de Eletrização
1. **Eletrização por Atrito:**
   - Ocorre quando dois corpos de substâncias diferentes, inicialmente neutros, são esfregados um contra o outro.
   - Devido à diferença de afinidade eletrônica descrita na **Série Triboelétrica**, um material perde elétrons (ficando eletrizado **positivamente**) e o outro ganha exatamente o mesmo número de elétrons (ficando eletrizado **negativamente**).
   - *Resultado final:* Corpos ficam com cargas de **sinais opostos e mesmo módulo** (\( Q_A = -Q_B \)).
2. **Eletrização por Contato:**
   - Ocorre pelo toque físico direto entre condutores elétricos, sendo ao menos um previamente carregado.
   - Elétrons livres migram espontaneamente entre os condutores até que seus potenciais elétricos se igualem.
   - *Resultado final:* Corpos adquirem **cargas de mesmo sinal**.
   - *Caso Especial de Esferas Condutoras Idênticas:* A carga total distribui-se simetricamente em partes iguais:
     \[ q'_1 = q'_2 = \frac{q_1 + q_2}{2} \]
3. **Eletrização por Indução:**
   - Permite carregar um condutor metálico neutro (**Induzido**) sem tocá-lo, utilizando um corpo carregado (**Indutor**).
   - *Etapas do Processo:*
     1. O indutor carregado (ex.: negativo) aproxima-se do induzido neutro sem tocá-lo, provocando a separação de cargas por repulsão eletrostática (**Polarização de Cargas**).
     2. Mantendo o indutor por perto, conecta-se o induzido à **Terra (Fio Terra)**: elétrons repelidos escoam para a Terra.
     3. Desfaz-se a ligação com a Terra.
     4. Afasta-se o indutor: o induzido fica eletrizado com **sinal oposto ao do indutor** (neste caso, positivo) de forma uniforme.

### 3. A Lei de Coulomb
Em 1785, Charles-Augustin de Coulomb estabeleceu por meio de uma balança de torção que a intensidade da força eletrostática de atração ou repulsão entre duas cargas puntiformes no vácuo é diretamente proporcional ao produto dos módulos das cargas e inversamente proporcional ao quadrado da distância que as separa:
\[ F_e = \frac{k_0 \cdot |q_1 \cdot q_2|}{d^2} \]
- \( F_e \): força eletrostática mútua (\( \text{Newtons} \)).
- \( k_0 \): constante eletrostática do meio. No vácuo: \( k_0 \approx 9 \times 10^9\text{ N}\cdot\text{m}^2/\text{C}^2 \).
- \( q_1, q_2 \): cargas elétricas puntiformes (\( \text{Coulombs} \)).
- \( d \): distância métrica entre as cargas (\( \text{metros} \)).
- **Par Ação e Reação:** Pela 3ª Lei de Newton, a força que \( q_1 \) exerce em \( q_2 \) é **rigorosamente igual em módulo, tem a mesma direção e sentidos opostos** à força que \( q_2 \) exerce em \( q_1 \), independentemente dos valores das cargas serem muito desiguais!

## 5. Definições e Conceitos
- **Carga Puntiforme:** Corpo eletrizado cujas dimensões geométricas são totalmente desprezíveis quando comparadas às distâncias de separação envolvidas.
- **Eletroscópio:** Dispositivo utilizado para detectar qualitativamente a presença de cargas elétricas líquidas em um corpo (ex.: pêndulo eletrostático e eletroscópio de folhas de ouro).
- **Polarização de Dielétricos:** Reorientação de dipolos elétricos moleculares em materiais isolantes quando submetidos a um corpo eletrizado próximo, explicando por que um pente atritado atrai pedacinhos de papel neutros.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Contatos Sucessivos entre Três Esferas Idênticas
Três esferas metálicas idênticas A, B e C estão apoiadas sobre suportes isolantes. Inicialmente, A possui carga \( Q_A = +8\text{ }\mu\text{C} \), e B e C estão totalmente neutras. A esfera A é colocada em contato com B e depois separada. Em seguida, a esfera A é colocada em contato com C e depois separada. Determine a carga final de cada uma das três esferas.
- **Resolução:**
  1. Primeiro contato (A com B):
     \[ Q'_A = Q'_B = \frac{Q_A + Q_B}{2} = \frac{+8 + 0}{2} = +4\text{ }\mu\text{C} \]
  2. Segundo contato (A agora com \( +4\text{ }\mu\text{C} \) encosta em C neutra):
     \[ Q''_A = Q'_C = \frac{Q'_A + Q_C}{2} = \frac{+4 + 0}{2} = +2\text{ }\mu\text{C} \]
  - Resposta: As cargas finais serão \( Q_A = +2\text{ }\mu\text{C} \), \( Q_B = +4\text{ }\mu\text{C} \) e \( Q_C = +2\text{ }\mu\text{C} \). (A soma total \( 2 + 4 + 2 = 8\text{ }\mu\text{C} \) conserva a carga inicial!).

### Exemplo 2: Aplicação Direta da Lei de Coulomb
Duas cargas puntiformes no vácuo de valores \( q_1 = +2\text{ }\mu\text{C} = 2 \times 10^{-6}\text{ C} \) e \( q_2 = -3\text{ }\mu\text{C} = -3 \times 10^{-6}\text{ C} \) estão separadas por uma distância de \( 30\text{ cm} \) (\( 0{,}3\text{ m} \)). Determine a intensidade da força eletrostática entre elas e classifique-a como atrativa ou repulsiva.
- **Resolução:**
  1. Como os sinais são contrários, a força é **Atrativa**.
  2. Aplicação da Lei de Coulomb:
     \[ F_e = \frac{k_0 \cdot |q_1| \cdot |q_2|}{d^2} = \frac{(9 \times 10^9) \cdot (2 \times 10^{-6}) \cdot (3 \times 10^{-6})}{(0{,}3)^2} \]
     \[ F_e = \frac{54 \times 10^{-3}}{0{,}09} = \frac{54 \times 10^{-3}}{9 \times 10^{-2}} = 6 \times 10^{-1} = 0{,}6\text{ N} \]
  - Resposta: A força entre as cargas é atrativa e tem intensidade de \( 0{,}6\text{ N} \).

## 7. Aplicações Práticas e Modelagem
- **Pintura Eletrostática a Pó:** As peças metálicas automotivas são aterradas com carga positiva e a tinta em pó recebe carga negativa no bico da pistola; a forte atração eletrostática faz a tinta cobrir a peça homogeneamente sem escorrimentos e com desperdício mínimo.
- **Precipitadores Eletrostáticos Industriais:** Instalados em chaminés de termelétricas e siderúrgicas para carregar eletricamente partículas de fuligem e cinzas tóxicas, atraindo-as para placas coletoras e impedindo sua dispersão na atmosfera.

## 8. Exercícios de Fixação
1. Dois corpos condutores foram submetidos a um processo de eletrização. Ao final, verificou-se que ambos adquiriram cargas elétricas de mesmo sinal positivo. Qual foi o processo de eletrização empregado?
2. Se a distância métrica entre duas cargas pontuais em repouso for triplicada, o que acontecerá com a intensidade da força de Coulomb entre elas?
3. Por que uma bexiga de borracha atritada no cabelo consegue grudar na parede de alvenaria neutra de um quarto?

## 9. Correção Comentada dos Exercícios
1. Foi a **Eletrização por Contato**, pois ela é o único processo no qual os corpos terminam necessariamente com cargas de **mesmo sinal**.
2. A força é inversamente proporcional ao quadrado da distância: \( F \propto \frac{1}{d^2} \). Triplicando a distância (\( 3d \)), a nova força será: \( F' = \frac{F}{3^2} = \frac{F}{9} \). A força será reduzida a **um nono** de seu valor inicial.
3. Devido à **Polarização de Cargas** da parede: a bexiga eletrizada induz uma leve reorientação nas moléculas da parede neutra, aproximando as cargas de sinal contrário e gerando uma força resultante de atração superior à repulsão das cargas distantes.

## 10. Desafio de Domínio
Duas pequenas esferas idênticas carregadas com cargas \( +q \) e \( +4q \) estão fixas a uma distância \( D \). Em que ponto ao longo do segmento de reta que as une deve ser posicionada uma terceira carga \( Q \) para que o sistema permaneça em equilíbrio eletrostático estático resultante?
*Pista:* Iguale as forças de Coulomb exercidas pelas duas cargas sobre a carga intermediária: \( \frac{k q Q}{x^2} = \frac{k (4q) Q}{(D-x)^2} \).

## 11. Resumo e Pontos-Chave
- Conservação da carga: a soma das cargas de um sistema isolado é invariante.
- Atrito: corpos com sinais opostos e módulos iguais.
- Contato: corpos com mesmo sinal. Esferas idênticas: média aritmética \( (q_1 + q_2)/2 \).
- Indução: aproximação sem contato, polarização e terra; induzido fica com sinal oposto ao do indutor.
- Lei de Coulomb: \( F_e = \frac{k_0 |q_1 q_2|}{d^2} \).
- Força elétrica: par ação e reação (mesmo módulo nas duas cargas).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Eletromagnetismo*. Vol. 3. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física III: Eletromagnetismo*. 14. ed. Pearson, 2016.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Lei de Coulomb da Força Eletrostática",
                syntax = """F_e = \\frac{k_0 \\cdot |q_1 \\cdot q_2|}{d^2}""",
                description = "Força mútua vetorial de atração ou repulsão entre duas cargas puntiformes em repouso no vácuo.",
                parametersInfo = """k_0 = 9 \\times 10^9\\text{ N}\\cdot\\text{m}^2/\\text{C}^2; \\quad q_1, q_2: \\text{cargas (C)}; \\quad d: \\text{distância (m)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Conservação e Partição em Esferas Condutoras",
                syntax = """q' = \\frac{q_1 + q_2 + \\dots + q_n}{n}""",
                description = "Divisão simétrica e equitativa de cargas após contato físico direto entre n esferas condutoras idênticas.",
                parametersInfo = """q': \\text{carga final idêntica de cada esfera}; \\quad n: \\text{número de corpos em contato}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Indução com Aterramento",
                codeOrFormula = """\\text{Indutor Negativo} \\implies \\text{Elétrons escoam para a Terra} \\implies \\text{Induzido Positivo}""",
                explanation = "O aterramento do induzido polarizado drena as cargas repelidas, garantindo carga líquida final de polaridade inversa à do indutor."
            ),
            LessonExample(
                title = "Variação da Força com a Distância",
                codeOrFormula = """d' = \\frac{d}{2} \\implies F' = \\frac{k |q_1 q_2|}{(d/2)^2} = 4 \\cdot F""",
                explanation = "Reduzir a distância entre duas cargas pela metade quadruplica a força eletrostática mútua entre elas."
            )
        ),
        goldTip = "Decore os sinais finais nos 3 processos de eletrização: (1) ATRITO -> SINAIS OPOSTOS; (2) CONTATO -> MESMO SINAL; (3) INDUÇÃO -> SINAIS OPOSTOS (o induzido termina com carga de sinal contrário ao indutor). Não confunda contato com indução!"
    )

    // =========================================================================
    // AULA fis_m2_a5: Campo Elétrico, Potencial e Blindagem Eletrostática
    // =========================================================================
    private val bundleFisM2A5 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como uma carga elétrica consegue atrair ou repelir outra carga situada a metros de distância através do vácuo absoluto, sem que haja qualquer matéria visível conectando-as? A resposta revolucionária proposta pelo físico britânico Michael Faraday no século XIX foi o conceito de **Campo Elétrico**: toda carga elétrica distorce o espaço circundante, criando uma entidade física vetorial real que transmite forças eletromagnéticas à velocidade da luz. Nesta aula, exploramos o campo elétrico, as linhas de força, o potencial eletrostático e o fenômeno de proteção mais célebre do ENEM: a **Gaiola de Faraday e a Blindagem Eletrostática**.

## 2. Objetivos de Aprendizagem
- Definir o vetor **Campo Elétrico** (\( \vec{E} \)) em Newtons por Coulomb (\( \text{N/C} \)) ou Volts por metro (\( \text{V/m} \)).
- Relacionar a força elétrica com o campo: \( \vec{F}_e = q \cdot \vec{E} \), determinando a direção e o sentido da força para cargas de prova positivas e negativas.
- Calcular o campo elétrico de uma carga puntiforme: \( E = \frac{k_0 \cdot |Q|}{d^2} \) (vetor de afastamento para \( Q > 0 \); de aproximação para \( Q < 0 \)).
- Compreender as propriedades das **Linhas de Força** (nascem nas cargas positivas e morrem nas negativas).
- Modelar o **Campo Elétrico Uniforme (CEU)** entre placas paralelas: \( U = E \cdot d \).
- Definir o **Potencial Elétrico Escalar** (\( V = \frac{k_0 \cdot Q}{d} \)) e o trabalho da força elétrica: \( W = q \cdot (V_A - V_B) \).
- Dominar com maestria as propriedades dos **Condutores em Equilíbrio Eletrostático**:
  1. O campo elétrico no interior de um condutor é **rigorosamente NULO** (\( E_{\text{int}} = 0 \)).
  2. O excesso de cargas distribui-se **exclusivamente na superfície externa**.
  3. O potencial elétrico é **constante em todos os pontos internos e na superfície**.
  4. O **Poder das Pontas** e o funcionamento dos para-raios de Franklin.
  5. A **Gaiola de Faraday** e a Blindagem Eletrostática em aviões, automóveis e blindagens coaxiais.

## 3. Pré-requisitos
- Força eletrostática e Lei de Coulomb (Física, Aula fis_m2_a4).
- Trabalho e conservação de energia mecânica (Mecânica, Aula fis_m1_a6).

## 4. Explicação Teórica Aprofundada

### 1. Definição do Vetor Campo Elétrico (\( \vec{E} \))
Uma carga geradora \( Q \) cria no espaço ao seu redor um campo elétrico. Se inserirmos uma carga de prova \( q \) em um ponto desse espaço, ela experimentará uma força elétrica:
\[ \vec{E} = \frac{\vec{F}_e}{q} \implies \vec{F}_e = q \cdot \vec{E} \]
- **Sentido da Força:**
  - Se a carga de prova for **positiva (\( q > 0 \))**: \( \vec{F}_e \) tem a **mesma direção e mesmo sentido** do vetor \( \vec{E} \).
  - Se a carga de prova for **negativa (\( q < 0 \))**: \( \vec{F}_e \) tem a **mesma direção e sentido OPOSTO** ao vetor \( \vec{E} \).
- **Campo de uma Carga Puntiforme no Vácuo:**
  \[ E = \frac{k_0 \cdot |Q|}{d^2} \]
  - Se \( Q > 0 \): o campo é radial de **Afastamento**.
  - Se \( Q < 0 \): o campo é radial de **Aproximação**.

### 2. Linhas de Força e Campo Elétrico Uniforme (CEU)
- **Linhas de Força:** Linhas imaginárias tangentes em cada ponto ao vetor campo elétrico:
  - Nascem em cargas positivas e terminam em cargas negativas.
  - A densidade espacial de linhas representa a intensidade do campo: onde as linhas são mais aglomeradas, o campo é mais intenso.
  - Duas linhas de força **nunca se cruzam**.
- **Campo Elétrico Uniforme (CEU):** Gerado entre duas placas metálicas condutoras planas e paralelas eletrizadas com cargas de sinais contrários. No interior entre as placas:
  - O vetor campo \( \vec{E} \) tem **mesmo módulo, mesma direção e mesmo sentido em todos os pontos**.
  - Linhas de força retilíneas, paralelas e equidistantes.
  - Relação fundamental entre ddp, campo e distância entre as placas:
    \[ U = E \cdot d \]

### 3. Potencial Elétrico Escalar (\( V \)) e Trabalho
O potencial elétrico é uma grandeza escalar associada à energia potencial por unidade de carga:
\[ V = \frac{k_0 \cdot Q}{d} \quad (\text{Volts} = \text{J/C}) \]
- O trabalho realizado pela força elétrica para deslocar uma carga \( q \) de um ponto A até um ponto B independe da trajetória (campo eletrostático é uma força conservativa):
  \[ W_{A \to B} = q \cdot (V_A - V_B) = q \cdot U_{AB} \]
- Cargas positivas livres movem-se espontaneamente em direção a pontos de **menor potencial elétrico**; cargas negativas livres movem-se em direção a pontos de **maior potencial**.

### 4. Condutores em Equilíbrio Eletrostático e Blindagem
Quando as cargas elétricas em um condutor atingem o repouso estático, a física eletrostática impõe teoremas fundamentais:
1. **O Campo Elétrico Interno é Estritamente Nulo (\( E_{\text{int}} = 0 \)):** Se houvesse campo interno, os elétrons livres continuariam sendo acelerados pela força \( \vec{F} = q\vec{E} \), o que contraria o equilíbrio.
2. **Excesso de Cargas na Superfície Externa:** Pela mútua repulsão de Coulomb, os portadores de carga em excesso afastam-se ao máximo possível, acomodando-se integralmente na face externa do corpo.
3. **Superfície Equipotencial:** Todos os pontos da superfície e de todo o volume interno do condutor encontram-se rigorosamente no **mesmo potencial elétrico** (\( V_{\text{int}} = V_{\text{sup}} \)).
4. **Poder das Pontas:** Em corpos pontiagudos, a densidade superficial de cargas acumula-se preferencialmente nas regiões de menor raio de curvatura (pontas), gerando campos elétricos fortíssimos capazes de ionizar o ar circundante (vento elétrico). Esse é o princípio do **para-raios de Benjamin Franklin**.
5. **Gaiola de Faraday e Blindagem Eletrostática:** O interior oco de qualquer condutor metálico fechado (ou malha metálica condutora) fica **completamente blindado contra campos e influências elétricas externas**.
   - Por que é seguro permanecer dentro de um automóvel durante uma tempestade de raios? **Não é por causa dos pneus de borracha** (o raio que rasgou quilômetros de ar não seria barrado por alguns centímetros de pneu!). A segurança decorre exclusivamente da **carcaça metálica do carro**, que atua como uma perfeita Gaiola de Faraday conduzindo o raio pela superfície externa até o solo, mantendo o campo interno nulo!

## 5. Definições e Conceitos
- **Rigidez Dielétrica:** Valor máximo de campo elétrico que um material isolante consegue suportar antes de se ionizar tornando-se condutor (para o ar seco: \( E_{\text{máx}} \approx 3 \times 10^6\text{ V/m} \)).
- **Superfície Equipotencial:** Superfície geométrica tridimensional na qual todos os pontos possuem o mesmo potencial elétrico; as linhas de campo são sempre perpendiculares às superfícies equipotenciais.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Força sobre Elétron em Campo Elétrico Uniforme
Um elétron de carga \( q = -1{,}6 \times 10^{-19}\text{ C} \) e massa \( m = 9 \times 10^{-31}\text{ kg} \) penetra em uma região de campo elétrico uniforme horizontal orientado para a direita de intensidade \( E = 2000\text{ N/C} \). Determine a intensidade, direção e sentido da força elétrica e a aceleração adquirida pelo elétron.
- **Resolução:**
  1. Força elétrica:
     \[ F_e = |q| \cdot E = (1{,}6 \times 10^{-19}) \cdot 2000 = 3{,}2 \times 10^{-16}\text{ N} \]
  2. Como o elétron tem carga negativa, a força elétrica atua na direção horizontal, com **sentido para a ESQUERDA** (oposto ao campo \( \vec{E} \)).
  3. Aceleração pela 2ª Lei de Newton:
     \[ a = \frac{F_e}{m} = \frac{3{,}2 \times 10^{-16}}{9 \times 10^{-31}} \approx 3{,}56 \times 10^{14}\text{ m/s}^2 \]
  - Resposta: A força é de \( 3{,}2 \times 10^{-16}\text{ N} \) para a esquerda e a aceleração é de \( \approx 3{,}56 \times 10^{14}\text{ m/s}^2 \).

### Exemplo 2: Gaiola de Faraday em Elevadores
Por que em muitos edifícios a chamada telefônica de um smartphone é interrompida ou perde completamente a barra de sinal quando a porta metálica do elevador se fecha?
- **Resolução:**
  - A cabine metálica do elevador fechada atua como uma **Gaiola de Faraday**, bloqueando a penetração de campos elétricos e ondas eletromagnéticas de alta frequência (blindagem eletrostática), impedindo a comunicação com a antena da operadora celular.

## 7. Aplicações Práticas e Modelagem
- **Cabos Coaxiais de TV e Internet:** Compostos por um fio de cobre central blindado por uma malha metálica cilíndrica trançada aterrada que impede que ruídos eletromagnéticos externos interfiram no sinal de dados.
- **Roupas Metálicas de Eletricistas de Alta Tensão:** Macacões com malhas de fios de aço inoxidável permitem que eletricistas façam manutenção em linhas vivas de \( 500\text{ kV} \) com as próprias mãos sem sofrer choque, pois a corrente circula pela malha externa do macacão.

## 8. Exercícios de Fixação
1. Em uma tempestade com intensa queda de raios em uma rodovia, qual é a atitude mais segura a ser tomada por um motorista: permanecer abrigado dentro do veículo com portas fechadas ou sair correndo para se abrigar debaixo de uma árvore alta? Justifique fisicamente.
2. Duas placas paralelas separadas por \( 5\text{ cm} \) (\( 0{,}05\text{ m} \)) estão submetidas a uma ddp de \( 200\text{ V} \). Qual é a intensidade do campo elétrico uniforme existente entre elas?
3. O que acontece com o campo elétrico no centro de uma esfera oca de cobre carregada positivamente?

## 9. Correção Comentada dos Exercícios
1. A atitude segura é **permanecer dentro do veículo fechado**. A carcaça metálica do automóvel funciona como uma **Gaiola de Faraday** (blindagem eletrostática), desviando a descarga elétrica pela superfície externa até o solo sem afetar o interior. Ficar debaixo de uma árvore é letal devido à sua forma pontiaguda (poder das pontas atrai raios).
2. Pela relação no CEU: \( U = E \cdot d \implies 200 = E \cdot 0{,}05 \implies E = \frac{200}{0{,}05} = 4000\text{ V/m} \) (ou \( 4000\text{ N/C} \)).
3. O campo elétrico no interior é **rigorosamente NULO (\( E = 0 \))**, pois o corpo condutor está em equilíbrio eletrostático e todo o excesso de carga distribui-se exclusivamente na face externa.

## 10. Desafio de Domínio
Por que um pássaro consegue pousar tranquilamente sobre um cabo de alta tensão descascado de \( 13.800\text{ V} \) sem levar nenhum choque, mas se uma pessoa encostar uma escada de alumínio do chão até esse mesmo fio sofrerá uma descarga fatal? Explique a diferença utilizando os conceitos de ddp (\( U \)) e potencial elétrico absoluto.
*Pista:* Avalie a distância entre as patas do pássaro e a resistência elétrica do trecho de fio entre elas.

## 11. Resumo e Pontos-Chave
- Vetor campo elétrico: \( \vec{F}_e = q \cdot \vec{E} \). Carga positiva: força no sentido do campo; negativa: sentido contrário.
- Carga puntiforme: \( E = \frac{k_0 |Q|}{d^2} \) (afastamento para positiva, aproximação para negativa).
- Campo elétrico uniforme (CEU): \( U = E \cdot d \).
- Trabalho: \( W = q(V_A - V_B) \).
- Condutores em equilíbrio eletrostático: campo interno é zero (\( E_{\text{int}} = 0 \)), excesso de carga na superfície, potencial constante.
- Gaiola de Faraday: blindagem eletrostática no interior oco metálico (carros, aviões, cabos coaxiais).
- Poder das pontas: concentração de campo em arestas agudas (para-raios).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Eletromagnetismo*. Vol. 3. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física III: Eletromagnetismo*. 14. ed. Pearson, 2016.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Relação Força-Campo Elétrico",
                syntax = """\\vec{F}_e = q \\cdot \\vec{E}""",
                description = "Força eletrostática vetorial exercida sobre uma carga de prova imersa em um campo elétrico.",
                parametersInfo = """F_e: \\text{força (N)}; \\quad q: \\text{carga com sinal (C)}; \\quad E: \\text{vetor campo (N/C ou V/m)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Equação do Campo Elétrico Uniforme (CEU)",
                syntax = """U = E \\cdot d""",
                description = "Relação escalar linear entre ddp, módulo do campo e espaçamento retilíneo entre placas condutoras paralelas.",
                parametersInfo = """U: \\text{tensão (Volts)}; \\quad E: \\text{campo uniforme (V/m)}; \\quad d: \\text{distância métrica (m)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Campo de Carga Puntiforme no Vácuo",
                syntax = """E = \\frac{k_0 \\cdot |Q|}{d^2}""",
                description = "Intensidade do campo decaindo com o quadrado da distância a partir de uma fonte puntiforme.",
                parametersInfo = """k_0 = 9 \\times 10^9\\text{ N}\\cdot\\text{m}^2/\\text{C}^2; \\quad Q: \\text{carga geradora}; \\quad d: \\text{distância}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Segurança em Veículoatingido por Raio",
                codeOrFormula = """E_{\\text{interior}} = 0 \\quad (\\text{Blindagem de Faraday})""",
                explanation = "A carcaça condutora metálica do automóvel isola completamente o habitáculo contra qualquer campo ou corrente elétrica externa."
            ),
            LessonExample(
                title = "Trajetória Parabólica em Campo Uniforme",
                codeOrFormula = """a_y = \\frac{qE}{m} = \\text{constante} \\implies \\text{Movimento Análogo ao Lançamento Horizontal}""",
                explanation = "Partículas carregadas injetadas perpendicularmente a um CEU executam movimento parabólico uniforme de deflexão."
            )
        ),
        goldTip = "O ENEM adora o mito dos pneus do carro! Se a questão perguntar por que é seguro ficar dentro do carro durante tempestades de raios, a resposta NUNCA é 'porque os pneus isolam'. A resposta correta é SEMPRE: 'Porque a carcaça metálica atua como uma GAIOLA DE FARADAY (blindagem eletrostática), fazendo o campo elétrico interno ser nulo'!"
    )

    // =========================================================================
    // AULA fis_m2_a6: Eletromagnetismo, Campo Magnético e Indução de Faraday
    // =========================================================================
    private val bundleFisM2A6 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Em 1820, o professor dinamarquês Hans Christian Oersted realizou uma demonstração experimental que mudou para sempre os rumos da civilização: ao aproximar uma bússola magnética de um fio percorrido por corrente elétrica, a agulha sofreu uma deflexão abrupta. Pela primeira vez na história, comprovou-se que a eletricidade e o magnetismo não eram reinos isolados, mas duas faces da mesmíssima força: o **Eletromagnetismo**. Poucos anos depois, Michael Faraday realizou o caminho inverso, demonstrando que a variação de campos magnéticos induz correntes elétricas. Da geração de quase toda a eletricidade do planeta nas usinas hidrelétricas e eólicas aos motores elétricos, guindastes de sucata e transformadores de rede, o eletromagnetismo é o ápice da física clássica no ENEM.

## 2. Objetivos de Aprendizagem
- Identificar as propriedades dos **Ímãs Naturais e Artificiais**: polos Norte e Sul e o **Princípio da Inseparabilidade dos Polos Magnéticos**.
- Compreender o **Campo Magnético Terrestre** e a orientação de bússolas (o Polo Norte magnético da Terra situa-se próximo ao Polo Sul geográfico).
- Analisar o **Experimento de Oersted**: correntes elétricas geram campos magnéticos circundantes.
- Aplicar a **Regra da Mão Direita** para determinar o sentido do campo magnético em fios retilíneos (\( B = \frac{\mu_0 \cdot i}{2\pi r} \)), espiras circulares e solenoides/eletroímãs.
- Deduzir e aplicar a **Força Magnética de Lorentz** sobre cargas móveis: \( F_{\text{mag}} = |q| \cdot v \cdot B \cdot \operatorname{sen}\theta \), aplicando a **Regra da Palma da Mão Direita / Regra da Mão Esquerda**.
- Analisar a trajetória circular de cargas em campo uniforme: raio da órbita \( R = \frac{m \cdot v}{|q| \cdot B} \).
- Calcular a força magnética sobre condutores retilíneos: \( F = B \cdot i \cdot L \cdot \operatorname{sen}\theta \) (princípio do motor elétrico).
- Definir **Fluxo Magnético** (\( \Phi = B \cdot A \cdot \cos\theta \)) e dominar a **Lei de Faraday-Neumann-Lenz**: \( \mathcal{E} = -\frac{\Delta \Phi}{\Delta t} \).
- Compreender a **Lei de Lenz** como a manifestação do princípio da conservação da energia na indução (a corrente induzida opõe-se à causa que a originou).
- Modelar o funcionamento de **Transformadores de Tensão**: \( \frac{U_1}{U_2} = \frac{N_1}{N_2} = \frac{i_2}{i_1} \).

## 3. Pré-requisitos
- Corrente, tensão e circuitos elétricos (Física, Aulas fis_m2_a1 e fis_m2_a2).
- Movimento Circular Uniforme e força centrípeta (Mecânica, Aula fis_m1_a3).

## 4. Explicação Teórica Aprofundada

### 1. Ímãs e Campo Magnético Terrestre
- **Polos Magnéticos:** Todo ímã possui dois polos inseparáveis: Norte (\( N \)) e Sul (\( S \)). Polos de mesmo nome repelem-se; polos de nomes contrários atraem-se.
- **Inseparabilidade dos Polos:** Se você cortar um ímã em barra ao meio, **você não obtém um polo norte isolado e um polo sul isolado**. Obtém-se imediatamente dois ímãs menores completos, cada um com seu próprio polo norte e sul. Não existem monopólos magnéticos na natureza clássica!
- **Magnetismo da Terra:** A Terra comporta-se como um gigantesco ímã esférico cujo núcleo de ferro e níquel fundidos em convecção gera um escudo protetor contra o vento solar (Magnetosfera):
  - O polo norte geográfico atrai o polo norte da bússola: logo, no norte geográfico encontra-se o **Polo Sul Magnético** da Terra!
  - O polo sul geográfico abriga o **Polo Norte Magnético** terrestre.

### 2. O Experimento de Oersted e Fontes de Campo
Oersted provou que **cargas elétricas em movimento geram campo magnético ao seu redor**.
- **Fio Retilíneo Longo:**
  As linhas de campo magnético são circunferências concêntricas centradas no fio.
  - Regra da mão direita: o polegar aponta no sentido da corrente \( i \); os outros quatro dedos curvados indicam o sentido das linhas de indução magnética \( \vec{B} \).
  - Intensidade (Lei de Biot-Savart):
    \[ B = \frac{\mu_0 \cdot i}{2\pi \cdot r} \]
- **Espira Circular (no centro):**
  \[ B = \frac{\mu_0 \cdot i}{2 R} \]
- **Solenoide (Bobina Longa / Eletroímã):**
  \[ B = \mu_0 \cdot \left(\frac{N}{L}\right) \cdot i = \mu_0 \cdot n \cdot i \]
  No interior do solenoide, o campo magnético é praticamente **uniforme e intenso**. Ao inserir um núcleo de ferro maciço no interior, obtém-se um **Eletroímã**, amplificando o campo milhares de vezes.

### 3. Força Magnética sobre Cargas Móveis (Força de Lorentz)
Quando uma partícula com carga \( q \) penetra com velocidade \( \vec{v} \) em uma região de campo magnético uniforme \( \vec{B} \):
\[ F_{\text{mag}} = |q| \cdot v \cdot B \cdot \operatorname{sen}\theta \]
onde \( \theta \) é o ângulo entre o vetor velocidade \( \vec{v} \) e o vetor campo \( \vec{B} \).
- **Regra da Palma da Mão Direita (ou Regra da Mão Esquerda):**
  - Polegar: velocidade da partícula (\( \vec{v} \)).
  - Quatro dedos estendidos: vetor campo magnético (\( \vec{B} \)).
  - Palma da mão (empurrão): força magnética (\( \vec{F}_{\text{mag}} \)) se \( q > 0 \). (Se \( q < 0 \), a força atua no dorso da mão, no sentido contrário!).
- **Casos Especiais de Lançamento:**
  1. *Lançamento Paralelo (\( \theta = 0^\circ \) ou \( 180^\circ \)):* \( \operatorname{sen}\theta = 0 \implies F_{\text{mag}} = 0 \). A partícula não sofre desvio, mantendo **Movimento Retilíneo Uniforme (MRU)**.
  2. *Lançamento Perpendicular (\( \theta = 90^\circ \)):* \( \operatorname{sen}(90^\circ) = 1 \implies F_{\text{mag}} = |q| v B \). Como a força magnética é perpendicular à velocidade em todos os instantes, **ela não realiza trabalho e não altera o módulo da velocidade**: atua puramente como **Força Centrípeta**, forçando a partícula a descrever um **Movimento Circular Uniforme (MCU)**:
     \[ F_{\text{mag}} = F_{\text{cp}} \implies |q| v B = \frac{m v^2}{R} \implies R = \frac{m \cdot v}{|q| \cdot B} \]
     (Mnemônica consagrada: *"Raio = Meu Violão Não Quebrou Bomba"*).

### 4. Força Magnética sobre Condutor com Corrente (Lei de Laplace)
Um fio condutor retilíneo de comprimento \( L \) conduzindo corrente \( i \) imerso em um campo \( \vec{B} \) sofre uma força mecânica:
\[ F = B \cdot i \cdot L \cdot \operatorname{sen}\theta \]
Esse fenômeno é o princípio de funcionamento do **Motor Elétrico**: a transformação de energia elétrica em torque e energia mecânica de rotação.

### 5. Indução Eletromagnética: Lei de Faraday e Lei de Lenz
1. **Fluxo Magnético (\( \Phi \)):** Mede a quantidade de linhas de indução magnética que atravessam uma determinada área de espira:
   \[ \Phi = B \cdot A \cdot \cos\theta \quad (\text{Weber} = \text{T}\cdot\text{m}^2) \]
2. **Lei de Faraday-Neumann:** Sempre que o fluxo magnético que atravessa um circuito condutor sofrer uma variação temporal (\( \Delta \Phi \neq 0 \)), surge no circuito uma força eletromotriz induzida (\( \mathcal{E} \)) capaz de gerar uma **corrente elétrica induzida**:
   \[ \mathcal{E} = -\frac{\Delta \Phi}{\Delta t} \]
   A variação do fluxo pode ocorrer de três formas: variando o campo \( B \), variando a área \( A \), ou girando a espira (variando o ângulo \( \theta \)). É assim que operam os alternadores das usinas hidrelétricas, termelétricas e aerogeradores!
3. **Lei de Lenz (Sinal Negativo):** **"O sentido da corrente elétrica induzida é tal que o campo magnético criado por ela sempre se OPÕE à variação de fluxo magnético que a produziu."**
   - Se o fluxo magnético está *aumentando*, o circuito gera um campo em sentido *oposto* para tentar frear o aumento.
   - Se o fluxo está *diminuindo*, o circuito gera um campo no *mesmo sentido* para tentar sustentar o fluxo.
   - Aplicações: freios magnéticos de trens de alta velocidade (frenagem por correntes de Foucault / parasitas sem desgaste mecânico de pastilhas) e cooktops de indução magnética.

### 6. Transformadores de Tensão
Dispositivos formados por um núcleo ferromagnético fechado envolto por duas bobinas independentes: o enrolamento **Primário** (com \( N_1 \) espiras) e o **Secundário** (com \( N_2 \) espiras):
- Operam **exclusivamente com Corrente Alternada (CA)**, pois exigem variação contínua de fluxo magnético para haver indução mútua. **Transformadores NÃO funcionam em Corrente Contínua (CC)!**
- **Relação de Transformação de Tensões:**
  \[ \frac{U_1}{U_2} = \frac{N_1}{N_2} \]
- **Conservação de Potência em Transformadores Ideais (\( P_1 = P_2 \)):**
  \[ U_1 \cdot i_1 = U_2 \cdot i_2 \implies \frac{U_1}{U_2} = \frac{i_2}{i_1} = \frac{N_1}{N_2} \]
  Se o transformador eleva a tensão (\( U_2 > U_1 \)), a corrente é obrigatoriamente reduzida na mesma proporção (\( i_2 < i_1 \)).

## 5. Definições e Conceitos
- **Tesla (T):** Unidade de intensidade de campo magnético no SI (\( 1\text{ T} = 1\text{ N}/(\text{A}\cdot\text{m}) \)). O campo magnético da Terra na superfície é de cerca de \( 50\text{ }\mu\text{T} \).
- **Correntes de Foucault (Eddy Currents):** Correntes circulares induzidas no interior de massas metálicas maciças quando submetidas a fluxos magnéticos variáveis, causando aquecimento dissipativo ou forte amortecimento eletrodinâmico.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Trajetória Circular de Próton no Campo Magnético
Um próton de massa \( m = 1{,}67 \times 10^{-27}\text{ kg} \) e carga \( q = +1{,}6 \times 10^{-19}\text{ C} \) penetra perpendicularmente (\( \theta = 90^\circ \)) em um campo magnético uniforme de intensidade \( B = 0{,}5\text{ T} \) com velocidade constante de \( 2 \times 10^6\text{ m/s} \). Calcule o raio da órbita circular descrita pela partícula.
- **Resolução:**
  1. Como a velocidade é perpendicular ao campo, a força magnética atua como resultante centrípeta gerando MCU:
     \[ R = \frac{m \cdot v}{|q| \cdot B} \]
  2. Substituição dos dados numéricos:
     \[ R = \frac{(1{,}67 \times 10^{-27}) \cdot (2 \times 10^6)}{(1{,}6 \times 10^{-19}) \cdot 0{,}5} = \frac{3{,}34 \times 10^{-21}}{0{,}8 \times 10^{-19}} = 4{,}175 \times 10^{-2}\text{ m} \approx 4{,}18\text{ cm} \]
  - Resposta: O próton descreve uma trajetória circular de raio \( R \approx 4{,}18\text{ cm} \).

### Exemplo 2: Transformador de Subestação de Bairro
Um transformador abaixador residencial recebe em seu enrolamento primário uma tensão alternada de \( 13.200\text{ V} \) proveniente da rede de distribuição de alta tensão e fornece \( 220\text{ V} \) no enrolamento secundário para as residências. Sabendo que o primário possui \( 6000\text{ espiras} \), quantas espiras deve ter o secundário? Se a corrente no secundário for de \( 60\text{ A} \), qual é a corrente no primário?
- **Resolução:**
  1. Número de espiras do secundário:
     \[ \frac{U_1}{U_2} = \frac{N_1}{N_2} \implies \frac{13200}{220} = \frac{6000}{N_2} \implies 60 = \frac{6000}{N_2} \implies N_2 = 100\text{ espiras} \]
  2. Corrente no primário pela conservação da potência:
     \[ \frac{U_1}{U_2} = \frac{i_2}{i_1} \implies 60 = \frac{60}{i_1} \implies i_1 = 1{,}0\text{ A} \]
  - Resposta: O secundário possui \( 100\text{ espiras} \) e a corrente que circula na rede primária de alta tensão é de apenas \( 1{,}0\text{ A} \).

## 7. Aplicações Práticas e Modelagem
- **Usinas Hidrelétricas (Itaipu / Belo Monte):** A água desce pela tubulação sob imensa pressão e gira as pás das turbinas; o eixo mecânico aciona rotores cheios de eletroímãs gigantescos que giram dentro de bobinas fixas (estator), gerando gigawatts de energia elétrica limpa por indução de Faraday contínua.
- **Fogões por Indução Eletromagnética (Cooktop):** Bobinas indutoras sob a placa vitrocerâmica geram campos magnéticos alternados de alta frequência. Quando uma panela de ferro ou aço é colocada em cima, o fluxo magnético variável induz violentas correntes de Foucault na base da panela, aquecendo o alimento por efeito Joule instantaneamente, mantendo a placa de vidro fria!
- **Trens de Levitação Magnética (Maglev):** Bobinas supercondutoras geram forças magnéticas de repulsão suficientes para sustentar vagões pesados flutuando a centímetros dos trilhos, eliminando o atrito mecânico de rolamento e atingindo velocidades superiores a \( 500\text{ km/h} \).

## 8. Exercícios de Fixação
1. Ao aproximarmos o polo Norte de um ímã em barra da face de uma espira condutora circular em repouso, o que acontece com a espira? Qual polo magnético a face frontal da espira formará segundo a Lei de Lenz?
2. Uma partícula com carga elétrica positiva penetra em repouso (\( v = 0 \)) em uma região onde existe exclusivamente um campo magnético uniforme de \( 10\text{ T} \). A partícula será acelerada pela força magnética?
3. Explique por que um transformador elétrico não funciona se for alimentado pela corrente contínua fornecida por uma bateria de carro de 12V.

## 9. Correção Comentada dos Exercícios
1. A aproximação do ímã provoca um aumento no fluxo magnético que atravessa a espira. Pela **Lei de Faraday**, surgirá uma corrente elétrica induzida. Pela **Lei de Lenz**, a espira gera um campo que se opõe à aproximação; logo, a face voltada para o ímã formará um **Polo Norte induzido** para repelir a aproximação do ímã.
2. **Não**. Pela Lei de Lorentz, \( F_{\text{mag}} = |q| \cdot v \cdot B \cdot \operatorname{sen}\theta \). Se a velocidade é nula (\( v = 0 \)), a força magnética é **estritamente nula**. Campos magnéticos não exercem forças sobre cargas em repouso!
3. Porque a indução eletromagnética descrita pela Lei de Faraday exige uma **variação temporal contínua do fluxo magnético** (\( \Delta \Phi/\Delta t \neq 0 \)). A bateria gera corrente contínua constante, que produz um campo magnético estático e invariável, sendo incapaz de induzir qualquer fem no enrolamento secundário.

## 10. Desafio de Domínio
Considere um anel circular de alumínio (metal não magnético) solto em queda livre sob gravidade através de um tubo oco transparente. Quando o anel passa pelo interior de uma região onde há um forte campo magnético horizontal localizado, sua velocidade de queda diminui sensivelmente, como se estivesse caindo através de mel viscoso. Qual é o fenômeno físico responsável por esse amortecimento e por que ele não viola a conservação da energia mecânica?
*Pista:* Relacione as Correntes de Foucault induzidas na massa do anel com a Lei de Lenz e a dissipação térmica por Efeito Joule.

## 11. Resumo e Pontos-Chave
- Ímãs: polos norte e sul inseparáveis. Campo da Terra: norte geográfico abriga sul magnético.
- Oersted: corrente elétrica gera campo magnético. Fio longo: \( B = \frac{\mu_0 i}{2\pi r} \).
- Força de Lorentz: \( F_{\text{mag}} = |q| v B \operatorname{sen}\theta \). Carga parada (\( v = 0 \)) não sofre força magnética.
- Lançamento perpendicular (\( \theta = 90^\circ \)): MCU de raio \( R = \frac{mv}{|q|B} \). A força magnética não realiza trabalho!
- Força em fios: \( F = B i L \operatorname{sen}\theta \) (princípio do motor elétrico).
- Lei de Faraday: variação de fluxo gera fem induzida: \( \mathcal{E} = -\frac{\Delta \Phi}{\Delta t} \).
- Lei de Lenz: a corrente induzida opõe-se à variação de fluxo que a originou (conservação da energia).
- Transformadores: \( \frac{U_1}{U_2} = \frac{N_1}{N_2} = \frac{i_2}{i_1} \). Funcionam apenas com Corrente Alternada (CA).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Eletromagnetismo*. Vol. 3. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 2. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física III: Eletromagnetismo*. 14. ed. Pearson, 2016.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Força Magnética de Lorentz sobre Carga Móvel",
                syntax = """F_{\\text{mag}} = |q| \\cdot v \\cdot B \\cdot \\operatorname{sen}\\theta""",
                description = "Força defletora perpendicular exercida por um campo magnético sobre uma partícula carregada em movimento.",
                parametersInfo = """q: \\text{carga (C)}; \\quad v: \\text{velocidade (m/s)}; \\quad B: \\text{campo (T)}; \\quad \\theta: \\text{ângulo entre } \\vec{v} \\text{ e } \\vec{B}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Raio da Órbita Circular em MCU Magnético",
                syntax = """R = \\frac{m \\cdot v}{|q| \\cdot B}""",
                description = "Raio de curvatura da trajetória circular gerada pela ação estritamente centrípeta da força magnética perpendicular.",
                parametersInfo = """m: \\text{massa da partícula (kg)}; \\quad v: \\text{velocidade}; \\quad q: \\text{carga}; \\quad B: \\text{intensidade do campo (T)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Lei da Indução de Faraday-Lenz",
                syntax = """\\mathcal{E} = -\\frac{\\Delta \\Phi}{\\Delta t} \\quad \\text{onde } \\Phi = B \\cdot A \\cdot \\cos\\theta""",
                description = "Força eletromotriz induzida proporcional à taxa temporal de variação do fluxo magnético através do circuito.",
                parametersInfo = """\\mathcal{E}: \\text{fem induzida (V)}; \\quad \\Phi: \\text{fluxo em Webers (Wb)}; \\quad t: \\text{tempo (s)}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Funcionamento da Usina Hidrelétrica",
                codeOrFormula = """\\Delta \\Phi / \\Delta t \\neq 0 \\implies \\mathcal{E} = -N \\frac{\\Delta \\Phi}{\\Delta t} \\implies \\text{Geração de Eletricidade}""",
                explanation = "A rotação forçada das bobinas pelo fluxo hídrico induz tensões elétricas alternadas contínuas de alta potência na rede elétrica."
            ),
            LessonExample(
                title = "Transformador Elevador na Transmissão de Energia",
                codeOrFormula = """\\frac{U_{\\text{rede}}}{U_{\\text{usina}}} = \\frac{N_2}{N_1} > 1 \\implies i_{\\text{rede}} = i_{\\text{usina}} \\cdot \\frac{U_{\\text{usina}}}{U_{\\text{rede}}} \\ll i_{\\text{usina}}""",
                explanation = "Elevar a tensão para centenas de milhares de Volts derruba a corrente circulante, reduzindo drasticamente as perdas ôhmicas por efeito Joule nos cabos."
            )
        ),
        goldTip = "Dois pontos fundamentais no ENEM: (1) Se a carga estiver em REPOUSO (v = 0), a força magnética é ZERO! Carga parada não interage com campo magnético estático. (2) TRANSFORMADORES NUNCA funcionam com corrente contínua (pilha/bateria), pois precisam de fluxo variável contínuo (corrente alternada) para haver indução!"
    )
}

