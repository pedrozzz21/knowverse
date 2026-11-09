package com.example.data.transpetro

import com.example.data.model.DifficultyTier
import com.example.data.model.EnemTrailDiscipline
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado
import com.example.data.model.LearningUnit

/**
 * Currículo oficial do tópico "Concurso Transpetro - ADM e Controle" (Fundação Cesgranrio — Nível Médio).
 *
 * Estrutura aprovada na Fase 1:
 * - Grupo A — Conhecimentos Específicos (40 questões na prova):
 *   1. Processos Administrativos e Legislação (9 tópicos • 27 subtópicos/aulas: tp_adm_m1_a1..tp_adm_m9_a3)
 *   2. Finanças e Contabilidade (4 tópicos • 15 subtópicos/aulas: tp_fin_m1_a1..tp_fin_m4_a2)
 *   3. Logística e Gestão da Cadeia de Suprimentos (6 tópicos • 18 subtópicos/aulas: tp_log_m1_a1..tp_log_m6_a1)
 *   4. Noções de Informática (5 tópicos • 13 subtópicos/aulas: tp_inf_m1_a1..tp_inf_m5_a3)
 * - Grupo B — Língua Portuguesa (10 questões na prova):
 *   5. Língua Portuguesa (11 tópicos • 22 subtópicos/aulas: tp_por_m1_a1..tp_por_m11_a2)
 * - Grupo C — Matemática (10 questões na prova):
 *   6. Matemática (12 tópicos • 25 subtópicos/aulas: tp_mat_m1_a1..tp_mat_m12_a2)
 *
 * Total: 6 Matérias • 47 Tópicos • 120 Subtópicos/Aulas.
 */
object TranspetroCurriculum {

    const val TOPIC_TITLE = "Concurso Transpetro - ADM e Controle"

    // =========================================================================
    // MATÉRIA 1: PROCESSOS ADMINISTRATIVOS E LEGISLAÇÃO (9 TÓPICOS • 27 AULAS)
    // =========================================================================
    val admDiscipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "transpetro_adm",
            name = "Processos Administrativos e Legislação",
            description = "Conhecimentos Específicos (Grupo A) — Fundamentos de administração, processos, gestão de pessoas, indicadores, SGI, manutenção, patrimônio, compras, contratos e Lei nº 13.303/2016.",
            modules = listOf(
                EnemTrailModule(
                    id = "mod_tp_adm_01",
                    moduleNumber = 1,
                    title = "1. Fundamentos da Administração",
                    summary = "Conceitos fundamentais, eficiência, eficácia, efetividade, funções administrativas (PODC), níveis organizacionais e estrutura.",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m1_a1", "mod_tp_adm_01", 1, "1.1. Conceitos Fundamentais de Administração", "Organização, recursos, eficiência, eficácia e efetividade administrativa", 35),
                        EnemTrailLesson("tp_adm_m1_a2", "mod_tp_adm_01", 2, "1.2. Funções Administrativas (PODC)", "Planejamento, Organização, Direção e Controle no ciclo administrativo", 35),
                        EnemTrailLesson("tp_adm_m1_a3", "mod_tp_adm_01", 3, "1.3. Níveis Organizacionais e Tipos de Planejamento", "Níveis estratégico, tático e operacional e seus planos correspondentes", 35),
                        EnemTrailLesson("tp_adm_m1_a4", "mod_tp_adm_01", 4, "1.4. Estrutura Organizacional, Delegação, Liderança e Comunicação", "Departamentalização, hierarquia, delegação de autoridade, estilos de liderança e fluxos de comunicação", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_01", "mod_tp_adm_01", "Simulado: Fundamentos da Administração", "Conclua as 4 aulas do Tópico 1", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_adm_02",
                    moduleNumber = 2,
                    title = "2. Gestão de Processos e Qualidade",
                    summary = "Mapeamento de processos, fluxogramas, gargalos, padronização operacional, ciclo PDCA e ferramentas da qualidade.",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m2_a1", "mod_tp_adm_02", 1, "2.1. Mapeamento, Modelagem e Padronização de Processos", "Entradas, transformação, saídas, fluxogramas, gargalos e procedimentos operacionais padrão", 35),
                        EnemTrailLesson("tp_adm_m2_a2", "mod_tp_adm_02", 2, "2.2. Ciclo PDCA, Melhoria Contínua e Ferramentas da Qualidade", "Etapas Plan-Do-Check-Act, Diagrama de Ishikawa, Gráfico de Pareto e 5W2H", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_02", "mod_tp_adm_02", "Simulado: Gestão de Processos", "Conclua as 2 aulas do Tópico 2", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_adm_03",
                    moduleNumber = 3,
                    title = "3. Gestão de Pessoas (Recursos Humanos)",
                    summary = "Equipes, recrutamento e seleção, cargos e salários, treinamento e desenvolvimento, avaliação de desempenho, competências e relações de trabalho.",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m3_a1", "mod_tp_adm_03", 1, "3.1. Conceitos Fundamentais e Gestão de Equipes", "Papel estratégico de RH, comportamento organizacional, motivação e trabalho em equipe", 35),
                        EnemTrailLesson("tp_adm_m3_a2", "mod_tp_adm_03", 2, "3.2. Recrutamento e Seleção de Pessoas", "Recrutamento interno, externo e misto; técnicas de seleção e ambientação (onboarding)", 35),
                        EnemTrailLesson("tp_adm_m3_a3", "mod_tp_adm_03", 3, "3.3. Administração de Cargos, Salários e Carreira", "Descrição e análise de cargos, avaliação de cargos, faixas salariais e progressão", 35),
                        EnemTrailLesson("tp_adm_m3_a4", "mod_tp_adm_03", 4, "3.4. Treinamento, Desenvolvimento e Educação Corporativa", "Diferença entre T&D, levantamento de necessidades (LNT), execução e avaliação de treinamento", 35),
                        EnemTrailLesson("tp_adm_m3_a5", "mod_tp_adm_03", 5, "3.5. Avaliação de Desempenho e Gestão por Competências", "Conhecimentos, Habilidades e Atitudes (CHA), lacuna (gap) de competências e métodos de avaliação", 40),
                        EnemTrailLesson("tp_adm_m3_a6", "mod_tp_adm_03", 6, "3.6. Relações de Trabalho, Clima e Benefícios", "Clima vs. cultura organizacional, qualidade de vida e gestão de planos de benefícios", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_03", "mod_tp_adm_03", "Simulado: Gestão de Pessoas", "Conclua as 6 aulas do Tópico 3", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_adm_04",
                    moduleNumber = 4,
                    title = "4. Gestão de Indicadores",
                    summary = "Conceito de indicadores, KPIs de desempenho, eficiência, eficácia, produtividade, metas e monitoramento de resultados.",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m4_a1", "mod_tp_adm_04", 1, "4.1. Conceito de Indicadores, Índices, Padrões e Metas", "Propriedades de um bom indicador, linha de base, metas SMART e periodicidade", 35),
                        EnemTrailLesson("tp_adm_m4_a2", "mod_tp_adm_04", 2, "4.2. Indicadores de Eficiência, Eficácia e Produtividade", "Cálculo de taxa de atendimento no prazo, produtividade operacional e qualidade", 35),
                        EnemTrailLesson("tp_adm_m4_a3", "mod_tp_adm_04", 3, "4.3. Monitoramento de Resultados e Análise de Desvios", "Painéis de acompanhamento, análise crítica de variações e planos de ação corretiva", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_04", "mod_tp_adm_04", "Simulado: Gestão de Indicadores", "Conclua as 3 aulas do Tópico 4", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_adm_05",
                    moduleNumber = 5,
                    title = "5. Sistema de Gestão Integrado (SGI)",
                    summary = "Integração de Qualidade, Meio Ambiente, Saúde e Segurança Ocupacional (SMS), auditorias, não conformidades e gestão de riscos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m5_a1", "mod_tp_adm_05", 1, "5.1. Conceitos e Finalidade do SGI (Qualidade, Meio Ambiente e SMS)", "Coerência entre políticas, redução de retrabalho e visão sistêmica de processos", 35),
                        EnemTrailLesson("tp_adm_m5_a2", "mod_tp_adm_05", 2, "5.2. Auditorias, Não Conformidades e Ações Corretivas/Preventivas", "Tratamento de desvios, análise de causa raiz e melhoria contínua no SGI", 35),
                        EnemTrailLesson("tp_adm_m5_a3", "mod_tp_adm_05", 3, "5.3. Gestão de Riscos Operacionais e Sustentabilidade", "Matriz de probabilidade versus impacto, mitigação de riscos e diretrizes de SMS", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_05", "mod_tp_adm_05", "Simulado: Sistema de Gestão Integrado", "Conclua as 3 aulas do Tópico 5", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_adm_06",
                    moduleNumber = 6,
                    title = "6. Gestão da Manutenção",
                    summary = "Manutenção corretiva, preventiva e preditiva (baseada na condição), Planejamento e Controle da Manutenção (PCM).",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m6_a1", "mod_tp_adm_06", 1, "6.1. Tipos de Manutenção: Corretiva, Preventiva e Preditiva", "Diferenças conceituais, monitoramento da condição real e custos de falha", 35),
                        EnemTrailLesson("tp_adm_m6_a2", "mod_tp_adm_06", 2, "6.2. Planejamento e Controle da Manutenção (PCM)", "Ordens de serviço, cronogramas, disponibilidade física e confiabilidade operacional", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_06", "mod_tp_adm_06", "Simulado: Gestão da Manutenção", "Conclua as 2 aulas do Tópico 6", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_adm_07",
                    moduleNumber = 7,
                    title = "7. Administração Patrimonial",
                    summary = "Patrimônio permanente, bens móveis e imóveis, tombamento, inventário físico, conservação, movimentação e baixa.",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m7_a1", "mod_tp_adm_07", 1, "7.1. Conceitos de Patrimônio, Bens Móveis/Imóveis e Tombamento", "Bens permanentes vs. materiais de consumo, registro patrimonial e plaquetamento", 35),
                        EnemTrailLesson("tp_adm_m7_a2", "mod_tp_adm_07", 2, "7.2. Inventário Patrimonial, Conservação, Movimentação e Baixa", "Verificação físico-contábil, termos de responsabilidade, alienação e desfazimento", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_07", "mod_tp_adm_07", "Simulado: Administração Patrimonial", "Conclua as 2 aulas do Tópico 7", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_adm_08",
                    moduleNumber = 8,
                    title = "8. Compras e Contratos",
                    summary = "Planejamento das contratações, especificação do objeto, gestão e fiscalização de contratos administrativos e empresariais.",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m8_a1", "mod_tp_adm_08", 1, "8.1. Planejamento das Contratações e Especificação do Objeto", "Estudo preliminar, termo de referência, clareza técnica e isonomia", 35),
                        EnemTrailLesson("tp_adm_m8_a2", "mod_tp_adm_08", 2, "8.2. Gestão e Fiscalização de Contratos", "Atribuições do gestor e do fiscal, registro de ocorrências, medição, aditivos e glosas", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_08", "mod_tp_adm_08", "Simulado: Compras e Contratos", "Conclua as 2 aulas do Tópico 8", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_adm_09",
                    moduleNumber = 9,
                    title = "9. Legislação Aplicada, Ética e Redação Oficial",
                    summary = "Lei nº 13.303/2016 (Lei das Estatais), relação com a Lei nº 14.133/2021, ética, integridade e comunicação administrativa.",
                    lessons = listOf(
                        EnemTrailLesson("tp_adm_m9_a1", "mod_tp_adm_09", 1, "9.1. Lei nº 13.303/2016 (Lei das Estatais): Governança e Licitações", "Regime licitatório das empresas públicas e sociedades de economia mista, dispensa e inexigibilidade", 40),
                        EnemTrailLesson("tp_adm_m9_a2", "mod_tp_adm_09", 2, "9.2. Contratos na Lei nº 13.303/2016 e Distinção frente à Lei nº 14.133/2021", "Cláusulas necessárias, duração, alterações contratuais por acordo e âmbito de aplicação da Lei 14.133/2021", 40),
                        EnemTrailLesson("tp_adm_m9_a3", "mod_tp_adm_09", 3, "9.3. Ética, Integridade, Conflito de Interesses e Redação Oficial", "Conduta íntegra, prevenção a conflitos de interesses e atributos da comunicação oficial (clareza, concisão, impessoalidade)", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_adm_09", "mod_tp_adm_09", "Simulado: Legislação Aplicada", "Conclua as 3 aulas do Tópico 9", true, 100, 10)
                )
            )
        )
    }

    // =========================================================================
    // MATÉRIA 2: FINANÇAS E CONTABILIDADE (4 TÓPICOS • 15 AULAS)
    // =========================================================================
    val finDiscipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "transpetro_fin",
            name = "Finanças e Contabilidade",
            description = "Conhecimentos Específicos (Grupo A) — Matemática financeira, fluxo de caixa, capital de giro, contabilidade geral, Balanço Patrimonial, DRE, custos e orçamento.",
            modules = listOf(
                EnemTrailModule(
                    id = "mod_tp_fin_01",
                    moduleNumber = 1,
                    title = "1. Matemática Financeira",
                    summary = "Porcentagem financeira, juros simples, juros compostos, equivalência de taxas e descontos simples e compostos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_fin_m1_a1", "mod_tp_fin_01", 1, "1.1. Porcentagem Financeira, Aumentos e Reduções Sucessivas", "Fatores multiplicativos, abatimentos sucessivos e variação percentual acumulada", 35),
                        EnemTrailLesson("tp_fin_m1_a2", "mod_tp_fin_01", 2, "1.2. Juros Simples, Capital, Montante e Taxas Proporcionais", "Regime linear de capitalização (J = C·i·n e M = C·(1 + i·n)) e conversão temporal", 35),
                        EnemTrailLesson("tp_fin_m1_a3", "mod_tp_fin_01", 3, "1.3. Juros Compostos, Taxa Nominal e Taxa Efetiva", "Capitalização exponencial (M = C·(1 + i)^n), taxas equivalentes e capitalização composta", 40),
                        EnemTrailLesson("tp_fin_m1_a4", "mod_tp_fin_01", 4, "1.4. Descontos Simples e Compostos (Comercial e Racional)", "Valor nominal, valor atual/líquido, desconto bancário/por fora e desconto racional/por dentro", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_fin_01", "mod_tp_fin_01", "Simulado: Matemática Financeira", "Conclua as 4 aulas do Tópico 1", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_fin_02",
                    moduleNumber = 2,
                    title = "2. Fluxo de Caixa e Administração Financeira",
                    summary = "Entradas e saídas de caixa, fluxo operacional, de investimento e financiamento, regime de caixa e capital de giro.",
                    lessons = listOf(
                        EnemTrailLesson("tp_fin_m2_a1", "mod_tp_fin_02", 1, "2.1. Conceitos Fundamentais de Fluxo de Caixa: Entradas, Saídas e Saldos", "Regime de caixa, saldo inicial, geração líquida de caixa e saldo final disponível", 35),
                        EnemTrailLesson("tp_fin_m2_a2", "mod_tp_fin_02", 2, "2.2. Fluxo de Caixa Operacional, de Investimento e de Financiamento", "Classificação das movimentações financeiras e representação gráfica de fluxos", 35),
                        EnemTrailLesson("tp_fin_m2_a3", "mod_tp_fin_02", 3, "2.3. Capital de Giro Líquido e Indicadores de Liquidez", "Capital circulante líquido (AC - PC), liquidez corrente e equilíbrio financeiro de curto prazo", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_fin_02", "mod_tp_fin_02", "Simulado: Fluxo de Caixa", "Conclua as 3 aulas do Tópico 2", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_fin_03",
                    moduleNumber = 3,
                    title = "3. Contabilidade Geral",
                    summary = "Patrimônio, equação fundamental, ativo, passivo, patrimônio líquido, Balanço Patrimonial, DRE, competência e depreciação.",
                    lessons = listOf(
                        EnemTrailLesson("tp_fin_m3_a1", "mod_tp_fin_03", 1, "3.1. Patrimônio: Bens, Direitos, Obrigações e Equação Patrimonial", "Conceito contábil de patrimônio e situações líquidas (Ativo = Passivo + Patrimônio Líquido)", 35),
                        EnemTrailLesson("tp_fin_m3_a2", "mod_tp_fin_03", 2, "3.2. Ativo, Passivo e Patrimônio Líquido: Grupos e Liquidez/Exigibilidade", "Ativo Circulante e Não Circulante; Passivo Circulante, Não Circulante e PL", 35),
                        EnemTrailLesson("tp_fin_m3_a3", "mod_tp_fin_03", 3, "3.3. Regimes Contábeis (Competência vs. Caixa) e Fatos Contábeis", "Reconhecimento de receitas e despesas pelo fato gerador e atos/fatos permutativos, modificativos e mistos", 40),
                        EnemTrailLesson("tp_fin_m3_a4", "mod_tp_fin_03", 4, "3.4. Balanço Patrimonial: Estrutura e Análise Estática", "Ordem decrescente de grau de liquidez e de exigibilidade; origens e aplicações de recursos", 35),
                        EnemTrailLesson("tp_fin_m3_a5", "mod_tp_fin_03", 5, "3.5. Demonstração do Resultado do Exercício (DRE)", "Receita bruta, deduções, receita líquida, CMV/CSP, lucro bruto, despesas operacionais e resultado líquido", 40),
                        EnemTrailLesson("tp_fin_m3_a6", "mod_tp_fin_03", 6, "3.6. Depreciação e Amortização Contábil", "Método linear (quotas constantes), valor residual, vida útil econômica e conta retificadora do ativo", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_fin_03", "mod_tp_fin_03", "Simulado: Contabilidade Geral", "Conclua as 6 aulas do Tópico 3", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_fin_04",
                    moduleNumber = 4,
                    title = "4. Custos e Controle Orçamentário",
                    summary = "Classificação de custos (fixos, variáveis, diretos e indiretos) e acompanhamento orçamentário (orçado vs. realizado).",
                    lessons = listOf(
                        EnemTrailLesson("tp_fin_m4_a1", "mod_tp_fin_04", 1, "4.1. Terminologia e Classificação de Custos", "Diferença entre gasto, custo, despesa, investimento e perda; custos fixos, variáveis, diretos e indiretos", 35),
                        EnemTrailLesson("tp_fin_m4_a2", "mod_tp_fin_04", 2, "4.2. Orçamento Empresarial e Controle Orçamentário", "Previsão orçamentária, análise de desvios entre orçado e realizado e controle financeiro", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_fin_04", "mod_tp_fin_04", "Simulado: Custos e Orçamento", "Conclua as 2 aulas do Tópico 4", true, 100, 10)
                )
            )
        )
    }

    // =========================================================================
    // MATÉRIA 3: LOGÍSTICA E GESTÃO DA CADEIA DE SUPRIMENTOS (6 TÓPICOS • 18 AULAS)
    // =========================================================================
    val logDiscipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "transpetro_log",
            name = "Logística e Gestão da Cadeia de Suprimentos",
            description = "Conhecimentos Específicos (Grupo A) — Administração de materiais, gestão de estoques, compras, armazenagem e layout, modais de transporte (com ênfase dutoviária e aquaviária) e suprimento em estatais.",
            modules = listOf(
                EnemTrailModule(
                    id = "mod_tp_log_01",
                    moduleNumber = 1,
                    title = "1. Administração de Materiais",
                    summary = "Conceitos, objetivos, atributos de classificação (ABC, XYZ, periculosidade, perecibilidade), padronização e codificação.",
                    lessons = listOf(
                        EnemTrailLesson("tp_log_m1_a1", "mod_tp_log_01", 1, "1.1. Conceitos e Gestão de Recursos Materiais", "Tríade suprir-armazenar-distribuir, nível de serviço e acuracidade dos controles", 35),
                        EnemTrailLesson("tp_log_m1_a2", "mod_tp_log_01", 2, "1.2. Classificação de Materiais: Atributos, Criticidade (XYZ) e Periculosidade", "Abrangência, flexibilidade e praticidade; materiais X, Y e Z; inflamáveis e perecíveis", 35),
                        EnemTrailLesson("tp_log_m1_a3", "mod_tp_log_01", 3, "1.3. Especificação, Padronização, Normalização e Codificação", "Redução de variedades, identificação unívoca de itens, sistemas decimal, alfanumérico e código de barras/RFID", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_log_01", "mod_tp_log_01", "Simulado: Administração de Materiais", "Conclua as 3 aulas do Tópico 1", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_log_02",
                    moduleNumber = 2,
                    title = "2. Gestão de Estoques",
                    summary = "Previsão de demanda, estoque mínimo/segurança, ponto de pedido, LEC, giro, cobertura, curva ABC, PEPS/FEFO e inventário.",
                    lessons = listOf(
                        EnemTrailLesson("tp_log_m2_a1", "mod_tp_log_02", 1, "2.1. Previsão de Demanda e Planejamento de Estoques", "Demanda dependente vs. independente, método da média móvel, tendência, sazonalidade e obsolescência", 35),
                        EnemTrailLesson("tp_log_m2_a2", "mod_tp_log_02", 2, "2.2. Estoque Mínimo (Segurança), Lead Time, Ponto de Pedido e Estoque Máximo", "Fórmula do Ponto de Pedido (PP = C × TR + EM), gráfico dente de serra e proteção contra incertezas", 40),
                        EnemTrailLesson("tp_log_m2_a3", "mod_tp_log_02", 3, "2.3. Custos de Estoque e Lote Econômico de Compra (LEC)", "Custos de armazenagem/manutenção, custos de pedido e custos de falta (ruptura)", 35),
                        EnemTrailLesson("tp_log_m2_a4", "mod_tp_log_02", 4, "2.4. Giro de Estoque, Cobertura e Classificação ABC (Pareto)", "Cálculo de giro e cobertura em dias; priorização financeira dos itens Classe A, B e C", 40),
                        EnemTrailLesson("tp_log_m2_a5", "mod_tp_log_02", 5, "2.5. Métodos de Controle (PEPS/FIFO, UEPS, Custo Médio, FEFO) e Inventários", "Valoração de estoques, controle de validade (FEFO), inventário geral vs. rotativo (cíclico) e acuracidade", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_log_02", "mod_tp_log_02", "Simulado: Gestão de Estoques", "Conclua as 5 aulas do Tópico 2", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_log_03",
                    moduleNumber = 3,
                    title = "3. Gestão de Compras",
                    summary = "Processo de compras, pesquisa de mercado, seleção e avaliação de fornecedores, cotação, negociação e Just-in-Time.",
                    lessons = listOf(
                        EnemTrailLesson("tp_log_m3_a1", "mod_tp_log_03", 1, "3.1. Processo de Compras, Cotação e Pesquisa de Mercado", "Etapas do ciclo de compras, especificação por desempenho, estimativa de preços e segregação de funções", 35),
                        EnemTrailLesson("tp_log_m3_a2", "mod_tp_log_03", 2, "3.2. Seleção, Homologação e Avaliação de Fornecedores", "Critérios técnicos, capacidade de entrega, qualidade, cumprimento de prazos e diligenciamento (follow-up)", 35),
                        EnemTrailLesson("tp_log_m3_a3", "mod_tp_log_03", 3, "3.3. Negociação, Compras Centralizadas/Descentralizadas e Just-in-Time", "Estratégias de aquisição corporativa e sincronização com a demanda (JIT)", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_log_03", "mod_tp_log_03", "Simulado: Gestão de Compras", "Conclua as 3 aulas do Tópico 3", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_log_04",
                    moduleNumber = 4,
                    title = "4. Armazenagem e Layout",
                    summary = "Recebimento, conferência, endereçamento, movimentação, expedição, verticalização e layout de armazéns.",
                    lessons = listOf(
                        EnemTrailLesson("tp_log_m4_a1", "mod_tp_log_04", 1, "4.1. Recebimento, Conferência e Aceitação de Materiais", "Conferência quantitativa, qualitativa (inspeção técnica) e documental (nota fiscal vs. pedido)", 35),
                        EnemTrailLesson("tp_log_m4_a2", "mod_tp_log_04", 2, "4.2. Endereçamento, Estocagem, Picking e Expedição", "Sistemas de endereçamento fixo e aleatório, separação de pedidos, embalagem e carregamento", 35),
                        EnemTrailLesson("tp_log_m4_a3", "mod_tp_log_04", 3, "4.3. Movimentação de Materiais, Verticalização e Layout de Armazéns", "Escolha de equipamentos (empilhadeiras, paleteiras, transelevadores), aproveitamento de altura e fluxo em linha/U", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_log_04", "mod_tp_log_04", "Simulado: Armazenagem e Layout", "Conclua as 3 aulas do Tópico 4", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_log_05",
                    moduleNumber = 5,
                    title = "5. Logística e Transportes",
                    summary = "Cadeia de suprimentos, efeito chicote, logística reversa e modais rodoviário, ferroviário, aquaviário, dutoviário e aéreo.",
                    lessons = listOf(
                        EnemTrailLesson("tp_log_m5_a1", "mod_tp_log_05", 1, "5.1. Cadeia de Suprimentos (Supply Chain), Efeito Chicote e Logística Reversa", "Integração de fluxos físicos/informacionais, distorção de demanda (bullwhip effect) e retorno pós-venda/pós-consumo", 35),
                        EnemTrailLesson("tp_log_m5_a2", "mod_tp_log_05", 2, "5.2. Modalidades de Transporte: Rodoviário, Ferroviário, Aquaviário, Dutoviário e Aéreo", "Características operacionais, capacidade, velocidade e papel estratégico de dutos e navios na Transpetro", 40),
                        EnemTrailLesson("tp_log_m5_a3", "mod_tp_log_05", 3, "5.3. Escolha de Modais, Intermodalidade, Multimodalidade e Custos Logísticos", "Critérios de seleção modal (custo fixo vs. variável), OTM (documento único) e eficiência energética", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_log_05", "mod_tp_log_05", "Simulado: Logística e Transportes", "Conclua as 3 aulas do Tópico 5", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_log_06",
                    moduleNumber = 6,
                    title = "6. Suprimento e Contratações na Cadeia Logística",
                    summary = "Instrumentos auxiliares de suprimentos na Lei nº 13.303/2016: pré-qualificação, registro de preços e catálogo eletrônico.",
                    lessons = listOf(
                        EnemTrailLesson("tp_log_m6_a1", "mod_tp_log_06", 1, "6.1. Procedimentos Auxiliares de Suprimento nas Estatais (Lei nº 13.303/2016)", "Sistema de Registro de Preços (SRP), catálogo eletrônico de padronização, pré-qualificação e cadastramento", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_log_06", "mod_tp_log_06", "Simulado: Suprimento em Estatais", "Conclua a aula do Tópico 6", true, 100, 10)
                )
            )
        )
    }

    // =========================================================================
    // MATÉRIA 4: NOÇÕES DE INFORMÁTICA (5 TÓPICOS • 13 AULAS)
    // =========================================================================
    val infDiscipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "transpetro_inf",
            name = "Noções de Informática",
            description = "Conhecimentos Específicos (Grupo A) — Hardware e software, Windows, Microsoft Office (Word, Excel e PowerPoint), redes, internet, correio eletrônico, segurança da informação e LGPD.",
            modules = listOf(
                EnemTrailModule(
                    id = "mod_tp_inf_01",
                    moduleNumber = 1,
                    title = "1. Fundamentos de Informática",
                    summary = "Hardware, software, CPU, memórias, periféricos de entrada e saída e dispositivos de armazenamento.",
                    lessons = listOf(
                        EnemTrailLesson("tp_inf_m1_a1", "mod_tp_inf_01", 1, "1.1. Hardware: Componentes, Memórias e Dispositivos de Entrada/Saída", "CPU, RAM (volátil), ROM, Cache, periféricos de entrada, saída e híbridos", 35),
                        EnemTrailLesson("tp_inf_m1_a2", "mod_tp_inf_01", 2, "1.2. Software e Dispositivos de Armazenamento", "Software básico/sistema vs. aplicativo; SSD, HD, unidades de medida (Byte, KB, MB, GB, TB) e nuvem", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_inf_01", "mod_tp_inf_01", "Simulado: Fundamentos de Informática", "Conclua as 2 aulas do Tópico 1", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_inf_02",
                    moduleNumber = 2,
                    title = "2. Sistemas Operacionais (Windows)",
                    summary = "Funções do sistema operacional, área de trabalho, gerenciamento de arquivos e pastas, extensões e atalhos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_inf_m2_a1", "mod_tp_inf_02", 1, "2.1. Sistemas Operacionais e Ambiente Windows", "Papel do SO, área de trabalho, barra de tarefas, gerenciador de tarefas e configurações", 35),
                        EnemTrailLesson("tp_inf_m2_a2", "mod_tp_inf_02", 2, "2.2. Arquivos, Pastas, Extensões e Atalhos no Windows", "Explorador de Arquivos, organização hierárquica, extensões (.xlsx, .docx, .pdf, .exe) e teclas de atalho", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_inf_02", "mod_tp_inf_02", "Simulado: Sistemas Operacionais", "Conclua as 2 aulas do Tópico 2", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_inf_03",
                    moduleNumber = 3,
                    title = "3. Pacote Microsoft Office",
                    summary = "Microsoft Word, Microsoft Excel (referências, fórmulas, funções SOMA, MÉDIA, SE, SOMASE, CONT.SE, PROCV e gráficos) e PowerPoint.",
                    lessons = listOf(
                        EnemTrailLesson("tp_inf_m3_a1", "mod_tp_inf_03", 1, "3.1. Microsoft Word: Formatação e Estruturação de Documentos", "Fonte, parágrafo, estilos, sumário automático, tabelas, quebras de seção e mala direta", 35),
                        EnemTrailLesson("tp_inf_m3_a2", "mod_tp_inf_03", 2, "3.2. Microsoft Excel: Planilhas, Intervalos e Referências ($)", "Operadores (: e ;), referência relativa (A1), absoluta (\$A\$1) e mista (\$A1 / A\$1) ao copiar fórmulas", 40),
                        EnemTrailLesson("tp_inf_m3_a3", "mod_tp_inf_03", 3, "3.3. Microsoft Excel: Funções Essenciais, Ordenação e Gráficos", "Funções SOMA, MÉDIA, MÍNIMO, MÁXIMO, SE, CONT.SE, SOMASE, PROCV/PROCX, filtros e escolha de gráficos", 40),
                        EnemTrailLesson("tp_inf_m3_a4", "mod_tp_inf_03", 4, "3.4. Microsoft PowerPoint: Apresentações e Slide Mestre", "Modos de exibição, Slide Mestre, layouts padronizados, animações vs. transições e exportação", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_inf_03", "mod_tp_inf_03", "Simulado: Pacote Microsoft Office", "Conclua as 4 aulas do Tópico 3", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_inf_04",
                    moduleNumber = 4,
                    title = "4. Internet, Redes e Correio Eletrônico",
                    summary = "Redes LAN/MAN/WAN, Internet, Intranet, Extranet, protocolos (HTTP/HTTPS, DNS), navegadores e e-mail.",
                    lessons = listOf(
                        EnemTrailLesson("tp_inf_m4_a1", "mod_tp_inf_04", 1, "4.1. Conceitos de Redes, Internet, Intranet e Protocolos", "Classificação geográfica (LAN, MAN, WAN), Intranet vs. Extranet, DNS, HTTP e HTTPS", 35),
                        EnemTrailLesson("tp_inf_m4_a2", "mod_tp_inf_04", 2, "4.2. Navegadores Web e Correio Eletrônico (Webmail)", "Cache, cookies, janela anônima, operadores de busca e campos de e-mail (Para, Cc, Cco, anexos seguros)", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_inf_04", "mod_tp_inf_04", "Simulado: Internet e Redes", "Conclua as 2 aulas do Tópico 4", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_inf_05",
                    moduleNumber = 5,
                    title = "5. Segurança da Informação e LGPD",
                    summary = "Princípios CID, autenticação multifator, menor privilégio, backup, malware (ransomware, phishing) e Lei nº 13.709/2018 (LGPD).",
                    lessons = listOf(
                        EnemTrailLesson("tp_inf_m5_a1", "mod_tp_inf_05", 1, "5.1. Princípios da Segurança, Autenticação (MFA), Menor Privilégio e Backup", "Confidencialidade, Integridade, Disponibilidade, Autenticidade, MFA e estratégias de cópia de segurança", 35),
                        EnemTrailLesson("tp_inf_m5_a2", "mod_tp_inf_05", 2, "5.2. Ameaças Digitais: Malware, Ransomware, Phishing e Engenharia Social", "Diferenças entre vírus, worm, trojan, spyware, ransomware (sequestro com criptografia) e phishing", 35),
                        EnemTrailLesson("tp_inf_m5_a3", "mod_tp_inf_05", 3, "5.3. Proteção de Dados e LGPD (Lei nº 13.709/2018)", "Dado pessoal, dado pessoal sensível, dado anonimizado, titular, controlador, operador, encarregado e ANPD", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_inf_05", "mod_tp_inf_05", "Simulado: Segurança e LGPD", "Conclua as 3 aulas do Tópico 5", true, 100, 10)
                )
            )
        )
    }

    // =========================================================================
    // MATÉRIA 5: LÍNGUA PORTUGUESA (11 TÓPICOS • 22 AULAS)
    // =========================================================================
    val porDiscipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "transpetro_por",
            name = "Língua Portuguesa",
            description = "Conhecimentos Básicos (Grupo B — 10 questões) — Interpretação textual, coesão, reescrita, ortografia, acentuação, morfologia, verbos, sintaxe, concordância, regência, crase e pontuação no estilo Cesgranrio.",
            modules = listOf(
                EnemTrailModule(
                    id = "mod_tp_por_01",
                    moduleNumber = 1,
                    title = "1. Interpretação e Compreensão de Textos",
                    summary = "Compreensão vs. interpretação, ideias principais e secundárias, inferências, sentido contextual e tipologia textual.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m1_a1", "mod_tp_por_01", 1, "1.1. Compreensão, Interpretação, Ideia Central e Inferências", "Informação explícita vs. implícita, tese, argumentos e extrapolação em provas da Cesgranrio", 35),
                        EnemTrailLesson("tp_por_m1_a2", "mod_tp_por_01", 2, "1.2. Sentido Contextual de Palavras e Tipologia Textual", "Sinonímia contextual, polissemia, denotação/conotação e textos dissertativos, expositivos e injuntivos", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_01", "mod_tp_por_01", "Simulado: Interpretação de Textos", "Conclua as 2 aulas do Tópico 1", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_02",
                    moduleNumber = 2,
                    title = "2. Coesão e Coerência",
                    summary = "Referenciação (anáfora e catáfora), progressão textual, conectivos e relações lógico-semânticas.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m2_a1", "mod_tp_por_02", 1, "2.1. Coesão Referencial e Progressão Textual", "Retomada anafórica, antecipação catafórica, hiperônimos e prevenção de ambiguidades", 35),
                        EnemTrailLesson("tp_por_m2_a2", "mod_tp_por_02", 2, "2.2. Conectivos e Relações de Sentido na Cesgranrio", "Oposição, concessão, causa, consequência, condição, finalidade, proporção e conclusão", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_02", "mod_tp_por_02", "Simulado: Coesão e Coerência", "Conclua as 2 aulas do Tópico 2", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_03",
                    moduleNumber = 3,
                    title = "3. Reescrita de Frases e Parágrafos",
                    summary = "Equivalência de sentido, substituição de palavras e conectores, alteração sintática e preservação da correção.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m3_a1", "mod_tp_por_03", 1, "3.1. Equivalência de Sentido e Substituição de Conectivos", "Troca de conjunções (ex.: embora / conquanto / apesar de) mantendo sentido e ajustes verbais", 35),
                        EnemTrailLesson("tp_por_m3_a2", "mod_tp_por_03", 2, "3.2. Alteração da Estrutura Sintática com Coerência e Correção", "Transposição de períodos, nominalização e manutenção da norma-padrão", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_03", "mod_tp_por_03", "Simulado: Reescrita", "Conclua as 2 aulas do Tópico 3", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_04",
                    moduleNumber = 4,
                    title = "4. Ortografia e Acentuação",
                    summary = "Ortografia oficial, emprego do hífen, parônimos/homônimos e regras de acentuação gráfica.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m4_a1", "mod_tp_por_04", 1, "4.1. Ortografia Oficial, Hífen e Vocábulos Frequentemente Confundidos", "Grafia com S/Z, X/CH, G/J, Ç/SS, uso dos porquês, onde/aonde, mal/mau e parônimos", 35),
                        EnemTrailLesson("tp_por_m4_a2", "mod_tp_por_04", 2, "4.2. Regras de Acentuação Gráfica e Novo Acordo Ortográfico", "Monossílabos, oxítonas, paroxítonas, proparoxítonas, hiatos (i/u) e acentos diferenciais (tem/têm, pode/pôde)", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_04", "mod_tp_por_04", "Simulado: Ortografia e Acentuação", "Conclua as 2 aulas do Tópico 4", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_05",
                    moduleNumber = 5,
                    title = "5. Morfologia",
                    summary = "Classes de palavras (substantivos, adjetivos, artigos, advérbios, preposições, conjunções) e emprego/colocação de pronomes.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m5_a1", "mod_tp_por_05", 1, "5.1. Classes de Palavras: Substantivos, Adjetivos, Artigos e Advérbios", "Papel morfossintático, plural dos compostos e locuções adjetivas/adverbiais", 35),
                        EnemTrailLesson("tp_por_m5_a2", "mod_tp_por_05", 2, "5.2. Pronomes e Colocação Pronominal (Próclise, Mesóclise e Ênclise)", "Pronomes oblíquos átonos (o/a/lhe), relativos e fatores atrativos de próclise", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_05", "mod_tp_por_05", "Simulado: Morfologia", "Conclua as 2 aulas do Tópico 5", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_06",
                    moduleNumber = 6,
                    title = "6. Verbos",
                    summary = "Tempos e modos verbais, conjugação, correlação verbal e vozes verbais (ativa, passiva analítica/sintética e reflexiva).",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m6_a1", "mod_tp_por_06", 1, "6.1. Tempos, Modos, Conjugação e Correlação Verbal", "Indicativo, subjuntivo e imperativo; verbos irregulares e harmonia temporal entre orações", 35),
                        EnemTrailLesson("tp_por_m6_a2", "mod_tp_por_06", 2, "6.2. Vozes Verbais e Conversão de Voz Ativa para Passiva", "Voz ativa, passiva analítica, passiva sintética (pronome apassivador) e manutenção do tempo verbal", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_06", "mod_tp_por_06", "Simulado: Verbos", "Conclua as 2 aulas do Tópico 6", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_07",
                    moduleNumber = 7,
                    title = "7. Sintaxe",
                    summary = "Termos essenciais, integrantes e acessórios da oração; período simples e período composto (coordenação e subordinação).",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m7_a1", "mod_tp_por_07", 1, "7.1. Sintaxe do Período Simples: Termos Essenciais, Integrantes e Acessórios", "Tipos de sujeito, transitividade verbal, objetos direto/indireto, complemento nominal vs. adjunto adnominal", 35),
                        EnemTrailLesson("tp_por_m7_a2", "mod_tp_por_07", 2, "7.2. Sintaxe do Período Composto: Coordenação e Subordinação", "Orações coordenadas sindéticas e subordinadas substantivas, adjetivas (restritivas/explicativas) e adverbiais", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_07", "mod_tp_por_07", "Simulado: Sintaxe", "Conclua as 2 aulas do Tópico 7", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_08",
                    moduleNumber = 8,
                    title = "8. Concordância",
                    summary = "Regras gerais e casos especiais de concordância nominal e concordância verbal no padrão Cesgranrio.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m8_a1", "mod_tp_por_08", 1, "8.1. Concordância Nominal", "Acordo com um ou mais substantivos e casos clássicos: anexo, incluso, mesmo, próprio, bastante, meio e é proibido", 35),
                        EnemTrailLesson("tp_por_m8_a2", "mod_tp_por_08", 2, "8.2. Concordância Verbal", "Sujeito simples e composto, verbos impessoais (haver no sentido de existir e fazer indicando tempo), partícula 'se' e porcentagens", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_08", "mod_tp_por_08", "Simulado: Concordância", "Conclua as 2 aulas do Tópico 8", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_09",
                    moduleNumber = 9,
                    title = "9. Regência",
                    summary = "Regência nominal, regência dos verbos mais cobrados em provas e regência com pronomes relativos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m9_a1", "mod_tp_por_09", 1, "9.1. Regência Nominal e Verbal", "Verbos assistir, aspirar, visar, informar, preferir, obedecer, implicar, esquecer/lembrar e pagar/perdoar", 40),
                        EnemTrailLesson("tp_por_m9_a2", "mod_tp_por_09", 2, "9.2. Regência com Pronomes Relativos", "Emprego de preposição antes de que, quem, o qual e cujo", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_09", "mod_tp_por_09", "Simulado: Regência", "Conclua as 2 aulas do Tópico 9", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_10",
                    moduleNumber = 10,
                    title = "10. Crase",
                    summary = "Fusão da preposição 'a' com o artigo/pronome 'a', casos obrigatórios, proibidos e facultativos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m10_a1", "mod_tp_por_10", 1, "10.1. Regra Geral da Crase e Casos Obrigatórios", "Teste da substituição por masculino, locuções adverbiais/prepositivas/conjuntivas femininas e indicação de horas", 40),
                        EnemTrailLesson("tp_por_m10_a2", "mod_tp_por_10", 2, "10.2. Casos Proibidos e Casos Facultativos de Crase", "Proibição antes de masculino, verbo, pronomes e palavras repetidas; casos facultativos e crase com nomes geográficos/casa/terra", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_10", "mod_tp_por_10", "Simulado: Crase", "Conclua as 2 aulas do Tópico 10", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_por_11",
                    moduleNumber = 11,
                    title = "11. Pontuação",
                    summary = "Emprego da vírgula no período simples e composto, ponto e vírgula, dois-pontos, travessão e parênteses.",
                    lessons = listOf(
                        EnemTrailLesson("tp_por_m11_a1", "mod_tp_por_11", 1, "11.1. Emprego da Vírgula no Período Simples", "Proibição entre sujeito-verbo-complemento; vírgula em adjunto adverbial deslocado, aposto, vocativo e enumerações", 40),
                        EnemTrailLesson("tp_por_m11_a2", "mod_tp_por_11", 2, "11.2. Vírgula no Período Composto e Outros Sinais de Pontuação", "Orações subordinadas adverbiais/adjetivas, ponto e vírgula, dois-pontos, travessão e aspas", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_por_11", "mod_tp_por_11", "Simulado: Pontuação", "Conclua as 2 aulas do Tópico 11", true, 100, 10)
                )
            )
        )
    }

    // =========================================================================
    // MATÉRIA 6: MATEMÁTICA (12 TÓPICOS • 25 AULAS)
    // =========================================================================
    val matDiscipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "transpetro_mat",
            name = "Matemática",
            description = "Conhecimentos Básicos (Grupo C — 10 questões) — Conjuntos, sistema legal de medidas, aritmética, proporções, porcentagem, equações, sistemas, PA/PG, funções, geometria, combinatória, probabilidade e estatística.",
            modules = listOf(
                EnemTrailModule(
                    id = "mod_tp_mat_01",
                    moduleNumber = 1,
                    title = "1. Conjuntos e Conjuntos Numéricos",
                    summary = "Operações com conjuntos, diagrama de Venn com 2 e 3 conjuntos, conjuntos numéricos (N, Z, Q, I, R) e intervalos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m1_a1", "mod_tp_mat_01", 1, "1.1. Teoria dos Conjuntos e Problemas com Diagramas de Venn", "União, interseção, diferença, complementar e princípio da inclusão-exclusão (n(A∪B) = n(A)+n(B)-n(A∩B))", 35),
                        EnemTrailLesson("tp_mat_m1_a2", "mod_tp_mat_01", 2, "1.2. Conjuntos Numéricos (N, Z, Q, R) e Intervalos Reais", "Números naturais, inteiros, racionais (frações geratrizes), irracionais, reais e operações com intervalos", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_01", "mod_tp_mat_01", "Simulado: Conjuntos", "Conclua as 2 aulas do Tópico 1", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_02",
                    moduleNumber = 2,
                    title = "2. Sistema Legal de Medidas",
                    summary = "Comprimento, área, volume, capacidade (m³ e litros), massa, tempo e problemas de vazão.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m2_a1", "mod_tp_mat_02", 1, "2.1. Medidas de Comprimento, Área, Volume e Capacidade", "Escalas decimal, centesimal e milesimal; equivalências fundamentais (1 m³ = 1.000 L e 1 dm³ = 1 L)", 35),
                        EnemTrailLesson("tp_mat_m2_a2", "mod_tp_mat_02", 2, "2.2. Medidas de Massa, Tempo e Vazão Operacional", "Tonelada, quilograma, grama; sistema sexagesimal (h, min, s) e conversão de vazão (m³/h para L/min)", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_02", "mod_tp_mat_02", "Simulado: Sistema de Medidas", "Conclua as 2 aulas do Tópico 2", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_03",
                    moduleNumber = 3,
                    title = "3. Matemática Básica",
                    summary = "Operações fundamentais, múltiplos e divisores (MMC/MDC), frações, potenciação, radiciação e expressões numéricas.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m3_a1", "mod_tp_mat_03", 1, "3.1. Operações Fundamentais, Expressões Numéricas, MMC e MDC", "Hierarquia de operações, divisibilidade, problemas de ciclos periódicos (MMC) e divisão máxima em partes iguais (MDC)", 35),
                        EnemTrailLesson("tp_mat_m3_a2", "mod_tp_mat_03", 2, "3.2. Frações, Decimais, Potenciação e Radiciação", "Operações fracionárias, problemas de partes do todo, propriedades de potências e racionalização", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_03", "mod_tp_mat_03", "Simulado: Matemática Básica", "Conclua as 2 aulas do Tópico 3", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_04",
                    moduleNumber = 4,
                    title = "4. Razões e Proporções",
                    summary = "Razão, proporção, grandezas diretamente e inversamente proporcionais, regra de três simples e composta.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m4_a1", "mod_tp_mat_04", 1, "4.1. Razão, Proporção e Divisão Proporcional", "Propriedades das proporções, constante de proporcionalidade (k) e divisão direta e inversa", 35),
                        EnemTrailLesson("tp_mat_m4_a2", "mod_tp_mat_04", 2, "4.2. Regra de Três Simples e Composta", "Análise de grandezas diretas e inversas (produtividade, máquinas, horas e prazo) na Cesgranrio", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_04", "mod_tp_mat_04", "Simulado: Razões e Proporções", "Conclua as 2 aulas do Tópico 4", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_05",
                    moduleNumber = 5,
                    title = "5. Porcentagem",
                    summary = "Cálculo percentual, aumentos, descontos, variações percentuais sucessivas e problemas de recomposição de base.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m5_a1", "mod_tp_mat_05", 1, "5.1. Cálculo Percentual, Aumentos, Descontos e Recomposição do Valor Inicial", "Cálculo de taxa percentual, valor final após reajuste e recuperação do preço antes do aumento/desconto", 35),
                        EnemTrailLesson("tp_mat_m5_a2", "mod_tp_mat_05", 2, "5.2. Variações Percentuais Sucessivas e Problemas Contextualizados", "Incidência acumulada (1 + i₁)·(1 - i₂) e problemas de orçamento e estoque", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_05", "mod_tp_mat_05", "Simulado: Porcentagem", "Conclua as 2 aulas do Tópico 5", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_06",
                    moduleNumber = 6,
                    title = "6. Equações",
                    summary = "Equações do 1º grau, equações do 2º grau (Bhaskara, discriminante, soma e produto) e resolução de problemas.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m6_a1", "mod_tp_mat_06", 1, "6.1. Equações do 1º Grau e Modelagem Algébrica de Problemas", "Tradução de enunciados para linguagem algébrica, funções de custo linear e resolução", 35),
                        EnemTrailLesson("tp_mat_m6_a2", "mod_tp_mat_06", 2, "6.2. Equações do 2º Grau e Problemas Aplicados", "Fórmula de Bhaskara, estudo do discriminante (Δ), relações de Girard (soma e produto) e aplicações", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_06", "mod_tp_mat_06", "Simulado: Equações", "Conclua as 2 aulas do Tópico 6", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_07",
                    moduleNumber = 7,
                    title = "7. Sistemas Lineares e Progressões",
                    summary = "Sistemas lineares de duas incógnitas (substituição e adição) e sequências numéricas (PA e PG).",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m7_a1", "mod_tp_mat_07", 1, "7.1. Sistemas Lineares de Duas Incógnitas e Aplicações", "Métodos da substituição e da adição em problemas de compras, lotes e alocação de recursos", 35),
                        EnemTrailLesson("tp_mat_m7_a2", "mod_tp_mat_07", 2, "7.2. Progressão Aritmética (PA) e Progressão Geométrica (PG)", "Termo geral e soma dos n primeiros termos na PA (crescimento constante) e na PG (crescimento multiplicativo)", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_07", "mod_tp_mat_07", "Simulado: Sistemas e Progressões", "Conclua as 2 aulas do Tópico 7", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_08",
                    moduleNumber = 8,
                    title = "8. Funções",
                    summary = "Função do 1º grau (afim), função do 2º grau (quadrática), vértice da parábola (máximos/mínimos) e gráficos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m8_a1", "mod_tp_mat_08", 1, "8.1. Função do 1º Grau (Afim) e Interpretação Gráfica", "Lei f(x) = ax + b, coeficiente angular (taxa de variação), coeficiente linear, zero da função e ponto de equilíbrio", 35),
                        EnemTrailLesson("tp_mat_m8_a2", "mod_tp_mat_08", 2, "8.2. Função do 2º Grau (Quadrática), Vértice e Otimização", "Parábola f(x) = ax² + bx + c, concavidade, coordenadas do vértice (Xv = -b/2a, Yv = -Δ/4a) e máximos/mínimos", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_08", "mod_tp_mat_08", "Simulado: Funções", "Conclua as 2 aulas do Tópico 8", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_09",
                    moduleNumber = 9,
                    title = "9. Funções Exponenciais e Logarítmicas",
                    summary = "Função e equação exponencial, definição de logaritmo, propriedades operatórias e equações logarítmicas.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m9_a1", "mod_tp_mat_09", 1, "9.1. Função e Equação Exponencial", "Propriedades de potências, redução à mesma base e problemas de crescimento/decaimento exponencial", 35),
                        EnemTrailLesson("tp_mat_m9_a2", "mod_tp_mat_09", 2, "9.2. Logaritmos: Definição, Propriedades e Equações", "Condição de existência, logaritmo do produto, quociente, potência e mudança de base", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_09", "mod_tp_mat_09", "Simulado: Exponenciais e Logaritmos", "Conclua as 2 aulas do Tópico 9", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_10",
                    moduleNumber = 10,
                    title = "10. Geometria Plana e Espacial",
                    summary = "Perímetros e áreas de figuras planas, Teorema de Pitágoras, áreas e volumes de prismas, cilindros, cones e esferas.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m10_a1", "mod_tp_mat_10", 1, "10.1. Geometria Plana: Polígonos, Pitágoras, Perímetros e Áreas", "Retângulo, quadrado, triângulo, trapézio, losango, circunferência/círculo e Teorema de Pitágoras", 35),
                        EnemTrailLesson("tp_mat_m10_a2", "mod_tp_mat_10", 2, "10.2. Geometria Espacial: Volumes de Sólidos e Reservatórios", "Cubo, bloco retangular (paralelepípedo), prisma, cilindro (tanques de armazenamento), cone e esfera", 40)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_10", "mod_tp_mat_10", "Simulado: Geometria", "Conclua as 2 aulas do Tópico 10", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_11",
                    moduleNumber = 11,
                    title = "11. Análise Combinatória e Probabilidade",
                    summary = "Princípio fundamental da contagem, permutações, arranjos, combinações e probabilidade de eventos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m11_a1", "mod_tp_mat_11", 1, "11.1. Princípio Fundamental da Contagem, Permutação, Arranjo e Combinação", "Quando a ordem importa (Arranjo) vs. quando a ordem não importa (Combinação: formação de comissões e equipes)", 40),
                        EnemTrailLesson("tp_mat_m11_a2", "mod_tp_mat_11", 2, "11.2. Probabilidade: Definição, União de Eventos e Eventos Independentes", "Espaço amostral equiprovável, probabilidade complementar, extração sem reposição e eventos simultâneos", 40),
                        EnemTrailLesson("tp_mat_m11_a3", "mod_tp_mat_11", 3, "11.3. Probabilidade Condicional e Tabelas de Contingência", "Cálculo de probabilidade com espaço amostral reduzido em tabelas operacionais", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_11", "mod_tp_mat_11", "Simulado: Combinatória e Probabilidade", "Conclua as 3 aulas do Tópico 11", true, 100, 10)
                ),
                EnemTrailModule(
                    id = "mod_tp_mat_12",
                    moduleNumber = 12,
                    title = "12. Estatística Básica",
                    summary = "Média aritmética simples e ponderada, mediana, moda e interpretação de tabelas e gráficos.",
                    lessons = listOf(
                        EnemTrailLesson("tp_mat_m12_a1", "mod_tp_mat_12", 1, "12.1. Média Aritmética (Simples e Ponderada), Mediana e Moda", "Cálculo das medidas de tendência central e robustez da mediana frente a valores discrepantes (outliers)", 40),
                        EnemTrailLesson("tp_mat_m12_a2", "mod_tp_mat_12", 2, "12.2. Interpretação de Tabelas, Frequências e Gráficos Estatísticos", "Análise de séries temporais, gráficos de barras, linhas e setores em questões da Cesgranrio", 35)
                    ),
                    finalSimulado = EnemTrailSimulado("sim_tp_mat_12", "mod_tp_mat_12", "Simulado: Estatística Básica", "Conclua as 2 aulas do Tópico 12", true, 100, 10)
                )
            )
        )
    }

    val allDisciplines: List<EnemTrailDiscipline> by lazy {
        listOf(
            admDiscipline,
            finDiscipline,
            logDiscipline,
            infDiscipline,
            porDiscipline,
            matDiscipline
        )
    }

    fun getAllModules(): List<EnemTrailModule> = allDisciplines.flatMap { it.modules }

    fun getAllLessons(): List<EnemTrailLesson> = allDisciplines.flatMap { d -> d.modules.flatMap { it.lessons } }

    fun findLesson(lessonId: String): EnemTrailLesson? {
        for (discipline in allDisciplines) {
            for (module in discipline.modules) {
                for (lesson in module.lessons) {
                    if (lesson.id == lessonId) return lesson
                }
            }
        }
        return null
    }

    fun findDisciplineByLessonId(lessonId: String): EnemTrailDiscipline? {
        return allDisciplines.firstOrNull { d ->
            d.modules.any { m -> m.lessons.any { it.id == lessonId } }
        }
    }

    fun findModuleByLessonId(lessonId: String): EnemTrailModule? {
        for (discipline in allDisciplines) {
            for (module in discipline.modules) {
                if (module.lessons.any { it.id == lessonId }) return module
            }
        }
        return null
    }

    fun getUnitsForCurriculumLoader(): List<LearningUnit> {
        var unitCounter = 1
        return allDisciplines.flatMap { discipline ->
            discipline.modules.map { module ->
                LearningUnit(
                    id = module.id,
                    unitNumber = unitCounter++,
                    tier = DifficultyTier.INTERMEDIATE,
                    title = "${discipline.name} — ${module.title}",
                    description = module.summary,
                    category = TOPIC_TITLE,
                    subject = discipline.name,
                    lessons = module.lessons.map { it.toAppLesson() }
                )
            }
        }
    }
}

