package com.example.data.history

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático Aprofundado e Questões Analíticas no Padrão ENEM
 * Módulo 1: História do Brasil Colônia
 * - hist_col_01: A Conquista e os Povos Originários
 * - hist_col_02: Economia e Sociedade Colonial
 * - hist_col_03: A Escravidão Negra e as Resistências
 * - hist_col_04: Revoltas Coloniais e Crise do Sistema
 */
object HistoryColonialDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "hist_col_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # A Conquista e os Povos Originários no ENEM

                    No ENEM, o contato inicial entre colonizadores europeus e as populações nativas não é tratado como uma "descoberta pacífica", mas como um violento processo de conquista, etnocentrismo e choque cultural que reconfigurou radicalmente as estruturas demográficas e ecológicas do continente.

                    ### 1. Diversidade Pré-Cabralina
                    Antes de 1500, o território brasileiro abrigava entre 3 e 5 milhões de indígenas distribuídos em centenas de etnias distintas (troncos linguísticos Tupi-Guarani, Macro-Jê, Aruak e Karib):
                    • **Organização Social:** Ausência de Estado centralizado, de propriedade privada da terra e de moeda mercantil. A terra pertencia à coletividade.
                    • **Divisão do Trabalho:** Baseada estritamente no gênero e na idade (homens na caça, guerra e derrubada de matas; mulheres na agricultura, coleta e cerâmica).
                    • **Cosmovisão e Antropofagia:** A guerra entre tribos não visava à acumulação de territórios para lucro, mas ao prestígio, equilíbrio de forças e vingança honrosa. A antropofagia ritualística (ex: entre os Tupinambás) consistia na assimilação simbólica da coragem e das virtudes do guerreiro inimigo capturado.

                    ### 2. Choque Cultural e a Lente Etnocêntrica
                    Os primeiros relatos coloniais, como a famosa *Carta de Pero Vaz de Caminha* (1500), revelam o etnocentrismo europeu:
                    • **O Mito da 'Falta de Fé, Lei e Rei':** Caminha escreveu que a língua nativa não possuía as letras F, L e R, interpretando a diferença cultural como carência institucional e moral (ausência de Fé católica, Lei civil e Rei absolutista).
                    • **O Indígena Idealizado vs. O 'Selvagem':** Oscilava entre a visão paradisíaca do bom selvagem dócil e a estigmatização do nativo como canibal herético quando este recusava a submissão aos interesses da Coroa.

                    ### 3. As Engrenagens da Violência Colonial
                    • **Escambo Inicial (1500–1530):** Troca de pau-brasil por machados, espelhos e facas. Funcionou enquanto o indígena viu interesse nas ferramentas metálicas.
                    • **Tentativa de Escravização e 'Guerra Justa':** Com a montagem dos engenhos, a Coroa e os colonos tentaram escravizar os nativos. Diante da resistência, justificavam ataques violentos sob a doutrina da "Guerra Justa" (ataques autorizados contra tribos que recusassem a fé católica ou atacassem portugueses).
                    • **Papel Ambíguo dos Jesuítas:** A Companhia de Jesus aldeava indígenas para catequizá-los, protegendo-os parcialmente da escravização direta dos bandeirantes, mas destruindo sua cosmovisão, impondo sedentarismo e facilitando epidemias.
                    • **O Genocídio Biológico:** As "armas invisíveis" (varíola, gripe, sarampo e tuberculose) dizimaram até 90% de aldeias inteiras desprovidas de imunidade biológica aos patógenos euroasiáticos.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Etnocentrismo", "Julgamento da cultura do outro a partir dos parâmetros da sua própria cultura", "Caminha vê 'ausência de fé, lei e rei' em vez de reconhecer a alteridade nativa"),
                    FormulaOrSyntaxItem("Guerra Justa", "Doutrina jurídica/religiosa que legalizava a escravização e massacre indígena", "Autorizada pela Coroa quando tribos rejeitavam a catequese ou resistiam à colonização"),
                    FormulaOrSyntaxItem("Genocídio Biológico", "Impacto epidemiológico devastador de vírus/bactérias trazidos da Europa", "Varíola, gripe e sarampo eliminaram grande parte da população originária")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Análise de Documento Histórico no ENEM",
                        codeOrFormula = "Carta de Caminha (1500): 'Eles não lavram nem criam [...] e estão tão rijos e tão nédios'",
                        explanation = "O ENEM frequentemente usa esse trecho para demonstrar que o olhar do colonizador reduzia a complexa relação ecológica dos indígenas com a floresta a uma 'simplicidade infantil', justificando a tutela portuguesa e a imposição da fé cristã."
                    )
                ),
                goldTip = "No ENEM: Nunca caia na falácia do 'indígena preguiçoso para o trabalho'! A resistência indígena à escravidão envolveu guerras, fugas pelo conhecimento profundo do território e recusa radical da lógica de acumulação mercantil europeia."
            )

            "hist_col_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Economia e Sociedade Colonial: Açúcar, Pecuária e Mineração

                    A colonização do Brasil estruturou-se sob a lógica do **Antigo Sistema Colonial**, cuja finalidade central era a complementariedade à economia metropolitana mercantilista através do monopólio do comércio (o *Pacto Colonial* ou *Exclusivo Metropolitano*).

                    ### 1. A Plantation Açucareira (Séculos XVI e XVII)
                    O açúcar foi a primeira mercadoria de alto valor a viabilizar a ocupação territorial efetiva:
                    • **O Tripé da Plantation:** Latifúndio (grandes extensões de terra concedidas por sesmarias), monocultura voltada à exportação e trabalho escravo compulsório.
                    • **A Aliança Luso-Flamenga:** Portugal detinha a terra e o monopólio da navegação; os holandeses financiavam os maquinários dos engenhos, compravam o açúcar bruto, refinavam-no em Amsterdã e distribuíam na Europa.
                    • **Sociedade Patriarcal e Polarizada:** Estrutura estamental com mobilidade social quase nula. No topo, os "senhores de engenho" detinham poder econômico, militar e político (através das Câmaras Municipais). Na base desumanizada, a grande massa de escravizados.

                    ### 2. A Pecuária e a Interiorização do Território
                    Enquanto a cana ocupava a faixa litorânea fértil de massapê, a Coroa proibiu a criação de gado a menos de 10 léguas da costa (Carta Régia de 1701) para preservar terras para a exportação:
                    • O gado avançou pelo sertão nordestino seguindo o curso do Rio São Francisco ("Rio dos Currais") e também pelos pampas sulistas.
                    • A pecuária utilizou primordialmente mão de obra livre e mestiça (vaqueiros pagos pelo sistema de *quarteação* — recebiam 1 de cada 4 bezerros).

                    ### 3. A Mineração no Século XVIII e a Reconfiguração Colonial
                    A descoberta de ouro nos sertões das Gerais gerou uma profunda transformação socioeconômica:
                    • **Deslocamento do Eixo:** A capital foi transferida de Salvador para o Rio de Janeiro (1763) para facilitar o escoamento e a fiscalização do ouro.
                    • **Urbanização e Sociedade Multifacetada:** Surgimento de cidades como Vila Rica (Ouro Preto), Mariana e Diamantina. Formação de classes médias urbanas (letrados, artesãos, tropeiros, pequenos comerciantes e clérigos).
                    • **Integração do Mercado Interno:** Pela primeira vez, regiões distantes da colônia se conectaram economicamente (o Sul fornecia gado e mulas, o Nordeste abastecia com carne-seca e açúcar, o Sudeste consumia).
                    • **Artes e Barroco Mineiro:** O apogeu cultural expresso nas esculturas e igrejas de Aleijadinho e nas pinturas de Mestre Ataíde, mesclando religiosidade e ironia crioula.
                    • **O Fiscalismo Régio Agressivo:** A Intendência das Minas cobrava o *Quinto* (20% do ouro retido nas Casas de Fundição). Diante do declínio das jazidas na segunda metade do século, a Coroa instituiu a cota de 100 arrobas anuais e ameaçou com a *Derrama* (cobrança forçada de dívidas fiscais confiscando bens da população), estopim para a Inconfidência Mineira.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Plantation", "Latifúndio + Monocultura + Mão de obra Escrava + Mercado Externo", "Modelo econômico colonial de exploração agrária"),
                    FormulaOrSyntaxItem("Pacto Colonial", "Colônia só vende matéria-prima à Metrópole e só compra manufaturas dela", "Exclusivo metropolitano para acumulação mercantil em Portugal"),
                    FormulaOrSyntaxItem("Derrama", "Cobrança arbitrária e compulsória para atingir a cota fiscal de 100 arrobas", "Mecanismo opressivo que catalisou a revolta das elites mineiras em 1789")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Impacto da Mineração na Mobilidade Social",
                        codeOrFormula = "Sociedade do Açúcar (Rígida e Bipolar) vs Sociedade do Ouro (Mais Dinâmica e Urbana)",
                        explanation = "O ENEM destaca que a mineração permitiu uma maior flexibilidade social: a existência de 'negros de ganho', mulheres forras no comércio ambulante (quitandeiras) e o crescimento de estratos intermediários urbanos inexistentes no isolamento dos engenhos rurais."
                    )
                ),
                goldTip = "Fique atento ao tropeirismo no ENEM: as tropas de mulas que desciam do Rio Grande do Sul e Paraná para abastecer as Minas Gerais foram responsáveis diretas pela criação das primeiras rotas do mercado interno brasileiro."
            )

            "hist_col_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # A Escravidão Negra e as Múltiplas Formas de Resistência

                    A escravidão negra foi a instituição estruturante da formação socioeconômica, política e cultural do Brasil durante mais de 350 anos. No ENEM, as questões abordam a violência sistêmica do cativeiro combinada necessariamente com o protagonismo e a agência histórica dos escravizados.

                    ### 1. O Tráfico Transatlântico como Grande Negócio
                    Entre os séculos XVI e XIX, mais de 4,8 milhões de africanos escravizados foram desembarcados no Brasil (o país que mais importou pessoas escravizadas nas Américas):
                    • **Comércio Triangular e Lucro Burguês:** O tráfico transatlântico de escravizados não existia por 'falta de opção', mas porque gerava gigantescos lucros tributários para a Coroa e acumulava capital para os traficantes luso-brasileiros.
                    • **A Travessia nos Tumbeiros:** Condições desumanas a bordo dos navios negreiros, superlotação, desnutrição, doenças e taxa de mortalidade de até 25% na travessia oceânica.

                    ### 2. A Vida nas Senzalas e a Lógica da Desumanização
                    • O escravizado era reduzido juridicamente à condição de *res* (coisa/mercadoria sem direitos civis).
                    • Jornadas de trabalho de até 18 horas nos períodos de safra e moagem de cana; expectativa de vida média de um escravizado no engenho inferior a 10 anos após o desembarque.
                    • Punições exemplares no pelourinho, uso de máscaras de flandres e correntes para manter o terror psicológico e disciplinar os corpos.

                    ### 3. A Resistência Escrava: Ativa, Cotidiana e Cultural
                    A historiografia contemporânea e o ENEM rejeitam a ideia de submissão passiva. Os cativos resistiram por múltiplos caminhos:
                    • **Resistências Diretas e Conflitivas:** Revoltas armadas, assassinatos de feitores violentos, suicídio ritualístico (como negação do valor da mercadoria ao senhor) e fugas individuais ou em bando.
                    • **Os Quilombos e Mocambos:** Espaços de liberdade e recomposição comunitária em matas fechadas. O **Quilombo dos Palmares** (na Serra da Barriga, Alagoas), liderado por Ganga Zumba, Dandara e Zumbi, resistiu por quase um século reunindo mais de 20 mil pessoas com agricultura policultora e metalurgia própria.
                    • **Negociações e Brechas Camponesas:** A concessão do cultivo de pequenas roças aos domingos ('brecha camponesa') permitia a venda de excedentes na feira e, eventualmente, a compra da carta de alforria.
                    • **Resistência Cultural e Religiosa:** O sincretismo religioso (associação de Orixás a santos católicos para burlar a vigilância da Igreja), a formação de irmandades leigas (como a Irmandade do Rosário dos Homens Pretos), a capoeira, as cantigas de trabalho e a transmissão oral dos saberes ancestrais africanos.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Agência Histórica", "Os escravizados atuavam como sujeitos ativos de sua história, não meras vítimas", "Conceito historiográfico cobrado em todas as edições do ENEM"),
                    FormulaOrSyntaxItem("Sincretismo", "Fusão de práticas e divindades africanas com o catolicismo barroco", "Estratégia de preservação da espiritualidade e identidade diante da coerção"),
                    FormulaOrSyntaxItem("Brecha Camponesa", "Autonomia de produção agrícola em pequenas faixas de terra cedidas aos domingos", "Possibilitava acúmulo de pecúlio para negociação de cartas de alforria")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Compreendendo o Quilombo dos Palmares",
                        codeOrFormula = "Palmares como sociedade multiétnica e alternativa ao sistema escravista",
                        explanation = "O ENEM não retrata Palmares apenas como um esconderijo militar, mas como uma sociedade complexa que abrigava negros fugitivos, indígenas desaldeados e brancos pobres marginalizados, desenvolvendo laços comerciais com povoados vizinhos."
                    )
                ),
                goldTip = "Cuidado com o conceito de 'alforria': a carta de alforria era frequentemente condicionada (o liberto ainda precisava prestar serviços ao senhor até a morte deste) e podia ser revogada por 'ingratidão'. A alforria nunca representou generosidade senhorial, mas sim conquista ou instrumento de controle."
            )

            "hist_col_04" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Revoltas Coloniais e a Crise do Antigo Sistema Colonial

                    No final do século XVII e ao longo do século XVIII, a colônia foi palco de dois grupos fundamentais de rebeliões. O ENEM cobra com frequência cirúrgica a **diferença estrutural entre movimentos nativistas e movimentos emancipacionistas**.

                    ### 1. Movimentos Nativistas (Séculos XVII e Início do XVIII)
                    Revoltas provocadas por atritos locais, disputas comerciais ou descontentamento com monopólios da Coroa, **sem projeto de independência ou separação de Portugal**:
                    • **Guerra dos Emboabas (1707–1709, MG):** Conflito violento entre bandeirantes paulistas (descobridores do ouro) e os "emboabas" (forasteiros vindos de Portugal e de outras capitanias). Resultou no massacre do Capão da Traição e na criação da Capitania de São Paulo e Minas de Ouro.
                    • **Guerra dos Mascates (1710–1711, PE):** Disputa política entre os decadentes aristocratas senhores de engenho de Olinda e os prósperos comerciantes portugueses ("mascates") do Recife, que conquistaram a elevação da povoação a vila independente.
                    • **Revolta de Beckman (1684, MA):** Revolta contra os abusos da Companhia de Comércio do Maranhão e contra a atuação dos jesuítas no controle da mão de obra indígena.
                    • **Revolta de Filipe dos Santos (1720, MG):** Protesto popular contra a criação das Casas de Fundição e a proibição da circulação do ouro em pó.

                    ### 2. A Crise do Sistema e os Movimentos Emancipacionistas
                    No final do século XVIII, a difusão dos ideais do **Iluminismo**, a Independência dos Estados Unidos (1776) e a Revolução Francesa (1789) inspiraram movimentos com **projeto explícito de ruptura colonial e proclamação da República**:

                    ### 3. Inconfidência Mineira (1789) vs. Conjuração Baiana (1798)
                    O ENEM frequentemente confronta esses dois marcos para analisar as diferenças de classe social, objetivos e desfechos:

                    | Critério | Inconfidência Mineira (1789) | Conjuração Baiana / Búzios (1798) |
                    | :--- | :--- | :--- |
                    | **Composição** | Elitista: poetas, magistrados, mineradores endividados e militares graduados | Popular: alfaiates, soldados rasos, artesãos, negros forros e escravizados |
                    | **Influência** | Iluminismo moderado e Independência dos EUA (1776) | Iluminismo radical da fase jacobina francesa e Revolução do Haiti (1791) |
                    | **Objetivos** | República em Minas, universidade em Vila Rica, perdão das dívidas tributárias | República democrática, abertura comercial, igualdade racial e salários justos |
                    | **Escravidão** | Sem consenso; a maioria dos líderes era dona de escravos e não propunha abolição | Defesa intransigente da **abolição imediata da escravidão** |
                    | **Desfecho** | Traição de Silvério dos Reis; penas brandas/degredo para a elite rica; pena capital apenas para Tiradentes | Enforcamento e esquartejamento de líderes populares (Lucas Dantas, Manuel Faustino, João de Deus) |
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Nativistas vs Emancipacionistas", "Nativistas = Conflitos regionais sem separação | Emancipacionistas = Ruptura e República", "Chave interpretativa essencial para questões de história colonial no ENEM"),
                    FormulaOrSyntaxItem("Inconfidência Mineira", "Elitista + Inspirada nos EUA + Não defendia a abolição da escravidão", "Revolta contra a ameaça da derrama em 1789"),
                    FormulaOrSyntaxItem("Conjuração Baiana (Búzios)", "Popular + Igualitária + Defesa irrestrita do fim da escravidão", "Inspirada no jacobinismo francês e no Haiti em 1798")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Construção da Memória de Tiradentes na República",
                        codeOrFormula = "Tiradentes como mártir republicano pós-1889",
                        explanation = "O ENEM aborda como a Proclamação da República (1889) resgatou a figura de Tiradentes, construindo uma imagem visual semelhante à de Cristo para criar um herói nacional cívico capaz de unificar o imaginário popular."
                    )
                ),
                goldTip = "Atenção máxima: a Conjuração Baiana é também chamada de 'Revolta dos Alfaiates' ou 'Revolta dos Búzios'. Se a questão falar em projeto republicano com abolição da escravidão e protagonismo de homens negros, a resposta é Bahia (1798)!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "Estudo do período colonial no Brasil focado em cidadania, conflitos e relações sociais.",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Revise sempre o contexto social dos sujeitos históricos envolvidos."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "hist_col_01" -> listOf(
                MathExercise(
                    id = "hist_ex_col_01_a",
                    title = "ENEM: Etnocentrismo e Contato Cultural",
                    problemStatement = "Na famosa carta enviada a D. Manuel I em 1500, Pero Vaz de Caminha descreve os habitantes da terra recém-avistada afirmando que eles 'parece-me gente de tal inocência que, se homem os entendesse e eles a nós, seriam logo cristãos, porque eles não têm nem entendem em nenhuma crença'.\n\nSob a ótica da historiografia crítica contemporânea avaliada no ENEM, esse trecho expressa:",
                    options = listOf(
                        "Um reconhecimento tolerante da diversidade e da complexidade espiritual das cosmologias originárias.",
                        "Uma percepção etnocêntrica que interpretava a diferença cultural como ausência moral e justificava a tutela civilizatória europeia.",
                        "Uma recusa explícita em catequizar os nativos diante da constatação de que eles já possuíam leis e monarquia.",
                        "Um diagnóstico científico baseado em estudos linguísticos avançados da família linguística Tupi-Guarani.",
                        "Uma proposta diplomática de convivência entre reinos soberanos em regime de igualdade jurídica internacional."
                    ),
                    correctIndex = 1,
                    hint = "Repare na palavra 'inocência' e na suposta 'falta de crença': o colonizador toma seus próprios referenciais religiosos como padrão universal de civilização.",
                    stepByStepSolution = "A alternativa B está correta. A carta de Pero Vaz de Caminha é um clássico exemplo de documento etnocêntrico: o colonizador europeu, ao não encontrar igrejas, templos cristãos ou dogmas monoteístas nos moldes ocidentais, deduz equivocadamente que os indígenas 'não têm fé nem crença'. Essa deslegitimação da cultura e da espiritualidade nativa serviu como justificativa ideológica para a catequese forçada e a submissão dos povos originários à Coroa e à Igreja."
                ),
                MathExercise(
                    id = "hist_ex_col_01_b",
                    title = "ENEM: A Violência e o Impacto Epidemiológico",
                    problemStatement = "Durante os primeiros cem anos de colonização no litoral brasileiro, estima-se que a população indígena sofreu uma redução demográfica vertiginosa, calculada por historiadores em perdas superiores a 80% dos habitantes das aldeias litorâneas.\n\nAssinale o fator que, aliado aos confrontos militares e à escravização, desempenhou o papel mais devastador nesse processo de dizimação:",
                    options = listOf(
                        "A transmigração voluntária das comunidades indígenas para a Europa em busca de salários nos portos metropolitanos.",
                        "O choque biológico decorrente da introdução de patógenos euroasiáticos (como varíola e gripe) para os quais os nativos não possuíam imunidade prévia.",
                        "A perda de territórios de caça provocada unicamente pelo esgotamento natural das árvores de pau-brasil sem intervenção humana.",
                        "A adoção espontânea do catolicismo que proibia qualquer forma de reprodução biológica nas aldeias nativas.",
                        "A escassez generalizada de alimentos provocada pela recusa dos colonos em praticar o escambo de facas de ferro."
                    ),
                    correctIndex = 1,
                    hint = "Pense no conceito de 'armas invisíveis da conquista': o isolamento milenar das Américas deixou as populações nativas biologicamente vulneráveis a vírus comuns no Velho Mundo.",
                    stepByStepSolution = "A alternativa B está correta. As populações das Américas estiveram biologicamente isoladas da Eurásia e África por milênios. A introdução de patógenos como a varíola, o sarampo, a gripe e a tuberculose causou verdadeiras hecatombes demográficas (o 'genocídio biológico'), dizimando aldeias inteiras antes mesmo que houvesse confronto bélico direto."
                )
            )

            "hist_col_02" -> listOf(
                MathExercise(
                    id = "hist_ex_col_02_a",
                    title = "ENEM: Economia do Açúcar e Estrutura Social",
                    problemStatement = "O sistema produtivo do açúcar implantado pela Coroa portuguesa no Nordeste colonial estruturou-se sob a base da 'plantation'. As principais características dessa estrutura produtiva e social correspondiam a:",
                    options = listOf(
                        "Pequenas propriedades familiares policultoras com produção voltada ao abastecimento dos centros urbanos locais.",
                        "Grandes propriedades monocultoras (latifúndios), trabalho escravo compulsório e produção prioritariamente voltada à exportação mercantil.",
                        "Indústrias têxteis estatais financiadas pela Companhia das Índias Orientais com mão de obra assalariada.",
                        "Propriedades cooperativas com distribuição paritária do lucro entre senhores de engenho e trabalhadores cativos.",
                        "Sistemas comunais de cultivo de trigo e uva para o consumo de colônias vizinhas do Prata."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se da fórmula da plantation: Latifúndio + Monocultura + Mão de obra Escrava + Mercado Externo.",
                    stepByStepSolution = "A alternativa B está correta. A montagem da plantation açucareira exigia grandes extensões de terra (latifúndios concedidos via sesmarias), a especialização em um gênero de alto valor no mercado internacional europeu (monocultura da cana-de-açúcar) e a utilização massiva de mão de obra compulsoriamente escravizada, resultando em uma sociedade rural, patriarcal e profundamente desigual."
                ),
                MathExercise(
                    id = "hist_ex_col_02_b",
                    title = "ENEM: Mineração e a Integração do Espaço Colonial",
                    problemStatement = "No século XVIII, a descoberta de jazidas de ouro e diamantes na região das Minas Gerais desencadeou profundas transformações socioespaciais na colônia. Dentre essas transformações, destaca-se:",
                    options = listOf(
                        "O esvaziamento total do Rio de Janeiro e a fixação definitiva da capital colonial em Salvador para proteger as rotas da prata.",
                        "A proliferação de centros urbanos com maior complexidade social e a articulação de um inédito mercado interno abastecido por tropas de mulas.",
                        "A proibição do tráfico transatlântico de escravizados africanos para substituir toda a mão de obra mineira por artesãos assalariados europeus.",
                        "O abandono completo de qualquer cobrança tributária por parte da Coroa para estimular a livre iniciativa dos garimpeiros.",
                        "A dissolução das divisões sociais coloniais em favor de uma república parlamentarista autônoma em Ouro Preto."
                    ),
                    correctIndex = 1,
                    hint = "Ao contrário do isolamento dos engenhos de açúcar no litoral, a mineração atraiu multidões para o interior, exigindo comida, vestimentas e transporte vindos de outras capitanias.",
                    stepByStepSolution = "A alternativa B está correta. A atividade mineradora, por ser eminentemente urbana e concentrada no interior, impulsionou a vida nas cidades (Vila Rica, Mariana), gerou uma classe média de letrados e prestadores de serviços e, fundamentalmente, integrou economicamente a colônia: o Sul passou a fornecer mulas e charque, enquanto o Nordeste enviava açúcar e aguardente para alimentar a população mineira através dos tropeiros."
                )
            )

            "hist_col_03" -> listOf(
                MathExercise(
                    id = "hist_ex_col_03_a",
                    title = "ENEM: A Agência e as Resistências Escravas",
                    problemStatement = "A historiografia recente enfatiza que os escravizados africanos e seus descendentes no Brasil não foram receptores passivos da opressão senhorial, mas sim agentes históricos que articularam variadas estratégias de sobrevivência e enfrentamento.\n\nConstitui um exemplo dessa resistência por vias socioculturais e de negociação:",
                    options = listOf(
                        "A renúncia voluntária de suas línguas, rituais e crenças para adotar a ideologia senhorial sem qualquer atrito.",
                        "A criação de irmandades religiosas leigas (como a do Rosário) e a manutenção de práticas de sincretismo para preservar laços identitários e comunitários.",
                        "A recusa em fugir para os quilombos em razão da crença generalizada na benevolência dos capitães do mato.",
                        "A assinatura de contratos de trabalho assalariado registrados nos cartórios coloniais antes de cada jornada nas lavouras.",
                        "A submissão incontestável às determinações do pelourinho como ritual de aceitação da condição de mercadoria."
                    ),
                    correctIndex = 1,
                    hint = "As irmandades leigas católicas e os terreiros eram espaços onde negros forros e cativos se organizavam para manter solidariedade, comprar alforrias e honrar seus antepassados.",
                    stepByStepSolution = "A alternativa B está correta. A resistência à escravidão operou tanto de forma violenta e de ruptura (quilombos, revoltas) quanto de forma cotidiana e simbólica. As irmandades religiosas de negros, sob a aparência de devoção cristã, funcionavam como importantes redes de solidariedade, apoio mútuo, ajuda para compra de cartas de alforria e preservação camuflada de tradições religiosas de matriz africana (sincretismo)."
                ),
                MathExercise(
                    id = "hist_ex_col_03_b",
                    title = "ENEM: Significado Histórico do Quilombo dos Palmares",
                    problemStatement = "O Quilombo dos Palmares, que floresceu ao longo de quase todo o século XVII na região da Serra da Barriga, representou para a ordem colonial escravocrata uma ameaça constante porque:",
                    options = listOf(
                        "Defendia a restauração da Coroa holandesa no Nordeste em troca da concessão de sesmarias açucareiras para nobres batavos.",
                        "Constituía uma experiência de organização social, política e econômica autônoma, provando a viabilidade de uma sociedade fora do cativeiro e do exclusivo metropolitano.",
                        "Contava com frotas de navios de guerra para bloquear o comércio marítimo português em Lisboa.",
                        "Exigia que a Coroa aumentasse os impostos sobre o gado para financiar o exército real contra os bandeirantes paulistas.",
                        "Era formado exclusivamente por mercadores ingleses contrários ao consumo de açúcar refinado."
                    ),
                    correctIndex = 1,
                    hint = "Palmares não era apenas um refúgio: abrigava milhares de pessoas, produzia alimentos diversos, forjava metais e desafiava o mito de que o negro só podia viver na condição de escravo.",
                    stepByStepSolution = "A alternativa B está correta. Palmares abrigou mais de 20 mil pessoas e sobreviveu por quase um século. Sua existência desmistificava o discurso colonial de submissão do negro, praticava agricultura diversificada (milho, feijão, mandioca) e desafiava a autoridade da Coroa e dos senhores de engenho, funcionando como um polo constante de atração para cativos fugidos de todo o Nordeste."
                )
            )

            "hist_col_04" -> listOf(
                MathExercise(
                    id = "hist_ex_col_04_a",
                    title = "ENEM: Nativismo vs Emancipacionismo",
                    problemStatement = "A historiografia tradicional e as provas do ENEM diferenciam com rigor os movimentos nativistas dos movimentos emancipacionistas ocorridos no Brasil colonial. A diferença fundamental entre essas duas categorias de revoltas reside no fato de que:",
                    options = listOf(
                        "As revoltas nativistas pretendiam a anexação do território brasileiro à Coroa espanhola, ao passo que as emancipacionistas queriam manter o pacto colonial com Portugal.",
                        "As revoltas nativistas questionavam apenas atritos regionais e monopólios fiscais específicos sem propor a independência, enquanto as emancipacionistas propunham a ruptura com a metrópole e a proclamação republicana.",
                        "Os movimentos nativistas contavam exclusivamente com a participação de negros escravizados, enquanto os emancipacionistas eram liderados unicamente por jesuítas estrangeiros.",
                        "As revoltas nativistas obtiveram a independência imediata de São Paulo e Pernambuco, enquanto as emancipacionistas foram todas pacificadas sem qualquer punição.",
                        "Ambos os movimentos tinham a mesma pauta única: exigir da Coroa portuguesa o aumento dos impostos cobrados nas Casas de Fundição."
                    ),
                    correctIndex = 1,
                    hint = "Pense no objetivo final: revoltas como Emboabas e Mascates queriam resolver questões locais com a metrópole; a Inconfidência Mineira e a Conjuração Baiana queriam romper os laços coloniais.",
                    stepByStepSolution = "A alternativa B está correta. Os movimentos nativistas (Guerra dos Emboabas, Guerra dos Mascates, Beckman) eram circunscritos a descontentamentos locais e privilégios de grupos regionais, mantendo lealdade ao Rei. Já as conjurações do final do século XVIII (Inconfidência Mineira de 1789 e Conjuração Baiana de 1798) eram emancipacionistas: influenciadas pelas Revoluções Atlânticas e pelo Iluminismo, questionavam a própria existência do sistema colonial e propunham a independência política e a formação de repúblicas."
                ),
                MathExercise(
                    id = "hist_ex_col_04_b",
                    title = "ENEM: Comparação Social nas Conjurações do Século XVIII",
                    problemStatement = "Ao comparar a Inconfidência Mineira (1789) com a Conjuração Baiana ou Revolta dos Búzios (1798), percebe-se uma distinção basilar quanto à sua base social e às suas reivindicações populares. Essa distinção evidencia-se no fato de que a Conjuração Baiana:",
                    options = listOf(
                        "Foi liderada exclusivamente por latifundiários que pretendiam manter intacta a escravidão e expandir o latifúndio açucareiro.",
                        "Apresentou composição predominantemente popular (alfaiates, soldados rasos e forros) e defendeu com firmeza a abolição imediata da escravidão e a igualdade de direitos civis.",
                        "Defendeu a manutenção da monarquia absolutista portuguesa desde que D. Maria I concedesse títulos de nobreza a todos os baianos.",
                        "Recusou os ideais da Revolução Francesa e se inspirou apenas na teologia escolástica medieval da Contrarreforma.",
                        "Foi perdoada integralmente pela Coroa, diferentemente de Minas Gerais, onde todos os inconfidentes ricos foram executados na forca."
                    ),
                    correctIndex = 1,
                    hint = "Lembre-se do nome 'Revolta dos Alfaiates': seus líderes eram homens pobres, artesãos e negros livres como Lucas Dantas e Manuel Faustino, que colocaram a luta contra a escravidão e o preconceito racial no centro da pauta.",
                    stepByStepSolution = "A alternativa B está correta. Enquanto a Inconfidência Mineira foi um movimento de elite (mineradores, poetas e magistrados) sem consenso sobre o fim da escravidão, a Conjuração Baiana de 1798 teve forte corte popular, influenciada pela fase radical jacobina da Revolução Francesa e pela Revolução Haitiana. Seus líderes reivindicavam a proclamação da República, o aumento do soldo militar, o livre comércio e a abolição incondicional da escravidão, sofrendo uma repressão desproporcional e violenta por parte das autoridades coloniais."
                )
            )

            else -> emptyList()
        }
    }
}
