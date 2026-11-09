package com.example.data.transpetro

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

object TranspetroPorDidactic {

    fun getBundle(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            // =====================================================================
            // MÓDULO 1 — INTERPRETAÇÃO E COMPREENSÃO DE TEXTOS
            // =====================================================================
            "tp_por_m1_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "Em provas de Língua Portuguesa da Fundação Cesgranrio para a Transpetro, as primeiras questões sempre cobram a leitura analítica do texto-base. Para gabaritar, é indispensável diferenciar compreensão textual (aquilo que está expresso na superfície do texto) de interpretação textual (aquilo que se deduz logicamente a partir de pistas e pressupostos linguísticos), evitando a armadilha clássica da extrapolação.",
                theory = """
                    1. Compreensão vs. Interpretação Textual:
                    • Compreensão (Informação Explícita): Consiste em localizar e parafrasear dados, fatos e argumentos efetivamente declarados pelo autor. Comandos típicos da Cesgranrio: "Segundo o texto...", "De acordo com o autor...", "O texto informa explicitamente que...".
                    • Interpretação (Inferência / Implícitos): Consiste em deduzir conclusões logicamente autorizadas pelas premissas do texto. Comandos típicos: "Infere-se do trecho...", "Depreende-se da leitura...", "O texto permite concluir que...".
                    
                    2. Ideia Central (Tese) vs. Ideias Secundárias (Argumentos):
                    • Ideia Central / Tese: É o posicionamento nuclear defendido ao longo do texto ou o eixo temático global que unifica todos os parágrafos.
                    • Ideias Secundárias: São exemplos, dados estatísticos, citações de autoridade, comparações e causas específicas utilizadas para sustentar a ideia central. Um erro recorrente em provas é assinalar um exemplo pontual como se fosse o objetivo global do texto.
                    
                    3. Pressupostos e Subentendidos:
                    • Pressuposto: É uma informação implícita marcada por uma palavra ou estrutura gramatical ("marcador de pressuposição"), sendo indiscutivelmente verdadeira dentro do enunciado. Exemplo: "A refinaria voltou a operar com capacidade máxima" — o verbo "voltar a" pressupõe que ela já havia operado com capacidade máxima anteriormente. Outros marcadores clássicos: advérbios como "ainda", "já", verbos como "deixar de", "continuar a", "tornar a" e adjetivos avaliativos.
                    • Subentendido: É uma insinuação contextual que depende da intenção comunicativa e do conhecimento de mundo do leitor, sem marca gramatical fixa.
                    
                    4. Erros Clássicos de Leitura (Distratores da Cesgranrio):
                    • Extrapolação: Ir além dos limites do texto, adicionando causas, soluções ou generalizações ("sempre", "nunca", "unicamente") que o autor não respaldou.
                    • Redução: Restringir o tema central a um detalhe periférico mencionado em apenas um parágrafo.
                    • Contradição: Inverter a relação de causa e efeito ou atribuir ao autor uma opinião que ele apenas citou para refutar.
                """.trimIndent(),
                practicalApplication = "Na rotina administrativa da Transpetro, a leitura precisa de pareceres jurídicos, relatórios de auditoria e normas operacionais exige separar fatos explicitamente documentados de inferências precipitadas. Por exemplo, se um relatório registra que 'o terminal de cabotagem deixou de apresentar atrasos no terceiro trimestre', o analista depreende com segurança (por pressuposto do verbo 'deixar de') que havia atrasos nos trimestres anteriores, mas não pode extrapolar que a causa única tenha sido a troca de fornecedores se o documento não apontar essa relação.",
                summary = """
                    • Compreensão = está escrito no texto (paráfrase fiel).
                    • Interpretação / Inferência = dedução lógica baseada em pistas textuais (sem inventar dados).
                    • Pressuposto = implícito garantido por marca linguística (já, ainda, deixar de, passar a, voltar a).
                    • Fuja de alternativas com generalizações absolutas ("exclusivamente", "jamais", "todo") quando o texto usa modalizadores ("frequentemente", "tende a", "em geral").
                """.trimIndent(),
                formulaName = "Matriz de Validação de Inferência (Padrão Cesgranrio)",
                formulaSyntax = "Premissa Explícita + Marcador Linguístico = Inferência Válida (Sem Extrapolação)",
                formulaTerms = listOf(
                    "Premissa Explícita: Trecho literal do texto que serve de âncora.",
                    "Marcador Linguístico: Verbo aspectual (passar a, deixar de), advérbio (já, ainda, até) ou conectivo.",
                    "Inferência Válida: Conclusão restrita aos limites lógicos da premissa."
                ),
                formulaWhenToUse = "Sempre que a questão pedir o que se 'depreende', 'infere', 'conclui' ou qual é a 'ideia central' de um parágrafo ou do texto.",
                exampleTitle = "Identificação de Pressuposto e Inferência em Texto Corporativo",
                exampleProblem = "Considere a frase: 'Os terminais aquaviários da companhia ainda demandam modernização nos sistemas de medição automatizada.' O que fica pressuposto pelo emprego do advérbio 'ainda'?",
                exampleSteps = listOf(
                    "Passo 1: Localizar o marcador linguístico de pressuposição: o advérbio de tempo 'ainda'.",
                    "Passo 2: Analisar o valor semântico de 'ainda' antes do verbo 'demandam': indica continuidade de um estado anterior até o momento presente (com expectativa de futura mudança).",
                    "Passo 3: Formular o pressuposto: antes do momento atual, os terminais já demandavam essa modernização, e tal necessidade permanece pendente."
                ),
                exampleAnswer = "Pressupõe-se que a necessidade de modernização já existia anteriormente e persiste até o momento da enunciação.",
                goldTip = "Quando a Cesgranrio perguntar a ideia central do texto, releia o 1º e o último parágrafos: em 90% dos textos dissertativos-argumentativos escolhidos pela banca, a tese é apresentada na introdução e reafirmada na conclusão."
            )

            "tp_por_m1_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "Além de cobrar a compreensão global, a Cesgranrio exige que o candidato reconheça a finalidade comunicativa do texto — distinguindo as tipologias textuais (narração, descrição, dissertação e injunção) e os gêneros textuais — bem como o sentido contextual de palavras, a polissemia, a sinonímia/antonímia e a diferença entre linguagem denotativa e conotativa.",
                theory = """
                    1. Tipologia Textual vs. Gênero Textual:
                    • Tipologia Textual (Sequências Tipológicas): Refere-se à estrutura linguística interna e predominante do texto (são limitadas: narrativa, descritiva, dissertativo-expositiva, dissertativo-argumentativa e injuntiva).
                    • Gênero Textual: Refere-se às formas concretas de textos que circulam na sociedade com função comunicativa definida (inúmeros: editorial, crônica, notícia, relatório técnico, manual de procedimentos, edital, e-mail corporativo).
                    
                    2. As Cinco Tipologias Textuais Cobradas em Prova:
                    • Narração: Relata fatos e acontecimentos em sequência temporal (antes/depois), envolvendo personagens, tempo, espaço e enredo (conflito/clímax/desfecho). Predominam verbos de ação no pretérito perfeito e imperfeito.
                    • Descrição: Retrata características físicas, psicológicas ou espaciais de um objeto, pessoa, ambiente ou processo estático. Predominam verbos de ligação (ser, estar, permanecer), adjetivos e locuções adjetivas (simultaneidade, e não sucessão temporal).
                    • Dissertação Expositiva: Apresenta, explica e informa saberes, conceitos ou dados de maneira impessoal e objetiva, sem o propósito principal de persuadir ou polemizar.
                    • Dissertação Argumentativa: Defende um ponto de vista (tese) sobre um tema debatível, articulando argumentos (causas, dados, exemplos, contra-argumentos) para convencer o leitor.
                    • Injunção (Instrucional): Orienta, instrui ou prescreve comportamentos e etapas de execução (manuais técnicos, normas de segurança, receitas, regulamentos). Marca-se pelo uso de verbos no imperativo ou infinitivo impessoal.
                    
                    3. Semântica Contextual (Sinonímia, Polissemia, Denotação e Conotação):
                    • Sentido Contextual e Polissemia: Uma mesma palavra pode assumir múltiplos sentidos conforme o contexto (polissemia). Na Cesgranrio, substituir um vocábulo por um sinônimo exige preservar não apenas o sentido de dicionário, mas o tom e a nuance exata da frase.
                    • Denotação vs. Conotação (Figuras de Linguagem): Denotação é o sentido literal, referencial e objetivo (padrão em relatórios técnicos); conotação é o sentido figurado, expressivo e metafórico (metáfora, metonímia, ironia, antítese, paradoxo, eufemismo, hipérbole e prosopopeia).
                """.trimIndent(),
                practicalApplication = "No ambiente corporativo da Transpetro, cada documento adota uma tipologia específica: um Padrão de Execução Operacional (PEO) utiliza tipologia predominantemente injuntiva (verbos no imperativo ou infinitivo instruindo o operador); um Relatório de Inspeção de Dutos mescla descrição técnica com dissertação expositiva; já um Parecer de Viabilidade Econômica aplica a dissertação argumentativa em linguagem estritamente denotativa para fundamentar uma decisão gerencial.",
                summary = """
                    • Narração = evolução no tempo (fatos, enredo, pretérito).
                    • Descrição = retrato simultâneo (características, adjetivos, verbos de estado).
                    • Dissertação Expositiva = explica/informa; Argumentativa = defende tese e persuade.
                    • Injunção = instrui/ordena passo a passo (imperativo/infinitivo).
                    • Denotação = sentido literal/real; Conotação = sentido figurado/metafórico.
                """.trimIndent(),
                formulaName = "Chave de Identificação Tipológica e Semântica",
                formulaSyntax = "Objetivo Comunicativo + Marcas Verbais/Morfossintáticas = Tipologia Predominante",
                formulaTerms = listOf(
                    "Contar fatos em sequência temporal: Narração (verbos no passado).",
                    "Caracterizar ser/objeto/cenário: Descrição (adjetivos e verbos de estado).",
                    "Defender opinião com argumentos: Dissertação-Argumentativa (conectivos lógicos).",
                    "Instruir ou ditar regras: Injunção (verbos no imperativo/infinitivo)."
                ),
                formulaWhenToUse = "Quando a questão indagar o objetivo principal do texto, a sequência tipológica predominante ou o sentido figurado/literal de uma expressão.",
                exampleTitle = "Distinção entre Dissertação Argumentativa e Expositiva e Sentido Figurado",
                exampleProblem = "No trecho: 'A logística dutoviária constitui a espinha dorsal do escoamento energético nacional, razão pela qual os investimentos em integridade estrutural jamais podem ser postergados', identifique a tipologia predominante e o valor semântico da expressão 'espinha dorsal'.",
                exampleSteps = listOf(
                    "Passo 1: Avaliar a intenção do enunciado: o autor emite um juízo de valor categórico ('jamais podem ser postergados') fundamentado em uma razão ('razão pela qual...').",
                    "Passo 2: Classificar a tipologia: como há defesa de posicionamento com argumento de causa/justificativa, predomina a sequência dissertativo-argumentativa.",
                    "Passo 3: Analisar a expressão 'espinha dorsal': empregada em sentido conotativo (metáfora) para designar o eixo central, essencial ou estruturante do sistema logístico."
                ),
                exampleAnswer = "Tipologia dissertativo-argumentativa; a expressão 'espinha dorsal' está empregada em sentido conotativo (metafórico), equivalendo a 'eixo estruturante/principal'.",
                goldTip = "Todo texto real pode conter trechos descritivos ou narrativos a serviço de uma tese. Na Cesgranrio, quando se pergunta o tipo textual 'predominante', observe a finalidade global do texto, e não um pequeno exemplo narrativo usado no 2º parágrafo para ilustrar a tese."
            )

            // =====================================================================
            // MÓDULO 2 — COESÃO E COERÊNCIA
            // =====================================================================
            "tp_por_m2_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A coesão referencial é um dos temas mais recorrentes nas provas da Cesgranrio: praticamente toda prova traz ao menos uma questão perguntando 'O pronome destacado refere-se a qual termo?' ou cobrando a distinção entre retomada (anáfora) e antecipação (catáfora). Dominar os mecanismos de referenciação garante leitura rápida, precisa e eliminação de ambiguidades.",
                theory = """
                    1. Coesão Referencial: Anáfora vs. Catáfora:
                    • Anáfora (Retomada): O elemento coesivo retoma um termo ou ideia já expresso anteriormente no texto (seta para trás ←). Exemplo: "Os relatórios fiscais foram auditados; eles não apresentaram inconsistências." ("eles" é anafórico de "Os relatórios fiscais").
                    • Catáfora (Antecipação): O elemento coesivo anuncia ou aponta para um termo que ainda será dito adiante no texto (seta para a frente →). Exemplo: "Só desejamos isto: que a operação ocorra com segurança total." ("isto" é catafórico da oração seguinte).
                    
                    2. Uso dos Pronomes Demonstrativos na Coesão Textual (Regra de Ouro Cesgranrio):
                    • ESTE / ESTA / ISTO:
                      a) No espaço textual (endofórico): É preferencialmente CATAFÓRICO (aponta para o que vai ser dito a seguir: "O lema é este: eficiência e ética").
                      b) Em enumerações com dois antecedentes: Retoma o termo MAIS PRÓXIMO (o último mencionado).
                      c) No tempo: Indica tempo presente ou atual ("neste ano em que vivemos").
                    • ESSE / ESSA / ISSO:
                      a) No espaço textual (endofórico): É ANAFÓRICO (retoma algo já mencionado antes: "Reduzir custos com segurança: esse é o nosso objetivo").
                      b) No tempo: Indica passado ou futuro próximos.
                    • AQUELE / AQUELA / AQUILO:
                      a) Em enumerações com dois antecedentes: Retoma o termo MAIS DISTANTE (o primeiro mencionado). Exemplo: "O transporte aquaviário e o dutoviário são estratégicos: este (dutoviário) opera por tubulações; aquele (aquaviário), por navios."
                      b) No tempo: Indica passado remoto.
                    
                    3. Outros Mecanismos de Coesão Referencial e Coerência:
                    • Elipse (Zêugma): Omissão de um termo facilmente identificável pelo contexto para evitar repetição viciosa.
                    • Substituição Lexical: Retomada por sinônimos, hiperônimos (termo mais abrangente: "petroleiro" → "a embarcação"), hipônimos (termo mais específico) ou expressões nominais definidas ("a estatal", "o referido contrato").
                    • Prevenção de Ambiguidade: Pronomes possessivos ("seu/sua") e relativos ("que") mal posicionados geram duplo sentido quando há dois núcleos possíveis como antecedente.
                """.trimIndent(),
                practicalApplication = "Em minutas contratuais e relatórios de controle na Transpetro, confundir 'este' com 'aquele' ou usar o pronome 'seu' de forma ambígua pode gerar disputas jurídicas sobre obrigações de fornecedores. Se uma cláusula diz 'A Contratante notificou a Contratada sobre o atraso em sua base operacional', o pronome 'sua' é ambíguo; a redação técnica exige especificar 'na base operacional desta (da Contratada)' ou 'na base operacional daquela (da Contratante)'.",
                summary = """
                    • Anáfora = retoma o que já passou (ESSE / ISSO / ELE / O QUAL).
                    • Catáfora = antecipa o que ainda virá (ESTE / ISTO).
                    • Dois termos antecedentes (A e B): ESTE = B (último/mais próximo); AQUELE = A (primeiro/mais distante).
                    • Hiperônimo = geral (ex.: veículo); Hipônimo = específico (ex.: caminhão-tanque).
                """.trimIndent(),
                formulaName = "Mapa de Referenciação dos Demonstrativos",
                formulaSyntax = "Termo A (1º) ... Termo B (2º) -> AQUELE = A (distante) | ESTE = B (próximo) | ISSO = Anáfora | ISTO = Catáfora",
                formulaTerms = listOf(
                    "ESTE/ISTO: Catafórico (anuncia o que vem depois) ou anafórico do termo mais recente.",
                    "ESSE/ISSO: Anafórico padrão (retoma ideia ou termo já enunciado).",
                    "AQUELE/AQUILO: Anafórico do termo mais distante quando há dois referentes."
                ),
                formulaWhenToUse = "Em todas as questões que pedem o referente de um pronome (pessoal, demonstrativo, relativo ou possessivo) ou o preenchimento correto de este/esse/aquele.",
                exampleTitle = "Resolução de Referenciação Dupla em Texto Técnico",
                exampleProblem = "Analise o período: 'A gerência de suprimentos e a coordenadoria financeira revisaram o orçamento anual; aquela validou os quantitativos físicos, ao passo que esta aprovou o cronograma de desembolso.' A quem se referem os pronomes 'aquela' e 'esta'?",
                exampleSteps = listOf(
                    "Passo 1: Identificar os dois referentes nominais antecedentes na primeira oração: Termo 1 = 'A gerência de suprimentos' (mais distante); Termo 2 = 'a coordenadoria financeira' (mais próximo).",
                    "Passo 2: Aplicar a regra de retomada distributiva: 'aquela' retoma o primeiro termo mencionado (mais distante).",
                    "Passo 3: 'esta' retoma o último termo mencionado (mais próximo)."
                ),
                exampleAnswer = "'Aquela' refere-se a 'A gerência de suprimentos', enquanto 'esta' refere-se a 'a coordenadoria financeira'.",
                goldTip = "Quando a Cesgranrio destacar um pronome relativo ('que', 'o qual', 'cujo') ou oblíquo ('o', 'a', 'lhe') e perguntar seu referente, substitua o pronome pelos candidatos das alternativas dentro da oração subordinada verificando concordância de gênero/número e coerência semântica!"
            )

            "tp_por_m2_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A coesão sequencial — operada pelas conjunções, locuções conjuntivas e preposições — é o tópico mais cobrado em toda a prova de Língua Portuguesa da Cesgranrio. A banca exige que o candidato identifique com precisão a relação lógico-semântica estabelecida entre as orações (concessão, adversidade, causa, consequência, condição, finalidade, proporção, conclusão e explicação).",
                theory = """
                    1. Quadro Definitivo dos Conectivos e Relações Lógico-Semânticas na Cesgranrio:
                    • Oposição / Adversidade (Coordenação): Contraste que marca o argumento mais forte na segunda oração.
                      Conectivos: mas, porém, contudo, todavia, entretanto, no entanto, não obstante (com verbo no indicativo).
                    • Concessão (Subordinação): Fato contrário ou obstáculo que NÃO impede a realização do fato principal.
                      Conectivos: embora, conquanto, ainda que, mesmo que, se bem que, posto que, por mais que, malgrado, a despeito de, apesar de (+ substantivo ou verbo no infinitivo).
                    • Causa (Origem / Motivo): Indica o motivo gerador de um efeito.
                      Conectivos: porque, visto que, já que, uma vez que, como (sempre no início do período), porquanto, haja vista, em virtude de, devido a.
                    • Consequência (Resultado / Efeito): Indica o efeito decorrente de uma causa.
                      Conectivos: tão/tanto/tamanho ... que, de modo que, de sorte que, de forma que, de maneira que.
                    • Condição (Hipótese): Requisito necessário (ou impedimento) para que a oração principal ocorra.
                      Conectivos: se, caso, contanto que, desde que (com verbo no subjuntivo), a menos que, a não ser que, salvo se, sem que.
                    • Finalidade (Objetivo / Propósito): Meta intencional da ação.
                      Conectivos: para que, a fim de que, com o fito de, com o propósito de, para (+ infinitivo).
                    • Proporção (Simultaneidade Gradual): Duas grandezas que variam paralelamente.
                      Conectivos: à medida que, à proporção que, ao passo que, quanto mais ... mais. (Atenção: não existe "na medida que" nem "à medida em que"!).
                    • Conformidade (Acordo / Critério): Harmonia com uma regra, fonte ou norma.
                      Conectivos: conforme, segundo, consoante, como ("Conforme determina o edital...").
                    • Conclusão vs. Explicação:
                      - Conclusivas: portanto, logo, por conseguinte, destarte, dessarte, pois (posposto ao verbo, entre vírgulas).
                      - Explicativas: porque, que, porquanto, pois (anteposto ao verbo, especialmente após verbo no imperativo).
                """.trimIndent(),
                practicalApplication = "Ao redigir justificativas de aditivos contratuais ou relatórios de análise de desvios na Transpetro, a escolha exata do operador argumentativo define o rumo da decisão: usar 'embora o custo inicial seja elevado, a automação reduzirá perdas operacionais' (concessão na 1ª oração) coloca o foco favorável na redução de perdas; já inverter com 'a automação reduzirá perdas, todavia o custo inicial é elevado' (adversidade na 2ª) desloca o peso argumentativo para o obstáculo orçamentário.",
                summary = """
                    • Concessão (obstáculo vencido): embora, conquanto, posto que, ainda que, apesar de, a despeito de.
                    • Causa (motivo): já que, visto que, uma vez que, porquanto, como (inicial), na medida em que.
                    • Proporção (variação conjunta): à medida que, à proporção que, ao passo que.
                    • Condição (requisito): caso, contanto que, desde que (+ subjuntivo), a menos que.
                    • POIS antes do verbo = explicativo/causal (= porque); POIS depois do verbo (entre vírgulas) = conclusivo (= portanto).
                """.trimIndent(),
                formulaName = "Diferenciação Crítica de Locuções Semelhantes",
                formulaSyntax = "À medida que = Proporção | Na medida em que = Causa (tendo em vista que) | Ao passo que = Proporção ou Oposição",
                formulaTerms = listOf(
                    "À medida que (com crase, sem 'em'): equivale a 'à proporção que' (variação gradual).",
                    "Na medida em que (sem crase, com 'em'): equivale a 'visto que / uma vez que' (relação de causa).",
                    "Conquanto / Posto que: conjunções concessivas (= embora); NUNCA confunda com 'porquanto' (= porque)."
                ),
                formulaWhenToUse = "Sempre que a Cesgranrio pedir o valor semântico de um conectivo sublinhado ou sua substituição sem alteração de sentido.",
                exampleTitle = "Identificação de Valor Semântico de Conectivo Menos Usual",
                exampleProblem = "No período: 'Conquanto as condições meteorológicas fossem adversas no litoral, a operação de atracação foi concluída dentro do cronograma, porquanto a equipe seguiu rigorosamente o protocolo de contingência', quais são os valores semânticos de 'Conquanto' e 'porquanto'?",
                exampleSteps = listOf(
                    "Passo 1: Analisar 'Conquanto': introduz um fato adverso (mau tempo) que não impediu a conclusão da atracação (com verbo 'fossem' no subjuntivo). Equivale a 'Embora'. Valor: Concessão.",
                    "Passo 2: Analisar 'porquanto': introduz o motivo/explicação pelo qual a operação teve êxito dentro do prazo. Equivale a 'porque / visto que'. Valor: Causa/Explicação."
                ),
                exampleAnswer = "'Conquanto' expressa concessão (= embora) e 'porquanto' expressa causa/explicação (= porque, visto que).",
                goldTip = "Decore este trio campeão de pegadinhas da Cesgranrio: CONQUANTO = embora (concessão); PORQUANTO = porque (causa/explicação); ENQUANTO = tempo ou contraste. E lembre-se: 'Posto que' na norma-padrão é SEMPRE concessivo (= embora)!"
            )

            // =====================================================================
            // MÓDULO 3 — REESCRITA DE FRASES E PARÁGRAFOS
            // =====================================================================
            "tp_por_m3_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "As questões de reescrita e equivalência de estruturas da Cesgranrio exigem dupla atenção: ao substituir um conectivo ou reestruturar uma oração, você precisa manter simultaneamente (1) o sentido original (mesma família semântica) e (2) a correção gramatical (especialmente o modo e o tempo verbal exigidos pelo novo conectivo).",
                theory = """
                    1. Regra de Ouro da Substituição de Conectivos:
                    Para substituir um conectivo mantendo o sentido e a correção gramatical, verifique duas etapas:
                    • Etapa Semântica: O novo conectivo pertence exatamente ao mesmo grupo semântico? (Ex.: concessivo só troca por concessivo ou adversativo com ajuste de coordenadas; causal só troca por causal).
                    • Etapa Morfossintática (Ajuste Verbal): Conectivos do mesmo grupo podem exigir modos verbais diferentes!
                    
                    2. Ajustes Verbais Clássicos nas Substituições da Cesgranrio:
                    • Grupo da Concessão:
                      - "Embora / Conquanto / Ainda que / Mesmo que / Posto que" + verbo no SUBJUNTIVO: "Embora o sistema apresente falhas..."
                      - "Apesar de / A despeito de / Malgrado" + verbo no INFINITIVO (ou substantivo): "Apesar de o sistema apresentar falhas..." (Atenção: não contraia a preposição 'de' com o artigo do sujeito de infinitivo: "apesar de o sistema apresentar", e não "do sistema").
                      - "Mesmo" + verbo no GERÚNDIO: "Mesmo apresentando falhas..."
                    • Grupo da Condição:
                      - "Se / Quando" exigem FUTURO DO SUBJUNTIVO ao indicar hipótese futura: "Se o fornecedor entregar o lote..."
                      - "Caso / Contanto que / Desde que / A menos que" exigem PRESENTE DO SUBJUNTIVO (ou pretérito imperfeito do subjuntivo): "Caso o fornecedor entregue o lote..." (NUNCA escreva "Caso o fornecedor entregar"!).
                    • Grupo da Causa vs. Consequência (Conversão Espelhada):
                      - "Como choveu muito (Causa), o embarque atrasou (Consequência)."
                      - Equivale a: "O embarque atrasou (Consequência) porque choveu muito (Causa)."
                      - Equivale a: "Choveu tanto (Causa), que o embarque atrasou (Consequência)."
                    
                    3. Manutenção do Sentido Original vs. Alteração Semântica:
                    • Deslocar advérbios restritivos ("apenas", "somente", "só", "inclusive") quase sempre altera o sentido da frase, pois muda o escopo do termo modificado:
                      - "O auditor analisou apenas os contratos ontem." (Só analisou contratos, nada mais).
                      - "O auditor analisou os contratos apenas ontem." (Só fez a análise ontem, em nenhum outro dia).
                """.trimIndent(),
                practicalApplication = "Ao revisar relatórios executivos ou minutas de ofícios na Transpetro, reescrever períodos longos para torná-los mais claros sem alterar o compromisso jurídico assumido é uma habilidade diária. Se o texto original diz 'Desde que a contratada comprove a regularidade fiscal, o pagamento será liberado', a substituição de 'Desde que' por 'Se' exige mudar 'comprove' (presente do subjuntivo) para 'comprovar' (futuro do subjuntivo): 'Se a contratada comprovar...'.",
                summary = """
                    • SE + Futuro do Subjuntivo ("Se ele fizer") <-> CASO + Presente do Subjuntivo ("Caso ele faça").
                    • EMBORA / CONQUANTO + Subjuntivo ("Embora seja") <-> APESAR DE + Infinitivo ("Apesar de ser").
                    • Deslocar "apenas / somente / mesmo / até" muda o foco e altera o sentido original!
                    • Adjetivo anteposto vs. posposto pode mudar o sentido ("homem grande" != "grande homem"; "funcionário simples" != "simples funcionário").
                """.trimIndent(),
                formulaName = "Equação de Equivalência Sintático-Semântica na Reescrita",
                formulaSyntax = "[Embora + Subjuntivo] == [Apesar de + Infinitivo] | [Se + Futuro do Subj.] == [Caso + Presente do Subj.]",
                formulaTerms = listOf(
                    "Embora / Conquanto / Ainda que: pedem verbo conjugado no modo Subjuntivo.",
                    "Apesar de / A despeito de: locuções prepositivas que pedem substantivo ou verbo no Infinitivo.",
                    "Se (hipótese futura): pede Futuro do Subjuntivo (fizer, trouxer, vier, mantiver).",
                    "Caso / Desde que / Contanto que: pedem Presente do Subjuntivo (faça, traga, venha, mantenha)."
                ),
                formulaWhenToUse = "Nas questões em que a banca propõe reescrever uma oração trocando o conectivo inicial e pergunta qual alternativa mantém o sentido e a norma-padrão.",
                exampleTitle = "Reescrita de Oração Concessiva com Ajuste Verbal",
                exampleProblem = "Reescreva a oração subordinada de 'Conquanto os custos logísticos tenham subido no semestre, a meta operacional foi atingida' substituindo 'Conquanto' por 'Apesar de', mantendo o sentido e a norma-padrão.",
                exampleSteps = listOf(
                    "Passo 1: Verificar a equivalência semântica: 'Conquanto' e 'Apesar de' ambos expressam concessão.",
                    "Passo 2: Ajustar a forma verbal: 'Conquanto' exigiu o pretérito composto do subjuntivo ('tenham subido'). A locução prepositiva 'Apesar de' exige verbo no infinitivo ('terem subido').",
                    "Passo 3: Evitar a contração da preposição 'de' com o artigo 'os' que acompanha o sujeito do infinitivo ('os custos logísticos')."
                ),
                exampleAnswer = "'Apesar de os custos logísticos terem subido no semestre, a meta operacional foi atingida.'",
                goldTip = "Cuidado com a pegadinha clássica da Cesgranrio: usar 'Caso' seguido de infinitivo ou futuro do subjuntivo ('Caso houver atraso' ou 'Caso surgir dúvida' — ERRADO!). O correto com 'Caso' é sempre 'Caso haja atraso' ou 'Caso surja dúvida'!"
            )

            "tp_por_m3_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "Além da troca de conectivos, a Cesgranrio cobra a reescrita global de parágrafos envolvendo alteração da estrutura sintática: nominalização (transformar verbos em substantivos), inversão da ordem das orações, paralelismo sintático e semântico, e eliminação de ambiguidades estruturais.",
                theory = """
                    1. Paralelismo Sintático e Semântico:
                    • Conceito: Elementos coordenados que desempenham a mesma função na frase devem apresentar estrutura gramatical simétrica (paralelismo sintático) e compatibilidade lógica (paralelismo semântico).
                    • Quebra de Paralelismo (Erro em Prova):
                      - Errado: "O programa visa à redução de custos e modernizar a frota." (Misturou substantivo 'à redução' com verbo no infinitivo 'modernizar').
                      - Correto (Nominalizado): "O programa visa à redução de custos e à modernização da frota."
                      - Correto (Verbal): "O programa visa reduzir custos e modernizar a frota."
                    • Paralelismo com Preposições e Crase:
                      - Se você usa artigo no primeiro elemento de uma enumeração regida pela mesma preposição, deve manter a simetria nos demais: "Refiro-me a auditores e a técnicos" (ambos sem artigo) ou "Refiro-me aos auditores e às técnicas" (ambos com artigo).
                    • Correlatos Aditivos e Alternativos:
                      - "Não só ... mas também / como também": se houver preposição antes de um termo, ela deve ser respeitada no segundo.
                      - "Tanto ... quanto / como": exigem estrita simetria. (Nunca misture "Tanto ... mas também" ou "Não só ... quanto").
                    
                    2. Nominalização e Transposição de Períodos:
                    • Transformar uma oração subordinada ("Quando os equipamentos foram inspecionados...") em um sintagma nominal preposicionado ("Na inspeção dos equipamentos...") enxuga o texto e preserva o valor temporal/causal, desde que mantida a regência nominal correta.
                    
                    3. Eliminação de Ambiguidades e Vícios de Construção:
                    • Gerundismo e Gerúndio Mal Empregado: O gerúndio deve indicar ação simultânea ou imediatamente anterior, nunca uma consequência futura desconectada ou promessa vaga ("Vou estar enviando").
                    • Ordem Canônica (SVC) vs. Inversões: Ao inverter termos (como colocar o objeto antes do verbo ou deslocar adjuntos), é proibido inserir vírgula única entre sujeito e verbo ou gerar confusão entre sujeito e objeto direto.
                """.trimIndent(),
                practicalApplication = "Na elaboração de planos de ação, editais e apresentações executivas da Transpetro, o paralelismo sintático é obrigatório na listagem de metas e atribuições. Uma lista de responsabilidades de um cargo técnico deve iniciar todos os itens com substantivos ('1. Acompanhamento de contratos; 2. Conferência de notas fiscais') ou todos com verbos no infinitivo ('1. Acompanhar contratos; 2. Conferir notas fiscais'), nunca misturando ambos.",
                summary = """
                    • Paralelismo Sintático = termos coordenados devem ter a mesma forma gramatical (verbo com verbo; substantivo com substantivo).
                    • Pares correlatos fixos: "Não só... mas também/como também" e "Tanto... quanto/como" (não misture os pares!).
                    • Nominalização = converter oração verbal em estrutura com substantivo abstrato (ex.: "porque atrasou a entrega" -> "devido ao atraso na entrega").
                    • Reescrita correta = Sentido preservado + Norma-padrão intacta (pontuação, regência, crase e concordância).
                """.trimIndent(),
                formulaName = "Regra da Simetria nos Termos Coordenados (Paralelismo)",
                formulaSyntax = "[Verbo Infinitivo + E + Verbo Infinitivo] OU [Substantivo + E + Substantivo]",
                formulaTerms = listOf(
                    "Simetria Verbal: 'para fiscalizar os dutos e garantir a segurança'.",
                    "Simetria Nominal: 'para a fiscalização dos dutos e a garantia da segurança'.",
                    "Simetria de Regência: 'necessitamos de planejamento (prep. DE) e de controle (prep. DE)'."
                ),
                formulaWhenToUse = "Ao julgar alternativas de reescrita de períodos que contenham enumerações, pares correlatos ('não só... mas também') ou nominalizações.",
                exampleTitle = "Correção de Quebra de Paralelismo e Nominalização",
                exampleProblem = "Identifique o erro de paralelismo na frase: 'A diretoria decidiu tanto investir na capacitação das equipes mas também na aquisição de novos sensores' e apresente a reescrita correta.",
                exampleSteps = listOf(
                    "Passo 1: Analisar o par correlato empregado: o autor iniciou com 'tanto', que exige obrigatoriamente 'quanto' ou 'como' (e não 'mas também', que pertence ao par 'não só... mas também').",
                    "Passo 2: Analisar a simetria sintática após o verbo 'decidiu investir': o primeiro termo é regido pela preposição 'em' ('na capacitação'), logo o segundo também deve ser regido por 'em' ('na aquisição').",
                    "Passo 3: Reescrever harmonizando o par correlato e a estrutura preposicionada."
                ),
                exampleAnswer = "'A diretoria decidiu investir tanto na capacitação das equipes quanto na aquisição de novos sensores' (ou 'não só na capacitação... mas também na aquisição...').",
                goldTip = "Em questões de reescrita na Cesgranrio, elimine primeiro as alternativas com erros gramaticais objetivos (crase proibida, vírgula entre sujeito e verbo, regência incorreta ou troca de 'Caso' + futuro do subjuntivo); geralmente restam apenas 2 alternativas para você comparar o sentido!"
            )

            // =====================================================================
            // MÓDULO 4 — ORTOGRAFIA E ACENTUAÇÃO
            // =====================================================================
            "tp_por_m4_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A ortografia oficial na Fundação Cesgranrio é cobrada tanto em questões diretas de preenchimento de lacunas quanto inserida nas alternativas de reescrita textual. Os pontos mais exigidos são o emprego dos 'porquês', a diferença entre parônimos e homônimos, o uso de letras (S/Z, X/CH, G/J, Ç/SS) e as regras do hífen após o Acordo Ortográfico.",
                theory = """
                    1. Emprego dos Quatro "Porquês":
                    • POR QUE (separado e sem acento):
                      a) Em perguntas diretas ou indiretas (equivale a "por qual razão / pelo qual"): "Por que o navio atrasou?" / "Quero saber por que o navio atrasou."
                      b) Como pronome relativo (equivale a "pelo qual / pela qual / pelos quais / pelas quais"): "As rotas por que (= pelas quais) navegamos são seguras."
                    • POR QUÊ (separado e com acento):
                      Exclusivamente em final de frase ou isolado antes de pausa (ponto, interrogação, exclamação): "O sistema travou, mas ninguém sabe por quê."
                    • PORQUE (junto e sem acento):
                      Conjunção explicativa ou causal em respostas e justificativas (equivale a "pois / já que / visto que"): "A válvula fechou porque a pressão subiu."
                    • PORQUÊ (junto e com acento):
                      Substantivo masculino (equivale a "o motivo / a razão"), vindo sempre precedido de artigo, pronome ou numeral: "Investigamos o porquê (= o motivo) da falha."
                    
                    2. Vocábulos Frequentemente Confundidos (Parônimos e Homônimos):
                    • ONDE vs. AONDE: "Onde" = lugar fixo/estático (verbos que pedem 'em': morar, estar, ficar); "Aonde" = movimento/destino (a + onde, com verbos que pedem 'a': ir, chegar, dirigir-se).
                    • MAL vs. MAU: "Mal" opõe-se a "BEM" (advérbio, substantivo 'o mal' ou conjunção temporal '= logo que'); "Mau" opõe-se a "BOM" (adjetivo).
                    • HÁ vs. A: "Há" indica tempo passado decorrido ("Há dois anos") ou sentido de existir; "A" indica tempo futuro ou distância ("Daqui a duas horas / a 10 km").
                    • SENÃO vs. SE NÃO: "Senão" (junto) = caso contrário, a não ser, defeito; "Se não" (separado) = conjunção condicional "caso não".
                    • Parônimos clássicos: Ratificar (confirmar) vs. Retificar (corrigir); Comprimento (extensão) vs. Cumprimento (saudação ou execução de dever); Infligir (aplicar pena) vs. Infringir (violar norma); Mandado (ordem judicial) vs. Mandato (período de representação política); Discriminar (diferenciar/especificar) vs. Descriminar (absolver/tirar a culpa); Eminente (ilustre/elevado) vs. Iminente (prestes a acontecer).
                    
                    3. Emprego de Letras e Uso do Hífen:
                    • Sufixos -EZ / -EZA (com Z) formam substantivos a partir de adjetivos (rígido -> rigidez; limpo -> limpeza). Sufixos -ÊS / -ESA (com S) indicam origem, título ou nacionalidade (francês, marquês).
                    • Verbos terminados em -ISAR (com S) quando o radical já tem S (análise -> analisar; pesquisa -> pesquisar); terminam em -IZAR (com Z) quando o radical não tem S (atual -> atualizar; padrão -> padronizar). Exceção clássica: catequese -> catequizar; síntese -> sintetizar.
                    • Hífen (Regra Geral do Novo Acordo):
                      - Usa-se hífen quando o prefixo termina com a MESMA letra com que começa a segunda palavra (micro-ondas, anti-inflamatório, sub-base, inter-regional) ou diante de H (super-homem, anti-higiênico).
                      - NÃO se usa hífen quando o prefixo termina em vogal e a segunda palavra começa com vogal DIFERENTE (autoaprendizagem, infraestrutura, semiaberto, extraoficial).
                      - Se o prefixo termina em vogal e a segunda palavra começa com R ou S, dobram-se o R e o S sem hífen (autorregulação, antissocial, contrarreforma, ultrassom).
                """.trimIndent(),
                practicalApplication = "Em documentos de conformidade e auditoria na Transpetro, confundir 'ratificar' com 'retificar' ou 'infringir' com 'infligir' inverte completamente o sentido jurídico de um despacho: 'ratificar o parecer' significa confirmá-lo integralmente, enquanto 'retificar o parecer' significa corrigir um erro nele contido.",
                summary = """
                    • Por que = pergunta/relativo (pelo qual); Por quê = fim de frase; Porque = resposta/causa (pois); O porquê = substantivo (o motivo).
                    • Mal <-> Bem; Mau <-> Bom.
                    • Ratificar = confirmar; Retificar = corrigir; Infringir = violar; Infligir = aplicar punição.
                    • Hífen: Letras iguais separam (micro-ondas); letras diferentes juntam (autoavaliação, infraestrutura); vogal + R/S dobra (antissocial, autorretrato).
                """.trimIndent(),
                formulaName = "Regras Práticas de Ortografia e Hífen",
                formulaSyntax = "Letra Igual = Hífen (micro-ondas) | Vogais Diferentes = Junto (infraestrutura) | Vogal + R/S = Dobra (autorregulação)",
                formulaTerms = listOf(
                    "Exceção dos prefixos SUB/SOB/ALÉM/AQUÉM/RECÉM/EX/VICE: 'sub-reitor', 'além-mar', 'ex-diretor', 'vice-presidente' mantêm hífen.",
                    "Prefixo CO-: junta-se mesmo com 'o', sem hífen (coordenar, cooperar, coobrigado).",
                    "Mal + vogal ou H: usa hífen (mal-estar, mal-humorado); Bem + qualquer palavra: em regra usa hífen (bem-vindo, bem-estar)."
                ),
                formulaWhenToUse = "Para resolver questões de grafia oficial, preenchimento de lacunas com porquês/parônimos e revisão de compostos com prefixos.",
                exampleTitle = "Emprego Correto dos Porquês e Parônimos em Despacho Administrativo",
                exampleProblem = "Complete corretamente as lacunas: 'A comissão explicou o ______ (por que / porquê) de ter decidido ______ (ratificar / retificar) os valores da planilha que continham erro de soma, informando as razões ______ (por que / porque) a revisão foi imediata.'",
                exampleSteps = listOf(
                    "Passo 1: Na 1ª lacuna, a palavra vem precedida do artigo 'o' ('o ______'), funcionando como substantivo (= o motivo). Logo, grafa-se junto e com acento: 'porquê'.",
                    "Passo 2: Na 2ª lacuna, a planilha continha erro de soma e precisava ser corrigida. Corrigir equivale a 'retificar' ('ratificar' seria confirmar).",
                    "Passo 3: Na 3ª lacuna, 'as razões ______' equivale a 'as razões pelas quais'. Quando equivale a 'pelas quais', grafa-se separado e sem acento: 'por que'."
                ),
                exampleAnswer = "porquê / retificar / por que.",
                goldTip = "Para nunca errar 'por que' (separado) em meio de frase sem interrogação, tente substituí-lo por 'por qual razão' ou 'pelo(a) qual': se qualquer uma dessas duas substituições funcionar, a grafia é obrigatoriamente separada ('por que')!"
            )

            "tp_por_m4_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A acentuação gráfica na Fundação Cesgranrio costuma cobrar a justificativa da regra que determina o acento de cada vocábulo ('Assinale o par de palavras acentuadas pela mesma regra') e as alterações trazidas pelo Novo Acordo Ortográfico (especialmente ditongos abertos, hiatos e acentos diferenciais de plural).",
                theory = """
                    1. As Regras Gerais de Acentuação Gráfica:
                    • Proparoxítonas (sílaba tônica na antepenúltima): TODAS são acentuadas, sem exceção (ex.: lógico, trânsito, lâmpada, marítimo, álibi).
                    • Paroxítonas (sílaba tônica na penúltima): Acentuam-se as terminadas em R, L, N, X, I(s), U(s), UM/UNS, Ã(s), ÃO(s), PS e DITONGO oral (seguido ou não de 's').
                      - Exemplos: caráter, nível, hífen, tórax, júri, bônus, fórum, órfã, órgão, bíceps, além das terminadas em ditongo crescente/decrescente (relatório, história, mágoa, série, jóquei).
                      - NÃO se acentuam paroxítonas terminadas em A, E, O, EM, ENS (nem 'hifens', nem 'itens', nem 'rubrica', nem 'recorde').
                    • Oxítonas (sílaba tônica na última): Acentuam-se as terminadas em A(s), E(s), O(s), EM, ENS (ex.: crachá, convés, avô, armazém, parabéns).
                    • Monossílabos Tônicos: Acentuam-se os terminados em A(s), E(s), O(s) (ex.: pá, gás, pé, mês, pó, nós) e nos ditongos abertos ÉI(s), ÉU(s), ÓI(s) (ex.: réis, céu, véu, dói, sóis).
                    
                    2. Regra Especial dos Hiatos (I e U Tônicos):
                    • Acentuam-se o "I" e o "U" tônicos quando formam hiato com a vogal anterior, estando sozinhos na sílaba (ou apenas com 's') e não seguidos de 'nh': sa-í-da, sa-ú-de, pa-ís, ba-ú, fa-ís-ca, ego-ís-mo (mas: ra-i-nha e ju-iz não levam acento).
                    • Atenção na Cesgranrio: "Saída", "país" e "baú" NÃO são acentuados pela regra das paroxítonas ou oxítonas, mas sim pela REGRA DO HIATO!
                    
                    3. O que Mudou com o Novo Acordo Ortográfico (Pontos de Prova):
                    • Ditongos Abertos ÉI e ÓI: Perderam o acento nas palavras PAROXÍTONAS (ideia, assembleia, heroico, boia, joia, paranoico), mas CONTINUAM acentuados nas OXÍTONAS e MONOSSÍLABOS (herói, anéis, papéis, constrói, céu, dói).
                    • Hiato com Ditongo Anterior: "I" e "U" tônicos nas paroxítonas precedidos de ditongo decrescente perderam o acento (feiura, baiuca), mas se a palavra for oxítona o acento permanece (Piauí, tuiuiú).
                    • Vogais Dobradas OO e EE: Perderam o acento circunflexo (voo, enjoo, creem, deem, leem, veem).
                    • Acentos Diferenciais que PERMANECEM (Cuidado!):
                      - PÔDE (pretérito perfeito) vs. PODE (presente).
                      - PÔR (verbo) vs. POR (preposição).
                      - TER e VIR (e seus derivados: manter, conter, convir, intervir) no plural:
                        Ele tem / Eles TÊM; Ele vem / Eles VÊM;
                        Ele mantém / Eles MANTÊM; Ele intervém / Eles INTERVÊM.
                """.trimIndent(),
                practicalApplication = "Na revisão gramatical de comunicados oficiais e relatórios da Transpetro, um dos erros mais frequentes (e mais cobrados pela Cesgranrio em concordância verbal!) é esquecer o acento circunflexo no plural de 'ter', 'vir' e seus derivados: quando o sujeito é plural ('Os terminais têm...', 'As normas mantêm...'), a ausência ou troca do acento agudo pelo circunflexo constitui erro grave de concordância e ortografia.",
                summary = """
                    • Proparoxítonas: todas acentuadas.
                    • Paroxítonas terminadas em ditongo (relatório, nível, água, série): regra muito cobrada pela Cesgranrio!
                    • Hiatos (sa-í-da, pa-ís, ca-í-ram, ba-ú): regra própria do I/U tônico em hiato (não misture com oxítonas!).
                    • Ele tem / Eles têm | Ele mantém / Eles mantêm | Ele vem / Eles vêm | Ele intervém / Eles intervêm.
                    • Ideia, assembleia, heroico, voo, leem, creem = SEM acento no Novo Acordo.
                """.trimIndent(),
                formulaName = "Diferencial Gráfico de Singular vs. Plural (Ter, Vir e Derivados)",
                formulaSyntax = "Ter/Vir (Sing: tem, vem -> Plur: têm, vêm) | Derivados (Sing: mantém, intervém -> Plur: mantêm, intervêm)",
                formulaTerms = listOf(
                    "Simples (monossílabos): singular SEM acento (tem, vem); plural COM circunflexo (têm, vêm).",
                    "Derivados (oxítonos): singular COM acento agudo (mantém, detém, convém); plural COM circunflexo (mantêm, detêm, convêm).",
                    "Crer, Dar, Ler, Ver (e derivados): dobram o 'e' no plural SEM acento (creem, deem, leem, veem, preveem)."
                ),
                formulaWhenToUse = "Tanto em questões de acentuação gráfica quanto em questões de concordância verbal com sujeito simples ou composto.",
                exampleTitle = "Classificação da Regra de Acentuação (Pegadinha Clássica Cesgranrio)",
                exampleProblem = "Por que as palavras 'contribuíram', 'relatório' e 'armazém' recebem acento gráfico?",
                exampleSteps = listOf(
                    "Passo 1: Separar as sílabas de 'contribuíram': con-tri-bu-í-ram. O 'i' é tônico e forma hiato com a vogal 'u' anterior, ficando sozinho na sílaba. Regra: Hiato (e não paroxítona!).",
                    "Passo 2: Separar as sílabas de 'relatório': re-la-tó-rio (ou re-la-tó-ri-o). É uma paroxítona terminada em ditongo crescente (eventualmente classificada como proparoxítona eventual/aparente).",
                    "Passo 3: Separar as sílabas de 'armazém': ar-ma-zém. A sílaba tônica é a última. Regra: Oxítona terminada em -em."
                ),
                exampleAnswer = "'Contribuíram' é acentuada pela regra do hiato ('i' tônico); 'relatório' é paroxítona terminada em ditongo; 'armazém' é oxítona terminada em '-em'.",
                goldTip = "Fique atento à pegadinha número 1 de acentuação da Cesgranrio: colocar 'país' ou 'baú' junto com 'café' ou 'cipó' dizendo que todas seguem a regra das oxítonas. FALSO! 'País' (pa-ís) e 'baú' (ba-ú) são acentuados pela regra do HIATO!"
            )

            // =====================================================================
            // MÓDULO 5 — MORFOLOGIA
            // =====================================================================
            "tp_por_m5_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "Na Morfologia, a Fundação Cesgranrio avalia a capacidade de identificar a classe gramatical que uma palavra assume dentro do contexto (conversão morfossintática), o papel modificador de adjetivos e advérbios, o valor semântico das preposições e a formação do plural dos substantivos e adjetivos compostos.",
                theory = """
                    1. Classes de Palavras Variáveis vs. Invariáveis (Relação de Modificação):
                    • Substantivo: Nomeia seres, conceitos, ações, estados ou objetos; funciona como núcleo do sujeito, dos objetos, do complemento nominal e do aposto. Qualquer palavra precedida de artigo torna-se substantivo (derivação imprópria: "O não foi categórico", "O caminhar é saudável").
                    • Adjetivo e Locução Adjetiva: Caracterizam ou especificam um SUBSTANTIVO, concordando com ele em gênero e número.
                      - Mudança de posição (Adjetivo Anteposto vs. Posposto): Na Cesgranrio, inverter a posição do adjetivo em relação ao substantivo frequentemente muda a classe ou o sentido:
                        "Funcionário alto" (estatura física) vs. "Alto funcionário" (cargo elevado na hierarquia);
                        "Certa informação" (alguma informação — pronome indefinido) vs. "Informação certa" (informação correta — adjetivo).
                    • Advérbio e Locução Adverbial: Palavra INVARIÁVEL (não tem feminino nem plural) que modifica um VERBO, um ADJETIVO ou outro ADVÉRBIO (jamais modifica substantivo!).
                      - Exemplo: "Os técnicos trabalharam bastante" (modifica o verbo 'trabalharam' -> advérbio de intensidade, invariável) vs. "Há bastantes relatórios na mesa" (modifica o substantivo 'relatórios' -> pronome indefinido/adjetivo, variável).
                    • Preposição: Palavra invariável que liga dois termos subordinando o segundo ao primeiro e assumindo valores semânticos no contexto (posse, causa, instrumento, companhia, finalidade, matéria, meio, modo).
                    
                    2. Plural dos Substantivos e Adjetivos Compostos:
                    • Substantivos Compostos com Hífen:
                      - Variam AMBOS os elementos quando formados por: Substantivo + Substantivo (carta-bilhete -> cartas-bilhetes), Substantivo + Adjetivo (amor-perfeito -> amores-perfeitos), Adjetivo + Substantivo (curto-circuito -> curtos-circuitos) ou Numeral + Substantivo (segunda-feira -> segundas-feiras).
                      - Varia APENAS O PRIMEIRO quando ligados por preposição (pé-de-moleque / água-de-colônia -> águas-de-colônia) ou quando o segundo substantivo limita/especifica o tipo do primeiro (navio-escola -> navios-escola; salário-família -> salários-família; bomba-relógio -> bombas-relógio — aqui 'escola' e 'família' funcionam como qualificadores, aceitando-se também na norma culta o plural dos dois em alguns casos, mas preferindo-se variar apenas o 1º).
                      - Varia APENAS O SEGUNDO quando o primeiro elemento é VERBO ou palavra INVARIÁVEL (guarda-chuva -> guarda-chuvas; abaixo-assinado -> abaixo-assinados; alto-falante -> alto-falantes; sempre-viva -> sempre-vivas) ou palavras repetidas/onomatopeicas (reco-reco -> reco-recos).
                    • Adjetivos Compostos:
                      - Regra Geral: Varia APENAS O ÚLTIMO elemento (contratos técnico-operacionais; medidas econômico-financeiras; clínicas médico-cirúrgicas). Exceção: surdo-mudo (surdos-mudos) e azul-marinho / azul-celeste (invariáveis).
                """.trimIndent(),
                practicalApplication = "Na redação de memoriais descritivos e editais na Transpetro, o plural correto de compostos técnicos evita falhas de revisão: escreve-se 'relatórios técnico-administrativos' (adjetivo composto: só o último varia), 'navios-tanque' ou 'navios-cisterna' (segundo substantivo especifica a finalidade do primeiro) e 'válvulas de retenção'.",
                summary = """
                    • Adjetivo modifica SUBSTANTIVO (variável); Advérbio modifica VERBO, ADJETIVO ou ADVÉRBIO (invariável).
                    • Derivação imprópria: artigo antes de verbo/adjetivo/advérbio transforma-o em substantivo.
                    • Plural de Substantivo Composto: Verbo/Invariável + Subst. -> só o 2º varia (guarda-noturnos? Cuidado: 'guarda' = policial é substantivo -> guardas-noturnos! 'guarda' = verbo guardar -> guarda-chuvas!).
                    • Plural de Adjetivo Composto: SÓ O ÚLTIMO varia (acordos luso-brasileiros, análises físico-químicas).
                """.trimIndent(),
                formulaName = "Distinção Morfossintática: Adjetivo vs. Advérbio e Plural de Compostos",
                formulaSyntax = "Modifica Substantivo -> Adjetivo (Variável) | Modifica Verbo/Adjetivo -> Advérbio (Invariável)",
                formulaTerms = listOf(
                    "Guarda-chuva / Guarda-roupa: Verbo + Substantivo -> guarda-chuvas, guarda-roupas.",
                    "Guarda-civil / Guarda-noturno / Guarda-marinha: Substantivo + Adjetivo/Substantivo -> guardas-civis, guardas-noturnos.",
                    "Adjetivo Composto (ex.: técnico-operacional): apenas o último vai para o plural -> técnico-operacionais."
                ),
                formulaWhenToUse = "Quando a questão cobrar a classe gramatical de uma palavra no contexto, a mudança de sentido pela posição do adjetivo ou a flexão de compostos.",
                exampleTitle = "Identificação de Classe Gramatical Contextual e Plural de Compostos",
                exampleProblem = "Nas frases: (I) 'Os técnicos falaram claro durante a auditoria' e (II) 'Elaboraram relatórios técnico-financeiros claros', qual é a classe de 'claro/claros' em cada frase e por que 'técnico' não foi para o plural em (II)?",
                exampleSteps = listOf(
                    "Passo 1: Em (I), 'claro' modifica o verbo 'falaram' (indica o modo como falaram = claramente), permanecendo invariável no masculino singular. Logo, funciona como advérbio de modo (adjetivo adverbializado).",
                    "Passo 2: Em (II), 'claros' caracteriza o substantivo 'relatórios', flexionando-se no plural. Logo, é adjetivo.",
                    "Passo 3: Em 'técnico-financeiros' (adjetivo composto que qualifica 'relatórios'), aplica-se a regra geral dos adjetivos compostos: apenas o último elemento ('financeiros') flexiona-se em gênero e número."
                ),
                exampleAnswer = "Em (I), 'claro' funciona como advérbio de modo (invariável); em (II), 'claros' é adjetivo; 'técnico-financeiros' é adjetivo composto, no qual apenas o último elemento varia.",
                goldTip = "A Cesgranrio adora cobrar o valor semântico das preposições ('de', 'com', 'por', 'para', 'em'). Sempre observe a relação lógica entre os termos conectados: 'tubulação de aço' (matéria), 'parada por manutenção' (causa/motivo), 'medir com sensor' (instrumento)."
            )

            "tp_por_m5_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "O emprego dos pronomes oblíquos átonos (o, a, os, as, lhe, lhes) e a colocação pronominal (próclise, mesóclise e ênclise) figuram entre as questões favoritas da Fundação Cesgranrio. A banca testa sistematicamente se o candidato sabe substituir objetos diretos e indiretos pelo pronome correto, aplicar as adaptações fonéticas (lo/la, no/na) e posicionar o pronome diante de palavras atrativas.",
                theory = """
                    1. Emprego dos Pronomes Oblíquos Átonos (O, A, OS, AS vs. LHE, LHES):
                    • O, A, OS, AS: Funcionam como OBJETO DIRETO (substituem complementos SEM preposição exigida pelo verbo).
                      - Adaptações fonéticas obrigatórias na ênclise:
                        a) Verbos terminados em R, S ou Z: caem essas consoantes e o pronome vira LO, LA, LOS, LAS (Analisar + o = analisá-lo; Fiz + a = fi-la; Lemos + os = lemo-los).
                        b) Verbos terminados em som nasal (M, ÃO, ÕE): o pronome vira NO, NA, NOS, NAS (Entregaram + o = entregaram-no; Põe + as = põe-nas).
                    • LHE, LHES: Funcionam como OBJETO INDIRETO (substituem complementos regidos pelas preposições A ou PARA, preferencialmente referentes a pessoas) ou como adjunto adnominal de posse ("Roubaram-lhe a carteira" = a carteira dele).
                      - Pegadinha Cesgranrio: Verbos transitivos diretos (como ver, encontrar, convidar, cumprimentar, estimar, conhecer) NÃO aceitam "lhe"! ("Eu o convidei", e não "Eu lhe convidei"). E verbos transitivos indiretos que regem outras preposições (de, em, com — como assistir a coisa, depender de, confiar em, aspirar a) também NÃO aceitam "lhe", exigindo a forma tônica ("assisti a ele", "dependo dele").
                    • EU vs. MIM: "Eu" é pronome reto (funciona como sujeito de verbo no infinitivo: "Para eu assinar o termo"); "Mim" é pronome oblíquo tônico (não conjuga verbo: "Entregou o termo para mim", "Para mim, assinar o termo é urgente").
                    
                    2. Colocação Pronominal (Próclise, Mesóclise e Ênclise):
                    • Regra Proibitiva nº 1: NUNCA se inicia período (ou oração após pausa/vírgula) com pronome oblíquo átono na norma-padrão ("Me entregaram o laudo" -> ERRADO; "Entregaram-me o laudo" -> CORRETO).
                    • Regra Proibitiva nº 2: NUNCA se usa ênclise em verbos no FUTURO do presente ("entregarei-te" -> ERRADO), no FUTURO do pretérito ("entregaria-te" -> ERRADO) nem no PARTICÍPIO ("tinha entregado-lhe" -> ERRADO).
                    • Fatores de PRÓCLISE OBRIGATÓRIA (Pronome ANTES do verbo — "P.A.N.C.I.R."):
                      1) Palavras Negativas: não, nunca, jamais, nada, ninguém, nem (sem pausa entre a palavra e o verbo).
                      2) Advérbios e locuções adverbiais (sem vírgula após eles!): já, sempre, aqui, talvez, só, apenas, ainda. (Se houver vírgula após o advérbio, cessa a atração e usa-se ênclise!).
                      3) Pronomes Relativos: que, quem, o qual, onde, cujo.
                      4) Pronomes Indefinidos e Interrogativos: tudo, todos, alguém, qualquer, quem, quanto.
                      5) Conjunções Subordinativas: que, se, embora, como, quando, conforme, porque, caso.
                      6) Em + Gerúndio ("Em se tratando de segurança...").
                    • MESÓCLISE (Pronome no MEIO do verbo): Obrigatória apenas com verbos no Futuro do Presente ou Futuro do Pretérito quando NÃO houver palavra atrativa antes deles ("Realizar-se-á a inspeção amanhã", "Dar-lhe-íamos o suporte"). Se houver palavra atrativa, a próclise vence a mesóclise ("Não se realizará").
                    • Locuções Verbais: Com verbo principal no infinitivo ou gerúndio, sem palavra atrativa: "Quero-lhe dizer" / "Quero dizer-lhe". Com palavra atrativa: "Não lhe quero dizer" ou "Não quero dizer-lhe" (nunca no meio após hífen se o 2º verbo for particípio!).
                """.trimIndent(),
                practicalApplication = "Em ofícios e comunicações formais da Transpetro, frases como 'Informamos-lhes que os relatórios encontram-se prontos' contêm erro de colocação pronominal cobrado pela Cesgranrio: a conjunção integrante 'que' atrai obrigatoriamente o pronome para antes do verbo ('...que os relatórios se encontram prontos' ou, se não houver sujeito intercalado que permita flexibilização, a banca cobra estritamente a próclise desencadeada por 'que', 'não', 'já' e 'quando').",
                summary = """
                    • O/A/OS/AS (lo/la, no/na) = Objeto Direto (sem preposição); LHE/LHES = Objeto Indireto (com prep. A/PARA).
                    • Proibido iniciar frase com me, te, se, o, a, lhe, nos, vos!
                    • Proibido usar pronome após Futuro (farei, faria) ou Particípio (feito).
                    • Palavras atrativas (Próclise): Negativas, Advérbios (sem vírgula), Pronomes Relativos/Indefinidos/Interrogativos e Conjunções Subordinativas.
                """.trimIndent(),
                formulaName = "Algoritmo de Colocação e Substituição Pronominal Cesgranrio",
                formulaSyntax = "Palavra Atrativa -> PRÓCLISE | Futuro s/ Atrativo -> MESÓCLISE | Início/Pausa -> ÊNCLISE",
                formulaTerms = listOf(
                    "Verbo em R, S, Z + o/a = -lo/-la (ex.: refazer + o = refazê-lo; propôs + a = propô-la).",
                    "Verbo nasal (M, ÃO, ÕE) + o/a = -no/-na (ex.: mantêm + os = mantêm-nos).",
                    "Advérbio SEM vírgula = Próclise ('Aqui se trabalha'); Advérbio COM vírgula = Ênclise ('Aqui, trabalha-se')."
                ),
                formulaWhenToUse = "Em todas as questões que pedem substituição de complemento verbal por pronome átono ou avaliação da posição correta do pronome oblíquo.",
                exampleTitle = "Substituição de Complemento e Colocação Pronominal",
                exampleProblem = "Substitua os termos destacados pelo pronome oblíquo átono adequado e posicione-o segundo a norma-padrão: (1) 'Os auditores ainda não analisaram OS CONTRATOS'; (2) 'Submeteram AS PLANILHAS à diretoria'.",
                exampleSteps = listOf(
                    "Passo 1: Na frase (1), 'analisaram' é verbo transitivo direto ('analisar algo'), logo 'os contratos' é objeto direto masculino plural, substituído por 'os'.",
                    "Passo 2: Como há a palavra negativa 'não' antes do verbo 'analisaram', a próclise é obrigatória: 'Os auditores ainda não os analisaram'.",
                    "Passo 3: Na frase (2), 'submeteram' inicia a frase (proibida próclise!). 'As planilhas' é objeto direto feminino plural ('as'). Como o verbo 'submeteram' termina em '-m' (som nasal), o pronome 'as' assume a forma '-nas' em ênclise: 'Submeteram-nas à diretoria'."
                ),
                exampleAnswer = "(1) 'Os auditores ainda não os analisaram'; (2) 'Submeteram-nas à diretoria'.",
                goldTip = "Quando houver um sujeito explícito entre a palavra atrativa (como a conjunção 'que' ou 'embora') e o verbo ('Espero que os técnicos realizem-na' vs. 'Espero que os técnicos a realizem'), a Cesgranrio dá preferência absoluta à PRÓCLISE ('Espero que os técnicos a realizem')!"
            )

            // =====================================================================
            // MÓDULO 6 — VERBOS
            // =====================================================================
            "tp_por_m6_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "O estudo dos verbos para a Cesgranrio exige dominar os valores semânticos dos tempos e modos verbais (indicativo, subjuntivo e imperativo), a conjugação dos verbos irregulares clássicos (derivados de ter, vir, pôr, ver e haver/intervir) e a harmonia temporal entre as orações (correlação verbal).",
                theory = """
                    1. Modos e Tempos Verbais (Valores Semânticos na Cesgranrio):
                    • Modo Indicativo (Certeza, Fato Real):
                      - Presente: fato atual, verdade científica/permanente ou ação habitual ("O duto transporta derivados diariamente").
                      - Pretérito Perfeito: ação pontual e totalmente concluída no passado ("A auditoria encerrou-se ontem").
                      - Pretérito Imperfeito: ação contínua, habitual ou durativa no passado, ou ação que ocorria quando outra sobreveio ("O terminal operava normalmente quando houve a queda de energia").
                      - Pretérito Mais-que-perfeito (simples: -ra, ou composto: tinha/havia + particípio): ação passada ANTERIOR a outra ação também passada ("Quando o inspetor chegou, o navio já atracara / já tinha atracado").
                      - Futuro do Pretérito (-ria): fato futuro dependente de condição passada, hipótese, dúvida ou polidez ("Caso recebesse os insumos, a fábrica dobraria a produção").
                    • Modo Subjuntivo (Hipótese, Dúvida, Desejo, Condição, Concessão):
                      - Presente (-e / -a): possibilidade atual ou futura ("É fundamental que a equipe cumpra o prazo").
                      - Pretérito Imperfeito (-sse): condição ou hipótese vinculada ao passado/futuro do pretérito ("Se a equipe cumprisse o prazo...").
                      - Futuro (-r): eventualidade futura real ("Quando/Se a equipe cumprir o prazo...").
                    • Modo Imperativo (Ordem, Pedido, Instrução):
                      - Afirmativo: Tu e Vós vêm do Presente do Indicativo SEM o 's' final; Você, Nós e Vocês vêm do Presente do Subjuntivo.
                      - Negativo: TODAS as pessoas vêm integralmente do Presente do Subjuntivo ("Não informes tu", "Não informe você").
                    
                    2. Verbos Irregulares Campeões de Prova ("Família Pôr, Ter, Vir e Ver"):
                    • INTERVIR / CONVIR / PROVIR (conjugam-se exatamente como VIR):
                      - Pretérito Perfeito: Eu intervim, Ele INTERVEIO (nunca "interviu"!), Nós interviemos, Eles INTERVIERAM.
                      - Futuro do Subjuntivo: Quando/Se ele INTERVIER (nunca "intervir"!).
                    • MANTER / DETER / CONTER / RETER (conjugam-se como TER):
                      - Pretérito Perfeito: Ele MANTEVE (nunca "manteu"!), Eles MANTIVERAM.
                      - Futuro do Subjuntivo: Se ele MANTIVER (nunca "manter"!).
                    • PROPOR / COMPOR / SUPOR / REPOR (conjugam-se como PÔR):
                      - Pretérito Perfeito: Ele PROPÔS, Eles PROPUSERAM.
                      - Futuro do Subjuntivo: Se ele PROPUSER (nunca "propor"!).
                    • PREVER / REVER / ANTEVER (conjugam-se como VER):
                      - Futuro do Subjuntivo: Se ele PREVIR (assim como "se ele vir", e não "se ele ver/prever"!).
                      - Exceção cuidado: PROVER (abastecer) segue "ver" no presente/futuro do subjuntivo, mas no pretérito perfeito é regular: "Ele proveu o estoque" (e não "praveu").
                    • REAVER (recuperar): só existe nas formas em que HAVER tem a letra "V" (Eu reouve, Ele reouve, Se ele reouver).
                    
                    3. Correlação Verbal (Harmonia Temporal):
                    • [Se + Futuro do Subjuntivo] <-> [Futuro do Presente do Indicativo]: "Se o gestor aprovar (ou 'Quando aprovar'), iniciaremos a obra."
                    • [Se + Pretérito Imperfeito do Subjuntivo (-sse)] <-> [Futuro do Pretérito do Indicativo (-ria)]: "Se o gestor aprovasse, iniciaríamos a obra."
                    • [Se + Tivesse/Houvesse + Particípio] <-> [Teria/Haveria + Particípio]: "Se o gestor tivesse aprovado, teríamos iniciado a obra."
                """.trimIndent(),
                practicalApplication = "Em atas de reunião e pareceres técnicos na Transpetro, erros de conjugação em verbos derivados como 'O gerente interviu na negociação' ou 'Se a contratada manter o cronograma' comprometem a credibilidade formal do documento e são alvos prediletos da Cesgranrio: o correto na norma-padrão é 'O gerente interveio na negociação' e 'Se a contratada mantiver o cronograma'.",
                summary = """
                    • Pretérito Mais-que-perfeito (-ra = tinha feito): passado anterior a outro passado.
                    • Pretérito Imperfeito (-va/-ia): fato contínuo/habitual no passado.
                    • Verbos derivados seguem o primitivo: Intervir = Vir (interveio, se intervier); Manter = Ter (manteve, se mantiver); Propor = Pôr (propôs, se propuser); Prever = Ver (previu, se previr).
                    • Correlação clássica: -SSE (Imperfeito do Subjuntivo) casa com -RIA (Futuro do Pretérito).
                """.trimIndent(),
                formulaName = "Tríade de Ouro da Correlação Verbal na Cesgranrio",
                formulaSyntax = "1) [Presente Subj. (-e/-a)] <-> [Presente/Futuro Ind.] | 2) [Fut. Subj. (-r)] <-> [Fut. Presente] | 3) [Imperf. Subj. (-sse)] <-> [Fut. Pretérito (-ria)]",
                formulaTerms = listOf(
                    "Se + Futuro do Subjuntivo (mantiver, intervier, propuser, vir) -> Futuro do Presente (manterá, fará).",
                    "Se/Caso + Imperfeito do Subjuntivo (mantivesse, interviesse) -> Futuro do Pretérito (manteria, faria).",
                    "Caso + Presente do Subjuntivo (mantenha, intervenha) -> Presente ou Futuro do Presente."
                ),
                formulaWhenToUse = "Para resolver questões de conjugação de verbos irregulares, correlação entre tempos verbais e sentido dos pretéritos.",
                exampleTitle = "Conjugação de Derivados e Correlação Verbal",
                exampleProblem = "Complete o período segundo a norma-padrão: 'Se o fiscal ______ (intervir) a tempo e a contratada ______ (manter) a equipe de prontidão, o vazamento não teria ocorrido? Ajuste também para a correlação correta com 'teria ocorrido'.'",
                exampleSteps = listOf(
                    "Passo 1: No Futuro do Subjuntivo, 'intervir' conjuga-se como 'vir' ('Se ele intervier') e 'manter' conjuga-se como 'ter' ('Se ela mantiver'), que pediriam na principal o futuro do presente ('não ocorrerá').",
                    "Passo 2: Como a oração principal está no futuro do pretérito composto ('não teria ocorrido') ou simples ('não ocorreria'), a correlação verbal exige o pretérito mais-que-perfeito do subjuntivo ('tivesse intervindo / tivesse mantido') ou pretérito imperfeito do subjuntivo ('interviesse / mantivesse')."
                ),
                exampleAnswer = "No futuro: 'Se o fiscal intervier a tempo e a contratada mantiver a equipe, o problema não ocorrerá.' No passado hipotético: 'Se o fiscal interviesse (ou tivesse intervindo) e a contratada mantivesse (ou tivesse mantido) a equipe, o problema não ocorreria (ou não teria ocorrido).'",
                goldTip = "Memorize o particípio de INTERVIR e VIR: é igual ao gerúndio! Escreve-se 'O diretor tinha INTERVINDO na reunião' (e nunca 'intervindo' confundido com 'intervido', que não existe!)."
            )

            "tp_por_m6_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "As vozes verbais e a transposição da voz ativa para a voz passiva (analítica e sintética) conectam o estudo de verbos diretamente às questões de reescrita e concordância verbal da Cesgranrio. Saber identificar o sujeito paciente e manter rigorosamente o mesmo tempo e modo verbal na conversão é garantia de ponto certo na prova.",
                theory = """
                    1. As Três Vozes Verbais:
                    • Voz Ativa: O sujeito é AGENTE (pratica a ação expressa pelo verbo).
                      Exemplo: "A equipe de engenharia revisou os projetos executivos."
                    • Voz Passiva (Sujeito PACIENTE — sofre/recebe a ação verbal): Divide-se em duas formas equivalentes:
                      a) Voz Passiva Analítica: [Sujeito Paciente] + [Verbo Auxiliar SER/ESTAR] + [Particípio do Verbo Principal concordando em gênero e número] + [Agente da Passiva (por/pelo/de)].
                         Exemplo: "Os projetos executivos foram revisados pela equipe de engenharia."
                      b) Voz Passiva Sintética (ou Pronominal): [Verbo Transitivo Direto (ou VTD+I) na 3ª pessoa] + [Pronome Apassivador SE] + [Sujeito Paciente (sem preposição!)].
                         Exemplo: "Revisaram-se os projetos executivos." (Equivale a "Projetos executivos foram revisados" -> logo, o verbo concorda no plural com 'os projetos executivos'!).
                    • Voz Reflexiva (e Recíproca): O sujeito é simultaneamente agente e paciente (pratica e recebe a ação: "O operador feriu-se") ou há ação mútua entre os elementos do sujeito plural ("Os técnicos cumprimentaram-se").
                    
                    2. Regras de Ouro na Conversão da Voz Ativa para a Passiva (Padrão Cesgranrio):
                    • Regra 1: Só admitem transposição para a voz passiva os verbos que possuem OBJETO DIRETO (Verbos Transitivos Diretos - VTD e Verbos Transitivos Diretos e Indiretos - VTDI). Verbos intransitivos, de ligação e transitivos indiretos NÃO vão para a voz passiva (salvo a raríssima exceção histórica de 'obedecer/desobedecer').
                    • Regra 2: O Objeto Direto da voz ativa transforma-se no SUJEITO PACIENTE da voz passiva.
                    • Regra 3: O Sujeito Agente da voz ativa transforma-se no AGENTE DA PASSIVA (precedido de 'por / pelo / pela').
                    • Regra 4 (A Mais Cobrada!): O verbo auxiliar SER na passiva analítica DEVE assumir exatamente o MESMO TEMPO e MODO em que estava o verbo principal na voz ativa, mais o particípio do verbo principal!
                      - Ativa no Presente ("revisa") -> Passiva: "é revisado"
                      - Ativa no Pretérito Perfeito ("revisou") -> Passiva: "foi revisado"
                      - Ativa no Pretérito Imperfeito ("revisava") -> Passiva: "era revisado"
                      - Ativa no Pretérito Mais-que-perfeito ("revisara / tinha revisado") -> Passiva: "fora revisado / tinha sido revisado"
                      - Ativa no Futuro do Presente ("revisará") -> Passiva: "será revisado"
                      - Ativa em Locução Verbal ("vai revisar / deve revisar / está revisando") -> Passiva: "vai ser revisado / deve ser revisado / está sendo revisado" (o número de verbos aumenta em 1!).
                """.trimIndent(),
                practicalApplication = "Em relatórios técnicos e atas de auditoria da Transpetro, a voz passiva (especialmente a sintética, como 'Identificaram-se três não conformidades' ou a analítica 'Foram identificadas três não conformidades') é amplamente empregada para conferir impessoalidade e destacar o objeto auditado em vez do autor da ação.",
                summary = """
                    • Objeto Direto da Ativa -> vira Sujeito Paciente da Passiva (manda na concordância!).
                    • Sujeito da Ativa -> vira Agente da Passiva (introduzido por "por / pelo / pela").
                    • Tempo do verbo auxiliar SER = exatamente o mesmo tempo do verbo da voz ativa!
                    • Passiva Sintética: VTD + SE + Sujeito Paciente -> o verbo concorda obrigatoriamente com o sujeito paciente ("Aluga-se galpão" / "Alugam-se galpões").
                """.trimIndent(),
                formulaName = "Fórmula de Conversão Ativa <-> Passiva Analítica e Sintética",
                formulaSyntax = "Ativa: [Suj. Agente] + [VTD (Tempo T)] + [Obj. Direto] => Passiva: [Obj. Direto vira Suj. Pac.] + [SER (Tempo T) + Particípio] + [por + Agente]",
                formulaTerms = listOf(
                    "Contagem de Verbos: A passiva analítica tem sempre 1 verbo a mais que a voz ativa (1 verbo na ativa -> 2 na passiva; 2 na ativa -> 3 na passiva).",
                    "VTD + SE (Partícula Apassivadora): admite conversão em [SER + Particípio] e concorda com o sujeito paciente.",
                    "VTI/VI + SE (Índice de Indeterminação do Sujeito): não admite passiva analítica e o verbo fica obrigatoriamente na 3ª pessoa do singular ('Precisa-se de técnicos')."
                ),
                formulaWhenToUse = "Sempre que a questão pedir a transposição de uma frase da voz ativa para a passiva (ou vice-versa) ou a concordância de verbos acompanhados da partícula 'se'.",
                exampleTitle = "Conversão de Locução Verbal da Voz Ativa para a Voz Passiva",
                exampleProblem = "Converta a frase 'A comissão de licitação havia homologado os novos contratos de transporte' para a voz passiva analítica e para a voz passiva sintética.",
                exampleSteps = listOf(
                    "Passo 1: Identificar os termos na voz ativa: Sujeito Agente = 'A comissão de licitação'; Locução Verbal (VTD) = 'havia homologado' (2 verbos, no pretérito mais-que-perfeito composto); Objeto Direto = 'os novos contratos de transporte' (plural).",
                    "Passo 2: Na passiva analítica, 'Os novos contratos de transporte' vira o sujeito paciente (plural). A locução verbal 'havia homologado' (2 verbos) ganha o auxiliar 'sido' e vai para o plural: 'haviam sido homologados' (3 verbos).",
                    "Passo 3: 'A comissão de licitação' vira o agente da passiva: 'pela comissão de licitação'. Na passiva sintética (omitindo o agente), usa-se 'Haviam-se homologado os novos contratos de transporte'."
                ),
                exampleAnswer = "Passiva analítica: 'Os novos contratos de transporte haviam sido homologados pela comissão de licitação.' Passiva sintética: 'Haviam-se homologado os novos contratos de transporte.'",
                goldTip = "Use o truque da contagem de verbos na Cesgranrio: se na voz ativa há 1 verbo ('homologou'), na passiva analítica haverá 2 ('foram homologados'); se na ativa há 2 verbos ('vai homologar' ou 'tinha homologado'), na passiva analítica haverá 3 ('vão ser homologados' ou 'tinham sido homologados')!"
            )

            // =====================================================================
            // MÓDULO 7 — SINTAXE
            // =====================================================================
            "tp_por_m7_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A sintaxe do período simples fornece a base estrutural para todas as regras de concordância, regência, crase e pontuação cobradas pela Fundação Cesgranrio. Reconhecer o sujeito, a transitividade verbal, os objetos (direto e indireto) e a distinção clássica entre adjunto adnominal e complemento nominal é decisivo na prova.",
                theory = """
                    1. Termos Essenciais da Oração (Sujeito e Predicado):
                    • Sujeito: Termo sobre o qual se declara algo e com o qual o verbo concorda. JAMAIS começa por preposição!
                      - Simples: 1 núcleo ("O relatório técnico foi enviado").
                      - Composto: 2 ou mais núcleos ("O gerente e o auditor assinaram a ata").
                      - Desinencial / Oculto / Elíptico: implícito, mas identificado pela desinência verbal ("Analisamos os dados ontem" -> sujeito: nós).
                      - Indeterminado: (a) verbo na 3ª pessoa do plural sem referente anterior ("Reajustaram as tarifas"); ou (b) VI / VTI / VL na 3ª pessoa do singular + SE (índice de indeterminação do sujeito: "Precisa-se de analistas", "Trata-se de normas rígidas").
                      - Oração sem Sujeito (Verbo Impessoal na 3ª pessoa do singular):
                        1) Verbo HAVER no sentido de existir, ocorrer ou tempo decorrido ("Houve reuniões decisivas", "Há dois meses").
                        2) Verbo FAZER indicando tempo decorrido ou clima ("Faz três anos que o duto opera").
                        3) Fenômenos da natureza em sentido literal ("Choveu forte no terminal").
                    • Predicado e Predicativos:
                      - Predicativo do Sujeito: caracteriza o sujeito por meio de verbo de ligação ("O parecer está completo") ou verbo de ação no predicado verbo-nominal ("Os inspetores retornaram satisfeitos").
                      - Predicativo do Objeto: caracteriza o objeto direto ou indireto ("O juiz declarou inocente o réu", "A auditoria julgou válidos os documentos").
                    
                    2. Termos Integrantes (Completam o Sentido de Verbos e Nomes):
                    • Objeto Direto (OD): Completa um VTD sem preposição obrigatória.
                    • Objeto Indireto (OI): Completa um VTI com preposição obrigatória exigida pelo verbo.
                    • Agente da Passiva: Pratica a ação na voz passiva analítica (regido por 'por/pelo' ou 'de').
                    • Complemento Nominal (CN) vs. Adjunto Adnominal (AA) com Preposição "DE" (Pegadinha Clássica!):
                      - Adjunto Adnominal (AA): Liga-se SEMPRE a um substantivo (concreto ou abstrato), tem valor ATIVO (pratica a ação) ou indica POSSE/MATÉRIA/ESPECIFICAÇÃO. Ex.: "A resposta do diretor" (o diretor respondeu -> valor ativo = AA); "O anel de ouro" (matéria = AA).
                      - Complemento Nominal (CN): Liga-se a ADJETIVO, ADVÉRBIO ou SUBSTANTIVO ABSTRATO; quando ligado a substantivo abstrato, tem SEMPRE valor PASSIVO (sofre a ação, funcionando como o "objeto" daquele nome). Ex.: "A revisão do contrato" (o contrato foi revisado -> valor passivo = CN); "Favorável ao projeto" (completa o adjetivo 'favorável' = CN).
                    
                    3. Termos Acessórios e Vocativo:
                    • Adjunto Adverbial: Indica circunstância (tempo, lugar, modo, causa, finalidade, concessão, instrumento).
                    • Aposto: Substantivo ou expressão que explica, resume, enumera ou especifica outro substantivo ("A Transpetro, maior subsidiária logística da Petrobras, opera terminais" -> aposto explicativo; "O terminal de Suape" -> aposto especificativo, sem vírgulas!).
                    • Vocativo: Chamamento isolado por vírgula ("Prezados colaboradores, confiram o anexo").
                """.trimIndent(),
                practicalApplication = "Em pareceres técnicos da Transpetro, distinguir se uma expressão como 'a fiscalização da empresa' significa que a empresa fiscalizou (adjunto adnominal — sentido ativo) ou que a empresa foi fiscalizada (complemento nominal — sentido passivo) é essencial para eliminar ambiguidades em relatórios de conformidade.",
                summary = """
                    • Verbo HAVER (= existir/tempo) e FAZER (tempo/clima) são impessoais -> sem sujeito -> 3ª pessoa do singular!
                    • Adjunto Adnominal vs. Complemento Nominal (ambos com prep. DE ligados a substantivo abstrato):
                      - Se PRATICA a ação ou indica posse = Adjunto Adnominal (Ativo).
                      - Se SOFRE a ação (paciente) ou liga-se a adjetivo/advérbio = Complemento Nominal (Passivo).
                    • Aposto Especificativo (ex.: "O rio Amazonas", "A diretora Marina") NÃO tem vírgula!
                """.trimIndent(),
                formulaName = "Teste Definitivo: Adjunto Adnominal (AA) vs. Complemento Nominal (CN)",
                formulaSyntax = "Ligado a Adjetivo/Advérbio -> CN | Ligado a Subst. Concreto -> AA | Ligado a Subst. Abstrato: Agente = AA / Paciente = CN",
                formulaTerms = listOf(
                    "Substantivo Abstrato + DE + Agente (quem faz): Adjunto Adnominal ('A auditoria do tribunal' -> o tribunal auditou).",
                    "Substantivo Abstrato + DE + Paciente (o que sofre): Complemento Nominal ('A auditoria das contas' -> as contas foram auditadas).",
                    "Qualquer termo preposicionado ligado a Adjetivo ou Advérbio: SEMPRE Complemento Nominal ('necessário à operação')."
                ),
                formulaWhenToUse = "Quando a questão pedir a função sintática de um termo preposicionado ou a classificação do sujeito e dos complementos verbais.",
                exampleTitle = "Diferenciação entre Adjunto Adnominal e Complemento Nominal",
                exampleProblem = "Na frase 'A leitura do relatório pelo engenheiro confirmou a necessidade de manutenção', classifique sintaticamente os termos 'do relatório', 'pelo engenheiro' (ou 'do engenheiro', se fosse 'A leitura do engenheiro') e 'de manutenção'.",
                exampleSteps = listOf(
                    "Passo 1: 'leitura' é substantivo abstrato derivado do verbo 'ler'. Em 'do relatório', o relatório sofre a ação de ser lido (valor passivo). Logo, 'do relatório' é Complemento Nominal.",
                    "Passo 2: Se a frase fosse 'A leitura do engenheiro', o engenheiro pratica a ação de ler (valor ativo) -> Adjunto Adnominal.",
                    "Passo 3: 'necessidade' é substantivo abstrato; 'de manutenção' completa o sentido de 'necessidade' como alvo/paciente do que é necessitado -> Complemento Nominal."
                ),
                exampleAnswer = "'do relatório' e 'de manutenção' são Complementos Nominais (valor passivo/integrante); em 'leitura do engenheiro', 'do engenheiro' seria Adjunto Adnominal (valor ativo).",
                goldTip = "Nunca confunda Aposto Especificativo ('O presidente Fulano', 'A Lei nº 13.303') com Adjunto Adnominal: o aposto especificativo é um substantivo próprio ou expressão que individualiza um substantivo comum sem preposição (ou com 'de' em topônimos como 'A cidade de Santos')!"
            )

            "tp_por_m7_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "No período composto, a Fundação Cesgranrio avalia a classificação das orações coordenadas (aditivas, adversativas, alternativas, conclusivas e explicativas) e das orações subordinadas (substantivas, adjetivas restritivas vs. explicativas e adverbiais), com especial atenção à diferença semântica entre orações adjetivas com e sem vírgula.",
                theory = """
                    1. Período Composto por Coordenação (Orações Independentes Sintaticamente):
                    • Assindéticas: Ligadas apenas por pausa/vírgula, sem conjunção.
                    • Sindéticas (introduzidas por conjunção coordenativa):
                      1) Aditivas (soma): e, nem (= e não), não só... mas também, tanto... quanto.
                      2) Adversativas (oposição/contraste): mas, porém, contudo, todavia, entretanto, no entanto.
                      3) Alternativas (escolha/alternância): ou, ou... ou, ora... ora, já... já, quer... quer.
                      4) Conclusivas (dedução lógica): logo, portanto, por conseguinte, por isso, pois (posposto ao verbo).
                      5) Explicativas (justificativa de ordem/opinião): que, porque, porquanto, pois (anteposto ao verbo).
                    
                    2. Orações Subordinadas Substantivas (Exercem função de Substantivo — troque a oração inteira por "ISSO"):
                    Introduzidas pelas conjunções integrantes QUE ou SE:
                    • Subjetiva (funciona como Sujeito da principal — o verbo da principal está na 3ª pessoa do singular e não tem sujeito antes dele!): "É necessário [que todos usem EPI]" (= ISSO é necessário); "Convém [que o relatório seja assinado]"; "Sabe-se [que o duto é seguro]".
                    • Objetiva Direta (OD): "O gestor informou [que a meta foi batida]" (O gestor informou ISSO).
                    • Objetiva Indireta (OI): "A diretoria necessita [de que o parecer seja célere]" (necessita DISSO).
                    • Completiva Nominal (CN): "Temos certeza [de que a auditoria aprovará as contas]" (certeza DISSO — ligada ao substantivo 'certeza' ou a um adjetivo).
                    • Predicativa (após verbo de ligação SER): "O fato é [que a operação superou as expectativas]" (O fato é ISSO).
                    • Apositiva (após dois-pontos explicando um nome): "Só exigimos um requisito: [que haja transparência total]".
                    
                    3. Orações Subordinadas Adjetivas (Introduzidas por Pronome Relativo: que, o qual, quem, cujo, onde):
                    • Restritiva (SEM vírgulas): Restringe, limita ou particulariza o antecedente a apenas uma parte do grupo. Exemplo: "Os navios que passaram por manutenção operam no terminal Sul." (Pressupõe que apenas uma parte dos navios passou por manutenção).
                    • Explicativa (COM vírgulas): Acrescenta uma informação geral ou inerente a TODO o conjunto do antecedente. Exemplo: "Os navios, que passaram por manutenção, operam no terminal Sul." (Informa que TODOS os navios passaram por manutenção).
                    
                    4. Orações Subordinadas Adverbiais (Exercem função de Adjunto Adverbial — 9 tipos):
                    Causais, Consecutivas, Condicionais, Concessivas, Comparativas, Conformativas, Finais, Proporcionais e Temporais.
                """.trimIndent(),
                practicalApplication = "Na redação de normas internas e contratos da Transpetro, colocar ou retirar vírgulas de uma oração adjetiva altera drasticamente o alcance jurídico da regra: 'Os contratos que ultrapassarem R$ 1 milhão passarão por auditoria especial' (Restritiva — só os contratos acima desse valor) vs. 'Os contratos, que ultrapassarem/ultrapassaram R$ 1 milhão, passarão por auditoria' (Explicativa — afirma que todos os contratos daquele lote ultrapassaram o valor).",
                summary = """
                    • Truque do ISSO: Se a oração iniciada por QUE/SE puder ser trocada por ISSO (ou DISSO), QUE/SE é Conjunção Integrante e a oração é Subordinada Substantiva!
                    • Se o QUE puder ser trocado por O QUAL / A QUAL, o QUE é Pronome Relativo e a oração é Subordinada Adjetiva!
                    • Adjetiva SEM vírgula = Restritiva (parte do todo); Adjetiva COM vírgula = Explicativa (todo o conjunto).
                    • "É preciso / É fundamental / Sabe-se / Convém + [QUE...]" = Oração Subordinada Substantiva Subjetiva!
                """.trimIndent(),
                formulaName = "Diferenciação Morfossintática da Palavra 'QUE' e Orações Adjetivas",
                formulaSyntax = "Troca toda a oração por ISSO -> Conj. Integrante (Substantiva) | Troca o QUE por O QUAL -> Pron. Relativo (Adjetiva)",
                formulaTerms = listOf(
                    "Adjetiva Restritiva (sem vírgula): particulariza/especifica apenas um subconjunto.",
                    "Adjetiva Explicativa (entre vírgulas): generaliza atributo para a totalidade do referente.",
                    "Substantiva Subjetiva: funciona como sujeito da oração principal ('Importa que cumpramos a lei' = Isso importa)."
                ),
                formulaWhenToUse = "Quando a Cesgranrio perguntar a função da palavra 'que', a classificação de uma oração subordinada ou o efeito de sentido da inserção/retirada de vírgulas antes do 'que'.",
                exampleTitle = "Efeito Semântico das Vírgulas nas Orações Adjetivas e Classificação do 'Que'",
                exampleProblem = "Compare os períodos: (I) 'Os técnicos da base que concluíram o curso receberam certificação' e (II) 'Os técnicos da base, que concluíram o curso, receberam certificação'. Qual a diferença de sentido entre (I) e (II)?",
                exampleSteps = listOf(
                    "Passo 1: Identificar a oração destacada 'que concluíram o curso': o 'que' equivale a 'os quais' (pronome relativo), introduzindo oração subordinada adjetiva.",
                    "Passo 2: Em (I), sem vírgulas, a oração é Adjetiva Restritiva: indica que SOMENTE os técnicos que concluíram o curso receberam certificação (havia outros que não concluíram).",
                    "Passo 3: Em (II), entre vírgulas, a oração é Adjetiva Explicativa: indica que TODOS os técnicos da base concluíram o curso e todos receberam certificação."
                ),
                exampleAnswer = "Em (I), apenas uma parte dos técnicos concluiu o curso e foi certificada (sentido restritivo); em (II), a totalidade dos técnicos da base concluiu o curso e foi certificada (sentido explicativo).",
                goldTip = "Toda vez que a Cesgranrio perguntar se a retirada das vírgulas antes de um pronome relativo 'que' mantém o sentido original, a resposta é NÃO! A frase continua gramaticalmente correta, mas o sentido muda de Explicativo (todos) para Restritivo (apenas alguns)!"
            )

            // =====================================================================
            // MÓDULO 8 — CONCORDÂNCIA
            // =====================================================================
            "tp_por_m8_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A concordância nominal estuda a adaptação em gênero (masculino/feminino) e número (singular/plural) dos adjetivos, artigos, pronomes, numerais e particípios ao substantivo a que se referem. Na Cesgranrio, além da regra do adjetivo anteposto ou posposto a dois substantivos, caem exaustivamente os casos especiais ('anexo', 'mesmo', 'próprio', 'bastante', 'meio', 'menos' e 'é proibido').",
                theory = """
                    1. Um Adjetivo Referindo-se a Dois ou Mais Substantivos:
                    • Adjetivo POSPOSTO (depois dos substantivos): Admite DUAS concordâncias!
                      a) Concordância Atrativa: concorda com o substantivo mais próximo ("Compramos equipamento e tubulação nova" ou "tubulação e equipamento novo").
                      b) Concordância Lógica/Gramatical: vai para o plural, prevalecendo o masculino se houver gêneros diferentes ("Compramos equipamento e tubulação novos").
                      *Atenção*: Se o adjetivo funcionar como PREDICATIVO (com verbo de ligação), a concordância no plural é obrigatória ("O equipamento e a tubulação estão novos").
                    • Adjetivo ANTEPOSTO (antes dos substantivos):
                      - Como Adjunto Adnominal: concorda obrigatoriamente com o MAIS PRÓXIMO ("Tivemos ótima produtividade e desempenho" / "Tivemos ótimo desempenho e produtividade").
                      - Como Predicativo (do sujeito ou do objeto): pode concordar com o mais próximo ou ir para o plural ("Julguei necessárias/necessário a revisão e o ajuste").
                    
                    2. Casos Especiais Campeões de Prova na Cesgranrio:
                    • ANEXO / INCLUSO / OBRIGADO / QUITE / LESO: São adjetivos/particípios e concordam normalmente em gênero e número com o substantivo a que se referem!
                      - "Seguem anexas as planilhas", "Os boletos estão inclusos", "A diretora disse: muito obrigada", "Estamos quites com o fisco", "Crime de lesa-pátria / leso-patrimônio".
                      - Exceção: A locução adverbial "EM ANEXO" é invariável ("Seguem em anexo as planilhas", embora a Cesgranrio prefira a forma flexionada "anexas").
                    • MESMO e PRÓPRIO:
                      - Quando significam "em pessoa / exatamente ele(a)" (pronomes demonstrativos de reforço), VARIAM: "As próprias auditoras conferiram", "Elas mesmas assinaram o laudo".
                      - Quando "mesmo" significa "realmente / de fato" ou "até / inclusive" (advérbio/operador), é INVARIÁVEL: "As auditoras conferiram mesmo os dados", "Mesmo as diretoras participaram".
                    • BASTANTE / MUITO / POUCO / CARO / BARATO / LONGE:
                      - Quando modificam um SUBSTANTIVO (ou equivalem a "muitos/suficientes"), VARIAM: "Há bastantes motivos", "As peças custaram caras / são caras".
                      - Quando modificam um VERBO, ADJETIVO ou ADVÉRBIO (equivalem a "muito"), são INVARIÁVEIS (advérbios): "Eles estudaram bastante", "Elas estão bastante preparadas", "As peças custaram caro".
                    • MEIO:
                      - Quando significa "metade" (numeral/adjetivo, modificando substantivo), VARIA: "Meia tonelada", "Uma hora e meia".
                      - Quando significa "um pouco / mais ou menos" (advérbio de intensidade, modificando adjetivo), é INVARIÁVEL: "A equipe ficou meio preocupada", "As portas estavam meio abertas" (NUNCA "meia preocupada"!).
                    • É PROIBIDO / É NECESSÁRIO / É BOM / É PERMITIDO / É PRECISO:
                      - SEM determinante (sem artigo/pronome antes do substantivo): fica INVARIÁVEL no masculino singular ("É proibido entrada", "Água é bom para a saúde", "É necessário cautela").
                      - COM determinante (com artigo 'a' ou pronome antes do substantivo): VARIA concordando com o determinante ("É proibida a entrada", "Esta água é boa", "É necessária muita cautela").
                    • MENOS, ALERTA e PSEUDO: São SEMPRE INVARIÁVEIS como advérbio/prefixo ("Havia menos pessoas", "Os vigias permanecem alerta", "Pseudociências"). (Nota: "Menos" jamás vira "menas"!).
                """.trimIndent(),
                practicalApplication = "Nos e-mails corporativos e despachos da Transpetro, a frase 'Encaminho anexas as notas fiscais' e 'É obrigatória a apresentação do crachá' demonstram domínio imediato da norma-padrão exigida tanto no ambiente empresarial quanto nas provas da Cesgranrio.",
                summary = """
                    • Anexo, incluso, obrigado, quite, mesmo (= em pessoa) e próprio -> VARIAM!
                    • Bastante = "muitos/suficientes" -> VARIA ("bastantes processos"); Bastante = "muito" -> INVARIÁVEL ("bastante ágeis").
                    • Meio = "metade" -> VARIA ("meia tarefa"); Meio = "um pouco" -> INVARIÁVEL ("meio cansada").
                    • É proibido entrada (sem artigo 'a') vs. É proibida A entrada (com artigo 'a').
                    • Menos e Alerta ("estar alerta") = SEMPRE INVARIÁVEIS!
                """.trimIndent(),
                formulaName = "Regras Rápidas dos Casos Especiais de Concordância Nominal",
                formulaSyntax = "Sem Artigo: 'É proibido entrada' | Com Artigo: 'É proibida A entrada' | Troca por MUITOS -> 'Bastantes' | Troca por UM POUCO -> 'Meio'",
                formulaTerms = listOf(
                    "Teste do BASTANTE: troque por 'muitos(as)'. Se couber 'muitos(as)', use 'bastantes' (plural); se couber 'muito', use 'bastante' (singular).",
                    "Teste do MEIO: antes de adjetivo feminino ('preocupada', 'cansada', 'aberta'), significa 'um pouco' -> 'meio' invariável!",
                    "Teste do É PROIBIDO: procure o artigo 'a' antes do substantivo feminino. Tem 'a'? Termina em '-a' ('É proibida a...')."
                ),
                formulaWhenToUse = "Em todas as questões que cobrem flexão de adjetivos, particípios e palavras de dupla classe (adjetivo vs. advérbio).",
                exampleTitle = "Resolução de Casos Especiais de Concordância Nominal",
                exampleProblem = "Preencha as lacunas segundo a norma-padrão: 'Seguem ______ (anexo) as faturas que as ______ (próprio) analistas conferiram; ainda há ______ (bastante) pendências, por isso a gerente ficou ______ (meio) apreensiva, lembrando que é ______ (proibido) a liberação sem visto.'",
                exampleSteps = listOf(
                    "Passo 1: 'as faturas' é feminino plural -> 'anexas'.",
                    "Passo 2: 'as ______ analistas' -> 'próprias' (reforça o substantivo feminino plural).",
                    "Passo 3: '______ pendências' -> equivale a 'muitas pendências' (modifica substantivo plural) -> 'bastantes'.",
                    "Passo 4: '______ apreensiva' -> modifica o adjetivo 'apreensiva' (= um pouco apreensiva) -> 'meio' (invariável).",
                    "Passo 5: 'é ______ a liberação' -> o substantivo 'liberação' está determinado pelo artigo 'a' -> 'proibida'."
                ),
                exampleAnswer = "anexas / próprias / bastantes / meio / proibida.",
                goldTip = "Bizu infalível para 'bastante/bastantes' na Cesgranrio: substitua a palavra na frase por 'MUITO'. Se 'muito' for para o plural ('muitos/muitas'), use 'BASTANTES'; se 'muito' continuar no singular, use 'BASTANTE'!"
            )

            "tp_por_m8_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A concordância verbal é a campeã absoluta de cobrança gramatical nas provas da Fundação Cesgranrio. Em quase todos os concursos da banca há uma questão inteira dedicada ao acordo entre o verbo e seu sujeito (especialmente sujeito posposto ou distante, voz passiva sintética com 'se', verbos impessoais 'haver' e 'fazer', expressões partitivas e porcentagens).",
                theory = """
                    1. Regra Geral e Sujeito Composto:
                    • Regra Geral: O verbo concorda em número e pessoa com o núcleo do sujeito, mesmo que o sujeito esteja posposto (depois do verbo) ou separado dele por um longo adjunto adnominal!
                      - Pegadinha Cesgranrio: "A implementação de novos controles operacionais nos terminais marítimos da região Sudeste REDUZIU (e não 'reduziram'!) os custos." (O núcleo do sujeito é 'A implementação', singular!).
                    • Sujeito Composto ANTES do verbo: Vai obrigatoriamente para o PLURAL ("O diretor e o gerente assinaram o contrato"), salvo se os núcleos forem sinônimos ou formarem gradação ascendente/resumida por 'tudo/nada/ninguém'.
                    • Sujeito Composto DEPOIS do verbo (Posposto): Admite DUAS concordâncias (Plural com todos ou Atrativa com o núcleo mais próximo: "Assinaram / Assinou o contrato o diretor e o gerente").
                    
                    2. Concordância com a Partícula "SE" (A Mais Cobrada pela Cesgranrio!):
                    • Caso A — Pronome Apassivador (VTD ou VTDI + SE): O termo sem preposição é o SUJEITO PACIENTE, e o verbo CONCORDA obrigatoriamente com ele!
                      - "Analisou-se o relatório" (singular) vs. "Analisaram-se os relatórios" (plural).
                      - "Devem-se cumprir as metas" (= As metas devem ser cumpridas — plural!).
                    • Caso B — Índice de Indeterminação do Sujeito (VTI, VI ou VL + SE): O termo vem preposicionado (objeto indireto) ou é adjunto adverbial/predicativo, e o verbo fica OBRIGATORIAMENTE na 3ª PESSOA DO SINGULAR!
                      - "Precisa-se de técnicos qualificados" (VTI + de -> singular!).
                      - "Trata-se de questões estratégicas" (VTI + de -> singular!).
                      - "Obedeceu-se aos protocolos de segurança" (VTI + a -> singular!).
                    
                    3. Verbos Impessoais (Haver, Fazer e Locuções Verbais):
                    • HAVER no sentido de existir, ocorrer, acontecer ou indicando tempo decorrido: É IMPESSOAL (não tem sujeito) e fica SEMPRE na 3ª pessoa do singular ("Houve muitos avanços", "Há três anos").
                    • FAZER indicando tempo decorrido ou clima: É IMPESSOAL e fica SEMPRE na 3ª pessoa do singular ("Faz dez meses que o sistema opera").
                    • Atenção nas Locuções Verbais: O verbo impessoal transmite sua impessoalidade ao verbo auxiliar!
                      - "Deve haver soluções" (e NUNCA "Devem haver soluções"!).
                      - "Pode haver falhas", "Costuma haver atrasos", "Vai fazer dois anos" (SEMPRE no singular!).
                      - Mas cuidado: Os verbos EXISTIR, OCORRER e ACONTECER NÃO são impessoais! Eles têm sujeito e vão para o plural normalmente ("Existem soluções", "Devem existir soluções", "Ocorreram falhas").
                    
                    4. Casos Especiais (Expressões Partitivas, Porcentagens, Coletivos, Mais de um, Que vs. Quem):
                    • Expressões Partitivas ("A maioria de", "Grande parte de", "A maior parte de", "Metade de" + nome no plural): O verbo pode ficar no SINGULAR (concordando com o núcleo partitivo) ou ir para o PLURAL (concordando com o especificador no plural): "A maioria dos técnicos aprovou / aprovaram a medida".
                    • Porcentagens e Frações ("1% dos...", "30% da..."): Concorda com o número percentual/numerador OU com o substantivo especificador ("25% do orçamento foi investido / foram investidos"; "1% dos candidatos compareceu / compareceram"). Se o percentual vier determinado por artigo/pronome plural ("Os 30% da frota foram renovados"), o plural é obrigatório.
                    • Mais de um: Fica no singular ("Mais de um técnico assinou"), salvo se houver reciprocidade ou repetição ("Mais de um técnico se cumprimentaram").
                    • Pronomes Relativos QUE vs. QUEM:
                      - Com QUE: o verbo concorda com o antecedente ("Fui eu que elaborei o laudo"; "Fomos nós que elaboramos").
                      - Com QUEM: o verbo fica na 3ª pessoa do singular ("Fui eu quem elaborou") ou concorda com o antecedente ("Fui eu quem elaborei").
                    • Verbo SER indicando horas, datas ou distâncias: Concorda com o numeral ("É uma hora", "São três horas", "São dez quilômetros", "Hoje é dia 15 / Hoje são 15 de maio").
                """.trimIndent(),
                practicalApplication = "Na redação de relatórios gerenciais da Transpetro, a Cesgranrio frequentemente retira trechos reais e testa construções como 'A existência de diversas cláusulas restritivas nos contratos de fornecimento de longo prazo impede (e não impedem!) a prorrogação automática' ou 'Deve haver critérios objetivos (e não devem haver!)' e 'Exigem-se comprovantes fiscais'.",
                summary = """
                    • Ache o NÚCLEO do sujeito (sem preposição!): o verbo concorda com o núcleo, não com o adjunto!
                    • VTD + SE: concorda com o sujeito paciente ("Validaram-se os documentos").
                    • VTI/VI + SE: sempre no singular ("Trata-se de metas", "Precisa-se de fiscais").
                    • HAVER (= existir) e FAZER (tempo) + seus auxiliares = SEMPRE no singular ("Deve haver riscos", "Vai fazer dois anos").
                    • EXISTIR / OCORRER = concordam no plural ("Devem existir riscos").
                    • "A maioria dos..." / "X% de..." = aceitam singular ou plural!
                """.trimIndent(),
                formulaName = "Algoritmo de Concordância Verbal na Cesgranrio",
                formulaSyntax = "Núcleo do Sujeito (s/ prep.) <-> Verbo | Haver/Fazer Impessoal -> 3ª Sing. | VTD+SE -> Concorda | VTI+SE -> 3ª Sing.",
                formulaTerms = listOf(
                    "Isolamento do Núcleo: Risque os termos preposicionados ('de...', 'nos...', 'com...') dentro do sujeito para ver o núcleo real.",
                    "Locução com Haver: 'Deve haver / Pode haver / Há de haver' -> auxiliar sempre no singular.",
                    "Locução com Existir/Ocorrer: 'Devem existir / Podem ocorrer' -> auxiliar concorda com o sujeito plural."
                ),
                formulaWhenToUse = "Em todas as questões de concordância verbal e reescrita com inversão da ordem sintática ou mudança de singular para plural.",
                exampleTitle = "Concordância com Sujeito Longo, Partícula 'Se' e Verbo Impessoal",
                exampleProblem = "Assinale a forma verbal correta em cada oração: (1) 'O controle rigoroso das emissões atmosféricas nas frotas marítimas (reduz / reduzem) o impacto ambiental'; (2) 'Não (deve haver / devem haver) falhas nas medições, pois (exige-se / exigem-se) parâmetros estritos de segurança e (trata-se / tratam-se) de operações críticas.'",
                exampleSteps = listOf(
                    "Passo 1: Em (1), isolar o núcleo do sujeito (termo sem preposição): 'O controle' (masculino singular). Os termos 'das emissões' e 'nas frotas' são preposicionados. Logo, o verbo fica no singular: 'reduz'.",
                    "Passo 2: Em (2), 'haver' está no sentido de 'existir' (impessoal), transmitindo sua impessoalidade ao auxiliar 'deve': 'Não deve haver falhas'.",
                    "Passo 3: 'exigir' é VTD ('exigir algo'); logo, 'parâmetros estritos' é sujeito paciente plural: 'exigem-se parâmetros'. Já 'tratar-se de' é VTI + SE (índice de indeterminação): fica invariável no singular -> 'trata-se de operações críticas'."
                ),
                exampleAnswer = "(1) reduz; (2) deve haver / exigem-se / trata-se.",
                goldTip = "Mais de 70% das questões de concordância verbal da Cesgranrio cobram uma destas três situações: (1) Sujeito singular seguido de longo adjunto no plural ('A criação de novos postos... gerou'), (2) Voz passiva sintética ('Buscam-se soluções') ou (3) Verbo 'haver' impessoal ('Houve/Deve haver mudanças'). Confira sempre essas três primeiro!"
            )

            // =====================================================================
            // MÓDULO 9 — REGÊNCIA
            // =====================================================================
            "tp_por_m9_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A regência verbal e nominal estuda a relação de subordinação entre um verbo (ou um nome: substantivo, adjetivo, advérbio) e seus complementos, definindo se há exigência de preposição e qual é a preposição exigida na norma-padrão. Na Cesgranrio, a regência é cobrada tanto isoladamente quanto como pré-requisito para questões de crase e pronomes relativos.",
                theory = """
                    1. Os 10 Verbos Mais Cobrados pela Cesgranrio (Regência Verbal):
                    • ASSISTIR:
                      1) Sentido de VER / PRESENCIAR: é VTI (rege preposição A) e NÃO aceita "lhe", usando-se "a ele(a)": "Assistimos ao treinamento técnico" / "Assistimos a ele".
                      2) Sentido de AJUDAR / PRESTAR ASSISTÊNCIA: é VTD (ou VTI): "O médico assistiu o (ou ao) trabalhador".
                      3) Sentido de CABER / PERTENCER (direito): é VTI (rege A e aceita "lhe"): "Esse direito assiste ao servidor / assiste-lhe".
                      4) Sentido de MORAR / RESIDIR: é VI (rege preposição EM): "O inspetor assiste em Macaé".
                    • ASPIRAR:
                      1) Sentido de INALAR / RESPIRAR / SUGerir pó: é VTD (sem preposição): "Aspirou o gás no laboratório".
                      2) Sentido de DESEJAR / ALMEJAR: é VTI (rege preposição A, sem aceitar 'lhe' -> usa-se 'a ele/a ela'): "O técnico aspira ao cargo de supervisão".
                    • VISAR:
                      1) Sentido de MIRAR ou PÔR VISTO/RUBRICAR: é VTD (sem preposição): "O gerente visou os cheques e relatórios".
                      2) Sentido de TER EM VISTA / OBJETIVAR / ALMEJAR: é VTI (rege preposição A, especialmente antes de substantivo; antes de infinitivo a preposição é frequentemente omitida no uso contemporâneo, mas na Cesgranrio dê preferência a 'visar a + substantivo' com preposição!): "A medida visa à segurança operacional".
                    • IMPLICAR:
                      1) Sentido de ACARRETAR / GERAR / PRODUZIR COMO CONSEQUÊNCIA: Na norma-padrão cobrada em concurso, é VTD (SEM a preposição "em"!): "O atraso implicará multa contratual" (e NUNCA "implicará em multa"!).
                      2) Sentido de ENVOLVER-SE: VTI pronominal com EM ("Implicou-se em litígio").
                      3) Sentido de TER IMPLICÂNCIA / ANTIPATIA: VTI com COM ("Implicava com o colega").
                    • PREFERIR:
                      É VTDI na estrutura fixa "PREFERIR X A Y" (prefere-se uma coisa A outra).
                      - Proibido pela norma-padrão usar "do que", "mais", "antes" ou "muito mais": escreve-se "Prefiro o transporte dutoviário ao rodoviário" (e NUNCA "Prefiro mais o dutoviário do que o rodoviário"!).
                    • OBEDECER e DESOBEDECER:
                      São SEMPRE VTI (regem a preposição A): "Obedecemos às normas de segurança e aos regulamentos".
                    • PAGAR, PERDOAR e AGRADECER:
                      - Objeto Direto (sem preposição) para COISA: "Pagamos a fatura", "Agradecemos o convite".
                      - Objeto Indireto (com preposição A) para PESSOA: "Pagamos ao fornecedor", "Perdoamos ao infrator".
                      - VTDI quando há coisa e pessoa juntas: "Pagamos a fatura ao fornecedor".
                    • INFORMAR, AVISAR, CERTIFICAR, NOTIFICAR e CIENTIFICAR:
                      São VTDI (um objeto é direto e o outro é indireto com preposição A ou DE/SOBRE — nunca dois objetos diretos nem dois indiretos!):
                      - Construção 1 (Informar ALGO A ALGUÉM): "Informamos o novo prazo aos licitantes."
                      - Construção 2 (Informar ALGUÉM DE/SOBRE ALGO): "Informamos os licitantes do (ou sobre o) novo prazo."
                    • ESQUECER e LEMBRAR:
                      - Sem pronome (eu esqueço, ele lembra): são VTD (SEM preposição!): "Esqueci a senha", "Lembrei o prazo".
                      - Com pronome (esquecer-se, lembrar-se): são VTI (COM preposição DE!): "Esqueci-me da senha", "Lembrei-me do prazo".
                    • CHEGAR e IR:
                      Regem a preposição A (destino), e não EM: "Chegamos ao terminal às 8h", "Fomos à sede da empresa".
                    
                    2. Regência Nominal (Nomes e Suas Preposições Exigidas):
                    • Acessível a; Adaptado a; Afável com/para com; Alheio a; Ansioso por/para; Apto a/para; Aversão a/por; Compatível com; Conformado com; Contemporâneo a/de; Contíguo a;Contrário a; Favorável a; Imune a/de; Inerente a; Nocivo a; Paralelo a; Passível de; Preferível a; Propenso a; Próximo a/de; Residente em (Rua X, e não "na Rua X" como vício formal, embora 'em' admita contração); Situado em (e não "a"); Suscetível a/de.
                """.trimIndent(),
                practicalApplication = "Na redação de notificações extrajudiciais e comunicados internos da Transpetro, construções como 'A falha implicará sanções administrativas', 'Obedeceu-se às diretrizes de SMS' e 'Informamos os fornecedores das novas regras (ou Informamos as novas regras aos fornecedores)' exemplificam o uso rigoroso da regência verbal.",
                summary = """
                    • Assistir (ver), Aspirar (desejar), Visar (objetivar) e Obedecer -> todos são VTI e exigem a preposição A!
                    • Implicar (= acarretar/gerar consequência) -> VTD (NÃO tem preposição "em": "Isso implica custos").
                    • Preferir -> "Prefiro X a Y" (sem "mais", sem "do que").
                    • Pagar/Perdoar/Agradecer -> Coisa = OD (sem prep.); Pessoa = OI (com prep. A).
                    • Esquecer/Lembrar sem pronome = sem "de"; Esquecer-se/Lembrar-se com pronome = com "de".
                """.trimIndent(),
                formulaName = "Painel de Regência Crítica dos Verbos Cesgranrio",
                formulaSyntax = "Implicar (acarretar) = VTD (s/ EM) | Preferir X A Y (s/ DO QUE) | Informar ALGO A alguém == Informar ALGUÉM DE algo",
                formulaTerms = listOf(
                    "Simetria de Informar/Avisar: nunca escreva 'Informei-lhe de que...' (dois objetos indiretos!). Escreva 'Informei-o de que...' ou 'Informei-lhe que...'.",
                    "Simetria de Esquecer/Lembrar: 'Ter SE' = 'Ter DE' ('Lembrou-se do acordo' vs. 'Lembrou o acordo').",
                    "Verbos de Movimento (ir, chegar, dirigir-se, retornar): exigem a preposição A (e não EM)."
                ),
                formulaWhenToUse = "Para resolver questões de regência verbal/nominal, preenchimento de preposições e validação de reescrita.",
                exampleTitle = "Correção de Regência Verbal em Comunicados Corporativos",
                exampleProblem = "Identifique e corrija os desvios de regência no período: 'A gerência informou aos supervisores de que o descumprimento do prazo implicará em penalidades, pois a estatal prefere mais a prevenção do que a correção emergencial.'",
                exampleSteps = listOf(
                    "Passo 1: O verbo 'informar' é VTDI: como 'aos supervisores' já é objeto indireto (com prep. 'a'), a oração seguinte deve ser objeto direto (sem a preposição 'de'): 'informou aos supervisores que...' (ou 'informou os supervisores de que...').",
                    "Passo 2: O verbo 'implicar' no sentido de acarretar é transitivo direto (não rege 'em'): 'implicará penalidades'.",
                    "Passo 3: O verbo 'preferir' rejeita intensificadores ('mais') e 'do que', exigindo a preposição 'a': 'prefere a prevenção à correção emergencial'."
                ),
                exampleAnswer = "'A gerência informou aos supervisores que o descumprimento do prazo implicará penalidades, pois a estatal prefere a prevenção à correção emergencial.'",
                goldTip = "Fique atento ao verbo 'IMPLICAR' e ao verbo 'INFORMAR': na Cesgranrio, 'implicar em' (no sentido de gerar consequência) e 'informar ao cliente de que...' são os dois distratores mais frequentes nas alternativas incorretas!"
            )

            "tp_por_m9_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A regência com pronomes relativos ('que', 'quem', 'o qual / a qual', 'cujo / cuja' e 'onde / aonde') é uma das marcas registradas da Fundação Cesgranrio. Em quase toda prova da banca há uma questão em que você deve identificar qual preposição deve anteceder o pronome relativo de acordo com a regência do verbo ou do nome da oração subordinada adjetiva.",
                theory = """
                    1. A Regra de Ouro do Deslocamento da Preposição:
                    Em português, quando o verbo ou o nome da oração subordinada adjetiva exige uma preposição (a, de, em, com, por, sobre, para), essa preposição migra obrigatoriamente para ANTES do pronome relativo!
                    • Método Prático em 2 Passos:
                      1) Olhe para o verbo (ou adjetivo/substantivo) que vem DEPOIS do pronome relativo e pergunte qual preposição ele exige.
                      2) Coloque essa exata preposição ANTES do pronome relativo!
                    • Exemplos Clássicos de Prova:
                      - "O relatório [a que / ao qual] fizemos referência foi aprovado." (Quem faz referência, faz referência A algo -> preposição A antes do 'que').
                      - "Os equipamentos [de que / dos quais] necessitamos já chegaram." (Quem necessita, necessita DE algo -> preposição DE antes do 'que').
                      - "A norma [a que / à qual] todos devem obedecer entrou em vigor." (Quem obedece, obedece A algo -> preposição A antes do 'que').
                      - "As metas [a que / às quais] a empresa visa são ambiciosas." (Quem visa no sentido de almejar, visa A algo -> preposição A antes do 'que').
                      - "O contrato [cujas cláusulas] analisamos..." vs. "O contrato [a cujas cláusulas] obedecemos..." (Veja a regra do CUJO abaixo!).
                    
                    2. Emprego Especial do Pronome Relativo CUJO (cujo, cuja, cujos, cujas):
                    • Valor Semântico: Indica POSSE, ligando dois substantivos (Possuidor + CUJO + Coisa Possuída).
                    • Regra 1: Concorda em gênero e número com o substantivo que vem DEPOIS dele (a coisa possuída).
                    • Regra 2 (Proibição Absoluta!): JAMAIS se usa artigo ('o', 'a', 'os', 'as') depois de CUJO! ("cujo o autor" -> ERRADO; "cujo autor" -> CORRETO).
                    • Regra 3 (Preposição antes de CUJO): CUJO pode e deve vir precedido de preposição quando o verbo ou nome da oração subordinada exigir!
                      - Exemplo: "A diretora, [em cuja competência] confiamos, assumiu o setor." (Quem confia, confia EM alguém -> 'em cuja competência').
                      - Exemplo: "O projeto, [a cuja execução] assistimos, foi premiado." (Quem assiste no sentido de ver, assiste A algo -> 'a cuja execução').
                    
                    3. QUE vs. O QUAL vs. QUEM vs. ONDE / AONDE:
                    • QUE vs. O QUAL (a qual, os quais, as quais):
                      - Com preposições monossilábicas (a, de, em, por) e um único antecedente claro, usa-se "que" ou "o qual".
                      - Com preposições de duas ou mais sílabas (perante, durante, segundo, mediante, contra, entre, sobre, através de) ou locuções prepositivas, usa-se OBRIGATORIAMENTE "o qual / a qual / os quais / as quais" ("A comissão perante a qual depusemos...").
                    • QUEM: Refere-se exclusivamente a PESSOAS (ou coisas personificadas) e, quando tem antecedente expresso, vem SEMPRE precedido de preposição ("O engenheiro a quem entregamos o laudo").
                    • ONDE vs. AONDE:
                      - Só podem ser usados como pronomes relativos para retomar LUGAR FÍSICO (ou espaço virtual/institucional claramente locativo)! Nunca use "onde" para retomar tempo ("Na época onde..." -> ERRADO: use "em que") ou ideias abstratas.
                      - ONDE = lugar fixo (em que): "O terminal onde (= em que) trabalhamos".
                      - AONDE = movimento/destino (a + onde, com verbos que pedem 'a'): "O terminal aonde (= ao qual) iremos amanhã".
                """.trimIndent(),
                practicalApplication = "Em pareceres jurídicos e termos de referência da Transpetro, construções como 'A cláusula a que a contratada se refere', 'O edital cujos anexos foram retificados' e 'O terminal aonde os inspetores se dirigiram' são marcas de redação culta e aparecem literalmente nas provas da Cesgranrio.",
                summary = """
                    • Olhe o verbo/nome DEPOIS do pronome relativo -> ponha a preposição que ele pede ANTES do pronome relativo!
                    • CUJO = posse entre 2 substantivos; concorda com o que vem DEPOIS; NUNCA tem artigo depois ("cujo o" não existe!); aceita preposição ANTES ("de cujo, a cuja, em cujos").
                    • ONDE = lugar fixo (em que); AONDE = movimento para um lugar (a que). Não use "onde" para tempo!
                    • Preposições longas (perante, mediante, sobre, contra, sem) exigem "o qual / a qual" (e não "que").
                """.trimIndent(),
                formulaName = "Fórmula de Regência com Pronomes Relativos e 'Cujo'",
                formulaSyntax = "[Antecedente] + [Prep. exigida pelo verbo/nome seguinte] + [que / o qual / quem / cujo + subst. s/ artigo] + [Verbo/Nome]",
                formulaTerms = listOf(
                    "Deslocamento: 'Gostamos DO projeto' -> 'O projeto DE QUE (ou DO QUAL) gostamos'.",
                    "Cujo + Regência: 'Discordamos DOS critérios do edital' -> 'O edital DE CUJOS critérios discordamos'.",
                    "Onde vs. Em que: Na dúvida, 'em que / no qual' substitui 'onde' e ainda serve para tempo, situação e lugar!"
                ),
                formulaWhenToUse = "Sempre que houver lacuna antes de pronome relativo ('que', 'qual', 'cujo', 'onde') ou reescrita unindo duas orações por meio de pronome relativo.",
                exampleTitle = "União de Orações com Pronome Relativo 'Cujo' Preposicionado",
                exampleProblem = "Una as duas orações em um único período empregando o pronome relativo 'cujo' de acordo com a norma-padrão: 'A Transpetro auditou a empresa fornecedora. Os gestores dependiam da regularidade fiscal dessa empresa.'",
                exampleSteps = listOf(
                    "Passo 1: Identificar a relação de posse na segunda oração: 'da regularidade fiscal dessa empresa' (a empresa possui a regularidade fiscal).",
                    "Passo 2: Substituir 'dessa empresa' pelo pronome relativo 'cuja', concordando no feminino singular com 'regularidade fiscal' (sem artigo 'a'!).",
                    "Passo 3: Verificar a regência do verbo da segunda oração: quem depende, depende DE algo. Portanto, a preposição 'de' deve vir ANTES de 'cuja': 'de cuja regularidade fiscal os gestores dependiam'."
                ),
                exampleAnswer = "'A Transpetro auditou a empresa fornecedora de cuja regularidade fiscal os gestores dependiam.'",
                goldTip = "Em toda prova da Cesgranrio, quando aparecer 'cujo' nas alternativas, faça dois testes em 5 segundos: (1) elimine qualquer alternativa que tenha artigo depois de cujo ('cujo o', 'cuja a'); (2) olhe o verbo/nome depois para ver se ele exige preposição ('a cujo', 'de cujo', 'em cujo', 'com cujo')!"
            )

            // =====================================================================
            // MÓDULO 10 — CRASE
            // =====================================================================
            "tp_por_m10_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A crase é a fusão de duas vogais idênticas (a preposição 'a' exigida por um verbo ou nome + o artigo definido feminino 'a/as' ou o 'a' inicial dos pronomes 'aquele/aquela/aquilo'), indicada pelo acento grave (`à / às`). Na Fundação Cesgranrio, a crase é cobrada em conjunto com a regência e o paralelismo sintático.",
                theory = """
                    1. A Regra Geral da Crase e o Teste do Masculino:
                    Para ocorrer crase pela regra geral, duas condições devem ser satisfeitas simultaneamente:
                    1ª) O termo regente (verbo, substantivo, adjetivo ou advérbio anterior) deve exigir a PREPOSIÇÃO "A";
                    2ª) O termo regido (palavra seguinte) deve ser feminino e admitir o ARTIGO "A(S)" (ou ser os pronomes demonstrativos "aquele(s), aquela(s), aquilo" ou "a qual / as quais").
                    • Teste Infalível da Substituição por Palavra Masculina:
                      Troque a palavra feminina seguinte por uma palavra masculina equivalente:
                      - Se no masculino aparecer "AO(S)", há crase obrigatória no feminino ("À(S)")!
                      - Exemplo: "Obedecemos às normas" -> troque 'normas' por 'regulamentos' -> "Obedecemos AOS regulamentos" (tem AO -> tem crase!).
                      - Exemplo: "Analisamos as normas" -> troque 'normas' por 'regulamentos' -> "Analisamos OS regulamentos" (não tem AO, é objeto direto -> SEM crase!).
                    
                    2. Crase com os Pronomes Demonstrativos AQUELE(S), AQUELA(S), AQUILO:
                    • Sempre que o verbo ou nome anterior exigir a preposição "A", ocorre fusão dessa preposição com o "A" inicial de aquele/aquela/aquilo (`àquele, àquela, àquilo`), mesmo que a palavra seja masculina!
                    • Teste Prático: Substitua "aquele/aquela/aquilo" por "ESTE / ESTA / ISTO". Se aparecer "A ESTE / A ESTA / A ISTO", usa-se crase (`àquele / àquela / àquilo`)!
                      - Exemplo: "Refiro-me àquele contrato" (= Refiro-me A ESTE contrato).
                    
                    3. Casos em que a Crase é SEMPRE OBRIGATÓRIA:
                    • Locuções Adverbiais Femininas (de tempo, lugar, modo): à tarde, à noite, às pressas, às vezes, à vista, à direita, à esquerda, à deriva, à toa, às claras, à medida que.
                    • Locuções Prepositivas Femininas (terminadas em 'de'): à espera de, à procura de, à frente de, à custa de, à base de, à mercê de, à exceção de.
                    • Locuções Conjuntivas Femininas (terminadas em 'que'): à medida que, à proporção que.
                    • Indicação Exata de Horas Determinadas: às 8h, à 1h da tarde, às 14h30min, à meia-noite, à uma hora.
                      - Cuidado! Se antes da hora já houver outra preposição (PARA, DESDE, APÓS, ENTRE, ATÉ sem preposição 'a'), NÃO há crase: "A reunião foi agendada para as 14h", "Estamos aqui desde as 8h", "Aguardaremos até as 17h (ou até às 17h — com 'até' é facultativo)".
                    • Locução "À moda de / À maneira de" (mesmo subentendida antes de palavra masculina!): "Bife à milanesa", "Estilo à Machado de Assis", "Gol à Pelé".
                """.trimIndent(),
                practicalApplication = "Em convocatórias de reuniões e cláusulas de pagamento da Transpetro, a aplicação precisa da crase diferencia expressões técnicas: 'A reunião terá início às 9h (com crase) e se estenderá até as 12h' vs. 'A reunião foi remarcada para as 9h (sem crase, pois a preposição é PARA)' e 'Pagamento à vista (locução adverbial feminina)'.",
                summary = """
                    • Crase = Preposição A + Artigo A (ou Aquele/Aquela/Aquilo).
                    • Teste do Masculino: Troque a feminina por masculina -> deu AO/AOS? Tem crase (À/ÀS)!
                    • Teste do Aquele: Troque por ESTE/ISTO -> deu A ESTE/A ISTO? Tem crase (ÀQUELE/ÀQUILO)!
                    • Sempre com crase: locuções adverbiais/prepositivas/conjuntivas femininas (às vezes, à espera de, à medida que) e horas exatas (às 10h — salvo após 'desde, para, após, entre').
                """.trimIndent(),
                formulaName = "Equação Fundamental da Crase e Testes Práticos",
                formulaSyntax = "Fem. trocada por Masc. == AO(S) -> À(S) | Aquele trocado por Este == A ESTE -> ÀQUELE",
                formulaTerms = listOf(
                    "À medida que / À proporção que: locuções conjuntivas proporcionais (crase obrigatória).",
                    "Horas com outra preposição: 'para as 10h', 'desde as 10h', 'após as 10h', 'entre as 8h e as 10h' -> SEM crase!",
                    "Paralelismo de Horas: 'Das 8h às 17h' (com 'das', tem 'às') vs. 'De 8h a 17h' (sem 'das', não tem 'às'!)."
                ),
                formulaWhenToUse = "Para validar o uso obrigatório do acento grave em sintagmas nominais femininos, pronomes demonstrativos, horas e locuções.",
                exampleTitle = "Aplicação da Regra Geral e do Paralelismo em Horas",
                exampleProblem = "Avalie o uso da crase em: 'O expediente externo funcionará de 8h às 17h para atender àquelas transportadoras que se submeteram às exigências do edital.' Há algum erro?",
                exampleSteps = listOf(
                    "Passo 1: Analisar 'de 8h às 17h': houve quebra de paralelismo! Como no primeiro termo usou-se apenas a preposição 'de' (sem artigo 'as'), no segundo só cabe a preposição 'a' ('de 8h a 17h'). Para usar 'às 17h', seria obrigatório escrever 'das 8h às 17h'.",
                    "Passo 2: Analisar 'atender àquelas transportadoras': no sentido de dar atendimento, 'atender' pode ser VTD ou VTI; porém, se reger 'a' (atender a estas transportadoras), ocorre crase legítima em 'àquelas'.",
                    "Passo 3: Analisar 'se submeteram às exigências': quem se submete, submete-se A algo (submeteram-se aos requisitos -> AO = ÀS). Correto."
                ),
                exampleAnswer = "Sim, há erro de paralelismo em 'de 8h às 17h': o correto segundo a norma-padrão cobrada pela Cesgranrio é 'das 8h às 17h' ou 'de 8h a 17h'.",
                goldTip = "A Cesgranrio cobra muito o par 'DE X A Y' versus 'DA(S) X À(S) Y'! Grave esta simetria: se tem 'DA/DAS' no começo, tem 'À/ÀS' no fim ('da segunda à sexta-feira', 'das 9h às 18h'); se tem apenas 'DE' no começo, tem apenas 'A' sem crase no fim ('de segunda a sexta-feira', 'de 9h a 18h')!"
            )

            "tp_por_m10_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "Para resolver as questões mais difíceis de crase da Fundação Cesgranrio, é tão importante saber onde a crase é proibida quanto onde ela é obrigatória ou facultativa. Mais da metade das alternativas incorretas em provas da banca coloca o acento grave diante de verbos, palavras masculinas, pronomes indefinidos ou substantivos no plural precedidos de 'a' no singular.",
                theory = """
                    1. Os 7 Casos em que a Crase é ABSOLUTAMENTE PROIBIDA:
                    1) Antes de Palavra Masculina: "Pagamento a prazo", "Andar a pé", "Relatório feito a lápis" (salvo se subentender 'à moda de').
                    2) Antes de Verbo (no infinitivo): "A equipe começou a analisar os dados", "Preços a partir de R$ 100".
                    3) Antes de Pronomes em Geral (Pessoais, de Tratamento, Indefinidos, Interrogativos e Demonstrativos que não começam com 'a'):
                       - Proibido antes de: ela, mim, você, Vossa Excelência, Vossa Senhoria, alguém, ninguém, tudo, todo(a), cada, qualquer, algum(a), nenhum(a), outro(a), quem, cuja, esta, essa.
                       - Exceções que aceitam artigo: "senhora", "senhorita", "dona", "madame" e "mesma/própria".
                    4) Antes de Substantivo no PLURAL quando o "A" está no SINGULAR:
                       - Regra de Ouro da Cesgranrio: "A + Palavra no Plural = CRASE NEM A PAU!"
                       - Exemplo: "Refiro-me a cláusulas contratuais" (sem crase, pois não há artigo 'as') vs. "Refiro-me às cláusulas contratuais" (com crase, pois há preposição 'a' + artigo 'as').
                    5) Entre Palavras Repetidas (Locuções com termos idênticos): "gota a gota", "frente a frente", "cara a cara", "dia a dia", "ponto a ponto", "passo a passo".
                    6) Antes de Artigo Indefinido ("uma / umas"): "Chegamos a uma conclusão".
                    7) Após outra Preposição (para, com, desde, por, perante, após, entre, sob, sobre): "Compareceu perante a comissão" (exceção: a preposição ATÉ admite crase facultativa!).
                    
                    2. Os 3 Casos em que a Crase é FACULTATIVA ("A Minha Até Maria"):
                    1) Antes de Pronome Possessivo Feminino Adjetivo no SINGULAR (minha, tua, sua, nossa, vossa + substantivo expresso):
                       - "Entreguei o relatório a (ou à) minha gerente."
                       - Atenção! Se o possessivo estiver no PLURAL, vale a regra do 'a' vs. 'as': "a minhas gerentes" (sem crase) vs. "às minhas gerentes" (crase obrigatória!). E se o possessivo for pronome substantivo (sem substantivo depois: "Refiro-me à sua"), a crase é obrigatória!
                    2) Antes de Nome Próprio Feminino de Pessoa (Antropônimos):
                       - "Enviamos o ofício a (ou à) Mariana." (Se for personalidade histórica/pública em registro formal onde não cabe artigo familiar, evita-se a crase).
                    3) Depois da Preposição ATÉ (seguida de palavra feminina que aceite artigo):
                       - "Os inspetores foram até a (ou até à) plataforma."
                    
                    3. Casos Especiais: Topônimos (Nomes de Lugar), CASA, TERRA e DISTÂNCIA:
                    • Nomes de Lugares (Cidades, Estados, Países):
                      Use o verso clássico: "Se vou A e volto DA, crase HÁ! Se vou A e volto DE, crase PRA QUÊ?"
                      - "Vou à Bahia" (volto DA Bahia); "Vou a Curitiba" (volto DE Curitiba).
                      - Cuidado! Se o lugar vier ESPECIFICADO por adjunto adnominal, ocorre crase sempre: "Vou à Curitiba dos parques arborizados".
                    • Palavra CASA (sentido de lar/residência própria) e Palavra TERRA (sentido de chão firme, oposto a bordo/mar):
                      - Sem especificador: NÃO têm crase ("Retornaram a casa", "Os marinheiros desceram a terra").
                      - Com especificador: TÊM crase ("Retornaram à casa dos pais", "Voltaremos à terra natal").
                    • Palavra DISTÂNCIA:
                      - Sem especificação (indeterminada): SEM crase ("Monitoramento a distância", "Ensino a distância - EAD").
                      - Com especificação (determinada): COM crase ("O navio ancorou à distância de duzentos metros").
                """.trimIndent(),
                practicalApplication = "Em editais e documentos oficiais da Transpetro, a Cesgranrio explora exaustivamente o contraste entre 'Obedecer a normas técnicas' (generalização no plural sem artigo -> crase proibida!) e 'Obedecer às normas técnicas' (especificação com artigo 'as' -> crase obrigatória!), além de 'treinamento a distância' (sem crase) vs. 'posicionado à distância de 50 metros' (com crase).",
                summary = """
                    • "A" no singular + Palavra no plural = NUNCA tem crase ("a empresas", "a normas", "a todas as áreas")!
                    • Antes de verbo, palavra masculina, palavras repetidas (dia a dia) e pronomes (esta, cada, toda, qualquer, Vossa Senhoria, alguém) = PROIBIDO!
                    • 3 Casos Facultativos: (1) Possessivo feminino singular (minha/sua + subst.), (2) Nome próprio de mulher e (3) Após a preposição ATÉ.
                    • Casa, Terra e Distância: só têm crase quando vierem ESPECIFICADAS!
                """.trimIndent(),
                formulaName = "Regras de Bloqueio e Facultatividade da Crase",
                formulaSyntax = "[A (sing.) + Plural -> PROIBIDO] | [Antes de Verbo/Masc./Pron. Indef. -> PROIBIDO] | [Possessivo Fem. Sing. / Nome Mulher / Até -> FACULTATIVO]",
                formulaTerms = listOf(
                    "Regra do Plural: 'a + plural' (sem 's' no 'a') jamais recebe acento grave.",
                    "Pronomes Indefinidos (toda, cada, qualquer, alguma, nenhuma, outra): repelem artigo, logo proíbem crase!",
                    "Casa/Terra/Distância: Sem especificador = sem crase; Com especificador = com crase."
                ),
                formulaWhenToUse = "Quando a questão pedir onde o acento grave é proibido, facultativo ou se a inserção de um artigo/especificador passa a exigir crase.",
                exampleTitle = "Identificação de Crase Proibida vs. Facultativa em Prova",
                exampleProblem = "Analise o período: 'O memorando foi encaminhado a todas as gerências e entregue a nossa diretora, que retornou a Brasília após inspecionar os navios a distância.' Em qual dos quatro 'a' o uso do acento grave seria facultativo sem alterar a estrutura?",
                exampleSteps = listOf(
                    "Passo 1: Em 'a todas as gerências', 'todas' é pronome indefinido no plural e o 'a' está no singular -> crase proibida.",
                    "Passo 2: Em 'entregue a nossa diretora', 'nossa' é pronome possessivo adjetivo feminino no singular antecedido da preposição 'a' exigida por 'entregue'. Logo, a crase é FACULTATIVA ('a nossa diretora' ou 'à nossa diretora').",
                    "Passo 3: Em 'retornou a Brasília' (quem volta, volta DE Brasília -> crase proibida) e em 'a distância' (não especificada -> crase proibida)."
                ),
                exampleAnswer = "Apenas antes de 'nossa diretora' ('entregue a / à nossa diretora'), por se tratar de pronome possessivo feminino adjetivo no singular.",
                goldTip = "Olhe sempre se o 'a' está sem 's' antes de uma palavra no plural ('a pessoas', 'a situações', 'a diversas áreas'): na Cesgranrio, essa é a maneira mais rápida de eliminar 2 ou 3 alternativas erradas em menos de 10 segundos!"
            )

            // =====================================================================
            // MÓDULO 11 — PONTUAÇÃO
            // =====================================================================
            "tp_por_m11_a1" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "A pontuação no período simples é um dos pilares da prova da Fundação Cesgranrio. Toda a lógica da vírgula no período simples parte de uma regra absoluta: na ordem direta (Sujeito + Verbo + Complementos + Adjuntos), NÃO se usa vírgula entre os termos essenciais e integrantes. A vírgula serve para marcar deslocamentos, intercalações, enumerações, apostos explicativos e vocativos.",
                theory = """
                    1. As Três Proibições Absolutas da Vírgula (Ordem Direta SVC):
                    JAMAIS se usa uma única vírgula para separar:
                    1) O SUJEITO do VERBO — por mais extenso que seja o sujeito ou mesmo que o sujeito esteja posposto ao verbo!
                       - Errado: "A modernização constante dos terminais aquaviários e dutoviários da companhia, garantiu maior eficiência." (Separou o sujeito do verbo 'garantiu'!).
                    2) O VERBO de seus COMPLEMENTOS (Objeto Direto ou Objeto Indireto).
                       - Errado: "A diretoria informou aos coordenadores de logística, o novo cronograma."
                    3) O NOME de seu ADJUNTO ADNOMINAL ou COMPLEMENTO NOMINAL (ou o Predicativo do verbo de ligação).
                    *Atenção*: Entre o sujeito e o verbo (ou entre o verbo e o objeto) pode haver DUAS vírgulas isolando um termo intercalado (como um adjunto adverbial ou aposto), mas NUNCA uma única vírgula!
                    
                    2. Casos em que a Vírgula é OBRIGATÓRIA ou FACULTATIVA no Período Simples:
                    • Adjunto Adverbial Deslocado (anticipado para o início ou intercalado no meio da oração):
                      - Se for LONGO (3 ou mais palavras): a vírgula é OBRIGATÓRIA (ou fortemente recomendada pela Cesgranrio): "Durante a auditoria anual de conformidade, os fiscais validaram os estoques." / "Os fiscais, durante a auditoria anual, validaram os estoques."
                      - Se for CURTO (1 ou 2 palavras, ex.: 'Ontem', 'Hoje', 'No Brasil', 'Em 2025'): a vírgula é FACULTATIVA ("Ontem os fiscais validaram os estoques" ou "Ontem, os fiscais validaram os estoques").
                      - Se estiver no FINAL da frase (ordem direta/canônica): em regra NÃO leva vírgula (ou é facultativa apenas se houver ênfase/extensão).
                    • Aposto Explicativo (isola uma explicação equivalente ao substantivo anterior):
                      - Obrigatório usar vírgulas (ou travessões/parênteses): "A Transpetro, subsidiária da Petrobras, opera em todo o litoral."
                    • Vocativo (chamamento ao interlocutor):
                      - Sempre isolado por vírgula: "Senhores licitantes, enviem a documentação."
                    • Termos de Mesma Função Sintática em Enumeração:
                      - Separam-se por vírgula os elementos coordenados sem conjunção: "Inspecionamos válvulas, bombas, sensores e tanques."
                    • Elipse Verbal (Zêugma — omissão de verbo já expresso anteriormente):
                      - Usa-se vírgula no lugar do verbo omitido: "O setor administrativo revisou os contratos; o financeiro, as notas fiscais." (a vírgula após 'financeiro' substitui 'revisou').
                    • Datas e Endereços (Topônimo + Data):
                      - "Rio de Janeiro, 15 de outubro de 2025." / "Av. Presidente Vargas, 328."
                """.trimIndent(),
                practicalApplication = "Ao redigir relatórios executivos na Transpetro, períodos com sujeitos longos são muito comuns ('O cumprimento rigoroso das diretrizes de segurança operacional e meio ambiente pelas empresas contratadas...'). Resistir à tentação da pausa respiratória e NÃO colocar vírgula antes do verbo principal é a marca de um redator que domina a norma-padrão e gabarita a Cesgranrio.",
                summary = """
                    • NUNCA separe com 1 vírgula: [Sujeito] x [Verbo] x [Complementos]!
                    • Duas vírgulas entre Sujeito e Verbo são permitidas APENAS para isolar termo intercalado ("O diretor, ontem à tarde, assinou o ato").
                    • Adjunto Adverbial Deslocado: Curto = vírgula facultativa; Longo (3+ palavras) = vírgula obrigatória!
                    • Aposto Explicativo, Vocativo e Zêugma (omissão de verbo) = vírgula obrigatória!
                """.trimIndent(),
                formulaName = "Lei Áurea da Vírgula no Período Simples",
                formulaSyntax = "PROIBIDO: Sujeito [,] Verbo [,] Objeto | PERMITIDO: Sujeito [, Termo Intercalado ,] Verbo + Objeto",
                formulaTerms = listOf(
                    "Ordem Direta (SVO + Adjunto no fim): dispensa vírgula.",
                    "Adjunto Adverbial Deslocado Curto (1-2 palavras): vírgula facultativa ('Em 2026(,) a meta subiu').",
                    "Adjunto Adverbial Deslocado Longo (3+ palavras): vírgula obrigatória ('No início do segundo semestre fiscal, a meta subiu')."
                ),
                formulaWhenToUse = "Para julgar questões da Cesgranrio que pedem a frase pontuada corretamente ou a justificativa de uma vírgula no período simples.",
                exampleTitle = "Diagnóstico de Erro Clássico de Vírgula entre Sujeito e Verbo",
                exampleProblem = "Explique por que a frase (A) está incorreta e por que a frase (B) está correta: (A) 'O supervisor de logística, no final do turno, conferiu, todos os lacres.' (B) 'O supervisor de logística, no final do turno, conferiu todos os lacres.'",
                exampleSteps = listOf(
                    "Passo 1: Identificar a estrutura sintática: Sujeito = 'O supervisor de logística'; Verbo Transitivo Direto = 'conferiu'; Objeto Direto = 'todos os lacres'; Adjunto Adverbial de Tempo Intercalado = 'no final do turno'.",
                    "Passo 2: Na frase (A), as duas primeiras vírgulas isolam corretamente o adjunto intercalado 'no final do turno', mas a 3ª vírgula separa o verbo 'conferiu' de seu objeto direto 'todos os lacres', violando a regra áurea.",
                    "Passo 3: Na frase (B), apenas o adjunto intercalado está entre vírgulas, mantendo intacta a ligação entre sujeito, verbo e objeto direto."
                ),
                exampleAnswer = "A frase (A) está incorreta porque separa com vírgula o verbo transitivo direto ('conferiu') de seu objeto direto ('todos os lacres'); a frase (B) isola apenas o adjunto adverbial intercalado ('no final do turno'), estando perfeita.",
                goldTip = "Quando a Cesgranrio apresentar 5 frases longas e pedir a única com pontuação correta, faça o 'Raio-X do Verbo Principal': ache o verbo de cada alternativa e veja se há uma vírgula solitária imediatamente antes dele (separando-o do sujeito) ou imediatamente depois dele (separando-o do objeto). Você eliminará 3 alternativas na hora!"
            )

            "tp_por_m11_a2" -> TranspetroDidacticProvider.buildBundle(
                lessonId = lessonId,
                intro = "No período composto e no uso dos demais sinais de pontuação (ponto e vírgula, dois-pontos, travessão, parênteses e aspas), a Fundação Cesgranrio cobra a pontuação das orações subordinadas adverbiais e adjetivas, os casos de vírgula antes da conjunção 'e' e a substituição equivalente entre vírgulas, travessões e parênteses.",
                theory = """
                    1. A Vírgula no Período Composto (Subordinação e Coordenação):
                    • Orações Subordinadas Adverbiais (Temporais, Causais, Condicionais, Concessivas, Finais, Proporcionais):
                      - Funcionam exatamente como um grande adjunto adverbial!
                      - DESLOCADAS (antes da oração principal ou intercaladas no meio dela): a vírgula é OBRIGATÓRIA!
                        Exemplo: "Embora o prazo fosse curto, a equipe concluiu o inventário."
                      - NA ORDEM DIRETA (depois da oração principal): a vírgula é FACULTATIVA (em geral não se usa em causais, finais, temporais e condicionais curtas; costuma-se usar em concessivas pospostas).
                    • Orações Subordinadas Adjetivas:
                      - Restritivas: PROIBIDO usar vírgula! ("Os contratos que venceram foram renovados").
                      - Explicativas: OBRIGATÓRIO isolar por vírgulas (ou travessões/parênteses)! ("Os contratos, que já estavam vencidos, foram arquivados").
                    • Orações Subordinadas Substantivas:
                      - NUNCA se separam da oração principal por vírgula (pois funcionam como sujeito, objeto ou complemento nominal da principal!), salvo as apositivas (que vêm após dois-pontos ou vírgula).
                    • Orações Coordenadas e a Vírgula antes do "E":
                      - Separam-se por vírgula as coordenadas assindéticas e as sindéticas adversativas (mas, porém, contudo), conclusivas (logo, portanto), explicativas (pois, porque) e alternativas.
                      - Quando se usa vírgula ANTES da conjunção aditiva "E"?
                        1) Quando as duas orações ligadas pelo "e" têm SUJEITOS DIFERENTES ("O engenheiro elaborou o laudo, e o diretor o aprovou").
                        2) Quando o "e" tem valor ADVERSATIVO (= mas: "Esforçou-se muito, e não obteve êxito").
                        3) Polissíndeto (repetição enfática do "e": "E fiscaliza, e confere, e registra, e aprova").
                    
                    2. Outros Sinais de Pontuação Cobrados pela Cesgranrio:
                    • Dois-Pontos (:):
                      Introduzem: (a) uma enumeração explicativa; (b) um aposto explicativo ou oração subordinada substantiva apositiva; (c) uma citação direta; ou (d) uma explicação/consequência/síntese do que acabou de ser afirmado (podendo muitas vezes substituir um conectivo explicativo como 'pois/porque'!).
                    • Ponto e Vírgula (;):
                      1) Separa itens de uma enumeração complexa (especialmente quando os itens já contêm vírgulas internas ou em incisos de leis/editais).
                      2) Separa orações coordenadas de sentidos opostos ou simétricos que já possuem vírgula interna (como no zêugma: "Uns cuidam da frota marítima; outros, da malha de dutos").
                      3) Antes de conjunções adversativas ou conclusivas deslocadas ("O projeto é viável; requer, contudo, aprovação prévia").
                    • Travessão Duplo (— ... —) e Parênteses (( ... )):
                      Isolam apostos explicativos, orações adjetivas explicativas, comentários intercalados ou siglas.
                      - Equivalência na Cesgranrio: Um par de vírgulas que isola um termo explicativo ou intercalado pode ser substituído por um par de travessões ou por parênteses sem prejuízo gramatical (o travessão dá maior destaque/ênfase ao trecho intercalado; os parênteses dão tom de informação complementar/acessória).
                    • Aspas (" "):
                      Indicam citação literal/direta, neologismos, estrangeirismos, gírias, arcaísmos ou destacam palavras empregadas com ironia ou sentido figurado/conotativo no texto.
                """.trimIndent(),
                practicalApplication = "Na leitura e elaboração de cláusulas contratuais e relatórios técnicos da Transpetro, o uso de ponto e vírgula organiza enumerações de obrigações das partes, os dois-pontos introduzem sínteses conclusivas e os travessões destacam ressalvas importantes dentro de um parágrafo sem quebrar a fluidez sintática.",
                summary = """
                    • Oração Subordinada Adverbial ANTES da principal = vírgula OBRIGATÓRIA!
                    • Oração Subordinada Substantiva (iniciada por 'que/se' = ISSO) = PROIBIDO separar do verbo por vírgula!
                    • Vírgula antes do "E": correta quando os sujeitos são diferentes ou quando "e" equivale a "mas".
                    • Dois-pontos (:) introduzem aposto, enumeração, citação ou explicação/causa (= pois).
                    • Par de vírgulas isolando aposto/intercalação <-> Par de travessões <-> Parênteses.
                """.trimIndent(),
                formulaName = "Mapa de Equivalência e Pontuação no Período Composto",
                formulaSyntax = "[Or. Sub. Adverbial Anteposta] , [Or. Principal] | [, Explicação ,] == [— Explicação —] == [( Explicação )]",
                formulaTerms = listOf(
                    "Dois-Pontos (:): podem substituir 'pois / porque / visto que' quando introduzem a causa/explicação da oração anterior.",
                    "Travessão Simples no fim da frase: pode substituir uma vírgula (ou dois-pontos) antes de um aposto explicativo final.",
                    "Aspas: na Cesgranrio, quase sempre marcam citação de terceiro, estrangeirismo ou sentido figurado/irônico."
                ),
                formulaWhenToUse = "Quando a questão cobrar pontuação de períodos compostos, uso de vírgula antes do 'e', função das aspas/dois-pontos ou substituição de vírgulas por travessões/parênteses.",
                exampleTitle = "Equivalência entre Dois-Pontos, Travessões e Conectivos Explicativos",
                exampleProblem = "Considere o período: 'A automação das válvulas — medida prioritária do plano diretor — não pode atrasar: dela depende a segurança operacional.' Explique a função dos travessões e dos dois-pontos e indique por qual conectivo os dois-pontos poderiam ser substituídos.",
                exampleSteps = listOf(
                    "Passo 1: Analisar o trecho entre travessões '— medida prioritária do plano diretor —': trata-se de um aposto explicativo referente a 'A automação das válvulas'. Os travessões poderiam ser substituídos por um par de vírgulas ou parênteses.",
                    "Passo 2: Analisar a relação entre as duas orações separadas pelos dois-pontos: a segunda oração ('dela depende a segurança operacional') apresenta a causa/justificativa pela qual a automação não pode atrasar.",
                    "Passo 3: Concluir a equivalência: os dois-pontos têm valor explicativo/causal, equivalendo a ', pois' / ', porque' / ', visto que'."
                ),
                exampleAnswer = "Os travessões isolam um aposto explicativo (substituíveis por vírgulas ou parênteses); os dois-pontos introduzem uma explicação/causa, podendo ser substituídos por ', pois' ou ', visto que'.",
                goldTip = "A Cesgranrio adora perguntar: 'Os dois-pontos no trecho X introduzem uma...' — se a oração após os dois-pontos explica o motivo da anterior, marque 'explicação / justificativa / causa' (faça o teste mental trocando os dois-pontos por 'pois' ou 'porque')!"
            )

            else -> null
        }
    }

    fun getExercises(lessonId: String): List<MathExercise> {
        return when (lessonId) {
            // =====================================================================
            // EXERCÍCIOS — MÓDULO 1 (TP-POR-SUP-01 a TP-POR-SUP-04)
            // =====================================================================
            "tp_por_m1_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-01",
                    lessonId = lessonId,
                    statement = "Leia o fragmento de relatório corporativo a seguir:\n\n\"Os terminais aquaviários da Região Nordeste voltaram a registrar índice zero de acidentes operacionais no último trimestre, consolidando a eficácia do treinamento contínuo das equipes de convés e terra.\"\n\nCom base nos mecanismos de compreensão, inferência e pressuposição linguística, depreende-se corretamente do período que:",
                    options = listOf(
                        "A) os terminais da Região Nordeste jamais haviam sofrido acidentes operacionais antes do último trimestre.",
                        "B) o índice zero de acidentes já havia sido alcançado em algum período anterior ao último trimestre analisado.",
                        "C) o treinamento contínuo das equipes foi implantado pela primeira vez no início do último trimestre.",
                        "D) as demais regiões operacionais da companhia apresentaram aumento no número de acidentes no mesmo período.",
                        "E) a ocorrência de acidentes independe da capacitação técnica das equipes que atuam em terra."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Identificar o marcador linguístico de pressuposição na oração principal: a locução verbal 'voltaram a registrar'.",
                        "Analisar o valor semântico do verbo 'voltar a + infinitivo': indica a repetição ou retomada de um fato que já havia ocorrido anteriormente e que sofrera interrupção.",
                        "Concluir que, se os terminais 'voltaram a registrar' índice zero, pressupõe-se obrigatoriamente que esse índice zero já havia sido alcançado em momento anterior."
                    ),
                    finalComment = "Na Fundação Cesgranrio, locuções como 'voltar a', 'deixar de', 'passar a' e advérbios como 'já' e 'ainda' funcionam como gatilhos de pressuposição. As demais alternativas incorrem em contradição (A e E) ou extrapolação (C e D)."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-02",
                    lessonId = lessonId,
                    statement = "Considere o seguinte trecho argumentativo:\n\n\"Embora a digitalização dos processos de controle logístico reduza substancialmente a incidência de falhas humanas no apontamento de estoques, a supervisão analítica por profissionais qualificados permanece insubstituível na tomada de decisões estratégicas.\"\n\nA ideia central (tese) defendida pelo autor no período acima consiste em afirmar que:",
                    options = listOf(
                        "A) a digitalização dos processos logísticos tornou obsoleta a atuação de profissionais na supervisão de estoques.",
                        "B) as falhas humanas no apontamento de estoques são impossíveis de serem reduzidas por ferramentas digitais.",
                        "C) a tecnologia digital elimina integralmente a necessidade de decisões estratégicas no setor logístico.",
                        "D) a análise humana qualificada continua essencial nas decisões estratégicas, mesmo diante dos ganhos trazidos pela digitalização.",
                        "E) os investimentos em automação devem ser suspensos em favor da contratação exclusiva de supervisores."
                    ),
                    correctIndex = 3,
                    steps = listOf(
                        "Separar a oração subordinada concessiva ('Embora a digitalização... reduza...') da oração principal ('a supervisão analítica... permanece insubstituível...').",
                        "Lembrar que, em períodos compostos com oração concessiva, a tese ou argumento central do autor reside sempre na oração principal.",
                        "Verificar que a alternativa D sintetiza com exatidão a tese da oração principal articulada à ressalva concessiva."
                    ),
                    finalComment = "Em períodos introduzidos por 'Embora', o autor reconhece um argumento secundário na oração concessiva para reafirmar sua tese principal na segunda oração."
                )
            )

            "tp_por_m1_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-03",
                    lessonId = lessonId,
                    statement = "Leia o excerto retirado de um manual interno de segurança operacional:\n\n\"Antes de iniciar a transferência de derivados entre os tanques de armazenamento, verifique a calibração dos manômetros de linha, certifique-se do fechamento das válvulas de drenagem secundárias e registre o horário exato de abertura no sistema supervisório.\"\n\nQuanto à tipologia textual, o fragmento acima classifica-se predominantemente como:",
                    options = listOf(
                        "A) narrativo, pois relata uma sucessão de eventos passados vivenciados pelos operadores do terminal.",
                        "B) dissertativo-argumentativo, pois defende uma tese polêmica acerca da modernização dos tanques.",
                        "C) injuntivo, pois instrui e orienta o leitor passo a passo sobre como executar um procedimento técnico, empregando verbos no modo imperativo.",
                        "D) descritivo, pois se limita a retratar visualmente a estrutura física e as dimensões dos manômetros.",
                        "E) poético-conotativo, pois recorre a figuras de linguagem subjetivas para sensibilizar a equipe."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Observar a finalidade comunicativa do texto: orientar/prescrever ações operacionais obrigatórias.",
                        "Identificar a marca morfossintática predominante: o emprego dos verbos no modo imperativo ('verifique', 'certifique-se', 'registre').",
                        "Associar a prescrição de procedimentos com verbos no imperativo à tipologia injuntiva (ou instrucional)."
                    ),
                    finalComment = "Textos normativos, manuais operacionais e protocolos de segurança que prescrevem condutas com verbos no imperativo pertencem à tipologia injuntiva."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-04",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que o termo destacado foi empregado em sentido CONOTATIVO (figurado), configurando uma metáfora no contexto corporativo:",
                    options = listOf(
                        "A) Os dutos subterrâneos passam por inspeções periódicas de ultrassom para detecção de corrosão.",
                        "B) A transparência na prestação de contas é o alicerce sobre o qual se constrói a governança da companhia.",
                        "C) O relatório financeiro do segundo trimestre foi encaminhado à diretoria executiva dentro do prazo.",
                        "D) Os navios de cabotagem transportam grandes volumes de biocombustíveis ao longo da costa brasileira.",
                        "E) A conferência documental exige o confronto entre a nota fiscal eletrônica e o pedido de compra."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Distinguir sentido denotativo (literal, objetivo) de sentido conotativo (figurado, metafórico).",
                        "Nas alternativas A, C, D e E, todos os termos ('dutos subterrâneos', 'relatório financeiro', 'costa brasileira', 'nota fiscal') estão em sentido estritamente literal.",
                        "Na alternativa B, a palavra 'alicerce' (literalmente a base física de alvenaria/concreto de uma edificação) foi transferida por semelhança para qualificar um conceito abstrato ('a transparência'), configurando metáfora (sentido conotativo)."
                    ),
                    finalComment = "A Cesgranrio cobra com frequência a identificação de palavras empregadas em sentido figurado (conotativo) dentro de textos jornalísticos e corporativos."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 2 (TP-POR-SUP-05 a TP-POR-SUP-08)
            // =====================================================================
            "tp_por_m2_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-05",
                    lessonId = lessonId,
                    statement = "Analise o mecanismo de coesão referencial no período abaixo:\n\n\"O transporte hidroviário e o modal rodoviário desempenham papéis distintos na matriz logística nacional: este destaca-se pela flexibilidade na entrega porta a porta de cargas fracionadas, ao passo que aquele apresenta maior capacidade energética para movimentar grandes volumes a longas distâncias.\"\n\nDe acordo com a norma-padrão, os pronomes demonstrativos \"este\" e \"aquele\" retomam, respectivamente:",
                    options = listOf(
                        "A) o transporte hidroviário / o modal rodoviário.",
                        "B) o modal rodoviário / o transporte hidroviário.",
                        "C) a matriz logística nacional / o modal rodoviário.",
                        "D) o transporte hidroviário / a matriz logística nacional.",
                        "E) cargas fracionadas / grandes volumes."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Localizar os dois termos antecedentes coordenados na primeira oração: Termo 1 (mais distante) = 'O transporte hidroviário'; Termo 2 (mais próximo) = 'o modal rodoviário'.",
                        "Aplicar a regra de coesão referencial distributiva dos demonstrativos: 'este' retoma o referente mais próximo (o último citado = 'o modal rodoviário').",
                        "'Aquele' retoma o referente mais distante (o primeiro citado = 'O transporte hidroviário')."
                    ),
                    finalComment = "Quando dois referentes são mencionados anteriormente, 'este' retoma o último (mais próximo) e 'aquele' retoma o primeiro (mais distante)."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-06",
                    lessonId = lessonId,
                    statement = "Observe o emprego dos pronomes demonstrativos nos dois enunciados a seguir:\n\nI. \"A diretriz fundamental da área de conformidade é ESTA: assegurar a integridade em todas as contratações.\"\nII. \"Reduzir o tempo de espera nos terminais sem comprometer a segurança: ESSE é o desafio diário da operação.\"\n\nNos enunciados I e II, os pronomes destacados exercem, respectivamente, papel coesivo:",
                    options = listOf(
                        "A) anafórico e anafórico.",
                        "B) catafórico e catafórico.",
                        "C) catafórico e anafórico.",
                        "D) anafórico e catafórico.",
                        "E) dêitico espacial e elíptico."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Analisar o enunciado I: o pronome 'ESTA' anuncia e antecipa a oração que vem depois dos dois-pontos ('assegurar a integridade...'). Antecipação textual denomina-se catáfora.",
                        "Analisar o enunciado II: o pronome 'ESSE' retoma a ideia que já foi enunciada antes dos dois-pontos ('Reduzir o tempo de espera...'). Retomada textual denomina-se anáfora.",
                        "Concluir que a sequência correta é catafórico (I) e anafórico (II)."
                    ),
                    finalComment = "No interior do texto, 'este/esta/isto' aponta para a frente (catáfora), enquanto 'esse/essa/isso' retoma o que já foi dito (anáfora)."
                )
            )

            "tp_por_m2_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-07",
                    lessonId = lessonId,
                    statement = "No período \"Os custos de armazenagem diminuíram significativamente no semestre, PORQUANTO a implantação do endereçamento informatizado eliminou o retrabalho na separação de pedidos\", o conectivo destacado estabelece, em relação à oração anterior, uma relação lógico-semântica de:",
                    options = listOf(
                        "A) concessão, equivalendo a \"conquanto\".",
                        "B) oposição, equivalendo a \"todavia\".",
                        "C) condição, equivalendo a \"contanto que\".",
                        "D) finalidade, equivalendo a \"a fim de que\".",
                        "E) causa/explicação, equivalendo a \"visto que\" ou \"porque\"."
                    ),
                    correctIndex = 4,
                    steps = listOf(
                        "Analisar a relação lógica entre os fatos: a eliminação do retrabalho pela informatização foi o motivo (causa/explicação) pelo qual os custos diminuíram.",
                        "Reconhecer o valor semântico da conjunção 'porquanto': na norma-padrão, 'porquanto' é conjunção causal/explicativa, sinônima exata de 'porque', 'visto que' e 'já que'.",
                        "Evitar a confusão clássica com 'conquanto', que é concessiva (= embora)."
                    ),
                    finalComment = "'Porquanto' equivale a 'porque / visto que' (causa/explicação), enquanto 'conquanto' equivale a 'embora' (concessão)."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-08",
                    lessonId = lessonId,
                    statement = "Leia o período abaixo:\n\n\"À medida que os sensores de telemetria enviam dados em tempo real ao centro de controle, a precisão no monitoramento da pressão dos oleodutos aumenta.\"\n\nA locução conjuntiva \"À medida que\" exprime relação de:",
                    options = listOf(
                        "A) proporcionalidade, podendo ser substituída por \"À proporção que\".",
                        "B) conformidade, podendo ser substituída por \"Consoante\".",
                        "C) causa, podendo ser substituída por \"Já que\".",
                        "D) concessão, podendo ser substituída por \"Posto que\".",
                        "E) condição, podendo ser substituída por \"Desde que\"."
                    ),
                    correctIndex = 0,
                    steps = listOf(
                        "Verificar a relação entre as duas orações: o aumento da precisão ocorre simultânea e gradualmente ao envio de dados em tempo real.",
                        "Identificar a locução conjuntiva 'À medida que' como marcadora clássica de proporcionalidade.",
                        "Confirmar sua equivalência exata com 'À proporção que' ou 'Ao passo que'."
                    ),
                    finalComment = "'À medida que' expressa proporção (= à proporção que). Já 'na medida em que' expressa causa (= tendo em vista que / uma vez que)."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 3 (TP-POR-SUP-09 a TP-POR-SUP-12)
            // =====================================================================
            "tp_por_m3_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-09",
                    lessonId = lessonId,
                    statement = "Considere o período a seguir:\n\n\"Conquanto o cronograma de manutenção preventiva seja rigoroso, a disponibilidade da frota manteve-se acima da meta anual.\"\n\nAssinale a alternativa em que a reescrita da oração destacada mantém o sentido original de concessão e atende integralmente à norma-padrão:",
                    options = listOf(
                        "A) Caso o cronograma de manutenção preventiva seja rigoroso,",
                        "B) Apesar de o cronograma de manutenção preventiva ser rigoroso,",
                        "C) Na medida em que o cronograma de manutenção preventiva é rigoroso,",
                        "D) Embora o cronograma de manutenção preventiva é rigoroso,",
                        "E) Desde que o cronograma de manutenção preventiva fosse rigoroso,"
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Identificar o sentido original de 'Conquanto': conjunção subordinativa concessiva (= embora).",
                        "Eliminar A e E (condicionais) e C ('na medida em que' = causal).",
                        "Eliminar D porque a conjunção 'Embora' exige obrigatoriamente verbo no modo subjuntivo ('seja', e não 'é').",
                        "Confirmar B: a locução prepositiva concessiva 'Apesar de' exige verbo no infinitivo ('ser'), sem contrair a preposição 'de' com o artigo 'o' do sujeito ('Apesar de o cronograma... ser rigoroso')."
                    ),
                    finalComment = "A troca de 'Conquanto/Embora + subjuntivo' por 'Apesar de + infinitivo' (sem contração de 'de + o' antes do sujeito de infinitivo) é uma das questões mais recorrentes da Cesgranrio."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-10",
                    lessonId = lessonId,
                    statement = "No trecho \"Se a fornecedora mantiver os padrões de qualidade exigidos no edital, o contrato poderá ser prorrogado\", a substituição da conjunção \"Se\" por \"Caso\", mantendo-se a correção gramatical e o sentido condicional, exige a seguinte reescrita da primeira oração:",
                    options = listOf(
                        "A) Caso a fornecedora mantiver os padrões de qualidade exigidos no edital,",
                        "B) Caso a fornecedora manter os padrões de qualidade exigidos no edital,",
                        "C) Caso a fornecedora mantém os padrões de qualidade exigidos no edital,",
                        "D) Caso a fornecedora mantenha os padrões de qualidade exigidos no edital,",
                        "E) Caso a fornecedora manteria os padrões de qualidade exigidos no edital,"
                    ),
                    correctIndex = 3,
                    steps = listOf(
                        "Observar que a conjunção 'Se' indicando condição futura rege o futuro do subjuntivo ('mantiver').",
                        "Ao substituir 'Se' pela conjunção condicional 'Caso', a norma-padrão exige a mudança do verbo do futuro do subjuntivo para o presente do subjuntivo ('mantenha').",
                        "Portanto, a única forma gramaticalmente correta é 'Caso a fornecedora mantenha...'."
                    ),
                    finalComment = "'Se' pede futuro do subjuntivo ('Se mantiver / Se fizer'); 'Caso' pede presente do subjuntivo ('Caso mantenha / Caso faça')."
                )
            )

            "tp_por_m3_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-11",
                    lessonId = lessonId,
                    statement = "Em relatórios administrativos, o respeito ao paralelismo sintático e semântico é requisito da norma-padrão. Assinale a alternativa cuja redação apresenta pleno paralelismo sintático entre os termos coordenados:",
                    options = listOf(
                        "A) O novo plano diretor visa tanto à redução dos custos operacionais mas também modernizar a malha de dutos.",
                        "B) A auditoria recomendou a revisão dos contratos vigentes e que se atualizassem os cadastros de fornecedores.",
                        "C) Compete à gerência de suprimentos planejar as aquisições, homologar os fornecedores e monitorar os prazos de entrega.",
                        "D) O treinamento foi elaborado não só para técnicos de campo, quanto para analistas administrativos.",
                        "E) A comissão decidiu pela abertura de sindicância e arquivar os processos antigos."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Analisar a simetria das estruturas coordenadas em cada alternativa.",
                        "Em A e D, houve cruzamento incorreto de pares correlatos ('tanto... mas também' e 'não só... quanto').",
                        "Em B e E, misturaram-se substantivos ('a revisão', 'pela abertura') com orações desenvolvidas ou verbos no infinitivo ('que se atualizassem', 'arquivar').",
                        "Em C, os três complementos de 'Compete à gerência' estão perfeitamente simétricos no infinitivo verbal: 'planejar..., homologar... e monitorar...'."
                    ),
                    finalComment = "O paralelismo sintático exige que elementos de mesma função em uma série coordenada apresentem estrutura gramatical idêntica (todos no infinitivo ou todos como sintagmas nominais)."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-12",
                    lessonId = lessonId,
                    statement = "Observe a frase abaixo, que apresenta ambiguidade estrutural:\n\n\"A coordenadora conversou com a analista de compras em sua sala após a reunião.\"\n\nAssinale a reescrita que elimina a ambiguidade do pronome possessivo, deixando claro que a conversa ocorreu na sala pertencente à analista de compras, de acordo com a norma-padrão:",
                    options = listOf(
                        "A) Após a reunião, a coordenadora conversou na sua sala com a analista de compras.",
                        "B) A coordenadora, em sua sala, conversou com a analista de compras após a reunião.",
                        "C) A coordenadora conversou com a analista de compras, após a reunião, na sala daquela.",
                        "D) A coordenadora conversou com a analista de compras, após a reunião, na sala desta.",
                        "E) Em sua própria sala, a coordenadora conversou com a analista de compras após a reunião."
                    ),
                    correctIndex = 3,
                    steps = listOf(
                        "Identificar os dois antecedentes femininos na oração: Termo 1 (mais distante) = 'A coordenadora'; Termo 2 (mais próximo) = 'a analista de compras'.",
                        "Aplicar a regra dos demonstrativos para desambiguar a posse: 'daquela' refere-se à primeira mencionada ('A coordenadora'), enquanto 'desta' refere-se à última mencionada ('a analista de compras').",
                        "Como o comando pede que a sala pertença à analista de compras (a mais próxima), o emprego de 'na sala desta' resolve a ambiguidade com precisão."
                    ),
                    finalComment = "Para desfazer a ambiguidade do possessivo 'seu/sua' diante de dois antecedentes, utiliza-se 'deste(a)' para o último termo mencionado e 'daquele(a)' para o primeiro."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 4 (TP-POR-SUP-13 a TP-POR-SUP-16)
            // =====================================================================
            "tp_por_m4_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-13",
                    lessonId = lessonId,
                    statement = "Preencha corretamente as lacunas do despacho administrativo abaixo de acordo com a ortografia oficial:\n\n\"A comissão de sindicância esclareceu o ______ de ter recomendado ______ a cláusula contratual que continha erro material de cálculo, expondo as razões ______ a medida deve ser adotada antes do fechamento contábil.\"",
                    options = listOf(
                        "A) porquê — retificar — por que",
                        "B) por que — ratificar — porque",
                        "C) porquê — ratificar — por quê",
                        "D) porque — retificar — por que",
                        "E) por quê — retificar — porque"
                    ),
                    correctIndex = 0,
                    steps = listOf(
                        "1ª lacuna ('o ______'): precedido do artigo definido 'o', o termo está substantivado e equivale a 'o motivo / a razão' -> grafa-se junto e com acento ('porquê').",
                        "2ª lacuna: como a cláusula continha 'erro material de cálculo', a recomendação é de corrigir o erro -> 'retificar' ('ratificar' significa confirmar).",
                        "3ª lacuna ('as razões ______'): equivale a 'as razões pelas quais' -> grafa-se separado e sem acento ('por que')."
                    ),
                    finalComment = "'O porquê' é substantivo (= o motivo); 'retificar' significa corrigir; 'por que' equivale a 'pelas quais'."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-14",
                    lessonId = lessonId,
                    statement = "Segundo as regras vigentes do Acordo Ortográfico da Língua Portuguesa relativas ao emprego do hífen e à grafia das palavras, assinale a alternativa em que TODOS os vocábulos estão grafados corretamente:",
                    options = listOf(
                        "A) infraestrutura — autoavaliação — micro-ondas — antissocial",
                        "B) infra-estrutura — auto-avaliação — microondas — anti-social",
                        "C) infraestrutura — auto-avaliação — micro-ondas — anti-social",
                        "D) infra-estrutura — autoavaliação — microondas — antissocial",
                        "E) infraestrutura — autoavaliação — microondas — antissocial"
                    ),
                    correctIndex = 0,
                    steps = listOf(
                        "Analisar 'infraestrutura' e 'autoavaliação': o prefixo termina em vogal ('a' e 'o') e o segundo elemento começa com vogal diferente ('e' e 'a') -> juntam-se SEM hífen.",
                        "Analisar 'micro-ondas': o prefixo termina com a mesma vogal ('o') com que se inicia o segundo elemento ('o') -> separam-se COM hífen.",
                        "Analisar 'antissocial': o prefixo termina em vogal ('i') e o segundo elemento começa com 's' -> dobra-se o 's' SEM hífen."
                    ),
                    finalComment = "Vogais diferentes juntam-se ('infraestrutura', 'autoavaliação'); vogais iguais separam-se com hífen ('micro-ondas', 'anti-inflamatório'); vogal + R/S dobra a consoante ('antissocial', 'autorregulação')."
                )
            )

            "tp_por_m4_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-15",
                    lessonId = lessonId,
                    statement = "De acordo com as regras de acentuação gráfica da Língua Portuguesa, assinale a alternativa em que as duas palavras recebem acento gráfico exatamente pela MESMA regra:",
                    options = listOf(
                        "A) país — armazém",
                        "B) saúde — concluíram",
                        "C) relatório — trânsito",
                        "D) caráter — você",
                        "E) baú — jacaré"
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Analisar A e E: 'país' (pa-ís) e 'baú' (ba-ú) são acentuados pela regra do HIATO ('i' e 'u' tônicos formando hiato com a vogal anterior), enquanto 'armazém' e 'jacaré' são oxítonas.",
                        "Analisar C: 'relatório' é paroxítona terminada em ditongo crescente; 'trânsito' é proparoxítona.",
                        "Analisar D: 'caráter' é paroxítona terminada em -r; 'você' é oxítona terminada em -e.",
                        "Confirmar B: 'saúde' (sa-ú-de) e 'concluíram' (con-clu-í-ram) recebem acento agudo pela mesma regra especial: 'i' e 'u' tônicos que formam hiato com a vogal anterior, isolados na sílaba."
                    ),
                    finalComment = "A Cesgranrio cobra insistentemente a regra do hiato ('saúde', 'concluíram', 'país', 'baú', 'faísca'), tentando confundi-la com as regras das oxítonas e paroxítonas."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-16",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que a acentuação gráfica das formas verbais atende integralmente às normas do Acordo Ortográfico vigente:",
                    options = listOf(
                        "A) Os novos sistemas de controle mantém a pressão estável e prevêem eventuais oscilações.",
                        "B) Os novos sistemas de controle mantêm a pressão estável e preveem eventuais oscilações.",
                        "C) Os novos sistemas de controle mantêem a pressão estável e prevêm eventuais oscilações.",
                        "D) Os novos sistemas de controle mantêm a pressão estável e prevêem eventuais oscilações.",
                        "E) Os novos sistemas de controle mantém a pressão estável e preveem eventuais oscilações."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Identificar o sujeito composto/plural: 'Os novos sistemas de controle' (3ª pessoa do plural).",
                        "Conferir o verbo 'manter' (derivado de 'ter'): na 3ª pessoa do singular grafa-se 'mantém' (acento agudo); na 3ª pessoa do plural grafa-se 'mantêm' (acento circunflexo diferencial).",
                        "Conferir o verbo 'prever' (derivado de 'ver'): assim como 'creem, deem, leem, veem', a terminação '-eem' perdeu o acento circunflexo no Novo Acordo, grafando-se 'preveem'."
                    ),
                    finalComment = "Os derivados de 'ter' e 'vir' levam circunflexo no plural ('mantêm', 'intervêm'), enquanto 'crer, dar, ler, ver' e seus derivados dobram o 'e' sem acento ('preveem', 'leem', 'veem')."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 5 (TP-POR-SUP-17 a TP-POR-SUP-20)
            // =====================================================================
            "tp_por_m5_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-17",
                    lessonId = lessonId,
                    statement = "Analise a classe morfológica das palavras destacadas nos dois períodos abaixo:\n\nI. \"As equipes de inspeção atuaram RÁPIDO para isolar a válvula.\"\nII. \"O terminal recebeu um RÁPIDO atendimento da brigada técnica.\"\n\nNos períodos I e II, o vocábulo \"rápido\" classifica-se, respectivamente, como:",
                    options = listOf(
                        "A) adjetivo e adjetivo.",
                        "B) advérbio e advérbio.",
                        "C) advérbio (adjetivo adverbializado) e adjetivo.",
                        "D) adjetivo e substantivo.",
                        "E) substantivo e advérbio."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "No período I, 'rápido' modifica o verbo 'atuaram', indicando o modo como as equipes atuaram (= rapidamente) e mantendo-se invariável no masculino singular -> funciona como advérbio de modo.",
                        "No período II, 'rápido' qualifica o substantivo masculino 'atendimento' -> funciona como adjetivo.",
                        "Portanto, a classificação correta é advérbio em I e adjetivo em II."
                    ),
                    finalComment = "Quando um adjetivo modifica um verbo e permanece invariável (equivalendo a um advérbio em '-mente'), ocorre derivação imprópria (adjetivo adverbializado)."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-18",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que a flexão de plural dos substantivos e adjetivos compostos respeita integralmente a norma-padrão da Língua Portuguesa:",
                    options = listOf(
                        "A) relatórios técnicos-operacionais — guardas-chuvas",
                        "B) relatórios técnico-operacionais — guarda-chuvas",
                        "C) relatórios técnicos-operacionais — guarda-chuvas",
                        "D) relatórios técnico-operacional — guardas-chuva",
                        "E) relatórios técnico-operacionais — guardas-chuvas"
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Analisar 'técnico-operacionais': trata-se de um adjetivo composto que qualifica 'relatórios'. Nos adjetivos compostos, varia apenas o último elemento ('técnico-operacionais').",
                        "Analisar 'guarda-chuvas': trata-se de substantivo composto formado por Verbo ('guardar') + Substantivo ('chuva'). Como verbo é invariável em número nesse tipo de composição, apenas o segundo elemento vai para o plural ('guarda-chuvas')."
                    ),
                    finalComment = "Em adjetivos compostos ('técnico-operacionais', 'econômico-financeiros'), só o último elemento vai para o plural; em substantivos compostos formados por Verbo + Substantivo ('guarda-chuvas'), só o substantivo varia."
                )
            )

            "tp_por_m5_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-19",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que a substituição do termo destacado pelo pronome oblíquo átono e sua colocação na frase estão em total conformidade com a norma-padrão:",
                    options = listOf(
                        "A) Os engenheiros não encontraram OS LAUDOS TÉCNICOS no arquivo -> Os engenheiros não encontraram-nos no arquivo.",
                        "B) A diretoria vai submeter O PROJETO à aprovação do conselho -> A diretoria vai submetê-lo à aprovação do conselho.",
                        "C) Os fiscais entregaram AS NOTIFICAÇÕES ontem -> Os fiscais entregaram-as ontem.",
                        "D) Nunca enviaram OS RELATÓRIOS ao setor contábil -> Nunca enviaram-os ao setor contábil.",
                        "E) Se faz necessário revisar O CONTRATO -> Se faz necessário revisá-lo."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Eliminar A e D: há palavras negativas ('não' e 'nunca') antes do verbo, o que torna obrigatória a próclise ('não os encontraram', 'Nunca os enviaram').",
                        "Eliminar C: após forma verbal terminada em som nasal ('entregaram'), o pronome 'as' assume a forma '-nas' ('entregaram-nas').",
                        "Eliminar E: é proibido iniciar período com o pronome oblíquo átono 'Se' ('Faz-se necessário').",
                        "Confirmar B: 'submeter' termina em '-r'; ao receber o pronome 'o' em ênclise (sem fator atrativo antes do infinitivo), perde o '-r' e recebe acento agudo por ser oxítona terminada em '-e' ('submetê-lo')."
                    ),
                    finalComment = "Verbos terminados em -r, -s, -z perdem a consoante final antes de o/a/os/as, convertendo o pronome em lo/la/los/las."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-20",
                    lessonId = lessonId,
                    statement = "Segundo as regras de colocação pronominal exigidas pela Fundação Cesgranrio, o emprego da PRÓCLISE é obrigatório na seguinte frase:",
                    options = listOf(
                        "A) Realizar-se-ão amanhã as vistorias nos tanques de tancagem.",
                        "B) Entregaram-me todas as planilhas de controle orçamentário.",
                        "C) Os auditores já se manifestaram favoravelmente à homologação do certame.",
                        "D) Atualmente, busca-se maior eficiência energética no transporte marítimo.",
                        "E) Cumprimentamo-los pelo excelente desempenho na auditoria externa."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Analisar A: verbo no futuro do presente em início de frase (mesóclise obrigatória: 'Realizar-se-ão').",
                        "Analisar B e E: verbo em início de período (ênclise obrigatória).",
                        "Analisar D: o advérbio 'Atualmente' está isolado por vírgula; a pausa anula a força atrativa do advérbio, exigindo ênclise ('busca-se').",
                        "Confirmar C: o advérbio 'já' antecede imediatamente o verbo sem pausa/vírgula, funcionando como palavra atrativa que torna obrigatória a próclise ('já se manifestaram')."
                    ),
                    finalComment = "Advérbios ('já', 'sempre', 'não', 'ainda') sem vírgula antes do verbo exercem atração obrigatória para a próclise."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 6 (TP-POR-SUP-21 a TP-POR-SUP-24)
            // =====================================================================
            "tp_por_m6_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-21",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que a conjugação dos verbos irregulares e a correlação temporal entre as orações estão integralmente corretas segundo a norma-padrão:",
                    options = listOf(
                        "A) Se o coordenador intervir na negociação e a equipe manter o foco, o acordo será assinado.",
                        "B) Quando a diretoria propor as novas metas, os supervisores reverão os cronogramas.",
                        "C) Se o coordenador intervier na negociação e a equipe mantiver o foco, o acordo será assinado.",
                        "D) Caso o fiscal detesse a embarcação ontem, o atraso teria sido maior.",
                        "E) Os técnicos já reouveram os dados que se perderam quando o servidor interviu na rede."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Lembrar a regra dos verbos derivados: 'intervir' conjuga-se igual a 'vir'; 'manter' e 'deter' conjugam-se igual a 'ter'; 'propor' conjuga-se igual a 'pôr'.",
                        "No futuro do subjuntivo (após 'Se' ou 'Quando'): 'Se ele intervier' (e não 'intervir'), 'Se ela mantiver' (e não 'manter'), 'Quando ela propuser' (e não 'propor').",
                        "No pretérito imperfeito do subjuntivo de 'deter': 'detivesse' (e não 'detesse'); no pretérito perfeito de 'intervir': 'interveio' (e não 'interviu').",
                        "Portanto, a alternativa C apresenta conjugação e correlação verbal ('intervier' + 'mantiver' -> 'será assinado') perfeitas."
                    ),
                    finalComment = "Os verbos derivados de 'ter', 'vir', 'pôr' e 'ver' mantêm rigorosamente a irregularidade dos verbos primitivos em todos os tempos do subjuntivo e do pretérito."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-22",
                    lessonId = lessonId,
                    statement = "No trecho \"Quando a equipe de manutenção chegou à estação de bombeamento, o sistema automático de segurança já interrompera o fluxo do duto\", a forma verbal destacada (\"interrompera\") está flexionada no:",
                    options = listOf(
                        "A) futuro do presente do indicativo, indicando uma ação que ocorrerá após a chegada da equipe.",
                        "B) pretérito mais-que-perfeito do indicativo, indicando uma ação passada concluída antes de outra ação também passada (\"chegou\").",
                        "C) pretérito imperfeito do indicativo, indicando uma ação habitual e contínua no presente.",
                        "D) futuro do pretérito do indicativo, expressando uma hipótese não realizada.",
                        "E) pretérito perfeito do subjuntivo, expressando dúvida quanto ao fechamento da válvula."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Observar a desinência modo-temporal '-ra' átona em 'interrompera' (equivalente à forma composta 'tinha interrompido' / 'havia interrompido').",
                        "Verificar a linha do tempo dos dois eventos no passado: primeiro o sistema interrompeu o fluxo; depois a equipe chegou.",
                        "Concluir que se trata do pretérito mais-que-perfeito do indicativo, cuja função clássica é expressar um fato passado anterior a outro fato também passado."
                    ),
                    finalComment = "Na Cesgranrio, formas verbais terminadas em '-ra' (sem acento, como 'interrompera', 'iniciara', 'fizera') pertencem ao pretérito mais-que-perfeito do indicativo (= tinha/havia + particípio)."
                )
            )

            "tp_por_m6_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-23",
                    lessonId = lessonId,
                    statement = "Considere a frase na voz ativa:\n\n\"A gerência de logística tinha implementado novos controles de inventário nos armazéns.\"\n\nAo transpor essa frase para a voz passiva analítica, mantendo-se rigorosamente o mesmo tempo e modo verbal, obtém-se:",
                    options = listOf(
                        "A) Novos controles de inventário foram implementados nos armazéns pela gerência de logística.",
                        "B) Novos controles de inventário tinham sido implementados nos armazéns pela gerência de logística.",
                        "C) Novos controles de inventário seriam implementados nos armazéns pela gerência de logística.",
                        "D) Novos controles de inventário estavam sendo implementados nos armazéns pela gerência de logística.",
                        "E) Novos controles de inventário hão de ser implementados nos armazéns pela gerência de logística."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Identificar o objeto direto da voz ativa: 'novos controles de inventário' (masculino plural), que passará a ser o sujeito paciente da voz passiva.",
                        "Analisar a locução verbal da voz ativa: 'tinha implementado' (pretérito mais-que-perfeito composto do indicativo, com 2 verbos).",
                        "Converter para a passiva analítica (3 verbos, concordando no plural com o novo sujeito): 'tinham sido implementados'.",
                        "Converter o sujeito da ativa ('A gerência de logística') em agente da passiva ('pela gerência de logística')."
                    ),
                    finalComment = "Na transposição de 'tinha implementado' (2 verbos) para a voz passiva analítica com sujeito plural, o auxiliar vai para o plural e acrescenta-se 'sido': 'tinham sido implementados' (3 verbos)."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-24",
                    lessonId = lessonId,
                    statement = "A frase \"Foram homologados os relatórios técnicos de inspeção\" encontra-se na voz passiva analítica. Sua conversão correta para a voz passiva sintética (pronominal), mantendo-se o mesmo tempo verbal (pretérito perfeito do indicativo), é:",
                    options = listOf(
                        "A) Homologou-se os relatórios técnicos de inspeção.",
                        "B) Homologaram-se os relatórios técnicos de inspeção.",
                        "C) Homologam-se os relatórios técnicos de inspeção.",
                        "D) Homologar-se-ão os relatórios técnicos de inspeção.",
                        "E) Homologava-se os relatórios técnicos de inspeção."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Identificar o sujeito paciente: 'os relatórios técnicos de inspeção' (3ª pessoa do plural).",
                        "Verificar o tempo verbal de 'Foram homologados': pretérito perfeito do indicativo.",
                        "Flexionar o verbo transitivo direto 'homologar' no pretérito perfeito do indicativo na 3ª pessoa do plural, acompanhado da partícula apassivadora 'se': 'Homologaram-se os relatórios técnicos de inspeção'."
                    ),
                    finalComment = "Na voz passiva sintética, o verbo transitivo direto concorda obrigatoriamente em número e pessoa com o sujeito paciente ('Homologaram-se os relatórios')."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 7 (TP-POR-SUP-25 a TP-POR-SUP-28)
            // =====================================================================
            "tp_por_m7_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-25",
                    lessonId = lessonId,
                    statement = "Considere os dois enunciados abaixo:\n\nI. \"A auditoria DO TRIBUNAL apontou melhorias nos controles internos.\"\nII. \"A auditoria DAS CONTAS ANUAIS foi concluída dentro do prazo legal.\"\n\nDo ponto de vista sintático, os termos preposicionados destacados em I e II exercem, respectivamente, a função de:",
                    options = listOf(
                        "A) complemento nominal e adjunto adnominal.",
                        "B) adjunto adnominal e complemento nominal.",
                        "C) objeto indireto e objeto indireto.",
                        "D) adjunto adnominal e adjunto adnominal.",
                        "E) complemento nominal e complemento nominal."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Observar que ambos os termos ('do tribunal' e 'das contas anuais') estão ligados ao substantivo abstrato 'auditoria'.",
                        "Em I ('auditoria do tribunal'), o tribunal pratica a ação de auditar (valor ativo/agente) -> classifica-se como Adjunto Adnominal.",
                        "Em II ('auditoria das contas anuais'), as contas anuais sofrem a ação de serem auditadas (valor passivo/paciente) -> classifica-se como Complemento Nominal."
                    ),
                    finalComment = "Ligado a substantivo abstrato, o termo preposicionado com 'de' que tem sentido ativo (agente) é Adjunto Adnominal; o que tem sentido passivo (paciente) é Complemento Nominal."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-26",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa cuja oração apresenta classificação de \"oração sem sujeito\" (verbo impessoal), segundo a norma-padrão:",
                    options = listOf(
                        "A) Existem diversos projetos de expansão nos terminais marítimos.",
                        "B) Precisa-se de operadores de movimentação e armazenagem.",
                        "C) Houve importantes reuniões estratégicas na sede da companhia.",
                        "D) Analisaram-se todas as propostas técnicas do pregão.",
                        "E) Chegaram os novos sensores de monitoramento de vazão."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Analisar A, D e E: possuem sujeito simples expresso posposto ao verbo ('diversos projetos', 'todas as propostas técnicas', 'os novos sensores').",
                        "Analisar B: 'Precisa-se de operadores' tem verbo transitivo indireto + índice de indeterminação do sujeito ('se'), configurando sujeito indeterminado (e não oração sem sujeito).",
                        "Confirmar C: o verbo 'haver' empregado no sentido de 'existir' ou 'ocorrer' ('Houve importantes reuniões') é impessoal, formando oração sem sujeito (onde 'importantes reuniões estratégicas' funciona como objeto direto)."
                    ),
                    finalComment = "O verbo 'haver' no sentido de existir/acontecer gera oração sem sujeito; já 'existir' e 'ocorrer' possuem sujeito normal."
                )
            )

            "tp_por_m7_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-27",
                    lessonId = lessonId,
                    statement = "Compare os dois períodos a seguir:\n\nI. \"Os dutos que transportam gás natural passaram por inspeção robotizada.\"\nII. \"Os dutos, que transportam gás natural, passaram por inspeção robotizada.\"\n\nSobre o sentido e a estrutura sintática dos períodos I e II, é correto afirmar que:",
                    options = listOf(
                        "A) ambos os períodos possuem exatamente o mesmo sentido, sendo o uso das vírgulas em II meramente estilístico.",
                        "B) em I, infere-se que todos os dutos existentes transportam gás natural e passaram por inspeção.",
                        "C) em I, a ausência de vírgulas caracteriza uma oração adjetiva restritiva, indicando que apenas os dutos que transportam gás natural passaram pela inspeção robotizada.",
                        "D) em II, a presença de vírgulas torna a frase gramaticalmente incorreta segundo a norma-padrão.",
                        "E) o vocábulo \"que\", em ambos os períodos, classifica-se como conjunção integrante introdutora de oração substantiva."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Identificar o pronome relativo 'que' (= os quais) introduzindo a oração subordinada adjetiva 'que transportam gás natural'.",
                        "No período I (sem vírgulas), a oração é subordinada adjetiva RESTRITIVA: restringe o universo de 'Os dutos' apenas àqueles que transportam gás natural.",
                        "No período II (com vírgulas), a oração é subordinada adjetiva EXPLICATIVA: generaliza que a totalidade dos dutos mencionados transporta gás natural e passou por inspeção."
                    ),
                    finalComment = "A oposição semântica entre oração adjetiva restritiva (sem vírgula = parte do grupo) e explicativa (com vírgula = todo o grupo) é presença constante nas provas da Cesgranrio."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-28",
                    lessonId = lessonId,
                    statement = "No período \"É imprescindível QUE TODAS AS EQUIPES CUMPRAM OS PROTOCOLOS DE SEGURANÇA\", a oração subordinada destacada exerce, em relação à oração principal, a função sintática de:",
                    options = listOf(
                        "A) objeto direto (oração subordinada substantiva objetiva direta).",
                        "B) sujeito (oração subordinada substantiva subjetiva).",
                        "C) complemento nominal (oração subordinada substantiva completiva nominal).",
                        "D) predicativo do sujeito (oração subordinada substantiva predicativa).",
                        "E) adjunto adnominal (oração subordinada adjetiva restritiva)."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Substituir toda a oração iniciada pela conjunção integrante 'que' pelo pronome demonstrativo 'ISSO': 'É imprescindível ISSO' (ou, na ordem direta: 'ISSO é imprescindível').",
                        "Analisar a estrutura da oração principal: verbo de ligação 'É' + predicativo do sujeito 'imprescindível'. O que é imprescindível? 'ISSO' (a oração inteira!).",
                        "Concluir que a oração subordinada funciona como sujeito da oração principal, classificando-se como oração subordinada substantiva subjetiva."
                    ),
                    finalComment = "Estruturas impessoais na principal do tipo 'É necessário / É fundamental / É imprescindível / Convém / Importa + [que...]' introduzem sempre uma oração subordinada substantiva subjetiva."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 8 (TP-POR-SUP-29 a TP-POR-SUP-32)
            // =====================================================================
            "tp_por_m8_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-29",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que a concordância nominal das palavras destacadas atende integralmente à norma-padrão cobrada pela Fundação Cesgranrio:",
                    options = listOf(
                        "A) Seguem ANEXO as planilhas orçamentárias que as PRÓPRIAS analistas elaboraram.",
                        "B) Seguem ANEXAS as planilhas orçamentárias que as PRÓPRIAS analistas elaboraram.",
                        "C) É PROIBIDO a entrada de veículos não autorizados no pátio de estocagem.",
                        "D) Havia MENAS solicitações de compra no sistema, mas a supervisora ficou MEIA preocupada.",
                        "E) Foram juntados BASTANTE documentos comprovando que as empresas estavam QUITE com o fisco."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Analisar A e B: o adjetivo 'anexo' e o pronome 'próprio' concordam em gênero e número com os substantivos femininos plurais ('as planilhas... anexas', 'as próprias analistas'). Logo, B está correta.",
                        "Analisar C: como o substantivo 'entrada' está determinado pelo artigo feminino 'a' ('a entrada'), a expressão exige concordância no feminino ('É proibida a entrada').",
                        "Analisar D: 'menos' é sempre invariável ('menas' não existe) e 'meio' antes de adjetivo ('preocupada') é advérbio invariável ('meio preocupada').",
                        "Analisar E: 'bastantes documentos' (equivale a 'muitos documentos', variável) e 'quites com o fisco' (plural)."
                    ),
                    finalComment = "'Anexo', 'próprio', 'mesmo' (= em pessoa) e 'quite' flexionam-se normalmente com o substantivo a que se referem."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-30",
                    lessonId = lessonId,
                    statement = "Preencha as lacunas abaixo de acordo com as regras de concordância nominal:\n\n\"Ainda existem ______ razões técnicas para que os operadores permaneçam ______ durante a manobra, visto que é ______ cautela máxima na operação de válvulas que ficaram ______ desgastadas.\"",
                    options = listOf(
                        "A) bastantes — alerta — necessário — meio",
                        "B) bastante — alertas — necessária — meia",
                        "C) bastantes — alertas — necessário — meia",
                        "D) bastante — alerta — necessária — meio",
                        "E) bastantes — alerta — necessária — meia"
                    ),
                    correctIndex = 0,
                    steps = listOf(
                        "1ª lacuna ('______ razões técnicas'): modifica o substantivo plural 'razões', equivalendo a 'muitas razões' -> 'bastantes'.",
                        "2ª lacuna ('permaneçam ______'): a palavra 'alerta' no sentido de 'em estado de atenção' funciona como advérbio e é invariável -> 'alerta'.",
                        "3ª lacuna ('é ______ cautela máxima'): o substantivo feminino 'cautela' não está antecedido por artigo 'a' nem pronome -> fica invariável no masculino ('é necessário cautela').",
                        "4ª lacuna ('______ desgastadas'): modifica o adjetivo/particípio 'desgastadas' (= um pouco desgastadas), funcionando como advérbio de intensidade invariável -> 'meio'."
                    ),
                    finalComment = "Esse exercício reúne os quatro casos especiais mais cobrados em provas: 'bastantes' (= muitas), 'alerta' (invariável), 'é necessário' (sem artigo = masculino) e 'meio' (= um pouco, invariável)."
                )
            )

            "tp_por_m8_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-31",
                    lessonId = lessonId,
                    statement = "De acordo com as regras de concordância verbal da norma-padrão exigidas pela Fundação Cesgranrio, assinale a alternativa CORRETA:",
                    options = listOf(
                        "A) A implementação de novos protocolos digitais de controle de acesso nos terminais marítimos reduziram o tempo de fila.",
                        "B) Devem haver soluções tecnológicas mais econômicas para o rastreamento de cargas no almoxarifado.",
                        "C) Tratam-se de diretrizes estratégicas estabelecidas pelo conselho de administração da estatal.",
                        "D) Exigem-se comprovantes atualizados de regularidade fiscal e trabalhista para a homologação do contrato.",
                        "E) Fazem cinco anos que o sistema integrado de gestão empresarial foi atualizado na filial."
                    ),
                    correctIndex = 3,
                    steps = listOf(
                        "Eliminar A: o núcleo do sujeito é 'A implementação' (singular); portanto, o verbo deve ficar no singular ('reduziu').",
                        "Eliminar B: o verbo 'haver' no sentido de existir é impessoal e transmite sua impessoalidade ao auxiliar ('Deve haver soluções').",
                        "Eliminar C: em 'Tratar-se de' (VTI + SE), o 'se' é índice de indeterminação do sujeito e o verbo fica obrigatoriamente no singular ('Trata-se de diretrizes').",
                        "Eliminar E: o verbo 'fazer' indicando tempo decorrido é impessoal ('Faz cinco anos').",
                        "Confirmar D: 'exigir' é VTD; 'comprovantes atualizados' é o sujeito paciente no plural, logo o verbo concorda no plural ('Exigem-se comprovantes')."
                    ),
                    finalComment = "Compare sempre: 'Exigem-se comprovantes' (VTD + SE = passiva sintética, concorda com o sujeito plural) vs. 'Trata-se de diretrizes' (VTI + SE = sujeito indeterminado, verbo sempre no singular)."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-32",
                    lessonId = lessonId,
                    statement = "Sobre os casos especiais de concordância verbal com expressões partitivas e porcentagens na norma-padrão, analise as construções a seguir:\n\nI. \"A maioria dos analistas administrativos participou do treinamento de conformidade.\"\nII. \"A maioria dos analistas administrativos participaram do treinamento de conformidade.\"\nIII. \"Cerca de 80% da frota de apoio passou por manutenção preventiva.\"\n\nEstá(ão) correta(s) segundo a norma-padrão:",
                    options = listOf(
                        "A) apenas I.",
                        "B) apenas II.",
                        "C) apenas I e III.",
                        "D) apenas II e III.",
                        "E) I, II e III."
                    ),
                    correctIndex = 4,
                    steps = listOf(
                        "Analisar I e II: diante de expressões partitivas ('A maioria de', 'Grande parte de', 'Metade de') seguidas de substantivo no plural ('dos analistas'), a norma-padrão faculta a concordância tanto no singular (com o núcleo 'maioria': 'participou') quanto no plural (com o especificador 'analistas': 'participaram').",
                        "Analisar III: nas expressões de porcentagem ('80% da frota'), o verbo pode concordar com o numeral ('80%... passaram') ou com o substantivo especificador no singular ('da frota passou').",
                        "Portanto, as três construções (I, II e III) são plenamente aceitas pela norma-padrão."
                    ),
                    finalComment = "Expressões partitivas ('a maioria dos...') e percentuais admitem dupla concordância (gramatical com o núcleo ou atrativa com o especificador)."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 9 (TP-POR-SUP-33 a TP-POR-SUP-36)
            // =====================================================================
            "tp_por_m9_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-33",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que a regência verbal atende integralmente às prescrições da norma-padrão:",
                    options = listOf(
                        "A) O descumprimento das cláusulas de segurança implicará em rescisão imediata do contrato.",
                        "B) A diretoria de logística prefere mais o transporte dutoviário do que o modal rodoviário.",
                        "C) Os engenheiros assistiram a todas as etapas do teste hidrostático na tubulação.",
                        "D) O setor de compras informou aos fornecedores de que o prazo de cotação foi prorrogado.",
                        "E) Os supervisores esqueceram-se o código de acesso ao sistema de faturamento."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Eliminar A: o verbo 'implicar' no sentido de acarretar/gerar consequência é transitivo direto e rejeita a preposição 'em' ('implicará rescisão imediata').",
                        "Eliminar B: o verbo 'preferir' rege a preposição 'a' e não admite 'mais' nem 'do que' ('prefere o transporte dutoviário ao modal rodoviário').",
                        "Eliminar D: 'informar' é VTDI; não se pode usar dois objetos indiretos ('aos fornecedores' + 'de que'). O correto é 'informou aos fornecedores que...' ou 'informou os fornecedores de que...'.",
                        "Eliminar E: 'esquecer-se' (pronominal) exige a preposição 'de' ('esqueceram-se do código' ou 'esqueceram o código').",
                        "Confirmar C: 'assistir' no sentido de ver/presenciar é VTI e exige a preposição 'a' ('assistiram a todas as etapas')."
                    ),
                    finalComment = "Cuidado redobrado com 'implicar' (sem 'em'), 'preferir X a Y' (sem 'do que'), 'assistir a' (ver) e 'informar algo a alguém / informar alguém de algo'."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-34",
                    lessonId = lessonId,
                    statement = "No período \"A nova política de recursos humanos VISA AO aperfeiçoamento técnico dos colaboradores que ASPIRAM A cargos de liderança e OBEDECEM AOS padrões éticos da companhia\", os três verbos destacados poderiam ser substituídos, mantendo-se o sentido contextual e a correção gramatical, caso se alterasse a regência para transitivos diretos (sem preposição), por:",
                    options = listOf(
                        "A) objetiva o — almejam — cumprem os",
                        "B) assiste ao — respiram — respeitam aos",
                        "C) implica em — desejam a — acatam os",
                        "D) mira ao — pretendem a — seguem aos",
                        "E) busca ao — anseiam por — atendem os"
                    ),
                    correctIndex = 0,
                    steps = listOf(
                        "Identificar o sentido de cada verbo no texto: 'visar a' = ter por objetivo; 'aspirar a' = desejar/almejar; 'obedecer a' = cumprir/acatar.",
                        "Verificar a transitividade dos substitutos na alternativa A: 'objetivar' é VTD ('objetiva o aperfeiçoamento'); 'almejar' é VTD ('almejam cargos'); 'cumprir' é VTD ('cumprem os padrões').",
                        "Confirmar que apenas a alternativa A preserva o sentido exato e emprega verbos transitivos diretos sem erro de preposição."
                    ),
                    finalComment = "Enquanto 'visar a', 'aspirar a' e 'obedecer a' são transitivos indiretos (pedem 'a'), seus sinônimos 'objetivar/buscar', 'almejar/desejar' e 'cumprir/acatar/respeitar' são transitivos diretos!"
                )
            )

            "tp_por_m9_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-35",
                    lessonId = lessonId,
                    statement = "Complete corretamente as lacunas do período abaixo com os pronomes relativos acompanhados (ou não) da preposição exigida pela norma-padrão:\n\n\"O edital de licitação ______ cláusulas os concorrentes devem obedecer estabelece os requisitos técnicos ______ a comissão julgadora se baseará para homologar a empresa ______ o contrato será celebrado.\"",
                    options = listOf(
                        "A) cujas as — que — com quem",
                        "B) a cujas — em que — com a qual",
                        "C) cujas — nos quais — a qual",
                        "D) de cujas — a que — por quem",
                        "E) em cujas — de que — na qual"
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "1ª lacuna ('______ cláusulas os concorrentes devem obedecer'): o verbo 'obedecer' exige a preposição 'a' (obedecer ÀS cláusulas do edital). Unindo a preposição 'a' ao pronome relativo possessivo 'cujas' (sem artigo depois!), obtém-se 'a cujas'.",
                        "2ª lacuna ('requisitos técnicos ______ a comissão julgadora se baseará'): quem se baseia, baseia-se EM algo. Logo, exige-se a preposição 'em' antes do relativo: 'em que' (ou 'nos quais').",
                        "3ª lacuna ('a empresa ______ o contrato será celebrado'): celebra-se um contrato COM uma empresa. Logo, exige-se a preposição 'com' antes do relativo referente a 'a empresa': 'com a qual'."
                    ),
                    finalComment = "Para acertar qualquer questão de pronome relativo na Cesgranrio, olhe sempre o verbo que vem DEPOIS da lacuna ('obedecer a', 'basear-se em', 'celebrar com') e anteponha a preposição exigida ao pronome relativo."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-36",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que o emprego de \"onde\" ou \"aonde\" está em total conformidade com a norma-padrão da Língua Portuguesa:",
                    options = listOf(
                        "A) O terminal aquaviário aonde os navios petroleiros permanecem atracados opera 24 horas por dia.",
                        "B) A reunião onde foram discutidas as metas financeiras do trimestre durou três horas.",
                        "C) O centro de distribuição aonde os caminhões de suprimento se dirigiram fica próximo à rodovia.",
                        "D) Vivemos uma época onde a transformação digital redefine os processos administrativos.",
                        "E) O relatório técnico onde constam os índices de manutenção já foi assinado."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Lembrar que 'onde' e 'aonde' só devem retomar LUGAR FÍSICO, o que elimina B ('reunião'), D ('época' -> tempo exige 'em que') e E (preferindo-se 'em que / no qual' para documentos abstratos quando há lugar físico estrito em C).",
                        "Distinguir 'onde' (lugar fixo, estático = 'em que') de 'aonde' (movimento/destino com verbo que pede a preposição 'a' = 'a que / ao qual').",
                        "Em A, 'permanecer atracado' é estático (permanecer EM -> deveria ser 'onde').",
                        "Em C, o verbo 'dirigir-se' indica movimento e exige a preposição 'a' (dirigir-se A algum lugar -> 'a + onde = aonde')."
                    ),
                    finalComment = "Usa-se 'aonde' exclusivamente com verbos de movimento que regem a preposição 'a' ('ir aonde', 'chegar aonde', 'dirigir-se aonde')."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 10 (TP-POR-SUP-37 a TP-POR-SUP-40)
            // =====================================================================
            "tp_por_m10_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-37",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que o emprego do acento grave indicativo de crase é OBRIGATÓRIO segundo a norma-padrão:",
                    options = listOf(
                        "A) A embarcação atracou exatamente as quatorze horas no terminal de Angra dos Reis.",
                        "B) A reunião de alinhamento operacional foi transferida para as quatorze horas.",
                        "C) Os operadores permaneceram no centro de controle desde as sete horas da manhã.",
                        "D) O atendimento aos fornecedores ocorre de segunda a sexta-feira em horário comercial.",
                        "E) O documento faz referência a várias normas técnicas de segurança industrial."
                    ),
                    correctIndex = 0,
                    steps = listOf(
                        "Analisar A: na indicação exata de horas determinadas ('às quatorze horas') sem outra preposição antes, o uso do acento grave é obrigatório ('atracou às quatorze horas').",
                        "Analisar B e C: antes das horas já existem as preposições 'para' e 'desde', o que impede a presença da preposição 'a' e proíbe a crase.",
                        "Analisar D: na expressão 'de segunda a sexta-feira', não há artigo no primeiro termo ('de'), logo por paralelismo não há artigo nem crase no segundo ('a').",
                        "Analisar E: o 'a' está no singular antes de expressão feminina no plural ('várias normas'), proibindo a crase."
                    ),
                    finalComment = "Horas determinadas exigem crase ('às 14h'), exceto quando antecedidas pelas preposições 'para', 'desde', 'após' ou 'entre'."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-38",
                    lessonId = lessonId,
                    statement = "Preencha corretamente as lacunas do período abaixo quanto à ocorrência ou não de crase:\n\n\"______ medida que a auditoria avançava, os fiscais solicitavam acesso ______ informações contábeis referentes ______quele exercício financeiro.\"",
                    options = listOf(
                        "A) A — às — aquele",
                        "B) À — a — àquele",
                        "C) À — às — àquele",
                        "D) A — a — àquele",
                        "E) À — às — aquele"
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "1ª lacuna ('______ medida que'): locução conjuntiva proporcional feminina -> crase sempre obrigatória ('À medida que').",
                        "2ª lacuna ('acesso ______ informações'): o substantivo 'acesso' rege a preposição 'a' (acesso AOS dados). Se escolhermos a forma determinada com artigo plural ('às') ou sem artigo ('a'), devemos verificar a 3ª lacuna.",
                        "3ª lacuna ('referentes ______quele exercício'): o adjetivo 'referentes' exige a preposição 'a' (referentes A ESTE exercício), fundindo-se obrigatoriamente com o 'a' inicial do demonstrativo 'aquele' -> 'àquele'.",
                        "Comparar B ('À — a — àquele') e C ('À — às — àquele'): em contextos formais onde 'informações contábeis' está especificada por 'referentes àquele exercício financeiro', o substantivo vem determinado pelo artigo ('às informações contábeis referentes àquele...'), consagrando C (sendo 'a informações' genérico e 'às informações... referentes àquele' determinado pelo especificador)."
                    ),
                    finalComment = "Quando um substantivo plural vem especificado por um restritivo ('informações contábeis referentes àquele exercício'), o artigo definido se impõe ('às informações'), e 'referentes a + aquele' resulta obrigatoriamente em 'àquele'."
                )
            )

            "tp_por_m10_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-39",
                    lessonId = lessonId,
                    statement = "Na Fundação Cesgranrio, identificar onde a crase é proibida é fundamental para avaliar a correção gramatical de períodos reescritos. Assinale a alternativa em que o acento grave foi empregado INCORRETAMENTE (crase proibida):",
                    options = listOf(
                        "A) Os manuais de segurança foram entregues àquela equipe de manutenção.",
                        "B) A companhia submeteu os contratos à análise da procuradoria jurídica.",
                        "C) Às vezes, o escoamento marítimo sofre ajustes devido às condições oceânicas.",
                        "D) O comunicado oficial foi enviado à todas as gerências operacionais da empresa.",
                        "E) Os técnicos retornaram à sede administrativa após a conclusão da vistoria."
                    ),
                    correctIndex = 3,
                    steps = listOf(
                        "Verificar a presença de palavras que repelem artigo nas alternativas.",
                        "Em A ('entregues a + aquela'), B ('submeteu ao exame / à análise'), C ('Às vezes' e 'devido aos ventos / às condições') e E ('retornaram ao prédio / à sede'), a crase é obrigatória.",
                        "Em D, o vocábulo 'todas' é pronome indefinido plural (precedido de 'a' no singular sem 's' e repelindo artigo antes de si). Portanto, 'à todas as gerências' contém erro crasso de crase proibida (o correto é 'a todas as gerências')."
                    ),
                    finalComment = "Nunca ocorre crase antes de pronomes indefinidos ('toda', 'todas', 'cada', 'qualquer', 'alguma', 'nenhuma') nem quando o 'a' está no singular antes de palavra no plural."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-40",
                    lessonId = lessonId,
                    statement = "Em qual das alternativas abaixo o emprego do acento grave indicativo de crase no vocábulo destacado é FACULTATIVO, podendo ser retirado sem prejuízo à correção gramatical e sem alteração de sentido?",
                    options = listOf(
                        "A) A supervisora entregou o relatório técnico À sua coordenadora imediata.",
                        "B) Os procedimentos operacionais devem obedecer ÀS normas regulamentadoras.",
                        "C) O treinamento prático será ministrado ÀS equipes do turno da manhã.",
                        "D) A embarcação sairá do porto ÀS seis horas da manhã.",
                        "E) O sucesso da operação está ligado À eficiência do planejamento logístico."
                    ),
                    correctIndex = 0,
                    steps = listOf(
                        "Recordar os três casos de crase facultativa: (1) antes de pronome possessivo feminino adjetivo no singular; (2) antes de nome próprio feminino de pessoa; (3) após a preposição 'até'.",
                        "Na alternativa A, 'sua' é pronome possessivo feminino no singular que acompanha o substantivo expresso 'coordenadora'. Logo, tanto 'entregou o relatório à sua coordenadora' quanto 'entregou o relatório a sua coordenadora' estão corretos.",
                        "Nas alternativas B, C, D e E, a crase é estritamente obrigatória."
                    ),
                    finalComment = "Antes de pronome possessivo feminino adjetivo no singular ('minha, tua, sua, nossa, vossa' + substantivo), o uso do artigo — e consequentemente da crase — é facultativo."
                )
            )

            // =====================================================================
            // EXERCÍCIOS — MÓDULO 11 (TP-POR-SUP-41 a TP-POR-SUP-44)
            // =====================================================================
            "tp_por_m11_a1" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-41",
                    lessonId = lessonId,
                    statement = "De acordo com as regras de pontuação do período simples cobradas pela Fundação Cesgranrio, assinale a alternativa cuja frase está pontuada CORRETAMENTE:",
                    options = listOf(
                        "A) A modernização dos sistemas de telemetria nos dutos terrestres, reduziu o índice de perdas operacionais.",
                        "B) Os auditores de controle interno, durante a inspeção semestral nos terminais, conferiram todos os registros de estoque.",
                        "C) A gerência de suprimentos encaminhou, aos fornecedores homologados, o novo calendário e, solicitou confirmação.",
                        "D) Durante a inspeção semestral nos terminais os auditores conferiram, todos os registros de estoque.",
                        "E) A Transpetro maior empresa de logística multimodal de petróleo e derivados do Brasil, investe em segurança."
                    ),
                    correctIndex = 1,
                    steps = listOf(
                        "Eliminar A: há uma vírgula proibida separando o sujeito ('A modernização... terrestres') de seu verbo ('reduziu').",
                        "Eliminar C: embora 'aos fornecedores homologados' pudesse ser visto como objeto indireto intercalado, a vírgula após a conjunção 'e' ('e, solicitou') sem termo intercalado é incorreta.",
                        "Eliminar D: falta a vírgula após o adjunto adverbial longo deslocado e há vírgula proibida entre o verbo 'conferiram' e seu objeto direto 'todos os registros'.",
                        "Eliminar E: o aposto explicativo ('maior empresa... do Brasil') intercalado exige DUAS vírgulas (faltou a primeira vírgula após 'A Transpetro').",
                        "Confirmar B: as duas vírgulas isolam perfeitamente o adjunto adverbial de tempo longo intercalado ('durante a inspeção semestral nos terminais') entre o sujeito e o verbo."
                    ),
                    finalComment = "Entre o sujeito e o verbo jamais cabe uma única vírgula, mas cabem perfeitamente duas vírgulas para isolar um adjunto adverbial ou aposto intercalado."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-42",
                    lessonId = lessonId,
                    statement = "No período \"A coordenadoria administrativa validou os termos de referência; a financeira, os cronogramas de desembolso\", a vírgula empregada após a palavra \"financeira\" justifica-se gramaticalmente por:",
                    options = listOf(
                        "A) isolar um vocativo dirigido ao setor financeiro.",
                        "B) separar um adjunto adverbial de lugar deslocado para o início da oração.",
                        "C) marcar a elipse (zêugma) da forma verbal \"validou\", já expressa na oração anterior.",
                        "D) isolar um aposto explicativo referente à coordenadoria administrativa.",
                        "E) separar itens de uma enumeração de objetos diretos."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Ler a segunda oração completando o sentido: 'a [coordenadoria] financeira [validou] os cronogramas de desembolso'.",
                        "Constatar que o verbo 'validou', já enunciado na primeira oração, foi omitido na segunda para evitar repetição.",
                        "Identificar a regra: usa-se vírgula para indicar a elipse (ou zêugma) de um verbo subentendido."
                    ),
                    finalComment = "Quando duas orações coordenadas são separadas por ponto e vírgula e o verbo da primeira é omitido na segunda (zêugma), emprega-se vírgula no exato local onde estaria o verbo."
                )
            )

            "tp_por_m11_a2" -> listOf(
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-43",
                    lessonId = lessonId,
                    statement = "Considere o emprego dos sinais de pontuação no período abaixo:\n\n\"O plano de contingência operacional — atualizado anualmente pela equipe de engenharia de segurança — não admite improvisações: qualquer desvio de procedimento deve ser reportado imediatamente.\"\n\nSobre a pontuação desse período, é correto afirmar que:",
                    options = listOf(
                        "A) o par de travessões poderia ser substituído por um par de vírgulas ou por parênteses, sem prejuízo à correção gramatical.",
                        "B) os dois-pontos introduzem uma relação de oposição adversativa, equivalendo a \"contudo\".",
                        "C) a supressão apenas do segundo travessão manteria a correção gramatical do período.",
                        "D) os dois-pontos separam o sujeito composto do predicado verbal da primeira oração.",
                        "E) os travessões isolam uma oração subordinada substantiva subjetiva."
                    ),
                    correctIndex = 0,
                    steps = listOf(
                        "Analisar o segmento entre os dois travessões ('atualizado anualmente pela equipe de engenharia de segurança'): trata-se de um termo explicativo intercalado entre o sujeito ('O plano de contingência operacional') e o verbo ('não admite').",
                        "Na norma-padrão cobrada pela Cesgranrio, o par de travessões que isola uma intercalação explicativa equivale estruturalmente a um par de vírgulas ou a parênteses.",
                        "Verificar que os dois-pontos introduzem uma explicação/esclarecimento (e não oposição), e que jamais se pode suprimir apenas um dos travessões de um termo intercalado."
                    ),
                    finalComment = "Pares de vírgulas, pares de travessões e parênteses são sinais equivalentes para isolar apostos explicativos e termos intercalados."
                ),
                TranspetroDidacticProvider.buildExercise(
                    id = "TP-POR-SUP-44",
                    lessonId = lessonId,
                    statement = "Assinale a alternativa em que a pontuação do período composto está em total conformidade com a norma-padrão da Língua Portuguesa:",
                    options = listOf(
                        "A) Embora os custos de frete tenham oscilado no mercado internacional a companhia manteve o equilíbrio contratual.",
                        "B) A diretoria executiva comunicou aos gestores, que o novo sistema entrará em operação na próxima segunda-feira.",
                        "C) Caso os fornecedores cumpram todas as especificações técnicas do edital, a homologação ocorrerá ainda neste mês.",
                        "D) Os contratos, que descumprirem as cláusulas ambientais, serão rescindidos, porém os demais serão renovados.",
                        "E) É fundamental, que todos os colaboradores participem do simulado de emergência no terminal."
                    ),
                    correctIndex = 2,
                    steps = listOf(
                        "Eliminar A: a oração subordinada adverbial concessiva ('Embora... internacional') está anteposta à oração principal, exigindo vírgula obrigatória ao seu final.",
                        "Eliminar B e E: é proibido separar por vírgula a oração principal da oração subordinada substantiva (objetiva direta em B e subjetiva em E).",
                        "Eliminar D: 'que descumprirem as cláusulas ambientais' tem sentido restritivo (apenas os contratos que descumprirem serão rescindidos, tanto que a frase opõe 'os demais'), logo não pode vir entre vírgulas.",
                        "Confirmar C: a oração subordinada adverbial condicional anteposta ('Caso os fornecedores cumpram... do edital,') está corretamente separada da oração principal por vírgula."
                    ),
                    finalComment = "Orações subordinadas adverbiais deslocadas para antes da principal exigem vírgula obrigatória; já orações subordinadas substantivas jamais são separadas do verbo da principal por vírgula."
                )
            )

            else -> emptyList()
        }
    }
}

