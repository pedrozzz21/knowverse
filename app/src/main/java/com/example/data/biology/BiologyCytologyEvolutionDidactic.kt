package com.example.data.biology

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático e Exercícios Especializados para:
 * - Módulo 4: Citologia e Bioquímica Celular (bio_cit_01 a bio_cit_05)
 * - Módulo 5: Evolução Biológica e Hominização (bio_evo_01 a bio_evo_04)
 */
object BiologyCytologyEvolutionDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle? {
        val entry = didacticMap[lesson.id] ?: return null
        return CalculusDidacticBundle(
            theoryMarkdown = entry.theoryMarkdown,
            formulas = entry.formulas,
            solvedExamples = entry.solvedExamples,
            imageResId = null,
            goldTip = entry.goldTip
        )
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise>? {
        return exerciseMap[lesson.id]
    }

    data class BioEntry(
        val theoryMarkdown: String,
        val formulas: List<FormulaOrSyntaxItem>,
        val solvedExamples: List<LessonExample>,
        val goldTip: String
    )

    private val didacticMap: Map<String, BioEntry> by lazy {
        mapOf(
            // ==========================================
            // MÓDULO 4: CITOLOGIA E BIOQUÍMICA
            // ==========================================
            "bio_cit_01" to BioEntry(
                theoryMarkdown = """
                    # Teoria Celular, Envoltórios e Fisiologia da Membrana Plasmática
                    
                    A Teoria Celular estabelece que todos os seres vivos são constituídos por células, as unidades morfofisiológicas fundamentais da vida, com todo organismo originando-se de outra célula preexistente.
                    
                    ### A Membrana Plasmática e o Mosaico Fluido (Singer e Nicolson)
                    A membrana é composta por uma **bicamada de fosfolipídios anfipáticos** (com cabeças polares hidrofílicas voltadas para o meio aquoso intra e extracelular e caudas apolares hidrofóbicas voltadas para o interior) e proteínas globulares integrais e periféricas com mobilidade lateral fluida.
                    
                    ### Mecanismos de Transporte Celular no ENEM
                    • **Osmose (Transporte Passivo de Água):** Movimento espontâneo de moléculas de água através de membrana semipermeável do meio **hipotônico (menos concentrado)** para o meio **hipertônico (mais concentrado)**.
                      - **Hemácia (Célula Animal):** Em meio hipertônico perde água e sofre **crenação** (murcha); em meio hipotônico ganha água, incha e sofre **hemólise** (lise osmótica por não possuir parede celular).
                      - **Célula Vegetal:** Em meio hipertônico sofre **plasmólise** (a membrana se descola da parede celular rígida); em meio hipotônico torna-se **túrgica** sem estourar, contida pela parede celulósica.
                    • **Transporte Ativo (Bomba de Na⁺/K⁺):** Consome ATP para bombear 3 Na⁺ para fora e 2 K⁺ para dentro contra os seus gradientes de concentração, mantendo o potencial de repouso das membranas neurais e musculares.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Osmose", "H2O migra do meio hipotônico -> hipertônico", "A favor do gradiente de potencial hídrico"),
                    FormulaOrSyntaxItem("Bomba de Sódio e Potássio", "3 Na⁺ saem / 2 K⁺ entram por molécula de ATP hidrolisada", "Transporte ativo primário que mantém eletronegatividade celular")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Salgar a Salada de Alface",
                        codeOrFormula = """\\text{Meio extracelular hipertônico} \\implies \\text{Perda de água por osmose (Plasmólise)}""",
                        explanation = "Ao temperar a salada com sal antes da hora, o meio externo torna-se muito concentrado (hipertônico). As células das folhas perdem água por osmose para o prato, e a alface murcha rapidamente."
                    )
                ),
                goldTip = "Célula animal em água destilada estoura (lise); célula vegetal em água destilada fica túrgica e NÃO arrebenta graças à resistência mecânica da parede celular celulósica!"
            ),

            "bio_cit_02" to BioEntry(
                theoryMarkdown = """
                    # Organelas Citoplasmáticas e a Fábrica Celular
                    
                    As organelas membranosas compartimentalizam funções bioquímicas no citoplasma eucariótico:
                    
                    ### O Sistema Endomembranoso e suas Funções
                    • **Retículo Endoplasmático Rugoso (RER ou Granular):** Associado a ribossomos na superfície externa. Síntese, enovelamento e controle de qualidade de **proteínas de exportação** (como enzimas digestivas e hormônios proteicos).
                    • **Retículo Endoplasmático Liso (REL ou Agranular):** Não possui ribossomos. Síntese de **lipídios** (fosfolipídios de membrana e hormônios esteroides como testosterona e progesterona), armazenamento de íons cálcio nas células musculares (retículo sarcoplasmático) e **destoxificação de álcool e medicamentos** nos hepatócitos do fígado.
                    • **Complexo Golgiense:** Recebe vesículas do RER, promove glicosilação de proteínas, empacota e endereça substâncias para secreção celular (exocitose), além de formar o **acrossomo** do espermatozoide e originar os **lisossomos**.
                    • **Lisossomos:** Vesículas repletas de enzimas hidrolíticas ácidas ativas em pH 5,0. Realizam a **digestão intracelular (heterofagia)** e a reciclagem de organelas velhas defeituosas (**autofagia**).
                    • **Peroxissomos:** Degradação de ácidos graxos longos por beta-oxidação e neutralização de peróxido de hidrogênio tóxico (\(\text{H}_2\text{O}_2\)) pela enzima **catalase**:
                    \[ 2 \text{H}_2\text{O}_2 \xrightarrow{\text{catalase}} 2 \text{H}_2\text{O} + \text{O}_2 \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Enzima Catalase (Peroxissomo)", "2 H2O2 -> 2 H2O + O2 (efervescência na água oxigenada)", "Destoxificação de radicais livres celulares"),
                    FormulaOrSyntaxItem("Fluxo de Secreção Celular", "RER -> Complexo de Golgi -> Vesículas de Secreção -> Exocitose", "Via clássica de exportação de proteínas")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Tolerância ao Álcool e Proliferação do REL",
                        codeOrFormula = """\\text{Consumo crônico de álcool} \\implies \\text{Hipertrofia do Retículo Liso hepático}""",
                        explanation = "O uso contínuo de álcool e sedativos induz as células do fígado a aumentarem a quantidade de Retículo Endoplasmático Liso para acelerar a destoxificação enzimática, gerando tolerância medicamentosa."
                    )
                ),
                goldTip = "Células secretoras (como as células do pâncreas que fabricam insulina) possuem Retículo Rugoso e Complexo de Golgi hipertrofiados!"
            ),

            "bio_cit_03" to BioEntry(
                theoryMarkdown = """
                    # Teoria Endossimbiótica e Biologia Mitocondrial
                    
                    Formulada por Lynn Margulis, a **Teoria Endossimbiótica** postula que as mitocôndrias e os cloroplastos evoluíram a partir de bactérias primitivas procariontes de vida livre (bactérias aeróbias e cianobactérias, respectivamente) que foram fagocitadas por células eucarióticas ancestrais e estabeleceram uma relação de simbiose mutualística estável e vantajosa.
                    
                    ### Evidências Bioquímicas Irrefutáveis da Origem Bacteriana
                    1. **DNA Próprio Circular:** Mitocôndrias e cloroplastos possuem molécula própria de DNA circular fechada e despida de histonas, idêntica aos plasmídeos bacterianos.
                    2. **Ribossomos 70S:** A maquinaria de tradução proteica interna dessas organelas utiliza ribossomos do tipo 70S (típicos de procariontes), e não os ribossomos 80S do citoplasma eucarionte.
                    3. **Dupla Membrana Envoltória:** A membrana interna tem composição lipídica bacteriana (como a cardiolipina), enquanto a membrana externa provém da vesícula de endocitose da célula hospedeira ancestral.
                    4. **Autoduplicação Independente:** Dividem-se por fissão binária simples, independentemente da mitose nuclear celular.
                    
                    ### Herança Mitocondrial Materna Exclusiva no ENEM
                    Na fecundação humana, apenas o núcleo do espermatozoide penetra no ovócito; a cauda e as mitocôndrias paternas sofrem degradação. Portanto, **todo o DNA mitocondrial de uma pessoa é herdado exclusivamente de sua mãe biológica**!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Evidências Endossimbióticas", "DNA circular próprio + Ribossomos 70S + Dupla membrana + Fissão binária", "Herança ancestral de procariontes fagocitados"),
                    FormulaOrSyntaxItem("Herança Materna", "Mãe afetada por mutação mitocondrial transmite a 100% dos filhos", "Pai afetado não transmite a nenhum filho")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Heredograma de Doença Mitocondrial",
                        codeOrFormula = "\\text{Mãe afetada} \\to \\text{Todos os filhos e filhas afetados}",
                        explanation = "Se uma mulher portadora de miopatia mitocondrial tiver filhos, todos herdam a mutação, pois as mitocôndrias do embrião originam-se exclusivamente do citoplasma do ovócito materno."
                    )
                ),
                goldTip = "Doença mitocondrial passa de MÃE para TODOS os filhos (homens e mulheres), mas o pai com a doença NUNCA transmite aos seus filhos!"
            ),

            "bio_cit_04" to BioEntry(
                theoryMarkdown = """
                    # Metabolismo Energético: Respiração Celular e Fermentação
                    
                    A respiração celular aeróbia oxida completamente a molécula de glicose (\(\text{C}_6\text{H}_{12}\text{O}_6\)) gerando gás carbônico, água e alta rentabilidade de trifosfato de adenosina (cerca de 30 a 32 ATPs):
                    \[ \text{C}_6\text{H}_{12}\text{O}_6 + 6 \text{O}_2 \longrightarrow 6 \text{CO}_2 + 6 \text{H}_2\text{O} + \text{Energia (ATP)} \]
                    
                    ### As Três Etapas da Respiração Celular
                    1. **Glicólise (no Citosol / Citoplasma):** Quebra anaeróbia da glicose em 2 moléculas de piruvato (ácido pirúvico), rendendo 2 ATPs e 2 NADH. Ocorre tanto em células aeróbias quanto em fermentadoras.
                    2. **Ciclo de Krebs (na Matriz Mitocondrial):** Descarboxilação e oxidação do piruvato/acetil-CoA, liberando \(\text{CO}_2\) e carregando cofatores aceptores de elétrons (\(\text{NADH}\) e \(\text{FADH}_2\)).
                    3. **Fosforilação Oxidativa (nas Cristas Mitocondriais):** Os elétrons de alta energia de NADH e FADH₂ percorrem a cadeia transportadora de elétrons, bombeando prótons \(\text{H}^+\) para o espaço intermembrana. O fluxo de retorno dos prótons pela **ATP sintase** produz a maior parte dos ATPs. O **oxigênio (\(\text{O}_2\)) atua como o aceptor final de elétrons e prótons**, formando água pura!
                    
                    ### Fermentação Láctica e Dores Musculares
                    Em esforço físico intenso onde a entrega de \(O_2\) é insuficiente para a demanda muscular, as fibras musculares esqueléticas realizam **fermentação láctica** no citosol para regenerar o \(NAD^+\) e manter a glicólise ativa, gerando acúmulo temporário de lactato.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Papel do Oxigênio", "O2 é o Aceptor Final de Elétrons na Crista Mitocondrial", "Liga-se a elétrons e prótons H⁺ para formar H2O"),
                    FormulaOrSyntaxItem("Inibição por Cianeto e Monóxido de Carbono", "Bloqueia o citocromo oxidase na cadeia respiratória", "Impede consumo de O2 e zera produção de ATP mitocondrial")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Intoxicação por Cianeto",
                        codeOrFormula = """\\text{Cianeto inibe a citocromo oxidase} \\implies \\text{Paralisação da cadeia respiratória}""",
                        explanation = "O cianeto impede a transferência final de elétrons para o oxigênio nas cristas mitocondriais. A célula não consegue mais sintetizar ATP por via oxidativa, provocando asfixia celular em minutos mesmo com abundância de oxigênio no sangue."
                    )
                ),
                goldTip = "O oxigênio NÃO participa da glicólise nem do ciclo de Krebs: sua função exclusiva na respiração celular é atuar como o ACEPTOR FINAL de elétrons e hidrogênios na fosforilação oxidativa!"
            ),

            "bio_cit_05" to BioEntry(
                theoryMarkdown = """
                    # Bioquímica Celular: Proteínas, Catálise Enzimática e Vitaminas
                    
                    ### Proteínas e Cinética Enzimática
                    As enzimas são catalisadores biológicos (na sua maioria proteínas) que **aceleram dramaticamente a velocidade das reações químicas reduzindo a energia de ativação necessária**, sem serem consumidas no processo.
                    • **Fatores que afetam a atividade enzimática:**
                      1. **Temperatura:** O aumento da temperatura acelera a reação até uma **temperatura ótima** (em torno de 37 °C no corpo humano). Acima desse limite, a agitação térmica rompe as pontes de hidrogênio da estrutura terciária e quaternária da proteína, provocando a **desnaturação irreversível** e perda do sítio catalítico.
                      2. **pH:** Cada enzima possui uma faixa estrita de pH ótimo (ex: pepsina gástrica pH 2, tripsina duodenal pH 8). Fora da faixa, as cargas dos aminoácidos mudam e a enzima perde a conformação nativa.
                    
                    ### Vitaminas e Carenciais no ENEM
                    • **Lipossolúveis (armazenáveis no tecido adiposo e fígado):** A (visão/retinol - xeroftalmia e cegueira noturna), D (calciferol/ossos - raquitismo em crianças e osteomalácia), E (tocoferol/antioxidante) e K (filoquinona/coagulação sanguínea).
                    • **Hidrossolúveis (eliminadas na urina diariamente):**
                      - **Vitamina C (Ácido Ascórbico):** Síntese de colágeno e absorção de ferro inorgânico. Sua carência severa causa **escorbuto** (sangramento gengival, perda de dentes e hemorragias).
                      - **Vitamina B1 (Tiamina):** Metabolismo de carboidratos. Sua carência provoca o **beribéri** (polineurite e fraqueza muscular).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Ação Catalítica Enzimática", "Redução da Energia de Ativação (Ea)", "Acelera reações termodinamicamente espontâneas"),
                    FormulaOrSyntaxItem("Carência de Vitamina C", "Falta de Ácido Ascórbico -> Defeito na síntese de colágeno -> Escorbuto", "Histórico das navegações marítimas no ENEM")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Febre Alta Prolongada",
                        codeOrFormula = """\\text{Temperatura acima de 41 °C} \\implies \\text{Risco de desnaturação proteica e enzimática}""",
                        explanation = "A febre moderada é benéfica para estimular o sistema imune. No entanto, temperaturas sustentadas acima de 41 °C colocam a vida em risco por provocarem desnaturação de enzimas vitais no sistema nervoso central."
                    )
                ),
                goldTip = "As enzimas aceleram a reação diminuindo a energia de ativação, mas NÃO alteram o saldo final da variação de entalpia (ΔH) nem o equilíbrio químico da reação!"
            ),

            // ==========================================
            // MÓDULO 5: EVOLUÇÃO BIOLÓGICA
            // ==========================================
            "bio_evo_01" to BioEntry(
                theoryMarkdown = """
                    # Evidências da Evolução: Homologia vs Analogia e Adaptação
                    
                    A evolução biológica é a descendência com modificação a partir de ancestrais comuns ao longo de escalas de tempo geológico.
                    
                    ### Homologia vs Analogia (Conceito Chave do ENEM!)
                    • **Órgãos Homólogos (Divergência Adaptativa ou Irradiação Adaptativa):**
                      - Estruturas com a **mesma origem embrionária e mesmo plano anatômico básico**, compartilhadas por parentesco evolutivo comum, que podem desempenhar funções semelhantes ou diferentes adaptadas a nichos distintos.
                      - **Exemplo clássico:** O membro anterior de mamíferos (braço humano, pata dianteira do cavalo, asa do morcego e nadadeira da baleia). Todos possuem úmero, rádio, ulna e carpos!
                    • **Órgãos Análogos (Convergência Evolutiva ou Adaptativa):**
                      - Estruturas com **origens embrionárias e ancestrais totalmente distintas**, que desempenham a **mesma função** em resposta a pressões seletivas ambientais semelhantes.
                      - **Exemplo clássico:** A asa da ave (membro de vertebrado) e a asa da borboleta (apêndice quitinoso de inseto artrópode); ou a forma fusiforme hidrodinâmica do golfinho (mamífero) e do tubarão (peixe cartilaginoso).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Homologia (Divergência)", "Mesma origem embrionária ancestral -> Funções distintas ou adaptadas", "Braço humano, asa de morcego e nadadeira de baleia"),
                    FormulaOrSyntaxItem("Analogia (Convergência)", "Origens embrionárias distintas -> Mesma função por pressões seletivas iguais", "Asa de ave e asa de inseto; tubarão e golfinho")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Tubarões e Golfinhos",
                        codeOrFormula = """\\text{Ambiente aquático pelágico} \\implies \\text{Seleção do corpo hidrodinâmico (Convergência)}""",
                        explanation = "O formato aerodinâmico/hidrodinâmico de tubarões e golfinhos não prova parentesco próximo, mas sim convergência adaptativa imposta pela seleção natural no mesmo meio aquático."
                    )
                ),
                goldTip = "Homologia indica PARENTESCO EVOLUTIVO COMUM (mesmo ancestral); Analogia indica apenas que a seleção natural favoreceu a mesma solução funcional para o mesmo problema ecológico!"
            ),

            "bio_evo_02" to BioEntry(
                theoryMarkdown = """
                    # Teorias Evolutivas: Lamarckismo e a Barreira Somática
                    
                    Jean-Baptiste de Lamarck propôs a primeira teoria coerente de evolução das espécies em 1809 (Filosofia Zoológica).
                    
                    ### Os Dois Pilares do Lamarckismo
                    1. **Lei do Uso e Desuso:** O uso frequente e contínuo de um órgão fortalece-o, desenvolve-o e aumenta suas dimensões; o desuso constante enfraquece-o, atrofia-o e pode levar ao seu desaparecimento.
                    2. **Lei da Herança dos Caracteres Adquiridos:** Todas as modificações anatômicas ou fisiológicas adquiridas por um indivíduo ao longo da sua vida são transmitidas diretamente aos seus descendentes genéticos.
                    • **Exemplo de Lamarck:** As girafas ancestrais de pescoço curto esticavam o pescoço continuamente para alcançar folhas no topo das acácias; pelo esforço, o pescoço cresceu e essa característica alongada foi transmitida aos filhotes.
                    
                    ### Por que o Lamarckismo foi Refutado?
                    August Weismann provou experimentalmente a existência da **Barreira Somática**: alterações ocorridas no corpo (células somáticas) durante a vida de um organismo **NÃO alteram o código genético dos gametas (células germinativas)**. Cortar a cauda de ratos por dezenas de gerações nunca gerou um único filhote sem cauda!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Postulado Lamarckista", "O meio ambiente INDUZ a modificação ativa no indivíduo", "Refutado pela genética moderna"),
                    FormulaOrSyntaxItem("Barreira de Weismann", "Célula somática não transmite alterações ao DNA das células germinativas", "Mutações precisam ocorrer na linhagem germinativa para serem herdáveis")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Musculação de Halterofilista",
                        codeOrFormula = "\\text{Hipertrofia muscular adquirida na academia NÃO é transmitida aos filhos}",
                        explanation = "A hipertrofia muscular decorre de estresse mecânico sobre células musculares somáticas. Como não há alteração nas sequências de DNA dos espermatozoides, os filhos não nascem musculosos."
                    )
                ),
                goldTip = "Identifique a visão lamarckista no ENEM: qualquer texto que afirme que o animal 'desenvolveu uma estrutura para se adaptar' ou 'em resposta à necessidade do meio' é LAMARCKISTA!"
            ),

            "bio_evo_03" to BioEntry(
                theoryMarkdown = """
                    # Darwinismo e a Síntese Moderna (Neodarwinismo)
                    
                    ### O Darwinismo Original (Charles Darwin e Alfred Wallace)
                    Em 1859, Darwin publicou 'A Origem das Espécies', fundamentada na **Seleção Natural**:
                    1. Dentro de qualquer população natural existe **variabilidade individual preexistente** (indivíduos não são idênticos).
                    2. Como os recursos do meio são limitados, trava-se uma luta pela sobrevivência.
                    3. Os indivíduos portadores de características mais vantajosas para aquele ambiente sobrevivem e deixam mais descendentes férteis (**reprodução diferencial**).
                    • **A lacuna de Darwin:** Darwin não conseguiu explicar de onde surgia a variabilidade inicial nem como ela era herdada, pois a genética de Mendel era desconhecida em sua época!
                    
                    ### O Neodarwinismo (Teoria Sintética da Evolução)
                    Unificou a seleção natural com as descobertas da Genética Mendeliana e Molecular:
                    • **Origem da Variabilidade:**
                      1. **Mutações Gênicas e Cromossômicas:** Única fonte primária de alelos novos. Ocorrem de forma aleatória e espontânea (e nunca orientadas pela necessidade do organismo!).
                      2. **Recombinação Gênica:** Mistura aleatória de alelos por crossing-over e segregação independente dos cromossomos na meiose.
                    • **Ação Seletiva do Meio:** O ambiente **seleciona os indivíduos mais aptos**, aumentando a frequência dos alelos favoráveis e diminuindo os desfavoráveis ao longo das gerações.
                    
                    ### Superbactérias e Resistência a Antibióticos no ENEM
                    Os antibióticos **NÃO causam mutações e NÃO induzem as bactérias a ficarem resistentes**! As bactérias resistentes já existiam previamente na população por mutação aleatória prévia; o antibiótico atua como um poderoso **agente de seleção natural**, eliminando as sensíveis e selecionando as resistentes que proliferam desimpedidas!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Fórmula do Neodarwinismo", "Variabilidade (Mutações + Recombinação) + Seleção Natural = Evolução", "Síntese Moderna da Biologia"),
                    FormulaOrSyntaxItem("Seleção de Superbactérias", "Uso indiscriminado de antibióticos -> Elimina sensíveis -> Seleciona cepas resistentes", "Exemplo clássico de seleção direcional contemporânea")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Inseticida contra Mosquitos",
                        codeOrFormula = "\\text{O inseticida NÃO torna o mosquito resistente; ele SELECIONA os resistentes preexistentes}",
                        explanation = "A aplicação contínua de um inseticida extermina a imensa maioria dos mosquitos suscetíveis. Alguns poucos indivíduos que já portavam mutação genética de resistência sobrevivem e geram populações imunes ao veneno."
                    )
                ),
                goldTip = "O antibiótico ou inseticida NUNCA cria resistência: ele funciona como um FILTRO SELETIVO que extermina os vulneráveis e poupa os mutantes previamente resistentes!"
            ),

            "bio_evo_04" to BioEntry(
                theoryMarkdown = """
                    # Evolução Humana e os Marcos da Hominização
                    
                    A linhagem evolutiva humana não descende dos macacos modernos atuais (como chimpanzés e gorilas), mas compartilha com eles um **ancestral comum que viveu há cerca de 6 a 7 milhões de anos** no continente africano.
                    
                    ### Os Grandes Marcos Bioculturais da Hominização
                    1. **Bipedalismo:** Transição postural e anatômica mais antiga. O bipedalismo adaptou os primeiros hominíneos à vida nas savanas abertas africanas, liberando os membros anteriores (mãos) da função locomotora.
                    2. **Oponibilidade do Polegar e Uso de Ferramentas:** Mãos livres com destreza motora fina permitiram manipular objetos e fabricar ferramentas líticas de pedra lascada (**Homo habilis**).
                    3. **Encefalização e Domínio do Fogo:** Aumento expressivo do volume e complexidade do córtex cerebral. O **Homo erectus** domesticou o fogo, cozinhou alimentos (aumentando a absorção de nutrientes e reduzindo o gasto energético da digestão), expandiu sua dieta e migrou para a Eurásia.
                    4. **Homo neanderthalensis e Homo sapiens:** Coexistiram na Europa e Ásia durante o Pleistoceno, com cruzamentos genéticos comprovados pelo sequenciamento de DNA fóssil. O Homo sapiens consolidou linguagem complexa, arte simbólica, agricultura e domesticação animal.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Sequência de Hominização", "Bipedalismo -> Mãos livres -> Ferramentas líticas -> Domínio do Fogo -> Linguagem simbólica", "Evolução biocultural dos hominíneos"),
                    FormulaOrSyntaxItem("Ancestral Comum Homem-Chimpanzé", "Hominídeos compartilham ~98,5% de identidade no DNA com chimpanzés", "Ramos filogenéticos irmãos derivados de ancestral comum africano")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Cozimento de Alimentos e Tamanho Cerebral",
                        codeOrFormula = """\\text{Domínio do fogo pelo } Homo erectus \\implies \\text{Maior aporte calórico para sustentar o cérebro}""",
                        explanation = "O cozimento quebra fibras e desnatura proteínas, facilitando a mastigação e digestão humana e fornecendo o excedente energético diário necessário para nutrir um encéfalo com alto consumo metabólico de glicose."
                    )
                ),
                goldTip = "O ser humano NÃO veio do chimpanzé! A espécie humana e o chimpanzé são 'primos evolutivos' que divergiram de um mesmo ramo ancestral comum no Mioceno!"
            )
        )
    }

    private val exerciseMap: Map<String, List<MathExercise>> by lazy {
        mapOf(
            // MÓDULO 4
            "bio_cit_01" to listOf(
                MathExercise(
                    id = "ex_bio_cit_01_1",
                    title = "ENEM: Plasmólise Celular e Conservação de Alimentos",
                    problemStatement = "O método ancestral de conservação de carne de sol através da adição de grande quantidade de cloreto de sódio (salga) e a produção de compotas de frutas com calda de açúcar muito concentrada são técnicas que impedem a proliferação de bactérias e fungos decompositores. Essa preservação alimentar é garantida porque os microrganismos em contato com a carne salgada sofrem:",
                    options = listOf(
                        "aumento exagerado da pressão osmótica interna com lise da parede celular.",
                        "perda intensa de água por osmose para o meio hipertônico externo, desidratando e inibindo seu metabolismo.",
                        "inibição irreversível da transcrição do RNA mensageiro causada por íons de cloro.",
                        "formação de esporos que aceleram a fermentação alcoólica dos lipídios da carne.",
                        "entrada massiva de soluto por transporte ativo com sobrecarga dos ribossomos."
                    ),
                    correctIndex = 1,
                    hint = "A carne salgada cria um meio altamente hipertônico (muito soluto). A água sai das bactérias em direção ao sal por osmose.",
                    stepByStepSolution = "A alternativa B está correta. A salga e o xarope de açúcar criam um meio extracelular hiperosmótico (hipertônico). Bactérias e fungos contaminantes perdem água rapidamente por osmose para o ambiente salgado/açucarado, sofrendo plasmólise e desidratação severa, o que paralisa suas reações metabólicas e impede a degradação do alimento."
                )
            ),

            "bio_cit_02" to listOf(
                MathExercise(
                    id = "ex_bio_cit_02_1",
                    title = "ENEM: Complexo Golgiense e Formação do Acrossomo",
                    problemStatement = "Durante o processo de espermiogênese no testículo, a espermátide sofre profunda diferenciação morfológica para transformar-se em espermatozoide maduro. Na região anterior da cabeça do gameta forma-se o acrossomo, uma vesícula rica em enzimas digestivas (como a hialuronidase) responsável por perfurar a zona pelúcida do ovócito na fecundação. A organela citoplasmática responsável pela síntese e empacotamento das enzimas no acrossomo é o:",
                    options = listOf(
                        "Complexo Golgiense.",
                        "Centríolo diplossômico.",
                        "Peroxissomo hepático.",
                        "Vacúolo de suco celular.",
                        "Retículo sarcoplasmático liso."
                    ),
                    correctIndex = 0,
                    hint = "O acrossomo é uma bolsa de enzimas digestivas formada a partir da fusão de vesículas do Complexo de Golgi.",
                    stepByStepSolution = "A alternativa A está correta. O acrossomo do espermatozoide é um lisossomo especializado que se origina a partir da condensação e fusão das vesículas do Complexo Golgiense. Ele contém hidrolases ácidas (principalmente hialuronidase) indispensáveis para digerir o envoltório glicoproteico que cerca o ovócito durante a penetração espermática."
                )
            ),

            "bio_cit_03" to listOf(
                MathExercise(
                    id = "ex_bio_cit_03_1",
                    title = "ENEM: Evidências da Teoria Endossimbiótica",
                    problemStatement = "A Teoria Endossimbiótica estabelece que organelas produtoras de energia celular (como mitocôndrias e cloroplastos) evoluíram a partir de ancestrais procariontes primitivos fagocitados por eucariontes primitivos. Qual das alternativas a seguir apresenta uma característica exclusiva dessas organelas que comprova sua ancestralidade bacteriana independente?",
                    options = listOf(
                        "Presença de envoltório nuclear carioteca e cromossomos lineares com histonas.",
                        "Presença de DNA próprio circular e ribossomos do tipo 70S semelhantes aos de bactérias.",
                        "Capacidade de sintetizar proteínas exclusivamente fora da célula hospedeira.",
                        "Ausência total de lipídios nas membranas externas que delimitam a organela.",
                        "Dependência de ribozimas virais para realizar os processos de transcrição."
                    ),
                    correctIndex = 1,
                    hint = "Mitocôndrias e bactérias compartilham DNA circular sem histonas e ribossomos 70S menores.",
                    stepByStepSolution = "A alternativa B está correta. Mitocôndrias e cloroplastos guardam vestígios indiscutíveis de seu passado procariótico de vida livre: possuem genoma próprio circular (idêntico ao nucleoide bacteriano), ribossomos 70S funcionais que realizam síntese proteica interna e capacidade de divisão binária independente da mitose celular."
                )
            ),

            "bio_cit_04" to listOf(
                MathExercise(
                    id = "ex_bio_cit_04_1",
                    title = "ENEM: Cadeia Transportadora de Elétrons e Aceptor Final de Oxigênio",
                    problemStatement = "Em uma aula prática de fisiologia celular, avaliou-se o consumo de oxigênio por mitocôndrias isoladas em tubo de ensaio. Quando se adicionou cianeto à solução, a síntese de ATP cessou imediatamente e o consumo de gás oxigênio foi zerado. Isso comprova que na respiração celular o oxigênio atua como:",
                    options = listOf(
                        "o doador inicial de elétrons para a quebra da glicose na glicólise citosólica.",
                        "o substrato catalítico que ativa o ciclo do ácido cítrico na matriz mitocondrial.",
                        "o aceptor final de elétrons e prótons na crista mitocondrial, formando moléculas de água.",
                        "um inibidor alostérico que bloqueia as permeases de glicose da membrana.",
                        "o catalisador enzimático responsável pela fermentação alcoólica de leveduras."
                    ),
                    correctIndex = 2,
                    hint = "O oxigênio recebe os elétrons no final da cadeia respiratória nas cristas mitocondriais, unindo-se aos prótons H⁺ para formar água.",
                    stepByStepSolution = "A alternativa C está correta. Na etapa de fosforilação oxidativa nas cristas mitocondriais, o gás oxigênio (O2) tem a função bioquímica vital de ser o aceptor final de elétrons que percorreram os complexos da cadeia respiratória. Ao receber esses elétrons e ligar-se a íons H⁺, ele é reduzido a H2O, mantendo o fluxo de elétrons e o gradiente de prótons que alimenta a ATP sintase."
                )
            ),

            "bio_cit_05" to listOf(
                MathExercise(
                    id = "ex_bio_cit_05_1",
                    title = "ENEM: Cinética Enzimática e Desnaturação Térmica",
                    problemStatement = "O gráfico de velocidade de uma reação catalisada pela enzima amilase salivar em função da temperatura exibe crescimento contínuo de velocidade entre 10 °C e 37 °C, seguido por queda vertiginosa da atividade enzimática quando a temperatura ultrapassa 50 °C, zerando a reação a 70 °C. O colapso da atividade catalítica nessa faixa térmica elevada é causado pelo(a):",
                    options = listOf(
                        "esgotamento irreversível de todos os reagentes de amido contidos no meio.",
                        "desnaturação proteica com rompimento de ligações de hidrogênio e alteração espacial do sítio ativo.",
                        "aumento na energia de ativação imposto pelo oxigênio do ar atmosférico.",
                        "transformação da enzima em um ácido graxo saturado insensível à catálise.",
                        "neutralização dos aminoácidos por formação de ligações peptídicas extras."
                    ),
                    correctIndex = 1,
                    hint = "O calor excessivo desnatura as proteínas, desfazendo a conformação tridimensional do sítio ativo onde o substrato se encaixa.",
                    stepByStepSolution = "A alternativa B está correta. As enzimas são proteínas com conformação espacial tridimensional nativa mantida por interações químicas fracas (pontes de hidrogênio, interações hidrofóbicas). Quando a temperatura sobe muito além da faixa ótima, o aumento da agitação molecular desestabiliza essas interações, desnaturando a enzima e destruindo a geometria complementar do sítio ativo (modelo chave-fechadura), o que anula a catálise."
                )
            ),

            // MÓDULO 5
            "bio_evo_01" to listOf(
                MathExercise(
                    id = "ex_bio_evo_01_1",
                    title = "ENEM: Homologia vs Analogia Evolutiva",
                    problemStatement = "Ao comparar a anatomia comparada de vertebrados e invertebrados, biólogos constatam que o membro anterior de um cavalo, a nadadeira dianteira de uma baleia e a asa de um morcego compartilham o mesmo arranjo ósseo basal (úmero, rádio, ulna e falanges), enquanto a asa de um morcego e a asa de uma mariposa desempenham a mesma função de voo, mas possuem estruturas e origens embriológicas inteiramente diferentes. A relação evolutiva entre a asa de morcego e a nadadeira de baleia, e entre a asa de morcego e a asa de mariposa, são classificadas, respectivamente, como:",
                    options = listOf(
                        "Homologia (divergência adaptativa) e Analogia (convergência evolutiva).",
                        "Analogia (convergência evolutiva) e Homologia (divergência adaptativa).",
                        "Homologia e Homologia decorrentes de cruzamento reprodutivo.",
                        "Analogia e Mimetismo batesiano adaptativo.",
                        "Convergência evolutiva e Irradiação embriológica pura."
                    ),
                    correctIndex = 0,
                    hint = "Mesma origem embrionária ancestral = Homologia (morcego e baleia); Origens diferentes com mesma função por pressão do meio = Analogia (morcego e mariposa).",
                    stepByStepSolution = "A alternativa A está correta. A asa de morcego e a nadadeira de baleia derivam do mesmo ancestral tetrápode comum, compartilhando a mesma arquitetura óssea homóloga resultante de irradiação adaptativa. Por sua vez, a asa de morcego (esqueleto ósseo de vertebrado) e a asa de mariposa (quitina de inseto) têm origens embrionárias distintas e desempenham a mesma função de voo devido a pressões seletivas similares do ar, caracterizando estruturas análogas por convergência adaptativa."
                )
            ),

            "bio_evo_02" to listOf(
                MathExercise(
                    id = "ex_bio_evo_02_1",
                    title = "ENEM: Refutação da Herança de Caracteres Adquiridos de Lamarck",
                    problemStatement = "No século XIX, o naturalista alemão August Weismann realizou um experimento clássico cortando a cauda de casais de camundongos por vinte gerações consecutivas. Ao longo de todo o experimento, todos os filhotes continuaram nascendo com caudas de comprimento perfeitamente normal. O resultado experimental de Weismann constitui evidência científica contrária à teoria evolutiva de:",
                    options = listOf(
                        "Lamarck, demonstrando que modificações fenotípicas adquiridas nas células somáticas não se transmitem ao material genético germinativo.",
                        "Darwin, provando que a seleção natural não atua sobre características corporais externas.",
                        "Mendel, refutando a segregação independente dos alelos dominantes durante a meiose.",
                        "Pasteur, comprovando que a biogênese só é válida para microrganismos unicelulares.",
                        "Linneu, demonstrando que espécies animais não podem ser classificadas por morfologia."
                    ),
                    correctIndex = 0,
                    hint = "Lamarck defendia a 'Lei da Herança dos Caracteres Adquiridos', segundo a qual o que se adquire em vida seria passado aos filhos.",
                    stepByStepSolution = "A alternativa A está correta. O experimento de Weismann demonstrou a existência da barreira somática (teoria do plasma germinativo). Mutilações, cicatrizes ou alterações que atingem tecidos corporais somáticos não alteram o DNA contido nos óvulos ou espermatozoides (células germinativas), refutando categoricamente a Lei da Herança dos Caracteres Adquiridos postulada por Lamarck."
                )
            ),

            "bio_evo_03" to listOf(
                MathExercise(
                    id = "ex_bio_evo_03_1",
                    title = "ENEM: Seleção Natural e Resistência a Antibióticos",
                    problemStatement = "O uso indiscriminado de antibióticos em hospitais e na pecuária intensiva tem acelerado o surgimento global de infecções causadas por 'superbactérias' resistentes a múltiplos fármacos. Sob a ótica da Teoria Sintética da Evolução (Neodarwinismo), o mecanismo biológico responsável por esse fenômeno consiste no fato de que:",
                    options = listOf(
                        "o antibiótico induz mutações orientadas no cromossomo bacteriano para permitir a sobrevivência da colônia.",
                        "as bactérias aprendem a metabolizar o antibiótico como fonte de carbono e transmitem o hábito à progênie.",
                        "a presença do antibiótico atua como um agente de seleção natural, eliminando as bactérias sensíveis e selecionando cepas resistentes preexistentes.",
                        "o medicamento causa a fusão de membranas celulares bacterianas formando um biofilme impenetrável.",
                        "os antibióticos estimulam o crossing-over mitótico entre espécies virais e bacterianas."
                    ),
                    correctIndex = 2,
                    hint = "O antibiótico não causa a mutação: ele é o agente seletivo do meio que elimina as bactérias normais e seleciona as mutantes resistentes que já existiam.",
                    stepByStepSolution = "A alternativa C está correta. A variabilidade genética nas bactérias surge previamente e de forma espontânea por mutações casuais ou transferência horizontal de genes (plasmídeos R). A administração de um antibiótico exerce forte pressão de seleção direcional: ele elimina as bactérias suscetíveis normais, restando apenas as raras variantes resistentes preexistentes, que se multiplicam livremente sem competição e tornam a infecção resistente."
                )
            ),

            "bio_evo_04" to listOf(
                MathExercise(
                    id = "ex_bio_evo_04_1",
                    title = "ENEM: Bipedalismo e Marcos da Hominização",
                    problemStatement = "O registro fóssil e as análises morfoanatômicas indicam que a transição crucial mais antiga na separação da linhagem dos hominíneos a partir do ancestral comum com os grandes primatas africanos foi o surgimento do bipedalismo ereto. Do ponto de vista adaptativo e evolutivo, a postura bípede favoreceu os primeiros hominíneos nas savanas africanas principalmente porque:",
                    options = listOf(
                        "permitiu o aumento instantâneo do volume cerebral para mais de 1.400 cm³ no gênero Australopithecus.",
                        "liberou os membros anteriores da função de suporte do peso, possibilitando carregar alimentos, crias e manipular ferramentas líticas.",
                        "eliminou a necessidade de consumir água doce por condensação de vapor nos pulmões.",
                        "tornou os hominíneos imunes às toxinas liberadas pelas serpentes terrestres.",
                        "acelerou a corrida em quatro patas nas copas densas das florestas úmidas de galeria."
                    ),
                    correctIndex = 1,
                    hint = "Andar em dois pés liberou as mãos para transportar alimentos, segurar filhotes e, posteriormente, fabricar ferramentas.",
                    stepByStepSolution = "A alternativa B está correta. O bipedalismo foi a primeira grande inovação adaptativa na hominização (presente em Australopithecus muito antes do grande aumento da caixa craniana). Andar ereto sobre dois membros inferiores liberou os braços e as mãos da locomoção, permitindo transportar alimentos através das savanas abertas, carregar crias em segurança e viabilizar a destreza motora para o futuro fabrico e manuseio de ferramentas de pedra."
                )
            )
        )
    }
}
