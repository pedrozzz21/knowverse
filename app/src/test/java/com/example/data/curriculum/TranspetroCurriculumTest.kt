package com.example.data.curriculum

import com.example.data.transpetro.TranspetroCurriculum
import com.example.data.transpetro.TranspetroDidacticProvider
import com.example.data.transpetro.TranspetroQuestionBankLoader
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

/**
 * Suite de testes unitários para o tópico "Concurso Transpetro - ADM e Controle"
 * Valida rigorosamente a divisão curricular, a integridade de todas as 120 aulas,
 * os 240 exercícios suplementares autorais e o banco de questões da Fundação Cesgranrio.
 */
class TranspetroCurriculumTest {

    @Test
    fun testTranspetroCurriculumStructure() {
        val disciplines = TranspetroCurriculum.allDisciplines
        assertEquals("Devem existir exatamente 6 matérias no currículo da Transpetro", 6, disciplines.size)

        val discMap = disciplines.associateBy { it.id }
        assertTrue("Matéria de Administração deve existir", discMap.containsKey("transpetro_adm"))
        assertTrue("Matéria de Finanças deve existir", discMap.containsKey("transpetro_fin"))
        assertTrue("Matéria de Logística deve existir", discMap.containsKey("transpetro_log"))
        assertTrue("Matéria de Informática deve existir", discMap.containsKey("transpetro_inf"))
        assertTrue("Matéria de Português deve existir", discMap.containsKey("transpetro_por"))
        assertTrue("Matéria de Matemática deve existir", discMap.containsKey("transpetro_mat"))

        // Grupo A (Específico): 4 matérias, 24 tópicos, 73 aulas
        val adm = discMap.getValue("transpetro_adm")
        assertEquals("Administração deve possuir 9 tópicos/módulos", 9, adm.modules.size)
        assertEquals("Administração deve possuir 27 aulas", 27, adm.modules.sumOf { it.lessons.size })

        val fin = discMap.getValue("transpetro_fin")
        assertEquals("Finanças deve possuir 4 tópicos/módulos", 4, fin.modules.size)
        assertEquals("Finanças deve possuir 15 aulas", 15, fin.modules.sumOf { it.lessons.size })

        val log = discMap.getValue("transpetro_log")
        assertEquals("Logística deve possuir 6 tópicos/módulos", 6, log.modules.size)
        assertEquals("Logística deve possuir 18 aulas", 18, log.modules.sumOf { it.lessons.size })

        val inf = discMap.getValue("transpetro_inf")
        assertEquals("Informática deve possuir 5 tópicos/módulos", 5, inf.modules.size)
        assertEquals("Informática deve possuir 13 aulas", 13, inf.modules.sumOf { it.lessons.size })

        // Grupo B (Língua Portuguesa): 1 matéria, 11 tópicos, 22 aulas
        val por = discMap.getValue("transpetro_por")
        assertEquals("Português deve possuir 11 tópicos/módulos", 11, por.modules.size)
        assertEquals("Português deve possuir 22 aulas", 22, por.modules.sumOf { it.lessons.size })

        // Grupo C (Matemática): 1 matéria, 12 tópicos, 25 aulas
        val mat = discMap.getValue("transpetro_mat")
        assertEquals("Matemática deve possuir 12 tópicos/módulos", 12, mat.modules.size)
        assertEquals("Matemática deve possuir 25 aulas", 25, mat.modules.sumOf { it.lessons.size })

        val totalModules = disciplines.sumOf { it.modules.size }
        val totalLessons = disciplines.sumOf { it.modules.sumOf { m -> m.lessons.size } }

        assertEquals("Total de módulos deve ser 47", 47, totalModules)
        assertEquals("Total de aulas didáticas deve ser exatamente 120", 120, totalLessons)
    }

    @Test
    fun testEveryLessonHasDidacticBundleAndQualityRequirements() {
        val allLessons = TranspetroCurriculum.getAllLessons()
        assertEquals(120, allLessons.size)

        allLessons.forEach { lesson ->
            val bundle = TranspetroDidacticProvider.getDidacticBundle(lesson.id)
            assertNotNull("Bundle didático não pode ser nulo para a aula ${lesson.id}", bundle)

            assertTrue("Teoria deve ter conteúdo pedagógico substancial na aula ${lesson.id}", bundle!!.theoryMarkdown.length >= 100)
            assertTrue("A aula ${lesson.id} deve conter seção de INTRODUÇÃO", bundle.theoryMarkdown.contains("1. INTRODUÇÃO"))
            assertTrue("A aula ${lesson.id} deve conter seção de EXPLICAÇÃO TEÓRICA", bundle.theoryMarkdown.contains("2. EXPLICAÇÃO TEÓRICA"))
            assertTrue("A aula ${lesson.id} deve conter seção de EXEMPLOS PRÁTICOS", bundle.theoryMarkdown.contains("3. EXEMPLOS PRÁTICOS"))
            assertTrue("A aula ${lesson.id} deve conter seção de RESUMO PARA REVISÃO", bundle.theoryMarkdown.contains("4. RESUMO PARA REVISÃO"))
            assertTrue("Dica de ouro (Atenção para a prova) deve estar preenchida na aula ${lesson.id}", bundle.goldTip.length >= 15)

            // Fórmulas e Sintaxe
            assertTrue("Fórmulas devem estar preenchidas na aula ${lesson.id}", bundle.formulas.isNotEmpty())
            val formula = bundle.formulas.first()
            assertTrue("Nome da fórmula/sintaxe obrigatório na aula ${lesson.id}", formula.name.isNotBlank())
            assertTrue("Sintaxe/fórmula obrigatória na aula ${lesson.id}", formula.syntax.isNotBlank())

            // Exemplo Resolvido
            assertTrue("Exemplo resolvido deve estar presente na aula ${lesson.id}", bundle.solvedExamples.isNotEmpty())
            val example = bundle.solvedExamples.first()
            assertTrue("Título do exemplo obrigatório na aula ${lesson.id}", example.title.isNotBlank())
            assertTrue("Enunciado do exemplo obrigatório na aula ${lesson.id}", example.problem.isNotBlank())
            assertTrue("Explicação/passos do exemplo obrigatórios na aula ${lesson.id}", example.explanation.isNotBlank())
        }
    }

    @Test
    fun testEveryLessonHasSupplementaryExercises() {
        val allLessons = TranspetroCurriculum.getAllLessons()
        var totalSupplementaryExercises = 0

        allLessons.forEach { lesson ->
            val exercises = TranspetroDidacticProvider.getSupplementaryExercisesForLesson(lesson.id)
            assertTrue("Aula ${lesson.id} deve ter pelo menos 2 exercícios suplementares comentados", exercises.size >= 2)
            totalSupplementaryExercises += exercises.size

            exercises.forEachIndexed { idx, ex ->
                assertTrue("Enunciado do exercício $idx na aula ${lesson.id} deve ser substancial", ex.problemStatement.length >= 20)
                assertEquals("Exercício $idx na aula ${lesson.id} deve ter exatamente 5 alternativas (padrão Cesgranrio)", 5, ex.options.size)
                assertTrue("Índice correto deve estar entre 0 e 4 no exercício $idx da aula ${lesson.id}", ex.correctIndex in 0..4)
                assertTrue("Resolução comentada deve estar presente no exercício $idx da aula ${lesson.id}", ex.stepByStepSolution.length >= 20)
                assertTrue("Alternativas não podem ser vazias no exercício $idx da aula ${lesson.id}", ex.options.none { it.isBlank() })
                assertEquals(
                    "Alternativas devem ser distintas no exercício $idx da aula ${lesson.id}",
                    ex.options.size,
                    ex.options.distinct().size
                )
            }
        }

        assertEquals("Devem existir exatamente 240 exercícios suplementares autorais", 240, totalSupplementaryExercises)
    }

    @Test
    fun testLessonRefsMatchingCurriculum() {
        val lessonRefs = TranspetroQuestionBankLoader.getAllLessonRefs()
        assertEquals("Devem existir 120 referências de aula para o banco de questões", 120, lessonRefs.size)

        val curriculumIds = TranspetroCurriculum.getAllLessons().map { it.id }.toSet()
        val refIds = lessonRefs.map { it.lessonId }.toSet()

        assertEquals("IDs do currículo e do loader devem coincidir perfeitamente", curriculumIds, refIds)
    }

    @Test
    fun testLessonToAppLessonConversion() {
        val allLessons = TranspetroCurriculum.getAllLessons()

        allLessons.forEach { trailLesson ->
            val appLesson = trailLesson.toAppLesson()
            assertEquals(trailLesson.id, appLesson.id)
            assertTrue("Explicação da aula ${trailLesson.id} deve ser não-vazia", appLesson.explanation.isNotBlank())
            assertTrue("Exercícios da aula ${trailLesson.id} devem ser resolvidos", appLesson.mathExercises.isNotEmpty())
            assertEquals(trailLesson.xpReward, appLesson.xpReward)
            assertTrue("XP da aula deve ser positivo", appLesson.xpReward > 0)
        }
    }
}

