package com.example.data.geography

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 2: Geografia Agrária (O Espaço Rural) - ENEM.
 * Aulas:
 * 1. geo_agr_01: Estrutura Fundiária
 * 2. geo_agr_02: Sistemas Agrícolas
 * 3. geo_agr_03: A Revolução Verde
 * 4. geo_agr_04: Fronteiras Agrícolas e Conflitos
 */
object GeographyAgrarianDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "geo_agr_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Estrutura Fundiária e Concentração de Terras no Brasil

                    A questão agrária no Brasil é caracterizada por uma das maiores concentrações de terra do mundo, cujas raízes históricas remontam ao período colonial e foram consolidadas pelas legislações do século XIX.

                    ### 1. Evolução Histórica da Posse da Terra
                    - **Regime de Sesmarias (1530–1822):** Doação de imensas glebas de terra da Coroa portuguesa a fidalgos coloniais com capital para montagem de engenhos e currais de gado, inaugurando o latifúndio monocultor.
                    - **Período de Posse Livre (1822–1850):** Vazio legal após a Independência, onde a terra era apropriada pela simples ocupação de posseiros e grileiros.
                    - **Lei de Terras de 1850 (Lei nº 601):** Marco regulatório que determinou que as terras públicas (devolutas) só poderiam ser adquiridas por **compra em leilão público à vista**, proibindo o apossamento por ocupação.
                    - **Consequência Crucial no ENEM:** A Lei de Terras de 1850 foi promulgada no mesmo ano da extinção do tráfico negreiro (Lei Eusébio de Queirós) e bloqueou deliberadamente o acesso à terra para os futuros libertos e para os imigrantes europeus empobrecidos, transformando a terra em mercadoria inacessível aos trabalhadores e garantindo mão de obra cativa para os cafezais paulistas.

                    ### 2. O Índice de Gini da Estrutura Fundiária
                    - O Índice de Gini mede a desigualdade de uma distribuição (0 = igualdade perfeita, 1 = concentração máxima).
                    - No campo brasileiro, o Gini da terra permanece consistentemente acima de 0,85: cerca de 1% dos proprietários detém quase metade da área agrícola do país, enquanto milhões de pequenos agricultores disputam parcelas minúsculas.

                    ### 3. Estatuto da Terra (1964) e Módulo Fiscal
                    - Criado durante a Ditadura Militar como resposta às Ligas Camponesas de Francisco Julião no Nordeste.
                    - **Módulo Fiscal:** Unidade de medida agrária fixada pelo INCRA em hectares para cada município, correspondendo à área mínima necessária para o sustento de uma família em determinada região econômica.
                    - **Classificação dos Imóveis:**
                      • Minifúndio: Área inferior a 1 módulo fiscal (inviabilidade econômica familiar).
                      • Pequena e Média Propriedade: Entre 1 e 15 módulos fiscais.
                      • Latifúndio por Dimensão: Propriedade com área superior a 600 vezes o módulo fiscal.
                      • Latifúndio por Exploração: Terra de qualquer dimensão mantida improdutiva para especulação imobiliária.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Lei de Terras (1850)", "Terra só por compra à vista", "Impediu o acesso à terra por libertos e imigrantes pobres"),
                    FormulaOrSyntaxItem("Índice de Gini Fundiário", "Gini > 0,85 no Brasil", "Reflete concentração extrema de terras em mãos de latifundiários"),
                    FormulaOrSyntaxItem("Módulo Fiscal", "Área mínima para subsistência familiar", "Varia por município conforme produtividade e localização")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Especulação Imobiliária no Campo",
                        codeOrFormula = "Latifúndio por Exploração = Terra ociosa valorizando como reserva de capital",
                        explanation = "O ENEM frequentemente aborda a função social da propriedade rural prevista na Constituição de 1988: para ser imune à desapropriação para reforma agrária, a terra precisa ser produtiva e cumprir requisitos trabalhistas e ambientais."
                    )
                ),
                goldTip = "Grilagem de terras: Prática ilegal de envelhecer títulos de propriedade forjados em caixas com grilos para dar aspecto de antiguidade, apropriando-se criminosamente de terras públicas da União."
            )

            "geo_agr_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Sistemas Agrícolas: Agricultura Familiar vs Agronegócio

                    No espaço agrário brasileiro contemporâneo coexistem dois modelos socioeconômicos antagônicos, complementares e em constante disputa política e territorial.

                    ### 1. Agricultura Familiar e Camponesa
                    - **Base Social e Produtiva:** Propriedades de pequeno e médio porte onde a gestão e a mão de obra são predominantemente dos membros da família.
                    - **Papel Econômico Estratégico:** Segundo o Censo Agropecuário do IBGE, a agricultura familiar responde por mais de 70% dos postos de trabalho no campo e fornece a maior parte dos alimentos básicos consumidos na mesa brasileira (mandioca, feijão, leite, hortaliças, milho e carne suína/aves).
                    - **Práticas de Cultivo:** Maior diversidade de culturas (policultura), rotação de cultivos e preservação do solo, com menor dependência de pacotes químicos pesados.

                    ### 2. O Agronegócio (Agribusiness)
                    - **Base Produtiva:** Grandes latifúndios altamente capitalizados e mecanizados (Complexos Agroindustriais - CAIs).
                    - **Foco em Commodities:** Produção em larga escala voltada ao mercado internacional exportador (soja em grão, milho transgênico, café, cana-de-açúcar para etanol/açúcar, algodão e carne bovina).
                    - **Tecnologia Intensiva:** Utilização intensiva de tratores guiados por satélite (agricultura de precisão), drones, sementes transgênicas patenteadas e adubos químicos importados.
                    - **Impacto Social:** Baixa geração de empregos diretos por hectare em razão da intensa mecanização das colheitas, favorecendo a migração forçada para as periferias urbanas.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Agricultura Familiar", "Policultura + Mão de obra familiar + Segurança alimentar", "Alimentos para o mercado interno e geração de empregos"),
                    FormulaOrSyntaxItem("Agronegócio Exportador", "Monocultura + Capital intensivo + Balança comercial", "Produção de commodities para exportação global"),
                    FormulaOrSyntaxItem("Commodities", "Matérias-primas agropecuárias com preço em bolsa (dólar)", "Soja, milho, boi gordo, café, açúcar")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Segurança Alimentar vs Balança Comercial no ENEM",
                        codeOrFormula = "Produzir comida no prato vs Gerar superávit primário em dólar",
                        explanation = "A prova costuma confrontar a retórica do 'agro é tudo' com a realidade: enquanto o agronegócio infla as exportações e o PIB, a agricultura familiar garante que a população não passe fome com a produção de alimentos frescos."
                    )
                ),
                goldTip = "O agronegócio não atua isolado da indústria: o conceito de Complexo Agroindustrial (CAI) expressa as cadeias 'antes da porteira' (máquinas, fertilizantes, venenos) e 'depois da porteira' (frigoríficos, silos, tradings globais)."
            )

            "geo_agr_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # A Revolução Verde: Modernização e Dependência Tecnológica

                    Iniciada na década de 1960 nos Estados Unidos e expandida para os países periféricos com o discurso de 'acabar com a fome mundial', a Revolução Verde transformou as bases técnicas da agricultura mundial.

                    ### 1. Os Pilares do Pacote Tecnológico
                    - Sementes geneticamente selecionadas e organismos geneticamente modificados (OGMs / transgênicos).
                    - Maquinários agrícolas pesados (tratores, colheitadeiras combinadas e pulverizadores aéreos).
                    - Insumos químicos industriais: fertilizantes químicos sintéticos (NPK) e defensivos agrícolas (agrotóxicos / pesticidas).
                    - Irrigação artificial intensiva por pivô central.

                    ### 2. A Realidade no Brasil: A 'Modernização Conservadora'
                    - A tecnologia ingressou no Brasil durante a ditadura militar através de subsídios de crédito rural e fundação da EMBRAPA (1973).
                    - **Por que 'Conservadora'?** Porque modernizou o padrão técnico das grandes propriedades sem alterar a estrutura arcaica e concentrada de propriedade da terra.
                    - Em vez de acabar com a fome, a produção voltou-se para ração animal na Europa e na China (soja), concentrando ainda mais as terras e expulsando trabalhadores rurais (êxodo rural).

                    ### 3. Impactos Socioambientais da Revolução Verde
                    - **Perda de Biodiversidade:** Monoculturas homogêneas vulneráveis a superpragas.
                    - **Contaminação Hídrica e do Solo:** Lixiviação de agrotóxicos para os lençóis freáticos e eutrofização de rios.
                    - **Intoxicação de Comunidades:** Pulverização aérea de pesticidas sobre comunidades rurais, quilombolas e aldeias indígenas.
                    - **Dependência Biotecnológica:** Agricultores obrigados a pagar royalties anuais a multinacionais de sementes transgênicas (ex: sementes resistentes ao glifosato).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Modernização Conservadora", "Alta Tecnologia + Manutenção do Latifúndio", "Mudança na técnica sem reforma fundiária"),
                    FormulaOrSyntaxItem("Transgênicos (OGMs)", "Genes modificados para resistência a herbicidas", "Controle oligopolístico por transnacionais agroquímicas"),
                    FormulaOrSyntaxItem("Lixiviação de Agrotóxicos", "Chuva carrega veneno agrícola para os rios", "Contaminação da cadeia trófica e aquíferos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Crítica à Promessa da Revolução Verde",
                        codeOrFormula = "A fome é um problema político de acesso/renda, não de volume de produção",
                        explanation = "O ENEM destaca o pensamento de Josué de Castro e Amartya Sen: a fome decorre da desigualdade de distribuição da riqueza e da terra, e não da escassez biológica de calorias produzidas."
                    )
                ),
                goldTip = "O Brasil é um dos maiores consumidores mundiais de agrotóxicos em volume absoluto, muitos dos quais banidos na União Europeia por riscos carcinogênicos e mutagênicos."
            )

            "geo_agr_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Fronteiras Agrícolas e Conflitos no Campo Brasileiro

                    O avanço espacial da agropecuária industrializada sobre áreas de vegetação nativa define as chamadas 'fronteiras agrícolas', marcadas por tensões ecológicas e confrontos violentos por terra.

                    ### 1. A Marcha Histórica da Soja e do Gado
                    - Nas décadas de 1970 e 1980, com o desenvolvimento de técnicas de calagem pela EMBRAPA para corrigir a acidez do solo do Cerrado, a soja expandiu-se do Sul para o Centro-Oeste (Mato Grosso e Goiás).
                    - A pecuária extensiva atua como 'pioneira': desmata, queima a mata nativa, planta capim para gado de corte e, anos depois, vende a terra desmatada e valorizada para produtores de grãos.

                    ### 2. As Regiões de Conflito Atual no ENEM
                    - **Arco do Desmatamento:** Faixa que se estende do leste do Pará, norte de Mato Grosso, Rondônia e Acre, onde o bioma amazônico é pressionado pelo desmatamento ilegal, fogo e grilagem.
                    - **MATOPIBA:** Fronteira agrícola formada por partes do Maranhão, Tocantins, Piauí e Bahia. Área de Cerrado intensamente disputada por megaempresas transnacionais do agronegócio contra populações tradicionais (gerazeiros, quebradeiras de coco babaçu e posseiros).

                    ### 3. Conflitos Sociais e Violência no Campo
                    - Dados da Comissão Pastoral da Terra (CPT) registram anualmente centenas de assassinatos de lideranças camponesas, indígenas e ambientalistas (como Chico Mendes em 1988 e Dorothy Stang em 2005).
                    - **Marco Temporal:** Tese jurídica refutada pelo STF que tentava limitar as demarcações de terras indígenas apenas àquelas ocupadas na data da promulgação da Constituição de 1988, ignorando expulsões forçadas anteriores.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("MATOPIBA", "Maranhão + Tocantins + Piauí + Bahia", "Nova fronteira da soja e milho no Cerrado nordestino"),
                    FormulaOrSyntaxItem("Arco do Desmatamento", "Borda sul e leste da Amazônia", "Avanço da pecuária, garimpo ilegal e monocultura de grãos"),
                    FormulaOrSyntaxItem("Populações Tradicionais", "Indígenas, Quilombolas, Ribeirinhos, Quebradeiras de coco", "Uso coletivo e sustentável da terra contra a grilagem")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Papel dos Povos Tradicionais na Conservação",
                        codeOrFormula = "Terras Indígenas e Reservas Extrativistas são as maiores barreiras contra o desmatamento",
                        explanation = "Mapas de monitoramento por satélite (INPE) mostram que as áreas sob posse e demarcação de povos originários e quilombos apresentam índices de preservação florestal muito superiores a propriedades privadas vizinhas."
                    )
                ),
                goldTip = "O ENEM sempre aborda conflitos no campo sob a ótica dos Direitos Humanos, valorizando a dignidade das comunidades tradicionais, a legalidade das demarcações e o cumprimento da função social da terra."
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Geografia Agrária\nEstudo das relações de trabalho, produção e disputas pela terra no espaço rural.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Relacione o avanço da fronteira agrícola aos impactos ecológicos nos biomas Cerrado e Amazônia."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "geo_agr_01" -> listOf(
                MathExercise(
                    id = "geo_ex_agr_01_a",
                    title = "ENEM: A Lei de Terras de 1850 e a Concentração Fundiária",
                    problemStatement = "Promulgada no mesmo ano em que o Brasil extinguiu formalmente o tráfico transatlântico de escravizados (1850), a Lei de Terras (Lei nº 601) estabeleceu que as terras devolutas só poderiam ser adquiridas por compra em dinheiro à vista. O impacto social direto dessa legislação para a estrutura agrária brasileira foi:",
                    options = listOf(
                        "Bloquear o acesso dos trabalhadores livres, libertos e imigrantes pobres à propriedade legal da terra, assegurando mão de obra cativa para os latifúndios.",
                        "Garantir a divisão equitativa de todos os latifúndios entre as famílias camponesas sem terra.",
                        "Promover a desapropriação imediata de todas as fazendas cafeeiras do Vale do Paraíba.",
                        "Entregar as terras públicas aos povos indígenas do interior como forma de reparação histórica.",
                        "Extinguir os impostos sobre exportação de café e açúcar para estimular a agricultura familiar."
                    ),
                    correctIndex = 0,
                    hint = "Ao exigir compra à vista em leilão público, a lei proibiu a posse por ocupação e impediu que escravizados libertos e imigrantes pobres se tornassem pequenos proprietários.",
                    stepByStepSolution = "A alternativa A está correta. A Lei de Terras de 1850 foi concebida pela elite senhorial cafeeira para transformar a terra em mercadoria cara e inacessível às classes populares. Sem recursos para comprar terras em leilão, os trabalhadores empobrecidos ficavam subjugados ao trabalho assalariado ou de parceria nas grandes fazendas."
                )
            )

            "geo_agr_02" -> listOf(
                MathExercise(
                    id = "geo_ex_agr_02_a",
                    title = "ENEM: Agricultura Familiar e Segurança Alimentar",
                    problemStatement = "Dados sucessivos do Censo Agropecuário demonstram que, embora ocupe menos de um quarto da área agricultável do país, a agricultura familiar desempenha um papel socioeconômico vital no Brasil porque:",
                    options = listOf(
                        "Responde pela maioria dos postos de trabalho no campo e fornece a maior parte dos alimentos básicos consumidos pela população no mercado interno.",
                        "Concentra 95% do volume financeiro das exportações brasileiras de soja e minério de ferro.",
                        "Utiliza colheitadeiras autônomas guiadas por satélite com eliminação completa do emprego humano.",
                        "Opera exclusivamente com monoculturas de eucalipto voltadas à indústria de celulose internacional.",
                        "Recebe a totalidade dos subsídios estatais de crédito rural oferecidos pelo Plano Safra."
                    ),
                    correctIndex = 0,
                    hint = "Pense no prato de comida do brasileiro: feijão, arroz, mandioca, leite, hortaliças e ovos vêm prioritariamente da agricultura familiar e camponesa.",
                    stepByStepSolution = "A alternativa A está correta. A agricultura familiar e camponesa é o esteio da segurança alimentar do Brasil, respondendo pela maior fatia dos alimentos que abastecem os lares e feiras livres, além de fixar o homem no campo gerando a maioria absoluta dos empregos do meio rural."
                )
            )

            "geo_agr_03" -> listOf(
                MathExercise(
                    id = "geo_ex_agr_03_a",
                    title = "ENEM: A Modernização Conservadora da Revolução Verde",
                    problemStatement = "A introdução do pacote tecnológico da Revolução Verde no Brasil a partir dos anos 1970 é frequentemente descrita pelos geógrafos como uma 'modernização conservadora' porque:",
                    options = listOf(
                        "Promoveu intensa tecnificação e mecanização das fazendas sem alterar a estrutura desigual de concentração de terras.",
                        "Distribuiu terras para todos os camponeses e baniu o uso de agrotóxicos em território nacional.",
                        "Proibiu o cultivo de soja e milho no Centro-Oeste para proteger os cerrados e florestas.",
                        "Priorizou o cultivo de alimentos orgânicos com adubação exclusivamente natural.",
                        "Eliminou as multinacionais do setor de sementes e nacionalizou os frigoríficos."
                    ),
                    correctIndex = 0,
                    hint = "Modernizou a técnica (tratores, veneno, sementes transgênicas), mas conservou a estrutura social arcaica (o latifúndio concentrador).",
                    stepByStepSolution = "A alternativa A está correta. O termo 'modernização conservadora' designa a modernização da base técnica produtiva das lavouras (tratores, fertilizantes, defensivos químicos) mantendo intacta e até aprofundando a secular concentração fundiária e a exclusão social no campo."
                )
            )

            "geo_agr_04" -> listOf(
                MathExercise(
                    id = "geo_ex_agr_04_a",
                    title = "ENEM: Conflitos Territoriais na Região do MATOPIBA",
                    problemStatement = "A região do MATOPIBA (confluência de Maranhão, Tocantins, Piauí e Bahia) consolidou-se como a mais recente fronteira agrícola de expansão da soja e do milho no Brasil. Essa expansão tem gerado intensos conflitos territoriais motivados principalmente por:",
                    options = listOf(
                        "Apropriação de terras e avanço do agronegócio sobre territórios tradicionalmente ocupados por comunidades geraizeiras, quilombolas e posseiros.",
                        "Queda dos preços internacionais dos grãos que provocou o abandono total das lavouras pelos grandes produtores.",
                        "Disputas diplomáticas entre os quatro estados pela posse de jazidas de petróleo marítimo no interior do país.",
                        "Tentativa do governo federal de proibir a exportação de grãos produzidos fora do estado de São Paulo.",
                        "Migração em massa de fazendeiros da Europa que compraram todas as terras devolutas sem intermediários."
                    ),
                    correctIndex = 0,
                    hint = "O avanço de grandes corporações do agronegócio sobre o Cerrado pressiona populações que vivem há gerações na terra sem títulos formalizados em cartório.",
                    stepByStepSolution = "A alternativa A está correta. A expansão do agronegócio no MATOPIBA avança sobre áreas de chapada e vales do Cerrado, gerando desmatamento acelerado e conflitos contra comunidades tradicionais (geraizeiros, quilombolas e pequenos posseiros), que frequentemente sofrem ameaças e violência decorrentes da grilagem e pistolagem."
                )
            )

            else -> emptyList()
        }
    }
}
