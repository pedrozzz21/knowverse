package com.example.data.math

data class CalculusExerciseItem(
    val id: String,
    val topicCode: String,
    val topicTitle: String,
    val difficulty: String,
    val source: String,
    val problemStatement: String,
    val options: List<String>,
    val correctIndex: Int,
    val solutionSteps: List<String>,
    val pedagogicalAlert: String,
    val keyMethod: String,
    val xpReward: Int = 30
)

object CalculusExercisesBank {

    val allExercises: List<CalculusExerciseItem> = listOf(
        // ========================================================
        // MÓDULO I: FUNÇÕES, DOMÍNIO E PROPRIEDADES (1xxxx)
        // ========================================================
        CalculusExerciseItem(
            id = "10101",
            topicCode = "01",
            topicTitle = "Funções, Domínio e Gráficos",
            difficulty = "Fácil",
            source = "UFRJ / Aula de Exercícios",
            problemStatement = """Determine o domínio real da função \[ f(x) = \ln(x - 1) - 1 \]""",
            options = listOf(
                """[1, +\infty)""",
                """(1, +\infty)""",
                """\mathbb{R} \setminus \{1\}""",
                """(0, +\infty)""",
                """[0, 1) \cup (1, +\infty)"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Identificação da restrição logarítmica. A função logaritmo natural ln(u) só é definida para argumentos estritamente positivos (u > 0).",
                "Passo 2: Imposição da inequação: x - 1 > 0 => x > 1.",
                "Passo 3: A constante -1 subtraída fora do logaritmo não impõe restrições adicionais.",
                "Resultado Final: D_f = (1, +\\infty)."
            ),
            pedagogicalAlert = "Não confunda x - 1 > 0 com x - 1 >= 0: o argumento do logaritmo NUNCA pode ser zero!",
            keyMethod = "Restrição logarítmica: x - 1 > 0 => D = (1, +\\infty)"
        ),

        CalculusExerciseItem(
            id = "10102",
            topicCode = "01",
            topicTitle = "Funções, Domínio e Gráficos",
            difficulty = "Fácil",
            source = "UFRJ (Lista 1)",
            problemStatement = """Determine o domínio real da função \[ f(x) = \frac{5}{x - 6} + \sqrt{x - 3} \]""",
            options = listOf(
                """[3, +\infty)""",
                """(3, 6) \cup (6, +\infty)""",
                """[3, 6) \cup (6, +\infty)""",
                """\mathbb{R} \setminus \{6\}""",
                """[0, 6) \cup (6, +\infty)"""
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Restrição da fração racional: o denominador não pode ser nulo, logo x - 6 ≠ 0 => x ≠ 6.",
                "Passo 2: Restrição do radical par: o radicando deve ser não negativo, logo x - 3 >= 0 => x >= 3.",
                "Passo 3: Intersecção simultânea das condições: x in [3, +\\infty) com x ≠ 6.",
                "Resultado Final: D_f = [3, 6) U (6, +\\infty)."
            ),
            pedagogicalAlert = "Lembre-se de incluir o ponto x = 3 com colchete fechado, pois raiz de 0 é perfeitamente válida no numerador.",
            keyMethod = "Intersecção: x ≠ 6 e x >= 3 => D = [3, 6) U (6, +\\infty)"
        ),

        CalculusExerciseItem(
            id = "20103",
            topicCode = "01",
            topicTitle = "Funções, Domínio e Gráficos",
            difficulty = "Mediano",
            source = "UFRJ (Lista 1)",
            problemStatement = """Determine o domínio real da função irracional \[ f(x) = \sqrt{4 - x} - \sqrt{x^2 - 1} \]""",
            options = listOf(
                """[-1, 1] \cup [4, +\infty)""",
                """(-\infty, -1] \cup [1, 4]""",
                """[-2, -1] \cup [1, 2]""",
                """[1, 4]""",
                """(-\infty, 4]"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Primeira condição radical: 4 - x >= 0 => x <= 4, logo D_1 = (-\\infty, 4].",
                "Passo 2: Segunda condição radical: x² - 1 >= 0 => (x - 1)(x + 1) >= 0 => x <= -1 ou x >= 1, logo D_2 = (-\\infty, -1] U [1, +\\infty).",
                "Passo 3: Intersecção D_1 ∩ D_2: (-\\infty, -1] U [1, 4].",
                "Resultado Final: D_f = (-\\infty, -1] U [1, 4]."
            ),
            pedagogicalAlert = "Ao resolver x² - 1 >= 0, faça o estudo de sinais da parábola para não esquecer a região x <= -1.",
            keyMethod = "Inequação simultânea: 4 - x >= 0 e x² - 1 >= 0 => D = (-\\infty, -1] U [1, 4]"
        ),

        CalculusExerciseItem(
            id = "20104",
            topicCode = "01",
            topicTitle = "Funções, Domínio e Gráficos",
            difficulty = "Mediano",
            source = "UFRJ / Notas de Aula",
            problemStatement = """Para a função homográfica \[ f(x) = \frac{x}{x - 3} \] determine o seu conjunto imagem real \(\operatorname{Im}(f)\).""",
            options = listOf(
                """\mathbb{R} \setminus \{3\}""",
                """\mathbb{R} \setminus \{0\}""",
                """\mathbb{R} \setminus \{1\}""",
                """(0, +\infty)""",
                """[-3, 3]"""
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Escreva y = f(x): y = x / (x - 3).",
                "Passo 2: Isole a variável x em função de y: y(x - 3) = x => yx - 3y = x => yx - x = 3y => x(y - 1) = 3y.",
                "Passo 3: Conclui-se que x = 3y / (y - 1).",
                "Passo 4: Para que x exista no conjunto dos números reais, o denominador em y não pode ser zero: y - 1 ≠ 0 => y ≠ 1.",
                "Resultado Final: Im(f) = \\mathbb{R} \\setminus {1}."
            ),
            pedagogicalAlert = "O valor excluído do domínio é x = 3, mas o valor excluído da imagem é y = 1 (assíntota horizontal da curva).",
            keyMethod = "Isolamento de x: x = 3y / (y - 1) => Im = R \\setminus {1}"
        ),

        CalculusExerciseItem(
            id = "20205",
            topicCode = "02",
            topicTitle = "Composição, Inversas e Simetria",
            difficulty = "Mediano",
            source = "UFRJ (Lista 1)",
            problemStatement = """Classifique as funções quanto à paridade em par, ímpar ou nem par nem ímpar:
(I) \(f(x) = \frac{x}{x^2+1}\)
(II) \(g(x) = \frac{x}{x+1}\)
(III) \(h(x) = x|x|\)""",
            options = listOf(
                """(I) Ímpar, (II) Par, (III) Ímpar""",
                """(I) Par, (II) Nem par nem ímpar, (III) Par""",
                """(I) Ímpar, (II) Nem par nem ímpar, (III) Ímpar""",
                """(I) Ímpar, (II) Ímpar, (III) Nem par nem ímpar""",
                """(I) Par, (II) Par, (III) Ímpar"""
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Avalie f(-x) para (I): f(-x) = (-x)/((-x)² + 1) = -x/(x² + 1) = -f(x). Logo, (I) é estritamente ímpar.",
                "Passo 2: Avalie g(-x) para (II): g(-x) = (-x)/(-x + 1) = x/(x - 1). Diferente de g(x) e de -g(x). Logo, (II) é nem par nem ímpar.",
                "Passo 3: Avalie h(-x) para (III): h(-x) = (-x)|-x| = -x|x| = -h(x). Logo, (III) é estritamente ímpar."
            ),
            pedagogicalAlert = "Lembre-se de que |-x| = |x|, logo o sinal negativo em (-x)|-x| vem exclusivamente do primeiro fator x.",
            keyMethod = "Teste de paridade analítico: f(-x) = -f(x) (ímpar), f(-x) = f(x) (par)"
        ),

        CalculusExerciseItem(
            id = "30206",
            topicCode = "02",
            topicTitle = "Composição, Inversas e Simetria",
            difficulty = "Difícil",
            source = "UFRJ (Lista 2)",
            problemStatement = """Considere a função bijetora \(f: [1, +\infty) \to [1, +\infty)\) dada por \[ f(x) = \sqrt{x^2 - 2x + 2} \] Obtenha a expressão analítica explícita da sua função inversa \(f^{-1}(x)\).""",
            options = listOf(
                """f^{-1}(x) = 1 + \sqrt{x^2 - 1}""",
                """f^{-1}(x) = 1 - \sqrt{x^2 - 1}""",
                """f^{-1}(x) = \sqrt{x^2 + 2x - 2}""",
                """f^{-1}(x) = x^2 - 2x + 1""",
                """f^{-1}(x) = 1 + \sqrt{x^2 + 1}"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Complete quadrados dentro do radical: x² - 2x + 2 = (x - 1)² + 1.",
                "Passo 2: Reescreva y = sqrt((x - 1)² + 1). Eleve ao quadrado: y² = (x - 1)² + 1.",
                "Passo 3: Isole (x - 1): (x - 1)² = y² - 1 => x - 1 = ± sqrt(y² - 1).",
                "Passo 4: Como o domínio original é [1, +\\infty), x >= 1 => x - 1 >= 0, exigindo a raiz positiva: x = 1 + sqrt(y² - 1).",
                "Resultado Final: f^{-1}(x) = 1 + \\sqrt{x^2 - 1}."
            ),
            pedagogicalAlert = "Ao extrair a raiz quadrada de (x-1)², o domínio x >= 1 força a escolha obrigatória do sinal positivo (+).",
            keyMethod = "Completação de quadrados: y² - 1 = (x - 1)² => f⁻¹(x) = 1 + sqrt(x² - 1)"
        ),

        CalculusExerciseItem(
            id = "10151",
            topicCode = "01",
            topicTitle = "Funções, Domínio e Gráficos",
            difficulty = "Fácil",
            source = "Questão Inédita",
            problemStatement = """Determine o domínio real da função logarítmica composta \[ g(x) = \ln(2x - 8) + 4 \]""",
            options = listOf(
                """[4, +\infty)""",
                """(-\infty, 4)""",
                """(4, +\infty)""",
                """(8, +\infty)""",
                """\mathbb{R} \setminus \{4\}"""
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: O argumento do logaritmo natural deve ser estritamente positivo: 2x - 8 > 0.",
                "Passo 2: 2x > 8 => x > 4.",
                "Passo 3: A constante +4 adicionada fora do logaritmo não afeta o domínio da função.",
                "Resultado Final: D_g = (4, +\\infty)."
            ),
            pedagogicalAlert = "Não feche o intervalo com colchete em 4: ln(0) não existe nos números reais!",
            keyMethod = "Condição: 2x - 8 > 0 => x > 4 => D = (4, +\\infty)"
        ),

        CalculusExerciseItem(
            id = "20152",
            topicCode = "01",
            topicTitle = "Funções, Domínio e Gráficos",
            difficulty = "Mediano",
            source = "Questão Inédita",
            problemStatement = """Determine o domínio real da função irracional e fracionária \[ h(x) = \sqrt{9 - x^2} + \frac{1}{\sqrt{x - 1}} \]""",
            options = listOf(
                """[-3, 3]""",
                """(1, 3]""",
                """[1, 3]""",
                """(1, 3)""",
                """[1, +\infty)"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Restrição do radical no numerador: 9 - x² >= 0 => x² <= 9 => -3 <= x <= 3.",
                "Passo 2: Restrição do radical no denominador: x - 1 deve ser estritamente positivo (x - 1 > 0) para evitar raiz de negativo e divisão por zero => x > 1.",
                "Passo 3: Intersecção: [-3, 3] ∩ (1, +\\infty) = (1, 3].",
                "Resultado Final: D_h = (1, 3]."
            ),
            pedagogicalAlert = "Atenção ao extremo 1: ele é estritamente aberto '(' pois o radical está no denominador!",
            keyMethod = "Intersecção: 9 - x² >= 0 e x - 1 > 0 => D = (1, 3]"
        ),

        CalculusExerciseItem(
            id = "20253",
            topicCode = "02",
            topicTitle = "Composição, Inversas e Simetria",
            difficulty = "Mediano",
            source = "Questão Inédita",
            problemStatement = """Dada a função homográfica \[ f(x) = \frac{2x + 5}{1 - x} \quad (x \neq 1) \] determine a expressão da sua função inversa \(f^{-1}(x)\) e seu conjunto imagem.""",
            options = listOf(
                """f^{-1}(x) = \frac{x - 5}{x + 2}, \quad \operatorname{Im} = \mathbb{R} \setminus \{-2\}""",
                """f^{-1}(x) = \frac{x + 5}{x - 2}, \quad \operatorname{Im} = \mathbb{R} \setminus \{2\}""",
                """f^{-1}(x) = \frac{1 - x}{2x + 5}, \quad \operatorname{Im} = \mathbb{R} \setminus \{-5/2\}""",
                """f^{-1}(x) = \frac{2x - 5}{x + 1}, \quad \operatorname{Im} = \mathbb{R} \setminus \{-1\}""",
                """f^{-1}(x) = \frac{x - 5}{2 - x}, \quad \operatorname{Im} = \mathbb{R} \setminus \{2\}"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Faça y = (2x + 5) / (1 - x).",
                "Passo 2: y(1 - x) = 2x + 5 => y - yx = 2x + 5.",
                "Passo 3: Agrupe os termos em x: y - 5 = 2x + yx => x(y + 2) = y - 5.",
                "Passo 4: Isole x: x = (y - 5) / (y + 2). Logo f⁻¹(x) = (x - 5) / (x + 2).",
                "Passo 5: Imagem de f é o domínio de f⁻¹, exigindo x + 2 ≠ 0 => Im = R \\setminus {-2}."
            ),
            pedagogicalAlert = "A imagem da função original é exatamente idêntica ao domínio da função inversa encontrada.",
            keyMethod = "Isolamento: y(1 - x) = 2x + 5 => f⁻¹(x) = (x - 5)/(x + 2), Im = R \\setminus {-2}"
        ),

        CalculusExerciseItem(
            id = "30254",
            topicCode = "02",
            topicTitle = "Composição, Inversas e Simetria",
            difficulty = "Difícil",
            source = "Questão Inédita",
            problemStatement = """Considere a função real definida por \[ k(x) = x|x| + x^3 \] Classifique sua paridade e demonstre a sua bijetividade em \(\mathbb{R}\).""",
            options = listOf(
                """k é par e não admite inversa em \mathbb{R}""",
                """k é ímpar, estritamente crescente e bijetora em \mathbb{R}""",
                """k é nem par nem ímpar, mas bijetora em \mathbb{R}""",
                """k é ímpar, com valor máximo absoluto em x = 0""",
                """k é periódica de período 2\pi"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: k(-x) = (-x)|-x| + (-x)³ = -x|x| - x³ = -(x|x| + x³) = -k(x) => k é estritamente ímpar.",
                "Passo 2: Ramos por partes: k(x) = x² + x³ para x >= 0, e k(x) = -x² + x³ para x < 0.",
                "Passo 3: Derivada por ramos: Para x > 0: k'(x) = 2x + 3x² > 0. Para x < 0: k'(x) = -2x + 3x² = x(3x - 2) > 0. Em x = 0: k'(0) = 0.",
                "Passo 4: Sendo k'(x) > 0 para todo x ≠ 0 e contínua, k é estritamente crescente em toda a reta real, o que garante injetividade.",
                "Passo 5: Limites nos infinitos tendem a ±\\infty, garantindo sobrejetividade pelo TVI. Logo, k é bijetora em R."
            ),
            pedagogicalAlert = "Uma função contínua estritamente monótona em R cujos limites nos extremos são ±\\infty é sempre bijetora.",
            keyMethod = "k(-x) = -k(x) (ímpar); k'(x) > 0 (estrita monotonicidade => bijeção)"
        ),

        // ========================================================
        // MÓDULO II: LIMITES, CONTINUIDADE E ASSÍNTOTAS (2xxxx)
        // ========================================================
        CalculusExerciseItem(
            id = "10301",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Fácil",
            source = "UFRJ / Aula de Exercícios / Lista 3",
            problemStatement = """Calcule por fatoração algébrica direta o limite: \[ \lim_{x \to -2} \frac{x^2 + x - 2}{x + 2} \]""",
            options = listOf(
                """0""",
                """-1""",
                """-3""",
                """1""",
                """\text{Não existe}"""
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Substituição direta: ((-2)² + (-2) - 2) / (-2 + 2) = (4 - 4)/0 = 0/0 (Indeterminação).",
                "Passo 2: Fatoração do numerador trinômio do 2º grau: x² + x - 2 = (x + 2)(x - 1).",
                "Passo 3: Cancelamento do fator nulo (x + 2) para x ≠ -2: lim_{x -> -2} (x - 1).",
                "Passo 4: Avaliação final: -2 - 1 = -3."
            ),
            pedagogicalAlert = "Podemos cancelar (x + 2) porque o limite investiga a vizinhança de -2 onde x ≠ -2, logo x + 2 ≠ 0.",
            keyMethod = "Fatoração do numerador (x + 2)(x - 1) => cancelamento => L = -3"
        ),

        CalculusExerciseItem(
            id = "20302",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Mediano",
            source = "UFRJ / Aula de Exercícios / Lista 3",
            problemStatement = """Calcule utilizando o método da racionalização pelo conjugado: \[ \lim_{x \to 4} \frac{\sqrt{x + 5} - 3}{x - 4} \]""",
            options = listOf(
                """1/3""",
                """1/6""",
                """1/9""",
                """0""",
                """3/4"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Substituição direta resulta em 0/0.",
                "Passo 2: Multiplique numerador e denominador pelo conjugado (sqrt(x+5) + 3).",
                "Passo 3: Numerador: (sqrt(x+5) - 3)(sqrt(x+5) + 3) = (x + 5) - 9 = x - 4.",
                "Passo 4: Cancele o fator comum (x - 4): 1 / (sqrt(x + 5) + 3).",
                "Passo 5: Avaliação no limite x -> 4: 1 / (sqrt(9) + 3) = 1 / (3 + 3) = 1/6."
            ),
            pedagogicalAlert = "Não expanda o denominador! Mantenha (x - 4)(sqrt(x+5)+3) para facilitar o cancelamento imediato.",
            keyMethod = "Conjugado do numerador (sqrt(x+5)+3) => cancelamento de (x - 4) => L = 1/6"
        ),

        CalculusExerciseItem(
            id = "20303",
            topicCode = "04",
            topicTitle = "Limites no Infinito e Assíntotas",
            difficulty = "Mediano",
            source = "UFRJ / Notas de Aula",
            problemStatement = """Calcule sem utilizar a Regra de L'Hôpital, colocando a maior potência em evidência: \[ \lim_{x \to \infty} \frac{3x^2 - x - 2}{5x^2 + 4x + 1} \]""",
            options = listOf(
                """0""",
                """3/5""",
                """\infty""",
                """-2""",
                """1"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Identifique a maior potência do denominador: x².",
                "Passo 2: Divida todos os termos do numerador e do denominador por x²: lim [3 - 1/x - 2/x²] / [5 + 4/x + 1/x²].",
                "Passo 3: Quando x -> \\infty, os termos 1/x, 2/x², 4/x e 1/x² tendem a 0.",
                "Passo 4: Resultado: (3 - 0 - 0) / (5 + 0 + 0) = 3/5."
            ),
            pedagogicalAlert = "Em limites no infinito de polinômios de mesmo grau, o resultado é a razão direta entre os coeficientes líderes.",
            keyMethod = "Divisão pela maior potência x² => 3/5"
        ),

        CalculusExerciseItem(
            id = "20304",
            topicCode = "04",
            topicTitle = "Limites no Infinito e Assíntotas",
            difficulty = "Mediano",
            source = "UFRJ / Notas de Aula / Lista 4",
            problemStatement = """Calcule o limite no infinito envolvendo radicais para \(x \to -\infty\): \[ \lim_{x \to -\infty} \frac{\sqrt{4x^2 + 1}}{3x - 5} \]""",
            options = listOf(
                """2/3""",
                """-2/3""",
                """4/3""",
                """-4/3""",
                """0"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Fatore x² de dentro do radical: sqrt(4x² + 1) = sqrt(x²(4 + 1/x²)) = sqrt(x²) * sqrt(4 + 1/x²).",
                "Passo 2: IDENTIDADE FUNDAMENTAL: sqrt(x²) = |x|. Como x -> -\\infty, x < 0, logo |x| = -x!",
                "Passo 3: O numerador reescreve-se como: -x * sqrt(4 + 1/x²).",
                "Passo 4: Divida numerador e denominador por x: [-sqrt(4 + 1/x²)] / [3 - 5/x].",
                "Passo 5: Tomando x -> -\\infty: -sqrt(4 + 0) / (3 - 0) = -2/3."
            ),
            pedagogicalAlert = "Alerta máximo de prova: sqrt(x²) = -x para x negativo! Omitir esse sinal negativo é o erro nº 1 em assíntotas.",
            keyMethod = "Fatoração radical com x -> -\\infty: sqrt(x²) = |x| = -x => L = -2/3"
        ),

        CalculusExerciseItem(
            id = "20305",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Mediano",
            source = "UFRJ / Notas de Aula / Lista 4",
            problemStatement = """Determine o valor da constante \(c\) para que a função seja contínua em \(x = 2\): \[ f(x) = \begin{cases} cx + 1, & x \le 2 \\ x^2 - c, & x > 2 \end{cases} \]""",
            options = listOf(
                """c = 0""",
                """c = 1""",
                """c = 2""",
                """c = -1""",
                """c = 3"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Valor no ponto f(2): ramo x <= 2 => f(2) = c(2) + 1 = 2c + 1.",
                "Passo 2: Limite lateral à esquerda (x -> 2⁻): lim (cx + 1) = 2c + 1.",
                "Passo 3: Limite lateral à direita (x -> 2⁺): lim (x² - c) = 2² - c = 4 - c.",
                "Passo 4: Condição de continuidade: lim_{x->2⁻} f(x) = lim_{x->2⁺} f(x) = f(2) => 2c + 1 = 4 - c.",
                "Passo 5: 3c = 3 => c = 1."
            ),
            pedagogicalAlert = "Para que haja continuidade, os limites laterais devem ser finitos e rigorosamente iguais entre si e ao valor funcional f(a).",
            keyMethod = "Igualação de limites laterais: 2c + 1 = 4 - c => c = 1"
        ),

        CalculusExerciseItem(
            id = "30306",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Difícil",
            source = "UFRJ / Aula de Exercícios / Lista 4",
            problemStatement = """Determine as constantes \(a\) e \(b\) para que a função seja contínua em \(\mathbb{R}\): \[ f(x) = \begin{cases} \frac{x^2 - 4}{x - 2}, & x < 2 \\ ax^2 - bx + 3, & 2 \le x < 3 \\ 2x - a + b, & x \ge 3 \end{cases} \]""",
            options = listOf(
                """a = 1, b = 2""",
                """a = 1/2, b = 1/2""",
                """a = 2, b = 1""",
                """a = 0, b = 1""",
                """a = -1, b = 2"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Transição em x = 2: Para x < 2, f(x) = (x-2)(x+2)/(x-2) = x + 2 => lim_{x->2⁻} = 4.",
                "Passo 2: Em x = 2: f(2) = a(2)² - b(2) + 3 = 4a - 2b + 3. Igualando: 4a - 2b + 3 = 4 => 4a - 2b = 1 (Eq. 1).",
                "Passo 3: Transição em x = 3: lim_{x->3⁻} = a(3)² - b(3) + 3 = 9a - 3b + 3. lim_{x->3⁺} = f(3) = 2(3) - a + b = 6 - a + b.",
                "Passo 4: Igualando em x = 3: 9a - 3b + 3 = 6 - a + b => 10a - 4b = 3 (Eq. 2).",
                "Passo 5: Multiplique Eq. 1 por 2: 8a - 4b = 2. Subtraindo da Eq. 2: 2a = 1 => a = 1/2. Substituindo: 4(1/2) - 2b = 1 => 2 - 2b = 1 => b = 1/2."
            ),
            pedagogicalAlert = "Monte o sistema linear 2x2 com as duas condições de colagem nos pontos de transição x = 2 e x = 3.",
            keyMethod = "Sistema linear 2x2: 4a - 2b = 1 e 10a - 4b = 3 => a = 1/2, b = 1/2"
        ),

        CalculusExerciseItem(
            id = "10351",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Fácil",
            source = "Questão Inédita",
            problemStatement = """Calcule por fatoração algébrica direta: \[ \lim_{x \to 5} \frac{x^2 - 25}{x - 5} \]""",
            options = listOf(
                """0""",
                """5""",
                """10""",
                """25""",
                """\text{Indeterminado}"""
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Substituição direta: (25 - 25)/(5 - 5) = 0/0.",
                "Passo 2: Fatore a diferença de quadrados no numerador: x² - 25 = (x - 5)(x + 5).",
                "Passo 3: Cancele o termo nulo (x - 5): lim_{x -> 5} (x + 5).",
                "Passo 4: 5 + 5 = 10."
            ),
            pedagogicalAlert = "Diferença de quadrados A² - B² = (A - B)(A + B) é a fatoração mais recorrente de provas de Cálculo.",
            keyMethod = "Diferença de quadrados (x - 5)(x + 5)/(x - 5) => L = 10"
        ),

        CalculusExerciseItem(
            id = "20352",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Mediano",
            source = "Questão Inédita",
            problemStatement = """Calcule sem utilizar a Regra de L'Hôpital: \[ \lim_{x \to 0} \frac{\sqrt{x + 9} - 3}{x} \]""",
            options = listOf(
                """1/3""",
                """1/6""",
                """1/9""",
                """0""",
                """3"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Substituição direta resulta em 0/0.",
                "Passo 2: Multiplique numerador e denominador pelo conjugado (sqrt(x+9) + 3).",
                "Passo 3: Numerador: (sqrt(x+9) - 3)(sqrt(x+9) + 3) = (x + 9) - 9 = x.",
                "Passo 4: Cancele x no numerador e denominador: 1 / (sqrt(x + 9) + 3).",
                "Passo 5: Avalie em x = 0: 1 / (3 + 3) = 1/6."
            ),
            pedagogicalAlert = "Não esqueça de manter o conjugado somando no denominador após o cancelamento.",
            keyMethod = "Racionalização pelo conjugado (sqrt(x+9)+3) => L = 1/6"
        ),

        CalculusExerciseItem(
            id = "20453",
            topicCode = "04",
            topicTitle = "Limites no Infinito e Assíntotas",
            difficulty = "Mediano",
            source = "Questão Inédita",
            problemStatement = """Determine todas as equações de assíntotas verticais e horizontais da curva \[ y = \frac{2x^2 + 3}{x^2 - 4} \]""",
            options = listOf(
                """Verticais: x = 2; Horizontal: y = 0""",
                """Verticais: x = 2 e x = -2; Horizontal: y = 2""",
                """Verticais: x = 4; Horizontal: y = 2""",
                """Verticais: x = 0; Horizontal: y = 3""",
                """Não possui assíntotas"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Assíntotas Verticais ocorrem onde o denominador se anula sem anular o numerador: x² - 4 = 0 => x = 2 e x = -2.",
                "Passo 2: Como o numerador 2(±2)² + 3 = 11 ≠ 0, ambos os pontos são assíntotas verticais reais x = 2 e x = -2.",
                "Passo 3: Assíntota Horizontal: calcule o limite no infinito lim_{x -> ±\\infty} (2x² + 3)/(x² - 4) = lim (2 + 3/x²)/(1 - 4/x²) = 2.",
                "Resultado Final: Assíntotas Verticais x = ±2; Assíntota Horizontal y = 2."
            ),
            pedagogicalAlert = "Assíntotas verticais são retas verticais da forma x = c; assíntotas horizontais são da forma y = L.",
            keyMethod = "Denominador nulo => x = ±2 (verticais); limite no infinito => y = 2 (horizontal)"
        ),

        CalculusExerciseItem(
            id = "30354",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Difícil",
            source = "Questão Inédita",
            problemStatement = """Determine os valores reais das constantes \(m\) e \(k\) para que a função seja contínua em todo \(\mathbb{R}\): \[ g(x) = \begin{cases} mx + 2k, & x < 1 \\ 5, & x = 1 \\ x^2 + kx + m, & x > 1 \end{cases} \]""",
            options = listOf(
                """m = 3, k = 1""",
                """m = 1, k = 2""",
                """m = 2, k = 3""",
                """m = 4, k = 1""",
                """m = 0, k = 5"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Continuidade em x = 1 exige lim_{x->1⁻} g(x) = lim_{x->1⁺} g(x) = g(1) = 5.",
                "Passo 2: Limite pela esquerda: lim_{x->1⁻} (mx + 2k) = m + 2k = 5 (Eq. 1).",
                "Passo 3: Limite pela direita: lim_{x->1⁺} (x² + kx + m) = 1 + k + m = 5 => m + k = 4 (Eq. 2).",
                "Passo 4: Subtraindo Eq. 2 de Eq. 1: (m + 2k) - (m + k) = 5 - 4 => k = 1.",
                "Passo 5: Substituindo k = 1 em Eq. 2: m + 1 = 4 => m = 3."
            ),
            pedagogicalAlert = "Ambos os ramos laterais devem se encontrar no ponto central isolado g(1) = 5.",
            keyMethod = "Sistema em x = 1: m + 2k = 5 e m + k = 4 => k = 1, m = 3"
        ),

        // ========================================================
        // MÓDULO III: DERIVADAS, REGRAS E RETA TANGENTE (3xxxx)
        // ========================================================
        CalculusExerciseItem(
            id = "10501",
            topicCode = "05",
            topicTitle = "Derivadas e Reta Tangente",
            difficulty = "Fácil",
            source = "UFRJ / Notas de Aula",
            problemStatement = """Obtenha a equação da reta tangente à curva \[ f(x) = x^2 - 3x + 4 \] no ponto de abscissa \(a = 2\).""",
            options = listOf(
                """y = 2x - 2""",
                """y = x""",
                """y = x + 2""",
                """y = -x + 4""",
                """y = 3x - 4"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Calcule a ordenada f(2): f(2) = 2² - 3(2) + 4 = 4 - 6 + 4 = 2. Ponto P(2, 2).",
                "Passo 2: Calcule a derivada f'(x): d/dx(x² - 3x + 4) = 2x - 3.",
                "Passo 3: Declividade m = f'(2) = 2(2) - 3 = 1.",
                "Passo 4: Equação da reta tangente: y - y₀ = m(x - x₀) => y - 2 = 1(x - 2) => y = x."
            ),
            pedagogicalAlert = "Siga o algoritmo de 4 passos: 1. Ponto P(a, f(a)) -> 2. Derivada f'(x) -> 3. Inclinação m = f'(a) -> 4. Equação da reta.",
            keyMethod = "Roteiro de 4 passos: f(2)=2, f'(x)=2x-3, m=1 => y = x"
        ),

        CalculusExerciseItem(
            id = "20502",
            topicCode = "05",
            topicTitle = "Derivadas e Reta Tangente",
            difficulty = "Mediano",
            source = "UFRJ / Aula de Exercícios",
            problemStatement = """Determine a lei da derivada de \(f(x) = \frac{3}{x^2 - 2}\) e encontre a equação da reta tangente no ponto \(x = 1\).""",
            options = listOf(
                """y = 6x - 9""",
                """y = -6x + 3""",
                """y = -3x""",
                """y = -6x - 3""",
                """y = 3x - 6"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Calcule f(1): f(1) = 3 / (1² - 2) = 3 / (-1) = -3. Ponto (1, -3).",
                "Passo 2: Reescreva f(x) = 3(x² - 2)⁻¹ e aplique a regra da cadeia: f'(x) = 3(-1)(x² - 2)⁻²(2x) = -6x / (x² - 2)².",
                "Passo 3: Declividade m = f'(1) = -6(1) / (1 - 2)² = -6 / 1 = -6.",
                "Passo 4: Equação da reta tangente: y - (-3) = -6(x - 1) => y + 3 = -6x + 6 => y = -6x + 3."
            ),
            pedagogicalAlert = "Ao usar a regra da cadeia em u⁻¹, não se esqueça de multiplicar pela derivada interna u' = 2x.",
            keyMethod = "Derivada f'(x) = -6x/(x²-2)²; em x=1: m=-6 => y = -6x + 3"
        ),

        CalculusExerciseItem(
            id = "20503",
            topicCode = "06",
            topicTitle = "Regra da Cadeia e Derivada Implícita",
            difficulty = "Mediano",
            source = "UFRJ / Aula de Exercícios / Lista 6",
            problemStatement = """Aplique as regras de derivação para obter a derivada das funções:
(I) \(f(x) = \cos(x) \cdot (x^2 - 3)\)
(II) \(g(x) = \tan(\sin x)\)""",
            options = listOf(
                """(I) -\sin(x)(x²-3) + 2x\cos(x);  (II) \sec^2(\sin x)\cos(x)""",
                """(I) \sin(x)(x²-3) - 2x\cos(x);  (II) \sec^2(x)\cos(x)""",
                """(I) -2x\sin(x);  (II) \tan(\cos x)""",
                """(I) -\sin(x)(2x);  (II) \sec^2(\sin x)""",
                """(I) \cos(x)(2x) + \sin(x)(x²-3);  (II) \cos^2(\sin x)"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Regra do produto para (I): u = cos(x), v = x² - 3 => u' = -sin(x), v' = 2x.",
                "Passo 2: f'(x) = u'v + uv' = -sin(x)(x² - 3) + 2x cos(x).",
                "Passo 3: Regra da cadeia para (II): y = tan(u), onde u = sin(x).",
                "Passo 4: y' = sec²(u) * u' = sec²(sin x) * cos(x)."
            ),
            pedagogicalAlert = "Na regra da cadeia, derive 'de fora para dentro': derive a tangente em relação ao seno, e depois multiplique pela derivada do seno.",
            keyMethod = "Regra do produto e regra da cadeia: f' = u'v + uv' e g' = sec²(u)*u'"
        ),

        CalculusExerciseItem(
            id = "20604",
            topicCode = "06",
            topicTitle = "Regra da Cadeia e Derivada Implícita",
            difficulty = "Mediano",
            source = "UFRJ (Lista 7)",
            problemStatement = """Utilizando a derivação implícita, determine a equação da reta tangente à curva \(x^2 + xy + y^2 = 3\) no ponto \((1, 1)\).""",
            options = listOf(
                """y = x""",
                """y = -x + 2""",
                """y = -2x + 3""",
                """y = x + 2""",
                """y = -x - 2"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Derive ambos os lados em relação a x: d/dx(x² + xy + y²) = d/dx(3).",
                "Passo 2: 2x + (1*y + x*y') + 2y*y' = 0 (atenção à regra do produto no termo misto xy!).",
                "Passo 3: Agrupe y': y'(x + 2y) = -(2x + y) => y' = -(2x + y) / (x + 2y).",
                "Passo 4: Avalie no ponto (1, 1): m = y'(1, 1) = -(2(1) + 1) / (1 + 2(1)) = -3 / 3 = -1.",
                "Passo 5: Reta tangente: y - 1 = -1(x - 1) => y - 1 = -x + 1 => y = -x + 2."
            ),
            pedagogicalAlert = "Erro clássico: a derivada de xy NÃO é y'! Como tanto x quanto y variam, aplique a regra do produto: (x)'y + x(y)' = y + xy'.",
            keyMethod = "Derivação implícita: 2x + y + xy' + 2yy' = 0 => y'(1,1) = -1 => y = -x + 2"
        ),

        CalculusExerciseItem(
            id = "30505",
            topicCode = "05",
            topicTitle = "Derivadas e Reta Tangente",
            difficulty = "Difícil",
            source = "UFRJ / Aula de Exercícios",
            problemStatement = """Determine as equações de todas as retas tangentes à curva \(3y = x^3 - 3x^2 + 6x + 4\) que sejam paralelas à reta \(2x - y + 3 = 0\).""",
            options = listOf(
                """y = 2x + 4/3  e  y = 2x - 4/3""",
                """y = 2x  e  y = 2x + 4""",
                """y = -x/2 + 1  e  y = -x/2 - 1""",
                """y = 2x + 1  e  y = 2x - 1""",
                """Apenas a reta y = 2x + 4/3"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Escreva y em função de x: y = (1/3)x³ - x² + 2x + 4/3.",
                "Passo 2: A reta de referência é y = 2x + 3, com coeficiente angular m = 2. Retas paralelas devem ter y' = 2.",
                "Passo 3: Derive: y' = x² - 2x + 2. Igualando a 2: x² - 2x + 2 = 2 => x² - 2x = 0 => x(x - 2) = 0 => x = 0 ou x = 2.",
                "Passo 4: Ponto 1 (x = 0): y = 4/3 => P₁(0, 4/3). Reta: y - 4/3 = 2(x - 0) => y = 2x + 4/3.",
                "Passo 5: Ponto 2 (x = 2): y = 8/3 - 4 + 4 + 4/3 = 8/3 => P₂(2, 8/3). Reta: y - 8/3 = 2(x - 2) => y = 2x - 4 + 8/3 = 2x - 4/3."
            ),
            pedagogicalAlert = "Retas paralelas possuem coeficientes angulares idênticos (m₁ = m₂).",
            keyMethod = "y' = 2 => x² - 2x = 0 => Pontos (0, 4/3) e (2, 8/3) => y = 2x ± 4/3"
        ),

        CalculusExerciseItem(
            id = "10551",
            topicCode = "05",
            topicTitle = "Derivadas e Reta Tangente",
            difficulty = "Fácil",
            source = "Questão Inédita",
            problemStatement = """Calcule a derivada da função polinomial \[ f(x) = 4x^3 - 5x^2 + 7x - 2 \] pela aplicação direta da regra da potência.""",
            options = listOf(
                """f'(x) = 12x^2 - 10x + 7""",
                """f'(x) = 12x^3 - 10x^2 + 7x""",
                """f'(x) = 4x^2 - 5x + 7""",
                """f'(x) = 12x^2 - 5x + 7""",
                """f'(x) = 24x - 10"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplique a regra do tombo termo a termo: d/dx(c xⁿ) = c * n xⁿ⁻¹.",
                "Passo 2: d/dx(4x³) = 4 * 3x² = 12x².",
                "Passo 3: d/dx(-5x²) = -5 * 2x = -10x.",
                "Passo 4: d/dx(7x) = 7 * 1 = 7.",
                "Passo 5: d/dx(-2) = 0 (derivada de constante pura é zero).",
                "Resultado Final: f'(x) = 12x² - 10x + 7."
            ),
            pedagogicalAlert = "A derivada de uma constante isolada é sempre nula.",
            keyMethod = "Regra da potência termo a termo: f'(x) = 12x² - 10x + 7"
        ),

        CalculusExerciseItem(
            id = "20552",
            topicCode = "05",
            topicTitle = "Derivadas e Reta Tangente",
            difficulty = "Mediano",
            source = "Questão Inédita",
            problemStatement = """Encontre a equação da reta tangente à curva \(y = x^3 - 2x + 1\) no ponto de abscissa \(x = 2\).""",
            options = listOf(
                """y = 10x - 15""",
                """y = 10x - 20""",
                """y = 6x - 7""",
                """y = 12x - 19""",
                """y = 10x + 5"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Calcule a ordenada y(2): y(2) = 2³ - 2(2) + 1 = 8 - 4 + 1 = 5. Ponto (2, 5).",
                "Passo 2: Calcule a derivada: y' = 3x² - 2.",
                "Passo 3: Declividade no ponto: m = y'(2) = 3(2)² - 2 = 12 - 2 = 10.",
                "Passo 4: Equação da reta: y - 5 = 10(x - 2) => y - 5 = 10x - 20 => y = 10x - 15."
            ),
            pedagogicalAlert = "Não esqueça de somar a ordenada y₀ ao isolar o y na equação final.",
            keyMethod = "Ponto (2, 5), y' = 3x² - 2 => m = 10 => y = 10x - 15"
        ),

        CalculusExerciseItem(
            id = "20653",
            topicCode = "06",
            topicTitle = "Regra da Cadeia e Derivada Implícita",
            difficulty = "Mediano",
            source = "Questão Inédita",
            problemStatement = """Utilize a técnica da derivação implícita para determinar \(\frac{dy}{dx}\) a partir da equação do Folium de Descartes: \[ x^3 + y^3 = 6xy \]""",
            options = listOf(
                """y' = \frac{2y - x^2}{y^2 - 2x}""",
                """y' = \frac{x^2 - 2y}{y^2 - 2x}""",
                """y' = \frac{2y + x^2}{y^2 + 2x}""",
                """y' = \frac{6y - 3x^2}{3y^2}""",
                """y' = -\frac{x^2}{y^2}"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Diferencie ambos os lados em relação a x: d/dx(x³ + y³) = d/dx(6xy).",
                "Passo 2: 3x² + 3y²y' = 6y + 6xy'.",
                "Passo 3: Divida toda a equação por 3 para simplificar: x² + y²y' = 2y + 2xy'.",
                "Passo 4: Agrupe os termos com y' à esquerda: y²y' - 2xy' = 2y - x² => y'(y² - 2x) = 2y - x².",
                "Passo 5: Isole y': y' = (2y - x²) / (y² - 2x)."
            ),
            pedagogicalAlert = "Sempre que derivar um termo em y com respeito a x, multiplique por y' (regra da cadeia).",
            keyMethod = "3x² + 3y²y' = 6y + 6xy' => y' = (2y - x²)/(y² - 2x)"
        ),

        CalculusExerciseItem(
            id = "30654",
            topicCode = "06",
            topicTitle = "Regra da Cadeia e Derivada Implícita",
            difficulty = "Difícil",
            source = "Questão Inédita",
            problemStatement = """Determine as coordenadas dos pontos sobre a lemniscata de Bernoulli \[ 2(x^2 + y^2)^2 = 25(x^2 - y^2) \] nos quais a reta tangente é perfeitamente horizontal (\(y' = 0\)).""",
            options = listOf(
                """4 pontos: \left(\pm \frac{5\sqrt{3}}{4}, \pm \frac{5}{4}\right)""",
                """2 pontos: (5, 0) \text{ e } (-5, 0)""",
                """4 pontos: (\pm 2, \pm 1)""",
                """Apenas a origem (0, 0)""",
                """Nenhum ponto possui tangente horizontal"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Derive implicitamente: 4(x² + y²)(2x + 2yy') = 25(2x - 2yy').",
                "Passo 2: 8(x² + y²)(x + yy') = 50x - 50yy'.",
                "Passo 3: Imponha a condição de tangente horizontal y' = 0: 8(x² + y²)(x) = 50x => x[8(x² + y²) - 50] = 0.",
                "Passo 4: x = 0 gera a origem (0,0), ponto singular nodal. A condição não trivial é 8(x² + y²) = 50 => x² + y² = 25/4.",
                "Passo 5: Substitua x² + y² = 25/4 na curva original: 2(25/4)² = 25(x² - y²) => x² - y² = 25/8.",
                "Passo 6: Resolvendo o sistema com x² + y² = 50/8 e x² - y² = 25/8: 2x² = 75/8 => x = ± 5sqrt(3)/4; 2y² = 25/8 => y = ± 5/4."
            ),
            pedagogicalAlert = "Tangente horizontal ocorre onde y' = 0 (numerador da derivada nulo com denominador não nulo).",
            keyMethod = "y' = 0 e sistema x² + y² = 25/4, x² - y² = 25/8 => (±5sqrt(3)/4, ±5/4)"
        ),

        // ========================================================
        // MÓDULO IV: APLICAÇÕES, TAXAS, L'HÔPITAL E OTIMIZAÇÃO (4xxxx)
        // ========================================================
        CalculusExerciseItem(
            id = "20701",
            topicCode = "07",
            topicTitle = "Taxas Relacionadas",
            difficulty = "Mediano",
            source = "UFRJ (Lista 8)",
            problemStatement = """Um poste de iluminação possui \(6\text{ m}\) de altura. Um homem de \(2\text{ m}\) de altura afasta-se do poste a \(1,5\text{ m/s}\). Calcule a velocidade com que se move a ponta da sombra do homem.""",
            options = listOf(
                """1,50 m/s""",
                """2,25 m/s""",
                """3,00 m/s""",
                """0,75 m/s""",
                """4,50 m/s"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Seja x a distância do homem à base do poste e s a distância da ponta da sombra à base do poste.",
                "Passo 2: Por semelhança de triângulos retângulos: s / 6 = (s - x) / 2.",
                "Passo 3: Multiplicando cruzado: 2s = 6(s - x) => 2s = 6s - 6x => 4s = 6x => s = (3/2)x.",
                "Passo 4: Diferencie ambos os lados em relação ao tempo t: ds/dt = (3/2) dx/dt.",
                "Passo 5: Como o homem se afasta a dx/dt = 1,5 m/s: ds/dt = (3/2) * 1,5 = 2,25 m/s."
            ),
            pedagogicalAlert = "Diferencie a velocidade da ponta da sombra (ds/dt) da taxa de crescimento do comprimento da sombra d(s-x)/dt.",
            keyMethod = "Semelhança: s/6 = (s-x)/2 => s = 1,5x => ds/dt = 1,5 * 1,5 = 2,25 m/s"
        ),

        CalculusExerciseItem(
            id = "20802",
            topicCode = "08",
            topicTitle = "Teoremas Teóricos (Rolle, TVM, TVI)",
            difficulty = "Mediano",
            source = "UFRJ (Lista 10)",
            problemStatement = """Verifique se \(f(x) = x^2 - 4x + 1\) satisfaz as hipóteses do Teorema de Rolle no intervalo \([0, 4]\) e determine o valor de \(c \in (0, 4)\) garantido pelo teorema.""",
            options = listOf(
                """c = 0""",
                """c = 1""",
                """c = 2""",
                """c = 3""",
                """As hipóteses não são satisfeitas"""
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Hipótese 1: f é polinomial, logo é contínua no intervalo fechado [0, 4].",
                "Passo 2: Hipótese 2: f é derivável no intervalo aberto (0, 4), com f'(x) = 2x - 4.",
                "Passo 3: Hipótese 3: f(0) = 0² - 4(0) + 1 = 1 e f(4) = 4² - 4(4) + 1 = 1, logo f(0) = f(4) = 1.",
                "Passo 4: As 3 hipóteses são satisfeitas! Conclusão do teorema: existe c in (0, 4) tal que f'(c) = 0.",
                "Passo 5: 2c - 4 = 0 => 2c = 4 => c = 2."
            ),
            pedagogicalAlert = "O Teorema de Rolle é um caso particular do TVM onde f(a) = f(b), garantindo derivada nula em c.",
            keyMethod = "f(0) = f(4) = 1 e f'(c) = 2c - 4 = 0 => c = 2"
        ),

        CalculusExerciseItem(
            id = "20903",
            topicCode = "09",
            topicTitle = "Regra de L'Hôpital e Otimização",
            difficulty = "Mediano",
            source = "UFRJ (Lista 11)",
            problemStatement = """Calcule os limites aplicando a Regra de L'Hôpital:
(I) \(\lim_{x \to 0} \frac{\sin(4x)}{\tan(5x)}\)
(II) \(\lim_{x \to \infty} \frac{\ln x}{\sqrt{x}}\)""",
            options = listOf(
                """(I) 4/5;  (II) 0""",
                """(I) 5/4;  (II) \infty""",
                """(I) 1;  (II) 1/2""",
                """(I) 4/5;  (II) 1""",
                """(I) 0;  (II) 0"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Limite (I) resulta na indeterminação 0/0. Aplique L'Hôpital: derive em cima e embaixo separadamente.",
                "Passo 2: d/dx(sin 4x) = 4 cos(4x); d/dx(tan 5x) = 5 sec²(5x).",
                "Passo 3: lim_{x -> 0} [4 cos(4x)] / [5 sec²(5x)] = [4 cos 0] / [5 sec² 0] = 4/5.",
                "Passo 4: Limite (II) resulta na indeterminação \\infty/\\infty. Aplique L'Hôpital: d/dx(ln x) = 1/x; d/dx(sqrt(x)) = 1/(2 sqrt(x)).",
                "Passo 5: lim_{x -> \\infty} (1/x) / [1/(2 sqrt(x))] = lim 2 sqrt(x) / x = lim 2 / sqrt(x) = 0."
            ),
            pedagogicalAlert = "Na Regra de L'Hôpital, NUNCA aplique a regra do quociente! Derive o numerador e o denominador individualmente.",
            keyMethod = "L'Hôpital: (I) 4cos(4x)/5sec²(5x) -> 4/5; (II) 2/sqrt(x) -> 0"
        ),

        CalculusExerciseItem(
            id = "30904",
            topicCode = "09",
            topicTitle = "Regra de L'Hôpital e Otimização",
            difficulty = "Difícil",
            source = "UFRJ (Lista 12)",
            problemStatement = """Determine as dimensões de um retângulo com perímetro fixo de \(100\text{ m}\) que possui a área máxima possível.""",
            options = listOf(
                """10 m x 40 m, área 400 m²""",
                """20 m x 30 m, área 600 m²""",
                """25 m x 25 m, área 625 m²""",
                """15 m x 35 m, área 525 m²""",
                """50 m x 50 m, área 2500 m²"""
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Perímetro 2x + 2y = 100 => x + y = 50 => y = 50 - x.",
                "Passo 2: Função objetivo da área: A(x) = x * y = x(50 - x) = 50x - x².",
                "Passo 3: Derivada da área: A'(x) = 50 - 2x.",
                "Passo 4: Ponto crítico A'(x) = 0 => 50 - 2x = 0 => 2x = 50 => x = 25 m.",
                "Passo 5: y = 50 - 25 = 25 m. Teste da 2ª derivada: A''(x) = -2 < 0 (confirma máximo absoluto). Área máxima: 25 * 25 = 625 m²."
            ),
            pedagogicalAlert = "Entre todos os retângulos de mesmo perímetro, o quadrado é sempre aquele que encerra a área máxima.",
            keyMethod = "A(x) = x(50 - x), A'(x) = 50 - 2x = 0 => quadrado 25 m x 25 m, Área = 625 m²"
        ),

        CalculusExerciseItem(
            id = "30905",
            topicCode = "09",
            topicTitle = "Regra de L'Hôpital e Otimização",
            difficulty = "Difícil",
            source = "UFRJ (Lista 12)",
            problemStatement = """Um contêiner retangular sem tampa deve ter volume de \(10\text{ m}^3\). A base tem comprimento igual ao dobro da largura. O material da base custa \(\$10/\text{m}^2\) e das laterais \(\$6/\text{m}^2\). Determine as dimensões ótimas e o custo mínimo aproximado.""",
            options = listOf(
                """w \approx 1,65 m, x \approx 3,30 m, h \approx 1,84 m, Custo \approx \$163,54""",
                """w = 2,00 m, x = 4,00 m, h = 1,25 m, Custo = \$210,00""",
                """w = 1,00 m, x = 2,00 m, h = 5,00 m, Custo = \$180,00""",
                """w \approx 2,50 m, x \approx 5,00 m, h \approx 0,80 m, Custo \approx \$250,00""",
                """w = 1,50 m, x = 3,00 m, h = 2,22 m, Custo = \$195,50"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Variáveis: largura = w, comprimento = 2w, altura = h. Volume = (2w)(w)(h) = 2w²h = 10 => h = 5/w².",
                "Passo 2: Área da base = 2w²; Custo base = 10 * 2w² = 20w².",
                "Passo 3: Área lateral = 2(wh) + 2(2wh) = 6wh; Custo lateral = 6 * 6wh = 36wh = 36w(5/w²) = 180/w.",
                "Passo 4: Custo Total C(w) = 20w² + 180/w. Derivando: C'(w) = 40w - 180/w².",
                "Passo 5: C'(w) = 0 => 40w³ = 180 => w³ = 4,5 => w = (4,5)^(1/3) \\approx 1,651 m.",
                "Passo 6: x = 2w \\approx 3,30 m; h = 5/w² \\approx 1,84 m. Custo mínimo C \\approx 20(1,651)² + 180/1,651 \\approx \$163,54."
            ),
            pedagogicalAlert = "Como o contêiner não tem tampa, calcule o custo da base apenas uma vez!",
            keyMethod = "Custo C(w) = 20w² + 180/w => w = sqrt[3]{4,5} \\approx 1,65 m => Custo \\approx \$163,54"
        ),

        CalculusExerciseItem(
            id = "20751",
            topicCode = "07",
            topicTitle = "Taxas Relacionadas",
            difficulty = "Mediano",
            source = "Questão Inédita",
            problemStatement = """Um balão esférico está sendo inflado de modo que seu volume aumenta a uma taxa constante de \(100\text{ cm}^3/\text{s}\). Quão rápido o raio cresce quando \(r = 5\text{ cm}\)?""",
            options = listOf(
                """1/\pi \text{ cm/s}""",
                """2/\pi \text{ cm/s}""",
                """4\pi \text{ cm/s}""",
                """100 \text{ cm/s}""",
                """1/(2\pi) \text{ cm/s}"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Fórmula do volume da esfera: V = (4/3)\\pi r³.",
                "Passo 2: Diferencie em relação ao tempo t: dV/dt = 4\\pi r² (dr/dt).",
                "Passo 3: Substitua dV/dt = 100 cm³/s e r = 5 cm: 100 = 4\\pi (5)² (dr/dt).",
                "Passo 4: 100 = 4\\pi (25) (dr/dt) => 100 = 100\\pi (dr/dt).",
                "Passo 5: dr/dt = 100 / (100\\pi) = 1/\\pi cm/s."
            ),
            pedagogicalAlert = "Diferencie a equação geométrica ANTES de substituir o raio r = 5 cm.",
            keyMethod = "V = 4/3 \\pi r³ => dV/dt = 4\\pi r² dr/dt => dr/dt = 1/\\pi cm/s"
        ),

        CalculusExerciseItem(
            id = "20952",
            topicCode = "09",
            topicTitle = "Regra de L'Hôpital e Otimização",
            difficulty = "Mediano",
            source = "Questão Inédita",
            problemStatement = """Calcule aplicando sucessivamente a Regra de L'Hôpital: \[ \lim_{x \to 0} \frac{e^x - 1 - x}{x^2} \]""",
            options = listOf(
                """0""",
                """1/2""",
                """1""",
                """2""",
                """\infty"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Substituição direta: (e⁰ - 1 - 0)/0² = 0/0. Primeira aplicação de L'Hôpital.",
                "Passo 2: d/dx(eˣ - 1 - x) = eˣ - 1; d/dx(x²) = 2x.",
                "Passo 3: Novo limite: lim_{x -> 0} (eˣ - 1)/(2x). Substituição direta: (1 - 1)/0 = 0/0. Segunda aplicação de L'Hôpital.",
                "Passo 4: d/dx(eˣ - 1) = eˣ; d/dx(2x) = 2.",
                "Passo 5: lim_{x -> 0} eˣ / 2 = e⁰ / 2 = 1/2."
            ),
            pedagogicalAlert = "Você pode aplicar L'Hôpital sucessivas vezes desde que a forma resultante continue sendo estritamente 0/0 ou \\infty/\\infty.",
            keyMethod = "L'Hôpital duplo: lim (eˣ - 1)/(2x) => lim eˣ/2 = 1/2"
        ),

        CalculusExerciseItem(
            id = "30853",
            topicCode = "08",
            topicTitle = "Teoremas Teóricos (Rolle, TVM, TVI)",
            difficulty = "Difícil",
            source = "Questão Inédita",
            problemStatement = """Utilize o Teorema do Valor Médio (TVM) para demonstrar rigorosamente a validade da desigualdade \(|\cos a - \cos b| \le |a - b|\) para quaisquer números reais \(a\) e \(b\).""",
            options = listOf(
                """Decorre de que |f'(c)| = |-\sin c| \le 1 para todo c \in \mathbb{R}""",
                """Decorre da periodicidade 2\pi da função cosseno""",
                """Decorre da Regra de L'Hôpital aplicada em a e b""",
                """Decorre de que a função cosseno é estritamente crescente""",
                """A desigualdade só é válida se a = b"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Se a = b, |cos a - cos a| = 0 <= 0, válida.",
                "Passo 2: Sem perda de generalidade, assuma a < b. Seja f(x) = cos x em [a, b]. f é contínua em [a, b] e derivável em (a, b) com f'(x) = -sin x.",
                "Passo 3: Pelo TVM, existe c in (a, b) tal que (cos b - cos a)/(b - a) = f'(c) = -sin c.",
                "Passo 4: Tomando valor absoluto em ambos os lados: |cos b - cos a| / |b - a| = |-sin c| = |sin c|.",
                "Passo 5: Como |sin c| <= 1 para todo número real c: |cos a - cos b| / |a - b| <= 1 => |cos a - cos b| <= |a - b|."
            ),
            pedagogicalAlert = "O limitante superior da derivada (|f'(x)| <= M) limita a variação máxima da função pelo TVM: |f(b) - f(a)| <= M|b - a|.",
            keyMethod = "TVM em cos(x): |cos b - cos a|/|b - a| = |sin c| <= 1 => |cos a - cos b| <= |a - b|"
        ),

        CalculusExerciseItem(
            id = "30954",
            topicCode = "09",
            topicTitle = "Regra de L'Hôpital e Otimização",
            difficulty = "Difícil",
            source = "Questão Inédita",
            problemStatement = """Encontre dois números reais positivos cuja soma seja \(60\) e cujo produto do primeiro pelo quadrado do segundo seja o maior possível.""",
            options = listOf(
                """x = 30, y = 30 (Produto = 27.000)""",
                """x = 20, y = 40 (Produto = 32.000)""",
                """x = 40, y = 20 (Produto = 16.000)""",
                """x = 15, y = 45 (Produto = 30.375)""",
                """x = 10, y = 50 (Produto = 25.000)"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Variáveis: x > 0 e y > 0 com x + y = 60 => y = 60 - x. Como y > 0, x in (0, 60).",
                "Passo 2: Função objetivo: P(x) = x * y² = x(60 - x)² = x(3600 - 120x + x²) = 3600x - 120x² + x³.",
                "Passo 3: Primeira derivada: P'(x) = 3600 - 240x + 3x² = 3(x² - 80x + 1200).",
                "Passo 4: Pontos críticos: 3(x - 20)(x - 60) = 0 => x = 20 (pois 60 está fora do domínio aberto (0, 60)).",
                "Passo 5: Teste da 2ª derivada: P''(x) = -240 + 6x => P''(20) = -240 + 120 = -120 < 0 (confirma máximo absoluto).",
                "Passo 6: Valores: x = 20 e y = 60 - 20 = 40. Produto máximo P = 20 * 40² = 20 * 1600 = 32.000."
            ),
            pedagogicalAlert = "O número que está ao quadrado deve receber 2/3 da soma total (y = 40) e o de grau 1 recebe 1/3 (x = 20).",
            keyMethod = "P(x) = x(60 - x)², P'(x) = 3(x - 20)(x - 60) = 0 => x = 20, y = 40, P_max = 32.000"
        ),

        // ========================================================
        // QUESTÕES INÉDITAS DO BANCO SISTEMATIZADO (IDs 11101 a 43204)
        // ========================================================
        CalculusExerciseItem(
            id = "11101",
            topicCode = "01",
            topicTitle = "Funções, Domínio e Gráficos",
            difficulty = "Mediano",
            source = "Banco Sistematizado (Item 11101)",
            problemStatement = """Determine o domínio real da função racional e radical: \[ f(x) = \frac{\sqrt{x^2 - 16}}{\ln(x - 2)} + \frac{1}{x^2 - 25} \]""",
            options = listOf(
                """[4, +\infty)""",
                """[4, 5) \cup (5, +\infty)""",
                """(2, 5) \cup (5, +\infty)""",
                """[4, +\infty) \setminus \{3, 5\}""",
                """(-\infty, -4] \cup [4, +\infty)"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Restrição radical par: x² - 16 >= 0 => x <= -4 ou x >= 4.",
                "Passo 2: Restrição logarítmica: x - 2 > 0 => x > 2.",
                "Passo 3: Restrição do denominador logarítmico: ln(x - 2) ≠ 0 => x - 2 ≠ 1 => x ≠ 3.",
                "Passo 4: Restrição do denominador racional: x² - 25 ≠ 0 => x ≠ ±5.",
                "Passo 5: Intersecção: ([4, +\\infty)) com x ≠ 3 (já fora) e x ≠ 5 (dentro). Conclui-se D_f = [4, 5) U (5, +\\infty)."
            ),
            pedagogicalAlert = "Não esqueça de checar ln(x - 2) ≠ 0, que anula o denominador em x = 3!",
            keyMethod = "Intersecção quádrupla das restrições algébricas => D = [4, 5) U (5, +\\infty)"
        ),

        CalculusExerciseItem(
            id = "11202",
            topicCode = "01",
            topicTitle = "Funções, Domínio e Gráficos",
            difficulty = "Mediano",
            source = "Banco Sistematizado (Item 11202)",
            problemStatement = """Considere a função real \(g(x) = \sqrt{9 - \sqrt{x + 1}}\). Determine o seu domínio \(D_g\) e imagem \(\operatorname{Im}(g)\).""",
            options = listOf(
                """D_g = [-1, 80], \quad \operatorname{Im}(g) = [0, 3]""",
                """D_g = [0, 80], \quad \operatorname{Im}(g) = [0, 9]""",
                """D_g = [-1, 9], \quad \operatorname{Im}(g) = [0, 3]""",
                """D_g = [-1, +\infty), \quad \operatorname{Im}(g) = [0, +\infty)""",
                """D_g = [0, 3], \quad \operatorname{Im}(g) = [-1, 80]"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Radical interno: x + 1 >= 0 => x >= -1.",
                "Passo 2: Radical externo: 9 - sqrt(x + 1) >= 0 => sqrt(x + 1) <= 9 => x + 1 <= 81 => x <= 80.",
                "Passo 3: Domínio: D_g = [-1, 80].",
                "Passo 4: Imagem: Para x = -1, g(-1) = sqrt(9 - 0) = 3; Para x = 80, g(80) = sqrt(9 - 9) = 0.",
                "Passo 5: Sendo contínua, pelo TVI, a imagem cobre todo o intervalo [0, 3]."
            ),
            pedagogicalAlert = "A imagem é limitada superiormente por 3 quando o radical interno atinge o seu valor mínimo zero.",
            keyMethod = "Radicais aninhados: x >= -1 e x <= 80 => D = [-1, 80], Im = [0, 3]"
        ),

        CalculusExerciseItem(
            id = "13304",
            topicCode = "02",
            topicTitle = "Composição, Inversas e Simetria",
            difficulty = "Difícil",
            source = "Banco Sistematizado (Item 13304)",
            problemStatement = """Classifique quanto à paridade e assíntotas a função: \[ h(x) = x \cdot \frac{e^x - 1}{e^x + 1} \]""",
            options = listOf(
                """h é par; seu gráfico tem simetria no eixo y e h(x) \sim x para x \to +\infty""",
                """h é ímpar; seu gráfico tem simetria na origem""",
                """h é nem par nem ímpar; possui assíntota horizontal em y = 0""",
                """h é periódica de período 1""",
                """h é descontínua em x = 0"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Calcule h(-x) = (-x) * (e⁻ˣ - 1)/(e⁻ˣ + 1).",
                "Passo 2: Multiplique o numerador e o denominador por eˣ: (-x) * (1 - eˣ)/(1 + eˣ).",
                "Passo 3: Note que (1 - eˣ) = -(eˣ - 1). Logo (-x) * [-(eˣ - 1)]/(eˣ + 1) = x * (eˣ - 1)/(eˣ + 1) = h(x).",
                "Passo 4: Como h(-x) = h(x), a função é rigorosamente par com simetria axial no eixo y.",
                "Passo 5: Para x -> +\\infty, (eˣ - 1)/(eˣ + 1) -> 1, logo h(x) assintota a reta y = x."
            ),
            pedagogicalAlert = "O produto de um termo ímpar (x) por uma função ímpar ((eˣ-1)/(eˣ+1)) resulta sempre em uma função PAR!",
            keyMethod = "h(-x) = h(x) (par); simetria axial no eixo y e assíntota oblíqua y = x"
        ),

        CalculusExerciseItem(
            id = "21101",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Mediano",
            source = "Banco Sistematizado (Item 21101)",
            problemStatement = """Calcule sem utilizar a Regra de L'Hôpital, aplicando o conjugado cúbico: \[ \lim_{x \to 1} \frac{\sqrt[3]{3x + 5} - 2}{x^2 - 1} \]""",
            options = listOf(
                """1/4""",
                """1/8""",
                """1/12""",
                """1/16""",
                """3/8"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Substituição direta: (sqrt[3]{8} - 2)/(1 - 1) = 0/0.",
                "Passo 2: Conjugado cúbico: (a - b)(a² + ab + b²) = a³ - b³, com a = sqrt[3]{3x+5} e b = 2.",
                "Passo 3: Numerador: (3x + 5) - 8 = 3x - 3 = 3(x - 1).",
                "Passo 4: Denominador: (x² - 1) = (x - 1)(x + 1).",
                "Passo 5: Cancele (x - 1): 3 / [(x + 1)(a² + ab + b²)].",
                "Passo 6: Para x = 1: 3 / [(1 + 1)(4 + 4 + 4)] = 3 / [2 * 12] = 3 / 24 = 1/8."
            ),
            pedagogicalAlert = "Para raízes cúbicas use o conjugado a² + ab + b², NUNCA o conjugado quadrático a + b!",
            keyMethod = "Conjugado cúbico a³ - b³ => cancelamento de (x - 1) => 3 / (2 * 12) = 1/8"
        ),

        CalculusExerciseItem(
            id = "21202",
            topicCode = "03",
            topicTitle = "Limites Finitos e Continuidade",
            difficulty = "Fácil",
            source = "Banco Sistematizado (Item 21202)",
            problemStatement = """Demonstre pelo Teorema do Confronto (Sanduíche) o valor do limite: \[ \lim_{x \to 0} x^4 \cdot \cos\left(\frac{3}{x^2}\right) \]""",
            options = listOf(
                """1""",
                """0""",
                """\infty""",
                """-1""",
                """\text{Não existe por oscilação}"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A função cosseno é limitada: -1 <= cos(3/x²) <= 1 para todo x ≠ 0.",
                "Passo 2: Como x⁴ > 0 para todo x ≠ 0, multiplique a desigualdade: -x⁴ <= x⁴ cos(3/x²) <= x⁴.",
                "Passo 3: Limites das extremidades: lim_{x->0} (-x⁴) = 0 e lim_{x->0} (x⁴) = 0.",
                "Passo 4: Pelo Teorema do Confronto, a função imprensada converge obrigatoriamente para 0."
            ),
            pedagogicalAlert = "Não calcule o limite de cos(3/x²) isolado: ele não existe devido à frequência infinita de oscilação!",
            keyMethod = "Teorema do Confronto: -x⁴ <= f(x) <= x⁴ e extremidades tendem a 0 => L = 0"
        ),

        CalculusExerciseItem(
            id = "22103",
            topicCode = "04",
            topicTitle = "Limites no Infinito e Assíntotas",
            difficulty = "Mediano",
            source = "Banco Sistematizado (Item 22103)",
            problemStatement = """Calcule o limite no infinito com diferença de radicais: \[ \lim_{x \to -\infty} \left( \sqrt{4x^2 + 3x} + 2x \right) \]""",
            options = listOf(
                """-3/4""",
                """3/4""",
                """0""",
                """-\infty""",
                """3/2"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Para x -> -\\infty, temos a indeterminação \\infty - \\infty.",
                "Passo 2: Multiplique pelo conjugado: [(4x² + 3x) - 4x²] / [sqrt(4x² + 3x) - 2x] = 3x / [sqrt(4x² + 3x) - 2x].",
                "Passo 3: No radical, sqrt(4x² + 3x) = sqrt(x²(4 + 3/x)) = |x| sqrt(4 + 3/x) = -x sqrt(4 + 3/x).",
                "Passo 4: Denominador: -x sqrt(4 + 3/x) - 2x = -x [sqrt(4 + 3/x) + 2].",
                "Passo 5: Cancele x: 3 / -[sqrt(4 + 0) + 2] = 3 / -[2 + 2] = -3/4."
            ),
            pedagogicalAlert = "O fator x negativo transforma sqrt(x²) em -x, gerando o sinal negativo no denominador.",
            keyMethod = "Racionalização e extração de |x| = -x para x < 0 => L = -3/4"
        ),

        CalculusExerciseItem(
            id = "23305",
            topicCode = "08",
            topicTitle = "Teoremas Teóricos (Rolle, TVM, TVI)",
            difficulty = "Mediano",
            source = "Banco Sistematizado (Item 23305)",
            problemStatement = """Demonstre pelo Teorema do Valor Intermediário (TVI) que a equação \[ x^5 - 3x - 1 = 0 \] possui ao menos uma raiz real no intervalo aberto \((1, 2)\).""",
            options = listOf(
                """P(1) = -3 < 0 e P(2) = 25 > 0, contínua em [1, 2] => existe raiz c \in (1, 2)""",
                """A função é estritamente decrescente em [1, 2]""",
                """A derivada P'(x) se anula em x = 1""",
                """P(1) e P(2) possuem o mesmo sinal positivo""",
                """O TVI só se aplica a funções trigonométricas"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Defina P(x) = x⁵ - 3x - 1. P é polinomial, logo é contínua em todo o intervalo fechado [1, 2].",
                "Passo 2: Calcule P(1) = 1⁵ - 3(1) - 1 = -3 < 0.",
                "Passo 3: Calcule P(2) = 2⁵ - 3(2) - 1 = 32 - 6 - 1 = 25 > 0.",
                "Passo 4: Como P(1) < 0 < P(2) e P é contínua, pelo TVI existe pelo menos um c in (1, 2) tal que P(c) = 0."
            ),
            pedagogicalAlert = "Para aplicar o TVI na busca de raízes, garanta que a função é contínua e que P(a) e P(b) tenham sinais opostos (P(a)*P(b) < 0).",
            keyMethod = "TVI: P(1) = -3 < 0 e P(2) = 25 > 0 com continuidade em [1, 2] => raiz em (1, 2)"
        ),

        CalculusExerciseItem(
            id = "31101",
            topicCode = "05",
            topicTitle = "Derivadas e Reta Tangente",
            difficulty = "Mediano",
            source = "Banco Sistematizado (Item 31101)",
            problemStatement = """Calcule a derivada de \(f(x) = \frac{1}{2x + 1}\) em \(x = a\) (\(a \neq -1/2\)) utilizando exclusivamente a definição formal por limite do quociente de Newton: \[ f'(a) = \lim_{h \to 0} \frac{f(a+h) - f(a)}{h} \]""",
            options = listOf(
                """f'(a) = \frac{-2}{(2a + 1)^2}""",
                """f'(a) = \frac{2}{(2a + 1)^2}""",
                """f'(a) = \frac{-1}{(2a + 1)}""",
                """f'(a) = -\frac{1}{2}""",
                """f'(a) = \frac{-2}{2a + 1}"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Quociente de Newton: [1/(2(a+h)+1) - 1/(2a+1)] / h.",
                "Passo 2: MMC no numerador: [(2a+1) - (2a+2h+1)] / [(2a+2h+1)(2a+1)] = -2h / [(2a+2h+1)(2a+1)].",
                "Passo 3: Divida por h (com h ≠ 0): -2 / [(2a+2h+1)(2a+1)].",
                "Passo 4: Tome o limite quando h -> 0: -2 / [(2a+1)(2a+1)] = -2 / (2a + 1)²."
            ),
            pedagogicalAlert = "Ao expandir 2(a+h)+1, não esqueça de distribuir o fator 2 para o incremento: 2a + 2h + 1.",
            keyMethod = "Limite de Newton: MMC no numerador => cancelamento de h => f'(a) = -2/(2a+1)²"
        ),

        CalculusExerciseItem(
            id = "31202",
            topicCode = "05",
            topicTitle = "Derivadas e Reta Tangente",
            difficulty = "Difícil",
            source = "Banco Sistematizado (Item 31202)",
            problemStatement = """Determine as equações das retas tangentes à curva \(y = x^3 - 3x + 2\) que sejam paralelas à reta \(9x - y + 5 = 0\).""",
            options = listOf(
                """y = 9x - 14  e  y = 9x + 18""",
                """y = 9x + 5  e  y = 9x - 5""",
                """y = 9x - 10  e  y = 9x + 10""",
                """y = -x/9 + 4  e  y = -x/9 - 2""",
                """y = 9x  e  y = 9x - 8"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A reta de referência é y = 9x + 5, com inclinação m = 9.",
                "Passo 2: Igualando a derivada a 9: y' = 3x² - 3 = 9 => 3x² = 12 => x² = 4 => x = ±2.",
                "Passo 3: Ponto 1 (x = 2): y(2) = 2³ - 3(2) + 2 = 4 => P₁(2, 4). Reta: y - 4 = 9(x - 2) => y = 9x - 14.",
                "Passo 4: Ponto 2 (x = -2): y(-2) = (-2)³ - 3(-2) + 2 = 0 => P₂(-2, 0). Reta: y - 0 = 9(x + 2) => y = 9x + 18."
            ),
            pedagogicalAlert = "Substitua x na função original para achar a coordenada y do ponto, e NÃO na derivada!",
            keyMethod = "y' = 3x² - 3 = 9 => x = ±2 => P₁(2, 4) e P₂(-2, 0) => y = 9x - 14 e y = 9x + 18"
        ),

        CalculusExerciseItem(
            id = "32203",
            topicCode = "06",
            topicTitle = "Regra da Cadeia e Derivada Implícita",
            difficulty = "Difícil",
            source = "Banco Sistematizado (Item 32203)",
            problemStatement = """Calcule a derivada da função composta \(y = \ln\left( \cos^2(3x) + e^{-\tan(x)} \right)\).""",
            options = listOf(
                """y' = \frac{-3\sin(6x) - \sec^2(x)e^{-\tan(x)}}{\cos^2(3x) + e^{-\tan(x)}}""",
                """y' = \frac{-6\cos(3x) - \sec^2(x)}{\cos^2(3x) + e^{-\tan(x)}}""",
                """y' = \frac{-3\sin(3x) + e^{-\tan(x)}}{\cos^2(3x) + e^{-\tan(x)}}""",
                """y' = \frac{1}{\cos^2(3x) + e^{-\tan(x)}}""",
                """y' = -3\sin(6x) - \sec^2(x)e^{-\tan(x)}"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Pela regra da cadeia para ln(u): y' = u' / u, onde u = cos²(3x) + e^(-tan x).",
                "Passo 2: Derivada de cos²(3x): 2 cos(3x) * (-sin 3x) * 3 = -6 sin(3x)cos(3x) = -3 sin(6x).",
                "Passo 3: Derivada de e^(-tan x): e^(-tan x) * (-sec² x) = -sec²(x) e^(-tan x).",
                "Passo 4: Montagem final: u' = -3 sin(6x) - sec²(x) e^(-tan x).",
                "Resultado: y' = [-3 sin(6x) - sec²(x) e^(-tan x)] / [cos²(3x) + e^(-tan x)]."
            ),
            pedagogicalAlert = "Não esqueça da derivada do argumento interno 3x e do sinal negativo vindo de d/dx(-tan x).",
            keyMethod = "Regra da cadeia mista: y' = u'/u com 2cos(3x)(-sin 3x)*3 = -3sin(6x)"
        ),

        CalculusExerciseItem(
            id = "32304",
            topicCode = "06",
            topicTitle = "Regra da Cadeia e Derivada Implícita",
            difficulty = "Difícil",
            source = "Banco Sistematizado (Item 32304)",
            problemStatement = """Para a curva Folium de Descartes \(x^3 + y^3 = 6xy\), determine o valor numérico exato da segunda derivada \(\frac{d^2y}{dx^2}\) no ponto \((3, 3)\).""",
            options = listOf(
                """y''(3, 3) = -16/3""",
                """y''(3, 3) = -1""",
                """y''(3, 3) = 0""",
                """y''(3, 3) = 16/3""",
                """y''(3, 3) = -8/9"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Primeira diferenciação implícita: x² + y²y' = 2y + 2xy'.",
                "Passo 2: No ponto (3, 3): 3² + 3²y' = 2(3) + 2(3)y' => 9 + 9y' = 6 + 6y' => 3y' = -3 => y' = -1.",
                "Passo 3: Diferencie a equação x² + y²y' = 2y + 2xy' novamente em relação a x: 2x + (2y(y')² + y²y'') = 2y' + (2y' + 2xy'').",
                "Passo 4: 2x + 2y(y')² + y²y'' = 4y' + 2xy''.",
                "Passo 5: Substitua x = 3, y = 3 e y' = -1: 2(3) + 2(3)(-1)² + 3²y'' = 4(-1) + 2(3)y''.",
                "Passo 6: 6 + 6 + 9y'' = -4 + 6y'' => 12 + 9y'' = -4 + 6y'' => 3y'' = -16 => y'' = -16/3."
            ),
            pedagogicalAlert = "Atenção ao derivar y²y': pela regra do produto e da cadeia temos d/dx(y²y') = 2y(y')*y' + y²(y'') = 2y(y')² + y²y''.",
            keyMethod = "Segunda derivação implícita: 2x + 2y(y')² + y²y'' = 4y' + 2xy'' => y''(3,3) = -16/3"
        ),

        CalculusExerciseItem(
            id = "41201",
            topicCode = "07",
            topicTitle = "Taxas Relacionadas",
            difficulty = "Difícil",
            source = "Banco Sistematizado (Item 41201)",
            problemStatement = """Um reservatório em cone invertido tem raio superior \(4\text{ m}\) e altura \(12\text{ m}\). A água entra a \(3\text{ m}^3/\text{min}\). Quão rápido o nível da água sobe quando a profundidade é \(h = 6\text{ m}\)?""",
            options = listOf(
                """\frac{3}{4\pi} \text{ m/min}""",
                """\frac{1}{4\pi} \text{ m/min}""",
                """\frac{3}{\pi} \text{ m/min}""",
                """\frac{9}{16\pi} \text{ m/min}""",
                """1 \text{ m/min}"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Semelhança de triângulos na seção transversal do cone: r / h = 4 / 12 => r = h / 3.",
                "Passo 2: Equação do volume em função apenas de h: V = (1/3)\\pi r² h = (1/3)\\pi (h/3)² h = (\\pi/27) h³.",
                "Passo 3: Diferencie em relação ao tempo t: dV/dt = (\\pi/9) h² (dh/dt).",
                "Passo 4: Substitua dV/dt = 3 m³/min e h = 6 m: 3 = (\\pi/9) (6)² (dh/dt) => 3 = (\\pi/9) (36) (dh/dt) => 3 = 4\\pi (dh/dt).",
                "Passo 5: dh/dt = 3 / (4\\pi) m/min."
            ),
            pedagogicalAlert = "Relacione as variáveis antes de derivar para não ficar com termos desconhecidos como dr/dt.",
            keyMethod = "r = h/3 => V = (\\pi/27)h³ => dV/dt = (\\pi/9)h² dh/dt => dh/dt = 3/(4\\pi) m/min"
        ),

        CalculusExerciseItem(
            id = "42202",
            topicCode = "08",
            topicTitle = "Teoremas Teóricos (Rolle, TVM, TVI)",
            difficulty = "Mediano",
            source = "Banco Sistematizado (Item 42202)",
            problemStatement = """Encontre o número \(c \in (0, 2)\) garantido pela conclusão do Teorema do Valor Médio para \(f(x) = x - x^3\) no intervalo \([0, 2]\).""",
            options = listOf(
                """c = \frac{2\sqrt{3}}{3}""",
                """c = \pm \frac{2\sqrt{3}}{3}""",
                """c = 1""",
                """c = \sqrt{3}""",
                """c = 4/3"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Taxa média no intervalo [0, 2]: f(0) = 0, f(2) = 2 - 8 = -6. [f(2) - f(0)] / (2 - 0) = -6 / 2 = -3.",
                "Passo 2: Derivada: f'(x) = 1 - 3x².",
                "Passo 3: Pelo TVM, f'(c) = -3 => 1 - 3c² = -3 => 3c² = 4 => c² = 4/3 => c = ± 2sqrt(3)/3.",
                "Passo 4: Como c deve pertencer ao intervalo aberto (0, 2), descarta-se a raiz negativa. Logo c = 2sqrt(3)/3 \\approx 1,155."
            ),
            pedagogicalAlert = "Descarte a solução negativa pois o TVM exige estritamente c dentro do intervalo aberto (0, 2).",
            keyMethod = "Taxa média = -3 => 1 - 3c² = -3 => c = 2sqrt(3)/3 in (0, 2)"
        ),

        CalculusExerciseItem(
            id = "42303",
            topicCode = "09",
            topicTitle = "Regra de L'Hôpital e Otimização",
            difficulty = "Difícil",
            source = "Banco Sistematizado (Item 42303)",
            problemStatement = """Calcule o limite exponencial indeterminado do tipo \(1^\infty\): \[ \lim_{x \to 0^+} \left(1 + 2x\right)^{\frac{1}{\sin(3x)}} \]""",
            options = listOf(
                """e^{2/3} = \sqrt[3]{e^2}""",
                """e^{3/2}""",
                """e^2""",
                """1""",
                """2/3"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplique o logaritmo natural: ln y = [1 / sin(3x)] * ln(1 + 2x) = ln(1 + 2x) / sin(3x).",
                "Passo 2: Quando x -> 0⁺, temos a forma 0/0. Aplique a Regra de L'Hôpital.",
                "Passo 3: Derivada do numerador: 2 / (1 + 2x); Derivada do denominador: 3 cos(3x).",
                "Passo 4: Limite: [2 / (1 + 0)] / [3 cos 0] = 2/3.",
                "Passo 5: Reverta a transformação exponencial: y = e^(ln y) => lim y = e^(2/3) = sqrt[3]{e²}."
            ),
            pedagogicalAlert = "Não esqueça de aplicar a função exponencial e^L no final: 2/3 é o limite de ln(y), e não do y original!",
            keyMethod = "ln(y) = ln(1+2x)/sin(3x) => L'Hôpital dá 2/3 => L = e^(2/3) = sqrt[3]{e²}"
        ),

        CalculusExerciseItem(
            id = "43204",
            topicCode = "09",
            topicTitle = "Regra de L'Hôpital e Otimização",
            difficulty = "Difícil",
            source = "Banco Sistematizado (Item 43204)",
            problemStatement = """Dispõe-se de \(600\text{ m}\) de cerca para construir três currais retangulares adjacentes idênticos compartilhando divisórias internas. Determine as dimensões externas totais que maximizam a área total cercada.""",
            options = listOf(
                """Largura total W = 150 m, Profundidade D = 75 m (Área = 11.250 m²)""",
                """Largura total W = 200 m, Profundidade D = 50 m (Área = 10.000 m²)""",
                """Largura total W = 100 m, Profundidade D = 100 m (Área = 10.000 m²)""",
                """Largura total W = 120 m, Profundidade D = 90 m (Área = 10.800 m²)""",
                """Largura total W = 300 m, Profundidade D = 37,5 m (Área = 11.250 m²)"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Sejam 3 currais de largura x cada (largura total W = 3x) e profundidade y.",
                "Passo 2: Cerca total: 2 horizontais de comprimento 3x (6x) + 4 verticais de comprimento y (4y). 6x + 4y = 600 => 3x + 2y = 300 => y = 150 - 1,5x.",
                "Passo 3: Área total: A(x) = W * y = (3x)(150 - 1,5x) = 450x - 4,5x².",
                "Passo 4: Derivada: A'(x) = 450 - 9x = 0 => 9x = 450 => x = 50 m.",
                "Passo 5: Largura total W = 3(50) = 150 m; Profundidade y = 150 - 1,5(50) = 75 m. Área máxima: 150 * 75 = 11.250 m²."
            ),
            pedagogicalAlert = "Não esqueça das cercas divisórias internas compartilhadas: são 4 cercas de profundidade y ao todo!",
            keyMethod = "6x + 4y = 600 => A(x) = 450x - 4,5x² => x = 50 m, W = 150 m, D = 75 m"
        ),

        // ========================================================
        // MÓDULO V: INTEGRAIS E TFC (IDs 5xxxx)
        // ========================================================
        CalculusExerciseItem(
            id = "50101",
            topicCode = "10",
            topicTitle = "Integrais e Teorema Fundamental do Cálculo",
            difficulty = "Fácil",
            source = "Apostila de Integrais / Imediatas",
            problemStatement = """Calcule a integral indefinida aplicando a linearidade e as regras imediatas: \[ \int \left( 4x^3 - 3\cos(x) + \frac{5}{x} \right) dx \]""",
            options = listOf(
                """x^4 - 3\sin(x) + 5\ln|x| + C""",
                """12x^2 + 3\sin(x) - \frac{5}{x^2} + C""",
                """x^4 + 3\sin(x) + 5\ln|x| + C""",
                """\frac{4x^4}{3} - 3\cos(x) + 5x + C""",
                """x^4 - 3\sin(x) + \frac{5}{x^2} + C"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplique a linearidade: 4 ∫ x³ dx - 3 ∫ cos(x) dx + 5 ∫ (1/x) dx.",
                "Passo 2: Integral da potência: 4(x⁴/4) = x⁴.",
                "Passo 3: Integral trigonométrica: -3(sin x) = -3 sin(x).",
                "Passo 4: Integral de 1/x: 5 ln|x|.",
                "Resultado Final: x⁴ - 3 sin(x) + 5 ln|x| + C."
            ),
            pedagogicalAlert = "A integral de cos(x) é sin(x) positivo, enquanto a integral de sin(x) é que carrega o sinal negativo (-cos(x)).",
            keyMethod = "Linearidade termo a termo => x⁴ - 3sin(x) + 5ln|x| + C"
        ),

        CalculusExerciseItem(
            id = "50102",
            topicCode = "10",
            topicTitle = "Integrais e Teorema Fundamental do Cálculo",
            difficulty = "Fácil",
            source = "Apostila de Integrais / Imediatas",
            problemStatement = """Calcule a integral da função radical pela regra da potência: \[ \int \sqrt{x} \, dx \]""",
            options = listOf(
                """\frac{2}{3}x^{3/2} + C""",
                """\frac{1}{2\sqrt{x}} + C""",
                """\frac{3}{2}x^{3/2} + C""",
                """\frac{2}{3}\sqrt{x} + C""",
                """x^{3/2} + C"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Converta a raiz em potência com expoente fracionário: sqrt(x) = x^(1/2).",
                "Passo 2: Aplique a regra da potência ∫ xⁿ dx = xⁿ⁺¹ / (n + 1) + C.",
                "Passo 3: n + 1 = 1/2 + 1 = 3/2.",
                "Passo 4: x^(3/2) / (3/2) = (2/3) x^(3/2) + C."
            ),
            pedagogicalAlert = "Dividir por 3/2 equivale a multiplicar pela fração invertida 2/3.",
            keyMethod = "Regra da potência: x^(1/2 + 1) / (3/2) => (2/3) x^(3/2) + C"
        ),

        CalculusExerciseItem(
            id = "50201",
            topicCode = "10",
            topicTitle = "Integrais e Teorema Fundamental do Cálculo",
            difficulty = "Mediano",
            source = "Apostila de Integrais / Substituição",
            problemStatement = """Calcule pelo método da substituição (\(u\)-substituição): \[ \int 2x \cos(x^2) \, dx \]""",
            options = listOf(
                """\sin(x^2) + C""",
                """-\sin(x^2) + C""",
                """2x\sin(x^2) + C""",
                """\cos(x^2) + C""",
                """\frac{1}{2}\sin(x^2) + C"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Escolha a variável interna: u = x².",
                "Passo 2: Diferencie: du = 2x dx.",
                "Passo 3: Substitua na integral: ∫ cos(u) du.",
                "Passo 4: Integre em relação a u: sin(u) + C.",
                "Passo 5: Retorne à variável x: sin(x²) + C."
            ),
            pedagogicalAlert = "Ao fazer a u-substituição, certifique-se de substituir todos os termos em x e dx por u e du.",
            keyMethod = "u = x² => du = 2x dx => ∫ cos(u) du = sin(x²) + C"
        ),

        CalculusExerciseItem(
            id = "50202",
            topicCode = "10",
            topicTitle = "Integrais e Teorema Fundamental do Cálculo",
            difficulty = "Mediano",
            source = "Apostila de Integrais / Partes",
            problemStatement = """Calcule utilizando a fórmula de integração por partes \(\int u \, dv = uv - \int v \, du\): \[ \int x e^x \, dx \]""",
            options = listOf(
                """e^x(x - 1) + C""",
                """e^x(x + 1) + C""",
                """x^2 e^x + C""",
                """\frac{x^2}{2}e^x + C""",
                """e^x - x + C"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Pela regra de prioridade LIATE, escolha a função algébrica como u: u = x => du = dx.",
                "Passo 2: A parte restante é dv = eˣ dx => v = ∫ eˣ dx = eˣ.",
                "Passo 3: Aplique a fórmula: ∫ u dv = u*v - ∫ v du.",
                "Passo 4: x eˣ - ∫ eˣ dx = x eˣ - eˣ + C.",
                "Passo 5: Colocando eˣ em evidência: eˣ(x - 1) + C."
            ),
            pedagogicalAlert = "Lembre-se da ordem LIATE para escolher u: Logaritmos > Inversas > Algébricas > Trigonométricas > Exponenciais.",
            keyMethod = "Partes: u = x, dv = eˣ dx => x eˣ - eˣ + C = eˣ(x - 1) + C"
        ),

        CalculusExerciseItem(
            id = "50301",
            topicCode = "10",
            topicTitle = "Integrais e Teorema Fundamental do Cálculo",
            difficulty = "Difícil",
            source = "Apostila de Integrais / Frações Parciais",
            problemStatement = """Calcule utilizando a técnica de decomposição em frações parciais: \[ \int \frac{1}{x^2 - 1} \, dx \]""",
            options = listOf(
                """\frac{1}{2}\ln\left|\frac{x - 1}{x + 1}\right| + C""",
                """\ln|x^2 - 1| + C""",
                """\arctan(x) + C""",
                """\frac{1}{2}\ln\left|\frac{x + 1}{x - 1}\right| + C""",
                """\ln|x - 1| - \ln|x + 1| + C"""
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Fatore o denominador: x² - 1 = (x - 1)(x + 1).",
                "Passo 2: Decomponha em frações parciais: 1 / [(x - 1)(x + 1)] = A / (x - 1) + B / (x + 1).",
                "Passo 3: Igualando numeradores: 1 = A(x + 1) + B(x - 1). Para x = 1: A = 1/2. Para x = -1: B = -1/2.",
                "Passo 4: Integre: (1/2) ∫ 1/(x-1) dx - (1/2) ∫ 1/(x+1) dx = (1/2) ln|x - 1| - (1/2) ln|x + 1| + C.",
                "Passo 5: Propriedade dos logaritmos ln(A) - ln(B) = ln(A/B): (1/2) ln|(x - 1)/(x + 1)| + C."
            ),
            pedagogicalAlert = "Não confunda 1/(x² - 1) (que se fatora e gera logaritmos) com 1/(x² + 1) (que gera arco-tangente arctan(x)).",
            keyMethod = "Frações parciais: 1/2 ln|x-1| - 1/2 ln|x+1| + C = 1/2 ln|(x-1)/(x+1)| + C"
        ),

        CalculusExerciseItem(
            id = "50401",
            topicCode = "10",
            topicTitle = "Integrais e Teorema Fundamental do Cálculo",
            difficulty = "Mediano",
            source = "Apostila de Integrais / Integral Definida",
            problemStatement = """Calcule o valor numérico da integral definida aplicando o Teorema Fundamental do Cálculo: \[ \int_{0}^{2} 3x^2 \, dx \]""",
            options = listOf(
                """6""",
                """8""",
                """12""",
                """16""",
                """24"""
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Encontre a primitiva F(x): F(x) = ∫ 3x² dx = 3(x³/3) = x³.",
                "Passo 2: Aplique o Teorema Fundamental do Cálculo: [F(b) - F(a)].",
                "Passo 3: Limite superior b = 2: F(2) = 2³ = 8.",
                "Passo 4: Limite inferior a = 0: F(0) = 0³ = 0.",
                "Passo 5: F(2) - F(0) = 8 - 0 = 8. (Representa a área sob a curva y = 3x² de x = 0 a x = 2)."
            ),
            pedagogicalAlert = "Na integral definida, a constante + C não é necessária pois (F(b) + C) - (F(a) + C) = F(b) - F(a).",
            keyMethod = "TFC: [x³] de 0 a 2 = 2³ - 0³ = 8"
        )
    )

    fun findById(id: String): CalculusExerciseItem? = allExercises.find { it.id == id }

    fun filter(topicCode: String? = null, difficulty: String? = null, query: String? = null): List<CalculusExerciseItem> {
        return allExercises.filter { item ->
            val matchTopic = topicCode.isNullOrBlank() || topicCode == "all" || item.topicCode == topicCode
            val matchDiff = difficulty.isNullOrBlank() || difficulty == "all" || item.difficulty.equals(difficulty, ignoreCase = true)
            val matchQuery = query.isNullOrBlank() ||
                item.id.contains(query, ignoreCase = true) ||
                item.topicTitle.contains(query, ignoreCase = true) ||
                item.problemStatement.contains(query, ignoreCase = true) ||
                item.source.contains(query, ignoreCase = true)
            matchTopic && matchDiff && matchQuery
        }
    }
}
