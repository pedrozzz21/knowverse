package com.example.data.languages

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático Aprofundado e Exercícios Comentados
 * Módulo 1: Interpretação de Texto e Semântica
 * - ling_sem_01: Tipologias e Gêneros Textuais
 * - ling_sem_02: Intertextualidade e Interdiscursividade
 * - ling_sem_03: Figuras de Linguagem
 * - ling_sem_04: Ambiguidade, Duplo Sentido e Humor em Tirinhas/Charges
 * - ling_sem_05: Estratégias Argumentativas
 */
object LanguagesInterpretationDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "ling_sem_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Tipologias e Gêneros Textuais no ENEM

                    No ENEM, a distinção entre **Tipologia Textual** (estrutura linguística finita) e **Gênero Textual** (práticas comunicativas infinitas e sociocomunicativas) é um dos pilares mais frequentes da prova.

                    ### 1. Tipologia Textual (Forma Estrutural)
                    Refere-se à natureza linguística e aos traços gramaticais predominantes no texto:
                    • **Narrativa:** Foco no tempo, espaço, narrador, personagens e sequência de ações (verbos no pretérito).
                    • **Dissertativa-Argumentativa:** Defesa de ponto de vista com tese, fundamentação lógica e repertório sociocultural.
                    • **Descritiva:** Caracterização de seres, ambientes ou objetos (predomínio de adjetivos e verbos de estado).
                    • **Injuntiva (Instrucional):** Prescrição de comportamento ou instrução passo a passo (verbos no imperativo ou infinitivo, ex: receitas, editais, bulas).
                    • **Expositiva:** Transmissão neutra e objetiva de informações e dados, sem defesa de tese.

                    ### 2. Gêneros Textuais (Função Social e Circulação)
                    São padrões sociocomunicativos que surgem da necessidade social:
                    • **Jornalísticos:** Notícia (relato objetivo de fato recente), reportagem (análise aprofundada com múltiplas fontes), artigo de opinião (autoria expressa e tese clara), crônica (olhar poético ou crítico sobre o cotidiano).
                    • **Digitais e Contemporâneos:** Posts de redes sociais, tweets, podcasts, memes e fóruns virtuais, marcados por concisão, hipertextualidade e multimodalidade.

                    ### 3. Matriz de Habilidades do ENEM
                    O Inep cobra o reconhecimento da **intenção comunicativa** e da **esfera de circulação**: por que o autor escolheu aquele gênero específico para produzir determinado efeito no leitor?
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Tipologia vs Gênero", "Tipologia = Estrutura (5 fixas) | Gênero = Uso social (infinitos)", "Tipologias compõem os gêneros em proporções variáveis"),
                    FormulaOrSyntaxItem("Injuntivo", "Verbos no imperativo / infinitivo prescritivo", "Instruir, orientar, ordenar ações"),
                    FormulaOrSyntaxItem("Dissertativo-Argumentativo", "Tese + Argumentos + Conclusão propositiva", "Convencer o interlocutor sobre uma tese")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Diferença entre Notícia e Reportagem",
                        codeOrFormula = "Fato factual imediato (Notícia) vs Investigação contextualizada (Reportagem)",
                        explanation = "A notícia relata 'o que, quem, onde, quando'. A reportagem aprofunda 'por que, impactos futuros, entrevistas contraditórias e análise estrutural'."
                    )
                ),
                goldTip = "No ENEM: Se o texto instruir o leitor a agir com comandos ou recomendações de saúde pública, a tipologia predominante é a injuntiva!"
            )

            "ling_sem_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Intertextualidade e Interdiscursividade

                    A comunicação nunca nasce do vácuo: todo texto dialoga com textos anteriores e com ideologias em circulação na sociedade.

                    ### 1. Intertextualidade (Relação entre Textos Concretos)
                    Ocorre quando um texto faz referência explícita ou implícita a outra obra:
                    • **Paródia:** Releitura subversiva e crítica, alterando o sentido original para produzir humor, ironia ou contestação política.
                    • **Paráfrase:** Reescrita que mantém o sentido e a ideologia da mensagem original com outras palavras (homenagem, confirmação ou reforço).
                    • **Epígrafe e Citação:** Menção direta entre aspas com indicação do autor original como argumento de autoridade.
                    • **Alusão:** Menção indireta a fatos históricos, bíblicos ou literários que exige repertório cultural compartilhado.

                    ### 2. Interdiscursividade (Diálogo entre Discursos e Ideologias)
                    É o cruzamento de vozes sociais no mesmo enunciado (discurso religioso, médico, jurídico e popular). Ocorre quando um anúncio de produto utiliza a estética de um laudo médico ou de um sermão religioso para persuadir.

                    ### 3. Atenção no ENEM
                    Muitas questões comparam a *Canção do Exílio* de Gonçalves Dias com poemas modernistas de Oswald de Andrade ou Murilo Mendes. Identifique se o texto recria reverenciando (paráfrase) ou subvertendo ironicamente (paródia).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Paródia", "Texto B altera o sentido de A com ironia/crítica", "Subversão crítica ou humorística"),
                    FormulaOrSyntaxItem("Paráfrase", "Texto B reescreve A preservando a mesma ideia central", "Reafirmação e manutenção do sentido"),
                    FormulaOrSyntaxItem("Interdiscursividade", "Apropriação de tons de voz/discursos de diferentes esferas", "Ex: Publicidade usando linguagem científica")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo Clássico: Canção do Exílio",
                        codeOrFormula = "Gonçalves Dias (nacionalismo ufanista) vs Oswald de Andrade (ironia modernista)",
                        explanation = "'Minha terra tem palmeiras onde canta o sabiá' (Romantismo) foi parodiado por Oswald: 'Minha terra tem palmares / onde gorjeia o mar'. A troca de palmeiras por palmares insere a questão racial e a crítica social."
                    )
                ),
                goldTip = "Se houver quebra de reverência e tom jocoso ou contestador em relação ao original, trata-se de paródia!"
            )

            "ling_sem_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Figuras de Linguagem e Recursos Expressivos

                    As figuras de linguagem são desvios intencionais da norma denotativa para gerar efeitos estéticos, persuasivos ou emocionais.

                    ### 1. Figuras de Palavras ou Pensamento Mais Cobradas
                    • **Metáfora:** Comparação implícita sem conectivo comparativo direto ('Seus olhos são dois faróis no nevoeiro').
                    • **Metonímia:** Substituição fundada na relação de proximidade lógica (o autor pela obra: 'Li Machado de Assis'; a parte pelo todo: 'Mil cabeças de gado'; o recipiente pelo conteúdo: 'Bebi um copo d'água').
                    • **Ironia:** Afirmação cujo sentido real é o oposto do literal, percebido pelo contexto e tom ('Que inteligência rara!', dito a quem cometeu um disparate).
                    • **Antítese:** Aproximação de palavras ou ideias de sentidos opostos sem que se anulem ('A vida e a morte; a tristeza e a alegria').
                    • **Paradoxo (Oxímoro):** Junção de ideias aparentemente contraditórias e irreconciliáveis na lógica formal ('Amor é fogo que arde sem se ver; é ferida que dói e não se sente').
                    • **Hipérbole:** Exagero intencional para dar ênfase ('Estou morrendo de sede').
                    • **Eufemismo:** Suavização de ideia desagradável ou dolorosa ('Ele partiu para o andar de cima').

                    ### 2. No ENEM: Foco no Efeito de Sentido
                    O ENEM nunca pede apenas 'qual é a figura'. A prova pergunta: **qual é o efeito expressivo** gerado pelo paradoxo ou pela ironia no contexto social do texto?
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Metáfora vs Comparação", "Metáfora não possui conectivo ('é uma fera'); Comparação possui ('como uma fera')", "Transposição semântica analógica"),
                    FormulaOrSyntaxItem("Antítese vs Paradoxo", "Antítese = Opostos coexistentes ('dia e noite'); Paradoxo = Contradição insolúvel ('ferida que dói sem doer')", "Complexidade semântica"),
                    FormulaOrSyntaxItem("Metonímia", "Parte/Todo, Continente/Conteúdo, Autor/Obra", "Relação de contiguidade e síntese")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Paradoxo em Camões e Poesia Moderna",
                        codeOrFormula = "'Um contentamento descontente'",
                        explanation = "A simultaneidade de estados opostos reflete a complexidade psicológica das contradições humanas perante o amor ou a existência."
                    )
                ),
                goldTip = "Lembre-se: Antítese opõe polos contrários possíveis. Paradoxo cria uma contradição lógica simultânea!"
            )

            "ling_sem_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Ambiguidade, Duplo Sentido e Humor em Tirinhas e Charges

                    Tirinhas, charges e cartoons são presenças garantidas em todas as edições do ENEM, unindo linguagem verbal e visual (multimodalidade).

                    ### 1. Polissemia e Duplo Sentido
                    • **Polissemia:** Propriedade de uma palavra possuir múltiplos sentidos conforme o contexto (ex: 'rede' de pesca, 'rede' social, 'rede' elétrica).
                    • **Duplo Sentido Intencional:** Recurso humorístico em que o enunciador explora a polissemia propositalmente para causar estranhamento ou crítica.
                    • **Ambiguidade Viciosa:** Defeito de redação que gera dúvida não intencional na interpretação da frase.

                    ### 2. A Construção do Humor e da Crítica
                    • **Quebra de Expectativa:** A tirinha constrói uma narrativa lógica nos primeiros quadros e introduz um elemento surpreendente no desfecho.
                    • **Ironia Visual e Não Verbal:** Expressões faciais, postura corporal, enquadramento e metáforas visuais (como lâmpada sobre a cabeça, fumaça indicando fúria).
                    • **Diferença entre Charge e Tirinha:** A charge é vinculada a um acontecimento político ou social contemporâneo específico (temporalmente datada); a tirinha foca nas relações humanas e cotidianas (atemporal).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Multimodalidade", "Linguagem Verbal (fala/legendas) + Linguagem Não Verbal (expressões, traços)", "Leitura integrada obrigatória"),
                    FormulaOrSyntaxItem("Quebra de Expectativa", "Premissa convencional -> Conclusão inesperada", "Mecanismo central do humor e reflexão crítica"),
                    FormulaOrSyntaxItem("Charge vs Cartum", "Charge = Fato político imediato datado | Cartum = Crítica a comportamentos universais", "Esfera de circulação jornalística")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Armandinho e Mafalda no ENEM",
                        codeOrFormula = "Inocência infantil revelando contradições do mundo adulto",
                        explanation = "A criança compreende uma expressão em seu sentido denotativo (literal), enquanto o adulto falava em sentido conotativo, expondo hipocrisias sociais."
                    )
                ),
                goldTip = "Em tirinhas do ENEM, nunca analise o texto verbal isolado da imagem: o olhar e a postura das personagens revelam o gabarito!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Estratégias Argumentativas e Progressão Temática

                    O texto dissertativo-argumentativo busca convencer o leitor mediante a sustentação de uma **tese** estruturada em argumentos legítimos.

                    ### 1. Tipos de Argumentos Válidos
                    • **Argumento de Autoridade:** Citação de pensadores, cientistas, filósofos ou instituições de pesquisa renomadas (ex: IBGE, OMS).
                    • **Argumento por Evidência / Dados Estatísticos:** Apresentação de dados empíricos e percentuais que comprovam a gravidade do problema.
                    • **Argumento por Exemplificação:** Narrativa de casos reais emblemáticos que ilustram o padrão defendido.
                    • **Argumento por Raciocínio Lógico (Causa e Consequência):** Demonstração da cadeia causal que conecta a origem ao impacto social.
                    • **Contra-argumentação:** Antecipação de objeção do oponente para refutá-la em seguida, demonstrando solidez crítica.

                    ### 2. Falácias Argumentativas (O que o ENEM condena)
                    • **Generalização Apressada:** Concluir uma regra universal a partir de um caso isolado.
                    • **Apelo à Emoção (Ad Misericordiam):** Substituir justificativas lógicas por chantagem emocional.
                    • **Ataque Pessoal (Ad Hominem):** Desqualificar o argumento atacando a pessoa que o formulou, e não a ideia.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Tese", "Juízo de valor ou posicionamento central a ser demonstrado", "Eixo orientador de todo o texto"),
                    FormulaOrSyntaxItem("Causa -> Consequência", "Origem estrutural -> Desdobramento no tecido social", "Fundamentação lógica robusta"),
                    FormulaOrSyntaxItem("Contra-argumentação", "Apresentar tese oposta + Refutação com evidência superior", "Persuasão avançada")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Estrutura do Argumento de Autoridade",
                        codeOrFormula = "Fato atual + Teoria sociológica de Zygmunt Bauman",
                        explanation = "A fragilidade das relações nas redes sociais é explicada pelo conceito de 'Modernidade Líquida', tornando o argumento irrefutável."
                    )
                ),
                goldTip = "No ENEM: Se o autor cita uma pesquisa do IPEA ou um sociólogo, sua intenção comunicativa é legitimar sua tese por autoridade ou evidência factual!"
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "ling_sem_01" -> listOf(
                MathExercise(
                    id = "ling_sem_01_q1",
                    title = "Tipologia e Gênero Textual no ENEM",
                    problemStatement = "Considere um folheto governamental com o título: 'Campanha Nacional de Vacinação contra a Gripe'. O texto traz: 'Dirija-se ao posto mais próximo de sua residência entre os dias 10 e 30 deste mês. Apresente seu cartão do SUS e vacine seus filhos com menos de 5 anos.' O predomínio tipológico e o objetivo comunicativo deste texto caracterizam-se por:",
                    options = listOf(
                        "narrativo, com objetivo de relatar a história da erradicação da gripe no país.",
                        "injuntivo, com objetivo de prescrever condutas preventivas e orientar a ação do cidadão.",
                        "dissertativo, com objetivo de debater as causas econômicas da produção de imunizantes.",
                        "descritivo, com objetivo de detalhar minuciosamente a fórmula bioquímica da vacina.",
                        "expositivo, com objetivo de apresentar conceitos acadêmicos neutros sobre virologia."
                    ),
                    correctIndex = 1,
                    hint = "Repare nos verbos 'dirija-se', 'apresente', 'vacine': são comandos dirigidos ao interlocutor.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. **Análise dos Verbos:** As formas 'dirija-se', 'apresente' e 'vacine' estão no modo imperativo, característico da tipologia injuntiva/instrucional.
                        2. **Função Social:** Campanhas de utilidade pública buscam intervir diretamente no comportamento social coletivo para adesão a uma medida de saúde.
                        3. **Por que as outras alternativas estão incorretas?**
                           • A (narrativo): não há enredo, personagens nem progressão temporal.
                           • C (dissertativo): o texto não debate teses filosóficas ou econômicas.
                           • D (descritivo): não há caracterização física de componentes da vacina.
                           • E (expositivo): vai além de expor fatos; conclama ativamente à vacinação.
                        Alternativa correta: B
                    """.trimIndent()
                ),
                MathExercise(
                    id = "ling_sem_01_q2",
                    title = "Gênero Notícia vs Artigo de Opinião",
                    problemStatement = "Em um jornal de grande circulação, a notícia e o artigo de opinião cumprem funções distintas. Enquanto a notícia preza pela impessoalidade e pelo relato objetivo de fatos, o artigo de opinião destaca-se por:",
                    options = listOf(
                        "adotar sempre a ficcionalização e o verso rimado para sensibilizar o público.",
                        "assumir autoria declarada com defesa explícita de um ponto de vista amparado em argumentos.",
                        "evitar qualquer adjetivação ou marca de subjetividade para manter neutralidade científica.",
                        "utilizar a primeira pessoa exclusivamente para narrar memórias infantis do articulista.",
                        "limitar-se à transcrição de dados do censo sem qualquer juízo de valor."
                    ),
                    correctIndex = 1,
                    hint = "O artigo de opinião traz a assinatura do articulista expressando seu julgamento crítico sobre um tema polêmico.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. O artigo de opinião pertence à esfera dissertativo-argumentativa com autoria explicitada, permitindo marcas de subjetividade e emprego de estratégias retóricas para convencer a sociedade sobre determinada tomada de posição.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_sem_02" -> listOf(
                MathExercise(
                    id = "ling_sem_02_q1",
                    title = "Paródia e Intertextualidade",
                    problemStatement = "Ao confrontar o célebre verso de Gonçalves Dias ('Minha terra tem palmeiras / Onde canta o sabiá') com o poema modernista de Oswald de Andrade ('Minha terra tem palmares / onde gorjeia o mar'), percebe-se um processo de intertextualidade marcado por:",
                    options = listOf(
                        "paráfrase, pois o autor moderno reforça o ufanismo ingênuo da era imperial.",
                        "paródia, pois a troca de 'palmeiras' por 'palmares' evoca a luta contra a escravidão e ressignifica a brasilidade criticamente.",
                        "plágio inconsciente, já que não há intenção de dialogar com o texto de Gonçalves Dias.",
                        "tradução intersemiótica, pois converteu um texto literário em linguagem puramente visual.",
                        "citação direta literal, preservando rigorosamente as rimas e métricas da poesia canônica."
                    ),
                    correctIndex = 1,
                    hint = "A referência ao Quilombo dos Palmares introduz crítica e ruptura com a visão romântica paradisíaca.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A alusão à palavra 'palmares' altera o foco ecológico e ufanista da palmeira para a dimensão histórica da resistência negra e do Quilombo dos Palmares.
                        2. Esse procedimento de subversão crítica do texto original configura paródia e desmistificação das narrativas oficiais.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_sem_03" -> listOf(
                MathExercise(
                    id = "ling_sem_03_q1",
                    title = "Identificação de Figuras de Linguagem",
                    problemStatement = "No soneto de Luís de Camões, os versos 'Amor é fogo que arde sem se ver; / É ferida que dói e não se sente; / É um descontentamento descontente;' estruturam sua força expressiva prioritariamente mediante o uso de:",
                    options = listOf(
                        "pleonasmo e elipse, que enxugam o texto até torná-lo incompreensível.",
                        "paradoxo, ao associar termos e sensações cujos significados lógicos se contradizem mutuamente no mesmo enunciado.",
                        "metonímia, ao trocar a parte pelo todo em todas as orações.",
                        "eufemismo, ao abrandar a tragédia do término de um relacionamento amoroso.",
                        "prosopopeia estrita, ao conceder voz humana aos animais da floresta."
                    ),
                    correctIndex = 1,
                    hint = "Sentir dor sem dor e ardor sem fogo visível são contradições lógicas simultâneas.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. Como uma ferida pode doer e não se sentir? Na lógica estrita, isso é uma contradição insolúvel.
                        2. A junção dessas ideias aparentemente inconciliáveis constitui o paradoxo (ou oxímoro), figura primordial para representar a complexidade das paixões humanas.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_sem_04" -> listOf(
                MathExercise(
                    id = "ling_sem_04_q1",
                    title = "Humor e Polissemia em Tirinhas",
                    problemStatement = "Em uma tirinha satírica, um náufrago em uma ilha deserta avista uma garrafa com uma mensagem trazida pelas ondas e comemora: 'Finalmente um contato com a civilização!'. Ao abrir o bilhete, lê: 'Sua fatura do cartão está vencida'. O humor e a crítica social da tirinha resultam de:",
                    options = listOf(
                        "uma descrição geográfica realista dos métodos de navegação marítima moderna.",
                        "quebra de expectativa, ao contrapor o anseio humano por socorro à onipresença da cobrança financeira nas sociedades de consumo.",
                        "emprego de linguagem arcaica de difícil compreensão para o leitor comum.",
                        "confirmação integral do final feliz esperado para um herói náufrago.",
                        "uso da ambiguidade não intencional gerada por erros ortográficos na mensagem da garrafa."
                    ),
                    correctIndex = 1,
                    hint = "Esperava-se socorro ou palavras humanas de acolhimento; veio uma cobrança bancária corporativa.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A expectativa construída é a chegada de auxílio humanitário ou conexão afetiva.
                        2. O desfecho revela a implacabilidade do sistema financeiro, que alcança o indivíduo até nos confins do planeta, constituindo sátira social por quebra de expectativa.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            else -> listOf(
                MathExercise(
                    id = "ling_sem_05_q1",
                    title = "Estratégia Argumentativa no ENEM",
                    problemStatement = "Em um ensaio sobre inclusão educacional, o autor escreve: 'De acordo com dados do Censo Escolar divulgados pelo Inep em 2023, mais de 1,2 milhão de estudantes com deficiência estão matriculados na rede pública, um avanço de 14% em quatro anos. Contudo, menos de 30% das escolas possuem rampas de acesso ou banheiros adaptados.' Ao combinar dados do Inep e a conjunção adversativa 'Contudo', o articulista mobiliza como estratégia:",
                    options = listOf(
                        "apelo emocional irracional sem fundamentação factual.",
                        "argumento por evidência em contraste adversativo, evidenciando o abismo entre o avanço de matrículas e o déficit estrutural de acessibilidade.",
                        "ataque pessoal aos diretores escolares para desqualificar suas gestões individuais.",
                        "generalização apressada baseada em relato anedótico de uma única cidade.",
                        "ironia sarcástica para ridicularizar as conquistas das pessoas com deficiência."
                    ),
                    correctIndex = 1,
                    hint = "Os dados dão sustentação confiável e a adversativa confronta a quantidade de alunos com a precariedade dos prédios.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A presença de estatísticas oficiais do Inep confere autoridade e factualidade à tese (argumento por evidência).
                        2. O conectivo 'Contudo' introduz um contraponto crítico crucial: ter matrícula garantida não significa ter cidadania espacial assegurada se faltam rampas e instalações físicas adequadas.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )
        }
    }
}
