package com.example.data.transpetro

import com.example.data.model.CalculusDidacticBundle
import com.example.data.model.MathExercise

object TranspetroAdmPart2Didactic {

    fun getBundle(lessonId: String): CalculusDidacticBundle? = when (lessonId) {
        // =====================================================================
        // MÓDULO 6: GESTÃO DA MANUTENÇÃO
        // =====================================================================
        "tp_adm_m6_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m6_a1",
            intro = "A manutenção industrial é o conjunto de ações técnicas e administrativas destinadas a manter ou recolocar um equipamento ou sistema em estado no qual ele possa executar suas funções requeridas. No concurso da Cesgranrio para a Transpetro, as questões cobram com frequência as distinções conceituais e os custos comparativos entre Manutenção Corretiva (não planejada e planejada), Preventiva (por tempo ou horas de uso) e Preditiva (baseada na condição real).",
            theory = """
                1. Manutenção Corretiva:
                Ação de manutenção efetuada após a ocorrência de uma pane ou falha funcional declarada, destinada a recolocar o item em condições normais de operação:
                • Corretiva Não Planejada (Emergencial): ocorre de surpresa quando o equipamento quebra durante a operação. Gera os custos mais elevados de todos (custo de reparo acelerado, horas extras, perdas de produção e riscos à segurança).
                • Corretiva Planejada: a falha ou anomalia é detectada previamente (por inspeção visual ou preditiva), mas a equipe planeja a paralisação do equipamento para um momento oportuno antes do colapso total.

                2. Manutenção Preventiva:
                Ação de manutenção efetuada em intervalos predeterminados de tempo ou de uso (quilômetros rodados, horas de operação, número de ciclos), de acordo com critérios prescritos pelo fabricante:
                • Características: visa a reduzir a probabilidade de falha ou a degradação do funcionamento de um componente.
                • Exemplos: troca de óleo e filtros a cada 5.000 horas de operação; substituição periódica de retentores anualmente.
                • Desvantagem: pode substituir peças que ainda teriam considerável vida útil remanescente ou abrir equipamentos desnecessariamente, introduzindo falhas humanas de montagem (curva da banheira).

                3. Manutenção Preditiva (Manutenção Baseada na Condição - CBM):
                Acompanhamento contínuo ou periódico dos parâmetros operacionais do equipamento em funcionamento, sem necessidade de desmontá-lo:
                • Técnicas consagradas: análise de vibrações mecânicas, termografia infravermelha (aquecimento de mancais e quadros elétricos), análise de óleo lubrificante (ferrografia e partículas de desgaste), ultrassom e ensaios não destrutivos (líquido penetrante e radiografia).
                • Vantagem: a intervenção só é executada quando o equipamento apresenta sintomas objetivos de degradação iminente, maximizando a vida útil dos componentes e eliminando paradas desnecessárias.

                4. Manutenção Produtiva Total (TPM):
                Filosofia que envolve todos os níveis organizacionais, destacando a 'Manutenção Autônoma' (onde o próprio operador executa limpezas, lubrificações básicas e inspeções simples do seu posto de trabalho).
            """.trimIndent(),
            practicalApplication = """
                Em um oleoduto da Transpetro, bombas centrífugas de alta pressão contam com sensores de vibração e termografia (manutenção preditiva). Se o sensor aponta elevação milimétrica na vibração do mancal, a equipe programa uma manutenção preventiva planejada para substituir o rolamento antes que a bomba trave e paralise o bombeamento de petróleo (evitando a corretiva emergencial de altíssimo custo).
            """.trimIndent(),
            summary = """
                • Corretiva: atua após a falha ocorrer. A emergencial gera os maiores custos de perda operacional.
                • Preventiva: atua por calendário ou horímetro fixo antes da falha.
                • Preditiva: monitora a condição real em tempo real por vibração, termografia e análise de óleo; intervenção cirúrgica.
                • TPM / Manutenção Autônoma: operador de base executa inspeção e cuidados diários com a máquina.
            """.trimIndent(),
            formulaName = "Comparação de Custos por Tipo de Manutenção",
            formulaSyntax = "Custo: Corretiva Emergencial >> Preventiva Periódica > Preditiva Baseada na Condição",
            formulaTerms = listOf(
                "Corretiva Emergencial = Custo do reparo + Custo massivo de lucro cessante por parada de produção",
                "Preventiva = Custo programado de troca periódica de componentes",
                "Preditiva = Custo de sensores e diagnóstico com economia por máxima vida útil de peças"
            ),
            formulaWhenToUse = "Utilizar para selecionar a melhor política de manutenção para equipamentos críticos de infraestrutura.",
            exampleTitle = "Identificação de Manutenção Preditiva",
            exampleProblem = "Uma equipe técnica utiliza uma câmera termográfica infravermelha para inspecionar os painéis elétricos das subestações de um terminal dutoviário sem desligar os circuitos. Identificou um ponto com temperatura de 85°C em um disjuntor e programou a troca do componente para o próximo final de semana. Qual modalidade de manutenção foi empregada no diagnóstico?",
            exampleSteps = listOf(
                "A inspeção foi realizada com o equipamento em pleno funcionamento sem interrupção.",
                "Utilizou técnica instrumental de monitoramento de parâmetro físico real (temperatura por termografia infravermelha).",
                "O objetivo foi diagnosticar a degradação incipiente antes da falha catastrófica.",
                "Trata-se rigorosamente de Manutenção Preditiva (baseada na condição)."
            ),
            exampleAnswer = "Manutenção Preditiva (monitoramento térmico baseado na condição operacional).",
            goldTip = "Falou em 'análise de vibração', 'termografia', 'análise de óleo' ou 'acompanhamento de parâmetros em tempo real', a resposta é sempre MANUTENÇÃO PREDITIVA!"
        ),

        "tp_adm_m6_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m6_a2",
            intro = "O Planejamento e Controle da Manutenção (PCM) é o cérebro que programa paradas, dimensiona recursos, gerencia ordens de serviço e monitora os indicadores clássicos de confiabilidade e manutenibilidade. No concurso da Cesgranrio, os pontos obrigatórios são o cálculo do MTBF (Mean Time Between Failures), do MTTR (Mean Time to Repair) e da Disponibilidade Operacional.",
            theory = """
                1. Atribuições do Planejamento e Controle da Manutenção (PCM):
                • Emissão e controle das Ordens de Serviço (OS): documento que autoriza, descreve tarefas, materiais, ferramentas e equipe responsável.
                • Gestão de sobressalentes e ferramentas especiais em conjunto com o almoxarifado.
                • Programação do cronograma de intervenções preventivas e paradas de usina/terminais.
                • Apuração e análise dos indicadores técnicos de confiabilidade.

                2. Indicadores Fundamentais de Manutenção:
                • Tempo Médio Entre Falhas (MTBF - Mean Time Between Failures):
                  Mede a Confiabilidade do equipamento (o tempo médio em que ele opera normalmente sem quebrar).
                  Fórmula:
                    MTBF = Tempo Total de Operação Ativa / Número de Falhas Ocorridas
                  Quanto maior o MTBF, mais confiável e estável é o equipamento.
                • Tempo Médio Para Reparo (MTTR - Mean Time to Repair):
                  Mede a Manutenibilidade do equipamento (a facilidade e rapidez da equipe em reparar o equipamento após uma pane).
                  Fórmula:
                    MTTR = Tempo Total Gasto em Reparos / Número de Falhas Reparadas
                  Quanto menor o MTTR, mais ágil e eficiente é a manutenção.
                • Disponibilidade Operacional (D ou Inerente):
                  Mede a probabilidade percentual de o equipamento estar em condições normais de funcionamento quando solicitado:
                  Fórmula:
                    Disponibilidade (%) = [ MTBF / (MTBF + MTTR) ] × 100%
                  Ou: Disponibilidade (%) = (Tempo em Operação / Tempo Total do Período) × 100%.
            """.trimIndent(),
            practicalApplication = """
                Em uma estação de bombeamento da Transpetro, uma bomba de óleo operou 720 horas em um mês. Sofreu duas paradas por pane: a primeira durou 4 horas e a segunda durou 6 horas (total de 10 horas paradas em reparos).
                - Tempo ativo operando: 720 - 10 = 710 horas.
                - MTBF = 710 / 2 = 355 horas entre falhas.
                - MTTR = 10 / 2 = 5 horas médias de reparo.
                - Disponibilidade = [ 355 / (355 + 5) ] × 100% = (355 / 360) × 100% = 98,61%.
            """.trimIndent(),
            summary = """
                • PCM: planeja, programa e controla ordens de serviço, sobressalentes e custos de manutenção.
                • MTBF: Confiabilidade. Tempo ativo / nº de falhas. (Queremos MTBF ALTO).
                • MTTR: Manutenibilidade. Tempo de reparo / nº de falhas. (Queremos MTTR BAIXO).
                • Disponibilidade: D = MTBF / (MTBF + MTTR). Percentual de tempo útil disponível para operação.
            """.trimIndent(),
            formulaName = "Fórmulas do MTBF, MTTR e Disponibilidade",
            formulaSyntax = "MTBF = T_op / N_falhas   |   MTTR = T_rep / N_falhas   |   Disp = [ MTBF / (MTBF + MTTR) ] × 100%",
            formulaTerms = listOf(
                "MTBF = Confiabilidade média entre paradas",
                "MTTR = Agilidade média na execução do reparo",
                "Disp = Percentual de tempo que o equipamento esteve disponível para operar"
            ),
            formulaWhenToUse = "Utilizar para calcular indicadores técnicos de confiabilidade de máquinas e equipamentos industriais.",
            exampleTitle = "Cálculo Completo de MTBF, MTTR e Disponibilidade",
            exampleProblem = "Um gerador de emergência operou por 900 horas durante um semestre e registrou 3 paradas por falha mecânica. O tempo total gasto pela equipe para consertar as 3 panes somou 36 horas. Calcule o MTBF, o MTTR e a Disponibilidade do gerador.",
            exampleSteps = listOf(
                "Calcular o MTBF: MTBF = Tempo de operação / Nº de falhas = 900 horas / 3 = 300 horas.",
                "Calcular o MTTR: MTTR = Tempo de reparo / Nº de falhas = 36 horas / 3 = 12 horas.",
                "Calcular a Disponibilidade Inerente: D = MTBF / (MTBF + MTTR).",
                "D = 300 / (300 + 12) = 300 / 312 = 0,9615.",
                "Multiplicar por 100%: D ≈ 96,15%."
            ),
            exampleAnswer = "MTBF = 300 horas; MTTR = 12 horas; Disponibilidade = 96,15%.",
            goldTip = "Fórmula mágica da Cesgranrio: DISPONIBILIDADE = MTBF / (MTBF + MTTR). Se a questão der o MTBF e o MTTR, basta aplicar diretamente essa razão!"
        ),

        // =====================================================================
        // MÓDULO 7: ADMINISTRAÇÃO PATRIMONIAL
        // =====================================================================
        "tp_adm_m7_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m7_a1",
            intro = "A administração patrimonial cuida do controle físico, contábil e jurídico dos bens permanentes que compõem o ativo imobilizado da entidade. No concurso da Cesgranrio, os pontos centrais são a diferenciação entre material de consumo e bem permanente, a classificação dos bens móveis e imóveis, e o procedimento de tombamento patrimonial (cadastramento e plaquetamento).",
            theory = """
                1. Classificação dos Bens Patrimoniais:
                • Bens Imóveis: vinculados ao solo, que não podem ser transportados sem destruição ou alteração de sua substância (terrenos, edifícios de escritórios, tanques de armazenagem fixos, faixas de servidão de oleodutos).
                • Bens Móveis: suscetíveis de movimento próprio ou de remoção por força alheia sem alteração de sua substância:
                  - Materiais de Consumo: bens que perdem sua identidade física ou que têm durabilidade usual inferior a dois anos, consumindo-se com o uso (papel, combustíveis, graxas, parafusos, filtros descartáveis).
                  - Bens Permanentes (Patrimoniáveis): bens com vida útil superior a dois anos, que não perdem sua identidade física pelo uso e que agregam valor duradouro ao patrimônio (veículos, bombas industriais, computadores, móveis, geradores, navios).
                • Critérios de Distinção (Portaria STN): durabilidade, fragilidade, perecibilidade, incorporabilidade e transformabilidade.

                2. O Processo de Tombamento Patrimonial:
                Procedimento administrativo pelo qual um bem permanente recém-adquirido é incorporado oficialmente ao acervo patrimonial da empresa:
                • Cadastramento: registro no sistema informatizado de controle patrimonial com dados da Nota Fiscal, número de série do fabricante, data de entrada, custo de aquisição, centro de custo e especificações técnicas.
                • Identificação Física (Plaquetamento / Emplaquetamento): afixação de uma etiqueta metálica, selo adesivo de segurança com código de barras ou tag RFID contendo o número único de patrimônio (número de tombamento).
                • Termo de Responsabilidade: documento formal assinado pelo servidor ou gestor da área que assume a guarda e zela pela conservação do bem tombado.
            """.trimIndent(),
            practicalApplication = """
                Ao adquirir uma impressora industrial a laser para o terminal da Transpetro, o setor de patrimônio efetua o tombamento: gera o número de patrimônio no sistema ERP, afixa a plaqueta de alumínio com código de barras no chassi e colhe a assinatura do Termo de Responsabilidade do supervisor administrativo que utilizará o equipamento, vinculando a guarda física ao centro de custo local.
            """.trimIndent(),
            summary = """
                • Material de consumo: durabilidade inferior a 2 anos ou que se consome com o uso.
                • Bem permanente: vida útil superior a 2 anos, conserva identidade e sofre tombamento.
                • Tombamento: cadastramento contábil + plaquetamento físico + Termo de Responsabilidade.
                • Bens imóveis (terrenos, prédios) vs. Bens móveis (veículos, máquinas, computadores).
            """.trimIndent(),
            formulaName = "Etapas do Tombamento Patrimonial",
            formulaSyntax = "Aquisição/Recebimento → Cadastramento Contábil → Plaquetamento Físico → Termo de Responsabilidade",
            formulaTerms = listOf(
                "Plaqueta = Identificador numérico exclusivo afixado no bem",
                "Termo de Responsabilidade = Vínculo formal de guarda atribuído ao chefe do setor usuário"
            ),
            formulaWhenToUse = "Utilizar para descrever o rito de incorporação e controle de bens permanentes nas organizações.",
            exampleTitle = "Diferença entre Material de Consumo e Bem Permanente",
            exampleProblem = "Um almoxarifado recebeu duas caixas: uma contendo 50 pacotes de papel A4 para relatórios e outra contendo 2 projetores multimídia portáteis para salas de treinamento. Qual deles deve ser submetido ao processo de tombamento patrimonial com plaqueta e por quê?",
            exampleSteps = listOf(
                "O papel A4 é material de consumo: consome-se com o uso e possui durabilidade inferior a 2 anos.",
                "Os projetores multimídia são bens permanentes: vida útil superior a 2 anos, não se consomem no primeiro uso e integram o ativo permanente.",
                "Portanto, apenas os projetores multimídia devem receber número de patrimônio e plaqueta de tombamento."
            ),
            exampleAnswer = "Apenas os projetores multimídia sofrem tombamento, pois são bens permanentes (ativo imobilizado).",
            goldTip = "Bens permanentes exigem OBRIGATORIAMENTE três coisas na Cesgranrio: Cadastramento no sistema, Plaqueta de tombamento no chassi e Termo de Responsabilidade assinado pelo detentor da carga!"
        ),

        "tp_adm_m7_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m7_a2",
            intro = "O ciclo de vida dos bens patrimoniais compreende o inventário físico periódico, a movimentação entre setores, a conservação e a baixa patrimonial (desfazimento e alienação). Para a Cesgranrio, os pontos determinantes são os tipos de inventário patrimonial, os termos de transferência e a classificação de bens inservíveis (ociosos, recuperáveis, antieconômicos e irrecuperáveis) segundo o Decreto nº 9.373/2018.",
            theory = """
                1. Inventário Físico Patrimonial:
                Processo de verificação in loco da existência física, localização e estado de conservação de todos os bens permanentes cadastrados:
                • Finalidades: comprovar a existência física dos bens, verificar o estado de conservação, identificar bens extraviados, não cadastrados ou fora de uso, e conciliar o inventário físico com o registro contábil do ativo.
                • Modalidades:
                  - Inventário Inicial: realizado na implantação ou criação da unidade.
                  - Inventário Periódico / Anual: realizado ao final de cada exercício financeiro.
                  - Inventário Eventual: realizado por mudança de chefia detentora da carga patrimonial, auditoria especial ou extinção de setor.

                2. Movimentação e Transferência de Bens:
                • Nenhum bem permanente pode ser transferido de uma sala ou terminal para outro sem a emissão da Guia de Transferência / Termo de Movimentação.
                • A transferência transfere formalmente a responsabilidade pela guarda do bem do antigo detentor para o novo encarregado.

                3. Baixa Patrimonial e Classificação de Bens Inservíveis (Decreto nº 9.373/2018):
                Quando um bem não atende mais às necessidades da entidade, é classificado como Inservível nas seguintes categorias:
                • Ocioso: bem em perfeitas condições de uso, mas que não está sendo aproveitado na unidade em que se encontra (deve ser remanejado).
                • Recuperável: bem avariado cujo custo de recuperação não ultrapassa 50% do seu valor de mercado.
                • Antieconômico: bem cuja manutenção é excessivamente onerosa ou cujo rendimento é precário face ao custo operacional (recuperação superior a 50% do valor de mercado).
                • Irrecuperável (Sucata): bem que perdeu totalmente suas características funcionais e não pode ser consertado economicamente.
                • Formas de Desfazimento: Alienação (Venda em Leilão para a maior oferta, Doação para entidades públicas/sociais ou Permuta) e Descarte/Inutilização ambientalmente adequada de sucatas.
            """.trimIndent(),
            practicalApplication = """
                Ao desativar computadores antigos e substituí-los por novos modelos nos escritórios da Transpetro, uma comissão especial de desfazimento vistoria as máquinas antigas. Aquelas que ainda funcionam para tarefas básicas são classificadas como 'ociosas' e doadas a escolas públicas locais; aquelas com placas-mãe queimadas cujo reparo é inviável são classificadas como 'irrecuperáveis' e baixadas do patrimônio para venda em leilão de sucata eletrônica reciclável.
            """.trimIndent(),
            summary = """
                • Inventário patrimonial: verifica existência, localização, conservação e conciliação contábil.
                • Transferência: exige atualização documental do Termo de Responsabilidade.
                • Bens Inservíveis: Ocioso (funciona mas não é usado), Recuperável (conserto até 50%), Antieconômico (conserto > 50%), Irrecuperável (sucata).
                • Desfazimento: Leilão (alienação), Doação, Permuta ou Inutilização.
            """.trimIndent(),
            formulaName = "Critério de Classificação de Bens Inservíveis",
            formulaSyntax = "Custo_Reparo ≤ 50% Valor_Mercado → Recuperável   |   Custo_Reparo > 50% Valor_Mercado → Antieconômico",
            formulaTerms = listOf(
                "Ocioso = Perfeitas condições, sem uso atual no setor",
                "Antieconômico = Manutenção cara demais frente ao valor residual",
                "Irrecuperável = Perda total de utilidade, destinado a descarte/leilão de sucata"
            ),
            formulaWhenToUse = "Utilizar para categorizar bens patrimoniais destinados a alienação, doação ou desfazimento.",
            exampleTitle = "Classificação de Bem Inservível",
            exampleProblem = "Uma caminhonete de transporte de equipe de manutenção sofreu avarias severas em um acidente. O valor de mercado de um veículo similar é de R$ 60.000,00 e o orçamento oficial de conserto das avarias mecânicas e estruturais totalizou R$ 42.000,00. Segundo os critérios legais de administração patrimonial, como esse bem deve ser classificado?",
            exampleSteps = listOf(
                "Calcular a proporção entre o custo de recuperação e o valor de mercado: 42.000 / 60.000 = 0,70 = 70%.",
                "Pela norma legal, o limite para considerar um bem recuperável é de até 50% do seu valor de mercado.",
                "Como o custo de reparo atinge 70% (superando 50%), o conserto é financeiramente inviável.",
                "Portanto, o bem deve ser classificado tecnicamente como Bem Antieconômico."
            ),
            exampleAnswer = "Bem Antieconômico (custo de reparo supera 50% do valor de mercado).",
            goldTip = "Regra de ouro cobrada na Cesgranrio: custo de conserto de até 50% do valor de mercado = RECUPERÁVEL. Custo de conserto acima de 50% do valor de mercado = ANTIECONÔMICO!"
        ),

        // =====================================================================
        // MÓDULO 8: COMPRAS E CONTRATOS
        // =====================================================================
        "tp_adm_m8_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m8_a1",
            intro = "O planejamento das contratações é a etapa mais crítica do processo de suprimentos. Contratações mal planejadas geram atrasos, superfaturamentos, aditivos desnecessários e falhas operacionais. A Cesgranrio cobra com rigor os documentos fundamentais da fase preparatória: o Estudo Técnico Preliminar (ETP), o Termo de Referência (TR) / Projeto Básico, a Matriz de Riscos e a estimativa orçamentária prévia.",
            theory = """
                1. A Fase Preparatória (Planejamento da Contratação):
                É a etapa interna onde a administração identifica sua real necessidade, avalia soluções de mercado e desenha a contratação antes de publicar qualquer edital público:
                • Documento de Formalização de Demanda (DFD): emitido pelo setor usuário solicitando a compra.
                • Estudo Técnico Preliminar (ETP):
                  - Documento que estuda as diferentes alternativas de mercado para atender à necessidade corporativa, analisando viabilidade técnica, econômica e ambiental, e recomendando a melhor solução.
                  - Demonstra se é melhor comprar, alugar ou terceirizar o serviço.

                2. Termo de Referência (TR) e Projeto Básico (PB):
                Documento técnico central que traduz a solução escolhida e baliza toda a licitação e o futuro contrato:
                • Elementos Obrigatórios do TR:
                  - Definição precisa e clara do objeto (especificação por desempenho).
                  - Justificativa da necessidade da contratação.
                  - Modelo de execução do serviço ou de fornecimento dos bens.
                  - Critérios de medição e pagamento vinculados a resultados.
                  - Modelo de gestão e fiscalização contratual.
                  - Obrigações do contratante e da contratada.
                  - Estimativa do valor de referência baseada em rigorosa pesquisa de preços.
                  - Adequação orçamentária (declaração de que há verba disponível para pagar).

                3. Matriz de Alocação de Riscos:
                Cláusula que define antecipadamente quem responde por cada risco durante a execução contratual (ex.: riscos de oscilação cambial, variações geológicas no solo ao cavar o duto, greves portuárias ou inflação).
            """.trimIndent(),
            practicalApplication = """
                Para contratar uma empresa especializada em dragagem do canal de acesso aquaviário de um porto da Transpetro, a equipe de engenharia e suprimentos elabora um ETP avaliando se a dragagem deve ser por sucção ou mecânica. Em seguida, redige o Termo de Referência estipulando que o pagamento será feito por metro cúbico de sedimento efetivamente retirado (medição batimétrica) e define na Matriz de Riscos que licenças ambientais cabem à estatal e tempestades marítimas são risco compartilhado.
            """.trimIndent(),
            summary = """
                • ETP: estuda as alternativas técnicas e econômicas viáveis de mercado para atender à demanda.
                • Termo de Referência (TR): documento obrigatório que define objeto, medição, obrigações e orçamento.
                • Especificação técnica: deve ser clara, impessoal e orientada a padrões de desempenho sem direcionamento.
                • Matriz de riscos: distribui responsabilidades e contingências financeiras entre estatal e contratado.
            """.trimIndent(),
            formulaName = "Estrutura Sequencial da Fase Preparatória",
            formulaSyntax = "DFD (Demanda) → ETP (Viabilidade) → Matriz de Riscos → Termo de Referência (TR) → Orçamento",
            formulaTerms = listOf(
                "ETP = Evidencia o problema societário e a melhor solução técnica",
                "TR = Contém todos os parâmetros necessários para o edital e a fiscalização",
                "Orçamento Estimado = Balizador do valor máximo da contratação"
            ),
            formulaWhenToUse = "Utilizar para fundamentar a legalidade e a conformidade técnica da fase interna das contratações públicas e estatais.",
            exampleTitle = "Identificação de Documento da Fase Preparatória",
            exampleProblem = "Qual documento da fase interna de planejamento tem como finalidade primordial evidenciar o problema a ser resolvido e a melhor solução dentre as alternativas possíveis, servindo de base para a posterior elaboração do Termo de Referência?",
            exampleSteps = listOf(
                "O Termo de Referência detalha a solução já escolhida.",
                "O documento que estuda preliminarmente as alternativas técnicas, compara cenários de mercado e comprova a viabilidade é o Estudo Técnico Preliminar (ETP).",
                "O ETP precede obrigatoriamente a elaboração do TR."
            ),
            exampleAnswer = "Estudo Técnico Preliminar (ETP).",
            goldTip = "Em provas de contratações: o ETP avalia a VIABILIDADE e compara ALTERNATIVAS; o Termo de Referência (TR) define e detalha as REGRAS e o OBJETO da solução que foi escolhida no ETP!"
        ),

        "tp_adm_m8_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m8_a2",
            intro = "A execução dos contratos exige acompanhamento sistemático para garantir que a empresa receba com qualidade tudo aquilo que foi contratado. A Cesgranrio cobra a distinção crucial entre a figura do Gestor do Contrato e a do Fiscal do Contrato, o registro de ocorrências no Livro de Registro, os critérios de medição, a aplicação de glosas e as penalidades contratuais.",
            theory = """
                1. Diferença entre Gestor do Contrato e Fiscal do Contrato:
                • Gestor do Contrato: autoridade administrativa responsável pela coordenação macro do contrato:
                  - Atribuições: controle financeiro e orçamentário, tramitação de aditivos contratuais de prazo e valor, reajustes e repactuações de preços, prorrogação de vigência e instauração formal de processos administrativos punitivos.
                • Fiscal do Contrato (Fiscal Técnico / Administrativo / Setorial): profissional que atua no local físico de execução:
                  - Atribuições: conferir a execução prática diária do objeto, verificar o cumprimento das especificações técnicas, atestar medições físicas, fiscalizar o recolhimento de encargos trabalhistas e previdenciários dos terceirizados (fiscalização administrativa para evitar responsabilidade subsidiária) e registrar ocorrências no livro próprio.

                2. Medição, Atesto e Aplicação de Glosas:
                • Medição: apuração quantitativa e qualitativa dos serviços efetivamente prestados no período (ex.: horas trabalhadas, metros soldados).
                • Atesto: declaração formal na Nota Fiscal de que o serviço foi prestado a contento.
                • Glosa Contratual: desconto financeiro efetuado na fatura quando a contratada deixa de prestar parte dos serviços contratados ou os executa em padrão inferior ao acordado. A glosa NÃO é penalidade; é ajuste de pagamento para remunerar apenas o que foi efetivamente entregue.

                3. Sanções Administrativas Contratuais:
                Em caso de inadimplemento culposo da contratada, a estatal pode aplicar sanções proporcionais:
                • Advertência escrita (faltas leves sem prejuízo).
                • Multa compensatória ou moratória (descontada de pagamentos ou da garantia).
                • Suspensão temporária de participação em licitação e impedimento de contratar com a própria estatal (por até 2 anos na Lei 13.303/2016).
            """.trimIndent(),
            practicalApplication = """
                Em um contrato de manutenção de tubulações em um terminal da Transpetro:
                - O Fiscal do Contrato acompanha a obra em campo, usa o paquímetro para medir a espessura da solda, atesta o boletim de medição mensal e confere se a terceirizada pagou o FGTS dos soldadores. Se a empresa contratada faltou a 20 horas de serviço, o fiscal aplica uma glosa proporcional de valor na medição.
                - O Gestor do Contrato, no escritório central, processa a fatura glosada para pagamento e elabora o termo aditivo se for necessário estender o prazo de execução da obra.
            """.trimIndent(),
            summary = """
                • Gestor: coordenação administrativa, aditivos, orçamento, prorrogações e penalidades formais.
                • Fiscal: acompanhamento técnico in loco no dia a dia, medições físicas e fiscalização trabalhista.
                • Glosa: desconto financeiro na nota por serviço não executado ou com defeito (não é sanção penal).
                • Sanções: advertência, multa e suspensão de licitar/contratar por até 2 anos com a estatal.
            """.trimIndent(),
            formulaName = "Divisão de Atribuições Contratuais",
            formulaSyntax = "Fiscal (In Loco / Técnico / Medição) vs. Gestor (Administrativo / Financeiro / Aditivos)",
            formulaTerms = listOf(
                "Fiscalização Técnica = Medir e atestar a qualidade física dos serviços prestados",
                "Gestão do Contrato = Gerenciar vigência, saldos orçamentários e aditivos contratuais",
                "Glosa = Ajuste da fatura abatendo serviços não prestados"
            ),
            formulaWhenToUse = "Utilizar para delimitar responsabilidades funcionais e evitar nulidades na fiscalização contratual.",
            exampleTitle = "Diferenciação entre Fiscalização e Gestão",
            exampleProblem = "Durante a execução de um serviço de pintura de tanques, quem tem a competência técnica para acompanhar a espessura da tinta na superfície, registrar as inconformidades diárias e atestar a medição física para liberação da fatura?",
            exampleSteps = listOf(
                "A atividade descrita envolve o acompanhamento prático, rotineiro e in loco da execução do objeto.",
                "Não envolve alteração jurídica do contrato, prorrogação de vigência nem gerenciamento orçamentário macro.",
                "Portanto, trata-se de atribuição direta do Fiscal do Contrato (especificamente o Fiscal Técnico)."
            ),
            exampleAnswer = "O Fiscal do Contrato (Fiscal Técnico).",
            goldTip = "Para não errar na prova: quem fica no chão de fábrica e mede a obra é o FISCAL; quem fica no escritório assinando aditivo e controlando o orçamento do contrato é o GESTOR!"
        ),

        // =====================================================================
        // MÓDULO 9: LEGISLAÇÃO APLICADA, ÉTICA E REDAÇÃO OFICIAL
        // =====================================================================
        "tp_adm_m9_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m9_a1",
            intro = "A Lei nº 13.303/2016 (Estatuto Jurídico das Empresas Estatais) disciplina a governança, transparência, licitações e contratos das empresas públicas e sociedades de economia mista no Brasil. Na prova da Fundação Cesgranrio para a Transpetro, o Título II da lei (artigos 28 ao 91) é o tema mais cobrado de toda a disciplina de legislação, especialmente os princípios, as hipóteses de Dispensa (Art. 29) e Inexigibilidade de Licitação (Art. 30), e as compras para atividade-fim (oportunidade de negócio).",
            theory = """
                1. Abrangência e Princípios da Lei nº 13.303/2016:
                • Aplica-se a empresas públicas, sociedades de economia mista e suas subsidiárias (União, Estados, DF e Municípios).
                • Princípios: impessoalidade, moralidade, igualdade, publicidade, eficiência, probidade administrativa, economicidade, desenvolvimento sustentável, vinculação ao instrumento convocatório e obtenção da proposta mais vantajosa (considerando o ciclo de vida do objeto).
                • Regulamento Interno: cada estatal deve editar seu próprio Regulamento de Licitações e Contratos (RLIC).

                2. Não Aplicação da Licitação para Atividade-Fim (Art. 28, § 3º, II):
                É dispensável a realização de licitação nos casos de comercialização, prestação ou execução, de forma direta, de produtos, serviços ou obras especificamente relacionados com seu objeto social (oportunidade de negócio), preservando a agilidade comercial da estatal frente a concorrentes privados.

                3. Licitação Dispensável (Dispensa de Licitação - Art. 29):
                A competição é teoricamente viável, mas a lei autoriza a contratação direta por razões de política pública ou conveniência:
                • Pequeno Valor: obras e serviços de engenharia até R$ 100.000,00 e outros serviços e compras até R$ 50.000,00 (valores atualizados periodicamente por decreto).
                • Emergência ou Calamidade Pública: quando caracterizada urgência de atendimento de situação que possa ocasionar prejuízo ou comprometer a segurança de pessoas, obras, serviços e equipamentos (limitada a bens e serviços necessários para afastar o perigo).
                • Licitação Deserta ou Fracassada: quando não acudiram interessados ou todos foram desclassificados e a repetição for prejudicial.
                • Locação ou Aquisição de Imóvel: cujas características de localização e instalações tornem necessária a escolha.

                4. Inexigibilidade de Licitação (Inviabilidade de Competição - Art. 30):
                A licitação é impossível porque NÃO HÁ COMO COMPETIR:
                • Fornecedor Exclusivo: aquisição de materiais ou equipamentos que só possam ser fornecidos por produtor, empresa ou representante comercial exclusivo (vedada preferência de marca).
                • Serviços Técnicos Especializados de Natureza Singular: contratação de serviços técnicos com profissionais ou empresas de notória especialização (vedada a inexigibilidade para serviços de publicidade e divulgação).
            """.trimIndent(),
            practicalApplication = """
                Na rotina da Transpetro:
                - Inexigibilidade: contratar o único fabricante mundial que detém a patente exclusiva de um sensor ultrassônico de inspeção interna de dutos (Art. 30, I).
                - Dispensa: contratar imediatamente um serviço de contenção e bombeamento de urgência após o rompimento acidental de um poliduto por deslizamento de encosta (Art. 29, II - emergência).
                - Pregão Eletrônico: contratar o fornecimento de combustíveis e uniformes comuns para equipes de apoio (bens comuns, Art. 32, IV).
            """.trimIndent(),
            summary = """
                • Lei 13.303/2016: Estatuto das Estatais aplicável à Transpetro.
                • Inexigibilidade (Art. 30): inviabilidade de competição (fornecedor exclusivo, notória especialização). Rol exemplificativo.
                • Dispensa (Art. 29): competição viável, mas dispensada por lei (pequeno valor, emergência, deserta). Rol taxativo.
                • Atividade-fim (Art. 28): não se exige licitação para comercialização direta do objeto social da empresa.
            """.trimIndent(),
            formulaName = "Distinção Jurídica Fundamental",
            formulaSyntax = "Inexigibilidade = Inviabilidade de Competição (Exclusivo/Notório)   |   Dispensa = Competição Possível mas Dispensada pela Lei",
            formulaTerms = listOf(
                "Art. 30 = Fornecedor exclusivo e serviço técnico com notória especialização",
                "Art. 29 = Emergência, pequeno valor, licitação deserta, compra de imóvel",
                "Pregão Eletrônico = Modalidade preferencial para bens e serviços comuns"
            ),
            formulaWhenToUse = "Utilizar para classificar casos concretos de contratação direta na administração pública e estatais.",
            exampleTitle = "Classificação de Contratação Direta",
            exampleProblem = "A Transpetro precisa adquirir peças sobressalentes para uma turbina náutica específica. A fabricante suíça é a detentora exclusiva da patente mundial de fabricação e não possui representantes concorrentes no país, fato comprovado por atestado da Junta Comercial. Qual é o fundamento jurídico legal para essa contratação direta?",
            exampleSteps = listOf(
                "Verificar se há viabilidade de competição: existe apenas um único produtor/fornecedor capaz de fornecer o bem.",
                "A ausência de competidores caracteriza inviabilidade jurídica e fática de competição.",
                "Pela Lei nº 13.303/2016 (Art. 30, I), a aquisição de materiais com fornecedor exclusivo constitui hipótese clássica de Inexigibilidade de Licitação."
            ),
            exampleAnswer = "Inexigibilidade de Licitação (Artigo 30, inciso I, da Lei nº 13.303/2016).",
            goldTip = "Cai SEMPRE na Cesgranrio: Se for FORNECEDOR EXCLUSIVO ou PROFISSIONAL DE NOTÓRIA ESPECIALIZAÇÃO, é INEXIGIBILIDADE! Se for EMERGÊNCIA, PEQUENO VALOR ou LICITAÇÃO DESERTA, é DISPENSA!"
        ),

        "tp_adm_m9_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m9_a2",
            intro = "O regime dos contratos administrativos e empresariais possui nuances essenciais entre a Lei nº 13.303/2016 (Lei das Estatais) e a Nova Lei de Licitações (Lei nº 14.133/2021). A Cesgranrio exige que o candidato compreenda com precisão o âmbito de aplicação de cada diploma e as regras de prazos, alterações contratuais e equilíbrio econômico-financeiro.",
            theory = """
                1. Campo de Aplicação: Lei nº 13.303/2016 versus Lei nº 14.133/2021:
                • Lei nº 14.133/2021 (Nova Lei de Licitações): aplica-se obrigatoriamente à Administração Direta (Ministérios, Secretarias), autarquias (ANP, IBAMA) e fundações públicas. É EXPRESSAMENTE NÃO APLICÁVEL às empresas públicas e sociedades de economia mista (como Transpetro, Petrobras e Banco do Brasil), ressalvadas apenas as normas de Direito Penal (crimes em licitações).
                • Lei nº 13.303/2016 (Estatuto das Estatais): rege as licitações e os contratos celebrados pelas estatais e suas subsidiárias, garantindo maior flexibilidade empresarial e negocial.

                2. Regime Contratual na Lei nº 13.303/2016:
                • Duração dos Contratos: a regra geral nas estatais estabelece que a duração dos contratos não excederá a 5 anos (Art. 71). Exceções permitem prazos mais longos para projetos contemplados no plano de negócios ou quando a pactuação por prazo superior for praxe de mercado.
                • Alterações Contratuais (Aditivos de Valor - Art. 81):
                  - A contratada fica obrigada a aceitar, nas mesmas condições contratuais, acréscimos ou supressões de até 25% do valor inicial atualizado do contrato (ou até 50% para reforma de edifícios ou equipamentos).
                  - É expressamente vedada a celebração de aditivos com efeitos retroativos.
                • Cláusulas Exorbitantes: embora inspirados no direito privado, os contratos das estatais mantêm prerrogativas de fiscalização, aplicação de penalidades, rescisão unilateral por interesse público e exigência de garantias.
                • Equilíbrio Econômico-Financeiro (Recomposição / Reequilíbrio): direito fundamental da contratada caso ocorram fatos imprevisíveis ou de consequências incalculáveis (álea extraordinária) que tornem a execução excessivamente onerosa.
            """.trimIndent(),
            practicalApplication = """
                Se a Transpetro contratar uma empresa de navegação de cabotagem por R$ 10 milhões para transporte de combustíveis e houver necessidade de expandir a rota em 20%, o Gestor de Contratos lavra um Termo Aditivo de acréscimo de R$ 2 milhões (respeitando o limite legal de 25% previsto no Art. 81 da Lei 13.303/2016). A empresa contratada é legalmente obrigada a aceitar essa alteração nas mesmas bases de preço unitário pactuadas.
            """.trimIndent(),
            summary = """
                • Lei 14.133/2021 NÃO se aplica às estatais (salvo crimes). As estatais regem-se pela Lei 13.303/2016.
                • Limites de aditivos: acréscimos ou supressões de até 25% (ou 50% para reformas).
                • Duração contratual: prazo de até 5 anos como regra geral na Lei das Estatais.
                • Reequilíbrio econômico-financeiro: assegura recomposição em caso de álea extraordinária imprevisível.
            """.trimIndent(),
            formulaName = "Limites Legais de Alteração Contratual (Lei 13.303/2016)",
            formulaSyntax = "Acréscimos / Supressões ≤ 25% (Geral)   |   Até 50% (Reformas de Equipamentos/Edifícios)",
            formulaTerms = listOf(
                "Base de Cálculo = Valor inicial atualizado do contrato",
                "Obrigatoriedade = O contratado é obrigado a aceitar as alterações unilaterais dentro desses percentuais",
                "Aditivos com efeito retroativo = Expressamente proibidos"
            ),
            formulaWhenToUse = "Utilizar para auditar a legalidade de aditivos contratuais de valor em compras e serviços corporativos.",
            exampleTitle = "Cálculo do Limite Máximo de Aditivo de Valor",
            exampleProblem = "Um contrato de prestação de serviços de apoio operacional firmado pela Transpetro tem o valor inicial atualizado de R$ 4.000.000,00. A administração deseja acrescer o volume de serviços prestados. Qual é o valor monetário máximo que pode ser acrescido unilateralmente ao contrato nos termos da Lei nº 13.303/2016?",
            exampleSteps = listOf(
                "Identificar a natureza do serviço: serviço comum de apoio operacional (não se trata de reforma de prédio ou equipamento).",
                "Identificar o limite percentual legal aplicável: até 25% do valor inicial atualizado.",
                "Calcular o valor máximo do aditivo: R$ 4.000.000,00 × 0,25 = R$ 1.000.000,00.",
                "Portanto, o contrato pode ser acrescido em até R$ 1.000.000,00."
            ),
            exampleAnswer = "O acréscimo máximo permitido por lei é de R$ 1.000.000,00 (25%).",
            goldTip = "Pegadinha de prova: a Nova Lei de Licitações (Lei 14.133/2021) NÃO se aplica à Transpetro nem à Petrobras! Se a questão perguntar qual lei disciplina as licitações de estatais, a resposta correta é a LEI Nº 13.303/2016!"
        ),

        "tp_adm_m9_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m9_a3",
            intro = "A integridade corporativa e a qualidade da comunicação pública são valores inegociáveis. O concurso da Cesgranrio exige o conhecimento das diretrizes de ética pública, prevenção a conflitos de interesses (Lei nº 12.813/2013) e os atributos fundamentais da Redação Oficial consoante a 3ª edição do Manual de Redação da Presidência da República (clareza, precisão, concisão, impessoalidade, formalidade e o padrão ofício unificado).",
            theory = """
                1. Ética, Integridade e Prevenção de Conflitos de Interesses:
                • Ética Pública: o agente público deve pautar sua conduta pelo princípio da moralidade administrativa, agindo com honestidade, lealdade institucional, probidade e transparência. O decoro da função deve ser preservado tanto dentro quanto fora do local de trabalho.
                • Conflito de Interesses (Lei nº 12.813/2013): situação gerada pelo confronto entre interesses públicos e privados, que possa comprometer o interesse coletivo ou influenciar de maneira imprópria o desempenho da função pública.
                  - Configura conflito: divulgar ou utilizar informação privilegiada em benefício próprio ou de terceiros; prestar serviços a empresas fiscalizadas ou contratadas pela estatal; aceitar presentes ou favores que possam criar obrigação de reciprocidade.

                2. Atributos da Redação Oficial (Manual da Presidência da República - 3ª Edição, 2018):
                A redação oficial é a maneira pela qual o Poder Público redige seus atos normativos e expedientes:
                • Clareza e Precisão: clareza é a qualidade de ser imediatamente compreendido pelo leitor sem ambiguidades; precisão é o uso do vocabulário exato que traduz exatamente o pensamento sem termos vagos.
                • Concisão: transmitir o máximo de informações com o mínimo de palavras necessárias (economia linguística), cortando redundâncias, prolixidades e clichês burocráticos.
                • Objetividade: ir direto ao ponto de forma estruturada, sem rodeios ou floreios poéticos.
                • Impessoalidade: o emissor é o serviço público, o destinatário é o cidadão ou outro órgão público, e o assunto é estritamente de interesse público (ausência de opiniões pessoais, subjetividades e autoelogios).
                • Formalidade e Padronização: respeito à norma culta da língua portuguesa, regras de civilidade e diagramação uniforme do documento.

                3. Consolidação do Padrão Ofício (3ª Edição do Manual):
                • Extinção das formas Aviso e Memorando. Tudo foi unificado sob a denominação única de OFÍCIO.
                • Fechos para Comunicações Oficiais: restam apenas dois fechos:
                  - 'Respeitosamente,' para autoridades de hierarquia SUPERIOR (inclusive Presidente da República).
                  - 'Atenciosamente,' para autoridades de MESMA hierarquia, hierarquia INFERIOR ou para particulares.
            """.trimIndent(),
            practicalApplication = """
                Um assistente administrativo da Transpetro precisa elaborar uma comunicação interna formal solicitando manutenção em uma válvula:
                - Redação Oficial: redige um Ofício em norma culta, claro, impessoal e conciso, sem gírias nem rodeios, utilizando o fecho 'Atenciosamente,' se endereçado a colega do mesmo nível ou 'Respeitosamente,' se endereçado ao Diretor Executivo.
                - Ética: guarda sigilo absoluto sobre os relatórios de vazão a que tem acesso, jamais repassando dados a terceiros ou empresas de consultoria concorrentes.
            """.trimIndent(),
            summary = """
                • Ética e Conflito de Interesses: primazia do interesse público, probidade e vedação ao uso de informação privilegiada.
                • Atributos da Redação Oficial: Clareza, Precisão, Concisão, Objetividade, Impessoalidade e Formalidade.
                • Padrão Ofício unificado (aviso e memorando foram extintos).
                • Fechos: 'Respeitosamente,' (autoridades superiores) e 'Atenciosamente,' (mesma hierarquia, inferior ou particular).
            """.trimIndent(),
            formulaName = "Regras de Fechos da Redação Oficial (Manual da PR - 3ª Edição)",
            formulaSyntax = "Hierarquia Superior → 'Respeitosamente,'   |   Mesma Hierarquia / Inferior / Particulares → 'Atenciosamente,'",
            formulaTerms = listOf(
                "Respeitosamente, = Presidente da República, Ministros, Diretores hierarquicamente superiores",
                "Atenciosamente, = Pares do mesmo nível funcional, subordinados e cidadãos em geral",
                "Proibição = Termos ultrapassados como 'Cordialmente' ou 'Sem mais para o momento, renovamos protestos de estima'"
            ),
            formulaWhenToUse = "Utilizar para selecionar o fecho correto em correspondências e atos oficiais da administração.",
            exampleTitle = "Escolha de Fecho em Redação Oficial",
            exampleProblem = "Um profissional da Transpetro elaborou um ofício técnico endereçado ao Presidente da República prestando informações sobre o transporte de combustíveis na costa brasileira. De acordo com o Manual de Redação da Presidência da República (3ª edição), qual é o fecho de cortesia correto a ser utilizado?",
            exampleSteps = listOf(
                "Identificar a hierarquia do destinatário: o Presidente da República é autoridade hierarquicamente superior.",
                "Consultar as regras oficiais de fechos para expedientes: para autoridades superiores, utiliza-se exclusivamente o fecho 'Respeitosamente,'.",
                "Para autoridades de mesma hierarquia ou particulares, utiliza-se 'Atenciosamente,'.",
                "Portanto, o único fecho admitido para o Presidente é 'Respeitosamente,'."
            ),
            exampleAnswer = "Respeitosamente,",
            goldTip = "Cai em todas as provas da Cesgranrio sobre redação oficial: hoje só existem DOIS fechos oficiais: RESPEITOSAMENTE (para superiores) e ATENCIOSAMENTE (para iguais, inferiores ou particulares). Qualquer outro fecho ('Cordialmente', 'Saudações') está ERRADO!"
        )

        else -> null
    }

    fun getExercises(lessonId: String): List<MathExercise> {
        return when (lessonId) {
            "tp_adm_m6_a1" -> exercisesM6A1()
            "tp_adm_m6_a2" -> exercisesM6A2()
            "tp_adm_m7_a1" -> exercisesM7A1()
            "tp_adm_m7_a2" -> exercisesM7A2()
            "tp_adm_m8_a1" -> exercisesM8A1()
            "tp_adm_m8_a2" -> exercisesM8A2()
            "tp_adm_m9_a1" -> exercisesM9A1()
            "tp_adm_m9_a2" -> exercisesM9A2()
            "tp_adm_m9_a3" -> exercisesM9A3()
            else -> emptyList()
        }
    }

    private fun exercisesM6A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-37",
            lessonId = "tp_adm_m6_a1",
            statement = "A manutenção industrial que realiza o monitoramento contínuo das condições mecânicas e elétricas dos equipamentos em pleno funcionamento, utilizando técnicas como análise de vibrações, termografia infravermelha e análise de lubrificantes para intervir apenas quando há sintomas reais de degradação, é a:",
            options = listOf(
                "A) Manutenção Corretiva Não Planejada",
                "B) Manutenção Preventiva por Horímetro",
                "C) Manutenção Preditiva (Baseada na Condição)",
                "D) Manutenção Corretiva Emergencial",
                "E) Manutenção Reativa Total"
            ),
            correctIndex = 2,
            steps = listOf(
                "Monitoramento contínuo por sensores e termografia com o equipamento operando define a Manutenção Preditiva.",
                "Ela programa a intervenção no momento exato antes da falha ocorrer, aproveitando ao máximo a vida útil das peças."
            ),
            finalComment = "Manutenção Preditiva monitora a condição física real do maquinário sem desmontá-lo."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-38",
            lessonId = "tp_adm_m6_a1",
            statement = "Qual modalidade de manutenção acarreta os maiores custos financeiros e riscos operacionais para uma empresa de transporte de combustíveis, por ocorrer sem aviso prévio e interromper a produção de forma intempestiva?",
            options = listOf(
                "A) Manutenção Preditiva",
                "B) Manutenção Preventiva Programada",
                "C) Manutenção Corretiva Não Planejada (Emergencial)",
                "D) Manutenção Autônoma",
                "E) Manutenção Corretiva Planejada"
            ),
            correctIndex = 2,
            steps = listOf(
                "A corretiva emergencial decorre da quebra inesperada em plena atividade.",
                "Provoca custos elevadíssimos de lucros cessantes, horas extras emergenciais, risco de acidentes e danos colaterais a outras peças."
            ),
            finalComment = "A manutenção corretiva emergencial é a mais custosa e prejudicial à operação."
        )
    )

    private fun exercisesM6A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-39",
            lessonId = "tp_adm_m6_a2",
            statement = "Uma turbina operou ativamente por 1.200 horas em determinado período e sofreu 4 paradas por defeito mecânico. O MTBF (Tempo Médio Entre Falhas) dessa turbina foi de:",
            options = listOf(
                "A) 250 horas",
                "B) 300 horas",
                "C) 350 horas",
                "D) 400 horas",
                "E) 450 horas"
            ),
            correctIndex = 1,
            steps = listOf(
                "MTBF = Tempo Total de Operação / Número de Falhas.",
                "MTBF = 1.200 horas / 4 falhas = 300 horas entre falhas."
            ),
            finalComment = "O MTBF de 300 horas indica o tempo médio em que a turbina opera continuamente sem quebrar."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-40",
            lessonId = "tp_adm_m6_a2",
            statement = "Se um equipamento apresenta um MTBF de 190 horas e um MTTR (Tempo Médio Para Reparo) de 10 horas, qual é a sua Disponibilidade Operacional Inerente?",
            options = listOf(
                "A) 90%",
                "B) 92,5%",
                "C) 95%",
                "D) 97,5%",
                "E) 98%"
            ),
            correctIndex = 2,
            steps = listOf(
                "Fórmula da Disponibilidade: D = MTBF / (MTBF + MTTR).",
                "D = 190 / (190 + 10) = 190 / 200 = 0,95.",
                "Multiplicar por 100% = 95%."
            ),
            finalComment = "A disponibilidade calculada pela relação MTBF / (MTBF + MTTR) é de exatamente 95%."
        )
    )

    private fun exercisesM7A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-41",
            lessonId = "tp_adm_m7_a1",
            statement = "O procedimento formal pelo qual um bem permanente adquirido por uma estatal é cadastrado no sistema corporativo, recebe uma plaqueta numerada de patrimônio e tem sua carga vinculada a um servidor por meio de Termo de Responsabilidade é denominado:",
            options = listOf(
                "A) Desfazimento",
                "B) Tombamento",
                "C) Glosa Patrimonial",
                "D) Amortização",
                "E) Baixa de Ativo"
            ),
            correctIndex = 1,
            steps = listOf(
                "Tombamento é o ato de registro, identificação física com plaqueta e atribuição formal de responsabilidade pela guarda de bem permanente."
            ),
            finalComment = "O tombamento incorpora oficialmente o bem ao patrimônio da entidade."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-42",
            lessonId = "tp_adm_m7_a1",
            statement = "Assinale a opção que apresenta exclusivamente exemplos de Bens Móveis Permanentes que devem sofrer processo de tombamento patrimonial:",
            options = listOf(
                "A) Resmas de papel A4, toners de tinta e copos descartáveis.",
                "B) Óleo lubrificante, estopas de limpeza e filtros descartáveis de combustível.",
                "C) Notebooks corporativos, empilhadeiras elétricas e bombas hidráulicas de bancada.",
                "D) Terrenos de faixas de duto e prédios administrativos.",
                "E) Parafusos galvanizados, porcas e fitas isolantes."
            ),
            correctIndex = 2,
            steps = listOf(
                "Bens permanentes móveis possuem vida útil superior a 2 anos e não perdem a identidade física com o uso: computadores, empilhadeiras e bombas industriais.",
                "Terrenos são bens imóveis; papéis e óleos são materiais de consumo."
            ),
            finalComment = "Notebooks, empilhadeiras e bombas são bens móveis permanentes sujeitos a tombamento."
        )
    )

    private fun exercisesM7A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-43",
            lessonId = "tp_adm_m7_a2",
            statement = "Nos termos do Decreto nº 9.373/2018 sobre desfazimento de bens públicos e estatais, o bem permanente que se encontra em perfeitas condições de uso e funcionamento, mas que não está sendo aproveitado na unidade em que se acha lotado, deve ser classificado tecnicamente como:",
            options = listOf(
                "A) Bem Antieconômico",
                "B) Bem Irrecuperável",
                "C) Bem Ocioso",
                "D) Bem de Consumo Imediato",
                "E) Bem Descartável"
            ),
            correctIndex = 2,
            steps = listOf(
                "Ocioso: bem que funciona perfeitamente, mas não tem uso no local onde está.",
                "Antieconômico: reparo custa mais de 50% do valor.",
                "Irrecuperável: sucata sem condições de conserto."
            ),
            finalComment = "Bem em perfeitas condições sem uso na unidade é classificado como Bem Ocioso."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-44",
            lessonId = "tp_adm_m7_a2",
            statement = "Quando um equipamento permanente apresenta danos cuja despesa de recuperação ultrapassa 50% do seu valor de mercado ou cuja manutenção é excessivamente onerosa face ao rendimento prestado, ele deve ser classificado formalmente como:",
            options = listOf(
                "A) Bem Recuperável",
                "B) Bem Ocioso",
                "C) Bem Antieconômico",
                "D) Bem Próprio de Uso Comum",
                "E) Bem Imaterial"
            ),
            correctIndex = 2,
            steps = listOf(
                "Se o conserto for de até 50% do valor de mercado, o bem é 'Recuperável'.",
                "Se o conserto for superior a 50% do valor de mercado, o bem é 'Antieconômico'."
            ),
            finalComment = "Reparo superior a 50% do valor de mercado caracteriza o Bem Antieconômico."
        )
    )

    private fun exercisesM8A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-45",
            lessonId = "tp_adm_m8_a1",
            statement = "No planejamento das contratações públicas e estatais, o documento que reúne a especificação detalhada do objeto por desempenho, a justificativa da contratação, os critérios de aceitação e medição, e a estimativa orçamentária é o:",
            options = listOf(
                "A) Termo de Referência (TR)",
                "B) Termo de Posse",
                "C) Relatório de Auditoria Externa",
                "D) Auto de Infração Fiscal",
                "E) Manifesto de Carga"
            ),
            correctIndex = 0,
            steps = listOf(
                "O Termo de Referência (TR) é o documento técnico obrigatório da fase preparatória que baliza a licitação e o contrato, definindo o objeto, obrigações e medição."
            ),
            finalComment = "O Termo de Referência (TR) estrutura todos os parâmetros técnicos e contratuais do objeto a ser contratado."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-46",
            lessonId = "tp_adm_m8_a1",
            statement = "A ferramenta contratual que define previamente a divisão de riscos entre a estatal contratante e a empresa contratada, estipulando quem arcará com os impactos financeiros de eventos futuros incertos durante a execução da obra ou serviço, é denominada:",
            options = listOf(
                "A) Matriz de Alocação de Riscos",
                "B) Parecer Jurídico Vinculante",
                "C) Tabela de Depreciação Linear",
                "D) Guia de Recolhimento do FGTS",
                "E) Ordem de Fornecimento"
            ),
            correctIndex = 0,
            steps = listOf(
                "A Matriz de Alocação de Riscos reparte responsabilidades e riscos operacionais e econômicos entre as partes contratantes."
            ),
            finalComment = "A Matriz de Alocação de Riscos distribui os encargos de potenciais eventos futuros entre as partes."
        )
    )

    private fun exercisesM8A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-47",
            lessonId = "tp_adm_m8_a2",
            statement = "Em relação à fiscalização e gestão de contratos na Administração Pública e em empresas estatais, constitui atribuição precípua do Fiscal do Contrato:",
            options = listOf(
                "A) Assinar termos aditivos de prorrogação de vigência e negociar reajustes financeiros corporativos.",
                "B) Acompanhar in loco a execução física do objeto, conferir o cumprimento das obrigações técnicas e atestar medições para pagamento.",
                "C) Instaurar processos punitivos para declarar a inidoneidade de licitantes.",
                "D) Aprovar o orçamento anual de investimentos da companhia.",
                "E) Autorizar transferências bancárias internacionais sem aval da diretoria."
            ),
            correctIndex = 1,
            steps = listOf(
                "O Fiscal do Contrato acompanha o dia a dia da obra/serviço, mede as quantidades e atesta a execução física.",
                "O Gestor do Contrato cuida da parte administrativa, orçamentária e dos aditivos."
            ),
            finalComment = "Fiscalizar in loco a execução e atestar medições é a missão central do Fiscal do Contrato."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-48",
            lessonId = "tp_adm_m8_a2",
            statement = "Quando uma empresa contratada deixa de executar parte de um serviço previsto no contrato ou o entrega fora do padrão técnico estipulado, o gestor/fiscal efetua a retenção ou abatimento do valor correspondente na nota fiscal. Esse desconto técnico é formalmente denominado:",
            options = listOf(
                "A) Multa Rescisória",
                "B) Glosa Contratual",
                "C) Tombamento Negativo",
                "D) Lucro Cessante",
                "E) Encargo Moratório"
            ),
            correctIndex = 1,
            steps = listOf(
                "Glosa contratual é a retenção ou cancelamento do pagamento correspondente a parcelas de serviços não executados ou entregues em desconformidade.",
                "Não é uma penalidade punitiva, mas o pagamento estrito daquilo que foi efetivamente entregue."
            ),
            finalComment = "Glosa é o desconto na fatura decorrente de serviços não prestados ou deficientes."
        )
    )

    private fun exercisesM9A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-49",
            lessonId = "tp_adm_m9_a1",
            statement = "Nos termos do Art. 30 da Lei nº 13.303/2016 (Estatuto das Estatais), a contratação direta que ocorre em razão da absoluta inviabilidade jurídica e fática de competição — como na contratação de fornecedor exclusivo ou profissional de notória especialização — fundamenta-se em:",
            options = listOf(
                "A) Dispensa de Licitação",
                "B) Inexigibilidade de Licitação",
                "C) Licitação Deserta",
                "D) Pregão Presencial",
                "E) Tomada de Preços"
            ),
            correctIndex = 1,
            steps = listOf(
                "Inviabilidade de competição = Inexigibilidade de Licitação (Art. 30).",
                "Competição viável mas dispensada por lei (pequeno valor, emergência) = Dispensa de Licitação (Art. 29)."
            ),
            finalComment = "A inviabilidade de competição fundamenta rigorosamente a Inexigibilidade de Licitação."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-50",
            lessonId = "tp_adm_m9_a1",
            statement = "Em caso de emergência ou calamidade pública que possa ocasionar prejuízo irreparável ou comprometer a segurança de pessoas, obras ou equipamentos de uma estatal, a contratação direta dos serviços estritamente necessários para afastar o perigo é caso de:",
            options = listOf(
                "A) Inexigibilidade de Licitação",
                "B) Licitação Dispensável (Dispensa de Licitação)",
                "C) Concurso Público Simplificado",
                "D) Pregão Internacional",
                "E) Diálogo Competitivo Emergencial"
            ),
            correctIndex = 1,
            steps = listOf(
                "Situações de emergência configuram hipótese expressa de Dispensa de Licitação prevista no Art. 29, inciso II da Lei nº 13.303/2016."
            ),
            finalComment = "A emergência autoriza a Dispensa de Licitação (Art. 29, II da Lei 13.303/2016)."
        )
    )

    private fun exercisesM9A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-51",
            lessonId = "tp_adm_m9_a2",
            statement = "Nos termos do Art. 81 da Lei nº 13.303/2016, a contratada fica obrigada a aceitar, nas mesmas condições contratuais pactuadas, os acréscimos ou supressões unilaterais determinados pela estatal em obras, serviços ou compras de até qual limite percentual do valor inicial atualizado do contrato?",
            options = listOf(
                "A) Até 10%",
                "B) Até 15%",
                "C) Até 20%",
                "D) Até 25%",
                "E) Até 50%"
            ),
            correctIndex = 3,
            steps = listOf(
                "O limite geral de alteração unilateral obrigatória para compras e serviços é de até 25% do valor inicial atualizado do contrato (Art. 81, § 1º da Lei 13.303/2016).",
                "Para reformas de edifícios ou equipamentos o limite sobe para até 50%."
            ),
            finalComment = "O limite geral para aditivos unilaterais obrigatórios na Lei das Estatais é de até 25%."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-52",
            lessonId = "tp_adm_m9_a2",
            statement = "Sobre a aplicabilidade da Lei nº 14.133/2021 (Nova Lei de Licitações) em relação às empresas públicas e sociedades de economia mista como a Transpetro, assinale a opção correta:",
            options = listOf(
                "A) Aplica-se integralmente a todos os contratos das estatais, revogando a Lei nº 13.303/2016.",
                "B) Não se aplica às empresas estatais em seus procedimentos de licitações e contratos, ressalvadas apenas as disposições de natureza penal que tipificam crimes.",
                "C) Aplica-se exclusivamente aos contratos de transporte de petróleo.",
                "D) Aplica-se apenas quando o valor do contrato ultrapassar R$ 100 milhões.",
                "E) É de aplicação facultativa a critério discricionário do presidente da estatal."
            ),
            correctIndex = 1,
            steps = listOf(
                "O artigo 1º, § 1º da Lei nº 14.133/2021 estabelece expressamente que as estatais regem-se pela Lei nº 13.303/2016, aplicando-se a elas apenas os artigos do Código Penal sobre crimes em licitações."
            ),
            finalComment = "A Lei 14.133/2021 não se aplica aos contratos das estatais (que seguem a Lei 13.303/2016), salvo crimes."
        )
    )

    private fun exercisesM9A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-53",
            lessonId = "tp_adm_m9_a3",
            statement = "De acordo com o Manual de Redação da Presidência da República (3ª edição, 2018), em comunicações oficiais expedidas para autoridades de mesma hierarquia, hierarquia inferior ou para particulares, deve-se empregar exclusivamente o seguinte fecho de cortesia:",
            options = listOf(
                "A) Respeitosamente,",
                "B) Atenciosamente,",
                "C) Cordialmente,",
                "D) Sem mais para o momento,",
                "E) Saudações fraternais,"
            ),
            correctIndex = 1,
            steps = listOf(
                "O Manual de Redação da Presidência da República estabelece apenas dois fechos oficiais:",
                "1. 'Respeitosamente,' para autoridades de hierarquia superior.",
                "2. 'Atenciosamente,' para autoridades de mesma hierarquia, inferior ou particulares."
            ),
            finalComment = "Para mesma hierarquia, inferior ou particulares, o fecho obrigatório é 'Atenciosamente,'."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-54",
            lessonId = "tp_adm_m9_a3",
            statement = "O atributo da Redação Oficial que consiste em transmitir o máximo de informações com a menor quantidade de palavras possível, eliminando redundâncias, repetições inúteis e floreios estilísticos sem prejuízo da clareza do texto, é a:",
            options = listOf(
                "A) Impessoalidade",
                "B) Concisão",
                "C) Subjetividade",
                "D) Formalidade",
                "E) Ambiguidade"
            ),
            correctIndex = 1,
            steps = listOf(
                "A concisão (economia linguística) é a arte de dizer o essencial com o menor número de palavras.",
                "Evita prolixidade, rodeios e burocratismos desnecessários."
            ),
            finalComment = "A concisão expressa o máximo de conteúdo com o mínimo de palavras."
        )
    )
}
