package com.example.data.languages

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático e Exercícios Comentados
 * Módulo 3: Literatura Brasileira (e bases de Portugal)
 * - ling_lit_01: Era Colonial (Quinhentismo, Barroco e Arcadismo)
 * - ling_lit_02: Romantismo (As 3 gerações da poesia e a prosa romântica)
 * - ling_lit_03: Realismo, Naturalismo e Parnasianismo (Foco absoluto em Machado de Assis e Aluísio Azevedo)
 * - ling_lit_04: Simbolismo e Pré-Modernismo (Euclides da Cunha, Lima Barreto e Monteiro Lobato)
 * - ling_lit_05: Modernismo Brasileiro (A Semana de Arte Moderna de 1922 e as 3 fases modernistas)
 * - ling_lit_06: Literatura Contemporânea (Poesia marginal, concretismo e tropicalismo)
 */
object LanguagesLiteratureDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "ling_lit_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Era Colonial: Quinhentismo, Barroco e Arcadismo

                    A literatura produzida no Brasil antes da Independência (1822) reflete as tensões coloniais, a catequese jesuítica, o dilema espiritual da Contrarreforma e o Iluminismo mineiro.

                    ### 1. Quinhentismo (Século XVI)
                    • **Literatura Informativa / de Viagem:** A *Carta de Pero Vaz de Caminha* ao rei D. Manuel I. Visão do colonizador europeu: fascínio pelo exotismo da terra, espanto com a nudez indígena e ambição econômica mercantil.
                    • **Literatura Jesuítica / Catequética:** Padre José de Anchieta e seus autos teatrais bilíngues (tupi-português) com função doutrinária católica.

                    ### 2. Barroco (Século XVII)
                    • **Dualismo e Conflito:** Espírito contra Matéria, Céu contra Inferno, Deus contra o Pecado. Crise existencial gerada pela Contrarreforma católica.
                    • **Gregório de Matos ('Boca do Inferno'):** Primeiro grande poeta brasileiro. Poesia lírico-amorosa (pecado e perdão), poesia religiosa (conflito espiritual) e poesia satírica (crítica mordaz à corrupção da nobreza e do clero na Bahia colonial).
                    • **Padre Antônio Vieira:** *Sermões* com extraordinário rigor retórico argumentativo, combatendo a hipocrisia dos colonos e a escravização indígena.

                    ### 3. Arcadismo (Século XVIII)
                    • **Iluminismo e Neoclassicismo:** Razão, clareza, harmonia e simplicidade pastoril em oposição aos exageros barrocos.
                    • **Lemas Latinos:** *Fugere urbem* (fugir da cidade), *Locus amoenus* (lugar agradável e sereno), *Carpe diem* (aproveitar o dia passageiro), *Aurea mediocritas* (equilíbrio material dourado).
                    • **Autores Mineiros e Inconfidência:** Tomás Antônio Gonzaga (*Marília de Dirceu* e as *Cartas Chilenas*, sátira política ao governador de Minas Gerais) e Cláudio Manuel da Costa.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Barroco (Cultismo x Conceptismo)", "Cultismo = Jogo de palavras e metáforas | Conceptismo = Jogo de ideias e lógica", "Conflito espiritual dualista"),
                    FormulaOrSyntaxItem("Arcadismo (Pastoralismo)", "Fugere urbem, Carpe diem, Aurea mediocritas", "Razão iluminista contra o rebuscamento"),
                    FormulaOrSyntaxItem("Gregório de Matos", "Lírica, Religiosa e Satírica", "Crítica pioneira às elites coloniais")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Cultismo e Conceptismo em Vieira",
                        codeOrFormula = "Sermão da Sexagésima: pregar é semear ideias com lógica",
                        explanation = "Vieira critica pregadores cultistas que usam palavras difíceis sem substância. Para ele, pregar deve convencer a mente e transformar a conduta através de conceitos claros."
                    )
                ),
                goldTip = "No ENEM: Se o poema colonial mineiro fala em pastores, ovelhas e natureza idealizada fingida, é Arcadismo! Se fala em pecado, culpa e barro transitório, é Barroco!"
            )

            "ling_lit_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # O Romantismo Brasileiro: Poesia e Prosa

                    Após a Independência de 1822, o Romantismo foi o projeto estético e político encarregado de forjar uma **identidade nacional brasileira**.

                    ### 1. As Três Gerações da Poesia Romântica
                    • **1ª Geração (Nacionalista / Indianista):** Gonçalves Dias (*Canção do Exílio*, *I-Juca Pirama*). O indígena é idealizado como o 'bom selvagem' e herói cavaleiresco nacional; natureza tropical exuberante e ufanismo patriótico.
                    • **2ª Geração (Ultrarromântica / Mal do Século / Byroniana):** Álvares de Azevedo (*Noite na Taverna*, *Lira dos Vinte Anos*), Fagundes Varela. Fuga da realidade pelo sonho, morte e devaneio mórbido; melancolia profunda, pessimismo, tédio e idealização inatingível da mulher virgem.
                    • **3ª Geração (Condoreira / Social / Hugoana):** Castro Alves (*O Navio Negreiro*, *Espumas Flutuantes*). O poeta assume o papel social do 'condor' que sobrevoa a história: denúncia indignada da escravidão, poesia grandiloquente e amor carnal/sensual consumado.

                    ### 2. A Prosa Romântica de José de Alencar
                    Alencar buscou cobrir todas as facetas do território e da alma nacional:
                    • **Romance Indianista:** *O Guarani*, *Iracema* (o indígena mítico na gênese da nação).
                    • **Romance Urbano:** *Senhora*, *Lucíola* (os costumes e casamentos por interesse da burguesia carioca).
                    • **Romance Regionalista:** *O Gaúcho*, *O Sertanejo* (costumes do interior do país).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("1ª Geração (Nacionalista)", "Índio herói + Natureza pátria ufanista", "Gonçalves Dias"),
                    FormulaOrSyntaxItem("2ª Geração (Ultrarromântica)", "Subjetivismo extremo + Melancolia + Tédio + Morte", "Álvares de Azevedo"),
                    FormulaOrSyntaxItem("3ª Geração (Condoreira)", "Engajamento social + Abolicionismo + Amor sensual", "Castro Alves")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Navio Negreiro no ENEM",
                        codeOrFormula = "'Senhor Deus dos desgraçados! Dizei-me vós, Senhor Deus!'",
                        explanation = "A poesia de Castro Alves apela diretamente à justiça divina e à indignação republicana para denunciar o crime histórico do tráfico transatlântico de escravizados."
                    )
                ),
                goldTip = "Poesia romântica no ENEM: se houver sofrimento noturno mórbido e fuga, é 2ª geração; se houver denúncia política abolicionista com metáforas grandiosas, é Castro Alves!"
            )

            "ling_lit_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Realismo, Naturalismo e Parnasianismo

                    Na segunda metade do século XIX, sob impacto do Positivismo, Evolucionismo e Cientificismo, os autores rompem com a idealização romântica para dissecar a sociedade nua e crua.

                    ### 1. Realismo: Machado de Assis (O Maior Clássico do ENEM)
                    Em 1881, com *Memórias Póstumas de Brás Cubas*, Machado inaugura o Realismo brasileiro:
                    • **Características Machadianas:** Narrador não confiável (primeira pessoa egoísta e cínica), quebra da linearidade cronológica, metalinguagem e diálogo sarcástico com o leitor.
                    • **Pessimismo e Desilusão:** Desnudamento das máscaras sociais; o amor, a religião e a filantropia revelam-se calculismos de classe.
                    • **Obras Canônicas no ENEM:** *Dom Casmurro* (a dúvida insolúvel sobre Capitu, construída pela ótica ciumenta e possessiva de Bentinho), *Quincas Borba* (o delírio do Humanitismo: 'Ao vencedor, as batatas!').

                    ### 2. Naturalismo: Aluísio Azevedo e o Determinismo
                    • O ser humano reduzido ao seu instinto animal (**zoomorfização**).
                    • **Determinismo Rígido de Taine:** O indivíduo é produto fatal de sua raça (biologia), meio (ambiente social) e momento histórico.
                    • **O Cortiço (1890):** O próprio cortiço é o protagonista coletivo, degradando os indivíduos (a transformação do português trabalhador Jerônimo sob o calor e sensualidade do meio tropical).

                    ### 3. Parnasianismo: Olavo Bilac
                    • 'Arte pela arte': busca da perfeição métrica, rimas ricas e raras, temática greco-latina e culto à forma pura do soneto, recusando temas sociais engajados.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Realismo (Machado)", "Análise psicológica sutil + Ironia fina + Narrador cínico", "Dom Casmurro, Memórias Póstumas"),
                    FormulaOrSyntaxItem("Naturalismo (Aluísio)", "Determinismo biológico/social + Zoomorfização + Instintos", "O Cortiço"),
                    FormulaOrSyntaxItem("Parnasianismo (Bilac)", "Arte pela Arte + Métrica rigorosa + Impessoalidade", "Sonetos impecáveis")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Bentinho e Capitu em Dom Casmurro",
                        codeOrFormula = "A impossibilidade do veredito: o ponto de vista é unilateral",
                        explanation = "O ENEM frequentemente cobra a compreensão de que o romance não é sobre adultério provado, mas sobre a construção obsessiva e persecutória de um narrador machista e inseguro."
                    )
                ),
                goldTip = "Se o texto descrever pessoas como feras impelidas por instintos sexuais ou calor do ambiente, é Naturalismo (*O Cortiço*)!"
            )

            "ling_lit_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Simbolismo e Pré-Modernismo (A Virada do Século)

                    O período de transição entre 1900 e 1922 revela as fraturas de um Brasil republicano excludente e arcaico.

                    ### 1. Simbolismo (Fim do Século XIX)
                    • Cruz e Sousa ('O Cisne Negro') e Alphonsus de Guimaraens.
                    • Misticismo, espiritualidade transcendental, sinestesia (cruzamento de sensações sensoriais: 'som doce', 'luz gélida'), musicalidade e predomínio da cor branca.
                    • Dor existencial e reflexo da condição negra no Brasil pós-abolição em Cruz e Sousa.

                    ### 2. Pré-Modernismo (1902–1922): Não é uma Escola, é uma Transição
                    Autores com formas linguísticas ainda tradicionais, mas com **olhar crítico inédito para o Brasil profundo e esquecido**:
                    • **Euclides da Cunha (*Os Sertões*, 1902):** A Guerra de Canudos. Tríptico 'A Terra, O Homem, A Luta'. A denúncia do massacre da população sertaneja de Antônio Conselheiro pelo Exército republicano ('O sertanejo é, antes de tudo, um forte').
                    • **Lima Barreto (*Triste Fim de Policarpo Quaresma*):** Crítica feroz ao nacionalismo ingênuo, à burocracia do Estado e ao racismo estrutural da Primeira República. Linguagem fluida, jornalística e antielitista.
                    • **Monteiro Lobato (*Urupês*):** Criação do Jeca Tatu, símbolo do caboclo doente e abandonado pelo poder público ('O Jeca não é assim: está assim').
                    • **Augusto dos Anjos (*Eu*):** Fusão única de Simbolismo, Parnasianismo e vocabulário científico-biológico da decomposição cadavérica.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Os Sertões (Euclides)", "A Terra, O Homem, A Luta | O sertanejo é um forte", "Guerra de Canudos e crítica ao litoral"),
                    FormulaOrSyntaxItem("Policarpo Quaresma (Lima Barreto)", "Nacionalismo ingênuo destruído pelo autoritarismo", "Crítica social e racial contundente"),
                    FormulaOrSyntaxItem("Simbolismo", "Sinestesia + Musicalidade + Misticismo transcendental", "Cruz e Sousa")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Proposta de Policarpo Quaresma",
                        codeOrFormula = "Oficialização do tupi-guarani como idioma nacional",
                        explanation = "A ingenuidade de Policarpo consiste em tentar resolver os graves problemas sociais do Brasil com medidas burocráticas e ufanistas descoladas da realidade do povo."
                    )
                ),
                goldTip = "Pré-Modernismo no ENEM: foco na denúncia das desigualdades regionais e nas populações marginalizadas (sertanejos de Canudos, subúrbio carioca com Lima Barreto)!"
            )

            "ling_lit_05" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # O Modernismo Brasileiro: Da Semana de 22 às Três Fases

                    O Modernismo é o movimento mais cobrado em toda a história do ENEM.

                    ### 1. A Semana de Arte Moderna (Fevereiro de 1922 - Teatro Municipal de SP)
                    • Rompimento com o academicismo parnasiano e com a cópia submissa de modelos europeus.
                    • Busca por uma arte genuinamente brasileira, integrando a cultura popular, a oralidade e a vanguarda.

                    ### 2. 1ª Fase (1922–1930): Fase Heroica e Destrutiva
                    • **Oswald de Andrade:** *Manifesto Antropófago* (1928) — devorar a cultura europeia colonizadora e digeri-la, mesclando-a com a força nativa e africana para criar uma arte brasileira autônoma. Poema-pílula, humor, quebra sintática.
                    • **Mário de Andrade:** *Macunaíma* (1928) — o 'herói sem nenhum caráter', síntese mítica da identidade fragmentada do brasileiro.
                    • **Manuel Bandeira:** *Os Sapos* (sátira avassaladora ao Parnasianismo) e a 'poética do cotidiano'.

                    ### 3. 2ª Fase (1930–1945): Fase de Consolidação e Maturidade
                    • **Poesia Social e Filosófica:** Carlos Drummond de Andrade (*Sentimento do Mundo*, *A Rosa do Povo* — engajamento político antifascista, solidariedade e pessimismo existencial) e Vinicius de Moraes.
                    • **Romance de 30 (Neorregionalismo Social):** Graciliano Ramos (*Vidas Secas* — a desumanização de Fabiano e sua família pela seca e pela opressão dos poderosos), Jorge Amado (*Capitães da Areia* — meninos de rua em Salvador), Rachel de Queiroz (*O Quinze*).

                    ### 4. 3ª Fase (1945 em diante): Geração Neoclássica e Sondagem Existencial
                    • **Clarice Lispector (*A Hora da Estrela*, *Laços de Família*):** Epifania, fluxo de consciência interior, ruptura do tempo linear, o drama de Macabéa no Rio de Janeiro.
                    • **João Guimarães Rosa (*Grande Sertão: Veredas*):** Recriação neológica da língua portuguesa sertaneja: 'O sertão é do tamanho do mundo'; o sertão como espelho universal da alma humana.
                    • **João Cabral de Melo Neto (*Morte e Vida Severina*):** O 'poeta-engenheiro' da secura, avesso ao sentimentalismo fácil.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Antropofagia (Oswald)", "Devorar a cultura estrangeira e recriá-la na perspectiva brasileira", "Tupi or not tupi, that is the question"),
                    FormulaOrSyntaxItem("Romance de 30 (Graciliano)", "Denúncia da seca, desumanização e desigualdade social", "Vidas Secas e Fabiano"),
                    FormulaOrSyntaxItem("Clarice Lispector", "Epifania, fluxo de consciência, introspecção feminina", "A Hora da Estrela")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Zoomorfização em Vidas Secas",
                        codeOrFormula = "Fabiano se comparando a bicho enquanto a cadela Baleia sonha como humana",
                        explanation = "A inversão de papéis entre Fabiano e a cadela Baleia escancara a violência da exclusão socioeconômica, que retira a dignidade linguística do sertanejo."
                    )
                ),
                goldTip = "Oswald de Andrade devora criticamente (Antropofagia); Drummond olha o mundo com pessimismo e solidariedade; Guimarães Rosa reinventa a língua no sertão!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Literatura Contemporânea: Ditadura, Concretismo e Vozes Plurais

                    A literatura a partir dos anos 1960 reage às violências do regime militar, fragmenta o cânone e incorpora as periferias e as minorias sociais.

                    ### 1. Poesia Concreta (Década de 1950/60)
                    • Irmãos Augusto e Haroldo de Campos, Décio Pignatari.
                    • Fim do verso tradicional; valorização do espaço em branco da página, do aspecto visual-gráfico, da decomposição fonética e dos jogos verbo-visuais (ex: poema 'Beba Coca Cola / Beba Cola / Caco / Caco / Cola / Caco / Cloaca').

                    ### 2. Poesia Marginal (Geração Mimeógrafo - Década de 1970)
                    • Ana Cristina Cesar, Chacal, Cacaso.
                    • Produção independente e artesanal rodada em mimeógrafo, distribuída de mão em mão em bares e portas de cinema para driblar a censura da Ditadura Militar.
                    • Tom coloquial, intimista, despojado, direto e bem-humorado.

                    ### 3. Tropicalismo e Canção Popular
                    • Caetano Veloso, Gilberto Gil, Torquato Neto.
                    • Fusão da antropofagia oswaldiana com guitarras elétricas do rock e ritmos folclóricos brasileiros (baião, samba), desafiando a moral conservadora.

                    ### 4. Prosa e Poesia Contemporânea das Periferias
                    • Carolina Maria de Jesus (*Quarto de Despejo* — a literatura viva da favela do Canindé nos anos 1960).
                    • Conceição Evaristo (o conceito de **'Escrevivência'** — literatura brotada da vivência de mulheres negras).
                    • Geovani Martins, Ferréz, Itamar Vieira Junior (*Torto Arado* — as marcas da escravidão na luta pela terra na Chapada Diamantina).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Concretismo", "Verbi-voco-visual: som, imagem gráfica e palavra integrados", "Décio Pignatari, Irmãos Campos"),
                    FormulaOrSyntaxItem("Poesia Marginal", "Mimeógrafo, linguagem do cotidiano, drible da censura", "Anos 70"),
                    FormulaOrSyntaxItem("Escrevivência (Conceição Evaristo)", "Escrita enraizada na experiência histórica de mulheres negras", "Vozes periféricas e decoloniais")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Torto Arado e Quarto de Despejo no ENEM",
                        codeOrFormula = "Literatura como documento de resistência e protagonismo histórico",
                        explanation = "A narrativa descentraliza o olhar das elites e coloca como narradores os sujeitos subalternizados que reconstroem a memória e a dignidade do país."
                    )
                ),
                goldTip = "No ENEM contemporâneo: textos de Conceição Evaristo e Carolina Maria de Jesus cobram o reconhecimento da Escrevivência como resgate da memória e cidadania!"
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "ling_lit_01" -> listOf(
                MathExercise(
                    id = "ling_lit_01_q1",
                    title = "Gregório de Matos e a Sátira Barroca",
                    problemStatement = "Ao escrever sobre a cidade de Salvador no século XVII, Gregório de Matos sentencia: 'Que falta nesta cidade? Verdade. / Que mais por sua desonra? Honra. / Falta mais que se lhe ponha? Vergonha.' Esse trecho da poesia satírica do 'Boca do Inferno' caracteriza-se por:",
                    options = listOf(
                        "ufanismo desmedido e louvor incondicional à honestidade dos magistrados coloniais.",
                        "crítica mordaz e corrosiva à degradação moral, política e administrativa da capital da colônia sob domínio português.",
                        "defesa das práticas idílicas do bucolismo pastoril e lemas latinos neoclássicos.",
                        "celebração ingênua das belezas botânicas e florestais do Recôncavo Baiano.",
                        "versos de temática estritamente mística dedicados à exaltação das freiras enclausuradas."
                    ),
                    correctIndex = 1,
                    hint = "O poeta ataca os vícios das autoridades locais, denunciando a ausência de verdade, honra e vergonha.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A vertente satírica de Gregório de Matos expôs sem meias-palavras as negociatas e a corrupção do governo da Bahia colonial, valendo-lhe o apelido de 'Boca do Inferno' e o posterior degredo para Angola.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_lit_02" -> listOf(
                MathExercise(
                    id = "ling_lit_02_q1",
                    title = "Castro Alves e a Poesia Condoreira",
                    problemStatement = "Nos versos de 'O Navio Negreiro', de Castro Alves: 'Era um sonho dantesco... o tombadilho / Que das luzernas avermelha o brilho, / Em sangue a se banhar. / Tinir de ferros... estalar do açoite... / Legiões de homens negros como a noite, / Horrendos a dançar...', a estética condoreira manifesta-se através de:",
                    options = listOf(
                        "fuga introspectiva para o mundo da morte por tédio burguês existencial.",
                        "grandiloquência verbal e apelo sensorial dramático para mobilizar a comoção pública contra a desumanidade da escravidão.",
                        "idealização heroica de guerreiros indígenas no molde das novelas de cavalaria medievais.",
                        "culto formal à métrica parnasiana impassível, desprovida de engajamento social.",
                        "elogio resignado ao sofrimento dos cativos como caminho exclusivo para a salvação espiritual."
                    ),
                    correctIndex = 1,
                    hint = "Castro Alves emprega imagens visuais e sonoras impactantes para despertar indignação política na sociedade.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A terceira geração romântica (condoreira) é marcada pelo engajamento cívico abolicionista e republicano.
                        2. O uso de adjetivação vigorosa ('sonho dantesco', 'banhar em sangue') e efeitos auditivos ('tinir de ferros', 'estalar do açoite') constrói uma cena teatral grandiosa capaz de abalar as consciências contra o cativeiro.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_lit_03" -> listOf(
                MathExercise(
                    id = "ling_lit_03_q1",
                    title = "Realismo de Machado de Assis",
                    problemStatement = "Em 'Memórias Póstumas de Brás Cubas', o protagonista inicia sua narrativa esclarecendo ao leitor que não é um autor defunto, mas um defunto autor, para quem a cova foi outro berço. Essa escolha narrativa permite a Machado de Assis:",
                    options = listOf(
                        "aderir à crença no espiritismo kardecista como verdade doutrinária dogmática.",
                        "libertar o narrador das convenções e hipocrisias sociais, permitindo um olhar cínico, irônico e desmascarador sobre a elite de seu tempo.",
                        "produzir uma novela de terror sobrenatural aos moldes góticos de Edgar Allan Poe.",
                        "exaltar a bondade inata do ser humano e a pureza de sentimentos desinteressados na corte imperial.",
                        "garantir neutralidade jornalística absoluta sem emitir nenhum juízo de valor pessoal."
                    ),
                    correctIndex = 1,
                    hint = "Um defunto não deve satisfações aos vivos, podendo expor sem pudor suas próprias mesquinharias e as da sociedade.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A condição de defunto autor outorga a Brás Cubas total liberdade enunciativa: ele não teme a opinião pública, o tribunal da moral ou a reprovação de classe.
                        2. Com isso, Machado constrói a mais afiada radiografia das vaidades e cinismos da aristocracia patriarcal e escravista do Rio de Janeiro.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_lit_04" -> listOf(
                MathExercise(
                    id = "ling_lit_04_q1",
                    title = "Pré-Modernismo: Triste Fim de Policarpo Quaresma",
                    problemStatement = "Em 'Triste Fim de Policarpo Quaresma', de Lima Barreto, o protagonista é um funcionário público apaixonado pelo Brasil que envia um requerimento ao Congresso propondo a adoção da língua tupi-guarani como idioma oficial do país. O desfecho trágico e incompreendido de Policarpo simboliza:",
                    options = listOf(
                        "o triunfo espetacular do positivismo militar republicano nos sertões brasileiros.",
                        "a denúncia da insensibilidade e intolerância das instituições do Estado perante a dissidência crítica e os idealismos patrióticos genuínos.",
                        "a prova irrefutável de que as línguas indígenas são biologicamente inviáveis na modernidade.",
                        "o conformismo pacífico da classe média carioca com os desmandos da ditadura florianista.",
                        "uma apologia ao imperialismo europeu contra as culturas locais sul-americanas."
                    ),
                    correctIndex = 1,
                    hint = "Policarpo dedica sua vida à pátria e termina fuzilado pelo próprio governo que tentou defender com paixão ingênua.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. Lima Barreto constrói Policarpo como o quixote brasileiro cuja dedicação sincera à identidade nacional colide com o autoritarismo militar e a mediocridade da burocracia estatal da República Velha.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_lit_05" -> listOf(
                MathExercise(
                    id = "ling_lit_05_q1",
                    title = "Vidas Secas de Graciliano Ramos",
                    problemStatement = "No capítulo 'Baleia' do romance 'Vidas Secas', de Graciliano Ramos, a cadela da família de retirantes, ao agonizar após ser alvejada por Fabiano, sonha com um mundo cheio de preás gordos. Por sua vez, Fabiano muitas vezes se define como 'um bicho'. Esse recurso literário tem como propósito fundamental:",
                    options = listOf(
                        "demonstrar que animais possuem raciocínio matemático superior ao dos seres humanos.",
                        "evidenciar o processo de desumanização imposto pela seca extrema e pela opressão fundiária, que rebaixa o sertanejo ao nível de bicho e confere à cadela a humanidade negada aos retirantes.",
                        "fazer propaganda da pecuária extensiva no semiárido nordestino.",
                        "apresentar uma fábula infantil inocente destinada a divertir crianças nas escolas rurais.",
                        "defender que os problemas do Nordeste decorrem unicamente da preguiça de seus habitantes."
                    ),
                    correctIndex = 1,
                    hint = "A miséria e o abandono social privam o ser humano de sua dignidade e da própria linguagem.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A animalização de Fabiano e a humanização poética de Baleia constituem a denúncia mais pungente de Graciliano Ramos sobre a violência estrutural do latifúndio e da seca, que despoja os trabalhadores rurais de sua humanidade básica.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            else -> listOf(
                MathExercise(
                    id = "ling_lit_06_q1",
                    title = "Literatura Contemporânea e Escrevivência",
                    problemStatement = "A escritora Conceição Evaristo cunhou o termo 'escrevivência' para caracterizar sua produção literária, afirmando: 'Nossa escrevivência não é para adormecer os da casa-grande, e sim para acordá-los de seus sonos injustos'. Essa perspectiva autoral manifesta-se através de:",
                    options = listOf(
                        "alienação voluntária em relação às desigualdades raciais e de gênero no país.",
                        "uma escrita comprometida e enraizada nas vivências e memórias coletivas da população negra e periférica, desafiando a hegemonia das narrativas coloniais tradicionais.",
                        "imitação servil dos sonetos parnasianos de autores franceses do século XIX.",
                        "desejo de agradar as elites dominantes em troca de patrocínio institucional.",
                        "recusa categórica ao uso de recursos literários ficcionais na produção em prosa."
                    ),
                    correctIndex = 1,
                    hint = "A escrevivência une escrita e vivência para romper silenciamentos históricos e despertar consciências.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. O conceito de escrevivência coloca a experiência subjetiva, histórica e corpórea das mulheres negras e faveladas no centro do fazer literário, transformando a palavra em instrumento de afirmação, dignidade e justiça social.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )
        }
    }
}
