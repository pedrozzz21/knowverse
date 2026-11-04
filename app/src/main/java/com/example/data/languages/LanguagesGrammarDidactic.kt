package com.example.data.languages

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático e Exercícios Comentados
 * Módulo 4: Gramática Aplicada ao Texto
 * - ling_gram_01: Classes de Palavras em Contexto (preposições, conjunções, pronomes)
 * - ling_gram_02: Sintaxe e Sentido (Orações coordenadas e subordinadas)
 * - ling_gram_03: Concordância e Regência (Verbal e Nominal na norma-padrão)
 * - ling_gram_04: Pontuação (Vírgulas, travessões e reticências como recursos de sentido)
 */
object LanguagesGrammarDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "ling_gram_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Classes de Palavras em Contexto Semântico

                    O ENEM **não cobra decoreba** ou classificação morfológica mecânica. A prova investiga como a escolha de uma classe gramatical constrói sentidos específicos no texto.

                    ### 1. Preposições e Seus Valores Semânticos
                    A preposição não é apenas um conectivo vazio; ela expressa relações conceituais ricas:
                    • **Posse:** 'O carro *de* Maria'.
                    • **Matéria / Origem:** 'Mesa *de* madeira'; 'Veio *de* Minas'.
                    • **Causa:** 'Tremia *de* frio'; 'Morreu *de* desgosto'.
                    • **Instrumento:** 'Feriu-se *com* a faca'; 'Escreveu *a* lápis'.
                    • **Modo:** 'Saiu *às* pressas'; 'Vive *com* dignidade'.
                    • **Finalidade:** 'Estudou *para* vencer'.
                    • **Companhia:** 'Passeou *com* os amigos'.

                    ### 2. Conjunções e Conectores Argumentativos
                    São os tijolos da progressão textual e coesão sequencial:
                    • **Conjunções Coordenativas:** Aditivas (*e, nem*), Adversativas (*mas, porém, todavia*), Alternativas (*ou... ou*), Conclusivas (*portanto, logo*), Explicativas (*pois, que*).
                    • **Conjunções Subordinativas:** Causais (*porque, visto que*), Concessivas (*embora, ainda que*), Condicionais (*se, caso*), Consecutivas (*tanto que*), Comparativas (*como, tal qual*), Temporais (*quando, assim que*), Finais (*a fim de que, para que*), Proporcionais (*à proporção que*), Conformativas (*conforme, segundo*).

                    ### 3. Pronomes e Referenciação Discursiva
                    • **Pronomes Demonstrativos no Texto:**
                      - **Este / Esta / Isto:** Refere-se ao que vai ser dito a seguir (**catáfora**) ou ao presente imediato.
                      - **Esse / Essa / Isso:** Retoma o que acabou de ser dito (**anáfora**) ou ao passado/futuro próximo.
                      - **Aquele / Aquela / Aquilo:** Retoma o elemento mais distante temporal ou espacialmente.
                    • **Pronomes Relativos (*que, cujo, onde*):**
                      - '*Cujo*' indica posse entre dois substantivos e nunca aceita artigo posterior (*cujo filho*, jamais *cujo o filho*).
                      - '*Onde*' só deve ser utilizado para indicar **lugar físico palpável** ('a cidade onde moro', e NÃO 'a reunião onde debatemos').
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Uso de 'Onde'", "Somente para local físico ('a praça onde nos vimos')", "Para situações abstratas, use 'em que' ou 'no qual'"),
                    FormulaOrSyntaxItem("Uso de 'Cujo'", "Posse entre substantivos sem artigo posterior", "Ex: O autor cuja obra foi premiada"),
                    FormulaOrSyntaxItem("Esse vs Este", "Esse = Retoma termo já dito | Este = Aponta para o termo que virá", "Coesão anafórica e catafórica")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Correção de Pronome Relativo no ENEM",
                        codeOrFormula = "'A época onde vivemos' (Incorreto) -> 'A época em que vivemos' (Correto)",
                        explanation = "Como 'época' é um recorte temporal e não um espaço geográfico, a norma-padrão exige 'em que', 'na qual' ou 'durante a qual'."
                    )
                ),
                goldTip = "Em redação e na prova de linguagens: nunca use 'onde' para tempo, lei, situação ou debate; use 'em que' ou 'no qual'!"
            )

            "ling_gram_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Sintaxe e Sentido: Coordenação e Subordinação

                    A organização sintática das orações reflete a intenção persuasiva e a hierarquia de ideias pretendida pelo autor.

                    ### 1. Coordenação (Independência Sintática)
                    As orações coordenadas têm sentido completo e são justapostas ou ligadas por conjunções coordenativas:
                    • 'O candidato estudou muito [oração 1 independentemente estruturada], **mas** não obteve a vaga [oração 2 coordenada adversativa].'
                    • A coordenação confere ritmo dinâmico, paratático e direto ao texto.

                    ### 2. Subordinação (Hierarquia e Dependência Sintática)
                    A oração subordinada desempenha uma função sintática (sujeito, objeto, adjunto) em relação à oração principal:
                    • **Substantivas:** Exercem papel de núcleo nominal ('É necessário **que você vença**' -> 'É necessária **sua vitória**').
                    • **Adjetivas:** Funcionam como adjunto adnominal, introduzidas por pronome relativo:
                      - **Explicativas (Entre vírgulas):** Referem-se à totalidade do conjunto ('Os seres humanos, **que são mortais**, buscam o sentido da vida').
                      - **Restritivas (Sem vírgulas):** Delimitam uma parcela específica do conjunto ('Os candidatos **que estudaram com seriedade** foram aprovados' -> apenas essa fração foi aprovada).
                    • **Adverbiais:** Expressam as circunstâncias lógicas de causa, consequência, tempo, condição, concessão e finalidade da ação principal.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Oração Adjetiva Explicativa", "Com vírgulas: aplica-se a todos os membros do grupo", "Ex: A água, que é essencial à vida, deve ser preservada"),
                    FormulaOrSyntaxItem("Oração Adjetiva Restritiva", "Sem vírgulas: restringe a apenas uma parte do grupo", "Ex: Os alunos que leram o edital não tiveram dúvidas"),
                    FormulaOrSyntaxItem("Causa x Consequência", "Causa vem antes cronologicamente; Consequência é o desdobramento", "Raciocínio lógico fundamental")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Diferença Semântica Fundamental da Vírgula nas Adjetivas",
                        codeOrFormula = "'Os motoristas que bebem causam acidentes' vs 'Os motoristas, que bebem, causam acidentes'",
                        explanation = "Sem vírgula (restritiva): apenas aqueles que ingerem álcool causam acidentes. Com vírgula (explicativa): afirma que todos os motoristas bebem, gerando uma generalização falsa."
                    )
                ),
                goldTip = "O ENEM adora cobrar o sentido da vírgula nas orações adjetivas: tirar a vírgula transforma uma explicação geral em uma restrição específica!"
            )

            "ling_gram_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Concordância e Regência na Norma-Padrão

                    A harmonia entre os termos da oração garante precisão semântica e atendimento aos critérios da Competência 1 da Redação e das questões de Linguagens.

                    ### 1. Concordância Verbal Crítica no ENEM
                    • **Verbo HAVER no sentido de existir ou tempo decorrido:** É impessoal, **não tem sujeito** e deve ficar rigorosamente na **3ª pessoa do singular** ('Havia muitas pessoas na praça', e NUNCA 'Haviam muitas pessoas').
                    • **Verbo FAZER indicando tempo decorrido ou clima:** Também impessoal ('Faz dez anos que não o vejo', e NUNCA 'Fazem dez anos').
                    • **Partícula SE (Apassivadora vs Indeterminadora):**
                      - **Pronome Apassivador (VTD + SE + Sujeito Paciente):** O verbo concorda com o sujeito ('Vende-se uma casa' / 'Vendem-se casas').
                      - **Índice de Indeterminação do Sujeito (VTI/VI + SE + Preposição):** Verbo obrigatoriamente no singular ('Precisa-se de operários' / 'Trata-se de problemas graves').
                    • **Expressões Partitivas ('A maioria de', 'Grande parte de'):** Concordância facultativa com o coletivo ou com o especificador ('A maioria dos alunos *passou*' ou '*passaram*').

                    ### 2. Regência Verbal e Mudança de Sentido
                    Determinados verbos alteram radicalmente de significado conforme a preposição regente:
                    • **Aspirar:**
                      - Sem preposição (VTD = sorver o ar): 'Aspirou o perfume da flor'.
                      - Com preposição *a* (VTI = almejar, desejar): 'Aspira **ao** cargo de médico'.
                    • **Assistir:**
                      - Com preposição *a* (VTI = presenciar, ver): 'Assistiu **ao** filme'.
                      - Sem preposição (VTD = dar assistência): 'O médico assistiu o paciente'.
                    • **Visar:**
                      - Com preposição *a* (VTI = objetivar): 'Visa **à** aprovação'.
                      - Sem preposição (VTD = mirar, assinar): 'Visou o alvo'; 'Visou o cheque'.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Haver e Fazer (Impessoais)", "Ficam sempre no singular ('Havia dúvidas', 'Faz dois meses')", "Sem sujeito plural"),
                    FormulaOrSyntaxItem("Vender-se x Precisar-se", "Vendem-se terrenos (Sujeito Plural) | Precisa-se de médicos (VTI, Singular)", "Voz passiva sintética vs Sujeito indeterminado"),
                    FormulaOrSyntaxItem("Assistir e Aspirar", "Assistir ao jogo (ver) | Aspirar ao sucesso (almejar)", "Exigem preposição 'a'")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Crase com o Verbo Visar",
                        codeOrFormula = "'O projeto visa à melhoria das escolas'",
                        explanation = "O verbo visar (no sentido de almejar) exige a preposição 'a'. Como 'melhoria' é substantivo feminino com artigo 'a', ocorre a fusão obrigatória (crase: à)."
                    )
                ),
                goldTip = "No ENEM: Se o verbo 'haver' significa existir, ele NUNCA vai para o plural! Guarde: 'Havia problemas', e jamais 'Haviam problemas'!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Pontuação como Recurso Expressivo e de Sentido

                    A pontuação não serve para indicar quando o leitor deve 'respirar'; ela é um instrumento sintático e discursivo que delimita unidades de sentido e cria ênfases expressivas.

                    ### 1. O Uso da Vírgula (Regras de Ouro)
                    • **PROIBIDO SEPARAR:** Nunca se separa por vírgula o Sujeito do Verbo, nem o Verbo de seus Objetos diretos/indiretos ('O professor explicou a matéria', e NUNCA 'O professor, explicou a matéria').
                    • **USO OBRIGATÓRIO:**
                      - **Vocativo (Chamamento):** Sempre isolado por vírgula ('Venha aqui, Pedro!'; 'Amigos, escutem com atenção!').
                      - **Aposto Explicativo:** 'Machado de Assis, **grande escritor brasileiro**, nasceu no Rio.'
                      - **Adjunto Adverbial Deslocado:** Quando a expressão de tempo, lugar ou modo vem no início ou meio da frase ('Na semana passada, todos compareceram').
                      - **Termos em Enumeração:** 'Comprou cadernos, lápis, borrachas e canetas.'
                      - **Conjunções Adversativas e Conclusivas Deslocadas:** 'O projeto era bom; não obteve, **contudo**, os recursos necessários.'

                    ### 2. Travessões, Parênteses e Dois-Pontos
                    • **Travessão Duplo:** Destaca e valoriza uma informação intercalada com mais ênfase visual e expressiva do que parênteses ou vírgulas.
                    • **Dois-Pontos:** Introduzem esclarecimento, enumeração, citação ou síntese explicativa da ideia anterior.
                    • **Reticências:** Sugerem continuidade de pensamento, hesitação, ironia ou silenciamento intencional da personagem.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Proibição Capital", "Não separar Sujeito e Verbo por vírgula simples", "Estrutura sintática inegociável"),
                    FormulaOrSyntaxItem("Vocativo", "Sempre isolado por vírgula ('Atenção, eleitores!')", "Interpelação direta"),
                    FormulaOrSyntaxItem("Travessão Intercalado", "Destaca e confere relevância expressiva à reflexão", "Alternativa estilística nobre à vírgula")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Distinção entre Aposto e Vocativo",
                        codeOrFormula = "'Pelé, o rei do futebol, venceu três Copas' (Aposto) vs 'Pelé, jogue com raça!' (Vocativo)",
                        explanation = "O aposto explica quem é o sujeito anterior; o vocativo chama o interlocutor diretamente para receber uma mensagem."
                    )
                ),
                goldTip = "Em provas do ENEM, travessões duplos intercalados quase sempre cumprem a função de inserir um comentário opinativo ou explicativo do autor!"
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "ling_gram_01" -> listOf(
                MathExercise(
                    id = "ling_gram_01_q1",
                    title = "Valor Semântico de Preposições",
                    problemStatement = "Analise o fragmento: 'O jovem cientista tremia de entusiasmo ao receber o prêmio internacional conquistado com muito esforço individual'. As preposições destacadas 'de' e 'com' expressam, no contexto, respectivamente as relações semânticas de:",
                    options = listOf(
                        "finalidade e tempo.",
                        "causa e instrumento/modo.",
                        "origem geográfica e companhia.",
                        "oposição e concessão.",
                        "posse e matéria."
                    ),
                    correctIndex = 1,
                    hint = "O que causou o tremor? O entusiasmo (causa). De que forma ou por qual meio ele conquistou o prêmio? Com esforço (modo/meio).",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. 'Tremia de entusiasmo': a preposição 'de' introduz o motivo gerador ou causa do tremor físico involuntário.
                        2. 'Conquistado com muito esforço': a preposição 'com' indica o modo ou meio pelo qual o resultado foi alcançado.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_gram_02" -> listOf(
                MathExercise(
                    id = "ling_gram_02_q1",
                    title = "Efeito Semântico da Oração Adjetiva",
                    problemStatement = "Considere os dois enunciados abaixo extraídos de um relatório de trânsito: \nI. 'Os motoristas de aplicativo que respeitam o limite de velocidade diminuem o risco de colisões graves.'\nII. 'Os motoristas de aplicativo, que respeitam o limite de velocidade, diminuem o risco de colisões graves.'\nA análise comparativa das orações adjetivas permite concluir que:",
                    options = listOf(
                        "os dois enunciados expressam rigorosamente o mesmo sentido sem qualquer alteração interpretativa.",
                        "em I, a oração restringe o benefício apenas à parcela dos motoristas que cumprem a norma; em II, a oração afirma categoricamente que todos os motoristas de aplicativo respeitam a velocidade.",
                        "o enunciado I está gramaticalmente incorreto por ausência injustificada de pontuação.",
                        "o enunciado II indica que apenas os motoristas infratores evitam acidentes.",
                        "o uso das vírgulas em II transforma a oração adjetiva em uma oração adverbial causal."
                    ),
                    correctIndex = 1,
                    hint = "A presença de vírgulas torna a oração adjetiva explicativa (abrangendo a totalidade do grupo).",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A oração adjetiva restritiva (sem vírgula) delimita uma parte do conjunto dos motoristas (somente aqueles que respeitam a velocidade).
                        2. A oração adjetiva explicativa (entre vírgulas) atribui a característica a todo o grupo de motoristas de aplicativo como uma qualidade geral de todos eles.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_gram_03" -> listOf(
                MathExercise(
                    id = "ling_gram_03_q1",
                    title = "Concordância Verbal com Haver e Existir",
                    problemStatement = "De acordo com a norma-padrão da língua portuguesa escrita, a opção em que a concordância verbal está inteiramente correta é:",
                    options = listOf(
                        "Haviam muitas pessoas na manifestação pública em frente ao ministério.",
                        "Havia muitas pessoas na manifestação pública em frente ao ministério.",
                        "Fazem cinco anos que os cientistas pesquisam essa vacina inovadora.",
                        "Precisa-se de novos técnicos agrícolas para a colheita no interior.",
                        "Vende-se apartamentos mobiliados com vista para a orla marítima."
                    ),
                    correctIndex = 1,
                    hint = "O verbo haver no sentido de existir é impessoal e não admite forma no plural.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. O verbo 'haver' com significado de existir é impessoal e deve ficar na 3ª pessoa do singular: 'Havia muitas pessoas'.
                        2. 'Fazem cinco anos' está errado (fazer de tempo é impessoal: 'Faz cinco anos').
                        3. 'Vende-se apartamentos' está errado (apassivadora: 'Vendem-se apartamentos').
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            else -> listOf(
                MathExercise(
                    id = "ling_gram_04_q1",
                    title = "Emprego da Pontuação e Expressividade",
                    problemStatement = "No período: 'A juventude brasileira — consciente dos desafios climáticos globais — tem liderado manifestações em defesa das florestas', o uso dos travessões duplos cumpre a função de:",
                    options = listOf(
                        "separar obrigatoriamente o sujeito do predicado por falta de conectivo.",
                        "isolar um comentário explicativo que destaca a postura crítica dos jovens perante o meio ambiente.",
                        "introduzir a fala de uma personagem em um diálogo teatral.",
                        "marcar a omissão acidental de dados estatísticos do Censo Florestal.",
                        "denotar dúvida ou indecisão do narrador quanto à veracidade do fato."
                    ),
                    correctIndex = 1,
                    hint = "O elemento entre travessões caracteriza e qualifica o sujeito 'a juventude brasileira'.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. Os travessões duplos intercalados funcionam com efeito semelhante aos parênteses ou vírgulas explicativas, conferindo ênfase estilística à caracterização da consciência ambiental da juventude.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )
        }
    }
}
