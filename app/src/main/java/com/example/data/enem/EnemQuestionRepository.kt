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
 * Repositório 100% Offline para o Banco de Questões do ENEM.
 * Lê e decodifica o arquivo estático `banco_questoes.json` localizado em assets.
 */
class EnemQuestionRepository private constructor(private val context: Context) {

    private val _questions = MutableStateFlow<List<EnemQuestion>>(emptyList())
    val questions: StateFlow<List<EnemQuestion>> = _questions.asStateFlow()

    private var isLoaded = false

    init {
        loadQuestionsSync()
    }

    /**
     * Carrega as questões de forma síncrona/imediata a partir do asset estático.
     */
    @Synchronized
    private fun loadQuestionsSync() {
        if (isLoaded && _questions.value.isNotEmpty()) return

        try {
            val jsonString = context.assets.open("banco_questoes.json").use { inputStream ->
                BufferedReader(InputStreamReader(inputStream)).use { it.readText() }
            }

            val parsed = parseJsonQuestions(jsonString)
            _questions.value = parsed
            isLoaded = true
            Log.d(TAG, "Carregadas com sucesso ${parsed.size} questões do ENEM do banco_questoes.json")
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao carregar banco_questoes.json: ${e.message}", e)
            _questions.value = emptyList()
        }
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
    private fun parseJsonQuestions(jsonString: String): List<EnemQuestion> {
        val list = mutableListOf<EnemQuestion>()
        val jsonArray = JSONArray(jsonString)

        for (i in 0 until jsonArray.length()) {
            val obj = jsonArray.getJSONObject(i)
            val id = obj.optString("id", "enem_${i}")
            val year = obj.optInt("year", 2023)
            val topic = obj.optString("topic", "Matemática Básica")
            val subtopic = obj.optString("subtopic", "")
            val title = obj.optString("title", "")
            val correctAnswer = obj.optString("correctAnswer", "A")

            val altArray = obj.optJSONArray("alternatives")
            val alternatives = mutableListOf<String>()
            if (altArray != null) {
                for (j in 0 until altArray.length()) {
                    alternatives.add(altArray.getString(j))
                }
            }

            list.add(
                EnemQuestion(
                    id = id,
                    year = year,
                    topic = topic,
                    subtopic = subtopic,
                    title = title,
                    alternatives = alternatives,
                    correctAnswer = correctAnswer
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
     * Filtra as questões com base em critérios de busca do estudante.
     */
    fun filterQuestions(
        topic: String? = null,
        subtopic: String? = null,
        year: Int? = null,
        searchQuery: String? = null
    ): List<EnemQuestion> {
        var result = getAllQuestions()

        if (!topic.isNullOrBlank() && topic != "all" && topic != "Todos") {
            result = result.filter { it.topic.equals(topic, ignoreCase = true) }
        }

        if (!subtopic.isNullOrBlank() && subtopic != "all" && subtopic != "Todos") {
            result = result.filter { it.subtopic.equals(subtopic, ignoreCase = true) }
        }

        if (year != null && year > 0) {
            result = result.filter { it.year == year }
        }

        if (!searchQuery.isNullOrBlank()) {
            val q = searchQuery.trim().lowercase()
            result = result.filter {
                it.title.lowercase().contains(q) ||
                        it.subtopic.lowercase().contains(q) ||
                        it.topic.lowercase().contains(q)
            }
        }

        return result
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
        subtopic: String? = null,
        year: Int? = null,
        searchQuery: String? = null
    ): List<UniversalExercise> {
        return filterQuestions(topic, subtopic, year, searchQuery).map { it.toUniversalExercise() }
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
