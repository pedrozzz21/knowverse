package com.example.data.exercise

import com.example.R
import com.example.data.math.BasicMathCurriculum
import com.example.data.math.CalculusExercisesBank
import com.example.data.math.GeoGebraGraphConfig

enum class ExerciseDiscipline(val label: String, val tag: String) {
    ALL("Todas as Matérias", "all"),
    ENEM("Banco ENEM (Oficial)", "enem"),
    BASIC_MATH("Matemática Básica", "basic_math"),
    CALCULUS("Cálculo I", "calculus"),
    PROGRAMMING("Programação (Python)", "programming")
}

data class VisualTable(
    val title: String,
    val headers: List<String>,
    val rows: List<List<String>>
)

data class UniversalExercise(
    val id: String,
    val discipline: ExerciseDiscipline,
    val category: String, // ex: "Python", "Cálculo I", "Matemática Básica"
    val subtopic: String, // ex: "if e else (Condicionais)", "MMC e MDC", "Limites", etc.
    val difficulty: String, // "Fácil", "Mediano", "Difícil"
    val source: String,
    val problemStatement: String,
    val codeSnippet: String? = null,
    val visualDrawableRes: Int? = null,
    val visualTable: VisualTable? = null,
    val terminalOutput: String? = null,
    val geoGebraConfig: GeoGebraGraphConfig? = null,
    val options: List<String>,
    val correctIndex: Int,
    val solutionSteps: List<String>,
    val pedagogicalAlert: String,
    val keyMethod: String,
    val xpReward: Int = 30
)

object UniversalExerciseBank {

    val curatedExercises: List<UniversalExercise> = listOf(
        // ========================================================
        // 1. MATEMÁTICA BÁSICA (ENEM) - GUIA COMPLETO & EXERCÍCIOS
        // ========================================================
        UniversalExercise(
            id = "mb_01",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Frações e Decimais",
            difficulty = "Fácil",
            source = "ENEM / Guia de Estudos",
            problemStatement = """Um recipiente continha 2,75 litros de água. Inicialmente, foram retirados 3/4 de litro dessa água e, em seguida, adicionou-se 1,2 litro ao volume restante. Qual é o volume final de água no recipiente?""",
            options = listOf(
                "2,30 litros",
                "3,20 litros",
                "3,95 litros",
                "1,80 litros",
                "4,70 litros"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Converter a fração 3/4 para número decimal para igualar a unidade de representação: 3/4 = 3 ÷ 4 = 0,75 litros.",
                "Passo 2: Subtrair a quantidade retirada do volume inicial: 2,75 - 0,75 = 2,00 litros.",
                "Passo 3: Adicionar o volume inserido posteriormente: 2,00 + 1,20 = 3,20 litros.",
                "Resultado Final: O volume final no recipiente é de 3,2 litros."
            ),
            pedagogicalAlert = "Ao operar números decimais com frações, converta todos para a mesma forma (decimal ou fração com mesmo denominador) para evitar erros de soma.",
            keyMethod = "Conversão fracionária: 3/4 = 0,75 L => 2,75 - 0,75 + 1,2 = 3,2 L"
        ),

        UniversalExercise(
            id = "mb_02",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "MMC e MDC",
            difficulty = "Mediano",
            source = "ENEM / Ciclos & Sincronicidade",
            problemStatement = """Dois corredores, A e B, treinam em uma pista circular. O corredor A completa uma volta em 12 minutos, enquanto o corredor B completa em 18 minutos. Se ambos partirem juntos do mesmo ponto às 08h00, a que horas eles se encontrarão novamente no ponto de partida?""",
            visualTable = VisualTable(
                title = "Decomposição Simultânea em Fatores Primos",
                headers = listOf("Fator Primo", "Números (12, 18)"),
                rows = listOf(
                    listOf("2", "6, 9"),
                    listOf("2", "3, 9"),
                    listOf("3", "1, 3"),
                    listOf("3", "1, 1")
                )
            ),
            options = listOf(
                "08h24",
                "08h30",
                "08h36",
                "08h48",
                "09h12"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Problemas de sincronicidade e ciclos periódicos que se repetem no futuro são resolvidos pelo Mínimo Múltiplo Comum (MMC).",
                "Passo 2: Fatoração simultânea de 12 e 18 por números primos: MMC(12, 18) = 2² × 3² = 4 × 9 = 36 minutos.",
                "Passo 3: Somar os 36 minutos ao horário inicial de partida: 08h00 + 36 min = 08h36.",
                "Resultado Final: Os corredores se encontrarão novamente às 08h36."
            ),
            pedagogicalAlert = "Não use MDC em problemas de encontros futuros! MDC é usado para divisão sem sobra; para ciclos que se repetem use sempre MMC.",
            keyMethod = "MMC(12, 18) = 2² × 3² = 36 min => 08h00 + 36 min = 08h36"
        ),

        UniversalExercise(
            id = "mb_03",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "MMC e MDC",
            difficulty = "Mediano",
            source = "ENEM / Divisão Máxima",
            problemStatement = """Um comerciante possui três rolos de fita com comprimentos de 120 cm, 180 cm e 240 cm. Ele deseja cortar todas as fitas em pedaços iguais, de modo que não haja sobra e que o tamanho de cada pedaço seja o maior possível. Qual deve ser o comprimento de cada pedaço e quantos pedaços serão obtidos no total?""",
            options = listOf(
                "Comprimento de 30 cm e 18 pedaços",
                "Comprimento de 40 cm e 12 pedaços",
                "Comprimento de 60 cm e 9 pedaços",
                "Comprimento de 60 cm e 11 pedaços",
                "Comprimento de 80 cm e 7 pedaços"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Problemas que pedem divisão em partes iguais, com tamanho máximo e sem sobras, exigem o Máximo Divisor Comum (MDC).",
                "Passo 2: Fatoração dos números: 120 = 2³ × 3 × 5; 180 = 2² × 3² × 5; 240 = 2⁴ × 3 × 5.",
                "Passo 3: Fatores primos comuns com os menores expoentes: MDC(120, 180, 240) = 2² × 3¹ × 5¹ = 4 × 3 × 5 = 60 cm.",
                "Passo 4: Quantidade de pedaços obtidos em cada rolo: Fita 1: 120 ÷ 60 = 2; Fita 2: 180 ÷ 60 = 3; Fita 3: 240 ÷ 60 = 4.",
                "Passo 5: Total de pedaços = 2 + 3 + 4 = 9 pedaços.",
                "Resultado Final: Cada pedaço terá 60 cm e serão obtidos 9 pedaços no total."
            ),
            pedagogicalAlert = "O MDC dá o tamanho de cada pedaço (60 cm). Para saber o número de pedaços, divida cada comprimento pelo MDC e some os resultados!",
            keyMethod = "MDC(120, 180, 240) = 60 cm => Pedaços = 2 + 3 + 4 = 9"
        ),

        UniversalExercise(
            id = "mb_04",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Potenciação e Radiciação",
            difficulty = "Mediano",
            source = "ENEM / Propriedades de Potências",
            problemStatement = """Simplifique a expressão numérica E = (2⁵ × 8⁻²) / 4⁻³ e apresente o resultado sob a forma de uma única potência de base 2.""",
            options = listOf(
                "2³ (ou 8)",
                "2⁴ (ou 16)",
                "2⁵ (ou 32)",
                "2⁶ (ou 64)",
                "2⁻¹ (ou 0,5)"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Reescrever todas as bases como potências de base 2: 8 = 2³ => 8⁻² = (2³)⁻² = 2⁻⁶.",
                "Passo 2: 4 = 2² => 4⁻³ = (2²)⁻³ = 2⁻⁶.",
                "Passo 3: Substituir na expressão original: E = (2⁵ × 2⁻⁶) / 2⁻⁶.",
                "Passo 4: No numerador, multiplicar somando os expoentes: 2⁵ × 2⁻⁶ = 2^(5 + (-6)) = 2⁻¹.",
                "Passo 5: Na divisão, subtrair os expoentes: E = 2⁻¹ / 2⁻⁶ = 2^(-1 - (-6)) = 2^(-1 + 6) = 2⁵ = 32.",
                "Resultado Final: E = 2⁵ = 32."
            ),
            pedagogicalAlert = "Cuidado com o sinal na divisão com expoente negativo: -1 - (-6) vira -1 + 6 = 5!",
            keyMethod = "Base 2 comum: 8⁻² = 2⁻⁶, 4⁻³ = 2⁻⁶ => E = (2⁵ × 2⁻⁶) / 2⁻⁶ = 2⁵"
        ),

        UniversalExercise(
            id = "mb_05",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Notação Científica & Ordem de Grandeza",
            difficulty = "Fácil",
            source = "ENEM / Ciências da Natureza e Matemática",
            problemStatement = """A distância média entre a Terra e o Sol é de aproximadamente 149.600.000 km, e o diâmetro de um átomo de hidrogênio é cerca de 0,000000000106 m. Escreva ambos os valores em notação científica e determine a ordem de grandeza do diâmetro do átomo.""",
            options = listOf(
                "1,496 × 10⁸ km e 1,06 × 10⁻¹⁰ m; Ordem de grandeza: 10⁻¹⁰ m",
                "14,96 × 10⁷ km e 1,06 × 10⁻¹¹ m; Ordem de grandeza: 10⁻¹¹ m",
                "1,496 × 10⁸ km e 1,06 × 10⁻¹⁰ m; Ordem de grandeza: 10⁻⁹ m",
                "1,496 × 10⁶ km e 10,6 × 10⁻¹¹ m; Ordem de grandeza: 10⁻¹⁰ m",
                "149,6 × 10⁶ km e 1,06 × 10⁻⁹ m; Ordem de grandeza: 10⁻⁹ m"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Notação científica exige a mantissa 1 ≤ a < 10.",
                "Passo 2: Distância Terra-Sol (149.600.000 km): Deslocar a vírgula 8 casas para a esquerda => 1,496 × 10⁸ km.",
                "Passo 3: Diâmetro do átomo (0,000000000106 m): Deslocar a vírgula 10 casas para a direita => 1,06 × 10⁻¹⁰ m.",
                "Passo 4: Critério de Ordem de Grandeza: Como a mantissa a = 1,06 é menor que √10 ≈ 3,16, mantém-se a potência original 10⁻¹⁰ m.",
                "Resultado Final: 1,496 × 10⁸ km e 1,06 × 10⁻¹⁰ m, com ordem de grandeza 10⁻¹⁰ m."
            ),
            pedagogicalAlert = "Regra de ouro da ordem de grandeza: compare a mantissa com √10 ≈ 3,16. Se a < 3,16, a ordem é 10ⁿ; se a ≥ 3,16, soma-se 1 ao expoente (10ⁿ⁺¹).",
            keyMethod = "Mantissa a = 1,06 < 3,16 => Ordem de grandeza = 10⁻¹⁰ m"
        ),

        UniversalExercise(
            id = "mb_06",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Conversão de Unidades",
            difficulty = "Fácil",
            source = "ENEM / Grandezas e Medidas",
            problemStatement = """Uma caixa-d'água possui formato cúbico com capacidade total de 15.000 litros. Calcule o volume desta caixa em metros cúbicos (m³) e determine a velocidade média equivalente em m/s de um vazamento no cano principal que foi registrado a 18 km/h.""",
            options = listOf(
                "Volume = 15 m³; Velocidade = 5 m/s",
                "Volume = 1,5 m³; Velocidade = 5 m/s",
                "Volume = 150 m³; Velocidade = 64,8 m/s",
                "Volume = 15 m³; Velocidade = 50 m/s",
                "Volume = 0,15 m³; Velocidade = 5 m/s"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Relação fundamental de volume e capacidade: 1 m³ = 1.000 litros.",
                "Passo 2: Conversão de litros para m³: V = 15.000 L ÷ 1.000 = 15 m³.",
                "Passo 3: Conversão de km/h para m/s: divide-se pelo fator constante 3,6.",
                "Passo 4: v = 18 km/h ÷ 3,6 = 5 m/s.",
                "Resultado Final: Volume = 15 m³ e Velocidade = 5 m/s."
            ),
            pedagogicalAlert = "Memorize para o ENEM: km/h para m/s divide por 3,6; m/s para km/h multiplica por 3,6. E 1 m³ = 1.000 L, 1 dm³ = 1 L, 1 cm³ = 1 mL.",
            keyMethod = "15.000 L / 1.000 = 15 m³ | 18 km/h / 3,6 = 5 m/s"
        ),

        UniversalExercise(
            id = "mb_07",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Razão e Proporção",
            difficulty = "Mediano",
            source = "ENEM / Divisão Proporcional",
            problemStatement = """Dois sócios, Carlos e Eduardo, investiram respectivamente R$ 20.000,00 e R$ 30.000,00 para fundar uma empresa. Após um ano, a empresa obteve um lucro líquido de R$ 50.000,00, que será dividido de forma diretamente proporcional ao valor investido por cada um. Qual a quantia exata do lucro que cabe a cada sócio?""",
            options = listOf(
                "Carlos: R$ 25.000,00 | Eduardo: R$ 25.000,00",
                "Carlos: R$ 20.000,00 | Eduardo: R$ 30.000,00",
                "Carlos: R$ 18.000,00 | Eduardo: R$ 32.000,00",
                "Carlos: R$ 15.000,00 | Eduardo: R$ 35.000,00",
                "Carlos: R$ 22.500,00 | Eduardo: R$ 27.500,00"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Estabelecer a razão simplificada entre os capitais investidos: 20.000 / 30.000 = 2 / 3.",
                "Passo 2: Definir a constante de proporcionalidade k: Carlos recebe 2k e Eduardo recebe 3k.",
                "Passo 3: Somar as partes e igualar ao lucro líquido total: 2k + 3k = 50.000 => 5k = 50.000 => k = 10.000.",
                "Passo 4: Calcular a cota de cada um: Carlos = 2 × 10.000 = R$ 20.000,00; Eduardo = 3 × 10.000 = R$ 30.000,00.",
                "Resultado Final: Carlos receberá R$ 20.000,00 e Eduardo receberá R$ 30.000,00."
            ),
            pedagogicalAlert = "Em divisão diretamente proporcional, a soma das partes (2k + 3k) divide o total. Em divisão inversamente proporcional, utiliza-se k/2 e k/3.",
            keyMethod = "Constante k: 5k = 50.000 => k = 10.000 => Carlos = 20.000, Eduardo = 30.000"
        ),

        UniversalExercise(
            id = "mb_08",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Fácil",
            source = "ENEM / Proporcionalidade Inversa",
            problemStatement = """Um veículo, mantendo uma velocidade constante de 80 km/h, realiza um percurso em 3 horas. Quanto tempo ele levaria para cobrir o mesmo percurso se a sua velocidade fosse de 120 km/h?""",
            options = listOf(
                "1,5 hora",
                "2,0 horas",
                "2,5 horas",
                "4,5 horas",
                "4,0 horas"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Montar a relação de grandezas: Velocidade (km/h) e Tempo (horas).",
                "Passo 2: Avaliação de proporcionalidade: Se a velocidade aumenta de 80 para 120 km/h, o tempo gasto para percorrer a mesma distância diminui. As grandezas são inversamente proporcionais!",
                "Passo 3: Em grandezas inversas, o produto permanece constante: 80 × 3 = 120 × x.",
                "Passo 4: 240 = 120x => x = 240 / 120 = 2 horas.",
                "Resultado Final: O tempo necessário será de 2 horas."
            ),
            pedagogicalAlert = "Cuidado clássico: NÃO multiplique em cruz (80·x = 120·3) quando as grandezas forem inversamente proporcionais! Em grandezas inversas, multiplique em linha reta: 80 × 3 = 120 × x.",
            keyMethod = "Grandeza Inversa: 80 × 3 = 120 × x => 240 = 120x => x = 2 horas"
        ),

        UniversalExercise(
            id = "mb_09",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Mediano",
            source = "ENEM / Regra de Três Composta",
            problemStatement = """Em uma fábrica, 6 máquinas idênticas produzem 1.200 peças trabalhando durante 8 dias. Quantas peças serão produzidas por 10 máquinas similares operando durante 6 dias?""",
            visualTable = VisualTable(
                title = "Alinhamento das Grandezas",
                headers = listOf("Máquinas", "Dias", "Peças (Incógnita x)"),
                rows = listOf(
                    listOf("6", "8", "1200"),
                    listOf("10", "6", "x")
                )
            ),
            options = listOf(
                "1.000 peças",
                "1.200 peças",
                "1.500 peças",
                "1.800 peças",
                "2.000 peças"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Fixar a coluna da incógnita: Peças (1200 / x).",
                "Passo 2: Comparar com Máquinas: Mais máquinas produzem mais peças => Direta.",
                "Passo 3: Comparar com Dias: Mais dias de trabalho produzem mais peças => Direta.",
                "Passo 4: Montar a proporção direta: 1200 / x = (6 / 10) × (8 / 6).",
                "Passo 5: Simplificar o fator 6: 1200 / x = 8 / 10 = 4 / 5.",
                "Passo 6: Multiplicar em cruz: 4x = 1200 × 5 => 4x = 6000 => x = 1500 peças.",
                "Resultado Final: Serão produzidas 1.500 peças."
            ),
            pedagogicalAlert = "Sempre compare cada grandeza individualmente com a grandeza da incógnita para verificar se alguma precisa ser invertida.",
            keyMethod = "1200 / x = (6/10) × (8/6) = 8/10 => 4x = 6000 => x = 1.500 peças"
        ),

        UniversalExercise(
            id = "mb_10",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Escalas Cartográficas",
            difficulty = "Fácil",
            source = "ENEM / Cartografia e Escala",
            problemStatement = """Em um mapa cartográfico desenhado na escala 1 : 250.000, a distância entre duas cidades é representada por um segmento de reta de 8 cm. Qual é a distância real em quilômetros (km) entre essas duas cidades?""",
            options = listOf(
                "2 km",
                "20 km",
                "200 km",
                "2.000 km",
                "20.000 km"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Aplicar a definição de escala: E = d / D, onde d é a medida no mapa e D é a medida real.",
                "Passo 2: 1 / 250.000 = 8 cm / D => D = 8 × 250.000 = 2.000.000 cm.",
                "Passo 3: Converter de centímetros para metros (divide por 100): 2.000.000 cm ÷ 100 = 20.000 m.",
                "Passo 4: Converter de metros para quilômetros (divide por 1.000): 20.000 m ÷ 1.000 = 20 km.",
                "Resultado Final: A distância real entre as cidades é de 20 km."
            ),
            pedagogicalAlert = "Atalho prático para o ENEM: Para converter de cm direto para km, corte 5 zeros (divide por 100.000). 2.000.000 cm = 20 km!",
            keyMethod = "D = 8 × 250.000 cm = 2.000.000 cm / 100.000 = 20 km"
        ),

        UniversalExercise(
            id = "mb_11",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Porcentagem e Variação",
            difficulty = "Mediano",
            source = "ENEM / Matemática Financeira",
            problemStatement = """Um produto cujo preço inicial era de R$ 200,00 sofreu um aumento de 10% e, no mês seguinte, recebeu um desconto de 20% sobre o novo preço. Qual é o preço final do produto e a taxa percentual de variação total em relação ao valor original?""",
            options = listOf(
                "Preço final R$ 180,00 | Desconto total de 10%",
                "Preço final R$ 176,00 | Desconto total de 12%",
                "Preço final R$ 176,00 | Desconto total de 10%",
                "Preço final R$ 184,00 | Desconto total de 8%",
                "Preço final R$ 190,00 | Desconto total de 5%"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Converter as variações em fatores multiplicativos acumulados.",
                "Passo 2: Aumento de 10% corresponde ao fator (1 + 0,10) = 1,10.",
                "Passo 3: Desconto de 20% corresponde ao fator (1 - 0,20) = 0,80.",
                "Passo 4: Fator total acumulado = 1,10 × 0,80 = 0,88.",
                "Passo 5: Preço final = R$ 200,00 × 0,88 = R$ 176,00.",
                "Passo 6: Variação percentual equivalente: 0,88 - 1 = -0,12 = -12% (desconto real de 12%).",
                "Resultado Final: Preço final de R$ 176,00 e desconto total de 12%."
            ),
            pedagogicalAlert = "Erro mais comum do ENEM: +10% seguido de -20% NÃO é igual a -10%! O segundo percentual incide sobre a base já reajustada, por isso o fator acumulado é 1,10 × 0,80 = 0,88 (-12%).",
            keyMethod = "Fatores encadeados: F = 1,10 × 0,80 = 0,88 => Preço = 200 × 0,88 = R$ 176,00 (-12%)"
        ),

        // ========================================================
        // 2. PROGRAMAÇÃO (PYTHON) - VARIEDADE COMPLETA
        // ========================================================
        UniversalExercise(
            id = "py_01",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Mediano",
            source = "Python Institute / Curto-Circuito Lógico",
            problemStatement = """Analise o código Python abaixo sobre avaliação de curto-circuito (short-circuit evaluation). Qual será a saída impressa no console ao executar o script?""",
            codeSnippet = """def checar():
    print("Executou checar")
    return False

x = True
if x or checar():
    print("Passou no IF")
else:
    print("Passou no ELSE")""",
            terminalOutput = "Passou no IF",
            options = listOf(
                "Executou checar\nPassou no IF",
                "Passou no IF",
                "Executou checar\nPassou no ELSE",
                "Passou no ELSE",
                "SyntaxError: invalid syntax"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A expressão condicional é 'x or checar()'.",
                "Passo 2: Em Python, o operador 'or' avalia o operando da esquerda primeiro. Como x é True, a expressão já é garantidamente verdadeira.",
                "Passo 3: Pela regra de avaliação de curto-circuito (short-circuit), o operando da direita 'checar()' NÃO É EXECUTADO!",
                "Passo 4: Portanto, a mensagem 'Executou checar' nunca é impressa.",
                "Passo 5: O bloco do 'if' é executado, imprimindo apenas 'Passou no IF'.",
                "Resultado Final: A saída impressa é exclusivamente 'Passou no IF'."
            ),
            pedagogicalAlert = "Em Python, 'True or f()' não executa f(), e 'False and f()' também não executa f(). Isso é amplamente usado para evitar erros de índice ou ponteiro nulo.",
            keyMethod = "Curto-circuito: True or <qualquer_coisa> retorna True sem avaliar o lado direito."
        ),

        UniversalExercise(
            id = "py_02",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Fácil",
            source = "USP / Estruturas de Seleção",
            problemStatement = """Considere o trecho de código abaixo que classifica a pontuação de um estudante em uma escala de conceitos. Qual conceito será impresso quando nota = 85?""",
            codeSnippet = """nota = 85

if nota >= 90:
    conceito = "A"
elif nota >= 80:
    conceito = "B"
elif nota >= 70:
    conceito = "C"
else:
    conceito = "D"

print(f"Conceito: {conceito}")""",
            terminalOutput = "Conceito: B",
            options = listOf(
                "Conceito: A",
                "Conceito: B",
                "Conceito: C",
                "Conceito: D",
                "Conceito: B e C"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: O interpretador testa a primeira condição: nota >= 90 (85 >= 90 é Falso).",
                "Passo 2: O interpretador avança para o primeiro elif: nota >= 80 (85 >= 80 é Verdadeiro).",
                "Passo 3: A variável 'conceito' recebe a string 'B'.",
                "Passo 4: Em uma cadeia if-elif-else, assim que uma condição é satisfeita, todos os elif e else subsequentes são ignorados.",
                "Passo 5: O print final exibe 'Conceito: B'."
            ),
            pedagogicalAlert = "A ordem das cláusulas if-elif é crucial! Se a verificação >= 70 viesse antes de >= 80, o programa atribuiria C para notas 85.",
            keyMethod = "Cadeia if/elif/else: a primeira condição verdadeira é executada e o restante é pulado."
        ),

        UniversalExercise(
            id = "py_03",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Difícil",
            source = "Python Core / Valores Verdade (Truthy & Falsy)",
            problemStatement = """Em Python, coleções vazias e certos valores possuem valor booleano implícito. Qual será a saída do código a seguir?""",
            codeSnippet = """valores = [0, "", [], {}, None, "0", [0]]
contagem = 0

for v in valores:
    if v:
        contagem += 1

print(contagem)""",
            terminalOutput = "2",
            options = listOf(
                "0",
                "1",
                "2",
                "4",
                "7"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Em Python, são considerados Falsy (falsos): 0, \"\" (string vazia), [] (lista vazia), {} (dicionário vazio) e None.",
                "Passo 2: Avaliação elemento a elemento:",
                "  • 0 -> Falso",
                "  • \"\" -> Falso",
                "  • [] -> Falso",
                "  • {} -> Falso",
                "  • None -> Falso",
                "  • \"0\" -> Verdadeiro (é uma string não-vazia com tamanho 1!)",
                "  • [0] -> Verdadeiro (é uma lista não-vazia com 1 elemento!)",
                "Passo 3: Exatamente 2 elementos são avaliados como Truthy (\"0\" e [0]).",
                "Passo 4: A variável contagem soma 2.",
                "Resultado Final: 2."
            ),
            pedagogicalAlert = "Atenção: \"0\" (string com caractere zero) é Verdadeiro em Python! Apenas a string vazia \"\" é Falsa.",
            keyMethod = "Valores Falsy: 0, \"\", [], {}, set(), None. Strings e listas não-vazias (\"0\", [0]) são sempre Truthy."
        ),

        UniversalExercise(
            id = "py_04",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Mediano",
            source = "Python Institute / Cláusula else em Laços",
            problemStatement = """O Python possui uma sintaxe exclusiva que permite associar uma cláusula 'else' a laços 'for' e 'while'. Analise o código abaixo e indique a saída impressa:""",
            codeSnippet = """numeros = [2, 4, 6, 8, 10]

for n in numeros:
    if n % 2 != 0:
        print("Encontrou ímpar")
        break
else:
    print("Todos são pares!")""",
            terminalOutput = "Todos são pares!",
            options = listOf(
                "Encontrou ímpar",
                "Todos são pares!",
                "Encontrou ímpar\nTodos são pares!",
                "Nenhuma saída",
                "SyntaxError: invalid syntax"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: O laço 'for' itera sobre cada número da lista [2, 4, 6, 8, 10].",
                "Passo 2: Para todos os números, n % 2 == 0, logo a condição do 'if' nunca é satisfeita e o 'break' nunca é disparado.",
                "Passo 3: Regra do 'else' em laços Python: o bloco 'else' é executado se, e somente se, o laço for concluído NATURALMENTE (isto é, sem ter sido interrompido por um 'break').",
                "Passo 4: Como o laço iterou até o fim sem interrupções, o bloco 'else' executa e imprime 'Todos são pares!'.",
                "Resultado Final: 'Todos são pares!'."
            ),
            pedagogicalAlert = "O 'else' em um laço não significa 'se o if for falso', mas sim 'se o laço terminou sem break'. Se houvesse um break, o else seria ignorado.",
            keyMethod = "for...else: o bloco else roda quando a iteração termina por esgotamento, sem acionar break."
        ),

        UniversalExercise(
            id = "py_05",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Fácil",
            source = "Beecrowd / Iteração com range",
            problemStatement = """Qual será a lista resultante gerada pela chamada list(range(10, 2, -3)) em Python?""",
            options = listOf(
                "[10, 7, 4, 1]",
                "[10, 7, 4]",
                "[10, 8, 6, 4, 2]",
                "[7, 4, 1]",
                "[] (lista vazia)"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A função range(start, stop, step) com step negativo itera de forma decrescente.",
                "Passo 2: Começa em start = 10.",
                "Passo 3: Aplica o passo de -3: 10 - 3 = 7.",
                "Passo 4: Aplica novamente: 7 - 3 = 4.",
                "Passo 5: Próximo valor seria 4 - 3 = 1, porém stop = 2 e o limite de parada não é inclusivo (a iteração para antes de ultrapassar 2).",
                "Passo 6: Elementos gerados: 10, 7, 4.",
                "Resultado Final: [10, 7, 4]."
            ),
            pedagogicalAlert = "O valor 'stop' no range nunca é incluído! Com step negativo, para assim que o próximo elemento for menor ou igual a stop.",
            keyMethod = "range(10, 2, -3): 10, 7, 4 (o 1 fica fora pois 1 < 2)"
        ),

        UniversalExercise(
            id = "py_06",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Listas, Tuplas & Fatiamento",
            difficulty = "Fácil",
            source = "USP / Fatiamento de Sequências",
            problemStatement = """Dada a lista letras = ['A', 'B', 'C', 'D', 'E', 'F'], qual será o resultado retornado pela expressão de fatiamento letras[1:5:2]?""",
            options = listOf(
                "['B', 'D']",
                "['B', 'C', 'D', 'E']",
                "['A', 'C', 'E']",
                "['B', 'D', 'F']",
                "['C', 'E']"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A sintaxe de slice é sequencia[inicio:fim:passo].",
                "Passo 2: inicio = 1 corresponde ao elemento 'B' (índice 0 é 'A').",
                "Passo 3: fim = 5 limita a extração até o índice 4 ('E').",
                "Passo 4: passo = 2 pula de 2 em 2 elementos.",
                "Passo 5: Índice 1: 'B'; Índice 1 + 2 = 3: 'D'; Índice 3 + 2 = 5 (atingiu o limite 'fim').",
                "Resultado Final: ['B', 'D']."
            ),
            pedagogicalAlert = "Lembre-se da indexação base 0: letras[0]='A', letras[1]='B', letras[2]='C', letras[3]='D', letras[4]='E'.",
            keyMethod = "Slice [1:5:2]: pega os índices 1 e 3 => ['B', 'D']"
        ),

        UniversalExercise(
            id = "py_07",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Listas, Tuplas & Fatiamento",
            difficulty = "Difícil",
            source = "Maratona SBC / Mutabilidade & Referências",
            problemStatement = """O código a seguir realiza uma cópia superficial de uma lista contendo sublistas mutáveis. Qual será a saída do print?""",
            codeSnippet = """orig = [[1, 2], [3, 4]]
copia = orig.copy()

copia[0].append(99)
copia.append([5, 6])

print(len(orig[0]), len(orig))""",
            terminalOutput = "3 2",
            options = listOf(
                "2 2",
                "3 2",
                "3 3",
                "2 3",
                "1 2"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: O método .copy() (ou fatiamento [:]) cria uma cópia superficial (shallow copy).",
                "Passo 2: A lista 'copia' é um novo objeto lista, mas os elementos internos ([1, 2] e [3, 4]) continuam sendo referências aos MESMOS objetos da lista 'orig'.",
                "Passo 3: 'copia[0].append(99)' modifica a sublista compartilhada em memória, de modo que orig[0] agora é [1, 2, 99] com tamanho 3!",
                "Passo 4: 'copia.append([5, 6])' adiciona um elemento na lista externa 'copia', sem alterar a lista externa 'orig' (que continua com 2 elementos).",
                "Passo 5: Portanto, len(orig[0]) é 3 e len(orig) é 2.",
                "Resultado Final: 3 2."
            ),
            pedagogicalAlert = "Shallow copy copia apenas o nível externo. Para duplicar recursivamente todas as estruturas internas sem compartilhamento de memória, utilize copy.deepcopy().",
            keyMethod = "Shallow Copy: sublistas mutáveis são compartilhadas por referência; append na sublista afeta a lista original."
        ),

        UniversalExercise(
            id = "py_08",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Dicionários & Sets",
            difficulty = "Fácil",
            source = "Python Institute / Acesso Seguro a Dicionários",
            problemStatement = """Qual é a principal vantagem de utilizar o método dicionario.get(chave, valor_padrao) em vez do operador de indexação dicionario[chave] em Python?""",
            options = listOf(
                "O método .get() executa em tempo O(log n), enquanto [chave] é O(n).",
                "O método .get() nunca levanta KeyError quando a chave não existe, retornando o valor padrão.",
                "O método .get() converte automaticamente tipos incompatíveis de chaves.",
                "O operador [chave] foi depreciado a partir do Python 3.10.",
                "O método .get() impede que o dicionário seja modificado por outras threads."
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Quando acessamos um dicionário via d[chave] e a chave não existe, o Python lança a exceção KeyError.",
                "Passo 2: O método d.get(chave, default) verifica a existência da chave de forma segura.",
                "Passo 3: Se a chave existir, retorna seu valor associado; se não existir, retorna o argumento default (ou None se omitido), sem quebrar a execução do programa.",
                "Resultado Final: Impede a exceção KeyError retornando um valor fallback seguro."
            ),
            pedagogicalAlert = "Use sempre .get(k, default) ao consultar dados vindos de APIs externas ou JSONs que podem ter campos opcionais.",
            keyMethod = "d.get(k, default) evita KeyError ao acessar chaves opcionais ou desconhecidas."
        ),

        UniversalExercise(
            id = "py_09",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Funções & Lambda",
            difficulty = "Difícil",
            source = "Python Core / A Armadilha do Default Mutável",
            problemStatement = """Considere a função abaixo com argumento padrão mutável. Qual será a saída exibida no console após as três chamadas?""",
            codeSnippet = """def adicionar(item, lista=[]):
    lista.append(item)
    return lista

print(adicionar(1))
print(adicionar(2))
print(adicionar(3, []))""",
            terminalOutput = "[1]\n[1, 2]\n[3]",
            options = listOf(
                "[1]\n[2]\n[3]",
                "[1]\n[1, 2]\n[1, 2, 3]",
                "[1]\n[1, 2]\n[3]",
                "[1]\n[2]\n[1, 2, 3]",
                "TypeError: default parameter must be immutable"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Em Python, os argumentos padrão de uma função são avaliados uma única vez, no momento em que a função é DEFINIDA, e não a cada chamada!",
                "Passo 2: Primeira chamada adicionar(1): usa a lista padrão criada na definição, anexa 1 e retorna [1].",
                "Passo 3: Segunda chamada adicionar(2): nenhum segundo argumento foi passado, então reutiliza a MESMA lista padrão, que já contém [1]. Anexa 2 e retorna [1, 2]!",
                "Passo 4: Terceira chamada adicionar(3, []): aqui uma NOVA lista vazia [] foi explicitamente passada como argumento, ignorando o padrão. Anexa 3 e retorna [3].",
                "Resultado Final: [1], [1, 2] e [3]."
            ),
            pedagogicalAlert = "Regra de Ouro do Python: NUNCA use objetos mutáveis (como [], {}) como valor padrão de parâmetros! Use 'lista=None' e dentro da função 'if lista is None: lista = []'.",
            keyMethod = "Argumentos padrão mutáveis são instanciados uma única vez na definição e persistem entre chamadas sucessivas."
        ),

        UniversalExercise(
            id = "py_10",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Tratamento de Exceções",
            difficulty = "Mediano",
            source = "USP / Fluxo de Exceções e Finally",
            problemStatement = """O que será impresso pela execução da função teste() abaixo, atentando para o comportamento do bloco 'finally'?""",
            codeSnippet = """def teste():
    try:
        return 10
    finally:
        print("Bloco Finally!")

res = teste()
print(f"Resultado: {res}")""",
            terminalOutput = "Bloco Finally!\nResultado: 10",
            options = listOf(
                "Resultado: 10",
                "Bloco Finally!\nResultado: 10",
                "Resultado: 10\nBloco Finally!",
                "Bloco Finally!",
                "RuntimeError: return inside try block"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: O bloco 'try' avalia a instrução 'return 10'.",
                "Passo 2: Em Python, a presença de uma cláusula 'finally' garante que ela SEMPRE será executada antes de sair do escopo da função, mesmo que haja uma declaração de 'return' pendente!",
                "Passo 3: O bloco 'finally' é executado primeiro, imprimindo 'Bloco Finally!'.",
                "Passo 4: O valor 10 é retornado para a variável 'res'.",
                "Passo 5: A linha seguinte imprime 'Resultado: 10'.",
                "Resultado Final: 'Bloco Finally!' seguido por 'Resultado: 10'."
            ),
            pedagogicalAlert = "O bloco 'finally' é executado incondicionalmente, mesmo com return, break ou exceções não capturadas. É ideal para fechar conexões e arquivos.",
            keyMethod = "O bloco finally sempre executa antes da finalização do retorno da função."
        ),

        // ========================================================
        // 3. CÁLCULO I COM ELEMENTOS VISUAIS E DIAGNÓSTICO
        // ========================================================
        UniversalExercise(
            id = "calc_vis_01",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Fácil",
            source = "UFRJ / Análise Visual de Gráficos",
            problemStatement = """Com base no gráfico diagnóstico dos limites laterais ao lado, analise o comportamento da função degrau modular f(x) = |x| / x na vizinhança de x = 0. Quais são os valores de lim_{x -> 0⁻} f(x), lim_{x -> 0⁺} f(x) e do limite bilateral lim_{x -> 0} f(x)?""",
            visualDrawableRes = R.drawable.grafico_limites_laterais,
            options = listOf(
                "Pela esquerda = 1; Pela direita = -1; Limite bilateral = 0",
                "Pela esquerda = -1; Pela direita = 1; Limite bilateral = Não Existe",
                "Pela esquerda = 0; Pela direita = 0; Limite bilateral = 0",
                "Pela esquerda = -1; Pela direita = 1; Limite bilateral = 1",
                "Pela esquerda = -∞; Pela direita = +∞; Limite bilateral = Não Existe"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Analisar o gráfico para x < 0 (aproximação pela esquerda, x -> 0⁻): os valores da função permanecem constantes na linha horizontal inferior em y = -1.",
                "Passo 2: Analisar o gráfico para x > 0 (aproximação pela direita, x -> 0⁺): os valores da função permanecem constantes na linha horizontal superior em y = 1.",
                "Passo 3: Aplicar o Teorema Fundamental da Existência do Limite: o limite bilateral lim_{x -> a} f(x) = L existe se, e somente se, os dois limites laterais forem exatamente iguais.",
                "Passo 4: Como L₁ = -1 ≠ L₂ = 1, os limites divergem e o limite bilateral NÃO EXISTE.",
                "Resultado Final: Esquerda = -1, Direita = 1 e Limite bilateral Não Existe."
            ),
            pedagogicalAlert = "O ponto f(0) não precisa estar definido. O limite analisa os limites laterais convergindo para o mesmo patamar. Se houver salto descontínuo, o limite bilateral não existe.",
            keyMethod = "lim(x → 0⁻) = -1 ≠ lim(x → 0⁺) = 1 => lim(x → 0) NÃO EXISTE"
        ),

        UniversalExercise(
            id = "calc_vis_02",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Assíntotas & Infinito",
            difficulty = "Mediano",
            source = "UFRJ / Assíntotas Horizontais e Verticais",
            problemStatement = """Observe o gráfico das assíntotas ao lado correspondente à função racional f(x) = (2x² + 3) / (x² - 4). Identifique as equações das assíntotas verticais e da assíntota horizontal da curva:""",
            visualDrawableRes = R.drawable.grafico_assintotas,
            options = listOf(
                "Verticais: x = 2 e x = -2; Horizontal: y = 2",
                "Verticais: x = 4 e x = -4; Horizontal: y = 0",
                "Verticais: x = 2 e x = -2; Horizontal: y = 3/4",
                "Verticais: x = 0; Horizontal: y = 2",
                "Verticais: x = 2; Horizontal: y = -2"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Assíntotas verticais ocorrem onde o denominador se anula (x² - 4 = 0 => x = ±2) e o numerador não se anula (2(±2)² + 3 = 11 ≠ 0). Logo, retas verticais x = 2 e x = -2.",
                "Passo 2: Assíntota horizontal avalia o limite no infinito: lim_{x -> ±∞} (2x² + 3) / (x² - 4).",
                "Passo 3: Dividir numerador e denominador pela maior potência de x (x²): lim (2 + 3/x²) / (1 - 4/x²) = (2 + 0) / (1 - 0) = 2.",
                "Passo 4: Logo, a reta horizontal é y = 2.",
                "Resultado Final: Assíntotas Verticais em x = ±2 e Assíntota Horizontal em y = 2."
            ),
            pedagogicalAlert = "Em funções racionais com graus iguais no numerador e denominador, a assíntota horizontal é a razão direta entre os coeficientes líderes: 2/1 = 2.",
            keyMethod = "Assíntotas verticais: x² - 4 = 0 => x = ±2 | Assíntota horizontal: lim(x → ∞) = 2/1 = 2"
        ),

        UniversalExercise(
            id = "calc_vis_03",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Domínio & Gráficos",
            difficulty = "Mediano",
            source = "UFRJ / Furo e Indeterminação 0/0",
            problemStatement = """O gráfico ao lado ilustra uma descontinuidade removível (furo) na função f(x) = (x² - 4) / (x - 2) no ponto x = 2. Embora a função não esteja definida em x = 2, calcule o valor do limite lim_{x -> 2} f(x) que preenche visualmente o furo:""",
            visualDrawableRes = R.drawable.grafico_furo_indeterminacao,
            options = listOf(
                "2",
                "4",
                "0",
                "Não Existe",
                "∞"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Substituição direta x = 2 gera a indeterminação 0/0.",
                "Passo 2: Fatorar a diferença de quadrados no numerador: x² - 4 = (x - 2)(x + 2).",
                "Passo 3: Cancelar o fator (x - 2) para todo x ≠ 2 na vizinhança perfurada: lim_{x -> 2} (x + 2).",
                "Passo 4: Substituir x = 2: 2 + 2 = 4.",
                "Passo 5: O gráfico representa exatamente a reta y = x + 2 com uma 'círculo vazio' (furo) na coordenada (2, 4).",
                "Resultado Final: O limite vale 4."
            ),
            pedagogicalAlert = "O furo ocorre porque o fator que zera o denominador também zera o numerador. Ao cancelar o fator comum, encontramos a altura exata do furo.",
            keyMethod = "Fatoração: (x - 2)(x + 2)/(x - 2) = x + 2 => lim(x → 2) = 2 + 2 = 4"
        ),

        UniversalExercise(
            id = "calc_vis_04",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Mediano",
            source = "UFRJ / Reta Tangente",
            problemStatement = """No gráfico da reta tangente ao lado, a curva representa a parábola y = x². Determine a equação da reta tangente à curva no ponto P(1, 1):""",
            visualDrawableRes = R.drawable.grafico_reta_tangente,
            options = listOf(
                "y = 2x - 1",
                "y = 2x + 1",
                "y = x",
                "y = 2x",
                "y = x + 1"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O coeficiente angular (inclinação m) da reta tangente é dado pela derivada da função no ponto de tangência: m = f'(x₀).",
                "Passo 2: Para f(x) = x², sua derivada imediata é f'(x) = 2x.",
                "Passo 3: No ponto x₀ = 1, temos m = f'(1) = 2(1) = 2.",
                "Passo 4: Utilizar a equação fundamental da reta que passa pelo ponto (x₀, y₀) = (1, 1): y - y₀ = m(x - x₀).",
                "Passo 5: y - 1 = 2(x - 1) => y - 1 = 2x - 2 => y = 2x - 1.",
                "Resultado Final: y = 2x - 1."
            ),
            pedagogicalAlert = "A reta tangente toca a curva no ponto de tangência com a mesma inclinação instantânea da função. A inclinação m é SEMPRE f'(x₀).",
            keyMethod = "f'(x) = 2x => m = f'(1) = 2 => y - 1 = 2(x - 1) => y = 2x - 1"
        ),

        UniversalExercise(
            id = "calc_vis_05",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Otimização & Estudo de Funções",
            difficulty = "Mediano",
            source = "UFRJ / Pontos Críticos e Extremos",
            problemStatement = """Com base no gráfico diagnóstico de estudo de funções ao lado, identifique a condição matemática necessária para a existência de um ponto de máximo ou mínimo local diferenciável em x = c:""",
            visualDrawableRes = R.drawable.grafico_estudo_funcao_max_min,
            options = listOf(
                "f(c) = 0 e f'(c) > 0",
                "f'(c) = 0 (reta tangente horizontal) ou f'(c) não existe",
                "f''(c) = 0 obrigatoriamente",
                "f(c) = f'(c) = f''(c)",
                "lim_{x -> c} f(x) = ∞"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: De acordo com o Teorema de Fermat para extremos locais, se f atinge um extremo relativo em c e f é diferenciável em c, então f'(c) = 0.",
                "Passo 2: Geometricamente, f'(c) = 0 significa que a reta tangente à curva é estritamente horizontal.",
                "Passo 3: Um ponto crítico de uma função ocorre onde f'(c) = 0 ou onde f'(c) não existe (como bicos ou cúspides).",
                "Resultado Final: f'(c) = 0 ou f'(c) não existe."
            ),
            pedagogicalAlert = "f'(c) = 0 é condição necessária, mas não suficiente! Por exemplo, para f(x) = x³, f'(0) = 0, mas x = 0 é ponto de inflexão e não de extremo.",
            keyMethod = "Pontos críticos: f'(c) = 0 (tangente horizontal) ou f'(c) não existe."
        ),

        UniversalExercise(
            id = "calc_vis_06",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Fácil",
            source = "UFRJ / Área sob a Curva e TFC",
            problemStatement = """O gráfico visual da integral ao lado ilustra a região plana delimitada sob a parábola f(x) = x², o eixo x e as retas verticais x = 0 e x = 3. Calcule o valor exato da área dessa região utilizando o Teorema Fundamental do Cálculo: ∫₀³ x² dx.""",
            visualDrawableRes = R.drawable.grafico_integral_area,
            options = listOf(
                "3",
                "6",
                "9",
                "12",
                "27"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Pela interpretação geométrica, a área sob uma curva contínua e positiva f(x) ≥ 0 no intervalo [a, b] é dada pela integral definida A = ∫_a^b f(x) dx.",
                "Passo 2: Encontrar a primitiva de f(x) = x² pela regra da potência de integração: F(x) = x^(2+1) / (2+1) = x³ / 3.",
                "Passo 3: Aplicar o Teorema Fundamental do Cálculo: ∫₀³ x² dx = F(3) - F(0).",
                "Passo 4: F(3) = (3)³ / 3 = 27 / 3 = 9.",
                "Passo 5: F(0) = 0³ / 3 = 0.",
                "Passo 6: Área = 9 - 0 = 9 unidades de área.",
                "Resultado Final: 9."
            ),
            pedagogicalAlert = "Em integrais definidas, a constante de integração + C se cancela na subtração F(b) - F(a), não sendo necessário escrevê-la.",
            keyMethod = "TFC: ∫₀³ x² dx = [x³/3]₀³ = 27/3 - 0 = 9"
        ),

        UniversalExercise(
            id = "calc_vis_07",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Mediano",
            source = "UFRJ / Aproximação Linear e Diferenciais",
            problemStatement = """Com base no gráfico de aproximação linear ao lado, a linearização de uma função diferenciável f(x) em torno de x = a é dada pela equação da reta tangente L(x) = f(a) + f'(a)(x - a). Utilize a linearização de f(x) = √x em a = 4 para estimar o valor aproximado de √4,08:""",
            visualDrawableRes = R.drawable.grafico_aproximacao_linear,
            options = listOf(
                "2,01",
                "2,02",
                "2,04",
                "2,08",
                "2,16"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Para f(x) = √x = x^(1/2), sua derivada é f'(x) = (1/2) x^(-1/2) = 1 / (2√x).",
                "Passo 2: Avaliar no ponto base a = 4: f(4) = √4 = 2 e f'(4) = 1 / (2√4) = 1 / (2 × 2) = 1/4 = 0,25.",
                "Passo 3: Montar a aproximação linear L(x): L(x) = f(4) + f'(4)(x - 4) = 2 + 0,25(x - 4).",
                "Passo 4: Para x = 4,08: L(4,08) = 2 + 0,25(4,08 - 4) = 2 + 0,25(0,08).",
                "Passo 5: 0,25 × 0,08 = 0,02 => L(4,08) = 2 + 0,02 = 2,02.",
                "Resultado Final: √4,08 ≈ 2,02."
            ),
            pedagogicalAlert = "A aproximação linear substitui a curva pela reta tangente nas proximidades do ponto de contato, sendo a base do cálculo numérico e diferenciais em engenharia.",
            keyMethod = "L(x) = f(a) + f'(a)(x - a) => L(4,08) = 2 + 0,25(0,08) = 2,02"
        ),

        // ========================================================
        // NOVOS EXERCÍCIOS CURADOS: PYTHON CONDICIONAIS & OPERADORES
        // ========================================================
        UniversalExercise(
            id = "py_if_04",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Fácil",
            source = "Python Brasil / Expressões Condicionais Ternárias",
            problemStatement = """O que será exibido no console após a execução do script Python abaixo, que utiliza expressões condicionais encadeadas (operador ternário inline)?""",
            codeSnippet = """nota = 6.5
status = "Aprovado" if nota >= 7.0 else "Exame" if nota >= 5.0 else "Reprovado"
print(f"Situação do Aluno: {status}")""",
            terminalOutput = "Situação do Aluno: Exame",
            options = listOf(
                "Situação do Aluno: Aprovado",
                "Situação do Aluno: Exame",
                "Situação do Aluno: Reprovado",
                "SyntaxError: invalid syntax",
                "Situação do Aluno: None"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A variável 'nota' recebe o valor float 6.5.",
                "Passo 2: Avalia-se a primeira condição: nota >= 7.0 (6.5 >= 7.0 é Falso).",
                "Passo 3: Como é falso, o fluxo salta para o primeiro 'else', que contém a segunda condição ternária: 'Exame if nota >= 5.0 else Reprovado'.",
                "Passo 4: Avalia-se nota >= 5.0 (6.5 >= 5.0 é Verdadeiro).",
                "Passo 5: O valor retornado é a string 'Exame', atribuída à variável 'status'.",
                "Resultado Final: 'Situação do Aluno: Exame'."
            ),
            pedagogicalAlert = "O operador ternário em Python segue a sintaxe 'valor_se_verdadeiro if condicao else valor_se_falso'. Quando encadeado, é avaliado da esquerda para a direita.",
            keyMethod = "Avaliação condicional ternária inline com valor intermediário."
        ),

        UniversalExercise(
            id = "py_if_05",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "if e else (Condicionais)",
            difficulty = "Mediano",
            source = "Python Core / Avaliação de Curto-Circuito (Short-Circuit)",
            problemStatement = """Em Python, os operadores lógicos 'or' e 'and' não retornam necessariamente valores booleanos (True/False), mas sim o operando que determinou o resultado (curto-circuito). Qual será a saída do código a seguir?""",
            codeSnippet = """x = [] or "Python"
y = 0 and "Cálculo"
z = "Dev" and [1, 2, 3]

print(x)
print(y)
print(z)""",
            terminalOutput = "Python\n0\n[1, 2, 3]",
            options = listOf(
                "True\nFalse\nTrue",
                "Python\n0\n[1, 2, 3]",
                "[]\nCálculo\nDev",
                "Python\nNone\n[1, 2, 3]",
                "TypeError: unsupported operand types"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Avaliar 'x = [] or \"Python\"'. Como a lista vazia '[]' é avaliada como falsy, o operador 'or' avalia o segundo operando. Sendo 'Python' truthy, ele é retornado diretamente: x = 'Python'.",
                "Passo 2: Avaliar 'y = 0 and \"Cálculo\"'. Como 0 é falsy, a expressão 'and' sofre curto-circuito imediatamente e retorna o próprio 0 sem avaliar o segundo termo: y = 0.",
                "Passo 3: Avaliar 'z = \"Dev\" and [1, 2, 3]'. Como 'Dev' é truthy, o operador 'and' precisa verificar o próximo termo e retorna o último operando avaliado: z = [1, 2, 3].",
                "Resultado Final: Python, 0 e [1, 2, 3]."
            ),
            pedagogicalAlert = "Valores considerados 'falsy' em Python: False, None, 0, 0.0, '', [], (), {}. O operador 'and' retorna o primeiro falsy ou o último operando. O 'or' retorna o primeiro truthy.",
            keyMethod = "Curto-circuito lógico: 'A or B' retorna o primeiro truthy; 'A and B' retorna o primeiro falsy."
        ),

        UniversalExercise(
            id = "py_loop_03",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "for & while (Laços)",
            difficulty = "Mediano",
            source = "Estruturas de Dados / Cláusula else em laços for",
            problemStatement = """Uma das peculiaridades mais elegantes de Python é a presença de uma cláusula 'else' associada a laços 'for' e 'while'. Analise a função busca_alvo e assinale a saída correta:""",
            codeSnippet = """def busca_alvo(lista, alvo):
    for item in lista:
        if item == alvo:
            print(f"Encontrado: {item}")
            break
    else:
        print("Alvo Inexistente!")

busca_alvo([10, 20, 30], 40)
busca_alvo([5, 15, 25], 15)""",
            terminalOutput = "Alvo Inexistente!\nEncontrado: 15",
            options = listOf(
                "Alvo Inexistente!\nEncontrado: 15",
                "Encontrado: 15",
                "Alvo Inexistente!\nAlvo Inexistente!",
                "None\nEncontrado: 15",
                "SyntaxError: else cannot belong to for loop"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Na primeira chamada busca_alvo([10, 20, 30], 40), o loop for itera por todos os 3 elementos sem encontrar 40.",
                "Passo 2: Como o loop NÃO foi interrompido por nenhum 'break', o bloco 'else' do for é disparado, imprimindo 'Alvo Inexistente!'.",
                "Passo 3: Na segunda chamada busca_alvo([5, 15, 25], 15), o número 15 é encontrado no segundo ciclo, imprimindo 'Encontrado: 15'.",
                "Passo 4: O comando 'break' interrompe o loop for imediatamente. O bloco 'else' NÃO é executado.",
                "Resultado Final: 'Alvo Inexistente!' seguido de 'Encontrado: 15'."
            ),
            pedagogicalAlert = "O 'else' em um laço for/while só roda se o laço terminar naturalmente sem encontrar um 'break'. É o padrão canônico para buscas sem flag booleana adicional.",
            keyMethod = "for...else: o bloco else só roda quando o loop termina sem break."
        ),

        UniversalExercise(
            id = "py_func_03",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Funções & Lambda",
            difficulty = "Difícil",
            source = "Python Pitfalls / Default Mutable Arguments Trap",
            problemStatement = """O que acontecerá ao executar as duas chamadas consecutivas da função acumula() abaixo, considerando o ciclo de vida do argumento padrão em Python?""",
            codeSnippet = """def acumula(valor, lista=[]):
    lista.append(valor)
    return lista

p1 = acumula(10)
p2 = acumula(20)

print(p1)
print(p2)""",
            terminalOutput = "[10, 20]\n[10, 20]",
            options = listOf(
                "[10]\n[20]",
                "[10, 20]\n[10, 20]",
                "[10]\n[10, 20]",
                "[10, 20]\n[20]",
                "TypeError: mutable default parameter"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: Em Python, os argumentos padrão são avaliados UMA ÚNICA VEZ no momento em que a função é definida (tempo de carregamento/compilação), e NÃO a cada chamada!",
                "Passo 2: A lista '[]' criada torna-se um objeto persistente na memória associado ao atributo __defaults__ da função.",
                "Passo 3: Na primeira chamada acumula(10), adiciona-se 10 à lista compartilhada -> [10].",
                "Passo 4: Na segunda chamada acumula(20), a MESMA lista compartilhada recebe o elemento 20 -> [10, 20].",
                "Passo 5: Como tanto p1 quanto p2 apontam para o mesmo endereço de memória na Heap, ambos exibem [10, 20].",
                "Resultado Final: [10, 20] e [10, 20]."
            ),
            pedagogicalAlert = "NUNCA use objetos mutáveis (como listas ou dicionários) como valores padrão de argumentos em funções. O padrão idiomático correto é usar 'def func(val, lista=None): if lista is None: lista = []'.",
            keyMethod = "Argumentos default são avaliados na definição: usar sempre None como sentinela."
        ),

        UniversalExercise(
            id = "py_poo_01",
            discipline = ExerciseDiscipline.PROGRAMMING,
            category = "Python",
            subtopic = "Classes & POO",
            difficulty = "Mediano",
            source = "Programação Orientada a Objetos / Herança e super()",
            problemStatement = """Considere o modelo de classes geométricas a seguir com herança simples e invocação do construtor da classe base com super(). Qual o valor impresso?""",
            codeSnippet = """class Retangulo:
    def __init__(self, largura, altura):
        self.largura = largura
        self.altura = altura

    def area(self):
        return self.largura * self.altura

class Quadrado(Retangulo):
    def __init__(self, lado):
        super().__init__(largura=lado, altura=lado)

q = Quadrado(lado=6)
print(f"Área do Quadrado: {q.area()} cm²")""",
            terminalOutput = "Área do Quadrado: 36 cm²",
            options = listOf(
                "Área do Quadrado: 24 cm²",
                "Área do Quadrado: 36 cm²",
                "Área do Quadrado: 12 cm²",
                "AttributeError: 'Quadrado' has no attribute 'area'",
                "TypeError: missing argument 'altura'"
            ),
            correctIndex = 1,
            solutionSteps = listOf(
                "Passo 1: A classe Quadrado herda todos os métodos públicos de Retangulo, incluindo def area(self).",
                "Passo 2: O construtor de Quadrado invoca super().__init__(largura=6, altura=6).",
                "Passo 3: self.largura passa a ser 6 e self.altura passa a ser 6 no objeto instanciado 'q'.",
                "Passo 4: Ao chamar q.area(), executa-se self.largura * self.altura = 6 * 6 = 36.",
                "Resultado Final: 'Área do Quadrado: 36 cm²'."
            ),
            pedagogicalAlert = "Herança permite reaproveitamento de lógica. A função super() delega a inicialização de atributos invariantes à superclasse de forma segura.",
            keyMethod = "Herança e polimorfismo: super().__init__() inicializa os atributos herdados."
        ),

        // ========================================================
        // NOVOS EXERCÍCIOS CURADOS: CÁLCULO I (TRIGONOMÉTRICOS & INTEGRAIS)
        // ========================================================
        UniversalExercise(
            id = "calc_vis_08",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Limites & Continuidade",
            difficulty = "Mediano",
            source = "UFRJ / P1 - Limites Trigonométricos Fundamentais",
            problemStatement = """Calcule o limite trigonométrico fundamental utilizando a propriedade de mudança de variável: \[ \\lim_{x \\to 0} \\frac{\\sin(5x)}{3x} \]""",
            options = listOf(
                "0",
                "1",
                "3/5",
                "5/3",
                "Não Existe"
            ),
            correctIndex = 3,
            solutionSteps = listOf(
                "Passo 1: Relembrar o Limite Fundamental Trigonométrico de Euler: lim_{u -> 0} (sin(u) / u) = 1.",
                "Passo 2: Isolar a fração de coeficientes para igualar o argumento interno do seno: sin(5x) exige 5x no denominador.",
                "Passo 3: Multiplicar e dividir por 5 e reorganizar os denominadores:",
                """\[ \lim_{x \to 0} \frac{\sin(5x)}{3x} = \frac{5}{3} \cdot \lim_{x \to 0} \frac{\sin(5x)}{5x} \]""",
                "Passo 4: Fazendo u = 5x, quando x -> 0, temos u -> 0. Logo, lim_{u -> 0} (sin(u)/u) = 1.",
                "Passo 5: Multiplicar pelo fator constante: (5/3) × 1 = 5/3.",
                "Resultado Final: 5/3."
            ),
            pedagogicalAlert = "A regra prática para limites da forma sin(ax) / (bx) quando x -> 0 é sempre igual à razão dos coeficientes a / b.",
            keyMethod = "lim(x -> 0) [sin(ax) / (bx)] = a / b => 5 / 3"
        ),

        UniversalExercise(
            id = "calc_vis_09",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Derivadas & Retas Tangentes",
            difficulty = "Mediano",
            source = "Cálculo Diferencial / Regra do Quociente",
            problemStatement = """Seja a função racional f(x) = (2x + 1)/(x - 3). Qual é o valor numérico exato da derivada f'(4) ?""",
            options = listOf(
                "-7",
                "-5",
                "7",
                "9",
                "-9"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplicar a Regra do Quociente para f(x) = u/v: f'(x) = [u'·v - u·v'] / v².",
                "Passo 2: Identificar u(x) = 2x + 1 => u'(x) = 2; e v(x) = x - 3 => v'(x) = 1.",
                "Passo 3: Montar a expressão analítica:",
                """\[ f'(x) = \frac{2(x - 3) - (2x + 1)(1)}{(x - 3)^2} = \frac{2x - 6 - 2x - 1}{(x - 3)^2} = \frac{-7}{(x - 3)^2} \]""",
                "Passo 4: Avaliar no ponto x = 4:",
                """\[ f'(4) = \frac{-7}{(4 - 3)^2} = \frac{-7}{(1)^2} = -7 \]""",
                "Resultado Final: f'(4) = -7."
            ),
            pedagogicalAlert = "Atenção ao sinal negativo da regra do quociente: o sinal de menos distribui para todos os termos do produto u·v'!",
            keyMethod = "Regra do Quociente: f'(x) = [u'v - uv'] / v² => f'(4) = -7"
        ),

        UniversalExercise(
            id = "calc_vis_10",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Fácil",
            source = "Teorema Fundamental do Cálculo / Área sob a Curva",
            problemStatement = """Calcule a integral definida que representa a área sob a parábola y = x² no intervalo fechado [0, 3]: ∫_0^3 x² dx""",
            options = listOf(
                "3",
                "6",
                "9",
                "12",
                "27"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Determinar a primitiva da função pela regra da potência de integração: ∫ x^n dx = (x^(n+1))/(n+1) + C.",
                "Passo 2: Primitiva F(x):",
                """\[ F(x) = \int x^2 \, dx = \frac{x^3}{3} \]""",
                "Passo 3: Aplicar o Teorema Fundamental do Cálculo: ∫_a^b f(x) dx = F(b) - F(a):",
                """\[ \left[ \frac{x^3}{3} \right]_0^3 = \frac{3^3}{3} - \frac{0^3}{3} = \frac{27}{3} - 0 = 9 \]""",
                "Resultado Final: 9."
            ),
            pedagogicalAlert = "Ao integrar potências, o expoente aumenta em 1 e divide-se pelo novo expoente. Na integral definida, a constante C cancela-se.",
            keyMethod = "∫_0^3 x² dx = [x³/3]_0^3 = 27/3 = 9"
        ),

        UniversalExercise(
            id = "calc_vis_11",
            discipline = ExerciseDiscipline.CALCULUS,
            category = "Cálculo I",
            subtopic = "Integrais & TFC",
            difficulty = "Mediano",
            source = "Cálculo Integral / Método de Substituição Simples",
            problemStatement = """Resolva a integral indefinida aplicando a substituição u = x²: ∫ 2x · e^(x²) dx""",
            options = listOf(
                "e^(x²) + C",
                "2 e^(x²) + C",
                "(1/2) e^(x²) + C",
                "x² e^(x²) + C",
                "e^(2x) + C"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Escolher a variável auxiliar: u = x².",
                "Passo 2: Calcular o diferencial du: du = (d/dx(x²)) dx = 2x dx.",
                "Passo 3: Observar que o termo '2x dx' já está presente exatamente no integrando!",
                "Passo 4: Reescrever a integral em termos de u:",
                """\[ \int 2x \cdot e^{x^2} \, dx = \int e^u \, du \]""",
                "Passo 5: A integral da exponencial na base natural é ela própria: ∫ e^u du = e^u + C.",
                "Passo 6: Voltar à variável original x: e^(x²) + C.",
                "Resultado Final: e^(x²) + C."
            ),
            pedagogicalAlert = "O método da substituição é a regra da cadeia percorrida no sentido inverso. Sempre verifique se o diferencial du está presente como fator multiplicativo.",
            keyMethod = "Substituição: u = x² => du = 2x dx => ∫ e^u du = e^(x²) + C"
        ),

        // ========================================================
        // NOVOS EXERCÍCIOS CURADOS: MATEMÁTICA BÁSICA (ENEM)
        // ========================================================
        UniversalExercise(
            id = "mb_12",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Porcentagem e Variação",
            difficulty = "Fácil",
            source = "ENEM / Aumentos Sucessivos Acumulados",
            problemStatement = """Um plano de telefonia móvel sofreu um reajuste de 20% no primeiro semestre e, seis meses depois, novo aumento de 30% sobre a fatura já reajustada. Qual foi o percentual total de aumento real acumulado?""",
            options = listOf(
                "50%",
                "52%",
                "54%",
                "56%",
                "60%"
            ),
            correctIndex = 3,
            solutionSteps = listOf(
                "Passo 1: Fator do primeiro aumento de 20%: 1 + 0,20 = 1,20.",
                "Passo 2: Fator do segundo aumento de 30%: 1 + 0,30 = 1,30.",
                "Passo 3: Fator acumulado total através da multiplicação encadeada:",
                """\[ F_{\text{total}} = 1,20 \times 1,30 = 1,56 \]""",
                "Passo 4: Converter o fator para taxa percentual de aumento: (1,56 - 1) × 100% = 56%.",
                "Resultado Final: 56% de aumento real."
            ),
            pedagogicalAlert = "A pegadinha clássica do ENEM é somar 20% + 30% = 50%. Aumentos sucessivos SEMPRE superam a soma simples porque o segundo aumento incide sobre uma base maior!",
            keyMethod = "F_total = 1,20 × 1,30 = 1,56 => Aumento acumulado de 56%"
        ),

        UniversalExercise(
            id = "mb_13",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Mediano",
            source = "ENEM / Produção Industrial e Horas de Trabalho",
            problemStatement = """Em uma fábrica automatizada, 5 máquinas idênticas produzem 600 peças de reposição operando durante 4 horas diárias. Se a fábrica colocar 8 dessas máquinas para operar durante 6 horas diárias, quantas peças serão produzidas?""",
            visualTable = VisualTable(
                title = "Quadro de Grandezas Proporcionais",
                headers = listOf("Máquinas", "Horas/Dia", "Peças Produzidas"),
                rows = listOf(
                    listOf("5", "4 h", "600 peças"),
                    listOf("8", "6 h", "x peças")
                )
            ),
            options = listOf(
                "1.000 peças",
                "1.200 peças",
                "1.440 peças",
                "1.600 peças",
                "1.800 peças"
            ),
            correctIndex = 2,
            solutionSteps = listOf(
                "Passo 1: Analisar as proporções em relação à quantidade de Peças (x):",
                "• Mais máquinas produzem MAIS peças (Diretamente Proporcional -> 8/5).",
                "• Mais horas de operação produzem MAIS peças (Diretamente Proporcional -> 6/4).",
                "Passo 2: Montar a equação mantendo a fração com x isolada:",
                """\[ \frac{x}{600} = \frac{8}{5} \times \frac{6}{4} \]""",
                "Passo 3: Simplificar: 6/4 = 1,5 e 8/5 = 1,6.",
                """\[ \frac{x}{600} = 1,6 \times 1,5 = 2,4 \]""",
                "Passo 4: Multiplicar pelo valor base: x = 600 × 2,4 = 1.440 peças.",
                "Resultado Final: 1.440 peças."
            ),
            pedagogicalAlert = "Todas as grandezas foram diretamente proporcionais à produção. Multiplicar 600 pelo produto das razões dá o resultado imediato.",
            keyMethod = "x = 600 × (8/5) × (6/4) = 600 × 2,4 = 1.440 peças"
        )
    )

    /**
     * Retorna o acervo unificado consolidado contendo os novos exercícios curados
     * (com tabelas e gráficos diagnósticos) e todo o banco pré-existente de Cálculo.
     */
    fun getAllExercises(): List<UniversalExercise> {
        val convertedCalculus = CalculusExercisesBank.allExercises.map { calcItem ->
            UniversalExercise(
                id = calcItem.id,
                discipline = ExerciseDiscipline.CALCULUS,
                category = "Cálculo I",
                subtopic = calcItem.topicTitle,
                difficulty = calcItem.difficulty,
                source = calcItem.source,
                problemStatement = calcItem.problemStatement,
                options = calcItem.options,
                correctIndex = calcItem.correctIndex,
                solutionSteps = calcItem.solutionSteps,
                pedagogicalAlert = calcItem.pedagogicalAlert,
                keyMethod = calcItem.keyMethod,
                xpReward = calcItem.xpReward
            )
        }

        val convertedBasicMath = BasicMathCurriculum.basicMathUnit.lessons.flatMap { lesson ->
            lesson.mathExercises.map { me ->
                UniversalExercise(
                    id = me.id,
                    discipline = ExerciseDiscipline.BASIC_MATH,
                    category = "Matemática Básica",
                    subtopic = lesson.title,
                    difficulty = when (lesson.id) {
                        "mb_l1", "mb_l2", "mb_l3", "mb_l4" -> "Fácil"
                        "mb_l10", "mb_l11", "mb_l12" -> "Difícil"
                        else -> "Mediano"
                    },
                    source = "Ciclo de Matemática Básica • ENEM & Vestibulares",
                    problemStatement = me.problemStatement,
                    options = me.options,
                    correctIndex = me.correctIndex,
                    solutionSteps = listOf(me.stepByStepSolution).filter { it.isNotBlank() },
                    pedagogicalAlert = me.hint,
                    keyMethod = "Resolução explicativa fundamentada nos princípios de matemática básica.",
                    xpReward = 15
                )
            }
        }

        return curatedExercises +
                GeoGebraGeometryExercises.exercises +
                ExtendedExerciseCollection.additionalExercises +
                ExpandedExerciseVariants.variantExercises +
                ComprehensivePythonExercises.exercises +
                ComprehensiveCalculusExercises.exercises +
                ComprehensiveBasicMathExercises.exercises +
                convertedCalculus +
                convertedBasicMath +
                externalEnemExercises
    }

    private var externalEnemExercises: List<UniversalExercise> = emptyList()

    /**
     * Registra exercícios externos (como o banco de dados oficial do ENEM carregado do JSON).
     */
    fun registerEnemExercises(exercises: List<UniversalExercise>) {
        externalEnemExercises = exercises
    }

    /**
     * Filtragem flexível multidimensional:
     * - Busca textual (pesquisa por termos no enunciado, código, método, ID)
     * - Matéria / Disciplina (Todas, Programação, Cálculo, Matemática Básica, ENEM)
     * - Tópico específico
     * - Dificuldade (Todas, Fácil, Mediano, Difícil)
     * - Filtro rápido de itens com elementos visuais (gráficos, tabelas, código)
     */
    fun filter(
        discipline: ExerciseDiscipline = ExerciseDiscipline.ALL,
        subtopic: String = "all",
        difficulty: String = "all",
        query: String = "",
        onlyWithVisuals: Boolean = false
    ): List<UniversalExercise> {
        val q = query.trim().lowercase()

        return getAllExercises().filter { item ->
            val matchesDiscipline = when (discipline) {
                ExerciseDiscipline.ALL -> true
                ExerciseDiscipline.ENEM -> item.discipline == ExerciseDiscipline.ENEM ||
                        item.source.contains("ENEM", ignoreCase = true) ||
                        item.category.contains("ENEM", ignoreCase = true)
                else -> item.discipline == discipline
            }

            val matchesSubtopic = when (subtopic) {
                "all" -> true
                else -> item.subtopic.equals(subtopic, ignoreCase = true) ||
                        item.subtopic.contains(subtopic, ignoreCase = true)
            }

            val matchesDifficulty = when (difficulty) {
                "all" -> true
                else -> item.difficulty.equals(difficulty, ignoreCase = true)
            }

            val hasVisuals = item.visualDrawableRes != null ||
                    item.visualTable != null ||
                    item.codeSnippet != null ||
                    item.terminalOutput != null

            val matchesVisuals = !onlyWithVisuals || hasVisuals

            val matchesQuery = if (q.isBlank()) {
                true
            } else {
                item.id.lowercase().contains(q) ||
                item.problemStatement.lowercase().contains(q) ||
                item.category.lowercase().contains(q) ||
                item.subtopic.lowercase().contains(q) ||
                item.keyMethod.lowercase().contains(q) ||
                item.source.lowercase().contains(q) ||
                (item.codeSnippet?.lowercase()?.contains(q) == true) ||
                item.options.any { it.lowercase().contains(q) }
            }

            matchesDiscipline && matchesSubtopic && matchesDifficulty && matchesVisuals && matchesQuery
        }
    }
}
