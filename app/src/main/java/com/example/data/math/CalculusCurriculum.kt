package com.example.data.math

import com.example.R
import com.example.data.model.DifficultyTier
import com.example.data.model.KahootModule
import com.example.data.model.KahootQuestion
import com.example.data.model.LearningUnit
import com.example.data.model.Lesson
import com.example.data.model.LessonType
import com.example.data.model.MathExercise

/**
 * Currículo completo de Cálculo I com ênfase visual, analítica e intuitiva.
 * Módulo 1: Limites e Continuidade (10 aulas individuais completas)
 * Módulo 2: Derivadas e Aplicações (8 aulas completas de P1 e P2 com gráficos em alta resolução)
 */
object CalculusCurriculum {

    // ========================================================
    // MÓDULO 1: LIMITES E CONTINUIDADE (10 Aulas Individuais)
    // ========================================================

    val limitsUnit = LearningUnit(
        id = "calc_u_limits",
        unitNumber = 1,
        tier = DifficultyTier.BASIC,
        title = "Limites e Continuidade",
        description = "Aproximação em vizinhança, limites laterais, indeterminações 0/0, fatoração, continuidade, assíntotas e Teorema do Confronto.",
        category = "Matemática",
        subject = "Cálculo I",
        lessons = listOf(
            Lesson(
                id = "calc_l1",
                unitId = "calc_u_limits",
                title = "Aula 1: O que é o Limite? (A Ideia Básica)",
                conceptTitle = "Aproximação na Vizinhança: lim_{x->a} f(x) = L",
                explanation = """
                    Imagine que você está se aproximando de uma pessoa numa festa. Você chega super perto, fala no ouvido dela, mas NUNCA encosta nela.
                    
                    Em matemática, o limite de f(x) quando x tende a 'a' estuda PARA ONDE a função está apontando à medida que x chega cada vez mais perto de 'a'.
                    
                    • A Notação:
                      lim_{x -> a} f(x) = L
                    
                    • A Regra de Ouro:
                      Não importa o que acontece exatamente no ponto x = a (a função pode até não existir ali ou ter um buraco). O que importa é apenas a tendência dos vizinhos bem pertinho do ponto 'a'!
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "O limite investiga a vizinhança imediata do ponto, não o ponto em si.",
                    "Mesmo que f(a) seja indefinido, o limite lim_{x->a} f(x) pode existir perfeitamente."
                ),
                solutionExplanation = "O limite analisa a trajetória nas imediações do ponto, e não o valor estático de f(a).",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l1_1",
                        title = "Tendência de Vizinhança e Cancelamento",
                        problemStatement = """Considere a função racional \[ f(x) = \\frac{x^2 - 1}{x - 1} \] Ao calcular o limite quando x tende a 1, a substituição direta resulta na indeterminação 0/0. Analisando a vizinhança perfurada (x ≠ 1), para qual valor real converge o limite \[ \\lim_{x \\to 1} f(x) \] ?""",
                        options = listOf(
                            "f(x) converge para 2",
                            "f(x) converge para 0",
                            "f(x) converge para 1",
                            "f(x) diverge para +∞",
                            "O limite não existe devido à indeterminação"
                        ),
                        correctIndex = 0,
                        hint = "Fatore o numerador pela diferença de dois quadrados: (x - 1)(x + 1) e simplifique para x ≠ 1.",
                        stepByStepSolution = """
                            Passo 1: Identificação da forma indeterminada. Ao substituir x = 1 diretamente, encontramos (1² - 1)/(1 - 1) = 0/0.
                            Passo 2: Fatoração algébrica do numerador pela diferença de quadrados:
                            \[ x^2 - 1 = (x - 1)(x + 1) \]
                            Passo 3: Cancelamento do fator nulo para toda a vizinhança perfurada x ≠ 1:
                            \[ f(x) = \frac{(x - 1)(x + 1)}{x - 1} = x + 1 \]
                            Passo 4: Cálculo do limite por substituição na expressão simplificada:
                            \[ \lim_{x \to 1} (x + 1) = 1 + 1 = 2 \]
                            Conclusão: Embora f(1) seja indefinido (um ponto aberto no gráfico), o limite bilateral existe e converge para 2.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l1_2",
                        title = "Existência do Limite vs Valor Pontual",
                        problemStatement = """Seja g(x) uma função com um ponto perfurado em x = 3, de modo que g(3) não existe no domínio. Com base nos fundamentos do Cálculo, o que podemos afirmar categoricamente sobre \[ \\lim_{x \\to 3} g(x) \] ?""",
                        options = listOf(
                            "O limite obrigatoriamente não existe porque g(3) é indefinido",
                            "O limite pode existir normalmente e ser um número real bem definido",
                            "O limite deve ser necessariamente igual a 0",
                            "O limite diverge obrigatoriamente para +∞ ou -∞",
                            "O limite só existe se a derivada g'(3) também existir"
                        ),
                        correctIndex = 1,
                        hint = "A definição formal de limite utiliza a vizinhança perfurada 0 < |x - a| < δ, sem depender do que ocorre exatamente no ponto x = a.",
                        stepByStepSolution = """
                            Passo 1: Definição formal de limite de Cauchy. O limite investiga o comportamento dos valores de g(x) na vizinhança perfurada 0 < |x - 3| < δ.
                            Passo 2: Análise da condição x ≠ 3. O valor estático pontual g(3) não participa da definição nem da existência do limite.
                            Passo 3: Distinção conceitual entre continuidade e existência de limite. A continuidade exige que lim g(x) = g(3), mas a existência do limite requer apenas a convergência na vizinhança.
                            Conclusão: O limite pode existir perfeitamente e ter valor real finito, independentemente da existência de g(3).
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l2",
                unitId = "calc_u_limits",
                title = "Aula 2: Limites Laterais e Existência",
                conceptTitle = "O Teste Decisivo do 'Vem por Onde?'",
                explanation = """
                    Para saber o limite em um ponto 'a', você precisa testar a aproximação por dois lados:
                    
                    • Pela esquerda (x -> a⁻): valores menores que a.
                    • Pela direita (x -> a⁺): valores maiores que a.
                    
                    Teorema da Existência (O Teste Decisivo):
                    O limite bilateral lim_{x->a} f(x) SÓ EXISTE se a aproximação pela esquerda for EXATAMENTE IGUAL à da direita:
                    lim_{x->a} f(x) = L  <=>  lim_{x->a⁻} f(x) = lim_{x->a⁺} f(x) = L
                    
                    Olhe o gráfico abaixo:
                    Quando x vem pela esquerda de 2, a função vai para 3.
                    Quando vem pela direita de 2, a função vai para 1.
                    Como 3 != 1, há um salto (pulo) no gráfico e o limite NÃO EXISTE em x = 2!
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "Se os limites laterais forem diferentes, o limite global NÃO existe.",
                    "Saltos no gráfico quebram a concordância bilateral."
                ),
                solutionExplanation = "O salto quebra a existência do limite bilateral.",
                xpReward = 35,
                imageResId = R.drawable.grafico_limites_laterais,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l2_1",
                        title = "Condição Fundamental de Existência",
                        problemStatement = """Considere que para uma função h(x), os limites laterais no ponto x = 4 foram avaliados como: \[ \\lim_{x \\to 4^-} h(x) = 7 \\quad \\text{e} \\quad \\lim_{x \\to 4^+} h(x) = 7 \] Pelo Teorema da Existência de Limites, qual é o valor do limite bilateral \[ \\lim_{x \\to 4} h(x) \] ?""",
                        options = listOf(
                            "O limite não existe sem saber o valor pontual de h(4)",
                            "O limite bilateral é a soma dos laterais: 14",
                            "O limite bilateral existe e é igual a 7",
                            "O limite bilateral é a média ponderada: 3.5",
                            "O limite é indeterminado da forma 7/0"
                        ),
                        correctIndex = 2,
                        hint = "Teorema Fundamental: lim_{x->a} h(x) = L se e somente se lim_{x->a⁻} h(x) = lim_{x->a⁺} h(x) = L.",
                        stepByStepSolution = """
                            Passo 1: Enunciado do Teorema da Existência. O limite bilateral lim_{x->a} h(x) = L existe se, e somente se, os dois limites laterais existirem e forem estritamente iguais a L.
                            Passo 2: Verificação das hipóteses do problema:
                            \[ \lim_{x \to 4^-} h(x) = 7 \quad \text{e} \quad \lim_{x \to 4^+} h(x) = 7 \]
                            Passo 3: Igualdade comprovada: 7 = 7 = L.
                            Conclusão: O limite bilateral lim_{x->4} h(x) existe e é exatamente igual a 7.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l2_2",
                        title = "Descontinuidade de Salto (Jump)",
                        problemStatement = """No estudo de uma função descontínua no ponto x = 2, constatou-se que \[ \\lim_{x \\to 2^-} f(x) = 3 \\quad \\text{e} \\quad \\lim_{x \\to 2^+} f(x) = 1 \] Qual a conclusão matemática correta sobre o comportamento de f(x) em x = 2?""",
                        options = listOf(
                            "O limite bilateral é a média aritmética: (3 + 1)/2 = 2",
                            "O limite bilateral vale 3, pois aproximações pela esquerda têm prioridade",
                            "O limite bilateral vale 1, pois aproximações pela direita definem a imagem",
                            "O limite bilateral não existe devido a uma descontinuidade de salto",
                            "O limite bilateral diverge para +∞"
                        ),
                        correctIndex = 3,
                        hint = "Observe que 3 ≠ 1. Quando as trajetórias vindas de lados opostos não convergem para a mesma altura, há uma quebra bilateral.",
                        stepByStepSolution = """
                            Passo 1: Comparação dos limites laterais: lim_{x->2⁻} f(x) = 3 e lim_{x->2⁺} f(x) = 1.
                            Passo 2: Aplicação da condição necessária de existência:
                            \[ \lim_{x \to 2^-} f(x) \neq \lim_{x \to 2^+} f(x) \implies (3 \neq 1) \]
                            Passo 3: Interpretação geométrica. O gráfico sofre uma quebra vertical abrupta com salto |3 - 1| = 2.
                            Conclusão: O limite bilateral não existe no ponto x = 2.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l3",
                unitId = "calc_u_limits",
                title = "Aula 3: Propriedades e Substituição Direta",
                conceptTitle = "As Regras do Jogo e Estratégia nº 1",
                explanation = """
                    Se os limites existirem e derem números finitos, você pode operar os limites como matemática normal:
                    
                    1. Soma / Subtração: O limite da soma é a soma dos limites.
                    2. Multiplicação: O limite do produto é o produto dos limites.
                    3. Divisão: O limite do quociente é o quociente dos limites (desde que o denominador não seja zero).
                    
                    Estratégia nº 1:
                    A primeira coisa que você faz em qualquer questão de limite é a SUBSTITUIÇÃO DIRETA (jogar o número na função).
                    Se der um número real normal sem divisão por zero, acabou a questão!
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "Substitua o valor diretamente antes de tentar métodos complexos.",
                    "Funções polinomiais são contínuas em todos os reais."
                ),
                solutionExplanation = "Substituição direta resolve limites de funções contínuas.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l3_1",
                        title = "Cálculo por Substituição Direta",
                        problemStatement = """Calcule o limite polinomial por substituição direta: \[ \\lim_{x \\to 3} (2x^2 - 3x + 5) \]""",
                        options = listOf(
                            "11",
                            "14",
                            "20",
                            "8",
                            "Indeterminado da forma 0/0"
                        ),
                        correctIndex = 1,
                        hint = "Substitua x = 3 em cada monômio: 2 · (3²) - 3 · (3) + 5.",
                        stepByStepSolution = """
                            Passo 1: Verificação da natureza da função. P(x) = 2x² - 3x + 5 é um polinômio de segundo grau, contínuo em toda a reta real ℝ.
                            Passo 2: Aplicação do Teorema da Substituição Direta:
                            \[ \lim_{x \to 3} P(x) = P(3) \]
                            Passo 3: Avaliação aritmética detalhada:
                            \[ P(3) = 2(3)^2 - 3(3) + 5 = 2(9) - 9 + 5 = 18 - 9 + 5 = 14 \]
                            Conclusão: O valor numérico é finito e bem determinado: lim_{x->3} (2x² - 3x + 5) = 14.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l3_2",
                        title = "Divisão com Denominador Não-Nulo",
                        problemStatement = """Calcule o limite racional aplicando as propriedades operatórias: \[ \\lim_{x \\to 2} \\frac{x^3 + 1}{x + 3} \]""",
                        options = listOf(
                            "9/5",
                            "8/5",
                            "7/5",
                            "3",
                            "Indeterminado"
                        ),
                        correctIndex = 0,
                        hint = "Verifique o denominador para x = 2: 2 + 3 = 5 ≠ 0. Não há indeterminação, aplique a substituição direta.",
                        stepByStepSolution = """
                            Passo 1: Verificação da condição de quociente. O limite do quociente é o quociente dos limites se o denominador for não-nulo:
                            \[ \lim_{x \to 2} \frac{f(x)}{g(x)} = \frac{\lim_{x \to 2} f(x)}{\lim_{x \to 2} g(x)} \quad \text{se } \lim_{x \to 2} g(x) \neq 0 \]
                            Passo 2: Cálculo individual do numerador e denominador:
                            \[ Numerador = 2^3 + 1 = 8 + 1 = 9 \]
                            \[ Denominador = 2 + 3 = 5 \]
                            Passo 3: Como 5 ≠ 0, a fração é diretamente avaliada: 9/5.
                            Conclusão: O limite existe e vale 9/5.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l4",
                unitId = "calc_u_limits",
                title = "Aula 4: Indeterminação 0/0 e o Furo Removível",
                conceptTitle = "A Ilusão do 0/0 Esconde um Buraco Geométrico",
                explanation = """
                    Quando você faz a substituição direta e encontra 0/0, isso NÃO significa erro ou zero!
                    Significa: 'Tem um fator (x - a) escondido em cima e embaixo atrapalhando o cálculo!'
                    
                    Olhe o gráfico abaixo:
                    A função f(x) = (x² - 4) / (x - 2) é uma reta perfeita y = x + 2, mas em x = 2 ela tem um FURO (bolinha aberta).
                    O valor f(2) não existe na álgebra estática, mas o LIMITE é perfeitamente igual a 4!
                    
                    Como destravar:
                    (x² - 4) / (x - 2) = [(x - 2)(x + 2)] / (x - 2) = x + 2.
                    Substituindo x = 2 em (x + 2), encontramos 4!
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "0/0 é uma indeterminação, não um resultado numérico.",
                    "Cancele o fator comum (x - a) que causa o zero no numerador e denominador."
                ),
                solutionExplanation = "O cancelamento remove o termo nulo.",
                xpReward = 35,
                imageResId = R.drawable.grafico_furo_indeterminacao,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l4_1",
                        title = "Destravando a Indeterminação 0/0",
                        problemStatement = """Avalie o limite racional que resulta na indeterminação 0/0: \[ \\lim_{x \\to 2} \\frac{x^2 - 4}{x - 2} \]""",
                        options = listOf(
                            "0",
                            "2",
                            "4",
                            "Indeterminado, portanto inexistente",
                            "+∞"
                        ),
                        correctIndex = 2,
                        hint = "Reconheça a diferença de dois quadrados: x² - 4 = (x - 2)(x + 2). Cancele o termo comum para x ≠ 2.",
                        stepByStepSolution = """
                            Passo 1: Identificação da indeterminação. Substituindo x = 2: (2² - 4)/(2 - 2) = 0/0.
                            Passo 2: Fatoração algébrica do numerador pela diferença de quadrados:
                            \[ x^2 - 4 = (x - 2)(x + 2) \]
                            Passo 3: Cancelamento do fator compartilhado para x ≠ 2:
                            \[ \frac{(x - 2)(x + 2)}{x - 2} = x + 2 \]
                            Passo 4: Cálculo do limite sobre a forma simplificada:
                            \[ \lim_{x \to 2} (x + 2) = 2 + 2 = 4 \]
                            Conclusão: O limite existe e converge para 4.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l4_2",
                        title = "Interpretação Gráfica do Furo Removível",
                        problemStatement = """Qual é o significado geométrico preciso de uma descontinuidade removível no gráfico da função f(x) = (x² - 4)/(x - 2)?""",
                        options = listOf(
                            "O gráfico é interrompido por uma assíntota vertical infinita em x = 2",
                            "O gráfico é a reta y = x + 2 com um furo pontual (ponto aberto) na coordenada (2, 4)",
                            "O gráfico sofre um salto vertical discreto de 4 unidades",
                            "A função oscila infinitamente ao redor do valor x = 2",
                            "A reta tangente em x = 2 torna-se estritamente horizontal"
                        ),
                        correctIndex = 1,
                        hint = "A função simplificada é y = x + 2 para todo x ≠ 2. O ponto (2, 4) é perfurado.",
                        stepByStepSolution = """
                            Passo 1: Análise algébrica. Para todo x ≠ 2, f(x) = x + 2. O traçado gráfico coincide com uma reta contínua de inclinação 1.
                            Passo 2: Análise pontual em x = 2. Como 2 não pertence ao domínio original, o ponto não é preenchido.
                            Passo 3: Análise do limite. Como lim_{x->2} f(x) = 4, a vizinhança aponta para a coordenada (2, 4).
                            Conclusão: O gráfico é exatamente a reta y = x + 2 com um ponto aberto (furo) em (2, 4).
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l5",
                unitId = "calc_u_limits",
                title = "Aula 5: Ferramentas Algébricas (Fatoração e Racionalização)",
                conceptTitle = "O Bisturi e o Espelho Conjugado",
                explanation = """
                    Duas técnicas clássicas para eliminar 0/0:
                    
                    1. Fatoração (O Bisturi):
                       lim_{x -> 3} (x² - 9) / (x - 3)
                       Fatorando o numerador: (x - 3)(x + 3) / (x - 3) = x + 3.
                       Substituindo x = 3: 3 + 3 = 6.
                    
                    2. Racionalização (O Espelho):
                       Quando houver raiz quadrada, multiplique numerador e denominador pelo CONJUGADO (trocando o sinal do meio).
                       (√x - 2) * (√x + 2) = x - 4.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "Diferença de quadrados: a² - b² = (a - b)(a + b).",
                    "Multiplicar pelo conjugado elimina a raiz: (√A - B)(√A + B) = A - B²."
                ),
                solutionExplanation = "Fatoração e conjugados eliminam indeterminações.",
                xpReward = 35,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l5_1",
                        title = "Racionalização de Raiz com Conjugado",
                        problemStatement = """Resolva o limite com radical pela técnica da multiplicação pelo conjugado: \[ \\lim_{x \\to 4} \\frac{\\sqrt{x} - 2}{x - 4} \]""",
                        options = listOf(
                            "1/2",
                            "1/4",
                            "1/8",
                            "0",
                            "4"
                        ),
                        correctIndex = 1,
                        hint = "Multiplique numerador e denominador pelo conjugado (√x + 2). Lembre que (√x - 2)(√x + 2) = x - 4.",
                        stepByStepSolution = """
                            Passo 1: Teste de substituição direta em x = 4: (√4 - 2)/(4 - 4) = 0/0.
                            Passo 2: Multiplicação pelo conjugado do numerador:
                            \[ \frac{(\sqrt{x} - 2)(\sqrt{x} + 2)}{(x - 4)(\sqrt{x} + 2)} = \frac{(\sqrt{x})^2 - 2^2}{(x - 4)(\sqrt{x} + 2)} = \frac{x - 4}{(x - 4)(\sqrt{x} + 2)} \]
                            Passo 3: Cancelamento do fator nulo (x - 4):
                            \[ = \frac{1}{\sqrt{x} + 2} \]
                            Passo 4: Cálculo do limite por substituição na expressão racionalizada:
                            \[ \lim_{x \to 4} \frac{1}{\sqrt{x} + 2} = \frac{1}{\sqrt{4} + 2} = \frac{1}{2 + 2} = \frac{1}{4} \]
                            Conclusão: O limite é 1/4.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l5_2",
                        title = "Fatoração Quadrática e Cancelamento",
                        problemStatement = """Calcule o limite com fatoração da diferença de quadrados: \[ \\lim_{x \\to 5} \\frac{x^2 - 25}{x - 5} \]""",
                        options = listOf(
                            "5",
                            "0",
                            "25",
                            "10",
                            "Indeterminado"
                        ),
                        correctIndex = 3,
                        hint = "Diferença de quadrados: a² - b² = (a - b)(a + b). Logo, x² - 25 = (x - 5)(x + 5).",
                        stepByStepSolution = """
                            Passo 1: Avaliação direta: (5² - 25)/(5 - 5) = 0/0 (indeterminação).
                            Passo 2: Fatoração algébrica do numerador: x² - 25 = (x - 5)(x + 5).
                            Passo 3: Cancelamento de (x - 5) na vizinhança perfurada:
                            \[ \frac{(x - 5)(x + 5)}{x - 5} = x + 5 \quad (\forall x \neq 5) \]
                            Passo 4: Aplicação do limite:
                            \[ \lim_{x \to 5} (x + 5) = 5 + 5 = 10 \]
                            Conclusão: O limite procurado vale 10.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l6",
                unitId = "calc_u_limits",
                title = "Aula 6: Continuidade e Matriz Diagnóstica",
                conceptTitle = "Desenhando Sem Tirar a Caneta do Papel",
                explanation = """
                    Uma função é contínua em x = a se você pode desenhá-la sem tirar a caneta do papel:
                    
                    A Regra dos 3 Passos da Continuidade:
                    1. f(a) precisa existir (o ponto existe).
                    2. lim_{x -> a} f(x) precisa existir (os dois lados concordam).
                    3. O limite e o valor no ponto têm que ser IGUAIS:
                       lim_{x -> a} f(x) = f(a)
                    
                    Matriz Diagnóstica de Descontinuidades:
                    • 1. Evitável (Removível): O limite existe, mas f(a) não existe ou está fora do lugar (furo).
                    • 2. Salto (Pulo): Os limites laterais são diferentes (lim⁻ != lim⁺).
                    • 3. Infinita: Ao menos um limite lateral tende a ±∞ (assíntota vertical).
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Para ser contínua, os 3 passos devem ser satisfeitos simultaneamente.",
                    "Se lim f(x) != f(a), a função falha no passo 3."
                ),
                solutionExplanation = "Continuidade exige igualdade entre o limite e o valor pontual.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l6_1",
                        title = "Os 3 Passos da Continuidade",
                        problemStatement = """Considere uma função f(x) onde o valor pontual é f(2) = 5, enquanto o limite bilateral converge para \[ \\lim_{x \\to 2} f(x) = 3 \] Com base no critério dos 3 passos, f(x) é contínua em x = 2?""",
                        options = listOf(
                            "Sim, porque o limite existe e possui valor finito",
                            "Não, porque a 3ª condição falhou: o limite (3) é diferente do valor da função f(2) = 5",
                            "Sim, desde que a função admita derivadas laterais",
                            "Não é possível determinar sem a fórmula analítica completa",
                            "Sim, a média ponderada (3 + 5)/2 restaura a continuidade"
                        ),
                        correctIndex = 1,
                        hint = "Critério dos 3 passos: 1) f(a) existe; 2) lim_{x->a} f(x) existe; 3) lim_{x->a} f(x) = f(a).",
                        stepByStepSolution = """
                            Passo 1: Verificação do Passo 1: f(2) = 5 (definido e finito).
                            Passo 2: Verificação do Passo 2: lim_{x->2} f(x) = 3 (o limite bilateral existe).
                            Passo 3: Verificação do Passo 3 (Concordância):
                            \[ 3 \neq 5 \implies \lim_{x \to 2} f(x) \neq f(2) \]
                            Conclusão: A terceira condição de continuidade falha. A função possui um ponto isolado destacado em (2, 5), sendo descontínua em x = 2.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l6_2",
                        title = "Identificação de Descontinuidade de Salto",
                        problemStatement = """Se no ponto x = 1 os limites laterais de uma função são \[ \\lim_{x \\to 1^-} f(x) = 4 \\quad \\text{e} \\quad \\lim_{x \\to 1^+} f(x) = -2 \] qual a classificação formal da descontinuidade em x = 1?""",
                        options = listOf(
                            "Descontinuidade de Salto (Jump) com amplitude 6",
                            "Descontinuidade Removível com furo em y = 4",
                            "Descontinuidade Infinita (Assintótica)",
                            "Ponto de Continuidade Ordinária",
                            "Descontinuidade Oscilatória Essencial"
                        ),
                        correctIndex = 0,
                        hint = "Limites laterais finitos e distintos geram um degrau ou salto de amplitude |L₂ - L₁|.",
                        stepByStepSolution = """
                            Passo 1: Avaliação dos limites laterais:
                            \[ \lim_{x \to 1^-} f(x) = 4 \quad \text{e} \quad \lim_{x \to 1^+} f(x) = -2 \]
                            Passo 2: Constatação de que ambos são números reais finitos, porém desiguais: 4 ≠ -2.
                            Passo 3: Cálculo da magnitude do salto vertical:
                            \[ \text{Salto} = |\lim_{x \to 1^+} f(x) - \lim_{x \to 1^-} f(x)| = |-2 - 4| = 6 \]
                            Conclusão: Trata-se de uma descontinuidade de salto de magnitude 6.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l7",
                unitId = "calc_u_limits",
                title = "Aula 7: Limites Infinitos e Assíntotas Verticais",
                conceptTitle = "O Abraço da Parede Vertical: 1/x e 1/x²",
                explanation = """
                    Quando o denominador vai a zero mas o numerador NÃO vai a zero (tipo 1/0), a função explode para o infinito!
                    
                    • Assíntota Vertical:
                      É a parede invisível x = a onde o gráfico dispara verticalmente.
                    
                    • 1. O Foguete Duplo: f(x) = 1/x²
                      Tanto pela esquerda quanto pela direita, o denominador ao quadrado é positivo. Ambos abraçam +∞!
                    
                    • 2. A Divisão Direcional: f(x) = 1/x
                      Pela esquerda (valores negativos) vai para -∞. Pela direita (valores positivos) vai para +∞.
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Constante não-nula dividida por zero indica assíntota vertical (±∞).",
                    "Analise o sinal do denominador ao se aproximar pela esquerda e pela direita."
                ),
                solutionExplanation = "Divisão por zero com numerador não-nulo gera assíntota vertical.",
                xpReward = 35,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l7_1",
                        title = "Comportamento Assintótico de 1/x²",
                        problemStatement = """Determine o comportamento assintótico e o valor do limite: \[ \\lim_{x \\to 0} \\frac{1}{x^2} \]""",
                        options = listOf(
                            "0",
                            "+∞",
                            "-∞",
                            "O limite não existe devido à inversão de sinais",
                            "Indeterminado"
                        ),
                        correctIndex = 1,
                        hint = "Para qualquer x ≠ 0, x² é estritamente positivo (x² > 0). Quando x -> 0, o denominador tende a zero pela direita (0⁺).",
                        stepByStepSolution = """
                            Passo 1: Análise pela esquerda (x -> 0⁻): x² > 0.
                            \[ \lim_{x \to 0^-} \frac{1}{x^2} = \frac{1}{(0^-)^2} = \frac{1}{0^+} = +\infty \]
                            Passo 2: Análise pela direita (x -> 0⁺): x² > 0.
                            \[ \lim_{x \to 0^+} \frac{1}{x^2} = \frac{1}{(0^+)^2} = \frac{1}{0^+} = +\infty \]
                            Passo 3: Como ambos os ramos laterais convergem para +∞ em uníssono:
                            \[ \lim_{x \to 0} \frac{1}{x^2} = +\infty \]
                            Conclusão: O limite diverge para +∞ e o eixo y (x = 0) é uma assíntota vertical da curva.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l7_2",
                        title = "Localização da Assíntota Vertical",
                        problemStatement = """Em qual reta cartesiana se localiza a assíntota vertical da função racional \[ f(x) = \\frac{5}{x - 3} \] ?""",
                        options = listOf(
                            "Na reta horizontal y = 5",
                            "Na reta horizontal y = 3",
                            "Na reta vertical x = 3",
                            "Na reta vertical x = -3",
                            "Na reta vertical x = 0"
                        ),
                        correctIndex = 2,
                        hint = "Assíntotas verticais ocorrem onde o denominador se anula (x - 3 = 0) e o numerador é uma constante não-nula.",
                        stepByStepSolution = """
                            Passo 1: Encontrar os pontos onde o denominador se anula: x - 3 = 0 => x = 3.
                            Passo 2: Verificar o numerador no ponto crítico: em x = 3, o numerador vale 5 ≠ 0.
                            Passo 3: Avaliar os limites laterais no ponto:
                            \[ \lim_{x \to 3^-} \frac{5}{x - 3} = \frac{5}{0^-} = -\infty \quad \text{e} \quad \lim_{x \to 3^+} \frac{5}{x - 3} = \frac{5}{0^+} = +\infty \]
                            Conclusão: A reta vertical x = 3 é a assíntota vertical da função.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l8",
                unitId = "calc_u_limits",
                title = "Aula 8: Limites no Infinito e Assíntotas Horizontais",
                conceptTitle = "A Busca pelo Horizonte Geométrico: lim_{x->∞} f(x) = L",
                explanation = """
                    Responde à pergunta: 'O que acontece lá no fim do universo quando x fica gigante?'
                    
                    • Assíntota Horizontal:
                      É a linha horizontal y = L onde o gráfico estabiliza no horizonte.
                    
                    • Teorema-Chave:
                      lim_{x -> ±∞} 1 / xʳ = 0 (para qualquer r > 0).
                      Pegar 1 valor fixo e dividir por uma multidão sem fim resulta em pó matemático (zero)!
                    
                    Olhe o gráfico abaixo da função f(x) = (2x + 1) / (x - 1):
                    • Assíntota Vertical em x = 1 (linha pontilhada laranja).
                    • Assíntota Horizontal em y = 2 (linha pontilhada ciano).
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "Para x -> ∞ em frações polinomiais, divida todos os termos pelo termo de maior grau.",
                    "Termos como c/x tendem a zero quando x tende ao infinito."
                ),
                solutionExplanation = "Os termos dominantes determinam a assíntota horizontal.",
                xpReward = 40,
                imageResId = R.drawable.grafico_assintotas,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l8_1",
                        title = "Assíntota Horizontal no Infinito",
                        problemStatement = """Calcule o limite no infinito para determinar a assíntota horizontal da curva: \[ \\lim_{x \\to \\infty} \\frac{2x + 1}{x - 1} \]""",
                        options = listOf(
                            "y = 1",
                            "y = 0",
                            "y = 2",
                            "y = -1",
                            "A curva não possui assíntotas horizontais"
                        ),
                        correctIndex = 2,
                        hint = "Coloque o termo de maior grau (x) em evidência no numerador e denominador.",
                        stepByStepSolution = """
                            Passo 1: Fatoração de x no numerador e denominador:
                            \[ \frac{2x + 1}{x - 1} = \frac{x(2 + \frac{1}{x})}{x(1 - \frac{1}{x})} = \frac{2 + \frac{1}{x}}{1 - \frac{1}{x}} \]
                            Passo 2: Aplicação do limite quando x -> ∞, sabendo que 1/x -> 0:
                            \[ \lim_{x \to \infty} \frac{2 + \frac{1}{x}}{1 - \frac{1}{x}} = \frac{2 + 0}{1 - 0} = \frac{2}{1} = 2 \]
                            Conclusão: O limite é 2, o que define a assíntota horizontal na reta y = 2.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l8_2",
                        title = "Grau do Denominador Maior que o Numerador",
                        problemStatement = """Determine o valor do limite quando o grau do denominador supera o do numerador: \[ \\lim_{x \\to \\infty} \\frac{3x + 4}{x^2 - 5} \]""",
                        options = listOf(
                            "3",
                            "+∞",
                            "4/5",
                            "0",
                            "Indeterminado"
                        ),
                        correctIndex = 3,
                        hint = "Divida todos os termos pela maior potência presente (x²). Termos do tipo c/x^k tendem a 0.",
                        stepByStepSolution = """
                            Passo 1: Divisão de todos os termos por x²:
                            \[ \frac{\frac{3x}{x^2} + \frac{4}{x^2}}{\frac{x^2}{x^2} - \frac{5}{x^2}} = \frac{\frac{3}{x} + \frac{4}{x^2}}{1 - \frac{5}{x^2}} \]
                            Passo 2: Avaliação dos limites infinitesimais:
                            \[ \lim_{x \to \infty} \frac{3}{x} = 0, \quad \lim_{x \to \infty} \frac{4}{x^2} = 0, \quad \lim_{x \to \infty} \frac{5}{x^2} = 0 \]
                            Passo 3: Substituição dos limites:
                            \[ \frac{0 + 0}{1 - 0} = \frac{0}{1} = 0 \]
                            Conclusão: O limite é 0 e a reta y = 0 (eixo x) é a assíntota horizontal da curva.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l9",
                unitId = "calc_u_limits",
                title = "Aula 9: Teorema do Sanduíche e Limite Trigonométrico",
                conceptTitle = "Domesticando Oscilações e lim_{x->0} sen(x)/x = 1",
                explanation = """
                    • Teorema do Sanduíche (ou do Confronto):
                      Se uma função g(x) está prensada no meio de duas funções f(x) <= g(x) <= h(x), e tanto a de cima quanto a de baixo vão para L, a do meio é FORÇADA a ir para L também!
                      Exemplo: x² * sen(1/x) prensada entre -x² e x² no zero vai para zero.
                    
                    • Limite Fundamental Trigonométrico:
                      lim_{x -> 0} sen(x) / x = 1
                      Guarde esse limite no coração, pois ele cai direto nas provas!
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "sen(x) está sempre limitado entre -1 e 1.",
                    "lim_{x->0} sen(kx)/x = k."
                ),
                solutionExplanation = "O Teorema do Confronto resolve funções oscilantes limitadas.",
                xpReward = 35,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l9_1",
                        title = "Limite Fundamental Trigonométrico com Ajuste Linear",
                        problemStatement = """Calcule o limite trigonométrico fundamental utilizando a mudança de variável linear: \[ \\lim_{x \\to 0} \\frac{\\text{sen}(5x)}{x} \]""",
                        options = listOf(
                            "1",
                            "0",
                            "5",
                            "1/5",
                            "Indeterminado"
                        ),
                        correctIndex = 2,
                        hint = "Multiplique e divida por 5 para formar o padrão sen(u)/u, com u = 5x.",
                        stepByStepSolution = """
                            Passo 1: Manipulação algébrica para evidenciar o limite trigonométrico fundamental:
                            \[ \frac{\text{sen}(5x)}{x} = 5 \cdot \frac{\text{sen}(5x)}{5x} \]
                            Passo 2: Definição de u = 5x. Quando x -> 0, u -> 0:
                            \[ \lim_{x \to 0} 5 \cdot \frac{\text{sen}(5x)}{5x} = 5 \cdot \lim_{u \to 0} \frac{\text{sen}(u)}{u} \]
                            Passo 3: Pelo Limite Fundamental de Euler, lim_{u->0} [sen(u)/u] = 1.
                            Passo 4: Multiplicação: 5 · 1 = 5.
                            Conclusão: O limite procurado vale 5.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l9_2",
                        title = "Teorema do Confronto (Sanduíche)",
                        problemStatement = """Aplique o Teorema do Confronto (Sanduíche) para resolver o limite com termo oscilante limitado: \[ \\lim_{x \\to 0} x^2 \\cos\\left(\\frac{1}{x}\\right) \]""",
                        options = listOf(
                            "1",
                            "0",
                            "Não existe devido à oscilação infinita",
                            "+∞",
                            "-1"
                        ),
                        correctIndex = 1,
                        hint = "O cosseno é limitado no intervalo [-1, 1]. Multiplique todos os membros por x².",
                        stepByStepSolution = """
                            Passo 1: Limitação da função cosseno para todo x ≠ 0:
                            \[ -1 \leq \cos\left(\frac{1}{x}\right) \leq 1 \]
                            Passo 2: Multiplicação por x² (que é estritamente positivo para x ≠ 0):
                            \[ -x^2 \leq x^2 \cos\left(\frac{1}{x}\right) \leq x^2 \]
                            Passo 3: Cálculo do limite das funções limitantes quando x -> 0:
                            \[ \lim_{x \to 0} (-x^2) = 0 \quad \text{e} \quad \lim_{x \to 0} (x^2) = 0 \]
                            Conclusão: Pelo Teorema do Confronto, a função imprensada converge compulsoriamente para 0.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l10",
                unitId = "calc_u_limits",
                title = "Aula 10: O Elo Fundamental (Da Secante à Tangente)",
                conceptTitle = "A Ponte para as Derivadas: f'(a) = lim_{h->0} [f(a+h) - f(a)] / h",
                explanation = """
                    Tudo no Cálculo se conecta aqui:
                    A DERIVADA nada mais é do que o limite da taxa de variação média quando o intervalo h encolhe até zero!
                    
                    • A Fórmula da Definição:
                      f'(a) = lim_{h -> 0} [f(a + h) - f(a)] / h
                    
                    Geometria:
                    Ao colapsar o intervalo físico 'h' entre os pontos até zero sob a lente do limite, convertemos a Reta Secante grosseira na perfeita e cobiçada Reta Tangente (Taxa Instantânea de Variação)!
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = "",
                hints = listOf(
                    "A inclinação da secante é Δy / Δx = [f(a+h) - f(a)] / h.",
                    "O limite quando h -> 0 transforma a reta secante em reta tangente."
                ),
                solutionExplanation = "A derivada é o limite da inclinação das retas secantes.",
                xpReward = 45,
                imageResId = R.drawable.grafico_reta_tangente,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l10_1",
                        title = "Derivada pela Definição de Limite",
                        problemStatement = """Utilizando a definição formal de derivada por limite \[ f'(a) = \\lim_{h \\to 0} \\frac{f(a+h) - f(a)}{h} \] calcule a derivada de f(x) = x² no ponto a = 3.""",
                        options = listOf(
                            "9",
                            "3",
                            "6",
                            "12",
                            "0"
                        ),
                        correctIndex = 2,
                        hint = "f(3+h) = (3+h)² = 9 + 6h + h². Subtraia f(3) = 9, divida por h e aplique o limite h -> 0.",
                        stepByStepSolution = """
                            Passo 1: Aplicação direta da definição formal para a = 3:
                            \[ f'(3) = \lim_{h \to 0} \frac{f(3+h) - f(3)}{h} = \lim_{h \to 0} \frac{(3+h)^2 - 3^2}{h} \]
                            Passo 2: Expansão do binômio ao quadrado:
                            \[ (3+h)^2 - 9 = (9 + 6h + h^2) - 9 = 6h + h^2 \]
                            Passo 3: Cancelamento de h no quociente de Newton para h ≠ 0:
                            \[ \frac{6h + h^2}{h} = \frac{h(6 + h)}{h} = 6 + h \]
                            Passo 4: Avaliação do limite quando h -> 0:
                            \[ \lim_{h \to 0} (6 + h) = 6 + 0 = 6 \]
                            Conclusão: A taxa instantânea de variação (derivada) f'(3) é exatamente 6.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l10_2",
                        title = "Interpretação Geométrica do Coeficiente Angular",
                        problemStatement = """Qual é a interpretação geométrica exata do valor da derivada primeira f'(a) calculada em um ponto da curva?""",
                        options = listOf(
                            "A área acumulada sob a curva desde a origem até o ponto a",
                            "A inclinação (coeficiente angular) da reta tangente à curva no ponto (a, f(a))",
                            "O comprimento de arco da curva no intervalo centrado em a",
                            "A curvatura elíptica da função",
                            "A coordenada vertical do ponto de máximo da curva"
                        ),
                        correctIndex = 1,
                        hint = "A derivada mede a taxa instantânea de inclinação, exatamente o coeficiente angular da reta tangente.",
                        stepByStepSolution = """
                            Passo 1: Reta Secante. Conecta dois pontos distintos (a, f(a)) e (a+h, f(a+h)), tendo inclinação:
                            \[ m_{\text{sec}} = \frac{\Delta y}{\Delta x} = \frac{f(a+h) - f(a)}{h} \]
                            Passo 2: Passagem ao limite. Quando h tende a 0, o segundo ponto desliza sobre a curva até coincidir com o primeiro.
                            Passo 3: Reta Tangente. A inclinação resultante é:
                            \[ m_{\text{tan}} = \lim_{h \to 0} m_{\text{sec}} = f'(a) \]
                            Conclusão: A derivada primeira f'(a) representa com rigor o coeficiente angular (inclinação) da reta tangente no ponto.
                        """.trimIndent()
                    )
                )
            )
        )
    )

    // ========================================================
    // MÓDULO 2: DERIVADAS E APLICAÇÕES (P1 e P2 - 8 Aulas)
    // ========================================================

    val derivativesUnit = LearningUnit(
        id = "calc_u_derivatives",
        unitNumber = 2,
        tier = DifficultyTier.BASIC,
        title = "Derivadas e Aplicações",
        description = "Taxa instantânea, regras do tombo, produto, quociente, cadeia, derivação implícita, aproximação linear, estudo de funções, L'Hôpital e otimização.",
        category = "Matemática",
        subject = "Cálculo I",
        lessons = listOf(
            Lesson(
                id = "calc_l11",
                unitId = "calc_u_derivatives",
                title = "Aula 1: O que é a Derivada (Na Prática)?",
                conceptTitle = "Taxa de Variação Instantânea e Reta Tangente",
                explanation = """
                    Se o limite estuda a aproximação, a DERIVADA mede a taxa de variação instantânea!
                    
                    • Na Física: Se a função é a posição s(t) do carro, a derivada s'(t) é a velocidade no velocímetro naquele exato segundo!
                    • Na Geometria: É o coeficiente angular (inclinação m) da reta tangente à curva no ponto.
                    
                    A Fórmula da Definição:
                    f'(x) = lim_{h -> 0} [f(x + h) - f(x)] / h
                    
                    Olhe o gráfico abaixo (grafico_reta_tangente):
                    À medida que a distância h entre dois pontos encolhe até zero, a reta secante (verde tracejada) vai girando até se ajustar perfeitamente como a reta tangente (vermelha) na curva no ponto P(1, 1).
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "A reta secante conecta 2 pontos; a reta tangente toca em apenas 1 ponto localmente.",
                    "O coeficiente angular m da reta tangente é exatamente o valor de f'(x)."
                ),
                solutionExplanation = "A reta tangente é o limite geométrico das retas secantes.",
                xpReward = 35,
                imageResId = R.drawable.grafico_reta_tangente,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l11_1",
                        title = "Taxa Instantânea vs Taxa Média",
                        problemStatement = """A trajetória de um corpo em queda livre é modelada por s(t) = t² metros. Determine a velocidade média entre t = 1s e t = 3s e compare com a velocidade instantânea em t = 1s:""",
                        options = listOf(
                            "Velocidade média = 2 m/s e instantânea = 4 m/s",
                            "Velocidade média = 4 m/s e instantânea = 2 m/s",
                            "Velocidade média = 3 m/s e instantânea = 3 m/s",
                            "Velocidade média = 8 m/s e instantânea = 4 m/s",
                            "Velocidade média = 4 m/s e instantânea = 1 m/s"
                        ),
                        correctIndex = 1,
                        hint = "Velocidade média = [s(3) - s(1)] / (3 - 1). Velocidade instantânea = s'(1) = 2(1).",
                        stepByStepSolution = """
                            Passo 1: Cálculo da velocidade média:
                            \[ v_{\text{média}} = \frac{\Delta s}{\Delta t} = \frac{s(3) - s(1)}{3 - 1} = \frac{3^2 - 1^2}{2} = \frac{9 - 1}{2} = \frac{8}{2} = 4\text{ m/s} \]
                            Passo 2: Cálculo da velocidade instantânea via derivada:
                            \[ s'(t) = \frac{d}{dt}(t^2) = 2t \]
                            Passo 3: Avaliação em t = 1:
                            \[ v_{\text{inst}}(1) = s'(1) = 2(1) = 2\text{ m/s} \]
                            Conclusão: A velocidade média no intervalo é 4 m/s e a velocidade instantânea em t = 1s é 2 m/s.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l11_2",
                        title = "Equação da Reta Tangente",
                        problemStatement = """Para a parábola f(x) = x² no ponto P(1, 1), sabemos que a derivada fornece a inclinação m = f'(1) = 2. Qual é a equação reduzida da reta tangente?""",
                        options = listOf(
                            "y = 2x + 1",
                            "y = 2x - 1",
                            "y = x + 1",
                            "y = 2x - 2",
                            "y = -2x + 3"
                        ),
                        correctIndex = 1,
                        hint = "Equação ponto-inclinação: y - y₀ = m(x - x₀), onde (x₀, y₀) = (1, 1) e m = 2.",
                        stepByStepSolution = """
                            Passo 1: Aplicação da equação da reta tangente na forma ponto-inclinação:
                            \[ y - y_0 = m(x - x_0) \implies y - 1 = 2(x - 1) \]
                            Passo 2: Distribuição do coeficiente angular:
                            \[ y - 1 = 2x - 2 \implies y = 2x - 2 + 1 \implies y = 2x - 1 \]
                            Conclusão: A equação reduzida da reta tangente é y = 2x - 1.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l12",
                unitId = "calc_u_derivatives",
                title = "Aula 2: As Regras Rápidas de Derivação",
                conceptTitle = "Sem Fazer Limite! Regra do Tombo e Funções Especiais",
                explanation = """
                    Ninguém calcula derivada por limite na prova toda. Usamos as regras práticas:
                    
                    1. Regra do Tombo (Potência):
                       O expoente 'cai' multiplicando na frente e você subtrai 1 do expoente original:
                       (xⁿ)' = n · xⁿ⁻¹
                       • Exemplos: (x³)' = 3x²  |  (x⁵)' = 5x⁴  |  (x)' = 1
                    
                    2. Derivada da Constante e Multiplicação por Constante:
                       • A derivada de um número sozinho é ZERO: (7)' = 0.
                       • Se o número estiver multiplicando o x, ele 'pega carona':
                         (4x³)' = 4 · (3x²) = 12x².
                    
                    3. Funções Especiais Famosas:
                       • Exponencial: (eˣ)' = eˣ (a única função do universo cuja derivada é ela mesma!)
                       • Logaritmo: (ln x)' = 1/x
                       • Trigonométricas:
                         (sen x)' = cos x
                         (cos x)' = -sen x
                         (tg x)' = sec² x
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "Derrube o expoente multiplicando e subtraia 1 da potência.",
                    "Derivada de constante pura sem variável é zero."
                ),
                solutionExplanation = "A regra da potência simplifica qualquer polinômio.",
                xpReward = 35,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l12_1",
                        title = "Regra do Tombo (Potência) com Polinômio",
                        problemStatement = """Calcule a derivada da função polinomial \[ f(x) = 5x^4 - 3x^2 + 7x - 12 \]""",
                        options = listOf(
                            "20x³ - 6x + 7",
                            "20x⁴ - 6x² + 7",
                            "5x³ - 3x + 7",
                            "20x³ - 6x",
                            "15x³ - 6x + 7"
                        ),
                        correctIndex = 0,
                        hint = "Aplique a regra do tombo (x^n)' = n·x^(n-1) termo a termo e lembre que a derivada de uma constante é zero.",
                        stepByStepSolution = """
                            Passo 1: Aplicação da Regra da Potência (d/dx[xⁿ] = n·xⁿ⁻¹) e linearidade da derivada termo a termo:
                            \[ \frac{d}{dx}(5x^4) = 5 \cdot (4x^3) = 20x^3 \]
                            Passo 2: Derivação do segundo termo:
                            \[ \frac{d}{dx}(-3x^2) = -3 \cdot (2x) = -6x \]
                            Passo 3: Derivação do termo linear:
                            \[ \frac{d}{dx}(7x) = 7 \cdot (1) = 7 \]
                            Passo 4: Derivação da constante pura:
                            \[ \frac{d}{dx}(-12) = 0 \]
                            Passo 5: Agrupamento final:
                            \[ f'(x) = 20x^3 - 6x + 7 \]
                            Conclusão: A derivada da função é 20x³ - 6x + 7.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l12_2",
                        title = "Derivadas de Funções Notáveis",
                        problemStatement = """Seja \[ f(x) = 3e^x + 4\\text{sen}(x) - 2\\ln(x) \] determine a expressão da derivada f'(x):""",
                        options = listOf(
                            "3eˣ - 4 cos(x) - 2/x",
                            "3xeˣ + 4 cos(x) - 2x",
                            "3eˣ + 4 sen(x) - 2/x",
                            "3eˣ + 4 cos(x) - 2/x",
                            "3eˣ + 4 cos(x) + 2/x"
                        ),
                        correctIndex = 3,
                        hint = "Tabela de derivadas imediatas: (eˣ)' = eˣ, (sen x)' = cos x e (ln x)' = 1/x.",
                        stepByStepSolution = """
                            Passo 1: Derivação da função exponencial natural:
                            \[ \frac{d}{dx}(3e^x) = 3 \cdot e^x = 3e^x \]
                            Passo 2: Derivação da função trigonométrica fundamental:
                            \[ \frac{d}{dx}(4\text{sen}(x)) = 4 \cdot \cos(x) = 4\cos(x) \]
                            Passo 3: Derivação da função logarítmica neperiana:
                            \[ \frac{d}{dx}(-2\ln(x)) = -2 \cdot \frac{1}{x} = -\frac{2}{x} \]
                            Passo 4: Combinação linear de todos os termos:
                            \[ f'(x) = 3e^x + 4\cos(x) - \frac{2}{x} \]
                            Conclusão: f'(x) = 3eˣ + 4 cos(x) - 2/x.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l13",
                unitId = "calc_u_derivatives",
                title = "Aula 3: As 3 Regras de Ouro para Operações",
                conceptTitle = "Regra do Produto, Quociente e da Cadeia",
                explanation = """
                    1. Regra do Produto (Multiplicação):
                       'Deriva o primeiro, copia o segundo MAIS copia o primeiro, deriva o segundo!'
                       (f · g)' = f' · g + f · g'
                       • Exemplo: y = x² · sen(x)
                         y' = (2x) · sen(x) + (x²) · cos(x)
                    
                    2. Regra do Quociente (Divisão):
                       'Deriva o de cima vezes o de baixo MENOS o de cima vezes a derivada do de baixo, TUDO SOBRE o de baixo ao quadrado!'
                       (f / g)' = (f' · g - f · g') / g²
                       • Exemplo: y = eˣ / x
                         y' = [(eˣ) · x - eˣ · 1] / x² = [eˣ(x - 1)] / x²
                    
                    3. Regra da Cadeia (Função Dentro de Função):
                       'Deriva a de FORA mantendo a de dentro intacta, e MULTIPLICA pela derivada da de DENTRO!'
                       [f(g(x))]' = f'(g(x)) · g'(x)
                       • Exemplo: y = sen(x³)
                         Fora: sen(u) => cos(u)
                         Dentro: x³ => 3x²
                         Resultado: y' = cos(x³) · 3x² = 3x² cos(x³)
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "Produto usa SOMA (+), Quociente usa SUBTRAÇÃO (-) e denominador ao quadrado.",
                    "Na Regra da Cadeia, NUNCA esqueça de multiplicar pela derivada do que está dentro!"
                ),
                solutionExplanation = "As 3 regras cobrem todas as composições e operações algébricas.",
                xpReward = 40,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l13_1",
                        title = "Regra do Produto de Funções",
                        problemStatement = """Utilize a Regra do Produto para derivar a função \[ f(x) = x^3 \\cdot e^x \]""",
                        options = listOf(
                            "3x² eˣ",
                            "x³ eˣ",
                            "x² eˣ (3 + x)",
                            "3x² + eˣ",
                            "3x³ eˣ"
                        ),
                        correctIndex = 2,
                        hint = "Regra do Produto: (u · v)' = u'·v + u·v'. Aqui u = x³ e v = eˣ.",
                        stepByStepSolution = """
                            Passo 1: Identificação dos fatores: u(x) = x³ e v(x) = eˣ.
                            Passo 2: Cálculo das derivadas individuais:
                            \[ u'(x) = 3x^2 \quad \text{e} \quad v'(x) = e^x \]
                            Passo 3: Aplicação da fórmula do produto (u'v + uv'):
                            \[ f'(x) = (3x^2) \cdot e^x + (x^3) \cdot e^x \]
                            Passo 4: Fatoração colocando o termo comum x² eˣ em evidência:
                            \[ f'(x) = e^x (3x^2 + x^3) = x^2 e^x (3 + x) \]
                            Conclusão: A derivada é x² eˣ (3 + x).
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l13_2",
                        title = "Regra da Cadeia para Funções Compostas",
                        problemStatement = """Derive a função composta \[ y = (2x^3 + 5)^4 \]""",
                        options = listOf(
                            "4 (2x³ + 5)³",
                            "24x² (2x³ + 5)³",
                            "24x (2x³ + 5)³",
                            "8x² (2x³ + 5)³",
                            "6x² (2x³ + 5)⁴"
                        ),
                        correctIndex = 1,
                        hint = "Regra da Cadeia: dy/dx = f'(u) · u'. Derive a externa 4u³ e multiplique pela derivada interna (2x³ + 5)' = 6x².",
                        stepByStepSolution = """
                            Passo 1: Identificação da função externa e interna:
                            \[ \text{Externa: } f(u) = u^4 \quad \implies \quad f'(u) = 4u^3 \]
                            \[ \text{Interna: } u(x) = 2x^3 + 5 \quad \implies \quad u'(x) = 6x^2 \]
                            Passo 2: Aplicação da Regra da Cadeia (dy/dx = f'(u) · u'):
                            \[ y' = 4(2x^3 + 5)^3 \cdot (6x^2) \]
                            Passo 3: Multiplicação dos coeficientes:
                            \[ y' = (4 \cdot 6x^2)(2x^3 + 5)^3 = 24x^2 (2x^3 + 5)^3 \]
                            Conclusão: y' = 24x² (2x³ + 5)³.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l14",
                unitId = "calc_u_derivatives",
                title = "Aula 4: Técnicas Especiais: Derivação Implícita e Logarítmica",
                conceptTitle = "Quando y Não Está Isolado ou Está no Expoente",
                explanation = """
                    1. Derivação Implícita:
                       Usa-se quando y está 'embaralhado' com x e não dá para isolar facilmente (ex: círculo x² + y² = 25).
                       
                       • A Regra: Derive tudo em relação a x normalmente. Toda vez que derivar um termo com y, MULTIPLIQUE por y' (ou dy/dx) por causa da Regra da Cadeia! Em seguida, isole y'.
                       • Exemplo: x² + y² = 25
                         1. Derive: 2x + 2y · y' = 0
                         2. Isole y': 2y · y' = -2x  =>  y' = -x / y
                    
                    2. Derivação Logarítmica:
                       Usa-se quando a variável está na base E no expoente ao mesmo tempo (tipo y = xˣ) ou produtos gigantes.
                       
                       • O Truque: Aplique ln dos dois lados antes de derivar!
                         1. y = xˣ  =>  ln(y) = ln(xˣ)
                         2. Derrube o expoente: ln(y) = x · ln(x)
                         3. Derive ambos os lados: (1/y) · y' = 1 · ln(x) + x · (1/x) = ln(x) + 1
                         4. Passe o y multiplicando: y' = y · (ln(x) + 1) = xˣ(ln(x) + 1)
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = "",
                hints = listOf(
                    "Toda vez que derivar y, surge um fator y' multiplicando.",
                    "Para xˣ, aplique ln dos dois lados para descer o expoente."
                ),
                solutionExplanation = "Derivação implícita e logarítmica resolvem equações relacionais complexas.",
                xpReward = 45,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l14_1",
                        title = "Derivação Implícita na Circunferência",
                        problemStatement = """Na equação da circunferência \[ x^2 + y^2 = 25 \] determine a inclinação da reta tangente no ponto P(3, 4):""",
                        options = listOf(
                            "4/3",
                            "-4/3",
                            "3/4",
                            "-3/4",
                            "-1"
                        ),
                        correctIndex = 3,
                        hint = "Derive implicitamente em relação a x: 2x + 2y·y' = 0. Isole y' = -x/y e substitua x = 3, y = 4.",
                        stepByStepSolution = """
                            Passo 1: Derivação de ambos os membros da equação em relação a x:
                            \[ \frac{d}{dx}(x^2) + \frac{d}{dx}(y^2) = \frac{d}{dx}(25) \]
                            Passo 2: Aplicação da Regra da Cadeia no termo com y:
                            \[ 2x + 2y \cdot y' = 0 \]
                            Passo 3: Isolamento algébrico de y':
                            \[ 2y \cdot y' = -2x \implies y' = -\frac{2x}{2y} = -\frac{x}{y} \]
                            Passo 4: Avaliação no ponto P(3, 4):
                            \[ y'(3, 4) = -\frac{3}{4} \]
                            Conclusão: A inclinação da reta tangente é -3/4.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l14_2",
                        title = "Derivação Logarítmica de xˣ",
                        problemStatement = """Calcule a derivada de \[ y = x^x \] para x > 0 utilizando a técnica da Derivação Logarítmica:""",
                        options = listOf(
                            "x · xˣ⁻¹ = xˣ",
                            "xˣ · ln(x)",
                            "xˣ (1 + ln(x))",
                            "eˣ (1 + x)",
                            "xˣ⁻¹ (ln(x) + 1)"
                        ),
                        correctIndex = 2,
                        hint = "Aplique ln em ambos os lados: ln y = x ln x. Derive implicitamente: y'/y = 1·ln x + x·(1/x) = ln x + 1.",
                        stepByStepSolution = """
                            Passo 1: Aplicação do logaritmo natural em ambos os membros:
                            \[ \ln(y) = \ln(x^x) = x \cdot \ln(x) \]
                            Passo 2: Derivação implícita em relação a x:
                            \[ \frac{d}{dx}(\ln(y)) = \frac{d}{dx}(x \cdot \ln(x)) \]
                            Passo 3: Regra do produto no membro direito:
                            \[ \frac{y'}{y} = (1) \cdot \ln(x) + x \cdot \frac{1}{x} = \ln(x) + 1 \]
                            Passo 4: Multiplicação por y para isolar y':
                            \[ y' = y \cdot (\ln(x) + 1) = x^x (1 + \ln(x)) \]
                            Conclusão: A derivada é xˣ (1 + ln(x)).
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l15",
                unitId = "calc_u_derivatives",
                title = "Aula 5: Aproximação Linear e Diferenciais",
                conceptTitle = "Linearização: L(x) = f(a) + f'(a)(x - a)",
                explanation = """
                    Curvas são difíceis de calcular de cabeça, mas retas são fáceis!
                    Se você estiver bem perto de um ponto x = a, o gráfico da função e o gráfico da reta tangente são quase IDÊNTICOS!
                    
                    A Fórmula da Linearização:
                    L(x) = f(a) + f'(a)(x - a)
                    
                    Olhe o gráfico abaixo (grafico_aproximacao_linear):
                    Quer calcular √4.05? Em vez de usar a curva complexa √x, usamos a reta tangente em a = 4:
                    • f(4) = √4 = 2
                    • f'(x) = 1 / (2√x)  =>  f'(4) = 1 / 4 = 0.25
                    • L(4.05) = 2 + 0.25(4.05 - 4) = 2 + 0.25(0.05) = 2.0125
                    
                    Valor exato na calculadora: 2.01246... Erro menor que 0.0001!
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "Escolha um valor 'a' fácil e muito próximo do valor pedido.",
                    "L(x) é a equação da reta tangente reescrita."
                ),
                solutionExplanation = "A linearização substitui a curva pela tangente nas proximidades de 'a'.",
                xpReward = 40,
                imageResId = R.drawable.grafico_aproximacao_linear,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l15_1",
                        title = "Linearização da Função Raiz Quadrada",
                        problemStatement = """Utilizando a reta tangente L(x) = f(a) + f'(a)(x - a) para \[ f(x) = \\sqrt{x} \] em torno de a = 4, estime o valor de √4.2:""",
                        options = listOf(
                            "2.10",
                            "2.05",
                            "2.025",
                            "2.20",
                            "2.00"
                        ),
                        correctIndex = 1,
                        hint = "f(4) = √4 = 2. f'(x) = 1/(2√x) => f'(4) = 1/4 = 0.25. L(4.2) = 2 + 0.25(4.2 - 4).",
                        stepByStepSolution = """
                            Passo 1: Cálculo do ponto base: f(4) = √4 = 2.
                            Passo 2: Cálculo da taxa de variação (derivada):
                            \[ f'(x) = \frac{1}{2\sqrt{x}} \implies f'(4) = \frac{1}{2\sqrt{4}} = \frac{1}{4} = 0.25 \]
                            Passo 3: Construção da aproximação linear:
                            \[ L(x) = f(4) + f'(4)(x - 4) = 2 + 0.25(x - 4) \]
                            Passo 4: Avaliação para x = 4.2 (onde Δx = 0.2):
                            \[ L(4.2) = 2 + 0.25(4.2 - 4) = 2 + 0.25(0.2) = 2 + 0.05 = 2.05 \]
                            Conclusão: A aproximação linear fornece √4.2 ≈ 2.05.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l15_2",
                        title = "Equação Geral de Linearização",
                        problemStatement = """Se para uma função diferenciável sabemos que f(1) = 3 e f'(1) = 5, qual é a fórmula da linearização L(x) em torno de a = 1?""",
                        options = listOf(
                            "L(x) = 5x + 3",
                            "L(x) = 3x + 5",
                            "L(x) = 5x - 2",
                            "L(x) = 5x - 5",
                            "L(x) = 3x - 2"
                        ),
                        correctIndex = 2,
                        hint = "L(x) = f(a) + f'(a)(x - a) = 3 + 5(x - 1). Simplifique a expressão.",
                        stepByStepSolution = """
                            Passo 1: Aplicação da fórmula geral da linearização:
                            \[ L(x) = f(1) + f'(1)(x - 1) \]
                            Passo 2: Substituição dos valores dados f(1) = 3 e f'(1) = 5:
                            \[ L(x) = 3 + 5(x - 1) \]
                            Passo 3: Simplificação algébrica:
                            \[ L(x) = 3 + 5x - 5 = 5x - 2 \]
                            Conclusão: A reta de aproximação linear é L(x) = 5x - 2.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l16",
                unitId = "calc_u_derivatives",
                title = "Aula 6: Estudo Completo da Função e Esboço de Gráficos",
                conceptTitle = "Pontos Críticos, Máximos, Mínimos e Concavidade",
                explanation = """
                    Esta é a parte onde unimos a 1ª e a 2ª derivada para mapear todo o comportamento do gráfico:
                    
                    Olhe o gráfico abaixo (grafico_estudo_funcao_max_min):
                    
                    1. Pontos Críticos:
                       Candidatos a extremos. Ocorrem onde f'(x) = 0 (tangente horizontal) ou onde f'(x) não existe.
                    
                    2. Teste da 1ª Derivada (Crescimento e Extremos):
                       • f'(x) > 0: Função CRESCENTE ↗
                       • f'(x) < 0: Função DECRESCENTE ↘
                       • Mudança de sinal:
                         (+ para -): Máximo Local 🏔️
                         (- para +): Mínimo Local 🕳️
                    
                    3. Teste da 2ª Derivada (Concavidade e Inflexão):
                       A 2ª derivada f''(x) mede a curvatura:
                       • f''(x) > 0: Concavidade para CIMA ⌣ ('Carinha Feliz' / copo segurando água)
                       • f''(x) < 0: Concavidade para BAIXO ⌢ ('Carinha Triste' / guarda-chuva)
                       • Ponto de Inflexão: f''(x) = 0 com MUDANÇA de concavidade!
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "f'(x) = 0 indica ponto crítico (candidato a max/min).",
                    "f''(x) > 0 indica concavidade para cima (mínimo se f'=0)."
                ),
                solutionExplanation = "A 1ª derivada rege inclinação e a 2ª derivada rege concavidade.",
                xpReward = 45,
                imageResId = R.drawable.grafico_estudo_funcao_max_min,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l16_1",
                        title = "Teste da Segunda Derivada em Ponto Crítico",
                        problemStatement = """Seja f(x) uma função com derivada nula f'(3) = 0 e segunda derivada f''(3) = -8. Com base no Teste da Segunda Derivada, o que podemos afirmar sobre x = 3?""",
                        options = listOf(
                            "x = 3 é um Ponto de Mínimo Local",
                            "x = 3 é um Ponto de Máximo Local",
                            "x = 3 é um Ponto de Inflexão com concavidade nula",
                            "O teste é inconclusivo e exige análise da 3ª derivada",
                            "A função é descontínua em x = 3"
                        ),
                        correctIndex = 1,
                        hint = "f''(x) < 0 indica concavidade voltada para baixo (formato de sino ou topo de morro). Logo, o ponto crítico é um Máximo.",
                        stepByStepSolution = """
                            Passo 1: Identificação do Ponto Crítico. Como f'(3) = 0, a reta tangente é horizontal em x = 3.
                            Passo 2: Análise da concavidade pela segunda derivada: f''(3) = -8 < 0.
                            Passo 3: Aplicação do Teste da Segunda Derivada:
                            - Se f''(c) > 0: concavidade para cima ∪ (Mínimo Local).
                            - Se f''(c) < 0: concavidade para baixo ∩ (Máximo Local).
                            Conclusão: Como f''(3) = -8 < 0, x = 3 é indiscutivelmente um Ponto de Máximo Local.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l16_2",
                        title = "Definição de Ponto de Inflexão",
                        problemStatement = """O que caracteriza formalmente um Ponto de Inflexão no gráfico de uma função duas vezes diferenciável?""",
                        options = listOf(
                            "O ponto onde a derivada primeira f'(x) atinge valor nulo",
                            "O ponto onde a função atinge o valor máximo absoluto",
                            "O ponto onde a curva muda o sentido de sua concavidade (de côncava para convexa ou vice-versa)",
                            "O ponto onde a reta tangente torna-se estritamente vertical",
                            "O ponto de cruzamento com o eixo das ordenadas"
                        ),
                        correctIndex = 2,
                        hint = "Inflexão refere-se à mudança de curvatura/concavidade, correspondendo à mudança de sinal de f''(x).",
                        stepByStepSolution = """
                            Passo 1: Definição geométrica. Um ponto de inflexão é o ponto onde o gráfico de uma função contínua muda o sentido da sua concavidade (de côncava para cima para côncava para baixo, ou vice-versa).
                            Passo 2: Condição necessária. Em funções diferenciáveis de segunda ordem, f''(x) = 0 ou não existe no ponto de inflexão.
                            Passo 3: Condição suficiente. A segunda derivada f''(x) deve obrigatoriamente alternar de sinal ao cruzar o ponto.
                            Conclusão: Um ponto de inflexão é definido pela mudança do sentido da concavidade da curva.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l17",
                unitId = "calc_u_derivatives",
                title = "Aula 7: Regra de L'Hôpital (O Shortcut de Ouro)",
                conceptTitle = "Destravando 0/0 e ∞/∞ com Derivadas",
                explanation = """
                    Quando você estiver calculando um limite e cair numa indeterminação do tipo 0/0 ou ∞/∞, a Regra de L'Hôpital autoriza você a DERIVAR o numerador e o denominador SEPARADAMENTE:
                    
                    lim_{x -> a} [f(x) / g(x)] = lim_{x -> a} [f'(x) / g'(x)]
                    
                    Exemplo Clássico:
                    lim_{x -> 0} sen(x) / x  =>  (0/0)
                    
                    Derivando em cima e embaixo separadamente:
                    • (sen x)' = cos x
                    • (x)' = 1
                    
                    lim_{x -> 0} cos(x) / 1 = cos(0) / 1 = 1 / 1 = 1!
                    
                    ATENÇÃO: Não use a Regra do Quociente aqui! Você apenas deriva o de cima sozinho e o de baixo sozinho.
                """.trimIndent(),
                lessonType = LessonType.PRACTICE,
                initialCode = "",
                hints = listOf(
                    "L'Hôpital só pode ser aplicada em indeterminações do tipo 0/0 ou ∞/∞.",
                    "Derive o de cima sozinho e o de baixo sozinho (NÃO use a regra do quociente)."
                ),
                solutionExplanation = "A Regra de L'Hôpital converte limites indeterminados em limites de derivadas.",
                xpReward = 40,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l17_1",
                        title = "Aplicação Direta da Regra de L'Hôpital",
                        problemStatement = """Calcule o limite que resulta na forma indeterminada 0/0 aplicando a Regra de L'Hôpital: \[ \\lim_{x \\to 0} \\frac{e^x - 1}{x} \]""",
                        options = listOf(
                            "0",
                            "e",
                            "1",
                            "+∞",
                            "Indeterminado"
                        ),
                        correctIndex = 2,
                        hint = "Substituindo x = 0 temos (e⁰ - 1)/0 = 0/0. Derive o numerador (eˣ)' = eˣ e o denominador (x)' = 1.",
                        stepByStepSolution = """
                            Passo 1: Verificação da indeterminação:
                            \[ \lim_{x \to 0} (e^x - 1) = e^0 - 1 = 0 \quad \text{e} \quad \lim_{x \to 0} x = 0 \implies \frac{0}{0} \]
                            Passo 2: Aplicação do Teorema de L'Hôpital (derivar numerador e denominador separadamente):
                            \[ \lim_{x \to 0} \frac{e^x - 1}{x} = \lim_{x \to 0} \frac{\frac{d}{dx}(e^x - 1)}{\frac{d}{dx}(x)} \]
                            Passo 3: Cálculo das derivadas: (eˣ - 1)' = eˣ e (x)' = 1.
                            Passo 4: Avaliação do novo limite:
                            \[ \lim_{x \to 0} \frac{e^x}{1} = \frac{e^0}{1} = \frac{1}{1} = 1 \]
                            Conclusão: O limite é exatamente igual a 1.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l17_2",
                        title = "Regra de L'Hôpital em Quociente Polinomial",
                        problemStatement = """Avalie o limite racional utilizando a Regra de L'Hôpital: \[ \\lim_{x \\to 2} \\frac{x^3 - 8}{x - 2} \]""",
                        options = listOf(
                            "4",
                            "8",
                            "12",
                            "16",
                            "0"
                        ),
                        correctIndex = 2,
                        hint = "2³ - 8 = 0 e 2 - 2 = 0 (forma 0/0). Derive: (x³ - 8)' = 3x² e (x - 2)' = 1.",
                        stepByStepSolution = """
                            Passo 1: Substituição direta: (2³ - 8)/(2 - 2) = 0/0 (indeterminação de Cauchy).
                            Passo 2: Aplicação da Regra de L'Hôpital:
                            \[ \lim_{x \to 2} \frac{x^3 - 8}{x - 2} = \lim_{x \to 2} \frac{\frac{d}{dx}(x^3 - 8)}{\frac{d}{dx}(x - 2)} \]
                            Passo 3: Cálculo das derivadas imediatas:
                            \[ \frac{d}{dx}(x^3 - 8) = 3x^2 \quad \text{e} \quad \frac{d}{dx}(x - 2) = 1 \]
                            Passo 4: Cálculo por substituição direta no novo quociente:
                            \[ \lim_{x \to 2} \frac{3x^2}{1} = 3(2)^2 = 3(4) = 12 \]
                            Conclusão: O limite é 12.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l18",
                unitId = "calc_u_derivatives",
                title = "Aula 8: Problemas de Otimização (A Aplicação Suprema)",
                conceptTitle = "Maximizando Lucro e Minimizando Custo em 4 Passos",
                explanation = """
                    Problemas de otimização pedem para você encontrar a MAIOR área, o MENOR custo, o MAIOR lucro ou o MENOR tempo.
                    
                    Roteiro de Resolução em 4 Passos:
                    1. Escrever a Equação Objetivo:
                       Crie a fórmula da grandeza que você quer maximizar/minimizar (ex: Área A = x · y).
                    
                    2. Deixar com Apenas UMA Variável:
                       Use as restrições do problema (ex: perímetro fixo 2x + 2y = 100 => y = 50 - x) para substituir na equação objetivo:
                       A(x) = x · (50 - x) = 50x - x²
                    
                    3. Derivar e Igualar a Zero:
                       Calcule A'(x) = 0 para achar o ponto crítico:
                       A'(x) = 50 - 2x = 0  =>  2x = 50  =>  x = 25
                    
                    4. Verificar o Resultado:
                       Confirme pela 2ª derivada: A''(x) = -2 < 0 (concavidade triste => MÁXIMO absoluto!).
                       Dimensões ótimas: x = 25 e y = 25 (um quadrado perfeito com área máxima de 625 m²)!
                """.trimIndent(),
                lessonType = LessonType.CHALLENGE,
                initialCode = "",
                hints = listOf(
                    "Sempre use a restrição do problema para eliminar uma das incógnitas.",
                    "O ponto ótimo ocorre onde a derivada da função objetivo se anula (f'(x) = 0)."
                ),
                solutionExplanation = "Otimização localiza o ápice ou vale através do ponto crítico f'(x) = 0.",
                xpReward = 50,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l18_1",
                        title = "Otimização de Área com Restrição de Perímetro",
                        problemStatement = """Um fazendeiro possui 100 metros lineares de cerca para cercar um terreno retangular de dimensões x e y (perímetro 2x + 2y = 100). Qual dimensão x maximiza a área do terreno?""",
                        options = listOf(
                            "x = 10 metros",
                            "x = 20 metros",
                            "x = 25 metros",
                            "x = 50 metros",
                            "x = 30 metros"
                        ),
                        correctIndex = 2,
                        hint = "Perímetro: 2x + 2y = 100 => y = 50 - x. Área A(x) = x(50 - x) = 50x - x². Derive e iguale a zero: A'(x) = 50 - 2x = 0.",
                        stepByStepSolution = """
                            Passo 1: Equação de vínculo (restrição):
                            \[ 2x + 2y = 100 \implies x + y = 50 \implies y = 50 - x \]
                            Passo 2: Formulação da função objetivo a maximizar (Área = largura × comprimento):
                            \[ A(x) = x \cdot y = x(50 - x) = 50x - x^2 \]
                            Passo 3: Derivação para encontrar o ponto crítico:
                            \[ A'(x) = \frac{d}{dx}(50x - x^2) = 50 - 2x \]
                            Passo 4: Igualar a derivada a zero (A'(x) = 0):
                            \[ 50 - 2x = 0 \implies 2x = 50 \implies x = 25\text{ metros} \]
                            Passo 5: Teste da segunda derivada para confirmação de máximo:
                            \[ A''(x) = -2 < 0 \implies \text{Ponto de Máximo Global} \]
                            Conclusão: O valor de x que maximiza a área cercada é x = 25 metros (um quadrado de 25m × 25m com área máxima de 625 m²).
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l18_2",
                        title = "Minimização de Custo de Produção",
                        problemStatement = """Uma fábrica modelou o seu custo operacional diário pela função \[ C(x) = x^2 - 40x + 500 \] onde x é a quantidade de lotes produzidos. Quantos lotes x minimizam o custo?""",
                        options = listOf(
                            "10 lotes",
                            "40 lotes",
                            "20 lotes",
                            "25 lotes",
                            "15 lotes"
                        ),
                        correctIndex = 2,
                        hint = "Derive a função de custo: C'(x) = 2x - 40. Iguale a zero para encontrar o ponto crítico.",
                        stepByStepSolution = """
                            Passo 1: Função Custo: C(x) = x² - 40x + 500.
                            Passo 2: Cálculo da taxa de variação (Custo Marginal):
                            \[ C'(x) = \frac{d}{dx}(x^2 - 40x + 500) = 2x - 40 \]
                            Passo 3: Ponto crítico onde o custo cessa de decrescer e atinge o mínimo (C'(x) = 0):
                            \[ 2x - 40 = 0 \implies 2x = 40 \implies x = 20\text{ lotes} \]
                            Passo 4: Teste da 2ª derivada para confirmação de mínimo:
                            \[ C''(x) = 2 > 0 \implies \text{Concavidade voltada para cima (Mínimo Local)} \]
                            Conclusão: A quantidade que minimiza o custo é de 20 lotes operacionais.
                        """.trimIndent()
                    )
                )
            )
        )
    )

    // ========================================================
    // TESTES FINAIS: ARENA DE CÁLCULO I (12 Questões Cada)
    // ========================================================

    val calculusLimitsFinalTest = KahootModule(
        id = "test_calculus_limits",
        tier = DifficultyTier.BASIC,
        title = "Teste Final: Cálculo I (Limites)",
        description = "Desafio decisivo de 12 questões sobre vizinhança, limites laterais, fatoração, continuidade e assíntotas!",
        category = "Matemática",
        subject = "Cálculo I",
        questions = listOf(
            KahootQuestion(
                id = "cq_1",
                question = "Segundo o Teorema da Existência, o que é estritamente necessário para que o limite bilateral lim_{x->a} f(x) exista?",
                codeSnippet = "lim_{x -> a} f(x) = L <=> ?",
                options = listOf(
                    "lim_{x->a⁻} f(x) deve ser igual a lim_{x->a⁺} f(x)",
                    "A função f(a) precisa estar definida e ser diferente de zero",
                    "A função precisa ter uma assíntota vertical no ponto a",
                    "A reta secante precisa ter inclinação negativa"
                ),
                correctIndex = 0,
                explanation = "O limite bilateral só existe se os limites laterais pela esquerda e pela direita existirem e forem idênticos.",
                conceptTag = "Limites Laterais e Existência",
                recommendedLessonId = "calc_l2",
                recommendedLessonTitle = "Aula 2: Limites Laterais e Existência"
            ),
            KahootQuestion(
                id = "cq_2",
                question = "No gráfico de limites laterais (pulo em x = 2), com lim_{x->2⁻} f(x) = 3 e lim_{x->2⁺} f(x) = 1, qual é o limite bilateral em x = 2?",
                codeSnippet = "lim_{x->2^-} = 3 | lim_{x->2^+} = 1",
                options = listOf(
                    "O limite bilateral NÃO existe (descontinuidade de salto)",
                    "O limite é 2 (a média aritmética)",
                    "O limite é 3",
                    "O limite é infinito (+∞)"
                ),
                correctIndex = 0,
                explanation = "Como 3 != 1, há um salto no gráfico e o limite bilateral NÃO existe.",
                conceptTag = "Limites Laterais e Salto",
                recommendedLessonId = "calc_l2",
                recommendedLessonTitle = "Aula 2: Limites Laterais e Existência"
            ),
            KahootQuestion(
                id = "cq_3",
                question = "Ao calcular lim_{x->3} (2x² - 3x + 5), qual é a primeira estratégia recomendada?",
                codeSnippet = "lim_{x -> 3} (2x**2 - 3x + 5)",
                options = listOf(
                    "Substituição direta simples: 2(3)² - 3(3) + 5 = 14",
                    "Derivar o numerador e o denominador",
                    "Declarar que o limite não existe",
                    "Criar uma tabela com 50 aproximações"
                ),
                correctIndex = 0,
                explanation = "Como é uma função polinomial contínua, a substituição direta x = 3 fornece a resposta exata: 14.",
                conceptTag = "Substituição Direta",
                recommendedLessonId = "calc_l3",
                recommendedLessonTitle = "Aula 3: Propriedades e Substituição Direta"
            ),
            KahootQuestion(
                id = "cq_4",
                question = "O que a regra de ouro do limite diz sobre o valor exato da função f(a)?",
                codeSnippet = "lim_{x -> a} f(x) vs f(a)",
                options = listOf(
                    "Não importa o que ocorre no ponto x=a, apenas na vizinhança",
                    "f(a) deve ser sempre estritamente maior que 0",
                    "f(a) obrigatoriamente deve ser igual ao limite",
                    "O ponto x=a deve ser isolado do domínio"
                ),
                correctIndex = 0,
                explanation = "O limite estuda o comportamento ao redor do ponto (na vizinhança), mesmo que a função não exista em x = a.",
                conceptTag = "A Ideia do Limite",
                recommendedLessonId = "calc_l1",
                recommendedLessonTitle = "Aula 1: O que é o Limite? (A Ideia Básica)"
            ),
            KahootQuestion(
                id = "cq_5",
                question = "Ao encontrar 0/0 pela substituição direta, qual é o significado analítico desse resultado?",
                codeSnippet = "f(x) -> 0/0",
                options = listOf(
                    "É uma indeterminação: há um fator nulo comum que pode ser cancelado",
                    "A função não tem limite em nenhum ponto",
                    "O resultado final é categoricamente 0",
                    "O resultado final é categoricamente 1"
                ),
                correctIndex = 0,
                explanation = "0/0 é uma indeterminação algébrica que esconde o verdadeiro limite através de fatoração ou cancelamento.",
                conceptTag = "Indeterminação 0/0",
                recommendedLessonId = "calc_l4",
                recommendedLessonTitle = "Aula 4: Indeterminação 0/0 e o Furo Removível"
            ),
            KahootQuestion(
                id = "cq_6",
                question = "Qual é o valor de lim_{x->2} (x² - 4)/(x - 2)?",
                codeSnippet = "lim_{x->2} (x^2 - 4) / (x - 2)",
                options = listOf(
                    "4",
                    "0",
                    "2",
                    "Infinito"
                ),
                correctIndex = 0,
                explanation = "Fatorando: (x - 2)(x + 2)/(x - 2) = x + 2. Quando x -> 2, 2 + 2 = 4.",
                conceptTag = "Fatoração Algébrica",
                recommendedLessonId = "calc_l4",
                recommendedLessonTitle = "Aula 4: Indeterminação 0/0 e o Furo Removível"
            ),
            KahootQuestion(
                id = "cq_7",
                question = "Quais são os 3 requisitos para que f(x) seja contínua em x = a?",
                codeSnippet = "Continuidade em x = a",
                options = listOf(
                    "f(a) existe, lim_{x->a} f(x) existe e lim_{x->a} f(x) = f(a)",
                    "f'(a) > 0, f''(a) > 0 e f(a) > 0",
                    "A função deve ser um polinômio de grau ímpar",
                    "Os limites laterais devem ser infinitos"
                ),
                correctIndex = 0,
                explanation = "Continuidade exige: existência de f(a), existência do limite e igualdade estrita entre ambos.",
                conceptTag = "Critérios de Continuidade",
                recommendedLessonId = "calc_l6",
                recommendedLessonTitle = "Aula 6: Continuidade e Matriz Diagnóstica"
            ),
            KahootQuestion(
                id = "cq_8",
                question = "O que caracteriza uma assíntota vertical no ponto x = a?",
                codeSnippet = "x = a (Assíntota Vertical)",
                options = listOf(
                    "Pelo menos um limite lateral tende a +∞ ou -∞",
                    "O limite bilateral é igual a zero",
                    "A função atinge seu valor máximo absoluto",
                    "A derivada segunda é nula"
                ),
                correctIndex = 0,
                explanation = "A assíntota vertical ocorre onde o gráfico dispara para ±∞ na vizinhança de a.",
                conceptTag = "Assíntotas Verticais",
                recommendedLessonId = "calc_l7",
                recommendedLessonTitle = "Aula 7: Limites Infinitos e Assíntotas Verticais"
            ),
            KahootQuestion(
                id = "cq_9",
                question = "Calcule o limite no infinito: lim_{x->∞} (3x² + 5)/(x² - 2).",
                codeSnippet = "lim_{x->∞} (3x² + 5) / (x² - 2)",
                options = listOf(
                    "3",
                    "5",
                    "0",
                    "∞"
                ),
                correctIndex = 0,
                explanation = "Dividindo por x²: (3 + 5/x²)/(1 - 2/x²) -> 3/1 = 3.",
                conceptTag = "Limites no Infinito",
                recommendedLessonId = "calc_l8",
                recommendedLessonTitle = "Aula 8: Limites no Infinito e Assíntotas Horizontais"
            ),
            KahootQuestion(
                id = "cq_10",
                question = "Qual é o valor do Limite Fundamental Trigonométrico lim_{x->0} sen(x)/x?",
                codeSnippet = "lim_{x->0} sen(x) / x",
                options = listOf(
                    "1",
                    "0",
                    "Indeterminado para sempre",
                    "π"
                ),
                correctIndex = 0,
                explanation = "lim_{x->0} sen(x)/x = 1 é a base de todas as derivadas trigonométricas.",
                conceptTag = "Limite Fundamental Trigonométrico",
                recommendedLessonId = "calc_l9",
                recommendedLessonTitle = "Aula 9: Teorema do Sanduíche e Limite Trigonométrico"
            ),
            KahootQuestion(
                id = "cq_11",
                question = "Se uma função f(x) está prensada entre g(x) = 2 - x² e h(x) = 2 + x² para todo x, qual é lim_{x->0} f(x)?",
                codeSnippet = "2 - x² <= f(x) <= 2 + x²",
                options = listOf(
                    "2 (pelo Teorema do Sanduíche)",
                    "0",
                    "4",
                    "Não é possível determinar"
                ),
                correctIndex = 0,
                explanation = "Como lim_{x->0} (2 - x²) = 2 e lim_{x->0} (2 + x²) = 2, f(x) é forçada a ser 2.",
                conceptTag = "Teorema do Sanduíche",
                recommendedLessonId = "calc_l9",
                recommendedLessonTitle = "Aula 9: Teorema do Sanduíche e Limite Trigonométrico"
            ),
            KahootQuestion(
                id = "cq_12",
                question = "Como a reta secante se transforma na reta tangente?",
                codeSnippet = "Reta Secante -> Reta Tangente",
                options = listOf(
                    "Quando o intervalo h entre os dois pontos encolhe até zero no limite",
                    "Quando a função é multiplicada por uma constante negativa",
                    "Quando o gráfico é rotacionado em 90 graus",
                    "Quando a concavidade da função se inverte"
                ),
                correctIndex = 0,
                explanation = "A reta tangente é o limite geométrico da reta secante quando h tende a zero.",
                conceptTag = "Da Secante à Tangente",
                recommendedLessonId = "calc_l10",
                recommendedLessonTitle = "Aula 10: O Elo Fundamental (Da Secante à Tangente)"
            )
        )
    )

    val calculusDerivativesFinalTest = KahootModule(
        id = "test_calculus_derivatives",
        tier = DifficultyTier.BASIC,
        title = "Teste Final: Cálculo I (Derivadas)",
        description = "Grande Arena de P1 e P2: 12 questões sobre regras de derivação, cadeia, linearização, estudo de funções, L'Hôpital e otimização!",
        category = "Matemática",
        subject = "Cálculo I",
        questions = listOf(
            KahootQuestion(
                id = "cd_1",
                question = "Pela Regra do Tombo, qual é a derivada de f(x) = 4x³ - 5x² + 9x - 7?",
                codeSnippet = "f(x) = 4x³ - 5x² + 9x - 7",
                options = listOf(
                    "12x² - 10x + 9",
                    "12x³ - 10x² + 9",
                    "4x² - 5x + 9",
                    "12x² - 10x"
                ),
                correctIndex = 0,
                explanation = "(4*3x²) - (5*2x) + 9(1) - 0 = 12x² - 10x + 9.",
                conceptTag = "Regra do Tombo",
                recommendedLessonId = "calc_l12",
                recommendedLessonTitle = "Aula 2: As Regras Rápidas de Derivação"
            ),
            KahootQuestion(
                id = "cd_2",
                question = "Qual é a única função elementar do universo cuja derivada é exatamente ela mesma?",
                codeSnippet = "(f(x))' = f(x)",
                options = listOf(
                    "f(x) = eˣ",
                    "f(x) = ln(x)",
                    "f(x) = sen(x)",
                    "f(x) = x"
                ),
                correctIndex = 0,
                explanation = "(eˣ)' = eˣ. Sua taxa de crescimento instantânea é sempre idêntica à sua própria altura.",
                conceptTag = "Derivada da Exponencial",
                recommendedLessonId = "calc_l12",
                recommendedLessonTitle = "Aula 2: As Regras Rápidas de Derivação"
            ),
            KahootQuestion(
                id = "cd_3",
                question = "Segundo a Regra do Produto (f · g)', como derivamos uma multiplicação de funções?",
                codeSnippet = "(f · g)' = ?",
                options = listOf(
                    "f' · g + f · g'",
                    "f' · g'",
                    "f' · g - f · g'",
                    "(f' + g') / 2"
                ),
                correctIndex = 0,
                explanation = "(f · g)' = f' · g + f · g' ('deriva o primeiro, copia o segundo MAIS copia o primeiro, deriva o segundo').",
                conceptTag = "Regra do Produto",
                recommendedLessonId = "calc_l13",
                recommendedLessonTitle = "Aula 3: As 3 Regras de Ouro para Operações"
            ),
            KahootQuestion(
                id = "cd_4",
                question = "Calcule a derivada de y = sen(x⁴) aplicando a Regra da Cadeia.",
                codeSnippet = "y = sen(x⁴)",
                options = listOf(
                    "4x³ cos(x⁴)",
                    "cos(x⁴)",
                    "4x³ sen(x⁴)",
                    "cos(4x³)"
                ),
                correctIndex = 0,
                explanation = "Derivada de fora: cos(x⁴). Derivada de dentro: 4x³. Multiplicando: 4x³ cos(x⁴).",
                conceptTag = "Regra da Cadeia",
                recommendedLessonId = "calc_l13",
                recommendedLessonTitle = "Aula 3: As 3 Regras de Ouro para Operações"
            ),
            KahootQuestion(
                id = "cd_5",
                question = "Na equação x² + y² = 100, usando derivação implícita, qual é a expressão de y' (ou dy/dx)?",
                codeSnippet = "x² + y² = 100",
                options = listOf(
                    "y' = -x / y",
                    "y' = -y / x",
                    "y' = x / y",
                    "y' = 2x + 2y"
                ),
                correctIndex = 0,
                explanation = "2x + 2y y' = 0 => 2y y' = -2x => y' = -x / y.",
                conceptTag = "Derivação Implícita",
                recommendedLessonId = "calc_l14",
                recommendedLessonTitle = "Aula 4: Técnicas Especiais: Derivação Implícita e Logarítmica"
            ),
            KahootQuestion(
                id = "cd_6",
                question = "Quando devemos utilizar a técnica da Derivação Logarítmica?",
                codeSnippet = "ln(y) = ln(f(x))",
                options = listOf(
                    "Quando a variável está na base e no expoente (tipo y = xˣ) ou produtos gigantes",
                    "Apenas para polinômios de grau 2",
                    "Apenas quando a função corta o eixo horizontal",
                    "Quando a derivada segunda for sempre nula"
                ),
                correctIndex = 0,
                explanation = "Aplica-se ln em ambos os lados para descer o expoente com a propriedade ln(aᵇ) = b · ln(a).",
                conceptTag = "Derivação Logarítmica",
                recommendedLessonId = "calc_l14",
                recommendedLessonTitle = "Aula 4: Técnicas Especiais: Derivação Implícita e Logarítmica"
            ),
            KahootQuestion(
                id = "cd_7",
                question = "Qual é a fórmula da Aproximação Linear (Linearização L(x)) em torno de x = a?",
                codeSnippet = "L(x) = ?",
                options = listOf(
                    "L(x) = f(a) + f'(a)(x - a)",
                    "L(x) = f'(a) + f(a)(x - a)",
                    "L(x) = f(a) · (x - a)",
                    "L(x) = f'(a) / (x - a)"
                ),
                correctIndex = 0,
                explanation = "L(x) = f(a) + f'(a)(x - a) é a reta tangente usada como aproximação da curva perto de a.",
                conceptTag = "Aproximação Linear",
                recommendedLessonId = "calc_l15",
                recommendedLessonTitle = "Aula 5: Aproximação Linear e Diferenciais"
            ),
            KahootQuestion(
                id = "cd_8",
                question = "O que caracteriza os Pontos Críticos de uma função derivável f(x)?",
                codeSnippet = "Ponto Crítico em x = c",
                options = listOf(
                    "f'(c) = 0 ou f'(c) não existe (a reta tangente é horizontal)",
                    "f(c) = 0 obrigatoriamente",
                    "f''(c) > 0 sempre",
                    "A função possui assíntota vertical"
                ),
                correctIndex = 0,
                explanation = "Pontos críticos são candidatos a máximos e mínimos onde f'(x) = 0 ou não existe.",
                conceptTag = "Pontos Críticos",
                recommendedLessonId = "calc_l16",
                recommendedLessonTitle = "Aula 6: Estudo Completo da Função e Esboço de Gráficos"
            ),
            KahootQuestion(
                id = "cd_9",
                question = "Se f'(2) = 0 e a segunda derivada é f''(2) = 6 (> 0), que tipo de ponto temos em x = 2?",
                codeSnippet = "f'(2) = 0 e f''(2) = 6 > 0",
                options = listOf(
                    "Ponto de Mínimo Local (concavidade para cima ⌣)",
                    "Ponto de Máximo Local (concavidade para baixo ⌢)",
                    "Ponto de Inflexão",
                    "O teste falha completamente"
                ),
                correctIndex = 0,
                explanation = "f''(x) > 0 indica concavidade para cima ('carinha feliz'). A tangente horizontal no fundo gera um Mínimo Local.",
                conceptTag = "Teste da 2ª Derivada",
                recommendedLessonId = "calc_l16",
                recommendedLessonTitle = "Aula 6: Estudo Completo da Função e Esboço de Gráficos"
            ),
            KahootQuestion(
                id = "cd_10",
                question = "Quando podemos aplicar a Regra de L'Hôpital para calcular um limite?",
                codeSnippet = "lim f(x)/g(x) = lim f'(x)/g'(x)",
                options = listOf(
                    "Apenas quando o limite direto resultar em 0/0 ou ∞/∞",
                    "Em qualquer fração matemática sem restrições",
                    "Apenas quando o denominador for estritamente igual a 1",
                    "Apenas para limites no infinito com polinômios"
                ),
                correctIndex = 0,
                explanation = "L'Hôpital exige rigorosamente a verificação prévia da indeterminação 0/0 ou ∞/∞.",
                conceptTag = "Regra de L'Hôpital",
                recommendedLessonId = "calc_l17",
                recommendedLessonTitle = "Aula 7: Regra de L'Hôpital (O Shortcut de Ouro)"
            ),
            KahootQuestion(
                id = "cd_11",
                question = "Calcule usando L'Hôpital: lim_{x->0} (e²ˣ - 1) / x.",
                codeSnippet = "lim_{x->0} (e^(2x) - 1) / x",
                options = listOf(
                    "2",
                    "1",
                    "0",
                    "e²"
                ),
                correctIndex = 0,
                explanation = "Derivada do de cima: 2e²ˣ. Derivada do de baixo: 1. Para x=0: 2e⁰ / 1 = 2.",
                conceptTag = "L'Hôpital na Prática",
                recommendedLessonId = "calc_l17",
                recommendedLessonTitle = "Aula 7: Regra de L'Hôpital (O Shortcut de Ouro)"
            ),
            KahootQuestion(
                id = "cd_12",
                question = "Qual é o terceiro passo fundamental no roteiro de resolução de problemas de Otimização?",
                codeSnippet = "Roteiro de Otimização",
                options = listOf(
                    "Derivar a função objetivo em relação à única variável e igualar a zero",
                    "Calcular a integral indefinida da restrição",
                    "Multiplicar a equação por uma constante arbitrária",
                    "Declarar que todo retângulo ótimo tem lados desiguais"
                ),
                correctIndex = 0,
                explanation = "Após deixar a equação objetivo com uma variável só, deriva-se e iguala-se a zero para encontrar o ponto crítico ótimo.",
                conceptTag = "Problemas de Otimização",
                recommendedLessonId = "calc_l18",
                recommendedLessonTitle = "Aula 8: Problemas de Otimização (A Aplicação Suprema)"
            )
        )
    )

    // ========================================================
    // MÓDULO 3: INTEGRAIS E TEOREMA FUNDAMENTAL DO CÁLCULO (8 Aulas)
    // ========================================================

    val integralsUnit = LearningUnit(
        id = "calc_u_integrals",
        unitNumber = 3,
        tier = DifficultyTier.BASIC,
        title = "Integrais e Teorema Fundamental",
        description = "Primitivas, regra da potência, integrais imediatas, linearidade, substituição, integração por partes, frações parciais e Teorema Fundamental do Cálculo.",
        category = "Matemática",
        subject = "Cálculo I",
        lessons = listOf(
            Lesson(
                id = "calc_l19",
                unitId = "calc_u_integrals",
                title = "Aula 1: O que significa 'Integrar'? (Primitivas)",
                conceptTitle = "A Antiderivada Geral e a Constante de Integração C",
                explanation = """
                    Integrar é fazer o caminho inverso da derivada. Se a derivada parte da posição e calcula a velocidade instantânea, a integral parte da velocidade e reconstrói o deslocamento total!
                    
                    • A Notação:
                      ∫ f(x) dx = F(x) + C  <=>  F'(x) = f(x)
                    
                    • Por que somar + C?
                      Como a derivada de qualquer constante é zero [d/dx(x² + 7) = 2x], existem infinitas funções primitivas que possuem a mesma derivada. A constante C representa toda essa família!
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Integrar é antiderivar: procure a função cuja derivada seja o integrando.",
                    "Lembre-se sempre de incluir a constante + C nas integrais indefinidas."
                ),
                solutionExplanation = "A integral indefinida representa toda a família de primitivas de uma função.",
                xpReward = 35,
                imageResId = R.drawable.grafico_integral_area,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l19_1",
                        title = "Identificação de Primitiva e Constante",
                        problemStatement = """Qual é a família de primitivas da função f(x) = 2x ?""",
                        options = listOf(
                            "F(x) = x² + C",
                            "F(x) = 2 + C",
                            "F(x) = x³/3 + C",
                            "F(x) = 2x² + C",
                            "F(x) = x²"
                        ),
                        correctIndex = 0,
                        hint = "Qual função quando derivada resulta exatamente em 2x?",
                        stepByStepSolution = """
                            Passo 1: Reconheça que d/dx[x²] = 2x.
                            Passo 2: Adicione a constante arbitrária de integração C.
                            Conclusão: ∫ 2x dx = x² + C.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l19_2",
                        title = "Propriedade Fundamental da Antiderivada",
                        problemStatement = """Se F'(x) = f(x) para todo x no domínio, o que afirma a relação fundamental do cálculo infinitesimal?""",
                        options = listOf(
                            "∫ f(x) dx = F(x) + C",
                            "∫ F(x) dx = f(x) + C",
                            "F(x) * f(x) = C",
                            "f'(x) = F(x)",
                            "∫ f'(x) dx = 0"
                        ),
                        correctIndex = 0,
                        hint = "A integral indefinida é a operação inversa direta da derivação.",
                        stepByStepSolution = """
                            Passo 1: A definição formal de integral indefinida estabelece que se F'(x) = f(x), então ∫ f(x) dx = F(x) + C.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l20",
                unitId = "calc_u_integrals",
                title = "Aula 2: A Mecânica Básica: Regra da Potência",
                conceptTitle = "O Inverso da Regra do Tombo: ∫ xⁿ dx = x^(n+1)/(n+1) + C",
                explanation = """
                    Na derivada (regra do tombo), você multiplicava pelo expoente e subtraía 1.
                    Na integral, você faz exatamente o oposto: SOMA 1 ao expoente e DIVIDE pelo novo expoente!
                    
                    Fórmula Geral:
                    ∫ xⁿ dx = [x^(n+1) / (n + 1)] + C   (para todo n ≠ -1)
                    
                    • Exemplos Práticos:
                      ∫ x³ dx = x⁴/4 + C
                      ∫ 1 dx = x + C  (pois x⁰ -> x¹/1)
                      ∫ √x dx = ∫ x^(1/2) dx = x^(3/2) / (3/2) + C = (2/3)x^(3/2) + C
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Converta raízes e potências no denominador para potências fracionárias ou negativas antes de integrar.",
                    "Dividir por uma fração a/b é o mesmo que multiplicar por b/a."
                ),
                solutionExplanation = "Soma-se 1 ao expoente e divide-se pelo novo valor.",
                xpReward = 35,
                imageResId = R.drawable.grafico_integral_area,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l20_1",
                        title = "Integral de Potência com Raiz (Exercício 50102)",
                        problemStatement = """Calcule a integral da função radical pela regra da potência: \[ \int \sqrt{x} \, dx \]""",
                        options = listOf(
                            """\frac{2}{3}x^{3/2} + C""",
                            """\frac{1}{2\sqrt{x}} + C""",
                            """\frac{3}{2}x^{3/2} + C""",
                            """\frac{2}{3}\sqrt{x} + C""",
                            """x^{3/2} + C"""
                        ),
                        correctIndex = 0,
                        hint = "Reescreva √x como x^(1/2). Some 1: 1/2 + 1 = 3/2. Divida por 3/2.",
                        stepByStepSolution = """
                            Passo 1: Escreva como expoente: √x = x^(1/2).
                            Passo 2: Some 1 ao expoente: 1/2 + 1 = 3/2.
                            Passo 3: Divida por 3/2: x^(3/2) / (3/2) = (2/3) x^(3/2) + C.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_calc_l20_2",
                        title = "Potência no Denominador",
                        problemStatement = """Calcule a integral da função racional: \[ \int \frac{1}{x^3} \, dx \]""",
                        options = listOf(
                            """-\frac{1}{2x^2} + C""",
                            """\frac{1}{4x^4} + C""",
                            """\ln|x^3| + C""",
                            """-\frac{3}{x^4} + C""",
                            """\frac{1}{2x^2} + C"""
                        ),
                        correctIndex = 0,
                        hint = "1/x³ = x^(-3). Somando 1 ao expoente: -3 + 1 = -2.",
                        stepByStepSolution = """
                            Passo 1: Reescreva com expoente negativo: ∫ x^(-3) dx.
                            Passo 2: Some 1: -3 + 1 = -2.
                            Passo 3: Divida pelo novo expoente: x^(-2) / (-2) + C = -1 / (2x²) + C.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l21",
                unitId = "calc_u_integrals",
                title = "Aula 3: Exponenciais, Logaritmos e Funções Trigonométricas",
                conceptTitle = "Integrais Imediatas Clássicas",
                explanation = """
                    Quando n = -1 na regra da potência, teríamos divisão por zero. A primitiva de 1/x é o logaritmo natural!
                    
                    • Primitiva do Logaritmo:
                      ∫ (1/x) dx = ln|x| + C
                    
                    • Primitivas Exponenciais:
                      ∫ eˣ dx = eˣ + C
                      ∫ aˣ dx = aˣ / ln(a) + C
                    
                    • Primitivas Trigonométricas:
                      ∫ cos(x) dx = sin(x) + C  (atenção: sinal POSITIVO!)
                      ∫ sin(x) dx = -cos(x) + C (atenção: sinal NEGATIVO!)
                      ∫ sec²(x) dx = tan(x) + C
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Cuidado com os sinais trigonométricos: derivada de sin é cos, mas integral de sin é -cos!",
                    "A integral de 1/x sempre leva módulo no argumento: ln|x|."
                ),
                solutionExplanation = "Integrais imediatas que desfazem as derivadas fundamentais.",
                xpReward = 35,
                imageResId = R.drawable.grafico_integral_area,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l21_1",
                        title = "Integral Trigonométrica Imediata",
                        problemStatement = """Calcule a integral indefinida: \[ \int (3\cos(x) - 4\sin(x)) \, dx \]""",
                        options = listOf(
                            """3\sin(x) + 4\cos(x) + C""",
                            """-3\sin(x) - 4\cos(x) + C""",
                            """3\sin(x) - 4\cos(x) + C""",
                            """-3\sin(x) + 4\cos(x) + C""",
                            """3\cos(x) + 4\sin(x) + C"""
                        ),
                        correctIndex = 0,
                        hint = "∫ cos(x) dx = sin(x) e ∫ sin(x) dx = -cos(x). Menos com menos dá mais!",
                        stepByStepSolution = """
                            Passo 1: 3 ∫ cos(x) dx = 3 sin(x).
                            Passo 2: -4 ∫ sin(x) dx = -4 (-cos(x)) = +4 cos(x).
                            Conclusão: 3 sin(x) + 4 cos(x) + C.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l22",
                unitId = "calc_u_integrals",
                title = "Aula 4: Propriedades da Linearidade na Integral",
                conceptTitle = "Integração Termo a Termo e Fatores Constantes",
                explanation = """
                    Assim como o limite e a derivada, a integral é um OPERADOR LINEAR:
                    
                    1. Constante multiplicando 'pula' para fora da integral:
                       ∫ c * f(x) dx = c * ∫ f(x) dx
                    
                    2. A integral da soma (ou subtração) é a soma das integrais:
                       ∫ [f(x) ± g(x)] dx = ∫ f(x) dx ± ∫ g(x) dx
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Quebre polinômios termo a termo e integre cada potência individualmente."
                ),
                solutionExplanation = "Aplicação de linearidade direta.",
                xpReward = 35,
                imageResId = R.drawable.grafico_integral_area,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l22_1",
                        title = "Linearidade Completa (Exercício 50101)",
                        problemStatement = """Calcule a integral indefinida aplicando a linearidade e as regras imediatas: \[ \int \left( 4x^3 - 3\cos(x) + \frac{5}{x} \right) dx \]""",
                        options = listOf(
                            """x^4 - 3\sin(x) + 5\ln|x| + C""",
                            """12x^2 + 3\sin(x) - \frac{5}{x^2} + C""",
                            """x^4 + 3\sin(x) + 5\ln|x| + C""",
                            """\frac{4x^4}{3} - 3\cos(x) + 5x + C""",
                            """x^4 - 3\sin(x) + \frac{5}{x^2} + C"""
                        ),
                        correctIndex = 0,
                        hint = "Integre termo a termo: 4(x⁴/4) - 3(sin x) + 5(ln|x|) + C.",
                        stepByStepSolution = """
                            Passo 1: 4 ∫ x³ dx = 4(x⁴/4) = x⁴.
                            Passo 2: -3 ∫ cos(x) dx = -3 sin(x).
                            Passo 3: 5 ∫ (1/x) dx = 5 ln|x|.
                            Resultado Final: x⁴ - 3 sin(x) + 5 ln|x| + C.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l23",
                unitId = "calc_u_integrals",
                title = "Aula 5: Técnica de Substituição (Regra da Cadeia ao Contrário)",
                conceptTitle = "A Técnica da Mudança de Variável (u-substituição)",
                explanation = """
                    Quando você tem uma função composta multiplicada pela derivada da função interna, usa-se a MUDANÇA DE VARIÁVEL (u-substituição).
                    
                    Fórmula de Substituição:
                    ∫ f(g(x)) * g'(x) dx = ∫ f(u) du   (onde u = g(x) e du = g'(x) dx)
                    
                    • Roteiro Infalível:
                      1. Identifique a 'função de dentro' mais complicada e chame de u.
                      2. Calcule du = u'(x) dx.
                      3. Veja se du (ou um múltiplo constante dele) está sobrando no resto da integral.
                      4. Substitua tudo para a variável u, resolva a integral imediata e depois devolva x!
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Procure pares onde uma parte é a função u e a outra parte é a sua derivada du.",
                    "Não esqueça de substituir o dx por du/u'(x)."
                ),
                solutionExplanation = "A u-substituição transforma integrais complexas em integrais imediatas.",
                xpReward = 40,
                imageResId = R.drawable.grafico_integral_area,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l23_1",
                        title = "Substituição Trigonométrica (Exercício 50201)",
                        problemStatement = """Calcule pelo método da substituição: \[ \int 2x \cos(x^2) \, dx \]""",
                        options = listOf(
                            """\sin(x^2) + C""",
                            """-\sin(x^2) + C""",
                            """2x\sin(x^2) + C""",
                            """\cos(x^2) + C""",
                            """\frac{1}{2}\sin(x^2) + C"""
                        ),
                        correctIndex = 0,
                        hint = "Chame u = x², então du = 2x dx.",
                        stepByStepSolution = """
                            Passo 1: Defina u = x² => du = 2x dx.
                            Passo 2: A integral vira ∫ cos(u) du.
                            Passo 3: ∫ cos(u) du = sin(u) + C.
                            Passo 4: Retornando a x: sin(x²) + C.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l24",
                unitId = "calc_u_integrals",
                title = "Aula 6: Integração por Partes (Regra do Produto ao Contrário)",
                conceptTitle = "A Fórmula: ∫ u dv = u*v - ∫ v du (Regra LIATE)",
                explanation = """
                    Quando o integrando é a multiplicação de duas funções de naturezas diferentes (por exemplo, x * eˣ ou x * cos(x)), usamos a INTEGRAÇÃO POR PARTES.
                    
                    Fórmula Mestra:
                    ∫ u dv = u * v - ∫ v du
                    
                    • Como escolher quem é 'u'? (Prioridade LIATE):
                      L - Logaritmos [ln(x)]
                      I - Inversas trigonométricas [arctan(x), arcsin(x)]
                      A - Algébricas [x, x², x³]
                      T - Trigonométricas [sin(x), cos(x)]
                      E - Exponenciais [eˣ, 2ˣ]
                      Quem vier primeiro no LIATE ganha o papel de u! O que sobrou vira dv.
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Memorize a frase: 'Um Dia Vi Uma Vaca Vestida De Uniforme' (u dv = u v - v du).",
                    "A ordem LIATE sempre define quem é o melhor u para ser derivado."
                ),
                solutionExplanation = "Integração por partes reduz integrais de produtos a formas simples.",
                xpReward = 40,
                imageResId = R.drawable.grafico_integral_area,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l24_1",
                        title = "Integração por Partes Clássica (Exercício 50202)",
                        problemStatement = """Calcule utilizando a fórmula de integração por partes: \[ \int x e^x \, dx \]""",
                        options = listOf(
                            """e^x(x - 1) + C""",
                            """e^x(x + 1) + C""",
                            """x^2 e^x + C""",
                            """\frac{x^2}{2}e^x + C""",
                            """e^x - x + C"""
                        ),
                        correctIndex = 0,
                        hint = "Pelo LIATE: u = x (algébrica) => du = dx; dv = eˣ dx => v = eˣ.",
                        stepByStepSolution = """
                            Passo 1: u = x => du = dx.
                            Passo 2: dv = eˣ dx => v = eˣ.
                            Passo 3: ∫ u dv = u*v - ∫ v du = x eˣ - ∫ eˣ dx = x eˣ - eˣ + C.
                            Passo 4: Fatorando eˣ: eˣ(x - 1) + C.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l25",
                unitId = "calc_u_integrals",
                title = "Aula 7: Frações Parciais e Substituição Trigonométrica",
                conceptTitle = "Decomposição de Funções Racionais P(x)/Q(x)",
                explanation = """
                    Quando temos frações racionais cujo denominador pode ser fatorado em termos lineares distintos (x - r₁)(x - r₂), decompomos em frações parciais simples:
                    
                    Exemplo:
                    1 / [(x - 1)(x + 1)] = [A / (x - 1)] + [B / (x + 1)]
                    
                    Multiplicando pelo denominador comum:
                    1 = A(x + 1) + B(x - 1)
                    
                    Substituindo raízes:
                    • Se x = 1: 1 = A(2) => A = 1/2
                    • Se x = -1: 1 = B(-2) => B = -1/2
                    
                    Cada fração simples resulta em um logaritmo natural imediato!
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Fatore o denominador primeiro antes de montar as constantes A e B.",
                    "Diferença de quadrados: x² - a² = (x - a)(x + a)."
                ),
                solutionExplanation = "Frações parciais transformam quocientes polinomiais em somas de logaritmos.",
                xpReward = 40,
                imageResId = R.drawable.grafico_integral_area,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l25_1",
                        title = "Frações Parciais (Exercício 50301)",
                        problemStatement = """Calcule utilizando a técnica de decomposição em frações parciais: \[ \int \frac{1}{x^2 - 1} \, dx \]""",
                        options = listOf(
                            """\frac{1}{2}\ln\left|\frac{x - 1}{x + 1}\right| + C""",
                            """\ln|x^2 - 1| + C""",
                            """\arctan(x) + C""",
                            """\frac{1}{2}\ln\left|\frac{x + 1}{x - 1}\right| + C""",
                            """\ln|x - 1| - \ln|x + 1| + C"""
                        ),
                        correctIndex = 0,
                        hint = "1/(x² - 1) = (1/2)/(x-1) - (1/2)/(x+1). Use ln(A) - ln(B) = ln(A/B).",
                        stepByStepSolution = """
                            Passo 1: Fatoração: x² - 1 = (x - 1)(x + 1).
                            Passo 2: Decomposição: 1/[(x-1)(x+1)] = 1/2/(x-1) - 1/2/(x+1).
                            Passo 3: Integral: (1/2) ln|x - 1| - (1/2) ln|x + 1| + C.
                            Passo 4: Propriedade dos logaritmos: (1/2) ln|(x-1)/(x+1)| + C.
                        """.trimIndent()
                    )
                )
            ),
            Lesson(
                id = "calc_l26",
                unitId = "calc_u_integrals",
                title = "Aula 8: A Integral Definida e o Teorema Fundamental (TFC)",
                conceptTitle = "Cálculo de Área e a Relação [F(b) - F(a)]",
                explanation = """
                    A Integral Definida calcula o ACÚMULO TOTAL ou a ÁREA LÍQUIDA sob o gráfico de f(x) entre os limites a e b.
                    
                    O Teorema Fundamental do Cálculo (TFC - Parte 2):
                    Se f é contínua em [a, b] e F é qualquer primitiva de f:
                    ∫_{a}^{b} f(x) dx = F(b) - F(a) = [F(x)]_{a}^{b}
                    
                    • Nota Fundamental:
                      Na integral definida, NÃO precisamos somar a constante + C, pois:
                      [F(b) + C] - [F(a) + C] = F(b) - F(a)!
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Calcule a primitiva F(x), aplique no limite superior b, depois no limite inferior a e subtraia: F(b) - F(a).",
                    "Integrais definidas resultam em um NÚMERO real, não em uma função."
                ),
                solutionExplanation = "O TFC estabelece a ligação definitiva entre a antiderivada e o cálculo de áreas.",
                xpReward = 45,
                imageResId = R.drawable.grafico_integral_area,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_calc_l26_1",
                        title = "Área sob Curva pelo TFC (Exercício 50401)",
                        problemStatement = """Calcule o valor numérico da integral definida aplicando o Teorema Fundamental do Cálculo: \[ \int_{0}^{2} 3x^2 \, dx \]""",
                        options = listOf(
                            """8""",
                            """6""",
                            """12""",
                            """16""",
                            """24"""
                        ),
                        correctIndex = 0,
                        hint = "A primitiva de 3x² é x³. Calcule F(2) - F(0) = 2³ - 0³.",
                        stepByStepSolution = """
                            Passo 1: Primitiva F(x) = ∫ 3x² dx = x³.
                            Passo 2: Limite superior: F(2) = 2³ = 8.
                            Passo 3: Limite inferior: F(0) = 0³ = 0.
                            Passo 4: TFC: F(2) - F(0) = 8 - 0 = 8.
                        """.trimIndent()
                    )
                )
            )
        )
    )

    val calculusIntegralsFinalTest = KahootModule(
        id = "test_calculus_integrals",
        tier = DifficultyTier.BASIC,
        title = "Arena de Desafio Final: Módulo de Integrais e TFC",
        description = "10 questões decisivas de Integração: regras imediatas, linearidade, substituição, integração por partes, frações parciais e cálculo de áreas pelo Teorema Fundamental do Cálculo.",
        category = "Matemática",
        subject = "Cálculo I",
        questions = listOf(
            KahootQuestion(
                id = "ci_1",
                question = "Qual é a relação que define a integral indefinida de f(x)?",
                codeSnippet = "∫ f(x) dx",
                options = listOf(
                    "F(x) + C, onde F'(x) = f(x)",
                    "f'(x) + C",
                    "F'(x) + C, onde f'(x) = F(x)",
                    "f(x) * x + C"
                ),
                correctIndex = 0,
                explanation = "A integral indefinida é a antiderivação: F'(x) = f(x).",
                conceptTag = "Definição de Integral",
                recommendedLessonId = "calc_l19",
                recommendedLessonTitle = "Aula 1: O que significa 'Integrar'?"
            ),
            KahootQuestion(
                id = "ci_2",
                question = "Calcule a integral de x⁴ pela regra da potência.",
                codeSnippet = "∫ x⁴ dx",
                options = listOf(
                    "x⁵ / 5 + C",
                    "4x³ + C",
                    "5x⁵ + C",
                    "x³ / 3 + C"
                ),
                correctIndex = 0,
                explanation = "Soma 1 ao expoente (4+1=5) e divide por 5: x⁵/5 + C.",
                conceptTag = "Regra da Potência",
                recommendedLessonId = "calc_l20",
                recommendedLessonTitle = "Aula 2: A Mecânica Básica: Regra da Potência"
            ),
            KahootQuestion(
                id = "ci_3",
                question = "Qual é o valor de ∫ 1/x dx para x ≠ 0?",
                codeSnippet = "∫ 1/x dx",
                options = listOf(
                    "ln|x| + C",
                    "-1/x² + C",
                    "eˣ + C",
                    "x + C"
                ),
                correctIndex = 0,
                explanation = "A função cuja derivada é 1/x é o logaritmo natural ln|x|.",
                conceptTag = "Integral do Logaritmo",
                recommendedLessonId = "calc_l21",
                recommendedLessonTitle = "Aula 3: Exponenciais, Logaritmos e Funções Trigonométricas"
            ),
            KahootQuestion(
                id = "ci_4",
                question = "Calcule a integral de cos(x).",
                codeSnippet = "∫ cos(x) dx",
                options = listOf(
                    "sin(x) + C",
                    "-sin(x) + C",
                    "tan(x) + C",
                    "-cos(x) + C"
                ),
                correctIndex = 0,
                explanation = "A derivada de sin(x) é cos(x), portanto ∫ cos(x) dx = sin(x) + C positivo.",
                conceptTag = "Trigonométrica Imediata",
                recommendedLessonId = "calc_l21",
                recommendedLessonTitle = "Aula 3: Exponenciais, Logaritmos e Funções Trigonométricas"
            ),
            KahootQuestion(
                id = "ci_5",
                question = "Qual é o resultado da substituição u = x² na integral ∫ 2x cos(x²) dx?",
                codeSnippet = "u = x²  =>  du = 2x dx",
                options = listOf(
                    "sin(x²) + C",
                    "-sin(x²) + C",
                    "cos(x²) + C",
                    "2 sin(x²) + C"
                ),
                correctIndex = 0,
                explanation = "∫ cos(u) du = sin(u) + C = sin(x²) + C.",
                conceptTag = "Técnica de Substituição",
                recommendedLessonId = "calc_l23",
                recommendedLessonTitle = "Aula 5: Técnica de Substituição"
            ),
            KahootQuestion(
                id = "ci_6",
                question = "Na integração por partes ∫ u dv, qual é a fórmula correta?",
                codeSnippet = "∫ u dv",
                options = listOf(
                    "u*v - ∫ v du",
                    "u*v + ∫ v du",
                    "u'*v - u*v'",
                    "u/v - ∫ du"
                ),
                correctIndex = 0,
                explanation = "Fórmula da integração por partes: ∫ u dv = u*v - ∫ v du.",
                conceptTag = "Integração por Partes",
                recommendedLessonId = "calc_l24",
                recommendedLessonTitle = "Aula 6: Integração por Partes"
            ),
            KahootQuestion(
                id = "ci_7",
                question = "Qual regra mnemônica indica a prioridade para a escolha de 'u' na integração por partes?",
                codeSnippet = "Prioridade de Escolha de u",
                options = listOf(
                    "LIATE (Logarítmica, Inversa, Algébrica, Trigonométrica, Exponencial)",
                    "PEMDAS (Parênteses, Expoentes, Multiplicação, Divisão, Adição, Subtração)",
                    "L'Hôpital",
                    "TVI (Teorema do Valor Intermediário)"
                ),
                correctIndex = 0,
                explanation = "A sigla LIATE orienta a ordem preferencial para derivar u.",
                conceptTag = "Regra LIATE",
                recommendedLessonId = "calc_l24",
                recommendedLessonTitle = "Aula 6: Integração por Partes"
            ),
            KahootQuestion(
                id = "ci_8",
                question = "Na decomposição de 1/(x² - 1) em frações parciais, quais são os termos resultantes?",
                codeSnippet = "1 / [(x - 1)(x + 1)]",
                options = listOf(
                    "(1/2) / (x - 1) - (1/2) / (x + 1)",
                    "1 / (x - 1) + 1 / (x + 1)",
                    "(1/2) / (x² - 1)",
                    "ln|x - 1| * ln|x + 1|"
                ),
                correctIndex = 0,
                explanation = "Para x=1, A=1/2; para x=-1, B=-1/2.",
                conceptTag = "Frações Parciais",
                recommendedLessonId = "calc_l25",
                recommendedLessonTitle = "Aula 7: Frações Parciais"
            ),
            KahootQuestion(
                id = "ci_9",
                question = "Pelo Teorema Fundamental do Cálculo, quanto vale ∫_{1}^{3} 2x dx?",
                codeSnippet = "∫_{1}^{3} 2x dx = [x²]_{1}^{3}",
                options = listOf(
                    "8",
                    "9",
                    "6",
                    "4"
                ),
                correctIndex = 0,
                explanation = "Primitiva x². F(3) - F(1) = 3² - 1² = 9 - 1 = 8.",
                conceptTag = "Teorema Fundamental do Cálculo",
                recommendedLessonId = "calc_l26",
                recommendedLessonTitle = "Aula 8: A Integral Definida e o Teorema Fundamental"
            ),
            KahootQuestion(
                id = "ci_10",
                question = "Por que não é necessário incluir a constante + C ao resolver uma integral definida?",
                codeSnippet = "[F(b) + C] - [F(a) + C]",
                options = listOf(
                    "Porque as constantes C se cancelam na subtração: F(b) + C - (F(a) + C) = F(b) - F(a)",
                    "Porque a constante sempre vale zero por convenção",
                    "Porque a integral definida só aceita funções estritamente positivas",
                    "Porque o Teorema Fundamental do Cálculo proíbe constantes"
                ),
                correctIndex = 0,
                explanation = "A constante de integração se cancela naturalmente no cálculo de F(b) - F(a).",
                conceptTag = "Constante no TFC",
                recommendedLessonId = "calc_l26",
                recommendedLessonTitle = "Aula 8: A Integral Definida e o Teorema Fundamental"
            )
        )
    )

    // Backward compatibility aliases
    val basicMathUnit get() = BasicMathCurriculum.basicMathUnit
    val basicMathFinalTest get() = BasicMathCurriculum.basicMathFinalTest

    val calculusFinalTest = calculusLimitsFinalTest

    val calculusModules = listOf("Matemática Básica", "Limites", "Derivadas", "Integrais")

    val calculusUnits get() = listOf(basicMathUnit, limitsUnit, derivativesUnit, integralsUnit)

    fun getUnitsForModule(moduleName: String): List<LearningUnit> {
        return when (moduleName) {
            "Matemática Básica" -> listOf(basicMathUnit)
            "Limites" -> listOf(limitsUnit)
            "Derivadas" -> listOf(derivativesUnit)
            "Integrais" -> listOf(integralsUnit)
            else -> emptyList()
        }
    }

    fun getAllLessons(): List<Lesson> {
        return listOf(basicMathUnit, limitsUnit, derivativesUnit, integralsUnit).flatMap { it.lessons }
    }

    fun findLesson(id: String): Lesson? {
        return getAllLessons().find { it.id == id }
    }

    fun findNextLesson(currentId: String): Lesson? {
        val all = getAllLessons()
        val index = all.indexOfFirst { it.id == currentId }
        return if (index >= 0 && index < all.size - 1) all[index + 1] else null
    }
}
