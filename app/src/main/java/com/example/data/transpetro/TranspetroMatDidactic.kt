package com.example.data.transpetro

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise
import com.example.data.transpetro.TranspetroDidacticProvider.buildBundle
import com.example.data.transpetro.TranspetroDidacticProvider.buildExercise

/**
 * Lote 6 — Conteúdo Didático e Questões Suplementares de Matemática (25 aulas: tp_mat_m1_a1..tp_mat_m12_a2)
 * Concurso Transpetro — Técnico de Administração e Controle Júnior (Fundação Cesgranrio).
 */
object TranspetroMatDidactic {

    fun getBundle(lessonId: String): CalculusDidacticBundle? = when (lessonId) {

        "tp_mat_m1_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "A Teoria dos Conjuntos e os Diagramas de Venn abrem o programa de Matemática da Fundação Cesgranrio e aparecem com enorme frequência em problemas de pesquisa operacional, levantamento de qualificações de equipes e auditoria de cadastros. Dominar o Princípio da Inclusão-Exclusão evita o erro mais clássico dos candidatos: somar duas vezes os elementos que pertencem simultaneamente a mais de um grupo.",
            theory = """
                • Operações Fundamentais entre Conjuntos:
                  - União (A ∪ B): conjunto formado pelos elementos que pertencem a A, ou a B, ou a ambos ("pelo menos um dos conjuntos").
                  - Interseção (A ∩ B): conjunto formado exclusivamente pelos elementos que pertencem simultaneamente a A e a B ("ambos os conjuntos").
                  - Diferença (A - B): conjunto dos elementos que pertencem somente a A e não pertencem a B ("apenas A"). Vale a relação: n(A - B) = n(A) - n(A ∩ B).
                  - Complementar (Aᶜ): elementos do conjunto universo (U) que não pertencem a A ("nenhum dos elementos de A").
                • Princípio da Inclusão-Exclusão (Cardinalidade):
                  - Para 2 conjuntos: n(A ∪ B) = n(A) + n(B) - n(A ∩ B).
                  - Para 3 conjuntos: n(A ∪ B ∪ C) = n(A) + n(B) + n(C) - n(A ∩ B) - n(A ∩ C) - n(B ∩ C) + n(A ∩ B ∩ C).
                • Regra de Ouro para Diagramas de Venn:
                  - Sempre preencha o diagrama de dentro para fora, começando pela interseção máxima (A ∩ B ∩ C ou A ∩ B) e descontando os valores já alocados ao preencher as regiões adjacentes. Não se esqueça de somar os elementos externos ("nenhum dos conjuntos") para igualar ao universo total n(U).
            """.trimIndent(),
            practicalApplication = "Em um terminal da Transpetro com 80 técnicos, 45 possuem treinamento em Brigada de Incêndio (A), 38 possuem certificação em Primeiros Socorros (B) e 12 não possuem nenhum dos dois treinamentos. Sabendo que 80 - 12 = 68 técnicos possuem ao menos um treinamento, o setor de RH calcula rapidamente quantos possuem ambas as certificações: n(A ∩ B) = 45 + 38 - 68 = 15 técnicos.",
            summary = "Preencha diagramas sempre da interseção central para as extremidades; subtraia a interseção quando o enunciado informar o total de um conjunto ('usam A') e não o valor exclusivo ('usam apenas A').",
            formulaName = "Princípio da Inclusão-Exclusão (2 Conjuntos)",
            formulaSyntax = "n(A ∪ B) = n(A) + n(B) - n(A ∩ B)   e   n(U) = n(A ∪ B) + n(Nenhum)",
            formulaTerms = listOf(
                "n(U): Total de elementos do conjunto universo pesquisado",
                "n(A) e n(B): Quantidade total de elementos em A e em B",
                "n(A ∩ B): Elementos que pertencem simultaneamente a A e B",
                "n(Nenhum): Elementos fora da união A ∪ B"
            ),
            formulaWhenToUse = "Aplicar em problemas que fornecem totais de grupos com sobreposição (interseção) e pedem o total geral, a interseção ou a quantidade exclusiva de uma categoria.",
            exampleTitle = "Levantamento de Fornecedores Homologados",
            exampleProblem = "Em um cadastro de 100 fornecedores da Transpetro, 62 fornecem materiais elétricos, 48 fornecem materiais hidráulicos e 15 não fornecem nenhum desses dois tipos de material. Quantos fornecedores fornecem APENAS materiais elétricos?",
            exampleSteps = listOf(
                "1. Calcular n(E ∪ H) descontando os que não fornecem nenhum dos dois: n(E ∪ H) = 100 - 15 = 85.",
                "2. Aplicar a inclusão-exclusão: 85 = 62 + 48 - n(E ∩ H) => n(E ∩ H) = 110 - 85 = 25 fornecedores (ambos).",
                "3. Calcular os que fornecem APENAS materiais elétricos: n(E - H) = n(E) - n(E ∩ H) = 62 - 25 = 37."
            ),
            exampleAnswer = "37 fornecedores fornecem apenas materiais elétricos.",
            goldTip = "Atenção às palavras-chave da Cesgranrio: 'apenas/somente/exclusivamente A' já exclui a interseção; quando o texto diz apenas 'consomem A', a interseção está embutida e precisa ser subtraída!"
        )

        "tp_mat_m1_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "O estudo dos Conjuntos Numéricos (N, Z, Q, I, R) e dos Intervalos Reais sustenta toda a linguagem algébrica das provas da Cesgranrio. Além da hierarquia de inclusão entre os conjuntos, a banca cobra a conversão de dízimas periódicas em frações geratrizes e operações de união e interseção de intervalos na reta real.",
            theory = """
                • Hierarquia dos Conjuntos Numéricos (N ⊂ Z ⊂ Q ⊂ R):
                  - Naturais (N): {0, 1, 2, 3, ...}. N* exclui o zero.
                  - Inteiros (Z): {..., -2, -1, 0, 1, 2, ...}. Inclui os negativos.
                  - Racionais (Q): todo número que pode ser escrito na forma de fração a/b, com a ∈ Z e b ∈ Z*. Inclui inteiros, decimais exatos e dízimas periódicas.
                  - Irracionais (I): números com representação decimal infinita e não periódica (ex.: √2, √3, π, e). Não podem ser escritos como fração de inteiros.
                  - Reais (R): união dos racionais com os irracionais (R = Q ∪ I, sendo Q ∩ I = ∅).
                • Fração Geratriz de Dízimas Periódicas:
                  - Simples: período no numerador sobre tantos noves (9) quantos forem os algarismos do período (ex.: 0,444... = 4/9; 0,2727... = 27/99 = 3/11).
                • Intervalos Reais:
                  - Colchete fechado [a, b] inclui os extremos (≤ ou ≥); colchete aberto ]a, b[ ou parênteses (a, b) exclui os extremos (< ou >).
            """.trimIndent(),
            practicalApplication = "Na programação de faixas operacionais de pressão em dutos, se o alarme de segurança A atua no intervalo [40, 85] bar e o sistema de bloqueio B atua em ]70, 110] bar, a faixa em que ambos os sistemas estão simultaneamente ativos corresponde à interseção ]70, 85] bar.",
            summary = "Todo natural é inteiro, todo inteiro é racional, e reais dividem-se em racionais e irracionais. Em intervalos reais, bolinha cheia = colchete fechado (pertence); bolinha vazia = colchete aberto (não pertence).",
            formulaName = "Fração Geratriz e Interseção de Intervalos",
            formulaSyntax = "0,ppp... = p / 99...9   |   [a, b] ∩ ]c, d] = ]máx(a,c), mín(b,d)]",
            formulaTerms = listOf(
                "p: Período que se repete na dízima periódica simples",
                "99...9: Tantos algarismos 9 quantos forem os dígitos do período p",
                "[a, b]: Intervalo fechado em a e em b (inclui os extremos)",
                "]c, d]: Intervalo aberto em c (exclui c) e fechado em d (inclui d)"
            ),
            formulaWhenToUse = "Usar na transformação de dízimas periódicas em frações irredutíveis e na determinação de faixas comuns (interseção) ou totais (união) de intervalos reais.",
            exampleTitle = "Interseção de Faixas de Temperatura de Armazenagem",
            exampleProblem = "Um aditivo químico exige temperatura no intervalo A = [-5, 22] °C, enquanto um solvente armazenado no mesmo galpão exige temperatura no intervalo B = ]10, 30[ °C. Qual é o intervalo de temperaturas inteiras em que ambos podem ser armazenados juntos?",
            exampleSteps = listOf(
                "1. Determinar a interseção contínua A ∩ B: o limite inferior é ]10 (aberto, pois 10 ∉ B) e o limite superior é 22] (fechado, pois 22 ∈ A e 22 ∈ B).",
                "2. Logo, A ∩ B = ]10, 22].",
                "3. Os números inteiros pertencentes a ]10, 22] vão de 11 até 22, totalizando 22 - 11 + 1 = 12 valores inteiros."
            ),
            exampleAnswer = "Intervalo contínuo ]10, 22] °C (inteiros de 11 °C a 22 °C, totalizando 12 valores).",
            goldTip = "Para contar quantos números inteiros existem de M até N (incluindo ambos), nunca faça apenas N - M: use sempre (N - M + 1)!"
        )

        "tp_mat_m2_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "O Sistema Legal de Medidas — especialmente a conversão entre unidades de comprimento, área, volume e capacidade (litros) — é presença obrigatória nas provas da Transpetro. Como a empresa opera tanques de armazenamento, oleodutos e navios-tanque, a Cesgranrio cobra constantemente a ponte entre metros cúbicos (m³) e litros (L).",
            theory = """
                • Escalas de Conversão Métrica (km — hm — dam — m — dm — cm — mm):
                  - Comprimento (1D — passo de 10¹): cada casa para a direita multiplica por 10; para a esquerda divide por 10 (1 km = 1.000 m; 1 m = 100 cm).
                  - Área (2D — passo de 10² = 100): cada casa para a direita multiplica por 100; para a esquerda divide por 100 (1 m² = 100 dm² = 10.000 cm²; 1 hectare = 1 hm² = 10.000 m²).
                  - Volume (3D — passo de 10³ = 1.000): cada casa para a direita multiplica por 1.000; para a esquerda divide por 1.000 (1 m³ = 1.000 dm³ = 1.000.000 cm³).
                • Ponte Fundamental entre Volume e Capacidade:
                  - 1 m³ = 1.000 litros (L)
                  - 1 dm³ = 1 litro (L)
                  - 1 cm³ = 1 mililitro (mL)
            """.trimIndent(),
            practicalApplication = "Ao cubar um dique de contenção retangular com 20 m de comprimento, 15 m de largura e 1,5 m de altura, o técnico obtém um volume de 450 m³. Multiplicando por 1.000, conclui imediatamente que a bacia comporta 450.000 litros de derivado líquido em caso de transbordamento.",
            summary = "Comprimento anda de 1 em 1 zero (×10); Área anda de 2 em 2 zeros (×100); Volume anda de 3 em 3 zeros (×1.000). Grave para a prova: 1 m³ = 1.000 L e 1 dm³ = 1 L.",
            formulaName = "Equivalência Volume × Capacidade",
            formulaSyntax = "1 m³ = 1.000 dm³ = 1.000 L   |   1 dm³ = 1 L   |   1 cm³ = 1 mL",
            formulaTerms = listOf(
                "m³ (metro cúbico): Unidade padrão de volume para tanques e dutos",
                "dm³ (decímetro cúbico): Equivalente exato a 1 litro",
                "cm³ (centímetro cúbico): Equivalente exato a 1 mililitro (mL)"
            ),
            formulaWhenToUse = "Utilizar sempre que o problema fornecer dimensões lineares (em m ou cm) e pedir a capacidade do reservatório em litros ou mililitros.",
            exampleTitle = "Transferência de Óleo Lubrificante em Tambores",
            exampleProblem = "Um reservatório contém 4,5 m³ de óleo lubrificante que será totalmente transferido para galões plásticos com capacidade de 25.000 cm³ cada um. Quantos galões serão completamente cheios?",
            exampleSteps = listOf(
                "1. Converter o volume do reservatório para litros: 4,5 m³ × 1.000 = 4.500 L.",
                "2. Converter a capacidade de cada galão para litros: como 1.000 cm³ = 1 dm³ = 1 L, temos 25.000 cm³ = 25 L.",
                "3. Dividir o volume total pela capacidade de cada galão: 4.500 L / 25 L = 180 galões."
            ),
            exampleAnswer = "Serão necessários 180 galões.",
            goldTip = "Antes de calcular qualquer área ou volume na prova, converta todas as medidas lineares para a mesma unidade (por exemplo, tudo para metros ou tudo para decímetros)!"
        )

        "tp_mat_m2_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "As medidas de massa, tempo (sistema sexagesimal) e vazão operacional conectam a aritmética à rotina real de bombeamento e expedição da Transpetro. Erros na conversão de horas decimais (como 2,4 h) para horas e minutos são armadilhas recorrentes nas provas da Cesgranrio.",
            theory = """
                • Medidas de Massa:
                  - 1 tonelada (t) = 1.000 quilogramas (kg)
                  - 1 quilograma (kg) = 1.000 gramas (g)
                  - 1 grama (g) = 1.000 miligramas (mg)
                • Medidas de Tempo (Sistema Sexagesimal — Base 60):
                  - 1 hora (h) = 60 minutos (min) = 3.600 segundos (s).
                  - Atenção às horas decimais: a parte decimal de uma hora deve ser multiplicada por 60 para virar minutos! Exemplo: 3,25 h = 3 h + (0,25 × 60) min = 3 h 15 min (e NUNCA 3 h 25 min!).
                • Vazão Operacional (Q):
                  - É a razão entre o volume escoado (V) e o intervalo de tempo (Δt): Q = V / Δt.
                  - Conversão rápida: para passar de m³/h para L/min, multiplica-se por 1.000 (m³ -> L) e divide-se por 60 (h -> min).
            """.trimIndent(),
            practicalApplication = "Se uma bomba de transferência opera com vazão constante de 180 m³/h para encher um tanque de 630 m³, o tempo de operação será Δt = 630 / 180 = 3,5 horas, o que equivale a 3 horas e 30 minutos no cronograma do Centro de Controle Operacional.",
            summary = "1 t = 1.000 kg; 1 h = 60 min = 3.600 s. Parte decimal de hora multiplica-se por 60 para obter minutos (0,5 h = 30 min; 0,2 h = 12 min; 0,75 h = 45 min).",
            formulaName = "Equação da Vazão Volumétrica e Tempo Operacional",
            formulaSyntax = "Q = V / Δt   =>   V = Q · Δt   =>   Δt = V / Q",
            formulaTerms = listOf(
                "Q: Vazão volumétrica (ex.: m³/h, L/min, L/s)",
                "V: Volume total transferido ou armazenado (m³ ou L)",
                "Δt: Tempo de bombeamento ou escoamento (h, min ou s)"
            ),
            formulaWhenToUse = "Aplicar em problemas de enchimento ou esvaziamento de tanques por bombas, torneiras ou dutos operando individualmente ou em conjunto.",
            exampleTitle = "Tempo de Bombeamento em Oleoduto",
            exampleProblem = "Um duto transfere combustível a uma vazão constante de 2.500 litros por minuto. Em quanto tempo (em horas e minutos) esse duto transferirá um lote de 540 m³ de combustível?",
            exampleSteps = listOf(
                "1. Converter o volume total de m³ para litros: V = 540 m³ × 1.000 = 540.000 L.",
                "2. Calcular o tempo em minutos: Δt = 540.000 L / 2.500 (L/min) = 216 minutos.",
                "3. Converter 216 minutos para horas e minutos: 216 ÷ 60 = 3 horas inteiras (180 min) e sobram 36 minutos."
            ),
            exampleAnswer = "3 horas e 36 minutos.",
            goldTip = "Quando duas bombas operam juntas enchendo o mesmo tanque, SOME suas vazões individuais (Q_total = Q₁ + Q₂); se uma enche e um dreno esvazia, SUBTRAIA as vazões (Q_efetiva = Q_entrada - Q_saída)!"
        )

        "tp_mat_m3_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "As operações fundamentais, o Mínimo Múltiplo Comum (MMC) e o Máximo Divisor Comum (MDC) protagonizam questões contextualizadas de planejamento de rotas, escalas de inspeção e divisão de lotes em almoxarifados. Saber distinguir imediatamente se o problema pede MMC ou MDC garante pontos rápidos na prova.",
            theory = """
                • Hierarquia das Expressões Numéricas:
                  - Símbolos de agrupamento: 1º Parênteses ( ), 2º Colchetes [ ], 3º Chaves { }.
                  - Operações: 1º Potenciação e Radiciação, 2º Multiplicação e Divisão (na ordem em que aparecem), 3º Adição e Subtração.
                • Mínimo Múltiplo Comum (MMC):
                  - É o menor número positivo (diferente de zero) que é múltiplo simultâneo de dois ou mais inteiros.
                  - Gatilho de prova: problemas de CICLOS PERIÓDICOS, coincidência de eventos futuros ("De quanto em quanto tempo voltarão a se encontrar simultaneamente?").
                • Máximo Divisor Comum (MDC):
                  - É o maior número inteiro que divide simultaneamente dois ou mais números sem deixar resto.
                  - Gatilho de prova: problemas de DIVISÃO EM PARTES IGUAIS de maior tamanho possível sem sobras ("Qual o menor número de lotes/pacotes de mesmo tamanho máximo?").
                • Propriedade Fundamental: MMC(a, b) × MDC(a, b) = a × b.
            """.trimIndent(),
            practicalApplication = "No almoxarifado da Transpetro, chegaram 120 válvulas de aço e 168 flanges que devem ser acondicionados em caixas com a mesma quantidade máxima de peças, sem misturar os tipos e sem sobrar nenhuma peça. Como MDC(120, 168) = 24, cada caixa conterá 24 peças (5 caixas de válvulas + 7 caixas de flanges = 12 caixas ao todo).",
            summary = "MMC = encontro futuro de ciclos periódicos (múltiplo >= aos números dados). MDC = partição em grupos iguais de tamanho máximo sem sobra (divisor <= aos números dados).",
            formulaName = "Decomposição Simultânea e Relação MMC × MDC",
            formulaSyntax = "MMC(a, b) · MDC(a, b) = a · b",
            formulaTerms = listOf(
                "MMC(a, b): Produto de todos os fatores primos comuns e não comuns com os maiores expoentes",
                "MDC(a, b): Produto apenas dos fatores primos que dividem simultaneamente a e b (menores expoentes)"
            ),
            formulaWhenToUse = "Usar MMC para calcular o próximo instante de coincidência de eventos periódicos e MDC para dividir quantidades distintas no menor número possível de pacotes iguais.",
            exampleTitle = "Escala de Inspeção Preventiva de Dutos",
            exampleProblem = "Três linhas de inspeção da Transpetro realizam vistorias periódicas a cada 12 dias, 15 dias e 20 dias, respectivamente. Se hoje as três equipes realizaram vistoria juntas, daqui a quantos dias ocorrerá a próxima vistoria simultânea das três equipes?",
            exampleSteps = listOf(
                "1. Identificar que se trata de coincidência futura de eventos periódicos -> cálculo de MMC(12, 15, 20).",
                "2. Fatorar os números: 12 = 2² · 3; 15 = 3 · 5; 20 = 2² · 5.",
                "3. Tomar todos os fatores primos com maiores expoentes: MMC = 2² · 3 · 5 = 4 · 15 = 60 dias."
            ),
            exampleAnswer = "A próxima vistoria simultânea ocorrerá daqui a 60 dias.",
            goldTip = "Em questões de MDC, leia a pergunta final com atenção: veja se a banca pede o TAMANHO de cada pacote (que é o próprio MDC) ou a QUANTIDADE TOTAL de pacotes (que é a soma dos quocientes após dividir cada total pelo MDC)!"
        )

        "tp_mat_m3_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "Problemas envolvendo frações ('partes do restante'), números decimais, potenciação (notação científica) e radiciação aparecem diretamente ou embutidos em questões de orçamento e controle de estoques da Cesgranrio.",
            theory = """
                • Operações com Frações e Problemas do "Restante":
                  - Adição/Subtração: igualar os denominadores pelo MMC.
                  - Multiplicação: numerador × numerador e denominador × denominador (Na matemática, a preposição "DE" significa multiplicação: 2/5 DE 3/4 = 2/5 × 3/4 = 6/20 = 3/10).
                  - Divisão de frações: conserva-se a primeira fração e multiplica-se pelo inverso da segunda: (a/b) ÷ (c/d) = (a/b) × (d/c).
                • Propriedades das Potências (mesma base a ≠ 0):
                  - Produto: aᵐ · aⁿ = aᵐ⁺ⁿ
                  - Quociente: aᵐ / aⁿ = aᵐ⁻ⁿ
                  - Potência de potência: (aᵐ)ⁿ = aᵐ·ⁿ
                  - Expoente negativo: a⁻ⁿ = (1/a)ⁿ
                  - Expoente fracionário: a^(m/n) = ⁿ√(aᵐ)
                • Notação Científica: escrita na forma N × 10ⁿ, em que 1 ≤ N < 10 e n ∈ Z.
            """.trimIndent(),
            practicalApplication = "Se um projeto logístico consome 1/4 do seu orçamento no 1º trimestre e 2/3 DO RESTANTE no 2º trimestre, ele consumiu no 2º trimestre (2/3) × (3/4) = 2/4 = 1/2 do orçamento total. Somando 1/4 + 1/2 = 3/4 consumidos, resta exatamente 1/4 do orçamento para o 2º semestre.",
            summary = "Preposição 'de' entre frações ou porcentagens equivale a multiplicação. Em consumos sucessivos sobre 'o restante', atualize a fração que sobrou a cada etapa antes de multiplicar.",
            formulaName = "Fração Complementar e Propriedades de Potências",
            formulaSyntax = "Restante de (a/b) = (b - a)/b   |   aᵐ · aⁿ = aᵐ⁺ⁿ   |   aᵐ / aⁿ = aᵐ⁻ⁿ",
            formulaTerms = listOf(
                "(b - a)/b: Fração complementar que sobra após retirar a/b do inteiro",
                "a: Base real não nula da potência",
                "m, n: Expoentes das potências"
            ),
            formulaWhenToUse = "Aplicar em problemas de retiradas sucessivas de estoque/orçamento e na simplificação de expressões com potências de base 10.",
            exampleTitle = "Execução de Orçamento Setorial em Etapas",
            exampleProblem = "Uma gerência administrativa utilizou 2/5 de sua verba anual no primeiro semestre. No terceiro trimestre, utilizou 1/3 do que havia sobrado, restando ainda R$ 120.000,00 para o quarto trimestre. Qual era a verba anual total dessa gerência?",
            exampleSteps = listOf(
                "1. Após gastar 2/5 no 1º semestre, sobraram 3/5 da verba total.",
                "2. No 3º trimestre, gastou 1/3 de 3/5, restando 2/3 de 3/5 = (2/3) × (3/5) = 2/5 da verba total.",
                "3. Igualar a fração restante ao saldo em reais: (2/5) · V = 120.000 => V = 120.000 × 5 / 2 = R$ 300.000,00."
            ),
            exampleAnswer = "A verba anual total era de R$ 300.000,00.",
            goldTip = "Em vez de somar as frações gastas, acompanhe sempre as frações QUE SOBRAM: se gastou 2/5 (sobrou 3/5) e depois gastou 1/3 do resto (sobrou 2/3 do resto), o saldo final é simplesmente (3/5) × (2/3) = 2/5 do total!"
        )

        "tp_mat_m4_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "Razão, proporção, escalas cartográficas e divisão proporcional formam o coração da aritmética cobrada pela Cesgranrio. Em todas as últimas provas da Transpetro e da Petrobras, pelo menos uma questão envolveu razão entre grandezas ou rateio proporcional de verbas e cargas.",
            theory = """
                • Razão e Proporção:
                  - Razão entre a e b (com b ≠ 0) é o quociente a/b.
                  - Proporção é a igualdade entre duas razões: a/b = c/d <=> a · d = b · c (propriedade fundamental: produto dos meios = produto dos extremos).
                  - Escala: E = Medida no Desenho / Medida Real (ambas na mesma unidade de comprimento).
                • Divisão Diretamente Proporcional:
                  - Dividir um total T em partes diretamente proporcionais a a, b e c consiste em escrever: A = a·k, B = b·k e C = c·k, onde k = T / (a + b + c) é a constante de proporcionalidade.
                • Divisão Inversamente Proporcional:
                  - Dividir T em partes inversamente proporcionais a a, b e c equivale a dividir diretamente proporcional aos inversos (1/a, 1/b e 1/c): A = k/a, B = k/b e C = k/c.
            """.trimIndent(),
            practicalApplication = "Para ratear um orçamento de manutenção de R$ 240.000,00 entre três bases operacionais em partes diretamente proporcionais ao número de equipamentos de cada base (12, 18 e 30 equipamentos), soma-se 12 + 18 + 30 = 60 equipamentos. A constante k vale 240.000 / 60 = R$ 4.000,00 por equipamento, cabendo R$ 48.000,00, R$ 72.000,00 e R$ 120.000,00 a cada base.",
            summary = "Use sempre a constante de proporcionalidade 'k'! Na divisão direta, multiplique por k (A = a·k); na divisão inversa, multiplique pelo inverso (A = k/a).",
            formulaName = "Constante de Proporcionalidade (k) no Rateio Direto e Inverso",
            formulaSyntax = "Direta: a·k + b·k + c·k = Total   |   Inversa: (k/a) + (k/b) + (k/c) = Total",
            formulaTerms = listOf(
                "k: Constante de proporcionalidade (valor de 1 cota-parte)",
                "a, b, c: Pesos ou parâmetros de rateio de cada participante",
                "Total: Montante global a ser distribuído"
            ),
            formulaWhenToUse = "Aplicar sempre que um valor total precisar ser repartido de forma diretamente ou inversamente proporcional a determinados parâmetros.",
            exampleTitle = "Divisão Inversamente Proporcional ao Número de Faltas",
            exampleProblem = "Um prêmio de produtividade de R$ 13.000,00 será dividido entre três equipes em partes inversamente proporcionais ao número de ocorrências registradas no ano: 2, 3 e 4 ocorrências. Quanto receberá a equipe que registrou o MENOR número de ocorrências?",
            exampleSteps = listOf(
                "1. Montar a soma com os inversos: k/2 + k/3 + k/4 = 13.000.",
                "2. Tirar o MMC(2, 3, 4) = 12: (6k + 4k + 3k) / 12 = 13.000 => 13k / 12 = 13.000 => k = 12.000.",
                "3. A equipe com menor número de ocorrências (2) receberá: k / 2 = 12.000 / 2 = R$ 6.000,00."
            ),
            exampleAnswer = "A equipe com 2 ocorrências receberá R$ 6.000,00.",
            goldTip = "Em divisão diretamente proporcional a (12, 18, 30), simplifique os números antes de somar as cotas! Dividindo todos por 6, os pesos viram (2, 3, 5), cuja soma é 10, tornando a conta instantânea!"
        )

        "tp_mat_m4_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "A Regra de Três Simples e Composta resolve problemas operacionais envolvendo número de técnicos, horas trabalhadas por dia, dias de prazo e volume de serviço executado. O segredo para nunca errar na Cesgranrio é comparar cada grandeza isoladamente com a coluna da incógnita (x).",
            theory = """
                • Análise de Proporcionalidade (Comparação com a Coluna do X):
                  - Diretamente Proporcionais (setas no mesmo sentido): quando uma grandeza aumenta, a outra também aumenta na mesma razão (ex.: mais volume de carga exige mais horas de bombeamento).
                  - Inversamente Proporcionais (setas em sentidos opostos): quando uma grandeza aumenta, a outra diminui na razão inversa (ex.: mais bombas operando em paralelo exigem menos horas para esvaziar o mesmo tanque; maior velocidade implica menor tempo de viagem).
                • Montagem da Equação na Regra de Três Composta:
                  - Isole a razão da coluna que contém a incógnita x no lado esquerdo da igualdade e iguale ao produto das demais razões, invertendo apenas aquelas que forem INVERSAMENTE proporcionais à coluna do x.
            """.trimIndent(),
            practicalApplication = "Se 4 analistas conferem 120 notas fiscais em 3 dias trabalhando 6 horas por dia, quantos dias levarão 6 analistas para conferir 300 notas fiscais trabalhando 5 horas por dia? Montando a proporção com inversão das grandezas inversas ao tempo (analistas e horas/dia) e manutenção da grandeza direta (notas fiscais), determina-se o prazo exato.",
            summary = "Fixe a coluna do X. Pergunte a cada outra coluna: 'Se eu aumentar esta grandeza, o X aumenta ou diminui?'. Se aumentar (Direta), mantém a fração; se diminuir (Inversa), inverte a fração!",
            formulaName = "Método Processo × Produto na Regra de Três Composta",
            formulaSyntax = "(Trabalhadores₁ · Horas/dia₁ · Dias₁) / Produção₁ = (Trabalhadores₂ · Horas/dia₂ · Dias₂) / Produção₂",
            formulaTerms = listOf(
                "Trabalhadores, Horas/dia, Dias: Fatores de esforço/insumo (ficam no numerador)",
                "Produção (serviço executado, peças, m³): Resultado/produto do trabalho (fica no denominador)"
            ),
            formulaWhenToUse = "Aplicar em qualquer problema de regra de três composta envolvendo equipes/máquinas, jornada diária, prazo e quantidade de trabalho produzido.",
            exampleTitle = "Dimensionamento de Equipe de Cadastramento",
            exampleProblem = "Trabalhando 6 horas por dia, 4 técnicos cadastram 240 itens patrimoniais em 5 dias. Quantos dias serão necessários para que 5 técnicos, trabalhando 8 horas por dia no mesmo ritmo, cadastrem 400 itens patrimoniais?",
            exampleSteps = listOf(
                "1. Aplicar a relação (Técnicos · Horas/dia · Dias) / Itens = constante.",
                "2. Substituir os dados dos dois cenários: (4 · 6 · 5) / 240 = (5 · 8 · d) / 400.",
                "3. Simplificar: 120 / 240 = 40d / 400 => 1/2 = d / 10 => d = 5 dias."
            ),
            exampleAnswer = "Serão necessários 5 dias.",
            goldTip = "A fórmula (Esforço / Produção = constante) elimina completamente a dúvida das setinhas: tudo que é 'esforço' (operários, máquinas, dias, horas/dia) multiplica em cima; o 'produto/obra' divide embaixo!"
        )

        "tp_mat_m5_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "Porcentagem é o tópico mais cobrado em toda a prova de Matemática da Cesgranrio. Além do cálculo direto de aumentos e descontos, a banca adora exigir a recomposição do valor original (descobrir quanto custava um serviço antes do reajuste ou do desconto).",
            theory = """
                • Representações de uma Taxa Percentual:
                  - Forma percentual: 15% | Forma fracionária: 15/100 = 3/20 | Forma decimal (unitária): 0,15.
                • Fatores Multiplicativos de Atualização:
                  - Aumento de i%: multiplica-se o valor inicial por (1 + i). Exemplo: aumento de 18% -> V_final = V_inicial × 1,18.
                  - Desconto de i%: multiplica-se o valor inicial por (1 - i). Exemplo: desconto de 12% -> V_final = V_inicial × 0,88.
                • Recomposição da Base (Armadilha Clássica):
                  - Se um valor V_inicial sofreu aumento de 25% e passou a custar R$ 500,00, NUNCA calcule 25% de 500! Divida pelo fator multiplicativo: V_inicial = 500 / 1,25 = R$ 400,00.
                  - Para retornar ao valor original após um aumento de i%, o desconto necessário sobre o novo valor é d = i / (1 + i).
            """.trimIndent(),
            practicalApplication = "Uma fatura contratual foi paga com multa de 5% por atraso, totalizando R$ 42.000,00. Para lançar na contabilidade o valor principal do serviço sem a multa, o técnico divide R$ 42.000,00 por 1,05, encontrando exatamente R$ 40.000,00 (e R$ 2.000,00 de encargo).",
            summary = "Aumento de i% -> multiplica por (1 + i). Desconto de i% -> multiplica por (1 - i). Para descobrir o valor ANTES do aumento ou desconto, DIVIDA o valor final pelo fator (1 ± i).",
            formulaName = "Fator de Atualização e Recomposição do Valor Inicial",
            formulaSyntax = "V_final = V_inicial · (1 ± i)   <=>   V_inicial = V_final / (1 ± i)",
            formulaTerms = listOf(
                "V_inicial: Valor original (base 100% da operação)",
                "V_final: Valor resultante após o reajuste ou abatimento",
                "i: Taxa percentual na forma decimal (ex.: 20% = 0,20)"
            ),
            formulaWhenToUse = "Aplicar em qualquer cálculo de reajuste, desconto ou descoberta do preço original antes da variação percentual.",
            exampleTitle = "Recuperação do Preço Original antes do Desconto",
            exampleProblem = "Após negociar um desconto de 15% sobre o preço de tabela, o setor de compras da Transpetro adquiriu um lote de bombas hidráulicas por R$ 68.000,00. Qual era o preço de tabela antes do desconto?",
            exampleSteps = listOf(
                "1. Identificar o fator de desconto para 15%: f = 1 - 0,15 = 0,85.",
                "2. Equacionar: Preço_Tabela × 0,85 = 68.000.",
                "3. Isolar o Preço_Tabela: 68.000 / 0,85 = 6.800.000 / 85 = R$ 80.000,00."
            ),
            exampleAnswer = "O preço de tabela antes do desconto era R$ 80.000,00.",
            goldTip = "Decore as equivalências clássicas de retorno à base: um aumento de 25% (×1,25 = ×5/4) é anulado por um desconto de 20% (×0,80 = ×4/5); um aumento de 20% (×6/5) é anulado por um desconto de 16,67% (1/6)!"
        )

        "tp_mat_m5_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "Em questões de Variações Percentuais Sucessivas e problemas de mistura ou participação relativa em tabelas, a Cesgranrio testa se o candidato sabe que porcentagens sucessivas incidem sobre bases atualizadas (e não se somam algebricamente).",
            theory = """
                • Variações Percentuais Sucessivas:
                  - O fator acumulado de várias taxas sucessivas é o PRODUTO dos fatores individuais:
                    F_acum = (1 ± i₁) · (1 ± i₂) · ... · (1 ± iₙ).
                  - Exemplo: um aumento de 10% seguido de outro aumento de 10% resulta em 1,10 × 1,10 = 1,21 (+21%, e não 20%).
                  - Um aumento de p% seguido de um desconto de p% SEMPRE gera perda líquida de (p²) / 100%: por exemplo, +20% seguido de -20% resulta em 1,20 × 0,80 = 0,96 (-4%).
                • Mudança de Participação Relativa (Misturas e Quadros de Pessoal):
                  - Quando uma categoria aumenta ou diminui dentro de um grupo, tanto o numerador quanto o denominador (total) se alteram! Equacione sempre em função das quantidades absolutas.
            """.trimIndent(),
            practicalApplication = "Se a tarifa de frete marítimo subiu 20% no 1º semestre e caiu 10% no 2º semestre, o fator acumulado no ano foi 1,20 × 0,90 = 1,08, o que representa um aumento líquido anual de 8%.",
            summary = "Nunca some nem subtraia taxas percentuais sucessivas! Multiplique os fatores (1 ± i₁) × (1 ± i₂) e compare o resultado com 1,00 (100%).",
            formulaName = "Taxa Acumulada em Variações Sucessivas",
            formulaSyntax = "1 + i_acum = (1 ± i₁) · (1 ± i₂)   =>   i_acum = (1 ± i₁) · (1 ± i₂) - 1",
            formulaTerms = listOf(
                "i₁, i₂: Taxas percentuais sucessivas na forma decimal",
                "i_acum: Variação percentual única equivalente a toda a sequência"
            ),
            formulaWhenToUse = "Aplicar quando um valor sofre dois ou mais reajustes/descontos consecutivos ou quando um produto de duas grandezas (ex.: Preço × Consumo = Receita) sofre variações nas duas variáveis.",
            exampleTitle = "Variação na Despesa Mensal com Combustível",
            exampleProblem = "O preço do litro de óleo diesel subiu 25%. Para conter gastos, a gerência de frota reduziu o volume mensal consumido em 12%. Qual foi a variação percentual na despesa mensal total com óleo diesel?",
            exampleSteps = listOf(
                "1. Sabemos que Despesa = Preço × Volume.",
                "2. O fator do preço é 1 + 0,25 = 1,25; o fator do volume é 1 - 0,12 = 0,88.",
                "3. Multiplicar os fatores: F_despesa = 1,25 × 0,88 = (5/4) × 0,88 = 5 × 0,22 = 1,10.",
                "4. Como 1,10 - 1 = +0,10, houve aumento de 10% na despesa total."
            ),
            exampleAnswer = "A despesa mensal aumentou 10%.",
            goldTip = "Se não houver nenhum valor em reais no enunciado, adote a Base 100! Comece com R$ 100,00, aplique as porcentagens passo a passo e veja quanto deu no final em relação a 100."
        )

        "tp_mat_m6_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "As Equações do 1º Grau e a modelagem algébrica de problemas traduzem enunciados em língua portuguesa para expressões matemáticas. Na Cesgranrio, o desafio não é resolver a equação final, mas sim montá-la corretamente a partir da leitura atenta do texto.",
            theory = """
                • Equação Polinomial do 1º Grau:
                  - Forma reduzida: a·x + b = 0, com a ≠ 0, cuja raiz única é x = -b / a.
                • Dicionário de Tradução Algébrica:
                  - "Um número": x
                  - "O dobro de um número mais 5": 2x + 5 | "O dobro de (um número mais 5)": 2(x + 5)
                  - "A terça parte de um número": x/3
                  - "Três números inteiros consecutivos": x, x + 1, x + 2
                  - "A razão entre A e B é 3/5": A/B = 3/5 => A = 3k e B = 5k
                  - "A excede B em 40 unidades": A - B = 40 <=> A = B + 40
            """.trimIndent(),
            practicalApplication = "Um contrato de locação de guindaste cobra uma taxa fixa mensal de R$ 12.000,00 mais R$ 250,00 por hora de operação. Se a fatura de determinado mês fechou em R$ 34.500,00, basta resolver 12.000 + 250x = 34.500 => 250x = 22.500 => x = 90 horas operadas.",
            summary = "Defina claramente o que a incógnita x representa, traduza cada frase em uma relação algébrica e confira no final se a pergunta pede o próprio x ou uma expressão derivada dele.",
            formulaName = "Equação Linear do 1º Grau",
            formulaSyntax = "a · x + b = c   =>   x = (c - b) / a   (com a ≠ 0)",
            formulaTerms = listOf(
                "x: Incógnita do problema",
                "a: Coeficiente multiplicativo (taxa variável unitária)",
                "b: Termo independente ou parcela fixa inicial",
                "c: Total resultante informado no enunciado"
            ),
            formulaWhenToUse = "Aplicar em problemas de custos lineares, partilhas com diferenças fixas, idades e saldos operacionais.",
            exampleTitle = "Alocação de Técnicos entre Turnos Operacionais",
            exampleProblem = "Um terminal conta com 56 operadores divididos entre os turnos diurno e noturno. Se o turno diurno possui 12 operadores a mais do que o turno noturno, quantos operadores trabalham no turno diurno?",
            exampleSteps = listOf(
                "1. Chamar de x o número de operadores do turno noturno; logo, o turno diurno possui (x + 12) operadores.",
                "2. Somar os dois turnos: x + (x + 12) = 56 => 2x = 44 => x = 22 (turno noturno).",
                "3. Calcular o turno diurno: x + 12 = 22 + 12 = 34 operadores."
            ),
            exampleAnswer = "34 operadores trabalham no turno diurno.",
            goldTip = "Ao terminar de achar o valor de x, releia a última frase do enunciado antes de marcar o gabarito! No exemplo acima, o valor de x (22) estaria na letra A esperando quem esquecesse de somar 12."
        )

        "tp_mat_m6_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "As Equações do 2º Grau aparecem na Cesgranrio em problemas de áreas retangulares, rateios em que o valor individual depende do número de participantes e estudos de raízes pela fórmula de Bhaskara ou pelas Relações de Girard (Soma e Produto).",
            theory = """
                • Forma Geral da Equação do 2º Grau:
                  - a·x² + b·x + c = 0, com a ≠ 0.
                • Discriminante (Delta) e Fórmula de Bhaskara:
                  - Δ = b² - 4·a·c
                  - x = (-b ± √Δ) / (2a)
                  - Se Δ > 0: duas raízes reais e distintas (x₁ ≠ x₂).
                  - Se Δ = 0: duas raízes reais e iguais (uma única raiz real dupla: x₁ = x₂ = -b / 2a).
                  - Se Δ < 0: não possui raízes reais.
                • Relações de Girard (Soma e Produto das Raízes):
                  - Soma das raízes: S = x₁ + x₂ = -b / a
                  - Produto das raízes: P = x₁ · x₂ = c / a
            """.trimIndent(),
            practicalApplication = "Em um rateio de uma despesa de R$ 1.200,00 entre x funcionários, dois deles foram transferidos e a cota de cada um dos restantes subiu R$ 30,00: (1.200 / (x - 2)) - (1.200 / x) = 30. Isso gera a equação x² - 2x - 80 = 0, cujas raízes por Soma (2) e Produto (-80) são 10 e -8, revelando que havia 10 funcionários inicialmente.",
            summary = "Δ = b² - 4ac. Raízes reais exigem Δ ≥ 0. Para ganhar tempo quando a = 1, use Soma = -b e Produto = c para achar as raízes mentalmente!",
            formulaName = "Fórmula de Bhaskara e Relações de Soma e Produto",
            formulaSyntax = "x = (-b ± √(b² - 4ac)) / 2a   |   S = x₁ + x₂ = -b/a   |   P = x₁ · x₂ = c/a",
            formulaTerms = listOf(
                "a, b, c: Coeficientes reais da equação ax² + bx + c = 0 (com a ≠ 0)",
                "Δ = b² - 4ac: Discriminante que define a quantidade de raízes reais",
                "S e P: Soma e produto das duas raízes da equação"
            ),
            formulaWhenToUse = "Aplicar para encontrar as raízes de equações quadráticas, analisar condições de existência de raízes reais ou calcular expressões envolvendo x₁ e x₂ sem precisar achá-las separadamente.",
            exampleTitle = "Dimensionamento de Pátio Retangular de Tubos",
            exampleProblem = "Um pátio retangular de estocagem de tubos tem perímetro igual a 68 metros e área igual a 280 m². Qual é a medida do maior lado (comprimento) desse pátio?",
            exampleSteps = listOf(
                "1. Se o perímetro 2(x + y) = 68, então a soma dos lados é S = x + y = 34 m.",
                "2. A área é o produto dos lados: P = x · y = 280 m².",
                "3. Montar a equação t² - S·t + P = 0 => t² - 34t + 280 = 0.",
                "4. Dois números que somam 34 e multiplicam 280 são 14 e 20 (pois 14 + 20 = 34 e 14 × 20 = 280)."
            ),
            exampleAnswer = "O maior lado mede 20 metros.",
            goldTip = "Todo problema de retângulo que informa o Perímetro (2P) e a Área (A) cai direto em Soma e Produto: a soma dos lados é Metade do Perímetro (x + y = Perímetro/2) e o produto é a Área (x·y = A)!"
        )

        "tp_mat_m7_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "Sistemas Lineares de duas incógnitas são um clássico absoluto da Fundação Cesgranrio. Aparecem em problemas de compras de dois tipos de materiais, frotas com dois modelos de veículos, notas fiscais com diferentes alíquotas ou alocação de cilindros de capacidades distintas.",
            theory = """
                • Sistema Linear 2×2:
                  - { a₁x + b₁y = c₁
                    { a₂x + b₂y = c₂
                • Método da Substituição:
                  - Isola-se uma incógnita na equação mais simples (ex.: y = c₁ - a₁x) e substitui-se a expressão obtida na outra equação.
                • Método da Adição (Mais rápido em provas):
                  - Multiplicam-se os dois lados de uma das equações por um fator conveniente de modo que os coeficientes de uma das incógnitas fiquem simétricos (+k e -k). Somando as duas equações membro a membro, essa incógnita desaparece imediatamente.
                • Classificação:
                  - SPD (Sistema Possível e Determinado): solução única (retas concorrentes).
                  - SPI (Sistema Possível e Indeterminado): infinitas soluções (equações proporcionais / retas coincidentes).
                  - SI (Sistema Impossível): nenhuma solução (retas paralelas distintas).
            """.trimIndent(),
            practicalApplication = "Um almoxarifado armazenou 3.200 litros de solvente utilizando ao todo 22 tambores, sendo alguns de 100 litros e outros de 200 litros. Montando x + y = 22 e 100x + 200y = 3.200 (ou x + 2y = 32), subtrai-se a primeira da segunda e obtém-se de imediato y = 10 tambores de 200 L e x = 12 tambores de 100 L.",
            summary = "Uma equação quase sempre contabiliza a QUANTIDADE total de itens (x + y = Total_itens) e a outra contabiliza o VALOR, MASSA ou VOLUME total (p₁x + p₂y = Total_valor).",
            formulaName = "Sistema Linear Padrão Quantidade × Valor/Capacidade",
            formulaSyntax = "{ x + y = Q_total   e   { v₁·x + v₂·y = V_total",
            formulaTerms = listOf(
                "x, y: Quantidades de itens de cada um dos dois tipos",
                "Q_total: Quantidade total de unidades somadas",
                "v₁, v₂: Valor unitário, massa ou capacidade de cada tipo",
                "V_total: Valor financeiro, massa ou volume total acumulado"
            ),
            formulaWhenToUse = "Aplicar sempre que o enunciado apresentar duas categorias de objetos/pessoas e fornecer duas informações globais (total de unidades e total financeiro/físico).",
            exampleTitle = "Aquisição de EPIs de Duas Categorias",
            exampleProblem = "Para equipar uma equipe de manutenção, foram comprados ao todo 30 pares de botas de segurança, entre modelos padrão (R$ 150,00 o par) e modelos especiais antichama (R$ 220,00 o par), totalizando R$ 5.480,00. Quantos pares do modelo especial antichama foram comprados?",
            exampleSteps = listOf(
                "1. Sejam P os pares padrão e E os pares especiais: P + E = 30 e 150P + 220E = 5.480.",
                "2. Como queremos achar E, multiplicamos a 1ª equação por (-150) para eliminar P: -150P - 150E = -4.500.",
                "3. Somando com a 2ª equação: 70E = 980 => E = 980 / 70 = 14 pares."
            ),
            exampleAnswer = "Foram comprados 14 pares do modelo especial antichama.",
            goldTip = "No método da adição, elimine sempre a incógnita que a questão NÃO pediu! Assim você já descobre direto na primeira conta a incógnita que responde à questão."
        )

        "tp_mat_m7_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "Progressões Aritméticas (PA) e Progressões Geométricas (PG) modelam sequências de metas mensais, cronogramas de inspeção, depreciação de ativos e crescimento de demandas logísticas nas provas da Cesgranrio.",
            theory = """
                • Progressão Aritmética (PA — crescimento linear por adição constante da razão r):
                  - Razão: r = a₂ - a₁ = a₃ - a₂.
                  - Termo Geral: aₙ = a₁ + (n - 1) · r   (ou aₙ = aₖ + (n - k) · r).
                  - Soma dos n primeiros termos: Sₙ = ((a₁ + aₙ) · n) / 2.
                  - Propriedade central: em toda PA de três termos (a, b, c), o termo do meio é a média aritmética dos extremos: b = (a + c) / 2.
                • Progressão Geométrica (PG — crescimento exponencial por multiplicação constante da razão q):
                  - Razão: q = a₂ / a₁ = a₃ / a₂.
                  - Termo Geral: aₙ = a₁ · qⁿ⁻¹.
                  - Soma Finita (q ≠ 1): Sₙ = a₁ · (qⁿ - 1) / (q - 1).
                  - Limite da Soma Infinita (-1 < q < 1): S_∞ = a₁ / (1 - q).
            """.trimIndent(),
            practicalApplication = "Um terminal inicia uma campanha de digitalização arquivando 400 processos no 1º mês e aumentando a meta em 150 processos a cada mês (PA de razão r = 150). No 12º mês, arquivará a₁₂ = 400 + 11 × 150 = 2.050 processos, acumulando no ano S₁₂ = ((400 + 2.050) × 12) / 2 = 14.700 processos digitalizados.",
            summary = "Soma um valor fixo a cada passo -> PA (aₙ = a₁ + (n-1)r). Multiplica por um fator fixo a cada passo -> PG (aₙ = a₁·qⁿ⁻¹). Não confunda o valor no n-ésimo mês (aₙ) com o total acumulado até o n-ésimo mês (Sₙ)!",
            formulaName = "Termo Geral e Soma da PA e da PG",
            formulaSyntax = "PA: aₙ = a₁ + (n-1)r  e  Sₙ = (a₁+aₙ)n/2   |   PG: aₙ = a₁·qⁿ⁻¹  e  Sₙ = a₁(qⁿ-1)/(q-1)",
            formulaTerms = listOf(
                "a₁: Primeiro termo da sequência",
                "aₙ: Termo de posição n",
                "r e q: Razão aditiva da PA e razão multiplicativa da PG",
                "Sₙ: Soma acumulada dos n primeiros termos"
            ),
            formulaWhenToUse = "Usar PA quando a variação absoluta entre termos consecutivos for constante; usar PG quando a variação percentual ou a razão multiplicativa entre termos for constante.",
            exampleTitle = "Cronograma Progressivo de Manutenção de Válvulas",
            exampleProblem = "Uma equipe de manutenção revisou 18 válvulas na 1ª semana do trimestre e, a cada semana seguinte, revisou 6 válvulas a mais do que na semana imediatamente anterior. Ao final de 10 semanas, qual foi o total acumulado de válvulas revisadas?",
            exampleSteps = listOf(
                "1. Identificar uma PA com a₁ = 18, r = 6 e n = 10 semanas.",
                "2. Calcular o termo da 10ª semana: a₁₀ = 18 + (10 - 1) · 6 = 18 + 54 = 72 válvulas.",
                "3. Calcular a soma acumulada nas 10 semanas: S₁₀ = ((18 + 72) · 10) / 2 = 90 · 5 = 450 válvulas."
            ),
            exampleAnswer = "Foram revisadas 450 válvulas ao todo nas 10 semanas.",
            goldTip = "Em uma PG com poucos termos (ex.: 4 ou 5 termos), muitas vezes é mais rápido escrever os termos um a um multiplicando pela razão e somá-los no braço do que aplicar a fórmula de Sₙ!"
        )

        "tp_mat_m8_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "A Função do 1º Grau (Função Afim) modela todas as relações de variação linear: custos fixos e variáveis, depreciação contábil linear, nível de tanques sob vazão constante e análise de ponto de equilíbrio entre duas propostas comerciais.",
            theory = """
                • Lei de Formação da Função Afim:
                  - f(x) = a·x + b, com a, b ∈ R e a ≠ 0. O gráfico é sempre uma RETA.
                • Significado Prático dos Coeficientes:
                  - Coeficiente Linear (b): valor inicial para x = 0, onde a reta corta o eixo vertical y -> f(0) = b (custo fixo, estoque inicial).
                  - Coeficiente Angular (a): taxa de variação constante da função -> a = Δy / Δx = (y₂ - y₁) / (x₂ - x₁).
                    • Se a > 0: função estritamente CRESCENTE.
                    • Se a < 0: função estritamente DECRESCENTE.
                • Zero ou Raiz da Função:
                  - Valor de x que anula a função (onde a reta corta o eixo horizontal x): a·x + b = 0 => x = -b / a.
            """.trimIndent(),
            practicalApplication = "Ao comparar duas transportadoras — Transportadora A cobrando R$ 3.000,00 fixos + R$ 15,00 por km (C_A(x) = 15x + 3.000) e Transportadora B cobrando R$ 1.200,00 fixos + R$ 24,00 por km (C_B(x) = 24x + 1.200) —, o ponto de equilíbrio ocorre em 15x + 3.000 = 24x + 1.200 => 9x = 1.800 => x = 200 km.",
            summary = "Na função f(x) = ax + b, 'b' é o valor fixo inicial (x = 0) e 'a = Δy/Δx' é a taxa de variação por unidade adicional de x.",
            formulaName = "Lei da Função Afim e Taxa de Variação (Coeficiente Angular)",
            formulaSyntax = "f(x) = a·x + b   |   a = (y₂ - y₁) / (x₂ - x₁)   |   b = f(0)",
            formulaTerms = listOf(
                "a: Coeficiente angular (variação de y para cada aumento de 1 unidade em x)",
                "b: Coeficiente linear (ordenada onde a reta intercepta o eixo y)",
                "(x₁, y₁) e (x₂, y₂): Dois pontos quaisquer pertencentes à reta"
            ),
            formulaWhenToUse = "Aplicar sempre que o gráfico for um segmento de reta ou o enunciado afirmar que uma grandeza varia linearmente em função do tempo ou da produção.",
            exampleTitle = "Depreciação Linear de Equipamento Industrial",
            exampleProblem = "Um compressor adquirido novo por R$ 180.000,00 sofre depreciação linear e, após 6 anos de uso, seu valor contábil passa a ser R$ 72.000,00. Qual será o valor contábil desse compressor após 8 anos de uso?",
            exampleSteps = listOf(
                "1. Em t = 0, o valor inicial é b = 180.000.",
                "2. Calcular a taxa de variação anual (a): a = (72.000 - 180.000) / (6 - 0) = -108.000 / 6 = -R$ 18.000,00 por ano.",
                "3. Escrever a função V(t) = 180.000 - 18.000·t e calcular para t = 8: V(8) = 180.000 - 18.000 × 8 = 180.000 - 144.000 = R$ 36.000,00."
            ),
            exampleAnswer = "R$ 36.000,00.",
            goldTip = "Em questões com gráfico de reta na Cesgranrio, nunca tente adivinhar no olho: pegue os dois pontos dados, calcule a taxa a = Δy/Δx e descubra o valor exato de qualquer abscissa!"
        )

        "tp_mat_m8_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "A Função do 2º Grau (Quadrática) é a ferramenta favorita da Cesgranrio para problemas de Otimização (encontrar o lucro máximo, a receita máxima, a área máxima ou o custo operacional mínimo). Dominar as coordenadas do vértice da parábola é indispensável.",
            theory = """
                • Lei de Formação e Gráfico (Parábola):
                  - f(x) = a·x² + b·x + c, com a ≠ 0.
                  - Se a > 0: concavidade voltada para CIMA (possui ponto de MÍNIMO no vértice).
                  - Se a < 0: concavidade voltada para BAIXO (possui ponto de MÁXIMO no vértice).
                  - O termo independente c é onde a parábola corta o eixo y: f(0) = c.
                • Coordenadas do Vértice (V = (Xᵥ, Yᵥ)):
                  - Abscissa do vértice (Xᵥ): Xᵥ = -b / (2a)  ou  Xᵥ = (x₁ + x₂) / 2 (média aritmética das raízes por simetria!).
                  - Ordenada do vértice (Yᵥ): Yᵥ = -Δ / (4a)  ou simplesmente Yᵥ = f(Xᵥ) (substituindo Xᵥ na função!).
            """.trimIndent(),
            practicalApplication = "Se o custo unitário (em reais) de manutenção de um lote de x bombas é dado por C(x) = x² - 40x + 650, a quantidade de bombas que minimiza o custo unitário é Xᵥ = -(-40) / (2 · 1) = 20 bombas, e o custo unitário mínimo é C(20) = 20² - 40(20) + 650 = 400 - 800 + 650 = R$ 250,00.",
            summary = "Atenção à pergunta de otimização: se pedir 'QUAL A QUANTIDADE x que gera o máximo/mínimo', calcule Xᵥ = -b/(2a); se pedir 'QUAL É O VALOR MÁXIMO/MÍNIMO atingido', calcule Yᵥ = f(Xᵥ)!",
            formulaName = "Coordenadas do Vértice da Parábola (Otimização)",
            formulaSyntax = "Xᵥ = -b / (2a) = (x₁ + x₂) / 2   |   Yᵥ = f(Xᵥ) = -Δ / (4a)",
            formulaTerms = listOf(
                "Xᵥ: Valor da variável de entrada x que otimiza (maximiza ou minimiza) a função",
                "Yᵥ: Valor extremo (máximo se a < 0; mínimo se a > 0) assumido pela função f(x)",
                "x₁, x₂: Raízes reais da parábola (simétricas em relação a Xᵥ)"
            ),
            formulaWhenToUse = "Aplicar sempre que o enunciado trouxer uma função do 2º grau e pedir o ponto de máximo (lucro/receita/altura/área máxima) ou de mínimo (custo/desperdício mínimo).",
            exampleTitle = "Minimização de Custo Operacional em Terminal",
            exampleProblem = "O custo diário C(x), em milhares de reais, para operar x plataformas de carregamento em um terminal é modelado pela função C(x) = 2x² - 24x + 110. Qual é o custo diário mínimo de operação desse terminal?",
            exampleSteps = listOf(
                "1. Como a = 2 > 0, a parábola tem concavidade para cima e admite valor mínimo no vértice.",
                "2. Calcular o número de plataformas que minimiza o custo: Xᵥ = -(-24) / (2 · 2) = 24 / 4 = 6 plataformas.",
                "3. Substituir Xᵥ = 6 na função para obter o custo mínimo (Yᵥ): C(6) = 2·(6²) - 24·(6) + 110 = 72 - 144 + 110 = 38 milhares de reais."
            ),
            exampleAnswer = "R$ 38.000,00 (38 milhares de reais).",
            goldTip = "Nem precisa decorar Yᵥ = -Δ/(4a): calcule Xᵥ = -b/(2a), que é uma conta super simples, e substitua esse número no lugar do x na própria função f(Xᵥ)!"
        )

        "tp_mat_m9_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "As Funções e Equações Exponenciais modelam processos em que a variável está no expoente, como juros compostos, depreciação percentual contínua, crescimento de demanda energética e decaimento de pressão ou concentração química.",
            theory = """
                • Função Exponencial:
                  - f(x) = A₀ · bˣ, com base b > 0 e b ≠ 1.
                  - Se b > 1: função estritamente CRESCENTE (crescimento exponencial).
                  - Se 0 < b < 1: função estritamente DECRESCENTE (decaimento/depreciação exponencial).
                  - Para x = 0, temos f(0) = A₀ · b⁰ = A₀ (valor inicial).
                • Resolução de Equações Exponenciais:
                  - Reduzem-se ambos os membros da igualdade a potências de MESMA BASE e igualam-se os expoentes:
                    b^(f(x)) = b^(g(x))  <=>  f(x) = g(x)  (para b > 0 e b ≠ 1).
                • Potências Clássicas para Reconhecimento Rápido:
                  - Base 2: 2¹=2, 2²=4, 2³=8, 2⁴=16, 2⁵=32, 2⁶=64, 2⁷=128, 2⁸=256, 2⁹=512, 2¹⁰=1.024.
                  - Base 3: 3¹=3, 3²=9, 3³=27, 3⁴=81, 3⁵=243. | Base 5: 5²=25, 5³=125, 5⁴=625.
            """.trimIndent(),
            practicalApplication = "A pressão residual P(t), em bar, em uma tubulação isolada para teste de estanqueidade decai segundo a lei P(t) = 160 · (0,5)^(t/3), com t em horas. Para saber em quantas horas a pressão atingirá 20 bar, resolve-se 160 · (0,5)^(t/3) = 20 => (1/2)^(t/3) = 20/160 = 1/8 = (1/2)³ => t/3 = 3 => t = 9 horas.",
            summary = "Passe o valor inicial dividindo, fatore os números até obter a mesma base nos dois lados da equação e iguale os expoentes!",
            formulaName = "Equação Exponencial por Igualdade de Bases",
            formulaSyntax = "A₀ · b^(k·t) = A(t)   =>   b^(k·t) = A(t) / A₀ = bⁿ   =>   k·t = n",
            formulaTerms = listOf(
                "A₀: Valor inicial para t = 0",
                "A(t): Valor atingido no instante t",
                "b: Base da evolução exponencial (fator multiplicativo)",
                "k·t: Expoente dependente do tempo t"
            ),
            formulaWhenToUse = "Aplicar sempre que a incógnita estiver no expoente de uma lei de crescimento ou decaimento e as bases puderem ser reduzidas a um mesmo número primo.",
            exampleTitle = "Depreciação Exponencial de Ativo Operacional",
            exampleProblem = "O valor de mercado V(t) de um equipamento especial, t anos após sua compra, é dado por V(t) = 640.000 · (0,5)^(0,25·t). Após quantos anos de uso o valor desse equipamento será igual a R$ 80.000,00?",
            exampleSteps = listOf(
                "1. Igualar V(t) a 80.000: 640.000 · (0,5)^(0,25·t) = 80.000.",
                "2. Isolar a potência: (1/2)^(0,25·t) = 80.000 / 640.000 = 1/8.",
                "3. Escrever 1/8 na base 1/2: (1/2)^(0,25·t) = (1/2)³.",
                "4. Igualar os expoentes: 0,25·t = 3 => t = 3 / 0,25 = 12 anos."
            ),
            exampleAnswer = "Após 12 anos de uso.",
            goldTip = "Transforme decimais em frações antes de fatorar! Ver 0,5 como 1/2, 0,25 como 1/4, 0,125 como 1/8 e 0,2 como 1/5 torna a igualação de bases imediata."
        )

        "tp_mat_m9_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "Os Logaritmos são a operação inversa da exponenciação. Na prova da Cesgranrio, aparecem justamente quando a equação exponencial possui bases que não são potências exatas uma da outra (ex.: 1,05ᵗ = 2), exigindo o uso das propriedades operatórias e das aproximações fornecidas no enunciado.",
            theory = """
                • Definição e Condição de Existência:
                  - log_b(a) = x  <=>  bˣ = a   (com logaritmando a > 0, base b > 0 e b ≠ 1).
                  - Quando a base é omitida (log x), trata-se do logaritmo decimal (base 10: log₁₀(10ⁿ) = n).
                  - Consequências imediatas: log_b(1) = 0; log_b(b) = 1; log_b(bᵏ) = k.
                • Propriedades Operatórias Fundamentais:
                  1. Logaritmo do Produto (vira soma): log_b(M · N) = log_b(M) + log_b(N)
                  2. Logaritmo do Quociente (vira subtração): log_b(M / N) = log_b(M) - log_b(N)
                  3. Logaritmo da Potência (regra do "tombo"): log_b(Mᵏ) = k · log_b(M)
                  4. Mudança de Base: log_b(a) = log_c(a) / log_c(b)
            """.trimIndent(),
            practicalApplication = "Para descobrir em quantos meses um fundo de reserva aplicado a 2% ao mês (fator 1,02) dobra de valor, resolve-se 1,02ⁿ = 2. Aplicando log nos dois lados: n · log(1,02) = log(2). Usando os dados da questão (log 2 = 0,30 e log 1,02 = 0,0086), obtém-se n = 0,30 / 0,0086 ≈ 35 meses.",
            summary = "log_b(a) = x significa bˣ = a. Produto dentro do log vira soma; divisão vira subtração; expoente 'tomba' multiplicando na frente do log.",
            formulaName = "Propriedades Operatórias dos Logaritmos",
            formulaSyntax = "log(M·N) = log M + log N   |   log(M/N) = log M - log N   |   log(Mᵏ) = k · log M",
            formulaTerms = listOf(
                "M, N: Logaritmandos reais estritamente positivos (M > 0, N > 0)",
                "k: Expoente real que passa multiplicando o logaritmo",
                "log: Logaritmo em qualquer base válida b (b > 0 e b ≠ 1)"
            ),
            formulaWhenToUse = "Aplicar para isolar incógnitas que estão no expoente (usando a regra do tombo) ou para calcular o logaritmo de um número composto a partir de log 2 e log 3 fornecidos no enunciado.",
            exampleTitle = "Cálculo de Tempo com Auxílio de Logaritmos",
            exampleProblem = "O volume de dados processados por um servidor cresce segundo a função D(t) = D₀ · (1,2)ᵗ, com t em anos. Considerando log₁₀(2) = 0,30 e log₁₀(3) = 0,48, em quantos anos o volume de dados triplicará em relação ao volume inicial D₀?",
            exampleSteps = listOf(
                "1. Queremos D(t) = 3·D₀ => D₀ · (1,2)ᵗ = 3·D₀ => (1,2)ᵗ = 3.",
                "2. Escrever 1,2 como fração: 1,2 = 12/10 = (2² · 3) / 10.",
                "3. Aplicar log₁₀ nos dois lados: t · log(12/10) = log(3) => t · (2·log 2 + log 3 - log 10) = log 3.",
                "4. Substituir os valores: t · (2×0,30 + 0,48 - 1) = 0,48 => t · (0,08) = 0,48 => t = 0,48 / 0,08 = 6 anos."
            ),
            exampleAnswer = "O volume de dados triplicará em 6 anos.",
            goldTip = "Truque favorito da Cesgranrio: quando a questão dá apenas log(2) = 0,30 e você precisa de log(5), lembre-se de que 5 = 10/2! Logo, log(5) = log(10) - log(2) = 1 - 0,30 = 0,70!"
        )

        "tp_mat_m10_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "A Geometria Plana na Cesgranrio foca no cálculo de perímetros, áreas de polígonos e círculos e na aplicação do Teorema de Pitágoras em plantas de terrenos, faixas de servidão de dutos e pátios industriais.",
            theory = """
                • Teorema de Pitágoras (Triângulo Retângulo):
                  - a² = b² + c² (o quadrado da hipotenusa é igual à soma dos quadrados dos catetos).
                  - Ternos Pitagóricos Clássicos (poupe contas!): (3, 4, 5) e seus múltiplos (6, 8, 10), (9, 12, 15), (30, 40, 50); além de (5, 12, 13) e (8, 15, 17).
                • Formulário Essencial de Áreas Planas:
                  - Retângulo: A = base × altura (b · h) | Perímetro = 2(b + h).
                  - Quadrado de lado L: A = L² | Diagonal d = L√2.
                  - Triângulo: A = (b · h) / 2 | Triângulo Equilátero de lado L: A = (L²√3) / 4.
                  - Trapézio: A = ((B + b) · h) / 2 (média das bases × altura).
                  - Losango: A = (D · d) / 2 (produto das diagonais dividido por 2).
                  - Círculo de raio R: Área A = π · R² | Comprimento da circunferência C = 2 · π · R.
            """.trimIndent(),
            practicalApplication = "Para cercar e pavimentar uma área de segurança circular de raio R = 10 m ao redor de uma válvula de bloqueio (adotando π = 3,14), serão necessários C = 2 × 3,14 × 10 = 62,8 metros de tela de proteção e A = 3,14 × 10² = 314 m² de piso industrial.",
            summary = "Memorize o terno pitagórico (3, 4, 5), a área do trapézio ((B+b)h/2) e as fórmulas do círculo (C = 2πR e A = πR²), cuidando para não usar o diâmetro no lugar do raio!",
            formulaName = "Teorema de Pitágoras, Trapézio e Círculo",
            formulaSyntax = "a² = b² + c²   |   A_trapézio = ((B + b) · h) / 2   |   A_círculo = π · R²",
            formulaTerms = listOf(
                "a: Hipotenusa (lado oposto ao ângulo reto de 90°)",
                "b, c: Catetos do triângulo retângulo",
                "B, b, h: Base maior, base menor e altura do trapézio",
                "R: Raio do círculo (metade do diâmetro: R = D/2)"
            ),
            formulaWhenToUse = "Aplicar em cálculos de distâncias diagonais, metragens de cercas (perímetro) e áreas de terrenos ou chapas metálicas.",
            exampleTitle = "Cálculo de Área de Terreno Trapezoidal",
            exampleProblem = "Um terreno com formato de trapézio retângulo possui bases paralelas medindo 28 m e 16 m, e o lado oblíquo mede 20 m. Qual é a área total desse terreno em m²?",
            exampleSteps = listOf(
                "1. Traçar a altura h do trapézio, formando um triângulo retângulo de hipotenusa 20 m e cateto horizontal igual à diferença das bases: 28 - 16 = 12 m.",
                "2. Aplicar Pitágoras para achar a altura h: h² + 12² = 20² => h² = 400 - 144 = 256 => h = 16 m (terno 3-4-5 multiplicado por 4: 12-16-20).",
                "3. Calcular a área do trapézio: A = ((28 + 16) × 16) / 2 = 44 × 8 = 352 m²."
            ),
            exampleAnswer = "A área do terreno é de 352 m².",
            goldTip = "Em todo trapézio retângulo ou isósceles onde falta a altura, baixe uma perpendicular a partir do vértice da base menor: ela sempre forma um triângulo retângulo na ponta!"
        )

        "tp_mat_m10_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "A Geometria Espacial é um dos temas mais estratégicos para a prova da Transpetro: tanques cilíndricos de petróleo, esferas de GLP, contêineres (paralelepípedos) e bacias de contenção aparecem diretamente nas questões da Cesgranrio.",
            theory = """
                • Prismas e Paralelepípedo Reto-Retângulo (Bloco Retangular):
                  - Volume do Paralelepípedo: V = a · b · c (comprimento × largura × altura).
                  - Volume do Cubo de aresta a: V = a³ | Área total = 6·a².
                  - Volume de qualquer Prisma: V = Área da Base (A_b) × Altura (h).
                • Cilindro Circular Reto (Tanques de Combustível e Tubulações):
                  - Volume: V = π · R² · h (Área do círculo da base × altura/comprimento).
                  - Área Lateral: A_lat = 2 · π · R · h.
                • Sólidos Pontiagudos (Pirâmide e Cone) e Esfera:
                  - Volume da Pirâmide e do Cone (têm ponta -> divide por 3!): V = (A_b · h) / 3  =>  V_cone = (π · R² · h) / 3.
                  - Volume da Esfera (Tanques de GLP): V = (4 / 3) · π · R³ | Área da superfície esférica: A = 4 · π · R².
            """.trimIndent(),
            practicalApplication = "Um tanque cilíndrico vertical da Transpetro com diâmetro interno de 20 m (raio R = 10 m) e altura útil de 12 m (usando π = 3,14) possui volume V = 3,14 × 10² × 12 = 3.768 m³, comportando 3.768.000 litros de derivado.",
            summary = "Prismas e cilindros: V = Área da Base × altura. Pirâmides e cones: V = (Área da Base × altura) / 3. Esfera: V = (4/3)πR³. Se o enunciado der o DIÂMETRO, divida por 2 antes de elevar ao quadrado!",
            formulaName = "Volumes do Paralelepípedo, Cilindro, Cone e Esfera",
            formulaSyntax = "V_bloco = a·b·c   |   V_cilindro = π·R²·h   |   V_cone = (π·R²·h)/3   |   V_esfera = (4/3)·π·R³",
            formulaTerms = listOf(
                "a, b, c: Comprimento, largura e altura do bloco retangular",
                "R: Raio da base circular ou da esfera (R = Diâmetro / 2)",
                "h: Altura do cilindro ou do cone"
            ),
            formulaWhenToUse = "Aplicar no cálculo de capacidade volumétrica de tanques cilíndricos, reservatórios esféricos, silos cônicos e caixas retangulares.",
            exampleTitle = "Capacidade Operacional de Tanque Cilíndrico",
            exampleProblem = "Um tanque cilíndrico reto tem diâmetro da base igual a 8 metros e altura igual a 5 metros. Sabendo que o tanque está com 75% de sua capacidade total preenchida com óleo diesel e adotando π = 3, qual é o volume de óleo diesel, em litros, contido nesse tanque?",
            exampleSteps = listOf(
                "1. Determinar o raio da base: se o diâmetro é 8 m, o raio é R = 8 / 2 = 4 m.",
                "2. Calcular o volume total do cilindro: V_total = π · R² · h = 3 × 4² × 5 = 3 × 16 × 5 = 240 m³.",
                "3. Calcular 75% (3/4) do volume total: V_óleo = (3/4) × 240 = 180 m³.",
                "4. Converter para litros (×1.000): 180 × 1.000 = 180.000 litros."
            ),
            exampleAnswer = "180.000 litros.",
            goldTip = "A pegadinha nº 1 da Cesgranrio em Geometria Espacial é fornecer o DIÂMETRO (ex.: D = 8 m) para que o candidato distraído jogue 8² na fórmula do cilindro. Sublinhe a palavra 'diâmetro' e escreva 'R = 4' imediatamente!"
        )

        "tp_mat_m11_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "Análise Combinatória é presença carimbada nas provas da Cesgranrio. O ponto crucial é saber diferenciar quando a ordem de escolha dos elementos altera o agrupamento (Permutação e Arranjo) e quando a ordem não importa (Combinação — caso clássico de formação de comissões e equipes de plantão).",
            theory = """
                • Princípio Fundamental da Contagem (PFC — Regra do E / OU):
                  - Princípio Multiplicativo ("E"): decisões sucessivas e independentes -> MULTIPLICAM-SE as possibilidades.
                  - Princípio Aditivo ("OU"): casos mutuamente exclusivos -> SOMAM-SE as possibilidades.
                • Permutação Simples e com Repetição (usa TODOS os n elementos):
                  - Simples: Pₙ = n! = n · (n - 1) · ... · 2 · 1  (por definição, 0! = 1 e 1! = 1).
                  - Com repetição (anagramas): Pₙ^(a,b) = n! / (a! · b!).
                • Arranjo Simples vs. Combinação Simples (escolhe p elementos dentre n disponíveis):
                  - ARRANJO (A ORDEM IMPORTA — senhas, pódios, cargos distintos como Presidente e Secretário):
                    A(n, p) = n! / (n - p)!
                  - COMBINAÇÃO (A ORDEM NÃO IMPORTA — comissões, equipes de trabalho, grupos de inspeção):
                    C(n, p) = n! / [ p! · (n - p)! ]
            """.trimIndent(),
            practicalApplication = "Se um supervisor precisa escolher 3 técnicos dentre 8 disponíveis para formar uma comissão de auditoria (sem distinção de cargos), a ordem de escolha não altera a comissão -> Combinação: C(8, 3) = (8 × 7 × 6) / (3 × 2 × 1) = 56 comissões possíveis.",
            summary = "Inverteu a ordem dos escolhidos e mudou o resultado (senha, fila, cargos diferentes)? É ARRANJO/PFC! Inverteu a ordem e continuou sendo a mesma equipe/comissão? É COMBINAÇÃO (divide pelo fatorial de p)!",
            formulaName = "Arranjo Simples vs. Combinação Simples",
            formulaSyntax = "A(n, p) = n! / (n - p)!   (Ordem importa)   |   C(n, p) = n! / [p! · (n - p)!]   (Ordem não importa)",
            formulaTerms = listOf(
                "n: Total de elementos disponíveis para escolha",
                "p: Quantidade de elementos escolhidos para formar cada grupo",
                "p!: Fator de correção na Combinação que elimina as ordens repetidas"
            ),
            formulaWhenToUse = "Usar PFC/Arranjo para códigos, placas, filas e cargos diferenciados; usar Combinação para formar equipes, comissões, duplas ou subconjuntos homogêneos.",
            exampleTitle = "Formação de Equipe Mista de Plantão",
            exampleProblem = "Um setor conta com 6 técnicos de administração e 5 técnicos de logística. De quantas maneiras distintas é possível formar uma comissão de 5 pessoas composta por exatamente 3 técnicos de administração e 2 técnicos de logística?",
            exampleSteps = listOf(
                "1. Como em uma comissão a ordem dentro de cada especialidade não importa, usamos Combinação.",
                "2. Escolher 3 dentre 6 de administração: C(6, 3) = (6 × 5 × 4) / (3 × 2 × 1) = 20 formas.",
                "3. Escolher 2 dentre 5 de logística: C(5, 2) = (5 × 4) / (2 × 1) = 10 formas.",
                "4. Pelo Princípio Multiplicativo (3 de ADM 'E' 2 de LOG): 20 × 10 = 200 comissões."
            ),
            exampleAnswer = "200 comissões distintas.",
            goldTip = "Macete prático para calcular C(n, p) sem abrir fatoriais gigantes: abra 'p' fatores decrescentes no numerador a partir de n e divida por p! No exemplo C(8, 3), são 3 fatores em cima (8·7·6) divididos por (3·2·1) = 56!"
        )

        "tp_mat_m11_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "Probabilidade é questão quase certa na prova de Matemática da Cesgranrio. A banca explora a definição clássica de Laplace, a probabilidade da união de dois eventos, eventos independentes sucessivos (com ou sem reposição) e o poderoso atalho do Evento Complementar.",
            theory = """
                • Definição Clássica de Probabilidade (Espaço Amostral Equiprovável):
                  - P(A) = n(A) / n(Ω) = (Casos Favoráveis) / (Total de Casos Possíveis), sendo 0 ≤ P(A) ≤ 1 (ou 0% a 100%).
                • Probabilidade do Evento Complementar ("Pelo menos um"):
                  - P(Aᶜ) = 1 - P(A).
                  - Para calcular P("ocorrer pelo menos 1 vez"), faça: P = 1 - P("não ocorrer nenhuma vez").
                • Probabilidade da União de Dois Eventos ("A OU B"):
                  - P(A ∪ B) = P(A) + P(B) - P(A ∩ B).
                • Probabilidade da Interseção / Eventos Sucessivos ("A E B"):
                  - Eventos Independentes (ou com reposição): P(A ∩ B) = P(A) · P(B).
                  - Extração Sem Reposição: na 2ª retirada, diminuem 1 unidade no total do espaço amostral e, se for o caso, 1 unidade nos favoráveis.
            """.trimIndent(),
            practicalApplication = "Em um lote com 20 válvulas, das quais 4 apresentam defeito de vedação, se um inspetor escolhe 2 válvulas ao acaso sem reposição, a probabilidade de ambas serem defeituosas é (4/20) × (3/19) = (1/5) × (3/19) = 3/95.",
            summary = "Conectivo 'OU' soma as probabilidades descontando a interseção; conectivo 'E' multiplica as probabilidades sucessivas; expressão 'pelo menos um' resolve-se por 1 - P(nenhum).",
            formulaName = "Probabilidade Clássica, União, Interseção e Complementar",
            formulaSyntax = "P(A) = n(A)/n(Ω)   |   P(A∪B) = P(A)+P(B)-P(A∩B)   |   P(pelo menos 1) = 1 - P(nenhum)",
            formulaTerms = listOf(
                "n(A): Número de resultados favoráveis ao evento A",
                "n(Ω): Número total de resultados possíveis no espaço amostral",
                "P(A ∩ B): Probabilidade de ocorrência simultânea de A e B"
            ),
            formulaWhenToUse = "Aplicar em sorteios, controle estatístico de qualidade de lotes, falhas independentes de equipamentos e seleção aleatória de funcionários.",
            exampleTitle = "Confiabilidade de Sistema com Bombas em Paralelo",
            exampleProblem = "Duas bombas independentes (A e B) operam em paralelo em uma estação da Transpetro. A probabilidade de a bomba A falhar em um dia crítico é de 10%, e a probabilidade de a bomba B falhar no mesmo dia é de 20%. Qual é a probabilidade de que PELO MENOS UMA das duas bombas funcione normalmente nesse dia?",
            exampleSteps = listOf(
                "1. O evento complementar de 'pelo menos uma funcionar' é 'nenhuma funcionar' (ambas falharem simultaneamente).",
                "2. Como as falhas são independentes: P(ambas falharem) = P(falha A) × P(falha B) = 0,10 × 0,20 = 0,02 (2%).",
                "3. Logo, P(pelo menos uma funcionar) = 1 - 0,02 = 0,98 = 98%."
            ),
            exampleAnswer = "98% (ou 0,98).",
            goldTip = "Toda vez que ler 'pelo menos um' ou 'ao menos um' em uma questão de probabilidade da Cesgranrio, não abra vários casos: calcule 100% menos a probabilidade de NENHUM ocorrer!"
        )

        "tp_mat_m11_a3" -> buildBundle(
            lessonId = lessonId,
            intro = "A Probabilidade Condicional e as Tabelas de Contingência (dupla entrada) são um formato predileto da Fundação Cesgranrio. Quando o enunciado informa uma condição já conhecida ('Sabendo-se que...', 'Escolhido um funcionário do setor X...'), o espaço amostral deixa de ser o total geral e passa a ser apenas o subgrupo da condição!",
            theory = """
                • Conceito de Probabilidade Condicional P(A | B):
                  - Lê-se: "probabilidade de ocorrer o evento A, DADO QUE (sabendo-se que) o evento B já ocorreu".
                  - Fórmula formal: P(A | B) = P(A ∩ B) / P(B) = n(A ∩ B) / n(B).
                • Interpretação Direta por Redução do Espaço Amostral:
                  - Não é preciso dividir duas probabilidades: basta olhar apenas para o universo restrito B!
                  - O NOVO DENOMINADOR é o total de elementos da condição conhecida n(B).
                  - O NOVO NUMERADOR é a quantidade de elementos que pertencem simultaneamente a A e a B, isto é, n(A ∩ B).
                • Leitura de Tabelas de Contingência (Linhas × Colunas):
                  - Se a condição dada fixa uma LINHA da tabela, o denominador será o total daquela linha; se fixa uma COLUNA, o denominador será o total daquela coluna.
            """.trimIndent(),
            practicalApplication = "Em uma tabela de 200 funcionários divididos por turno (Manhã/Noite) e cargo (Técnico/Engenheiro), há 120 funcionários no turno da Manhã, dos quais 90 são Técnicos. Sorteando um funcionário e SABENDO-SE que ele trabalha no turno da Manhã, a probabilidade de ser Técnico é simplesmente 90 / 120 = 3/4 = 75%.",
            summary = "Na probabilidade condicional P(A | B), o que vem depois do 'sabendo-se que / dado que' (evento B) vira o seu NOVO DENOMINADOR, e a interseção (A ∩ B) vai no numerador!",
            formulaName = "Fórmula da Probabilidade Condicional",
            formulaSyntax = "P(A | B) = n(A ∩ B) / n(B) = P(A ∩ B) / P(B)",
            formulaTerms = listOf(
                "P(A | B): Probabilidade de A ocorrer sabendo que B ocorreu",
                "n(B): Total de elementos do grupo condicionante B (novo espaço amostral reduzido)",
                "n(A ∩ B): Elementos que satisfazem simultaneamente o evento A e a condição B"
            ),
            formulaWhenToUse = "Aplicar sempre que o enunciado restringir o universo por expressões como 'Sabendo-se que...', 'Dado que...', 'Dentre os que possuem...', 'Se o item sorteado é...'.",
            exampleTitle = "Auditoria de Notas Fiscais em Tabela de Dupla Entrada",
            exampleProblem = "Em um lote de 300 notas fiscais auditadas, 180 são de serviços e 120 são de materiais. Constatou-se que 27 notas de serviços e 13 notas de materiais continham erro de preenchimento. Escolhida ao acaso uma nota fiscal COM ERRO de preenchimento, qual é a probabilidade de ela ser uma nota de serviços?",
            exampleSteps = listOf(
                "1. Identificar a condição dada (novo denominador): 'escolhida uma nota COM ERRO'. Total de notas com erro = 27 + 13 = 40 notas.",
                "2. Identificar os casos favoráveis dentro desse grupo (interseção 'com erro E de serviços'): 27 notas.",
                "3. Calcular a probabilidade condicional: P(Serviços | Erro) = 27 / 40 = 0,675 = 67,5%."
            ),
            exampleAnswer = "27/40 (ou 67,5%).",
            goldTip = "Cuidado para não inverter o denominador! Compare as duas frases: 'Escolhida uma nota de serviços, qual a chance de ter erro?' (27/180) vs. 'Escolhida uma nota com erro, qual a chance de ser de serviços?' (27/40)."
        )

        "tp_mat_m12_a1" -> buildBundle(
            lessonId = lessonId,
            intro = "As Medidas de Tendência Central — Média Aritmética Simples, Média Aritmética Ponderada, Mediana e Moda — encerram o bloco analítico da prova de Matemática da Cesgranrio e são cobradas tanto em contas diretas quanto em questões conceituais.",
            theory = """
                • Média Aritmética Simples (x̄):
                  - Soma de todos os n valores dividida por n: x̄ = (x₁ + x₂ + ... + xₙ) / n.
                  - Dica operacional: a soma dos termos é sempre Soma = n · x̄.
                • Média Aritmética Ponderada (x̄_p):
                  - Cada valor xᵢ é multiplicado pelo seu respectivo peso ou frequência pᵢ e o total é dividido pela SOMA DOS PESOS:
                    x̄_p = (x₁·p₁ + x₂·p₂ + ... + xₖ·pₖ) / (p₁ + p₂ + ... + pₖ).
                • Mediana (Md):
                  - É o valor que ocupa a posição central após colocarmos os dados em ROL (ordem crescente ou decrescente!).
                  - Se n é ÍMPAR: a mediana é o termo central único na posição (n + 1)/2.
                  - Se n é PAR: a mediana é a média aritmética dos dois termos centrais nas posições (n/2) e (n/2 + 1).
                • Moda (Mo):
                  - É o valor mais frequente (que mais se repete) na distribuição.
            """.trimIndent(),
            practicalApplication = "Se os tempos de atendimento (em minutos) de 6 chamados foram {18, 12, 25, 14, 90, 16}, colocamos primeiro em ROL: {12, 14, 16, 18, 25, 90}. Como n = 6 é par, a mediana é a média do 3º e 4º termos: Md = (16 + 18)/2 = 17 min (muito mais representativa que a média simples de 29,17 min, que foi distorcida pelo outlier 90 min).",
            summary = "Média Ponderada divide pela SOMA DOS PESOS. Para achar a Mediana, ORDENE O ROL PRIMEIRO (n ímpar -> termo do meio; n par -> média dos dois do meio). Moda é o valor que mais se repete.",
            formulaName = "Média Simples, Média Ponderada e Posição da Mediana",
            formulaSyntax = "x̄ = (∑xᵢ)/n   |   x̄_p = ∑(xᵢ·pᵢ) / ∑pᵢ   |   Md (n par no rol) = (x_(n/2) + x_(n/2 + 1)) / 2",
            formulaTerms = listOf(
                "xᵢ: Valores observados na amostra",
                "pᵢ: Peso ou frequência absoluta de cada valor xᵢ",
                "n: Quantidade total de elementos da amostra"
            ),
            formulaWhenToUse = "Aplicar para resumir conjuntos de dados operacionais, descobrir um valor faltante a partir da média conhecida ou encontrar o valor central (mediana).",
            exampleTitle = "Cálculo de Salário Médio Ponderado por Cargo",
            exampleProblem = "Em uma equipe de apoio administrativo, 6 assistentes recebem R$ 4.000,00 cada, 3 analistas recebem R$ 7.000,00 cada e 1 supervisor recebe R$ 10.000,00. Qual é a média salarial e qual é a mediana salarial dessa equipe de 10 profissionais?",
            exampleSteps = listOf(
                "1. Calcular a Média Ponderada pelos 10 funcionários: x̄_p = (6×4.000 + 3×7.000 + 1×10.000) / (6 + 3 + 1) = (24.000 + 21.000 + 10.000) / 10 = R$ 5.500,00.",
                "2. Para a Mediana de n = 10 dados em rol, tomamos a média do 5º e do 6º termos.",
                "3. Como os 6 primeiros termos do rol são todos iguais a R$ 4.000,00, tanto o 5º quanto o 6º termo valem R$ 4.000,00. Logo, Md = R$ 4.000,00."
            ),
            exampleAnswer = "Média salarial de R$ 5.500,00 e mediana salarial de R$ 4.000,00.",
            goldTip = "Nunca calcule a Mediana com os números na ordem em que vieram no enunciado! A Cesgranrio sempre entrega a lista embaralhada para pegar quem toma o termo do meio sem ordenar em rol."
        )

        "tp_mat_m12_a2" -> buildBundle(
            lessonId = lessonId,
            intro = "A leitura e interpretação de Tabelas de Frequências e Gráficos Estatísticos (barras, colunas, linhas e setores/pizza), aliada às noções de dispersão, fecha o currículo de Matemática da Transpetro.",
            theory = """
                • Tabelas de Distribuição de Frequências:
                  - Frequência Absoluta (fᵢ): número de ocorrências de cada classe ou valor. A soma de todas as fᵢ é o tamanho total da amostra N.
                  - Frequência Relativa (frᵢ): participação percentual ou unitária de cada classe -> frᵢ = fᵢ / N (a soma total é 1 ou 100%).
                  - Frequência Acumulada (Fᵢ): soma das frequências até aquela linha (muito útil para localizar a posição da mediana!).
                • Gráficos de Setores (Gráfico de Pizza — 360°):
                  - O ângulo central α de cada setor é diretamente proporcional à sua frequência relativa:
                    α / 360° = fᵢ / N = frᵢ  =>  α = frᵢ × 360°  (sendo 10% = 36°, 25% = 90°, 50% = 180°).
                • Noções de Dispersão (Regularidade / Homogeneidade):
                  - Amplitude Total: A = Valor_máximo - Valor_mínimo.
                  - Variância e Desvio-Padrão: medem o grau de afastamento dos dados em relação à média. Quanto MENOR o desvio-padrão, MAIS REGULAR/HOMOGÊNEO é o desempenho da série!
            """.trimIndent(),
            practicalApplication = "Em um relatório gerencial em formato de gráfico de setores (360°), se as despesas de manutenção preventiva correspondem a um setor de 108°, a participação percentual dessa rubrica no orçamento é 108° / 360° = 0,30 = 30%.",
            summary = "No gráfico de setores, 360° equivalem a 100% (cada 1% = 3,6° e cada 10% = 36°). Em perguntas sobre qual equipe é 'mais regular ou homogênea', procure a que apresenta o MENOR desvio-padrão ou menor amplitude!",
            formulaName = "Proporcionalidade no Gráfico de Setores e Amplitude",
            formulaSyntax = "Ângulo (α) / 360° = Parte / Total = Taxa (%) / 100%   |   Amplitude = X_máx - X_mín",
            formulaTerms = listOf(
                "α: Ângulo central do setor circular em graus",
                "Parte / Total: Frequência relativa da categoria analisada",
                "X_máx e X_mín: Maior e menor valor observado na série estatística"
            ),
            formulaWhenToUse = "Aplicar na conversão entre ângulos de gráficos de pizza, porcentagens e valores absolutos, bem como na comparação da regularidade entre séries.",
            exampleTitle = "Conversão de Ângulo em Gráfico de Setores Orçamentário",
            exampleProblem = "Um gráfico de setores representa a distribuição dos 400 contratos ativos de uma diretoria da Transpetro. Se o setor correspondente aos contratos de transporte dutoviário possui ângulo central de 126°, quantos contratos ativos pertencem a essa categoria?",
            exampleSteps = listOf(
                "1. Montar a proporção entre o ângulo do setor e o círculo completo (360°): x / 400 = 126° / 360°.",
                "2. Simplificar 126 / 360 dividindo por 18: 126 ÷ 18 = 7 e 360 ÷ 18 = 20 => fração 7/20 (35%).",
                "3. Calcular x: x = 400 × (7 / 20) = 20 × 7 = 140 contratos."
            ),
            exampleAnswer = "140 contratos pertencem à categoria de transporte dutoviário.",
            goldTip = "Para achar a Mediana em uma Tabela de Frequências, nunca tire a mediana da coluna das frequências! Calcule a frequência acumulada e descubra em qual linha cai a posição central (N+1)/2."
        )

        else -> null
    }

    fun getExercises(lessonId: String): List<MathExercise> = when (lessonId) {

        "tp_mat_m1_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-01",
                lessonId = lessonId,
                statement = "Em uma pesquisa realizada com os 120 empregados de um terminal da Transpetro sobre a fluência nos idiomas inglês e espanhol, constatou-se que 75 empregados falam inglês, 48 falam espanhol e 18 não falam nenhum desses dois idiomas. O número de empregados desse terminal que falam APENAS inglês é:",
                options = listOf(
                    "A) 37",
                    "B) 48",
                    "C) 54",
                    "D) 57",
                    "E) 62"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Descontar do total os 18 empregados que não falam nenhum dos dois idiomas: n(I ∪ E) = 120 - 18 = 102 empregados.",
                    "2. Aplicar o Princípio da Inclusão-Exclusão: n(I ∪ E) = n(I) + n(E) - n(I ∩ E) => 102 = 75 + 48 - n(I ∩ E).",
                    "3. Calcular a interseção (ambos os idiomas): n(I ∩ E) = 123 - 102 = 21 empregados.",
                    "4. Calcular os que falam APENAS inglês: n(I - E) = n(I) - n(I ∩ E) = 75 - 21 = 54 empregados."
                ),
                finalComment = "Subtraindo a interseção (21) do total que fala inglês (75), obtêm-se 54 empregados que falam exclusivamente inglês (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-02",
                lessonId = lessonId,
                statement = "Três módulos do sistema corporativo (Compras, Estoque e Contratos) são utilizados por uma equipe administrativa. Sabe-se que 30 técnicos utilizam o módulo de Compras, 28 utilizam Estoque, 25 utilizam Contratos, 12 utilizam Compras e Estoque, 10 utilizam Compras e Contratos, 9 utilizam Estoque e Contratos, e 5 técnicos utilizam todos os três módulos. Se todos os técnicos da equipe utilizam pelo menos um módulo, quantos técnicos compõem essa equipe?",
                options = listOf(
                    "A) 47",
                    "B) 52",
                    "C) 57",
                    "D) 62",
                    "E) 67"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Aplicar a fórmula da inclusão-exclusão para 3 conjuntos: n(A ∪ B ∪ C) = n(A) + n(B) + n(C) - n(A∩B) - n(A∩C) - n(B∩C) + n(A∩B∩C).",
                    "2. Soma dos conjuntos individuais: 30 + 28 + 25 = 83.",
                    "3. Subtração das interseções dois a dois: 83 - (12 + 10 + 9) = 83 - 31 = 52.",
                    "4. Soma da interseção tripla: 52 + 5 = 57 técnicos."
                ),
                finalComment = "Pelo Princípio da Inclusão-Exclusão para três conjuntos, o total de elementos da união é 83 - 31 + 5 = 57 técnicos (Gabarito C)."
            )
        )

        "tp_mat_m1_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-03",
                lessonId = lessonId,
                statement = "Considere os intervalos reais A = [-3, 8[ e B = ]2, 11], que representam faixas de variação de temperatura (em °C) em duas câmaras de teste. A quantidade de números INTEIROS que pertencem ao conjunto interseção A ∩ B é igual a:",
                options = listOf(
                    "A) 5",
                    "B) 6",
                    "C) 7",
                    "D) 8",
                    "E) 15"
                ),
                correctIndex = 0,
                steps = listOf(
                    "1. Determinar o intervalo de interseção A ∩ B: o limite inferior é ]2 (aberto em 2, pois 2 ∉ B) e o limite superior é 8[ (aberto em 8, pois 8 ∉ A).",
                    "2. Assim, A ∩ B = ]2, 8[.",
                    "3. Listar os números inteiros estritamente maiores que 2 e estritamente menores que 8: {3, 4, 5, 6, 7}, totalizando 5 números inteiros."
                ),
                finalComment = "Como ambos os extremos de ]2, 8[ são abertos, pertencem à interseção apenas os inteiros 3, 4, 5, 6 e 7, perfazendo 5 valores (Gabarito A)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-04",
                lessonId = lessonId,
                statement = "O valor da expressão numérica obtida pela soma da dízima periódica 0,555... com a fração 7/9, dividida por 0,333..., é um número pertencente ao conjunto dos:",
                options = listOf(
                    "A) irracionais positivos.",
                    "B) inteiros negativos.",
                    "C) naturais e igual a 4.",
                    "D) racionais não inteiros e igual a 4/3.",
                    "E) naturais e igual a 3."
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Converter as dízimas periódicas simples em suas frações geratrizes: 0,555... = 5/9 e 0,333... = 3/9 = 1/3.",
                    "2. Calcular a soma do numerador: 5/9 + 7/9 = 12/9 = 4/3.",
                    "3. Dividir o resultado por 0,333... (1/3): (4/3) ÷ (1/3) = (4/3) × (3/1) = 4.",
                    "4. O número 4 é um número natural (e consequentemente inteiro, racional e real)."
                ),
                finalComment = "Convertendo as dízimas em frações geratrizes de denominador 9, obtém-se (12/9) ÷ (3/9) = 12/3 = 4, que é natural (Gabarito C)."
            )
        )

        "tp_mat_m2_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-05",
                lessonId = lessonId,
                statement = "Uma bacia de contenção de formato retangular possui volume interno de 72 m³. Se o volume contido nessa bacia corresponde a 65% de sua capacidade máxima, quantos litros faltam para atingir a capacidade total da bacia?",
                options = listOf(
                    "A) 2.520 L",
                    "B) 4.680 L",
                    "C) 25.200 L",
                    "D) 35.000 L",
                    "E) 46.800 L"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Se a bacia está com 65% de sua capacidade, faltam 100% - 65% = 35% para atingir a capacidade total.",
                    "2. Calcular 35% de 72 m³: 0,35 × 72 = 25,2 m³.",
                    "3. Converter metros cúbicos para litros (1 m³ = 1.000 L): 25,2 × 1.000 = 25.200 litros."
                ),
                finalComment = "Restam 35% de 72 m³ = 25,2 m³, que multiplicados por 1.000 equivalem a 25.200 L (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-06",
                lessonId = lessonId,
                statement = "Em uma planta de engenharia desenhada na escala 1 : 2.500, o trecho retilíneo de um duto mede 18 cm. O comprimento real desse trecho de duto, em metros, é igual a:",
                options = listOf(
                    "A) 45 m",
                    "B) 180 m",
                    "C) 250 m",
                    "D) 450 m",
                    "E) 4.500 m"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Pela definição de escala 1 : 2.500, cada 1 cm no desenho corresponde a 2.500 cm na realidade.",
                    "2. Calcular a medida real em centímetros: 18 cm × 2.500 = 45.000 cm.",
                    "3. Converter centímetros para metros (dividir por 100): 45.000 / 100 = 450 metros."
                ),
                finalComment = "Multiplicando 18 cm pelo fator de escala 2.500 e dividindo por 100 para converter em metros, obtêm-se 450 m (Gabarito D)."
            )
        )

        "tp_mat_m2_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-07",
                lessonId = lessonId,
                statement = "Uma bomba de recalque transfere água industrial a uma vazão constante de 150 litros por segundo. Funcionando ininterruptamente durante 2 horas e 20 minutos, o volume total transferido por essa bomba, em metros cúbicos (m³), será igual a:",
                options = listOf(
                    "A) 330 m³",
                    "B) 900 m³",
                    "C) 1.080 m³",
                    "D) 1.260 m³",
                    "E) 1.320 m³"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Converter o tempo total para minutos: 2 h 20 min = 120 + 20 = 140 minutos.",
                    "2. Converter o tempo para segundos: 140 min × 60 s/min = 8.400 segundos.",
                    "3. Calcular o volume em litros: V = 150 L/s × 8.400 s = 1.260.000 litros.",
                    "4. Converter litros para m³ (dividir por 1.000): 1.260.000 / 1.000 = 1.260 m³."
                ),
                finalComment = "Em 8.400 segundos, a bomba transfere 1.260.000 L, equivalentes a 1.260 m³ (Gabarito D)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-08",
                lessonId = lessonId,
                statement = "Uma inspeção técnica iniciada às 08h 45min teve duração exata de 3,4 horas. O horário exato de término dessa inspeção foi:",
                options = listOf(
                    "A) 11h 85min",
                    "B) 12h 05min",
                    "C) 12h 09min",
                    "D) 12h 15min",
                    "E) 12h 25min"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Converter 3,4 horas em horas e minutos: 3 horas inteiras + (0,4 × 60) minutos = 3 h 24 min.",
                    "2. Somar ao horário inicial: 08h 45min + 3h 24min = 11h 69min.",
                    "3. Como 69 min = 1 h + 09 min, o horário final é 12h 09min."
                ),
                finalComment = "A parte decimal 0,4 h equivale a 0,4 × 60 = 24 minutos; somando 3h 24min a 08h 45min, obtém-se 12h 09min (Gabarito C)."
            )
        )

        "tp_mat_m3_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-09",
                lessonId = lessonId,
                statement = "No centro de controle da Transpetro, três sistemas automatizados emitem relatórios de telemetria a cada 18 minutos, 24 minutos e 30 minutos, respectivamente. Se os três sistemas emitiram relatórios simultaneamente às 07h 00min, o próximo horário em que os três voltarão a emitir relatórios ao mesmo tempo será às:",
                options = listOf(
                    "A) 10h 00min",
                    "B) 11h 30min",
                    "C) 12h 00min",
                    "D) 13h 00min",
                    "E) 14h 20min"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Calcular o MMC(18, 24, 30) para encontrar o período de coincidência dos ciclos.",
                    "2. Fatorando: 18 = 2 · 3²; 24 = 2³ · 3; 30 = 2 · 3 · 5.",
                    "3. MMC = 2³ · 3² · 5 = 8 · 9 · 5 = 360 minutos.",
                    "4. Converter 360 minutos para horas: 360 / 60 = 6 horas. Somando a 07h 00min: 07h + 6h = 13h 00min."
                ),
                finalComment = "O MMC entre 18, 24 e 30 minutos é 360 minutos (6 horas), levando a próxima coincidência para as 13h 00min (Gabarito D)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-10",
                lessonId = lessonId,
                statement = "Um almoxarife precisa dividir três rolos de cabos de aço de comprimentos 144 m, 180 m e 252 m em pedaços de mesmo comprimento, sendo esse comprimento o maior possível, sem que haja sobra em nenhum dos rolos. O número TOTAL de pedaços obtidos após o corte dos três rolos será:",
                options = listOf(
                    "A) 12",
                    "B) 16",
                    "C) 18",
                    "D) 24",
                    "E) 36"
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. Encontrar o comprimento máximo de cada pedaço pelo MDC(144, 180, 252).",
                    "2. Fatorando: 144 = 2⁴ · 3²; 180 = 2² · 3² · 5; 252 = 2² · 3² · 7.",
                    "3. O MDC é 2² · 3² = 4 · 9 = 36 metros (comprimento de cada pedaço).",
                    "4. Calcular a quantidade total de pedaços: (144/36) + (180/36) + (252/36) = 4 + 5 + 7 = 16 pedaços."
                ),
                finalComment = "Cada pedaço medirá MDC = 36 m, resultando em 4 + 5 + 7 = 16 pedaços ao todo (Gabarito B)."
            )
        )

        "tp_mat_m3_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-11",
                lessonId = lessonId,
                statement = "Do lote total de luvas de proteção recebido por um terminal, 1/4 foi enviado ao setor de manutenção na segunda-feira. Na terça-feira, foram enviados 3/5 DO RESTANTE ao setor de operação, sobrando ainda 180 pares de luvas no almoxarifado. A quantidade total de pares de luvas recebida inicialmente nesse lote foi de:",
                options = listOf(
                    "A) 360",
                    "B) 450",
                    "C) 540",
                    "D) 600",
                    "E) 720"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Após enviar 1/4 na segunda-feira, restaram 3/4 do lote.",
                    "2. Na terça-feira, enviou-se 3/5 do restante, logo sobraram 2/5 desse restante: (2/5) × (3/4) = 6/20 = 3/10 do lote total.",
                    "3. Igualar a fração final ao saldo físico: (3/10) · Total = 180 => Total = 180 × 10 / 3 = 600 pares."
                ),
                finalComment = "Acompanhando as frações remanescentes ((3/4) × (2/5) = 3/10), conclui-se que 3/10 do total equivalem a 180, logo o lote inicial tinha 600 pares (Gabarito D)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-12",
                lessonId = lessonId,
                statement = "Simplificando a expressão numérica (10⁻³ · 0,0002 · 10⁸) / (4 · 10⁻²), obtém-se como resultado:",
                options = listOf(
                    "A) 0,5",
                    "B) 5",
                    "C) 50",
                    "D) 500",
                    "E) 5.000"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Escrever 0,0002 em potência de 10: 0,0002 = 2 · 10⁻⁴.",
                    "2. Simplificar o numerador: 10⁻³ · 2 · 10⁻⁴ · 10⁸ = 2 · 10⁻³⁻⁴⁺⁸ = 2 · 10¹ = 20.",
                    "3. Dividir pelo denominador (4 · 10⁻²): (20 / 4) · 10² = 5 · 100 = 500."
                ),
                finalComment = "Aplicando as propriedades de potências de base 10, obtém-se (2 · 10¹) / (4 · 10⁻²) = 0,5 · 10³ = 500 (Gabarito D)."
            )
        )

        "tp_mat_m4_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-13",
                lessonId = lessonId,
                statement = "Três bases operacionais (X, Y e Z) receberão um lote total de 360 rádios comunicadores, divididos em partes diretamente proporcionais ao número de brigadistas de cada base: 15, 20 e 25 brigadistas, respectivamente. Quantos rádios a base Z receberá a mais do que a base X?",
                options = listOf(
                    "A) 30",
                    "B) 45",
                    "C) 60",
                    "D) 90",
                    "E) 150"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Simplificar os pesos (15, 20, 25) dividindo por 5: obtemos (3, 4, 5).",
                    "2. Somar as cotas: 3k + 4k + 5k = 360 => 12k = 360 => k = 30 rádios por cota.",
                    "3. Calcular a diferença entre Z (5k) e X (3k): Z - X = 5k - 3k = 2k = 2 × 30 = 60 rádios."
                ),
                finalComment = "A base X recebe 3×30 = 90 rádios e a base Z recebe 5×30 = 150 rádios; a diferença é de 60 rádios (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-14",
                lessonId = lessonId,
                statement = "Uma verba de incentivo de R$ 26.000,00 foi dividida entre três setores em partes INVERSAMENTE proporcionais ao tempo médio de resposta a chamados (2 horas, 3 horas e 4 horas). O setor mais rápido (2 horas) recebeu a quantia de:",
                options = listOf(
                    "A) R$ 6.000,00",
                    "B) R$ 8.000,00",
                    "C) R$ 10.000,00",
                    "D) R$ 12.000,00",
                    "E) R$ 14.000,00"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Montar a divisão inversamente proporcional a 2, 3 e 4: k/2 + k/3 + k/4 = 26.000.",
                    "2. O MMC(2, 3, 4) é 12: (6k + 4k + 3k) / 12 = 26.000 => 13k / 12 = 26.000 => k = 24.000.",
                    "3. Calcular a parte do setor de 2 horas: k / 2 = 24.000 / 2 = R$ 12.000,00."
                ),
                finalComment = "As partes são proporcionais a 6, 4 e 3 (soma 13); cabem ao setor mais rápido 6/13 de R$ 26.000,00 = R$ 12.000,00 (Gabarito D)."
            )
        )

        "tp_mat_m4_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-15",
                lessonId = lessonId,
                statement = "Seis bombas idênticas, operando 8 horas por dia, transferem 3.600 m³ de derivado em 5 dias. Quantos dias serão necessários para que 4 dessas bombas, operando 10 horas por dia, transfiram 4.500 m³ do mesmo derivado?",
                options = listOf(
                    "A) 6",
                    "B) 7",
                    "C) 7,5",
                    "D) 8",
                    "E) 9"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Aplicar a fórmula Processo / Produto: (Bombas · Horas/dia · Dias) / Volume = constante.",
                    "2. Substituir os dados: (6 · 8 · 5) / 3.600 = (4 · 10 · d) / 4.500.",
                    "3. Simplificar: 240 / 3.600 = 40d / 4.500 => 1 / 15 = 4d / 450 => 4d = 30 => d = 7,5 dias."
                ),
                finalComment = "Pela proporcionalidade composta entre bombas, jornada diária e volume bombeado, são necessários 7,5 dias (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-16",
                lessonId = lessonId,
                statement = "Uma equipe de 8 técnicos conclui a auditoria de um almoxarifado em 15 dias, trabalhando no mesmo ritmo. Se a gerência precisar concluir essa mesma auditoria em apenas 10 dias, quantos técnicos A MAIS, com a mesma produtividade, deverão ser integrados à equipe original?",
                options = listOf(
                    "A) 2",
                    "B) 4",
                    "C) 6",
                    "D) 10",
                    "E) 12"
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. Número de técnicos e prazo são grandezas INVERSAMENTE proporcionais (produto constante).",
                    "2. Calcular o total de técnicos necessários (T): T × 10 = 8 × 15 => 10T = 120 => T = 12 técnicos.",
                    "3. Calcular quantos técnicos A MAIS devem ser integrados: 12 - 8 = 4 técnicos."
                ),
                finalComment = "São necessários 12 técnicos no total; como já existem 8 na equipe, devem ser integrados mais 4 técnicos (Gabarito B)."
            )
        )

        "tp_mat_m5_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-17",
                lessonId = lessonId,
                statement = "Após sofrer um reajuste contratual de 12%, o valor mensal de um serviço de vigilância patrimonial passou a ser de R$ 50.400,00. O valor desse contrato ANTES do reajuste era de:",
                options = listOf(
                    "A) R$ 44.352,00",
                    "B) R$ 44.800,00",
                    "C) R$ 45.000,00",
                    "D) R$ 45.200,00",
                    "E) R$ 46.000,00"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Identificar o fator multiplicativo de um aumento de 12%: f = 1 + 0,12 = 1,12.",
                    "2. Equacionar: V_inicial × 1,12 = 50.400.",
                    "3. Dividir pelo fator: V_inicial = 50.400 / 1,12 = 5.040.000 / 112 = R$ 45.000,00."
                ),
                finalComment = "Dividindo o valor reajustado (R$ 50.400,00) pelo fator 1,12, recompõe-se a base original de R$ 45.000,00 (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-18",
                lessonId = lessonId,
                statement = "Em um setor administrativo com 80 empregados, 35% são mulheres. Se forem contratadas mais 20 mulheres e nenhum homem, a nova porcentagem de mulheres no quadro de empregados desse setor passará a ser de:",
                options = listOf(
                    "A) 42%",
                    "B) 45%",
                    "C) 48%",
                    "D) 52%",
                    "E) 55%"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Calcular o número atual de mulheres: 35% de 80 = 0,35 × 80 = 28 mulheres.",
                    "2. Somar as 20 novas contratadas tanto no numerador (mulheres) quanto no denominador (total do setor):",
                    "   Novo número de mulheres = 28 + 20 = 48; Novo total de empregados = 80 + 20 = 100.",
                    "3. Calcular a nova porcentagem: 48 / 100 = 48%."
                ),
                finalComment = "O setor passa a ter 48 mulheres em um novo total de 100 empregados, correspondendo a 48% (Gabarito C)."
            )
        )

        "tp_mat_m5_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-19",
                lessonId = lessonId,
                statement = "O preço de um insumo industrial sofreu um aumento de 20% em janeiro e outro aumento de 15% em junho. No fechamento do ano, o fornecedor concedeu um desconto de 10% sobre o preço vigente desde junho. A variação percentual acumulada no preço desse insumo após as três alterações foi um aumento de:",
                options = listOf(
                    "A) 22,5%",
                    "B) 24,2%",
                    "C) 25,0%",
                    "D) 26,8%",
                    "E) 28,0%"
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. Multiplicar os três fatores sucessivos: F = 1,20 × 1,15 × 0,90.",
                    "2. Calcular 1,20 × 1,15 = 1,38 (+38% acumulado nos dois aumentos).",
                    "3. Multiplicar pelo fator de desconto (0,90): 1,38 × 0,90 = 1,242.",
                    "4. Subtrair 1: 1,242 - 1 = 0,242 = +24,2%."
                ),
                finalComment = "O produto dos fatores 1,20 × 1,15 × 0,90 resulta em 1,242, o que equivale a um aumento acumulado de 24,2% (Gabarito B)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-20",
                lessonId = lessonId,
                statement = "Após dois descontos sucessivos de 20% e de 10%, um lote de válvulas foi adquirido por R$ 36.000,00. Caso o fornecedor tivesse concedido um único desconto equivalente aos dois descontos sucessivos, a taxa percentual desse desconto único seria de 28%, e o preço original do lote antes dos descontos era de:",
                options = listOf(
                    "A) R$ 46.800,00",
                    "B) R$ 48.000,00",
                    "C) R$ 50.000,00",
                    "D) R$ 51.400,00",
                    "E) R$ 54.000,00"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Calcular o fator acumulado dos dois descontos: 0,80 × 0,90 = 0,72 (desconto único equivalente de 1 - 0,72 = 28%).",
                    "2. Igualar ao valor pago: V_original × 0,72 = 36.000.",
                    "3. Isolar V_original: 36.000 / 0,72 = 3.600.000 / 72 = R$ 50.000,00."
                ),
                finalComment = "Com fator remanescente de 0,72, o valor original do lote era 36.000 / 0,72 = R$ 50.000,00 (Gabarito C)."
            )
        )

        "tp_mat_m6_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-21",
                lessonId = lessonId,
                statement = "Três reservatórios (A, B e C) contêm juntos 940 m³ de óleo combustível. O reservatório B contém 80 m³ a mais do que o reservatório A, e o reservatório C contém o dobro do volume do reservatório B. O volume contido no reservatório B, em m³, é igual a:",
                options = listOf(
                    "A) 175",
                    "B) 215",
                    "C) 255",
                    "D) 295",
                    "E) 510"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Expressar os três volumes em função de A = x: B = x + 80 e C = 2(x + 80) = 2x + 160.",
                    "2. Somar os três volumes: x + (x + 80) + (2x + 160) = 940 => 4x + 240 = 940.",
                    "3. Resolver para x: 4x = 700 => x = 175 m³ (volume de A).",
                    "4. Calcular o volume de B pedido na questão: B = 175 + 80 = 255 m³."
                ),
                finalComment = "Modelando em função de A (175 m³), obtém-se B = 175 + 80 = 255 m³ e C = 510 m³ (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-22",
                lessonId = lessonId,
                statement = "Um servidor digitou 1/3 dos processos de um lote pela manhã e, à tarde, digitou 2/5 do total de processos do lote mais 12 processos, restando ainda 20 processos para o dia seguinte. O número total de processos desse lote era:",
                options = listOf(
                    "A) 90",
                    "B) 105",
                    "C) 120",
                    "D) 135",
                    "E) 150"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Seja x o total de processos do lote: (x/3) + (2x/5 + 12) + 20 = x.",
                    "2. Somar as constantes: x/3 + 2x/5 + 32 = x.",
                    "3. Tirar o MMC(3, 5) = 15: 5x + 6x + 480 = 15x => 11x + 480 = 15x => 4x = 480 => x = 120 processos."
                ),
                finalComment = "Igualando a soma das partes a x com denominador comum 15, encontra-se x = 120 processos (Gabarito C)."
            )
        )

        "tp_mat_m6_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-23",
                lessonId = lessonId,
                statement = "Um grupo de técnicos decidiu dividir igualmente uma despesa de R$ 720,00 referente a um curso de atualização. Na última hora, 3 técnicos desistiram, e a cota de cada um dos demais participantes aumentou em R$ 12,00. Quantos técnicos participaram efetivamente do pagamento final?",
                options = listOf(
                    "A) 10",
                    "B) 12",
                    "C) 15",
                    "D) 18",
                    "E) 20"
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. Seja x o número inicial de técnicos: a cota inicial era 720/x e a cota final de cada um dos (x - 3) técnicos foi 720/(x - 3).",
                    "2. Equacionar: 720/(x - 3) - 720/x = 12. Dividindo tudo por 12: 60/(x - 3) - 60/x = 1.",
                    "3. Multiplicar por x(x - 3): 60x - 60(x - 3) = x² - 3x => x² - 3x - 180 = 0.",
                    "4. Por Soma (3) e Produto (-180), as raízes são x = 15 e x = -12. Como desistiram 3, pagaram efetivamente 15 - 3 = 12 técnicos."
                ),
                finalComment = "Eram 15 técnicos inicialmente (R$ 48,00 cada); com a saída de 3, pagaram efetivamente 12 técnicos (R$ 60,00 cada) (Gabarito B)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-24",
                lessonId = lessonId,
                statement = "Para que a equação do 2º grau 2x² - 12x + k = 0 admita duas raízes reais e iguais (uma única raiz real dupla), o valor da constante real k deve ser igual a:",
                options = listOf(
                    "A) 6",
                    "B) 9",
                    "C) 12",
                    "D) 18",
                    "E) 36"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. A condição para duas raízes reais e iguais é que o discriminante seja nulo (Δ = 0).",
                    "2. Calcular Δ = b² - 4ac: (-12)² - 4 · 2 · k = 0.",
                    "3. Resolver para k: 144 - 8k = 0 => 8k = 144 => k = 144 / 8 = 18."
                ),
                finalComment = "Impondo Δ = b² - 4ac = 0, tem-se 144 - 8k = 0, resultando em k = 18 (Gabarito D)."
            )
        )

        "tp_mat_m7_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-25",
                lessonId = lessonId,
                statement = "Um caminhão de suprimentos transporta ao todo 45 caixas de ferramentas dos tipos Leve (12 kg cada) e Pesada (20 kg cada), perfazendo uma carga líquida total de 684 kg. A diferença entre a quantidade de caixas Leves e a quantidade de caixas Pesadas nessa carga é igual a:",
                options = listOf(
                    "A) 6",
                    "B) 9",
                    "C) 12",
                    "D) 18",
                    "E) 27"
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. Montar o sistema linear: L + P = 45 e 12L + 20P = 684.",
                    "2. Multiplicar a 1ª equação por (-12): -12L - 12P = -540.",
                    "3. Somar com a 2ª equação: 8P = 144 => P = 18 caixas Pesadas.",
                    "4. Calcular L: L = 45 - 18 = 27 caixas Leves. A diferença é L - P = 27 - 18 = 9."
                ),
                finalComment = "São 27 caixas Leves e 18 caixas Pesadas, cuja diferença é 27 - 18 = 9 (Gabarito B)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-26",
                lessonId = lessonId,
                statement = "Em uma licitação, a compra de 3 notebooks e 2 impressoras custou R$ 19.000,00, enquanto a compra de 2 notebooks e 3 impressoras idênticas custou R$ 16.000,00. O preço unitário de cada notebook mais o preço unitário de cada impressora soma:",
                options = listOf(
                    "A) R$ 6.000,00",
                    "B) R$ 6.500,00",
                    "C) R$ 7.000,00",
                    "D) R$ 7.500,00",
                    "E) R$ 8.000,00"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Observar a simetria do sistema: 3N + 2I = 19.000 e 2N + 3I = 16.000.",
                    "2. Como a questão pede (N + I), basta somar diretamente as duas equações membro a membro:",
                    "   (3N + 2N) + (2I + 3I) = 19.000 + 16.000 => 5N + 5I = 35.000.",
                    "3. Dividir toda a equação por 5: N + I = 35.000 / 5 = R$ 7.000,00."
                ),
                finalComment = "Somando as duas equações, obtém-se 5(N + I) = 35.000, logo N + I = R$ 7.000,00 sem precisar achar cada preço separadamente (Gabarito C)."
            )
        )

        "tp_mat_m7_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-27",
                lessonId = lessonId,
                statement = "Um plano de expansão logística prevê a instalação de sensores em dutos ao longo de 12 meses em Progressão Aritmética (PA). Se no 1º mês foram instalados 25 sensores e no 12º mês serão instalados 135 sensores, a razão mensal de aumento (r) e o total acumulado de sensores instalados nos 12 meses serão, respectivamente:",
                options = listOf(
                    "A) 10 e 900",
                    "B) 10 e 960",
                    "C) 11 e 960",
                    "D) 11 e 1.020",
                    "E) 12 e 960"
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. Usar a fórmula do termo geral da PA para n = 12: a₁₂ = a₁ + 11r => 135 = 25 + 11r => 11r = 110 => r = 10.",
                    "2. Usar a fórmula da soma dos 12 termos: S₁₂ = ((a₁ + a₁₂) · 12) / 2 = (25 + 135) · 6 = 160 · 6 = 960 sensores."
                ),
                finalComment = "A razão da PA é r = 10 e a soma acumulada nos 12 meses é S₁₂ = 960 sensores (Gabarito B)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-28",
                lessonId = lessonId,
                statement = "O número de acessos a um novo portal interno de suprimentos dobrou a cada semana durante as 5 primeiras semanas de operação, formando uma Progressão Geométrica (PG). Se na 1ª semana houve 150 acessos, o número TOTAL acumulado de acessos ao longo dessas 5 semanas foi de:",
                options = listOf(
                    "A) 2.400",
                    "B) 3.600",
                    "C) 4.500",
                    "D) 4.650",
                    "E) 4.800"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Identificar uma PG com a₁ = 150, razão q = 2 e n = 5 semanas.",
                    "2. Aplicar a soma finita da PG: S₅ = a₁ · (q⁵ - 1) / (q - 1) = 150 · (2⁵ - 1) / (2 - 1).",
                    "3. Calcular: S₅ = 150 · (32 - 1) = 150 × 31 = 4.650 acessos (ou somar 150 + 300 + 600 + 1.200 + 2.400 = 4.650)."
                ),
                finalComment = "Somando os 5 termos da PG (150, 300, 600, 1.200 e 2.400), obtém-se o acumulado de 4.650 acessos (Gabarito D)."
            )
        )

        "tp_mat_m8_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-29",
                lessonId = lessonId,
                statement = "Para contratar o transporte de cargas especiais, a Transpetro analisa duas propostas: a Empresa Alfa cobra taxa fixa mensal de R$ 18.000,00 mais R$ 40,00 por tonelada transportada; a Empresa Beta cobra taxa fixa mensal de R$ 12.000,00 mais R$ 55,00 por tonelada transportada. A partir de quantas toneladas mensais a proposta da Empresa Alfa passa a ser mais econômica do que a da Empresa Beta?",
                options = listOf(
                    "A) Acima de 300 toneladas.",
                    "B) Acima de 350 toneladas.",
                    "C) Acima de 400 toneladas.",
                    "D) Acima de 450 toneladas.",
                    "E) Acima de 500 toneladas."
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Escrever as funções de custo mensal para x toneladas: C_Alfa(x) = 18.000 + 40x e C_Beta(x) = 12.000 + 55x.",
                    "2. Impôr que o custo da Alfa seja menor que o da Beta: 18.000 + 40x < 12.000 + 55x.",
                    "3. Resolver a inequação: 6.000 < 15x => x > 6.000 / 15 => x > 400 toneladas."
                ),
                finalComment = "Em x = 400 t os custos empatam (R$ 34.000,00); acima de 400 toneladas, a Empresa Alfa torna-se mais econômica (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-30",
                lessonId = lessonId,
                statement = "Um tanque contendo inicialmente 840 m³ de combustível é esvaziado a uma vazão constante, de modo que o volume V(t) remanescente após t horas é dado por uma função afim. Se após 4 horas de operação o volume no tanque é de 600 m³, em quantas horas totais, contadas desde o início da operação, o tanque ficará completamente vazio?",
                options = listOf(
                    "A) 10 horas",
                    "B) 12 horas",
                    "C) 14 horas",
                    "D) 15 horas",
                    "E) 16 horas"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Calcular a taxa de variação horária (a): em 4 horas, o volume caiu de 840 m³ para 600 m³ => a = (600 - 840) / 4 = -240 / 4 = -60 m³/h.",
                    "2. Escrever a função afim do volume: V(t) = 840 - 60t.",
                    "3. Encontrar o zero da função (V(t) = 0): 840 - 60t = 0 => t = 840 / 60 = 14 horas."
                ),
                finalComment = "Sendo esvaziado à razão constante de 60 m³/h, os 840 m³ iniciais esgotam-se em 840 / 60 = 14 horas (Gabarito C)."
            )
        )

        "tp_mat_m8_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-31",
                lessonId = lessonId,
                statement = "O lucro mensal L(x), em milhares de reais, obtido com a operação logística de x lotes de contêineres é modelado pela função quadrática L(x) = -x² + 30x - 125. O número de lotes x que proporciona o lucro mensal MÁXIMO e o valor desse lucro máximo são, respectivamente:",
                options = listOf(
                    "A) 15 lotes e R$ 100.000,00",
                    "B) 15 lotes e R$ 125.000,00",
                    "C) 30 lotes e R$ 100.000,00",
                    "D) 30 lotes e R$ 225.000,00",
                    "E) 15 lotes e R$ 350.000,00"
                ),
                correctIndex = 0,
                steps = listOf(
                    "1. Calcular a abscissa do vértice (Xᵥ) que maximiza a função (a = -1, b = 30): Xᵥ = -b / (2a) = -30 / (2 · (-1)) = 15 lotes.",
                    "2. Substituir Xᵥ = 15 na função para achar o lucro máximo (Yᵥ): L(15) = -(15²) + 30·(15) - 125 = -225 + 450 - 125 = 100 milhares de reais (R$ 100.000,00)."
                ),
                finalComment = "O vértice da parábola ocorre em Xᵥ = 15 lotes, gerando lucro máximo Yᵥ = 100 milhares de reais = R$ 100.000,00 (Gabarito A)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-32",
                lessonId = lessonId,
                statement = "Um terreno retangular situado à margem de uma rodovia retilínea será cercado em apenas três de seus lados (dois lados perpendiculares à rodovia, de medida x cada, e um lado paralelo à rodovia), pois o lado voltado para a rodovia já possui muro. Dispondo-se de 120 metros lineares de tela para cercar esses três lados, a ÁREA MÁXIMA que esse terreno pode ter é de:",
                options = listOf(
                    "A) 900 m²",
                    "B) 1.200 m²",
                    "C) 1.600 m²",
                    "D) 1.800 m²",
                    "E) 3.600 m²"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Se os dois lados laterais medem x cada um, o lado paralelo à rodovia mede (120 - 2x).",
                    "2. Expressar a área em função de x: A(x) = x · (120 - 2x) = -2x² + 120x.",
                    "3. Calcular o Xᵥ que maximiza a área: Xᵥ = -120 / (2 · (-2)) = 30 m (e o outro lado mede 120 - 60 = 60 m).",
                    "4. Calcular a área máxima: A_máx = 30 m × 60 m = 1.800 m²."
                ),
                finalComment = "A função área A(x) = -2x² + 120x atinge seu máximo em x = 30 m, resultando em 30 × 60 = 1.800 m² (Gabarito D)."
            )
        )

        "tp_mat_m9_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-33",
                lessonId = lessonId,
                statement = "A concentração C(t) de um traçador químico injetado em um duto decai exponencialmente em função do tempo t, em horas, segundo a lei C(t) = 320 · 2^(-0,5·t), medida em mg/L. Em quantas horas após a injeção a concentração do traçador será igual a 20 mg/L?",
                options = listOf(
                    "A) 4 horas",
                    "B) 6 horas",
                    "C) 8 horas",
                    "D) 10 horas",
                    "E) 16 horas"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Igualar C(t) a 20: 320 · 2^(-0,5·t) = 20.",
                    "2. Dividir ambos os lados por 320: 2^(-0,5·t) = 20 / 320 = 1 / 16.",
                    "3. Escrever 1/16 como potência de base 2: 1/16 = 2⁻⁴.",
                    "4. Igualar os expoentes: -0,5·t = -4 => t = 4 / 0,5 = 8 horas."
                ),
                finalComment = "Reduzindo à base 2, tem-se 2^(-0,5t) = 2⁻⁴, o que fornece t = 8 horas (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-34",
                lessonId = lessonId,
                statement = "A soma das raízes reais da equação exponencial 9ˣ - 10 · 3ˣ + 9 = 0 é igual a:",
                options = listOf(
                    "A) 0",
                    "B) 1",
                    "C) 2",
                    "D) 3",
                    "E) 10"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Reescrever 9ˣ como (3²)ˣ = (3ˣ)² e fazer a mudança de variável y = 3ˣ:",
                    "   y² - 10y + 9 = 0.",
                    "2. Encontrar as raízes de y por Soma (10) e Produto (9): y₁ = 1 e y₂ = 9.",
                    "3. Voltar para a variável original x: 3^(x₁) = 1 = 3⁰ => x₁ = 0; e 3^(x₂) = 9 = 3² => x₂ = 2.",
                    "4. Somar as raízes x₁ + x₂ = 0 + 2 = 2."
                ),
                finalComment = "As soluções para 3ˣ são 1 e 9, logo x₁ = 0 e x₂ = 2, cuja soma é 2 (Gabarito C)."
            )
        )

        "tp_mat_m9_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-35",
                lessonId = lessonId,
                statement = "Adotando as aproximações log₁₀(2) = 0,30 e log₁₀(3) = 0,48, o valor do logaritmo decimal log₁₀(45) é igual a:",
                options = listOf(
                    "A) 1,36",
                    "B) 1,54",
                    "C) 1,66",
                    "D) 1,78",
                    "E) 1,84"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Fatorar 45 usando os fatores 2, 3 e 10: 45 = 9 × 5 = 3² × (10 / 2).",
                    "2. Aplicar as propriedades operatórias do logaritmo:",
                    "   log₁₀(45) = log₁₀(3² · 10 / 2) = 2·log₁₀(3) + log₁₀(10) - log₁₀(2).",
                    "3. Substituir os valores: 2·(0,48) + 1 - 0,30 = 0,96 + 0,70 = 1,66."
                ),
                finalComment = "Escrevendo 45 = 3² × (10/2), obtém-se 2(0,48) + 1 - 0,30 = 1,66 (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-36",
                lessonId = lessonId,
                statement = "Um capital aplicado à taxa composta de 10% ao ano duplica de valor após t anos, satisfazendo a equação (1,1)ᵗ = 2. Utilizando log₁₀(2) = 0,301 e log₁₀(11) = 1,041, o tempo t necessário para duplicar esse capital é de aproximadamente:",
                options = listOf(
                    "A) 6,2 anos",
                    "B) 6,8 anos",
                    "C) 7,3 anos",
                    "D) 8,1 anos",
                    "E) 9,0 anos"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Aplicar log₁₀ nos dois membros de (11/10)ᵗ = 2: t · [log₁₀(11) - log₁₀(10)] = log₁₀(2).",
                    "2. Substituir os valores dados: t · (1,041 - 1) = 0,301 => t · 0,041 = 0,301.",
                    "3. Dividir: t = 0,301 / 0,041 = 301 / 41 ≈ 7,34 anos."
                ),
                finalComment = "Como log(1,1) = log(11) - log(10) = 0,041, temos t = 0,301 / 0,041 ≈ 7,3 anos (Gabarito C)."
            )
        )

        "tp_mat_m10_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-37",
                lessonId = lessonId,
                statement = "Para ligar dois pontos de medição A e B em um terminal, um técnico caminhou 240 metros na direção Norte e, em seguida, 180 metros na direção Leste. Caso fosse instalada uma tubulação retilínea ligando diretamente o ponto A ao ponto B, o comprimento dessa tubulação seria de:",
                options = listOf(
                    "A) 280 m",
                    "B) 300 m",
                    "C) 320 m",
                    "D) 360 m",
                    "E) 420 m"
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. As direções Norte e Leste são perpendiculares (formam 90°), configurando um triângulo retângulo de catetos 180 m e 240 m.",
                    "2. Observar a proporção com o terno pitagórico (3, 4, 5) multiplicado por 60: 3×60 = 180 e 4×60 = 240.",
                    "3. Logo, a hipotenusa mede 5 × 60 = 300 metros (ou √(180² + 240²) = √(32.400 + 57.600) = √90.000 = 300 m)."
                ),
                finalComment = "Pelo Teorema de Pitágoras (terno 3-4-5 multiplicado por 60), a distância em linha reta é de 300 m (Gabarito B)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-38",
                lessonId = lessonId,
                statement = "Uma placa de sinalização circular possui diâmetro igual a 60 cm. Adotando π = 3,14, a área de uma face dessa placa, em centímetros quadrados (cm²), é igual a:",
                options = listOf(
                    "A) 942 cm²",
                    "B) 1.884 cm²",
                    "C) 2.826 cm²",
                    "D) 3.768 cm²",
                    "E) 11.304 cm²"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Determinar o raio R a partir do diâmetro D = 60 cm: R = 60 / 2 = 30 cm.",
                    "2. Aplicar a fórmula da área do círculo: A = π · R² = 3,14 × 30².",
                    "3. Calcular: A = 3,14 × 900 = 2.826 cm²."
                ),
                finalComment = "Com raio R = 30 cm, a área do círculo é 3,14 × 900 = 2.826 cm² (Gabarito C)."
            )
        )

        "tp_mat_m10_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-39",
                lessonId = lessonId,
                statement = "Um reservatório cilíndrico de GLP tem raio da base igual a 5 metros e altura igual a 10 metros. Todo o volume desse cilindro será transferido para pequenos cilindros auxiliares de raio da base igual a 1 metro e altura igual a 2 metros. Quantos cilindros auxiliares serão completamente cheios?",
                options = listOf(
                    "A) 10",
                    "B) 25",
                    "C) 50",
                    "D) 100",
                    "E) 125"
                ),
                correctIndex = 4,
                steps = listOf(
                    "1. Calcular o volume do cilindro grande: V_G = π · R² · H = π · 5² · 10 = 250π m³.",
                    "2. Calcular o volume de cada cilindro auxiliar: V_p = π · r² · h = π · 1² · 2 = 2π m³.",
                    "3. Dividir os volumes: N = 250π / 2π = 125 cilindros auxiliares."
                ),
                finalComment = "A razão entre os volumes é (5/1)² × (10/2) = 25 × 5 = 125 cilindros auxiliares (Gabarito E)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-40",
                lessonId = lessonId,
                statement = "Uma esfera metálica de armazenamento possui diâmetro interno igual a 6 metros. Adotando π = 3,14, o volume interno dessa esfera, em metros cúbicos (m³), é igual a:",
                options = listOf(
                    "A) 37,68 m³",
                    "B) 84,78 m³",
                    "C) 113,04 m³",
                    "D) 226,08 m³",
                    "E) 904,32 m³"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Obter o raio da esfera a partir do diâmetro de 6 m: R = 6 / 2 = 3 m.",
                    "2. Aplicar a fórmula do volume da esfera: V = (4/3) · π · R³ = (4/3) · 3,14 · 3³.",
                    "3. Simplificar: V = 4 · 3,14 · 9 = 36 × 3,14 = 113,04 m³."
                ),
                finalComment = "Para R = 3 m, o volume esférico é (4/3) × 3,14 × 27 = 36 × 3,14 = 113,04 m³ (Gabarito C)."
            )
        )

        "tp_mat_m11_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-41",
                lessonId = lessonId,
                statement = "Para auditar um contrato, o gerente deve escolher uma comissão de 4 profissionais dentre 5 administradores e 4 contadores disponíveis no setor, de modo que a comissão contenha PELO MENOS 3 administradores. O número de comissões distintas que podem ser formadas atendendo a essa exigência é:",
                options = listOf(
                    "A) 20",
                    "B) 25",
                    "C) 40",
                    "D) 45",
                    "E) 60"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. 'Pelo menos 3 administradores' em uma comissão de 4 pessoas divide-se em dois casos mutuamente exclusivos:",
                    "   Caso 1: 3 administradores E 1 contador -> C(5, 3) × C(4, 1) = 10 × 4 = 40 comissões.",
                    "   Caso 2: 4 administradores E 0 contador -> C(5, 4) × C(4, 0) = 5 × 1 = 5 comissões.",
                    "2. Somar os dois casos pelo Princípio Aditivo: 40 + 5 = 45 comissões."
                ),
                finalComment = "Somando os casos com 3 administradores (40) e com 4 administradores (5), obtêm-se 45 comissões possíveis (Gabarito D)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-42",
                lessonId = lessonId,
                statement = "O código de identificação de um lote de peças é formado por 2 letras distintas escolhidas dentre as 5 vogais (A, E, I, O, U), seguidas por 3 algarismos distintos escolhidos dentre os algarismos de 1 a 6. Quantos códigos distintos podem ser criados de modo que o último algarismo seja obrigatoriamente PAR?",
                options = listOf(
                    "A) 600",
                    "B) 720",
                    "C) 900",
                    "D) 1.200",
                    "E) 1.440"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Escolha das 2 vogais distintas: 5 × 4 = 20 maneiras.",
                    "2. Começar os algarismos pela restrição (último algarismo PAR dentre {1, 2, 3, 4, 5, 6}): há 3 opções pares ({2, 4, 6}).",
                    "3. Escolhido o último algarismo, restam 5 opções para o 1º algarismo e 4 opções para o 2º algarismo: 5 × 4 × 3 = 60 maneiras.",
                    "4. Multiplicar tudo pelo PFC: 20 × 60 = 1.200 códigos distintos."
                ),
                finalComment = "Pelo Princípio Fundamental da Contagem, temos (5 × 4) × (5 × 4 × 3) = 20 × 60 = 1.200 códigos (Gabarito D)."
            )
        )

        "tp_mat_m11_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-43",
                lessonId = lessonId,
                statement = "Em uma caixa há 12 disjuntores aparentemente idênticos, dos quais 3 estão com defeito. Se um eletricista retirar, ao acaso e sem reposição, 2 disjuntores dessa caixa, a probabilidade de que AMBOS estejam funcionando perfeitamente é de:",
                options = listOf(
                    "A) 1/2",
                    "B) 6/11",
                    "C) 9/16",
                    "D) 3/4",
                    "E) 9/11"
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. Dos 12 disjuntores, 12 - 3 = 9 estão perfeitos.",
                    "2. Probabilidade de o 1º ser perfeito: 9 / 12 = 3 / 4.",
                    "3. Sem reposição, restam 8 perfeitos num total de 11: probabilidade de o 2º ser perfeito = 8 / 11.",
                    "4. Multiplicar as probabilidades: P = (3 / 4) × (8 / 11) = 6 / 11."
                ),
                finalComment = "Na extração sem reposição, P = (9/12) × (8/11) = 72/132 = 6/11 (Gabarito B)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-44",
                lessonId = lessonId,
                statement = "Um inspetor realiza 3 testes independentes em válvulas de alívio. Se a probabilidade de encontrar uma falha em cada teste é de 1/4, a probabilidade de que ele encontre falha em PELO MENOS UM dos 3 testes é igual a:",
                options = listOf(
                    "A) 3/4",
                    "B) 9/16",
                    "C) 27/64",
                    "D) 37/64",
                    "E) 63/64"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Se a probabilidade de falha em um teste é 1/4, a probabilidade de NÃO haver falha em um teste é 1 - 1/4 = 3/4.",
                    "2. A probabilidade de não encontrar falha em NENHUM dos 3 testes independentes é (3/4)³ = 27 / 64.",
                    "3. Usar o evento complementar: P(pelo menos uma falha) = 1 - 27/64 = 37/64."
                ),
                finalComment = "Pelo evento complementar, 1 - (3/4)³ = 1 - 27/64 = 37/64 (Gabarito D)."
            )
        )

        "tp_mat_m11_a3" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-45",
                lessonId = lessonId,
                statement = "A frota de veículos de apoio de uma regional é composta por 60 caminhonetes (das quais 15 são movidas a diesel e 45 são flex) e 40 automóveis de passeio (dos quais 5 são movidos a diesel e 35 são flex). Escolhendo-se ao acaso um veículo dessa frota e SABENDO-SE que ele é movido a diesel, a probabilidade de que seja uma caminhonete é de:",
                options = listOf(
                    "A) 15%",
                    "B) 25%",
                    "C) 60%",
                    "D) 75%",
                    "E) 80%"
                ),
                correctIndex = 3,
                steps = listOf(
                    "1. Determinar o novo espaço amostral reduzido (todos os veículos movidos a diesel): 15 caminhonetes + 5 automóveis = 20 veículos a diesel.",
                    "2. Identificar os casos favoráveis dentro desse universo (ser caminhonete E a diesel): 15 veículos.",
                    "3. Calcular a probabilidade condicional: P(Caminhonete | Diesel) = 15 / 20 = 3 / 4 = 75%."
                ),
                finalComment = "Restringindo o universo aos 20 veículos a diesel, 15 são caminhonetes, o que corresponde a 15/20 = 75% (Gabarito D)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-46",
                lessonId = lessonId,
                statement = "Em um lote de 200 peças fornecidas por duas fábricas (F₁ e F₂), sabe-se que F₁ forneceu 120 peças (com 5% de peças fora de especificação) e F₂ forneceu 80 peças (com 10% de peças fora de especificação). Sorteando-se ao acaso uma peça do lote e verificando-se que ela está FORA de especificação, a probabilidade de ter sido fabricada por F₂ é:",
                options = listOf(
                    "A) 2/7",
                    "B) 3/7",
                    "C) 4/7",
                    "D) 5/7",
                    "E) 1/2"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Calcular o número de peças fora de especificação de cada fábrica: F₁ tem 5% de 120 = 6 peças; F₂ tem 10% de 80 = 8 peças.",
                    "2. Total de peças fora de especificação (novo denominador): 6 + 8 = 14 peças.",
                    "3. Probabilidade de vir de F₂ dado que está fora de especificação: 8 / 14 = 4 / 7."
                ),
                finalComment = "Das 14 peças fora de especificação no lote (6 da F₁ e 8 da F₂), 8 vieram de F₂, resultando em 8/14 = 4/7 (Gabarito C)."
            )
        )

        "tp_mat_m12_a1" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-47",
                lessonId = lessonId,
                statement = "Os volumes diários (em m³) bombeados por uma estação durante os 6 primeiros dias da semana foram: 420, 380, 450, 380, 490 e 400. A diferença entre a MEDIANA e a MODA desses 6 valores é igual a:",
                options = listOf(
                    "A) 10 m³",
                    "B) 20 m³",
                    "C) 30 m³",
                    "D) 40 m³",
                    "E) 50 m³"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Ordenar os 6 valores em ROL crescente: {380, 380, 400, 420, 450, 490}.",
                    "2. Como n = 6 é par, a Mediana é a média dos dois termos centrais (3º e 4º termos): Md = (400 + 420) / 2 = 410 m³.",
                    "3. A Moda é o valor que mais se repete: Mo = 380 m³ (aparece 2 vezes).",
                    "4. Calcular a diferença Md - Mo = 410 - 380 = 30 m³."
                ),
                finalComment = "No rol ordenado, a mediana é 410 m³ e a moda é 380 m³; a diferença entre elas é de 30 m³ (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-48",
                lessonId = lessonId,
                statement = "A média salarial dos 12 empregados de uma coordenadoria é de R$ 6.200,00. Se 2 empregados que recebiam R$ 8.700,00 cada um se aposentarem e não forem substituídos, a nova média salarial dos 10 empregados remanescentes passará a ser de:",
                options = listOf(
                    "A) R$ 5.500,00",
                    "B) R$ 5.600,00",
                    "C) R$ 5.700,00",
                    "D) R$ 5.800,00",
                    "E) R$ 5.950,00"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Calcular a soma salarial inicial dos 12 empregados: Soma₁₂ = 12 × 6.200 = R$ 74.400,00.",
                    "2. Subtrair os salários dos 2 aposentados: Soma₁₀ = 74.400 - (2 × 8.700) = 74.400 - 17.400 = R$ 57.000,00.",
                    "3. Dividir pelo novo número de empregados (10): Nova Média = 57.000 / 10 = R$ 5.700,00."
                ),
                finalComment = "Retirando R$ 17.400,00 da folha original de R$ 74.400,00, restam R$ 57.000,00 para 10 empregados, gerando média de R$ 5.700,00 (Gabarito C)."
            )
        )

        "tp_mat_m12_a2" -> listOf(
            buildExercise(
                id = "TP-MAT-SUP-49",
                lessonId = lessonId,
                statement = "Em uma avaliação de fornecedores, a distribuição das notas atribuídas a 40 empresas foi registrada na seguinte tabela de frequências: Nota 6 (8 empresas); Nota 7 (10 empresas); Nota 8 (14 empresas); Nota 9 (5 empresas); Nota 10 (3 empresas). Se esses dados forem representados em um gráfico de setores (360°), a soma dos ângulos centrais correspondentes às empresas que obtiveram nota IGUAL OU SUPERIOR a 8 será de:",
                options = listOf(
                    "A) 162°",
                    "B) 180°",
                    "C) 198°",
                    "D) 216°",
                    "E) 220°"
                ),
                correctIndex = 2,
                steps = listOf(
                    "1. Somar a quantidade de empresas com nota ≥ 8 (notas 8, 9 e 10): 14 + 5 + 3 = 22 empresas.",
                    "2. Calcular a fração sobre o total de 40 empresas: 22 / 40 = 11 / 20.",
                    "3. Multiplicar por 360°: α = (11 / 20) × 360° = 11 × 18° = 198°."
                ),
                finalComment = "As 22 empresas com nota ≥ 8 representam 55% da amostra (22/40), o que corresponde a 0,55 × 360° = 198° no gráfico de setores (Gabarito C)."
            ),
            buildExercise(
                id = "TP-MAT-SUP-50",
                lessonId = lessonId,
                statement = "Duas equipes de manutenção (A e B) obtiveram exatamente a mesma média mensal de 50 ordens de serviço concluídas no semestre. Contudo, o desvio-padrão mensal da equipe A foi de 2,4 ordens, enquanto o desvio-padrão da equipe B foi de 8,9 ordens. Com base nas medidas de dispersão, conclui-se corretamente que:",
                options = listOf(
                    "A) a equipe B concluiu mais ordens de serviço no semestre do que a equipe A.",
                    "B) a equipe A apresentou um desempenho mensal mais regular e homogêneo do que a equipe B.",
                    "C) a mediana da equipe B é necessariamente superior à mediana da equipe A.",
                    "D) a equipe B apresentou menor amplitude de variação mensal do que a equipe A.",
                    "E) ambas as equipes tiveram exatamente o mesmo número de ordens concluídas em todos os meses."
                ),
                correctIndex = 1,
                steps = listOf(
                    "1. Como ambas têm a mesma média (50) em 6 meses, ambas concluíram o mesmo total (300 ordens).",
                    "2. O desvio-padrão mede a dispersão (variabilidade) dos dados em torno da média.",
                    "3. Quanto MENOR o desvio-padrão (2,4 < 8,9), menor a oscilação mensal e MAIS REGULAR (homogêneo) é o desempenho da equipe."
                ),
                finalComment = "Um menor desvio-padrão indica menor dispersão em torno da média, comprovando que a equipe A teve desempenho mais regular e homogêneo (Gabarito B)."
            )
        )

        else -> emptyList()
    }
}

