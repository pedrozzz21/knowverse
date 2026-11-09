package com.example.data.humanities

import com.example.data.assessment.BankQuestion
import com.example.data.assessment.LessonRef
import com.example.data.assessment.QDifficulty
import com.example.data.assessment.Question
import com.example.data.assessment.QuestionFormat
import com.example.data.assessment.QuestionSource
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado
import com.example.data.model.MathExercise

/**
 * Currículo oficial e banco didático de Filosofia e Sociologia para Ciências Humanas e suas Tecnologias (ENEM Dia 1).
 * Cobre integralmente a Matriz de Referência do INEP/MEC:
 * - Filosofia: Filosofia Antiga e Medieval, Teoria do Conhecimento (Moderna), Filosofia Política e Ética, Filosofia Contemporânea.
 * - Sociologia: Clássicos da Sociologia (Durkheim, Weber, Marx), Cultura e Indústria Cultural, Cidadania, Estado e Movimentos Sociais, Mundo do Trabalho e Desigualdades.
 */
object EnemPhilosophySociologyCurriculum {

    private val philosophyRegex = Regex(
        "\\b(filosof\\w*|s[oó]crates|plat[aã]o|arist[oó]teles|descartes|kant|locke|hobbes|rousseau|nietzsche|habermas|agostinho|tomas de aquino|aquino|maquiavel|espinosa|spinoza|hume|schopenhauer|sartre|arendt|adorno|horkheimer|foucault|epistemolog\\w*|metaf[ií]sic\\w*|empirism\\w*|racionalism\\w*|contratualism\\w*|[eé]tica|imperativo categ[oó]rico|mito da caverna|mai[eê]utica|sofista\\w*|estoicism\\w*|ceticism\\w*|escol[aá]stica|patr[ií]stica|fenomenologia|existencialismo)\\b",
        RegexOption.IGNORE_CASE
    )

    private val sociologyRegex = Regex(
        "\\b(sociolog\\w*|durkheim|max weber|weber|karl marx|marx|auguste comte|comte|bauman|bourdieu|giddens|fato social|a[cç][aã]o social|mais-valia|luta de classes|cidadania|movimentos? socia\\w*|estratifica[cç][aã]o social|ind[uú]stria cultural|patrim[oô]nio imaterial|identidade cultural|diversidade cultural|etnocentrismo|relativismo cultural|coes[aã]o social|anomia|modernidade l[ií]quida|habitus|viol[eê]ncia simb[oó]lica)\\b",
        RegexOption.IGNORE_CASE
    )

    fun classifyHumanitiesSubject(statement: String, topic: String, subtopic: String): String? {
        val combined = "$topic $subtopic $statement"
        return when {
            philosophyRegex.containsMatchIn(combined) -> "Filosofia"
            sociologyRegex.containsMatchIn(combined) -> "Sociologia"
            else -> null
        }
    }

    fun getPhilosophyModules(): List<EnemTrailModule> = listOf(
        EnemTrailModule(
            id = "mod_fil_01_antiga",
            moduleNumber = 1,
            title = "Módulo 1: Filosofia Antiga e Medieval",
            summary = "Pré-Socráticos, Sócrates, Platão, Aristóteles, Helenismo e Filosofia Medieval",
            lessons = listOf(
                EnemTrailLesson("fil_ant_01", "mod_fil_01_antiga", 1, "Do Mito ao Logos e os Pré-Socráticos", "Cosmologia, Physis e Arché (Tales, Heráclito e Parmênides)"),
                EnemTrailLesson("fil_ant_02", "mod_fil_01_antiga", 2, "Sócrates, os Sofistas e a Maiêutica", "Antropologia filosófica, retórica sofística e ironia socrática"),
                EnemTrailLesson("fil_ant_03", "mod_fil_01_antiga", 3, "Platão: Mundo das Ideias e Política", "Dualismo ontológico, Alegoria da Caverna e A República"),
                EnemTrailLesson("fil_ant_04", "mod_fil_01_antiga", 4, "Aristóteles: Metafísica, Lógica e Ética", "Ato e potência, quatro causas, justa medida (eudaimonia) e animal político"),
                EnemTrailLesson("fil_ant_05", "mod_fil_01_antiga", 5, "Filosofia Helenística e Medieval", "Estoicismo, Epicurismo, Ceticismo, Patrística (Santo Agostinho) e Escolástica (Tomás de Aquino)")
            ),
            finalSimulado = EnemTrailSimulado(id = "sim_fil_01", moduleId = "mod_fil_01_antiga")
        ),
        EnemTrailModule(
            id = "mod_fil_02_moderna",
            moduleNumber = 2,
            title = "Módulo 2: Filosofia Moderna e Teoria do Conhecimento",
            summary = "Racionalismo, Empirismo, Criticismo Kantiano e Ciência Moderna",
            lessons = listOf(
                EnemTrailLesson("fil_mod_01", "mod_fil_02_moderna", 1, "Racionalismo Cartesiano", "René Descartes, dúvida metódica, cogito e ideias inatas"),
                EnemTrailLesson("fil_mod_02", "mod_fil_02_moderna", 2, "Empirismo Britânico", "Francis Bacon, John Locke (tábula rasa) e David Hume (hábito e causalidade)"),
                EnemTrailLesson("fil_mod_03", "mod_fil_02_moderna", 3, "Criticismo de Immanuel Kant", "Juízos sintéticos a priori, revolução copernicana e Esclarecimento (Aufklärung)")
            ),
            finalSimulado = EnemTrailSimulado(id = "sim_fil_02", moduleId = "mod_fil_02_moderna")
        ),
        EnemTrailModule(
            id = "mod_fil_03_politica_etica",
            moduleNumber = 3,
            title = "Módulo 3: Filosofia Política e Ética",
            summary = "Maquiavel, Contratualistas (Hobbes, Locke, Rousseau) e Ética",
            lessons = listOf(
                EnemTrailLesson("fil_pol_01", "mod_fil_03_politica_etica", 1, "Maquiavel e a Autonomia da Política", "Virtù, Fortuna e o realismo político em O Príncipe"),
                EnemTrailLesson("fil_pol_02", "mod_fil_03_politica_etica", 2, "Contratualismo: Hobbes, Locke e Rousseau", "Estado de natureza, contrato social, soberania e propriedade"),
                EnemTrailLesson("fil_pol_03", "mod_fil_03_politica_etica", 3, "Ética Deontológica, Utilitarismo e Justiça", "Imperativo categórico kantiano, utilitarismo (Bentham/Mill) e John Rawls")
            ),
            finalSimulado = EnemTrailSimulado(id = "sim_fil_03", moduleId = "mod_fil_03_politica_etica")
        ),
        EnemTrailModule(
            id = "mod_fil_04_contemporanea",
            moduleNumber = 4,
            title = "Módulo 4: Filosofia Contemporânea",
            summary = "Nietzsche, Escola de Frankfurt, Existencialismo, Foucault e Habermas",
            lessons = listOf(
                EnemTrailLesson("fil_cont_01", "mod_fil_04_contemporanea", 1, "Nietzsche e a Crítica da Moral Ocidental", "Genealogia da moral, apolíneo vs. dionisíaco e transvaloração dos valores"),
                EnemTrailLesson("fil_cont_02", "mod_fil_04_contemporanea", 2, "Escola de Frankfurt e Hannah Arendt", "Razão instrumental, indústria cultural, banalidade do mal e espaço público"),
                EnemTrailLesson("fil_cont_03", "mod_fil_04_contemporanea", 3, "Foucault, Habermas e Bioética", "Microfísica do poder, biopolítica, agir comunicativo e ética aplicada")
            ),
            finalSimulado = EnemTrailSimulado(id = "sim_fil_04", moduleId = "mod_fil_04_contemporanea")
        )
    )

    fun getSociologyModules(): List<EnemTrailModule> = listOf(
        EnemTrailModule(
            id = "mod_soc_01_classicos",
            moduleNumber = 1,
            title = "Módulo 1: Os Clássicos da Sociologia",
            summary = "Surgimento da Sociologia, Émile Durkheim, Max Weber e Karl Marx",
            lessons = listOf(
                EnemTrailLesson("soc_cla_01", "mod_soc_01_classicos", 1, "Positivismo e Émile Durkheim", "Fato social, coercitividade, solidariedade mecânica e orgânica"),
                EnemTrailLesson("soc_cla_02", "mod_soc_01_classicos", 2, "Sociologia Compreensiva de Max Weber", "Ação social, tipos ideais, desencantamento do mundo e dominação legítima"),
                EnemTrailLesson("soc_cla_03", "mod_soc_01_classicos", 3, "Materialismo Histórico de Karl Marx", "Trabalho, alienação, mais-valia, ideologia e luta de classes")
            ),
            finalSimulado = EnemTrailSimulado(id = "sim_soc_01", moduleId = "mod_soc_01_classicos")
        ),
        EnemTrailModule(
            id = "mod_soc_02_cultura",
            moduleNumber = 2,
            title = "Módulo 2: Cultura, Identidade e Indústria Cultural",
            summary = "Antropologia Cultural, Patrimônio, Etnocentrismo e Meios de Comunicação",
            lessons = listOf(
                EnemTrailLesson("soc_cul_01", "mod_soc_02_cultura", 1, "Antropologia: Etnocentrismo e Relativismo Cultural", "Diversidade cultural, alteridade e crítica ao evolucionismo social"),
                EnemTrailLesson("soc_cul_02", "mod_soc_02_cultura", 2, "Patrimônio Cultural Material e Imaterial", "Memória coletiva, tombamento, saberes tradicionais e identidade nacional"),
                EnemTrailLesson("soc_cul_03", "mod_soc_02_cultura", 3, "Indústria Cultural, Consumo e Cibercultura", "Cultura de massa, padronização estética, redes digitais e esfera pública")
            ),
            finalSimulado = EnemTrailSimulado(id = "sim_soc_02", moduleId = "mod_soc_02_cultura")
        ),
        EnemTrailModule(
            id = "mod_soc_03_cidadania",
            moduleNumber = 3,
            title = "Módulo 3: Cidadania, Estado e Movimentos Sociais",
            summary = "Direitos Civis, Políticos e Sociais, Democracia e Ação Coletiva",
            lessons = listOf(
                EnemTrailLesson("soc_cid_01", "mod_soc_03_cidadania", 1, "Cidadania e Direitos em T. H. Marshall", "Evolução histórica dos direitos civis, políticos, sociais e humanos"),
                EnemTrailLesson("soc_cid_02", "mod_soc_03_cidadania", 2, "Movimentos Sociais Contemporâneos", "Movimentos urbanos, agrários, ambientalistas, feministas e antirracistas"),
                EnemTrailLesson("soc_cid_03", "mod_soc_03_cidadania", 3, "Democracia Participativa e Representativa", "Partidos, constituição cidadã de 1988 e controle social")
            ),
            finalSimulado = EnemTrailSimulado(id = "sim_soc_03", moduleId = "mod_soc_03_cidadania")
        ),
        EnemTrailModule(
            id = "mod_soc_04_trabalho",
            moduleNumber = 4,
            title = "Módulo 4: Mundo do Trabalho e Estratificação Social",
            summary = "Taylorismo, Fordismo, Toyotismo, Uberização e Desigualdades no Brasil",
            lessons = listOf(
                EnemTrailLesson("soc_tra_01", "mod_soc_04_trabalho", 1, "Modelos Produtivos e Precarização do Trabalho", "Fordismo, Toyotismo, flexibilização, terceirização e plataformização"),
                EnemTrailLesson("soc_tra_02", "mod_soc_04_trabalho", 2, "Estratificação, Mobilidade e Desigualdade Social", "Bourdieu (capital cultural e violência simbólica), Bauman (modernidade líquida) e estrutura social brasileira")
            ),
            finalSimulado = EnemTrailSimulado(id = "sim_soc_04", moduleId = "mod_soc_04_trabalho")
        )
    )

    fun buildAuthoredBankQuestions(startOrder: Int = 2000): Pair<List<LessonRef>, List<BankQuestion>> {
        val lessons = mutableListOf<LessonRef>()
        val questions = mutableListOf<BankQuestion>()
        var order = startOrder

        for (mod in getPhilosophyModules()) {
            for (lesson in mod.lessons) {
                val ref = LessonRef(
                    lessonId = lesson.id,
                    subject = "Filosofia",
                    moduleId = mod.id,
                    moduleTitle = mod.title,
                    title = lesson.title,
                    subtopic = lesson.subtopic,
                    order = order++
                )
                lessons.add(ref)
                getPhilosophyExercises(lesson.id).forEachIndexed { idx, ex ->
                    val diff = when (idx % 3) {
                        0 -> QDifficulty.EASY
                        1 -> QDifficulty.MEDIUM
                        else -> QDifficulty.HARD
                    }
                    questions.add(
                        BankQuestion(
                            question = Question(
                                id = "enem_fil_${lesson.id}_$idx",
                                lessonId = lesson.id,
                                format = QuestionFormat.MULTIPLE_CHOICE,
                                difficulty = diff,
                                skill = mod.title,
                                concept = lesson.subtopic,
                                statement = ex.problemStatement,
                                explanation = ex.stepByStepSolution,
                                retryGuidance = ex.hint,
                                source = QuestionSource.INEP_ENEM,
                                options = ex.options,
                                answerIndex = ex.correctIndex
                            ),
                            lesson = ref
                        )
                    )
                }
            }
        }

        for (mod in getSociologyModules()) {
            for (lesson in mod.lessons) {
                val ref = LessonRef(
                    lessonId = lesson.id,
                    subject = "Sociologia",
                    moduleId = mod.id,
                    moduleTitle = mod.title,
                    title = lesson.title,
                    subtopic = lesson.subtopic,
                    order = order++
                )
                lessons.add(ref)
                getSociologyExercises(lesson.id).forEachIndexed { idx, ex ->
                    val diff = when (idx % 3) {
                        0 -> QDifficulty.EASY
                        1 -> QDifficulty.MEDIUM
                        else -> QDifficulty.HARD
                    }
                    questions.add(
                        BankQuestion(
                            question = Question(
                                id = "enem_soc_${lesson.id}_$idx",
                                lessonId = lesson.id,
                                format = QuestionFormat.MULTIPLE_CHOICE,
                                difficulty = diff,
                                skill = mod.title,
                                concept = lesson.subtopic,
                                statement = ex.problemStatement,
                                explanation = ex.stepByStepSolution,
                                retryGuidance = ex.hint,
                                source = QuestionSource.INEP_ENEM,
                                options = ex.options,
                                answerIndex = ex.correctIndex
                            ),
                            lesson = ref
                        )
                    )
                }
            }
        }

        return Pair(lessons, questions)
    }

    fun getPhilosophyExercises(lessonId: String): List<MathExercise> = when (lessonId) {
        "fil_ant_01" -> listOf(
            MathExercise(
                id = "fil_ant_01_q1",
                title = "Passagem do Mito ao Logos",
                problemStatement = "(ENEM) O surgimento da filosofia na Grécia Antiga (século VI a.C.) caracterizou-se pela investigação racional da physis (natureza) e pela busca da arché (princípio originário). Ao comparar o discurso filosófico nascente com a tradição mítica de Homero e Hesíodo, verifica-se que os filósofos pré-socráticos inovaram ao:",
                options = listOf(
                    "rejeitar qualquer ordem natural no cosmos, atribuindo os fenômenos físicos ao acaso divino.",
                    "exigir justificativas racionais e causas imanentes à própria natureza para explicar a origem e a transformação do cosmos.",
                    "substituir a observação da natureza pela obediência dogmática aos oráculos religiosos de Delfos.",
                    "restringir o debate filosófico aos sacerdotes, proibindo a discussão pública nas cidades-Estado.",
                    "negar a existência do movimento e da multiplicidade a partir de relatos genealógicos dos deuses olímpicos."
                ),
                correctIndex = 1,
                hint = "Os pré-socráticos buscaram uma explicação racional (logos) baseada em princípios internos à própria natureza (physis).",
                stepByStepSolution = "A filosofia pré-socrática substitui a cosmogonia (narrativa genealógica sobrenatural dos mitos) pela cosmologia racional, buscando a arché na própria physis."
            ),
            MathExercise(
                id = "fil_ant_01_q2",
                title = "Heráclito e Parmênides: Devir vs. Ser",
                problemStatement = "(ENEM) Enquanto Heráclito de Éfeso afirmava que 'tudo flui' (panta rei) e que ninguém pode entrar duas vezes no mesmo rio, pois as águas se renovam pelo conflito dos opostos, Parmênides de Eleia sustentava que 'o Ser é e o não-ser não é'. Esse embate clássico da filosofia pré-socrática inaugura a discussão ontológica acerca da:",
                options = listOf(
                    "oposição entre o devir perpétuo das coisas sensíveis e a imutabilidade lógica do Ser pensado pela razão.",
                    "superioridade das artes poéticas sobre a geometria demonstrativa na pólis grega.",
                    "impossibilidade de qualquer linguagem humana referir-se ao mundo material.",
                    "subordinação da filosofia grega aos dogmas teológicos do Oriente Médio.",
                    "organização censitária das magistraturas democráticas em Atenas."
                ),
                correctIndex = 0,
                hint = "Heráclito enfatiza a mudança (devir) e a tensão dos contrários; Parmênides defende a unidade e imutabilidade do Ser.",
                stepByStepSolution = "O debate entre o mobilismo de Heráclito e o imobilismo racionalista de Parmênides funda a ontologia clássica, que Platão e Aristóteles buscarão sintetizar."
            )
        )
        "fil_ant_02" -> listOf(
            MathExercise(
                id = "fil_ant_02_q1",
                title = "Método Socrático: Ironia e Maiêutica",
                problemStatement = "(ENEM) Nos diálogos platônicos, Sócrates interroga seus interlocutores na Ágora ateniense sobre conceitos como justiça, coragem e piedade. Ao reconhecer que 'só sei que nada sei', o método socrático articula duas etapas complementares — a ironia e a maiêutica — que têm por finalidade:",
                options = listOf(
                    "transmitir um conjunto fechado de doutrinas escritas para memorização passiva dos discípulos.",
                    "vender técnicas de persuasão retórica para garantir a vitória em tribunais independentemente da verdade.",
                    "desconstruir as falsas certezas do interlocutor (ironia) e levá-lo a dar à luz conceitos universais pelo próprio exame racional (maiêutica).",
                    "provar que toda verdade é meramente subjetiva e depende da conveniência política de cada cidadão.",
                    "afastar os jovens da reflexão ética, concentrando-se exclusivamente no estudo astronômico dos astros."
                ),
                correctIndex = 2,
                hint = "A ironia purga os preconceitos e opiniões infundadas (doxa); a maiêutica ('parto das ideias') conduz à definição conceitual.",
                stepByStepSolution = "Ao contrário do relativismo sofístico, Sócrates utiliza o diálogo crítico para refutar opiniões aparentes e alcançar definições éticas universais."
            ),
            MathExercise(
                id = "fil_ant_02_q2",
                title = "Os Sofistas e a Democracia Ateniense",
                problemStatement = "(ENEM) Protágoras de Abdera celebrizou-se pela máxima 'O homem é a medida de todas as coisas, das que são enquanto são e das que não são enquanto não são'. No contexto da democracia ateniense do século V a.C., a atuação dos sofistas valorizava:",
                options = listOf(
                    "o domínio da retórica e da argumentação persuasiva nas assembleias políticas, concebendo a verdade como construção discursiva e consensual.",
                    "a contemplação metafísica de essências eternas acessíveis apenas a reis-filósofos hereditários.",
                    "o isolamento ascético fora da vida pública para evitar a corrupção das instituições democráticas.",
                    "a fundamentação teocrática das leis da pólis como decretos imutáveis de Zeus.",
                    "a abolição da palavra falada nos julgamentos populares da Helieia."
                ),
                correctIndex = 0,
                hint = "Na democracia direta ateniense, a isegoria (direito igual à palavra na assembleia) tornava a retórica e a persuasão fundamentais.",
                stepByStepSolution = "Os sofistas atendiam à demanda prática da democracia ateniense, ensinando a arte da eloquência e defendendo o caráter convencional (nomos) das leis e valores."
            )
        )
        "fil_ant_03" -> listOf(
            MathExercise(
                id = "fil_ant_03_q1",
                title = "Platão: Alegoria da Caverna e Epistemologia",
                problemStatement = "(ENEM) No Livro VII de A República, Platão descreve prisioneiros acorrentados desde a infância no fundo de uma caverna, que tomam as sombras projetadas na parede como a única realidade existente. Nessa alegoria clássica, a saída do prisioneiro em direção à luz do Sol simboliza:",
                options = listOf(
                    "a aceitação conformista das aparências sensíveis como critério definitivo da ciência.",
                    "a ascese dialética da alma, que supera a opinião enganosa dos sentidos (doxa) rumo ao conhecimento inteligível das Ideias e do Bem (episteme).",
                    "o abandono da razão filosófica em favor da inspiração poética e mitológica.",
                    "a defesa da tirania militar como regime político superior à sofocracia.",
                    "a impossibilidade humana de distinguir entre simulacros e realidades inteligíveis."
                ),
                correctIndex = 1,
                hint = "Em Platão, o mundo sensível corresponde às sombras (doxa), enquanto o mundo inteligível das Formas/Ideias corresponde à ciência verdadeira (episteme).",
                stepByStepSolution = "A Alegoria da Caverna sintetiza o dualismo epistemológico e pedagógico platônico: educar é converter o olhar da alma das sombras sensíveis para as essências inteligíveis."
            )
        )
        "fil_ant_04" -> listOf(
            MathExercise(
                id = "fil_ant_04_q1",
                title = "Ética a Nicômaco: A Virtude como Justa Medida",
                problemStatement = "(ENEM) Em Ética a Nicômaco, Aristóteles define a virtude moral (areté) como uma disposição de caráter relacionada com a escolha deliberada, consistindo numa 'mediana' (mesotes) determinada pela razão prudente (phronesis) entre dois vícios: um por excesso e outro por falta. De acordo com essa concepção:",
                options = listOf(
                    "a virtude é inata e independe do hábito ou da educação prática do cidadão na pólis.",
                    "o agir ético exige sabedoria prática e exercício reiterado do equilíbrio racional, evitando extremos como a covardia e a temeridade.",
                    "a felicidade (eudaimonia) consiste no acúmulo ilimitado de riquezas materiais e prazeres imediatos.",
                    "as paixões humanas devem ser completamente extirpadas, pois qualquer sentimento impede o juízo moral.",
                    "a ética é divorciada da política, realizando-se plenamente apenas no isolamento solitário."
                ),
                correctIndex = 1,
                hint = "Para Aristóteles, a coragem é o justo meio entre a covardia (falta) e a temeridade (excesso), adquirida pelo hábito racional.",
                stepByStepSolution = "A ética aristotélica é teleológica e eudaimonista: a excelência moral constrói-se pela prática habitual da justa medida guiada pela prudência (phronesis)."
            )
        )
        "fil_ant_05" -> listOf(
            MathExercise(
                id = "fil_ant_05_q1",
                title = "Estoicismo e Ataraxia no Período Helenístico",
                problemStatement = "(ENEM) Pensadores estoicos como Sêneca, Epicteto e Marco Aurélio ensinaram que a tranquilidade da alma (ataraxia) depende de distinguir rigorosamente entre o que está sob nosso controle (nossos juízos, desejos e ações) e o que não depende de nós (riqueza, fama, doença, morte e acontecimentos externos). Para o estoicismo, a liberdade e a sabedoria consistem em:",
                options = listOf(
                    "rebelar-se contra as leis da natureza e tentar controlar todos os infortúnios do destino.",
                    "governar as próprias paixões pela razão e aceitar com serenidade a ordem racional do cosmos (amor fati).",
                    "buscar o prazer corpóreo imediato sem qualquer compromisso com os deveres cívicos.",
                    "suspender todo juízo moral e recusar a participação na vida política.",
                    "acumular bens exteriores como condição indispensável para a virtude moral."
                ),
                correctIndex = 1,
                hint = "O estoicismo propõe o autodomínio racional (apatheia) diante das paixões e a harmonia com o Logos universal.",
                stepByStepSolution = "Para os estoicos, o sofrimento nasce de desejar controlar o incontrolável; o sábio conquista a liberdade interior ao dominar seus juízos e viver segundo a razão."
            )
        )
        "fil_mod_01" -> listOf(
            MathExercise(
                id = "fil_mod_01_q1",
                title = "Descartes: Dúvida Metódica e Cogito",
                problemStatement = "(ENEM) Nas Meditações Metafísicas, René Descartes decide rejeitar provisoriamente como falso tudo aquilo em que pudesse imaginar a menor dúvida — os dados dos sentidos, a realidade do mundo exterior e até as verdades matemáticas (hipótese do gênio maligno). Esse procedimento radical da dúvida hiperbólica tem como objetivo:",
                options = listOf(
                    "demonstrar que o ceticismo absoluto é insuperável e que nenhuma ciência humana é possível.",
                    "encontrar um fundamento indubitável, claro e distinto para o conhecimento científico, alcançado na certeza do 'Penso, logo existo' (Cogito, ergo sum).",
                    "provar que toda ideia verdadeira deriva exclusivamente das impressões sensoriais imediatas.",
                    "restaurar o argumento de autoridade da escolástica medieval como critério de verdade.",
                    "subordinar a física matemática às aparências qualitativas da percepção cotidiana."
                ),
                correctIndex = 1,
                hint = "A dúvida em Descartes não é um fim cético, mas um método para encontrar uma primeira verdade indubitável (o sujeito pensante).",
                stepByStepSolution = "Mesmo que um gênio maligno me engane em tudo, para ser enganado é necessário que eu pense e exista enquanto coisa pensante (res cogitans)."
            )
        )
        "fil_mod_02" -> listOf(
            MathExercise(
                id = "fil_mod_02_q1",
                title = "Empirismo: John Locke e David Hume",
                problemStatement = "(ENEM) 'Suponhamos, pois, que a mente seja, como se diz, um papel em branco, desprovida de todos os caracteres, sem quaisquer ideias; como ela será suprida? A isso respondo, numa palavra: pela experiência.' (LOCKE, J. Ensaio acerca do entendimento humano). Ao formular a tese da tábula rasa, o empirismo moderno contrapõe-se diretamente ao:",
                options = listOf(
                    "método indutivo experimental defendido por Francis Bacon na ciência moderna.",
                    "inatismo racionalista cartesiano, que admitia a existência de ideias verdadeiras inscritas na alma desde o nascimento.",
                    "criticismo kantiano, que negava qualquer participação da sensibilidade na produção do conhecimento.",
                    "materialismo histórico oitocentista, que explicava a consciência a partir das relações produtivas.",
                    "ceticismo antigo, que duvidava da infalibilidade dos dogmas metafísicos."
                ),
                correctIndex = 1,
                hint = "Locke refuta o inatismo de Descartes e Platão, sustentando que todo conhecimento provém da experiência sensível (sensação) e interna (reflexão).",
                stepByStepSolution = "A metáfora da tábula rasa sintetiza a tese empirista de que não há ideias inatas: todas as representações mentais originam-se da experiência."
            )
        )
        "fil_mod_03" -> listOf(
            MathExercise(
                id = "fil_mod_03_q1",
                title = "Kant: Criticismo e Esclarecimento (Aufklärung)",
                problemStatement = "(ENEM) Em 'Resposta à pergunta: Que é o Esclarecimento?', Immanuel Kant define o Esclarecimento (Aufklärung) como a saída do ser humano de sua menoridade, da qual ele próprio é culpado por falta de coragem de servir-se de seu próprio entendimento sem a tutela de outrem (Sapere aude!). Já na Crítica da Razão Pura, Kant supera o impasse entre racionalistas e empiristas ao demonstrar que:",
                options = listOf(
                    "todo conhecimento começa com a experiência sensível, mas é organizado pelas formas a priori da sensibilidade (espaço e tempo) e pelas categorias do entendimento.",
                    "a razão humana pode conhecer cientificamente coisas em si mesmas (númenos) sem qualquer recurso aos sentidos.",
                    "o sujeito cognoscente é um receptor inteiramente passivo que apenas copia os objetos exteriores.",
                    "a moralidade deve basear-se na obediência heterônoma a recompensas e punições externas.",
                    "as leis universais da física são meras convenções arbitrárias desprovidas de validade objetiva."
                ),
                correctIndex = 0,
                hint = "Para Kant, 'conceitos sem intuições são vazios; intuições sem conceitos são cegas': sensibilidade e entendimento cooperam no conhecimento dos fenômenos.",
                stepByStepSolution = "A 'Revolução Copernicana' de Kant mostra que o sujeito estrutura ativamente os dados da experiência mediante formas e categorias a priori."
            )
        )
        "fil_pol_01" -> listOf(
            MathExercise(
                id = "fil_pol_01_q1",
                title = "Maquiavel: Virtù e Fortuna em O Príncipe",
                problemStatement = "(ENEM) Em O Príncipe, Nicolau Maquiavel rompe com a tradição dos 'espelhos de príncipes' medievais ao analisar a política segundo a 'verdade efetiva da coisa', e não segundo repúblicas ou principados imaginários. Nesse realismo político moderno, a relação entre virtù e fortuna significa que o governante eficaz deve:",
                options = listOf(
                    "submeter todas as decisões de Estado aos preceitos da moral cristã privada, ainda que isso provoque a ruína da república.",
                    "aguardar passivamente os caprichos do acaso (fortuna), confiando na proteção providencial.",
                    "exercer a capacidade estratégica, prudência e firmeza de ação (virtù) para prever circunstâncias históricas instáveis (fortuna) e preservar a ordem e o Estado.",
                    "governar exclusivamente pelo terror constante, buscando o ódio ativo de todos os súditos.",
                    "abolir as leis civis e os exércitos próprios, entregando a defesa da cidade a tropas mercenárias."
                ),
                correctIndex = 2,
                hint = "Virtù em Maquiavel não é a virtude cristã, mas a excelência política e capacidade de leitura das circunstâncias (fortuna) para manter o Estado.",
                stepByStepSolution = "Maquiavel funda a ciência política moderna ao autonomizar a lógica do poder e da estabilidade estatal frente à moral privada e religiosa."
            )
        )
        "fil_pol_02" -> listOf(
            MathExercise(
                id = "fil_pol_02_q1",
                title = "Contratualismo: Hobbes, Locke e Rousseau",
                problemStatement = "(ENEM) Os filósofos contratualistas dos séculos XVII e XVIII fundamentaram a legitimidade do Estado moderno a partir da passagem do 'estado de natureza' para a 'sociedade civil' mediante um pacto social. Comparando as matrizes de Thomas Hobbes, John Locke e Jean-Jacques Rousseau, assinale a proposição correta:",
                options = listOf(
                    "Para Hobbes, o estado de natureza é de paz e cooperação espontânea, cabendo ao Estado apenas proteger a propriedade privada.",
                    "Para Locke, o pacto social visa preservar os direitos naturais preexistentes (vida, liberdade e propriedade), sendo legítimo o direito de resistência contra governos tirânicos.",
                    "Para Rousseau, o surgimento da propriedade privada eliminou as desigualdades e garantiu a liberdade natural plena.",
                    "Para Hobbes, a soberania deve ser fragmentada em três poderes independentes para limitar a autoridade do Leviatã.",
                    "Para Locke, os indivíduos alienam todos os seus direitos de modo irrevogável a um monarca absoluto."
                ),
                correctIndex = 1,
                hint = "Locke é o pai do liberalismo político: o governo é um fiduciário instituído pelo consentimento para garantir vida, liberdade e propriedade.",
                stepByStepSolution = "Em Locke, o contrato social não suprime os direitos naturais, mas cria um juiz imparcial (governo limitado) para protegê-los."
            )
        )
        "fil_pol_03" -> listOf(
            MathExercise(
                id = "fil_pol_03_q1",
                title = "Ética Kantiana vs. Utilitarismo e Justiça em Rawls",
                problemStatement = "(ENEM) 'Age apenas segundo uma máxima tal que possas ao mesmo tempo querer que ela se torne lei universal' (KANT, I. Fundamentação da metafísica dos costumes). Enquanto a ética deontológica kantiana avalia a moralidade da ação pelo dever e pela universalidade da intenção, a teoria da justiça como equidade de John Rawls propõe que princípios justos sejam escolhidos sob um 'véu de ignorância', o qual consiste em:",
                options = listOf(
                    "maximizar o prazer da maioria mesmo que isso implique suprimir as liberdades básicas de minorias vulneráveis.",
                    "deliberar sobre as regras fundamentais da sociedade sem que ninguém saiba previamente sua classe social, talentos ou posição, garantindo liberdade igual e proteção aos menos favorecidos.",
                    "atribuir privilégios jurídicos aos cidadãos mais ricos com base no mérito hereditário.",
                    "eliminar todas as instituições públicas de redistribuição de renda e educação.",
                    "fundamentar os direitos civis em dogmas religiosos compartilhados pela maioria."
                ),
                correctIndex = 1,
                hint = "Na posição original sob o 'véu de ignorância' de Rawls, escolhem-se princípios equitativos (liberdade igual e princípio da diferença).",
                stepByStepSolution = "O véu de ignorância garante imparcialidade na escolha dos princípios de justiça, protegendo as liberdades básicas e permitindo desigualdades apenas se beneficiarem os menos favorecidos."
            )
        )
        "fil_cont_01" -> listOf(
            MathExercise(
                id = "fil_cont_01_q1",
                title = "Nietzsche: Crítica à Metafísica e Genealogia da Moral",
                problemStatement = "(ENEM) Friedrich Nietzsche empreendeu uma crítica contundente à tradição metafísica socrático-platônica e à moral ascética, propondo a 'transvaloração de todos os valores'. Nessa perspectiva, a chamada 'moral de rebanho' caracteriza-se por:",
                options = listOf(
                    "afirmar entusiasticamente a vida terrena, o corpo, a criatividade artística e a superação de si.",
                    "negar os instintos vitais e o mundo sensível em nome de valores transcendentes baseados no ressentimento e na culpa.",
                    "promover a autonomia crítica dos indivíduos singulares contra a padronização social.",
                    "valorizar a tragédia grega pré-socrática pela união entre o apolíneo e o dionisíaco.",
                    "defender o progresso científico positivista como redenção definitiva da humanidade."
                ),
                correctIndex = 1,
                hint = "Em A Genealogia da Moral, Nietzsche denuncia como o ressentimento inverteu os valores vitais afirmativos em culpa e negação da vida.",
                stepByStepSolution = "Nietzsche diagnostica o niilismo passivo na tradição que desvaloriza o corpo e o devir terreno em favor de um além metafísico."
            )
        )
        "fil_cont_02" -> listOf(
            MathExercise(
                id = "fil_cont_02_q1",
                title = "Hannah Arendt e a Banalidade do Mal",
                problemStatement = "(ENEM) Ao acompanhar o julgamento do oficial nazista Adolf Eichmann em Jerusalém, a filósofa Hannah Arendt cunhou o conceito de 'banalidade do mal'. Com essa expressão, a autora destacou que grandes atrocidades em regimes totalitários podem ser perpetradas por indivíduos que:",
                options = listOf(
                    "possuem necessariamente distúrbios psiquiátricos graves e ódio fanático patológico.",
                    "renunciam à capacidade de pensar criticamente e de julgar moralmente, agindo como burocratas obedientes que apenas cumprem ordens e rotinas técnicas.",
                    "atuam de forma isolada e clandestina contra as leis e diretrizes do aparelho estatal.",
                    "buscam deliberadamente o martírio político para derrubar a burocracia moderna.",
                    "desconhecem a existência de hierarquias administrativas e normas jurídicas."
                ),
                correctIndex = 1,
                hint = "Para Arendt, Eichmann não era um monstro demoníaco excepcional, mas um burocrata incapaz de refletir sobre as consequências morais de seus atos.",
                stepByStepSolution = "A banalidade do mal decorre da ausência de pensamento reflexivo (irreflexão) e da redução do agente moral a uma engrenagem burocrática."
            )
        )
        else -> listOf(
            MathExercise(
                id = "fil_cont_03_q1",
                title = "Michel Foucault e Jurgen Habermas: Poder Disciplinar e Agir Comunicativo",
                problemStatement = "(ENEM) Em Vigiar e Punir, Michel Foucault analisa como as sociedades modernas substituíram os suplícios corporais públicos por instituições disciplinares (prisões, escolas, hospitais, fábricas) baseadas no modelo do Panóptico. O objetivo central do poder disciplinar descrito por Foucault é:",
                options = listOf(
                    "exterminar fisicamente os infratores em praça pública para demonstrar a vingança pessoal do monarca.",
                    "produzir 'corpos dóceis' — economicamente produtivos e politicamente submissos — mediante vigilância contínua, normalização e adestramento.",
                    "abolir todas as formas de controle do tempo e do espaço no interior das fábricas e escolas.",
                    "restringir o exercício do poder exclusivamente ao parlamento e aos tribunais superiores.",
                    "garantir a autogestão plena dos trabalhadores sem qualquer supervisão hierárquica."
                ),
                correctIndex = 1,
                hint = "Para Foucault, o poder disciplinar não é apenas repressivo, mas produtivo: molda hábitos, administra o tempo e fabrica corpos dóceis e úteis.",
                stepByStepSolution = "A microfísica do poder opera por meio da vigilância panóptica internalizada e da sanção normalizadora, produzindo sujeitos úteis e obedientes."
            )
        )
    }

    fun getSociologyExercises(lessonId: String): List<MathExercise> = when (lessonId) {
        "soc_cla_01" -> listOf(
            MathExercise(
                id = "soc_cla_01_q1",
                title = "Émile Durkheim: Fato Social e Coesão",
                problemStatement = "(ENEM) Para estabelecer a Sociologia como ciência autônoma no final do século XIX, Émile Durkheim definiu seu objeto próprio de estudo: o 'fato social'. Segundo As Regras do Método Sociológico, os fatos sociais — como as leis, os costumes, a língua e o sistema monetário — identificam-se por três características essenciais:",
                options = listOf(
                    "subjetividade individual, espontaneidade biológica e ausência de sanções coletivas.",
                    "coercitividade, exterioridade em relação às consciências individuais e generalidade no interior de dada sociedade.",
                    "dependência exclusiva da vontade psicológica de cada cidadão isolado.",
                    "imutabilidade absoluta no tempo e ausência de relação com a divisão do trabalho social.",
                    "redução de todos os fenômenos coletivos à luta armada entre classes antagônicas."
                ),
                correctIndex = 1,
                hint = "Para Durkheim, o fato social existe fora do indivíduo (exterioridade), impõe-se a ele (coercitividade) e repete-se no grupo (generalidade).",
                stepByStepSolution = "Durkheim delimita o fato social pelas propriedades de exterioridade, coercitividade e generalidade, devendo o sociólogo tratá-los 'como coisas'."
            ),
            MathExercise(
                id = "soc_cla_01_q2",
                title = "Solidariedade Mecânica e Orgânica em Durkheim",
                problemStatement = "(ENEM) Na obra Da Divisão do Trabalho Social, Durkheim distingue dois tipos de coesão social: a solidariedade mecânica e a solidariedade orgânica. Nas sociedades capitalistas industriais modernas, predomina a solidariedade orgânica, que se fundamenta na:",
                options = listOf(
                    "semelhança absoluta de crenças religiosas e ausência de especialização profissional.",
                    "intensa divisão do trabalho social e interdependência funcional entre indivíduos que exercem papéis especializados.",
                    "supressão do direito restitutivo em favor de punições exclusivamente expiatórias e religiosas.",
                    "autossuficiência produtiva de clãs familiares isolados sem trocas mercantis.",
                    "eliminação das instituições jurídicas e profissionais na vida urbana."
                ),
                correctIndex = 1,
                hint = "Assim como os órgãos de um organismo vivo possuem funções diferentes e dependem uns dos outros, a solidariedade orgânica nasce da especialização do trabalho.",
                stepByStepSolution = "Na sociedade moderna complexa, a coesão orgânica decorre da complementaridade e interdependência geradas pela divisão social do trabalho."
            )
        )
        "soc_cla_02" -> listOf(
            MathExercise(
                id = "soc_cla_02_q1",
                title = "Max Weber: Ação Social e Desencantamento do Mundo",
                problemStatement = "(ENEM) Ao contrário do positivismo durkheimiano, a Sociologia Compreensiva de Max Weber toma como unidade fundamental de análise a 'ação social', definida como toda conduta humana dotada de um sentido subjetivo orientado pelo comportamento de outros. Quando um indivíduo age calculando racionalmente os meios mais eficientes para atingir determinado fim econômico ou técnico, trata-se da:",
                options = listOf(
                    "ação social tradicional, guiada por hábitos arraigados e costumes imemoriais.",
                    "ação social afetiva, movida por paixões, humores e sentimentos imediatos.",
                    "ação social racional referente a fins (instrumental), típica da burocratização e do capitalismo moderno.",
                    "ação reflexa puramente biológica, desprovida de intencionalidade comunicativa.",
                    "ação carismática mística, avessa a qualquer cálculo ou planejamento metódico."
                ),
                correctIndex = 2,
                hint = "Weber classifica quatro tipos puros de ação social: racional com relação a fins, racional com relação a valores, afetiva e tradicional.",
                stepByStepSolution = "A modernidade ocidental, segundo Weber, caracteriza-se pela crescente racionalização e predomínio da ação racional referente a fins."
            )
        )
        "soc_cla_03" -> listOf(
            MathExercise(
                id = "soc_cla_03_q1",
                title = "Karl Marx: Trabalho, Alienação e Mais-Valia",
                problemStatement = "(ENEM) Na análise do modo de produção capitalista desenvolvida por Karl Marx em O Capital, a força de trabalho torna-se uma mercadoria cujo valor de uso possui a propriedade peculiar de criar mais valor do que ela mesma custa para ser reproduzida (salário). A diferença entre o valor total produzido pelo trabalhador e o valor pago pela sua força de trabalho denomina-se:",
                options = listOf(
                    "solidariedade orgânica, responsável pela harmonia distributiva entre capital e trabalho.",
                    "mais-valia, que constitui a fonte do lucro capitalista e da acumulação de capital.",
                    "ação social estamental, baseada no prestígio honorífico das corporações medievais.",
                    "fetichismo benigno do consumo, que elimina a alienação no chão de fábrica.",
                    "capital cultural institucionalizado, medido pelos diplomas escolares."
                ),
                correctIndex = 1,
                hint = "O tempo de trabalho excedente não remunerado ao operário gera a mais-valia (absoluta ou relativa).",
                stepByStepSolution = "Em Marx, a exploração capitalista ocorre na esfera da produção mediante a apropriação privada da mais-valia gerada pelo trabalho."
            )
        )
        "soc_cul_01" -> listOf(
            MathExercise(
                id = "soc_cul_01_q1",
                title = "Etnocentrismo e Relativismo Cultural",
                problemStatement = "(ENEM) A Antropologia contemporânea superou o evolucionismo cultural do século XIX — que classificava as sociedades humanas em uma escala única de 'selvagens', 'bárbaras' e 'civilizadas' — ao consolidar o conceito de relativismo cultural. Adotar uma postura relativista na análise antropológica significa:",
                options = listOf(
                    "julgar os costumes de outros povos a partir dos padrões morais e estéticos da própria sociedade do observador.",
                    "compreender as práticas, crenças e símbolos de cada grupo humano a partir da lógica interna de seu próprio contexto cultural, combatendo o etnocentrismo.",
                    "provar que determinadas línguas e culturas indígenas são biologicamente inferiores às europeias.",
                    "impor a homogeneização cultural global como requisito para o desenvolvimento econômico.",
                    "negar a existência de diversidade simbólica entre diferentes sociedades humanas."
                ),
                correctIndex = 1,
                hint = "O etnocentrismo toma a própria cultura como centro e régua universal; o relativismo cultural busca compreender o outro em seus próprios termos.",
                stepByStepSolution = "Franz Boas e a antropologia moderna demonstraram que cada cultura possui historicidade e coerência simbólica próprias, refutando o etnocentrismo."
            )
        )
        "soc_cul_02" -> listOf(
            MathExercise(
                id = "soc_cul_02_q1",
                title = "Patrimônio Cultural Imaterial e Memória Social",
                problemStatement = "(ENEM) O Instituto do Patrimônio Histórico e Artístico Nacional (IPHAN), em consonância com a Constituição de 1988, reconhece como patrimônio cultural brasileiro não apenas monumentos arquitetônicos e conjuntos urbanos históricos, mas também bens de natureza imaterial, tais como o frevo, a roda de capoeira, o ofício das paneleiras de Goiabeiras e o Círio de Nazaré. O registro do patrimônio imaterial tem por finalidade:",
                options = listOf(
                    "congelar as práticas populares em museus fechados, proibindo sua recriação pelas comunidades.",
                    "valorizar e salvaguardar saberes, celebrações, formas de expressão e modos de fazer que constituem a memória e a identidade coletiva de diferentes grupos formadores da sociedade brasileira.",
                    "substituir a preservação de prédios históricos pela comercialização privada de festas folclóricas.",
                    "restringir o conceito de cultura nacional às manifestações eruditas de origem europeia.",
                    "padronizar os sotaques e rituais regionais segundo uma norma única estatal."
                ),
                correctIndex = 1,
                hint = "O patrimônio imaterial abrange práticas vivas, saberes, celebrações e lugares transmitidos entre gerações.",
                stepByStepSolution = "O reconhecimento do patrimônio imaterial democratiza a memória nacional ao incluir os saberes e expressões de povos indígenas, afro-brasileiros e comunidades tradicionais."
            )
        )
        "soc_cul_03" -> listOf(
            MathExercise(
                id = "soc_cul_03_q1",
                title = "Indústria Cultural em Adorno e Horkheimer",
                problemStatement = "(ENEM) Na obra Dialética do Esclarecimento (1947), Theodor Adorno e Max Horkheimer cunharam o conceito de 'Indústria Cultural' para analisar a produção artística e midiática sob o capitalismo tardio. Segundo os autores da Escola de Frankfurt, a Indústria Cultural caracteriza-se por:",
                options = listOf(
                    "estimular a emancipação crítica e a reflexão autônoma do público por meio de obras de vanguarda experimental.",
                    "transformar bens culturais em mercadorias padronizadas produzidas em série para entretenimento fácil, promovendo a passividade e o conformismo social.",
                    "surgir espontaneamente das tradições folclóricas rurais sem interferência do mercado publicitário.",
                    "eliminar a busca pelo lucro nas empresas de cinema, rádio, televisão e plataformas digitais.",
                    "restringir o acesso aos meios de comunicação apenas à elite intelectual universitária."
                ),
                correctIndex = 1,
                hint = "Para Adorno e Horkheimer, a indústria cultural aplica a lógica da linha de montagem às obras de arte, padronizando o gosto e atrofiando a imaginação crítica.",
                stepByStepSolution = "Ao converter a arte em mercadoria de consumo rápido e fórmulas repetitivas, a indústria cultural domestica o lazer e reforça a adaptação ao status quo."
            )
        )
        "soc_cid_01" -> listOf(
            MathExercise(
                id = "soc_cid_01_q1",
                title = "Cidadania: Direitos Civis, Políticos e Sociais",
                problemStatement = "(ENEM) Na clássica obra Cidadania, Classe Social e Status, o sociólogo T. H. Marshall analisa a construção histórica da cidadania moderna a partir de três dimensões de direitos: civis (liberdade individual, igualdade perante a lei, propriedade), políticos (votar e ser votado, participação no poder) e sociais. Constitui exemplo específico de direito social:",
                options = listOf(
                    "a liberdade de expressão e de manifestação religiosa sem censura prévia.",
                    "o acesso universal à educação pública, à saúde, à previdência e à proteção trabalhista digna.",
                    "o direito de habeas corpus contra prisões arbitrárias praticadas pelo Estado.",
                    "a faculdade de fundar partidos políticos e concorrer a cargos eletivos no parlamento.",
                    "a inviolabilidade do domicílio e do sigilo de correspondência."
                ),
                correctIndex = 1,
                hint = "Direitos civis referem-se às liberdades individuais; políticos, à participação eleitoral; sociais, ao bem-estar mínimo (educação, saúde, trabalho, seguridade).",
                stepByStepSolution = "Os direitos sociais conquistados no século XX exigem atuação positiva do Estado (políticas públicas) para garantir condições materiais dignas de existência."
            )
        )
        "soc_cid_02" -> listOf(
            MathExercise(
                id = "soc_cid_02_q1",
                title = "Novos Movimentos Sociais e Reconhecimento",
                problemStatement = "(ENEM) A partir das últimas décadas do século XX, ganharam destaque os chamados 'novos movimentos sociais' — como os movimentos ambientalistas, feministas, negro, indígena e LGBTQIA+. Diferentemente dos movimentos sindicais clássicos focados exclusivamente na pauta salarial, essas mobilizações contemporâneas articulam:",
                options = listOf(
                    "a recusa completa da democracia e dos direitos humanos universais.",
                    "lutas por reconhecimento identitário, direitos difusos, igualdade substantiva e combate a formas históricas de discriminação e exclusão.",
                    "a defesa do retorno ao voto censitário e à sociedade estamental do Antigo Regime.",
                    "a atuação exclusiva dentro de gabinetes ministeriais, sem manifestações públicas.",
                    "a substituição das pautas ambientais pelo incentivo irrestrito aos combustíveis fósseis."
                ),
                correctIndex = 1,
                hint = "Os novos movimentos sociais combinam demandas redistributivas com lutas por reconhecimento cultural, dignidade, meio ambiente e direitos humanos.",
                stepByStepSolution = "Esses movimentos ampliam a esfera pública democrática ao politizar desigualdades de gênero, raça, etnia e preservação socioambiental."
            )
        )
        "soc_cid_03" -> listOf(
            MathExercise(
                id = "soc_cid_03_q1",
                title = "Constituição de 1988 e Participação Democrática",
                problemStatement = "(ENEM) A Constituição Federal brasileira de 1988, conhecida como 'Constituição Cidadã', consolidou o modelo de democracia semidireta ou participativa ao combinar o sufrágio universal representativo com instrumentos de participação direta da população, entre os quais se incluem:",
                options = listOf(
                    "o plebiscito, o referendo, a iniciativa popular de lei, os conselhos gestores de políticas públicas e os orçamentos participativos.",
                    "a nomeação vitalícia de senadores e governadores pelo Poder Moderador.",
                    "a exigência de renda mínima comprovada para o alistamento eleitoral.",
                    "a proibição de audiências públicas e de sindicatos de trabalhadores urbanos.",
                    "o fechamento das assembleias legislativas estaduais durante períodos eleitorais."
                ),
                correctIndex = 0,
                hint = "O art. 14 da CF/88 prevê plebiscito, referendo e iniciativa popular, além de canais institucionais de controle social.",
                stepByStepSolution = "A redemocratização brasileira institucionalizou mecanismos participativos que complementam a representação parlamentar tradicional."
            )
        )
        "soc_tra_01" -> listOf(
            MathExercise(
                id = "soc_tra_01_q1",
                title = "Do Fordismo ao Toyotismo e à Uberização",
                problemStatement = "(ENEM) Nas últimas décadas, o mundo do trabalho passou da rigidez do modelo taylorista-fordista (produção em massa, linha de montagem fixa e grandes estoques) para a acumulação flexível toyotista (produção sob demanda just-in-time e trabalhador polivalente) e, mais recentemente, para a 'uberização' mediada por algoritmos digitais. Um efeito socioeconômico central dessa plataformização do trabalho é:",
                options = listOf(
                    "a universalização do emprego formal vitalício com estabilidade estatutária para todos os entregadores e motoristas.",
                    "a transferência dos custos, riscos e instrumentos de trabalho para o próprio trabalhador, acompanhada da fragilização de vínculos e garantias trabalhistas sob o discurso do 'empreendedorismo de si'.",
                    "a redução da jornada diária para quatro horas com remuneração fixa garantida pelas plataformas.",
                    "o fortalecimento automático das convenções coletivas sindicais sem mediação tecnológica.",
                    "o desaparecimento do setor de serviços nas metrópoles contemporâneas."
                ),
                correctIndex = 1,
                hint = "A uberização combina gerenciamento algorítmico em tempo real com ausência de direitos trabalhistas clássicos (férias, 13º, limite de jornada).",
                stepByStepSolution = "As plataformas digitais exercem controle sobre tarifas e metas via algoritmos, mas transferem os custos de manutenção e os riscos sociais ao trabalhador."
            )
        )
        else -> listOf(
            MathExercise(
                id = "soc_tra_02_q1",
                title = "Pierre Bourdieu e Zygmunt Bauman: Capital Cultural e Modernidade Líquida",
                problemStatement = "(ENEM) O sociólogo francês Pierre Bourdieu demonstrou que as desigualdades escolares e sociais não decorrem apenas da distribuição desigual de renda (capital econômico), mas também da posse diferenciada de 'capital cultural' (domínio da norma culta, familiaridade com bens artísticos, títulos escolares) e 'capital social' (redes de relações). Quando a escola trata como 'dom natural' aquilo que na verdade é herança cultural das classes favorecidas, opera-se o que Bourdieu denomina:",
                options = listOf(
                    "mobilidade social plena e neutralidade meritocrática absoluta.",
                    "violência simbólica e reprodução das desigualdades sociais sob a aparência de seleção neutra.",
                    "solidariedade mecânica baseada na igualdade patrimonial entre as famílias.",
                    "anomia jurídica decorrente da ausência de currículos escolares.",
                    "mais-valia absoluta extraída diretamente dos estudantes."
                ),
                correctIndex = 1,
                hint = "A violência simbólica impõe os códigos culturais das classes dominantes como os únicos legítimos, naturalizando privilégios sociais como mérito individual.",
                stepByStepSolution = "Para Bourdieu, ao exigir de todos o capital cultural que apenas algumas famílias transmitem no ambiente doméstico, a instituição escolar legitima e reproduz a estrutura de classes."
            )
        )
    }
}
