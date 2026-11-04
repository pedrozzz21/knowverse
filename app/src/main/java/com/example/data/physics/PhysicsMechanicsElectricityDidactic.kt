package com.example.data.physics

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor Didático Especializado de Física para Mecânica e Eletricidade.
 * Cobre as aulas:
 * - Módulo 1 (Mecânica): fis_m1_a3 a fis_m1_a10 (MCU, Leis de Newton, Máquinas Simples, Trabalho e Energia, Colisões, Estática, Hidrostática, Gravitação)
 * - Módulo 2 (Eletricidade e Magnetismo): fis_m2_a2 a fis_m2_a6 (Leis de Ohm, Circuitos, Eletrostática, Campo/Blindagem, Eletromagnetismo/Lenz)
 */
object PhysicsMechanicsElectricityDidactic {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        val entry = didacticRegistry[lessonId] ?: return null
        return CalculusDidacticBundle(
            theoryMarkdown = entry.theoryMarkdown,
            formulas = entry.formulas,
            solvedExamples = entry.solvedExamples,
            imageResId = null,
            goldTip = entry.goldTip,
            assetImagePath = entry.assetImagePath
        )
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise>? {
        return exerciseRegistry[lessonId]
    }

    data class PhysicsEntry(
        val theoryMarkdown: String,
        val formulas: List<FormulaOrSyntaxItem>,
        val solvedExamples: List<LessonExample>,
        val goldTip: String,
        val assetImagePath: String? = null
    )

    private val didacticRegistry: Map<String, PhysicsEntry> by lazy {
        mapOf(
            "fis_m1_a3" to PhysicsEntry(
                theoryMarkdown = """
                    # Movimento Circular Uniforme (MCU) e Acoplamento de Polias
                    
                    No Movimento Circular Uniforme, a trajetória é circular e o módulo da velocidade tangencial permanece constante, mas a **direção e o sentido da velocidade variam continuamente**, gerando uma aceleração centrípeta apontando para o centro.
                    
                    ### Grandezas Fundamentais
                    • **Período (\(T\)):** tempo necessário para completar uma volta inteira (em segundos).
                    • **Frequência (\(f\)):** número de voltas completadas por unidade de tempo (\(f = 1/T\), em Hertz).
                    • **Velocidade Angular (\(\omega\)):** taxa de variação do ângulo central: \(\omega = \frac{2\pi}{T} = 2\pi f\).
                    • **Velocidade Linear (\(v\)):** \(v = \omega \cdot R\).
                    
                    ### Acoplamento de Polias e Engrenagens no ENEM (Bicicleta)
                    1. **Acoplamento por Correia ou Contato Direto:** polias conectadas por corrente (coroa e catraca) possuem a **mesma velocidade linear**: \(v_1 = v_2 \implies R_1 \cdot f_1 = R_2 \cdot f_2\). A engrenagem menor gira com maior frequência!
                    2. **Acoplamento pelo Mesmo Eixo:** polias fixadas no mesmo eixo giram juntas, possuindo a **mesma velocidade angular e frequência**: \(\omega_1 = \omega_2 \implies f_1 = f_2\).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Aceleração Centrípeta",
                        syntax = "a_c = \\frac{v^2}{R} = \\omega^2 \\cdot R",
                        description = "Responsável por alterar a direção do vetor velocidade em curvas.",
                        parametersInfo = "Se o raio da curva dobra para a mesma velocidade, a aceleração centrípeta cai pela metade."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Acoplamento por Correia",
                        syntax = "R_A \\cdot f_A = R_B \\cdot f_B",
                        description = "Igualdade das velocidades periféricas tangenciais.",
                        parametersInfo = "Catraca menor (raio menor) gira com maior rotação/frequência."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Marchas de Bicicleta",
                        codeOrFormula = "R_{coroa} \\cdot f_{pedal} = R_{catraca} \\cdot f_{roda}",
                        explanation = "Para atingir a maior velocidade linear pedalando com frequência constante, o ciclista deve escolher a coroa de MAIOR raio acoplada à catraca de MENOR raio."
                    )
                ),
                goldTip = "Em curvas horizontais em pistas planas, a força centrípeta necessária para manter o veículo na pista é fornecida exclusivamente pelo ATRITO estático dos pneus com o asfalto!",
                assetImagePath = "images/fisica/cinematica.png"
            ),

            "fis_m1_a4" to PhysicsEntry(
                theoryMarkdown = """
                    # Leis de Newton e Forças da Natureza
                    
                    As três leis enunciadas por Isaac Newton regem a mecânica clássica:
                    
                    ### 1ª Lei de Newton (Princípio da Inércia)
                    Todo corpo permanece em repouso ou em movimento retilíneo uniforme (MRU) a menos que uma força resultante não nula atue sobre ele. A inércia de um corpo é medida exclusivamente por sua **massa**.
                    
                    ### 2ª Lei de Newton (Princípio Fundamental da Dinâmica)
                    A força resultante aplicada sobre um corpo de massa \(m\) produz uma aceleração com mesma direção e sentido:
                    \[ \vec{F}_R = m \cdot \vec{a} \]
                    
                    ### 3ª Lei de Newton (Princípio da Ação e Reação)
                    A toda força de ação corresponde uma força de reação de mesmo módulo, mesma direção e sentidos opostos, aplicadas em **corpos diferentes** (logo, **NUNCA se anulam**!).
                    
                    ### Forças de Atrito e Força Elástica
                    • **Atrito Estático Máximo:** \(F_{at,e} = \mu_e \cdot N\) (impede o início do deslizamento).
                    • **Atrito Cinético/Dinâmico:** \(F_{at,c} = \mu_c \cdot N\) (atua durante o escorregamento, sempre menor que o estático máximo).
                    • **Lei de Hooke (Molas):** \(F_{el} = k \cdot x\).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Segunda Lei de Newton",
                        syntax = "F_R = m \\cdot a",
                        description = "Força resultante proporcional à aceleração adquirida.",
                        parametersInfo = "1 Newton (N) = 1 kg * m/s²."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Força de Atrito",
                        syntax = "F_{at} = \\mu \\cdot N",
                        description = "Força paralela à superfície que se opõe à tendência de deslizamento.",
                        parametersInfo = "μ é o coeficiente adimensional de atrito e N é a força normal de compressão."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Pessoa Andando",
                        codeOrFormula = "F_{at,estático} \\text{ empurra a pessoa para frente}",
                        explanation = "Ao caminhar, o pé empurra o chão para trás (ação). O chão reage empurrando o pé para frente através da força de atrito estático (reação)."
                    )
                ),
                goldTip = "O par de ação e reação NUNCA pode ser Normal e Peso, porque ambas estão aplicadas no mesmo corpo! A reação do Peso está no centro da Terra."
            ),

            "fis_m1_a5" to PhysicsEntry(
                theoryMarkdown = """
                    # Máquinas Simples: Alavancas, Roldanas e Plano Inclinado
                    
                    Máquinas simples transmitem e transformam forças mecânicas para proporcionar **vantagem mecânica**, permitindo erguer ou movimentar cargas pesadas com menor esforço humano.
                    
                    ### Alavancas e Classificação (Ponto de Apoio O, Força Potente P, Força Resistente R)
                    • **Interfixa:** o apoio está entre a potência e a resistência (ex: tesoura, alicate, gangorra).
                    • **Inter-resistente:** a resistência está entre o apoio e a potência (ex: carrinho de mão, quebra-nozes). Sempre multiplica a força!
                    • **Interpotente:** a força potente é aplicada entre o apoio e a resistência (ex: pinça, braço humano segurando halteres). Prioriza precisão e velocidade.
                    
                    ### Roldanas (Polias)
                    • **Roldana Fixa:** apenas inverte o sentido da força; não reduz o módulo da força necessária (\(F = P\)).
                    • **Roldanas Móveis (Talha Exponencial):** cada roldana móvel reduz pela metade a força motora necessária:
                    \[ F = \frac{P}{2^n} \]
                    onde \(n\) é o número de roldanas móveis. Em contrapartida, o operador deve puxar \(2^n\) vezes mais comprimento de corda!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equilíbrio da Alavanca",
                        syntax = "F_P \\cdot d_P = F_R \\cdot d_R",
                        description = "Igualdade dos momentos das forças potente e resistente.",
                        parametersInfo = "Braço de alavanca maior exige menor força aplicada."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Talha Exponencial",
                        syntax = "F = \\frac{P}{2^n}",
                        description = "Força reduzida exponencialmente pelo número de polias móveis.",
                        parametersInfo = "O trabalho mecânico total realizado idealmente permanece o mesmo (conservação da energia)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Talha com 3 Roldanas Móveis",
                        codeOrFormula = "F = \\frac{800}{2^3} = \\frac{800}{8} = 100 \\text{ N}",
                        explanation = "Para erguer um motor de peso 800 N com 3 roldanas móveis, a força necessária na corda é de apenas 100 N."
                    )
                ),
                goldTip = "Nenhuma máquina simples economiza energia ou trabalho: ela apenas reduz a força necessária ao custo de aumentar proporcionalmente a distância percorrida!"
            ),

            "fis_m1_a6" to PhysicsEntry(
                theoryMarkdown = """
                    # Trabalho, Potência e Energia Mecânica
                    
                    ### Trabalho de uma Força Constante (\(W\) ou \(\tau\))
                    \[ \tau = F \cdot d \cdot \cos(\theta) \]
                    • Se a força for a favor do deslocamento (\(\theta = 0^\circ\)): trabalho motor (\(\tau > 0\)).
                    • Se a força se opõe ao deslocamento (\(\theta = 180^\circ\)): trabalho resistente (\(\tau < 0\), como o atrito).
                    • Se a força for perpendicular (\(\theta = 90^\circ\)): o trabalho é nulo (\(\tau = 0\), como a força centrípeta).
                    
                    ### Formas de Energia Mecânica (\(E_M\))
                    • **Energia Cinética:** \(E_c = \frac{m \cdot v^2}{2}\)
                    • **Energia Potencial Gravitacional:** \(E_{pg} = m \cdot g \cdot h\)
                    • **Energia Potencial Elástica:** \(E_{pe} = \frac{k \cdot x^2}{2}\)
                    
                    ### Princípio da Conservação da Energia Mecânica
                    Na ausência de forças dissipativas (atrito e resistência do ar):
                    \[ E_{M,\text{inicial}} = E_{M,\text{final}} \]
                    Havendo forças dissipativas: \(\tau_{\text{dissipativo}} = E_{M,\text{final}} - E_{M,\text{inicial}}\).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Conservação da Energia Mecânica",
                        syntax = "E_{cin} + E_{pot} = \\text{constante}",
                        description = "Transformação mútua entre energia cinética e potencial em sistemas conservativos.",
                        parametersInfo = "Aplicável em montanhas-russas, pêndulos e quedas livres."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Potência Mecânica",
                        syntax = "P = \\frac{\\tau}{\\Delta t} = F \\cdot v",
                        description = "Taxa temporal de realização de trabalho ou transferência energética.",
                        parametersInfo = "1 Watt (W) = 1 Joule por segundo (J/s)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Velocidade ao pé da Montanha-Russa",
                        codeOrFormula = "mgh = \\frac{mv^2}{2} \\implies v = \\sqrt{2gh}",
                        explanation = "Um carrinho solto do repouso a 20 m de altura sem atrito atinge a base com velocidade v = sqrt(2 * 10 * 20) = sqrt(400) = 20 m/s (72 km/h)."
                    )
                ),
                goldTip = "Em quedas livres a partir do repouso sem atrito, a velocidade final v = sqrt(2gh) independe completamente da massa do objeto!"
            ),

            "fis_m1_a7" to PhysicsEntry(
                theoryMarkdown = """
                    # Quantidade de Movimento, Teorema do Impulso e Colisões
                    
                    ### Quantidade de Movimento (Momento Linear)
                    Grandeza vetorial definida pelo produto da massa pela velocidade do corpo:
                    \[ \vec{Q} = m \cdot \vec{v} \]
                    
                    ### Teorema do Impulso
                    O impulso de uma força resultante atuando durante um intervalo \(\Delta t\) é igual à variação da quantidade de movimento:
                    \[ \vec{I} = \vec{F}_R \cdot \Delta t = \Delta \vec{Q} = m \cdot \vec{v}_f - m \cdot \vec{v}_i \]
                    Em gráficos \(F \times t\), a área sob a curva é numericamente igual ao módulo do impulso.
                    
                    ### Segurança no Trânsito (Airbags e Zonas de Deformação)
                    Para uma mesma variação de velocidade (\(\Delta Q\) constante), aumentar o tempo de frenagem (\(\Delta t\)) reduz drasticamente a força média de impacto sobre os passageiros: \(F = \frac{\Delta Q}{\Delta t}\).
                    
                    ### Conservação do Momento e Tipos de Colisão
                    Em qualquer sistema mecanicamente isolado: \(\vec{Q}_{\text{antes}} = \vec{Q}_{\text{depois}}\).
                    • **Perfeitamente Elástica:** conserva momento e energia cinética (\(e = 1\)).
                    • **Inelástica (Totalmente Inelástica):** os corpos colidem e seguem grudados com a mesma velocidade (\(v_1' = v_2'\)); há perda máxima de energia cinética na forma de deformação e calor (\(e = 0\)).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Teorema do Impulso",
                        syntax = "I = F_m \\cdot \\Delta t = \\Delta Q",
                        description = "Explica o princípio dos airbags e capacetes no amortecimento de choques.",
                        parametersInfo = "Maior tempo de impacto -> Menor força exercida sobre o corpo."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Colisão Perfeitamente Inelástica",
                        syntax = "m_1 v_1 + m_2 v_2 = (m_1 + m_2) \\cdot v'",
                        description = "Os corpos permanecem unidos após o impacto.",
                        parametersInfo = "Utilizado para calcular velocidades conjuntas pós-colisão no ENEM."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Colisão de Veículos no Cruzamento",
                        codeOrFormula = "m v_1 + m(0) = 2m \\cdot v' \\implies v' = v_1 / 2",
                        explanation = "Um carro de massa m a 20 m/s bate na traseira de um carro idêntico parado. Se engatarem os para-choques, a velocidade do conjunto após a batida é 10 m/s."
                    )
                ),
                goldTip = "A quantidade de movimento SEMPRE se conserva em qualquer choque mecânico isolado, seja ele elástico ou inelástico!"
            ),

            "fis_m1_a8" to PhysicsEntry(
                theoryMarkdown = """
                    # Estática do Ponto Material e de Corpos Extensos (Torque)
                    
                    Para que uma estrutura ou corpo extenso permaneça em equilíbrio estático completo, duas condições devem ser satisfeitas simultaneamente:
                    
                    ### 1. Equilíbrio de Translação
                    A soma vetorial de todas as forças aplicadas sobre o corpo deve ser nula:
                    \[ \sum \vec{F} = \vec{0} \]
                    
                    ### 2. Equilíbrio de Rotação (Momento de uma Força / Torque)
                    A soma dos momentos de todas as forças em relação a qualquer ponto de giro escolhido deve ser nula:
                    \[ \sum M = 0 \]
                    O módulo do torque em relação a um ponto fixo \(O\) é dado por:
                    \[ M = \pm F \cdot d \cdot \sin(\theta) \]
                    onde \(d\) é o braço de alavanca (distância perpendicular do ponto de apoio à linha de ação da força).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Momento de uma Força (Torque)",
                        syntax = "M = F \\cdot b = F \\cdot d \\cdot \\sin(\\theta)",
                        description = "Tendência de rotação que uma força produz em torno de um eixo.",
                        parametersInfo = "Forças cuja linha de ação passa exatamente pelo ponto de apoio não produzem torque (b = 0)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Chave de Roda para Trocar Pneu",
                        codeOrFormula = "M = F_1 \\cdot d_1 = F_2 \\cdot d_2",
                        explanation = "Uma chave de roda mais longa (maior d) permite gerar o mesmo torque para desapertar o parafuso com uma força muscular muito menor."
                    )
                ),
                goldTip = "Dica de ouro: ao escolher o ponto para calcular os torques, posicione o polo sobre o apoio que possui a força desconhecida que você NÃO quer calcular, pois o torque dela será zero!"
            ),

            "fis_m1_a9" to PhysicsEntry(
                theoryMarkdown = """
                    # Hidrostática: Densidade, Stevin, Pascal e Empuxo de Arquimedes
                    
                    ### 1. Massa Específica e Pressão
                    • Massa específica (densidade): \(\rho = \frac{m}{V}\).
                    • Pressão: \(P = \frac{F}{A}\) (\(1 \text{ Pa} = 1 \text{ N/m}^2\); \(1 \text{ atm} \approx 10^5 \text{ Pa} \approx 10 \text{ m de coluna d'água}\)).
                    
                    ### 2. Teorema de Stevin (Pressão Hidrostática)
                    A pressão no interior de um líquido homogêneo em repouso aumenta linearmente com a profundidade:
                    \[ P = P_{\text{atm}} + \rho \cdot g \cdot h \]
                    Dois pontos em um mesmo líquido e na mesma linha horizontal suportam pressões idênticas.
                    
                    ### 3. Princípio de Pascal (Prensas Hidráulicas)
                    O acréscimo de pressão aplicado a um ponto de um líquido ideal transmite-se integralmente a todos os pontos:
                    \[ \frac{F_1}{A_1} = \frac{F_2}{A_2} \]
                    
                    ### 4. Princípio de Arquimedes (Empuxo)
                    Todo corpo mergulhado em um fluido recebe uma força vertical para cima (Empuxo) igual ao peso do volume de fluido deslocado:
                    \[ E = \rho_{\text{líquido}} \cdot V_{\text{imerso}} \cdot g \]
                    • Se \(P > E\): o corpo afunda com aceleração.
                    • Se \(P = E\): o corpo flutua em equilíbrio (\(\rho_{\text{corpo}} / \rho_{\text{líquido}} = V_{\text{imerso}} / V_{\text{total}}\)).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Teorema de Stevin",
                        syntax = "\\Delta P = \\rho \\cdot g \\cdot \\Delta h",
                        description = "Variação de pressão proporcional à profundidade do fluido.",
                        parametersInfo = "A cada 10 metros de profundidade na água, a pressão hidrostática aumenta aproximadamente 1 atm."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Empuxo de Arquimedes",
                        syntax = "E = \\rho_{\\text{fluido}} \\cdot V_{\\text{imerso}} \\cdot g",
                        description = "Força vertical ascendente exercida pelo fluido sobre o corpo.",
                        parametersInfo = "Cuidado: a densidade na fórmula do empuxo é a do LÍQUIDO, nunca a do corpo!"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Iceberg Flutuando",
                        codeOrFormula = "\\frac{V_{\\text{imerso}}}{V_{\\text{total}}} = \\frac{\\rho_{\\text{gelo}}}{\\rho_{\\text{água}}} = \\frac{0,92}{1,0} = 92\\%",
                        explanation = "Como o gelo tem densidade 0,92 g/cm³ e a água salgada 1,03 g/cm³, cerca de 90% do volume total do iceberg permanece submerso."
                    )
                ),
                goldTip = "Em prensas hidráulicas, o pistão com área 10 vezes maior suporta uma força 10 vezes maior, mas desloca-se 10 vezes menos!"
            ),

            "fis_m1_a10" to PhysicsEntry(
                theoryMarkdown = """
                    # Gravitação Universal e Leis de Kepler
                    
                    ### Leis de Kepler sobre os Movimentos Planetários
                    1. **1ª Lei (Lei das Órbitas):** Os planetas orbitam o Sol em elipses, com o Sol ocupando um dos focos. O ponto mais próximo é o **Periélio** e o mais afastado é o **Afélio**.
                    2. **2ª Lei (Lei das Áreas):** O segmento imaginário Sol-planeta varre áreas iguais em tempos iguais (\(A_1 / \Delta t_1 = A_2 / \Delta t_2\)). Consequência: a velocidade orbital é **máxima no periélio** e **mínima no afélio**.
                    3. **3ª Lei (Lei dos Períodos):** O quadrado do período orbital (\(T\)) é proporcional ao cubo do raio médio da órbita (\(R\)):
                    \[ \frac{T^2}{R^3} = K \]
                    Planetas mais distantes do Sol possuem anos orbitais muito mais longos!
                    
                    ### Lei da Gravitação Universal de Newton
                    Dois corpos de massas \(M\) e \(m\) separados pela distância \(d\) atraem-se mutuamente com força de intensidade:
                    \[ F_G = G \cdot \frac{M \cdot m}{d^2} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Lei da Gravitação de Newton",
                        syntax = "F_G = G \\frac{M \\cdot m}{d^2}",
                        description = "Atração gravitacional entre duas massas pontuais.",
                        parametersInfo = "G é a constante gravitacional universal (6,67 * 10^-11 N m²/kg²)."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Terceira Lei de Kepler",
                        syntax = "\\frac{T_A^2}{R_A^3} = \\frac{T_B^2}{R_B^3}",
                        description = "Relaciona o período de translação e a distância média à estrela.",
                        parametersInfo = "Válido para quaisquer corpos em órbita do mesmo centro de atração."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Satélite Geoestacionário",
                        codeOrFormula = "T = 24 \\text{ horas}, \\quad \\text{órbita sobre a linha do Equador}",
                        explanation = "Um satélite geoestacionário possui período de translação igual ao período de rotação da Terra (24h) e gira no mesmo sentido, parecendo imóvel a um observador no solo."
                    )
                ),
                goldTip = "Astronautas na Estação Espacial Internacional (ISS) flutuam NÃO porque a gravidade é zero, mas porque eles estão em queda livre perpétua em órbita com a nave (estado de imponderabilidade)!"
            ),

            // ==========================================
            // MÓDULO 2 (Eletricidade)
            // ==========================================
            "fis_m2_a2" to PhysicsEntry(
                theoryMarkdown = """
                    # 1ª e 2ª Leis de Ohm e Associação de Resistores
                    
                    ### 1ª Lei de Ohm
                    Em condutores ôhmicos mantidos a temperatura constante, a diferença de potencial é diretamente proporcional à corrente:
                    \[ U = R \cdot I \]
                    
                    ### 2ª Lei de Ohm (Resistência de Fios)
                    A resistência de um fio condutor depende de sua composição geométrica e material:
                    \[ R = \rho \cdot \frac{L}{A} \]
                    • Fio mais longo (\(L\) maior) \(\implies\) maior resistência.
                    • Fio mais grosso (\(A\) maior) \(\implies\) menor resistência (menos aquecimento e menor queda de tensão).
                    
                    ### Associação de Resistores no ENEM
                    • **Em Série:** mesma corrente \(I\) atravessa todos os resistores. A ddp se divide: \(R_{eq} = R_1 + R_2 + R_3\).
                    • **Em Paralelo:** mesma ddp \(U\) aplicada em todos. A corrente se divide:
                    \[ \frac{1}{R_{eq}} = \frac{1}{R_1} + \frac{1}{R_2} \implies R_{eq} = \frac{R_1 \cdot R_2}{R_1 + R_2} \]
                    Nas instalações residenciais, todos os eletrodomésticos são ligados em **paralelo** para receberem a mesma voltagem (127V ou 220V) e funcionarem de forma independente!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Segunda Lei de Ohm",
                        syntax = "R = \\rho \\frac{L}{A}",
                        description = "Resistência proporcional ao comprimento e inversamente proporcional à área transversal.",
                        parametersInfo = "Duplicar o raio do fio quadruplica a área (A = pi*r²), reduzindo a resistência a 1/4."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Resistores Idênticos em Paralelo",
                        syntax = "R_{eq} = \\frac{R}{n}",
                        description = "Resistência equivalente de n resistores de valor R em paralelo.",
                        parametersInfo = "Ligar mais lâmpadas em paralelo diminui a resistência total e aumenta a corrente geral."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Associação Mista",
                        codeOrFormula = "R_p = \\frac{6 \\cdot 3}{6 + 3} = 2\\,\\Omega \\implies R_{eq} = 2 + 4 = 6\\,\\Omega",
                        explanation = "Dois resistores de 6 Ω e 3 Ω em paralelo equivalem a 2 Ω. Ligados em série com um resistor de 4 Ω, a resistência total do circuito é 6 Ω."
                    )
                ),
                goldTip = "Em circuitos em paralelo, se um aparelho queimar ou for desligado, os demais continuam funcionando normalmente na mesma voltagem!"
            ),

            "fis_m2_a3" to PhysicsEntry(
                theoryMarkdown = """
                    # Circuitos Elétricos, Dispositivos de Proteção e Medidores
                    
                    ### Dispositivos de Segurança no ENEM
                    • **Fusíveis e Disjuntores:** interrompem a passagem da corrente quando ela ultrapassa o limite seguro dimensionado, prevenindo incêndios por superaquecimento. Devem ser sempre instalados **em série no fio fase**!
                    • **Fio Terra:** desvia correntes de fuga para a terra, evitando choques elétricos caso a carcaça metálica do eletrodoméstico seja energizada.
                    
                    ### Instrumentos de Medição Elétrica
                    • **Amperímetro:** mede corrente elétrica. Deve ser ligado em **série** no ramo a ser medido e possuir **resistência interna ideal nula (\(R_A \approx 0\))** para não alterar o circuito.
                    • **Voltímetro:** mede diferença de potencial. Deve ser ligado em **paralelo** com o elemento a ser medido e possuir **resistência interna ideal infinita (\(R_V \approx \infty\))** para não desviar corrente.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Dimensionamento de Disjuntor",
                        syntax = "I = \\frac{P_{\\text{total}}}{U}",
                        description = "Cálculo da corrente nominal mínima necessária para proteger a instalação.",
                        parametersInfo = "O disjuntor deve ser escolhido ligeiramente acima da corrente de operação normal."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Chuveiro e Disjuntor",
                        codeOrFormula = "I = \\frac{6600 \\text{ W}}{220 \\text{ V}} = 30 \\text{ A}",
                        explanation = "Um chuveiro elétrico de 6.600 W em 220 V consome corrente de 30 A. O disjuntor adequado para esse circuito deve ser calibrado para 32 A."
                    )
                ),
                goldTip = "Nunca ligue um amperímetro em paralelo, pois sua baixíssima resistência causará um CURTO-CIRCUITO imediato!"
            ),

            "fis_m2_a4" to PhysicsEntry(
                theoryMarkdown = """
                    # Eletrostática: Cargas, Processos de Eletrização e Lei de Coulomb
                    
                    ### Princípios Fundamentais da Eletrostática
                    1. **Atração e Repulsão:** Cargas de mesmo sinal repelem-se; cargas de sinais opostos atraem-se.
                    2. **Conservação das Cargas:** Em um sistema eletricamente isolado, a soma algébrica das cargas permanece constante.
                    3. **Quantização da Carga:** Toda carga líquida é múltiplo inteiro da carga elementar: \(Q = n \cdot e\), onde \(e = 1,6 \cdot 10^{-19} \text{ C}\).
                    
                    ### Processos de Eletrização
                    • **Por Atrito:** dois corpos neutros de materiais diferentes são atritados. Um perde elétrons e o outro ganha; terminam com **cargas de mesmo módulo e sinais opostos**.
                    • **Por Contato:** condutores são colocados em contato. Há redistribuição de elétrons; terminam com **cargas de mesmo sinal**.
                    • **Por Indução:** aproxima-se um indutor carregado sem tocar, conecta-se o induzido à terra (aterramento) e desfaz-se o aterramento. O corpo induzido termina com **carga de sinal oposto ao indutor**.
                    
                    ### Lei de Coulomb (Força Elétrica)
                    \[ F = K_0 \cdot \frac{|Q_1| \cdot |Q_2|}{d^2} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Lei de Coulomb",
                        syntax = "F_e = K_0 \\frac{|q_1 \\cdot q_2|}{d^2}",
                        description = "Força eletrostática entre duas cargas puntiformes no vácuo.",
                        parametersInfo = "K_0 = 9 * 10^9 N m²/C². Se a distância d dobra, a força cai para 1/4."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Contato entre Esferas Idênticas",
                        codeOrFormula = "Q' = \\frac{Q_A + Q_B}{2} = \\frac{+6\\mu\\text{C} + (-2\\mu\\text{C})}{2} = +2\\mu\\text{C}",
                        explanation = "Duas esferas condutoras idênticas com cargas +6 μC e -2 μC entram em contato. A carga final de cada uma após a separação é +2 μC."
                    )
                ),
                goldTip = "Um corpo neutro pode ser atraído por um corpo eletrizado por indução eletrostática! Atração não prova que ambos estão eletrizados; apenas repulsão prova."
            ),

            "fis_m2_a5" to PhysicsEntry(
                theoryMarkdown = """
                    # Campo Elétrico, Potencial e Blindagem Eletrostática
                    
                    ### Campo Elétrico (\(\vec{E}\))
                    Região do espaço modificada pela presença de uma carga geradora \(Q\). Uma carga de prova positiva \(q > 0\) sofre uma força com mesmo sentido do campo; uma carga negativa sofre força no sentido oposto.
                    • Linhas de campo elétrico **nascem em cargas positivas** e **morrem em cargas negativas**.
                    
                    ### Potencial Elétrico e Trabalho
                    O trabalho para deslocar uma carga \(q\) entre dois pontos com diferença de potencial \(U = V_A - V_B\) é:
                    \[ \tau = q \cdot U \]
                    
                    ### Blindagem Eletrostática e Gaiola de Faraday
                    Em um condutor metálico em equilíbrio eletrostático:
                    1. Todo excesso de carga distribui-se exclusivamente na sua **superfície externa**.
                    2. No interior do condutor oco, o **campo elétrico resultante é rigorosamente ZERO** (\(E_{\text{interno}} = 0\)).
                    Isso explica por que aviões atingidos por raios não sofrem danos aos passageiros e carros protegem durante tempestades!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Relação Campo Uniforme e DDP",
                        syntax = "U = E \\cdot d",
                        description = "Válido para placas condutoras paralelas (capacitores planos).",
                        parametersInfo = "U em Volts, E em V/m ou N/C, d em metros."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Gaiola de Faraday",
                        syntax = "E_{\\text{interior}} = 0",
                        description = "Anulação interna do campo elétrico por redistribuição de cargas na casca condutora.",
                        parametersInfo = "Bloqueia campos eletrostáticos e atenua ondas eletromagnéticas."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Raio em Carro de Passeio",
                        codeOrFormula = "\\text{Estrutura metálica atua como Gaiola de Faraday}",
                        explanation = "Ao ser atingido por um raio, a carcaça de aço do carro conduz a descarga até o solo sem que o campo elétrico penetre na cabine, mantendo os ocupantes a salvo (e não pelo isolamento dos pneus de borracha)."
                    )
                ),
                goldTip = "A segurança dentro do carro durante uma tempestade de raios decorre da BLINDAGEM METÁLICA (Gaiola de Faraday), e não do isolamento dos pneus de borracha!"
            ),

            "fis_m2_a6" to PhysicsEntry(
                theoryMarkdown = """
                    # Eletromagnetismo, Campo Magnético e Indução de Faraday
                    
                    ### Experimento de Oersted
                    Uma corrente elétrica que percorre um condutor produz ao seu redor um **campo magnético** (\(\vec{B}\)). Corrente gera campo magnético!
                    
                    ### Força Magnética sobre Cargas Móveis (Força de Lorentz)
                    Uma partícula com carga \(q\) lançada com velocidade \(v\) em um campo magnético \(B\) sofre força:
                    \[ F_{\text{mag}} = |q| \cdot v \cdot B \cdot \sin(\theta) \]
                    Orientada pela **Regra da Mão Direita**. Como a força magnética é sempre perpendicular à velocidade, ela **NÃO realiza trabalho mecânico** (\(\tau = 0\)) e apenas altera a direção do movimento (MCU).
                    
                    ### Lei de Faraday e Lei de Lenz (Indução Eletromagnética)
                    Sempre que houver **variação do fluxo magnético** (\(\Delta \Phi\)) através de uma espira ou circuito fechado, surge uma corrente elétrica induzida:
                    \[ \mathcal{E} = -\frac{\Delta \Phi}{\Delta t} \]
                    • **Lei de Lenz:** O sentido da corrente induzida é tal que o campo magnético criado por ela **opõe-se à variação do fluxo** que a originou (conservação da energia). É a base do funcionamento de hidrelétricas, turbinas eólicas e fogões de indução!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Fluxo Magnético",
                        syntax = "\\Phi = B \\cdot A \\cdot \\cos(\\theta)",
                        description = "Quantidade de linhas de campo que atravessam uma área de superfície.",
                        parametersInfo = "Unidade no SI: Weber (Wb) = Tesla * metro quadrado."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Lei de Faraday-Lenz",
                        syntax = "\\mathcal{E} = -\\frac{\\Delta \\Phi}{\\Delta t}",
                        description = "Força eletromotriz induzida pela variação do fluxo no tempo.",
                        parametersInfo = "Base operacional de usinas elétricas, alternadores de carros e captadores de guitarra."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Usina Hidrelétrica",
                        codeOrFormula = "\\text{Energia Potencial} \\to \\text{Cinética} \\to \\text{Indução Eletromagnética} \\to \\text{Elétrica}",
                        explanation = "A água represada cai girando as pás da turbina, que rotacionam imãs no interior de bobinas de cobre, gerando corrente elétrica alternada por indução de Faraday."
                    )
                ),
                goldTip = "Para haver corrente induzida, o fluxo magnético TEM que VARIAR! Se o ímã ficar parado dentro da bobina, o fluxo é constante e a corrente induzida é rigorosamente zero."
            )
        )
    }

    private val exerciseRegistry: Map<String, List<MathExercise>> by lazy {
        mapOf(
            "fis_m1_a3" to listOf(
                MathExercise(
                    id = "ex_fis_m1_a3_1",
                    title = "ENEM: Transmissão por Corrente de Bicicleta",
                    problemStatement = "Uma bicicleta possui coroa dianteira com raio de 12 cm acoplada por uma corrente inextensível a uma catraca traseira com raio de 4 cm. Se o ciclista pedala fazendo a coroa girar com frequência constante de 1,5 Hz (90 rpm), qual é a frequência de rotação da catraca (e consequentemente da roda traseira)?",
                    options = listOf("1,5 Hz", "3,0 Hz", "4,5 Hz", "6,0 Hz", "9,0 Hz"),
                    correctIndex = 2,
                    hint = "Como as polias estão acopladas por correia/corrente, a velocidade linear periférica é a mesma: R_coroa * f_coroa = R_catraca * f_catraca.",
                    stepByStepSolution = "v_coroa = v_catraca -> R_1 * f_1 = R_2 * f_2\n12 cm * 1,5 Hz = 4 cm * f_2 -> 18 = 4 * f_2 -> f_2 = 18 / 4 = 4,5 Hz."
                )
            ),

            "fis_m1_a4" to listOf(
                MathExercise(
                    id = "ex_fis_m1_a4_1",
                    title = "ENEM: Arrancada e Coeficiente de Atrito",
                    problemStatement = "Um bloco de massa 20 kg repousa sobre uma superfície horizontal áspera cujo coeficiente de atrito estático com o bloco é μ_e = 0,40 e o coeficiente cinético é μ_c = 0,30. Adotando g = 10 m/s², uma pessoa aplica uma força horizontal constante de 70 N sobre o bloco. O módulo da força de atrito que atua no bloco nessas condições é:",
                    options = listOf("60 N", "70 N", "80 N", "100 N", "200 N"),
                    correctIndex = 1,
                    hint = "Primeiro calcule o atrito estático MÁXIMO (F_at,max = μ_e * N). Se a força aplicada for menor que esse valor, o bloco não se move e o atrito estático iguala a força aplicada!",
                    stepByStepSolution = "N = m * g = 20 * 10 = 200 N.\nF_at,estático_máx = 0,40 * 200 = 80 N.\nComo a força aplicada F = 70 N é menor que 80 N, o bloco permanece em repouso e a força de atrito estático equilibra exatamente a força aplicada: F_at = 70 N."
                )
            ),

            "fis_m1_a5" to listOf(
                MathExercise(
                    id = "ex_fis_m1_a5_1",
                    title = "ENEM: Vantagem Mecânica em Carrinho de Mão",
                    problemStatement = "Um operário transporta sacos de cimento em um carrinho de mão (alavanca inter-resistente). O centro de gravidade da carga de 60 kg (peso 600 N) fica a 0,5 m do eixo da roda (ponto de apoio). As mãos do operário seguram os braços do carrinho a 1,5 m do eixo da roda. A força vertical mínima que o operário deve aplicar para levantar o carrinho é de:",
                    options = listOf("150 N", "200 N", "300 N", "400 N", "600 N"),
                    correctIndex = 1,
                    hint = "Aplique o equilíbrio de momentos (torques) em relação ao ponto de apoio: F_operário * d_operário = Peso * d_peso.",
                    stepByStepSolution = "F * 1,5 = 600 * 0,5 -> 1,5 F = 300 -> F = 300 / 1,5 = 200 N."
                )
            ),

            "fis_m1_a6" to listOf(
                MathExercise(
                    id = "ex_fis_m1_a6_1",
                    title = "ENEM: Usina Hidrelétrica e Potência Mecânica",
                    problemStatement = "Em uma usina hidrelétrica, a água cai de uma represa com desnível vertical efetivo de 50 metros com uma vazão volumétrica de 20 m³/s. Sabendo que a densidade da água é 1.000 kg/m³, a aceleração da gravidade é 10 m/s² e o rendimento global da turbina/gerador é de 80%, a potência elétrica útil gerada é de:",
                    options = listOf("6 MW", "8 MW", "10 MW", "12 MW", "16 MW"),
                    correctIndex = 1,
                    hint = "Massa por segundo = 20.000 kg/s. Potência teórica = (m/Δt) * g * h. Multiplique pelo rendimento de 0,80.",
                    stepByStepSolution = "Massa de água por segundo: 20 m³/s * 1000 kg/m³ = 20.000 kg/s.\nPotência total da queda d'água = 20.000 * 10 * 50 = 10.000.000 W = 10 MW.\nPotência útil = 10 MW * 0,80 = 8 MW."
                )
            ),

            "fis_m1_a7" to listOf(
                MathExercise(
                    id = "ex_fis_m1_a7_1",
                    title = "ENEM: Eficácia do Cinto de Segurança e Airbag",
                    problemStatement = "Durante um teste de colisão frontal contra uma barreira fixa, um boneco 'dummy' de 70 kg sofre uma desaceleração que o leva de 72 km/h (20 m/s) ao repouso. Graças ao airbag e ao cinto retrátil, a duração da desaceleração foi de 0,14 s. A intensidade média da força retardadora suportada pelo boneco foi de:",
                    options = listOf("5.000 N", "10.000 N", "14.000 N", "20.000 N", "28.000 N"),
                    correctIndex = 1,
                    hint = "Aplique o Teorema do Impulso: F_média * Δt = m * Δv.",
                    stepByStepSolution = "ΔQ = m * (v_f - v_i) = 70 * (0 - 20) = -1.400 kg·m/s.\n|Impulso| = F_média * Δt -> 1.400 = F_média * 0,14 -> F_média = 1.400 / 0,14 = 10.000 N."
                )
            ),

            "fis_m1_a8" to listOf(
                MathExercise(
                    id = "ex_fis_m1_a8_1",
                    title = "ENEM: Equilíbrio de Barra Articulada em Andaime",
                    problemStatement = "Uma prancha de madeira homogênea de 4,0 m de comprimento e peso 200 N repousa sobre dois apoios A e B, localizados nas extremidades esquerda e direita, respectivamente. Um pintor de peso 800 N fica em pé sobre a prancha a 1,0 m da extremidade A. A força normal de apoio exercida pelo suporte B sobre a prancha é de:",
                    options = listOf("200 N", "300 N", "400 N", "500 N", "600 N"),
                    correctIndex = 1,
                    hint = "Calcule o equilíbrio de torques com polo no apoio A: N_B * 4 = Peso_barra * 2 + Peso_pintor * 1.",
                    stepByStepSolution = "A barra homogênea tem centro de gravidade no meio (2,0 m de A).\nΣ M_A = 0 -> N_B * 4 - (200 N * 2,0 m) - (800 N * 1,0 m) = 0\n4 N_B = 400 + 800 = 1.200 -> N_B = 1.200 / 4 = 300 N."
                )
            ),

            "fis_m1_a9" to listOf(
                MathExercise(
                    id = "ex_fis_m1_a9_1",
                    title = "ENEM: Flutuação de Balsa e Empuxo",
                    problemStatement = "Uma balsa de fundo chato com área de base igual a 20 m² flutua em águas calmas de um rio (densidade da água = 1.000 kg/m³). Ao ser carregada com caminhões pesados, a balsa afunda 0,30 m adicionais na água sem tombar. Considerando g = 10 m/s², qual é a massa total da carga adicional de caminhões colocada sobre a balsa?",
                    options = listOf("3.000 kg", "4.500 kg", "6.000 kg", "9.000 kg", "12.000 kg"),
                    correctIndex = 2,
                    hint = "O empuxo adicional equilibra o peso da carga: ΔE = ΔP -> ρ_água * ΔV * g = m_carga * g.",
                    stepByStepSolution = "Volume adicional submerso: ΔV = Área * Δh = 20 m² * 0,30 m = 6 m³.\nΔE = ρ * ΔV * g = 1.000 * 6 * 10 = 60.000 N.\nPeso da carga = 60.000 N -> m_carga = 60.000 / 10 = 6.000 kg."
                )
            ),

            "fis_m1_a10" to listOf(
                MathExercise(
                    id = "ex_fis_m1_a10_1",
                    title = "ENEM: Terceira Lei de Kepler e Órbita de Satélite",
                    problemStatement = "Dois satélites artificiais, A e B, orbitam circularmente a Terra. O satélite A possui raio de órbita R e período de revolução de 2 horas. O satélite B é posicionado em uma órbita mais alta com raio igual a 4R. De acordo com a 3ª Lei de Kepler (T²/R³ = constante), qual é o período de revolução do satélite B?",
                    options = listOf("4 horas", "8 horas", "12 horas", "16 horas", "32 horas"),
                    correctIndex = 3,
                    hint = "T_B² / (4R)³ = T_A² / R³ -> T_B² / 64 = 4 / 1 -> T_B² = 256.",
                    stepByStepSolution = "T_B² / (4R)³ = T_A² / R³\nT_B² / 64 = 2² / 1 = 4\nT_B² = 64 * 4 = 256 -> T_B = √256 = 16 horas."
                )
            ),

            // MÓDULO 2
            "fis_m2_a2" to listOf(
                MathExercise(
                    id = "ex_fis_m2_a2_1",
                    title = "ENEM: Resistência de Cabo Subterrâneo (2ª Lei de Ohm)",
                    problemStatement = "Um técnico de eletricidade precisa substituir uma linha de cobre de comprimento L e seção reta circular de diâmetro D por outra de mesmo material e mesmo comprimento L, mas que apresente apenas a metade da resistência elétrica original. Para isso, o diâmetro da nova fiação deve ser multiplicado pelo fator:",
                    options = listOf("√2", "2", "2√2", "4", "1/2"),
                    correctIndex = 0,
                    hint = "R = ρ * L / A. Para reduzir a resistência pela metade, a área A deve dobrar. Como A = π * (D/2)², duplicar a área exige multiplicar o diâmetro por √2.",
                    stepByStepSolution = "R_novo = R / 2 -> A_novo = 2 * A_antigo.\nπ (D_novo)² / 4 = 2 * [π (D_antigo)² / 4] -> (D_novo)² = 2 (D_antigo)² -> D_novo = D_antigo * √2."
                )
            ),

            "fis_m2_a3" to listOf(
                MathExercise(
                    id = "ex_fis_m2_a3_1",
                    title = "ENEM: Dimensionamento de Fusível de Proteção",
                    problemStatement = "Em uma bancada de laboratório com tensão de 120 V, deseja-se ligar simultaneamente um aquecedor de 600 W, duas lâmpadas de 60 W cada e um ferro elétrico de 720 W. Todos os aparelhos são ligados em paralelo e protegidos por um único fusível. O valor comercial mínimo de corrente que esse fusível deve suportar sem queimar é de:",
                    options = listOf("5 A", "10 A", "12 A", "15 A", "20 A"),
                    correctIndex = 2,
                    hint = "Calcule a potência total somando todos os aparelhos e determine a corrente total com P = U * I.",
                    stepByStepSolution = "P_total = 600 + 2*60 + 720 = 600 + 120 + 720 = 1.440 W.\nI_total = P_total / U = 1.440 W / 120 V = 12 A.\nO fusível deve suportar pelo menos 12 A."
                )
            ),

            "fis_m2_a4" to listOf(
                MathExercise(
                    id = "ex_fis_m2_a4_1",
                    title = "ENEM: Repulsão Eletrostática e Lei de Coulomb",
                    problemStatement = "Duas partículas eletrizadas com cargas idênticas positivas Q encontram-se fixas no vácuo a uma distância d, repelindo-se com força de intensidade F. Se a distância entre elas for triplicada (3d) e o valor de cada carga for dobrado (2Q), a nova força eletrostática entre as partículas passará a ser de:",
                    options = listOf("F/9", "4F/9", "2F/3", "4F/3", "6F"),
                    correctIndex = 1,
                    hint = "Substitua 2Q e 3d na fórmula de Coulomb: F' = k * (2Q * 2Q) / (3d)².",
                    stepByStepSolution = "F_inicial = k * Q² / d².\nF_final = k * (2Q)² / (3d)² = k * 4Q² / (9d²) = (4/9) * (k Q² / d²) = 4F / 9."
                )
            ),

            "fis_m2_a5" to listOf(
                MathExercise(
                    id = "ex_fis_m2_a5_1",
                    title = "ENEM: Blindagem Eletrostática em Telefonia Celular",
                    problemStatement = "Um estudante coloca seu telefone celular dentro de uma caixa de biscoitos inteiramente metálica e fecha bem a tampa de metal. Ao ligar de outro aparelho para o telefone contido na caixa, ouve a mensagem de que o número está fora de área de cobertura. O celular não recebeu o sinal eletromagnético porque:",
                    options = listOf(
                        "O metal absorveu a bateria do celular.",
                        "As paredes metálicas atuaram como blindagem eletrostática (Gaiola de Faraday).",
                        "A tampa fechada impediu a passagem do ar, inviabilizando o som da ligação.",
                        "O magnetismo do metal inverteu a polaridade da antena receptora.",
                        "O campo gravitacional dentro da caixa impediu a oscilação das ondas de rádio."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se do comportamento de condutores metálicos fechados: as cargas livres na superfície reorganizam-se e anulam o campo elétrico no interior.",
                    stepByStepSolution = "A caixa metálica atua como uma Gaiola de Faraday, bloqueando a penetração de ondas eletromagnéticas de radiofrequência no seu interior (Alternativa B)."
                )
            ),

            "fis_m2_a6" to listOf(
                MathExercise(
                    id = "ex_fis_m2_a6_1",
                    title = "ENEM: Frenagem Magnética e Lei de Lenz",
                    problemStatement = "Um forte ímã de neodímio é solto verticalmente dentro de um longo tubo oco de cobre (material não ferromagnético condutor). Observa-se que, após instantes, o ímã atinge uma velocidade terminal lenta e constante, caindo muito mais devagar do que se caísse no ar livre. Isso ocorre porque:",
                    options = listOf(
                        "O cobre atrai magneticamente o ímã como o ferro faria.",
                        "O ar dentro do tubo sofre compressão adiabática que segura o ímã.",
                        "A variação de fluxo magnético induz correntes no cobre que geram um campo contrário à queda (Lei de Lenz).",
                        "O campo gravitacional da Terra é anulado pelo cobre condutor.",
                        "A carga eletrostática acumulada no ímã repele os elétrons do tubo."
                    ),
                    correctIndex = 2,
                    hint = "A queda do ímã cria variação de fluxo magnético nas paredes do tubo, gerando correntes circulares de Foucault que produzem força retardadora ascendente.",
                    stepByStepSolution = "Pela Lei de Faraday-Lenz, a descida do ímã altera o fluxo magnético no tubo de cobre, induzindo correntes parasitas (de Foucault). Essas correntes geram um campo magnético que se opõe ao movimento da fonte indutora, freando a queda (Alternativa C)."
                )
            )
        )
    }
}
