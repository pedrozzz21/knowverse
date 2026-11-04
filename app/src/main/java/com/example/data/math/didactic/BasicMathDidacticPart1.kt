package com.example.data.math.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo Didático Aprofundado de Matemática Básica — Parte 1 (Aulas 1 a 4).
 * Estruturado com os 12 pilares pedagógicos:
 * 1. Introdução
 * 2. Objetivos de Aprendizagem
 * 3. Pré-requisitos
 * 4. Explicação Teórica Aprofundada
 * 5. Definições e Conceitos
 * 6. Exemplos Resolvidos Passo a Passo
 * 7. Aplicações Práticas
 * 8. Exercícios de Fixação
 * 9. Correção Comentada
 * 10. Desafio de Domínio
 * 11. Resumo
 * 12. Referências
 */
object BasicMathDidacticPart1 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "mb_l1" -> bundleMbL1
            "mb_l2" -> bundleMbL2
            "mb_l3" -> bundleMbL3
            "mb_l4" -> bundleMbL4
            else -> null
        }
    }

    // =========================================================================
    // AULA 1: Conjuntos Numéricos, Frações e Decimais (mb_l1)
    // =========================================================================
    private val bundleMbL1 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 1: Conjuntos Numéricos, Operações Fundamentais, Frações e Decimais
            
            ## 1. Introdução
            A aritmética dos números reais é o alicerce absoluto de toda a matemática elementar e superior. Seja ao calcular dosagens farmacológicas em ciências da saúde, balancear equações estequiométricas na química ou analisar o comportamento de limites em Cálculo I, a fluência em representações fracionárias e decimais previne os erros mais comuns e fatais em exames como o ENEM.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Classificar rigorosamente qualquer número nos conjuntos ${'$'}\mathbb{N}, \mathbb{Z}, \mathbb{Q}, \mathbb{I}${'$'} e ${'$'}\mathbb{R}${'$'}.
            - Aplicar corretamente a regra dos sinais nas quatro operações fundamentais.
            - Operar adições, subtrações, multiplicações e divisões de frações com denominadores distintos sem memorização mecânica.
            - Converter decimais exatos e dízimas periódicas em frações geratrizes e vice-versa.
            
            ## 3. Pré-requisitos
            - Contagem elementar e operações aritméticas básicas com números naturais.
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. Estrutura dos Conjuntos Numéricos
            1. **Naturais (${'$'}\mathbb{N}${'$'}):** ${'$'}\{0, 1, 2, 3, \dots\}${'$'} — surgem da necessidade de contagem de elementos discretos.
            2. **Inteiros (${'$'}\mathbb{Z}${'$'}):** ${'$'}\{\dots, -3, -2, -1, 0, 1, 2, 3, \dots\}${'$'} — incorporam grandezas opostas, déficits e orientação de retas.
               - *Regra de Sinais na Multiplicação e Divisão:* Sinais iguais geram resultado positivo (${'$'}(+)\cdot(+) = +${'$'} e ${'$'}(-)\cdot(-) = +${'$'}); sinais contrários geram resultado negativo (${'$'}(+)\cdot(-) = -${'$'} e ${'$'}(-)\cdot(+) = -${'$'} ).
               - *Soma de Opostos:* ${'$'}a + (-b) = a - b${'$'}. Se os sinais forem iguais, somam-se os valores absolutos e preserva-se o sinal.
            3. **Racionais (${'$'}\mathbb{Q}${'$'}):** Todo número que pode ser expresso na forma ${'$'}\frac{a}{b}${'$'}, onde ${'$'}a \in \mathbb{Z}${'$'} e ${'$'}b \in \mathbb{Z}^*${'$'} (${'$'}b \neq 0${'$'}).
               - Compreende inteiros (${'$'}\frac{5}{1} = 5${'$'}), decimais exatos (${'$'}0{,}75 = \frac{75}{100} = \frac{3}{4}${'$'}) e dízimas periódicas (${'$'}0{,}333\dots = \frac{1}{3}${'$'}).
            4. **Irracionais (${'$'}\mathbb{I}${'$'}):** Números com infinitas casas decimais não periódicas que **não podem** ser expressos como quociente de dois inteiros (ex.: ${'$'}\sqrt{2} \approx 1{,}4142\dots${'$'}, ${'$'}\pi \approx 3{,}14159\dots${'$'}, ${'$'}e \approx 2{,}71828\dots${'$'}).
            5. **Reais (${'$'}\mathbb{R}${'$'}):** União completa dos racionais com os irracionais (${'$'}\mathbb{R} = \mathbb{Q} \cup \mathbb{I}${'$'}). Preenche geometricamente a reta contínua.
            
            ### B. A Aritmética das Frações
            Uma fração ${'$'}\frac{a}{b}${'$'} representa ${'$'}a${'$'} partes de um todo particionado em ${'$'}b${'$'} partes iguais.
            - **Adição e Subtração:** Só podemos somar parcelas de mesma natureza (mesmo denominador). Quando os denominadores são diferentes, devemos homogeneizar as unidades fracionárias pelo Mínimo Múltiplo Comum (MMC):
              ${'$'}${'$'}\frac{a}{b} \pm \frac{c}{d} = \frac{\frac{\text{MMC}}{b}\cdot a \pm \frac{\text{MMC}}{d}\cdot c}{\text{MMC}}${'$'}${'$'}
            - **Multiplicação:** Representa uma fração de outra fração (ex.: metade de um terço é ${'$'}\frac{1}{2} \cdot \frac{1}{3} = \frac{1}{6}${'$'}):
              ${'$'}${'$'}\frac{a}{b} \times \frac{c}{d} = \frac{a \cdot c}{b \cdot d}${'$'}${'$'}
            - **Divisão:** Quantas vezes o divisor cabe dentro do dividendo? Matematicamente, dividir por uma fração é multiplicar pelo seu inverso:
              ${'$'}${'$'}\frac{a}{b} \div \frac{c}{d} = \frac{a}{b} \times \frac{d}{c} = \frac{a \cdot d}{b \cdot c} \quad (c \neq 0)${'$'}${'$'}
            
            ### C. Frações Geratrizes de Dízimas Periódicas
            - **Dízima Periódica Simples:** Período inicia logo após a vírgula.
              Exemplo: ${'$'}x = 0{,}777\dots \implies 10x = 7{,}777\dots \implies 9x = 7 \implies x = \frac{7}{9}${'$'}.
            - **Dízima Periódica Composta:** Possui parte não periódica (antiperíodo) antes do período.
              Exemplo: ${'$'}x = 0{,}1666\dots \implies 10x = 1{,}666\dots${'$'} e ${'$'}100x = 16{,}666\dots${'$'}.
              Subtraindo: ${'$'}100x - 10x = 16 - 1 = 15 \implies 90x = 15 \implies x = \frac{15}{90} = \frac{1}{6}${'$'}.
            
            ## 5. Definições e Conceitos
            - **Fração Irredutível:** Aquela em que numerador e denominador são primos entre si (${'$'}\text{MDC}(a,b) = 1${'$'}).
            - **Inverso Multiplicativo:** O número que multiplicado por ${'$'}x${'$'} resulta em 1. O inverso de ${'$'}\frac{a}{b}${'$'} é ${'$'}\frac{b}{a}${'$'}.
            - **Valor Posicional Decimal:** Cada casa à direita da vírgula representa uma potência de dez no denominador: décimos (${'$'}10^{-1}${'$'}), centésimos (${'$'}10^{-2}${'$'}), milésimos (${'$'}10^{-3}${'$'}).
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Expressão Aritmética Fracionária Mista
            Calcule o valor exato da expressão:
            ${'$'}${'$'}E = \frac{\frac{2}{3} + \frac{1}{4}}{\frac{5}{6} - \frac{1}{2}} \times 0{,}4${'$'}${'$'}
            
            **Resolução Analítica:**
            - *Passo 1 (Numerador):* Denominadores 3 e 4. ${'$'}\text{MMC}(3, 4) = 12${'$'}.
              ${'$'}${'$'}\frac{2}{3} + \frac{1}{4} = \frac{4\cdot 2 + 3\cdot 1}{12} = \frac{8 + 3}{12} = \frac{11}{12}${'$'}${'$'}
            - *Passo 2 (Denominador):* Denominadores 6 e 2. ${'$'}\text{MMC}(6, 2) = 6${'$'}.
              ${'$'}${'$'}\frac{5}{6} - \frac{1}{2} = \frac{5 - 3\cdot 1}{6} = \frac{2}{6} = \frac{1}{3}${'$'}${'$'}
            - *Passo 3 (Divisão principal):*
              ${'$'}${'$'}\frac{\frac{11}{12}}{\frac{1}{3}} = \frac{11}{12} \times \frac{3}{1} = \frac{33}{12} = \frac{11}{4}${'$'}${'$'}
            - *Passo 4 (Multiplicação pelo decimal convertido):* ${'$'}0{,}4 = \frac{4}{10} = \frac{2}{5}${'$'}.
              ${'$'}${'$'}E = \frac{11}{4} \times \frac{2}{5} = \frac{11 \times 2}{4 \times 5} = \frac{22}{20} = \frac{11}{10} = 1{,}1${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Farmacologia e Saúde:** Se um frasco de medicamento contém ${'$'}150\text{ mL}${'$'} e a dose prescrita é de ${'$'}\frac{3}{4}${'$'} de colher dosadora contendo ${'$'}5\text{ mL}${'$'} (${'$'}3{,}75\text{ mL}${'$'}), o total de doses disponíveis é:
              ${'$'}${'$'}150 \div \frac{15}{4} = 150 \times \frac{4}{15} = 40\text{ doses exatas}${'$'}${'$'}
            - **Engenharia e Medição:** O alinhamento de engrenagens e tubulações industriais exige compatibilidade milimétrica entre frações de polegada (${'$'}\frac{3}{8}"${'$'}, ${'$'}\frac{1}{2}"${'$'}) e medidas métricas em decimais.
            
            ## 8. Exercícios de Fixação
            1. Qual é a fração irredutível geratriz da dízima periódica ${'$'}1{,}2333\dots${'$'}?
            2. Um reservatório tem capacidade de ${'$'}2400\text{ L}${'$'}. Inicialmente estava preenchido em ${'$'}\frac{5}{8}${'$'} de sua capacidade. Foram retirados ${'$'}450\text{ L}${'$'} e adicionados ${'$'}\frac{1}{4}${'$'} da capacidade total. Qual o volume final em litros?
            
            ## 9. Correção Comentada
            - *Exercício 1:* Seja ${'$'}x = 1{,}2333\dots \implies 10x = 12{,}333\dots${'$'} e ${'$'}100x = 123{,}333\dots${'$'}.
              Subtraindo as equações: ${'$'}100x - 10x = 123 - 12 \implies 90x = 111 \implies x = \frac{111}{90}${'$'}.
              Simplificando por 3: ${'$'}\frac{111 \div 3}{90 \div 3} = \frac{37}{30}${'$'}.
            - *Exercício 2:*
              - Volume inicial: ${'$'}\frac{5}{8} \times 2400 = 5 \times 300 = 1500\text{ L}${'$'}.
              - Após retirada: ${'$'}1500 - 450 = 1050\text{ L}${'$'}.
              - Adição de ${'$'}\frac{1}{4}${'$'}: ${'$'}\frac{1}{4} \times 2400 = 600\text{ L}${'$'}.
              - Volume final: ${'$'}1050 + 600 = 1650\text{ L}${'$'}.
            
            ## 10. Desafio de Domínio
            Demonstre por que o número decimal ${'$'}0{,}999\dots${'$'} é rigorosamente igual a ${'$'}1${'$'} e não apenas "uma aproximação".
            *Pista:* Utilize a técnica da fração geratriz multiplicando por 10 ou a soma da série geométrica infinita com primeiro termo ${'$'}a_1 = 0{,}9${'$'} e razão ${'$'}q = 0{,}1${'$'}:
            ${'$'}${'$'}S = \frac{a_1}{1 - q} = \frac{0{,}9}{1 - 0{,}1} = \frac{0{,}9}{0{,}9} = 1${'$'}${'$'}
            
            ## 11. Resumo
            - ${'$'}\mathbb{N} \subset \mathbb{Z} \subset \mathbb{Q} \subset \mathbb{R}${'$'} e ${'$'}\mathbb{I} \subset \mathbb{R}${'$'}, com ${'$'}\mathbb{Q} \cap \mathbb{I} = \emptyset${'$'}.
            - Na soma/subtração de frações, o MMC dos denominadores é obrigatório.
            - Na divisão de frações, multiplica-se pelo inverso multiplicativo da segunda.
            - Dízimas periódicas pertencem a ${'$'}\mathbb{Q}${'$'} e sempre possuem fração geratriz exata.
            
            ## 12. Referências
            - BRASIL. Ministério da Educação. *Base Nacional Comum Curricular (BNCC)*: Ensino Fundamental e Médio. Área de Matemática. Brasília: MEC, 2018.
            - OPENSTAX. *Prealgebra 2e*. Rice University, 2020. Disponível em: https://openstax.org/details/books/prealgebra-2e.
            - IEZZI, Gelson et al. *Fundamentos de Matemática Elementar*, Volume 1: Conjuntos e Funções. São Paulo: Atual, 2013.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Soma e Subtração de Frações",
                syntax = "\\frac{a}{b} \\pm \\frac{c}{d} = \\frac{\\frac{MMC}{b}\\cdot a \\pm \\frac{MMC}{d}\\cdot c}{MMC}",
                description = "Homogeneização de denominadores distintos através do Mínimo Múltiplo Comum.",
                parametersInfo = "b ≠ 0 e d ≠ 0"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Divisão de Frações",
                syntax = "\\frac{a}{b} \\div \\frac{c}{d} = \\frac{a}{b} \\times \\frac{d}{c} = \\frac{a \\cdot d}{b \\cdot c}",
                description = "Multiplicação do dividendo pelo inverso do divisor.",
                parametersInfo = "c ≠ 0, b ≠ 0 e d ≠ 0"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Fração Geratriz (Dízima Periódica Simples)",
                syntax = "0,\\overline{p} = \\frac{p}{10^k - 1} = \\frac{p}{99\\dots9}",
                description = "Conversão de dízima simples com período de k dígitos em fração racional.",
                parametersInfo = "k = quantidade de algarismos do período p"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Problema Clássico do ENEM: Mistura de Combustíveis",
                codeOrFormula = "V_{\\text{álcool}} = \\frac{27}{100} \\times 45 = 12{,}15\\text{ litros}",
                explanation = """
                    Enunciado: A gasolina comum brasileira contém 27% de etanol anidro em volume. Se um motorista abastece seu tanque com exatamente 45 litros dessa gasolina, quantos litros de etanol e quantos litros de gasolina pura estão presentes?
                    
                    Passo 1: Escrever a porcentagem como fração irredutível ou decimal:
                    27% = 27/100 = 0,27.
                    
                    Passo 2: Calcular o volume de etanol:
                    V_álcool = 45 × 0,27 = 45 × (27/100) = 1215/100 = 12,15 litros.
                    
                    Passo 3: Calcular o volume de gasolina pura por diferença:
                    V_gasolina = 45 - 12,15 = 32,85 litros.
                    
                    Verificação: 12,15 + 32,85 = 45,00 litros.
                """.trimIndent()
            )
        ),
        goldTip = "Em provas com tempo restrito como o ENEM, memorize as frações fundamentais decimais: 1/2 = 0,5; 1/4 = 0,25; 3/4 = 0,75; 1/5 = 0,2; 1/8 = 0,125. Isso poupa preciosos minutos de divisão manual!"
    )

    // =========================================================================
    // AULA 2: Divisibilidade, MMC e MDC (mb_l2)
    // =========================================================================
    private val bundleMbL2 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 2: Divisibilidade, Fatoração Prima, MMC e MDC
            
            ## 1. Introdução
            A teoria elementar dos números rege a estrutura multiplicativa dos inteiros. Problemas clássicos do ENEM sobre sincronicidade de eventos periódicos (como cometas que cruzam a órbita, semáforos que piscam simultaneamente ou linhas de ônibus que partem juntas) dependem do Mínimo Múltiplo Comum (MMC). Já problemas de divisão territorial, corte de materiais sem desperdício ou empacotamento em lotes de tamanho máximo são regidos pelo Máximo Divisor Comum (MDC).
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Aplicar os critérios de divisibilidade por 2, 3, 4, 5, 6, 8, 9 e 10 para simplificar cálculos mentais rapidamente.
            - Decompor qualquer número inteiro em fatores primos pelo Teorema Fundamental da Aritmética.
            - Distinguir imediatamente se uma situação-problema exige MMC ou MDC através de palavras-chave contextuais.
            - Calcular MMC e MDC por fatoração simultânea e pela regra dos expoentes canônicos.
            
            ## 3. Pré-requisitos
            - Conjuntos numéricos (${'$'}\mathbb{N}${'$'} e ${'$'}\mathbb{Z}${'$'}) e operações de multiplicação e divisão (Aula 1).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. Critérios de Divisibilidade Rápidos
            - **Por 2:** O último algarismo é par (0, 2, 4, 6, 8).
            - **Por 3:** A soma de todos os seus algarismos resulta em um múltiplo de 3 (ex.: 4.713 ${'$'}\to 4+7+1+3 = 15${'$'}, que é divisível por 3).
            - **Por 4:** Os dois últimos algarismos formam um número divisível por 4 ou terminam em 00 (ex.: 5.328 ${'$'}\to 28${'$'} é divisível por 4).
            - **Por 5:** O último algarismo é 0 ou 5.
            - **Por 6:** O número é simultaneamente divisível por 2 e por 3 (par e com soma múltipla de 3).
            - **Por 8:** Os três últimos algarismos formam um número divisível por 8 ou terminam em 000.
            - **Por 9:** A soma de todos os algarismos resulta em um múltiplo de 9.
            - **Por 10:** O último algarismo é 0.
            
            ### B. Teorema Fundamental da Aritmética
            Todo número inteiro ${'$'}n > 1${'$'} pode ser decomposto de maneira **única** (a menos da ordem) como um produto de fatores primos:
            ${'$'}${'$'}n = p_1^{\alpha_1} \cdot p_2^{\alpha_2} \cdots p_k^{\alpha_k}${'$'}${'$'}
            Exemplo: ${'$'}360 = 2^3 \cdot 3^2 \cdot 5^1${'$'}.
            - *Quantidade de divisores positivos de ${'$'}n${'$'}:*
              ${'$'}${'$'}D(n) = (\alpha_1 + 1)(\alpha_2 + 1)\cdots(\alpha_k + 1)${'$'}${'$'}
              Para 360: ${'$'}D(360) = (3+1)(2+1)(1+1) = 4 \times 3 \times 2 = 24${'$'} divisores positivos!
            
            ### C. MMC vs. MDC: Como Diferenciar no ENEM
            | Conceito | Pergunta Pedagógica | Palavras-Chave no Enunciado | Operação Canônica |
            | :--- | :--- | :--- | :--- |
            | **MMC** | *Quando ocorrerá o próximo encontro?* | Ciclos, repetição, coincidência futura, periódico, simultâneo. | Todos os fatores primos com os **maiores expoentes**. |
            | **MDC** | *Como repartir sem sobras na maior escala possível?* | Divisão em partes iguais, maior tamanho possível, sem desperdício. | Apenas os fatores primos comuns com os **menores expoentes**. |
            
            ### D. Propriedade Fundamental do Produto
            Para quaisquer dois números inteiros positivos ${'$'}a${'$'} e ${'$'}b${'$'}:
            ${'$'}${'$'}\text{MMC}(a, b) \times \text{MDC}(a, b) = a \cdot b${'$'}${'$'}
            
            ## 5. Definições e Conceitos
            - **Número Primo:** Número natural maior que 1 que possui apenas dois divisores positivos distintos: 1 e ele mesmo.
            - **Números Primos entre Si (Coprimos):** Dois ou mais números cujo único divisor comum é a unidade (${'$'}\text{MDC}(a, b) = 1${'$'}). Exemplo: 8 e 15 são coprimos, embora nenhum dos dois seja primo isoladamente.
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Sincronicidade de Linhas de Ônibus (MMC)
            Três linhas de transporte coletivo partem de um mesmo terminal rodoviário às 06:00 da manhã. A linha A sai a cada 15 minutos, a linha B a cada 20 minutos e a linha C a cada 25 minutos. A que horas os três ônibus partirão juntos do terminal pela primeira vez após as 06:00?
            
            **Resolução Analítica:**
            - *Passo 1:* Identificar a natureza do problema. Trata-se de repetição periódica de ciclos temporais ${'$'}\implies${'$'} MMC.
            - *Passo 2:* Fatoração simultânea de 15, 20 e 25:
              - ${'$'}15 = 3 \cdot 5${'$'}
              - ${'$'}20 = 2^2 \cdot 5${'$'}
              - ${'$'}25 = 5^2${'$'}
            - *Passo 3:* Tomar todos os fatores primos com seus maiores expoentes:
              ${'$'}${'$'}\text{MMC}(15, 20, 25) = 2^2 \cdot 3^1 \cdot 5^2 = 4 \cdot 3 \cdot 25 = 300\text{ minutos}${'$'}${'$'}
            - *Passo 4:* Converter minutos em horas:
              ${'$'}${'$'}300 \div 60 = 5\text{ horas}${'$'}${'$'}
            - *Passo 5:* Somar ao horário inicial:
              ${'$'}${'$'}06:00 + 5\text{ horas} = 11:00\text{ da manhã}${'$'}${'$'}
            
            ### Exemplo 2: Divisão de Vigas sem Sobras (MDC)
            Um marceneiro possui três toras de madeira de comprimentos ${'$'}120\text{ cm}${'$'}, ${'$'}180\text{ cm}${'$'} e ${'$'}240\text{ cm}${'$'}. Ele deseja serrá-las em tábuas de igual comprimento, com o maior tamanho possível e sem desperdício. Qual deve ser o comprimento de cada tábua e quantas tábuas serão obtidas no total?
            
            **Resolução Analítica:**
            - *Passo 1:* Problema de corte/partição máxima comum ${'$'}\implies${'$'} MDC.
            - *Passo 2:* Fatoração de 120, 180 e 240:
              - ${'$'}120 = 2^3 \cdot 3 \cdot 5${'$'}
              - ${'$'}180 = 2^2 \cdot 3^2 \cdot 5${'$'}
              - ${'$'}240 = 2^4 \cdot 3 \cdot 5${'$'}
            - *Passo 3:* Fatores comuns com os menores expoentes:
              ${'$'}${'$'}\text{MDC}(120, 180, 240) = 2^2 \cdot 3^1 \cdot 5^1 = 4 \cdot 3 \cdot 5 = 60\text{ cm}${'$'}${'$'}
              Cada tábua medirá exatamente ${'$'}60\text{ cm}${'$'}.
            - *Passo 4:* Quantidade total de tábuas:
              ${'$'}${'$'}\frac{120}{60} + \frac{180}{60} + \frac{240}{60} = 2 + 3 + 4 = 9\text{ tábuas}${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Astronomia e Engenharia Aeroespacial:** O cálculo de janelas de lançamento de sondas espaciais entre órbitas planetárias baseia-se no período sinódico, que é análogo ao MMC das velocidades orbitais angulares.
            - **Criptografia RSA:** A segurança de sistemas bancários e chaves públicas mundiais baseia-se na extrema dificuldade computacional de fatorar números colossais formados pelo produto de dois números primos gigantescos.
            
            ## 8. Exercícios de Fixação
            1. Dois satélites artificiais orbitam a Terra. O satélite Alpha completa uma volta a cada 90 minutos, enquanto o satélite Beta completa uma volta a cada 105 minutos. Se eles se alinharam sobre o meridiano de Greenwich ao meio-dia, após quantas horas voltarão a se alinhar sobre o mesmo ponto?
            2. O produto de dois números inteiros positivos é 2160 e o MDC entre eles é 6. Qual é o MMC desses dois números?
            
            ## 9. Correção Comentada
            - *Exercício 1:* ${'$'}\text{MMC}(90, 105)${'$'}.
              ${'$'}90 = 2 \cdot 3^2 \cdot 5${'$'}; ${'$'}105 = 3 \cdot 5 \cdot 7${'$'}.
              ${'$'}\text{MMC} = 2 \cdot 3^2 \cdot 5 \cdot 7 = 2 \cdot 9 \cdot 35 = 630\text{ minutos}${'$'}.
              Em horas: ${'$'}630 \div 60 = 10{,}5\text{ horas} = 10\text{ horas e } 30\text{ minutos}${'$'}.
            - *Exercício 2:* Usando a propriedade fundamental:
              ${'$'}${'$'}\text{MMC}(a, b) \times \text{MDC}(a, b) = a \cdot b \implies \text{MMC} \times 6 = 2160 \implies \text{MMC} = \frac{2160}{6} = 360${'$'}${'$'}
            
            ## 10. Desafio de Domínio
            Determine o menor número inteiro positivo que, ao ser dividido por 6, deixa resto 5; ao ser dividido por 5, deixa resto 4; e ao ser dividido por 4, deixa resto 3.
            *Dica:* Observe que em todos os casos falta exatamente 1 unidade para a divisão ser exata (${'$'}n + 1${'$'} é múltiplo comum de 6, 5 e 4). Portanto, ${'$'}n = \text{MMC}(6, 5, 4) - 1 = 60 - 1 = 59${'$'}.
            
            ## 11. Resumo
            - Critérios de divisibilidade aceleram o reconhecimento de fatores primos.
            - O MMC calcula o menor múltiplo comum (pontos de sincronia futura).
            - O MDC calcula o maior divisor comum (partição máxima sem sobra).
            - Relação áurea: ${'$'}\text{MMC}(a, b) \cdot \text{MDC}(a, b) = a \cdot b${'$'}.
            
            ## 12. Referências
            - HEFEZ, Abramo. *Elementos de Aritmética*. Rio de Janeiro: SBM, 2011.
            - INEP. *Matriz de Referência do ENEM: Matemática e suas Tecnologias*. Competência de Área 1. Brasília: INEP/MEC.
            - OPENSTAX. *Prealgebra 2e*, Chapter 2: Integers and Divisibility. Rice University, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Cálculo do MMC por Fatores Canônicos",
                syntax = "\\text{MMC}(a, b) = \\prod p_i^{\\max(\\alpha_i, \\beta_i)}",
                description = "Produto de todos os fatores primos comuns e não comuns, elevados aos seus maiores expoentes.",
                parametersInfo = "Utilizado para sincronicidade e ciclos periódicos."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Cálculo do MDC por Fatores Canônicos",
                syntax = "\\text{MDC}(a, b) = \\prod p_i^{\\min(\\alpha_i, \\beta_i)}",
                description = "Produto exclusivo dos fatores primos comuns, elevados aos seus menores expoentes.",
                parametersInfo = "Utilizado para divisão em partes iguais máximas sem resto."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Relação Fundamental entre MMC e MDC",
                syntax = "\\text{MMC}(a, b) \\times \\text{MDC}(a, b) = a \\cdot b",
                description = "O produto do MMC pelo MDC de dois números é rigorosamente igual ao produto entre esses dois números.",
                parametersInfo = "Válido para quaisquer a, b pertencentes aos inteiros positivos."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Problema de Pista Circular e Ultrapassagem",
                codeOrFormula = "\\text{MMC}(72, 90) = 360\\text{ segundos} = 6\\text{ minutos}",
                explanation = """
                    Dois ciclistas treinam em uma pista fechada, largando juntos do mesmo ponto e no mesmo sentido. O ciclista A completa cada volta em 72 segundos e o ciclista B em 90 segundos.
                    
                    Após quanto tempo da largada o ciclista A alcançará o ciclista B no ponto de partida?
                    
                    Passo 1: Fatorar os períodos:
                    72 = 2³ × 3² = 8 × 9
                    90 = 2 × 3² × 5 = 2 × 9 × 5
                    
                    Passo 2: Calcular o MMC:
                    MMC = 2³ × 3² × 5 = 8 × 9 × 5 = 360 segundos.
                    
                    Passo 3: Converter para minutos:
                    360 ÷ 60 = 6 minutos.
                    
                    Nesse momento, o ciclista A terá dado 360/72 = 5 voltas e o B terá dado 360/90 = 4 voltas.
                """.trimIndent()
            )
        ),
        goldTip = "Leu 'encontro simultâneo, no mesmo instante, periódico'? Calcule MMC. Leu 'cortar no maior tamanho possível sem sobras, repartir igualmente'? Calcule MDC!"
    )

    // =========================================================================
    // AULA 3: Potenciação, Propriedades Operatórias e Radiciação (mb_l3)
    // =========================================================================
    private val bundleMbL3 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 3: Potenciação, Propriedades Operatórias e Radiciação
            
            ## 1. Introdução
            A potenciação e a radiciação formam a linguagem matemática para modelar crescimentos exponenciais, juros compostos, decaimento radioativo e escalas microscópicas. Dominar a manipulação de expoentes fracionários e negativos é o pré-requisito técnico direto para resolver equações exponenciais no ENEM e calcular derivadas pela regra do tombo em Cálculo I.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Manipular todas as 7 propriedades fundamentais da potenciação com segurança algébrica.
            - Interpretar e operar expoentes negativos como inverso multiplicativo (${'$'}a^{-n} = \frac{1}{a^n}${'$'}).
            - Converter radicais em potências com expoentes fracionários (${'$'}\sqrt[n]{a^m} = a^{m/n}${'$'}) e simplificar raízes.
            - Racionalizar denominadores com raízes simples e binômios com conjugados.
            
            ## 3. Pré-requisitos
            - Fatoração de inteiros em potências de primos (Aula 2) e frações (Aula 1).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. Definição Formal de Potência
            Para ${'$'}a \in \mathbb{R}${'$'} e ${'$'}n \in \mathbb{N}^*${'$'}, definimos:
            ${'$'}${'$'}a^n = \underbrace{a \cdot a \cdot a \cdots a}_{n \text{ fatores}}${'$'}${'$'}
            Onde ${'$'}a${'$'} é a **base** e ${'$'}n${'$'} é o **expoente**.
            - Por convenção analítica coerente: ${'$'}a^0 = 1${'$'} para todo ${'$'}a \neq 0${'$'} (pois ${'$'}\frac{a^n}{a^n} = a^{n-n} = a^0 = 1${'$'}).
            - A expressão ${'$'}0^0${'$'} é uma indeterminação matemática.
            
            ### B. As Propriedades Operatórias Fundamentais
            1. **Produto de potências de mesma base:** Mantém-se a base e somam-se os expoentes:
               ${'$'}${'$'}a^m \cdot a^n = a^{m+n}${'$'}${'$'}
            2. **Quociente de potências de mesma base:** Mantém-se a base e subtraem-se os expoentes:
               ${'$'}${'$'}\frac{a^m}{a^n} = a^{m-n} \quad (a \neq 0)${'$'}${'$'}
            3. **Potência de potência:** Mantém-se a base e multiplicam-se os expoentes:
               ${'$'}${'$'}(a^m)^n = a^{m \cdot n} \quad \text{(Atenção: } a^{m^n} \neq (a^m)^n\text{!)}${'$'}${'$'}
            4. **Potência de um produto:** O expoente distribui-se para cada fator:
               ${'$'}${'$'}(a \cdot b)^n = a^n \cdot b^n${'$'}${'$'}
            5. **Potência de um quociente:**
               ${'$'}${'$'}\left(\frac{a}{b}\right)^n = \frac{a^n}{b^n} \quad (b \neq 0)${'$'}${'$'}
            6. **Expoente negativo:** Representa o inverso da base elevado ao expoente positivo:
               ${'$'}${'$'}a^{-n} = \frac{1}{a^n} \quad \text{e} \quad \left(\frac{a}{b}\right)^{-n} = \left(\frac{b}{a}\right)^n${'$'}${'$'}
            
            ### C. Radiciação e a Ponte dos Expoentes Fracionários
            A radiciação é a operação inversa da potenciação: ${'$'}\sqrt[n]{b} = a \iff a^n = b${'$'} (com restrições de sinal nos reais para índices pares: ${'$'}b \ge 0${'$'}).
            - **O Teorema da Ponte:**
              ${'$'}${'$'}\sqrt[n]{a^m} = a^{\frac{m}{n}}${'$'}${'$'}
              *Regra mnemônica:* O que está no sol (índice da raiz, fora) vai para a sombra (denominador); o que está na sombra (expoente interno) vai para o sol (numerador).
            
            ### D. Racionalização de Denominadores
            Elimina raízes irracionais do denominador para facilitar estimativas e comparações:
            1. *Raiz quadrada simples:*
               ${'$'}${'$'}\frac{k}{\sqrt{a}} \times \frac{\sqrt{a}}{\sqrt{a}} = \frac{k\sqrt{a}}{a}${'$'}${'$'}
            2. *Binômio com raiz (uso do produto notável conjugado ${'$'}(x+y)(x-y) = x^2 - y^2${'$'}):*
               ${'$'}${'$'}\frac{k}{\sqrt{a} + \sqrt{b}} \times \frac{\sqrt{a} - \sqrt{b}}{\sqrt{a} - \sqrt{b}} = \frac{k(\sqrt{a} - \sqrt{b})}{a - b}${'$'}${'$'}
            
            ## 5. Definições e Conceitos
            - **Radicando:** O número sob o radical.
            - **Índice do Radical:** Indica a ordem da raiz (quadrada ${'$'}n=2${'$'}, cúbica ${'$'}n=3${'$'}, enésima ${'$'}n${'$'}).
            - **Radicais Semelhantes:** Radicais que possuem o mesmo índice e o mesmo radicando (ex.: ${'$'}3\sqrt{5}${'$'} e ${'$'}-7\sqrt{5}${'$'}), que podem ser somados algebricamente.
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Simplificação Algébrica de Potências de Base 2
            Simplifique a expressão numérica:
            ${'$'}${'$'}E = \frac{2^{n+3} - 2^{n+1}}{2^{n+2}}${'$'}${'$'}
            
            **Resolução Analítica:**
            - *Passo 1:* Decompor as potências no numerador usando ${'$'}a^{m+n} = a^m \cdot a^n${'$'}:
              ${'$'}${'$'}2^{n+3} = 2^n \cdot 2^3 = 8 \cdot 2^n${'$'}${'$'}
              ${'$'}${'$'}2^{n+1} = 2^n \cdot 2^1 = 2 \cdot 2^n${'$'}${'$'}
            - *Passo 2:* Fatorar o termo comum ${'$'}2^n${'$'} em evidência no numerador:
              ${'$'}${'$'}\text{Numerador} = 2^n \cdot (8 - 2) = 6 \cdot 2^n${'$'}${'$'}
            - *Passo 3:* Decompor o denominador:
              ${'$'}${'$'}\text{Denominador} = 2^{n+2} = 2^n \cdot 2^2 = 4 \cdot 2^n${'$'}${'$'}
            - *Passo 4:* Cancelar o fator não-nulo ${'$'}2^n${'$'}:
              ${'$'}${'$'}E = \frac{6 \cdot 2^n}{4 \cdot 2^n} = \frac{6}{4} = \frac{3}{2} = 1{,}5${'$'}${'$'}
            
            ### Exemplo 2: Operações com Expoente Fracionário e Radicais
            Calcule o valor numérico exato de:
            ${'$'}${'$'}K = 8^{2/3} + \left(\frac{1}{16}\right)^{-3/4} - \sqrt{50} + \sqrt{18}${'$'}${'$'}
            
            **Resolução Analítica:**
            - *Termo 1:* ${'$'}8^{2/3} = (2^3)^{2/3} = 2^{3 \cdot \frac{2}{3}} = 2^2 = 4${'$'}.
            - *Termo 2:* Inverter a fração para trocar o sinal do expoente:
              ${'$'}${'$'}\left(\frac{1}{16}\right)^{-3/4} = 16^{3/4} = (2^4)^{3/4} = 2^{4 \cdot \frac{3}{4}} = 2^3 = 8${'$'}${'$'}
            - *Termo 3 e 4 (Simplificação de radicais):*
              ${'$'}${'$'}\sqrt{50} = \sqrt{25 \cdot 2} = 5\sqrt{2}${'$'}${'$'}
              ${'$'}${'$'}\sqrt{18} = \sqrt{9 \cdot 2} = 3\sqrt{2}${'$'}${'$'}
              Logo: ${'$'}-\sqrt{50} + \sqrt{18} = -5\sqrt{2} + 3\sqrt{2} = -2\sqrt{2}${'$'}.
            - *Resultado Final:*
              ${'$'}${'$'}K = 4 + 8 - 2\sqrt{2} = 12 - 2\sqrt{2}${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Crescimento Exponencial e Epidemiologia:** A propagação descontrolada de um vírus onde cada indivíduo infecta 3 pessoas gera a progressão ${'$'}P(t) = P_0 \cdot 3^t${'$'}. Em apenas 10 ciclos, o fator de ampliação atinge ${'$'}3^{10} = 59.049${'$'} vezes o valor inicial!
            - **Acústica e a Escala Decibel:** O ouvido humano responde logaritmicamente à intensidade sonora, que é expressa por potências de 10 da potência de referência (${'$'}10^{-12}\text{ W/m}^2${'$'}).
            
            ## 8. Exercícios de Fixação
            1. Qual é o valor de ${'$'}\left(\frac{27}{8}\right)^{-2/3}${'$'}?
            2. Racionalize a fração ${'$'}\frac{6}{\sqrt{5} - \sqrt{2}}${'$'} e expresse o resultado na forma mais simples.
            
            ## 9. Correção Comentada
            - *Exercício 1:* Inverte-se a base e aplica-se o expoente positivo:
              ${'$'}${'$'}\left(\frac{8}{27}\right)^{2/3} = \frac{8^{2/3}}{27^{2/3}} = \frac{(2^3)^{2/3}}{(3^3)^{2/3}} = \frac{2^2}{3^2} = \frac{4}{9}${'$'}${'$'}
            - *Exercício 2:* Multiplica-se pelo conjugado ${'$'}(\sqrt{5} + \sqrt{2})${'$'}:
              ${'$'}${'$'}\frac{6(\sqrt{5} + \sqrt{2})}{(\sqrt{5} - \sqrt{2})(\sqrt{5} + \sqrt{2})} = \frac{6(\sqrt{5} + \sqrt{2})}{(\sqrt{5})^2 - (\sqrt{2})^2} = \frac{6(\sqrt{5} + \sqrt{2})}{5 - 2} = \frac{6(\sqrt{5} + \sqrt{2})}{3} = 2(\sqrt{5} + \sqrt{2})${'$'}${'$'}
            
            ## 10. Desafio de Domínio
            Determine qual número é maior sem calculadora: ${'$'}2^{300}${'$'} ou ${'$'}3^{200}${'$'}.
            *Resolução:* Escreva ambos com o mesmo expoente usando potência de potência:
            ${'$'}${'$'}2^{300} = (2^3)^{100} = 8^{100}${'$'}${'$'}
            ${'$'}${'$'}3^{200} = (3^2)^{100} = 9^{100}${'$'}${'$'}
            Como as bases são ${'$'}8 < 9${'$'} e os expoentes são idênticos positivos (100), concluímos rigorosamente que ${'$'}3^{200} > 2^{300}${'$'}!
            
            ## 11. Resumo
            - Multiplicação de mesma base soma expoentes; divisão subtrai.
            - Expoente negativo inverte a fração: ${'$'}\left(\frac{a}{b}\right)^{-n} = \left(\frac{b}{a}\right)^n${'$'}.
            - Raiz é expoente fracionário: ${'$'}\sqrt[n]{a^m} = a^{m/n}${'$'}.
            - Para simplificar potências com variáveis no expoente, coloque a menor potência em evidência.
            
            ## 12. Referências
            - LIMA, Elon Lages et al. *A Matemática do Ensino Médio*, Volume 1. Coleção do Professor de Matemática. Rio de Janeiro: SBM, 2016.
            - OPENSTAX. *College Algebra 2e*, Chapter 1: Prerequisites - Exponents and Radicals. Rice University, 2021.
            - STEWART, James. *Cálculo*, Volume 1: Apêndice A (Álgebra e Trigonometria). São Paulo: Cengage, 2013.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Conversão Raiz em Potência Fracionária",
                syntax = "\\sqrt[n]{a^m} = a^{\\frac{m}{n}}",
                description = "Transformação fundamental de qualquer raiz em expoente fracionário.",
                parametersInfo = "n >= 2, a > 0 se n for par."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Inverso de Potência (Expoente Negativo)",
                syntax = "a^{-n} = \\frac{1}{a^n} \\quad \\text{e} \\quad \\left(\\frac{a}{b}\\right)^{-n} = \\left(\\frac{b}{a}\\right)^n",
                description = "O sinal negativo no expoente indica inversão da base da potência.",
                parametersInfo = "a ≠ 0 e b ≠ 0"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Racionalização por Conjugado",
                syntax = "\\frac{k}{\\sqrt{a} \\pm \\sqrt{b}} = \\frac{k(\\sqrt{a} \\mp \\sqrt{b})}{a - b}",
                description = "Aplicação do produto notável da diferença de quadrados para eliminar raízes do denominador.",
                parametersInfo = "a ≠ b, a > 0 e b > 0"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Simplificação com Fator Comum em Evidência",
                codeOrFormula = "\\frac{3^{x+2} + 3^x}{3^{x+1} - 3^x} = \\frac{3^x(9+1)}{3^x(3-1)} = \\frac{10}{2} = 5",
                explanation = """
                    Enunciado: Simplifique a expressão para todo x real:
                    E = (3^(x+2) + 3^x) / (3^(x+1) - 3^x)
                    
                    Passo 1: Decompor as potências:
                    3^(x+2) = 3^x × 3² = 9 × 3^x
                    3^(x+1) = 3^x × 3¹ = 3 × 3^x
                    
                    Passo 2: Isolar 3^x em evidência no numerador e no denominador:
                    Numerador = 3^x × (9 + 1) = 10 × 3^x
                    Denominador = 3^x × (3 - 1) = 2 × 3^x
                    
                    Passo 3: Cancelar 3^x:
                    E = (10 × 3^x) / (2 × 3^x) = 10/2 = 5.
                    O resultado é uma constante independente de x!
                """.trimIndent()
            )
        ),
        goldTip = "Em expressões exponenciais com somas no numerador e denominador, JAMAIS corte termos individualmente! Fatore a menor potência em evidência antes de cancelar!"
    )

    // =========================================================================
    // AULA 4: Notação Científica e Ordem de Grandeza (mb_l4)
    // =========================================================================
    private val bundleMbL4 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 4: Notação Científica e Ordem de Grandeza
            
            ## 1. Introdução
            Da distância entre a Terra e o Sol (${'$'}\approx 150.000.000\text{ km}${'$'}) ao raio de um átomo de hidrogênio (${'$'}\approx 0{,}000000000053\text{ m}${'$'}), a natureza opera em ordens de magnitude extremas. A Notação Científica padroniza esses valores numéricos para facilitar operações aritméticas, eliminar erros de contagem de zeros e permitir comparações físicas instantâneas na prova de Ciências da Natureza e Matemática do ENEM.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Escrever qualquer número real na forma padrão de Notação Científica: ${'$'}N \times 10^k${'$'}, com ${'$'}1 \le |N| < 10${'$'} e ${'$'}k \in \mathbb{Z}${'$'}.
            - Efetuar adições, subtrações, multiplicações e divisões entre números em notação científica.
            - Determinar a **Ordem de Grandeza** precisa adotando o critério matemático da média geométrica (${'$'}\sqrt{10} \approx 3{,}16${'$'}).
            - Interpretar prefixos métricos do Sistema Internacional (micro, mili, quilo, mega, giga).
            
            ## 3. Pré-requisitos
            - Potenciação com base 10 e expoentes negativos (Aula 3) e números decimais (Aula 1).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. O Formato Canônico da Notação Científica
            Qualquer número real não-nulo ${'$'}X${'$'} pode ser expresso como:
            ${'$'}${'$'}X = N \times 10^k${'$'}${'$'}
            Onde:
            - **${'$'}N${'$'} (Mantissa ou Coeficiente):** Número real tal que ${'$'}1 \le |N| < 10${'$'}. Deve haver **exatamente um dígito diferente de zero** antes da vírgula.
            - **${'$'}k${'$'} (Ordem Exponencial):** Número inteiro (${'$'}k \in \mathbb{Z}${'$'}).
              - Se o número original é grande (${'$'}> 10${'$'}), a vírgula anda para a **esquerda** e ${'$'}k${'$'} é **positivo** (ex.: ${'$'}450.000 = 4{,}5 \times 10^5${'$'}).
              - Se o número original é pequeno (${'$'}< 1${'$'}), a vírgula anda para a **direita** e ${'$'}k${'$'} é **negativo** (ex.: ${'$'}0{,}00032 = 3{,}2 \times 10^{-4}${'$'}).
            
            ### B. Aritmética em Notação Científica
            - **Multiplicação:** Multiplicam-se os coeficientes e somam-se os expoentes:
              ${'$'}${'$'}(a \times 10^p) \times (b \times 10^q) = (a \cdot b) \times 10^{p+q}${'$'}${'$'}
              *Ajuste final:* Se ${'$'}a \cdot b \ge 10${'$'}, converte-se novamente para ${'$'}1 \le N < 10${'$'}.
            - **Divisão:** Dividem-se os coeficientes e subtraem-se os expoentes:
              ${'$'}${'$'}\frac{a \times 10^p}{b \times 10^q} = \left(\frac{a}{b}\right) \times 10^{p-q}${'$'}${'$'}
            - **Adição e Subtração:** **Obrigatório igualar os expoentes** antes de somar ou subtrair as mantissas:
              ${'$'}${'$'}3{,}2 \times 10^4 + 5{,}1 \times 10^3 = 3{,}2 \times 10^4 + 0{,}51 \times 10^4 = (3{,}2 + 0{,}51) \times 10^4 = 3{,}71 \times 10^4${'$'}${'$'}
            
            ### C. Ordem de Grandeza (O.G.)
            A Ordem de Grandeza é a potência de 10 mais próxima do número avaliado.
            Para decidir se ${'$'}N \times 10^k${'$'} está mais próximo de ${'$'}10^k${'$'} ou de ${'$'}10^{k+1}${'$'}, calcula-se o ponto médio geométrico da escala logarítmica:
            ${'$'}${'$'}\sqrt{10^k \times 10^{k+1}} = \sqrt{10^{2k+1}} = 10^k \cdot \sqrt{10} \approx 10^k \times 3{,}162${'$'}${'$'}
            - **Regra de Ouro da Ordem de Grandeza:**
              - Se a mantissa ${'$'}N < \sqrt{10}${'$'} (${'$'}\approx 3{,}16${'$'}), a O.G. é ${'$'}10^k${'$'}.
              - Se a mantissa ${'$'}N \ge \sqrt{10}${'$'} (${'$'}\approx 3{,}16${'$'}), a O.G. é ${'$'}10^{k+1}${'$'}.
            
            ### D. Tabela de Prefixos do Sistema Internacional (SI)
            | Prefixo | Símbolo | Fator Multiplicativo | Exemplo Prático |
            | :--- | :--- | :--- | :--- |
            | Giga | G | ${'$'}10^9${'$'} | 1 Gigabyte (${'$'}10^9\text{ bytes}${'$'}) |
            | Mega | M | ${'$'}10^6${'$'} | 1 Megawatt (${'$'}10^6\text{ W}${'$'}) |
            | Quilo | k | ${'$'}10^3${'$'} | 1 Quilômetro (${'$'}10^3\text{ m}${'$'}) |
            | Mili | m | ${'$'}10^{-3}${'$'} | 1 Mililitro (${'$'}10^{-3}\text{ L}${'$'}) |
            | Micro | ${'$'}\mu${'$'} | ${'$'}10^{-6}${'$'} | 1 Micrômetro (${'$'}10^{-6}\text{ m}${'$'}) |
            | Nano | n | ${'$'}10^{-9}${'$'} | 1 Nanômetro (${'$'}10^{-9}\text{ m}${'$'}) |
            
            ## 5. Definições e Conceitos
            - **Algarismos Significativos:** Algarismos que carregam precisão experimental em uma medida (todos os dígitos conhecidos com certeza mais o primeiro dígito duvidoso).
            - **Escala Logarítmica:** Escala onde cada intervalo unitário representa uma multiplicação por uma constante de base (habitualmente 10).
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Operações Mistas em Notação Científica
            Calcule o resultado da expressão abaixo e indique sua Ordem de Grandeza:
            ${'$'}${'$'}Q = \frac{(4{,}8 \times 10^8) \times (3{,}0 \times 10^{-2})}{1{,}2 \times 10^3}${'$'}${'$'}
            
            **Resolução Analítica:**
            - *Passo 1 (Multiplicação no numerador):*
              ${'$'}${'$'}\text{Numerador} = (4{,}8 \times 3{,}0) \times 10^{8 + (-2)} = 14{,}4 \times 10^6${'$'}${'$'}
            - *Passo 2 (Divisão pelo denominador):*
              ${'$'}${'$'}Q = \frac{14{,}4 \times 10^6}{1{,}2 \times 10^3} = \left(\frac{14{,}4}{1{,}2}\right) \times 10^{6 - 3} = 12 \times 10^3${'$'}${'$'}
            - *Passo 3 (Ajuste para forma canônica ${'$'}1 \le N < 10${'$'}):*
              ${'$'}${'$'}12 = 1{,}2 \times 10^1 \implies Q = 1{,}2 \times 10^1 \times 10^3 = 1{,}2 \times 10^4${'$'}${'$'}
            - *Passo 4 (Determinação da Ordem de Grandeza):*
              A mantissa é ${'$'}N = 1{,}2${'$'}. Como ${'$'}1{,}2 < 3{,}16${'$'}, a potência não é incrementada:
              ${'$'}${'$'}\text{Ordem de Grandeza} = 10^4${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Física Nuclear e Química:** A constante de Avogadro (${'$'}6{,}022 \times 10^{23}\text{ partículas/mol}${'$'}) permite quantificar átomos imperceptíveis aos olhos humanos em gramas mensuráveis de reagentes.
            - **Astronomia:** A velocidade da luz no vácuo (${'$'}c \approx 3{,}0 \times 10^8\text{ m/s}${'$'}) percorrida durante um ano terrestre define a unidade de comprimento astronômica conhecida como Ano-Luz:
              ${'$'}${'$'}1\text{ ano-luz} \approx (3 \times 10^8\text{ m/s}) \times (3{,}15 \times 10^7\text{ s}) \approx 9{,}46 \times 10^{15}\text{ metros}${'$'}${'$'}
            
            ## 8. Exercícios de Fixação
            1. Um glóbulo vermelho humano tem diâmetro médio de ${'$'}0{,}0000075\text{ metros}${'$'}. Escreva esse valor em notação científica e determine sua ordem de grandeza.
            2. Calcule o valor exato de ${'$'}(8{,}0 \times 10^5) - (3{,}0 \times 10^4)${'$'} em notação científica.
            
            ## 9. Correção Comentada
            - *Exercício 1:*
              - Andando com a vírgula 6 casas para a direita: ${'$'}7{,}5 \times 10^{-6}\text{ m}${'$'}.
              - Como a mantissa ${'$'}N = 7{,}5 \ge 3{,}16${'$'}, somamos 1 ao expoente:
                ${'$'}${'$'}\text{Ordem de Grandeza} = 10^{-6 + 1} = 10^{-5}\text{ m}${'$'}${'$'}
            - *Exercício 2:*
              - Igualar os expoentes: ${'$'}3{,}0 \times 10^4 = 0{,}3 \times 10^5${'$'}.
              - Subtrair: ${'$'}(8{,}0 - 0{,}3) \times 10^5 = 7{,}7 \times 10^5${'$'}.
            
            ## 10. Desafio de Domínio
            O consumo diário de água de uma metrópole com 10 milhões de habitantes é de 200 litros por pessoa. Se um reservatório cúbico possui aresta de ${'$'}1\text{ km}${'$'} (${'$'}10^3\text{ m}${'$'}), durante quantos dias esse reservatório cheio conseguiria abastecer a metrópole?
            *Resolução:*
            - População: ${'$'}10^7${'$'} pessoas.
            - Consumo diário: ${'$'}10^7 \times 200\text{ L} = 2 \times 10^9\text{ litros/dia}${'$'}.
            - Volume do reservatório: ${'$'}V = (10^3\text{ m})^3 = 10^9\text{ m}^3${'$'}.
            - Como ${'$'}1\text{ m}^3 = 10^3\text{ L}${'$'}: ${'$'}V = 10^9 \times 10^3 = 10^{12}\text{ litros}${'$'}.
            - Quantidade de dias: ${'$'}\frac{10^{12}\text{ L}}{2 \times 10^9\text{ L/dia}} = \frac{1}{2} \times 10^3 = 0{,}5 \times 10^3 = 500\text{ dias}${'$'}!
            
            ## 11. Resumo
            - Notação científica canônica: ${'$'}N \times 10^k${'$'} com ${'$'}1 \le N < 10${'$'}.
            - Na soma/subtração, deve-se igualar a base exponencial antes de operar.
            - Ordem de grandeza usa a referência ${'$'}\sqrt{10} \approx 3{,}16${'$'} para arredondamento exponencial.
            - Memorize os prefixos SI: ${'$'}\mu${'$'} (${'$'}10^{-6}${'$'}), m (${'$'}10^{-3}${'$'}), k (${'$'}10^3${'$'}), M (${'$'}10^6${'$'}), G (${'$'}10^9${'$'}).
            
            ## 12. Referências
            - HALLIDAY, David; RESNICK, Robert; WALKER, Jearl. *Fundamentos de Física*, Volume 1: Mecânica. Capítulo 1: Medição. Rio de Janeiro: LTC, 2016.
            - INEP. *Relatório Pedagógico do ENEM: Ciências da Natureza e suas Tecnologias*. Brasília: INEP, 2022.
            - NIST. *The International System of Units (SI)*. National Institute of Standards and Technology Special Publication 330, 2019.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Forma Padrão da Notação Científica",
                syntax = "X = N \\times 10^k \\quad (1 \\le |N| < 10, \\; k \\in \\mathbb{Z})",
                description = "Representação canônica internacional de magnitudes físicas e matemáticas.",
                parametersInfo = "N é a mantissa e k é a ordem de magnitude."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Critério da Ordem de Grandeza (O.G.)",
                syntax = "\\text{O.G.} = \\begin{cases} 10^k, & \\text{se } N < \\sqrt{10} \\approx 3{,}16 \\\\ 10^{k+1}, & \\text{se } N \\ge \\sqrt{10} \\approx 3{,}16 \\end{cases}",
                description = "Determinação analítica da potência de 10 mais próxima baseada na média geométrica.",
                parametersInfo = "A fronteira 3,16 decorre de 10^0.5."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Cálculo de Densidade Populacional de Bactérias",
                codeOrFormula = "\\text{Concentração} = \\frac{4{,}5 \\times 10^8}{1{,}5 \\times 10^{-3}} = 3{,}0 \\times 10^{11}\\text{ bactérias/L}",
                explanation = """
                    Uma cultura biológica contém 4,5 × 10⁸ bactérias em um volume de 1,5 mL (1,5 × 10⁻³ L).
                    Qual é a concentração em bactérias por litro e sua ordem de grandeza?
                    
                    Passo 1: Dividir os coeficientes e subtrair os expoentes:
                    (4,5 / 1,5) × 10^(8 - (-3)) = 3,0 × 10^(8 + 3) = 3,0 × 10¹¹ bactérias/L.
                    
                    Passo 2: Avaliar a ordem de grandeza:
                    Como 3,0 < 3,16, a ordem de grandeza é 10¹¹ bactérias por litro!
                """.trimIndent()
            )
        ),
        goldTip = "Cuidado no ENEM: O critério oficial adota a média geométrica √10 ≈ 3,16 e NÃO a média aritmética 5,5! Se o coeficiente for 4, a ordem de grandeza já pula para a próxima potência (10^(k+1))!"
    )
}
