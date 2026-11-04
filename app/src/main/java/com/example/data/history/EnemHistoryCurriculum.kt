package com.example.data.history

import com.example.data.model.EnemTrailDiscipline
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado

/**
 * Matriz Completa e Oficial da Trilha de História e Ciências Humanas para o ENEM no Knowverse.
 *
 * Diretriz Pedagógica:
 * - A prova do ENEM analisa processos sociais, cidadania, lutas por direitos,
 *   relações de trabalho, conflitos de poder e transformações culturais.
 *
 * Estrutura: 6 Módulos Estruturantes • 26 Aulas Analíticas:
 * Módulo 1: História do Brasil: Colônia (4 aulas)
 * Módulo 2: História do Brasil: Império (4 aulas)
 * Módulo 3: História do Brasil: República (5 aulas)
 * Módulo 4: História Geral: Antiguidade e Idade Média (4 aulas)
 * Módulo 5: História Geral: Idade Moderna (4 aulas)
 * Módulo 6: História Geral: Idade Contemporânea (5 aulas)
 */
object EnemHistoryCurriculum {

    val discipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "historia_enem",
            name = "História • Ciências Humanas ENEM",
            description = "Trilha analítica de História do Brasil e Geral com foco em processos sociais, cidadania, lutas por direitos, relações de trabalho e transformações culturais.",
            modules = listOf(
                // ========================================================
                // MÓDULO 1: História do Brasil: Colônia (4 aulas)
                // ========================================================
                EnemTrailModule(
                    id = "mod_hist_01_colonia",
                    moduleNumber = 1,
                    title = "História do Brasil: Colônia",
                    summary = "Sociedades originárias e violência colonial, economia da plantation e mineração, escravidão e resistências, e as revoltas emancipacionistas.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "hist_col_01",
                            moduleId = "mod_hist_01_colonia",
                            lessonIndex = 1,
                            title = "1. A Conquista e os Povos Originários",
                            subtopic = "Sociedades indígenas pré-cabralinas, choque cultural e a violência da colonização",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "hist_col_02",
                            moduleId = "mod_hist_01_colonia",
                            lessonIndex = 2,
                            title = "2. Economia e Sociedade Colonial",
                            subtopic = "Ciclo do açúcar, engenhos, plantation, pecuária no interior e a mineração no século XVIII",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "hist_col_03",
                            moduleId = "mod_hist_01_colonia",
                            lessonIndex = 3,
                            title = "3. A Escravidão Negra e as Resistências",
                            subtopic = "Tráfico negreiro, a vida nas senzalas, quilombos (Palmares) e a cultura afro-brasileira como forma de resistência",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_col_04",
                            moduleId = "mod_hist_01_colonia",
                            lessonIndex = 4,
                            title = "4. Revoltas Coloniais e Crise do Sistema",
                            subtopic = "Movimentos nativistas (Emboabas, Mascates) versus movimentos emancipacionistas (Inconfidência Mineira e Conjuração Baiana)",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_hist_col",
                        moduleId = "mod_hist_01_colonia",
                        title = "Simulado Final: História do Brasil Colônia",
                        lockNotice = "Conclua as 4 aulas de História Colonial para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 2: História do Brasil: Império (4 aulas)
                // ========================================================
                EnemTrailModule(
                    id = "mod_hist_02_imperio",
                    moduleNumber = 2,
                    title = "História do Brasil: Império",
                    summary = "Abertura dos portos, Constituição de 1824 com Poder Moderador, rebeliões regenciais, economia cafeeira no Segundo Reinado e a abolição/República.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "hist_imp_01",
                            moduleId = "mod_hist_02_imperio",
                            lessonIndex = 1,
                            title = "1. Processo de Independência e Primeiro Reinado",
                            subtopic = "Vinda da família real em 1808, o 7 de setembro e a Constituição outorgada de 1824",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "hist_imp_02",
                            moduleId = "mod_hist_02_imperio",
                            lessonIndex = 2,
                            title = "2. Período Regencial e as Revoltas",
                            subtopic = "A ausência de D. Pedro II, instabilidade política e revoltas populares/separatistas (Cabanagem, Farroupilha, Balaiada e Revolta dos Malês)",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_imp_03",
                            moduleId = "mod_hist_02_imperio",
                            lessonIndex = 3,
                            title = "3. Segundo Reinado",
                            subtopic = "A política do café no Sudeste, a Guerra do Paraguai e o parlamentarismo às avessas",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "hist_imp_04",
                            moduleId = "mod_hist_02_imperio",
                            lessonIndex = 4,
                            title = "4. A Crise do Império e o Fim da Escravidão",
                            subtopic = "As leis abolicionistas graduais, imigração europeia, movimento republicano e o Golpe de 1889",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_hist_imp",
                        moduleId = "mod_hist_02_imperio",
                        title = "Simulado Final: História do Brasil Império",
                        lockNotice = "Conclua as 4 aulas de História do Império para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 3: História do Brasil: República (5 aulas)
                // ========================================================
                EnemTrailModule(
                    id = "mod_hist_03_republica",
                    moduleNumber = 3,
                    title = "História do Brasil: República",
                    summary = "Da República Velha oligárquica à Nova República: coronelismo, Era Vargas, nacional-desenvolvimentismo, Ditadura Militar e a Constituição de 1988.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "hist_rep_01",
                            moduleId = "mod_hist_03_republica",
                            lessonIndex = 1,
                            title = "1. República Velha (1889–1930)",
                            subtopic = "Coronelismo, política do café com leite, revoltas populares (Canudos, Chibata, Vacina, Contestado) e o Tenentismo",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_rep_02",
                            moduleId = "mod_hist_03_republica",
                            lessonIndex = 2,
                            title = "2. Era Vargas (1930–1945)",
                            subtopic = "A Revolução de 30, a CLT e as leis trabalhistas, o Estado Novo e a propaganda de massas",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_rep_03",
                            moduleId = "mod_hist_03_republica",
                            lessonIndex = 3,
                            title = "3. República Populista (1945–1964)",
                            subtopic = "O nacional-desenvolvimentismo de JK (Brasília e a indústria automobilística), crises políticas e as Reformas de Base de Jango",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_rep_04",
                            moduleId = "mod_hist_03_republica",
                            lessonIndex = 4,
                            title = "4. Ditadura Civil-Militar (1964–1985)",
                            subtopic = "Atos Institucionais (AI-5), repressão, censura, o Milagre Econômico, movimentos de resistência e as Diretas Já",
                            xpReward = 45
                        ),
                        EnemTrailLesson(
                            id = "hist_rep_05",
                            moduleId = "mod_hist_03_republica",
                            lessonIndex = 5,
                            title = "5. Nova República (1985 até os dias atuais)",
                            subtopic = "A Constituição Cidadã de 1988, estabilização econômica (Plano Real) e as conquistas dos movimentos sociais contemporâneos",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_hist_rep",
                        moduleId = "mod_hist_03_republica",
                        title = "Simulado Final: História do Brasil República",
                        lockNotice = "Conclua as 5 aulas de História Republicana para liberar o simulado",
                        isLocked = true,
                        xpReward = 130,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 4: História Geral: Antiguidade e Idade Média (4 aulas)
                // ========================================================
                EnemTrailModule(
                    id = "mod_hist_04_antiguidade_media",
                    moduleNumber = 4,
                    title = "História Geral: Antiguidade e Idade Média",
                    summary = "Democracia ateniense e militarismo espartano, República e Império Romano, Feudalismo e hegemonia cristã, e o Mundo Islâmico.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "hist_ant_01",
                            moduleId = "mod_hist_04_antiguidade_media",
                            lessonIndex = 1,
                            title = "1. Grécia Antiga",
                            subtopic = "A invenção da Democracia em Atenas, o modelo militarista em Esparta e a cidadania restrita",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "hist_ant_02",
                            moduleId = "mod_hist_04_antiguidade_media",
                            lessonIndex = 2,
                            title = "2. Roma Antiga",
                            subtopic = "Da República ao Império, o Direito Romano, a política de pão e circo e a crise do escravismo",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "hist_ant_03",
                            moduleId = "mod_hist_04_antiguidade_media",
                            lessonIndex = 3,
                            title = "3. Mundo Medieval",
                            subtopic = "O Feudalismo (economia de subsistência e ordens sociais), a hegemonia da Igreja Católica, as Cruzadas e o renascimento comercial/urbano",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_ant_04",
                            moduleId = "mod_hist_04_antiguidade_media",
                            lessonIndex = 4,
                            title = "4. O Mundo Islâmico e as Rotas Comerciais",
                            subtopic = "Expansão árabe, avanços científicos e conexões comerciais e culturais com o Ocidente",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_hist_ant_med",
                        moduleId = "mod_hist_04_antiguidade_media",
                        title = "Simulado Final: Antiguidade e Idade Média",
                        lockNotice = "Conclua as 4 aulas de Antiguidade e Medievo para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 5: História Geral: Idade Moderna (4 aulas)
                // ========================================================
                EnemTrailModule(
                    id = "mod_hist_05_moderna",
                    moduleNumber = 5,
                    title = "História Geral: Idade Moderna",
                    summary = "Renascimento cultural e humanismo, Reforma Protestante e Contrarreforma, Absolutismo e Mercantilismo, e as Grandes Navegações.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "hist_mod_01",
                            moduleId = "mod_hist_05_moderna",
                            lessonIndex = 1,
                            title = "1. Renascimento Cultural e Científico",
                            subtopic = "O humanismo, o antropocentrismo e a ruptura com o teocentrismo medieval",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "hist_mod_02",
                            moduleId = "mod_hist_05_moderna",
                            lessonIndex = 2,
                            title = "2. Reformas Religiosas",
                            subtopic = "A Reforma Protestante de Lutero e Calvino e a Contrarreforma Católica (Companhia de Jesus e a Inquisição)",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_mod_03",
                            moduleId = "mod_hist_05_moderna",
                            lessonIndex = 3,
                            title = "3. Absolutismo e Mercantilismo",
                            subtopic = "A concentração do poder real, o pacto colonial e a busca por metais preciosos",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "hist_mod_04",
                            moduleId = "mod_hist_05_moderna",
                            lessonIndex = 4,
                            title = "4. Expansão Marítima Europeia",
                            subtopic = "As grandes navegações, a rota para as Índias e a colonização das Américas",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_hist_mod",
                        moduleId = "mod_hist_05_moderna",
                        title = "Simulado Final: História Moderna",
                        lockNotice = "Conclua as 4 aulas de História Moderna para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 6: História Geral: Idade Contemporânea (5 aulas)
                // ========================================================
                EnemTrailModule(
                    id = "mod_hist_06_contemporanea",
                    moduleNumber = 6,
                    title = "História Geral: Idade Contemporânea",
                    summary = "Iluminismo, Revoluções Industriais e ideologias trabalhistas, Imperialismo, Guerras Mundiais/Totalitarismos e a Guerra Fria.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "hist_cont_01",
                            moduleId = "mod_hist_06_contemporanea",
                            lessonIndex = 1,
                            title = "1. Iluminismo e a Era das Revoluções",
                            subtopic = "As ideias liberais, a Revolução Francesa e a Independência dos Estados Unidos",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_cont_02",
                            moduleId = "mod_hist_06_contemporanea",
                            lessonIndex = 2,
                            title = "2. Revoluções Industriais",
                            subtopic = "As mudanças nas relações de trabalho, a exploração do proletariado, ludismo, cartismo e o surgimento do Socialismo e Anarquismo",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_cont_03",
                            moduleId = "mod_hist_06_contemporanea",
                            lessonIndex = 3,
                            title = "3. Imperialismo e Neocolonialismo no Século XIX",
                            subtopic = "A Partilha da África e a dominação da Ásia justificadas pelo Darwinismo Social",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "hist_cont_04",
                            moduleId = "mod_hist_06_contemporanea",
                            lessonIndex = 4,
                            title = "4. As Guerras Mundiais e os Totalitarismos",
                            subtopic = "Primeira Guerra, Revolução Russa de 1917, Crise de 1929, Fascismo, Nazismo e o Holocausto na Segunda Guerra",
                            xpReward = 45
                        ),
                        EnemTrailLesson(
                            id = "hist_cont_05",
                            moduleId = "mod_hist_06_contemporanea",
                            lessonIndex = 5,
                            title = "5. Guerra Fria e Descolonização",
                            subtopic = "A bipolaridade mundial (EUA x URSS), Corrida Espacial, Guerra do Vietnã e os processos de libertação na África e na Ásia",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_hist_cont",
                        moduleId = "mod_hist_06_contemporanea",
                        title = "Simulado Final: História Contemporânea",
                        lockNotice = "Conclua as 5 aulas de História Contemporânea para liberar o simulado",
                        isLocked = true,
                        xpReward = 130,
                        questionCount = 10
                    )
                )
            )
        )
    }

    fun getAllModules(): List<EnemTrailModule> = discipline.modules

    fun findModule(moduleId: String): EnemTrailModule? = discipline.modules.find { it.id == moduleId }

    fun findLesson(lessonId: String): EnemTrailLesson? {
        return discipline.modules
            .flatMap { it.lessons }
            .firstOrNull { it.id == lessonId }
    }
}
