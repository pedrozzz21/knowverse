package com.example.data.history

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático Aprofundado e Questões Analíticas no Padrão ENEM
 * Módulo 6: História Geral: Idade Contemporânea
 * - hist_cont_01: Iluminismo e a Era das Revoluções
 * - hist_cont_02: Revoluções Industriais e Ideologias Operárias
 * - hist_cont_03: Imperialismo e Neocolonialismo no Século XIX
 * - hist_cont_04: As Guerras Mundiais e os Totalitarismos
 * - hist_cont_05: Guerra Fria e Descolonização
 */
object HistoryContemporaryDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "hist_cont_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Iluminismo e a Era das Revoluções (Século XVIII)

                    O Iluminismo ("Século das Luzes") demoliu as bases ideológicas do Antigo Regime (Absolutismo, Mercantilismo e Privilégios Feudais do Clero e da Nobreza), inaugurando a era dos direitos civis e das democracias representativas liberais.

                    ### 1. Os Grandes Filósofos Iluministas
                    • **John Locke:** Pai do Liberalismo Político. Defendia que os homens possuem **direitos naturais inalienáveis** (Vida, Liberdade e Propriedade). O governo existe para proteger esses direitos; se falhar, o povo tem o direito legítimo de rebelião.
                    • **Montesquieu (O Espírito das Leis):** Formulação da **Divisão dos Três Poderes** (Executivo, Legislativo e Judiciário). Para evitar a tirania absolutista, "só o poder freia o poder" (*sistema de freios e contrapesos*).
                    • **Voltaire:** Campeão intransigente da **liberdade de expressão**, tolerância religiosa e crítica contundente ao fanatismo da Igreja Católica.
                    • **Jean-Jacques Rousseau (O Contrato Social):** Diferenciava-se dos demais liberais por defender a **Democracia Popular** e a soberania do povo (*vontade geral*). Apontou a invenção da propriedade privada como a raiz histórica das desigualdades.
                    • **Adam Smith (A Riqueza das Nações):** Liberalismo Econômico. Crítica ao protecionismo mercantilista; a economia é guiada por leis naturais de oferta e procura (*mão invisível do mercado*); o Estado deve adotar o princípio do *laissez-faire* (deixar fazer, deixar passar).

                    ### 2. A Independência dos Estados Unidos (1776)
                    • Primeiro movimento a concretizar na prática as ideias iluministas de Locke contra a tirania colonial inglesa (Lei do Selo, Lei do Chá e Leis Intoleráveis).
                    • **Declaração de 4 de Julho de 1776:** Redigida por Thomas Jefferson, afirmando a igualdade natural e a busca da felicidade.
                    • **A Contradição Estrutural:** Proclamou a liberdade e a República mantendo intocada a **escravidão negra** no Sul e o massacre dos povos indígenas no avanço para o Oeste.

                    ### 3. A Revolução Francesa (1789)
                    Marco divisor que encerrou a Idade Moderna e iniciou a Idade Contemporânea:
                    • **Sociedade de Ordens:** Primeiro Estado (Clero) e Segundo Estado (Nobreza) isentos de impostos e detentores de terras; Terceiro Estado (Burguesia, operários urbanos/sans-culottes e camponeses — 98% da população) pagava todos os impostos.
                    • **Queda da Bastilha (14 de julho de 1789):** Símbolo da destruição do absolutismo popular.
                    • **Declaração dos Direitos do Homem e do Cidadão:** Consagrou a tríade *Liberdade, Igualdade e Fraternidade*.
                    • **Fases:** Assembleia Nacional (1789-1792), Convenção Nacional/Fase Jacobina do Terror com Robespierre (1792-1794, abolição da escravidão nas colônias, voto universal masculino, tabelamento de preços), Reação Termidoriana (Girondinos ricos) e o Golpe do 18 Brumário de Napoleão Bonaparte (1799).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Separação dos Poderes", "Executivo, Legislativo e Judiciário independentes e harmônicos", "Teoria de Montesquieu contra a tirania monárquica"),
                    FormulaOrSyntaxItem("Laissez-Faire", "Não intervenção do Estado na economia e livre concorrência", "Princípio fundante do liberalismo econômico de Adam Smith"),
                    FormulaOrSyntaxItem("Declaração de 1789", "Igualdade jurídica formal perante a lei para todos os homens", "Demoliu os privilégios de sangue da nobreza feudal")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Contradição Universal dos Direitos Humanos em 1789",
                        codeOrFormula = "Direitos do Homem vs Exclusão das Mulheres e Escravizados",
                        explanation = "O ENEM discute a contestação de Olympe de Gouges (autora da 'Declaração dos Direitos da Mulher e da Cidadã' em 1791, guilhotinada pelos revolucionários) e a Revolução Haitiana de 1791, que levou os princípios da liberdade francesa às últimas consequências ao abolir a escravidão pelas armas."
                    )
                ),
                goldTip = "No ENEM: A Revolução Francesa instituiu a igualdade JURÍDICA ('todos são iguais perante a lei'), mas NÃO a igualdade ECONÔMICA (a burguesia assumiu o controle do Estado e consagrou o direito sagrado à propriedade privada)."
            )

            "hist_cont_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # As Revoluções Industriais e as Novas Ideologias Trabalhistas

                    A transição da manufatura artesanal para a maquinofatura no século XVIII transformou radicalmente a relação do ser humano com a natureza, com o tempo e com o trabalho, gerando a moderna polarização entre burguesia industrial e proletariado.

                    ### 1. A Primeira Revolução Industrial (Inglaterra, c. 1760)
                    • **Pioneirismo Inglês:** Acumulação primitiva de capitais pelo comércio marítimo colonial; reservas abundantes de **carvão mineral e ferro**; marinha mercante hegemônica; e a **Lei dos Cercamentos dos Campos (Enclosure Acts)**, que expulsou os camponeses da terra e forneceu uma massa de mão de obra barata e faminta para as fábricas urbanas (Manchester, Londres).
                    • **A Máquina a Vapor (James Watt) e o Tear Mecânico:** Substituição da força humana e animal pela energia do vapor alimentado por carvão, multiplicando a produtividade.

                    ### 2. A Realidade Desumana do Proletariado
                    • Jornadas de trabalho extenuantes de 14 a 16 horas diárias em galpões insalubres e poluídos.
                    • Exploração massiva do **trabalho infantil e feminino**, remunerados com salários até 70% inferiores aos dos homens adultos.
                    • Inexistência de qualquer direito trabalhista (sem descanso semanal, férias, aposentadoria ou proteção contra mutilações em maquinários).

                    ### 3. As Reações Operárias e o Surgimento das Novas Ideologias
                    • **Ludismo (Os Quebradores de Máquinas):** Movimento de rebelião espontânea liderado pelo mítico Ned Ludd que destruía as máquinas industriais, vistas como responsáveis diretas pelo desemprego e pela miséria salarial.
                    • **Cartismo (Inglaterra, 1838):** Movimento que enviou ao parlamento britânico a *Carta do Povo*, exigindo reformas políticas: sufrágio universal masculino e voto secreto, compreendendo que as leis só mudariam com deputados operários.
                    • **Socialismo Científico (Karl Marx e Friedrich Engels):**
                      - *Materialismo Histórico:* O motor da história é a luta de classes (senhor x escravo, nobre x servo, burguês x proletário).
                      - *Mais-Valia:* A riqueza burguesa origina-se da apropriação do valor excedente produzido pelo trabalhador e não remunerado no salário.
                      - *Revolução Proletária:* A classe trabalhadora deve tomar o Estado pela via revolucionária, instaurar a ditadura do proletariado e caminhar para a sociedade sem classes e sem Estado (Comunismo).
                    • **Anarquismo (Bakunin e Kropotkin):** Defendia a destruição imediata de toda forma de autoridade e hierarquia (Estado, Igreja, Exército, Capital), sem passar por nenhuma fase de transição estatal.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Lei dos Cercamentos", "Privatização das terras comunais gerando êxodo rural forçado", "Criou o exército de mão de obra de reserva para a indústria britânica"),
                    FormulaOrSyntaxItem("Mais-Valia", "Diferença entre o valor gerado pelo trabalhador e o salário pago pelo burguês", "Conceito marxista central que explica a acumulação capitalista"),
                    FormulaOrSyntaxItem("Ludismo vs Cartismo", "Destruição física de máquinas (Ludismo) vs Luta política institucional (Cartismo)", "Evolução da consciência de classe do operariado no século XIX")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Disciplina Fabril e o Tempo do Relógio",
                        codeOrFormula = "Do tempo da natureza (sol/chuva) para o tempo mecânico do relógio fabril",
                        explanation = "O historiador E.P. Thompson, muito cobrado no ENEM, demonstra que a fábrica impôs uma nova disciplina temporal: o tempo virou dinheiro (time is money), e o atraso de minutos passou a ser punido com cortes salariais."
                    )
                ),
                goldTip = "Diferencie Socialismo Utópico (Fourier, Owen: acreditavam que a burguesia se conscientizaria espontaneamente da igualdade) do Socialismo Científico (Marx: revolução e luta de classes fundamentadas na economia)."
            )

            "hist_cont_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Imperialismo e Neocolonialismo no Século XIX

                    Na esteira da Segunda Revolução Industrial (aço, eletricidade, petróleo e química), as grandes potências capitalistas europeias partilharam violentamente a África e subjugaram a Ásia para alimentar sua produção fabril.

                    ### 1. Causas Econômicas da Corrida Imperialista
                    • **Busca de Mercados Consumidores:** A superprodução industrial europeia exigia novos mercados cativos fora do continente.
                    • **Fontes de Matérias-Primas Estratégicas:** Necessidade voraz de borracha, petróleo, cobre, algodão e minérios preciosos.
                    • **Exportação de Capitais Excedentes:** Os bancos e trustes monopolistas precisavam investir capitais na construção de ferrovias e portos em territórios ultramarinos.

                    ### 2. A Justificativa Ideológica: O Racismo Científico
                    Para mascarar a pilhagem econômica e a violência armada, as potências forjaram discursos pseudocientíficos:
                    • **Darwinismo Social (Herbert Spencer):** A apropriação distorcida da teoria da evolução das espécies de Darwin para afirmar que a "raça branca caucasiana" era biologicamente superior e mais evoluída que negros e asiáticos.
                    • **O 'Fardo do Homem Branco' (Rudyard Kipling):** O dever civilizatório altruísta do europeu de levar a ciência, a fé cristã e a tecnologia aos povos supostamente "selvagens, infantis e bárbaros".

                    ### 3. A Partilha da África e a Dominação da Ásia
                    • **Conferência de Berlim (1884–1885):** Convocada por Otto von Bismarck, retalhou o mapa do continente africano com réguas sobre mesas diplomáticas europeias, criando **fronteiras artificiais** que reuniram etnias historicamente inimigas no mesmo território e dividiram comunidades ancestrais.
                    • **O Genocídio no Congo:** O rei Leopoldo II da Bélgica transformou o Congo em sua propriedade privada de extração de borracha e marfim, provocando a morte e mutilação de mais de 10 milhões de congoleses.
                    • **Guerra do Ópio (1839–1860, China):** A marinha britânica impôs militarmente o comércio de ópio (droga alucinógena que destruía a população chinesa), forçando a assinatura dos "Tratados Desiguais" (Tratado de Nanquim: cessão de Hong Kong à Inglaterra e abertura de portos).
                    • **Guerra dos Cipaios (1857, Índia):** Revolta de soldados indianos contra o domínio colonial da Coroa britânica (que transformou a Índia na "Jóia da Coroa").
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Fronteiras Artificiais", "Partilha arbitrária de povos e línguas na Conferência de Berlim", "Raiz histórica das guerras civis e conflitos étnicos da África contemporânea"),
                    FormulaOrSyntaxItem("Darwinismo Social", "Uso ideológico fraudulento da biologia para justificar a supremacia racial", "Base de sustentação teórica do racismo colonial e do nazismo"),
                    FormulaOrSyntaxItem("Tratado de Nanquim", "Abertura forçada da China ao comércio britânico após a Guerra do Ópio", "Início do 'Século de Humilhação' chinês")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Impacto das Fronteiras Artificiais na África Contemporânea",
                        codeOrFormula = "Genocídio de Ruanda (Hutus x Tutsis) e a herança da divisão colonial",
                        explanation = "O ENEM cobra recorrentemente como as potências coloniais (Bélgica, França, Inglaterra) utilizavam a estratégia de 'dividir para governar', privilegiando determinadas etnias para governar os outros povos locais, plantando o ódio que explodiu em massacres no século XX."
                    )
                ),
                goldTip = "Diferencie o Colonialismo dos séculos XVI/XVII (Pacto Colonial na América, metalismo, mercantilismo) do Neocolonialismo do século XIX (África e Ásia, capitalismo monopolista, industrialização pesada)."
            )

            "hist_cont_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # As Guerras Mundiais e os Regimes Totalitários (1914–1945)

                    A primeira metade do século XX foi marcada pela barbárie industrializada: duas guerras mundiais devastadoras, a quebra da economia liberal em 1929 e a ascensão de ditaduras totalitárias fascistas que culminaram no Holocausto.

                    ### 1. A Primeira Guerra Mundial (1914–1918)
                    • **Causas Profundas:** Rivalidades imperialistas da Partilha da África e Ásia; nacionalismos exacerbados (Pan-eslavismo e Revanchismo Francês pela perda da Alsácia-Lorena para a Alemanha); e a política de **Paz Armada e Alianças Secretas** (Tríplice Aliança: Alemanha, Áustria-Hungria e Itália vs. Tríplice Entente: Inglaterra, França e Rússia).
                    • **Guerra de Trincheiras:** O uso de metralhadoras, gases venenosos (mostarda), tanques e aviões transformou a guerra em uma carnificina estática e desumanizadora.
                    • **O Tratado de Versalhes (1919):** Impôs condições humilhantes e vingativas à Alemanha derrotada (perda de colônias, pagamento de reparações astronômicas e desmilitarização), gerando o ressentimento nacionalista que abriu caminho para o nazismo.

                    ### 2. A Revolução Russa de 1917 e a Crise de 1929
                    • **Revolução Russa (1917):** Derrubou a autocracia czarista e levou os Bolcheviques de Lênin e Trotsky ao poder sob o lema "Paz, Pão e Terra", criando a União Soviética (URSS), primeiro Estado socialista da história.
                    • **A Quebra da Bolsa de Nova York (1929):** Crise de superprodução do capitalismo liberal americano; falência de bancos, desemprego em massa global e falência da tese liberal da autorregulação dos mercados. Superada nos EUA através do **New Deal** de Franklin Roosevelt (intervenção do Estado em obras públicas e seguridade social, inspirado em Keynes).

                    ### 3. A Ascensão do Nazifascismo e a Segunda Guerra Mundial (1939–1945)
                    • **Características do Totalitarismo Fascista (Itália de Mussolini e Alemanha de Hitler):**
                      - Nacionalismo chauvinista fanático e militarismo expansionista (*Espaço Vital / Lebensraum*).
                      - Culto messiânico à figura do líder incontestável (*Duce / Führer*).
                      - Partido único, censura violenta e terrorismo policial (Gestapo, SS).
                      - **Antissemitismo de Estado e Racismo Eugênico:** A ideologia de uma suposta 'raça ariana' pura que culminou na **Solução Final** — o Holocausto, assassinato industrializado e planejado de 6 milhões de judeus, além de ciganos, homossexuais, comunistas e pessoas com deficiência em campos de extermínio como Auschwitz.
                    • **O Desfecho da Guerra:** A Batalha de Stalingrado (1943) marcou a virada contra o exército nazista pelas tropas soviéticas; o Dia D na Normandia (1944) libertou a Europa Ocidental; e o bombardeio atômico norte-americano sobre Hiroshima e Nagasaki em agosto de 1945 inaugurou a era nuclear.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Tratado de Versalhes (1919)", "Paz punitiva que humilhou a Alemanha e alimentou o revanchismo nazista", "Principal elo causal entre a Primeira e a Segunda Guerra"),
                    FormulaOrSyntaxItem("New Deal", "Intervenção estatal de obras públicas contra a Grande Depressão de 1929", "Início do Estado de Bem-Estar Social capitalista (Keynesianismo)"),
                    FormulaOrSyntaxItem("Holocausto (Shoah)", "Extermínio industrializado e burocrático de 6 milhões de judeus pelo Nazismo", "Maior crime contra a humanidade do século XX")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Conceito de 'Banalidade do Mal' de Hannah Arendt",
                        codeOrFormula = "Burocratas executando ordens genocidas sem questionamento crítico",
                        explanation = "O ENEM explora a análise da filósofa Hannah Arendt sobre o julgamento de Adolf Eichmann: os carrascos nazistas muitas vezes não eram monstros sádicos isolados, mas burocratas comuns cumpridores de ordens em um sistema totalitário que anulou a empatia e o pensamento moral."
                    )
                ),
                goldTip = "Fique atento à contradição da crise de 1929: nos EUA havia montanhas de alimentos apodrecendo em celeiros enquanto multidões passavam fome nas ruas por falta de dinheiro para comprar."
            )

            "hist_cont_05" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Guerra Fria e os Processos de Descolonização Afro-Asiática

                    A ordem mundial pós-1945 caracterizou-se pela **bipolaridade ideológica e geopolítica** entre duas superpotências armadas com arsenais nucleares: os Estados Unidos (líder do bloco capitalista) e a União Soviética (líder do bloco socialista).

                    ### 1. A Dinâmica da Guerra Fria (1947–1991)
                    Conflito sem confronto militar direto entre as superpotências ("Fria"), devido à certeza de destruição mútua assegurada por armas termonucleares, mas com disputas violentas em guerras por procuração (*proxy wars*):
                    • **Doutrina Truman e Plano Marshall (EUA):** Contenção do comunismo e financiamento bilionário da reconstrução da Europa Ocidental capitalista.
                    • **Alianças Militares:** OTAN (liderada pelos EUA) versus Pacto de Varsóvia (liderado pela URSS).
                    • **A Corrida Espacial e Tecnológica:** Lançamento do satélite soviético Sputnik (1957), primeiro homem no espaço (Yuri Gagarin, 1961) e chegada norte-americana à Lua na missão Apollo 11 (1969).
                    • **Principais Conflitos Periféricos:**
                      - *Guerra da Coreia (1950–1953):* Divisão da península no Paralelo 38 (Coreia do Norte comunista e Coreia do Sul capitalista).
                      - *Crise dos Mísseis em Cuba (1962):* O momento em que o planeta esteve a minutos de uma guerra nuclear aberta.
                      - *Guerra do Vietnã (1959–1975):* A humilhante derrota militar norte-americana diante da guerrilha comunista dos vietcongues, impulsionada pelo movimento pacifista de contracultura (Maio de 68 e festival de Woodstock).

                    ### 2. A Descolonização da África e da Ásia
                    O enfraquecimento financeiro e militar das potências coloniais europeias após a Segunda Guerra Mundial abriu caminho para os movimentos de libertação nacional afro-asiáticos:
                    • **A Conferência de Bandung (Indonésia, 1955):** Reunião histórica de líderes de países recém-independentes da África e Ásia (como Nehru da Índia e Nasser do Egito) proclamando a condenação do imperialismo e fundando o **Movimento dos Países Não Alinhados** ("Terceiro Mundo"), recusando o alinhamento cego a Washington ou Moscou.
                    • **Caminhos da Independência:**
                      - *Via Pacífica e Desobediência Civil:* A Índia de Mahatma Gandhi (resistência não violenta, greves de fome e a Marcha do Sal em 1947).
                      - *Via Revolucionária Armada e Sangrenta:* A Guerra de Libertação da Argélia contra a França (1954–1962) e as guerras de independência das colônias portuguesas (Angola, Moçambique e Guiné-Bissau nos anos 1970).
                    • **O Fim da Guerra Fria (1989–1991):** As reformas de Mikhail Gorbachev na URSS (*Glasnost* — transparência política; e *Perestroika* — reestruturação econômica), a Queda do Muro de Berlim (1989) e a dissolução formal da União Soviética em 1991 consagraram a transição para a multipolaridade contemporânea.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Bipolaridade", "Mundo dividido entre o bloco capitalista (EUA) e socialista (URSS)", "Eixo geopolítico global entre 1947 e 1991"),
                    FormulaOrSyntaxItem("Conferência de Bandung (1955)", "Nascimento do Terceiro Mundo e do Não Alinhamento ativo", "Marco anticolonial fundamental nas provas de Humanas do ENEM"),
                    FormulaOrSyntaxItem("Queda do Muro de Berlim (1989)", "Símbolo máximo da derrocada do bloco socialista no Leste Europeu", "Encerramento simbólico da Guerra Fria")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Desobediência Civil de Gandhi na Índia",
                        codeOrFormula = "Satyagraha (Força da Verdade e Não Violência Ativa)",
                        explanation = "O ENEM analisa como a estratégia de boicote aos tecidos ingleses e a produção autônoma de sal mobilizaram milhões de indianos, deslegitimando a autoridade moral e econômica do Império Britânico sem o recurso às armas de fogo."
                    )
                ),
                goldTip = "O termo 'Terceiro Mundo' surgiu em Bandung em 1955: não era sinônimo de país pobre, mas dos países que recusavam pertencer ao Primeiro Mundo (capitalista) ou ao Segundo Mundo (socialista soviético)!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "Estudo de História Geral: Idade Contemporânea.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Analise as transformações estruturais da geopolítica mundial e das lutas sociais."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "hist_cont_01" -> listOf(
                MathExercise(
                    id = "hist_ex_cont_01_a",
                    title = "ENEM: A Teoria dos Três Poderes de Montesquieu",
                    problemStatement = "Na obra 'O Espírito das Leis' (1748), o filósofo iluminista Montesquieu propôs a célebre separação dos poderes do Estado. A finalidade política dessa formulação era:",
                    options = listOf(
                        "Fortalecer a autoridade absoluta do rei sobre a Igreja para permitir a cobrança irrestrita de tributos sobre a nobreza.",
                        "Garantir a liberdade civil e evitar o despotismo através de um sistema de freios e contrapesos em que o poder controle o próprio poder.",
                        "Subordinar os juízes civis aos tribunais eclesiásticos da Santa Inquisição católica em todas as causas penais.",
                        "Extinguir os parlamentos nacionais e transferir a soberania das leis para os sindicatos operários.",
                        "Restaurar a democracia direta ateniense com a proibição de qualquer eleição representativa."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da célebre máxima: 'Para que não se possa abusar do poder, é preciso que, pela disposição das coisas, o poder freie o poder'.",
                    stepByStepSolution = "A alternativa B está correta. Montesquieu visava desmontar o modelo absolutista, no qual o monarca concentrava em suas mãos as atribuições de fazer a lei (Legislativo), aplicá-la (Executivo) e julgar seus opositores (Judiciário). Ao propor três esferas autônomas e harmônicas com prerrogativas mútuas de fiscalização (freios e contrapesos), estabeleceu a base jurídica que protege a sociedade contra governantes autoritários."
                ),
                MathExercise(
                    id = "hist_ex_cont_01_b",
                    title = "ENEM: O Significado da Declaração dos Direitos de 1789",
                    problemStatement = "Aprovada pela Assembleia Nacional francesa em agosto de 1789, a Declaração dos Direitos do Homem e do Cidadão estabeleceu princípios universais que abalaram as estruturas do Antigo Regime ao consagrar:",
                    options = listOf(
                        "O direito hereditário da nobreza de nascer isenta de impostos e de ocupar com exclusividade os postos de comando militar.",
                        "A igualdade jurídica de todos os homens perante a lei, a liberdade de opinião e a consagração da propriedade como um direito inviolável e sagrado.",
                        "A obrigatoriedade de que todos os cidadãos franceses se convertessem ao protestantismo calvinista.",
                        "A abolição total de qualquer forma de propriedade privada e a estatização das oficinas artesanais de Paris.",
                        "A dissolução do exército nacional e a entrega da cidade de Paris aos reis da Áustria e da Prússia."
                    ),
                    correctIndex = 1,
                    hint = "A Declaração de 1789 destruiu os privilégios medievais de nascimento: a lei passou a ser igual para todos, independentemente de ser nobre ou plebeu.",
                    stepByStepSolution = "A alternativa B está correta. A Declaração de 1789 foi o manifesto supremo da vitória burguesa na Revolução Francesa. Ao proclamar que 'os homens nascem e permanecem livres e iguais em direitos', sepultou a sociedade de ordens do feudalismo absolutista, consolidou as liberdades individuais e garantiu o direito à propriedade privada como base do novo ordenamento jurídico ocidental."
                )
            )

            "hist_cont_02" -> listOf(
                MathExercise(
                    id = "hist_ex_cont_02_a",
                    title = "ENEM: As Condições de Trabalho na Revolução Industrial",
                    problemStatement = "Durante as primeiras décadas da Revolução Industrial na Inglaterra, o cotidiano das fábricas de tecidos e das minas de carvão caracterizou-se:",
                    options = listOf(
                        "Por jornadas reduzidas de seis horas diárias e ampla concessão de férias remuneradas custeadas pelos proprietários de tear.",
                        "Por jornadas extenuantes que superavam catorze horas diárias em ambientes insalubres, acompanhadas pela exploração maciça do trabalho infantil e feminino com salários rebaixados.",
                        "Pelo monopólio do trabalho concedido exclusivamente a nobres arruinados que recebiam aposentadoria integral da Coroa britânica.",
                        "Pela distribuição paritária das ações das companhias industriais entre patrões e operários através de assembleias fabris.",
                        "Pela proibição do uso de máquinas a vapor para preservar a saúde e a integridade física dos trabalhadores rurais."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se dos relatos de crianças de 8 anos limpando engrenagens de teares em movimento e de mulheres trabalhando 16 horas sem direitos.",
                    stepByStepSolution = "A alternativa B está correta. A fase pioneira da industrialização capitalista operou em um vácuo de regulamentação estatal. A burguesia impunha jornadas brutais de 14 a 16 horas sem intervalos de descanso, recorrendo intensivamente ao trabalho de crianças e mulheres devido à sua docilidade de disciplina e para pagar salários de miséria, resultando em mutilações frequentes e expectativa de vida operária que mal ultrapassava os 20 anos de idade em cidades industriais como Manchester."
                ),
                MathExercise(
                    id = "hist_ex_cont_02_b",
                    title = "ENEM: A Crítica de Marx e o Conceito de Mais-Valia",
                    problemStatement = "No século XIX, Karl Marx e Friedrich Engels formularam o 'Socialismo Científico', apresentando uma crítica radical à economia política liberal. Na análise marxista, o conceito de 'mais-valia' refere-se:",
                    options = listOf(
                        "Ao imposto cobrado pelo rei sobre a venda de grãos nas feiras medievais de Champanhe.",
                        "À parcela do valor do trabalho executado pelo operário que não é remunerada pelo salário e é apropriada privadamente pelo proprietário dos meios de produção como lucro.",
                        "Ao bônus de produtividade pago em dinheiro aos artesãos mais eficientes das corporações de ofício.",
                        "À perda financeira sofrida pelos capitalistas quando as máquinas industriais quebram por desgaste mecânico.",
                        "À doação voluntária feita pelos sindicatos para a compra de matérias-primas importadas das colônias."
                    ),
                    correctIndex = 1,
                    hint = "O operário trabalha 8 horas, mas seu salário equivale a apenas 2 horas do que ele produziu. As 6 horas restantes vão para o bolso do patrão.",
                    stepByStepSolution = "A alternativa B está correta. A teoria da mais-valia é o alicerce da crítica econômica de Marx: demonstra que a fonte do lucro burguês não nasce da simples revenda de mercadorias no mercado, mas da exploração direta do proletariado no processo produtivo. O trabalhador gera um montante de valor muito superior à quantia que recebe na forma de salário; essa diferença (a mais-valia) é retida pelo capitalista, alimentando a acumulação e aprofundando a desigualdade entre as classes sociais."
                )
            )

            "hist_cont_03" -> listOf(
                MathExercise(
                    id = "hist_ex_cont_03_a",
                    title = "ENEM: A Conferência de Berlim e as Fronteiras Artificiais",
                    problemStatement = "A Conferência de Berlim (1884–1885), realizada na Alemanha com a presença exclusiva de potências imperialistas europeias e dos Estados Unidos, produziu consequências históricas devastadoras para o continente africano ao determinar:",
                    options = listOf(
                        "A independência imediata de todas as nações africanas com ajuda financeira da rainha Vitória.",
                        "A partilha arbitrária do território africano em fronteiras artificiais desenhadas para atender aos interesses de exploração colonial, desrespeitando identidades étnicas e culturais ancestrais.",
                        "A proibição definitiva da extração de ouro e diamantes na África do Sul e no Congo belga.",
                        "A devolução pacífica de todas as colônias aos reinos tradicionais da África Ocidental sob supervisão do Vaticano.",
                        "A unificação de toda a África em uma única república federativa chefiada por generais etíopes."
                    ),
                    correctIndex = 1,
                    hint = "Diplomatas europeus traçaram linhas retas no mapa da África com régua e caneta, forçando povos rivais a viverem juntos e separando famílias.",
                    stepByStepSolution = "A alternativa B está correta. A Conferência de Berlim consagrou o princípio da ocupação efetiva da África pelas potências neocoloniais. O retalhamento do continente traçou fronteiras artificiais sem qualquer consulta aos povos originários, agrupando nações historicamente hostis sob a mesma autoridade colonial e dividindo etnias com laços ancestrais profundos. Essa herança perversa gerou guerras civis sangrentas e instabilidade crônica que se estendem até a África contemporânea."
                ),
                MathExercise(
                    id = "hist_ex_cont_03_b",
                    title = "ENEM: O Darwinismo Social como Legitimador do Imperialismo",
                    problemStatement = "Para justificar a conquista e a espoliação violenta da África e da Ásia no século XIX, ideólogos europeus formularam teorias como o 'Darwinismo Social'. Esse discurso ideológico sustentava que:",
                    options = listOf(
                        "Todas as culturas humanas possuem o mesmo valor moral e devem ser preservadas do contato com a tecnologia moderna.",
                        "A civilização branca ocidental representava o ápice da evolução biológica e social, possuindo o 'dever natural' de colonizar e civilizar os povos considerados inferiores e atrasados.",
                        "A Europa deveria submeter-se à liderança militar e religiosa dos imperadores chineses da Dinastia Qing.",
                        "O uso de armas de fogo contra qualquer ser humano constituía um pecado mortal que impedia a salvação da alma.",
                        "A miscigenação entre todas as raças era a única garantia de progresso e inteligência para as futuras gerações."
                    ),
                    correctIndex = 1,
                    hint = "Pense no conceito de 'O Fardo do Homem Branco': a ideia racista de que os brancos tinham a obrigação de 'civilizar' os povos negros e indígenas.",
                    stepByStepSolution = "A alternativa B está correta. O Darwinismo Social de Herbert Spencer foi uma apropriação pseudocientífica da biologia de Darwin aplicada à sociologia. Defendia que a vitória imperialista das nações europeias era a comprovação da 'sobrevivência do mais apto' e da superioridade inata da raça branca. Essa falácia eugênica conferiu uma roupagem de missão humanitária e civilizatória ('o fardo do homem branco') ao massacre militar e à pilhagem sistemática de matérias-primas e vidas na África e Ásia."
                )
            )

            "hist_cont_04" -> listOf(
                MathExercise(
                    id = "hist_ex_cont_04_a",
                    title = "ENEM: O Tratado de Versalhes e a Germinação do Nazismo",
                    problemStatement = "O Tratado de Versalhes (1919), imposto à Alemanha ao término da Primeira Guerra Mundial, é considerado pelos historiadores um dos fatores determinantes para a ascensão do Partido Nazista na década de 1930 porque:",
                    options = listOf(
                        "Premiou a Alemanha com territórios coloniais abundantes no Pacífico e empréstimos a juros nulos dos Estados Unidos.",
                        "Impôs cláusulas de humilhação e pesadas indenizações econômicas que destruíram a economia alemã, alimentando o sentimento de revanchismo chauvinista explorado por Adolf Hitler.",
                        "Proibiu a circulação de jornais e obrigou todos os generais alemães a se tornarem operários industriais na França.",
                        "Extinguiu a propriedade privada e instaurou uma ditadura comunista sob a liderança direta de Leon Trotsky em Berlim.",
                        "Exigiu que todos os cidadãos alemães adotassem o catolicismo romano como religião compulsória de Estado."
                    ),
                    correctIndex = 1,
                    hint = "A chamada 'paz punitiva': a Alemanha perdeu terras, foi obrigada a desarmar seu exército e a pagar quantias astronômicas, gerando ódio e orgulho ferido.",
                    stepByStepSolution = "A alternativa B está correta. O Tratado de Versalhes impôs à Alemanha a 'cláusula de culpa da guerra' (Artigo 231), confiscando suas colônias, retirando a Alsácia-Lorena, desmilitarizando a Renânia e exigindo reparações financeiras impagáveis. A crise socioeconômica decorrente dessa punição gerou humilhação nacional e hiperinflação, terreno fértil que foi habilmente explorado pela propaganda nazista de Hitler para canalizar o rancor popular contra a democracia liberal de Weimar e os judeus."
                ),
                MathExercise(
                    id = "hist_ex_cont_04_b",
                    title = "ENEM: A Singularidade do Holocausto no Nazismo",
                    problemStatement = "O Holocausto (Shoah), perpetrado pelo regime nazista durante a Segunda Guerra Mundial e que resultou no assassinato de seis milhões de judeus, é compreendido pela filosofia e história contemporâneas como um marco inédito da barbárie moderna porque:",
                    options = listOf(
                        "Foi fruto exclusivo de combates militares acidentais ocorridos no front russo durante os invernos rigorosos.",
                        "Mobilizou todo o aparato burocrático, tecnológico e industrial do Estado para planejar e executar o extermínio sistemático de um grupo humano com base em preceitos racistas.",
                        "Ocorreu sem o conhecimento ou a autorização de Adolf Hitler e da cúpula da SS em Berlim.",
                        "Foi liderado pela Cruz Vermelha Internacional como método de combate a epidemias de cólera nos campos de refugiados.",
                        "Atingiu exclusivamente generais ricos que possuíam fábricas de aviões e tanques na Alemanha."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se dos trens pontuais, das câmaras de gás, da contabilidade macabra e da cumplicidade da burocracia estatal: o genocídio como linha de produção industrial.",
                    stepByStepSolution = "A alternativa B está correta. A singularidade histórica do Holocausto reside no fato de que o extermínio de seres humanos foi planejado e executado com frieza burocrática e tecnologia fabril moderna ('a Solução Final'). Médicos, engenheiros, operadores de trens, contadores e administradores de Estado foram mobilizados pelo regime nazista para classificar, transportar e assassinar milhões de judeus, ciganos e dissidentes em escala industrial em câmaras de gás (Auschwitz, Treblinka), revelando os perigos da razão instrumental despida de ética humana."
                )
            )

            "hist_cont_05" -> listOf(
                MathExercise(
                    id = "hist_ex_cont_05_a",
                    title = "ENEM: A Bipolaridade da Guerra Fria e as Guerras por Procuração",
                    problemStatement = "A ordem geopolítica da Guerra Fria (1947–1991) caracterizou-se pela rivalidade ideológica e estratégica entre os Estados Unidos e a União Soviética. Embora nunca tenha ocorrido um confronto bélico direto entre as duas potências nucleares, essa rivalidade manifestou-se:",
                    options = listOf(
                        "Na paz absoluta em todos os continentes e na dissolução espontânea de todos os exércitos nacionais da Ásia e da América Latina.",
                        "Em intensos conflitos regionais por procuração (como as Guerras da Coreia e do Vietnã), na corrida armamentista e espacial e no apoio a ditaduras alinhadas.",
                        "Na renúncia de ambas as superpotências em produzir armas atômicas em prol de acordos ecológicos de preservação da floresta amazônica.",
                        "Na unificação de Washington e Moscou sob uma única constituição republicana presidida pelo secretário-geral da ONU.",
                        "No boicote conjunto de EUA e URSS contra o comércio de petróleo dos países do Oriente Médio."
                    ),
                    correctIndex = 1,
                    hint = "A guerra foi 'fria' apenas entre Washington e Moscou por medo da bomba atômica; na periferia (Coreia, Vietnã, América Latina), ela foi quente e sangrenta.",
                    stepByStepSolution = "A alternativa B está correta. O equilíbrio do terror nuclear (destruição mútua assegurada) impediu que Estados Unidos e União Soviética se enfrentassem diretamente. Contudo, a Guerra Fria foi pontuada por sangrentos conflitos periféricos ('guerras por procuração'), nos quais cada superpotência financiava e armava facções rivais (como na Guerra da Coreia, Guerra do Vietnã e guerras civis africanas), combinados à espionagem (CIA e KGB), à corrida espacial e ao suporte ativo a golpes militares na América Latina."
                ),
                MathExercise(
                    id = "hist_ex_cont_05_b",
                    title = "ENEM: A Conferência de Bandung e o Movimento Não Alinhado",
                    problemStatement = "Realizada em 1955 na Indonésia, a Conferência de Bandung reuniu representantes de 29 nações recém-independentes da Ásia e da África. A importância histórica dessa conferência consistiu em:",
                    options = listOf(
                        "Subordinar formalmente todos os países participantes à liderança militar e financeira do Pacto de Varsóvia soviético.",
                        "Consolidar a condenação formal ao colonialismo e ao racismo, inaugurando o conceito de 'Terceiro Mundo' e a política do Não Alinhamento ativo diante da bipolaridade da Guerra Fria.",
                        "Exigir a recolonização imediata do continente africano por frotas navais britânicas e portuguesas.",
                        "Declarar guerra nuclear contra a França e os Estados Unidos para forçar a exportação obrigatória de arroz.",
                        "Aprovar a criação de uma monarquia imperial absolutista unificada entre a Índia e o Egito."
                    ),
                    correctIndex = 1,
                    hint = "Bandung foi o grito do Sul Global: os países da África e Ásia disseram 'não' ao imperialismo e recusaram ser peões no jogo entre EUA e URSS.",
                    stepByStepSolution = "A alternativa B está correta. A Conferência de Bandung (1955) foi o grande marco diplomático e político da descolonização afro-asiática. Seus líderes (como Nehru da Índia, Sukarno da Indonésia e Zhou Enlai da China) repudiaram o racismo colonialista e afirmaram a soberania dos povos que acabavam de conquistar sua liberdade. Ao formularem o princípio do 'Não Alinhamento' ativo, recusaram a subordinação automática a qualquer dos blocos hegemônicos da Guerra Fria, forjando a identidade política do chamado 'Terceiro Mundo'."
                )
            )

            else -> emptyList()
        }
    }
}
