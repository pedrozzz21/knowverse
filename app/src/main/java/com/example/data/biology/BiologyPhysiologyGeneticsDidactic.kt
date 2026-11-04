package com.example.data.biology

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático e Exercícios Especializados para:
 * - Módulo 2: Fisiologia Humana (bio_fis_01 a bio_fis_04)
 * - Módulo 3: Genética e Biotecnologia (bio_gen_01 a bio_gen_05)
 */
object BiologyPhysiologyGeneticsDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle? {
        val entry = didacticMap[lesson.id] ?: return null
        return CalculusDidacticBundle(
            theoryMarkdown = entry.theoryMarkdown,
            formulas = entry.formulas,
            solvedExamples = entry.solvedExamples,
            imageResId = null,
            goldTip = entry.goldTip
        )
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise>? {
        return exerciseMap[lesson.id]
    }

    data class BioEntry(
        val theoryMarkdown: String,
        val formulas: List<FormulaOrSyntaxItem>,
        val solvedExamples: List<LessonExample>,
        val goldTip: String
    )

    private val didacticMap: Map<String, BioEntry> by lazy {
        mapOf(
            // ==========================================
            // MÓDULO 2: FISIOLOGIA HUMANA
            // ==========================================
            "bio_fis_01" to BioEntry(
                theoryMarkdown = """
                    # Fisiologia Humana Integrada: Digestão, Respiração, Circulação e Excreção
                    
                    A homeostase corporal depende da cooperação íntima entre os quatro grandes sistemas de nutrição e transporte:
                    
                    ### 1. Sistema Digestório e Microambientes de pH
                    • **Boca (pH neutro ~7,0):** Início da digestão de amido pela ptialina (amilase salivar).
                    • **Estômago (pH fortemente ácido ~2,0):** O suco gástrico contém HCl e pepsinogênio. O HCl ativa o pepsinogênio em **pepsina**, que digere proteínas. O muco gástrico protege a mucosa contra autodigestão (úlceras).
                    • **Duodeno (pH básico ~8,0):** O quimo ácido recebe o bicarbonato pancreático para neutralização, a **bile** do fígado (que emulsifica lipídios sem conter enzimas) e enzimas pancreáticas (tripsina, quimiotripsina, lipase pancreática, nuclease e amilase).
                    
                    ### 2. Respiração e Equilíbrio Ácido-Básico do Sangue
                    O oxigênio difunde-se nos alvéolos por gradiente de pressão e liga-se reversivelmente à **hemoglobina** das hemácias (oxi-hemoglobina).
                    O CO₂ produzido na respiração celular é transportado principalmente dissolvido no plasma como íon **bicarbonato (HCO₃⁻)**:
                    \[ \text{CO}_2 + \text{H}_2\text{O} \xrightleftharpoons{\text{anidrase carbônica}} \text{H}_2\text{CO}_3 \xrightleftharpoons{} \text{H}^+ + \text{HCO}_3^- \]
                    Se houver hipoventilação, o CO₂ acumula-se, liberando mais H⁺ e provocando **acidose respiratória**. O bulbo cerebral detecta a queda do pH e acelera a frequência respiratória!
                    
                    ### 3. Excreção Renal e Osmorregulação
                    Os néfrons filtram o sangue na cápsula glomerular (filtração mecânica), reabsorvem glicose e aminoácidos no túbulo contorcido proximal e regulam a reabsorção de água sob ação do hormônio **ADH (vasopressina)** nos ductos coletores e da **aldosterona** (que reabsorve Na⁺ e excreta K⁺).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Equilíbrio de Tampão Bicarbonato", "CO₂ + H₂O ⇌ H₂CO₃ ⇌ H⁺ + HCO₃⁻", "Regula o pH do sangue humano entre 7,35 e 7,45"),
                    FormulaOrSyntaxItem("Hormônio ADH", "Alta osmolalidade plasmática -> Hipotálamo -> Liberação de ADH -> Reabsorção de água", "Inibido por álcool, gerando desidratação e ressaca")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Controle Bulbar da Respiração",
                        codeOrFormula = "\\text{Aumento de CO}_2 \\implies \\text{Queda do pH sanguíneo} \\implies \\text{Bulbo acelera respiração}",
                        explanation = "Diferente do senso comum, o principal estímulo para o bulbo respiratório acelerar a ventilação durante o exercício físico não é a falta de oxigênio, mas sim o aumento de CO₂ que acidifica o sangue."
                    )
                ),
                goldTip = "A bile NÃO contém enzimas digestivas! Ela atua puramente como um detergente biológico que emulsifica grandes gotas de gordura em gotículas microscópicas para aumentar a área de contato da lipase pancreática!"
            ),

            "bio_fis_02" to BioEntry(
                theoryMarkdown = """
                    # Imunologia Médica: Vacinas, Soros, Antígenos e Anticorpos
                    
                    O sistema imunológico protege o corpo contra patógenos invasores (vírus, bactérias, fungos e protozoários) por meio de respostas inatas e adaptativas.
                    
                    ### 1. Imunização Ativa vs Imunização Passiva no ENEM
                    • **Vacina (Imunização Ativa - Ação Preventiva):** Inocula **antígenos** inativados, atenuados ou fragmentos de RNAm. Estimula os linfócitos B e T a produzirem anticorpos próprios e, crucialmente, **células de memória imunológica**. Possui ação lenta (dias/semanas), mas confere proteção duradoura.
                    • **Soro Terapêutico (Imunização Passiva - Ação Curativa):** Inocula **anticorpos prontos** produzidos em outro organismo (ex: cavalo). Usado em emergências com risco de morte iminente (picada de cobra jararaca, cascavel, escorpião ou toxina tetânica). Possui ação imediata, mas temporária e **NÃO gera células de memória**!
                    
                    ### 2. Resposta Primária vs Resposta Secundária
                    Na resposta primária (primeiro contato), a produção de anticorpos é lenta e em menor concentração. Na resposta secundária (novo contato com o mesmo patógeno), as células de memória disparam produção massiva e ultrarrápida de anticorpos, neutralizando o invasor antes do desenvolvimento de sintomas da doença.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Vacina", "Antígeno atenuado -> Estimula anticorpos próprios + Células de memória", "Prevenção duradoura (ativa)"),
                    FormulaOrSyntaxItem("Soro", "Anticorpos prontos exógenos -> Neutralização imediata da toxina", "Terapia emergencial sem memória (passiva)")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Picada de Serpente Peçonhenta",
                        codeOrFormula = "\\text{Soro antiofídico heterólogo com anticorpos policlonais}",
                        explanation = "A vítima de picada de cobra não pode receber vacina, pois o corpo demoraria semanas para gerar anticorpos e o veneno seria letal. Aplica-se o soro com anticorpos prontos para neutralizar a peçonha imediatamente."
                    )
                ),
                goldTip = "Mnemônico fundamental do ENEM: Vacina previne e gera memória (V de Viva a Memória); Soro cura na emergência e não deixa memória!"
            ),

            "bio_fis_03" to BioEntry(
                theoryMarkdown = """
                    # Reprodução Humana e Ciclo Menstrual Feminino
                    
                    O ciclo menstrual tem duração média de 28 dias e é controlado pelo eixo hipotálamo-hipófise-ovários através de retroalimentação hormonal.
                    
                    ### As Fases do Ciclo e a Dinâmica Hormonal
                    1. **Fase Folicular (Dias 1 a 13):** A hipófise secreta o **FSH (Hormônio Folículo-Estimulante)**, estimulando o amadurecimento dos folículos ovarianos. Os folículos em crescimento secretam **estrógeno**, que regenera e espessa o endométrio uterino.
                    2. **Ovulação (Dia 14):** O estrógeno atinge um nível crítico, desencadeando um pico agudo de **LH (Hormônio Luteinizante)** liberado pela adeno-hipófise. O pico de LH provoca a ruptura do folículo e a liberação do ovócito secundário.
                    3. **Fase Lútea (Dias 15 a 28):** O folículo rompido transforma-se no **corpo lúteo (amarelo)**, que passa a secretar grandes quantidades de **progesterona** e estrógeno. A progesterona vasculariza e prepara o endométrio para a **nidação** (fixação do embrião).
                    4. **Menstruação:** Se não houver fecundação, o corpo lúteo atrofia, os níveis de progesterona e estrógeno despencam bruscamente e a camada funcional do endométrio descama, iniciando novo ciclo.
                    
                    ### Gravidez e o Hormônio hCG
                    Havendo fecundação e nidação na tuba/útero, o trofoblasto embrionário secreta **hCG (gonadotrofina coriônica humana)**, que mantém o corpo lúteo ativo secretando progesterona, impedindo a menstruação. É o hormônio detectado nos testes de farmácia e de sangue!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Pico Ovulatório", "Pico de LH induzido por estrógeno no 14º dia -> Ovulação", "Momento fértil máximo do ciclo feminino"),
                    FormulaOrSyntaxItem("Manutenção da Gravidez", "hCG embrionário -> Mantém corpo lúteo -> Mantém progesterona alta", "Impede descamação endometrial")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Teste de Gravidez Positivo",
                        codeOrFormula = "\\text{Detecção de beta-hCG na urina}",
                        explanation = "A presença de hCG confirma gravidez porque esse hormônio é produzido exclusivamente pelo córion embrionário após a implantação na parede uterina."
                    )
                ),
                goldTip = "A progesterona é o 'hormônio da gestação': se os níveis de progesterona caírem, o endométrio descama e ocorre a menstruação (ou abortamento espontâneo)!"
            ),

            "bio_fis_04" to BioEntry(
                theoryMarkdown = """
                    # Métodos Contraceptivos e Prevenção de ISTs
                    
                    ### 1. Métodos Anticoncepcionais e Mecanismo de Ação
                    • **Pílula Anticoncepcional Combinada (Oral):** Contém estrógeno e progestagênio sintéticos em doses contínuas. Esses hormônios exercem **feedback negativo sobre a hipófise**, inibindo a secreção de **FSH e LH**. Sem o pico de LH, **NÃO ocorre ovulação**.
                    • **Preservativo (Camisinha Masculina e Feminina):** Método de barreira mecânica. É o **ÚNICO método contraceptivo que previne simultaneamente a gravidez e a transmissão de Infecções Sexualmente Transmissíveis (ISTs)** como HIV, Sífilis, Hepatites B e C e Gonorreia!
                    • **DIU (Dispositivo Intrauterino):** O DIU de cobre cria ambiente citotóxico e inflamatório que imobiliza espermatozoides; o hormonal (Mirena) libera progestagênio localmente tornando o muco cervical espesso.
                    • **Métodos Cirúrgicos Definitivos:** Vasectomia (secção dos ductos deferentes no homem; não afeta a ereção nem a produção de testosterona) e Laqueadura tubária (secção das tubas uterinas na mulher).
                    
                    ### 2. Vírus Papiloma Humano (HPV) e Vacinação
                    O HPV é transmitido por contato sexual e está diretamente associado ao surgimento de câncer de colo de útero. A vacina recombinante contra o HPV distribuída pelo SUS deve ser aplicada prioritariamente antes do início da vida sexual (em adolescentes de 9 a 14 anos) para máxima eficácia imunológica preventiva.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Mecanismo da Pílula", "Estrógeno + Progesterona exógenos -> Bloqueio de FSH e LH -> Anovulação", "Contracepção hormonal por feedback negativo"),
                    FormulaOrSyntaxItem("Prevenção Dupla de ISTs", "Camisinha = Barreira mecânica contra fluidos biológicos", "Único método com proteção comprovada contra ISTs")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Vasectomia e Ejaculação",
                        codeOrFormula = "\\text{Corte dos ductos deferentes não altera volume seminal nem libido}",
                        explanation = "A vasectomia impede apenas a chegada dos espermatozoides ao sêmen. Como mais de 95% do volume do esperma é produzido pelas vesículas seminais e próstata, o homem continua ejaculando normalmente."
                    )
                ),
                goldTip = "Nem pílula, nem DIU, nem laqueadura, nem vasectomia protegem contra o vírus do HIV ou HPV: para proteção contra ISTs, o preservativo de barreira é indispensável!"
            ),

            // ==========================================
            // MÓDULO 3: GENÉTICA E BIOTECNOLOGIA
            // ==========================================
            "bio_gen_01" to BioEntry(
                theoryMarkdown = """
                    # Genética Molecular: Ácidos Nucleicos, Transcrição e Código Genético
                    
                    O Dogma Central da Biologia Molecular estabelece o fluxo da informação genética:
                    \[ \text{DNA} \xrightarrow{\text{Replicação}} \text{DNA} \xrightarrow{\text{Transcrição}} \text{RNAm} \xrightarrow{\text{Tradução}} \text{Proteína} \]
                    
                    ### Diferenças entre DNA e RNA
                    • **DNA (Ácido Desoxirribonucleico):** Dupla hélice antiparalela, açúcar **desoxirribose**, bases nitrogenadas Adenina (A), Timina (T), Citosina (C) e Guanina (G). Relação de Chargaff: \(A = T\) e \(C = G\).
                    • **RNA (Ácido Ribonucleico):** Fita simples, açúcar **ribose**, bases A, **Uracila (U)**, C e G.
                    
                    ### Características Fundamentais do Código Genético no ENEM
                    1. **Universal:** A mesma trinca de bases (códon) codifica o mesmo aminoácido em praticamente todas as formas de vida (de uma bactéria a uma baleia). Isso possibilita a transgenia e biotecnologia!
                    2. **Degenerado (Redundante):** Como existem 64 combinações de códons para apenas 20 aminoácidos, diferentes códons podem codificar o mesmo aminoácido (ex: GGU, GGC, GGA e GGG codificam glicina). Isso atenua o efeito de mutações pontuais!
                    3. **Não Ambíguo:** Cada códon específico codifica rigorosamente um único aminoácido.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Regra de Chargaff", "A = T (duas pontes de H) e C = G (três pontes de H)", "Emparelhamento de bases na dupla hélice de DNA"),
                    FormulaOrSyntaxItem("Código Degenerado", "64 códons possíveis para 20 aminoácidos", "Proteção contra mutações silenciosas no 3º nucleotídeo")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Mutação Silenciosa",
                        codeOrFormula = "\\text{Troca de base na 3ª posição do códon sem alterar aminoácido}",
                        explanation = "Se uma mutação pontual substitui GGU por GGC no RNAm, o aminoácido inserido no ribossomo continua sendo a glicina, gerando uma proteína normal e idêntica graças à degenerescência do código genético."
                    )
                ),
                goldTip = "DNA tem TIMINA; RNA tem URACILA! Na transcrição de DNA para RNA: onde tem A no DNA entra U no RNA; onde tem T no DNA entra A no RNA!"
            ),

            "bio_gen_02" to BioEntry(
                theoryMarkdown = """
                    # Primeira Lei de Mendel: Segregação dos Fatores e Monoibridismo
                    
                    Gregor Mendel demonstrou que cada característica hereditária é determinada por um par de fatores (hoje chamados de **genes alelos**), que se separam (segregam) durante a formação dos gametas (meiose), de modo que cada gameta carrega apenas um alelo de cada par.
                    
                    ### Conceitos Essenciais
                    • **Homozigoto (Puro):** Indivíduo com dois alelos idênticos (\(AA\) ou \(aa\)).
                    • **Heterozigoto (Híbrido):** Indivíduo com alelos diferentes (\(Aa\)).
                    • **Genótipo:** Constituição genética do indivíduo (os alelos presentes).
                    • **Fenótipo:** Expressão observável ou bioquímica, resultante da interação entre o genótipo e o meio ambiente: \(\text{Fenótipo} = \text{Genótipo} + \text{Ambiente}\).
                    
                    ### Proporções Mendelianas Clássicas do Cruzamento \(Aa \times Aa\)
                    • **Proporção Genotípica:** \(1 AA : 2 Aa : 1 aa\) (25% \(AA\), 50% \(Aa\), 25% \(aa\)).
                    • **Proporção Fenotípica (Dominância Completa):** \(3 \text{ Dominantes} : 1 \text{ Recessivo}\) (75% / 25%).
                    
                    ### Regras de Probabilidade em Genética
                    • **Regra do 'E' (Eventos Independentes):** Multiplicam-se as probabilidades individuais: \(P(A \text{ e } B) = P(A) \cdot P(B)\).
                    • **Regra do 'OU' (Eventos Mutuamente Exclusivos):** Somam-se as probabilidades: \(P(A \text{ ou } B) = P(A) + P(B)\).
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Cruzamento Monoíbrido", "Aa x Aa -> 1/4 AA + 1/2 Aa + 1/4 aa", "Primeira Lei da Segregação de Mendel"),
                    FormulaOrSyntaxItem("Probabilidade com Sexo", "P(característica) * P(sexo masculino ou feminino = 1/2)", "Cálculo de probabilidade conjunta no ENEM")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Probabilidade de Filho com Albinismo",
                        codeOrFormula = "Aa \\times Aa \\implies P(aa) = 1/4 \\implies P(\\text{menina albina}) = 1/4 \\cdot 1/2 = 1/8",
                        explanation = "Um casal heterozigoto para albinismo recessivo quer saber a chance de ter uma menina albina. A chance de ser albina (aa) é 1/4 e a chance de ser do sexo feminino é 1/2. Probabilidade conjunta = 1/8 (12,5%)."
                    )
                ),
                goldTip = "Em heredogramas do ENEM: Pais com mesmo fenótipo que têm um filho com fenótipo diferente revelam que os pais são HETEROZIGOTOS e o filho tem caráter RECESSIVO!"
            ),

            "bio_gen_03" to BioEntry(
                theoryMarkdown = """
                    # Segunda Lei de Mendel e Linkage (Ligação Gênica)
                    
                    ### Segunda Lei de Mendel (Segregação Independente)
                    Quando dois ou mais pares de alelos estão localizados em **cromossomos não homólogos diferentes**, eles se segregam de maneira totalmente independente durante a anáfase I da meiose.
                    • Cruzamento di-híbrido (\(AaBb \times AaBb\)): gera a proporção fenotípica clássica **9 : 3 : 3 : 1** (9 duplo-dominantes, 3 dominantes para A e recessivos para B, 3 recessivos para A e dominantes para B, e 1 duplo-recessivo).
                    
                    ### Linkage (Ligação Gênica) e Crossing-Over
                    Quando dois genes estão situados **no mesmo cromossomo**, eles tendem a ser transmitidos juntos (ligados), violando a 2ª Lei de Mendel!
                    • Durante a prófase I da meiose, pode ocorrer **Crossing-Over (Permutação)** entre cromátides homólogas, gerando gametas recombinantes.
                    • **Taxa de Recombinação (\(\text{TR}\)):** A frequência de gametas recombinantes mede diretamente a distância física entre os genes no cromossomo (medida em Morganídeos ou Centimorgans - cM):
                    \[ 1\% \text{ de recombinação} = 1 \text{ morganídeo (cM)} \]
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Proporção Di-híbrida de Mendel", "AaBb x AaBb -> 9 : 3 : 3 : 1", "Válido apenas para genes em cromossomos diferentes"),
                    FormulaOrSyntaxItem("Mapeamento Genético (Linkage)", "Taxa de Recombinação (%) = Distância em Morganídeos (cM)", "Quanto mais distantes os genes, maior a taxa de crossing-over")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Mapeamento de Três Genes",
                        codeOrFormula = "AB = 10 \\text{ cM}, \\quad BC = 6 \\text{ cM}, \\quad AC = 16 \\text{ cM} \\implies \\text{Ordem } A - B - C",
                        explanation = "Como a maior distância é AC (16 cM), os genes A e C estão nas extremidades e B fica no meio: a distância AB (10) somada a BC (6) totaliza os 16 morganídeos de AC."
                    )
                ),
                goldTip = "Se a taxa de recombinação for zero (Linkage total sem crossing), o heterozigoto produz apenas 2 tipos de gametas parentais em proporção 50% / 50%!"
            ),

            "bio_gen_04" to BioEntry(
                theoryMarkdown = """
                    # Doenças Genéticas, Sistema ABO, Fator Rh e Herança Sexual
                    
                    ### 1. Sistema ABO (Polialelia e Codominância)
                    • Alelos: \(I^A\) e \(I^B\) são codominantes entre si, e ambos dominam o alelo recessivo \(i\).
                    • **Sangue A:** Genótipos \(I^A I^A\) ou \(I^A i\). Aglutinogênio A nas hemácias e Aglutinina Anti-B no plasma.
                    • **Sangue B:** Genótipos \(I^B I^B\) ou \(I^B i\). Aglutinogênio B nas hemácias e Anti-A no plasma.
                    • **Sangue AB:** Genótipo \(I^A I^B\). Possui aglutinogênios A e B; nenhum anticorpo anti-A/anti-B no plasma (**Receptor Universal de hemácias**).
                    • **Sangue O:** Genótipo \(ii\). Sem aglutinogênios de membrana; possui anticorpos Anti-A e Anti-B no plasma (**Doador Universal de hemácias**).
                    
                    ### 2. Fator Rh e Eritroblastose Fetal (DHRN)
                    • Alelo \(R\) (Rh positivo, dominante) produz o antígeno D; \(rr\) (Rh negativo, recessivo).
                    • **Condição de risco para Eritroblastose:** Mãe Rh⁻ (\(rr\)) gerando um feto Rh⁺ (\(R_\_\)), cujo pai é Rh⁺. Na primeira gestação de feto Rh⁺, a mãe se sensibiliza no parto. Em uma segunda gestação de feto Rh⁺, os anticorpos maternos anti-Rh (IgG) atravessam a placenta e destroem as hemácias fetais.
                    • **Prevenção:** Injeção de soro com anticorpos anti-Rh na mãe logo após o primeiro parto para destruir as hemácias fetais antes da sensibilização.
                    
                    ### 3. Herança Ligada ao Cromossomo X
                    Como homens são hemizigotos (\(X^d Y\)), basta um único alelo mutado para manifestarem anomalias recessivas ligadas ao X, como o **Daltonismo** e a **Hemofilia**. Mulheres só manifestam se forem homozigotas recessivas (\(X^d X^d\)). Pais daltônicos transmitem o cromossomo X exclusivamente para suas filhas!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Eritroblastose Fetal", "Mãe Rh⁻ + Pai Rh⁺ -> 2º Feto Rh⁺ em risco", "Prevenção com imunoglobulina anti-Rh após o 1º parto"),
                    FormulaOrSyntaxItem("Herança Ligada ao X", "Homem: X^D Y (normal) ou X^d Y (afetado) | Mulher: X^D X^D, X^D X^d (portadora) ou X^d X^d", "Afeta homens com frequência muito superior a mulheres")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Mãe Portadora de Daltonismo",
                        codeOrFormula = "X^D X^d \\times X^D Y \\implies P(\\text{filho homem daltônico}) = 1/2 \\text{ dos meninos}",
                        explanation = "Uma mulher com visão normal portadora de daltonismo (X^D X^d) casada com homem com visão normal (X^D Y) tem 50% de chance de gerar um filho homem daltônico (X^d Y) e 0% de chance de ter uma filha daltônica."
                    )
                ),
                goldTip = "O doador universal de HEMÁCIAS é o O Rh negativo; porém, o doador universal de PLASMA é o sangue AB, pois o plasma AB não possui nenhuma aglutinina anti-A ou anti-B!"
            ),

            "bio_gen_05" to BioEntry(
                theoryMarkdown = """
                    # Biotecnologia Contemporânea: DNA Recombinante, Transgênicos, Clonagem e CRISPR
                    
                    ### 1. Tecnologia do DNA Recombinante e Transgenia
                    • **Enzimas de Restrição ('Tesouras Moleculares'):** Cortam o DNA em sítios palindrômicos específicos, gerando extremidades coesivas.
                    • **DNA Ligase:** Cola os fragmentos de DNA de espécies diferentes no plasmídeo bacteriano (vetor).
                    • **Transgênico vs Cisgênico:** O transgênico recebe gene de espécie diferente (ex: soja com gene bacteriano de resistência a glifosato); o cisgênico recebe gene de variedade da mesma espécie.
                    • **Por que funciona?** Porque o **CÓDIGO GENÉTICO É UNIVERSAL**: a bactéria traduz o gene humano exatamente na mesma proteína!
                    
                    ### 2. Clonagem Reprodutiva vs Terapêutica (Ovelha Dolly)
                    Transfere-se o núcleo de uma célula somática adulta (\(2n\)) para um ovócito anucleado, estimulando mitoses. Na clonagem reprodutiva, implanta-se o embrião no útero de uma mãe de aluguel; na terapêutica, extraem-se células-tronco embrionárias pluripotentes.
                    
                    ### 3. PCR (Reação em Cadeia da Polimerase) e Eletroforese
                    • **PCR:** Multiplica milhões de cópias de um fragmento de DNA in vitro usando a enzima termoestável Taq polimerase.
                    • **Eletroforese:** Separa fragmentos de DNA em gel sob campo elétrico. Como o DNA tem carga negativa (grupos fosfato), os fragmentos migram em direção ao polo positivo; fragmentos menores correm mais rápido pelo gel, formando o padrão de 'código de barras' usado em testes de paternidade e perícia criminal!
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Bases da Transgenia", "Enzima de Restrição + DNA Ligase + Plasmídeo Vetor", "Construção de moléculas de DNA recombinante"),
                    FormulaOrSyntaxItem("Eletroforese em Gel", "Carga negativa do DNA -> Migração para o polo positivo (+)", "Fragmentos menores correm mais rápido através da malha do gel")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Exemplo ENEM: Teste de Paternidade por Eletroforese",
                        codeOrFormula = "\\text{Cada banda do filho que não veio da mãe DEVE ter vindo do pai}",
                        explanation = "Ao analisar o gel de eletroforese, todas as bandas presentes no DNA do filho devem ser identificadas na mãe ou no suposto pai biológico."
                    )
                ),
                goldTip = "O DNA tem carga elétrica GLOBAL NEGATIVA devido aos grupos fosfato PO₄³⁻ da sua cadeia principal; por isso migra sempre para o polo positivo na eletroforese!"
            )
        )
    }

    private val exerciseMap: Map<String, List<MathExercise>> by lazy {
        mapOf(
            // MÓDULO 2
            "bio_fis_01" to listOf(
                MathExercise(
                    id = "ex_bio_fis_01_1",
                    title = "ENEM: Digestão Química e Microambiente Duodenal",
                    problemStatement = "O processo de digestão química ao longo do trato gastrointestinal humano depende de compartimentos com faixas estritas de pH para o funcionamento das enzimas. Se as secreções pancreáticas alcalinas não neutralizassem o quimo ácido estomacal ao chegar ao duodeno, o efeito fisiológico imediato seria o(a):",
                    options = listOf(
                        "inativação e desnaturação das enzimas digestivas duodenais e pancreáticas que operam em pH básico.",
                        "aumento na taxa catalítica da amilase salivar que sobreviveu à passagem pelo estômago.",
                        "digestão acelerada dos triglicerídeos pela pepsina gástrica estimulada pelo ácido clorídrico.",
                        "formação de glicogênio diretamente nas vilosidades da mucosa intestinal.",
                        "bloqueio imediato da contração dos movimentos peristálticos do esôfago."
                    ),
                    correctIndex = 0,
                    hint = "A pepsina opera em pH 2, mas as enzimas que atuam no duodeno (tripsina, quimiotripsina, lipase) necessitam de pH alcalino (~8) proporcionado pelo bicarbonato pancreático.",
                    stepByStepSolution = "A alternativa A está correta. O estômago tem pH ácido (~2) ótimo para a pepsina. Ao passar para o duodeno, as enzimas pancreáticas e entéricas (tripsina, lipase, amilase) necessitam de pH básico (~8) proporcionado pelo bicarbonato de sódio do suco pancreático. Sem essa neutralização, o meio ácido inativa e desnatura essas enzimas, paralisando a digestão intestinal."
                )
            ),

            "bio_fis_02" to listOf(
                MathExercise(
                    id = "ex_bio_fis_02_1",
                    title = "ENEM: Soroterapia de Emergência vs Vacinação",
                    problemStatement = "Um trabalhador rural é picado no tornozelo por uma serpente cascavel no interior do país. Ao ser socorrido no hospital regional, o médico administra imediatamente soro anticrotálico intravenoso em vez de aplicar uma vacina contra veneno ofídico. A escolha pela soroterapia justifica-se pelo fato de que o soro:",
                    options = listOf(
                        "induz a produção ativa de anticorpos próprios e células de memória no paciente.",
                        "fornece anticorpos heterólogos prontos que neutralizam as toxinas do veneno com ação curativa imediata.",
                        "contém antígenos atenuados de ação rápida que treinam os linfócitos T citotóxicos.",
                        "repara as paredes dos vasos sanguíneos lesados através de fatores hormonais de crescimento.",
                        "confere imunidade permanente contra futuras picadas da mesma espécie de serpente."
                    ),
                    correctIndex = 1,
                    hint = "Na emergência não há tempo para o corpo produzir anticorpos: o soro fornece anticorpos prontos para ação imediata.",
                    stepByStepSolution = "A alternativa B está correta. A peçonha ofídica tem ação rápida e letal. A vacina é um método de imunização ativa preventiva que leva dias a semanas para induzir a síntese de anticorpos. O soro terapêutico é uma imunização passiva curativa que inocula grande quantidade de anticorpos prontos purificados, capazes de neutralizar a toxina circulante de forma imediata."
                )
            ),

            "bio_fis_03" to listOf(
                MathExercise(
                    id = "ex_bio_fis_03_1",
                    title = "ENEM: Regulação Hormonal do Ciclo Menstrual e Nidação",
                    problemStatement = "Durante a segunda metade do ciclo menstrual feminino (fase lútea), o folículo ovariano rompido transforma-se em corpo lúteo sob estímulo do LH. A principal função hormonal do corpo lúteo para viabilizar uma eventual gravidez consiste em secretar:",
                    options = listOf(
                        "FSH, para estimular o crescimento de dezenas de novos folículos secundários.",
                        "progesterona, para vascularizar e manter espessada a camada funcional do endométrio uterino.",
                        "oxitocina, para promover as contrações da musculatura lisa do miométrio.",
                        "insulina, para reduzir a captação de glicose pelos tecidos embrionários.",
                        "testosterona, para acelerar a locomoção dos espermatozoides pelas tubas uterinas."
                    ),
                    correctIndex = 1,
                    hint = "O corpo lúteo secreta progesterona, essencial para preparar o endométrio para a nidação (implantação do blastocisto).",
                    stepByStepSolution = "A alternativa B está correta. O corpo lúteo no ovário secreta elevadas taxas de progesterona (e estrógeno), que mantêm o endométrio uterino ricamente vascularizado, secretor e receptivo à nidação do blastocisto. Caso ocorra fecundação, a progesterona impede que o endométrio se descame (menstruação)."
                )
            ),

            "bio_fis_04" to listOf(
                MathExercise(
                    id = "ex_bio_fis_04_1",
                    title = "ENEM: Mecanismo de Ação da Pílula Anticoncepcional Combinada",
                    problemStatement = "As pílulas anticoncepcionais combinadas compostas por doses sintéticas de estrogênio e progestagênio são amplamente utilizadas no planejamento familiar reprodutivo. Do ponto de vista endócrino, essas pílulas impedem a gravidez porque:",
                    options = listOf(
                        "destroem a parede celular dos espermatozoides depositados no canal vaginal.",
                        "exercem feedback negativo sobre a hipófise, inibindo a secreção de FSH e LH e bloqueando a ovulação.",
                        "aumentam a temperatura do útero para inviabilizar a respiração celular do zigoto.",
                        "estimulam a produção maciça de ocitocina pela neuro-hipófise durante todo o mês.",
                        "induzem mutações cromossômicas temporárias nos ovócitos retidos nos ovários."
                    ),
                    correctIndex = 1,
                    hint = "Níveis plasmáticos constantes de estrógeno e progesterona inibem a liberação de FSH e LH na adeno-hipófise por retroalimentação negativa, impedindo o pico de LH e a ovulação.",
                    stepByStepSolution = "A alternativa B está correta. Ao manter níveis circulantes estáveis de hormônios ovarianos sintéticos (estrogênio e progestagênio), a pílula inibe por feedback negativo a secreção de FSH e LH pela adeno-hipófise. Sem o pico de LH no meio do ciclo, não ocorre a maturação folicular nem a ovulação, tornando a fertilização impossível."
                )
            ),

            // MÓDULO 3
            "bio_gen_01" to listOf(
                MathExercise(
                    id = "ex_bio_gen_01_1",
                    title = "ENEM: Degenerescência do Código Genético e Mutações Silenciosas",
                    problemStatement = "O código genético humano é classificado como degenerado ou redundante. Em um ensaio de sequenciamento gênico, constatou-se que um paciente sofreu uma mutação pontual no DNA em que o códon de RNAm correspondente passou de GAA para GAG. Ambas as trincas codificam o aminoácido ácido glutâmico. Como consequência fenotípica dessa mutação, constata-se:",
                    options = listOf(
                        "a síntese de uma proteína com estrutura primária e funcionalidade perfeitamente normais (mutação silenciosa).",
                        "a interrupção prematura da tradução devido ao surgimento de um códon de parada (stop códon).",
                        "a conversão da célula em linhagem tumoral maligna irreversível.",
                        "a desnaturação imediata de todas as enzimas ribossomais do citoplasma.",
                        "a substituição do ácido glutâmico por valina gerando anemia falciforme."
                    ),
                    correctIndex = 0,
                    hint = "Como o novo códon codifica o mesmo aminoácido, a proteína formada é rigorosamente idêntica.",
                    stepByStepSolution = "A alternativa A está correta. A degenerescência do código genético significa que múltiplos códons especificam o mesmo aminoácido. Quando uma mutação substitui uma base nitrogenada mas o novo códon continua codificando o mesmo aminoácido original, a sequência polipeptídica não se altera (mutação silenciosa), mantendo a proteína com atividade biológica plena."
                )
            ),

            "bio_gen_02" to listOf(
                MathExercise(
                    id = "ex_bio_gen_02_1",
                    title = "ENEM: Herança Autossômica Recessiva e Albinismo",
                    problemStatement = "O albinismo óculo-cutâneo clássico é uma anomalia genética autossômica recessiva (alelo 'a') caracterizada pela ausência de síntese do pigmento melanina. Um casal com pigmentação normal de pele tem um primeiro filho albino. A probabilidade de esse mesmo casal vir a ter uma segunda criança com pigmentação normal de pele e do sexo feminino é de:",
                    options = listOf("1/8", "1/4", "3/8", "1/2", "3/4"),
                    correctIndex = 2,
                    hint = "Como o casal de fenótipo normal gerou um filho albino (aa), ambos são heterozigotos (Aa). A chance de nascer normal é 3/4 e a chance de ser menina é 1/2.",
                    stepByStepSolution = "Casal heterozigoto Aa x Aa.\nChance de nascer com pigmentação normal (AA ou Aa) = 3/4.\nChance de ser do sexo feminino = 1/2.\nProbabilidade conjunta (regra do 'E') = (3/4) * (1/2) = 3/8 (37,5%)."
                )
            ),

            "bio_gen_03" to listOf(
                MathExercise(
                    id = "ex_bio_gen_03_1",
                    title = "ENEM: Ligação Gênica (Linkage) e Taxa de Recombinação",
                    problemStatement = "Em um experimento de cruzamento-teste envolvendo uma linhagem de moscas Drosophila melanogaster, cruzou-se uma fêmea di-híbrida de genótipo Ab/aB com um macho duplo-recessivo ab/ab. Na descendência, observou-se que 16% dos indivíduos apresentavam fenótipos recombinantes resultantes de crossing-over. A distância física entre esses dois genes no mapa cromossômico é de:",
                    options = listOf("8 morganídeos (cM)", "16 morganídeos (cM)", "32 morganídeos (cM)", "42 morganídeos (cM)", "50 morganídeos (cM)"),
                    correctIndex = 1,
                    hint = "Por convenção de mapeamento genético, 1% de recombinação cromossômica equivale a 1 morganídeo (cM).",
                    stepByStepSolution = "A alternativa B está correta. Em genética cromossômica, a frequência de recombinação (crossing-over) é diretamente proporcional à distância física entre os loci dos genes: 1% de recombinantes equivale a 1 unidade de mapa (1 morganídeo ou 1 centimorgan). Logo, 16% de taxa de recombinação corresponde a uma distância genética de 16 cM."
                )
            ),

            "bio_gen_04" to listOf(
                MathExercise(
                    id = "ex_bio_gen_04_1",
                    title = "ENEM: Eritroblastose Fetal e Fator Rh",
                    problemStatement = "A Eritroblastose Fetal (ou Doença Hemolítica do Recém-Nascido) é uma condição clínica grave caracterizada pela destruição de hemácias do feto por anticorpos maternos. Para que haja risco biológico de manifestação dessa doença em uma gestação, a constituição dos tipos sanguíneos para o Fator Rh do pai, da mãe e do feto em gestação deve ser, respectivamente:",
                    options = listOf(
                        "Pai Rh⁺, Mãe Rh⁻ e Feto Rh⁺.",
                        "Pai Rh⁻, Mãe Rh⁺ e Feto Rh⁻.",
                        "Pai Rh⁻, Mãe Rh⁻ e Feto Rh⁺.",
                        "Pai Rh⁺, Mãe Rh⁺ e Feto Rh⁻.",
                        "Pai Rh⁻, Mãe Rh⁻ e Feto Rh⁻."
                    ),
                    correctIndex = 0,
                    hint = "A mãe deve ser Rh negativa para se sensibilizar contra o antígeno Rh positivo herdado do pai pelo feto.",
                    stepByStepSolution = "A alternativa A está correta. A incompatibilidade Rh que causa eritroblastose fetal ocorre exclusivamente quando a mãe é Rh negativa (rr) e o feto é Rh positivo (herdando o alelo R dominante do pai). No primeiro parto, a mãe entra em contato com o sangue fetal Rh⁺ e produz anticorpos anti-Rh. Em gestações subsequentes de fetos Rh⁺, esses anticorpos IgG maternos atravessam a placenta destruindo as hemácias do concepto."
                )
            ),

            "bio_gen_05" to listOf(
                MathExercise(
                    id = "ex_bio_gen_05_1",
                    title = "ENEM: Eletroforese em Gel de DNA e Teste de Paternidade",
                    problemStatement = "Em um laboratório de perícia forense, realizou-se a eletroforese em gel de agarose de amostras de DNA extraídas de uma criança, de sua mãe biológica confirmada e de três homens que afirmam ser o pai (Suspeitos 1, 2 e 3). Na análise comparativa das bandas eletroforéticas, o perito identificará o pai biológico verdadeiro observando que:",
                    options = listOf(
                        "todas as bandas de DNA da criança que não coincidem com as da mãe biológica devem obrigatoriamente estar presentes no pai biológico.",
                        "o pai biológico deve possuir um número menor de fragmentos de restrição do que a mãe.",
                        "o DNA do pai deve ter corrido no sentido oposto (em direção ao polo negativo) em virtude das cargas positivas.",
                        "os fragmentos de DNA da mãe anulam completamente as bandas transmitidas pelo cromossomo paterno.",
                        "apenas as bandas do cromossomo mitocondrial materno são utilizadas na identificação do parentesco."
                    ),
                    correctIndex = 0,
                    hint = "A criança herda 50% dos seus alelos da mãe e 50% do pai biológico. Cada banda presente na criança que não veio da mãe teve que vir necessariamente do pai.",
                    stepByStepSolution = "A alternativa A está correta. Cada indivíduo herda metade de seus cromossomos da mãe e metade do pai. Portanto, na análise de marcadores polimórficos de DNA (como VNTR e STR) por eletroforese, todas as bandas do filho devem ser encontradas na mãe ou no pai biológico. As bandas que não possuem correspondência na mãe têm origem paterna obrigatória; o homem que apresentar todas essas bandas paternas é o pai biológico."
                )
            )
        )
    }
}
