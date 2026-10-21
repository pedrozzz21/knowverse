package com.example.data.exercise

/**
 * Acervo completo de exercícios de Cálculo I com distribuição balanceada:
 * 2 Fáceis, 2 Médias e 2 Difíceis para cada assunto do currículo.
 */
object ComprehensiveCalculusExercises {

    val exercises: List<UniversalExercise> = listOf(
        // ========================================================
        // 1. LIMITES & CONTINUIDADE
        // ========================================================
        UniversalExercise(
            id = "calc_lim_facil_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Fácil",
            source = "Cálculo I / Substituição Direta",
            problemStatement = """Calcule o limite por substituição direta contínua:
\[ \lim_{x \to 3} (2x^2 - 4x + 1) \]""",
            options = listOf(
                "7",
                "19",
                "13",
                "1",
                "0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Funções polinomiais são contínuas em toda a reta real ℝ.",
                "Passo 2: O limite quando x tende a 'a' é igual ao valor numérico f(a).",
                "Passo 3: Substituindo x = 3:",
                "  f(3) = 2(3)² - 4(3) + 1 = 2(9) - 12 + 1 = 18 - 12 + 1 = 7.",
                "Resultado Final: 7."
            ),
            pedagogicalAlert = "Se a função for polinomial ou racional sem zerar o denominador, o cálculo resume-se à substituição direta.",
            keyMethod = "Continuidade de polinômios: lim_{x -> a} P(x) = P(a)"
        ),
        UniversalExercise(
            id = "calc_lim_facil_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Fácil",
            source = "Cálculo I / Indeterminação 0/0 Básica",
            problemStatement = """Calcule o limite simplificando a fração algébrica:
\[ \lim_{x \to 2} \frac{x^2 - 4}{x - 2} \]""",
            options = listOf(
                "4",
                "2",
                "0",
                "Indeterminado (não existe)",
                "8"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Substituição direta dá 0/0 (indeterminação matemática).",
                "Passo 2: Fatorar o numerador como diferença de quadrados: x² - 4 = (x - 2)(x + 2).",
                "Passo 3: Cancelar o fator comum (x - 2) para x ≠ 2:",
                "  lim_{x -> 2} (x + 2).",
                "Passo 4: Substituir x = 2: 2 + 2 = 4.",
                "Resultado Final: 4."
            ),
            pedagogicalAlert = "O valor da função em x = 2 não existe (buraco no gráfico), mas o limite existe e vale 4.",
            keyMethod = "Fatoração de diferença de quadrados: a² - b² = (a - b)(a + b)"
        ),
        UniversalExercise(
            id = "calc_lim_med_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Mediano",
            source = "Cálculo I / Racionalização",
            problemStatement = """Determine o limite racionalizando o numerador:
\[ \lim_{x \to 0} \frac{\sqrt{x + 4} - 2}{x} \]""",
            options = listOf(
                "1/4",
                "1/2",
                "1",
                "0",
                "2"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Substituição em x = 0: (√4 - 2)/0 = (2 - 2)/0 = 0/0.",
                "Passo 2: Multiplicar numerador e denominador pelo conjugado (√(x + 4) + 2):",
                "  [(√(x + 4) - 2)(√(x + 4) + 2)] / [x (√(x + 4) + 2)] = [(x + 4) - 4] / [x (√(x + 4) + 2)].",
                "Passo 3: Simplificar o numerador: x / [x (√(x + 4) + 2)] = 1 / (√(x + 4) + 2).",
                "Passo 4: Substituir x = 0: 1 / (√4 + 2) = 1 / (2 + 2) = 1/4.",
                "Resultado Final: 1/4 (ou 0,25)."
            ),
            pedagogicalAlert = "Multiplicar pelo conjugado elimina a raiz quadrada e cancela a indeterminação no denominador.",
            keyMethod = "Racionalização com o conjugado: (√A - B)(√A + B) = A - B²"
        ),
        UniversalExercise(
            id = "calc_lim_med_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Mediano",
            source = "Cálculo I / Limites Laterais",
            problemStatement = """Considere a função definida por partes:
\[ f(x) = \begin{cases} 2x + 1, & x < 2 \\ x^2 + k, & x \ge 2 \end{cases} \]
Qual deve ser o valor da constante \( k \) para que a função seja contínua em \( x = 2 \)?""",
            options = listOf(
                "k = 1",
                "k = 5",
                "k = -1",
                "k = 3",
                "k = 0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Para ser contínua em x = 2, os limites laterais devem ser iguais ao valor da função f(2):",
                "  lim_{x -> 2⁻} f(x) = lim_{x -> 2⁺} f(x) = f(2).",
                "Passo 2: Limite pela esquerda (x < 2): lim_{x -> 2⁻} (2x + 1) = 2(2) + 1 = 5.",
                "Passo 3: Limite pela direita (x ≥ 2): lim_{x -> 2⁺} (x² + k) = 2² + k = 4 + k.",
                "Passo 4: Igualar os limites: 4 + k = 5 => k = 1.",
                "Resultado Final: k = 1."
            ),
            pedagogicalAlert = "Continuidade exige três condições: f(c) existe, o limite bilateral existe (laterais iguais) e lim f(x) = f(c).",
            keyMethod = "Continuidade pontual igualando limites laterais à esquerda e à direita"
        ),
        UniversalExercise(
            id = "calc_lim_dif_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Difícil",
            source = "Cálculo I / Teorema do Confronto (Sanduíche)",
            problemStatement = """Calcule o limite utilizando o Teorema do Confronto:
\[ \lim_{x \to 0} x^2 \cdot \cos\left(\frac{1}{x}\right) \]""",
            options = listOf(
                "0",
                "1",
                "Não existe devido à oscilação",
                "∞",
                "-1"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A função cos(1/x) oscila indefinidamente entre -1 e 1 para x próximo de zero:",
                "  -1 ≤ cos(1/x) ≤ 1 para todo x ≠ 0.",
                "Passo 2: Como x² ≥ 0 para todo x, multiplicamos toda a desigualdade por x² sem inverter o sinal:",
                "  -x² ≤ x² cos(1/x) ≤ x².",
                "Passo 3: Tomar o limite quando x -> 0 nas extremidades:",
                "  lim_{x -> 0} (-x²) = 0 e lim_{x -> 0} (x²) = 0.",
                "Passo 4: Pelo Teorema do Confronto (Sanduíche), a função imprensada no meio obrigatoriamente converge para 0.",
                "Resultado Final: 0."
            ),
            pedagogicalAlert = "Embora lim cos(1/x) sozinho não exista, a multiplicação pelo fator infinitesimal x² 'esmaga' a oscilação em direção a zero.",
            keyMethod = "Teorema do Sanduíche: se g(x) ≤ f(x) ≤ h(x) e lim g = lim h = L, então lim f = L"
        ),
        UniversalExercise(
            id = "calc_lim_dif_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Difícil",
            source = "Cálculo I / Limite Notável de Euler (1^∞)",
            problemStatement = """Calcule o limite exponencial notável:
\[ \lim_{x \to \infty} \left( 1 + \frac{3}{x} \right)^{2x} \]""",
            options = listOf(
                "e^6",
                "e^3",
                "e^2",
                "1",
                "∞"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A forma é da indeterminação 1^∞.",
                "Passo 2: Lembrar da definição do número de Euler: lim_{u -> ∞} (1 + 1/u)^u = e.",
                "Passo 3: Reescrever a expressão na base e:",
                "  (1 + 3/x)^{2x} = [ (1 + 3/x)^{x/3} ]^{6}.",
                "Passo 4: Fazendo u = x/3, quando x -> ∞ temos u -> ∞, logo a base interna converge para 'e'.",
                "Passo 5: Portanto, o limite resulta em e^6.",
                "Resultado Final: e^6."
            ),
            pedagogicalAlert = "Fórmula direta para limites do tipo (1 + k/x)^{m x}: o resultado é sempre e^{k · m}.",
            keyMethod = "Limite fundamental exponencial: lim_{x -> ∞} (1 + a/x)^{b x} = e^{ab}"
        ),

        // ========================================================
        // 2. ASSÍNTOTAS & INFINITO
        // ========================================================
        UniversalExercise(
            id = "calc_assint_facil_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Fácil",
            source = "Cálculo I / Assíntota Vertical",
            problemStatement = """Qual é a equação da assíntota vertical da função:
\[ f(x) = \frac{5}{x - 3} \]""",
            options = listOf(
                "x = 3",
                "y = 3",
                "x = 0",
                "y = 5",
                "x = -3"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Assíntotas verticais ocorrem onde o denominador se anula sem anular o numerador.",
                "Passo 2: Denominador x - 3 = 0 => x = 3.",
                "Passo 3: Numerador é constante 5 ≠ 0.",
                "Passo 4: Logo, x = 3 é a reta assíntota vertical."
            ),
            pedagogicalAlert = "Assíntotas verticais são retas verticais, portanto sua equação é da forma 'x = constante'.",
            keyMethod = "Localização de assíntotas verticais: zeros isolados do denominador"
        ),
        UniversalExercise(
            id = "calc_assint_facil_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Fácil",
            source = "Cálculo I / Limites no Infinito Básicos",
            problemStatement = """Calcule o limite no infinito:
\[ \lim_{x \to \infty} \frac{4x^2 + 1}{2x^2 - 5} \]""",
            options = listOf(
                "2",
                "4",
                "1/2",
                "0",
                "∞"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Quando numerador e denominador possuem o mesmo grau máximo (grau 2), o limite no infinito é a razão entre os coeficientes líderes.",
                "Passo 2: Coeficiente líder do numerador: 4.",
                "Passo 3: Coeficiente líder do denominador: 2.",
                "Passo 4: Razão: 4 / 2 = 2.",
                "Resultado Final: 2."
            ),
            pedagogicalAlert = "Essa mesma reta y = 2 representa a assíntota horizontal da função para x -> ±∞.",
            keyMethod = "Razão de coeficientes líderes para polinômios de mesmo grau no infinito"
        ),
        UniversalExercise(
            id = "calc_assint_med_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Mediano",
            source = "Cálculo I / Graus Diferentes no Infinito",
            problemStatement = """Calcule o comportamento assintótico:
\[ \lim_{x \to \infty} \frac{3x + 7}{5x^3 - 2} \]""",
            options = listOf(
                "0 (Assíntota horizontal em y = 0)",
                "3/5",
                "∞",
                "7/(-2)",
                "Não existe"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Grau do numerador é 1 e grau do denominador é 3.",
                "Passo 2: O denominador cresce com potência x³ muito mais rápido que o numerador x.",
                "Passo 3: Dividir todos os termos por x³:",
                "  lim_{x -> ∞} [ (3/x² + 7/x³) / (5 - 2/x³) ] = [0 + 0] / [5 - 0] = 0 / 5 = 0.",
                "Resultado Final: 0."
            ),
            pedagogicalAlert = "Sempre que o grau do denominador for estritamente maior que o grau do numerador, o limite no infinito é zero (eixo x é assíntota horizontal).",
            keyMethod = "Dominância de potências polinomiais no infinito"
        ),
        UniversalExercise(
            id = "calc_assint_med_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Mediano",
            source = "Cálculo I / Assíntota Oblíqua (Inclinada)",
            problemStatement = """A função racional \( f(x) = \frac{2x^2 + 3x + 1}{x - 1} \) possui qual equação de assíntota oblíqua?""",
            options = listOf(
                "y = 2x + 5",
                "y = 2x + 3",
                "y = 2x - 1",
                "y = x + 2",
                "Não possui assíntota oblíqua"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Uma assíntota oblíqua (y = ax + b) ocorre quando o grau do numerador é exatamente 1 unidade maior que o do denominador (aqui 2 vs 1).",
                "Passo 2: Realizar a divisão euclidiana de polinômios (2x² + 3x + 1) por (x - 1):",
                "  2x² ÷ x = 2x",
                "  2x(x - 1) = 2x² - 2x. Subtraindo: (3x + 1) - (-2x) = 5x + 1.",
                "  5x ÷ x = 5",
                "  5(x - 1) = 5x - 5. Subtraindo: 1 - (-5) = 6 (resto).",
                "Passo 3: Quociente obtido: q(x) = 2x + 5.",
                "Passo 4: f(x) = (2x + 5) + 6/(x - 1). Quando x -> ±∞, o termo 6/(x - 1) -> 0.",
                "Resultado Final: y = 2x + 5."
            ),
            pedagogicalAlert = "A assíntota oblíqua é dada exatamente pelo quociente inteiro da divisão de polinômios.",
            keyMethod = "Divisão polinomial para determinação de assíntotas oblíquas: f(x) = ax + b + R(x)/D(x)"
        ),
        UniversalExercise(
            id = "calc_assint_dif_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Difícil",
            source = "Cálculo I / Limites no Infinito com Raízes",
            problemStatement = """Calcule o limite no infinito negativo com raiz quadrada:
\[ \lim_{x \to -\infty} \frac{\sqrt{4x^2 + 3}}{2x - 1} \]""",
            options = listOf(
                "-1",
                "1",
                "2",
                "-2",
                "0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: CUIDADO CRÍTICO: Para x < 0 (infinito negativo), √x² = |x| = -x.",
                "Passo 2: Fatorar x² de dentro da raiz: √(4x² + 3) = √(x² (4 + 3/x²)) = |x| √(4 + 3/x²) = -x √(4 + 3/x²).",
                "Passo 3: Fatorar x no denominador: 2x - 1 = x (2 - 1/x).",
                "Passo 4: Montar o limite cancelando x:",
                "  lim_{x -> -∞} [ -x √(4 + 3/x²) ] / [ x (2 - 1/x) ] = [ -√(4 + 0) ] / [ 2 - 0 ] = -2 / 2 = -1.",
                "Resultado Final: -1."
            ),
            pedagogicalAlert = "A maior armadilha do Cálculo I: √x² = |x|, que resulta em -x quando x tende a -∞!",
            keyMethod = "Cuidado de sinal em radicais no infinito negativo: √x² = -x para x < 0"
        ),
        UniversalExercise(
            id = "calc_assint_dif_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Difícil",
            source = "Cálculo I / Indeterminação ∞ - ∞",
            problemStatement = """Calcule o limite de subtração de radicais no infinito:
\[ \lim_{x \to \infty} \left( \sqrt{x^2 + 5x} - x \right) \]""",
            options = listOf(
                "5/2",
                "5",
                "0",
                "∞",
                "1/2"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Substituição direta dá ∞ - ∞ (indeterminação).",
                "Passo 2: Multiplicar e dividir pela expressão conjugada (√(x² + 5x) + x):",
                "  [(√(x² + 5x) - x)(√(x² + 5x) + x)] / [√(x² + 5x) + x] = [(x² + 5x) - x²] / [√(x² + 5x) + x].",
                "Passo 3: O numerador simplifica para 5x.",
                "Passo 4: Fatorar x no denominador (para x > 0, √(x² + 5x) = x √(1 + 5/x)):",
                "  5x / [ x (√(1 + 5/x) + 1) ] = 5 / [ √(1 + 5/x) + 1 ].",
                "Passo 5: Quando x -> ∞, 5/x -> 0: 5 / (√1 + 1) = 5 / 2 = 2,5.",
                "Resultado Final: 5/2."
            ),
            pedagogicalAlert = "Indeterminações do tipo ∞ - ∞ com radicais sempre requerem a técnica do conjugado para transformar a subtração em quociente.",
            keyMethod = "Resolução de ∞ - ∞ com conjugado algébrico"
        ),

        // ========================================================
        // 3. DERIVADAS & RETAS TANGENTES
        // ========================================================
        UniversalExercise(
            id = "calc_der_facil_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Fácil",
            source = "Cálculo I / Regra do Tombo (Potência)",
            problemStatement = """Calcule a derivada de \( f(x) = 4x^3 - 5x^2 + 7x - 9 \):""",
            options = listOf(
                "f'(x) = 12x² - 10x + 7",
                "f'(x) = 12x² - 10x",
                "f'(x) = 7x² - 5x + 7",
                "f'(x) = 4x² - 5x + 7",
                "f'(x) = 12x³ - 10x² + 7x"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplicar a Regra do Tombo d/dx [x^n] = n x^{n-1} em cada monômio:",
                "  d/dx [4x³] = 4(3x²) = 12x².",
                "  d/dx [-5x²] = -5(2x) = -10x.",
                "  d/dx [7x] = 7(1) = 7.",
                "  d/dx [-9] = 0 (derivada de constante é zero).",
                "Passo 2: Somar os termos: 12x² - 10x + 7."
            ),
            pedagogicalAlert = "A derivada de uma constante isolada é sempre zero.",
            keyMethod = "Regra do Tombo linear: d/dx [c x^n] = c n x^{n-1}"
        ),
        UniversalExercise(
            id = "calc_der_facil_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Fácil",
            source = "Cálculo I / Inclinação da Tangente",
            problemStatement = """Qual é o coeficiente angular (inclinação) da reta tangente à parábola \( f(x) = x^2 \) no ponto onde \( x = 3 \)?""",
            options = listOf(
                "m = 6",
                "m = 9",
                "m = 3",
                "m = 2",
                "m = 1/6"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A inclinação da reta tangente é dada pelo valor da derivada f'(x) no ponto considerado.",
                "Passo 2: Derivada de f(x) = x²: f'(x) = 2x.",
                "Passo 3: Substituir x = 3: f'(3) = 2(3) = 6.",
                "Resultado Final: m = 6."
            ),
            pedagogicalAlert = "O coeficiente angular da reta tangente NÃO é o valor de f(3)=9, mas sim o valor da derivada f'(3)=6.",
            keyMethod = "Interpretação geométrica da derivada: m = f'(x_0)"
        ),
        UniversalExercise(
            id = "calc_der_med_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Mediano",
            source = "Cálculo I / Equação da Reta Tangente",
            problemStatement = """Encontre a equação da reta tangente à curva \( f(x) = x^3 - 2x \) no ponto onde \( x = 1 \):""",
            options = listOf(
                "y = x - 2",
                "y = x - 1",
                "y = 3x - 4",
                "y = 2x - 3",
                "y = -x"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Encontrar a coordenada y do ponto de tangência: f(1) = 1³ - 2(1) = 1 - 2 = -1. O ponto é P(1, -1).",
                "Passo 2: Encontrar a derivada de f(x): f'(x) = 3x² - 2.",
                "Passo 3: Calcular a inclinação no ponto x = 1: m = f'(1) = 3(1)² - 2 = 3 - 2 = 1.",
                "Passo 4: Aplicar a equação fundamental da reta (ponto-declive): y - y₀ = m(x - x₀):",
                "  y - (-1) = 1 · (x - 1)",
                "  y + 1 = x - 1 => y = x - 2.",
                "Resultado Final: y = x - 2."
            ),
            pedagogicalAlert = "Sempre calcule tanto o ponto f(x₀) quanto a inclinação f'(x₀) antes de montar a reta.",
            keyMethod = "Equação da reta tangente: y - f(x₀) = f'(x₀)(x - x₀)"
        ),
        UniversalExercise(
            id = "calc_der_med_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Mediano",
            source = "Cálculo I / Regra do Quociente",
            problemStatement = """Calcule a derivada da função racional \( f(x) = \frac{x}{x + 1} \):""",
            options = listOf(
                "1 / (x + 1)²",
                "(2x + 1) / (x + 1)²",
                "-1 / (x + 1)²",
                "1",
                "x / (x + 1)²"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Pela Regra do Quociente: [u/v]' = (u' v - u v') / v².",
                "Passo 2: u = x => u' = 1; v = x + 1 => v' = 1.",
                "Passo 3: Aplicar na fórmula:",
                "  f'(x) = [ 1 · (x + 1) - x · 1 ] / (x + 1)²",
                "  f'(x) = [ x + 1 - x ] / (x + 1)² = 1 / (x + 1)².",
                "Resultado Final: 1 / (x + 1)²."
            ),
            pedagogicalAlert = "Na Regra do Quociente há sinal de MENOS no numerador: u'v - uv'. A inversão de ordem inverte o sinal do resultado.",
            keyMethod = "Regra do Quociente: (u/v)' = (u'v - uv') / v²"
        ),
        UniversalExercise(
            id = "calc_der_dif_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Difícil",
            source = "Cálculo I / Reta Normal",
            problemStatement = """Qual é a equação da reta NORMAL (perpendicular à reta tangente) à curva \( f(x) = \frac{1}{x} \) no ponto onde \( x = 2 \)?""",
            options = listOf(
                "y = 4x - 15/2",
                "y = -1/4 x + 1",
                "y = 4x + 1/2",
                "y = -4x + 17/2",
                "y = 2x - 3"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Ponto de tangência em x = 2: f(2) = 1/2. Ponto P(2, 1/2).",
                "Passo 2: Derivada de f(x) = x⁻¹: f'(x) = -x⁻² = -1/x².",
                "Passo 3: Inclinação da reta tangente em x = 2: m_t = -1/(2)² = -1/4.",
                "Passo 4: Duas retas perpendiculares satisfazem m_n · m_t = -1, logo:",
                "  m_n = -1 / m_t = -1 / (-1/4) = 4.",
                "Passo 5: Equação da reta normal com ponto P(2, 1/2) e m_n = 4:",
                "  y - 1/2 = 4(x - 2)",
                "  y - 1/2 = 4x - 8 => y = 4x - 8 + 1/2 = 4x - 15/2.",
                "Resultado Final: y = 4x - 15/2."
            ),
            pedagogicalAlert = "A reta normal é perpendicular à tangente; portanto seu coeficiente angular é o simétrico do inverso: m_n = -1 / f'(x₀).",
            keyMethod = "Equação da reta normal: m_n = -1 / f'(x₀)"
        ),
        UniversalExercise(
            id = "calc_der_dif_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Difícil",
            source = "Cálculo I / Derivação Logarítmica (x^x)",
            problemStatement = """Calcule a derivada da função \( f(x) = x^x \) para \( x > 0 \):""",
            options = listOf(
                "x^x (ln x + 1)",
                "x · x^{x-1}",
                "x^x · ln x",
                "x^{x-1}",
                "e^x (x + 1)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A base e o expoente dependem de x, logo NÃO podemos usar a regra da potência nem da exponencial pura.",
                "Passo 2: Aplicar logaritmo natural em ambos os lados: ln y = ln(x^x) = x · ln x.",
                "Passo 3: Derivar implicitamente em relação a x:",
                "  d/dx [ln y] = d/dx [x · ln x]",
                "  (1/y) · y' = 1 · ln x + x · (1/x) = ln x + 1.",
                "Passo 4: Multiplicar por y para isolar y':",
                "  y' = y · (ln x + 1) = x^x (ln x + 1).",
                "Resultado Final: x^x (ln x + 1)."
            ),
            pedagogicalAlert = "Funções do tipo f(x)^{g(x)} exigem derivação logarítmica ou conversão para e^{g(x) ln f(x)}.",
            keyMethod = "Derivação logarítmica: y = f(x)^{g(x)} => ln y = g(x) ln f(x)"
        ),

        // ========================================================
        // 4. REGRA DA CADEIA
        // ========================================================
        UniversalExercise(
            id = "calc_cad_facil_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Regra da Cadeia",
            difficulty = "Fácil",
            source = "Cálculo I / Potência Composta",
            problemStatement = """Derive a função composta \( f(x) = (3x + 2)^5 \):""",
            options = listOf(
                "15 (3x + 2)⁴",
                "5 (3x + 2)⁴",
                "(3x + 2)⁴",
                "15x (3x + 2)⁴",
                "3 (3x + 2)⁴"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Identificar a função externa g(u) = u⁵ e interna u = 3x + 2.",
                "Passo 2: Derivada da externa: g'(u) = 5u⁴.",
                "Passo 3: Derivada da interna: u' = 3.",
                "Passo 4: Pela Regra da Cadeia: f'(x) = g'(u) · u' = 5(3x + 2)⁴ · 3 = 15(3x + 2)⁴."
            ),
            pedagogicalAlert = "Não esqueça de multiplicar pela derivada da função interna (aqui, o 3).",
            keyMethod = "Regra da Cadeia para potências: d/dx [u^n] = n u^{n-1} · u'"
        ),
        UniversalExercise(
            id = "calc_cad_facil_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Regra da Cadeia",
            difficulty = "Fácil",
            source = "Cálculo I / Exponencial Composta",
            problemStatement = """Calcule a derivada de \( f(x) = e^{5x} \):""",
            options = listOf(
                "5 e^{5x}",
                "e^{5x}",
                "5x e^{5x-1}",
                "5 e^x",
                "1/5 e^{5x}"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A função externa é e^u com u = 5x.",
                "Passo 2: Derivada da exponencial: d/du [e^u] = e^u.",
                "Passo 3: Derivada da interna: d/dx [5x] = 5.",
                "Passo 4: Regra da Cadeia: d/dx [e^{5x}] = e^{5x} · 5 = 5 e^{5x}."
            ),
            pedagogicalAlert = "A derivada de e^{kx} é sempre k e^{kx}.",
            keyMethod = "Regra da Cadeia exponencial: d/dx [e^u] = e^u · u'"
        ),
        UniversalExercise(
            id = "calc_cad_med_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Regra da Cadeia",
            difficulty = "Mediano",
            source = "Cálculo I / Trigonométrica com Argumento",
            problemStatement = """Calcule a derivada de f(x) = sen(x³):""",
            options = listOf(
                "3x² · cos(x³)",
                "cos(x³)",
                "3x² · sen(x²)",
                "-cos(x³)",
                "3 · cos(x²)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Função externa: sen(u); função interna: u = x³.",
                "Passo 2: Derivada da externa: d/du [sen(u)] = cos(u).",
                "Passo 3: Derivada da interna: du/dx = 3x².",
                "Passo 4: Multiplicar pela Regra da Cadeia:",
                "  f'(x) = cos(x³) · 3x² = 3x² cos(x³)."
            ),
            pedagogicalAlert = "Cuidado com a notação: sen(x³) significa sen(x·x·x), e NÃO (sen x)³.",
            keyMethod = "Regra da Cadeia trigonométrica: d/dx [sen u] = cos u · u'"
        ),
        UniversalExercise(
            id = "calc_cad_med_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Regra da Cadeia",
            difficulty = "Mediano",
            source = "Cálculo I / Logaritmo Composto",
            problemStatement = """Determine a derivada de \( f(x) = \ln(x^2 + 4) \):""",
            options = listOf(
                "2x / (x² + 4)",
                "1 / (x² + 4)",
                "2 / (x² + 4)",
                "x / (x² + 4)",
                "2x ln(x² + 4)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Função externa ln(u) com u = x² + 4.",
                "Passo 2: Derivada de ln(u): 1/u.",
                "Passo 3: Derivada de u: u' = 2x.",
                "Passo 4: f'(x) = (1 / u) · u' = 2x / (x² + 4)."
            ),
            pedagogicalAlert = "A derivada do logaritmo natural de uma função u(x) é sempre u'/u.",
            keyMethod = "Regra da Cadeia logarítmica: d/dx [ln u] = u' / u"
        ),
        UniversalExercise(
            id = "calc_cad_dif_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Regra da Cadeia",
            difficulty = "Difícil",
            source = "Cálculo I / Tríplice Composição",
            problemStatement = """Calcule a derivada da função com três camadas de composição:
f(x) = √(sen(e^(2x)))""",
            options = listOf(
                "(e^(2x) · cos(e^(2x))) / √(sen(e^(2x)))",
                "cos(e^(2x)) / (2 · √(sen(e^(2x))))",
                "2 · e^(2x) · cos(e^(2x))",
                "e^(2x) / √(sen(e^(2x)))",
                "-cos(e^(2x)) / √(sen(e^(2x)))"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Identificar as 3 camadas:",
                "  Camada 1 (externa): √u = u^{1/2}",
                "  Camada 2 (intermediária): sen(v)",
                "  Camada 3 (interna): v = e^{2x}",
                "Passo 2: Derivada da camada 1: 1 / (2√u).",
                "Passo 3: Derivada da camada 2: cos(v).",
                "Passo 4: Derivada da camada 3: 2 e^{2x}.",
                "Passo 5: Multiplicar as 3 derivadas:",
                "  f'(x) = [ 1 / (2√sen(e^{2x})) ] · [ cos(e^{2x}) ] · [ 2 e^{2x} ].",
                "Passo 6: O fator 2 cancela com o denominador 2:",
                "  f'(x) = [ e^{2x} cos(e^{2x}) ] / √sen(e^{2x})."
            ),
            pedagogicalAlert = "Em composições múltiplas, trabalhe de fora para dentro como descascando uma cebola, multiplicando cada camada sucessiva.",
            keyMethod = "Regra da Cadeia múltipla: (f ∘ g ∘ h)'(x) = f'(g(h(x))) · g'(h(x)) · h'(x)"
        ),
        UniversalExercise(
            id = "calc_cad_dif_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Regra da Cadeia",
            difficulty = "Difícil",
            source = "Cálculo I / Produto e Cadeia Combinados",
            problemStatement = """Calcule a derivada de \( f(x) = x^2 \cdot e^{-x^2} \) e encontre os valores de x onde a derivada se anula (pontos críticos):""",
            options = listOf(
                "x = 0 e x = ±1",
                "x = 0 e x = ±2",
                "Apenas x = 0",
                "x = ±1",
                "Não possui pontos críticos reais"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplicar a Regra do Produto: f'(x) = (x²)' · e^{-x²} + x² · (e^{-x²})'.",
                "Passo 2: Derivada de e^{-x²} pela Regra da Cadeia: e^{-x²} · (-2x).",
                "Passo 3: Juntar os termos:",
                "  f'(x) = 2x e^{-x²} + x² (-2x e^{-x²}) = 2x e^{-x²} (1 - x²).",
                "Passo 4: Igualar a zero para achar os pontos críticos: 2x e^{-x²} (1 - x²) = 0.",
                "Passo 5: Como e^{-x²} nunca é zero, temos:",
                "  2x = 0 => x = 0",
                "  1 - x² = 0 => x² = 1 => x = ±1.",
                "Resultado Final: x = 0, x = 1 e x = -1."
            ),
            pedagogicalAlert = "Fatorar termos comuns como e^{-x²} simplifica enormemente a resolução das raízes da derivada.",
            keyMethod = "Combinação da Regra do Produto com a Regra da Cadeia"
        ),

        // ========================================================
        // 5. TAXAS RELACIONADAS
        // ========================================================
        UniversalExercise(
            id = "calc_tax_facil_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Taxas Relacionadas",
            difficulty = "Fácil",
            source = "Cálculo I / Área de Quadrado em Expansão",
            problemStatement = """O lado x de um quadrado está crescendo a uma taxa de 3 cm/s. A que taxa a área A do quadrado está crescendo no instante em que x = 10 cm?""",
            options = listOf(
                "60 cm²/s",
                "30 cm²/s",
                "100 cm²/s",
                "20 cm²/s",
                "9 cm²/s"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A fórmula da área do quadrado é A = x².",
                "Passo 2: Derivar ambos os lados em relação ao tempo t pela Regra da Cadeia:",
                "  dA/dt = 2x · (dx/dt).",
                "Passo 3: Substituir os valores dados (x = 10 cm e dx/dt = 3 cm/s):",
                "  dA/dt = 2(10) · 3 = 20 · 3 = 60 cm²/s.",
                "Resultado Final: 60 cm²/s."
            ),
            pedagogicalAlert = "A taxa de crescimento da área depende do tamanho instantâneo do quadrado: quanto maior o quadrado, mais rápida a área cresce para uma mesma velocidade de lado.",
            keyMethod = "Derivação implícita temporal: dA/dt = (dA/dx) · (dx/dt)"
        ),
        UniversalExercise(
            id = "calc_tax_facil_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Taxas Relacionadas",
            difficulty = "Fácil",
            source = "Cálculo I / Raio de Círculo",
            problemStatement = """Uma mancha circular de óleo tem raio aumentando a 2 m/s. Qual a taxa de aumento do perímetro (comprimento) da circunferência \( C = 2\pi r \)?""",
            options = listOf(
                "4π m/s",
                "2π m/s",
                "8π m/s",
                "4 m/s",
                "π m/s"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Relação do perímetro com o raio: C = 2π r.",
                "Passo 2: Derivar em relação a t: dC/dt = 2π · (dr/dt).",
                "Passo 3: Substituir dr/dt = 2 m/s:",
                "  dC/dt = 2π (2) = 4π m/s.",
                "Passo 4: Note que a taxa do perímetro é constante e independente do raio atual r."
            ),
            pedagogicalAlert = "Como o perímetro é linear com o raio, sua taxa de expansão é constante ao longo de todo o tempo.",
            keyMethod = "Taxa linear: dC/dt = 2π (dr/dt)"
        ),
        UniversalExercise(
            id = "calc_tax_med_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Taxas Relacionadas",
            difficulty = "Mediano",
            source = "Cálculo I / Escada Deslizando na Parede",
            problemStatement = """Uma escada de 5 metros está apoiada em uma parede vertical. A base da escada desliza para longe da parede a uma velocidade de 2 m/s. Com que velocidade o topo da escada está descendo pela parede no instante em que a base está a 3 metros da parede?""",
            options = listOf(
                "-1,5 m/s (descendo a 1,5 m/s)",
                "-2,0 m/s",
                "-0,75 m/s",
                "-3,0 m/s",
                "-1,0 m/s"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Pelo Teorema de Pitágoras: x² + y² = L² = 5² = 25.",
                "Passo 2: Quando x = 3 m: 3² + y² = 25 => y² = 16 => y = 4 m.",
                "Passo 3: Derivar a equação em relação ao tempo t:",
                "  2x (dx/dt) + 2y (dy/dt) = 0 => x (dx/dt) + y (dy/dt) = 0.",
                "Passo 4: Substituir x = 3, dx/dt = +2 e y = 4:",
                "  3(2) + 4(dy/dt) = 0",
                "  6 + 4(dy/dt) = 0 => dy/dt = -6 / 4 = -1,5 m/s.",
                "Resultado Final: -1,5 m/s (sinal negativo indica decréscimo de altura)."
            ),
            pedagogicalAlert = "O sinal negativo em dy/dt confirma que a distância do topo até o chão está diminuindo.",
            keyMethod = "Taxas relacionadas com Pitágoras: x(dx/dt) + y(dy/dt) = 0"
        ),
        UniversalExercise(
            id = "calc_tax_med_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Taxas Relacionadas",
            difficulty = "Mediano",
            source = "Cálculo I / Barcos Afastando-se em Ângulo Reto",
            problemStatement = """O barco A navega para o Norte a 15 km/h e o barco B para o Leste a 20 km/h, ambos partindo do mesmo ponto às 12h00. A que velocidade a distância D entre eles está aumentando após 1 hora de viagem?""",
            options = listOf(
                "25 km/h",
                "35 km/h",
                "20 km/h",
                "17,5 km/h",
                "50 km/h"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Em t = 1 h, o barco A percorreu y = 15 km e o barco B percorreu x = 20 km.",
                "Passo 2: A distância entre eles é dada por D² = x² + y².",
                "Passo 3: Em t = 1 h: D² = 20² + 15² = 400 + 225 = 625 => D = 25 km.",
                "Passo 4: Derivar temporalmente: 2D (dD/dt) = 2x (dx/dt) + 2y (dy/dt):",
                "  D (dD/dt) = x (dx/dt) + y (dy/dt).",
                "Passo 5: Substituir: 25 (dD/dt) = 20(20) + 15(15) = 400 + 225 = 625.",
                "  dD/dt = 625 / 25 = 25 km/h.",
                "Resultado Final: 25 km/h."
            ),
            pedagogicalAlert = "Como o movimento é retilíneo uniforme com partida conjunta na origem, a velocidade de separação é constante.",
            keyMethod = "Distância euclidiana dinâmica: D (dD/dt) = x (dx/dt) + y (dy/dt)"
        ),
        UniversalExercise(
            id = "calc_tax_dif_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Taxas Relacionadas",
            difficulty = "Difícil",
            source = "Cálculo I / Tanque Cônico Esvaziando",
            problemStatement = """Um tanque cônico invertido possui raio superior R = 2 m e altura total H = 6 m. Água escorre do tanque a 2 m³/min. A que taxa o nível da água h está caindo no instante em que h = 3 m?
(Volume do cone \( V = \frac{1}{3} \pi r^2 h \))""",
            options = listOf(
                "-2/π m/min",
                "-1/π m/min",
                "-4/π m/min",
                "-1/(2π) m/min",
                "-3/π m/min"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Por semelhança de triângulos no corte vertical do cone: r / h = R / H = 2 / 6 = 1/3 => r = h / 3.",
                "Passo 2: Substituir r na fórmula do volume para expressar V exclusivamente em função de h:",
                "  V = (1/3) π (h/3)² h = (1/3) π (h² / 9) h = (π / 27) h³.",
                "Passo 3: Derivar em relação ao tempo t:",
                "  dV/dt = (3π / 27) h² (dh/dt) = (π / 9) h² (dh/dt).",
                "Passo 4: Substituir dV/dt = -2 m³/min (água saindo) e h = 3 m:",
                "  -2 = (π / 9) (3)² (dh/dt)",
                "  -2 = (π / 9) (9) (dh/dt) = π (dh/dt).",
                "Passo 5: Isolar dh/dt:",
                "  dh/dt = -2 / π m/min.",
                "Resultado Final: -2/π m/min."
            ),
            pedagogicalAlert = "Eliminar a variável de raio r antes de derivar é o segredo para simplificar tanques cônicos.",
            keyMethod = "Semelhança de triângulos em cones para redução a uma única variável: r = (R/H) h"
        ),
        UniversalExercise(
            id = "calc_tax_dif_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Taxas Relacionadas",
            difficulty = "Difícil",
            source = "Cálculo I / Sombra de um Pedestre",
            problemStatement = """Um poste de iluminação possui altura de 6 metros. Uma pessoa de 2 metros de altura afasta-se do poste em linha reta a 1,5 m/s. Com que velocidade a ponta da sombra da pessoa está se movendo pelo chão?""",
            options = listOf(
                "2,25 m/s",
                "1,50 m/s",
                "0,75 m/s",
                "3,00 m/s",
                "4,50 m/s"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Seja x a distância da pessoa ao poste e s o comprimento da sombra. A posição da ponta da sombra a partir da base do poste é p = x + s.",
                "Passo 2: Por semelhança de triângulos entre poste (altura 6) e pessoa (altura 2):",
                "  6 / (x + s) = 2 / s.",
                "Passo 3: Multiplicar cruzado:",
                "  6s = 2(x + s) = 2x + 2s",
                "  4s = 2x => s = x / 2.",
                "Passo 4: A posição total da ponta da sombra é p = x + s = x + x/2 = (3/2) x.",
                "Passo 5: Derivar em relação ao tempo t:",
                "  dp/dt = (3/2) · (dx/dt) = (3/2) · 1,5 = 2,25 m/s.",
                "Resultado Final: 2,25 m/s."
            ),
            pedagogicalAlert = "Diferencie a velocidade da ponta da sombra (dp/dt) da velocidade de crescimento do comprimento da sombra (ds/dt = 0,75 m/s).",
            keyMethod = "Semelhança de triângulos para problemas de sombra óptica"
        ),

        // ========================================================
        // 6. OTIMIZAÇÃO
        // ========================================================
        UniversalExercise(
            id = "calc_otim_facil_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização",
            difficulty = "Fácil",
            source = "Cálculo I / Área Máxima de Retângulo",
            problemStatement = """Um fazendeiro possui 100 metros de cerca para cercar um terreno retangular. Qual é a área MÁXIMA que ele pode obter?""",
            options = listOf(
                "625 m² (um quadrado de 25m x 25m)",
                "500 m²",
                "1000 m²",
                "400 m²",
                "250 m²"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Perímetro: 2x + 2y = 100 => x + y = 50 => y = 50 - x.",
                "Passo 2: Função área a ser maximizada: A(x) = x · y = x(50 - x) = 50x - x².",
                "Passo 3: Derivar e igualar a zero: A'(x) = 50 - 2x = 0 => 2x = 50 => x = 25 metros.",
                "Passo 4: Como a segunda derivada A''(x) = -2 < 0, x = 25 é ponto de máximo absoluto.",
                "Passo 5: Calcular a área máxima: A(25) = 25 × (50 - 25) = 25 × 25 = 625 m²."
            ),
            pedagogicalAlert = "Dentre todos os retângulos de mesmo perímetro, o de maior área é sempre o quadrado perfeito.",
            keyMethod = "Maximização de área com restrição de perímetro fixo"
        ),
        UniversalExercise(
            id = "calc_otim_facil_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização",
            difficulty = "Fácil",
            source = "Cálculo I / Custo Mínimo",
            problemStatement = """O custo de produção de x unidades é dado por \( C(x) = 2x^2 - 40x + 500 \). Quantas unidades minimizam o custo?""",
            options = listOf(
                "10 unidades",
                "20 unidades",
                "40 unidades",
                "5 unidades",
                "15 unidades"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Para achar o mínimo, calcula-se a primeira derivada e iguala-se a zero: C'(x) = 0.",
                "Passo 2: C'(x) = 4x - 40.",
                "Passo 3: 4x - 40 = 0 => 4x = 40 => x = 10 unidades.",
                "Passo 4: Verificação pela 2ª derivada: C''(x) = 4 > 0 (confirma ponto de mínimo).",
                "Resultado Final: 10 unidades."
            ),
            pedagogicalAlert = "Em funções quadráticas ax² + bx + c, o vértice ocorre em x = -b / (2a) = 40 / 4 = 10.",
            keyMethod = "Ponto crítico com C'(x) = 0 e teste da 2ª derivada C''(x) > 0"
        ),
        UniversalExercise(
            id = "calc_otim_med_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização",
            difficulty = "Mediano",
            source = "Cálculo I / Caixa sem Tampa de Volume Máximo",
            problemStatement = """De uma folha quadrada de papelão de 12 cm de lado, cortam-se quatro quadrados iguais de lado x nos cantos para dobrar e formar uma caixa aberta sem tampa. Qual valor de x maximiza o volume da caixa?""",
            options = listOf(
                "x = 2 cm",
                "x = 3 cm",
                "x = 1 cm",
                "x = 4 cm",
                "x = 6 cm"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: As dimensões da base da caixa serão (12 - 2x) por (12 - 2x), e a altura será x.",
                "Passo 2: Volume da caixa: V(x) = x (12 - 2x)² = x (144 - 48x + 4x²) = 4x³ - 48x² + 144x.",
                "Passo 3: Derivar em relação a x: V'(x) = 12x² - 96x + 144.",
                "Passo 4: Igualar a zero e dividir por 12: x² - 8x + 12 = 0.",
                "Passo 5: Fatorar: (x - 2)(x - 6) = 0 => x = 2 ou x = 6.",
                "Passo 6: Se x = 6, o lado 12 - 2(6) = 0 (volume zero). Logo, x = 2 cm é o ponto de volume máximo.",
                "Resultado Final: x = 2 cm (Volume = 2 · 8 · 8 = 128 cm³)."
            ),
            pedagogicalAlert = "Sempre verifique o domínio físico do problema: para dobrar o papelão de 12 cm, x deve estar estritamente no intervalo 0 < x < 6.",
            keyMethod = "Otimização geométrica de volume com restrição de domínio físico"
        ),
        UniversalExercise(
            id = "calc_otim_med_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização",
            difficulty = "Mediano",
            source = "Cálculo I / Distância Mínima à Parábola",
            problemStatement = """Qual é o ponto sobre a parábola \( y = x^2 \) mais próximo do ponto \( P(0, 2) \)?""",
            options = listOf(
                "(±√1,5 ; 1,5) = (±√(3/2) ; 3/2)",
                "(0, 0)",
                "(±1, 1)",
                "(±2, 4)",
                "(0, 2)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O quadrado da distância de um ponto (x, x²) ao ponto (0, 2) é:",
                "  D²(x) = (x - 0)² + (x² - 2)² = x² + x⁴ - 4x² + 4 = x⁴ - 3x² + 4.",
                "Passo 2: Minimizar D² equivale a minimizar a distância D.",
                "Passo 3: Derivar: d/dx [D²] = 4x³ - 6x = 0 => 2x (2x² - 3) = 0.",
                "Passo 4: Raízes: x = 0 ou 2x² = 3 => x = ±√(3/2).",
                "Passo 5: Para x = 0: D² = 4 (distância 2).",
                "  Para x = ±√(3/2): D² = (3/2)² - 3(3/2) + 4 = 9/4 - 18/4 + 16/4 = 7/4 = 1,75 < 4.",
                "Passo 6: A coordenada y correspondente é y = x² = 3/2 = 1,5.",
                "Resultado Final: (±√(3/2), 3/2)."
            ),
            pedagogicalAlert = "Minimizar o quadrado da distância D² evita trabalhar com raízes quadradas incômodas na derivação.",
            keyMethod = "Otimização de distância euclidiana via função quadrática de distância D²"
        ),
        UniversalExercise(
            id = "calc_otim_dif_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização",
            difficulty = "Difícil",
            source = "Cálculo I / Lata Cilíndrica de Área Mínima",
            problemStatement = """Uma indústria deseja fabricar latas cilíndricas fechadas com volume fixo V = 1.000 cm³. Para gastar a menor quantidade possível de folha metálica (área superficial mínima), qual deve ser a relação entre a altura h e o raio r da lata?""",
            options = listOf(
                "h = 2r (altura igual ao diâmetro)",
                "h = r",
                "h = 4r",
                "h = π r",
                "h = r / 2"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Volume fixo do cilindro: V = π r² h => h = V / (π r²).",
                "Passo 2: Área superficial total (2 tampas circulares + lateral):",
                "  A(r) = 2π r² + 2π r h.",
                "Passo 3: Substituir h: A(r) = 2π r² + 2π r [V / (π r²)] = 2π r² + 2V / r.",
                "Passo 4: Derivar em relação a r e igualar a zero:",
                "  A'(r) = 4π r - 2V / r² = 0 => 4π r³ = 2V => 2π r³ = V.",
                "Passo 5: Como V = π r² h, temos:",
                "  2π r³ = π r² h => 2r = h.",
                "Resultado Final: h = 2r (a altura ótima de uma lata cilíndrica fechada é exatamente igual ao diâmetro da base)."
            ),
            pedagogicalAlert = "Esse resultado clássico (h = 2r) é universal para qualquer lata cilíndrica fechada de volume fixo, independente do valor numérico de V.",
            keyMethod = "Minimização de superfície com restrição de volume cúbico"
        ),
        UniversalExercise(
            id = "calc_otim_dif_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização",
            difficulty = "Difícil",
            source = "Cálculo I / Lei da Refração de Snell e Tempo Mínimo",
            problemStatement = """Um salva-vidas corre na areia a 5 m/s e nada na água a 2 m/s. Ao resgatar um banhista em apuros, qual princípio do Cálculo Diferencial determina a trajetória que minimiza o tempo total gasto?""",
            options = listOf(
                "O Princípio de Fermat de Tempo Mínimo (que deduz a Lei de Snell da Refração: sen θ₁ / v₁ = sen θ₂ / v₂).",
                "O Teorema de Tales de Proporcionalidade Linear pura.",
                "O Teorema do Confronto para ondas transversais.",
                "A Regra de L'Hôpital para razões de velocidade.",
                "A Regra dos Trapézios de Newton."
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A função tempo total é T(x) = (distância na areia) / v₁ + (distância na água) / v₂.",
                "Passo 2: Derivando T(x) em relação ao ponto de entrada x na água e igualando a zero T'(x) = 0, obtém-se exatamente a condição:",
                "  sen θ₁ / v₁ = sen θ₂ / v₂.",
                "Passo 3: Esse resultado histórico corresponde ao Princípio de Fermat, formulado por Pierre de Fermat utilizando o Cálculo Diferencial primórdio."
            ),
            pedagogicalAlert = "A menor distância em linha reta NÃO é o caminho mais rápido quando as velocidades nos dois meios são diferentes.",
            keyMethod = "Princípio de Fermat e otimização de tempo com velocidades variáveis"
        ),

        // ========================================================
        // 7. INTEGRAIS & TFC
        // ========================================================
        UniversalExercise(
            id = "calc_int_facil_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Fácil",
            source = "Cálculo I / Integral Indefinida Básica",
            problemStatement = """Calcule a integral indefinida:
\[ \int (6x^2 + 4x - 5) \, dx \]""",
            options = listOf(
                "2x³ + 2x² - 5x + C",
                "6x³ + 4x² - 5x + C",
                "12x + 4 + C",
                "2x³ + 4x² - 5x + C",
                "3x³ + 2x² - 5x + C"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplicar a regra da potência para integrais: ∫ x^n dx = (x^{n+1}) / (n + 1).",
                "Passo 2: ∫ 6x² dx = 6(x³ / 3) = 2x³.",
                "Passo 3: ∫ 4x dx = 4(x² / 2) = 2x².",
                "Passo 4: ∫ -5 dx = -5x.",
                "Passo 5: Adicionar a constante arbitrária de integração C.",
                "Resultado Final: 2x³ + 2x² - 5x + C."
            ),
            pedagogicalAlert = "Em integrais indefinidas, nunca se esqueça de adicionar a constante de integração + C.",
            keyMethod = "Regra da potência para integrais imediatas: ∫ x^n dx = x^{n+1}/(n+1) + C"
        ),
        UniversalExercise(
            id = "calc_int_facil_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Fácil",
            source = "Cálculo I / Teorema Fundamental do Cálculo (TFC)",
            problemStatement = """Calcule a integral definida:
\[ \int_{1}^{3} 3x^2 \, dx \]""",
            options = listOf(
                "26",
                "27",
                "28",
                "8",
                "24"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Encontrar a primitiva: F(x) = ∫ 3x² dx = 3(x³/3) = x³.",
                "Passo 2: Pelo Teorema Fundamental do Cálculo (TFC): ∫_a^b f(x) dx = F(b) - F(a).",
                "Passo 3: F(3) - F(1) = (3)³ - (1)³ = 27 - 1 = 26.",
                "Resultado Final: 26."
            ),
            pedagogicalAlert = "Em integrais definidas o resultado é um valor numérico real escalar, dispensando a constante + C.",
            keyMethod = "Aplicação da fórmula de Newton-Leibniz: F(b) - F(a)"
        ),
        UniversalExercise(
            id = "calc_int_med_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Mediano",
            source = "Cálculo I / Área Entre Duas Curvas",
            problemStatement = """Calcule a área da região delimitada pelas curvas \( y = x \) e \( y = x^2 \):""",
            options = listOf(
                "1/6",
                "1/3",
                "1/2",
                "1/4",
                "5/6"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Encontrar os pontos de interseção: x = x² => x² - x = 0 => x(x - 1) = 0 => x = 0 e x = 1.",
                "Passo 2: No intervalo [0, 1], a reta y = x fica acima da parábola y = x² (ex: em x = 0,5, 0,5 > 0,25).",
                "Passo 3: A área é dada pela integral: A = ∫_0^1 (curva superior - curva inferior) dx:",
                "  A = ∫_0^1 (x - x²) dx.",
                "Passo 4: Integrar: [ x²/2 - x³/3 ]_0^1 = (1/2 - 1/3) - 0 = (3 - 2) / 6 = 1/6.",
                "Resultado Final: 1/6."
            ),
            pedagogicalAlert = "Área é sempre uma grandeza estritamente positiva; subtraia sempre a curva inferior da superior: ∫ (y_cima - y_baixo) dx.",
            keyMethod = "Cálculo de área entre curvas por integral definida"
        ),
        UniversalExercise(
            id = "calc_int_med_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Mediano",
            source = "Cálculo I / 1ª Parte do TFC (Derivada da Integral)",
            problemStatement = """Pelo Teorema Fundamental do Cálculo, qual é a derivada da função g(x) = ∫₀^{x²} cos(t) dt?""",
            options = listOf(
                "2x · cos(x²)",
                "cos(x²)",
                "2x · sen(x²)",
                "cos(x)",
                "-sen(x²)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A 1ª parte do TFC combinada com a Regra da Cadeia estabelece que:",
                "  d/dx [ ∫_a^{u(x)} f(t) dt ] = f(u(x)) · u'(x).",
                "Passo 2: O limite superior é u(x) = x², logo u'(x) = 2x.",
                "Passo 3: Substituir t por u(x): f(u(x)) = cos(x²).",
                "Passo 4: Multiplicar pela derivada da cadeia: 2x cos(x²)."
            ),
            pedagogicalAlert = "Se o limite de integração for uma função de x (como x²), deve-se multiplicar pela derivada interna pelo TFC com Regra da Cadeia.",
            keyMethod = "Derivação sob o sinal de integral: d/dx [∫_a^u f(t) dt] = f(u) · u'"
        ),
        UniversalExercise(
            id = "calc_int_dif_1",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Difícil",
            source = "Cálculo I / Frações Parciais",
            problemStatement = """Resolva a integral utilizando decomposição em frações parciais:
∫ [ 1 / (x² - 1) ] dx""",
            options = listOf(
                "(1/2) · ln|(x - 1) / (x + 1)| + C",
                "ln|x² - 1| + C",
                "arctan(x) + C",
                "(1/2) · ln|x² - 1| + C",
                "ln|(x + 1) / (x - 1)| + C"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Fatorar o denominador: x² - 1 = (x - 1)(x + 1).",
                "Passo 2: Decomposição em frações parciais: 1 / [(x - 1)(x + 1)] = A / (x - 1) + B / (x + 1).",
                "Passo 3: 1 = A(x + 1) + B(x - 1).",
                "  Se x = 1: 1 = A(2) => A = 1/2.",
                "  Se x = -1: 1 = B(-2) => B = -1/2.",
                "Passo 4: Integrar separadamente:",
                "  ∫ [ (1/2)/(x - 1) - (1/2)/(x + 1) ] dx = (1/2) ln|x - 1| - (1/2) ln|x + 1| + C.",
                "Passo 5: Aplicar propriedade dos logaritmos ln(A) - ln(B) = ln(A/B):",
                "  (1/2) ln|(x - 1)/(x + 1)| + C."
            ),
            pedagogicalAlert = "Não confunda ∫ 1/(x² - 1) dx (que dá frações parciais / arctanh) com ∫ 1/(x² + 1) dx que dá arctan(x).",
            keyMethod = "Método das Frações Parciais para fatores lineares distintos"
        ),
        UniversalExercise(
            id = "calc_int_dif_2",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Difícil",
            source = "Cálculo I / Comprimento de Arco de Curva",
            problemStatement = """Qual integral representa o comprimento de arco s de uma curva suave y = f(x) no intervalo [a, b]?""",
            options = listOf(
                "∫_a^b √(1 + [f'(x)]²) dx",
                "∫_a^b √(1 + f(x)) dx",
                "∫_a^b (1 + [f'(x)]²) dx",
                "2π · ∫_a^b f(x) dx",
                "∫_a^b √([f'(x)]² - 1) dx"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Pelo Teorema de Pitágoras no elemento diferencial de arco: (ds)² = (dx)² + (dy)².",
                "Passo 2: Fatorando (dx)²: ds = √[ (dx)² (1 + (dy/dx)²) ] = √[ 1 + (f'(x))² ] dx.",
                "Passo 3: Integrando de a até b obtemos a fórmula do comprimento de arco retificado:",
                "  s = ∫_a^b √[ 1 + (f'(x))² ] dx."
            ),
            pedagogicalAlert = "O elemento de linha ds = √(1 + (y')²) dx decorre diretamente do Teorema de Pitágoras infinitesimal.",
            keyMethod = "Fórmula do comprimento de arco retificado: s = ∫ √(1 + [f'(x)]²) dx"
        )
    )
}
