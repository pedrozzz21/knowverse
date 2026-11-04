package com.example.data.assessment

import com.squareup.moshi.Moshi

/**
 * Parser do formato JSON dos bancos (`assets/assessment/q_*.json`) para [Question].
 * Usa Moshi genérico (JVM puro) para poder ser exercitado em testes unitários sem Android.
 */
object AssessmentJson {

    private val anyAdapter by lazy { Moshi.Builder().build().adapter(Any::class.java) }

    @Suppress("UNCHECKED_CAST")
    private fun parseRoot(text: String): Any? = anyAdapter.fromJson(text.removePrefix("\uFEFF"))

    @Suppress("UNCHECKED_CAST")
    fun parseQuestions(text: String): List<Question> {
        val root = parseRoot(text) as? List<Any?> ?: error("Raiz do banco deve ser um array JSON")
        return root.map { toQuestion(it as Map<String, Any?>) }
    }

    @Suppress("UNCHECKED_CAST")
    fun parseLessonIndex(text: String): List<LessonRef> {
        val root = parseRoot(text) as? List<Any?> ?: error("lesson_index deve ser um array")
        return root.map {
            val m = it as Map<String, Any?>
            LessonRef(
                lessonId = m.str("lessonId"),
                subject = m.str("subject"),
                moduleId = m.str("moduleId"),
                moduleTitle = m.str("moduleTitle"),
                title = m.str("title"),
                subtopic = m.str("subtopic"),
                order = (m["order"] as? Double)?.toInt() ?: 0
            )
        }
    }

    private fun Map<String, Any?>.str(key: String, default: String = ""): String = (this[key] as? String) ?: default

    @Suppress("UNCHECKED_CAST")
    private fun Map<String, Any?>.strList(key: String): List<String> =
        (this[key] as? List<Any?>)?.map { it?.toString().orEmpty() } ?: emptyList()

    private fun Map<String, Any?>.num(key: String): Double? = (this[key] as? Double)

    @Suppress("UNCHECKED_CAST")
    private fun toQuestion(m: Map<String, Any?>): Question {
        val format = QuestionFormat.valueOf(m.str("format"))
        val rubric = (m["rubric"] as? List<Any?>)?.map { raw ->
            val c = raw as Map<String, Any?>
            RubricCriterion(
                criterion = c.str("criterion"),
                points = (c["points"] as? Double)?.toInt() ?: 1,
                keywords = (c["keywords"] as? List<Any?>)?.map { g ->
                    (g as List<Any?>).map { it.toString() }
                } ?: emptyList()
            )
        } ?: emptyList()
        val chart = (m["chart"] as? Map<String, Any?>)?.let { c ->
            ChartData(
                type = c.str("type", "table"),
                title = c.str("title"),
                labels = c.strList("labels"),
                series = (c["series"] as? List<Any?>)?.map { s ->
                    val sm = s as Map<String, Any?>
                    ChartSeries(
                        name = sm.str("name"),
                        values = (sm["values"] as? List<Any?>)?.map { (it as? Double) ?: 0.0 } ?: emptyList()
                    )
                } ?: emptyList(),
                unit = c.str("unit")
            )
        }
        val code = (m["code"] as? Map<String, Any?>)?.let { c ->
            CodeSpec(
                starter = c.str("starter"),
                reference = c.str("reference"),
                tests = (c["tests"] as? List<Any?>)?.map { t ->
                    val tm = t as Map<String, Any?>
                    CodeTestCase(tm.str("call"), tm.str("expected"), (tm["hidden"] as? Boolean) ?: false)
                } ?: emptyList()
            )
        }
        val pairs = (m["pairs"] as? List<Any?>)?.map { p ->
            val l = p as List<Any?>
            l[0].toString() to l[1].toString()
        } ?: emptyList()

        return Question(
            id = m.str("id"),
            lessonId = m.str("lessonId"),
            format = format,
            difficulty = QDifficulty.valueOf(m.str("difficulty")),
            skill = m.str("skill"),
            concept = m.str("concept"),
            statement = m.str("statement"),
            explanation = m.str("explanation"),
            retryGuidance = m.str("retryGuidance"),
            source = QuestionSource.AUTORAL,
            options = m.strList("options"),
            answerIndex = (m["answerIndex"] as? Double)?.toInt() ?: -1,
            distractorConcepts = m.strList("distractorConcepts"),
            answerBool = m["answerBool"] as? Boolean,
            pairs = pairs,
            answers = m.strList("answers"),
            answerNumber = m.num("answerNumber"),
            tolerance = m.num("tolerance") ?: 0.0,
            unit = m.str("unit"),
            chart = chart,
            rubric = rubric,
            modelAnswer = m.str("modelAnswer"),
            passScore = m.num("passScore") ?: 0.6,
            code = code
        )
    }
}

