package com.example.data.geography

import com.example.data.model.EnemTrailDiscipline
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado

/**
 * Trilha Estruturante Oficial de Geografia para o ENEM (Ciências Humanas).
 * Organizada em 5 grandes módulos temáticos e 21 aulas aprofundadas:
 * 1. Geografia Física e Meio Ambiente (5 Aulas)
 * 2. Geografia Agrária: O Espaço Rural (4 Aulas)
 * 3. Geografia Urbana e Demografia (4 Aulas)
 * 4. Geografia Econômica e Globalização (4 Aulas)
 * 5. Geopolítica e Ordem Mundial (4 Aulas)
 */
object EnemGeographyCurriculum {

    val discipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "geografia_enem",
            name = "Geografia ENEM • Humanas",
            description = "Trilha analítica de Geografia para o ENEM. Aborda a produção do espaço geográfico, dinâmicas naturais, espaço agrário e urbano, redes globais e geopolítica contemporânea.",
            modules = listOf(
                // ========================================================
                // MÓDULO 1: Geografia Física e Meio Ambiente
                // ========================================================
                EnemTrailModule(
                    id = "mod_geo_01_fisica",
                    moduleNumber = 1,
                    title = "Geografia Física e Meio Ambiente",
                    summary = "Cartografia, geomorfologia, dinâmica climática, bacias hidrográficas e os impactos socioambientais contemporâneos.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "geo_fis_01",
                            moduleId = "mod_geo_01_fisica",
                            lessonIndex = 1,
                            title = "1. Cartografia e Orientação",
                            subtopic = "Escalas, projeções cilíndricas, cônicas e azimutais, coordenadas geográficas e fusos horários",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_fis_02",
                            moduleId = "mod_geo_01_fisica",
                            lessonIndex = 2,
                            title = "2. Geomorfologia e Relevo",
                            subtopic = "Tectonismo, placas litosféricas, agentes endógenos/exógenos e classificação do relevo brasileiro de Jurandyr Ross",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_fis_03",
                            moduleId = "mod_geo_01_fisica",
                            lessonIndex = 3,
                            title = "3. Climatologia",
                            subtopic = "Fatores e elementos do clima, climas do Brasil, massas de ar e anomalias climáticas (El Niño e La Niña)",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_fis_04",
                            moduleId = "mod_geo_01_fisica",
                            lessonIndex = 4,
                            title = "4. Hidrografia",
                            subtopic = "Bacias hidrográficas brasileiras, ciclo hidrológico, aquíferos subterrâneos e matriz hidrelétrica",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_fis_05",
                            moduleId = "mod_geo_01_fisica",
                            lessonIndex = 5,
                            title = "5. Questões Ambientais e Sustentabilidade",
                            subtopic = "Desmatamento e queimadas nos biomas, poluição de solos e águas, ilhas de calor, inversão térmica e aquecimento global",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_geo_01",
                        moduleId = "mod_geo_01_fisica",
                        title = "Simulado Final: Geografia Física e Meio Ambiente",
                        lockNotice = "Conclua as 5 aulas do Módulo 1 para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 2: Geografia Agrária (O Espaço Rural)
                // ========================================================
                EnemTrailModule(
                    id = "mod_geo_02_agraria",
                    moduleNumber = 2,
                    title = "Geografia Agrária (O Espaço Rural)",
                    summary = "Estrutura fundiária histórica, dualidade agronegócio vs. agricultura familiar, Revolução Verde e conflitos socioespaciais.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "geo_agr_01",
                            moduleId = "mod_geo_02_agraria",
                            lessonIndex = 1,
                            title = "1. Estrutura Fundiária",
                            subtopic = "Concentração de terras no Brasil, índice de Gini rural, Lei de Terras de 1850, estatuto da terra e reforma agrária",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_agr_02",
                            moduleId = "mod_geo_02_agraria",
                            lessonIndex = 2,
                            title = "2. Sistemas Agrícolas",
                            subtopic = "Agricultura familiar e segurança alimentar versus agronegócio patronal exportador de commodities",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_agr_03",
                            moduleId = "mod_geo_02_agraria",
                            lessonIndex = 3,
                            title = "3. A Revolução Verde",
                            subtopic = "Modernização conservadora do campo, mecanização, sementes transgênicas, agrotóxicos e impactos ecológicos",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_agr_04",
                            moduleId = "mod_geo_02_agraria",
                            lessonIndex = 4,
                            title = "4. Fronteiras Agrícolas e Conflitos",
                            subtopic = "Avanço da soja e pecuária no Cerrado e Amazônia (Arco do Desmatamento e MATOPIBA), grilagem e violência contra povos originários",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_geo_02",
                        moduleId = "mod_geo_02_agraria",
                        title = "Simulado Final: Geografia Agrária",
                        lockNotice = "Conclua as 4 aulas do Módulo 2 para liberar o simulado",
                        isLocked = true,
                        xpReward = 110,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 3: Geografia Urbana e Demografia
                // ========================================================
                EnemTrailModule(
                    id = "mod_geo_03_urbana",
                    moduleNumber = 3,
                    title = "Geografia Urbana e Demografia",
                    summary = "Transição demográfica, fluxos migratórios, redes urbanas, metropolização e as contradições da segregação socioespacial.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "geo_urb_01",
                            moduleId = "mod_geo_03_urbana",
                            lessonIndex = 1,
                            title = "1. Dinâmica Populacional (Demografia)",
                            subtopic = "Fases da transição demográfica, análise de pirâmides etárias, queda da fecundidade, envelhecimento e bônus demográfico",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_urb_02",
                            moduleId = "mod_geo_03_urbana",
                            lessonIndex = 2,
                            title = "2. Movimentos Migratórios",
                            subtopic = "Êxodo rural brasileiro, migração nordestina para o Sudeste, migrações pendulares, transumância e refugiados globais",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_urb_03",
                            moduleId = "mod_geo_03_urbana",
                            lessonIndex = 3,
                            title = "3. Processo de Urbanização",
                            subtopic = "Urbanização acelerada nos países do Sul global, hierarquia e redes urbanas, metrópoles, megacidades, cidades globais e conurbação",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_urb_04",
                            moduleId = "mod_geo_03_urbana",
                            lessonIndex = 4,
                            title = "4. Problemas Sociais e Infraestrutura Urbana",
                            subtopic = "Segregação socioespacial, gentrificação, periferização, favelização, mobilidade precarizada e déficit de saneamento básico",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_geo_03",
                        moduleId = "mod_geo_03_urbana",
                        title = "Simulado Final: Urbana e Demografia",
                        lockNotice = "Conclua as 4 aulas do Módulo 3 para liberar o simulado",
                        isLocked = true,
                        xpReward = 110,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 4: Geografia Econômica e Globalização
                // ========================================================
                EnemTrailModule(
                    id = "mod_geo_04_economica",
                    moduleNumber = 4,
                    title = "Geografia Econômica e Globalização",
                    summary = "Evolução do capitalismo, o meio técnico-científico-informacional, DIT e os modelos de industrialização mundial e brasileira.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "geo_eco_01",
                            moduleId = "mod_geo_04_economica",
                            lessonIndex = 1,
                            title = "1. Fases do Capitalismo",
                            subtopic = "Do mercantilismo ao capitalismo industrial, financeiro-monopolista e informacional-cognitivo",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_eco_02",
                            moduleId = "mod_geo_04_economica",
                            lessonIndex = 2,
                            title = "2. Globalização",
                            subtopic = "O meio técnico-científico-informacional, compressão espaço-tempo, empresas transnacionais e exclusão digital",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_eco_03",
                            moduleId = "mod_geo_04_economica",
                            lessonIndex = 3,
                            title = "3. Divisão Internacional do Trabalho (DIT)",
                            subtopic = "Evolução da DIT clássica à nova DIT: centros de inovação tecnológica vs. periferias exportadoras de commodities",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_eco_04",
                            moduleId = "mod_geo_04_economica",
                            lessonIndex = 4,
                            title = "4. Modelos Produtivos e Industrialização",
                            subtopic = "Taylorismo, Fordismo e Toyotismo (acumulação flexível), e as fases da industrialização brasileira por substituição de importações",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_geo_04",
                        moduleId = "mod_geo_04_economica",
                        title = "Simulado Final: Economia e Globalização",
                        lockNotice = "Conclua as 4 aulas do Módulo 4 para liberar o simulado",
                        isLocked = true,
                        xpReward = 110,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 5: Geopolítica e Ordem Mundial
                // ========================================================
                EnemTrailModule(
                    id = "mod_geo_05_geopolitica",
                    moduleNumber = 5,
                    title = "Geopolítica e Ordem Mundial",
                    summary = "Transição da ordem bipolar para a multipolaridade, blocos econômicos, BRICS, conflitos territoriais e recursos estratégicos.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "geo_pol_01",
                            moduleId = "mod_geo_05_geopolitica",
                            lessonIndex = 1,
                            title = "1. A Ordem Mundial",
                            subtopic = "Bipolaridade na Guerra Fria, momento unipolar dos EUA e a emergência da ordem multipolar com a ascensão chinesa",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_pol_02",
                            moduleId = "mod_geo_05_geopolitica",
                            lessonIndex = 2,
                            title = "2. Blocos Econômicos e Fóruns Multilaterais",
                            subtopic = "Graus de integração econômica (zona de livre comércio a união monetária), Mercosul, União Europeia, USMCA e o papel dos BRICS",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_pol_03",
                            moduleId = "mod_geo_05_geopolitica",
                            lessonIndex = 3,
                            title = "3. Conflitos e Tensões Contemporâneas",
                            subtopic = "Questão palestina-israelense no Oriente Médio, Guerra na Ucrânia e tensões OTAN-Rússia, e disputas no Mar do Sul da China",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "geo_pol_04",
                            moduleId = "mod_geo_05_geopolitica",
                            lessonIndex = 4,
                            title = "4. Disputas por Território e Recursos Estratégicos",
                            subtopic = "Geopolítica da energia (petróleo e gás), chokepoints navais (Ormuz, Malaca, Suez), água doce e a corrida pelos recursos do Ártico",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_geo_05",
                        moduleId = "mod_geo_05_geopolitica",
                        title = "Simulado Final: Geopolítica e Ordem Mundial",
                        lockNotice = "Conclua as 4 aulas do Módulo 5 para liberar o simulado",
                        isLocked = true,
                        xpReward = 110,
                        questionCount = 10
                    )
                )
            )
        )
    }

    fun getAllModules(): List<EnemTrailModule> = discipline.modules

    fun findLesson(lessonId: String): EnemTrailLesson? = findLessonById(lessonId)

    fun findLessonById(lessonId: String): EnemTrailLesson? {
        for (module in discipline.modules) {
            for (lesson in module.lessons) {
                if (lesson.id == lessonId) return lesson
            }
        }
        return null
    }

    fun findModuleByLessonId(lessonId: String): EnemTrailModule? {
        return discipline.modules.find { module -> module.lessons.any { it.id == lessonId } }
    }
}
