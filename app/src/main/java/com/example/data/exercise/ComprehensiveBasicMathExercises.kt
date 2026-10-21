package com.example.data.exercise

/**
 * Acervo completo de exercícios de Matemática Básica (ENEM/Vestibulares)
 * com distribuição balanceada: 2 Fáceis, 2 Médias e 2 Difíceis para cada assunto do currículo.
 */
object ComprehensiveBasicMathExercises {

    val exercises: List<UniversalExercise> = listOf(
        // ========================================================
        // 1. FRAÇÕES E DECIMAIS
        // ========================================================
        UniversalExercise(
            id = "mb_frac_facil_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Frações e Decimais",
            difficulty = "Fácil",
            source = "ENEM / Operações com Frações",
            problemStatement = "Qual é o resultado da soma de frações com denominadores diferentes: 2/5 + 1/3?",
            options = listOf(
                "11/15",
                "3/8",
                "3/15",
                "2/15",
                "7/15"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Encontrar o mínimo múltiplo comum entre os denominadores 5 e 3: MMC(5, 3) = 15.",
                "Passo 2: Reduzir ao mesmo denominador:",
                "  2/5 = (2 × 3) / (5 × 3) = 6/15.",
                "  1/3 = (1 × 5) / (3 × 5) = 5/15.",
                "Passo 3: Somar os numeradores: 6/15 + 5/15 = 11/15.",
                "Resultado Final: 11/15."
            ),
            pedagogicalAlert = "NUNCA some numerador com numerador e denominador com denominador (2/5 + 1/3 ≠ 3/8). Denominadores exigem MMC.",
            keyMethod = "Soma de frações com denominadores diferentes via MMC"
        ),
        UniversalExercise(
            id = "mb_frac_facil_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Frações e Decimais",
            difficulty = "Fácil",
            source = "ENEM / Conversão Decimal-Fração",
            problemStatement = "A fração irredutível equivalente ao número decimal 0,375 é:",
            options = listOf(
                "3/8",
                "375/100",
                "7/16",
                "3/4",
                "1/3"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Escrever o decimal como fração decimal com potência de 10: 0,375 = 375 / 1000.",
                "Passo 2: Simplificar dividindo sucessivamente por fatores comuns (ou por 125):",
                "  375 ÷ 125 = 3.",
                "  1000 ÷ 125 = 8.",
                "Passo 3: A fração irredutível é 3/8.",
                "Resultado Final: 3/8."
            ),
            pedagogicalAlert = "Três casas decimais dividem por 1.000; simplifique dividindo por 5 repetidas vezes se não souber o MDC direto.",
            keyMethod = "Conversão de decimal exato para fração irredutível"
        ),
        UniversalExercise(
            id = "mb_frac_med_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Frações e Decimais",
            difficulty = "Mediano",
            source = "ENEM / Dízima Periódica Simples",
            problemStatement = "Qual é a fração geratriz da dízima periódica simples 0,777...?",
            options = listOf(
                "7/9",
                "7/10",
                "77/100",
                "7/99",
                "7/8"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Seja x = 0,777...",
                "Passo 2: Multiplicar por 10 para deslocar um período: 10x = 7,777...",
                "Passo 3: Subtrair a primeira equação da segunda para eliminar a parte periódica infinita:",
                "  10x - x = 7,777... - 0,777...",
                "  9x = 7 => x = 7/9.",
                "Resultado Final: 7/9."
            ),
            pedagogicalAlert = "Dízimas periódicas simples colocam o algarismo que se repete sobre tantos noves quantos forem seus dígitos.",
            keyMethod = "Fração geratriz pelo método algébrico de eliminação periódica: 10x - x = 9x"
        ),
        UniversalExercise(
            id = "mb_frac_med_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Frações e Decimais",
            difficulty = "Mediano",
            source = "ENEM / Problema de Resto Fracionário",
            problemStatement = "Lucas gastou 1/3 do seu salário com aluguel e 2/5 do que sobrou com alimentação. Se ainda lhe restaram R$ 1.200,00, qual é o salário total de Lucas?",
            options = listOf(
                "R$ 3.000,00",
                "R$ 2.400,00",
                "R$ 3.600,00",
                "R$ 4.000,00",
                "R$ 2.800,00"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Lucas gastou 1/3 com aluguel, restando: 1 - 1/3 = 2/3 do salário.",
                "Passo 2: Gastou 2/5 DO QUE SOBROU com alimentação: (2/5) · (2/3) = 4/15 do salário total.",
                "Passo 3: Total gasto até agora: 1/3 + 4/15 = 5/15 + 4/15 = 9/15 = 3/5 do salário.",
                "Passo 4: A fração que restou foi: 1 - 3/5 = 2/5 do salário.",
                "Passo 5: Como 2/5 correspondem a R$ 1.200,00, cada 1/5 equivale a 1.200 ÷ 2 = R$ 600,00.",
                "Passo 6: O salário total (5/5) é: 5 × 600 = R$ 3.000,00.",
                "Resultado Final: R$ 3.000,00."
            ),
            pedagogicalAlert = "Cuidado com o termo 'do que sobrou': indica multiplicação de frações, não subtração direta.",
            keyMethod = "Resolução de problemas de frações encadeadas: resto = (1 - f₁) · (1 - f₂)"
        ),
        UniversalExercise(
            id = "mb_frac_dif_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Frações e Decimais",
            difficulty = "Difícil",
            source = "FUVEST / Dízima Periódica Composta",
            problemStatement = "Determine a fração geratriz irredutível da dízima periódica composta 1,2333...:",
            options = listOf(
                "37/30",
                "123/99",
                "123/90",
                "111/90",
                "37/33"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Seja x = 1,2333...",
                "Passo 2: Multiplicar por 10 para isolar o anteperíodo: 10x = 12,333...",
                "Passo 3: Multiplicar por 100 para isolar um período completo: 100x = 123,333...",
                "Passo 4: Subtrair as duas equações: 100x - 10x = 123,333... - 12,333... => 90x = 111.",
                "Passo 5: x = 111 / 90. Simplificando por 3: 111 ÷ 3 = 37 e 90 ÷ 3 = 30.",
                "Resultado Final: 37/30 (pois 37/30 = 1,2333...)."
            ),
            pedagogicalAlert = "Em dízimas compostas, o denominador é formado por noves (número de algarismos do período) seguidos de zeros (número de algarismos do anteperíodo).",
            keyMethod = "Fração geratriz de dízima periódica composta via sistema 100x - 10x"
        ),
        UniversalExercise(
            id = "mb_frac_dif_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Frações e Decimais",
            difficulty = "Difícil",
            source = "ENEM / Frações Contínuas e Escalas",
            problemStatement = """Simplifique a fração complexa contínua:
1 / (1 + 1 / (1 + 1/2))""",
            options = listOf(
                "3/5",
                "2/3",
                "5/3",
                "1/2",
                "5/8"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Resolver de baixo para cima. O denominador mais interno é 1 + 1/2 = 3/2.",
                "Passo 2: O inverso de 3/2 é 1 / (3/2) = 2/3.",
                "Passo 3: A próxima soma intermediária é 1 + 2/3 = 5/3.",
                "Passo 4: A fração global é o inverso de 5/3: 1 / (5/3) = 3/5.",
                "Resultado Final: 3/5 (ou 0,6)."
            ),
            pedagogicalAlert = "Frações em cascata (contínuas) devem ser calculadas estritamente da base inferior para o topo.",
            keyMethod = "Simplificação de frações contínuas bottom-up"
        ),

        // ========================================================
        // 2. MMC E MDC
        // ========================================================
        UniversalExercise(
            id = "mb_mmc_facil_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "MMC e MDC",
            difficulty = "Fácil",
            source = "ENEM / Cálculo Direto de MMC",
            problemStatement = "Qual é o Mínimo Múltiplo Comum entre os números 12 e 20?",
            options = listOf(
                "60",
                "240",
                "4",
                "120",
                "40"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Fatoração de 12: 12 = 2² × 3.",
                "Passo 2: Fatoração de 20: 20 = 2² × 5.",
                "Passo 3: O MMC é o produto dos fatores primos comuns e não comuns com os maiores expoentes:",
                "  MMC = 2² × 3 × 5 = 4 × 15 = 60.",
                "Resultado Final: 60."
            ),
            pedagogicalAlert = "O MDC seria 4 (menores expoentes comuns); o MMC é 60 (maiores expoentes).",
            keyMethod = "Cálculo de MMC por decomposição em fatores primos"
        ),
        UniversalExercise(
            id = "mb_mmc_facil_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "MMC e MDC",
            difficulty = "Fácil",
            source = "ENEM / Cálculo Direto de MDC",
            problemStatement = "Qual é o Máximo Divisor Comum (MDC) entre os números 36 e 48?",
            options = listOf(
                "12",
                "6",
                "4",
                "24",
                "144"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Fatoração de 36: 36 = 2² × 3².",
                "Passo 2: Fatoração de 48: 48 = 2⁴ × 3.",
                "Passo 3: O MDC é o produto dos fatores primos APENAS COMUNS com os MENORES expoentes:",
                "  MDC = 2² × 3¹ = 4 × 3 = 12.",
                "Resultado Final: 12."
            ),
            pedagogicalAlert = "O MDC divide ambos exatamente; 12 é o maior número que divide 36 e 48 ao mesmo tempo.",
            keyMethod = "Cálculo de MDC por fatores comuns de menor expoente"
        ),
        UniversalExercise(
            id = "mb_mmc_med_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "MMC e MDC",
            difficulty = "Mediano",
            source = "ENEM / Medicamentos Periódicos (MMC)",
            problemStatement = "Um paciente toma um remédio A a cada 6 horas, um remédio B a cada 8 horas e um remédio C a cada 12 horas. Se ele tomou os três juntos às 08h00 de segunda-feira, quando ele voltará a tomar os três remédios simultaneamente?",
            options = listOf(
                "Às 08h00 de terça-feira (após 24 horas)",
                "Às 20h00 de segunda-feira (após 12 horas)",
                "Às 00h00 de terça-feira (após 16 horas)",
                "Às 08h00 de quarta-feira (após 48 horas)",
                "Às 18h00 de segunda-feira (após 10 horas)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Problemas de sincronicidade futura periódica resolvem-se por MMC.",
                "Passo 2: Calcular MMC(6, 8, 12):",
                "  6 = 2 × 3",
                "  8 = 2³",
                "  12 = 2² × 3",
                "  MMC = 2³ × 3 = 8 × 3 = 24 horas.",
                "Passo 3: Somar 24 horas a segunda-feira às 08h00 resulta em terça-feira às 08h00.",
                "Resultado Final: Terça-feira às 08h00."
            ),
            pedagogicalAlert = "Sempre que o enunciado falar em 'tomar juntos novamente', 'encontrar na pista' ou 'piscar ao mesmo tempo', use MMC.",
            keyMethod = "Aplicação prática de MMC para eventos periódicos sincronizados"
        ),
        UniversalExercise(
            id = "mb_mmc_med_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "MMC e MDC",
            difficulty = "Mediano",
            source = "ENEM / Divisão em Lotes Iguais (MDC)",
            problemStatement = "Um marceneiro tem duas tábuas de madeira, uma com 180 cm de comprimento e outra com 240 cm. Ele deseja cortá-las em pedaços de mesmo comprimento, sendo este o maior possível e sem sobras. Qual deve ser o comprimento de cada pedaço e quantos pedaços ele obterá no total?",
            options = listOf(
                "60 cm por pedaço (total de 7 pedaços)",
                "30 cm por pedaço (total de 14 pedaços)",
                "40 cm por pedaço (total de 10 pedaços)",
                "120 cm por pedaço (total de 3 pedaços)",
                "60 cm por pedaço (total de 6 pedaços)"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O problema pede para 'dividir em partes de mesmo tamanho' e que seja o 'maior possível': isto é a definição de MDC.",
                "Passo 2: Calcular MDC(180, 240):",
                "  180 = 2² × 3² × 5",
                "  240 = 2⁴ × 3 × 5",
                "  MDC = 2² × 3¹ × 5¹ = 4 × 15 = 60 cm.",
                "Passo 3: Comprimento de cada pedaço: 60 cm.",
                "Passo 4: Total de pedaços da tábua de 180 cm: 180 ÷ 60 = 3 pedaços.",
                "Passo 5: Total de pedaços da tábua de 240 cm: 240 ÷ 60 = 4 pedaços.",
                "Passo 6: Total geral de pedaços: 3 + 4 = 7 pedaços.",
                "Resultado Final: 60 cm por pedaço (7 pedaços no total)."
            ),
            pedagogicalAlert = "Atenção: o MDC dá o tamanho de cada pedaço (60 cm). O número de pedaços é a soma das divisões (3 + 4 = 7).",
            keyMethod = "Divisão equitativa máxima com MDC: tamanho = MDC e quantidade = soma dos quocientes"
        ),
        UniversalExercise(
            id = "mb_mmc_dif_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "MMC e MDC",
            difficulty = "Difícil",
            source = "OBMEP / Propriedade MMC(a,b) · MDC(a,b) = a · b",
            problemStatement = "O produto de dois números inteiros positivos é igual a 2.160 e o MDC entre eles é 12. Qual é o valor do MMC desses dois números?",
            options = listOf(
                "180",
                "120",
                "240",
                "360",
                "90"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Lembrar do Teorema Fundamental que relaciona MMC e MDC de dois números naturais:",
                "  MMC(a, b) × MDC(a, b) = a × b.",
                "Passo 2: Substituir os valores conhecidos do enunciado:",
                "  MMC(a, b) × 12 = 2.160.",
                "Passo 3: Isolar o MMC:",
                "  MMC(a, b) = 2.160 ÷ 12 = 180.",
                "Resultado Final: 180."
            ),
            pedagogicalAlert = "Essa propriedade só é válida exatamente para DOIS números: MMC(a,b) · MDC(a,b) = a · b.",
            keyMethod = "Relação fundamental de produto de dois números com MMC e MDC"
        ),
        UniversalExercise(
            id = "mb_mmc_dif_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "MMC e MDC",
            difficulty = "Difícil",
            source = "ENEM / Divisão com Resto Constante",
            problemStatement = "Qual é o menor número natural que, quando dividido por 4, deixa resto 3; quando dividido por 5, deixa resto 4; e quando dividido por 6, deixa resto 5?",
            options = listOf(
                "59",
                "119",
                "61",
                "58",
                "121"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Observar a diferença entre divisor e resto em cada caso:",
                "  4 - 3 = 1",
                "  5 - 4 = 1",
                "  6 - 5 = 1",
                "Passo 2: Em todos os casos, se somarmos 1 unidade ao número procurado N, ele se tornará divisível exatamente por 4, 5 e 6!",
                "Passo 3: Logo, N + 1 deve ser múltiplo comum de 4, 5 e 6.",
                "Passo 4: Calcular MMC(4, 5, 6):",
                "  4 = 2²",
                "  5 = 5",
                "  6 = 2 × 3",
                "  MMC(4, 5, 6) = 2² × 3 × 5 = 60.",
                "Passo 5: N + 1 = 60 => N = 60 - 1 = 59.",
                "Passo 6: Verificação: 59 ÷ 4 = 14 (resto 3); 59 ÷ 5 = 11 (resto 4); 59 ÷ 6 = 9 (resto 5).",
                "Resultado Final: 59."
            ),
            pedagogicalAlert = "Quando os restos têm mesma distância para os divisores (falta 1 para todos), o resultado é MMC(divisores) - 1.",
            keyMethod = "Problema de congruência com resto deficiente constante: N = MMC(d₁, d₂, d₃) - deficiência"
        ),

        // ========================================================
        // 3. PORCENTAGEM E VARIAÇÃO
        // ========================================================
        UniversalExercise(
            id = "mb_porc_facil_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Porcentagem e Variação",
            difficulty = "Fácil",
            source = "ENEM / Desconto Simples",
            problemStatement = "Um produto que custava R$ 200,00 teve um desconto de 15%. Qual é o novo preço do produto?",
            options = listOf(
                "R$ 170,00",
                "R$ 185,00",
                "R$ 160,00",
                "R$ 175,00",
                "R$ 30,00"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Calcular 15% de R$ 200,00:",
                "  15% = 15/100 = 0,15.",
                "  Desconto = 200 × 0,15 = R$ 30,00.",
                "Passo 2: Subtrair o desconto do preço original: 200 - 30 = R$ 170,00.",
                "Passo 3: Alternativamente, multiplicar pelo fator de desconto: 200 × (1 - 0,15) = 200 × 0,85 = R$ 170,00.",
                "Resultado Final: R$ 170,00."
            ),
            pedagogicalAlert = "Desconto de 15% significa pagar 85% do valor original (fator 0,85).",
            keyMethod = "Fator multiplicativo de desconto: Preço_Final = Preço_Inicial × (1 - i)"
        ),
        UniversalExercise(
            id = "mb_porc_facil_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Porcentagem e Variação",
            difficulty = "Fácil",
            source = "ENEM / Aumento Percentual",
            problemStatement = "Uma mensalidade escolar passou de R$ 500,00 para R$ 550,00. Qual foi o percentual de aumento aplicado?",
            options = listOf(
                "10%",
                "50%",
                "5%",
                "12%",
                "8%"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Variação absoluta: 550 - 500 = R$ 50,00.",
                "Passo 2: Dividir a variação pelo valor INICIAL (base de cálculo):",
                "  Variação relativa = 50 / 500 = 1 / 10 = 0,10.",
                "Passo 3: Multiplicar por 100 para expressar em porcentagem: 0,10 × 100 = 10%.",
                "Resultado Final: 10% de aumento."
            ),
            pedagogicalAlert = "A base da porcentagem é sempre o valor INICIAL (500), e nunca o valor final (550).",
            keyMethod = "Variação percentual: Δ% = [(V_final - V_inicial) / V_inicial] × 100"
        ),
        UniversalExercise(
            id = "mb_porc_med_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Porcentagem e Variação",
            difficulty = "Mediano",
            source = "ENEM / Aumentos Sucessivos",
            problemStatement = "O preço da gasolina subiu 10% em janeiro e mais 20% em fevereiro. Qual foi o aumento percentual ACUMULADO nesses dois meses?",
            options = listOf(
                "32%",
                "30%",
                "28%",
                "35%",
                "22%"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aumentos sucessivos são calculados pelo produto de seus fatores multiplicativos, NUNCA por soma direta!",
                "Passo 2: Fator do 1º aumento (10%): f₁ = 1 + 0,10 = 1,10.",
                "Passo 3: Fator do 2º aumento (20%): f₂ = 1 + 0,20 = 1,20.",
                "Passo 4: Fator acumulado total: f_total = 1,10 × 1,20 = 1,32.",
                "Passo 5: Subtrair 1 para achar a taxa acumulada: 1,32 - 1 = 0,32 = 32%.",
                "Resultado Final: 32% (e não 30%)."
            ),
            pedagogicalAlert = "Armadilha mais comum do ENEM: somar 10% + 20% = 30%. O segundo aumento incide sobre o valor já reajustado (juros sobre juros).",
            keyMethod = "Fatores acumulados sucessivos: Fator_total = f₁ × f₂ × ... × f_n"
        ),
        UniversalExercise(
            id = "mb_porc_med_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Porcentagem e Variação",
            difficulty = "Mediano",
            source = "ENEM / Aumento seguido de Desconto Igual",
            problemStatement = "Uma mercadoria sofreu um aumento de 20% e, no mês seguinte, recebeu um desconto de 20%. Em relação ao preço original, o preço final:",
            options = listOf(
                "Diminuiu 4%",
                "Permaneceu exatamente igual",
                "Aumentou 4%",
                "Diminuiu 2%",
                "Diminuiu 10%"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Fator de aumento de 20%: f₁ = 1 + 0,20 = 1,20.",
                "Passo 2: Fator de desconto de 20%: f₂ = 1 - 0,20 = 0,80.",
                "Passo 3: Fator acumulado total: f_total = 1,20 × 0,80 = 0,96.",
                "Passo 4: Como 0,96 é menor que 1, houve redução: 1 - 0,96 = 0,04 = 4% de redução.",
                "Passo 5: Exemplo numérico com R$ 100: com +20% vai a R$ 120. Desconto de 20% sobre 120 é R$ 24 (120 - 24 = R$ 96).",
                "Resultado Final: Diminuiu 4%."
            ),
            pedagogicalAlert = "Um aumento de x% seguido de um desconto de x% SEMPRE gera prejuízo líquido de (x/10)% ao quadrado: (20/10)² = 4% de queda.",
            keyMethod = "Regra de aumento e desconto idênticos: perda líquida = (x/10)² %"
        ),
        UniversalExercise(
            id = "mb_porc_dif_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Porcentagem e Variação",
            difficulty = "Difícil",
            source = "ENEM / Preço de Custo vs Margem sobre Venda",
            problemStatement = "Um lojista comprou um artigo por R$ 120,00 e deseja vendê-lo obtendo um lucro de 25% calculado SOBRE O PREÇO DE VENDA. Por quanto ele deve vender o artigo?",
            options = listOf(
                "R$ 160,00",
                "R$ 150,00",
                "R$ 145,00",
                "R$ 170,00",
                "R$ 180,00"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Preço de Venda V = Custo + Lucro.",
                "Passo 2: O enunciado define que o Lucro é 25% DE V (e não do custo!): Lucro = 0,25 V.",
                "Passo 3: Substituindo na equação:",
                "  V = 120 + 0,25 V",
                "  V - 0,25 V = 120",
                "  0,75 V = 120.",
                "Passo 4: Isolar V:",
                "  V = 120 ÷ 0,75 = 120 ÷ (3/4) = 120 × (4/3) = 160.",
                "Passo 5: Verificação: 25% de 160 é R$ 40,00. Custo 120 + 40 = R$ 160,00.",
                "Resultado Final: R$ 160,00."
            ),
            pedagogicalAlert = "Cuidado com o referencial: lucro sobre o custo seria 120 × 1,25 = R$ 150; mas sobre a VENDA resulta em R$ 160.",
            keyMethod = "Margem de lucro sobre preço de venda: V = Custo / (1 - margem)"
        ),
        UniversalExercise(
            id = "mb_porc_dif_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Porcentagem e Variação",
            difficulty = "Difícil",
            source = "ENEM / Inflação e Perda de Poder de Compra",
            problemStatement = "Se a inflação acumulada de um determinado ano foi de 25% e o salário de um trabalhador não teve nenhum reajuste, qual foi a perda percentual exata do seu poder de compra?",
            options = listOf(
                "20%",
                "25%",
                "15%",
                "12,5%",
                "30%"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Seja S o salário e P o nível geral de preços inicial. O poder de compra inicial é C₁ = S / P.",
                "Passo 2: Com inflação de 25%, os preços passaram a P₂ = 1,25 P.",
                "Passo 3: O novo poder de compra é C₂ = S / (1,25 P) = (1 / 1,25) · (S / P) = 0,80 C₁.",
                "Passo 4: O trabalhador agora compra 80% do que comprava antes.",
                "Passo 5: A perda percentual de poder de compra é: 1 - 0,80 = 0,20 = 20%.",
                "Resultado Final: 20% (e não 25%)."
            ),
            pedagogicalAlert = "A inflação de 25% equivale a uma perda de poder de compra de 20%, pois Poder_Compra = 1 / (1 + inflação).",
            keyMethod = "Relação inversa entre taxa de inflação e poder aquisitivo: P.A. = 1 / (1 + i)"
        ),

        // ========================================================
        // 4. REGRA DE TRÊS (SIMPLES E COMPOSTA)
        // ========================================================
        UniversalExercise(
            id = "mb_r3_facil_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Fácil",
            source = "ENEM / Regra de Três Direta",
            problemStatement = "Um automóvel consome 12 litros de gasolina para percorrer 180 km. Quantos litros de gasolina serão necessários para percorrer 300 km nas mesmas condições?",
            options = listOf(
                "20 litros",
                "25 litros",
                "18 litros",
                "15 litros",
                "24 litros"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: As grandezas 'distância' e 'litros' são diretamente proporcionais (mais km exigem mais combustível).",
                "Passo 2: Montar a proporção direta:",
                "  180 km ---- 12 L",
                "  300 km ---- x L",
                "Passo 3: Multiplicar cruzado:",
                "  180 · x = 300 · 12",
                "  180x = 3.600 => x = 3.600 / 180 = 20 litros.",
                "Resultado Final: 20 litros."
            ),
            pedagogicalAlert = "Grandezas diretamente proporcionais multiplicam-se em cruz.",
            keyMethod = "Regra de três simples direta: x = (b · c) / a"
        ),
        UniversalExercise(
            id = "mb_r3_facil_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Fácil",
            source = "ENEM / Regra de Três Inversa",
            problemStatement = "Um carro viaja a uma velocidade constante de 60 km/h e completa uma viagem em 4 horas. Se a velocidade fosse de 80 km/h, em quanto tempo faria o mesmo trajeto?",
            options = listOf(
                "3 horas",
                "3 horas e 20 minutos",
                "2 horas e 30 minutos",
                "5 horas",
                "2 horas"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: As grandezas 'velocidade' e 'tempo' são inversamente proporcionais (maior velocidade reduz o tempo gasto).",
                "Passo 2: Em grandezas inversas, o produto entre as grandezas permanece constante:",
                "  v₁ · t₁ = v₂ · t₂",
                "  60 · 4 = 80 · t₂",
                "  240 = 80 · t₂ => t₂ = 240 / 80 = 3 horas.",
                "Resultado Final: 3 horas."
            ),
            pedagogicalAlert = "Grandezas inversamente proporcionais multiplicam-se em linha reta: a · b = c · x.",
            keyMethod = "Regra de três inversa com produto constante: v₁ t₁ = v₂ t₂"
        ),
        UniversalExercise(
            id = "mb_r3_med_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Mediano",
            source = "ENEM / Regra de Três Composta",
            problemStatement = "Em uma fábrica, 6 máquinas idênticas produzem 900 peças funcionando durante 5 horas por dia. Quantas peças 8 dessas máquinas produzirão funcionando durante 6 horas por dia?",
            options = listOf(
                "1.440 peças",
                "1.200 peças",
                "1.600 peças",
                "1.350 peças",
                "1.500 peças"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Identificar as grandezas em relação à grandeza da incógnita (Peças):",
                "  - Máquinas vs Peças: mais máquinas produzem mais peças (DIRETA).",
                "  - Horas vs Peças: mais horas produzem mais peças (DIRETA).",
                "Passo 2: Montar a equação da proporção:",
                "  900 / x = (6 / 8) · (5 / 6).",
                "Passo 3: Simplificar o produto das frações: (6 / 8) · (5 / 6) = 5 / 8.",
                "Passo 4: Igualar: 900 / x = 5 / 8 => 5x = 900 · 8 = 7.200.",
                "Passo 5: x = 7.200 / 5 = 1.440 peças.",
                "Resultado Final: 1.440 peças."
            ),
            pedagogicalAlert = "Na regra de três composta, analise a relação de cada grandeza individualmente com a coluna que contém a incógnita x.",
            keyMethod = "Regra de três composta com isolamento da razão da incógnita"
        ),
        UniversalExercise(
            id = "mb_r3_med_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Mediano",
            source = "ENEM / Torneiras e Ralos",
            problemStatement = "Uma torneira enche um tanque em 3 horas e outra torneira enche o mesmo tanque em 6 horas. Se abrirmos ambas as torneiras juntas, em quanto tempo o tanque vazio ficará totalmente cheio?",
            options = listOf(
                "2 horas",
                "4,5 horas",
                "1,5 hora",
                "2 horas e 30 minutos",
                "1 hora"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Calcular a vazão de cada torneira por hora (fração do tanque por hora):",
                "  Torneira 1: 1/3 do tanque por hora.",
                "  Torneira 2: 1/6 do tanque por hora.",
                "Passo 2: Somar as vazões das duas torneiras juntas:",
                "  Vazão total = 1/3 + 1/6 = 2/6 + 1/6 = 3/6 = 1/2 do tanque por hora.",
                "Passo 3: Se enchem 1/2 tanque em 1 hora, para encher o tanque inteiro (2/2) serão necessárias:",
                "  Tempo = 1 / (1/2) = 2 horas.",
                "Resultado Final: 2 horas."
            ),
            pedagogicalAlert = "Nunca faça a média dos tempos (3+6)/2 = 4,5h! Duas torneiras juntas sempre enchem mais rápido do que a mais rápida sozinha.",
            keyMethod = "Problema de torneiras e vazões: 1/T = 1/t₁ + 1/t₂"
        ),
        UniversalExercise(
            id = "mb_r3_dif_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Difícil",
            source = "ENEM / Regra de Três Composta Mista (Direta e Inversa)",
            problemStatement = "Para pavimentar 1.200 metros de estrada, 15 operários trabalharam 8 horas por dia durante 10 dias. Quantos dias serão necessários para 12 operários pavimentarem 1.800 metros dessa estrada, trabalhando 6 horas por dia?",
            options = listOf(
                "25 dias",
                "20 dias",
                "30 dias",
                "18 dias",
                "22 dias"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Incógnita: 'Dias' (10 / x). Analisar cada grandeza em relação a 'Dias':",
                "  - Operários: menos operários exigem MAIS dias (INVERSA: inverte 15/12 para 12/15).",
                "  - Metros: mais estrada exige MAIS dias (DIRETA: mantém 1.200/1.800).",
                "  - Horas/dia: menos horas diárias exigem MAIS dias (INVERSA: inverte 8/6 para 6/8).",
                "Passo 2: Montar a equação multiplicando as razões ajustadas:",
                "  10 / x = (12 / 15) · (1.200 / 1.800) · (6 / 8).",
                "Passo 3: Simplificar cada fração:",
                "  12/15 = 4/5.",
                "  1.200/1.800 = 2/3.",
                "  6/8 = 3/4.",
                "Passo 4: Multiplicar as frações:",
                "  (4/5) · (2/3) · (3/4) = (4 · 2 · 3) / (5 · 3 · 4) = 2/5.",
                "Passo 5: Igualar: 10 / x = 2 / 5 => 2x = 50 => x = 25 dias.",
                "Resultado Final: 25 dias."
            ),
            pedagogicalAlert = "Inverta a fração exclusivamente das grandezas inversas antes de multiplicar.",
            keyMethod = "Regra de três composta mista: razão(x) = produto das razões corrigidas"
        ),
        UniversalExercise(
            id = "mb_r3_dif_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Regra de Três (Simples e Composta)",
            difficulty = "Difícil",
            source = "ENEM / Proporcionalidade com Consumo Variável",
            problemStatement = "Um navio partiu para uma viagem com mantimentos suficientes para alimentar 40 tripulantes durante 30 dias. Após 10 dias de viagem, o navio resgatou 10 náufragos. Se a ração diária por pessoa for reduzida para 80% do valor inicial, por quantos dias adicionais os mantimentos restantes durarão?",
            options = listOf(
                "20 dias adicionais",
                "16 dias adicionais",
                "15 dias adicionais",
                "24 dias adicionais",
                "18 dias adicionais"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Após 10 dias, restavam mantimentos para 40 pessoas durante: 30 - 10 = 20 dias (com 100% de ração).",
                "Passo 2: Novo número de pessoas: 40 + 10 = 50 pessoas. Nova ração: 0,80.",
                "Passo 3: A quantidade total de ração restante é equivalente a: 40 pessoas × 20 dias × 1,00 ração = 800 unidades.",
                "Passo 4: O consumo diário do novo grupo é: 50 pessoas × 0,80 ração = 40 unidades por dia.",
                "Passo 5: Número de dias que os mantimentos durarão: 800 unidades ÷ 40 unidades/dia = 20 dias.",
                "Resultado Final: 20 dias adicionais."
            ),
            pedagogicalAlert = "Considere apenas o estoque que sobrou após os primeiros 10 dias, ignorando o que já foi consumido.",
            keyMethod = "Conservação de estoque em problemas de abastecimento dinâmico"
        ),

        // ========================================================
        // 5. EQUAÇÕES E SISTEMAS LINEARES
        // ========================================================
        UniversalExercise(
            id = "mb_eq_facil_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Equações e Sistemas Lineares",
            difficulty = "Fácil",
            source = "ENEM / Equação do 1º Grau",
            problemStatement = "Resolva a equação do 1º grau: 3x - 7 = 2x + 5.",
            options = listOf(
                "x = 12",
                "x = -2",
                "x = 2",
                "x = 6",
                "x = -12"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Agrupar os termos com incógnita no primeiro membro: 3x - 2x = 5 + 7.",
                "Passo 2: Reduzir os termos semelhantes: x = 12.",
                "Resultado Final: x = 12."
            ),
            pedagogicalAlert = "Ao passar termos para o outro lado da igualdade, inverta a operação (adição vira subtração e vice-versa).",
            keyMethod = "Isolamento da incógnita na equação do 1º grau"
        ),
        UniversalExercise(
            id = "mb_eq_facil_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Equações e Sistemas Lineares",
            difficulty = "Fácil",
            source = "ENEM / Equação do 2º Grau por Soma e Produto",
            problemStatement = "Quais são as raízes da equação quadrática x² - 7x + 10 = 0 usando a relação de Soma e Produto?",
            options = listOf(
                "x₁ = 2 e x₂ = 5",
                "x₁ = -2 e x₂ = -5",
                "x₁ = 1 e x₂ = 10",
                "x₁ = -1 e x₂ = -10",
                "x₁ = 3 e x₂ = 4"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Pelas relações de Girard para x² - Sx + P = 0:",
                "  Soma S = -b/a = -(-7)/1 = 7.",
                "  Produto P = c/a = 10/1 = 10.",
                "Passo 2: Procurar dois números que somados dão 7 e multiplicados dão 10.",
                "Passo 3: 2 + 5 = 7 e 2 × 5 = 10.",
                "Resultado Final: As raízes são 2 e 5."
            ),
            pedagogicalAlert = "Soma e produto é muito mais rápido que a fórmula de Bhaskara para coeficientes inteiros com a = 1.",
            keyMethod = "Relações de Girard (Soma e Produto): S = x₁ + x₂ e P = x₁ · x₂"
        ),
        UniversalExercise(
            id = "mb_eq_med_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Equações e Sistemas Lineares",
            difficulty = "Mediano",
            source = "ENEM / Sistema 2x2 Clássico",
            problemStatement = "Em um estacionamento há carros e motos, totalizando 30 veículos e 84 rodas. Quantos carros e quantas motos há no estacionamento?",
            options = listOf(
                "12 carros e 18 motos",
                "18 carros e 12 motos",
                "15 carros e 15 motos",
                "20 carros e 10 motos",
                "14 carros e 16 motos"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Definir as variáveis: c = número de carros, m = número de motos.",
                "Passo 2: Montar o sistema de equações:",
                "  Equação 1 (veículos): c + m = 30",
                "  Equação 2 (rodas): 4c + 2m = 84",
                "Passo 3: Multiplicar a Equação 1 por -2: -2c - 2m = -60.",
                "Passo 4: Somar com a Equação 2:",
                "  (4c - 2c) + (2m - 2m) = 84 - 60",
                "  2c = 24 => c = 12 carros.",
                "Passo 5: Achar m: 12 + m = 30 => m = 18 motos.",
                "Passo 6: Verificação: 12(4) + 18(2) = 48 + 36 = 84 rodas.",
                "Resultado Final: 12 carros e 18 motos."
            ),
            pedagogicalAlert = "O método da adição é o mais seguro para evitar frações em sistemas lineares 2x2.",
            keyMethod = "Método da adição para resolução de sistemas lineares 2x2"
        ),
        UniversalExercise(
            id = "mb_eq_med_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Equações e Sistemas Lineares",
            difficulty = "Mediano",
            source = "ENEM / Vértice da Parábola e Ponto de Máximo",
            problemStatement = "A trajetória de um projétil é descrita pela função quadrática h(t) = -5t² + 30t, onde h é a altura em metros e t o tempo em segundos. Qual é a altura MÁXIMA atingida pelo projétil?",
            options = listOf(
                "45 metros (no instante t = 3s)",
                "30 metros",
                "90 metros",
                "50 metros",
                "60 metros"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O instante de altura máxima é a coordenada t do vértice: t_v = -b / (2a).",
                "Passo 2: a = -5, b = 30:",
                "  t_v = -30 / (2 · (-5)) = -30 / (-10) = 3 segundos.",
                "Passo 3: A altura máxima é o valor da função no vértice h(t_v):",
                "  h(3) = -5(3)² + 30(3) = -5(9) + 90 = -45 + 90 = 45 metros.",
                "Passo 4: Alternativamente, usando y_v = -Δ / (4a): Δ = 30² - 4(-5)(0) = 900. y_v = -900 / (4 · (-5)) = -900 / -20 = 45 metros.",
                "Resultado Final: 45 metros."
            ),
            pedagogicalAlert = "Diferencie 'quando ocorre o máximo' (t_v = 3s) de 'qual é o valor máximo' (y_v = 45m). O ENEM cobra ambos com frequência.",
            keyMethod = "Cálculo das coordenadas do vértice da parábola: x_v = -b/(2a) e y_v = -Δ/(4a)"
        ),
        UniversalExercise(
            id = "mb_eq_dif_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Equações e Sistemas Lineares",
            difficulty = "Difícil",
            source = "FUVEST / Sistema Linear 3x3 por Escalonamento",
            problemStatement = """Resolva o sistema linear 3x3 e encontre o valor de \( z \):
\[ \begin{cases} x + y + z = 6 \\ 2x - y + z = 3 \\ x + 2y - z = 2 \end{cases} \]""",
            options = listOf(
                "z = 3",
                "z = 1",
                "z = 2",
                "z = 4",
                "z = 0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Somar a Equação 1 e a Equação 3:",
                "  (x + y + z) + (x + 2y - z) = 6 + 2",
                "  2x + 3y = 8. (Equação A)",
                "Passo 2: Subtrair a Equação 1 da Equação 2 para eliminar z:",
                "  (2x - y + z) - (x + y + z) = 3 - 6",
                "  x - 2y = -3 => x = 2y - 3. (Equação B)",
                "Passo 3: Substituir x na Equação A:",
                "  2(2y - 3) + 3y = 8 => 4y - 6 + 3y = 8",
                "  7y = 14 => y = 2.",
                "Passo 4: Encontrar x: x = 2(2) - 3 = 4 - 3 = 1.",
                "Passo 5: Encontrar z usando a Equação 1: 1 + 2 + z = 6 => 3 + z = 6 => z = 3.",
                "Resultado Final: z = 3 (solução única: x=1, y=2, z=3)."
            ),
            pedagogicalAlert = "Ao escalonar sistemas lineares 3x3, elimine a mesma variável em dois pares distintos de equações para obter um subsistema 2x2.",
            keyMethod = "Método do escalonamento de Gauss para sistemas lineares 3x3"
        ),
        UniversalExercise(
            id = "mb_eq_dif_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Equações e Sistemas Lineares",
            difficulty = "Difícil",
            source = "ENEM / Equação Exponencial Redutível a 2º Grau",
            problemStatement = """Encontre o conjunto de soluções reais da equação:
\[ 4^x - 5 \cdot 2^x + 4 = 0 \]""",
            options = listOf(
                "x = 0 e x = 2",
                "x = 1 e x = 4",
                "x = 2 e x = 4",
                "Apenas x = 2",
                "x = -1 e x = 2"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Observar que 4^x = (2²)^x = (2^x)².",
                "Passo 2: Fazer a mudança de variável: seja y = 2^x (com y > 0 obrigatoriamente).",
                "Passo 3: Reescrever a equação em função de y:",
                "  y² - 5y + 4 = 0.",
                "Passo 4: Fatorar por soma e produto: (y - 1)(y - 4) = 0 => y = 1 ou y = 4.",
                "Passo 5: Retornar à variável original x:",
                "  Para y = 1: 2^x = 1 = 2⁰ => x = 0.",
                "  Para y = 4: 2^x = 4 = 2² => x = 2.",
                "Resultado Final: x = 0 e x = 2."
            ),
            pedagogicalAlert = "Não pare na resposta de y! Lembre-se de desfazer a substituição y = 2^x para encontrar a solução de x.",
            keyMethod = "Resolução de equação exponencial por mudança de variável quadrática"
        ),

        // ========================================================
        // 6. GEOMETRIA: ÁREAS E VOLUMES
        // ========================================================
        UniversalExercise(
            id = "mb_geom_facil_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria: Áreas e Volumes",
            difficulty = "Fácil",
            source = "ENEM / Teorema de Pitágoras",
            problemStatement = "Um triângulo retângulo possui catetos de comprimentos 6 cm e 8 cm. Qual é o comprimento da sua hipotenusa?",
            options = listOf(
                "10 cm",
                "14 cm",
                "12 cm",
                "48 cm",
                "√28 cm"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Aplicar o Teorema de Pitágoras: a² + b² = c².",
                "Passo 2: Substituir os catetos: 6² + 8² = 36 + 64 = 100.",
                "Passo 3: c = √100 = 10 cm.",
                "Passo 4: Observe que este é um múltiplo do triângulo pitagórico fundamental (3, 4, 5) ampliado por fator 2.",
                "Resultado Final: 10 cm."
            ),
            pedagogicalAlert = "Reconhecer ternos pitagóricos como (3, 4, 5), (5, 12, 13) e seus múltiplos economiza muito tempo em provas.",
            keyMethod = "Teorema de Pitágoras: c = √(a² + b²)"
        ),
        UniversalExercise(
            id = "mb_geom_facil_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria: Áreas e Volumes",
            difficulty = "Fácil",
            source = "ENEM / Área do Trapézio",
            problemStatement = "Qual é a área de um terreno em formato de trapézio com base maior B = 14 m, base menor b = 6 m e altura h = 5 m?",
            options = listOf(
                "50 m²",
                "100 m²",
                "40 m²",
                "60 m²",
                "35 m²"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A fórmula da área do trapézio é A = [(B + b) · h] / 2.",
                "Passo 2: Substituir os valores:",
                "  A = [(14 + 6) · 5] / 2 = [20 · 5] / 2 = 100 / 2 = 50 m².",
                "Resultado Final: 50 m²."
            ),
            pedagogicalAlert = "A área do trapézio é a média aritmética das bases multiplicada pela altura.",
            keyMethod = "Área do trapézio: A = [(B + b) · h] / 2"
        ),
        UniversalExercise(
            id = "mb_geom_med_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria: Áreas e Volumes",
            difficulty = "Mediano",
            source = "ENEM / Caixa D'Água e Conversão m³ para Litros",
            problemStatement = "Uma piscina retangular possui 8 metros de comprimento, 4 metros de largura e 1,5 metro de profundidade. Quantos litros de água são necessários para enchê-la totalmente?",
            options = listOf(
                "48.000 litros",
                "4.800 litros",
                "480.000 litros",
                "32.000 litros",
                "24.000 litros"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Calcular o volume em metros cúbicos multiplicando as 3 dimensões (paralelepípedo reto):",
                "  V = c × l × h = 8 × 4 × 1,5 = 32 × 1,5 = 48 m³.",
                "Passo 2: Lembrar da equivalência padrão: 1 m³ = 1.000 litros.",
                "Passo 3: Converter para litros:",
                "  Volume em litros = 48 × 1.000 = 48.000 litros.",
                "Resultado Final: 48.000 litros."
            ),
            pedagogicalAlert = "Não esqueça: 1 m³ = 1.000 L, 1 dm³ = 1 L, e 1 cm³ = 1 mL.",
            keyMethod = "Volume de paralelepípedo e conversão métrica de volume para capacidade"
        ),
        UniversalExercise(
            id = "mb_geom_med_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria: Áreas e Volumes",
            difficulty = "Mediano",
            source = "ENEM / Volume do Cone",
            problemStatement = "Uma casquinha de sorvete possui formato cônico com raio da base r = 3 cm e altura h = 10 cm. Adotando π = 3,14, qual é o volume aproximado de sorvete que preenche o interior da casquinha?",
            options = listOf(
                "94,2 cm³",
                "282,6 cm³",
                "31,4 cm³",
                "141,3 cm³",
                "62,8 cm³"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A fórmula do volume do cone é V = (1/3) π r² h.",
                "Passo 2: Substituir os valores (r = 3 cm, h = 10 cm, π = 3,14):",
                "  V = (1/3) · 3,14 · (3)² · 10 = (1/3) · 3,14 · 9 · 10.",
                "Passo 3: Simplificar 9 ÷ 3 = 3:",
                "  V = 3,14 · 3 · 10 = 3,14 · 30 = 94,2 cm³.",
                "Resultado Final: 94,2 cm³."
            ),
            pedagogicalAlert = "O volume do cone é exatamente UM TERÇO do volume do cilindro de mesmo raio e mesma altura.",
            keyMethod = "Volume de sólidos pontiagudos (cones e pirâmides): V = (1/3) Área_base × h"
        ),
        UniversalExercise(
            id = "mb_geom_dif_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria: Áreas e Volumes",
            difficulty = "Difícil",
            source = "ENEM / Volume da Esfera Inscrita no Cubo",
            problemStatement = "Uma esfera de chumbo está perfeitamente inscrita no interior de uma caixa cúbica de aresta a = 6 cm. Qual é o volume da esfera em termos de π?",
            options = listOf(
                "36π cm³",
                "288π cm³",
                "72π cm³",
                "18π cm³",
                "108π cm³"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Uma esfera inscrita em um cubo toca as 6 faces opostas; logo, seu diâmetro 2r é igual à aresta do cubo a:",
                "  2r = 6 cm => raio r = 3 cm.",
                "Passo 2: A fórmula do volume da esfera é V = (4/3) π r³.",
                "Passo 3: Substituir r = 3 cm:",
                "  V = (4/3) π (3)³ = (4/3) π (27).",
                "Passo 4: Simplificar 27 ÷ 3 = 9:",
                "  V = 4 · 9 · π = 36π cm³.",
                "Resultado Final: 36π cm³ (aproximadamente 113,1 cm³)."
            ),
            pedagogicalAlert = "O raio da esfera inscrita é a METADE da aresta do cubo (r = a/2).",
            keyMethod = "Inscrição de sólidos tridimensionais e volume de esfera: V = (4/3) π r³"
        ),
        UniversalExercise(
            id = "mb_geom_dif_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Geometria: Áreas e Volumes",
            difficulty = "Difícil",
            source = "FUVEST / Tronco de Pirâmide e Razão de Semelhança",
            problemStatement = "Uma pirâmide reta de altura H = 12 cm e volume V = 324 cm³ é seccionada por um plano paralelo à base a uma altura de 8 cm da base. Qual é o volume da pirâmide pequena superior destacada?",
            options = listOf(
                "12 cm³",
                "36 cm³",
                "27 cm³",
                "108 cm³",
                "4 cm³"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A altura do corte está a 8 cm da base, logo a altura da pirâmide pequena superior é h = H - 8 = 12 - 8 = 4 cm.",
                "Passo 2: A razão de semelhança linear entre a pirâmide pequena e a grande é k = h / H = 4 / 12 = 1/3.",
                "Passo 3: A razão entre volumes de sólidos semelhantes é o CUBO da razão linear: V_pequeno / V_grande = k³ = (1/3)³ = 1/27.",
                "Passo 4: Calcular o volume da pirâmide pequena:",
                "  V_pequeno = V_grande / 27 = 324 / 27 = 12 cm³.",
                "Resultado Final: 12 cm³."
            ),
            pedagogicalAlert = "Propriedade fundamental de semelhança: comprimentos variam com k, áreas variam com k² e volumes variam com k³.",
            keyMethod = "Razão cúbica de volumes em sólidos semelhantes: V₁/V₂ = (h₁/h₂)³"
        ),

        // ========================================================
        // 7. ESTATÍSTICA: MÉDIA, MEDIANA E MODA
        // ========================================================
        UniversalExercise(
            id = "mb_est_facil_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Média, Mediana e Moda",
            difficulty = "Fácil",
            source = "ENEM / Moda e Média Aritmética",
            problemStatement = "Considere a lista de idades de um grupo de amigos: [18, 20, 20, 22, 25]. Qual é a MODA dessa distribuição?",
            options = listOf(
                "20 anos (aparece com maior frequência)",
                "21 anos",
                "22 anos",
                "18 anos",
                "25 anos"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A moda de um conjunto estatístico é o valor que se repete com maior frequência absoluta.",
                "Passo 2: Contagem: 18 aparece 1x, 20 aparece 2x, 22 aparece 1x, 25 aparece 1x.",
                "Passo 3: O elemento mais frequente é 20 anos.",
                "Resultado Final: 20 anos."
            ),
            pedagogicalAlert = "Se nenhum elemento se repetir, o conjunto é chamado de 'amodal'; se dois empatarem, é 'bimodal'.",
            keyMethod = "Definição de moda estatística: elemento de maior frequência"
        ),
        UniversalExercise(
            id = "mb_est_facil_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Média, Mediana e Moda",
            difficulty = "Fácil",
            source = "ENEM / Mediana de Quantidade Par de Elementos",
            problemStatement = "Qual é a MEDIANA do conjunto de números: [12, 5, 8, 20, 15, 3]?",
            options = listOf(
                "10",
                "8",
                "12",
                "10,5",
                "9"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: OBRIGATÓRIO: Ordenar os dados em ordem crescente (rol): [3, 5, 8, 12, 15, 20].",
                "Passo 2: O conjunto tem n = 6 elementos (número par).",
                "Passo 3: A mediana é a média aritmética dos dois elementos centrais (3º e 4º elementos):",
                "  Elementos centrais: 8 e 12.",
                "Passo 4: Mediana = (8 + 12) / 2 = 20 / 2 = 10.",
                "Resultado Final: 10."
            ),
            pedagogicalAlert = "NUNCA calcule a mediana sem antes ordenar os dados em rol crescente!",
            keyMethod = "Cálculo da mediana para quantidade par: média dos dois termos centrais do rol"
        ),
        UniversalExercise(
            id = "mb_est_med_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Média, Mediana e Moda",
            difficulty = "Mediano",
            source = "ENEM / Média Ponderada com Pesos",
            problemStatement = "Um estudante obteve as seguintes notas em três provas: Prova 1 (nota 6,0, peso 2), Prova 2 (nota 7,0, peso 3) e Prova 3 (nota 9,0, peso 5). Qual foi a média ponderada final do estudante?",
            options = listOf(
                "7,8",
                "7,3",
                "8,0",
                "7,5",
                "8,2"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A fórmula da média ponderada é Mp = (Σ nota_i × peso_i) / (Σ peso_i).",
                "Passo 2: Soma ponderada dos produtos:",
                "  (6,0 × 2) + (7,0 × 3) + (9,0 × 5) = 12 + 21 + 45 = 78.",
                "Passo 3: Soma dos pesos: 2 + 3 + 5 = 10.",
                "Passo 4: Média ponderada = 78 ÷ 10 = 7,8.",
                "Resultado Final: 7,8."
            ),
            pedagogicalAlert = "O denominador da média ponderada é a SOMA DOS PESOS, e não a quantidade de provas.",
            keyMethod = "Média ponderada: Mp = Σ(x_i · p_i) / Σ p_i"
        ),
        UniversalExercise(
            id = "mb_est_med_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Média, Mediana e Moda",
            difficulty = "Mediano",
            source = "ENEM / Nota Necessária para Atingir Média",
            problemStatement = "Um curso adota média aritmética simples 7,0 em 4 bimestres para aprovação. Se um aluno tirou 5,5 no 1º bimestre, 6,5 no 2º e 7,0 no 3º, qual nota mínima ele precisa tirar no 4º bimestre para ser aprovado?",
            options = listOf(
                "9,0",
                "8,5",
                "8,0",
                "9,5",
                "10,0"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: Para ter média 7,0 em 4 bimestres, a soma total das 4 notas deve ser: 7,0 × 4 = 28,0 pontos.",
                "Passo 2: Somar as notas já obtidas nos 3 primeiros bimestres: 5,5 + 6,5 + 7,0 = 19,0 pontos.",
                "Passo 3: Subtrair a soma obtida da soma necessária: 28,0 - 19,0 = 9,0 pontos.",
                "Resultado Final: 9,0."
            ),
            pedagogicalAlert = "Multiplique a média desejada pelo total de avaliações para descobrir a pontuação total exigida.",
            keyMethod = "Meta de média aritmética: Pontos_necessários = Média × N - Pontos_atuais"
        ),
        UniversalExercise(
            id = "mb_est_dif_1",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Média, Mediana e Moda",
            difficulty = "Difícil",
            source = "ENEM / Desvio Padrão e Regularidade",
            problemStatement = "Três atiradores realizaram 5 tiros cada. Todos obtiveram a mesma média de 80 pontos, mas seus desvios padrões foram: Atirador A (DP = 2,5), Atirador B (DP = 0,8) e Atirador C (DP = 5,1). Qual atirador foi o MAIS REGULAR?",
            options = listOf(
                "Atirador B (menor desvio padrão indica maior consistência e homogeneidade)",
                "Atirador C",
                "Atirador A",
                "Todos foram igualmente regulares pois têm a mesma média",
                "Não é possível determinar sem a mediana"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: O desvio padrão mede a dispersão dos dados em torno da média aritmética.",
                "Passo 2: Quanto MENOR o desvio padrão (DP), mais concentrados e homogêneos estão os valores próximos da média, o que define REGULARIDADE.",
                "Passo 3: Comparando: DP_B (0,8) < DP_A (2,5) < DP_C (5,1).",
                "Passo 4: O Atirador B possui a menor dispersão, logo foi o mais regular.",
                "Resultado Final: Atirador B."
            ),
            pedagogicalAlert = "Cobrança anual no ENEM: 'mais regular', 'mais homogêneo' ou 'mais consistente' é sinônimo direto de MENOR desvio padrão ou variância.",
            keyMethod = "Interpretação estatística de regularidade pelo desvio padrão (menor DP = mais regular)"
        ),
        UniversalExercise(
            id = "mb_est_dif_2",
            discipline = ExerciseDiscipline.BASIC_MATH,
            category = "Matemática Básica",
            subtopic = "Estatística: Média, Mediana e Moda",
            difficulty = "Difícil",
            source = "ENEM / Alteração de Média por Entrada de Elemento",
            problemStatement = "Em um grupo de 10 pessoas, a média de idade é de 24 anos. Se uma nova pessoa de 46 anos entrar no grupo, qual passará a ser a nova média de idade do grupo?",
            options = listOf(
                "26 anos",
                "28 anos",
                "25 anos",
                "27 anos",
                "35 anos"
            ),
            correctIndex = 0,
            solutionSteps = listOf(
                "Passo 1: A soma total das idades das 10 primeiras pessoas é: 10 × 24 = 240 anos.",
                "Passo 2: Ao entrar a nova pessoa de 46 anos, a nova soma total passa a ser: 240 + 46 = 286 anos.",
                "Passo 3: O novo número total de pessoas no grupo agora é: 10 + 1 = 11 pessoas.",
                "Passo 4: Calcular a nova média dividindo a nova soma pelo novo total:",
                "  Nova Média = 286 ÷ 11 = 26 anos.",
                "Resultado Final: 26 anos."
            ),
            pedagogicalAlert = "Lembre-se de atualizar tanto a soma no numerador quanto a quantidade de pessoas no denominador (passou de 10 para 11).",
            keyMethod = "Recálculo de média com inserção de novos dados: M_nova = (Soma_anterior + novo_valor) / (n + 1)"
        )
    )
}
