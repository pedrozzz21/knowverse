package com.example.data.math.didactic

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.FormulaOrSyntaxItem
import com.example.data.model.LessonExample

/**
 * Conteúdo Didático Aprofundado de Matemática Básica — Parte 2 (Aulas 5 a 8).
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
object BasicMathDidacticPart2 {

    fun get(lessonId: String): CalculusDidacticBundle? {
        return when (lessonId) {
            "mb_l5" -> bundleMbL5
            "mb_l6" -> bundleMbL6
            "mb_l7" -> bundleMbL7
            "mb_l8" -> bundleMbL8
            else -> null
        }
    }

    // =========================================================================
    // AULA 5: Conversão de Unidades e Capacidade (mb_l5)
    // =========================================================================
    private val bundleMbL5 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 5: Sistema Métrico Decimal e Conversão de Unidades
            
            ## 1. Introdução
            A cada edição do ENEM, questões de matemática e ciências da natureza exigem conversões entre metros, centímetros cúbicos, litros e vazão temporal. Um dos erros estatisticamente mais frequentes entre os candidatos é esquecer que ao converter unidades de área o fator de conversão é elevado ao quadrado (${'$'}10^2${'$'}), e em unidades de volume o fator é elevado ao cubo (${'$'}10^3${'$'}). Dominar essas relações geométricas elimina pegadinhas clássicas.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Converter com segurança unidades lineares (${'$'}\text{m}${'$'}), superficiais (${'$'}\text{m}^2${'$'}) e volumétricas (${'$'}\text{m}^3${'$'}).
            - Estabelecer a ponte vital entre volume e capacidade (${'$'}1\text{ L} = 1\text{ dm}^3${'$'} e ${'$'}1\text{ m}^3 = 1000\text{ L}${'$'}).
            - Efetuar cálculos de vazão volumétrica (${'$'}Q = \frac{\Delta V}{\Delta t}${'$'}) em unidades mistas (${'$'}\text{L/s}, \text{m}^3/\text{h}${'$'}).
            - Operar conversões de tempo (horas, minutos e segundos na base sexagesimal 60).
            
            ## 3. Pré-requisitos
            - Notação científica e potências de 10 (Aula 4) e frações decimais (Aula 1).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. O Sistema Métrico Decimal Linear
            A escala padrão de comprimento baseia-se em potências de 10 com o metro (${'$'}\text{m}${'$'}) como unidade fundamental:
            ${'$'}${'$'}\text{km} \quad \text{hm} \quad \text{dam} \quad \mathbf{m} \quad \text{dm} \quad \text{cm} \quad \text{mm}${'$'}${'$'}
            - Para a direita: multiplica-se por 10 a cada casa.
            - Para a esquerda: divide-se por 10 a cada casa.
            
            ### B. Unidades de Área (Bidimensionais — Fator ${'$'}10^2 = 100${'$'})
            Como a área é o produto de duas dimensões lineares (${'$'}\text{comprimento} \times \text{largura}${'$'}), cada degrau na tabela corresponde a um salto de **duas casas decimais** (${'$'}10^2 = 100${'$'}):
            ${'$'}${'$'}1\text{ m}^2 = (10\text{ dm})^2 = 100\text{ dm}^2 = (100\text{ cm})^2 = 10.000\text{ cm}^2${'$'}${'$'}
            ${'$'}${'$'}1\text{ km}^2 = (1.000\text{ m})^2 = 1.000.000\text{ m}^2 = 10^6\text{ m}^2${'$'}${'$'}
            - *Unidade agrária:* O hectare (${'$'}\text{ha}${'$'}) é a área de um quadrado de ${'$'}100\text{ m} \times 100\text{ m}${'$'}:
              ${'$'}${'$'}1\text{ hectare (ha)} = 10.000\text{ m}^2${'$'}${'$'}
            
            ### C. Unidades de Volume (Tridimensionais — Fator ${'$'}10^3 = 1000${'$'})
            Sendo tridimensional (${'$'}\text{comprimento} \times \text{largura} \times \text{altura}${'$'}), cada degrau na tabela representa um fator de **três casas decimais** (${'$'}10^3 = 1000${'$'}):
            ${'$'}${'$'}1\text{ m}^3 = (10\text{ dm})^3 = 1.000\text{ dm}^3${'$'}${'$'}
            ${'$'}${'$'}1\text{ cm}^3 = 10^{-6}\text{ m}^3${'$'}${'$'}
            
            ### D. A Ponte de Ouro: Volume vs. Capacidade
            A equivalência fundamental universal que você deve gravar na memória:
            ${'$'}${'$'}\mathbf{1\text{ decímetro cúbico (dm}^3\mathbf{)} = 1\text{ litro (L)}}${'$'}${'$'}
            ${'$'}${'$'}\mathbf{1\text{ metro cúbico (m}^3\mathbf{)} = 1.000\text{ litros (L)}}${'$'}${'$'}
            ${'$'}${'$'}\mathbf{1\text{ centímetro cúbico (cm}^3\mathbf{)} = 1\text{ mililitro (mL)}}${'$'}${'$'}
            
            ### E. Conversão de Tempo (Base Sexagesimal 60)
            Diferente do sistema métrico decimal, o tempo opera na base 60:
            ${'$'}${'$'}1\text{ hora} = 60\text{ minutos} = 3.600\text{ segundos}${'$'}${'$'}
            - *Atenção máxima:* ${'$'}2{,}5\text{ horas} \neq 2\text{ horas e } 50\text{ minutos}${'$'}!
              ${'$'}${'$'}2{,}5\text{ h} = 2\text{ h} + 0{,}5\text{ h} = 2\text{ h} + (0{,}5 \times 60\text{ min}) = 2\text{ h e } 30\text{ min}${'$'}${'$'}
            
            ## 5. Definições e Conceitos
            - **Vazão Volumétrica (${'$'}Q${'$'}):** Volume de fluido que atravessa uma seção por unidade de tempo:
              ${'$'}${'$'}Q = \frac{\Delta V}{\Delta t}${'$'}${'$'}
            - **Massa e Capacidade da Água Pura:** Nas CNTP e densidade de ${'$'}1\text{ g/cm}^3${'$'}:
              ${'$'}${'$'}1\text{ L de água pura} = 1\text{ kg} \quad \text{e} \quad 1\text{ m}^3\text{ de água} = 1\text{ tonelada (1000 kg)}${'$'}${'$'}
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Esvaziamento de Piscina por Bomba Hidráulica
            Uma piscina possui formato de paralelepípedo com comprimento de ${'$'}8\text{ m}${'$'}, largura de ${'$'}5\text{ m}${'$'} e profundidade constante de ${'$'}1{,}5\text{ m}${'$'}. Uma bomba hidráulica com vazão constante de ${'$'}50\text{ litros por minuto}${'$'} é acionada para esvaziá-la completamente. Quantas horas e minutos a bomba levará para concluir o esvaziamento?
            
            **Resolução Analítica:**
            - *Passo 1 (Cálculo do volume em metros cúbicos):*
              ${'$'}${'$'}V = 8 \times 5 \times 1{,}5 = 40 \times 1{,}5 = 60\text{ m}^3${'$'}${'$'}
            - *Passo 2 (Conversão para litros usando a ponte ${'$'}1\text{ m}^3 = 1000\text{ L}${'$'}):*
              ${'$'}${'$'}V = 60 \times 1.000 = 60.000\text{ litros}${'$'}${'$'}
            - *Passo 3 (Tempo necessário em minutos):*
              ${'$'}${'$'}\Delta t = \frac{V}{Q} = \frac{60.000\text{ L}}{50\text{ L/min}} = 1.200\text{ minutos}${'$'}${'$'}
            - *Passo 4 (Conversão de minutos para horas na base 60):*
              ${'$'}${'$'}\Delta t = \frac{1.200}{60} = 20\text{ horas exatas}${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Hidrologia e Saneamento:** Na previsão de chuvas, uma precipitação de ${'$'}1\text{ mm}${'$'} equivale a ${'$'}1\text{ litro de água}${'$'} acumulado em cada metro quadrado de superfície (${'$'}1\text{ mm} \times 1\text{ m}^2 = 10^{-3}\text{ m} \times 1\text{ m}^2 = 10^{-3}\text{ m}^3 = 1\text{ L}${'$'}).
            - **Engenharia Civil:** Dimensionamento de lajes de concreto em ${'$'}\text{m}^3${'$'} a partir das áreas em ${'$'}\text{m}^2${'$'} multiplicadas pela espessura em centímetros convertida para metros.
            
            ## 8. Exercícios de Fixação
            1. Quantos litros de água são necessários para encher um reservatório de ${'$'}4{,}2\text{ m}^3${'$'}?
            2. Um atleta completou uma maratona em ${'$'}2\text{ horas, } 24\text{ minutos e } 36\text{ segundos}${'$'}. Expresse esse tempo exatamente em horas decimais.
            
            ## 9. Correção Comentada
            - *Exercício 1:* Como ${'$'}1\text{ m}^3 = 1000\text{ L}${'$'}:
              ${'$'}${'$'}4{,}2 \times 1.000 = 4.200\text{ litros}${'$'}${'$'}
            - *Exercício 2:*
              - ${'$'}36\text{ s} = \frac{36}{60}\text{ min} = 0{,}6\text{ min}${'$'}.
              - Tempo em minutos: ${'$'}24 + 0{,}6 = 24{,}6\text{ min}${'$'}.
              - Convertendo minutos para horas: ${'$'}\frac{24{,}6}{60} = 0{,}41\text{ h}${'$'}.
              - Tempo total: ${'$'}2 + 0{,}41 = 2{,}41\text{ horas}${'$'}.
            
            ## 10. Desafio de Domínio
            Um vazamento em uma torneira goteja a uma taxa constante de ${'$'}0{,}2\text{ mL}${'$'} por segundo. Se a água custa R${'$'} 8,00 por metro cúbico faturado, qual será o custo financeiro aproximado do desperdício acumulado ao longo de um mês comercial de 30 dias?
            *Resolução:*
            - Segundos em 30 dias: ${'$'}30 \times 24 \times 3.600 = 2.592.000\text{ segundos}${'$'}.
            - Volume em mL: ${'$'}2.592.000 \times 0{,}2 = 518.400\text{ mL} = 518{,}4\text{ L}${'$'}.
            - Volume em ${'$'}\text{m}^3${'$'}: ${'$'}\frac{518{,}4}{1000} = 0{,}5184\text{ m}^3${'$'}.
            - Custo: ${'$'}0{,}5184 \times 8{,}00 \approx \text{R${'$'}} 4{,}15${'$'}.
            
            ## 11. Resumo
            - Em unidades lineares, cada degrau multiplica/divide por 10.
            - Em área, cada degrau multiplica/divide por 100 (${'$'}10^2${'$'}).
            - Em volume, cada degrau multiplica/divide por 1.000 (${'$'}10^3${'$'}).
            - As pontes vitais: ${'$'}1\text{ dm}^3 = 1\text{ L}${'$'}; ${'$'}1\text{ m}^3 = 1.000\text{ L}${'$'}; ${'$'}1\text{ cm}^3 = 1\text{ mL}${'$'}.
            - Tempo é sexagesimal: multiplique ou divida por 60, jamais por 100!
            
            ## 12. Referências
            - INMETRO. *Quadro Geral de Unidades de Medida*. Portaria INMETRO nº 590/2013. Rio de Janeiro: INMETRO, 2013.
            - BIPM. *Le Système international d'unités (SI)*. 9. ed. Sèvres: Bureau International des Poids et Mesures, 2019.
            - BRASIL. MEC. *Matriz de Referência do ENEM*. Habilidade 3: Selecionar e utilizar unidades de medida adequadas. Brasília: MEC/INEP.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equivalência Fundamental Volume-Capacidade",
                syntax = "1\\text{ dm}^3 = 1\\text{ L} \\quad \\iff \\quad 1\\text{ m}^3 = 1.000\\text{ L}",
                description = "Ponte padrão entre o sistema métrico dimensional tridimensional e o volume de líquidos.",
                parametersInfo = "1 cm³ = 1 mL"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Conversão de Tempo Decimal para Sexagesimal",
                syntax = "t_{\\text{min}} = t_{\\text{h(decimal)}} \\times 60",
                description = "Conversão da parte decimal de uma hora em minutos reais na base 60.",
                parametersInfo = "1 hora = 60 min = 3600 s"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Conversão de Vazão de Bomba de Combustível",
                codeOrFormula = "72\\text{ m}^3/\\text{h} = \\frac{72.000\\text{ L}}{3600\\text{ s}} = 20\\text{ L/s}",
                explanation = """
                    Uma bomba de abastecimento industrial transfere combustível à taxa de 72 m³ por hora.
                    Qual é a vazão equivalente em litros por segundo?
                    
                    Passo 1: Converter o numerador (72 m³ para litros):
                    72 m³ = 72 × 1.000 L = 72.000 litros.
                    
                    Passo 2: Converter o denominador (1 hora para segundos):
                    1 h = 3.600 segundos.
                    
                    Passo 3: Efetuar a divisão direta:
                    Vazão = 72.000 L / 3.600 s = 720 / 36 = 20 litros por segundo.
                """.trimIndent()
            )
        ),
        goldTip = "Alerta ENEM: Nunca confunda 0,3 horas com 30 minutos! 0,3 horas = 0,3 × 60 = 18 minutos!"
    )

    // =========================================================================
    // AULA 6: Razão, Proporção e Grandezas Proporcionais (mb_l6)
    // =========================================================================
    private val bundleMbL6 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 6: Razão, Proporção e Grandezas Diretamente e Inversamente Proporcionais
            
            ## 1. Introdução
            A proporcionalidade é a espinha dorsal da prova de Matemática do ENEM, respondendo historicamente por mais de 25% de todas as questões do caderno de exatas. O conceito permite comparar quantidades relativas (velocidade média, densidade demográfica, rendimento de combustível, consumo de energia) e modelar divisões proporcionais em heranças, cotas societárias e processos químicos industriais.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Definir analiticamente uma razão como o quociente entre duas grandezas.
            - Aplicar a Propriedade Fundamental das Proporções ("o produto dos extremos é igual ao produto dos meios").
            - Distinguir analiticamente e graficamente grandezas diretamente proporcionais (${'$'}y = kx${'$'}) de grandezas inversamente proporcionais (${'$'}y = \frac{k}{x}${'$'}).
            - Efetuar divisões de uma quantidade em partes direta ou inversamente proporcionais a uma lista de coeficientes.
            
            ## 3. Pré-requisitos
            - Frações e equações elementares do 1º grau (Aulas 1 e 5).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. Razão entre Grandezas
            A razão entre dois números ${'$'}a${'$'} e ${'$'}b${'$'} (${'$'}b \neq 0${'$'}) é o quociente:
            ${'$'}${'$'}R = \frac{a}{b}${'$'}${'$'}
            - *Razões de mesma espécie:* Adimensionais (ex.: escala ${'$'}1:50${'$'}, rendimento percentual).
            - *Razões de espécies distintas:* Originam novas grandezas físicas derivadas do SI:
              - **Velocidade Média:** ${'$'}v = \frac{\Delta s}{\Delta t}${'$'} (${'$'}\text{km/h}${'$'} ou ${'$'}\text{m/s}${'$'}).
              - **Densidade Demográfica:** ${'$'}D = \frac{\text{População}}{\text{Área}}${'$'} (${'$'}\text{hab/km}^2${'$'}).
              - **Concentração Comum:** ${'$'}C = \frac{m}{V}${'$'} (${'$'}\text{g/L}${'$'}).
            
            ### B. Proporção e sua Propriedade Fundamental
            Uma proporção é a igualdade formal entre duas razões equivalentes:
            ${'$'}${'$'}\frac{a}{b} = \frac{c}{d} \iff a \cdot d = b \cdot c \quad (b, d \neq 0)${'$'}${'$'}
            Onde ${'$'}a${'$'} e ${'$'}d${'$'} são os **extremos**, e ${'$'}b${'$'} e ${'$'}c${'$'} são os **meios**.
            
            ### C. Grandezas Diretamente Proporcionais (GDP)
            Duas grandezas ${'$'}x${'$'} e ${'$'}y${'$'} são diretamente proporcionais quando a sua **razão** é constante:
            ${'$'}${'$'}\frac{y}{x} = k \iff y = k \cdot x \quad (k > 0)${'$'}${'$'}
            - Quando ${'$'}x${'$'} dobra, ${'$'}y${'$'} dobra; quando ${'$'}x${'$'} cai pela metade, ${'$'}y${'$'} cai pela metade.
            - *Comportamento Gráfico:* Uma reta que passa obrigatoriamente pela origem ${'$'}(0, 0)${'$'}.
            
            ### D. Grandezas Inversamente Proporcionais (GIP)
            Duas grandezas ${'$'}x${'$'} e ${'$'}y${'$'} são inversamente proporcionais quando o seu **produto** é constante:
            ${'$'}${'$'}x \cdot y = k \iff y = \frac{k}{x} \quad (k > 0)${'$'}${'$'}
            - Quando ${'$'}x${'$'} dobra, ${'$'}y${'$'} é reduzido à metade; quando ${'$'}x${'$'} triplica, ${'$'}y${'$'} cai para um terço.
            - *Comportamento Gráfico:* Uma curva assintótica suave chamada **hipérbole equilátera**.
            
            ### E. Divisão Proporcional em Partes Diretas e Inversas
            Para dividir uma quantia total ${'$'}S${'$'} em partes ${'$'}x, y, z${'$'} proporcionais a ${'$'}p, q, r${'$'}:
            1. *Partes Diretamente Proporcionais:*
               ${'$'}${'$'}\frac{x}{p} = \frac{y}{q} = \frac{z}{r} = k \implies x = pk, \; y = qk, \; z = rk${'$'}${'$'}
               Como ${'$'}x + y + z = S \implies k(p + q + r) = S \implies k = \frac{S}{p + q + r}${'$'}.
            2. *Partes Inversamente Proporcionais:*
               Basta dividir em partes diretamente proporcionais aos **inversos** dos coeficientes (${'$'}\frac{1}{p}, \frac{1}{q}, \frac{1}{r}${'$'})!
            
            ## 5. Definições e Conceitos
            - **Constante de Proporcionalidade (${'$'}k${'$'}):** Taxa unitária fixa que parametriza o sistema físico ou econômico.
            - **Quarta Proporcional:** O quarto termo ${'$'}x${'$'} que satisfaz ${'$'}\frac{a}{b} = \frac{c}{x}${'$'}, obtido por ${'$'}x = \frac{b \cdot c}{a}${'$'}.
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Divisão de Lucro Societário Proporcional ao Capital e Tempo
            Dois sócios fundaram uma startup. O sócio A investiu R${'$'} 30.000,00 e permaneceu ativo durante 10 meses. O sócio B investiu R${'$'} 20.000,00 e permaneceu durante 15 meses. Ao final do período, a empresa registrou um lucro líquido de R${'$'} 90.000,00. Quanto cada sócio deve receber em uma divisão proporcional ao investimento e ao tempo?
            
            **Resolução Analítica:**
            - *Passo 1 (Cálculo dos pesos compostos):*
              O retorno é proporcional ao produto ${'$'}(\text{capital} \times \text{tempo})${'$'}:
              ${'$'}${'$'}\text{Peso A} = 30.000 \times 10 = 300.000${'$'}${'$'}
              ${'$'}${'$'}\text{Peso B} = 20.000 \times 15 = 300.000${'$'}${'$'}
            - *Passo 2 (Comparação dos pesos):*
              Os pesos de ambos são rigorosamente idênticos (${'$'}300.000 = 300.000${'$'})!
            - *Passo 3 (Distribuição):*
              ${'$'}${'$'}L_A = L_B = \frac{90.000}{2} = \text{R${'$'}} 45.000,00\text{ para cada sócio}${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Farmacocinética:** A dosagem terapêutica de anestésicos pediátricos é diretamente proporcional à massa corporal da criança em quilogramas (${'$'}\text{mg/kg}${'$'}).
            - **Economia e Produtividade:** A velocidade de carregamento de um navio cargueiro é inversamente proporcional ao número de guindastes operando simultaneamente no cais.
            
            ## 8. Exercícios de Fixação
            1. Um prêmio de R${'$'} 60.000,00 será dividido entre dois operários em partes inversamente proporcionais às suas faltas ao trabalho no ano. O operário A faltou 2 dias e o operário B faltou 3 dias. Quanto receberá o operário A?
            2. Três torneiras idênticas enchem um tanque em 6 horas. Quantas horas quatro dessas torneiras levariam para encher o mesmo tanque?
            
            ## 9. Correção Comentada
            - *Exercício 1:* Inversamente proporcional a 2 e 3 significa diretamente proporcional a ${'$'}\frac{1}{2}${'$'} e ${'$'}\frac{1}{3}${'$'}.
              Igualando os denominadores com MMC 6: ${'$'}\frac{3}{6}${'$'} e ${'$'}\frac{2}{6}${'$'}, ou seja, proporção de 3 para 2!
              Total de partes: ${'$'}3 + 2 = 5${'$'} partes.
              Valor de cada parte: ${'$'}\frac{60.000}{5} = \text{R${'$'}} 12.000,00${'$'}.
              Operário A (3 partes): ${'$'}3 \times 12.000 = \text{R${'$'}} 36.000,00${'$'}.
            - *Exercício 2:* Quantidade de torneiras e tempo são grandezas inversamente proporcionais (${'$'}t \cdot n = \text{constante}${'$'}):
              ${'$'}${'$'}3 \times 6 = 4 \times t \implies 18 = 4t \implies t = \frac{18}{4} = 4{,}5\text{ horas} = 4\text{ horas e } 30\text{ minutos}${'$'}${'$'}
            
            ## 10. Desafio de Domínio
            Três amigos dividiram uma conta de R${'$'} 1.200,00 em partes proporcionais às suas idades: 20, 25 e 35 anos. Porém, o mais velho pagou apenas metade da sua parte devida, e os outros dois dividiram o saldo restante proporcionalmente às suas idades. Quanto pagou o mais jovem ao final?
            *Resolução:*
            - Soma das idades: ${'$'}20 + 25 + 35 = 80${'$'}.
            - Constante inicial: ${'$'}k = \frac{1200}{80} = 15${'$'}.
            - Quotas teóricas: Jovem (${'$'}20 \times 15 = 300${'$'}), Médio (${'$'}25 \times 15 = 375${'$'}), Velho (${'$'}35 \times 15 = 525${'$'}).
            - O mais velho pagou metade: ${'$'}\frac{525}{2} = 262{,}50${'$'}. Saldo restante a cobrir: ${'$'}262{,}50${'$'}.
            - Nova proporção entre Jovem (20) e Médio (25), soma = 45:
              Adicional do jovem: ${'$'}\frac{20}{45} \times 262{,}50 = \frac{4}{9} \times 262{,}50 = 116{,}67${'$'}.
              Total pago pelo jovem: ${'$'}300 + 116{,}67 = \text{R${'$'}} 416{,}67${'$'}.
            
            ## 11. Resumo
            - Proporção fundamental: produto dos extremos igual ao produto dos meios (${'$'}a \cdot d = b \cdot c${'$'}).
            - Grandezas diretas: quociente constante (${'$'}\frac{y}{x} = k${'$'}). Gráfico é reta na origem.
            - Grandezas inversas: produto constante (${'$'}x \cdot y = k${'$'}). Gráfico é hipérbole equilátera.
            - Para divisão inversamente proporcional, inverta os coeficientes e opere como direta.
            
            ## 12. Referências
            - D'AMBROSIO, Ubiratan. *Etnomatemática: Elo entre as tradições e a modernidade*. Belo Horizonte: Autêntica, 2001.
            - INEP. *Matriz de Referência do ENEM: Matemática e suas Tecnologias*. Competência 4: Avaliar a variação de grandezas para resolver problemas. Brasília: INEP.
            - OPENSTAX. *College Algebra 2e*, Section 5.8: Modeling Using Variation. Rice University, 2021.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Propriedade Fundamental das Proporções",
                syntax = "\\frac{a}{b} = \\frac{c}{d} \\iff a \\cdot d = b \\cdot c",
                description = "Igualdade do produto cruzado em qualquer proporção válida.",
                parametersInfo = "b ≠ 0 e d ≠ 0"
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Grandezas Diretamente vs. Inversamente Proporcionais",
                syntax = "y = k \\cdot x \\;\\text{(GDP)} \\quad \\iff \\quad y = \\frac{k}{x} \\;\\text{(GIP)}",
                description = "Modelos algébricos da dependência linear direta vs. recíproca inversa.",
                parametersInfo = "k é a constante de proporcionalidade"
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Divisão em Partes Diretamente Proporcionais",
                codeOrFormula = "k = \\frac{180.000}{2 + 3 + 5} = 18.000 \\implies A=36k, B=54k, C=90k",
                explanation = """
                    Três herdeiros dividiram R${'$'} 180.000,00 proporcionalmente aos seus filhos: 2, 3 e 5 filhos.
                    
                    Passo 1: Somar as cotas:
                    2 + 3 + 5 = 10 cotas.
                    
                    Passo 2: Achar o valor de cada cota unitária k:
                    k = 180.000 / 10 = R${'$'} 18.000,00.
                    
                    Passo 3: Multiplicar pelo número de filhos de cada herdeiro:
                    Herdeiro 1 (2 filhos): 2 × 18.000 = R${'$'} 36.000,00
                    Herdeiro 2 (3 filhos): 3 × 18.000 = R${'$'} 54.000,00
                    Herdeiro 3 (5 filhos): 5 × 18.000 = R${'$'} 90.000,00
                    
                    Soma: 36.000 + 54.000 + 90.000 = R${'$'} 180.000,00.
                """.trimIndent()
            )
        ),
        goldTip = "Em divisões inversamente proporcionais a 2, 3 e 4: NÃO divida por 2, 3 e 4! Use as frações inversas 1/2, 1/3 e 1/4, iguale ao mesmo denominador (6/12, 4/12, 3/12) e use as cotas 6, 4 e 3!"
    )

    // =========================================================================
    // AULA 7: Regra de Três Simples e Composta (mb_l7)
    // =========================================================================
    private val bundleMbL7 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 7: Regra de Três Simples e Composta
            
            ## 1. Introdução
            A Regra de Três é o algoritmo clássico para encontrar um quarto valor desconhecido a partir de três valores conhecidos relacionados por proporcionalidade. Em sua versão composta, relaciona simultaneamente três ou mais grandezas interdependentes (operários, horas por dia, dias trabalhados, volume produzido e grau de dificuldade da tarefa). Errar a orientação das setas de proporcionalidade é a causa de mais de 70% dos erros dos estudantes nessa matéria.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Montar tabelas estruturadas isolando incógnitas e identificando o sentido de cada grandeza em relação à grandeza focal.
            - Resolver regras de três simples diretas e inversas com rapidez e precisão.
            - Dominar o método analítico das proporções e o método direto do produto das linhas para regras de três compostas.
            - Evitar a armadilha de comparar grandezas secundárias entre si, mantendo o foco estrito na grandeza da incógnita.
            
            ## 3. Pré-requisitos
            - Razão, proporção e grandezas diretas/inversas (Aula 6).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. O Algoritmo da Regra de Três Simples
            1. Organize as grandezas em colunas com a mesma unidade de medida.
            2. Atribua uma seta para cima na coluna que contém a incógnita (${'$'}x${'$'}).
            3. Analise cada grandeza isoladamente em relação à grandeza de ${'$'}x${'$'}:
               - *Se o aumento da grandeza provoca o aumento de ${'$'}x${'$'}:* As grandezas são **diretas** (seta no mesmo sentido).
               - *Se o aumento da grandeza provoca a redução de ${'$'}x${'$'}:* As grandezas são **inversas** (seta no sentido oposto).
            4. Se for direta, multiplica-se em cruz (${'$'}a \cdot x = b \cdot c${'$'}). Se for inversa, inverte-se a coluna antes de cruzar ou multiplica-se em linha horizontal (${'$'}a \cdot b = c \cdot x${'$'}).
            
            ### B. O Método Analítico da Regra de Três Composta
            Quando há mais de duas grandezas envolvidas:
            1. Isole a razão contendo a incógnita no primeiro membro da equação: ${'$'}\frac{x_1}{x_2}${'$'}.
            2. No segundo membro, escreva o produto de todas as outras razões.
            3. Para cada grandeza secundária:
               - Se for **direta** com a incógnita: mantenha a fração original (${'$'}\frac{a_1}{a_2}${'$'}).
               - Se for **inversa** com a incógnita: inverta a fração (${'$'}\frac{a_2}{a_1}${'$'}).
            
            ${'$'}${'$'}\frac{x_1}{x_2} = \left(\frac{a_1}{a_2}\right)_{\text{direta}} \times \left(\frac{b_2}{b_1}\right)_{\text{inversa}} \times \left(\frac{c_1}{c_2}\right)_{\text{direta}}${'$'}${'$'}
            
            ### C. O Método Alternativo: "Quem faz o quê?" (Processo vs. Produto)
            Toda questão prática de regra de três composta envolve:
            - **Sujeitos / Processo:** Operários, máquinas, horas por dia, dias, eficiência, velocidade.
            - **Produto / Efeito Final:** Metros de muro construído, peças produzidas, área pavimentada, faturamento.
            - **Equação Canônica:**
              ${'$'}${'$'}\frac{(\text{Processo})_1}{(\text{Produto})_1} = \frac{(\text{Processo})_2}{(\text{Produto})_2}${'$'}${'$'}
              Esse método elimina a necessidade de desenhar setas, reduzindo drasticamente as chances de erro!
            
            ## 5. Definições e Conceitos
            - **Grandeza Focal:** A grandeza que contém a incógnita ${'$'}x${'$'}.
            - **Independência Condicional:** Ao avaliar a relação entre a grandeza A e a grandeza da incógnita, considere todas as outras grandezas temporariamente fixas e constantes.
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Regra de Três Composta Completa
            Em uma fábrica têxtil, 12 costureiras, trabalhando 8 horas por dia durante 15 dias, confeccionam 1.800 peças de uniforme. Devido a uma encomenda urgente, a empresa contrata mais 6 costureiras com a mesma produtividade. Trabalhando 10 horas por dia, quantos dias essas 18 costureiras levarão para produzir 3.600 peças?
            
            **Resolução Analítica:**
            - *Passo 1 (Organização das Grandezas):*
              - Costureiras: Inicial = 12; Final = ${'$'}12 + 6 = 18${'$'}.
              - Horas/dia: Inicial = 8; Final = 10.
              - Peças (Produto): Inicial = 1.800; Final = 3.600.
              - Dias (Incógnita ${'$'}x${'$'}): Inicial = 15; Final = ${'$'}x${'$'}.
            - *Passo 2 (Análise de Proporcionalidade em relação aos Dias):*
              - *Mais costureiras* ${'$'}\implies${'$'} *Menos dias* necessários (Inversa ${'$'}\to${'$'} inverter para ${'$'}\frac{18}{12}${'$'}).
              - *Mais horas por dia* ${'$'}\implies${'$'} *Menos dias* necessários (Inversa ${'$'}\to${'$'} inverter para ${'$'}\frac{10}{8}${'$'}).
              - *Mais peças a produzir* ${'$'}\implies${'$'} *Mais dias* necessários (Direta ${'$'}\to${'$'} manter ${'$'}\frac{1.800}{3.600}${'$'}).
            - *Passo 3 (Montagem da Equação Analítica):*
              ${'$'}${'$'}\frac{15}{x} = \frac{18}{12} \times \frac{10}{8} \times \frac{1.800}{3.600}${'$'}${'$'}
            - *Passo 4 (Simplificação de Frações antes de multiplicar):*
              - ${'$'}\frac{18}{12} = \frac{3}{2}${'$'}
              - ${'$'}\frac{10}{8} = \frac{5}{4}${'$'}
              - ${'$'}\frac{1.800}{3.600} = \frac{1}{2}${'$'}
              ${'$'}${'$'}\frac{15}{x} = \frac{3}{2} \times \frac{5}{4} \times \frac{1}{2} = \frac{15}{16}${'$'}${'$'}
            - *Passo 5 (Resolução final):*
              ${'$'}${'$'}\frac{15}{x} = \frac{15}{16} \implies x = 16\text{ dias}${'$'}${'$'}
            
            ## 7. Aplicações Práticas
            - **Engenharia de Produção e Obras:** Dimensionamento de cronogramas de pavimentação asfáltica onde tratores, horas de turno e extensão da via são otimizados sob prazos de entrega contratuais.
            - **Logística Hospitalar:** Cálculo de suprimento de cilindros de oxigênio em leitos de UTI em função do fluxo em litros por minuto e tempo de uso de pacientes.
            
            ## 8. Exercícios de Fixação
            1. Se 8 tratores realizam a aragem de um terreno em 15 horas, quantas horas levariam 12 tratores com a mesma capacidade operacional para realizar a mesma tarefa?
            2. Três impressoras industriais imprimem 120.000 panfletos em 4 horas. Quantas impressoras idênticas seriam necessárias para imprimir 300.000 panfletos em 5 horas?
            
            ## 9. Correção Comentada
            - *Exercício 1:* Tratores e horas são grandezas inversamente proporcionais:
              ${'$'}${'$'}8 \times 15 = 12 \times x \implies 120 = 12x \implies x = 10\text{ horas}${'$'}${'$'}
            - *Exercício 2:* Método do Processo/Produto:
              - Processo 1: ${'$'}3\text{ impressoras} \times 4\text{ horas} = 12${'$'}. Produto 1: ${'$'}120.000${'$'}.
              - Processo 2: ${'$'}n\text{ impressoras} \times 5\text{ horas} = 5n${'$'}. Produto 2: ${'$'}300.000${'$'}.
              ${'$'}${'$'}\frac{12}{120.000} = \frac{5n}{300.000} \implies \frac{1}{10.000} = \frac{5n}{300.000} \implies 50.000n = 300.000 \implies n = 6\text{ impressoras}${'$'}${'$'}
            
            ## 10. Desafio de Domínio
            Cinco operários de rendimento padrão constroem um muro de ${'$'}60\text{ metros}${'$'} de comprimento por ${'$'}2\text{ metros}${'$'} de altura em 6 dias, trabalhando 8 horas por dia. Se a empresa precisa erguer um muro de ${'$'}100\text{ metros}${'$'} de comprimento por ${'$'}3\text{ metros}${'$'} de altura em 10 dias, com operários trabalhando 6 horas por dia, cuja produtividade individual é 20% menor que a dos operários originais, quantos operários devem ser contratados?
            *Resolução:*
            - Área do muro 1: ${'$'}60 \times 2 = 120\text{ m}^2${'$'}.
            - Área do muro 2: ${'$'}100 \times 3 = 300\text{ m}^2${'$'}.
            - Produtividade relativa: ${'$'}P_1 = 1{,}0${'$'} e ${'$'}P_2 = 0{,}8${'$'}.
            - Equação: ${'$'}\frac{5 \times 1{,}0 \times 6 \times 8}{120} = \frac{n \times 0{,}8 \times 10 \times 6}{300}${'$'}.
            - Lado esquerdo: ${'$'}\frac{240}{120} = 2${'$'}.
            - Lado direito: ${'$'}\frac{48n}{300} = 2 \implies 48n = 600 \implies n = 12{,}5 \implies${'$'} serão necessários 13 operários inteiros!
            
            ## 11. Resumo
            - Compare sempre cada grandeza diretamente com a coluna do ${'$'}x${'$'}.
            - Grandezas diretas mantêm a fração; grandezas inversas invertem a fração.
            - No método Processo/Produto: ${'$'}\frac{(\text{Quem e Quando})_1}{\text{O que foi feito}_1} = \frac{(\text{Quem e Quando})_2}{\text{O que foi feito}_2}${'$'}.
            
            ## 12. Referências
            - BRASIL. MEC/INEP. *Guia de Correção da Prova de Matemática e suas Tecnologias*. Brasília: INEP, 2021.
            - LIMA, Elon Lages. *Temas e Problemas Elementares*. Rio de Janeiro: SBM, 2013.
            - OPENSTAX. *Prealgebra 2e*, Section 7.5: Solve Proportion and Similar Figure Applications. Rice University, 2020.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Equação Geral da Regra de Três Composta",
                syntax = "\\frac{x_1}{x_2} = \\prod_{i \\in \\text{Diretas}} \\frac{d_{1,i}}{d_{2,i}} \\times \\prod_{j \\in \\text{Inversas}} \\frac{inv_{2,j}}{inv_{1,j}}",
                description = "Isolamento da razão com a incógnita e produto ponderado das razões diretas e inversas.",
                parametersInfo = "Mantenha rigorosamente as mesmas unidades em cada coluna."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Método Canônico Processo vs. Produto",
                syntax = "\\frac{\\text{Sujeitos}_1 \\times \\text{Tempo}_1}{\\text{Obra / Efeito Final}_1} = \\frac{\\text{Sujeitos}_2 \\times \\text{Tempo}_2}{\\text{Obra / Efeito Final}_2}",
                description = "Relação universal direta que dispensa setas em problemas de produtividade e engenharia.",
                parametersInfo = "A obra representa a meta física final executada."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Construção de Pavimentação com Diferentes Máquinas",
                codeOrFormula = "\\frac{6 \\times 10 \\times 8}{4000} = \\frac{10 \\times d \\times 6}{6000} \\implies d = 12\\text{ dias}",
                explanation = """
                    Se 6 máquinas trabalhando 8 horas por dia durante 10 dias asfaltam 4.000 m² de rodovia, quantos dias levarão 10 máquinas trabalhando 6 horas por dia para asfaltar 6.000 m²?
                    
                    Passo 1: Aplicar Processo / Produto:
                    (6 máquinas × 8 h/dia × 10 dias) / 4.000 m² = (10 máquinas × 6 h/dia × d dias) / 6.000 m²
                    
                    Passo 2: Simplificar os termos numéricos:
                    (480) / 4.000 = (60d) / 6.000
                    0,12 = 0,01d
                    
                    Passo 3: Isolar a incógnita:
                    d = 0,12 / 0,01 = 12 dias exatos!
                """.trimIndent()
            )
        ),
        goldTip = "Ao analisar as setas da regra de três, NUNCA olhe para os números, olhe apenas para o CONCEITO! Pergunte: 'Se eu tiver mais operários, preciso de MAIS ou MENOS dias para o mesmo serviço?'"
    )

    // =========================================================================
    // AULA 8: Escalas Cartográficas (Lineares, Superficiais e Volumétricas) (mb_l8)
    // =========================================================================
    private val bundleMbL8 = CalculusDidacticBundle(
        theoryMarkdown = """
            # Aula 8: Escalas Cartográficas (Lineares, Superficiais e Volumétricas)
            
            ## 1. Introdução
            As escalas cartográficas e maquetes representam a redução matemática proporcional do espaço real em plantas, mapas e modelos tridimensionais. No ENEM, as escalas aparecem anualmente em questões interdisciplinares de Matemática e Geografia. O desafio mais frequente e traiçoeiro reside na transição da escala linear para o cálculo de **áreas territoriais** e **volumes de maquetes**, onde a razão de proporção deve ser elevada ao quadrado ou ao cubo.
            
            ## 2. Objetivos de Aprendizagem
            Ao concluir esta aula, você será capaz de:
            - Definir e calcular a escala linear ${'$'}E = \frac{d}{D}${'$'} mantendo estrita homogeneidade de unidades (${'$'}\text{cm}${'$'} para ${'$'}\text{cm}${'$'}).
            - Determinar distâncias reais a partir de medições com régua em mapas.
            - Aplicar o Teorema da Escala de Áreas: ${'$'}\frac{\text{Área no mapa}}{\text{Área real}} = E^2${'$'}.
            - Aplicar o Teorema da Escala de Volumes: ${'$'}\frac{\text{Volume na maquete}}{\text{Volume real}} = E^3${'$'}.
            - Diferenciar com clareza escala gráfica de escala numérica e identificar qual escala é "maior" ou "menor".
            
            ## 3. Pré-requisitos
            - Razão e proporção (Aula 6) e conversão de unidades lineares, de área e de volume (Aula 5).
            
            ## 4. Explicação Teórica Aprofundada
            
            ### A. Definição da Escala Numérica Linear
            A escala numérica ${'$'}E${'$'} é a razão pura (adimensional) entre a distância gráfica medida no mapa (${'$'}d${'$'}) e a distância real equivalente no terreno (${'$'}D${'$'}):
            ${'$'}${'$'}E = \frac{d}{D} \quad \text{ou na forma habitual} \quad 1 : M${'$'}${'$'}
            - *Regra fundamental:* ${'$'}d${'$'} e ${'$'}D${'$'} **devem estar obrigatoriamente na mesma unidade** (geralmente centímetros, ${'$'}\text{cm}${'$'}) antes de efetuar a simplificação.
            - Exemplo: Uma escala de ${'$'}1 : 50.000${'$'} significa que ${'$'}1\text{ cm}${'$'} no mapa corresponde a ${'$'}50.000\text{ cm}${'$'} no mundo real.
              Convertendo para metros: ${'$'}50.000\text{ cm} = 500\text{ m} = 0{,}5\text{ km}${'$'}.
            
            ### B. Escala "Grande" vs. Escala "Pequena"
            Como a escala é uma fração (${'$'}\frac{1}{M}${'$'}), quanto **menor** for o denominador ${'$'}M${'$'}, **maior** será o valor numérico da fração:
            - **Escala Grande (${'$'}1:50${'$'} até ${'$'}1:10.000${'$'}):** Denominador pequeno. Representa áreas menores com **grande riqueza de detalhes** (plantas arquitetônicas de casas, bairros, projetos de engenharia).
            - **Escala Pequena (${'$'}1:1.000.000${'$'} até ${'$'}1:50.000.000${'$'}):** Denominador gigantesco. Representa áreas imensas com **baixo detalhamento** e grande generalização (mapamúndi, continentes, países).
            
            ### C. A Escala de Áreas (Bidimensional — Quadrática)
            Se todas as dimensões lineares de uma figura plana são multiplicadas por ${'$'}E${'$'}, a sua área é multiplicada por ${'$'}E^2${'$'}:
            ${'$'}${'$'}\frac{\text{Área no mapa}}{\text{Área real}} = E^2 = \left(\frac{1}{M}\right)^2 = \frac{1}{M^2}${'$'}${'$'}
            - *Exemplo Prático:* Se a escala linear é ${'$'}1 : 100${'$'}, a escala de áreas será:
              ${'$'}${'$'}\left(\frac{1}{100}\right)^2 = \frac{1}{10.000}${'$'}${'$'}
              Portanto, ${'$'}1\text{ cm}^2${'$'} no desenho corresponde a ${'$'}10.000\text{ cm}^2 = 1\text{ m}^2${'$'} no terreno real!
            
            ### D. A Escala de Volumes e Capacidades (Tridimensional — Cúbica)
            Para maquetes, protótipos e modelos tridimensionais, todas as três dimensões espaciais (comprimento, largura e altura) são reduzidas pelo fator ${'$'}E${'$'}. Logo, a razão entre os volumes é dada por ${'$'}E^3${'$'}:
            ${'$'}${'$'}\frac{\text{Volume na maquete}}{\text{Volume real}} = E^3 = \left(\frac{1}{M}\right)^3 = \frac{1}{M^3}${'$'}${'$'}
            - *Exemplo Crítico do ENEM:* Uma maquete de um prédio na escala linear de ${'$'}1 : 100${'$'} tem um reservatório de água com volume de ${'$'}8\text{ cm}^3${'$'}. Qual é o volume real do reservatório em litros?
              ${'$'}${'$'}\frac{V_{\text{maquete}}}{V_{\text{real}}} = \left(\frac{1}{100}\right)^3 = \frac{1}{1.000.000}${'$'}${'$'}
              ${'$'}${'$'}V_{\text{real}} = 8\text{ cm}^3 \times 1.000.000 = 8.000.000\text{ cm}^3${'$'}${'$'}
              Como ${'$'}1.000\text{ cm}^3 = 1\text{ L}${'$'}, temos: ${'$'}V_{\text{real}} = \frac{8.000.000}{1000} = 8.000\text{ litros}${'$'}!
            
            ## 5. Definições e Conceitos
            - **Escala Gráfica:** Linha graduada desenhada diretamente no mapa que indica a correspondência métrica visual imediata (ex.: um segmento de ${'$'}1\text{ cm}${'$'} com a indicação "${'$'}5\text{ km}${'$'}"). Possui a grande vantagem de manter a proporcionalidade caso o mapa seja ampliado ou reduzido por fotocópia digital.
            - **Mapeamento Topográfico:** Representação do relevo através de curvas de nível baseada em escalas detalhadas.
            
            ## 6. Exemplos Resolvidos Passo a Passo
            
            ### Exemplo 1: Cálculo de Área Real de Lagoa em Mapa Cartográfico
            Em um mapa cartográfico de escala ${'$'}1 : 250.000${'$'}, uma reserva florestal retangular ocupa uma área gráfica desenhada de ${'$'}4\text{ cm}${'$'} de comprimento por ${'$'}3\text{ cm}${'$'} de largura. Qual é a área real dessa reserva em quilômetros quadrados (${'$'}\text{km}^2${'$'})?
            
            **Resolução Analítica:**
            - *Método das Dimensões Lineares Reais:*
              - Na escala ${'$'}1 : 250.000${'$'}, cada ${'$'}1\text{ cm}${'$'} representa ${'$'}250.000\text{ cm}${'$'}.
              - Convertendo para quilômetros: ${'$'}250.000\text{ cm} = 2.500\text{ m} = 2{,}5\text{ km}${'$'}.
              - Comprimento real: ${'$'}4\text{ cm} \times 2{,}5\text{ km/cm} = 10\text{ km}${'$'}.
              - Largura real: ${'$'}3\text{ cm} \times 2{,}5\text{ km/cm} = 7{,}5\text{ km}${'$'}.
              - Área real no terreno:
                ${'$'}${'$'}\text{Área} = 10\text{ km} \times 7{,}5\text{ km} = 75\text{ km}^2${'$'}${'$'}
            - *Método da Escala de Áreas Direta:*
              - Área no mapa: ${'$'}a = 4 \times 3 = 12\text{ cm}^2${'$'}.
              - Como ${'$'}1\text{ cm} = 2{,}5\text{ km}${'$'}, elevando ao quadrado:
                ${'$'}${'$'}1\text{ cm}^2 = (2{,}5\text{ km})^2 = 6{,}25\text{ km}^2${'$'}${'$'}
              - Área real: ${'$'}12 \times 6{,}25 = 75\text{ km}^2${'$'}.
              Ambos os métodos confirmam com absoluta precisão o resultado de ${'$'}75\text{ km}^2${'$'}!
            
            ## 7. Aplicações Práticas
            - **Sistemas de Informação Geográfica (SIG / GPS):** Ferramentas como Google Maps e softwares de georreferenciamento alternam dinamicamente a escala digital em tempo real conforme o usuário aplica o zoom na tela.
            - **Design Automobilístico e Túnel de Vento:** Protótipos aerodinâmicos de carros de corrida são testados na escala ${'$'}1 : 4${'$'} em túneis de vento para medição da resistência do ar e sustentação.
            
            ## 8. Exercícios de Fixação
            1. A distância em linha reta entre duas cidades em um mapa desenhado na escala ${'$'}1 : 500.000${'$'} é de ${'$'}7{,}2\text{ cm}${'$'}. Qual é a distância real em quilômetros?
            2. Um protótipo em miniatura de um tanque esférico de combustível foi construído na escala ${'$'}1 : 20${'$'}. Se o modelo consome ${'$'}250\text{ mL}${'$'} de tinta para ser preenchido internamente, qual é o volume do tanque real em litros?
            
            ## 9. Correção Comentada
            - *Exercício 1:*
              ${'$'}${'$'}D = 7{,}2 \times 500.000\text{ cm} = 3.600.000\text{ cm}${'$'}${'$'}
              Convertendo para metros (divide por 100): ${'$'}36.000\text{ m}${'$'}.
              Convertendo para km (divide por 1000): ${'$'}36\text{ km}${'$'}.
            - *Exercício 2:* Relação volumétrica cúbica:
              ${'$'}${'$'}\frac{V_{\text{miniatura}}}{V_{\text{real}}} = \left(\frac{1}{20}\right)^3 = \frac{1}{8.000}${'$'}${'$'}
              ${'$'}${'$'}V_{\text{real}} = 250\text{ mL} \times 8.000 = 2.000.000\text{ mL} = 2.000\text{ litros}${'$'}${'$'}
            
            ## 10. Desafio de Domínio
            Um arquiteto imprimiu a planta de uma residência na escala ${'$'}1 : 50${'$'}. Uma sala quadrada tinha área de ${'$'}16\text{ cm}^2${'$'} no desenho. Por equívoco na reprografia, a planta foi reduzida por fotocópia e a mesma sala passou a medir ${'$'}9\text{ cm}^2${'$'} no novo papel. Qual é a nova escala do desenho fotocopiado?
            *Resolução:*
            - Na escala original ${'$'}1 : 50${'$'}, o lado da sala quadrada no papel era ${'$'}\sqrt{16} = 4\text{ cm}${'$'}.
            - Lado real da sala: ${'$'}4\text{ cm} \times 50 = 200\text{ cm} = 2\text{ metros}${'$'}.
            - Na planta reduzida, a área é ${'$'}9\text{ cm}^2${'$'}, logo o lado no novo papel é ${'$'}\sqrt{9} = 3\text{ cm}${'$'}.
            - A nova escala é a razão entre o novo lado gráfico (${'$'}3\text{ cm}${'$'}) e o lado real (${'$'}200\text{ cm}${'$'}):
              ${'$'}${'$'}\text{Nova Escala} = \frac{3}{200} = 1 : \frac{200}{3} \approx 1 : 66{,}67${'$'}${'$'}
            
            ## 11. Resumo
            - Escala linear: ${'$'}E = \frac{d}{D}${'$'} (sempre na mesma unidade de medida).
            - Escala de áreas: ${'$'}\frac{A_{\text{mapa}}}{A_{\text{real}}} = E^2${'$'}.
            - Escala de volumes: ${'$'}\frac{V_{\text{modelo}}}{V_{\text{real}}} = E^3${'$'}.
            - Escala grande mostra detalhes em áreas pequenas; escala pequena mostra grandes extensões territoriais.
            
            ## 12. Referências
            - IBGE. *Atlas Geográfico Escolar*. 8. ed. Rio de Janeiro: IBGE, 2018.
            - DUARTE, Paulo Araújo. *Fundamentos de Cartografia*. Florianópolis: Editora da UFSC, 2008.
            - INEP. *Matriz de Referência do ENEM: Matemática e suas Tecnologias*. Habilidade 12: Resolver situação-problema que envolva escala e proporcionalidade geométrica.
        """.trimIndent(),
        formulas = listOf(
            FormulaOrSyntaxItem(
                termOrFunction = "Escala Linear Cartográfica",
                syntax = "E = \\frac{d}{D} = \\frac{1}{M}",
                description = "Razão dimensional direta entre a medida gráfica d e a distância real D.",
                parametersInfo = "d e D devem estar estritamente na mesma unidade métrica (cm)."
            ),
            FormulaOrSyntaxItem(
                termOrFunction = "Teorema da Escala Superficial e Volumétrica",
                syntax = "\\frac{A_{\\text{mapa}}}{A_{\\text{real}}} = E^2 = \\frac{1}{M^2} \\quad \\text{e} \\quad \\frac{V_{\\text{maquete}}}{V_{\\text{real}}} = E^3 = \\frac{1}{M^3}",
                description = "Ampliação ou redução quadrática para áreas e cúbica para volumes.",
                parametersInfo = "M é o denominador da escala linear."
            )
        ),
        solvedExamples = listOf(
            LessonExample(
                title = "Problema Clássico ENEM: Capacidade de Maquete de Piscina",
                codeOrFormula = "\\frac{V_{\\text{maquete}}}{V_{\\text{real}}} = \\left(\\frac{1}{50}\\right)^3 = \\frac{1}{125.000} \\implies V_{\\text{real}} = 400\\text{ mL} \\times 125.000 = 50.000\\text{ L}",
                explanation = """
                    Uma maquete de condomínio é construída na escala 1 : 50. A piscina do clube na maquete possui capacidade volumétrica de 400 mL de água.
                    Qual é o volume real da piscina em litros?
                    
                    Passo 1: Aplicar a relação cúbica:
                    E³ = (1/50)³ = 1 / 125.000.
                    
                    Passo 2: Multiplicar pelo fator volumétrico:
                    V_real = 400 mL × 125.000 = 50.000.000 mL.
                    
                    Passo 3: Converter de mL para Litros (dividir por 1.000):
                    50.000.000 / 1.000 = 50.000 litros (ou 50 m³).
                """.trimIndent()
            )
        ),
        goldTip = "O erro mais punido pelo TRI no ENEM em escalas: calcular área multiplicando apenas por E em vez de E²! Se a escala é 1:100, a área é multiplicada por 100² = 10.000!"
    )
}
