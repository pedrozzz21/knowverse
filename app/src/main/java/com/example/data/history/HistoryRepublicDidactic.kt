package com.example.data.history

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático Aprofundado e Questões Analíticas no Padrão ENEM
 * Módulo 3: História do Brasil República
 * - hist_rep_01: República Velha (1889–1930)
 * - hist_rep_02: Era Vargas (1930–1945)
 * - hist_rep_03: República Populista (1945–1964)
 * - hist_rep_04: Ditadura Civil-Militar (1964–1985)
 * - hist_rep_05: Nova República (1985 até os dias atuais)
 */
object HistoryRepublicDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "hist_rep_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # República Velha (1889–1930): Oligarquias e Resistências Populares

                    A Primeira República no Brasil estruturou-se como um arranjo político entre as elites agrárias regionais, excluindo a ampla maioria da população trabalhadora através de práticas coercitivas e fraudes eleitorais.

                    ### 1. A Engrenagem das Oligarquias
                    • **Coronelismo e Voto de Cabresto:** O voto era aberto (não secreto), permitindo que os fazendeiros e chefes políticos locais ("coronéis") controlassem o voto dos eleitores dependentes por meio de favores, violência física (jagunços) e fraudes nas atas eleitorais ("degola").
                    • **Política dos Governadores:** Articulada pelo presidente Campos Sales, consistia em uma troca de favores mútua: o presidente apoiava as oligarquias estaduais dominantes e, em contrapartida, as bancadas dos governadores no Congresso aprovavam os projetos do Executivo federal.
                    • **Política do Café com Leite:** Alternância na Presidência da República entre as oligarquias cafeeira de São Paulo e leiteira/agrária de Minas Gerais.

                    ### 2. O Caldeirão de Revoltas Sociais
                    Diante do abandono estatal, da carestia e do autoritarismo, explodiram revoltas urbanas e rurais de grande repercussão no ENEM:
                    • **Guerra de Canudos (1896–1897, BA):** Comunidade messiânica liderada por Antônio Conselheiro em Belo Monte, reunindo sertanejos, ex-escravizados e sertanejos sem-terra. Destruída pelo Exército após quatro expedições militares.
                    • **Guerra do Contestado (1912–1916, SC/PR):** Revolta de camponeses expulsos de suas terras para a construção da ferrovia *Brazil Railway Company* e a exploração madeireira por empresas estrangeiras.
                    • **Revolta da Vacina (1904, RJ):** Insurreição popular no Rio de Janeiro contra a vacinação antivariólica obrigatória conduzida por Oswaldo Cruz e a violenta reforma urbana do prefeito Pereira Passos (política do "bota-abaixo", que demoliu cortiços e empurrou as camadas populares para os morros, gerando as favelas).
                    • **Revolta da Chibata (1910, RJ):** Marinheiros negros da Armada, liderados por João Cândido ("o Almirante Negro"), rebelaram-se contra os castigos corporais (chibatadas), o racismo institucional e a má alimentação nos navios de guerra.

                    ### 3. A Crise dos Anos 1920 e o Tenentismo
                    • **Movimento Tenentista:** Oficiais de baixa patente do Exército (tenentes e capitães) rebelaram-se contra as oligarquias cafeeiras, exigindo voto secreto, moralização política e criação da Justiça Eleitoral (Revolta dos 18 do Forte de Copacabana em 1922 e a Coluna Prestes).
                    • **A Semana de Arte Moderna de 1922:** Ruptura estética no Teatro Municipal de São Paulo, defendendo a antropofagia cultural e uma arte genuinamente brasileira contra o academicismo parnasiano.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Coronelismo", "Poder privado dos latifundiários sobre o voto aberto", "Base municipal que sustentava a Política dos Governadores"),
                    FormulaOrSyntaxItem("Revoltas Messiânicas", "Canudos e Contestado", "Resposta camponesa de sobrevivência e fé diante da exclusão republicana"),
                    FormulaOrSyntaxItem("Tenentismo", "Rebelião militar moralizadora dos anos 1920", "Exigia voto secreto e pôs em xeque o domínio das oligarquias cafeeiras")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Reforma Pereira Passos e a Segregação Urbana",
                        codeOrFormula = "Higienismo e Belle Époque carioca vs População pobre dos cortiços",
                        explanation = "O ENEM analisa como a modernização inspirada em Paris ('bota-abaixo') expulsou os trabalhadores do centro do Rio para as encostas dos morros, inaugurando o processo histórico de favelização."
                    )
                ),
                goldTip = "Relacione sempre a Revolta da Chibata (1910) com a herança colonial: mesmo 22 anos após a abolição da escravidão, a Marinha de guerra ainda açoitava marinheiros negros no convés."
            )

            "hist_rep_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Era Vargas (1930–1945): Centralização, Trabalho e Cidadania Regulada

                    A Revolução de 1930 encerrou a hegemonia da República do Café com Leite e levou Getúlio Vargas ao poder. O período varguista redefiniu as relações entre Estado, classe trabalhadora e desenvolvimento industrial no Brasil.

                    ### 1. As Três Fases da Era Vargas
                    • **Governo Provisório (1930–1934):** Dissolução do Congresso, nomeação de interventores nos estados (gerando a Revolução Constitucionalista de 1932 em SP) e promulgação da Constituição de 1934 (que garantiu o voto feminino, voto secreto e direitos trabalhistas básicos).
                    • **Governo Constitucional (1934–1937):** Polarização ideológica entre a AIB (Ação Integralista Brasileira, de inspiração fascista) e a ANL (Aliança Nacional Libertadora, de esquerda anti-imperialista, liderada por Luís Carlos Prestes). O pretexto do fictício "Plano Cohen" (suposta ameaça comunista) justificou o fechamento das instituições democráticas.
                    • **O Estado Novo (1937–1945):** Ditadura aberta com a Constituição "Polaca" de 1937, censura prévia através do DIP (Departamento de Imprensa e Propaganda), repressão política a opositores e culto personalista à figura de Vargas como o "Pai dos Pobres".

                    ### 2. A Legislação Trabalhista e o Corporativismo
                    • **Cidadania Regulada:** A sociologia histórica (Wanderley Guilherme dos Santos) demonstra que a cidadania no varguismo dependia da vinculação do indivíduo a uma profissão reconhecida em carteira de trabalho (CLT de 1943).
                    • **Controle Sindical:** A estrutura sindical era atrelada ao Ministério do Trabalho através da cobrança do imposto sindical obrigatório e da proibição de greves livres, cooptando a liderança operária (sindicatos "pelegos").
                    • **Industrialização de Base:** Criação das indústrias estatais estratégicas com recursos da neutralidade diplomática na Segunda Guerra Mundial (CSN em Volta Redonda, Vale do Rio Doce, Fábrica Nacional de Motores).

                    ### 3. A Contradição da Segunda Guerra e o Fim do Estado Novo
                    O envio da FEB (Força Expedicionária Brasileira) à Itália para lutar contra o nazifascismo gerou uma contradição insustentável: soldados brasileiros lutavam pela democracia no exterior enquanto viviam sob uma ditadura em casa, culminando na deposição de Vargas em 1945 pelo Exército.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Cidadania Regulada", "Direitos de cidadania condicionados ao registro formal na CLT", "Conceito sociológico amplamente cobrado no ENEM"),
                    FormulaOrSyntaxItem("DIP", "Departamento de Imprensa e Propaganda", "Aparelho do Estado Novo para censura e propaganda oficial"),
                    FormulaOrSyntaxItem("Indústria de Base", "Companhia Siderúrgica Nacional e Vale do Rio Doce", "Papel do Estado como indutor da industrialização pesada")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Uso do Rádio no Estado Novo",
                        codeOrFormula = "Programa 'A Hora do Brasil' e os sambas ufanistas",
                        explanation = "O ENEM demonstra como o rádio foi apropriado pelo regime para unificar a identidade cultural nacional, desestimular a malandragem boêmia e exaltar a dignidade do trabalho operário disciplinado."
                    )
                ),
                goldTip = "Fique atento: as leis trabalhistas da CLT de Vargas NÃO foram estendidas aos trabalhadores rurais e domésticos! Apenas os operários urbanos foram contemplados nessa fase."
            )

            "hist_rep_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # República Populista (1945–1964): Desenvolvimentismo e Crise Social

                    O intervalo democrático entre o fim do Estado Novo e o golpe de 1964 foi marcado pela redemocratização, disputa de projetos econômicos (nacionalismo vs. liberalismo) e pela intensa mobilização popular.

                    ### 1. O Retorno de Vargas (1951–1954) e o Debate do Petróleo
                    • Eleito pelo voto popular, Vargas adotou um nacionalismo econômico estratégico.
                    • **Criação da Petrobras (1953):** Fruto da grande campanha popular "O Petróleo é Nosso", garantindo o monopólio estatal da exploração do petróleo.
                    • **Crise Política e Suicídio:** Diante da forte oposição da UDN (liderada pelo jornalista Carlos Lacerda) e da pressão militar após o atentado da Rua Tonelero, Vargas cometeu suicídio em agosto de 1954, deixando a célebre Carta-Testamento ("Saio da vida para entrar na História"), adiando o golpe militar em uma década.

                    ### 2. O Governo JK (1956–1961): O Nacional-Desenvolvimentismo
                    • **Plano de Metas:** Slogan "50 anos em 5", priorizando energia, transporte e indústrias pesadas.
                    • **Abertura ao Capital Multinacional:** Instalação das montadoras de automóveis estrangeiras (Volkswagen, Ford) no ABC Paulista, consagrando a opção pelo rodoviarismo em detrimento das ferrovias.
                    • **Construção de Brasília (1960):** Transferência da capital federal para o planalto central como símbolo de integração geopolítica e modernidade arquitetônica (Oscar Niemeyer e Lúcio Costa), erguida com o suor dos candangos nordestinos.
                    • **O Custo Oculto:** Explosão da dívida externa, inflação galopante e aprofundamento das desigualdades regionais.

                    ### 3. A Crise de Jânio Quadros e o Governo Jango (1961–1964)
                    • **Jânio Quadros:** Governo errático de apenas 7 meses, marcado por medidas folclóricas (proibição de biquínis e brigas de galo) e pela Política Externa Independente (condecoração do revolucionário Che Guevara), culminando em sua renúncia inesperada.
                    • **João Goulart (Jango) e as Reformas de Base:** Jango propôs reformas estruturais de inclusão social: reforma agrária no campo (atendendo às Ligas Camponesas de Francisco Julião), reforma tributária progressiva, educacional (método Paulo Freire) e eleitoral (voto para os analfabetos).
                    • **O Golpe de 1964:** As elites empresariais (IPES, IBAD), grandes latifundiários, a cúpula das Forças Armadas e o apoio do governo dos Estados Unidos (Operação Brother Sam) articularam o golpe civil-militar de 31 de março de 1964 sob o falso pretexto de impedir uma "ameaça comunista".
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Nacional-Desenvolvimentismo", "Aliança entre capital estatal de base e multinacionais de consumo", "Modelo de JK que privilegiou as rodovias e a indústria automobilística"),
                    FormulaOrSyntaxItem("Reformas de Base", "Reforma agrária, urbana, tributária e educacional propostas por Jango", "Principal projeto modernizador e distributivo estancado pelo golpe de 64"),
                    FormulaOrSyntaxItem("Ligas Camponesas", "Movimento de trabalhadores rurais no Nordeste por terra e direitos", "Lideradas por Francisco Julião, catalisaram a urgência da reforma agrária")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Contradição da Construção de Brasília",
                        codeOrFormula = "Vanguarda arquitetônica futurista vs Exploração dos candangos",
                        explanation = "O ENEM aborda como a monumentalidade modernista de Brasília contrastava com o cotidiano desumano dos trabalhadores nordestinos (candangos), que construíram a cidade em ritmo frenético e foram empurrados para as cidades-satélites sem saneamento."
                    )
                ),
                goldTip = "O termo correto e consolidado na historiografia do ENEM é DITADURA CIVIL-MILITAR, destacando que os militares não agiram sozinhos: contaram com apoio da grande imprensa, empresários e setores conservadores da Igreja."
            )

            "hist_rep_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Ditadura Civil-Militar (1964–1985): Autoritarismo, Repressão e Resistências

                    Durante 21 anos, o Brasil viveu sob um regime militar caracterizado pela suspensão dos direitos políticos fundamentais, perseguição a opositores e aprofundamento das desigualdades econômicas.

                    ### 1. O Recrudescimento Institucional e o AI-5
                    • **Atos Institucionais (AIs):** Mecanismos jurídicos de exceção que sobrepunham a vontade dos generais-presidentes à Constituição:
                      - *AI-1 (1964):* Cassação de mandatos e suspensão de direitos políticos de parlamentares.
                      - *AI-2 (1965):* Extinção dos partidos políticos e criação do bipartidarismo artificial: ARENA (governista) e MDB (oposição consentida).
                      - *AI-5 (13 de dezembro de 1968):* O ápice do terrorismo de Estado. Fechamento do Congresso Nacional por tempo indeterminado, suspensão do *habeas corpus* para crimes políticos, censura prévia generalizada a jornais, músicas, filmes e teatro, e carta branca para prisões arbitrárias e tortura sistemática no DOI-CODI e OBAN.

                    ### 2. O 'Milagre Econômico' (1969–1973) e a Concentração de Renda
                    Sob o governo de Emílio Médici, o PIB cresceu a taxas de 10% ao ano, impulsionado por obras faraônicas (Ponte Rio-Niterói, Transamazônica, Usina de Itaipu):
                    • **A Ilusão do Crescimento:** O crescimento foi financiado por endividamento externo bilionário e arrocho salarial sobre a classe trabalhadora.
                    • **A Metáfora do Bolo de Delfim Netto:** O ministro da Fazenda afirmava que era preciso *'fazer o bolo crescer para depois dividir'*. O bolo cresceu, mas nunca foi dividido: os ricos ficaram mais ricos e os trabalhadores sofreram queda brutal no poder de compra, gerando extrema concentração de renda.

                    ### 3. A Luta contra a Ditadura e as Diretas Já
                    • **Resistências Culturais:** O Tropicalismo (Caetano Veloso, Gilberto Gil), as canções de protesto de Chico Buarque e Geraldo Vandré, o teatro engajado e o Cinema Novo.
                    • **Luta Armada e Guerrilha:** Movimentos de esquerda urbana (ALN de Carlos Marighella, MR-8) e a Guerrilha do Araguaia na Amazônia.
                    • **O Novo Sindicalismo do ABC Paulista (1978–1980):** Greves operárias lideradas por metalúrgicos como Lula, que desafiaram a Lei de Segurança Nacional e inauguraram uma nova era de mobilização popular independente.
                    • **A Transição 'Lenta, Gradual e Segura' (Geisel e Figueiredo):** Lei da Anistia de 1979 (que libertou presos políticos e permitiu o retorno dos exilados, mas anistiou preventivamente os agentes estatais torturadores).
                    • **Campanha das Diretas Já (1984):** O maior movimento popular de massas da história republicana, que levou milhões de brasileiros às ruas vestindo amarelo para exigir eleições presidenciais diretas através da Emenda Dante de Oliveira.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("AI-5 (1968)", "Suspensão do habeas corpus + Fechamento do Congresso + Censura", "Instrumento máximo do terror estatal e da repressão sistemática"),
                    FormulaOrSyntaxItem("Milagre Econômico", "Crescimento do PIB com arrocho salarial e endividamento externo", "Resultou no recorde histórico de concentração de renda e desigualdade"),
                    FormulaOrSyntaxItem("Diretas Já (1984)", "Mobilização popular suprapartidária pela eleição presidencial direta", "Embora derrotada na Câmara, tornou a ditadura militar politicamente insustentável")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Lei da Anistia de 1979 e a Justiça de Transição",
                        codeOrFormula = "Anistia 'ampla, geral e irrestrita' como pacto conciliador",
                        explanation = "O ENEM discute com frequência que o regime militar impôs uma anistia bilateral que blindou torturadores e militares de responderem judicialmente por crimes contra a humanidade, diferentemente do que ocorreu na Argentina e no Chile."
                    )
                ),
                goldTip = "Atenção no ENEM: A Emenda Dante de Oliveira (Diretas Já) foi DERROTADA no Congresso em 1984 por não atingir os dois terços necessários. O primeiro presidente civil (Tancredo Neves) foi eleito por via INDIRETA no Colégio Eleitoral."
            )

            "hist_rep_05" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Nova República (1985–Dias Atuais): A Constituição Cidadã e a Democracia

                    A redemocratização restabeleceu o Estado Democrático de Direito no Brasil, mas enfrentou o desafio de conciliar a conquista de direitos civis com a estabilização econômica e o combate à herança de desigualdades históricas.

                    ### 1. A Constituição Cidadã de 1988
                    Promulgada após ampla mobilização popular e emendas populares na Assembleia Nacional Constituinte presidida por Ulysses Guimarães:
                    • **Ampliação da Cidadania:** Definição do racismo como crime inafiançável e imprescritível; garantia de direitos territoriais às comunidades indígenas e quilombolas (Artigos 231 e 68 do ADCT); criminalização da tortura; igualdade formal entre homens e mulheres.
                    • **Seguridade Social Universal:** Criação do SUS (Sistema Único de Saúde), da Previdência Social pública e do conceito de Assistência Social como dever do Estado e direito do cidadão.
                    • **Universalização do Voto:** Concessão do direito de voto aos analfabetos e facultativo aos jovens de 16 e 17 anos.

                    ### 2. A Batalha contra a Hiperinflação e o Plano Real (1994)
                    Durante os governos Sarney e Collor, o Brasil viveu uma espiral inflacionária devastadora (hiperinflação superior a 1000% ao ano) que corroía diariamente o salário dos trabalhadores:
                    • **O Impeachment de Collor (1992):** O escândalo de corrupção do esquema PC Farias levou milhares de jovens com rostos pintados de verde e amarelo às ruas (os "Caras-Pintadas"), resultando no primeiro afastamento de um presidente por impeachment na história do país.
                    • **O Plano Real (1994):** Conduzido no governo Itamar Franco pelo ministro Fernando Henrique Cardoso, utilizou a URV (Unidade Real de Valor) para estabilizar os preços, ancorou a nova moeda (o Real) e encerrou décadas de inflação descontrolada.

                    ### 3. As Políticas Sociais e as Lutas Contemporâneas no Século XXI
                    • **Afirmação de Direitos Coletivos:** Nos anos 2000, o fortalecimento das políticas distributivas de combate à extrema pobreza (Bolsa Família, valorização do salário mínimo) e das políticas de ações afirmativas (Lei de Cotas Raciais e Sociais nas universidades federais em 2012).
                    • **O Papel dos Movimentos Sociais:** O protagonismo do MST (Movimento dos Trabalhadores Rurais Sem Terra), MTST (Trabalhadores Sem Teto), movimento negro, feminista e indígena na cobrança pela efetivação das garantias da Constituição de 1988.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Constituição Cidadã de 1988", "Marco civilizatório que estabeleceu o Estado de Bem-Estar Social brasileiro", "Consagrou o SUS, direitos indígenas/quilombolas e igualdade de gênero"),
                    FormulaOrSyntaxItem("Plano Real (1994)", "Estabilização monetária através da URV e fim da hiperinflação", "Restabeleceu o poder de compra e previsibilidade da economia"),
                    FormulaOrSyntaxItem("Ações Afirmativas", "Políticas públicas para corrigir disparidades históricas de raça e classe", "Ex: Lei de Cotas Sociais e Raciais no ensino superior público")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Artigo 231 da Constituição e o Direito Originário Indígena",
                        codeOrFormula = "Direito congênito e tradicional sobre as terras imemoriais",
                        explanation = "O ENEM enfatiza que a Constituição de 1988 rompeu com a tutela integracionista anterior, reconhecendo aos povos originários sua organização social, costumes e direitos originários sobre as terras tradicionalmente ocupadas."
                    )
                ),
                goldTip = "Lembre-se: no ENEM, a Constituição de 1988 é o grande documento de referência para redação e para a prova de Ciências Humanas. Se a questão tratar de direitos e dignidade humana, a resposta dialoga com a Carta de 88!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "Estudo aprofundado do período republicano brasileiro.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Analise sempre os atores sociais envolvidos e as garantias constitucionais."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "hist_rep_01" -> listOf(
                MathExercise(
                    id = "hist_ex_rep_01_a",
                    title = "ENEM: Coronelismo e a Política dos Governadores",
                    problemStatement = "Na Primeira República brasileira (1889–1930), a sustentação do poder oligárquico apoiava-se na articulação entre o plano municipal e o governo federal. Essa dinâmica estruturava-se a partir de:",
                    options = listOf(
                        "Eleições secretas e auditadas pela Justiça Eleitoral, garantindo a alternância pacífica dos partidos de esquerda.",
                        "Controle do voto aberto pelos coronéis locais ('voto de cabresto'), integrado à troca de favores com as oligarquias estaduais e o presidente ('política dos governadores').",
                        "Supressão total das Câmaras Municipais e nomeação direta de generais do exército para governar todos os municípios cafeeiros.",
                        "Financiamento estatal obrigatório de comícios para incentivar o voto espontâneo dos trabalhadores rurais analfabetos.",
                        "Monopólio absoluto do Partido Republicano Gaúcho, que governou ininterruptamente de 1889 a 1930 sem a participação de São Paulo."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da rede de favores: o coronel coagia o eleitor no município, o governador elegia deputados federais leais, e o presidente protegia a oligarquia estadual.",
                    stepByStepSolution = "A alternativa B está correta. O sistema de poder da República Velha articulava o coronelismo na base municipal (onde o voto aberto permitia o 'voto de cabresto' e a perseguição a oposicionistas) com a 'política dos governadores' no plano federal concebida por Campos Sales. Esse arranjo garantia a estabilidade das oligarquias estaduais e o domínio alternado de São Paulo e Minas Gerais ('café com leite')."
                ),
                MathExercise(
                    id = "hist_ex_rep_01_b",
                    title = "ENEM: A Revolta da Vacina e o Higienismo",
                    problemStatement = "A Revolta da Vacina, ocorrida no Rio de Janeiro em 1904, não pode ser reduzida a um simples obscurantismo popular contra a ciência. Sob a ótica das tensões sociais urbanas da época, o levante foi provocado:",
                    options = listOf(
                        "Pela recusa da população de classe alta em aceitar o fim da monarquia absolutista e a proclamação da república.",
                        "Pela insatisfação generalizada contra as reformas urbanas autoritárias do 'bota-abaixo' de Pereira Passos, que demoliram cortiços e expulsaram as classes populares do centro para as favelas.",
                        "Pela imposição de vacinas vindas da França que exigiam a renúncia do direito de voto dos operários de fábrica.",
                        "Pelo apoio entusiasta dos revoltosos à invasão da marinha alemã na Baía de Guanabara.",
                        "Pela proibição do comércio de café no porto carioca imposta pelo sanitarista Oswaldo Cruz."
                    ),
                    correctIndex = 1,
                    hint = "A vacinação obrigatória foi a gota d'água em um contexto de demolição de moradias populares e higienismo autoritário que violentava a privacidade das famílias.",
                    stepByStepSolution = "A alternativa B está correta. A historiografia consolidada no ENEM compreende a Revolta da Vacina de 1904 como uma explosão social contra o autoritarismo e a exclusão da reforma urbana de Pereira Passos. As demolições em massa de cortiços e habitações coletivas no centro da capital ('bota-abaixo') empurraram a população pobre para os morros sem assistência. A invasão dos lares por agentes sanitários armados para a vacinação obrigatória funcionou como o estopim de uma revolta contra o desprezo do Estado republicano pelas camadas populares."
                )
            )

            "hist_rep_02" -> listOf(
                MathExercise(
                    id = "hist_ex_rep_02_a",
                    title = "ENEM: Cidadania Regulada e Corporativismo Varguista",
                    problemStatement = "A criação da Consolidação das Leis do Trabalho (CLT) em 1943 e a política sindical do governo Vargas são caracterizadas pelos sociólogos pela noção de 'cidadania regulada'. Esse conceito expressa o fato de que:",
                    options = listOf(
                        "Todos os brasileiros, mesmo sem profissão ou residência, tinham acesso automático a todos os direitos sociais e políticos.",
                        "O usufruto dos direitos sociais e previdenciários estava condicionado à vinculação formal a uma ocupação reconhecida pelo Estado e ao controle sindical ministerial.",
                        "Apenas os latifundiários e cafeicultores podiam filiar-se a sindicatos e negociar salários.",
                        "O Estado brasileiro abriu mão de intervir na economia para permitir a livre negociação capital-trabalho sem nenhuma lei estatal.",
                        "As greves foram incentivadas e legalizadas como principal instrumento de formulação de políticas econômicas pelo presidente."
                    ),
                    correctIndex = 1,
                    hint = "Para ter direitos varguistas, o trabalhador precisava ter 'carteira assinada' em profissão reconhecida, sob a fiscalização do Ministério do Trabalho.",
                    stepByStepSolution = "A alternativa B está correta. A 'cidadania regulada', formulada pelo sociólogo Wanderley Guilherme dos Santos, define o modelo varguista em que o acesso a direitos trabalhistas e aposentadoria dependia estritamente de o cidadão pertencer a uma categoria profissional legalmente reconhecida pelo Estado. Em troca das concessões trabalhistas da CLT, o Estado tutelava e atrelava os sindicatos ao Ministério do Trabalho, neutralizando mobilizações autônomas e proibindo greves."
                ),
                MathExercise(
                    id = "hist_ex_rep_02_b",
                    title = "ENEM: A Propaganda do Estado Novo e o DIP",
                    problemStatement = "Durante o Estado Novo (1937–1945), o Departamento de Imprensa e Propaganda (DIP) exerceu um papel determinante na legitimação do regime autoritário de Getúlio Vargas por meio de:",
                    options = listOf(
                        "Incentivo financeiro irrestrito à circulação de jornais comunistas e anarquistas que criticassem abertamente as leis trabalhistas.",
                        "Exercício da censura sistemática sobre os meios de comunicação aliada à propaganda oficial do culto à figura de Vargas como líder protetor e educador da nação.",
                        "Fechamento de todas as emissoras de rádio do país para evitar que o povo tivesse acesso a músicas e noticiários.",
                        "Defesa pública da anexação de territórios da Argentina e do Paraguai como missão sagrada do povo brasileiro.",
                        "Proibição do ensino da língua portuguesa nas escolas primárias para favorecer o dialeto regional gaúcho."
                    ),
                    correctIndex = 1,
                    hint = "O DIP combinava duas armas: a tesoura da censura prévia para calar opositores e a buzina da propaganda oficial ('Hora do Brasil') para promover o governante.",
                    stepByStepSolution = "A alternativa B está correta. O DIP funcionou como a máquina de controle ideológico do Estado Novo: censurava previamente notícias, letras de música e peças de teatro que destoassem da narrativa oficial e, ao mesmo tempo, produzia cartilhas infantis, programas radiofônicos ('A Hora do Brasil') e desfiles patrióticos que construíam a mística de Getúlio Vargas como o 'Pai dos Pobres' e arquiteto da nova identidade nacional."
                )
            )

            "hist_rep_03" -> listOf(
                MathExercise(
                    id = "hist_ex_rep_03_a",
                    title = "ENEM: As Reformas de Base e o Golpe de 1964",
                    problemStatement = "O governo de João Goulart (1961–1964) foi marcado pela radicalização do debate político em torno das 'Reformas de Base'. A reação dos setores conservadores que culminou no golpe civil-militar deveu-se principalmente ao fato de que essas reformas:",
                    options = listOf(
                        "Propunham a entrega de todas as reservas de petróleo brasileiras para empresas multinacionais petrolíferas dos Estados Unidos.",
                        "Visavam a alterações estruturais distributivas (reforma agrária, fiscal e educacional) que contrariavam os interesses do latifúndio tradicional e das elites empresariais.",
                        "Defendiam a invasão militar imediata de Cuba e a prisão de todos os estudantes universitários brasileiros.",
                        "Pretendiam transformar o Brasil em uma monarquia absolutista dinástica sob o comando da família real portuguesa.",
                        "Extinguiam todos os direitos trabalhistas conquistados na CLT para baratear a mão de obra industrial."
                    ),
                    correctIndex = 1,
                    hint = "Pense na palavra 'agrária': mexer na propriedade da terra no Brasil sempre foi o maior tabu para as elites dominantes.",
                    stepByStepSolution = "A alternativa B está correta. As Reformas de Base de Jango propunham desapropriações de terras improdutivas para reforma agrária, controle de remessa de lucros para o exterior, reforma tributária progressiva e extensão do direito de voto aos analfabetos. Essas propostas distributivas e democratizantes ameaçavam os privilégios da oligarquia agrária, de grandes empresários e de multinacionais, que se articularam com a cúpula militar e o governo norte-americano para derrubar o presidente legítimo em 1964."
                ),
                MathExercise(
                    id = "hist_ex_rep_03_b",
                    title = "ENEM: O Modelo Desenvolvimentista de JK",
                    problemStatement = "O Plano de Metas do presidente Juscelino Kubitschek (1956–1961), sintetizado no lema '50 anos em 5', promoveu uma acelerada modernização da economia brasileira. Uma das consequências estruturais dessa política desenvolvimentista foi:",
                    options = listOf(
                        "O abandono do transporte rodoviário em favor de uma gigantesca e moderna malha ferroviária que cobriu todo o território nacional.",
                        "A consolidação do modelo rodoviarista com a atração de multinacionais automotivas e o aumento das disparidades regionais e da inflação.",
                        "A proibição do ingresso de capitais estrangeiros e a estatização total de todos os bancos comerciais e indústrias leves.",
                        "A eliminação definitiva da dívida externa brasileira por meio do corte de todos os gastos em infraestrutura energética.",
                        "A fixação da sede administrativa do governo brasileiro na cidade de Salvador para priorizar a exportação de cana-de-açúcar."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da montagem da fábrica da Volkswagen no ABC e das grandes rodovias abertas para ligar o país a Brasília.",
                    stepByStepSolution = "A alternativa B está correta. O governo JK consolidou a opção pelo modelo rodoviário através da atração de montadoras estrangeiras de veículos e da construção de rodovias de integração nacional. Embora tenha modernizado o parque fabril e construído Brasília, essa estratégia gerou um pesado endividamento externo, disparou as taxas de inflação e aprofundou a dependência nacional do transporte por caminhões e do petróleo."
                )
            )

            "hist_rep_04" -> listOf(
                MathExercise(
                    id = "hist_ex_rep_04_a",
                    title = "ENEM: O Significado Autoritário do AI-5",
                    problemStatement = "O Ato Institucional nº 5 (AI-5), baixado em 13 de dezembro de 1968 durante o governo do general Costa e Silva, é considerado pelos historiadores o marco mais sombrio da Ditadura Civil-Militar brasileira porque:",
                    options = listOf(
                        "Decretou o fim imediato das Forças Armadas e transferiu o governo nacional para a Assembleia Geral da ONU.",
                        "Institucionalizou o terrorismo de Estado ao suspender o habeas corpus para crimes políticos, fechar o Congresso e instaurar a censura prévia irrestrita.",
                        "Convocou eleições presidenciais diretas imediatas com a participação de todos os partidos de oposição clandestinos.",
                        "Concedeu anistia ampla e irrestrita a todos os estudantes e operários presos durante os protestos de rua de 1968.",
                        "Proibiu a presença de qualquer militar em cargos da administração pública federal e estadual."
                    ),
                    correctIndex = 1,
                    hint = "Pense no que acontecia se você fosse preso sob o AI-5: não havia advogado ou juiz que pudesse pedir sua soltura via habeas corpus.",
                    stepByStepSolution = "A alternativa B está correta. O AI-5 conferiu poderes quase absolutos ao Poder Executivo militar, permitindo fechar o Congresso, cassar mandatos de opositores, demitir juízes, suspender a garantia de habeas corpus para acusados de crimes contra a segurança nacional e censurar previamente a imprensa e as artes. Na prática, abriu caminho para a atuação clandestina dos órgãos de repressão (DOI-CODI, OBAN), legitimando prisões ilegais, torturas sistemáticas e assassinatos políticos cometidos por agentes do Estado."
                ),
                MathExercise(
                    id = "hist_ex_rep_04_b",
                    title = "ENEM: O Milagre Econômico e a Distribuição de Renda",
                    problemStatement = "Durante os anos do chamado 'Milagre Econômico' (1969–1973), o Brasil registrou taxas recordes de crescimento do Produto Interno Bruto (PIB). No entanto, sob o ponto de vista social e da cidadania avaliado no ENEM, esse período caracterizou-se:",
                    options = listOf(
                        "Por uma expressiva redução da miséria e distribuição equitativa das riquezas entre todas as camadas sociais.",
                        "Pelo arrocho dos salários dos trabalhadores, expansão da dívida externa e drástico agravamento da concentração de renda no país.",
                        "Pela extinção de todos os bancos privados para transformar o crédito em um serviço comunal gerido por sindicatos operários.",
                        "Pela triplicação do poder de compra do salário mínimo e pela concessão de terras na Amazônia para indígenas sem desmatamento.",
                        "Por uma política de pleno emprego que erradicou o analfabetismo funcional nas capitais do Nordeste."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da metáfora do bolo: 'Fazer o bolo crescer para depois dividir'. Quem comeu o bolo foram as classes mais ricas.",
                    stepByStepSolution = "A alternativa B está correta. O crescimento acelerado do PIB no Milagre Econômico foi obtido às custas da repressão violenta aos sindicatos, do arrocho salarial imposto à classe trabalhadora e de pesados empréstimos internacionais. O resultado foi um dos momentos de maior aumento da desigualdade social e da concentração de renda da história brasileira, enquanto o custo de vida aumentava e os serviços públicos básicos de saúde e educação eram negligenciados."
                )
            )

            "hist_rep_05" -> listOf(
                MathExercise(
                    id = "hist_ex_rep_05_a",
                    title = "ENEM: A Constituição Cidadã de 1988 e os Direitos Sociais",
                    problemStatement = "A Constituição Federal de 1988, apelidada por Ulysses Guimarães de 'Constituição Cidadã', representou uma ruptura com a tradição autoritária brasileira ao consagrar:",
                    options = listOf(
                        "A restauração do voto censitário baseado na renda financeira e a subordinação das leis civis ao direito canônico católico.",
                        "A ampliação histórica dos direitos fundamentais, estabelecendo a saúde como dever do Estado (SUS), tipificando o racismo como crime inafiançável e reconhecendo os direitos originários dos povos indígenas e quilombolas.",
                        "A proibição do voto feminino e a criminalização de qualquer manifestação pública de trabalhadores grevistas.",
                        "A extinção do Ministério Público e a proibição da previdência social pública para trabalhadores do campo.",
                        "A concessão de poderes plenipotenciários ao Presidente da República para governar por meio de atos institucionais sem intervenção judicial."
                    ),
                    correctIndex = 1,
                    hint = "A Carta de 1988 é o pilar do Estado Democrático de Direito brasileiro contemporâneo e universalizou a saúde e a cidadania.",
                    stepByStepSolution = "A alternativa B está correta. A Constituição de 1988 ampliou substantivamente a cidadania e os direitos sociais no Brasil. Criou o Sistema Único de Saúde (SUS) universal e público, declarou o racismo crime inafiançável e imprescritível, proibiu a tortura, assegurou o direito de greve e a liberdade sindical, garantiu direitos territoriais ancestrais a indígenas e remanescentes de quilombos e instituiu o voto facultativo para jovens de 16 e 17 anos e analfabetos."
                ),
                MathExercise(
                    id = "hist_ex_rep_05_b",
                    title = "ENEM: A Luta pela Efetivação dos Direitos Contemporâneos",
                    problemStatement = "Apesar dos avanços formais e jurídicos trazidos pela Constituição de 1988 e pelos governos democráticos da Nova República, a realidade social brasileira ainda se depara com o desafio de:",
                    options = listOf(
                        "Superar a monarquia parlamentarista que ainda comanda as Forças Armadas a partir de Petrópolis.",
                        "Efetivar na prática cotidiana as garantias de dignidade humana, combatendo as históricas desigualdades socioespaciais, a violência policial seletiva e a lentidão na demarcação de terras e na reforma agrária.",
                        "Impedir que os cidadãos pobres tenham acesso gratuito ao ensino superior público e ao voto secreto.",
                        "Extinguir o uso de moedas fiduciárias em prol do retorno ao escambo de açúcar nos centros urbanos.",
                        "Restabelecer a censura prévia nos jornais para garantir a moralização da administração pública."
                    ),
                    correctIndex = 1,
                    hint = "Existe um abismo entre o texto da lei ('a lei no papel') e a vida concreta de milhões de brasileiros nas periferias e no campo.",
                    stepByStepSolution = "A alternativa B está correta. O ENEM frequentemente aborda o descompasso entre a igualdade jurídica formal prometida pela Constituição de 1988 e as contradições da realidade brasileira contemporânea. A luta dos movimentos sociais (indígenas, negros, camponeses, sem-teto e LGBTQIA+) concentra-se na concretização desses direitos no dia a dia, enfrentando o racismo estrutural, a concentração de terras, a violência do Estado e a precarização dos serviços essenciais de saúde, moradia e educação."
                )
            )

            else -> emptyList()
        }
    }
}
