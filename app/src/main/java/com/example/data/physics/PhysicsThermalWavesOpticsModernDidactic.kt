package com.example.data.physics

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor Didático Especializado de Física para Termologia, Ondulatória, Óptica e Física Moderna.
 * Cobre as aulas:
 * - Módulo 3 (Termologia): fis_m3_a1 a fis_m3_a5
 * - Módulo 4 (Ondulatória): fis_m4_a2 a fis_m4_a5
 * - Módulo 5 (Óptica): fis_m5_a1 a fis_m5_a5
 * - Módulo 6 (Física Moderna): fis_m6_a1 a fis_m6_a3
 */
object PhysicsThermalWavesOpticsModernDidactic {

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
            // ==========================================
            // MÓDULO 3: TERMOLOGIA
            // ==========================================
            "fis_m3_a1" to PhysicsEntry(
                theoryMarkdown = """
                    # Termometria e Escalas Termométricas
                    
                    **Temperatura** é a medida macroscópica do grau de agitação térmica média das partículas (átomos e moléculas) de um corpo. Já o **calor** é a energia térmica em trânsito espontâneo do corpo de maior temperatura para o de menor temperatura.
                    
                    ### As Três Escalas Principais
                    • **Celsius (°C):** baseada no gelo fundente (0 °C) e na água em ebulição (100 °C) a 1 atm.
                    • **Fahrenheit (°F):** escala anglo-saxônica (32 °F para o gelo e 212 °F para o vapor).
                    • **Kelvin (K):** escala termodinâmica absoluta do SI. O zero absoluto (\(0 \text{ K} = -273,15^\circ\text{C}\)) é o estado teórico de mínima agitação molecular.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação de Conversão de Escalas",
                        syntax = """\\frac{T_C}{5} = \\frac{T_F - 32}{9} = \\frac{T_K - 273}{5}""",
                        description = "Relação de proporcionalidade entre termômetros Celsius, Fahrenheit e Kelvin.",
                        parametersInfo = "Uma variação de 1 °C equivale exatamente à variação de 1 K."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Febre Humana em Fahrenheit",
                        codeOrFormula = """\\frac{38}{5} = \\frac{T_F - 32}{9} \\implies T_F = 100,4^\\circ\\text{F}""",
                        explanation = "Uma pessoa com febre de 38 °C nos Estados Unidos tem sua temperatura lida como 100,4 °F."
                    )
                ),
                goldTip = "Cuidado com variações de temperatura: uma variação de temperatura de ΔT = 10 °C equivale a uma variação de 10 K (e NÃO 283 K)!"
            ),

            "fis_m3_a2" to PhysicsEntry(
                theoryMarkdown = """
                    # Dilatação Térmica dos Sólidos e Líquidos
                    
                    Quando a temperatura de um material aumenta, a amplitude de vibração de seus átomos cresce, aumentando o distanciamento interatômico médio e provocando expansão dimensional.
                    
                    ### Tipos de Dilatação em Sólidos
                    • **Linear (comprimento):** \(\Delta L = L_0 \cdot \alpha \cdot \Delta T\)
                    • **Superficial (área):** \(\Delta A = A_0 \cdot \beta \cdot \Delta T\) (\(\beta = 2\alpha\))
                    • **Volumétrica (volume):** \(\Delta V = V_0 \cdot \gamma \cdot \Delta T\) (\(\gamma = 3\alpha\))
                    
                    ### Comportamento Anômalo da Água no ENEM
                    Entre 0 °C e 4 °C, a água líquida sofre uma contração de volume ao ser aquecida (sua densidade é máxima a 4 °C). Essa propriedade é vital para a biosfera aquática: rios e lagos congelam apenas na superfície, permitindo a vida subaquática em regiões polares!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Dilatação Linear",
                        syntax = """\\Delta L = L_0 \\cdot \\alpha \\cdot \\Delta T""",
                        description = "Expansão longitudinal de barras, vigas e trilhos metálicos.",
                        parametersInfo = "Juntas de dilatação em pontes e trilhos evitam empenamentos mecânicos."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Dilatação de Líquidos em Recipientes",
                        syntax = """\\Delta V_{\\text{real}} = \\Delta V_{\\text{aparente}} + \\Delta V_{\\text{recipiente}}""",
                        description = "O líquido se expande mais do que o frasco de vidro que o contém.",
                        parametersInfo = "γ_real = γ_aparente + γ_frasco."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Lâmina Bimetálica",
                        codeOrFormula = """\\text{Curva-se para o lado do metal de menor } \\alpha""",
                        explanation = "Em termostatos de ferros elétricos, duas tiras de metais com coeficientes diferentes são soldadas. Ao aquecer, a tira de maior α estica mais, encurvando o conjunto e abrindo o circuito."
                    )
                ),
                goldTip = "Se uma placa metálica com um furo circular no centro for aquecida, o furo AUMENTA de tamanho exatamente como se fosse feito do mesmo material!"
            ),

            "fis_m3_a3" to PhysicsEntry(
                theoryMarkdown = """
                    # Calorimetria: Calor Sensível, Calor Latente e Trocas de Calor
                    
                    ### 1. Calor Sensível (Variação de Temperatura sem Mudança de Estado)
                    \[ Q_s = m \cdot c \cdot \Delta T \]
                    • \(c\): **calor específico sensível** (em cal/g·°C ou J/kg·K).
                    • A água possui um alto calor específico (\(c_{\text{água}} = 1,0 \text{ cal/g}^\circ\text{C}\)), atuando como um poderoso moderador térmico do clima litorâneo (brisa marítima).
                    
                    ### 2. Calor Latente (Mudança de Estado Físico com Temperatura Constante)
                    \[ Q_l = m \cdot L \]
                    Durante a fusão ou ebulição de uma substância pura a pressão constante, a temperatura permanece estritamente invariável.
                    
                    ### 3. Princípio Geral das Trocas de Calor
                    Em um calorímetro ideal isolado termicamente:
                    \[ \sum Q = Q_{\text{cedido}} + Q_{\text{recebido}} = 0 \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação Fundamental da Calorimetria",
                        syntax = """Q = m \\cdot c \\cdot \\Delta T""",
                        description = "Mnemônico popular: 'Que macete'.",
                        parametersInfo = "Massa m em gramas ou kg; c em cal/g°C ou J/kg°C."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação do Calor Latente",
                        syntax = """Q = m \\cdot L""",
                        description = "Mnemônico: 'Que moleza'.",
                        parametersInfo = "L_fusão do gelo = 80 cal/g; L_vaporização da água = 540 cal/g."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Potência Térmica de Ebulidor",
                        codeOrFormula = """P = \\frac{Q}{\\Delta t} = \\frac{m \\cdot c \\cdot \\Delta T}{\\Delta t}""",
                        explanation = "Para aquecer 500 g de água de 20 °C para 80 °C em 5 minutos (300 s): Q = 500 * 1 * 60 = 30.000 cal = 126.000 J (1 cal = 4,2 J). Potência = 126.000 / 300 = 420 W."
                    )
                ),
                goldTip = "Suar resfria o corpo humano porque a água na pele consome calor latente de vaporização (540 cal/g) retirado do próprio corpo ao evaporar!"
            ),

            "fis_m3_a4" to PhysicsEntry(
                theoryMarkdown = """
                    # Processos de Propagação do Calor: Condução, Convecção e Irradiação
                    
                    O calor pode se propagar por três mecanismos físicos distintos:
                    
                    ### 1. Condução Térmica
                    Transferência de energia partícula a partícula por colisões atômicas. Ocorre principalmente em **sólidos**. Metais são excelentes condutores térmicos devido aos seus elétrons livres. Não ocorre no vácuo.
                    
                    ### 2. Convecção Térmica
                    Transporte de matéria aquecida gerado por **diferenças de densidade**. Ocorre exclusivamente em **fluidos** (líquidos e gases). O fluido quente menos denso sobe e o fluido frio mais denso desce, formando correntes de convecção (ex: ar-condicionado instalado no alto, congelador em cima na geladeira, brisa marítima). Não ocorre no vácuo!
                    
                    ### 3. Irradiação Térmica
                    Propagação por **ondas eletromagnéticas** (principalmente radiação infravermelha). É o **ÚNICO processo que ocorre no vácuo**! É como a energia solar chega à Terra. Superfícies escuras e foscas absorvem e emitem radiação com máxima eficiência; superfícies claras e espelhadas refletem a radiação.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Lei de Fourier da Condução",
                        syntax = """\\Phi = \\frac{k \\cdot A \\cdot (T_1 - T_2)}{e}""",
                        description = "Fluxo de calor através de uma parede de espessura e e condutividade térmica k.",
                        parametersInfo = "Paredes duplas e isolantes (isopor, fibra de vidro) reduzem o fluxo térmico."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Garrafa Térmica (Termo de Dewar)",
                        codeOrFormula = "\\text{Vácuo barra condução e convecção; espelhamento barra irradiação}",
                        explanation = "A garrafa térmica possui parede dupla de vidro com vácuo intermediário (que anula a condução e a convecção) e superfícies espelhadas (que refletem a irradiação infravermelha de volta)."
                    )
                ),
                goldTip = "O ar-condicionado deve ser instalado no alto porque o ar frio, sendo mais denso, desse naturalmente, criando convecção e refrigerando uniformemente o ambiente!"
            ),

            "fis_m3_a5" to PhysicsEntry(
                theoryMarkdown = """
                    # Termodinâmica: 1ª Lei, Transformações Gasosas e Ciclo de Carnot
                    
                    ### Equação de Estado dos Gases Ideais (Clapeyron)
                    \[ P \cdot V = n \cdot R \cdot T \]
                    (com temperatura sempre expressa na escala absoluta Kelvin!).
                    
                    ### Trabalho em um Gás (\(W\))
                    Em transformações isobáricas: \(W = P \cdot \Delta V\).
                    • Expansão (\(\Delta V > 0\)): o gás realiza trabalho sobre o meio (\(W > 0\)).
                    • Compressão (\(\Delta V < 0\)): o meio realiza trabalho sobre o gás (\(W < 0\)).
                    
                    ### 1ª Lei da Termodinâmica (Conservação da Energia)
                    \[ \Delta U = Q - W \]
                    onde \(\Delta U = \frac{3}{2} n R \Delta T\) é a variação da energia interna (depende apenas da temperatura).
                    
                    ### 2ª Lei da Termodinâmica e Ciclo de Carnot
                    É impossível construir uma máquina térmica que, operando em ciclos, converta integralmente todo o calor recebido em trabalho útil (o rendimento nunca atinge 100%).
                    O rendimento máximo teórico para qualquer máquina térmica entre duas fontes é o **Rendimento de Carnot**:
                    \[ \eta_{\text{Carnot}} = 1 - \frac{T_{\text{fria}}}{T_{\text{quente}}} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Primeira Lei da Termodinâmica",
                        syntax = """\\Delta U = Q - W""",
                        description = "Balanço energético de calor, trabalho e energia interna.",
                        parametersInfo = "Em ciclos fechados, ΔU = 0, logo o trabalho líquido iguala o calor líquido: W = Q."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Rendimento de Carnot",
                        syntax = """\\eta = 1 - \\frac{T_F}{T_Q}""",
                        description = "Limite termodinâmico inatingível por máquinas reais.",
                        parametersInfo = "As temperaturas T_F e T_Q DEVEM ser expressas em KELVIN!"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Rendimento Máximo de Motor",
                        codeOrFormula = """\\eta = 1 - \\frac{300 \\text{ K}}{600 \\text{ K}} = 1 - 0,5 = 50\\%""",
                        explanation = "Uma máquina térmica opera entre fonte fria a 27 °C (300 K) e quente a 327 °C (600 K). Seu rendimento máximo jamais ultrapassará 50%."
                    )
                ),
                goldTip = "Nunca calcule o rendimento de Carnot em graus Celsius! Sempre some 273 para obter as temperaturas absolutas em Kelvin antes de dividir!"
            ),

            // ==========================================
            // MÓDULO 4: ONDULATÓRIA
            // ==========================================
            "fis_m4_a2" to PhysicsEntry(
                theoryMarkdown = """
                    # Fenômenos Ondulatórios: Reflexão, Refração e Difração
                    
                    ### 1. Reflexão
                    A onda atinge uma barreira e retorna ao meio original. A velocidade, o comprimento de onda e a frequência permanecem inalterados.
                    • Em cordas com extremidade fixa: a reflexão ocorre com inversão de fase (180°).
                    • Em extremidade livre: a reflexão ocorre sem inversão de fase.
                    
                    ### 2. Refração
                    A onda muda de meio de propagação. Sua **frequência permanece rigorosamente constante**, mas sua velocidade (\(v\)) e comprimento de onda (\(\lambda\)) alteram-se na mesma proporção:
                    \[ \frac{v_1}{\lambda_1} = \frac{v_2}{\lambda_2} = f \]
                    
                    ### 3. Difração
                    Capacidade da onda de **contornar obstáculos ou fendas** ao passar por eles. A difração é máxima quando a largura da abertura (\(d\)) é da mesma ordem de grandeza do comprimento de onda:
                    \[ d \approx \lambda \]
                    Isso explica por que conseguimos ouvir uma conversa através de uma fresta de porta (o som tem \(\lambda \sim 1 \text{ m}\)), mas não conseguimos ver as pessoas (a luz visível tem \(\lambda \sim 500 \text{ nm}\)).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Condição Ótima de Difração",
                        syntax = """\\lambda \\approx d""",
                        description = "Comprimento de onda comparável à fenda.",
                        parametersInfo = "Quanto maior o comprimento de onda, maior a capacidade de difração."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Sinal de Wi-Fi e Sombras de Parede",
                        codeOrFormula = """\\lambda = \\frac{v}{f} = \\frac{3 \\cdot 10^8 \\text{ m/s}}{2,4 \\cdot 10^9 \\text{ Hz}} = 0,125 \\text{ m} = 12,5 \\text{ cm}""",
                        explanation = "O roteador de 2,4 GHz emite ondas com comprimento de 12,5 cm, capaz de difratar facilmente por portas e corredores da residência."
                    )
                ),
                goldTip = "Lembre-se: em qualquer refração de onda (mecânica ou eletromagnética), a FREQUÊNCIA É UMA CONSTANTE INVIOLÁVEL estabelecida pela fonte!"
            ),

            "fis_m4_a3" to PhysicsEntry(
                theoryMarkdown = """
                    # Interferência, Polarização e Ressonância
                    
                    ### 1. Interferência Ondulatória
                    Superposição de duas ondas de mesma frequência que se encontram no espaço:
                    • **Construtiva:** crista coincide com crista (a amplitude dobra). A diferença de caminho é \(\Delta x = N \cdot \lambda\).
                    • **Destrutiva:** crista coincide com vale (as amplitudes se subtraem, podendo anular o som). A diferença de caminho é \(\Delta x = (2N + 1) \cdot \frac{\lambda}{2}\).
                    É o princípio fundamental dos fones com cancelamento de ruído ativo!
                    
                    ### 2. Polarização
                    Seleção de uma única direção de vibração para ondas transversais através de um filtro polarizador (polaroid).
                    • **Atenção:** Como o som é uma onda mecânica longitudinal, o som **NUNCA pode ser polarizado**! A polarização é exclusividade de ondas transversais (como a luz).
                    
                    ### 3. Ressonância
                    Ocorre quando um sistema oscilante é excitado por uma força periódica externa cuja frequência coincide com a sua **frequência natural de vibração**, provocando um aumento dramático na amplitude de oscilação (ex: ponte de Tacoma desabando, taça quebrando com som agudo, sintonia de rádio).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Cancelamento de Ruído por Interferência Destrutiva",
                        syntax = """\\Delta x = \\frac{\\lambda}{2}""",
                        description = "Ondas em oposição de fase de 180° anulam-se mutuamente.",
                        parametersInfo = "O microfone capta o ruído ambiente e gera uma onda invertida de mesma amplitude."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Óculos 3D Polarizados",
                        codeOrFormula = "\\text{Filtros ortogonais a 90° filtram imagens para cada olho}",
                        explanation = "A tela projeta duas imagens com polarizações perpendiculares. Cada lente do óculos bloqueia uma e deixa passar a outra, criando percepção de profundidade estereoscópica."
                    )
                ),
                goldTip = "Pegadinha clássica do ENEM: Som pode sofrer reflexão, refração, difração, interferência e ressonância, mas NUNCA polarização porque é longitudinal!"
            ),

            "fis_m4_a4" to PhysicsEntry(
                theoryMarkdown = """
                    # Acústica e Qualidades Fisiológicas do Som
                    
                    O som é uma onda mecânica longitudinal que sensibiliza o aparelho auditivo humano (faixa audível: de 20 Hz a 20.000 Hz).
                    
                    ### As Três Qualidades Fisiológicas do Som no ENEM
                    1. **Altura:** qualidade que diferencia sons **graves (baixa frequência)** de sons **agudos (alta frequência)**. Depende exclusivamente da **frequência** (\(f\)).
                    2. **Intensidade (Volume):** qualidade que diferencia sons **fortes (muita energia)** de sons **fracos (pouca energia)**. Depende da **amplitude** da onda sonora. Medida em Decibéis (dB) em escala logarítmica.
                    3. **Timbre:** qualidade que permite distinguir sons de **mesma altura e mesma intensidade emitidos por instrumentos musicais diferentes** (ex: piano vs violino). Depende do formato da onda e dos harmônicos secundários que a acompanham!
                    
                    ### Tubos Sonoros
                    • **Tubo Aberto:** possui ventres nas duas extremidades: \(f_n = n \cdot \frac{v}{2L}\) (\(n = 1, 2, 3\dots\)).
                    • **Tubo Fechado:** possui nó na ponta fechada e ventre na aberta; só emite **harmônicos ímpares**: \(f_n = n \cdot \frac{v}{4L}\) (\(n = 1, 3, 5\dots\)).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Frequência de Tubos Sonoros Abertos",
                        syntax = """f_n = n \\frac{v}{2L} \\quad (n = 1, 2, 3 \\dots)""",
                        description = "Emite todos os harmônicos inteiros.",
                        parametersInfo = "v é a velocidade do som no ar (~340 m/s) e L é o comprimento do tubo."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Ajuste de Voz no Microfone",
                        codeOrFormula = "\\text{Aumentar o volume} = \\text{Aumentar a amplitude da onda}",
                        explanation = "No cotidiano dizemos 'abaixe a altura da TV', mas fisicamente alterar o volume mexe com a INTENSIDADE (amplitude), e não com a ALTURA (que é a frequência das notas)."
                    )
                ),
                goldTip = "No linguajar da física: som alto = som agudo (frequência alta); som forte = som volumoso (amplitude alta). Não confunda!"
            ),

            "fis_m4_a5" to PhysicsEntry(
                theoryMarkdown = """
                    # Efeito Doppler e Aplicações Tecnológicas
                    
                    O Efeito Doppler é a **variação aparente da frequência** percebida por um observador quando existe movimento relativo de aproximação ou afastamento entre ele e a fonte emissora de ondas.
                    
                    ### Comportamento Observado
                    • **Aproximação Relativa:** as frentes de onda são comprimidas espacialmente diante da fonte em movimento. O observador recebe mais cristas por segundo \(\implies\) **frequência aparente maior (som mais agudo / luz desviada para o azul - blueshift)**.
                    • **Afastamento Relativo:** as frentes de onda se distanciam. O observador recebe menos cristas por segundo \(\implies\) **frequência aparente menor (som mais grave / luz desviada para o vermelho - redshift)**.
                    
                    ### Aplicações no ENEM
                    • Sirene de ambulância que soa mais aguda ao se aproximar e mais grave ao se afastar.
                    • Radares de velocidade de trânsito (efeito Doppler com micro-ondas).
                    • Ultrassonografia Doppler para medir a velocidade do fluxo sanguíneo.
                    • Expansão do Universo (o desvio para o vermelho - redshift - da luz de galáxias distantes comprovou que elas estão se afastando da Terra).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação Geral do Efeito Doppler",
                        syntax = """f_{\\text{obs}} = f_{\\text{fonte}} \\left( \\frac{v \\pm v_{\\text{obs}}}{v \\mp v_{\\text{fonte}}} \\right)""",
                        description = "Relação entre a frequência real emitida e a aparente ouvida.",
                        parametersInfo = "Convenção de sinais: adota-se o sentido positivo do Observador para a Fonte."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Sirene de Virola em Aproximação",
                        codeOrFormula = """f_{obs} > f_{fonte} \\implies \\text{O som percebido é mais agudo}""",
                        explanation = "Enquanto a ambulância corre em direção ao pedestre parado na calçada, o som da sirene é percebido em tom mais agudo constante do que o tom emitido originalmente pelo alto-falante."
                    )
                ),
                goldTip = "Atenção: a velocidade de propagação do som no ar NÃO muda com a aproximação da ambulância! O que varia para o observador é exclusivamente a frequência percebida!"
            ),

            // ==========================================
            // MÓDULO 5: ÓPTICA
            // ==========================================
            "fis_m5_a1" to PhysicsEntry(
                theoryMarkdown = """
                    # Fundamentos da Óptica Geométrica e Propagação da Luz
                    
                    A Óptica Geométrica estuda a luz tratada como raios luminosos retilíneos.
                    
                    ### Os Três Princípios Fundamentais
                    1. **Propagação Retilínea da Luz:** Em meios homogêneos, isotrópicos e transparentes, a luz propaga-se sempre em linha reta (origem de sombras, penumbras e eclipses).
                    2. **Independência dos Raios Luminosos:** Quando dois feixes de luz se cruzam no espaço, um segue sua trajetória sem alterar as propriedades do outro.
                    3. **Reversibilidade dos Raios Luminosos:** O caminho óptico percorrido pela luz independe do sentido de propagação (se você vê os olhos de alguém pelo retrovisor, a pessoa também vê seus olhos).
                    
                    ### Câmera Escura de Orifício
                    Uma caixa opaca com um pequeno orifício forma na face posterior uma imagem invertida:
                    \[ \frac{i}{o} = \frac{p'}{p} \]
                    onde \(o\) é a altura do objeto, \(i\) é a imagem, \(p\) a distância ao furo e \(p'\) a profundidade da caixa.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Semelhança na Câmera Escura",
                        syntax = """\\frac{i}{o} = \\frac{p'}{p}""",
                        description = "Proporção geométrica de formação de sombras e imagens invertidas.",
                        parametersInfo = "Base conceitual das primeiras máquinas fotográficas (câmera escura de Da Vinci)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Altura de Edifício por Sombras",
                        codeOrFormula = """\\frac{H_{\\text{prédio}}}{S_{\\text{prédio}}} = \\frac{h_{\\text{vara}}}{s_{\\text{vara}}}""",
                        explanation = "Como os raios solares chegam paralelos, uma estaca de 1 m que projeta 0,5 m de sombra permite deduzir que um prédio cuja sombra mede 20 m tem altura de 40 metros."
                    )
                ),
                goldTip = "Em eclipses solares, observadores na região de UMBRA presenciam eclipse total (escuridão completa), enquanto na região de PENUMBRA presenciam eclipse parcial!"
            ),

            "fis_m5_a2" to PhysicsEntry(
                theoryMarkdown = """
                    # Espelhos Planos e Espelhos Esféricos (Côncavos e Convexos)
                    
                    ### 1. Espelhos Planos
                    A imagem conjugada por um espelho plano é sempre **VIRTUAL, DIREITA, de MESMO TAMANHO do objeto e SIMÉTRICA** em relação à superfície refletora. Possui enantiomorfismo (inversão lateral direita-esquerda).
                    • Campo visual: para ver o corpo inteiro, o espelho plano precisa ter pelo menos metade da altura da pessoa (\(H/2\)).
                    
                    ### 2. Espelhos Esféricos de Gauss
                    • **Espelho Convexo (foco virtual, \(f < 0\)):** usado em retrovisores de ônibus e esquinas de lojas. Para qualquer posição do objeto real, conjuga SEMPRE uma imagem: **VIRTUAL, DIREITA e MENOR**, com amplo campo visual!
                    • **Espelho Côncavo (foco real, \(f > 0\)):** usado em espelhos de maquiagem/barbeador e faróis de carros. Quando o objeto fica **entre o foco e o vértice**, conjuga imagem **VIRTUAL, DIREITA e MAIOR** (ampliação para maquiagem).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação de Gauss dos Espelhos",
                        syntax = """\\frac{1}{f} = \\frac{1}{p} + \\frac{1}{p'}""",
                        description = "Relaciona distância focal f, distância do objeto p e da imagem p'.",
                        parametersInfo = "f = R/2. Imagem real tem p' > 0; imagem virtual tem p' < 0."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Aumento Linear Transversal",
                        syntax = """A = \\frac{i}{o} = -\\frac{p'}{p}""",
                        description = "Fator de ampliação ou redução da imagem.",
                        parametersInfo = "Se A > 0 imagem é direita; se A < 0 imagem é invertida."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Retrovisor Direito Convexo",
                        codeOrFormula = "\\text{Imagem menor e mais próxima, ampliando o campo visual}",
                        explanation = "A imagem conjugada pelo espelho convexo é sempre virtual, direita e reduzida. Como parece menor, dá a falsa sensação de que o carro de trás está mais distante do que realmente está."
                    )
                ),
                goldTip = "Memorize a regra de ouro: Imagem REAL é sempre INVERTIDA; imagem VIRTUAL é sempre DIREITA!"
            ),

            "fis_m5_a3" to PhysicsEntry(
                theoryMarkdown = """
                    # Refração Luminosa, Lei de Snell e Reflexão Total
                    
                    ### Índice de Refração Absoluto (\(n\))
                    Razão entre a velocidade da luz no vácuo (\(c \approx 3 \cdot 10^8 \text{ m/s}\)) e a velocidade no meio (\(v\)):
                    \[ n = \frac{c}{v} \ge 1 \]
                    
                    ### Lei de Snell-Descartes
                    \[ n_1 \cdot \sin(\theta_1) = n_2 \cdot \sin(\theta_2) \]
                    • Ao passar para um meio mais refringente (\(n_2 > n_1\)): a velocidade diminui e o raio se **aproxima da normal** (\(\theta_2 < \theta_1\)).
                    • Ao passar para um meio menos refringente (\(n_2 < n_1\)): a velocidade aumenta e o raio se **afasta da normal**.
                    
                    ### Reflexão Total e Fibras Ópticas no ENEM
                    Condições obrigatórias para que ocorra Reflexão Total (sem nenhuma refração):
                    1. A luz deve viajar do meio **MAIS refringente para o MENOS refringente** (\(n_1 > n_2\)).
                    2. O ângulo de incidência deve ser **MAIOR que o ângulo limite** (\(\theta_i > \theta_L\)), onde:
                    \[ \sin(\theta_L) = \frac{n_{\text{menor}}}{n_{\text{maior}}} \]
                    É o princípio físico que guia pulsos de luz dentro de cabos de fibra óptica de telecomunicações!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Lei de Snell-Descartes",
                        syntax = """n_1 \\cdot \\sin(\\theta_1) = n_2 \\cdot \\sin(\\theta_2)""",
                        description = "Desvio angular da luz na interface de dois meios ópticos transparentes.",
                        parametersInfo = "Os ângulos são sempre medidos em relação à reta NORMAL perpendicular à fronteira."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Ângulo Limite de Reflexão Total",
                        syntax = """\\sin(\\theta_L) = \\frac{n_{\\text{menor}}}{n_{\\text{maior}}}""",
                        description = "Ângulo a partir do qual toda a energia luminosa é 100% refletida.",
                        parametersInfo = "Núcleo da fibra óptica tem índice n maior do que a casca."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Miragem no Asfalto Quente",
                        codeOrFormula = """n_{\\text{ar frio}} > n_{\\text{ar quente}} \\implies \\text{Reflexão Total}""",
                        explanation = "O asfalto aquecido torna o ar junto ao solo menos refringente. Os raios vindos do céu sofrem refrações sucessivas e reflexão total, fazendo o asfalto parecer molhado (refletindo o azul do céu)."
                    )
                ),
                goldTip = "Em fibras ópticas, o índice de refração do NÚCLEO central deve ser estritamente MAIOR que o índice de refração da CASCA externa para haver reflexão total!"
            ),

            "fis_m5_a4" to PhysicsEntry(
                theoryMarkdown = """
                    # Lentes Esféricas Delgadas, Foco e Vergência
                    
                    As lentes delgadas operam por **dupla refração** da luz. No ar (\(n_{\text{lente}} > n_{\text{ar}}\)):
                    • **Lentes de Bordas Finas (Convergentes):** biconvexa, plano-convexa e côncavo-convexa. Foco real (\(f > 0\)).
                    • **Lentes de Bordas Grossas (Divergentes):** bicôncava, plano-côncava e convexo-côncava. Foco virtual (\(f < 0\)).
                    
                    ### Formação de Imagens
                    • A lente divergente conjuga sempre o mesmo tipo de imagem para objeto real: **VIRTUAL, DIREITA e MENOR** (comportamento idêntico ao do espelho convexo).
                    • A lente convergente funciona como lupa quando o objeto está entre o foco e o centro óptico: imagem **VIRTUAL, DIREITA e MAIOR**.
                    
                    ### Vergência (Grau da Lente)
                    A vergência \(V\) mede a capacidade de desviar os raios de luz:
                    \[ V = \frac{1}{f} \]
                    com a distância focal \(f\) obrigatoriamente expressa em **metros**. A unidade é a **Dioptria (di)**, popularmente chamada de "grau".
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação da Vergência",
                        syntax = """V = \\frac{1}{f} \\quad (f \\text{ em metros})""",
                        description = "Poder de refração expresso em dioptrias (graus).",
                        parametersInfo = "Lente convergente tem V > 0 (+graus); divergente tem V < 0 (-graus)."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Lente de Grau Positivo",
                        codeOrFormula = """f = 25 \\text{ cm} = 0,25 \\text{ m} \\implies V = \\frac{1}{0,25} = +4 \\text{ dioptrias}""",
                        explanation = "Uma lente convergente com distância focal de 25 cm tem vergência igual a +4,0 graus."
                    )
                ),
                goldTip = "Ao calcular o 'grau' (dioptria), NUNCA esqueça de converter a distância focal de centímetros para metros dividindo por 100!"
            ),

            "fis_m5_a5" to PhysicsEntry(
                theoryMarkdown = """
                    # Óptica da Visão Humana e Correção de Ametropias
                    
                    O olho humano emétrope (normal) foca os raios luminosos exatamente sobre a **retina**, formando uma imagem real e invertida que o cérebro processa.
                    
                    ### Principais Ametropias no ENEM
                    1. **Miopia:** o globo ocular é mais alongado que o normal ou a córnea é muito curva. A imagem de objetos distantes forma-se **ANTES da retina**.
                       • **Correção:** lentes **Divergentes (grau negativo, \(V < 0\))**, que afastam os raios para focar na retina.
                    2. **Hipermetropia:** o globo ocular é mais curto que o normal. A imagem de objetos próximos forma-se **DEPOIS da retina**.
                       • **Correção:** lentes **Convergentes (grau positivo, \(V > 0\))**, que adiantam a convergência dos raios.
                    3. **Presbiopia ('Vista Cansada'):** perda de elasticidade do músculo ciliar e do cristalino com o envelhecimento; dificuldade para focar de perto.
                       • **Correção:** lentes **Convergentes ou Multifocais**.
                    4. **Astigmatismo:** curvatura assimétrica ou irregular da córnea, gerando múltiplos focos.
                       • **Correção:** lentes **Cilíndricas**.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Ponto Remoto do Míope",
                        syntax = """V = -\\frac{1}{d_{\\text{max}}}""",
                        description = "Vergência da lente divergente que corrige o ponto remoto do míope.",
                        parametersInfo = "Um míope que só enxerga nítido até 2 metros precisa de lente de V = -1/2 = -0,5 grau."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Receita de Oftalmologista",
                        codeOrFormula = """\\text{Olho direito: } -2,50 \\text{ di } \\implies \\text{Miopia, lente divergente}""",
                        explanation = "O sinal negativo na receita oftalmológica identifica imediatamente que o paciente é míope e necessita de lente esférica divergente."
                    )
                ),
                goldTip = "Mnemônico infalível: Míope usa lente Divergente (MD); Hipermetrope usa lente Convergente (HC)!"
            ),

            // ==========================================
            // MÓDULO 6: FÍSICA MODERNA
            // ==========================================
            "fis_m6_a1" to PhysicsEntry(
                theoryMarkdown = """
                    # Efeito Fotoelétrico e a Dualidade Onda-Partícula
                    
                    ### O Efeito Fotoelétrico
                    Fenômeno em que **elétrons são ejetados da superfície de uma placa metálica quando esta é iluminada por radiação eletromagnética** de frequência suficientemente alta.
                    
                    ### A Explicação de Albert Einstein (Prêmio Nobel de 1921)
                    Einstein propôs que a luz é emitida e absorvida em pacotes discretos de energia chamados **fótons**. A energia de um fóton é proporcional à sua frequência (\(f\)):
                    \[ E_{\text{fóton}} = h \cdot f \]
                    (onde \(h \approx 6,63 \cdot 10^{-34} \text{ J}\cdot\text{s}\) é a Constante de Planck).
                    
                    ### Equação Fotoelétrica de Einstein
                    \[ h \cdot f = \Phi + E_{c,\text{max}} \]
                    • \(\Phi\) é a **Função Trabalho** (energia mínima de ligação necessária para arrancar o elétron daquele metal).
                    • Se \(h \cdot f < \Phi\): nenhum fotoelétron é ejetado, não importando quão intensa seja a luz!
                    • Aumentar a **intensidade da luz** aumenta o **número de fótons e de fotoelétrons emitidos por segundo**, mas NÃO altera a velocidade máxima dos elétrons!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação do Fóton de Planck",
                        syntax = """E = h \\cdot f = \\frac{h \\cdot c}{\\lambda}""",
                        description = "Energia quantizada do fóton individual de radiação.",
                        parametersInfo = "Frequência maior (ex: ultravioleta) carrega fótons muito mais energéticos que o infravermelho."
                    ),
                    FormulaOrSyntaxItem(
                        termOrFunction = "Balanço Energético Fotoelétrico",
                        syntax = """E_{c,\\text{max}} = h \\cdot f - \\Phi""",
                        description = "Energia cinética excedente do fotoelétron liberado.",
                        parametersInfo = "Base operacional de sensores solares fotovoltaicos e sensores CCD de câmeras digitais."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Luz Vermelha vs Ultravioleta",
                        codeOrFormula = "f_{UV} > f_{\\text{corte}} > f_{\\text{vermelho}}",
                        explanation = "A luz vermelha, mesmo que de um refletor de estádio com altíssima potência luminosa, não ejeta elétrons do zinco porque cada fóton individual tem energia insuficiente. Um fraco feixe UV ejeta elétrons instantaneamente."
                    )
                ),
                goldTip = "No efeito fotoelétrico: Frequência determina se o elétron SAI e com qual velocidade; Intensidade luminosa determina QUANTOS elétrons saem!"
            ),

            "fis_m6_a2" to PhysicsEntry(
                theoryMarkdown = """
                    # Reações Nucleares, Radioatividade e Meia-Vida
                    
                    ### Radiações Nucleares Naturais
                    • **Partícula Alfa (\(\alpha\)):** núcleo de Hélio (\(_2^4\text{He}\)), carga \(+2\) e massa 4. Baixo poder de penetração (folha de papel bloqueia), alto poder ionizante.
                    • **Partícula Beta (\(\beta\)):** elétron nuclear (\(_{-1}^0\text{e}\)), gerado pela desintegração de um nêutron (\(n \to p + e^- + \bar{\nu}\)). Médio poder de penetração.
                    • **Radiação Gama (\(\gamma\)):** onda eletromagnética de frequência altíssima (\(_0^0\gamma\)). Altíssimo poder de penetração (bloqueada apenas por grossas placas de chumbo ou concreto).
                    
                    ### Fissão e Fusão Nuclear no ENEM
                    • **Fissão Nuclear:** quebra de um núcleo pesado e instável (como o Urânio-235) bombardeado por um nêutron lento, gerando núcleos médios, mais nêutrons e imensa liberação de energia em cadeia (usinas nucleares e bomba atômica).
                    • **Fusão Nuclear:** união de núcleos atômicos leves (como Isótopos de Hidrogênio: Deutério e Trítio) formando um núcleo mais pesado de Hélio a temperaturas de milhões de graus (energia das estrelas e do Sol).
                    
                    ### Lei do Decaimento e Meia-Vida (\(T_{1/2}\))
                    Tempo necessário para que a quantidade de átomos radioativos de uma amostra seja reduzida à metade:
                    \[ M(t) = \frac{M_0}{2^x} \quad \text{onde } x = \frac{t}{T_{1/2}} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equação do Decaimento Radioativo",
                        syntax = """m = \\frac{m_0}{2^n} \\quad \\text{com } n = \\frac{t}{t_{1/2}}""",
                        description = "Massa remanescente após n períodos de meia-vida.",
                        parametersInfo = "Usada na datação por Carbono-14 em arqueologia e descarte de lixo hospitalar."
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Decaimento de Iodo-131 na Medicina",
                        codeOrFormula = """t = 24 \\text{ dias}, \\quad T_{1/2} = 8 \\text{ dias} \\implies n = 3 \\implies m = \\frac{80 \\text{ mg}}{2^3} = 10 \\text{ mg}""",
                        explanation = "Uma amostra de 80 mg de Iodo-131 (usado no tratamento de tireoide) após 24 dias (3 meias-vidas) fica reduzida a 80 / 8 = 10 mg."
                    )
                ),
                goldTip = "Lembre-se: Fusão une núcleos leves (Sol); Fissão parte núcleos pesados (Usinas de Angra)!"
            ),

            "fis_m6_a3" to PhysicsEntry(
                theoryMarkdown = """
                    # Teoria da Relatividade Restrita e Equivalência Massa-Energia
                    
                    Em 1905, Albert Einstein publicou a Teoria da Relatividade Restrita, assentada sobre dois postulados fundamentais:
                    
                    ### Os Dois Postulados da Relatividade
                    1. **Princípio da Relatividade:** As leis da física são as mesmas em todos os referenciais inerciais.
                    2. **Constância da Velocidade da Luz:** A velocidade da luz no vácuo (\(c \approx 3 \cdot 10^8 \text{ m/s}\)) tem o mesmo valor em todas as direções e em todos os referenciais inerciais, independendo do movimento da fonte ou do observador!
                    
                    ### Consequências Cinemáticas Relativísticas
                    Quando corpos se movem a velocidades comparáveis à velocidade da luz (\(v \sim c\)):
                    • **Dilatação Temporal:** o tempo passa mais devagar para o observador em movimento relativo: \(\Delta t = \gamma \cdot \Delta t_0\).
                    • **Contração Espacial:** o comprimento medido no sentido do movimento é contraído: \(L = \frac{L_0}{\gamma}\).
                    (onde \(\gamma = \frac{1}{\sqrt{1 - v^2/c^2}} \ge 1\) é o Fator de Lorentz).
                    
                    ### Equivalência Massa-Energia de Einstein
                    A massa é uma forma condensada de energia pura:
                    \[ E = m \cdot c^2 \]
                    Uma pequeníssima quantidade de massa perdida (\(\Delta m\)) em reações nucleares de fissão ou fusão converte-se em quantidades titânicas de energia!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem(
                        termOrFunction = "Equivalência Massa-Energia",
                        syntax = """E = m \\cdot c^2 = \\Delta m \\cdot c^2""",
                        description = "Equação mais famosa da física contemporânea.",
                        parametersInfo = "c² = (3*10^8)² = 9*10^16 m²/s². 1 grama de matéria convertida libera 90 trilhões de Joules!"
                    )
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Correção Relativística do Sistema GPS",
                        codeOrFormula = "\\text{Satélites ajustam relógios atômicos por dilatação do tempo}",
                        explanation = "Os relógios atômicos a bordo dos satélites GPS orbitam a 14.000 km/h. Sem as correções da relatividade restrita e geral, a marcação de posição na Terra erraria mais de 10 km por dia!"
                    )
                ),
                goldTip = "A velocidade da luz no vácuo é a velocidade limite cósmica insuperável: nada que possua massa real pode atingir ou ultrapassar c!"
            )
        )
    }

    private val exerciseRegistry: Map<String, List<MathExercise>> by lazy {
        mapOf(
            // MÓDULO 3
            "fis_m3_a1" to listOf(
                MathExercise(
                    id = "ex_fis_m3_a1_1",
                    title = "ENEM: Clima Desértico e Escala Fahrenheit",
                    problemStatement = "Em um boletim meteorológico internacional sobre o Vale da Morte nos Estados Unidos, a temperatura máxima registrada no solo foi de 122 °F. Na escala termométrica Celsius adotada oficialmente no Brasil, essa temperatura corresponde a:",
                    options = listOf("40 °C", "45 °C", "50 °C", "55 °C", "60 °C"),
                    correctIndex = 2,
                    hint = "Aplique T_C / 5 = (T_F - 32) / 9.",
                    stepByStepSolution = "T_C / 5 = (122 - 32) / 9 -> T_C / 5 = 90 / 9 = 10 -> T_C = 5 * 10 = 50 °C."
                )
            ),

            "fis_m3_a2" to listOf(
                MathExercise(
                    id = "ex_fis_m3_a2_1",
                    title = "ENEM: Junta de Dilatação em Viadutos",
                    problemStatement = "Uma viga de concreto protendido de 50 metros de comprimento em um viaduto possui coeficiente de dilatação linear α = 1,2 × 10⁻⁵ °C⁻¹. Sabendo que a temperatura da estrutura oscila de 10 °C na madrugada fria de inverno a 50 °C no auge do verão ensolarado (ΔT = 40 °C), a folga mínima que a junta de dilatação deve comportar para evitar trincas estruturais é de:",
                    options = listOf("1,2 mm", "12 mm", "24 mm", "36 mm", "48 mm"),
                    correctIndex = 2,
                    hint = "Calcule ΔL = L_0 * α * ΔT.",
                    stepByStepSolution = "ΔL = 50 m * (1,2 * 10^-5 °C^-1) * 40 °C = 2000 * 1,2 * 10^-5 = 2,4 * 10^-2 m = 0,024 m = 24 mm."
                )
            ),

            "fis_m3_a3" to listOf(
                MathExercise(
                    id = "ex_fis_m3_a3_1",
                    title = "ENEM: Calor Específico e Brisa Marítima",
                    problemStatement = "Durante o dia em uma praia ensolarada, a areia esquenta muito mais rápido do que a água do mar. À noite, a areia esfria rapidamente, enquanto a água do mar permanece morna por muito tempo. Esse fenômeno ocorre primordialmente porque:",
                    options = listOf(
                        "A água do mar reflete 100% da radiação solar recebida.",
                        "O calor específico da água é significativamente maior do que o calor específico da areia.",
                        "A areia é um isolante térmico que não conduz calor para as camadas inferiores.",
                        "A água possui menor densidade do que os grãos de quartzo da areia.",
                        "O calor latente de fusão da areia é muito inferior ao da água salgada."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se de Q = m * c * ΔT: materiais com maior calor específico sofrem menor variação de temperatura para a mesma quantidade de calor.",
                    stepByStepSolution = "A água possui um calor específico elevado (1,0 cal/g°C), exigindo grande quantidade de calor para variar sua temperatura e atuando como moderador térmico (Alternativa B)."
                )
            ),

            "fis_m3_a4" to listOf(
                MathExercise(
                    id = "ex_fis_m3_a4_1",
                    title = "ENEM: Isolamento em Edificações Sustentáveis",
                    problemStatement = "Em regiões com invernos rigorosos, janelas residenciais eficientes são construídas com vidro duplo, mantendo uma camada selada de ar seco ou vácuo entre duas placas de vidro. A presença dessa camada selada reduz o gasto com aquecimento doméstico porque:",
                    options = listOf(
                        "O ar selado impede a passagem de radiação luminosa visível.",
                        "O ar e o vácuo são péssimos condutores de calor, dificultando a condução e a convecção térmica com o exterior.",
                        "O vidro duplo converte energia mecânica do vento em calor sensível interno.",
                        "A pressão interna entre os vidros duplica a temperatura ambiente por compressão adiabática.",
                        "O espaço entre vidros absorve radiação ultravioleta prejudicial."
                    ),
                    correctIndex = 1,
                    hint = "A condutividade térmica do ar em repouso é extremamente baixa, minimizando perdas por condução térmica.",
                    stepByStepSolution = "O ar estagnado e o vácuo possuem condutividade térmica quase nula, impedindo que o calor do interior da residência escape por condução e convecção para o ar gélido de fora (Alternativa B)."
                )
            ),

            "fis_m3_a5" to listOf(
                MathExercise(
                    id = "ex_fis_m3_a5_1",
                    title = "ENEM: Rendimento Máximo de Usina Termelétrica",
                    problemStatement = "Uma usina termelétrica opera com vapor superaquecido a 527 °C gerado na caldeira (fonte quente) e utiliza a água de um rio vizinho a 27 °C como fonte de resfriamento condensador (fonte fria). O rendimento térmico máximo teórico permitido pelas leis da termodinâmica (Ciclo de Carnot) para essa usina é de:",
                    options = listOf("37,5%", "50,0%", "62,5%", "75,0%", "94,8%"),
                    correctIndex = 2,
                    hint = "Converta ambas as temperaturas para Kelvin somando 273 e aplique η = 1 - (T_fria / T_quente).",
                    stepByStepSolution = "T_quente = 527 + 273 = 800 K.\nT_fria = 27 + 273 = 300 K.\nη = 1 - (300 / 800) = 1 - 0,375 = 0,625 = 62,5%."
                )
            ),

            // MÓDULO 4
            "fis_m4_a2" to listOf(
                MathExercise(
                    id = "ex_fis_m4_a2_1",
                    title = "ENEM: Difração do Som e da Luz",
                    problemStatement = "Uma pessoa do lado de fora de uma sala com a porta entreaberta consegue ouvir com clareza a voz de quem conversa lá dentro, mas não consegue ver a imagem da pessoa falante. Do ponto de vista ondulatório, isso decorre do fato de que:",
                    options = listOf(
                        "O som propaga-se com velocidade superior à da luz no ar.",
                        "A luz sofre reflexão total no batente da porta e não penetra.",
                        "O comprimento de onda do som é comparável à abertura da porta, permitindo acentuada difração, enquanto o da luz visível é microscópico.",
                        "As ondas de luz são longitudinais e não conseguem contornar cantos pontiagudos.",
                        "O ar quente no interior da sala refrata os raios ópticos para longe da porta."
                    ),
                    correctIndex = 2,
                    hint = "A difração só se torna expressiva quando o comprimento de onda λ é da ordem de grandeza da fenda/abertura.",
                    stepByStepSolution = "As ondas sonoras audíveis têm comprimento de onda em torno de 1 metro (ordem da abertura da porta), sofrendo forte difração. A luz visível tem comprimento da ordem de nanômetros (~500 nm), propagando-se em linha reta sem difração perceptível macroscópica (Alternativa C)."
                )
            ),

            "fis_m4_a3" to listOf(
                MathExercise(
                    id = "ex_fis_m4_a3_1",
                    title = "ENEM: Fones com Cancelamento de Ruído Ativo",
                    problemStatement = "Fones de ouvido modernos com cancelamento ativo de ruído possuem um microfone embutido que capta o barulho sonoro externo (como o zumbido contínuo das turbinas de um avião) e um circuito eletrônico que gera instantaneamente um sinal acústico transmitido ao ouvido. O fenômeno ondulatório que viabiliza a anulação desse ruído ambiente é a:",
                    options = listOf(
                        "Polarização mecânica da onda sonora.",
                        "Reflexão difusa na carcaça do fone.",
                        "Interferência totalmente destrutiva entre ondas em oposição de fase.",
                        "Refração acústica para frequências inaudíveis de ultrassom.",
                        "Ressonância das frequências graves nas conchas acústicas."
                    ),
                    correctIndex = 2,
                    hint = "Superpor crista com vale provoca subtração de amplitudes, zerando a oscilação.",
                    stepByStepSolution = "O circuito processa o ruído e emite uma onda com a mesma frequência e amplitude, porém em oposição de fase de 180° (defasagem de meio comprimento de onda), gerando interferência destrutiva que anula o som percebido (Alternativa C)."
                )
            ),

            "fis_m4_a4" to listOf(
                MathExercise(
                    id = "ex_fis_m4_a4_1",
                    title = "ENEM: Altura e Timbre em Instrumentos Musicais",
                    problemStatement = "Quando um trompete e um clarinete tocam exatamente a mesma nota musical Dó central com a mesma intensidade sonora em uma orquestra sinfônica, um ouvinte atento consegue distinguir com facilidade qual é o som de cada instrumento. Essa capacidade de distinção deve-se à diferença de:",
                    options = listOf(
                        "Altura sonora das notas.",
                        "Velocidade de propagação do som de cada instrumento no ar.",
                        "Intensidade e amplitude da onda primária.",
                        "Timbre, associado ao formato complexo da onda resultante da combinação de harmônicos.",
                        "Frequência fundamental emitida pelas palhetas e bocais."
                    ),
                    correctIndex = 3,
                    hint = "A nota é a mesma (mesma frequência) e o volume é o mesmo (mesma amplitude). O que diferencia instrumentos diferentes tocando a mesma nota é o timbre.",
                    stepByStepSolution = "O timbre é a qualidade fisiológica que permite diferenciar sons de mesma frequência e intensidade emitidos por fontes distintas, devido à quantidade e intensidade dos harmônicos secundários que acompanham a fundamental (Alternativa D)."
                )
            ),

            "fis_m4_a5" to listOf(
                MathExercise(
                    id = "ex_fis_m4_a5_1",
                    title = "ENEM: Efeito Doppler e Aproximação de Veículo",
                    problemStatement = "Um pedestre aguarda na calçada quando uma ambulância se aproxima em alta velocidade com a sirene ligada. Comparando o som ouvido pelo pedestre durante a aproximação com o som ouvido quando a ambulância está parada em repouso no hospital, constata-se que o pedestre percebe um som:",
                    options = listOf(
                        "De mesma frequência, porém com velocidade de propagação maior.",
                        "De frequência aparente mais alta (mais agudo).",
                        "De frequência aparente mais baixa (mais grave).",
                        "Com menor amplitude e mesmo timbre.",
                        "Cuja frequência oscila aleatoriamente entre grave e agudo."
                    ),
                    correctIndex = 1,
                    hint = "Na aproximação relativa, as frentes de onda são comprimidas, aumentando o número de cristas que chegam ao ouvido por segundo.",
                    stepByStepSolution = "Pelo Efeito Doppler, quando a fonte emissora se aproxima do observador parado, a frequência aparente percebida é superior à frequência real emitida pela sirene (som mais agudo) (Alternativa B)."
                )
            ),

            // MÓDULO 5
            "fis_m5_a1" to listOf(
                MathExercise(
                    id = "ex_fis_m5_a1_1",
                    title = "ENEM: Câmera Escura de Orifício e Imagem Invertida",
                    problemStatement = "Uma vela acesa de 12 cm de altura está posicionada a 60 cm do orifício de uma câmera escura com profundidade interna de 20 cm. A altura da imagem projetada no anteparo da face posterior da câmera é de:",
                    options = listOf("2 cm", "4 cm", "6 cm", "8 cm", "10 cm"),
                    correctIndex = 1,
                    hint = "Aplique a relação de semelhança da câmera escura: i / o = p' / p.",
                    stepByStepSolution = "i / 12 = 20 / 60 -> i / 12 = 1 / 3 -> i = 12 / 3 = 4 cm."
                )
            ),

            "fis_m5_a2" to listOf(
                MathExercise(
                    id = "ex_fis_m5_a2_1",
                    title = "ENEM: Espelhos Convexos em Segurança de Ônibus",
                    problemStatement = "Em portas traseiras de ônibus urbanos e em corredores de supermercados, utilizam-se espelhos esféricos convexos para monitorar passageiros e clientes. A principal vantagem óptica desses espelhos em relação aos espelhos planos convencionais é que eles:",
                    options = listOf(
                        "Formam imagens reais e invertidas projetadas sobre o motorista.",
                        "Aumentam o tamanho aparente dos objetos, facilitando a leitura de detalhes.",
                        "Possuem maior campo visual, conjugando imagens sempre virtuais, direitas e reduzidas.",
                        "Eliminam completamente a reflexão de raios ultravioleta.",
                        "Invertem a orientação das cores dos objetos refletidos."
                    ),
                    correctIndex = 2,
                    hint = "O espelho convexo diminui o tamanho das imagens para fazer caber um ângulo de visão muito maior.",
                    stepByStepSolution = "O espelho esférico convexo conjuga exclusivamente imagens virtuais, direitas e menores que o objeto, proporcionando um campo visual muito mais amplo para vigilância (Alternativa C)."
                )
            ),

            "fis_m5_a3" to listOf(
                MathExercise(
                    id = "ex_fis_m5_a3_1",
                    title = "ENEM: Fibra Óptica e Reflexão Interna Total",
                    problemStatement = "Cabos submarinos de fibra óptica conduzem gigabits de dados por segundo por meio de pulsos de luz laser guiados em seu interior sem escapar pelas laterais. Para que a luz viaje confinada no núcleo transparente da fibra óptica, é condição necessária que:",
                    options = listOf(
                        "A velocidade da luz no núcleo seja maior que a velocidade no vácuo.",
                        "O índice de refração do núcleo seja maior que o índice de refração da casca externa.",
                        "A luz incida na parede da fibra com ângulo inferior ao ângulo limite.",
                        "A casca externa da fibra seja feita de ouro ou prata espelhada.",
                        "A frequência da onda luminosa varie continuamente ao longo do percurso."
                    ),
                    correctIndex = 1,
                    hint = "A reflexão total só ocorre quando a luz tenta passar do meio MAIS refringente para o MENOS refringente (n_núcleo > n_casca).",
                    stepByStepSolution = "A condição fundamental da reflexão total é viajar de um meio de maior índice de refração para outro de menor índice (n_núcleo > n_casca) sob ângulo maior que o limite (Alternativa B)."
                )
            ),

            "fis_m5_a4" to listOf(
                MathExercise(
                    id = "ex_fis_m5_a4_1",
                    title = "ENEM: Vergência de Lente de Aumento (Lupa)",
                    problemStatement = "Um perito criminal utiliza uma lupa de leitura (lente esférica convergente) com distância focal de 20 centímetros para examinar impressões digitais. A vergência óptica dessa lente, expressa em dioptrias (graus), é igual a:",
                    options = listOf("+0,05 di", "+0,20 di", "+2,0 di", "+5,0 di", "+20,0 di"),
                    correctIndex = 3,
                    hint = "Converta 20 cm em metros (f = 0,20 m) e calcule V = 1 / f.",
                    stepByStepSolution = "f = 20 cm = 0,20 m.\nV = 1 / f = 1 / 0,20 = +5,0 dioptrias."
                )
            ),

            "fis_m5_a5" to listOf(
                MathExercise(
                    id = "ex_fis_m5_a5_1",
                    title = "ENEM: Correção da Miopia com Lentes Divergentes",
                    problemStatement = "Um jovem com miopia apresenta dificuldade para enxergar com nitidez objetos situados longe de seus olhos, pois as imagens de objetos distantes convergem e se formam antes de atingirem a retina. A prescrição oftalmológica correta para compensar essa ametropia e projetar a imagem sobre a retina consiste no uso de óculos com lentes:",
                    options = listOf(
                        "Convergentes de vergência positiva.",
                        "Divergentes de vergência negativa.",
                        "Cilíndricas de curvatura variável.",
                        "Bifocais com prisma dispersor.",
                        "Polarizadas sem poder dióptrico."
                    ),
                    correctIndex = 1,
                    hint = "A miopia converge os raios antes da hora; logo, é preciso uma lente DIVERGENTE (grau negativo) para abrir os raios e empurrar o foco para a retina.",
                    stepByStepSolution = "O míope precisa afastar a convergência para que ela atinja a retina no fundo do olho, o que é realizado por lentes esféricas divergentes (vergência com sinal negativo) (Alternativa B)."
                )
            ),

            // MÓDULO 6
            "fis_m6_a1" to listOf(
                MathExercise(
                    id = "ex_fis_m6_a1_1",
                    title = "ENEM: Painéis Solares e Efeito Fotoelétrico",
                    problemStatement = "Em células fotovoltaicas, a luz solar incidente promove a ejeção de elétrons do silício, estabelecendo uma corrente elétrica útil. De acordo com a teoria quântica formulada por Einstein para o Efeito Fotoelétrico, se aumentarmos a INTENSIDADE da luz incidente mantendo inalterada a sua frequência (acima da frequência de corte), o efeito observado será:",
                    options = listOf(
                        "O aumento na velocidade e na energia cinética máxima de cada elétron ejetado.",
                        "A diminuição da corrente elétrica gerada pelo painel.",
                        "O aumento no número de elétrons ejetados por segundo (maior corrente).",
                        "A interrupção imediata da emissão de fotoelétrons.",
                        "A alteração da função trabalho do material semicondutor."
                    ),
                    correctIndex = 2,
                    hint = "Maior intensidade luminosa = mais fótons chegando por segundo = mais elétrons arrancados por segundo.",
                    stepByStepSolution = "A energia de cada fóton depende unicamente de sua frequência (E = h*f). Aumentar a intensidade luminosa significa aumentar a densidade de fluxo de fótons, arrancando um número maior de elétrons por unidade de tempo e elevando a corrente elétrica (Alternativa C)."
                )
            ),

            "fis_m6_a2" to listOf(
                MathExercise(
                    id = "ex_fis_m6_a2_1",
                    title = "ENEM: Datação de Fósseis por Carbono-14",
                    problemStatement = "O Carbono-14 é um isótopo radioativo com tempo de meia-vida de aproximadamente 5.730 anos, absorvido pelos seres vivos durante a respiração e alimentação. Em um fragmento de madeira fóssil retirado de um sítio arqueológico, constatou-se que a atividade do Carbono-14 estava reduzida a 12,5% (1/8) do valor medido em uma árvore viva de mesma espécie. A idade estimada desse artefato é de aproximadamente:",
                    options = listOf("5.730 anos", "11.460 anos", "17.190 anos", "22.920 anos", "28.650 anos"),
                    correctIndex = 2,
                    hint = "1/8 corresponde a 1/(2³), ou seja, decorreram 3 períodos completos de meia-vida.",
                    stepByStepSolution = "A fração remanescente é 12,5% = 1/8 = (1/2)³ -> n = 3 meias-vidas.\nTempo total = 3 * 5.730 anos = 17.190 anos."
                )
            ),

            "fis_m6_a3" to listOf(
                MathExercise(
                    id = "ex_fis_m6_a3_1",
                    title = "ENEM: Equivalência Massa-Energia no Sol",
                    problemStatement = "No interior do núcleo do Sol, reações contínuas de fusão nuclear transformam quatro prótons de hidrogênio em um núcleo estável de hélio. A massa do núcleo de hélio resultante é cerca de 0,7% menor que a soma das massas dos quatro prótons iniciais. De acordo com a relação relativística de Einstein E = Δm · c², essa massa faltante (defeito de massa) converte-se em:",
                    options = listOf(
                        "Matéria escura gravitacional estável.",
                        "Radiação térmica e eletromagnética que aquece o sistema solar.",
                        "Campos magnéticos estáticos nas manchas solares.",
                        "Gás ozônio absorvido na alta atmosfera da Terra.",
                        "Aceleração angular na rotação do próprio Sol."
                    ),
                    correctIndex = 1,
                    hint = "A equação E = mc² demonstra que a massa é convertida diretamente em energia eletromagnética irradiada.",
                    stepByStepSolution = "O defeito de massa nas reações de fusão termonuclear no interior do Sol é transformado em radiação eletromagnética pura (luz e calor), que sustenta a vida na Terra (Alternativa B)."
                )
            )
        )
    }
}
