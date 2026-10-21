package com.example.data.python

import com.example.data.model.DifficultyTier
import com.example.data.model.KahootModule
import com.example.data.model.KahootQuestion
import com.example.data.model.KnowledgeArea
import com.example.data.model.LearningUnit
import com.example.data.model.Lesson
import com.example.data.model.LessonType
import com.example.data.model.TestCase
import com.example.data.model.ValidationType

object PythonCurriculum {

    val knowledgeAreas = listOf(
        KnowledgeArea(
            id = "python",
            title = "Programação: Python Completo",
            description = "Currículo estruturado: do primeiro comando até Orientação a Objetos, Regex, Estruturas de Dados e Algoritmos.",
            iconKey = "PYTHON",
            isAvailable = true,
            totalLessons = 36,
            estimatedHours = "30h de prática",
            category = "Tecnologia"
        ),
        KnowledgeArea(
            id = "math",
            title = "Matemática Aplicada & Cálculo I",
            description = "Limites, Continuidade, Derivadas, Taxas de Variação e Otimização com visualização gráfica diagnóstica.",
            iconKey = "MATH",
            isAvailable = true,
            totalLessons = 18,
            estimatedHours = "25h de prática",
            category = "Ciências Exatas"
        ),
        KnowledgeArea(
            id = "ai",
            title = "Inteligência Artificial & Machine Learning",
            description = "Fundamentos de modelos preditivos, regressão, classificação e redes neurais.",
            iconKey = "AI",
            isAvailable = false,
            totalLessons = 30,
            estimatedHours = "25h de prática",
            category = "Inteligência Artificial"
        ),
        KnowledgeArea(
            id = "data",
            title = "Ciência de Dados & Visualização",
            description = "Pipelines analíticos, Pandas, NumPy, estatística descritiva e métricas.",
            iconKey = "DATA",
            isAvailable = false,
            totalLessons = 28,
            estimatedHours = "22h de prática",
            category = "Dados"
        )
    )

    // ==========================================
    // TIER 1: BÁSICO (Arena Final - 12 Questões)
    // ==========================================

    val basicKahoot = KahootModule(
        id = "kahoot_basic",
        tier = DifficultyTier.BASIC,
        title = "Teste Final: Fundamentos de Python",
        description = "Desafio decisivo de 12 questões sobre sintaxe, tipos primitivos, operadores, condicionais, laços e dicionários!",
        category = "Programação",
        subject = "Python",
        questions = listOf(
            KahootQuestion(
                id = "kb_q1",
                question = "Qual é o resultado da operação 7 // 2 em Python?",
                codeSnippet = "resultado = 7 // 2\nprint(resultado)",
                options = listOf("3.5", "3", "1", "Erro de Sintaxe"),
                correctIndex = 1,
                explanation = "O operador // realiza divisão inteira, descartando a parte fracionária. 7 // 2 resulta em 3.",
                conceptTag = "Operadores Aritméticos e Divisão Inteira",
                recommendedLessonId = "py_b1_l2",
                recommendedLessonTitle = "Aula 2: Variáveis e Tipos Numéricos"
            ),
            KahootQuestion(
                id = "kb_q2",
                question = "Qual método adiciona um elemento ao final de uma lista?",
                codeSnippet = "frutas = ['maçã', 'banana']",
                options = listOf("frutas.add('uva')", "frutas.push('uva')", "frutas.append('uva')", "frutas.insert('uva')"),
                correctIndex = 2,
                explanation = "Em Python, listas utilizam o método .append() para anexar itens ao final.",
                conceptTag = "Manipulação de Listas (.append)",
                recommendedLessonId = "py_b3_l1",
                recommendedLessonTitle = "Aula 5: Listas e Métodos"
            ),
            KahootQuestion(
                id = "kb_q3",
                question = "Qual estrutura de dados garante elementos únicos e não ordenados?",
                codeSnippet = "itens = {1, 2, 2, 3}",
                options = listOf("list", "dict", "tuple", "set"),
                correctIndex = 3,
                explanation = "O tipo set (conjunto) armazena apenas valores únicos, eliminando duplicatas automaticamente.",
                conceptTag = "Coleções e Conjuntos (Set)",
                recommendedLessonId = "py_b3_l2",
                recommendedLessonTitle = "Aula 6: Conjuntos e Dicionários"
            ),
            KahootQuestion(
                id = "kb_q4",
                question = "O que será impresso pelo código abaixo?",
                codeSnippet = "def soma(a, b=10):\n    return a + b\nprint(soma(5))",
                options = listOf("5", "15", "Erro: falta argumento b", "None"),
                correctIndex = 1,
                explanation = "Como 'b' possui valor padrão 10, a chamada soma(5) calcula 5 + 10 = 15.",
                conceptTag = "Funções e Parâmetros com Valor Padrão",
                recommendedLessonId = "py_b1_l2",
                recommendedLessonTitle = "Aula 2: Variáveis e Tipagem Dinâmica"
            ),
            KahootQuestion(
                id = "kb_q5",
                question = "Qual é o tipo da variável criada com: x = 4.0?",
                codeSnippet = "x = 4.0\nprint(type(x))",
                options = listOf("int", "float", "str", "double"),
                correctIndex = 1,
                explanation = "Números com ponto decimal em Python pertencem ao tipo float.",
                conceptTag = "Tipos Primitivos",
                recommendedLessonId = "py_b1_l2",
                recommendedLessonTitle = "Aula 2: Variáveis e Tipagem Dinâmica"
            ),
            KahootQuestion(
                id = "kb_q6",
                question = "Qual operador calcula o resto de uma divisão inteira?",
                codeSnippet = "resto = 17 ? 5",
                options = listOf("%", "//", "/", "**"),
                correctIndex = 0,
                explanation = "O operador % (módulo) retorna o resto da divisão. 17 % 5 resulta em 2.",
                conceptTag = "Operador Módulo (%)",
                recommendedLessonId = "py_b2_l1",
                recommendedLessonTitle = "Aula 3: Operadores Aritméticos"
            ),
            KahootQuestion(
                id = "kb_q7",
                question = "Qual palavra-chave é usada para testar condições intermediárias em um bloco condicional?",
                codeSnippet = "if x > 10:\n    ...\n???: \n    ...\nelse:\n    ...",
                options = listOf("elif", "elseif", "else if", "switch"),
                correctIndex = 0,
                explanation = "Em Python, a abreviação oficial de 'else if' é a palavra-chave elif.",
                conceptTag = "Estrutura Condicional elif",
                recommendedLessonId = "py_b2_l2",
                recommendedLessonTitle = "Aula 4: Controle com if, elif e else"
            ),
            KahootQuestion(
                id = "kb_q8",
                question = "O que range(1, 5) gera quando percorrido por um laço for?",
                codeSnippet = "for i in range(1, 5):\n    print(i)",
                options = listOf("1, 2, 3, 4", "1, 2, 3, 4, 5", "0, 1, 2, 3, 4", "2, 3, 4, 5"),
                correctIndex = 0,
                explanation = "A função range(inicio, fim) é exclusiva no limite final: vai de 1 até 4.",
                conceptTag = "Função range()",
                recommendedLessonId = "py_b3_l1",
                recommendedLessonTitle = "Aula 5: Laço for com range()"
            ),
            KahootQuestion(
                id = "kb_q9",
                question = "Como acessar o valor da chave 'idade' no dicionário pessoa = {'nome': 'Carlos', 'idade': 28}?",
                codeSnippet = "pessoa = {'nome': 'Carlos', 'idade': 28}",
                options = listOf("pessoa['idade']", "pessoa(idade)", "pessoa.idade()", "pessoa[1]"),
                correctIndex = 0,
                explanation = "Valores de dicionários são indexados entre colchetes pela chave: pessoa['idade'].",
                conceptTag = "Dicionários (dict)",
                recommendedLessonId = "py_b3_l2",
                recommendedLessonTitle = "Aula 6: Dicionários (Chave e Valor)"
            ),
            KahootQuestion(
                id = "kb_q10",
                question = "Qual é o resultado de int('42') + 8?",
                codeSnippet = "total = int('42') + 8\nprint(total)",
                options = listOf("50", "'428'", "Erro de tipo", "42.8"),
                correctIndex = 0,
                explanation = "int('42') converte a string para o número 42. Somando 8, obtemos 50.",
                conceptTag = "Conversão de Tipos (Type Casting)",
                recommendedLessonId = "py_b1_l3",
                recommendedLessonTitle = "Aula 3: Conversão de Tipos"
            ),
            KahootQuestion(
                id = "kb_q11",
                question = "Como comentários de uma única linha são iniciados em Python?",
                codeSnippet = "? Meu comentário explicativo",
                options = listOf("#", "//", "/*", "--"),
                correctIndex = 0,
                explanation = "Comentários de linha única começam com o caractere cerquilha (#).",
                conceptTag = "Sintaxe e Comentários",
                recommendedLessonId = "py_b1_l1",
                recommendedLessonTitle = "Aula 1: Primeiros Passos com print()"
            ),
            KahootQuestion(
                id = "kb_q12",
                question = "Qual é a saída de len(['A', 'B', 'C', 'D'])?",
                codeSnippet = "itens = ['A', 'B', 'C', 'D']\nprint(len(itens))",
                options = listOf("4", "3", "5", "Erro"),
                correctIndex = 0,
                explanation = "A função len() retorna a quantidade de itens em uma coleção. Há 4 elementos.",
                conceptTag = "Função len()",
                recommendedLessonId = "py_b3_l1",
                recommendedLessonTitle = "Aula 5: Laço for com range()"
            )
        )
    )

    private val unit1Basic = LearningUnit(
        id = "py_b1",
        unitNumber = 1,
        tier = DifficultyTier.BASIC,
        title = "Sintaxe, Variáveis & Tipos Primitivos",
        description = "Domine a função print(), variáveis dinâmicas, tipos int, float, str, bool e conversões de tipos.",
        lessons = listOf(
            Lesson(
                id = "py_b1_l1",
                unitId = "py_b1",
                title = "Primeiros Passos com print()",
                conceptTitle = "A Função print() e Saída Padrão",
                explanation = """
                    Em Python, a função print() exibe dados e mensagens no console do terminal. Cadeias de texto devem estar delimitadas por aspas simples ('...') ou aspas duplas ("...").
                    
                    🎯 Objetivo da Missão:
                    Escreva um comando usando a função print() para exibir no terminal exatamente a mensagem: Olá, Python!
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = """
# 🎯 Objetivo: Use a função print() para exibir no terminal exatamente a mensagem: Olá, Python!
# Digite seu código na linha abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Utilize a função print() passando a mensagem entre aspas, por exemplo: print('...')",
                    "Certifique-se de escrever exatamente 'Olá, Python!' com acentuação e pontuação corretas."
                ),
                solutionExplanation = "A função print() recebe argumentos posicionais e envia a representação em texto para stdout.",
                xpReward = 25,
                testCases = listOf(
                    TestCase(
                        name = "Saída padrão",
                        expectedOutput = "Olá, Python!",
                        validationType = ValidationType.CONTAINS_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_b1_l2",
                unitId = "py_b1",
                title = "Variáveis e Tipagem Dinâmica",
                conceptTitle = "Declarando Variáveis",
                explanation = """
                    Python atribui tipos aos dados automaticamente sem você precisar declarar o tipo antes do nome.
                    
                    🎯 Objetivo da Missão:
                    Crie uma variável chamada 'linguagem' com o valor "Python" e uma variável chamada 'versao' com o número 3. Em seguida, utilize print(linguagem, versao) para exibi-las separadas por espaço.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
# 🎯 Objetivo: Crie a variável 'linguagem' com "Python" e 'versao' com 3.
# Em seguida, exiba ambas com a instrução print(linguagem, versao).
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Use o operador de atribuição = para associar os valores aos nomes das variáveis.",
                    "A função print() aceita múltiplos argumentos separados por vírgula e insere espaço entre eles."
                ),
                solutionExplanation = "O Python associa os tipos str e int às variáveis dinamicamente.",
                xpReward = 30,
                testCases = listOf(
                    TestCase(
                        name = "Variáveis impressas",
                        expectedOutput = "Python 3",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_b1_l3",
                unitId = "py_b1",
                title = "Conversão de Tipos (Type Casting)",
                conceptTitle = "Casting com int() e float()",
                explanation = """
                    Quando valores numéricos chegam como texto (por exemplo, "50"), precisamos convertê-los com int() ou float() antes de realizar operações matemáticas.
                    
                    🎯 Objetivo da Missão:
                    Converta a variável valor_texto para um número inteiro, some 25 ao resultado e imprima o total obtido.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
valor_texto = "50"

# 🎯 Objetivo: Converta valor_texto para int, some 25 e imprima o valor final.
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Passe valor_texto como argumento para int(), somando 25 ao resultado obtido.",
                    "Armazene a soma em uma variável e passe-a para a função print()."
                ),
                solutionExplanation = "int('50') converte a string para o número inteiro 50, permitindo a soma aritmética.",
                xpReward = 35,
                testCases = listOf(
                    TestCase(
                        name = "Soma convertida",
                        expectedOutput = "75",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            )
        )
    )

    private val unit2Basic = LearningUnit(
        id = "py_b2",
        unitNumber = 2,
        tier = DifficultyTier.BASIC,
        title = "Operadores & Estruturas Condicionais",
        description = "Operações aritméticas, divisão inteira, operador de módulo e blocos if, elif e else.",
        lessons = listOf(
            Lesson(
                id = "py_b2_l1",
                unitId = "py_b2",
                title = "Operadores Aritméticos",
                conceptTitle = "Divisão Inteira e Módulo (%)",
                explanation = """
                    O operador // calcula o quociente inteiro da divisão e % calcula o resto da divisão. O resto da divisão por 2, por exemplo, permite saber se um número é par ou ímpar.
                    
                    🎯 Objetivo da Missão:
                    Calcule e imprima na tela o resto da divisão da variável 'numero' (17) por 5 utilizando o operador %.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
numero = 17

# 🎯 Objetivo: Calcule o resto da divisão de 'numero' por 5 e exiba o resultado.
# Escreva a instrução abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Utilize a sintaxe: print(numero % 5)",
                    "17 dividido por 5 resulta em 3 com resto 2."
                ),
                solutionExplanation = "O operador % é fundamental para controle cíclico e verificação de divisibilidade.",
                xpReward = 30,
                testCases = listOf(
                    TestCase(
                        name = "Resto da divisão",
                        expectedOutput = "2",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_b2_l2",
                unitId = "py_b2",
                title = "Controle com if, elif e else",
                conceptTitle = "Tomada de Decisão com Indentação",
                explanation = """
                    Blocos condicionais orientam o fluxo do programa. Em Python, a indentação com 4 espaços define o corpo de cada bloco.
                    
                    🎯 Objetivo da Missão:
                    Dada a variável 'nota', crie a seguinte estrutura condicional:
                    • Se nota >= 7, imprima "Aprovado"
                    • Senão se nota >= 5, imprima "Recuperação"
                    • Caso contrário, imprima "Reprovado"
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = """
nota = 8.5

# 🎯 Objetivo: Crie os blocos if, elif e else avaliando a variável 'nota'.
# Escreva sua estrutura condicional abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Lembre-se dos dois pontos (:) após cada declaração de condição.",
                    "Indente as linhas internas com 4 espaços."
                ),
                solutionExplanation = "O interpretador avalia as expressões sequencialmente e executa o primeiro ramo verdadeiro.",
                xpReward = 40,
                testCases = listOf(
                    TestCase(
                        name = "Verifica aprovação",
                        expectedOutput = "Aprovado",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            )
        )
    )

    private val unit3Basic = LearningUnit(
        id = "py_b3",
        unitNumber = 3,
        tier = DifficultyTier.BASIC,
        title = "Laços de Repetição & Dicionários",
        description = "Sequências com range(), laços for e mapeamento de dados com chave-valor em dicionários.",
        lessons = listOf(
            Lesson(
                id = "py_b3_l1",
                unitId = "py_b3",
                title = "Laço for com range()",
                conceptTitle = "Acumulando Valores em Sequência",
                explanation = """
                    A função range(inicio, fim) gera uma sequência numérica exclusiva no limite final.
                    
                    🎯 Objetivo da Missão:
                    Utilize um laço for com range(1, 6) para somar todos os números de 1 até 5 na variável 'soma'. Ao final do laço, imprima o valor total da soma.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
soma = 0

# 🎯 Objetivo: Itere de 1 a 5 usando for i in range(1, 6) acumulando em 'soma'.
# Ao terminar o laço, imprima o valor de 'soma'.
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Dentro do laço, incremente a variável com soma += i.",
                    "A instrução print(soma) deve ficar fora do laço (sem indentação)."
                ),
                solutionExplanation = "1 + 2 + 3 + 4 + 5 = 15.",
                xpReward = 35,
                testCases = listOf(
                    TestCase(
                        name = "Soma do range",
                        expectedOutput = "15",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_b3_l2",
                unitId = "py_b3",
                title = "Dicionários (Chave e Valor)",
                conceptTitle = "Mapeamentos com dict",
                explanation = """
                    Dicionários associam chaves descritivas a seus respectivos valores.
                    
                    🎯 Objetivo da Missão:
                    Acesse o valor correspondente à chave "nota" dentro do dicionário 'aluno' e imprima esse valor no console.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
aluno = {"nome": "Alice", "nota": 9.5}

# 🎯 Objetivo: Imprima o valor da chave "nota" contida no dicionário 'aluno'.
# Escreva a instrução abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Para recuperar o valor de uma chave, use a sintaxe de colchetes: dicionario[chave].",
                    "Você também pode utilizar o método aluno.get('nota')."
                ),
                solutionExplanation = "Dicionários fornecem acesso imediato O(1) através de tabelas hash.",
                xpReward = 35,
                testCases = listOf(
                    TestCase(
                        name = "Acesso ao dicionário",
                        expectedOutput = "9.5",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            )
        ),
        kahootModule = basicKahoot
    )

    // ==========================================
    // TIER 2: INTERMEDIÁRIO (Arena Final - 12 Questões)
    // ==========================================

    val intermediateKahoot = KahootModule(
        id = "kahoot_intermediate",
        tier = DifficultyTier.INTERMEDIATE,
        title = "Teste Final: Python Funcional e Algoritmos",
        description = "Desafio de 12 questões sobre f-strings, cifras de substituição, lambdas, list comprehension, exceções e busca binária!",
        category = "Programação",
        subject = "Python",
        questions = listOf(
            KahootQuestion(
                id = "ki_q1",
                question = "O que a expressão [x**2 for x in [1, 2, 3]] produz?",
                codeSnippet = "quadrados = [x**2 for x in [1, 2, 3]]\nprint(quadrados)",
                options = listOf("[2, 4, 6]", "[1, 4, 9]", "[1, 2, 3]", "Erro de Sintaxe"),
                correctIndex = 1,
                explanation = "List comprehension calcula o quadrado de cada elemento: 1² = 1, 2² = 4, 3² = 9.",
                conceptTag = "List Comprehensions e Transformações",
                recommendedLessonId = "py_i2_l1",
                recommendedLessonTitle = "Aula 13: List Comprehension com Condicional"
            ),
            KahootQuestion(
                id = "ki_q2",
                question = "Como declarar uma função anônima que dobra um número?",
                codeSnippet = null,
                options = listOf("def dobro(x) => x * 2", "lambda x: x * 2", "fn(x) { return x * 2 }", "x -> x * 2"),
                correctIndex = 1,
                explanation = "Funções anônimas em Python utilizam a palavra-chave 'lambda': lambda x: x * 2.",
                conceptTag = "Funções Anônimas Lambda",
                recommendedLessonId = "py_i2_l1",
                recommendedLessonTitle = "Aula 13: List Comprehension"
            ),
            KahootQuestion(
                id = "ki_q3",
                question = "Qual bloco é SEMPRE executado, ocorrendo ou não uma exceção?",
                codeSnippet = "try:\n    pass\nexcept:\n    pass\n???:\n    print('fim')",
                options = listOf("else", "finally", "catch", "defer"),
                correctIndex = 1,
                explanation = "O bloco 'finally' sempre é executado para liberação de recursos.",
                conceptTag = "Tratamento de Exceções (try/except/finally)",
                recommendedLessonId = "py_i2_l2",
                recommendedLessonTitle = "Aula 14: Tratamento de Exceções"
            ),
            KahootQuestion(
                id = "ki_q4",
                question = "Qual algoritmo de busca divide o intervalo de busca pela metade a cada passo?",
                codeSnippet = null,
                options = listOf("Busca Linear", "Busca em Largura", "Busca Binária", "Busca Gulosa"),
                correctIndex = 2,
                explanation = "A Busca Binária tem complexidade O(log n) e requer que a lista esteja previamente ordenada.",
                conceptTag = "Algoritmos e Busca Binária",
                recommendedLessonId = "py_i3_l1",
                recommendedLessonTitle = "Aula 15: Algoritmo de Busca Binária"
            ),
            KahootQuestion(
                id = "ki_q5",
                question = "Em uma f-string, qual especificador de formato arredonda um float para 2 casas decimais?",
                codeSnippet = "f'{valor:???}'",
                options = listOf(".2f", "2f", "%2", "round(2)"),
                correctIndex = 0,
                explanation = "A especificação :.2f indica 2 dígitos após o ponto em ponto flutuante.",
                conceptTag = "F-Strings e Precisão",
                recommendedLessonId = "py_i1_l1",
                recommendedLessonTitle = "Aula 11: F-Strings e Formatação Numérica"
            ),
            KahootQuestion(
                id = "ki_q6",
                question = "Qual é o resultado da expressão [x for x in range(6) if x % 2 == 0]?",
                codeSnippet = "pares = [x for x in range(6) if x % 2 == 0]",
                options = listOf("[0, 2, 4]", "[2, 4, 6]", "[0, 1, 2, 3, 4, 5]", "[2, 4]"),
                correctIndex = 0,
                explanation = "range(6) gera 0, 1, 2, 3, 4, 5. Os pares são 0, 2 e 4.",
                conceptTag = "Filtros em List Comprehension",
                recommendedLessonId = "py_i2_l1",
                recommendedLessonTitle = "Aula 13: List Comprehension com Condicional"
            ),
            KahootQuestion(
                id = "ki_q7",
                question = "Qual exceção é levantada ao tentar converter a string 'abc' para int('abc')?",
                codeSnippet = "int('abc')",
                options = listOf("ValueError", "TypeError", "IndexError", "KeyError"),
                correctIndex = 0,
                explanation = "A conversão falha com ValueError pois os caracteres literais não representam dígitos válidos.",
                conceptTag = "Exceções e ValueError",
                recommendedLessonId = "py_i2_l2",
                recommendedLessonTitle = "Aula 14: Tratamento de Exceções com try / except"
            ),
            KahootQuestion(
                id = "ki_q8",
                question = "Qual é a pré-condição indispensável para executar uma Busca Binária com sucesso?",
                codeSnippet = "busca_binaria(lista, alvo)",
                options = listOf(
                    "A lista precisa estar previamente ordenada",
                    "A lista precisa conter apenas números positivos",
                    "O tamanho da lista deve ser uma potência de 2",
                    "A lista não pode conter elementos repetidos"
                ),
                correctIndex = 0,
                explanation = "A busca binária compara o elemento do meio e descarta metade do espaço, o que só é válido se a lista estiver ordenada.",
                conceptTag = "Busca Binária e Pré-requisitos",
                recommendedLessonId = "py_i3_l1",
                recommendedLessonTitle = "Aula 15: Algoritmo de Busca Binária"
            ),
            KahootQuestion(
                id = "ki_q9",
                question = "Em uma Cifra de César com deslocamento +3, para qual letra o caractere 'x' é mapeado no alfabeto de 26 letras?",
                codeSnippet = "(alfabeto.find('x') + 3) % 26",
                options = listOf("a", "y", "z", "b"),
                correctIndex = 0,
                explanation = "'x' é a posição 23. (23 + 3) % 26 = 26 % 26 = 0, que corresponde à letra 'a'.",
                conceptTag = "Cifra de Substituição e Operador Módulo",
                recommendedLessonId = "py_i1_l2",
                recommendedLessonTitle = "Aula 12: Projeto: Cifra de Substituição"
            ),
            KahootQuestion(
                id = "ki_q10",
                question = "Qual é o valor retornado pela expressão list(map(lambda x: x + 1, [1, 2, 3]))?",
                codeSnippet = "list(map(lambda x: x + 1, [1, 2, 3]))",
                options = listOf("[2, 3, 4]", "[1, 2, 3, 1]", "[3, 4, 5]", "Erro"),
                correctIndex = 0,
                explanation = "A função map aplica a lambda a cada elemento, somando 1: 1+1=2, 2+1=3, 3+1=4.",
                conceptTag = "Função map() e Lambdas",
                recommendedLessonId = "py_i2_l1",
                recommendedLessonTitle = "Aula 13: List Comprehension com Condicional"
            ),
            KahootQuestion(
                id = "ki_q11",
                question = "Qual cláusula do bloco try/except é executada SOMENTE se NENHUMA exceção ocorrer?",
                codeSnippet = "try:\n    ...\nexcept:\n    ...\n???: \n    print('Sucesso sem erros')",
                options = listOf("else", "finally", "then", "catch"),
                correctIndex = 0,
                explanation = "A cláusula else do bloco try/except roda exclusivamente se o bloco try for concluído sem erros.",
                conceptTag = "Cláusula else em Exceções",
                recommendedLessonId = "py_i2_l2",
                recommendedLessonTitle = "Aula 14: Tratamento de Exceções com try / except"
            ),
            KahootQuestion(
                id = "ki_q12",
                question = "Qual método de string remove espaços em branco das extremidades (início e fim)?",
                codeSnippet = "'  texto  '.???()",
                options = listOf("strip()", "trim()", "clean()", "split()"),
                correctIndex = 0,
                explanation = "Em Python, o método .strip() limpa espaços nas pontas da string.",
                conceptTag = "Métodos de String (.strip)",
                recommendedLessonId = "py_i1_l1",
                recommendedLessonTitle = "Aula 11: F-Strings e Formatação Numérica"
            )
        )
    )

    private val unit1Intermediate = LearningUnit(
        id = "py_i1",
        unitNumber = 4,
        tier = DifficultyTier.INTERMEDIATE,
        title = "Strings Avançadas & Cifras",
        description = "F-strings, formatação de precisão, métodos de string e implementação de Cifras de Substituição.",
        lessons = listOf(
            Lesson(
                id = "py_i1_l1",
                unitId = "py_i1",
                title = "F-Strings e Formatação Numérica",
                conceptTitle = "Interpolação Moderna de Variáveis",
                explanation = """
                    F-strings (f"...") permitem inserir variáveis e expressões diretamente dentro do texto, formatando casas decimais com facilidade (ex: {preco:.2f}).
                    
                    🎯 Objetivo da Missão:
                    Dada a constante 'pi' (3.14159265), exiba no terminal a string formatada exatamente como: "Valor: 3.14" utilizando f-string com :.2f.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
pi = 3.14159265

# 🎯 Objetivo: Imprima no console o texto "Valor: 3.14" usando f-string com precisão de 2 casas.
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Utilize uma f-string com a expressão formatada: f\"Valor: {pi:.2f}\"",
                    "O especificador :.2f arredonda e fixa duas casas decimais após o ponto."
                ),
                solutionExplanation = "A formatação :.2f formata números float com duas casas decimais.",
                xpReward = 40,
                testCases = listOf(
                    TestCase(
                        name = "Formatação pi",
                        expectedOutput = "Valor: 3.14",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_i1_l2",
                unitId = "py_i1",
                title = "Projeto: Cifra de Substituição",
                conceptTitle = "Manipulação e Criptografia Clássica",
                explanation = """
                    A Cifra de César desloca cada letra do alfabeto por um número fixo de posições.
                    
                    🎯 Objetivo da Missão:
                    Desloque a mensagem "abc" em +3 posições no alfabeto. O resultado esperado é a string "def". Imprima a mensagem cifrada.
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = """
alfabeto = "abcdefghijklmnopqrstuvwxyz"
deslocamento = 3
mensagem = "abc"

# 🎯 Objetivo: Percorra 'mensagem', desloque cada letra em +3 no alfabeto e imprima a string cifrada resultante ("def").
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Para cada caractere em mensagem, encontre sua posição com alfabeto.find(char).",
                    "Calcule a nova posição com (pos + deslocamento) % 26 e concatene o caractere correspondente."
                ),
                solutionExplanation = "A Cifra de César demonstra controle de índices cíclicos com operador %.",
                xpReward = 50,
                testCases = listOf(
                    TestCase(
                        name = "Cifra deslocada",
                        expectedOutput = "def",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            )
        )
    )

    private val unit2Intermediate = LearningUnit(
        id = "py_i2",
        unitNumber = 5,
        tier = DifficultyTier.INTERMEDIATE,
        title = "Compreensões & Exceções",
        description = "List comprehension com filtros e tratamento de exceções robusto.",
        lessons = listOf(
            Lesson(
                id = "py_i2_l1",
                unitId = "py_i2",
                title = "List Comprehension com Condicional",
                conceptTitle = "Criação Concisa e Elegante de Listas",
                explanation = """
                    Compreensões de lista unem laço e filtro em uma única expressão concisa: [expressao for item in colecao if condicao].
                    
                    🎯 Objetivo da Missão:
                    Dada a lista 'numeros', crie uma nova lista que contenha apenas os números pares multiplicados por 10 e imprima essa lista.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
numeros = [1, 2, 3, 4, 5, 6, 7, 8]

# 🎯 Objetivo: Crie a list comprehension selecionando apenas os números pares (x % 2 == 0)
# e multiplicando-os por 10. Imprima a lista resultante ([20, 40, 60, 80]).
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Utilize a estrutura: [x * 10 for x in numeros if x % 2 == 0]",
                    "Imprima a lista resultante com a função print()."
                ),
                solutionExplanation = "List comprehensions operam em nível de bytecode otimizado no interpretador.",
                xpReward = 45,
                testCases = listOf(
                    TestCase(
                        name = "Pares multiplicados",
                        expectedOutput = "[20, 40, 60, 80]",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_i2_l2",
                unitId = "py_i2",
                title = "Tratamento de Exceções com try / except",
                conceptTitle = "Resiliência e Proteção contra Falhas",
                explanation = """
                    O bloco try/except intercepta erros em tempo de execução sem interromper abruptamente a execução do sistema.
                    
                    🎯 Objetivo da Missão:
                    Ao tentar converter a variável 'entrada' para inteiro com int(entrada), capture o erro ValueError e imprima a mensagem "Entrada Invalida".
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = """
entrada = "abc"

# 🎯 Objetivo: Coloque a conversão int(entrada) dentro de um bloco try.
# No bloco except ValueError, imprima a mensagem: Entrada Invalida
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Abra o bloco try: e tente a conversão int(entrada).",
                    "No bloco except ValueError:, exiba a mensagem 'Entrada Invalida' com print."
                ),
                solutionExplanation = "O bloco try-except evita falhas fatais ao lidar com entradas inseguras.",
                xpReward = 45,
                testCases = listOf(
                    TestCase(
                        name = "Erro capturado",
                        expectedOutput = "Entrada Invalida",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            )
        )
    )

    private val unit3Intermediate = LearningUnit(
        id = "py_i3",
        unitNumber = 6,
        tier = DifficultyTier.INTERMEDIATE,
        title = "Algoritmos Fundamentais: Busca Binária",
        description = "Implementação e análise de complexidade da Busca Binária em listas ordenadas.",
        lessons = listOf(
            Lesson(
                id = "py_i3_l1",
                unitId = "py_i3",
                title = "Algoritmo de Busca Binária",
                conceptTitle = "Estratégia de Divisão e Conquista",
                explanation = """
                    A busca binária encontra elementos em listas ordenadas dividindo o espaço pela metade a cada passo, com desempenho O(log n).
                    
                    🎯 Objetivo da Missão:
                    Implemente a busca binária para encontrar o índice do número 23 na lista ordenada dada. Imprima o resultado no formato: "Posição: 5".
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = """
lista = [2, 5, 8, 12, 16, 23, 38, 56, 72, 91]
alvo = 23

# 🎯 Objetivo: Calcule a posição de 'alvo' na lista ordenada usando busca binária.
# Ao encontrar, imprima: Posição: {indice}
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Defina inicio = 0 e fim = len(lista) - 1. Calcule meio = (inicio + fim) // 2.",
                    "Se lista[meio] < alvo, inicio = meio + 1; se for maior, fim = meio - 1."
                ),
                solutionExplanation = "Busca Binária resolve consultas em tempo logarítmico O(log n).",
                xpReward = 55,
                testCases = listOf(
                    TestCase(
                        name = "Posição encontrada",
                        expectedOutput = "Posição: 5",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            )
        ),
        kahootModule = intermediateKahoot
    )

    // ==========================================
    // TIER 3: AVANÇADO (Arena Final - 12 Questões)
    // ==========================================

    val advancedKahoot = KahootModule(
        id = "kahoot_advanced",
        tier = DifficultyTier.ADVANCED,
        title = "Teste Final: Engenharia de Software com Python",
        description = "Desafio de maestria com 12 questões: POO, métodos dunder, geradores, regex, pilhas e filas!",
        category = "Programação",
        subject = "Python",
        questions = listOf(
            KahootQuestion(
                id = "ka_q1",
                question = "Qual método especial (dunder) define a representação em texto amigável de um objeto?",
                codeSnippet = "class Conta:\n    def ???(self):\n        return f'Conta #{self.id}'",
                options = listOf("__init__", "__repr__", "__str__", "__text__"),
                correctIndex = 2,
                explanation = "O método __str__ é chamado pelas funções print() e str() para representação legível.",
                conceptTag = "Orientação a Objetos e Métodos Especiais (__str__)",
                recommendedLessonId = "py_a1_l1",
                recommendedLessonTitle = "Aula 16: Classes, Construtor e Métodos"
            ),
            KahootQuestion(
                id = "ka_q2",
                question = "Qual palavra-chave transforma uma função comum em uma função geradora (generator)?",
                codeSnippet = "def contador():\n    ??? 1",
                options = listOf("return", "yield", "emit", "async"),
                correctIndex = 1,
                explanation = "A palavra 'yield' pausa a função e produz um valor por vez sob demanda através do protocolo iterator.",
                conceptTag = "Funções Geradoras e Yield",
                recommendedLessonId = "py_a2_l1",
                recommendedLessonTitle = "Aula 18: Geradores com yield"
            ),
            KahootQuestion(
                id = "ka_q3",
                question = "Em regex, qual padrão valida se uma string contém exatamente 4 dígitos numéricos?",
                codeSnippet = "import re\npadrao = r'???'",
                options = listOf("\\d{4}", "[0-9]{1,4}", "\\w{4}", "\\s{4}"),
                correctIndex = 0,
                explanation = "\\d representa qualquer caractere numérico e {4} define a quantidade exata de 4 repetições.",
                conceptTag = "Expressões Regulares (Regex)",
                recommendedLessonId = "py_a2_l2",
                recommendedLessonTitle = "Aula 19: Expressões Regulares (re)"
            ),
            KahootQuestion(
                id = "ka_q4",
                question = "Qual estrutura de dados segue estritamente a política LIFO (Last In, First Out)?",
                codeSnippet = null,
                options = listOf("Fila (Queue)", "Pilha (Stack)", "Lista Encadeada", "Árvore Binária"),
                correctIndex = 1,
                explanation = "Uma Pilha (Stack) insere e remove sempre pelo topo: o último elemento a entrar é o primeiro a sair.",
                conceptTag = "Estruturas de Dados e Pilhas (Stack)",
                recommendedLessonId = "py_a2_l3",
                recommendedLessonTitle = "Aula 20: Estrutura de Dados: Pilha (Stack)"
            ),
            KahootQuestion(
                id = "ka_q5",
                question = "Qual função permite chamar o construtor da classe pai dentro de uma subclasse?",
                codeSnippet = "class Sub(Pai):\n    def __init__(self):\n        ???().__init__()",
                options = listOf("super()", "parent()", "base()", "Pai.self()"),
                correctIndex = 0,
                explanation = "A função super() retorna um objeto proxy que delega chamadas de métodos para a classe ascendente.",
                conceptTag = "Herança e Função super()",
                recommendedLessonId = "py_a1_l2",
                recommendedLessonTitle = "Aula 17: Herança e Polimorfismo"
            ),
            KahootQuestion(
                id = "ka_q6",
                question = "Em Python, qual é o papel do parâmetro 'self' no primeiro argumento dos métodos de instância?",
                codeSnippet = "def metodo(self): ...",
                options = listOf(
                    "Representa a instância específica do objeto que está executando o método",
                    "É uma palavra reservada obrigatória em todo o arquivo",
                    "Cria uma variável global compartilhada entre todas as classes",
                    "Define o tipo estático da classe"
                ),
                correctIndex = 0,
                explanation = "self referencia explicitamente a instância que invocou o método, permitindo acessar seus atributos.",
                conceptTag = "Instâncias e Parâmetro self",
                recommendedLessonId = "py_a1_l1",
                recommendedLessonTitle = "Aula 16: Classes, Construtor e Métodos"
            ),
            KahootQuestion(
                id = "ka_q7",
                question = "Qual é a principal vantagem de um gerador (generator) em relação a uma lista convencional?",
                codeSnippet = "def gen(): yield x",
                options = listOf(
                    "Economiza memória gerando itens sob demanda (lazy evaluation)",
                    "Executa sempre 10 vezes mais rápido que código C",
                    "Permite indexação direta com colchetes",
                    "Impede a ocorrência de erros de sintaxe"
                ),
                correctIndex = 0,
                explanation = "Geradores não alocam todos os itens na memória RAM de uma vez; produzem sob demanda via protocolo iterador.",
                conceptTag = "Avaliação Preguiçosa (Lazy Evaluation)",
                recommendedLessonId = "py_a2_l1",
                recommendedLessonTitle = "Aula 18: Geradores com yield"
            ),
            KahootQuestion(
                id = "ka_q8",
                question = "No módulo re de expressões regulares, qual função retorna todas as ocorrências encontradas em formato de lista?",
                codeSnippet = "import re\nre.???(padrao, texto)",
                options = listOf("findall()", "match()", "search()", "collect()"),
                correctIndex = 0,
                explanation = "re.findall() varre a string inteira e retorna uma lista com todas as correspondências do padrão.",
                conceptTag = "Módulo re e findall()",
                recommendedLessonId = "py_a2_l2",
                recommendedLessonTitle = "Aula 19: Expressões Regulares (re)"
            ),
            KahootQuestion(
                id = "ka_q9",
                question = "Qual método da lista em Python remove e retorna o último elemento inserido (comportamento de pilha)?",
                codeSnippet = "item = lista.???()",
                options = listOf("pop()", "remove()", "delete()", "discard()"),
                correctIndex = 0,
                explanation = "lista.pop() remove e devolve o último item (topo) em tempo O(1).",
                conceptTag = "Operação Pop em Pilhas",
                recommendedLessonId = "py_a2_l3",
                recommendedLessonTitle = "Aula 20: Estrutura de Dados: Pilha (Stack)"
            ),
            KahootQuestion(
                id = "ka_q10",
                question = "Qual método dunder é invocado quando usamos a função len(objeto)?",
                codeSnippet = "len(meu_objeto)",
                options = listOf("__len__", "__size__", "__count__", "__length__"),
                correctIndex = 0,
                explanation = "O interpretador Python redireciona a chamada global len(obj) para o método especial obj.__len__().",
                conceptTag = "Métodos Dunder e Protocolo de Coleções",
                recommendedLessonId = "py_a1_l1",
                recommendedLessonTitle = "Aula 16: Classes, Construtor e Métodos"
            ),
            KahootQuestion(
                id = "ka_q11",
                question = "O que o metacaractere ^ (circunflexo) representa em uma expressão regular?",
                codeSnippet = "re.search(r'^Inicio', texto)",
                options = listOf(
                    "O início exato da string",
                    "O fim da string",
                    "Qualquer dígito numérico",
                    "Um espaço em branco"
                ),
                correctIndex = 0,
                explanation = "Em regex, ^ indica âncora de início de linha/string, e $ indica âncora de final.",
                conceptTag = "Âncoras em Expressões Regulares",
                recommendedLessonId = "py_a2_l2",
                recommendedLessonTitle = "Aula 19: Expressões Regulares (re)"
            ),
            KahootQuestion(
                id = "ka_q12",
                question = "Qual decorator é nativamente utilizado para transformar um método de classe em uma propriedade com sintaxe de atributo?",
                codeSnippet = "@???\ndef total(self): return self._total",
                options = listOf("@property", "@getter", "@attribute", "@field"),
                correctIndex = 0,
                explanation = "@property permite acessar o método como se fosse um atributo direto (obj.total), mantendo encapsulamento.",
                conceptTag = "Encapsulamento e @property",
                recommendedLessonId = "py_a1_l1",
                recommendedLessonTitle = "Aula 16: Classes, Construtor e Métodos"
            )
        )
    )

    private val unit1Advanced = LearningUnit(
        id = "py_a1",
        unitNumber = 7,
        tier = DifficultyTier.ADVANCED,
        title = "Classes, Herança & Métodos Mágicos",
        description = "Programação Orientada a Objetos profissional com construtor __init__, herança com super() e encapsulamento.",
        lessons = listOf(
            Lesson(
                id = "py_a1_l1",
                unitId = "py_a1",
                title = "Classes, Construtor e Métodos",
                conceptTitle = "Modelagem Orientada a Objetos",
                explanation = """
                    Classes agrupam dados (atributos) e funções (métodos). O método __init__ é o construtor executado na instanciação.
                    
                    🎯 Objetivo da Missão:
                    Crie a classe 'Livro' com o construtor __init__(self, titulo, autor) e o método detalhes(self) que retorne a string formatada f"{self.titulo} por {self.autor}".
                    Instancie com ("Clean Code", "Robert Martin") e imprima o retorno de detalhes().
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
# 🎯 Objetivo: Declare a classe Livro com __init__(self, titulo, autor) e o método detalhes(self).
# Em seguida, crie meu_livro = Livro("Clean Code", "Robert Martin") e imprima meu_livro.detalhes().
# Escreva sua classe e execução abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Defina class Livro: com def __init__(self, titulo, autor): associando self.titulo e self.autor.",
                    "No método detalhes(self), retorne a f-string com os dados."
                ),
                solutionExplanation = "Classes encapsulam estado e comportamento de entidades do domínio.",
                xpReward = 50,
                testCases = listOf(
                    TestCase(
                        name = "Detalhes do livro",
                        expectedOutput = "Clean Code por Robert Martin",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_a1_l2",
                unitId = "py_a1",
                title = "Herança e Polimorfismo com super()",
                conceptTitle = "Reaproveitamento e Especialização de Classes",
                explanation = """
                    Subclasses herdam comportamentos da classe mãe. A função super() delega a inicialização para a superclasse.
                    
                    🎯 Objetivo da Missão:
                    Crie a classe Funcionario(nome) e a subclasse Desenvolvedor(Funcionario) que recebe (nome, linguagem). Chame super().__init__(nome), implemente apresentacao(self) retornando f"{self.nome} programa em {self.linguagem}" e exiba a apresentação para ("Pedro", "Python").
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = """
class Funcionario:
    def __init__(self, nome):
        self.nome = nome

# 🎯 Objetivo: Crie a subclasse Desenvolvedor(Funcionario).
# No construtor, use super().__init__(nome) e armazene self.linguagem.
# Crie o método apresentacao(self) retornando "{self.nome} programa em {self.linguagem}".
# Instancie dev = Desenvolvedor("Pedro", "Python") e imprima dev.apresentacao().
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "Dentro de Desenvolvedor.__init__(self, nome, linguagem), invoque super().__init__(nome).",
                    "Instancie o desenvolvedor e passe a chamada dev.apresentacao() para a função print()."
                ),
                solutionExplanation = "A herança especializa estruturas reaproveitando código validado da classe ancestral.",
                xpReward = 60,
                testCases = listOf(
                    TestCase(
                        name = "Apresentação dev",
                        expectedOutput = "Pedro programa em Python",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            )
        )
    )

    private val unit2Advanced = LearningUnit(
        id = "py_a2",
        unitNumber = 8,
        tier = DifficultyTier.ADVANCED,
        title = "Geradores, Regex & Estruturas de Dados",
        description = "Geradores eficientes com yield, expressões regulares com o módulo re e estrutura de Pilha (Stack).",
        lessons = listOf(
            Lesson(
                id = "py_a2_l1",
                unitId = "py_a2",
                title = "Geradores com yield",
                conceptTitle = "Iteradores Sob Demanda",
                explanation = """
                    O comando yield pausa a função e entrega um item por vez, economizando consumo de memória.
                    
                    🎯 Objetivo da Missão:
                    Crie a função geradora gerar_quadrados(limite) que usa yield para fornecer os quadrados dos números de 1 até limite. Converta gerar_quadrados(4) em lista com list() e imprima o resultado ([1, 4, 9, 16]).
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = """
# 🎯 Objetivo: Implemente a função geradora gerar_quadrados(limite) usando laço e yield i * i.
# Converta list(gerar_quadrados(4)) e imprima no console.
# Escreva sua função abaixo:

                """.trimIndent(),
                hints = listOf(
                    "No laço for i in range(1, limite + 1): utilize o comando yield i * i.",
                    "Imprima print(list(gerar_quadrados(4)))."
                ),
                solutionExplanation = "Geradores implementam lazy evaluation, fundamental para grandes fluxos de dados.",
                xpReward = 50,
                testCases = listOf(
                    TestCase(
                        name = "Valores gerados",
                        expectedOutput = "[1, 4, 9, 16]",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_a2_l2",
                unitId = "py_a2",
                title = "Expressões Regulares (re)",
                conceptTitle = "Casamento de Padrões em Textos",
                explanation = """
                    O módulo re do Python permite buscar padrões complexos em strings.
                    
                    🎯 Objetivo da Missão:
                    Utilize re.findall com o padrão r'\b\d{3}\b' para encontrar todos os números de exatamente 3 dígitos presentes no texto fornecido e imprima a lista encontrada.
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = """
import re

texto = "Código 102 confirmado, lote 99 rejeitado, pedido 850 entregue"

# 🎯 Objetivo: Use re.findall(r'\b\d{3}\b', texto) para extrair os números de 3 dígitos e imprima a lista.
# Escreva seu código abaixo:

                """.trimIndent(),
                hints = listOf(
                    "O padrão r'\\b\\d{3}\\b' captura sequências numéricas de 3 dígitos cercadas por limites de palavra.",
                    "Atribua o retorno de re.findall a uma variável e use print()."
                ),
                solutionExplanation = "Expressões regulares fornecem busca e validação determinística de strings.",
                xpReward = 60,
                testCases = listOf(
                    TestCase(
                        name = "Números encontrados",
                        expectedOutput = "['102', '850']",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            ),
            Lesson(
                id = "py_a2_l3",
                unitId = "py_a2",
                title = "Estrutura de Dados: Pilha (Stack)",
                conceptTitle = "Implementação da Política LIFO",
                explanation = """
                    Uma pilha insere e remove elementos sempre pelo topo (Last In, First Out).
                    
                    🎯 Objetivo da Missão:
                    Implemente a classe Pilha com métodos push(item), pop() e topo(). Insira "A", "B", "C", faça um pop() e imprima "Topo: " seguido do elemento restante no topo ("Topo: B").
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = """
# 🎯 Objetivo: Implemente a classe Pilha com lista interna itens, métodos push, pop e topo.
# Faça push("A"), push("B"), push("C"), pop(), e imprima f"Topo: {p.topo()}".
# Escreva sua implementação abaixo:

                """.trimIndent(),
                hints = listOf(
                    "No construtor, crie self.itens = []. No push use append(), no pop use pop().",
                    "O método topo() deve retornar self.itens[-1] se houver itens."
                ),
                solutionExplanation = "Pilhas gerenciam escopos de chamada, parsers sintáticos e algoritmos de histórico.",
                xpReward = 70,
                testCases = listOf(
                    TestCase(
                        name = "Topo da pilha",
                        expectedOutput = "Topo: B",
                        validationType = ValidationType.EXACT_OUTPUT
                    )
                )
            )
        ),
        kahootModule = advancedKahoot
    )

    val pythonUnits = listOf(
        unit1Basic,
        unit2Basic,
        unit3Basic,
        unit1Intermediate,
        unit2Intermediate,
        unit3Intermediate,
        unit1Advanced,
        unit2Advanced
    )

    fun getAllLessons(): List<Lesson> {
        return pythonUnits.flatMap { it.lessons }
    }

    fun getUnitsForTier(tier: DifficultyTier): List<LearningUnit> {
        return pythonUnits.filter { it.tier == tier }
    }

    fun findLesson(lessonId: String): Lesson? {
        for (u in pythonUnits) {
            for (l in u.lessons) {
                if (l.id == lessonId) return l
            }
        }
        return null
    }

    fun findNextLesson(currentLessonId: String): Lesson? {
        val all = getAllLessons()
        val idx = all.indexOfFirst { it.id == currentLessonId }
        return if (idx in 0 until all.size - 1) all[idx + 1] else null
    }

    fun getNextLesson(currentLessonId: String): Lesson? = findNextLesson(currentLessonId)
}
