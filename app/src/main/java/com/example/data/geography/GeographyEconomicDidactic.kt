package com.example.data.geography

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 4: Geografia Econômica e Globalização - ENEM.
 * Aulas:
 * 1. geo_eco_01: Fases do Capitalismo
 * 2. geo_eco_02: Globalização
 * 3. geo_eco_03: Divisão Internacional do Trabalho (DIT)
 * 4. geo_eco_04: Modelos Produtivos e Industrialização Brasileira
 */
object GeographyEconomicDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "geo_eco_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # As Fases do Capitalismo e a Transformação do Espaço

                    O sistema capitalista evoluiu historicamente ao longo de quatro grandes fases, alterando progressivamente a relação entre os Estados nacionais, os mercados globais e o espaço geográfico.

                    ### 1. Capitalismo Comercial ou Mercantil (Séculos XV a XVIII)
                    - **Contexto:** Grandes Navegações e colonização das Américas e costas da África e Ásia.
                    - **Lógica Econômica:** Acumulação primitiva de capitais baseada no comércio de especiarias, ouro, prata e produtos tropicais das plantations escravistas.
                    - **Doutrina:** Mercantilismo (metalismo, balança comercial favorável e protecionismo da Coroa através do pacto colonial).

                    ### 2. Capitalismo Industrial (Séculos XVIII e XIX)
                    - **Contexto:** Primeira e Segunda Revoluções Industriais (Inglaterra, França, Alemanha e EUA).
                    - **Lógica Econômica:** O motor da acumulação desloca-se do comércio para a produção industrial fabril e exploração do trabalho assalariado.
                    - **Doutrina:** Liberalismo econômico clássico (Adam Smith, livre concorrência e a teoria da 'mão invisível' do mercado).

                    ### 3. Capitalismo Financeiro ou Monopolista (Final do Séc. XIX até meados do Séc. XX)
                    - **Contexto:** Fusão do capital industrial com o capital bancário, originando gigantescas corporações e trustes.
                    - **Práticas Concorrenciais:** Formação de cartéis, monopólios, oligopólios e holdings.
                    - **Geopolítica:** Corrida imperialista e partilha colonial da África e da Ásia (Conferência de Berlim de 1884).

                    ### 4. Capitalismo Informacional ou Cognitivo (Pós-Segunda Guerra / Anos 1970 até hoje)
                    - **Contexto:** Terceira Revolução Industrial (Revolução Técnico-Científica-Informacional) e Quarta Revolução Industrial (Indústria 4.0).
                    - **Lógica Econômica:** O valor central reside no conhecimento, pesquisa e desenvolvimento (P&D), softwares, algoritmos, dados e finanças especulativas globais instantâneas.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Mercantilismo", "Metalismo + Balança Comercial Favorável + Pacto Colonial", "Primeira fase do capitalismo comercial"),
                    FormulaOrSyntaxItem("Capital Financeiro", "Capital Bancário + Capital Industrial", "Financiamento em bolsa de valores e fusões monopolistas"),
                    FormulaOrSyntaxItem("Capitalismo Informacional", "P&D + Chips + Dados digitais + Especulação em milissegundos", "Predomínio do setor terciário quaternário e inteligência artificial")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Diferença entre Cartel e Truste",
                        codeOrFormula = "Cartel (Acordo secreto de preços) vs Truste (Fusão de empresas concorrentes)",
                        explanation = "No cartel, empresas independentes combinam preços e cotas para eliminar a concorrência (ex: postos de gasolina); no truste, grandes companhias se fundem para dominar toda a cadeia de um setor."
                    )
                ),
                goldTip = "O meio geográfico evoluiu em três etapas segundo Milton Santos: Meio Natural -> Meio Técnico -> Meio Técnico-Científico-Informacional."
            )

            "geo_eco_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Globalização e Redes Globais: A Compressão do Espaço-Tempo

                    A globalização é o estágio contemporâneo de integração econômica, financeira, tecnológica e cultural do planeta, impulsionada pelo avanço das redes de transporte e telecomunicações.

                    ### 1. A Compressão Espaço-Tempo (David Harvey)
                    - A velocidade dos aviões a jato, dos navios porta-contêineres e a transmissão de dados por cabos submarinos de fibra óptica reduziram virtualmente as distâncias territoriais.
                    - 'A aldeia global': Eventos econômicos ou culturais em uma parte do mundo repercutem em tempo real no restante do globo.

                    ### 2. A Fragmentação Espacial da Produção
                    - As corporações transnacionais (multinacionais) deixaram de produzir o produto inteiro em uma única fábrica.
                    - **Fábrica Global:** O design e o desenvolvimento do produto ocorrem nos países sede ricos (EUA, Alemanha, Japão); a produção de peças e montagem é terceirizada para países asiáticos e periféricos em busca de mão de obra barata, incentivos fiscais e leis ambientais flexíveis; as vendas e publicidade operam no mundo inteiro.

                    ### 3. A Crítica de Milton Santos: A Globalização como Fábula e como Perversidade
                    - **A Globalização como Fábula:** O discurso das elites globais de que o mundo é uma aldeia conectada onde todos têm as mesmas oportunidades e liberdade de circulação.
                    - **A Globalização como Perversidade:** A realidade dos fatos: enquanto os capitais e mercadorias circulam livremente sem fronteiras, os seres humanos pobres têm sua circulação criminalizada por muros e xenofobia. Aumenta o desemprego estrutural e aprofunda-se a desigualdade entre Norte e Sul global.
                    - **Por uma Outra Globalização:** A possibilidade de utilizar as tecnologias de informação para a solidariedade e emancipação popular.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Compressão Espaço-Tempo", "Mais velocidade de circulação = Menos atrito da distância", "Conceito de David Harvey para a aceleração global"),
                    FormulaOrSyntaxItem("Desterritorialização", "Produção fragmentada em múltiplos países", "Busca incessante por menores custos operacionais"),
                    FormulaOrSyntaxItem("Globalização Perversa (Milton Santos)", "Livre circulação para o capital, muros para os refugiados", "Aprofundamento da exclusão e consumismo excludente")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Cadeia Global do Smartphone no ENEM",
                        codeOrFormula = "Design na Califórnia + Minerais na África + Montagem na Ásia + Lucro em paraísos fiscais",
                        explanation = "A fabricação de um celular moderno expressa com precisão a divisão global do trabalho contemporânea: alta tecnologia nos países desenvolvidos e superexploração de trabalho manual e recursos minerais na periferia."
                    )
                ),
                goldTip = "Cuidado com pegadinhas no ENEM: A globalização não produz homogeneização cultural absoluta; pelo contrário, o contato global frequentemente intensifica nacionalismos, fundamentalismos e a reafirmação de identidades locais."
            )

            "geo_eco_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Divisão Internacional do Trabalho (DIT)

                    A Divisão Internacional do Trabalho (DIT) designa a especialização produtiva dos países no mercado mundial e expressa a hierarquia de poder econômico entre nações ao longo da história.

                    ### 1. A DIT Clássica (Séculos XVI ao XIX)
                    - **Colônias e Países Periféricos:** Exportadores de matérias-primas, produtos agrícolas tropicais e metais preciosos.
                    - **Metrópoles e Países Centrais:** Importadores de matérias-primas e exportadores exclusivos de produtos manufaturados industriais com alto valor agregado.

                    ### 2. A DIT Tradicional da Guerra Fria (Pós-Segunda Guerra)
                    - Países subdesenvolvidos que iniciaram industrialização tardia por substituição de importações (como Brasil, México e Argentina).
                    - Países desenvolvidos forneciam maquinários pesados, tecnologias patenteadas e empréstimos financeiros em troca de matérias-primas e lucros repatriados de filiais multinacionais.

                    ### 3. A Nova Divisão Internacional do Trabalho (Nova DIT)
                    - **Países Desenvolvidos (Centro):** Controle acionário das transnacionais, geração de tecnologia de ponta, patentes, marcas e serviços financeiros globais.
                    - **Países Emergentes (como China, Tigres Asiáticos, Índia, México):** Grandes parques industriais voltados à exportação de produtos manufaturados e bens eletrônicos (mão de obra barata e disciplinada).
                    - **Países Periféricos e Primário-Exportadores (como grande parte da América do Sul e África):** Exportação massiva de commodities agropecuárias e minerais, sofrendo com a deterioração dos termos de troca quando os preços internacionais caem.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("DIT Clássica", "Colônias (Matérias-primas) vs Metrópoles (Manufaturas)", "Exploração mercantilista e colonial direta"),
                    FormulaOrSyntaxItem("Nova DIT", "Centro (P&D, Finanças, Patentes) vs Periferia/Emergentes (Produção física e Commodities)", "Divisão moderna de poder e lucro corporativo"),
                    FormulaOrSyntaxItem("Termos de Troca", "Preço das exportações dividido pelo preço das importações", "Deterioração histórica dos preços das matérias-primas")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Papel do Brasil na Nova DIT",
                        codeOrFormula = "Grande exportador de commodities (Soja, Ferro, Petróleo bruto e Carnes)",
                        explanation = "Apesar de possuir um parque industrial diversificado, a pauta de exportações do Brasil nas últimas décadas passou por um processo de reprimarização, concentrando suas vendas externas em produtos do agronegócio e extrativismo mineral."
                    )
                ),
                goldTip = "Reprimarização da economia: Aumento da participação de produtos primários (commodities agrícolas e minerais) na pauta de exportações em detrimento de produtos manufaturados industriais de alto valor agregado."
            )

            "geo_eco_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Modelos Produtivos Industriais e a Industrialização Brasileira

                    A organização fabril do trabalho evoluiu para maximizar a produtividade e a taxa de lucro, moldando diretamente os sindicatos e as classes operárias.

                    ### 1. Modelos Produtivos Clássicos
                    - **Taylorismo (Administração Científica):** Separação rigorosa entre concepção e execução do trabalho. Cronometragem dos movimentos do operário para eliminar 'tempos mortos'.
                    - **Fordismo (Produção e Consumo em Massa):** Introdução da esteira rolante mecanizada, operário ultraespecializado em uma única tarefa repetitiva, estoques gigantescos de peças e produtos idênticos e padronizados. Política salarial voltada à formação de um mercado consumidor de massa.
                    - **Toyotismo (Produção Flexível / Just-in-Time):** Desenvolvido no Japão pós-Segunda Guerra. Produção sob demanda (sem grandes estoques para não imobilizar capital), operário polivalente/multifuncional, controle rigoroso de qualidade (círculos de qualidade) e terceirização de etapas fabris.

                    ### 2. A Trajetória da Industrialização Brasileira
                    - **Fase Primário-Exportadora (Até 1930):** Economia do café. O café gerou infraestrutura portuária (Porto de Santos), ferrovias, imigração e o primeiro mercado consumidor de trabalhadores assalariados em São Paulo.
                    - **Era Vargas (1930–1945):** Industrialização por Substituição de Importações decorrente da quebra das economias centrais na Crise de 1929 e Segunda Guerra Mundial. Criação da indústria de base estatal: Companhia Siderúrgica Nacional (CSN), Vale do Rio Doce e fábrica de motores (FNM).
                    - **Plano de Metas de Juscelino Kubitschek (1956–1961):** Tripé econômico ('50 anos em 5'): capital estatal na infraestrutura e energia, capital nacional privado nos bens de consumo não duráveis e capital transnacional estrangeiro na indústria automobilística e química pesada.
                    - **Desconcentração Industrial Recente:** A partir da década de 1990, indústrias deixam o ABC paulista para fugir de altos salários e forte atuação sindical, migrando para o interior de SP, Sul e Nordeste atraídas por guerra fiscal (isenção de impostos) e mão de obra mais barata.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Fordismo", "Esteira rolante + Produção em massa + Estoque alto + Tarefa repetitiva", "Modelo padrão do século XX até a crise de 1973"),
                    FormulaOrSyntaxItem("Toyotismo (Just-in-Time)", "Produção sob demanda + Estoque zero + Operário multifuncional", "Flexibilização produtiva e terceirização contemporânea"),
                    FormulaOrSyntaxItem("Guerra Fiscal", "Disputa entre estados e municípios por fábricas", "Concessão de terrenos grátis e isenção tributária")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Toyotismo e Precarização do Trabalho",
                        codeOrFormula = "Flexibilização do trabalho = Menos direitos e mais terceirização",
                        explanation = "O ENEM aborda o toyotismo associando a exigência de trabalhador 'polivalente' à perda de estabilidade empregatícia, pejotização e aumento da rotatividade e ansiedade no ambiente fabril."
                    )
                ),
                goldTip = "O Brasil é classificado como um país de industrialização tardia ou retardatária, pois desenvolveu seu parque industrial mais de um século após a Inglaterra, valendo-se da importação de máquinas e patentes já desenvolvidas no exterior."
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Geografia Econômica e Globalização\nEstudo dos fluxos de capitais, cadeias de valor e organização da produção.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Conecte os modelos de produção às transformações no mercado de trabalho e aos direitos sociais."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "geo_eco_01" -> listOf(
                MathExercise(
                    id = "geo_ex_eco_01_a",
                    title = "ENEM: As Mudanças do Capitalismo Informacional",
                    problemStatement = "O capitalismo informacional ou cognitivo, consolidado a partir do final do século XX, distingue-se das fases industriais anteriores porque a geração de riqueza e a liderança de mercado deslocaram-se prioritariamente para:",
                    options = listOf(
                        "O controle de dados, pesquisa científica, desenvolvimento de softwares e serviços financeiros automatizados.",
                        "A extração manual de carvão mineral em minas subterrâneas para alimentar fornos a lenha.",
                        "A proibição do comércio internacional de patentes para estimular o artesanato doméstico.",
                        "O monopólio exclusivo do transporte marítimo por navios a vela de madeira.",
                        "A produção artesanal de tecidos de lã sem o uso de qualquer ferramenta mecânica."
                    ),
                    correctIndex = 0,
                    hint = "Pense no que move as maiores empresas do mundo hoje (Google, Apple, Microsoft, Nvidia): não são chaminés de aço, mas dados, chips e algoritmos.",
                    stepByStepSolution = "A alternativa A está correta. No capitalismo informacional, o conhecimento, a pesquisa e desenvolvimento (P&D), os fluxos de informação em tempo real e os algoritmos tornaram-se os principais vetores de produtividade e poder geopolítico mundial, superando a mera posse física de maquinários pesados da fase fordista."
                )
            )

            "geo_eco_02" -> listOf(
                MathExercise(
                    id = "geo_ex_eco_02_a",
                    title = "ENEM: A Crítica de Milton Santos à Globalização",
                    problemStatement = "Em sua obra seminal 'Por uma outra globalização', o geógrafo Milton Santos afirma que a globalização nos é apresentada como uma 'fábula', mas funciona concretamente como uma 'perversidade'. Essa perversidade manifesta-se principalmente pelo fato de que:",
                    options = listOf(
                        "Enquanto os fluxos de mercadorias e capitais financeiros circulam livremente pelo mundo, a circulação de trabalhadores pobres é barrada por fronteiras vigiadas e xenofobia.",
                        "Todos os países do mundo passaram a usufruir da mesma renda per capita e qualidade de vida.",
                        "A tecnologia digital extinguiu a pobreza em todos os países da África e da América Latina.",
                        "Os sindicatos operários assumiram o controle de todas as empresas multinacionais americanas.",
                        "A desigualdade social desapareceu completamente devido à expansão da internet de alta velocidade."
                    ),
                    correctIndex = 0,
                    hint = "Livre circulação para o dinheiro e produtos caros, mas repressão violenta e muros contra imigrantes e refugiados pobres.",
                    stepByStepSolution = "A alternativa A está correta. A crítica de Milton Santos expõe a assimetria da globalização: o discurso apregoa um mundo sem fronteiras para corporações, capitais especulativos e elites turísticas, mas impõe severas barreiras, muros de isolamento, precarização do trabalho e criminalização contra os trabalhadores e refugiados do Sul Global."
                )
            )

            "geo_eco_03" -> listOf(
                MathExercise(
                    id = "geo_ex_eco_03_a",
                    title = "ENEM: A Nova Divisão Internacional do Trabalho (Nova DIT)",
                    problemStatement = "Na Nova Divisão Internacional do Trabalho (Nova DIT), países em desenvolvimento e emergentes, como China e México, passaram a ocupar qual posição central na hierarquia da produção global?",
                    options = listOf(
                        "Plataformas fabris de manufatura voltadas à exportação de mercadorias de consumo e montagem industrial com custos baixos de produção.",
                        "Centros mundiais de emissão de moeda de reserva internacional e controle das patentes de inteligência artificial.",
                        "Economias exclusivamente de subsistência indígena sem nenhuma relação com o comércio exterior.",
                        "Monopolizadores exclusivos de todas as bolsas de valores e bancos centrais do G7.",
                        "Países importadores de lixo atômico europeu sem capacidade de produzir artigos manufaturados."
                    ),
                    correctIndex = 0,
                    hint = "Muitos países do Sul deixaram de exportar apenas soja ou ferro para se tornarem a 'fábrica do mundo', montando roupas, tênis, peças e eletrônicos para as marcas do Norte.",
                    stepByStepSolution = "A alternativa A está correta. Na Nova DIT, os países centrais concentram a concepção, o design, o marketing e o controle de patentes (alto valor agregado), enquanto países emergentes e periféricos recebem fábricas montadoras de transnacionais atraídas por baixos salários, incentivos tributários e facilidades de exportação."
                )
            )

            "geo_eco_04" -> listOf(
                MathExercise(
                    id = "geo_ex_eco_04_a",
                    title = "ENEM: A Lógica Produtiva do Toyotismo",
                    problemStatement = "O modelo de produção flexível toyotista, consolidado nas últimas décadas do século XX em substituição ao modelo fordista tradicional, tem como uma de suas características fundamentais:",
                    options = listOf(
                        "A produção just-in-time, voltada para atender à demanda imediata do mercado consumidor sem acumulação de estoques caros.",
                        "A obrigatoriedade de estocar peças suficientes para dez anos de produção ininterrupta.",
                        "A contratação de trabalhadores para desempenhar apenas uma única função mecânica durante toda a carreira.",
                        "A recusa em utilizar qualquer tipo de computador ou robô nas linhas de produção.",
                        "A proibição do controle de qualidade para acelerar o ritmo de saída dos produtos das fábricas."
                    ),
                    correctIndex = 0,
                    hint = "Just-in-time significa 'na hora certa': produz apenas o que já está vendido ou encomendado, evitando desperdício de capital em depósitos cheios de carros encalhados.",
                    stepByStepSolution = "A alternativa A está correta. O toyotismo opera na lógica da produção enxuta e flexível: a fábrica só adquire matéria-prima e produz os lotes de acordo com as encomendas reais do mercado (sistema just-in-time e kanban), exigindo trabalhadores polivalentes e eliminação de desperdícios operacionais."
                )
            )

            else -> emptyList()
        }
    }
}
