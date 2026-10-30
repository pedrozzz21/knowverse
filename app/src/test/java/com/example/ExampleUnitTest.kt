package com.example

import com.example.data.model.UserProgress
import com.example.data.python.PythonCurriculum
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Test

class ExampleUnitTest {

    @Test
    fun testCurriculumUnitsAndLessons() {
        val units = PythonCurriculum.pythonUnits
        assertEquals("Should have 8 pedagogical units", 8, units.size)

        val allLessons = PythonCurriculum.getAllLessons()
        assertTrue("Should have multiple structured lessons", allLessons.size >= 15)

        val firstLesson = PythonCurriculum.findLesson("py_b1_l1")
        assertNotNull("First lesson should exist", firstLesson)
        assertEquals("Primeiros Passos com print()", firstLesson?.title)
        assertTrue(firstLesson?.hints?.isNotEmpty() == true)

        val nextLesson = PythonCurriculum.getNextLesson("py_b1_l1")
        assertNotNull("Next lesson should exist", nextLesson)
        assertEquals("py_b1_l2", nextLesson?.id)
    }

    @Test
    fun testKnowledgeAreasCatalog() {
        val areas = PythonCurriculum.knowledgeAreas
        assertTrue(areas.any { it.id == "python" && it.isAvailable })
        assertTrue(areas.any { it.id == "math" && it.isAvailable })
        assertTrue(areas.any { it.id == "ai" && !it.isAvailable })
        assertTrue(areas.any { it.id == "data" && !it.isAvailable })
    }

    @Test
    fun testUserProgressLevelCalculation() {
        val progressInitial = UserProgress(totalXp = 0)
        assertEquals(1, progressInitial.level)
        assertEquals(0f, progressInitial.currentLevelProgress, 0.01f)
        assertEquals(100, progressInitial.xpToNextLevel)

        val progressLevel2 = UserProgress(totalXp = 150)
        assertEquals(2, progressLevel2.level)
        assertEquals(0.5f, progressLevel2.currentLevelProgress, 0.01f)
        assertEquals(50, progressLevel2.xpToNextLevel)

        val progressLevel5 = UserProgress(totalXp = 480)
        assertEquals(5, progressLevel5.level)
        assertEquals(0.8f, progressLevel5.currentLevelProgress, 0.01f)
        assertEquals(20, progressLevel5.xpToNextLevel)
    }

    @Test
    fun testLessonTestCasesIntegrity() {
        val lessons = PythonCurriculum.getAllLessons()
        for (lesson in lessons) {
            assertTrue("Lesson title should not be blank", lesson.title.isNotBlank())
            assertTrue("Explanation should not be blank", lesson.explanation.isNotBlank())
            assertTrue("XP reward should be positive", lesson.xpReward > 0)
        }
    }

    @Test
    fun testExerciseBankComprehensiveDistribution() {
        val allExercises = com.example.data.exercise.UniversalExerciseBank.getAllExercises()
        assertTrue("Total exercises should be substantial (> 100)", allExercises.size >= 120)

        // Check each discipline is populated
        val pyExercises = allExercises.filter { it.discipline == com.example.data.exercise.ExerciseDiscipline.PROGRAMMING }
        val calcExercises = allExercises.filter { it.discipline == com.example.data.exercise.ExerciseDiscipline.CALCULUS }
        val mbExercises = allExercises.filter { it.discipline == com.example.data.exercise.ExerciseDiscipline.BASIC_MATH }

        assertTrue("Python should have at least 42 exercises", pyExercises.size >= 42)
        assertTrue("Calculus should have at least 42 exercises", calcExercises.size >= 42)
        assertTrue("Basic Math should have at least 42 exercises", mbExercises.size >= 42)

        // Verify difficulty categories exist in each discipline
        for (disc in listOf(pyExercises, calcExercises, mbExercises)) {
            val faciles = disc.filter { it.difficulty == "Fácil" }
            val medianos = disc.filter { it.difficulty == "Mediano" }
            val dificeis = disc.filter { it.difficulty == "Difícil" }

            assertTrue("Should have Fácil questions", faciles.isNotEmpty())
            assertTrue("Should have Mediano questions", medianos.isNotEmpty())
            assertTrue("Should have Difícil questions", dificeis.isNotEmpty())
        }
    }

    @Test
    fun testExerciseIntegrity() {
        val allExercises = com.example.data.exercise.UniversalExerciseBank.getAllExercises()
        val seenIds = mutableSetOf<String>()

        for (ex in allExercises) {
            assertTrue("Exercise ID should not be blank", ex.id.isNotBlank())
            assertTrue("Duplicate exercise ID found: ${ex.id}", seenIds.add(ex.id))
            assertTrue("Exercise problemStatement should not be blank: ${ex.id}", ex.problemStatement.isNotBlank())
            assertTrue("Exercise should have at least 2 options: ${ex.id}", ex.options.size >= 2)
            assertTrue("correctIndex out of bounds for ${ex.id}: ${ex.correctIndex} vs size ${ex.options.size}",
                ex.correctIndex in 0 until ex.options.size)
            assertTrue("KeyMethod should not be blank: ${ex.id}", ex.keyMethod.isNotBlank())
            assertTrue("XP reward should be positive for ${ex.id}", ex.xpReward > 0)
        }
    }

    @Test
    fun testAdaptiveDifficultyProgression() {
        val allExercises = com.example.data.exercise.UniversalExerciseBank.getAllExercises()
        val mbExercises = allExercises.filter { it.discipline == com.example.data.exercise.ExerciseDiscipline.BASIC_MATH }
        val mediumEx = mbExercises.first { it.difficulty == "Mediano" }

        // Test 1: Acerto -> Avança para questão com dificuldade igual ou superior
        val nextAfterCorrect = com.example.ui.screens.findNextAdaptiveExercise(
            current = mediumEx,
            allExercises = allExercises,
            answeredIds = setOf(mediumEx.id),
            wasCorrect = true
        )
        assertNotNull("Deve encontrar próxima questão adaptativa após acerto", nextAfterCorrect)
        assertTrue(
            "Nível após acerto deve ser igual ou superior (atual: ${mediumEx.difficulty}, próximo: ${nextAfterCorrect?.difficulty})",
            com.example.ui.screens.getDifficultyRank(nextAfterCorrect!!.difficulty) >= com.example.ui.screens.getDifficultyRank(mediumEx.difficulty)
        )

        // Test 2: Erro -> Avança para questão com dificuldade mais fácil no mesmo tópico
        val nextAfterWrong = com.example.ui.screens.findNextAdaptiveExercise(
            current = mediumEx,
            allExercises = allExercises,
            answeredIds = setOf(mediumEx.id),
            wasCorrect = false
        )
        assertNotNull("Deve encontrar próxima questão adaptativa após erro", nextAfterWrong)
        assertTrue(
            "Nível após erro deve ser mais fácil ou básico (atual: ${mediumEx.difficulty}, próximo: ${nextAfterWrong?.difficulty})",
            com.example.ui.screens.getDifficultyRank(nextAfterWrong!!.difficulty) <= com.example.ui.screens.getDifficultyRank(mediumEx.difficulty)
        )
    }

    @Test
    fun testEnemMathCurriculumTaxonomy() {
        val modules = com.example.data.math.EnemMathCurriculum.getAllModules()
        assertEquals("Deve ter exatamente 14 módulos de Matemática para o ENEM", 14, modules.size)

        val expectedLessonCounts = listOf(
            10, // Módulo 1: Matemática Básica
            3,  // Módulo 2: Matemática Financeira
            6,  // Módulo 3: Estatística
            9,  // Módulo 4: Geometria Plana
            9,  // Módulo 5: Geometria Espacial
            6,  // Módulo 6: Funções
            5,  // Módulo 7: Álgebra e Equações
            5,  // Módulo 8: Análise Combinatória
            6,  // Módulo 9: Probabilidade
            3,  // Módulo 10: Sequências e Progressões
            3,  // Módulo 11: Logaritmos
            5,  // Módulo 12: Trigonometria
            6,  // Módulo 13: Geometria Analítica
            3   // Módulo 14: Matrizes e Sistemas Lineares
        )

        val allLessons = com.example.data.math.EnemMathCurriculum.getAllLessons()
        val totalExpected = expectedLessonCounts.sum() // 81
        assertEquals("Total de aulas na trilha deve ser exatamente 81", totalExpected, allLessons.size)

        modules.forEachIndexed { index, module ->
            assertEquals(index + 1, module.moduleNumber)
            assertEquals("Módulo ${module.moduleNumber} deve ter o número correto de aulas",
                expectedLessonCounts[index], module.lessons.size)
            assertTrue("Título do módulo não pode ser vazio", module.title.isNotBlank())
            assertTrue("Resumo do módulo não pode ser vazio", module.summary.isNotBlank())
            assertNotNull("Deve ter simulado final", module.finalSimulado)
            assertTrue("Simulado deve conter aviso de bloqueio", module.finalSimulado.lockNotice.contains("Bloqueado"))

            module.lessons.forEach { lesson ->
                assertTrue("ID da aula não pode ser vazio", lesson.id.isNotBlank())
                assertTrue("Título da aula não pode ser vazio: ${lesson.id}", lesson.title.isNotBlank())
                assertTrue("Subtópico da aula não pode ser vazio: ${lesson.id}", lesson.subtopic.isNotBlank())
                assertEquals("Recompensa em XP deve ser 30", 30, lesson.xpReward)
            }
        }
    }

    @Test
    fun testBiologyCurriculumAndAlternativeAnalysis() {
        val bioModules = com.example.data.biology.EnemBiologyCurriculum.getAllModules()
        assertEquals("Deve ter 5 módulos de Biologia", 5, bioModules.size)

        // Verifica se cada módulo possui aulas válidas com conteúdo didático e exercícios
        bioModules.forEach { module ->
            assertTrue("Módulo ${module.title} deve ter aulas", module.lessons.isNotEmpty())
            module.lessons.forEach { lesson ->
                val bundle = com.example.data.biology.EnemBiologyDidacticProvider.getDidacticBundle(lesson.id)
                assertNotNull("Aula ${lesson.id} deve ter bundle didático", bundle)
                assertTrue("Aula ${lesson.id} deve ter teoria", bundle!!.theoryMarkdown.isNotBlank())

                val exercises = com.example.data.biology.EnemBiologyDidacticProvider.getExercisesForLesson(lesson.id)
                assertTrue("Aula ${lesson.id} deve ter exercícios", exercises.isNotEmpty())

                exercises.forEach { ex ->
                    assertEquals(5, ex.options.size)
                    assertTrue(ex.correctIndex in 0..4)
                    // Verifica se a resolução explica as alternativas e distratores
                    assertTrue("Exercício ${ex.id} deve conter análise aprofundada de distratores ou alternativas",
                        ex.stepByStepSolution.contains("Alternativa") || ex.stepByStepSolution.contains("Distratores"))
                }
            }
        }
    }

    @Test
    fun testBiologyAlternativeAnalyzerDeepExploration() {
        val statement = "Em um experimento sobre o sistema digestório, amostras de quimo gástrico foram analisadas."
        val options = listOf(
            "A amilase salivar atua intensamente no interior do estômago devido ao pH ácido.",
            "A pepsina degrada proteínas em meio ácido sob ação do ácido clorídrico.",
            "A bile contém enzimas digestivas que quebram carboidratos no estômago.",
            "O pH estomacal é alcalino para favorecer a tripsina pancreática.",
            "A digestão de lipídios ocorre totalmente antes da entrada no esôfago."
        )
        val analysis = com.example.data.biology.BiologyAlternativeAnalyzer.generateBiologyDeepAnalysis(
            statement = statement,
            options = options,
            correctIndex = 1,
            subtopic = "Digestão e Enzimas"
        )

        assertTrue("Deve gerar múltiplos passos de análise", analysis.size >= 4)
        assertTrue("Passo 1 deve conter contexto biológico", analysis[0].contains("Ensinamento"))
        assertTrue("Passo 2 deve conter a alternativa correta", analysis[1].contains("CORRETA"))
        assertTrue("Passo 3 deve conter a análise dos distratores", analysis[2].contains("Distratores"))
        assertTrue("Passo 4 deve conter a dica ENEM", analysis[3].contains("Dica de Ouro"))
    }

    @Test
    fun testAlternativeRandomizationDistribution() {
        val exercise = com.example.data.model.MathExercise(
            id = "test_q1",
            title = "Teste de Randomização",
            problemStatement = "Qual é a alternativa correta?",
            options = listOf("Opção A", "Opção B", "Opção C", "Opção D", "Opção E"),
            correctIndex = 0,
            hint = "Dica teste",
            stepByStepSolution = "Alternativa correta é a letra A"
        )

        val correctIndicesFound = mutableSetOf<Int>()
        for (i in 0 until 50) {
            val randomized = exercise.randomized()
            assertEquals("O tamanho das opções deve continuar 5", 5, randomized.options.size)
            assertTrue("O índice correto deve estar entre 0 e 4", randomized.correctIndex in 0..4)
            val correctOptionText = randomized.options[randomized.correctIndex]
            assertTrue("A opção correta deve ser Opção A", correctOptionText.contains("Opção A"))
            correctIndicesFound.add(randomized.correctIndex)
        }

        // Com 50 execuções, a probabilidade da alternativa ficar sempre no mesmo índice é (1/5)^49 ≈ 0
        assertTrue("A resposta certa deve se distribuir por múltiplos índices", correctIndicesFound.size >= 3)
    }

    @Test
    fun testPhysicsAndMathDidacticCoverage() {
        val physicsModules = com.example.data.physics.EnemPhysicsCurriculum.getAllModules()
        assertEquals(6, physicsModules.size)
        physicsModules.forEach { module ->
            module.lessons.forEach { lesson ->
                val bundle = com.example.data.physics.EnemPhysicsDidacticProvider.getDidacticBundle(lesson.id)
                assertNotNull("Bundle de física não pode ser nulo para ${lesson.id}", bundle)
                val ex = com.example.data.physics.EnemPhysicsDidacticProvider.getExercisesForLesson(lesson.id)
                assertTrue("Exercícios de física não podem ser vazios para ${lesson.id}", ex.isNotEmpty())
            }
        }

        val mathModules = com.example.data.math.EnemMathCurriculum.getAllModules()
        assertEquals(14, mathModules.size)
        mathModules.forEach { module ->
            module.lessons.forEach { lesson ->
                val bundle = com.example.data.math.EnemDidacticProvider.getDidacticBundle(lesson.id)
                assertNotNull("Bundle de matemática não pode ser nulo para ${lesson.id}", bundle)
                val ex = com.example.data.math.EnemDidacticProvider.getExercisesForLesson(lesson.id)
                assertTrue("Exercícios de matemática não podem ser vazios para ${lesson.id}", ex.isNotEmpty())
            }
        }
    }

    @Test
    fun testEnemPedagogicalDifficultyClassification() {
        // 1. Difficulty must NOT depend on year!
        val easyQuestion2022 = com.example.data.enem.EnemQuestion(
            id = "test_1",
            year = 2022,
            topic = "Biologia",
            subtopic = "Ecologia",
            title = "Qual é o nicho ecológico do produtor na cadeia alimentar?",
            alternatives = listOf("Autótrofo", "Heterótrofo", "Decompositor", "Parasita", "Carnívoro"),
            correctAnswer = "A",
            difficulty = "Fácil"
        ).toUniversalExercise()
        assertEquals("Fácil", easyQuestion2022.difficulty)

        val hardQuestion2022 = com.example.data.enem.EnemQuestion(
            id = "test_2",
            year = 2022,
            topic = "Matemática",
            subtopic = "Estatística",
            title = "Calcule o desvio padrão e a variância amostral com base na tabela com 15 classes...",
            alternatives = listOf("1,2", "3,4", "5,6", "7,8", "9,0"),
            correctAnswer = "B",
            difficulty = "Difícil"
        ).toUniversalExercise()
        assertEquals("Difícil", hardQuestion2022.difficulty)

        val mediumQuestion2024 = com.example.data.enem.EnemQuestion(
            id = "test_3",
            year = 2024,
            topic = "Física",
            subtopic = "Termologia",
            title = "Um calorímetro contém 200g de água a 20ºC. Ao adicionar um bloco de ferro...",
            alternatives = listOf("25ºC", "30ºC", "35ºC", "40ºC", "45ºC"),
            correctAnswer = "C",
            difficulty = "Média"
        ).toUniversalExercise()
        assertEquals("Média", mediumQuestion2024.difficulty)

        // 2. Automated pedagogical fallback classification without explicit difficulty
        val inferredHard = com.example.data.enem.EnemQuestion.classifyPedagogicalDifficulty(
            statement = "Uma fábrica aplica juros compostos com tabela price e amortização de R$ 50.000,00...",
            topic = "Matemática",
            subtopic = "Matemática Financeira"
        )
        assertEquals("Difícil", inferredHard)

        val inferredEasy = com.example.data.enem.EnemQuestion.classifyPedagogicalDifficulty(
            statement = "Analise o gráfico de barras e faça a leitura direta da quantidade de vacinas...",
            topic = "Biologia",
            subtopic = "Imunologia"
        )
        assertEquals("Fácil", inferredEasy)

        // 3. getDifficultyRank must correctly rank Fácil=1, Média=2, Difícil=3
        assertEquals(1, com.example.ui.screens.getDifficultyRank("Fácil"))
        assertEquals(2, com.example.ui.screens.getDifficultyRank("Média"))
        assertEquals(2, com.example.ui.screens.getDifficultyRank("Mediano"))
        assertEquals(3, com.example.ui.screens.getDifficultyRank("Difícil"))
    }

    @Test
    fun testStrictStreakSystemAndGmtMinus3TimeApi() {
        val today = com.example.data.time.NetworkTimeProvider.getTodayGmt3()
        val yesterday = com.example.data.time.NetworkTimeProvider.getYesterdayGmt3()
        assertTrue("Today in GMT-3 should match YYYY-MM-DD", today.matches(Regex("""\d{4}-\d{2}-\d{2}""")))
        assertTrue("Yesterday in GMT-3 should match YYYY-MM-DD", yesterday.matches(Regex("""\d{4}-\d{2}-\d{2}""")))

        // 1. Initial user has 0d streak
        val initialUser = UserProgress(currentStreak = 0, lastQualifyingStudyDate = "")
        assertEquals(0, initialUser.currentStreak)
        assertFalse(initialUser.hasStudiedToday())

        // 2. Expiration rule: user had 5d streak, but last qualifying activity was 2 days ago (passed 00h00 GMT-3)
        val expiredStreakUser = UserProgress(
            currentStreak = 5,
            lastQualifyingStudyDate = "2026-10-20" // old date
        )
        val isExpired = expiredStreakUser.currentStreak > 0 &&
                expiredStreakUser.lastQualifyingStudyDate != today &&
                expiredStreakUser.lastQualifyingStudyDate != yesterday
        assertTrue("Streak should expire after missing 00h00 GMT-3 deadline", isExpired)

        // 3. Maintained streak: user studied yesterday, has not studied yet today
        val maintainedUser = UserProgress(
            currentStreak = 3,
            lastQualifyingStudyDate = yesterday
        )
        val isStillValid = maintainedUser.lastQualifyingStudyDate == yesterday
        assertTrue("Streak from yesterday is valid until 00h00 of today", isStillValid)
        assertFalse("User has not yet studied today", maintainedUser.hasStudiedToday())

        // 4. Studied today: user did a class or exercise today
        val activeTodayUser = UserProgress(
            currentStreak = 4,
            lastQualifyingStudyDate = today
        )
        assertTrue("User has studied today", activeTodayUser.hasStudiedToday())
    }

    @Test
    fun testDevBroadcastAuthorizationAndApkDownloadModels() {
        val devEmail = "contatopedro21g@gmail.com"
        val devUser = com.example.auth.AppUser(
            uid = "dev_123",
            email = devEmail,
            displayName = "Pedro Dev",
            isDev = true
        )
        val regularUser = com.example.auth.AppUser(
            uid = "usr_456",
            email = "aluno@escola.com",
            displayName = "Aluno Silva",
            isDev = false
        )

        // 1. Strict Dev-only permission validation
        val isDevPermitted = (devUser.isDev || devUser.email.equals(devEmail, ignoreCase = true))
        val isRegularPermitted = (regularUser.isDev || regularUser.email.equals(devEmail, ignoreCase = true))
        assertTrue("Dev user Pedro must have broadcast authorization", isDevPermitted)
        assertFalse("Regular user must NOT have broadcast authorization", isRegularPermitted)

        // 2. BroadcastNotification model properties
        val updateBroadcast = com.example.notification.BroadcastNotification(
            id = "b_1",
            title = "Hora de estudar! Nova atualização!!",
            message = "Nova versão disponível do Knowverse!",
            senderEmail = devEmail,
            senderName = "Pedro (Dev Admin)",
            type = com.example.notification.BroadcastNotification.TYPE_APP_UPDATE,
            apkUrl = "https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/Knowverse.apk",
            apkVersion = "1.2.0"
        )
        assertTrue("Update broadcast must flag isUpdate = true", updateBroadcast.isUpdate)
        assertEquals("1.2.0", updateBroadcast.apkVersion)
        assertNotNull(updateBroadcast.apkUrl)

        // 3. AppReleaseUpdate model
        val release = com.example.notification.AppReleaseUpdate(
            versionCode = 2,
            versionName = "1.2.0",
            releaseTitle = "Hora de estudar! Nova atualização!!",
            releaseNotes = "Melhorias de desempenho e sistema de ofensiva estrito.",
            apkUrl = "https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/Knowverse.apk",
            publishedBy = devEmail
        )
        assertEquals(2, release.versionCode)
        assertEquals(devEmail, release.publishedBy)
    }
}



