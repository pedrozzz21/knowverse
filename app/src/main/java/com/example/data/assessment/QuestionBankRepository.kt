package com.example.data.assessment

import android.content.Context
import com.example.data.exercise.ExerciseDiscipline
import com.example.data.exercise.UniversalExercise
import com.example.data.exercise.UniversalExerciseBank
import java.io.File
import kotlin.math.roundToInt
import kotlin.random.Random

enum class SpecificExamPreset(
    val id: String,
    val title: String,
    val badge: String,
    val banca: String,
    val examFormatLabel: String,
    val subtitle: String,
    val compositionSummary: String,
    val defaultQuestionCount: Int,
    val maxQuestionCount: Int
) {
    CUSTOM(
        id = "custom",
        title = "Personalizado",
        badge = "Livre",
        banca = "Knowverse Adaptativo",
        examFormatLabel = "Multiformato (8 formatos)",
        subtitle = "Escolha disciplina, módulo, avaliação cumulativa ou subtópico",
        compositionSummary = "Sorteio adaptativo conforme matéria e modalidade selecionadas (1 a 45 questões)",
        defaultQuestionCount = 15,
        maxQuestionCount = 45
    ),
    ENEM_DIA_1(
        id = "enem_dia_1",
        title = "ENEM • Dia 1",
        badge = "Banca INEP • 90 Questões",
        banca = "INEP / MEC",
        examFormatLabel = "Múltipla Escolha Oficial (A–E)",
        subtitle = "Linguagens, Códigos e suas Tecnologias (45q) + Ciências Humanas: História, Geografia, Filosofia e Sociologia (45q)",
        compositionSummary = "Proporção Oficial: 50% Linguagens (45q) + 50% Humanas: 15 História, 15 Geografia, 8 Filosofia e 7 Sociologia (45q)",
        defaultQuestionCount = 90,
        maxQuestionCount = 90
    ),
    ENEM_DIA_2(
        id = "enem_dia_2",
        title = "ENEM • Dia 2",
        badge = "Banca INEP • 90 Questões",
        banca = "INEP / MEC",
        examFormatLabel = "Múltipla Escolha Oficial (A–E)",
        subtitle = "Ciências da Natureza (45q) + Matemática e suas Tecnologias (45q)",
        compositionSummary = "Proporção Oficial: 50% Natureza: 15 Biologia, 15 Física e 15 Química (45q) + 50% Matemática (45q)",
        defaultQuestionCount = 90,
        maxQuestionCount = 90
    ),
    TRANSPETRO(
        id = "transpetro",
        title = "Transpetro",
        badge = "Banca Cesgranrio • 60 Questões",
        banca = "Fundação Cesgranrio",
        examFormatLabel = "Múltipla Escolha Oficial (A–E)",
        subtitle = "Bloco I: Conhecimentos Básicos (20q) + Bloco II: Conhecimentos Específicos (40q)",
        compositionSummary = "Proporção Oficial: 33,3% Básicos (10 Português + 10 Matemática) + 66,7% Específicos (10 ADM, 10 Finanças, 10 Logística, 10 Informática)",
        defaultQuestionCount = 60,
        maxQuestionCount = 60
    )
}

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
    val includeLegacy: Boolean = true,
    val preset: SpecificExamPreset = SpecificExamPreset.CUSTOM
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

    @Volatile
    private var cachedEnemExtraLessons: List<LessonRef>? = null

    @Volatile
    private var cachedEnemExtraQuestions: List<BankQuestion>? = null

    @Volatile
    private var lastSimuladoQuestionIds: Set<String> = emptySet()

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

        // Carrega disciplinas complementares do ENEM Dia 1 e Dia 2 (Linguagens, História, Geografia e Química)
        try {
            val extraPair = buildEnemExtraCurriculaBridge(context)
            cachedEnemExtraLessons = extraPair.first
            cachedEnemExtraQuestions = extraPair.second
        } catch (_: Exception) {
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

    private fun buildEnemExtraCurriculaBridge(context: Context?): Pair<List<LessonRef>, List<BankQuestion>> {
        val lessons = mutableListOf<LessonRef>()
        val questions = mutableListOf<BankQuestion>()
        var globalOrder = 1000

        fun registerDiscipline(
            subjectName: String,
            modules: List<com.example.data.model.EnemTrailModule>,
            exerciseFetcher: (String) -> List<com.example.data.model.MathExercise>
        ) {
            for (mod in modules) {
                for (lesson in mod.lessons) {
                    val ref = LessonRef(
                        lessonId = lesson.id,
                        subject = subjectName,
                        moduleId = mod.id,
                        moduleTitle = mod.title,
                        title = lesson.title,
                        subtopic = lesson.subtopic,
                        order = globalOrder++
                    )
                    lessons.add(ref)
                    val exList = exerciseFetcher(lesson.id)
                    exList.forEachIndexed { idx, ex ->
                        val diff = when {
                            idx == 0 -> QDifficulty.EASY
                            idx >= exList.size - 1 -> QDifficulty.HARD
                            else -> QDifficulty.MEDIUM
                        }
                        if (ex.problemStatement.isNotBlank() && ex.options.size >= 2 && ex.correctIndex in ex.options.indices) {
                            val q = Question(
                                id = "enem_extra_${lesson.id}_${ex.id}_$idx",
                                lessonId = lesson.id,
                                format = QuestionFormat.MULTIPLE_CHOICE,
                                difficulty = diff,
                                skill = mod.title,
                                concept = lesson.subtopic,
                                statement = ex.problemStatement,
                                explanation = ex.stepByStepSolution.ifBlank {
                                    "Revisar conceitos de ${lesson.subtopic} (${mod.title})."
                                },
                                retryGuidance = ex.hint.ifBlank {
                                    "Releia a fundamentação teórica da aula '${lesson.title}' e identifique os conceitos-chave."
                                },
                                source = QuestionSource.AUTORAL,
                                options = ex.options,
                                answerIndex = ex.correctIndex
                            )
                            questions.add(BankQuestion(question = q, lesson = ref))
                        }
                    }
                }
            }
        }

        registerDiscipline(
            subjectName = "Linguagens",
            modules = com.example.data.languages.EnemLanguagesCurriculum.getAllModules(),
            exerciseFetcher = { id -> com.example.data.languages.EnemLanguagesDidacticProvider.getExercisesForLesson(id) }
        )
        registerDiscipline(
            subjectName = "História",
            modules = com.example.data.history.EnemHistoryCurriculum.getAllModules(),
            exerciseFetcher = { id -> com.example.data.history.EnemHistoryDidacticProvider.getExercisesForLesson(id) }
        )
        registerDiscipline(
            subjectName = "Geografia",
            modules = com.example.data.geography.EnemGeographyCurriculum.getAllModules(),
            exerciseFetcher = { id -> com.example.data.geography.EnemGeographyDidacticProvider.getExercisesForLesson(id) }
        )
        registerDiscipline(
            subjectName = "Química",
            modules = com.example.data.chemistry.EnemChemistryCurriculum.getAllModules(),
            exerciseFetcher = { id -> com.example.data.chemistry.EnemChemistryDidacticProvider.getExercisesForLesson(id) }
        )

        // Registra Filosofia e Sociologia (Ciências Humanas — ENEM Dia 1)
        val (filSocLessons, filSocQuestions) = com.example.data.humanities.EnemPhilosophySociologyCurriculum.buildAuthoredBankQuestions(globalOrder)
        lessons.addAll(filSocLessons)
        questions.addAll(filSocQuestions)

        val seenStatements = (
            cachedAuthoredQuestions.orEmpty().map { it.question.statement.trim().lowercase() } +
                questions.map { it.question.statement.trim().lowercase() }
            ).toMutableSet()

        val allAvailableLessons = cachedLessons.orEmpty() + lessons
        val lessonByModule = allAvailableLessons.groupBy { it.moduleId }
        val lessonBySubject = allAvailableLessons.groupBy { it.subject }
        val optionPrefixRegex = Regex("^[A-Ea-e][)\\.]\\s*")
        val markdownImageRegex = Regex("!\\[[^\\]]*]\\([^)]*\\)")

        // Importa integralmente todos os 6 bancos JSON oficiais da API do ENEM (2.382 questões)
        val enemAssetFiles = listOf(
            "questoes_enem_linguagens.json" to "Linguagens",
            "questoes_enem_geografia.json" to "Geografia",
            "questoes_enem_humanas.json" to "História",
            "questoes_enem_quimica.json" to "Química",
            "questoes_enem_natureza.json" to "Biologia",
            "banco_questoes.json" to "Matemática"
        )

        val listType = com.squareup.moshi.Types.newParameterizedType(List::class.java, Map::class.java)
        val mapListAdapter = com.squareup.moshi.Moshi.Builder().build().adapter<List<Map<String, Any?>>>(listType)
        val subjectCounters = mutableMapOf<String, Int>()

        for ((fileName, defaultSubj) in enemAssetFiles) {
            runCatching {
                val rawText = readAssetText(context, fileName)
                val rawItems = mapListAdapter.fromJson(rawText).orEmpty()

                for (item in rawItems) {
                    val rawId = (item["id"] as? String)?.trim().orEmpty()
                    val rawStmtOriginal = ((item["statement"] as? String)
                        ?: (item["title"] as? String)
                        ?: (item["problemStatement"] as? String)).orEmpty().trim()
                    val rawStmt = rawStmtOriginal
                        .replace(markdownImageRegex, "")
                        .replace(Regex("\n{3,}"), "\n\n")
                        .trim()
                    if (rawStmt.length < 35) continue

                    val normStmt = rawStmt.lowercase()
                    if (!seenStatements.add(normStmt)) continue

                    val rawOpts = ((item["options"] as? List<*>) ?: (item["alternatives"] as? List<*>))
                        ?.mapNotNull { (it as? String)?.replace(optionPrefixRegex, "")?.trim() }
                        ?.filter { it.isNotBlank() }
                        .orEmpty()
                    if (rawOpts.size < 4 || rawOpts.map { it.lowercase() }.distinct().size != rawOpts.size) continue

                    val rawDisc = (item["disciplina"] as? String).orEmpty()
                    val rawTopic = (item["topic"] as? String).orEmpty()
                    val rawSubtopic = (item["subtopic"] as? String).orEmpty()

                    val resolvedSubject = when (fileName) {
                        "questoes_enem_humanas.json" -> {
                            com.example.data.humanities.EnemPhilosophySociologyCurriculum.classifyHumanitiesSubject(
                                statement = rawStmt,
                                topic = rawTopic,
                                subtopic = rawSubtopic
                            ) ?: "História"
                        }
                        "questoes_enem_natureza.json" -> {
                            when {
                                rawDisc.contains("Física", ignoreCase = true) || rawTopic.contains("Física", ignoreCase = true) -> "Física"
                                rawDisc.contains("Química", ignoreCase = true) || rawTopic.contains("Química", ignoreCase = true) -> "Química"
                                else -> "Biologia"
                            }
                        }
                        else -> defaultSubj
                    }

                    val subjectLessons = lessonBySubject[resolvedSubject].orEmpty()
                    if (subjectLessons.isEmpty()) continue

                    val correctLetter = ((item["correctLetter"] as? String) ?: (item["correctAnswer"] as? String))
                        ?.trim()?.uppercase()
                    val ansIdx = when {
                        !correctLetter.isNullOrBlank() -> (correctLetter.first() - 'A').coerceIn(0, rawOpts.size - 1)
                        item["correctIndex"] is Number -> (item["correctIndex"] as Number).toInt().coerceIn(0, rawOpts.size - 1)
                        else -> 0
                    }

                    val localIdx = (subjectCounters[resolvedSubject] ?: 0)
                    subjectCounters[resolvedSubject] = localIdx + 1

                    val rawDiff = (item["difficulty"] as? String)?.lowercase().orEmpty()
                    val diff = when {
                        rawDiff.contains("fácil") || rawDiff.contains("facil") -> QDifficulty.EASY
                        rawDiff.contains("difícil") || rawDiff.contains("dificil") -> QDifficulty.HARD
                        else -> when (localIdx % 3) {
                            0 -> QDifficulty.EASY
                            1 -> QDifficulty.MEDIUM
                            else -> QDifficulty.HARD
                        }
                    }

                    val modId = (item["moduleId"] as? String).orEmpty()
                    val modLessons = if (resolvedSubject == defaultSubj) {
                        lessonByModule[modId].orEmpty().ifEmpty { subjectLessons }
                    } else {
                        subjectLessons
                    }
                    val targetLesson = modLessons[localIdx % modLessons.size]

                    val explanation = (item["explanation"] as? String)?.trim().orEmpty().ifBlank {
                        "Gabarito oficial INEP/ENEM: alternativa ${('A' + ansIdx)}."
                    }

                    val q = Question(
                        id = "enem_asset_${resolvedSubject.lowercase()}_${rawId.ifBlank { localIdx.toString() }}_$localIdx",
                        lessonId = targetLesson.lessonId,
                        format = QuestionFormat.MULTIPLE_CHOICE,
                        difficulty = diff,
                        skill = (item["moduleTitle"] as? String)?.takeIf { it.isNotBlank() && resolvedSubject == defaultSubj }
                            ?: targetLesson.moduleTitle,
                        concept = rawSubtopic.takeIf { it.isNotBlank() && resolvedSubject == defaultSubj }
                            ?: targetLesson.subtopic,
                        statement = rawStmt,
                        explanation = explanation,
                        retryGuidance = "Revise os conceitos de '${targetLesson.subtopic}' na trilha de $resolvedSubject.",
                        source = QuestionSource.INEP_ENEM,
                        options = rawOpts,
                        answerIndex = ansIdx
                    )
                    questions.add(BankQuestion(question = q, lesson = targetLesson))
                }
            }
        }

        return Pair(lessons, questions.distinctBy { it.id })
    }

    fun getEnemExtraLessons(context: Context? = null): List<LessonRef> {
        ensureLoaded(context)
        return cachedEnemExtraLessons.orEmpty()
    }

    fun getEnemExtraQuestions(context: Context? = null): List<BankQuestion> {
        ensureLoaded(context)
        return cachedEnemExtraQuestions.orEmpty()
    }

    fun getAllQuestions(
        context: Context? = null,
        includeLegacy: Boolean = true,
        includeTranspetro: Boolean = true,
        includeEnemExtra: Boolean = true
    ): List<BankQuestion> {
        ensureLoaded(context)
        val authored = cachedAuthoredQuestions.orEmpty()
        val legacy = if (includeLegacy) cachedLegacyQuestions.orEmpty() else emptyList()
        val transpetro = if (includeTranspetro) getTranspetroQuestions(context) else emptyList()
        val enemExtra = if (includeEnemExtra) getEnemExtraQuestions(context) else emptyList()
        return authored + legacy + transpetro + enemExtra
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
        val standard = cachedLessons.orEmpty().map { it.subject }
        val enemExtra = getEnemExtraLessons(context).map { it.subject }
        val transpetro = getTranspetroLessons().map { it.subject }
        return (standard + enemExtra + transpetro).distinct()
    }

    fun getModulesForSubject(subject: String, context: Context? = null): List<ModuleSummary> {
        ensureLoaded(context)
        val allLessons = cachedLessons.orEmpty() + getEnemExtraLessons(context) + getTranspetroLessons()
        val filtered = if (subject.isBlank() || subject.equals("Todas", ignoreCase = true)) {
            allLessons
        } else {
            allLessons.filter { it.subject.equals(subject, ignoreCase = true) }
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
        val allLessons = cachedLessons.orEmpty() + getEnemExtraLessons(context) + getTranspetroLessons()
        return allLessons
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
        ensureLoaded(context)
        if (lessonId.startsWith("tp_")) {
            return getTranspetroQuestions(context).filter { it.lesson.lessonId == lessonId }
        }
        val isCoreAuthoredLesson = cachedLessons.orEmpty().any { it.lessonId == lessonId }
        val includeExtra = includeLegacy || !isCoreAuthoredLesson
        return getAllQuestions(
            context = context,
            includeLegacy = includeLegacy,
            includeTranspetro = true,
            includeEnemExtra = includeExtra
        ).filter { it.lesson.lessonId == lessonId }
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
        includeTranspetro: Boolean = true,
        context: Context? = null
    ): List<BankQuestion> {
        val q = query.trim().lowercase()
        val extraSubjects = setOf("linguagens", "história", "geografia", "química", "filosofia", "sociologia")
        val useEnemExtra = includeLegacy || subject.trim().lowercase() in extraSubjects
        return getAllQuestions(context, includeLegacy, includeTranspetro, includeEnemExtra = useEnemExtra).filter { item ->
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
        random: Random = Random(System.nanoTime())
    ): List<BankQuestion> {
        ensureLoaded(context)

        if (config.preset != SpecificExamPreset.CUSTOM) {
            val presetTargetCount = config.questionCount.coerceIn(1, config.preset.maxQuestionCount)
            val generated = generateSpecificPresetSimulado(config, presetTargetCount, context, random)
            lastSimuladoQuestionIds = generated.map { it.id }.toSet()
            return generated
        }

        val targetCount = config.questionCount.coerceIn(1, MAX_SIMULADO_QUESTIONS)
        val extraSubjects = setOf("linguagens", "história", "geografia", "química", "filosofia", "sociologia")
        val useEnemExtra = config.includeLegacy || config.subject.trim().lowercase() in extraSubjects

        val basePool = getAllQuestions(
            context = context,
            includeLegacy = config.includeLegacy,
            includeTranspetro = true,
            includeEnemExtra = useEnemExtra
        ).filter { bq ->
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
                            val result = combined.shuffled(random).take(targetCount)
                            lastSimuladoQuestionIds = result.map { it.id }.toSet()
                            return result
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

        val generated = sampleBalancedByDifficulty(candidatePool, targetCount, random)
        lastSimuladoQuestionIds = generated.map { it.id }.toSet()
        return generated
    }

    /**
     * Gera simulados de cadernos específicos respeitando rigorosamente a proporção, quantidade
     * e formato de Múltipla Escolha (A–E) da banca examinadora oficial:
     * - ENEM_DIA_1 (Banca INEP — 90 questões):
     *   • 50% Linguagens, Códigos e suas Tecnologias (45q)
     *   • 50% Ciências Humanas e suas Tecnologias (45q: 15 História + 15 Geografia + 8 Filosofia + 7 Sociologia)
     * - ENEM_DIA_2 (Banca INEP — 90 questões):
     *   • 50% Ciências da Natureza e suas Tecnologias (45q: 15 Biologia + 15 Física + 15 Química)
     *   • 50% Matemática e suas Tecnologias (45q)
     * - TRANSPETRO (Banca Fundação Cesgranrio — 60 questões):
     *   • Bloco I — Conhecimentos Básicos (33,33% = 20q): 10 Língua Portuguesa (16,67%) + 10 Matemática (16,67%)
     *   • Bloco II — Conhecimentos Específicos (66,67% = 40q): 10 ADM/Legislação + 10 Finanças + 10 Logística + 10 Informática
     */
    private fun generateSpecificPresetSimulado(
        config: SimuladoConfig,
        targetCount: Int,
        context: Context?,
        random: Random
    ): List<BankQuestion> {
        val officialMultipleChoiceFilter: (BankQuestion) -> Boolean = { bq ->
            bq.question.format == QuestionFormat.MULTIPLE_CHOICE &&
                bq.question.options.size >= 4 &&
                (config.allowedFormats.isEmpty() || bq.question.format in config.allowedFormats)
        }

        return when (config.preset) {
            SpecificExamPreset.ENEM_DIA_1 -> {
                val all = getAllQuestions(
                    context = context,
                    includeLegacy = config.includeLegacy,
                    includeTranspetro = false,
                    includeEnemExtra = true
                ).filter(officialMultipleChoiceFilter)
                    .distinctBy { it.question.statement.trim().lowercase() }

                val linguagensPool = all.filter { it.subject.equals("Linguagens", ignoreCase = true) }
                val historiaPool = all.filter { it.subject.equals("História", ignoreCase = true) }
                val geografiaPool = all.filter { it.subject.equals("Geografia", ignoreCase = true) }
                val filosofiaPool = all.filter { it.subject.equals("Filosofia", ignoreCase = true) }
                val sociologiaPool = all.filter { it.subject.equals("Sociologia", ignoreCase = true) }

                val lingCount = (targetCount * 0.50).roundToInt().coerceAtLeast(1)
                val humCount = (targetCount - lingCount).coerceAtLeast(0)

                val filCount = (humCount * (8.0 / 45.0)).roundToInt().coerceAtLeast(if (humCount >= 4) 1 else 0)
                val socCount = (humCount * (7.0 / 45.0)).roundToInt().coerceAtLeast(if (humCount >= 4) 1 else 0)
                val remHum = (humCount - filCount - socCount).coerceAtLeast(0)
                val histCount = (remHum + 1) / 2
                val geoCount = (remHum - histCount).coerceAtLeast(0)

                val pickedLing = sampleBalancedByDifficulty(linguagensPool, lingCount, random)
                val pickedHist = sampleBalancedByDifficulty(historiaPool, histCount, random)
                val pickedGeo = sampleBalancedByDifficulty(geografiaPool, geoCount, random)
                val pickedFil = sampleBalancedByDifficulty(filosofiaPool, filCount, random)
                val pickedSoc = sampleBalancedByDifficulty(sociologiaPool, socCount, random)

                // Caderno 1º Dia: Questões 01..45 (Linguagens) seguidas de 46..90 (Ciências Humanas: História, Geografia, Filosofia e Sociologia embaralhadas)
                val humanasCombined = (pickedHist + pickedGeo + pickedFil + pickedSoc).shuffled(random)
                val combined = (pickedLing.shuffled(random) + humanasCombined).distinctBy { it.id }
                if (combined.size < targetCount) {
                    val remainingPool = (linguagensPool + historiaPool + geografiaPool + filosofiaPool + sociologiaPool)
                        .filterNot { c -> combined.any { it.id == c.id } }
                    (combined + sampleBalancedByDifficulty(remainingPool, targetCount - combined.size, random))
                        .take(targetCount)
                } else {
                    combined.take(targetCount)
                }
            }

            SpecificExamPreset.ENEM_DIA_2 -> {
                val all = getAllQuestions(
                    context = context,
                    includeLegacy = config.includeLegacy,
                    includeTranspetro = false,
                    includeEnemExtra = true
                ).filter(officialMultipleChoiceFilter)
                    .distinctBy { it.question.statement.trim().lowercase() }

                val bioPool = all.filter { it.subject.equals("Biologia", ignoreCase = true) }
                val fisPool = all.filter { it.subject.equals("Física", ignoreCase = true) }
                val quimPool = all.filter { it.subject.equals("Química", ignoreCase = true) }
                val matPool = all.filter {
                    !it.lesson.lessonId.startsWith("tp_") &&
                        (it.subject.equals("Matemática", ignoreCase = true) ||
                            it.subject.equals("Matemática Básica", ignoreCase = true))
                }

                val natCount = (targetCount * 0.50).roundToInt().coerceAtLeast(1)
                val matCount = (targetCount - natCount).coerceAtLeast(0)
                val bioCount = (natCount / 3.0).roundToInt()
                val fisCount = (natCount / 3.0).roundToInt()
                val quimCount = (natCount - bioCount - fisCount).coerceAtLeast(0)

                val pickedBio = sampleBalancedByDifficulty(bioPool, bioCount, random)
                val pickedFis = sampleBalancedByDifficulty(fisPool, fisCount, random)
                val pickedQuim = sampleBalancedByDifficulty(quimPool, quimCount, random)
                val pickedMat = sampleBalancedByDifficulty(matPool, matCount, random)

                // Caderno 2º Dia: Questões 01..45 (Ciências da Natureza embaralhadas) seguidas de 46..90 (Matemática)
                val natureCombined = (pickedBio + pickedFis + pickedQuim).shuffled(random)
                val combined = (natureCombined + pickedMat.shuffled(random)).distinctBy { it.id }
                if (combined.size < targetCount) {
                    val remainingPool = (bioPool + fisPool + quimPool + matPool)
                        .filterNot { c -> combined.any { it.id == c.id } }
                    (combined + sampleBalancedByDifficulty(remainingPool, targetCount - combined.size, random))
                        .take(targetCount)
                } else {
                    combined.take(targetCount)
                }
            }

            SpecificExamPreset.TRANSPETRO -> {
                val tpPool = getTranspetroQuestions(context)
                    .filter(officialMultipleChoiceFilter)
                    .distinctBy { it.question.statement.trim().lowercase() }

                val porPool = tpPool.filter { it.lesson.lessonId.startsWith("tp_por_") }
                val matPool = tpPool.filter { it.lesson.lessonId.startsWith("tp_mat_") }
                val admPool = tpPool.filter { it.lesson.lessonId.startsWith("tp_adm_") }
                val finPool = tpPool.filter { it.lesson.lessonId.startsWith("tp_fin_") }
                val logPool = tpPool.filter { it.lesson.lessonId.startsWith("tp_log_") }
                val infPool = tpPool.filter { it.lesson.lessonId.startsWith("tp_inf_") }

                // Proporção Oficial Edital Fundação Cesgranrio (60 questões):
                // Bloco I (Básicos — 20/60 = 33,33%): 10 Língua Portuguesa (16,67%) + 10 Matemática (16,67%)
                // Bloco II (Específicos — 40/60 = 66,67%): 10 ADM + 10 Finanças + 10 Logística + 10 Informática
                val countPor = (targetCount * (10.0 / 60.0)).roundToInt().coerceAtLeast(if (targetCount >= 3) 1 else 0)
                val countMat = (targetCount * (10.0 / 60.0)).roundToInt().coerceAtLeast(if (targetCount >= 3) 1 else 0)
                val countEsp = (targetCount - countPor - countMat).coerceAtLeast(1)

                val countAdm = (countEsp / 4.0).roundToInt()
                val countFin = (countEsp / 4.0).roundToInt()
                val countLog = (countEsp / 4.0).roundToInt()
                val countInf = (countEsp - countAdm - countFin - countLog).coerceAtLeast(0)

                val pickedPor = sampleBalancedByDifficulty(porPool, countPor, random)
                val pickedMat = sampleBalancedByDifficulty(matPool, countMat, random)
                val pickedAdm = sampleBalancedByDifficulty(admPool, countAdm, random)
                val pickedFin = sampleBalancedByDifficulty(finPool, countFin, random)
                val pickedLog = sampleBalancedByDifficulty(logPool, countLog, random)
                val pickedInf = sampleBalancedByDifficulty(infPool, countInf, random)

                // Caderno Cesgranrio: Bloco I (Português 01..10, Matemática 11..20) + Bloco II (Específicos 21..60)
                val blocoII = (pickedAdm + pickedFin + pickedLog + pickedInf).shuffled(random)
                val combined = (pickedPor.shuffled(random) + pickedMat.shuffled(random) + blocoII).distinctBy { it.id }
                if (combined.size < targetCount) {
                    val remainingPool = tpPool.filterNot { c -> combined.any { it.id == c.id } }
                    (combined + sampleBalancedByDifficulty(remainingPool, targetCount - combined.size, random))
                        .take(targetCount)
                } else {
                    combined.take(targetCount)
                }
            }

            SpecificExamPreset.CUSTOM -> emptyList()
        }
    }

    /**
     * Seleciona questões respeitando as proporções orientativas (30% Fácil, 45% Média, 25% Difícil),
     * diversificando as aulas (`lessonId`) e controlando a semelhança com o simulado imediatamente anterior:
     * permite repetição parcial natural (ex.: até ~25% de questões em comum), mas impede que dois
     * simulados seguidos tenham 80%–90% de semelhança ou sigam o mesmo padrão.
     */
    private fun sampleBalancedByDifficulty(
        pool: List<BankQuestion>,
        count: Int,
        random: Random
    ): List<BankQuestion> {
        if (count <= 0 || pool.isEmpty()) return emptyList()
        if (pool.size <= count) return pool.shuffled(random)

        val previousIds = lastSimuladoQuestionIds
        // Permite até ~25% de repetição natural em relação ao simulado anterior quando o banco é amplo
        val maxRepeatedAllowed = if (previousIds.isEmpty()) count else (count * 0.25).roundToInt()

        val easyTarget = (count * 0.30).roundToInt()
        val hardTarget = (count * 0.25).roundToInt()
        val mediumTarget = (count - easyTarget - hardTarget).coerceAtLeast(0)

        val easyPool = diversifyByLessonAndFormat(pool.filter { it.question.difficulty == QDifficulty.EASY }, random)
        val mediumPool = diversifyByLessonAndFormat(pool.filter { it.question.difficulty == QDifficulty.MEDIUM }, random)
        val hardPool = diversifyByLessonAndFormat(pool.filter { it.question.difficulty == QDifficulty.HARD }, random)

        val selected = LinkedHashMap<String, BankQuestion>()
        var repeatedCount = 0

        fun pickFromTier(tierPool: List<BankQuestion>, tierTarget: Int) {
            if (tierTarget <= 0 || tierPool.isEmpty()) return
            var addedInTier = 0
            // 1ª passagem: aceita repetição natural até o teto maxRepeatedAllowed
            for (item in tierPool) {
                if (addedInTier >= tierTarget) break
                if (selected.containsKey(item.id)) continue
                val isRepeated = item.id in previousIds
                if (isRepeated && repeatedCount >= maxRepeatedAllowed) continue
                selected[item.id] = item
                if (isRepeated) repeatedCount++
                addedInTier++
            }
            // 2ª passagem (fallback caso o banco daquele nível seja pequeno)
            if (addedInTier < tierTarget) {
                for (item in tierPool) {
                    if (addedInTier >= tierTarget) break
                    if (selected.putIfAbsent(item.id, item) == null) {
                        addedInTier++
                    }
                }
            }
        }

        pickFromTier(easyPool, easyTarget)
        pickFromTier(mediumPool, mediumTarget)
        pickFromTier(hardPool, hardTarget)

        if (selected.size < count) {
            val allShuffled = diversifyByLessonAndFormat(pool, random)
            for (item in allShuffled) {
                if (selected.size >= count) break
                if (selected.containsKey(item.id)) continue
                val isRepeated = item.id in previousIds
                if (isRepeated && repeatedCount >= maxRepeatedAllowed) continue
                selected[item.id] = item
                if (isRepeated) repeatedCount++
            }
            for (item in allShuffled) {
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
        val byLesson = items.shuffled(random).groupBy { it.lesson.lessonId }.mapValues { it.value.shuffled(random).toMutableList() }
        val lessonKeys = byLesson.keys.toList().shuffled(random)
        val result = mutableListOf<BankQuestion>()
        var added = true
        while (added) {
            added = false
            for (key in lessonKeys.shuffled(random)) {
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

