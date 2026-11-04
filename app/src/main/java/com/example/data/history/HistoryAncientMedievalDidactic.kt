package com.example.data.history

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático Aprofundado e Questões Analíticas no Padrão ENEM
 * Módulo 4: História Geral: Antiguidade e Idade Média
 * - hist_ant_01: Grécia Antiga
 * - hist_ant_02: Roma Antiga
 * - hist_ant_03: Mundo Medieval
 * - hist_ant_04: O Mundo Islâmico e as Rotas Comerciais
 */
object HistoryAncientMedievalDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "hist_ant_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Grécia Antiga no ENEM: Democracia, Cidadania Restrita e Modelos Sociais

                    No ENEM, a Grécia Antiga não é cobrada como uma cronologia mitológica, mas pela análise comparativa dos modelos sociopolíticos das *póleis* (cidades-estado independentes), com ênfase na natureza excludente da cidadania clássica.

                    ### 1. Atenas e a Invenção da Democracia Direta
                    • **Evolução Política:** De Drácon e Sólon às reformas de Clístenes (508 a.C.), considerado o "pai da democracia".
                    • **Democracia Direta na Ágora:** Os cidadãos reuniam-se na praça pública (*Ágora* ou *Eclésia*) para debater e votar as leis diretamente, sem representantes eleitos.
                    • **Princípios Fundamentais:**
                      - *Isegoria:* Igualdade no direito à palavra na assembleia.
                      - *Isonomia:* Igualdade perante a lei para todos os cidadãos.
                      - *Ostracismo:* Banimento temporário por 10 anos de qualquer cidadão suspeito de conspirar contra o regime democrático.
                    • **O Limite Estrutural da Cidadania Ateniense:** A cidadania era privilégio de uma minoria ínfima. Eram cidadãos apenas **homens adultos, livres, filhos de pai e mãe atenienses**. Estavam excluídos:
                      - *Mulheres:* Confinadas ao espaço doméstico (*gineceu*), sem direitos políticos.
                      - *Metecos:* Estrangeiros livres que viviam em Atenas, pagavam tributos e atuavam no comércio, mas sem voto.
                      - *Escravizados:* A ampla maioria da força produtiva (prisioneiros de guerra ou dívida), cuja exploração laboral garantia o ócio necessário (*otium*) para os cidadãos debaterem filosofia e política.

                    ### 2. Esparta: Militarismo, Oligarquia e Coerção
                    • **Sociedade Militarizada:** Voltada para a guerra terrestre e a disciplina física incondicional. Desde os 7 anos, os meninos eram entregues ao Estado (*agoge*).
                    • **Estrutura Social Rígida:**
                      - *Espartíatas (Iguais):* Elite guerreira descendente dos invasores dórios, proprietários das terras férteis.
                      - *Periecos:* Homens livres periféricos, comerciantes e artesãos desprovidos de direitos de comando político.
                      - *Hilotas:* População autóctone dominada e reduzida à servidão estatal coletiva, submetida a massacres anuais de terror ritualístico (*Criptia*) para evitar revoltas.
                    • **Regime Político:** Oligarquia aristocrática chefiada por uma diarquia (dois reis), a *Gerúsia* (conselho de 28 anciãos aristocratas) e os *Éforos*.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Democracia Direta", "Decisões deliberadas pessoalmente na Ágora sem intermediários", "Modelo ateniense de participação cidadã"),
                    FormulaOrSyntaxItem("Cidadania Restrita", "Apenas homens adultos livres filhos de pais atenienses", "Excluía mulheres, metecos e os escravizados"),
                    FormulaOrSyntaxItem("Hilotas", "Servos coletivos da terra subordinados ao Estado espartano", "Base produtiva explorada pela elite guerreira espartíata")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Contradição da Democracia Escravista",
                        codeOrFormula = "Liberdade política dos cidadãos viabilizada pelo cativeiro dos escravizados",
                        explanation = "O ENEM enfatiza que o 'século de ouro' da filosofia e democracia ateniense só existiu materialmente porque a escravidão desonerava os homens livres do trabalho físico pesado, permitindo o ócio deliberativo."
                    )
                ),
                goldTip = "Cuidado ao comparar a democracia ateniense com a contemporânea: a nossa é indireta/representativa e de sufrágio universal; a de Atenas era direta, mas radicalmente excludente."
            )

            "hist_ant_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Roma Antiga: Da República ao Império, Direito e Crise Escravista

                    Roma legou à civilização ocidental matrizes fundamentais: o Direito codificado, as técnicas de engenharia e a estrutura administrativa de um império multiétnico sustentado pelo modo de produção escravista.

                    ### 1. A República Romana (509 a.C.–27 a.C.) e as Lutas Plebeias
                    • **Conflito Social:** Tensão perene entre os *Patrícios* (aristocracia latifundiária que monopolizava o Senado) e os *Plebeus* (maioria livre de artesãos, camponeses e soldados sem representação).
                    • **Conquistas Plebeias por Greves:** Após ameaçarem abandonar Roma desguarnecida militarmente (Secessão da Plebe no Monte Sagrado), os plebeus conquistaram:
                      - *Tribunos da Plebe:* Magistrados invioláveis com poder de vetar leis que prejudicassem a plebe.
                      - *Lei das Doze Tábuas (450 a.C.):* Primeira codificação escrita das leis, impedindo interpretações arbitrárias dos patrícios.
                      - *Lei Canuleia:* Autorização do casamento misto entre patrícios e plebeus ricos.
                      - *Fim da escravidão por dívidas.*

                    ### 2. O Império Romano e a Política de 'Pão e Circo'
                    • **A Expansão Militar e o Mare Nostrum:** A vitória sobre Cartago nas Guerras Púnicas transformou o Mar Mediterrâneo em lago romano (*Mare Nostrum*), inundando Roma de riquezas e milhões de escravizados prisioneiros de guerra.
                    • **Ruína da Pequena Propriedade:** Os camponeses plebeus empobreceram por não competirem com as grandes fazendas escravistas (*latifundia*), migrando para Roma e formando uma massa ociosa e empobrecida.
                    • **Pão e Circo (Panem et Circenses):** Estratégia de apaziguamento social: o Estado distribuía trigo e promovia espetáculos de gladiadores no Coliseu e corridas de bigas no Circo Máximo para alienar as massas e prevenir rebeliões.

                    ### 3. A Crise do Império e a Transição para o Feudalismo (Século III ao V d.C.)
                    • **O Fim da Expansão e a Crise do Escravismo:** Com a fixação das fronteiras (*Pax Romana*), cessou o fluxo de prisioneiros de guerra. A escassez de escravizados encareceu a produção agrícola.
                    • **O Colonato (Ruralização):** Grandes latifundiários acolheram plebeus falidos e ex-escravizados na terra em troca de proteção e de parte substantiva da colheita (origem da servidão medieval).
                    • **Divisão do Império (395 d.C.):** Império Romano do Ocidente (capital Roma/Ravena) e Império Romano do Oriente (Bizantino, capital Constantinopla). As invasões dos povos germânicos ("bárbaros") fragmentaram o Ocidente em 476 d.C.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Direito Romano", "Lei escrita impessoal e codificada (Lei das Doze Tábuas e Corpus Juris Civilis)", "Base dos códigos civis modernos ocidentais"),
                    FormulaOrSyntaxItem("Pão e Circo", "Distribuição gratuita de alimentos e espetáculos sangrentos de massa", "Técnica de contenção de conflitos e cooptação da plebe urbana"),
                    FormulaOrSyntaxItem("Colonato", "Trabalhador preso à terra em regime de coparticipação agrícola", "Elo de transição entre o escravismo antigo e a servidão feudal")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Impacto do Cristianismo no Império Romano",
                        codeOrFormula = "De religião perseguida a religião oficial do Estado (Édito de Milão e Édito de Tessalônica)",
                        explanation = "O ENEM aborda como o Cristianismo, inicialmente reprimido por contestar a divindade imperial, foi adotado pelo imperador Constantino (313 d.C.) e tornado oficial por Teodósio (380 d.C.) como cimento ideológico para tentar frear a desagregação do Império."
                    )
                ),
                goldTip = "Para o ENEM, lembre-se: a queda de Roma não foi um evento militar repentino de um único dia, mas um longo processo de crise interna do sistema escravista somado à ruralização e às migrações germânicas."
            )

            "hist_ant_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # O Mundo Medieval: Feudalismo, Poder Católico e Renascimento Urbano

                    A Idade Média (séculos V a XV) na Europa Ocidental é analisada pelo ENEM superando o preconceito oitocentista de 'Idade das Trevas'. Trata-se de uma sociedade com profunda coesão cultural, descentralização política e dinâmica própria de transformações.

                    ### 1. A Estrutura do Feudalismo
                    Fruto da fusão entre elementos romanos (*colonato*, *vilae*) e tradições germânicas (*comitatus*, laços de honra pessoal):
                    • **Economia Feudal:** Agrária, voltada para a subsistência local no senhorio rural (feudo), com escassa circulação monetária e baixa divisão técnica do trabalho.
                    • **Sociedade Estamental Tripartite:** Legitimada pela teologia medieval como vontade divina imutável:
                      - *Clero (Os que rezam - Oratores):* Monopólio cultural, salvação espiritual e grandes proprietários de terras.
                      - *Nobreza (Os que lutam - Bellatores):* Senhores das armas, castelos e cavalarias.
                      - *Servos (Os que trabalham - Laboratores):* Camponeses presos à terra, desprovidos de direitos de comando.
                    • **Obrigações Servis:** *Corveia* (trabalho compulsório nas terras senhoriais), *Talha* (entrega de parte da produção servil), *Banalidades* (tributo pelo uso do moinho e forno do senhor) e *Tostão de Pedro / Dízimo* (para a Igreja).
                    • **Relações de Suserania e Vassalagem:** Alianças militares e de honra horizontais *exclusivas entre nobres*, celebradas pelo rito da homenagem e investidura (concessão de um feudo pelo suserano ao vassalo leal).

                    ### 2. A Hegemonia da Igreja Católica
                    • A Igreja era a instituição mais rica e poderosa do Ocidente medieval, detentora de um terço das terras agricultáveis.
                    • Monopolizava o saber letrado, os mosteiros preservavam manuscritos antigos, e a visão de mundo era estritamente **teocêntrica** (Deus no centro de todas as explicações da realidade).

                    ### 3. As Cruzadas e o Renascimento Comercial e Urbano (Século XI ao XIV)
                    • **As Cruzadas (1095–1291):** Expedições militares de cunho religioso convocadas pelo Papa Urbano II para reconquistar a Terra Santa (Jerusalém) dos muçulmanos.
                    • **Consequências Econômicas e Culturais:**
                      - Reabertura do Mar Mediterrâneo ao comércio ocidental.
                      - Ressurgimento das rotas terrestres de comércio (Feiras de Champanhe) e florescimento das cidades comerciais italianas (Gênova e Veneza) e da Liga Hanseática no Norte europeu.
                      - **Surgimento da Burguesia:** Habitantes dos *burgos* (cidades muradas) dedicados ao comércio, às trocas monetárias e à produção artesanal organizada em corporações de ofício.
                      - A Crise do Século XIV: Fome generalizada, a devastação da Peste Negra (1347–1351) e a Guerra dos Cem Anos aceleraram a dissolução dos laços feudais.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Sociedade Feudal", "Oratores (Clero) + Bellatores (Guerra) + Laboratores (Trabalho)", "Estrutura estamental com mobilidade social quase inexistente"),
                    FormulaOrSyntaxItem("Suserania e Vassalagem", "Pacto de fidelidade militar entre nobres feudais", "Fragmentação política e ausência de Estado centralizado"),
                    FormulaOrSyntaxItem("Renascimento Comercial", "Reabertura do Mediterrâneo pelas Cruzadas e nascimento dos burgos", "Embrião da economia monetária mercantil e da classe burguesa")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Impacto da Peste Negra nas Relações de Trabalho",
                        codeOrFormula = "Queda de 1/3 da população europeia -> Escassez de mão de obra servil",
                        explanation = "O ENEM discute como a mortandade provocada pela Peste Negra no século XIV conferiu maior poder de barganha aos camponeses sobreviventes, acelerando a substituição da servidão tradicional pelo pagamento de salários e gerando revoltas camponesas (Jacqueries)."
                    )
                ),
                goldTip = "Atenção crucial no ENEM: Suserania e Vassalagem ocorre APENAS ENTRE NOBRES (senhores feudais). Entre o senhor feudal e o camponês pobre, a relação é de SERVIDÃO e dominação senhorial!"
            )

            "hist_ant_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # O Mundo Islâmico e as Rotas Comerciais Afro-Asiáticas

                    O surgimento do Islã no século VII na Península Arábica unificou tribos beduínas e forjou uma das mais brilhantes civilizações da história, atuando como ponte mercantil, científica e cultural entre Ásia, África e Europa.

                    ### 1. Maomé e os Fundamentos do Islã
                    • Em 622 d.C., Maomé realizou a *Hégira* (fuga de Meca para Medina), fundando a comunidade de fiéis muçulmanos (*Ummah*) e inaugurando o calendário islâmico.
                    • **Os Cinco Pilares:** Profissão de fé em Alá como único Deus e Maomé seu profeta, cinco orações diárias, esmola aos necessitados (*zakat*), jejum no mês do Ramadã e peregrinação a Meca (*hajj*) ao menos uma vez na vida.
                    • **Linguagem e Cultura:** O Alcorão em árabe serviu como instrumento de padronização linguística e unificação de povos distintos (árabes, persas, berberes).

                    ### 2. A Expansão Islâmica e a Conexão Global
                    • Em menos de um século, os califados muçulmanos expandiram-se da Península Ibérica (Al-Andalus) até o Norte da África, Oriente Médio e Índia.
                    • **As Cidades e as Rotas:** Damasco, Bagdá, Cairo e Córdoba tornaram-se metrópoles cosmopolitas e centros vibrantes de trocas comerciais (Rota da Seda e Rotas Transaarianas de ouro, sal e tecidos finos).

                    ### 3. A Vanguarda Científica e Filosófica Islâmica
                    Enquanto a Europa feudal vivia em isolamento rural, o mundo islâmico viveu uma idade de ouro do conhecimento:
                    • **Matemática e Astronomia:** Desenvolvimento da Álgebra (Al-Khwarizmi, de onde vem a palavra 'algoritmo'), popularização dos algarismos indo-arábicos (incluindo o número zero), trigonometria e cálculo de órbitas celestes com astrolábios aprimorados.
                    • **Medicina:** Avicena (*Ibn Sina*) escreveu o *Cânone da Medicina*, obra de referência que ensinou a Europa durante séculos sobre anatomia, contágio e cirurgia.
                    • **Preservação e Tradução Clássica:** Os sábios islâmicos traduziram, comentaram e salvaram do esquecimento os textos da filosofia clássica grega (Aristóteles e Platão), que posteriormente reingressaram na Europa renascentista através de Córdoba e Toledo.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Al-Andalus", "Península Ibérica sob domínio muçulmano (séculos VIII a XV)", "Espaço de convivência intercultural e tolerância entre judeus, cristãos e islâmicos"),
                    FormulaOrSyntaxItem("Revolução Científica Árabe", "Álgebra, algarismos indo-arábicos, óptica e medicina de ponta", "Herança científica fundamental para a modernidade ocidental"),
                    FormulaOrSyntaxItem("Rotas Transaarianas", "Conexão mercantil do Islã com os grandes reinos negros da África Ocidental (Mali, Gana, Songhai)", "Comércio de ouro de Tombuctu e intercâmbio civilizatório")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Desconstruindo o Eurocentrismo no ENEM",
                        codeOrFormula = "A Idade Média não foi um período de estagnação global",
                        explanation = "O ENEM utiliza documentos islâmicos para contrapor a visão eurocêntrica: enquanto a Europa feudal estava ruralizada, cidades como Bagdá e Córdoba possuíam hospitais públicos, iluminação pública, bibliotecas com centenas de milhares de volumes e universidades."
                    )
                ),
                goldTip = "Lembre-se: palavras cotidianas da língua portuguesa que começam com 'al-' têm origem na herança árabe medieval na Península Ibérica: açúcar, arroz, algodão, alfaiate, alface, alcachofra, álgebra e álcool!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "Estudo de História Geral: Antiguidade e Idade Média.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Compare sempre os modelos de cidadania e relações de trabalho."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "hist_ant_01" -> listOf(
                MathExercise(
                    id = "hist_ex_ant_01_a",
                    title = "ENEM: A Natureza Excludente da Democracia Ateniense",
                    problemStatement = "Na famosa oração fúnebre pronunciada por Péricles no século V a.C., ele celebrava: 'Nossa constituição política não copia as leis dos países vizinhos; antes, nós é que servimos de modelo a alguns deles. Seu nome é Democracia, porque o governo pertence não a uma minoria, mas à maioria'.\n\nNo entanto, ao analisar a organização socioespacial de Atenas à luz da crítica histórica do ENEM, constata-se que a democracia ateniense era caracterizada por:",
                    options = listOf(
                        "Um sufrágio universal irrestrito que concedia plenos direitos eleitorais a homens, mulheres e estrangeiros residentes.",
                        "Um modelo direto de deliberação na Ágora sustentado pelo trabalho compulsório de uma maioria desprovida de direitos de cidadania (escravizados, mulheres e metecos).",
                        "Uma monarquia absolutista em que o rei de Atenas concentrava todo o poder legislativo e religioso sem assembleias.",
                        "Uma república feudal camponesa sem moeda e sem comércio marítimo com o mar Mediterrâneo.",
                        "Um sistema representativo em que apenas sacerdotes do templo de Delfos podiam se candidatar ao parlamento ateniense."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se de quem ficava de fora da praça pública: mulheres, estrangeiros e a enorme população escravizada que realizava o trabalho produtivo.",
                    stepByStepSolution = "A alternativa B está correta. A democracia clássica em Atenas era direta para os cidadãos, mas radicalmente excludente: os considerados cidadãos (homens adultos, livres e filhos de pais atenienses) somavam menos de 10% a 15% da população total da pólis. A ampla maioria — mulheres, estrangeiros residentes (metecos) e a massa de trabalhadores escravizados — não possuía qualquer direito de voto ou participação nas decisões políticas."
                ),
                MathExercise(
                    id = "hist_ex_ant_01_b",
                    title = "ENEM: Modelos Sociopolíticos em Atenas e Esparta",
                    problemStatement = "A Grécia Clássica caracterizava-se pela coexistência de diversas cidades-estado (póleis) que, embora compartilhassem a mesma matriz linguística e religiosa, desenvolveram estruturas institucionais profundamente distintas.\n\nA comparação entre Esparta e Atenas revela que a sociedade espartana diferenciava-se pelo predomínio de:",
                    options = listOf(
                        "Uma democracia participativa de vanguarda artística e grande tolerância às liberdades individuais.",
                        "Uma organização aristocrática militarizada, onde a formação do cidadão-guerreiro priorizava a disciplina física e a submissão dos servos hilotas ao Estado.",
                        "Uma economia exclusivamente financeira baseada na emissão de papel-moeda e no livre comércio com a Pérsia.",
                        "Um governo teocrático comandado por imperadores divinizados que proibiam a posse de escudos e lanças.",
                        "Uma confederação de marinheiros mercantes dedicados exclusivamente à colonização do oceano Pacífico."
                    ),
                    correctIndex = 1,
                    hint = "Pense na educação guerreira espartana (a 'agoge') e no papel dos hilotas como população escravizada pelo Estado.",
                    stepByStepSolution = "A alternativa B está correta. Esparta era uma pólis oligárquica e guerreira, governada por dois reis e pela Gerúsia. Os espartíatas (iguais) dedicavam-se com exclusividade ao treinamento militar desde os 7 anos de idade. Essa máquina bélica destinava-se a manter a segurança territorial e, internamente, policiar e reprimir a imensa população de hilotas (servos coletivos do Estado espartano), submetidos a regimes anuais de intimidação armada."
                )
            )

            "hist_ant_02" -> listOf(
                MathExercise(
                    id = "hist_ex_ant_02_a",
                    title = "ENEM: A Luta entre Patrícios e Plebeus na Roma Republicana",
                    problemStatement = "Durante a República Romana, as tensões entre a aristocracia patrícia e a massa plebeia deflagraram intensas lutas sociais. Dentre as principais conquistas alcançadas pelos plebeus a partir dessas lutas, destaca-se:",
                    options = listOf(
                        "A proibição do Senado romano e a expulsão imediata de todos os proprietários de terras da península itálica.",
                        "A criação do tribunato da plebe com poder de veto legislativo e a publicação da Lei das Doze Tábuas, conferindo publicidade e impessoalidade às leis escritas.",
                        "A submissão voluntária dos plebeus à escravidão perpétua em troca do direito de assistir às corridas do Circo Máximo.",
                        "A transformação de Roma em um feudo autossuficiente isolado de qualquer contato com o Mar Mediterrâneo.",
                        "A abolição imediata da propriedade privada da terra e a fundação de um Estado socialista comandado por gladiadores."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da Lei das Doze Tábuas e do poder dos Tribunos da Plebe de dizer 'Veto!' aos senadores.",
                    stepByStepSolution = "A alternativa B está correta. Diante da ameaça plebeia de não servir no exército e abandonar Roma (Secessão no Monte Sagrado), os patrícios foram forçados a ceder. Criaram-se os Tribunos da Plebe (magistrados invioláveis defensores dos direitos populares com poder de veto) e foi redigida a Lei das Doze Tábuas (450 a.C.), fundamental por registrar o direito por escrito, rompendo o monopólio oral dos patrícios sobre a justiça."
                ),
                MathExercise(
                    id = "hist_ex_ant_02_b",
                    title = "ENEM: A Política do Pão e Circo e a Crise do Império",
                    problemStatement = "No período imperial romano, a política de 'Pão e Circo' (panem et circenses) consistia na distribuição gratuita de trigo e na organização de combates de gladiadores no Coliseu. No contexto sociopolítico de Roma, essa política funcionava como:",
                    options = listOf(
                        "Um projeto de escolarização universal para capacitar os escravizados a exercerem funções científicas.",
                        "Um mecanismo de apaziguamento e controle social para desmobilizar a massa de plebeus desempregados e prevenir insurreições urbanas contra o imperador.",
                        "Uma punição civil para cidadãos que se recusassem a cultuar as divindades pagãs tradicionais.",
                        "Um tratado de paz assinado com os reis bárbaros para desmilitarizar as legiões das fronteiras imperiais.",
                        "Um modelo democrático de escolha do imperador com base no aplauso popular aos gladiadores vitoriosos."
                    ),
                    correctIndex = 1,
                    hint = "População faminta e sem emprego é perigosa para o poder imperial: comida grátis e entretenimento violento mantêm o povo ocupado e dócil.",
                    stepByStepSolution = "A alternativa B está correta. A política de 'Pão e Circo' era uma estratégia calculada dos imperadores romanos para neutralizar as tensões da plebe marginalizada. A concentração de terras e a expansão do escravismo haviam expulsado os pequenos camponeses do campo, criando em Roma uma massa de centenas de milhares de desocupados. Distribuindo rações periódicas de trigo e oferecendo diversão pública alienante (gladiadores e corridas), o governo impedia rebeliões populares."
                )
            )

            "hist_ant_03" -> listOf(
                MathExercise(
                    id = "hist_ex_ant_03_a",
                    title = "ENEM: Estrutura Social e Relações de Trabalho no Feudalismo",
                    problemStatement = "Na sociedade feudal da Europa Ocidental, as relações de produção e de poder eram caracterizadas por:",
                    options = listOf(
                        "Uma forte mobilidade social ascendente viabilizada pela aprovação em concursos públicos organizados pelo rei.",
                        "Uma organização estamental fundamentada na posse da terra, onde os servos estavam presos ao feudo e sustentavam a nobreza e o clero por meio de pesadas obrigações servis (como a corveia e a talha).",
                        "Uma economia estritamente capitalista com indústrias automatizadas e salários pagos semanalmente aos camponeses.",
                        "Um governo democrático direto onde os servos votavam anualmente para escolher quem seria o papa católico.",
                        "Uma sociedade de consumo de massas com importação contínua de trigo e café das colônias americanas."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da divisão tripartite (os que oram, os que guerreiam e os que trabalham) e dos tributos feudais em trabalho e produtos.",
                    stepByStepSolution = "A alternativa B está correta. O feudalismo caracterizou-se pela descentralização política, economia agrária de subsistência e uma rígida estrutura social estamental legitimada pela Igreja Católica. Na base dessa pirâmide estavam os servos, camponeses vinculados juridicamente à terra dos senhores feudais, obrigados a trabalhar de graça vários dias na semana na reserva senhorial (corveia) e a entregar porcentagens expressivas de suas colheitas (talha e banalidades)."
                ),
                MathExercise(
                    id = "hist_ex_ant_03_b",
                    title = "ENEM: As Cruzadas e o Renascimento Comercial",
                    problemStatement = "Convocadas a partir do final do século XI pelo papado sob a justificativa de libertar os lugares sagrados do Cristianismo, as Cruzadas produziram impactos econômicos e socioculturais decisivos no Ocidente, entre os quais se destaca:",
                    options = listOf(
                        "A proibição definitiva da circulação de moedas na Europa e o fechamento do mar Mediterrâneo por mais mil anos.",
                        "A reabertura das rotas marítimas no Mediterrâneo ao comércio europeu, estimulando o renascimento urbano, as feiras comerciais e a ascensão da burguesia mercantil.",
                        "A conversão de toda a população de Paris e Londres à fé islâmica e o fim do Sacro Império Romano-Germânico.",
                        "A abolição pacífica e universal de todos os tributos cobrados pelos nobres e o desarmamento dos exércitos reais.",
                        "A destruição total de todas as cidades portuárias italianas e o retorno definitivo ao isolamento primitivo das florestas."
                    ),
                    correctIndex = 1,
                    hint = "As Cruzadas levaram multidões ao Oriente Médio e restabeleceram o comércio de sedas, especiarias e tecidos finos, enriquecendo cidades como Veneza e Gênova.",
                    stepByStepSolution = "A alternativa B está correta. Embora tenham falhado em seu objetivo religioso e militar permanente em Jerusalém, as Cruzadas reabriram o Mar Mediterrâneo ao comércio ocidental. Cidades italianas (Gênova e Veneza) acumularam fortunas transportando cruzados e mercadorias orientais (especiarias, perfumes, tecidos de seda). Esse fluxo comercial reanimou a circulação monetária, revitalizou as cidades (burgos) e fortaleceu a nova classe burguesa, abalando as bases tradicionais do isolamento feudal."
                )
            )

            "hist_ant_04" -> listOf(
                MathExercise(
                    id = "hist_ex_ant_04_a",
                    title = "ENEM: A Idade de Ouro Científica do Mundo Islâmico",
                    problemStatement = "Durante a Idade Média, enquanto a Europa Ocidental vivia em um ambiente cultural marcadamente ruralizado e teocêntrico, os grandes centros urbanos do mundo islâmico (como Bagdá, Cairo e Córdoba) floresciam como polos de produção intelectual, caracterizados por:",
                    options = listOf(
                        "Uma rejeição violenta a qualquer forma de conhecimento clássico grego ou romano, com a destruição sistemática de todas as bibliotecas antigas.",
                        "Avanços extraordinários na astronomia, óptica, medicina e matemática (introdução do zero e desenvolvimento da álgebra), combinados à preservação e tradução de textos clássicos.",
                        "A imposição de uma economia puramente nômade que proibia o cultivo agrícola e a edificação de cidades e hospitais.",
                        "A proibição do comércio marítimo e das trocas mercantis com mercadores europeus e asiáticos.",
                        "A submissão voluntária de todos os califas à autoridade espiritual exclusiva do Papa católico de Roma."
                    ),
                    correctIndex = 1,
                    hint = "Pense na palavra 'Álgebra', no uso do número zero e nas obras de medicina de Avicena que ensinaram a Europa.",
                    stepByStepSolution = "A alternativa B está correta. A civilização islâmica medieval alcançou um extraordinário apogeu científico e intelectual. Os sábios muçulmanos preservaram, traduziram e ampliaram a herança filosófica grega de Aristóteles e Platão, criaram a Álgebra, consolidaram os algarismos arábicos com o zero, aperfeiçoaram o astrolábio e produziram compêndios médicos avançados (como os de Avicena) que revolucionaram o conhecimento ocidental séculos mais tarde."
                ),
                MathExercise(
                    id = "hist_ex_ant_04_b",
                    title = "ENEM: Rotas Comerciais e Conexões Transculturais",
                    problemStatement = "A rápida expansão islâmica a partir do século VII articulou um vasto espaço geográfico que interligava o Oriente Médio, o Norte da África, a Ásia Central e a Península Ibérica. Essa articulação proporcionou:",
                    options = listOf(
                        "O isolamento total das civilizações asiáticas, que deixaram de manter qualquer comércio com o continente africano.",
                        "A formação de uma rede mercantil e cultural cosmopolita que integrou as rotas transaarianas de ouro e sal às rotas marítimas asiáticas de especiarias e tecidos.",
                        "A obrigatoriedade de que todos os produtos comercializados fossem doados gratuitamente às autoridades de Meca.",
                        "A decadência definitiva da navegação astronômica e o retorno ao uso exclusivo de carruagens terrestres na África.",
                        "A unificação de todas as monarquias europeias sob o comando militar direto dos generais persas."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da Rota da Seda e das caravanas pelo deserto do Saara: o Islã era uma civilização de mercadores urbanos que ligavam o mundo.",
                    stepByStepSolution = "A alternativa B está correta. O mundo islâmico funcionou como o grande elo de conexão global medieval. Através de uma rede integrada de rotas terrestres (caravanas de camelos pelo Saara comercializando ouro dos reinos de Mali e Gana) e rotas marítimas no Oceano Índico e Mediterrâneo, os mercadores muçulmanos conectavam os mercados da China, Índia, África e Europa, promovendo intensa circulação de bens materiais, tecnologias e ideias científicas."
                )
            )

            else -> emptyList()
        }
    }
}
