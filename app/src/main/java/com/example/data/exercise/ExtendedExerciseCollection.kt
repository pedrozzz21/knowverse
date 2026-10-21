package com.example.data.exercise

/**
 * Coleção estendida de novos exercícios verificados para Python e Cálculo I
 * com gabarito comentado passo a passo, alertas pedagógicos e elementos visuais (tabelas e código).
 */
object ExtendedExerciseCollection {

    val additionalExercises: List<UniversalExercise> = listOf(
        // ========================================================
        // PYTHON: IF E ELSE & CONDICIONAIS (FÁCIL / MÉDIO / DIFÍCIL)
        // ========================================================
        UniversalExercise(
            id = "py_ife_01",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Fácil",
            source = "Python Brasil / Condicionais Básicas",
            problemStatement = "Analise o código Python a seguir que utiliza a sintaxe do operador condicional ternário. Qual será o valor impresso no console?",
            codeSnippet = """numero = 0
resultado = "Positivo" if numero > 0 else ("Zero" if numero == 0 else "Negativo")
print(resultado)""",
            terminalOutput = "Zero",
            options = listOf(
                "Positivo",
                "Zero",
                "Negativo",
                "None",
                "SyntaxError: invalid syntax"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A variável 'numero' recebe o valor inteiro 0.",
                "Passo 2: Avalia-se a primeira condição do operador ternário: 'numero > 0' (0 > 0 é Falso).",
                "Passo 3: Como é falso, passa-se para a cláusula 'else', que contém outro ternário aninhado: 'Zero' if numero == 0 else 'Negativo'.",
                "Passo 4: Avalia-se 'numero == 0' (0 == 0 é Verdadeiro).",
                "Passo 5: A expressão retorna a string 'Zero'.",
                "Resultado Final: 'Zero'."
            ),
            pedagogicalAlert = "O operador ternário em Python possui a ordem: <valor_se_verdadeiro> if <condição> else <valor_se_falso>.",
            keyMethod = "Ternário aninhado em Python: A if cond1 else (B if cond2 else C)"
        ),

        UniversalExercise(
            id = "py_ife_02",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Fácil",
            source = "Python Core / Comparações Encadeadas",
            problemStatement = "Em Python, operadores de comparação podem ser encadeados de forma matemática. Dado x = 15, qual será o resultado da avaliação da expressão condicional?",
            codeSnippet = """x = 15
if 10 < x <= 20 != 25:
    print("Válido")
else:
    print("Inválido")""",
            terminalOutput = "Válido",
            options = listOf(
                "Válido",
                "Inválido",
                "TypeError: cannot chain different comparison operators",
                "SyntaxError",
                "None"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Em Python, a expressão encadeada '10 < x <= 20 != 25' é internamente equivalente a: (10 < x) and (x <= 20) and (20 != 25).",
                "Passo 2: Avaliando cada parte com x = 15:",
                "  • 10 < 15 -> Verdadeiro",
                "  • 15 <= 20 -> Verdadeiro",
                "  • 20 != 25 -> Verdadeiro",
                "Passo 3: Como todas as três comparações são verdadeiras e unidas por 'and', a expressão inteira é Verdadeira.",
                "Passo 4: O bloco 'if' executa e imprime 'Válido'.",
                "Resultado Final: 'Válido'."
            ),
            pedagogicalAlert = "Python permite comparações encadeadas naturais como 'a < b < c', que diferem de linguagens como C ou Java.",
            keyMethod = "Encadeamento de comparações: a < b < c é equivalente a (a < b) and (b < c)."
        ),

        UniversalExercise(
            id = "py_ife_03",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Mediano",
            source = "USP / Avaliação de Curto-Circuito (Short-Circuit)",
            problemStatement = "O interpretador Python realiza avaliação de curto-circuito (short-circuit) em operadores booleanos 'and' e 'or'. O que o programa a seguir exibirá?",
            codeSnippet = """def log_mensagem(txt):
    print(f"Chamou: {txt}")
    return True

condicao = False
if condicao and log_mensagem("A"):
    print("Ramo 1")
elif not condicao or log_mensagem("B"):
    print("Ramo 2")""",
            terminalOutput = "Ramo 2",
            options = listOf(
                "Chamou: A\nRamo 1",
                "Chamou: B\nRamo 2",
                "Ramo 2",
                "Chamou: A\nChamou: B\nRamo 2",
                "Ramo 1"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Avaliação do primeiro 'if': 'condicao and log_mensagem(\"A\")'.",
                "Passo 2: Como 'condicao' é False e o operador é 'and', o Python não precisa avaliar o lado direito (curto-circuito!). Portanto, log_mensagem(\"A\") NUNCA é chamada.",
                "Passo 3: O fluxo segue para o 'elif': 'not condicao or log_mensagem(\"B\")'.",
                "Passo 4: 'not condicao' equivale a 'not False', que é True.",
                "Passo 5: Como o lado esquerdo de um operador 'or' é True, a expressão inteira já é garantida como True! O Python interrompe a avaliação (curto-circuito) e log_mensagem(\"B\") também NÃO é chamada.",
                "Passo 6: O corpo do elif é executado, imprimindo apenas 'Ramo 2'.",
                "Resultado Final: Apenas 'Ramo 2' é impresso."
            ),
            pedagogicalAlert = "Atenção: Nem 'Chamou: A' nem 'Chamou: B' foram impressos porque ambos os lados direitos foram ignorados pelo curto-circuito!",
            keyMethod = "Short-circuit: False and X -> False imediatamente; True or Y -> True imediatamente."
        ),

        UniversalExercise(
            id = "py_ife_04",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Mediano",
            source = "Python Institute / Identidade 'is' vs Igualdade '=='",
            problemStatement = "Analise o trecho de código abaixo que compara duas listas com o mesmo conteúdo utilizando os operadores '==' e 'is'. Qual será a saída?",
            codeSnippet = """lista_a = [1, 2, 3]
lista_b = [1, 2, 3]

if lista_a == lista_b:
    if lista_a is lista_b:
        print("Iguais e mesmo objeto")
    else:
        print("Mesmo valor, objetos distintos")
else:
    print("Valores diferentes")""",
            terminalOutput = "Mesmo valor, objetos distintos",
            options = listOf(
                "Iguais e mesmo objeto",
                "Mesmo valor, objetos distintos",
                "Valores diferentes",
                "TypeError",
                "None"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: O operador '==' compara a IGUALDADE de valor/conteúdo dos objetos.",
                "Passo 2: lista_a == lista_b compara elemento a elemento: [1, 2, 3] == [1, 2, 3], que é True.",
                "Passo 3: O operador 'is' compara a IDENTIDADE em memória (endereço retornado por id()).",
                "Passo 4: Como lista_a e lista_b foram instanciadas como literais de listas separadas, elas ocupam posições distintas na memória (id(lista_a) != id(lista_b)).",
                "Passo 5: Logo, 'lista_a is lista_b' resulta em False, caindo no 'else' interno: 'Mesmo valor, objetos distintos'.",
                "Resultado Final: 'Mesmo valor, objetos distintos'."
            ),
            pedagogicalAlert = "Nunca use 'is' para comparar valores de strings ou listas. Use 'is' apenas para checagens de identidade como 'if x is None:'.",
            keyMethod = "'==' compara valores (conteúdo); 'is' compara referências de memória (identidade de objeto)."
        ),

        UniversalExercise(
            id = "py_ife_05",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Difícil",
            source = "Python 3.10+ PEP 634 / Structural Pattern Matching",
            problemStatement = "A partir do Python 3.10, a estrutura 'match...case' introduziu casamento de padrões com guardas condicionais 'if'. Qual será o resultado exibido pelo código a seguir?",
            codeSnippet = """comando = ["mover", 10, 20]

match comando:
    case ["mover", x, y] if x == y:
        print(f"Diagonal: {x}")
    case ["mover", x, y] if x < y:
        print(f"Vertical predominante: dx={x}, dy={y}")
    case ["mover", x, y]:
        print("Outro movimento")
    case _:
        print("Comando desconhecido")""",
            terminalOutput = "Vertical predominante: dx=10, dy=20",
            options = listOf(
                "Diagonal: 10",
                "Vertical predominante: dx=10, dy=20",
                "Outro movimento",
                "Comando desconhecido",
                "SyntaxError"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A lista comando possui 3 elementos: [\"mover\", 10, 20].",
                "Passo 2: Primeiro case: casa o formato [\"mover\", x, y] com x=10 e y=20, porém possui guarda condicional 'if x == y' (10 == 20 é Falso). O case falha.",
                "Passo 3: Segundo case: casa o formato [\"mover\", x, y] com guarda 'if x < y' (10 < 20 é Verdadeiro!).",
                "Passo 4: Como o padrão casou e a guarda é verdadeira, o bloco executa:",
                "  print(f\"Vertical predominante: dx=10, dy=20\")",
                "Resultado Final: 'Vertical predominante: dx=10, dy=20'."
            ),
            pedagogicalAlert = "Em match-case, a guarda 'if' deve ser verdadeira para que o case seja acionado. Se falsa, o Python tenta os próximos cases sequencialmente.",
            keyMethod = "match...case com guardas condicionais 'if' filtra padrões estruturais com base em lógica booleana."
        ),

        UniversalExercise(
            id = "py_ife_06",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Difícil",
            source = "Maratona SBC / Precedência de Operadores Lógicos",
            problemStatement = "Em Python, qual é a ordem correta de precedência entre os operadores lógicos 'not', 'and' e 'or'? Analise a saída da expressão booleana:",
            codeSnippet = """a = True
b = False
c = False

resultado = not a or b and not c
print(resultado)""",
            terminalOutput = "False",
            options = listOf(
                "True",
                "False",
                "None",
                "TypeError",
                "Indeterminado"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A ordem de precedência dos operadores booleanos em Python é estritamente:",
                "  1º: 'not' (maior precedência)",
                "  2º: 'and'",
                "  3º: 'or' (menor precedência)",
                "Passo 2: Aplicando 'not' primeiro:",
                "  • not a -> not True = False",
                "  • not c -> not False = True",
                "Passo 3: A expressão vira: False or (b and True).",
                "Passo 4: Aplicando 'and':",
                "  • b and True -> False and True = False.",
                "Passo 5: A expressão vira: False or False = False.",
                "Resultado Final: False."
            ),
            pedagogicalAlert = "Não confunda a ordem! 'not' executa antes de 'and', e 'and' executa antes de 'or'. Use sempre parênteses para tornar seu código explícito.",
            keyMethod = "Precedência booleana: not > and > or."
        ),

        // ========================================================
        // PYTHON: LAÇOS & ESTRUTURAS DE DADOS
        // ========================================================
        UniversalExercise(
            id = "py_loop_01",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Mediano",
            source = "USP / List Comprehension com Filtro",
            problemStatement = "Qual será a lista gerada pela seguinte compreensão de listas (List Comprehension) em Python?",
            codeSnippet = """numeros = [1, 2, 3, 4, 5, 6]
quadrados_pares = [n**2 for n in numeros if n % 2 == 0]
print(quadrados_pares)""",
            terminalOutput = "[4, 16, 36]",
            options = listOf(
                "[1, 4, 9, 16, 25, 36]",
                "[4, 16, 36]",
                "[2, 4, 6]",
                "[4, 8, 12]",
                "[1, 9, 25]"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A compreensão itera sobre numeros = [1, 2, 3, 4, 5, 6].",
                "Passo 2: O filtro condicional 'if n % 2 == 0' seleciona apenas os pares: 2, 4 e 6.",
                "Passo 3: A expressão à esquerda 'n**2' eleva cada número selecionado ao quadrado:",
                "  • 2² = 4",
                "  • 4² = 16",
                "  • 6² = 36",
                "Passo 4: Monta-se a lista resultante: [4, 16, 36].",
                "Resultado Final: [4, 16, 36]."
            ),
            pedagogicalAlert = "O 'if' no final da list comprehension atua como um filtro (filter).",
            keyMethod = "[expr for item in lista if cond] aplica expr apenas aos itens que satisfazem cond."
        ),

        // ========================================================
        // CÁLCULO I: LIMITES, DERIVADAS E INTEGRAIS (COM GABARITO COMENTADO)
        // ========================================================
        UniversalExercise(
            id = "calc_lim_01",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Fácil",
            source = "Cálculo James Stewart / Limite Fundamental Trigonométrico",
            problemStatement = "Calcule o limite fundamental trigonométrico:\n\\[ \\lim_{x \\to 0} \\frac{\\sin(4x)}{2x} \\]",
            visualTable = VisualTable(
                title = "Tabela de Aproximação Numérica em torno de x = 0",
                headers = listOf("x (radianos)", "sin(4x)", "2x", "sin(4x) / (2x)"),
                rows = listOf(
                    listOf("0,1", "0,3894", "0,2", "1,947"),
                    listOf("0,01", "0,03998", "0,02", "1,999"),
                    listOf("0,001", "0,0039999", "0,002", "1,99999"),
                    listOf("→ 0", "→ 0", "→ 0", "→ 2,00000")
                )
            ),
            options = listOf(
                "0",
                "1",
                "2",
                "4",
                "Não existe"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Ao substituir diretamente x = 0, obtemos sin(0)/0 = 0/0, uma indeterminação.",
                "Passo 2: Relembramos o Limite Trigonométrico Fundamental: lim_{u -> 0} (sin(u)/u) = 1.",
                "Passo 3: Para ter o mesmo argumento 4x no denominador, multiplicamos o numerador e o denominador por 2:",
                "\\[ \\frac{\\sin(4x)}{2x} = 2 \\times \\frac{\\sin(4x)}{4x} \\]",
                "Passo 4: Fazendo a mudança de variável u = 4x. Quando x -> 0, u -> 0:",
                "\\[ \\lim_{x \\to 0} 2 \\times \\frac{\\sin(4x)}{4x} = 2 \\times 1 = 2 \\]",
                "Resultado Final: O limite é igual a 2."
            ),
            pedagogicalAlert = "Não esqueça: lim_{x->0} (sin(ax) / bx) = a / b. Aqui, 4 / 2 = 2.",
            keyMethod = "\\lim_{x \\to 0} \\frac{\\sin(ax)}{bx} = \\frac{a}{b} = \\frac{4}{2} = 2"
        ),

        UniversalExercise(
            id = "calc_lim_02",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Mediano",
            source = "UFMG / Racionalização e Indeterminação 0/0",
            problemStatement = "Determine o valor do limite a seguir eliminando a indeterminação:\n\\[ \\lim_{x \\to 4} \\frac{\\sqrt{x} - 2}{x - 4} \\]",
            options = listOf(
                "1/4 (ou 0,25)",
                "1/2 (ou 0,50)",
                "1",
                "0",
                "Infinito"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Substituição direta: (sqrt(4) - 2) / (4 - 4) = (2 - 2) / 0 = 0/0 (indeterminação).",
                "Passo 2: Multiplicar numerador e denominador pelo conjugado da raiz: (sqrt(x) + 2):",
                "\\[ \\frac{(\\sqrt{x} - 2)(\\sqrt{x} + 2)}{(x - 4)(\\sqrt{x} + 2)} \\]",
                "Passo 3: No numerador, produto notável (a - b)(a + b) = a² - b²:",
                "\\[ (\\sqrt{x})^2 - 2^2 = x - 4 \\]",
                "Passo 4: A fração simplifica cancelando (x - 4):",
                "\\[ \\frac{x - 4}{(x - 4)(\\sqrt{x} + 2)} = \\frac{1}{\\sqrt{x} + 2} \\quad (\\text{para } x \\neq 4) \\]",
                "Passo 5: Calculando o limite com a expressão simplificada:",
                "\\[ \\lim_{x \\to 4} \\frac{1}{\\sqrt{4} + 2} = \\frac{1}{2 + 2} = \\frac{1}{4} \\]",
                "Resultado Final: 1/4."
            ),
            pedagogicalAlert = "Multiplicar pelo conjugado (sqrt(x) + 2) é a técnica padrão para desfazer raízes em indeterminações do tipo 0/0.",
            keyMethod = "Racionalização: (sqrt(x)-2)(sqrt(x)+2)/(x-4) = (x-4)/(x-4)(sqrt(x)+2) = 1/(sqrt(x)+2) => 1/4"
        ),

        UniversalExercise(
            id = "calc_lim_03",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Mediano",
            source = "USP POLI / Limites no Infinito com Raízes",
            problemStatement = "Calcule o limite no infinito:\n\\[ \\lim_{x \\to +\\infty} \\frac{\\sqrt{9x^2 + 5}}{2x - 1} \\]",
            options = listOf(
                "3/2",
                "9/2",
                "3",
                "+Infinito",
                "0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Para x -> +infinito, x > 0, logo sqrt(x²) = |x| = x.",
                "Passo 2: Colocar x² em evidência dentro da raiz no numerador:",
                "\\[ \\sqrt{9x^2 + 5} = \\sqrt{x^2 \\left(9 + \\frac{5}{x^2}\\right)} = x \\sqrt{9 + \\frac{5}{x^2}} \\]",
                "Passo 3: Colocar x em evidência no denominador: 2x - 1 = x (2 - 1/x).",
                "Passo 4: Dividir numerador e denominador por x:",
                "\\[ \\frac{x \\sqrt{9 + \\frac{5}{x^2}}}{x \\left(2 - \\frac{1}{x}\\right)} = \\frac{\\sqrt{9 + \\frac{5}{x^2}}}{2 - \\frac{1}{x}} \\]",
                "Passo 5: Quando x -> +infinito, 5/x² -> 0 e 1/x -> 0:",
                "\\[ \\frac{\\sqrt{9 + 0}}{2 - 0} = \\frac{\\sqrt{9}}{2} = \\frac{3}{2} \\]",
                "Resultado Final: 3/2."
            ),
            pedagogicalAlert = "Atenção: Se x tendesse a -infinito, sqrt(x²) seria -x, e o resultado seria -3/2!",
            keyMethod = "\\lim_{x \\to +\\infty} \\frac{\\sqrt{9x^2}}{2x} = \\frac{3x}{2x} = \\frac{3}{2}"
        ),

        UniversalExercise(
            id = "calc_der_01",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Fácil",
            source = "Cálculo Guidorizzi / Regra do Produto",
            problemStatement = "Dada a função f(x) = x³ · eˣ, encontre sua derivada f'(x):",
            options = listOf(
                "f'(x) = 3x² · eˣ",
                "f'(x) = x² eˣ (3 + x)",
                "f'(x) = 3x² · x eˣ",
                "f'(x) = (3x² + 1) eˣ",
                "f'(x) = x³ · eˣ"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A função é o produto de duas funções: u(x) = x³ e v(x) = eˣ.",
                "Passo 2: Derivadas individuais: u'(x) = 3x² e v'(x) = eˣ.",
                "Passo 3: Regra do Produto: (u · v)' = u' · v + u · v'.",
                "Passo 4: Substituindo: f'(x) = (3x²) · eˣ + (x³) · eˣ.",
                "Passo 5: Fatorando o termo comum x² eˣ:",
                "\\[ f'(x) = x^2 e^x (3 + x) \\]",
                "Resultado Final: x² eˣ (3 + x)."
            ),
            pedagogicalAlert = "Não esqueça: a derivada do produto NÃO é o produto das derivadas! Use sempre u'v + uv'.",
            keyMethod = "Regra do Produto: (x³ · eˣ)' = 3x² eˣ + x³ eˣ = x² eˣ (3 + x)"
        ),

        UniversalExercise(
            id = "calc_der_02",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Mediano",
            source = "UNICAMP / Equação da Reta Tangente",
            problemStatement = "Determine a equação da reta tangente à curva y = f(x) = x² - 3x + 2 no ponto onde x = 3.",
            options = listOf(
                "y = 3x - 7",
                "y = 3x - 9",
                "y = 2x - 4",
                "y = 3x + 2",
                "y = 6x - 16"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Determinar o ponto de tangência (x₀, y₀). Para x = 3:",
                "\\[ y_0 = f(3) = 3^2 - 3(3) + 2 = 9 - 9 + 2 = 2 \\implies (3, 2) \\]",
                "Passo 2: A inclinação m da reta tangente é dada pela derivada f'(x) no ponto x = 3:",
                "\\[ f'(x) = \\frac{d}{dx}(x^2 - 3x + 2) = 2x - 3 \\]",
                "Passo 3: Avaliando em x = 3: m = f'(3) = 2(3) - 3 = 6 - 3 = 3.",
                "Passo 4: Usando a equação fundamental da reta: y - y₀ = m(x - x₀):",
                "\\[ y - 2 = 3(x - 3) \\implies y - 2 = 3x - 9 \\implies y = 3x - 7 \\]",
                "Resultado Final: y = 3x - 7."
            ),
            pedagogicalAlert = "O coeficiente angular da reta tangente a uma função em x₀ é SEMPRE a primeira derivada f'(x₀)!",
            keyMethod = "m = f'(3) = 3; Ponto = (3, 2) => y - 2 = 3(x - 3) => y = 3x - 7"
        ),

        UniversalExercise(
            id = "calc_der_03",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização",
            difficulty = "Difícil",
            source = "ENEM / IME / Otimização de Área com Perímetro Fixo",
            problemStatement = "Um fazendeiro possui 40 metros de cerca e deseja cercar uma horta retangular com a maior área possível. Qual é a área máxima que ele poderá obter?",
            visualTable = VisualTable(
                title = "Análise de Dimensões e Área para Perímetro Fixo 2(x + y) = 40",
                headers = listOf("Largura x (m)", "Comprimento y = 20 - x (m)", "Área A = x · y (m²)"),
                rows = listOf(
                    listOf("2", "18", "36"),
                    listOf("5", "15", "75"),
                    listOf("8", "12", "96"),
                    listOf("10 (Vértice)", "10", "100 (Máximo)"),
                    listOf("12", "8", "96")
                )
            ),
            options = listOf(
                "80 m²",
                "96 m²",
                "100 m²",
                "120 m²",
                "160 m²"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Sejam x e y as dimensões do retângulo. O perímetro total é 2x + 2y = 40 => x + y = 20 => y = 20 - x.",
                "Passo 2: A função de área a ser maximizada é A(x) = x · y = x(20 - x) = 20x - x².",
                "Passo 3: Para encontrar os pontos críticos, calculamos a primeira derivada e igualamos a zero:",
                "\\[ A'(x) = 20 - 2x = 0 \\implies 2x = 20 \\implies x = 10\\text{ metros} \\]",
                "Passo 4: Teste da segunda derivada: A''(x) = -2 < 0 (concavidade para baixo, confirmando ponto de máximo absoluto).",
                "Passo 5: Calculando a outra dimensão e a área máxima:",
                "\\[ y = 20 - 10 = 10\\text{ metros} \\quad \\implies \\quad A_{\\max} = 10 \\times 10 = 100\\text{ m}^2 \\]",
                "Resultado Final: 100 m² (o retângulo de área máxima com perímetro fixo é sempre um quadrado)."
            ),
            pedagogicalAlert = "Teorema clássico: Dentre todos os retângulos de mesmo perímetro, o quadrado é o que encerra a maior área.",
            keyMethod = "A(x) = 20x - x² => A'(x) = 20 - 2x = 0 => x = 10 m => Área Máxima = 100 m²"
        ),

        UniversalExercise(
            id = "calc_int_01",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Fácil",
            source = "Cálculo James Stewart / Integral Indefinida e Regra da Potência",
            problemStatement = "Encontre a família de primitivas da função polinomial:\n\\[ \\int (6x^2 - 4x + 5)\\,dx \\]",
            options = listOf(
                "2x³ - 2x² + 5x + C",
                "12x - 4 + C",
                "3x³ - 4x² + 5x + C",
                "6x³ - 4x² + 5x + C",
                "2x³ - 4x² + 5 + C"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplicar a linearidade da integral: integral de soma é a soma das integrais.",
                "Passo 2: Regra da potência: integral(x^n dx) = x^(n+1) / (n+1):",
                "  • integral(6x² dx) = 6 · (x³/3) = 2x³",
                "  • integral(-4x dx) = -4 · (x²/2) = -2x²",
                "  • integral(5 dx) = 5x",
                "Passo 3: Somar a constante de integração C:",
                "\\[ 2x^3 - 2x^2 + 5x + C \\]",
                "Resultado Final: 2x³ - 2x² + 5x + C."
            ),
            pedagogicalAlert = "Não esqueça de adicionar a constante arbitrária + C em todas as integrais indefinidas!",
            keyMethod = "\\int (6x^2 - 4x + 5)\\,dx = 6(x^3/3) - 4(x^2/2) + 5x + C = 2x^3 - 2x^2 + 5x + C"
        ),

        UniversalExercise(
            id = "calc_int_02",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Mediano",
            source = "USP POLI / Integração por Mudança de Variável (Substituição)",
            problemStatement = "Calcule a integral indefinida por substituição de variável:\n\\[ \\int 2x \\cos(x^2)\\,dx \\]",
            options = listOf(
                "sin(x²) + C",
                "-sin(x²) + C",
                "2x sin(x²) + C",
                "cos(x²) + C",
                "x² sin(x) + C"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Identificar a função interna e sua derivada: u = x².",
                "Passo 2: Diferencial: du = 2x dx.",
                "Passo 3: Observe que o termo '2x dx' já está presente na integral!",
                "Passo 4: Substituir tudo em termos de u:",
                "\\[ \\int \\cos(u)\\,du = \\sin(u) + C \\]",
                "Passo 5: Retornar à variável original x (u = x²):",
                "\\[ \\sin(x^2) + C \\]",
                "Resultado Final: sin(x²) + C."
            ),
            pedagogicalAlert = "Sempre confira derivando a resposta: d/dx [sin(x²) + C] = cos(x²) · (2x) = 2x cos(x²). Confere perfeitamente!",
            keyMethod = "Substituição: u = x² => du = 2x dx => \\int \\cos(u) du = \\sin(u) + C = \\sin(x^2) + C"
        ),

        UniversalExercise(
            id = "calc_int_03",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Mediano",
            source = "Cálculo Stewart / Teorema Fundamental do Cálculo",
            problemStatement = "Calcule a integral definida pelo Teorema Fundamental do Cálculo:\n\\[ \\int_0^2 (3x^2 - 2x + 1)\\,dx \\]",
            options = listOf(
                "4",
                "6",
                "8",
                "10",
                "12"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Encontrar uma primitiva F(x) para f(x) = 3x² - 2x + 1:",
                "\\[ F(x) = 3\\left(\\frac{x^3}{3}\\right) - 2\\left(\\frac{x^2}{2}\\right) + x = x^3 - x^2 + x \\]",
                "Passo 2: Pelo TFC: integral de a até b é F(b) - F(a):",
                "Passo 3: Avaliar no limite superior b = 2:",
                "\\[ F(2) = 2^3 - 2^2 + 2 = 8 - 4 + 2 = 6 \\]",
                "Passo 4: Avaliar no limite inferior a = 0:",
                "\\[ F(0) = 0^3 - 0^2 + 0 = 0 \\]",
                "Passo 5: Subtrair: F(2) - F(0) = 6 - 0 = 6.",
                "Resultado Final: 6."
            ),
            pedagogicalAlert = "Em integrais definidas, a constante C cancela-se: [F(b) + C] - [F(a) + C] = F(b) - F(a).",
            keyMethod = "TFC: [x³ - x² + x]_0^2 = (8 - 4 + 2) - 0 = 6"
        ),

        UniversalExercise(
            id = "calc_int_04",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Difícil",
            source = "ITA / Integração por Partes",
            problemStatement = "Resolva a integral utilizando o método de integração por partes:\n\\[ \\int x e^x\\,dx \\]",
            options = listOf(
                "(x - 1) eˣ + C",
                "(x + 1) eˣ + C",
                "x² eˣ + C",
                "eˣ (x² - x) + C",
                "x eˣ - x + C"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Fórmula da integração por partes: integral(u dv) = u · v - integral(v du).",
                "Passo 2: Pela regra mnemônica LIATE (Logarítmica, Inversa, Algébrica, Trigonométrica, Exponencial):",
                "  • Escolhemos a algébrica para u: u = x  => du = dx.",
                "  • O restante é dv: dv = eˣ dx => v = eˣ.",
                "Passo 3: Aplicando a fórmula:",
                "\\[ \\int x e^x\\,dx = x e^x - \\int e^x\\,dx \\]",
                "Passo 4: A integral de eˣ é o próprio eˣ:",
                "\\[ x e^x - e^x + C = (x - 1)e^x + C \\]",
                "Resultado Final: (x - 1) eˣ + C."
            ),
            pedagogicalAlert = "Verificação: d/dx [(x - 1)eˣ] = 1 · eˣ + (x - 1)eˣ = eˣ + x eˣ - eˣ = x eˣ. Confere!",
            keyMethod = "Partes: u = x, dv = eˣ dx => u v - \\int v du = x eˣ - eˣ + C = (x - 1)eˣ + C"
        ),

        // ========================================================
        // NOVOS EXERCÍCIOS DE MATEMÁTICA BÁSICA (ÁLGEBRA, ESTATÍSTICA, GEOMETRIA E ESCALAS)
        // ========================================================
        UniversalExercise(
            id = "mb_ext_01",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Equações e Sistemas Lineares",
            difficulty = "Mediano",
            source = "ENEM / Modelagem de Sistemas Lineares",
            problemStatement = "Em um estacionamento há carros (4 rodas) e motos (2 rodas), totalizando 45 veículos e 140 rodas no chão. Quantos carros e quantas motos estão nesse estacionamento?",
            visualTable = VisualTable(
                title = "Sistema Linear 2x2: Veículos e Rodas",
                headers = listOf("Equação", "Expressão Algébrica", "Significado"),
                rows = listOf(
                    listOf("Eq. 1", "C + M = 45", "Total de veículos"),
                    listOf("Eq. 2", "4C + 2M = 140", "Total de rodas"),
                    listOf("Eq. 2 ÷ 2", "2C + M = 70", "Equação simplificada")
                )
            ),
            options = listOf(
                "20 carros e 25 motos",
                "25 carros e 20 motos",
                "30 carros e 15 motos",
                "15 carros e 30 motos",
                "22 carros e 23 motos"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Definir as incógnitas: C = quantidade de carros e M = quantidade de motos.",
                "Passo 2: Montar o sistema: C + M = 45 (Eq. 1) e 4C + 2M = 140 (Eq. 2).",
                "Passo 3: Dividir a Eq. 2 por 2 para simplificar: 2C + M = 70.",
                "Passo 4: Subtrair a Eq. 1 da Eq. 2 simplificada: (2C + M) - (C + M) = 70 - 45 => C = 25 carros.",
                "Passo 5: Substituir C = 25 na Eq. 1: 25 + M = 45 => M = 20 motos.",
                "Resultado Final: 25 carros e 20 motos."
            ),
            pedagogicalAlert = "Dividir toda a equação por um fator comum antes de isolar variáveis reduz drasticamente erros de conta!",
            keyMethod = "Sistema 2x2: C + M = 45 e 2C + M = 70 => C = 25, M = 20"
        ),

        UniversalExercise(
            id = "mb_ext_02",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Média, Mediana e Moda",
            difficulty = "Fácil",
            source = "ENEM / Medidas de Tendência Central",
            problemStatement = "O consumo diário de água (em litros) de uma residência durante 7 dias foi: 150, 120, 180, 120, 200, 140, 160. Qual é a Mediana e a Moda desse consumo, respectivamente?",
            visualTable = VisualTable(
                title = "Ordenação em Rol Crescente (Obrigatória para Mediana)",
                headers = listOf("Posição", "1º", "2º", "3º", "4º (Central)", "5º", "6º", "7º"),
                rows = listOf(
                    listOf("Original", "150", "120", "180", "120", "200", "140", "160"),
                    listOf("Rol Ordenado", "120", "120", "140", "150 (Md)", "160", "180", "200")
                )
            ),
            options = listOf(
                "Mediana = 120 L e Moda = 150 L",
                "Mediana = 150 L e Moda = 120 L",
                "Mediana = 140 L e Moda = 120 L",
                "Mediana = 160 L e Moda = 180 L",
                "Mediana = 152,8 L e Moda = 120 L"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Colocar os 7 valores em ordem crescente (rol): [120, 120, 140, 150, 160, 180, 200].",
                "Passo 2: Como n = 7 é ímpar, a Mediana é o termo na posição (7+1)/2 = 4º termo.",
                "Passo 3: O 4º termo do rol ordenado é 150 L.",
                "Passo 4: A Moda é o valor que mais se repete: 120 L aparece duas vezes.",
                "Resultado Final: Mediana = 150 L e Moda = 120 L."
            ),
            pedagogicalAlert = "Nunca pegue o termo do meio sem ordenar a lista primeiro! Sem ordenar, você pegaria 120 por engano.",
            keyMethod = "Rol: [120, 120, 140, 150, 160, 180, 200] => 4º termo (Md) = 150; mais frequente (Mo) = 120"
        ),

        UniversalExercise(
            id = "mb_ext_03",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Média, Mediana e Moda",
            difficulty = "Mediano",
            source = "ENEM / Média Aritmética Ponderada",
            problemStatement = "Em uma universidade, a nota final é a média ponderada de três avaliações: Prova 1 (peso 2), Prova 2 (peso 3) e Prova Final (peso 5). Se um estudante tirou 6,0 na Prova 1, 7,0 na Prova 2 e 9,0 na Prova Final, qual é sua média final?",
            options = listOf(
                "7,3",
                "7,5",
                "7,8",
                "8,0",
                "8,2"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Multiplicar cada nota pelo seu respectivo peso: 6,0×2 = 12,0; 7,0×3 = 21,0; 9,0×5 = 45,0.",
                "Passo 2: Somar os produtos ponderados: 12,0 + 21,0 + 45,0 = 78,0.",
                "Passo 3: Dividir pela soma dos pesos (2 + 3 + 5 = 10): 78,0 ÷ 10 = 7,8.",
                "Resultado Final: 7,8."
            ),
            pedagogicalAlert = "Na média ponderada, o denominador é SEMPRE a soma dos pesos (10), e nunca a quantidade de provas (3)!",
            keyMethod = "Média Ponderada = (6×2 + 7×3 + 9×5) / (2 + 3 + 5) = 78 / 10 = 7,8"
        ),

        UniversalExercise(
            id = "mb_ext_04",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria: Áreas e Volumes",
            difficulty = "Mediano",
            source = "ENEM / Geometria Espacial e Capacidade",
            problemStatement = "Um reservatório cilíndrico de água possui raio da base R = 2 metros e altura h = 5 metros. Adotando a aproximação π = 3, qual é a capacidade total desse reservatório em litros?",
            options = listOf(
                "30.000 litros",
                "45.000 litros",
                "60.000 litros",
                "75.000 litros",
                "120.000 litros"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Calcular o volume do cilindro em metros cúbicos pela fórmula V = π · R² · h.",
                "Passo 2: Substituindo π = 3, R = 2 e h = 5: V = 3 × (2²) × 5 = 3 × 4 × 5 = 60 m³.",
                "Passo 3: Converter de metros cúbicos para litros usando a relação fundamental 1 m³ = 1.000 L:",
                "  60 m³ × 1.000 = 60.000 litros.",
                "Resultado Final: 60.000 litros."
            ),
            pedagogicalAlert = "Guarde para o ENEM: 1 cm³ = 1 mL, 1 dm³ = 1 L e 1 m³ = 1.000 L!",
            keyMethod = "V = π R² h = 3 × 4 × 5 = 60 m³ = 60.000 L"
        ),

        UniversalExercise(
            id = "mb_ext_05",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Escalas Cartográficas",
            difficulty = "Difícil",
            source = "ENEM / Escala Quadrática de Áreas",
            problemStatement = "A planta baixa de um galpão retangular foi desenhada na escala linear 1 : 200. Se no desenho a área do galpão mede 15 cm², qual é a área real desse galpão em metros quadrados (m²)?",
            options = listOf(
                "30 m²",
                "60 m²",
                "150 m²",
                "300 m²",
                "600 m²"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Quando a escala linear é 1 : k, a razão entre as ÁREAS é o quadrado da escala: (1 : k)² = 1 : k².",
                "Passo 2: Para k = 200, o fator de ampliação de área é k² = 200² = 40.000.",
                "Passo 3: Multiplicar a área do desenho por 40.000: A_real = 15 cm² × 40.000 = 600.000 cm².",
                "Passo 4: Converter de cm² para m² dividindo por 10.000 (pois 1 m = 100 cm => 1 m² = 100² cm² = 10.000 cm²):",
                "  A_real = 600.000 ÷ 10.000 = 60 m².",
                "Resultado Final: 60 m²."
            ),
            pedagogicalAlert = "Nunca multiplique uma área diretamente pela escala linear! Para áreas, eleve o fator da escala ao quadrado.",
            keyMethod = "A_real = A_mapa × k² = 15 cm² × 200² = 600.000 cm² = 60 m²"
        ),

        UniversalExercise(
            id = "py_ext_07",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dicionários & Sets",
            difficulty = "Mediano",
            source = "Python Institute / Dict Comprehension & Inversão",
            problemStatement = "Analise o código abaixo que utiliza compreensão de dicionário (Dict Comprehension) com filtro condicional. Qual será a saída impressa?",
            codeSnippet = """precos = {"maçã": 4, "banana": 2, "uva": 8, "pera": 5}
caros = {k.upper(): v * 10 for k, v in precos.items() if v >= 5}
print(caros)""",
            terminalOutput = "{'UVA': 80, 'PERA': 50}",
            options = listOf(
                "{'MAÇÃ': 40, 'BANANA': 20, 'UVA': 80, 'PERA': 50}",
                "{'UVA': 80, 'PERA': 50}",
                "{'uva': 8, 'pera': 5}",
                "['UVA', 'PERA']",
                "{'UVA': 8, 'PERA': 5}"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: O método precos.items() produz os pares (chave, valor) do dicionário original.",
                "Passo 2: O filtro 'if v >= 5' mantém apenas os itens cujo valor é maior ou igual a 5: ('uva', 8) e ('pera', 5).",
                "Passo 3: A expressão transforma a chave em maiúsculas (k.upper()) e multiplica o valor por 10 (v * 10):",
                "  • 'uva' -> 'UVA': 8 * 10 = 80",
                "  • 'pera' -> 'PERA': 5 * 10 = 50",
                "Resultado Final: {'UVA': 80, 'PERA': 50}."
            ),
            pedagogicalAlert = "Dict comprehensions usam chaves {k: v for ...} com dois-pontos separando chave e valor.",
            keyMethod = "{k.upper(): v*10 for k, v in d.items() if v >= 5} filtra e transforma chaves/valores simultaneamente."
        )
    )
}
