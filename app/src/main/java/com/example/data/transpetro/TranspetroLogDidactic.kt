package com.example.data.transpetro

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

object TranspetroLogDidactic {

    fun getBundle(lessonId: String): CalculusDidacticBundle? = when (lessonId) {
        // =====================================================================
        // MÓDULO 1: ADMINISTRAÇÃO DE MATERIAIS
        // =====================================================================
        "tp_log_m1_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m1_a1",
            intro = "A Administração de Recursos Materiais é o subsistema logístico responsável por garantir que a organização disponha dos insumos, peças, combustíveis e equipamentos certos, no local adequado, no momento oportuno, nas quantidades estipuladas e ao menor custo global. Na prova da Fundação Cesgranrio para a Transpetro, o tema é cobrado sob a ótica da tríade operacional 'suprir, armazenar e distribuir', do equilíbrio entre custo de estocagem e nível de atendimento, e da busca contínua por acurácia nos controles patrimoniais.",
            theory = """
                1. Conceito e Escopo da Administração de Materiais:
                A administração de materiais coordena o fluxo físico de bens tangíveis necessários ao funcionamento da organização, compreendendo três grandes macroprocessos:
                • Gestão de Compras (Suprimento): aquisição, negociação e diligenciamento de materiais e serviços.
                • Gestão de Estoques: planejamento, controle quantitativo e financeiro dos materiais estocados.
                • Gestão de Centros de Distribuição / Almoxarifados: recebimento, guarda física, conservação, endereçamento e expedição.

                2. A Tríade Logística Operacional:
                • Suprir: pesquisar mercado, qualificar fornecedores e programar entregas em consonância com as necessidades operacionais.
                • Armazenar: preservar a integridade física de peças, conexões, reagentes e equipamentos, otimizando o aproveitamento volumétrico e a segurança das instalações.
                • Distribuir: alimentar os pontos de consumo operacional (bases de bombeamento, navios petroleiros, frentes de manutenção de dutos) com pontualidade e rastreabilidade.

                3. Trade-off Clássico (Custo vs. Nível de Serviço):
                • Estoques elevados: maximizam a disponibilidade e minimizam o risco de parada operacional (nível de serviço próximo de 100%), mas geram alto custo de oportunidade do capital parado, custo de armazenagem, risco de obsolescência e deterioração.
                • Estoques reduzidos: diminuem custos financeiros e imobilização de capital, mas elevam o risco de ruptura de estoque (stockout), paralisando manutenções críticas ou operações de transporte de óleo e derivados.

                4. Acurácia dos Controles de Estoque:
                • Acuracidade física x contábil mede o percentual de itens cujo saldo registrado no sistema corporativo (ERP) coincide exatamente com o saldo físico apurado em contagem presencial.
                • Fórmula do Índice de Acuracidade (IA):
                  IA = (Número de Itens com Saldo Físico Correto / Número Total de Itens Verificados) × 100%.
                • Em operações de alta criticidade e empresas estatais com auditorias rigorosas, a meta corporativa de acuracidade situa-se rotineiramente acima de 98%.
            """.trimIndent(),
            practicalApplication = """
                Em uma base de distribuição ou terminal aquaviário da Transpetro, um sobressalente de bomba de transferência de combustível não pode faltar durante uma manutenção programada, pois uma hora de paralisação de navio atracionado acarreta custos vultosos de sobreestadia (demurrage). Ao mesmo tempo, manter dezenas de rotores e válvulas de grande porte estocados por anos imobiliza milhões de reais do orçamento. A gestão de materiais busca o equilíbrio exato, dimensionando estoques estratégicos com elevado índice de acuracidade cadastral.
            """.trimIndent(),
            summary = """
                • Macroprocessos: Gestão de Compras, Gestão de Estoques e Gestão de Almoxarifados (Suprir, Armazenar e Distribuir).
                • Trade-off central: balancear o custo de manutenção de estoques contra o custo de ruptura e o nível de serviço ao usuário interno.
                • Acuracidade: conformidade percentual estrita entre o saldo físico no almoxarifado e o saldo escritural registrado no sistema de controle.
            """.trimIndent(),
            formulaName = "Índice de Acuracidade de Estoque",
            formulaSyntax = "IA = (N_conformes / N_total) × 100%",
            formulaTerms = listOf(
                "IA = Índice de Acuracidade (%)",
                "N_conformes = Quantidade de itens com conferência física idêntica ao registro contábil",
                "N_total = Quantidade total de itens amostrados ou inventariados"
            ),
            formulaWhenToUse = "Utilizar para mensurar a confiabilidade dos registros de estoque e avaliar a eficácia dos procedimentos de recebimento, baixa e controle físico.",
            exampleTitle = "Cálculo da Acuracidade em Inventário Amostral",
            exampleProblem = "Uma equipe de almoxarifado conferiu 250 itens de sobressalentes de dutos em um terminal. Destes, 235 itens apresentaram exata correspondência física e contábil, 10 itens apresentaram sobras físicas e 5 itens apresentaram faltas físicas. Qual é a acuracidade de estoque observada?",
            exampleSteps = listOf(
                "Identificar o número de itens com conformidade absoluta: 235.",
                "Identificar o total de itens verificados: 250.",
                "Aplicar a fórmula: IA = (235 / 250) × 100%.",
                "Calcular a razão: 235 / 250 = 0,94 = 94%."
            ),
            exampleAnswer = "O índice de acuracidade de estoque verificado é de 94%.",
            goldTip = "Na Cesgranrio, atente-se: sobra de estoque também é divergência! Tanto itens com faltas quanto itens com sobras físicas reduzem o numerador da acuracidade."
        )
        "tp_log_m1_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m1_a2",
            intro = "A classificação de materiais é o agrupamento de itens segundo características físicas, operacionais e de valor para orientar decisões de armazenagem e aquisição. Para o concurso da Transpetro, a Cesgranrio foca nos atributos da classificação (abrangência, flexibilidade e praticidade), na classificação por criticidade operacional (Sistema XYZ) e na classificação por periculosidade e perecibilidade.",
            theory = """
                1. Atributos Fundamentais da Classificação de Materiais:
                • Abrangência: o sistema deve cobrir a totalidade dos materiais utilizados na empresa, desde materiais de escritório até turbinas e dutos de grande diâmetro.
                • Flexibilidade: deve permitir a inclusão de novos itens e adaptações sem exigir a reestruturação de todo o catálogo.
                • Praticidade: deve ser simples, direto, de fácil compreensão e operacionalização pelos profissionais de almoxarifado e compras.

                2. Classificação por Criticidade Operacional (Sistema XYZ):
                Avalia o impacto da falta do material sobre as operações essenciais da organização:
                • Classe X (Baixa Criticidade): materiais de aplicação comum cuja falta não paralisa a produção nem compromete a segurança; possuem fácil obtenção no mercado local e equivalentes intercambiáveis.
                • Classe Y (Média Criticidade): materiais com grau intermediário de importância operacional, cuja falta causa desarranjos ou atrasos parciais, mas para os quais existem similares ou fornecedores alternativos em prazo moderado.
                • Classe Z (Alta Criticidade - Máxima Importância): itens vitais para a operação ou segurança pessoal e ambiental. Sua falta acarreta paralisação imediata de linhas de bombeamento, navios ou instalações industriais, não havendo substitutos imediatos nem fornecedores de pronta entrega. Exigem controle rigoroso de estoque mínimo.

                3. Classificação por Periculosidade e Perecibilidade:
                • Periculosidade: substâncias inflamáveis, corrosivas, tóxicas, explosivas ou radioativas. Exigem instalações especiais de armazenamento (diques de contenção, ventilação à prova de explosão, extintores específicos e FISPQ/FDS afixada).
                • Perecibilidade: materiais sujeitos a degradação temporal (tintas industriais, reagentes de análise de combustíveis, borrachas de vedação e elastômeros). Exigem rigorosa gestão do prazo de validade (método FEFO).
            """.trimIndent(),
            practicalApplication = """
                Em um terminal de tancagem de óleo combustível, gaxetas especiais e selos mecânicos de bombas de alta pressão são classificados como 'Classe Z': embora possuam custo monetário unitário às vezes moderado, sua ausência impede o bombeamento de derivados e gera riscos ambientais de vazamento. Em contrapartida, parafusos galvanizados comuns de fixação de grades de piso são 'Classe X', pois podem ser adquiridos em fornecedores locais sem paralisar operações vitais.
            """.trimIndent(),
            summary = """
                • Atributos da classificação: Abrangência (cobre tudo), Flexibilidade (permite mudanças) e Praticidade (fácil uso).
                • Classificação XYZ: mede o impacto da falta nas operações essenciais (X = não crítico; Y = intermediário; Z = crítico, paralisa a operação ou afeta segurança/meio ambiente).
                • Materiais perigosos e perecíveis exigem armazenagem técnica especializada e controle por FEFO.
            """.trimIndent(),
            formulaName = "Matriz de Criticidade XYZ",
            formulaSyntax = "Criticidade = Impacto da Falha × Tempo de Reposição Externa",
            formulaTerms = listOf(
                "Classe X = Falta não afeta a continuidade operacional",
                "Classe Y = Falta causa atrasos sem interrupção catastrófica",
                "Classe Z = Falta causa paralisação total da operação ou risco à segurança/meio ambiente"
            ),
            formulaWhenToUse = "Utilizar para definir políticas diferenciadas de estoque de segurança e prioridade de compra para sobressalentes vitais.",
            exampleTitle = "Classificação de Sobressalentes Operacionais",
            exampleProblem = "Uma equipe de suprimentos analisa três itens: (1) Filtro de óleo para gerador de emergência de plataforma sem fornecedor alternativo de entrega rápida; (2) Fita crepe de demarcação de piso de oficina; (3) Válvula reguladora com 2 fornecedores nacionais e prazo de entrega de 15 dias. Como classificar os itens pelo Sistema XYZ?",
            exampleSteps = listOf(
                "Item 1: Essencial para segurança e operação de emergência, sem substituto imediato -> Classe Z.",
                "Item 2: Uso rotineiro, não crítico, facilmente substituível -> Classe X.",
                "Item 3: Importância moderada com fornecedores nacionais e reposição viável -> Classe Y."
            ),
            exampleAnswer = "Item 1 é Classe Z; Item 2 é Classe X; Item 3 é Classe Y.",
            goldTip = "Não confunda Curva ABC (valor financeiro acumulado) com Curva XYZ (criticidade operacional). Um parafuso de vedação de duto pode ser Classe C no valor monetário, mas Classe Z na criticidade!"
        )
        "tp_log_m1_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m1_a3",
            intro = "A padronização e a codificação de materiais são os alicerces da gestão da cadeia de suprimentos. Evitam compras duplicadas de itens idênticos com nomes comerciais distintos, reduzem variedades de peças em estoque e asseguram uma linguagem técnica unívoca entre solicitantes, almoxarifes e compradores. A Cesgranrio cobra a distinção entre especificação, padronização e normalização, além dos sistemas de codificação e tecnologias de identificação (código de barras e RFID).",
            theory = """
                1. Especificação, Padronização e Normalização:
                • Especificação: descrição detalhada e técnica das propriedades mecânicas, químicas, elétricas e dimensionais de um item, definindo seu padrão de qualidade e desempenho.
                • Padronização: redução do número de tipos, modelos e variedades de um mesmo material utilizado na empresa. Exemplo: adotar apenas três tipos de válvulas de bloqueio padrão para todos os novos ramais de oleodutos.
                • Normalização: aplicação de normas técnicas consagradas (ABNT, ISO, ASTM, API) na fabricação, ensaio e emprego dos materiais, garantindo intercambiabilidade internacional.

                2. Sistemas de Codificação de Materiais:
                Codificar significa atribuir a cada material um conjunto ordenado de caracteres (letras e/ou números) que o identifique unicamente no catálogo de suprimentos.
                • Sistema Numérico (Decimal): utiliza algarismos agrupados por classes e subclasses. Exemplo: 12.34.567.8 (12 = Grupo Tubulações, 34 = Subgrupo Flanges, 567 = Diâmetro e Pressão, 8 = Dígito Verificador). É o mais adotado em grandes corporações e sistemas ERP (SAP).
                • Sistema Alfanumérico: combina letras e números (ex.: FL-A36-100 para Flange Aço Carbono ASTM A36 DN 100).
                • Sistema Alfabético: utiliza apenas letras (pouco utilizado em estoques complexos).

                3. Tecnologias de Captura Automática de Dados (AIDC):
                • Código de Barras (1D/2D - QR Code): leitura óptica linear ou bidimensional; exige linha de visada direta entre o leitor e o código impresso na etiqueta; baixo custo unitário.
                • RFID (Identificação por Radiofrequência): leitura por ondas eletromagnéticas de tags com microchips e antenas; não exige linha de visada; permite leitura simultânea e em massa de múltiplos paletes em movimento; alta resistência a poeira, óleo e umidade em ambientes industriais severos.
            """.trimIndent(),
            practicalApplication = """
                Na Transpetro, a padronização de tubulações e conexões sob normas API e ABNT permite que uma equipe de manutenção em um terminal de São Paulo utilize conexões sobressalentes transferidas de um terminal no Rio de Janeiro com total compatibilidade técnica. A codificação decimal unificada no ERP garante que o mesmo anel de vedação nunca seja cadastrado sob dois códigos distintos, evitando compras em duplicidade.
            """.trimIndent(),
            summary = """
                • Especificação: detalhamento técnico; Padronização: redução de variedades; Normalização: conformidade com normas técnicas (ABNT, API).
                • Codificação Decimal: estruturação hierárquica por grupos, classes e itens individuais, viabilizando busca e auditoria no ERP.
                • RFID vs. Código de Barras: RFID permite leitura a distância e em lote sem visada direta, ideal para rastreamento de cargas industriais pesadas.
            """.trimIndent(),
            formulaName = "Estrutura Hierárquica da Codificação Decimal",
            formulaSyntax = "Código = [Grupo].[Subgrupo].[Classe].[Item Individual]-[DV]",
            formulaTerms = listOf(
                "Grupo = Grande família de materiais (ex.: Válvulas)",
                "Subgrupo = Categoria funcional específica (ex.: Válvulas Esfera)",
                "Classe = Parâmetros dimensionais e de pressão (ex.: Classe 300, 4 polegadas)",
                "Item e DV = Número sequencial identificador e dígito de controle"
            ),
            formulaWhenToUse = "Utilizar no cadastramento unívoco de materiais no catálogo corporativo para eliminar duplicidades cadastrais.",
            exampleTitle = "Identificação de Peças por Padronização",
            exampleProblem = "Uma refinaria utilizava 18 marcas e modelos diferentes de manômetros de pressão em seus tanques. O setor de engenharia e suprimentos instituiu uma padronização, estabelecendo apenas 3 modelos oficiais conformes às normas ABNT. Quais são os principais impactos dessa padronização na gestão de estoques?",
            exampleSteps = listOf(
                "Redução drástica no número de itens distintos a serem cadastrados e controlados no almoxarifado.",
                "Aumento da quantidade demandada por modelo padronizado, permitindo maiores lotes de compra e descontos por escala com fabricantes.",
                "Redução do estoque de segurança global necessário para cobrir oscilações de demanda.",
                "Facilidade de substituição e manutenção com mão de obra técnica treinada em modelos homogêneos."
            ),
            exampleAnswer = "Redução do estoque total, ganho de escala nas compras, simplificação do almoxarifado e maior intercambiabilidade operacional.",
            goldTip = "Para a prova: a principal finalidade da padronização de materiais é a REDUÇÃO DE VARIEDADES, gerando economia de escala e menor valor de estoque imobilizado!"
        )
        // =====================================================================
        // MÓDULO 2: GESTÃO DE ESTOQUES
        // =====================================================================
        "tp_log_m2_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m2_a1",
            intro = "A previsão de demanda é o ponto de partida do planejamento de estoques. Antecipar o consumo futuro permite programar ordens de compra e produção com a antecedência necessária. No concurso da Cesgranrio, destacam-se a diferenciação entre demanda dependente e independente, e a aplicação prática do método da Média Móvel Simples e Ponderada para estimar consumos futuros.",
            theory = """
                1. Demanda Independente versus Demanda Dependente:
                • Demanda Independente: o consumo do item não decorre diretamente da programação de fabricação de outro produto da empresa, sofrendo influência direta do mercado externo ou do desgaste operacional não determinístico. É tratada por modelos estatísticos de previsão (ex.: consumo de combustíveis, ferramentas de uso geral).
                • Demanda Dependente: a necessidade do item deriva diretamente do plano de produção ou do cronograma de manutenção de um equipamento pai (sistema MRP - Materials Requirement Planning). Ex.: a quantidade de parafusos para montar 10 motores conhecidos é calculada com exatidão matemática a partir da lista de materiais (BOM).

                2. Fatores que Afetam a Demanda:
                • Tendência: evolução persistente de alta ou de queda no consumo ao longo de sucessivos períodos.
                • Sazonalidade: oscilações previsíveis e repetitivas em períodos fixos (ex.: maior demanda por diesel marítimo em períodos de safra de grãos).
                • Aleatoriedade (Ruído): variações imprevistas decorrentes de eventos climáticos, acidentes ou paradas não programadas.

                3. Métodos Quantitativos de Previsão: Média Móvel:
                • Média Móvel Simples (MMS): calcula a média aritmética do consumo dos últimos 'n' períodos para projetar o próximo período.
                  Fórmula: Previsão_(t+1) = (C_t + C_(t-1) + ... + C_(t-n+1)) / n
                • Média Móvel Ponderada (MMP): atribui pesos diferenciados a cada período, normalmente dando maior peso aos períodos mais recentes para refletir tendências de curto prazo.
                  Fórmula: Previsão_(t+1) = Σ(C_i × Peso_i) / Σ(Pesos)
            """.trimIndent(),
            practicalApplication = """
                No abastecimento de diesel em um terminal logístico, o gestor de suprimentos acompanha o consumo médio mensal para planejar contratos de suprimento com refinarias. Ao utilizar a média móvel de 3 meses com pesos maiores para os meses recentes, ele ajusta as encomendas considerando tendências operacionais imediatas sem ser enganado por picos isolados ocorridos seis meses atrás.
            """.trimIndent(),
            summary = """
                • Demanda independente: estimada por modelos estatísticos de previsão histórica.
                • Demanda dependente: calculada deterministicamente a partir da programação da demanda mãe (MRP / BOM).
                • Média móvel simples: suaviza variações aleatórias calculando a média dos últimos 'n' períodos.
                • Média móvel ponderada: confere maior peso aos dados mais recentes.
            """.trimIndent(),
            formulaName = "Média Móvel Simples e Ponderada de Demanda",
            formulaSyntax = "MMS = (Σ C_i) / n   |   MMP = Σ (C_i × P_i) / Σ P_i",
            formulaTerms = listOf(
                "C_i = Consumo real verificado no período i",
                "n = Número de períodos considerados na média",
                "P_i = Peso atribuído ao consumo do período i"
            ),
            formulaWhenToUse = "Utilizar para prever o consumo de materiais com padrão de demanda estável ou com leve tendência de variação.",
            exampleTitle = "Previsão de Consumo com Média Móvel Ponderada",
            exampleProblem = "O consumo de lubrificante industrial em uma unidade nos últimos 3 meses foi de: Mês 1 = 4.000 litros; Mês 2 = 5.000 litros; Mês 3 = 6.000 litros. Se a gerência utiliza o método da média móvel ponderada com pesos 1 para o mês 1, 2 para o mês 2 e 3 para o mês 3, qual é a demanda prevista para o Mês 4?",
            exampleSteps = listOf(
                "Multiplicar o consumo de cada mês pelo respectivo peso: Mês 1 = 4.000 × 1 = 4.000; Mês 2 = 5.000 × 2 = 10.000; Mês 3 = 6.000 × 3 = 18.000.",
                "Somar os produtos obtidos: 4.000 + 10.000 + 18.000 = 32.000.",
                "Somar os pesos utilizados: 1 + 2 + 3 = 6.",
                "Dividir o numerador pela soma dos pesos: Previsão = 32.000 / 6 = 5.333,33 litros."
            ),
            exampleAnswer = "A demanda prevista para o Mês 4 é de aproximadamente 5.333 litros.",
            goldTip = "Em questões da Cesgranrio de média móvel ponderada, confirme sempre se a soma dos pesos é igual a 1 (quando dados em percentual) ou divida pela soma dos pesos se forem números inteiros!"
        )
        "tp_log_m2_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m2_a2",
            intro = "O Ponto de Pedido (PP) e o Estoque Mínimo (EM ou Estoque de Segurança) são os dois conceitos quantitativos mais recorrentes em provas de logística da Fundação Cesgranrio. O ponto de pedido indica o nível exato de estoque em que uma nova ordem de compra deve ser emitida para que o lote chegue antes que o estoque caia abaixo do nível de segurança.",
            theory = """
                1. Estoque Mínimo (Estoque de Segurança - EM):
                É uma quantidade reserva de material mantida em estoque destinada exclusivamente a absorver incertezas:
                • Atrasos no prazo de entrega do fornecedor (Lead Time).
                • Picos anormais de consumo operacional acima da média prevista durante o período de ressuprimento.
                • O estoque de segurança não deve ser consumido em condições normais de operação; ele protege a empresa contra a ruptura (falta de estoque).

                2. Tempo de Reposição (Lead Time - TR):
                É o intervalo de tempo transcorrido entre a detecção da necessidade de compra e a efetiva disponibilização do material no almoxarifado para uso. Compreende:
                • Tempo de emissão do pedido e cotação interna.
                • Tempo de fabricação e transporte pelo fornecedor.
                • Tempo de recebimento, conferência técnica e liberação no almoxarifado.

                3. Ponto de Pedido (PP ou Nível de Ressuprimento):
                É a quantidade física em estoque que, ao ser atingida pelo consumo regular, dispara a emissão de uma nova solicitação de compra.
                Fórmula Fundamental do Ponto de Pedido:
                  PP = (C × TR) + EM
                Onde:
                • C = Consumo médio do material por unidade de tempo (ex.: unidades por dia).
                • TR = Tempo de Reposição (Lead Time) na mesma unidade de tempo (ex.: dias).
                • (C × TR) = Consumo previsto durante o tempo de ressuprimento.
                • EM = Estoque Mínimo (de segurança).

                4. Estoque Máximo (E_max):
                É a maior quantidade de material que deve ser estocada para evitar custos desnecessários de posse e saturação de espaço físico:
                  E_max = EM + Q_lote (onde Q_lote é a quantidade do lote de compra).

                5. Gráfico Dente de Serra:
                Representação visual do comportamento do estoque ao longo do tempo:
                • Linha descendente inclinada: consumo contínuo do material.
                • Queda até atingir o PP: momento em que o pedido de compra é disparado.
                • Chegada do lote no momento em que o saldo atinge o EM: linha sobe instantaneamente até o Estoque Máximo.
            """.trimIndent(),
            practicalApplication = """
                Um duto de transporte de derivados consome em média 5 filtros de linha por dia. O fornecedor dessas peças leva 20 dias para fabricar e entregar o lote no terminal da Transpetro (Lead Time = 20 dias). A gerência estipulou um estoque de segurança de 30 filtros para cobrir imprevistos. O ponto de pedido será: PP = (5 × 20) + 30 = 100 + 30 = 130 unidades. Quando o saldo de filtros no almoxarifado cair para 130 peças, o comprador deve emitir imediatamente a ordem de compra.
            """.trimIndent(),
            summary = """
                • Ponto de Pedido: PP = (C × TR) + EM. Dispara a reposição do material.
                • Estoque Mínimo (Segurança): reserva que absorve atrasos de fornecedores e aumentos de consumo.
                • Estoque Máximo: E_max = EM + Q_lote.
                • Lead Time (TR): prazo total entre a decisão de compra e a entrada física do material no almoxarifado.
            """.trimIndent(),
            formulaName = "Fórmula do Ponto de Pedido",
            formulaSyntax = "PP = (C × TR) + EM   |   E_max = EM + Q",
            formulaTerms = listOf(
                "PP = Ponto de Pedido (unidades)",
                "C = Consumo médio diário/mensal",
                "TR = Tempo de Reposição / Lead Time (mesma unidade de tempo de C)",
                "EM = Estoque Mínimo ou de Segurança (unidades)",
                "Q = Tamanho do lote de compra ou Lote Econômico"
            ),
            formulaWhenToUse = "Utilizar sempre que a questão solicitar o momento de disparar o pedido de compra ou os limites operacionais de estoque.",
            exampleTitle = "Cálculo do Ponto de Pedido e do Estoque Máximo",
            exampleProblem = "Uma estação compressora consome em média 8 unidades de uma válvula por semana. O lead time do fornecedor é de 3 semanas. Para prevenir riscos operacionais, foi estabelecido um estoque mínimo de 15 válvulas. O lote padrão de compra é de 60 válvulas. Calcule o Ponto de Pedido e o Estoque Máximo.",
            exampleSteps = listOf(
                "Calcular o consumo durante o lead time: C × TR = 8 válvulas/semana × 3 semanas = 24 válvulas.",
                "Calcular o Ponto de Pedido: PP = (C × TR) + EM = 24 + 15 = 39 válvulas.",
                "Calcular o Estoque Máximo: E_max = EM + Q = 15 + 60 = 75 válvulas."
            ),
            exampleAnswer = "Ponto de Pedido = 39 válvulas; Estoque Máximo = 75 válvulas.",
            goldTip = "Cuidado com as unidades de tempo na fórmula do Ponto de Pedido! Se o consumo 'C' for mensal e o Lead Time 'TR' for dado em dias, converta ambos para a mesma base temporal (ex.: divida o consumo mensal por 30 para obter o diário)."
        )
        "tp_log_m2_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m2_a3",
            intro = "A gestão eficiente de estoques exige o equilíbrio entre os custos de pedir (obtenção) e os custos de estocar (manutenção/posse). O modelo do Lote Econômico de Compra (LEC ou EOQ - Economic Order Quantity) resolve matematicamente esse conflito, determinando o tamanho do pedido que minimiza o Custo Total de Estoque. É um dos cálculos mais clássicos da banca Cesgranrio.",
            theory = """
                1. Os Custos Associados aos Estoques:
                • Custo de Pedido / Obtenção (Cp ou S): custos fixos incorridos a cada ordem de compra emitida (despesas administrativas de cotação, envio, comunicação, processamento da nota fiscal e conferência). Quanto maior o tamanho do lote, menos pedidos são feitos no ano e menor é o custo total de pedidos.
                • Custo de Manutenção / Posse / Armazenagem (Ce ou H): custos de manter o produto estocado (custo do capital imobilizado, aluguel de armazém, climatização, seguros, perdas, furtos, obsolescência e mão de obra). Quanto maior o tamanho do lote, maior o estoque médio e maior o custo total de armazenagem.
                • Custo de Falta / Ruptura: custos intangíveis e diretos decorrentes da ausência do item (paradas de linha, multas contratuais, perda de clientes).

                2. Conceito do Lote Econômico de Compra (LEC):
                O LEC é a quantidade de compra na qual o Custo Total de Pedidos iguala exatamente o Custo Total de Armazenagem. No ponto em que essas duas curvas se cruzam, o Custo Total Anual atinge seu valor mínimo.

                3. Fórmula Matemática do LEC (Fórmula de Wilson):
                  LEC = √[ (2 × D × Cp) / Ce ]
                Onde:
                • D = Demanda anual do item (em unidades/ano).
                • Cp = Custo de emissão de um pedido (R$/pedido).
                • Ce = Custo anual de armazenar uma unidade do item (R$/unidade/ano). Frequentemente expresso como uma taxa percentual 'i' sobre o custo unitário do item 'P': Ce = P × i.

                4. Número Ótimo de Pedidos no Ano (N) e Estoque Médio (EMed):
                • N = D / LEC (quantidade de pedidos emitidos ao longo do ano).
                • Estoque Médio Operacional = LEC / 2 (desconsiderando estoque de segurança).
            """.trimIndent(),
            practicalApplication = """
                Em um contrato de aquisição de reagentes químicos para análise de qualidade de diesel marítimo na Transpetro, emitir uma ordem de compra custa R$ 150,00 (processo administrativo), enquanto estocar um galão custa R$ 12,00 por ano. Sabendo que o terminal consome 3.600 galões por ano, o cálculo do LEC indica o tamanho ideal de cada remessa para não sobrecarregar o almoxarifado nem gastar excessivamente com ordens de compra fragmentadas.
            """.trimIndent(),
            summary = """
                • Custo de Pedido cai com lotes maiores; Custo de Armazenagem sobe com lotes maiores.
                • LEC é a quantidade ótima que minimiza o custo total, ocorrendo onde Custo de Pedido = Custo de Estocagem.
                • Fórmula do LEC: √[ (2 × D × Cp) / Ce ].
                • Número anual de pedidos: N = D / LEC. Estoque médio regular: LEC / 2.
            """.trimIndent(),
            formulaName = "Fórmula do Lote Econômico de Compra (LEC / EOQ)",
            formulaSyntax = "LEC = √[ (2 × D × Cp) / Ce ]   |   N = D / LEC",
            formulaTerms = listOf(
                "LEC = Quantidade ótima de cada pedido de compra",
                "D = Demanda anual total (unidades/ano)",
                "Cp = Custo de emissão de cada pedido de compra (R$/pedido)",
                "Ce = Custo unitário de estocagem por unidade ao ano (R$/unid./ano)",
                "N = Frequência ideal de pedidos de compra no ano"
            ),
            formulaWhenToUse = "Utilizar para determinar o lote de compra ideal que minimiza os custos operacionais combinados de pedido e estocagem.",
            exampleTitle = "Cálculo do LEC e do Número de Pedidos",
            exampleProblem = "Uma unidade logística consome anualmente 4.000 unidades de um filtro de óleo. O custo para emitir uma ordem de compra é de R$ 50,00. O custo anual para manter uma unidade do filtro estocada é de R$ 4,00. Qual é o Lote Econômico de Compra (LEC) e quantos pedidos devem ser emitidos por ano?",
            exampleSteps = listOf(
                "Identificar as variáveis: D = 4.000 unidades/ano; Cp = R$ 50,00; Ce = R$ 4,00.",
                "Calcular o numerador da fórmula: 2 × D × Cp = 2 × 4.000 × 50 = 400.000.",
                "Dividir pelo custo de estocagem Ce: 400.000 / 4 = 100.000.",
                "Extrair a raiz quadrada: LEC = √100.000 = 316,22 ≈ 316 unidades por pedido.",
                "Calcular o número de pedidos no ano: N = 4.000 / 316,22 ≈ 12,65 pedidos/ano (cerca de 1 pedido a cada 28 dias)."
            ),
            exampleAnswer = "O LEC é de aproximadamente 316 unidades e a empresa emitirá cerca de 13 pedidos ao longo do ano.",
            goldTip = "Propriedade fundamental do LEC muito cobrada em questões teóricas da Cesgranrio: no ponto do Lote Econômico de Compra, o custo anual de pedidos é exatamente IGUAL ao custo anual de armazenagem!"
        )
        "tp_log_m2_a4" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m2_a4",
            intro = "Giro de Estoque e Cobertura de Estoque são indicadores cruciais da velocidade de rotação e da autonomia dos materiais armazenados. Já a Classificação ABC baseia-se no Teorema de Pareto (regra 80/20) para categorizar os itens em função do seu valor monetário acumulado de consumo. Trata-se de um dos temas prediletos da Cesgranrio para a área de suprimentos e almoxarifado.",
            theory = """
                1. Giro de Estoque (Rotação de Estoque):
                Mede quantas vezes o estoque de um material é renovado (consumido e reposto) ao longo de um determinado período (normalmente 1 ano).
                Fórmula:
                  Giro de Estoque = Consumo Anual / Estoque Médio
                (ambos expressos na mesma base: ou ambos em unidades físicas, ou ambos em valor monetário a preço de custo).
                • Um giro alto indica estoque dinâmico, menor capital parado e maior eficiência operacional.
                • Um giro baixo indica lentidão, risco de obsolescência e recursos excessivamente imobilizados.

                2. Cobertura de Estoque (Taxa de Cobertura / Antigiro):
                Indica o tempo (em dias, semanas ou meses) que o estoque disponível é capaz de atender à demanda futura média sem necessidade de novas compras.
                Fórmula:
                  Cobertura (em dias) = (Estoque Médio / Consumo Anual) × 360 dias = 360 / Giro
                  Ou: Cobertura (em meses) = 12 / Giro

                3. Classificação ABC (Curva de Pareto):
                Hierarquiza os itens de estoque segundo o seu valor financeiro de consumo acumulado no período:
                • Classe A (Alta Relevância Financeira): cerca de 10% a 20% da quantidade total de itens estocados, respondendo por aproximadamente 70% a 80% do valor monetário total movimentado. Exigem controle rigoroso, inventários frequentes, previsões apuradas e pedidos fracionados.
                • Classe B (Média Relevância): cerca de 20% a 30% dos itens, respondendo por aproximadamente 15% a 20% do valor monetário total. Controle intermediário.
                • Classe C (Baixa Relevância Financeira): cerca de 50% a 70% dos itens do almoxarifado, respondendo por apenas 5% a 10% do valor financeiro total. Permitem controles simplificados, pedidos maiores e estoques de segurança mais folgados.
            """.trimIndent(),
            practicalApplication = """
                No almoxarifado central de um terminal marítimo, tubos de ligas nobres e atuadores de válvulas automatizadas representam poucos itens na prateleira (Classe A), mas consomem a maior fatia do orçamento; recebem conferências diárias e negociações diretas. Já arruelas, porcas e vedações de baixo custo correspondem a centenas de itens diferentes (Classe C), sendo comprados em grandes quantidades para evitar custos administrativos de requisições repetidas.
            """.trimIndent(),
            summary = """
                • Giro de Estoque = Consumo / Estoque Médio. Quanto maior o giro, mais eficiente é a gestão.
                • Cobertura de Estoque = Estoque Médio / Consumo Médio Diário (ou 360 / Giro). Mede a autonomia em dias.
                • Curva ABC: Classe A (poucos itens, muito valor ~80%), Classe B (intermediário ~15%), Classe C (muitos itens, pouco valor ~5%).
            """.trimIndent(),
            formulaName = "Fórmulas de Giro, Cobertura e Curva ABC",
            formulaSyntax = "Giro = Consumo / Estoque_Médio   |   Cobertura = 360 / Giro   |   % Valor = (V_item / V_total) × 100",
            formulaTerms = listOf(
                "Giro = Número de vezes que o estoque girou no ano",
                "Cobertura = Autonomia de estoque em dias",
                "Consumo e Estoque Médio = Devem estar na mesma unidade de medida (R$ ou unidades)",
                "Classe A = ~80% do valor | Classe B = ~15% do valor | Classe C = ~5% do valor"
            ),
            formulaWhenToUse = "Utilizar para mensurar a rotação patrimonial do estoque e priorizar controles gerenciais na Curva ABC.",
            exampleTitle = "Cálculo do Giro e Cobertura de um Terminal",
            exampleProblem = "Um terminal de derivados possui um estoque médio de peças avaliado em R$ 1.500.000,00. O consumo anual dessas peças foi de R$ 9.000.000,00. Calcule o giro de estoque anual e a cobertura média de estoque em dias (considere o ano comercial com 360 dias).",
            exampleSteps = listOf(
                "Calcular o Giro de Estoque: Giro = Consumo Anual / Estoque Médio = 9.000.000 / 1.500.000 = 6 vezes ao ano.",
                "Calcular a Cobertura em dias: Cobertura = 360 dias / Giro = 360 / 6 = 60 dias.",
                "Alternativamente: Cobertura = (1.500.000 / 9.000.000) × 360 = (1/6) × 360 = 60 dias."
            ),
            exampleAnswer = "O giro foi de 6 vezes ao ano e a cobertura de estoque é de 60 dias de consumo.",
            goldTip = "Em questões de Giro e Cobertura: Giro alto significa estoque que roda rápido e, portanto, POUCOS DIAS de cobertura. Giro baixo significa estoque parado e MUITOS DIAS de cobertura."
        )
        "tp_log_m2_a5" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m2_a5",
            intro = "A saída física e financeira de materiais no almoxarifado pode ser avaliada por diferentes critérios de valoração: PEPS (FIFO), UEPS (LIFO), Custo Médio Ponderado Móvel e FEFO. Além disso, a conferência de saldos exige rotinas estruturadas de inventário físico (geral versus rotativo). O concurso da Cesgranrio exige pleno domínio das regras tributárias brasileiras de aceitação do PEPS e Custo Médio e a proibição do UEPS pela legislação do IR.",
            theory = """
                1. Critérios de Valoração e Saída de Materiais:
                • PEPS / FIFO (Primeiro que Entra, Primeiro que Sai): as saídas são valoradas pelo custo das entradas mais antigas. Em períodos de inflação, resulta em maior valor de estoque final (itens mais caros ficam no armazém) e menor Custo das Mercadorias Vendidas (CMV), elevando o lucro contábil. É aceito pela legislação fiscal brasileira e pelas normas contábeis internacionais (CPC 16 / IAS 2).
                • UEPS / LIFO (Último que Entra, Primeiro que Sai): as saídas são valoradas pelo custo das entradas mais recentes. Em períodos inflacionários, gera maior CMV e reduz o lucro tributável. É EXPRESSAMENTE VEDADO pela legislação fiscal brasileira (RIR) e pelas normas contábeis do CPC/IFRS.
                • Custo Médio Ponderado Móvel: a cada nova entrada de mercadorias com preço diferente, recalcula-se o custo médio unitário: Custo Médio = (Valor Total do Estoque) / (Quantidade Total). É o método mais praticado no Brasil e amplamente aceito pelo fisco.
                • FEFO (First Expired, First Out - Primeiro que Vence, Primeiro que Sai): critério estritamente físico de gestão de armazenagem para produtos perecíveis (reagentes, tintas, borrachas). O lote com data de validade mais próxima deve ser expedido primeiro, independentemente da data de entrada no armazém.

                2. Modalidades de Inventário Físico:
                • Inventário Geral (Periódico): paralisação das atividades do almoxarifado ao final do exercício contábil para contagem de 100% dos itens existentes. Alto custo operacional e risco de descontinuidade do atendimento.
                • Inventário Rotativo (Cíclico): contagem contínua de grupos de itens ao longo de todo o ano, sem necessidade de paralisar o almoxarifado. A periodicidade de contagem é definida pela Curva ABC (itens Classe A contados mensalmente, Classe B trimestralmente e Classe C anualmente). Identifica causas de divergências mais rapidamente e mantém a acuracidade elevada o ano todo.
            """.trimIndent(),
            practicalApplication = """
                No almoxarifado de insumos químicos e reagentes de laboratório da Transpetro, a equipe segue o critério operacional FEFO: ao receber um novo lote de solvente de teste, os frascos com validade mais curta são posicionados na frente da prateleira para serem entregues prioritariamente aos técnicos, evitando descarte de materiais vencidos. Na contabilidade, as baixas seguem o Custo Médio Móvel registrado no ERP corporativo.
            """.trimIndent(),
            summary = """
                • PEPS (FIFO): baixa pelo custo mais antigo. Aceito pela lei fiscal. Gera maior valor de estoque na inflação.
                • UEPS (LIFO): baixa pelo custo mais recente. PROIBIDO pela legislação tributária brasileira e pelo CPC 16.
                • Custo Médio Móvel: recalcula o preço unitário a cada compra. É a regra geral nas empresas brasileiras.
                • FEFO: critério operacional de prioridade pelo prazo de validade mais próximo.
                • Inventário Rotativo: contagens parciais contínuas ao longo do ano sem paralisar a operação; preferível ao inventário geral.
            """.trimIndent(),
            formulaName = "Fórmula do Custo Médio Ponderado Móvel",
            formulaSyntax = "Custo_Médio_Novo = (Valor_Anterior + Valor_Nova_Entrada) / (Qtd_Anterior + Qtd_Nova_Entrada)",
            formulaTerms = listOf(
                "Valor_Anterior = Saldo financeiro em estoque antes da compra",
                "Valor_Nova_Entrada = Quantidade adquirida × Preço unitário da nova nota fiscal",
                "Qtd_Total = Soma das unidades físicas anteriores com as recém-adquiridas"
            ),
            formulaWhenToUse = "Utilizar para recalcular o custo unitário de saída do material a cada nova aquisição com preço distinto.",
            exampleTitle = "Valoração de Estoque por Custo Médio",
            exampleProblem = "Um almoxarifado possuía 100 unidades de uma graxa especial a R$ 20,00 cada (Total = R$ 2.000,00). Comprou mais 200 unidades a R$ 26,00 cada. Em seguida, consumiu 150 unidades. Qual é o custo da saída pelo método do Custo Médio Ponderado Móvel e qual é o saldo final do estoque?",
            exampleSteps = listOf(
                "Calcular o valor total após a nova entrada: Saldo anterior (R$ 2.000,00) + Nova compra (200 × 26 = R$ 5.200,00) = R$ 7.200,00.",
                "Calcular a quantidade física total: 100 + 200 = 300 unidades.",
                "Calcular o Custo Médio Unitário: 7.200,00 / 300 = R$ 24,00 por unidade.",
                "Calcular o custo da saída de 150 unidades: 150 × 24,00 = R$ 3.600,00.",
                "Calcular o saldo final remanescente: 150 unidades restantes × 24,00 = R$ 3.600,00."
            ),
            exampleAnswer = "O custo da saída foi de R$ 3.600,00 e o estoque final remanescente é de R$ 3.600,00 (150 unidades a R$ 24,00 cada).",
            goldTip = "Pegadinha clássica da Cesgranrio: afirmar que o método UEPS é amplamente aceito pela Receita Federal para reduzir impostos. Lembre-se: o UEPS é terminantemente PROIBIDO pelo Regulamento do Imposto de Renda no Brasil!"
        )
        // =====================================================================
        // MÓDULO 3: GESTÃO DE COMPRAS
        // =====================================================================
        "tp_log_m3_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m3_a1",
            intro = "O processo de compras compreende o ciclo de atividades que transforma uma requisição interna de materiais ou serviços em um contrato ou ordem de fornecimento. Para a Cesgranrio e a Transpetro, os pontos focais são a segregação de funções, a pesquisa criteriosa de mercado para estimativa de preços e a correta especificação do objeto por requisitos de desempenho.",
            theory = """
                1. O Ciclo Operacional de Compras:
                Compreende etapas sequenciais bem definidas:
                • Emissão e aprovação da Requisição de Materiais/Serviços (RM/RC) pelo setor usuário.
                • Verificação de saldo em estoque (para evitar comprar o que já existe armazenado).
                • Pesquisa de mercado e cotação de preços com fornecedores qualificados.
                • Análise técnica e comercial das propostas (menor preço, prazo de entrega, garantias).
                • Emissão da Ordem de Compra (Autorização de Fornecimento / Contrato).
                • Diligenciamento (follow-up) do cumprimento de prazos.
                • Recebimento, conferência física e documental no almoxarifado.
                • Liquidação e pagamento pelo setor financeiro.

                2. Segregação de Funções no Ciclo de Suprimentos:
                Princípio fundamental de controle interno e conformidade (compliance) em empresas estatais:
                • Quem requisita não compra.
                • Quem compra não recebe a mercadoria no almoxarifado.
                • Quem recebe e atesta a nota não autoriza o pagamento financeiro.
                Essa separação de atribuições impede fraudes, desvios de conduta e conflitos de interesses.

                3. Pesquisa de Preços de Mercado e Cotação:
                Para subsidiar a compra e comprovar a vantajosidade econômica perante órgãos de controle (TCU, CGU):
                • Cotações diretas com fornecedores do ramo.
                • Preços praticados em outras estatais e contratações públicas similares (Painel de Preços).
                • Mídias especializadas, tabelas de referência e notas fiscais eletrônicas recentes.
            """.trimIndent(),
            practicalApplication = """
                Quando uma gerência de manutenção de dutos da Transpetro precisa de juntas de expansão metálicas, ela abre uma Requisição de Compra detalhando o desempenho exigido sob a norma ASME. O setor de suprimentos faz cotação de mercado sem interferência do requisitante na escolha comercial, garantindo lisura e transparência fiscal perante a Lei das Estatais.
            """.trimIndent(),
            summary = """
                • Ciclo de compras: da requisição interna à liquidação financeira e entrega física.
                • Segregação de funções: requisitar, comprar, receber e pagar devem ser executados por agentes distintos para assegurar integridade.
                • Pesquisa de mercado: balizamento de preços obrigatório para atestar economicidade frente ao TCU/CGU.
            """.trimIndent(),
            formulaName = "Métrica de Eficiência do Ciclo de Compras (Lead Time de Compras)",
            formulaSyntax = "Lead_Time_Compras = Data_Entrega_Efetiva - Data_Abertura_Requisição",
            formulaTerms = listOf(
                "Data_Abertura_Requisição = Momento em que a área demandante submete o pedido formal",
                "Data_Entrega_Efetiva = Momento em que o material é conferido e liberado no almoxarifado"
            ),
            formulaWhenToUse = "Utilizar para mensurar a eficiência e agilidade dos processos de suprimento corporativo.",
            exampleTitle = "Análise de Cotações com Critério Global",
            exampleProblem = "Uma compra corporativa recebeu duas propostas para fornecimento de 100 válvulas: Fornecedor A cobra R$ 200,00 por válvula com frete CIF incluso e prazo de 10 dias. Fornecedor B cobra R$ 180,00 por válvula com frete FOB (frete pago pela empresa no valor de R$ 3.000,00) e prazo de 20 dias. Qual proposta é economicamente mais vantajosa?",
            exampleSteps = listOf(
                "Calcular o custo total da Proposta A: 100 válvulas × R$ 200,00 = R$ 20.000,00 (frete já incluso).",
                "Calcular o custo total da Proposta B: (100 válvulas × R$ 180,00) + R$ 3.000,00 de frete = 18.000 + 3.000 = R$ 21.000,00.",
                "Comparar os custos globais: R$ 20.000,00 (Proposta A) < R$ 21.000,00 (Proposta B)."
            ),
            exampleAnswer = "A Proposta A é mais econômica em R$ 1.000,00 no custo global (Total Cost of Ownership), além de oferecer entrega mais rápida.",
            goldTip = "Em compras públicas e estatais, o critério de menor preço deve considerar o custo total da contratação (incluindo frete, tributos e despesas acessórias) e não apenas o preço unitário cotado na fábrica!"
        )
        "tp_log_m3_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m3_a2",
            intro = "A gestão de fornecedores deixou de ser uma relação puramente transacional de menor preço imediato para se tornar um processo estratégico de homologação, desenvolvimento e monitoramento de desempenho. A Cesgranrio cobra com frequência os critérios de avaliação (OTIF - On-Time In-Full, índice de refugo, conformidade documental) e as práticas de diligenciamento de fornecedores.",
            theory = """
                1. Homologação e Qualificação de Fornecedores:
                Processo prévio pelo qual a empresa avalia a aptidão técnica, jurídica, fiscal, econômico-financeira e ambiental de potenciais parceiros antes da contratação:
                • Capacidade técnica e operacional: certificações de qualidade (ISO 9001, ISO 14001, ISO 45001), histórico de fornecimento e parque fabril.
                • Regularidade fiscal e trabalhista: certidões negativas de débito e cumprimento de normas de segurança.
                • Idoneidade e integridade (Compliance): checagem em listas de sanções públicas (CEIS/CNEP) e prevenção a corrupção.

                2. Avaliação de Desempenho de Fornecedores (IDF - Índice de Desempenho):
                Acompanhamento contínuo dos fornecedores contratados com base em indicadores objetivos:
                • Pontualidade de Entrega (On-Time): percentual de pedidos entregues dentro do prazo acordado.
                • Qualidade Técnica (Conformidade): percentual de lotes aceitos sem não conformidades ou devoluções.
                • Quantidade Correta (In-Full): entrega do volume integral sem faltas parciais de itens.
                • Indicador OTIF (On-Time, In-Full): mensura o percentual de entregas que foram pontuais E completas simultaneamente:
                  OTIF (%) = (Nº de Entregas no Prazo e Completas / Nº Total de Entregas) × 100%.

                3. Diligenciamento (Follow-up de Fornecedores):
                Atividade proativa de acompanhamento do pedido desde a emissão da ordem de compra até a entrega final:
                • Acompanhamento do cronograma de fabricação na planta do fornecedor.
                • Antecipação de potenciais gargalos em matérias-primas ou transportes.
                • Evita que o almoxarifado só descubra o atraso no dia previsto da entrega.
            """.trimIndent(),
            practicalApplication = """
                Para a construção ou manutenção de navios e rebocadores operados pela Transpetro, chapas de aço naval e motores propulsores exigem fornecedores rigorosamente homologados por sociedades classificadoras navais internacionais (DNV, Lloyd's). O setor de diligenciamento realiza visitas periódicas aos estaleiros para inspecionar testes metalúrgicos e garantir que as entregas não sofram atrasos que comprometam a entrada em operação dos navios.
            """.trimIndent(),
            summary = """
                • Homologação: qualificação prévia em aspectos técnicos, fiscais, ambientais e de integridade (compliance).
                • Indicador OTIF: mede a excelência da entrega (entregas no prazo E com quantidade e qualidade completas).
                • Diligenciamento (follow-up): acompanhamento preventivo do cronograma de fabricação e transporte do fornecedor para evitar surpresas.
            """.trimIndent(),
            formulaName = "Indicador OTIF (On-Time In-Full)",
            formulaSyntax = "OTIF = (Entregas_No_Prazo_e_Completas / Total_Entregas) × 100%",
            formulaTerms = listOf(
                "OTIF = Índice de entregas perfeitas (%)",
                "Entregas_No_Prazo_e_Completas = Remessas que cumpriram simultaneamente prazo e quantidade sem rejeições",
                "Total_Entregas = Total de remessas despachadas pelo fornecedor no período avaliado"
            ),
            formulaWhenToUse = "Utilizar para mensurar a qualidade de serviço e o cumprimento contratual de fornecedores parceiros.",
            exampleTitle = "Cálculo do OTIF de um Fornecedor de Válvulas",
            exampleProblem = "No último trimestre, um fornecedor realizou 80 entregas de materiais. Desse total: 72 entregas foram feitas pontualmente no prazo; 70 entregas continham a quantidade completa e sem defeitos; e 64 entregas atenderam simultaneamente ao prazo e ao pedido completo. Qual é o índice OTIF desse fornecedor?",
            exampleSteps = listOf(
                "Identificar o total de remessas: 80.",
                "Identificar o número de entregas que foram simultaneamente pontuais E completas (On-Time E In-Full): 64.",
                "Aplicar a fórmula do OTIF: (64 / 80) × 100%.",
                "Calcular: 64 / 80 = 0,80 = 80%."
            ),
            exampleAnswer = "O índice OTIF do fornecedor foi de 80%.",
            goldTip = "Cuidado em questões da Cesgranrio: o OTIF não é a média aritmética entre o índice de pontualidade e o índice de qualidade! Ele exige a ocorrência SIMULTÂNEA de ambos os requisitos na mesma remessa."
        )
        "tp_log_m3_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m3_a3",
            intro = "A estratégia de compras envolve decisões sobre estrutura organizacional (compras centralizadas versus descentralizadas), técnicas de negociação comercial e modelos de sincronização e enxugamento de estoques como a filosofia Just-in-Time (JIT). Questões da Cesgranrio costumam confrontar as vantagens econômicas das compras centralizadas com a agilidade das descentralizadas.",
            theory = """
                1. Compras Centralizadas versus Descentralizadas:
                • Compras Centralizadas: uma única unidade ou gerência corporativa de suprimentos executa as compras de toda a empresa.
                  - Vantagens: ganhos de escala (maiores volumes geram maiores descontos de preço), padronização técnica de itens, maior controle sobre contratos e fornecedores, e especialização da equipe de compradores.
                  - Desvantagens: maior lentidão no atendimento a demandas urgentes de bases distantes e risco de distanciamento das particularidades locais.
                • Compras Descentralizadas: cada unidade operacional ou filial possui autonomia para realizar suas próprias aquisições.
                  - Vantagens: rapidez e agilidade para compras emergenciais e valorização do comércio local.
                  - Desvantagens: perda de poder de barganha por compras fragmentadas, risco de despadronização e menor controle de conformidade.
                • Modelo Híbrido: centralizam-se compras estratégicas e de grande valor (Classe A) e descentralizam-se pequenas compras de pronto pagamento (pequeno valor/suprimento de fundos).

                2. Negociação em Compras Corporativas:
                • Abordagem Ganha-Ganha (Negociação Integrativa): busca criar valor conjunto com o fornecedor, focando em relacionamentos de longo prazo, qualidade e redução mútua de custos operacionais.
                • Abordagem Ganha-Perde (Negociação Distributiva): foco exclusivo no preço imediato, comum em compras pontuais de commodities sem relação de longo prazo.

                3. Filosofia Just-in-Time (JIT) em Suprimentos:
                • Produzir e comprar apenas o necessário, no momento exato em que for consumido, nas quantidades estritas demandadas.
                • Meta ideal: 'estoque zero', eliminação de desperdícios e estoques em trânsito sincronizados.
                • Exige fornecedores parceiros altamente confiáveis, com zero defeitos, proximidade geográfica ou transporte veloz, e entregas frequentes em lotes pequenos.
            """.trimIndent(),
            practicalApplication = """
                A Transpetro adota um modelo predominantemente centralizado para aquisição de grandes contratos de óleo combustível, tintas navais e sobressalentes críticos de dutos, obtendo milhões de reais em descontos por volume com grandes fabricantes mundiais. Ao mesmo tempo, mantém cartões corporativos descentralizados para que chefes de estações de bombeamento remotas adquiram reparos hidráulicos urgentes de baixo valor sem paralisar o transporte.
            """.trimIndent(),
            summary = """
                • Compras Centralizadas: geram economia de escala, padronização e maior controle de compliance.
                • Compras Descentralizadas: oferecem velocidade e resposta rápida a emergências locais.
                • Just-in-Time (JIT): compras e entregas sincronizadas com o consumo; busca estoque zero e exige altíssima confiabilidade dos fornecedores.
            """.trimIndent(),
            formulaName = "Trade-off Estrutural de Compras",
            formulaSyntax = "Economia_Escala (Centralizado) vs. Agilidade_Local (Descentralizado)",
            formulaTerms = listOf(
                "Centralização = Poder de barganha, preços unitários menores e padronização corporativa",
                "Descentralização = Redução de lead time para atendimento a necessidades urgentes nas pontas"
            ),
            formulaWhenToUse = "Utilizar para definir a alocação de responsabilidades e limites de competência decisória na área de suprimentos.",
            exampleTitle = "Comparação de Custos por Ganho de Escala",
            exampleProblem = "Se 5 terminais comprarem independentemente 200 unidades de um material a R$ 100,00 cada, o gasto total será de R$ 100.000,00. Centralizando a compra em lote único de 1.000 unidades, o fabricante concede 15% de desconto no preço unitário. Qual é a economia gerada pela compra centralizada?",
            exampleSteps = listOf(
                "Gasto descentralizado: 1.000 unidades × R$ 100,00 = R$ 100.000,00.",
                "Preço unitário com desconto centralizado: R$ 100,00 × (1 - 0,15) = R$ 85,00.",
                "Gasto centralizado: 1.000 unidades × R$ 85,00 = R$ 85.000,00.",
                "Economia de escala obtida: 100.000 - 85.000 = R$ 15.000,00."
            ),
            exampleAnswer = "A compra centralizada gerou uma economia líquida de R$ 15.000,00 (15%).",
            goldTip = "Em questões da Cesgranrio sobre Just-in-Time, lembre-se: o JIT NÃO elimina a necessidade de controle de qualidade; pelo contrário, ele exige qualidade assegurada na fonte ('defeito zero'), pois sem estoque de segurança qualquer peça com defeito paralisa a operação!"
        )
        // =====================================================================
        // MÓDULO 4: ARMAZENAGEM E LAYOUT
        // =====================================================================
        "tp_log_m4_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m4_a1",
            intro = "O recebimento de materiais é a porta de entrada física e contábil do almoxarifado. É a fase em que a empresa verifica se o que foi entregue pelo fornecedor corresponde exatamente ao que foi pactuado no pedido de compra. No concurso da Cesgranrio para a Transpetro, as questões cobram com frequência as três fases do recebimento: conferência documental, quantitativa e qualitativa, além da emissão do termo de aceitação ou recusa.",
            theory = """
                1. As Etapas do Recebimento de Materiais:
                • Entrada do Veículo e Triagem Inicial: identificação do transportador, checagem da nota fiscal e conferência preliminar da integridade externa das embalagens e lacres.
                • Conferência Documental (Fiscal): confrontação da Nota Fiscal (DANFE) com a Ordem de Compra / Contrato. Verificam-se CNPJ, dados da empresa, descrição dos itens, códigos fiscais (NCM/CFOP), alíquotas de tributos, preços unitários e condições de pagamento. Divergências documentais impedem a entrada da mercadoria.
                • Conferência Quantitativa: contagem física dos itens recebidos. Pode ser feita por contagem manual peça por peça, pesagem, cubagem ou amostragem estatística. Prática recomendada: 'Conferência Cega' (o conferente recebe uma listagem sem as quantidades para contar sem viés prévio).
                • Conferência Qualitativa (Inspeção Técnica): verificação das especificações técnicas, tolerâncias dimensionais, testes laboratoriais de pureza química, conformidade de certificados de matéria-prima e ensaios de pressão.
                • Regularização e Aceitação: emissão do Termo de Recebimento Definitivo (atesto), entrada contábil do material no estoque do ERP e liberação para o setor financeiro efetuar o pagamento.

                2. Tratamento de Inconformidades e Recusa:
                • Devolução total: em caso de vício insanável, produto totalmente incorreto ou nota fiscal inidônea.
                • Recebimento condicional / com ressalva: aceite parcial quando faltarem poucas unidades de um lote grande, lavrando-se ocorrência no conhecimento de transporte e na nota.
            """.trimIndent(),
            practicalApplication = """
                Ao receber um lote de válvulas esferas industriais para oleodutos na Transpetro, o conferente de almoxarifado realiza a contagem física cega das caixas (conferência quantitativa). Em seguida, um engenheiro ou técnico de inspeção analisa os certificados de corrida do aço e realiza ensaios hidrostáticos de estanqueidade (conferência qualitativa). Apenas após esse duplo aval a nota fiscal é atestada para pagamento.
            """.trimIndent(),
            summary = """
                • Conferência Documental: confronta Nota Fiscal com o Pedido de Compra (preço, CNPJ, tributos).
                • Conferência Quantitativa: contagem física (idealmente por conferência cega).
                • Conferência Qualitativa: inspeção técnica de materiais, certificados e ensaios de laboratório.
                • Aceitação definitiva: gera o atesto na NF e alimenta o saldo no sistema ERP.
            """.trimIndent(),
            formulaName = "Fluxo Sequencial do Recebimento",
            formulaSyntax = "Conferência Documental → Quantitativa (Cega) → Qualitativa (Técnica) → Atesto/Regularização",
            formulaTerms = listOf(
                "Conferência Cega = Contagem física sem conhecimento prévio da quantidade da NF",
                "Atesto = Declaração formal de conformidade para liquidação e pagamento da despesa"
            ),
            formulaWhenToUse = "Utilizar para garantir a conformidade física e legal dos bens incorporados ao patrimônio da empresa.",
            exampleTitle = "Processo de Conferência Cega em Almoxarifado",
            exampleProblem = "Uma ordem de compra previa a entrega de 500 parafusos estruturais. O fornecedor entregou 5 caixas lacradas. Por que a gerência de suprimentos determina que o conferente realize uma 'conferência cega' em vez de entregar a cópia da Nota Fiscal com as quantidades já preenchidas?",
            exampleSteps = listOf(
                "Na conferência aberta (com a NF visível), o conferente pode ser induzido a assumir que há 500 parafusos sem contar criteriosamente todas as unidades.",
                "Na conferência cega, o operador recebe apenas a lista dos itens sem as quantidades, sendo obrigado a contar efetivamente item a item.",
                "O resultado da contagem física é inserido no sistema e confrontado automaticamente com a nota fiscal emitida pelo fornecedor.",
                "Se houver divergência (ex.: 480 ou 520 unidades), o sistema aponta a inconsistência para imediata recusa ou ajuste de faturamento."
            ),
            exampleAnswer = "Para eliminar a acomodação e o viés do operador, assegurando contagem fidedigna e evitando desvios ou aceitação involuntária de entregas a menor.",
            goldTip = "Termo muito cobrado pela Cesgranrio: CONFERÊNCIA CEGA (ou contagem cega) é o método no qual o conferente desconhece a quantidade da nota fiscal, garantindo imparcialidade e rigor na apuração física!"
        )
        "tp_log_m4_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m4_a2",
            intro = "O endereçamento de estoques e os processos de estocagem, picking (separação de pedidos) e expedição determinam a produtividade operacional de um armazém. No concurso da Transpetro, a Cesgranrio foca na comparação entre endereçamento fixo e aleatório (caótico), nos métodos de picking (por pedido, por lote, por zona) e nos fluxos de embalagem e expedição.",
            theory = """
                1. Sistemas de Endereçamento no Almoxarifado:
                Cada posição de estocagem recebe um código estruturado (ex.: Rua-Módulo-Nível-Vão).
                • Endereçamento Fixo (Dedicado): cada material possui um local exclusivo e pré-determinado de guarda.
                  - Vantagem: facilidade de localização visual pelos operadores que conhecem o armazém.
                  - Desvantagem: baixa taxa de ocupação espacial; se o item estiver com estoque zerado, o espaço fica ocioso e não pode ser ocupado por outro material.
                • Endereçamento Caótico (Aleatório / Livre): os materiais são guardados em qualquer posição vaga disponível no momento do recebimento, sendo a localização registrada no sistema WMS (Warehouse Management System).
                  - Vantagem: aproveitamento máximo do espaço volumétrico do armazém (taxas de ocupação próximas de 100%).
                  - Desvantagem: dependência absoluta do sistema informatizado WMS e de leitura por código de barras ou RFID.

                2. Processo de Picking (Separação de Pedidos):
                É a atividade de coletar materiais nas prateleiras para atender a requisições internas de manutenção ou clientes:
                • Picking Discreto (por Pedido): um operador percorre o armazém coletando todos os itens de uma única requisição por vez.
                • Picking por Lote (Batch Picking): o operador coleta simultaneamente itens para múltiplos pedidos e depois os classifica em uma área de consolidação. Reduz o tempo de deslocamento no galpão.
                • Picking por Zona: o armazém é dividido em zonas e cada operador atua exclusivamente em sua zona, separando os itens daquela área para o pedido.

                3. Expedição:
                Fase final no almoxarifado: conferência final de saída, embalagem de proteção, etiquetagem e emissão do manifesto de carga para transporte.
            """.trimIndent(),
            practicalApplication = """
                Nos galpões de peças de manutenção da Transpetro, o uso de um WMS com endereçamento dinâmico e leitores ópticos permite que itens de alta rotatividade (Classe A) sejam estocados próximos às docas de expedição e nos níveis mais baixos das prateleiras (ergonomia e velocidade), enquanto peças pesadas de baixa rotação ocupam áreas mais distantes, minimizando trajetos e tempos de picking das equipes de campo.
            """.trimIndent(),
            summary = """
                • Endereçamento Fixo: local exclusivo por item; fácil visualmente, mas desperdiça espaço quando o estoque zera.
                • Endereçamento Aleatório/Caótico: guarda em qualquer vaga livre; maximiza ocupação volumétrica com auxílio do WMS.
                • Picking: coleta de materiais nas posições; pode ser discreto (por pedido), por lote ou por zona.
                • Itens de alto giro (Curva A) devem ficar posicionados perto das docas para encurtar deslocamentos.
            """.trimIndent(),
            formulaName = "Eficiência de Aproveitamento Volumétrico",
            formulaSyntax = "Taxa_Ocupação = (Volume_Efetivamente_Ocupado / Volume_Total_Armazenagem) × 100%",
            formulaTerms = listOf(
                "Volume_Efetivamente_Ocupado = Metros cúbicos de carga estocada",
                "Volume_Total_Armazenagem = Capacidade volumétrica útil das estruturas porta-paletes"
            ),
            formulaWhenToUse = "Utilizar para mensurar a saturação física e a otimização de espaço em armazéns.",
            exampleTitle = "Escolha de Política de Endereçamento",
            exampleProblem = "Um novo galpão de suprimentos possui limitação física de área e precisa abrigar grande variedade de sobressalentes com flutuações sazonais de estoque. Qual política de endereçamento deve ser adotada pela gerência logística e por quê?",
            exampleSteps = listOf(
                "Analisar a restrição: espaço físico escasso e alta variedade de itens.",
                "Avaliar o endereçamento fixo: reservaria vagas vazias quando estoques caíssem, gerando ociosidade insustentável.",
                "Avaliar o endereçamento aleatório: aloca qualquer palete na primeira vaga disponível gerenciada por WMS.",
                "Concluir pela política que maximiza o fator de ocupação cúbica."
            ),
            exampleAnswer = "Endereçamento Aleatório (Caótico) com suporte de WMS, pois proporciona o melhor aproveitamento do espaço volumétrico disponível.",
            goldTip = "Em provas da Cesgranrio: associar itens de ALTA ROTATIVIDADE (Classe A da curva ABC) à localização próxima das portas de saída e em alturas ergonômicas (nível do operador) para reduzir tempo de coleta no picking!"
        )
        "tp_log_m4_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m4_a3",
            intro = "A movimentação de materiais, a verticalização de estoques e o projeto de layout de armazéns impactam diretamente a segurança operacional, a velocidade dos fluxos e os custos operacionais. Na prova da Cesgranrio, os pontos fundamentais abrangem a escolha de equipamentos de transporte interno (paleteiras, empilhadeiras, pontes rolantes), os tipos de estruturas de armazenagem (porta-paletes, drive-in, cantilever) e os fluxos de layout em linha reta e em formato de 'U'.",
            theory = """
                1. Equipamentos de Movimentação Interna de Materiais:
                • Manuais: paleteiras manuais para movimentação horizontal de cargas leves em distâncias curtas.
                • Mecanizados / Motorizados: empilhadeiras elétricas ou a combustão (glp/diesel) para movimentação horizontal e verticalização em grandes alturas; carrinhos rebocadores.
                • Fixos / Aéreos: pontes rolantes e pórticos móveis para movimentação de cargas pesadas, bobinas, dutos e motores em oficinas de manutenção; esteiras transportadoras e transportadores de roletes.

                2. Estruturas de Armazenagem e Verticalização:
                A verticalização aproveita o pé-direito do armazém, multiplicando a capacidade estática sem expandir a área construída no solo:
                • Estrutura Porta-Paletes Convencional: acesso direto e seletivo a qualquer palete sem movimentar os vizinhos; ideal para alta variedade de itens.
                • Drive-in / Drive-through: empilhadeira entra na própria estrutura; alta densidade de estocagem para grandes lotes do mesmo produto com baixa seletividade.
                • Cantilever: estrutura em braços suspensos sem colunas frontais; ideal para materiais compridos e tubulares (tubos, eixos, vigas e perfis de dutos).

                3. Projeto de Layout de Armazéns:
                O layout deve promover fluxos contínuos, sem cruzamentos e com distâncias mínimas:
                • Layout em Fluxo Retilíneo (Linha Reta): entrada/recebimento em uma extremidade e expedição na extremidade oposta. Indicado para grandes volumes e áreas alongadas.
                • Layout em Fluxo em Formato de 'U': recebimento e expedição localizados no mesmo lado do edifício. Facilita o compartilhamento de docas, equipamentos de pátio e supervisão, além de permitir fácil expansão dos fundos do armazém.
            """.trimIndent(),
            practicalApplication = """
                Nos pátios e armazéns de tubulações da Transpetro, estruturas do tipo Cantilever e pontes rolantes industriais são mandatórias para armazenar e movimentar tubos de aço de 12 metros de comprimento com total segurança operacional, evitando acidentes com cargas pesadas e danos aos revestimentos anticorrosivos das tubulações.
            """.trimIndent(),
            summary = """
                • Equipamentos: paleteiras (horizontal leve), empilhadeiras (verticalização) e pontes rolantes (cargas indivisíveis e pesadas).
                • Estruturas: Porta-Paletes (seletividade 100%), Drive-in (densidade de lote) e Cantilever (materiais compridos como dutos e perfis).
                • Layout em 'U': docas de recebimento e expedição no mesmo lado, permitindo compartilhamento de docas e supervisão unificada.
            """.trimIndent(),
            formulaName = "Fator de Aproveitamento Volumétrico do Pé-Direito",
            formulaSyntax = "Capacidade_Vertical = Área_Piso × Altura_Útil_Pé_Direito × Fator_Estrutural",
            formulaTerms = listOf(
                "Área_Piso = Área do armazém em m²",
                "Altura_Útil = Altura vertical aproveitável pelas empilhadeiras",
                "Fator_Estrutural = Coeficiente de ocupação útil descontando corredores de manobra"
            ),
            formulaWhenToUse = "Utilizar no dimensionamento da capacidade estática de galpões e seleção de estruturas porta-paletes.",
            exampleTitle = "Seleção de Estrutura para Tubulações Industriais",
            exampleProblem = "Um almoxarifado de base operacional precisa estocar tubos de aço carbono de 6 e 12 metros de comprimento utilizados na manutenção de oleodutos. Qual estrutura de armazenagem é tecnicamente recomendada para essa aplicação?",
            exampleSteps = listOf(
                "Analisar as características da carga: cargas lineares longas, rígidas e de grande comprimento.",
                "Verificar porta-paletes convencional: colunas intermediárias impedem a inserção horizontal de barras longas.",
                "Verificar estrutura Cantilever: possui colunas traseiras centrais e braços em balanço abertos sem restrição frontal.",
                "Concluir pela adequação técnica do Cantilever para materiais longos e tubulares."
            ),
            exampleAnswer = "Estrutura do tipo Cantilever, pois seus braços em balanço livres de colunas frontais permitem apoiar e manusear materiais longos e tubos com segurança.",
            goldTip = "Para guardar tubos, barras de aço ou perfis compridos, a resposta nas provas da Cesgranrio é quase sempre a estrutura do tipo CANTILEVER!"
        )
        // =====================================================================
        // MÓDULO 5: LOGÍSTICA E TRANSPORTES
        // =====================================================================
        "tp_log_m5_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m5_a1",
            intro = "O gerenciamento da cadeia de suprimentos (Supply Chain Management - SCM) integra os fluxos físicos, financeiros e de informação desde o fornecedor inicial até o cliente final. O Efeito Chicote (Bullwhip Effect) é a distorção e amplificação da variação de demanda que ocorre ao longo dessa cadeia. Além disso, a Logística Reversa fecha o ciclo produtivo, tratando o retorno de resíduos, embalagens e produtos pós-consumo.",
            theory = """
                1. Conceito de Supply Chain Management (SCM):
                O SCM vai além da logística interna da empresa, promovendo a integração estratégica e colaborativa entre fornecedores, transportadores, operadores logísticos, centros de distribuição e clientes:
                • Fluxo de Materiais (Físico): sentido fornecedor → cliente (com retorno na logística reversa).
                • Fluxo de Informações: bidirecional (pedidos, previsões, níveis de estoque, rastreamento).
                • Fluxo Financeiro: sentido cliente → fornecedor (pagamentos e liquidações).

                2. O Efeito Chicote (Bullwhip Effect):
                Fenômeno em que pequenas oscilações na demanda do consumidor final geram oscilações cada vez maiores e desproporcionais nos níveis de estoque dos elos anteriores da cadeia (varejo → distribuidor → fábrica → fornecedor de matéria-prima).
                • Causas principais: falta de compartilhamento de dados de demanda em tempo real, compras em grandes lotes, pânico contra escassez e atrasos no tempo de resposta (lead time).
                • Mitigação: integração de sistemas de informação (EDI/APIs), estoques gerenciados pelo fornecedor (VMI - Vendor Managed Inventory) e previsões colaborativas (CPFR).

                3. Logística Reversa (Lei nº 12.305/2010 - Política Nacional de Resíduos Sólidos):
                Área da logística que gerencia o fluxo de materiais do ponto de consumo de volta ao ponto de origem ou destinação ambientalmente adequada:
                • Logística Reversa de Pós-Venda: produtos devolvidos sem uso ou com defeitos comerciais, recolhidos para troca, conserto ou reinspeção.
                • Logística Reversa de Pós-Consumo: bens descartados pelo usuário ao final de sua vida útil (óleos lubrificantes usados, baterias, pneus, tambores metálicos, filtros contaminados). Objetiva reciclagem, reaproveitamento ou descarte seguro, atendendo à responsabilidade compartilhada pelo ciclo de vida dos produtos.
            """.trimIndent(),
            practicalApplication = """
                Na cadeia de óleo e gás da Petrobras/Transpetro, tambores metálicos de lubrificantes, filtros de linha contaminados com hidrocarbonetos e óleos usados são rigorosamente coletados por programas de logística reversa de pós-consumo, sendo encaminhados para empresas de rerrefino ou reciclagem certificadas por órgãos ambientais (IBAMA), garantindo conformidade com a PNRS e as políticas de SMS corporativo.
            """.trimIndent(),
            summary = """
                • SCM: integração de fluxos físicos, informacionais e financeiros entre todos os elos da cadeia.
                • Efeito Chicote: amplificação da oscilação de estoques a montante na cadeia devido à falta de visibilidade da demanda final.
                • Logística Reversa: Pós-Venda (produtos devolvidos por falhas comerciais) vs. Pós-Consumo (bens descartados ao fim da vida útil para reciclagem ou descarte ecológico).
            """.trimIndent(),
            formulaName = "Métrica do Efeito Chicote",
            formulaSyntax = "Razão_Chicote = Variância_Ordens_Compra / Variância_Demanda_Final",
            formulaTerms = listOf(
                "Razão_Chicote > 1 = Presença de efeito chicote (amplificação da volatilidade)",
                "Variância_Ordens_Compra = Dispersão dos pedidos emitidos pelos elos superiores",
                "Variância_Demanda_Final = Dispersão do consumo real do cliente na ponta"
            ),
            formulaWhenToUse = "Utilizar para diagnosticar a distorção na transmissão de sinais de demanda ao longo da cadeia de suprimentos.",
            exampleTitle = "Identificação de Logística Reversa de Pós-Consumo",
            exampleProblem = "Um terminal portuário recolhe semanalmente centenas de baterias industriais exauridas e filtros de óleo usados retirados de motores de rebocadores para encaminhamento a usinas de descontaminação e reciclagem. Essa atividade classifica-se em qual canal de logística reversa?",
            exampleSteps = listOf(
                "Analisar o ciclo de vida do item: os filtros e baterias já cumpriram toda a sua vida útil e foram descartados pelo operador.",
                "Não se trata de troca de produto em garantia ou cancelamento comercial de compra (o que seria pós-venda).",
                "Materiais descartados após o uso integral integram a logística reversa de pós-consumo.",
                "Concluir pelo enquadramento em logística reversa de pós-consumo com foco em sustentabilidade e atendimento à PNRS."
            ),
            exampleAnswer = "Logística Reversa de Pós-Consumo.",
            goldTip = "Em questões de Logística Reversa: se o produto teve sua vida útil encerrada e virou resíduo, é PÓS-CONSUMO. Se o produto foi devolvido por erro de envio, desistência ou defeito imediato na entrega, é PÓS-VENDA!"
        )
        "tp_log_m5_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m5_a2",
            intro = "As características operacionais, técnicas e econômicas dos cinco modais básicos de transporte (Rodoviário, Ferroviário, Aquaviário, Dutoviário e Aéreo) constituem conteúdo central para a Transpetro, uma empresa cuja missão primordial é exatamente a operação dutoviária e o transporte aquaviário de petróleo, gás e derivados. A Cesgranrio cobra a comparação de custos fixos/variáveis, capacidade de carga e velocidade de cada modal.",
            theory = """
                1. Modal Dutoviário (Pipelines):
                Transporte de fluidos (líquidos e gases) por meio de tubulações (oleodutos, gasodutos e polidutos):
                • Características: fluxo contínuo (24 horas por dia, 365 dias por ano), imune a congestionamentos de tráfego e condições climáticas adversas, e baixíssimo índice de perdas e acidentes.
                • Estrutura de Custos: custos fixos altíssimos (investimento maciço na instalação das tubulações, bombas, estações de compressão e direitos de passagem) e custos variáveis operacionais extremamente baixos por tonelada-quilômetro.
                • Limitações: inflexibilidade absoluta de rotas (só entrega onde há tubulação instalada) e restrito a produtos líquidos e gasosos.

                2. Modal Aquaviário (Marítimo de Longo Curso, Cabotagem e Fluvial):
                Transporte por navios petroleiros, gaseiros e barcaças:
                • Características: altíssima capacidade de carga unitária e capacidade de cobrir distâncias globais com alta eficiência energética.
                • Estrutura de Custos: custos fixos moderados/altos (aquisição de embarcações e taxas portuárias) e custos variáveis baixos para grandes lotes a longas distâncias.
                • Limitações: velocidade lenta, dependência de portos e hidrovias adequadas e necessidade de integração com outros modais terrestres.

                3. Modal Ferroviário:
                Transporte por trens e composições em trilhos:
                • Características: indicado para grandes volumes de granéis sólidos e líquidos a médias e longas distâncias.
                • Estrutura de Custos: custos fixos elevados (locomotivas, vagões e malha) e custos variáveis baixos.

                4. Modal Rodoviário:
                Transporte por caminhões em rodovias:
                • Características: alta flexibilidade porta a porta, ampla disponibilidade e rapidez em curtas distâncias.
                • Estrutura de Custos: baixos custos fixos de entrada e altos custos variáveis (combustível, pneus, manutenção, pedágio e mão de obra). Baixa capacidade de carga e alto índice de emissões e acidentes.

                5. Modal Aéreo:
                Transporte por aviões:
                • Características: altíssima velocidade e segurança, indicado para cargas emergenciais, de alto valor agregado ou perecíveis. Custo de frete mais alto de todos os modais.
            """.trimIndent(),
            practicalApplication = """
                A Transpetro opera a espinha dorsal logística da energia no Brasil: utiliza o modal dutoviário para transportar milhões de barris de petróleo cru de terminais marítimos até as refinarias com segurança ambiental e custo operacional mínimo. Para a distribuição entre portos ao longo do litoral brasileiro, opera uma frota de navios no modal aquaviário por cabotagem, unindo alta capacidade de carga a baixos custos de transporte por tonelada transportada.
            """.trimIndent(),
            summary = """
                • Dutoviário: fluxo contínuo, custo fixo elevadíssimo, custo variável muito baixo, inflexível geograficamente, privativo de granéis líquidos/gás.
                • Aquaviário: altíssima capacidade e economia em longas distâncias; velocidade lenta.
                • Rodoviário: flexibilidade porta a porta, baixa capacidade unitária e alto custo variável.
                • Ferroviário: grandes volumes de granéis a médias/longas distâncias com custo intermediário.
                • Aéreo: máxima velocidade e custo mais elevado, para cargas urgentes ou de altíssimo valor unitário.
            """.trimIndent(),
            formulaName = "Comparação da Estrutura de Custos por Modal",
            formulaSyntax = "Custo_Total_Transporte = Custo_Fixo_Infraestrutura + (Custo_Variável_km × Distância)",
            formulaTerms = listOf(
                "Dutoviário: Altíssimo Fixo, Mínimo Variável",
                "Rodoviário: Mínimo Fixo, Altíssimo Variável",
                "Aquaviário e Ferroviário: Fixo Alto, Variável Baixo para grandes volumes"
            ),
            formulaWhenToUse = "Utilizar para selecionar o modal de transporte mais econômico em função da distância e volume de carga.",
            exampleTitle = "Análise Comparativa entre Duto e Caminhão Tanque",
            exampleProblem = "Uma refinaria precisa receber 50.000 m³ de óleo combustível diariamente a partir de um terminal a 80 km de distância. Por que o modal dutoviário é tecnicamente e economicamente superior ao rodoviário para esse cenário operacional?",
            exampleSteps = listOf(
                "Calcular o impacto do modal rodoviário: 50.000 m³ exigiriam cerca de 1.600 viagens de caminhões-tanque de 30 m³ por dia nas rodovias.",
                "Isso acarretaria saturação do trânsito, altíssimo risco de acidentes rodoviários, emissões massivas de carbono e custo elevadíssimo de frete por caminhão.",
                "O modal dutoviário opera de forma subterrânea contínua com bombeamento elétrico, transportando todo o volume sem interrupção de tráfego.",
                "Apesar do alto custo inicial de implantação do duto, o custo variável operacional por metro cúbico transportado é uma fração ínfima do custo rodoviário."
            ),
            exampleAnswer = "O modal dutoviário garante fluxo ininterrupto, menor custo variável operacional por unidade transportada, maior segurança ambiental e elimina o congestionamento rodoviário.",
            goldTip = "Para o concurso da Transpetro: guarde bem que o modal DUTOVIÁRIO possui os maiores custos fixos iniciais e os menores custos operacionais variáveis por volume transportado de todos os modais terrestres!"
        )
        "tp_log_m5_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m5_a3",
            intro = "A combinação eficiente de modais é essencial para a competitividade logística nacional. A Cesgranrio frequentemente exige do candidato a distinção conceitual e jurídica entre Intermodalidade e Multimodalidade, o papel legal do Operador de Transporte Multimodal (OTM - Lei nº 9.611/1998) e a utilização de técnicas de consolidação como Cross-Docking e Milk Run.",
            theory = """
                1. Intermodalidade versus Multimodalidade:
                Ambos utilizam dois ou mais modais de transporte para movimentar uma carga da origem ao destino, mas diferem juridicamente:
                • Intermodalidade: utilização de múltiplos modais em que cada trecho é contratado de forma independente pelo embarcador com transportadores distintos, havendo múltiplos contratos e divisão de responsabilidades jurídicas entre as partes.
                • Multimodalidade: utilização de múltiplos modais sob a responsabilidade jurídica de um ÚNICO operador — o Operador de Transporte Multimodal (OTM). A operação é acobertada por um documento fiscal e contratual único: o Conhecimento de Transporte Multimodal de Cargas (CTMC). O OTM responde por toda a carga perante o cliente da origem ao destino final.

                2. O Operador de Transporte Multimodal (OTM - Lei nº 9.611/1998):
                • Pessoa jurídica habilitada perante a Agência Nacional de Transportes Terrestres (ANTT).
                • Assume a responsabilidade integral pela execução dos serviços, inclusive por perdas, avarias ou atrasos ocorridos em qualquer dos trechos (mesmo que subcontrate terceiros).

                3. Estratégias de Consolidação e Distribuição:
                • Cross-Docking: sistema de distribuição em que as mercadorias recebidas nas docas de entrada são imediatamente descarregadas, conferidas, triadas e transferidas diretamente para os veículos nas docas de expedição, sem estocagem intermediária. Reduz custos de armazenagem e tempo de ciclo.
                • Milk Run (Coleta Programada): rota circular em que um único veículo de coleta passa pelas plantas de múltiplos fornecedores em horários fixos e pré-programados, recolhendo os materiais em lotes fracionados para abastecer a planta consumidora central.
            """.trimIndent(),
            practicalApplication = """
                Na logística de derivados de petróleo, a cabotagem marítima leva o combustível de uma grande refinaria até um porto regional (modal aquaviário). De lá, o produto é descarregado em um terminal e injetado em um poliduto até uma base de distribuição interiorana (modal dutoviário) e, por fim, carregado em caminhões-tanque para entrega aos postos (modal rodoviário). Se todo esse trajeto for contratado sob um documento único com um OTM responsável porta a porta, trata-se de transporte multimodal.
            """.trimIndent(),
            summary = """
                • Intermodalidade: vários modais com contratos e conhecimentos de transporte individuais por trecho.
                • Multimodalidade: vários modais sob contrato ÚNICO e documento fiscal único (CTMC), coordenado pelo OTM.
                • Operador de Transporte Multimodal (OTM): assume a responsabilidade civil total pela carga do início ao fim.
                • Cross-Docking: transbordo direto doca-a-doca sem armazenagem física.
                • Milk Run: rota programada de coleta em múltiplos fornecedores.
            """.trimIndent(),
            formulaName = "Diferença Jurídica: Intermodal vs. Multimodal",
            formulaSyntax = "Intermodal = Múltiplos Contratos   |   Multimodal = CTMC Único sob Gestão do OTM",
            formulaTerms = listOf(
                "CTMC = Conhecimento de Transporte Multimodal de Cargas",
                "OTM = Operador de Transporte Multimodal (responsabilidade integral perante o contratante)"
            ),
            formulaWhenToUse = "Utilizar para diferenciar o enquadramento contratual e a responsabilidade civil em operações logísticas combinadas.",
            exampleTitle = "Identificação de Transporte Multimodal",
            exampleProblem = "Uma empresa contratou uma transportadora para levar uma carga industrial do Rio de Janeiro a Manaus. A carga seguiu de carreta até Santos, de navio porta-contêiner até o porto de Manaus e de caminhão até a fábrica. A transportadora emitiu um único Conhecimento de Transporte Multimodal de Cargas (CTMC) e assumiu a responsabilidade total por todo o trajeto. Essa operação é classificada como:",
            exampleSteps = listOf(
                "Houve o emprego de mais de um modal de transporte (rodoviário e aquaviário marítimo/fluvial).",
                "A contratação foi acobertada por um único documento (CTMC).",
                "Uma única pessoa jurídica (OTM) assumiu a responsabilidade integral pelo transporte porta a porta.",
                "Portanto, trata-se formalmente de Transporte Multimodal."
            ),
            exampleAnswer = "Transporte Multimodal de Cargas.",
            goldTip = "Ponto chave para não errar na Cesgranrio: se houver UM SÓ documento de transporte (CTMC) e uma só empresa assumindo a responsabilidade civil por todo o trajeto com múltiplos modais, a operação é MULTIMODAL!"
        )
        // =====================================================================
        // MÓDULO 6: SUPRIMENTO EM ESTATAIS
        // =====================================================================
        "tp_log_m6_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_log_m6_a1",
            intro = "As contratações e procedimentos de suprimento na Transpetro regem-se especificamente pela Lei nº 13.303/2016 (Lei das Estatais) e por seu Regulamento Interno de Licitações e Contratos (RLIC). Para a Cesgranrio, os instrumentos auxiliares de compras e suprimentos — como o Sistema de Registro de Preços (SRP), a Pré-qualificação Permanente e o Catálogo Eletrônico de Padronização — são cobranças constantes em questões de nível médio.",
            theory = """
                1. Procedimentos Auxiliares das Licitações nas Estatais (Art. 63 da Lei nº 13.303/2016):
                Para dar agilidade às suas aquisições comerciais e operacionais, as empresas estatais utilizam os seguintes procedimentos auxiliares:
                • Pré-qualificação Permanente: procedimento público destinado a certificar previamente que determinados fornecedores atendem às condições de habilitação e/ou que seus bens e produtos satisfazem aos requisitos técnicos e de qualidade estabelecidos pela estatal. Pode ser aberta a qualquer tempo e agiliza licitações futuras.
                • Cadastramento: registro unificado de fornecedores com verificação de idoneidade jurídica, fiscal e técnica.
                • Sistema de Registro de Preços (SRP): procedimento licitatório pelo qual se selecionam fornecedores e se registram preços de bens e serviços de consumo frequente, mediante a formalização de uma Ata de Registro de Preços (ARP).
                  - Não obriga a estatal a contratar todo o quantitativo estimado.
                  - As compras são feitas sob demanda, conforme a necessidade real de caixa e estoque da empresa, durante o prazo de vigência da ata (normalmente de até 1 ano, admitida prorrogação conforme regulamento).
                • Catálogo Eletrônico de Padronização: documento digital obrigatório com a descrição e especificação técnica de bens e serviços comuns, simplificando os editais e impedindo compras de itens fora do padrão corporativo.

                2. Adoção Preferencial do Pregão Eletrônico:
                A Lei nº 13.303/2016 determina que, para aquisição de bens e serviços comuns, deve-se adotar preferencialmente a modalidade Pregão, na forma eletrônica, cujo critério de julgamento é o de menor preço ou maior desconto.
            """.trimIndent(),
            practicalApplication = """
                Para suprir combustíveis e materiais de consumo em dezenas de navios ao longo do ano sem precisar abrir dezenas de licitações isoladas, a Transpetro realiza uma licitação por Sistema de Registro de Preços. Assina-se uma Ata de Registro de Preços com o fornecedor vencedor: à medida que cada navio atraca e necessita de abastecimento, a gerência emite uma ordem de compra contra a ata, garantindo preço padronizado e entrega sem burocracia.
            """.trimIndent(),
            summary = """
                • Procedimentos auxiliares (Art. 63 da Lei 13.303/2016): Pré-qualificação, Cadastramento, Sistema de Registro de Preços (SRP) e Catálogo Eletrônico.
                • Ata de Registro de Preços (ARP): vincula o fornecedor aos preços cotados, mas NÃO obriga a estatal a comprar.
                • Bens e serviços comuns: licitados preferencialmente por pregão eletrônico.
            """.trimIndent(),
            formulaName = "Instrumentos de Eficiência nas Compras das Estatais",
            formulaSyntax = "Sistema de Registro de Preços (SRP) + Pré-qualificação + Pregão Eletrônico",
            formulaTerms = listOf(
                "SRP = Preços registrados em Ata para contratações parceladas sob demanda",
                "Pré-qualificação = Avaliação antecipada de fornecedores e produtos para acelerar licitações",
                "Pregão Eletrônico = Modalidade preferencial para bens e serviços comuns nas estatais"
            ),
            formulaWhenToUse = "Utilizar para justificar e aplicar as rotinas de compras corporativas amparadas pela Lei nº 13.303/2016.",
            exampleTitle = "Aplicação do Sistema de Registro de Preços",
            exampleProblem = "Uma estatal do setor de transporte precisa comprar lubrificantes para uso em seus motores ao longo do ano. O consumo é frequente e contínuo, mas incerto quanto às datas exatas de atracação dos navios. Qual procedimento é o mais adequado para realizar essa contratação sem precisar armazenar todo o volume anual de uma só vez?",
            exampleSteps = listOf(
                "A compra envolve bens padronizados de demanda parcelada e incerta.",
                "A realização de licitação tradicional obrigaria a estatal a receber e pagar todo o lote de imediato ou abrir dezenas de licitações fragmentadas.",
                "O Sistema de Registro de Preços (SRP) permite registrar os preços unitários em Ata e acionar as entregas parceladas conforme a necessidade.",
                "A empresa não fica obrigada a adquirir a totalidade registrada se a demanda cair."
            ),
            exampleAnswer = "Licitação para Sistema de Registro de Preços (SRP), com formalização de Ata de Registro de Preços.",
            goldTip = "Cai sempre na Cesgranrio: a formalização da Ata de Registro de Preços (ARP) NÃO obriga a administração da estatal a contratar o total previsto, gerando flexibilidade e proteção orçamentária!"
        )

        else -> null
    }


    fun getExercises(lessonId: String): List<MathExercise> {
        return when (lessonId) {
            "tp_log_m1_a1" -> exercisesM1A1()
            "tp_log_m1_a2" -> exercisesM1A2()
            "tp_log_m1_a3" -> exercisesM1A3()
            "tp_log_m2_a1" -> exercisesM2A1()
            "tp_log_m2_a2" -> exercisesM2A2()
            "tp_log_m2_a3" -> exercisesM2A3()
            "tp_log_m2_a4" -> exercisesM2A4()
            "tp_log_m2_a5" -> exercisesM2A5()
            "tp_log_m3_a1" -> exercisesM3A1()
            "tp_log_m3_a2" -> exercisesM3A2()
            "tp_log_m3_a3" -> exercisesM3A3()
            "tp_log_m4_a1" -> exercisesM4A1()
            "tp_log_m4_a2" -> exercisesM4A2()
            "tp_log_m4_a3" -> exercisesM4A3()
            "tp_log_m5_a1" -> exercisesM5A1()
            "tp_log_m5_a2" -> exercisesM5A2()
            "tp_log_m5_a3" -> exercisesM5A3()
            "tp_log_m6_a1" -> exercisesM6A1()
            else -> emptyList()
        }
    }

    private fun exercisesM1A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-01",
            lessonId = "tp_log_m1_a1",
            statement = "Na administração de materiais, a decisão entre manter estoques volumosos de segurança ou operar com estoques mínimos envolve um conflito de objetivos (trade-off). Qual é a principal consequência financeira de se manter estoques elevados?",
            options = listOf(
                "A) Redução dos custos de oportunidade do capital.",
                "B) Aumento do custo de armazenagem, seguros e obsolescência com imobilização de capital de giro.",
                "C) Aumento do risco de ruptura de estoque na produção.",
                "D) Eliminação dos custos de obsolescência técnica.",
                "E) Redução automática do tempo de reposição (lead time) dos fornecedores."
            ),
            correctIndex = 1,
            steps = listOf(
                "Estoques elevados garantem alto nível de atendimento e evitam faltas operacionais.",
                "No entanto, exigem maior espaço físico, elevam custos de manutenção, seguros e manuseio, além de amarrarem recursos financeiros que poderiam render em aplicações ou investimentos produtivos."
            ),
            finalComment = "Estoques elevados imobilizam capital de giro e encarecem a armazenagem e o risco de deterioração."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-02",
            lessonId = "tp_log_m1_a1",
            statement = "Em uma auditoria de almoxarifado de um terminal de transporte de combustíveis, foram conferidos 400 itens cadastrados no sistema. Desses, 380 itens apresentaram exata correspondência entre o saldo físico e o contábil. Qual foi o índice de acuracidade desse estoque?",
            options = listOf(
                "A) 90%",
                "B) 92,5%",
                "C) 95%",
                "D) 97,5%",
                "E) 98%"
            ),
            correctIndex = 2,
            steps = listOf(
                "Acuracidade = (Itens com saldo correto / Total de itens verificados) × 100%.",
                "Acuracidade = (380 / 400) × 100% = 0,95 × 100% = 95%."
            ),
            finalComment = "O índice de acuracidade apurado foi de exatamente 95%."
        )
    )

    private fun exercisesM1A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-03",
            lessonId = "tp_log_m1_a2",
            statement = "Segundo a classificação de materiais por criticidade operacional (Sistema XYZ), um item que paralisa imediatamente a linha de transporte ou coloca em risco a segurança e o meio ambiente quando falta, sem substitutos no mercado em curto prazo, é classificado como:",
            options = listOf(
                "A) Classe X",
                "B) Classe Y",
                "C) Classe Z",
                "D) Classe A",
                "E) Classe C"
            ),
            correctIndex = 2,
            steps = listOf(
                "Classe X: itens de baixa criticidade, de fácil obtenção e com similares no mercado.",
                "Classe Y: itens de média criticidade, cuja falta traz dificuldades contornáveis.",
                "Classe Z: itens vitais, de alta criticidade, cuja falta paralisa operações essenciais ou viola segurança/SMS, sem substituto imediato."
            ),
            finalComment = "A Classe Z define materiais vitais com impacto operacional e de segurança crítico."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-04",
            lessonId = "tp_log_m1_a2",
            statement = "Os sistemas de classificação de materiais devem pautar-se por atributos essenciais para serem eficazes. O atributo que determina que o sistema deve abranger todo o universo de materiais da empresa, contemplando desde materiais de expediente até peças pesadas de engenharia, é denominado:",
            options = listOf(
                "A) Flexibilidade",
                "B) Praticidade",
                "C) Abrangência",
                "D) Padronização",
                "E) Normalização"
            ),
            correctIndex = 2,
            steps = listOf(
                "Abrangência: qualidade de cobrir a totalidade dos itens da organização.",
                "Flexibilidade: facilidade de inclusão de novos itens sem quebrar a estrutura.",
                "Praticidade: simplicidade de uso pelos operadores."
            ),
            finalComment = "A abrangência assegura que nenhum item fique de fora do catálogo de controle."
        )
    )

    private fun exercisesM1A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-05",
            lessonId = "tp_log_m1_a3",
            statement = "Em relação à tecnologia de Identificação por Radiofrequência (RFID) comparada ao tradicional Código de Barras óptico no controle de materiais de almoxarifado, assinale a vantagem correta do RFID:",
            options = listOf(
                "A) Exigência de contato físico ou linha de visada direta com a etiqueta.",
                "B) Custo unitário da etiqueta substancialmente menor que a etiqueta de código de barras.",
                "C) Capacidade de realizar leitura simultânea de múltiplos itens a distância sem necessidade de visada direta.",
                "D) Impossibilidade de regravar informações na etiqueta inteligente.",
                "E) Leitura restrita a ambientes internos livres de poeira."
            ),
            correctIndex = 2,
            steps = listOf(
                "O código de barras exige linha de visada direta entre o leitor óptico e a etiqueta impressa.",
                "A tecnologia RFID utiliza ondas eletromagnéticas, permitindo capturar dados de dezenas de paletes simultaneamente mesmo dentro de caixas lacradas e sem contato visual."
            ),
            finalComment = "A leitura simultânea sem visada direta e a distância é o grande diferencial do RFID na logística."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-06",
            lessonId = "tp_log_m1_a3",
            statement = "A prática de reduzir deliberadamente a diversidade de tipos, tamanhos e modelos de uma peça empregada em diferentes áreas de uma mesma corporação é tecnicamente denominada:",
            options = listOf(
                "A) Especificação",
                "B) Padronização",
                "C) Codificação",
                "D) Tombamento",
                "E) Diligenciamento"
            ),
            correctIndex = 1,
            steps = listOf(
                "Especificação: descrição minuciosa das características de um material.",
                "Padronização: redução de variedades de itens semelhantes para simplificar compras e estoques.",
                "Codificação: atribuição de códigos alfanuméricos ou decimais aos itens."
            ),
            finalComment = "Padronizar consiste em reduzir variedades para simplificar a gestão e baratear custos."
        )
    )

    private fun exercisesM2A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-07",
            lessonId = "tp_log_m2_a1",
            statement = "O consumo mensal de juntas de vedação em um terminal nos últimos quatro meses foi de: Mês 1 = 120; Mês 2 = 140; Mês 3 = 130; Mês 4 = 150. Pelo método da Média Móvel Simples de 3 períodos, qual é a previsão de consumo para o Mês 5?",
            options = listOf(
                "A) 130 unidades",
                "B) 135 unidades",
                "C) 140 unidades",
                "D) 145 unidades",
                "E) 150 unidades"
            ),
            correctIndex = 2,
            steps = listOf(
                "A média móvel simples de 3 períodos considera apenas os 3 meses mais recentes: Mês 2 (140), Mês 3 (130) e Mês 4 (150).",
                "O Mês 1 é descartado da janela móvel.",
                "Soma = 140 + 130 + 150 = 420.",
                "Previsão para o Mês 5 = 420 / 3 = 140 unidades."
            ),
            finalComment = "A média dos últimos 3 meses (140 + 130 + 150)/3 resulta em 140 unidades para o Mês 5."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-08",
            lessonId = "tp_log_m2_a1",
            statement = "Sobre a natureza da demanda na gestão de estoques, assinale a opção que caracteriza corretamente uma Demanda Dependente:",
            options = listOf(
                "A) Demanda sujeita exclusivamente a flutuações e caprichos do mercado externo.",
                "B) Necessidade calculada diretamente a partir da lista técnica (BOM) ou programação de montagem de um produto pai.",
                "C) Demanda estimada exclusivamente por métodos de séries temporais como a média móvel.",
                "D) Demanda de produtos finais de consumo em lojas de varejo.",
                "E) Demanda com padrão estritamente aleatório e imprevisível."
            ),
            correctIndex = 1,
            steps = listOf(
                "Demanda dependente deriva da programação de produção de um bem composto (MRP).",
                "Se a empresa decide montar 50 bombas industriais, a quantidade de rolamentos e eixos é calculada com exatidão matemática a partir da lista técnica de materiais (BOM - Bill of Materials)."
            ),
            finalComment = "A demanda dependente é derivada deterministicamente da necessidade de outro item pai."
        )
    )

    private fun exercisesM2A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-09",
            lessonId = "tp_log_m2_a2",
            statement = "Uma base operacional consome 10 unidades de um filtro especial por dia. O fornecedor dessas peças exige um prazo de entrega (Lead Time) de 15 dias. A empresa estipulou que deve manter um estoque mínimo de segurança de 50 unidades. O Ponto de Pedido (PP) dessa peça é de:",
            options = listOf(
                "A) 150 unidades",
                "B) 180 unidades",
                "C) 200 unidades",
                "D) 250 unidades",
                "E) 300 unidades"
            ),
            correctIndex = 2,
            steps = listOf(
                "Fórmula do Ponto de Pedido: PP = (Consumo Diário × Lead Time) + Estoque Mínimo.",
                "Consumo durante o lead time: 10 unidades/dia × 15 dias = 150 unidades.",
                "Adicionar o estoque de segurança: 150 + 50 = 200 unidades."
            ),
            finalComment = "Ao atingir 200 unidades em estoque, a compra deve ser disparada para não invadir a reserva de segurança."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-10",
            lessonId = "tp_log_m2_a2",
            statement = "Se o estoque mínimo de um sobressalente é de 40 unidades e a empresa adquire esse material em lotes econômicos de 120 unidades, qual é o Estoque Máximo teórico desse material no almoxarifado?",
            options = listOf(
                "A) 80 unidades",
                "B) 120 unidades",
                "C) 160 unidades",
                "D) 200 unidades",
                "E) 240 unidades"
            ),
            correctIndex = 2,
            steps = listOf(
                "Estoque Máximo = Estoque Mínimo + Tamanho do Lote de Compra (Q).",
                "Estoque Máximo = 40 + 120 = 160 unidades."
            ),
            finalComment = "O estoque atinge o nível máximo logo após o recebimento do lote padrão sobre o estoque mínimo: 40 + 120 = 160."
        )
    )

    private fun exercisesM2A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-11",
            lessonId = "tp_log_m2_a3",
            statement = "No modelo clássico do Lote Econômico de Compra (LEC), qual condição teórica e gráfica ocorre exatamente no ponto em que o custo total anual de estoques é mínimo?",
            options = listOf(
                "A) O custo anual de armazenagem é igual a zero.",
                "B) O custo anual de emissão de pedidos iguala exatamente o custo anual de armazenagem.",
                "C) O custo de falta de estoque supera o custo de aquisição.",
                "D) O estoque médio iguala a demanda anual total.",
                "E) O custo unitário do item é reduzido a zero pelo fornecedor."
            ),
            correctIndex = 1,
            steps = listOf(
                "O Custo Total de Estoque é a soma do Custo de Pedidos com o Custo de Armazenagem.",
                "À medida que o lote aumenta, o custo de pedidos cai e o custo de armazenagem sobe linearmente.",
                "O mínimo da função custo total ocorre rigorosamente na interseção das duas curvas, ou seja, onde Custo de Pedido = Custo de Armazenagem."
            ),
            finalComment = "No ponto do LEC, o custo anual de pedidos é idêntico ao custo anual de manutenção/armazenagem."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-12",
            lessonId = "tp_log_m2_a3",
            statement = "Uma empresa consome 1.800 unidades de um insumo por ano. O custo de emissão de cada pedido de compra é de R$ 40,00 e o custo de estocagem é de R$ 4,00 por unidade ao ano. Qual é o Lote Econômico de Compra (LEC)?",
            options = listOf(
                "A) 120 unidades",
                "B) 150 unidades",
                "C) 180 unidades",
                "D) 190 unidades",
                "E) 200 unidades"
            ),
            correctIndex = 3,
            steps = listOf(
                "Fórmula do LEC = √[ (2 × D × Cp) / Ce ].",
                "Numerador = 2 × 1.800 × 40 = 144.000.",
                "Divisão por Ce = 144.000 / 4 = 36.000.",
                "Raiz quadrada = √36.000 ≈ 189,7 ≈ 190 unidades."
            ),
            finalComment = "O LEC calculado pela fórmula de Wilson resulta em aproximadamente 190 unidades por pedido."
        )
    )

    private fun exercisesM2A4(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-13",
            lessonId = "tp_log_m2_a4",
            statement = "Um almoxarifado registrou um consumo anual de materiais de R$ 6.000.000,00, mantendo um estoque médio anual avaliado em R$ 1.000.000,00. Sabendo que o ano comercial possui 360 dias, quais são, respectivamente, o Giro de Estoque e a Cobertura de Estoque?",
            options = listOf(
                "A) 6 vezes ao ano e 60 dias de cobertura",
                "B) 6 vezes ao ano e 30 dias de cobertura",
                "C) 12 vezes ao ano e 30 dias de cobertura",
                "D) 5 vezes ao ano e 72 dias de cobertura",
                "E) 4 vezes ao ano e 90 dias de cobertura"
            ),
            correctIndex = 0,
            steps = listOf(
                "Giro de Estoque = Consumo Anual / Estoque Médio = 6.000.000 / 1.000.000 = 6 vezes ao ano.",
                "Cobertura em dias = 360 dias / Giro = 360 / 6 = 60 dias."
            ),
            finalComment = "Giro = 6 vezes ao ano; Cobertura = 60 dias de autonomia de consumo."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-14",
            lessonId = "tp_log_m2_a4",
            statement = "Na aplicação da Curva ABC em um almoxarifado de grande porte, os itens pertencentes à Classe A caracterizam-se por:",
            options = listOf(
                "A) Grande quantidade física de itens (cerca de 70% do total) com baixíssimo impacto financeiro.",
                "B) Pequena quantidade física de itens (cerca de 10% a 20% do total) que concentram a maior parte do valor financeiro (cerca de 70% a 80%).",
                "C) Itens com giro nulo e destinados a sucata ou descarte.",
                "D) Materiais de consumo comum que dispensam controle informatizado.",
                "E) Itens de valor financeiro intermediário avaliados exclusivamente por peso."
            ),
            correctIndex = 1,
            steps = listOf(
                "Princípio de Pareto (80/20): poucos itens concentram a maior parte do valor.",
                "Classe A reúne entre 10% e 20% dos itens do almoxarifado, representando de 70% a 80% do valor econômico acumulado."
            ),
            finalComment = "Classe A = poucos itens de altíssimo valor financeiro acumulado, exigindo controle prioritário."
        )
    )

    private fun exercisesM2A5(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-15",
            lessonId = "tp_log_m2_a5",
            statement = "No Brasil, em relação aos métodos contábeis de valoração de saídas de estoques, o método UEPS (Último a Entrar, Primeiro a Sair) caracteriza-se por ser:",
            options = listOf(
                "A) Obrigatório para todas as empresas comerciais segundo o CPC 16.",
                "B) O único método admitido pela Receita Federal para apuração do Imposto de Renda de Pessoas Jurídicas.",
                "C) Expressamente vedado pela legislação do Imposto de Renda e pelas normas do CPC/IFRS.",
                "D) Recomendado exclusivamente para materiais perecíveis de data de validade curta.",
                "E) Equivalente ao método PEPS em períodos de alta inflação."
            ),
            correctIndex = 2,
            steps = listOf(
                "O método UEPS (LIFO) valoriza saídas pelos custos mais recentes (mais altos na inflação), reduzindo artificialmente o lucro contábil.",
                "Por isso, a legislação tributária brasileira (RIR) e as normas contábeis internacionais (CPC 16 / IAS 2) PROÍBEM terminantemente o seu uso."
            ),
            finalComment = "O UEPS é expressamente proibido pela legislação fiscal e contábil no Brasil."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-16",
            lessonId = "tp_log_m2_a5",
            statement = "Qual é a principal vantagem operacional do Inventário Rotativo (Cíclico) sobre o Inventário Geral (Periódico) em um centro de distribuição?",
            options = listOf(
                "A) Exige a paralisação completa de todas as atividades do almoxarifado por vários dias.",
                "B) É realizado apenas a cada cinco anos para auditoria externa.",
                "C) Permite a contagem contínua e programada de grupos de itens sem interromper o atendimento às operações, identificando causas de desvios com rapidez.",
                "D) Não necessita de registros no sistema de informação.",
                "E) Aplica-se exclusivamente aos materiais da Classe C da curva ABC."
            ),
            correctIndex = 2,
            steps = listOf(
                "O inventário geral exige parar toda a operação e fechar o armazém.",
                "O inventário rotativo divide o acervo em lotes e realiza contagens diárias ou semanais ao longo do ano sem paralisar o atendimento, assegurando alta acuracidade contínua."
            ),
            finalComment = "O inventário rotativo não interrompe a operação e detecta erros de lançamento logo após sua ocorrência."
        )
    )

    private fun exercisesM3A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-17",
            lessonId = "tp_log_m3_a1",
            statement = "O princípio do controle interno que determina que as funções de requisitar, autorizar a compra, conferir o recebimento físico e efetuar o pagamento a fornecedores devem ser exercidas por agentes e setores distintos é denominado:",
            options = listOf(
                "A) Princípio da Autotutela Administrativa",
                "B) Segregação de Funções",
                "C) Princípio da Especialidade Contratual",
                "D) Vinculação ao Instrumento Convocatório",
                "E) Razoabilidade da Proposta"
            ),
            correctIndex = 1,
            steps = listOf(
                "A segregação de funções impede que uma mesma pessoa ou equipe execute todas as etapas críticas de uma transação financeira/patrimonial.",
                "Essa divisão é pilar de compliance para evitar fraudes, conluios e pagamentos indevidos."
            ),
            finalComment = "Segregação de funções assegura que quem compra não seja quem recebe nem quem paga."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-18",
            lessonId = "tp_log_m3_a1",
            statement = "Na elaboração da especificação de materiais para aquisição em estatais, recomenda-se priorizar a especificação por desempenho (requisitos funcionais) em detrimento da indicação de marcas comerciais. Essa diretriz visa a:",
            options = listOf(
                "A) Dificultar a participação de fabricantes nacionais.",
                "B) Estimular a competitividade, a isonomia entre fornecedores e a obtenção de preços mais vantajosos sem direcionamento ilícito.",
                "C) Reduzir o nível de qualidade técnica do produto fornecido.",
                "D) Dispensar a fiscalização e inspeção de recebimento.",
                "E) Permitir compras sem documentação de regularidade fiscal."
            ),
            correctIndex = 1,
            steps = listOf(
                "A legislação de compras públicas e das estatais veda a preferência de marcas comerciais sem justificativa técnica fundamentada.",
                "Especificar por padrões de desempenho amplia o rol de licitantes e estimula a disputa pelo menor preço."
            ),
            finalComment = "Especificar por desempenho garante isonomia, ampla concorrência e evita direcionamento indevido."
        )
    )

    private fun exercisesM3A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-19",
            lessonId = "tp_log_m3_a2",
            statement = "O indicador logístico OTIF (On-Time In-Full) é utilizado para avaliar a excelência dos fornecedores. Em que situação uma entrega é considerada um pedido perfeito segundo o OTIF?",
            options = listOf(
                "A) Quando a entrega é feita dentro do prazo, mesmo que com metade dos itens em falta.",
                "B) Quando a entrega contém todos os itens pedidos sem avarias, mesmo entregue com duas semanas de atraso.",
                "C) Quando a mercadoria é entregue rigorosamente dentro do prazo acordado E com a totalidade da quantidade e qualidade solicitadas.",
                "D) Quando o fornecedor concede desconto comercial de 10% na fatura.",
                "E) Quando o transporte é feito obrigatoriamente por modal aéreo."
            ),
            correctIndex = 2,
            steps = listOf(
                "On-Time: cumprimento estrito do prazo estipulado.",
                "In-Full: atendimento completo de quantidade e especificações sem avarias ou faltas.",
                "OTIF exige o atendimento SIMULTÂNEO de ambos os parâmetros."
            ),
            finalComment = "OTIF avalia o percentual de pedidos entregues no prazo E com a quantidade e qualidade completas."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-20",
            lessonId = "tp_log_m3_a2",
            statement = "A atividade proativa desenvolvida pela equipe de suprimentos que consiste em acompanhar sistematicamente o cronograma de fabricação e transporte do fornecedor para antecipar desvios e garantir o cumprimento do prazo acordado é chamada de:",
            options = listOf(
                "A) Tombamento",
                "B) Diligenciamento (Follow-up)",
                "C) Glosa Contratual",
                "D) Picking Discreto",
                "E) Cross-Docking"
            ),
            correctIndex = 1,
            steps = listOf(
                "Diligenciamento (follow-up): acompanhamento ativo do pedido junto ao fornecedor.",
                "Permite identificar problemas na linha de produção do fabricante antes do prazo final de entrega."
            ),
            finalComment = "Diligenciamento é o acompanhamento do pedido para evitar atrasos na entrega."
        )
    )

    private fun exercisesM3A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-21",
            lessonId = "tp_log_m3_a3",
            statement = "Uma organização que centraliza corporativamente todas as suas grandes compras de matérias-primas e sobressalentes estratégicos busca principalmente qual vantagem competitiva?",
            options = listOf(
                "A) Maior dispersão e despadronização de marcas.",
                "B) Ganhos de economia de escala e maior poder de barganha comercial frente a grandes fornecedores.",
                "C) Eliminação de procedimentos formais de fiscalização.",
                "D) Atendimento instantâneo a pequenas compras emergenciais locais sem aprovação.",
                "E) Redução do controle orçamentário pela alta administração."
            ),
            correctIndex = 1,
            steps = listOf(
                "Centralizar compras consolida as demandas de várias filiais em grandes volumes.",
                "Isso confere enorme poder de negociação e descontos substanciais de escala com fabricantes."
            ),
            finalComment = "Compras centralizadas maximizam o poder de barganha e garantem padronização corporativa."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-22",
            lessonId = "tp_log_m3_a3",
            statement = "A filosofia Just-in-Time (JIT) aplicada à gestão de suprimentos visa a trabalhar com estoques reduzidos ou nulos. Para que o JIT funcione com eficácia sem paralisar a produção, é pré-requisito indispensável:",
            options = listOf(
                "A) Grandes estoques de segurança espalhados pelo armazém.",
                "B) Fornecedores altamente confiáveis, pontuais, com qualidade assegurada na fonte e tempos de resposta rápidos.",
                "C) Pedidos anuais de compra em lotes gigantescos.",
                "D) Inspeção demorada de 100% das peças na entrada da fábrica.",
                "E) Contratação exclusiva de transportadores de longa distância marítima internacional."
            ),
            correctIndex = 1,
            steps = listOf(
                "Sem estoques de proteção, qualquer atraso ou entrega de peça com defeito paralisa a operação.",
                "Portanto, o JIT depende criticamente de fornecedores de altíssima confiabilidade e proximidade operacional."
            ),
            finalComment = "JIT exige fornecedores altamente pontuais e com padrão de defeito zero na entrega."
        )
    )

    private fun exercisesM4A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-23",
            lessonId = "tp_log_m4_a1",
            statement = "No processo de recebimento de materiais no almoxarifado, a etapa responsável por confrontar os dados fiscais da Nota Fiscal Eletrônica com a Ordem de Compra (preço unitário, CNPJ, tributos e condições de faturamento) é a:",
            options = listOf(
                "A) Conferência Qualitativa",
                "B) Conferência Quantitativa",
                "C) Conferência Documental",
                "D) Conferência Cega",
                "E) Roteirização"
            ),
            correctIndex = 2,
            steps = listOf(
                "Conferência Documental (ou fiscal): valida a legalidade e a conformidade do documento fiscal com o pedido contratado.",
                "Conferência Quantitativa: contagem física das unidades entregues.",
                "Conferência Qualitativa: análise técnica de conformidade das especificações do material."
            ),
            finalComment = "A conferência documental valida a correspondência legal e fiscal da NF com o pedido de compra."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-24",
            lessonId = "tp_log_m4_a1",
            statement = "Por que a adoção da técnica de 'Conferência Cega' no recebimento físico de materiais é amplamente recomendada pelas boas práticas de auditoria e controle?",
            options = listOf(
                "A) Porque dispensa a inspeção física dos pacotes lacrados.",
                "B) Porque impede que o conferente saiba antecipadamente a quantidade faturada, forçando a contagem real e evitando acomodação ou validações automáticas.",
                "C) Porque acelera o recebimento eliminando a contagem de peças.",
                "D) Porque permite aceitar materiais vencidos no armazém.",
                "E) Porque transfere a responsabilidade da contagem para o motorista transportador."
            ),
            correctIndex = 1,
            steps = listOf(
                "Na conferência cega, o almoxarife recebe uma lista sem o número de unidades constantes da nota fiscal.",
                "Isso o obriga a contar meticulosamente cada volume, garantindo fidelidade absoluta no registro físico."
            ),
            finalComment = "A conferência cega elimina o viés de acomodação e assegura contagens fidedignas."
        )
    )

    private fun exercisesM4A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-25",
            lessonId = "tp_log_m4_a2",
            statement = "No projeto de estocagem de um armazém, o sistema de endereçamento no qual cada item não possui uma posição fixa exclusiva, sendo alocado na vaga disponível no momento da entrada com controle por sistema informatizado (WMS), é denominado:",
            options = listOf(
                "A) Endereçamento Fixo",
                "B) Endereçamento Aleatório (ou Caótico)",
                "C) Endereçamento Geográfico",
                "D) Endereçamento Manual",
                "E) Endereçamento Linear"
            ),
            correctIndex = 1,
            steps = listOf(
                "Endereçamento fixo reserva posições específicas para cada material (deixando vagas ociosas quando falta o produto).",
                "Endereçamento aleatório (ou caótico) maximiza a taxa de ocupação espacial aproveitando qualquer vão vago com registro no WMS."
            ),
            finalComment = "Endereçamento aleatório ou caótico otimiza o uso do espaço físico do armazém."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-26",
            lessonId = "tp_log_m4_a2",
            statement = "A atividade interna do armazém que consiste na separação, coleta e agrupamento dos itens solicitados em uma requisição de materiais ou pedido de cliente para posterior expedição é denominada internacionalmente de:",
            options = listOf(
                "A) Cross-docking",
                "B) Picking",
                "C) Milk run",
                "D) Follow-up",
                "E) Tombamento"
            ),
            correctIndex = 1,
            steps = listOf(
                "Picking (order picking): processo de coleta física de materiais nos locais de armazenagem.",
                "É uma das atividades mais intensivas em mão de obra e tempo dentro de um armazém."
            ),
            finalComment = "Picking é a atividade de separação e coleta de pedidos nos pontos de estocagem."
        )
    )

    private fun exercisesM4A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-27",
            lessonId = "tp_log_m4_a3",
            statement = "Para armazenar materiais compridos, tubulações de aço de grande diâmetro e perfis estruturais de 6 a 12 metros de comprimento, a estrutura de armazenagem mais indicada por não possuir colunas frontais intermediárias que obstruam o acesso é a:",
            options = listOf(
                "A) Estrutura Porta-Paletes Convencional",
                "B) Estrutura Drive-in",
                "C) Estrutura Cantilever",
                "D) Gaveteiro de Plástico",
                "E) Carrossel Vertical"
            ),
            correctIndex = 2,
            steps = listOf(
                "Porta-paletes possui colunas verticais que barram barras compridas.",
                "A estrutura Cantilever possui colunas centrais traseiras e braços de sustentação abertos em balanço, perfeita para tubulações e barras longas."
            ),
            finalComment = "Cantilever é a estrutura ideal para materiais compridos como tubos e perfis industriais."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-28",
            lessonId = "tp_log_m4_a3",
            statement = "O arranjo físico (layout) de armazém desenhado em formato de 'U', onde o recebimento e a expedição de mercadorias compartilham o mesmo lado do edifício, oferece como principal vantagem operacional:",
            options = listOf(
                "A) Aumento das distâncias totais percorridas pelas empilhadeiras em todas as operações.",
                "B) Compartilhamento facilitado de docas, equipamentos de pátio e mão de obra de supervisão entre recebimento e expedição.",
                "C) Impossibilidade de expansão física do galpão no futuro.",
                "D) Cruzamento permanente e perigoso de fluxos de entrada e saída no meio das prateleiras.",
                "E) Exclusividade para empresas que operam sem paletes."
            ),
            correctIndex = 1,
            steps = listOf(
                "Layout em U concentra docas em uma mesma fachada.",
                "Isso flexibiliza o uso de empilhadeiras e operadores (que descarregam pela manhã e carregam à tarde nas mesmas docas) e permite expansão fácil pelos fundos do armazém."
            ),
            finalComment = "O layout em 'U' permite compartilhar docas, equipamentos e supervisão na mesma área externa."
        )
    )

    private fun exercisesM5A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-29",
            lessonId = "tp_log_m5_a1",
            statement = "O fenômeno verificado na cadeia de suprimentos no qual uma pequena oscilação na demanda do consumidor final é sucessivamente amplificada à medida que os pedidos sobem em direção aos distribuidores, fabricantes e produtores de matéria-prima é conhecido como:",
            options = listOf(
                "A) Efeito Gargalo",
                "B) Efeito Chicote (Bullwhip Effect)",
                "C) Efeito Substituição",
                "D) Teoria das Restrições",
                "E) Efeito Pareto"
            ),
            correctIndex = 1,
            steps = listOf(
                "O Efeito Chicote decorre da distorção de informações, compras por lotes e falta de integração da demanda real ao longo dos elos da cadeia de suprimentos."
            ),
            finalComment = "O Efeito Chicote amplifica artificialmente a volatilidade de pedidos nos elos a montante da cadeia."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-30",
            lessonId = "tp_log_m5_a1",
            statement = "De acordo com a Política Nacional de Resíduos Sólidos (Lei nº 12.305/2010), a coleta e encaminhamento para rerrefino ou reciclagem de óleos lubrificantes industriais usados, baterias e pneus descartados pelos usuários ao final de sua vida útil enquadra-se no conceito de:",
            options = listOf(
                "A) Logística Reversa de Pós-Venda",
                "B) Logística Reversa de Pós-Consumo",
                "C) Logística de Distribuição Direta",
                "D) Milk Run Integrado",
                "E) Cross-Docking Inverso"
            ),
            correctIndex = 1,
            steps = listOf(
                "Pós-venda trata de produtos não consumidos ou devolvidos por vícios comerciais.",
                "Pós-consumo trata de materiais que já cumpriram sua utilidade e são descartados como resíduos para reintegração ambiental ou reaproveitamento."
            ),
            finalComment = "Bens descartados após o uso integral constituem logística reversa de pós-consumo."
        )
    )

    private fun exercisesM5A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-31",
            lessonId = "tp_log_m5_a2",
            statement = "Sobre as características econômicas e operacionais do modal Dutoviário de transporte, assinale a opção correta:",
            options = listOf(
                "A) Apresenta baixos custos fixos de implantação e altos custos variáveis operacionais.",
                "B) Possui alta flexibilidade de rotas, alcançando qualquer destino sem necessidade de obras físicas.",
                "C) Apresenta elevadíssimos custos fixos de implantação de infraestrutura, mas custos variáveis operacionais extremamente baixos por volume transportado em fluxo contínuo.",
                "D) É o modal mais veloz para transporte de cargas secas fracionadas.",
                "E) Sofre interrupções frequentes em razão de condições climáticas adversas como neblina e chuvas."
            ),
            correctIndex = 2,
            steps = listOf(
                "Dutos exigem investimentos bilionários em tubulações, bombas e automação (altíssimo custo fixo).",
                "Em operação, seu custo por metro cúbico bombeado é mínimo e o fluxo é contínuo e imune ao clima."
            ),
            finalComment = "Dutos combinam altíssimo custo fixo inicial com o menor custo variável operacional por unidade de fluido transportado."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-32",
            lessonId = "tp_log_m5_a2",
            statement = "O modal de transporte caracterizado por oferecer serviço porta a porta, ampla capilaridade geográfica, agilidade em curtas distâncias, porém com alta taxa de emissão de poluentes e elevado custo operacional variável por combustível e pedágios é o:",
            options = listOf(
                "A) Modal Ferroviário",
                "B) Modal Aquaviário",
                "C) Modal Dutoviário",
                "D) Modal Rodoviário",
                "E) Modal Aéreo"
            ),
            correctIndex = 3,
            steps = listOf(
                "O transporte rodoviário é o único modal que oferece entrega direta porta a porta sem necessidade de transbordo entre modais.",
                "Sua contrapartida é o alto custo variável por tonelada e a baixa capacidade de carga se comparado a trens, navios e dutos."
            ),
            finalComment = "O modal rodoviário destaca-se pela capilaridade porta a porta com altos custos operacionais variáveis."
        )
    )

    private fun exercisesM5A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-33",
            lessonId = "tp_log_m5_a3",
            statement = "Nos termos da Lei nº 9.611/1998, a operação de transporte de cargas que utiliza dois ou mais modais diferentes, regida por um contrato único e executada sob a responsabilidade jurídica exclusiva de um Operador de Transporte Multimodal (OTM), é classificada como:",
            options = listOf(
                "A) Transporte Unimodal Conexo",
                "B) Intermodalidade Tradicional",
                "C) Transporte Multimodal de Cargas",
                "D) Cabotagem Simples",
                "E) Cabotagem Fracionada"
            ),
            correctIndex = 2,
            steps = listOf(
                "A existência de um único responsável perante o cliente (OTM) e de um único documento fiscal (CTMC) para toda a rota combinada define juridicamente o Transporte Multimodal."
            ),
            finalComment = "Múltiplos modais sob contrato único e responsabilidade integral do OTM caracterizam o transporte multimodal."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-34",
            lessonId = "tp_log_m5_a3",
            statement = "A técnica de distribuição logística na qual os produtos recebidos nas docas de entrada são imediatamente descarregados, conferidos e transferidos para os veículos de entrega na expedição, sem serem estocados nas prateleiras do armazém, é chamada de:",
            options = listOf(
                "A) Cross-Docking",
                "B) Milk Run",
                "C) Armazenagem Caótica",
                "D) Curva ABC",
                "E) Tombamento de Cargas"
            ),
            correctIndex = 0,
            steps = listOf(
                "Cross-docking (cruzamento de docas) elimina a armazenagem intermediária.",
                "O produto transita diretamente da doca de recebimento para a doca de expedição, reduzindo o tempo de ciclo e os custos de estocagem."
            ),
            finalComment = "Cross-docking é o transbordo imediato entre docas de recebimento e expedição sem estocagem."
        )
    )

    private fun exercisesM6A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-35",
            lessonId = "tp_log_m6_a1",
            statement = "Nos termos do Art. 63 da Lei nº 13.303/2016 (Lei das Estatais), qual procedimento auxiliar permite a seleção prévia de preços de bens e serviços de consumo frequente mediante Ata, sem que a estatal fique obrigada a contratar a totalidade dos quantitativos registrados?",
            options = listOf(
                "A) Pré-qualificação Permanente",
                "B) Cadastramento Geral",
                "C) Sistema de Registro de Preços (SRP)",
                "D) Concurso Público",
                "E) Tomada de Preços"
            ),
            correctIndex = 2,
            steps = listOf(
                "O Sistema de Registro de Preços (SRP) formaliza uma Ata de Registro de Preços (ARP).",
                "A empresa adquire os materiais parceladamente conforme suas necessidades reais de consumo, sem obrigação de compra de todo o quantitativo estimado."
            ),
            finalComment = "O SRP registra preços em Ata para compras sob demanda sem obrigação de aquisição integral."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-LOG-SUP-36",
            lessonId = "tp_log_m6_a1",
            statement = "O procedimento permanente e público pelo qual uma empresa estatal analisa e atesta antecipadamente que determinados fornecedores atendem às condições de habilitação ou que seus produtos satisfazem aos requisitos técnicos exigidos, acelerando licitações futuras, é denominado:",
            options = listOf(
                "A) Pré-qualificação Permanente",
                "B) Leilão Eletrônico",
                "C) Manifestação de Interesse Privado",
                "D) Desapropriação Administrativa",
                "E) Roteirização Licitatória"
            ),
            correctIndex = 0,
            steps = listOf(
                "A pré-qualificação permanente é aberta a qualquer tempo pela estatal.",
                "Ela avalia fornecedores e certifica produtos previamente, reduzindo o tempo de habilitação nas licitações subsequentes."
            ),
            finalComment = "A Pré-qualificação Permanente atesta requisitos de habilitação e conformidade técnica de forma antecipada."
        )
    )
}

