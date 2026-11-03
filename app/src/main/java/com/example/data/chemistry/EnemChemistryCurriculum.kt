package com.example.data.chemistry

import com.example.data.model.EnemTrailDiscipline
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado

/**
 * Trilha Estruturante Oficial de Química para o ENEM (Ciências da Natureza).
 * Organizada em 4 grandes módulos temáticos e 21 aulas aprofundadas:
 * 1. Química Geral e Estrutura da Matéria (5 Aulas)
 * 2. Físico-Química: A Matemática da Química (6 Aulas)
 * 3. Química Orgânica (5 Aulas)
 * 4. Química Ambiental e do Cotidiano (5 Aulas)
 */
object EnemChemistryCurriculum {

    val discipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "quimica_enem",
            name = "Química ENEM • Natureza",
            description = "Trilha analítica de Química para o ENEM. Aborda a matéria, transformações físico-químicas, funções orgânicas, combustíveis e processos ambientais no cotidiano.",
            modules = listOf(
                // ========================================================
                // MÓDULO 1: Química Geral e Estrutura da Matéria
                // ========================================================
                EnemTrailModule(
                    id = "mod_quim_01_geral",
                    moduleNumber = 1,
                    title = "Química Geral e Estrutura da Matéria",
                    summary = "Substâncias e misturas, métodos de fracionamento, modelos atômicos, tabela periódica, ligações químicas e forças intermoleculares.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "quim_ger_01",
                            moduleId = "mod_quim_01_geral",
                            lessonIndex = 1,
                            title = "1. Substâncias e Misturas",
                            subtopic = "Gráficos de mudança de fase (eutéticas e azeotrópicas) e processos de separação de misturas",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_ger_02",
                            moduleId = "mod_quim_01_geral",
                            lessonIndex = 2,
                            title = "2. Modelos Atômicos e Estrutura",
                            subtopic = "Evolução dos modelos (Dalton, Thomson, Rutherford, Bohr) e distribuição eletrônica em subníveis",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_ger_03",
                            moduleId = "mod_quim_01_geral",
                            lessonIndex = 3,
                            title = "3. Tabela Periódica e Propriedades",
                            subtopic = "Famílias, períodos e tendências de raio atômico, energia de ionização e eletronegatividade",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_ger_04",
                            moduleId = "mod_quim_01_geral",
                            lessonIndex = 4,
                            title = "4. Ligações Químicas e Geometria",
                            subtopic = "Ligação iônica, covalente, metálica, teoria do octeto e geometrias moleculares espaciais",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_ger_05",
                            moduleId = "mod_quim_01_geral",
                            lessonIndex = 5,
                            title = "5. Polaridade e Forças Intermoleculares",
                            subtopic = "Dipolo induzido, dipolo-dipolo, pontes de hidrogênio e a regra 'semelhante dissolve semelhante'",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_quim_01",
                        moduleId = "mod_quim_01_geral",
                        title = "Simulado Final: Química Geral e Estrutura da Matéria",
                        lockNotice = "Conclua as 5 aulas do Módulo 1 para liberar o simulado",
                        isLocked = true,
                        xpReward = 110,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 2: Físico-Química (A Matemática da Química)
                // ========================================================
                EnemTrailModule(
                    id = "mod_quim_02_fisico",
                    moduleNumber = 2,
                    title = "Físico-Química: A Matemática da Química",
                    summary = "Estequiometria, cálculos de soluções, termoquímica, cinética das reações, equilíbrios iônicos e eletroquímica (pilhas e eletrólise).",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "quim_fis_01",
                            moduleId = "mod_quim_02_fisico",
                            lessonIndex = 1,
                            title = "1. Estequiometria e Cálculos Químicos",
                            subtopic = "Leis ponderais, constante de Avogadro, reagente limitante, excesso, rendimento e pureza",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "quim_fis_02",
                            moduleId = "mod_quim_02_fisico",
                            lessonIndex = 2,
                            title = "2. Soluções e Concentrações",
                            subtopic = "Curvas de solubilidade, concentração molar, concentração comum, título percentual, ppm e diluição",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_fis_03",
                            moduleId = "mod_quim_02_fisico",
                            lessonIndex = 3,
                            title = "3. Termoquímica",
                            subtopic = "Processos endotérmicos e exotérmicos, entalpia padrão de formação, energia de ligação e Lei de Hess",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "quim_fis_04",
                            moduleId = "mod_quim_02_fisico",
                            lessonIndex = 4,
                            title = "4. Cinética Química",
                            subtopic = "Velocidade média das reações, teoria das colisões, energia de ativação e ação de catalisadores",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_fis_05",
                            moduleId = "mod_quim_02_fisico",
                            lessonIndex = 5,
                            title = "5. Equilíbrio Químico e pH",
                            subtopic = "Constantes Kc e Kp, Princípio de Le Chatelier, autoionização da água e cálculo de pH e pOH",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "quim_fis_06",
                            moduleId = "mod_quim_02_fisico",
                            lessonIndex = 6,
                            title = "6. Eletroquímica: Pilhas e Eletrólise",
                            subtopic = "Número de oxidação (Nox), semi-reações de oxirredução, potencial padrão de pilha e eletrólise",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_quim_02",
                        moduleId = "mod_quim_02_fisico",
                        title = "Simulado Final: Físico-Química",
                        lockNotice = "Conclua as 6 aulas do Módulo 2 para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 12
                    )
                ),

                // ========================================================
                // MÓDULO 3: Química Orgânica
                // ========================================================
                EnemTrailModule(
                    id = "mod_quim_03_organica",
                    moduleNumber = 3,
                    title = "Química Orgânica",
                    summary = "Cadeias carbônicas, identificação de todas as funções orgânicas, regras da IUPAC, isomeria plana e espacial, e reações clássicas.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "quim_org_01",
                            moduleId = "mod_quim_03_organica",
                            lessonIndex = 1,
                            title = "1. Introdução e Cadeias Carbônicas",
                            subtopic = "Postulados de Kekulé, hibridização do carbono (sp³, sp², sp) e classificação das cadeias",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_org_02",
                            moduleId = "mod_quim_03_organica",
                            lessonIndex = 2,
                            title = "2. Reconhecimento de Funções Orgânicas",
                            subtopic = "Identificação de Hidrocarbonetos, Álcoois, Fenóis, Éteres, Aldeídos, Cetonas, Ácidos, Ésteres e Nitrogenadas",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "quim_org_03",
                            moduleId = "mod_quim_03_organica",
                            lessonIndex = 3,
                            title = "3. Nomenclatura Orgânica IUPAC",
                            subtopic = "Regras oficiais da IUPAC (prefixo, infixo e sufixo), cadeia principal e numeração de ramificações",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_org_04",
                            moduleId = "mod_quim_03_organica",
                            lessonIndex = 4,
                            title = "4. Isomeria Plana e Espacial",
                            subtopic = "Isomeria constitucional (função, cadeia, posição, metameria), isomeria geométrica (Cis-Trans) e óptica",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "quim_org_05",
                            moduleId = "mod_quim_03_organica",
                            lessonIndex = 5,
                            title = "5. Reações Orgânicas Clássicas",
                            subtopic = "Esterificação de Fischer, hidrólise alcalina (saponificação), reações de combustão e polimerização",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_quim_03",
                        moduleId = "mod_quim_03_organica",
                        title = "Simulado Final: Química Orgânica",
                        lockNotice = "Conclua as 5 aulas do Módulo 3 para liberar o simulado",
                        isLocked = true,
                        xpReward = 110,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 4: Química Ambiental e do Cotidiano
                // ========================================================
                EnemTrailModule(
                    id = "mod_quim_04_ambiental",
                    moduleNumber = 4,
                    title = "Química Ambiental e do Cotidiano (Padrão ENEM)",
                    summary = "Tratamento de água e efluentes, química atmosférica (chuva ácida, ozônio e efeito estufa), matrizes energéticas, sabões e polímeros.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "quim_amb_01",
                            moduleId = "mod_quim_04_ambiental",
                            lessonIndex = 1,
                            title = "1. Química da Água e Saneamento",
                            subtopic = "Etapas completas da Estação de Tratamento de Água (ETA) e tratamento biológico de esgotos",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_amb_02",
                            moduleId = "mod_quim_04_ambiental",
                            lessonIndex = 2,
                            title = "2. Química Atmosférica e Poluição",
                            subtopic = "Formação da chuva ácida (SOx e NOx), destruição da camada de ozônio por CFCs e gases estufa",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "quim_amb_03",
                            moduleId = "mod_quim_04_ambiental",
                            lessonIndex = 3,
                            title = "3. Combustíveis e Energia",
                            subtopic = "Destilação fracionada do petróleo, craqueamento, gasolina, etanol de biomassa e biodiesel",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "quim_amb_04",
                            moduleId = "mod_quim_04_ambiental",
                            lessonIndex = 4,
                            title = "4. Ação de Sabões e Detergentes",
                            subtopic = "Estrutura anfifílica, micelas, quebra de tensão superficial e comportamento em águas duras",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "quim_amb_05",
                            moduleId = "mod_quim_04_ambiental",
                            lessonIndex = 5,
                            title = "5. Polímeros e Reciclagem",
                            subtopic = "Termoplásticos vs termofixos, polímeros de consumo (PET, PVC, PEAD) e bioplásticos biodegradáveis",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_quim_04",
                        moduleId = "mod_quim_04_ambiental",
                        title = "Simulado Final: Química Ambiental e do Cotidiano",
                        lockNotice = "Conclua as 5 aulas do Módulo 4 para liberar o simulado",
                        isLocked = true,
                        xpReward = 110,
                        questionCount = 10
                    )
                )
            )
        )
    }

    fun getAllModules(): List<EnemTrailModule> = discipline.modules

    fun getAllLessons(): List<EnemTrailLesson> = discipline.modules.flatMap { it.lessons }

    fun findLesson(lessonId: String): EnemTrailLesson? = findLessonById(lessonId)

    fun findLessonById(lessonId: String): EnemTrailLesson? {
        return discipline.modules.flatMap { it.lessons }.find { it.id == lessonId }
    }

    fun getModule(moduleId: String): EnemTrailModule? {
        return discipline.modules.find { it.id == moduleId }
    }
}
