package com.example.data.math.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo Didático Aprofundado de Matemática Básica — Parte 3 (Aulas 9 a 12).
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
object BasicMathDidacticPart3 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "mb_l9" -> bundleMbL9
            "mb_l10" -> bundleMbL10
            "mb_l11" -> bundleMbL11
            "mb_l12" -> bundleMbL12
            else -> null
        }
    }

    // =========================================================================
    // AULA 9: Porcentagem, Aumentos e Descontos Sucessivos (mb_l9)
    // =========================================================================
    private val bundleMbL9 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 9: Porcentagem, Variação Percentual e Fatores Sucessivos
            
            ## 1. Introdução
            A porcentagem é a linguagem universal das relações econômicas, índices de inflação, pesquisas eleitorais, descontos comerciais e análises epidemiológicas. No ENEM, problemas envolvendo aumentos e descontos sucessivos são desenhados para derrubar o aluno que apenas "soma as porcentagens" (ex.: achar que um aumento de 10% seguido de outro de 10% equivale a 20%). A chave conceitual reside na utilização de **Fatores Multiplicativos**.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Representar qualquer porcentagem nas três formas equivalentes: percentual (${'$'}p\%${'$'}), fracionária (${'$'}\frac{p}{100}${'$'}) e decimal (${'$'}0{,}0p${'$'}).
            - Aplicar instantaneamente o **Fator Multiplicativo de Aumento** (${'$'}1 + i${'$'}) e de **Desconto** (${'$'}1 - i${'$'}).
            - Resolver aumentos e descontos sucessivos através do produto direto de seus fatores.
            - Calcular a variação percentual relativa: ${'$'}\Delta\% = \frac{V_f - V_i}{V_i} \times 100\%${'$'}.
            - Determinar o valor original antes de um reajuste através da divisão pelo fator multiplicativo.
            
            ## 3. Pré-requisitos
            - Frações e decimais (Aula 1) e equações do 1º grau (Aula 6).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. Definição Formal de Porcentagem
            A expressão ${'$'}p\%${'$'} ("${'$'}p${'$'} por cento") é simplesmente uma fração com denominador 100:
            ${'$'}${'$'}p\% = \frac{p}{100}${'$'}${'$'}
            - Exemplos de conversão rápida:
              - ${'$'}15\% = \frac{15}{100} = 0{,}15${'$'}
              - ${'$'}7{,}5\% = \frac{7{,}5}{100} = 0{,}075${'$'}
              - ${'$'}120\% = \frac{120}{100} = 1{,}20${'$'}
            
            ### B. A Técnica do Fator Multiplicativo
            Em vez de calcular a porcentagem e depois somar ou subtrair do valor inicial, multiplicamos o valor diretamente pelo **fator de atualização** (${'$'}F${'$'}):
            1. **Aumento de ${'$'}i\%${'$'}:**
               ${'$'}${'$'}V_{\text{final}} = V_{\text{inicial}} \times (1 + i)${'$'}${'$'}
               - Aumento de ${'$'}10\% \implies F = 1 + 0{,}10 = 1{,}10${'$'}.
               - Aumento de ${'$'}25\% \implies F = 1 + 0{,}25 = 1{,}25${'$'}.
               - Aumento de ${'$'}4\% \implies F = 1 + 0{,}04 = 1{,}04${'$'}.
            2. **Desconto de ${'$'}i\%${'$'}:**
               ${'$'}${'$'}V_{\text{final}} = V_{\text{inicial}} \times (1 - i)${'$'}${'$'}
               - Desconto de ${'$'}15\% \implies F = 1 - 0{,}15 = 0{,}85${'$'}.
               - Desconto de ${'$'}30\% \implies F = 1 - 0{,}30 = 0{,}70${'$'}.
               - Desconto de ${'$'}8\% \implies F = 1 - 0{,}08 = 0{,}92${'$'}.
            
            ### C. Aumentos e Descontos Sucessivos
            Quando um valor sofre múltiplos reajustes sucessivos, o fator acumulado total é o **produto dos fatores individuais**:
            ${'$'}${'$'}F_{\text{total}} = F_1 \times F_2 \times F_3 \cdots \times F_n${'$'}${'$'}
            - *Exemplo de Aumentos Sucessivos:* Dois aumentos de ${'$'}10\%${'$'}:
              ${'$'}${'$'}F_{\text{total}} = 1{,}10 \times 1{,}10 = 1{,}21 \implies \text{Aumento real de } 21\%\text{ (e não 20%!)}${'$'}${'$'}
            - *Exemplo Misto:* Um aumento de ${'$'}20\%${'$'} seguido de um desconto de ${'$'}20\%${'$'}:
              ${'$'}${'$'}F_{\text{total}} = 1{,}20 \times 0{,}80 = 0{,}96 \implies \text{Prejuízo real de } 4\%\text{ (e não volta ao valor inicial!)}${'$'}${'$'}
            
            ### D. Variação Percentual Relativa
            Para medir o crescimento ou queda percentual entre dois momentos no tempo:
            ${'$'}${'$'}\Delta\% = \frac{V_{\text{final}} - V_{\text{inicial}}}{V_{\text{inicial}}} \times 100\%${'$'}${'$'}
            - O denominador é **sempre o valor de referência original** (${'$'}V_{\text{inicial}}${'$'}).
            
            ### E. Retorno ao Valor Original (Cálculo Reverso)
            Se um produto passou a custar R${'$'} 132,00 após um aumento de 20%, qual era o preço original?
            - *Erro grave do candidato desatento:* Calcular ${'$'}20\%${'$'} de 132 e subtrair (${'$'}132 - 26{,}40 = 105{,}60${'$'}). Isso está completamente errado!
            - *Cálculo analítico correto:*
              ${'$'}${'$'}V_{\text{inicial}} \times (1 + 0{,}20) = 132 \implies V_{\text{inicial}} \times 1{,}2 = 132 \implies V_{\text{inicial}} = \frac{132}{1{,}2} = \text{R${'$'}} 110,00${'$'}${'$'}
            
            ## 5. Definições e Conceitos
            - **Ponto Percentual (p.p.):** Diferença aritmética absoluta entre duas taxas percentuais. Se uma taxa sobe de ${'$'}10\%${'$'} para ${'$'}12\%${'$'}, ela subiu **2 pontos percentuais**, mas sofreu um aumento relativo de **20%** (${'$'}\frac{12-10}{10} = 0{,}20${'$'}).
            - **Desconto Comercial vs. Desconto Racional:** Na prática comercial comum, o abatimento é aplicado diretamente sobre o valor nominal do bem.
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Inflação Acumulada em Trimestre
            Um produto sofreu reajustes de inflação nos três primeiros meses do ano: aumento de 5% em janeiro, aumento de 8% em fevereiro e redução promocional de 10% em março. Qual foi a variação percentual acumulada real ao final do primeiro trimestre?
            
            **Resolução Analítica:**
            - *Passo 1 (Determinação dos fatores multiplicativos individuais):*
              - Janeiro (${'$'}+5\%${'$'}): ${'$'}F_1 = 1 + 0{,}05 = 1{,}05${'$'}.
              - Fevereiro (${'$'}+8\%${'$'}): ${'$'}F_2 = 1 + 0{,}08 = 1{,}08${'$'}.
              - Março (${'$'}-10\%${'$'}): ${'$'}F_3 = 1 - 0{,}10 = 0{,}90${'$'}.
            - *Passo 2 (Cálculo do fator acumulado total):*
              ${'$'}${'$'}F_{\text{total}} = 1{,}05 \times 1{,}08 \times 0{,}90${'$'}${'$'}
              ${'$'}${'$'}1{,}05 \times 1{,}08 = 1{,}134${'$'}${'$'}
              ${'$'}${'$'}1{,}134 \times 0{,}90 = 1{,}0206${'$'}${'$'}
            - *Passo 3 (Interpretação do resultado):*
              Como ${'$'}1{,}0206 > 1${'$'}, houve um aumento acumulado real de:
              ${'$'}${'$'}(1{,}0206 - 1) \times 100\% = +2{,}06\%${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Educação Financeira e Investimentos:** Comparação entre taxa de rendimento nominal de uma aplicação financeira (${'$'}12\%\text{ a.a.}${'$'}) e a inflação oficial (IPCA de ${'$'}5\%\text{ a.a.}${'$'}). O ganho real é regido pela Equação de Fisher:
              ${'$'}${'$'}(1 + i_{\text{real}}) = \frac{1 + i_{\text{nominal}}}{1 + i_{\text{inflação}}} = \frac{1{,}12}{1{,}05} \approx 1{,}0667 \implies \text{Ganho real de } 6{,}67\%${'$'}${'$'}
            - **Tributação e Margem de Lucro:** Determinação do preço de venda com base no *markup* e impostos em cascata (ICMS, PIS, COFINS).
            
            ## 8. Exercícios de Fixação
            1. Um lojista anuncia uma liquidação: "Compre hoje com 30% de desconto e, se pagar à vista no PIX, ganhe mais 10% de desconto adicional sobre o saldo". Qual é o desconto percentual real total obtido pelo cliente à vista?
            2. Uma empresa reduziu sua folha de pagamentos em 20%. Meses depois, para retornar exatamente ao valor financeiro anterior da folha, de quantos por cento ela deverá aumentar a folha atual?
            
            ## 9. Correção Comentada
            - *Exercício 1:* Multiplicação dos fatores de desconto:
              ${'$'}${'$'}F = (1 - 0{,}30) \times (1 - 0{,}10) = 0{,}70 \times 0{,}90 = 0{,}63${'$'}${'$'}
              Como o cliente pagará 63% do valor inicial, o desconto real total foi de:
              ${'$'}${'$'}100\% - 63\% = 37\%\text{ (e não 40%!)}${'$'}${'$'}
            - *Exercício 2:*
              Se a folha era 100, após redução de 20% passou a 80.
              Para ir de 80 de volta para 100, o aumento absoluto necessário é de 20.
              Variação percentual sobre a base 80:
              ${'$'}${'$'}\Delta\% = \frac{20}{80} = \frac{1}{4} = 25\%${'$'}${'$'}
              Portanto, é necessário um aumento de **25%** para compensar uma queda prévia de 20%!
            
            ## 10. Desafio de Domínio
            O preço da gasolina subiu 25%. Para que um motorista gaste exatamente a mesma quantia de dinheiro mensal que gastava antes, qual deve ser a porcentagem de redução no volume de combustível abastecido?
            *Resolução:*
            - Seja ${'$'}G${'$'} o gasto mensal: ${'$'}G = P \times V${'$'}.
            - O novo preço é ${'$'}P' = 1{,}25 P${'$'}.
            - Queremos que ${'$'}P' \times V' = P \times V \implies 1{,}25 P \times V' = P \times V \implies V' = \frac{V}{1{,}25} = \frac{V}{\frac{5}{4}} = \frac{4}{5} V = 0{,}80 V${'$'}.
            - Como o novo volume é 80% do volume anterior, a redução de consumo deve ser de exatamente **20%**!
            
            ## 11. Resumo
            - Aumento de ${'$'}i\% \implies${'$'} multiplicar por ${'$'}(1 + i)${'$'}.
            - Desconto de ${'$'}i\% \implies${'$'} multiplicar por ${'$'}(1 - i)${'$'}.
            - Variações sucessivas se multiplicam, nunca se somam diretamente!
            - Para desfazer um reajuste de ${'$'}i\%${'$'}, divide-se pelo fator ${'$'}(1 + i)${'$'}.
            
            ## 12. Referências
            - ASSAF NETO, Alexandre. *Matemática Financeira e suas Aplicações*. 14. ed. São Paulo: Atlas, 2019.
            - BRASIL. MEC/INEP. *Exame Nacional do Ensino Médio: Matriz de Referência e Relatórios de Desempenho*. Brasília: INEP.
            - OPENSTAX. *Prealgebra 2e*, Chapter 6: Percents. Rice University, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Fatores Multiplicativos de Atualização",
                syntax = "F_{\\text{aumento}} = (1 + i) \\quad \\text{e} \\quad F_{\\text{desconto}} = (1 - i)",
                description = "Transformação direta de variações percentuais em operadores multiplicativos.",
                parametersInfo = "i é a taxa percentual na forma decimal (ex: 20% -> i = 0,20)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Variação Percentual Acumulada Sucessiva",
                syntax = "F_{\\text{total}} = \\prod_{k=1}^n F_k \\implies \\Delta\\% = (F_{\\text{total}} - 1) \\times 100\\%",
                description = "Composição de múltiplos aumentos e descontos sucessivos no tempo.",
                parametersInfo = "Se F_total > 1: aumento acumulado; se F_total < 1: desconto acumulado."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Fórmula da Variação Percentual Relativa",
                syntax = "\\Delta\\% = \\frac{V_f - V_i}{V_i} \\times 100\\%",
                description = "Cálculo analítico do ganho ou perda percentual relativo à base de partida.",
                parametersInfo = "Vi é obrigatoriamente o valor anterior inicial."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Problema ENEM: Aumento e Desconto Sucessivo no Comércio",
                codeOrFormula = "F_{\\text{total}} = 1{,}20 \\times 0{,}85 = 1{,}02 \\implies +2\\%",
                explanation = """
                    Um produto teve seu preço reajustado em 20% no mês de outubro. Em novembro, devido à Black Friday, o comerciante concedeu um desconto de 15% sobre o preço já reajustado.
                    Qual foi a variação percentual final do preço em relação ao valor original de setembro?
                    
                    Passo 1: Identificar os fatores:
                    F1 (aumento de 20%) = 1 + 0,20 = 1,20
                    F2 (desconto de 15%) = 1 - 0,15 = 0,85
                    
                    Passo 2: Multiplicar os fatores:
                    F_total = 1,20 × 0,85 = 1,02
                    
                    Passo 3: Interpretar o resultado:
                    1,02 - 1 = +0,02, o que corresponde a um aumento real de exatamente 2%!
                """.trimIndent()
            )
        ),
        goldTip = "Para calcular aumentos e descontos mentalmente no ENEM: 10% é só andar uma casa com a vírgula para a esquerda; 1% é andar duas casas. Decomponha qualquer porcentagem em blocos de 10%, 5% e 1%!"
    )

    // =========================================================================
    // AULA 10: Álgebra, Produtos Notáveis, Fatoração e Equações (mb_l10)
    // =========================================================================
    private val bundleMbL10 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 10: Expressões Algébricas, Produtos Notáveis, Fatoração, Equações e Inequações
            
            ## 1. Introdução
            A álgebra é a transição da aritmética concreta para o pensamento abstrato e generalizável. Seja para expressar lucros em função de unidades vendidas (${'$'}L(x) = R(x) - C(x)${'$'}), determinar raízes de funções quadráticas ou calcular limites indeterminados em Cálculo I através de cancelamento de fatores algébricos, o domínio dos produtos notáveis e da fatoração é a ferramenta mais poderosa e versátil do estudante de exatas.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Operar polinômios e expressões algébricas (monômios, termos semelhantes, graus).
            - Aplicar com fluência os três principais Produtos Notáveis: Quadrado da Soma, Quadrado da Diferença e Produto da Soma pela Diferença.
            - Fatorar expressões através de: Fator Comum em Evidência, Agrupamento, Diferença de Quadrados e Trinômio Quadrado Perfeito.
            - Resolver equações e inequações do 1º grau interpretando intervalos e sinais.
            - Resolver equações do 2º grau pela Fórmula de Bhaskara e pelas Relações de Girard (Soma e Produto).
            - Resolver sistemas lineares 2x2 pelos métodos da Substituição e da Adição.
            
            ## 3. Pré-requisitos
            - Potenciação, radiciação (Aula 3) e regra dos sinais (Aula 1).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. Os Produtos Notáveis Fundamentais
            São identidades algébricas canônicas que aceleram multiplicações polinomiais frequentes:
            1. **Quadrado da Soma de Dois Termos:**
               ${'$'}${'$'}(a + b)^2 = a^2 + 2ab + b^2${'$'}${'$'}
               *Atenção extrema:* ${'$'}(a + b)^2 \neq a^2 + b^2${'$'}! O termo central ${'$'}2ab${'$'} ("duas vezes o primeiro pelo segundo") é indispensável.
            2. **Quadrado da Diferença de Dois Termos:**
               ${'$'}${'$'}(a - b)^2 = a^2 - 2ab + b^2${'$'}${'$'}
            3. **Produto da Soma pela Diferença (Diferença de Quadrados):**
               ${'$'}${'$'}(a + b)(a - b) = a^2 - b^2${'$'}${'$'}
            4. **Cubo da Soma e da Diferença:**
               ${'$'}${'$'}(a \pm b)^3 = a^3 \pm 3a^2b + 3ab^2 \pm b^3${'$'}${'$'}
            
            ### B. Técnicas de Fatoração Algébrica
            Fatorar significa transformar uma soma ou subtração de termos em um **produto** de fatores:
            1. **Fator Comum em Evidência:** Identifica-se o MDC dos coeficientes e as variáveis comuns com seus menores expoentes:
               ${'$'}${'$'}ax + ay = a(x + y)${'$'}${'$'}
               Exemplo: ${'$'}6x^3 + 9x^2 = 3x^2(2x + 3)${'$'}.
            2. **Agrupamento:** Aplica-se o fator comum sucessivamente em blocos:
               ${'$'}${'$'}ax + bx + ay + by = x(a + b) + y(a + b) = (a + b)(x + y)${'$'}${'$'}
            3. **Diferença de Quadrados:**
               ${'$'}${'$'}a^2 - b^2 = (a + b)(a - b)${'$'}${'$'}
               Exemplo: ${'$'}4x^2 - 25 = (2x + 5)(2x - 5)${'$'}.
            4. **Trinômio Quadrado Perfeito (TQP):**
               ${'$'}${'$'}a^2 \pm 2ab + b^2 = (a \pm b)^2${'$'}${'$'}
            
            ### C. Equações e Inequações do 1º Grau
            - **Equação Geral:** ${'$'}ax + b = 0 \iff x = -\frac{b}{a} \quad (a \neq 0)${'$'}.
            - **Inequação:** ${'$'}ax + b > 0${'$'} ou ${'$'}ax + b \le 0${'$'}.
              - *Regra Crítica das Inequações:* Ao multiplicar ou dividir ambos os membros por um número **negativo**, o sentido da desigualdade **inverte obrigatoriamente**!
                ${'$'}${'$'}-2x > 6 \iff x < \frac{6}{-2} \iff x < -3${'$'}${'$'}
            
            ### D. Equação do 2º Grau (Quadrática)
            Forma geral: ${'$'}ax^2 + bx + c = 0${'$'} (${'$'}a \neq 0${'$'}).
            - **Discriminante (${'$'}\Delta${'$'}):**
              ${'$'}${'$'}\Delta = b^2 - 4ac${'$'}${'$'}
              - Se ${'$'}\Delta > 0${'$'}: duas raízes reais e distintas (${'$'}x_1 \neq x_2${'$'}).
              - Se ${'$'}\Delta = 0${'$'}: uma raiz real dupla (${'$'}x_1 = x_2 = -\frac{b}{2a}${'$'}).
              - Se ${'$'}\Delta < 0${'$'}: nenhuma raiz real (${'$'}\mathbb{R}${'$'}).
            - **Fórmula de Bhaskara:**
              ${'$'}${'$'}x = \frac{-b \pm \sqrt{\Delta}}{2a}${'$'}${'$'}
            - **Relações de Girard (Soma e Produto):**
              ${'$'}${'$'}S = x_1 + x_2 = -\frac{b}{a} \quad \text{e} \quad P = x_1 \cdot x_2 = \frac{c}{a}${'$'}${'$'}
              Para equações mônicas (${'$'}a = 1${'$'}), a equação tem o formato:
              ${'$'}${'$'}x^2 - Sx + P = 0${'$'}${'$'}
            
            ### E. Sistemas Lineares 2x2
            ${'$'}${'$'}\begin{cases} a_1 x + b_1 y = c_1 \\ a_2 x + b_2 y = c_2 \end{cases}${'$'}${'$'}
            - **Método da Substituição:** Isola-se uma incógnita em uma das equações e substitui-se na outra.
            - **Método da Adição:** Multiplicam-se uma ou ambas as equações por constantes convenientes para que os coeficientes de uma das incógnitas tornem-se opostos simétricos, cancelando-se ao somar as linhas.
            
            ## 5. Definições e Conceitos
            - **Grau de um Polinômio:** O maior expoente que acompanha a variável na forma reduzida.
            - **Raiz de uma Equação:** O valor numérico que, ao substituir a variável, torna a igualdade matematicamente verdadeira.
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Fatoração e Resolução Rápida por Girard
            Encontre mentalmente as raízes reais da equação quadrática:
            ${'$'}${'$'}x^2 - 7x + 12 = 0${'$'}${'$'}
            
            **Resolução Analítica:**
            - *Passo 1:* Identificar os coeficientes: ${'$'}a = 1, b = -7, c = 12${'$'}.
            - *Passo 2:* Aplicar Soma e Produto:
              ${'$'}${'$'}S = -\frac{b}{a} = -(-7) = 7${'$'}${'$'}
              ${'$'}${'$'}P = \frac{c}{a} = \frac{12}{1} = 12${'$'}${'$'}
            - *Passo 3:* Encontrar dois inteiros cujo produto seja 12 e cuja soma seja 7:
              - Pares multiplicativos de 12: ${'$'}(1, 12) \to 1+12=13${'$'}; ${'$'}(2, 6) \to 2+6=8${'$'}; ${'$'}(3, 4) \to 3+4=7${'$'}.
            - *Conclusão:* As raízes são ${'$'}x_1 = 3${'$'} e ${'$'}x_2 = 4${'$'}.
            - *Forma Fatorada:* ${'$'}(x - 3)(x - 4) = 0${'$'}.
            
            ### Exemplo 2: Sistema Linear Traduzido do Português (ENEM)
            Em uma garagem há motos (2 rodas) e carros (4 rodas), totalizando 30 veículos e 84 rodas. Quantos carros e quantas motos estão estacionados?
            
            **Resolução Analítica:**
            - *Passo 1 (Modelagem Algébrica):*
              Seja ${'$'}m${'$'} o número de motos e ${'$'}c${'$'} o número de carros.
              ${'$'}${'$'}\begin{cases} m + c = 30 & \text{(Total de veículos)} \\ 2m + 4c = 84 & \text{(Total de rodas)} \end{cases}${'$'}${'$'}
            - *Passo 2 (Método da Adição):* Multiplicar a primeira equação por ${'$'}-2${'$'}:
              ${'$'}${'$'}-2m - 2c = -60${'$'}${'$'}
              ${'$'}${'$'}2m + 4c = 84${'$'}${'$'}
            - *Passo 3 (Somar as equações membro a membro):*
              ${'$'}${'$'}(-2m + 2m) + (-2c + 4c) = -60 + 84 \implies 2c = 24 \implies c = 12\text{ carros}${'$'}${'$'}
            - *Passo 4 (Determinar a outra incógnita):*
              ${'$'}${'$'}m = 30 - 12 = 18\text{ motos}${'$'}${'$'}
              Verificação: ${'$'}18 \times 2 + 12 \times 4 = 36 + 48 = 84${'$'} rodas!
            
            ## 7. Aplicações Práticas
            - **Economia e Gestão:** Cálculo do Ponto de Equilíbrio (*Break-Even Point*) onde a receita total iguala o custo fixo mais variável (${'$'}R(q) = C(q)${'$'}).
            - **Física Balística:** As trajetórias parabólicas de projéteis e fogos de artifício no ar sob ação da gravidade são modeladas por equações quadráticas ${'$'}h(t) = v_0 t - \frac{1}{2}gt^2${'$'}.
            
            ## 8. Exercícios de Fixação
            1. Fatore completamente a expressão algébrica ${'$'}x^3 - 4x${'$'}.
            2. Resolva a inequação no conjunto dos números reais: ${'$'}3x - 5 \ge 5x + 7${'$'}.
            
            ## 9. Correção Comentada
            - *Exercício 1:*
              - Fator comum em evidência ${'$'}x${'$'}: ${'$'}x(x^2 - 4)${'$'}.
              - Diferença de quadrados no parêntese (${'$'}x^2 - 2^2${'$'}): ${'$'}(x + 2)(x - 2)${'$'}.
              - Fatoração completa: ${'$'}x(x + 2)(x - 2)${'$'}.
            - *Exercício 2:*
              ${'$'}${'$'}3x - 5x \ge 7 + 5 \implies -2x \ge 12${'$'}${'$'}
              Dividindo por ${'$'}-2${'$'} e invertendo o sinal da desigualdade:
              ${'$'}${'$'}x \le \frac{12}{-2} \implies x \le -6${'$'}${'$'}
              Conjunto solução: ${'$'}S = \{x \in \mathbb{R} \mid x \le -6\} = ]-\infty, -6]${'$'}.
            
            ## 10. Desafio de Domínio
            Sabendo que ${'$'}a + b = 8${'$'} e ${'$'}a \cdot b = 15${'$'}, determine o valor numérico exato de ${'$'}a^2 + b^2${'$'} sem calcular individualmente o valor de ${'$'}a${'$'} e ${'$'}b${'$'}.
            *Resolução:*
            Utilize a identidade do quadrado da soma:
            ${'$'}${'$'}(a + b)^2 = a^2 + 2ab + b^2${'$'}${'$'}
            Substituindo os valores conhecidos:
            ${'$'}${'$'}8^2 = (a^2 + b^2) + 2(15) \implies 64 = (a^2 + b^2) + 30 \implies a^2 + b^2 = 64 - 30 = 34${'$'}${'$'}
            
            ## 11. Resumo
            - Produtos notáveis: ${'$'}(a \pm b)^2 = a^2 \pm 2ab + b^2${'$'} e ${'$'}(a+b)(a-b) = a^2 - b^2${'$'}.
            - Fatorar é transformar somas em produtos usando fatores comuns e diferenças de quadrados.
            - Na inequação, multiplicar por número negativo inverte o sentido da desigualdade.
            - Bhaskara e Girard encontram as raízes reais de qualquer trinômio do segundo grau.
            
            ## 12. Referências
            - IEZZI, Gelson. *Fundamentos de Matemática Elementar*, Volume 2: Logaritmos e Álgebra. São Paulo: Atual, 2013.
            - OPENSTAX. *College Algebra 2e*, Chapter 2: Linear Equations and Factoring Polynomials. Rice University, 2021.
            - BRASIL. MEC/INEP. *Matriz de Referência do ENEM: Matemática e suas Tecnologias*. Competência 5: Modelar e resolver problemas com equações e inequações.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Produtos Notáveis Canônicos",
                syntax = "(a \\pm b)^2 = a^2 \\pm 2ab + b^2 \\quad \\text{e} \\quad (a+b)(a-b) = a^2 - b^2",
                description = "Identidades algébricas para expansão e fatoração de polinômios.",
                parametersInfo = "Válido para quaisquer monômios a e b reais."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Fórmula de Bhaskara e Relações de Girard",
                syntax = "x = \\frac{-b \\pm \\sqrt{b^2 - 4ac}}{2a} \\quad \\iff \\quad S = -\\frac{b}{a}, \\; P = \\frac{c}{a}",
                description = "Determinação exata das raízes reais da equação quadrática ax² + bx + c = 0.",
                parametersInfo = "Delta = b² - 4ac. Se Delta < 0, não existem raízes reais."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Resolução de Sistema Linear com Equação de Balanço",
                codeOrFormula = "\\begin{cases} x + y = 50 \\\\ 12x + 20y = 760 \\end{cases} \\implies x = 30, y = 20",
                explanation = """
                    Um evento vendeu 50 ingressos entre inteiras (R${'$'} 20) e meias-entradas (R${'$'} 12), arrecadando R${'$'} 760,00.
                    Quantas meias-entradas (x) e inteiras (y) foram vendidas?
                    
                    Passo 1: Montar o sistema:
                    x + y = 50  =>  y = 50 - x
                    12x + 20y = 760
                    
                    Passo 2: Substituir y na segunda equação:
                    12x + 20(50 - x) = 760
                    12x + 1000 - 20x = 760
                    -8x = 760 - 1000 = -240
                    x = -240 / -8 = 30 meias-entradas.
                    
                    Passo 3: Achar y:
                    y = 50 - 30 = 20 ingressos inteiros.
                """.trimIndent()
            )
        ),
        goldTip = "Antes de aplicar a fórmula de Bhaskara completa, teste sempre Soma (-b/a) e Produto (c/a)! Quando a = 1, você frequentemente acha as duas raízes mentalmente em menos de 10 segundos!"
    )

    // =========================================================================
    // AULA 11: Estatística: Média, Mediana e Moda (mb_l11)
    // =========================================================================
    private val bundleMbL11 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 11: Estatística Descritiva: Média, Mediana e Moda
            
            ## 1. Introdução
            A estatística descritiva organiza, resume e sintetiza grandes conjuntos de dados do mundo real para tomada de decisões fundamentadas. No ENEM, a interpretação de tabelas, gráficos de colunas e o cálculo de medidas de tendência central (Média Aritmética Simples, Média Ponderada, Moda e Mediana) e de dispersão (Desvio Padrão) caem sem exceção todos os anos em múltiplos itens.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Calcular a **Média Aritmética Simples** e a **Média Ponderada** atribuindo pesos corretamente.
            - Determinar a **Moda** (elemento de maior frequência) em distribuições unimodais, bimodais e multimodais.
            - Determinar a **Mediana** de uma lista de dados ordenando o rol estatístico, tanto para número ímpar quanto para número par de elementos.
            - Interpretar o significado físico e estatístico do **Desvio Padrão** e da **Variância** como medidas de regularidade e homogeneidade.
            
            ## 3. Pré-requisitos
            - Operações fundamentais, frações e decimais (Aula 1).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. As Medidas de Tendência Central
            
            #### 1. Média Aritmética Simples (${'$'}\bar{x}${'$'})
            Soma de todos os ${'$'}n${'$'} valores observados dividida pelo total de observações:
            ${'$'}${'$'}\bar{x} = \frac{x_1 + x_2 + \cdots + x_n}{n} = \frac{1}{n} \sum_{i=1}^n x_i${'$'}${'$'}
            - *Sensibilidade:* A média é extremamente sensível a valores discrepantes (*outliers* extremos).
            
            #### 2. Média Ponderada (${'$'}\bar{x}_p${'$'})
            Quando diferentes elementos possuem frequências ou pesos distintos de importância (${'$'}w_i${'$'}):
            ${'$'}${'$'}\bar{x}_p = \frac{w_1 x_1 + w_2 x_2 + \cdots + w_k x_k}{w_1 + w_2 + \cdots + w_k} = \frac{\sum w_i x_i}{\sum w_i}${'$'}${'$'}
            
            #### 3. Moda (${'$'}Mo${'$'})
            O valor que ocorre com a **maior frequência absoluta** na amostra de dados.
            - Se nenhum valor se repete: distribuição **amodal**.
            - Se dois valores empatam na maior frequência: distribuição **bimodal**.
            
            #### 4. Mediana (${'$'}Me${'$'})
            O valor numérico central que divide o conjunto de dados ordenados (em ordem crescente ou decrescente, chamado **Rol**) em exatamente duas metades iguais (50% dos dados abaixo e 50% acima).
            - **Passo Obrigatório Zero:** Ordenar os dados em rol!
            - **Caso 1: Quantidade ímpar de dados (${'$'}n${'$'} ímpar):**
              A mediana é o elemento que ocupa exatamente a posição central ${'$'}p = \frac{n + 1}{2}${'$'}:
              ${'$'}${'$'}Me = x_{\left(\frac{n+1}{2}\right)}${'$'}${'$'}
            - **Caso 2: Quantidade par de dados (${'$'}n${'$'} par):**
              Não existe um único termo central. A mediana é a **média aritmética dos dois termos centrais** que ocupam as posições ${'$'}\frac{n}{2}${'$'} e ${'$'}\frac{n}{2} + 1${'$'}:
              ${'$'}${'$'}Me = \frac{x_{(n/2)} + x_{(n/2 + 1)}}{2}${'$'}${'$'}
            - *Vantagem da Mediana:* É uma medida robusta, que **não é distorcida por valores extremos**.
            
            ### B. Medidas de Dispersão: Variância e Desvio Padrão
            Duas amostras podem ter rigorosamente a mesma média e apresentarem comportamentos completamente distintos.
            - Amostra A: ${'$'}\{10, 10, 10\} \implies \bar{x} = 10${'$'} (Totalmente homogênea e regular).
            - Amostra B: ${'$'}\{0, 10, 20\} \implies \bar{x} = 10${'$'} (Altamente dispersa e irregular).
            
            1. **Desvio:** ${'$'}d_i = x_i - \bar{x}${'$'} (diferença entre o dado e a média).
            2. **Variância (${'$'}\sigma^2${'$'}):** Média dos quadrados dos desvios:
               ${'$'}${'$'}\sigma^2 = \frac{1}{n} \sum_{i=1}^n (x_i - \bar{x})^2${'$'}${'$'}
            3. **Desvio Padrão (${'$'}\sigma${'$'}):** Raiz quadrada positiva da variância:
               ${'$'}${'$'}\sigma = \sqrt{\sigma^2} = \sqrt{\frac{1}{n} \sum_{i=1}^n (x_i - \bar{x})^2${'$'}${'$'}
               - *Interpretação para o ENEM:* **Menor desvio padrão** significa dados mais concentrados ao redor da média, logo o aluno, máquina ou atleta é **mais regular e confiável**!
            
            ## 5. Definições e Conceitos
            - **Rol Estatístico:** A lista ordenada (crescente ou decrescente) de todos os dados de uma amostra.
            - **Amplitude Amostral:** A diferença entre o maior e o menor valor do conjunto (${'$'}A = x_{\max} - x_{\min}${'$'}).
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Cálculo da Mediana em Rol Par e Ímpar
            Considere as notas de um estudante em 6 simulados: ${'$'}\{7{,}0; \; 8{,}5; \; 6{,}0; \; 9{,}0; \; 7{,}5; \; 8{,}0\}${'$'}.
            Determine a média e a mediana das notas.
            
            **Resolução Analítica:**
            - *Passo 1 (Cálculo da Média):*
              ${'$'}${'$'}\bar{x} = \frac{7{,}0 + 8{,}5 + 6{,}0 + 9{,}0 + 7{,}5 + 8{,}0}{6} = \frac{46{,}0}{6} \approx 7{,}67${'$'}${'$'}
            - *Passo 2 (Montagem obrigatória do Rol Crescente):*
              ${'$'}${'$'}\text{Rol: } \{6{,}0; \; 7{,}0; \; \mathbf{7{,}5}; \; \mathbf{8{,}0}; \; 8{,}5; \; 9{,}0\}${'$'}${'$'}
            - *Passo 3 (Identificação dos Termos Centrais):*
              Como ${'$'}n = 6${'$'} (par), os dois termos centrais estão nas posições ${'$'}6/2 = 3ª${'$'} e ${'$'}3ª + 1 = 4ª${'$'}:
              Termo na 3ª posição = ${'$'}7{,}5${'$'}
              Termo na 4ª posição = ${'$'}8{,}0${'$'}
            - *Passo 4 (Cálculo da Mediana):*
              ${'$'}${'$'}Me = \frac{7{,}5 + 8{,}0}{2} = \frac{15{,}5}{2} = 7{,}75${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Economia e Renda Nacional:** A Renda Mediana é um indicador social muito mais fiel da distribuição de riqueza de um país que a Renda Per Capita Média, pois a presença de bilionários puxa a média para cima sem refletir a realidade da maioria.
            - **Controle de Qualidade Fabril:** Em linhas de engarrafamento de refrigerantes de ${'$'}2\text{ litros}${'$'}, a calibração da máquina exige que o desvio padrão de enchimento seja inferior a ${'$'}5\text{ mL}${'$'}.
            
            ## 8. Exercícios de Fixação
            1. Um grupo de amigos tem as seguintes idades: ${'$'}18, 20, 20, 22, 25, 45${'$'}. Qual é a média, a moda e a mediana desse grupo?
            2. Três candidatos disputam uma vaga de trabalho. Suas notas médias foram idênticas (8,0). O candidato A teve desvio padrão 0,4; o candidato B teve 1,2; o candidato C teve 0,8. Qual candidato apresentou o desempenho mais regular e homogêneo?
            
            ## 9. Correção Comentada
            - *Exercício 1:*
              - Média: ${'$'}\frac{18 + 20 + 20 + 22 + 25 + 45}{6} = \frac{150}{6} = 25\text{ anos}${'$'}.
              - Moda: O valor ${'$'}20${'$'} se repete duas vezes ${'$'}\implies Mo = 20\text{ anos}${'$'}.
              - Mediana: O rol já está ordenado. Termos centrais na 3ª e 4ª posições (${'$'}20${'$'} e ${'$'}22${'$'}):
                ${'$'}${'$'}Me = \frac{20 + 22}{2} = 21\text{ anos}${'$'}${'$'}
              (Note como a idade 45 puxou a média para 25, enquanto a mediana 21 reflete a realidade da maioria).
            - *Exercício 2:*
              O candidato mais regular é aquele com o **menor desvio padrão**, pois suas notas variaram menos em relação à média. Resposta: **Candidato A** (${'$'}\sigma = 0{,}4${'$'}).
            
            ## 10. Desafio de Domínio
            Em uma prova com 5 questões de pesos 1, 2, 3, 4 e 5, um estudante acertou as questões de pesos 1, 2 e 5 e errou as de pesos 3 e 4. Sabendo que cada acerto confere nota máxima 10 na questão e cada erro nota zero, qual foi a média ponderada final do estudante?
            *Resolução:*
            - Soma dos pesos: ${'$'}1 + 2 + 3 + 4 + 5 = 15${'$'}.
            - Pontuação: ${'$'}(1 \times 10) + (2 \times 10) + (3 \times 0) + (4 \times 0) + (5 \times 10) = 10 + 20 + 0 + 0 + 50 = 80${'$'}.
            - Média Ponderada: ${'$'}\bar{x}_p = \frac{80}{15} = \frac{16}{3} \approx 5{,}33${'$'}.
            
            ## 11. Resumo
            - Média: soma de todos dividida por ${'$'}n${'$'}.
            - Moda: o valor mais frequente.
            - Mediana: o termo do meio no rol ordenado (média dos 2 termos centrais se ${'$'}n${'$'} for par).
            - Desvio Padrão mede dispersão: quanto menor o desvio padrão, mais regular é o conjunto.
            
            ## 12. Referências
            - BUSSAB, Wilton de Oliveira; MORETTIN, Pedro Alberto. *Estatística Básica*. 9. ed. São Paulo: Saraiva, 2017.
            - INEP. *Matriz de Referência do ENEM: Matemática e suas Tecnologias*. Competência de Área 7: Compreender o caráter aleatório e estatístico de dados. Brasília: INEP.
            - OPENSTAX. *Introductory Statistics*, Chapter 2: Descriptive Statistics. Rice University, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Média Aritmética Ponderada",
                syntax = "\\bar{x}_p = \\frac{\\sum_{i=1}^k w_i x_i}{\\sum_{i=1}^k w_i}",
                description = "Média onde cada observação x_i é ponderada por sua frequência ou peso de importância w_i.",
                parametersInfo = "A soma dos pesos no denominador não precisa somar 100."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Desvio Padrão Amostral",
                syntax = "\\sigma = \\sqrt{\\frac{1}{n} \\sum_{i=1}^n (x_i - \\bar{x})^2}",
                description = "Medida canônica de dispersão em relação à média aritmética.",
                parametersInfo = "Desvio padrão zero significa que todos os elementos da lista são idênticos."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Determinação de Mediana com Tabela de Frequências",
                codeOrFormula = "\\text{Posições centrais de 40 dados: } 20ª \\text{ e } 21ª \\implies Me = 3",
                explanation = """
                    Uma pesquisa com 40 famílias registrou o número de filhos:
                    0 filhos: 8 famílias (posições 1 a 8)
                    1 filho: 10 famílias (posições 9 a 18)
                    2 filhos: 6 famílias (posições 19 a 24)
                    3 filhos: 16 famílias (posições 25 a 40)
                    
                    Qual é a mediana do número de filhos?
                    
                    Passo 1: Total de dados N = 40 (par).
                    As posições centrais são 40/2 = 20ª posição e 21ª posição.
                    
                    Passo 2: Localizar as posições pela frequência acumulada:
                    Até 1 filho: 8 + 10 = 18 famílias.
                    As famílias das posições 19 a 24 possuem exatamente 2 filhos!
                    Logo, o 20º dado é 2 e o 21º dado é 2.
                    
                    Passo 3: Média dos termos centrais:
                    Me = (2 + 2) / 2 = 2 filhos.
                """.trimIndent()
            )
        ),
        goldTip = "Em questões de gráfico ou tabela para achar a mediana: NUNCA pegue o valor do meio da tabela! Conte a quantidade total de observações e descubra em qual categoria caem os dados centrais!"
    )

    // =========================================================================
    // AULA 12: Geometria Essencial: Áreas, Pitágoras e Volumes (mb_l12)
    // =========================================================================
    private val bundleMbL12 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 12: Geometria Essencial: Perímetros, Áreas Planas, Pitágoras e Volumes
            
            ## 1. Introdução
            A geometria espacial e plana responde por cerca de 30% da prova de Matemática do ENEM. O sucesso na resolução de problemas espaciais complexos (prismas, cilindros, troncos de pirâmides e projeções ortogonais) depende do domínio prévio absoluto das relações métricas no triângulo retângulo (Teorema de Pitágoras) e do cálculo rigoroso de áreas fundamentais de polígonos e círculos.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Aplicar o **Teorema de Pitágoras** e reconhecer os ternos pitagóricos fundamentais (${'$'}3-4-5${'$'} e ${'$'}5-12-13${'$'}).
            - Calcular perímetros e áreas das principais figuras planas: triângulos, quadrados, retângulos, paralelogramos, losangos, trapézios e círculos.
            - Calcular a área e o comprimento de arcos de setores circulares por regra de três e proporção angular.
            - Calcular a área total e o volume de sólidos retos fundamentais: paralelepípedos retângulos e cilindros circulares retos.
            
            ## 3. Pré-requisitos
            - Equações do 2º grau e radiciação (Aula 10) e conversão de unidades (Aula 5).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. O Teorema de Pitágoras no Triângulo Retângulo
            Em todo triângulo retângulo, o quadrado da hipotenusa (${'$'}a${'$'}, lado oposto ao ângulo reto de ${'$'}90^\circ${'$'}) é igual à soma dos quadrados dos catetos (${'$'}b${'$'} e ${'$'}c${'$'}):
            ${'$'}${'$'}a^2 = b^2 + c^2${'$'}${'$'}
            - **Ternos Pitagóricos Notáveis (Memorização Estratégica):**
              - Triângulo ${'$'}3 - 4 - 5${'$'} (e seus múltiplos: ${'$'}6-8-10${'$'}, ${'$'}9-12-15${'$'}, ${'$'}30-40-50${'$'}).
              - Triângulo ${'$'}5 - 12 - 13${'$'} (e seus múltiplos: ${'$'}10-24-26${'$'}).
              - Triângulo ${'$'}8 - 15 - 17${'$'}.
            - **Diagonal do Quadrado de lado ${'$'}L${'$'}:** ${'$'}d = L\sqrt{2}${'$'}.
            - **Altura do Triângulo Equilátero de lado ${'$'}L${'$'}:** ${'$'}h = \frac{L\sqrt{3}}{2}${'$'}.
            
            ### B. Áreas das Figuras Planas Fundamentais
            1. **Retângulo:** ${'$'}A = b \cdot h${'$'} (base ${'$'}\times${'$'} altura).
            2. **Quadrado:** ${'$'}A = L^2 = \frac{d^2}{2}${'$'}.
            3. **Paralelogramo:** ${'$'}A = b \cdot h${'$'}.
            4. **Triângulo Qualquer:** ${'$'}A = \frac{b \cdot h}{2}${'$'}.
               - *Fórmula Trigonométrica:* ${'$'}A = \frac{a \cdot b \cdot \sin(\theta)}{2}${'$'}.
               - *Triângulo Equilátero de lado ${'$'}L${'$'}:* ${'$'}A = \frac{L^2\sqrt{3}}{4}${'$'}.
            5. **Losango:** ${'$'}A = \frac{D \cdot d}{2}${'$'} (semi-produto das diagonais).
            6. **Trapézio:** ${'$'}A = \frac{(B + b) \cdot h}{2}${'$'} (média das bases vezes a altura).
            7. **Círculo e Circunferência:**
               - Comprimento da circunferência: ${'$'}C = 2\pi R = \pi D${'$'}.
               - Área do círculo: ${'$'}A = \pi R^2${'$'}.
               - Setor circular de ângulo central ${'$'}\alpha${'$'} (em graus): ${'$'}A_{\text{setor}} = \frac{\alpha}{360^\circ} \times \pi R^2${'$'}.
            
            ### C. Introdução aos Sólidos Geométricos e Volumes
            O volume de todo prisma reto ou cilindro reto segue o princípio fundamental de Cavalieri:
            ${'$'}${'$'}V = A_{\text{base}} \times h${'$'}${'$'}
            1. **Paralelepípedo Retângulo:**
               ${'$'}${'$'}V = a \cdot b \cdot c \quad \text{e} \quad \text{Área Total} = 2(ab + ac + bc)${'$'}${'$'}
               Diagonal espacial: ${'$'}D = \sqrt{a^2 + b^2 + c^2}${'$'}.
            2. **Cubo de aresta ${'$'}a${'$'}:**
               ${'$'}${'$'}V = a^3 \quad \text{e} \quad D = a\sqrt{3}${'$'}${'$'}
            3. **Cilindro Circular Reto:**
               Base é um círculo de raio ${'$'}R${'$'}:
               ${'$'}${'$'}V = \pi R^2 \cdot h \quad \text{e} \quad A_{\text{lateral}} = 2\pi R \cdot h${'$'}${'$'}
            
            ## 5. Definições e Conceitos
            - **Hipotenusa:** O maior lado de um triângulo retângulo, sempre oposto ao vértice de ${'$'}90^\circ${'$'}.
            - **Apótema:** Segmento que liga o centro de um polígono regular ao ponto médio de um de seus lados.
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Cálculo de Área de Terreno em Formato de Trapézio
            Um terreno residencial tem formato de trapézio retângulo com base maior medindo ${'$'}24\text{ m}${'$'}, base menor medindo ${'$'}16\text{ m}${'$'} e lado oblíquo medindo ${'$'}10\text{ m}${'$'}. Calcule a área total do terreno em metros quadrados.
            
            **Resolução Analítica:**
            - *Passo 1 (Determinar a altura do trapézio via Pitágoras):*
              Ao traçar a altura a partir do vértice da base menor, divide-se a base maior em duas partes: uma de ${'$'}16\text{ m}${'$'} e uma sobra de ${'$'}24 - 16 = 8\text{ m}${'$'}.
              Forma-se um triângulo retângulo de catetos ${'$'}8\text{ m}${'$'} e ${'$'}h${'$'}, com hipotenusa medindo ${'$'}10\text{ m}${'$'} (lado oblíquo).
            - *Passo 2 (Aplicação do Teorema de Pitágoras):*
              ${'$'}${'$'}10^2 = 8^2 + h^2 \implies 100 = 64 + h^2 \implies h^2 = 36 \implies h = 6\text{ metros}${'$'}${'$'}
              (Note que é o terno pitagórico ${'$'}6 - 8 - 10${'$'}, múltiplo do ${'$'}3 - 4 - 5${'$'}!)
            - *Passo 3 (Cálculo da Área do Trapézio):*
              ${'$'}${'$'}A = \frac{(B + b) \cdot h}{2} = \frac{(24 + 16) \cdot 6}{2} = \frac{40 \cdot 6}{2} = 40 \times 3 = 120\text{ m}^2${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Arquitetura e Instalação de Pisos:** Para cobrir o chão de uma sala de ${'$'}20\text{ m}^2${'$'} com lajotas de ${'$'}50\text{ cm} \times 50\text{ cm}${'$'} (${'$'}0{,}25\text{ m}^2${'$'}), o cálculo de área determina o mínimo exato de peças: ${'$'}\frac{20}{0{,}25} = 80\text{ lajotas}${'$'} (acrescentando-se 10% para perdas de recorte).
            - **Engenharia de Reservatórios de Petróleo:** Tanques cilíndricos horizontais e verticais utilizam ${'$'}V = \pi R^2 h${'$'} para calibrar réguas de nível volumétrico.
            
            ## 8. Exercícios de Fixação
            1. Um cabo de aço de sustentação de uma antena transmissora é fixado no solo a ${'$'}12\text{ metros}${'$'} da base da antena. Se a antena tem ${'$'}16\text{ metros}${'$'} de altura, qual é o comprimento do cabo esticado?
            2. Um cilindro circular reto possui raio da base igual a ${'$'}3\text{ metros}${'$'} e altura igual a ${'$'}7\text{ metros}${'$'}. Adotando ${'$'}\pi \approx 3{,}14${'$'}, qual é o volume do cilindro em litros?
            
            ## 9. Correção Comentada
            - *Exercício 1:* Aplicação de Pitágoras:
              ${'$'}${'$'}L^2 = 12^2 + 16^2 = 144 + 256 = 400 \implies L = \sqrt{400} = 20\text{ metros}${'$'}${'$'}
              (Terno pitagórico ${'$'}3 \times 4 = 12${'$'}, ${'$'}4 \times 4 = 16${'$'}, ${'$'}5 \times 4 = 20${'$'}!)
            - *Exercício 2:* Volume do cilindro:
              ${'$'}${'$'}V = \pi R^2 h = 3{,}14 \times 3^2 \times 7 = 3{,}14 \times 9 \times 7 = 3{,}14 \times 63 = 197{,}82\text{ m}^3${'$'}${'$'}
              Convertendo para litros (${'$'}1\text{ m}^3 = 1.000\text{ L}${'$'}):
              ${'$'}${'$'}197{,}82 \times 1.000 = 197.820\text{ litros}${'$'}${'$'}
            
            ## 10. Desafio de Domínio
            Em uma praça circular de raio ${'$'}R = 10\text{ metros}${'$'}, construiu-se um canteiro de flores no formato de um triângulo equilátero inscrito na circunferência. Calcule a área da região da praça que sobrou fora do canteiro de flores (área residual).
            *Resolução:*
            - Área total da praça: ${'$'}A_{\text{círculo}} = \pi R^2 = \pi \cdot 10^2 = 100\pi\text{ m}^2${'$'}.
            - Lado do triângulo equilátero inscrito em circunferência de raio ${'$'}R${'$'}: ${'$'}L = R\sqrt{3} = 10\sqrt{3}\text{ m}${'$'}.
            - Área do triângulo equilátero:
              ${'$'}${'$'}A_{\text{triângulo}} = \frac{L^2 \sqrt{3}}{4} = \frac{(10\sqrt{3})^2 \sqrt{3}}{4} = \frac{300\sqrt{3}}{4} = 75\sqrt{3}\text{ m}^2${'$'}${'$'}
            - Área residual:
              ${'$'}${'$'}A_{\text{residual}} = 100\pi - 75\sqrt{3} \approx 100(3{,}14) - 75(1{,}73) = 314 - 129{,}75 = 184{,}25\text{ m}^2${'$'}${'$'}
            
            ## 11. Resumo
            - Pitágoras no triângulo retângulo: ${'$'}a^2 = b^2 + c^2${'$'}.
            - Memorize o terno ${'$'}3-4-5${'$'} e seus múltiplos.
            - Áreas fundamentais: Triângulo (${'$'}\frac{b \cdot h}{2}${'$'}), Trapézio (${'$'}\frac{(B+b)h}{2}${'$'}), Círculo (${'$'}\pi R^2${'$'}).
            - Volumes de prismas e cilindros: ${'$'}V = A_{\text{base}} \times h${'$'}.
            
            ## 12. Referências
            - DANTE, Luiz Roberto. *Matemática: Contexto & Aplicações*, Volume 2 (Geometria Plana e Espacial). São Paulo: Ática, 2016.
            - DOLCE, Osvaldo; POMPEO, José Nicolau. *Fundamentos de Matemática Elementar*, Volume 9: Geometria Plana. São Paulo: Atual, 2013.
            - INEP. *Matriz de Referência do ENEM: Matemática e suas Tecnologias*. Competência de Área 2: Utilizar o conhecimento geométrico para realizar a leitura e a representação da realidade.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema de Pitágoras",
                syntax = "a^2 = b^2 + c^2",
                description = "Relação métrica fundamental entre a hipotenusa a e os catetos b e c.",
                parametersInfo = "Válido exclusivamente para triângulos com ângulo reto (90°)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Área do Círculo e Volume do Cilindro",
                syntax = "A_{\\text{círculo}} = \\pi R^2 \\quad \\iff \\quad V_{\\text{cilindro}} = \\pi R^2 \\cdot h",
                description = "Área da base circular e volume de preenchimento do cilindro reto.",
                parametersInfo = "R é o raio da seção transversal e h é a altura retilínea."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Problema Clássico ENEM: Capacidade de Lata Cilíndrica",
                codeOrFormula = "V = \\pi \\cdot 4^2 \\cdot 10 = 3{,}14 \\times 16 \\times 10 = 502{,}4\\text{ cm}^3 \\approx 502\\text{ mL}",
                explanation = """
                    Uma lata de óleo possui formato de cilindro reto com diâmetro da base medindo 8 cm (raio R = 4 cm) e altura de 10 cm.
                    Qual é o volume de óleo contido na lata cheia?
                    
                    Passo 1: Identificar o raio a partir do diâmetro:
                    Raio R = 8 / 2 = 4 cm.
                    
                    Passo 2: Aplicar a fórmula do volume do cilindro (V = π R² h):
                    V = 3,14 × 4² × 10 = 3,14 × 16 × 10 = 502,4 cm³.
                    
                    Passo 3: Como 1 cm³ = 1 mL:
                    Capacidade = 502,4 mL (aproximadamente meio litro).
                """.trimIndent()
            )
        ),
        goldTip = "Cuidado com diâmetro vs. raio no ENEM! O enunciado quase sempre dá o DIÂMETRO (ex: 8 cm), mas a fórmula usa o RAIO (4 cm)! Sempre divida por 2 antes de elevar ao quadrado!"
    )
}
