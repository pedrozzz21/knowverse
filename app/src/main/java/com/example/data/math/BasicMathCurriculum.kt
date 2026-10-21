package com.example.data.math

import com.example.data.model.DifficultyTier
import com.example.data.model.KahootModule
import com.example.data.model.KahootQuestion
import com.example.data.model.LearningUnit
import com.example.data.model.Lesson
import com.example.data.model.LessonType
import com.example.data.model.MathExercise

/**
 * Currículo completo do Ciclo de Matemática Básica (ENEM).
 * Categoria: Matemática
 * Módulo: Matemática Básica
 * Submódulos:
 *  1. Frações e Decimais (mb_l1)
 *  2. MMC e MDC (mb_l2)
 *  3. Potenciação e Radiciação (mb_l3)
 *  4. Notação Científica e Ordem de Grandeza (mb_l4)
 *  5. Conversão de Unidades (mb_l5)
 *  6. Razão, Proporção e Grandezas (mb_l6)
 *  7. Regra de Três Simples e Composta (mb_l7)
 *  8. Escalas Cartográficas (mb_l8)
 *  9. Porcentagem - Aumentos e Descontos Sucessivos (mb_l9)
 */
object BasicMathCurriculum {

    val basicMathUnit = LearningUnit(
        id = "math_u_basic",
        unitNumber = 1,
        tier = DifficultyTier.BASIC,
        title = "Matemática Básica (Ciclo ENEM)",
        description = "Os 9 pilares essenciais: Frações, Decimais, MMC/MDC, Potências, Notação Científica, Unidades, Razão, Regra de Três, Escalas e Porcentagem.",
        category = "Matemática",
        subject = "Matemática Básica",
        lessons = listOf(
            Lesson(
                id = "mb_l1",
                unitId = "math_u_basic",
                title = "Aula 1: Frações e Decimais",
                conceptTitle = "Operações com Frações e Decimais no ENEM",
                explanation = """
                    As operações aritméticas entre frações e decimais formam a base indispensável para a prova de Matemática do ENEM.
                    
                    • Denominadores iguais: somam-se ou subtraem-se os numeradores diretamente.
                    • Denominadores diferentes: encontra-se o MMC para unificar a base fracionária.
                    • Multiplicação de frações: numerador × numerador e denominador × denominador.
                    • Divisão de frações: conserva a primeira e multiplica pelo inverso da segunda.
                    • Números decimais: alinhar vírgula sob vírgula na adição/subtração e contar casas decimais no produto.
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Converta frações com denomidadores fáceis (1/4 = 0,25; 1/2 = 0,5; 3/4 = 0,75) para decimais para acelerar o cálculo.",
                    "Na divisão de decimais, iguale o número de casas após a vírgula antes de dividir."
                ),
                solutionExplanation = "Frações e decimais são duas faces do mesmo número racional. Igualar a base é a chave para o cálculo correto.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l1_1",
                        title = "Operações Mistas com Frações e Decimais",
                        problemStatement = """Um recipiente continha 2,75 litros de água. Inicialmente, foram retirados \( \frac{3}{4} \) de litro dessa água e, em seguida, adicionou-se 1,2 litro ao volume restante. Qual é o volume final de água no recipiente?""",
                        options = listOf(
                            "2,30 litros",
                            "3,20 litros",
                            "3,95 litros",
                            "1,80 litros",
                            "3,50 litros"
                        ),
                        correctIndex = 1,
                        hint = "Converta 3/4 para decimal dividindo 3 por 4: 3/4 = 0,75 L.",
                        stepByStepSolution = """
                            Passo 1: Converter a fração 3/4 para número decimal:
                            \[ \frac{3}{4} = 3 \div 4 = 0,75\text{ litros} \]
                            Passo 2: Subtrair a quantidade retirada do volume inicial:
                            \[ 2,75 - 0,75 = 2,00\text{ litros} \]
                            Passo 3: Adicionar o volume inserido posteriormente:
                            \[ 2,00 + 1,20 = 3,20\text{ litros} \]
                            Conclusão: O volume final no recipiente é exatamente 3,20 litros.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l1_2",
                        title = "Soma de Frações com Denominadores Diferentes",
                        problemStatement = """Em uma receita culinária industrial, misturam-se \( \frac{2}{3} \) de quilograma de farinha de trigo com \( \frac{5}{6} \) de quilograma de amido de milho. Qual é a massa total da mistura em quilogramas na forma irredutível?""",
                        options = listOf(
                            "7/9 kg",
                            "3/2 kg",
                            "7/6 kg",
                            "10/9 kg",
                            "5/3 kg"
                        ),
                        correctIndex = 1,
                        hint = "O MMC entre 3 e 6 é 6. Obtenha a fração equivalente 2/3 = 4/6 e some.",
                        stepByStepSolution = """
                            Passo 1: Encontrar o MMC entre 3 e 6: MMC(3, 6) = 6.
                            Passo 2: Converter 2/3 para base 6:
                            \[ \frac{2}{3} = \frac{2 \times 2}{3 \times 2} = \frac{4}{6} \]
                            Passo 3: Somar os numeradores:
                            \[ \frac{4}{6} + \frac{5}{6} = \frac{9}{6} \]
                            Passo 4: Simplificar dividindo numerador e denominador por 3:
                            \[ \frac{9 \div 3}{6 \div 3} = \frac{3}{2}\text{ kg} = 1,5\text{ kg} \]
                            Conclusão: A massa total é 3/2 kg (ou 1,5 kg).
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l2",
                unitId = "math_u_basic",
                title = "Aula 2: MMC e MDC (Múltiplos e Divisores)",
                conceptTitle = "Sincronicidade vs Divisão em Partes Iguais",
                explanation = """
                    • MMC (Mínimo Múltiplo Comum): menor número inteiro positivo múltiplo simultâneo.
                      Aplica-se a problemas de sincronicidade, ciclos que se repetem ou encontros periódicos no futuro.
                    
                    • MDC (Máximo Divisor Comum): maior número inteiro que divide exatamente dois ou mais números.
                      Aplica-se a problemas de divisão em partes iguais, com o maior tamanho possível e sem sobras.
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Pergunta de 'quando voltam a se encontrar juntos' é sempre MMC.",
                    "Pergunta de 'cortar no maior tamanho possível sem sobras' é sempre MDC."
                ),
                solutionExplanation = "O MMC busca o ponto futuro de convergência periódica; o MDC busca a escala máxima comum de partição.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l2_1",
                        title = "Problema de Sincronicidade em Pista Circular (MMC)",
                        problemStatement = """Dois corredores, A e B, treinam em uma pista circular. O corredor A completa uma volta em 12 minutos, enquanto o corredor B completa em 18 minutos. Se ambos partirem juntos do mesmo ponto às 08h00, a que horas eles se encontrarão novamente no ponto de partida?""",
                        options = listOf(
                            "08h24",
                            "08h30",
                            "08h36",
                            "08h48",
                            "09h12"
                        ),
                        correctIndex = 2,
                        hint = "Calcule o MMC entre 12 e 18 por fatoração prima simultânea.",
                        stepByStepSolution = """
                            Passo 1: Fatoração simultânea de 12 e 18:
                            12, 18 | 2
                             6,  9 | 2
                             3,  9 | 3
                             1,  3 | 3
                             1,  1 | MMC = 2² × 3² = 4 × 9 = 36 minutos.
                            Passo 2: Adicionar 36 minutos ao horário de saída (08h00):
                            08h00 + 36 min = 08h36.
                            Conclusão: Os atletas voltam a se encontrar no ponto inicial às 08h36.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l2_2",
                        title = "Corte Máximo de Fitas sem Desperdício (MDC)",
                        problemStatement = """Um comerciante possui três rolos de fita com comprimentos de 120 cm, 180 cm e 240 cm. Ele deseja cortar todas as fitas em pedaços iguais, de modo que não haja sobra e que o tamanho de cada pedaço seja o maior possível. Qual deve ser o comprimento de cada pedaço e quantos pedaços serão obtidos no total?""",
                        options = listOf(
                            "30 cm e 18 pedaços",
                            "40 cm e 12 pedaços",
                            "60 cm e 9 pedaços",
                            "60 cm e 11 pedaços",
                            "80 cm e 7 pedaços"
                        ),
                        correctIndex = 2,
                        hint = "O MDC dá o tamanho do pedaço. Divida cada fita por esse MDC para obter o número de pedaços.",
                        stepByStepSolution = """
                            Passo 1: Fatoração prima:
                            120 = 2³ × 3 × 5
                            180 = 2² × 3² × 5
                            240 = 2⁴ × 3 × 5
                            MDC = 2² × 3¹ × 5¹ = 4 × 3 × 5 = 60 cm (tamanho de cada pedaço).
                            Passo 2: Total de pedaços:
                            120 ÷ 60 = 2
                            180 ÷ 60 = 3
                            240 ÷ 60 = 4
                            Total = 2 + 3 + 4 = 9 pedaços.
                            Conclusão: Cada pedaço mede 60 cm e obtêm-se 9 pedaços.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l3",
                unitId = "math_u_basic",
                title = "Aula 3: Potenciação e Radiciação",
                conceptTitle = "Propriedades Operatórias e Expoente Fracionário",
                explanation = """
                    • Multiplicação de mesma base: conserva a base e soma os expoentes: \( a^m \cdot a^n = a^{m+n} \).
                    • Divisão de mesma base: conserva a base e subtrai os expoentes: \( a^m \div a^n = a^{m-n} \).
                    • Potência de potência: multiplica os expoentes: \( (a^m)^n = a^{m \cdot n} \).
                    • Expoente negativo: inverte a base: \( a^{-n} = \frac{1}{a^n} \).
                    • Expoente fracionário (Radiciação): \( a^{\frac{m}{n}} = \sqrt[n]{a^m} \).
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Em expressões com bases compostas (4, 8, 16, 27), fature tudo para bases primas (2 e 3).",
                    "Quem está 'na sombra' (numerador da fração) vai para a potência interna; quem está 'no sol' (denominador) vira o índice da raiz."
                ),
                solutionExplanation = "A decomposição em bases primas simplifica drasticamente qualquer expressão algébrica exponencial.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l3_1",
                        title = "Simplificação Algébrica com Potências de Base 2",
                        problemStatement = """Simplifique a expressão numérica E = (2⁵ × 8⁻²) / 4⁻³ em uma única potência e determine seu valor decimal inteiro:""",
                        options = listOf(
                            "8",
                            "16",
                            "32",
                            "64",
                            "128"
                        ),
                        correctIndex = 2,
                        hint = "Fatore 8 = 2³ e 4 = 2². Aplique potência de potência.",
                        stepByStepSolution = """
                            Passo 1: Converter todas as bases para 2:
                            8⁻² = (2³)⁻² = 2⁻⁶
                            4⁻³ = (2²)⁻³ = 2⁻⁶
                            Passo 2: Montar a expressão unificada:
                            \[ E = \frac{2^5 \times 2^{-6}}{2^{-6}} \]
                            Passo 3: Cancelar o fator idêntico 2⁻⁶ no numerador e denominador:
                            \[ E = 2^5 = 32 \]
                            Conclusão: O valor da expressão é 2⁵ = 32.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l3_2",
                        title = "Cálculo com Expoente Fracionário",
                        problemStatement = """Qual é o valor numérico exato da expressão com expoente fracionário \( 27^{\frac{2}{3}} + 16^{\frac{3}{4}} \) ?""",
                        options = listOf(
                            "15",
                            "17",
                            "19",
                            "21",
                            "25"
                        ),
                        correctIndex = 1,
                        hint = "Reescreva 27 = 3³ e 16 = 2⁴ antes de multiplicar os expoentes.",
                        stepByStepSolution = """
                            Passo 1: Fatorar 27 e 16:
                            \[ 27^{\frac{2}{3}} = (3^3)^{\frac{2}{3}} = 3^{3 \times \frac{2}{3}} = 3^2 = 9 \]
                            \[ 16^{\frac{3}{4}} = (2^4)^{\frac{3}{4}} = 2^{4 \times \frac{3}{4}} = 2^3 = 8 \]
                            Passo 2: Somar os resultados obtidos:
                            \[ 9 + 8 = 17 \]
                            Conclusão: O valor exato é 17.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l4",
                unitId = "math_u_basic",
                title = "Aula 4: Notação Científica e Ordem de Grandeza",
                conceptTitle = "Representação Exponencial e Magnitude Física",
                explanation = """
                    • Notação Científica: escrita na forma \( a \times 10^n \), onde \( 1 \le a < 10 \) e \( n \in \mathbb{Z} \).
                    • Deslocamento da vírgula:
                      - Deslocar para a esquerda: o expoente \( n \) AUMENTA.
                      - Deslocar para a direita: o expoente \( n \) DIMINUI.
                    • Ordem de Grandeza: compara-se a mantissa \( a \) com o corte oficial \( \sqrt{10} \approx 3,16 \):
                      - Se \( a < 3,16 \): OG = \( 10^n \).
                      - Se \( a \ge 3,16 \): OG = \( 10^{n+1} \).
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "A mantissa 'a' deve sempre ser maior ou igual a 1 e estritamente menor que 10.",
                    "No ENEM, o critério oficial de ordem de grandeza adota √10 ≈ 3,16 como valor de transição."
                ),
                solutionExplanation = "A notação científica padroniza grandezas extremas e a ordem de grandeza indica a escala de potência de 10 mais representativa.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l4_1",
                        title = "Conversão para Notação Científica",
                        problemStatement = """A distância média entre a Terra e o Sol é de aproximadamente 149.600.000 km. Escrita na forma padrão de notação científica, essa distância é expressa por:""",
                        options = listOf(
                            "14,96 × 10⁷ km",
                            "1,496 × 10⁸ km",
                            "1,496 × 10⁶ km",
                            "0,1496 × 10⁹ km",
                            "149,6 × 10⁶ km"
                        ),
                        correctIndex = 1,
                        hint = "A vírgula deve ficar após o primeiro algarismo não-nulo (1). Conte as casas deslocadas para a esquerda.",
                        stepByStepSolution = """
                            Passo 1: Posicionar a vírgula de modo que a mantissa pertença ao intervalo [1, 10[: mantissa = 1,496.
                            Passo 2: Contar as casas decimais deslocadas para a esquerda a partir do final do número:
                            149.600.000 tem 8 casas decimais deslocadas.
                            Passo 3: Como o deslocamento foi para a esquerda, o expoente é positivo (+8).
                            Conclusão: 1,496 × 10⁸ km.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l4_2",
                        title = "Determinação da Ordem de Grandeza",
                        problemStatement = """O raio de uma bactéria filamentosa mede 4,5 × 10⁻⁶ metros. Adotando o critério padrão do ENEM com corte em √10 ≈ 3,16, qual é a ordem de grandeza desse raio?""",
                        options = listOf(
                            "10⁻⁷ m",
                            "10⁻⁶ m",
                            "10⁻⁵ m",
                            "10⁻⁴ m",
                            "10⁻⁸ m"
                        ),
                        correctIndex = 2,
                        hint = "Como a mantissa é 4,5, compare com 3,16: 4,5 >= 3,16, logo soma-se 1 ao expoente.",
                        stepByStepSolution = """
                            Passo 1: Identificar a mantissa e o expoente:
                            a = 4,5 e n = -6.
                            Passo 2: Comparar com o corte √10 ≈ 3,16:
                            Como 4,5 ≥ 3,16, a potência de 10 mais próxima é a superior (10^(n+1)).
                            Passo 3: Somar 1 ao expoente:
                            -6 + 1 = -5.
                            Conclusão: A ordem de grandeza é 10⁻⁵ m.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l5",
                unitId = "math_u_basic",
                title = "Aula 5: Conversão de Unidades e Capacidade",
                conceptTitle = "Sistema Métrico, Volumes e Vazão",
                explanation = """
                    • Comprimento: cada salto multiplica ou divide por 10.
                    • Área (\(\text{m}^2\)): cada salto multiplica ou divide por 100 (\(10^2\)).
                    • Volume (\(\text{m}^3\)): cada salto multiplica ou divide por 1.000 (\(10^3\)).
                    • Relações fundamentais de capacidade:
                      - \( 1\text{ dm}^3 = 1\text{ litro} \)
                      - \( 1\text{ m}^3 = 1.000\text{ litros} \)
                      - \( 1\text{ cm}^3 = 1\text{ mL} \)
                    • Velocidade:
                      - De km/h para m/s: divide por 3,6.
                      - De m/s para km/h: multiplica por 3,6.
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Memorize: 1 metro cúbico equivale a uma caixa d'água de 1.000 litros.",
                    "72 km/h dividido por 3,6 é igual a 20 m/s."
                ),
                solutionExplanation = "Compreender as dimensões espaciais (expoentes 1, 2 e 3) evita erros clássicos de potências de 10.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l5_1",
                        title = "Conversão de Capacidade para Metros Cúbicos",
                        problemStatement = """Um reservatório de um condomínio possui capacidade total de 18.000 litros. Qual é o volume ocupado por essa água em metros cúbicos (\(\text{m}^3\))?""",
                        options = listOf(
                            "1,8 m³",
                            "18 m³",
                            "180 m³",
                            "1.800 m³",
                            "0,18 m³"
                        ),
                        correctIndex = 1,
                        hint = "Lembre-se da relação: 1 m³ = 1.000 L. Divida a quantidade de litros por 1.000.",
                        stepByStepSolution = """
                            Passo 1: Estabelecer a equivalência padrão do SI:
                            1 m³ = 1.000 litros.
                            Passo 2: Converter 18.000 L para m³:
                            \[ V = \frac{18.000}{1.000} = 18\text{ m}^3 \]
                            Conclusão: O volume total é de 18 m³.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l5_2",
                        title = "Conversão de Velocidade Escalar Média",
                        problemStatement = """Um veículo trafega em uma rodovia com velocidade constante de 108 km/h. Qual é essa velocidade expressa na unidade padrão do Sistema Internacional (m/s)?""",
                        options = listOf(
                            "25 m/s",
                            "30 m/s",
                            "35 m/s",
                            "40 m/s",
                            "45 m/s"
                        ),
                        correctIndex = 1,
                        hint = "Para converter de km/h para m/s, divida o valor por 3,6.",
                        stepByStepSolution = """
                            Passo 1: Aplicar a regra de conversão de velocidade:
                            1 km = 1.000 m e 1 h = 3.600 s => 1.000 / 3.600 = 1 / 3,6.
                            Passo 2: Dividir 108 por 3,6:
                            \[ v = \frac{108}{3,6} = \frac{1.080}{36} = 30\text{ m/s} \]
                            Conclusão: 108 km/h corresponde a 30 m/s.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l6",
                unitId = "math_u_basic",
                title = "Aula 6: Razão, Proporção e Grandezas",
                conceptTitle = "Grandezas Diretas e Inversas",
                explanation = """
                    • Razão: divisão entre dois valores \( a / b \) (\( b \neq 0 \)).
                    • Proporção: igualdade entre razões: \( a/b = c/d \iff a \cdot d = b \cdot c \).
                    • Grandezas Diretamente Proporcionais: o quociente entre elas é constante (\( A/B = k \)). Se uma dobra, a outra dobra.
                    • Grandezas Inversamente Proporcionais: o produto entre elas é constante (\( A \cdot B = k \)). Se uma dobra, a outra cai pela metade.
                    • Método da Constante k: muito útil para divisão proporcional de heranças, lucros ou recursos.
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Em divisões proporcionais, some os coeficientes para encontrar o valor da constante k unitária.",
                    "Velocidade e tempo de viagem são grandezas inversamente proporcionais (mais velocidade = menos tempo)."
                ),
                solutionExplanation = "A constante de proporcionalidade k transforma relações complexas em simples equações lineares de primeiro grau.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l6_1",
                        title = "Divisão Proporcional de Lucro por Capital",
                        problemStatement = """Dois sócios, Carlos e Eduardo, investiram respectivamente R$ 20.000,00 e R$ 30.000,00 para abrir uma startup. Ao final do primeiro semestre, obtiveram um lucro líquido de R$ 50.000,00, que será dividido em partes diretamente proporcionais ao capital investido. Quanto receberá Carlos?""",
                        options = listOf(
                            "R$ 15.000,00",
                            "R$ 20.000,00",
                            "R$ 25.000,00",
                            "R$ 30.000,00",
                            "R$ 35.000,00"
                        ),
                        correctIndex = 1,
                        hint = "A razão dos capitais é 2 : 3. Logo Carlos = 2k e Eduardo = 3k, onde 2k + 3k = 50.000.",
                        stepByStepSolution = """
                            Passo 1: Simplificar os capitais: 20.000 : 30.000 = 2 : 3.
                            Passo 2: Atribuir a constante k:
                            Carlos = 2k e Eduardo = 3k.
                            Passo 3: Montar a equação da soma:
                            2k + 3k = 50.000 => 5k = 50.000 => k = 10.000.
                            Passo 4: Calcular a cota de Carlos:
                            Carlos = 2 × 10.000 = R$ 20.000,00.
                            Conclusão: Carlos receberá R$ 20.000,00 e Eduardo R$ 30.000,00.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l6_2",
                        title = "Proporção Inversa: Velocidade e Tempo",
                        problemStatement = """Um caminhão de entregas faz determinado trajeto à velocidade média constante de 60 km/h e completa o percurso em 4 horas. Se na volta o motorista trafegar à velocidade média de 80 km/h pelo mesmo trajeto, em quanto tempo completará a viagem?""",
                        options = listOf(
                            "2 horas e 30 minutos",
                            "3 horas",
                            "3 horas e 15 minutos",
                            "3 horas e 30 minutos",
                            "4 horas e 30 minutos"
                        ),
                        correctIndex = 1,
                        hint = "Velocidade e tempo são inversamente proporcionais: V1 × T1 = V2 × T2.",
                        stepByStepSolution = """
                            Passo 1: Como o trajeto é fixo, a distância é constante:
                            D = V × T = 60 km/h × 4 h = 240 km.
                            Passo 2: Calcular o tempo com a nova velocidade de 80 km/h:
                            \[ T_2 = \frac{D}{V_2} = \frac{240\text{ km}}{80\text{ km/h}} = 3\text{ horas} \]
                            Conclusão: O caminhão completará a viagem em exatamente 3 horas.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l7",
                unitId = "math_u_basic",
                title = "Aula 7: Regra de Três (Simples e Composta)",
                conceptTitle = "Análise de Grandezas e Equacionamento",
                explanation = """
                    • Passo 1: Organizar as grandezas em colunas bem definidas.
                    • Passo 2: Fixar a grandeza da incógnita (coluna do \( x \)).
                    • Passo 3: Comparar individualmente cada grandeza com a do \( x \):
                      - Se aumentando a grandeza o \( x \) também aumenta: DIRETAMENTE PROPORCIONAL (mantém a fração).
                      - Se aumentando a grandeza o \( x \) diminui: INVERSAMENTE PROPORCIONAL (inverte a fração).
                    • Passo 4: Montar a equação: fração do \( x \) = produto das outras frações.
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Sempre faça a pergunta comparando com a incógnita: 'Se eu tiver mais operários, preciso de mais ou menos dias?' (Menos dias -> Inversa!).",
                    "A fração do x fica isolada no primeiro membro da equação."
                ),
                solutionExplanation = "A regra de três composta é o método mais cobrado de proporções do ENEM, exigindo rigor no sentido das relações.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l7_1",
                        title = "Regra de Três Composta Clássica de Obras",
                        problemStatement = """Em uma construtora, 6 operários de mesmo rendimento constroem um muro de 120 metros de extensão em 8 dias de trabalho. Quantos dias serão necessários para que 8 operários construam um muro semelhante de 300 metros de extensão?""",
                        options = listOf(
                            "10 dias",
                            "12 dias",
                            "15 dias",
                            "18 dias",
                            "20 dias"
                        ),
                        correctIndex = 2,
                        hint = "Analise em relação aos Dias: Mais operários -> menos dias (inversa); Mais metros -> mais dias (direta).",
                        stepByStepSolution = """
                            Passo 1: Organizar as colunas:
                            Operários: 6 -> 8
                            Metros: 120 -> 300
                            Dias: 8 -> x
                            Passo 2: Análise de proporção com Dias:
                            Operários e Dias: INVERSA (inverte para 8/6).
                            Metros e Dias: DIRETA (mantém 120/300).
                            Passo 3: Montar a equação:
                            \[ \frac{8}{x} = \frac{8}{6} \times \frac{120}{300} \]
                            Passo 4: Simplificar as frações:
                            120 / 300 = 12 / 30 = 2 / 5.
                            \[ \frac{8}{x} = \frac{8}{6} \times \frac{2}{5} = \frac{16}{30} = \frac{8}{15} \]
                            \[ \frac{8}{x} = \frac{8}{15} \implies x = 15\text{ dias} \]
                            Conclusão: Serão necessários 15 dias de trabalho.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l7_2",
                        title = "Regra de Três Simples Inversa: Torneiras e Tempo",
                        problemStatement = """Para encher uma piscina, 3 torneiras idênticas com a mesma vazão levam 10 horas. Se forem abertas 5 dessas torneiras simultaneamente, em quanto tempo a piscina estará completamente cheia?""",
                        options = listOf(
                            "4 horas",
                            "5 horas",
                            "6 horas",
                            "7 horas",
                            "8 horas"
                        ),
                        correctIndex = 2,
                        hint = "Mais torneiras enchem a piscina em menos tempo (proporção inversa). Multiplique em linha: 3 × 10 = 5 × t.",
                        stepByStepSolution = """
                            Passo 1: Identificar a relação de proporcionalidade:
                            Mais torneiras => Menor tempo para encher (Grandezas Inversamente Proporcionais).
                            Passo 2: Equacionar o produto constante:
                            \[ 3 \times 10 = 5 \times t \]
                            \[ 30 = 5t \implies t = \frac{30}{5} = 6\text{ horas} \]
                            Conclusão: A piscina será preenchida em 6 horas.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l8",
                unitId = "math_u_basic",
                title = "Aula 8: Escalas Cartográficas",
                conceptTitle = "Escala Linear vs Escala de Área",
                explanation = """
                    • Escala Linear (\( E \)): razão entre o comprimento no desenho/mapa (\( d \)) e o comprimento real correspondente (\( D \)), na MESMA unidade:
                      \[ E = \frac{d}{D} \]
                    • Cuidado com a conversão: sempre passe quilômetros ou metros para centímetros antes de calcular a razão!
                      - \( 1\text{ km} = 1.000\text{ m} = 100.000\text{ cm} \)
                    • Escala de Área: se a escala linear é \( 1 : k \), a escala de áreas é quadrática:
                      \[ \frac{A_{\text{mapa}}}{A_{\text{real}}} = E^2 = \left(\frac{1}{k}\right)^2 = \frac{1}{k^2} \]
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Multiplique quilômetros por 100.000 para converter direto em centímetros.",
                    "Se a questão envolver área de terreno ou planta, a escala deve ser elevada ao quadrado!"
                ),
                solutionExplanation = "A escala cartográfica é uma razão adimensional que exige estrita homogeneidade de unidades de medida.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l8_1",
                        title = "Distância Real a partir de Escala Cartográfica",
                        problemStatement = """Em um mapa confeccionado na escala cartográfica 1 : 50.000, a distância em linha reta entre duas cidades turísticas é representada por um segmento de 4 cm. Qual é a distância real em linha reta entre essas cidades em quilômetros?""",
                        options = listOf(
                            "0,2 km",
                            "2,0 km",
                            "20 km",
                            "200 km",
                            "2.000 km"
                        ),
                        correctIndex = 1,
                        hint = "Multiplique 4 cm por 50.000 para obter os centímetros reais e converta para quilômetros dividindo por 100.000.",
                        stepByStepSolution = """
                            Passo 1: Aplicar a fórmula da escala linear:
                            \[ D = d \times 50.000 = 4\text{ cm} \times 50.000 = 200.000\text{ cm} \]
                            Passo 2: Converter centímetros para metros dividindo por 100:
                            200.000 cm ÷ 100 = 2.000 metros.
                            Passo 3: Converter metros para quilômetros dividindo por 1.000:
                            2.000 m ÷ 1.000 = 2,0 km.
                            Conclusão: A distância real entre as cidades é de 2 km.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l8_2",
                        title = "Escala de Áreas em Plantas Arquitetônicas",
                        problemStatement = """A planta baixa de uma sala retangular foi desenhada na escala linear 1 : 100. Na planta, a sala tem dimensões de 4 cm por 5 cm, resultando em 20 cm² de área de papel. Qual é a área real dessa sala em metros quadrados (\(\text{m}^2\))?""",
                        options = listOf(
                            "2 m²",
                            "10 m²",
                            "20 m²",
                            "200 m²",
                            "2.000 m²"
                        ),
                        correctIndex = 2,
                        hint = "Dimensões reais: 4 cm -> 400 cm = 4 m; 5 cm -> 500 cm = 5 m. Multiplique as dimensões reais.",
                        stepByStepSolution = """
                            Passo 1: Converter as medidas lineares para a realidade:
                            Largura real = 4 cm × 100 = 400 cm = 4 metros.
                            Comprimento real = 5 cm × 100 = 500 cm = 5 metros.
                            Passo 2: Multiplicar as dimensões reais:
                            \[ \text{Área real} = 4\text{ m} \times 5\text{ m} = 20\text{ m}^2 \]
                            Método alternativo pela escala de áreas:
                            \[ A_{\text{real}} = 20\text{ cm}^2 \times (100)^2 = 20 \times 10.000\text{ cm}^2 = 200.000\text{ cm}^2 \]
                            Como 1 m² = 10.000 cm², 200.000 cm² ÷ 10.000 = 20 m².
                            Conclusão: A sala tem exatamente 20 m² de área real.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l9",
                unitId = "math_u_basic",
                title = "Aula 9: Porcentagem (Aumentos e Descontos Sucessivos)",
                conceptTitle = "Fator Multiplicativo e Variações Percentuais",
                explanation = """
                    • Representação: \( p\% = \frac{p}{100} \).
                    • Fator de Aumento: multiplicar por \( (1 + i) \), onde \( i \) é a taxa na forma decimal.
                    • Fator de Desconto: multiplicar por \( (1 - i) \).
                    • Aumentos e Descontos Sucessivos:
                      O fator total é o produto dos fatores individuais:
                      \[ F_{\text{total}} = (1 \pm i_1) \times (1 \pm i_2) \times \dots \]
                    • ATENÇÃO DE OURO DO ENEM: NUNCA some porcentagens de variações sucessivas! Elas incidem sobre valores-base já alterados.
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Aumento de 10% seguido de desconto de 20% resulta em 1,10 × 0,80 = 0,88 (que é um desconto real de 12%, e NÃO -10%).",
                    "Dois aumentos sucessivos de 20% equivalem a 1,20 × 1,20 = 1,44 (aumento real de 44%)."
                ),
                solutionExplanation = "O fator multiplicativo encadeado impede a ilusão aritmética de somar taxas percentuais sobre bases distintas.",
                xpReward = 30,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l9_1",
                        title = "Variação Acumulada de Aumento e Desconto Sucessivo",
                        problemStatement = """Um produto que custava inicialmente R$ 200,00 sofreu um aumento de 10% no início do mês e, no final do mês, uma liquidação concedeu 20% de desconto sobre o novo valor reajustado. Qual é o preço final do produto e qual foi a variação percentual líquida sobre o preço original?""",
                        options = listOf(
                            "R$ 180,00 e desconto de 10%",
                            "R$ 176,00 e desconto de 12%",
                            "R$ 176,00 e desconto de 14%",
                            "R$ 184,00 e desconto de 8%",
                            "R$ 170,00 e desconto de 15%"
                        ),
                        correctIndex = 1,
                        hint = "Multiplique os fatores: 1,10 (aumento de 10%) × 0,80 (desconto de 20%) = 0,88.",
                        stepByStepSolution = """
                            Passo 1: Determinar os fatores de cada operação:
                            Fator de aumento de 10%: 1 + 0,10 = 1,10.
                            Fator de desconto de 20%: 1 - 0,20 = 0,80.
                            Passo 2: Calcular o fator acumulado total:
                            \[ F = 1,10 \times 0,80 = 0,88 \]
                            Passo 3: Calcular o preço final:
                            \[ V_{\text{final}} = 200,00 \times 0,88 = \text{R\$ } 176,00 \]
                            Passo 4: Determinar a variação líquida total:
                            0,88 - 1,00 = -0,12 = -12% (desconto efetivo de 12%).
                            Conclusão: O preço final é R$ 176,00 e a variação foi um desconto de 12%.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l9_2",
                        title = "Dois Aumentos Sucessivos Acumulados",
                        problemStatement = """A tarifa de transporte de uma metrópole sofreu dois aumentos sucessivos: o primeiro de 20% e o segundo de 30% sobre o novo valor. Qual foi o percentual total acumulado de aumento da tarifa?""",
                        options = listOf(
                            "50%",
                            "52%",
                            "54%",
                            "56%",
                            "60%"
                        ),
                        correctIndex = 3,
                        hint = "Multiplique os fatores 1,20 × 1,30. Não cometa o erro de somar 20% + 30%!",
                        stepByStepSolution = """
                            Passo 1: Identificar os fatores de aumento:
                            Primeiro aumento de 20%: 1 + 0,20 = 1,20.
                            Segundo aumento de 30%: 1 + 0,30 = 1,30.
                            Passo 2: Calcular o produto dos fatores:
                            \[ F = 1,20 \times 1,30 = 1,56 \]
                            Passo 3: Converter para percentual de aumento:
                            1,56 - 1 = 0,56 = 56% de aumento real acumulado.
                            Conclusão: O aumento total foi de 56% (e não 50%).
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l10",
                unitId = "math_u_basic",
                title = "Aula 10: Equações de 1º/2º Grau e Sistemas Lineares",
                conceptTitle = "Modelagem Algébrica, Bhaskara e Sistemas 2x2",
                explanation = """
                    • Equação do 1º Grau: \( ax + b = 0 \implies x = -\frac{b}{a} \).
                    • Sistemas Lineares 2x2: Duas equações simultâneas com duas incógnitas (\(x\) e \(y\)), resolvidas por Substituição ou Adição.
                    • Equação do 2º Grau: \( ax^2 + bx + c = 0 \), onde \(\Delta = b^2 - 4ac\) e \(x = \frac{-b \pm \sqrt{\Delta}}{2a}\).
                    • Relações de Soma e Produto (Girard):
                      \[ S = x_1 + x_2 = -\frac{b}{a} \quad \text{e} \quad P = x_1 \cdot x_2 = \frac{c}{a} \]
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Em sistemas lineares de problemas do ENEM, identifique a equação de quantidade total (x + y = total) e a equação de valor ponderado.",
                    "Para equações quadráticas com a = 1, tente achar dois números cuja soma é -b e cujo produto é c."
                ),
                solutionExplanation = "A tradução de problemas em linguagem natural para equações algébricas é uma das competências mais exigidas no ENEM.",
                xpReward = 35,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l10_1",
                        title = "Sistema Linear em Estacionamento (Motos e Carros)",
                        problemStatement = """Em um estacionamento há carros (4 rodas) e motos (2 rodas), totalizando 45 veículos e 140 rodas no chão. Quantos carros e quantas motos estão nesse estacionamento?""",
                        options = listOf(
                            "20 carros e 25 motos",
                            "25 carros e 20 motos",
                            "30 carros e 15 motos",
                            "15 carros e 30 motos",
                            "22 carros e 23 motos"
                        ),
                        correctIndex = 1,
                        hint = "Monte o sistema: C + M = 45 e 4C + 2M = 140. Divida a segunda equação por 2.",
                        stepByStepSolution = """
                            Passo 1: Definir as incógnitas: C = carros, M = motos.
                            Passo 2: Equacionar os dados:
                            \[ \begin{cases} C + M = 45 \\ 4C + 2M = 140 \end{cases} \]
                            Passo 3: Simplificar a 2ª equação dividindo por 2:
                            \[ 2C + M = 70 \]
                            Passo 4: Subtrair a 1ª equação da 2ª simplificada:
                            \[ (2C + M) - (C + M) = 70 - 45 \implies C = 25\text{ carros} \]
                            Passo 5: Encontrar M:
                            \[ M = 45 - 25 = 20\text{ motos} \]
                            Conclusão: Há 25 carros e 20 motos.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l10_2",
                        title = "Raízes Reais de Equação do 2º Grau",
                        problemStatement = """A área de um terreno retangular de lados \((x - 2)\) e \((x - 5)\) é Modelada pela equação \(x^2 - 7x + 10 = 0\). Quais são as duas raízes reais dessa equação?""",
                        options = listOf(
                            "x = 1 e x = 10",
                            "x = -2 e x = -5",
                            "x = 2 e x = 5",
                            "x = 3 e x = 4",
                            "x = 0 e x = 7"
                        ),
                        correctIndex = 2,
                        hint = "Use Soma e Produto: S = -(-7)/1 = 7 e P = 10/1 = 10. Quais dois números somam 7 e multiplicam 10?",
                        stepByStepSolution = """
                            Passo 1: Identificar os coeficientes: a = 1, b = -7, c = 10.
                            Passo 2: Pela relação de Soma e Produto:
                            Soma S = -b/a = 7.
                            Produto P = c/a = 10.
                            Passo 3: Os números que somados dão 7 e multiplicados dão 10 são 2 e 5.
                            Verificação por Bhaskara:
                            \[ \Delta = (-7)^2 - 4(1)(10) = 49 - 40 = 9 \implies x = \frac{7 \pm 3}{2} \implies x_1 = 5, \, x_2 = 2 \]
                            Conclusão: As raízes são x = 2 e x = 5.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l11",
                unitId = "math_u_basic",
                title = "Aula 11: Estatística: Média, Mediana e Moda",
                conceptTitle = "Medidas de Tendência Central e Análise de Dados",
                explanation = """
                    • Média Aritmética Simples: Soma de todos os elementos dividida pelo total de elementos \(n\).
                    • Média Aritmética Ponderada: Soma dos produtos \(x_i \cdot p_i\) dividida pela soma dos pesos \(\sum p_i\).
                    • Mediana (\(Md\)): Termo central após **ordenar os dados em rol crescente**. Se \(n\) for par, é a média dos dois termos centrais.
                    • Moda (\(Mo\)): Valor de maior frequência absoluta (que mais aparece).
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Sempre coloque a lista de números em ordem crescente antes de procurar a mediana!",
                    "Na média ponderada, divida pela soma dos pesos, nunca pela quantidade de notas."
                ),
                solutionExplanation = "Média, mediana e moda aparecem todos os anos no ENEM para interpretação de gráficos e tabelas.",
                xpReward = 35,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l11_1",
                        title = "Cálculo de Média Ponderada Semestral",
                        problemStatement = """Em uma universidade, a nota final de Cálculo é a média ponderada de três avaliações: Prova 1 (peso 2), Prova 2 (peso 3) e Prova Final (peso 5). Se um estudante tirou 6,0 na Prova 1, 7,0 na Prova 2 e 9,0 na Prova Final, qual é sua média final?""",
                        options = listOf(
                            "7,3",
                            "7,5",
                            "7,8",
                            "8,0",
                            "8,2"
                        ),
                        correctIndex = 2,
                        hint = "Multiplique cada nota pelo seu peso: (6×2 + 7×3 + 9×5) e divida pela soma dos pesos (2 + 3 + 5 = 10).",
                        stepByStepSolution = """
                            Passo 1: Multiplicar cada nota pelo respectivo peso:
                            Prova 1: 6,0 × 2 = 12,0.
                            Prova 2: 7,0 × 3 = 21,0.
                            Prova Final: 9,0 × 5 = 45,0.
                            Passo 2: Somar os produtos:
                            12,0 + 21,0 + 45,0 = 78,0.
                            Passo 3: Dividir pela soma dos pesos (2 + 3 + 5 = 10):
                            \[ \bar{x}_p = \frac{78,0}{10} = 7,8 \]
                            Conclusão: A média ponderada final é 7,8.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l11_2",
                        title = "Mediana e Moda de Série de Dados",
                        problemStatement = """O consumo diário de água (em litros) de uma residência durante 7 dias foi: 150, 120, 180, 120, 200, 140, 160. Qual é a Mediana e a Moda desse consumo, respectivamente?""",
                        options = listOf(
                            "Mediana = 120 L e Moda = 150 L",
                            "Mediana = 150 L e Moda = 120 L",
                            "Mediana = 140 L e Moda = 120 L",
                            "Mediana = 160 L e Moda = 180 L",
                            "Mediana = 152,8 L e Moda = 120 L"
                        ),
                        correctIndex = 1,
                        hint = "Ordene os 7 valores em ordem crescente. O 4º elemento será a mediana, e o valor repetido será a moda.",
                        stepByStepSolution = """
                            Passo 1: Ordenar os 7 valores em ordem crescente (rol):
                            [120, 120, 140, 150, 160, 180, 200].
                            Passo 2: Encontrar a Mediana (4º termo, central entre os 7):
                            Mediana = 150 L.
                            Passo 3: Encontrar a Moda (valor que mais se repete):
                            O valor 120 L aparece 2 vezes (todos os outros aparecem 1 vez). Logo, Moda = 120 L.
                            Conclusão: Mediana = 150 L e Moda = 120 L.
                        """.trimIndent()
                    )
                )
            ),

            Lesson(
                id = "mb_l12",
                unitId = "math_u_basic",
                title = "Aula 12: Geometria Essencial: Áreas, Pitágoras e Volumes",
                conceptTitle = "Figuras Planas, Triângulo Retângulo e Sólidos",
                explanation = """
                    • Área do Retângulo: \( A = b \cdot h \) | Área do Triângulo: \( A = \frac{b \cdot h}{2} \).
                    • Área do Círculo: \( A = \pi R^2 \) | Circunferência: \( C = 2\pi R \).
                    • Teorema de Pitágoras: \( a^2 = b^2 + c^2 \), onde \(a\) é a hipotenusa.
                    • Volume de Prismas e Cilindros: \( V = A_{\text{base}} \cdot h \implies V_{\text{cilindro}} = \pi R^2 h \).
                    • Volume de Pirâmides e Cones: \( V = \frac{1}{3} A_{\text{base}} \cdot h \).
                """.trimIndent(),
                lessonType = LessonType.LEARN,
                initialCode = "",
                hints = listOf(
                    "Lembre-se do triângulo pitagórico 3-4-5 e de seus múltiplos (6-8-10, 9-12-15).",
                    "Para converter volume em m³ para litros, multiplique por 1.000."
                ),
                solutionExplanation = "A geometria espacial do ENEM concentra-se em cilindros, prismas retangulares e conversão de m³ para litros.",
                xpReward = 35,
                mathExercises = listOf(
                    MathExercise(
                        id = "ex_mb_l12_1",
                        title = "Teorema de Pitágoras e Área de Terreno",
                        problemStatement = """Uma escada de 10 metros de comprimento está apoiada no topo de um muro vertical. A base da escada dista 6 metros do pé do muro no chão horizontal. Qual é a altura desse muro?""",
                        options = listOf(
                            "4 metros",
                            "7 metros",
                            "8 metros",
                            "9 metros",
                            "12 metros"
                        ),
                        correctIndex = 2,
                        hint = "A escada é a hipotenusa (10 m) e a distância no solo é um cateto (6 m): h² + 6² = 10².",
                        stepByStepSolution = """
                            Passo 1: Aplicar o Teorema de Pitágoras no triângulo retângulo formado pelo muro (h), solo (6 m) e escada (10 m):
                            \[ h^2 + 6^2 = 10^2 \]
                            Passo 2: Resolver as potências:
                            \[ h^2 + 36 = 100 \implies h^2 = 100 - 36 = 64 \]
                            Passo 3: Extrair a raiz quadrada positiva:
                            \[ h = \sqrt{64} = 8\text{ metros} \]
                            Conclusão: O muro possui 8 metros de altura.
                        """.trimIndent()
                    ),
                    MathExercise(
                        id = "ex_mb_l12_2",
                        title = "Volume de Silo Cilíndrico em Litros",
                        problemStatement = """Um reservatório cilíndrico de água possui raio da base R = 2 metros e altura h = 5 metros. Adotando a aproximação \(\pi = 3\), qual é a capacidade total desse reservatório em litros?""",
                        options = listOf(
                            "30.000 litros",
                            "45.000 litros",
                            "60.000 litros",
                            "75.000 litros",
                            "120.000 litros"
                        ),
                        correctIndex = 2,
                        hint = "Calcule V = π · R² · h em metros cúbicos e multiplique por 1.000 para passar para litros.",
                        stepByStepSolution = """
                            Passo 1: Calcular o volume do cilindro em metros cúbicos:
                            \[ V = \pi \cdot R^2 \cdot h = 3 \times 2^2 \times 5 = 3 \times 4 \times 5 = 60\text{ m}^3 \]
                            Passo 2: Converter metros cúbicos para litros (1 m³ = 1.000 L):
                            \[ \text{Capacidade} = 60 \times 1.000 = 60.000\text{ litros} \]
                            Conclusão: A capacidade total é de 60.000 litros.
                        """.trimIndent()
                    )
                )
            )
        )
    )

    // ========================================================
    // TESTE FINAL: CICLO DE MATEMÁTICA BÁSICA (ENEM)
    // ========================================================

    val basicMathFinalTest = KahootModule(
        id = "test_math_basic",
        tier = DifficultyTier.BASIC,
        title = "Simulado Final: Matemática Básica (ENEM)",
        description = "8 questões integrando Aritmética, Proporções, Unidades, Escalas, Porcentagens, Álgebra, Estatística e Geometria.",
        questions = listOf(
            KahootQuestion(
                id = "kq_mb_01",
                question = "Dois medicamentos são prescritos a um paciente: um de 6 em 6 horas e outro de 8 em 8 horas. Se o paciente tomou ambos juntos às 12h00 de segunda-feira, quando tomará os dois simultaneamente de novo?",
                codeSnippet = null,
                options = listOf(
                    "Terça-feira às 12h00 (24h depois)",
                    "Terça-feira às 00h00 (12h depois)",
                    "Segunda-feira às 20h00",
                    "Quarta-feira às 12h00 (48h depois)"
                ),
                correctIndex = 0,
                explanation = "MMC(6, 8) = 24 horas. Portanto, exatamente 24 horas após as 12h00 de segunda, ou seja, às 12h00 de terça-feira.",
                conceptTag = "MMC e Sincronicidade"
            ),
            KahootQuestion(
                id = "kq_mb_02",
                question = "Uma piscina de 45.000 litros está totalmente cheia. Qual é o volume dessa água expresso na unidade metros cúbicos (m³)?",
                codeSnippet = null,
                options = listOf(
                    "4,5 m³",
                    "45 m³",
                    "450 m³",
                    "0,45 m³"
                ),
                correctIndex = 1,
                explanation = "Como 1 m³ equivale a 1.000 L, temos: 45.000 ÷ 1.000 = 45 m³.",
                conceptTag = "Conversão de Unidades"
            ),
            KahootQuestion(
                id = "kq_mb_03",
                question = "Na planta de um loteamento feita na escala 1 : 20.000, um canal de drenagem mede 15 cm. Qual é o comprimento real desse canal em quilômetros?",
                codeSnippet = null,
                options = listOf(
                    "1,5 km",
                    "3,0 km",
                    "30 km",
                    "0,3 km"
                ),
                correctIndex = 1,
                explanation = "15 cm × 20.000 = 300.000 cm = 3.000 m = 3,0 km.",
                conceptTag = "Escalas Cartográficas"
            ),
            KahootQuestion(
                id = "kq_mb_04",
                question = "Um smartphone de R$ 1.500,00 tem seu preço aumentado em 20% e, dias depois, recebe um desconto promocional de 20% à vista. O preço final é:",
                codeSnippet = null,
                options = listOf(
                    "Exatamente R$ 1.500,00",
                    "R$ 1.440,00 (4% mais barato)",
                    "R$ 1.400,00",
                    "R$ 1.560,00 (4% mais caro)"
                ),
                correctIndex = 1,
                explanation = "1.500 × 1,20 × 0,80 = 1.500 × 0,96 = R$ 1.440,00. As taxas não se anulam porque o desconto incide sobre a base já aumentada!",
                conceptTag = "Porcentagem e Variação"
            ),
            KahootQuestion(
                id = "kq_mb_05",
                question = "Se 4 pintores pintam uma fachada em 9 horas, em quantas horas 6 pintores com a mesma produtividade pintariam a mesma fachada?",
                codeSnippet = null,
                options = listOf(
                    "13,5 horas",
                    "7,5 horas",
                    "6,0 horas",
                    "4,5 horas"
                ),
                correctIndex = 2,
                explanation = "4 × 9 = 36 horas-homem. Logo, com 6 pintores: 36 ÷ 6 = 6 horas.",
                conceptTag = "Regra de Três Inversa"
            ),
            KahootQuestion(
                id = "kq_mb_06",
                question = "Qual é a mediana do seguinte conjunto de notas de um candidato no simulado: [720, 640, 810, 590, 750]?",
                codeSnippet = null,
                options = listOf(
                    "810",
                    "720",
                    "702",
                    "640"
                ),
                correctIndex = 1,
                explanation = "Ordenando em rol crescente: [590, 640, 720, 750, 810]. O termo central (3º de 5) é 720.",
                conceptTag = "Estatística e Mediana"
            ),
            KahootQuestion(
                id = "kq_mb_07",
                question = "A soma e o produto das raízes da equação do 2º grau x² - 9x + 20 = 0 valem, respectivamente:",
                codeSnippet = null,
                options = listOf(
                    "Soma = -9 e Produto = 20 (raízes -4 e -5)",
                    "Soma = 9 e Produto = 20 (raízes 4 e 5)",
                    "Soma = 20 e Produto = 9",
                    "Soma = 9 e Produto = -20"
                ),
                correctIndex = 1,
                explanation = "S = -b/a = -(-9)/1 = 9 e P = c/a = 20/1 = 20. Os números que somam 9 e multiplicam 20 são 4 e 5.",
                conceptTag = "Equação do 2º Grau"
            ),
            KahootQuestion(
                id = "kq_mb_08",
                question = "Uma caixa d'água em formato de bloco retangular mede 2 m de comprimento, 1,5 m de largura e 1 m de altura. Sua capacidade em litros é:",
                codeSnippet = null,
                options = listOf(
                    "300 litros",
                    "1.500 litros",
                    "3.000 litros",
                    "4.500 litros"
                ),
                correctIndex = 2,
                explanation = "Volume = 2 × 1,5 × 1 = 3 m³. Como 1 m³ = 1.000 litros, a capacidade é de 3.000 litros.",
                conceptTag = "Geometria Espacial"
            )
        ),
        category = "Matemática",
        subject = "Matemática Básica"
    )
}
