package com.example.data.geography

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 3: Geografia Urbana e Demografia - ENEM.
 * Aulas:
 * 1. geo_urb_01: Dinâmica Populacional
 * 2. geo_urb_02: Movimentos Migratórios
 * 3. geo_urb_03: Processo de Urbanização
 * 4. geo_urb_04: Problemas Sociais e Infraestrutura
 */
object GeographyUrbanDemographyDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "geo_urb_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Dinâmica Populacional: Transição Demográfica e Pirâmides Etárias

                    A demografia no ENEM aborda as transformações estruturais no crescimento vegetativo, fecundidade, expectativa de vida e estrutura etária das sociedades.

                    ### 1. Fases da Transição Demográfica
                    - **Fase 1 (Pré-Transição):** Altas taxas de natalidade e mortalidade (população jovem com crescimento vegetativo lento e instável por epidemias e guerras).
                    - **Fase 2 (Explosão Demográfica / Transição Inicial):** Queda acentuada da mortalidade (avanços no saneamento, antibióticos e vacinas) mantendo a natalidade alta.
                    - **Fase 3 (Desaceleração do Crescimento):** Queda acelerada da taxa de fecundidade e natalidade, provocada pela urbanização, entrada da mulher no mercado de trabalho e métodos contraceptivos.
                    - **Fase 4 (Estabilização / Envelhecimento):** Baixa natalidade e baixa mortalidade. Crescimento vegetativo próximo a zero ou negativo.

                    ### 2. A Transição Demográfica no Brasil
                    - O Brasil passou de uma taxa de fecundidade de 6,3 filhos por mulher na década de 1960 para menos de 1,6 filho por mulher atualmente (abaixo da taxa de reposição populacional de 2,1).
                    - **Evolução da Pirâmide Etária Brasileira:**
                      • Década de 1970: Base larga (muitas crianças e jovens) e topo estreito (poucos idosos).
                      • Atualidade: Base em estreitamento rápido, corpo alargado (adultos) e topo em forte expansão (envelhecimento populacional acelerado).

                    ### 3. O 'Bônus Demográfico' (Janela de Oportunidade)
                    - Momento histórico em que a População em Idade Ativa (PIA - 15 a 64 anos) é proporcionalmente maior que a população dependente (crianças e idosos).
                    - **O Desafio no ENEM:** O bônus demográfico é temporário. Se o país não investir pesadamente em educação de qualidade, ciência e geração de empregos qualificados enquanto a maioria é jovem, enfrentará o envelhecimento populacional antes de atingir o desenvolvimento socioeconômico.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Crescimento Vegetativo", "CV = Taxa de Natalidade - Taxa de Mortalidade", "Saldo biológico da população sem considerar migrações"),
                    FormulaOrSyntaxItem("Taxa de Fecundidade", "Média de filhos por mulher em idade fértil (15-49 anos)", "Taxa de reposição populacional ideal = 2,1 filhos"),
                    FormulaOrSyntaxItem("Bônus Demográfico", "População ativa (15-64 anos) > População dependente", "Janela histórica ideal para investimento econômico e previdência")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Impacto do Envelhecimento Populacional no Brasil",
                        codeOrFormula = "Estreitamento da base + Alargamento do topo da pirâmide etária",
                        explanation = "O ENEM discute o envelhecimento demográfico relacionando-o à sustentabilidade da Previdência Social, aos custos do Sistema Único de Saúde (SUS) e à necessidade de novas políticas públicas de cuidados para a terceira idade."
                    )
                ),
                goldTip = "O Brasil não vive mais uma 'explosão demográfica'. Hoje o ritmo de crescimento da população brasileira é muito baixo e o país caminha para o início do declínio populacional absoluto antes de 2040."
            )

            "geo_urb_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Movimentos Migratórios e a Crise Global de Refugiados

                    As migrações humanas refletem desigualdades econômicas, transformações tecnológicas, conflitos bélicos e perseguições políticas e ambientais.

                    ### 1. Migrações Históricas Internas no Brasil
                    - **Nordestinos para o Sudeste (1950–1980):** O maior fluxo migratório interno do país. Motivado pelo êxodo rural decorrente das secas no Sertão, concentração fundiária e atração pela rápida industrialização em São Paulo e Rio de Janeiro.
                    - **A 'Marcha para o Oeste' (décadas de 1970–1990):** Migração de agricultores do Sul do Brasil para o Centro-Oeste e Norte para abertura da fronteira agrícola da soja e pecuária.
                    - **Construção de Brasília (1956–1960):** Os 'candangos' (trabalhadores predominantemente nordestinos) ergueram a nova capital no Planalto Central.

                    ### 2. Tendências Migratórias Contemporâneas no Brasil
                    - **Migração de Retorno:** Redução dos fluxos para o Sudeste e retorno de muitos migrantes nordestinos para suas regiões de origem, impulsionados pela desconcentração industrial e investimentos no Nordeste.
                    - **Crescimento das Cidades Médias:** Redução do crescimento das grandes metrópoles e dinamização de municípios do interior com melhor qualidade de vida.
                    - **Migração Pendular:** Deslocamento diário e regular de ida e volta entre o município de moradia e o município de trabalho/estudo em regiões metropolitanas.

                    ### 3. A Crise Global de Refugiados no ENEM
                    - **Diferença Conceitual Crucial:**
                      • Migrante Econômico: Desloca-se voluntariamente em busca de melhores condições de vida e trabalho.
                      • Refugiado (Convenção de Genebra de 1951): Pessoa que foge de seu país por fundado temor de perseguição por motivo de raça, religião, nacionalidade, grupo social, opinião política ou grave violação generalizada de direitos humanos.
                    - **Xenofobia e Muros no Século XXI:** Discursos de ódio e políticas anti-imigração em países desenvolvidos, criminalizando requerentes de asilo e construindo barreiras físicas e navais.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Migração Pendular", "Deslocamento diário casa-trabalho em metrópoles", "Não implica mudança definitiva de domicílio"),
                    FormulaOrSyntaxItem("Refugiado (ONU)", "Fuga por guerra, violência generalizada ou perseguição", "Possui direito ao princípio do 'non-refoulement' (não devolução)"),
                    FormulaOrSyntaxItem("Fuga de Cérebros", "Migração de cientistas e profissionais altamente qualificados", "Perda de capital intelectual da periferia para países centrais")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Migração de Refugiados Venezuelanos no Brasil",
                        codeOrFormula = "Operação Acolhida em Roraima e Interiorização",
                        explanation = "A chegada de milhares de venezuelanos pela fronteira de Pacaraima (RR) decorrente da crise política e econômica vizinha é analisada no ENEM sob os prismas da assistência humanitária, integração laboral e desafios da xenofobia."
                    )
                ),
                goldTip = "Transumância ou migração sazonal: Deslocamento periódico em que o trabalhador passa alguns meses em outra região (ex: corte de cana) e retorna para sua terra ao término da safra."
            )

            "geo_urb_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Processo de Urbanização: Redes Urbanas e Metropolização

                    A urbanização ocorre quando o crescimento da população urbana supera o crescimento da população rural, transformando o espaço geográfico em um meio predominantemente técnico e edificado.

                    ### 1. Fatores do Êxodo Rural e Urbanização Brasileira
                    - **Urbanização Rápida e Concentrada:** O Brasil passou de um país agrário na década de 1940 para um país com mais de 85% de sua população vivendo em cidades atualmente.
                    - **Fatores Repulsivos no Campo:** Mecanização das lavouras pela Revolução Verde, desemprego estrutural e concentração secular de terras.
                    - **Fatores Atrativos na Cidade:** Concentração das indústrias, comércio, serviços e a promessa de mobilidade social e acesso a hospitais e escolas.

                    ### 2. Conceitos Centrais da Rede Urbana
                    - **Hierarquia Urbana:** A relação de influência e dependência entre vilas, centros locais, centros sub-regionais, capitais regionais, metrópoles e metrópoles nacionais (São Paulo e Rio de Janeiro).
                    - **Conurbação:** Encontro e fusão física de duas ou mais cidades vizinhas decorrente de seu crescimento horizontal, compartilhando avenidas e serviços urbanos.
                    - **Metrópole e Região Metropolitana (RM):** Conjunto de municípios conurbados e integrados socioeconomicamente a uma cidade polo que lidera a prestação de serviços de alta complexidade.
                    - **Megacidade:** Critério estritamente quantitativo da ONU (aglomerações urbanas com população superior a 10 milhões de habitantes, ex: São Paulo, Tóquio, Mumbai).
                    - **Cidade Global (Metrópole Mundial):** Critério qualitativo de comando e poder financeiro na economia global (bolsas de valores, sedes de multinacionais, aeroportos internacionais, ex: São Paulo, Nova York, Londres).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Conurbação", "Integração física horizontal de duas ou mais cidades", "Cria manchas urbanas contínuas em regiões metropolitanas"),
                    FormulaOrSyntaxItem("Megacidade", "População urbana > 10 milhões de habitantes", "Critério exclusivamente demográfico da ONU"),
                    FormulaOrSyntaxItem("Cidade Global", "Pólo de poder e decisões na economia mundial", "Bolsas de valores, sedes transnacionais e conectividade")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Diferença entre Megacidade e Cidade Global",
                        codeOrFormula = "Megacidade = Muita gente (demográfico) vs Cidade Global = Muito comando (geopolítico/financeiro)",
                        explanation = "Daca (Bangladesh) tem mais de 20 milhões de habitantes e é uma megacidade, mas não é uma cidade global de primeiro nível; Frankfurt e Genebra têm menos de 1 milhão de habitantes, mas são cidades globais cruciais pelo poder bancário e diplomático."
                    )
                ),
                goldTip = "A urbanização nos países periféricos (como o Brasil) foi rápida, caótica e macrocefálica, enquanto nos países desenvolvidos ocorreu de forma mais lenta e planejada acompanhando a Revolução Industrial."
            )

            "geo_urb_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Problemas Sociais Urbanos e Infraestrutura

                    A rápida urbanização brasileira sem reformas estruturais aprofundou a segregação socioespacial e gerou graves déficits de infraestrutura pública.

                    ### 1. Segregação Socioespacial e a Cidade Bipartida
                    - **Periferização:** A população de baixa renda é empurrada para as bordas periféricas desprovidas de infraestrutura, saneamento básico, áreas de lazer e transporte público de massa.
                    - **Gentrificação:** Processo de reforma e valorização imobiliária de bairros históricos ou degradados que resulta na elevação dos custos de vida e aluguel, expulsando os moradores tradicionais pobres para dar lugar a classes abastadas e comércio gourmet.
                    - **Autossegregação:** Condomínios fechados e enclaves fortificados das elites que se isolam do tecido urbano público.

                    ### 2. Mobilidade Urbana e o Modelo Rodoviarista
                    - A opção histórica brasileira pelo transporte automotivo individual (carros) e ônibus em detrimento do transporte sobre trilhos (trens e metrôs) provocou congestionamentos crônicos, poluição atmosférica e perda diária de horas de vida da classe trabalhadora no trânsito.
                    - Desigualdade no tempo de deslocamento: trabalhadores da periferia gastam frequentemente mais de 3 a 4 horas por dia em transportes coletivos precários.

                    ### 3. Saneamento Básico e Moradia Digna
                    - Déficit habitacional crônico: milhões de famílias vivem em ocupações irregulares, favelas e encostas de morro sob risco iminente de deslizamento em temporais de verão.
                    - Esgoto a céu aberto e contaminação de mananciais, expondo as populações mais vulneráveis a doenças de veiculação hídrica (leptospirose, diarreia infecciosa, cólera).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Segregação Socioespacial", "Desigualdade social materializada no espaço da cidade", "Centro com serviços vs Periferia desassistida"),
                    FormulaOrSyntaxItem("Gentrificação", "Revitalização urbana com expulsão das classes populares", "Substituição do perfil socioeconômico de um bairro"),
                    FormulaOrSyntaxItem("Direito à Cidade (David Harvey)", "Acesso democrático e universal aos bens e serviços urbanos", "Reivindicação de transporte, moradia e espaços públicos de lazer")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Fenômeno da Gentrificação no ENEM",
                        codeOrFormula = "Modernização estética com exclusão social",
                        explanation = "A revitalização de áreas portuárias ou bairros operários valoriza o solo para o mercado imobiliário especulativo, forçando a saída das famílias originárias por incapacidade de pagar os novos aluguéis e tributos (IPTU)."
                    )
                ),
                goldTip = "O termo 'favelização' não deve ser associado à ilegalidade moral dos moradores, mas sim à ausência histórica do Estado em garantir habitação popular acessível e terra urbanizada."
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Geografia Urbana e Demografia\nEstudo da dinâmica das populações, cidades e infraestrutura.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Conecte os problemas urbanos à segregação socioespacial e ao modelo de desenvolvimento."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "geo_urb_01" -> listOf(
                MathExercise(
                    id = "geo_ex_urb_01_a",
                    title = "ENEM: O Bônus Demográfico e seus Desafios",
                    problemStatement = "O Brasil vivencia nas primeiras décadas do século XXI o chamado 'bônus demográfico', fase da transição demográfica caracterizada por:",
                    options = listOf(
                        "Maior proporção de pessoas em idade ativa e produtiva em relação ao número de crianças e idosos dependentes.",
                        "Aumento desenfreado da taxa de fecundidade que atingiu mais de 7 filhos por mulher.",
                        "Redução imediata e definitiva de todos os gastos públicos com saúde e educação básica.",
                        "Igualdade matemática perfeita entre o número de nascimentos e o número de óbitos em todas as capitais.",
                        "Extinção completa da população economicamente ativa devido ao avanço da inteligência artificial."
                    ),
                    correctIndex = 0,
                    hint = "O bônus demográfico acontece quando a maior parte da população está entre 15 e 64 anos, pronta para trabalhar e impulsionar a economia do país.",
                    stepByStepSolution = "A alternativa A está correta. O bônus demográfico (ou janela de oportunidade) é o período em que a razão de dependência cai ao seu nível mínimo, ou seja, a fatia da população em idade ativa (PIA - 15 a 64 anos) é significativamente maior do que a soma de crianças e idosos, criando condições ideais para a geração de riqueza e acúmulo previdenciário."
                )
            )

            "geo_urb_02" -> listOf(
                MathExercise(
                    id = "geo_ex_urb_02_a",
                    title = "ENEM: A Natureza da Migração Pendular",
                    problemStatement = "Em grandes regiões metropolitanas, como a de São Paulo, Rio de Janeiro e Belo Horizonte, milhões de trabalhadores realizam diariamente deslocamentos pendulares. Esse fenômeno é caracterizado por:",
                    options = listOf(
                        "Deslocamentos regulares de ida e volta entre o município de residência e o município de trabalho ou estudo, sem mudança definitiva de domicílio.",
                        "Mudança definitiva de cidade motivada exclusivamente por desastres ambientais e cheias de rios.",
                        "Viagens de férias turísticas realizadas por aposentados que se transferem para o litoral.",
                        "Migração forçada de trabalhadores que passam 6 meses no corte de cana e depois migram para a Europa.",
                        "Êxodo permanente de moradores das capitais para trabalhar exclusivamente no extrativismo da Amazônia."
                    ),
                    correctIndex = 0,
                    hint = "Pendular lembra o movimento do pêndulo do relógio: vai de manhã para o trabalho e volta à noite para casa na cidade dormitório.",
                    stepByStepSolution = "A alternativa A está correta. A migração pendular é o movimento diário ou periódico realizado por trabalhadores e estudantes que moram em municípios periféricos (muitas vezes denominados 'cidades dormitório') e se deslocam para os polos centrais da metrópole em busca de emprego ou formação, retornando ao final do dia para suas residências."
                )
            )

            "geo_urb_03" -> listOf(
                MathExercise(
                    id = "geo_ex_urb_03_a",
                    title = "ENEM: O Fenômeno Urbano da Conurbação",
                    problemStatement = "O intenso crescimento horizontal das metrópoles provocou em muitas regiões metropolitanas o fenômeno da conurbação. Esse conceito geográfico designa:",
                    options = listOf(
                        "A unificação física e espacial das áreas urbanas de dois ou mais municípios vizinhos em decorrência de sua expansão contínua.",
                        "A perda total de população de uma metrópole que é abandonada por seus moradores em direção ao campo.",
                        "A construção de muralhas divisórias entre bairros ricos e periferias sob fiscalização do exército.",
                        "A criação de capitais planejadas no interior do país para desconcentrar as indústrias litorâneas.",
                        "O aumento desordenado da taxa de natalidade exclusivo em áreas rurais isoladas."
                    ),
                    correctIndex = 0,
                    hint = "Pense no limite entre cidades vizinhas onde você cruza uma rua e já está no outro município sem perceber nenhuma área rural entre eles.",
                    stepByStepSolution = "A alternativa A está correta. Conurbação é o processo pelo qual a expansão urbana contínua de duas ou mais cidades vizinhas faz com que suas manchas urbanas se encontrem e se fundam territorialmente, tornando imperceptível a fronteira geográfica entre os municípios no dia a dia da população."
                )
            )

            "geo_urb_04" -> listOf(
                MathExercise(
                    id = "geo_ex_urb_04_a",
                    title = "ENEM: O Processo de Gentrificação Urbana",
                    problemStatement = "O processo de gentrificação, observado em diversas áreas centrais ou portuárias de metrópoles globais e brasileiras, tem como consequência social mais evidente:",
                    options = listOf(
                        "A valorização imobiliária que encarece o custo de vida no bairro e força a expulsão das famílias populares tradicionais para as periferias distantes.",
                        "A distribuição gratuita de apartamentos reformados para toda a população de baixa renda da cidade.",
                        "A proibição do tráfego de automóveis importados para incentivar o transporte hidroviário público.",
                        "A demolição total dos edifícios históricos para construção exclusiva de favelas populares.",
                        "A perda de investimentos públicos e o esvaziamento comercial permanente da região revitalizada."
                    ),
                    correctIndex = 0,
                    hint = "Revitalizar uma área sem política habitacional aumenta o valor do metro quadrado e dos aluguéis, expulsando os moradores pobres que não conseguem pagar os novos preços.",
                    stepByStepSolution = "A alternativa A está correta. A gentrificação ocorre quando uma intervenção urbanística valoriza esteticamente um bairro histórico ou degradado, atraindo empreendimentos imobiliários e comércio de elite. Com a alta exponencial dos aluguéis, preços de serviços e impostos, a população de menor renda residente histórica é forçada a deixar o local por não conseguir arcar com o novo custo de vida."
                )
            )

            else -> emptyList()
        }
    }
}
