package com.example.data.history

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático Aprofundado e Questões Analíticas no Padrão ENEM
 * Módulo 2: História do Brasil Império
 * - hist_imp_01: Processo de Independência e Primeiro Reinado
 * - hist_imp_02: Período Regencial e as Revoltas
 * - hist_imp_03: Segundo Reinado
 * - hist_imp_04: A Crise do Império e o Fim da Escravidão
 */
object HistoryEmpireDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "hist_imp_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Independência e Primeiro Reinado: A Construção do Estado Imperial

                    O processo de Independência do Brasil (1822) e a configuração do Primeiro Reinado (1822–1831) são compreendidos no ENEM como uma transição conservadora liderada pela própria elite agrária em torno do herdeiro da dinastia de Bragança, assegurando a continuidade do latifúndio, da escravidão e da centralização monárquica.

                    ### 1. A Vinda da Família Real (1808) e a Abertura dos Portos
                    Em 1807, fugindo das tropas napoleônicas sob escolta da marinha britânica, D. João VI transferiu a sede da Coroa para o Rio de Janeiro ("interiorização da metrópole"):
                    • **Abertura dos Portos às Nações Amigas (1808):** Rompeu na prática o Pacto Colonial, beneficiando amplamente a Inglaterra industrial.
                    • **Tratados de 1810:** Estabeleceram taxas alfandegárias ultrapreferenciais para mercadorias inglesas (15%), enquanto produtos portugueses pagavam 16% e das demais nações pagavam 24%.
                    • **Elevação a Reino Unido (1815):** Consolidou o status político e administrativo da colônia, mas gerou ressentimento em Portugal.

                    ### 2. O 7 de Setembro e a 'Independência Conservadora'
                    A Revolução Liberal do Porto (1820) exigia o retorno do rei a Lisboa e a tentativa de 'recolonizar' o Brasil:
                    • Em resposta à pressão das Cortes portuguesas, o "Partido Brasileiro" (grandes proprietários de terras do Sudeste) articulou a permanência de D. Pedro (Dia do Fico, 9 de janeiro de 1822).
                    • Ao proclamar a independência às margens do Ipiranga, D. Pedro I evitou o fracionamento territorial (ocorrido na América espanhola) e garantiu que a estrutura social escravocrata permanecesse intacta, sem participação popular nas decisões.

                    ### 3. A Constituição Outorgada de 1824 e o Poder Moderador
                    Em 1823, diante de um projeto liberal da Constituinte que limitava os poderes do monarca ("Constituição da Mandioca"), D. Pedro I dissolveu o parlamento à força militar (a *Noite da Agonia*):
                    • **Constituição de 1824:** Primeira constituição brasileira, imposta (outorgada) por decreto imperial.
                    • **O Poder Moderador (Art. 98):** "A chave de toda a organização política", exclusivo do Imperador, ficava acima dos poderes Executivo, Legislativo e Judiciário, permitindo vetar leis, dissolver a Câmara dos Deputados e nomear senadores vitalícios.
                    • **Voto Censitário e Indireto:** Apenas homens livres com renda líquida anual mínima comprovada (em moeda corrente) podiam votar. A grande maioria da população (escravizados, mulheres e homens pobres) ficou desprovida de direitos políticos.
                    • **Padroado Régio:** A Igreja Católica foi subordinada ao Estado imperial; o imperador nomeava bispos e autorizava bulas papais.
                    • **Reações:** A Confederação do Equador (1824) em Pernambuco, liderada por Frei Caneca, eclodiu em repúdio ao autoritarismo outorgado da Constituição de 1824, defendendo uma República liberal federativa.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Poder Moderador", "Poder exclusivo do Imperador sobreposto aos demais Poderes", "Permitia dissolver a Câmara, nomear ministros e governar autocraticamente"),
                    FormulaOrSyntaxItem("Voto Censitário", "Direito de voto condicionado à renda financeira mínima comprovada", "Excluiu a ampla maioria da população trabalhadora e negra da cidadania política"),
                    FormulaOrSyntaxItem("Independência Conservadora", "Ruptura política com Portugal sem alteração da estrutura social", "Manteve o latifúndio agroexportador, a escravidão e a monarquia hereditária")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Singularidade da Independência Brasileira nas Américas",
                        codeOrFormula = "América Espanhola (Repúblicas Fragmentadas) vs Brasil (Monarquia Territorialmente Unificada)",
                        explanation = "O ENEM aborda que, enquanto a América hispânica se fragmentou em dezenas de repúblicas sob a liderança de líderes locais (caudilhos), o Brasil manteve a unidade territorial sob uma monarquia dinástica europeia para preservar a escravidão e conter revoltas populares."
                    )
                ),
                goldTip = "Lembre-se da diferença jurídica: a Constituição de 1824 foi OUTORGADA (imposta autocraticamente pelo imperador), e não promulgada por uma assembleia constituinte eleita!"
            )

            "hist_imp_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # O Período Regencial (1831–1840) e as Revoltas Populares

                    Com a abdicação de D. Pedro I em 1831 e a minoridade do herdeiro Pedro de Alcântara (com apenas 5 anos), o Brasil foi governado por regentes. A historiografia define este período como a **"experiência republicana de fato"** do Império, marcada por descentralização política e intensos levantes provinciais e separatistas.

                    ### 1. As Correntes Políticas e as Reformas Institucionais
                    Três grupos disputavam o controle do aparelho de Estado no Rio de Janeiro:
                    • **Restauradores (Caramurus):** Defendiam o retorno de D. Pedro I ao trono (extintos com a morte de D. Pedro em 1834).
                    • **Liberais Moderados (Chimangos):** Grandes proprietários de terras do Sudeste, favoráveis a uma monarquia centralizada com reformas moderadas.
                    • **Liberais Exaltados (Farroupilhas / Jurujubas):** Defendiam a federação provincial autônoma e até a República.
                    • **O Ato Adicional de 1834:** Reforma liberal na Constituição de 1824 que criou as Assembleias Legislativas Provinciais (descentralização) e a Regência Una.
                    • **A Guarda Nacional (1831):** Criada pelo ministro Padre Diogo Feijó como força militar civil leal aos proprietários rurais locais. O comando era entregue aos grandes fazendeiros ("coronéis"), embrião do coronelismo brasileiro.

                    ### 2. O Mosaico das Revoltas Regenciais no ENEM
                    Diante da crise econômica e do vácuo de poder imperial, explodiram revoltas de naturezas distintas:

                    • **Cabanagem (1835–1840, Pará):**
                      - Base: População paupérrima de ribeirinhos, indígenas e tapuios que habitavam cabanas à beira dos rios.
                      - Destaque: **Única revolta em que os revoltosos populares conseguiram tomar a capital provincial (Belém) e o poder político**.
                      - Repressão: Extrema violência das tropas legalistas, resultando na morte de cerca de 30 mil pessoas (30% da população da província).

                    • **Guerra dos Farrapos / Farroupilha (1835–1845, Rio Grande do Sul e Santa Catarina):**
                      - Base: Elite estancieira criadora de gado do Sul (Bento Gonçalves, David Canabarro) e apoio de Giuseppe Garibaldi.
                      - Motivação: Revolta fiscal contra as tarifas impostas pelo governo imperial sobre o charque gaúcho, que era mais caro do que o charque platino importado do Uruguai e da Argentina.
                      - Destaque: Proclamação da República Rio-Grandense (Piratini) e República Juliana. Durou 10 anos e terminou com anistia ampla e integração de oficiais rebeldes ao Exército imperial pelo Tratado de Ponche Verde (1845).

                    • **Revolta dos Malês (1835, Salvador, Bahia):**
                      - Base: Escravizados e negros libertos urbanos de origem iorubá e haussá de fé islâmica (*malês*).
                      - Destaque: **Líderes alfabetizados em árabe que planejaram uma insurreição urbana na noite do Ramadã contra a opressão senhorial e a imposição católica**.
                      - Consequência: Instaurou o "medo do haitianismo" (pânico das elites brasileiras de uma revolução negra como a do Haiti de 1791), resultando em açoites, deportações e endurecimento do controle sobre escravizados urbanos.

                    • **Balaiada (1838–1841, Maranhão):**
                      - Base: Confronto popular entre vaqueiros, artesãos (fabricantes de balaios liderados por Manuel dos Balaios) e o quilombo de Cosme Bento com mais de 3 mil cativos fugidos.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Ato Adicional de 1834", "Descentralização política provisória e criação das Assembleias Provinciais", "Concedeu autonomia que acirrou as disputas de poder locais"),
                    FormulaOrSyntaxItem("Revolta dos Malês (1835)", "Insurreição de escravizados e forros muçulmanos letrados em Salvador", "Maior levante de escravizados urbanos da história das Américas"),
                    FormulaOrSyntaxItem("Guarda Nacional", "Milícia paramilitar civil entregue aos grandes proprietários de terras", "Origem institucional da figura do 'Coronel' na política brasileira")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Tratamento Diferenciado da Monarquia às Revoltas",
                        codeOrFormula = "Farroupilha (Elite do Sul: Anistia e Acordo) vs Cabanagem/Malês (Populares/Negros: Massacre e Repressão)",
                        explanation = "O ENEM cobra o corte de classe e de raça da repressão imperial: os fazendeiros farroupilhas do Sul foram anistiados e tiveram seus postos incorporados ao Exército; já os caboclos do Pará e os negros malês da Bahia foram massacrados sumariamente."
                    )
                ),
                goldTip = "No ENEM: A Revolta dos Malês destaca-se por desafiar o estereótipo do escravizado 'ignorante': muitos malês sabiam ler e escrever em árabe fluente, ao passo que a esmagadora maioria de seus senhores brancos era analfabeta!"
            )

            "hist_imp_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # O Segundo Reinado: Café, Guerra do Paraguai e Hegemonia Oligárquica

                    O longo reinado de D. Pedro II (1840–1889) representou a consolidação institucional do Estado imperial centralizado, alicerçado no sucesso da lavoura cafeeira no Sudeste e no equilíbrio controlado entre as elites políticas.

                    ### 1. O Golpe da Maioridade e o 'Parlamentarismo às Avessas'
                    Para encerrar as revoltas regenciais e unificar a nação sob uma figura de autoridade, os Liberais lideraram a antecipação da coroação de D. Pedro II aos 14 anos de idade em 1840:
                    • **Luzias (Liberais) vs. Saquaremas (Conservadores):** Partidos que se revezavam no governo sem discrepâncias de fundo ideológico. Ambas as agremiações defendiam a grande propriedade e o regime escravocrata (*'Nada mais parecido com um saquarema do que um luzia no poder'*).
                    • **O Sistema Parlamentarista às Avessas:**
                      - *Modelo Britânico:* O eleitorado vota no Parlamento -> o Parlamento escolhe o Primeiro-Ministro -> se o gabinete perde maioria, o governo cai.
                      - *Modelo Brasileiro Imperial:* O **Imperador** (via Poder Moderador) escolhia o Primeiro-Ministro (Presidente do Conselho) -> o Primeiro-Ministro organizava as eleições através de fraudes e coerção local ("eleições do cacete") -> a Câmara recém-eleita invariavelmente possuía maioria a favor do gabinete indicado pelo Imperador.
                      - D. Pedro II atuava como um árbitro neutro que alternava os partidos para evitar a hegemonia excessiva de qualquer facção.

                    ### 2. A Economia do Café e a Modernização Conservadora
                    O café tornou-se o principal produto da pauta de exportações brasileiras, respondendo por mais de 50% das receitas cambiais:
                    • **Vale do Paraíba (RJ e SP):** Região pioneira da cafeicultura. Solo exaurido por práticas agrícolas predatórias, relevo montanhoso, forte mentalidade aristocrática tradicional e apego irredutível à escravidão. Entrou em decadência acelerada a partir de 1870.
                    • **Oeste Paulista (Campinas, Ribeirão Preto):** Solo fértil de *terra roxa*, planaltos propícios a ferrovias (São Paulo Railway) e cafeicultores com visão empresarial e financeira. Foi a vanguarda na substituição do escravo pela mão de obra imigrante europeia e na defesa da República.
                    • **A 'Era Mauá':** Curto ciclo de investimentos industriais, estaleiros, ferrovias e iluminação a gás liderado pelo Barão de Mauá, após a extinção do tráfico negreiro liberar capitais ociosos.

                    ### 3. A Guerra da Tríplice Aliança / Guerra do Paraguai (1864–1870)
                    Maior conflito armado da história da América do Sul, opondo a Tríplice Aliança (Brasil, Argentina e Uruguai, com apoio financeiro britânico) contra o Paraguai de Solano López:
                    • **Os 'Voluntários da Pátria':** O Brasil recrutou homens pobres e prometeu liberdade a escravizados que aceitassem lutar no front de batalha.
                    • **Consequências Decisivas para o Brasil:**
                      1. *Destruição do Paraguai:* Arrasou a infraestrutura e dizimou grande parte da população masculina paraguaia.
                      2. *Endividamento Financeiro:* O Império contraiu gigantescas dívidas com bancos britânicos (como a Casa Rothschild).
                      3. *O Fortalecimento Político do Exército:* A corporação militar retornou profissionalizada, coesa e com sentimento de identidade nacional, abraçando o ideário positivista ("Ordem e Progresso") e o abolicionismo, entrando em rota direta de colisão com a elite política civil monárquica.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Parlamentarismo às Avessas", "O Imperador nomeava o Primeiro-Ministro, que depois forjava a maioria na Câmara", "Inversão do modelo clássico britânico para garantir a soberania do Poder Moderador"),
                    FormulaOrSyntaxItem("Oeste Paulista", "Terra roxa + Ferrovias + Imigração + Mentalidade Empresarial", "Região cafeeira dinâmica que financiou a Proclamação da República"),
                    FormulaOrSyntaxItem("Consequências da Guerra do Paraguai", "Endividamento britânico + Fortalecimento político e abolicionista do Exército", "Principal catalisador da crise estrutural da monarquia brasileira")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Impacto da Guerra do Paraguai na Questão da Escravidão",
                        codeOrFormula = "Escravizados lutando como 'Voluntários da Pátria' ao lado de soldados brancos",
                        explanation = "O ENEM destaca a contradição moral e política insustentável: soldados e oficiais negros do Exército voltaram da guerra como heróis laureados, mas encontravam suas mães, filhos e irmãos ainda acorrentados e açoitados nas fazendas de café do Vale do Paraíba."
                    )
                ),
                goldTip = "Atenção à oposição geográfica no café: Vale do Paraíba = Escravista, tradicional, conservador e monarquista; Oeste Paulista = Aberto à imigração, ferrovias, republicano e abolicionista prático."
            )

            "hist_imp_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # A Crise do Império e o Fim da Escravidão no Brasil

                    A queda do Império e a Proclamação da República (1889) resultaram da ruptura simultânea dos três pilares fundamentais que sustentavam o trono de D. Pedro II: a Igreja, o Exército e os grandes proprietários escravistas.

                    ### 1. O Fim do Tráfico Negreiro e a Lei de Terras (1850)
                    Sob severa pressão naval inglesa (a *Bill Aberdeen* de 1845 autorizava a Real Marinha britânica a afundar e capturar navios negreiros em qualquer oceano), o Brasil aprovou a **Lei Eusébio de Queirós (1850)**, encerrando a entrada de novos escravizados:
                    • **Tráfico Interprovincial:** Os cafeicultores ricos do Sudeste passaram a comprar massivamente escravizados do decadente Norte e Nordeste açucareiro.
                    • **A Lei de Terras de 1850:** Promulgada apenas duas semanas após o fim do tráfico, determinou que terras devolutas do Estado só poderiam ser adquiridas por **compra em dinheiro à vista**, e nunca mais por simples ocupação ou posse.
                    • *Objetivo no ENEM:* Impedir que imigrantes pobres e negros alforriados tivessem acesso à terra própria, garantindo que fossem forçados a vender sua força de trabalho aos latifúndios cafeeiros.

                    ### 2. O Processo Abolicionista Lento e a Resistência Negra
                    A elite parlamentar tentou protelar a abolição com uma legislação paliativa e gradual:
                    • **Lei do Ventre Livre (1871):** Declarava livres os filhos de mulheres escravizadas nascidos a partir daquela data, mas deixava-os sob tutela senhorial até os 8 anos (com indenização do Estado) ou até os 21 anos (trabalhando gratuitamente para o senhor).
                    • **Lei dos Sexagenários / Saraiva-Cotegipe (1885):** Libertava escravizados com mais de 60 anos, obrigando-os a prestar mais 3 anos de serviços ao senhor para indenizá-lo. Na prática, desobrigava o fazendeiro de alimentar idosos doentes.
                    • **O Movimento Abolicionista Popular:** Longe de ser um 'favor da princesa', a abolição foi arrancada pela mobilização de advogados negros e jornalistas (Luiz Gama, André Rebouças, José do Patrocínio), comícios públicos de massa, revoltas armadas e fugas maciças de senzalas inteiras para o Quilombo do Jabaquara em Santos.
                    • **Lei Áurea (13 de maio de 1888):** Aprovada no parlamento e assinada pela Princesa Isabel, aboliu formalmente a escravidão com apenas dois artigos.
                    • *A Ausência de Inclusão Cidadã:* A Lei Áurea **não concedeu terras, escolas, moradia ou indenização aos ex-escravizados**, empurrando centenas de milhares de homens e mulheres negras para o subemprego, a informalidade e a segregação espacial nas periferias e favelas.

                    ### 3. A Queda do Trono: O Rompimento do Tripé de Sustentação
                    A monarquia ruiu porque perdeu seus aliados históricos:
                    • **A Questão Religiosa (1872–1875):** Conflito em que o imperador prendeu dois bispos católicos que cumpriam ordens papais antimassônicas, alienando o clero.
                    • **A Questão Militar (1884–1889):** Militares proibidos pelo governo imperial civil de manifestar opiniões políticas nos jornais. Aderiram ao positivismo de Benjamin Constant ("progresso guiado pela ciência e pelo exército").
                    • **A Questão Escravocrata (Os 'Republicanos de 13 de Maio'):** Os barões do café do Vale do Paraíba, furiosos com a Lei Áurea promulgada sem indenização financeira pela perda de suas 'propriedades humanas', abandonaram a monarquia e aderiram ao Partido Republicano por pura vingança.
                    • **O Golpe de 15 de Novembro de 1889:** Liderado pelo Marechal Deodoro da Fonseca, cercou o Ministério da Guerra e depôs D. Pedro II.
                    • *A Afastamento Popular:* Conforme registrou o jornalista Aristides Lobo na época: *"O povo assistiu àquilo tudo bestializado, atônito, surpreso, sem saber o que significava; julgou ver um desfile militar"*.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Lei de Terras de 1850", "Acesso à terra condicionado estritamente à compra em dinheiro", "Estratégia da elite para barrar o acesso à propriedade aos libertos e imigrantes"),
                    FormulaOrSyntaxItem("Lei Áurea sem Reparação", "Abolição jurídica imediata sem políticas públicas de integração ou terra", "Raiz estrutural da exclusão socioeconômica e do racismo estrutural no Brasil"),
                    FormulaOrSyntaxItem("Tripé de Sustentação Monárquica", "Exército + Igreja Católica + Aristocracia Escravista", "A ruptura simultânea com esses 3 pilares viabilizou o Golpe Republicano de 1889")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Análise da Citação de Aristides Lobo ('O povo assistiu bestializado')",
                        codeOrFormula = "Proclamação da República como Golpe de Cúpula Cívico-Militar sem Revolução Popular",
                        explanation = "O ENEM utiliza essa famosa crônica para ilustrar que a República não nasceu de uma insurreição democrática popular, mas de um arranjo entre a cúpula militar positivista e os cafeicultores paulistas, alijando as classes populares da nova ordem política."
                    )
                ),
                goldTip = "Relacione sempre a Lei de Terras (1850) com a Lei Eusébio de Queirós (1850): as duas leis foram aprovadas quase juntas justamente para garantir que o latifúndio monocultor continuasse hegemônico após o fim da entrada de escravizados africanos."
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "Estudo do período imperial brasileiro focado na formação do Estado e nas lutas sociais.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Atente-se para os limites da cidadania na ordem monárquica."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "hist_imp_01" -> listOf(
                MathExercise(
                    id = "hist_ex_imp_01_a",
                    title = "ENEM: A Constituição de 1824 e a Cidadania Censitária",
                    problemStatement = "A Constituição outorgada de 1824 estruturou o sistema político do Império do Brasil introduzindo o Poder Moderador e definindo os critérios para o exercício dos direitos políticos.\n\nEm relação ao corpo de cidadãos com direito a voto no Primeiro Reinado, a Constituição estabeleceu que:",
                    options = listOf(
                        "O sufrágio era universal, permitindo a homens, mulheres e libertos a escolha direta do Imperador.",
                        "O voto era censitário e indireto, exigindo uma renda financeira mínima comprovada que excluía grande parte da população pobre e os escravizados.",
                        "Apenas os membros das ordens religiosas jesuíticas e oficiais militares tinham o direito de votar nas províncias.",
                        "O voto era facultativo e restrito aos cidadãos com diploma universitário obtido na Europa.",
                        "As eleições eram dispensadas porque todos os deputados eram nomeados vitaliciamente pela Assembleia Constituinte."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se do critério 'censitário': o direito ao voto estava condicionado à posse de riqueza (renda líquida anual mínima).",
                    stepByStepSolution = "A alternativa B está correta. A Constituição de 1824 estabeleceu o voto censitário e indireto (em dois graus). Para ser votante ou eleitor de paróquia e província, era obrigatório comprovar uma renda mínima anual em dinheiro (ou alqueires de farinha). Essa barreira censitária marginalizou juridicamente os escravizados (que não tinham condição de cidadão), as mulheres e a maioria dos homens livres pobres, concentrando o poder nas mãos dos grandes proprietários de terras e comerciantes ricos."
                ),
                MathExercise(
                    id = "hist_ex_imp_01_b",
                    title = "ENEM: O Poder Moderador e o Autoritarismo Imperial",
                    problemStatement = "O artigo 98 da Constituição de 1824 declarava: 'O Poder Moderador é a chave de toda a organização política, e é delegado privativamente ao Imperador, como Chefe Supremo da Nação'.\n\nNa prática política do Império brasileiro, a função desse quarto poder correspondia a:",
                    options = listOf(
                        "Garantir a autonomia irrestrita aos municípios para declararem independência sempre que julgassem necessário.",
                        "Permitir ao monarca intervir nos outros três poderes, dissolvendo a Câmara dos Deputados e nomeando senadores vitalícios para assegurar a supremacia da Coroa.",
                        "Transferir o comando das Forças Armadas para o Primeiro-Ministro eleito pela bancada de oposição.",
                        "Subordinar a figura do Imperador às decisões do Supremo Tribunal de Justiça em todos os atos civis.",
                        "Garantir que a Igreja Católica pudesse revogar qualquer lei aprovada pelo parlamento imperial."
                    ),
                    correctIndex = 1,
                    hint = "O Poder Moderador foi idealizado pelo pensador suíço Benjamin Constant, mas no Brasil de D. Pedro I serviu como instrumento de controle absolutista sobre o Legislativo e o Judiciário.",
                    stepByStepSolution = "A alternativa B está correta. O Poder Moderador concedia a D. Pedro I (e posteriormente a D. Pedro II) amplos poderes constitucionais para interferir diretamente nas deliberações do Legislativo, dissolver a Câmara dos Deputados quando esta entrava em atrito com o imperador, vetar leis, nomear senadores vitalícios (a partir de uma lista tríplice) e destituir juízes, consolidando o caráter centralizador e autoritário do Estado imperial."
                )
            )

            "hist_imp_02" -> listOf(
                MathExercise(
                    id = "hist_ex_imp_02_a",
                    title = "ENEM: As Revoltas do Período Regencial",
                    problemStatement = "Durante a década de 1830, o Brasil viveu uma onda de rebeliões provinciais (Cabanagem, Farroupilha, Sabinada, Balaiada e Revolta dos Malês). A eclosão simultânea desses conflitos decorreu principalmente:",
                    options = listOf(
                        "Da tentativa de invasão da marinha francesa para restabelecer a colônia no Rio de Janeiro.",
                        "Da crise econômica gerada pelo vácuo de poder centralizado com a abdicação de D. Pedro I e da insatisfação com a cobrança de impostos e a exclusão social.",
                        "Da decisão do regente Feijó de abolir sumariamente a escravidão e distribuir terras para todas as famílias do Norte.",
                        "Da recusa dos governadores provinciais em aceitar a proclamação da República promovida pelo imperador D. Pedro II aos cinco anos.",
                        "Do fechamento de todas as rotas comerciais marítimas pelos imperadores da Tríplice Aliança platina."
                    ),
                    correctIndex = 1,
                    hint = "Com a renúncia de D. Pedro I em 1831, o governo regencial não detinha a legitimidade monárquica plena, estimulando tanto as elites regionais quanto as massas marginalizadas a se insurgirem.",
                    stepByStepSolution = "A alternativa B está correta. O Período Regencial foi marcado por uma profunda crise de legitimidade do poder central. A ausência de um imperador coroado, somada às disputas entre facções políticas no Rio de Janeiro, à miséria das camadas populares no interior e aos pesados impostos cobrados das províncias, criou o cenário propício para revoltas tanto de caráter popular e identitário (Cabanagem, Malês) quanto de protesto das elites regionais (Farroupilha)."
                ),
                MathExercise(
                    id = "hist_ex_imp_02_b",
                    title = "ENEM: A Especificidade da Revolta dos Malês (1835)",
                    problemStatement = "Em janeiro de 1835, eclodiu em Salvador a Revolta dos Malês. Este levante urbano é considerado singular na história das resistências escravas no Brasil porque:",
                    options = listOf(
                        "Contou com o apoio financeiro integral da aristocracia cafeeira paulista para desestabilizar o Império.",
                        "Foi articulado por africanos islamizados (iorubás e haussás), muitos alfabetizados em língua árabe, que contestavam a escravidão e a imposição da religião católica oficial.",
                        "Pretendia transformar a Bahia em uma monarquia constitucional chefiada por D. Pedro I após seu retorno de Portugal.",
                        "Defendia a manutenção do cativeiro no campo e a libertação apenas dos trabalhadores domésticos da capital.",
                        "Resultou na libertação pacífica e imediata de todos os escravizados da província baiana com anistia do governo regencial."
                    ),
                    correctIndex = 1,
                    hint = "Observe o termo 'Malês', derivado de 'imalê' (muçulmano em iorubá). Seus líderes utilizavam amuletos com versículos do Alcorão escritos em árabe para organizar o movimento.",
                    stepByStepSolution = "A alternativa B está correta. A Revolta dos Malês em 1835 foi liderada por escravizados e negros forros urbanos convertidos ao Islã. Ao contrário do estereótipo colonial de ignorância dos escravizados, muitos dos rebeldes malês sabiam ler e escrever em árabe fluente, utilizando cartas cifradas para planejar o levante contra a opressão senhorial e as restrições religiosas católicas impostas pelo Estado imperial."
                )
            )

            "hist_imp_03" -> listOf(
                MathExercise(
                    id = "hist_ex_imp_03_a",
                    title = "ENEM: A Dinâmica do Parlamentarismo às Avessas",
                    problemStatement = "O arranjo político que vigorou no Segundo Reinado sob o reinado de D. Pedro II ficou conhecido historiograficamente como 'Parlamentarismo às Avessas'. Esse modelo se caracterizava pelo fato de que:",
                    options = listOf(
                        "O povo elegia diretamente o Primeiro-Ministro, e este decidia se o Imperador continuaria no trono.",
                        "O Imperador utilizava o Poder Moderador para nomear o Presidente do Conselho de Ministros, que posteriormente forjava as eleições para obter maioria no Parlamento.",
                        "O poder legislativo era chefiado pelo bispo do Rio de Janeiro, sem qualquer interferência de deputados ou senadores civis.",
                        "Os partidos Liberal e Conservador foram banidos, permitindo apenas a eleição de generais veteranos do Exército.",
                        "O Parlamento tinha o poder exclusivo de destituir a monarquia e proclamar a república a qualquer momento."
                    ),
                    correctIndex = 1,
                    hint = "Pense na ordem inversa: na Inglaterra, o Parlamento vem antes do Primeiro-Ministro. No Brasil imperial, o Imperador vinha antes de tudo.",
                    stepByStepSolution = "A alternativa B está correta. No parlamentarismo clássico britânico, o poder emana do parlamento eleito pelos cidadãos. No Brasil do Segundo Reinado, ocorria a inversão ('às avessas'): D. Pedro II escolhia discricionariamente o Primeiro-Ministro com base no Poder Moderador. Uma vez empossado, esse ministro organizava as eleições através de fraudes, coronéis locais e coerção policial, garantindo a maioria dos assentos na Câmara para a sua legenda política."
                ),
                MathExercise(
                    id = "hist_ex_imp_03_b",
                    title = "ENEM: As Transformações do Café e o Oeste Paulista",
                    problemStatement = "A partir da segunda metade do século XIX, a expansão cafeeira no Oeste Paulista diferenciou-se estruturalmente da cafeicultura tradicional do Vale do Paraíba por apresentar:",
                    options = listOf(
                        "O abandono total do cultivo do café para se dedicar exclusivamente à mineração e à metalurgia pesada.",
                        "A presença de solos férteis de terra roxa, investimentos em redes ferroviárias e a substituição progressiva do trabalho escravo pelo braço imigrante assalariado.",
                        "A proibição do ingresso de imigrantes italianos e alemães em razão da lealdade exclusiva aos senhores de engenho de açúcar.",
                        "O cultivo exclusivo em pequenas terras comunais sem latifúndio e sem fins de exportação internacional.",
                        "A destruição de todas as linhas de trem em prol do transporte manual de sacas por tropeiros montados."
                    ),
                    correctIndex = 1,
                    hint = "O Oeste Paulista (região de Campinas e Ribeirão Preto) tinha topografia favorável a trilhos de trem, terras vulcânicas férteis e fazendeiros voltados para o mercado financeiro e a imigração.",
                    stepByStepSolution = "A alternativa B está correta. Enquanto o Vale do Paraíba entrou em colapso devido à erosão dos morros e ao apego irracional à escravidão tradicional, o Oeste Paulista emergiu como um polo dinâmico. Beneficiando-se do solo riquíssimo de terra roxa e do relevo plano, os fazendeiros investiram nas ferrovias (como a Santos-Jundiaí) e patrocinaram o sistema de colonato com imigrantes europeus assalariados, liderando a posterior proclamação da República."
                )
            )

            "hist_imp_04" -> listOf(
                MathExercise(
                    id = "hist_ex_imp_04_a",
                    title = "ENEM: A Lei de Terras de 1850 e o Fechamento Social",
                    problemStatement = "Aprovada no mesmo ano em que a Lei Eusébio de Queirós extinguiu o tráfico transatlântico de escravizados, a Lei de Terras de 1850 estabeleceu novas regras para a posse do solo brasileiro. A finalidade sociopolítica central dessa lei consistiu em:",
                    options = listOf(
                        "Distribuir pequenas propriedades rurais gratuitas para todos os negros libertos e imigrantes pobres que chegassem ao país.",
                        "Determinar que as terras públicas só poderiam ser obtidas por compra em dinheiro à vista, impedindo o acesso à terra aos trabalhadores livres e imigrantes pobres.",
                        "Estatizar todos os latifúndios de café e transformá-los em cooperativas agrícolas geridas pelo Ministério da Justiça.",
                        "Proibir terminantemente que qualquer proprietário de terras pudesse vender café para o mercado externo britânico.",
                        "Restituir todas as sesmarias da Coroa às comunidades indígenas originárias do interior de São Paulo."
                    ),
                    correctIndex = 1,
                    hint = "Com o fim iminente da escravidão, como os grandes fazendeiros de café garantiriam que imigrantes e ex-escravizados trabalhassem em suas fazendas em vez de cultivarem sua própria terra?",
                    stepByStepSolution = "A alternativa B está correta. A Lei de Terras de 1850 foi uma manobra conservadora da elite agrária imperial. Sabendo que o fim do tráfico de escravizados exigiria a atração de trabalhadores livres (imigrantes e forros), a lei aboliu a posse tradicional e determinou que a terra só podia ser comprada por altos valores em dinheiro à vista. Dessa forma, impossibilitou que os pobres se tornassem donos de terra, forçando-os a trabalhar como empregados mal remunerados nos cafezais dos barões da elite."
                ),
                MathExercise(
                    id = "hist_ex_imp_04_b",
                    title = "ENEM: A Queda do Império e o Golpe de 1889",
                    problemStatement = "O colapso da monarquia brasileira em 15 de novembro de 1889 é amplamente analisado pelos historiadores como resultado da quebra dos laços de sustentação política do trono com a Igreja, o Exército e os cafeicultores escravistas. A esse respeito, a famosa expressão do jornalista Aristides Lobo — 'O povo assistiu àquilo tudo bestializado' — sintetiza:",
                    options = listOf(
                        "O caráter de insurreição popular armada com massas operárias ocupando as ruas do Rio de Janeiro para derrubar a família real.",
                        "O caráter cupular e militar do movimento republicano, conduzido sem a participação efetiva ou a mobilização das camadas populares, que viram a proclamação como um mero desfile militar.",
                        "A alegria incontrolável dos escravizados recém-libertos que elegeram Marechal Deodoro como o primeiro presidente socialista do continente.",
                        "O apoio irrestrito dos camponeses pobres à restauração imediata do absolutismo português sob o comando do Papa Pio IX.",
                        "A tristeza generalizada da população que derrotou os militares golpistas e reinstalou D. Pedro II no trono no dia seguinte."
                    ),
                    correctIndex = 1,
                    hint = "A República no Brasil não foi fruto de uma revolução popular nas ruas (como na França), mas de um golpe palaciano orquestrado pela cúpula militar positivista aliada aos cafeicultores paulistas.",
                    stepByStepSolution = "A alternativa B está correta. A Proclamação da República não contou com mobilização ou participação popular ativa. Tratou-se de um movimento de cúpula (golpe cívico-militar) articulado por oficiais positivistas do Exército e pelos cafeicultores do Oeste Paulista, descontentes com o Império. O povo pobre do Rio de Janeiro observou a marcha das tropas de Deodoro da Fonseca como se fosse uma parada militar comemorativa ('bestializado'), sendo excluído das decisões fundadoras da nova ordem republicana."
                )
            )

            else -> emptyList()
        }
    }
}
