package com.example.data.languages

import com.example.data.model.EnemTrailDiscipline
import com.example.data.model.EnemTrailLesson
import com.example.data.model.EnemTrailModule
import com.example.data.model.EnemTrailSimulado

/**
 * Trilha Oficial de Linguagens, Códigos e suas Tecnologias para o ENEM.
 * Contempla os 5 módulos estruturantes solicitados pelo estudante:
 * 1. Interpretação de Texto e Semântica
 * 2. Linguística e Comunicação
 * 3. Literatura Brasileira (e bases de Portugal)
 * 4. Gramática Aplicada ao Texto
 * 5. Artes e Expressão Corporal
 */
object EnemLanguagesCurriculum {

    val discipline: EnemTrailDiscipline by lazy {
        EnemTrailDiscipline(
            id = "linguagens_enem",
            name = "Linguagens e Códigos ENEM",
            description = "Trilha preparatória completa de Linguagens, Códigos e suas Tecnologias para o ENEM, abordando Interpretação, Linguística, Literatura Brasileira, Gramática em Contexto e Expressão Corporal/Artes.",
            modules = listOf(
                // ========================================================
                // MÓDULO 1: Interpretação de Texto e Semântica
                // ========================================================
                EnemTrailModule(
                    id = "mod_ling_01_interpretacao",
                    moduleNumber = 1,
                    title = "Interpretação de Texto e Semântica",
                    summary = "Tipologias textuais, intertextualidade, figuras de linguagem, ambiguidade, charges/tirinhas e estratégias argumentativas.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "ling_sem_01",
                            moduleId = "mod_ling_01_interpretacao",
                            lessonIndex = 1,
                            title = "1. Tipologias e Gêneros Textuais",
                            subtopic = "Narrativo, dissertativo, injuntivo, jornalístico e digital",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_sem_02",
                            moduleId = "mod_ling_01_interpretacao",
                            lessonIndex = 2,
                            title = "2. Intertextualidade e Interdiscursividade",
                            subtopic = "Paródia, paráfrase, epígrafe e alusão",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_sem_03",
                            moduleId = "mod_ling_01_interpretacao",
                            lessonIndex = 3,
                            title = "3. Figuras de Linguagem",
                            subtopic = "Metáfora, metonímia, ironia, antítese e paradoxo",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_sem_04",
                            moduleId = "mod_ling_01_interpretacao",
                            lessonIndex = 4,
                            title = "4. Ambiguidade, Duplo Sentido e Humor em Tirinhas/Charges",
                            subtopic = "Polissemia, quebra de expectativa e ironia gráfica",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_sem_05",
                            moduleId = "mod_ling_01_interpretacao",
                            lessonIndex = 5,
                            title = "5. Estratégias Argumentativas",
                            subtopic = "Tese, persuasão, falácias e progressão temática",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_ling_01",
                        moduleId = "mod_ling_01_interpretacao",
                        title = "Simulado Final: Interpretação e Semântica",
                        lockNotice = "Conclua as 5 aulas do Módulo 1 para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 2: Linguística e Comunicação
                // ========================================================
                EnemTrailModule(
                    id = "mod_ling_02_linguistica",
                    moduleNumber = 2,
                    title = "Linguística e Comunicação",
                    summary = "Funções da linguagem de Jakobson, variação e preconceito linguístico, tecnologias na comunicação e coesão/coerência.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "ling_com_01",
                            moduleId = "mod_ling_02_linguistica",
                            lessonIndex = 1,
                            title = "1. Funções da Linguagem",
                            subtopic = "Referencial, emotiva, fática, conativa, metalinguística e poética",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_com_02",
                            moduleId = "mod_ling_02_linguistica",
                            lessonIndex = 2,
                            title = "2. Variação Linguística",
                            subtopic = "Variações regionais, sociais, históricas, de estilo e preconceito linguístico",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_com_03",
                            moduleId = "mod_ling_02_linguistica",
                            lessonIndex = 3,
                            title = "3. Impacto das Tecnologias na Comunicação",
                            subtopic = "Hipertexto, internetês, memes e redes sociais",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_com_04",
                            moduleId = "mod_ling_02_linguistica",
                            lessonIndex = 4,
                            title = "4. Coesão e Coerência",
                            subtopic = "Uso de conectivos, anáforas e catáforas para ligação de ideias",
                            xpReward = 40
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_ling_02",
                        moduleId = "mod_ling_02_linguistica",
                        title = "Simulado Final: Linguística e Comunicação",
                        lockNotice = "Conclua as 4 aulas do Módulo 2 para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 3: Literatura Brasileira (e bases de Portugal)
                // ========================================================
                EnemTrailModule(
                    id = "mod_ling_03_literatura",
                    moduleNumber = 3,
                    title = "Literatura Brasileira (e bases de Portugal)",
                    summary = "Do Quinhentismo ao Contemporâneo: Barroco, Romantismo, Realismo com Machado de Assis, Pré-Modernismo, Modernismo de 22 e Poesia Marginal.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "ling_lit_01",
                            moduleId = "mod_ling_03_literatura",
                            lessonIndex = 1,
                            title = "1. Era Colonial",
                            subtopic = "Quinhentismo, Barroco e Arcadismo",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_lit_02",
                            moduleId = "mod_ling_03_literatura",
                            lessonIndex = 2,
                            title = "2. Romantismo",
                            subtopic = "As 3 gerações da poesia e a prosa romântica",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_lit_03",
                            moduleId = "mod_ling_03_literatura",
                            lessonIndex = 3,
                            title = "3. Realismo, Naturalismo e Parnasianismo",
                            subtopic = "Foco absoluto em Machado de Assis e Aluísio Azevedo",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "ling_lit_04",
                            moduleId = "mod_ling_03_literatura",
                            lessonIndex = 4,
                            title = "4. Simbolismo e Pré-Modernismo",
                            subtopic = "Euclides da Cunha, Lima Barreto e Monteiro Lobato",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_lit_05",
                            moduleId = "mod_ling_03_literatura",
                            lessonIndex = 5,
                            title = "5. Modernismo Brasileiro",
                            subtopic = "A Semana de Arte Moderna de 1922 e as 3 fases modernistas",
                            xpReward = 40
                        ),
                        EnemTrailLesson(
                            id = "ling_lit_06",
                            moduleId = "mod_ling_03_literatura",
                            lessonIndex = 6,
                            title = "6. Literatura Contemporânea",
                            subtopic = "Poesia marginal, concretismo e tropicalismo",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_ling_03",
                        moduleId = "mod_ling_03_literatura",
                        title = "Simulado Final: Literatura Brasileira",
                        lockNotice = "Conclua as 6 aulas do Módulo 3 para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 4: Gramática Aplicada ao Texto
                // ========================================================
                EnemTrailModule(
                    id = "mod_ling_04_gramatica",
                    moduleNumber = 4,
                    title = "Gramática Aplicada ao Texto",
                    summary = "Classes de palavras em contexto semântico, sintaxe e sentido em orações coordenadas/subordinadas, regência e pontuação expressiva.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "ling_gram_01",
                            moduleId = "mod_ling_04_gramatica",
                            lessonIndex = 1,
                            title = "1. Classes de Palavras em Contexto",
                            subtopic = "Valor semântico de preposições, conjunções e pronomes",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_gram_02",
                            moduleId = "mod_ling_04_gramatica",
                            lessonIndex = 2,
                            title = "2. Sintaxe e Sentido",
                            subtopic = "Orações coordenadas e subordinadas no texto",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_gram_03",
                            moduleId = "mod_ling_04_gramatica",
                            lessonIndex = 3,
                            title = "3. Concordância e Regência",
                            subtopic = "Concordância verbal/nominal e regência aplicada à norma-padrão",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_gram_04",
                            moduleId = "mod_ling_04_gramatica",
                            lessonIndex = 4,
                            title = "4. Pontuação",
                            subtopic = "Uso da vírgula, travessões e reticências como recursos de sentido",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_ling_04",
                        moduleId = "mod_ling_04_gramatica",
                        title = "Simulado Final: Gramática Aplicada ao Texto",
                        lockNotice = "Conclua as 4 aulas do Módulo 4 para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                ),

                // ========================================================
                // MÓDULO 5: Artes e Expressão Corporal
                // ========================================================
                EnemTrailModule(
                    id = "mod_ling_05_artes_corpo",
                    moduleNumber = 5,
                    title = "Artes e Expressão Corporal",
                    summary = "Vanguardas europeias, modernismo nas artes plásticas (Tarsila e Portinari), cultura corporal, esportes x atividades físicas e padrões estéticos.",
                    lessons = listOf(
                        EnemTrailLesson(
                            id = "ling_art_01",
                            moduleId = "mod_ling_05_artes_corpo",
                            lessonIndex = 1,
                            title = "1. Vanguardas Europeias",
                            subtopic = "Cubismo, Surrealismo, Dadaísmo, Expressionismo e Futurismo",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_art_02",
                            moduleId = "mod_ling_05_artes_corpo",
                            lessonIndex = 2,
                            title = "2. Arte Brasileira",
                            subtopic = "Semana de 22, Tarsila do Amaral, Portinari e Arte Contemporânea",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_art_03",
                            moduleId = "mod_ling_05_artes_corpo",
                            lessonIndex = 3,
                            title = "3. O Corpo e a Saúde",
                            subtopic = "Práticas corporais, esporte x atividade física, dança e lutas",
                            xpReward = 35
                        ),
                        EnemTrailLesson(
                            id = "ling_art_04",
                            moduleId = "mod_ling_05_artes_corpo",
                            lessonIndex = 4,
                            title = "4. Padrões Estéticos e Indústria Cultural",
                            subtopic = "Mídia, consumo do corpo e alienação",
                            xpReward = 35
                        )
                    ),
                    finalSimulado = EnemTrailSimulado(
                        id = "simulado_final_ling_05",
                        moduleId = "mod_ling_05_artes_corpo",
                        title = "Simulado Final: Artes e Expressão Corporal",
                        lockNotice = "Conclua as 4 aulas do Módulo 5 para liberar o simulado",
                        isLocked = true,
                        xpReward = 120,
                        questionCount = 10
                    )
                )
            )
        )
    }

    fun getAllModules(): List<EnemTrailModule> = discipline.modules

    fun findLesson(lessonId: String): EnemTrailLesson? {
        return discipline.modules
            .flatMap { it.lessons }
            .firstOrNull { it.id == lessonId }
    }
}
