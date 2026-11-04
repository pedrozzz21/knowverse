package com.example.data.geography

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 5: Geopolítica e Ordem Mundial - ENEM.
 * Aulas:
 * 1. geo_pol_01: A Ordem Mundial
 * 2. geo_pol_02: Blocos Econômicos
 * 3. geo_pol_03: Conflitos e Tensões Contemporâneas
 * 4. geo_pol_04: Disputas por Território e Recursos
 */
object GeographyGeopoliticsDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "geo_pol_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # A Ordem Mundial: Da Guerra Fria à Multipolaridade

                    A geopolítica mundial estuda as relações de poder, hegemonia, disputas territoriais e estratégias de influência entre os Estados nacionais na arena internacional.

                    ### 1. A Velha Ordem Mundial: A Bipolaridade da Guerra Fria (1945–1991)
                    - Divisão ideológica, militar e política do planeta em dois blocos rivais:
                      • Bloco Capitalista: Liderado pelos Estados Unidos (OTAN, Doutrina Truman, Plano Marshall).
                      • Bloco Socialista: Liderado pela União Soviética (Pacto de Varsóvia, COMECON).
                    - **Equilíbrio do Terror (MAD - Destruição Mútua Assegurada):** A posse de arsenais atômicos maciços impediu o confronto militar direto entre as superpotências, canalizando as tensões para guerras por procuração (Guerra da Coreia, Guerra do Vietnã, Guerra do Afeganistão).
                    - Queda do Muro de Berlim (1989) e desintegração da URSS (1991): Fim da bipolaridade.

                    ### 2. A Nova Ordem Mundial: Unimultipolaridade
                    - **Unipolaridade Militar:** Os Estados Unidos permanecem como a única superpotência bélica global indiscutível, com bases militares distribuídas por todos os oceanos e orçamento de defesa superior à soma das nações seguintes.
                    - **Multipolaridade Econômica e Geopolítica:** Emergência de múltiplos polos de poder econômico e comercial:
                      • União Europeia (euro e mercado integrado).
                      • Japão e Tigres Asiáticos.
                      • China (ascensão vertiginosa como potência industrial, comercial e tecnológica).
                      • Os BRICS (Brasil, Rússia, Índia, China, África do Sul e novos membros ampliados), defendendo a desdolarização do comércio e uma governança global menos ocidentalizada.

                    ### 3. A Divisão Norte-Sul
                    - Substituiu a antiga divisão Leste-Oeste da Guerra Fria.
                    - Linha Norte-Sul socioeconômica: Não segue a linha do Equador geográfico (Austrália e Nova Zelândia, embora no Hemisfério Sul geográfico, pertencem ao 'Norte desenvolvido').
                    - Separa os países desenvolvidos (ricos, exportadores de tecnologia e credores mundiais) dos países em desenvolvimento e subdesenvolvidos do Sul Global.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Ordem Bipolar", "EUA (Capitalismo / OTAN) vs URSS (Socialismo / Varsóvia)", "Equilíbrio do terror atômico e guerras por procuração"),
                    FormulaOrSyntaxItem("Unimultipolaridade", "Unipolar no poder bélico (EUA) + Multipolar na economia (China, UE, BRICS)", "Conceito que sintetiza o mundo pós-Guerra Fria"),
                    FormulaOrSyntaxItem("Linha Norte-Sul", "Divisão socioeconômica entre países ricos e Sul Global", "Critério de desenvolvimento, não de latitude física")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Ascensão Chinesa e a Nova Bipolaridade",
                        codeOrFormula = "Iniciativa Cinturão e Rota (Nova Rota da Seda) + Disputa Tecnológica (5G e semicondutores)",
                        explanation = "O ENEM discute a rivalidade contemporânea entre Washington e Pequim não como uma reedição ideológica da Guerra Fria soviética, mas como uma disputa por hegemonia comercial, rotas marítimas (Estreito de Malaca) e dominância em semicondutores e inteligência artificial."
                    )
                ),
                goldTip = "BRICS não é um bloco econômico no sentido técnico (não possui tarifa externa comum nem livre comércio formal entre todos); é um fórum político e econômico de cooperação estratégica de potências emergentes com seu próprio banco de fomento (Novo Banco de Desenvolvimento)."
            )

            "geo_pol_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Blocos Econômicos Regionais e a Integração dos Mercados

                    Como resposta à globalização e para fortalecer a competitividade das empresas nacionais, os países formaram blocos econômicos com diferentes níveis de integração.

                    ### 1. Os 4 Estágios de Integração Econômica
                    - **1. Zona de Livre Comércio:** Eliminação ou redução gradual de tarifas alfandegárias para mercadorias produzidas dentro do bloco. Cada país mantém sua tarifa externa independente para países de fora (Ex: USMCA / antigo NAFTA).
                    - **2. União Aduaneira:** Além do livre comércio interno, os países adotam uma **Tarifa Externa Comum (TEC)** para produtos importados de países de fora do bloco (Ex: MERCOSUL).
                    - **3. Mercado Comum:** Além de livre comércio e TEC, garante a livre circulação de pessoas, serviços e capitais (mão de obra e investimentos circulam sem visto ou barreiras bancárias).
                    - **4. União Econômica e Monetária:** Nível máximo de integração. Abriga todas as etapas anteriores, adota políticas fiscais e macroeconômicas harmonizadas, um Banco Central compartilhado e uma moeda única (Ex: União Europeia com a Zona do Euro).

                    ### 2. O MERCOSUL (Mercado Comum do Sul)
                    - Criado em 1991 pelo Tratado de Assunção por Brasil, Argentina, Paraguai e Uruguai.
                    - **Estágio Atual:** Embora tenha 'Mercado Comum' no nome, o Mercosul é tecnicamente uma **União Aduaneira imperfeita ou incompleta**, pois ainda possui exceções à Tarifa Externa Comum e não assegura plena integração de serviços e pessoas.
                    - **Importância para o Brasil:** Destino prioritário das exportações de produtos manufaturados industriais brasileiros de alto valor agregado (especialmente automóveis e máquinas para a Argentina).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Zona de Livre Comércio", "Tarifa zero entre os membros", "Ex: USMCA (EUA, Canadá, México)"),
                    FormulaOrSyntaxItem("União Aduaneira", "Livre Comércio + TEC (Tarifa Externa Comum)", "Ex: Mercosul"),
                    FormulaOrSyntaxItem("União Econômica e Monetária", "Mercado Comum + Banco Central + Moeda Única (Euro)", "Ex: União Europeia (estágio mais avançado do planeta)")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Impacto do Brexit na União Europeia",
                        codeOrFormula = "Saída do Reino Unido da UE (2020) = Crítica à perda de soberania e livre trânsito",
                        explanation = "O Brexit é cobrado no ENEM como manifestação de movimentos nacionalistas e eurocéticos contra a imigração comunitária e a submissão das leis britânicas às diretrizes do Parlamento Europeu em Bruxelas."
                    )
                ),
                goldTip = "O Mercosul é uma União Aduaneira, não um Mercado Comum pleno, porque ainda há disputas tarifárias e barreiras burocráticas à circulação de mão de obra e serviços entre os países membros."
            )

            "geo_pol_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Conflitos e Tensões Contemporâneas no Mundo

                    O pós-Guerra Fria não inaugurou uma era de paz perpétua, mas fragmentou as tensões em guerras civis, disputas étnico-religiosas, separatismos e conflitos interimperialistas.

                    ### 1. A Questão Palestina e o Oriente Médio
                    - **Origens:** Partilha da Palestina pela ONU (1947), criação do Estado de Israel (1948) e a *Nakba* (expulsão de centenas de milhares de civis palestinos).
                    - **Guerra dos Seis Dias (1967):** Israel ocupou militarmente a Cisjordânia, a Faixa de Gaza, Jerusalém Oriental e as Colinas de Golã sírias.
                    - **Pontos Centrais de Disputa no ENEM:**
                      • A expansão de assentamentos judaicos ilegais na Cisjordânia (que fragmentam o território palestino inviabilizando a Solução de Dois Estados).
                      • O controle de Jerusalém (sagrada para judeus, muçulmanos e cristãos).
                      • O direito de retorno dos refugiados palestinos.
                      • A disputa vital pelo controle dos aquíferos e das águas da bacia do Rio Jordão.

                    ### 2. O Conflito Rússia vs Ucrânia
                    - **Raízes Geopolíticas:** Expansão contínua da OTAN para o leste europeu após o colapso soviético, cercando as fronteiras ocidentais da Rússia.
                    - **Anexação da Crimeia (2014) e Invasão de 2022:** A Rússia busca impedir a entrada da Ucrânia na aliança militar ocidental e assegurar o controle do Mar Negro e de suas rotas gasíferas.
                    - **Impactos Globais:** Inflação mundial de alimentos (trigo e fertilizantes) e transição acelerada da matriz energética da Europa para substituir o gás russo.

                    ### 3. A Guerra Civil na Síria e a Crise Curda
                    - Primavera Árabe de 2011 e a desintegração do regime de Bashar al-Assad, com intervenção russa, iraniana e americana.
                    - **Os Curdos:** Maior nação sem Estado do mundo (mais de 30 milhões de pessoas espalhadas por Turquia, Síria, Iraque e Irã), historicamente perseguidos e combatentes centrais contra o Estado Islâmico (ISIS).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Solução de Dois Estados", "Criação de um Estado Palestino soberano ao lado de Israel", "Fórmula diplomática histórica da ONU para a paz no Oriente Médio"),
                    FormulaOrSyntaxItem("Curdos", "Povo sem Estado (Curdistão histórico)", "Mais de 30 milhões de pessoas espalhadas em quatro nações soberanas"),
                    FormulaOrSyntaxItem("OTAN vs Rússia", "Expansão da aliança militar ocidental para o Leste Europeu", "Estopim para o confronto territorial na Ucrânia")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Dimensão Hídrica no Conflito Israel-Palestina",
                        codeOrFormula = "Controle das águas do Rio Jordão e Aquífero das Montanhas",
                        explanation = "O ENEM destaca que o conflito no Oriente Médio não é apenas religioso ou territorial, mas envolve recursos escassos: Israel controla mais de 80% dos recursos hídricos da Cisjordânia, limitando o consumo palestino para privilegiar a irrigação agrícola em seus assentamentos."
                    )
                ),
                goldTip = "O que é uma nação sem Estado? Um grupo humano coeso com mesma identidade linguística, cultural e histórica, mas desprovido de soberania territorial e governo autônomo internacionalmente reconhecido (Ex: Curdos, Tibetanos, Bascos, Palestinos)."
            )

            "geo_pol_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Disputas Territoriais e Recursos Estratégicos Globais

                    No século XXI, as tensões geopolíticas estão diretamente conectadas à garantia de segurança energética, mineral, hídrica e alimentar das grandes potências industriais.

                    ### 1. Recursos Energéticos e Chokepoints Marítimos
                    - **O Petróleo como Arma Geopolítica:** O controle das reservas do Golfo Pérsico, Mar do Norte, Bacia de Santos (Pré-Sal) e Venezuela.
                    - **Chokepoints (Gargalos Estratégicos de Navegação):**
                      • Estreito de Ormuz: Por onde passa cerca de um quinto de todo o petróleo consumido no mundo diariamente.
                      • Estreito de Malaca: Ponto nevrálgico entre os oceanos Índico e Pacífico por onde transita o petróleo que abastece a China e o Japão.
                      • Canal de Suez e Canal do Panamá: Artérias vitais do comércio marítimo global que reduzem viagens continentais.

                    ### 2. Minerais Críticos e a Transição Energética
                    - A substituição dos combustíveis fósseis por baterias e carros elétricos desencadeou a 'corrida pelos minerais da transição':
                      • **Lítio ("O Ouro Branco"):** O Triângulo do Lítio na América do Sul (Bolívia, Argentina e Chile) e as reservas do Vale do Jequitinhonha em Minas Gerais.
                      • **Terras Raras:** Minerais indispensáveis para chips, ímãs de turbinas eólicas e mísseis teleguiados, cujo processamento mundial é amplamente monopolizado pela China (> 70%).
                      • **Cobalto e Níquel:** Riqueza mineral concentrada na República Democrática do Congo, marcada por conflitos armados e exploração de trabalho infantil.

                    ### 3. A Geopolítica da Água e Terras Estrangeiras (Land Grabbing)
                    - Aquisição ou arrendamento de milhões de hectares de terras agrícolas em países africanos e latino-americanos por fundos soberanos da China e dos Emirados Árabes para garantir sua segurança alimentar futura.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Chokepoint Marítimo", "Passagens marítimas estreitas com altíssimo fluxo comercial", "Ex: Estreito de Ormuz, Malaca, Canal de Suez"),
                    FormulaOrSyntaxItem("Triângulo do Lítio", "Bolívia + Argentina + Chile", "Maior concentração mundial de salares de lítio para baterias"),
                    FormulaOrSyntaxItem("Land Grabbing", "Estrangeirização e apropriação de terras agrícolas por governos externos", "Ameaça à soberania alimentar de países periféricos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "O Pré-Sal como Recurso Geopolítico Estratégico",
                        codeOrFormula = "Soberania energética nacional vs Modelo de concessão/partilha",
                        explanation = "A descoberta do Pré-Sal no litoral brasileiro transformou o país em exportador líquido de petróleo e motivou a discussão sobre o Fundo Social do Pré-Sal, destinado a financiar a educação pública e a saúde segundo a Lei nº 12.858/2013."
                    )
                ),
                goldTip = "O Estreito de Ormuz conecta o Golfo Pérsico ao Oceano Índico; qualquer ameaça de bloqueio militar iraniano no estreito gera imediata disparada no preço internacional do barril de petróleo (Brent)."
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Geopolítica e Ordem Mundial\nEstudo dos blocos econômicos, hegemonia e conflitos estratégicos internacionais.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Conecte os conflitos geopolíticos às disputas por recursos energéticos e rotas comerciais."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "geo_pol_01" -> listOf(
                MathExercise(
                    id = "geo_ex_pol_01_a",
                    title = "ENEM: A Natureza da Unimultipolaridade Contemporânea",
                    problemStatement = "A geopolítica mundial pós-Guerra Fria é frequentemente caracterizada pelos analistas como 'unimultipolar'. Esse conceito descreve uma ordem internacional na qual:",
                    options = listOf(
                        "Os Estados Unidos exercem hegemonia bélica e militar incontestada (unipolar), enquanto na economia e comércio o poder é dividido entre múltiplos polos como a União Europeia, China e BRICS (multipolar).",
                        "Todos os países do planeta possuem exatamente o mesmo número de ogivas nucleares e mísseis hipersônicos.",
                        "A Organização das Nações Unidas aboliu todos os exércitos nacionais e instituiu uma moeda única planetária.",
                        "A União Soviética recuperou o controle sobre todo o Leste Europeu e restabeleceu o Pacto de Varsóvia.",
                        "O comércio internacional de petróleo foi totalmente substituído pelo escambo de produtos agrícolas tradicionais."
                    ),
                    correctIndex = 0,
                    hint = "Unipolar no poder militar (EUA gastam mais que todos os outros juntos), mas multipolar na riqueza e comércio (China, UE, Japão, emergentes).",
                    stepByStepSolution = "A alternativa A está correta. A ordem unimultipolar sintetiza a contradição da geopolítica atual: no campo estratégico-militar, os EUA permanecem como superpotência solitária com alcance global sem paralelo; todavia, no campo econômico, financeiro e diplomático, o poder mundial é compartilhado com gigantes industriais e comerciais como a China, a União Europeia e as economias emergentes do Sul Global."
                )
            )

            "geo_pol_02" -> listOf(
                MathExercise(
                    id = "geo_ex_pol_02_a",
                    title = "ENEM: Os Níveis de Integração dos Blocos Econômicos",
                    problemStatement = "O MERCOSUL (Mercado Comum do Sul) e a União Europeia representam diferentes estágios do processo de integração regional de mercados. A diferença fundamental entre eles reside no fato de que:",
                    options = listOf(
                        "A União Europeia atingiu o estágio de união econômica e monetária com moeda única (euro) e livre circulação de pessoas, enquanto o Mercosul opera essencialmente como uma união aduaneira imperfeita com tarifa externa comum.",
                        "O Mercosul já unificou todos os bancos centrais e adotou uma moeda única obrigatória em toda a América do Sul.",
                        "A União Europeia é apenas uma zona de livre comércio onde cada país cobra impostos alfandegários diferentes sobre seus vizinhos.",
                        "Ambos os blocos extinguiram totalmente suas fronteiras políticas e transformaram-se em um único Estado nacional soberano.",
                        "O Mercosul proíbe a exportação de produtos agrícolas entre seus membros para não competir com a indústria paulista."
                    ),
                    correctIndex = 0,
                    hint = "A Europa tem o Euro, livre circulação de pessoas (Schengen) e Parlamento Europeu. O Mercosul tem a TEC (Tarifa Externa Comum), mas ainda não possui moeda única nem integração monetária plena.",
                    stepByStepSolution = "A alternativa A está correta. A União Europeia é o bloco mais integrado do planeta (União Econômica e Monetária), com livre circulação de capitais, mercadorias, serviços e cidadãos, além de um Banco Central compartilhado e a moeda comum (Euro). Já o Mercosul estacionou no estágio de União Aduaneira imperfeita, mantendo a Tarifa Externa Comum (TEC), mas com diversas listas de exceção nacional e sem unificação monetária."
                )
            )

            "geo_pol_03" -> listOf(
                MathExercise(
                    id = "geo_ex_pol_03_a",
                    title = "ENEM: A Condição do Povo Curdo no Oriente Médio",
                    problemStatement = "Os curdos, população estimada em mais de 30 milhões de pessoas distribuída territorialmente entre a Turquia, Síria, Iraque e Irã, constituem o exemplo clássico de:",
                    options = listOf(
                        "Uma nação sem Estado que luta há décadas por autonomia e pelo reconhecimento internacional de seu território soberano (o Curdistão).",
                        "Uma colônia europeia ultramarina que se recusa a adotar a religião islâmica para manter laços com a Coroa britânica.",
                        "Um povo nômade do deserto que não possui idioma próprio e depende exclusivamente do turismo religioso na Arábia Saudita.",
                        "Um Estado imperialista que invadiu os países vizinhos para construir ferrovias até o Mar Cáspio.",
                        "Um grupo terrorista financiado pela OTAN para desestabilizar todas as refinarias de petróleo do Catar."
                    ),
                    correctIndex = 0,
                    hint = "Nação sem Estado: possuem povo, língua, história e cultura compartilhadas, mas não possuem um país independente demarcado no mapa político.",
                    stepByStepSolution = "A alternativa A está correta. Os curdos representam a maior nação sem Estado soberano do mundo. Apesar de possuírem forte identidade cultural, idioma e coesão étnica milenar, suas terras natais foram retalhadas entre quatro Estados nacionais modernos no pós-Primeira Guerra Mundial, sofrendo constantes perseguições e repressão em suas reivindicações de autodeterminação."
                )
            )

            "geo_pol_04" -> listOf(
                MathExercise(
                    id = "geo_ex_pol_04_a",
                    title = "ENEM: Chokepoints Marítimos e a Geopolítica do Petróleo",
                    problemStatement = "O Estreito de Ormuz, passagem marítima estratégica que conecta o Golfo Pérsico ao Mar da Arábia e ao Oceano Índico, é considerado um dos pontos mais críticos da geopolítica mundial ('chokepoint') porque:",
                    options = listOf(
                        "Por suas águas estreitas transita diariamente cerca de 20% de todo o petróleo comercializado no mercado global, tornando o abastecimento das potências vulnerável a conflitos na região.",
                        "Concentra a totalidade das reservas de ouro e diamantes do continente africano sob tutela da marinha americana.",
                        "É a única passagem marítima aberta que permite o escoamento do gelo potável da Antártica para o hemisfério norte.",
                        "Abriga o canal artificial mais longo do mundo, construído para impedir que a marinha chinesa acesse o Mediterrâneo.",
                        "Constitui uma zona de proteção ambiental rigorosa onde é terminantemente proibida a navegação de qualquer navio a motor."
                    ),
                    correctIndex = 0,
                    hint = "Ormuz é a 'garganta' por onde saem os superpetroleiros da Arábia Saudita, Irã, Iraque, Emirados e Kuwait em direção aos mercados da Ásia, Europa e Américas.",
                    stepByStepSolution = "A alternativa A está correta. O Estreito de Ormuz é o gargalo (chokepoint) energético mais estratégico do planeta. Como grande parte do petróleo extraído nos países exportadores do Golfo Pérsico precisa necessariamente atravessar essa estreita faixa de água vigiada pelo Irã, qualquer bloqueio ou tensão militar tem o poder imediato de disparar as cotações globais de energia e causar desabastecimento internacional."
                )
            )

            else -> emptyList()
        }
    }
}
