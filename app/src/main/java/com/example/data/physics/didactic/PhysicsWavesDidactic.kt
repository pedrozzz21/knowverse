package com.example.data.physics.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Física ENEM — Módulo 3: Ondulatória (Aulas fis_m4_a1 a fis_m4_a5).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em Halliday & Resnick, Sears & Zemansky, Tipler e Matriz ENEM / INEP.
 */
object PhysicsWavesDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "fis_m4_a1" -> bundleFisM4A1
            "fis_m4_a2" -> bundleFisM4A2
            "fis_m4_a3" -> bundleFisM4A3
            "fis_m4_a4" -> bundleFisM4A4
            "fis_m4_a5" -> bundleFisM4A5
            else -> null
        }
    }

    // =========================================================================
    // AULA fis_m4_a1: Fundamentos da Ondulatória e Equação Fundamental
    // =========================================================================
    private val bundleFisM4A1 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
O universo físico é permeado por vibrações e perturbações que viajam pelo espaço. A **Ondulatória** é o ramo da Física dedicado ao estudo da geração, propagação e interação das ondas. Uma das leis mais fundamentais e imutáveis da ondulatória dita que **uma onda transporta energia e quantidade de movimento, mas NUNCA transporta matéria**. No ENEM, a ondulatória é uma das temáticas com maior número médio de questões anuais, conectando telecomunicações (redes Wi-Fi, 4G/5G, rádio, fibra óptica), medicina diagnóstica (ultrassonografia) e fenômenos atmosféricos sonoros e luminosos.

## 2. Objetivos de Aprendizagem
- Compreender a definição física de onda e o princípio de transporte exclusivo de energia sem transporte de matéria.
- Classificar ondas quanto à sua **natureza física** (mecânicas vs. eletromagnéticas vs. gravitacionais).
- Classificar ondas quanto à **direção de oscilação e propagação** (transversais vs. longitudinais).
- Caracterizar geometricamente uma onda periódica harmônica: cristas, vales, amplitude (\( A \)), comprimento de onda (\( \lambda \)), período (\( T \)) e frequência (\( f \)).
- Deduzir e aplicar com maestria a **Equação Fundamental da Ondulatória**: \( v = \lambda \cdot f \).
- Analisar o espectro eletromagnético completo, correlacionando frequência, comprimento de onda e energia dos fótons (\( E = h \cdot f \)).

## 3. Pré-requisitos
- Movimento Circular Uniforme: relação entre período e frequência (\( f = 1/T \)) (Mecânica, Aula fis_m1_a3).
- Notação científica e operações com potências de 10 (Matemática Básica, Aula mb_l04).

## 4. Explicação Teórica Aprofundada

### 1. Classificação das Ondas
1. **Quanto à Natureza:**
   - **Ondas Mecânicas:** Exigem obrigatoriamente um meio material (sólido, líquido ou gasoso) para se propagar, transmitindo deformações elásticas de molécula a molécula. **Não se propagam no vácuo**. Exemplos: som, ondas em cordas, ondas na superfície da água, ondas sísmicas primárias e secundárias.
   - **Ondas Eletromagnéticas:** Formadas pela oscilação mútua, acoplada e perpendicular de um campo elétrico (\( \vec{E} \)) e um campo magnético (\( \vec{B} \)). **Propagam-se no vácuo** com a velocidade máxima do cosmos (\( c \approx 3 \times 10^8\text{ m/s} \)) e também em certos meios materiais transparentes. Exemplos: ondas de rádio, micro-ondas, radiação infravermelha, luz visível, radiação ultravioleta, raios X e raios gama.
2. **Quanto à Direção de Oscilação:**
   - **Ondas Transversais:** As partículas do meio oscilam perpendicularmente à direção em que a onda se propaga. Exemplo: ondas em cordas oscilantes, e **todas as ondas eletromagnéticas**.
   - **Ondas Longitudinais:** As partículas do meio oscilam na mesma direção em que a onda se propaga (compressões e rarefações sucessivas). Exemplo clássico: **o som em fluidos** (ar e água).

### 2. Elementos de uma Onda Periódica
- **Crista:** Ponto de máxima elongação positiva da onda.
- **Vale:** Ponto de máxima elongação negativa (mais baixo).
- **Amplitude (\( A \)):** Distância medida da linha de equilíbrio até uma crista ou vale. Relaciona-se diretamente com a **energia** transportada pela onda (a energia é proporcional a \( A^2 \)).
- **Comprimento de Onda (\( \lambda \)):** Distância entre duas cristas consecutivas, dois vales consecutivos, ou a distância percorrida pela perturbação durante o tempo de um período completo (\( T \)).
- **Período (\( T \)):** Intervalo de tempo necessário para a realização de um ciclo completo (uma oscilação inteira). Unidade SI: segundos (\( \text{s} \)).
- **Frequência (\( f \)):** Número de oscilações completas executadas por segundo. Unidade SI: Hertz (\( 1\text{ Hz} = 1\text{ s}^{-1} \)):
  \[ f = \frac{1}{T} \]

### 3. A Equação Fundamental da Ondulatória
Sabendo que em um meio homogêneo e isotrópico a perturbação viaja com velocidade constante:
\[ v = \frac{\Delta S}{\Delta t} = \frac{\lambda}{T} \]
Como \( f = \frac{1}{T} \), obtém-se a equação mais ubíqua da física ondulatória:
\[ v = \lambda \cdot f \]

### 4. Postulado Fundamental da Origem e Propagação
Dois princípios regem toda a ondulatória para o ENEM:
1. **A Frequência depende EXCLUSIVAMENTE da Fonte:** Uma vez gerada por uma fonte oscilante, a frequência \( f \) de uma onda permanece **estritamente inalterada**, mesmo que ela mude de meio material (refração) ou reflita.
2. **A Velocidade depende EXCLUSIVAMENTE do Meio de Propagação:** Em uma mesma corda ou no mesmo ar atmosférico, todas as ondas sonoras ou luminosas viajam com a mesma velocidade, independentemente de sua frequência ou intensidade. Assim, se \( v \) é constante no meio:
   \[ \lambda \propto \frac{1}{f} \]
   Ondas de alta frequência possuem comprimentos de onda curtos; ondas de baixa frequência possuem comprimentos de onda longos.

### 5. O Espectro Eletromagnético (Do Rádio aos Raios Gama)
Em ordem crescente de frequência (e decrescente de comprimento de onda):
1. **Ondas de Rádio / TV:** \( \lambda \sim 10^3\text{ m} \) a \( 1\text{ m} \), \( f \sim \text{kHz a MHz} \). Grande capacidade de difração em montanhas e prédios.
2. **Micro-ondas:** \( \lambda \sim 1\text{ m} \) a \( 1\text{ mm} \), \( f \sim \text{GHz} \). Telefonia celular (4G/5G), Wi-Fi, radares aeronáuticos e fornos de micro-ondas (ressonância com moléculas de água).
3. **Infravermelho:** Radiação térmica emitida pelo calor dos corpos. Controles remotos e visão noturna.
4. **Luz Visível:** Estreitíssima faixa sensível à retina humana, de \( 700\text{ nm} \) (vermelho, menor energia) a \( 400\text{ nm} \) (violeta, maior energia).
5. **Ultravioleta (UV-A, UV-B, UV-C):** Radiação ionizante moderada, bronzeamento, síntese de vitamina D, mas causadora de câncer de pele e cataratas.
6. **Raios X:** Alta frequência, alta penetração. Diagnóstico por imagem de ossos e tecidos densos.
7. **Raios Gama (\( \gamma \)):** Emissões nucleares de altíssima energia com poder ionizante destrutivo celular, usados em radioterapia oncológica e esterilização hospitalar.

## 5. Definições e Conceitos
- **Frente de Onda:** Superfície que une todos os pontos do espaço atingidos simultaneamente pela mesma fase de oscilação da perturbação.
- **Raios de Onda:** Linhas orientadas perpendiculares às frentes de onda que indicam a trajetória de propagação da energia.
- **Fóton:** Quanta elementar discreto de energia associado à onda eletromagnética, cuja energia vale \( E = h \cdot f \).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Cálculo de Comprimento de Onda em Rede Wi-Fi
Um roteador de internet residencial de padrão moderno transmite seus dados na frequência de micro-ondas de \( 2{,}4\text{ GHz} \) (\( 2{,}4 \times 10^9\text{ Hz} \)). Sabendo que a velocidade de propagação das ondas eletromagnéticas no ar é de aproximadamente \( 3 \times 10^8\text{ m/s} \), determine o comprimento de onda (\( \lambda \)) dessa radiação.
- **Resolução:**
  1. Identificação dos dados: \( v = 3 \times 10^8\text{ m/s} \), \( f = 2{,}4 \times 10^9\text{ Hz} \).
  2. Aplicação da equação fundamental:
     \[ v = \lambda \cdot f \implies \lambda = \frac{v}{f} \]
  3. Cálculo numérico:
     \[ \lambda = \frac{3 \times 10^8}{2{,}4 \times 10^9} = \frac{3}{24} = \frac{1}{8} = 0{,}125\text{ m} = 12{,}5\text{ cm} \]
  - Resposta: O comprimento de onda do sinal Wi-Fi é de \( 12{,}5\text{ cm} \).

### Exemplo 2: Interpretação Gráfica Espaço-Temporal
Uma onda periódica propaga-se com velocidade de \( 20\text{ m/s} \). Ao analisar o perfil espacial da onda em um instante congelado, verifica-se que a distância horizontal entre a primeira crista e a quarta crista consecutiva é de \( 15\text{ metros} \). Determine o comprimento de onda e a frequência dessa oscilação.
- **Resolução:**
  1. Entre a 1ª crista e a 4ª crista consecutiva há exatamente \( 3 \) comprimentos de onda completos:
     \[ 3 \lambda = 15\text{ m} \implies \lambda = 5\text{ m} \]
  2. Frequência pela equação fundamental:
     \[ f = \frac{v}{\lambda} = \frac{20}{5} = 4\text{ Hz} \]
  - Resposta: O comprimento de onda é \( 5\text{ m} \) e a frequência é \( 4\text{ Hz} \).

## 7. Aplicações Práticas e Modelagem
- **Comunicação por Fibras Ópticas:** Pulsos de luz laser infravermelha trafegam no interior de cabos de sílica por reflexão interna total com perdas mínimas, sustentando a infraestrutura global da internet.
- **Radares de Trânsito:** Ondas eletromagnéticas na faixa de gigahertz são emitidas contra automóveis e captadas de volta para deduzir a velocidade do veículo instantaneamente.
- **Ultrassonografia Médica:** Ondas sonoras de frequências superiores a \( 2\text{ MHz} \) penetram tecidos biológicos e refletem nas fronteiras entre órgãos com impedâncias acústicas distintas, gerando imagens anatômicas sem o uso de radiação ionizante perigosa.

## 8. Exercícios de Fixação
1. Um barco de pesca ancorado em alto-mar sobe e desce em uma crista de onda a cada \( 4\text{ segundos} \). Se a distância horizontal entre duas cristas consecutivas for de \( 12\text{ metros} \), qual é a velocidade de propagação dessas ondas marinhas?
2. Por que os astronautas na superfície lunar não conseguem conversar oralmente sem o uso de intercomunicadores de rádio, mesmo estando a menos de um metro de distância um do outro?
3. Ordene as seguintes radiações eletromagnéticas em ordem decrescente de energia dos seus fótons: Luz Amarela, Raios X, Micro-ondas e Radiação Ultravioleta.

## 9. Correção Comentada dos Exercícios
1. O período é \( T = 4\text{ s} \implies f = \frac{1}{4} = 0{,}25\text{ Hz} \). Comprimento de onda \( \lambda = 12\text{ m} \). Velocidade: \( v = \lambda \cdot f = 12 \cdot 0{,}25 = 3\text{ m/s} \).
2. O som é uma **onda mecânica** e requer obrigatoriamente um meio material para propagar suas compressões e rarefações. A Lua é envolvida por vácuo quase perfeito, inviabilizando a propagação sonora; os comunicadores utilizam ondas de rádio (eletromagnéticas), que viajam perfeitamente pelo vácuo.
3. A energia é dada por \( E = h \cdot f \). Logo, a ordem de energia é idêntica à ordem de frequência decrescente: **Raios X > Radiação Ultravioleta > Luz Amarela > Micro-ondas**.

## 10. Desafio de Domínio
Uma onda propaga-se da corda A para uma corda B mais grossa (maior densidade linear \( \mu \)) emendada à primeira sob tração constante \( F \). Pela fórmula de Taylor (\( v = \sqrt{F/\mu} \)), a velocidade diminui na corda B. Sabendo que a frequência é mantida constante pela fonte geradora, o que acontece com o comprimento de onda \( \lambda \) na corda B?
*Pista:* Utilize a Equação Fundamental \( v = \lambda \cdot f \).

## 11. Resumo e Pontos-Chave
- Ondas transportam energia e momento, mas nunca transportam matéria.
- Mecânicas precisam de meio material (não viajam no vácuo); Eletromagnéticas propagam-se no vácuo (\( c \approx 3 \times 10^8\text{ m/s} \)).
- Transversais (oscilação perpendicular à propagação); Longitudinais (oscilação na mesma direção).
- Equação Fundamental: \( v = \lambda \cdot f \).
- A frequência \( f \) é definida pela fonte geradora e nunca muda.
- A velocidade \( v \) depende apenas das características do meio de propagação.

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 1. 6. ed. LTC, 2009.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Fundamental da Ondulatória",
                syntax = """v = \\lambda \\cdot f""",
                description = "Relação universal conectando a velocidade de propagação de qualquer onda periódica ao seu comprimento de onda e frequência.",
                parametersInfo = """v: \\text{velocidade (m/s)}; \\quad \\lambda: \\text{comprimento de onda (m)}; \\quad f: \\text{frequência (Hz)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Relação Frequência-Período",
                syntax = """f = \\frac{1}{T}""",
                description = "Definição matemática de frequência como o inverso do tempo de um ciclo periódico completo.",
                parametersInfo = """f: \\text{frequência (Hertz)}; \\quad T: \\text{período (segundos)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Fórmula de Taylor para Ondas em Cordas",
                syntax = """v = \\sqrt{\\frac{F}{\\mu}} \\quad \\text{onde } \\mu = \\frac{m}{L}""",
                description = "Velocidade de propagação de ondas transversais em uma corda sob tração mecânica F com densidade linear mu.",
                parametersInfo = """F: \\text{força de tração (N)}; \\quad \\mu: \\text{massa por unidade de comprimento (kg/m)}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cálculo de Frequência de Estação de Rádio FM",
                codeOrFormula = """f = 100\\text{ MHz} = 10^8\\text{ Hz} \\implies \\lambda = \\frac{3 \\times 10^8}{10^8} = 3\\text{ m}""",
                explanation = "O comprimento de onda de emissoras de rádio FM na faixa de 100 MHz é de exatamente 3 metros no ar."
            ),
            LessonExample(
                title = "Transição de Meio e Invariância de Frequência",
                codeOrFormula = """\\frac{v_1}{\\lambda_1} = \\frac{v_2}{\\lambda_2} = f = \\text{constante}""",
                explanation = "Quando uma onda passa de um meio mais rápido para um mais lento, o comprimento de onda encurta na mesma proporção em que a velocidade diminui."
            )
        ),
        goldTip = "Decore esta regra de ouro do ENEM: a frequência da onda NUNCA muda ao mudar de meio, pois ela depende unicamente da fonte emissora. O que muda é a velocidade (definida pelas propriedades do meio), o que força o comprimento de onda a se ajustar na proporção direta: se a velocidade cair pela metade, o comprimento de onda cairá pela metade!"
    )

    // =========================================================================
    // AULA fis_m4_a2: Fenômenos Ondulatórios - Reflexão, Refração e Difração
    // =========================================================================
    private val bundleFisM4A2 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Quando uma onda se propaga e encontra obstáculos, fronteiras entre meios ou fendas espaciais, ela manifesta comportamentos fascinantes conhecidos como **Fenômenos Ondulatórios**. Trata-se do núcleo central da ondulatória no ENEM: praticamente todos os anos a banca do exame cobra a identificação precisa do fenômeno ondulatório atuante em um determinado cenário da vida cotidiana ou da tecnologia moderna. Nesta aula, exploramos em detalhe a tríade inicial: **Reflexão**, **Refração** e a notável **Difração**.

## 2. Objetivos de Aprendizagem
- Analisar a **Reflexão** de ondas bidimensionais e em cordas com extremidade fixa (com inversão de fase) e livre (sem inversão de fase).
- Compreender a **Refração** ondulatória, a Lei de Snell-Descartes e a constância mandatória da frequência (\( f = \text{cte} \)).
- Compreender o **Princípio de Huygens** e a propagação de frentes de onda secundárias.
- Dominar o fenômeno da **Difração**: a capacidade das ondas de contornar obstáculos ou atravessar orifícios.
- Estabelecer o critério fundamental para a ocorrência expressiva da difração: o comprimento de onda \( \lambda \) deve ter a mesma ordem de grandeza do orifício ou obstáculo (\( \lambda \approx d \)).
- Explicar por que conseguimos ouvir uma conversa que ocorre atrás de um muro ou em uma sala contígua, mas não conseguimos enxergar as pessoas que falam (comparação entre difração sonora vs. difração luminosa).

## 3. Pré-requisitos
- Fundamentos da Ondulatória e Equação Fundamental (\( v = \lambda \cdot f \)) (Física, Aula fis_m4_a1).
- Noções de Óptica Geométrica e ângulos normais à superfície (Física, Aula fis_m5_a1).

## 4. Explicação Teórica Aprofundada

### 1. Reflexão de Ondas
A reflexão ocorre quando uma onda atinge uma superfície que separa dois meios e retorna para o meio de origem, mantendo sua velocidade, frequência e comprimento de onda inalterados:
- **Leis da Reflexão:** O raio incidente, o raio refletido e a reta normal à superfície no ponto de incidência são coplanares; e o ângulo de incidência é rigorosamente igual ao ângulo de reflexão:
  \[ \theta_i = \theta_r \]
- **Reflexão em Cordas:**
  - *Extremidade Fixa:* Quando o pulso atinge a parede fixa, exerce uma força sobre a parede para cima; pela 3ª Lei de Newton (Ação e Reação), a parede reage exercendo uma força de igual módulo para baixo no pulso, retornando o pulso **com inversão de fase** (defasagem de \( 180^\circ \) ou \( \pi\text{ rad} \)).
  - *Extremidade Livre (anel móvel deslizante):* O anel sobe livremente e puxa a corda de volta, retornando o pulso **sem inversão de fase**.

### 2. Refração de Ondas
A refração ocorre quando uma onda atravessa a superfície de separação entre dois meios com propriedades físicas distintas, sofrendo alteração em sua **velocidade de propagação**:
- **Regras Imutáveis na Refração:**
  1. A frequência \( f \) **NÃO se altera** (\( f_1 = f_2 \)).
  2. A velocidade \( v \) varia dependendo das características físicas dos meios (\( v_1 \neq v_2 \)).
  3. O comprimento de onda \( \lambda \) varia proporcionalmente à velocidade:
     \[ \frac{v_1}{v_2} = \frac{\lambda_1}{\lambda_2} \]
- **Lei de Snell-Descartes:**
  \[ \frac{\operatorname{sen}\theta_1}{\operatorname{sen}\theta_2} = \frac{v_1}{v_2} = \frac{\lambda_1}{\lambda_2} \]
  Se a onda passa para um meio onde sua velocidade diminui (\( v_2 < v_1 \)), ela se aproxima da normal (\( \theta_2 < \theta_1 \)) e seu comprimento de onda encurta (\( \lambda_2 < \lambda_1 \)).

### 3. O Princípio de Huygens
Proposto pelo físico holandês Christiaan Huygens em 1678: **"Cada ponto de uma frente de onda primária atua como uma fonte pontual independente de novas ondinhas esféricas secundárias, que se propagam para a frente com a mesma velocidade da onda no meio."** A nova frente de onda em qualquer instante posterior é a superfície tangente (envoltória) a essas ondinhas secundárias. Esse princípio elegante explica matematicamente a reflexão, a refração e, sobretudo, a difração.

### 4. Difração de Ondas
A difração é a propriedade exclusiva das ondas de **contornar obstáculos** ou espalhar-se para além dos limites geométricos das sombras após atravessar aberturas ou fendas.
- **Critério de Ocorrência da Difração:**
  A difração só é perceptível e significativa quando o tamanho da fenda ou do obstáculo (\( d \)) tem dimensões **da mesma ordem de grandeza ou menores que o comprimento de onda** da onda incidente:
  \[ \lambda \ge d \quad \text{ou} \quad \frac{\lambda}{d} \approx 1 \]
  - Se \( d \gg \lambda \): A onda propaga-se retilineamente, projetando uma sombra geométrica nítida (difração desprezível).
  - Se \( d \le \lambda \): As ondinhas de Huygens curvam-se completamente ao redor da borda da fenda, e a abertura passa a comportar-se praticamente como uma fonte pontual esférica que preenche toda a região atrás do obstáculo!

### 5. Comparação Clássica do ENEM: Por que ouvimos atrás do muro, mas não vemos?
- **O Som:** A velocidade do som no ar é \( \approx 340\text{ m/s} \). Para uma voz humana típica de frequência \( 340\text{ Hz} \):
  \[ \lambda_{\text{som}} = \frac{340}{340} = 1\text{ metro} \]
  Como o comprimento de onda do som (\( \sim 1\text{ m} \)) tem a mesma ordem de grandeza de portas, janelas, árvores e muros (\( \sim 1\text{ a } 2\text{ m} \)), o som **difrata intensamente**, contornando o muro com extrema facilidade!
- **A Luz Visível:** O comprimento de onda da luz visível é minúsculo:
  \[ \lambda_{\text{luz}} \approx 400\text{ a } 700\text{ nanômetros} = 0{,}0000005\text{ m} \]
  Portas e muros são milhões de vezes maiores que o comprimento de onda da luz (\( d \gg \lambda \)). Logo, a luz sofre **difração imperceptível** a olho nu no cotidiano, projetando sombras retilíneas e não contornando o muro!

## 5. Definições e Conceitos
- **Inversão de Fase:** Mudança de \( 180^\circ \) na forma do pulso refletido (uma crista retorna como vale).
- **Ângulo de Incidência (\( \theta_i \)):** Ângulo formado entre a direção de propagação do raio da onda e a reta normal à superfície (e NÃO com a superfície em si!).
- **Difração Quântica:** Proposta por Louis de Broglie: elétrons e partículas com massa também exibem difração quando disparados contra redes cristalinas microscópicas.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Refração de Ondas na Água
Uma onda periódica propaga-se na superfície da água em uma região profunda com velocidade de \( 4\text{ m/s} \) e comprimento de onda de \( 0{,}8\text{ m} \). Ao adentrar uma região rasa, a velocidade cai para \( 2{,}5\text{ m/s} \). Determine a frequência da onda e o novo comprimento de onda na região rasa.
- **Resolução:**
  1. A frequência é invariante na refração e é calculada na região profunda:
     \[ f = \frac{v_1}{\lambda_1} = \frac{4}{0{,}8} = 5\text{ Hz} \]
  2. Na região rasa, a frequência permanece \( 5\text{ Hz} \). Logo:
     \[ \lambda_2 = \frac{v_2}{f} = \frac{2{,}5}{5} = 0{,}5\text{ m} \]
  - Resposta: A frequência é de \( 5\text{ Hz} \) (constante) e o novo comprimento de onda é \( 0{,}5\text{ m} \).

### Exemplo 2: Identificação de Fenômeno no ENEM
Uma pessoa posicionada em uma calçada ouve com clareza o som de uma ambulância se aproximando em uma rua transversal, mesmo antes do veículo apontar na esquina e entrar em sua linha de visão direta. O fenômeno ondulatório que viabiliza a audição da sirene dobrando a esquina é a:
- **Resolução:**
  - O som contorna as esquinas dos edifícios porque seu comprimento de onda (\( \sim 1\text{ metro} \)) é comparável à escala da esquina, caracterizando o fenômeno da **Difração**.

## 7. Aplicações Práticas e Modelagem
- **Sombras Acústicas e Paredes Antirruído:** Barreiras sonoras construídas ao lado de rodovias expressas tentam minimizar a difração sonora para não perturbar condomínios vizinhos.
- **Microscópios Ópticos e Limite de Resolução:** A difração da luz através da abertura das lentes de um microscópio impõe o limite de difração de Abbe: é impossível enxergar detalhes menores que metade do comprimento de onda da luz visível (\( \sim 250\text{ nm} \)). Por isso, para enxergar vírus, é indispensável usar microscópios eletrônicos.
- **Comunicação por Rádio AM vs. FM:** O rádio AM possui comprimentos de onda de centenas de metros (\( \sim 300\text{ m} \)), sofrendo difração fantástica sobre colinas e prédios, enquanto o FM (\( \sim 3\text{ m} \)) sofre bloqueios mais severos por obstáculos urbanos.

## 8. Exercícios de Fixação
1. Um pulso ondulatório é gerado em uma corda que está fixada rigidamente a uma parede maciça. Ao sofrer reflexão na parede, como retorna o pulso?
2. Uma onda eletromagnética de frequência \( 6 \times 10^{14}\text{ Hz} \) propaga-se no vácuo (\( c = 3 \times 10^8\text{ m/s} \)) e incide em um bloco de vidro onde sua velocidade é de \( 2 \times 10^8\text{ m/s} \). Qual é o comprimento de onda dessa radiação dentro do vidro?
3. Cite a condição dimensional geométrica para que uma onda sofra difração pronunciada ao atingir uma fenda em um anteparo.

## 9. Correção Comentada dos Exercícios
1. Retorna **com inversão de fase** (uma crista reflete como um vale), mantendo a mesma velocidade em módulo, mesmo comprimento de onda e frequência.
2. A frequência não se altera na refração (\( f = 6 \times 10^{14}\text{ Hz} \)). O comprimento de onda no vidro é: \( \lambda_{\text{vidro}} = \frac{v}{f} = \frac{2 \times 10^8}{6 \times 10^{14}} = \frac{1}{3} \times 10^{-6}\text{ m} \approx 333\text{ nm} \).
3. A largura da abertura \( d \) deve ter dimensões da mesma ordem de grandeza ou menores que o comprimento de onda da onda (\( d \le \lambda \)).

## 10. Desafio de Domínio
Por que os cabos de rede Wi-Fi de \( 5\text{ GHz} \) (\( \lambda \approx 6\text{ cm} \)) oferecem maior taxa de transferência de dados que os de \( 2{,}4\text{ GHz} \) (\( \lambda \approx 12{,}5\text{ cm} \)), mas têm alcance espacial muito menor dentro de uma residência com muitas paredes e divisórias?
*Pista:* Relacione a frequência com a largura de banda e o comprimento de onda com a capacidade de difração ao contornar portas e cantos de paredes.

## 11. Resumo e Pontos-Chave
- Reflexão: \( \theta_i = \theta_r \). Corda fixa: inverte a fase; corda livre: mantém a fase.
- Refração: passagem para outro meio com alteração de velocidade. Frequência constante: \( \frac{v_1}{v_2} = \frac{\lambda_1}{\lambda_2} \).
- Princípio de Huygens: cada ponto da frente atua como fonte de ondas secundárias.
- Difração: capacidade da onda de contornar obstáculos ou passar por fendas (critério: \( \lambda \approx d \)).
- O som difrata facilmente no cotidiano (\( \lambda \sim 1\text{ m} \)); a luz não difrata visivelmente no cotidiano (\( \lambda \sim 10^{-7}\text{ m} \)).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 1. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Lei da Refração de Ondas",
                syntax = """\\frac{v_1}{v_2} = \\frac{\\lambda_1}{\\lambda_2} \\quad (f = \\text{constante})""",
                description = "Relação de refração que expressa a variação do comprimento de onda proporcionalmente à mudança de velocidade.",
                parametersInfo = """v_1, v_2: \\text{velocidades nos meios 1 e 2}; \\quad \\lambda_1, \\lambda_2: \\text{comprimentos de onda correspondentes}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Condição Fundamental de Difração",
                syntax = """\\frac{\\lambda}{d} \\ge 1 \\iff \\lambda \\ge d""",
                description = "Critério dimensional de abertura para espalhamento angular e contorno acentuado de obstáculos.",
                parametersInfo = """\\lambda: \\text{comprimento de onda da perturbação}; \\quad d: \\text{largura da fenda ou dimensão do obstáculo}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Reflexão com Extremidade Fixa vs Livre",
                codeOrFormula = """\\Delta \\phi_{\\text{fixa}} = \\pi\\text{ rad} (180^\\circ); \\quad \\Delta \\phi_{\\text{livre}} = 0""",
                explanation = "A reação mecânica da parede rígida inverte a polaridade do pulso incidente, enquanto o engaste livre reflete mantendo crista como crista."
            ),
            LessonExample(
                title = "Comparação de Difração entre Rádio AM e FM",
                codeOrFormula = """\\lambda_{\\text{AM}} \\approx 300\\text{ m} \\gg \\lambda_{\\text{FM}} \\approx 3\\text{ m}""",
                explanation = "As ondas quilométricas do AM contornam montanhas por difração com eficiência incomensuravelmente superior à do FM."
            )
        ),
        goldTip = "Toda vez que a questão do ENEM descrever uma onda mudando de direção ao passar por uma fenda estreita ou contornando a quina de um muro, a resposta é DIFRAÇÃO! Se a onda bater e voltar para o mesmo meio, é REFLEXÃO. Se mudar de meio e variar de velocidade, é REFRAÇÃO!"
    )

    // =========================================================================
    // AULA fis_m4_a3: Interferência, Polarização e Ressonância
    // =========================================================================
    private val bundleFisM4A3 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Completando os grandes fenômenos ondulatórios que moldam a física clássica e a tecnologia moderna, chegamos aos três conceitos mais sofisticados e com altíssima incidência nas questões analíticas do ENEM: a **Interferência** (superposição de ondas com reforço ou anulação mútua), a **Polarização** (filtragem geométrica exclusiva de ondas transversais) e a **Ressonância** (transferência eficiente de energia quando a frequência de excitação coincide com a frequência natural de oscilação do sistema).

## 2. Objetivos de Aprendizagem
- Aplicar o **Princípio da Superposição Linear** para ondas que se cruzam no espaço.
- Deduzir as condições matemáticas para **Interferência Construtiva** e **Interferência Destrutiva** a partir da diferença de caminhos ópticos (\( \Delta d \)).
- Compreender a formação de **Ondas Estacionárias**, identificando nós (interferência destrutiva) e ventres (interferência construtiva).
- Dominar o fenômeno da **Polarização**, compreendendo por que ele é uma prova inequívoca da natureza **transversal** da luz e por que **o som NUNCA pode ser polarizado**.
- Compreender o funcionamento de filtros polarizadores (polaroides) e a Lei de Malus.
- Compreender a **Ressonância**, sua definição física e identificar suas manifestações cotidianas (forno micro-ondas, sintonia de rádio, desabamento da ponte de Tacoma Narrows e taças de cristal quebradas por cantores de ópera).

## 3. Pré-requisitos
- Fundamentos da Ondulatória e Equação Fundamental (\( v = \lambda \cdot f \)) (Física, Aula fis_m4_a1).
- Fenômenos ondulatórios: reflexão e refração (Física, Aula fis_m4_a2).

## 4. Explicação Teórica Aprofundada

### 1. Princípio da Superposição e Interferência de Ondas
Quando duas ou mais ondas periódicas propagam-se simultaneamente na mesma região do espaço, elas se sobrepõem: a elongação resultante em cada ponto é a soma algébrica das elongações individuais:
\[ y_{\text{res}} = y_1 + y_2 \]
Após o cruzamento, cada onda prossegue sua trajetória exatamente com a mesma velocidade, amplitude e forma originais, como se as outras nunca tivessem existido (independência mútua das ondas).
- **Interferência Construtiva (Reforço):** Duas cristas (ou dois vales) coincidem no mesmo ponto em concordância de fase:
  \[ A_{\text{res}} = A_1 + A_2 \]
- **Interferência Destrutiva (Anulação):** Uma crista coincide com um vale em oposição de fase:
  \[ A_{\text{res}} = |A_1 - A_2| \]
  Se as amplitudes forem idênticas (\( A_1 = A_2 \)), ocorre anulação total (\( A_{\text{res}} = 0 \)). É esse princípio exato que fundamenta os **fones de ouvido com cancelamento de ruído ativo (ANC)**: microfones externos captam o ruído ambiente e geram uma onda sonora em perfeita oposição de fase de 180°, anulando o som por interferência destrutiva antes que ele atinja o tímpano.

### 2. Condição de Interferência para Duas Fontes Coerentes em Fase
Considere duas fontes pontuais \( F_1 \) e \( F_2 \) emitindo ondas em fase de mesmo comprimento \( \lambda \). Para um ponto \( P \) situado a distâncias \( d_1 \) e \( d_2 \), a diferença de percurso é \( \Delta d = |d_1 - d_2| \):
- **Interferência Construtiva (Maximização):**
  \[ \Delta d = n \cdot \frac{\lambda}{2} \quad \text{com } n \in \{0, 2, 4, 6, \dots\} \text{ (par)} \implies \Delta d = k \cdot \lambda \]
- **Interferência Destrutiva (Minimização / Silêncio):**
  \[ \Delta d = n \cdot \frac{\lambda}{2} \quad \text{com } n \in \{1, 3, 5, 7, \dots\} \text{ (ímpar)} \]

### 3. Ondas Estacionárias
Resultam da interferência contínua entre duas ondas idênticas que se propagam na mesma direção e em sentidos opostos (como uma onda e seu reflexo em uma corda esticada):
- **Nós (Nodos):** Pontos permanentemente parados com amplitude estritamente nula (\( A = 0 \)) devido à interferência destrutiva permanente.
- **Ventres (Antinodos):** Pontos que oscilam com amplitude máxima (\( 2A \)) devido à interferência construtiva.
- A distância entre dois nós consecutivos (ou dois ventres consecutivos) vale sempre **meio comprimento de onda**:
  \[ d_{N-N} = \frac{\lambda}{2} \]

### 4. Polarização: O Teste Definitivo de Ondas Transversais
A polarização é o processo de selecionar ou filtrar uma única direção de oscilação para uma onda que originalmente vibra em múltiplos planos ortogonais à propagação.
- **A Luz Não Polarizada (Luz Natural):** O vetor campo elétrico (\( \vec{E} \)) oscila aleatoriamente em todas as direções perpendiculares à direção de propagação.
- **Filtro Polarizador (Polaroide):** Possui polímeros de longas cadeias moleculares alinhadas que absorvem as componentes elétricas paralelas a elas e deixam passar apenas a componente perpendicular (o eixo de transmissão do filtro).
- **Cruzamento de Dois Polarizadores (Lei de Malus):**
  Se a luz já polarizada incidir em um segundo filtro (analisador) cujo eixo forma um ângulo \( \theta \) com o primeiro:
  \[ I = I_0 \cdot \cos^2\theta \]
  Se os dois polarizadores estiverem a \( 90^\circ \) (eixos cruzados perpendiculares), **nenhuma luz passa** (\( I = 0 \)).
- **A Regra de Ouro do ENEM:** **Apenas ondas transversais podem ser polarizadas!** Ondas longitudinais (como o som no ar) vibram paralelamente à direção de deslocamento, sendo fisicamente impossível polarizá-las.

### 5. Ressonância
Todo sistema físico oscilante (um pêndulo, uma ponte, uma coluna de ar, um átomo, uma molécula) possui uma ou mais **frequências naturais de oscilação** (\( f_0 \)), determinadas por suas propriedades mecânicas, elásticas ou geométricas.
- **Definição de Ressonância:** Ocorre quando um sistema oscilante é excitado por uma força periódica externa cuja frequência \( f_{\text{ext}} \) coincide exatamente ou fica muito próxima de sua frequência natural:
  \[ f_{\text{ext}} \approx f_0 \]
- **Consequência:** O sistema passa a absorver energia da fonte excitadora com eficiência máxima a cada ciclo, provocando um **crescimento dramático na amplitude de oscilação**.
- **Exemplos no ENEM:**
  - *Forno de Micro-ondas:* As ondas eletromagnéticas de \( 2{,}45\text{ GHz} \) têm frequência compatível com os modos vibracionais/rotacionais das moléculas de água dos alimentos, transferindo energia cinética com altíssima taxa e aquecendo a refeição.
  - *Sintonia de Rádio:* Ao girar o botão de sintonia, ajusta-se a capacitância do circuito RLC interno para que sua frequência de ressonância natural coincida com a frequência da estação desejada, amplificando apenas o seu sinal.
  - *Ponte de Tacoma Narrows (EUA, 1940):* Rajadas de vento causaram vórtices periódicos de von Kármán cuja frequência coincidiu com a frequência natural de torção da ponte suspensa, amplificando as oscilações até o colapso estrutural catastrófico.

## 5. Definições e Conceitos
- **Fontes Coerentes:** Fontes emissoras que possuem rigorosamente a mesma frequência e mantêm uma diferença de fase constante no tempo.
- **Luz Linearmente Polarizada:** Onda eletromagnética cujo vetor campo elétrico vibra confinado a um único plano fixo no espaço.
- **Amortecimento:** Dissipação gradual da energia mecânica de um oscilador por atrito ou resistência do ar, limitando a amplitude máxima atingida na ressonância.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Cancelamento de Ruído por Interferência Destrutiva
Um microfone capta um ruído externo de tráfego de frequência \( 170\text{ Hz} \). Sabendo que a velocidade do som no ar é \( 340\text{ m/s} \), qual deve ser a defasagem temporal mínima introduzida pelo circuito do fone para gerar anulação completa do ruído?
- **Resolução:**
  1. Período da onda sonora:
     \[ T = \frac{1}{f} = \frac{1}{170}\text{ s} \]
  2. Para que haja interferência destrutiva plena, a crista do som gerado pelo fone deve coincidir exatamente com o vale do ruído externo, exigindo uma defasagem de meio ciclo (\( 180^\circ \) ou \( \pi\text{ rad} \)):
     \[ \Delta t = \frac{T}{2} = \frac{1}{2 \cdot 170} = \frac{1}{340}\text{ s} \approx 2{,}94\text{ milissegundos} \]
  - Resposta: O fone gera a onda defasada de meio período (\( \approx 2{,}94\text{ ms} \)), anulando o ruído por interferência destrutiva.

### Exemplo 2: Cinema 3D e Polarização
Em salas de cinema 3D convencionais, o projetor lança simultaneamente na tela duas imagens capturadas sob ângulos ligeiramente diferentes. Os óculos fornecidos ao espectador possuem duas lentes escuras. Ao fechar um dos olhos, o espectador enxerga a imagem correspondente nítida; ao abrir ambos, percebe o efeito tridimensional de profundidade. Qual fenômeno garante que cada olho receba exclusivamente a sua imagem?
- **Resolução:**
  - O fenômeno é a **Polarização**. O projetor emite as imagens com luz polarizada em eixos ortogonais entre si (por exemplo, lente esquerda com eixo vertical e lente direita com eixo horizontal, ou polarizações circulares horária e anti-horária). As lentes dos óculos contêm filtros polaroides orientados de tal forma que cada olho absorve a imagem do outro e transmite apenas a imagem correspondente, permitindo ao cérebro fundir os ângulos e reconstruir a visão binocular tridimensional.

## 7. Aplicações Práticas e Modelagem
- **Películas Fotográficas e Óculos de Sol Polarizados:** A luz solar refletida em superfícies horizontais lisas (asfalto molhado, espelhos d'água) sofre polarização parcial horizontal. Óculos de sol com filtros polarizadores verticais bloqueiam esse reflexo ofuscante sem escurecer excessivamente a visão geral.
- **Ressonância Magnética Nuclear (RMN):** Utiliza campos magnéticos fortíssimos e pulsos de radiofrequência em ressonância com os núcleos de hidrogênio (prótons) da água do corpo humano para gerar imagens anatômicas tridimensionais de altíssima definição diagnóstica.
- **Telas de Cristal Líquido (LCD):** Compostas por camadas de cristais líquidos imprensadas entre dois filtros polarizadores cruzados; correntes elétricas micrométricas rotacionam a polarização da luz para modular o brilho de cada subpixel da tela.

## 8. Exercícios de Fixação
1. Um estudante tenta polarizar o som emitido por uma caixa acústica utilizando uma folha de plástico polaroide. O estudante terá sucesso? Justifique cientificamente.
2. Em um experimento de interferência com duas fontes sonoras em fase emitindo um som de \( \lambda = 2\text{ metros} \), um ouvinte situa-se em um ponto onde a distância até a primeira fonte é de \( 10\text{ m} \) e até a segunda é de \( 7\text{ m} \). O ouvinte perceberá som reforçado (máximo) ou silêncio (mínimo)?
3. Uma taça de cristal quebra quando uma cantora lírica atinge uma nota aguda contínua em alto volume. Identifique e defina o fenômeno físico responsável por esse efeito.

## 9. Correção Comentada dos Exercícios
1. **Não terá sucesso.** A polarização é uma propriedade física exclusiva de **ondas transversais**. O som em meios fluidos como o ar é uma **onda puramente longitudinal** (as partículas vibram na mesma direção de deslocamento da energia), tornando impossível sua polarização.
2. Diferença de caminhos: \( \Delta d = |10 - 7| = 3\text{ metros} \).
   Igualando à expressão: \( \Delta d = n \cdot \frac{\lambda}{2} \implies 3 = n \cdot \frac{2}{2} \implies n = 3 \).
   Como \( n = 3 \) é um número **ímpar**, as ondas chegam em oposição de fase, ocorrendo **Interferência Destrutiva** (o ouvinte perceberá som de intensidade mínima / quase silêncio).
3. O fenômeno é a **Ressonância**. A cantora emitiu uma onda sonora cuja frequência coincidiu perfeitamente com a frequência natural de vibração da estrutura cristalina da taça, fazendo com que a taça absorvesse energia continuamente até que a amplitude mecânica superasse o limite de resistência elástica do cristal, despedaçando-o.

## 10. Desafio de Domínio
Por que em uma sala de concertos ou em um auditório é fundamental o uso de revestimentos acústicos assimétricos e painéis difusores, em vez de paredes perfeitamente planas e paralelas? Relacione sua resposta com o fenômeno de ondas estacionárias e pontos mortos de interferência.
*Pista:* Pense no que ocorre quando reflexões paralelas geram nós acústicos fixos onde o público não consegue ouvir determinadas frequências.

## 11. Resumo e Pontos-Chave
- Interferência: superposição de ondas com reforço (construtiva, crista+crista) ou anulação (destrutiva, crista+vale).
- Condição de caminho: \( \Delta d = n \frac{\lambda}{2} \) (\( n \) par: construtiva; \( n \) ímpar: destrutiva).
- Fones ANC usam interferência destrutiva ativa de ondas em oposição de fase.
- Ondas estacionárias: nós (anulação, amplitude zero) e ventres (reforço, amplitude máxima). Distância nó-nó = \( \lambda/2 \).
- Polarização: seleção de um único plano de vibração; exclusiva de ondas transversais (a luz pode; o som nunca!).
- Ressonância: excitação na frequência natural com máxima transferência de energia e explosão de amplitude.

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 1. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Geral da Interferência por Diferença de Caminho",
                syntax = """\\Delta d = |d_1 - d_2| = n \\cdot \\frac{\\lambda}{2}""",
                description = "Condição matemática para interferência entre fontes coerentes em fase.",
                parametersInfo = """\\Delta d: \\text{diferença de caminho (m)}; \\quad n \\text{ par: construtiva}; \\quad n \\text{ ímpar: destrutiva}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Espaçamento Entre Nós em Onda Estacionária",
                syntax = """d_{N-N} = \\frac{\\lambda}{2} \\quad \\text{e} \\quad d_{N-V} = \\frac{\\lambda}{4}""",
                description = "Distância geométrica fixa entre dois nós consecutivos ou entre um nó e o ventre consecutivo.",
                parametersInfo = """\\lambda: \\text{comprimento de onda da oscilação estacionária}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Lei de Malus da Polarização Luminosa",
                syntax = """I = I_0 \\cdot \\cos^2\\theta""",
                description = "Intensidade da luz transmitida através de um analisador polarizador formando um ângulo theta com o plano de polarização original.",
                parametersInfo = """I_0: \\text{intensidade incidente}; \\quad \\theta: \\text{ângulo entre os eixos de polarização}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Fones com Cancelamento Ativo de Ruído (ANC)",
                codeOrFormula = """y_{\\text{ruído}} + y_{\\text{alto-falante}} = A\\operatorname{sen}(\\omega t) + A\\operatorname{sen}(\\omega t + \\pi) = 0""",
                explanation = "A emissão artificial de uma onda sonora em oposição de fase exata de 180° anula o ruído de fundo por interferência puramente destrutiva."
            ),
            LessonExample(
                title = "Filtros Polarizadores Cruzados a 90 Graus",
                codeOrFormula = """I = I_0 \\cdot \\cos^2(90^\\circ) = I_0 \\cdot 0 = 0""",
                explanation = "Dois filtros polares alinhados perpendicularmente barram totalmente a transmissão de fótons luminosos."
            )
        ),
        goldTip = "A pegadinha mais letal do ENEM sobre ondulatória: 'O som emitido por uma sirene pode ser polarizado?' NUNCA! A polarização é exclusividade absoluta de ondas transversais (como a luz e as ondas de rádio). O som é uma onda longitudinal, logo jamais pode sofrer polarização!"
    )

    // =========================================================================
    // AULA fis_m4_a4: Acústica e Qualidades Fisiológicas do Som
    // =========================================================================
    private val bundleFisM4A4 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A **Acústica** é o ramo da Física ondulatória dedicado à produção, transmissão, percepção e controle das ondas sonoras. No ser humano, o aparelho auditivo opera como um sofisticado transdutor mecanoelétrico capaz de converter variações infinitesimais de pressão do ar em impulsos elétricos decodificados pelo cérebro como sons musicais, ruídos ou fala. O ENEM aborda a acústica sob duas perspectivas principais: a caracterização objetiva das grandezas físicas e a resposta subjetiva do sistema biológico humano, conhecida como as **Qualidades Fisiológicas do Som**.

## 2. Objetivos de Aprendizagem
- Caracterizar a natureza física do som: onda mecânica, longitudinal em fluidos e tridimensional.
- Analisar a velocidade do som e sua dependência com o meio: \( v_{\text{sólidos}} > v_{\text{líquidos}} > v_{\text{gases}} \) e com a temperatura do ar.
- Definir o espectro sonoro humano: infrassons (\( < 20\text{ Hz} \)), sons audíveis (\( 20\text{ Hz} \le f \le 20.000\text{ Hz} \)) e ultrassons (\( > 20.000\text{ Hz} \)).
- Dominar as três **Qualidades Fisiológicas do Som**:
  1. **Altura** (frequência: grave vs. agudo).
  2. **Intensidade** (amplitude / energia: som forte vs. som fraco) e escala logarítmica de Nível Sonoro em Decibéis (\( \text{dB} \)).
  3. **Timbre** (forma da onda / composição de harmônicos: distinção de instrumentos).
- Compreender a reflexão do som: reforço, reverberação e eco (\( \Delta t \ge 0{,}1\text{ s} \implies d \ge 17\text{ m} \)).
- Modelar ondas estacionárias em **Tubos Sonoros Abertos e Fechados**.

## 3. Pré-requisitos
- Equação Fundamental da Ondulatória (\( v = \lambda \cdot f \)) (Física, Aula fis_m4_a1).
- Logaritmos decimais para cálculo do nível sonoro (Matemática Básica, Aula mb_l08).

## 4. Explicação Teórica Aprofundada

### 1. A Natureza e a Propagação do Som
O som é uma onda mecânica gerada pela vibração de um corpo material que perturba a pressão do meio circundante, produzindo regiões alternadas de **compressão** (maior densidade/pressão) e **rarefação** (menor densidade/pressão).
- **Velocidade do Som:** Depende da elasticidade e da densidade inercial do meio:
  \[ v_{\text{sólidos}} > v_{\text{líquidos}} > v_{\text{gases}} \]
  - No ar seco a \( 20^\circ\text{C} \): \( v \approx 340\text{ m/s} \).
  - Na água do mar: \( v \approx 1500\text{ m/s} \).
  - No ferro/aço: \( v \approx 5100\text{ m/s} \).
  Em gases, quanto mais quente o ar, maior a velocidade térmica molecular e mais rápido o som viaja.

### 2. O Espectro Sonoro
O ouvido humano padrão é sensível exclusivamente à faixa entre \( 20\text{ Hz} \) e \( 20.000\text{ Hz} \) (\( 20\text{ kHz} \)):
- **Infrassom (\( f < 20\text{ Hz} \)):** Inaudível para humanos. Produzido por terremotos, avalanches, tsunamis e comunicação de longa distância entre elefantes e baleias.
- **Faixa Audível (\( 20\text{ Hz} \le f \le 20\text{ kHz} \)):** Voz humana (\( \approx 85\text{ a } 255\text{ Hz} \)).
- **Ultrassom (\( f > 20\text{ kHz} \)):** Inaudível para humanos. Utilizado por morcegos e golfinhos para ecolocalização, e na medicina diagnóstica (\( 2\text{ a } 15\text{ MHz} \)) e limpeza por cavitação ultrassônica.

### 3. Qualidades Fisiológicas do Som
Três atributos auditivos independentes:
1. **Altura (Qualidade associada à FREQUÊNCIA):**
   - Permite diferenciar um **som grave** (baixa frequência, voz grossa de baixo/barítono, som do bumbo ou contrabaixo) de um **som agudo** (alta frequência, voz fina de soprano, som do violino ou flauta).
   - *Atenção:* "Aumentar a altura" na física significa tornar o som mais agudo (aumentar a frequência), e **NÃO** aumentar o volume!
2. **Intensidade (Qualidade associada à AMPLITUDE e ENERGIA):**
   - Permite diferenciar um **som forte** (grande amplitude de pressão, muita energia transportada) de um **som fraco** (pequena amplitude, som sussurrado).
   - **Intensidade Física (\( I \)):** É a potência sonora distribuída por unidade de área perpendicular à propagação:
     \[ I = \frac{P}{A} = \frac{P}{4\pi R^2} \quad (\text{W/m}^2) \]
   - **Limiar de Audibilidade:** \( I_0 = 10^{-12}\text{ W/m}^2 \) (menor intensidade percebida pelo ouvido saudável a 1 kHz).
   - **Limiar de Dor:** \( I_{\text{dor}} = 1\text{ W/m}^2 \).
   - **Nível Sonoro em Decibéis (\( \beta \)):** Devido à resposta logarítmica da sensibilidade auditiva (Lei de Weber-Fechner):
     \[ \beta = 10 \cdot \log_{10}\left(\frac{I}{I_0}\right) \quad (\text{dB}) \]
     Se a intensidade física \( I \) decuplica (\( \times 10 \)), o nível sonoro aumenta em \( +10\text{ dB} \). Se aumentar \( 100 \) vezes, o nível aumenta em \( +20\text{ dB} \).
3. **Timbre (Qualidade associada à FORMA DA ONDA e HARMÔNICOS):**
   - É a propriedade que permite distinguir duas fontes sonoras que emitem uma nota musical de **mesma frequência fundamental (mesma altura) e mesma intensidade sonora**, tocadas por instrumentos diferentes (por exemplo, a mesma nota Dó tocada por um piano e por um violino).
   - Origina-se da superposição de harmônicos superiores (análise de Fourier) gerados pela ressonância específica do corpo físico de cada instrumento.

### 4. Reflexão Sonora: Reforço, Reverberação e Eco
O ouvido humano possui uma persistência auditiva temporal de \( 0{,}1\text{ segundo} \) (\( \Delta t_{\text{mín}} = 0{,}1\text{ s} \)). Se dois pulsos sonoros atingirem a orelha em um intervalo menor que \( 0{,}1\text{ s} \), o cérebro não os separa, fundindo-os:
- **Reforço (\( \Delta t \to 0 \)):** O som refletido chega quase simultaneamente ao som direto, provocando apenas aumento aparente da intensidade.
- **Reverberação (\( \Delta t < 0{,}1\text{ s} \)):** O som refletido chega antes de \( 0{,}1\text{ s} \), causando um prolongamento da sensação auditiva (sensação de eco inacabado, comum em catedrais e salas vazias).
- **Eco (\( \Delta t \ge 0{,}1\text{ s} \)):** O som refletido retorna com atraso suficiente para que o cérebro perceba uma repetição nítida e isolada da palavra original.
  - Distância mínima para o eco no ar (\( v = 340\text{ m/s} \)):
    \[ \Delta S = v \cdot \Delta t \implies 2d = 340 \cdot 0{,}1 = 34\text{ m} \implies d = 17\text{ metros} \]
    O obstáculo refletor deve estar a no mínimo **17 metros** de distância da fonte emissora.

### 5. Tubos Sonoros (Ondas Estacionárias Acústicas)
1. **Tubos Abertos (Abertos nas duas extremidades):**
   - Apresentam **ventres de deslocamento** nas duas pontas abertas:
     \[ L = n \cdot \frac{\lambda}{2} \implies f_n = n \cdot \frac{v}{2L} \quad (n = 1, 2, 3, \dots) \]
   - Emite **todos os harmônicos** (pares e ímpares).
2. **Tubos Fechados (Uma ponta aberta e uma fechada):**
   - Apresentam nó na ponta fechada e ventre na ponta aberta:
     \[ L = n \cdot \frac{\lambda}{4} \implies f_n = n \cdot \frac{v}{4L} \quad (n = 1, 3, 5, \dots) \]
   - Emite **estritamente harmônicos ímpares**.

## 5. Definições e Conceitos
- **Poluição Sonora:** Exposição prolongada a níveis sonoros acima de \( 80\text{ dB} \), capaz de lesionar irreversivelmente os cílios das células ciliadas da cóclea.
- **Sonar (Sound Navigation and Ranging):** Equipamento naval que emite pulsos ultrassônicos e mede o tempo de retorno do eco para mapear o leito oceânico e detectar submarinos (\( d = \frac{v \cdot \Delta t}{2} \)).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Cálculo de Nível Sonoro com Duplicação de Potência
Uma máquina industrial emite um ruído com intensidade sonora de \( 10^{-4}\text{ W/m}^2 \). Adotando o limiar auditivo padrão \( I_0 = 10^{-12}\text{ W/m}^2 \), determine o nível sonoro desse ruído em decibéis. Se uma segunda máquina idêntica for ligada ao lado, para quanto sobe o nível sonoro? (Dado: \( \log_{10} 2 = 0{,}30 \)).
- **Resolução:**
  1. Nível sonoro para uma máquina:
     \[ \beta_1 = 10 \cdot \log_{10}\left(\frac{10^{-4}}{10^{-12}}\right) = 10 \cdot \log_{10}(10^8) = 10 \cdot 8 = 80\text{ dB} \]
  2. Com duas máquinas idênticas operando simultaneamente, a intensidade dobra: \( I_2 = 2 \times 10^{-4}\text{ W/m}^2 \).
     \[ \beta_2 = 10 \cdot \log_{10}\left(\frac{2 \times 10^{-4}}{10^{-12}}\right) = 10 \cdot \log_{10}(2 \times 10^8) \]
     \[ \beta_2 = 10 \cdot (\log_{10} 2 + \log_{10} 10^8) = 10 \cdot (0{,}30 + 8) = 10 \cdot 8{,}30 = 83\text{ dB} \]
  - Resposta: O ruído de uma máquina é de \( 80\text{ dB} \). Ao dobrar as fontes sonoras, o nível sobe para \( 83\text{ dB} \) (e NÃO para 160 dB!).

### Exemplo 2: Distinção Fisiológica
Um locutor de rádio diz ao microfone: *"Por favor, aumentem a altura do aparelho de som de vocês!"*. Sob o ponto de vista estrito da Física ondulatória, o locutor cometeu uma imprecisão conceitual?
- **Resolução:**
  - Sim. Na Física, a **Altura** está ligada unicamente à **frequência**: aumentar a altura significa emitir uma frequência mais alta, tornando o som mais fino (agudo). O que o locutor desejava era aumentar o "volume", isto é, a **Intensidade Sonora** (amplitude e potência acústica).

## 7. Aplicações Práticas e Modelagem
- **Ecolocalização Biológica:** Morcegos emitem pulsos de ultrassom de \( 40\text{ kHz} \) a \( 100\text{ kHz} \); calculando o retardo do eco e o desvio Doppler, detectam a distância, tamanho e velocidade de mosquitos no breu total.
- **Instrumentos Musicais de Sopro:** Flautas e clarinetes ajustam o comprimento efetivo \( L \) da coluna de ar fechando orifícios com os dedos, mudando a frequência fundamental \( f_1 = \frac{v}{2L} \) da nota musical emitida.

## 8. Exercícios de Fixação
1. Um navio de pesquisa oceanográfica emite um pulso de sonar verticalmente em direção ao fundo do mar. O eco refletido retorna ao receptor do navio após \( 1{,}6\text{ segundo} \). Sabendo que a velocidade de propagação do som na água marinha é de \( 1500\text{ m/s} \), determine a profundidade do oceano naquele ponto.
2. Relacione cada termo com sua respectiva qualidade fisiológica:
   - (A) Frequência da onda
   - (B) Amplitude da onda
   - (C) Formato geométrico da onda e seus harmônicos
   ( ) Timbre; ( ) Altura; ( ) Intensidade.
3. Um tubo sonoro fechado de \( 0{,}85\text{ m} \) de comprimento emite seu som fundamental. Sabendo que a velocidade do som no ar é \( 340\text{ m/s} \), calcule a frequência fundamental desse tubo.

## 9. Correção Comentada dos Exercícios
1. O pulso desce até o fundo e sobe de volta: \( 2d = v \cdot \Delta t \implies 2d = 1500 \cdot 1{,}6 = 2400\text{ m} \implies d = 1200\text{ metros} \).
2. A sequência correta é: (C) Timbre; (A) Altura; (B) Intensidade.
3. Tubo fechado no harmônico fundamental (\( n = 1 \)): \( L = \frac{\lambda}{4} \implies \lambda = 4L = 4 \cdot 0{,}85 = 3{,}4\text{ m} \).
   Frequência: \( f = \frac{v}{\lambda} = \frac{340}{3{,}4} = 100\text{ Hz} \).

## 10. Desafio de Domínio
Por que ao inalarmos gás hélio puro de um balão e falarmos imediatamente em seguida, a nossa voz fica temporariamente com um tom extremamente "fino" e agudo? A frequência fundamental das nossas cordas vocais mudou de fato, ou o fenômeno reside na velocidade de propagação do som no gás que preenche as vias aéreas?
*Pista:* Compare a densidade e a velocidade do som no Hélio (\( \approx 965\text{ m/s} \)) com a velocidade do som no Ar (\( \approx 340\text{ m/s} \)) dentro da cavidade ressonante da laringe (\( f = v/\lambda \)).

## 11. Resumo e Pontos-Chave
- Som: onda mecânica, longitudinal em fluidos, requer meio material (\( v_{\text{sól}} > v_{\text{líq}} > v_{\text{gás}} \)).
- Altura = Frequência (grave vs. agudo).
- Intensidade = Amplitude / Energia (forte vs. fraco). Nível em decibéis: \( \beta = 10 \log(I/I_0) \).
- Timbre = Formato da onda / Espectro harmônico (distinção de instrumentos na mesma nota e volume).
- Eco no ar: distância mínima de \( 17\text{ m} \) (\( \Delta t \ge 0{,}1\text{ s} \)).
- Tubos abertos: todos os harmônicos (\( f_n = n \frac{v}{2L} \)). Tubos fechados: apenas harmônicos ímpares (\( f_n = n \frac{v}{4L} \)).

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 1. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Nível Sonoro em Decibéis",
                syntax = """\\beta = 10 \\cdot \\log_{10}\\left(\\frac{I}{I_0}\\right)""",
                description = "Escala logarítmica de sensação auditiva relativa ao limiar de audibilidade humana.",
                parametersInfo = """I: \\text{intensidade sonora (W/m}^2); \\quad I_0 = 10^{-12}\\text{ W/m}^2; \\quad \\beta: \\text{nível sonoro em decibéis (dB)}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Harmônicos em Tubos Sonoros Abertos",
                syntax = """f_n = n \\cdot \\frac{v}{2L} \\quad (n = 1, 2, 3, \\dots)""",
                description = "Frequências dos harmônicos ressonantes em tubos acústicos abertos em ambas as extremidades.",
                parametersInfo = """n: \\text{número do harmônico (inteiro)}; \\quad v: \\text{velocidade do som}; \\quad L: \\text{comprimento do tubo}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Harmônicos em Tubos Sonoros Fechados",
                syntax = """f_n = n \\cdot \\frac{v}{4L} \\quad (n = 1, 3, 5, \\dots)""",
                description = "Frequências dos harmônicos possíveis em tubos sonoros fechados em uma extremidade.",
                parametersInfo = """n: \\text{número ímpar}; \\quad v: \\text{velocidade do som}; \\quad L: \\text{comprimento do tubo}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Distância Mínima para Percepção de Eco",
                codeOrFormula = """d_{\\text{mín}} = \\frac{v \\cdot \\Delta t_{\\text{mín}}}{2} = \\frac{340 \\cdot 0{,}1}{2} = 17\\text{ m}""",
                explanation = "Como a persistência auditiva humana é de 0,1 s, o som precisa percorrer ida e volta em pelo menos 17 metros de distância para ser discriminado como eco."
            ),
            LessonExample(
                title = "Aumento de Intensidade e Escala Logarítmica",
                codeOrFormula = """I' = 100 \\cdot I \\implies \\Delta \\beta = 10 \\cdot \\log_{10}(100) = 10 \\cdot 2 = +20\\text{ dB}""",
                explanation = "Um aumento de cem vezes na energia física de uma fonte sonora reflete em um acréscimo de exatamente 20 decibéis no nível sonoro."
            )
        ),
        goldTip = "Cuidado com o vocabulário popular! No cotidiano as pessoas dizem 'abaixa o som porque está muito alto'. Na FÍSICA isso é ERRADO! Som alto = som agudo (frequência alta). Som baixo = som grave (frequência baixa). Para o volume do som, a física usa SOM FORTE (grande amplitude/intensidade) e SOM FRACO (pequena amplitude/intensidade)!"
    )

    // =========================================================================
    // AULA fis_m4_a5: Efeito Doppler e Aplicações Tecnológicas
    // =========================================================================
    private val bundleFisM4A5 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Quem já ouviu a passagem rápida de uma ambulância ou carro de corrida percebeu um efeito sonoro inconfundível: o som da sirene ou do motor parece marcadamente agudo enquanto o veículo se aproxima e se torna subitamente grave assim que o veículo passa e começa a se afastar. Essa alteração aparente na frequência percebida por um observador devido ao movimento relativo entre ele e a fonte emissora de ondas é o **Efeito Doppler** (homenagem ao físico austríaco Christian Doppler, 1842). Trata-se de um fenômeno universal aplicável tanto a ondas mecânicas quanto a ondas eletromagnéticas, operando como base de radares de velocidade, ecocardiogramas com fluxo sanguíneo e da descoberta cosmológica da **expansão do universo (Redshift)**.

## 2. Objetivos de Aprendizagem
- Compreender a origem física do Efeito Doppler como uma compressão ou dilatação espacial aparente das frentes de onda pelo movimento da fonte ou do observador.
- Identificar as regras fundamentais: **Aproximação relativa \( \implies \) Frequência aparente mais alta (som mais agudo)**; **Afastamento relativo \( \implies \) Frequência aparente mais baixa (som mais grave)**.
- Deduzir e aplicar com rigor a Equação Geral do Efeito Doppler Acústico: \( f' = f \left( \frac{v \pm v_O}{v \mp v_F} \right) \).
- Dominar a convenção de sinais do referencial orientado do observador para a fonte.
- Analisar o Efeito Doppler Óptico na Astronomia: Desvio para o Vermelho (*Redshift*, afastamento galáctico / Lei de Hubble) e Desvio para o Azul (*Blueshift*, aproximação).
- Compreender aplicações tecnológicas modernas: radares de trânsito e o ultrassom Doppler médico.

## 3. Pré-requisitos
- Fundamentos da Ondulatória e Equação Fundamental (\( v = \lambda \cdot f \)) (Física, Aula fis_m4_a1).
- Cinemática: velocidade relativa e referencial (Física, Aula fis_m1_a1).

## 4. Explicação Teórica Aprofundada

### 1. Mecanismo Físico do Efeito Doppler
1. **Fonte em Movimento e Observador em Repouso:**
   - Quando a fonte emissora se move com velocidade \( v_F \) em direção a um observador parado, ela emite uma crista de onda e se desloca para a frente antes de emitir a próxima crista.
   - Consequentemente, as frentes de onda no sentido do movimento ficam **espacialmente comprimidas**: o comprimento de onda aparente diminui (\( \lambda' < \lambda \)):
     \[ \lambda' = \lambda - v_F \cdot T = \frac{v - v_F}{f} \]
   - Como a velocidade do som \( v \) depende apenas do ar (meio fixo), o observador recebe as frentes de onda com uma taxa temporal maior: a **frequência aparente observada aumenta** (\( f' > f \), som mais agudo).
   - Quando a fonte se afasta, as frentes de onda ficam **espaçadas** (\( \lambda'' > \lambda \)), e o observador percebe uma **frequência menor** (\( f'' < f \), som mais grave).
2. **Observador em Movimento e Fonte em Repouso:**
   - Se o observador corre ao encontro de uma fonte fixa, o comprimento de onda no espaço é invariante, mas a velocidade relativa com que ele intercepta as cristas é maior (\( v_{\text{rel}} = v + v_O \)), elevando a frequência percebida.

### 2. A Equação Geral do Efeito Doppler
Para qualquer combinação de movimentos do observador e da fonte ao longo da linha que os une:
\[ f' = f \cdot \left( \frac{v \pm v_O}{v \mp v_F} \right) \]
- \( f' \): frequência aparente percebida pelo observador (\( \text{Hz} \)).
- \( f \): frequência real emitida pela fonte (\( \text{Hz} \)).
- \( v \): velocidade de propagação da onda no meio (\( \approx 340\text{ m/s} \) no ar).
- \( v_O \): velocidade do observador em relação ao meio.
- \( v_F \): velocidade da fonte em relação ao meio.

### 3. Regra Prática Infalível de Sinais
Adote o eixo de coordenadas orientado **sempre DO OBSERVADOR PARA A FONTE (\( O \to F \))**:
- Se a velocidade aponta no sentido do vetor \( O \to F \), use sinal positivo (\( + \)).
- Se a velocidade aponta no sentido contrário ao vetor \( O \to F \), use sinal negativo (\( - \)).
- *Verificação Lógica Mandatória:*
  - Se há **aproximação**, o resultado final deve necessariamente dar \( f' > f \).
  - Se há **afastamento**, o resultado final deve necessariamente dar \( f' < f \).

### 4. Efeito Doppler na Astronomia e o Redshift Cosmológico
No caso das ondas eletromagnéticas da luz das estrelas e galáxias distantes:
- **Redshift (Desvio para o Vermelho):** Quando uma galáxia está se afastando da Terra, as linhas espectrais características de emissão e absorção (como as do hidrogênio) são deslocadas em direção a frequências menores, isto é, em direção ao extremo vermelho do espectro visível.
  - Na década de 1920, Edwin Hubble descobriu que praticamente todas as galáxias distantes apresentam *Redshift*, e quanto mais distantes estão, maior é a sua velocidade de recessão (\( v = H_0 \cdot d \)). Essa foi a primeira comprovação observacional irrefutável de que **o Universo está em expansão** contínua desde o Big Bang.
- **Blueshift (Desvio para o Azul):** Se um astro estivesse se aproximando da Terra, seu espectro sofreria desvio para frequências mais altas (extremo azul/violeta). A galáxia de Andrômeda é um dos raros exemplos que apresenta *Blueshift*, pois está em rota de aproximação gravitacional com a Via Láctea.

### 5. Aplicações Tecnológicas do Efeito Doppler
- **Radares de Trânsito Doppler:** O aparelho emite um feixe de micro-ondas contra um automóvel. O feixe bate na lataria em movimento e reflete de volta com frequência alterada pelo efeito Doppler. O microprocessador calcula instantaneamente a velocidade do carro comparando a frequência emitida com a refletida.
- **Ecocardiograma com Doppler (Ultrassom Cardiovascular):** Mede o fluxo e a velocidade das hemácias nos vasos sanguíneos e válvulas cardíacas. O sangue que se aproxima do transdutor gera reflexão com desvio para frequências mais altas (codificado em azul ou vermelho na tela), permitindo diagnosticar sopros e estenosess arteriais.

## 5. Definições e Conceitos
- **Cone de Mach (Onda de Choque):** Quando a fonte move-se a uma velocidade superior à do som no meio (\( v_F > v \), regime supersônico), as frentes de onda acumulam-se em uma superfície cônica que gera um estrondo sônico violento (*sonic boom*).
- **Número de Mach:** Razão entre a velocidade do objeto e a velocidade do som no meio circundante (\( \text{Mach } 1 \approx 340\text{ m/s} \)).

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Cálculo da Frequência de uma Ambulância em Aproximação
Uma ambulância desloca-se em uma avenida reta com velocidade constante de \( 72\text{ km/h} \) (\( 20\text{ m/s} \)), emitindo um som de sirene de frequência pura de \( 800\text{ Hz} \). Um pedestre encontra-se parado na calçada. Sabendo que a velocidade do som no ar é de \( 340\text{ m/s} \), determine a frequência do som percebida pelo pedestre durante a aproximação da ambulância.
- **Resolução:**
  1. Identificação dos dados: \( v = 340\text{ m/s} \), \( v_F = 20\text{ m/s} \), \( v_O = 0 \) (pedestre parado), \( f = 800\text{ Hz} \).
  2. Como é aproximação, a frequência percebida deve ser maior. Para que a fração aumente, o denominador deve diminuir:
     \[ f' = f \cdot \left( \frac{v}{v - v_F} \right) \]
  3. Cálculo numérico:
     \[ f' = 800 \cdot \left( \frac{340}{340 - 20} \right) = 800 \cdot \left( \frac{340}{320} \right) = 800 \cdot 1{,}0625 = 850\text{ Hz} \]
  - Resposta: O pedestre percebe um som mais agudo, de frequência \( 850\text{ Hz} \).

### Exemplo 2: Variação de Frequência no Afastamento
Utilizando os mesmos dados do Exemplo 1, qual será a frequência percebida pelo pedestre após a ambulância ultrapassá-lo e passar a se afastar?
- **Resolução:**
  1. No afastamento, o denominador deve aumentar para reduzir o valor de \( f' \):
     \[ f'' = f \cdot \left( \frac{v}{v + v_F} \right) \]
  2. Cálculo numérico:
     \[ f'' = 800 \cdot \left( \frac{340}{340 + 20} \right) = 800 \cdot \left( \frac{340}{360} \right) = 800 \cdot \frac{17}{18} \approx 755{,}5\text{ Hz} \]
  - Resposta: Durante o afastamento, o som percebido torna-se mais grave, caindo para \( \approx 755{,}5\text{ Hz} \).

## 7. Aplicações Práticas e Modelagem
- **Meteorologia Radar (NEXRAD Doppler):** Identifica a velocidade de rotação de massas de nuvens de tempestade e gotas de chuva, permitindo emitir alertas precoces de tornados antes que toquem o solo.
- **Detecção de Exoplanetas (Método da Velocidade Radial):** A atração gravitacional mútua entre uma estrela e um planeta em sua órbita faz a estrela executar uma pequena oscilação periódica ("bamboleio"), detectável pela variação Doppler da luz da estrela.

## 8. Exercícios de Fixação
1. Um trem apita com frequência de \( 500\text{ Hz} \) enquanto se afasta de uma estação com velocidade constante. Os passageiros parados na plataforma ouvirão um apito de frequência (Maior que 500 Hz / Menor que 500 Hz / Exatamente 500 Hz)? Justifique qualitativamente.
2. Um astrônomo ao analisar o espectro de uma galáxia muito distante nota que as raias de absorção do hidrogênio estão deslocadas em direção a comprimentos de onda maiores do que os medidos no laboratório terrestre. Essa galáxia está se aproximando ou se afastando da Terra? Qual o nome desse efeito?
3. Um carro de polícia move-se a \( 34\text{ m/s} \) aproximando-se de um observador parado. A sirene emite som de \( 900\text{ Hz} \). Sabendo que \( v_{\text{som}} = 340\text{ m/s} \), calcule a frequência ouvida pelo observador.

## 9. Correção Comentada dos Exercícios
1. Os passageiros ouvirão um som de frequência **menor que 500 Hz**. No afastamento relativo, as frentes de onda se espalham no espaço percorrido, reduzindo a taxa com que atingem o tímpano do observador (som mais grave).
2. A galáxia está **se afastando da Terra**. Comprimentos de onda maiores correspondem a frequências menores (deslocamento para o vermelho), fenômeno denominado **Redshift**.
3. Na aproximação: \( f' = 900 \cdot \left( \frac{340}{340 - 34} \right) = 900 \cdot \left( \frac{340}{306} \right) = 900 \cdot \frac{10}{9} = 1000\text{ Hz} \).

## 10. Desafio de Domínio
Dois automóveis A e B trafegam em uma mesma via no mesmo sentido com velocidades rigorosamente idênticas de \( 100\text{ km/h} \). O veículo da frente (A) aciona a buzina. O motorista do veículo de trás (B) perceberá algum efeito Doppler na frequência da buzina? Demonstre matematicamente utilizando a Equação Geral.
*Pista:* Avalie a velocidade relativa entre o observador e a fonte (\( v_A = v_B \)).

## 11. Resumo e Pontos-Chave
- Efeito Doppler: variação aparente da frequência decorrente do movimento relativo entre fonte e observador.
- Aproximação: frequência aparente maior (\( f' > f \), som mais agudo, luz deslocada para o azul/Blueshift).
- Afastamento: frequência aparente menor (\( f' < f \), som mais grave, luz deslocada para o vermelho/Redshift).
- Equação: \( f' = f \left( \frac{v \pm v_O}{v \mp v_F} \right) \).
- Redshift galáctico comprova que o Universo está em expansão (Lei de Hubble).
- Aplicações: radares de velocidade, ultrassom Doppler arterial e previsão meteorológica.

## 12. Referências Bibliográficas
- HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física: Gravitação, Ondas e Termodinâmica*. Vol. 2. 10. ed. LTC, 2016.
- SEARS, F. W.; ZEMANSKY, M. W. *Física II: Termodinâmica e Ondas*. 14. ed. Pearson, 2016.
- TIPLER, Paul A.; MOSCA, Gene. *Física para Cientistas e Engenheiros*. Vol. 1. 6. ed. LTC, 2009.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Geral do Efeito Doppler Sonoro",
                syntax = """f' = f \\cdot \\left( \\frac{v \\pm v_O}{v \\mp v_F} \\right)""",
                description = "Expressão universal da frequência aparente percebida pelo observador em movimento relativo com a fonte sonora.",
                parametersInfo = """f': \\text{frequência percebida}; \\quad f: \\text{frequência emitida}; \\quad v: \\text{vel. som}; \\quad v_O, v_F: \\text{vel. observador e fonte}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Comprimento de Onda Aparente na Aproximação",
                syntax = """\\lambda' = \\frac{v - v_F}{f}""",
                description = "Encurtamento espacial real das frentes de onda na direção à frente de uma fonte sonora em movimento.",
                parametersInfo = """v: \\text{velocidade de propagação}; \\quad v_F: \\text{velocidade da fonte}; \\quad f: \\text{frequência própria}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Efeito Doppler Óptico Não-Relativístico",
                syntax = """\\frac{\\Delta f}{f} \\approx \\frac{v_{\\text{rel}}}{c} \\iff \\frac{\\Delta \\lambda}{\\lambda} \\approx \\frac{v_{\\text{rel}}}{c}""",
                description = "Desvio fracionário de frequência ou comprimento de onda para ondas eletromagnéticas astronômicas com velocidades subluminais.",
                parametersInfo = """v_{\\text{rel}}: \\text{velocidade relativa de aproximação ou afastamento}; \\quad c: \\text{velocidade da luz}"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Sirene em Aproximação Rápida",
                codeOrFormula = """f' = f \\cdot \\frac{v}{v - v_F} > f""",
                explanation = "A compressão das frentes de onda no sentido do movimento da fonte gera um aumento nítido na frequência percebida pelo observador estacionário."
            ),
            LessonExample(
                title = "Desvio Cosmológico para o Vermelho (Redshift)",
                codeOrFormula = """z = \\frac{\\lambda_{\\text{obs}} - \\lambda_{\\text{emit}}}{\\lambda_{\\text{emit}}} > 0 \\implies v_{\\text{afastamento}} = z \\cdot c""",
                explanation = "O estiramento das ondas de luz provenientes de galáxias distantes revela sua velocidade de afastamento cósmico."
            )
        ),
        goldTip = "Regra prática imediata para o ENEM: se há APROXIMAÇÃO entre fonte e observador, a frequência aparente é sempre MAIOR (som mais agudo). Se há AFASTAMENTO, a frequência aparente é sempre MENOR (som mais grave). Nunca erre essa relação conceitual preliminar antes de fazer qualquer cálculo!"
    )
}

