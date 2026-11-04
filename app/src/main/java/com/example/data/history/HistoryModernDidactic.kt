package com.example.data.history

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático Aprofundado e Questões Analíticas no Padrão ENEM
 * Módulo 5: História Geral: Idade Moderna
 * - hist_mod_01: Renascimento Cultural e Científico
 * - hist_mod_02: Reformas Religiosas
 * - hist_mod_03: Absolutismo e Mercantilismo
 * - hist_mod_04: Expansão Marítima Europeia
 */
object HistoryModernDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "hist_mod_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Renascimento Cultural e Científico (Séculos XIV a XVI)

                    O Renascimento marcou a transição da mentalidade medieval para a modernidade ocidental. No ENEM, o movimento não é visto como uma recusa a Deus (os renascentistas eram profundamente religiosos), mas sim como uma reconfiguração do lugar do ser humano no cosmos e uma nova postura crítica diante da natureza.

                    ### 1. Pilares Filosóficos do Renascimento
                    • **Humanismo:** Valorização das potencialidades, da dignidade e da capacidade intelectual e criativa do ser humano, inspirado no resgate dos clássicos greco-romanos.
                    • **Antropocentrismo:** O ser humano como centro das reflexões e artífice de seu próprio destino, em oposição ao *Teocentrismo* medieval (onde todos os fenômenos eram explicados passivamente como desígnios inquestionáveis de Deus).
                    • **Racionalismo e Empirismo:** A verdade deve ser buscada pelo uso da razão crítica, da observação direta dos fenômenos naturais e da experimentação científica, e não pela cega submissão ao princípio de autoridade da Igreja.
                    • **Individualismo e Hedonismo:** Afirmação dos talentos individuais, da assinatura autoral das obras (rompendo o anonimato artesanal medieval) e da valorização dos prazeres terrenos legítimos.

                    ### 2. O Berço Italiano e o Mecenato
                    • Cidades como Florença, Veneza e Roma enriqueceram com o comércio mediterrâneo e abrigavam os **Mecenas** (burgueses ricos, banqueiros como a família Médici e papas que financiavam pintores, escultores e cientistas em busca de prestígio social).
                    • **Grandes Mestres:** Leonardo da Vinci (o homem universal polímata: pintura, anatomia, engenharia), Michelangelo (o teto da Capela Sistina, escultura de David), Rafael Sanzio e Botticelli.
                    • **Inovações Técnicas nas Artes:** A invenção da **perspectiva linear** (profundidade tridimensional geométrica), o chiaroscuro (jogo de luz e sombra) e o realismo anatômico minucioso.

                    ### 3. A Revolução Científica
                    • **Heliocentrismo:** Nicolau Copérnico e Galileu Galilei demonstraram cientificamente que o Sol é o centro do sistema e a Terra gira ao seu redor, demolindo o *Geocentrismo* ptolomaico defendido pelo dogma eclesiástico.
                    • **A Imprensa de Gutenberg (c. 1450):** O aperfeiçoamento dos tipos móveis permitiu a impressão em massa de livros, barateando a circulação de ideias e quebrando o monopólio católico dos monges copistas.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Antropocentrismo", "O ser humano no centro das investigações intelectuais e criativas", "Ruptura epistêmica com o teocentrismo medieval"),
                    FormulaOrSyntaxItem("Mecenato", "Financiamento de artistas por burgueses e papas em troca de prestígio", "Base material que viabilizou o Renascimento italiano"),
                    FormulaOrSyntaxItem("Heliocentrismo", "O Sol como centro orbital, provado por Copérnico e Galileu", "Marco fundador da física e astronomia modernas")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O 'Homem Vitruviano' de Da Vinci como Síntese",
                        codeOrFormula = "Homem inscrito no círculo e no quadrado: Razão, Proporção e Beleza",
                        explanation = "O ENEM analisa essa obra icônica de Da Vinci como o símbolo supremo do Humanismo renascentista: o corpo humano desenhado com proporções matemáticas precisas, unindo ciência, arte e centralidade cósmica."
                    )
                ),
                goldTip = "Cuidado com pegadinhas no ENEM: os renascentistas NÃO eram ateus! A grande maioria das obras de Da Vinci e Michelangelo retratava temas bíblicos (Pietà, Moisés, Criação de Adão), mas com uma humanização realista dos corpos divinos."
            )

            "hist_mod_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # As Reformas Religiosas: Protestantismo e a Contrarreforma Católica

                    No século XVI, a Igreja Católica romana perdeu o monopólio da fé na Europa Ocidental. O ENEM aborda a Reforma não apenas por disputas teológicas, mas pelas profundas transformações sociopolíticas e econômicas ligadas à ascensão burguesa e ao fortalecimento das monarquias nacionais.

                    ### 1. Causas e Crise Moral da Igreja Medieval
                    • **Venda de Indulgências:** Cobrança de dinheiro para conceder o perdão dos pecados e garantir a redução da pena da alma no Purgatório.
                    • **Simonia:** Venda descarada de relíquias sagradas falsificadas (pedaços de madeira supostamente da cruz de Cristo) e de cargos eclesiásticos (bispados para nobres ricos).
                    • **Nicolaísmo:** Descumprimento sistemático do celibato clerical por sacerdotes e papas.

                    ### 2. As Principais Correntes da Reforma Protestante
                    • **Luteranismo (Alemanha, 1517):** O monge Martinho Lutero afixou as *95 Teses* na porta da igreja de Wittenberg:
                      - *Salvação pela Fé (Sola Fide):* A salvação da alma é uma graça gratuita de Deus obtida unicamente pela fé pessoal sincera, e não por boas obras ou pagamento de indulgências.
                      - *Livre Interpretação da Bíblia (Sola Scriptura):* Tradução das Escrituras do latim para as línguas vernáculas (Lutero traduziu para o alemão), extinguindo a necessidade da intermediação sacerdotal e do Papa.
                      - Redução dos sacramentos a apenas dois bíblicos: Batismo e Eucaristia.
                    • **Calvinismo (Suíça):** João Calvino formulou a doutrina da **Predestinação Absoluta**:
                      - Deus já havia escolhido, desde o início dos tempos, quem seria salvo e quem seria condenado.
                      - *A Ética Protestante e o Espírito do Capitalismo (Max Weber):* O sucesso no trabalho árduo, na disciplina e na poupança material era visto como um sinal visível da graça divina, justificando teologicamente o lucro burguês (condenado como pecado de usura pela Igreja Católica).
                    • **Anglicanismo (Inglaterra, 1534):** Motivado por razões de Estado: o rei Henrique VIII rompeu com o Papa pelo *Ato de Supremacia*, fundou a Igreja Anglicana (da qual se tornou chefe político e espiritual) e confiscou as vastas terras da Igreja para a nobreza inglesa.

                    ### 3. A Contrarreforma ou Reforma Católica
                    Reunida no **Concílio de Trento (1545–1563)** para frear o avanço protestante:
                    • **Reafirmação Dogmática:** Manutenção dos sete sacramentos, do celibato clerical e da autoridade infalível do Papa.
                    • **Instrumentos de Combate:**
                      - *Companhia de Jesus (Jesuítas):* Ordem militarmente disciplinada fundada por Inácio de Loyola para expandir a fé católica através da catequese nas terras coloniais recém-conquistadas da América e Ásia.
                      - *Tribunal do Santo Ofício (Inquisição):* Perseguição implacável, tortura e condenação à fogueira (*auto de fé*) de hereges, judeus e cientistas.
                      - *Index Librorum Prohibitorum:* Lista oficial de livros de leitura proibida aos católicos (incluindo obras científicas de Galileu, Copérnico e filósofos iluministas).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Justificação pela Fé", "A salvação é um dom divino individual, não se compra com indulgências", "Pilar teológico de Lutero nas 95 Teses"),
                    FormulaOrSyntaxItem("Predestinação Calvinista", "O trabalho diligente e o sucesso material vistos como sinal da graça divina", "Base ética do capitalismo burguês moderno segundo Max Weber"),
                    FormulaOrSyntaxItem("Concílio de Trento", "Reafirmação dos dogmas católicos + Inquisição + Jesuítas na América", "Estratégia de contenção da Contrarreforma Católica")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Calvino e a Burguesia Mercantil",
                        codeOrFormula = "Igreja Católica (Usura é Pecado) vs Calvinismo (O Lucro é Louvável)",
                        explanation = "O ENEM frequentemente aborda como o Calvinismo se difundiu com velocidade entre os mercadores e banqueiros da Holanda, Inglaterra (puritanos) e França (huguenotes), porque eliminou a culpa moral do lucro e do juro inerentes à atividade comercial."
                    )
                ),
                goldTip = "Lembre-se da conexão com a História do Brasil: a vinda dos Jesuítas (Padre Manuel da Nóbrega, José de Anchieta) para catequizar os indígenas foi consequência direta da Contrarreforma do Concílio de Trento."
            )

            "hist_mod_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Absolutismo Monárquico e as Práticas do Mercantilismo

                    Na Idade Moderna europeia (séculos XVI a XVIII), a consolidação dos Estados Nacionais gerou uma aliança estratégica entre o rei e a burguesia mercantil, culminando na concentração de poderes na figura monárquica e na política mercantilista.

                    ### 1. O Absolutismo Monárquico
                    Sistema político em que o soberano exercia o controle centralizado das leis, da arrecadação de impostos, da justiça e do monopólio da violência legítima (exército nacional permanente):
                    • **O Auge Francês:** Luís XIV, o "Rei Sol" (*'L'État, c'est moi'* — o Estado sou eu), governando do monumental Palácio de Versalhes, onde a nobreza foi domesticada e mantida sob pensões e cortesias reais.
                    • **Teorias de Legitimação do Poder Absoluto:**
                      - *Nicolau Maquiavel (O Príncipe):* A política desvinculada da moral religiosa tradicional. A razão de Estado está acima de qualquer escrúpulo; para manter a estabilidade do poder, "os fins justificam os meios" e "é melhor ser temido do que amado".
                      - *Thomas Hobbes (Leviatã):* No estado de natureza primitivo, o homem é o lobo do próprio homem (*homo homini lupus*), vivendo em guerra de todos contra todos. Para sobreviver, os indivíduos firmam um pacto social irrevogável entregando sua liberdade a um soberano absolutista (o Leviatã) em troca de paz e segurança civil.
                      - *Jacques Bossuet (Teoria do Direito Divino dos Reis):* O rei é o representante direto de Deus na Terra; rebelar-se contra o monarca é cometer pecado mortal contra o Criador.

                    ### 2. O Mercantilismo: A Prática Econômica da Idade Moderna
                    Conjunto de práticas econômicas estatais com o objetivo primordial de fortalecer as finanças da monarquia absolutista:
                    • **Metalismo (Bulionismo):** A riqueza de um Estado era medida pela quantidade de ouro e prata acumulada em seus cofres (adotado intensamente pela Espanha nas minas de Potosí).
                    • **Balança Comercial Favorável:** Exportar mais mercadorias (receber ouro) do que importar (evitar a saída de moeda).
                    • **Protecionismo Alfandegário:** Aplicação de pesadas taxas sobre a entrada de produtos manufaturados estrangeiros para proteger a produção interna.
                    • **Colbertismo / Manufaturas:** Política francesa de estímulo estatal à produção de artigos manufaturados de luxo de alto valor agregado (tapeçarias, perfumes, cristais).
                    • **O Sistema Colonial (Pacto Colonial):** As colônias na América funcionavam como áreas de exploração exclusivas: só podiam fornecer matérias-primas baratas à metrópole e eram obrigadas a consumir os produtos manufaturados metropolitanos a preços monopolistas.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Balança Comercial Favorável", "Exportações > Importações = Entrada líquida de metais preciosos", "Princípio regulador supremo do mercantilismo absolutista"),
                    FormulaOrSyntaxItem("Direito Divino dos Reis", "O poder monárquico emana de Deus e não do consentimento popular", "Teoria de Bossuet para justificar a obediência incondicional"),
                    FormulaOrSyntaxItem("Leviatã de Hobbes", "Pacto social para escapar da anarquia e da guerra de todos contra todos", "Justificativa contratualista do absolutismo")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Aliança Rei-Burguesia no Estado Absolutista",
                        codeOrFormula = "Rei unifica pesos, medidas e moeda <-> Burguesia paga tributos e financia o exército",
                        explanation = "O ENEM destaca o caráter pragmático do Absolutismo: o rei eliminava os pedágios feudais e padronizava a moeda favorecendo os negócios burgueses; em contrapartida, os impostos burgueses pagavam o exército profissional que subjugava os nobres rebeldes."
                    )
                ),
                goldTip = "O Absolutismo NÃO significava que o monarca podia fazer literalmente tudo sem regras: ele estava contido pelos costumes, pelos privilégios feudais da nobreza e pela necessidade de arrecadar tributos da burguesia."
            )

            "hist_mod_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # A Expansão Marítima Europeia e a Conquista dos Mares

                    Nos séculos XV e XVI, os países da Península Ibérica inauguraram a era das Grandes Navegações, reconfigurando a geopolítica mundial e integrando pela primeira vez a economia do globo no chamado "sistema-mundo moderno".

                    ### 1. Fatores do Pioneirismo Português
                    Portugal foi o primeiro país europeu a lançar-se no Atlântico desconhecido ("Mar Tenebroso") devido à conjunção pioneira de fatores:
                    • **Precocidade da Centralização Monárquica:** A Revolução de Avis (1385) consolidou uma monarquia nacional centralizada aliada à burguesia mercantil mercantilista séculos antes de Espanha, França ou Inglaterra.
                    • **Posição Geográfica Estratégica:** Extensa costa voltada para o Oceano Atlântico e proximidade com as correntes marítimas favoráveis.
                    • **Desenvolvimento Técnico:** Escola de Sagres (infante D. Henrique), desenvolvimento da caravela de velas latinas triangulares (que permitia navegar contra o vento "bolinando"), aperfeiçoamento da bússola, astrolábio e da cartografia.
                    • **A Queda de Constantinopla (1453):** A tomada da cidade pelo Império Turco-Otomano bloqueou a rota terrestre tradicional para o Oriente, forçando os europeus a buscarem um caminho alternativo para as ricas especiarias da Índia contornando a África (*Périplo Africano*).

                    ### 2. Os Dois Projetos Ibéricos de Navegação
                    • **A Rota Portuguesa (Périplo Africano):** Navegação costeira ao redor do continente africano: Ceuta (1415), Cabo das Tormentas/Boa Esperança (Bartolomeu Dias, 1488) e chegada a Calicute na Índia (Vasco da Gama, 1498).
                    • **A Rota Espanhola (Circunavegação):** Cristóvão Colombo propôs alcançar o Oriente navegando em linha reta para o Ocidente, partindo da premissa da esfericidade da Terra. Desembarcou nas Bahamas em 1492, acreditando ter chegado às Índias.

                    ### 3. A Partilha do Mundo e o Choque Colonial
                    • **Tratado de Tordesilhas (1494):** Acordo diplomático intermediado pelo papa que dividiu o mundo não europeu entre Portugal e Espanha através de um meridiano a 370 léguas a oeste das ilhas de Cabo Verde.
                    • **O Encontro e a Destruição:** A chegada dos conquistadores espanhóis (Hernán Cortés no Império Asteca e Francisco Pizarro no Império Inca) combinou superioridade bélica (armas de fogo, cavalos, armaduras de aço), alianças com tribos rivais e, fundamentalmente, o choque epidemiológico de vírus letais.
                    • **A Riqueza e a 'Revolução dos Preços':** A extração de toneladas de prata em Potosí (Bolívia) e Zacatecas (México) gerou uma inflação galopante na Europa, enriquecendo mercadores e empobrecendo trabalhadores assalariados.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Périplo Africano", "Estratégia portuguesa de contornar a África para chegar à Índia", "Concluiu-se com Vasco da Gama em 1498"),
                    FormulaOrSyntaxItem("Tratado de Tordesilhas (1494)", "Divisão diplomática das terras ultramarinas entre Portugal e Espanha", "Assinado 6 anos antes da chegada de Cabral ao Brasil"),
                    FormulaOrSyntaxItem("Revolução dos Preços", "Inflação na Europa provocada pela enxurrada de prata e ouro das Américas", "Consequência econômica global do saque aos impérios pré-colombianos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Sentido da Colonização Americana",
                        codeOrFormula = "Colônias de Povoamento (EUA do Norte) vs Colônias de Exploração (América Latina)",
                        explanation = "O ENEM discute a tese clássica de Caio Prado Jr.: a colonização do Brasil e da América espanhola foi desenhada para atender às necessidades mercantis metropolitanas (monocultura, mineração, escravidão e drenagem de riquezas para a Europa)."
                    )
                ),
                goldTip = "O bloqueio otomano em 1453 é a grande virada: as especiarias (cravo, canela, noz-moscada, pimenta) não eram capricho, mas essenciais para conservar a carne e mascarar o sabor de alimentos deteriorados no inverno europeu."
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "Estudo de História Geral: Idade Moderna.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Relacione o nascimento da modernidade com a ascensão da burguesia e as navegações."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "hist_mod_01" -> listOf(
                MathExercise(
                    id = "hist_ex_mod_01_a",
                    title = "ENEM: Antropocentrismo e o Pensamento Renascentista",
                    problemStatement = "O Renascimento cultural e científico dos séculos XV e XVI operou uma profunda inflexão na história do pensamento ocidental. Essa inflexão caracterizou-se pela:",
                    options = listOf(
                        "Proclamação do ateísmo universal e pela destruição violenta de todos os templos e imagens católicas da Itália.",
                        "Substituição da matriz exclusivamente teocêntrica medieval pelo antropocentrismo humanista, valorizando a capacidade da razão e da observação empírica para decifrar a natureza.",
                        "Rejeição completa de qualquer texto ou escultura da Antiguidade Clássica greco-romana.",
                        "Imposição de uma visão puramente rural que proibia o comércio monetário e a vida urbana nas cidades portuárias.",
                        "Defesa intransigente do modelo geocêntrico de Ptolomeu como única verdade científica autorizada pelos sábios humanistas."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se do Humanismo: o ser humano passa a ser visto como dotado de razão, inteligência e criatividade para investigar o mundo.",
                    stepByStepSolution = "A alternativa B está correta. O Renascimento não significou uma negação da religiosidade, mas uma ruptura com o teocentrismo estéril medieval. Ao colocar o ser humano no centro das investigações (antropocentrismo humanista) e resgatar a herança clássica greco-romana, os renascentistas valorizaram o racionalismo, a experimentação e a observação da natureza, abrindo as portas para a Revolução Científica moderna (Copérnico, Galileu, Da Vinci)."
                ),
                MathExercise(
                    id = "hist_ex_mod_01_b",
                    title = "ENEM: A Revolução da Imprensa de Gutenberg",
                    problemStatement = "A invenção e o aperfeiçoamento da prensa de tipos móveis por Johannes Gutenberg por volta de 1450 gerou impactos socioculturais revolucionários na Europa porque:",
                    options = listOf(
                        "Proibiu terminantemente a publicação de qualquer livro que não estivesse escrito em latim eclesiástico.",
                        "Permitiu a reprodução em escala inédita de obras impressas, democratizando a circulação de novas ideias científicas e teológicas e enfraquecendo o monopólio clerical sobre a cultura letrada.",
                        "Elevou em dez vezes o custo financeiro dos livros, impedindo que a burguesia mercantil tivesse acesso a compêndios cartográficos.",
                        "Substituiu a leitura de textos escritos por rituais tribais transmitidos unicamente por via oral.",
                        "Foi prontamente proibida por todos os monarcas absolutistas da Europa até o século XIX."
                    ),
                    correctIndex = 1,
                    hint = "Antes de Gutenberg, monges levavam meses copiando um único livro à mão. Com a prensa, centenas de bíblias e tratados científicos podiam ser impressos rapidamente.",
                    stepByStepSolution = "A alternativa B está correta. A tecnologia da prensa de tipos móveis de Gutenberg revolucionou a comunicação e a circulação do saber: barateou a produção de livros, acelerou a difusão dos clássicos humanistas renascentistas e, crucialmente, permitiu que a Bíblia traduzida para as línguas locais por Lutero fosse lida por milhares de fiéis, quebrando o monopólio milenar que a Igreja Católica exercia sobre a cópia e a interpretação dos textos."
                )
            )

            "hist_mod_02" -> listOf(
                MathExercise(
                    id = "hist_ex_mod_02_a",
                    title = "ENEM: Luteranismo e o Rompimento Teológico",
                    problemStatement = "Em 1517, ao publicar as 95 Teses contra a venda de indulgências pela Igreja Católica, Martinho Lutero inaugurou a Reforma Protestante, cujo núcleo doutrinário estabelecia:",
                    options = listOf(
                        "A necessidade de que o Papa tivesse poder imperial sobre todos os reis da Europa e cobrasse dízimos em dobro.",
                        "A doutrina da justificação pela fé, pela qual a salvação da alma é uma graça divina individual que prescinde da compra de indulgências e da mediação sacerdotal romana.",
                        "A obrigação de que todos os fiéis realizassem peregrinações anuais a Roma para serem batizados pessoalmente pelo Papa.",
                        "A extinção do Cristianismo e o retorno ao politeísmo clássico grego de Zeus e Atena.",
                        "A proibição do casamento dos pastores protestantes e a obrigatoriedade da reclusão de todos os fiéis em mosteiros fechados."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se dos dois grandes pilares de Lutero: Sola Fide (só a fé salva) e Sola Scriptura (só a Bíblia como autoridade de fé).",
                    stepByStepSolution = "A alternativa B está correta. A ruptura de Martinho Lutero contestou a mercantilização da fé (venda de indulgências para financiar a Basílica de São Pedro). Lutero sustentava a tese da 'justificação pela fé': o fiel é salvo pela graça divina mediante sua fé sincera, e não por pagamentos pecuniários à Igreja ou por ritos eclesiásticos. Ao pregar a livre interpretação da Bíblia e traduzi-la para a língua do povo, retirou do clero católico o monopólio da salvação."
                ),
                MathExercise(
                    id = "hist_ex_mod_02_b",
                    title = "ENEM: A Ética Calvinista e o Espírito Capitalista",
                    problemStatement = "Na clássica obra 'A Ética Protestante e o Espírito do Capitalismo', o sociólogo Max Weber analisa como a teologia calvinista da Predestinação Absoluta favoreceu a expansão do capitalismo moderno. Segundo essa análise, isso ocorreu porque o calvinismo:",
                    options = listOf(
                        "Proibiu o trabalho assalariado e determinou que os lucros comerciais fossem queimados em fogueiras públicas.",
                        "Interpretava a disciplina, o trabalho árduo e o enriquecimento lícito como sinais visíveis da bênção e da eleição divina, legitimando moralmente a acumulação e o reinvestimento burguês.",
                        "Defendia que a preguiça e a mendicância eram virtudes sagradas que garantiam o paraíso imediato aos homens de negócios.",
                        "Exigia a devolução de todas as propriedades privadas à Coroa católica de Portugal.",
                        "Restabeleceu a condenação medieval da usura e do lucro com penas de prisão perpétua para comerciantes de tecidos."
                    ),
                    correctIndex = 1,
                    hint = "Para Calvino, o sucesso material honesto não era pecado (como dizia o catolicismo medieval), mas a confirmação de que Deus abençoou seu trabalho diário.",
                    stepByStepSolution = "A alternativa B está correta. O calvinismo operou uma revolução ética fundamental: ao pregar que a salvação era predestinada por Deus e que a dedicação metódica ao trabalho e a prosperidade material sem ostentação eram indícios da graça divina, retirou o estigma de pecado do lucro e do empréstimo a juros. Essa moral do dever profissional (ascese laica) casou-se perfeitamente com os interesses de poupança e reinvestimento produtivo da burguesia mercantil europeia."
                )
            )

            "hist_mod_03" -> listOf(
                MathExercise(
                    id = "hist_ex_mod_03_a",
                    title = "ENEM: O Mercantilismo e a Balança Comercial",
                    problemStatement = "O mercantilismo caracterizou a política econômica das monarquias absolutistas na Europa Moderna. O princípio basilar dessa política, voltado para assegurar a riqueza e a soberania do Estado, consistia em:",
                    options = listOf(
                        "Estimular as importações de tecidos ingleses para desvalorizar a moeda nacional.",
                        "Manter uma balança comercial favorável (exportar mais e importar menos) para reter metais preciosos (ouro e prata) dentro do reino.",
                        "Extinguir todas as taxas alfandegárias para promover o livre-comércio mundial desregulado.",
                        "Proibir o comércio das metrópoles com suas possessões coloniais na América.",
                        "Distribuir a totalidade das reservas de ouro do tesouro real aos camponeses pobres das aldeias."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da fórmula: Exportações > Importações = superávit comercial e acúmulo de metais nos cofres do rei (metalismo).",
                    stepByStepSolution = "A alternativa B está correta. A lógica do mercantilismo partia da premissa do metalismo: a força de uma monarquia absolutista media-se pela quantidade de metais preciosos (ouro e prata) retidos em seus cofres. Para conseguir esse acúmulo, o Estado adotava o protecionismo alfandegário e buscava manter uma balança comercial favorável (vendendo produtos manufaturados caros e comprando apenas o indispensável), apoiando-se no exclusivo do pacto colonial."
                ),
                MathExercise(
                    id = "hist_ex_mod_03_b",
                    title = "ENEM: Teóricos do Absolutismo e a Justificação do Poder",
                    problemStatement = "Na Inglaterra do século XVII, Thomas Hobbes publicou o 'Leviatã', justificando a existência de um poder monárquico absolutista centralizado. Segundo a teoria contratualista de Hobbes, a legitimidade do soberano repousa no fato de que:",
                    options = listOf(
                        "O rei é um anjo divino sem corpo carnal que desceu do céu para governar sem necessitar de súditos.",
                        "Os indivíduos, vivendo em um estado de natureza de medo constante e guerra de todos contra todos, renunciaram à sua liberdade irrestrita através de um pacto social para que um governante forte garantisse a ordem e a sobrevivência coletiva.",
                        "O povo tem o direito constitucional de destituir e enforcar o rei toda vez que houver aumento de impostos sobre o gado.",
                        "As leis devem ser votadas diretamente por plebiscito popular semanal sem a existência de nenhum chefe de Estado.",
                        "O poder político emana do comando exclusivo dos bispos católicos de Roma."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da célebre frase: 'O homem é o lobo do próprio homem'. Sem um poder forte para impor a lei, a humanidade se autodestrói.",
                    stepByStepSolution = "A alternativa B está correta. Thomas Hobbes fundamenta o absolutismo em uma perspectiva laica e contratualista: no estado de natureza primitivo, sem autoridade constituída, prevalecia o egoísmo, o medo violento e a guerra civil permanente ('o homem é o lobo do homem'). Para escapar dessa barbárie autodestrutiva, os seres humanos celebram um contrato social irrevogável, transferindo o monopólio da força ao Estado/monarca (o Leviatã), que impõe a ordem, a paz e a segurança jurídica."
                )
            )

            "hist_mod_04" -> listOf(
                MathExercise(
                    id = "hist_ex_mod_04_a",
                    title = "ENEM: O Pioneirismo Português nas Navegações",
                    problemStatement = "Portugal foi a primeira nação europeia a empreender com sucesso as Grandes Navegações no Oceano Atlântico ao longo do século XV. O fator fundamental que explica esse pioneirismo lusitano foi:",
                    options = listOf(
                        "A abundância de jazidas de prata encontradas nas margens do Rio Tejo em Lisboa.",
                        "A precoce centralização monárquica consolidada com a Revolução de Avis (1385), articulando os interesses da Coroa com a experiência e os capitais da burguesia mercantil lusa.",
                        "A vitória militar sobre a Inglaterra na Guerra dos Cem Anos, que obrigou os britânicos a cederem suas frotas a Portugal.",
                        "A renúncia voluntária do Império Otomano em cobrar qualquer pedágio sobre o comércio de especiarias no mar Mediterrâneo.",
                        "A crença unânime entre os sábios renascentistas de que a Terra era plana e quadrada."
                    ),
                    correctIndex = 1,
                    hint = "Enquanto Espanha, França e Inglaterra guerreavam ou enfrentavam disputas dinásticas internas, Portugal já tinha um Estado unificado e focado no mar desde o século XIV.",
                    stepByStepSolution = "A alternativa B está correta. O pioneirismo português decorreu de uma combinação favorável de fatores: posição geográfica atlantica, paz interna e conhecimento náutico (Escola de Sagres, bússola, caravelas). O fator político decisivo foi a Revolução de Avis (1385), que unificou precocemente o Estado Nacional em torno do rei D. João I, forjando uma aliança indissolúvel entre a Coroa e os mercadores lisboetas em busca de novas rotas mercantis e lucros ultramarinos."
                ),
                MathExercise(
                    id = "hist_ex_mod_04_b",
                    title = "ENEM: A Conquista da América e o Choque Colonial",
                    problemStatement = "A conquista dos grandes impérios pré-colombianos (Astecas e Incas) pelos espanhóis no início do século XVI resultou na rápida subjugação de civilizações que contavam com exércitos de centenas de milhares de guerreiros. O fator decisivo para a vitória dos conquistadores europeus foi:",
                    options = listOf(
                        "A superioridade numérica esmagadora dos soldados espanhóis em relação à população nativa americana.",
                        "A combinação de alianças militares com povos indígenas subjugados pelos impérios locais, a superioridade de armamentos e o impacto demográfico devastador das epidemias trazidas da Europa.",
                        "A recusa pacífica dos imperadores Montezuma e Atahualpa em utilizar qualquer tipo de arma em confrontos armados.",
                        "A conversão voluntária e imediata de todos os guerreiros astecas ao luteranismo alemão antes das batalhas.",
                        "O uso de frotas de submarinos modernos que bombardearam as pirâmides de Machu Picchu e Tenochtitlán."
                    ),
                    correctIndex = 1,
                    hint = "Poucos espanhóis conquistaram impérios gigantescos porque exploraram rivalidades étnicas locais e contaram com o auxílio letal de vírus como a varíola.",
                    stepByStepSolution = "A alternativa B está correta. A queda de impérios complexos como o Asteca e o Inca não ocorreu por magia ou heroísmo isolado: Hernán Cortés e Francisco Pizarro exploraram com astúcia as divisões e rivalidades étnicas locais, aliando-se a povos subjugados pelos astecas que queriam se libertar dos tributos imperiais. Isso foi potencializado pelo terror das armas de fogo, dos cavalos e, sobretudo, pelo choque biológico das epidemias (como a varíola e o sarampo), que causaram hecatombes demográficas dizimando as estruturas militares e políticas nativas."
                )
            )

            else -> emptyList()
        }
    }
}
