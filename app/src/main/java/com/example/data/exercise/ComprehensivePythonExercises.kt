package com.example.data.exercise

/**
 * Acervo estruturado de exercícios de Python com distribuição balanceada:
 * 2 Fáceis, 2 Médias e 2 Difíceis para cada assunto do currículo.
 */
object ComprehensivePythonExercises {

    val exercises: List<UniversalExercise> = listOf(
        // ========================================================
        // 1. IF E ELSE (CONDICIONAIS)
        // ========================================================
        UniversalExercise(
            id = "py_cond_facil_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Fácil",
            source = "Python / Controle de Fluxo",
            problemStatement = "Analise o trecho de código abaixo. O que será impresso?",
            codeSnippet = """idade = 17
if idade >= 18:
    print("Maior de idade")
else:
    print("Menor de idade")""",
            terminalOutput = "Menor de idade",
            options = listOf(
                "Menor de idade",
                "Maior de idade",
                "None",
                "Erro de Sintaxe",
                "True"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A variável 'idade' recebe o valor inteiro 17.",
                "Passo 2: A condição do 'if' avalia '17 >= 18', que resulta em False.",
                "Passo 3: Como o 'if' é falso, o fluxo salta diretamente para o bloco 'else:'.",
                "Passo 4: A instrução 'print(\"Menor de idade\")' é executada."
            ),
            pedagogicalAlert = "O operador '>=' requer que o valor seja estritamente maior ou igual. 17 é menor que 18.",
            keyMethod = "Controle de fluxo binário: if <condição>: ... else: ..."
        ),
        UniversalExercise(
            id = "py_cond_facil_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Fácil",
            source = "Python / Operadores Lógicos",
            problemStatement = "Qual mensagem é impressa quando 'tem_ingresso = True' e 'tem_documento = False'?",
            codeSnippet = """tem_ingresso = True
tem_documento = False
if tem_ingresso and tem_documento:
    print("Acesso Permitido")
else:
    print("Acesso Negado")""",
            terminalOutput = "Acesso Negado",
            options = listOf(
                "Acesso Negado",
                "Acesso Permitido",
                "True",
                "False",
                "None"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O operador lógico 'and' exige que ambas as expressões sejam verdadeiras para resultar em True.",
                "Passo 2: Avaliando: True and False resulta em False.",
                "Passo 3: Com resultado False, o ramo 'else' é ativado imprimindo 'Acesso Negado'."
            ),
            pedagogicalAlert = "No operador 'and', basta que um dos operandos seja False para toda a expressão ser False.",
            keyMethod = "Tabela verdade do operador and: True and False == False"
        ),
        UniversalExercise(
            id = "py_cond_med_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Mediano",
            source = "Python / Operador Ternário",
            problemStatement = "Qual é o valor final atribuído à variável 'resultado' pelo operador condicional ternário?",
            codeSnippet = """saldo = 150
limite = 200
resultado = "Aprovado" if saldo + limite >= 350 else "Recusado"
print(resultado)""",
            terminalOutput = "Aprovado",
            options = listOf(
                "Aprovado",
                "Recusado",
                "350",
                "True",
                "SyntaxError"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Em Python, a expressão condicional ternária segue a sintaxe: <valor_se_true> if <condicao> else <valor_se_false>.",
                "Passo 2: A condição avaliada é 'saldo + limite >= 350'.",
                "Passo 3: 150 + 200 = 350, logo 350 >= 350 é True.",
                "Passo 4: Como a condição é True, 'Aprovado' é retornado e atribuído a 'resultado'."
            ),
            pedagogicalAlert = "Não confunda a ordem ternária de Python (X if C else Y) com a de linguagens C/Java (C ? X : Y).",
            keyMethod = "Operador Ternário em Python: valor_se_verdadeiro if condicao else valor_se_falso"
        ),
        UniversalExercise(
            id = "py_cond_med_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Mediano",
            source = "Python / Curto-Circuito Lógico",
            problemStatement = "Analise o comportamento de curto-circuito (short-circuit). O que será impresso no terminal?",
            codeSnippet = """def checar():
    print("Checagem executada")
    return True

valor = False
if valor and checar():
    print("Condição atendida")
else:
    print("Fim")""",
            terminalOutput = "Fim",
            options = listOf(
                "Fim",
                "Checagem executada\nFim",
                "Checagem executada\nCondição atendida",
                "Condição atendida",
                "None"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A expressão condicional avalia 'valor and checar()'.",
                "Passo 2: 'valor' é False. Na conjunção 'and', se o primeiro operando é falso, o Python não avalia o segundo operando (curto-circuito).",
                "Passo 3: A função 'checar()' NUNCA é chamada, logo 'Checagem executada' NÃO é impresso.",
                "Passo 4: O ramo 'else' é executado, imprimindo 'Fim'."
            ),
            pedagogicalAlert = "Avaliação de curto-circuito poupa tempo de CPU e evita execução de efeitos colaterais indesejados.",
            keyMethod = "Curto-circuito lógico: False and <expr> interrompe a avaliação imediatamente"
        ),
        UniversalExercise(
            id = "py_cond_dif_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Difícil",
            source = "Python 3.10+ / Pattern Matching",
            problemStatement = "O que será impresso pela estrutura 'match/case' (Structural Pattern Matching) introduzida no Python moderno?",
            codeSnippet = """status = (404, "Not Found")
match status:
    case (200, msg):
        print(f"OK: {msg}")
    case (404, "Not Found"):
        print("Recurso Inexistente")
    case (codigo, msg) if codigo >= 500:
        print("Erro de Servidor")
    case _:
        print("Outro Status")""",
            terminalOutput = "Recurso Inexistente",
            options = listOf(
                "Recurso Inexistente",
                "OK: Not Found",
                "Erro de Servidor",
                "Outro Status",
                "SyntaxError"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O comando 'match status:' inspeciona a tupla (404, \"Not Found\").",
                "Passo 2: O primeiro case '(200, msg)' falha porque o primeiro elemento 404 != 200.",
                "Passo 3: O segundo case '(404, \"Not Found\")' casa perfeitamente com os valores da tupla.",
                "Passo 4: Imprime 'Recurso Inexistente' e encerra o bloco 'match'."
            ),
            pedagogicalAlert = "Pattern Matching em Python vai além do switch/case tradicional: desestrutura tuplas, listas e objetos dinamicamente.",
            keyMethod = "Structural Pattern Matching com match ... case em tuplas compostas"
        ),
        UniversalExercise(
            id = "py_cond_dif_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Difícil",
            source = "Python Internals / Truthiness & Precedência",
            problemStatement = "Qual é o valor impresso pela combinação de valores falsy e truthy?",
            codeSnippet = """x = []
y = [0]
resultado = (x or y) and (not x and y[0] == 0)
print(resultado)""",
            terminalOutput = "True",
            options = listOf(
                "True",
                "False",
                "[0]",
                "[]",
                "0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Uma lista vazia 'x = []' possui valor booleano False (falsy).",
                "Passo 2: Uma lista com elementos 'y = [0]' possui valor booleano True (truthy).",
                "Passo 3: Na expressão '(x or y)', como x é False, o operador 'or' retorna 'y', ou seja, [0], que é truthy.",
                "Passo 4: Na expressão '(not x and y[0] == 0)': 'not x' é not False == True. E 'y[0] == 0' é True. True and True == True.",
                "Passo 5: [0] and True: como o primeiro é truthy, o operador 'and' retorna o segundo operando: True.",
                "Resultado Final: True."
            ),
            pedagogicalAlert = "Coleções vazias ([], {}, set(), '') avaliam como False em contextos booleanos; com 1 ou mais itens, avaliam como True.",
            keyMethod = "Truthiness de coleções vazias e comportamento do operador booleano and/or"
        ),

        // ========================================================
        // 2. FOR & WHILE (LAÇOS)
        // ========================================================
        UniversalExercise(
            id = "py_loop_facil_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Fácil",
            source = "Python / Laço for com range()",
            problemStatement = "Quantas vezes a palavra 'Loop' será impressa?",
            codeSnippet = """for i in range(1, 5):
    print("Loop")""",
            terminalOutput = "Loop\nLoop\nLoop\nLoop",
            options = listOf(
                "4 vezes",
                "5 vezes",
                "1 vez",
                "3 vezes",
                "Nenhuma vez"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'range(1, 5)' gera os inteiros iniciando em 1 até 4 (o limite superior 5 é exclusivo).",
                "Passo 2: Os valores iterados são i = 1, 2, 3, 4.",
                "Passo 3: O laço executa exatamente 4 iterações, imprimindo 'Loop' 4 vezes."
            ),
            pedagogicalAlert = "Em range(start, stop), o argumento 'stop' nunca é incluído na iteração.",
            keyMethod = "Iteração com range(start, stop): total de passos = stop - start"
        ),
        UniversalExercise(
            id = "py_loop_facil_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Fácil",
            source = "Python / Laço while",
            problemStatement = "Qual será a saída do laço while ao decrementar o contador?",
            codeSnippet = """c = 3
while c > 0:
    print(c, end=" ")
    c -= 1""",
            terminalOutput = "3 2 1 ",
            options = listOf(
                "3 2 1 ",
                "3 2 1 0 ",
                "2 1 0 ",
                "3 3 3 ",
                "Loop infinito"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: c = 3: 3 > 0 é True -> imprime '3 ', c passa a 2.",
                "Passo 2: c = 2: 2 > 0 é True -> imprime '2 ', c passa a 1.",
                "Passo 3: c = 1: 1 > 0 é True -> imprime '1 ', c passa a 0.",
                "Passo 4: c = 0: 0 > 0 é False -> laço encerra.",
                "Resultado Final: '3 2 1 '."
            ),
            pedagogicalAlert = "Certifique-se de decrementar o contador dentro do while para evitar loops infinitos.",
            keyMethod = "Decremento de contador em laço while: c -= 1"
        ),
        UniversalExercise(
            id = "py_loop_med_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Mediano",
            source = "Python / for-else e break",
            problemStatement = "O que será impresso ao iterar sobre a lista procurando o número 7?",
            codeSnippet = """numeros = [2, 4, 6, 8]
for n in numeros:
    if n == 7:
        print("Encontrado")
        break
else:
    print("Não encontrado")""",
            terminalOutput = "Não encontrado",
            options = listOf(
                "Não encontrado",
                "Encontrado",
                "Encontrado\nNão encontrado",
                "SyntaxError",
                "None"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O laço percorre 2, 4, 6 e 8.",
                "Passo 2: Nenhum dos elementos é igual a 7, logo a cláusula 'break' NUNCA é acionada.",
                "Passo 3: Em Python, a cláusula 'else:' vinculada ao 'for' executa SOMENTE quando o laço termina naturalmente sem sofrer 'break'.",
                "Passo 4: Como não houve break, 'Não encontrado' é impresso."
            ),
            pedagogicalAlert = "O 'else' em um laço for roda quando o laço termina por exaustão do iterável, e NÃO a cada iteração.",
            keyMethod = "Padrão de busca for-else com interrupção por break"
        ),
        UniversalExercise(
            id = "py_loop_med_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Mediano",
            source = "Python / continue e acumuladores",
            problemStatement = "Qual é o valor final da variável 'soma'?",
            codeSnippet = """soma = 0
for i in range(1, 10):
    if i % 2 == 0:
        continue
    soma += i
print(soma)""",
            terminalOutput = "25",
            options = listOf(
                "25",
                "45",
                "20",
                "16",
                "9"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'range(1, 10)' gera os números 1, 2, 3, 4, 5, 6, 7, 8, 9.",
                "Passo 2: A condição 'if i % 2 == 0: continue' pula imediatamente todos os números pares.",
                "Passo 3: Apenas os números ímpares são somados: 1 + 3 + 5 + 7 + 9.",
                "Passo 4: 1 + 3 = 4; 4 + 5 = 9; 9 + 7 = 16; 16 + 9 = 25.",
                "Resultado Final: 25."
            ),
            pedagogicalAlert = "A instrução 'continue' interrompe apenas a iteração atual e salta diretamente para a próxima rodada do laço.",
            keyMethod = "Filtragem com continue: soma dos ímpares até 9 = 25"
        ),
        UniversalExercise(
            id = "py_loop_dif_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Difícil",
            source = "Python / Laços Aninhados & Fatoração",
            problemStatement = "Quantos passos serão somados pelo laço aninhado?",
            codeSnippet = """contador = 0
for i in range(1, 5):
    for j in range(i):
        contador += 1
print(contador)""",
            terminalOutput = "10",
            options = listOf(
                "10",
                "16",
                "20",
                "15",
                "6"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O laço externo varia 'i' em [1, 2, 3, 4].",
                "Passo 2: Para cada i, o laço interno executa range(i) vezes (isto é, exatamente i vezes):",
                "  Quando i = 1: roda 1 vez.",
                "  Quando i = 2: roda 2 vezes.",
                "  Quando i = 3: roda 3 vezes.",
                "  Quando i = 4: roda 4 vezes.",
                "Passo 3: O total de execuções é a soma aritmética 1 + 2 + 3 + 4 = 10.",
                "Resultado Final: 10."
            ),
            pedagogicalAlert = "Laços triangulares onde o limite interno depende do índice externo somam 1 + 2 + ... + n = n(n+1)/2 iterações.",
            keyMethod = "Complexidade de laços triangulares: n(n+1)/2"
        ),
        UniversalExercise(
            id = "py_loop_dif_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Difícil",
            source = "Python / Modificação de Coleção durante Iteração",
            problemStatement = "O que acontece ao tentar remover elementos ímpares de uma lista enquanto se itera diretamente sobre ela?",
            codeSnippet = """itens = [1, 2, 3, 4, 5]
for x in itens:
    if x % 2 != 0:
        itens.remove(x)
print(itens)""",
            terminalOutput = "[2, 4]",
            options = listOf(
                "[2, 4]",
                "[2, 4, 5]",
                "[]",
                "RuntimeError: list changed size during iteration",
                "[1, 3, 5]"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Em listas nativas de Python, remover itens durante iteração por for avança o cursor pulando elementos adjacentes.",
                "Passo 2: Ao iterar sobre índices: no idx 0 (item 1), 1 é removido, a lista vira [2, 3, 4, 5].",
                "Passo 3: Próximo idx é 1 (item 3), 3 é removido, a lista vira [2, 4, 5].",
                "Passo 4: Próximo idx é 2 (item 5), 5 é removido, a lista vira [2, 4].",
                "Passo 5: Próximo idx 3 excede o tamanho da lista (2). O laço finaliza com [2, 4]."
            ),
            pedagogicalAlert = "Nunca remova elementos de uma lista iterando sobre ela diretamente; use list comprehension ou itere sobre uma cópia (itens[:]).",
            keyMethod = "Efeito colateral de mutação de lista em iteração: saltos de índice"
        ),

        // ========================================================
        // 3. LISTAS, TUPLAS & FATIAMENTO
        // ========================================================
        UniversalExercise(
            id = "py_list_facil_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Listas, Tuplas & Fatiamento",
            difficulty = "Fácil",
            source = "Python / Listas",
            problemStatement = "Como adicionar o elemento 'maçã' ao final da lista 'frutas = ['uva', 'pera']'?",
            options = listOf(
                "frutas.append('maçã')",
                "frutas.add('maçã')",
                "frutas.push('maçã')",
                "frutas.insert('maçã')",
                "frutas.extend('maçã')"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O método nativo de listas em Python para inserir um único elemento ao final é '.append()'.",
                "Passo 2: '.add()' é usado em sets, '.push()' não existe em Python (é de JavaScript/C++), e '.extend()' adicionaria as letras individualmente se receber uma string."
            ),
            pedagogicalAlert = "Use append(item) para um item e extend(iteravel) para concatenar outra coleção.",
            keyMethod = "Método list.append(x) para inserção no fim da lista"
        ),
        UniversalExercise(
            id = "py_list_facil_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Listas, Tuplas & Fatiamento",
            difficulty = "Fácil",
            source = "Python / Tuplas",
            problemStatement = "O que acontece ao tentar executar 'tup[0] = 99' sendo 'tup = (10, 20, 30)'?",
            options = listOf(
                "Dispara um TypeError informando que a tupla não suporta atribuição de itens.",
                "O valor no índice 0 é atualizado para 99 com sucesso.",
                "A tupla é automaticamente convertida em uma lista.",
                "Dispara um IndexError de índice fora de alcance.",
                "O valor 99 é inserido no final da tupla."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Tuplas em Python são estruturas de dados estritamente IMUTÁVEIS.",
                "Passo 2: Qualquer tentativa de reatribuição de itens após a criação resulta em: TypeError: 'tuple' object does not support item assignment."
            ),
            pedagogicalAlert = "Diferença essencial: Listas são mutáveis ([1, 2]); Tuplas são imutáveis ((1, 2)).",
            keyMethod = "Imutabilidade de tuplas: proteção contra alterações acidentais de dados"
        ),
        UniversalExercise(
            id = "py_list_med_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Listas, Tuplas & Fatiamento",
            difficulty = "Mediano",
            source = "Python / Fatiamento com Passo Negativo",
            problemStatement = "Qual é o resultado da expressão 'texto[::-1]' aplicada à string 'texto = \"PYTHON\"'?",
            options = listOf(
                "\"NOHTYP\"",
                "\"PYTHON\"",
                "\"P\"",
                "\"N\"",
                "IndexError"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A sintaxe de slicing é sequencia[inicio : fim : passo].",
                "Passo 2: Omitir 'inicio' e 'fim' com passo -1 percorre toda a sequência de trás para frente.",
                "Passo 3: A inversão da palavra 'PYTHON' gera 'NOHTYP'."
            ),
            pedagogicalAlert = "O passo -1 no fatiamento é a forma mais rápida e idiomática de inverter sequências em Python.",
            keyMethod = "Inversão de sequências com passo negativo: seq[::-1]"
        ),
        UniversalExercise(
            id = "py_list_med_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Listas, Tuplas & Fatiamento",
            difficulty = "Mediano",
            source = "Python / List Comprehensions",
            problemStatement = "Qual lista será produzida pela seguinte compreensão de lista?",
            codeSnippet = """quadrados = [x**2 for x in range(6) if x % 2 == 0]
print(quadrados)""",
            terminalOutput = "[0, 4, 16]",
            options = listOf(
                "[0, 4, 16]",
                "[4, 16, 36]",
                "[0, 1, 4, 9, 16, 25]",
                "[4, 16]",
                "[0, 2, 4]"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'range(6)' gera os inteiros de 0 a 5: 0, 1, 2, 3, 4, 5.",
                "Passo 2: O filtro 'if x % 2 == 0' seleciona apenas os pares: 0, 2, 4.",
                "Passo 3: A transformação calcula o quadrado de cada par selecionado: 0² = 0, 2² = 4, 4² = 16.",
                "Resultado Final: [0, 4, 16]."
            ),
            pedagogicalAlert = "O filtro 'if' em uma list comprehension é posicionado após o 'for'.",
            keyMethod = "Transformação e filtragem: [expr for item in iter if cond]"
        ),
        UniversalExercise(
            id = "py_list_dif_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Listas, Tuplas & Fatiamento",
            difficulty = "Difícil",
            source = "Python / Cópias Rasas vs Profundas",
            problemStatement = "Qual será o conteúdo de 'original' após as alterações demonstradas no código?",
            codeSnippet = """import copy
original = [[1, 2], [3, 4]]
copia_rasa = list(original)
copia_profunda = copy.deepcopy(original)

copia_rasa[0][0] = 99
copia_profunda[1][0] = 77
print(original)""",
            terminalOutput = "[[99, 2], [3, 4]]",
            options = listOf(
                "[[99, 2], [3, 4]]",
                "[[1, 2], [3, 4]]",
                "[[99, 2], [77, 4]]",
                "[[1, 2], [77, 4]]",
                "[[99, 99], [77, 77]]"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'copia_rasa = list(original)' cria uma cópia rasa (shallow copy). A lista externa é nova, mas as sublistas internas apontam para os mesmos objetos na memória.",
                "Passo 2: 'copia_rasa[0][0] = 99' altera a sublista interna compartilhada, modificando também 'original[0][0]' para 99.",
                "Passo 3: 'copia_profunda = copy.deepcopy(original)' cria uma cópia profunda (deep copy) recursiva de todos os objetos aninhados.",
                "Passo 4: 'copia_profunda[1][0] = 77' altera apenas a cópia isolada, NÃO afetando 'original[1][0]'.",
                "Resultado Final: [[99, 2], [3, 4]]."
            ),
            pedagogicalAlert = "Cuidado com cópias rasas de listas bidimensionais: alterar sublistas afeta a lista original.",
            keyMethod = "Diferença entre cópia rasa (list(x)) e cópia profunda (copy.deepcopy(x))"
        ),
        UniversalExercise(
            id = "py_list_dif_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Listas, Tuplas & Fatiamento",
            difficulty = "Difícil",
            source = "Python / Desempacotamento Estendido",
            problemStatement = "Qual é o valor atribuído a 'resto' no desempacotamento de lista com operador estrela (*)?",
            codeSnippet = """primeiro, *resto, ultimo = [10, 20, 30, 40, 50]
print(resto)""",
            terminalOutput = "[20, 30, 40]",
            options = listOf(
                "[20, 30, 40]",
                "(20, 30, 40)",
                "[20, 30, 40, 50]",
                "[10, 20, 30, 40]",
                "30"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'primeiro' recebe o elemento inicial: 10.",
                "Passo 2: 'ultimo' recebe o elemento final: 50.",
                "Passo 3: A variável prefixada com estrela '*resto' captura todos os elementos intermediários como uma nova LISTA: [20, 30, 40].",
                "Resultado Final: [20, 30, 40]."
            ),
            pedagogicalAlert = "O desempacotamento estendido com '*' sempre produz uma lista, mesmo que o iterável original seja uma tupla.",
            keyMethod = "Desempacotamento flexível: a, *meio, b = colecao"
        ),

        // ========================================================
        // 4. DICIONÁRIOS & SETS
        // ========================================================
        UniversalExercise(
            id = "py_dict_facil_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dicionários & Sets",
            difficulty = "Fácil",
            source = "Python / Dicionários get()",
            problemStatement = "Por que o método 'dados.get(\"chave\", padrao)' é mais seguro do que 'dados[\"chave\"]'?",
            options = listOf(
                "Porque retorna o valor padrão em vez de disparar KeyError se a chave não existir.",
                "Porque criptografa o valor da chave na memória RAM.",
                "Porque converte o valor obtido automaticamente para string.",
                "Porque impede que o dicionário seja alterado por outras threads.",
                "Porque pesquisa a chave em tempo O(log n) em vez de O(n)."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Acessar 'dados[\"chave\"]' para uma chave inexistente dispara a exceção fatal 'KeyError'.",
                "Passo 2: O método '.get(chave, valor_padrao)' captura a ausência de forma graciosa e retorna o valor padrão (ou None se omitido) sem interromper o programa."
            ),
            pedagogicalAlert = "Sempre prefira dict.get(k, default) ao lidar com dicionários de fontes externas (como APIs JSON) onde chaves podem estar ausentes.",
            keyMethod = "Acesso seguro a dicionários com dict.get(key, default)"
        ),
        UniversalExercise(
            id = "py_dict_facil_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dicionários & Sets",
            difficulty = "Fácil",
            source = "Python / Sets e Unicidade",
            problemStatement = "Qual será o tamanho final do conjunto 'conj = set([1, 2, 2, 3, 3, 3, 4])'?",
            options = listOf(
                "4",
                "7",
                "3",
                "1",
                "TypeError"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Conjuntos (sets) em Python são coleções não ordenadas de elementos obrigatoriamente ÚNICOS.",
                "Passo 2: Duplicatas são descartadas automaticamente na conversão.",
                "Passo 3: Os elementos únicos são {1, 2, 3, 4}, totalizando len = 4."
            ),
            pedagogicalAlert = "Sets são ideais para eliminar itens repetidos de uma lista instantaneamente: list(set(itens)).",
            keyMethod = "Eliminação de duplicatas e unicidade em sets"
        ),
        UniversalExercise(
            id = "py_dict_med_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dicionários & Sets",
            difficulty = "Mediano",
            source = "Python / Operações de Conjuntos",
            problemStatement = "Dados os conjuntos A = {1, 2, 3, 4} e B = {3, 4, 5, 6}, qual é o resultado da diferença simétrica 'A ^ B'?",
            options = listOf(
                "{1, 2, 5, 6}",
                "{3, 4}",
                "{1, 2, 3, 4, 5, 6}",
                "{1, 2}",
                "{5, 6}"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A diferença simétrica (operador ^) contém os elementos que estão em A ou em B, mas NÃO em ambos.",
                "Passo 2: A união é {1, 2, 3, 4, 5, 6} e a interseção é {3, 4}.",
                "Passo 3: Subtraindo a interseção da união: {1, 2, 5, 6}.",
                "Resultado Final: {1, 2, 5, 6}."
            ),
            pedagogicalAlert = "Diferença simétrica A ^ B equivale a (A - B) | (B - A).",
            keyMethod = "Operações de teoria dos conjuntos: união (|), interseção (&), diferença (-) e simétrica (^)"
        ),
        UniversalExercise(
            id = "py_dict_med_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dicionários & Sets",
            difficulty = "Mediano",
            source = "Python 3.9+ / Operador de União de Dicionários",
            problemStatement = "O que será impresso pela fusão de dicionários com o operador pipe '|'?",
            codeSnippet = """a = {"x": 1, "y": 2}
b = {"y": 99, "z": 3}
c = a | b
print(c["y"])""",
            terminalOutput = "99",
            options = listOf(
                "99",
                "2",
                "[2, 99]",
                "KeyError",
                "None"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O operador '|' une dois dicionários criando um novo dicionário.",
                "Passo 2: Em caso de chaves coincidentes (como 'y'), o valor do dicionário da DIREITA prevalece sobre o da esquerda.",
                "Passo 3: Como b possui 'y': 99, o valor final em c['y'] é 99."
            ),
            pedagogicalAlert = "No operador de merge '|', a precedência em colisões de chave é sempre do operando à direita.",
            keyMethod = "Merge de dicionários em Python 3.9+: d1 | d2 com sobrescrita à direita"
        ),
        UniversalExercise(
            id = "py_dict_dif_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dicionários & Sets",
            difficulty = "Difícil",
            source = "Python / Hashability & Chaves Válidas",
            problemStatement = "Qual dos seguintes objetos PODE ser utilizado como chave em um dicionário Python?",
            options = listOf(
                "(1, 2, \"texto\")",
                "[1, 2, 3]",
                "{\"a\": 1}",
                "{1, 2, 3}",
                "(1, [2, 3])"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Para ser chave de dicionário em Python, o objeto deve ser HASHABLE (possuir __hash__() imutável ao longo do ciclo de vida).",
                "Passo 2: Listas, dicionários e sets são mutáveis (unhashable) e disparam TypeError: unhashable type.",
                "Passo 3: A tupla '(1, [2, 3])' contém uma lista mutável interna, portanto também é unhashable.",
                "Passo 4: A tupla '(1, 2, \"texto\")' contém apenas elementos estritamente imutáveis, logo é hashable e válida como chave."
            ),
            pedagogicalAlert = "Uma tupla só é hashable se TODOS os seus elementos internos também forem imutáveis.",
            keyMethod = "Requisito de chaves em dicionários: apenas tipos hashable (imutáveis)"
        ),
        UniversalExercise(
            id = "py_dict_dif_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dicionários & Sets",
            difficulty = "Difícil",
            source = "Python / collections.defaultdict",
            problemStatement = "O que será impresso ao acessar uma chave inexistente usando um defaultdict de lista?",
            codeSnippet = """from collections import defaultdict
mapa = defaultdict(list)
mapa["chave_a"].append(10)
mapa["chave_a"].append(20)
print(mapa["chave_b"])""",
            terminalOutput = "[]",
            options = listOf(
                "[]",
                "KeyError",
                "None",
                "[10, 20]",
                "0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'defaultdict(list)' recebe a fábrica de tipos 'list'.",
                "Passo 2: Quando uma chave inexistente (como 'chave_b') é consultada, a fábrica é chamada automaticamente: list() retorna [].",
                "Passo 3: Essa nova lista vazia é inserida no dicionário sob a chave 'chave_b' e retornada.",
                "Passo 4: A impressão exibe '[]' sem disparar KeyError."
            ),
            pedagogicalAlert = "defaultdict instancia automaticamente o valor padrão ao consultar qualquer chave desconhecida.",
            keyMethod = "Fábrica de valores padrão com collections.defaultdict(type)"
        ),

        // ========================================================
        // 5. FUNÇÕES & LAMBDA
        // ========================================================
        UniversalExercise(
            id = "py_func_facil_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Funções & Lambda",
            difficulty = "Fácil",
            source = "Python / Funções Lambda",
            problemStatement = "Qual é a sintaxe correta de uma função anônima lambda que calcula o triplo de um número x?",
            options = listOf(
                "lambda x: x * 3",
                "def lambda(x): return x * 3",
                "lambda(x) -> x * 3",
                "func(x) => x * 3",
                "lambda x { return x * 3 }"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Funções anônimas em Python iniciam com a palavra-chave 'lambda'.",
                "Passo 2: Em seguida vêm os parâmetros separados por vírgula, dois-pontos e a expressão única de retorno implícito: 'lambda x: x * 3'."
            ),
            pedagogicalAlert = "Lambdas em Python não utilizam a palavra 'return'; o resultado da expressão após os dois-pontos é retornado automaticamente.",
            keyMethod = "Sintaxe lambda: lambda <argumentos>: <expressão>"
        ),
        UniversalExercise(
            id = "py_func_facil_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Funções & Lambda",
            difficulty = "Fácil",
            source = "Python / Parâmetros Opcionais",
            problemStatement = "Qual será a saída da função ao ser chamada sem o segundo parâmetro?",
            codeSnippet = """def saudar(nome, saudacao="Olá"):
    return f"{saudacao}, {nome}!"

print(saudar("Ana"))""",
            terminalOutput = "Olá, Ana!",
            options = listOf(
                "Olá, Ana!",
                "Ana!",
                "None, Ana!",
                "TypeError: missing required argument",
                "Olá, None!"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O parâmetro 'saudacao' possui o valor padrão 'Olá'.",
                "Passo 2: Ao chamar 'saudar(\"Ana\")', o argumento opcional é omitido e assume o valor padrão.",
                "Passo 3: A f-string formata 'Olá, Ana!'."
            ),
            pedagogicalAlert = "Parâmetros padrão devem sempre vir após os parâmetros obrigatórios na assinatura da função.",
            keyMethod = "Valores padrão em argumentos de funções: def f(a, b=padrao)"
        ),
        UniversalExercise(
            id = "py_func_med_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Funções & Lambda",
            difficulty = "Mediano",
            source = "Python / map() e filter()",
            problemStatement = "Qual será a lista resultante da combinação de filter e map?",
            codeSnippet = """nums = [1, 2, 3, 4, 5, 6]
pares = filter(lambda x: x % 2 == 0, nums)
dobros = list(map(lambda x: x * 2, pares))
print(dobros)""",
            terminalOutput = "[4, 8, 12]",
            options = listOf(
                "[4, 8, 12]",
                "[2, 4, 6]",
                "[2, 4, 6, 8, 10, 12]",
                "[8, 12]",
                "[4, 16, 36]"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O 'filter(lambda x: x % 2 == 0, nums)' seleciona os pares da lista: 2, 4, 6.",
                "Passo 2: O 'map(lambda x: x * 2, pares)' dobra cada par selecionado: 2*2 = 4, 4*2 = 8, 6*2 = 12.",
                "Passo 3: Convertendo para lista: [4, 8, 12]."
            ),
            pedagogicalAlert = "map() e filter() retornam iteradores preguiçosos (lazy); é necessário envolvê-los em list() para obter a lista materializada.",
            keyMethod = "Pipelines funcionais com filter() e map()"
        ),
        UniversalExercise(
            id = "py_func_med_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Funções & Lambda",
            difficulty = "Mediano",
            source = "Python / sorted() com chave lambda",
            problemStatement = "Qual será a ordem dos alunos ao ordenar a lista de tuplas pela nota (segundo elemento) de forma decrescente?",
            codeSnippet = """alunos = [("Pedro", 7.5), ("Julia", 9.0), ("Lucas", 6.0)]
ordenados = sorted(alunos, key=lambda aluno: aluno[1], reverse=True)
print([a[0] for a in ordenados])""",
            terminalOutput = "['Julia', 'Pedro', 'Lucas']",
            options = listOf(
                "['Julia', 'Pedro', 'Lucas']",
                "['Lucas', 'Pedro', 'Julia']",
                "['Julia', 'Lucas', 'Pedro']",
                "['Pedro', 'Julia', 'Lucas']",
                "[9.0, 7.5, 6.0]"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A função sorted() usa a função lambda 'key=lambda aluno: aluno[1]' para extrair a nota de cada tupla.",
                "Passo 2: As notas são 7.5, 9.0 e 6.0.",
                "Passo 3: Com 'reverse=True', a ordenação é feita da maior para a menor nota: 9.0 (Julia), 7.5 (Pedro), 6.0 (Lucas).",
                "Passo 4: A list comprehension extrai apenas os nomes: ['Julia', 'Pedro', 'Lucas']."
            ),
            pedagogicalAlert = "O parâmetro 'key' em sorted() é uma função de transformação de alta ordem usada como critério de comparação.",
            keyMethod = "Ordenação customizada com sorted(colecao, key=lambda x: ..., reverse=True)"
        ),
        UniversalExercise(
            id = "py_func_dif_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Funções & Lambda",
            difficulty = "Difícil",
            source = "Python / Armadilha de Argumento Padrão Mutável",
            problemStatement = "Analise o clássico comportamento de argumentos padrão mutáveis em Python. O que será impresso?",
            codeSnippet = """def adicionar_item(item, lista=[]):
    lista.append(item)
    return lista

adicionar_item("A")
adicionar_item("B")
print(adicionar_item("C"))""",
            terminalOutput = "['A', 'B', 'C']",
            options = listOf(
                "['A', 'B', 'C']",
                "['C']",
                "['B', 'C']",
                "['A']",
                "TypeError"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Em Python, os valores padrão de argumentos são avaliados UMA ÚNICA VEZ quando a função é definida (tempo de carregamento), e NÃO a cada chamada.",
                "Passo 2: A mesma lista vazia [] criada na definição é reutilizada em todas as chamadas subsequentes que omitem o segundo argumento.",
                "Passo 3: Chamada 1: adiciona 'A' -> lista = ['A'].",
                "Passo 4: Chamada 2: adiciona 'B' -> lista = ['A', 'B'].",
                "Passo 5: Chamada 3: adiciona 'C' -> lista = ['A', 'B', 'C'].",
                "Resultado Final: ['A', 'B', 'C']."
            ),
            pedagogicalAlert = "Boas práticas: Nunca use objetos mutáveis (como [], {}) como valor padrão. Use 'lista=None' e inicialize dentro da função: 'if lista is None: lista = []'.",
            keyMethod = "Armadilha de default mutável em assinaturas de funções"
        ),
        UniversalExercise(
            id = "py_func_dif_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Funções & Lambda",
            difficulty = "Difícil",
            source = "Python / Closures & Late Binding",
            problemStatement = "O que será impresso ao executar a última função da lista de lambdas criadas em um laço?",
            codeSnippet = """funcoes = [lambda: i for i in range(3)]
print([f() for f in funcoes])""",
            terminalOutput = "[2, 2, 2]",
            options = listOf(
                "[2, 2, 2]",
                "[0, 1, 2]",
                "[0, 0, 0]",
                "[3, 3, 3]",
                "NameError: name 'i' is not defined"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Closures em Python utilizam 'late binding' (ligação tardia): a variável livre 'i' é resolvida apenas no momento em que a função lambda é EXECUTADA, e não quando é criada.",
                "Passo 2: Quando o laço de criação termina, a variável 'i' permanece com o seu último valor no escopo: i = 2.",
                "Passo 3: Ao executar '[f() for f in funcoes]', cada uma das três lambdas lê o valor atual de 'i', que é 2.",
                "Resultado Final: [2, 2, 2]."
            ),
            pedagogicalAlert = "Para capturar o valor imediato em cada iteração, use argumento padrão na lambda: 'lambda i=i: i'.",
            keyMethod = "Ligação tardia (late binding) em closures e lambdas dentro de laços"
        ),

        // ========================================================
        // 6. CLASSES & POO
        // ========================================================
        UniversalExercise(
            id = "py_poo_facil_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Classes & POO",
            difficulty = "Fácil",
            source = "Python / Construtor __init__",
            problemStatement = "Qual é o papel do primeiro parâmetro 'self' nos métodos de uma classe Python?",
            options = listOf(
                "Representa a referência à instância específica do objeto que está executando o método.",
                "É uma palavra-chave reservada que define a classe como estática.",
                "Serve para importar automaticamente os módulos pai.",
                "Indica que o método não aceita argumentos externos.",
                "Determina o tipo de retorno da função construtora."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'self' é a convenção em Python que recebe explicitamente a referência da própria instância do objeto.",
                "Passo 2: Permite acessar e modificar os atributos de instância: self.atributo = valor."
            ),
            pedagogicalAlert = "Ao chamar obj.metodo(), o Python passa 'obj' como primeiro argumento 'self' nos bastidores.",
            keyMethod = "Instanciação e referência 'self' em métodos de classe"
        ),
        UniversalExercise(
            id = "py_poo_facil_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Classes & POO",
            difficulty = "Fácil",
            source = "Python / Herança Simples",
            problemStatement = "Como a classe 'Cachorro' herda da classe 'Animal' em Python?",
            options = listOf(
                "class Cachorro(Animal):",
                "class Cachorro extends Animal:",
                "class Cachorro inherits Animal:",
                "class Cachorro: Animal",
                "class Cachorro implements Animal:"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A herança em Python é expressa passando a classe pai entre parênteses logo após o nome da nova classe:",
                "  class Cachorro(Animal):",
                "Passo 2: Palavras como 'extends' ou 'implements' pertencem a Java/TypeScript, não ao Python."
            ),
            pedagogicalAlert = "Python suporta herança múltipla passando várias classes separadas por vírgula: class C(A, B):.",
            keyMethod = "Sintaxe de herança em Python: class Filha(Mae):"
        ),
        UniversalExercise(
            id = "py_poo_med_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Classes & POO",
            difficulty = "Mediano",
            source = "Python / super() e Sobrescrita de Método",
            problemStatement = "O que será impresso ao instanciar o objeto Gerente?",
            codeSnippet = """class Funcionario:
    def __init__(self, nome):
        self.nome = nome

class Gerente(Funcionario):
    def __init__(self, nome, setor):
        super().__init__(nome)
        self.setor = setor

g = Gerente("Mariana", "TI")
print(f"{g.nome} - {g.setor}")""",
            terminalOutput = "Mariana - TI",
            options = listOf(
                "Mariana - TI",
                "Mariana - None",
                "TI - Mariana",
                "AttributeError: 'Gerente' object has no attribute 'nome'",
                "TypeError"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A classe Gerente estende Funcionario.",
                "Passo 2: A chamada 'super().__init__(nome)' invoca o construtor da classe pai Funcionario, inicializando self.nome com 'Mariana'.",
                "Passo 3: A linha seguinte define o atributo exclusivo self.setor com 'TI'.",
                "Passo 4: A interpolação imprime 'Mariana - TI'."
            ),
            pedagogicalAlert = "Se você sobrescrever __init__ sem chamar super().__init__(), os atributos da classe pai não serão inicializados.",
            keyMethod = "Reutilização de construtor da classe base com super().__init__()"
        ),
        UniversalExercise(
            id = "py_poo_med_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Classes & POO",
            difficulty = "Mediano",
            source = "Python / Atributo de Classe vs Instância",
            problemStatement = "Qual será o valor impresso no código abaixo?",
            codeSnippet = """class Contador:
    total = 0
    def __init__(self):
        Contador.total += 1

c1 = Contador()
c2 = Contador()
c3 = Contador()
print(c1.total)""",
            terminalOutput = "3",
            options = listOf(
                "3",
                "1",
                "0",
                "AttributeError",
                "9"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'total = 0' é uma variável/atributo DE CLASSE, compartilhada por todas as instâncias da classe Contador.",
                "Passo 2: A cada nova chamada a Contador(), o construtor incrementa Contador.total em 1.",
                "Passo 3: Três instâncias foram criadas (c1, c2, c3), incrementando o contador para 3.",
                "Passo 4: Acessar c1.total consulta o atributo de classe compartilhado, que vale 3."
            ),
            pedagogicalAlert = "Atributos de classe pertencem à classe e são compartilhados; atributos de instância (self.x) pertencem a cada objeto individual.",
            keyMethod = "Atributos estáticos de classe vs atributos de instância"
        ),
        UniversalExercise(
            id = "py_poo_dif_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Classes & POO",
            difficulty = "Difícil",
            source = "Python / Métodos Especiais (Dunder __str__ e __repr__)",
            problemStatement = "O que será impresso ao exibir o objeto diretamente no console?",
            codeSnippet = """class Ponto:
    def __init__(self, x, y):
        self.x = x
        self.y = y
    def __str__(self):
        return f"Ponto({self.x}, {self.y})"
    def __repr__(self):
        return f"P({self.x},{self.y})"

p = Ponto(3, 4)
print(str(p), repr(p))""",
            terminalOutput = "Ponto(3, 4) P(3,4)",
            options = listOf(
                "Ponto(3, 4) P(3,4)",
                "P(3,4) Ponto(3, 4)",
                "<__main__.Ponto object at 0x...>",
                "Ponto(3, 4) Ponto(3, 4)",
                "P(3,4) P(3,4)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: '__str__()' é voltado para representação legível ao usuário final (acionado por print(obj) e str(obj)). Retorna 'Ponto(3, 4)'.",
                "Passo 2: '__repr__()' é voltado para depuração e desenvolvedores (acionado por repr(obj)). Retorna 'P(3,4)'.",
                "Passo 3: A impressão junta ambos os retornos com espaço: 'Ponto(3, 4) P(3,4)'."
            ),
            pedagogicalAlert = "Se __str__ não for implementado, o Python recorre automaticamente ao __repr__ como fallback.",
            keyMethod = "Sobrecarga de representação de strings com __str__ e __repr__"
        ),
        UniversalExercise(
            id = "py_poo_dif_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Classes & POO",
            difficulty = "Difícil",
            source = "Python / @classmethod vs @staticmethod",
            problemStatement = "Qual é a diferença fundamental entre um método decorado com '@classmethod' e outro com '@staticmethod'?",
            options = listOf(
                "@classmethod recebe a própria classe como primeiro argumento ('cls'), enquanto @staticmethod não recebe nenhuma referência implícita.",
                "@staticmethod só pode ser chamado a partir de instâncias, e @classmethod apenas pela classe.",
                "@classmethod é compilado em C para maior velocidade, e @staticmethod é interpretado.",
                "@classmethod impede que subclasses sobrescrevam o método.",
                "Não há diferença funcional; são aliases idênticos."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Em '@classmethod', o primeiro parâmetro obrigatório é 'cls', apontando para a classe (útil para factory methods polimórficos).",
                "Passo 2: Em '@staticmethod', nenhum argumento implícito (nem self, nem cls) é passado; atua como uma função comum isolada dentro do namespace da classe."
            ),
            pedagogicalAlert = "Use @classmethod para criar construtores alternativos (ex: Ponto.from_json()) que funcionem corretamente mesmo com herança.",
            keyMethod = "Diferenças entre métodos de instância (self), métodos de classe (cls) e métodos estáticos"
        ),

        // ========================================================
        // 7. TRATAMENTO DE EXCEÇÕES
        // ========================================================
        UniversalExercise(
            id = "py_exc_facil_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Tratamento de Exceções",
            difficulty = "Fácil",
            source = "Python / Bloco try-except",
            problemStatement = "Qual bloco é executado quando uma exceção é disparada dentro do bloco 'try'?",
            options = listOf(
                "except",
                "else",
                "catch",
                "throws",
                "pass"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A palavra-chave em Python para capturar erros é 'except'.",
                "Passo 2: 'catch' é utilizado em Java/C++/JavaScript, mas em Python a palavra-chave é estritamente 'except'."
            ),
            pedagogicalAlert = "Evite usar 'except:' pelado sem especificar o tipo de exceção, pois isso captura até interrupções do teclado (Ctrl+C).",
            keyMethod = "Captura de exceções com try e except TipoDoErro:"
        ),
        UniversalExercise(
            id = "py_exc_facil_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Tratamento de Exceções",
            difficulty = "Fácil",
            source = "Python / Bloco finally",
            problemStatement = "Sob quais circunstâncias o bloco 'finally:' é executado em Python?",
            options = listOf(
                "SEMPRE, independentemente de ocorrer ou não uma exceção no bloco try.",
                "Somente quando uma exceção NÃO é disparada.",
                "Somente quando ocorre um erro fatal não tratado.",
                "Apenas se o bloco except for acionado com sucesso.",
                "Apenas uma vez durante a inicialização do programa."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O bloco 'finally' tem garantia de execução incondicional.",
                "Passo 2: Executa após o try bem-sucedido ou após o except lidar com um erro, ideal para limpeza de conexões e fechamento de arquivos."
            ),
            pedagogicalAlert = "O 'finally' roda mesmo se houver um 'return' no meio do bloco try!",
            keyMethod = "Garantia de limpeza com o bloco finally"
        ),
        UniversalExercise(
            id = "py_exc_med_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Tratamento de Exceções",
            difficulty = "Mediano",
            source = "Python / Múltiplos excepts e Ordem de Captura",
            problemStatement = "O que será impresso ao tentar converter uma string inválida e dividir?",
            codeSnippet = """try:
    valor = int("abc")
    res = 10 / valor
except ZeroDivisionError:
    print("Divisão por zero")
except ValueError:
    print("Erro de valor")
except Exception:
    print("Erro genérico")""",
            terminalOutput = "Erro de valor",
            options = listOf(
                "Erro de valor",
                "Divisão por zero",
                "Erro genérico",
                "ValueError",
                "Crash do programa"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A instrução 'int(\"abc\")' falha imediatamente ao tentar converter texto alfabético em inteiro.",
                "Passo 2: A exceção disparada é especificamente um 'ValueError'.",
                "Passo 3: O Python testa os blocos except de cima para baixo: 'ZeroDivisionError' não casa, mas 'ValueError' casa perfeitamente.",
                "Passo 4: Imprime 'Erro de valor'."
            ),
            pedagogicalAlert = "Sempre posicione exceções específicas antes de exceções genéricas (como Exception) para não mascarar a causa raiz do erro.",
            keyMethod = "Hierarquia de captura com múltiplos blocos except"
        ),
        UniversalExercise(
            id = "py_exc_med_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Tratamento de Exceções",
            difficulty = "Mediano",
            source = "Python / raise e Mensagens Customizadas",
            problemStatement = "Como forçar deliberadamente o disparo de uma exceção do tipo TypeError com uma mensagem personalizada?",
            options = listOf(
                "raise TypeError(\"Tipo de dado incompatível\")",
                "throw new TypeError(\"Tipo de dado incompatível\")",
                "except TypeError(\"Tipo de dado incompatível\")",
                "error TypeError(\"Tipo de dado incompatível\")",
                "dispatch TypeError(\"Tipo de dado incompatível\")"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A palavra-chave para disparar exceções explicitamente em Python é 'raise'.",
                "Passo 2: Instancia-se a classe de erro passando a mensagem como parâmetro: 'raise TypeError(\"...\")'."
            ),
            pedagogicalAlert = "'throw' é a sintaxe de C++/Java/JavaScript; em Python utiliza-se 'raise'.",
            keyMethod = "Disparo deliberado de exceções com a palavra-chave raise"
        ),
        UniversalExercise(
            id = "py_exc_dif_1",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Tratamento de Exceções",
            difficulty = "Difícil",
            source = "Python / Criando Exceções Personalizadas",
            problemStatement = "Como definir adequadamente uma classe de exceção personalizada chamada 'SaldoInsuficienteError'?",
            codeSnippet = """class SaldoInsuficienteError(Exception):
    def __init__(self, saldo, saque):
        super().__init__(f"Saldo de {saldo} é menor que saque de {saque}")
        self.saldo = saldo
        self.saque = saque""",
            options = listOf(
                "Herdar diretamente de 'Exception' (ou de uma subclasse específica de exceção).",
                "Herdar da classe 'Error' que é raiz de todas as exceções nativas.",
                "Apenas criar uma classe comum sem herança pois qualquer classe pode ser disparada com raise.",
                "Decorar a classe com '@exception_handler'.",
                "Implementar a interface '__throwable__'."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Todas as exceções personalizadas definidas pelo usuário devem herdar de 'Exception' (ou de 'ValueError'/'RuntimeError').",
                "Passo 2: Em Python não existe classe nativa chamada 'Error' (a raiz é BaseException, mas Exception é a convenção obrigatória para exceções de aplicação)."
            ),
            pedagogicalAlert = "Nunca herde de BaseException diretamente para exceções comuns de aplicação; BaseException captura até sinais de sistema como KeyboardInterrupt.",
            keyMethod = "Criação de exceções personalizadas herdando de Exception"
        ),
        UniversalExercise(
            id = "py_exc_dif_2",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Tratamento de Exceções",
            difficulty = "Difícil",
            source = "Python 3 / Exception Chaining (raise ... from ...)",
            problemStatement = "Para que serve a sintaxe 'raise NovoErro(...) from erro_original' em Python 3?",
            options = listOf(
                "Para preservar o rastreamento (traceback) da causa original do erro via atributo __cause__.",
                "Para desativar a exibição da mensagem de erro no console do terminal.",
                "Para repetir a execução do bloco try automaticamente após a falha.",
                "Para converter o erro em um warning não bloqueante.",
                "Para delegar o tratamento do erro para uma thread em segundo plano."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'raise ... from ...' implementa o encadeamento explícito de exceções (Exception Chaining).",
                "Passo 2: Permite traduzir uma exceção técnica de baixo nível em uma exceção de domínio mais clara, sem perder o rastro da falha original, atribuindo-a a 'e.__cause__'."
            ),
            pedagogicalAlert = "Ao usar 'raise ... from None', o Python suprime o traceback original intencionalmente para não vazar detalhes internos sensíveis.",
            keyMethod = "Encadeamento explícito de exceções: raise CustomError from original_error"
        )
    )
}
