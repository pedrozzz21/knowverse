package com.example.data.physics

import com.example.data.model.EnemTrailDiscipline
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado

/**
 * Repositório e Taxonomia Oficial da Trilha de Física ENEM para o Knowverse.
 * Totalmente compatível com a arquitetura de trilhas do Android e da versão Web.
 */
object EnemPhysicsCurriculum {

    val discipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "fisica_enem",
            name = "Física ENEM",
            description = "Trilha completa e sequencial de Física para o ENEM contendo 6 módulos essenciais: Mecânica, Eletricidade e Magnetismo, Termologia, Ondulatória, Óptica e Física Moderna.",
            modules = listOf(
                // ========================================================
                // MÓDULO 1: Mecânica
                // ========================================================
                EnemTrailModule(
                    id = "mod_fis_01_mecanica",
                    moduleNumber = 1,
                    title = "Mecânica",
                    summary = "Cinemática, Dinâmica, Trabalho, Energia, Impulso, Estática, Hidrostática e Gravitação Universal.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "fis_m1_a1",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 1,
                            title = "Aula 1: Cinemática Escalar (Ordem de Grandeza, MRU e MRUV)",
                            subtopic = "MRU, MRUV e Funções Horárias",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a2",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 2,
                            title = "Aula 2: Análise Gráfica, Vetores e Lançamentos",
                            subtopic = "Gráficos do Movimento e Lançamentos (Vertical, Horizontal, Oblíquo e Queda Livre)",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a3",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 3,
                            title = "Aula 3: Cinemática Angular e Movimento Circular Uniforme (MCU)",
                            subtopic = "MCU, Período, Frequência e Aceleração Centrípeta",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a4",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 4,
                            title = "Aula 4: Dinâmica e Leis de Newton",
                            subtopic = "1ª, 2ª e 3ª Leis de Newton, Atrito, Força Elástica e Centrípeta",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a5",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 5,
                            title = "Aula 5: Máquinas Simples e Aplicações",
                            subtopic = "Roldanas, Elevadores, Plano Inclinado e Alavancas",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a6",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 6,
                            title = "Aula 6: Trabalho, Potência e Energia Mecânica",
                            subtopic = "Trabalho, Potência, Energia Cinética, Potencial e Conservação da Energia",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a7",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 7,
                            title = "Aula 7: Impulso, Quantidade de Movimento e Colisões",
                            subtopic = "Teorema do Impulso, Choques Mecânicos e Conservação do Momento",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a8",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 8,
                            title = "Aula 8: Estática do Ponto Material e Corpos Extensos",
                            subtopic = "Condições de Equilíbrio e Momento de uma Força (Torque)",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a9",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 9,
                            title = "Aula 9: Hidrostática (Pressão, Stevin, Pascal e Arquimedes)",
                            subtopic = "Massa Específica, Pressão Hidrostática, Vasos Comunicantes e Empuxo",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m1_a10",
                            moduleId = "mod_fis_01_mecanica",
                            lessonIndex = 10,
                            title = "Aula 10: Gravitação Universal e Leis de Kepler",
                            subtopic = "Órbitas Planetárias e Lei da Gravitação de Newton",
                            xpReward = 30
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_fis_01",
                        moduleId = "mod_fis_01_mecanica",
                        title = "Simulado Final: Mecânica",
                        xpReward = 100,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 2: Eletricidade e Magnetismo
                // ========================================================
                EnemTrailModule(
                    id = "mod_fis_02_eletricidade",
                    moduleNumber = 2,
                    title = "Eletricidade e Magnetismo",
                    summary = "Eletrodinâmica (maior incidência), Eletrostática e Eletromagnetismo.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "fis_m2_a1",
                            moduleId = "mod_fis_02_eletricidade",
                            lessonIndex = 1,
                            title = "Aula 1: Eletrodinâmica - Corrente, Tensão e Potência Elétrica",
                            subtopic = "Conceito de Carga, Corrente Elétrica, ddp e Cálculo de Consumo de Energia (kWh)",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m2_a2",
                            moduleId = "mod_fis_02_eletricidade",
                            lessonIndex = 2,
                            title = "Aula 2: 1ª e 2ª Leis de Ohm e Resistores",
                            subtopic = "Resistência, Resistividade, Efeito Joule e Associação de Resistores (Série/Paralelo)",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m2_a3",
                            moduleId = "mod_fis_02_eletricidade",
                            lessonIndex = 3,
                            title = "Aula 3: Circuitos Elétricos, Curtos-Circuitos e Dimensionamento",
                            subtopic = "Fusíveis, Disjuntores, Geradores, Receptores e Medidores (Amperímetro e Voltímetro)",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m2_a4",
                            moduleId = "mod_fis_02_eletricidade",
                            lessonIndex = 4,
                            title = "Aula 4: Eletrostática - Cargas, Processos de Eletrização e Lei de Coulomb",
                            subtopic = "Atrito, Contato, Indução e Força Elétrica",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m2_a5",
                            moduleId = "mod_fis_02_eletricidade",
                            lessonIndex = 5,
                            title = "Aula 5: Campo Elétrico, Potencial e Blindagem Eletrostática",
                            subtopic = "Linhas de Força, Gaiola de Faraday e Energia Potencial Elétrica",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m2_a6",
                            moduleId = "mod_fis_02_eletricidade",
                            lessonIndex = 6,
                            title = "Aula 6: Eletromagnetismo, Campo Magnético e Indução de Faraday",
                            subtopic = "Ímãs, Campo da Terra, Experimento de Oersted, Força Magnética e Lei de Lenz",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_fis_02",
                        moduleId = "mod_fis_02_eletricidade",
                        title = "Simulado Final: Eletricidade e Magnetismo",
                        xpReward = 100,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 3: Termologia (Termodinâmica)
                // ========================================================
                EnemTrailModule(
                    id = "mod_fis_03_termologia",
                    moduleNumber = 3,
                    title = "Termologia (Termodinâmica)",
                    summary = "Termometria, Dilatação Térmica, Calorimetria e Leis da Termodinâmica.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "fis_m3_a1",
                            moduleId = "mod_fis_03_termologia",
                            lessonIndex = 1,
                            title = "Aula 1: Termometria e Escalas Termométricas",
                            subtopic = "Conceito de Temperatura, Escalas Celsius, Fahrenheit e Kelvin",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m3_a2",
                            moduleId = "mod_fis_03_termologia",
                            lessonIndex = 2,
                            title = "Aula 2: Dilatação Térmica dos Sólidos e Líquidos",
                            subtopic = "Dilatação Linear, Superficial, Volumétrica e Dilatação Anômala da Água",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m3_a3",
                            moduleId = "mod_fis_03_termologia",
                            lessonIndex = 3,
                            title = "Aula 3: Calorimetria - Calor Sensível e Calor Latente",
                            subtopic = "Capacidade Térmica, Equação Fundamental da Calorimetria e Mudanças de Fase",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m3_a4",
                            moduleId = "mod_fis_03_termologia",
                            lessonIndex = 4,
                            title = "Aula 4: Processos de Transmissão de Calor e Equilíbrio Térmico",
                            subtopic = "Condução, Convecção, Irradiação Térmica e Efeito Estufa",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m3_a5",
                            moduleId = "mod_fis_03_termologia",
                            lessonIndex = 5,
                            title = "Aula 5: Termodinâmica, Gases Ideais e Máquinas Térmicas",
                            subtopic = "Equação de Clapeyron, 1ª Lei da Termodinâmica, Ciclo de Carnot e 2ª Lei",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_fis_03",
                        moduleId = "mod_fis_03_termologia",
                        title = "Simulado Final: Termologia",
                        xpReward = 100,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 4: Ondulatória
                // ========================================================
                EnemTrailModule(
                    id = "mod_fis_04_ondulatoria",
                    moduleNumber = 4,
                    title = "Ondulatória",
                    summary = "Fundamentos da Ondulatória, Equação Fundamental, Fenômenos Ondulatórios e Acústica.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "fis_m4_a1",
                            moduleId = "mod_fis_04_ondulatoria",
                            lessonIndex = 1,
                            title = "Aula 1: Fundamentos da Ondulatória e Equação Fundamental (v = λ · f)",
                            subtopic = "Classificação das Ondas (Mecânicas vs Eletromagnéticas, Transversais e Longitudinais)",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m4_a2",
                            moduleId = "mod_fis_04_ondulatoria",
                            lessonIndex = 2,
                            title = "Aula 2: Fenômenos Ondulatórios - Reflexão, Refração e Difração",
                            subtopic = "Comportamento das Ondas e Contorno de Obstáculos",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m4_a3",
                            moduleId = "mod_fis_04_ondulatoria",
                            lessonIndex = 3,
                            title = "Aula 3: Interferência, Polarização e Ressonância",
                            subtopic = "Interferência Construtiva/Destrutiva, Filtros de Polarização e Frequência Própria",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m4_a4",
                            moduleId = "mod_fis_04_ondulatoria",
                            lessonIndex = 4,
                            title = "Aula 4: Acústica e Qualidades Fisiológicas do Som",
                            subtopic = "Altura (Frequência), Intensidade (Volume), Timbre e Ondas Estacionárias em Cordas/Tubos",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m4_a5",
                            moduleId = "mod_fis_04_ondulatoria",
                            lessonIndex = 5,
                            title = "Aula 5: Efeito Doppler e Aplicações Tecnológicas",
                            subtopic = "Deslocamento Aparente de Frequência no ENEM (Radares, Som e Astronomia)",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_fis_04",
                        moduleId = "mod_fis_04_ondulatoria",
                        title = "Simulado Final: Ondulatória",
                        xpReward = 100,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 5: Óptica
                // ========================================================
                EnemTrailModule(
                    id = "mod_fis_05_optica",
                    moduleNumber = 5,
                    title = "Óptica",
                    summary = "Óptica Geométrica, Espelhos Planos e Esféricos, Refração, Lentes e Óptica da Visão.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "fis_m5_a1",
                            moduleId = "mod_fis_05_optica",
                            lessonIndex = 1,
                            title = "Aula 1: Fundamentos da Óptica Geométrica e Propagação da Luz",
                            subtopic = "Fontes de Luz, Princípio da Independência, Reversibilidade e Sombras",
                            xpReward = 30
                        ),
                        EnemTrailLesson(
                            id = "fis_m5_a2",
                            moduleId = "mod_fis_05_optica",
                            lessonIndex = 2,
                            title = "Aula 2: Espelhos Planos e Esféricos (Côncavos e Convexos)",
                            subtopic = "Formação de Imagens (Real, Virtual, Direita e Invertida) e Campo Visual",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m5_a3",
                            moduleId = "mod_fis_05_optica",
                            lessonIndex = 3,
                            title = "Aula 3: Refração Luminosa, Lei de Snell-Descartes e Ângulo Limite",
                            subtopic = "Índice de Refração, Dioptro Plano e Reflexão Total (Fibras Ópticas)",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m5_a4",
                            moduleId = "mod_fis_05_optica",
                            lessonIndex = 4,
                            title = "Aula 4: Lentes Esféricas Delgadas e Equação de Gauss",
                            subtopic = "Lentes Convergentes/Divergentes, Foco, Vergência e Construção Geométrica",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m5_a5",
                            moduleId = "mod_fis_05_optica",
                            lessonIndex = 5,
                            title = "Aula 5: Óptica da Visão e Instrumentos Ópticos",
                            subtopic = "Olho Humano, Miopia, Hipermetropia, Astigmatismo e Correção por Lentes",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_fis_05",
                        moduleId = "mod_fis_05_optica",
                        title = "Simulado Final: Óptica",
                        xpReward = 100,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 6: Física Moderna
                // ========================================================
                EnemTrailModule(
                    id = "mod_fis_06_moderna",
                    moduleNumber = 6,
                    title = "Física Moderna",
                    summary = "Mecânica Quântica, Efeito Fotoelétrico, Estrutura Atômica, Reações Nucleares e Relatividade.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "fis_m6_a1",
                            moduleId = "mod_fis_06_moderna",
                            lessonIndex = 1,
                            title = "Aula 1: Dualidade Onda-Partícula e Efeito Fotoelétrico",
                            subtopic = "Fótons, Energia de Planck (E = h · f) e Células Fotoelétricas no ENEM",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m6_a2",
                            moduleId = "mod_fis_06_moderna",
                            lessonIndex = 2,
                            title = "Aula 2: Estrutura Atômica, Radioatividade e Reações Nucleares",
                            subtopic = "Fissão Nuclear, Fusão Nuclear, Decaimento Alfa/Beta/Gama e Meia-Vida",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "fis_m6_a3",
                            moduleId = "mod_fis_06_moderna",
                            lessonIndex = 3,
                            title = "Aula 3: Teoria da Relatividade Restrita e Geral",
                            subtopic = "Postulados de Einstein, Dilatação Temporal, Contração Espacial e Equivalência E = mc²",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_fis_06",
                        moduleId = "mod_fis_06_moderna",
                        title = "Simulado Final: Física Moderna",
                        xpReward = 100,
                        questionCount = 10
                    )
                )
            )
        )
    }

    fun getAllModules(): List<EnemTrailModule> = discipline.modules

    fun findModule(moduleId: String): EnemTrailModule? = discipline.modules.find { it.id == moduleId }

    fun findLesson(lessonId: String): EnemTrailLesson? {
        for (module in discipline.modules) {
            val lesson = module.lessons.find { it.id == lessonId }
            if (lesson != null) return lesson
        }
        return null
    }
}
