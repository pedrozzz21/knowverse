package com.example.data.biology

import com.example.data.model.EnemTrailDiscipline
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado

/**
 * Repositório e Taxonomia Oficial da Trilha de Biologia ENEM para o Knowverse.
 * Focado no Tópico 1: Ecologia com os 7 subtópicos solicitados.
 */
object EnemBiologyCurriculum {

    val discipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "biologia_enem",
            name = "Biologia ENEM",
            description = "Trilha oficial e completa de Biologia para o ENEM focando em Ecologia, Fisiologia Humana, Genética, Citologia e Evolução.",
            modules = listOf(
                // ========================================================
                // MÓDULO 1: Ecologia
                // ========================================================
                EnemTrailModule(
                    id = "mod_bio_01_ecologia",
                    moduleNumber = 1,
                    title = "Ecologia",
                    summary = "Conceitos de Meio Ambiente, Cadeias e Teias, Relações Ecológicas, Ciclos Biogeoquímicos, Impactos Ambientais, Efeito Estufa e Sustentabilidade.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "bio_eco_01",
                            moduleId = "mod_bio_01_ecologia",
                            lessonIndex = 1,
                            title = "1. Conceitos de Meio Ambiente e Biodiversidade",
                            subtopic = "Habitat, Nicho Ecológico e Hotspots",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "bio_eco_02",
                            moduleId = "mod_bio_01_ecologia",
                            lessonIndex = 2,
                            title = "2. Cadeias e Teias Alimentares",
                            subtopic = "Níveis Tróficos, Fluxo de Energia e Bioacumulação",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "bio_eco_03",
                            moduleId = "mod_bio_01_ecologia",
                            lessonIndex = 3,
                            title = "3. Relações Ecológicas",
                            subtopic = "Relações Harmônicas e Desarmônicas",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "bio_eco_04",
                            moduleId = "mod_bio_01_ecologia",
                            lessonIndex = 4,
                            title = "4. Ciclos Biogeoquímicos",
                            subtopic = "Ciclo da Água, Carbono e Nitrogênio",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_eco_05",
                            moduleId = "mod_bio_01_ecologia",
                            lessonIndex = 5,
                            title = "5. Impactos Ambientais e Poluição",
                            subtopic = "Eutrofização, Chuva Ácida e Inversão Térmica",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_eco_06",
                            moduleId = "mod_bio_01_ecologia",
                            lessonIndex = 6,
                            title = "6. Efeito Estufa e Aquecimento Global",
                            subtopic = "Gases Estufa, Aquecimento dos Oceanos e Branqueamento de Corais",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "bio_eco_07",
                            moduleId = "mod_bio_01_ecologia",
                            lessonIndex = 7,
                            title = "7. Sustentabilidade",
                            subtopic = "Energias Renováveis, Biorremediação e Controle Biológico",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_bio_01",
                        moduleId = "mod_bio_01_ecologia",
                        title = "Simulado Final: Ecologia ENEM",
                        lockNotice = "Conclua as 7 aulas de Ecologia para liberar o simulado oficial",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                )
            )
        )
    }

    fun getAllModules(): List<EnemTrailModule> = discipline.modules
}
