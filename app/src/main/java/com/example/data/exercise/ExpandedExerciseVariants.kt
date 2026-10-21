package com.example.data.exercise

/**
 * Acervo expandido de novas variações de exercícios para o Knowverse.
 * Inclui exercícios práticos de Python avançado, Cálculo I analítico e Matemática Básica,
 * todos com passos de resolução completos, armadilhas conceituais e métodos-chave.
 */
object ExpandedExerciseVariants {

    val variantExercises: List<UniversalExercise> = listOf(
        // ========================================================
        // 1. PYTHON: ESTRUTURAS AVANÇADAS, LAMBDAS, GENERATORS & COMPREHENSIONS
        // ========================================================
        UniversalExercise(
            id = "py_var_01",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dictionary Comprehension & Filtros",
            difficulty = "Mediano",
            source = "Python Avançado / Dicionários",
            problemStatement = "Analise a compreensão de dicionário a seguir. Qual será o conteúdo do dicionário 'pares_quadrados'?",
            codeSnippet = """dados = {"a": 1, "b": 2, "c": 3, "d": 4, "e": 5}
pares_quadrados = {k: v**2 for k, v in dados.items() if v % 2 == 0}
print(pares_quadrados)""",
            terminalOutput = "{'b': 4, 'd': 16}",
            options = listOf(
                "{'b': 4, 'd': 16}",
                "{'a': 1, 'c': 9, 'e': 25}",
                "{'b': 2, 'd': 4}",
                "[4, 16]",
                "SyntaxError"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A estrutura {k: v**2 for k, v in dados.items() if v % 2 == 0} percorre os pares chave-valor de 'dados'.",
                "Passo 2: O filtro 'if v % 2 == 0' seleciona apenas os valores pares (v = 2 para a chave 'b' e v = 4 para a chave 'd').",
                "Passo 3: Para cada chave selecionada, o novo valor é elevado ao quadrado: 2² = 4 e 4² = 16.",
                "Resultado Final: {'b': 4, 'd': 16}."
            ),
            pedagogicalAlert = "Em dictionary comprehensions, a sintaxe padrão é {chave: valor for item in iterável if condição}.",
            keyMethod = "Filtragem e transformação de dicionários: {k: f(v) for k, v in d.items() if cond(v)}"
        ),

        UniversalExercise(
            id = "py_var_02",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Generators & Avaliação Preguiçosa (Lazy)",
            difficulty = "Difícil",
            source = "Python Internals / Geradores",
            problemStatement = "Qual será a sequência impressa no console ao executar o código que utiliza a palavra-chave 'yield'?",
            codeSnippet = """def gerador_sequencia():
    print("Início")
    yield 1
    print("Meio")
    yield 2
    print("Fim")

gen = gerador_sequencia()
val1 = next(gen)
val2 = next(gen)""",
            terminalOutput = "Início\nMeio",
            options = listOf(
                "Início\nMeio",
                "Início\nMeio\nFim",
                "1\n2",
                "Início\n1\nMeio\n2",
                "StopIteration"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Chamar 'gerador_sequencia()' não executa o corpo da função imediatamente; apenas cria o objeto generator.",
                "Passo 2: O primeiro 'next(gen)' executa até o primeiro 'yield 1', imprimindo 'Início' e retornando 1.",
                "Passo 3: O segundo 'next(gen)' retoma exatamente após 'yield 1', executa até 'yield 2', imprimindo 'Meio' e retornando 2.",
                "Passo 4: Como 'next()' não foi chamado uma terceira vez, 'print(\"Fim\")' nunca é executado.",
                "Resultado Final: 'Início' seguido de 'Meio'."
            ),
            pedagogicalAlert = "Geradores em Python mantêm o estado de execução pausado entre chamadas consecutivas de next().",
            keyMethod = "Execução pausável com yield em geradores (Lazy Evaluation)."
        ),

        UniversalExercise(
            id = "py_var_03",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Fatiamento de Listas & Matrizes",
            difficulty = "Fácil",
            source = "Python Básico / Slicing",
            problemStatement = "Dada a lista 'numeros = [10, 20, 30, 40, 50, 60, 70, 80]', qual é o resultado da expressão 'numeros[1:6:2]'?",
            options = listOf(
                "[20, 40, 60]",
                "[10, 30, 50]",
                "[20, 50]",
                "[20, 30, 40, 50, 60]",
                "[40, 60, 80]"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A sintaxe de fatiamento é lista[início : fim : passo].",
                "Passo 2: início = 1 (índice do elemento 20).",
                "Passo 3: fim = 6 (índice 6 é 70, não incluído, intervalo semiaberto [1, 6)).",
                "Passo 4: passo = 2 (salta de dois em dois índices: 1, 3, 5).",
                "Passo 5: Elementos nos índices 1, 3 e 5: numeros[1] = 20, numeros[3] = 40, numeros[5] = 60.",
                "Resultado Final: [20, 40, 60]."
            ),
            pedagogicalAlert = "O índice de parada em fatiamentos Python é exclusivo: lista[a:b] vai do índice 'a' até 'b-1'.",
            keyMethod = "Slicing com passo: lista[start:stop:step]"
        ),

        UniversalExercise(
            id = "py_var_04",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Função zip() e Transposição de Matriz",
            difficulty = "Mediano",
            source = "Python Idiomático / Matrizes",
            problemStatement = "Considere a matriz 2x3 representada como lista de listas: 'matriz = [[1, 2, 3], [4, 5, 6]]'. Qual é o resultado de '[list(col) for col in zip(*matriz)]'?",
            codeSnippet = """matriz = [[1, 2, 3], [4, 5, 6]]
transposta = [list(col) for col in zip(*matriz)]
print(transposta)""",
            terminalOutput = "[[1, 4], [2, 5], [3, 6]]",
            options = listOf(
                "[[1, 4], [2, 5], [3, 6]]",
                "[[4, 5, 6], [1, 2, 3]]",
                "[[1, 2, 3], [4, 5, 6]]",
                "[(1, 4), (2, 5), (3, 6)]",
                "[[1, 2], [3, 4], [5, 6]]"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O operador de desempacotamento '*matriz' passa [1, 2, 3] e [4, 5, 6] como dois argumentos separados para zip().",
                "Passo 2: zip([1, 2, 3], [4, 5, 6]) agrupa os elementos de mesmo índice: (1, 4), (2, 5) e (3, 6).",
                "Passo 3: A list comprehension converte cada tupla em lista: [list(col) ...].",
                "Resultado Final: [[1, 4], [2, 5], [3, 6]] (a matriz transposta de dimensão 3x2)."
            ),
            pedagogicalAlert = "O padrão 'zip(*matriz)' é a forma mais idiomática e veloz de calcular a transposta de uma matriz em Python sem usar bibliotecas externas.",
            keyMethod = "Transposição matricial: zip(*matriz)"
        ),

        UniversalExercise(
            id = "py_var_05",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "try, except, else e finally",
            difficulty = "Mediano",
            source = "Python / Tratamento de Exceções",
            problemStatement = "O que será impresso no console ao executar o código a seguir?",
            codeSnippet = """def teste_fluxo(a, b):
    try:
        res = a / b
    except ZeroDivisionError:
        print("Erro", end=" ")
    else:
        print("Sucesso", end=" ")
    finally:
        print("Fim")

teste_fluxo(10, 2)""",
            terminalOutput = "Sucesso Fim",
            options = listOf(
                "Sucesso Fim",
                "Sucesso",
                "Erro Fim",
                "Erro Sucesso Fim",
                "Fim"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A divisão 10 / 2 é realizada com sucesso (res = 5.0) sem disparar exceção.",
                "Passo 2: O bloco 'except ZeroDivisionError' é ignorado.",
                "Passo 3: O bloco 'else' executa exclusivamente quando nenhuma exceção ocorre no 'try', imprimindo 'Sucesso '.",
                "Passo 4: O bloco 'finally' SEMPRE executa, independente de haver erro ou não, imprimindo 'Fim'.",
                "Resultado Final: 'Sucesso Fim'."
            ),
            pedagogicalAlert = "O bloco 'else' em tratamento de exceções só roda se o 'try' for bem-sucedido; o 'finally' roda invariavelmente.",
            keyMethod = "Controle de fluxo de exceções: try -> else -> finally"
        ),

        // ========================================================
        // 2. CÁLCULO I: LIMITES TRIGONOMÉTRICOS, L'HÔPITAL & DERIVADAS
        // ========================================================
        UniversalExercise(
            id = "calc_var_01",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limite Trigonométrico Fundamental",
            difficulty = "Mediano",
            source = "Cálculo I / P1 Engenharia",
            problemStatement = """Calcule o limite trigonométrico:
\[ \lim_{x \to 0} \frac{1 - \cos x}{x^2} \]""",
            options = listOf(
                "1/2",
                "1",
                "0",
                "2",
                "Não existe"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Substituição direta: (1 - cos 0)/0² = (1 - 1)/0 = 0/0 (indeterminação).",
                "Passo 2: Multiplicar numerador e denominador pelo conjugado trigonométrico (1 + cos x):",
                "  [(1 - cos x)(1 + cos x)] / [x² (1 + cos x)] = (1 - cos² x) / [x² (1 + cos x)].",
                "Passo 3: Pela identidade fundamental, 1 - cos² x = sen² x:",
                "  [sen² x / x²] · [1 / (1 + cos x)].",
                "Passo 4: Aplicar o limite fundamental lim_{x -> 0} (sen x / x) = 1:",
                "  (1)² · [1 / (1 + cos 0)] = 1 · [1 / (1 + 1)] = 1/2.",
                "Resultado Final: 1/2."
            ),
            pedagogicalAlert = "O limite lim_{x -> 0} (1 - cos x)/x² = 1/2 é um dos limites notáveis mais cobrados e é a base da aproximação de Taylor para o cosseno.",
            keyMethod = "Conjugado trigonométrico: 1 - cos x = sen² x / (1 + cos x)"
        ),

        UniversalExercise(
            id = "calc_var_02",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Regra de L'Hôpital (Indeterminação 0/0)",
            difficulty = "Mediano",
            source = "Cálculo I / Provas Politécnica",
            problemStatement = """Determine o valor do limite utilizando a Regra de L'Hôpital:
\[ \lim_{x \to 0} \frac{e^{3x} - 1 - 3x}{x^2} \]""",
            options = listOf(
                "9/2",
                "3/2",
                "3",
                "9",
                "0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Substituição direta: (e⁰ - 1 - 0)/0 = (1 - 1 - 0)/0 = 0/0 (indeterminação que autoriza L'Hôpital).",
                "Passo 2: Primeira aplicação de L'Hôpital: derivar numerador e denominador separadamente em relação a x:",
                "  d/dx [e^{3x} - 1 - 3x] = 3e^{3x} - 3",
                "  d/dx [x²] = 2x",
                "  Novo limite: lim_{x -> 0} (3e^{3x} - 3) / (2x).",
                "Passo 3: Substituição em x = 0: (3(1) - 3)/0 = 0/0 (continua indeterminado).",
                "Passo 4: Segunda aplicação de L'Hôpital:",
                "  d/dx [3e^{3x} - 3] = 9e^{3x}",
                "  d/dx [2x] = 2",
                "  Novo limite: lim_{x -> 0} (9e^{3x}) / 2 = 9(1) / 2 = 9/2.",
                "Resultado Final: 9/2 (ou 4,5)."
            ),
            pedagogicalAlert = "Aplique L'Hôpital derivando numerador e denominador individualmente, NUNCA use a regra do quociente para aplicar L'Hôpital!",
            keyMethod = "Regra de L'Hôpital sucessiva: lim [f(x)/g(x)] = lim [f'(x)/g'(x)] = lim [f''(x)/g''(x)]"
        ),

        UniversalExercise(
            id = "calc_var_03",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivação Implícita",
            difficulty = "Difícil",
            source = "Cálculo I / Retas Tangentes Implícitas",
            problemStatement = """Uma circunferência é descrita pela equação implícita:
\[ x^2 + y^2 = 25 \]
Qual é a inclinação (coeficiente angular) da reta tangente à curva no ponto P(3, 4)?""",
            options = listOf(
                "-3/4",
                "-4/3",
                "3/4",
                "4/3",
                "-3/5"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplicar a derivada d/dx em ambos os lados da equação, tratando y como função implícita de x (regra da cadeia para y²):",
                "  d/dx [x² + y²] = d/dx [25]",
                "  2x + 2y · (dy/dx) = 0.",
                "Passo 2: Isolar dy/dx:",
                "  2y · (dy/dx) = -2x => dy/dx = -x / y.",
                "Passo 3: Substituir as coordenadas do ponto P(3, 4): x = 3 e y = 4:",
                "  m = dy/dx |_{(3, 4)} = -3 / 4.",
                "Resultado Final: -3/4 (ou -0,75)."
            ),
            pedagogicalAlert = "Na derivação implícita, sempre que derivar qualquer termo contendo a variável dependente y, multiplique pelo fator dy/dx (ou y').",
            keyMethod = "Derivação Implícita: d/dx[y^n] = n y^{n-1} · y'"
        ),

        UniversalExercise(
            id = "calc_var_04",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integração por Partes",
            difficulty = "Mediano",
            source = "Cálculo I / Primitivas",
            problemStatement = """Calcule a integral indefinida utilizando o método de integração por partes:
\[ \int x \cdot e^{2x} \, dx \]""",
            options = listOf(
                "(x/2) e^{2x} - (1/4) e^{2x} + C",
                "x e^{2x} - e^{2x} + C",
                "(x/2) e^{2x} + (1/4) e^{2x} + C",
                "(x²/4) e^{2x} + C",
                "(1/2) e^{2x} + C"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A fórmula da integração por partes é: ∫ u dv = u·v - ∫ v du.",
                "Passo 2: Pela regra de prioridade LIATE (Algébrica antes de Exponencial):",
                "  u = x  =>  du = dx",
                "  dv = e^{2x} dx  =>  v = ∫ e^{2x} dx = (1/2) e^{2x}.",
                "Passo 3: Aplicar na fórmula:",
                "  ∫ x e^{2x} dx = x · ((1/2) e^{2x}) - ∫ ((1/2) e^{2x}) dx.",
                "Passo 4: Integrar o termo restante:",
                "  (x/2) e^{2x} - (1/2) · ((1/2) e^{2x}) + C = (x/2) e^{2x} - (1/4) e^{2x} + C.",
                "Resultado Final: (x/2) e^{2x} - (1/4) e^{2x} + C = ((2x - 1)/4) e^{2x} + C."
            ),
            pedagogicalAlert = "A regra LIATE (Logarítmica, Inversa trig, Algébrica, Trigonométrica, Exponencial) indica qual termo deve ser escolhido como 'u'.",
            keyMethod = "Integração por partes: ∫ u dv = u v - ∫ v du com LIATE"
        ),

        UniversalExercise(
            id = "calc_var_05",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integração por Substituição (u-du)",
            difficulty = "Fácil",
            source = "Cálculo I / Integrais Imediatas",
            problemStatement = """Resolva a integral indefinida:
\[ \int \frac{2x}{x^2 + 9} \, dx \]""",
            options = listOf(
                "ln(x² + 9) + C",
                "2 ln(x² + 9) + C",
                "arctan(x/3) + C",
                "1 / (x² + 9) + C",
                "x² ln(x² + 9) + C"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Identificar a derivada do denominador presente no numerador:",
                "  Seja u = x² + 9.",
                "Passo 2: Calcular a diferencial: du = 2x dx.",
                "Passo 3: Substituir na integral:",
                "  ∫ (2x dx) / (x² + 9) = ∫ (1 / u) du.",
                "Passo 4: A integral de 1/u é ln|u| + C:",
                "  ln|x² + 9| + C.",
                "Passo 5: Como x² + 9 > 0 para todo x real, dispensa-se o módulo.",
                "Resultado Final: ln(x² + 9) + C."
            ),
            pedagogicalAlert = "Sempre que a derivada do denominador aparecer no numerador multiplicada por constante, a integral resulta em um logaritmo natural.",
            keyMethod = "Regra da substituição com logaritmo: ∫ [f'(x) / f(x)] dx = ln|f(x)| + C"
        ),

        // ========================================================
        // 3. MATEMÁTICA BÁSICA & RACIOCÍNIO LÓGICO (ENEM & FUVEST)
        // ========================================================
        UniversalExercise(
            id = "mb_var_01",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria Espacial • Cilindros",
            difficulty = "Mediano",
            source = "ENEM / Geometria & Volumes",
            problemStatement = "Um reservatório cilíndrico reto possui raio da base igual a 3 metros e altura de 10 metros. Se utilizarmos a aproximação π = 3,14, quantos litros de água esse reservatório comporta quando totalmente cheio?",
            options = listOf(
                "282.600 litros",
                "28.260 litros",
                "94.200 litros",
                "314.000 litros",
                "188.400 litros"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A fórmula do volume do cilindro reto é V = Área da base × altura = π · r² · h.",
                "Passo 2: Substituir os valores dados (r = 3 m, h = 10 m, π = 3,14):",
                "  V = 3,14 · (3)² · 10 = 3,14 · 9 · 10 = 3,14 · 90 = 282,6 m³.",
                "Passo 3: Lembrar da conversão fundamental de medidas: 1 m³ equivale exatamente a 1.000 litros.",
                "Passo 4: Volume em litros: 282,6 × 1.000 = 282.600 litros.",
                "Resultado Final: 282.600 litros."
            ),
            pedagogicalAlert = "A conversão 1 m³ = 1.000 litros e 1 dm³ = 1 litro é cobrada anualmente no ENEM.",
            keyMethod = "Volume cilíndrico: V = π r² h e conversão 1 m³ = 1.000 L"
        ),

        UniversalExercise(
            id = "mb_var_02",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Probabilidade Condicional",
            difficulty = "Mediano",
            source = "FUVEST / Probabilidade",
            problemStatement = "Em uma urna há 5 bolas azuis e 7 bolas vermelhas. Duas bolas são sorteadas sucessivamente e sem reposição. Qual é a probabilidade de que a primeira bola seja azul e a segunda bola seja vermelha?",
            options = listOf(
                "35/132",
                "35/144",
                "5/12",
                "7/12",
                "12/132"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Total de bolas na urna no início: 5 azuis + 7 vermelhas = 12 bolas.",
                "Passo 2: Probabilidade do primeiro evento (1ª ser azul): P(A) = 5 / 12.",
                "Passo 3: Como o sorteio é sem reposição, restam 11 bolas na urna, das quais ainda há 7 vermelhas.",
                "Passo 4: Probabilidade condicional do segundo evento: P(V|A) = 7 / 11.",
                "Passo 5: Pelo princípio multiplicativo da probabilidade: P(A e V) = (5/12) · (7/11) = 35 / 132.",
                "Resultado Final: 35/132."
            ),
            pedagogicalAlert = "Em sorteios sem reposição, o espaço amostral diminui em 1 unidade para cada retirada consecutiva.",
            keyMethod = "Princípio multiplicativo: P(A ∩ B) = P(A) · P(B|A)"
        ),

        UniversalExercise(
            id = "mb_var_03",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Juros Compostos e Matemática Financeira",
            difficulty = "Difícil",
            source = "ENEM / Matemática Financeira",
            problemStatement = "Um capital de R$ 5.000,00 foi aplicado sob regime de juros compostos a uma taxa de 10% ao ano durante 2 anos. Qual foi o montante final resgatado e o rendimento total de juros acumulados?",
            options = listOf(
                "Montante: R$ 6.050,00 | Juros: R$ 1.050,00",
                "Montante: R$ 6.000,00 | Juros: R$ 1.000,00",
                "Montante: R$ 5.500,00 | Juros: R$ 500,00",
                "Montante: R$ 6.100,00 | Juros: R$ 1.100,00",
                "Montante: R$ 6.250,00 | Juros: R$ 1.250,00"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A fórmula dos juros compostos é M = C · (1 + i)^t.",
                "Passo 2: Identificar os parâmetros: Capital C = 5.000, taxa i = 10% = 0,10 ao ano, tempo t = 2 anos.",
                "Passo 3: Calcular o fator de acumulação: (1 + 0,10)² = (1,10)² = 1,21.",
                "Passo 4: Calcular o Montante: M = 5.000 × 1,21 = R$ 6.050,00.",
                "Passo 5: Os juros correspondem à diferença M - C: 6.050 - 5.000 = R$ 1.050,00 (observar que nos juros simples seriam 1.000,00).",
                "Resultado Final: Montante R$ 6.050,00 e Juros R$ 1.050,00."
            ),
            pedagogicalAlert = "Juros compostos crescem exponencialmente (juros sobre juros); juros simples crescem linearmente.",
            keyMethod = "Juros Compostos: M = C (1 + i)^t e J = M - C"
        ),

        // Python Avançado Adicional
        UniversalExercise(
            id = "py_var_06",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Recursão e Memoization (@lru_cache)",
            difficulty = "Mediano",
            source = "Python Avançado / Algoritmos",
            problemStatement = "O código a seguir calcula o n-ésimo número de Fibonacci utilizando a técnica de Memoization manual com dicionário. Qual será o valor impresso no console?",
            codeSnippet = """memo = {0: 0, 1: 1}
def fib(n):
    if n not in memo:
        memo[n] = fib(n - 1) + fib(n - 2)
    return memo[n]

print(fib(7))""",
            terminalOutput = "13",
            options = listOf(
                "13",
                "8",
                "21",
                "7",
                "RecursionError"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A sequência de Fibonacci inicia em: fib(0)=0, fib(1)=1, fib(2)=1, fib(3)=2, fib(4)=3, fib(5)=5, fib(6)=8, fib(7)=13.",
                "Passo 2: Com o dicionário memo, cada subproblema é calculado exatamente uma vez, reduzindo a complexidade de tempo de O(2^n) para O(n).",
                "Passo 3: A consulta final retorna memo[7] = 13.",
                "Resultado Final: 13."
            ),
            pedagogicalAlert = "Em Python profissional, podemos usar o decorador '@functools.lru_cache(maxsize=None)' para obter memoization automática sem gerenciar dicionários globais.",
            keyMethod = "Memoization (Programação Dinâmica Top-Down): memo[n] = f(n-1) + f(n-2)"
        ),

        UniversalExercise(
            id = "py_var_07",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Gerenciadores de Contexto (with statement)",
            difficulty = "Mediano",
            source = "Python / Boas Práticas",
            problemStatement = "Por que a construção 'with open(\"dados.txt\", \"r\") as f:' é considerada a forma correta e idiomática de abrir arquivos em Python?",
            options = listOf(
                "Porque garante o fechamento automático do arquivo (f.close()) mesmo se ocorrer uma exceção.",
                "Porque carrega o arquivo diretamente na memória RAM sem consumir descritores de arquivo.",
                "Porque impede que o arquivo seja editado por outros programas concorrentes no sistema operacional.",
                "Porque converte automaticamente todo o conteúdo textual em objetos JSON nativos.",
                "Porque executa a leitura do arquivo em uma thread separada em segundo plano."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A declaração 'with' ativa o protocolo Context Manager em Python.",
                "Passo 2: Ao entrar no bloco, o método '__enter__()' é chamado e retorna o descritor de arquivo.",
                "Passo 3: Ao sair do bloco (seja por término normal ou por disparo de exceção), o método '__exit__()' é invocado obrigatoriamente.",
                "Passo 4: No caso de arquivos, '__exit__()' executa 'close()', evitando vazamento de recursos no sistema operacional.",
                "Resultado Final: Garante o fechamento automático do arquivo mesmo se ocorrer exceção."
            ),
            pedagogicalAlert = "Abrir arquivos com 'open()' sem 'with' pode deixar descritores abertos e causar travamentos no sistema ou bloqueios de escrita.",
            keyMethod = "Protocolo de Context Manager: __enter__() e __exit__() para liberação segura de recursos"
        ),

        UniversalExercise(
            id = "py_var_08",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Coleções Especializadas: Counter & Frequência",
            difficulty = "Fácil",
            source = "Python Standard Library / collections",
            problemStatement = "Considere a utilização do módulo 'collections.Counter'. O que será impresso no terminal após a execução do trecho abaixo?",
            codeSnippet = """from collections import Counter
palavras = ["sol", "lua", "sol", "estrela", "lua", "sol"]
contagem = Counter(palavras)
print(contagem.most_common(1)[0])""",
            terminalOutput = "('sol', 3)",
            options = listOf(
                "('sol', 3)",
                "[('sol', 3)]",
                "3",
                "{'sol': 3}",
                "('lua', 2)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: 'Counter(palavras)' contabiliza as ocorrências de cada termo: 'sol': 3, 'lua': 2, 'estrela': 1.",
                "Passo 2: O método 'most_common(1)' retorna uma lista com o top-1 elemento mais frequente: [('sol', 3)].",
                "Passo 3: O índice '[0]' extrai a primeira tupla da lista: ('sol', 3).",
                "Resultado Final: ('sol', 3)."
            ),
            pedagogicalAlert = "O método 'most_common(n)' de um Counter retorna uma lista de tuplas (elemento, contagem) ordenadas por frequência decrescente.",
            keyMethod = "Contagem e ordenação por frequência com collections.Counter"
        ),

        UniversalExercise(
            id = "py_var_09",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Empacotamento de Argumentos: *args e **kwargs",
            difficulty = "Mediano",
            source = "Python / Funções Avançadas",
            problemStatement = "Qual será a saída do código que desempacota tuplas e dicionários em argumentos de função?",
            codeSnippet = """def operacao(a, b, c=10):
    return a * b + c

valores = (3, 4)
extras = {"c": 5}
print(operacao(*valores, **extras))""",
            terminalOutput = "17",
            options = listOf(
                "17",
                "22",
                "12",
                "TypeError",
                "70"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: '*valores' desempacota a tupla (3, 4) nos parâmetros posicionais: a = 3 e b = 4.",
                "Passo 2: '**extras' desempacota o dicionário {'c': 5} no parâmetro nomeado c, sobrescrevendo o valor padrão 10 para c = 5.",
                "Passo 3: A função calcula: a * b + c = 3 * 4 + 5 = 12 + 5 = 17.",
                "Resultado Final: 17."
            ),
            pedagogicalAlert = "O asterisco simples '*' desempacota iteráveis (listas/tuplas) em argumentos posicionais; o asterisco duplo '**' desempacota dicionários em argumentos nomeados.",
            keyMethod = "Desempacotamento de argumentos: func(*args, **kwargs)"
        ),

        UniversalExercise(
            id = "py_var_10",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Orientação a Objetos: Propriedades e Encapsulamento",
            difficulty = "Mediano",
            source = "Python / POO",
            problemStatement = "O que acontece ao tentar atribuir um valor negativo à propriedade 'preco' da classe Produto?",
            codeSnippet = """class Produto:
    def __init__(self, preco):
        self._preco = preco

    @property
    def preco(self):
        return self._preco

    @preco.setter
    def preco(self, valor):
        if valor < 0:
            raise ValueError("Preço não pode ser negativo")
        self._preco = valor

p = Produto(100)
p.preco = -50""",
            terminalOutput = "ValueError: Preço não pode ser negativo",
            options = listOf(
                "Gera uma exceção 'ValueError: Preço não pode ser negativo'",
                "O valor de p._preco passa a ser -50 normalmente",
                "O valor é ignorado e permanece 100 sem erros",
                "Gera um erro de sintaxe 'AttributeError'",
                "Converte automaticamente o valor para o módulo positivo 50"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A anotação '@property' transforma o método preco() em um getter.",
                "Passo 2: O decorador '@preco.setter' intercepta qualquer atribuição de valor 'p.preco = x'.",
                "Passo 3: Na linha 'p.preco = -50', o setter é disparado com 'valor = -50'.",
                "Passo 4: A validação 'if valor < 0:' é satisfeita e a instrução 'raise ValueError(...)' é executada imediatamente.",
                "Resultado Final: Disparo de ValueError."
            ),
            pedagogicalAlert = "Em Python idiomático, use decoradores @property para getters e setters em vez de criar métodos tradicionais get_x() e set_x().",
            keyMethod = "Encapsulamento pitônico com @property e @<prop>.setter"
        ),

        // Cálculo I Adicional
        UniversalExercise(
            id = "calc_var_06",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais Impróprias e Convergência",
            difficulty = "Difícil",
            source = "Cálculo I / Análise Real",
            problemStatement = """Calcule a integral imprópria com limite superior infinito:
\[ \int_{1}^{\infty} \frac{1}{x^2} \, dx \]""",
            options = listOf(
                "1 (Converge para 1)",
                "Diverge para +∞",
                "0",
                "1/2",
                "2"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Uma integral com limite superior infinito é definida como o limite: lim_{b -> ∞} ∫_1^b x^{-2} dx.",
                "Passo 2: Encontrar a primitiva de x^{-2}: ∫ x^{-2} dx = -x^{-1} = -1/x.",
                "Passo 3: Aplicar os limites de integração pelo Teorema Fundamental do Cálculo:",
                "  [-1/b] - [-1/1] = 1 - 1/b.",
                "Passo 4: Calcular o limite quando b tende ao infinito:",
                "  lim_{b -> ∞} (1 - 1/b) = 1 - 0 = 1.",
                "Resultado Final: A integral converge e seu valor é exatamente 1."
            ),
            pedagogicalAlert = "Pelo critério da p-integral, ∫_1^∞ (1/x^p) dx converge se e somente se p > 1. Como aqui p = 2 > 1, ela converge.",
            keyMethod = "Integral imprópria de 1ª espécie: ∫_a^∞ f(x) dx = lim_{b -> ∞} ∫_a^b f(x) dx"
        ),

        UniversalExercise(
            id = "calc_var_07",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Teorema do Valor Médio (TVM de Lagrange)",
            difficulty = "Mediano",
            source = "Cálculo I / Teoremas Fundamentais",
            problemStatement = """Dada a função \( f(x) = x^2 \) no intervalo fechado \([0, 4]\), encontre o valor de \( c \in (0, 4) \) garantido pelo Teorema do Valor Médio tal que:
\[ f'(c) = \frac{f(4) - f(0)}{4 - 0} \]""",
            options = listOf(
                "c = 2",
                "c = 1",
                "c = 2,5",
                "c = √2",
                "c = 3"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O Teorema do Valor Médio afirma que se f é contínua em [a, b] e derivável em (a, b), existe pelo menos um c em (a, b) onde f'(c) = [f(b) - f(a)] / (b - a).",
                "Passo 2: Calcular a taxa de variação média no intervalo [0, 4]:",
                "  f(4) = 4² = 16, f(0) = 0² = 0.",
                "  Taxa média = (16 - 0) / (4 - 0) = 16 / 4 = 4.",
                "Passo 3: Calcular a derivada de f(x): f'(x) = 2x.",
                "Passo 4: Igualar f'(c) à taxa média: 2c = 4 => c = 2.",
                "Passo 5: Verificar que c = 2 pertence ao intervalo aberto (0, 4).",
                "Resultado Final: c = 2."
            ),
            pedagogicalAlert = "Geometricamente, o TVM garante que existe pelo menos um ponto onde a reta tangente é perfeitamente paralela à reta secante que une as extremidades.",
            keyMethod = "Teorema do Valor Médio: f'(c) = [f(b) - f(a)] / (b - a)"
        ),

        UniversalExercise(
            id = "calc_var_08",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Taxas Relacionadas",
            difficulty = "Difícil",
            source = "Cálculo I / Aplicações da Derivada",
            problemStatement = """Um balão esférico está sendo inflado de modo que seu volume aumenta a uma taxa constante de 100 cm³/s. A que taxa o raio da esfera está aumentando no instante em que o raio é r = 5 cm?
(Dica: Volume da esfera \( V = \frac{4}{3} \pi r^3 \))""",
            options = listOf(
                "1 / (π) cm/s ≈ 0,318 cm/s",
                "1 / (2π) cm/s",
                "2 / (π) cm/s",
                "4π cm/s",
                "25 / (π) cm/s"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A fórmula do volume da esfera é V = (4/3) π r³.",
                "Passo 2: Derivar ambos os lados em relação ao tempo t usando a Regra da Cadeia:",
                "  dV/dt = 4π r² · (dr/dt).",
                "Passo 3: Substituir os valores fornecidos no problema (dV/dt = 100 cm³/s e r = 5 cm):",
                "  100 = 4π (5)² · (dr/dt)",
                "  100 = 4π (25) · (dr/dt)",
                "  100 = 100π · (dr/dt).",
                "Passo 4: Isolar dr/dt:",
                "  dr/dt = 100 / (100π) = 1/π cm/s.",
                "Resultado Final: 1/π cm/s (aproximadamente 0,318 cm/s)."
            ),
            pedagogicalAlert = "Em taxas relacionadas, a derivada em relação ao tempo 't' exige multiplicar pela taxa interna dr/dt pela Regra da Cadeia.",
            keyMethod = "Taxas Relacionadas: dV/dt = (dV/dr) · (dr/dt)"
        ),

        UniversalExercise(
            id = "calc_var_09",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização e Teste da 2ª Derivada",
            difficulty = "Mediano",
            source = "Cálculo I / Extremos Locais",
            problemStatement = """Considere a função \( f(x) = x^3 - 3x^2 + 2 \). Determine os pontos críticos e classifique o ponto em \( x = 2 \):""",
            options = listOf(
                "x = 2 é um ponto de Mínimo Local relativo",
                "x = 2 é um ponto de Máximo Local relativo",
                "x = 2 é um ponto de inflexão",
                "x = 2 é uma assíntota vertical",
                "x = 2 não é ponto crítico"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Encontrar a primeira derivada f'(x): f'(x) = 3x² - 6x.",
                "Passo 2: Determinar os pontos críticos igualando a derivada a zero: 3x(x - 2) = 0 => x = 0 ou x = 2.",
                "Passo 3: Encontrar a segunda derivada f''(x): f''(x) = 6x - 6.",
                "Passo 4: Avaliar a concavidade em x = 2:",
                "  f''(2) = 6(2) - 6 = 12 - 6 = +6 > 0.",
                "Passo 5: Como f''(2) > 0, a curva possui concavidade voltada para cima (formato de bacia), o que caracteriza um MÍNIMO local.",
                "Resultado Final: x = 2 é ponto de mínimo local (com valor f(2) = 2³ - 3(4) + 2 = -2)."
            ),
            pedagogicalAlert = "Teste da 2ª Derivada: f'(c) = 0 e f''(c) > 0 indica MÍNIMO; se f''(c) < 0 indica MÁXIMO; se f''(c) = 0 o teste é inconclusivo.",
            keyMethod = "Classificação de extremos: f'(c) = 0 e sinal de f''(c)"
        ),

        UniversalExercise(
            id = "calc_var_10",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Sólidos de Revolução (Método dos Discos)",
            difficulty = "Difícil",
            source = "Cálculo I / Volumes de Rotação",
            problemStatement = """Calcule o volume do sólido gerado pela rotação da região sob a curva \( y = \sqrt{x} \), para \( 0 \le x \le 4 \), em torno do eixo x:
\[ V = \pi \int_{0}^{4} [f(x)]^2 \, dx \]""",
            options = listOf(
                "8π unidades de volume",
                "16π unidades de volume",
                "4π unidades de volume",
                "8 unidades de volume",
                "32π / 3 unidades de volume"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Pelo Método dos Discos Circulares, cada fatia perpendicular ao eixo x é um disco de raio R(x) = f(x) = √x.",
                "Passo 2: A área de cada disco é A(x) = π [R(x)]² = π (√x)² = π · x.",
                "Passo 3: Montar a integral de volume de x = 0 até x = 4:",
                "  V = π ∫_0^4 x dx.",
                "Passo 4: Calcular a primitiva: ∫ x dx = x² / 2.",
                "Passo 5: Aplicar os limites:",
                "  V = π · [ (4)² / 2 - (0)² / 2 ] = π · [ 16 / 2 ] = 8π.",
                "Resultado Final: 8π unidades de volume (aproximadamente 25,13 u.v.)."
            ),
            pedagogicalAlert = "No método dos discos em torno do eixo x, integramos π [f(x)]² dx. Se fosse em torno do eixo y, integraríamos em relação a dy.",
            keyMethod = "Volume de revolução pelo método dos discos: V = π ∫ [f(x)]² dx"
        ),

        // Matemática Básica Adicional (ENEM)
        UniversalExercise(
            id = "mb_var_04",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Progressão Aritmética (PA) & Soma de Gauss",
            difficulty = "Mediano",
            source = "ENEM / Sequências Numéricas",
            problemStatement = "Uma pessoa decide economizar dinheiro durante 30 dias. No primeiro dia ela guarda R$ 5,00 e, a cada dia seguinte, guarda R$ 3,00 a mais do que no dia anterior. Quanto ela terá guardado no total ao final dos 30 dias?",
            options = listOf(
                "R$ 1.455,00",
                "R$ 1.350,00",
                "R$ 92,00",
                "R$ 2.760,00",
                "R$ 1.620,00"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A sequência de depósitos forma uma P.A. com primeiro termo a₁ = 5, razão r = 3 e número de termos n = 30.",
                "Passo 2: Calcular o valor guardado no 30º dia (termo geral):",
                "  a_n = a₁ + (n - 1) · r",
                "  a₃₀ = 5 + (30 - 1) · 3 = 5 + 29 · 3 = 5 + 87 = R$ 92,00.",
                "Passo 3: Calcular a soma acumulada de todos os 30 dias pela fórmula da soma dos termos da P.A.:",
                "  S_n = [ (a₁ + a_n) · n ] / 2",
                "  S₃₀ = [ (5 + 92) · 30 ] / 2 = (97 · 30) / 2 = 97 · 15 = R$ 1.455,00.",
                "Resultado Final: R$ 1.455,00."
            ),
            pedagogicalAlert = "Cuidado para não confundir o valor do último termo (R$ 92) com a soma total de todos os dias economizados (R$ 1.455).",
            keyMethod = "P.A.: a_n = a₁ + (n-1)r e S_n = [(a₁ + a_n) · n] / 2"
        ),

        UniversalExercise(
            id = "mb_var_05",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Progressão Geométrica (PG) Infinita Convergente",
            difficulty = "Mediano",
            source = "ENEM / Séries Geométricas",
            problemStatement = """Uma bola de borracha é solta de uma altura inicial de 16 metros. A cada quique no solo, ela rebate atingindo exatamente metade (1/2) da altura atingida no salto anterior. Qual é a distância total percorrida pela bola (somando todas as subidas e descidas) até parar completamente?""",
            options = listOf(
                "48 metros",
                "32 metros",
                "16 metros",
                "64 metros",
                "24 metros"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A descida inicial percorre 16 metros.",
                "Passo 2: Cada quique subsequente envolve uma subida e uma descida de mesma altura: 2 · (altura atingida).",
                "  1º quique: sobe 8m e desce 8m = 16m.",
                "  2º quique: sobe 4m e desce 4m = 8m.",
                "  3º quique: sobe 2m e desce 2m = 4m.",
                "Passo 3: A sequência de quiques (a partir do 1º) é uma P.G. infinita: 16, 8, 4, 2, ... com a₁ = 16 e razão q = 1/2.",
                "Passo 4: Como |q| < 1, a soma da P.G. infinita é S = a₁ / (1 - q) = 16 / (1 - 1/2) = 16 / (1/2) = 32 metros.",
                "Passo 5: Somar a queda inicial (16 metros) ao total dos quiques: 16 + 32 = 48 metros.",
                "Resultado Final: 48 metros."
            ),
            pedagogicalAlert = "Atenção: a primeira descida ocorre apenas uma vez (para baixo); todos os quiques posteriores ocorrem em dobro (sobe e desce).",
            keyMethod = "Soma da P.G. infinita: S = a₁ / (1 - q) para |q| < 1"
        ),

        UniversalExercise(
            id = "mb_var_06",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Logaritmos e Escala Richter",
            difficulty = "Difícil",
            source = "ENEM / Logaritmos & Ciências",
            problemStatement = """A magnitude M de um terremoto na Escala Richter é dada por:
\[ M = \frac{2}{3} \log_{10} \left( \frac{E}{E_0} \right) \]
Onde E é a energia liberada e E₀ é uma energia de referência. Se um terremoto A possui magnitude M_A = 7 e um terremoto B possui magnitude M_B = 5, quantas vezes a energia liberada pelo terremoto A é maior que a do terremoto B?""",
            options = listOf(
                "1.000 vezes maior (10³)",
                "100 vezes maior (10²)",
                "2 vezes maior",
                "10 vezes maior",
                "10.000 vezes maior"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Da fórmula dada: (3/2) M = log₁₀(E / E₀) => E = E₀ · 10^{(3/2) M}.",
                "Passo 2: Para o terremoto A (M = 7): E_A = E₀ · 10^{(3/2) · 7} = E₀ · 10^{10,5}.",
                "Passo 3: Para o terremoto B (M = 5): E_B = E₀ · 10^{(3/2) · 5} = E₀ · 10^{7,5}.",
                "Passo 4: Dividir a energia E_A por E_B para encontrar a razão:",
                "  E_A / E_B = 10^{10,5} / 10^{7,5} = 10^{10,5 - 7,5} = 10^3 = 1.000.",
                "Resultado Final: 1.000 vezes maior."
            ),
            pedagogicalAlert = "Em escalas logarítmicas como a de Richter, cada unidade inteira a mais não significa uma adição simples, mas uma multiplicação exponencial de energia.",
            keyMethod = "Propriedades operatórias de logaritmos e potenciação: log_b(x) = y <=> b^y = x"
        ),

        UniversalExercise(
            id = "mb_var_07",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Análise Combinatória: Permutação com Repetição",
            difficulty = "Fácil",
            source = "ENEM / Contagem e Anagramas",
            problemStatement = "Quantos anagramas distintos podem ser formados com as letras da palavra 'KNOWVERSE'?",
            options = listOf(
                "181.440 anagramas",
                "362.880 anagramas",
                "40.320 anagramas",
                "90.720 anagramas",
                "725.760 anagramas"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Contar o total de letras na palavra 'KNOWVERSE': total de 9 letras.",
                "Passo 2: Identificar as letras repetidas:",
                "  K: 1, N: 1, O: 1, W: 1, V: 1, E: 2, R: 1, S: 1.",
                "  Apenas a letra 'E' repete 2 vezes.",
                "Passo 3: Aplicar a fórmula de permutação com repetição: P_n^{k} = n! / (k₁! · k₂! ...):",
                "  P_9^{2} = 9! / 2!.",
                "Passo 4: Calcular os fatoriais:",
                "  9! = 9 × 8 × 7 × 6 × 5 × 4 × 3 × 2 × 1 = 362.880.",
                "  P_9^{2} = 362.880 / 2 = 181.440 anagramas.",
                "Resultado Final: 181.440 anagramas."
            ),
            pedagogicalAlert = "Letras repetidas não geram novos arranjos se trocarem de lugar entre si, por isso dividimos pelo fatorial do número de repetições.",
            keyMethod = "Permutação com repetição: P_n^k = n! / k!"
        ),

        UniversalExercise(
            id = "mb_var_08",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Mediana com Distribuição de Frequência",
            difficulty = "Mediano",
            source = "ENEM / Estatística Descritiva",
            problemStatement = """Uma turma de 25 alunos realizou um simulado. As notas obtidas foram registradas na tabela de frequência abaixo:
- Nota 5: 4 alunos
- Nota 6: 6 alunos
- Nota 7: 8 alunos
- Nota 8: 5 alunos
- Nota 9: 2 alunos
Qual é a MEDIANA das notas dessa turma?""",
            options = listOf(
                "7,0",
                "6,5",
                "6,0",
                "7,5",
                "8,0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Total de alunos (n = 25). Como n é ímpar, a mediana é exatamente o elemento central na posição (n + 1) / 2 = (25 + 1) / 2 = 13ª posição.",
                "Passo 2: Construir a frequência acumulada:",
                "  Nota 5: 4 alunos (posições 1ª a 4ª)",
                "  Nota 6: 4 + 6 = 10 alunos (posições 5ª a 10ª)",
                "  Nota 7: 10 + 8 = 18 alunos (posições 11ª a 18ª)",
                "  Nota 8: 18 + 5 = 23 alunos (posições 19ª a 23ª)",
                "  Nota 9: 23 + 2 = 25 alunos (posições 24ª e 25ª).",
                "Passo 3: A 13ª posição cai exatamente dentro da faixa de alunos com Nota 7 (que vai da 11ª até a 18ª posição).",
                "Resultado Final: A mediana é 7,0."
            ),
            pedagogicalAlert = "A mediana divide o conjunto ordenado em duas metades com 50% dos dados abaixo e 50% acima. Não confunda com a média aritmética simples.",
            keyMethod = "Cálculo da mediana por frequência acumulada em conjuntos ordenados"
        )
    )
}

