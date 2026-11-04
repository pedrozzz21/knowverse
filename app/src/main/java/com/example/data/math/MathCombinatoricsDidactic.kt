package com.example.data.math

import com.example.data.model.EnemTrailLesson
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample
import com.example.data.model.MathExercise

/**
 * Provedor Didático Especializado de Análise Combinatória, Probabilidade e Estatística para o ENEM.
 * Cobre Módulo 3 (enem_m3_a1 a enem_m3_a6), Módulo 8 (enem_m8_a1 a enem_m8_a5) e Módulo 9 (enem_m9_a1 a enem_m9_a6).
 */
object MathCombinatoricsDidactic {

    fun buildBundle(lesson: EnemTrailLesson): CalculusDidacticBundle {
        return when (lesson.id) {
            // Módulo 3: Estatística
            "enem_m3_a1" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Leitura e Interpretação de Gráficos e Tabelas no ENEM
                    
                    Mais de 5 questões da prova de Matemática exigem puramente leitura atenta de gráficos de barras, setores, linhas e infográficos.
                    
                    ### Tipos de Gráficos
                    • **Barras/Colunas:** Comparam quantidades discretas entre categorias.
                    • **Linhas:** Indicam evolução temporal contínua de uma grandeza.
                    • **Setores (Pizza):** Mostram a composição percentual de um todo (a soma das fatias deve ser 100% ou 360°).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Ângulo no Gráfico de Setores", "\\alpha = \\frac{\\%}{100} \\cdot 360°", "Conversão de porcentagem para graus da fatia")),
                solvedExamples = listOf(LessonExample("Fatia de 25%", "25% de 360° = 90°", "Ângulo reto no centro do gráfico de setores.")),
                goldTip = "Leia com lupa o título do gráfico e as unidades nos eixos (ex: valores em milhares ou milhões)!"
            )

            "enem_m3_a2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Média Aritmética Simples
                    
                    A média simples soma todos os valores amostrais e divide pelo número total de observações:
                    \( \bar{x} = \frac{\sum x_i}{n} \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Média Simples", "\\bar{x} = \\frac{x_1 + x_2 + \\dots + x_n}{n}", "Soma dividida pelo total de termos")),
                solvedExamples = listOf(LessonExample("Média de Notas", "(7,0 + 8,0 + 9,0) / 3 = 8,0", "Soma 24 dividida por 3.")),
                goldTip = "Em problemas com nota final pretendida: se a média de 4 provas deve ser 7,0, a soma das 4 notas deve dar no mínimo 28,0!"
            )

            "enem_m3_a3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Média Aritmética Ponderada
                    
                    Utilizada quando diferentes observações possuem pesos (ou frequências) desiguais:
                    \( \bar{x}_p = \frac{\sum (x_i \cdot p_i)}{\sum p_i} \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Média Ponderada", "\\bar{x}_p = \\frac{x_1 p_1 + \\dots + x_n p_n}{p_1 + \\dots + p_n}", "Divisão pela soma dos pesos")),
                solvedExamples = listOf(LessonExample("Nota no ENEM", "Redação peso 3 (800) e Mat peso 2 (700) -> (2400 + 1400)/5 = 760", "Divisão por 5.")),
                goldTip = "Não divida pelo número de matérias, divida SEMPRE pela SOMA DOS PESOS!"
            )

            "enem_m3_a4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Moda Estatística
                    
                    A moda (\( M_o \)) é o valor que ocorre com maior frequência em um conjunto de dados. Um conjunto pode ser amodal (sem repetições), unimodal ou bimodal.
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Moda", "Valor mais frequente", "Dado com maior frequência absoluta")),
                solvedExamples = listOf(LessonExample("Conjunto {3, 5, 5, 8, 9}", "Moda = 5", "O 5 repete duas vezes enquanto os outros aparecem uma única vez.")),
                goldTip = "Em tabelas de frequência, a moda é o valor da linha que tem o MAIOR número na coluna de frequência!"
            )

            "enem_m3_a5" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Mediana e Posição Central
                    
                    A mediana (\( M_d \)) divide o conjunto ordenado de dados (em rol) exatamente ao meio.
                    
                    ### Como Calcular
                    1. Ordene os dados em ordem crescente (**Rol**).
                    2. Se \( n \) for ímpar: a mediana é o elemento central exatamente na posição \( \frac{n + 1}{2} \).
                    3. Se \( n \) for par: a mediana é a média aritmética dos dois termos centrais.
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Mediana de Conjunto Par", "M_d = \\frac{x_{n/2} + x_{n/2 + 1}}{2}", "Média dos dois valores do meio")),
                solvedExamples = listOf(LessonExample("Dados {2, 4, 7, 9, 12, 14}", "Termos centrais 7 e 9 -> Md = (7 + 9) / 2 = 8", "Conjunto de 6 elementos.")),
                goldTip = "ERRO MAIS COMUM: Calcular a mediana sem antes colocar a lista em ordem crescente! Monte o rol primeiro!"
            )

            "enem_m3_a6" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Medidas de Dispersão: Variância e Desvio Padrão
                    
                    Indicam a regularidade de um conjunto de dados em torno da média.
                    • **Desvio Padrão (\( \sigma \)):** É a raiz quadrada da variância.
                    • Quanto **menor** o desvio padrão, mais **regular**, **homogêneo** e **confiável** é o desempenho do candidato ou máquina.
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Desvio Padrão", "\\sigma = \\sqrt{Var}", "Menor desvio padrão = Maior regularidade")),
                solvedExamples = listOf(LessonExample("Dois alunos com média 7,0", "Aluno A tem σ = 0,5 e Aluno B tem σ = 2,5", "O Aluno A é muito mais regular e constante.")),
                goldTip = "Se a questão do ENEM pergunta 'qual candidato foi mais regular', escolha aquele que possui o MENOR desvio padrão!"
            )

            // Módulo 8: Análise Combinatória
            "enem_m8_a1" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Princípio Fundamental da Contagem (PFC)
                    
                    Se uma decisão pode ser tomada de \( m \) maneiras e, em seguida, uma segunda decisão pode ser tomada de \( n \) maneiras independentes, o total de combinações possíveis é \( m \cdot n \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Princípio Multiplicativo", "N = n_1 \\cdot n_2 \\cdot \\dots \\cdot n_k", "Multiplicação das opções de cada etapa")),
                solvedExamples = listOf(LessonExample("Placa de Carro (3 letras e 4 números)", "26 * 26 * 26 * 10 * 10 * 10 * 10", "Multiplicação de todas as escolhas.")),
                goldTip = "Comece sempre preenchendo as etapas que possuem restrições (ex: algarismos distintos, números pares ou não nulos)!"
            )

            "enem_m8_a2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Fatorial e Simplificações Algébricas
                    
                    O fatorial de um número natural \( n \) é o produto de todos os inteiros positivos de 1 até \( n \):
                    \( n! = n \cdot (n - 1) \cdot (n - 2) \cdots 1 \). Por convenção: \( 0! = 1 \) e \( 1! = 1 \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Fatorial", "n! = n \\cdot (n - 1)!", "Propriedade recursiva de simplificação")),
                solvedExamples = listOf(LessonExample("Simplificação", "10! / 8! = (10 * 9 * 8!) / 8! = 90", "Cancele o fatorial comum no denominador.")),
                goldTip = "Nunca calcule fatoriais grandes inteiros! Abra o maior até o número do menor e corte!"
            )

            "enem_m8_a3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Permutações Simples e com Repetição (Anagramas)
                    
                    Permutar significa reordenar todos os elementos disponíveis.
                    • **Permutação Simples:** \( P_n = n! \)
                    • **Permutação com Repetição (elementos repetidos \( a, b \)):** \( P_n^{a, b} = \frac{n!}{a! \cdot b!} \)
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Anagramas com Repetição", "P_n^{a, b} = \\frac{n!}{a! \\cdot b!}", "Divisão pelos fatoriais das letras repetidas")),
                solvedExamples = listOf(LessonExample("Anagramas de 'ARARA'", "5 letras com 3 'A' e 2 'R': P = 5! / (3! * 2!) = 120 / (6 * 2) = 10", "10 palavras distintas.")),
                goldTip = "Se elementos devem ficar 'juntos em qualquer ordem', amarre-os em um bloco único e multiplique pelo fatorial interno do bloco!"
            )

            "enem_m8_a4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Arranjos Simples: A Ordem Importa!
                    
                    Em um arranjo de \( n \) elementos tomados \( p \) a \( p \), a troca de ordem dos elementos gera um agrupamento diferente (ex: pódio de 1º, 2º e 3º lugares, senhas, cargos de presidente e tesoureiro).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Arranjo Simples", "A_{n, p} = \\frac{n!}{(n - p)!}", "A ordem gera grupos distintos")),
                solvedExamples = listOf(LessonExample("Eleição de Presidente e Vice entre 10 membros", "A(10, 2) = 10 * 9 = 90 opções", "Ordem define o cargo.")),
                goldTip = "Faça a pergunta-chave: 'Se eu mudar a ordem dos escolhidos, muda o resultado?' Se mudar, é ARRANJO (ou PFC)!"
            )

            "enem_m8_a5" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Combinações Simples: A Ordem NÃO Importa!
                    
                    Na combinação, mudar a ordem dos elementos não cria um grupo novo (ex: comissões, equipes de trabalho, escolha de sabores de pizza ou mãos de baralho).
                    \( C_{n, p} = \frac{n!}{p! \cdot (n - p)!} \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Combinação Simples", "C_{n, p} = \\frac{n!}{p!(n - p)!}", "A ordem dos elementos é indiferente")),
                solvedExamples = listOf(LessonExample("Comissão de 3 médicos entre 8 disponíveis", "C(8, 3) = (8 * 7 * 6) / (3 * 2 * 1) = 56 equipes", "Ordem não altera o grupo.")),
                goldTip = "A imensa maioria dos problemas de agrupamento no ENEM é de COMBINAÇÃO! Divida sempre pelo fatorial do número de escolhidos!"
            )

            // Módulo 9: Probabilidade
            "enem_m9_a1" -> CalculusDidacticBundle(
                theoryMarkdown = "# Experimento Aleatório, Espaço Amostral e Evento\nEspaço amostral (Ω) é o conjunto de todos os resultados possíveis. Evento (A) é qualquer subconjunto de Ω.",
                formulas = listOf(FormulaOrSyntaxItem("Espaço Amostral de Moedas", "n(\\Omega) = 2^k", "Número de resultados ao lançar k moedas")),
                solvedExamples = listOf(LessonExample("Lançamento de 2 dados", "n(Ω) = 6 * 6 = 36 pares ordenados", "Espaço amostral completo.")),
                goldTip = "Antes de calcular a probabilidade, conte primeiro o número total de possibilidades do espaço amostral!"
            )

            "enem_m9_a2" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Definição Clássica de Probabilidade (Laplace)
                    
                    Para espaços equiprováveis:
                    \( P(A) = \frac{\text{Casos Favoráveis}}{\text{Casos Possíveis}} = \frac{n(A)}{n(\Omega)} \).
                    A probabilidade está sempre no intervalo \( 0 \le P(A) \le 1 \) (ou de 0% a 100%).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Probabilidade Clássica", "P(A) = \\frac{n(A)}{n(\\Omega)}", "Razão entre favoráveis e possíveis")),
                solvedExamples = listOf(LessonExample("Tirar número par no dado comum", "Favoráveis {2, 4, 6} (3 casos) em 6 possíveis -> P = 3/6 = 50%", "Probabilidade de 1/2.")),
                goldTip = "Identifique os 'casos favoráveis' filtrando rigorosamente o que a questão pede como sucesso!"
            )

            "enem_m9_a3" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Probabilidade da União de Eventos
                    
                    Probabilidade de ocorrer o evento A **OU** o evento B:
                    \( P(A \cup B) = P(A) + P(B) - P(A \cap B) \).
                    Se forem mutuamente exclusivos (não podem ocorrer juntos), \( P(A \cap B) = 0 \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Regra da Soma", "P(A \\cup B) = P(A) + P(B) - P(A \\cap B)", "Subtração da intersecção para evitar contagem dupla")),
                solvedExamples = listOf(LessonExample("Tirar um Ás ou uma carta de Copas", "4/52 + 13/52 - 1/52 = 16/52 = 4/13", "O Ás de Copas foi contado duas vezes.")),
                goldTip = "A palavra 'OU' na probabilidade significa SOMA (lembrando de subtrair quem está nos dois grupos)!"
            )

            "enem_m9_a4" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Probabilidade da Intersecção (Eventos Independentes)
                    
                    Probabilidade de ocorrer o evento A **E** o evento B sucessivamente:
                    \( P(A \cap B) = P(A) \cdot P(B) \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Regra do Produto", "P(A \\cap B) = P(A) \\cdot P(B)", "Multiplicação para eventos independentes")),
                solvedExamples = listOf(LessonExample("Tirar duas 'Caras' consecutivas", "1/2 * 1/2 = 1/4 = 25%", "Eventos independentes.")),
                goldTip = "A palavra 'E' na probabilidade significa MULTIPLICAÇÃO!"
            )

            "enem_m9_a5" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Probabilidade Condicional
                    
                    Probabilidade de ocorrer A sabendo previamente que o evento B já ocorreu:
                    \( P(A|B) = \frac{P(A \cap B)}{P(B)} = \frac{n(A \cap B)}{n(B)} \).
                    O evento B passa a ser o novo espaço amostral reduzido!
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Probabilidade Condicional", "P(A|B) = \\frac{n(A \\cap B)}{n(B)}", "Espaço amostral reduzido ao conjunto B")),
                solvedExamples = listOf(LessonExample("Dado com resultado par", "Sabendo que saiu par {2, 4, 6}, qual a chance de ser primo? Apenas o 2 -> P = 1/3", "Espaço reduzido para 3.")),
                goldTip = "Se a frase diz 'sabendo que...', descarte todo o resto e use apenas essa categoria como denominador!"
            )

            "enem_m9_a6" -> CalculusDidacticBundle(
                theoryMarkdown = """
                    # Probabilidade do Evento Complementar
                    
                    A probabilidade de um evento NÃO acontecer é dada por:
                    \( P(A^c) = 1 - P(A) \).
                    Estratégia mestre para questões com 'pelo menos um':
                    \( P(\text{pelo menos um}) = 1 - P(\text{nenhum}) \).
                """.trimIndent(),
                formulas = listOf(FormulaOrSyntaxItem("Evento Complementar", "P(A) = 1 - P(A^c)", "100% menos a probabilidade de falha")),
                solvedExamples = listOf(LessonExample("Pelo menos uma cara em 3 moedas", "1 - P(todas coroas) = 1 - (1/2)³ = 1 - 1/8 = 7/8 (87,5%)", "Muito mais rápido que somar 1, 2 e 3 caras.")),
                goldTip = "Viu a expressão 'PELO MENOS UM' no ENEM? Calcule a probabilidade de NENHUM e subtraia de 1!"
            )

            else -> CalculusDidacticBundle(
                theoryMarkdown = "# Estatística e Probabilidade ENEM",
                formulas = emptyList(),
                solvedExamples = emptyList(),
                goldTip = "Organize sempre as contagens com rigor."
            )
        }
    }

    fun buildExercises(lesson: EnemTrailLesson): List<MathExercise> {
        return when (lesson.id) {
            "enem_m3_a1" -> listOf(
                MathExercise(
                    id = "ex_m3_a1_1",
                    title = "ENEM: Leitura de Gráfico de Setores",
                    problemStatement = "Em uma pesquisa de preferência realizada com 1.200 consumidores, o gráfico de setores indicou que o setor correspondente ao Produto A possui um ângulo central de 72°. O número de consumidores que declararam preferência pelo Produto A foi de:",
                    options = listOf("120", "180", "240", "288", "360"),
                    correctIndex = 2,
                    hint = "Como o círculo completo tem 360°, a fração é 72° / 360° = 1/5. Calcule 1/5 de 1.200.",
                    stepByStepSolution = "Alternativa correta: C (240 consumidores).\n\n72° / 360° = 1/5 = 20%.\nTotal de pessoas = 1.200 * 20% = 240."
                )
            )

            "enem_m3_a2" -> listOf(
                MathExercise(
                    id = "ex_m3_a2_1",
                    title = "ENEM: Meta de Média em Avaliações",
                    problemStatement = "Um estudante obteve as notas 6,0; 7,0 e 5,0 nas três primeiras provas do bimestre. Para que sua média aritmética nas quatro provas seja de no mínimo 7,0, a nota mínima que ele precisa tirar na quarta avaliação é:",
                    options = listOf("8,0", "8,5", "9,0", "9,5", "10,0"),
                    correctIndex = 4,
                    hint = "A soma das 4 notas deve ser no mínimo 4 * 7,0 = 28,0.",
                    stepByStepSolution = "Alternativa correta: E (10,0).\n\nSoma atual = 6 + 7 + 5 = 18.\nSoma necessária = 4 * 7,0 = 28.\nNota na 4ª prova = 28 - 18 = 10,0."
                )
            )

            "enem_m3_a3" -> listOf(
                MathExercise(
                    id = "ex_m3_a3_1",
                    title = "ENEM: Média Ponderada em Concurso",
                    problemStatement = "Em um processo seletivo, a prova de Conhecimentos Específicos tem peso 3 e a de Língua Portuguesa tem peso 2. Um candidato tirou nota 80 em Específicos e nota 60 em Português. Sua média ponderada final foi de:",
                    options = listOf("68", "70", "72", "74", "76"),
                    correctIndex = 2,
                    hint = "Mp = (80 * 3 + 60 * 2) / (3 + 2).",
                    stepByStepSolution = "Alternativa correta: C (72).\n\nMp = (240 + 120) / 5 = 360 / 5 = 72."
                )
            )

            "enem_m3_a4" -> listOf(
                MathExercise(
                    id = "ex_m3_a4_1",
                    title = "ENEM: Moda em Vendas de Calçados",
                    problemStatement = "Uma loja registrou os tamanhos dos últimos pares de tênis vendidos: {38, 39, 40, 40, 41, 40, 42, 39, 40, 43}. A moda dessa amostra de calçados vendidos é o número:",
                    options = listOf("39", "40", "41", "42", "40,2"),
                    correctIndex = 1,
                    hint = "A moda é o número que mais se repete na lista.",
                    stepByStepSolution = "Alternativa correta: B (40).\n\nO número 40 aparece 4 vezes, com frequência absoluta superior a todos os demais calçados."
                )
            )

            "enem_m3_a5" -> listOf(
                MathExercise(
                    id = "ex_m3_a5_1",
                    title = "ENEM: Mediana de Conjunto de Dados",
                    problemStatement = "O tempo diário de treino (em minutos) de um atleta ao longo de 8 dias foi: 45, 60, 30, 50, 40, 55, 65, 35. A mediana desse conjunto de tempos é de:",
                    options = listOf("45,0 minutos", "47,5 minutos", "50,0 minutos", "52,5 minutos", "55,0 minutos"),
                    correctIndex = 1,
                    hint = "Ordene primeiro a lista em rol crescente e faça a média dos dois valores centrais.",
                    stepByStepSolution = "Alternativa correta: B (47,5 minutos).\n\nRol ordenado: 30, 35, 40, 45, 50, 55, 60, 65.\nTermos centrais: 45 e 50.\nMediana = (45 + 50) / 2 = 47,5 minutos."
                )
            )

            "enem_m3_a6" -> listOf(
                MathExercise(
                    id = "ex_m3_a6_1",
                    title = "ENEM: Interpretação de Desvio Padrão",
                    problemStatement = "Três máquinas empacotadoras de grãos A, B e C produzem pacotes com média de 1.000 gramas. Os desvios padrão apurados no controle de qualidade foram: Máquina A (σ = 2,5 g), Máquina B (σ = 0,8 g) e Máquina C (σ = 4,2 g). O setor de engenharia deve escolher a máquina com produção mais homogênea e regular. A máquina escolhida deve ser a:",
                    options = listOf("Máquina A", "Máquina B", "Máquina C", "Qualquer uma, pois as médias são iguais", "Máquina C, por ter maior tolerância"),
                    correctIndex = 1,
                    hint = "Maior regularidade e homogeneidade correspondem ao MENOR desvio padrão.",
                    stepByStepSolution = "Alternativa correta: B (Máquina B).\n\nO desvio padrão mede a dispersão em torno da média. Como a Máquina B tem o menor desvio padrão (0,8 g), seus pacotes apresentam peso muito mais constante e uniforme."
                )
            )

            "enem_m8_a1" -> listOf(
                MathExercise(
                    id = "ex_m8_a1_1",
                    title = "ENEM: Combinações de Roupas pelo PFC",
                    problemStatement = "Um jovem dispõe de 4 camisas de cores diferentes, 3 calças distintas e 2 pares de sapatos. O número de maneiras distintas que ele pode se vestir escolhendo uma camisa, uma calça e um par de sapatos é:",
                    options = listOf("9", "12", "18", "24", "36"),
                    correctIndex = 3,
                    hint = "Pelo Princípio Multiplicativo: 4 * 3 * 2.",
                    stepByStepSolution = "Alternativa correta: D (24 maneiras).\n\nN = 4 * 3 * 2 = 24 combinações de vestuário."
                )
            )

            "enem_m8_a2" -> listOf(
                MathExercise(
                    id = "ex_m8_a2_1",
                    title = "ENEM: Simplificação de Fatoriais",
                    problemStatement = "O valor da expressão numérica (12! + 11!) / 10! é igual a:",
                    options = listOf("132", "143", "144", "154", "165"),
                    correctIndex = 1,
                    hint = "Fatore 10! no numerador: (12 * 11 * 10! + 11 * 10!) / 10! = 132 + 11.",
                    stepByStepSolution = "Alternativa correta: B (143).\n\n(132 * 10! + 11 * 10!) / 10! = 132 + 11 = 143."
                )
            )

            "enem_m8_a3" -> listOf(
                MathExercise(
                    id = "ex_m8_a3_1",
                    title = "ENEM: Anagramas com Letras Repetidas",
                    problemStatement = "O número de anagramas diferentes que podem ser formados com as letras da palavra BRASIL mantendo as vogais 'A' e 'I' sempre juntas em qualquer ordem é:",
                    options = listOf("120", "240", "360", "480", "720"),
                    correctIndex = 1,
                    hint = "Considere o bloco (AI) como 1 elemento. Teremos 5 elementos permutando, multiplicados por 2! da ordem interna das vogais.",
                    stepByStepSolution = "Alternativa correta: B (240 anagramas).\n\nElementos: B, R, S, L e o bloco (A, I) -> 5 elementos.\nP = 5! * 2! = 120 * 2 = 240."
                )
            )

            "enem_m8_a4" -> listOf(
                MathExercise(
                    id = "ex_m8_a4_1",
                    title = "ENEM: Criação de Senhas Numéricas (Arranjo)",
                    problemStatement = "Um cofre eletrônico exige uma senha formada por 4 algarismos distintos escolhidos entre os algarismos de 1 a 9. O total de senhas possíveis de serem criadas é:",
                    options = listOf("126", "504", "1.512", "3.024", "6.561"),
                    correctIndex = 3,
                    hint = "A ordem importa. A(9, 4) = 9 * 8 * 7 * 6.",
                    stepByStepSolution = "Alternativa correta: D (3.024 senhas).\n\n9 opções para o 1º dígito, 8 para o 2º, 7 para o 3º e 6 para o 4º: 9 * 8 * 7 * 6 = 3.024."
                )
            )

            "enem_m8_a5" -> listOf(
                MathExercise(
                    id = "ex_m8_a5_1",
                    title = "ENEM: Formação de Comissão de Trabalho (Combinação)",
                    problemStatement = "Em um hospital, um plantão de emergência deve ser composto por 3 médicos escolhidos entre um grupo de 10 médicos plantonistas disponíveis. Quantas equipes de plantão diferentes podem ser formadas?",
                    options = listOf("120", "240", "360", "720", "1.000"),
                    correctIndex = 0,
                    hint = "A ordem dos médicos não altera a equipe: C(10, 3) = (10 * 9 * 8) / (3 * 2 * 1).",
                    stepByStepSolution = "Alternativa correta: A (120 equipes).\n\nC(10, 3) = 720 / 6 = 120 equipes."
                )
            )

            "enem_m9_a1" -> listOf(
                MathExercise(
                    id = "ex_m9_a1_1",
                    title = "ENEM: Espaço Amostral no Lançamento de Três Moedas",
                    problemStatement = "Ao lançar 3 moedas honestas simultaneamente, o número total de resultados possíveis no espaço amostral é:",
                    options = listOf("3", "6", "8", "9", "12"),
                    correctIndex = 2,
                    hint = "Cada moeda tem 2 resultados (Cara ou Coroa). n(Ω) = 2³.",
                    stepByStepSolution = "Alternativa correta: C (8 resultados).\n\n2 * 2 * 2 = 8 sequências possíveis."
                )
            )

            "enem_m9_a2" -> listOf(
                MathExercise(
                    id = "ex_m9_a2_1",
                    title = "ENEM: Probabilidade Clássica em Urna",
                    problemStatement = "Uma urna contém 12 bolas brancas, 18 bolas vermelhas e 20 bolas pretas, todas de mesmo tamanho e peso. Retirando-se uma bola ao acaso dessa urna, a probabilidade de ela ser vermelha é de:",
                    options = listOf("24%", "30%", "36%", "40%", "45%"),
                    correctIndex = 2,
                    hint = "Total de bolas = 12 + 18 + 20 = 50. P = 18 / 50.",
                    stepByStepSolution = "Alternativa correta: C (36%).\n\nP = 18 / 50 = 36 / 100 = 36%."
                )
            )

            "enem_m9_a3" -> listOf(
                MathExercise(
                    id = "ex_m9_a3_1",
                    title = "ENEM: Probabilidade da União (Regra da Soma)",
                    problemStatement = "Ao sortear um número de 1 a 50 em uma rifa beneficente, a probabilidade de o número sorteado ser múltiplo de 5 OU múltiplo de 8 é de:",
                    options = listOf("24%", "26%", "30%", "32%", "34%"),
                    correctIndex = 2,
                    hint = "Múltiplos de 5: 10 números. Múltiplos de 8: 6 números. Múltiplos de 40 (ambos): 1 número. P = (10 + 6 - 1) / 50.",
                    stepByStepSolution = "Múltiplos de 5: {5, 10, ..., 50} (10 números).\nMúltiplos de 8: {8, 16, 24, 32, 40, 48} (6 números).\nIntersecção (múltiplos de 40): {40} (1 número).\nFavoráveis = 10 + 6 - 1 = 15.\nP = 15 / 50 = 30% (Alternativa C)."
                )
            )

            "enem_m9_a4" -> listOf(
                MathExercise(
                    id = "ex_m9_a4_1",
                    title = "ENEM: Probabilidade de Eventos Independentes",
                    problemStatement = "A probabilidade de um atirador A acertar o alvo é de 80% (4/5) e a de um atirador B acertar é de 70% (7/10). Se ambos atiram de forma independente, a probabilidade de AMBOS acertarem o alvo é de:",
                    options = listOf("50%", "56%", "64%", "72%", "75%"),
                    correctIndex = 1,
                    hint = "Multiplique as probabilidades: P(A e B) = 0,80 * 0,70.",
                    stepByStepSolution = "Alternativa correta: B (56%).\n\nP = 0,80 * 0,70 = 0,56 = 56%."
                )
            )

            "enem_m9_a5" -> listOf(
                MathExercise(
                    id = "ex_m9_a5_1",
                    title = "ENEM: Probabilidade Condicional com Espaço Reduzido",
                    problemStatement = "Em uma escola com 200 alunos, 120 praticam futebol, 80 praticam vôlei e 40 praticam ambos os esportes. Escolhendo-se ao acaso um aluno que sabidamente joga futebol, a probabilidade de ele também praticar vôlei é de:",
                    options = listOf("20%", "25%", "33,3%", "40%", "50%"),
                    correctIndex = 2,
                    hint = "O novo espaço amostral são os 120 que jogam futebol. Favoráveis: os 40 que praticam ambos.",
                    stepByStepSolution = "Alternativa correta: C (33,3% ou 1/3).\n\nP(Vôlei | Futebol) = 40 / 120 = 1/3 ≈ 33,3%."
                )
            )

            "enem_m9_a6" -> listOf(
                MathExercise(
                    id = "ex_m9_a6_1",
                    title = "ENEM: Probabilidade do Evento Complementar",
                    problemStatement = "A probabilidade de um equipamento de segurança falhar em um dia é de 10% (0,10). Em um sistema com dois equipamentos operando de forma independente, a probabilidade de PELO MENOS UM deles funcionar com sucesso é de:",
                    options = listOf("80%", "81%", "90%", "95%", "99%"),
                    correctIndex = 4,
                    hint = "P(pelo menos um) = 1 - P(ambos falharem) = 1 - (0,10 * 0,10).",
                    stepByStepSolution = "Alternativa correta: E (99%).\n\nP(ambos falharem) = 0,10 * 0,10 = 0,01 (1%).\nP(ao menos um funcionar) = 1 - 0,01 = 0,99 = 99%."
                )
            )

            else -> emptyList()
        }
    }
}
