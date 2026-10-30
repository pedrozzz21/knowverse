package com.example.data.biology

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Gerador de Conteúdo Didático e Exercícios Comentados Aprofundados
 * para os novos módulos da Trilha de Biologia ENEM:
 * - Módulo 2: Fisiologia Humana
 * - Módulo 3: Genética e Biotecnologia
 * - Módulo 4: Citologia e Bioquímica Celular
 * - Módulo 5: Evolução Biológica e Hominização
 */
object BiologyModuleGenerator {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        val title = lesson.title
        val subtopic = lesson.subtopic

        return when {
            lesson.moduleId == "mod_bio_02_fisiologia" -> buildPhysiologyBundle(lesson)
            lesson.moduleId == "mod_bio_03_genetica" -> buildGeneticsBundle(lesson)
            lesson.moduleId == "mod_bio_04_citologia" -> buildCytologyBundle(lesson)
            lesson.moduleId == "mod_bio_05_evolucao" -> buildEvolutionBundle(lesson)
            else -> buildGeneralBiologyBundle(lesson)
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when {
            lesson.moduleId == "mod_bio_02_fisiologia" -> buildPhysiologyExercises(lesson)
            lesson.moduleId == "mod_bio_03_genetica" -> buildGeneticsExercises(lesson)
            lesson.moduleId == "mod_bio_04_citologia" -> buildCytologyExercises(lesson)
            lesson.moduleId == "mod_bio_05_evolucao" -> buildEvolutionExercises(lesson)
            else -> buildDefaultBiologyExercises(lesson)
        }
    }

    // ========================================================
    // MÓDULO 2: FISIOLOGIA HUMANA
    // ========================================================
    private fun buildPhysiologyBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return CalculusDidacticBundle(
            theoryMarkdown = """
                # Fisiologia Humana: ${lesson.title}
                
                No ENEM, a **Fisiologia Humana** é cobrada através da relação entre órgãos, regulação hormonal e manutenção da homeostase frente a desafios clínicos e ambientais.
                
                ### Subtópico: ${lesson.subtopic}
                
                ### 1. Mecanismos e Integração Funcional
                O organismo humano opera sob regulação por retroalimentação negativa (**feedback negativo**). Quando uma variável interna (glicemia, pressão arterial, osmolalidade, temperatura ou pH) desvia da faixa fisiológica ideal, sensores neurais e endócrinos ativam respostas coordenadas para restaurar o ponto de equilíbrio.
                
                ### 2. Pontos Críticos e Atenção no ENEM
                • **Controle Químico e Enzimático:** As enzimas têm sítio ativo termo e pH-sensível (ex: amilase salivar em pH 7, pepsina estomacal em pH 2, tripsina duodenal em pH 8).
                • **Transporte de Gases:** A hematose pulmonar depende de gradiente de difusão de O₂ e CO₂. O CO₂ é transportado prioritariamente como íon bicarbonato (HCO₃⁻) no plasma, regulando o pH sanguíneo.
                • **Ação Renal e Osmorregulação:** Néfrons filtram o plasma na cápsula de Bowman e reabsorvem água nos túbulos coletores sob ação do hormônio ADH (vasopressina).
                • **Imunologia Aplicada:** Vacinas inoculam antígenos atenuados para gerar imunidade ativa e memória; soros fornecem anticorpos prontos para ação curativa e emergencial (imunidade passiva).
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem("Feedback Negativo", "Estímulo -> Resposta inibidora do estímulo inicial", "Manutenção da homeostase corporal"),
                FormulaOrSyntaxItem("Hematose e Bicarbonato", "CO₂ + H₂O ⇌ H₂CO₃ ⇌ H⁺ + HCO₃⁻", "Equilíbrio ácido-básico do sangue (pH 7,35 - 7,45)"),
                FormulaOrSyntaxItem("Ação do ADH", "Aumento da osmolalidade -> Secreção de ADH -> Reabsorção de H₂O", "Inibido pelo consumo de álcool (desidratação)")
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Análise Clínica: Vacina vs Soro Antiofídico",
                    codeOrFormula = "Prevenção com memória imunológica vs Terapia emergencial imediata",
                    explanation = "A vítima de picada de cobra necessita de anticorpos neutralizantes imediatos (soro), pois a resposta ativa primária induzida por uma vacina levaria semanas, sendo tardia para combater a peçonha."
                )
            ),
            goldTip = "No ENEM: Se a questão mencionar ingestão de álcool e micção excessiva, a resposta envolve inibição do ADH pela hipófise!"
        )
    }

    private fun buildPhysiologyExercises(lesson: EnemTrailLesson): List<MathExercise> {
        val q1 = MathExercise(
            id = "${lesson.id}_q1",
            title = "Homeostase e Regulação Fisiológica",
            problemStatement = "Durante uma corrida de longa distância em dia quente, um atleta perde grande volume de líquido pelo suor. Em resposta ao aumento da osmolalidade plasmática, o organismo ativa mecanismos compensatórios para manter o volume sanguíneo. A resposta fisiológica correta esperada é:",
            options = listOf(
                "inibição da secreção do hormônio ADH pela neuro-hipófise, aumentando o volume de urina.",
                "estímulo à secreção do hormônio ADH, promovendo reabsorção renal de água nos ductos coletores.",
                "aumento da taxa de filtração glomerular, acelerando a perda de sais minerais pela bexiga.",
                "diminuição da produção de aldosterona pela medula suprarrenal, retendo potássio.",
                "bloqueio imediato da sudorese para evitar perda calórica através da pele."
            ),
            correctIndex = 1,
            hint = "Pense no hormônio antidiurético (ADH): alta concentração do sangue estimula sua liberação para salvar água.",
            stepByStepSolution = BiologyAlternativeAnalyzer.generateFormattedSolutionString(
                statement = "Durante uma corrida de longa distância em dia quente, um atleta perde grande volume de líquido pelo suor. Em resposta ao aumento da osmolalidade plasmática, o organismo ativa mecanismos compensatórios para manter o volume sanguíneo.",
                options = listOf(
                    "inibição da secreção do hormônio ADH pela neuro-hipófise, aumentando o volume de urina.",
                    "estímulo à secreção do hormônio ADH, promovendo reabsorção renal de água nos ductos coletores.",
                    "aumento da taxa de filtração glomerular, acelerando a perda de sais minerais pela bexiga.",
                    "diminuição da produção de aldosterona pela medula suprarrenal, retendo potássio.",
                    "bloqueio imediato da sudorese para evitar perda calórica através da pele."
                ),
                correctIndex = 1,
                subtopic = "Sistema Excretor e Hormônio ADH"
            )
        )

        val q2 = MathExercise(
            id = "${lesson.id}_q2",
            title = "Digestão Química e Microambiente de pH",
            problemStatement = "O processo de digestão dos nutrientes ao longo do tubo digestório humano depende de enzimas com faixas estritas de pH ótimo para catálise. Se o quimo alimentar não recebesse as secreções pancreáticas e biliares ao chegar ao duodeno, ocorreria:",
            options = listOf(
                "bloqueio da ação das enzimas duodenais devido à manutenção do pH ácido do estômago.",
                "digestão acelerada de lipídios pela amilase em virtude do excesso de ácido clorídrico.",
                "desnaturação total da pepsina no estômago antes de entrar no intestino delgado.",
                "aumento na síntese de ptialina pelas vilosidades intestinais em substituição à bile.",
                "emulsificação imediata de gorduras pela tripsina ativada em meio ácido."
            ),
            correctIndex = 0,
            hint = "O estômago é ácido (pH 2) e o duodeno precisa de bicarbonato para neutralizar e ativar enzimas que atuam em pH básico (pH 8).",
            stepByStepSolution = BiologyAlternativeAnalyzer.generateFormattedSolutionString(
                statement = "O processo de digestão dos nutrientes ao longo do tubo digestório humano depende de enzimas com faixas estritas de pH ótimo para catálise. Se o quimo alimentar não recebesse as secreções pancreáticas e biliares ao chegar ao duodeno, ocorreria:",
                options = listOf(
                    "bloqueio da ação das enzimas duodenais devido à manutenção do pH ácido do estômago.",
                    "digestão acelerada de lipídios pela amilase em virtude do excesso de ácido clorídrico.",
                    "desnaturação total da pepsina no estômago antes de entrar no intestino delgado.",
                    "aumento na síntese de ptialina pelas vilosidades intestinais em substituição à bile.",
                    "emulsificação imediata de gorduras pela tripsina ativada em meio ácido."
                ),
                correctIndex = 0,
                subtopic = "Digestão e Enzimas Digestivas"
            )
        )

        return listOf(q1, q2)
    }

    // ========================================================
    // MÓDULO 3: GENÉTICA E BIOTECNOLOGIA
    // ========================================================
    private fun buildGeneticsBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return CalculusDidacticBundle(
            theoryMarkdown = """
                # Genética e Biotecnologia: ${lesson.title}
                
                A Genética estuda a hereditariedade e a base molecular da vida. No ENEM, a ênfase recai sobre a interpretação de heredogramas, transfusões sanguíneas e aplicações bioéticas da engenharia genética.
                
                ### Subtópico: ${lesson.subtopic}
                
                ### 1. Padrões de Herança e Leis de Mendel
                • **1ª Lei de Mendel (Monoibridismo):** Segregação igual dos alelos na meiose (Aa -> 50% A, 50% a). Em cruzamentos entre heterozigotos (Aa x Aa), a proporção fenotípica clássica é 3:1 e a genotípica 1:2:1.
                • **Herança dos Grupos Sanguíneos (Sistema ABO e Rh):**
                  - O grupo O (ii) não tem aglutinogênios de membrana e possui anticorpos anti-A e anti-B plasmáticos (Doador universal de hemácias).
                  - O grupo AB (IAIB) possui ambos os aglutinogênios e nenhum anticorpo anti-A/anti-B no plasma (Receptor universal).
                  - **Eritroblastose Fetal:** Mãe Rh⁻ sensibilizada gestando feto Rh⁺.
                
                ### 2. Biologia Molecular e Biotecnologia
                • **Universalidade do Código Genético:** O fato de que a trinca de bases (códon) codifica o mesmo aminoácido em bactérias, plantas e humanos é o fundamento biológico que permite a criação de **organismos transgênicos**.
                • **CRISPR-Cas9:** Ferramenta bacteriana de defesa adaptada para edição cirúrgica e sítio-específica de sequências de DNA.
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem("Proporção Monoíbrida", "Aa x Aa -> 3 dominantes : 1 recessivo (75% / 25%)", "Primeira Lei de Mendel"),
                FormulaOrSyntaxItem("Sistema ABO", "IA e IB (codominantes) > i (recessivo)", "Polialelia e determinação de grupos sanguíneos"),
                FormulaOrSyntaxItem("Dogma Central", "DNA -> (Transcrição) -> RNAm -> (Tradução) -> Proteína", "Expressão gênica celular")
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Identificação de Casal Heterozigoto em Heredograma",
                    codeOrFormula = "Pais fenotipicamente iguais gerando filho fenotipicamente diferente.",
                    explanation = "Se pais sem a anomalia têm um filho afetado, a anomalia é recessiva e ambos os pais são obrigatoriamente heterozigotos (portadores Aa)."
                )
            ),
            goldTip = "No ENEM: Se o exercício perguntar como uma bactéria consegue produzir insulina humana idêntica, a resposta é a UNIVERSALIDADE DO CÓDIGO GENÉTICO!"
        )
    }

    private fun buildGeneticsExercises(lesson: EnemTrailLesson): List<MathExercise> {
        val q1 = MathExercise(
            id = "${lesson.id}_q1",
            title = "Biotecnologia e Produção de Proteínas Recombinantes",
            problemStatement = "A técnica do DNA recombinante permitiu a inserção do gene humano que codifica a insulina no plasmídeo da bactéria Escherichia coli. As bactérias transformadas passaram a transcrever e traduzir com perfeição a proteína da insulina humana terapêutica. Essa síntese correta em um organismo procarionte é viabilizada biologicamente porque:",
            options = listOf(
                "o código genético é universal, com os mesmos códons especificando os mesmos aminoácidos em diferentes reinos.",
                "as bactérias possuem o mesmo número de cromossomos lineares que a espécie humana.",
                "a maquinaria de tradução bacteriana substitui o RNA transportador humano por ribozimas virais.",
                "o processo de splicing alternativo bacteriano remove os éxons humanos desnecessários.",
                "a insulina é um lipídio estrutural que não depende de enzimas celulares para síntese."
            ),
            correctIndex = 0,
            hint = "Lembre-se da característica do código genético que permite a organismos distintos lerem as mesmas trincas de bases.",
            stepByStepSolution = BiologyAlternativeAnalyzer.generateFormattedSolutionString(
                statement = "A técnica do DNA recombinante permitiu a inserção do gene humano que codifica a insulina no plasmídeo da bactéria Escherichia coli. As bactérias transformadas passaram a transcrever e traduzir com perfeição a proteína da insulina humana terapêutica.",
                options = listOf(
                    "o código genético é universal, com os mesmos códons especificando os mesmos aminoácidos em diferentes reinos.",
                    "as bactérias possuem o mesmo número de cromossomos lineares que a espécie humana.",
                    "a maquinaria de tradução bacteriana substitui o RNA transportador humano por ribozimas virais.",
                    "o processo de splicing alternativo bacteriano remove os éxons humanos desnecessários.",
                    "a insulina é um lipídio estrutural que não depende de enzimas celulares para síntese."
                ),
                correctIndex = 0,
                subtopic = "Biotecnologia e DNA Recombinante"
            )
        )

        val q2 = MathExercise(
            id = "${lesson.id}_q2",
            title = "Grupos Sanguíneos e Transfusões no ENEM",
            problemStatement = "Um paciente hospitalizado necessita de transfusão sanguínea de emergência. A tipagem rápida constatou que suas hemácias contêm o aglutinogênio A e suas hemácias não aglutinam na presença de anticorpo anti-Rh, revelando que ele é do grupo A Rh⁻. Caso não haja sangue idêntico no banco, ele poderá receber hemácias de um doador do grupo:",
            options = listOf(
                "AB Rh⁻, pois não possui aglutininas no plasma.",
                "O Rh⁻, pois suas hemácias não expressam aglutinogênios A, B ou fator Rh.",
                "A Rh⁺, já que o fator Rh não induz resposta imune em primeira transfusão.",
                "B Rh⁻, pois a aglutinina anti-B neutraliza os anticorpos do receptor.",
                "O Rh⁺, pois o grupo O neutraliza o fator Rh do receptor."
            ),
            correctIndex = 1,
            hint = "Pessoa Rh negativo não pode receber Rh positivo, e pessoa do grupo A não pode receber B.",
            stepByStepSolution = BiologyAlternativeAnalyzer.generateFormattedSolutionString(
                statement = "Um paciente hospitalizado com sangue tipo A Rh⁻ necessita de transfusão emergencial de concentrado de hemácias. Caso não haja sangue idêntico no banco, ele poderá receber hemácias de um doador do grupo:",
                options = listOf(
                    "AB Rh⁻, pois não possui aglutininas no plasma.",
                    "O Rh⁻, pois suas hemácias não expressam aglutinogênios A, B ou fator Rh.",
                    "A Rh⁺, já que o fator Rh não induz resposta imune em primeira transfusão.",
                    "B Rh⁻, pois a aglutinina anti-B neutraliza os anticorpos do receptor.",
                    "O Rh⁺, pois o grupo O neutraliza o fator Rh do receptor."
                ),
                correctIndex = 1,
                subtopic = "Sistema ABO e Fator Rh"
            )
        )

        return listOf(q1, q2)
    }

    // ========================================================
    // MÓDULO 4: CITOLOGIA E BIOQUÍMICA CELULAR
    // ========================================================
    private fun buildCytologyBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return CalculusDidacticBundle(
            theoryMarkdown = """
                # Citologia e Bioquímica Celular: ${lesson.title}
                
                A Citologia investiga a estrutura morfofisiológica da célula, os processos de conversão energética e o metabolismo celular.
                
                ### Subtópico: ${lesson.subtopic}
                
                ### 1. Membrana Plasmática e Transportes
                A membrana plasmática tem modelo de **Mosaico Fluido** (bicamada fosfolipídica anfipática com proteínas inseridas).
                • **Transporte Passivo (sem gasto de ATP):** Difusão simples (gases como O₂ e CO₂), difusão facilitada (glicose por permeases) e osmose (água a favor do gradiente através de aquaporinas).
                • **Transporte Ativo (com consumo de ATP):** Bomba de Na⁺/K⁺ ATPase, transportando íons contra o gradiente eletroquímico para manter o potencial de repouso celular.
                
                ### 2. Bioenergética Celular
                • **Respiração Celular Aeróbica:** C₆H₁₂O₆ + 6O₂ -> 6CO₂ + 6H₂O + ~30-32 ATP.
                  - Glicólise (citosol, anaeróbica)
                  - Ciclo de Krebs (matriz mitocondrial)
                  - Fosforilação Oxidativa (cristas mitocondriais, onde o O₂ é o aceptor final de elétrons).
                • **Fermentação:** Processo anaeróbico de baixo rendimento (apenas 2 ATPs gerados na glicólise).
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem("Equação da Respiração", "C₆H₁₂O₆ + 6 O₂ -> 6 CO₂ + 6 H₂O + ~30 ATP", "Catabolismo oxidativo da glicose"),
                FormulaOrSyntaxItem("Osmose Celular", "Água se move da solução hipotônica para a hipertônica", "Equilíbrio de solvente através de membrana semipermeável"),
                FormulaOrSyntaxItem("Bomba de Sódio e Potássio", "Consome 1 ATP para ejetar 3 Na⁺ e captar 2 K⁺", "Manutenção da polaridade da membrana plasmática")
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Comportamento Osmótico em Hemácias vs Células Vegetais",
                    codeOrFormula = "Lise celular vs Turgência",
                    explanation = "Em meio hipotônico, a hemácia ganha água até sofrer hemólise (rompimento), enquanto a célula vegetal fica túrgida sem se romper graças à rigidez de sua parede celular celulósica."
                )
            ),
            goldTip = "No ENEM: Lembre-se de que o O₂ respirado NÃO se transforma em CO₂; o O₂ se une a prótons e elétrons para formar ÁGUA (H₂O) no final da cadeia respiratória!"
        )
    }

    private fun buildCytologyExercises(lesson: EnemTrailLesson): List<MathExercise> {
        val q1 = MathExercise(
            id = "${lesson.id}_q1",
            title = "Bioenergética e Cadeia Respiratória",
            problemStatement = "O monóxido de carbono (CO) e o cianeto são venenos letais porque se ligam irreversivelmente ao complexo IV (citocromo c oxidase) da cadeia respiratória mitocondrial, impedindo a transferência de elétrons para o aceptor final. A consequência bioquímica direta desse bloqueio para a célula humana é:",
            options = listOf(
                "cessação da síntese de ATP por fosforilação oxidativa e acúmulo de NADH e FADH₂.",
                "aumento drástico no consumo de oxigênio pelas cristas mitocondriais.",
                "ativação descontrolada do Ciclo de Calvin no citoplasma celular.",
                "hiperpolarização da membrana celular devido à conversão do cianeto em glicose.",
                "substituição instantânea da respiração aeróbica por fotólise da água."
            ),
            correctIndex = 0,
            hint = "Se a cadeia de elétrons para, a ATP sintase não tem gradiente de prótons para gerar energia química.",
            stepByStepSolution = BiologyAlternativeAnalyzer.generateFormattedSolutionString(
                statement = "O monóxido de carbono (CO) e o cianeto são venenos letais porque se ligam irreversivelmente ao complexo IV da cadeia respiratória mitocondrial, impedindo a transferência de elétrons para o aceptor final.",
                options = listOf(
                    "cessação da síntese de ATP por fosforilação oxidativa e acúmulo de NADH e FADH₂.",
                    "aumento drástico no consumo de oxigênio pelas cristas mitocondriais.",
                    "ativação descontrolada do Ciclo de Calvin no citoplasma celular.",
                    "hiperpolarização da membrana celular devido à conversão do cianeto em glicose.",
                    "substituição instantânea da respiração aeróbica por fotólise da água."
                ),
                correctIndex = 0,
                subtopic = "Respiração Celular e Cadeia Transportadora de Elétrons"
            )
        )

        return listOf(q1)
    }

    // ========================================================
    // MÓDULO 5: EVOLUÇÃO E HOMINIZAÇÃO
    // ========================================================
    private fun buildEvolutionBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return CalculusDidacticBundle(
            theoryMarkdown = """
                # Evolução Biológica e Hominização: ${lesson.title}
                
                A Evolução é o eixo unificador de toda a Biologia moderna. No ENEM, as questões examinam a seleção natural, a origem da variabilidade genética e a anatomia comparada.
                
                ### Subtópico: ${lesson.subtopic}
                
                ### 1. Lamarckismo vs. Darwinismo vs. Neodarwinismo
                • **Lamarck:** Propôs a Lei do Uso e Desuso e a transmissão dos caracteres adquiridos. Refutada: alterações somáticas não alteram o genoma das células germinativas.
                • **Darwin:** Propôs que as espécies compartilham ancestrais comuns e que a **Seleção Natural** atua favorecendo indivíduos que já possuem variações pré-adaptadas ao ambiente.
                • **Neodarwinismo (Teoria Sintética):** Complementou Darwin identificando que as **mutações aleatórias** e a **recombinação meiótica** geram a variabilidade, enquanto a seleção natural direciona as frequências alélicas.
                
                ### 2. Evidências Evolutivas e Anatomia Comparada
                • **Órgãos Homólogos:** Mesma origem embrionária, podendo ter funções distintas (Divergência Adaptativa). Ex: nadadeira de baleia e braço humano.
                • **Órgãos Análogos:** Origens embrionárias diferentes mas mesma função decorrente de pressões ambientais semelhantes (Convergência Adaptativa). Ex: asa de inseto e asa de ave.
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem("Seleção Natural", "Variabilidade prévia + Pressão ambiental -> Reprodução diferencial", "Mecanismo orientador da evolução"),
                FormulaOrSyntaxItem("Homologia", "Mesma origem embrionária (ancestral comum) -> Funções diversas", "Divergência adaptativa"),
                FormulaOrSyntaxItem("Analogia", "Origens diferentes -> Mesma função por pressão ecológica semelhante", "Convergência adaptativa")
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Resistência Bacteriana a Antibióticos no ENEM",
                    codeOrFormula = "O antibiótico não cria a resistência; ele seleciona os indivíduos mutantes pré-existentes.",
                    explanation = "O uso indiscriminado de antibióticos atua como agente seletivo, eliminando bactérias sensíveis e deixando apenas as mutantes previamente resistentes, que proliferam livremente."
                )
            ),
            goldTip = "No ENEM: NUNCA marque alternativas que digam que o animal 'mudou de cor PARA se camuflar' ou que as bactérias 'se tornaram resistentes PARA sobreviver'. A evolução não tem intenção nem vontade!"
        )
    }

    private fun buildEvolutionExercises(lesson: EnemTrailLesson): List<MathExercise> {
        val q1 = MathExercise(
            id = "${lesson.id}_q1",
            title = "Mecanismo Evolutivo e Resistência Bacteriana",
            problemStatement = "O uso inadequado e repetido de antibióticos pela população humana tem provocado o aumento alarmante de linhagens de superbactérias resistentes em ambientes hospitalares. À luz da Teoria Sintética da Evolução (Neodarwinismo), o surgimento dessas linhagens resistentes é explicado porque o antibiótico:",
            options = listOf(
                "induz mutações adaptativas específicas no DNA bacteriano para protegê-las do fármaco.",
                "atua como agente de seleção natural, eliminando as sensíveis e permitindo a proliferação das previamente resistentes.",
                "estimula o sistema de transcrição bacteriano a produzir anticorpos que digerem a molécula do medicamento.",
                "provoca a fusão de plasmídeos patogênicos transformando bactérias comensais em vírus resistentes.",
                "força as bactérias a utilizarem mais a parede celular, transmitindo o caráter adquirido aos descendentes."
            ),
            correctIndex = 1,
            hint = "O ambiente seleciona, não cria a mutação de propósito.",
            stepByStepSolution = BiologyAlternativeAnalyzer.generateFormattedSolutionString(
                statement = "O uso inadequado e repetido de antibióticos tem provocado o aumento de superbactérias resistentes em ambientes hospitalares. À luz do Neodarwinismo, esse fenômeno é explicado porque o fármaco:",
                options = listOf(
                    "induz mutações adaptativas específicas no DNA bacteriano para protegê-las do fármaco.",
                    "atua como agente de seleção natural, eliminando as sensíveis e permitindo a proliferação das previamente resistentes.",
                    "estimula o sistema de transcrição bacteriano a produzir anticorpos que digerem a molécula do medicamento.",
                    "provoca a fusão de plasmídeos patogênicos transformando bactérias comensais em vírus resistentes.",
                    "força as bactérias a utilizarem mais a parede celular, transmitindo o caráter adquirido aos descendentes."
                ),
                correctIndex = 1,
                subtopic = "Neodarwinismo e Seleção Natural"
            )
        )

        return listOf(q1)
    }

    private fun buildGeneralBiologyBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return CalculusDidacticBundle(
            theoryMarkdown = """
                # Biologia ENEM: ${lesson.title}
                
                ### Subtópico: ${lesson.subtopic}
                
                No Exame Nacional do Ensino Médio, esta aula integra as competências de Ciências da Natureza, exigindo a compreensão das bases biológicas, seus desdobramentos ecológicos e tecnológicos para a sociedade contemporânea.
                
                Explore os conceitos, analise os exemplos resolvidos e pratique os exercícios com resolução detalhada passo a passo de cada alternativa.
            """.trimIndent(),
            formulas = listOf(
                FormulaOrSyntaxItem("Princípio Biológico Central", "Estrutura <-> Função <-> Adaptação", "Eixo integrador das Ciências da Natureza no ENEM")
            ),
            solvedExamples = listOf(
                LessonExample(
                    title = "Aplicação Conceitual ENEM",
                    codeOrFormula = "Identificação de Variáveis e Leis Naturais",
                    explanation = "Analise o gráfico ou enunciado, identifique os fatores limitantes e aplique a teoria sem generalizações precipitadas."
                )
            ),
            goldTip = "Leia com atenção os comandos do ENEM e elimine distratores que utilizam termos absolutos como 'sempre', 'nunca' ou 'único'."
        )
    }

    private fun buildDefaultBiologyExercises(lesson: EnemTrailLesson): List<MathExercise> {
        val q1 = MathExercise(
            id = "${lesson.id}_q1",
            title = "Fixação ENEM: ${lesson.subtopic}",
            problemStatement = "Considerando os princípios biológicos associados a ${lesson.subtopic}, a afirmativa correta sobre a organização e funcionamento dos sistemas vivos correspondentes é:",
            options = listOf(
                "os mecanismos fisiológicos e ecológicos operam de forma isolada, sem interdependência de fatores abióticos.",
                "as interações moleculares e celulares são ajustadas por processos evolutivos que garantem a homeostase e adaptação ao ambiente.",
                "as células procariontes e eucariontes realizam as mesmas etapas metabólicas exatamente nos mesmos compartimentos membranosos.",
                "as mutações genéticas ocorrem sempre com a finalidade preconcebida de melhorar a aptidão física do organismo.",
                "o fluxo de energia em qualquer sistema ecológico é cíclico e infinito sem nenhuma dissipação térmica."
            ),
            correctIndex = 1,
            hint = "A vida se apoia na integração entre níveis moleculares, celulares e adaptação contínua ao meio.",
            stepByStepSolution = BiologyAlternativeAnalyzer.generateFormattedSolutionString(
                statement = "Considerando os princípios biológicos associados a ${lesson.subtopic}, a afirmativa correta sobre a organização e funcionamento dos sistemas vivos correspondentes é:",
                options = listOf(
                    "os mecanismos fisiológicos e ecológicos operam de forma isolada, sem interdependência de fatores abióticos.",
                    "as interações moleculares e celulares são ajustadas por processos evolutivos que garantem a homeostase e adaptação ao ambiente.",
                    "as células procariontes e eucariontes realizam as mesmas etapas metabólicas exatamente nos mesmos compartimentos membranosos.",
                    "as mutações genéticas ocorrem sempre com a finalidade preconcebida de melhorar a aptidão física do organismo.",
                    "o fluxo de energia em qualquer sistema ecológico é cíclico e infinito sem nenhuma dissipação térmica."
                ),
                correctIndex = 1,
                subtopic = lesson.subtopic
            )
        )
        return listOf(q1)
    }
}
