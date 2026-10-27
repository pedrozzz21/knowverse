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
                ),
                // ========================================================
                // MÓDULO 2: Fisiologia e Anatomia Humana
                // ========================================================
                EnemTrailModule(
                    id = "mod_bio_02_fisiologia",
                    moduleNumber = 2,
                    title = "Fisiologia e Anatomia Humana",
                    summary = "Sistemas Integrados, Imunologia, Ciclo Menstrual, Gravidez, Métodos Contraceptivos e ISTs.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "bio_fis_01",
                            moduleId = "mod_bio_02_fisiologia",
                            lessonIndex = 1,
                            title = "1. Sistemas do Corpo Humano e Anatomia Integrada",
                            subtopic = "Digestão, Respiração, Circulação e Excreção",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_fis_02",
                            moduleId = "mod_bio_02_fisiologia",
                            lessonIndex = 2,
                            title = "2. Saúde e Doenças (Imunologia)",
                            subtopic = "Vírus, Bactérias, Parasitas, Vacinas e Soros",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_fis_03",
                            moduleId = "mod_bio_02_fisiologia",
                            lessonIndex = 3,
                            title = "3. Reprodução e Gravidez",
                            subtopic = "Ciclo Menstrual, Ovulação, Nidação e Placenta",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_fis_04",
                            moduleId = "mod_bio_02_fisiologia",
                            lessonIndex = 4,
                            title = "4. Métodos Contraceptivos e ISTs",
                            subtopic = "Barreiras, Hormonais, Vasectomia e Prevenção",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_bio_02",
                        moduleId = "mod_bio_02_fisiologia",
                        title = "Simulado Final: Fisiologia Humana ENEM",
                        lockNotice = "Conclua as 4 aulas de Fisiologia para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),
                // ========================================================
                // MÓDULO 3: Genética e Biotecnologia
                // ========================================================
                EnemTrailModule(
                    id = "mod_bio_03_genetica",
                    moduleNumber = 3,
                    title = "Genética e Biotecnologia",
                    summary = "Ácidos Nucleicos, Leis de Mendel, Linkage, Sistema ABO, Herança Sexual, Transgênicos e PCR.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "bio_gen_01",
                            moduleId = "mod_bio_03_genetica",
                            lessonIndex = 1,
                            title = "1. Conceitos Iniciais e Ácidos Nucleicos",
                            subtopic = "DNA, RNA, Genótipo e Fenótipo",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_gen_02",
                            moduleId = "mod_bio_03_genetica",
                            lessonIndex = 2,
                            title = "2. Primeira Lei de Mendel",
                            subtopic = "Segregação dos Fatores e Monoibridismo 3:1",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_gen_03",
                            moduleId = "mod_bio_03_genetica",
                            lessonIndex = 3,
                            title = "3. Segunda Lei de Mendel e Linkage",
                            subtopic = "Segregação Independente 9:3:3:1 e Crossing-over",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_gen_04",
                            moduleId = "mod_bio_03_genetica",
                            lessonIndex = 4,
                            title = "4. Doenças Genéticas e Sistema ABO",
                            subtopic = "Tipagem Sanguínea, Rh, Daltonismo e Down",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_gen_05",
                            moduleId = "mod_bio_03_genetica",
                            lessonIndex = 5,
                            title = "5. Biotecnologia e Transgênicos",
                            subtopic = "DNA Recombinante, Eletroforese, Dolly e Células-Tronco",
                            xpReward = 45
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_bio_03",
                        moduleId = "mod_bio_03_genetica",
                        title = "Simulado Final: Genética ENEM",
                        lockNotice = "Conclua as 5 aulas de Genética para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),
                // ========================================================
                // MÓDULO 4: Citologia e Bioquímica Celular
                // ========================================================
                EnemTrailModule(
                    id = "mod_bio_04_citologia",
                    moduleNumber = 4,
                    title = "Citologia e Bioquímica Celular",
                    summary = "Teoria Celular, Envoltórios, Citoesqueleto, Organelas, Teoria Endossimbiótica, Metabolismo e Bioquímica.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "bio_cit_01",
                            moduleId = "mod_bio_04_citologia",
                            lessonIndex = 1,
                            title = "1. Teoria Celular, Envoltórios e Citoesqueleto",
                            subtopic = "Procariontes, Eucariontes, Membrana, Osmose e Glicocálix",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_cit_02",
                            moduleId = "mod_bio_04_citologia",
                            lessonIndex = 2,
                            title = "2. Organelas Citoplasmáticas e Célula Animal vs. Vegetal",
                            subtopic = "Ribossomos, RER, REL, Golgi, Lisossomos e Peroxissomos",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_cit_03",
                            moduleId = "mod_bio_04_citologia",
                            lessonIndex = 3,
                            title = "3. Teoria Endossimbiótica e Mitocôndria",
                            subtopic = "Origem Bacteriana, Evidências e Herança Mitocondrial Materna",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_cit_04",
                            moduleId = "mod_bio_04_citologia",
                            lessonIndex = 4,
                            title = "4. Metabolismo Energético",
                            subtopic = "Respiração Celular, Fermentação Láctica/Alcoólica e Fotossíntese",
                            xpReward = 45
                        ),
                        EnemTrailLesson(
                            id = "bio_cit_05",
                            moduleId = "mod_bio_04_citologia",
                            lessonIndex = 5,
                            title = "5. Bioquímica Celular",
                            subtopic = "Água, Sais, Carboidratos, Lipídios, Enzimas e Vitaminas",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_bio_04",
                        moduleId = "mod_bio_04_citologia",
                        title = "Simulado Final: Citologia e Bioquímica ENEM",
                        lockNotice = "Conclua as 5 aulas de Citologia para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),
                // ========================================================
                // MÓDULO 5: Evolução Biológica e Hominização
                // ========================================================
                EnemTrailModule(
                    id = "mod_bio_05_evolucao",
                    moduleNumber = 5,
                    title = "Evolução Biológica e Hominização",
                    summary = "Evidências da Evolução, Homologia vs. Analogia, Lamarckismo, Darwinismo, Neodarwinismo e Evolução Humana.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "bio_evo_01",
                            moduleId = "mod_bio_05_evolucao",
                            lessonIndex = 1,
                            title = "1. Evidências da Evolução e Homologia vs. Analogia",
                            subtopic = "Fósseis, Órgãos Vestigiais e Irradiação vs. Convergência",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_evo_02",
                            moduleId = "mod_bio_05_evolucao",
                            lessonIndex = 2,
                            title = "2. Lamarckismo",
                            subtopic = "Uso e Desuso, Caracteres Adquiridos e Barreira Somática",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "bio_evo_03",
                            moduleId = "mod_bio_05_evolucao",
                            lessonIndex = 3,
                            title = "3. Darwinismo e Neodarwinismo",
                            subtopic = "Seleção Natural, Mutação, Recombinação e Superbactérias",
                            xpReward = 45
                        ),
                        EnemTrailLesson(
                            id = "bio_evo_04",
                            moduleId = "mod_bio_05_evolucao",
                            lessonIndex = 4,
                            title = "4. Evolução Humana e Hominização",
                            subtopic = "Ancestral Comum, Bipedalismo, Homo habilis, erectus e sapiens",
                            xpReward = 45
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_bio_05",
                        moduleId = "mod_bio_05_evolucao",
                        title = "Simulado Final: Evolução ENEM",
                        lockNotice = "Conclua as 4 aulas de Evolução para liberar o simulado",
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
