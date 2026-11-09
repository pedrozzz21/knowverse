package com.example.data.transpetro

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

/**
 * Conteúdo didático completo e exercícios suplementares para a disciplina
 * "Finanças e Contabilidade" (transpetro_fin) do Concurso Transpetro - ADM e Controle
 * (Fundação Cesgranrio — Nível Médio).
 */
object TranspetroFinDidactic {

    fun getBundle(lessonId: String): CalculusDidacticBundle? = when (lessonId) {
        // =====================================================================
        // MÓDULO 1: MATEMÁTICA FINANCEIRA
        // =====================================================================
        "tp_fin_m1_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m1_a1",
            intro = "A porcentagem financeira e o cálculo de variações percentuais sucessivas constituem a base de toda a análise administrativa, comercial e orçamentária na Transpetro. Seja na negociação de descontos escalonados em contratos de fornecimento de materiais, no reajuste anual de tarifas logísticas ou na apuração de margens sobre operações com mercadorias, a Fundação Cesgranrio exige que o candidato domine fatores multiplicativos e distinga margens calculadas sobre o custo daquelas calculadas sobre o preço de venda.",
            theory = """
                1. Razão Centesimal e Fator Multiplicativo (Fator de Atualização):
                Toda taxa percentual i% representa uma razão de denominador 100 (i/100). Para agilizar cálculos financeiros:
                • Aumento percentual de i%: multiplica-se o valor inicial V₀ pelo fator (1 + i). Exemplo: acréscimo de 18% corresponde a multiplicar por 1,18.
                • Redução (abatimento/desconto) percentual de i%: multiplica-se o valor inicial V₀ pelo fator (1 - i). Exemplo: redução de 12% corresponde a multiplicar por 0,88.

                2. Aumentos e Reduções Percentuais Sucessivos:
                Quando incidem variações sucessivas i₁, i₂, ..., iₙ sobre um valor base, cada nova taxa incide sobre o resultado imediatamente anterior, e NUNCA de forma aditiva simples sobre o valor original.
                • Fator Acumulado: F_total = (1 ± i₁) × (1 ± i₂) × ... × (1 ± iₙ).
                • Taxa Acumulada Efetiva: i_acum = F_total - 1.
                • Pegadinha clássica da Cesgranrio: Um aumento de 20% seguido de um desconto de 20% não recompõe o valor original, pois 1,20 × 0,80 = 0,96, resultando em perda líquida de 4%. Do mesmo modo, dois abatimentos sucessivos de 10% equivalem a um desconto único de 19% (0,90 × 0,90 = 0,81).

                3. Operações sobre Mercadorias (Lucro sobre o Custo vs. Lucro sobre a Venda):
                Nas operações comerciais, o Preço de Venda (PV) relaciona-se ao Preço de Custo (PC) e ao Lucro (L) ou Prejuízo (P) por PV = PC + L.
                • Lucro sobre o Custo: L = i_c × PC → PV = PC × (1 + i_c).
                • Lucro sobre o Preço de Venda (Margem Bruta Comercial): L = i_v × PV → PC = PV × (1 - i_v), logo PV = PC / (1 - i_v).
            """.trimIndent(),
            practicalApplication = """
                Na gestão de contratos de suprimentos da Transpetro, um fornecedor de válvulas industriais propõe reajustar sua tabela em 10% devido ao aumento do aço, mas concede um desconto comercial de 5% para fechamento de lote anual. O técnico de administração aplica fatores sucessivos: 1,10 × 0,95 = 1,045, constatando que o impacto líquido no orçamento da companhia será um acréscimo exato de 4,5%, e não de 5%.
            """.trimIndent(),
            summary = """
                • Fator de aumento: (1 + i); Fator de redução: (1 - i).
                • Variações sucessivas: multiplicam-se os fatores (nunca se somam diretamente as taxas).
                • Aumento de x% seguido de redução de x% gera sempre redução líquida de x²/100%.
                • Lucro sobre a venda: PV = PC / (1 - i_v); Lucro sobre o custo: PV = PC × (1 + i_c).
            """.trimIndent(),
            formulaName = "Fator Acumulado de Variações Sucessivas e Preço de Venda",
            formulaSyntax = "V_f = V_0 × (1 ± i_1) × (1 ± i_2)   |   PV = PC / (1 - i_venda)",
            formulaTerms = listOf(
                "V_f = Valor final após as variações sucessivas",
                "V_0 = Valor inicial de referência",
                "i_1, i_2 = Taxas percentuais na forma unitária (ex.: 10% = 0,10)",
                "PV e PC = Preço de Venda e Preço de Custo da mercadoria"
            ),
            formulaWhenToUse = "Utilizar em reajustes contratuais acumulados, descontos comerciais em cascata e formação de preços com margem sobre o custo ou sobre a venda.",
            exampleTitle = "Abatimentos Sucessivos em Aquisição de Insumos Operacionais",
            exampleProblem = "Um lote de equipamentos para manutenção de terminais aquaviários tinha preço de tabela de R$ 80.000,00. Após negociação, a Transpetro obteve dois descontos sucessivos: 10% de desconto corporativo e, sobre o novo valor, mais 5% para pagamento faturado em 15 dias. Qual foi o valor final pago e a taxa percentual total de desconto?",
            exampleSteps = listOf(
                "Converter as taxas de desconto em fatores multiplicativos: d₁ = 10% → F₁ = 1 - 0,10 = 0,90; d₂ = 5% → F₂ = 1 - 0,05 = 0,95.",
                "Calcular o fator multiplicativo acumulado: F_total = 0,90 × 0,95 = 0,855.",
                "Apurar a taxa efetiva de desconto: 1 - 0,855 = 0,145 = 14,5%.",
                "Calcular o valor final desembolsado: V_f = 80.000 × 0,855 = R$ 68.400,00."
            ),
            exampleAnswer = "Valor pago de R$ 68.400,00, correspondente a um desconto acumulado efetivo de 14,5%.",
            goldTip = "Na Cesgranrio, quando o enunciado informar que o lucro foi de 25% SOBRE O PREÇO DE VENDA, lembre-se de que o Custo representa 75% (3/4) da Venda; logo, o lucro equivale a 1/3 (33,33%) sobre o Preço de Custo!"
        )

        "tp_fin_m1_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m1_a2",
            intro = "O regime de capitalização simples (juros simples) é amplamente cobrado pela Fundação Cesgranrio em operações de curto prazo, apuração de encargos moratórios lineares e conversão de taxas proporcionais. Dominar a compatibilização entre a unidade de tempo da taxa e o prazo da operação evita os erros mais frequentes de prova.",
            theory = """
                1. Regime de Capitalização Simples (Linear):
                Nos juros simples, a taxa de juros i incide exclusivamente sobre o Capital Inicial (C ou P, Principal) ao longo de todos os n períodos. Os juros gerados em cada período são constantes e formam uma Progressão Aritmética (PA).
                • Juros Simples: J = C × i × n
                • Montante (Valor Futuro): M = C + J = C × (1 + i × n)

                2. Taxas Proporcionais (Equivalência no Regime Simples):
                Duas taxas são proporcionais quando mantêm razão linear direta com seus respectivos prazos. No regime simples, taxas proporcionais também são equivalentes (produzem o mesmo montante no mesmo prazo).
                • Conversão: i_anual = 12 × i_mensal = 4 × i_trimestral = 2 × i_semestral.
                • Juros Comercial (Ordinário) vs. Juros Exato:
                  - Ano comercial: 360 dias e mês comercial de 30 dias (padrão da Cesgranrio salvo menção expressa em contrário).
                  - Ano civil (exato): 365 dias (ou 366 em ano bissexto).
            """.trimIndent(),
            practicalApplication = """
                Na conferência de faturas em atraso de prestadores ou clientes, a cláusula contratual frequentemente prevê juros de mora simples de 1% ao mês pro rata die (proporcional aos dias corridos de atraso). Para 18 dias de atraso no mês comercial (30 dias), a taxa proporcional aplicada pelo assistente administrativo será de (1% / 30) × 18 = 0,6% sobre o valor original da fatura.
            """.trimIndent(),
            summary = """
                • J = C × i × n  e  M = C × (1 + i × n).
                • Taxa (i) e prazo (n) devem estar obrigatoriamente na mesma unidade de tempo.
                • Taxas proporcionais: divisão ou multiplicação direta pelo número de subperíodos (ex.: 24% a.a. = 2% a.m.).
                • Ano comercial padrão = 360 dias; mês comercial = 30 dias.
            """.trimIndent(),
            formulaName = "Juros Simples, Montante Linear e Taxas Proporcionais",
            formulaSyntax = "J = C × i × n   |   M = C × (1 + i × n)   |   i_m = i_a / 12",
            formulaTerms = listOf(
                "J = Juros simples acumulados no período",
                "C = Capital inicial (Principal ou Valor Presente)",
                "i = Taxa unitária de juros por período",
                "n = Número de períodos de capitalização (compatível com a taxa i)",
                "M = Montante final (Capital + Juros)"
            ),
            formulaWhenToUse = "Aplicar sempre que o enunciado indicar capitalização simples, regime linear ou taxas proporcionais.",
            exampleTitle = "Cálculo de Montante com Taxa Anual e Prazo em Meses",
            exampleProblem = "Um fundo de reserva operacional de R$ 150.000,00 foi aplicado sob regime de juros simples à taxa linear de 18% ao ano, durante o prazo de 8 meses. Qual o valor dos juros auferidos e o montante resgatado ao final do período?",
            exampleSteps = listOf(
                "Compatibilizar a taxa anual para mensal (taxa proporcional): i = 18% a.a. / 12 meses = 1,5% a.m. = 0,015.",
                "Aplicar a fórmula dos juros simples para n = 8 meses: J = 150.000 × 0,015 × 8.",
                "Multiplicar 0,015 × 8 = 0,12 (12% de rendimento acumulado no período).",
                "Calcular J = 150.000 × 0,12 = R$ 18.000,00 e M = 150.000 + 18.000 = R$ 168.000,00."
            ),
            exampleAnswer = "Juros de R$ 18.000,00 e montante final de R$ 168.000,00.",
            goldTip = "Antes de substituir valores na fórmula, verifique se a taxa está ao ano e o prazo em meses (ou dias). Converta sempre a taxa para a unidade do prazo dividindo de forma linear!"
        )

        "tp_fin_m1_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m1_a3",
            intro = "No mercado financeiro e na avaliação de contratos e investimentos corporativos da Transpetro, o regime padrão é o de juros compostos ('juros sobre juros'). A Cesgranrio cobra intensamente o cálculo do fator de capitalização composta, a diferença entre taxas equivalentes e proporcionais, e a conversão de uma taxa nominal em taxa efetiva.",
            theory = """
                1. Regime de Capitalização Composta (Exponencial):
                Ao fim de cada período, os juros produzidos são incorporados ao capital para o cálculo dos juros do período seguinte. Os montantes crescem segundo uma Progressão Geométrica (PG) de razão (1 + i).
                • Montante Composto: M = C × (1 + i)ⁿ
                • Juros Compostos: J = M - C = C × [(1 + i)ⁿ - 1]
                • Fator de Acumulação de Capital (FAC): (1 + i)ⁿ

                2. Taxas Equivalentes em Juros Compostos:
                Duas taxas em unidades de tempo distintas são equivalentes quando, aplicadas ao mesmo capital durante o mesmo intervalo de tempo, produzem exatamente o mesmo montante sob capitalização composta:
                • (1 + i_anual) = (1 + i_semestral)² = (1 + i_trimestral)⁴ = (1 + i_mensal)¹²

                3. Taxa Nominal versus Taxa Efetiva:
                • Taxa Nominal: é uma taxa declarada em contrato cujo período de referência NÃO coincide com o período de capitalização (ex.: '24% ao ano com capitalização mensal'). A taxa nominal nunca é usada diretamente na fórmula exponencial.
                • Taxa Efetiva: obtém-se primeiro a taxa por período de capitalização por proporção simples (24% / 12 = 2% a.m.) e, em seguida, capitaliza-se de forma composta para o período anual: i_efetiva = (1 + 0,02)¹² - 1.

                4. Convenção Linear vs. Convenção Exponencial para Prazos Fracionários:
                Na convenção linear, a parte inteira do prazo é capitalizada a juros compostos e a fração a juros simples: M = C × (1 + i)^k × (1 + i × p/q).
            """.trimIndent(),
            practicalApplication = """
                Na contratação de uma linha de financiamento para modernização de dutos, uma instituição financeira apresenta proposta com taxa nominal de 12% ao ano com capitalização semestral. O analista financeiro da Transpetro divide 12% por 2 semestres (6% ao semestre) e calcula a taxa efetiva anual: (1,06)² - 1 = 1,1236 - 1 = 12,36% ao ano, permitindo comparação justa com outras linhas de crédito.
            """.trimIndent(),
            summary = """
                • Montante Composto: M = C × (1 + i)ⁿ.
                • Taxas equivalentes (compostas): (1 + i_maior) = (1 + i_menor)^k.
                • Taxa Nominal → divide-se proporcionalmente para achar a taxa efetiva do subperíodo → eleva-se a k para achar a taxa efetiva do período maior.
                • Para k > 1, a taxa efetiva anual é sempre maior que a taxa nominal anual.
            """.trimIndent(),
            formulaName = "Montante Composto e Relação de Equivalência de Taxas",
            formulaSyntax = "M = C × (1 + i)^n   |   1 + i_efetiva = (1 + i_nominal / k)^k",
            formulaTerms = listOf(
                "M = Montante composto (Valor Futuro)",
                "C = Capital inicial (Valor Presente)",
                "i = Taxa efetiva por período de capitalização",
                "n = Número de períodos de capitalização",
                "k = Número de capitalizações dentro do período da taxa nominal"
            ),
            formulaWhenToUse = "Utilizar em aplicações financeiras, empréstimos sob juros compostos, cálculo de taxa efetiva a partir de taxa nominal e equivalência composta.",
            exampleTitle = "Conversão de Taxa Nominal em Efetiva e Cálculo de Montante",
            exampleProblem = "Uma empresa aplicou R$ 100.000,00 pelo prazo de 1 ano à taxa nominal de 20% ao ano com capitalização semestral. Qual a taxa efetiva anual dessa operação e o montante bruto acumulado ao final de 1 ano?",
            exampleSteps = listOf(
                "Identificar a frequência de capitalização: 1 ano possui k = 2 semestres.",
                "Calcular a taxa efetiva semestral (proporcional à nominal): i_s = 20% / 2 = 10% ao semestre = 0,10.",
                "Calcular a taxa efetiva anual por capitalização composta: i_ef = (1 + 0,10)² - 1 = 1,21 - 1 = 0,21 = 21% ao ano.",
                "Determinar o montante após 1 ano (2 semestres): M = 100.000 × 1,21 = R$ 121.000,00."
            ),
            exampleAnswer = "Taxa efetiva anual de 21,0% e montante acumulado de R$ 121.000,00.",
            goldTip = "Toda vez que a Cesgranrio trouxer uma expressão como 'taxa de 18% ao ano com capitalização mensal', saiba que essa taxa é NOMINAL! Divida por 12 imediatamente para obter a taxa efetiva mensal de 1,5% a.m."
        )

        "tp_fin_m1_a4" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m1_a4",
            intro = "As operações de desconto de títulos, a equivalência de capitais no tempo e os sistemas de amortização (Price e SAC) compõem os tópicos mais decisivos de Matemática Financeira nas provas da Cesgranrio. O candidato precisa distinguir com precisão o desconto simples comercial ('por fora') do desconto racional ('por dentro'), além de compreender o comportamento das prestações e amortizações.",
            theory = """
                1. Conceitos de Valor Nominal (N) e Valor Atual/Líquido (A ou V):
                • Valor Nominal (N): valor de face do título na data de seu vencimento futuro.
                • Valor Atual (A): valor de resgate ou liquidação antecipada em n períodos antes do vencimento.
                • Desconto (D): abatimento concedido pela antecipação (D = N - A).

                2. Desconto Simples Comercial ou Bancário ('Por Fora' — D_c):
                Incide diretamente sobre o Valor Nominal (N) do título:
                • D_c = N × d × n   e   A_c = N × (1 - d × n)
                Se houver taxa administrativa/bancária prefixada h sobre o nominal: D_bancário = N × (d × n + h).

                3. Desconto Simples Racional ('Por Dentro' — D_r):
                Equivale ao juro simples calculado sobre o Valor Atual (A_r):
                • A_r = N / (1 + i × n)   e   D_r = A_r × i × n = (N × i × n) / (1 + i × n)
                • Relação fundamental entre D_c e D_r (para mesma taxa d = i e mesmo prazo n):
                  D_c = D_r × (1 + i × n)  →  O desconto comercial é SEMPRE maior que o desconto racional!

                4. Equivalência de Capitais e Séries Uniformes (SAC vs. Sistema Francês/Price):
                • Equivalência de Capitais: dois conjuntos de obrigações são equivalentes em uma data focal se a soma de seus valores atuais nessa data for igual. No regime simples, a data focal altera o resultado; no composto, a equivalência independe da data focal.
                • Sistema SAC (Amortização Constante): a cota de amortização é fixa (A = Saldo Devedor Inicial / n); os juros e as prestações (P_k = A + J_k) são decrescentes em PA.
                • Sistema Price (Francês): as prestações (PMT) são constantes ao longo de todo o prazo; os juros decrescem e as cotas de amortização crescem exponencialmente.
            """.trimIndent(),
            practicalApplication = """
                Quando uma fornecedora da cadeia de óleo e gás antecipa uma duplicata de R$ 50.000,00 junto a um banco 2 meses antes do vencimento sob taxa de desconto comercial simples de 3% a.m., o banco retém D_c = 50.000 × 0,03 × 2 = R$ 3.000,00, creditando R$ 47.000,00. Já na contratação de financiamentos de longo prazo pela Transpetro pelo sistema SAC, a amortização constante reduz rapidamente o saldo devedor e o gasto total com juros.
            """.trimIndent(),
            summary = """
                • Desconto Comercial ('por fora'): D_c = N × d × n  |  A_c = N × (1 - d × n).
                • Desconto Racional ('por dentro'): A_r = N / (1 + i × n)  |  D_r = N - A_r.
                • Relação direta: D_c = D_r × (1 + i × n), logo D_c > D_r.
                • SAC: Amortização constante e prestação decrescente. Price: Prestação constante e amortização crescente.
            """.trimIndent(),
            formulaName = "Desconto Simples Comercial, Racional e Sistemas de Amortização",
            formulaSyntax = "D_c = N × d × n   |   A_r = N / (1 + i × n)   |   D_c = D_r × (1 + i × n)",
            formulaTerms = listOf(
                "N = Valor Nominal (valor de face no vencimento)",
                "A_c, A_r = Valor Atual (líquido) nos descontos comercial e racional",
                "d, i = Taxa de desconto comercial e taxa de desconto racional",
                "n = Prazo de antecipação (períodos antes do vencimento)"
            ),
            formulaWhenToUse = "Utilizar no cálculo de antecipação de recebíveis, substituição de títulos (equivalência de capitais) e análise de parcelas nos sistemas SAC e Price.",
            exampleTitle = "Comparação entre Desconto Comercial e Racional em Duplicata",
            exampleProblem = "Um título de valor nominal igual a R$ 24.000,00 é descontado 4 meses antes de seu vencimento à taxa simples de 5% ao mês. Calcule o valor do desconto comercial simples (D_c) e o valor do desconto racional simples (D_r).",
            exampleSteps = listOf(
                "Calcular o fator de prazo × taxa: i × n = 0,05 × 4 = 0,20.",
                "Apurar o Desconto Comercial ('por fora'): D_c = N × d × n = 24.000 × 0,20 = R$ 4.800,00 (Valor Atual Comercial = R$ 19.200,00).",
                "Apurar o Valor Atual Racional ('por dentro'): A_r = N / (1 + i × n) = 24.000 / 1,20 = R$ 20.000,00.",
                "Apurar o Desconto Racional: D_r = N - A_r = 24.000 - 20.000 = R$ 4.000,00 (confira: D_c = 4.000 × 1,20 = 4.800)."
            ),
            exampleAnswer = "Desconto comercial de R$ 4.800,00 e desconto racional de R$ 4.000,00.",
            goldTip = "Memorize a identidade D_c = D_r × (1 + i × n). Se a Cesgranrio der o valor do desconto comercial e do desconto racional do mesmo título, basta dividir D_c por D_r para descobrir imediatamente o fator (1 + i × n)!"
        )

        // =====================================================================
        // MÓDULO 2: FLUXO DE CAIXA E ADMINISTRAÇÃO FINANCEIRA
        // =====================================================================
        "tp_fin_m2_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m2_a1",
            intro = "O controle do Fluxo de Caixa e a Demonstração dos Fluxos de Caixa (DFC — CPC 03 / Lei nº 6.404/1976) são instrumentos essenciais para garantir a solvência diária e a transparência financeira da Transpetro. Compreender a mecânica entre saldo inicial, entradas (ingressos), saídas (desembolsos) e a segregação entre atividades operacionais, de investimento e de financiamento é presença constante nas provas da Cesgranrio.",
            theory = """
                1. Conceitos Fundamentais de Fluxo de Caixa e Regime de Caixa:
                O fluxo de caixa registra exclusivamente as movimentações financeiras efetivas de numerário (Caixa e Equivalentes de Caixa — aplicações financeiras de liquidez imediata, conversíveis em montante conhecido de caixa e sujeitas a insignificante risco de mudança de valor, geralmente com vencimento original de até 90 dias).
                • Equação Básica de Tesouraria:
                  Saldo Final de Caixa = Saldo Inicial + Entradas Efetivas - Saídas Efetivas
                  Geração Líquida de Caixa (ΔCaixa) = Entradas - Saídas

                2. Estrutura da DFC (CPC 03): As Três Categorias de Atividades:
                • Atividades Operacionais (FCO): principais atividades geradoras de receita da entidade (recebimentos de clientes por transporte e armazenagem, pagamentos a fornecedores, salários, encargos e tributos operacionais).
                • Atividades de Investimento (FCI): aquisição e venda de ativos de longo prazo (Imobilizado, Intangível, Investimentos permanentes) e aplicações financeiras que não sejam equivalentes de caixa.
                • Atividades de Financiamento (FCF): mudanças no tamanho e na composição do capital próprio e do endividamento (integralização de capital, captação e amortização do principal de empréstimos e debêntures, pagamento de dividendos).

                3. Método Direto vs. Método Indireto no FCO:
                • Método Direto: explicita as classes brutas de recebimentos e pagamentos operacionais.
                • Método Indireto: parte do Lucro Líquido do Exercício (DRE), soma despesas que não afetam o caixa (como Depreciação, Amortização e Exaustão) e ajusta pelas variações nas contas operacionais do circulante (aumento de clientes ou estoques consome caixa; aumento de fornecedores preserva caixa).
            """.trimIndent(),
            practicalApplication = """
                Na programação financeira mensal de um terminal da Transpetro, a tesouraria inicia o mês com saldo de R$ 2,0 milhões, recebe R$ 8,5 milhões de tarifas de movimentação (operacional), desembolsa R$ 5,2 milhões com folha, energia e fornecedores (operacional) e paga R$ 1,3 milhão pela compra à vista de bombas centrífugas (investimento). A geração líquida é de +R$ 2,0 milhões, encerrando o mês com saldo disponível de R$ 4,0 milhões.
            """.trimIndent(),
            summary = """
                • Fluxo de Caixa segue o Regime de Caixa (entrada e saída efetiva de dinheiro).
                • Saldo Final = Saldo Inicial + Ingressos - Desembolsos.
                • Operacional: giro do negócio (clientes, fornecedores, salários, impostos).
                • Investimento: compra/venda de Imobilizado, Intangível e participações.
                • Financiamento: captação/amortização de empréstimos, emissão de ações e dividendos pagos.
            """.trimIndent(),
            formulaName = "Equação Fundamental do Fluxo de Caixa e DFC",
            formulaSyntax = "SF = SI + FCO + FCI + FCF   |   ΔCaixa = Entradas - Saídas",
            formulaTerms = listOf(
                "SF e SI = Saldo Final e Saldo Inicial de Caixa e Equivalentes de Caixa",
                "FCO = Fluxo de Caixa das Atividades Operacionais",
                "FCI = Fluxo de Caixa das Atividades de Investimento",
                "FCF = Fluxo de Caixa das Atividades de Financiamento"
            ),
            formulaWhenToUse = "Aplicar para apurar a variação líquida de caixa do período, o saldo final de disponibilidade e a classificação dos eventos na DFC.",
            exampleTitle = "Apuração do Saldo Final e Classificação de Fluxos de Caixa",
            exampleProblem = "Em determinado trimestre, uma empresa estatal apresentava saldo inicial de caixa de R$ 500.000,00. No período, ocorreram apenas os seguintes eventos financeiros à vista: recebimento de clientes (R$ 1.200.000,00); pagamento de fornecedores e salários (R$ 750.000,00); aquisição de equipamentos industriais (R$ 300.000,00); e captação de novo empréstimo bancário (R$ 200.000,00). Calcule o FCO, o FCI, o FCF e o Saldo Final de Caixa.",
            exampleSteps = listOf(
                "Apurar o Fluxo Operacional (FCO): Recebimento de clientes (+1.200.000) - Pagamento a fornecedores e salários (-750.000) = +R$ 450.000,00.",
                "Apurar o Fluxo de Investimento (FCI): Aquisição de equipamentos imobilizados = -R$ 300.000,00.",
                "Apurar o Fluxo de Financiamento (FCF): Captação de empréstimo bancário = +R$ 200.000,00.",
                "Calcular a geração líquida (ΔCaixa) = 450.000 - 300.000 + 200.000 = +R$ 350.000,00; Saldo Final = 500.000 + 350.000 = R$ 850.000,00."
            ),
            exampleAnswer = "FCO = +R$ 450.000,00; FCI = -R$ 300.000,00; FCF = +R$ 200.000,00; Saldo Final = R$ 850.000,00.",
            goldTip = "A Depreciação reduz o lucro na DRE, mas NÃO representa saída de dinheiro! No método indireto da DFC, a depreciação deve ser SOMADA de volta ao Lucro Líquido para encontrar o caixa gerado pelas operações."
        )

        "tp_fin_m2_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m2_a2",
            intro = "Na rotina diária de Administração e Controle da Transpetro, a execução financeira depende do rigoroso acompanhamento de Contas a Pagar, Contas a Receber, da classificação precisa dos fluxos operacionais, de investimento e de financiamento, e da Conciliação Bancária. A Cesgranrio cobra tanto a separação das atividades financeiras quanto os ajustes entre o razão contábil da empresa e o extrato bancário.",
            theory = """
                1. Controle de Contas a Pagar e Contas a Receber:
                • Contas a Pagar (Desembolsos): exige verificação da liquidação regular da despesa (conferência da nota fiscal com o pedido de compra e o ateste de medição do fiscal do contrato), retenção de tributos na fonte, programação de vencimentos (aging list) e prevenção de multas e juros moratórios.
                • Contas a Receber (Ingressos): envolve faturamento pontual dos serviços de transporte e tancagem, emissão de boletos/faturas, controle de inadimplência (régua de cobrança) e baixa de títulos liquidados.

                2. Classificação Detalhada dos Fluxos (Operacional, Investimento e Financiamento):
                • Operacional: recebimentos de vendas/serviços, pagamentos de insumos, tributos sobre vendas e renda, seguros e folha.
                • Investimento: compra/venda à vista de imobilizado, intangível, concessão e recebimento do principal de empréstimos feitos a terceiros.
                • Financiamento: emissão de ações/debêntures, tomada e amortização do principal de empréstimos obtidos e dividendos pagos aos acionistas.

                3. Conciliação Bancária:
                Procedimento de controle interno que confronta o saldo da conta contábil 'Bancos Conta Movimento' (escrituração interna da empresa) com o saldo informado no Extrato Bancário na mesma data-base, identificando diferenças temporárias ou pendências de registro:
                • Cheques emitidos e ainda não compensados (em trânsito): já foram subtraídos na contabilidade da empresa, mas ainda constam no saldo do extrato bancário → devem ser SUBTRAÍDOS do saldo do extrato na conciliação.
                • Depósitos em trânsito (bloqueados/não compensados): já foram somados na contabilidade, mas ainda não constam no extrato → devem ser SOMADOS ao saldo do extrato.
                • Tarifas bancárias, IOF, débitos automáticos e cheques devolvidos: já reduziram o extrato bancário, mas ainda não foram contabilizados pela empresa → devem ser SUBTRAÍDOS do razão contábil da empresa.
                • Créditos de cobrança ou rendimentos creditados pelo banco sem aviso prévio: já estão no extrato, devendo ser SOMADOS ao razão contábil da empresa.
            """.trimIndent(),
            practicalApplication = """
                Ao fechar o movimento do dia 30, o técnico administrativo da Transpetro verifica que o razão contábil da conta Bancos aponta R$ 120.000,00, enquanto o extrato bancário exibe R$ 132.000,00. Na conciliação, ele identifica um pagamento de R$ 15.000,00 agendado via ordem de pagamento ainda não compensado pelo banco e R$ 3.000,00 de tarifas de custódia debitadas no extrato sem lançamento contábil. Após lançar a tarifa no razão (120.000 - 3.000 = 117.000) e deduzir a ordem em trânsito do extrato (132.000 - 15.000 = 117.000), os saldos conciliam perfeitamente em R$ 117.000,00.
            """.trimIndent(),
            summary = """
                • Contas a Pagar: exige tríplice conferência (Contrato/Pedido + Nota Fiscal + Ateste/Medição).
                • Conciliação Bancária iguala o Saldo Contábil Ajustado ao Saldo do Extrato Ajustado.
                • Ajustes no Extrato: (-) cheques/ordens em trânsito; (+) depósitos em trânsito.
                • Ajustes na Contabilidade: (-) tarifas/despesas bancárias e cheques sem fundos; (+) avisos de crédito não contabilizados.
            """.trimIndent(),
            formulaName = "Igualdade da Conciliação Bancária",
            formulaSyntax = "Saldo_Contábil + Créditos_Aviso - Tarifas = Saldo_Extrato + Depósitos_Trânsito - Cheques_Trânsito",
            formulaTerms = listOf(
                "Saldo_Contábil = Saldo registrado no livro Razão da empresa antes da conciliação",
                "Saldo_Extrato = Saldo final reportado pelo banco no extrato da data-base",
                "Cheques_Trânsito = Ordens ou cheques emitidos pela empresa e ainda não descontados no banco",
                "Depósitos_Trânsito = Valores depositados pela empresa e ainda não liberados no extrato"
            ),
            formulaWhenToUse = "Utilizar para encontrar o saldo disponível real e identificar lançamentos pendentes na tesouraria.",
            exampleTitle = "Conciliação entre Razão Contábil e Extrato Bancário",
            exampleProblem = "No encerramento do mês, o livro Razão da conta Bancos de uma unidade operacional indicava saldo de R$ 85.000,00. O extrato bancário na mesma data apresentava saldo de R$ 94.200,00. Foram constatadas três divergências: (1) cheque emitido para fornecedor no valor de R$ 12.000,00 ainda não apresentado ao banco; (2) tarifas bancárias de R$ 800,00 debitadas no extrato e não contabilizadas; e (3) depósito de cliente de R$ 2.000,00 em compensação (já registrado no Razão, mas ausente no extrato). Qual é o saldo bancário real conciliado?",
            exampleSteps = listOf(
                "Ajustar o Saldo Contábil (Razão): subtrair a tarifa bancária ainda não registrada: 85.000 - 800 = R$ 84.200,00.",
                "Ajustar o Saldo do Extrato Bancário: somar o depósito em trânsito (+2.000) e subtrair o cheque em trânsito (-12.000).",
                "Calcular o Saldo Ajustado do Extrato: 94.200 + 2.000 - 12.000 = R$ 84.200,00.",
                "Conferir a igualdade: ambos os saldos ajustados convergem para R$ 84.200,00."
            ),
            exampleAnswer = "O saldo bancário real e conciliado é de R$ 84.200,00.",
            goldTip = "Pergunte sempre: 'Quem ainda não sabe desse evento: o Banco ou a Contabilidade da empresa?'. Cheques em trânsito o banco ainda não sabe (ajuste o extrato); tarifas bancárias a contabilidade ainda não sabe (ajuste o razão)."
        )

        "tp_fin_m2_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m2_a3",
            intro = "A administração do Capital de Giro, o Capital Circulante Líquido (CCL), a análise dos Ciclos Operacional, Econômico e Financeiro e os Indicadores de Liquidez determinam a saúde financeira de curto prazo da organização. Na prova da Transpetro, a Cesgranrio avalia a capacidade de calcular a folga financeira e interpretar o impacto dos prazos médios de estocagem, recebimento e pagamento.",
            theory = """
                1. Capital de Giro (Ativo Circulante) e Capital Circulante Líquido (CCL / CDG):
                • Capital de Giro Total: corresponde ao total do Ativo Circulante (AC — disponibilidades, créditos de curto prazo e estoques).
                • Capital Circulante Líquido (CCL) ou Capital de Giro Líquido (CGL): é a diferença entre o Ativo Circulante (AC) e o Passivo Circulante (PC):
                  CCL = AC - PC
                  - Se AC > PC → CCL > 0 (Positivo/Superavitário: parte do ativo de curto prazo é financiada por recursos de longo prazo).
                  - Se AC < PC → CCL < 0 (Negativo/Deficitário: recursos de curto prazo estão financiando ativos não circulantes).
                  - Se AC = PC → CCL = 0 (Nulo).

                2. Ciclos Operacional, Econômico e Financeiro (Ciclo de Caixa):
                • Prazo Médio de Estocagem (PME): tempo médio que os materiais permanecem em estoque até o consumo/venda.
                • Prazo Médio de Recebimento (PMR): tempo médio entre o faturamento do serviço/venda e o efetivo recebimento dos clientes.
                • Prazo Médio de Pagamento (PMP): tempo médio entre a compra dos insumos e o pagamento aos fornecedores.
                • Ciclo Econômico (CE): CE = PME (tempo em que o recurso fica imobilizado fisicamente em estoques).
                • Ciclo Operacional (CO): CO = PME + PMR (vai da compra da matéria-prima até o recebimento da venda).
                • Ciclo Financeiro ou Ciclo de Caixa (CF): CF = CO - PMP = PME + PMR - PMP (intervalo entre o desembolso ao fornecedor e o recebimento do cliente). Quanto menor o Ciclo Financeiro, menor a Necessidade de Capital de Giro (NCG).

                3. Indicadores Fundamentais de Liquidez:
                • Liquidez Corrente (LC): LC = AC / PC (existe folga de curto prazo quando LC > 1, o que equivale a CCL > 0).
                • Liquidez Seca (LS): LS = (AC - Estoques - Despesas Antecipadas) / PC (exclui ativos de realização menos imediata).
                • Liquidez Imediata (LI): LI = Disponibilidades / PC.
                • Liquidez Geral (LG): LG = (AC + Realizável a Longo Prazo) / (PC + Passivo Não Circulante).
            """.trimIndent(),
            practicalApplication = """
                Se a Transpetro mantém peças sobressalentes em estoque por 40 dias (PME = 40), recebe as faturas de transporte em 30 dias (PMR = 30) e paga seus fornecedores em 25 dias (PMP = 25), seu Ciclo Operacional é de 70 dias (40 + 30) e seu Ciclo Financeiro é de 45 dias (70 - 25). Se a área de Suprimentos renegociar o prazo de pagamento junto aos fornecedores para 40 dias, o Ciclo Financeiro cai para 30 dias, reduzindo a pressão sobre o caixa.
            """.trimIndent(),
            summary = """
                • CCL = Ativo Circulante (AC) - Passivo Circulante (PC).
                • Ciclo Econômico = PME.
                • Ciclo Operacional (CO) = PME + PMR.
                • Ciclo Financeiro / de Caixa (CF) = PME + PMR - PMP.
                • Liquidez Corrente (LC = AC / PC) > 1 se, e somente se, CCL > 0.
            """.trimIndent(),
            formulaName = "Capital Circulante Líquido, Ciclo Financeiro e Liquidez Corrente",
            formulaSyntax = "CCL = AC - PC   |   CF = PME + PMR - PMP   |   LC = AC / PC",
            formulaTerms = listOf(
                "CCL = Capital Circulante Líquido (Capital de Giro Líquido)",
                "AC e PC = Ativo Circulante e Passivo Circulante",
                "PME, PMR, PMP = Prazos Médios de Estocagem, Recebimento e Pagamento (em dias)",
                "CF = Ciclo Financeiro (Ciclo de Caixa)",
                "LC = Índice de Liquidez Corrente"
            ),
            formulaWhenToUse = "Utilizar para avaliar a solvência de curto prazo, calcular a folga financeira e dimensionar o giro operacional em dias.",
            exampleTitle = "Cálculo do CCL, Liquidez Corrente e Ciclo Financeiro",
            exampleProblem = "Uma companhia apresenta Ativo Circulante de R$ 900.000,00 (sendo R$ 300.000,00 em Estoques) e Passivo Circulante de R$ 600.000,00. Seus prazos médios são: PME = 35 dias, PMR = 25 dias e PMP = 20 dias. Determine o CCL, a Liquidez Corrente, a Liquidez Seca, o Ciclo Operacional e o Ciclo Financeiro.",
            exampleSteps = listOf(
                "Calcular o Capital Circulante Líquido: CCL = AC - PC = 900.000 - 600.000 = R$ 300.000,00.",
                "Calcular a Liquidez Corrente: LC = 900.000 / 600.000 = 1,50.",
                "Calcular a Liquidez Seca: LS = (900.000 - 300.000) / 600.000 = 600.000 / 600.000 = 1,00.",
                "Calcular os ciclos: Ciclo Operacional = 35 + 25 = 60 dias; Ciclo Financeiro = 60 - 20 = 40 dias."
            ),
            exampleAnswer = "CCL = R$ 300.000,00; LC = 1,50; LS = 1,00; Ciclo Operacional = 60 dias; Ciclo Financeiro = 40 dias.",
            goldTip = "Repare na conexão direta: sempre que o CCL for positivo (AC > PC), a Liquidez Corrente (AC / PC) será obrigatoriamente maior que 1,0!"
        )

        "tp_fin_m2_a4" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m2_a4",
            intro = "Além dos indicadores de liquidez, a análise econômico-financeira abrange os índices de Endividamento, Rentabilidade e os métodos quantitativos de viabilidade de projetos de investimento (VPL, TIR e Payback). A Cesgranrio cobra a interpretação gerencial desses indicadores na seleção de projetos de infraestrutura e logística.",
            theory = """
                1. Indicadores de Estrutura de Capital e Endividamento:
                • Endividamento Geral (EG): (PC + PNC) / Ativo Total (mede a proporção dos ativos financiada por capital de terceiros).
                • Participação de Capitais de Terceiros (PCT): (PC + PNC) / Patrimônio Líquido.
                • Composição do Endividamento (CE): PC / (PC + PNC) — indica quanto da dívida total vence no curto prazo (quanto menor, melhor o perfil da dívida).

                2. Indicadores de Rentabilidade:
                • Giro do Ativo: Receita Líquida / Ativo Total.
                • Margem Líquida: Lucro Líquido / Receita Líquida.
                • Retorno sobre o Ativo (ROA ou ROI): Lucro Operacional (ou Líquido) / Ativo Total.
                • Retorno sobre o Patrimônio Líquido (ROE): Lucro Líquido / Patrimônio Líquido.

                3. Análise de Viabilidade de Projetos (VPL, TIR e Payback):
                • Valor Presente Líquido (VPL): soma algébrica dos fluxos de caixa futuros descontados à Taxa Mínima de Atratividade (TMA, ou custo de capital k), subtraído o investimento inicial (I₀):
                  VPL = -I₀ + Σ [FC_t / (1 + k)^t].
                  Critério de decisão: se VPL > 0, o projeto cria valor econômico e deve ser aceito; se VPL = 0, remunera exatamente a TMA; se VPL < 0, deve ser rejeitado.
                • Taxa Interna de Retorno (TIR): é a taxa de desconto r que torna o VPL exatamente igual a zero (VPL(r) = 0). O projeto é viável quando TIR > TMA.
                • Payback (Simples e Descontado): tempo necessário para que os fluxos de caixa acumulados recuperem o investimento inicial.
            """.trimIndent(),
            practicalApplication = """
                Para decidir pela automação de um sistema de medição em dutos com investimento inicial de R$ 500.000,00 e economia operacional estimada no fluxo de caixa pelos próximos anos, a área financeira da Transpetro desconta os fluxos pela Taxa Mínima de Atratividade (TMA) da companhia. Constatando VPL positivo de R$ 85.000,00 (logo, TIR superior à TMA), o investimento é aprovado tecnicamente.
            """.trimIndent(),
            summary = """
                • Composição do Endividamento = PC / Passivo Exigível Total (mede concentração no curto prazo).
                • ROE = Lucro Líquido / PL; ROA = Lucro Líquido / Ativo Total.
                • VPL > 0 ⇔ TIR > TMA (Projeto economicamente viável).
                • VPL = 0 ⇔ TIR = TMA (Projeto no limite de indiferença financeira).
            """.trimIndent(),
            formulaName = "Valor Presente Líquido (VPL) e Retorno sobre o Patrimônio (ROE)",
            formulaSyntax = "VPL = -I_0 + Σ [FC_t / (1 + TMA)^t]   |   ROE = Lucro_Líquido / PL",
            formulaTerms = listOf(
                "VPL = Valor Presente Líquido do projeto de investimento",
                "I_0 = Desembolso de investimento inicial na data zero",
                "FC_t = Fluxo de caixa líquido esperado no período t",
                "TMA = Taxa Mínima de Atratividade (custo de oportunidade do capital)",
                "ROE = Return on Equity (Retorno sobre o Patrimônio Líquido)"
            ),
            formulaWhenToUse = "Utilizar na avaliação de viabilidade financeira de projetos de capital e na análise de rentabilidade e endividamento.",
            exampleTitle = "Cálculo de VPL para Projeto de Modernização Operacional",
            exampleProblem = "Um projeto de eficiência energética em um terminal exige investimento inicial imediato de R$ 200.000,00 e gerará entradas líquidas de caixa de R$ 121.000,00 no final do ano 1 e R$ 133.100,00 no final do ano 2. Sabendo que a Taxa Mínima de Atratividade (TMA) é de 10% ao ano, calcule o VPL e indique se o projeto é viável.",
            exampleSteps = listOf(
                "Trazer o fluxo do ano 1 a valor presente: VP₁ = 121.000 / (1 + 0,10)¹ = 121.000 / 1,10 = R$ 110.000,00.",
                "Trazer o fluxo do ano 2 a valor presente: VP₂ = 133.100 / (1 + 0,10)² = 133.100 / 1,21 = R$ 110.000,00.",
                "Somar os valores presentes das entradas: VP_entradas = 110.000 + 110.000 = R$ 220.000,00.",
                "Calcular o VPL subtraindo o investimento inicial: VPL = 220.000 - 200.000 = +R$ 20.000,00 (como VPL > 0 e TIR > 10%, o projeto é viável)."
            ),
            exampleAnswer = "VPL positivo de R$ 20.000,00; o projeto é economicamente viável.",
            goldTip = "Na Cesgranrio, se a Taxa Interna de Retorno (TIR) de um projeto convencional é igual a 15% a.a. e a TMA da empresa é de 12% a.a., você nem precisa fazer contas: como TIR > TMA, o VPL é obrigatoriamente POSITIVO!"
        )

        // =====================================================================
        // MÓDULO 3: CONTABILIDADE GERAL
        // =====================================================================
        "tp_fin_m3_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m3_a1",
            intro = "O estudo da Contabilidade Geral inicia-se pela compreensão do objeto contábil — o Patrimônio — e de seus componentes qualitativos e quantitativos. Nas provas da Cesgranrio para a Transpetro, a Equação Fundamental do Patrimônio e a classificação das Situações Líquidas Patrimoniais são cobradas em questões de cálculo direto e análise teórica.",
            theory = """
                1. Conceito Contábil de Patrimônio:
                O patrimônio é o conjunto de Bens, Direitos e Obrigações vinculados a uma entidade econômica (pessoa física ou jurídica), avaliado em moeda:
                • Bens: elementos materiais (tangíveis: imóveis, navios, dutos, veículos, estoques, dinheiro em caixa) ou imateriais (intangíveis: softwares, marcas, patentes) úteis às atividades da entidade.
                • Direitos: valores a receber de terceiros (duplicatas a receber, clientes, adiantamentos a fornecedores, impostos a recuperar, aplicações em bancos).
                • Obrigações (Passivo Exigível): dívidas e compromissos assumidos perante terceiros que serão exigidos no vencimento (fornecedores, salários a pagar, impostos a recolher, empréstimos bancários).

                2. Equação Fundamental do Patrimônio:
                • Ativo (A) = Bens + Direitos (parte positiva, aplicações de recursos).
                • Passivo Exigível (PE ou P) = Obrigações com terceiros (capital de terceiros).
                • Patrimônio Líquido (PL) ou Situação Líquida (SL) = riqueza líquida efetiva (capital próprio).
                • Equação Básica: Ativo = Passivo Exigível + Patrimônio Líquido  (A = P + PL  ⇔  PL = A - P).

                3. As Situações Líquidas Patrimoniais:
                • Positiva, Superavitária ou Ativa: A > P ⇒ PL > 0.
                • Nula, Equilibrada ou Inexistente: A = P ⇒ PL = 0.
                • Negativa, Deficitária, Passiva ou Passivo a Descoberto: A < P ⇒ PL < 0 (as dívidas superam o total de bens e direitos).
            """.trimIndent(),
            practicalApplication = """
                Ao levantar os saldos patrimoniais de uma subsidiária logística que detém R$ 800.000,00 em Bens, R$ 400.000,00 em Direitos junto a clientes e R$ 500.000,00 em Obrigações com fornecedores e bancos, o analista apura Ativo Total de R$ 1.200.000,00 e Passivo Exigível de R$ 500.000,00. Pela equação fundamental, o Patrimônio Líquido é de R$ 700.000,00, configurando Situação Líquida Positiva (Superavitária).
            """.trimIndent(),
            summary = """
                • Patrimônio = Bens + Direitos (Ativo) e Obrigações (Passivo Exigível).
                • Equação Fundamental: Ativo = Passivo Exigível + Patrimônio Líquido (PL = A - P).
                • A > P: Situação Líquida Positiva / Superavitária.
                • A < P: Situação Líquida Negativa / Passivo a Descoberto.
            """.trimIndent(),
            formulaName = "Equação Fundamental da Contabilidade",
            formulaSyntax = "Ativo = Bens + Direitos   |   Ativo = Passivo_Exigível + Patrimônio_Líquido",
            formulaTerms = listOf(
                "Ativo = Total de aplicações de recursos (Bens + Direitos)",
                "Passivo_Exigível = Obrigações perante terceiros (Capital de Terceiros)",
                "Patrimônio_Líquido = Riqueza própria da entidade (Capital Próprio / Situação Líquida)"
            ),
            formulaWhenToUse = "Utilizar para encontrar o valor do Ativo, do Passivo Exigível ou do Patrimônio Líquido a partir de uma listagem de contas patrimoniais.",
            exampleTitle = "Apuração de Ativo, Passivo e Situação Líquida",
            exampleProblem = "Considere os seguintes saldos de uma empresa: Caixa = R$ 20.000; Clientes (Duplicatas a Receber) = R$ 45.000; Estoques = R$ 35.000; Fornecedores = R$ 30.000; Empréstimos a Pagar = R$ 25.000; Máquinas e Equipamentos = R$ 80.000; Salários a Pagar = R$ 15.000. Calcule o valor dos Bens, dos Direitos, do Ativo Total, do Passivo Exigível e do Patrimônio Líquido.",
            exampleSteps = listOf(
                "Somar os Bens (tangíveis/disponíveis): Caixa (20.000) + Estoques (35.000) + Máquinas (80.000) = R$ 135.000,00.",
                "Identificar os Direitos: Clientes (Duplicatas a Receber) = R$ 45.000,00.",
                "Calcular o Ativo Total (Bens + Direitos): 135.000 + 45.000 = R$ 180.000,00.",
                "Somar as Obrigações (Passivo Exigível): Fornecedores (30.000) + Empréstimos (25.000) + Salários a Pagar (15.000) = R$ 70.000,00.",
                "Determinar o Patrimônio Líquido: PL = Ativo - Passivo = 180.000 - 70.000 = R$ 110.000,00 (Situação Líquida Positiva)."
            ),
            exampleAnswer = "Bens = R$ 135.000; Direitos = R$ 45.000; Ativo = R$ 180.000; Passivo Exigível = R$ 70.000; Patrimônio Líquido = R$ 110.000.",
            goldTip = "Cuidado com as expressões 'Adiantamento a Fornecedores' (é um DIREITO de receber a mercadoria → Ativo!) e 'Adiantamento de Clientes' (é uma OBRIGAÇÃO de entregar o produto/serviço → Passivo!)."
        )

        "tp_fin_m3_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m3_a2",
            intro = "O funcionamento da escrituração contábil apoia-se na estrutura dos grupos patrimoniais, na natureza devedora ou credora das contas e no Método das Partidas Dobradas. Compreender por que uma conta de Ativo aumenta por Débito (enquanto na linguagem bancária comum 'crédito' parece algo positivo) é indispensável para acertar as questões da Cesgranrio.",
            theory = """
                1. Grupos Patrimoniais, Grau de Liquidez e Grau de Exigibilidade (Lei nº 6.404/1976 e CPC 26):
                • Ativo (Aplicações de recursos — disposto em ordem DECRESCENTE de grau de liquidez):
                  - Ativo Circulante (AC): disponibilidades e itens realizáveis até o término do exercício social seguinte (12 meses) ou dentro do ciclo operacional.
                  - Ativo Não Circulante (ANC): subdividido em Realizável a Longo Prazo (RLP), Investimentos, Imobilizado e Intangível.
                • Passivo e Patrimônio Líquido (Origens de recursos — dispostos em ordem DECRESCENTE de grau de exigibilidade):
                  - Passivo Circulante (PC): obrigações exigíveis até o fim do exercício seguinte (12 meses).
                  - Passivo Não Circulante (PNC): obrigações exigíveis após o término do exercício seguinte.
                  - Patrimônio Líquido (PL): Capital Social, Reservas de Capital, Ajustes de Avaliação Patrimonial, Reservas de Lucros, Ações em Tesouraria (retificadora) e Prejuízos Acumulados (retificadora).

                2. Natureza das Contas Contábeis (Devedora vs. Credora):
                • Contas de Natureza DEVEDORA (aumentam por DÉBITO e diminuem por CRÉDITO):
                  - Contas do ATIVO (Aplicações de recursos)
                  - Contas de DESPESAS e CUSTOS
                  - Contas Retificadoras do Passivo e do PL (ex.: Prejuízos Acumulados, Ações em Tesouraria, Encargos Financeiros a Transcorrer)
                • Contas de Natureza CREDORA (aumentam por CRÉDITO e diminuem por DÉBITO):
                  - Contas do PASSIVO EXIGÍVEL e do PATRIMÔNIO LÍQUIDO (Origens de recursos)
                  - Contas de RECEITAS
                  - Contas Retificadoras do Ativo (ex.: Depreciação Acumulada, Perdas Estimadas com Créditos de Liquidação Duvidosa — PECLD)

                3. Método das Partidas Dobradas (Luca Pacioli):
                Não há débito(s) sem crédito(s) correspondente(s) de igual valor total. Em qualquer lançamento contábil, a soma dos valores debitados é sempre idêntica à soma dos valores creditados, fazendo com que o total dos saldos devedores no Balancete de Verificação seja igual ao total dos saldos credores.
            """.trimIndent(),
            practicalApplication = """
                Quando a Transpetro adquire um lote de peças de reposição para o almoxarifado por R$ 40.000,00 a prazo, ocorre uma origem de recurso em Fornecedores (Passivo — natureza credora, aumenta por Crédito de R$ 40.000,00) e uma aplicação de recurso em Estoques (Ativo — natureza devedora, aumenta por Débito de R$ 40.000,00), respeitando integralmente o método das partidas dobradas.
            """.trimIndent(),
            summary = """
                • Ativo e Despesas: natureza DEVEDORA (+ Débito, - Crédito).
                • Passivo, PL e Receitas: natureza CREDORA (+ Crédito, - Débito).
                • Contas Retificadoras possuem natureza OPOSTA à do grupo a que pertencem (ex.: Depreciação Acumulada é conta do Ativo com saldo CREDOR).
                • Partidas Dobradas: Total de Débitos = Total de Créditos.
            """.trimIndent(),
            formulaName = "Igualdade das Partidas Dobradas e Mecanismo de Débito e Crédito",
            formulaSyntax = "∑ Débitos = ∑ Créditos   |   Ativo/Despesa: (+D, -C)   |   Passivo/PL/Receita: (+C, -D)",
            formulaTerms = listOf(
                "Débito (D) = Aplicação de recurso (aumenta Ativo/Despesa e reduz Passivo/PL)",
                "Crédito (C) = Origem de recurso (aumenta Passivo/PL/Receita e reduz Ativo)",
                "Conta Retificadora = Conta que reduz o saldo do grupo principal por ter natureza inversa"
            ),
            formulaWhenToUse = "Aplicar para identificar o lançamento contábil correto (qual conta debitar e qual creditar) e classificar contas por natureza.",
            exampleTitle = "Escrituração pelo Método das Partidas Dobradas",
            exampleProblem = "Uma empresa liquidou uma duplicata de fornecedor no valor de R$ 15.000,00 por meio de transferência bancária, sem juros ou descontos. Quais contas foram movimentadas, qual a natureza de cada uma e qual o lançamento contábil realizado?",
            exampleSteps = listOf(
                "Identificar as contas envolvidas: 'Fornecedores' (Passivo Circulante, natureza credora) e 'Bancos Conta Movimento' (Ativo Circulante, natureza devedora).",
                "Determinar o efeito na conta Fornecedores: a dívida diminuiu em R$ 15.000,00. Como Passivo tem natureza credora, sua redução faz-se mediante DÉBITO.",
                "Determinar o efeito na conta Bancos: o saldo bancário diminuiu em R$ 15.000,00. Como Ativo tem natureza devedora, sua redução faz-se mediante CRÉDITO.",
                "Registrar o lançamento: Débito em Fornecedores (R$ 15.000,00) e Crédito em Bancos Conta Movimento (R$ 15.000,00)."
            ),
            exampleAnswer = "Débito na conta Fornecedores (redução de Passivo) e Crédito na conta Bancos Conta Movimento (redução de Ativo), no valor de R$ 15.000,00.",
            goldTip = "Desconecte o conceito contábil do extrato bancário pessoal: na Contabilidade da empresa, receber dinheiro no caixa ou no banco é sempre um DÉBITO na conta Caixa/Bancos, e pagar algo é sempre um CRÉDITO na conta Caixa/Bancos!"
        )
        else -> getBundlePart2(lessonId)
    }

    private fun getBundlePart2(lessonId: String): CalculusDidacticBundle? = when (lessonId) {
        "tp_fin_m3_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m3_a3",
            intro = "Dominar a estrutura do Balanço Patrimonial (Ativo Circulante e Não Circulante, Passivo e Patrimônio Líquido), o cálculo da Depreciação e a distinção entre os Regimes Contábeis (Competência vs. Caixa) e os Fatos Contábeis (Permutativos, Modificativos e Mistos) é essencial nas provas da Cesgranrio para a Transpetro.",
            theory = """
                1. Regimes Contábeis: Competência versus Caixa:
                • Regime de Competência (obrigatório pela Lei nº 6.404/1976 e CPC 00 para a DRE e o Balanço): as receitas e as despesas são reconhecidas no período em que ocorre o seu FATO GERADOR (prestação do serviço, entrega da mercadoria, incorrência do gasto), independentemente do efetivo recebimento ou pagamento em dinheiro.
                • Regime de Caixa: receitas e despesas são computadas apenas no momento da entrada ou saída financeira efetiva de numerário (aplicado na DFC).

                2. Atos Administrativos vs. Fatos Contábeis (Patrimoniais):
                • Ato Administrativo: evento que não provoca alteração imediata no patrimônio (ex.: assinatura de contrato, admissão de empregado, envio de cotação).
                • Fato Contábil Permutativo (Qualitativo ou Compensativo): altera a qualidade dos elementos patrimoniais (troca entre contas de Ativo e/ou Passivo) SEM alterar o valor do Patrimônio Líquido (ex.: compra de veículo à vista ou a prazo; pagamento de fornecedor sem juros).
                • Fato Contábil Modificativo (Quantitativo): provoca variação positiva (Aumentativo — receita) ou negativa (Diminutivo — despesa) no Patrimônio Líquido (ex.: apropriação da folha de salários; registro da despesa de depreciação).
                • Fato Contábil Misto (Composto): combina uma permuta patrimonial com uma variação no Patrimônio Líquido (ex.: pagamento de duplicata com juros de mora ou com desconto obtido; venda de ativo imobilizado com lucro ou prejuízo).

                3. Estrutura do Balanço Patrimonial e Depreciação (CPC 26 e CPC 27):
                • Ativo Circulante (AC) e Ativo Não Circulante (ANC = Realizável a Longo Prazo + Investimentos + Imobilizado + Intangível).
                • Passivo Circulante (PC), Passivo Não Circulante (PNC) e Patrimônio Líquido (PL).
                • Depreciação: alocação sistemática do valor depreciável (Custo de Aquisição menos Valor Residual) de um bem tangível do Ativo Imobilizado ao longo de sua vida útil econômica:
                  Quota Anual = (Custo_Aquisição - Valor_Residual) / Vida_Útil
                  Valor Contábil Líquido = Custo_Aquisição - Depreciação_Acumulada (conta retificadora do Imobilizado, de natureza credora).
            """.trimIndent(),
            practicalApplication = """
                Quando um terminal da Transpetro presta serviço de tancagem em dezembro (R$ 200.000,00) com faturamento para recebimento em janeiro do ano seguinte, pelo Regime de Competência a Receita de Serviços e o direito em Clientes são reconhecidos imediatamente em dezembro (Fato Modificativo Aumentativo). Quando o cliente paga em janeiro sem encargos, ocorre apenas um Fato Permutativo (Débito em Bancos e Crédito em Clientes).
            """.trimIndent(),
            summary = """
                • Regime de Competência: reconhece pelo fato gerador (independente do financeiro).
                • Fato Permutativo: não altera o PL (troca entre Ativo e/ou Passivo).
                • Fato Modificativo: altera diretamente o PL (receita ou despesa isolada).
                • Fato Misto: envolve permuta entre Ativo/Passivo + alteração simultânea do PL (juros, descontos, lucro na venda).
                • Depreciação Linear: (Custo - Valor Residual) / Vida Útil.
            """.trimIndent(),
            formulaName = "Depreciação Linear, Valor Contábil e Mutação do PL",
            formulaSyntax = "Dep_Anual = (Custo - V_Residual) / Vida_Útil   |   V_Contábil = Custo - Dep_Acumulada",
            formulaTerms = listOf(
                "Custo = Valor de aquisição e instalação do ativo imobilizado",
                "V_Residual = Valor estimado de realização ao final da vida útil",
                "Vida_Útil = Período estimado de utilização econômica pela empresa",
                "V_Contábil = Valor líquido pelo qual o ativo figura no Balanço Patrimonial"
            ),
            formulaWhenToUse = "Aplicar no cálculo da despesa de depreciação, do valor contábil líquido de imobilizados e na classificação de fatos contábeis.",
            exampleTitle = "Cálculo de Depreciação e Classificação de Fato Contábil",
            exampleProblem = "Uma máquina industrial foi adquirida por R$ 120.000,00, com valor residual estimado em R$ 20.000,00 e vida útil econômica de 10 anos. Pelo método linear, qual é a despesa mensal de depreciação, qual o valor contábil líquido após 3 anos de uso e como se classifica o fato contábil do reconhecimento da depreciação?",
            exampleSteps = listOf(
                "Calcular a base depreciável (Valor Depreciável): 120.000 - 20.000 = R$ 100.000,00.",
                "Apurar a quota anual de depreciação: 100.000 / 10 anos = R$ 10.000,00 por ano (ou R$ 833,33 por mês).",
                "Calcular a Depreciação Acumulada após 3 anos: 3 × 10.000 = R$ 30.000,00.",
                "Determinar o Valor Contábil Líquido após 3 anos: 120.000 - 30.000 = R$ 90.000,00.",
                "Classificar o fato contábil: Débito em Despesa de Depreciação (reduz PL) e Crédito em Depreciação Acumulada (reduz Ativo) → Fato Contábil Modificativo Diminutivo."
            ),
            exampleAnswer = "Depreciação anual de R$ 10.000,00 (R$ 833,33/mês), Valor Contábil Líquido de R$ 90.000,00 após 3 anos e Fato Modificativo Diminutivo.",
            goldTip = "Pagar uma dívida com DESCONTO obtido é um Fato Misto Aumentativo (sai menos caixa do que o valor do passivo baixado, gerando receita financeira); pagar uma dívida com JUROS de mora é um Fato Misto Diminutivo!"
        )

        "tp_fin_m3_a4" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m3_a4",
            intro = "A análise conjunta do Balanço Patrimonial (ordem decrescente de liquidez e exigibilidade) e da Demonstração do Resultado do Exercício (DRE — art. 187 da Lei nº 6.404/1976) é um dos temas mais cobrados pela Fundação Cesgranrio. O candidato deve saber exatamente como cada conta é ordenada no Balanço e como se apuram a Receita Líquida, o Lucro Bruto, o Resultado Operacional e o Lucro Líquido na DRE.",
            theory = """
                1. Estrutura Estática do Balanço Patrimonial (Lei nº 6.404/1976):
                • Ativo (Aplicações — Ordem decrescente de grau de liquidez):
                  1. Ativo Circulante (Disponibilidades, Clientes, Estoques, Despesas do Exercício Seguinte).
                  2. Ativo Não Circulante: (a) Realizável a Longo Prazo; (b) Investimentos; (c) Imobilizado; (d) Intangível.
                • Passivo e PL (Origens — Ordem decrescente de grau de exigibilidade):
                  1. Passivo Circulante (obrigações de curto prazo).
                  2. Passivo Não Circulante (obrigações de longo prazo).
                  3. Patrimônio Líquido (Capital Social, Reservas de Capital, Ajustes de Avaliação Patrimonial, Reservas de Lucros, Ações em Tesouraria [-], Prejuízos Acumulados [-]).

                2. Estrutura Vertical da Demonstração do Resultado do Exercício (DRE):
                A DRE é uma demonstração dinâmica (elaborada pelo Regime de Competência) que confronta receitas, custos e despesas do período:
                  (+) Receita Bruta de Vendas e/ou Serviços
                  (-) Deduções da Receita Bruta: Vendas Canceladas (Devoluções), Abatimentos, Descontos Incondicionais (Comerciais) Concedidos e Tributos Incidentes sobre Vendas (ICMS, ISS, PIS, COFINS)
                  (=) RECEITA OPERACIONAL LÍQUIDA (ROL)
                  (-) Custo das Mercadorias Vendidas (CMV), dos Produtos Vendidos (CPV) ou dos Serviços Prestados (CSP)
                  (=) LUCRO BRUTO (Resultado Operacional Bruto)
                  (-) Despesas Operacionais: Despesas Comerciais/Vendas, Despesas Gerais e Administrativas (incluindo depreciação administrativa) e Outras Receitas/Despesas Operacionais
                  (=) Resultado Operacional Antes do Resultado Financeiro (EBIT / LAJIR)
                  (+/-) Resultado Financeiro Líquido (Receitas Financeiras - Despesas Financeiras, incluindo descontos financeiros/condicionais)
                  (=) Resultado Antes dos Tributos sobre o Lucro (LAIR)
                  (-) Provisão para IRPJ e CSLL
                  (-) Participações Estatutárias (Debêntures, Empregados, Administradores, Partes Beneficiárias)
                  (=) LUCRO OU PREJUÍZO LÍQUIDO DO EXERCÍCIO

                3. Apuração do Custo das Mercadorias Vendidas (CMV):
                • CMV = Estoque Inicial (EI) + Compras Líquidas (CL) - Estoque Final (EF).
            """.trimIndent(),
            practicalApplication = """
                Na controladoria da Transpetro, ao elaborar os relatórios trimestrais, os tributos incidentes sobre o faturamento de serviços logísticos (ISS, PIS e COFINS) são deduzidos da Receita Bruta para encontrar a Receita Líquida. Em seguida, subtraem-se os Custos dos Serviços Prestados (operação e manutenção de dutos e navios) para apurar o Lucro Bruto, separando-os das Despesas Administrativas da sede corporativa.
            """.trimIndent(),
            summary = """
                • Balanço Patrimonial: Ativo em ordem decrescente de liquidez; Passivo em ordem decrescente de exigibilidade.
                • Receita Líquida = Receita Bruta - (Devoluções + Abatimentos + Descontos Incondicionais + Tributos sobre Vendas).
                • Lucro Bruto = Receita Líquida - CMV (onde CMV = EI + Compras Líquidas - EF).
                • Descontos Incondicionais (na nota fiscal) deduzem a Receita Bruta; Descontos Condicionais (financeiros, por pagamento antecipado) são Despesas Financeiras!
            """.trimIndent(),
            formulaName = "Sequência de Apuração da DRE e Fórmula do CMV",
            formulaSyntax = "ROL = RB - Deduções   |   LB = ROL - CMV   |   CMV = EI + CL - EF",
            formulaTerms = listOf(
                "RB e ROL = Receita Bruta e Receita Operacional Líquida",
                "LB = Lucro Bruto (Resultado Operacional Bruto)",
                "CMV = Custo das Mercadorias Vendidas (ou CSP para Serviços Prestados)",
                "EI, CL, EF = Estoque Inicial, Compras Líquidas e Estoque Final"
            ),
            formulaWhenToUse = "Aplicar sempre que a questão solicitar o cálculo da Receita Líquida, do CMV, do Lucro Bruto ou do Lucro Líquido do Exercício.",
            exampleTitle = "Apuração do Lucro Bruto e do Lucro Líquido na DRE",
            exampleProblem = "Determinada companhia apresentou os seguintes dados no exercício: Receita Bruta de Vendas = R$ 500.000; Impostos sobre Vendas = R$ 80.000; Devoluções de Vendas = R$ 20.000; Descontos Incondicionais Concedidos = R$ 10.000; CMV = R$ 210.000; Despesas Administrativas e Comerciais = R$ 90.000; Despesas Financeiras Líquidas = R$ 15.000; IR/CSLL = R$ 25.000. Calcule a Receita Líquida, o Lucro Bruto e o Lucro Líquido.",
            exampleSteps = listOf(
                "Somar as Deduções da Receita Bruta: Impostos (80.000) + Devoluções (20.000) + Descontos Incondicionais (10.000) = R$ 110.000,00.",
                "Calcular a Receita Operacional Líquida (ROL): 500.000 - 110.000 = R$ 390.000,00.",
                "Apurar o Lucro Bruto (ROL - CMV): 390.000 - 210.000 = R$ 180.000,00.",
                "Deduzir as Despesas Operacionais (90.000) e Financeiras (15.000) para achar o LAIR: 180.000 - 90.000 - 15.000 = R$ 75.000,00.",
                "Subtrair o IR/CSLL (25.000) para obter o Lucro Líquido: 75.000 - 25.000 = R$ 50.000,00."
            ),
            exampleAnswer = "Receita Líquida = R$ 390.000,00; Lucro Bruto = R$ 180.000,00; Lucro Líquido = R$ 50.000,00.",
            goldTip = "Pegadinha favorita da Cesgranrio na DRE: 'Desconto Financeiro Concedido' (condicional) NÃO entra nas deduções da Receita Bruta e não reduz o Lucro Bruto; ele entra mais abaixo, como Despesa Financeira!"
        )

        "tp_fin_m3_a5" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m3_a5",
            intro = "O aprofundamento na Demonstração do Resultado do Exercício (DRE) em conjunto com o confronto entre Regime de Competência e Regime de Caixa e a classificação dos Fatos Contábeis permite resolver as questões integradas da Cesgranrio, nas quais o candidato precisa apurar simultaneamente o resultado econômico (competência) e o efeito financeiro (caixa) de diversas transações.",
            theory = """
                1. Confronto Prático: Resultado pelo Regime de Competência vs. Regime de Caixa:
                • No Regime de Competência:
                  - Receitas = serviços prestados ou vendas entregues no período (ainda que a prazo).
                  - Despesas = gastos incorridos/consumidos no período (ainda que pagos no futuro ou pagos antecipadamente em período anterior).
                  - Recebimento de adiantamento de clientes NÃO é receita no regime de competência (gera obrigação no Passivo — Receita Antecipada).
                  - Pagamento antecipado de seguro ou aluguel NÃO é despesa imediata no regime de competência (gera direito no Ativo — Despesa Antecipada, apropriada mês a mês pro rata tempore).
                • No Regime de Caixa:
                  - Computam-se estritamente os recebimentos (inclusive adiantamentos recebidos) e os pagamentos efetivos (inclusive pagamentos antecipados).

                2. Integração entre DRE e Fatos Contábeis:
                • Venda de mercadorias envolve dois lançamentos simultâneos:
                  1º Reconhecimento da Receita de Venda (Fato Modificativo Aumentativo: Débito em Caixa/Clientes e Crédito em Receita de Vendas).
                  2º Baixa do Estoque pelo Custo (Fato Modificativo Diminutivo: Débito em CMV e Crédito em Estoques).
                  No conjunto, a venda com lucro ou prejuízo caracteriza um Fato Misto!

                3. Despesas Antecipadas e Receitas Diferidas:
                • A contratação de apólice de seguro anual à vista é inicialmente um Fato Permutativo (Troca Caixa por Seguros a Vencer no Ativo Circulante). Ao final de cada mês transcorrido, 1/12 do valor é apropriado como Despesa de Seguros na DRE (Fato Modificativo Diminutivo).
            """.trimIndent(),
            practicalApplication = """
                Se a Transpetro contrata e paga à vista, em 1º de outubro, um prêmio de seguro operacional de R$ 120.000,00 com vigência de 12 meses (R$ 10.000,00/mês), o desembolso no Fluxo de Caixa de outubro é de R$ 120.000,00. Contudo, na DRE do exercício encerrado em 31 de dezembro (3 meses de vigência: outubro, novembro e dezembro), a Despesa de Seguros reconhecida por competência será de apenas R$ 30.000,00, permanecendo R$ 90.000,00 no Ativo Circulante como Despesas do Exercício Seguinte.
            """.trimIndent(),
            summary = """
                • Competência: fato gerador (entrega do bem/serviço ou consumo do recurso).
                • Caixa: efetivo ingresso ou desembolso financeiro.
                • Despesa paga antecipadamente = Ativo Circulante (apropriada ao resultado conforme o tempo passa).
                • Adiantamento recebido de cliente = Passivo Circulante (vira receita apenas quando o serviço/bem é entregue).
            """.trimIndent(),
            formulaName = "Apuração do Resultado por Competência vs. Caixa",
            formulaSyntax = "Resultado_Competência = Receitas_Geradas - Despesas_Incorridas   |   Resultado_Caixa = Recebimentos - Pagamentos",
            formulaTerms = listOf(
                "Receitas_Geradas = Vendas e serviços efetivamente realizados no período (a vista ou a prazo)",
                "Despesas_Incorridas = Custos e despesas cujo fato gerador ocorreu no período",
                "Recebimentos e Pagamentos = Entradas e saídas efetivas de caixa no período"
            ),
            formulaWhenToUse = "Utilizar nas questões da Cesgranrio que apresentam uma lista de eventos mensais e pedem a comparação entre o lucro pelo Regime de Competência e pelo Regime de Caixa.",
            exampleTitle = "Apuração Comparativa: Competência versus Caixa",
            exampleProblem = "Em determinado mês, uma empresa realizou as seguintes operações: (1) Prestou serviços de R$ 80.000, recebendo 60% à vista e o restante para o mês seguinte; (2) Recebeu adiantamento de cliente de R$ 15.000 para serviço a ser prestado no trimestre seguinte; (3) Incorreu em despesas de salários de R$ 30.000, que serão pagas no 5º dia útil do mês seguinte; (4) Pagou à vista R$ 10.000 de aluguel referente ao próprio mês. Calcule o resultado pelo Regime de Competência e pelo Regime de Caixa.",
            exampleSteps = listOf(
                "Apurar o Resultado pelo Regime de Competência: Receita de serviços prestados (+80.000) - Despesa de salários incorrida (-30.000) - Despesa de aluguel do mês (-10.000) = +R$ 40.000,00 (o adiantamento de 15.000 é Passivo, não é receita).",
                "Apurar as entradas no Regime de Caixa: 60% de 80.000 (+48.000) + Adiantamento recebido (+15.000) = R$ 63.000,00.",
                "Apurar as saídas no Regime de Caixa: apenas o aluguel pago à vista (-10.000), pois os salários serão pagos no mês seguinte.",
                "Calcular o Resultado pelo Regime de Caixa: 63.000 - 10.000 = +R$ 53.000,00."
            ),
            exampleAnswer = "Resultado pelo Regime de Competência = Lucro de R$ 40.000,00; Resultado pelo Regime de Caixa = Superávit de R$ 53.000,00.",
            goldTip = "Em questões comparativas Competência × Caixa da Cesgranrio, monte duas colunas rápidas na prova: na coluna Competência ignore quando o dinheiro entrou/saiu e olhe só o mês a que o serviço/despesa pertence; na coluna Caixa olhe apenas o que entrou ou saiu da conta bancária naquele mês!"
        )

        "tp_fin_m3_a6" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m3_a6",
            intro = "A Depreciação, a Amortização e a Exaustão representam o reconhecimento contábil da perda de capacidade de geração de benefícios econômicos futuros dos ativos não circulantes ao longo do tempo. Na Transpetro, cujo patrimônio é intensivo em dutos, navios, tanques e sistemas de controle (Imobilizado e Intangível), esse tema possui altíssima relevância prática e incidência em provas da Cesgranrio.",
            theory = """
                1. Distinção Conceitual: Depreciação, Amortização e Exaustão (Lei nº 6.404/1976 e CPC 27 / CPC 04):
                • Depreciação: incide sobre bens corpóreos (tangíveis) do Ativo Imobilizado (máquinas, equipamentos, veículos, edifícios, navios-tanque, dutovias) pelo desgaste físico pelo uso, ação da natureza ou obsolescência tecnológica.
                • Amortização: incide sobre direitos e bens incorpóreos do Ativo Intangível com vida útil definida (softwares, licenças de uso, patentes, direitos de concessão).
                • Exaustão: incide sobre recursos naturais esgotáveis (reservas minerais, petrolíferas e florestais) explorados economicamente.

                2. Regras de Cálculo da Depreciação (Método Linear / Quotas Constantes):
                • Início da Depreciação: começa a ser calculada a partir do momento em que o ativo está DISPONÍVEL PARA USO (instalado no local e nas condições necessárias para operar), mesmo que ainda não esteja operando em capacidade total.
                • Ativos que NÃO sofrem depreciação: Terrenos (salvo raras exceções de exploração), obras de arte e bens ainda em construção (Imobilizado em Andamento).
                • Valor Depreciável = Custo de Aquisição - Valor Residual.
                • Taxa Anual de Depreciação = 100% / Vida Útil (em anos).
                • Quota Anual = Valor Depreciável × Taxa Anual = (Custo - Valor Residual) / Vida Útil.
                • Lançamento Contábil:
                  Débito: Despesa ou Custo de Depreciação (Resultado — DRE ou Custo de Produção)
                  Crédito: Depreciação Acumulada (Conta Retificadora do Ativo Imobilizado, de natureza credora).
            """.trimIndent(),
            practicalApplication = """
                Quando a Transpetro coloca em operação um novo sistema de bombeamento adquirido por R$ 2.400.000,00, com valor residual estimado de R$ 240.000,00 e vida útil de 15 anos, a contabilidade passa a apropriar mensalmente uma quota de depreciação de (2.400.000 - 240.000) / 180 meses = R$ 12.000,00/mês, atualizando o valor contábil líquido do equipamento no Balanço Patrimonial.
            """.trimIndent(),
            summary = """
                • Bens Tangíveis (Imobilizado) → Depreciação.
                • Bens Intangíveis (Marcas, Softwares, Patentes) → Amortização.
                • Recursos Naturais (Jazidas, Florestas) → Exaustão.
                • Início: quando o bem está disponível para uso (pronto para operar).
                • Depreciação Acumulada é conta retificadora do Ativo (natureza credora).
            """.trimIndent(),
            formulaName = "Quota de Depreciação Linear e Valor Contábil Líquido",
            formulaSyntax = "Quota_Mensal = (Custo_Aquisição - Valor_Residual) / Meses_Vida_Útil   |   VCL = Custo - Dep_Acumulada",
            formulaTerms = listOf(
                "Custo_Aquisição = Preço de compra acrescido de impostos não recuperáveis, frete e instalação",
                "Valor_Residual = Valor estimado de venda do bem ao final de sua vida útil",
                "Meses_Vida_Útil = Tempo total estimado de uso econômico expresso em meses",
                "VCL = Valor Contábil Líquido apresentado no Ativo Imobilizado"
            ),
            formulaWhenToUse = "Utilizar no cálculo da quota mensal/anual de depreciação ou amortização e na apuração de ganho ou perda de capital na venda de imobilizado.",
            exampleTitle = "Depreciação Acumulada com Valor Residual e Cálculo Proporcional",
            exampleProblem = "Em 01/04/2025, uma empresa adquiriu e colocou imediatamente em uso um equipamento por R$ 90.000,00, estimando vida útil de 5 anos (60 meses) e valor residual de 10% do custo de aquisição. Qual o saldo da conta Depreciação Acumulada e o Valor Contábil Líquido desse bem em 31/12/2025?",
            exampleSteps = listOf(
                "Determinar o Valor Residual: 10% de R$ 90.000,00 = R$ 9.000,00.",
                "Calcular o Valor Depreciável: 90.000 - 9.000 = R$ 81.000,00.",
                "Apurar a quota mensal de depreciação: 81.000 / 60 meses = R$ 1.350,00 por mês.",
                "Contar os meses de utilização em 2025 (de 01/04 a 31/12): 9 meses (abril a dezembro).",
                "Calcular a Depreciação Acumulada em 31/12/2025: 9 × 1.350 = R$ 12.150,00; Valor Contábil Líquido = 90.000 - 12.150 = R$ 77.850,00."
            ),
            exampleAnswer = "Depreciação Acumulada de R$ 12.150,00 e Valor Contábil Líquido de R$ 77.850,00.",
            goldTip = "Atenção à data de aquisição na prova da Cesgranrio! Se o bem ficou pronto para uso em 1º de abril, no primeiro ano você deve depreciar apenas 9/12 avos da quota anual (abril a dezembro), e não o ano inteiro!"
        )

        // =====================================================================
        // MÓDULO 4: CUSTOS E CONTROLE ORÇAMENTÁRIO
        // =====================================================================
        "tp_fin_m4_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m4_a1",
            intro = "No Módulo de Custos e Controle Orçamentário, o domínio da terminologia contábil de custos (Gasto, Investimento, Custo, Despesa e Perda), da classificação dos custos (Fixos, Variáveis, Diretos e Indiretos) e dos modelos de Planejamento Orçamentário (Operacional, de Capital, Base Zero e Matricial) é fundamental para a atuação administrativa na Transpetro e recorrente nas provas da Cesgranrio.",
            theory = """
                1. Terminologia Fundamental da Contabilidade de Custos:
                • Gasto: sacrifício financeiro com que a entidade arca para a obtenção de um produto ou serviço (conceito amplo que engloba investimento, custo e despesa).
                • Investimento: gasto ativado em função de sua vida útil ou de benefícios atribuíveis a futuros períodos (ex.: compra de matéria-prima enquanto está estocada; compra de máquina).
                • Custo: gasto relativo a bem ou serviço utilizado na PRODUÇÃO de outros bens ou na PRESTAÇÃO DE SERVIÇOS (ex.: energia elétrica das bombas do oleoduto, mão de obra dos operadores de terminal, manutenção industrial).
                • Despesa: bem ou serviço consumido direta ou indiretamente para a obtenção de receitas nas áreas administrativa, comercial e financeira (ex.: salários do RH e jurídico, publicidade, comissão de vendedores).
                • Perda: consumo anormal e involuntário de bens ou serviços (ex.: incêndio, vazamento acidental, deterioração por enchente), lançado diretamente contra o resultado do período.

                2. Classificação dos Custos:
                • Quanto à identificação com o objeto de custeio:
                  - Custos Diretos: podem ser diretamente apropriados aos produtos/serviços por medida objetiva de consumo, sem necessidade de rateio (ex.: matéria-prima direta, mão de obra direta).
                  - Custos Indiretos (CIF): necessitam de critérios de rateio ou estimativa para serem alocados aos diversos produtos/serviços (ex.: aluguel do galpão industrial, supervisão geral da planta, depreciação compartilhada).
                • Quanto ao comportamento em relação ao volume de atividade/produção:
                  - Custos Fixos (CF): o valor TOTAL permanece constante no curto prazo dentro da capacidade instalada, independentemente do volume produzido; porém, o custo fixo UNITÁRIO (CF / Q) é decrescente à medida que a produção aumenta.
                  - Custos Variáveis (CV): o valor TOTAL varia proporcionalmente ao volume produzido/movimentado; porém, o custo variável UNITÁRIO (CVu) é constante por unidade.

                3. Planejamento e Controle Orçamentário (Operacional, Capital, OBZ e Matricial):
                • Orçamento Operacional: projeta receitas, custos de produção e despesas operacionais do ciclo anual.
                • Orçamento de Capital (Investimentos): contempla projetos de expansão, aquisição de imobilizado e grandes paradas de manutenção plurianuais.
                • Orçamento Base Zero (OBZ): rejeita a simples extrapolação histórica (orçamento incremental) e exige que cada gestor justifique do zero todas as despesas e pacotes de decisão a cada novo ciclo orçamentário.
                • Orçamento Matricial: cruza pacotes de gastos (entidades/naturezas de despesa) com unidades de negócio ou centros de custos, sob dupla responsabilidade gerencial.
            """.trimIndent(),
            practicalApplication = """
                Na operação de um terminal aquaviário da Transpetro, o gasto com vigilância patrimonial fixa do terminal e a depreciação linear dos tanques são Custos Fixos (independem do volume de m³ bombeados no mês), enquanto o consumo de energia elétrica das bombas de recalque e de aditivos químicos varia conforme o volume movimentado (Custos Variáveis). Já na elaboração do orçamento anual, a companhia utiliza o Orçamento Base Zero (OBZ) para revisar contratos de apoio administrativo sem perpetuar ineficiências de anos anteriores.
            """.trimIndent(),
            summary = """
                • Custo = gasto na produção/operação; Despesa = gasto administrativo/comercial/financeiro.
                • Direto = apropriação objetiva sem rateio; Indireto = exige rateio.
                • Custo Fixo: total constante, unitário decrescente com o aumento da produção (CFu = CF / Q).
                • Custo Variável: total proporcional à quantidade, unitário constante.
                • Orçamento Base Zero (OBZ): exige justificativa integral de cada gasto a partir do zero, sem reajuste automático do histórico.
            """.trimIndent(),
            formulaName = "Custo Total, Custo Unitário e Comportamento de Custos Fixos e Variáveis",
            formulaSyntax = "CT = CF + (CV_u × Q)   |   C_unitário = (CF / Q) + CV_u",
            formulaTerms = listOf(
                "CT = Custo Total de produção ou operação no período",
                "CF = Custo Fixo Total do período (independe de Q dentro da capacidade)",
                "CV_u = Custo Variável Unitário (constante por unidade)",
                "Q = Quantidade produzida ou volume operacional movimentado"
            ),
            formulaWhenToUse = "Aplicar para calcular o custo total ou unitário em diferentes níveis de atividade e para distinguir custos fixos, variáveis, diretos e indiretos.",
            exampleTitle = "Cálculo do Custo Total e Redução do Custo Fixo Unitário",
            exampleProblem = "Um terminal logístico possui Custos Fixos mensais de R$ 240.000,00 e Custo Variável Unitário de R$ 15,00 por tonelada movimentada. Se o volume movimentado passar de 6.000 toneladas no Mês 1 para 8.000 toneladas no Mês 2, qual será o Custo Total e o Custo Médio Unitário por tonelada em cada mês?",
            exampleSteps = listOf(
                "Mês 1 (Q = 6.000 t): Custo Fixo Unitário = 240.000 / 6.000 = R$ 40,00/t; Custo Unitário Total = 40 + 15 = R$ 55,00/t; Custo Total = 240.000 + (15 × 6.000) = R$ 330.000,00.",
                "Mês 2 (Q = 8.000 t): Custo Fixo Unitário = 240.000 / 8.000 = R$ 30,00/t; Custo Unitário Total = 30 + 15 = R$ 45,00/t; Custo Total = 240.000 + (15 × 8.000) = R$ 360.000,00.",
                "Observar a diluição do custo fixo: o aumento de volume elevou o Custo Total (de R$ 330 mil para R$ 360 mil), mas reduziu o Custo Unitário por tonelada de R$ 55,00 para R$ 45,00."
            ),
            exampleAnswer = "Mês 1: Custo Total = R$ 330.000,00 e Custo Unitário = R$ 55,00/t; Mês 2: Custo Total = R$ 360.000,00 e Custo Unitário = R$ 45,00/t.",
            goldTip = "Cuidado com a afirmação clássica de prova: 'O custo fixo é fixo por unidade'. FALSO! O Custo Fixo é constante no TOTAL, mas é VARIÁVEL (decrescente) por unidade produzida. Já o Custo Variável é variável no total, mas FIXO por unidade!"
        )

        "tp_fin_m4_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_fin_m4_a2",
            intro = "O fechamento do módulo aborda a análise de Custo-Volume-Lucro (Margem de Contribuição e Ponto de Equilíbrio), os Sistemas de Custeio (Absorção vs. Variável) e o Controle Orçamentário (acompanhamento entre Orçado e Realizado). A Cesgranrio cobra tanto o cálculo do Ponto de Equilíbrio Contábil, Econômico e Financeiro quanto a análise de variações orçamentárias.",
            theory = """
                1. Margem de Contribuição Unitária (MCu) e Total (MCT):
                Representa quanto cada unidade vendida ou serviço prestado contribui para cobrir os custos e despesas fixos e gerar lucro operacional:
                • MCu = Preço de Venda Unitário (PVu) - (Custos Variáveis Unitários + Despesas Variáveis Unitárias)
                • Índice de Margem de Contribuição (IMC): IMC = MCu / PVu.

                2. Ponto de Equilíbrio (Break-even Point):
                Nível de atividade (em quantidade Q ou receita) em que a Receita Total iguala os Custos e Despesas Totais:
                • Ponto de Equilíbrio Contábil (PEC — Lucro Contábil = 0):
                  PEC_q = (Custos Fixos + Despesas Fixas) / MCu
                • Ponto de Equilíbrio Econômico (PEE — garante um Lucro Meta / Custo de Oportunidade L_meta):
                  PEE_q = (Custos Fixos + Despesas Fixas + L_meta) / MCu
                • Ponto de Equilíbrio Financeiro (PEF — exclui gastos fixos não desembolsáveis, como a Depreciação):
                  PEF_q = (Custos Fixos + Despesas Fixas - Depreciação) / MCu
                  Nota: Vale sempre a relação PEF < PEC < PEE!

                3. Sistemas de Custeio: Absorção vs. Variável (Direto):
                • Custeio por Absorção: apropria TODOS os custos de produção (fixos e variáveis, diretos e indiretos) aos produtos. É o único aceito pela legislação societária e fiscal brasileira (CPC 16 e Imposto de Renda).
                • Custeio Variável (Marginal): aloca aos produtos apenas os custos variáveis, tratando os custos fixos integralmente como despesa do período. Uso exclusivo gerencial.

                4. Controle Orçamentário (Orçado vs. Realizado):
                • Variação (Desvio) Orçamentária = Valor Realizado - Valor Orçado.
                • Desvio Favorável: receita realizada maior que a orçada, ou custo/despesa realizado menor que o orçado.
                • Desvio Desfavorável (Estouro): receita realizada menor que a orçada, ou custo/despesa realizado maior que o orçado.
                • Análise de Variação de Volume vs. Variação de Preço (Orçamento Flexível): separa o desvio causado pelo aumento do nível de atividade daquele causado pela ineficiência ou encarecimento unitário.
            """.trimIndent(),
            practicalApplication = """
                No controle orçamentário mensal de uma gerência da Transpetro, o orçamento previa gasto de R$ 400.000,00 com manutenção preventiva (80 intervenções a R$ 5.000,00 cada), mas o realizado foi de R$ 432.000,00 para 90 intervenções (R$ 4.800,00 cada). Embora o desvio bruto em relação ao orçamento estático pareça desfavorável em R$ 32.000,00, a análise pelo Orçamento Flexível mostra que para 90 intervenções o orçamento ajustado seria R$ 450.000,00, revelando economia de eficiência (variação favorável de preço) de R$ 18.000,00.
            """.trimIndent(),
            summary = """
                • Margem de Contribuição Unitária: MCu = PVu - (CVu + DVu).
                • PEC = (CF + DF) / MCu; PEE = (CF + DF + Lucro_Meta) / MCu; PEF = (CF + DF - Depreciação) / MCu.
                • Custeio por Absorção é o obrigatório por lei e pelo CPC 16; Custeio Variável é gerencial.
                • Controle Orçamentário confronta Orçado × Realizado para identificar desvios favoráveis e desfavoráveis e acionar medidas corretivas.
            """.trimIndent(),
            formulaName = "Margem de Contribuição, Ponto de Equilíbrio e Desvio Orçamentário",
            formulaSyntax = "MC_u = PV_u - (CV_u + DV_u)   |   PEC = (CF + DF) / MC_u   |   Desvio = Realizado - Orçado",
            formulaTerms = listOf(
                "MC_u = Margem de Contribuição Unitária",
                "PV_u, CV_u, DV_u = Preço de Venda, Custo Variável e Despesa Variável por unidade",
                "CF e DF = Custos Fixos Totais e Despesas Fixas Totais do período",
                "PEC = Ponto de Equilíbrio Contábil em unidades físicas"
            ),
            formulaWhenToUse = "Utilizar para calcular a quantidade ou receita mínima para não ter prejuízo (PEC), atingir meta de lucro (PEE), equilibrar o caixa (PEF) ou analisar variações orçamentárias.",
            exampleTitle = "Cálculo dos Pontos de Equilíbrio Contábil, Econômico e Financeiro",
            exampleProblem = "Uma empresa presta um serviço padronizado cobrando R$ 120,00 por atendimento. Os custos e despesas variáveis somam R$ 70,00 por atendimento. Os custos e despesas fixos mensais totalizam R$ 150.000,00 (dos quais R$ 30.000,00 referem-se à depreciação mensal de equipamentos). Se a empresa deseja obter um lucro operacional meta de R$ 40.000,00 no mês, calcule o PEC, o PEE e o PEF em quantidade de atendimentos.",
            exampleSteps = listOf(
                "Calcular a Margem de Contribuição Unitária (MCu): 120,00 - 70,00 = R$ 50,00 por atendimento.",
                "Calcular o Ponto de Equilíbrio Contábil (PEC): 150.000 / 50 = 3.000 atendimentos.",
                "Calcular o Ponto de Equilíbrio Econômico (PEE): (150.000 + 40.000) / 50 = 190.000 / 50 = 3.800 atendimentos.",
                "Calcular o Ponto de Equilíbrio Financeiro (PEF): (150.000 - 30.000) / 50 = 120.000 / 50 = 2.400 atendimentos."
            ),
            exampleAnswer = "PEC = 3.000 atendimentos; PEE = 3.800 atendimentos; PEF = 2.400 atendimentos.",
            goldTip = "Para achar o Ponto de Equilíbrio em VALOR DE RECEITA (R$), basta multiplicar a quantidade do PEC pelo Preço de Venda Unitário, ou dividir os Custos e Despesas Fixos pelo Índice de Margem de Contribuição (MCu / PVu)!"
        )

        else -> null
    }


    fun getExercises(lessonId: String): List<MathExercise> {
        return when (lessonId) {
            "tp_fin_m1_a1" -> exercisesM1A1()
            "tp_fin_m1_a2" -> exercisesM1A2()
            "tp_fin_m1_a3" -> exercisesM1A3()
            "tp_fin_m1_a4" -> exercisesM1A4()
            "tp_fin_m2_a1" -> exercisesM2A1()
            "tp_fin_m2_a2" -> exercisesM2A2()
            "tp_fin_m2_a3" -> exercisesM2A3()
            "tp_fin_m3_a1" -> exercisesM3A1()
            "tp_fin_m3_a2" -> exercisesM3A2()
            "tp_fin_m3_a3" -> exercisesM3A3()
            "tp_fin_m3_a4" -> exercisesM3A4()
            "tp_fin_m3_a5" -> exercisesM3A5()
            "tp_fin_m3_a6" -> exercisesM3A6()
            "tp_fin_m4_a1" -> exercisesM4A1()
            "tp_fin_m4_a2" -> exercisesM4A2()
            else -> emptyList()
        }
    }

    private fun exercisesM1A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-01",
            lessonId = "tp_fin_m1_a1",
            statement = "Uma gerência financeira observou que a despesa mensal com combustíveis em um terminal era de R$ 80.000,00 e, após um ajuste tarifário e expansão de frotas, passou para R$ 96.000,00. Qual foi a taxa percentual de aumento observada?",
            options = listOf(
                "A) 16%",
                "B) 18%",
                "C) 20%",
                "D) 22%",
                "E) 25%"
            ),
            correctIndex = 2,
            steps = listOf(
                "Calcular a variação absoluta: 96.000 - 80.000 = R$ 16.000,00.",
                "Calcular a taxa relativa sobre a base inicial: 16.000 / 80.000 = 0,20.",
                "Multiplicar por 100 para obter o percentual: 0,20 * 100 = 20%."
            ),
            finalComment = "A taxa de aumento percentual em relação ao valor base de R$ 80.000,00 foi de exatamente 20%."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-02",
            lessonId = "tp_fin_m1_a1",
            statement = "Um lote de peças sobressalentes foi adquirido por R$ 500,00 e vendido com lucro de 25% calculado sobre o preço de venda. Qual foi o preço de venda desse lote?",
            options = listOf(
                "A) R$ 600,00",
                "B) R$ 625,00",
                "C) R$ 650,00",
                "D) R$ 666,67",
                "E) R$ 700,00"
            ),
            correctIndex = 3,
            steps = listOf(
                "Fórmula do preço de venda com margem sobre a venda: PV = Custo + Lucro.",
                "Como Lucro = 0,25 * PV, temos: PV = 500 + 0,25 * PV.",
                "Isolando PV: PV * (1 - 0,25) = 500 => 0,75 * PV = 500.",
                "Calculando: PV = 500 / 0,75 = R$ 666,67."
            ),
            finalComment = "Atenção: margem sobre o preço de venda difere do markup sobre o custo. Com 25% sobre a venda, PV = Custo / (1 - 0,25) = R$ 666,67."
        )
    )

    private fun exercisesM1A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-03",
            lessonId = "tp_fin_m1_a2",
            statement = "Um capital de R$ 40.000,00 foi aplicado a juros simples durante 9 meses à taxa de 1,5% ao mês. Qual é o valor do montante resgatado ao final do período?",
            options = listOf(
                "A) R$ 44.500,00",
                "B) R$ 45.400,00",
                "C) R$ 46.200,00",
                "D) R$ 47.000,00",
                "E) R$ 48.000,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "Cálculo dos juros simples: J = C * i * n.",
                "J = 40.000 * 0,015 * 9 = 40.000 * 0,135 = R$ 5.400,00.",
                "Cálculo do montante: M = C + J = 40.000 + 5.400 = R$ 45.400,00."
            ),
            finalComment = "Em juros simples, a taxa de 1,5% ao mês em 9 meses gera 13,5% de juros totais, resultando em R$ 45.400,00 de montante."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-04",
            lessonId = "tp_fin_m1_a2",
            statement = "No regime de juros simples, qual é a taxa anual proporcional correspondente a uma taxa mensal de 2,5% ao mês?",
            options = listOf(
                "A) 25%",
                "B) 28%",
                "C) 30%",
                "D) 32,5%",
                "E) 34%"
            ),
            correctIndex = 2,
            steps = listOf(
                "No regime simples, taxas proporcionais seguem proporção linear direta.",
                "Um ano possui 12 meses.",
                "Taxa anual = 2,5% * 12 = 30% ao ano."
            ),
            finalComment = "Em juros simples, a taxa proporcional anual é obtida pela multiplicação direta pelo número de meses (2,5% * 12 = 30% a.a.)."
        )
    )

    private fun exercisesM1A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-05",
            lessonId = "tp_fin_m1_a3",
            statement = "Uma aplicação de R$ 20.000,00 foi realizada a juros compostos com taxa de 10% ao ano, durante 2 anos. Qual o montante final obtido?",
            options = listOf(
                "A) R$ 24.000,00",
                "B) R$ 24.200,00",
                "C) R$ 24.400,00",
                "D) R$ 24.600,00",
                "E) R$ 25.000,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "Fórmula do montante em juros compostos: M = C * (1 + i)^n.",
                "M = 20.000 * (1 + 0,10)^2 = 20.000 * 1,10^2.",
                "1,10^2 = 1,21.",
                "M = 20.000 * 1,21 = R$ 24.200,00."
            ),
            finalComment = "Em juros compostos, os juros do segundo ano incidem sobre o capital já corrigido pelo primeiro ano: M = 20.000 * 1,21 = R$ 24.200,00."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-06",
            lessonId = "tp_fin_m1_a3",
            statement = "Em regime de juros compostos, se uma taxa é de 44% para um biênio (2 anos), qual é a taxa anual equivalente?",
            options = listOf(
                "A) 18%",
                "B) 20%",
                "C) 22%",
                "D) 24%",
                "E) 26%"
            ),
            correctIndex = 1,
            steps = listOf(
                "Relação de taxas equivalentes em juros compostos: (1 + i_ano)^2 = 1 + i_biênio.",
                "(1 + i_ano)^2 = 1 + 0,44 = 1,44.",
                "1 + i_ano = raiz(1,44) = 1,20.",
                "i_ano = 1,20 - 1 = 0,20 = 20% ao ano."
            ),
            finalComment = "A raiz quadrada de 1,44 é 1,20, o que equivale a uma taxa de 20% ao ano em juros compostos."
        )
    )

    private fun exercisesM1A4(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-07",
            lessonId = "tp_fin_m1_a4",
            statement = "Um título com valor nominal de R$ 10.000,00 é descontado 2 meses antes do vencimento a uma taxa de desconto simples comercial (por fora) de 3% ao mês. Qual o valor descontado recebido pela empresa?",
            options = listOf(
                "A) R$ 9.200,00",
                "B) R$ 9.400,00",
                "C) R$ 9.500,00",
                "D) R$ 9.600,00",
                "E) R$ 9.700,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "Desconto comercial simples: D = N * d * t.",
                "D = 10.000 * 0,03 * 2 = R$ 600,00.",
                "Valor atual comercial: V = N - D = 10.000 - 600 = R$ 9.400,00."
            ),
            finalComment = "No desconto comercial (por fora), o desconto incide diretamente sobre o valor nominal do título: 10.000 - 600 = R$ 9.400,00."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-08",
            lessonId = "tp_fin_m1_a4",
            statement = "Em relação aos sistemas de amortização de dívidas, qual é a principal diferença entre o Sistema Francês (Tabela Price) e o Sistema de Amortização Constante (SAC)?",
            options = listOf(
                "A) Na Price as amortizações são constantes e no SAC as parcelas são constantes.",
                "B) Na Price as prestações são constantes e no SAC as quotas de amortização são constantes com prestações decrescentes.",
                "C) No SAC os juros são crescentes ao longo do tempo.",
                "D) Na Price o saldo devedor cai linearmente a cada mês.",
                "E) Ambos possuem prestações e amortizações fixas durante todo o prazo."
            ),
            correctIndex = 1,
            steps = listOf(
                "No Sistema Francês (Price), a prestação total (juros + amortização) é fixa e constante em todo o período.",
                "No SAC (Sistema de Amortização Constante), a quota de amortização é fixa (A = Saldo / n) e os juros caem à medida que o saldo reduz, gerando prestações decrescentes."
            ),
            finalComment = "Tabela Price = Prestações iguais; SAC = Amortizações iguais e prestações decrescentes."
        )
    )

    private fun exercisesM2A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-09",
            lessonId = "tp_fin_m2_a1",
            statement = "Na Demonstração dos Fluxos de Caixa (DFC), a aquisição à vista de novos dutos e equipamentos para ampliação da capacidade de transporte deve ser classificada como fluxo de caixa de qual atividade?",
            options = listOf(
                "A) Atividades Operacionais",
                "B) Atividades de Investimento",
                "C) Atividades de Financiamento",
                "D) Atividades Patrimoniais Neutras",
                "E) Atividades Não Realizáveis"
            ),
            correctIndex = 1,
            steps = listOf(
                "A DFC classifica os fluxos em: Operacionais, de Investimento e de Financiamento.",
                "Gastos com aquisição ou venda de bens do Ativo Não Circulante (Imobilizado e Intangível) são classificados como Atividades de Investimento."
            ),
            finalComment = "A compra de bens de capital (imobilizado) é saída de caixa das Atividades de Investimento."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-10",
            lessonId = "tp_fin_m2_a1",
            statement = "No método indireto de elaboração do Fluxo de Caixa Operacional, a despesa de depreciação apurada na DRE deve ser:",
            options = listOf(
                "A) Subtraída do Lucro Líquido, pois representa saída financeira.",
                "B) Desconsiderada, pois não afeta o balanço.",
                "C) Adicionada ao Lucro Líquido, pois reduziu o lucro mas não consumiu caixa.",
                "D) Multiplicada pela taxa de imposto para ajuste.",
                "E) Transferida para o fluxo de financiamento."
            ),
            correctIndex = 2,
            steps = listOf(
                "A depreciação é uma despesa contábil não desembolsável (não gera saída de dinheiro).",
                "Como ela foi deduzida para apurar o Lucro Líquido na DRE, no método indireto ela é somada de volta para reconciliar o lucro com o caixa gerado."
            ),
            finalComment = "A depreciação é adicionada ao Lucro Líquido no método indireto por se tratar de despesa que não afeta as disponibilidades da empresa."
        )
    )

    private fun exercisesM2A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-11",
            lessonId = "tp_fin_m2_a2",
            statement = "Durante a conciliação bancária mensal de uma unidade, o saldo do extrato acusava R$ 50.000,00 e a conta Bancos na contabilidade acusava R$ 42.000,00. Identificou-se que cheques emitidos e entregues a prestadores de serviços no total de R$ 9.000,00 ainda não haviam sido compensados pelo banco e que uma tarifa de R$ 1.000,00 debitada pelo banco ainda não constava no razão. Qual é o saldo bancário real conciliado?",
            options = listOf(
                "A) R$ 40.000,00",
                "B) R$ 41.000,00",
                "C) R$ 42.000,00",
                "D) R$ 43.000,00",
                "E) R$ 50.000,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "Partindo do extrato: 50.000 - 9.000 (cheques a compensar) = R$ 41.000,00.",
                "Partindo da contabilidade: 42.000 - 1.000 (tarifa pendente de registro) = R$ 41.000,00.",
                "Ambos os caminhos coincidem em R$ 41.000,00."
            ),
            finalComment = "O saldo conciliado e ajustado das disponibilidades bancárias é de R$ 41.000,00."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-12",
            lessonId = "tp_fin_m2_a2",
            statement = "Uma fatura de serviços no valor de R$ 60.000,00 foi paga com 15 dias de atraso. O contrato previa multa moratória de 2% e juros simples de 1% ao mês (considerando mês de 30 dias). Qual foi o valor total pago?",
            options = listOf(
                "A) R$ 60.900,00",
                "B) R$ 61.200,00",
                "C) R$ 61.500,00",
                "D) R$ 61.800,00",
                "E) R$ 62.100,00"
            ),
            correctIndex = 2,
            steps = listOf(
                "Multa moratória de 2%: 60.000 * 0,02 = R$ 1.200,00.",
                "Juros de 1% ao mês por 15 dias (meio mês): 60.000 * 0,01 * (15/30) = R$ 300,00.",
                "Valor total = 60.000 + 1.200 + 300 = R$ 61.500,00."
            ),
            finalComment = "O valor pago engloba o principal (60.000) + multa fixa (1.200) + juros proporcionais (300) = R$ 61.500,00."
        )
    )

    private fun exercisesM2A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-13",
            lessonId = "tp_fin_m2_a3",
            statement = "Uma empresa apresenta Ativo Circulante de R$ 180.000,00, Passivo Circulante de R$ 110.000,00 e Ativo Não Circulante de R$ 320.000,00. O Capital Circulante Líquido (CCL) dessa empresa é de:",
            options = listOf(
                "A) R$ 50.000,00",
                "B) R$ 70.000,00",
                "C) R$ 90.000,00",
                "D) R$ 110.000,00",
                "E) R$ 210.000,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "Fórmula do Capital Circulante Líquido: CCL = Ativo Circulante - Passivo Circulante.",
                "CCL = 180.000 - 110.000 = R$ 70.000,00."
            ),
            finalComment = "O CCL representa a folga de recursos de curto prazo: AC - PC = 180.000 - 110.000 = R$ 70.000,00."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-14",
            lessonId = "tp_fin_m2_a3",
            statement = "Uma empresa possui Prazo Médio de Estocagem (PME) de 40 dias, Prazo Médio de Recebimento de Vendas (PMR) de 50 dias e Prazo Médio de Pagamento a Fornecedores (PMP) de 35 dias. Quais são, respectivamente, o Ciclo Operacional e o Ciclo Financeiro dessa empresa?",
            options = listOf(
                "A) 75 dias e 40 dias",
                "B) 90 dias e 55 dias",
                "C) 90 dias e 45 dias",
                "D) 85 dias e 50 dias",
                "E) 95 dias e 60 dias"
            ),
            correctIndex = 1,
            steps = listOf(
                "Ciclo Operacional (CO) = PME + PMR = 40 + 50 = 90 dias.",
                "Ciclo Financeiro (ou Ciclo de Caixa) = CO - PMP = 90 - 35 = 55 dias."
            ),
            finalComment = "Ciclo Operacional = 40 + 50 = 90 dias; Ciclo Financeiro = 90 - 35 = 55 dias."
        )
    )

    private fun exercisesM2A4(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-15",
            lessonId = "tp_fin_m2_a4",
            statement = "Um balanço apresenta Caixa e Equivalentes de R$ 30.000,00, Clientes de R$ 50.000,00, Estoques de R$ 40.000,00 e Passivo Circulante de R$ 80.000,00. O Índice de Liquidez Seca dessa organização é de:",
            options = listOf(
                "A) 0,80",
                "B) 1,00",
                "C) 1,20",
                "D) 1,50",
                "E) 1,60"
            ),
            correctIndex = 1,
            steps = listOf(
                "Ativo Circulante = 30.000 + 50.000 + 40.000 = R$ 120.000,00.",
                "Fórmula da Liquidez Seca = (Ativo Circulante - Estoques) / Passivo Circulante.",
                "Liquidez Seca = (120.000 - 40.000) / 80.000 = 80.000 / 80.000 = 1,00."
            ),
            finalComment = "A Liquidez Seca desconsidera os estoques por sua menor liquidez: (120.000 - 40.000) / 80.000 = 1,00."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-16",
            lessonId = "tp_fin_m2_a4",
            statement = "Na análise de viabilidade econômico-financeira de um projeto de investimento em infraestrutura, quando o Valor Presente Líquido (VPL) calculado à Taxa Mínima de Atratividade (TMA) é maior que zero, conclui-se que:",
            options = listOf(
                "A) A Taxa Interna de Retorno (TIR) é inferior à TMA.",
                "B) O projeto deve ser rejeitado por gerar prejuízo.",
                "C) A Taxa Interna de Retorno (TIR) é superior à TMA e o projeto é economicamente viável.",
                "D) O tempo de retorno (payback) é infinito.",
                "E) O fluxo de caixa futuro é negativo."
            ),
            correctIndex = 2,
            steps = listOf(
                "VPL > 0 significa que as entradas de caixa descontadas superam o investimento inicial.",
                "Isso implica matematicamente que a rentabilidade própria do projeto (TIR) é superior à taxa de desconto exigida (TMA), tornando o investimento viável."
            ),
            finalComment = "VPL > 0 indica que a TIR é maior que a TMA, agregando valor à organização."
        )
    )

    private fun exercisesM3A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-17",
            lessonId = "tp_fin_m3_a1",
            statement = "Uma entidade possui um conjunto de Bens avaliados em R$ 140.000,00, Direitos de R$ 60.000,00 e Obrigações com terceiros que totalizam R$ 230.000,00. Qual é a sua Situação Líquida Patrimonial?",
            options = listOf(
                "A) Positiva em R$ 30.000,00",
                "B) Nula (equilíbrio total)",
                "C) Negativa em R$ 30.000,00 (Passivo a Descoberto)",
                "D) Positiva em R$ 70.000,00",
                "E) Negativa em R$ 90.000,00"
            ),
            correctIndex = 2,
            steps = listOf(
                "Ativo Total = Bens + Direitos = 140.000 + 60.000 = R$ 200.000,00.",
                "Passivo Exigível (Obrigações) = R$ 230.000,00.",
                "Situação Líquida (PL) = Ativo - Passivo = 200.000 - 230.000 = -R$ 30.000,00.",
                "Quando o Passivo Exigível supera o Ativo Total, há Situação Líquida Negativa, tecnicamente denominada Passivo a Descoberto."
            ),
            finalComment = "Com Ativo de R$ 200.000 e Obrigações de R$ 230.000, a situação líquida é negativa em R$ 30.000,00 (Passivo a Descoberto)."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-18",
            lessonId = "tp_fin_m3_a1",
            statement = "Assinale a alternativa que contém exclusivamente contas representativas de Direitos para uma empresa:",
            options = listOf(
                "A) Caixa, Veículos e Terrenos",
                "B) Duplicatas a Receber, Adiantamento a Fornecedores e Aplicações Financeiras",
                "C) Fornecedores, Salários a Pagar e Financiamentos",
                "D) Capital Social, Reservas de Lucros e Prejuízos Acumulados",
                "E) Estoque de Matérias-Primas, Máquinas e Instalações"
            ),
            correctIndex = 1,
            steps = listOf(
                "Bens: coisas materiais ou imateriais (veículos, estoques, máquinas, marcas).",
                "Direitos: valores que a empresa tem a receber ou exigir de terceiros (Duplicatas a Receber, Adiantamento a Fornecedores, Títulos a Receber).",
                "Obrigações: dívidas com terceiros (Fornecedores, Salários a Pagar)."
            ),
            finalComment = "Duplicatas a Receber e Adiantamento a Fornecedores são direitos contra terceiros componentes do Ativo."
        )
    )

    private fun exercisesM3A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-19",
            lessonId = "tp_fin_m3_a2",
            statement = "A aquisição de um veículo operacional no valor de R$ 80.000,00, sendo R$ 20.000,00 pagos à vista em transferência bancária e o restante a prazo mediante duplicatas, gera o seguinte lançamento contábil pelo método das partidas dobradas:",
            options = listOf(
                "A) Débito em Veículos R$ 80.000; Crédito em Bancos R$ 20.000 e Crédito em Duplicatas a Pagar R$ 60.000.",
                "B) Crédito em Veículos R$ 80.000; Débito em Bancos R$ 20.000 e Débito em Duplicatas a Pagar R$ 60.000.",
                "C) Débito em Bancos R$ 20.000; Débito em Duplicatas R$ 60.000 e Crédito em Veículos R$ 80.000.",
                "D) Débito em Despesas com Veículos R$ 80.000 e Crédito em Caixa R$ 80.000.",
                "E) Crédito em Ativo Não Circulante R$ 80.000 e Débito em Passivo Circulante R$ 80.000."
            ),
            correctIndex = 0,
            steps = listOf(
                "Aumento de Ativo Imobilizado (Veículos): Débito de R$ 80.000,00.",
                "Diminuição de Ativo Circulante (Bancos Conta Movimento): Crédito de R$ 20.000,00.",
                "Aumento de Passivo Exigível (Duplicatas/Financiamento a Pagar): Crédito de R$ 60.000,00.",
                "Total a débito (80.000) = Total a crédito (20.000 + 60.000)."
            ),
            finalComment = "Lançamento correto de 3ª fórmula: D - Veículos 80.000 / C - Bancos 20.000 / C - Duplicatas a Pagar 60.000."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-20",
            lessonId = "tp_fin_m3_a2",
            statement = "Quanto à natureza dos saldos das contas contábeis, é correto afirmar que:",
            options = listOf(
                "A) Contas de Ativo têm natureza credora e aumentam com créditos.",
                "B) Contas de Passivo e Patrimônio Líquido têm natureza devedora.",
                "C) Contas de Despesa têm natureza devedora e aumentam com débitos.",
                "D) Contas de Receita têm natureza devedora e aumentam com débitos.",
                "E) Todas as contas do Balanço Patrimonial possuem natureza mista."
            ),
            correctIndex = 2,
            steps = listOf(
                "Contas de Ativo e Despesas possuem natureza DEVEDORA (aumentam a débito e diminuem a crédito).",
                "Contas de Passivo, Patrimônio Líquido e Receitas possuem natureza CREDORA (aumentam a crédito e diminuem a débito)."
            ),
            finalComment = "As contas de Despesa possuem natureza devedora, aumentando sempre por meio de lançamentos a débito."
        )
    )

    private fun exercisesM3A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-21",
            lessonId = "tp_fin_m3_a3",
            statement = "Uma bomba hidráulica de grande porte foi adquirida por R$ 120.000,00. A empresa estimou vida útil de 10 anos e valor residual de R$ 20.000,00 ao término desse prazo. Pelo método das cotas constantes (linear), qual será a depreciação acumulada desse bem após 4 anos de operação?",
            options = listOf(
                "A) R$ 36.000,00",
                "B) R$ 40.000,00",
                "C) R$ 44.000,00",
                "D) R$ 48.000,00",
                "E) R$ 50.000,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "Valor Depreciável = Custo de Aquisição - Valor Residual = 120.000 - 20.000 = R$ 100.000,00.",
                "Depreciação anual = 100.000 / 10 = R$ 10.000,00 por ano.",
                "Depreciação acumulada em 4 anos = 10.000 * 4 = R$ 40.000,00."
            ),
            finalComment = "Com valor depreciável de 100.000 e vida útil de 10 anos, a depreciação anual é 10.000. Em 4 anos, a depreciação acumulada atinge R$ 40.000,00."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-22",
            lessonId = "tp_fin_m3_a3",
            statement = "No Balanço Patrimonial, os direitos realizáveis após o término do exercício social subsequente devem ser classificados no:",
            options = listOf(
                "A) Ativo Circulante Disponível",
                "B) Ativo Realizável a Longo Prazo (Ativo Não Circulante)",
                "C) Ativo Imobilizado",
                "D) Passivo Não Circulante",
                "E) Patrimônio Líquido"
            ),
            correctIndex = 1,
            steps = listOf(
                "Ativo Circulante abrange disponibilidades e direitos realizáveis até o término do exercício seguinte.",
                "Direitos realizáveis após o término do exercício social subsequente compõem o Ativo Realizável a Longo Prazo, subgrupo do Ativo Não Circulante (Lei nº 6.404/76)."
            ),
            finalComment = "Direitos com prazo superior a um exercício subsequente integram o Ativo Realizável a Longo Prazo (Ativo Não Circulante)."
        )
    )

    private fun exercisesM3A4(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-23",
            lessonId = "tp_fin_m3_a4",
            statement = "Uma empresa comercial registrou Receita Bruta de Vendas de R$ 200.000,00, Devoluções e Abatimentos de R$ 10.000,00, Tributos sobre Vendas (ICMS/PIS/COFINS) de R$ 30.000,00 e Custo das Mercadorias Vendidas (CMV) de R$ 90.000,00. Qual foi o Lucro Bruto apurado na DRE?",
            options = listOf(
                "A) R$ 60.000,00",
                "B) R$ 70.000,00",
                "C) R$ 80.000,00",
                "D) R$ 90.000,00",
                "E) R$ 100.000,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "Receita Líquida = Receita Bruta - Devoluções - Tributos sobre Vendas.",
                "Receita Líquida = 200.000 - 10.000 - 30.000 = R$ 160.000,00.",
                "Lucro Bruto = Receita Líquida - CMV.",
                "Lucro Bruto = 160.000 - 90.000 = R$ 70.000,00."
            ),
            finalComment = "O Lucro Bruto confronta a Receita Líquida (160.000) com o Custo das Mercadorias Vendidas (90.000), resultando em R$ 70.000,00."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-24",
            lessonId = "tp_fin_m3_a4",
            statement = "Na estrutura da Demonstração do Resultado do Exercício (DRE), as despesas administrativas, despesas com vendas e outras receitas/despesas operacionais são deduzidas de qual linha para se chegar ao Resultado Operacional antes dos Tributos?",
            options = listOf(
                "A) Da Receita Bruta de Vendas",
                "B) Do Lucro Bruto",
                "C) Do Caixa Operacional",
                "D) Do Patrimônio Líquido",
                "E) Das Reservas de Lucros"
            ),
            correctIndex = 1,
            steps = listOf(
                "Estrutura vertical da DRE: Receita Líquida (-) Custo das Vendas = Lucro Bruto.",
                "A partir do Lucro Bruto, deduzem-se as Despesas Operacionais (Administrativas, Vendas, Financeiras Líquidas) para apurar o Lucro Operacional (LAIR)."
            ),
            finalComment = "As despesas operacionais são deduzidas diretamente do Lucro Bruto para obter o resultado operacional."
        )
    )

    private fun exercisesM3A5(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-25",
            lessonId = "tp_fin_m3_a5",
            statement = "Em 01/12/2025, uma empresa contratou e pagou à vista uma apólice de seguro contra incêndio no valor de R$ 12.000,00, com vigência de 12 meses (de dezembro/2025 a novembro/2026). Pelo Regime de Competência, qual valor deve ser reconhecido como Despesa com Seguros no encerramento do exercício de 2025?",
            options = listOf(
                "A) R$ 0,00",
                "B) R$ 1.000,00",
                "C) R$ 6.000,00",
                "D) R$ 11.000,00",
                "E) R$ 12.000,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "Pelo Regime de Competência, a despesa é apropriada ao período em que o serviço foi usufruído (fato gerador), independentemente do pagamento.",
                "Em 2025 transcorreu apenas 1 mês (dezembro).",
                "Apropriação em 2025: 12.000 / 12 meses = R$ 1.000,00.",
                "Os R$ 11.000,00 restantes permanecem no Ativo Circulante como Despesas do Exercício Seguinte (Seguros a Apropriar)."
            ),
            finalComment = "No Regime de Competência, apenas 1 mês incorreu em 2025, logo reconhece-se R$ 1.000,00 como despesa de 2025."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-26",
            lessonId = "tp_fin_m3_a5",
            statement = "O recebimento em dinheiro de uma duplicata a receber com cobrança de juros de mora por atraso do cliente é classificado como qual tipo de fato contábil?",
            options = listOf(
                "A) Fato Permutativo entre contas do ativo.",
                "B) Fato Modificativo Diminutivo.",
                "C) Fato Misto Aumentativo.",
                "D) Fato Misto Diminutivo.",
                "E) Fato Compensativo Puro."
            ),
            correctIndex = 2,
            steps = listOf(
                "Ocorre uma permuta entre elementos do Ativo: entrada no Caixa e baixa em Duplicatas a Receber.",
                "Simultaneamente, há o reconhecimento de uma Receita Financeira (juros de mora), que aumenta o Patrimônio Líquido.",
                "A união de um fato permutativo com uma variação aumentativa no PL caracteriza um Fato Misto (ou Composto) Aumentativo."
            ),
            finalComment = "A operação permuta ativos e reconhece receita de juros, configurando um Fato Misto Aumentativo."
        )
    )

    private fun exercisesM3A6(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-27",
            lessonId = "tp_fin_m3_a6",
            statement = "Um equipamento de bombeamento de óleo foi adquirido por uma transportadora dutoviária por R$ 240.000,00. A empresa estimou a vida útil econômica do equipamento em 10 anos e fixou um valor residual de 10% do custo de aquisição. Adotando o método da linha reta (quotas constantes), qual será o valor contábil líquido do equipamento ao final do 4º ano de uso?",
            options = listOf(
                "A) R$ 144.000,00",
                "B) R$ 153.600,00",
                "C) R$ 160.000,00",
                "D) R$ 172.800,00",
                "E) R$ 180.000,00"
            ),
            correctIndex = 1,
            steps = listOf(
                "1. Calcular o valor residual: 10% de R$ 240.000,00 = R$ 24.000,00.",
                "2. Calcular a base de cálculo depreciável: Custo - Valor Residual = 240.000 - 24.000 = R$ 216.000,00.",
                "3. Quota anual de depreciação = 216.000 / 10 anos = R$ 21.600,00 por ano.",
                "4. Depreciação acumulada em 4 anos = 4 * 21.600 = R$ 86.400,00.",
                "5. Valor contábil líquido = Custo Histórico - Depreciação Acumulada = 240.000 - 86.400 = R$ 153.600,00."
            ),
            finalComment = "A depreciação acumulada incide sobre a base depreciável (R$ 216.000), totalizando R$ 86.400 em 4 anos. O valor contábil líquido é 240.000 - 86.400 = R$ 153.600,00."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-28",
            lessonId = "tp_fin_m3_a6",
            statement = "No Balanço Patrimonial elaborado de acordo com as Normas Brasileiras de Contabilidade (NBC TG / Lei nº 6.404/1976), a conta 'Depreciação Acumulada' deve ser classificada tecnicamente como:",
            options = listOf(
                "A) Conta de Passivo Não Circulante decorrente de obrigações de reposição futura.",
                "B) Conta retificadora (redutora) do Ativo Não Circulante Imobilizado, com saldo credor.",
                "C) Conta de Patrimônio Líquido que reduz as reservas de capital.",
                "D) Conta de Despesa Operacional no Ativo Circulante.",
                "E) Conta de Provisão Financeira no Passivo Circulante."
            ),
            correctIndex = 1,
            steps = listOf(
                "A depreciação representa a perda de valor dos bens tangíveis pelo uso, ação do tempo ou obsolescência.",
                "Sua contrapartida acumula-se em conta redutora do respectivo subgrupo do Imobilizado (Ativo Não Circulante), possuindo natureza credora para subtrair do custo original do bem."
            ),
            finalComment = "Depreciação Acumulada é conta retificadora do Ativo Imobilizado com saldo credor."
        )
    )

    private fun exercisesM4A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-27",
            lessonId = "tp_fin_m4_a1",
            statement = "No planejamento orçamentário empresarial, a metodologia que não utiliza a base histórica de gastos do período anterior como ponto de partida, exigindo que cada gestor justifique a necessidade de cada rubrica de custo ou investimento a partir do zero a cada ciclo, é denominada:",
            options = listOf(
                "A) Orçamento Estático",
                "B) Orçamento Incremental",
                "C) Orçamento Base Zero (OBZ)",
                "D) Orçamento Flexível",
                "E) Orçamento Contínuo (Rolling Budget)"
            ),
            correctIndex = 2,
            steps = listOf(
                "O Orçamento Incremental parte do histórico anterior aplicando correções.",
                "O Orçamento Base Zero (OBZ) rompe com o passado e exige que todas as despesas sejam justificadas individualmente por pacotes de decisão a partir de uma base zero."
            ),
            finalComment = "O Orçamento Base Zero (OBZ) desconsidera o histórico e exige justificativa detalhada de cada gasto a partir do zero."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-28",
            lessonId = "tp_fin_m4_a1",
            statement = "A técnica de controle orçamentário em que os custos e despesas são planejados e acompanhados sob duas perspectivas simultâneas — a do gestor do centro de custos (responsável pela demanda) e a do gestor do pacote de gastos (responsável técnico corporativo pela rubrica) — é conhecida como:",
            options = listOf(
                "A) Orçamento Matricial",
                "B) Orçamento por Tendência",
                "C) Orçamento Impositivo",
                "D) Orçamento Estocástico",
                "E) Orçamento Corretivo"
            ),
            correctIndex = 0,
            steps = listOf(
                "No Orçamento Matricial (GMD - Gerenciamento Matricial de Despesas), cruza-se quem gasta (centros de custo) com o que se gasta (pacotes de gastos), havendo dupla responsabilidade e maior rigor no controle."
            ),
            finalComment = "O Orçamento Matricial estabelece o cruzamento entre entidades orçamentárias e pacotes de gastos com dupla aprovação e acompanhamento."
        )
    )

    private fun exercisesM4A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-29",
            lessonId = "tp_fin_m4_a2",
            statement = "Uma empresa vende seu produto a R$ 150,00 por unidade. Os custos e despesas variáveis somam R$ 90,00 por unidade e os custos e despesas fixos totalizam R$ 180.000,00 por mês. Quantas unidades a empresa precisa vender mensalmente para atingir o Ponto de Equilíbrio Contábil (PEC)?",
            options = listOf(
                "A) 2.000 unidades",
                "B) 2.500 unidades",
                "C) 3.000 unidades",
                "D) 3.500 unidades",
                "E) 4.000 unidades"
            ),
            correctIndex = 2,
            steps = listOf(
                "Calcular a Margem de Contribuição Unitária (MCu): 150 - 90 = R$ 60,00 por unidade.",
                "Fórmula do Ponto de Equilíbrio Contábil em unidades: PEC = Custos e Despesas Fixos / MCu.",
                "PEC = 180.000 / 60 = 3.000 unidades."
            ),
            finalComment = "A empresa precisa vender 3.000 unidades para cobrir todos os seus custos e despesas fixos (MCu de R$ 60 * 3.000 = R$ 180.000)."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-FIN-SUP-30",
            lessonId = "tp_fin_m4_a2",
            statement = "Sobre a relação entre os diferentes pontos de equilíbrio de uma organização que possui gastos fixos com depreciação de bens operacionais e almeja uma margem de lucro mínima sobre o capital próprio, é correto afirmar que:",
            options = listOf(
                "A) Ponto de Equilíbrio Econômico < Ponto de Equilíbrio Contábil < Ponto de Equilíbrio Financeiro",
                "B) Ponto de Equilíbrio Financeiro < Ponto de Equilíbrio Contábil < Ponto de Equilíbrio Econômico",
                "C) Ponto de Equilíbrio Contábil é sempre o maior de todos.",
                "D) Ponto de Equilíbrio Financeiro inclui os juros sobre capital próprio.",
                "E) O Ponto de Equilíbrio Econômico deduz as perdas não financeiras."
            ),
            correctIndex = 1,
            steps = listOf(
                "No PEF, deduzem-se despesas não desembolsáveis (depreciação), resultando em menor volume de vendas necessário.",
                "No PEC, cobrem-se exatamente os custos e despesas fixos contábeis totais.",
                "No PEE, além dos custos fixos, adiciona-se a meta de lucro desejada, exigindo o maior volume de vendas.",
                "Logo: PEF < PEC < PEE."
            ),
            finalComment = "A ordem hierárquica das quantidades necessárias é sempre PEF < PEC < PEE."
        )
    )
}

