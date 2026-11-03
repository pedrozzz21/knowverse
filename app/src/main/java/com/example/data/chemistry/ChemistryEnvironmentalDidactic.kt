package com.example.data.chemistry

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo para o Módulo 4: Química Ambiental e do Cotidiano (ENEM).
 * Aulas:
 * 1. quim_amb_01: Química da Água e Saneamento
 * 2. quim_amb_02: Química Atmosférica e Poluição
 * 3. quim_amb_03: Combustíveis e Energia
 * 4. quim_amb_04: Ação de Sabões e Detergentes
 * 5. quim_amb_05: Polímeros e Reciclagem
 */
object ChemistryEnvironmentalDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "quim_amb_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Química da Água, Saneamento Básico e Estações de Tratamento (ETA e ETE)

                    A água potável é um recurso escasso e essencial à sobrevivência humana. No ENEM, as etapas físico-químicas de uma **Estação de Tratamento de Água (ETA)** e o controle da Demanda Bioquímica de Oxigênio (DBO) são cobrados de forma recorrente.

                    ### 1. Etapas Sequenciais da Estação de Tratamento de Água (ETA)
                    - **1. Captação e Gradeamento:** Retenção mecânica de detritos sólidos grosseiros (troncos, folhas, lixo plástico) por grades metálicas.
                    - **2. Coagulação (Processo Químico Crucial):**
                      • A água bruta contém argila e micropartículas coloidais suspensas com cargas elétricas superficiais negativas que se repelem mutuamente e não sedimentam.
                      • Adiciona-se um agente coagulante, tipicamente o **Sulfato de Alumínio [Al_2(SO_4)_3]** ou Cloreto Férrico (FeCl_3), juntamente com **Cal Virgem (CaO) ou Cal Hidratada [Ca(OH)_2]** para elevar o pH.
                      • Reação de hidrólise do alumínio em meio básico:
                        ${"$"}Al^{3+}_{(aq)} + 3 OH^-_{(aq)} → Al(OH)_{3(s)} \quad (Precipitado gelatinoso insolúvel)${"$"}
                    - **3. Floculação (Processo Físico):**
                      • A água é submetida a agitação mecânica lenta em tanques com paletas. O precipitado gelatinoso de Al(OH)_3 atrai e agrega as partículas coloidais de sujeira, formando **flocos volumosos e pesados**.
                    - **4. Decantação:**
                      • A água escoa para tanques de repouso absoluto. Pela ação da gravidade e diferença de densidade, os flocos pesados sedimentam no fundo, formando uma camada de lodo que é descartada.
                    - **5. Filtração em Leito Misto:**
                      • A água sobrenadante clarificada atravessa filtros constituídos por camadas estratificadas de **cascalho, areia fina e carvão ativado (antracito)**.
                      • O carvão ativado atua por **adsorção**, retendo compostos orgânicos voláteis responsáveis por odores e sabores desagradáveis.
                    - **6. Cloração / Desinfecção:**
                      • Adição de cloro gasoso (Cl_2) ou hipoclorito de sódio (NaClO). Em água, o cloro gera o **Ácido Hipocloroso (HClO)**, um agente oxidante potente que destrói a membrana celular e extermina bactérias, vírus e protozoários patogênicos.
                    - **7. Fluoretação:**
                      • Adição de compostos de flúor (como ácido fluossilícico H_2SiF_6) em doses rigorosas para prevenir a cárie dentária na população infanto-juvenil.
                    - **8. Correção Final de pH:**
                      • Adição controlada de cal ou barrilha para elevar o pH para ~7,2–7,8, evitando que a acidez residual da água corroa as tubulações metálicas da rede urbana de distribuição.

                    ### 2. Estação de Tratamento de Esgoto (ETE) e DBO
                    - **Demanda Bioquímica de Oxigênio (DBO):** Quantidade de oxigênio dissolvido (O_2) consumida pelos microrganismos aeróbios para degradar a matéria orgânica presente na água:
                      • Água limpa: Baixa DBO (pouca matéria orgânica, abundância de O_2 dissolvido para a vida aquática).
                      • Esgoto bruto: DBO altíssima (bactérias consomem todo o oxigênio da água para digerir a matéria orgânica, provocando a asfixia em massa de peixes).
                    - **Tratamento Biológico de Esgotos (Lodos Ativados):**
                      • O esgoto decantado é aerado com injeção de ar comprimido para estimular a proliferação de bactérias aeróbias benéficas que convertem a matéria orgânica poluente em biomassa sedimentável e CO_2.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Coagulação com Sulfato de Alumínio", "Al³⁺ + 3 OH⁻ -> Al(OH)3 (sólido gelatinoso)", "Precipitado que atrai partículas coloidais de argila"),
                    FormulaOrSyntaxItem("Ação Desinfetante do Cloro", "Cl2 + H2O <=> HClO + H⁺ + Cl⁻", "O ácido hipocloroso (HClO) é o agente germicida oxidante"),
                    FormulaOrSyntaxItem("Demanda Bioquímica de Oxigênio (DBO)", "DBO alta = Alta poluição orgânica = Pouco O2 dissolvido", "Indicador central da qualidade ambiental de corpos hídricos")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Uso de Cal na Coagulação do Tratamento de Água",
                        codeOrFormula = "CaO + H2O -> Ca(OH)2 | Al2(SO4)3 + 3 Ca(OH)2 -> 2 Al(OH)3(s) + 3 CaSO4",
                        explanation = "A adição de sulfato de alumínio acidifica a água devido à hidrólise ácida do cátion Al³⁺. Sem a adição prévia ou conjunta de óxido de cálcio (cal virgem) ou hidróxido de cálcio, o pH cai em demasia e o hidróxido de alumínio não precipita eficientemente. A cal neutraliza a acidez e fornece íons hidroxila (OH⁻) essenciais para a precipitação dos flocos."
                    )
                ),
                goldTip = "Decore a ordem das etapas da ETA: 1. Coagulação -> 2. Floculação -> 3. Decantação -> 4. Filtração -> 5. Cloração -> 6. Fluoretação!"
            )

            "quim_amb_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Química Atmosférica: Chuva Ácida, Camada de Ozônio e Poluentes

                    A atmosfera terrestre é um reator químico dinâmico. No ENEM, os fenômenos de poluição atmosférica são abordados com foco nas **reações químicas causadoras, fórmulas moleculares e tecnologias de mitigação**.

                    ### 1. Chuva Ácida: Óxidos de Enxofre (SO_x) e Nitrogênio (NO_x)
                    - Toda chuva natural é ligeiramente ácida (pH \approx 5,6) pela dissolução do dióxido de carbono atmosférico:
                      ${"$"}CO_{2(g)} + H_2O_{(l)} ⇌ H_2CO_{3(aq)} ⇌ H^+_{(aq)} + HCO_{3(aq)}^-${"$"}
                    - A **Chuva Ácida nociva (pH < 4,5)** decorre de atividades industriais e queima de combustíveis fósseis:
                      • **Óxidos de Enxofre (SO_2 e SO_3):** Originados pela queima de carvão mineral e óleo diesel ricos em enxofre:
                        ${"$"}S_{(s)} + O_{2(g)} → SO_{2(g)}${"$"}
                        ${"$"}2SO_{2(g)} + O_{2(g)} \xrightarrow{catalisadores} 2SO_{3(g)}${"$"}
                        ${"$"}SO_{3(g)} + H_2O_{(l)} → H_2SO_{4(aq)} \quad (Ácido Sulfúrico, ácido forte!)${"$"}
                      • **Óxidos de Nitrogênio (NO e NO_2):** Originados nos motores a combustão interna, onde a altíssima temperatura e pressão combinam o N_2 e O_2 do próprio ar:
                        ${"$"}N_{2(g)} + O_{2(g)} → 2NO_{(g)} \quad e \quad 2NO_{(g)} + O_{2(g)} → 2NO_{2(g)}${"$"}
                        ${"$"}2NO_{2(g)} + H_2O_{(l)} → HNO_{3(aq)} + HNO_{2(aq)} \quad (Ácido Nítrico, forte!)${"$"}
                    - **Impactos:** Corrosão de monumentos históricos e pontes de mármore e calcário:
                      ${"$"}CaCO_{3(s)} + H_2SO_{4(aq)} → CaSO_{4(s)} + H_2O_{(l)} + CO_{2(g)}${"$"}
                      Acidificação de solos, lixiviação de nutrientes minerais e liberação de íons Al^{3+} tóxicos para raízes de plantas e peixes em lagos.

                    ### 2. Destruição Catalítica da Camada de Ozônio (O_3) por CFCs
                    - A camada de ozônio na estratosfera filtra a radiação solar ultravioleta nociva (UV-B), que causa câncer de pele e mutações genéticas.
                    - Os **Clorofluorcarbonetos (CFCs, como o Freon-12 CF_2Cl_2)**, utilizados antigamente em aerossóis e fluidos refrigerantes de geladeiras, são quimicamente inertes na troposfera e sobem intactos até a estratosfera.
                    - **Mecanismo Catalítico em Cadeia de Destruição do Ozônio:**
                      1. Fotólise por radiação UV quebra a ligação C-Cl, liberando o radical livre de cloro:
                         ${"$"}CF_2Cl_2 \xrightarrow{UV} CF_2Cl^\bullet + Cl^\bullet${"$"}
                      2. O radical livre Cl^\bullet ataca a molécula de ozônio:
                         ${"$"}Cl^\bullet + O_3 → ClO^\bullet + O_2${"$"}
                      3. O monóxido de cloro reage com oxigênio atômico, regenerando o radical Cl^\bullet:
                         ${"$"}ClO^\bullet + O → Cl^\bullet + O_2${"$"}
                      4. **Equação Global:** O_3 + O → 2O_2
                      • O radical Cl^\bullet atua como **catalisador homogêneo**: um único átomo de cloro é capaz de destruir mais de 100.000 moléculas de ozônio antes de ser neutralizado! (Protocolo de Montreal baniu os CFCs com sucesso).

                    ### 3. Gases do Efeito Estufa e Aquecimento Global
                    - O efeito estufa natural é benéfico e mantém a temperatura média do planeta em +15°C (sem ele, a Terra estaria congelada a -18°C).
                    - O problema abordado no ENEM é a exacerbação antropogênica:
                      • **Dióxido de Carbono (CO_2):** Principal gás em volume de emissão (queima de carvão, derivados de petróleo e desmatamento/queimadas).
                      • **Metano (CH_4):** Potencial de aquecimento global cerca de 25 vezes superior ao do CO_2 em 100 anos (originado na pecuária bovina por fermentação entérica, aterros sanitários e orizicultura irrigada).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Formação do Ácido Sulfúrico", "SO3 + H2O -> H2SO4", "Gás anidrido sulfúrico reage com a umidade das nuvens"),
                    FormulaOrSyntaxItem("Destruição do Ozônio por Cloro", "Cl• + O3 -> ClO• + O2 | ClO• + O -> Cl• + O2", "O radical livre de cloro atua como catalisador e regenera-se"),
                    FormulaOrSyntaxItem("Corrosão de Mármore por Chuva Ácida", "CaCO3 + H2SO4 -> CaSO4 + H2O + CO2", "Desprendimento de gás carbônico e degradação física da rocha")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Diferença Crítica entre Camada de Ozônio e Efeito Estufa",
                        codeOrFormula = "Ozônio = Filtro de Radiação UV | Efeito Estufa = Retenção de Radiação Infravermelha (Calor)",
                        explanation = "É um erro comum misturar os dois fenômenos: a destruição da camada de ozônio permite que raios ultravioleta (UV) de alta energia cheguem à superfície, provocando danos celulares e câncer. Já o efeito estufa decorre da absorção e retenção da radiação infravermelha térmica (calor) por gases como CO2 e CH4 na troposfera."
                    )
                ),
                goldTip = "O radical livre de cloro é um CATALISADOR na destruição do ozônio: ele inicia e acelera a destruição do O3 e é regenerado ao final de cada ciclo!"
            )

            "quim_amb_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Combustíveis Fósseis, Refino do Petróleo e Biocombustíveis

                    A matriz energética mundial é dominada por combustíveis químicos. No ENEM, a transição para energias renováveis e o aproveitamento do petróleo e da biomassa são temas onipresentes.

                    ### 1. Petróleo e Fracionamento em Torres de Destilação
                    - O petróleo é um combustível fóssil líquido de cor escura, formado pela decomposição anaeróbia de matéria orgânica fóssil (plâncton marinho) soterrada há milhões de anos sob alta pressão e temperatura.
                    - É uma mistura complexa de milhares de compostos orgânicos, principalmente **hidrocarbonetos apolares**.
                    - **Destilação Fracionada na Refinaria:**
                      • O petróleo é aquecido e vaporizado na base de uma alta **torre de fracionamento**. Conforme os vapores sobem, a temperatura diminui progressivamente.
                      • Frações com menor massa molar têm **menor ponto de ebulição** e condensam nas bandejas superiores da torre. Frações de alta massa molar condensam na base:
                        ```
                        Topo (Mais Volátil / Menor PE / Cadeia Curta):
                        1. Gás Liquefeito de Petróleo (GLP: Propano C3H8 + Butano C4H10) -> Ferve < 20°C
                        2. Éter de petróleo e Solventes (C5 - C6)
                        3. Gasolina automotiva (C5 - C10, foco em isoctano C8H18) -> Ferve entre 40°C e 200°C
                        4. Querosene de aviação (C11 - C12)
                        5. Óleo Diesel (C13 - C20) -> Motores pesados
                        6. Óleos Lubrificantes e Parafinas (C20 - C30)
                        Base (Menos Volátil / Maior PE / Cadeia Longa):
                        7. Resíduo asfáltico e Piche (C30+) -> Pavimentação
                        ```
                    - **Craqueamento Catalítico (Cracking):**
                      • Processo químico que quebra moléculas pesadas de óleo de baixo valor comercial (C_{16}H_{34}) em cadeias menores de alta demanda, como gasolina (C_8H_{18}) e alcenos leves (C_2H_4 para plásticos).

                    ### 2. Biocombustíveis: Etanol e Biodiesel no Brasil
                    - **Etanol de Primeira Geração (Cana-de-Açúcar):**
                      • Moagem da cana → extração do caldo (rico em sacarose C_{12}H_{22}O_{11}) → fermentação alcoólica anaeróbia por leveduras (*Saccharomyces cerevisiae*):
                        ${"$"}C_6H_{12}O_{6(aq)} \xrightarrow{levedura} 2 C_2H_5OH_{(l)} + 2 CO_{2(g)}${"$"}
                      • A mistura fermentada atinge cerca de 10% de álcool e é purificada por destilação fracionada até atingir o azeótropo (álcool 96°GL).
                      • **Vantagem Ecológica no ENEM:** Ciclo fechado do carbono: o CO_2 lançado pelo escapamento do carro foi absorvido da atmosfera pela fotossíntese da lavoura de cana durante seu crescimento.
                    - **Biodiesel e a Reação de Transesterificação:**
                      • O biodiesel é produzido pela reação entre **triglicerídeos** (óleos vegetais de soja, palma, mamona ou gordura animal residual) e um álcool de cadeia curta (**metanol ou etanol**), na presença de um catalisador alcalino forte (NaOH ou KOH):
                        ${"$"}Triglicerídeo + 3 Álcool \xrightarrow{NaOH} 3 Ésteres de Ácidos Graxos (Biodiesel) + Glicerol${"$"}
                      • *Vantagens do Biodiesel:* Renovável, biodegradável, isento de enxofre (não produz chuva ácida por SO_2) e com alto índice de cetano para motores a diesel.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Transesterificação do Biodiesel", "Triglicerídeo + 3 Álcool -> 3 Biodiesel (Éster) + Glicerol", "Reação fundamental de obtenção de ésteres metílicos ou etílicos"),
                    FormulaOrSyntaxItem("Fermentação Alcoólica", "C6H12O6 -> 2 C2H5OH + 2 CO2", "Conversão biológica de glicose em etanol e gás carbônico"),
                    FormulaOrSyntaxItem("Craqueamento Térmico", "C16H34 -> C8H18 (gasolina) + C8H16 (alceno)", "Quebra de cadeias longas em cadeias curtas de maior valor")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Balanço de Carbono do Etanol vs Gasolina",
                        codeOrFormula = "Gasolina: Carbono fóssil estocado soterrado há milhões de anos -> Aumenta CO2 na atmosfera",
                        explanation = "A gasolina queima liberando carbono fóssil soterrado no subsolo desde o período Cretáceo, provocando acréscimo líquido na concentração de CO2 atmosférico. Já o bioetanol participa do ciclo contemporâneo do carbono: a queima devolve o mesmo CO2 que a planta removeu por fotossíntese meses antes."
                    )
                ),
                goldTip = "Biodiesel é ÉSTER! A reação para produzi-lo chama-se TRANSESTERIFICAÇÃO (transforma um triéster de triglicerídeo em três monoésteres de biodiesel) gerando glicerina como subproduto!"
            )

            "quim_amb_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Ação Tensoativa de Sabões, Detergentes e Micelas

                    Por que a água pura não consegue lavar pratos engordurados com óleo? Porque a água é altamente polar e o óleo é apolar: eles não interagem e apresentam alta tensão superficial. Para limpar a gordura, é indispensável a utilização de **agentes tensoativos (surfactantes)**.

                    ### 1. A Estrutura Anfifílica (ou Anfipática)
                    As moléculas de sabão e detergente possuem dupla afinidade em uma mesma estrutura química:
                    - **Cauda Apolar (Lipofílica / Hidrofóbica):** Longa cadeia de hidrocarboneto apolar (com mais de 12 carbonos) que se dissolve e interage intensamente com gorduras, óleos e graxas por forças de dispersão de London.
                    - **Cabeça Polar (Hidrofílica / Lipofóbica):** Extremidade iônica ou altamente polar que estabelece fortes ligações de hidrogênio e atrações íon-dipolo com a água.
                      • Nos sabões: Grupo carboxilato de sódio ou potássio (-COO^- Na^+).
                      • Nos detergentes: Grupo sulfonato de sódio (-SO_3^- Na^+).

                    ### 2. O Mecanismo de Ação e a Formação de Micelas
                    - **1. Quebra da Tensão Superficial:** As moléculas do sabão posicionam-se na interface água-ar, rompendo a rede coesa de ligações de hidrogênio na superfície da água e permitindo que ela "molhe" melhor a superfície a ser lavada.
                    - **2. Emulsificação da Gordura e Micelas:**
                      • As caudas apolares penetram na gota de gordura e interagem com ela, enquanto as cabeças polares ficam voltadas para fora, em contato direto com a água.
                      • Formam-se agregados esféricos microscópicos denominados **MICELAS**.
                      • O centro da micela aprisiona a gordura, e o exterior da micela é recoberto por cargas elétricas negativas (-COO^-).
                      • As cargas de mesmo sinal na superfície das micelas se repelem mutuamente, impedindo que as gotículas de óleo se juntem novamente e permitindo que a sujeira seja arrastada e suspensa pela corrente de água durante o enxágue.

                    ### 3. Diferença entre Sabão e Detergente e o Problema da Água Dura
                    - **Água Dura (Rica em íons Ca^{2+} e Mg^{2+}):**
                      • Quando o sabão comum é adicionado a águas duras (comuns em regiões de rochas calcárias subterrâneas), os íons Ca^{2+} e Mg^{2+} reagem com o carboxilato e precipitam sais insolúveis insolúveis em água (o "sabão de cálcio" ou borra branca):
                        ${"$"}2 R-COO^- Na^+ + Ca^{2+}_{(aq)} → (R-COO)_2Ca_{(s)} \downarrow + 2 Na^+_{(aq)}${"$"}
                      • O sabão precipitado **perde completamente a ação tensoativa**, não faz espuma e mancha as roupas, exigindo um gasto excessivo de produto.
                      • **Vantagem dos Detergentes Sintéticos:** Os sulfonatos de cálcio [(R-SO_3)_2Ca] são solúveis em água. Portanto, os **detergentes continuam limpando e espumando perfeitamente mesmo em águas duras**.
                    - **Biodegradabilidade de Detergentes:**
                      • *Biodegradáveis:* Possuem cadeia carbônica **linear** (normal), que é facilmente digerida por enzimas de bactérias na natureza.
                      • *Não Biodegradáveis:* Possuem cadeia **ramificada**, que resiste ao ataque bacteriano, persistindo nos rios e formando imensas montanhas de espuma tóxica que bloqueiam a oxigenação das águas.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Estrutura do Sabão", "R-COO⁻ Na⁺ (Cadeia apolar longa + cabeça polar iônica)", "Molécula anfifílica que interage com água e gordura"),
                    FormulaOrSyntaxItem("Precipitação em Água Dura", "2 R-COO⁻ + Ca²⁺ -> (R-COO)2Ca (precipitado)", "Sabões comuns perdem a função em presença de Ca²⁺ e Mg²⁺"),
                    FormulaOrSyntaxItem("Micela", "Núcleo apolar lipofílico + Periferia polar hidrofílica", "Estrutura esférica que emulsifica a gordura para lavagem")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Como o Sabão Destrói o Coronavírus (SARS-CoV-2)",
                        codeOrFormula = "Envelope lipídico do vírus + Cauda apolar do sabão -> Dissolução do envelope e inativação",
                        explanation = "O vírus SARS-CoV-2 possui um envelope externo protetor formado por uma bicamada lipídica (gordura). As caudas apolares das moléculas de sabão inserem-se nessa membrana lipídica, desestabilizando e rompendo o envoltório viral. Com o envelope dissolvido, as proteínas de espícula se desprendem e o vírus é completamente inativado."
                    )
                ),
                goldTip = "Para o ENEM: Cadeia LINEAR = Biodegradável. Cadeia RAMIFICADA = Não biodegradável (polui rios com espumas persistentes)!"
            )

            "quim_amb_05" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Polímeros Sintéticos, Classificação e Desafios da Reciclagem

                    Polímeros são macromoléculas formadas pela união covalente repetitiva de milhares de unidades estruturais menores denominadas **monômeros** (do grego: *poly* = muitos; *meros* = partes). No ENEM, o plástico é abordado sob a ótica da **sustentabilidade, tempo de degradação e reciclabilidade**.

                    ### 1. Termoplásticos vs Termofixos (Termorrígidos)
                    - **Termoplásticos (Recicláveis Mecanicamente):**
                      • Formados por cadeias lineares ou ramificadas que interagem apenas por forças intermoleculares (sem ligações cruzadas permanentes).
                      • **Amolecem e fundem quando aquecidos**, podendo ser remodelados e resfriados inúmeras vezes sem perda de suas propriedades fundamentais.
                      • São os plásticos recicláveis do nosso cotidiano (garrafas, potes de comida, sacolas plásticas).
                    - **Termofixos ou Termorrígidos (Não Recicláveis por Fusão):**
                      • Suas cadeias são unidas por uma densa rede tridimensional de **ligações cruzadas covalentes fortes**.
                      • **NÃO fundem com o calor**. Quando aquecidos a temperaturas elevadas, degradam-se e carbonizam (queimam) sem amolecer.
                      • Não podem ser remoldados por fusão.
                      • *Exemplos:* Baquelite (cabos de panela e tomadas antigas), resina epóxi, poliuretano (espumas de colchão) e **borracha vulcanizada** de pneus (com pontes de enxofre cruzadas).

                    ### 2. Os Principais Termoplásticos do Cotidiano (Códigos de Reciclagem)
                    - **1. PET (Poliéster de Tereftalato de Etileno):** Garrafas de refrigerante, fibras têxteis de poliéster. Polímero de condensação.
                    - **2. PEAD (Polietileno de Alta Densidade):** Frascos rígidos de produtos de limpeza e xampus, tubulações de saneamento. Cadeias lineares empacotadas densamente.
                    - **3. PVC (Policloreto de Vinila):** Tubos e conexões hidráulicas de construção civil, forros. Contém cloro em sua estrutura ([CH_2-CHCl]_n).
                    - **4. PEBD (Polietileno de Baixa Densidade):** Sacolas plásticas de supermercado, filmes agrícolas. Cadeias muito ramificadas e flexíveis.
                    - **5. PP (Polipropileno):** Potes de alimentos plásticos que vão ao micro-ondas, tampinhas de garrafa, copos descartáveis duráveis.
                    - **6. PS (Poliestireno):** Copos descartáveis frágeis e, na forma expandida por gás, o **Isopor** (isolante térmico acústico).

                    ### 3. Bioplásticos e Polímeros Biodegradáveis
                    - Os plásticos convencionais derivados do petróleo levam centenas de anos para se degradar porque as bactérias não possuem enzimas capazes de quebrar as fortes ligações carbono-carbono apolares sintéticas, fragmentando-se em **microplásticos** que contaminam a fauna aquática e a cadeia alimentar humana.
                    - **Ácido Polilático (PLA):**
                      • Biopolímero biodegradável obtido a partir do amido de fontes vegetais renováveis (milho, mandioca ou cana-de-açúcar).
                      • O amido é hidrolisado a glicose, fermentado a **Ácido Lático** e polimerizado.
                      • Em usinas de compostagem adequadas (com umidade e temperatura controladas), o PLA é degradado em água, CO_2 e matéria orgânica biofertilizante em poucos meses.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Polimerização do Polietileno", "n CH2=CH2 -> -[CH2-CH2]-n", "Quebra da ligação pi do eteno e soma contínua de monômeros"),
                    FormulaOrSyntaxItem("Diferença Estrutural Termofixo", "Presença de ligações cruzadas covalentes tridimensionais", "Impede a fusão térmica e inviabiliza a reciclagem mecânica"),
                    FormulaOrSyntaxItem("Síntese do PLA", "Amido -> Glicose -> Ácido Lático -> Ácido Polilático (PLA)", "Polímero biodegradável de fonte vegetal renovável")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Reciclagem de Pneus e Vulcanização da Borracha",
                        codeOrFormula = "Borracha natural (Poliisopreno) + Enxofre (aquecimento) -> Ligações cruzadas S-S (Vulcanização)",
                        explanation = "A borracha vulcanizada de pneus é um polímero termofixo que não funde ao ser aquecido devido às pontes dissulfeto (-S-S-) que cruzam as cadeias carbônicas. Por essa razão, pneus não podem ser reciclados por simples derretimento em moldes; seu reaproveitamento exige trituração mecânica em pó de borracha para incorporar ao asfalto ecológico ou incineração em fornos de cimenteiras."
                    )
                ),
                goldTip = "Para o ENEM: Termoplástico derrete com o calor e pode ser reciclado mecanicamente. Termofixo NÃO derrete com o calor porque tem ligações cruzadas permanentes!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Química Ambiental e do Cotidiano\nSaneamento, atmosfera, combustíveis e sustentabilidade no ENEM.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Relacione sempre as reações químicas aos ciclos biogeoquímicos e aos impactos ambientais globais."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "quim_amb_01" -> listOf(
                MathExercise(
                    id = "quim_ex_amb_01_a",
                    title = "ENEM: Função da Cal na Coagulação do Tratamento de Água",
                    problemStatement = "Em uma Estação de Tratamento de Água (ETA), a adição do coagulante sulfato de alumínio [Al_2(SO_4)_3] na água bruta provoca a hidrólise ácida dos íons alumínio, tornando a água ligeiramente ácida. Para que ocorra a formação ótima do precipitado gelatinoso de hidróxido de alumínio [Al(OH)_3], que aglomerará as impurezas coloidais em flocos decantáveis, é indispensável adicionar conjuntamente:",
                    options = listOf(
                        "Hidróxido de cálcio (cal hidratada) ou óxido de cálcio para elevar o pH e fornecer íons hidroxila.",
                        "Ácido clorídrico concentrado para acidificar ainda mais a solução e quebrar as moléculas de argila.",
                        "Gás carbônico sob alta pressão para borbulhar a solução e acelerar a evaporação da água.",
                        "Cloreto de sódio em pó para aumentar a salinidade e promover a flotação superficial.",
                        "Álcool etílico anidro para desidratar as bactérias e dissolver os flocos de argila."
                    ),
                    correctIndex = 0,
                    hint = "O Al(OH)3 precisa de meio básico/neutro com abundância de íons OH- fornecidos por uma base como Ca(OH)2 para se formar e precipitar.",
                    stepByStepSolution = "A alternativa A está correta. A hidrólise do cátion Al³⁺ consome íons hidroxila da água e libera íons H⁺, acidificando o meio. Se o pH cair excessivamente, o hidróxido de alumínio não se forma como precipitado gelatinoso, comprometendo toda a floculação. A cal (CaO ou Ca(OH)2) neutraliza a acidez e fornece íons OH⁻ necessários para a formação quantitativa do precipitado de Al(OH)3, viabilizando a remoção da turbidez por decantação."
                )
            )

            "quim_amb_02" -> listOf(
                MathExercise(
                    id = "quim_ex_amb_02_a",
                    title = "ENEM: Mecanismo de Degradação da Camada de Ozônio por Radicais Cloro",
                    problemStatement = "O Protocolo de Montreal (1987) foi um marco histórico global ao estabelecer o banimento progressivo da produção e consumo de substâncias que destroem a camada de ozônio estratosférica, em especial os clorofluorcarbonetos (CFCs). Do ponto de vista químico e cinético, o átomo de cloro liberado na estratosfera após a fotólise dos CFCs é extremamente danoso à camada de ozônio porque atua como:",
                    options = listOf(
                        "Um catalisador homogêneo que acelera a quebra do ozônio e se regenera ao final do ciclo, destruindo milhares de moléculas de O3.",
                        "Um reagente consumido estequiometricamente na proporção de um átomo de cloro para cada molécula de ozônio.",
                        "Um inibidor cinético que desacelera a velocidade natural de regeneração do ozônio pelo oxigênio.",
                        "Um agente redutor que converte o ozônio em ácido clorídrico gasoso na alta atmosfera.",
                        "Um filtro óptico que bloqueia a radiação infravermelha provocando resfriamento global extremo."
                    ),
                    correctIndex = 0,
                    hint = "O radical de cloro (Cl•) participa das etapas intermediárias acelerando a decomposição e é liberado intacto no final da reação.",
                    stepByStepSolution = "A alternativa A está correta. O radical livre de cloro (Cl•) atua como um autêntico catalisador: ele reage com o ozônio formando monóxido de cloro (ClO• + O2) e, em seguida, o ClO• colide com oxigênio atômico regenerando o radical Cl• e gerando mais O2. Como o catalisador não é consumido no balanço global, uma única espécie de cloro pode participar sucessivamente de dezenas de milhares de ciclos catalíticos de destruição de O3."
                )
            )

            "quim_amb_03" -> listOf(
                MathExercise(
                    id = "quim_ex_amb_03_a",
                    title = "ENEM: Rota Química de Síntese do Biodiesel",
                    problemStatement = "O Programa Nacional de Produção e Uso do Biodiesel (PNPB) incentiva a substituição gradual do diesel derivado do petróleo pelo biodiesel de fontes agrícolas renováveis. Na indústria, a principal rota química de obtenção do biodiesel a partir de óleos vegetais (como o de soja) consiste em fazer reagir os triglicerídeos com um álcool de cadeia curta (metanol ou etanol) na presença de soda cáustica. Essa transformação química é classificada como:",
                    options = listOf(
                        "Transesterificação, que converte um triéster de ácidos graxos em três ésteres de cadeia curta e glicerol.",
                        "Esterificação direta de Fischer entre alcanos e ácido sulfúrico fumante.",
                        "Hidrogenação catalítica de ligações duplas para produzir margarina vegetal sólida.",
                        "Craqueamento térmico com despolimerização do polietileno em hidrocarbonetos leves.",
                        "Oxidação enérgica de álcoois terciários produzindo gás carbônico e água pura."
                    ),
                    correctIndex = 0,
                    hint = "Reação que troca a cadeia alcóxi de um éster (glicerídeo) por outra cadeia alcóxi (metila ou etila) = Transesterificação.",
                    stepByStepSolution = "A alternativa A está correta. A transesterificação (ou alcoólise de ésteres) é a reação entre um éster existente (os triglicerídeos do óleo vegetal) e um álcool (metanol ou etanol) catalisada por base (NaOH/KOH). As três ligações éster do triglicerídeo são clivadas, gerando três ésteres alquílicos de ácidos graxos (que constituem o biodiesel pronto para queima) e liberando o glicerol (glicerina) como subproduto de alto valor agregado."
                )
            )

            "quim_amb_04" -> listOf(
                MathExercise(
                    id = "quim_ex_amb_04_a",
                    title = "ENEM: Comportamento de Sabões em Regiões de Água Dura",
                    problemStatement = "Em certas regiões do sertão nordestino e em áreas cársticas ricas em rochas de calcário (CaCO_3), a água de poços artesianos é classificada como 'água dura' devido à elevada concentração de cátions dissolvidos de cálcio (Ca^{2+}) e magnésio (Mg^{2+}). Os moradores dessas localidades relatam que o sabão comum em barra 'não espuma direito' e deixa resíduos esbranquiçados nas roupas e vasilhas. Esse fenômeno ocorre quimicamente porque:",
                    options = listOf(
                        "Os íons cálcio e magnésio reagem com os ânions carboxilato do sabão formando precipitados insolúveis sem propriedades tensoativas.",
                        "O calcário dissolve o oxigênio da água tornando a combustão do sabão impossível.",
                        "A água dura decompõe a glicerina em gases tóxicos que anulam a viscosidade da solução.",
                        "Os cátions metálicos tornam as caudas carbônicas do sabão polares, impedindo a interação com a água.",
                        "A dureza da água reduz o pH para zero, convertendo o sabão em éter volátil."
                    ),
                    correctIndex = 0,
                    hint = "Cátions Ca2+ e Mg2+ precipitam com os sais de ácidos graxos, formando sabões de cálcio insolúveis em água.",
                    stepByStepSolution = "A alternativa A está correta. O sabão comum é composto por sais solúveis de sódio ou potássio de ácidos graxos (R-COO⁻ Na⁺). Na presença de cátions bivalentes como Ca²⁺ e Mg²⁺, ocorre uma reação de dupla troca formando sais de cálcio e magnésio [(R-COO)2Ca], que são insolúveis em água e precipitam como uma borra insolúvel ('sabão de cálcio'). Essa precipitação neutraliza a capacidade anfifílica do produto, impedindo a formação de micelas e espuma até que todos os íons metálicos tenham sido precipitados."
                )
            )

            "quim_amb_05" -> listOf(
                MathExercise(
                    id = "quim_ex_amb_05_a",
                    title = "ENEM: Termoplásticos versus Termofixos na Indústria e Reciclagem",
                    problemStatement = "Dentre os polímeros consumidos pela sociedade contemporânea, as garrafas de refrigerante feitas de politereftalato de etileno (PET) e as sacolas de polietileno (PE) são facilmente coletadas, trituradas, aquecidas, fundidas e remoldadas em novos produtos plásticos em usinas de reciclagem mecânica. Por outro lado, a borracha vulcanizada de pneus velhos e os cabos de panelas de baquelite não podem ser reciclados por simples fusão. Essa diferença crucial no comportamento térmico deve-se:",
                    options = listOf(
                        "À presença de uma rede tridimensional rígida de ligações cruzadas covalentes nos termofixos, que não se rompem com o calor sem decompor o polímero.",
                        "Ao fato de os termoplásticos serem formados exclusivamente por monômeros inorgânicos metálicos.",
                        "À ausência total de carbono nas moléculas que constituem os termofixos como a borracha vulcanizada.",
                        "Às ligações iônicas dos termoplásticos que facilitam o derretimento a temperaturas criogênicas.",
                        "À biodegradabilidade instantânea dos pneus quando descartados em contato com a luz solar."
                    ),
                    correctIndex = 0,
                    hint = "Termoplásticos fundem porque só têm atrações intermoleculares fracas entre cadeias; Termofixos possuem ligações cruzadas covalentes permanentes.",
                    stepByStepSolution = "A alternativa A está correta. Os termoplásticos (como o PET e o polietileno) são constituídos por cadeias lineares ou ramificadas mantidas unidas por forças intermoleculares. Ao serem aquecidos, a energia térmica supera essas atrações fracas e as cadeias deslizam umas sobre as outras, fundindo o plástico para nova moldagem mecânica. Já os termofixos (como a borracha vulcanizada com enxofre e a baquelite) contêm ligações cruzadas covalentes permanentes entre as cadeias, formando uma rede molecular tridimensional única que não funde com o calor, carbonizando se superaquecida."
                )
            )

            else -> emptyList()
        }
    }
}
