package com.example.data.biology

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor Didático Completo de Biologia ENEM (Módulo 1: Ecologia).
 * Fornece teoria em Markdown, pontos-chave, ilustrações offline e atividades estilo ENEM.
 */
object EnemBiologyDidacticProvider {

    fun getDidacticBundle(lessonId: String): CalculusDidacticBundle? {
        val entry = didacticMap[lessonId] ?: return null
        return CalculusDidacticBundle(
            theoryMarkdown = entry.theoryMarkdown,
            formulas = entry.formulas,
            solvedExamples = entry.solvedExamples,
            imageResId = entry.imageResId,
            goldTip = entry.goldTip
        )
    }

    fun getExercisesForLesson(lessonId: String): List<MathExercise> {
        return exercisesMap[lessonId] ?: emptyList()
    }

    private val exercisesMap: Map<String, List<MathExercise>> by lazy {
        mapOf(
            "bio_eco_01" to listOf(
                MathExercise(
                    id = "bio_eco_01_q1",
                    title = "Partição de Recursos e Nicho Ecológico",
                    problemStatement = "Em uma floresta tropical convivem duas espécies de aves frugívoras de tamanho semelhante. A espécie A forrageia exclusivamente nas primeiras horas da manhã na copa das árvores mais altas, enquanto a espécie B alimenta-se ao entardecer nos estratos arbustivos médios e inferiores da floresta. Essa partilha de recursos evita a extinção de uma das espécies e é explicada porque:",
                    options = listOf(
                        "ambas as espécies compartilham o mesmo nicho ecológico, anulando a competição.",
                        "as duas espécies habitam ecossistemas diferentes dentro do mesmo bioma.",
                        "a partição temporal e espacial dos recursos resulta em nichos ecológicos distintos.",
                        "a seleção natural eliminou completamente a necessidade de alimentação de ambas.",
                        "o habitat diferente impede qualquer tipo de interação ecológica entre elas."
                    ),
                    correctIndex = 2,
                    hint = "Lembre-se do Princípio de Gause: nichos diferentes permitem coexistência no mesmo habitat.",
                    stepByStepSolution = "As espécies partilham o mesmo habitat, mas exploram horários e estratos florestais diferentes, caracterizando nichos ecológicos distintos e diminuindo a competição direta."
                ),
                MathExercise(
                    id = "bio_eco_01_q2",
                    title = "Critérios de Hotspots de Biodiversidade",
                    problemStatement = "O Cerrado brasileiro é considerado um dos hotspots de biodiversidade mundiais. Para receber essa classificação internacional da Conservation International, uma região geográfica necessita obrigatoriamente apresentar:",
                    options = listOf(
                        "apenas grande extensão territorial contínua sem ocupação humana.",
                        "alto grau de endemismo de espécies e perda crítica de pelo menos 70% da vegetação original.",
                        "ausência total de espécies ameaçadas de extinção em seu inventário biológico.",
                        "clima equatorial úmido com chuvas torrenciais bem distribuídas o ano inteiro.",
                        "exclusividade na presença de grandes mamíferos carnívoros no topo da cadeia trófica."
                    ),
                    correctIndex = 1,
                    hint = "Hotspot une riqueza endêmica excepcional com ameaça de destruição severa.",
                    stepByStepSolution = "A definição científica exige no mínimo 1.500 espécies de plantas vasculares endêmicas e perda acumulada de ao menos 70% da cobertura vegetal nativa."
                ),
                MathExercise(
                    id = "bio_eco_01_q3",
                    title = "Escalas de Organização em Ecologia",
                    problemStatement = "Um pesquisador coletou amostras de solo, água, insetos herbívoros, fungos decompositores e mediu a taxa de insolação e temperatura em uma clareira de mata de galeria. Ao integrar as características dos seres vivos com as variáveis físico-químicas monitoradas, a escala ecológica investigada corresponde a um(a):",
                    options = listOf(
                        "população biológica.",
                        "comunidade clímax.",
                        "ecossistema.",
                        "biocenose aquática.",
                        "nicho reprodutivo."
                    ),
                    correctIndex = 2,
                    hint = "Comunidade (biótico) + Fatores Físico-Químicos (abiótico) = ?",
                    stepByStepSolution = "A interação funcional entre a comunidade biológica (biótico) e o ambiente físico-químico circundante (abiótico) define o conceito de ecossistema."
                )
            ),
            "bio_eco_02" to listOf(
                MathExercise(
                    id = "bio_eco_02_q1",
                    title = "Bioacumulação de Mercúrio na Teia Trófica",
                    problemStatement = "Em um ecossistema aquático continental contaminado por resíduos de mercúrio metálico proveniente de garimpo ilegal, foram coletadas amostras de fitoplâncton, zooplâncton, pequenos peixes onívoros, peixes carnívoros de médio porte e aves piscívoras de grande porte que nidificam no topo da margem. Em qual desses grupos de organismos será encontrada a maior concentração de mercúrio por grama de tecido?",
                    options = listOf(
                        "No fitoplâncton, por absorver a água diretamente.",
                        "No zooplâncton, por ser o primeiro consumidor da cadeia.",
                        "Nos pequenos peixes, devido ao consumo de zooplâncton abundante.",
                        "Nos peixes carnívoros, pela metabolização acelerada.",
                        "Nas aves piscívoras de grande porte, por ocuparem o topo da teia trófica."
                    ),
                    correctIndex = 4,
                    hint = "Na magnificação trófica, a concentração de poluente lipossolúvel cresce ao longo dos níveis tróficos.",
                    stepByStepSolution = "O mercúrio é um poluente não biodegradável e cumulativo. Cada nível trófico consome grande quantidade de biomassa do nível anterior, resultando em concentração máxima no topo (aves piscívoras)."
                ),
                MathExercise(
                    id = "bio_eco_02_q2",
                    title = "Fluxo Unidirecional de Energia vs. Cíclico de Matéria",
                    problemStatement = "Ao analisar o fluxo de matéria e energia em uma pradaria nativa, um biólogo afirmou: 'A energia e a matéria circulam de modo idêntico pelas cadeias tróficas, sendo continuamente recicladas pelos fungos e bactérias decompositores'. Do ponto de vista termodinâmico e ecológico, essa afirmação está:",
                    options = listOf(
                        "correta, pois toda energia perdida em forma de calor é reabsorvida pelos produtores.",
                        "incorreta, pois a matéria é cíclica, mas a energia tem fluxo unidirecional e decrescente.",
                        "correta, já que os decompositores transformam o calor emitido em novas moléculas de ATP.",
                        "incorreta, porque os produtores absorvem matéria orgânica diretamente do ar atmosférico.",
                        "incorreta, pois apenas a energia é conservada nos ecossistemas, enquanto a matéria desaparece."
                    ),
                    correctIndex = 1,
                    hint = "A matéria é reaproveitada pelos produtores; o calor dissipado não retorna à fotossíntese.",
                    stepByStepSolution = "A matéria circula de forma cíclica (decompositores mineralizam compostos para os produtores). A energia dissipa-se na forma de calor metabólico a cada nível (unidirecional e decrescente)."
                ),
                MathExercise(
                    id = "bio_eco_02_q3",
                    title = "Cascata Trófica por Perda de Predador-Chave",
                    problemStatement = "Em uma represa de abastecimento, a remoção acidental de uma espécie de peixe carnívoro de topo provocou um aumento descontrolado na população de peixes herbívoros, o que levou a uma redução drástica das macrófitas aquáticas e ao colapso de pequenos invertebrados. Esse efeito em cascata que desestabiliza vários níveis tróficos denomina-se:",
                    options = listOf(
                        "eutrofização antrópica artificial.",
                        "sucessão ecológica primária acelerada.",
                        "efeito cascata trófico por perda de predador-chave.",
                        "irradiação adaptativa simpatricial.",
                        "exclusão competitiva intraespecífica."
                    ),
                    correctIndex = 2,
                    hint = "Predadores de topo exercem controle 'top-down' sobre a cadeia alimentar.",
                    stepByStepSolution = "A perda do predador-chave desregula a densidade populacional das presas intermediárias, propagando desequilíbrios em cascata por toda a teia trófica."
                )
            ),
            "bio_eco_03" to listOf(
                MathExercise(
                    id = "bio_eco_03_q1",
                    title = "Epifitismo e Inquilinismo",
                    problemStatement = "Em florestas densas, é comum observar orquídeas e bromélias crescendo fixadas nas bifurcações dos galhos de árvores centenárias. Embora utilizem a casca das árvores como suporte mecânico para ter acesso à radiação luminosa do dossel, suas raízes adventícias não penetram os tecidos vasculares e retiram água exclusivamente da umidade do ar e chuvas. Essa relação ecológica é classificada como:",
                    options = listOf(
                        "parasitismo, pois a presença das plantas sufoca a fotossíntese foliar da árvore hospedeira.",
                        "mutualismo obrigatório, haja vista que a árvore não resistiria sem as bromélias.",
                        "epifitismo, uma modalidade de inquilinismo harmônico sem prejuízo para a árvore suporte.",
                        "amensalismo, decorrente da liberação contínua de aleloquímicos tóxicos na casca.",
                        "predação vegetal, uma vez que há sequestro de nutrientes do xilema e floema da árvore."
                    ),
                    correctIndex = 2,
                    hint = "A planta apenas usa o tronco como apoio físico para buscar luz, sem extrair seiva.",
                    stepByStepSolution = "O epifitismo é inquilinismo entre plantas (+/0): a epífita ganha suporte e luz, enquanto a árvore hospedeira não sofre dano nem benefício relevante."
                ),
                MathExercise(
                    id = "bio_eco_03_q2",
                    title = "Fixação Biológica de Nitrogênio e Simbiose",
                    problemStatement = "Em um estudo agronômico, constatou-se que o cultivo consorciado de milho com certas espécies de leguminosas melhora a produtividade sem uso de fertilizantes químicos. As raízes das leguminosas formam nódulos habitados por bactérias do gênero Rhizobium, que fornecem compostos nitrogenados à planta e recebem carboidratos sintetizados na fotossíntese. Caso sejam separadas em laboratório, a sobrevivência e crescimento de ambas são drasticamente comprometidos. Essa relação representa um:",
                    options = listOf(
                        "comensalismo trófico facultativo.",
                        "amensalismo interespecífico.",
                        "mutualismo obrigatório (simbiose).",
                        "parasitismo foliar moderado.",
                        "inquilinismo rizomático."
                    ),
                    correctIndex = 2,
                    hint = "Ambos os parceiros dependem mutuamente da troca para sua sobrevivência (+/+ obrigatório).",
                    stepByStepSolution = "A associação entre leguminosas e Rhizobium é um mutualismo simbiótico obrigatório (+/+): a bactéria doa nitrogênio assimilável e a planta fornece abrigo e carboidratos da fotossíntese."
                ),
                MathExercise(
                    id = "bio_eco_03_q3",
                    title = "Maré Vermelha e Amensalismo",
                    problemStatement = "A maré vermelha é um fenômeno ambiental provocado pela proliferação explosiva de dinoflagelados (algas microscópicas). Esses microrganismos liberam potentes toxinas hidrossolúveis no mar que atingem o sistema nervoso de peixes, crustáceos e mamíferos aquáticos, provocando mortalidade massiva da fauna marinha sem que as algas se alimentem desses animais mortos. A relação biológica entre os dinoflagelados e os peixes asfixiados caracteriza:",
                    options = listOf(
                        "predação direta carnívora.",
                        "competição intraespecífica por espaço pelágico.",
                        "amensalismo (antibiose).",
                        "mutualismo acidental marinho.",
                        "protocooperação bioquímica."
                    ),
                    correctIndex = 2,
                    hint = "Uma espécie libera toxina que mata a outra sem visar predação (+/- ou 0/-).",
                    stepByStepSolution = "No amensalismo ou antibiose, os metabólitos ou toxinas secretados por uma população inibem ou aniquilam organismos vizinhos sem fins tróficos diretos."
                )
            ),
            "bio_eco_04" to listOf(
                MathExercise(
                    id = "bio_eco_04_q1",
                    title = "Rotação de Culturas e Nódulos Radiculares",
                    problemStatement = "A técnica agrícola da rotação de culturas intercalando o plantio de milho com o cultivo de soja ou feijão permite manter o solo produtivo e fértil, diminuindo drasticamente os custos com a compra de adubos químicos nitrogenados sintéticos. O fator biológico que fundamenta a eficácia dessa prática é a presença de:",
                    options = listOf(
                        "fungos micorrízicos que transformam nitrato em fósforo assimilável.",
                        "algas unicelulares fixadas nas folhas que realizam fotossíntese de compostos carbonados.",
                        "bactérias fixadoras de nitrogênio alojadas em nódulos radiculares das leguminosas.",
                        "minhocas que oxidam o gás nitrogênio diretamente no lúmen do tubo digestivo.",
                        "vírus bacteriófagos que quebram a amônia em gás nitrogênio livre."
                    ),
                    correctIndex = 2,
                    hint = "Leguminosas formam nódulos com bactérias Rhizobium capazes de converter N2 em amônia.",
                    stepByStepSolution = "A rotação com leguminosas fertiliza o solo biologicamente porque as bactérias fixadoras convertem o gás N2 atmosférico inerte em compostos nitrogenados assimiláveis pelas culturas sucessoras."
                ),
                MathExercise(
                    id = "bio_eco_04_q2",
                    title = "Etapas Bioquímicas da Nitrificação",
                    problemStatement = "Em um lago artificial de uma propriedade rural, o excesso de matéria orgânica nitrogenada passou pelo processo de nitrificação realizado pelas bactérias de solo e sedimentos. Qual é a sequência bioquímica correta das transformações sofridas pelo nitrogênio durante esse processo de oxidação bacteriana até atingir a forma assimilável pela flora aquática?",
                    options = listOf(
                        "Gás nitrogênio (N2) -> Nitrato (NO3-) -> Amônia (NH3).",
                        "Amônia (NH3) -> Nitrito (NO2-) -> Nitrato (NO3-).",
                        "Nitrato (NO3-) -> Gás nitrogênio (N2) -> Nitrito (NO2-).",
                        "Nitrito (NO2-) -> Amônia (NH3) -> Ureia.",
                        "Ácido úrico -> Gás nitrogênio (N2) -> Aminoácidos."
                    ),
                    correctIndex = 1,
                    hint = "Amônia oxida para Nitrito (Nitrosomonas) e Nitrito oxida para Nitrato (Nitrobacter).",
                    stepByStepSolution = "A nitrificação compõe-se de duas etapas oxidativas sucessivas: Nitritação (NH3 -> NO2-) seguida de Nitratação (NO2- -> NO3-). O nitrato é a forma absorvida pelas plantas."
                ),
                MathExercise(
                    id = "bio_eco_04_q3",
                    title = "Rios Voadores e Evapotranspiração Amazônica",
                    problemStatement = "Pesquisadores alertam que a devastação contínua da cobertura vegetal da Bacia Amazônica pode alterar profundamente o regime de precipitação no Sudeste e Sul do Brasil, afetando a geração hidrelétrica e o agronegócio. Isso ocorre porque as árvores da floresta atuam diretamente no ciclo hidrológico por meio do mecanismo biológico de:",
                    options = listOf(
                        "lixiviação profunda de minerais alcalinos das bacias sedimentares.",
                        "evapotranspiração em grande escala gerando os rios voadores de umidade.",
                        "percolação de águas pluviais subterrâneas que resfriam as placas tectônicas.",
                        "desnitrificação acelerada liberando vapor d'água no lugar de nitrogênio.",
                        "condensação interna de seiva elaborada convertida em geada matinal."
                    ),
                    correctIndex = 1,
                    hint = "A transpiração somada à evaporação das árvores da Amazônia forma os 'rios voadores'.",
                    stepByStepSolution = "A evapotranspiração maciça da floresta amazônica bombeia trilhões de litros de vapor de água para a atmosfera, originando correntes aéreas úmidas ('rios voadores') que regulam as chuvas do centro-sul."
                )
            ),
            "bio_eco_05" to listOf(
                MathExercise(
                    id = "bio_eco_05_q1",
                    title = "Mecanismo Causal da Eutrofização",
                    problemStatement = "Uma lagoa localizada próxima a áreas agrícolas e residenciais começou a exalar odor fétido e apresentou milhares de peixes mortos flutuando em sua superfície. As análises laboratoriais revelaram alta turbidez superficial, ausência quase total de oxigênio dissolvido nas profundidades e multiplicação massiva de bactérias anaeróbicas. A causa direta primária que desencadeou esse desastre biológico foi:",
                    options = listOf(
                        "a redução acentuada da temperatura da água decorrente de inversão térmica.",
                        "o aporte excessivo de compostos de nitrogênio e fósforo via esgoto e lixiviação de fertilizantes.",
                        "o derramamento de mercúrio metálico com intoxicação aguda imediata de fitoplâncton.",
                        "a precipitação de chuva ácida com dissolução instantânea das conchas dos moluscos.",
                        "a sobrepesca predatória que extinguiu os decompositores naturais do fundo lacustre."
                    ),
                    correctIndex = 1,
                    hint = "A eutrofização tem início com a carga excessiva de nutrientes agrícolas ou esgoto doméstico.",
                    stepByStepSolution = "O excesso de fósforo e nitrogênio causa hiperproliferação de algas superficiais, bloqueando a luz solar. A morte de vegetais de fundo estimula bactérias aeróbicas que exaurem o O2, asfixiando os peixes."
                ),
                MathExercise(
                    id = "bio_eco_05_q2",
                    title = "Bioacumulação em Cadeia Trófica Ripária",
                    problemStatement = "O tombamento de um caminhão-tanque em uma rodovia serrana provocou o vazamento de grande volume de agrotóxico não biodegradável em um córrego límpido de montanha. Meses após o acidente, os testes ecotoxicológicos demonstraram que a concentração da toxina era de 0,02 ppm na água, 0,5 ppm nas algas filamentosas, 12 ppm nos peixes carnívoros e 45 ppm nas lontras da região. O fenômeno ecológico constatado nos dados amostrais é a:",
                    options = listOf(
                        "eutrofização artificial ascendente.",
                        "inversão trófica estocástica.",
                        "magnificação trófica (bioacumulação).",
                        "lixiviação orgânica seletiva.",
                        "sucessão secundária regressiva."
                    ),
                    correctIndex = 2,
                    hint = "Substâncias tóxicas persistentes concentram-se cada vez mais nos níveis tróficos superiores.",
                    stepByStepSolution = "A concentração do poluente sobe de 0,02 ppm na água até 45 ppm no predador de topo (lontras), caracterizando a magnificação trófica (biomagnificação)."
                ),
                MathExercise(
                    id = "bio_eco_05_q3",
                    title = "Dinâmica Atmosférica da Inversão Térmica",
                    problemStatement = "Em grandes centros urbanos localizados em bacias orográficas durante o inverno, secretarias de saúde reportam picos acentuados de atendimentos pediátricos e geriátricos por afecções respiratórias associados à formação de uma névoa acinzentada densa (smog) que não se dissipa até o meio-dia. A dinâmica atmosférica responsável por reter essa névoa tóxica nas camadas respiráveis da cidade é explicada:",
                    options = listOf(
                        "pela formação de correntes ascendentes turbulentas geradas pelo asfalto aquecido.",
                        "pelo aprisionamento de uma camada de ar frio superficial e denso sob uma camada de ar quente superior.",
                        "pela liberação repentina de gás ozônio proveniente de descontinuidades na estratosfera polar.",
                        "pelo deslocamento horizontal de cinzas vulcânicas ricas em sulfetos de hidrogênio.",
                        "pela condensação de vapor de água marinha ionizada por radiação ultravioleta B."
                    ),
                    correctIndex = 1,
                    hint = "O ar frio e denso fica aprisionado junto ao solo pela camada quente, cessando a convecção.",
                    stepByStepSolution = "No inverno, o rápido resfriamento noturno do solo retém o ar frio e denso na base, impedindo a convecção vertical e concentrando a poluição na faixa habitada."
                )
            ),
            "bio_eco_06" to listOf(
                MathExercise(
                    id = "bio_eco_06_q1",
                    title = "Fontes de Metano na Agropecuária e Resíduos",
                    problemStatement = "Cientistas apontam que a pecuária extensiva de corte e o descarte inadequado de resíduos sólidos urbanos em lixões a céu aberto contribuem de modo significativo para o agravamento do aquecimento global. O principal gás do efeito estufa gerado no trato digestivo dos ruminantes e na decomposição anaeróbica desses resíduos é o:",
                    options = listOf(
                        "dióxido de enxofre (SO2).",
                        "gás metano (CH4).",
                        "óxido nitroso (N2O).",
                        "clorofluorcarboneto (CFC).",
                        "gás oxigênio diatômico (O2)."
                    ),
                    correctIndex = 1,
                    hint = "Gás gerado em condições de ausência de oxigênio (bactérias metanogênicas).",
                    stepByStepSolution = "A fermentação entérica no rúmen de bovinos e a decomposição anaeróbica em aterros geram metano (CH4), gás que retém calor com poder cerca de 28 vezes maior que o CO2."
                ),
                MathExercise(
                    id = "bio_eco_06_q2",
                    title = "Branqueamento de Corais e Zooxantelas",
                    problemStatement = "O branqueamento dos recifes de corais tem se tornado recorrente em diversas regiões dos oceanos tropicais. O estresse térmico provocado pela elevação da temperatura média das águas induz os pólipos dos cnidários a expelirem suas microalgas mutualistas (zooxantelas). Como consequência biológica imediata da perda dessa associação mutualística, os corais:",
                    options = listOf(
                        "perdem a capacidade de digestão extracelular por falta de enzimas ácidas.",
                        "passam a secretar carbonato de cálcio em velocidade quatro vezes superior à basal.",
                        "sofrem severo déficit nutricional decorrente da ausência de matéria orgânica fotoautotrófica.",
                        "migram ativamente em direção aos polos em busca de águas com maior teor de oxigênio.",
                        "transformam-se em organismos autótrofos quimiossintetizantes de grande profundidade."
                    ),
                    correctIndex = 2,
                    hint = "As zooxantelas fotossintetizantes alimentam os pólipos dos corais.",
                    stepByStepSolution = "As algas zooxantelas fornecem a maior parte da energia e nutrientes dos corais via fotossíntese. Ao expulsá-las devido ao estresse térmico, o coral enfraquece e morre por inanição."
                ),
                MathExercise(
                    id = "bio_eco_06_q3",
                    title = "Acidificação dos Oceanos e Calcificação",
                    problemStatement = "A dissolução de elevadas concentrações de dióxido de carbono (CO2) emitido por atividades antrópicas nas águas oceânicas tem provocado a acidificação dos mares. Esse processo químico compromete drasticamente os ecossistemas marinhos porque:",
                    options = listOf(
                        "aumenta o pH da água salgada, precipitando íons sódio e cloreto em forma de sal gema.",
                        "reduz a concentração de íons carbonato (CO3²⁻), dificultando a calcificação de conchas e esqueletos.",
                        "bloqueia a radiação infravermelha, impedindo a reprodução dos grandes cetáceos.",
                        "provoca a decomposição imediata de todo o oxigênio molecular livre dissolvido.",
                        "converte os sedimentos marinhos profundos em hidrocarbonetos aromáticos altamente tóxicos."
                    ),
                    correctIndex = 1,
                    hint = "CO2 forma ácido carbônico que consome os íons carbonato essenciais para as conchas de calcário.",
                    stepByStepSolution = "A acidez converte íons carbonato (CO3²⁻) em bicarbonato (HCO3⁻), diminuindo a disponibilidade de carbonato livre para a síntese do exoesqueleto de CaCO3 de corais e moluscos."
                )
            ),
            "bio_eco_07" to listOf(
                MathExercise(
                    id = "bio_eco_07_q1",
                    title = "Biogás e Biodigestores na Agropecuária",
                    problemStatement = "Um polo agropecuário produtor de suínos enfrentava graves problemas de autuação ambiental devido ao acúmulo de dejetos orgânicos líquidos nos mananciais vizinhos. Para solucionar o passivo ambiental, a cooperativa instalou biodigestores vedados onde colônias bacterianas anaeróbicas transformam a biomassa em biofertilizante rico e um gás inflamável. Esse gás combustível obtido é o:",
                    options = listOf(
                        "dióxido de carbono purificado.",
                        "metano (CH4), aproveitado para gerar eletricidade no complexo.",
                        "gás ozônio (O3), utilizado para desinfecção química do ar.",
                        "monóxido de carbono (CO), empregado em reações de polimerização.",
                        "hélio gasoso (He), estocado para refrigeração de equipamentos."
                    ),
                    correctIndex = 1,
                    hint = "A digestão anaeróbica de dejetos orgânicos gera biogás rico em metano combustível.",
                    stepByStepSolution = "O biogás produzido nos biodigestores é formado predominantemente por metano (CH4), excelente combustível renovável para geração de calor e eletricidade na fazenda."
                ),
                MathExercise(
                    id = "bio_eco_07_q2",
                    title = "Biorremediação com Microrganismos",
                    problemStatement = "Uma grande empresa química provocou contaminação acidental do solo de um distrito industrial com solventes aromáticos clorados. Em vez de remover e incinerar milhares de toneladas de terra contaminada, a equipe técnica contratada optou por inocular no subsolo espécies selecionadas de fungos e bactérias decompositoras tolerantes, fornecendo aeração forçada e fertilização mineral para acelerar a biodegradação dos poluentes. Essa abordagem biotecnológica sustentável é denominada:",
                    options = listOf(
                        "magnificação trófica direcionada.",
                        "eutrofização corretiva.",
                        "biorremediação.",
                        "transgenia xenobiótica induzida.",
                        "lixiviação química quelante."
                    ),
                    correctIndex = 2,
                    hint = "Uso de organismos biológicos vivos para degradar e neutralizar poluentes ambientais.",
                    stepByStepSolution = "A biorremediação emprega a maquinaria enzimática de bactérias ou fungos para metabolizar e desintoxicar contaminantes químicos em solos ou aquíferos."
                ),
                MathExercise(
                    id = "bio_eco_07_q3",
                    title = "Controle Biológico com Parasitoides e Bt",
                    problemStatement = "Um produtor de hortaliças orgânicas decidiu abandonar totalmente os inseticidas químicos para conter o ataque de lagartas desfolhadoras. Ele passou a liberar periodicamente em sua estufa microvespas que põem seus ovos dentro dos ovos das lagartas invasoras, além de pulverizar uma calda contendo esporos da bactéria entomopatogênica Bacillus thuringiensis. As técnicas adotadas pelo agricultor representam uma aplicação prática de:",
                    options = listOf(
                        "controle químico de choque com defensivos de amplo espectro.",
                        "controle biológico de pragas utilizando parasitoides e bioinseticida microbiológico.",
                        "adubação verde combinada com seleção artificial de predadores vertebrados.",
                        "mutagênese radioativa em vegetais de estufa para induzir autodefesa.",
                        "sucessão ecológica primária acelerada por bioestimulação radicular."
                    ),
                    correctIndex = 1,
                    hint = "Combinação de inimigos naturais (parasitoides) e agentes biológicos bacterianos específicos.",
                    stepByStepSolution = "O uso de parasitoides (microvespas) e bactérias patogênicas para insetos (Bacillus thuringiensis) caracteriza o controle biológico, que não gera toxicidade residual no ecossistema."
                )
            )
        )
    }

    private data class DidacticEntry(
        val theoryMarkdown: String,
        val formulas: List<FormulaOrSyntaxItem>,
        val solvedExamples: List<LessonExample>,
        val imageResId: Int? = null,
        val goldTip: String
    )

    private val didacticMap: Map<String, DidacticEntry> by lazy {
        mapOf(
            "bio_eco_01" to DidacticEntry(
                theoryMarkdown = """
# Conceitos de Meio Ambiente e Biodiversidade: O Palco da Vida

A **Ecologia** estuda as interações dos seres vivos entre si e com o ambiente físico. Para o ENEM, dominar esses conceitos é o alicerce interpretativo de toda a prova de Biologia:

### 1. Níveis de Organização
• **Organismo:** Indivíduo único de uma espécie com metabolismo próprio.
• **População:** Conjunto de indivíduos da **mesma espécie** no mesmo espaço e tempo.
• **Comunidade (Biocenose):** Conjunto de todas as populações de espécies diferentes interagindo.
• **Ecossistema:** União da comunidade biológica (biótico) com os fatores abióticos (luz, água, solo, temperatura).
• **Biosfera:** A soma integrada de todos os ecossistemas habitáveis da Terra.

### 2. Habitat vs. Nicho Ecológico
• **Habitat:** O "endereço" da espécie. Onde ela reside habitualmente.
• **Nicho Ecológico:** A "profissão" da espécie. O que come, horários de forrageamento, rituais reprodutivos e tolerâncias térmicas.
> **Princípio de Gause (Exclusão Competitiva):** Espécies com nichos idênticos não coexistem indefinidamente no mesmo habitat; haverá competição extrema até a extinção ou expulsão de uma delas.

### 3. Hotspots de Biodiversidade
Áreas com alta riqueza e **endemismo**, que já perderam mais de 70% da vegetação nativa. No Brasil: **Mata Atlântica** e **Cerrado**.

![Níveis Ecológicos de Organização](../../assets/images/ecologia/biodiversidade.png)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Habitat", "Endereço físico onde a espécie vive habitualmente", "Local geográfico, altitude, estrato da copa"),
                    FormulaOrSyntaxItem("Nicho Ecológico", "Função/profissão biológica da espécie no ecossistema", "Dieta, hábito noturno, predadores, reprodução"),
                    FormulaOrSyntaxItem("Princípio de Gause", "Dois nichos idênticos no mesmo habitat geram exclusão competitiva", "Nicho igual = Competição severa")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Coexistência de Aves no Mesmo Bioma",
                        scenario = "Duas espécies de gaviões habitam a mesma serra: uma caça roedores no chão ao meio-dia, a outra caça morcegos no crepúsculo.",
                        stepByStep = "Mesmo habitat (serra), mas nichos ecológicos temporais e alimentares distintos. Não ocorre sobreposição prejudicial de nichos."
                    )
                ),
                goldTip = "No ENEM: Se a questão falar em 'o que come e quando caça', a resposta é NICHO ECOLÓGICO. Se falar em 'onde vive e constrói ninho', é HABITAT!"
            ),
            "bio_eco_02" to DidacticEntry(
                theoryMarkdown = """
# Cadeias e Teias Alimentares: Fluxo de Energia e Bioacumulação

Os ecossistemas dependem de fluxos constantes de matéria e energia:
• **A Energia é Unidirecional e Decrescente:** O Sol fornece a energia primária. A cada nível trófico, 80% a 90% da energia se dissipa na forma de calor metabólico (respiração celular). Não pode ser reciclada.
• **A Matéria é Cíclica:** Átomos de carbono, nitrogênio e fósforo passam pelos seres vivos e são 100% reciclados pelos decompositores.

### 1. Níveis Tróficos
• **Produtores (1º Nível):** Autótrofos fotossintetizantes (plantas, algas).
• **Consumidores Primários (2º Nível):** Herbívoros que comem produtores.
• **Consumidores Secundários e Terciários:** Carnívoros predadores.
• **Decompositores:** Fungos e bactérias que atuam em todos os níveis reciclando a matéria orgânica morta.

### 2. Magnificação Trófica (Bioacumulação)
Metais pesados (mercúrio, chumbo) e pesticidas organoclorados (DDT) não são metabolizados. 
> **Atenção ENEM:** A concentração do poluente **aumenta progressivamente a cada nível trófico**! O predador de topo da cadeia acumula a maior dose tóxica por biomassa corporal.

![Pirâmide Trófica e Fluxo Energético](../../assets/images/ecologia/teia_alimentar.png)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Fluxo Energético", "Unidirecional e decrescente (perda como calor em cada nível)", "E_produtor > E_herbívoro > E_carnívoro"),
                    FormulaOrSyntaxItem("Ciclo da Matéria", "Cíclico (reciclada continuamente pelos decompositores)", "Decomposição mineraliza nutrientes"),
                    FormulaOrSyntaxItem("Magnificação Trófica", "Concentração de poluente persistente aumenta na cadeia", "[Toxina] topo >> [Toxina] base")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Contaminação por Mercúrio no Garimpo",
                        scenario = "Em um rio poluído com mercúrio: Algas têm 0,01 ppm, peixes têm 2,5 ppm e a lontra de topo tem 35 ppm.",
                        stepByStep = "Como o mercúrio não é excretado, a lontra consome centenas de peixes ao longo da vida e acumula a concentração máxima."
                    )
                ),
                goldTip = "Cuidado com pegadinhas: Energia diminui ao longo da cadeia, mas a concentração de metais pesados AUMENTA!"
            ),
            "bio_eco_03" to DidacticEntry(
                theoryMarkdown = """
# Relações Ecológicas: Harmônicas e Desarmônicas

Classificação das interações biológicas cobradas com rigor no ENEM:

### 1. Relações Harmônicas (+/+ ou +/0)
• **Mutualismo (+/+):** Obrigatória para a sobrevivência de ambos. Ex: Líquenes (alga + fungo), micorrizas, bactérias no rúmen.
• **Protocooperação (+/+):** Facultativa (ambos lucram, mas vivem separados). Ex: Pássaro-palito e crocodilo, anêmona e caranguejo-eremita.
• **Comensalismo (+/0):** Um se beneficia na alimentação sem lesar o outro. Ex: Rêmora no tubarão.
• **Inquilinismo / Epifitismo (+/0):** Um usa o outro como abrigo/suporte mecânico. Ex: Bromélias e orquídeas sobre troncos de árvores.

### 2. Relações Desarmônicas (+/- ou -/-)
• **Competição (-/-):** Prejudicial a ambos pelo gasto energético e estresse. Disputa por recursos limitados.
• **Predação (+/-):** O predador captura, mata e consome a presa.
• **Parasitismo (+/-):** O parasita debilita o hospedeiro sem matá-lo de imediato. Ex: Carrapatos, lombrigas, erva-de-passarinho.
• **Amensalismo / Antibiose (+/-):** Uma espécie secreta substâncias inibitórias. Ex: O fungo Penicillium secretando penicilina contra bactérias; Maré Vermelha.

![Quadro Completo das Relações Ecológicas](../../assets/images/ecologia/relacoes_ecologicas.png)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Harmônicas (+/+, +/0)", "Mutualismo, Protocooperação, Comensalismo, Inquilinismo", "Nenhum envolvido sofre dano"),
                    FormulaOrSyntaxItem("Desarmônicas (+/-, -/-)", "Competição (-/-), Predação (+/-), Parasitismo (+/-), Amensalismo (+/-)", "Ao menos um sofre prejuízo")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Diferença entre Mutualismo e Protocooperação",
                        scenario = "A alga e o fungo formam um líquen e morrem se isolados. O pássaro-palito limpa o dente do crocodilo, mas ambos vivem sem isso.",
                        stepByStep = "Líquen = Mutualismo (+/+ obrigatório). Pássaro e crocodilo = Protocooperação (+/+ facultativo)."
                    )
                ),
                goldTip = "Epifitismo NÃO É PARASITISMO! A orquídea só quer pegar sol no alto da árvore; ela não bebe a seiva da árvore hospedeira."
            ),
            "bio_eco_04" to DidacticEntry(
                theoryMarkdown = """
# Ciclos Biogeoquímicos: A Reciclagem Perpétua dos Elementos

A matéria é finita e circula entre reservatórios inorgânicos e seres vivos.

### 1. Ciclo do Nitrogênio (O mais cobrado no ENEM!)
A atmosfera tem 78% de N2, mas plantas e animais não o absorvem diretamente.
1. **Fixação:** Bactérias (*Rhizobium* em raízes de leguminosas) transformam N2 em Amônia (NH3/NH4+).
2. **Nitrificação (2 etapas):**
   - **Nitritação:** *Nitrosomonas* oxidam Amônia em Nitrito (NO2-, tóxico).
   - **Nitratação:** *Nitrobacter* oxidam Nitrito em Nitrato (NO3-, absorvível pelas plantas!).
3. **Assimilação:** Vegetais absorvem nitrato para montar proteínas e DNA.
4. **Desnitrificação:** Bactérias desnitrificantes (*Pseudomonas*) devolvem N2 para a atmosfera.

### 2. Ciclo da Água e os Rios Voadores
A evapotranspiração da Floresta Amazônica lança trilhões de litros de vapor na atmosfera, gerando massas aéreas de umidade ("rios voadores") que regulam as chuvas do Sudeste e Sul do Brasil.

![Esquema do Ciclo do Nitrogênio e Ação Bacteriana](../../assets/images/ecologia/ciclo_nitrogenio.png)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Fixação", "N2 (atmosfera) -> NH3 (Amônia)", "Realizada por Rhizobium em leguminosas"),
                    FormulaOrSyntaxItem("Nitrificação", "Amônia -> Nitrito (NO2-) -> Nitrato (NO3-)", "Nitrosomonas e Nitrobacter"),
                    FormulaOrSyntaxItem("Desnitrificação", "Nitrato (NO3-) -> N2 (gás)", "Pseudomonas em solo anaeróbico")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Adubação Verde e Rotação de Culturas",
                        scenario = "Intercalar milho com soja reduz a necessidade de fertilizantes químicos.",
                        stepByStep = "A soja é leguminosa; suas raízes têm Rhizobium que fixam N2 no solo em forma de nitrato, enriquecendo o terreno naturalmente."
                    )
                ),
                goldTip = "Grave a ordem das bactérias da nitrificação: Primeiro Nitrosomonas (faz nitrito), depois Nitrobacter (faz nitrato)!"
            ),
            "bio_eco_05" to DidacticEntry(
                theoryMarkdown = """
# Impactos Ambientais e Poluição: A Pegada Humana

O ENEM foca nos mecanismos químicos e biológicos de degradação ambiental:

### 1. Eutrofização (Campeã Absoluta de Cobrança)
Despejo de esgoto doméstico não tratado ou fertilizantes (ricos em Nitrogênio e Fósforo) em rios e lagos:
1. Superproliferação de algas na superfície (floração verde).
2. Bloqueio da luz solar para o fundo.
3. Morte em massa das plantas e algas submersas (cessação da fotossíntese).
4. Bactérias aeróbicas decompõem a matéria morta e **consomem todo o O2 da água**.
5. **Anóxia e morte de peixes por asfixia**, seguida de proliferação de bactérias anaeróbicas fétidas.

### 2. Chuva Ácida
Queima de combustíveis com enxofre e nitrogênio gera SO2 e NO2, formando ácido sulfúrico (H2SO4) e nítrico (HNO3). Acidifica rios, corrói monumentos e queima folhas.

### 3. Inversão Térmica
No inverno, o ar frio (mais denso) fica preso perto do chão sob uma camada de ar quente superior. Impede a convecção e aprisiona poluentes no nível do solo, disparando crises respiratórias.

![Etapas Ilustradas da Eutrofização em Lago](../../assets/images/ecologia/eutrofizacao.png)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Eutrofização", "Nutrientes (N, P) -> Floração -> Bloqueio Solar -> Bactérias Aeróbicas -> Anóxia", "Esgoto/Adubo leva à asfixia dos peixes"),
                    FormulaOrSyntaxItem("Chuva Ácida", "SO2 / NO2 + H2O -> H2SO4 / HNO3 (pH < 5,0)", "Corrosão e acidificação aquática"),
                    FormulaOrSyntaxItem("Inversão Térmica", "Ar frio preso no solo por ar quente superior", "Sem convecção, retém poluentes no inverno")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Morte de Peixes em Lago com Esgoto",
                        scenario = "Um lago fica verde e dias depois milhares de tilápias morrem na praia.",
                        stepByStep = "A causa mortis direta não é o veneno do esgoto, mas sim a anóxia (falta de O2) causada pela decomposição bacteriana aeróbica."
                    )
                ),
                goldTip = "Na Eutrofização, os peixes morrem por FALTA DE OXIGÊNIO (anóxia consumida por bactérias aeróbicas), e não por excesso de algas!"
            ),
            "bio_eco_06" to DidacticEntry(
                theoryMarkdown = """
# Efeito Estufa e Aquecimento Global

Distinção fundamental exigida na prova do ENEM:

### 1. Efeito Estufa Natural: Benéfico e Vital
Sem ele, a Terra estaria a **-18°C congelada**. O efeito estufa natural mantém o planeta a uma média de **+15°C**, permitindo a água líquida e a vida.
A superfície da Terra absorve radiação solar e reemite radiação infravermelha (calor), retida pelos Gases de Efeito Estufa (GEE).

### 2. Gases de Efeito Estufa (GEE)
• **CO2 (Dióxido de Carbono):** Principal em volume. Emitido por queima de fósseis e desmatamento/queimadas.
• **Metano (CH4):** Poder estufa 28x maior que o CO2. Proveniente de aterros, lixões, pecuária (rúmen dos bois) e arrozais.
• **Óxido Nitroso (N2O):** Uso de fertilizantes químicos nitrogenados.

### 3. Consequências Críticas
• **Branqueamento de Corais:** Água quente induz corais a expulsarem as algas fotossintetizantes *zooxantelas*, morrendo de fome.
• **Acidificação Oceânica:** Excesso de CO2 forma ácido carbônico (H2CO3) e consome carbonato (CO3²⁻), impedindo corais e moluscos de formarem conchas de CaCO3.

![Radiação Solar e Efeito Estufa](../../assets/images/ecologia/efeito_estufa.png)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Efeito Estufa Natural", "Radiação solar -> Absorção -> Infravermelho retido por GEE", "Garante média de +15°C no planeta"),
                    FormulaOrSyntaxItem("Poder de Aquecimento", "CH4 (Metano) tem potencial térmico ~28x maior que o CO2", "Pecuária bovina e lixões"),
                    FormulaOrSyntaxItem("Acidificação Oceânica", "CO2 + H2O -> H2CO3 -> H+ consome CO3²⁻", "Dificulta síntese de conchas de calcário")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Por que o Gado Aumenta o Efeito Estufa?",
                        scenario = "A pecuária extensiva é alvo de debates ecológicos sobre mudanças climáticas.",
                        stepByStep = "A digestão de celulose no rúmen produz metano (CH4), liberado por eructação. O metano é um GEE de altíssimo poder de retenção de calor."
                    )
                ),
                goldTip = "O Efeito Estufa NÃO é ruim por natureza; o problema é a sua INTENSIFICAÇÃO antrópica acelerada pela queima de combustíveis fósseis."
            ),
            "bio_eco_07" to DidacticEntry(
                theoryMarkdown = """
# Sustentabilidade e Transição Ecológica: O Futuro da Vida

Desenvolvimento Sustentável é suprir as necessidades do presente sem comprometer a capacidade das gerações futuras.

### 1. Matriz Energética Renovável
• **Solar e Eólica:** Fontes limpas e inesgotáveis sem emissão de GEE na operação.
• **Biocombustíveis (Etanol e Biodiesel):** Ciclo neutro de carbono (o CO2 emitido foi absorvido na fotossíntese da safra).
• **Biogás / Biometano:** Aproveitamento do CH4 de fezes de suínos/bovinos e aterros para gerar eletricidade em biodigestores.

### 2. Biorremediação
Emprego de microrganismos (bactérias e fungos) ou vegetais para degradar e neutralizar poluentes tóxicos ou petróleo derramado no meio ambiente.

### 3. Controle Biológico de Pragas
Substituição de agrotóxicos sintéticos pelo uso de inimigos naturais específicos:
• **Parasitoides:** Microvespas que depositam ovos em larvas invasoras.
• **Bioinseticidas:** Bactéria *Bacillus thuringiensis* (Bt), letal apenas para o trato intestinal de lagartas sem danificar vertebrados.

### 4. Economia Circular e Logística Reversa
Reinserção de materiais descartados no ciclo produtivo, com fabricantes responsáveis pela coleta de baterias, lâmpadas e embalagens pós-uso.

![Pilares da Sustentabilidade](../../assets/images/ecologia/sustentabilidade.png)
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Desenvolvimento Sustentável", "Atender o presente sem esgotar o futuro", "Relatório Brundtland (1987)"),
                    FormulaOrSyntaxItem("Biorremediação", "Bactérias / Fungos degradam contaminantes e petróleo", "Biotecnologia verde de descontaminação"),
                    FormulaOrSyntaxItem("Controle Biológico", "Inimigos naturais (parasitoides/predadores/Bt) contra pragas", "Substitui defensivos químicos sintéticos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Uso de Biodigestores em Granja de Porcos",
                        scenario = "Tratar dejetos suínos e gerar energia elétrica para a propriedade.",
                        stepByStep = "Bactérias anaeróbicas convertem esterco em metano (biogás combustível) e biofertilizante, zerando o passivo ambiental de poluição da água."
                    )
                ),
                goldTip = "No ENEM: Se a questão falar em 'usar bactérias ou fungos para limpar óleo ou solo tóxico', a resposta é BIORREMEDIAÇÃO!"
            )
        )
    }
}
