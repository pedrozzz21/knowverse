package com.example.data.transpetro

import android.content.Context
import com.example.data.assessment.BankQuestion
import com.example.data.assessment.LessonRef
import com.example.data.assessment.QDifficulty
import com.example.data.assessment.Question
import com.example.data.assessment.QuestionFormat
import com.example.data.assessment.QuestionSource
import com.example.data.model.MathExercise
import com.squareup.moshi.Moshi
import java.io.File

data class TranspetroRawOption(val id: String, val text: String)

data class TranspetroRawQuestion(
    val id: String,
    val subject: String,
    val topic: String,
    val difficulty: String?,
    val source_type: String?,
    val statement: String?,
    val options: List<TranspetroRawOption>,
    val correct_answer: String?,
    val explanation: String?,
    val origin_label: String?,
    val lessonId: String
)

object TranspetroQuestionBankLoader {

    private const val ASSET_PATH = "assessment/transpetro_adm_controle_banco_questoes_360.json"

    @Volatile
    private var cachedQuestions: List<TranspetroRawQuestion>? = null

    fun loadFullQuestions(context: Context? = null): List<TranspetroRawQuestion> {
        cachedQuestions?.let { return it }
        val jsonText = readRawJson(context) ?: return emptyList()
        val parsed = parseJson(jsonText)
        if (parsed.isNotEmpty()) {
            cachedQuestions = parsed
        }
        return parsed
    }

    fun getExercisesForLesson(lessonId: String, context: Context? = null): List<MathExercise> {
        return loadFullQuestions(context)
            .filter { it.lessonId == lessonId }
            .map { q -> toMathExercise(q) }
    }

    fun getAllBankQuestions(context: Context? = null): List<BankQuestion> {
        val fromJson = loadFullQuestions(context).map { q -> toBankQuestion(q) }
        val lessonExercises = TranspetroCurriculum.getAllLessons().flatMap { lesson ->
            TranspetroDidacticProvider.getSupplementaryExercisesForLesson(lesson.id).map { ex ->
                mathExerciseToBankQuestion(ex, lesson.id)
            }
        }
        return (fromJson + lessonExercises).distinctBy { it.id }
    }

    fun getAllLessonRefs(): List<LessonRef> {
        var order = 1
        return TranspetroCurriculum.allDisciplines.flatMap { disc ->
            disc.modules.flatMap { mod ->
                mod.lessons.map { lesson ->
                    LessonRef(
                        lessonId = lesson.id,
                        subject = disc.name,
                        moduleId = mod.id,
                        moduleTitle = mod.title,
                        title = lesson.title,
                        subtopic = lesson.subtopic,
                        order = order++
                    )
                }
            }
        }
    }

    private fun readRawJson(context: Context?): String? {
        if (context != null) {
            runCatching {
                context.assets.open(ASSET_PATH).bufferedReader(Charsets.UTF_8).use { it.readText() }
            }.getOrNull()?.let { return it }
        }
        val fallbackFiles = listOf(
            File("app/src/main/assets/$ASSET_PATH"),
            File("src/main/assets/$ASSET_PATH"),
            File("public/transpetro_adm_controle_banco_questoes_360.json"),
            File("../public/transpetro_adm_controle_banco_questoes_360.json")
        )
        for (file in fallbackFiles) {
            if (file.exists() && file.isFile) {
                return runCatching { file.readText(Charsets.UTF_8) }.getOrNull()
            }
        }
        return null
    }

    @Suppress("UNCHECKED_CAST")
    private fun parseJson(jsonText: String): List<TranspetroRawQuestion> {
        return runCatching {
            val adapter = Moshi.Builder().build().adapter(Map::class.java)
            val root = adapter.fromJson(jsonText) as? Map<String, Any?> ?: return emptyList()
            val rawList = root["questions"] as? List<Map<String, Any?>> ?: return emptyList()
            rawList.mapNotNull { item ->
                val statement = (item["statement"] as? String)?.trim()
                if (statement.isNullOrBlank()) return@mapNotNull null
                val id = (item["id"] as? String)?.trim().orEmpty()
                val subject = (item["subject"] as? String)?.trim().orEmpty()
                val topic = (item["topic"] as? String)?.trim().orEmpty()
                val difficulty = (item["difficulty"] as? String)?.trim()
                val sourceType = (item["source_type"] as? String)?.trim()
                val correctAnswer = (item["correct_answer"] as? String)?.trim()
                val explanation = (item["explanation"] as? String)?.trim()
                val originLabel = (item["origin_label"] as? String)?.trim()
                val rawOptions = item["options"] as? List<Map<String, Any?>> ?: emptyList()
                val options = rawOptions.mapNotNull { opt ->
                    val optId = (opt["id"] as? String)?.trim().orEmpty()
                    val optText = (opt["text"] as? String)?.trim().orEmpty()
                    if (optId.isNotEmpty() && optText.isNotEmpty()) {
                        TranspetroRawOption(id = optId, text = optText)
                    } else null
                }
                if (id.isEmpty() || options.size < 2 || correctAnswer.isNullOrEmpty()) {
                    return@mapNotNull null
                }
                val itemLessonId = (item["lessonId"] as? String)?.trim().orEmpty()
                TranspetroRawQuestion(
                    id = id,
                    subject = subject,
                    topic = topic,
                    difficulty = difficulty,
                    source_type = sourceType,
                    statement = statement,
                    options = options,
                    correct_answer = correctAnswer,
                    explanation = explanation,
                    origin_label = originLabel,
                    lessonId = if (itemLessonId.isNotEmpty()) itemLessonId else resolveLessonId(id, subject, topic)
                )
            }
        }.getOrDefault(emptyList())
    }

    fun toMathExercise(q: TranspetroRawQuestion): MathExercise {
        val correctIndex = q.options.indexOfFirst { it.id.equals(q.correct_answer, ignoreCase = true) }
            .coerceAtLeast(0)
        val correctChoice = q.options.getOrNull(correctIndex)
        val baseExplanation = q.explanation?.takeIf { it.isNotBlank() }
            ?: "Alternativa correta: ${q.correct_answer}."
        return MathExercise(
            id = q.id,
            title = "Questão ${q.id} • ${q.topic}",
            problemStatement = q.statement.orEmpty(),
            options = q.options.map { "${it.id}) ${it.text}" },
            correctIndex = correctIndex,
            hint = "Dica: atente para o padrão da Fundação Cesgranrio sobre ${q.topic}.",
            stepByStepSolution = listOf(
                "Identificação do tema (${q.topic}): analise o comando da questão sob o padrão Cesgranrio.",
                "Fundamentação técnica: $baseExplanation",
                "Conclusão: a alternativa correta é ${correctChoice?.id ?: q.correct_answer}) ${correctChoice?.text.orEmpty()}."
            ).joinToString("\n")
        )
    }

    private fun toBankQuestion(q: TranspetroRawQuestion): BankQuestion {
        val disc = TranspetroCurriculum.findDisciplineByLessonId(q.lessonId)
        val mod = disc?.modules?.firstOrNull { m -> m.lessons.any { it.id == q.lessonId } }
        val lessonTitle = TranspetroCurriculum.findLesson(q.lessonId)?.title ?: q.topic
        val diff = when (q.difficulty?.lowercase()) {
            "fácil", "facil" -> QDifficulty.EASY
            "difícil", "dificil" -> QDifficulty.HARD
            else -> QDifficulty.MEDIUM
        }
        val correctIndex = q.options.indexOfFirst { it.id.equals(q.correct_answer, ignoreCase = true) }
            .coerceAtLeast(0)
        val baseExplanation = q.explanation?.takeIf { it.isNotBlank() }
            ?: "Gabarito oficial: alternativa ${q.correct_answer}."

        val lessonRef = LessonRef(
            lessonId = q.lessonId,
            subject = disc?.name ?: "Concurso Transpetro - ADM e Controle",
            moduleId = mod?.id ?: "mod_tp",
            moduleTitle = mod?.title ?: "Módulo Transpetro",
            title = lessonTitle,
            subtopic = lessonTitle,
            order = 1
        )
        val question = Question(
            id = q.id,
            lessonId = q.lessonId,
            format = QuestionFormat.MULTIPLE_CHOICE,
            difficulty = diff,
            skill = q.topic,
            concept = lessonTitle,
            statement = q.statement.orEmpty(),
            explanation = baseExplanation,
            retryGuidance = "Revise a teoria da aula e tente novamente.",
            source = QuestionSource.AUTORAL,
            options = q.options.map { "${it.id}) ${it.text}" },
            answerIndex = correctIndex
        )
        return BankQuestion(question = question, lesson = lessonRef)
    }

    private fun mathExerciseToBankQuestion(ex: MathExercise, lessonId: String): BankQuestion {
        val disc = TranspetroCurriculum.findDisciplineByLessonId(lessonId)
        val mod = disc?.modules?.firstOrNull { m -> m.lessons.any { it.id == lessonId } }
        val lessonTitle = TranspetroCurriculum.findLesson(lessonId)?.title ?: "Transpetro"

        val lessonRef = LessonRef(
            lessonId = lessonId,
            subject = disc?.name ?: "Concurso Transpetro - ADM e Controle",
            moduleId = mod?.id ?: "mod_tp",
            moduleTitle = mod?.title ?: "Módulo Transpetro",
            title = lessonTitle,
            subtopic = lessonTitle,
            order = 1
        )
        val question = Question(
            id = ex.id,
            lessonId = lessonId,
            format = QuestionFormat.MULTIPLE_CHOICE,
            difficulty = QDifficulty.MEDIUM,
            skill = mod?.title ?: lessonTitle,
            concept = lessonTitle,
            statement = ex.problemStatement,
            explanation = ex.stepByStepSolution,
            retryGuidance = "Analise as etapas da resolução comentada e tente resolver sem consultar.",
            source = QuestionSource.AUTORAL,
            options = ex.options,
            answerIndex = ex.correctIndex
        )
        return BankQuestion(question = question, lesson = lessonRef)
    }

    private fun resolveLessonId(id: String, subject: String, topic: String): String {
        explicitQuestionLessonMap[id]?.let { return it }
        val realMatch = Regex("""TP-REAL-\d{4}-(\d{2})""").find(id)
        if (realMatch != null) {
            val num = realMatch.groupValues[1].toIntOrNull() ?: 1
            realQuestionLessonIdMap[num]?.let { return it }
        }
        return when {
            subject.contains("Administrativos", ignoreCase = true) -> "tp_adm_m1_a1"
            subject.contains("Finanças", ignoreCase = true) -> "tp_fin_m1_a1"
            subject.contains("Logística", ignoreCase = true) -> "tp_log_m1_a1"
            subject.contains("Informática", ignoreCase = true) -> "tp_inf_m1_a1"
            subject.contains("Portuguesa", ignoreCase = true) -> "tp_por_m1_a1"
            subject.contains("Matemática", ignoreCase = true) -> "tp_mat_m1_a1"
            else -> "tp_adm_m1_a1"
        }
    }

    private val realQuestionLessonIdMap: Map<Int, String> = mapOf(
        1 to "tp_por_m1_a1", 2 to "tp_por_m1_a2", 3 to "tp_por_m2_a1", 4 to "tp_por_m2_a2",
        5 to "tp_por_m3_a1", 6 to "tp_por_m3_a2", 7 to "tp_por_m7_a1", 8 to "tp_por_m8_a1",
        9 to "tp_por_m9_a1", 10 to "tp_por_m11_a1",
        11 to "tp_mat_m1_a1", 12 to "tp_mat_m1_a2", 13 to "tp_mat_m1_a2", 14 to "tp_mat_m2_a1",
        15 to "tp_mat_m2_a2", 16 to "tp_mat_m3_a1", 17 to "tp_mat_m4_a1", 18 to "tp_mat_m6_a1",
        19 to "tp_mat_m8_a1", 20 to "tp_mat_m9_a1",
        21 to "tp_adm_m1_a2", 22 to "tp_adm_m1_a3", 23 to "tp_adm_m1_a4", 24 to "tp_adm_m2_a1",
        25 to "tp_adm_m2_a2", 26 to "tp_adm_m3_a1", 27 to "tp_adm_m3_a2", 28 to "tp_adm_m3_a3",
        29 to "tp_adm_m3_a4", 30 to "tp_adm_m3_a5", 31 to "tp_adm_m3_a6", 32 to "tp_adm_m4_a2",
        33 to "tp_adm_m5_a1", 34 to "tp_adm_m6_a1", 35 to "tp_adm_m9_a1",
        36 to "tp_fin_m1_a1", 37 to "tp_fin_m1_a2", 38 to "tp_fin_m2_a1", 39 to "tp_fin_m2_a2",
        40 to "tp_fin_m3_a1", 41 to "tp_fin_m3_a2", 42 to "tp_fin_m3_a3", 43 to "tp_fin_m4_a1",
        44 to "tp_fin_m4_a2",
        45 to "tp_log_m1_a1", 46 to "tp_log_m1_a1", 47 to "tp_log_m1_a2", 48 to "tp_log_m2_a1",
        49 to "tp_log_m2_a2", 50 to "tp_log_m2_a3", 51 to "tp_log_m3_a1", 52 to "tp_log_m4_a1",
        53 to "tp_log_m5_a1", 54 to "tp_log_m6_a1",
        55 to "tp_inf_m1_a1", 56 to "tp_inf_m1_a2", 57 to "tp_inf_m1_a2", 58 to "tp_inf_m1_a2",
        59 to "tp_inf_m2_a1", 60 to "tp_inf_m4_a1"
    )

    private val explicitQuestionLessonMap: Map<String, String> = mapOf(
        // Processos Administrativos e Legislação (41)
        "TP-ADM-001" to "tp_adm_m1_a1",
        "TP-ADM-002" to "tp_adm_m1_a2",
        "TP-ADM-003" to "tp_adm_m1_a4",
        "TP-ADM-004" to "tp_adm_m2_a2",
        "TP-ADM-005" to "tp_adm_m2_a3",
        "TP-ADM-006" to "tp_adm_m2_a4",
        "TP-ADM-007" to "tp_adm_m3_a1",
        "TP-ADM-008" to "tp_adm_m3_a2",
        "TP-ADM-009" to "tp_adm_m4_a3",
        "TP-ADM-010" to "tp_adm_m5_a2",
        "TP-ADM-011" to "tp_adm_m5_a3",
        "TP-ADM-012" to "tp_adm_m6_a1",
        "TP-ADM-013" to "tp_adm_m7_a1",
        "TP-ADM-014" to "tp_adm_m8_a1",
        "TP-ADM-015" to "tp_adm_m8_a2",
        "TP-ADM-016" to "tp_adm_m4_a1",
        "TP-NEW-001" to "tp_adm_m1_a1",
        "TP-NEW-002" to "tp_adm_m1_a2",
        "TP-NEW-003" to "tp_adm_m1_a2",
        "TP-NEW-004" to "tp_adm_m1_a3",
        "TP-NEW-005" to "tp_adm_m1_a3",
        "TP-NEW-006" to "tp_adm_m2_a5",
        "TP-NEW-007" to "tp_adm_m2_a1",
        "TP-NEW-008" to "tp_adm_m2_a4",
        "TP-NEW-009" to "tp_adm_m2_a3",
        "TP-NEW-010" to "tp_adm_m2_a4",
        "TP-NEW-011" to "tp_adm_m3_a1",
        "TP-NEW-012" to "tp_adm_m3_a3",
        "TP-NEW-013" to "tp_adm_m4_a1",
        "TP-NEW-014" to "tp_adm_m4_a2",
        "TP-NEW-015" to "tp_adm_m4_a3",
        "TP-NEW-016" to "tp_adm_m5_a1",
        "TP-NEW-017" to "tp_adm_m5_a3",
        "TP-NEW-018" to "tp_adm_m6_a2",
        "TP-NEW-019" to "tp_adm_m6_a3",
        "TP-NEW-020" to "tp_adm_m7_a3",
        "TP-NEW-021" to "tp_adm_m8_a1",
        "TP-NEW-022" to "tp_adm_m7_a2",
        "TP-NEW-023" to "tp_adm_m8_a3",
        "TP-NEW-024" to "tp_adm_m8_a3",
        "TP-NEW-025" to "tp_adm_m9_a1",

        // Finanças e Contabilidade (28)
        "TP-FIN-001" to "tp_fin_m1_a1",
        "TP-FIN-002" to "tp_fin_m1_a2",
        "TP-FIN-003" to "tp_fin_m1_a3",
        "TP-FIN-004" to "tp_fin_m2_a1",
        "TP-FIN-005" to "tp_fin_m3_a1",
        "TP-FIN-006" to "tp_fin_m3_a3",
        "TP-FIN-007" to "tp_fin_m3_a4",
        "TP-FIN-008" to "tp_fin_m1_a4",
        "TP-NEW-026" to "tp_fin_m1_a2",
        "TP-NEW-027" to "tp_fin_m1_a3",
        "TP-NEW-028" to "tp_fin_m1_a4",
        "TP-NEW-029" to "tp_fin_m2_a1",
        "TP-NEW-030" to "tp_fin_m2_a2",
        "TP-NEW-031" to "tp_fin_m3_a1",
        "TP-NEW-032" to "tp_fin_m3_a2",
        "TP-NEW-033" to "tp_fin_m3_a3",
        "TP-NEW-034" to "tp_fin_m3_a4",
        "TP-NEW-035" to "tp_fin_m3_a5",
        "TP-NEW-036" to "tp_fin_m3_a3",
        "TP-NEW-037" to "tp_fin_m2_a3",
        "TP-NEW-038" to "tp_fin_m2_a4",
        "TP-NEW-039" to "tp_fin_m4_a2",
        "TP-NEW-040" to "tp_fin_m4_a2",
        "TP-NEW-041" to "tp_fin_m4_a1",
        "TP-NEW-042" to "tp_fin_m1_a1",
        "TP-NEW-043" to "tp_fin_m1_a4",
        "TP-NEW-044" to "tp_fin_m3_a2",
        "TP-NEW-045" to "tp_fin_m3_a5",

        // Logística e Gestão da Cadeia de Suprimentos (34)
        "TP-LOG-001" to "tp_log_m1_a1",
        "TP-LOG-002" to "tp_log_m1_a2",
        "TP-LOG-003" to "tp_log_m1_a4",
        "TP-LOG-004" to "tp_log_m2_a1",
        "TP-LOG-005" to "tp_log_m6_a1",
        "TP-LOG-006" to "tp_log_m5_a1",
        "TP-LOG-007" to "tp_log_m3_a1",
        "TP-LOG-008" to "tp_log_m4_a1",
        "TP-LOG-009" to "tp_log_m1_a5",
        "TP-NEW-046" to "tp_log_m1_a1",
        "TP-NEW-047" to "tp_log_m1_a2",
        "TP-NEW-048" to "tp_log_m1_a2",
        "TP-NEW-049" to "tp_log_m1_a3",
        "TP-NEW-050" to "tp_log_m1_a3",
        "TP-NEW-051" to "tp_log_m1_a4",
        "TP-NEW-052" to "tp_log_m1_a5",
        "TP-NEW-053" to "tp_log_m2_a1",
        "TP-NEW-054" to "tp_log_m2_a2",
        "TP-NEW-055" to "tp_log_m2_a3",
        "TP-NEW-056" to "tp_log_m5_a1",
        "TP-NEW-057" to "tp_log_m3_a1",
        "TP-NEW-058" to "tp_log_m3_a2",
        "TP-NEW-059" to "tp_log_m3_a3",
        "TP-NEW-060" to "tp_log_m4_a1",
        "TP-NEW-061" to "tp_log_m4_a3",
        "TP-NEW-062" to "tp_log_m4_a2",
        "TP-NEW-063" to "tp_log_m5_a2",
        "TP-NEW-064" to "tp_log_m5_a3",
        "TP-NEW-065" to "tp_log_m5_a2",
        "TP-NEW-066" to "tp_log_m6_a1",
        "TP-NEW-067" to "tp_log_m2_a2",
        "TP-NEW-068" to "tp_log_m5_a1",
        "TP-NEW-069" to "tp_log_m1_a3",
        "TP-NEW-070" to "tp_log_m4_a3",

        // Noções de Informática (22)
        "TP-INF-001" to "tp_inf_m2_a1",
        "TP-INF-002" to "tp_inf_m2_a2",
        "TP-INF-003" to "tp_inf_m1_a2",
        "TP-INF-004" to "tp_inf_m5_a1",
        "TP-INF-005" to "tp_inf_m5_a2",
        "TP-INF-006" to "tp_inf_m5_a3",
        "TP-INF-007" to "tp_inf_m4_a2",
        "TP-NEW-071" to "tp_inf_m2_a1",
        "TP-NEW-072" to "tp_inf_m2_a2",
        "TP-NEW-073" to "tp_inf_m2_a3",
        "TP-NEW-074" to "tp_inf_m2_a3",
        "TP-NEW-075" to "tp_inf_m2_a4",
        "TP-NEW-076" to "tp_inf_m2_a2",
        "TP-NEW-077" to "tp_inf_m1_a1",
        "TP-NEW-078" to "tp_inf_m1_a2",
        "TP-NEW-079" to "tp_inf_m4_a1",
        "TP-NEW-080" to "tp_inf_m4_a2",
        "TP-NEW-081" to "tp_inf_m5_a1",
        "TP-NEW-082" to "tp_inf_m5_a2",
        "TP-NEW-083" to "tp_inf_m3_a1",
        "TP-NEW-084" to "tp_inf_m5_a3",
        "TP-NEW-085" to "tp_inf_m4_a3",

        // Língua Portuguesa (25)
        "TP-POR-001" to "tp_por_m1_a1",
        "TP-POR-002" to "tp_por_m2_a2",
        "TP-POR-003" to "tp_por_m6_a2",
        "TP-POR-004" to "tp_por_m7_a1",
        "TP-POR-005" to "tp_por_m8_a1",
        "TP-POR-006" to "tp_por_m9_a1",
        "TP-POR-007" to "tp_por_m4_a2",
        "TP-POR-008" to "tp_por_m11_a1",
        "TP-POR-009" to "tp_por_m3_a1",
        "TP-POR-010" to "tp_por_m2_a1",
        "TP-NEW-086" to "tp_por_m1_a1",
        "TP-NEW-087" to "tp_por_m1_a2",
        "TP-NEW-088" to "tp_por_m2_a1",
        "TP-NEW-089" to "tp_por_m6_a1",
        "TP-NEW-090" to "tp_por_m7_a2",
        "TP-NEW-091" to "tp_por_m8_a2",
        "TP-NEW-092" to "tp_por_m9_a2",
        "TP-NEW-093" to "tp_por_m4_a2",
        "TP-NEW-094" to "tp_por_m2_a2",
        "TP-NEW-095" to "tp_por_m11_a1",
        "TP-NEW-096" to "tp_por_m4_a1",
        "TP-NEW-097" to "tp_por_m3_a1",
        "TP-NEW-098" to "tp_por_m3_a2",
        "TP-NEW-099" to "tp_por_m5_a1",
        "TP-NEW-100" to "tp_por_m10_a1",

        // Matemática (30)
        "TP-MAT-001" to "tp_mat_m2_a1",
        "TP-MAT-002" to "tp_mat_m2_a2",
        "TP-MAT-003" to "tp_mat_m3_a1",
        "TP-MAT-004" to "tp_mat_m1_a1",
        "TP-MAT-005" to "tp_mat_m4_a1",
        "TP-MAT-006" to "tp_mat_m10_a2",
        "TP-MAT-007" to "tp_mat_m10_a1",
        "TP-MAT-008" to "tp_mat_m12_a1",
        "TP-MAT-009" to "tp_mat_m9_a2",
        "TP-MAT-010" to "tp_mat_m11_a1",
        "TP-NEW-101" to "tp_mat_m8_a1",
        "TP-NEW-102" to "tp_mat_m8_a2",
        "TP-NEW-103" to "tp_mat_m4_a2",
        "TP-NEW-104" to "tp_mat_m4_a3",
        "TP-NEW-105" to "tp_mat_m5_a1",
        "TP-NEW-106" to "tp_mat_m5_a2",
        "TP-NEW-107" to "tp_mat_m3_a1",
        "TP-NEW-108" to "tp_mat_m3_a2",
        "TP-NEW-109" to "tp_mat_m2_a1",
        "TP-NEW-110" to "tp_mat_m2_a2",
        "TP-NEW-111" to "tp_mat_m1_a2",
        "TP-NEW-112" to "tp_mat_m9_a2",
        "TP-NEW-113" to "tp_mat_m9_a2",
        "TP-NEW-114" to "tp_mat_m9_a1",
        "TP-NEW-115" to "tp_mat_m10_a1",
        "TP-NEW-116" to "tp_mat_m10_a2",
        "TP-NEW-117" to "tp_mat_m11_a2",
        "TP-NEW-118" to "tp_mat_m11_a3",
        "TP-NEW-119" to "tp_mat_m11_a1",
        "TP-NEW-120" to "tp_mat_m12_a2"
    )
}
