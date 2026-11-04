package com.example.data.physics.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Física ENEM — Módulo 1: Mecânica (Parte 2: Aulas fis_m1_a6 a fis_m1_a10).
 * Estrutura curricular rigorosa em 12 seções por aula, fundamentada em Halliday & Resnick, Sears & Zemansky, Tipler e Matriz de Referência do ENEM / INEP.
 */
object PhysicsMechanicsPart2 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "fis_m1_a6" -> bundleFisM1A6
            "fis_m1_a7" -> bundleFisM1A7
            "fis_m1_a8" -> bundleFisM1A8
            "fis_m1_a9" -> bundleFisM1A9
            "fis_m1_a10" -> bundleFisM1A10
            else -> null
        }
    }

    // =========================================================================
    // AULA fis_m1_a6: Trabalho, Potência e Energia Mecânica
    // =========================================================================
    private val bundleFisM1A6 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A energia é indiscutivelmente o conceito unificador mais profundo de toda a ciência natural. Desde os primórdios da termodinâmica e da mecânica clássica, o ser humano percebeu que a energia não pode ser criada nem destruída, mas apenas transformada de uma modalidade para outra. No ENEM, as questões de conservação da energia mecânica, o cálculo do trabalho realizado por forças constantes ou variáveis (como a força elástica) e o dimensionamento da potência de motores e hidrelétricas constituem um dos tópicos mais frequentes de toda a prova de Ciências da Natureza.

## 2. Objetivos de Aprendizagem
- Definir Trabalho Mecânico (\( W = F \cdot d \cdot \cos\theta \)) e interpretar seu sinal (trabalho motor, resistente ou nulo).
- Calcular o trabalho de forças variáveis através da área geométrica sob o gráfico \( F \times d \).
- Compreender e aplicar o **Teorema da Energia Cinética (TEC)**: \( W_{\text{resultante}} = \Delta E_c \).
- Definir Energia Potencial Gravitacional (\( E_{pg} = m g h \)) e Energia Potencial Elástica (\( E_{pel} = \frac{1}{2} k x^2 \)).
- Diferenciar Forças Conservativas (gravidade, elástica) de Forças Dissipativas (atrito, resistência do ar).
- Aplicar a **Conservação da Energia Mecânica** (\( E_{m,\text{inicial}} = E_{m,\text{final}} \)) em montanhas-russas, pêndulos e pistas de skate.
- Calcular Potência Mecânica (\( P = W / \Delta t = F \cdot v \)) e Rendimento (\( \eta = P_{\text{útil}} / P_{\text{total}} \)).

## 3. Pré-requisitos
- Cinemática e Equação de Torricelli (Aula fis_m1_a1).
- Leis de Newton e Força Elástica (Aula fis_m1_a4).

## 4. Explicação Teórica Aprofundada

### 1. Trabalho de uma Força Constante
O trabalho (\( W \) ou \( \tau \)) mede a quantidade de energia transferida a um corpo mediante a ação de uma força ao longo de um deslocamento:
\[ W = F \cdot d \cdot \cos\theta \]
- Onde \( \theta \) é o ângulo formado entre o vetor força \( \vec{F} \) e o vetor deslocamento \( \vec{d} \).
- No SI: medido em **Joules (J)** (\( 1\text{ J} = 1\text{ N} \cdot 1\text{ m} \)).
- **Casos Especiais de Ângulo:**
  - \( \theta = 0^\circ \implies \cos 0^\circ = 1 \implies W = F \cdot d \) (**Trabalho Motor Máximo**: a força ajuda o movimento).
  - \( \theta = 180^\circ \implies \cos 180^\circ = -1 \implies W = -F \cdot d \) (**Trabalho Resistente**: a força atrapalha o movimento, como a força de atrito).
  - \( \theta = 90^\circ \implies \cos 90^\circ = 0 \implies W = 0 \) (**Trabalho Nulo**: forças perpendiculares ao deslocamento, como a Força Normal em piso horizontal ou a Força Centrípeta, **JAMAIS realizam trabalho**!).

### 2. Teorema da Energia Cinética (TEC)
A **Energia Cinética (\( E_c \))** é a modalidade associada ao estado de movimento e velocidade de uma massa:
\[ E_c = \frac{m \cdot v^2}{2} \]
- **O Teorema da Energia Cinética:** O trabalho da força resultante que atua sobre um corpo entre dois pontos equivale exatamente à variação de sua energia cinética:
  \[ W_{\text{res}} = \Delta E_c = E_{c,\text{final}} - E_{c,\text{inicial}} = \frac{m v^2}{2} - \frac{m v_0^2}{2} \]
  Esse teorema é universal: vale para qualquer tipo de trajetória e qualquer tipo de força (conservativa ou dissipativa).

### 3. Energias Potenciais e Sistemas Conservativos
- **Energia Potencial Gravitacional (\( E_{pg} \)):** Energia armazenada pela posição relativa em um campo gravitacional:
  \[ E_{pg} = m \cdot g \cdot h \]
- **Energia Potencial Elástica (\( E_{pel} \)):** Energia acumulada na deformação de molas e elastômeros:
  \[ E_{pel} = \frac{k \cdot x^2}{2} \]
- **Energia Mecânica Total (\( E_m \)):**
  \[ E_m = E_c + E_p = \frac{m v^2}{2} + m g h + \frac{k x^2}{2} \]
- **Princípio da Conservação da Energia Mecânica:** Na presença exclusiva de **forças conservativas** (peso e força elástica), a energia mecânica do sistema permanece rigorosamente constante ao longo de todo o trajeto:
  \[ E_{m,\text{inicial}} = E_{m,\text{final}} \iff E_{c1} + E_{p1} = E_{c2} + E_{p2} \]
- **Sistemas Dissipativos (Com Atrito):** As forças dissipativas degradam parte da energia mecânica útil em calor e deformação:
  \[ W_{\text{atrito}} = \Delta E_m = E_{m,\text{final}} - E_{m,\text{inicial}} < 0 \]

### 4. Potência e Rendimento
- **Potência Média (\( P \)):** Taxa temporal de realização de trabalho ou consumo de energia:
  \[ P = \frac{W}{\Delta t} = \frac{\Delta E}{\Delta t} \]
  No SI: expressa em **Watts (W)** (\( 1\text{ W} = 1\text{ J/s} \)).
  - Relação com velocidade: \( P = \frac{F \cdot d}{\Delta t} = F \cdot v_m \).
  - Unidade prática: Cavalo-Vapor (\( 1\text{ cv} \approx 735{,}5\text{ W} \)) ou Horse-Power (\( 1\text{ HP} \approx 746\text{ W} \)).
- **Rendimento (\( \eta \)):** Fração da potência total fornecida que é efetivamente convertida em trabalho útil:
  \[ \eta = \frac{P_{\text{útil}}}{P_{\text{total}}} = \frac{E_{\text{útil}}}{E_{\text{total}}} \quad (0 \le \eta < 1) \]

## 5. Definições e Conceitos
- **Força Conservativa:** Força cujo trabalho realizado sobre uma partícula independe da trajetória percorrida, dependendo unicamente das posições inicial e final (ex.: gravidade). Em um percurso fechado (ida e volta), seu trabalho total é nulo.
- **Força Dissipativa:** Força que retira energia mecânica do sistema convertendo-a irreversivelmente em energia térmica (ex.: atrito dinâmico).
- **Quilowatt-hora (kWh):** Unidade de energia (e não de potência!) utilizada nas contas de luz: \( 1\text{ kWh} = 1.000\text{ W} \times 3.600\text{ s} = 3{,}6 \times 10^6\text{ Joules} \).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Carrinho de Montanha-Russa e Loop no ENEM
Um carrinho de montanha-russa de massa \( m = 200\text{ kg} \) parte do repouso do topo de uma colina a uma altura \( H = 20\text{ metros} \) do solo. Desprezando o atrito com os trilhos e a resistência do ar e adotando \( g = 10\text{ m/s}^2 \), determine:
a) A velocidade do carrinho na base da descida (\( h = 0 \)).
b) A velocidade do carrinho ao passar pelo topo de um loop circular de raio \( R = 5\text{ metros} \) (altura do topo = \( 2R = 10\text{ m} \)).
- **Passo 1 (Velocidade na Base via Conservação Mecânica):**
  Como não há atrito, a energia mecânica se conserva:
  \[ E_{m,\text{topo}} = E_{m,\text{base}} \implies m g H + 0 = 0 + \frac{m v_{\text{base}}^2}{2} \]
  Cancelando a massa \( m \) de ambos os lados:
  \[ g H = \frac{v_{\text{base}}^2}{2} \implies v_{\text{base}} = \sqrt{2 g H} = \sqrt{2 \times 10 \times 20} = \sqrt{400} = 20\text{ m/s} \]
  (A velocidade independe da massa do carrinho e equivale a \( 72\text{ km/h} \)).
- **Passo 2 (Velocidade no Topo do Loop a 10 metros):**
  \[ E_{m,\text{topo}} = E_{m,\text{loop}} \implies m g H = m g (2R) + \frac{m v_{\text{loop}}^2}{2} \]
  \[ g H = 2gR + \frac{v_{\text{loop}}^2}{2} \implies 10 \times 20 = 10 \times 10 + \frac{v_{\text{loop}}^2}{2} \]
  \[ 200 = 100 + \frac{v_{\text{loop}}^2}{2} \implies \frac{v_{\text{loop}}^2}{2} = 100 \implies v_{\text{loop}}^2 = 200 \implies v_{\text{loop}} = \sqrt{200} \approx 14{,}14\text{ m/s} \]

## 7. Aplicações Práticas e Modelagem
- **Usinas Hidrelétricas:** A energia potencial gravitacional da água represada em grande altitude é convertida em energia cinética de escoamento nos dutos forçados, que gira as turbinas (energia mecânica rotacional), sendo finalmente convertida em energia elétrica nos geradores eletromagnéticos.
- **Freios Regenerativos em Carros Elétricos:** Durante a frenagem, os motores elétricos operam como geradores, convertendo a energia cinética do veículo em energia química armazenada na bateria em vez de desperdiçá-la como calor de atrito nas pastilhas.

## 8. Exercícios de Fixação
1. Por que a Força Normal e a Força Centrípeta não realizam trabalho mecânico sobre um corpo em movimento?
2. Um corpo de 4 kg move-se a 10 m/s quando uma força constante atua sobre ele até acelerá-lo a 20 m/s. Qual foi o trabalho resultante realizado sobre o corpo?
3. Um motor consome 2.000 W de potência elétrica total e realiza 1.500 W de potência mecânica útil. Qual é o seu rendimento percentual?

## 9. Correção Comentada dos Exercícios
1. Porque ambas as forças atuam sempre em direção rigorosamente perpendicular (\( \theta = 90^\circ \)) ao vetor deslocamento instantâneo. Como \( \cos 90^\circ = 0 \), o trabalho realizado é nulo (\( W = F \cdot d \cdot \cos 90^\circ = 0 \)).
2. Pelo Teorema da Energia Cinética: \( W_{\text{res}} = \Delta E_c = \frac{m}{2}(v^2 - v_0^2) = \frac{4}{2}(20^2 - 10^2) = 2 \times (400 - 100) = 2 \times 300 = 600\text{ Joules} \).
3. Rendimento: \( \eta = P_{\text{útil}} / P_{\text{total}} = 1.500 / 2.000 = 0{,}75 = 75\% \).

## 10. Desafio de Domínio
Em uma montanha-russa vertical com loop circular perfeito de raio \( R \), determine qual deve ser a velocidade mínima que o carrinho precisa ter no topo mais alto do loop para conseguir completar a volta circular sem perder o contato com os trilhos (condição de força normal nula no topo).

## 11. Resumo e Pontos-Chave
- Trabalho: \( W = F \cdot d \cdot \cos\theta \) (Motor se \( \theta < 90^\circ \); Resistente se \( \theta > 90^\circ \); Nulo se \( \theta = 90^\circ \)).
- Teorema da Energia Cinética: \( W_{\text{res}} = \Delta E_c = \frac{1}{2} m v^2 - \frac{1}{2} m v_0^2 \).
- Energia Potencial Gravitacional: \( E_{pg} = m g h \). Energia Elástica: \( E_{pel} = \frac{1}{2} k x^2 \).
- Conservação da Energia Mecânica: Sem atrito, \( E_c + E_p = \text{constante} \).
- Com atrito: \( W_{\text{atrito}} = \Delta E_m < 0 \) (energia mecânica dissipada em calor).
- Potência: \( P = W / \Delta t = F \cdot v \); Rendimento: \( \eta = P_{\text{útil}} / P_{\text{total}} \).

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Mecânica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- SEARS, F.; ZEMANSKY, M.; YOUNG, H. D.; FREEDMAN, R. A. *Física I: Mecânica*. 14ª ed. São Paulo: Pearson, 2016.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema da Energia Cinética (TEC)",
                syntax = """W_{\\text{res}} = \\Delta E_c = \\frac{1}{2} m v^2 - \\frac{1}{2} m v_0^2""",
                description = "O trabalho total realizado pela soma de todas as forças equivale à variação de energia cinética da partícula.",
                parametersInfo = "Válido para trajetórias retilíneas ou curvilíneas e forças conservativas ou dissipativas."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Conservação da Energia Mecânica",
                syntax = """E_{m1} = E_{m2} \\implies \\frac{1}{2} m v_1^2 + m g h_1 + \\frac{1}{2} k x_1^2 = \\frac{1}{2} m v_2^2 + m g h_2 + \\frac{1}{2} k x_2^2""",
                description = "Em sistemas conservativos isolados de atrito, a energia mecânica total permanece invariante.",
                parametersInfo = "A energia cinética converte-se continuamente em potencial gravitacional ou elástica e vice-versa."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Velocidade de Queda Livre por Conservação de Energia",
                codeOrFormula = """m g H = \\frac{m v^2}{2} \\implies v = \\sqrt{2 g H} = \\sqrt{2 \\times 10 \\times 20} = 20\\text{ m/s}""",
                explanation = "Determinação da velocidade na base de uma descida sem atrito de 20 metros de altura."
            )
        ),
        goldTip = "Macete clássico do ENEM: Forças perpendiculares ao movimento (como a Força Normal em plano horizontal e a Força Centrípeta) NUNCA realizam trabalho (trabalho é rigorosamente zero)!"
    )

    // =========================================================================
    // AULA fis_m1_a7: Impulso, Quantidade de Movimento e Colisões
    // =========================================================================
    private val bundleFisM1A7 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como podemos explicar o recuo violento de um canhão ao disparar uma bala de artilharia, a propulsão dos foguetes espaciais no vácuo estéril do espaço interestelar, ou a proteção vital oferecida pela lataria deformável e airbags em acidentes automobilísticos? Todas essas situações são regidas pela grandeza vetorial que Isaac Newton originalmente chamou de "quantidade de matéria e movimento": o **Momento Linear (Quantidade de Movimento)** e o **Impulso de uma Força**. No ENEM, a conservação do momento linear em colisões mecânicas e a relação inversa entre força de impacto e tempo de colisão são temas de altíssima relevância social e tecnológica.

## 2. Objetivos de Aprendizagem
- Definir a **Quantidade de Movimento** (Momento Linear): \( \vec{Q} = m \cdot \vec{v} \).
- Definir o **Impulso de uma Força**: \( \vec{I} = \vec{F} \cdot \Delta t \) e calcular o impulso pela área do gráfico \( F \times t \).
- Compreender e aplicar o **Teorema do Impulso**: \( \vec{I}_{\text{res}} = \Delta \vec{Q} \).
- Explicar por que aumentar a duração do impacto (\( \Delta t \)) diminui drasticamente a força média de destruição (\( F_{\text{méd}} = \Delta Q / \Delta t \)).
- Demonstrar o Princípio da **Conservação da Quantidade de Movimento** em sistemas mecanicamente isolados de forças externas: \( \vec{Q}_{\text{antes}} = \vec{Q}_{\text{depois}} \).
- Classificar as Colisões Mecânicas quanto ao coeficiente de restituição (\( e \)): Perfeitamente Elástica, Parcialmente Elástica e Perfeitamente Inelástica (onde os corpos saem grudados com perda máxima de energia cinética).

## 3. Pré-requisitos
- Segunda e Terceira Leis de Newton (Aula fis_m1_a4).
- Conceito de energia cinética e conservação de energia (Aula fis_m1_a6).

## 4. Explicação Teórica Aprofundada

### 1. Quantidade de Movimento e Impulso
- **Quantidade de Movimento (\( \vec{Q} \)):** É a grandeza vetorial que expressa a inércia em movimento de um corpo. Possui a **mesma direção e o mesmo sentido do vetor velocidade**:
  \[ \vec{Q} = m \cdot \vec{v} \]
  - Unidade SI: **\( \text{kg} \cdot \text{m/s} \)** ou **\( \text{N} \cdot \text{s} \)**.
- **Impulso de uma Força Constante (\( \vec{I} \)):** Mede a ação de uma força aplicada durante um intervalo de tempo:
  \[ \vec{I} = \vec{F} \cdot \Delta t \]
  - Em forças variáveis: \( \vec{I} \) é numericamente igual à área delimitada sob o gráfico \( F \times t \).
- **O Teorema do Impulso:** Newton expressou originalmente sua Segunda Lei como a taxa temporal de variação do momento linear:
  \[ \vec{F}_{\text{res}} = \frac{\Delta \vec{Q}}{\Delta t} \implies \vec{I}_{\text{res}} = \Delta \vec{Q} = \vec{Q}_{\text{final}} - \vec{Q}_{\text{inicial}} \]

### 2. A Física da Segurança no Trânsito: Por que Amortecer?
Pelo Teorema do Impulso:
\[ F_{\text{média}} = \frac{\Delta Q}{\Delta t} \]
- Em uma colisão de um veículo contra um muro a 72 km/h, a variação de momento \( \Delta Q \) até a parada é fixa e inevitável.
- **Se a estrutura for rígida indeformável:** O tempo de colisão é minúsculo (\( \Delta t \to 0{,}01\text{ s} \)). A força média de impacto \( F_{\text{média}} \) dispara para patamares letais de dezenas de toneladas, esmagando os órgãos internos dos ocupantes.
- **Com Zonas de Deformação Programada e Airbag:** A frente do carro amassa progressivamente e o airbag infla, estendendo o tempo de desaceleração em 10 vezes (\( \Delta t \to 0{,}10\text{ s} \)). A força média de impacto que atinge o tórax cai para um décimo, salvando vidas!

### 3. Conservação da Quantidade de Movimento
Em um **sistema isolado de forças externas** (ou seja, onde a resultante das forças externas é nula ou desprezível durante o curto instante do choque), as forças que atuam são puramente forças internas de interação mútua (ação e reação):
\[ \vec{Q}_{\text{total, antes}} = \vec{Q}_{\text{total, depois}} \]
- **Exemplo do Recuo da Arma:** Um atirador segura um rifle em repouso (\( Q_{\text{antes}} = 0 \)). Ao disparar, a bala sai para a frente com momento \( m_{\text{bala}} \cdot v_{\text{bala}} \). Pela conservação:
  \[ 0 = m_{\text{bala}} \cdot v_{\text{bala}} + M_{\text{arma}} \cdot v_{\text{recuo}} \implies v_{\text{recuo}} = -\frac{m_{\text{bala}}}{M_{\text{arma}}} \cdot v_{\text{bala}} \]
  O rifle recua para trás em sentido oposto ao do projétil.
- **Propulsão de Foguetes:** O foguete ejeta gases de combustão em altíssima velocidade para trás; em reação inercial de momento, o corpo do foguete é acelerado para a frente.

### 4. Tipos de Choques Mecânicos
O Coeficiente de Restituição (\( e \)) mede a elasticidade da colisão:
\[ e = \frac{v_{\text{afastamento}}}{v_{\text{aproximação}}} \]
1. **Colisão Perfeitamente Elástica (\( e = 1 \)):**
   - A quantidade de movimento **se conserva** (\( Q_{\text{antes}} = Q_{\text{depois}} \)).
   - A energia cinética **se conserva integralmente** (\( E_{c,\text{antes}} = E_{c,\text{depois}} \)).
   - Exemplo aproximado: Bolas de bilhar de marfim ou moléculas de gás.
2. **Colisão Parcialmente Elástica (\( 0 < e < 1 \)):**
   - A quantidade de movimento se conserva.
   - Parte da energia cinética dissipa-se como calor, ruído e deformação residual.
3. **Colisão Perfeitamente Inelástica (\( e = 0 \)):**
   - Os corpos colidem e **permanecem unidos grudados após o choque**, deslocando-se com a **mesma velocidade comum final** (\( v_A = v_B = v' \)).
   - A quantidade de movimento **se conserva rigorosamente**:
     \[ m_A v_A + m_B v_B = (m_A + m_B) v' \]
   - É o choque que apresenta a **máxima dissipação de energia cinética** possível!

## 5. Definições e Conceitos
- **Sistema Mecanicamente Isolado:** Sistema de corpos sobre o qual a resultante das forças externas ao conjunto é nula.
- **Força Impulsiva:** Força de grande intensidade que atua durante um intervalo de tempo extremamente breve (ex.: taco batendo em bola de beisebol).
- **Coeficiente de Restituição:** Razão entre a velocidade relativa de afastamento e a velocidade relativa de aproximação dos corpos colidentes.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Colisão Inelástica de Veículos no Cruzamento no ENEM
Um caminhão de massa \( M = 3.000\text{ kg} \) trafega a \( 20\text{ m/s} \) em uma avenida retilínea quando atinge a traseira de um automóvel de massa \( m = 1.000\text{ kg} \) que estava parado no semáforo vermelho. Devido ao impacto, os engates se travam e ambos os veículos seguem juntos imediatamente após a colisão.
a) Qual é a velocidade comum do conjunto logo após o engavetamento?
b) Quanta energia cinética foi dissipada durante a deformação dos veículos?
- **Passo 1 (Conservação da Quantidade de Movimento):**
  Como as forças de frenagem no chão durante a batida instantânea são desprezíveis perto das forças impulsivas internas:
  \[ Q_{\text{antes}} = Q_{\text{depois}} \implies M \cdot v_C + m \cdot v_A = (M + m) \cdot v' \]
  \[ 3.000 \times 20 + 1.000 \times 0 = (3.000 + 1.000) \cdot v' \]
  \[ 60.000 = 4.000 \cdot v' \implies v' = \frac{60.000}{4.000} = 15\text{ m/s} \]
  (O conjunto desloca-se a 15 m/s, ou 54 km/h).
- **Passo 2 (Energia Cinética Antes):**
  \[ E_{c,\text{antes}} = \frac{1}{2} M v_C^2 = \frac{1}{2} (3.000) (20)^2 = 1.500 \times 400 = 600.000\text{ Joules} \]
- **Passo 3 (Energia Cinética Depois):**
  \[ E_{c,\text{depois}} = \frac{1}{2} (M + m) (v')^2 = \frac{1}{2} (4.000) (15)^2 = 2.000 \times 225 = 450.000\text{ Joules} \]
- **Passo 4 (Energia Dissipada no Impacto):**
  \[ E_{\text{dissipada}} = 600.000 - 450.000 = 150.000\text{ Joules (ou 150 kJ)} \]
  (Essa enorme energia dissipada foi convertida em som estrondoso, calor térmico e deformação plástica da lataria).

## 7. Aplicações Práticas e Modelagem
- **Pêndulo Balístico:** Dispositivo clássico usado na perícia criminal para medir a velocidade de projéteis de armas de fogo a partir da altura máxima atingida por um bloco de madeira no qual o projétil se incrusta.
- **Airbags Automotivos:** Fabricados com tecido de náilon e válvulas de escape de gás que controlam a desaceleração da cabeça do motorista, prolongando o tempo de parada (\( \Delta t \)) e evitando traumatismo craniano severo.

## 8. Exercícios de Fixação
1. Qual a quantidade de movimento de um caminhão de 5 toneladas que se move a 72 km/h?
2. Em qualquer colisão mecânica real no vácuo, a energia cinética sempre se conserva? E a quantidade de movimento?
3. Se dois corpos colidem e saem grudados unidos após o choque, como se classifica essa colisão?

## 9. Correção Comentada dos Exercícios
1. Massa \( m = 5.000\text{ kg} \); velocidade \( v = 72 / 3{,}6 = 20\text{ m/s} \). Quantidade de movimento: \( Q = m \cdot v = 5.000 \times 20 = 100.000\text{ kg}\cdot\text{m/s} \).
2. Não! A energia cinética só se conserva em colisões perfeitamente elásticas; em colisões reais, parte da energia sempre se degrada em calor e deformação. Contudo, em um sistema isolado, a **Quantidade de Movimento SEMPRE se conserva integralmente em qualquer tipo de colisão**!
3. Trata-se de uma **Colisão Perfeitamente Inelástica** (coeficiente de restituição \( e = 0 \)), caracterizada pela velocidade comum final e máxima perda relativa de energia cinética.

## 10. Desafio de Domínio
Dois patinadores em uma pista de gelo perfeitamente polida estão inicialmente em repouso abraçados. O patinador A tem massa de 80 kg e o patinador B tem massa de 50 kg. Eles se empurram mutuamente e se separam. Se o patinador B adquire velocidade de 4 m/s para a direita, calcule a velocidade e o sentido do movimento adquirido pelo patinador A.

## 11. Resumo e Pontos-Chave
- Quantidade de movimento: \( \vec{Q} = m \cdot \vec{v} \) (grandeza vetorial).
- Teorema do Impulso: \( \vec{I}_{\text{res}} = \vec{F} \cdot \Delta t = \Delta \vec{Q} \).
- Segurança viária: Aumentar \( \Delta t \) reduz a força média destrutiva (\( F = \Delta Q / \Delta t \)).
- Em sistemas isolados: \( \vec{Q}_{\text{antes}} = \vec{Q}_{\text{depois}} \) (conserva-se SEMPRE, em qualquer choque!).
- Colisão Elástica: Conserva \( Q \) e conserva \( E_c \).
- Colisão Inelástica: Conserva \( Q \), mas dissipa o máximo de \( E_c \) (corpos saem grudados com mesma velocidade final).

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Mecânica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- SEARS, F.; ZEMANSKY, M.; YOUNG, H. D.; FREEDMAN, R. A. *Física I: Mecânica*. 14ª ed. São Paulo: Pearson, 2016.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema do Impulso",
                syntax = """\\vec{I} = \\vec{F}_{\\text{res}} \\cdot \\Delta t = \\Delta \\vec{Q} = m \\vec{v} - m \\vec{v}_0""",
                description = "O impulso da força resultante equivale à variação temporal do vetor quantidade de movimento.",
                parametersInfo = "Se o tempo de contato Δt aumenta, a força de impacto F diminui na mesma proporção."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Colisão Perfeitamente Inelástica",
                syntax = """m_A \\vec{v}_A + m_B \\vec{v}_B = (m_A + m_B) \\vec{v}'""",
                description = "Os corpos acoplam-se e movem-se com uma velocidade compartilhada única após a colisão.",
                parametersInfo = "Apresenta a máxima taxa percentual de dissipação de energia cinética em calor e deformação."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Engavetamento Inelástico de Veículos",
                codeOrFormula = """M v_C + m v_A = (M + m)v' \\implies 3000(20) + 0 = 4000 v' \\implies v' = 15\\text{ m/s}""",
                explanation = "Conservação da quantidade de movimento aplicada a dois veículos que colidem e continuam grudados."
            )
        ),
        goldTip = "Regra de ouro das colisões no ENEM: A quantidade de movimento se conserva em QUALQUER colisão (elástica ou inelástica). Já a energia cinética só se conserva se a colisão for perfeitamente ELÁSTICA!"
    )

    // =========================================================================
    // AULA fis_m1_a8: Estática do Ponto Material e Corpos Extensos
    // =========================================================================
    private val bundleFisM1A8 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como uma ponte pênsil estaiada se sustenta sem tombar sob o peso de milhares de caminhões em movimento? Como uma escada apoiada em uma parede lisa permanece equilibrada sem escorregar sob os pés de um pintor, ou por que é muito mais fácil soltar a porca emperrada de uma roda usando uma chave de boca longa em vez de uma curta? A resposta a essas perguntas reside na **Estática**, a área da Mecânica que investiga as condições necessárias e suficientes para que corpos permaneçam em perfeito equilíbrio de translação e de rotação.

## 2. Objetivos de Aprendizagem
- Diferenciar Equilíbrio Estático (repouso) de Equilíbrio Dinâmico (MRU).
- Aplicar a **1ª Condição de Equilíbrio (Equilíbrio de Translação)**: \( \sum \vec{F} = \vec{0} \) (anulação das componentes em \( x \) e \( y \)).
- Definir **Momento de uma Força (Torque)**: \( M = \pm F \cdot d \cdot \sin\theta \), compreendendo o papel do braço de alavanca e a convenção de sinais (horário e anti-horário).
- Aplicar a **2ª Condição de Equilíbrio (Equilíbrio de Rotação)**: \( \sum \vec{M}_O = \vec{0} \) em relação a qualquer ponto de apoio.
- Localizar o **Centro de Gravidade (Centro de Massa)** e analisar as condições de tombamento e estabilidade de corpos apoiados.
- Resolver problemas clássicos do ENEM: vigas biapoiadas, escadas apoiadas e pontes estaiadas.

## 3. Pré-requisitos
- Decomposição vetorial de forças (Aula fis_m1_a2).
- Leis de Newton e forças mecânicas (Peso, Normal, Tração, Atrito) (Aula fis_m1_a4).

## 4. Explicação Teórica Aprofundada

### 1. Equilíbrio do Ponto Material (Translação)
Para uma partícula puntual (cujas dimensões geométricas são desprezíveis), não existe possibilidade física de rotação sobre si mesma. Portanto, a única exigência para o equilíbrio é que a força resultante seja nula:
\[ \vec{F}_{\text{res}} = \sum \vec{F} = \vec{0} \]
No plano cartesiano, isso se desdobra em duas equações escalares independentes:
\[ \sum F_x = 0 \quad \text{e} \quad \sum F_y = 0 \]
- *Método do Polígono Fechado:* Quando três forças mantêm um ponto em equilíbrio, seus vetores somados formam geometricamente um **triângulo fechado**, permitindo aplicar a Lei dos Senos (Teorema de Lamy) ou Teorema de Pitágoras.

### 2. Equilíbrio de Corpos Extensos: O Conceito de Torque (Momento)
Para corpos com dimensões reais (uma viga, uma escada, uma porta), não basta anular as forças: o corpo pode não sair do lugar, mas começar a girar descontroladamente!
- **Momento de uma Força ou Torque (\( \vec{M} \)):** É a grandeza física vetorial que mede a eficácia ou capacidade de uma força fazer um corpo extenso **girar** ao redor de um eixo ou ponto pivô (\( O \)):
  \[ M_O = F \cdot b = F \cdot d \cdot \sin\theta \]
  - \( d \): distância do ponto de aplicação da força até o polo de giro \( O \).
  - \( \theta \): ângulo entre o vetor força e a barra.
  - \( b = d \cdot \sin\theta \): **Braço da Força** (a menor distância perpendicular do polo à linha de ação da força).
  - No SI: medido em **Newton-metro (\( \text{N} \cdot \text{m} \))**.
- **Convenção de Sinais:**
  - Sentido Anti-horário: Momento Positivo (\( + \)).
  - Sentido Horário: Momento Negativo (\( - \)).
- **Forças que Passam pelo Eixo:** Se a linha de ação da força passar exatamente sobre o polo de giro (\( d = 0 \)), seu braço é nulo e seu **torque é estritamente zero** (empurrar uma porta na direção da dobradiça nunca consegue fazê-la girar!).

### 3. As Duas Condições de Equilíbrio do Corpo Rígido
Para que uma estrutura extensa esteja em repouso estático absoluto, duas condições devem ser satisfeitas simultaneamente:
1. **Não Transladar (Equilíbrio de Translação):**
   \[ \sum \vec{F}_{\text{externas}} = \vec{0} \iff \sum F_x = 0 \quad \land \quad \sum F_y = 0 \]
2. **Não Rotacionar (Equilíbrio de Rotação):**
   A soma algébrica de todos os momentos de força em relação a qualquer polo \( O \) escolhido deve ser nula:
   \[ \sum M_O = 0 \]

### 4. Centro de Gravidade e Estabilidade
- **Centro de Gravidade (CG):** Ponto geométrico onde podemos considerar concentrada toda a massa e onde atua o vetor peso resultante do corpo.
- **Condição de Tombamento:** Um corpo apoiado sobre uma superfície plana só permanece em equilíbrio estável se a **linha de ação vertical que passa pelo seu Centro de Gravidade cair dentro da sua Base de Sustentação**. Se o corpo for inclinado a ponto de a projeção vertical do CG cair para fora da base, o torque do próprio peso atuará tombando o objeto.

## 5. Definições e Conceitos
- **Binário ou Casal de Forças:** Par de forças de mesma intensidade, direções paralelas e sentidos opostos aplicadas em pontos distintos do corpo. Um binário produz rotação pura sem translação (\( F_{\text{res}} = 0 \), mas \( M_{\text{res}} \neq 0 \)), como ao girar o volante do carro com as duas mãos.
- **Polo de Redução Arbitrário:** Em problemas estáticos em equilíbrio, o ponto de cálculo do momento pode ser escolhido em qualquer local conveniente da barra (a melhor estratégia prática é fixar o polo no ponto onde atua a força desconhecida mais difícil de calcular, pois seu braço será zero e ela sairá da equação!).

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Equilíbrio de Prancha Homogênea Biapoiada no ENEM
Uma prancha homogênea de madeira de comprimento \( L = 4\text{ metros} \) e massa \( M = 60\text{ kg} \) repousa na horizontal apoiada sobre dois cavaletes: o apoio A na extremidade esquerda (\( x = 0 \)) e o apoio B localizado a \( 3\text{ metros} \) de A (\( x = 3\text{ m} \)). Uma pessoa de massa \( m = 80\text{ kg} \) caminha sobre a prancha. Adotando \( g = 10\text{ m/s}^2 \):
a) Quando a pessoa está parada exatamente no meio da prancha (\( x = 2\text{ m} \)), quais são as forças normais exercidas pelos apoios A e B?
b) Até que distância além do apoio B a pessoa pode caminhar para a direita antes que a prancha comece a tombar?
- **Passo 1 (Cálculo dos Pesos):**
  - Peso da prancha: \( P_{\text{prancha}} = 60 \times 10 = 600\text{ N} \) (atua no centro de gravidade, no meio da prancha: \( x = 2\text{ m} \)).
  - Peso da pessoa: \( P_{\text{pessoa}} = 80 \times 10 = 800\text{ N} \) (também em \( x = 2\text{ m} \)).
  - Carga total vertical para baixo: \( 600 + 800 = 1.400\text{ N} \).
- **Passo 2 (Momento em Relação ao Apoio A no Caso A):**
  Escolhendo o apoio A (\( x = 0 \)) como polo de giro:
  \[ \sum M_A = 0 \implies -(P_{\text{prancha}} \cdot 2) - (P_{\text{pessoa}} \cdot 2) + (N_B \cdot 3) = 0 \]
  \[ -(600 \times 2) - (800 \times 2) + 3 N_B = 0 \implies -1.200 - 1.600 + 3 N_B = 0 \]
  \[ 3 N_B = 2.800 \implies N_B = \frac{2.800}{3} \approx 933{,}3\text{ Newtons} \]
- **Passo 3 (Força no Apoio A via Equilíbrio Vertical):**
  \[ N_A + N_B = P_{\text{total}} \implies N_A = 1.400 - 933{,}3 = 466{,}7\text{ Newtons} \]
- **Passo 4 (Caso B - Iminência de Tombamento):**
  Quando a prancha está prestes a tombar girando sobre o apoio B, a extremidade esquerda descola do cavalete A, de modo que a **força no apoio A se anula**: \( N_A = 0 \)!
  Fixando o polo de rotação no próprio apoio B (\( x = 3\text{ m} \)):
  - O peso da prancha (em \( x = 2\text{ m} \)) dista \( d_1 = 3 - 2 = 1\text{ metro} \) à esquerda de B (tende a girar no sentido anti-horário: positivo).
  - A pessoa está a uma distância \( x_p \) à direita de B (tende a tombar a prancha no sentido horário: negativo).
  \[ \sum M_B = 0 \implies +(P_{\text{prancha}} \cdot 1) - (P_{\text{pessoa}} \cdot x_p) = 0 \]
  \[ 600 \times 1 = 800 \cdot x_p \implies x_p = \frac{600}{800} = 0{,}75\text{ metros} \]
  A pessoa pode caminhar com segurança até no máximo \( 75\text{ cm} \) além do apoio B. Se der mais um passo, a prancha tomba!

## 7. Aplicações Práticas e Modelagem
- **Gruas e Guindastes de Construção Civil:** Possuem contrapesos massivos de concreto posicionados no braço oposto da lança para que a soma dos momentos seja nula, evitando o tombamento fatal da torre ao içar cargas pesadas.
- **Arquitetura de Pontes Estaiadas:** Cabos de aço tracionados transmitem forças em ângulos precisos para equilibrar o momento fletor gerado pelo fluxo de automóveis sobre o vão livre central.

## 8. Exercícios de Fixação
1. Quais são as duas condições necessárias para que um corpo rígido extenso esteja em equilíbrio estático?
2. Por que a maçaneta de uma porta é sempre instalada na extremidade oposta às dobradiças e nunca perto do meio?
3. O que acontece com a força de contato no apoio oposto quando uma estrutura entra na iminência de tombar sobre um pivô?

## 9. Correção Comentada dos Exercícios
1. A resultante de todas as forças deve ser nula (\( \sum \vec{F} = 0 \), equilíbrio de translação) e a soma de todos os momentos de força em relação a qualquer ponto deve ser nula (\( \sum \vec{M} = 0 \), equilíbrio de rotação).
2. Para maximizar a distância \( d \) até o eixo de rotação (as dobradiças). Como o torque é proporcional ao braço da força (\( M = F \cdot d \)), quanto maior a distância, menor é a força muscular que a pessoa precisa fazer para abrir ou fechar a porta.
3. A força normal de contato no apoio oposto reduz-se a zero (\( N = 0 \)), pois a estrutura começa a se descolar fisicamente da superfície naquele ponto.

## 10. Desafio de Domínio
Uma escada homogênea de 10 kg e 5 metros de comprimento apoia-se em uma parede vertical perfeitamente lisa (sem atrito) e em um piso horizontal rugoso com atrito estático \( \mu_e = 0{,}5 \). Determine o menor ângulo de inclinação \( \theta \) com o solo para o qual a escada permanece em repouso sem deslizar.

## 11. Resumo e Pontos-Chave
- Ponto Material: Apenas \( \sum \vec{F} = \vec{0} \).
- Corpo Extenso: Exige \( \sum \vec{F} = \vec{0} \) E \( \sum \vec{M}_O = 0 \).
- Momento (Torque): \( M = F \cdot d \cdot \sin\theta \) (mede a tendência de rotação).
- Força cuja linha de ação passa pelo pivô tem momento nulo (\( M = 0 \)).
- Iminência de tombar: O apoio oposto descola e sua força de contato anula-se (\( N = 0 \)).
- Estabilidade: A linha vertical do Centro de Gravidade deve cair dentro da base de sustentação.

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Mecânica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- HIBBELER, R. C. *Estática: Mecânica para Engenharia*. 14ª ed. São Paulo: Pearson, 2017.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Momento de uma Força (Torque)",
                syntax = """M_O = \\pm F \\cdot d \\cdot \\sin\\theta""",
                description = "Mede a capacidade de uma força gerar rotação ao redor de um eixo ou polo O.",
                parametersInfo = "F é força (N), d é a distância até o polo (m) e θ é o ângulo de aplicação."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Duas Condições de Equilíbrio Estático",
                syntax = """\\sum \\vec{F} = \\vec{0} \\quad (\\text{Não translada}) \\quad \\land \\quad \\sum M_O = 0 \\quad (\\text{Não rotaciona})""",
                description = "Conjunto fundamental de equações para estabilidade absoluta de estruturas e vigas.",
                parametersInfo = "Válido para qualquer referencial inercial e qualquer polo arbitrário escolhido."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Iminência de Tombamento em Prancha",
                codeOrFormula = """\\sum M_B = 0 \\implies P_{\\text{prancha}} \\cdot d_1 = P_{\\text{pessoa}} \\cdot x_p \\implies 600(1) = 800 x_p \\implies x_p = 0{,}75\\text{ m}""",
                explanation = "No limite de tombamento, o apoio oposto descola (N_A = 0) e a soma dos torques no pivô B determina o alcance máximo do pedestre."
            )
        ),
        goldTip = "Estratégia infalível de Estática no ENEM: Coloque o ponto de apoio (polo) em cima da força desconhecida que você NÃO quer calcular. O braço dela vira zero e ela desaparece da equação!"
    )

    // =========================================================================
    // AULA fis_m1_a9: Hidrostática (Pressão, Stevin, Pascal e Arquimedes)
    // =========================================================================
    private val bundleFisM1A9 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como pode um gigantesco navio de aço de mais de cem mil toneladas flutuar serenamente nas águas do oceano enquanto uma minúscula pedrinha afunda imediatamente até o fundo? Por que os mergulhadores sentem dor nos tímpanos ao submergirem em profundidade, ou como um mecânico de automóveis consegue erguer uma caminhonete de duas toneladas no posto pisando levemente em um pedal hidráulico? A resposta a todos esses fenômenos fascinantes é regida pela **Hidrostática**, o estudo dos fluidos em repouso e de suas leis fundamentais formuladas por Stevin, Pascal e Arquimedes.

## 2. Objetivos de Aprendizagem
- Diferenciar com rigor conceitual **Massa Específica** (propriedade da substância pura) de **Densidade** (propriedade do corpo oco ou maciço).
- Definir **Pressão** (\( P = F / A \)) e dominar as unidades de medida (\( 1\text{ atm} = 10^5\text{ Pa} = 760\text{ mmHg} \)).
- Deduzir e aplicar o **Teorema Fundamental da Hidrostática (Lei de Stevin)**: \( P = P_0 + \rho g h \).
- Explicar o funcionamento dos **Vasos Comunicantes** e tubos em 'U' contendo líquidos imiscíveis.
- Compreender e calcular o **Princípio de Pascal** em prensas e freios hidráulicos (\( F_1 / A_1 = F_2 / A_2 \)).
- Dominar o **Princípio de Arquimedes e o Empuxo**: \( E = \rho_{\text{líquido}} \cdot V_{\text{submerso}} \cdot g \) e as três condições de flutuação de corpos.

## 3. Pré-requisitos
- Força Peso, equilíbrio estático e Segunda Lei de Newton (Aulas fis_m1_a4 e fis_m1_a8).
- Unidades de área e volume (\( 1\text{ m}^3 = 1.000\text{ L} = 10^6\text{ cm}^3 \)).

## 4. Explicação Teórica Aprofundada

### 1. Densidade e Pressão
- **Massa Específica (\( \rho \) ou \( \mu \)):** Razão entre a massa de uma substância homogênea e o volume que ela ocupa:
  \[ \rho = \frac{m}{V} \]
  No SI: medido em **\( \text{kg/m}^3 \)** (\( 1\text{ g/cm}^3 = 1.000\text{ kg/m}^3 \)). A massa específica da água pura é \( 1\text{ g/cm}^3 = 1.000\text{ kg/m}^3 \).
- **Pressão (\( P \)):** Razão entre o módulo da força perpendicular exercida e a área de contato:
  \[ P = \frac{F}{A} \]
  No SI: expressa em **Pascal (Pa)** (\( 1\text{ Pa} = 1\text{ N/m}^2 \)).
  - Quanto menor a área, maior a pressão para a mesma força (por isso agulhas e facas afiadas furam e cortam com tanta facilidade).

### 2. Teorema de Stevin (Pressão Hidrostática)
A pressão em um líquido em equilíbrio aumenta linearmente com a profundidade (\( h \)) devido ao peso da coluna de fluido acima:
\[ \Delta P = \rho \cdot g \cdot h \]
- **Pressão Absoluta no Fundo de um Lago ou Mar:**
  \[ P = P_{\text{atmosférica}} + \rho \cdot g \cdot h \]
  Onde \( P_{\text{atm}} \approx 10^5\text{ Pa} = 1\text{ atm} \).
  - *Regra Prática do Mar:* Como a água tem \( \rho \approx 1.000\text{ kg/m}^3 \) e \( g \approx 10\text{ m/s}^2 \), a cada **10 metros de profundidade**, a pressão hidrostática aumenta em exatamente **\( 1\text{ atm} \)** (\( 10^5\text{ Pa} \))! A 30 metros de profundidade, a pressão total é de 4 atm (1 atm do ar + 3 atm da água).
- **Vasos Comunicantes e Tubo em U com Líquidos Imiscíveis:**
  Dois pontos localizados na mesma cota altimétrica horizontal no interior do mesmo líquido homogêneo em repouso suportam exatamente a **mesma pressão**:
  \[ P_A = P_B \implies \rho_1 \cdot h_1 = \rho_2 \cdot h_2 \]
  O líquido menos denso atinge uma altura maior na coluna do tubo.

### 3. Princípio de Pascal (Prensas Hidráulicas)
> *"O acréscimo de pressão exercido em um ponto qualquer de um fluido incompressível em equilíbrio é transmitido integralmente a todos os pontos do fluido e às paredes do recipiente."*
- **Prensa Hidráulica e Elevador de Posto:**
  Dois êmbolos de áreas \( A_1 \) e \( A_2 \) conectados por fluido incompressível:
  \[ \Delta P_1 = \Delta P_2 \implies \frac{F_1}{A_1} = \frac{F_2}{A_2} \iff F_2 = F_1 \cdot \left(\frac{A_2}{A_1}\right) \]
  Se a área do êmbolo 2 for 50 vezes maior que a área do êmbolo 1, a força será **multiplicada por 50**!
  - *Conservação do Trabalho:* O volume de líquido deslocado é conservado (\( V = A_1 \cdot d_1 = A_2 \cdot d_2 \)). Para o êmbolo 2 subir uma altura \( d_2 \), o operador precisa empurrar o êmbolo 1 por uma distância 50 vezes maior.

### 4. Princípio de Arquimedes e o Empuxo
> *"Todo corpo imerso total ou parcialmente em um fluido em repouso sofre a ação de uma força vertical orientada para cima, denominada Empuxo, cuja intensidade é igual ao peso do volume de fluido deslocado pelo corpo."*
\[ E = P_{\text{fluido deslocado}} = m_{\text{fluido}} \cdot g = \rho_{\text{fluido}} \cdot V_{\text{submerso}} \cdot g \]
- **Origem Física do Empuxo:** Decorre da diferença de pressão entre a face inferior (mais profunda, maior pressão) e a face superior do corpo imerso (\( \Delta P = \rho g h \)). A resultante dessas forças de pressão aponta sempre **para cima**.
- **As Três Condições de Flutuação:**
  1. **Se \( \rho_{\text{corpo}} > \rho_{\text{líquido}} \implies P > E \):** O corpo afunda com aceleração orientada para baixo até bater no fundo. Seu peso aparente é: \( P_{\text{aparente}} = P - E \).
  2. **Se \( \rho_{\text{corpo}} = \rho_{\text{líquido}} \implies P = E \):** O corpo fica em equilíbrio indiferente totalmente submerso em qualquer profundidade em que for colocado.
  3. **Se \( \rho_{\text{corpo}} < \rho_{\text{líquido}} \implies P = E \) (em equilíbrio de flutuação):** O corpo flutua na superfície com apenas uma fração de seu volume submersa:
     \[ P = E \implies m_{\text{corpo}} \cdot g = \rho_{\text{líq}} \cdot V_{\text{sub}} \cdot g \implies \rho_{\text{corpo}} \cdot V_{\text{total}} = \rho_{\text{líq}} \cdot V_{\text{sub}} \]
     \[ \frac{V_{\text{submerso}}}{V_{\text{total}}} = \frac{\rho_{\text{corpo}}}{\rho_{\text{líquido}}} \]
     - Exemplo do Iceberg: Como o gelo tem densidade \( \rho_{\text{gelo}} \approx 0{,}92\text{ g/cm}^3 \) e a água do mar \( \rho_{\text{mar}} \approx 1{,}03\text{ g/cm}^3 \), a fração submersa é \( 0{,}92 / 1{,}03 \approx 90\% \)! Apenas 10% do iceberg fica visível acima da água.

## 5. Definições e Conceitos
- **Experiência de Torricelli:** Evidenciou a pressão atmosférica utilizando um tubo de vidro com mercúrio (\( Hg \)), equilibrando uma coluna de mercúrio de exatamente \( 76\text{ cm} \) (\( 760\text{ mmHg} \)) ao nível do mar.
- **Peso Aparente:** Sensação de peso reduzido experimentada por um corpo quando imerso em um líquido, igual à diferença entre seu peso real e o empuxo hidrostático.
- **Densidade do Navio:** Um navio de aço flutua porque ele é **oco**: o volume total engloba gigantescas salas e porões preenchidos com ar, tornando a sua densidade média global muito menor que a densidade da água do mar.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Elevador Hidráulico de Veículos no ENEM
Em uma oficina mecânica, um elevador hidráulico possui dois pistões circulares: o pistão menor possui raio \( r_1 = 10\text{ cm} \) e o pistão maior possui raio \( r_2 = 50\text{ cm} \). Um automóvel de massa \( m = 1.500\text{ kg} \) está apoiado sobre o pistão maior. Adotando \( g = 10\text{ m/s}^2 \):
a) Qual a força que o mecânico precisa aplicar sobre o pistão menor para manter o automóvel em equilíbrio?
b) Se o pistão menor for empurrado para baixo por uma distância de \( 25\text{ cm} \), quantos centímetros o automóvel subirá?
- **Passo 1 (Cálculo das Áreas dos Pistões Circulares):**
  A área de um círculo é proporcional ao quadrado do raio (\( A = \pi r^2 \)):
  \[ \frac{A_2}{A_1} = \frac{\pi r_2^2}{\pi r_1^2} = \left(\frac{r_2}{r_1}\right)^2 = \left(\frac{50}{10}\right)^2 = 5^2 = 25 \]
  (A área do pistão grande é 25 vezes maior que a do pistão pequeno).
- **Passo 2 (Cálculo da Força via Princípio de Pascal):**
  O peso do carro sobre o pistão grande é: \( P = 1.500 \times 10 = 15.000\text{ Newtons} \).
  \[ \frac{F_1}{A_1} = \frac{F_2}{A_2} \implies F_1 = F_2 \cdot \left(\frac{A_1}{A_2}\right) = 15.000 \times \frac{1}{25} = 600\text{ Newtons} \]
  (O mecânico precisa exercer uma força correspondente a apenas 60 kg para equilibrar um veículo de 1,5 tonelada!).
- **Passo 3 (Cálculo do Deslocamento Vertical):**
  Como o óleo é incompressível, os volumes são iguais:
  \[ A_1 \cdot d_1 = A_2 \cdot d_2 \implies d_2 = d_1 \cdot \left(\frac{A_1}{A_2}\right) = 25\text{ cm} \times \frac{1}{25} = 1\text{ centímetro} \]
  (O carro sobe 1 cm para cada 25 cm que o pedal desce).

## 7. Aplicações Práticas e Modelagem
- **Submarinos e Tanques de Lastro:** Para submergir, o submarino abre válvulas enchendo seus tanques de lastro com água do mar, aumentando sua densidade média até \( \rho > \rho_{\text{água}} \). Para emergir à tona, ele injeta ar comprimido nos tanques expulsando a água, reduzindo sua densidade até que o empuxo supere seu peso.
- **Densímetros de Combustível em Postos:** Tubos de vidro graduados que flutuam em altura específica no etanol hidratado. Se houver adição fraudulenta de água (mais densa que o etanol), o densímetro sobe além da marca limite, alertando o consumidor sobre adulteração.

## 8. Exercícios de Fixação
1. Qual é a pressão total absoluta suportada por um mergulhador a 20 metros de profundidade em águas marinhas? (Adote \( P_{\text{atm}} = 10^5\text{ Pa} \), \( g = 10\text{ m/s}^2 \), \( \rho = 1.000\text{ kg/m}^3 \)).
2. Um bloco cúbico de madeira com volume de \( 0{,}02\text{ m}^3 \) flutua na água com 70% de seu volume submerso. Qual é a massa específica da madeira?
3. Se o raio do pistão maior de uma prensa hidráulica for triplicado em relação ao menor, quantas vezes a força aplicada no pistão menor será multiplicada?

## 9. Correção Comentada dos Exercícios
1. Pressão hidrostática: \( \Delta P = \rho g h = 1.000 \times 10 \times 20 = 200.000\text{ Pa} = 2\text{ atm} \). Pressão absoluta: \( P = P_{\text{atm}} + \Delta P = 1\text{ atm} + 2\text{ atm} = 3\text{ atm} = 3 \times 10^5\text{ Pa} \).
2. Na flutuação de equilíbrio: \( \frac{\rho_{\text{madeira}}}{\rho_{\text{água}}} = \frac{V_{\text{submerso}}}{V_{\text{total}}} = 0{,}70 \implies \rho_{\text{madeira}} = 0{,}70 \times 1.000 = 700\text{ kg/m}^3 \) (ou \( 0{,}70\text{ g/cm}^3 \)).
3. Como a área do círculo varia com o quadrado do raio (\( A = \pi r^2 \)), se o raio é multiplicado por 3, a área é multiplicada por \( 3^2 = 9 \). Portanto, a força será multiplicada por **9 vezes**.

## 10. Desafio de Domínio
Um copo cilíndrico contém água pura com um cubo de gelo flutuando em repouso. O nível da água coincide exatamente com a borda do copo. Quando o cubo de gelo derreter completamente, a água irá transbordar, o nível irá baixar ou o nível permanecerá exatamente inalterado na borda? Demonstre rigorosamente através do Princípio de Arquimedes.

## 11. Resumo e Pontos-Chave
- Pressão: \( P = F / A \). No SI: Pascal (\( 1\text{ Pa} = 1\text{ N/m}^2 \); \( 1\text{ atm} \approx 10^5\text{ Pa} = 760\text{ mmHg} \)).
- Teorema de Stevin: \( \Delta P = \rho g h \) (na água, cada 10 metros de profundidade somam mais 1 atm).
- Vasos comunicantes: Pontos no mesmo nível no mesmo líquido têm a mesma pressão.
- Princípio de Pascal: \( F_1 / A_1 = F_2 / A_2 \) (força multiplicada na razão das áreas).
- Princípio de Arquimedes: \( E = \rho_{\text{líquido}} \cdot V_{\text{submerso}} \cdot g \) (peso do líquido deslocado).
- Flutuação: \( \frac{V_{\text{submerso}}}{V_{\text{total}}} = \frac{\rho_{\text{corpo}}}{\rho_{\text{líquido}}} \).

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- SEARS, F.; ZEMANSKY, M.; YOUNG, H. D.; FREEDMAN, R. A. *Física I: Mecânica*. 14ª ed. São Paulo: Pearson, 2016.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema Fundamental de Stevin",
                syntax = """P = P_0 + \\rho \\cdot g \\cdot h""",
                description = "Calcula a pressão absoluta em função da profundidade do fluido.",
                parametersInfo = "P0 é a pressão na superfície (atmosférica), ρ é a massa específica do fluido, g a gravidade e h a profundidade."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Princípio de Arquimedes (Empuxo)",
                syntax = """E = \\rho_{\\text{líquido}} \\cdot V_{\\text{submerso}} \\cdot g""",
                description = "Força vertical ascendente exercida pelo fluido sobre qualquer corpo total ou parcialmente nele imerso.",
                parametersInfo = "O empuxo depende exclusivamente da densidade do fluido e do volume de líquido deslocado, e não da massa do corpo."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Fração Submersa de um Bloco Flutuante",
                codeOrFormula = """\\frac{V_{\\text{submerso}}}{V_{\\text{total}}} = \\frac{\\rho_{\\text{corpo}}}{\\rho_{\\text{líquido}}} = \\frac{700}{1000} = 0{,}70 \\implies 70\\% \\text{ submerso}""",
                explanation = "Determinação direta da porcentagem de volume submerso de um corpo de madeira flutuando em água pura."
            )
        ),
        goldTip = "Pegadinha clássica do ENEM: O empuxo depende da densidade do LÍQUIDO e do volume de líquido deslocado! Colocar chumbo ou plástico de mesmo volume dentro da água gera exatamente o MESMO empuxo!"
    )

    // =========================================================================
    // AULA fis_m1_a10: Gravitação Universal e Leis de Kepler
    // =========================================================================
    private val bundleFisM1A10 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Desde as civilizações mais antigas que contemplavam o balé místico dos astros no firmamento noturno até a moderna era espacial de satélites de GPS, telecomunicações e exploração de exoplanetas, a humanidade buscou decifrar a engrenagem cósmica do universo. Coube ao astrônomo alemão Johannes Kepler enunciar no século XVII as leis geométricas empíricas que governam as órbitas dos planetas, e a Sir Isaac Newton sintetizar a física celeste e a física terrestre em uma única equação magistral: a **Lei da Gravitação Universal**. No ENEM, as Leis de Kepler e o movimento orbital de satélites artificiais geoestacionários constituem um tema elegante de altíssima cobrança conceitual.

## 2. Objetivos de Aprendizagem
- Enunciar e aplicar a **Primeira Lei de Kepler (Lei das Órbitas Elípticas)** e identificar o Sol em um dos focos da elipse.
- Compreender a **Segunda Lei de Kepler (Lei das Áreas)** e explicar por que a velocidade do planeta é máxima no **Periélio** e mínima no **Afélio**.
- Aplicar a **Terceira Lei de Kepler (Lei dos Períodos)**: \( T^2 / R^3 = \text{constante} \), relacionando períodos orbitais e raios médios.
- Formular a **Lei da Gravitação Universal de Newton**: \( F_g = G \frac{M \cdot m}{r^2} \).
- Calcular o campo gravitacional na superfície de planetas (\( g = G \cdot M / R^2 \)) e em altitudes elevadas.
- Determinar a **Velocidade Orbital** de satélites (\( v_{\text{orb}} = \sqrt{G M / r} \)) e caracterizar os **Satélites Geoestacionários**.

## 3. Pré-requisitos
- Movimento Circular Uniforme e Aceleração Centrípeta (Aula fis_m1_a3).
- Dinâmica e Leis de Newton (Aula fis_m1_a4).

## 4. Explicação Teórica Aprofundada

### 1. As Três Leis de Johannes Kepler
Formuladas com base nas minuciosas observações astronômicas de Tycho Brahe:
1. **1ª Lei de Kepler (Lei das Órbitas):**
   > *"As órbitas dos planetas ao redor do Sol são elipses, com o Sol ocupando um dos focos da elipse."*
   - Uma elipse possui dois focos (\( F_1 \) e \( F_2 \)). No outro foco da órbita planetária não há absolutamente nada (é espaço vazio).
   - Como a excentricidade das órbitas da maioria dos planetas do Sistema Solar é muito baixa (a da Terra é apenas \( e \approx 0{,}017 \)), as órbitas são elipses quase circulares.
2. **2ª Lei de Kepler (Lei das Áreas):**
   > *"A reta imaginária que une o centro do Sol ao centro de um planeta (o raio-vetor) varre áreas iguais em intervalos de tempo iguais."*
   \[ \frac{\Delta A}{\Delta t} = \text{constante (Velocidade Areolar)} \]
   - **Consequência Física Revolucionária:** O movimento planetário **NÃO É UNIFORME**!
   - **Periélio:** Ponto da órbita mais próximo do Sol. Como a atração gravitacional é mais intensa e o raio é menor, o planeta move-se com **velocidade máxima**:
     \[ v_{\text{periélio}} = \text{máxima} \]
   - **Afélio:** Ponto da órbita mais afastado do Sol. O planeta move-se com **velocidade mínima**:
     \[ v_{\text{afélio}} = \text{mínima} \]
   - Do afélio para o periélio, o movimento é acelerado; do periélio para o afélio, o movimento é retardado.
3. **3ª Lei de Kepler (Lei dos Períodos ou Harmônica):**
   > *"Os quadrados dos períodos de translação dos planetas ao redor do Sol são diretamente proporcionais aos cubos dos raios médios (semieixo maior) de suas órbitas."*
   \[ \frac{T^2}{R^3} = K = \text{constante} \iff \frac{T_1^2}{R_1^3} = \frac{T_2^2}{R_2^3} \]
   - A constante \( K \) depende exclusivamente da massa do astro central (o Sol).
   - Conclusão: **Planetas mais distantes do Sol possuem períodos de translação muito mais longos** (Mercúrio demora apenas 88 dias terrestres para dar uma volta ao redor do Sol; Netuno demora mais de 164 anos terrestres!).

### 2. A Lei da Gravitação Universal de Newton
Newton demonstrou que a mesma força que faz a maçã cair da árvore é a força que mantém a Lua em órbita ao redor da Terra:
\[ F_g = G \cdot \frac{M \cdot m}{r^2} \]
- Onde \( G = 6{,}67 \times 10^{-11}\text{ N}\cdot\text{m}^2/\text{kg}^2 \) é a **Constante de Gravitação Universal**.
- A força gravitacional é **sempre atrativa**, atua na linha imaginária que une os centros de massa dos dois corpos e forma um par de **Ação e Reação** (a Terra atrai o Sol com a mesma intensidade com que o Sol atrai a Terra).
- **Aceleração da Gravidade em um Astro:**
  Igualando a força peso à força gravitacional (\( P = F_g \)):
  \[ m \cdot g = G \cdot \frac{M \cdot m}{R^2} \implies g = \frac{G \cdot M}{R^2} \]
  - Na Terra: \( g_0 = G M / R^2 \approx 9{,}8\text{ m/s}^2 \).
  - Em uma altitude \( h \) acima da superfície: \( g(h) = \frac{G M}{(R + h)^2} \).

### 3. Velocidade Orbital e Satélites Geoestacionários
Para um satélite de massa \( m \) manter-se em órbita circular estável de raio \( r \) ao redor da Terra (massa \( M \)), a força gravitacional atua como a **Força Centrípeta**:
\[ F_g = F_{cp} \implies G \frac{M \cdot m}{r^2} = \frac{m \cdot v^2}{r} \implies v_{\text{orbital}} = \sqrt{\frac{G \cdot M}{r}} \]
- A velocidade orbital depende apenas da massa do planeta central \( M \) e do raio da órbita \( r \); **independe completamente da massa do satélite**!
- Quanto mais próxima da Terra for a órbita, maior precisa ser a velocidade para não cair (a Estação Espacial Internacional - ISS orbita a cerca de 400 km de altitude a velozes \( 27.600\text{ km/h} \)).
- **Satélites Geoestacionários (Comunicações e Clima):**
  - Ficam parados aparentemente sobre o mesmo ponto do equador da Terra.
  - Para isso, seu período de translação orbital deve ser **exatamente igual ao período de rotação da Terra**: \( T = 24\text{ horas} \).
  - Devem obrigatoriamente estar no **plano equatorial**, girando no mesmo sentido de rotação da Terra (de Oeste para Leste).
  - Pela 3ª Lei de Kepler, isso fixa uma órbita única a uma altitude de aproximadamente **\( 36.000\text{ km} \)** da superfície terrestre.

## 5. Definições e Conceitos
- **Periélio e Afélio:** Pontos de maior aproximação e maior afastamento em relação ao Sol. Em relação à Terra, chamam-se **Perigeu e Apogeu**.
- **Velocidade de Escape:** Menor velocidade com que um corpo deve ser lançado da superfície de um planeta para escapar definitivamente da atração gravitacional sem receber propulsão adicional (\( v_{\text{escape}} = \sqrt{2 G M / R} = \sqrt{2} \cdot v_{\text{orbital}} \approx 11{,}2\text{ km/s} \) na Terra).
- **Imponderabilidade em Órbita:** Os astronautas flutuam na estação espacial não porque a gravidade é zero (a 400 km de altura, \( g \) ainda vale cerca de 90% do valor do solo!), mas porque a nave e os astronautas estão em **eterna queda livre contínua**, sem normal de contato com o chão.

## 6. Exemplos Resolvidos Passo a Passo
### Exemplo 1: Comparação de Períodos Orbitais no ENEM
Um planeta hipotético do Sistema Solar é descoberto orbitando o Sol com um raio médio de órbita igual a 4 vezes o raio médio da órbita da Terra (\( R_P = 4 \cdot R_T \)). Sabendo que o período de translação da Terra ao redor do Sol é de 1 ano terrestre (\( T_T = 1\text{ ano} \)), qual é o período de translação desse novo planeta em anos terrestres?
- **Passo 1 (Aplicação da 3ª Lei de Kepler):**
  A razão \( T^2 / R^3 \) é idêntica para todos os astros que orbitam o mesmo corpo central (o Sol):
  \[ \frac{T_P^2}{R_P^3} = \frac{T_T^2}{R_T^3} \implies \frac{T_P^2}{(4 R_T)^3} = \frac{1^2}{R_T^3} \]
- **Passo 2 (Desenvolvimento Algébrico):**
  \[ \frac{T_P^2}{64 \cdot R_T^3} = \frac{1}{R_T^3} \]
  Cancelando \( R_T^3 \) em ambos os lados:
  \[ T_P^2 = 64 \implies T_P = \sqrt{64} = 8\text{ anos terrestres} \]
  O planeta demora 8 anos terrestres para dar uma volta completa ao redor do Sol.

## 7. Aplicações Práticas e Modelagem
- **Constelação de Satélites GPS:** 24 satélites operando em órbitas médias (\( \approx 20.200\text{ km} \)) com períodos de 12 horas. Ao captar o sinal de relógios atômicos de no mínimo 4 satélites simultâneos, o smartphone calcula latitude, longitude e altitude com precisão milimétrica.
- **Efeito Estilingue Gravitacional (Gravitational Slingshot):** Sondas interplanetárias (como Voyager e New Horizons) usam a atração gravitacional e o momento orbital de planetas gigantes como Júpiter para ganhar velocidade e alterar sua trajetória sem gastar combustível precioso.

## 8. Exercícios de Fixação
1. O que acontece com a velocidade de um cometa quando ele se aproxima do Sol em sua trajetória elíptica?
2. Se a distância entre dois planetas for duplicada, o que acontecerá com a força gravitacional mútua de atração entre eles?
3. Cite duas características obrigatórias que um satélite geoestacionário deve possuir em relação ao planeta Terra.

## 9. Correção Comentada dos Exercícios
1. A velocidade aumenta continuamente até atingir o valor máximo no ponto de maior aproximação (Periélio), em decorrência da 2ª Lei de Kepler (conservação do momento angular e da energia mecânica).
2. Pela Lei da Gravitação Universal, a força varia inversamente com o quadrado da distância (\( F \propto 1/r^2 \)). Se a distância duplica (\( 2r \)), a força fica dividida por \( 2^2 = 4 \) (a força reduz-se a um quarto de seu valor original).
3. Deve possuir período de rotação orbital de exatamente 24 horas (igual ao da Terra), orbitar no plano equatorial e deslocar-se no mesmo sentido de rotação da Terra (de Oeste para Leste).

## 10. Desafio de Domínio
Explique por que um astronauta flutua no interior da Estação Espacial Internacional (ISS) a 400 km de altitude, desmistificando o erro comum de que "a gravidade é zero no espaço".

## 11. Resumo e Pontos-Chave
- 1ª Lei de Kepler: Órbitas elípticas com o Sol em um dos focos.
- 2ª Lei de Kepler: Áreas iguais em tempos iguais. Velocidade máxima no Periélio (perto do Sol) e mínima no Afélio (longe).
- 3ª Lei de Kepler: \( T^2 / R^3 = \text{constante} \) (planetas mais distantes demoram mais para transladar).
- Lei da Gravitação de Newton: \( F_g = G \frac{M m}{r^2} \) (força mútua atrativa proporcional ao inverso do quadrado da distância).
- Gravidade no planeta: \( g = G M / R^2 \).
- Velocidade orbital: \( v_{\text{orb}} = \sqrt{G M / r} \) (independe da massa do satélite).
- Satélite Geoestacionário: Órbita equatorial, \( T = 24\text{ h} \), altitude \( \approx 36.000\text{ km} \).

## 12. Referências Bibliográficas
- HALLIDAY, D.; RESNICK, R.; WALKER, J. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. 10ª ed. Rio de Janeiro: LTC, 2016.
- SEARS, F.; ZEMANSKY, M.; YOUNG, H. D.; FREEDMAN, R. A. *Física I: Mecânica*. 14ª ed. São Paulo: Pearson, 2016.
- OPENSTAX. *University Physics, Volume 1*. Rice University: OpenStax, 2021.
- INEP. *Matriz de Referência para o ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Terceira Lei de Kepler (Lei dos Períodos)",
                syntax = """\\frac{T^2}{R^3} = \\text{constante} \\implies \\frac{T_1^2}{R_1^3} = \\frac{T_2^2}{R_2^3}""",
                description = "O quadrado do período de translação orbital é proporcional ao cubo do semieixo maior da órbita.",
                parametersInfo = "Válido para qualquer sistema de corpos orbitando o mesmo astro atrator central."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Lei da Gravitação Universal de Newton",
                syntax = """F_g = G \\cdot \\frac{M \\cdot m}{r^2}""",
                description = "Força mútua atrativa entre massas puntiformes no espaço.",
                parametersInfo = "G é a constante gravitacional universal, M e m são as massas e r é a distância entre os centros."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cálculo de Período Planetário por Kepler",
                codeOrFormula = """\\frac{T_P^2}{(4R_T)^3} = \\frac{1^2}{R_T^3} \\implies T_P^2 = 64 \\implies T_P = 8\\text{ anos}""",
                explanation = "Determinação do período de translação orbital de um planeta cuja distância média ao Sol é 4 vezes a da Terra."
            )
        ),
        goldTip = "Para o ENEM: Os astronautas flutuam na estação espacial NÃO porque a gravidade acabou (a gravidade lá ainda é 90% da da Terra). Eles flutuam porque estão em permanente queda livre contínua junto com a nave!"
    )
}

