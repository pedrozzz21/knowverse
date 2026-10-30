package com.example.data.geography

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 1: Geografia Física e Meio Ambiente (ENEM).
 * Aulas:
 * 1. geo_fis_01: Cartografia e Orientação
 * 2. geo_fis_02: Geomorfologia e Relevo
 * 3. geo_fis_03: Climatologia
 * 4. geo_fis_04: Hidrografia
 * 5. geo_fis_05: Questões Ambientais e Sustentabilidade
 */
object GeographyPhysicalDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "geo_fis_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Cartografia e Orientação: A Linguagem do Espaço

                    A cartografia no ENEM não é tratada como mera técnica de desenho, mas como um **instrumento de poder, comunicação e interpretação geopolítica**. Todo mapa é uma representação seletiva da superfície curva da Terra em um plano bidimensional, o que inevitavelmente produz distorções matemáticas e opções ideológicas.

                    ### 1. Escala Cartográfica: Proporção e Nível de Detalhamento
                    A escala estabelece a relação matemática entre a distância gráfica no mapa (d) e a distância real no terreno (D):
                    $$\text{Escala} = \frac{d}{D}$$

                    - **Grande Escala (ex: 1:500 a 1:25.000):** Denominador pequeno. Representa áreas menores (bairros, cidades, plantas cadastrais) com alto nível de detalhe e riqueza visual.
                    - **Pequena Escala (ex: 1:1.000.000 a 1:50.000.000):** Denominador gigantesco. Representa áreas imensas (países, continentes, planisférios) com baixo nível de detalhe, generalizando os traçados.

                    ### 2. Projeções Cartográficas: A Geopolítica da Representação
                    Como é matematicamente impossível planificar uma esfera sem deformá-la, cada projeção preserva uma propriedade geométrica sacrificando outras:
                    - **Projeção de Mercator (1569 - Conforme):** Preserva as formas exatas dos continentes, mas distorce severamente as áreas nas altas latitudes. Eurocêntrica, ampliou o Hemisfério Norte para favorecer as Grandes Navegações.
                    - **Projeção de Peters (1973 - Equivalente):** Preserva a proporção real das áreas territoriais, sacrificando a forma (continentes alongados verticalmente). Destaca o Terceiro Mundo e os países do Sul Global.
                    - **Projeção Azimutal / Polar (Equidistante):** O ponto focal é o polo (como no emblema da ONU). Muito cobrada no contexto da Guerra Fria para ilustrar rotas intercontinentais de mísseis e a proximidade entre EUA e URSS pelo Ártico.

                    ### 3. Fusos Horários e Coordenadas Geográficas
                    - A Terra completa uma rotação de 360° em aproximadamente 24 horas, resultando em 24 fusos horários de 15° de longitude cada.
                    - Movimento de Rotação (Oeste para Leste): a leste as horas adiantam (+), a oeste as horas atrasam (-).
                    - **Fusos Horários do Brasil:** O Brasil possui 4 fusos horários oficiais, todos a oeste de Greenwich (UTC-2, UTC-3 horário de Brasília, UTC-4 e UTC-5 no Acre e sudoeste do Amazonas).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Fórmula da Escala", "E = d / D", "d = distância no mapa (cm), D = distância real no terreno (cm)"),
                    FormulaOrSyntaxItem("Projeção Conforme (Mercator)", "Preserva Ângulos e Formas", "Distorce e hipertrofia áreas de altas latitudes"),
                    FormulaOrSyntaxItem("Projeção Equivalente (Peters)", "Preserva Áreas e Proporções", "Distorce as formas continentais (alongamento vertical)")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Cálculo de Escala Cartográfica no ENEM",
                        codeOrFormula = "Distância real = d * Denominador da Escala",
                        explanation = "Se um mapa tem escala 1:250.000 e a distância entre dois municípios mede 4 cm no papel, a distância real é 4 * 250.000 cm = 1.000.000 cm = 10 km."
                    )
                ),
                goldTip = "Macete ENEM: Denominador pequeno = Grande detalhe (planta de bairro). Denominador grande = Pouco detalhe (planisfério mundi)."
            )

            "geo_fis_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Geomorfologia e Relevo: A Estrutura da Litosfera

                    O relevo terrestre resulta da interação contínua entre forças endógenas (internas) que criam e erguem as formas e forças exógenas (externas) que modelam, desgastam e sedimentam.

                    ### 1. Tectônica de Placas e Agentes Internos
                    - **Limites Divergentes:** Separação de placas e formação de dorsais oceânicas (ex: Dorsal Mesoatlântica).
                    - **Limites Convergentes:** Colisão e subducção de placas litosféricas, gerando dobramentos modernos, fossas abissais e intenso vulcanismo/sismicidade (ex: Cordilheira dos Andes e Círculo de Fogo do Pacífico).
                    - **Limites Transformantes:** Deslizamento lateral entre placas (ex: Falha de San Andreas na Califórnia).

                    ### 2. Características Geológicas do Território Brasileiro
                    - **Estabilidade Tectônica:** O Brasil está localizado no centro da Placa Sul-Americana, longe de zonas ativas de borda de placa. Por isso não possui dobramentos modernos, vulcões ativos ou terremotos de grande magnitude.
                    - **Estruturas Geológicas:** Crátons/Escudos Cristalinos antigos (36% do território, ricos em minerais metálicos como ferro e manganês) e Bacias Sedimentares (64% do território, ricas em combustíveis fósseis como petróleo e gás).
                    - **Classificação do Relevo segundo Jurandyr Ross:** Dividido em Planaltos (processos de erosão superam sedimentação), Depressões (áreas rebaixadas em relação ao relevo circundante) e Planícies (processos de sedimentação superam a erosão).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Agentes Endógenos", "Tectonismo + Vulcanismo + Abalos Sísmicos", "Forças construtoras e formadoras do relevo"),
                    FormulaOrSyntaxItem("Agentes Exógenos", "Intemperismo + Erosão + Sedimentação", "Forças modeladoras e escultoras do relevo"),
                    FormulaOrSyntaxItem("Classificação de Ross", "Planaltos + Depressões + Planícies", "Critério morfoclimático e morfoestrutural brasileiro")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Origem dos Minerais no Brasil",
                        codeOrFormula = "Escudos Cristalinos (Minerais Metálicos) vs Bacias Sedimentares (Petróleo e Carvão)",
                        explanation = "O Quadrilátero Ferrífero (MG) e a Província Mineral de Carajás (PA) estão em terrenos cristalinos do Arqueozoico/Proterozoico, o que explica sua abundância de minério de ferro de alto teor."
                    )
                ),
                goldTip = "O Brasil não possui montanhas no sentido tectônico rigoroso (dobramentos modernos). Nossas formas são serras, chapadas e morros mamelonares (mar de morros) intensamente desgastados pela erosão milenar."
            )

            "geo_fis_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Climatologia: Dinâmica Atmosférica e Fenômenos Climáticos

                    O clima no ENEM exige a compreensão da circulação geral da atmosfera, das massas de ar e dos fatores geográficos que condicionam as temperaturas e chuvas.

                    ### 1. Elementos vs. Fatores do Clima
                    - **Elementos:** Grandezas meteorológicas variáveis (Temperatura, Umidade, Pressão Atmosférica, Precipitação).
                    - **Fatores:** Condições geográficas estáveis que atuam sobre os elementos (Latitude, Altitude, Maritimidade, Continentalidade, Correntes Marítimas, Relevo e Vegetação).

                    ### 2. As 5 Massas de Ar no Brasil
                    - **mEc (Equatorial Continental):** Quente e muito úmida (exceção à regra de massas continentais secas, devido à intensa evapotranspiração da Floresta Amazônica).
                    - **mEa (Equatorial Atlântica):** Quente e úmida, atua no litoral norte e nordestino.
                    - **mTa (Tropical Atlântica):** Quente e úmida, responsável pelas chuvas frontais e orográficas no litoral leste e sudeste.
                    - **mTc (Tropical Continental):** Quente e seca, originada na depressão do Chaco, associada aos veranicos no Centro-Sul.
                    - **mPa (Polar Atlântica):** Fria e úmida, causa quedas bruscas de temperatura, geadas no Sul, chuvas de inverno no litoral nordestino e a friagem na Amazônia Ocidental.

                    ### 3. Fenômenos ENOS: El Niño e La Niña
                    - **El Niño:** Aquecimento anômalo das águas superficiais do Oceano Pacífico Equatorial. Efeitos no Brasil: secas severas no Semiárido e norte da Amazônia; chuvas torrenciais e inundações no Sul.
                    - **La Niña:** Resfriamento anômalo das águas do Pacífico Equatorial. Efeitos no Brasil: aumento das chuvas no Norte/Nordeste e estiagens no Sul.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Chuva Orográfica (Relevo)", "Massa úmida sobe barreira -> condensa -> precipita", "Gera barlavento úmido e sotavento seco (sombra de chuva)"),
                    FormulaOrSyntaxItem("El Niño", "Aquecimento anormal do Pacífico Equatorial", "Seca no Norte/Nordeste, excesso de chuva no Sul do Brasil"),
                    FormulaOrSyntaxItem("La Niña", "Resfriamento anormal do Pacífico Equatorial", "Mais chuva no Norte/Nordeste, seca no Sul do Brasil")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "A Friagem Amazônica",
                        codeOrFormula = "Avanço da mPa pelos vales fluviais até a Amazônia ocidental",
                        explanation = "A massa Polar Atlântica sobe pelo interior do continente e atinge Rondônia e Acre, derrubando bruscamente a temperatura em regiões tipicamente equatoriais."
                    )
                ),
                goldTip = "Rios Voadores: A umidade bombeada pela evapotranspiração da Amazônia (mEc) encontra o paredão da Cordilheira dos Andes e é defletida para o Centro-Oeste e Sudeste, abastecendo as chuvas agrícolas e reservatórios hidrelétricos."
            )

            "geo_fis_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Hidrografia e Bacias Hidrográficas do Brasil

                    O Brasil abriga cerca de 12% da água doce superficial do planeta, caracterizando-se por rios de regime predominantemente pluvial tropical e drenagem exorreica (que deságua no oceano).

                    ### 1. Perfil Hidrográfico Brasileiro
                    - Predomínio de rios caudalosos de planalto (com grande potencial de geração hidrelétrica).
                    - Poucos rios navegáveis próximos aos grandes centros consumidores (necessidade de eclusas).
                    - Foz em estuário na maioria dos rios (com exceção do Rio Parnaíba em delta).

                    ### 2. Principais Bacias Hidrográficas
                    - **Bacia Amazônica:** Maior bacia hidrográfica do mundo em volume e extensão. Rio Amazonas de planície, mas com afluentes de planalto de alto potencial hidrelétrico (Belo Monte no Rio Xingu, Jirau e Santo Antônio no Rio Madeira).
                    - **Bacia do Tocantins-Araguaia:** Maior bacia inteiramente em território brasileiro. Abriga a Usina de Tucuruí e a Ilha do Bananal (maior ilha fluvial do mundo).
                    - **Bacia do Paraná (Platina):** Principal bacia em aproveitamento hidrelétrico e transporte hidroviário (Hidrovia Tietê-Paraná). Abriga Itaipu Binacional.
                    - **Bacia do São Francisco ("O Rio da Integração Nacional"):** Nasce na Serra da Canastra (MG) e atravessa o Polígono das Secas. É um rio perene crucial para geração de energia (Sobradinho, Xingó), irrigação de fruticultura (Petrolina/Juazeiro) e transposição hídrica.

                    ### 3. Águas Subterrâneas: Aquíferos Estratégicos
                    - **Aquífero Guarani:** Bacia sedimentar do Paraná, compartilhado por Brasil, Argentina, Paraguai e Uruguai.
                    - **Sistema Aquífero Grande Amazônia (SAGA/Alter do Chão):** Maior reserva de água subterrânea potável em volume do planeta.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Divisores de Águas", "Cadeias montanhosas que delimitam vertentes", "Orientam o escoamento superficial para bacias distintas"),
                    FormulaOrSyntaxItem("Transposição do São Francisco", "Eixos Norte e Leste", "Segurança hídrica para bacias receptoras intermitentes do Semiárido"),
                    FormulaOrSyntaxItem("Matriz Elétrica Brasileira", "Predomínio de fontes renováveis (Hídrica > 55%)", "Vulnerabilidade a crises hídricas e regimes de estiagem")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Impacto da Transposição do Rio São Francisco",
                        codeOrFormula = "Garantia hídrica urbana vs Conflitos de uso da água",
                        explanation = "O ENEM discute a transposição sob a perspectiva da democratização do acesso à água para consumo humano e dessedentação animal versus disputas com o agronegócio irrigado e impactos nos ecossistemas fluviais."
                    )
                ),
                goldTip = "Rios perenes nunca secam; rios intermitentes/temporários secam no período de estiagem severa. O Rio São Francisco é perene graças às chuvas regulares em suas cabeceiras em Minas Gerais."
            )

            "geo_fis_05" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Questões Ambientais e Sustentabilidade no ENEM

                    O tema ambiental é um dos mais interdisciplinares e frequentes do ENEM, conectando geografia física, biologia, química e cidadania ecológica.

                    ### 1. Impactos nos Biomas Brasileiros
                    - **Amazônia:** Arco do desmatamento (expansão da soja e pecuária bovina), garimpo ilegal com contaminação por mercúrio e grilagem de terras públicas.
                    - **Cerrado ("A Caixa D'água do Brasil"):** Savana mais biodiversa do mundo, devastada pela expansão do agronegócio em larga escala (região do MATOPIBA). Perda da capacidade de recarga hídrica dos aquíferos.
                    - **Mata Atlântica:** Reduzida a menos de 12% de sua cobertura original pela ocupação histórica litorânea e urbanização.
                    - **Caatinga:** Único bioma exclusivamente brasileiro, sob risco crescente de desertificação devido ao sobrepastoreio, desmatamento para lenha e práticas agrícolas inadequadas.

                    ### 2. Problemas Ambientais Urbanos
                    - **Ilhas de Calor:** Aumento da temperatura média nas áreas centrais das metrópoles provocado pelo concreto, asfalto, verticalização e escassez de áreas verdes.
                    - **Inversão Térmica:** Fenômeno meteorológico natural de inverno no qual uma camada de ar quente retém o ar frio próximo à superfície, aprisionando poluentes atmosféricos e agravando doenças respiratórias.
                    - **Enchentes e Inundações:** Agravadas pela impermeabilização excessiva do solo urbano e canalização de rios de planície.

                    ### 3. Aquecimento Global e Geopolítica do Clima
                    - Intensificação antrópica do efeito estufa pela queima de combustíveis fósseis e queimadas.
                    - Acordos Internacionais: Protocolo de Kyoto, Acordo de Paris (meta de 1,5°C) e transição energética para fontes renováveis (solar, eólica, biomassa e hidrogênio verde).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Ilha de Calor Urbana", "Asfalto + Concreto + Poluição + Menos Vegetação", "Elevação da temperatura térmica no centro metropolitano"),
                    FormulaOrSyntaxItem("Inversão Térmica", "Ar frio aprisionado na superfície por ar quente", "Bloqueio da dispersão vertical de fumaça e poluentes"),
                    FormulaOrSyntaxItem("Desertificação", "Clima árido/semiárido + Degradação do solo + Perda biológica", "Avanço da aridez irreversível no Semiárido brasileiro")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Diferença entre Efeito Estufa e Aquecimento Global",
                        codeOrFormula = "Efeito Estufa (Natural e vital) vs Aquecimento Global (Antrópico e nocivo)",
                        explanation = "Sem o efeito estufa natural, a Terra teria temperatura média de -18°C. O problema abordado no ENEM é a exacerbação antropogênica decorrente de emissões maciças de gases como dióxido de carbono e metano."
                    )
                ),
                goldTip = "Atenção máxima no ENEM: A inversão térmica é um fenômeno meteorológico natural e temporário de dias frios de inverno; o que o torna danoso na cidade é a presença massiva de poluentes que não conseguem subir e dispersar."
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Geografia Física e Meio Ambiente\nEstudo integrado do relevo, clima, águas e conservação ecológica.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Conecte os fenômenos físicos às atividades humanas e aos impactos ambientais."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "geo_fis_01" -> listOf(
                MathExercise(
                    id = "geo_ex_fis_01_a",
                    title = "ENEM: Análise Crítica de Projeções Cartográficas",
                    problemStatement = "A projeção de Mercator, concebida no século XVI durante a expansão marítima europeia, mantém os ângulos e as formas exatas dos continentes, mas apresenta distorções significativas de área. Do ponto de vista geopolítico e ideológico, essa projeção foi criticada no século XX porque:",
                    options = listOf(
                        "Hipertrofiou as massas continentais do Hemisfério Norte, reforçando uma visão eurocêntrica do mundo.",
                        "Subestimou o tamanho da Europa e da América do Norte em benefício dos continentes tropicais.",
                        "Eliminou a linha do Equador e os meridianos para dificultar as rotas de navegação comercial.",
                        "Representou a Antártica como o centro econômico e político do planeta Terra.",
                        "Inverteu os polos magnéticos para favorecer os países recém-descolonizados da África."
                    ),
                    correctIndex = 0,
                    hint = "Mercator preserva formas, mas exagera áreas nas proximidades dos polos (altas latitudes), fazendo a Groenlândia parecer maior que a América do Sul.",
                    stepByStepSolution = "A alternativa A está correta. A projeção cilíndrica conforme de Mercator preserva as formas e os ângulos para facilitar a navegação em linha reta, mas distorce desproporcionalmente as áreas conforme se afasta do Equador. Com isso, a Europa e as potências do Norte aparecem gigantescas em comparação aos países do Sul Global, consolidando uma perspectiva eurocêntrica."
                ),
                MathExercise(
                    id = "geo_ex_fis_01_b",
                    title = "ENEM: Relação entre Escala e Nível de Detalhe",
                    problemStatement = "Um pesquisador precisa analisar a distribuição dos lotes urbanos e das redes de saneamento de um determinado bairro, enquanto outro estuda as bacias hidrográficas em escala continental. Para suas respectivas pesquisas, os mapas mais adequados devem possuir:",
                    options = listOf(
                        "Grande escala para o estudo do bairro e pequena escala para o estudo continental.",
                        "Pequena escala para o estudo do bairro e grande escala para o estudo continental.",
                        "Escala 1:1.000.000 para o bairro e escala 1:1.000 para o continente.",
                        "A mesma escala reduzida em ambos os estudos para assegurar comparabilidade métrica.",
                        "Nenhuma escala numérica, utilizando apenas escalas gráficas sem unidades de medida."
                    ),
                    correctIndex = 0,
                    hint = "Grande escala = denominador menor = riqueza de detalhes (ideal para bairros). Pequena escala = denominador gigante = visão panorâmica (continentes).",
                    stepByStepSolution = "A alternativa A está correta. O estudo de um bairro exige grande riqueza de detalhes e pouco nível de redução (grande escala, ex: 1:5.000). Já a análise continental abrange uma área imensa e exige alta generalização gráfica com grande redução (pequena escala, ex: 1:5.000.000)."
                )
            )

            "geo_fis_02" -> listOf(
                MathExercise(
                    id = "geo_ex_fis_02_a",
                    title = "ENEM: Estrutura Geológica e Mineração no Brasil",
                    problemStatement = "As grandes jazidas de minério de ferro exploradas no Quadrilátero Ferrífero (Minas Gerais) e na Serra dos Carajás (Pará) estão geologicamente associadas a qual estrutura do relevo brasileiro?",
                    options = listOf(
                        "Escudos cristalinos e crátons de idade pré-cambriana, formados por rochas magmáticas e metamórficas antigas.",
                        "Bacias sedimentares jovens do período Terciário, ricas em hidrocarbonetos e xisto betuminoso.",
                        "Zonas de dobramentos modernos originadas pela convergência entre a Placa de Nazca e a Placa Sul-Americana.",
                        "Dorsais mesoceânicas de vulcanismo ativo localizadas no interior do continente.",
                        "Planícies aluviais litorâneas formadas exclusivamente por sedimentos marinhos recentes do Quaternário."
                    ),
                    correctIndex = 0,
                    hint = "Minerais metálicos (ferro, manganês, ouro) ocorrem em terrenos cristalinos muito antigos (Pré-Cambriano). Combustíveis fósseis (petróleo, carvão) ocorrem em bacias sedimentares.",
                    stepByStepSolution = "A alternativa A está correta. Os minerais metálicos encontram-se nos escudos cristalinos ou crátons (cerca de 36% do relevo brasileiro), formados nas eras Arqueozoica e Proterozoica há bilhões de anos, onde a atuação de calor e pressão metamorfoseou a litosfera originando expressivas jazidas minerais."
                )
            )

            "geo_fis_03" -> listOf(
                MathExercise(
                    id = "geo_ex_fis_03_a",
                    title = "ENEM: Mecanismo dos Rios Voadores e Clima no Brasil",
                    problemStatement = "A Floresta Amazônica desempenha papel determinante na dinâmica das chuvas nas regiões Centro-Oeste e Sudeste do Brasil através do fenômeno conhecido como 'Rios Voadores'. Esse mecanismo baseia-se fundamentalmente na:",
                    options = listOf(
                        "Elevada evapotranspiração da floresta que alimenta a massa Equatorial Continental (mEc), cujo vapor é bloqueado e defletido pela Cordilheira dos Andes.",
                        "Descarga subterrânea do Aquífero Alter do Chão que escoa diretamente para a bacia do Rio Tietê.",
                        "Infiltração de correntes marinhas frias vindas do Oceano Pacífico que transpõem os Andes em altas altitudes.",
                        "Atuação exclusiva da massa Tropical Continental (mTc), que carrega umidade do Deserto do Atacama para o Brasil.",
                        "Incineração de biomassa vegetal que produz núcleos de condensação artificiais em larga escala no outono."
                    ),
                    correctIndex = 0,
                    hint = "A floresta bombeia vapor d'água para a atmosfera (mEc). Esse vapor viaja para oeste, esbarra na Cordilheira dos Andes e faz uma curva em direção ao Centro-Sul.",
                    stepByStepSolution = "A alternativa A está correta. A massa de ar Equatorial Continental (mEc), aquecida e ultraúmida pela intensa evapotranspiração das árvores amazônicas, transporta gigantescos volumes de vapor d'água para o oeste. Ao encontrar a barreira orográfica dos Andes, o fluxo de ar é desviado para o Centro-Oeste e Sudeste, regulando o regime hídrico e a agricultura do país."
                )
            )

            "geo_fis_04" -> listOf(
                MathExercise(
                    id = "geo_ex_fis_04_a",
                    title = "ENEM: Importância do Rio São Francisco",
                    problemStatement = "O Rio São Francisco é historicamente denominado 'Rio da Integração Nacional'. Apesar de atravessar uma das áreas mais secas do país (o Polígono das Secas), ele mantém sua perenidade ao longo do ano inteiro porque:",
                    options = listOf(
                        "Suas nascentes estão situadas na Serra da Canastra (MG), sob o clima tropical úmido com altos índices de pluviosidade.",
                        "Recebe água canalizada do Rio Amazonas através de eclusas transcontinentais artificiais.",
                        "Seu leito está totalmente localizado em uma fossa tectônica com degelo regular no inverno.",
                        "Alimenta-se exclusivamente de água salobra vinda da evaporação do Oceano Atlântico em sua foz.",
                        "Atravessa áreas desérticas onde a evaporação superficial foi totalmente neutralizada por barramentos."
                    ),
                    correctIndex = 0,
                    hint = "As cabeceiras do São Francisco ficam em Minas Gerais, região com estações chuvosas fartas que alimentam o curso d'água antes de ele adentrar o Semiárido.",
                    stepByStepSolution = "A alternativa A está correta. O São Francisco é um rio perene alóctone (cruza o Semiárido sem secar) porque suas cabeceiras e principais afluentes da margem esquerda drenam áreas de relevo planáltico em Minas Gerais, caracterizadas por chuvas abundantes e regulares no verão."
                )
            )

            "geo_fis_05" -> listOf(
                MathExercise(
                    id = "geo_ex_fis_05_a",
                    title = "ENEM: Dinâmica da Inversão Térmica nas Cidades",
                    problemStatement = "Em grandes centros urbanos industrializados, nas manhãs frias de inverno, verifica-se com frequência o fenômeno da inversão térmica. Esse fenômeno gera graves problemas de saúde pública para a população urbana porque:",
                    options = listOf(
                        "Uma camada superior de ar quente bloqueia a ascensão do ar frio superficial, aprisionando poluentes tóxicos junto ao nível do solo.",
                        "O aumento descontrolado dos ventos alísios dispersa os poluentes para as áreas rurais vizinhas.",
                        "A umidade relativa do ar atinge 100%, dissolvendo o asfalto e gerando gases de combustão espontânea.",
                        "A radiação ultravioleta é totalmente extinta, provocando o resfriamento permanente de todos os edifícios.",
                        "A pressão atmosférica cai a zero, impedindo a oxigenação dos pulmões e a circulação de pedestres."
                    ),
                    correctIndex = 0,
                    hint = "Normalmente o ar frio fica em cima e o quente embaixo (que sobe). Na inversão térmica, o ar frio fica preso embaixo com a poluição, sob uma tampa de ar quente.",
                    stepByStepSolution = "A alternativa A está correta. A inversão térmica ocorre quando a superfície esfria rapidamente à noite e resfria a camada inferior de ar. O ar frio, sendo mais denso, não consegue subir, enquanto a camada superior mais quente atua como uma 'tampa', impedindo a convecção e aprisionando material particulado e gases tóxicos na troposfera inferior."
                )
            )

            else -> emptyList()
        }
    }
}
