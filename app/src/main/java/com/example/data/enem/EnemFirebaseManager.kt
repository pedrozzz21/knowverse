package com.example.data.enem

import android.content.Context
import android.util.Log
import com.example.data.local.UserProgressRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

/**
 * Registro de submissão de resposta do ENEM.
 */
data class EnemAnswerSubmission(
    val questionId: String,
    val year: Int,
    val topic: String,
    val subtopic: String,
    val selectedAlternative: String,
    val isCorrect: Boolean,
    val timestamp: Long = System.currentTimeMillis(),
    val xpEarned: Int = if (isCorrect) 45 else 10
)

/**
 * Métricas agregadas por Tópico da Taxonomia ENEM.
 */
data class TopicPerformance(
    val topic: String,
    val totalAnswered: Int,
    val correctCount: Int
) {
    val accuracy: Float
        get() = if (totalAnswered > 0) (correctCount.toFloat() / totalAnswered.toFloat()) * 100f else 0f
}

/**
 * Estatísticas globais do usuário para o ENEM.
 */
data class EnemUserStats(
    val totalAnswered: Int = 0,
    val totalCorrect: Int = 0,
    val totalXp: Int = 0,
    val topicStats: Map<String, TopicPerformance> = emptyMap()
) {
    val accuracyPercent: Float
        get() = if (totalAnswered > 0) (totalCorrect.toFloat() / totalAnswered.toFloat()) * 100f else 0f
}

/**
 * Gerenciador de Sincronização e Telemetria no Firebase para o Banco de Questões do ENEM.
 * Suporta funcionamento 100% Offline com sincronização bidirecional no Firestore quando conectado.
 */
class EnemFirebaseManager private constructor(
    private val context: Context,
    private val progressRepository: UserProgressRepository
) {
    private val scope = CoroutineScope(Dispatchers.IO)
    private val prefs = context.getSharedPreferences("enem_telemetry_prefs", Context.MODE_PRIVATE)

    private val auth by lazy { FirebaseAuth.getInstance() }
    private val firestore by lazy {
        try {
            FirebaseFirestore.getInstance()
        } catch (e: Exception) {
            Log.w(TAG, "Firestore not initialized: ${e.message}")
            null
        }
    }

    private val _answeredQuestions = MutableStateFlow<Map<String, EnemAnswerSubmission>>(emptyMap())
    val answeredQuestions: StateFlow<Map<String, EnemAnswerSubmission>> = _answeredQuestions.asStateFlow()

    private val _userStats = MutableStateFlow(EnemUserStats())
    val userStats: StateFlow<EnemUserStats> = _userStats.asStateFlow()

    init {
        loadLocalHistory()
        syncWithFirestoreIfAvailable()
    }

    /**
     * Carrega histórico local em cache (offline-first).
     */
    private fun loadLocalHistory() {
        val uid = currentUserId
        val key = "enem_history_$uid"
        val raw = prefs.getString(key, null) ?: return

        try {
            val jsonArray = JSONArray(raw)
            val map = mutableMapOf<String, EnemAnswerSubmission>()

            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val sub = EnemAnswerSubmission(
                    questionId = obj.getString("questionId"),
                    year = obj.optInt("year", 2023),
                    topic = obj.optString("topic", "Matemática Básica"),
                    subtopic = obj.optString("subtopic", ""),
                    selectedAlternative = obj.optString("selectedAlternative", "A"),
                    isCorrect = obj.optBoolean("isCorrect", false),
                    timestamp = obj.optLong("timestamp", System.currentTimeMillis()),
                    xpEarned = obj.optInt("xpEarned", 0)
                )
                map[sub.questionId] = sub
            }
            _answeredQuestions.value = map
            computeStats(map.values)
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao decodificar histórico local: ${e.message}")
        }
    }

    /**
     * Salva uma submissão de resposta (Offline-First + Firebase Cloud Sync).
     */
    fun recordAnswer(
        question: EnemQuestion,
        selectedAlternative: String,
        isCorrect: Boolean,
        onComplete: ((EnemAnswerSubmission) -> Unit)? = null
    ) {
        val xp = if (isCorrect) 45 else 10
        val submission = EnemAnswerSubmission(
            questionId = question.id,
            year = question.year,
            topic = question.topic,
            subtopic = question.subtopic,
            selectedAlternative = selectedAlternative,
            isCorrect = isCorrect,
            timestamp = System.currentTimeMillis(),
            xpEarned = xp
        )

        // 1. Atualização em memória
        val updated = _answeredQuestions.value.toMutableMap()
        updated[question.id] = submission
        _answeredQuestions.value = updated
        computeStats(updated.values)

        // 2. Persistência Offline local (SharedPreferences)
        saveLocalHistory(updated.values.toList())

        // 3. Atualizar XP geral no Knowverse
        progressRepository.recordCheckIn()
        progressRepository.addBonusXp(xp)

        // 4. Sincronização em nuvem no Firestore
        syncSubmissionToFirestore(submission)

        onComplete?.invoke(submission)
    }

    private fun computeStats(submissions: Collection<EnemAnswerSubmission>) {
        val total = submissions.size
        val correct = submissions.count { it.isCorrect }
        val xp = submissions.sumOf { it.xpEarned }

        val topicMap = mutableMapOf<String, Pair<Int, Int>>() // topic -> (total, correct)
        for (s in submissions) {
            val curr = topicMap.getOrDefault(s.topic, Pair(0, 0))
            topicMap[s.topic] = Pair(curr.first + 1, curr.second + (if (s.isCorrect) 1 else 0))
        }

        val performanceMap = topicMap.mapValues { (topic, pair) ->
            TopicPerformance(
                topic = topic,
                totalAnswered = pair.first,
                correctCount = pair.second
            )
        }

        _userStats.value = EnemUserStats(
            totalAnswered = total,
            totalCorrect = correct,
            totalXp = xp,
            topicStats = performanceMap
        )
    }

    private fun saveLocalHistory(list: List<EnemAnswerSubmission>) {
        try {
            val uid = currentUserId
            val array = JSONArray()
            for (item in list) {
                val obj = JSONObject().apply {
                    put("questionId", item.questionId)
                    put("year", item.year)
                    put("topic", item.topic)
                    put("subtopic", item.subtopic)
                    put("selectedAlternative", item.selectedAlternative)
                    put("isCorrect", item.isCorrect)
                    put("timestamp", item.timestamp)
                    put("xpEarned", item.xpEarned)
                }
                array.put(obj)
            }
            prefs.edit().putString("enem_history_$uid", array.toString()).apply()
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao salvar histórico local: ${e.message}")
        }
    }

    /**
     * Sincroniza a submissão com o Firestore.
     */
    private fun syncSubmissionToFirestore(submission: EnemAnswerSubmission) {
        val db = firestore ?: return
        val uid = auth.currentUser?.uid ?: return

        scope.launch {
            try {
                // 1. Salvar na subcoleção do usuário /users/{uid}/enem_history/{questionId}
                val docData = hashMapOf(
                    "questionId" to submission.questionId,
                    "year" to submission.year,
                    "topic" to submission.topic,
                    "subtopic" to submission.subtopic,
                    "selectedAlternative" to submission.selectedAlternative,
                    "isCorrect" to submission.isCorrect,
                    "timestamp" to submission.timestamp,
                    "xpEarned" to submission.xpEarned,
                    "updatedAt" to FieldValue.serverTimestamp()
                )

                db.collection("users")
                    .document(uid)
                    .collection("enem_history")
                    .document(submission.questionId)
                    .set(docData, SetOptions.merge())

                // 2. Atualizar métricas consolidadas no documento raiz do usuário
                val stats = _userStats.value
                val topicSummaryMap = stats.topicStats.mapValues { (_, perf) ->
                    mapOf(
                        "total" to perf.totalAnswered,
                        "correct" to perf.correctCount,
                        "accuracy" to perf.accuracy
                    )
                }

                val userDocUpdate = hashMapOf(
                    "enemQuestionsAnswered" to stats.totalAnswered,
                    "enemCorrectAnswers" to stats.totalCorrect,
                    "enemAccuracyRate" to stats.accuracyPercent,
                    "enemTotalXp" to stats.totalXp,
                    "enemTopicStats" to topicSummaryMap,
                    "updatedAt" to FieldValue.serverTimestamp()
                )

                db.collection("users")
                    .document(uid)
                    .set(userDocUpdate, SetOptions.merge())

                Log.d(TAG, "Submissão ENEM sincronizada com Firestore com sucesso")
            } catch (e: Exception) {
                Log.w(TAG, "Falha na sincronização online (offline-mode ativo): ${e.message}")
            }
        }
    }

    /**
     * Baixa histórico e consolidados remotos do Firestore caso existam.
     */
    fun syncWithFirestoreIfAvailable() {
        val db = firestore ?: return
        val uid = auth.currentUser?.uid ?: return

        scope.launch {
            try {
                db.collection("users")
                    .document(uid)
                    .collection("enem_history")
                    .get()
                    .addOnSuccessListener { querySnapshot ->
                        if (!querySnapshot.isEmpty) {
                            val remoteMap = _answeredQuestions.value.toMutableMap()
                            for (doc in querySnapshot.documents) {
                                val qId = doc.getString("questionId") ?: doc.id
                                val isCorrect = doc.getBoolean("isCorrect") ?: false
                                val sub = EnemAnswerSubmission(
                                    questionId = qId,
                                    year = doc.getLong("year")?.toInt() ?: 2023,
                                    topic = doc.getString("topic") ?: "Matemática Básica",
                                    subtopic = doc.getString("subtopic") ?: "",
                                    selectedAlternative = doc.getString("selectedAlternative") ?: "A",
                                    isCorrect = isCorrect,
                                    timestamp = doc.getLong("timestamp") ?: System.currentTimeMillis(),
                                    xpEarned = doc.getLong("xpEarned")?.toInt() ?: if (isCorrect) 45 else 10
                                )
                                remoteMap[qId] = sub
                            }
                            _answeredQuestions.value = remoteMap
                            computeStats(remoteMap.values)
                            saveLocalHistory(remoteMap.values.toList())
                        }
                    }
            } catch (e: Exception) {
                Log.w(TAG, "Aviso ao buscar enem_history no Firestore: ${e.message}")
            }
        }
    }

    private val currentUserId: String
        get() = auth.currentUser?.uid ?: "local_student"

    companion object {
        private const val TAG = "EnemFirebaseManager"

        @Volatile
        private var INSTANCE: EnemFirebaseManager? = null

        fun getInstance(context: Context, progressRepo: UserProgressRepository): EnemFirebaseManager {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: EnemFirebaseManager(context.applicationContext, progressRepo).also { INSTANCE = it }
            }
        }
    }
}
