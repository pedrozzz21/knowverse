package com.example.data.languages

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático e Exercícios Comentados
 * Módulo 5: Artes e Expressão Corporal (Parte integrante da prova de Linguagens no ENEM)
 * - ling_art_01: Vanguardas Europeias (Cubismo, Surrealismo, Dadaísmo, Expressionismo, Futurismo)
 * - ling_art_02: Arte Brasileira (Semana de 22, Tarsila do Amaral, Portinari, Arte Contemporânea)
 * - ling_art_03: O Corpo e a Saúde (Práticas corporais, esporte x atividade física, dança e lutas)
 * - ling_art_04: Padrões Estéticos e Indústria Cultural (Mídia, consumo do corpo e alienação)
 */
object LanguagesArtsBodyDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "ling_art_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # As Vanguardas Europeias do Início do Século XX

                    No início do século XX, com a eclosão da 1ª Guerra Mundial e a aceleração industrial, artistas europeus romperam violentamente com o academicismo e com a imitação servil da realidade.

                    ### 1. Principais Movimentos de Vanguarda no ENEM
                    • **Futurismo (Filippo Marinetti, 1909):** Culto à máquina, à velocidade, à eletricidade e ao dinamismo da metrópole moderna. Rompimento radical com museus e bibliotecas do passado.
                    • **Cubismo (Pablo Picasso, Georges Braque, 1907):** Geometrização das formas e sobreposição de múltiplos ângulos e perspectivas simultâneas na mesma tela bidimensional (*Les Demoiselles d'Avignon*, *Guernica*).
                    • **Expressionismo (Edvard Munch, Ernst Kirchner, 1905):** Deformação expressiva da realidade objetiva para projetar a angústia interior, o medo, a solidão e o desespero do homem moderno (*O Grito*).
                    • **Dadaísmo (Tristan Tzara, Marcel Duchamp, 1916):** Anti-arte radical, niilismo, deboche e culto ao absurdo e ao acaso como resposta ao horror irracional da Primeira Guerra Mundial. Criação do *Ready-made* (a roda de bicicleta ou o mictório transformados em obra de arte pela escolha do artista).
                    • **Surrealismo (André Breton, Salvador Dalí, René Magritte, 1924):** Valorização do inconsciente freudiano, do mundo dos sonhos, da escrita automática e do delírio desprovido de censura moral ou racional (*A Persistência da Memória* com os relógios derretidos).

                    ### 2. Influência Direta no Brasil
                    As vanguardas europeias alimentaram diretamente os artistas brasileiros da Semana de 1922 (Anita Malfatti, Oswald de Andrade, Mário de Andrade), que as 'devoraram' para fundar a arte moderna brasileira.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Cubismo", "Múltiplas perspectivas geométricas simultâneas", "Picasso, Braque"),
                    FormulaOrSyntaxItem("Expressionismo", "Deformação exterior para expressar a angústia interior", "Munch (O Grito), Anita Malfatti"),
                    FormulaOrSyntaxItem("Dadaísmo (Ready-made)", "Objeto industrial cotidiano ressignificado em museu", "Duchamp"),
                    FormulaOrSyntaxItem("Surrealismo", "Inconsciente, psicanálise freudiana, lógica onírica", "Salvador Dalí, Magritte")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Guernica de Picasso no ENEM",
                        codeOrFormula = "Cubismo a serviço da denúncia do bombardeio fascista na Guerra Civil Espanhola",
                        explanation = "A fragmentação das figuras humanas e dos animais em planos geométricos despedaçados traduz visualmente a dor coletiva, a carnificina e a destruição da guerra."
                    )
                ),
                goldTip = "No ENEM: Se a obra apresenta relógios derretidos e cenários de sonho, é Surrealismo! Se usa mictório ou ferro de passar para debochar da arte, é Dadaísmo!"
            )

            "ling_art_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Arte Brasileira: Da Semana de 22 à Contemporaneidade

                    A arte brasileira do século XX constrói uma reflexão crítica sobre a mestiçagem, o trabalhador e as desigualdades sociais.

                    ### 1. A Semana de 22 e Anita Malfatti
                    • Em 1917, a exposição expressionista de Anita Malfatti foi duramente atacada por Monteiro Lobato no artigo 'Paranoia ou Mistificação?'. O ataque catalisou a união dos jovens artistas que culminou na Semana de Arte Moderna de 1922 no Teatro Municipal de São Paulo.
                    • Rompimento com o academicismo e com o gosto burguês conservador paulistano.

                    ### 2. Tarsila do Amaral e o Movimento Antropofágico
                    • **Fase Pau-Brasil (1924):** Cores vivas e 'caipiras' (azul puríssimo, rosa, amarelo), paisagens tropicais com palmeiras e favelas cariocas.
                    • **Fase Antropofágica (1928):** A pintura *Abaporu* ('o homem que come') — cabeça minúscula e pés e mãos agigantados ancorados na terra brasileira, inspirando o manifesto de Oswald de Andrade.
                    • **Fase Social (1933):** O quadro *Operários* — dezenas de rostos sobrepostos de diversas etnias diante das chaminés das fábricas, retratando a industrialização e a diversidade da força de trabalho imigrante e nacional.

                    ### 3. Candido Portinari e o Drama Social
                    • Foco nos retirantes da seca (*Retirantes*, 1944), nos trabalhadores rurais do café e nos meninos de Brodowski.
                    • Grandes murais públicos (*Guerra e Paz*, na sede da ONU em Nova York).

                    ### 4. Neoconcretismo e Arte Contemporânea
                    • Lygia Clark (*Bichos* — esculturas geométricas manipuladas pelo próprio público participante).
                    • Hélio Oiticica (*Parangolés* — capas de tecido para serem vestidas e dançadas no samba).
                    • A arte contemporânea rompe a distância passiva entre o espectador e o objeto.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Abaporu (Tarsila)", "Pés enormes no solo e cabeça pequena: ligação umbilical com a terra nativa", "Símbolo da Antropofagia"),
                    FormulaOrSyntaxItem("Operários (Tarsila)", "Faces multiétnicas empilhadas diante da fábrica", "Pioneirismo na denúncia operária industrial"),
                    FormulaOrSyntaxItem("Retirantes (Portinari)", "Expressões esquálidas e ossadas no semiárido", "Denúncia da tragédia da seca"),
                    FormulaOrSyntaxItem("Lygia Clark & Oiticica", "Espectador ativo participante (Bichos e Parangolés)", "Arte sensorial contemporânea")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Quadro Operários no ENEM",
                        codeOrFormula = "Multietnicidade + Chaminés fabris cinzentas",
                        explanation = "Tarsila do Amaral denuncia a massificação do operariado na metrópole paulistana, destacando que a riqueza do país é erguida sobre os ombros de trabalhadores anônimos de todas as cores."
                    )
                ),
                goldTip = "O ENEM prioriza o diálogo entre pintura e história social: Abaporu (identidade mítica nativa), Operários (industrialização e proletariado) e Retirantes (migração e seca)!"
            )

            "ling_art_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # O Corpo e a Saúde: Práticas Corporais na Prova de Linguagens

                    A Educação Física no ENEM integra a área de Linguagens porque **o corpo é uma forma de comunicação, expressão de identidade cultural e linguagem social**.

                    ### 1. Esporte versus Atividade Física / Exercício Físico
                    • **Esporte:** Prática corporal institucionalizada, regida por regras formais universais estabelecidas por confederações/federações, visando à competição e ao rendimento.
                    • **Atividade Física:** Qualquer movimento voluntário produzido pelo corpo com gasto energético acima do repouso (caminhar até a padaria, varrer a casa, subir escadas).
                    • **Exercício Físico:** Atividade física estruturada, planejada, repetitiva e deliberada com o objetivo de melhorar a aptidão física e a saúde.

                    ### 2. A Dança e a Expressão Coletiva
                    • A dança é patrimônio cultural imaterial da humanidade. Manifestações como o frevo, maracatu, samba de roda, jongo e o breakdance comunicam resistência histórica, memória ancestral e integração comunitária.

                    ### 3. Lutas versus Violência / Briga
                    • **Luta:** Prática corporal milenar dotada de regras claras, respeito rigoroso à integridade física do oponente, código de honra e autocontrole emocional (judô, capoeira, karatê).
                    • **Briga / Violência:** Ato desregrado, sem limites, cujo único objetivo é ferir ou destruir o outro, repudiado pelo esporte e pela ética humana.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Esporte vs Exercício", "Esporte = Regras universais oficiais + Competição | Exercício = Saúde estruturada", "Diferenciação conceitual"),
                    FormulaOrSyntaxItem("Luta vs Briga", "Luta = Regras éticas, respeito e controle | Briga = Violência física desregrada", "Conceito de alteridade"),
                    FormulaOrSyntaxItem("Cultura Corporal", "O movimento como linguagem e expressão de pertencimento", "Matriz do ENEM")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Capoeira como Prática Híbrida",
                        codeOrFormula = "Luta + Dança + Jogo + Resistência Histórica Quilombola",
                        explanation = "A capoeira disfarçou o treinamento de autodefesa dos escravizados sob o manto da dança e da música, tornando-se patrimônio imaterial da humanidade pela UNESCO."
                    )
                ),
                goldTip = "No ENEM: Questões de educação física sempre valorizam a inclusão social, a democratização do lazer e a diferença entre luta regrada e violência de rua!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Padrões Estéticos, Mídia e Indústria Cultural

                    A sociedade contemporânea transforma o corpo humano em mercadoria e objeto de consumo, gerando pressões sociais e sofrimento psíquico.

                    ### 1. A Tirania da Imagem e Padrões Inalcançáveis
                    • A publicidade e as redes sociais promovem um padrão estético hegemônico (magreza extrema, corpos hipermusculosos e juventude eterna).
                    • O uso massivo de filtros digitais e cirurgias estéticas desumaniza a diversidade corporal natural, gerando quadros de vigorexia, anorexia, bulimia e depressão.

                    ### 2. Conceito de Indústria Cultural (Adorno e Horkheimer)
                    • A cultura, a arte e o próprio corpo tornam-se produtos industriais padronizados para consumo de massa.
                    • A promessa de 'felicidade' é atrelada à compra de suplementos milagrosos, procedimentos estéticos e cosméticos.

                    ### 3. Movimentos de Resistência e Autoaceitação
                    • **Diversidade Corporal (Body Positivity):** Movimentos sociais que defendem o respeito a corpos gordos, corpos com deficiência, corpos idosos e não binários, denunciando a gordofobia e o capacitismo como formas de opressão social.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Indústria Cultural", "Padronização e mercantilização das necessidades e dos corpos", "Escola de Frankfurt (Adorno)"),
                    FormulaOrSyntaxItem("Padrão Estético Hegemônico", "Construção midiática de corpos ideais para impulsionar o consumo", "Tirania da imagem"),
                    FormulaOrSyntaxItem("Gordofobia e Capacitismo", "Preconceito e exclusão baseados na forma corporal ou deficiência", "Direitos humanos e cidadania")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Análise de Campanhas de Cosméticos no ENEM",
                        codeOrFormula = "A 'promessa' da juventude eterna associada ao sucesso social",
                        explanation = "O ENEM problematiza a retórica publicitária que culpabiliza o indivíduo pelo envelhecimento natural do corpo para comercializar produtos antienvelhecimento."
                    )
                ),
                goldTip = "No ENEM: A abordagem sobre corpo e mídia SEMPRE defende a pluralidade das formas corporais contra a padronização artificial imposta pelo mercado publicitário!"
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "ling_art_01" -> listOf(
                MathExercise(
                    id = "ling_art_01_q1",
                    title = "Vanguardas Europeias no ENEM",
                    problemStatement = "Em 1917, o artista francês Marcel Duchamp enviou para uma exposição de arte em Nova York um mictório de louça comum invertido, assinado com o pseudônimo 'R. Mutt' e intitulado 'A Fonte'. A obra, expoente máximo do Dadaísmo e precursora do ready-made, provocou escândalo porque:",
                    options = listOf(
                        "comprovava o domínio virtuoso da escultura em mármore clássico do Renascimento.",
                        "desafiava os critérios tradicionais de estética e autoria, demonstrando que a obra de arte reside na ideia e na escolha crítica do artista, e não na confecção manual do objeto.",
                        "fazia apologia à poluição dos rios e à destruição das instalações sanitárias urbanas.",
                        "procurava ensinar noções de encanamento industrial aos críticos de arte nova-iorquinos.",
                        "afirmava a superioridade do artesanato grego sobre a produção em massa moderna."
                    ),
                    correctIndex = 1,
                    hint = "O ready-made tira um objeto industrial comum de seu uso cotidiano e o coloca em um museu, forçando uma reflexão conceitual.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A proposta dadaísta de Marcel Duchamp operou uma das maiores revoluções da história da arte: ao descontextualizar um mictório e levá-lo ao museu, ele afirmou que o valor da arte é um conceito filosófico e institucional, quebrando o fetiche da técnica acadêmica.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_art_02" -> listOf(
                MathExercise(
                    id = "ling_art_02_q1",
                    title = "Tarsila do Amaral e o Abaporu",
                    problemStatement = "A célebre pintura 'Abaporu' (1928), de Tarsila do Amaral, apresenta uma figura humana nua, solitária, com um pé e um braço gigantescos pousados na terra ao lado de um cacto sob um sol radiante, contrastando com uma cabeça minúscula. Na perspectiva do Modernismo brasileiro, essa desproporção anatômica simboliza:",
                    options = listOf(
                        "a incapacidade técnica da pintora de reproduzir a anatomia humana segundo os manuais médicos.",
                        "a ligação visceral e física do homem brasileiro com a terra e com a natureza tropical, valorizando a força instintiva e o enraizamento telúrico.",
                        "uma doença deformadora típica das regiões áridas do semiárido sertanejo.",
                        "o desdém da artista pelas belezas naturais e pelas tradições folclóricas do país.",
                        "uma denúncia dos efeitos danosos do calçado inadequado para operários da construção civil."
                    ),
                    correctIndex = 1,
                    hint = "Os pés agigantados fincados no solo evocam o contato direto e a sustentação do ser na terra nativa.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. 'Abaporu' (em tupi, 'homem que come carne humana') inaugurou o Movimento Antropofágico. A enorme dimensão dos pés e membros inferiores expressa o enraizamento profundo no solo nativo e a força da materialidade brasileira, em oposição ao intelectualismo europeu hipertrofiado.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_art_03" -> listOf(
                MathExercise(
                    id = "ling_art_03_q1",
                    title = "O Corpo como Prática Cultural",
                    problemStatement = "Ao longo da história do Brasil, a capoeira foi perseguida pelas autoridades policiais e criminalizada no Código Penal da República em 1890 como contravenção. Na atualidade, reconhecida como Patrimônio Cultural Imaterial da Humanidade pela UNESCO, a capoeira destaca-se por:",
                    options = listOf(
                        "constituir uma prática corporal exclusiva para o treino de soldados das forças armadas.",
                        "articular luta, dança, música e ancestralidade como forma de resistência cultural e preservação da memória da população afro-brasileira.",
                        "estimular brigas desregradas e violentas para resolução de desavenças comunitárias em locais públicos.",
                        "rejeitar qualquer forma de musicalidade ou ritmo sonoro em suas rodas de apresentação.",
                        "limitar-se à cópia fiel dos modelos de ginástica militar sueca do século XIX."
                    ),
                    correctIndex = 1,
                    hint = "A capoeira mescla elementos de jogo corporal, música e resistência histórica contra a opressão.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A capoeira transcende a mera dimensão física: é um fenômeno de linguagem corporal onde o ritmo do berimbau, os cantos ancestrais e o jogo de esquiva compõem um monumento vivo da resistência negra no Brasil.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            else -> listOf(
                MathExercise(
                    id = "ling_art_04_q1",
                    title = "Mídia, Corpo e Indústria Cultural",
                    problemStatement = "Pesquisas sociológicas recentes apontam que a exposição contínua a imagens digitais com filtros de embelezamento nas redes sociais tem provocado o aumento de transtornos de imagem corporal entre jovens e adolescentes. Diante desse fenômeno, a crítica cultural contemporânea alerta para:",
                    options = listOf(
                        "a necessidade de criminalizar a fotografia digital em todas as esferas do cotidiano.",
                        "os perigos da imposição mercadológica de padrões estéticos inalcançáveis, que transformam o corpo em produto de consumo e geram sofrimento psíquico decorrente da não aceitação da diversidade real.",
                        "a confirmação de que os filtros digitais democratizam democraticamente a beleza biológica sem efeitos colaterais.",
                        "a vantagem biológica indiscutível de cirurgias plásticas prematuras na infância.",
                        "o desinteresse completo dos jovens por qualquer forma de validação estética nas plataformas virtuais."
                    ),
                    correctIndex = 1,
                    hint = "A mercantilização do corpo e a cobrança por uma perfeição artificial geram angústia e exclusão.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A indústria cultural do consumo estimula a inadequação corporal para lucrar com produtos estéticos, cosméticos e cirurgias. A reflexão crítica do ENEM incentiva o reconhecimento da pluralidade dos corpos contra a ditadura da imagem hegemônica.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )
        }
    }
}
