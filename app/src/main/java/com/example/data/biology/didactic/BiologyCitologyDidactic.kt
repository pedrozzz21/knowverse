package com.example.data.biology.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo didático aprofundado de Biologia ENEM — Módulo 1: Citologia e Bioquímica Celular (Aulas bio_cit_01 a bio_cit_05).
 * Estrutura rigorosa em 12 seções por aula, fundamentada em Alberts (Biologia Molecular da Célula), Junqueira & Carneiro, OpenStax Biology 2e e Matriz do ENEM / INEP.
 */
object BiologyCitologyDidactic {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "bio_cit_01" -> bundleBioCit01
            "bio_cit_02" -> bundleBioCit02
            "bio_cit_03" -> bundleBioCit03
            "bio_cit_04" -> bundleBioCit04
            "bio_cit_05" -> bundleBioCit05
            else -> null
        }
    }

    // =========================================================================
    // AULA bio_cit_01: Teoria Celular, Envoltórios e Citoesqueleto
    // =========================================================================
    private val bundleBioCit01 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A célula é a unidade morfofisiológica fundamental de todos os seres vivos na Terra. Formulada no século XIX por Matthias Schleiden, Theodor Schwann e Rudolf Virchow, a **Teoria Celular** estabeleceu três postulados basilares: todos os organismos são constituídos por uma ou mais células; a célula é a menor unidade com as propriedades vitais; e toda célula provém unicamente da divisão de uma célula pré-existente (*omnis cellula e cellula*). No ENEM, o estudo citológico não se restringe à descrição estática de organelas, mas investiga a dinâmica da membrana plasmática, o equilíbrio osmótico e o transporte transmembrana em contextos médicos, industriais e ecológicos.

## 2. Objetivos de Aprendizagem
- Diferenciar células **procariontes** (bactérias e arqueas, sem carioteca e sem organelas membranosas) de células **eucariontes** (animais, vegetais, fungos e protozoários, compartimentadas internamente).
- Descrever a arquitetura do **Mosaico Fluido** da membrana plasmática (Singer e Nicolson, 1972): bicamada lipídica fosfolipídica anfifílica, colesterol e proteínas integrais e periféricas.
- Analisar a função biológica do **Glicocálix** no reconhecimento celular, histocompatibilidade e imunidade.
- Diferenciar os tipos de paredes celulares: bacteriana (peptidoglicano), fúngica (quitina) e vegetal (celulose).
- Caracterizar os elementos do **Citoesqueleto**: microfilamentos de actina, microtúbulos de tubulina e filamentos intermediários.
- Dominar os mecanismos de transporte transmembrana:
  - **Transporte Passivo** (sem gasto de ATP, a favor do gradiente): difusão simples, difusão facilitada e **Osmose**.
  - **Transporte Ativo** (com hidrólise de ATP, contra o gradiente): **Bomba de Sódio-Potássio (\( \text{Na}^+/\text{K}^+ \))**.
  - **Transporte em Bloco**: endocitose (fagocitose e pinocitose) e exocitose.
- Prever o comportamento de hemácias humanas e células vegetais em meios **isotônicos**, **hipotônicos** (lise celular vs. turgência vegetal) e **hipertônicos** (crenação vs. plasmólise).

## 3. Pré-requisitos
- Bioquímica de lipídios e proteínas (Física/Química e Biologia Geral).
- Concentração de soluções e pressão osmótica (Química, Soluções).

## 4. Explicação Teórica Aprofundada

### 1. Organização Celular: Procariontes vs. Eucariontes
- **Procariontes:** Ausência de envoltório nuclear delimitador (DNA circular disperso na região nucleoide), ausência de sistema de endomembranas e organelas compartimentadas (sem mitocôndrias, Golgi ou retículo); possuem ribossomos menores do tipo 70S e plasmídeos (pequenos anéis de DNA móvel com genes de resistência a antibióticos).
- **Eucariontes:** Núcleo verdadeiro revestido por dupla membrana com poros (carioteca), cromatina condensada com histonas, citoesqueleto complexo e organelas membranosas especializadas (ribossomos 80S).

### 2. A Membrana Plasmática: O Modelo do Mosaico Fluido
A membrana plasmática tem espessura de \( 7\text{ a } 10\text{ nm} \):
- **Bicamada Fosfolipídica:** Moléculas anfifílicas com cabeça polar hidrofílica (fosfato e glicerol) voltada para os meios aquosos intra e extracelular, e duas caudas apolares hidrofóbicas (ácidos graxos) voltadas para o interior da bicamada.
- **Fluidez da Membrana:** Os lipídios realizam movimentos laterais rápidos e rotações. Nos animais, o **Colesterol** intercalado atua como regulador térmico de fluidez: em altas temperaturas impede fluidez excessiva; em baixas temperaturas impede o empacotamento e congelamento da membrana.
- **Glicocálix:** Rede de glicoproteínas e glicolipídios na face externa que atua no reconhecimento celular (sistema sanguíneo ABO), ancoragem e proteção mecânica.

### 3. Citoesqueleto
- **Microfilamentos de Actina (\( \approx 7\text{ nm} \)):** Movimentos celulares (emissão de pseudópodes na fagocitose), ciclose em células vegetais e citocinese na divisão animal.
- **Microtúbulos de Tubulina (\( \approx 25\text{ nm} \)):** Formam os centríolos, o fuso mitótico, cílios e flagelos (arranjo \( 9+2 \)) e atuam como "trilhos" para motores moleculares (cinesina e dineína).
- **Filamentos Intermediários (\( \approx 10\text{ nm} \), ex.: Queratina):** Conferem resistência mecânica contra estiramento e tensão aos tecidos epiteliais.

### 4. Dinâmica Osmótica: Célula Animal vs. Vegetal
A osmose é a difusão da água através de uma membrana semipermeável do meio **hipotônico** (menor concentração de soluto / mais diluído) para o meio **hipertônico** (maior concentração de soluto / mais concentrado):
1. **Em Meio Hipertônico:**
   - Célula Animal (Hemácia): Perde água e enruga-se (**Crenação**).
   - Célula Vegetal: A água sai do vacúolo central, o citoplasma retrai e a membrana plasmática descola da parede celular rígida: a célula sofre **Plasmólise**. (Ao ser devolvida à água pura, absorve água e recupera o volume original: **Deplasmólise**).
2. **Em Meio Hipotônico:**
   - Célula Animal: Absorve água continuamente até romper a membrana plasmática (**Lise Celular / Hemólise**).
   - Célula Vegetal: Absorve água até o vacúolo distender-se contra a parede celulósica. A parede celular rígida exerce uma contra-pressão elástica mecânica que equilibra o influxo de água: a célula fica **Túrgida**, mas **NUNCA sofre lise celular**!

### 5. A Bomba de Sódio-Potássio (\( \text{Na}^+/\text{K}^+ \text{-ATPase} \))
Transportador ativo primário fundamental para a homeostase celular:
- Para cada molécula de ATP hidrolisada em ADP + Pi, a bomba ejeta **\( 3\text{ íons Na}^+ \)** para o meio extracelular e internaliza **\( 2\text{ íons K}^+ \)** para o meio intracelular, ambos contra seus gradientes químicos de concentração.
- Mantém o meio intracelular rico em \( \text{K}^+ \) e o meio extracelular rico em \( \text{Na}^+ \), gerando a polarização elétrica de repouso das membranas (\( \approx -70\text{ mV} \)) nos neurônios e fibras musculares.

## 5. Definições e Conceitos
- **Permeabilidade Seletiva:** Capacidade da membrana de controlar ativamente a entrada e saída de substâncias: gases (\( \text{O}_2, \text{CO}_2 \)) e moléculas lipossolúveis atravessam livremente; íons carregados e glicose exigem proteínas carreadoras ou canais.
- **Aquaporinas:** Canais proteicos transmembrana especializados que aceleram o transporte de água por osmose.
- **Fagocitose:** Englobamento de partículas sólidas grandes (bactérias, restos celulares) por emissão de pseudópodes mediada por actina.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Conservação de Alimentos por Salgamento
Por que o método tradicional de salga da carne-seca (charque) ou o uso de caldas concentradas de açúcar em compotas de frutas impede a proliferação de bactérias e fungos decompositores?
- **Resolução:**
  1. A alta concentração externa de sal ou açúcar torna o meio extremamente **hipertônico** em relação ao citoplasma celular dos microrganismos.
  2. Ao entrarem em contato com o alimento salgado, as bactérias e esporos fúngicos perdem água rapidamente por **osmose** para o exterior.
  3. A intensa desidratação celular inibe a atividade enzimática e causa colapso metabólico ou morte dos microrganismos por plasmólise, conservando o alimento por longos períodos.

### Exemplo 2: Resposta de Hemácias a Soluções Fisiológicas
Um técnico de enfermagem aplica por engano uma injeção intravenosa de água destilada pura na veia de um paciente em vez de soro fisiológico (\( \text{NaCl } 0{,}9\% \)). O que ocorrerá com as hemácias do sangue desse paciente?
- **Resolução:**
  - A água destilada é amplamente **hipotônica** em relação ao citoplasma das hemácias humanas (que é isotônico a \( 0{,}9\% \) de cloreto de sódio).
  - A água entrará massivamente nas hemácias por osmose. Como a célula animal não possui parede celular protetora para conter a pressão hidrostática, as hemácias se expandirão e sofrerão **lise celular (hemólise)**, liberando hemoglobina no plasma e causando falência renal aguda.

## 7. Aplicações Práticas e Modelagem
- **Dessalinização por Osmose Reversa:** Aplicação de alta pressão mecânica sobre a água do mar para forçar a passagem da água através de membranas poliméricas semipermeáveis no sentido inverso ao natural, purificando água potável no semiárido nordestino e em regiões costeiras.
- **Medicamentos Quimioterápicos (Taxol e Colchicina):** Bloqueiam a polimerização ou despolimerização dos microtúbulos de tubulina do citoesqueleto, impedindo a formação do fuso mitótico e paralisando a proliferação descontrolada de células cancerosas.

## 8. Exercícios de Fixação
1. Qual componente lipídico exclusivo das membranas plasmáticas de células animais é responsável por modular a fluidez da membrana frente a variações de temperatura?
2. Uma célula vegetal foi colocada em um tubo com solução salina desconhecida. Após alguns minutos, observou-se ao microscópio que a membrana plasmática retraiu-se descolando da parede celulósica. A solução é isotônica, hipotônica ou hipertônica em relação à célula? Como se denomina esse estado?
3. Explique a diferença de gasto energético entre a difusão facilitada e a bomba de sódio-potássio.

## 9. Correção Comentada dos Exercícios
1. É o **Colesterol**. Intercalado entre as caudas apolares dos fosfolipídios, ele estabiliza a membrana impedindo cristalização em baixas temperaturas e fluidez excessiva em altas temperaturas.
2. A solução é **Hipertônica**. A célula perdeu água por osmose, e o fenômeno de retração citoplasmática com descolamento da membrana em vegetais é a **Plasmólise**.
3. A difusão facilitada é um **transporte passivo** (ocorre a favor do gradiente de concentração através de permeases/canais sem qualquer gasto de ATP). A bomba de sódio-potássio é um **transporte ativo primário** (move íons contra seus gradientes eletroquímicos consumindo ATP).

## 10. Desafio de Domínio
Por que os moluscos terrestres (lesmas e caracóis) morrem desidratados quando se joga sal de cozinha sobre a sua superfície corporal, enquanto os peixes ósseos marinhos conseguem nadar no oceano altamente salgado sem sofrer desidratação letal?
*Pista:* Relacione a ausência de queratina e epitélio impermeabilizante nas lesmas com os mecanismos de osmorregulação ativa e brânquias nos peixes teleósteos.

## 11. Resumo e Pontos-Chave
- Procariontes: sem carioteca e sem organelas membranosas; Eucariontes: compartimentados.
- Membrana: Mosaico Fluido (bicamada fosfolipídica anfifílica + proteínas + colesterol).
- Parede celular: bactérias (peptidoglicano), fungos (quitina), vegetais (celulose).
- Citoesqueleto: microfilamentos (actina), microtúbulos (tubulina/centríolos), filamentos intermediários (resistência mecânica).
- Osmose: água vai do hipo para o hiper. Hemácia estoura no hipo (lise); vegetal fica túrgida pela contra-pressão da parede celular rígida.
- Bomba \( \text{Na}^+/\text{K}^+ \): ejeta 3 \( \text{Na}^+ \), absorve 2 \( \text{K}^+ \), consome 1 ATP.

## 12. Referências Bibliográficas
- ALBERTS, Bruce et al. *Biologia Molecular da Célula*. 6. ed. Porto Alegre: Artmed, 2017.
- JUNQUEIRA, L. C.; CARNEIRO, J. *Biologia Celular e Molecular*. 9. ed. Guanabara Koogan, 2012.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equilíbrio Osmótico e Pressão de Parede",
                syntax = """\\text{DPD} = \\text{PO} - \\text{PT} \\quad (\\text{ou } S_c = S_i - M)""",
                description = "Capacidade de sucção de água por uma célula vegetal integrando a pressão osmótica interna e a turgência elástica da parede celulósica.",
                parametersInfo = """DPD: \\text{déficit de pressão de difusão}; \\quad PO: \\text{pressão osmótica}; \\quad PT: \\text{pressão de turgor da parede}"""
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Estequiometria da Bomba de Sódio-Potássio",
                syntax = """1\\text{ ATP} + 3\\text{ Na}^+_{\\text{int}} + 2\\text{ K}^+_{\\text{ext}} \\to 1\\text{ ADP} + \\text{Pi} + 3\\text{ Na}^+_{\\text{ext}} + 2\\text{ K}^+_{\\text{int}}""",
                description = "Ciclo eletrogênico de transporte ativo primário gerador do potencial de membrana de repouso.",
                parametersInfo = "Gera gradiente eletroquímico celular vital para condução nervosa e transporte secundário de glicose."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Hemólise de Hemácias em Água Destilada",
                codeOrFormula = """\\text{Hemácia em Meio Hipotônico} \\implies \\text{Influxo Maciço de } \\text{H}_2\\text{O} \\implies \\text{Lise Celular (Estouro)}""",
                explanation = "Sem parede celular celulósica, a membrana plasmática lipídica não resiste à pressão osmótica interna da entrada de água."
            ),
            LessonExample(
                title = "Célula Vegetal Túrgida",
                codeOrFormula = """\\text{PO} = \\text{PT} \\implies \\text{DPD} = 0 \\implies \\text{Célula Túrgida em Equilíbrio Dinâmico}""",
                explanation = "A resistência mecânica da parede celulósica impede que a célula vegetal rompa, garantindo a sustentação hidráulica de plantas herbáceas."
            )
        ),
        goldTip = "O ENEM adora a comparação: Célula vegetal em meio hipotônico NÃO ESTOURA (fica túrgida, pois a parede de celulose protege contra o rompimento). Já a célula animal (como a hemácia) ESTOURA (sofre lise/hemólise) porque possui apenas a fina e frágil membrana plasmática!"
    )

    // =========================================================================
    // AULA bio_cit_02: Organelas Citoplasmáticas e Célula Animal vs. Vegetal
    // =========================================================================
    private val bundleBioCit02 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A evolução da célula eucariótica foi marcada pelo desenvolvimento de um complexo sistema de endomembranas que compartimentou o citoplasma em organelas com funções bioquímicas altamente especializadas. Cada organela opera como um departamento fabril interligado: enquanto o retículo e o complexo golgiense processam e despacham proteínas e lipídios, os lisossomos realizam a reciclagem molecular e os peroxissomos neutralizam espécies reativas de oxigênio. No ENEM, este tema é explorado através da correlação entre a abundância de determinadas organelas em tecidos específicos e suas funções metabólicas (ex.: hepatócitos cheios de retículo liso, plasmócitos repletos de retículo rugoso e espermatozoides movidos por mitocôndrias).

## 2. Objetivos de Aprendizagem
- Identificar a estrutura, morfologia e função das principais organelas citoplasmáticas.
- Analisar a cooperação na rota de secreção celular: **Ribossomos \( \to \) RER \( \to \) Complexo Golgiense \( \to \) Vesículas de Secreção**.
- Diferenciar o **Retículo Endoplasmático Rugoso (RER)** do **Retículo Endoplasmático Liso (REL)**.
- Descrever as múltiplas funções do **Complexo Golgiense** (glicosilação, secreção, formação de lisossomos e biogênese do acrossomo).
- Caracterizar a digestão intracelular pelos **Lisossomos**: funções heterofágica (defesa/nutrição), autofágica (renovação celular) e apoptose/autólise.
- Analisar o papel dos **Peroxissomos** e da enzima **Catalase** na degradação do peróxido de hidrogênio (\( \text{H}_2\text{O}_2 \)).
- Contrastar estruturalmente a **Célula Animal** (centríolos, lisossomos evidentes) da **Célula Vegetal** (parede de celulose, cloroplastos e grande vacúolo central).

## 3. Pré-requisitos
- Teoria celular e membrana plasmática (Biologia, Aula bio_cit_01).
- Síntese de proteínas e ácidos nucleicos (Biologia, Aula bio_gen_01).

## 4. Explicação Teórica Aprofundada

### 1. Ribossomos e Retículos Endoplasmáticos
- **Ribossomos Livres:** Complexos ribonucleoproteicos (RNAr + proteínas) que traduzem proteínas destinadas ao uso interno no próprio citosol, núcleo, mitocôndrias e peroxissomos.
- **Retículo Endoplasmático Rugoso (RER / Granular):**
  - Apresenta ribossomos aderidos à face externa de suas cisternas membranosas.
  - Função: síntese, enovelamento inicial e controle de qualidade de proteínas destinadas à exportação (secreção celular), à membrana plasmática e às enzimas digestivas lisossômicas.
  - Abundância: células acinares do pâncreas (enzimas digestivas) e plasmócitos (produção massiva de anticorpos/imunoglobulinas).
- **Retículo Endoplasmático Liso (REL / Agranular):**
  - Túbulos membranosos sem ribossomos associados.
  - Funções:
    1. **Síntese de Lipídios:** Fosfolipídios, triglicerídeos e hormônios esteroides (testosterona, estrogênio e progesterona nas gônadas e córtex adrenal).
    2. **Desintoxicação Celular (Clearance Hepático):** Inativação de drogas lipossolúveis, álcool, medicamentos e pesticidas pelo citocromo P450 nos hepatócitos do fígado.
    3. **Armazenamento e Liberação de Cálcio (\( \text{Ca}^{2+} \)):** No tecido muscular estriado é chamado de retículo sarcoplasmático, liberando cálcio para disparar a contração muscular.

### 2. O Complexo Golgiense (Aparelho de Golgi)
Conjunto de discos membranosos empilhados (dictiossomos) com polaridade funcional: face *cis* (receptora de vesículas do RER) e face *trans* (despachante de vesículas secretoras):
- **Glicosilação Final e Modificação Pós-Traducional:** Adiciona carboidratos a proteínas e lipídios formando glicoproteínas e glicolipídios.
- **Empacotamento e Secreção:** Direciona proteínas em vesículas secretoras que sofrem exocitose na membrana plasmática.
- **Formação dos Lisossomos:** Brotamento de vesículas ricas em hidrolases ácidas.
- **Biogênese do Acrossomo:** Vesícula gigante rica em enzimas digestivas (hialuronidase) posicionada na cabeça do espermatozoide, indispensável para perfurar a zona pelúcida do óvulo na fecundação.

### 3. Lisossomos e Digestão Intracelular
Bolsas membranosas contendo cerca de 50 tipos de **hidrolases ácidas** (proteases, nucleases, lipases) cujo pH ótimo é ácido (\( \approx 4{,}5\text{ a } 5{,}0 \)), mantido por bombas de prótons (\( \text{H}^+ \)) dependentes de ATP na membrana:
1. **Heterofagia:** Digestão de material capturado do exterior por fagocitose ou pinocitose (ex.: macrófagos digerindo bactérias patogênicas).
2. **Autofagia:** Digestão de organelas velhas ou danificadas da própria célula para reciclagem de nutrientes e sobrevivência celular durante o jejum.
3. **Autólise (Apoptose):** Ruptura maciça das membranas lisossômicas liberando enzimas no citosol, digerindo a própria célula. Ocorre na regressão da cauda do girino durante a metamorfose e na formação dos dedos embrionários (reabsorção das membranas interdigitais).
- **Doenças do Armazenamento Lisossômico:** Ex.: Doença de Tay-Sachs (ausência genética da enzima hexosaminidase A causa acúmulo tóxico de lipídios gangliosídeos nos neurônios cerebrais) e Silicose (inalação de pó de sílica por mineiros perfura os lisossomos dos macrófagos pulmonares causando fibrose irreversível).

### 4. Peroxissomos e a Enzima Catalase
Organelas vesiculares contendo enzimas oxidativas (oxidases) que realizam a beta-oxidação de ácidos graxos de cadeia muito longa.
- Esse metabolismo produz um subproduto altamente tóxico e oxidante: o **Peróxido de Hidrogênio (\( \text{H}_2\text{O}_2 \) - água oxigenada)**.
- A enzima **Catalase** presente no interior dos peroxissomos quebra imediatamente o peróxido em água pura e gás oxigênio:
  \[ 2 \, \text{H}_2\text{O}_2 \xrightarrow{\text{Catalase}} 2 \, \text{H}_2\text{O} + \text{O}_2 \]
  (É por isso que a água oxigenada borbulha vigorosamente ao ser jogada sobre um corte na pele: a catalase dos tecidos lesados e do sangue decompõe o composto liberando gás oxigênio, matando bactérias anaeróbias como o tétano!).

### 5. Comparativo Mandatório: Célula Animal vs. Célula Vegetal
- **Estruturas Exclusivas (ou quase) da Célula Vegetal:**
  1. **Parede Celular Celulósica:** Rígida, impede a lise osmótica e confere sustentação mecânica.
  2. **Plastos (Cloroplastos):** Realizam fotossíntese contendo clorofila e tilacoides.
  3. **Grande Vacúolo Central de Suco Celular:** Ocupa até 90% do volume da célula adulta, responsável pela osmorregulação, turgidez e armazenamento de reservas e pigmentos (antocianinas).
  4. **Glioxissomos:** Peroxissomos especializados que convertem lipídios em carboidratos em sementes em germinação.
- **Estruturas Típicas da Célula Animal:**
  1. **Centríolos e Centrossomos Típicos:** Arranjo \( 9 \times 3 \) de microtúbulos (plantas com sementes / angiospermas não possuem centríolos).
  2. **Lisossomos Típicos:** Presentes e abundantes (em vegetais, o vacúolo central assume função hidrolítica análoga).

## 5. Definições e Conceitos
- **Células Acinares Pancreáticas:** Células secretoras do pâncreas exócrino ricas em RER e Golgi para produzir suco pancreático rico em tripsina e amilase.
- **Hepatócitos:** Células hepáticas com REL extraordinariamente desenvolvido para desintoxicação metabólica contínua.
- **Plasmodesmos:** Pontes citoplasmáticas que atravessam a parede celulósica conectando células vegetais vizinhas.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Associação entre Tecido e Organela Predominante
Pesquisadores coletaram amostras de três tipos celulares humanos distintos (A, B e C) e analisaram a abundância de organelas:
- A célula A apresenta volume massivo de Retículo Endoplasmático Rugoso e vesículas de secreção polarizadas.
- A célula B apresenta abundância excepcional de Retículo Endoplasmático Liso.
- A célula C apresenta citoplasma repleto de mitocôndrias ativas intercaladas com filamentos de actina e miosina.
Identifique tecidos prováveis de origem de cada amostra.
- **Resolução:**
  1. Célula A: RER hiperdesenvolvido sintetiza proteínas para exportação; provável **Plasmócito** (produção de anticorpos) ou **Célula Acinar Pancreática** (enzimas digestivas).
  2. Célula B: REL hiperdesenvolvido sintetiza lipídios/esteroides e inativa toxinas; provável **Célula de Leydig do testículo** (síntese de testosterona) ou **Hepatócito do fígado** (desintoxicação).
  3. Célula C: Mitocôndrias abundantes produzem imensa taxa de ATP por respiração aeróbia para contração mecânica; provável **Célula Muscular Esquelética ou Miocárdica**.

### Exemplo 2: Ação da Água Oxigenada em Ferimentos
Por que aplicar peróxido de hidrogênio (\( \text{H}_2\text{O}_2 \)) a 10 volumes sobre um ferimento profundo ajuda a prevenir o tétano causado pela bactéria *Clostridium tetani*?
- **Resolução:**
  - A catalase liberada pelas células rompidas decompõe o \( \text{H}_2\text{O}_2 \) liberando bolhas de oxigênio gasoso (\( \text{O}_2 \)).
  - A bactéria do tétano é uma espécie **anaeróbia estrita** (morre na presença de oxigênio gasoso). A alta concentração local de \( \text{O}_2 \) gerada pela catalase elimina a bactéria e esteriliza o leito da lesão.

## 7. Aplicações Práticas e Modelagem
- **Tolerância a Medicamentos (Indução Enzimática no REL):** O consumo crônico de álcool ou sedativos barbitúricos estimula os hepatócitos a proliferarem seu Retículo Endoplasmático Liso e enzimas do citocromo P450. Consequentemente, o indivíduo passa a metabolizar esses compostos mais rapidamente, exigindo doses cada vez maiores para obter o mesmo efeito clínico (tolerância farmacológica).
- **Fertilização In Vitro (FIV e Capacitação Espermática):** Defeitos na formação do acrossomo pelo complexo golgiense impedem a penetração do espermatozoide no ovócito, exigindo a injeção intracitoplasmática de espermatozoide (ICSI).

## 8. Exercícios de Fixação
1. Qual organela é a responsável direta pela formação do acrossomo nos espermatozoides humanos?
2. Em qual organela celular ocorre a inativação de substâncias tóxicas como álcool e medicamentos nos hepatócitos?
3. Cite duas estruturas morfológicas celulares presentes em células vegetais de folhas e que estão ausentes em células epiteliais animais.

## 9. Correção Comentada dos Exercícios
1. O **Complexo Golgiense (Aparelho de Golgi)**. Suas vesículas fundem-se na extremidade anterior do núcleo do espermatozoide formando o acrossomo rico em enzimas hidrolíticas.
2. No **Retículo Endoplasmático Liso (REL / Agranular)**.
3. A **Parede Celular Celulósica** e os **Cloroplastos** (além do grande vacúolo central de suco celular).

## 10. Desafio de Domínio
Por que os girinos de sapos e rãs perdem completamente a sua cauda durante a metamorfose para a fase adulta, e qual é o papel dos lisossomos nesse remodelamento corporal? Essa morte celular programada provoca inflamação destrutiva no anfíbio?
*Pista:* Relacione a autólise controlada / apoptose com a fagocitose dos restos celulares por macrófagos sem extravasamento citoplasmático.

## 11. Resumo e Pontos-Chave
- RER: síntese de proteínas para exportação, membrana e lisossomos (abundante em plasmócitos e pâncreas).
- REL: síntese de lipídios/esteroides, desintoxicação hepática e reserva de \( \text{Ca}^{2+} \).
- Complexo de Golgi: glicosilação, empacotamento, secreção, forma lisossomos e acrossomo.
- Lisossomos: hidrolases ácidas (pH ~5). Heterofagia (nutrição/defesa), autofagia (reciclagem) e autólise/apoptose (metamorfose).
- Peroxissomos: catalase decompõe \( \text{H}_2\text{O}_2 \) em \( \text{H}_2\text{O} + \text{O}_2 \).
- Célula vegetal tem parede de celulose, cloroplastos e grande vacúolo central; animal tem centríolos e lisossomos evidentes.

## 12. Referências Bibliográficas
- ALBERTS, Bruce et al. *Biologia Molecular da Célula*. 6. ed. Porto Alegre: Artmed, 2017.
- JUNQUEIRA, L. C.; CARNEIRO, J. *Biologia Celular e Molecular*. 9. ed. Guanabara Koogan, 2012.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Decomposição do Peróxido de Hidrogênio pela Catalase",
                syntax = """2\\text{ H}_2\\text{O}_2 \\xrightarrow{\\text{Catalase}} 2\\text{ H}_2\\text{O} + \\text{O}_2 \\uparrow""",
                description = "Reação antioxidante de defesa enzimática nos peroxissomos que neutraliza radicais livres e estresse oxidativo.",
                parametersInfo = "Ação de altíssima velocidade cinética com desprendimento visível de gás oxigênio em ferimentos."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Fluxo na Rota Secreta Clássica",
                syntax = """\\text{Ribossomos no RER} \\xrightarrow{\\text{vesícula}} \\text{Golgi (cis} \\to \\text{trans)} \\xrightarrow{\\text{vesícula secretora}} \\text{Exocitose}""",
                description = "Sequência compartimentada de síntese, enovelamento, glicosilação e exportação de proteínas secretoras.",
                parametersInfo = "Governada por sinais peptídicos de endereçamento e proteínas de revestimento COP-II e clatrina."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Autofagia em Períodos de Restrição Nutricional",
                codeOrFormula = """\\text{Isolamento de Mitocôndria Velha} + \\text{Lisossomo Primário} \\implies \\text{Autofagossomo} \\implies \\text{Nutrientes Reciclados}""",
                explanation = "O Prêmio Nobel de Medicina de 2016 (Yoshinori Ohsumi) elucidou os genes da autofagia lisossômica que mantém o fornecimento de aminoácidos durante a privação alimentar."
            ),
            LessonExample(
                title = "Acrossomo e Fecundação",
                codeOrFormula = """\\text{Acrossomo do Espermatozoide} \\xrightarrow{\\text{Hialuronidase}} \\text{Digestão da Corona Radiata e Zona Pelúcida}""",
                explanation = "A vesícula derivada do Golgi posicionada na cabeça do espermatozoide perfura os envoltórios do óvulo viabilizando o cariogamia."
            )
        ),
        goldTip = "O ENEM adora correlacionar função do tecido com a organela que se destaca nele! Memorize esta trinca clássica: (1) Célula do FÍGADO -> Muito Retículo Liso (desintoxicação); (2) Célula do PÂNCREAS ou PLASMÓCITO -> Muito Retículo Rugoso (secreção de enzimas/anticorpos); (3) Célula do MÚSCULO ou ESPERMATOZOIDE -> Muitas Mitocôndrias (gasto brutal de ATP)!"
    )

    // =========================================================================
    // AULA bio_cit_03: Teoria Endossimbiótica e Mitocôndria
    // =========================================================================
    private val bundleBioCit03 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
Como surgiu a complexa célula eucariótica a partir de ancestrais procariontes simples? Em 1967, a bióloga evolucionista norte-americana Lynn Margulis formulou uma das teorias mais brilhantes e revolucionárias da história da ciência: a **Teoria Endossimbiótica (Endossimbiose Sequencial)**. Segundo essa teoria, duas organelas energéticas centrais — as **Mitocôndrias** e os **Cloroplastos** — não surgiram por invaginações sucessivas da membrana plasmática, mas foram outrora bactérias de vida livre ancestrais fagocitadas por células pré-eucarióticas primitivas que, em vez de serem digeridas, estabeleceram uma relação de simbiose mutualística permanente e irreversível. No ENEM, este tema é explorado através de suas evidências moleculares irrefutáveis, da herança mitocondrial estritamente materna e da genética populacional.

## 2. Objetivos de Aprendizagem
- Compreender a cronologia evolutiva da Teoria Endossimbiótica: fagocitose de uma alfa-proteobactéria aeróbia (origem da **Mitocôndria**) seguida da fagocitose de uma cianobactéria fotossintetizante (origem do **Cloroplasto**).
- Listar e explicar as **Evidências Experimentais e Moleculares Irrefutáveis** da endossimbiose:
  1. Presença de **DNA próprio circular e desnudo** (sem histonas, idêntico ao bacteriano).
  2. Presença de **Ribossomos próprios do tipo 70S** (iguais aos bacterianos, inibidos por antibióticos como cloranfenicol).
  3. **Dupla Membrana Envoltória**: a membrana interna tem composição lipídica bacteriana (rica em cardiolipina e sem colesterol); a externa deriva da membrana da célula hospedeira fagocitária.
  4. Capacidade de **Autoduplicação Independente** por fissão binária simples (bipartição), idêntica às bactérias.
- Analisar a estrutura da mitocôndria: membrana externa, espaço intermembranas, membrana interna pregueada em **cristas mitocondriais** e **matriz mitocondrial**.
- Compreender o padrão de **Herança Mitocondrial Materna** na espécie humana e utilizá-lo na reconstrução de genealogias e na determinação de doenças mitocondriais.

## 3. Pré-requisitos
- Células procariontes vs eucariontes e organelas (Biologia, Aulas bio_cit_01 e bio_cit_02).
- Respiração celular e metabolismo energético (Biologia, Aula bio_cit_04).

## 4. Explicação Teórica Aprofundada

### 1. O Cenário Evolutivo da Endossimbiose
Há cerca de 2 bilhões de anos, a proliferação das cianobactérias fotossintetizantes causou a **Grande Crise do Oxigênio** (a catástrofe do oxigênio), tornando a atmosfera rica em \( \text{O}_2 \), um gás altamente tóxico para os organismos anaeróbios primitivos da época:
1. Uma célula hospedeira anaeróbia ancestral fagocitou uma **bactéria aeróbia primitiva** (ancestral das alfa-proteobactérias).
2. A bactéria fagocitada encontrou no citoplasma hospedeiro um ambiente protegido e rico em nutrientes (piruvato); em troca, a bactéria consumia o oxigênio tóxico e gerava uma quantidade colossal de energia na forma de ATP pela respiração aeróbia.
3. Ao longo de milhões de anos, muitos genes bacterianos migraram para o núcleo celular, consolidando uma simbiose obrigatória: surgiu a **Mitocôndria** presente em todos os eucariotos modernos.
4. Posteriormente, uma linhagem de eucariotos primitivos fagocitou uma **cianobactéria fotossintetizante**, dando origem aos **Cloroplastos** nos ancestrais de algas e plantas terrestres.

### 2. Evidências que Comprovam a Teoria de Margulis
Qualquer estudante do ENEM deve dominar o quarteto de evidências:
1. **Genoma Próprio Circular:** Mitocôndrias e cloroplastos contêm seu próprio DNA (DNAmt), circular, dupla-fita e desprovido de histonas, exatamente como o nucleoide das eubactérias.
2. **Ribossomos Próprios 70S:** Enquanto o citoplasma eucarioto possui ribossomos 80S, a matriz mitocondrial e o estroma dos cloroplastos possuem ribossomos 70S funcionais que realizam síntese proteica própria e são inibidos por antibióticos antibacterianos.
3. **Membrana Dupla Envoltória:**
   - A membrana interna é a antiga membrana plasmática da bactéria (contém o fosfolipídio exclusivo **cardiolipina** e é desprovida de colesterol).
   - A membrana externa deriva da vesícula de endocitose formada pela membrana da célula hospedeira ancestral no momento do englobamento.
4. **Divisão por Bipartição:** As mitocôndrias proliferam por fissão binária simples independente do ciclo de mitose do núcleo da célula hospedeira.

### 3. A Herança Mitocondrial Estritamente Materna
Na fecundação humana:
- O espermatozoide possui suas mitocôndrias concentradas na sua **peça intermediária** (o colo), que fornece energia ATP para o batimento flagelar até atingir o óvulo.
- Durante a penetração do espermatozoide no ovócito secundário, apenas o núcleo e o centríolo do espermatozoide penetram no citoplasma; a cauda e a peça intermediária desprendem-se fora do ovócito. Caso alguma mitocôndria paterna acabe penetrando, ela é rapidamente marcada por ubiquitina e destruída pelos lisossomos maternos por autofagia.
- **Consequência Genética:** **100% das mitocôndrias de qualquer ser humano foram herdadas de sua mãe biológica!**
- **Padrão em Heredogramas:**
  - Se uma mulher afetada por uma mutação no DNAmt tiver filhos, **TODOS os seus filhos e filhas herdarão a condição**.
  - Se um homem afetado tiver filhos, **NENHUM de seus filhos ou filhas herdará a condição**, pois homens não transmitem mitocôndrias para a prole.

### 4. Aplicação Antropológica: A "Eva Mitocondrial"
Como o DNA mitocondrial não sofre recombinação genética por crossing-over durante a meiose e é transmitido exclusivamente pela linhagem matrilinear, suas únicas alterações ao longo das gerações decorrem de mutações pontuais espontâneas que acumulam a uma taxa constante (o relógio molecular). Mapeando essas mutações em populações de todos os continentes, geneticistas determinaram que toda a humanidade contemporânea descende de uma ancestral comum matrilinear que viveu na África Subsaariana há cerca de \( 150.000\text{ a } 200.000\text{ anos} \), apelidada de **Eva Mitocondrial**.

## 5. Definições e Conceitos
- **Cristas Mitocondriais:** Dobras da membrana interna que maximizam a área de superfície para ancoragem dos complexos enzimáticos da cadeia respiratória e ATP-sintase.
- **Matriz Mitocondrial:** Espaço gelatinoso interno delimitado pela membrana interna, onde ocorrem o Ciclo de Krebs, a beta-oxidação de ácidos graxos e onde residem o DNAmt e os ribossomos 70S.
- **Heteroplasmia:** Presença de uma mistura de mitocôndrias com DNA normal e mitocôndrias mutadas dentro de uma mesma célula, explicando a variabilidade de sintomas em doenças mitocondriais.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Análise de Heredograma com Herança Mitocondrial
Considere uma família onde a mãe é portadora da Síndrome de LHON (Neuropatia Óptica Hereditária de Leber, doença causada por mutação no gene mitocondrial da subunidade 4 do complexo I da cadeia respiratória) e o pai é saudável. Sabendo que o casal teve 3 filhas e 2 filhos, e que todos os filhos casaram-se com parceiros saudáveis, determine a probabilidade de a doença se manifestar na primeira e na segunda geração da prole.
- **Resolução:**
  1. Primeira Geração (filhos da mãe afetada):
     - Como a herança é mitocondrial materna, a mãe transmite seu DNAmt mutado para todos os descendentes. **100% dos 5 filhos (as 3 filhas e os 2 filhos) serão afetados pela síndrome**.
  2. Segunda Geração:
     - As 3 filhas afetadas transmitirão a doença para **todos os seus respectivos filhos (100% afetados)**.
     - Os 2 filhos afetados casados com mulheres saudáveis não transmitirão o DNAmt; logo, **0% dos filhos dos homens terão a síndrome**.

### Exemplo 2: Evidências Moleculares no ENEM
Por que o tratamento prolongado com certos antibióticos de amplo espectro (como o cloranfenicol ou tetraciclina) em doses elevadas pode provocar disfunções musculares e cansaço extremo em pacientes humanos?
- **Resolução:**
  - O cloranfenicol inibe os ribossomos 70S das bactérias.
  - Como as mitocôndrias das células humanas descendem evolutivamente de bactérias e ainda mantêm **ribossomos 70S próprios** em sua matriz, doses elevadas do fármaco inibem inadvertidamente a síntese proteica nas mitocôndrias do paciente, prejudicando a cadeia respiratória e a geração de ATP nos tecidos de alto consumo metabólico, como o músculo esquelético.

## 7. Aplicações Práticas e Modelagem
- **Identificação Forense de Vítimas (DNAmt):** Em ossadas antigas ou cadáveres carbonizados onde o DNA nuclear se degradou devido ao calor ou tempo, o DNA mitocondrial sobrevive intacto porque existem milhares de cópias de DNAmt por célula (contra apenas duas cópias de DNA nuclear). O perfil genético pode ser comparado com qualquer parente da linhagem materna da vítima (mãe, avó materna, irmãos ou tios maternos).
- **Técnica do Bebê com "Três Pais Genéticos" (Terapia de Substituição Mitocondrial):** Para evitar que mães com mutações mitocondriais graves transmitam a doença incurável aos filhos, o núcleo do óvulo da mãe é transferido para o citoplasma desnucleado de um óvulo doado por uma mulher saudável com mitocôndrias perfeitas antes da fertilização in vitro. O embrião resultante herda o DNA nuclear do pai e da mãe biológica e apenas o DNA mitocondrial (menos de 0,1% dos genes) da doadora.

## 8. Exercícios de Fixação
1. Cite duas características presentes nas mitocôndrias que fundamentam a Teoria Endossimbiótica de Lynn Margulis.
2. Em um processo de perícia forense internacional, deseja-se confirmar se uma ossada humana pertence a um soldado desaparecido em combate na década de 1970. Entre os seguintes familiares vivos disponíveis para teste de DNA mitocondrial, qual é o único parente viável para o confronto genético: (A) O filho do soldado; (B) A prima materna do soldado (filha da irmã da mãe do soldado); (C) O irmão do pai do soldado.
3. As mitocôndrias realizam a síntese de todas as proteínas necessárias para a sua função através de seu próprio DNAmt? Justifique.

## 9. Correção Comentada dos Exercícios
1. A presença de **DNA circular próprio e desnudo** (semelhante ao bacteriano) e a presença de **ribossomos 70S próprios** (além da dupla membrana e divisão por bipartição).
2. O parente correto é a **(B) Prima materna do soldado**. O soldado herdou seu DNAmt de sua mãe, que o compartilhou com sua irmã (tia do soldado), que por sua vez transmitiu esse mesmo DNAmt idêntico para a sua filha (prima materna). O filho do soldado herdou as mitocôndrias da mãe dele (esposa do soldado), não servindo para o teste.
3. **Não**. Embora possuam DNAmt próprio e ribossomos 70S, ao longo de bilhões de anos a vasta maioria dos genes bacterianos originais migrou para o núcleo da célula eucariótica hospedeira. A mitocôndria moderna produz apenas 13 proteínas de sua cadeia respiratória; as demais centenas de proteínas mitocondriais são codificadas pelo DNA nuclear, traduzidas no citosol e importadas para a organela.

## 10. Desafio de Domínio
Por que os organismos procariontes primitivos nunca conseguiram desenvolver formas de vida pluricelulares complexas de grande porte com bilhões de células diferenciadas, e por que a aquisição endossimbiótica da mitocôndria foi o gatilho bioenergético indispensável para a evolução da multicelularidade nos animais e plantas?
*Pista:* Compare a razão entre área de membrana geradora de ATP e volume celular em uma bactéria versus uma célula eucariótica com milhares de cristas mitocondriais internas.

## 11. Resumo e Pontos-Chave
- Teoria Endossimbiótica (Lynn Margulis): mitocôndrias e cloroplastos eram bactérias livres fagocitadas.
- Evidências: DNA próprio circular, ribossomos 70S, dupla membrana e autoduplicação por bipartição.
- Herança Mitocondrial: estritamente MATERNA na espécie humana (o espermatozoide não contribui com mitocôndrias na fecundação).
- Doenças mitocondriais: mãe afetada passa para 100% dos filhos e filhas; pai afetado não passa para ninguém.
- Eva Mitocondrial: ancestral matrilinear comum na África há ~200 mil anos.
- Uso forense: DNAmt é ultrarresistente e tem milhares de cópias por célula.

## 12. Referências Bibliográficas
- MARGULIS, Lynn. *Origin of Eukaryotic Cells*. Yale University Press, 1970.
- ALBERTS, Bruce et al. *Biologia Molecular da Célula*. 6. ed. Porto Alegre: Artmed, 2017.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Padrão de Transmissão da Herança Mitocondrial Materna",
                syntax = """\\text{Mãe Mutada } (\\text{DNAmt}) \\implies \\text{100\\% dos Filhos e Filhas Afetados} \\quad | \\quad \\text{Pai Mutado} \\implies 0\\% \\text{ Afetados}""",
                description = "Regra genética mandatória em heredogramas decorrente da eliminação das organelas paternas no zigoto.",
                parametersInfo = "Utilizado no diagnóstico clínico de neuropatias, encefalopatias mitocondriais e miopatias."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Estrutura do Genoma Mitocondrial Humano",
                syntax = """\\text{DNAmt} \\approx 16.569\\text{ pares de bases} \\implies 37\\text{ genes} \\quad (13\\text{ polipeptídeos} + 22\\text{ RNAt} + 2\\text{ RNAr})""",
                description = "Genoma circular compacto que não possui íntrons e codifica subunidades críticas da fosforilação oxidativa.",
                parametersInfo = "Taxa de mutação cerca de 10 a 20 vezes superior à do genoma nuclear por falta de histonas protetoras."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Herança Mitocondrial em Heredograma",
                codeOrFormula = """\\text{Mãe (afetada)} \\times \\text{Pai (normal)} \\implies \\text{Todos os 4 filhos e filhas nascem afetados}""",
                explanation = "A transmissão universal da mãe para toda a prole independentemente do sexo biológico comprova a herança citoplasmática mitocondrial."
            ),
            LessonExample(
                title = "DNAmt na Identificação Forense",
                codeOrFormula = """\\text{Confronto} = \\text{DNAmt da Ossada} \\equiv \\text{DNAmt de Irmão, Mãe ou Sobrinho Matrilinear}""",
                explanation = "A invariância do DNAmt ao longo da linhagem feminina direta possibilita identificação criminal e histórica precisa mesmo gerações depois."
            )
        ),
        goldTip = "Em questões de genética do ENEM com heredogramas: se uma doença afetar TODOS os filhos e filhas de uma mulher afetada, e NENHUM filho de um homem afetado herdar a doença, mate a questão na hora: trata-se de HERANÇA MITOCONDRIAL (materna)!"
    )

    // =========================================================================
    // AULA bio_cit_04: Metabolismo Energético
    // =========================================================================
    private val bundleBioCit04 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A vida é uma luta termodinâmica contínua contra a entropia. Para manter sua integridade molecular, sintetizar proteínas, bombear íons e contrair músculos, os seres vivos realizam transformações bioquímicas ordenadas conhecidas como **Metabolismo Energético**. O centro de todas as transações energéticas na biologia celular é a molécula de **ATP (Adenosina Trifosfato)**, a "moeda energética universal". No ENEM, o metabolismo energético é um dos conteúdos de maior relevância biológica, articulando a respiração celular aeróbia, as fermentações na indústria alimentícia e de biocombustíveis (etanol de cana-de-açúcar) e a fotossíntese como motor primário do ciclo do carbono e fluxo de energia nos ecossistemas.

## 2. Objetivos de Aprendizagem
- Compreender o ciclo do ATP e a hidrólise de ligações fosfoanidro de alta energia: \( \text{ATP} \rightleftharpoons \text{ADP} + \text{Pi} + \text{Energia} \).
- Analisar a **Respiração Celular Aeróbia** em suas três etapas integradas:
  1. **Glicólise** (citosol / anaeróbia): clivagem da glicose em 2 piruvatos, rendimento líquido de \( 2\text{ ATP} \) e \( 2\text{ NADH} \).
  2. **Ciclo de Krebs / Ciclo do Ácido Cítrico** (matriz mitocondrial): descarboxilação oxidativa do piruvato em Acetil-CoA, oxidação completa liberando \( \text{CO}_2 \), gerando \( \text{NADH} \), \( \text{FADH}_2 \) e \( \text{ATP/GTP} \).
  3. **Cadeia Transportadora de Elétrons e Fosforilação Oxidativa** (cristas mitocondriais): fluxo de elétrons, bombeamento quimiosmótico de prótons (\( \text{H}^+ \)) e o papel do oxigênio (\( \text{O}_2 \)) como **Aceptor Final de Elétrons**.
- Diferenciar os processos de **Fermentação**:
  - **Fermentação Láctica:** no tecido muscular humano em hipóxia e bactérias do iogurte.
  - **Fermentação Alcoólica:** por leveduras (*Saccharomyces cerevisiae*), produzindo etanol e liberando bolhas de \( \text{CO}_2 \) (pães, cervejas e etanol combustível).
- Analisar a **Fotossíntese**:
  - **Etapa Fotoquímica (Clara / Tilacoides):** fotólise da água, transporte de elétrons, produção de \( \text{NADPH} \), \( \text{ATP} \) e liberação de \( \text{O}_2 \).
  - **Etapa Química / Ciclo de Calvin (Escura / Estroma):** fixação do \( \text{CO}_2 \) atmosférico pela enzima RuBisCO gerando glicose.

## 3. Pré-requisitos
- Célula eucariótica e estrutura da mitocôndria e cloroplasto (Biologia, Aulas bio_cit_01, bio_cit_02 e bio_cit_03).
- Reações de oxirredução e termoquímica (Química, Termoquímica e Eletroquímica).

## 4. Explicação Teórica Aprofundada

### 1. O ATP: A Moeda Energética Universal
Composto por uma base nitrogenada adenina, uma ribose e três grupos fosfato ligados em série. As duas ligações terminais entre os fosfatos são instáveis e altamente energéticas devido à repulsão mútua das cargas negativas dos átomos de oxigênio:
\[ \text{ATP} + \text{H}_2\text{O} \to \text{ADP} + \text{Pi} + 7{,}3\text{ kcal/mol} \quad (\approx 30{,}5\text{ kJ/mol}) \]
A célula recicla continuamente essa molécula: a energia liberada na oxidação da glicose acopla-se para refosforilar o ADP em ATP.

### 2. Respiração Celular Aeróbia: As Três Etapas
Equação global balanceada:
\[ \text{C}_6\text{H}_{12}\text{O}_6 + 6 \, \text{O}_2 \to 6 \, \text{CO}_2 + 6 \, \text{H}_2\text{O} + \text{Energia (}\sim 30\text{ a } 32\text{ ATP)} \]
1. **Glicólise (Citosol / Hialoplasma):**
   - Processo anaeróbio universal em todos os domínios da vida.
   - Uma molécula de glicose (\( 6\text{C} \)) é clivada em duas moléculas de ácido pirúvico / piruvato (\( 3\text{C} \)).
   - Gasto de \( 2\text{ ATP} \) na ativação; produção de \( 4\text{ ATP} \) por fosforilação ao nível do substrato: **Rendimento líquido de \( 2\text{ ATP} \)** e redução de \( 2\text{ NAD}^+ \) para \( 2\text{ NADH} \).
2. **Ciclo de Krebs (Matriz Mitocondrial):**
   - Os piruvatos entram na matriz mitocondrial sofrendo descarboxilação oxidativa por perda de \( \text{CO}_2 \), unindo-se à coenzima A para formar **Acetil-CoA** (\( 2\text{C} \)), gerando \( \text{NADH} \).
   - O Acetil-CoA entra no ciclo fundindo-se ao ácido oxalacético (\( 4\text{C} \)) para formar ácido cítrico (\( 6\text{C} \)).
   - Ao longo de sucessivas reações catalisadas por enzimas, os átomos de carbono originais da glicose são totalmente oxidados e liberados na forma de **\( \text{CO}_2 \)** residual da respiração.
   - A energia é capturada em transportadores de elétrons de alta energia: por molécula de glicose (duas voltas do ciclo), produzem-se **\( 6\text{ NADH} \)**, **\( 2\text{ FADH}_2 \)** e **\( 2\text{ ATP} \)** (ou GTP).
3. **Cadeia Transportadora de Elétrons e Quimiosmose (Cristas Mitocondriais):**
   - Os elétrons ricos em energia do \( \text{NADH} \) e \( \text{FADH}_2 \) fluem sequencialmente através de quatro grandes complexos proteicos (Complexos I a IV) e citocromos embutidos na membrana interna.
   - À medida que os elétrons caem para níveis de menor energia livre, a energia liberada é usada para bombear ativamente íons prótons (\( \text{H}^+ \)) da matriz mitocondrial para o espaço intermembranas.
   - Gera-se um colossal **gradiente eletroquímico de prótons** (força próton-motriz).
   - Os prótons acumulam-se no espaço intermembranas e retornam à matriz mitocondrial exclusivamente através do canal rotatório da enzima **ATP-Sintase**, cuja rotação mecânica molecular sintetiza dezenas de ATPs por fosforilação oxidativa (Teoria Quimiosmótica de Peter Mitchell, Nobel de 1978).
   - **O Papel Fundamental do Oxigênio:** O \( \text{O}_2 \) inalado pelos pulmões atua no final da cadeia como o **Aceptor Final de Elétrons e Prótons**, formando água:
     \[ \frac{1}{2} \, \text{O}_2 + 2\text{ e}^- + 2 \, \text{H}^+ \to \text{H}_2\text{O} \]
   - *Se não houver \( \text{O}_2 \):* os elétrons congestionam toda a cadeia respiratória; o bombeamento de prótons cessa; os carreadores ficam aprisionados como NADH sem conseguir se reoxidar e a produção aeróbia de ATP colapsa! Substâncias como o **Cianeto** e o **Monóxido de Carbono (CO)** bloqueiam o complexo IV (citocromo c oxidase), provocando asfixia celular química imediata.

### 3. Fermentações: Vias Anaeróbias Alternativas
Ocorrem no citosol na ausência de oxigênio ou em organismos anaeróbios. O objetivo biológico fundamental da fermentação **NÃO é gerar ATP extra**, mas sim **reoxidar o NADH de volta em \( \text{NAD}^+ \)** para que a Glicólise continue funcionando e gerando os seus modestos \( 2\text{ ATP} \) de sobrevivência:
1. **Fermentação Láctica:**
   - O piruvato é reduzido diretamente pelo NADH transformando-se em **Ácido Láctico (Lactato)**, sem desprendimento de gás:
     \[ \text{Glicose} + 2\text{ ADP} + 2\text{ Pi} \to 2 \, \text{Lactato} + 2\text{ ATP} \]
   - Ocorre nos músculos esqueléticos humanos sob esforço extenuante (quando o suprimento de oxigênio cardiovascular é insuficiente para a demanda mitocondrial) e em bactérias láticas na fabricação de iogurtes, queijos e conservas.
2. **Fermentação Alcoólica:**
   - O piruvato sofre descarboxilação liberando **gás carbônico (\( \text{CO}_2 \))** e acetaldeído, que é reduzido pelo NADH gerando **Etanol (Álcool Etílico)**:
     \[ \text{Glicose} + 2\text{ ADP} + 2\text{ Pi} \to 2 \, \text{Etanol} + 2 \, \text{CO}_2 \uparrow + 2\text{ ATP} \]
   - Realizada por leveduras (*Saccharomyces cerevisiae*):
     - Na fabricação do **Pão:** o \( \text{CO}_2 \) liberado forma bolhas de gás que fazem a massa expandir e ficar fofa; o álcool evapora no calor do forno.
     - Na fabricação de **Bebidas e Etanol Combustível:** o mosto fermentado de uva ou cana-de-açúcar produz o etanol retido no meio líquido.

### 4. Fotossíntese: O Motor Autotrófico da Biosfera
Equação global:
\[ 6 \, \text{CO}_2 + 6 \, \text{H}_2\text{O} + \text{Luz Solar} \to \text{C}_6\text{H}_{12}\text{O}_6 + 6 \, \text{O}_2 \uparrow \]
1. **Fase Fotoquímica (Reações de Claro / Tilacoides):**
   - A clorofila absorve luz visível (principalmente nos comprimentos azul e vermelho; reflete o verde).
   - **Fotólise da Água (Reação de Hill):** A luz quebra a molécula de água liberando elétrons, prótons e **Gás Oxigênio (\( \text{O}_2 \))**. Todo o \( \text{O}_2 \) liberado na fotossíntese provém exclusivamente da quebra da ÁGUA, e NÃO do \( \text{CO}_2 \)!
   - Produz **ATP** (fotofosforilação) e **\( \text{NADPH} \)** para a etapa seguinte.
2. **Fase Química / Ciclo de Calvin-Benson (Estroma do Cloroplasto):**
   - Fixação do \( \text{CO}_2 \) atmosférico pela enzima **RuBisCO**, utilizando o ATP e o NADPH produzidos na fase clara para sintetizar trioses que formarão glicose, amido e celulose.

## 5. Definições e Conceitos
- **Ponto de Compensação Fótico (PCF):** Intensidade luminosa na qual a velocidade da fotossíntese iguala rigorosamente a velocidade da respiração celular da planta (\( \text{Taxa Fotossíntese} = \text{Taxa Respiração} \)): o consumo de \( \text{O}_2 \) iguala a produção, e a troca gasosa com o ambiente é nula.
- **Quimiossíntese:** Produção autotrófica de matéria orgânica a partir de energia liberada na oxidação de compostos inorgânicos (\( \text{NH}_4^+, \text{NO}_2^-, \text{H}_2\text{S} \)), realizada por bactérias nitrificantes do solo e bactérias hidrotermais de fossas oceânicas sem uso de luz solar.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Identificação da Origem do Oxigênio Fotossintético
Em um experimento clássico de biologia molecular com a alga verde *Chlorella*, pesquisadores forneceram aos organismos água marcada com o isótopo pesado de oxigênio (\( \text{H}_2^{18}\text{O} \)) e gás carbônico comum (\( \text{C}^{16}\text{O}_2 \)). Ao analisar os produtos da fotossíntese, o oxigênio isotopicamente marcado (\( ^{18}\text{O}_2 \)) apareceu no gás oxigênio liberado para a atmosfera ou na glicose sintetizada?
- **Resolução:**
  - O oxigênio pesado (\( ^{18}\text{O} \)) apareceu **integralmente no gás oxigênio (\( ^{18}\text{O}_2 \)) liberado na atmosfera**.
  - Este experimento de Ruben e Kamen (1941) comprovou definitivamente que o oxigênio gasoso resultante da fotossíntese origina-se da **fotólise da água** que ocorre na fase clara no interior dos tilacoides, e não da quebra da molécula de dióxido de carbono.

### Exemplo 2: O Efeito da Ausência de Oxigênio nas Células Humanas
Durante uma corrida de 100 metros rasos com intensidade máxima, o atleta de alta performance entra temporariamente em débito de oxigênio muscular. Qual via metabólica garante a continuidade do fornecimento de ATP e qual composto se acumula no sarcoplasma?
- **Resolução:**
  - Em hipóxia aguda, a cadeia respiratória mitocondrial é bloqueada por escassez de oxigênio aceptor final.
  - Para evitar o bloqueio da glicólise, as células musculares acionam a **Fermentação Láctica**, onde o piruvato é reduzido pelo NADH gerando **Ácido Láctico (Lactato)**. O acúmulo de íons lactato e a queda do pH celular associam-se à fadiga muscular transitória.

## 7. Aplicações Práticas e Modelagem
- **Produção de Bioetanol no Brasil:** A cana-de-açúcar é moída para extração do caldo de sacarose, que é inoculado com leveduras em dornas industriais para realizar fermentação alcoólica contínua, gerando biocombustível renovável de baixa emissão de carbono para frotas de automóveis flex.
- **Desacopladores Mitocondriais (Termogenina no Tecido Adiposo Castanho):** Recém-nascidos e mamíferos hibernantes possuem tecido adiposo marrom com mitocôndrias cheias de **termogenina (UCP-1)**, uma proteína canal que permite o retorno dos prótons para a matriz sem passar pela ATP-sintase; a energia do gradiente não gera ATP, dissipando-se 100% como **calor para aquecer o corpo**.

## 8. Exercícios de Fixação
1. Qual é a função bioquímica desempenhada pelo oxigênio (\( \text{O}_2 \)) na respiração celular aeróbia e onde ele atua?
2. Por que a massa do pão cresce quando adicionamos fermento biológico fresco (*Saccharomyces*), mas não cresce da mesma forma se colocarmos fermento em massa guardada no congelador a \( -20^\circ\text{C} \)?
3. Na fotossíntese, qual é a molécula doadora dos elétrons que recompõe a clorofila excitada pela luz na fase fotoquímica?

## 9. Correção Comentada dos Exercícios
1. O oxigênio atua como o **Aceptor Final de Elétrons e Prótons** no final da cadeia transportadora de elétrons nas cristas mitocondriais, unindo-se a prótons \( \text{H}^+ \) para formar água (\( \text{H}_2\text{O} \)).
2. As leveduras do fermento biológico realizam fermentação alcoólica, liberando **gás carbônico (\( \text{CO}_2 \))** que fica retido na rede de glúten fazendo a massa inflar. A temperaturas de congelamento (\( -20^\circ\text{C} \)), as enzimas metabólicas das leveduras ficam cineticamente inativadas pelo frio extremo, cessando a fermentação e o desprendimento gasoso.
3. É a molécula de **Água (\( \text{H}_2\text{O} \))**, que sofre fotólise liberando elétrons para a clorofila oxidada, prótons \( \text{H}^+ \) para o lúmen do tilacoide e \( \text{O}_2 \) gasoso.

## 10. Desafio de Domínio
O dinitrofenol (DNP) é uma substância química proibida historicamente usada em pílulas milagrosas de emagrecimento na década de 1930. O DNP atua como um desacoplador que fura a membrana interna da mitocôndria permitindo que os prótons atravessem sem passar pela ATP-sintase. Explique por que o uso do DNP provoca perda rápida de peso corporal acompanhada de hipertermia severa mortal e sudorese extrema.
*Pista:* Pense na queima desesperada de reservas de glicose e gordura pela célula para tentar gerar o ATP que nunca é formado, dissipando toda a energia como calor metabólico.

## 11. Resumo e Pontos-Chave
- ATP: moeda energética (\( \text{ATP} \to \text{ADP} + \text{Pi} + \text{energia} \)).
- Respiração aeróbia: (1) Glicólise (citosol, anaeróbia, 2 ATP líquidos); (2) Ciclo de Krebs (matriz, libera \( \text{CO}_2 \), produz NADH/FADH2); (3) Cadeia Respiratória (cristas, ATP-sintase quimiosmótica, \( \text{O}_2 \) como aceptor final formando \( \text{H}_2\text{O} \)).
- Fermentação: reoxida NADH sem oxigênio. Láctica (músculos, iogurte, sem gás); Alcoólica (leveduras, etanol + \( \text{CO}_2 \uparrow \), pão e biocombustível).
- Fotossíntese: Clara (tilacoides, quebra água e libera \( \text{O}_2 \), forma ATP e NADPH); Escura (estroma, Ciclo de Calvin fixa \( \text{CO}_2 \) por RuBisCO).
- O oxigênio do ar que respiramos vem da quebra da ÁGUA na fotossíntese!

## 12. Referências Bibliográficas
- LEHNINGER, A. L.; NELSON, D. L.; COX, M. M. *Princípios de Bioquímica*. 7. ed. Artmed, 2018.
- ALBERTS, Bruce et al. *Biologia Molecular da Célula*. 6. ed. Porto Alegre: Artmed, 2017.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Global da Respiração Celular Aeróbia",
                syntax = """\\text{C}_6\\text{H}_{12}\\text{O}_6 + 6\\text{ O}_2 \\to 6\\text{ CO}_2 + 6\\text{ H}_2\\text{O} + \\Delta G^\\circ \\approx -2870\\text{ kJ/mol} \\quad (\\sim 30\\text{ a } 32\\text{ ATP})""",
                description = "Oxidação catabólica completa da glicose com alto rendimento energético acoplado por fosforilação oxidativa.",
                parametersInfo = "Realizada sucessivamente no citosol (glicólise) e mitocôndria (Krebs e cristas respiratórias)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Geral da Fotossíntese Oxigênica",
                syntax = """6\\text{ CO}_2 + 6\\text{ H}_2\\text{O} + \\text{Luz} \\to \\text{C}_6\\text{H}_{12}\\text{O}_6 + 6\\text{ O}_2 \\uparrow""",
                description = "Conversão anabólica de energia radiante solar em energia química estável nas ligações de açúcares.",
                parametersInfo = "Todo o oxigênio liberado provém comprovadamente da fotólise das moléculas de água nos tilacoides."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Fermentação Alcoólica na Panificação",
                codeOrFormula = """\\text{Glicose} \\xrightarrow{\\text{Levedura}} 2\\text{ Etanol} + 2\\text{ CO}_2 \\uparrow \\implies \\text{Crescimento e Aeração da Massa do Pão}""",
                explanation = "O desprendimento de gás carbônico pelas leveduras forma alvéolos gasosos internos que conferem maciez e volume ao pão."
            ),
            LessonExample(
                title = "O Papel do Aceptor Final na Cadeia Respiratória",
                codeOrFormula = """\\text{O}_2 + 4\\text{ e}^- + 4\\text{ H}^+ \\to 2\\text{ H}_2\\text{O} \\implies \\text{Manutenção da Força Próton-Motriz}""",
                explanation = "A redução do oxigênio molecular em água no complexo IV garante o fluxo contínuo de elétrons e a geração contínua de ATP."
            )
        ),
        goldTip = "Duas regras de ouro absolutas do ENEM: (1) O oxigênio liberado na fotossíntese vem da ÁGUA (fotólise nos tilacoides) e NUNCA do gás carbônico! (2) Na respiração celular, o oxigênio que respiramos NÃO vira gás carbônico: ele é o ACEPTOR FINAL de elétrons e vira ÁGUA pura no final das cristas mitocondriais!"
    )

    // =========================================================================
    // AULA bio_cit_05: Bioquímica Celular
    // =========================================================================
    private val bundleBioCit05 = CalculusDidacticBundle(
        theoryMarkdown = """
## 1. Introdução
A matéria viva é uma sofisticada máquina química constituída por moléculas inorgânicas (**Água** e **Sais Minerais**) e macromoléculas orgânicas carbonadas (**Carboidratos**, **Lipídios**, **Proteínas**, **Enzimas**, **Ácidos Nucleicos** e **Vitaminas**). Compreender a estrutura atômica e os mecanismos de ação dessas substâncias é a chave mestra para desvendar desde o metabolismo termorregulador e a digestão de alimentos até a biologia molecular e a farmacologia. No ENEM, a bioquímica celular é cobrada de forma contextualizada na saúde pública (dietas balanceadas, desnutrição, avitaminoses, anemias) e em processos biotecnológicos industriais.

## 2. Objetivos de Aprendizagem
- Analisar as propriedades físico-químicas singulares da **Água**: polaridade, pontes de hidrogênio, alto calor específico, calor latente de vaporização, solvente universal e forças de coesão/adesão (tensão superficial e capilaridade).
- Identificar o papel fisiológico dos principais **Sais Minerais**: Cálcio (\( \text{Ca}^{2+} \)), Ferro (\( \text{Fe}^{2+} \)), Sódio (\( \text{Na}^+ \)), Potássio (\( \text{K}^+ \)), Magnésio (\( \text{Mg}^{2+} \)), Fósforo (\( \text{P} \)) e Iodo (\( \text{I} \)).
- Classificar e analisar os **Carboidratos** (monossacarídeos, dissacarídeos e polissacarídeos de reserva energética e estruturais).
- Classificar os **Lipídios** (glicerídeos, fosfolipídios, ceras e esteroides como o colesterol e derivados).
- Compreender a estrutura das **Proteínas**: aminoácidos, ligações peptídicas, conformações espaciais (primária a quaternária) e os mecanismos de **Desnaturação Proteica**.
- Dominar a cinética das **Enzimas**: conceito de catalisador biológico, energia de ativação, modelo chave-fechadura/ajuste induzido e fatores intervenientes (temperatura, pH e concentração do substrato).
- Caracterizar as **Vitaminas** lipossolúveis (A, D, E, K) e hidrossolúveis (C e complexo B), diagnosticando suas respectivas avitaminoses.

## 3. Pré-requisitos
- Ligações químicas covalentes e de hidrogênio (Química Geral).
- Funções orgânicas: álcoois, ésteres, aminas e ácidos carboxílicos (Química Orgânica).

## 4. Explicação Teórica Aprofundada

### 1. A Água e sua Importância Biológica
Representa entre 60% e 75% da massa corporal humana e até 95% em organismos como águas-vivas:
- **Polaridade e Ligações de Hidrogênio:** A geometria angular com dipolo permanente faz da água um excepcional **Solvente Universal** para moléculas polares e iônicas (hidrofílicas).
- **Alto Calor Específico:** Absorve ou perde grandes quantidades de calor com pequena variação de temperatura, atuando como moderador térmico interno contra flutuações bruscas de temperatura ambiental.
- **Alto Calor Latente de Vaporização:** Para evaporar, cada grama de água absorve cerca de \( 540\text{ calorias} \), tornando o suor o mecanismo de resfriamento mais eficiente de mamíferos.
- **Coesão e Adesão:** A atração intermolecular mútua cria **alta tensão superficial** (permitindo que insetos caminhem sobre a lâmina d'água) e, combinada com a adesão aos tubos de xilema, sustenta a subida da seiva bruta até a copa de árvores gigantescas (Teoria da Coesão-Tensão de Dixon).

### 2. Sais Minerais Essenciais e Fisiologia
- **Cálcio (\( \text{Ca}^{2+} \)):** Mineralização de ossos e dentes, contração muscular, coagulação sanguínea e liberação de neurotransmissores nas sinapses. (Carência: osteoporose, raquitismo e tetania).
- **Ferro (\( \text{Fe}^{2+} \)):** Componente central do grupo heme da **hemoglobina** e mioglobina para transporte de \( \text{O}_2 \). (Carência: **Anemia Ferropriva**, provocando fadiga crônica, palidez e falta de ar).
- **Sódio (\( \text{Na}^+ \)) e Potássio (\( \text{K}^+ \)):** Condução do impulso nervoso, equilíbrio hídrico e osmorregulação.
- **Magnésio (\( \text{Mg}^{2+} \)):** Cofator enzimático universal e componente central da molécula de **Clorofila** nos vegetais.
- **Iodo (\( \text{I} \)):** Síntese dos hormônios tireoidianos \( \text{T}_3 \) e \( \text{T}_4 \). (Carência: **Bócio Endêmico** com hipertrofia compensatória da tireoide; corrigido pela iodação obrigatória do sal de cozinha no Brasil).

### 3. Carboidratos (Glicídios)
1. **Monossacarídeos (\( \text{C}_n\text{H}_{2n}\text{O}_n \)):** Pentoses estruturais nos ácidos nucleicos (Ribose no RNA e Desoxirribose no DNA); Hexoses energéticas (Glicose, Frutose e Galactose).
2. **Dissacarídeos:**
   - *Sacarose:* Glicose + Frutose (açúcar da cana).
   - *Lactose:* Glicose + Galactose (açúcar do leite; a deficiência da enzima lactase causa intolerância à lactose).
   - *Maltose:* Glicose + Glicose (amido digerido).
3. **Polissacarídeos:**
   - **De Reserva Energética:** **Glicogênio** (em animais e fungos, estocado no fígado e músculos) e **Amido** (em plantas, estocado em raízes tuberosas e sementes).
   - **Estruturais:** **Celulose** (parede celular vegetal; fibra alimentar indigestível por humanos que estimula o peristaltismo intestinal) e **Quitina** (parede fúngica e exoesqueleto de artrópodes).

### 4. Lipídios
Moléculas insolúveis em água e solúveis em solventes orgânicos apolares:
- **Glicerídeos (Óleos e Gorduras):** Triglicerídeos formados por glicerol ligado a 3 ácidos graxos. Excelente reserva energética compacta de longo prazo (rendem o dobro de calorias por grama que os carboidratos) e isolamento térmico subcutâneo (panículo adiposo de focas e baleias).
- **Fosfolipídios:** Lipídios anfifílicos formadores das membranas biológicas.
- **Esteroides:** Destaca-se o **Colesterol**, sintetizado no fígado e precursor de hormônios sexuais (testosterona, estrogênio), hormônios adrenais (cortisol), sais biliares e vitamina D.
  - *LDL ("Colesterol Ruim"):* Transporta colesterol para os tecidos; em excesso oxidado, deposita-se na parede das artérias formando placas de ateroma (**Aterosclerose**).
  - *HDL ("Colesterol Bom"):* Recolhe o colesterol periférico em excesso levando-o de volta ao fígado para descarte biliar.

### 5. Proteínas e Enzimas
- **Proteínas:** Polímeros lineares de aminoácidos unidos por **ligações peptídicas** covalentes (grupo carboxila reage com grupo amina com eliminação de água).
  - Possuem conformações tridimensionais essenciais para sua atividade: primária, secundária (alfa-hélice e folha beta), terciária (enovelamento tridimensional globular) e quaternária (múltiplas cadeias polipeptídicas, ex.: hemoglobina).
  - **Desnaturação Proteica:** Perda irreversível da conformação espacial terciária provocada por aquecimento térmico excessivo ou pH extremo. A cadeia desdobra-se e a proteína **perde totalmente sua função biológica** (ex.: a clara do ovo que coagula e fica branca ao fritar).
- **Enzimas (Catalisadores Biológicos):**
  - Aceleram as reações biológicas diminuindo drasticamente a **Energia de Ativação (\( E_a \))** da reação, sem serem consumidas no processo.
  - Possuem um **Sítio Ativo** específico que interage com o substrato.
  - Fatores que afetam a velocidade enzimática:
    1. *Temperatura:* A velocidade cresce com o calor até atingir uma temperatura ótima (\( \approx 37^\circ\text{C} \) em humanos); acima disso, a enzima sofre desnaturação e a velocidade desaba a zero.
    2. *pH Ótimo:* Pepsina estomacal (\( \text{pH } \approx 2 \)); tripsina pancreática intestinal (\( \text{pH } \approx 8 \)).

### 6. Vitaminas e Avitaminoses
1. **Lipossolúveis (Solúveis em lipídios, estocáveis no fígado):**
   - **Vitamina A (Retinol):** Síntese de pigmentos da visão (rodopsina) e epitélios. Carência: **Cegueira Noturna** (hemeralopia) e xeroftalmia (olho seco).
   - **Vitamina D (Calciferol):** Absorção intestinal de cálcio; ativada na pele por radiação UV solar. Carência: **Raquitismo** em crianças (ossos moles e arqueados) e osteomalácia em adultos.
   - **Vitamina E (Tocoferol):** Potente antioxidante de membranas.
   - **Vitamina K (Filoquinona):** Síntese de fatores de coagulação no fígado. Carência: **Hemorragias** espontâneas.
2. **Hidrossolúveis (Solúveis em água, não acumuladas em excesso, exigem ingestão diária contínua):**
   - **Vitamina C (Ácido Ascórbico):** Síntese de colágeno e absorção do ferro vegetal. Carência: **Escorbuto** (sangramento gengival, perda de dentes e hemorragias capilares, histórico em marinheiros na Era das Grandes Navegações).
   - **Complexo B:** Coenzimas do metabolismo energético:
     - \( B_1 \) (Tiamina): Carência causa **Beribéri** (polineurite e fraqueza muscular).
     - \( B_3 \) (Niacina): Carência causa **Pelagra** (os 3 D's: Diarreia, Dermatite e Demência).
     - \( B_9 \) (Ácido Fólico): Formação do tubo neural embrionário (suplementado na gravidez para evitar anencefalia e espinha bífida).
     - \( B_{12} \) (Cobalamina): Síntese de hemácias. Carência: **Anemia Perniciosa** (comum em veganos estritos sem suplementação e pessoas após cirurgia bariátrica).

## 5. Definições e Conceitos
- **Aminoácidos Essenciais:** Aqueles que o organismo humano é incapaz de sintetizar por via metabólica própria, devendo ser obtidos obrigatoriamente através da dieta alimentar (são 8 aminoácidos essenciais em humanos adultos).
- **Inibição Competitiva:** Molécula com estrutura química parecida com a do substrato que se liga ao sítio ativo da enzima competindo com o substrato verdadeiro, diminuindo a taxa reacional.

## 6. Exemplos Resolvidos Passo a Passo

### Exemplo 1: Febre Alta e Desnaturação Enzimática
Por que febres humanas muito elevadas e persistentes (acima de \( 41^\circ\text{C} \)) representam uma emergência médica gravíssima que requer controle farmacológico imediato?
- **Resolução:**
  - As enzimas humanas possuem temperatura ótima de atuação em torno de \( 36{,}5^\circ\text{C} \text{ a } 37^\circ\text{C} \).
  - Em temperaturas acima de \( 40^\circ\text{C} \), as pontes de hidrogênio e interações hidrofóbicas que estabilizam as estruturas terciárias das proteínas começam a se romper.
  - Ocorre **desnaturação de enzimas metabólicas essenciais**, bloqueando vias do ciclo de Krebs, canais iônicos de neurônios no cérebro e transporte de oxigênio, podendo provocar convulsões irreversíveis, coma e falência de múltiplos órgãos.

### Exemplo 2: Absorção do Ferro da Dieta e Vitamina C
Por que médicos e nutricionistas frequentemente recomendam consumir frutas cítricas ricas em vitamina C (como laranja, limão ou acerola) imediatamente após as refeições ricas em feijão e folhas escuras?
- **Resolução:**
  - O ferro de origem vegetal (não-heme) encontra-se no estado oxidado férrico (\( \text{Fe}^{3+} \)), cuja absorção pelas células intestinais é muito baixa e ineficiente.
  - O ácido ascórbico (vitamina C) é um agente redutor que converte o ferro férrico (\( \text{Fe}^{3+} \)) em ferro ferroso (\( \text{Fe}^{2+} \)), que é amplamente solúvel e facilmente absorvido pelos enterócitos intestinais, prevenindo a anemia ferropriva.

## 7. Aplicações Práticas e Modelagem
- **Testes Imunológicos e Anticorpos:** Os testes rápidos de gravidez (beta-hCG) e os testes de COVID-19 utilizam anticorpos conjugados específicos; o reconhecimento do antígeno baseia-se na conformação espacial terciária rígida do sítio de ligação proteico.
- **Tratamento da Água Sanitária e Álcool 70%:** O álcool etílico a 70% consegue penetrar a membrana lipídica bacteriana sem evaporar instantaneamente, coagulando e desnaturando todas as enzimas intracelulares do patógeno, esterilizando superfícies hospitalares.

## 8. Exercícios de Fixação
1. Qual polissacarídeo estrutural de origem vegetal é o constituinte principal das fibras alimentares e auxilia na motilidade intestinal de seres humanos?
2. Um marinheiro britânico do século XVIII apresentava fraqueza extrema, sangramento nas gengivas e reabertura de feridas antigas cicatrizadas. Qual avitaminose esse marinheiro apresentava e como ela era tratada?
3. O que acontece com a velocidade de uma reação enzimática quando a temperatura se eleva muito acima da temperatura ótima da enzima? Explique a causa molecular.

## 9. Correção Comentada dos Exercícios
1. A **Celulose**. Embora os seres humanos não possuam a enzima celulase para digerir suas ligações beta-glicosídicas, as fibras celulósicas aumentam o bolo fecal, retêm água e estimulam o peristaltismo saudável do cólon.
2. O marinheiro sofria de **Escorbuto**, causado pela carência grave de **Vitamina C (Ácido Ascórbico)**, indispensável para a síntese correta do colágeno dos tecidos conjuntivos e vasos sanguíneos. Tratava-se fornecendo frutas cítricas frescas (limão e laranjas).
3. A velocidade da reação **desaba abruptamente a zero**. A agitação térmica excessiva quebra as ligações fracas que sustentam a forma tridimensional da proteína, causando **desnaturação irreversível** e deformando o sítio ativo da enzima.

## 10. Desafio de Domínio
Por que os ursos polares e esquimós que consomem uma dieta exclusivamente carnívora baseada em focas e peixes gordurosos correm risco fatal de hipervitaminose por intoxicação ao ingerir o fígado do urso polar, enquanto não sofrem nenhum risco de toxicidade por consumir doses maciças de vitamina C?
*Pista:* Relacione a solubilidade e capacidade de estocagem hepática das vitaminas lipossolúveis (Vitamina A) frente à excreção renal rápida das vitaminas hidrossolúveis.

## 11. Resumo e Pontos-Chave
- Água: solvente polar, pontes de H, alto calor específico (modera temperatura) e calor de vaporização (suor).
- Sais minerais: Cálcio (ossos, contração, coagulação); Ferro (hemoglobina, anemia ferropriva); Iodo (tireoide, bócio).
- Carboidratos: monossacarídeos (glicose); polissacarídeos de reserva (glicogênio em animais, amido em plantas) e estruturais (celulose e quitina).
- Lipídios: triglicerídeos (reserva/isolante); fosfolipídios (membranas); colesterol (LDL e HDL, precursor hormonal).
- Proteínas: polímeros de aminoácidos com ligação peptídica. Desnaturação destrói a forma terciária e anula a função.
- Enzimas: catalisadores biológicos que reduzem a energia de ativação. Afetadas por pH e temperatura.
- Vitaminas: Lipossolúveis (A, D, E, K); Hidrossolúveis (C - escorbuto, Complexo B - beribéri, pelagra e anemia).

## 12. Referências Bibliográficas
- LEHNINGER, A. L.; NELSON, D. L.; COX, M. M. *Princípios de Bioquímica*. 7. ed. Artmed, 2018.
- ALBERTS, Bruce et al. *Biologia Molecular da Célula*. 6. ed. Porto Alegre: Artmed, 2017.
- OPENSTAX. *Biology 2e*. Rice University: OpenStax, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Formação da Ligação Peptídica",
                syntax = """\\text{R}_1\\text{-COOH} + \\text{R}_2\\text{-NH}_2 \\to \\text{R}_1\\text{-CO-NH-R}_2 + \\text{H}_2\\text{O} \\quad (\\text{Síntese por Desidratação})""",
                description = "Reação de condensação covalente entre o carbono da carboxila de um aminoácido e o nitrogênio amínico do seguinte.",
                parametersInfo = "Catalisada no sítio peptidil-transferase do ribossomo durante a tradução celular."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Cinética de Michaelis-Menten",
                syntax = """v = \\frac{V_{\\text{máx}} \\cdot [S]}{K_m + [S]}""",
                description = "Velocidade catalítica inicial da enzima em função da concentração de substrato livre disponível.",
                parametersInfo = """V_{\\text{máx}}: \\text{velocidade de saturação}; \\quad K_m: \\text{constante de afinidade (metade de } V_{\\text{máx}})"""
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Efeito do pH na Pepsina vs Tripsina",
                codeOrFormula = """\\text{Pepsina Estomacal: pH Ótimo} \\approx 2 \\quad | \\quad \\text{Tripsina Pancreática: pH Ótimo} \\approx 8""",
                explanation = "A alteração brusca do quimo ácido ao entrar no duodeno neutraliza a pepsina por desnaturação e ativa a tripsina alcalina."
            ),
            LessonExample(
                title = "Perfil Lipídico e Aterosclerose",
                codeOrFormula = """\\uparrow [\\text{LDL Oxidado}] + \\downarrow [\\text{HDL}] \\implies \\text{Formação de Placas de Ateroma e Risco de Infarto}""",
                explanation = "O balanço entre os transportadores lipoproteicos modula o risco cardiovascular e a deposição arterial de placas lipídicas."
            )
        ),
        goldTip = "Para o ENEM, não confunda o papel das vitaminas: Vitamina C tem a ver com COLÁGENO e vasos (carência dá ESCORBUTO com sangramento de gengiva). Vitamina D tem a ver com CÁLCIO e ossos (carência dá RAQUITISMO). Vitamina A tem a ver com a VISÃO (carência dá CEGUEIRA NOTURNA). Vitamina K tem a ver com COAGULAÇÃO (carência dá HEMORRAGIA)!"
    )
}

