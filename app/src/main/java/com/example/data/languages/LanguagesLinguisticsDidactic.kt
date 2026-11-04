package com.example.data.languages

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Conteúdo Didático e Exercícios Comentados
 * Módulo 2: Linguística e Comunicação
 * - ling_com_01: Funções da Linguagem
 * - ling_com_02: Variação Linguística
 * - ling_com_03: Impacto das Tecnologias na Comunicação
 * - ling_com_04: Coesão e Coerência
 */
object LanguagesLinguisticsDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            "ling_com_01" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # As 6 Funções da Linguagem (Roman Jakobson)

                    No modelo de comunicação de Jakobson, todo ato comunicativo envolve seis elementos: **Emissor**, **Receptor**, **Mensagem**, **Código**, **Canal** e **Referente (Contexto)**. A predominância de um desses elementos define a Função da Linguagem.

                    ### 1. Quadro Sistemático das Funções
                    • **Função Referencial (Denotativa / Informativa):** Foco no **Referente/Contexto**. Transmissão objetiva e impessoal de informações da realidade (jornais, livros didáticos, artigos científicos). Terceira pessoa gramatical.
                    • **Função Emotiva (Expressiva):** Foco no **Emissor**. Manifestação de sentimentos, opiniões, emoções e impressões pessoais. Primeira pessoa ('eu'), pontos de exclamação e adjetivos valorativos.
                    • **Função Conativa (Apelativa):** Foco no **Receptor**. Busca influenciar, persuadir, convencer ou ordenar o leitor. Verbos no imperativo, pronomes de segunda pessoa ('você', 'tu') e vocativos (propaganda, sermões, discursos políticos).
                    • **Função Metalinguística:** Foco no **Código**. A linguagem explica a própria linguagem (o dicionário definindo palavras, o poema falando sobre o ato de escrever poesia, um filme sobre cinema).
                    • **Função Fática:** Foco no **Canal**. Manutenção, teste ou encerramento do contato comunicativo ('Alô?', 'Está me ouvindo?', 'Entende?', cumprimentos e saudações).
                    • **Função Poética:** Foco na **Mensagem**. Valorização estética da forma do texto, exploração da sonoridade, rimas, trocadilhos, ritmo e metáforas (literatura, slogans publicitários, letras de música).

                    ### 2. A Pegadinha do ENEM
                    Raramente um texto possui apenas uma função isolada. O ENEM sempre pergunta pela função **predominante**, isto é, aquela que orienta o propósito comunicativo principal.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Referencial (Contexto)", "Objetividade, 3ª pessoa, dados factuais", "Artigos científicos, notícias"),
                    FormulaOrSyntaxItem("Conativa / Apelativa (Receptor)", "Imperativo, 'você', convencimento direto", "Propagandas, campanhas sociais"),
                    FormulaOrSyntaxItem("Metalinguística (Código)", "O código explicando o código", "Gramáticas, dicionários, poemas sobre fazer versos"),
                    FormulaOrSyntaxItem("Emotiva (Emissor)", "1ª pessoa, subjetividade, emoção explícita", "Diários íntimos, autobiografias, cartas pessoais")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Propaganda Institucional do Ministério da Saúde",
                        codeOrFormula = "'Lave as mãos com frequência e proteja sua família'",
                        explanation = "O verbo 'lave' e o pronome 'sua' indicam foco no receptor, caracterizando a Função Conativa/Apelativa para induzir um hábito sanitário."
                    )
                ),
                goldTip = "Dicionário falando de palavras ou poema falando sobre compor poesia? Gabarito imediato: Função Metalinguística!"
            )

            "ling_com_02" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Variação Linguística e Combate ao Preconceito

                    A língua não é homogênea nem estática. Ela varia dinamicamente segundo fatores geográficos, históricos, socioculturais e situacionais.

                    ### 1. Eixos de Variação Linguística
                    • **Diatópica (Geográfica / Regional):** Diferenças de vocabulário, pronúncia e sintaxe conforme a região geográfica (sotaques e regionalismos: 'mandioca', 'aipim', 'macaxeira'; 'tu vai', 'ocê').
                    • **Diastrática (Social):** Variações entre diferentes grupos sociais, faixas etárias, profissões (jargões técnicos de médicos, advogados, marinheiros; gírias juvenis ou comunitárias).
                    • **Diacrônica (Histórica):** Transformação da língua através do tempo (ex: 'Vossa Mercê' -> 'Vosmecê' -> 'Você' -> 'Cê').
                    • **Diafásica (Estilística / Situacional):** Adequação do registro ao contexto de uso: **Registro Formal / Culto** (em entrevistas de emprego, tribunais, redação do ENEM) versus **Registro Informal / Coloquial** (entre amigos, família, mensagens instantâneas).

                    ### 2. Postura Ética e Científica do ENEM: Preconceito Linguístico
                    O ENEM se baseia na Sociolinguística moderna (Marcos Bagno e outros):
                    • **Não existe 'falar errado' na fala espontânea:** Existem variedades adequadas e inadequadas ao contexto comunicativo.
                    • **Preconceito Linguístico:** Discriminação social mascarada de julgamento gramatical, que marginaliza falantes das classes populares ou do interior do país.
                    • A escola deve ensinar a norma-padrão como ferramenta de ampliação de cidadania e acesso ao poder, sem desvalorizar as variantes maternas.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Adequação Contextual", "Não há 'certo' ou 'errado' absoluto, mas 'adequado' ou 'inadequado' à situação", "Princípio sociolinguístico fundamental"),
                    FormulaOrSyntaxItem("Diatópica (Lugar)", "Regionalismos geográficos (Norte, Sul, Nordeste, etc.)", "Identidade cultural"),
                    FormulaOrSyntaxItem("Diafásica (Registro)", "Formal (norma culta) vs Coloquial (informalidade)", "Adequação ao interlocutor e ao meio")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Patativa do Assaré e Guimarães Rosa no ENEM",
                        codeOrFormula = "Uso estético e identitário do falar sertanejo e caipira",
                        explanation = "A literatura incorpora a fala popular não como erro, mas como expressão legítima de sensibilidade, cultura e resistência popular."
                    )
                ),
                goldTip = "Se a questão do ENEM abordar pronúncias regionais ou fala popular, a alternativa correta NUNCA dirá que a variante é 'inferior' ou 'corrompida'!"
            )

            "ling_com_03" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Impacto das Tecnologias na Comunicação

                    A revolução digital e a internet transformaram os modos de produção, leitura e circulação do texto.

                    ### 1. Hipertexto e Multimodalidade
                    • **Hipertexto:** Estrutura de leitura não linear em que links levam o leitor a novos nós de informação, permitindo que cada usuário construa sua própria rota de aprendizagem.
                    • **Multimodalidade:** Integração simultânea de códigos semióticos (texto verbal escrito, áudio, infográficos, gifs, vídeos e ícones interativos).

                    ### 2. O 'Internetês', Memes e Redes Sociais
                    • O internetês não é a 'destruição' da língua, mas uma adaptação comunicativa pautada pela velocidade e economia de toques no teclado (abreviações como 'vc', 'tb', emojis complementando o tom de voz).
                    • **Memes:** Unidades culturais de humor e crítica que se propagam viralmente, exigindo rápida decodificação de repertório visual e contemporâneo compartilhado.
                    • **Bolhas de Informação e Desinformação:** Algoritmos que priorizam engajamento emocional e radicalização, desafiando a leitura crítica dos cidadãos.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Hipertexto", "Leitura em nós não lineares interligados por hiperlinks", "Autonomia do leitor e navegação interativa"),
                    FormulaOrSyntaxItem("Multimodalidade", "Linguagem verbal + Visual + Sonora integradas", "Cultura digital contemporânea")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Análise de Infográficos Digitais",
                        codeOrFormula = "Dados estatísticos + Ícones visuais + Pequenos blocos de texto",
                        explanation = "A infografia permite rápida absorção de dados complexos em telas móveis, otimizando a cognição visual."
                    )
                ),
                goldTip = "O ENEM encara o internetês e as linguagens das redes como práticas sociais legítimas em seus ambientes próprios, exigindo apenas a capacidade de alternar para a norma-padrão quando exigido!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Coesão e Coerência Textual

                    A **coesão** é a costura gramatical e lexical explícita do texto; a **coerência** é a harmonia de sentido global e a não contradição das ideias.

                    ### 1. Mecanismos de Coesão Referencial
                    • **Anáfora:** Retomada de um termo que já foi mencionado anteriormente no texto ('Machado de Assis nasceu no Rio. O escritor publicou obras-primas. Ele revolucionou as letras.').
                    • **Catáfora:** Antecipação de um termo que só será apresentado depois ('Só desejo isto: a sua aprovação.').
                    • **Elipse (Coesão por Omissão):** Apagamento de termo recuperável pelo contexto ('Os alunos estudaram muito; foram aprovados [eles]').
                    • **Substituição Lexical:** Uso de sinônimos, hiperônimos (termo geral) ou expressões nominais qualificadoras para evitar repetições cansativas.

                    ### 2. Coesão Sequencial (O Papel das Conjunções)
                    Estabelece relações lógico-discursivas fundamentais entre as orações:
                    • **Adversativa (Contraste):** Mas, porém, contudo, todavia, no entanto.
                    • **Concessiva (Quebra de expectativa sem anular o fato):** Embora, apesar de, ainda que, mesmo que.
                    • **Causal (Motivo):** Porque, já que, visto que, como (no início da frase).
                    • **Conclusiva (Desfecho lógico):** Portanto, logo, por conseguinte, destarte.
                    • **Explicativa:** Pois (anteposto ao verbo), que, porque.
                    • **Conformativa:** Conforme, segundo, consoante.
                """.trimIndent(),
                formulas = listOf(
                    FormulaOrSyntaxItem("Anáfora vs Catáfora", "Anáfora = Retoma termo anterior | Catáfora = Antecipa termo seguinte", "Continuidade referencial"),
                    FormulaOrSyntaxItem("Adversativa vs Concessiva", "Adversativa introduz ideia que vence o contraste | Concessiva introduz obstáculo superado", "Relações semânticas de oposição"),
                    FormulaOrSyntaxItem("Coerência", "Não contradição lógica, progressão temática contínua", "Sentido global do texto")
                ),
                solvedExamples = listOf(
                    LessonExample(
                        title = "Diferença entre Mas e Embora",
                        codeOrFormula = "'Estudou muito, mas não passou' vs 'Embora tenha estudado muito, não passou'",
                        explanation = "O 'mas' dá mais peso ao fracasso; o 'embora' subordina o esforço como concessão, mantendo a oração principal focada no desfecho."
                    )
                ),
                goldTip = "Na redação e na prova de linguagens: trocar 'embora' por 'mas' altera a estrutura sintática e o foco de ênfase argumentativa!"
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "ling_com_01" -> listOf(
                MathExercise(
                    id = "ling_com_01_q1",
                    title = "Identificação de Função da Linguagem",
                    problemStatement = "Analise o trecho de um poema contemporâneo: 'Gastei trinta dias para encontrar o verso exato / Rasguei papéis, descartei rimas / A poesia se faz com palavras, não com sentimentos'. Ao tematizar o próprio processo artesanal de elaboração do poema através da própria poesia, predomina no texto a função:",
                    options = listOf(
                        "fática, com o objetivo exclusivo de testar a eficiência sonora do microfone.",
                        "metalinguística, pois o código poético é utilizado para refletir criticamente sobre o próprio código e a produção lírica.",
                        "conativa, apelando para que o leitor adquira imediatamente a antologia do poeta.",
                        "referencial pura, relatando de maneira imparcial uma notícia jornalística sobre a venda de papel.",
                        "exotérica, que se propõe a transmitir fórmulas mágicas da alquimia clássica."
                    ),
                    correctIndex = 1,
                    hint = "Quando o poema fala de fazer versos, a linguagem explica a própria linguagem.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A função metalinguística ocorre quando a mensagem tem por foco central o próprio código linguístico utilizado.
                        2. O eu lírico discute o ato de selecionar palavras e descartar rimas, explicando a criação poética com os próprios recursos da poesia.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_com_02" -> listOf(
                MathExercise(
                    id = "ling_com_02_q1",
                    title = "Variação Linguística e Adequação",
                    problemStatement = "Um estudante do interior de Minas Gerais utiliza a expressão 'uai, sô, esse trem tá bão demais' durante uma conversa descontraída com seus primos no final de semana. Contudo, ao redigir a redação dissertativo-argumentativa do ENEM, opta por 'o fenômeno revela-se extremamente profícuo para a comunidade'. A atitude do estudante revela:",
                    options = listOf(
                        "completa ignorância gramatical por misturar falares incompatíveis ao longo da vida.",
                        "competência sociolinguística, reconhecendo que a variedade regional informal é legítima no ambiente familiar, ao passo que a redação oficial exige a norma-padrão formal.",
                        "vergonha injustificável de suas origens culturais que o leva a renegar suas tradições.",
                        "violação das diretrizes do Inep, que proíbem o emprego de vocabulário culto na prova escrita.",
                        "preconceito linguístico contra os habitantes das grandes metrópoles brasileiras."
                    ),
                    correctIndex = 1,
                    hint = "O domínio da linguagem reside na capacidade de transitar com adequação entre os diversos contextos sociais.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. Um falante proficiente é aquele que sabe adequar seu registro (diafásico) à situação comunicativa.
                        2. No convívio familiar, a variante regional mineira reforça laços afetivos e identidade; no vestibular oficial, a norma culta é exigência do edital. Saber alternar conforme o contexto é a essência da competência linguística.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            "ling_com_03" -> listOf(
                MathExercise(
                    id = "ling_com_03_q1",
                    title = "Hipertexto e Leitura Digital",
                    problemStatement = "Ao navegar por uma enciclopédia virtual ou portal de notícias, o usuário depara-se com links em azul sublinhados que o conduzem a vídeos, notas biográficas e dados estatísticos complementares. Essa arquitetura textual, denominada hipertexto, caracteriza-se por:",
                    options = listOf(
                        "impor uma leitura estritamente linear, obrigando o leitor a percorrer o texto do primeiro ao último caractere sem desvios.",
                        "proporcionar uma experiência de leitura multilinear e interativa, na qual o leitor participa ativamente da definição de sua rota informativa.",
                        "eliminar a necessidade de pensamento crítico ou interpretação dos dados expostos.",
                        "limitar o acesso à informação a quem possui formação técnica especializada em programação web.",
                        "destruir os padrões de coerência textual em qualquer circunstância comunicativa."
                    ),
                    correctIndex = 1,
                    hint = "O hiperlink rompe a linearidade do livro impresso tradicional, dando autonomia ao leitor.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. O hipertexto é estruturado em rede (nós e links), permitindo que cada leitor trace caminhos individuais conforme seu interesse e necessidade momentânea.
                        Alternativa correta: B
                    """.trimIndent()
                )
            )

            else -> listOf(
                MathExercise(
                    id = "ling_com_04_q1",
                    title = "Coesão Referencial e Conectivos",
                    problemStatement = "Considere o período: 'Embora a legislação brasileira preveja sanções severas aos crimes de racismo, muitos casos ainda são registrados cotidianamente nas delegacias do país.' O conectivo 'Embora' estabelece entre as orações uma relação semântica de:",
                    options = listOf(
                        "causa evidente, demonstrando que a existência da lei é o motivo do surgimento do crime.",
                        "concessão, introduzindo um fato real que contrasta com a quebra de expectativa expressa na oração principal sem anulá-la.",
                        "consequência direta, mostrando o resultado previsível da promulgação do código penal.",
                        "conclusão formal deduzida a partir de premissas silogísticas clássicas.",
                        "finalidade expressa, sinalizando o objetivo almejado pelas autoridades policiais."
                    ),
                    correctIndex = 1,
                    hint = "A existência de uma lei severa criaria a expectativa de diminuição dos crimes; contudo, eles continuam ocorrendo.",
                    stepByStepSolution = """
                        ### Resolução Detalhada:
                        1. A conjunção subordinativa 'embora' introduz oração adverbial concessiva.
                        2. A concessão expressa uma ideia de contrariedade ou oposição que não é suficiente para impedir o cumprimento da ação principal: a lei existe (fato 1), mas não tem impedido o registro de novos casos (fato 2).
                        Alternativa correta: B
                    """.trimIndent()
                )
            )
        }
    }
}
