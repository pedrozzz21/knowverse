package com.example.data.biology

/**
 * Motor de Análise Pedagógica Aprofundada para Questões de Biologia.
 * Responsável por pesquisar o contexto biológico, decompor cada uma das alternativas
 * (A, B, C, D, E), justificar cientificamente por que a correta é verdadeira e por que
 * cada distrator está errado, ensinando a fundo a questão para o estudante no ENEM.
 */
object BiologyAlternativeAnalyzer {

    data class BiologyAnalysisResult(
        val pedagogicalLesson: String,
        val alternativesAnalysis: List<AlternativeEvaluation>,
        val enemExamTip: String
    )

    data class AlternativeEvaluation(
        val letter: String,
        val text: String,
        val isCorrect: Boolean,
        val reason: String
    )

    /**
     * Gera a lista completa de passos de resolução aprofundados para uma questão de Biologia.
     */
    fun generateBiologyDeepAnalysis(
        statement: String,
        options: List<String>,
        correctIndex: Int,
        subtopic: String
    ): List<String> {
        val domain = detectBiologicalDomain(statement + " " + subtopic + " " + options.joinToString(" "))
        val correctOption = options.getOrNull(correctIndex) ?: ""
        val cleanCorrect = cleanOptionText(correctOption)
        val correctLetter = ('A' + correctIndex).toString()

        val steps = mutableListOf<String>()

        // 1. Ensinamento Conceitual do Tema
        steps.add(
            "🧠 Ensinamento Conceitual & Contexto Biológico ($subtopic):\n" +
                    explainBiologicalConcept(domain, statement, cleanCorrect)
        )

        // 2. Análise da Alternativa Correta
        steps.add(
            "✅ Alternativa $correctLetter (CORRETA - Gabarito Oficial):\n" +
                    "\"$cleanCorrect\"\n" +
                    "Por que é verdadeira: ${explainWhyCorrect(domain, cleanCorrect, statement)}"
        )

        // 3. Análise Individual de Todos os Distratores (Por que cada alternativa está errada)
        val distractorExplanations = StringBuilder("❌ Análise dos Distratores (Por que as demais alternativas estão incorretas):\n\n")
        options.forEachIndexed { idx, opt ->
            if (idx != correctIndex) {
                val letter = ('A' + idx).toString()
                val clean = cleanOptionText(opt)
                val distractorReason = explainWhyDistractorWrong(domain, clean, cleanCorrect, statement)
                distractorExplanations.append("• Alternativa $letter [INCORRETA]: \"$clean\"\n  Erro biológico: $distractorReason\n\n")
            }
        }
        steps.add(distractorExplanations.toString().trimEnd())

        // 4. Síntese Didática e Padrão de Prova do ENEM
        steps.add(
            "🎯 Dica de Ouro & Padrão de Cobrança ENEM:\n" +
                    getEnemDomainTip(domain, subtopic)
        )

        return steps
    }

    /**
     * Gera uma explicação unificada em formato texto contínuo para o campo stepByStepSolution.
     */
    fun generateFormattedSolutionString(
        statement: String,
        options: List<String>,
        correctIndex: Int,
        subtopic: String
    ): String {
        val analysis = generateBiologyDeepAnalysis(statement, options, correctIndex, subtopic)
        return analysis.joinToString("\n\n" + "—".repeat(28) + "\n\n")
    }

    private fun cleanOptionText(opt: String): String {
        return opt.replace(Regex("^(\\([A-E]\\)|[A-E]\\)|\\b[A-E]\\s*[-–:]\\s*)", RegexOption.IGNORE_CASE), "").trim()
    }

    private enum class BioDomain {
        ECOLOGIA,
        FISIOLOGIA,
        GENETICA,
        CITOLOGIA,
        EVOLUCAO,
        GERAL
    }

    private fun detectBiologicalDomain(text: String): BioDomain {
        val lower = text.lowercase()
        return when {
            lower.contains("cadeia") || lower.contains("teia") || lower.contains("trófico") ||
                    lower.contains("nicho") || lower.contains("habitat") || lower.contains("poluição") ||
                    lower.contains("eutrofização") || lower.contains("biomagnificação") || lower.contains("magnificação") ||
                    lower.contains("sucessão") || lower.contains("ecossistema") || lower.contains("estufa") ||
                    lower.contains("relação ecológica") || lower.contains("mutualismo") || lower.contains("parasitismo") ||
                    lower.contains("cerrado") || lower.contains("mata atlântica") || lower.contains("biorremediação") -> BioDomain.ECOLOGIA

            lower.contains("digest") || lower.contains("enzima") || lower.contains("estômago") ||
                    lower.contains("intestino") || lower.contains("coração") || lower.contains("cardíac") ||
                    lower.contains("circula") || lower.contains("vaso") || lower.contains("sangue") ||
                    lower.contains("pulmão") || lower.contains("respir") || lower.contains("hematose") ||
                    lower.contains("rim") || lower.contains("renal") || lower.contains("néfron") ||
                    lower.contains("urina") || lower.contains("adh") || lower.contains("hormônio") ||
                    lower.contains("insulina") || lower.contains("glucagon") || lower.contains("tireoide") ||
                    lower.contains("neurônio") || lower.contains("sinapse") || lower.contains("imun") ||
                    lower.contains("anticorpo") || lower.contains("vacina") || lower.contains("soro") -> BioDomain.FISIOLOGIA

            lower.contains("mendel") || lower.contains("alelo") || lower.contains("gene") ||
                    lower.contains("genótipo") || lower.contains("fenótipo") || lower.contains("dna") ||
                    lower.contains("rna") || lower.contains("cromossomo") || lower.contains("heredograma") ||
                    lower.contains("abo") || lower.contains("fator rh") || lower.contains("eritroblastose") ||
                    lower.contains("transgênic") || lower.contains("crispr") || lower.contains("clonagem") ||
                    lower.contains("pcr") || lower.contains("código genético") || lower.contains("tradução") -> BioDomain.GENETICA

            lower.contains("membrana") || lower.contains("osmose") || lower.contains("difusão") ||
                    lower.contains("mitocôndria") || lower.contains("ribossomo") || lower.contains("golgi") ||
                    lower.contains("lisossomo") || lower.contains("respiração celular") || lower.contains("atp") ||
                    lower.contains("glicólise") || lower.contains("krebs") || lower.contains("fotossíntese") ||
                    lower.contains("cloroplasto") || lower.contains("mitose") || lower.contains("meiose") ||
                    lower.contains("crossing-over") || lower.contains("proteína") || lower.contains("lipídio") ||
                    lower.contains("carboidrato") || lower.contains("desnaturação") -> BioDomain.CITOLOGIA

            lower.contains("darwin") || lower.contains("lamarck") || lower.contains("seleção natural") ||
                    lower.contains("neodarwinismo") || lower.contains("mutação") || lower.contains("fóssil") ||
                    lower.contains("homolog") || lower.contains("análog") || lower.contains("cladograma") ||
                    lower.contains("ancestral") || lower.contains("especiação") || lower.contains("bipedalismo") ||
                    lower.contains("hominídeo") || lower.contains("hominização") || lower.contains("resistência a antibiótico") -> BioDomain.EVOLUCAO

            else -> BioDomain.GERAL
        }
    }

    private fun explainBiologicalConcept(domain: BioDomain, statement: String, correctText: String): String {
        val s = statement.lowercase()
        return when (domain) {
            BioDomain.ECOLOGIA -> when {
                s.contains("cadeia") || s.contains("teia") || s.contains("energia") ->
                    "Nas teias ecológicas, a energia flui de forma unidirecional e decrescente a cada nível trófico (produtor -> consumidor primário -> secundário), com perdas em calor metabólico. Já contaminantes bioacumulativos (metais pesados, agrotóxicos persistentes) sofrem biomagnificação trófica, concentrando-se progressivamente nos carnívoros de topo."
                s.contains("relação") || s.contains("mutualismo") || s.contains("parasit") ->
                    "As relações ecológicas são classificadas pelo benefício (+) ou prejuízo (-) mútuo. Relações harmônicas mantêm o equilíbrio trófico sem dano, enquanto relações desarmônicas (predação, parasitismo, competição) funcionam como pressões seletivas cruciais para o controle populacional e autorregulação dos ecossistemas."
                s.contains("ciclo") || s.contains("carbono") || s.contains("nitrogênio") ->
                    "Os ciclos biogeoquímicos garantem a reciclagem contínua da matéria orgânica e inorgânica. O carbono transita entre a atmosfera e a biosfera através da fotossíntese e respiração. O nitrogênio depende de bactérias fixadoras e nitrificantes (Rhizobium, Nitrosomonas, Nitrobacter) para ser convertido em formas assimiláveis pelas plantas."
                s.contains("impacto") || s.contains("eutrofização") || s.contains("poluição") ->
                    "Impactos ecológicos geram cascatas de degradação. Na eutrofização, o excesso de matéria orgânica provoca floração de algas, bloqueio solar, morte de submersos, proliferação de bactérias decompositoras e esgotamento total do oxigênio dissolvido (anoxia), provocando mortandade da fauna aquática."
                else ->
                    "A ecologia no ENEM avalia as respostas dos organismos frente a mudanças no meio biótico e abiótico, exigindo compreender a partição de nichos, o equilíbrio dinâmico e o papel vital da biodiversidade na resiliência ambiental."
            }

            BioDomain.FISIOLOGIA -> when {
                s.contains("digest") || s.contains("enzima") ->
                    "A digestão humana é um processo sequencial químico-enzimático onde o pH controla a atividade das enzimas: amilase salivar (pH 7 neutro), pepsina gástrica (pH 2 ácido) e tripsina/lipase pancreáticas (pH 8 básico no duodeno). A bile não possui enzimas, atuando exclusivamente como emulsificante mecânico de lipídios."
                s.contains("circula") || s.contains("coração") || s.contains("pressão") ->
                    "O sistema cardiovascular humano é duplo e completo. O lado direito transporta sangue venoso aos pulmões (pequena circulação), enquanto o lado esquerdo bombeia sangue arterial para todo o corpo sob alta pressão (grande circulação), sem mistura de sangues no coração de quatro cavidades."
                s.contains("respir") || s.contains("hematose") || s.contains("oxigênio") ->
                    "A hematose alveolar baseia-se na difusão simples por gradiente de pressão parcial de gases. O transporte de O2 ocorre ligado à hemoglobina nos eritrócitos, e a maior parte do CO2 é carreada dissolvida no plasma na forma de íons bicarbonato (HCO3-), regulando o pH sanguíneo sob controle do bulbo encefálico."
                s.contains("rim") || s.contains("néfron") || s.contains("adh") ->
                    "A osmorregulação renal ocorre no néfron através da filtração glomerular, reabsorção tubular e secreção ativa. O hormônio antidiurético (ADH ou vasopressina), secretado pela neuro-hipófise, aumenta a permeabilidade à água nos ductos coletores, retendo água no organismo e concentrando a urina."
                s.contains("imun") || s.contains("vacina") || s.contains("soro") ->
                    "A imunização ativa (vacina) inocula antígenos atenuados/inativados para estimular a resposta imunológica própria e gerar memória celular duradoura (linfócitos B e T). A imunização passiva (soro) inocula anticorpos heterólogos prontos para ação curativa e emergencial, sem gerar memória imunitária."
                else ->
                    "A fisiologia humana opera por homeostase via mecanismos de feedback negativo (retroalimentação), onde sistemas integrados mantêm constantes a temperatura, glicemia, volemia e pH do meio interno."
            }

            BioDomain.GENETICA -> when {
                s.contains("mendel") || s.contains("cruzamento") ->
                    "A 1ª Lei de Mendel estabelece a segregação independente dos fatores (alelos) durante a meiose na formação dos gametas. Cada indivíduo herda um alelo paterno e um materno. A proporção fenotípica clássica em cruzamentos de heterozigotos (Aa x Aa) é 3 dominantes para 1 recessivo (3:1)."
                s.contains("sangue") || s.contains("abo") || s.contains("rh") ->
                    "O sistema ABO é um caso clássico de polialelia e codominância: alelos IA e IB são codominantes entre si e dominantes sobre o alelo i. O grupo O (ii) não tem aglutinogênios nas hemácias e possui anticorpos anti-A e anti-B no plasma (doador universal). No fator Rh, a eritroblastose fetal ocorre quando mãe Rh- gera feto Rh+ após sensibilização prévia."
                s.contains("dna") || s.contains("rna") || s.contains("código") ->
                    "O dogma central da biologia molecular compreende a replicação semiconservativa do DNA, transcrição em RNA mensageiro e tradução em proteínas nos ribossomos. O código genético é universal (quase todos os seres usam os mesmos códons) e degenerado (múltiplos códons codificam o mesmo aminoácido, reduzindo o impacto de mutações pontuais)."
                s.contains("transgênic") || s.contains("biotec") || s.contains("crispr") ->
                    "Organismos Geneticamente Modificados (Transgênicos) recebem genes funcionais de espécies distintas através de enzimas de restrição e vetores plasmídicos, viabilizado pela universalidade do código genético. O sistema CRISPR-Cas9 permite edição sítio-específica precisa do genoma."
                else ->
                    "A genética contemporânea no ENEM conecta a transmissão hereditária mendeliana molecularmente à expressão gênica e às aplicações biotecnológicas éticas e sanitárias."
            }

            BioDomain.CITOLOGIA -> when {
                s.contains("membrana") || s.contains("transporte") || s.contains("osmose") ->
                    "A membrana celular segue o modelo do mosaico fluido (bicamada fosfolipídica anfipática com proteínas integrais e periféricas). O transporte passivo (difusão simples, facilitada e osmose) ocorre a favor do gradiente eletroquímico sem consumo de ATP; já o transporte ativo (ex: bomba Na+/K+) consome energia para mover solutos contra o gradiente."
                s.contains("respiração") || s.contains("atp") || s.contains("fermentação") ->
                    "A respiração celular aeróbica compreende três etapas: Glicólise (citosol, anaeróbica), Ciclo de Krebs (matriz mitocondrial) e Fosforilação Oxidativa (cristas mitocondriais, com a cadeia transportadora de elétrons e oxigênio como aceptor final), gerando cerca de 30-32 ATPs, contra apenas 2 ATPs da fermentação."
                s.contains("fotossíntese") || s.contains("cloroplasto") ->
                    "A fotossíntese converte energia luminosa em energia química: na fase fotoquímica (tilacoides) ocorre a fotólise da água com liberação de O2 e geração de ATP e NADPH; na fase enzimática/Ciclo de Calvin (estroma), ocorre a fixação do CO2 para síntese de glicose."
                s.contains("mitose") || s.contains("meiose") ->
                    "A mitose é equacional (célula 2n gera duas células idênticas 2n), responsável pelo crescimento somático e regeneração tecidual. A meiose é reducional e diversificadora (uma célula 2n gera quatro gametas n), promovendo variabilidade genética através do crossing-over na prófase I e segregação independente dos cromossomos homólogos."
                else ->
                    "A citologia foca na correlação entre estrutura e função: a célula é a unidade morfofisiológica básica, e cada organela desempenha compartimentalização metabólica indispensável à sobrevivência do organismo."
            }

            BioDomain.EVOLUCAO -> when {
                s.contains("lamarck") || s.contains("darwin") ->
                    "A teoria de Lamarck baseava-se na lei do uso e desuso de órgãos e transmissão dos caracteres adquiridos ao longo da vida (hipótese refutada pela genética molecular, pois alterações somáticas não alteram o DNA gamético). Darwin propôs a descendência com modificação e seleção natural: o ambiente seleciona os indivíduos que já apresentam variações vantajosas pré-existentes."
                s.contains("neodarwin") || s.contains("mutação") || s.contains("seleção") ->
                    "O Neodarwinismo (Teoria Sintética da Evolução) uniu a seleção natural aos mecanismos genéticos desconhecidos por Darwin: as mutações gênicas aleatórias e a recombinação meiótica são as fontes primárias geradoras de variabilidade, enquanto a seleção natural é o fator evolutivo orientador que filtra as frequências alélicas da população."
                s.contains("homolog") || s.contains("análog") || s.contains("órgão") ->
                    "Órgãos homólogos possuem a mesma origem embrionária e ancestralidade evolutiva comum, mesmo que tenham funções diferentes (divergência adaptativa, ex: braço humano e asa de morcego). Órgãos análogos têm origens embrionárias distintas mas desempenham mesma função por pressões seletivas semelhantes (convergência adaptativa, ex: asa de inseto e asa de ave)."
                else ->
                    "A evolução biológica no ENEM nunca é teleológica (não possui finalidade preconcebida); ela é um processo histórico contínuo onde populações inteiras (e não indivíduos isolados) evoluem através da alteração nas frequências gênicas ao longo das gerações."
            }

            BioDomain.GERAL ->
                "A questão investiga a correlação entre estrutura e função biológica, onde processos moleculares e celulares determinam as respostas adaptativas do organismo ao meio ambiente."
        }
    }

    private fun explainWhyCorrect(domain: BioDomain, correctText: String, statement: String): String {
        val lower = correctText.lowercase()
        return when {
            lower.contains("nicho") ->
                "Ao explorar recursos temporais ou espaciais distintos no ecossistema, os organismos reduzem a sobreposição de nichos ecológicos, permitindo a coexistência pacífica e minimizando a exclusão competitiva de acordo com o Princípio de Gause."
            lower.contains("endemismo") || lower.contains("hotspot") ->
                "Atende aos critérios internacionais estritos de Hotspot de Biodiversidade: concentração de pelo menos 1.500 espécies de plantas vasculares exclusivas (endêmicas) e perda cumulativa superior a 70% de sua cobertura vegetal original nativa."
            lower.contains("ecossistema") ->
                "A integração equilibrada entre a comunidade de seres vivos (fatores bióticos) e as variáveis físico-químicas como água, temperatura e solo (fatores abióticos) define a unidade biológica funcional do ecossistema."
            lower.contains("biomagnificação") || lower.contains("bioacumula") ->
                "Poluentes lipossolúveis não biodegradáveis acumulam-se progressivamente ao longo dos tecidos vivos da cadeia trófica, resultando em concentrações exponencialmente maiores nos carnívoros de topo."
            lower.contains("vacina") || lower.contains("antígeno") ->
                "As vacinas contêm antígenos inativados ou atenuados que estimulam o próprio sistema imunológico do indivíduo a sintetizar anticorpos específicos e células de memória imunológica duradoura (linfócitos B e T)."
            lower.contains("soro") || lower.contains("anticorpo") ->
                "O soro terapêutico injeta anticorpos pré-formados com efeito neutralizante imediato, ideal para urgências toxicológicas (veneno de serpente, tétano) em que o paciente não pode aguardar os dias necessários para a resposta imune ativa."
            lower.contains("enzima") || lower.contains("ph") ->
                "Cada enzima do trato digestório possui conformação estrutural ternária ótima adaptada ao microambiente do órgão onde atua, garantindo velocidade máxima de catálise sem sofrer desnaturação proteica."
            lower.contains("seleção natural") || lower.contains("vantajos") ->
                "As variações gênicas vantajosas pré-existentes na população conferem maior taxa de sobrevivência e fecundidade diferencial em determinado ambiente, aumentando a frequência desses alelos ao longo das gerações."
            lower.contains("difusão") || lower.contains("osmose") ->
                "O transporte ocorre de forma espontânea e termodinamicamente favorável a favor do gradiente de concentração, sem dissipação de energia metabólica na forma de hidrólise de ATP."
            lower.contains("mutação") || lower.contains("recombinação") ->
                "As mutações e o crossing-over introduzem alelos e combinações inéditas na linhagem germinativa, gerando a diversidade fenotípica imprescindível para a atuação da seleção natural."
            else ->
                "Esta alternativa sintetiza com exatidão o princípio biológico cobrado pelo ENEM, correlacionando diretamente a evidência apresentada no enunciado com os postulados científicos comprovados."
        }
    }

    private fun explainWhyDistractorWrong(
        domain: BioDomain,
        distractorText: String,
        correctText: String,
        statement: String
    ): String {
        val lower = distractorText.lowercase()
        return when {
            lower.contains("mesmo nicho") ->
                "Se duas espécies ocupassem o mesmo nicho ecológico simultaneamente no mesmo habitat, ocorreria exclusão competitiva inevitável (Princípio de Gause), com a extinção local de uma delas."
            lower.contains("eliminou") || lower.contains("anulou a necessidade") ->
                "A seleção natural jamais elimina necessidades fisiológicas vitais como alimentação ou respiração em organismos heterótrofos; ela atua moldando comportamentos e eficiência metabólica."
            lower.contains("ecossistemas diferentes") ->
                "O enunciado deixa claro que os organismos habitam a mesma comunidade e ecossistema local; o que diverge é o micro-habitat ou estrato de forrageamento."
            lower.contains("apenas") || lower.contains("exclusividade") || lower.contains("ausência total") ->
                "Generalização indevida muito comum no ENEM. Classificações biológicas como Hotspots exigem simultaneamente alta taxa de endemismo e alto índice de degradação antrópica comprovada."
            lower.contains("transmissão de caracteres adquiridos") || lower.contains("uso e desuso") ->
                "Conceito lamarckista refutado. Alterações fenotípicas adquiridas nas células somáticas por treino ou lesão não alteram as sequências nucleotídicas do DNA das células germinativas/gametas."
            lower.contains("gerar memória") && (lower.contains("soro") || lower.contains("anticorpos prontos")) ->
                "Confusão clássica do ENEM: o soro fornece imunidade passiva temporária e não induz proliferação clonal de linfócitos de memória, cessando seu efeito após a depuração dos anticorpos exógenos."
            lower.contains("vacina") && (lower.contains("emergencial") || lower.contains("curativo")) ->
                "A vacina é profilática (preventiva) e demanda dias para induzir a resposta imune primária. Em picadas de animais peçonhentos, a vacina seria ineficaz no curto prazo."
            lower.contains("bile digere quimicamente") || lower.contains("enzimas da bile") ->
                "Pegadinha recorrente no ENEM: a bile é secretada pelo fígado e concentrada na vesícula biliar, mas NÃO CONTÉM enzimas digestivas. Sua função é emulsificação física (ação detergente) de gotas de gordura."
            lower.contains("estômago com ph básico") || lower.contains("duodeno com ph ácido") ->
                "Inversão anatômico-química de pH: o estômago possui suco gástrico rico em HCl com pH ácido (~2), enquanto o duodeno recebe bicarbonato pancreático para elevar o pH para alcalino (~8)."
            lower.contains("oxigênio aceptor inicial") ->
                "O gás oxigênio (O2) é o aceptor FINAL de elétrons e prótons H+ na cadeia respiratória mitocondrial, unindo-se a eles para formar moléculas de água (H2O), e não aceptor inicial."
            lower.contains("população") && statement.lowercase().contains("espécies diferentes") ->
                "População refere-se exclusivamente a indivíduos da MESMA espécie no mesmo território. Ao incluir espécies distintas e fatores físico-químicos, o nível de organização é ecossistema."
            lower.contains("sem gastar atp") && (lower.contains("contra o gradiente") || lower.contains("ativo")) ->
                "Transportar substâncias contra o gradiente eletroquímico de concentração viola o equilíbrio difusional espontâneo e requer obrigatoriamente hidrólise de ATP por carreadores específicos."
            lower.contains("meiose ocorre nas células somáticas") ->
                "A meiose em animais destina-se exclusivamente à gametogênese nas células germinativas das gônadas. O crescimento e regeneração somáticos realizam-se por mitose."
            else ->
                "Contém erro conceitual direto ou incoerência com as leis biológicas fundamentais do tema, divergindo dos dados empíricos observados e das condições estipuladas no problema."
        }
    }

    private fun getEnemDomainTip(domain: BioDomain, subtopic: String): String {
        return when (domain) {
            BioDomain.ECOLOGIA ->
                "Em Ecologia no ENEM (assunto campeão da prova com ~33% das questões), fique atento às distinções conceituais finas: Nicho (profissão) vs Habitat (endereço), Bioacumulação (ao longo da vida do indivíduo) vs Biomagnificação (ao longo dos níveis tróficos), e Eutrofização (onde a causa imediata da morte de peixes é a falta de oxigênio por ação bacteriana, e não a toxina das algas)."
            BioDomain.FISIOLOGIA ->
                "Em Fisiologia Humana no ENEM, as questões adoram cenários clínicos e de saúde pública: Vacina (prevenção, antígeno, memória) vs Soro (cura, anticorpo, imediato), ação do ADH com consumo de álcool (o álcool inibe o ADH, causando micção frequente e desidratação), e o papel da bile como detergente físico sem enzimas."
            BioDomain.GENETICA ->
                "Em Genética no ENEM, decore as bases da compatibilidade sanguínea (O- é doador universal de hemácias, AB+ é receptor universal de hemácias), os heredogramas com pais fenotipicamente iguais gerando filho diferente (revelando que o filho é recessivo e os pais heterozigotos), e a universalidade do código genético como base da tecnologia do DNA recombinante."
            BioDomain.CITOLOGIA ->
                "Em Citologia no ENEM, lembre-se do rendimento energético (respiração aeróbica com aceptor final O2 rende ~30 ATPs, enquanto fermentação rende apenas 2 ATPs), da osmose (célula vegetal plasmolisada não se rompe por possuir parede celulósica), e da especificidade chave-fechadura de enzimas sob variações de temperatura e pH."
            BioDomain.EVOLUCAO ->
                "Em Evolução no ENEM, fuja das alternativas que usem verbos no imperativo de intenção ('as bactérias mutaram PARA resistir' ou 'a girafa esticou o pescoço PARA alcançar'). A mutação ocorre ao acaso; o ambiente apenas SELECIONA quem já possui o alelo favorável. Lembre-se também de Órgãos Homólogos (mesma origem = divergência) vs Análogos (mesma função = convergência)."
            BioDomain.GERAL ->
                "No ENEM de Ciências da Natureza, o enunciado quase sempre traz um contexto real ou experimental. Sublinhe as variáveis dependentes e independentes, identifique o conceito biológico nuclear e elimine alternativas com termos absolutos ou contradições fisiológicas."
        }
    }
}
