package com.example.data.enem

import android.content.Context
import android.util.Log
import com.example.data.exercise.UniversalExercise
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.withContext
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Repositório 100% Offline para o Banco Oficial de Questões do ENEM (API enem-api).
 * Carrega e mapeia questões de Matemática, Ciências da Natureza (Física e Biologia),
 * Ciências Humanas (História) e Linguagens e Códigos por matéria e módulo.
 */
class EnemQuestionRepository private constructor(private val context: Context) {

    private val _questions = MutableStateFlow<List<EnemQuestion>>(emptyList())
    val questions: StateFlow<List<EnemQuestion>> = _questions.asStateFlow()

    private var isLoaded = false

    init {
        loadQuestionsSync()
    }

    /**
     * Carrega as questões de forma síncrona/imediata a partir dos assets estáticos.
     */
    @Synchronized
    private fun loadQuestionsSync() {
        if (isLoaded && _questions.value.isNotEmpty()) return

        val allParsed = mutableListOf<EnemQuestion>()
        val seenIds = mutableSetOf<String>()

        // 1. Carrega banco_questoes.json (Matemática e geral)
        try {
            val jsonString = context.assets.open("banco_questoes.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
            }
            val parsed = parseJsonQuestions(jsonString, "mat", seenIds)
            allParsed.addAll(parsed)
            Log.d(TAG, "Carregadas com sucesso ${parsed.size} questões do ENEM de banco_questoes.json")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao carregar banco_questoes.json: ${e.message}", e)
        }

        // 2. Carrega questoes_enem_natureza.json (Física e Biologia)
        try {
            val jsonNatureza = context.assets.open("questoes_enem_natureza.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
            }
            val parsedNatureza = parseJsonQuestions(jsonNatureza, "nat", seenIds)
            allParsed.addAll(parsedNatureza)
            Log.d(TAG, "Carregadas com sucesso ${parsedNatureza.size} questões de Natureza do ENEM (Física e Biologia)")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao carregar questoes_enem_natureza.json: ${e.message}", e)
        }

        // 3. Carrega questoes_enem_humanas.json (História e Ciências Humanas)
        try {
            val jsonHumanas = context.assets.open("questoes_enem_humanas.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
            }
            val parsedHumanas = parseJsonQuestions(jsonHumanas, "hum", seenIds)
            allParsed.addAll(parsedHumanas)
            Log.d(TAG, "Carregadas com sucesso ${parsedHumanas.size} questões de Humanas/História do ENEM")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao carregar questoes_enem_humanas.json: ${e.message}", e)
        }

        // 4. Carrega questoes_enem_linguagens.json (Linguagens, Códigos e Artes)
        try {
            val jsonLinguagens = context.assets.open("questoes_enem_linguagens.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
            }
            val parsedLinguagens = parseJsonQuestions(jsonLinguagens, "ling", seenIds)
            allParsed.addAll(parsedLinguagens)
            Log.d(TAG, "Carregadas com sucesso ${parsedLinguagens.size} questões de Linguagens do ENEM")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao carregar questoes_enem_linguagens.json: ${e.message}", e)
        }

        // 5. Carrega questoes_enem_geografia.json (Geografia e Ciências Humanas)
        try {
            val jsonGeografia = context.assets.open("questoes_enem_geografia.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
            }
            val parsedGeografia = parseJsonQuestions(jsonGeografia, "geo", seenIds)
            allParsed.addAll(parsedGeografia)
            Log.d(TAG, "Carregadas com sucesso ${parsedGeografia.size} questões de Geografia do ENEM")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao carregar questoes_enem_geografia.json: ${e.message}", e)
        }

        // 6. Carrega questoes_enem_quimica.json (Química e Ciências da Natureza)
        try {
            val jsonQuimica = context.assets.open("questoes_enem_quimica.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
            }
            val parsedQuimica = parseJsonQuestions(jsonQuimica, "quim", seenIds)
            allParsed.addAll(parsedQuimica)
            Log.d(TAG, "Carregadas com sucesso ${parsedQuimica.size} questões de Química do ENEM")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao carregar questoes_enem_quimica.json: ${e.message}", e)
        }

        // 7. Carrega questoes_apostila.json (Apostilas Edcley ENEM 2026 com gabaritos oficiais e comentários)
        try {
            val jsonApostila = context.assets.open("questoes_apostila.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
            }
            val parsedApostila = parseJsonQuestions(jsonApostila, "apostila", seenIds)
            allParsed.addAll(parsedApostila)
            Log.d(TAG, "Carregadas com sucesso ${parsedApostila.size} questões das apostilas ENEM 2026")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao carregar questoes_apostila.json: ${e.message}", e)
        }

        _questions.value = allParsed
        isLoaded = true
    }

    /**
     * Recarrega as questões assincronamente em Dispatchers.IO.
     */
    suspend fun reloadQuestionsAsync(): List<EnemQuestion> = withContext(Dispatchers.IO) {
        loadQuestionsSync()
        _questions.value
    }

    /**
     * Faz o parse seguro do array JSON com tratamento para possíveis variações de tipos.
     */
    private fun parseJsonQuestions(
        jsonString: String,
        sourceTag: String = "enem",
        seenIds: MutableSet<String> = mutableSetOf()
    ): List<EnemQuestion> {
        val list = mutableListOf<EnemQuestion>()
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val rawId = obj.optString("id", "${sourceTag}_$i")
            val id = if (seenIds.add(rawId)) {
                rawId
            } else {
                var attempt = 2
                var candidate = "${rawId}_${sourceTag}_$attempt"
                while (!seenIds.add(candidate)) {
                    attempt++
                    candidate = "${rawId}_${sourceTag}_$attempt"
                }
                candidate
            }
            val year = obj.optInt("year", 2023)
            val topic = when {
                obj.has("disciplina") -> obj.optString("disciplina", "Ciências do ENEM")
                obj.has("topic") -> obj.optString("topic", "Matemática Básica")
                else -> "Ciências do ENEM"
            }
            val subtopic = obj.optString("subtopic", topic)
            val title = when {
                obj.has("statement") -> obj.optString("statement")
                obj.has("title") -> obj.optString("title")
                obj.has("problemStatement") -> obj.optString("problemStatement")
                else -> ""
            }

            val altArray = obj.optJSONArray("options") ?: obj.optJSONArray("alternatives")
            val alternatives = mutableListOf<String>()
            if (altArray != null) {
                for (j in 0 until altArray.length()) {
                    alternatives.add(altArray.getString(j))
                }
            }

            val correctAnswer = when {
                obj.has("correctLetter") -> obj.optString("correctLetter", "A")
                obj.has("correctAnswer") -> obj.optString("correctAnswer", "A")
                obj.has("correctIndex") -> {
                    val idx = obj.optInt("correctIndex", 0)
                    ('A' + idx).toString()
                }
                else -> "A"
            }

            val explanation = obj.optString("explanation", "")
            val moduleId = obj.optString("moduleId", "")
            val moduleTitle = obj.optString("moduleTitle", "")
            val difficulty = when {
                obj.has("difficulty") -> {
                    val raw = obj.opt("difficulty")
                    when (raw) {
                        is Int -> when (raw) {
                            1 -> "Fácil"
                            2 -> "Média"
                            3 -> "Difícil"
                            else -> "Média"
                        }
                        is String -> EnemQuestion.normalizeDifficulty(raw)
                        else -> "Média"
                    }
                }
                else -> "Média"
            }

            list.add(
                EnemQuestion(
                    id = id,
                    year = year,
                    topic = topic,
                    subtopic = subtopic,
                    title = title,
                    alternatives = alternatives,
                    correctAnswer = correctAnswer,
                    explanation = explanation,
                    moduleId = moduleId,
                    moduleTitle = moduleTitle,
                    difficulty = difficulty
                )
            )
        }
        return list
    }

    /**
     * Retorna todas as questões carregadas.
     */
    fun getAllQuestions(): List<EnemQuestion> {
        if (_questions.value.isEmpty()) loadQuestionsSync()
        return _questions.value
    }

    /**
     * Retorna questões de um módulo específico (ex: "mod_hist_01_colonia", "mod_mat_01_basica").
     */
    fun getQuestionsByModule(moduleId: String): List<EnemQuestion> {
        return getAllQuestions().filter { it.moduleId.equals(moduleId, ignoreCase = true) }
    }

    /**
     * Retorna questões por matéria/disciplina (ex: "História", "Física", "Biologia", "Matemática", "Linguagens").
     */
    fun getQuestionsByDiscipline(discipline: String): List<EnemQuestion> {
        val d = discipline.lowercase()
        return getAllQuestions().filter {
            it.topic.lowercase().contains(d) ||
            it.subtopic.lowercase().contains(d) ||
            it.moduleTitle.lowercase().contains(d)
        }
    }

    /**
     * Filtra as questões com base em critérios de busca do estudante:
     * matéria, módulo, subtópico, ano, dificuldade e pesquisa textual.
     */
    fun filterQuestions(
        topic: String? = null,
        moduleId: String? = null,
        subtopic: String? = null,
        year: Int? = null,
        searchQuery: String? = null,
        difficulty: String? = null,
        discipline: String? = null
    ): List<EnemQuestion> {
        var result = getAllQuestions()

        if (!discipline.isNullOrBlank() && discipline != "all" && discipline != "Todos") {
            val d = discipline.lowercase()
            result = result.filter {
                it.topic.lowercase().contains(d) ||
                it.subtopic.lowercase().contains(d) ||
                it.moduleTitle.lowercase().contains(d)
            }
        }

        if (!moduleId.isNullOrBlank() && moduleId != "all" && moduleId != "Todos") {
            result = result.filter { it.moduleId.equals(moduleId, ignoreCase = true) }
        }

        if (!topic.isNullOrBlank() && topic != "all" && topic != "Todos") {
            result = result.filter { it.topic.equals(topic, ignoreCase = true) }
        }

        if (!subtopic.isNullOrBlank() && subtopic != "all" && subtopic != "Todos") {
            result = result.filter {
                it.subtopic.equals(subtopic, ignoreCase = true) ||
                it.moduleTitle.contains(subtopic, ignoreCase = true)
            }
        }

        if (year != null && year > 0) {
            result = result.filter { it.year == year }
        }

        if (!difficulty.isNullOrBlank() && difficulty != "all" && difficulty != "Todos") {
            val norm = EnemQuestion.normalizeDifficulty(difficulty)
            result = result.filter { it.difficulty.equals(norm, ignoreCase = true) }
        }

        if (!searchQuery.isNullOrBlank()) {
            val q = searchQuery.trim().lowercase()
            result = result.filter {
                it.title.lowercase().contains(q) ||
                it.subtopic.lowercase().contains(q) ||
                it.topic.lowercase().contains(q) ||
                it.moduleTitle.lowercase().contains(q) ||
                it.explanation.lowercase().contains(q)
            }
        }

        return result
    }

    /**
     * Retorna a contagem de questões mapeadas por ID de módulo.
     */
    fun getModuleCounts(): Map<String, Int> {
        val counts = mutableMapOf<String, Int>()
        for (q in getAllQuestions()) {
            if (q.moduleId.isNotBlank()) {
                counts[q.moduleId] = (counts[q.moduleId] ?: 0) + 1
            }
        }
        return counts
    }

    /**
     * Retorna a lista dos anos disponíveis no banco de dados.
     */
    fun getAvailableYears(): List<Int> {
        return getAllQuestions().map { it.year }.distinct().sortedDescending()
    }

    /**
     * Converte as questões do ENEM para o formato de UniversalExercise para exibição na UI.
     */
    fun getAsUniversalExercises(
        topic: String? = null,
        moduleId: String? = null,
        subtopic: String? = null,
        year: Int? = null,
        searchQuery: String? = null,
        difficulty: String? = null,
        discipline: String? = null
    ): List<UniversalExercise> {
        return filterQuestions(
            topic = topic,
            moduleId = moduleId,
            subtopic = subtopic,
            year = year,
            searchQuery = searchQuery,
            difficulty = difficulty,
            discipline = discipline
        ).map { it.toUniversalExercise() }
    }

    /**
     * Retorna uma questão específica por ID.
     */
    fun getQuestionById(id: String): EnemQuestion? {
        return getAllQuestions().find { it.id == id }
    }

    companion object {
        private const val TAG = "EnemQuestionRepo"

        @Volatile
        private var INSTANCE: EnemQuestionRepository? = null

        fun getInstance(context: Context): EnemQuestionRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: EnemQuestionRepository(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
