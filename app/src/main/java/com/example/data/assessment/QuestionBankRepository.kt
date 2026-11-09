package com.example.data.assessment

import android.content.Context
import com.example.data.exercise.ExerciseDiscipline
import com.example.data.exercise.UniversalExercise
import com.example.data.exercise.UniversalExerciseBank
import java.io.File
import kotlin.math.roundToInt
import kotlin.random.Random

enum class SimuladoMode(val label: String, val description: String) {
    BY_SUBJECT(
        "Por Matéria (Geral)",
        "Sorteia questões aleatórias de toda a disciplina escolhida, equilibrando dificuldade e formatos."
    ),
    BY_MODULE(
        "Avaliação de Módulo",
        "Mistura conteúdos de todas as aulas de um módulo específico já estudado."
    ),
    CUMULATIVE(
        "Avaliação Cumulativa",
        "Exige recuperação ativa de módulos e aulas anteriores até o ponto selecionado na trilha."
    ),
    BY_SUBTOPIC(
        "Por Subtópico",
        "Filtra especificamente pelo subtópico da aula (ex.: Eq. do 2º Grau, Citologia, Cinemática)."
    )
}

data class ModuleSummary(
    val moduleId: String,
    val moduleTitle: String,
    val subject: String,
    val lessonCount: Int,
    val minOrder: Int,
    val maxOrder: Int
)

data class SimuladoConfig(
    val subject: String = "Todas",
    val mode: SimuladoMode = SimuladoMode.BY_SUBJECT,
    val moduleId: String? = null,
    val subtopic: String? = null,
    val questionCount: Int = 15,
    val allowedFormats: Set<QuestionFormat> = emptySet(),
    val includeLegacy: Boolean = true
)

data class QuarantinedQuestion(
    val questionId: String,
    val discipline: String,
    val module: String,
    val subtopic: String,
    val reason: String,
    val timestamp: Long = System.currentTimeMillis(),
    val origin: String = "UniversalExerciseBank"
)

/**
 * Repositório unificado de questões e gerador de simulados (Etapa 5).
 * Carrega os 6 bancos JSON validados (1.930 questões distribuídas nas 193 aulas)
 * e preserva todos os exercícios legados do aplicativo devidamente associados.
 */
object QuestionBankRepository {

    const val MAX_SIMULADO_QUESTIONS = 45

    private val bankFiles = listOf(
        "assessment/q_enem_math.json",
        "assessment/q_enem_bio.json",
        "assessment/q_enem_fis.json",
        "assessment/q_python.json",
        "assessment/q_basic_math.json",
        "assessment/q_calculus.json"
    )

    @Volatile
    private var cachedLessons: List<LessonRef>? = null

    @Volatile
    private var cachedAuthoredQuestions: List<BankQuestion>? = null

    @Volatile
    private var cachedLegacyQuestions: List<BankQuestion>? = null

    @Volatile
    private var cachedTranspetroLessons: List<LessonRef>? = null

    @Volatile
    private var cachedTranspetroQuestions: List<BankQuestion>? = null

    private val _quarantinedQuestions = mutableListOf<QuarantinedQuestion>()

    fun getQuarantinedQuestions(): List<QuarantinedQuestion> = synchronized(this) {
        _quarantinedQuestions.toList()
    }

    fun getQuarantineCount(): Int = synchronized(this) {
        _quarantinedQuestions.size
    }

    private fun readAssetText(context: Context?, relativePath: String): String {
        if (context != null) {
            return context.assets.open(relativePath).bufferedReader(Charsets.UTF_8).use { it.readText() }
        }
        val candidates = listOf(
            File("app/src/main/assets/$relativePath"),
            File("src/main/assets/$relativePath"),
            File("../app/src/main/assets/$relativePath")
        )
        val file = candidates.firstOrNull { it.exists() }
            ?: error("Arquivo de asset não encontrado: $relativePath")
        return file.readText(Charsets.UTF_8)
    }

    @Synchronized
    fun ensureLoaded(context: Context? = null) {
        if (cachedLessons != null && cachedAuthoredQuestions != null) return

        val lessonsText = readAssetText(context, "assessment/lesson_index.json")
        val lessons = AssessmentJson.parseLessonIndex(lessonsText).sortedBy { it.order }
        val lessonById = lessons.associateBy { it.lessonId }

        _quarantinedQuestions.clear()
        val authored = mutableListOf<BankQuestion>()
        val seenIds = mutableSetOf<String>()

        for (path in bankFiles) {
            val raw = readAssetText(context, path)
            val parsed = AssessmentJson.parseQuestions(raw)
            for (q in parsed) {
                val lesson = lessonById[q.lessonId]
                if (lesson == null) {
                    _quarantinedQuestions.add(
                        QuarantinedQuestion(
                            questionId = q.id,
                            discipline = "Desconhecida",
                            module = q.lessonId,
                            subtopic = q.concept,
                            reason = "Aula '${q.lessonId}' não encontrada em lesson_index.json",
                            origin = path
                        )
                    )
                    continue
                }
                if (!seenIds.add(q.id)) {
                    _quarantinedQuestions.add(
                        QuarantinedQuestion(
                            questionId = q.id,
                            discipline = lesson.subject,
                            module = lesson.moduleTitle,
                            subtopic = lesson.subtopic,
                            reason = "Identificador de questão duplicado: '${q.id}'",
                            origin = path
                        )
                    )
                    continue
                }
                val validationError = validateQuestionIntegrity(q)
                if (validationError != null) {
                    _quarantinedQuestions.add(
                        QuarantinedQuestion(
                            questionId = q.id,
                            discipline = lesson.subject,
                            module = lesson.moduleTitle,
                            subtopic = lesson.subtopic,
                            reason = validationError,
                            origin = path
                        )
                    )
                    continue
                }
                authored.add(BankQuestion(question = q, lesson = lesson))
            }
        }

        cachedLessons = lessons
        cachedAuthoredQuestions = authored
        cachedLegacyQuestions = buildLegacyBridge(lessons)

        // Carrega questões e aulas do Concurso Transpetro em cache dedicado
        try {
            cachedTranspetroLessons = com.example.data.transpetro.TranspetroQuestionBankLoader.getAllLessonRefs()
            cachedTranspetroQuestions = com.example.data.transpetro.TranspetroQuestionBankLoader.getAllBankQuestions(context)
        } catch (_: Exception) {
            // Em testes unitários headless ou sem asset local disponível
        }
    }

    /**
     * Valida a integridade estrutural e pedagógica de uma questão.
     * Retorna `null` se a questão for válida, ou o motivo da rejeição para quarentena.
     */
    fun validateQuestionIntegrity(q: Question): String? {
        if (q.id.isBlank()) return "ID da questão em branco"
        if (q.statement.isBlank()) return "Enunciado em branco"
        if (q.explanation.isBlank()) return "Explicação pedagógica ausente"

        return when (q.format) {
            QuestionFormat.MULTIPLE_CHOICE, QuestionFormat.CHART -> {
                if (q.options.size < 2) {
                    "Questão de múltipla escolha com menos de 2 alternativas (${q.options.size})"
                } else if (q.options.any { it.isBlank() }) {
                    "Questão possui alternativa em branco"
                } else if (q.options.map { it.trim().lowercase() }.distinct().size != q.options.size) {
                    "Questão possui alternativas duplicadas"
                } else if (q.answerIndex !in q.options.indices) {
                    "Gabarito answerIndex (${q.answerIndex}) fora do intervalo [0..${q.options.size - 1}]"
                } else if (q.format == QuestionFormat.CHART && q.chart == null) {
                    "Questão de interpretação gráfica sem dados de gráfico (chart)"
                } else {
                    null
                }
            }
            QuestionFormat.TRUE_FALSE -> {
                if (q.answerBool == null) "Questão V/F sem gabarito booleano (answerBool)" else null
            }
            QuestionFormat.MATCHING -> {
                if (q.pairs.size < 2 || q.pairs.any { it.first.isBlank() || it.second.isBlank() }) {
                    "Pares de associação inválidos (${q.pairs.size})"
                } else {
                    null
                }
            }
            QuestionFormat.FILL_BLANK -> {
                if (q.answers.isEmpty() || q.answers.any { it.isBlank() }) {
                    "Questão de lacuna sem respostas válidas (answers)"
                } else {
                    null
                }
            }
            QuestionFormat.NUMERIC -> {
                if (q.answerNumber == null || !q.answerNumber.isFinite()) {
                    "Questão numérica sem valor numérico finito (answerNumber)"
                } else {
                    null
                }
            }
            QuestionFormat.ESSAY -> {
                if (q.rubric.isEmpty()) {
                    "Questão discursiva sem rubrica explícita"
                } else {
                    null
                }
            }
            QuestionFormat.CODE -> {
                if (q.code == null || q.code.tests.isEmpty()) {
                    "Questão de programação prática sem casos de teste automatizados"
                } else {
                    null
                }
            }
        }
    }

    private fun buildLegacyBridge(lessons: List<LessonRef>): List<BankQuestion> {
        val bySubject = lessons.groupBy { it.subject }
        val results = mutableListOf<BankQuestion>()

        val allExercises = try {
            UniversalExerciseBank.getAllExercises()
        } catch (e: Exception) {
            android.util.Log.e("QuestionBankRepository", "Erro crítico ao carregar exercícios legados", e)
            return emptyList()
        }

        for (ex in allExercises) {
            val subj = mapDisciplineToSubject(ex.discipline, ex.category)
            val subjectLessons = bySubject[subj].orEmpty()
            if (subjectLessons.isEmpty()) {
                _quarantinedQuestions.add(
                    QuarantinedQuestion(
                        questionId = ex.id,
                        discipline = ex.discipline.name,
                        module = ex.category,
                        subtopic = ex.subtopic,
                        reason = "Disciplina '$subj' não possui aulas cadastradas no índice de aulas",
                        origin = "UniversalExerciseBank:${ex.discipline.name}"
                    )
                )
                continue
            }

            if (ex.problemStatement.isBlank() || ex.options.size < 2 || ex.correctIndex !in ex.options.indices) {
                _quarantinedQuestions.add(
                    QuarantinedQuestion(
                        questionId = ex.id,
                        discipline = ex.discipline.name,
                        module = ex.category,
                        subtopic = ex.subtopic,
                        reason = " Questão legada estruturalmente inválida (options=${ex.options.size}, correctIndex=${ex.correctIndex})".trim(),
                        origin = "UniversalExerciseBank:${ex.discipline.name}"
                    )
                )
                continue
            }

            if (ex.options.map { it.trim().lowercase() }.distinct().size != ex.options.size) {
                _quarantinedQuestions.add(
                    QuarantinedQuestion(
                        questionId = ex.id,
                        discipline = ex.discipline.name,
                        module = ex.category,
                        subtopic = ex.subtopic,
                        reason = "Questão legada possui alternativas duplicadas",
                        origin = "UniversalExerciseBank:${ex.discipline.name}"
                    )
                )
                continue
            }

            // Associação estrita: correspondência exata ou substantiva sem recorrer a fallback arbitrário
            val matchedLesson = subjectLessons.firstOrNull { l ->
                l.subtopic.equals(ex.subtopic, ignoreCase = true) ||
                    (ex.subtopic.length >= 4 && l.subtopic.contains(ex.subtopic, ignoreCase = true)) ||
                    (l.subtopic.length >= 4 && ex.subtopic.contains(l.subtopic, ignoreCase = true)) ||
                    (ex.subtopic.length >= 4 && l.title.contains(ex.subtopic, ignoreCase = true))
            }

            if (matchedLesson == null) {
                _quarantinedQuestions.add(
                    QuarantinedQuestion(
                        questionId = ex.id,
                        discipline = ex.discipline.name,
                        module = ex.category,
                        subtopic = ex.subtopic,
                        reason = "Subtópico '${ex.subtopic}' da disciplina '$subj' não corresponde a nenhuma aula mapeada",
                        origin = "UniversalExerciseBank:${ex.discipline.name}"
                    )
                )
                // Não adiciona aos simulados: preserva integridade pedagógica
                continue
            }

            val diff = when {
                ex.difficulty.contains("Fácil", ignoreCase = true) ||
                    ex.difficulty.contains("Básico", ignoreCase = true) -> QDifficulty.EASY
                ex.difficulty.contains("Difícil", ignoreCase = true) ||
                    ex.difficulty.contains("Desafiador", ignoreCase = true) -> QDifficulty.HARD
                else -> QDifficulty.MEDIUM
            }

            val isOfficialEnem = ex.discipline == ExerciseDiscipline.ENEM ||
                ex.source.contains("INEP", ignoreCase = true) ||
                ex.source.startsWith("ENEM 20", ignoreCase = true)

            val explanationText = ex.solutionSteps.joinToString("\n").ifBlank {
                "Aplique o método '${ex.keyMethod}' aos dados do enunciado para chegar à alternativa correta."
            }

            val distractorList = ex.options.indices.map { idx ->
                if (idx == ex.correctIndex) ""
                else ex.pedagogicalAlert.ifBlank { "Revisar aplicação de ${ex.keyMethod} em ${ex.subtopic}" }
            }

            val q = Question(
                id = "legacy_${ex.id}",
                lessonId = matchedLesson.lessonId,
                format = QuestionFormat.MULTIPLE_CHOICE,
                difficulty = diff,
                skill = ex.keyMethod.ifBlank { "Resolver problema de ${ex.subtopic}" },
                concept = ex.subtopic.ifBlank { matchedLesson.subtopic },
                statement = ex.problemStatement,
                explanation = explanationText,
                retryGuidance = ex.pedagogicalAlert.ifBlank {
                    "Revise o método '${ex.keyMethod}' na aula '${matchedLesson.title}' e refaça o cálculo."
                },
                source = if (isOfficialEnem) QuestionSource.INEP_ENEM else QuestionSource.LEGACY,
                options = ex.options,
                answerIndex = ex.correctIndex,
                distractorConcepts = distractorList
            )
            results.add(BankQuestion(question = q, lesson = matchedLesson))
        }

        return results
    }

    private fun mapDisciplineToSubject(discipline: ExerciseDiscipline, category: String): String {
        return when (discipline) {
            ExerciseDiscipline.PROGRAMMING -> "Python"
            ExerciseDiscipline.CALCULUS -> "Cálculo I"
            ExerciseDiscipline.BASIC_MATH -> "Matemática Básica"
            ExerciseDiscipline.BIOLOGY -> "Biologia"
            ExerciseDiscipline.PHYSICS -> "Física"
            else -> when {
                category.contains("Biologia", ignoreCase = true) -> "Biologia"
                category.contains("Física", ignoreCase = true) -> "Física"
                category.contains("Python", ignoreCase = true) -> "Python"
                category.contains("Cálculo", ignoreCase = true) -> "Cálculo I"
                else -> "Matemática"
            }
        }
    }

    fun getAllLessons(context: Context? = null): List<LessonRef> {
        ensureLoaded(context)
        return cachedLessons.orEmpty()
    }

    fun getAuthoredQuestions(context: Context? = null): List<BankQuestion> {
        ensureLoaded(context)
        return cachedAuthoredQuestions.orEmpty()
    }

    fun getAllQuestions(
        context: Context? = null,
        includeLegacy: Boolean = true,
        includeTranspetro: Boolean = false
    ): List<BankQuestion> {
        ensureLoaded(context)
        val authored = cachedAuthoredQuestions.orEmpty()
        val legacy = if (includeLegacy) cachedLegacyQuestions.orEmpty() else emptyList()
        val transpetro = if (includeTranspetro) getTranspetroQuestions(context) else emptyList()
        return authored + legacy + transpetro
    }

    fun getTranspetroLessons(): List<LessonRef> {
        return cachedTranspetroLessons ?: com.example.data.transpetro.TranspetroQuestionBankLoader.getAllLessonRefs()
    }

    fun getTranspetroQuestions(context: Context? = null): List<BankQuestion> {
        ensureLoaded(context)
        return cachedTranspetroQuestions ?: com.example.data.transpetro.TranspetroQuestionBankLoader.getAllBankQuestions(context)
    }

    fun getSubjects(context: Context? = null): List<String> {
        ensureLoaded(context)
        return cachedLessons.orEmpty().map { it.subject }.distinct()
    }

    fun getModulesForSubject(subject: String, context: Context? = null): List<ModuleSummary> {
        ensureLoaded(context)
        val filtered = if (subject.isBlank() || subject.equals("Todas", ignoreCase = true)) {
            cachedLessons.orEmpty()
        } else {
            cachedLessons.orEmpty().filter { it.subject.equals(subject, ignoreCase = true) }
        }
        return filtered
            .groupBy { it.moduleId }
            .map { (modId, list) ->
                val first = list.first()
                ModuleSummary(
                    moduleId = modId,
                    moduleTitle = first.moduleTitle,
                    subject = first.subject,
                    lessonCount = list.size,
                    minOrder = list.minOf { it.order },
                    maxOrder = list.maxOf { it.order }
                )
            }
            .sortedBy { it.minOrder }
    }

    fun getSubtopicsForSubject(
        subject: String,
        moduleId: String? = null,
        context: Context? = null
    ): List<String> {
        ensureLoaded(context)
        return cachedLessons.orEmpty()
            .filter {
                (subject.isBlank() || subject.equals("Todas", ignoreCase = true) || it.subject.equals(subject, ignoreCase = true)) &&
                    (moduleId.isNullOrBlank() || moduleId == "all" || it.moduleId == moduleId)
            }
            .map { it.subtopic }
            .distinct()
    }

    fun getQuestionsForLesson(
        lessonId: String,
        includeLegacy: Boolean = true,
        context: Context? = null
    ): List<BankQuestion> {
        if (lessonId.startsWith("tp_")) {
            return getTranspetroQuestions(context).filter { it.lesson.lessonId == lessonId }
        }
        return getAllQuestions(context, includeLegacy).filter { it.lesson.lessonId == lessonId }
    }

    fun filterQuestions(
        subject: String = "Todas",
        moduleId: String? = null,
        subtopic: String? = null,
        lessonId: String? = null,
        format: QuestionFormat? = null,
        difficulty: QDifficulty? = null,
        query: String = "",
        includeLegacy: Boolean = true,
        context: Context? = null
    ): List<BankQuestion> {
        val q = query.trim().lowercase()
        return getAllQuestions(context, includeLegacy).filter { item ->
            val matchSubject = subject.isBlank() ||
                subject.equals("Todas", ignoreCase = true) ||
                subject.equals("all", ignoreCase = true) ||
                item.subject.equals(subject, ignoreCase = true)

            val matchModule = moduleId.isNullOrBlank() ||
                moduleId.equals("all", ignoreCase = true) ||
                item.lesson.moduleId.equals(moduleId, ignoreCase = true)

            val matchSubtopic = subtopic.isNullOrBlank() ||
                subtopic.equals("all", ignoreCase = true) ||
                subtopic.equals("Todos", ignoreCase = true) ||
                item.lesson.subtopic.equals(subtopic, ignoreCase = true) ||
                item.lesson.subtopic.contains(subtopic, ignoreCase = true) ||
                item.question.concept.contains(subtopic, ignoreCase = true)

            val matchLesson = lessonId.isNullOrBlank() ||
                lessonId.equals("all", ignoreCase = true) ||
                item.lesson.lessonId.equals(lessonId, ignoreCase = true)

            val matchFormat = format == null || item.question.format == format
            val matchDifficulty = difficulty == null || item.question.difficulty == difficulty

            val matchQuery = if (q.isBlank()) {
                true
            } else {
                item.id.lowercase().contains(q) ||
                    item.question.statement.lowercase().contains(q) ||
                    item.question.concept.lowercase().contains(q) ||
                    item.question.skill.lowercase().contains(q) ||
                    item.lesson.title.lowercase().contains(q) ||
                    item.lesson.subtopic.lowercase().contains(q) ||
                    item.lesson.moduleTitle.lowercase().contains(q)
            }

            matchSubject && matchModule && matchSubtopic && matchLesson && matchFormat && matchDifficulty && matchQuery
        }
    }

    /**
     * Gera um simulado aleatório (máx. 45 questões) respeitando a modalidade:
     * - BY_SUBJECT: amostra toda a matéria selecionada (ou todas as matérias).
     * - BY_MODULE: mistura as aulas do módulo selecionado.
     * - CUMULATIVE: mistura conteúdos desde o 1º módulo da disciplina até o módulo selecionado,
     *   exigindo recuperação ativa de módulos e aulas anteriores.
     * - BY_SUBTOPIC: foca no subtópico escolhido.
     */
    fun generateSimulado(
        config: SimuladoConfig,
        context: Context? = null,
        random: Random = Random.Default
    ): List<BankQuestion> {
        ensureLoaded(context)
        val targetCount = config.questionCount.coerceIn(1, MAX_SIMULADO_QUESTIONS)
        val basePool = getAllQuestions(context, config.includeLegacy).filter { bq ->
            val matchSubject = config.subject.isBlank() ||
                config.subject.equals("Todas", ignoreCase = true) ||
                bq.subject.equals(config.subject, ignoreCase = true)
            val matchFormat = config.allowedFormats.isEmpty() || bq.question.format in config.allowedFormats
            matchSubject && matchFormat
        }

        if (basePool.isEmpty()) return emptyList()

        val candidatePool: List<BankQuestion> = when (config.mode) {
            SimuladoMode.BY_SUBJECT -> basePool

            SimuladoMode.BY_MODULE -> {
                val modId = config.moduleId
                if (modId.isNullOrBlank()) basePool
                else basePool.filter { it.lesson.moduleId == modId }.ifEmpty { basePool }
            }

            SimuladoMode.CUMULATIVE -> {
                val modules = getModulesForSubject(config.subject, context)
                val targetMod = modules.firstOrNull { it.moduleId == config.moduleId } ?: modules.lastOrNull()
                if (targetMod == null) {
                    basePool
                } else {
                    val allowedOrders = 0..targetMod.maxOrder
                    val cumulativeItems = basePool.filter {
                        it.lesson.subject.equals(targetMod.subject, ignoreCase = true) &&
                            it.lesson.order in allowedOrders
                    }.ifEmpty { basePool }

                    // Garante mistura real entre o módulo atual e módulos/aulas anteriores
                    val currentLessons = cumulativeItems.filter { it.lesson.moduleId == targetMod.moduleId }
                    val previousLessons = cumulativeItems.filter { it.lesson.moduleId != targetMod.moduleId }
                    if (previousLessons.isNotEmpty() && currentLessons.isNotEmpty()) {
                        val prevTarget = (targetCount * 0.55).roundToInt().coerceAtLeast(1)
                        val currTarget = (targetCount - prevTarget).coerceAtLeast(1)
                        val pickedPrev = sampleBalancedByDifficulty(previousLessons, prevTarget, random)
                        val pickedCurr = sampleBalancedByDifficulty(currentLessons, currTarget, random)
                        val combined = (pickedPrev + pickedCurr).distinctBy { it.id }
                        if (combined.size >= targetCount) {
                            return combined.shuffled(random).take(targetCount)
                        }
                    }
                    cumulativeItems
                }
            }

            SimuladoMode.BY_SUBTOPIC -> {
                val sub = config.subtopic
                if (sub.isNullOrBlank()) basePool
                else basePool.filter {
                    it.lesson.subtopic.equals(sub, ignoreCase = true) ||
                        it.lesson.subtopic.contains(sub, ignoreCase = true) ||
                        it.question.concept.contains(sub, ignoreCase = true)
                }.ifEmpty { basePool }
            }
        }

        return sampleBalancedByDifficulty(candidatePool, targetCount, random)
    }

    /**
     * Seleciona questões respeitando as proporções orientativas (30% Fácil, 45% Média, 25% Difícil)
     * e diversificando as aulas (`lessonId`) e formatos (`QuestionFormat`).
     */
    private fun sampleBalancedByDifficulty(
        pool: List<BankQuestion>,
        count: Int,
        random: Random
    ): List<BankQuestion> {
        if (pool.size <= count) return pool.shuffled(random)

        val easyTarget = (count * 0.30).roundToInt()
        val hardTarget = (count * 0.25).roundToInt()
        val mediumTarget = (count - easyTarget - hardTarget).coerceAtLeast(0)

        val easyPool = diversifyByLessonAndFormat(pool.filter { it.question.difficulty == QDifficulty.EASY }, random)
        val mediumPool = diversifyByLessonAndFormat(pool.filter { it.question.difficulty == QDifficulty.MEDIUM }, random)
        val hardPool = diversifyByLessonAndFormat(pool.filter { it.question.difficulty == QDifficulty.HARD }, random)

        val selected = LinkedHashMap<String, BankQuestion>()
        easyPool.take(easyTarget).forEach { selected[it.id] = it }
        mediumPool.take(mediumTarget).forEach { selected[it.id] = it }
        hardPool.take(hardTarget).forEach { selected[it.id] = it }

        if (selected.size < count) {
            for (item in diversifyByLessonAndFormat(pool, random)) {
                if (selected.size >= count) break
                selected.putIfAbsent(item.id, item)
            }
        }

        return selected.values.toList().shuffled(random).take(count)
    }

    private fun diversifyByLessonAndFormat(
        items: List<BankQuestion>,
        random: Random
    ): List<BankQuestion> {
        if (items.isEmpty()) return emptyList()
        val byLesson = items.shuffled(random).groupBy { it.lesson.lessonId }.mapValues { it.value.toMutableList() }
        val lessonKeys = byLesson.keys.toList().shuffled(random)
        val result = mutableListOf<BankQuestion>()
        var added = true
        while (added) {
            added = false
            for (key in lessonKeys) {
                val bucket = byLesson[key]
                if (!bucket.isNullOrEmpty()) {
                    result.add(bucket.removeAt(0))
                    added = true
                }
            }
        }
        return result
    }
}

