package com.example.data.transpetro

import com.example.data.math.CalculusDidacticBundle
import com.example.data.model.MathExercise

object TranspetroAdmPart1Didactic {

    fun getBundle(lessonId: String): CalculusDidacticBundle? = when (lessonId) {
        // =====================================================================
        // MÓDULO 1: FUNDAMENTOS DA ADMINISTRAÇÃO
        // =====================================================================
        "tp_adm_m1_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m1_a1",
            intro = "A teoria da administração estuda como organizar e coordenar recursos humanos, financeiros, materiais e tecnológicos para alcançar objetivos organizacionais. Para a Fundação Cesgranrio e a Transpetro, o cerne deste tópico reside na distinção rigorosa entre os conceitos de Eficiência, Eficácia e Efetividade, além da compreensão da organização como um sistema aberto integrado ao seu ambiente.",
            theory = """
                1. Organização e Recursos:
                Uma organização é uma entidade social estruturada, orientada para metas deliberadas e vinculada a um ambiente externo. Articula quatro tipos de recursos:
                • Recursos Físicos e Materiais: instalações, terminais, navios, dutos e equipamentos de bombeamento.
                • Recursos Financeiros: capital de giro, orçamento, fluxo de caixa e linhas de crédito.
                • Recursos Humanos: talentos, competências técnicas, operadores, tripulantes e gestores.
                • Recursos Tecnológicos e Informacionais: sistemas SCADA de monitoramento de dutos, ERP e patentes.

                2. A Tríade do Desempenho: Eficiência, Eficácia e Efetividade:
                • Eficiência (Foco nos Meios e Processos): consiste em realizar uma tarefa da melhor forma possível, otimizando o uso dos recursos, minimizando desperdícios, tempo e custos. Mede a produtividade (fazer as coisas direito).
                  - Exemplo: bombear o mesmo volume de petróleo consumindo 10% menos energia elétrica.
                • Eficácia (Foco nos Fins e Resultados): consiste em atingir os objetivos e metas planejados, independentemente dos recursos consumidos. Mede o alcance do resultado (fazer a coisa certa).
                  - Exemplo: entregar os 500.000 barris programados no prazo estipulado para a refinaria.
                • Efetividade (Foco nos Impactos e Transformação): consiste em produzir um impacto real, duradouro e positivo no ambiente e para os clientes/sociedade; reflete a sustentabilidade e a relevância social e ambiental da organização (fazer com que as coisas façam a diferença).
                  - Exemplo: garantir a segurança energética nacional sem registrar nenhum incidente ambiental ou acidente de trabalho durante o ano.

                3. Organizações como Sistemas Abertos:
                Apresentam constante interação com o meio ambiente por meio de:
                • Entradas (Inputs): matérias-primas, mão de obra, informações, normas legais e regulatórias (ANP).
                • Processamento (Throughput): refino, transporte dutoviário e marítimo, armazenagem e gestão.
                • Saídas (Outputs): combustíveis distribuídos, serviços prestados, relatórios e valor aos acionistas.
                • Retroalimentação (Feedback): pesquisas de satisfação de clientes, auditorias e auditorias externas que realimentam o ciclo.
            """.trimIndent(),
            practicalApplication = """
                Na rotina de um terminal aquaviário da Transpetro, uma equipe de manutenção pode ser muito eficiente (reparando uma válvula em tempo recorde e gastando pouco insumo), mas se a válvula reparada não for a prioritária para liberar o navio atracado, a ação não foi eficaz. Por outro lado, manter o fluxo contínuo de abastecimento sem acidentes nem vazamentos por uma década representa a verdadeira efetividade operacional e ambiental da empresa.
            """.trimIndent(),
            summary = """
                • Eficiência: foco no método, nos custos e nos recursos (fazer direito as coisas).
                • Eficácia: foco nas metas e nos resultados alcançados (fazer a coisa certa).
                • Efetividade: foco no impacto gerado no ambiente e na sociedade (fazer a diferença).
                • Sistema Aberto: ciclo contínuo de entradas, transformação, saídas e retroalimentação.
            """.trimIndent(),
            formulaName = "Métricas de Eficiência e Eficácia",
            formulaSyntax = "Eficiência = Saídas / Entradas   |   Eficácia = Resultado_Real / Meta_Planejada",
            formulaTerms = listOf(
                "Eficiência = Razão de aproveitamento de recursos (menor custo por unidade produzida)",
                "Eficácia = Percentual de alcance da meta estipulada no plano",
                "Efetividade = Índice de impacto social, ambiental e econômico global"
            ),
            formulaWhenToUse = "Utilizar para diferenciar o foco operacional (recursos) do estratégico (resultados e impactos).",
            exampleTitle = "Análise Prática de Eficiência versus Eficácia",
            exampleProblem = "Uma estação compressora de gás tinha a meta de bombear 1.000.000 m³ de gás natural no mês com custo de R$ 50.000,00. Ao final do mês, bombeou 850.000 m³ com custo reduzido de R$ 38.000,00. Analise o desempenho em termos de eficiência e eficácia.",
            exampleSteps = listOf(
                "Calcular a eficácia: bombeou 850.000 m³ de uma meta de 1.000.000 m³. Eficácia = 850.000 / 1.000.000 = 85%. Não foi plenamente eficaz (meta não cumprida).",
                "Calcular o custo unitário previsto: 50.000 / 1.000.000 = R$ 0,050/m³.",
                "Calcular o custo unitário real: 38.000 / 850.000 = R$ 0,0447/m³.",
                "Houve redução do custo unitário por metro cúbico bombeado (maior economia de insumos por unidade produzida), caracterizando alta eficiência nos meios, embora a meta global de volume não tenha sido atingida."
            ),
            exampleAnswer = "A operação demonstrou eficiência operacional no aproveitamento de recursos, mas foi ineficaz no cumprimento do volume total meta planejado.",
            goldTip = "Decore para a Cesgranrio: Eficiência = MEIOS e custos; Eficácia = FINS e cumprimento de metas; Efetividade = IMPACTO e transformação no ambiente externo!"
        )

        "tp_adm_m1_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m1_a2",
            intro = "O processo administrativo clássico é composto pelas quatro funções fundamentais da administração: Planejar, Organizar, Dirigir e Controlar (o célebre acrônimo PODC). A Fundação Cesgranrio cobra frequentemente as características de cada função e a sua natureza interligada, cíclica e dinâmica no cotidiano de uma organização.",
            theory = """
                1. As Quatro Funções Administrativas (Ciclo PODC):
                • Planejar (Planejamento):
                  - Primeira etapa do processo administrativo; define para onde a organização quer ir.
                  - Atividades: definição da missão, visão, objetivos organizacionais, estratégias, programas de ação, orçamentos e cenários futuros.
                  - Antecipa acontecimentos e busca reduzir incertezas e riscos.
                • Organizar (Organização):
                  - Estruturação dos recursos para viabilizar a execução do plano.
                  - Atividades: divisão do trabalho, departamentalização, definição de cargos, atribuições de autoridade e responsabilidade, hierarquia e alocação de equipamentos e instalações.
                • Dirigir (Direção / Liderança):
                  - Condução e mobilização das pessoas para que executem as tarefas planejadas.
                  - Atividades: liderança, motivação, comunicação organizacional, coordenação de equipes e resolução de conflitos interpessoais. É a única função que lida diretamente e primordialmente com o comportamento humano.
                • Controlar (Controle):
                  - Monitoramento e avaliação das atividades para assegurar que os resultados reais correspondam ao que foi planejado.
                  - Quatro etapas do processo de controle:
                    1. Estabelecimento de padrões ou critérios de desempenho.
                    2. Medição do desempenho real executado.
                    3. Comparação do desempenho real com o padrão planejado.
                    4. Ação corretiva para eliminar desvios ou ajustar os padrões.

                2. A Natureza Cíclica e Interativa do PODC:
                As funções não ocorrem de forma isolada nem puramente linear; elas se sobrepõem e retroalimentam continuamente. O controle retroalimenta o planejamento com informações para novos planos.
            """.trimIndent(),
            practicalApplication = """
                Em uma parada programada de manutenção de um oleoduto na Transpetro:
                - Planejar: estabelecer o cronograma de 15 dias, escopo de tubos a inspecionar e orçamento de R$ 2 milhões.
                - Organizar: distribuir técnicos em turnos de revezamento, disponibilizar ferramentas e designar supervisores de frente.
                - Dirigir: orientar as equipes em reuniões diárias de segurança (DDS), motivar e resolver impasses de comunicação.
                - Controlar: acompanhar diariamente o percentual de soldas inspecionadas em relação ao cronograma e aprovar horas extras caso haja atrasos.
            """.trimIndent(),
            summary = """
                • Planejamento: define objetivos, metas e estratégias futuras.
                • Organização: divide tarefas, aloca recursos e estrutura cargos e departamentos.
                • Direção: lidera, motiva e comunica com as pessoas para execução do trabalho.
                • Controle: estabelece padrões, mede o realizado, compara com o planejado e corrige desvios.
            """.trimIndent(),
            formulaName = "Ciclo das Funções Administrativas",
            formulaSyntax = "Planejar → Organizar → Dirigir → Controlar ↺ (Retroalimentação)",
            formulaTerms = listOf(
                "P = Definição de objetivos e recursos necessários",
                "O = Distribuição do trabalho e autoridade",
                "D = Mobilização de pessoas, liderança e comunicação",
                "C = Acompanhamento de indicadores e correção de desvios"
            ),
            formulaWhenToUse = "Utilizar para identificar qual fase do processo gerencial está sendo descrita na situação-problema da questão.",
            exampleTitle = "Identificação de Função Administrativa",
            exampleProblem = "Um gerente de terminal reuniu sua equipe para analisar os relatórios de vazão de combustível do último mês. Ele constatou que a velocidade de carregamento de caminhões ficou 12% abaixo da meta estipulada e determinou imediatamente a substituição preventiva dos bicos injetores para normalizar a operação. Qual função administrativa o gerente exerceu preponderantemente?",
            exampleSteps = listOf(
                "O gestor comparou os dados reais com as metas previamente estabelecidas (medição e comparação).",
                "Identificou um desvio de desempenho (-12%).",
                "Adotou uma ação corretiva imediata (substituição dos bicos injetores) para trazer o resultado de volta ao padrão planejado.",
                "Essas etapas caracterizam rigorosamente a função de CONTROLE."
            ),
            exampleAnswer = "Função de Controle (especificamente: comparação de resultados e tomada de ação corretiva).",
            goldTip = "Se o enunciado falar em 'motivar equipe', 'comunicar' ou 'liderar', marque DIREÇÃO. Se falar em 'definir organograma' ou 'atribuir tarefas', marque ORGANIZAÇÃO. Se falar em 'comparar com a meta' ou 'corrigir desvio', marque CONTROLE!"
        )

        "tp_adm_m1_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m1_a3",
            intro = "As decisões organizacionais distribuem-se em três níveis hierárquicos distintos: Estratégico, Tático e Operacional. A cada nível corresponde um horizonte de tempo, um grau de incerteza, uma amplitude de impacto e um tipo específico de plano. No concurso da Cesgranrio, essa divisão piramidal é amplamente explorada em questões comparativas diretas.",
            theory = """
                1. Os Três Níveis Organizacionais:
                • Nível Estratégico (Institucional / Alta Administração):
                  - Ocupantes: Conselho de Administração, Presidente, Diretores executivos.
                  - Foco: a organização como um todo e sua relação com o ambiente externo (mercado, regulação, concorrentes, sustentabilidade).
                  - Horizonte temporal: Longo Prazo (3 a 5 anos ou mais).
                  - Grau de incerteza: Alto (lida com variáveis externas incontroláveis).
                  - Abrangência: Ampla e holística; planos genéricos e macroorientadores.
                • Nível Tático (Intermediário / Gerencial):
                  - Ocupantes: Gerentes de departamentos, gerentes regionais, chefes de divisão.
                  - Foco: articulação e coordenação de uma unidade de negócio ou departamento específico (ex.: Gerência de Manutenção, Gerência Financeira).
                  - Horizonte temporal: Médio Prazo (normalmente de 1 ano).
                  - Função: traduzir os macro-objetivos estratégicos em planos setoriais departamentais.
                • Nível Operacional (Supervisão e Execução):
                  - Ocupantes: Supervisores de turno, encarregados, operadores e técnicos de base.
                  - Foco: tarefas, atividades rotineiras e procedimentos específicos do dia a dia.
                  - Horizonte temporal: Curto Prazo (dias, semanas ou meses).
                  - Grau de certeza: Alto (tarefas padronizadas, detalhadas e repetitivas).

                2. Tipos de Planos Operacionais:
                • Procedimentos Operacionais Padrão (POP): sequências passo a passo de instruções para executar uma rotina (métodos).
                • Orçamentos: planos quantitativos expressos em moeda (financeiro).
                • Cronogramas / Programações: planos associados a prazos e alocação de tempo.
                • Normas e Regulamentos: diretrizes sobre o que se deve ou não fazer (comportamento).
            """.trimIndent(),
            practicalApplication = """
                Na Transpetro:
                - Estratégico: Diretoria decide investir na renovação da frota de navios petroleiros e em gasodutos para os próximos 10 anos (Plano Estratégico Corporativo).
                - Tático: Gerência de Operações Navais elabora o plano anual de manutenção preventiva das embarcações (Plano Tático Departamental).
                - Operacional: Encarregado de bordo emite a ordem de serviço para troca de óleo do motor do navio no plantão de terça-feira conforme o POP (Plano Operacional).
            """.trimIndent(),
            summary = """
                • Estratégico: Alta administração, longo prazo, visão global da empresa, ambiente externo, alto risco.
                • Tático: Gerências setoriais, médio prazo, foco departamental, desdobramento de metas.
                • Operacional: Supervisão e execução, curto prazo, rotinas, tarefas detalhadas e procedimentos padronizados.
            """.trimIndent(),
            formulaName = "Hierarquia dos Planos Organizacionais",
            formulaSyntax = "Plano Estratégico (Longo Prazo) → Plano Tático (Médio Prazo) → Plano Operacional (Curto Prazo)",
            formulaTerms = listOf(
                "Estratégico = Visão holística corporativa, longo alcance, missão e visão",
                "Tático = Conexão departamental, metas anuais de cada área",
                "Operacional = Tarefas imediatas, procedimentos técnicos, rotinas e POPs"
            ),
            formulaWhenToUse = "Utilizar para classificar decisões e planos organizacionais segundo a hierarquia e o horizonte de tempo.",
            exampleTitle = "Classificação de Decisões Organizacionais",
            exampleProblem = "Considere três decisões tomadas em uma estatal de transporte dutoviário: (1) Aquisição de uma nova empresa transportadora para expandir negócios nos próximos 8 anos; (2) Cronograma de inspeção de soldas do oleoduto da Zona Sul para a próxima quarta-feira; (3) Plano orçamentário anual de treinamento dos operadores de bombeamento. Classifique cada decisão em seu nível organizacional.",
            exampleSteps = listOf(
                "Decisão 1: Envolve a empresa como um todo, investimentos maciços de longo prazo e posicionamento de mercado -> Nível Estratégico.",
                "Decisão 2: Envolve tarefa rotineira específica de curtíssimo prazo executada por equipe técnica -> Nível Operacional.",
                "Decisão 3: Envolve um plano setorial (treinamento) com horizonte médio de 1 ano para o departamento de RH/Operações -> Nível Tático."
            ),
            exampleAnswer = "Decisão 1 é Estratégica; Decisão 2 é Operacional; Decisão 3 é Tática.",
            goldTip = "Em questões de planejamento: se o horizonte for LONGO PRAZO e envolver a organização como um todo, é ESTRATÉGICO. Se for MÉDIO PRAZO e departamental, é TÁTICO. Se for CURTO PRAZO e focado em tarefas e procedimentos, é OPERACIONAL!"
        )

        "tp_adm_m1_a4" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m1_a4",
            intro = "A estrutura organizacional estabelece as relações formais de subordinação, os agrupamentos de departamentos, as linhas de autoridade e a rede de comunicações. Para a Cesgranrio, os conceitos de amplitude administrativa (controle), centralização versus descentralização, delegação de autoridade, estilos clássicos de liderança e barreiras de comunicação são alvos constantes de prova.",
            theory = """
                1. Elementos da Estrutura Organizacional:
                • Cadeia de Comando (Linha de Autoridade): linha contínua de autoridade que liga todas as pessoas da organização do topo à base, indicando quem se reporta a quem (Unidade de Comando).
                • Amplitude Administrativa (Amplitude de Controle): número de subordinados que um gestor pode supervisionar com eficiência.
                  - Estrutura Aguda (Vertical / Alta): amplitude estreita (poucos subordinados por chefe); gera muitos níveis hierárquicos, comunicação lenta e burocrática, e chefia centralizadora.
                  - Estrutura Achatada (Horizontal / Plana): amplitude ampla (muitos subordinados por chefe); poucos níveis hierárquicos, comunicação direta e rápida, e maior autonomia.

                2. Delegação de Autoridade:
                • Transferência formal da execução de tarefas e da autoridade para tomar decisões de um superior para um subordinado.
                • Regra de Ouro da Delegação: DELEGA-SE A AUTORIDADE, MAS A RESPONSABILIDADE FINAL PERMANECE INDELEGÁVEL pelo chefe superior.

                3. Estilos Clássicos de Liderança (White e Lippitt):
                • Autocrático: líder centraliza todas as decisões; fixa as diretrizes sem consultar a equipe; comunicação unidirecional descendente. Gera tensão e dependência.
                • Democrático: decisões debatidas e construídas com a equipe; líder atua como facilitador e orientador; comunicação bidirecional e horizontal. Gera engajamento e alta qualidade.
                • Liberal (Laissez-Faire): líder delega total liberdade à equipe, participando minimamente; intervenção quase nula. Só funciona com equipes altamente maduras e autônomas.

                4. Comunicação Organizacional e Barreiras:
                • Fluxos: Descendente (ordens da chefia para a base), Ascendente (relatórios e feedbacks da base para o topo), Horizontal (entre pares do mesmo nível) e Diagonal (entre setores e níveis distintos).
                • Barreiras: filtragem (manipulação da informação pelo emissor), sobrecarga de informação, ruído físico e linguagem técnica inadequada (jargões excessivos).
            """.trimIndent(),
            practicalApplication = """
                Em um navio petroleiro da Transpetro, a disciplina náutica exige respeito à cadeia de comando formal (Comandante, Imediato, Oficiais e Marinheiros). Durante manobras críticas de atracação em terminais, adota-se um comando firme e assertivo (orientação diretiva); em contrapartida, em reuniões prévias de análise de riscos de segurança (APR), adota-se a liderança participativa/democrática, onde marinheiros e oficiais debatem livremente medidas de proteção ambiental.
            """.trimIndent(),
            summary = """
                • Amplitude estreita = estrutura vertical/alta; amplitude ampla = estrutura horizontal/achatada.
                • Delegação: a autoridade é delegada, mas a responsabilidade final é sempre mantida pelo gestor.
                • Liderança: Autocrática (centralizadora), Democrática (participativa) e Liberal (equipe solta).
                • Comunicação: fluxos descendente, ascendente, horizontal e diagonal.
            """.trimIndent(),
            formulaName = "Relação de Amplitude e Níveis Hierárquicos",
            formulaSyntax = "Amplitude Estreita → Mais Níveis (Vertical)   |   Amplitude Ampla → Menos Níveis (Achatada)",
            formulaTerms = listOf(
                "Amplitude = Quantidade de liderados diretos supervisionados por um chefe",
                "Vertical = Decisões lentas, maior controle pessoal",
                "Horizontal = Decisões ágeis, maior autonomia para os subordinados"
            ),
            formulaWhenToUse = "Utilizar para analisar o impacto do número de subordinados por gestor no formato do organograma.",
            exampleTitle = "Análise sobre Delegação e Responsabilidade",
            exampleProblem = "Um supervisor de terminal delegou a um técnico experiente a tarefa de calibrar os sensores de nível de um tanque de óleo. O técnico cometeu um erro de calibração que resultou em alarme falso e interrupção operacional. Perante a alta gerência da empresa, como se configura a responsabilidade pelo ocorrido?",
            exampleSteps = listOf(
                "A delegação transfere ao subordinado a atribuição operacional e a autoridade técnica para agir.",
                "Entretanto, na teoria administrativa clássica e na governança pública, a responsabilidade final do gestor que delegou a função nunca se extingue (culpa in eligendo ou in vigilando).",
                "O supervisor continua integralmente responsável perante a organização pelo cumprimento satisfatório da tarefa e pela integridade das operações da sua área."
            ),
            exampleAnswer = "O supervisor permanece responsável perante a organização, pois a autoridade pode ser delegada, mas a responsabilidade do cargo superior é indelegável.",
            goldTip = "Frase obrigatória de prova da Cesgranrio: 'A responsabilidade final é indelegável'. O gestor transfere poder de execução (autoridade), mas continua prestando contas pelo resultado final perante a empresa!"
        )

        // =====================================================================
        // MÓDULO 2: GESTÃO DE PROCESSOS E QUALIDADE
        // =====================================================================
        "tp_adm_m2_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m2_a1",
            intro = "A gestão por processos visa a romper a visão fragmentada das 'ilhas funcionais' (departamentos isolados), focando nos fluxos ponta a ponta que geram valor para os clientes. A Cesgranrio cobra com destaque os conceitos de entrada-processo-saída, o mapeamento de fluxogramas, a identificação de gargalos operacionais e a formulação de Procedimentos Operacionais Padrão (POP).",
            theory = """
                1. Conceito e Elementos de um Processo:
                Um processo é um conjunto estruturado e ordenado de atividades inter-relacionadas que transforma insumos (entradas) em produtos, serviços ou informações de maior valor agregado (saídas):
                • Entradas (Inputs): recursos materiais, dados, solicitações e energia que alimentam a atividade.
                • Processamento / Atividades: sequência de operações de transformação física ou informacional.
                • Saídas (Outputs): bens finais, relatórios, combustíveis transferidos ou serviços entregues.
                • Clientes do Processo: internos (outro setor da empresa) ou externos (sociedade, distribuidoras).
                • Fornecedores do Processo: quem provê as entradas.
                • Dono do Processo (Process Owner): gestor responsável pelo desempenho e resultado ponta a ponta do processo, superando as barreiras departamentais.

                2. Ferramentas de Mapeamento e Modelagem: Fluxograma:
                Representação gráfica sequencial do fluxo de trabalho por meio de símbolos padronizados:
                • Retângulo: representa uma tarefa, ação ou etapa de processamento.
                • Losango: representa um ponto de decisão (pergunta que bifurca o fluxo, geralmente com respostas 'Sim' ou 'Não').
                • Círculo / Oval / Retângulo com cantos arredondados: ponto de início ou término do fluxo.
                • Setas / Linhas: indicam a direção e a ordem de execução do fluxo.
                • Raia de Natação (Swimlane): linhas horizontais ou verticais que indicam qual departamento ou cargo é responsável por cada etapa.

                3. Gargalos Operacionais e Padronização:
                • Gargalo (Bottleneck): etapa do processo com a menor capacidade produtiva ou maior lentidão, que determina o ritmo máximo de todo o sistema. A teoria das restrições recomenda focar melhorias diretamente no gargalo.
                • Procedimento Operacional Padrão (POP / SOP): documento normativo que descreve o passo a passo detalhado de uma rotina para garantir repetibilidade, qualidade constante e segurança operacional.
            """.trimIndent(),
            practicalApplication = """
                No processo de carregamento de navios petroleiros da Transpetro, o fluxo compreende: recebimento da programação de viagem → atracação → inspeção de tanques (conferência de estanqueidade) → conexão dos mangotes de transferência → bombeamento do óleo combustível → desconexão e emissão do manifesto de carga. O mapeamento por fluxograma identifica se o gargalo está na velocidade de vazão das bombas ou na burocracia da liberação documental das autoridades portuárias.
            """.trimIndent(),
            summary = """
                • Processo: conjunto de atividades que agrega valor transformando entradas em saídas.
                • Visão ponta a ponta: supera a divisão departamental clássica focando na entrega ao cliente.
                • Símbolos de fluxograma: Retângulo (tarefa/ação) e Losango (ponto de decisão/bifurcação).
                • Gargalo: etapa mais lenta que limita a vazão total do processo.
                • POP: padronização técnica para assegurar consistência e segurança.
            """.trimIndent(),
            formulaName = "Equação Fundamental do Processo",
            formulaSyntax = "Entradas (Inputs) + Regras de Negócio → Atividades de Transformação → Saídas (Outputs)",
            formulaTerms = listOf(
                "Inputs = Insumos, materiais, dados e requisições",
                "Transformação = Atividades que agregam valor segundo procedimentos técnicos",
                "Outputs = Resultados tangíveis e intangíveis entregues aos clientes"
            ),
            formulaWhenToUse = "Utilizar para mapear etapas de trabalho e identificar oportunidades de otimização de fluxos.",
            exampleTitle = "Identificação de Gargalo em Linha de Carregamento",
            exampleProblem = "Um processo de envase de lubrificantes possui 3 etapas sequenciais com as seguintes capacidades máximas: Etapa 1 (Preenchimento) = 600 frascos/hora; Etapa 2 (Rotulagem) = 450 frascos/hora; Etapa 3 (Encaixotamento) = 700 frascos/hora. Qual é a capacidade máxima efetiva do processo global e onde está o gargalo?",
            exampleSteps = listOf(
                "A capacidade total de um processo linear é limitada pela capacidade da sua etapa mais lenta.",
                "Comparando as etapas: 600 (Etapa 1), 450 (Etapa 2) e 700 (Etapa 3).",
                "A Etapa 2 (Rotulagem) possui a menor taxa de produção horária (450 frascos/hora).",
                "Portanto, a capacidade efetiva máxima do processo é de 450 frascos/hora e a Etapa 2 é o gargalo da linha."
            ),
            exampleAnswer = "Capacidade máxima de 450 frascos/hora; o gargalo é a Etapa 2 (Rotulagem).",
            goldTip = "Em questões da Cesgranrio sobre simbologia de fluxogramas: LOSANGO representa exclusivamente PONTO DE DECISÃO (com duas ou mais saídas alternativas), enquanto RETÂNGULO representa ATIVIDADE OU PROCESSAMENTO!"
        )

        "tp_adm_m2_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m2_a2",
            intro = "A gestão da qualidade fundamenta-se no Ciclo PDCA (Ciclo de Deming/Shewhart) e nas ferramentas clássicas de diagnóstico e resolução de problemas. O concurso da Cesgranrio cobra rotineiramente a correspondência de ações a cada fase do PDCA, a montagem do 5W2H, a aplicação da Curva de Pareto e o Diagrama de Causa e Efeito (Ishikawa / Espinha de Peixe).",
            theory = """
                1. O Ciclo PDCA (Melhoria Contínua):
                Método iterativo de gestão em quatro passos para controle e melhoria contínua de processos:
                • Plan (Planejar): identificar o problema, analisar o fenômeno, descobrir as causas fundamentais e elaborar o plano de ação detalhado com metas claras e métodos de execução.
                • Do (Executar / Fazer): treinar os colaboradores envolvidos no novo método e executar o plano de ação rigorosamente conforme prescrito. Também envolve a coleta de dados durante a execução.
                • Check (Verificar / Checar): comparar os resultados alcançados com as metas planejadas, analisando se as causas foram efetivamente eliminadas.
                • Act (Agir / Atuar Corretivamente): se os resultados forem satisfatórios, padroniza-se o novo método e dissemina-se o aprendizado; se os resultados forem insatisfatórios, roda-se o PDCA novamente para corrigir as falhas.

                2. As Ferramentas da Qualidade mais Cobradas:
                • Diagrama de Causa e Efeito (Ishikawa / Espinha de Peixe / 6M): organiza visualmente as causas potenciais de um determinado problema (efeito), categorizando-as em: Método, Matéria-Prima, Mão de Obra, Máquinas, Medição e Meio Ambiente. É ferramenta de DIAGNÓSTICO E BRAINSTORMING, não de priorização quantitativa.
                • Gráfico de Pareto: diagrama de barras verticais ordenado de forma decrescente com uma linha de percentual acumulado. Baseia-se no princípio 80/20: cerca de 80% dos problemas decorrem de apenas 20% das causas fundamentais ('poucos vitais versus muitos triviais'). Serve para PRIORIZAR ações.
                • Matriz 5W2H: plano de ação operacional composto por 7 perguntas em inglês:
                  - What (O que será feito?), Why (Por que será feito?), Where (Onde?), When (Quando / Prazos?), Who (Quem será o responsável?), How (Como será feito? - método), How Much (Quanto custará?).
            """.trimIndent(),
            practicalApplication = """
                Ao registrar um aumento atípico de paradas não programadas em bombas de óleo de um terminal da Transpetro:
                - Ishikawa: reúne-se engenheiros e mecânicos para investigar as causas prováveis estruturadas nos 6M (desgaste de peças, óleo contaminado, falha operacional).
                - Pareto: tabulam-se as ocorrências e descobre-se que 82% das falhas são causadas por vedações desgastadas (o item 'vital').
                - 5W2H: monta-se o plano de ação definindo quem trocará as vedações, em qual prazo e com qual custo.
            """.trimIndent(),
            summary = """
                • PDCA: Plan (Planejar), Do (Executar/Treinar), Check (Verificar resultados), Act (Padronizar ou Corrigir).
                • Diagrama de Ishikawa (6M): mapeia causas potenciais divididas em Método, Máquina, Mão de obra, etc.
                • Gráfico de Pareto: barras decrescentes ordenadas pelo impacto financeiro/frequência para priorização (80/20).
                • 5W2H: plano de ação tático-operacional estruturado em 7 perguntas objetivas.
            """.trimIndent(),
            formulaName = "Princípio de Pareto e Estrutura 5W2H",
            formulaSyntax = "Pareto: 80% dos Efeitos ← 20% das Causas Vitais   |   5W2H = O quê + Por quê + Onde + Quando + Quem + Como + Custo",
            formulaTerms = listOf(
                "Poucos Vitais = Causas críticas que devem receber a prioridade máxima de intervenção",
                "Muitos Triviais = Grande quantidade de falhas residuais de pequeno impacto",
                "5W2H = Ferramenta para estruturação completa de planos de ação"
            ),
            formulaWhenToUse = "Utilizar para diagnosticar falhas de processo, priorizar intervenções operacionais e montar planos de ação.",
            exampleTitle = "Identificação de Etapa do PDCA",
            exampleProblem = "Uma refinaria identificou excesso de emissão de particulados na chaminé. O comitê de sustentabilidade reuniu-se, investigou as causas raízes e estabeleceu um plano de ação para instalação de novos filtros até o mês seguinte. A qual etapa do Ciclo PDCA corresponde essa reunião e elaboração do plano?",
            exampleSteps = listOf(
                "O comitê diagnosticou o problema e suas causas raízes.",
                "Estabeleceu o objetivo e estruturou o plano de ação operacional.",
                "Ainda não houve a execução física da instalação (que será a fase Do), nem a verificação posterior (Check).",
                "Portanto, trata-se formalmente da etapa PLAN (Planejar)."
            ),
            exampleAnswer = "Etapa PLAN (Planejamento) do Ciclo PDCA.",
            goldTip = "Não confunda a função do Ishikawa com a do Pareto na Cesgranrio: Ishikawa identifica e organiza CAUSAS POSSÍVEIS (qualitativo/brainstorming); Pareto PRIORIZA as causas de maior impacto (quantitativo/80-20)!"
        )

        // =====================================================================
        // MÓDULO 3: GESTÃO DE PESSOAS (RECURSOS HUMANOS)
        // =====================================================================
        "tp_adm_m3_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m3_a1",
            intro = "A evolução da gestão de recursos humanos transformou os trabalhadores de meros 'custos operacionais ou mãos de obra descartáveis' em 'parceiros estratégicos e capital intelectual' das organizações. Para a Cesgranrio, os conceitos de comportamento organizacional, teorias motivacionais de conteúdo e processo (Maslow, Herzberg e McClelland) e dinâmicas de trabalho em equipe são temas frequentes.",
            theory = """
                1. Evolução da Gestão de Pessoas:
                • Era Clássica / Tradicional (Departamento de Pessoal): foco burocrático, legalista, controle de ponto, fiscalização e pagamento de salários. As pessoas eram tratadas como engrenagens mecânicas.
                • Era Moderna / Estratégica (Gestão de Talentos / Capital Humano): pessoas como parceiras estratégicas, detentoras de competências, criatividade e capacidade de inovação essenciais para a competitividade corporativa.

                2. Teorias Clássicas de Motivação:
                • Hierarquia das Necessidades de Maslow (Pirâmide):
                  As necessidades humanas estruturam-se em cinco níveis ascendentes; uma necessidade superior só atua como motivadora após a satisfação do nível inferior:
                  1. Fisiológicas (fome, sono, salário básico).
                  2. Segurança (estabilidade, seguro de vida, ambiente sem risco).
                  3. Sociais / Amor (amizade, aceitação no grupo, clima acolhedor).
                  4. Estima (reconhecimento, status, promoções).
                  5. Auto-realização (desenvolvimento pleno do potencial, autonomia, criatividade).
                • Teoria dos Dois Fatores de Herzberg:
                  - Fatores Higiênicos (Extrínsecos / Contexto): salário, benefícios, condições físicas de trabalho, relacionamento com chefia e políticas internas. Quando presentes, NÃO motivam, apenas EVITAM a insatisfação. Quando precários, causam profunda insatisfação.
                  - Fatores Motivacionais (Intrínsecos / Conteúdo): a natureza desafiadora da tarefa, realização pessoal, autonomia, reconhecimento e crescimento profissional. São os ÚNICOS capazes de gerar motivação duradoura e engajamento.
                • Teoria das Necessidades Adquiridas de McClelland:
                  Indivíduos são motivados por três necessidades dominantes: Necessidade de Realização (desafios pessoais), Necessidade de Afiliação (relacionamentos harmoniosos) e Necessidade de Poder (influenciar e liderar outros).

                3. Equipes de Trabalho de Alto Desempenho:
                Diferença entre Grupo e Equipe:
                • Grupo: pessoas reunidas trabalhando no mesmo local com metas individuais e sem sinergia.
                • Equipe: conjunto de pessoas com habilidades complementares comprometidas com um objetivo comum e responsabilidade mútua, gerando sinergia positiva (o todo supera a soma das partes).
            """.trimIndent(),
            practicalApplication = """
                A bordo de um navio petroleiro da Transpetro navegando por semanas, a tripulação precisa operar como uma verdadeira equipe coesa. Salários em dia e alojamentos confortáveis com ar-condicionado atendem aos fatores higiênicos (evitam insatisfação e greve); porém, o orgulho de navegar com segurança recorde e o reconhecimento formal por liderar operações complexas atendem aos fatores motivacionais de Herzberg, promovendo excelência operacional.
            """.trimIndent(),
            summary = """
                • Gestão estratégica de pessoas: colaboradores como parceiros e capital intelectual da organização.
                • Maslow: pirâmide de 5 níveis (Fisiológicas → Segurança → Sociais → Estima → Auto-realização).
                • Herzberg: Fatores Higiênicos (salário, ambiente) apenas evitam insatisfação; Fatores Motivacionais (conteúdo do cargo, reconhecimento) geram motivação real.
                • Sinergia de equipes: cooperação integrada onde o resultado coletivo supera a soma dos resultados individuais.
            """.trimIndent(),
            formulaName = "Teoria Bifatorial de Herzberg",
            formulaSyntax = "Higiênicos (Evitam Insatisfação) vs. Motivacionais (Geram Motivação)",
            formulaTerms = listOf(
                "Fatores Higiênicos = Salário, benefícios, estabilidade, regras internas e ambiente físico",
                "Fatores Motivacionais = Responsabilidade, desafio, autonomia, crescimento e realização pessoal"
            ),
            formulaWhenToUse = "Utilizar para analisar programas de benefícios, clima organizacional e desenho de cargos motivadores.",
            exampleTitle = "Aplicação da Teoria de Herzberg em Política Salarial",
            exampleProblem = "Uma empresa concedeu reajuste salarial de 15% e melhorou o plano de saúde de todos os seus colaboradores. Segundo a Teoria dos Dois Fatores de Herzberg, qual é o impacto esperado dessa medida sobre a motivação a longo prazo?",
            exampleSteps = listOf(
                "Identificar a natureza dos elementos oferecidos: salário e plano de saúde são fatores higiênicos (extrínsecos ao conteúdo do trabalho).",
                "Analisar o efeito dos fatores higiênicos segundo Herzberg: eles são essenciais para evitar a insatisfação dos trabalhadores.",
                "Entretanto, fatores higiênicos não possuem o poder de criar motivação autêntica e engajamento sustentável no longo prazo.",
                "Para motivar de fato, a empresa precisaria enriquecer as tarefas, oferecer desafios, autonomia e reconhecimento pelo trabalho realizado."
            ),
            exampleAnswer = "A medida reduz ou elimina a insatisfação com a remuneração, mas não gerará motivação sustentável de longo prazo por se tratar de fatores higiênicos.",
            goldTip = "Item clássico da Cesgranrio: segundo Frederick Herzberg, SALÁRIO É UM FATOR HIGIÊNICO e NÃO motivacional! Ele não motiva; ele apenas evita que o colaborador fique insatisfeito!"
        )

        "tp_adm_m3_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m3_a2",
            intro = "O processo de agregar pessoas compreende o recrutamento e a seleção de candidatos. Em sociedades de economia mista como a Transpetro, a admissão permanente para o quadro de terra e mar submete-se à obrigatoriedade constitucional de concurso público (Art. 37, II da CF/88), mas a teoria de Recursos Humanos é cobrada nas provas da Cesgranrio com ênfase nas modalidades de recrutamento interno, externo e misto, e nas técnicas de seleção.",
            theory = """
                1. Recrutamento de Pessoas (Divulgação e Atração):
                Conjunto de atividades desenhado para atrair candidatos qualificados para a organização:
                • Recrutamento Interno: busca preencher vagas promovendo ou transferindo colaboradores que já pertencem à própria organização.
                  - Vantagens: mais rápido e econômico; aproveita investimentos já feitos em treinamento; motiva e estimula o plano de carreira interno; menor margem de erro por conhecer o histórico do candidato.
                  - Desvantagens: risco de gerar ciúmes e atritos internos; pode incentivar o 'conservadorismo' e a endogamia cultural (falta de ideias novas).
                • Recrutamento Externo: busca candidatos no mercado de trabalho externo (bancos de currículos, agências, feiras, anúncios).
                  - Vantagens: introduz 'sangue novo', novas perspectivas culturais e competências já prontas do mercado.
                  - Desvantagens: processo mais lento e oneroso; maior incerteza sobre a adaptação do candidato; pode desmotivar a equipe interna que esperava promoção.
                • Recrutamento Misto: combina abordagens interna e externa para a mesma vaga.
                • Em Empresas Públicas e Estatais: o recrutamento externo para cargos efetivos dá-se obrigatoriamente por Concurso Público de provas ou de provas e títulos (CF/88, art. 37, II).

                2. Seleção de Pessoas (Filtragem e Escolha):
                Comparação entre o perfil exigido pelo cargo (Job Description) e o perfil apresentado pelos candidatos:
                • Técnicas de Seleção:
                  - Provas de Conhecimentos / Habilidades Técnicas: provas teóricas objetivas, discursivas e testes práticos de operação.
                  - Testes Psicológicos / Psicotécnicos: avaliam aptidões cognitivas, traços de personalidade e atenção concentrada.
                  - Entrevistas de Seleção: semiestruturadas ou comportamentais (focadas em incidentes críticos passados do candidato).
                  - Dinâmicas de Grupo e Simulações (Role-playing): observação do trabalho em equipe, liderança e comunicação em situações simuladas de pressão.

                3. Socialização Organizacional e Onboarding:
                Processo de acolhimento e ambientação do novo contratado, apresentando a cultura corporativa, normas de segurança e missão da empresa.
            """.trimIndent(),
            practicalApplication = """
                Para selecionar técnicos de operação de dutos, a Transpetro realiza concurso público com rigorosa prova eliminatória e classificatória de conhecimentos (concurso Cesgranrio) e posterior exame médico/psicotécnico. Uma vez contratados pela CLT, os novos profissionais passam por um extenso programa de onboarding e treinamento na universidade corporativa para conhecer os rigorosos protocolos de SMS antes de assumir seus postos.
            """.trimIndent(),
            summary = """
                • Recrutamento Interno: valoriza colaboradores internos, mais rápido e econômico; risco de estagnação de ideias.
                • Recrutamento Externo: traz inovação e novas competências; mais caro e demorado.
                • Estatais: admissão obrigatória mediante Concurso Público (CF/88, art. 37, II).
                • Seleção: provas de conhecimentos, entrevistas, testes de perfil e simulações para confrontar perfil com a vaga.
                • Onboarding: ambientação e aculturação do novo colaborador aos valores da empresa.
            """.trimIndent(),
            formulaName = "Comparação de Modalidades de Recrutamento",
            formulaSyntax = "Recrutamento Interno (Carreira e Motivação) vs. Recrutamento Externo (Renovação e Sangue Novo)",
            formulaTerms = listOf(
                "Interno = Baixo custo, rapidez, valorização interna, menor incerteza",
                "Externo = Inovação cultural, novas experiências de mercado, maior custo e prazo",
                "Concurso Público = Princípio da isonomia e impessoalidade para ingresso em estatais"
            ),
            formulaWhenToUse = "Utilizar para analisar vantagens e desvantagens de políticas de atração e seleção de talentos.",
            exampleTitle = "Análise de Política de Recrutamento",
            exampleProblem = "Uma grande empresa de engenharia precisava de um novo Gerente de Projetos. Optou por publicar anúncio externo em vez de promover um de seus coordenadores técnicos internos. Qual é a principal vantagem estratégica associada a essa escolha por recrutamento externo?",
            exampleSteps = listOf(
                "Analisar o recrutamento externo: traz profissionais de fora da organização.",
                "A principal vantagem técnica reside na entrada de novas ideias, abordagens modernas e experiências bem-sucedidas desenvolvidas em concorrentes ou outros setores industriais ('sangue novo').",
                "Além disso, a empresa aproveita investimentos de capacitação que já foram custeados por outras organizações."
            ),
            exampleAnswer = "Renovação do quadro com novos conhecimentos, experiências e práticas de mercado (oxigenação cultural).",
            goldTip = "Em concursos públicos: estatais (sociedades de economia mista e empresas públicas) contratam empregados sob regime da CLT, mas são OBRIGADAS a realizar concurso público por força do artigo 37 da Constituição Federal!"
        )

        "tp_adm_m3_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m3_a3",
            intro = "A administração de cargos e salários assegura a coerência interna (equidade entre os diferentes cargos dentro da empresa) e a competitividade externa (alinhamento das faixas salariais com o mercado de trabalho). A Cesgranrio foca nas etapas de descrição e análise de cargos, nos métodos de avaliação de cargos e nas diretrizes dos Planos de Cargos e Remuneração (PCR).",
            theory = """
                1. Descrição versus Análise de Cargos:
                • Descrição de Cargos: relato detalhado e impessoal sobre O QUE o ocupante do cargo faz, COMO faz, QUANDO faz e POR QUE faz. Foca nos deveres, responsabilidades e tarefas intrínsecas ao cargo.
                • Análise de Cargos: especificação dos requisitos que o ocupante DEVE POSSUIR para desempenhar o cargo com sucesso (fatores de especificação). Abrange:
                  - Requisitos Mentais (instrução formal, experiência prévia, raciocínio lógico).
                  - Requisitos Físicos (esforço físico, acuidade visual, destreza motora).
                  - Responsabilidades Envolvidas (por equipamentos, valores monetários, segurança de terceiros e dados sigilosos).
                  - Condições de Trabalho (ambiente insalubre, perigoso, trabalho em regime de turno/embarque).

                2. Métodos de Avaliação de Cargos:
                Técnicas para mensurar o valor relativo de cada cargo dentro da organização para estabelecer a hierarquia salarial:
                • Métodos Não Quantitativos:
                  - Escalonamento Simples (Ranking): comparação global de cargo contra cargo em ordem de importância.
                  - Graus Predeterminados (Classificação em Categorias): agrupamento dos cargos em faixas pré-definidas (ex.: Não Qualificado, Semiqualificado, Técnico e Especialista).
                • Métodos Quantitativos:
                  - Método de Pontos (Point Rating): o mais utilizado em grandes empresas corporativas. Decompõe os cargos em fatores compensáveis (conhecimento, complexidade, responsabilidade), atribuindo pontos graduados a cada nível para definir o salário de forma matemática e objetiva.

                3. Equidade Interna versus Equidade Externa:
                • Equidade Interna: garantida pela avaliação criteriosa de cargos, estabelecendo que cargos com responsabilidades e exigências semelhantes recebam salários da mesma faixa.
                • Equidade Externa: obtida por meio de Pesquisas Salariais de mercado, garantindo que a empresa pague salários atrativos para não perder seus profissionais para concorrentes.
            """.trimIndent(),
            practicalApplication = """
                No Plano de Cargos, Carreiras e Salários da Transpetro, a descrição de cargo de 'Profissional de Nível Médio - Administração e Controle' elenca tarefas como controle documental, suporte a compras e fiscalização de rotinas patrimoniais. Já a análise de cargo exige ensino médio completo, conhecimentos de informática corporativa e responsabilidade pelo trato de dados protegidos pela LGPD, fundamentando a respectiva tabela salarial homologada em acordo coletivo.
            """.trimIndent(),
            summary = """
                • Descrição de cargo: foca no cargo e nas tarefas (o que faz, como faz, para que faz).
                • Análise de cargo: foca no ocupante e nas exigências (escolaridade, esforço, responsabilidade, riscos).
                • Método de Pontos: técnica quantitativa mais consagrada para garantir equidade salarial interna.
                • Equidade interna (justiça entre cargos internos) + Equidade externa (pesquisa de mercado).
            """.trimIndent(),
            formulaName = "Pilares da Remuneração Estruturada",
            formulaSyntax = "Avaliação de Cargos (Equidade Interna) + Pesquisa Salarial (Equidade Externa) → Tabela Salarial",
            formulaTerms = listOf(
                "Equidade Interna = Salários proporcionais à complexidade e responsabilidade dos cargos internos",
                "Equidade Externa = Competitividade frente aos salários médios praticados pelo mercado de atuação",
                "Fatores Compensáveis = Competências, esforço, responsabilidades e condições de trabalho"
            ),
            formulaWhenToUse = "Utilizar para desenhar políticas de remuneração equânimes e estruturar planos de cargos e salários.",
            exampleTitle = "Diferença entre Descrição e Análise de Cargos",
            exampleProblem = "Em um documento corporativo constam dois tópicos sobre o cargo de Almoxarife Técnico: Tópico A: 'Realizar conferência física e lançamento de notas fiscais no sistema ERP'; Tópico B: 'Exigência de Curso Técnico em Logística, habilitação de empilhadeira e experiência mínima de 2 anos'. Como classificar tecnicamente os Tópicos A e B?",
            exampleSteps = listOf(
                "O Tópico A descreve as tarefas diárias e rotinas de trabalho atribuídas ao cargo -> Descrição de Cargos.",
                "O Tópico B lista os requisitos mínimos, qualificações formais e certificações exigidas do profissional ocupante -> Análise de Cargos (Especificação).",
                "Portanto, A é descrição e B é análise de cargo."
            ),
            exampleAnswer = "O Tópico A é Descrição de Cargos; o Tópico B é Análise de Cargos (Especificação).",
            goldTip = "Pegadinha clássica da Cesgranrio: confundir Descrição com Análise. Lembre-se: DESCRIÇÃO olha para as TAREFAS do cargo; ANÁLISE olha para os REQUISITOS e qualificações da PESSOA que vai ocupar o cargo!"
        )

        "tp_adm_m3_a4" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m3_a4",
            intro = "O processo de Treinamento, Desenvolvimento e Educação Corporativa (TD&E) é fundamental para capacitar e atualizar equipes técnicas. A Cesgranrio costuma exigir a diferenciação precisa entre treinamento (curto prazo/cargo atual), desenvolvimento (médio/longo prazo/cargos futuros) e educação formal, além do domínio das quatro fases do ciclo de treinamento e do modelo de avaliação de Kirkpatrick.",
            theory = """
                1. Diferenciação Conceitual: Treinamento vs. Desenvolvimento vs. Educação:
                • Treinamento: processo de aprendizagem de Curto Prazo voltado para o cargo atual, com objetivo de suprir carências imediatas de desempenho, ensinar novas técnicas ou procedimentos operacionais (ex.: curso de operação de um novo software de tancagem).
                • Desenvolvimento de Pessoas: processo de Médio e Longo Prazo voltado para a carreira e para cargos futuros. Foca no crescimento pessoal e gerencial amplo, preparando o profissional para assumir novos desafios e liderança.
                • Educação Corporativa: processo contínuo de difusão de conhecimentos vinculados à estratégia corporativa de longo prazo (Universidades Corporativas).

                2. As Quatro Etapas do Ciclo de Treinamento:
                1. Levantamento das Necessidades de Treinamento (LNT / Diagnóstico): identificar carências de competências entre o desempenho atual e o desejado (pesquisas, avaliação de desempenho, novas tecnologias).
                2. Desenho do Programa de Treinamento (Planejamento): definir quem será treinado, quem treinará, conteúdo programático, metodologia (presencial, EaD), local e orçamento.
                3. Execução / Condução: aplicação das aulas, oficinas, dinâmicas e estudos de caso na prática.
                4. Avaliação dos Resultados: mensuração da eficácia do treinamento e retorno sobre o investimento.

                3. Níveis de Avaliação de Treinamento (Modelo de Donald Kirkpatrick):
                • Nível 1 - Reação: avalia a satisfação dos participantes com o curso, instrutor e instalações (pesquisa de satisfação imediata ao fim do curso).
                • Nível 2 - Aprendizado: mede a aquisição real de novos conhecimentos e habilidades (provas teóricas ou testes práticos antes e após o curso).
                • Nível 3 - Comportamento no Cargo (Transferência): avalia se o treinando aplica os novos conhecimentos no seu dia a dia de trabalho após retornar à sua rotina operacional.
                • Nível 4 - Resultados Organizacionais (Impacto nos Negócios / ROI): mede o impacto nos indicadores corporativos (aumento da produtividade, redução de acidentes, corte de custos operacionais e retorno financeiro).
            """.trimIndent(),
            practicalApplication = """
                Na Transpetro, um operador de oleoduto que assume um posto participa de um Treinamento operacional imediato sobre procedimentos de segurança contra vazamentos (curto prazo/cargo atual). Ao longo dos anos, para prepará-lo para ser Chefe de Terminal, a empresa custeia cursos de pós-graduação em gestão de operações e liderança na Universidade Petrobras/Transpetro (Desenvolvimento de Pessoas de longo prazo). A avaliação no Nível 4 mede a redução efetiva de paradas de bombas após os treinamentos.
            """.trimIndent(),
            summary = """
                • Treinamento: foco no cargo atual e no curto prazo (habilidade operacional imediata).
                • Desenvolvimento: foco na carreira e no longo prazo (preparação para futuros cargos e liderança).
                • Ciclo de Treinamento: 1. Diagnóstico (LNT) → 2. Desenho/Planejamento → 3. Execução → 4. Avaliação.
                • Modelo de Kirkpatrick: 1. Reação → 2. Aprendizado → 3. Comportamento no Cargo → 4. Resultados/Impacto.
            """.trimIndent(),
            formulaName = "Fórmula do ROI de Treinamento (Nível 4 de Kirkpatrick)",
            formulaSyntax = "ROI (%) = [ (Benefício_Financeiro_Líquido - Custo_Treinamento) / Custo_Treinamento ] × 100%",
            formulaTerms = listOf(
                "Benefício Financeiro = Economia de custos ou aumento de receitas geradas pelas novas práticas",
                "Custo de Treinamento = Total de gastos com instrutores, diárias, materiais e horas paradas",
                "ROI > 0 = O treinamento gerou valor econômico mensurável para a organização"
            ),
            formulaWhenToUse = "Utilizar para comprovar o retorno financeiro e estratégico de programas de capacitação profissional.",
            exampleTitle = "Classificação de Nível no Modelo de Kirkpatrick",
            exampleProblem = "Três meses após um treinamento de manuseio seguro de válvulas, o setor de RH realizou uma observação de campo e constatou que os operadores passaram a utilizar corretamente os equipamentos de proteção e as ferramentas antichama nas operações diárias. Em qual nível do Modelo de Kirkpatrick essa avaliação se enquadra?",
            exampleSteps = listOf(
                "A avaliação não mediu apenas a satisfação com o curso (Nível 1 - Reação).",
                "Não mediu apenas se os participantes passaram em uma prova na sala de aula (Nível 2 - Aprendizado).",
                "Verificou a mudança efetiva de atitude e a aplicação prática do aprendizado no ambiente real de trabalho (transferência para a rotina).",
                "Portanto, trata-se rigorosamente do Nível 3 - Comportamento no Cargo."
            ),
            exampleAnswer = "Nível 3 do Modelo de Kirkpatrick (Comportamento no Cargo / Transferência de Aprendizagem).",
            goldTip = "Para provas da Cesgranrio: Treinamento = foco no PRESENTE e no CARGO ATUAL; Desenvolvimento = foco no FUTURO e na CARREIRA!"
        )

        "tp_adm_m3_a5" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m3_a5",
            intro = "A gestão por competências e a avaliação de desempenho estruturam a identificação de lacunas (gaps) de conhecimento e o alinhamento das entregas individuais aos objetivos estratégicos da organização. No concurso da Cesgranrio, os focos primordiais residem no modelo CHA (Conhecimentos, Habilidades e Atitudes), nos métodos clássicos de avaliação de desempenho e na avaliação de 360 graus.",
            theory = """
                1. O Conceito de Competência: O Modelo CHA:
                Competência é a capacidade de mobilizar recursos cognitivos e comportamentais para agir eficazmente em uma situação profissional:
                • Conhecimento (SABER): saber teórico, técnico e conceitual adquirido por meio da formação escolar, acadêmica e leitura.
                • Habilidade (SABER FAZER): aplicação prática do conhecimento, domínio técnico operacional, destreza e uso de ferramentas.
                • Atitude (QUERER FAZER): aspecto comportamental, iniciativa, proatividade, compromisso ético e postura interpessoal.
                • Mapeamento de Competências e Gap: o confronto entre as competências requeridas pela organização e as competências demonstradas pelo profissional apura a lacuna (Gap de Competência), que orienta os planos de capacitação e desenvolvimento.

                2. Métodos Tradicionais de Avaliação de Desempenho:
                • Escalas Gráficas: método mais simples e popular; avalia fatores de desempenho (produtividade, assiduidade, qualidade) por meio de uma régua contínua com conceitos (Ótimo, Bom, Regular, Ruim). Sujeito ao 'Efeito Halo' (tendência a generalizar uma característica positiva ou negativa para todos os fatores) e à tendência central.
                • Escolha Forçada: o avaliador é obrigado a escolher uma ou duas frases que melhor descrevem o colaborador dentro de blocos de alternativas com significados positivos e negativos ocultos. Elimina a subjetividade do avaliador, mas é de difícil compreensão e aplicação.
                • Incidentes Críticos: registra fatos reais e comportamentos extremos altamente positivos (sucessos) ou altamente negativos (falhas graves) ocorridos na rotina.
                • Pesquisa de Campo: especialista de RH entrevista os supervisores diretos para diagnosticar o desempenho de cada colaborador no próprio local de trabalho.

                3. Avaliação 360 Graus (Múltiplas Fontes / Circular):
                O colaborador é avaliado simultaneamente por todos os atores com quem interage formalmente: superior imediato, pares/colegas de trabalho, subordinados diretos, clientes internos/externos, fornecedores e autoavaliação. Oferece visão ampla e rica, exigindo alto grau de maturidade da cultura organizacional.
            """.trimIndent(),
            practicalApplication = """
                Na avaliação anual de desempenho de um operador da Transpetro:
                - Conhecimento (C): ele sabe as normas técnicas da ANP e normas de segurança de manuseio de GLP.
                - Habilidade (H): ele opera com precisão os painéis de controle e manobra válvulas sem perdas de pressão.
                - Atitude (A): diante de uma anomalia em dia chuvoso, ele toma a iniciativa imediata de acionar a equipe de contenção e reportar ao supervisor sem hesitar.
                A avaliação circular 360° permite que marinheiros subordinados e oficiais superiores avaliem conjuntamente seu desempenho de liderança a bordo.
            """.trimIndent(),
            summary = """
                • Competência (CHA): Conhecimento (Saber) + Habilidade (Saber Fazer) + Atitude (Querer Fazer).
                • Gap de competência: defasagem entre o perfil exigido e o perfil real do colaborador.
                • Escalas Gráficas: método simples por notas/conceitos; vulnerável ao Efeito Halo e complacência.
                • Avaliação 360 Graus: avaliação abrangente feita por chefes, pares, subordinados, clientes e autoavaliação.
            """.trimIndent(),
            formulaName = "Fórmula do Gap de Competências",
            formulaSyntax = "Gap = Competência_Exigida_pelo_Cargo - Competência_Apresentada_pelo_Colaborador",
            formulaTerms = listOf(
                "Gap = 0: Competência plenamente atendida",
                "Gap > 0: Necessidade de capacitação ou remanejamento de função",
                "CHA = Conhecimento + Habilidade + Atitude mobilizados na prática"
            ),
            formulaWhenToUse = "Utilizar no diagnóstico de necessidades de capacitação e mapeamento de perfis de liderança.",
            exampleTitle = "Identificação do Componente da Competência (CHA)",
            exampleProblem = "Um técnico de manutenção realizou com louvor um curso teórico avançado de soldagem submarina (nota 10 na prova teórica), mas na primeira operação real em campo não conseguiu manusear corretamente o maçarico subaquático por falta de destreza motora. Qual componente do CHA falhou nesse caso?",
            exampleSteps = listOf(
                "O profissional demonstrou possuir pleno CONHECIMENTO (saber teórico comprovado na prova teórica).",
                "Entretanto, demonstrou carência de HABILIDADE (saber fazer prático, aplicação motora e domínio da técnica na prática).",
                "Não houve menção a recusa de trabalhar (o que seria atitude).",
                "Portanto, o componente ausente ou deficiente foi a Habilidade."
            ),
            exampleAnswer = "O componente deficiente foi a Habilidade (saber fazer prático).",
            goldTip = "Distinção rápida para a prova: Conhecimento é TEORIA (saber); Habilidade é PRÁTICA e técnica (saber fazer); Atitude é COMPORTAMENTO e iniciativa (querer fazer)!"
        )

        "tp_adm_m3_a6" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m3_a6",
            intro = "As relações de trabalho, o clima e a cultura organizacional determinam o ambiente psicológico e social onde as atividades são desempenhadas. Para a Cesgranrio, os pontos mais cobrados envolvem a distinção entre Clima e Cultura Organizacional, os programas de Qualidade de Vida no Trabalho (QVT), e a gestão de planos de benefícios sociais (assistenciais, recreativos e de previdência complementar).",
            theory = """
                1. Cultura Organizacional versus Clima Organizacional:
                • Cultura Organizacional: representa o conjunto duradouro de valores, crenças compartilhadas, costumes, normas não escritas, rituais, mitos e hábitos que moldam o modo como a organização funciona. É profunda, resistente a mudanças rápidas e construída historicamente ao longo de anos (a 'personalidade' da empresa).
                  - Modelo do Iceberg da Cultura:
                    * Nível Visível / Superficial: artefatos, código de vestimenta (dress code), layout dos escritórios, logotipos e rituais públicos.
                    * Nível Oculto / Profundo: pressupostos básicos, crenças inconscientes e valores essenciais dos membros.
                • Clima Organizacional: é a percepção psicológica momentânea e coletiva que os colaboradores têm do ambiente de trabalho em determinado período. É volátil, superficial e facilmente influenciável por acontecimentos recentes (como boatos de demissão, mudanças de chefia, reajustes salariais ou atrasos de pagamento). É o 'tempo meteorológico' da empresa.

                2. Qualidade de Vida no Trabalho (QVT - Modelo de Walton):
                Programas e diretrizes voltados ao bem-estar físico, psicológico e social dos trabalhadores no exercício de suas funções:
                • Compensação justa e adequada (salário digno).
                • Condições de segurança e saúde no trabalho (ergonomia, proteção contra acidentes).
                • Uso e desenvolvimento de capacidades pessoais.
                • Integração social e ausência de preconceitos.
                • Relevância social da vida no trabalho (orgulho da instituição).

                3. Planos de Benefícios Sociais:
                Remunerações indiretas concedidas aos empregados:
                • Benefícios Legais / Obrigatórios: FGTS, 13º salário, férias remuneradas e descanso semanal remunerado.
                • Benefícios Voluntários / Espontâneos: plano de saúde médico-hospitalar corporativo, previdência complementar (como a Petros no Sistema Petrobras), vale-alimentação/refeição estendido e auxílio-creche.
            """.trimIndent(),
            practicalApplication = """
                Na Transpetro, a cultura de segurança e proteção ambiental (SMS) é inegociável, sedimentada ao longo de décadas em treinamentos e rituais formais (artefato cultural profundo). Já o clima organizacional em uma base de bombeamento pode apresentar oscilações sazonais durante campanhas salariais; a aplicação periódica de Pesquisas de Clima Organizacional permite à gerência de RH mapear tensões pontuais e restabelecer o equilíbrio e a motivação do time.
            """.trimIndent(),
            summary = """
                • Cultura Organizacional: valores, crenças e normas profundas e duradouras da organização (personalidade).
                • Clima Organizacional: atmosfera psicológica e percepção momentânea do ambiente (tempo meteorológico).
                • QVT: bem-estar físico, psicológico e ergonômico no ambiente de trabalho (Modelo de Walton).
                • Benefícios Legais (CLT/CF) vs. Espontâneos (Previdência Petros, plano médico de ponta, auxílios).
            """.trimIndent(),
            formulaName = "Metáfora Meteorológica da Organização",
            formulaSyntax = "Cultura Organizacional (Clima de Longo Prazo / Raízes) vs. Clima Organizacional (Tempo Meteorológico Atual)",
            formulaTerms = listOf(
                "Cultura = Difícil e lenta de mudar, estrutural e profunda",
                "Clima = Rápido de oscilar, sensível a políticas e eventos imediatos da liderança"
            ),
            formulaWhenToUse = "Utilizar para diferenciar intervenções culturais profundas de pesquisas pontuais de satisfação e clima.",
            exampleTitle = "Diferenciação entre Clima e Cultura",
            exampleProblem = "Após a substituição abrupta de um diretor e a divulgação de notícias de reestruturação setorial, os colaboradores passaram a relatar ansiedade e desmotivação nas pesquisas internas mensais. Essa alteração na percepção imediata do ambiente de trabalho retrata primariamente uma mudança em qual elemento organizacional?",
            exampleSteps = listOf(
                "A situação descreve uma reação psicológica coletiva e imediata a eventos recentes da conjuntura interna.",
                "Não se trata de alteração estrutural nas crenças, mitos e valores históricos construídos em décadas (cultura).",
                "Trata-se de uma oscilação circunstancial e transitória na percepção do ambiente, conceituada rigorosamente como Clima Organizacional."
            ),
            exampleAnswer = "Clima Organizacional (percepção psicológica circunstancial do ambiente de trabalho).",
            goldTip = "Pegadinha clássica: 'Cultura' é o clima geográfico de uma região (permanente, profundo e histórico); 'Clima Organizacional' é o tempo do dia (hoje faz sol, amanhã chove, sensível a eventos imediatos)!"
        )

        // =====================================================================
        // MÓDULO 4: GESTÃO DE INDICADORES
        // =====================================================================
        "tp_adm_m4_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m4_a1",
            intro = "A máxima de William Edwards Deming sintetiza a gestão moderna: 'Não se gerencia o que não se mede'. Indicadores de desempenho (Key Performance Indicators - KPIs) traduzem objetivos estratégicos em dados quantitativos para monitoramento. No concurso da Cesgranrio, os pontos vitais incluem as propriedades de um indicador confiável, a linha de base e a estruturação de metas pelo padrão SMART.",
            theory = """
                1. Conceito e Função dos Indicadores:
                Um indicador é uma métrica quantificável que representa o estado ou a evolução de um processo ou objetivo:
                • Índice: valor numérico medido em determinado momento (ex.: 98,2% de entregas no prazo).
                • Linha de Base (Baseline): valor de referência histórica inicial antes de qualquer nova intervenção.
                • Padrão / Parâmetro: nível de desempenho aceitável definido em normas ou contratos.
                • Meta: resultado numérico quantitativo associado a um prazo limite que se almeja atingir.

                2. Propriedades de um Bom Indicador de Desempenho:
                Para ser confiável e útil, o indicador deve possuir atributos essenciais:
                • Validade / Relevância: mede exatamente o fenômeno que se pretende avaliar (sem viés).
                • Confiabilidade: se medido por pessoas diferentes com o mesmo método, gera rigorosamente o mesmo resultado.
                • Simplicidade e Clareza: de fácil cálculo, compreensão e comunicação aos colaboradores da linha de frente.
                • Economicidade: o custo de coletar os dados do indicador não pode superar o benefício gerado pela medição.
                • Tempestividade: o dado deve estar disponível a tempo de subsidiar a tomada de decisão gerencial.

                3. Metas no Padrão SMART:
                Critério mnemônico para formulação de metas eficazes:
                • S (Specific - Específica): clara, sem ambiguidades; define exatamente o que deve ser alcançado.
                • M (Measurable - Mensurável): quantificável em números, percentuais ou unidades monetárias.
                • A (Achievable / Attainable - Atingível): desafiadora, porém realista e factível com os recursos disponíveis.
                • R (Relevant - Relevante): diretamente conectada aos objetivos estratégicos e à missão da organização.
                • T (Time-bound - Temporal): com prazo de conclusão estipulado (dia, mês ou ano limite).
            """.trimIndent(),
            practicalApplication = """
                Em vez de definir genericamente 'melhorar a segurança das operações', a gerência da Transpetro estabelece uma meta SMART: 'Reduzir a taxa de frequência de acidentes com afastamento (TFCA) de 0,8 para menos de 0,3 acidentes por milhão de homens-hora trabalhados (Específica e Mensurável) até 31 de dezembro de 2026 (Temporal), por meio da reciclagem dos POPs e auditorias comportamentais (Atingível e Relevante)'.
            """.trimIndent(),
            summary = """
                • Indicador: métrica quantificável que reflete o desempenho de processos ou objetivos.
                • Propriedades: Validade (mede o certo), Confiabilidade (sempre igual), Tempestividade (a tempo) e Simplicidade.
                • Metas SMART: Específica (S), Mensurável (M), Atingível (A), Relevante (R) e Temporal (T).
                • Linha de base: ponto de partida histórico contra o qual a evolução é confrontada.
            """.trimIndent(),
            formulaName = "Critério de Metas SMART",
            formulaSyntax = "Meta SMART = Ação Específica (S) + Valor Numérico (M) + Viabilidade (A) + Relevância Estrutural (R) + Data Limite (T)",
            formulaTerms = listOf(
                "S = O que exatamente será alcançado",
                "M = Unidade e valor de mensuração",
                "A = Factibilidade operacional",
                "R = Alinhamento estratégico",
                "T = Prazo final estipulado"
            ),
            formulaWhenToUse = "Utilizar para auditar e validar se metas de planos de ação atendem aos requisitos técnicos de governança.",
            exampleTitle = "Validação de Meta pelo Critério SMART",
            exampleProblem = "Considere a seguinte declaração de um plano de gestão: 'Aumentar a produtividade do terminal o máximo possível'. Essa formulação cumpre os critérios técnicos de uma meta SMART?",
            exampleSteps = listOf(
                "Analisar a especificidade: a expressão 'o máximo possível' é vaga e subjetiva (não é Specific).",
                "Analisar a mensurabilidade: não indica percentual, volume nem valor numérico (não é Measurable).",
                "Analisar o prazo: não fixa data nem período limite para cumprimento (não é Time-bound).",
                "Portanto, trata-se de um mero desejo genérico e não de uma meta SMART."
            ),
            exampleAnswer = "Não cumpre. Falta especificidade, falta mensuração numérica e falta definição de prazo temporal.",
            goldTip = "Em provas da Cesgranrio: uma meta bem formulada NUNCA usa termos como 'o máximo possível' ou 'melhorar a qualidade'; ela SEMPRE traz um número (ex.: 15%) e um prazo (ex.: até dezembro)!"
        )

        "tp_adm_m4_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m4_a2",
            intro = "A gestão operacional e estratégica requer indicadores agrupados em diferentes dimensões de desempenho: Eficiência, Eficácia, Produtividade e Qualidade. Para o concurso da Transpetro, a Cesgranrio cobra fórmulas de taxa de atendimento, índices de produtividade de mão de obra e equipamentos, além do cálculo de índices de conformidade operacional.",
            theory = """
                1. Dimensões Clássicas dos Indicadores de Desempenho:
                • Indicadores de Eficiência: mensuram a relação entre os insumos consumidos e os produtos/serviços gerados (custo unitário, consumo de energia por tonelada, horas trabalhadas por reparo).
                • Indicadores de Eficácia: mensuram o grau de alcance das metas e resultados planejados (taxa de entregas no prazo, percentual de atendimento do volume programado, índice de satisfação de clientes).
                • Indicadores de Produtividade: razão direta entre o total de saídas (produtos/serviços) e a quantidade de fatores produtivos utilizados (mão de obra, máquinas, horas):
                  Produtividade = Volume Produzido / Recursos Consumidos.
                • Indicadores de Qualidade / Conformidade: percentual de itens ou processos em conformidade estrita com os padrões técnicos em relação ao total inspecionado:
                  Taxa de Não Conformidade = (Defeitos / Total Produzido) × 100%.

                2. Fórmulas Recorrentes em Provas:
                • Taxa de Pontualidade / Atendimento no Prazo (On-Time Delivery):
                  T_prazo = (Entregas Realizadas no Prazo / Total de Entregas Realizadas) × 100%.
                • Produtividade de Equipamento / Bombeamento:
                  Produtividade = Volume Total Bombeado (m³) / Horas Efetivas de Operação.
                • Taxa de Falha Operacional:
                  T_falha = (Número de Incidentes ou Paradas / Horas Totais de Operação) × 1000.
            """.trimIndent(),
            practicalApplication = """
                Em um duto que transporta gasolina da refinaria para o centro de distribuição na Transpetro:
                - Eficácia: a refinaria programou 200.000 m³ e o duto entregou os 200.000 m³ no mês (Eficácia = 100%).
                - Eficiência: o consumo de energia para bombear esse volume foi de 1,2 kWh/m³ frente a uma meta de 1,4 kWh/m³ (economia de energia).
                - Produtividade: o duto operou a uma vazão média horária de 400 m³/hora durante as 500 horas de bombeamento ativo.
            """.trimIndent(),
            summary = """
                • Produtividade = Saídas geradas / Fator de produção consumido (ex.: m³/hora).
                • Eficácia = Resultado Realizado / Meta Planejada (ex.: % de pontualidade).
                • Eficiência = Custo ou Insumo Real / Insumo Padrão (menor gasto por unidade).
                • Indicadores de qualidade medem conformidade estrita e ausência de desvios e refugos.
            """.trimIndent(),
            formulaName = "Fórmulas Fundamentais de Produtividade e Atendimento",
            formulaSyntax = "Produtividade = Output / Input   |   Taxa_Prazo = (N_prazo / N_total) × 100%",
            formulaTerms = listOf(
                "Output = Volume ou quantidade de produtos/serviços gerados",
                "Input = Horas de trabalho, máquinas ou energia elétrica consumida",
                "Taxa_Prazo = Percentual de pedidos ou manutenções concluídas no prazo acordado"
            ),
            formulaWhenToUse = "Utilizar para calcular taxas de cumprimento de metas operacionais e produtividade de equipes.",
            exampleTitle = "Cálculo da Produtividade de Bombas e Pontualidade",
            exampleProblem = "Uma estação de bombeamento operou durante 120 horas no mês e transferiu 480.000 m³ de combustível. Além disso, de 50 manutenções programadas no período, 46 foram concluídas rigorosamente dentro do prazo pactuado. Calcule a produtividade média horária de bombeamento e o índice de pontualidade das manutenções.",
            exampleSteps = listOf(
                "Calcular a produtividade horária: Produtividade = Volume / Horas = 480.000 m³ / 120 horas = 4.000 m³/hora.",
                "Calcular a taxa de pontualidade: Pontualidade = (46 manutenções no prazo / 50 manutenções totais) × 100%.",
                "Calcular a fração: 46 / 50 = 0,92 × 100% = 92%."
            ),
            exampleAnswer = "Produtividade = 4.000 m³/hora; Índice de pontualidade das manutenções = 92%.",
            goldTip = "Em questões de produtividade da Cesgranrio: identifique sempre o numerador (o que foi produzido/entregue) e o denominador (o que foi consumido: horas, homens, máquinas ou reais). Produtividade é sempre OUTPUT sobre INPUT!"
        )

        "tp_adm_m4_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m4_a3",
            intro = "Medir indicadores sem uma rotina sistemática de análise e ação corretiva gera apenas burocracia inútil. O monitoramento contínuo de resultados, o uso de painéis gerenciais (Dashboards / Balanced Scorecard) e a análise crítica de desvios para elaboração de planos de ação corretiva são as atividades que fecham o ciclo de governança e controle.",
            theory = """
                1. Painéis de Acompanhamento (Dashboards e Gestão à Vista):
                Ferramentas visuais que consolidam os principais indicadores de desempenho da área em tempo real ou periodicidade definida:
                • Farolização / Semáforo de Desempenho:
                  - Verde: indicador atingiu ou superou a meta estabelecida (situação sob controle).
                  - Amarelo: indicador dentro da faixa de tolerância preventiva (alerta para risco iminente de desvio).
                  - Vermelho: indicador abaixo do limite aceitável (anomalia crítica que exige plano de ação imediato).
                • Balanced Scorecard (BSC - Kaplan e Norton):
                  Painel estratégico balanceado que monitora a organização sob quatro perspectivas integradas:
                  1. Perspectiva Financeira: rentabilidade, retorno sobre o capital, receita líquida e redução de custos.
                  2. Perspectiva dos Clientes: pontualidade, satisfação, participação de mercado e retenção.
                  3. Perspectiva dos Processos Internos: tempo de ciclo, qualidade, inovação e excelência operacional.
                  4. Perspectiva do Aprendizado e Crescimento: capacitação de pessoas, clima organizacional, liderança e tecnologia da informação.

                2. Análise Crítica de Desvios e Ações Corretivas:
                Ao detectar um indicador 'no vermelho', o processo gerencial segue três passos obrigatórios:
                • Análise de Causa Raiz: investigação sistemática (ex.: método dos '5 Porquês' ou Ishikawa) para descobrir por que a meta não foi cumprida.
                • Plano de Ação de Bloqueio (5W2H): definição imediata de ações para mitigar e eliminar a causa raiz.
                • Reavaliação no Ciclo Seguinte: verificação de eficácia para comprovar se o indicador retornou ao verde.
            """.trimIndent(),
            practicalApplication = """
                Na sala de controle central da Transpetro, operadores e gerentes monitoram um painel digital com a vazão de todos os dutos do país. Se a pressão em um trecho do oleoduto Rio-São Paulo cair abaixo do padrão (farol vermelho), o sistema dispara um protocolo emergencial de investigação de causa raiz e fecha automaticamente as válvulas de bloqueio de segurança em segundos, evitando transbordamentos e acidentes ambientais.
            """.trimIndent(),
            summary = """
                • Farolização: Verde (na meta), Amarelo (alerta de tolerância) e Vermelho (desvio crítico / fora da meta).
                • BSC (Balanced Scorecard): equilibra 4 perspectivas (Financeira, Clientes, Processos Internos e Aprendizado/Crescimento).
                • Análise de desvio: identifica causa raiz e formula plano de ação corretivo (5W2H) para trazer o indicador de volta ao verde.
            """.trimIndent(),
            formulaName = "Perspectivas do Balanced Scorecard (BSC)",
            formulaSyntax = "BSC = Financeira + Clientes + Processos Internos + Aprendizado e Crescimento",
            formulaTerms = listOf(
                "Financeira = Sustentabilidade econômica para acionistas",
                "Clientes = Proposta de valor e satisfação do mercado",
                "Processos Internos = Eficiência e qualidade das operações chave",
                "Aprendizado e Crescimento = Pessoas, cultura e infraestrutura tecnológica"
            ),
            formulaWhenToUse = "Utilizar para estruturar painéis de controle integrados que conectam a estratégia corporativa aos KPIs operacionais.",
            exampleTitle = "Identificação de Perspectiva no Balanced Scorecard",
            exampleProblem = "Em um programa de metas corporativas, foram incluídos dois indicadores: (1) Horas de capacitação técnica em SMS por empregado; (2) Índice de lucratividade operacional líquida. A quais perspectivas do Balanced Scorecard (BSC) esses indicadores pertencem?",
            exampleSteps = listOf(
                "O Indicador 1 mede a qualificação do capital humano e desenvolvimento dos colaboradores -> Perspectiva do Aprendizado e Crescimento.",
                "O Indicador 2 mede o resultado econômico e a rentabilidade do negócio para os acionistas -> Perspectiva Financeira.",
                "Portanto, os indicadores pertencem, respectivamente, a Aprendizado e Crescimento e Financeira."
            ),
            exampleAnswer = "Indicador 1: Perspectiva do Aprendizado e Crescimento; Indicador 2: Perspectiva Financeira.",
            goldTip = "As 4 perspectivas do BSC caem com altíssima frequência na Cesgranrio! Decore o quarteto: FINANCEIRA, CLIENTES, PROCESSOS INTERNOS e APRENDIZADO/CRESCIMENTO!"
        )

        // =====================================================================
        // MÓDULO 5: SISTEMA DE GESTÃO INTEGRADO (SGI)
        // =====================================================================
        "tp_adm_m5_a1" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m5_a1",
            intro = "O Sistema de Gestão Integrado (SGI) unifica a gestão da Qualidade, do Meio Ambiente e da Segurança e Saúde no Trabalho (SMS) sob uma estrutura e política corporativa única. Para a Transpetro, uma operadora de transporte de combustíveis de alto risco, o SGI é vital. A Cesgranrio cobra a correspondência das normas internacionais ISO 9001 (Qualidade), ISO 14001 (Meio Ambiente) e ISO 45001 (Saúde e Segurança Ocupacional), bem como as vantagens da integração.",
            theory = """
                1. As Normas Pilares do SGI:
                O SGI integra os requisitos de três normas com base na estrutura de alto nível (Anexo SL):
                • ABNT NBR ISO 9001 (Gestão da Qualidade):
                  - Foco: satisfação dos clientes, conformidade dos produtos e serviços, gestão de processos e melhoria contínua.
                • ABNT NBR ISO 14001 (Gestão Ambiental):
                  - Foco: proteção ambiental, prevenção da poluição, mitigação de impactos adversos, cumprimento da legislação ambiental (IBAMA, órgãos estaduais) e sustentabilidade operacional.
                • ABNT NBR ISO 45001 (Gestão de Segurança e Saúde Ocupacional - antiga OHSAS 18001):
                  - Foco: prevenção de lesões, doenças e mortes ocupacionais, eliminação de perigos e redução de riscos para proporcionar locais de trabalho seguros e saudáveis aos trabalhadores.

                2. Vantagens da Implantação Integrada do SGI:
                • Eliminação de redundâncias e burocracia: documentação unificada, auditorias integradas simultâneas e treinamento unificado.
                • Redução de custos administrativos e operacionais: menos manuais e procedimentos duplicados.
                • Coerência estratégica: alinhamento pleno entre as metas de produção, segurança do trabalhador e preservação ambiental.
                • Visão sistêmica: evita que uma meta de qualidade comprometa a segurança do operador ou o meio ambiente.

                3. Política do SGI:
                Documento formal emitido pela alta administração que declara o compromisso inegociável da empresa com a qualidade dos serviços, a proteção do meio ambiente, a integridade física dos colaboradores e o atendimento aos requisitos legais aplicáveis.
            """.trimIndent(),
            practicalApplication = """
                Na operação de descarregamento de um navio petroleiro no terminal da Transpetro, o SGI funciona integrado:
                - ISO 9001: o combustível transferido é testado em laboratório para garantir que não sofreu contaminação na viagem e cumpre as normas da ANP (Qualidade).
                - ISO 14001: barreiras de contenção são posicionadas ao redor do casco do navio para conter qualquer eventual vazamento no mar (Meio Ambiente).
                - ISO 45001: os operadores utilizam máscaras autônomas, cintos trava-quedas e calçados antiestáticos (Saúde e Segurança Ocupacional).
            """.trimIndent(),
            summary = """
                • ISO 9001: Qualidade e foco na satisfação do cliente.
                • ISO 14001: Meio Ambiente, prevenção da poluição e sustentabilidade.
                • ISO 45001: Segurança e Saúde Ocupacional (SMS), prevenção de acidentes e proteção da vida.
                • SGI: unifica as três normas em política única, documentação padronizada e auditorias conjuntas, reduzindo custos e burocracia.
            """.trimIndent(),
            formulaName = "Tríade do Sistema de Gestão Integrado (SGI)",
            formulaSyntax = "SGI = ISO 9001 (Qualidade) + ISO 14001 (Meio Ambiente) + ISO 45001 (Segurança e Saúde Ocupacional)",
            formulaTerms = listOf(
                "ISO 9001 = Eficiência de processos e satisfação dos requisitos do cliente",
                "ISO 14001 = Redução de impactos ambientais e conformidade ecológica",
                "ISO 45001 = Preservação da vida, ergonomia e eliminação de perigos no ambiente de trabalho"
            ),
            formulaWhenToUse = "Utilizar para identificar normas e princípios aplicáveis à governança e certificação integrada de processos industriais.",
            exampleTitle = "Correspondência das Normas ISO no SGI",
            exampleProblem = "Uma estatal do setor de energia está realizando auditoria externa para renovar suas certificações de Qualidade, Preservação Ambiental e Saúde/Segurança do Trabalhador. Quais são, respectivamente, os códigos das três normas internacionais ABNT ISO que fundamentam esse processo?",
            exampleSteps = listOf(
                "Identificar a norma internacional de Gestão da Qualidade: ISO 9001.",
                "Identificar a norma internacional de Gestão Ambiental: ISO 14001.",
                "Identificar a norma internacional de Segurança e Saúde Ocupacional: ISO 45001.",
                "Portanto, o conjunto normativo é ISO 9001, ISO 14001 e ISO 45001."
            ),
            exampleAnswer = "ABNT NBR ISO 9001, ISO 14001 e ISO 45001.",
            goldTip = "Em concursos de estatais: guarde que a antiga norma de segurança OHSAS 18001 foi substituída oficialmente pela ISO 45001!"
        )

        "tp_adm_m5_a2" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m5_a2",
            intro = "As auditorias do SGI garantem a conformidade contínua dos processos em relação às normas de Qualidade, Meio Ambiente e SMS. O tratamento formal de Não Conformidades (NC) e a distinção entre Ações Corretivas e Ações Preventivas são tópicos de alta relevância no concurso da Cesgranrio.",
            theory = """
                1. Tipos de Auditoria do SGI:
                • Auditoria de Primeira Parte (Auditoria Interna): realizada pela própria organização (por auditores internos treinados e independentes da área auditada) para verificar a conformidade de seus processos.
                • Auditoria de Segunda Parte: realizada por uma organização em seus fornecedores ou parceiros para avaliar o cumprimento de cláusulas contratuais e padrões de qualidade.
                • Auditoria de Terceira Parte (Auditoria de Certificação): conduzida por um organismo de certificação independente e acreditado (ex.: Bureau Veritas, DNV, SGS, ABNT) para emissão ou renovação do selo ISO.

                2. Conceito de Não Conformidade (NC):
                • É o não atendimento a um requisito pré-estabelecido (em normas ISO, leis vigentes, procedimentos internos ou contratos).
                • Classificação: Não Conformidade Maior (quebra grave do sistema ou risco imediato à segurança/meio ambiente) e Não Conformidade Menor (desvio pontual sem prejuízo sistêmico).

                3. Disposição Imediata, Ação Corretiva e Ação Preventiva:
                • Disposição / Correção Imediata: ação tomada para conter e remediar o sintoma do problema de imediato (ex.: fechar uma válvula para conter um vazamento e limpar o chão). NÃO elimina a causa raiz.
                • Ação Corretiva: ação tomada para eliminar a CAUSA RAIZ de uma não conformidade JÁ OCORRIDA, com o objetivo expresso de EVITAR SUA REINCIDÊNCIA (ex.: alterar o material de todas as gaxetas e treinar os mecânicos no novo torque de aperto).
                • Ação Preventiva / Gestão de Riscos: ação tomada para eliminar a causa de uma potencial não conformidade que AINDA NÃO OCORREU, baseando-se em análises de risco preditivas e lições aprendidas (agir proativamente antes da falha).
            """.trimIndent(),
            practicalApplication = """
                Durante uma auditoria interna em um oleoduto da Transpetro, constata-se que o registro de calibração de um sensor de gás estava vencido há 5 dias (Não Conformidade). Como correção imediata, calibra-se o sensor no mesmo dia. Como Ação Corretiva sistêmica, implanta-se um alerta automático no ERP que avisa o fiscal 30 dias antes do vencimento e apura-se a causa raiz para evitar que qualquer outro sensor opere descalibrado.
            """.trimIndent(),
            summary = """
                • Auditorias: 1ª Parte (Interna), 2ª Parte (Fornecedores), 3ª Parte (Organismo Certificador Independente).
                • Não Conformidade: não atendimento a um requisito legal, normativo ou operacional.
                • Correção Imediata: resolve o efeito momentâneo do problema.
                • Ação Corretiva: elimina a CAUSA RAIZ da falha já ocorrida para EVITAR QUE SE REPITA.
                • Gestão de Riscos (Ação Preventiva): antecipa-se à ocorrência potencial da falha.
            """.trimIndent(),
            formulaName = "Ciclo de Tratamento de Não Conformidades (RCA)",
            formulaSyntax = "Detecção da NC → Correção Imediata → Análise de Causa Raiz → Ação Corretiva → Verificação de Eficácia",
            formulaTerms = listOf(
                "RCA = Root Cause Analysis (Análise de Causa Raiz)",
                "Ação Corretiva = Atua na causa para evitar reincidência",
                "Eficácia = Comprovação após determinado período de que o problema não voltou a ocorrer"
            ),
            formulaWhenToUse = "Utilizar para diferenciar correções pontuais de ações estruturais corretivas e preventivas.",
            exampleTitle = "Diferença entre Correção e Ação Corretiva",
            exampleProblem = "Uma mangueira de abastecimento de óleo rompeu-se no pátio, derramando 20 litros de produto. A equipe imediatamente estancou o fluxo, jogou pó de serra absorvente e substituiu a mangueira furada por uma nova do almoxarifado. Em seguida, a engenharia investigou e descobriu que o fornecedor entregou um lote com borracha fora da especificação de pressão, determinando o recolhimento de todas as mangueiras daquele lote e a notificação do fabricante. Como classificar a primeira e a segunda intervenção?",
            exampleSteps = listOf(
                "A primeira intervenção (estancar, absorver e trocar a mangueira furada) conteve o sintoma imediato do acidente -> Correção Imediata / Disposição.",
                "A segunda intervenção investigou a causa raiz (lote de borracha fora da especificação) e recolheu todas as peças para evitar novos rompimentos futuros -> Ação Corretiva.",
                "Portanto, a primeira foi correção e a segunda foi ação corretiva."
            ),
            exampleAnswer = "A primeira foi Correção Imediata (contenção); a segunda foi Ação Corretiva (eliminação da causa raiz).",
            goldTip = "Pegadinha campeã de audiência da Cesgranrio: trocar uma peça quebrada ou limpar um vazamento de óleo é apenas CORREÇÃO IMEDIATA! Só é AÇÃO CORRETIVA quando se descobre a CAUSA RAIZ e se atua sobre ela para evitar que o problema volte a ocorrer!"
        )

        "tp_adm_m5_a3" -> TranspetroDidacticProvider.buildBundle(
            lessonId = "tp_adm_m5_a3",
            intro = "A gestão de riscos operacionais no SGI visa a identificar perigos, avaliar riscos e implementar barreiras preventivas para mitigar a probabilidade de falhas e a severidade de impactos ambientais e humanos. O concurso da Cesgranrio cobra com destaque a distinção entre Perigo e Risco, a matriz de Probabilidade versus Impacto e a hierarquia de controle de riscos operacionais.",
            theory = """
                1. Distinção Conceitual entre Perigo e Risco:
                • Perigo (Fator de Risco): fonte, situação ou ato com potencial intrínseco de causar danos humanos (lesões ou doenças), danos ambientais ou perdas patrimoniais.
                  - Exemplo: o petróleo pressurizado circulando dentro de uma tubulação; o trabalho em altura em uma torre de tancagem; eletricidade de alta tensão. O perigo é uma propriedade inerente da substância ou da condição.
                • Risco: combinação da Probabilidade de ocorrência de um evento perigoso com a Gravidade (Severidade / Impacto) dos danos que ele pode causar:
                  Risco = Probabilidade (Frequência) × Impacto (Severidade).
                  - Exemplo: o risco de vazamento do petróleo por corrosão da parede do duto; o risco de queda do operador durante a inspeção.

                2. Matriz de Avaliação de Riscos (Matriz de Severidade vs. Probabilidade):
                Instrumento visual matricial que cruza a probabilidade de ocorrência (Rara, Improvável, Moderada, Provável, Frequente) com a severidade do impacto (Desprezível, Menor, Moderada, Crítica, Catastrófica). Classifica os riscos em faixas:
                • Risco Baixo (Aceitável): monitoramento com controles operacionais de rotina.
                • Risco Médio (Tolerável): exige planos de melhoria e inspeções periódicas.
                • Risco Alto / Crítico (Intolerável): exige paralisação imediata da atividade ou instalação imediata de barreiras até a mitigação do risco.

                3. Hierarquia das Medidas de Controle de Riscos (ISO 45001):
                Ordem decrescente de eficácia que deve ser seguida para eliminar ou reduzir riscos:
                1. Eliminação: retirar fisicamente o perigo da operação (medida mais eficaz).
                2. Substituição: substituir o perigo por algo menos perigoso (ex.: trocar solvente tóxico por desengraxante biodegradável aquoso).
                3. Controles de Engenharia: isolar as pessoas do perigo por barreiras físicas (válvulas automáticas de corte, bacias de contenção, guardas de proteção em motores).
                4. Controles Administrativos: procedimentos de trabalho (POP), sinalização, treinamento e Permissão de Trabalho (PT).
                5. Equipamentos de Proteção Individual (EPI): capacetes, luvas, botas e máscaras (medida menos eficaz e de última linha de defesa).
            """.trimIndent(),
            practicalApplication = """
                Na operação de transporte dutoviário de gás combustível na Transpetro:
                - Perigo: o gás natural sob alta pressão inflamável.
                - Risco: vazamento com ignição e explosão.
                - Controles de engenharia: tubos com espessura reforçada, válvulas de alívio automáticas e detectores ópticos de chama.
                - Controles administrativos: Permissão de Trabalho (PT) e Análise Preliminar de Risco (APR) assinadas antes de qualquer intervenção com fogo.
                - EPI: vestimentas antichama (último recurso para proteção do trabalhador).
            """.trimIndent(),
            summary = """
                • Perigo: fonte intrínseca com potencial de dano (o gás inflamável).
                • Risco = Probabilidade de ocorrência × Severidade do impacto.
                • Matriz de Riscos: classifica riscos em Aceitável, Tolerável e Intolerável (Crítico).
                • Hierarquia de Controle (ISO 45001): Eliminação → Substituição → Controles de Engenharia → Controles Administrativos → EPI.
                • EPI é a ÚLTIMA barreira na hierarquia de segurança, devendo ser utilizado quando as medidas coletivas não forem suficientes.
            """.trimIndent(),
            formulaName = "Fórmula e Hierarquia de Riscos Operacionais",
            formulaSyntax = "Risco = Probabilidade × Gravidade   |   Eliminação > Substituição > Engenharia > Administrativo > EPI",
            formulaTerms = listOf(
                "Probabilidade = Frequência ou chance de o evento danoso ocorrer",
                "Gravidade = Magnitude das perdas humanas, ambientais e patrimoniais resultantes",
                "Hierarquia = Prioridade mandatória: proteção coletiva e na fonte antes do EPI individual"
            ),
            formulaWhenToUse = "Utilizar para priorizar investimentos em segurança e dimensionar barreiras operacionais de SMS.",
            exampleTitle = "Aplicação da Hierarquia de Controle de Riscos",
            exampleProblem = "Em uma oficina de manutenção de bombas, os mecânicos utilizavam um solvente volátil altamente inflamável para limpeza de peças, gerando vapores tóxicos. A gerência determinou a troca imediata do produto por uma solução desengraxante aquosa e biodegradável não inflamável. Segundo a hierarquia de controle de riscos da ISO 45001, qual medida foi adotada?",
            exampleSteps = listOf(
                "Analisar a intervenção: o produto perigoso foi trocado por uma substância de menor agressividade e sem inflamabilidade.",
                "Não foi apenas isolado fisicamente (controle de engenharia), nem foi dada ordem de uso de máscara aos operadores (EPI).",
                "A substituição de um material perigoso por outro mais seguro enquadra-se rigorosamente no nível de SUBSTITUIÇÃO.",
                "Substituição é a segunda medida mais eficaz na hierarquia de segurança."
            ),
            exampleAnswer = "Medida de Substituição (segundo nível da hierarquia de controle da ISO 45001).",
            goldTip = "Em provas da Cesgranrio sobre segurança: EPI (Equipamento de Proteção Individual) é a MEDIDA MENOS EFICAZ de todas e deve ser adotada apenas como última alternativa, após esgotadas as medidas de eliminação, substituição e controle de engenharia coletivo!"
        )

        else -> null
    }


    fun getExercises(lessonId: String): List<MathExercise> {
        return when (lessonId) {
            "tp_adm_m1_a1" -> exercisesM1A1()
            "tp_adm_m1_a2" -> exercisesM1A2()
            "tp_adm_m1_a3" -> exercisesM1A3()
            "tp_adm_m1_a4" -> exercisesM1A4()
            "tp_adm_m2_a1" -> exercisesM2A1()
            "tp_adm_m2_a2" -> exercisesM2A2()
            "tp_adm_m3_a1" -> exercisesM3A1()
            "tp_adm_m3_a2" -> exercisesM3A2()
            "tp_adm_m3_a3" -> exercisesM3A3()
            "tp_adm_m3_a4" -> exercisesM3A4()
            "tp_adm_m3_a5" -> exercisesM3A5()
            "tp_adm_m3_a6" -> exercisesM3A6()
            "tp_adm_m4_a1" -> exercisesM4A1()
            "tp_adm_m4_a2" -> exercisesM4A2()
            "tp_adm_m4_a3" -> exercisesM4A3()
            "tp_adm_m5_a1" -> exercisesM5A1()
            "tp_adm_m5_a2" -> exercisesM5A2()
            "tp_adm_m5_a3" -> exercisesM5A3()
            else -> emptyList()
        }
    }

    private fun exercisesM1A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-01",
            lessonId = "tp_adm_m1_a1",
            statement = "Na teoria geral da administração, o conceito que expressa a capacidade de uma organização atingir plenamente os objetivos e metas planejados, focalizando o resultado final das operações independentemente dos recursos despendidos, é a:",
            options = listOf(
                "A) Eficiência",
                "B) Eficácia",
                "C) Elasticidade",
                "D) Economicidade",
                "E) Entropia"
            ),
            correctIndex = 1,
            steps = listOf(
                "Eficiência relaciona-se aos meios, processos e custos (fazer as coisas com o menor gasto de recursos).",
                "Eficácia relaciona-se aos fins, aos resultados e ao alcance estrito das metas planejadas.",
                "Efetividade relaciona-se ao impacto social e ambiental gerado.",
                "Logo, o alcance das metas e resultados finais caracteriza a Eficácia."
            ),
            finalComment = "Eficácia é o grau de atingimento das metas e objetivos estipulados no planejamento."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-02",
            lessonId = "tp_adm_m1_a1",
            statement = "Uma equipe de bombeamento conseguiu transportar o volume contratado de combustíveis consumindo 15% menos energia elétrica e utilizando menos tempo de máquina que o previsto no orçamento. Essa equipe evidenciou prioritariamente elevado grau de:",
            options = listOf(
                "A) Inércia administrativa",
                "B) Eficiência operacional",
                "C) Rigidez estrutural",
                "D) Burocracia disfuncional",
                "E) Descentralização decisória"
            ),
            correctIndex = 1,
            steps = listOf(
                "A equipe otimizou o uso dos insumos (energia elétrica e horas de máquina).",
                "A economia de recursos e a produtividade no uso dos meios de produção definem o conceito de Eficiência."
            ),
            finalComment = "Fazer mais com menos recursos (otimização de custos e meios) caracteriza a eficiência."
        )
    )

    private fun exercisesM1A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-03",
            lessonId = "tp_adm_m1_a2",
            statement = "A função administrativa que consiste em monitorar as atividades organizacionais, comparando o desempenho real com os padrões previamente fixados para assegurar o cumprimento dos objetivos e corrigir desvios tempestivamente, é o:",
            options = listOf(
                "A) Planejamento",
                "B) Direção",
                "C) Controle",
                "D) Organização",
                "E) Liderança"
            ),
            correctIndex = 2,
            steps = listOf(
                "Planejar: define objetivos e estratégias futuras.",
                "Organizar: distribui recursos e atribui responsabilidades na estrutura.",
                "Dirigir: orienta e motiva pessoas.",
                "Controlar: estabelece padrões, mede o realizado, compara e corrige desvios."
            ),
            finalComment = "A medição de desempenho e a correção de rumos configuram a função Controle."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-04",
            lessonId = "tp_adm_m1_a2",
            statement = "No processo administrativo, qual é a única função que lida diretamente e primordialmente com a liderança, motivação, coordenação interpessoal e comunicação entre as pessoas?",
            options = listOf(
                "A) Planejamento",
                "B) Organização",
                "C) Direção",
                "D) Controle",
                "E) Orçamento"
            ),
            correctIndex = 2,
            steps = listOf(
                "A função Direção atua no nível interpessoal da administração.",
                "Enquanto planejar e organizar lidam com ideias e recursos, dirigir lida diretamente com o comportamento e engajamento humano."
            ),
            finalComment = "A Direção é a função administrativa que mobiliza e conduz as pessoas rumo às metas."
        )
    )

    private fun exercisesM1A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-05",
            lessonId = "tp_adm_m1_a3",
            statement = "O tipo de planejamento elaborado pela alta administração que possui horizonte temporal de longo prazo, abrange a organização como uma totalidade sistêmica e define a missão, visão e grandes diretrizes institucionais frente ao ambiente externo é o:",
            options = listOf(
                "A) Planejamento Operacional",
                "B) Planejamento Tático",
                "C) Planejamento Estratégico",
                "D) Planejamento Setorial",
                "E) Procedimento Padrão"
            ),
            correctIndex = 2,
            steps = listOf(
                "Planejamento Operacional: curto prazo, focado em tarefas e procedimentos de rotina.",
                "Planejamento Tático: médio prazo, focado em departamentos ou gerências específicas.",
                "Planejamento Estratégico: longo prazo, alta administração, holístico e voltado ao ambiente externo."
            ),
            finalComment = "O Planejamento Estratégico define os rumos de longo prazo da organização como um todo."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-06",
            lessonId = "tp_adm_m1_a3",
            statement = "Os Procedimentos Operacionais Padrão (POP), que descrevem detalhadamente o passo a passo técnico para execução de manutenções e operações diárias com horizonte de curto prazo, constituem instrumentos típicos de qual nível de planejamento?",
            options = listOf(
                "A) Nível Estratégico",
                "B) Nível Institucional",
                "C) Nível Tático",
                "D) Nível Operacional",
                "E) Nível Normativo Central"
            ),
            correctIndex = 3,
            steps = listOf(
                "Os POPs são planos operacionais detalhados que prescrevem tarefas específicas do dia a dia da operação.",
                "Pertencem essencialmente ao Nível Operacional da organização."
            ),
            finalComment = "POPs e roteiros detalhados de tarefas são planos do nível operacional."
        )
    )

    private fun exercisesM1A4(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-07",
            lessonId = "tp_adm_m1_a4",
            statement = "Uma organização com amplitude administrativa estreita (poucos subordinados diretos por chefe) apresenta, como consequência direta na sua estrutura organizacional:",
            options = listOf(
                "A) Uma estrutura plana e horizontal com pouquíssimos níveis hierárquicos.",
                "B) Uma estrutura verticalizada e aguda, com maior quantidade de níveis hierárquicos e comunicação mais lenta.",
                "C) A eliminação de cargos de chefia intermediária.",
                "D) Tomada de decisão descentralizada na base operacional.",
                "E) Impossibilidade de fiscalização direta dos operadores."
            ),
            correctIndex = 1,
            steps = listOf(
                "Amplitude estreita significa que cada gestor supervisiona poucos subordinados.",
                "Para enquadrar toda a força de trabalho, criam-se muitos escalões sucessivos de chefia, gerando uma estrutura alta (verticalizada), com comunicações mais lentas."
            ),
            finalComment = "Amplitude estreita gera estruturas verticais/agudas com muitos níveis hierárquicos intermediários."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-08",
            lessonId = "tp_adm_m1_a4",
            statement = "O estilo de liderança no qual as diretrizes, métodos e divisão de tarefas são debatidos e decididos pelo grupo sob o estímulo e orientação do líder, que atua de forma participativa e bidirecional, é denominado:",
            options = listOf(
                "A) Liderança Autocrática",
                "B) Liderança Democrática",
                "C) Liderança Liberal (Laissez-faire)",
                "D) Liderança Coercitiva",
                "E) Liderança Centralizadora"
            ),
            correctIndex = 1,
            steps = listOf(
                "Autocrática: líder decide sozinho e impõe tarefas.",
                "Democrática: decisões compartilhadas com o grupo, incentivando debate e cooperação.",
                "Liberal: líder se abstém de participar e deixa o grupo totalmente livre."
            ),
            finalComment = "A liderança democrática envolve a equipe no processo decisório e estimula o engajamento coletivo."
        )
    )

    private fun exercisesM2A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-09",
            lessonId = "tp_adm_m2_a1",
            statement = "Na representação gráfica de processos organizacionais por meio de fluxogramas padronizados, o símbolo do 'Losango' é utilizado exclusivamente para indicar:",
            options = listOf(
                "A) O início ou término absoluto do processo.",
                "B) Uma atividade operacional ou tarefa de processamento.",
                "C) Um ponto de decisão ou bifurcação condicional do fluxo.",
                "D) Um atraso ou espera forçada de material.",
                "E) O arquivamento definitivo de um documento físico."
            ),
            correctIndex = 2,
            steps = listOf(
                "Retângulo: tarefa, ação ou atividade de processamento.",
                "Losango: ponto de decisão (pergunta que gera bifurcações como Sim/Não).",
                "Círculo/Oval: início ou fim do fluxo."
            ),
            finalComment = "O losango em fluxogramas representa invariavelmente pontos de decisão condicional."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-10",
            lessonId = "tp_adm_m2_a1",
            statement = "Em uma linha de processos industriais composta por etapas sequenciais, a etapa que apresenta a menor capacidade produtiva ou a maior restrição de velocidade, determinando o ritmo máximo de saída de todo o sistema produtivo, é conceituada como:",
            options = listOf(
                "A) Entrada crítica",
                "B) Gargalo operacional",
                "C) Ponto de inflexão",
                "D) Raia de controle",
                "E) Efeito cascata"
            ),
            correctIndex = 1,
            steps = listOf(
                "Gargalo (bottleneck) é a restrição que limita a vazão total do processo.",
                "Nenhum sistema pode operar com capacidade superior àquela permitida pelo seu gargalo."
            ),
            finalComment = "Gargalo é a etapa restritiva que dita a capacidade máxima de vazão de todo o processo."
        )
    )

    private fun exercisesM2A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-11",
            lessonId = "tp_adm_m2_a2",
            statement = "A ferramenta da qualidade que organiza visualmente em categorias (como Método, Máquina, Mão de Obra e Meio Ambiente) as possíveis causas que concorrem para a ocorrência de um determinado problema é o:",
            options = listOf(
                "A) Gráfico de Gantt",
                "B) Diagrama de Ishikawa (Espinha de Peixe)",
                "C) Histograma de Frequências",
                "D) Diagrama de Dispersão",
                "E) Fluxograma Linear"
            ),
            correctIndex = 1,
            steps = listOf(
                "O Diagrama de Ishikawa (Causa e Efeito ou Espinha de Peixe) agrupa hipóteses de causas nos 6M.",
                "É ferramenta de brainstorming e diagnóstico analítico de falhas."
            ),
            finalComment = "Diagrama de Ishikawa organiza as possíveis causas de um problema em categorias como os 6M."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-12",
            lessonId = "tp_adm_m2_a2",
            statement = "No planejamento e estruturação de um plano de ação pela metodologia 5W2H, a pergunta que corresponde à definição do método operacional ou modo como a atividade será executada é:",
            options = listOf(
                "A) Who",
                "B) Why",
                "C) How",
                "D) Where",
                "E) When"
            ),
            correctIndex = 2,
            steps = listOf(
                "What = O que será feito.",
                "Why = Por que será feito.",
                "Who = Quem fará.",
                "Where = Onde será feito.",
                "When = Quando será feito.",
                "How = Como será executado (método).",
                "How much = Quanto custará."
            ),
            finalComment = "How especifica o método e procedimento operacional de execução no 5W2H."
        )
    )

    private fun exercisesM3A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-13",
            lessonId = "tp_adm_m3_a1",
            statement = "Segundo a Teoria dos Dois Fatores de Frederick Herzberg, fatores como salário, benefícios, estabilidade, condições físicas do ambiente de trabalho e relações interpessoais com a chefia são classificados como:",
            options = listOf(
                "A) Fatores Motivacionais",
                "B) Fatores Intrínsecos",
                "C) Fatores Higiênicos",
                "D) Fatores de Auto-realização",
                "E) Fatores Transversais"
            ),
            correctIndex = 2,
            steps = listOf(
                "Fatores higiênicos (extrínsecos) relacionam-se ao contexto do trabalho e evitam a insatisfação, mas não motivam por si sós.",
                "Fatores motivacionais (intrínsecos) relacionam-se ao conteúdo do cargo, autonomia e desafios."
            ),
            finalComment = "Salário e ambiente de trabalho são fatores higiênicos segundo a teoria de Herzberg."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-14",
            lessonId = "tp_adm_m3_a1",
            statement = "Na Pirâmide de Necessidades de Abraham Maslow, as necessidades humanas associadas ao sentimento de pertencimento a um grupo, aceitação, amizade e afeto são denominadas:",
            options = listOf(
                "A) Fisiológicas",
                "B) De Segurança",
                "C) Sociais (ou de Amor)",
                "D) De Estima",
                "E) De Auto-realização"
            ),
            correctIndex = 2,
            steps = listOf(
                "Nível 1: Fisiológicas (básicas de sobrevivência).",
                "Nível 2: Segurança (proteção contra perigos e incertezas).",
                "Nível 3: Sociais (pertencimento, amizade e relações afetivas).",
                "Nível 4: Estima (reconhecimento e prestígio).",
                "Nível 5: Auto-realização (pleno potencial)."
            ),
            finalComment = "O terceiro degrau da pirâmide de Maslow abriga as necessidades sociais e de relacionamento."
        )
    )

    private fun exercisesM3A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-15",
            lessonId = "tp_adm_m3_a2",
            statement = "Constitui a principal vantagem do Recrutamento Interno em relação ao Recrutamento Externo na gestão de pessoas de uma organização:",
            options = listOf(
                "A) Introdução sistemática de novas ideias e culturas organizacionais externas.",
                "B) Menor custo financeiro, maior rapidez no processo e valorização do plano de carreira dos colaboradores atuais.",
                "C) Eliminação absoluta de qualquer conflito de interesses entre colegas.",
                "D) Ampliação do número de candidatos inscritos no processo.",
                "E) Desobrigação de realização de entrevistas de seleção."
            ),
            correctIndex = 1,
            steps = listOf(
                "Recrutamento interno aproveita profissionais já conhecidos da casa.",
                "É mais econômico, rápido e estimula a motivação dos empregados pela perspectiva de promoção interna."
            ),
            finalComment = "O recrutamento interno é mais ágil, barato e estimula a progressão funcional."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-16",
            lessonId = "tp_adm_m3_a2",
            statement = "Nos termos do Art. 37, inciso II, da Constituição Federal de 1988, a investidura em cargo ou emprego público em sociedades de economia mista e empresas públicas depende de:",
            options = listOf(
                "A) Mera indicação política da diretoria executiva sem critérios formais.",
                "B) Aprovação prévia em concurso público de provas ou de provas e títulos.",
                "C) Seleção confidencial conduzida exclusivamente por agências privadas.",
                "D) Sorteio público em praça pública.",
                "E) Análise curricular sem atribuição de notas ou pesos."
            ),
            correctIndex = 1,
            steps = listOf(
                "O princípio do concurso público é obrigatório para a Administração Pública direta e indireta (incluindo estatais como a Transpetro e a Petrobras).",
                "Garante impessoalidade, isonomia e mérito na contratação de empregados públicos."
            ),
            finalComment = "A aprovação em concurso público é exigência constitucional obrigatória para admissão em estatais."
        )
    )

    private fun exercisesM3A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-17",
            lessonId = "tp_adm_m3_a3",
            statement = "Na administração de cargos e remuneração, o documento formal que detalha de maneira impessoal as atribuições, deveres, responsabilidades e tarefas rotineiras de uma posição de trabalho é a:",
            options = listOf(
                "A) Análise de Cargos",
                "B) Descrição de Cargos",
                "C) Pesquisa Salarial",
                "D) Avaliação por Pontos",
                "E) Escala de Desempenho"
            ),
            correctIndex = 1,
            steps = listOf(
                "Descrição de cargos relata o que o ocupante faz, como faz e para que faz.",
                "Análise de cargos elenca os requisitos (escolaridade, experiência, esforço) exigidos da pessoa que vai ocupar o cargo."
            ),
            finalComment = "A Descrição de Cargos foca estritamente nas tarefas e responsabilidades do cargo."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-18",
            lessonId = "tp_adm_m3_a3",
            statement = "A busca pela 'Equidade Interna' em um plano de cargos e salários tem por objetivo central assegurar que:",
            options = listOf(
                "A) Todos os colaboradores da empresa recebam rigorosamente o mesmo salário, independentemente do cargo.",
                "B) Haja justiça distributiva e coerência salarial entre os diferentes cargos dentro da própria organização com base na complexidade e responsabilidade.",
                "C) Os salários da empresa sejam inferiores aos praticados pelos concorrentes de mercado.",
                "D) O pagamento seja efetuado exclusivamente por comissões de vendas.",
                "E) Os benefícios obrigatórios sejam cancelados em anos de prejuízo."
            ),
            correctIndex = 1,
            steps = listOf(
                "Equidade interna garante que cargos de mesma complexidade e responsabilidade pertençam a faixas salariais coerentes dentro da própria empresa.",
                "Equidade externa alinha os salários da empresa às médias pagas pelo mercado."
            ),
            finalComment = "Equidade interna garante coerência e justiça relativa entre os cargos dentro da organização."
        )
    )

    private fun exercisesM3A4(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-19",
            lessonId = "tp_adm_m3_a4",
            statement = "No modelo de avaliação de treinamento proposto por Donald Kirkpatrick, a etapa que avalia se os colaboradores treinados estão efetivamente aplicando na sua rotina diária de trabalho os novos conhecimentos e comportamentos aprendidos é o:",
            options = listOf(
                "A) Nível 1 - Reação",
                "B) Nível 2 - Aprendizado",
                "C) Nível 3 - Comportamento no Cargo",
                "D) Nível 4 - Resultados Organizacionais",
                "E) Nível 5 - Auditoria Externa"
            ),
            correctIndex = 2,
            steps = listOf(
                "Nível 1 (Reação): satisfação do participante com o curso.",
                "Nível 2 (Aprendizado): aquisição de conhecimentos comprovada em provas.",
                "Nível 3 (Comportamento): aplicação prática das técnicas na rotina operacional.",
                "Nível 4 (Resultados): retorno financeiro e impacto nos negócios."
            ),
            finalComment = "O Nível 3 de Kirkpatrick avalia a transferência do aprendizado para o comportamento no trabalho."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-20",
            lessonId = "tp_adm_m3_a4",
            statement = "Qual é a principal distinção conceitual entre os processos de Treinamento e de Desenvolvimento de Pessoas na teoria de recursos humanos?",
            options = listOf(
                "A) Treinamento foca em cargos futuros e no longo prazo; Desenvolvimento foca no cargo atual e no curto prazo.",
                "B) Treinamento é voltado para o cargo atual e para necessidades imediatas de curto prazo; Desenvolvimento é orientado para a carreira e para desafios futuros de médio e longo prazo.",
                "C) Treinamento é exclusivo para diretores; Desenvolvimento é exclusivo para operários.",
                "D) Ambos são estritamente sinônimos e não possuem distinção na literatura especializada.",
                "E) Desenvolvimento refere-se unicamente a cursos de graduação universitária formal."
            ),
            correctIndex = 1,
            steps = listOf(
                "Treinamento: prepara a pessoa para o cargo atual no presente/curto prazo.",
                "Desenvolvimento: prepara o profissional para a carreira e cargos futuros no médio/longo prazo."
            ),
            finalComment = "Treinamento mira o presente e o cargo atual; Desenvolvimento mira o futuro e a carreira."
        )
    )

    private fun exercisesM3A5(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-21",
            lessonId = "tp_adm_m3_a5",
            statement = "Na gestão por competências, o modelo mnemônico CHA é composto por três dimensões fundamentais da atuação humana. Assinale a correspondência correta de cada termo:",
            options = listOf(
                "A) Conhecimento (Querer fazer); Habilidade (Saber); Atitude (Saber fazer).",
                "B) Conhecimento (Saber teórico); Habilidade (Saber fazer prático); Atitude (Querer fazer / comportamento).",
                "C) Competência, Hierarquia e Autoridade.",
                "D) Conhecimento, Homologação e Aptidão.",
                "E) Conhecimento (Saber fazer); Habilidade (Saber); Atitude (Poder fazer)."
            ),
            correctIndex = 1,
            steps = listOf(
                "C = Conhecimento = Saber teórico, conceitual.",
                "H = Habilidade = Saber fazer, capacidade técnica e prática.",
                "A = Atitude = Querer fazer, iniciativa, postura e engajamento."
            ),
            finalComment = "CHA: Conhecimento (Saber) + Habilidade (Saber Fazer) + Atitude (Querer Fazer)."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-22",
            lessonId = "tp_adm_m3_a5",
            statement = "O método moderno de avaliação de desempenho no qual o colaborador é avaliado simultaneamente por seu superior imediato, seus pares de trabalho, seus subordinados diretos, clientes internos/externos e por si próprio (autoavaliação) é a:",
            options = listOf(
                "A) Avaliação por Escalas Gráficas",
                "B) Avaliação por Escolha Forçada",
                "C) Avaliação 360 Graus (ou Circular)",
                "D) Pesquisa de Campo",
                "E) Avaliação por Incidentes Críticos"
            ),
            correctIndex = 2,
            steps = listOf(
                "Avaliação 360° envolve todas as fontes circulares de interação do colaborador.",
                "Proporciona uma visão rica e abrangente sobre competências e relacionamentos interpessoais."
            ),
            finalComment = "Avaliação 360 Graus coleta percepções de superiores, pares, subordinados e autoavaliação."
        )
    )

    private fun exercisesM3A6(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-23",
            lessonId = "tp_adm_m3_a6",
            statement = "Em relação aos conceitos de Cultura Organizacional e Clima Organizacional, é correto afirmar que:",
            options = listOf(
                "A) O Clima é profundo e permanente, enquanto a Cultura muda diariamente conforme os humores da chefia.",
                "B) A Cultura é o conjunto estruturado de valores e crenças históricas profundas da organização, enquanto o Clima é a percepção momentânea da atmosfera de trabalho.",
                "C) Não existe diferença técnica entre os termos, tratando-se de conceitos intercambiáveis.",
                "D) O Clima decorre exclusivamente de normas escritas de segurança.",
                "E) A Cultura é mensurada por pesquisas de satisfação de frequência semanal."
            ),
            correctIndex = 1,
            steps = listOf(
                "Cultura = valores profundos, crenças e hábitos consolidados historicamente ao longo do tempo.",
                "Clima = percepção circunstancial e psicológica momentânea dos empregados em determinado período."
            ),
            finalComment = "Cultura é estrutural e profunda; Clima é a percepção imediata e momentânea do ambiente."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-24",
            lessonId = "tp_adm_m3_a6",
            statement = "Constitui exemplo de benefício voluntário ou espontâneo concedido pela empresa para reter talentos e melhorar a qualidade de vida no trabalho:",
            options = listOf(
                "A) Fundo de Garantia do Tempo de Serviço (FGTS)",
                "B) Décimo Terceiro Salário",
                "C) Plano de Previdência Complementar Fechada (Petros)",
                "D) Descanso Semanal Remunerado (DSR)",
                "E) Férias remuneradas anuais com adicional de um terço"
            ),
            correctIndex = 2,
            steps = listOf(
                "FGTS, 13º, DSR e Férias são benefícios legais obrigatórios impostos pela CLT e CF/88.",
                "Planos de previdência complementar e seguro de saúde ampliado são benefícios voluntários oferecidos pela empresa."
            ),
            finalComment = "Planos de previdência complementar fechada constituem benefícios voluntários e estratégicos."
        )
    )

    private fun exercisesM4A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-25",
            lessonId = "tp_adm_m4_a1",
            statement = "No estabelecimento de metas pelo padrão SMART, a letra 'M' indica que toda meta administrativa eficaz deve ser obrigatoriamente:",
            options = listOf(
                "A) Motivadora",
                "B) Mensurável",
                "C) Moderada",
                "D) Moral",
                "E) Multidisciplinar"
            ),
            correctIndex = 1,
            steps = listOf(
                "S = Specific (Específica)",
                "M = Measurable (Mensurável)",
                "A = Achievable (Atingível)",
                "R = Relevant (Relevante)",
                "T = Time-bound (Temporal / com prazo)"
            ),
            finalComment = "A meta deve ser mensurável quantitativamente para permitir acompanhamento objetivo."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-26",
            lessonId = "tp_adm_m4_a1",
            statement = "O valor numérico inicial obtido antes de qualquer intervenção ou melhoria em um processo, utilizado como ponto de partida histórico para comparar a evolução de um indicador, é denominado:",
            options = listOf(
                "A) Meta SMART",
                "B) Linha de Base (Baseline)",
                "C) Desvio Padrão",
                "D) Ponto de Ruptura",
                "E) Limite de Tolerância Crítica"
            ),
            correctIndex = 1,
            steps = listOf(
                "Linha de base (baseline) é o patamar inicial de desempenho de um processo.",
                "Serve de parâmetro de confronto para saber se as ações surtiram efeito positivo ou negativo."
            ),
            finalComment = "A Linha de Base (Baseline) é o ponto de partida de medição histórica de um processo."
        )
    )

    private fun exercisesM4A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-27",
            lessonId = "tp_adm_m4_a2",
            statement = "Um terminal expediu 250 cargas de combustível durante o mês. Desse total, 230 cargas foram despachadas pontualmente dentro da janela horária contratual acordada com os clientes. Qual foi a Taxa de Pontualidade (On-Time Delivery) observada?",
            options = listOf(
                "A) 88%",
                "B) 90%",
                "C) 92%",
                "D) 94%",
                "E) 96%"
            ),
            correctIndex = 2,
            steps = listOf(
                "Taxa = (Cargas no prazo / Cargas totais) × 100%.",
                "Taxa = (230 / 250) × 100%.",
                "230 / 250 = 0,92 × 100% = 92%."
            ),
            finalComment = "A taxa de pontualidade nas expedições foi de 92%."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-28",
            lessonId = "tp_adm_m4_a2",
            statement = "Um indicador que confronta o volume de óleo diesel transferido (em m³) com a quantidade de horas de trabalho da equipe operacional necessárias para a transferência mede primariamente a:",
            options = listOf(
                "A) Efetividade institucional",
                "B) Produtividade do trabalho",
                "C) Amplitude administrativa",
                "D) Rotatividade de pessoal",
                "E) Rigidez funcional"
            ),
            correctIndex = 1,
            steps = listOf(
                "A relação direta entre quantidade de produtos gerados (output) e insumo de mão de obra (input) mensura a produtividade.",
                "Trata-se de indicador clássico de produtividade do fator trabalho."
            ),
            finalComment = "A razão output/input de mão de obra calcula a produtividade do trabalho."
        )
    )

    private fun exercisesM4A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-29",
            lessonId = "tp_adm_m4_a3",
            statement = "O Balanced Scorecard (BSC) é um sistema de gestão estratégica que equilibra o acompanhamento da organização sob quatro perspectivas essenciais. Assinale a alternativa que apresenta as quatro perspectivas clássicas do BSC:",
            options = listOf(
                "A) Financeira, Clientes, Processos Internos e Aprendizado e Crescimento.",
                "B) Orçamentária, Fiscal, Contábil e Patrimonial.",
                "C) Recursos Humanos, Logística, Manutenção e Operações.",
                "D) Curto Prazo, Médio Prazo, Longo Prazo e Contingência.",
                "E) Vendas, Compras, Estoques e Expedição."
            ),
            correctIndex = 0,
            steps = listOf(
                "As 4 perspectivas do BSC criadas por Kaplan e Norton são:",
                "1. Financeira.",
                "2. Clientes.",
                "3. Processos Internos.",
                "4. Aprendizado e Crescimento."
            ),
            finalComment = "O BSC monitora as perspectivas Financeira, Clientes, Processos Internos e Aprendizado/Crescimento."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-30",
            lessonId = "tp_adm_m4_a3",
            statement = "Em um painel de gestão à vista de monitoramento de desempenho (Dashboard), a cor 'Vermelha' no semáforo de um indicador de segurança operacional sinaliza que:",
            options = listOf(
                "A) A meta foi plenamente superada e o processo está em conformidade ótima.",
                "B) O processo encontra-se dentro da faixa normal de operação aceitável.",
                "C) O indicador atingiu um nível de desvio crítico fora dos padrões toleráveis, exigindo investigação imediata de causa raiz e plano de ação corretiva.",
                "D) Os dados de medição foram perdidos pela equipe de TI.",
                "E) A empresa deve cancelar todas as metas futuras."
            ),
            correctIndex = 2,
            steps = listOf(
                "Verde: meta cumprida com êxito.",
                "Amarelo: alerta preventivo na margem de tolerância.",
                "Vermelho: anomalia e descumprimento crítico da meta, acionando planos de bloqueio e investigação."
            ),
            finalComment = "Farol vermelho indica desvio crítico que demanda intervenção e correção imediata."
        )
    )

    private fun exercisesM5A1(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-31",
            lessonId = "tp_adm_m5_a1",
            statement = "O Sistema de Gestão Integrado (SGI) reúne normas técnicas internacionais para harmonizar processos industriais. A norma internacional ABNT NBR ISO voltada especificamente para o estabelecimento de diretrizes de Gestão Ambiental e prevenção da poluição é a:",
            options = listOf(
                "A) ISO 9001",
                "B) ISO 14001",
                "C) ISO 45001",
                "D) ISO 27001",
                "E) ISO 31000"
            ),
            correctIndex = 1,
            steps = listOf(
                "ISO 9001: Gestão da Qualidade.",
                "ISO 14001: Gestão Ambiental.",
                "ISO 45001: Segurança e Saúde no Trabalho.",
                "ISO 27001: Segurança da Informação.",
                "ISO 31000: Gestão de Riscos."
            ),
            finalComment = "A norma de Gestão Ambiental de referência mundial é a ISO 14001."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-32",
            lessonId = "tp_adm_m5_a1",
            statement = "Qual é o principal benefício organizacional obtido pela integração dos sistemas de gestão da Qualidade, Meio Ambiente e SMS em um único Sistema de Gestão Integrado (SGI)?",
            options = listOf(
                "A) Aumento da quantidade de manuais burocráticos e duplicidade de procedimentos internos.",
                "B) Redução de custos e burocracia com a eliminação de redundâncias, adoção de política única e realização de auditorias conjuntas integradas.",
                "C) Descentralização caótica das responsabilidades operacionais.",
                "D) Desobrigação do cumprimento da legislação trabalhista e ambiental.",
                "E) Fim da exigência de auditorias internas periódicas."
            ),
            correctIndex = 1,
            steps = listOf(
                "O SGI elimina a fragmentação de procedimentos e manuais.",
                "Unifica a política corporativa, otimiza treinamentos e permite auditorias conjuntas com redução expressiva de custos e burocracia."
            ),
            finalComment = "O SGI unifica processos, reduz redundâncias documentais e barateia auditorias."
        )
    )

    private fun exercisesM5A2(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-33",
            lessonId = "tp_adm_m5_a2",
            statement = "Na sistemática do SGI, quando uma auditoria detecta o descumprimento de um requisito normativo ou contratual (Não Conformidade), a ação que atua especificamente sobre a CAUSA RAIZ do problema para EVITAR QUE ELE VOLTE A OCORRER é denominada:",
            options = listOf(
                "A) Disposição Imediata",
                "B) Correção Simples",
                "C) Ação Corretiva",
                "D) Concessão Operacional",
                "E) Ação de Descarte"
            ),
            correctIndex = 2,
            steps = listOf(
                "Correção ou disposição imediata atua no efeito para remediar o sintoma.",
                "Ação Corretiva investiga e elimina a causa raiz para evitar a reincidência futura do desvio."
            ),
            finalComment = "Ação Corretiva elimina a causa raiz para evitar que a falha torne a ocorrer."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-34",
            lessonId = "tp_adm_m5_a2",
            statement = "A auditoria realizada por uma entidade de certificação externa, independente e formalmente acreditada, com vistas à concessão oficial ou renovação do certificado de conformidade com uma norma ISO, é classificada como:",
            options = listOf(
                "A) Auditoria de Primeira Parte",
                "B) Auditoria Interna de Rotina",
                "C) Auditoria de Segunda Parte",
                "D) Auditoria de Terceira Parte",
                "E) Autoavaliação de Desempenho"
            ),
            correctIndex = 3,
            steps = listOf(
                "1ª Parte: Interna (feita pela própria empresa).",
                "2ª Parte: Feita em fornecedores.",
                "3ª Parte: Conduzida por organismo certificador independente e neutro para concessão de selo ISO."
            ),
            finalComment = "Auditoria de Terceira Parte é realizada por organismos certificadores independentes."
        )
    )

    private fun exercisesM5A3(): List<MathExercise> = listOf(
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-35",
            lessonId = "tp_adm_m5_a3",
            statement = "Na hierarquia de medidas de controle de riscos ocupacionais prevista na norma ISO 45001, qual medida deve ser adotada apenas como última alternativa, após esgotadas as possibilidades de eliminação, substituição e controles de engenharia e administrativos?",
            options = listOf(
                "A) Substituição por materiais menos perigosos",
                "B) Uso de Equipamentos de Proteção Individual (EPI)",
                "C) Instalação de barreiras físicas e enclausuramento de máquinas",
                "D) Eliminação física do perigo",
                "E) Treinamento operacional dos trabalhadores"
            ),
            correctIndex = 1,
            steps = listOf(
                "Hierarquia de Controle de Riscos:",
                "1. Eliminação (mais eficaz).",
                "2. Substituição.",
                "3. Controles de Engenharia.",
                "4. Controles Administrativos.",
                "5. EPI (última barreira de defesa, menos eficaz)."
            ),
            finalComment = "O uso de EPI é a última linha de defesa na hierarquia de segurança ocupacional."
        ),
        TranspetroDidacticProvider.buildExercise(
            id = "TP-ADM-SUP-36",
            lessonId = "tp_adm_m5_a3",
            statement = "Na metodologia de gestão de riscos de SMS, o Risco Operacional é conceituado matematicamente e tecnicamente como o produto entre:",
            options = listOf(
                "A) O custo financeiro do equipamento e o número de empregados expostos.",
                "B) A Probabilidade de ocorrência do evento adverso e a Severidade (Impacto/Gravidade) dos danos resultantes.",
                "C) A quantidade de normas vigentes e a taxa de câmbio.",
                "D) O tempo de garantia do maquinário e o volume produzido.",
                "E) A velocidade do vento e a profundidade do duto."
            ),
            correctIndex = 1,
            steps = listOf(
                "Risco = Probabilidade de ocorrência × Severidade do impacto.",
                "Combina a frequência esperada da falha com a gravidade potencial das lesões ou impactos ambientais."
            ),
            finalComment = "Risco é o produto da probabilidade de ocorrência pela gravidade de suas consequências."
        )
    )
}
