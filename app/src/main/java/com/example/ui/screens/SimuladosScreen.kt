package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Assessment
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.FilterList
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material.icons.filled.Shuffle
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.assessment.AnswerEvaluator
import com.example.data.assessment.BankQuestion
import com.example.data.assessment.Feedback
import com.example.data.assessment.QDifficulty
import com.example.data.assessment.QuestionBankRepository
import com.example.data.assessment.QuestionFormat
import com.example.data.assessment.SimuladoConfig
import com.example.data.assessment.SimuladoMode
import com.example.data.assessment.SpecificExamPreset
import com.example.data.assessment.UserAnswer
import com.example.data.assessment.Verdict
import com.example.data.local.UserProgressRepository
import com.example.data.python.PythonEngine
import com.example.ui.components.QuestionRendererCard
import com.example.ui.components.TopUserStatsBar
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.BorderActive
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.PrimaryVioletLight
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.theme.WarningAmber
import android.content.Context
import com.squareup.moshi.Moshi
import kotlin.math.roundToInt

internal enum class SimuladoScreenState {
    CONFIGURATOR,
    RUNNING_EXAM,
    DIAGNOSTIC_REPORT,
    LESSON_BANK_EXPLORER
}

/**
 * Armazenamento resiliente de sessão de Simulado:
 * mantém respostas em rascunho (`answerMap`), avaliações (`feedbackMap`), questões geradas,
 * questão atual e cronômetro tanto em memória quanto em SharedPreferences para impedir
 * qualquer perda de respostas ao trocar de aba, girar a tela ou concluir a prova.
 */
internal object SimuladoSessionStore {
    private const val PREFS_NAME = "knowverse_simulado_session"
    private const val KEY_SESSION_JSON = "active_simulado_json"

    var screenState: SimuladoScreenState = SimuladoScreenState.CONFIGURATOR
    var activePreset: SpecificExamPreset = SpecificExamPreset.CUSTOM
    var generatedQuestionIds: List<String> = emptyList()
    var currentQuestionIdx: Int = 0
    var examElapsedSeconds: Int = 0
    val answerMap: MutableMap<String, UserAnswer> = LinkedHashMap()
    val feedbackMap: MutableMap<String, Feedback> = LinkedHashMap()
    private var loadedFromPrefs: Boolean = false

    fun ensureRestored(context: Context?) {
        if (loadedFromPrefs || context == null) return
        loadedFromPrefs = true
        try {
            val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val raw = prefs.getString(KEY_SESSION_JSON, null) ?: return
            restoreFromJsonString(raw)
        } catch (_: Exception) {
        }
    }

    fun persist(context: Context?) {
        if (context == null) return
        try {
            val prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(KEY_SESSION_JSON, toJsonString()).apply()
        } catch (_: Exception) {
        }
    }

    fun finalizePendingAnswers(
        questions: List<BankQuestion>,
        answers: Map<String, UserAnswer>,
        feedbacks: MutableMap<String, Feedback>
    ) {
        for (bq in questions) {
            if (feedbacks[bq.id] == null) {
                val draft = answers[bq.id]
                if (draft != null) {
                    feedbacks[bq.id] = AnswerEvaluator.evaluate(bq.question, draft)
                }
            }
        }
    }

    private val anyAdapter by lazy { Moshi.Builder().build().adapter(Any::class.java) }

    fun toJsonString(): String {
        return try {
            val answersMap = LinkedHashMap<String, Any>()
            for ((qId, ans) in answerMap) {
                val aObj = when (ans) {
                    is UserAnswer.Choice -> mapOf("type" to "CHOICE", "index" to ans.index)
                    is UserAnswer.Bool -> mapOf("type" to "BOOL", "value" to ans.value)
                    is UserAnswer.Text -> mapOf("type" to "TEXT", "text" to ans.value)
                    is UserAnswer.Pairs -> mapOf("type" to "PAIRS", "pairs" to LinkedHashMap(ans.mapping))
                }
                answersMap[qId] = aObj
            }

            val feedbacksMap = LinkedHashMap<String, Any>()
            for ((qId, fb) in feedbackMap) {
                val safeDetails: List<String> = when (val d = fb.details) {
                    is Collection<*> -> d.map { it?.toString().orEmpty() }
                    null -> emptyList()
                    else -> listOf(d.toString())
                }
                feedbacksMap[qId] = mapOf(
                    "verdict" to fb.verdict.name,
                    "score" to fb.score,
                    "headline" to fb.headline,
                    "reasoning" to fb.reasoning,
                    "misunderstoodConcept" to (fb.misunderstoodConcept ?: ""),
                    "retryTip" to (fb.retryTip ?: ""),
                    "details" to safeDetails
                )
            }

            val root = mapOf(
                "screenState" to screenState.name,
                "activePreset" to activePreset.name,
                "currentQuestionIdx" to currentQuestionIdx,
                "examElapsedSeconds" to examElapsedSeconds,
                "generatedQuestionIds" to generatedQuestionIds,
                "answers" to answersMap,
                "feedbacks" to feedbacksMap
            )
            anyAdapter.toJson(root)
        } catch (e: Exception) {
            android.util.Log.e("SimuladoSession", "Erro ao serializar sessão: ${e.message}")
            "{}"
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun restoreFromJsonString(raw: String) {
        val root = anyAdapter.fromJson(raw) as? Map<String, Any?> ?: return
        val stateName = root["screenState"] as? String ?: SimuladoScreenState.CONFIGURATOR.name
        screenState = try {
            SimuladoScreenState.valueOf(stateName)
        } catch (_: Exception) {
            SimuladoScreenState.CONFIGURATOR
        }
        val presetName = root["activePreset"] as? String ?: SpecificExamPreset.CUSTOM.name
        activePreset = try {
            SpecificExamPreset.valueOf(presetName)
        } catch (_: Exception) {
            SpecificExamPreset.CUSTOM
        }
        currentQuestionIdx = ((root["currentQuestionIdx"] as? Number)?.toInt() ?: 0).coerceAtLeast(0)
        examElapsedSeconds = ((root["examElapsedSeconds"] as? Number)?.toInt() ?: 0).coerceAtLeast(0)

        generatedQuestionIds = (root["generatedQuestionIds"] as? List<Any?>)
            ?.mapNotNull { (it as? String)?.takeIf { s -> s.isNotBlank() } }
            ?: emptyList()

        answerMap.clear()
        val answersObj = root["answers"] as? Map<String, Any?>
        if (answersObj != null) {
            for ((qId, rawAns) in answersObj) {
                val aObj = rawAns as? Map<String, Any?> ?: continue
                val parsed: UserAnswer? = when (aObj["type"] as? String) {
                    "CHOICE" -> UserAnswer.Choice((aObj["index"] as? Number)?.toInt() ?: 0)
                    "BOOL" -> UserAnswer.Bool(aObj["value"] as? Boolean ?: false)
                    "TEXT" -> UserAnswer.Text(aObj["text"] as? String ?: "")
                    "PAIRS" -> {
                        val pObj = aObj["pairs"] as? Map<String, Any?>
                        val map = LinkedHashMap<String, String>()
                        pObj?.forEach { (k, v) -> map[k] = v?.toString().orEmpty() }
                        UserAnswer.Pairs(map)
                    }
                    else -> null
                }
                if (parsed != null) answerMap[qId] = parsed
            }
        }

        feedbackMap.clear()
        val feedbacksObj = root["feedbacks"] as? Map<String, Any?>
        if (feedbacksObj != null) {
            for ((qId, rawFb) in feedbacksObj) {
                val fObj = rawFb as? Map<String, Any?> ?: continue
                val verdictName = fObj["verdict"] as? String ?: Verdict.INCORRECT.name
                val verdict = try {
                    Verdict.valueOf(verdictName)
                } catch (_: Exception) {
                    Verdict.INCORRECT
                }
                val detailsList = (fObj["details"] as? List<Any?>)?.map { it?.toString().orEmpty() } ?: emptyList()
                feedbackMap[qId] = Feedback(
                    verdict = verdict,
                    score = (fObj["score"] as? Number)?.toDouble() ?: 0.0,
                    headline = fObj["headline"] as? String ?: "",
                    reasoning = fObj["reasoning"] as? String ?: "",
                    misunderstoodConcept = fObj["misunderstoodConcept"] as? String ?: "",
                    retryTip = fObj["retryTip"] as? String ?: "",
                    details = detailsList
                )
            }
        }
    }
}

/**
 * Aba exclusiva para desenvolvimento e realização de Simulados (Etapa 5):
 * - Escolha de matéria/disciplina.
 * - Avaliações por módulo (misturando conteúdos ensinados no módulo).
 * - Avaliações cumulativas (exigindo recuperação ativa de módulos e aulas anteriores).
 * - Filtro por subtópico das aulas (ex.: Eq. do 2º Grau, Citologia, etc.).
 * - Escolha da quantidade de questões (1 a 45 — máx. 45) com geração aleatória balanceada.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SimuladosScreen(
    progressRepository: UserProgressRepository,
    pythonEngine: PythonEngine? = null,
    userPhotoUrl: String? = null,
    onSimuladoRunningChange: (Boolean) -> Unit = {}
) {
    val context = LocalContext.current
    val userProgress by progressRepository.progressState.collectAsState()

    LaunchedEffect(Unit) {
        QuestionBankRepository.ensureLoaded(context)
        SimuladoSessionStore.ensureRestored(context)
    }

    val subjects = remember { listOf("Todas") + QuestionBankRepository.getSubjects(context) }

    var screenState by rememberSaveable {
        SimuladoSessionStore.ensureRestored(context)
        mutableStateOf(SimuladoSessionStore.screenState)
    }
    var selectedPreset by rememberSaveable {
        SimuladoSessionStore.ensureRestored(context)
        mutableStateOf(SimuladoSessionStore.activePreset)
    }
    var showExitDialog by rememberSaveable { mutableStateOf(false) }
    var selectedSubject by rememberSaveable { mutableStateOf("Todas") }
    var selectedMode by rememberSaveable { mutableStateOf(SimuladoMode.BY_SUBJECT) }
    var selectedModuleId by rememberSaveable { mutableStateOf<String?>(null) }
    var selectedSubtopic by rememberSaveable { mutableStateOf<String?>(null) }
    var questionCountSlider by rememberSaveable { mutableFloatStateOf(15f) }
    var selectedFormatFilter by rememberSaveable { mutableStateOf<QuestionFormat?>(null) }
    var selectedDifficultyFilter by rememberSaveable { mutableStateOf<QDifficulty?>(null) }

    val availableModules = remember(selectedSubject) {
        QuestionBankRepository.getModulesForSubject(selectedSubject, context)
    }
    val availableSubtopics = remember(selectedSubject, selectedModuleId) {
        QuestionBankRepository.getSubtopicsForSubject(selectedSubject, selectedModuleId, context)
    }

    LaunchedEffect(selectedSubject) {
        if (selectedModuleId == null || availableModules.none { it.moduleId == selectedModuleId }) {
            selectedModuleId = availableModules.firstOrNull()?.moduleId
        }
        if (selectedSubtopic == null || selectedSubtopic !in availableSubtopics) {
            selectedSubtopic = availableSubtopics.firstOrNull()
        }
    }

    var generatedQuestionIds by rememberSaveable {
        SimuladoSessionStore.ensureRestored(context)
        mutableStateOf(SimuladoSessionStore.generatedQuestionIds)
    }
    var cachedGeneratedQuestions by remember { mutableStateOf<List<BankQuestion>>(emptyList()) }
    var examElapsedSeconds by rememberSaveable {
        SimuladoSessionStore.ensureRestored(context)
        mutableStateOf(SimuladoSessionStore.examElapsedSeconds)
    }

    LaunchedEffect(screenState) {
        onSimuladoRunningChange(screenState == SimuladoScreenState.RUNNING_EXAM)
        SimuladoSessionStore.screenState = screenState
        SimuladoSessionStore.persist(context)
        while (screenState == SimuladoScreenState.RUNNING_EXAM) {
            kotlinx.coroutines.delay(1000L)
            examElapsedSeconds += 1
            SimuladoSessionStore.examElapsedSeconds = examElapsedSeconds
        }
    }

    // Intercepta botão Voltar do sistema durante simulado ativo para exigir confirmação
    BackHandler(enabled = screenState == SimuladoScreenState.RUNNING_EXAM) {
        showExitDialog = true
    }
    val generatedQuestions: List<BankQuestion> = remember(generatedQuestionIds, cachedGeneratedQuestions) {
        if (cachedGeneratedQuestions.isNotEmpty() && cachedGeneratedQuestions.map { it.id } == generatedQuestionIds) {
            cachedGeneratedQuestions
        } else if (generatedQuestionIds.isNotEmpty()) {
            val byId = QuestionBankRepository.getAllQuestions(includeLegacy = true, context = context).associateBy { it.id }
            generatedQuestionIds.mapNotNull { byId[it] }
        } else {
            emptyList()
        }
    }
    var currentQuestionIdx by rememberSaveable {
        SimuladoSessionStore.ensureRestored(context)
        mutableIntStateOf(SimuladoSessionStore.currentQuestionIdx)
    }
    val feedbackMap = remember {
        SimuladoSessionStore.ensureRestored(context)
        mutableStateMapOf<String, Feedback>().apply { putAll(SimuladoSessionStore.feedbackMap) }
    }
    val answerMap = remember {
        SimuladoSessionStore.ensureRestored(context)
        mutableStateMapOf<String, UserAnswer>().apply { putAll(SimuladoSessionStore.answerMap) }
    }

    fun syncSessionStore() {
        SimuladoSessionStore.screenState = screenState
        SimuladoSessionStore.activePreset = selectedPreset
        SimuladoSessionStore.generatedQuestionIds = generatedQuestionIds
        SimuladoSessionStore.currentQuestionIdx = currentQuestionIdx
        SimuladoSessionStore.examElapsedSeconds = examElapsedSeconds
        SimuladoSessionStore.answerMap.clear()
        SimuladoSessionStore.answerMap.putAll(answerMap)
        SimuladoSessionStore.feedbackMap.clear()
        SimuladoSessionStore.feedbackMap.putAll(feedbackMap)
        SimuladoSessionStore.persist(context)
    }

    fun launchSimulado(presetToRun: SpecificExamPreset, countToRun: Int) {
        selectedPreset = presetToRun
        val cfg = SimuladoConfig(
            subject = selectedSubject,
            mode = selectedMode,
            moduleId = selectedModuleId,
            subtopic = selectedSubtopic,
            questionCount = countToRun,
            preset = presetToRun
        )
        val questions = QuestionBankRepository.generateSimulado(cfg, context)
        if (questions.isNotEmpty()) {
            cachedGeneratedQuestions = questions
            generatedQuestionIds = questions.map { it.id }
            currentQuestionIdx = 0
            examElapsedSeconds = 0
            feedbackMap.clear()
            answerMap.clear()
            screenState = SimuladoScreenState.RUNNING_EXAM
            syncSessionStore()
        }
    }

    fun finalizeSimuladoAndOpenReport() {
        SimuladoSessionStore.finalizePendingAnswers(generatedQuestions, answerMap, feedbackMap)
        for (bq in generatedQuestions) {
            val fb = feedbackMap[bq.id]
            if (fb != null) {
                if (fb.isCorrect) {
                    progressRepository.recordSolvedExercise(bq.id, 20)
                } else {
                    progressRepository.recordWrongQuestion(bq.id, bq.lesson.lessonId)
                }
            }
        }
        screenState = SimuladoScreenState.DIAGNOSTIC_REPORT
        syncSessionStore()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
    ) {
        TopUserStatsBar(
            userProgress = userProgress,
            userPhotoUrl = userPhotoUrl
        )

        when (screenState) {
            SimuladoScreenState.CONFIGURATOR -> {
                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    // Cabeçalho + Alternador para Explorador do Banco por Aula
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Central de Simulados & Avaliações",
                                style = MaterialTheme.typography.titleLarge,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Text(
                                text = "Avaliações por módulo, cumulativas e personalizadas (máx. 45 questões)",
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary
                            )
                        }
                        OutlinedButton(
                            onClick = {
                                screenState = SimuladoScreenState.LESSON_BANK_EXPLORER
                                syncSessionStore()
                            },
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.FilterList,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Banco por Aula")
                        }
                    }

                    // Banner de Retomada caso exista um Simulado em Andamento salvo
                    if (generatedQuestions.isNotEmpty()) {
                        Surface(
                            color = PrimaryViolet.copy(alpha = 0.14f),
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, BorderActive, RoundedCornerShape(12.dp))
                                .clickable {
                                    screenState = SimuladoScreenState.RUNNING_EXAM
                                    syncSessionStore()
                                }
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.padding(14.dp)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Simulado em Andamento Salvo",
                                        style = MaterialTheme.typography.titleSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryVioletLight
                                    )
                                    Text(
                                        text = "${answerMap.size} de ${generatedQuestions.size} questões respondidas • Nenhuma resposta perdida",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextSecondary
                                    )
                                }
                                Button(
                                    onClick = {
                                        screenState = SimuladoScreenState.RUNNING_EXAM
                                        syncSessionStore()
                                    },
                                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                                    shape = RoundedCornerShape(8.dp)
                                ) {
                                    Text("Retomar")
                                }
                            }
                        }
                    }

                    val maxQuestionsForPreset = selectedPreset.maxQuestionCount
                    val targetCount = questionCountSlider.roundToInt().coerceIn(1, maxQuestionsForPreset)

                    // 1. Simulados Específicos (ENEM Dia 1, ENEM Dia 2, Transpetro e Personalizado)
                    SectionCard(title = "1. Simulados Específicos & Cadernos Oficiais") {
                        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                            val specificPresets = listOf(
                                SpecificExamPreset.ENEM_DIA_1,
                                SpecificExamPreset.ENEM_DIA_2,
                                SpecificExamPreset.TRANSPETRO,
                                SpecificExamPreset.CUSTOM
                            )
                            specificPresets.forEach { preset ->
                                val isSel = selectedPreset == preset
                                val badgeColor = when (preset) {
                                    SpecificExamPreset.ENEM_DIA_1 -> AccentCyan
                                    SpecificExamPreset.ENEM_DIA_2 -> SuccessEmerald
                                    SpecificExamPreset.TRANSPETRO -> WarningAmber
                                    SpecificExamPreset.CUSTOM -> PrimaryVioletLight
                                }
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(if (isSel) PrimaryViolet.copy(alpha = 0.14f) else SurfaceCard)
                                        .border(
                                            width = 1.dp,
                                            color = if (isSel) BorderActive else BorderSubtle,
                                            shape = RoundedCornerShape(10.dp)
                                        )
                                        .clickable {
                                            selectedPreset = preset
                                            questionCountSlider = preset.defaultQuestionCount.toFloat()
                                        }
                                        .padding(12.dp)
                                        .testTag("preset_card_${preset.id}")
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween,
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(8.dp),
                                            modifier = Modifier.weight(1f)
                                        ) {
                                            Text(
                                                text = preset.title,
                                                style = MaterialTheme.typography.titleSmall,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isSel) PrimaryVioletLight else TextPrimary
                                            )
                                            Surface(
                                                color = badgeColor.copy(alpha = 0.16f),
                                                shape = RoundedCornerShape(6.dp)
                                            ) {
                                                Text(
                                                    text = preset.badge,
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontWeight = FontWeight.SemiBold,
                                                    color = badgeColor,
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }

                                        if (preset != SpecificExamPreset.CUSTOM) {
                                            Button(
                                                onClick = {
                                                    val countToUse = if (isSel) targetCount else preset.defaultQuestionCount
                                                    selectedPreset = preset
                                                    questionCountSlider = countToUse.toFloat()
                                                    launchSimulado(preset, countToUse)
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = if (isSel) PrimaryViolet else SurfaceElevated
                                                ),
                                                contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                                                shape = RoundedCornerShape(8.dp),
                                                modifier = Modifier
                                                    .height(32.dp)
                                                    .testTag("btn_quick_start_${preset.id}")
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.PlayArrow,
                                                    contentDescription = null,
                                                    modifier = Modifier.size(14.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(
                                                    text = "Iniciar",
                                                    style = MaterialTheme.typography.labelSmall,
                                                    fontWeight = FontWeight.Bold
                                                )
                                            }
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(4.dp))
                                    Text(
                                        text = "Banca: ${preset.banca} • Formato: ${preset.examFormatLabel}",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.SemiBold,
                                        color = AccentCyan
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = preset.subtitle,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextSecondary
                                    )
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = preset.compositionSummary,
                                        style = MaterialTheme.typography.labelSmall,
                                        color = TextTertiary
                                    )
                                }
                            }
                        }
                    }

                    if (selectedPreset != SpecificExamPreset.CUSTOM) {
                        // 2. Resumo da Matriz Oficial para o Caderno Específico Selecionado
                        SectionCard(title = "2. Matriz Oficial da Prova • ${selectedPreset.title}") {
                            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                                Text(
                                    text = "Banca Examinadora: ${selectedPreset.banca} • ${selectedPreset.examFormatLabel}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.SemiBold,
                                    color = AccentCyan
                                )
                                when (selectedPreset) {
                                    SpecificExamPreset.ENEM_DIA_1 -> {
                                        val lingCount = (targetCount * 0.50).roundToInt().coerceAtLeast(1)
                                        val humCount = (targetCount - lingCount).coerceAtLeast(0)
                                        val histCount = (humCount * (15.0 / 45.0)).roundToInt()
                                        val geoCount = (humCount * (15.0 / 45.0)).roundToInt()
                                        val filCount = (humCount * (8.0 / 45.0)).roundToInt()
                                        val socCount = (humCount - histCount - geoCount - filCount).coerceAtLeast(0)
                                        Text(
                                            text = "• Bloco 1 — Linguagens, Códigos e suas Tecnologias (50%): $lingCount questões (Língua Portuguesa, Literatura, Inglês/Espanhol e Artes)",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextPrimary
                                        )
                                        Text(
                                            text = "• Bloco 2 — Ciências Humanas e suas Tecnologias (50%): $humCount questões ($histCount História • $geoCount Geografia • $filCount Filosofia • $socCount Sociologia)",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextPrimary
                                        )
                                    }
                                    SpecificExamPreset.ENEM_DIA_2 -> {
                                        val natCount = (targetCount * 0.50).roundToInt().coerceAtLeast(1)
                                        val matCount = (targetCount - natCount).coerceAtLeast(0)
                                        val bioCount = natCount / 3
                                        val fisCount = (natCount - bioCount) / 2
                                        val quiCount = (natCount - bioCount - fisCount).coerceAtLeast(0)
                                        Text(
                                            text = "• Bloco 1 — Ciências da Natureza e suas Tecnologias (50%): $natCount questões ($bioCount Biologia • $fisCount Física • $quiCount Química)",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextPrimary
                                        )
                                        Text(
                                            text = "• Bloco 2 — Matemática e suas Tecnologias (50%): $matCount questões (Matemática ENEM e Matemática Básica)",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextPrimary
                                        )
                                    }
                                    SpecificExamPreset.TRANSPETRO -> {
                                        val countPor = (targetCount * (10.0 / 60.0)).roundToInt().coerceAtLeast(if (targetCount >= 3) 1 else 0)
                                        val countMat = (targetCount * (10.0 / 60.0)).roundToInt().coerceAtLeast(if (targetCount >= 3) 1 else 0)
                                        val countEsp = (targetCount - countPor - countMat).coerceAtLeast(1)
                                        val countAdm = countEsp / 4
                                        val countFin = (countEsp - countAdm) / 3
                                        val countLog = (countEsp - countAdm - countFin) / 2
                                        val countInf = (countEsp - countAdm - countFin - countLog).coerceAtLeast(0)
                                        Text(
                                            text = "• Bloco I — Conhecimentos Básicos (33,3%): ${countPor + countMat} questões ($countPor Língua Portuguesa + $countMat Matemática)",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextPrimary
                                        )
                                        Text(
                                            text = "• Bloco II — Conhecimentos Específicos (66,7%): $countEsp questões ($countAdm Processos Adm. • $countFin Finanças • $countLog Logística • $countInf Informática)",
                                            style = MaterialTheme.typography.bodySmall,
                                            color = TextPrimary
                                        )
                                        Text(
                                            text = "Critério Oficial Cesgranrio: mínimo de 50% de acertos no Bloco I, 50% no Bloco II e proibição de zerar Português ou Matemática.",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = WarningAmber
                                        )
                                    }
                                    SpecificExamPreset.CUSTOM -> {}
                                }
                            }
                        }
                    } else {
                        // 2. Seleção da Matéria / Disciplina (Modo Personalizado)
                        SectionCard(title = "2. Escolha a Matéria") {
                            FlowRow(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                subjects.forEach { subj ->
                                    SelectableChip(
                                        label = subj,
                                        selected = selectedSubject == subj,
                                        onClick = { selectedSubject = subj }
                                    )
                                }
                            }
                        }

                        // 3. Modalidade de Avaliação (Geral, Módulo, Cumulativa, Subtópico)
                        SectionCard(title = "3. Modalidade da Avaliação") {
                            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                                SimuladoMode.entries.forEach { mode ->
                                    val isSel = selectedMode == mode
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .clip(RoundedCornerShape(10.dp))
                                            .background(if (isSel) PrimaryViolet.copy(alpha = 0.15f) else SurfaceCard)
                                            .border(
                                                1.dp,
                                                if (isSel) BorderActive else BorderSubtle,
                                                RoundedCornerShape(10.dp)
                                            )
                                            .clickable { selectedMode = mode }
                                            .padding(12.dp)
                                    ) {
                                        Column(modifier = Modifier.weight(1f)) {
                                            Text(
                                                text = mode.label,
                                                style = MaterialTheme.typography.bodyMedium,
                                                fontWeight = FontWeight.Bold,
                                                color = if (isSel) PrimaryVioletLight else TextPrimary
                                            )
                                            Text(
                                                text = mode.description,
                                                style = MaterialTheme.typography.labelSmall,
                                                color = TextSecondary
                                            )
                                        }
                                    }
                                }
                            }
                        }

                        // 3b. Seleção de Módulo (para Avaliação de Módulo ou Cumulativa)
                        if (selectedMode == SimuladoMode.BY_MODULE || selectedMode == SimuladoMode.CUMULATIVE) {
                            val sectionTitle = if (selectedMode == SimuladoMode.CUMULATIVE) {
                                "Selecione até qual Módulo cobrar (Avaliação Cumulativa)"
                            } else {
                                "Selecione o Módulo da Avaliação"
                            }
                            SectionCard(title = sectionTitle) {
                                if (selectedMode == SimuladoMode.CUMULATIVE) {
                                    Text(
                                        text = "O simulado misturará questões do módulo selecionado com questões de módulos e aulas anteriores para exigir recuperação ativa.",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = AccentCyan,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    )
                                }
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    availableModules.forEach { mod ->
                                        SelectableChip(
                                            label = "${mod.moduleTitle} (${mod.lessonCount} aulas)",
                                            selected = selectedModuleId == mod.moduleId,
                                            onClick = { selectedModuleId = mod.moduleId }
                                        )
                                    }
                                }
                            }
                        }

                        // 3c. Seleção de Subtópico (quando modo = BY_SUBTOPIC)
                        if (selectedMode == SimuladoMode.BY_SUBTOPIC) {
                            SectionCard(title = "Escolha o Subtópico da Aula") {
                                FlowRow(
                                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                                    verticalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    availableSubtopics.take(30).forEach { sub ->
                                        SelectableChip(
                                            label = sub,
                                            selected = selectedSubtopic == sub,
                                            onClick = { selectedSubtopic = sub }
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // Quantidade de Questões (proporcional ao caderno oficial ou modo personalizado)
                    val presetChips = when (selectedPreset) {
                        SpecificExamPreset.ENEM_DIA_1, SpecificExamPreset.ENEM_DIA_2 -> listOf(15, 30, 45, 60, 90)
                        SpecificExamPreset.TRANSPETRO -> listOf(15, 30, 45, 60)
                        SpecificExamPreset.CUSTOM -> listOf(5, 10, 15, 25, 35, 45)
                    }
                    SectionCard(title = "Quantidade de Questões: $targetCount (Máx. $maxQuestionsForPreset)") {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                presetChips.forEach { presetCount ->
                                    SelectableChip(
                                        label = if (presetCount == selectedPreset.defaultQuestionCount && selectedPreset != SpecificExamPreset.CUSTOM) {
                                            "$presetCount (Oficial)"
                                        } else {
                                            "$presetCount"
                                        },
                                        selected = targetCount == presetCount,
                                        onClick = { questionCountSlider = presetCount.toFloat() }
                                    )
                                }
                            }
                            Slider(
                                value = questionCountSlider.coerceIn(1f, maxQuestionsForPreset.toFloat()),
                                onValueChange = { questionCountSlider = it },
                                valueRange = 1f..maxQuestionsForPreset.toFloat(),
                                steps = (maxQuestionsForPreset - 2).coerceAtLeast(1),
                                colors = SliderDefaults.colors(
                                    thumbColor = PrimaryViolet,
                                    activeTrackColor = PrimaryViolet
                                )
                            )
                            Text(
                                text = "Sorteio aleatório balanceado (~30% Fácil • ~45% Média • ~25% Difícil) com controle anti-repetição de padrão entre simulados.",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextTertiary
                            )
                        }
                    }

                    // Botão Gerar Simulado
                    val buttonTitle = if (selectedPreset == SpecificExamPreset.CUSTOM) {
                        "Gerar Simulado Personalizado ($targetCount questões)"
                    } else {
                        "Iniciar Simulado ${selectedPreset.title} ($targetCount questões)"
                    }
                    Button(
                        onClick = {
                            launchSimulado(selectedPreset, targetCount)
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                        shape = RoundedCornerShape(12.dp),
                        contentPadding = PaddingValues(vertical = 14.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .testTag("btn_generate_simulado")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Shuffle,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = buttonTitle,
                            style = MaterialTheme.typography.titleSmall,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }

            SimuladoScreenState.RUNNING_EXAM -> {
                val total = generatedQuestions.size
                val currentQ = generatedQuestions.getOrNull(currentQuestionIdx)
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 16.dp, vertical = 10.dp)
                ) {
                    // Barra Superior do Simulado em Andamento
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                showExitDialog = true
                            },
                            contentPadding = PaddingValues(horizontal = 10.dp, vertical = 6.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Configurar", style = MaterialTheme.typography.labelMedium)
                        }

                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val mins = (examElapsedSeconds / 60).toString().padStart(2, '0')
                            val secs = (examElapsedSeconds % 60).toString().padStart(2, '0')
                            val answeredCount = maxOf(answerMap.size, feedbackMap.size)
                            val presetHeaderPrefix = if (selectedPreset != SpecificExamPreset.CUSTOM) {
                                "${selectedPreset.title} • "
                            } else {
                                ""
                            }
                            Text(
                                text = "${presetHeaderPrefix}Questão ${currentQuestionIdx + 1} de $total • $answeredCount/$total",
                                style = MaterialTheme.typography.labelLarge,
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                            Text(
                                text = "Tempo: $mins:$secs",
                                style = MaterialTheme.typography.labelSmall,
                                color = AccentCyan,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Button(
                            onClick = { finalizeSimuladoAndOpenReport() },
                            colors = ButtonDefaults.buttonColors(containerColor = SuccessEmerald),
                            contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Assessment,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Concluir", style = MaterialTheme.typography.labelMedium)
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Barra de Progresso Geral do Simulado (considera respostas salvas e avaliadas)
                    val answeredTotal = maxOf(answerMap.size, feedbackMap.size)
                    val progressFraction = if (total > 0) (answeredTotal.toFloat() / total.toFloat()).coerceIn(0f, 1f) else 0f
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(5.dp)
                            .clip(RoundedCornerShape(3.dp))
                            .background(SurfaceElevated)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(progressFraction.coerceAtLeast(if (answeredTotal > 0) 0.04f else 0f))
                                .height(5.dp)
                                .clip(RoundedCornerShape(3.dp))
                                .background(SuccessEmerald)
                        )
                    }

                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(vertical = 4.dp)
                    ) {
                        generatedQuestions.forEachIndexed { idx, bq ->
                            val fb = feedbackMap[bq.id]
                            val hasDraft = answerMap.containsKey(bq.id)
                            val isCurrent = idx == currentQuestionIdx
                            val pillColor = when (fb?.verdict) {
                                Verdict.CORRECT -> SuccessEmerald
                                Verdict.PARTIAL -> WarningAmber
                                Verdict.INCORRECT -> ErrorRose
                                else -> when {
                                    isCurrent -> PrimaryViolet
                                    hasDraft -> AccentCyan
                                    else -> SurfaceCard
                                }
                            }
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(CircleShape)
                                    .background(pillColor.copy(alpha = if (isCurrent) 0.9f else 0.28f))
                                    .border(
                                        width = if (isCurrent) 2.dp else 1.dp,
                                        color = if (isCurrent) TextPrimary else pillColor,
                                        shape = CircleShape
                                    )
                                    .clickable {
                                        currentQuestionIdx = idx
                                        syncSessionStore()
                                    }
                            ) {
                                Text(
                                    text = "${idx + 1}",
                                    style = MaterialTheme.typography.labelSmall,
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Questão Atual
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        if (currentQ != null) {
                            QuestionRendererCard(
                                bankQuestion = currentQ,
                                questionNumber = currentQuestionIdx + 1,
                                pythonEngine = pythonEngine,
                                existingAnswer = answerMap[currentQ.id],
                                existingFeedback = feedbackMap[currentQ.id],
                                onAnswerDraftChanged = { ans ->
                                    answerMap[currentQ.id] = ans
                                    syncSessionStore()
                                },
                                onEvaluated = { ans, fb ->
                                    answerMap[currentQ.id] = ans
                                    feedbackMap[currentQ.id] = fb
                                    if (fb.isCorrect) {
                                        progressRepository.recordSolvedExercise(currentQ.id, 20)
                                    } else {
                                        progressRepository.recordWrongQuestion(currentQ.id, currentQ.lesson.lessonId)
                                    }
                                    syncSessionStore()
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    // Controles Anterior / Próxima
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = {
                                if (currentQuestionIdx > 0) {
                                    currentQuestionIdx--
                                    syncSessionStore()
                                }
                            },
                            enabled = currentQuestionIdx > 0,
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null)
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Anterior")
                        }

                        Button(
                            onClick = {
                                if (currentQuestionIdx < total - 1) {
                                    currentQuestionIdx++
                                    syncSessionStore()
                                } else {
                                    finalizeSimuladoAndOpenReport()
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                            shape = RoundedCornerShape(10.dp)
                        ) {
                            Text(if (currentQuestionIdx < total - 1) "Próxima Questão" else "Ver Relatório Final")
                            Spacer(modifier = Modifier.width(6.dp))
                            Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null)
                        }
                    }
                }
            }

            SimuladoScreenState.DIAGNOSTIC_REPORT -> {
                val total = generatedQuestions.size.coerceAtLeast(1)
                val totalScore = generatedQuestions.sumOf { feedbackMap[it.id]?.score ?: 0.0 }
                val pct = ((totalScore / total) * 100).roundToInt()
                val correctCount = generatedQuestions.count { feedbackMap[it.id]?.verdict == Verdict.CORRECT }
                val partialCount = generatedQuestions.count { feedbackMap[it.id]?.verdict == Verdict.PARTIAL }
                val wrongCount = generatedQuestions.count {
                    val v = feedbackMap[it.id]?.verdict
                    v == Verdict.INCORRECT || v == null || v == Verdict.UNANSWERED
                }

                Column(
                    verticalArrangement = Arrangement.spacedBy(14.dp),
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState())
                        .padding(16.dp)
                ) {
                    SectionCard(title = "Relatório Diagnóstico do Simulado") {
                        Text(
                            text = "Aproveitamento Geral: $pct%",
                            style = MaterialTheme.typography.headlineSmall,
                            fontWeight = FontWeight.Bold,
                            color = if (pct >= 70) SuccessEmerald else if (pct >= 50) WarningAmber else ErrorRose
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                        Text(
                            text = "Corretas: $correctCount • Parciais: $partialCount • Incorretas/Em branco: $wrongCount (Total: ${generatedQuestions.size})",
                            style = MaterialTheme.typography.bodyMedium,
                            color = TextSecondary
                        )
                    }

                    // Critérios Oficiais de Habilitação Cesgranrio (quando simulado = Transpetro)
                    if (selectedPreset == SpecificExamPreset.TRANSPETRO) {
                        val basicQuestions = generatedQuestions.filter {
                            it.lesson.lessonId.startsWith("tp_por_") || it.lesson.lessonId.startsWith("tp_mat_")
                        }
                        val specificQuestions = generatedQuestions.filter {
                            it.lesson.lessonId.startsWith("tp_adm_") ||
                                it.lesson.lessonId.startsWith("tp_fin_") ||
                                it.lesson.lessonId.startsWith("tp_log_") ||
                                it.lesson.lessonId.startsWith("tp_inf_")
                        }
                        val porQuestions = generatedQuestions.filter { it.lesson.lessonId.startsWith("tp_por_") }
                        val matQuestions = generatedQuestions.filter { it.lesson.lessonId.startsWith("tp_mat_") }

                        val basicHits = basicQuestions.sumOf { feedbackMap[it.id]?.score ?: 0.0 }
                        val specificHits = specificQuestions.sumOf { feedbackMap[it.id]?.score ?: 0.0 }
                        val porHits = porQuestions.sumOf { feedbackMap[it.id]?.score ?: 0.0 }
                        val matHits = matQuestions.sumOf { feedbackMap[it.id]?.score ?: 0.0 }

                        val basicPct = if (basicQuestions.isNotEmpty()) ((basicHits / basicQuestions.size) * 100).roundToInt() else 0
                        val specificPct = if (specificQuestions.isNotEmpty()) ((specificHits / specificQuestions.size) * 100).roundToInt() else 0
                        val isQualified = basicPct >= 50 && specificPct >= 50 &&
                            (porQuestions.isEmpty() || porHits > 0.0) &&
                            (matQuestions.isEmpty() || matHits > 0.0)

                        SectionCard(title = "Critérios de Habilitação • Edital Fundação Cesgranrio") {
                            Text(
                                text = if (isQualified) "HABILITADO PELOS CRITÉRIOS DO EDITAL" else "NÃO HABILITADO (REVISAR MÍNIMOS POR BLOCO)",
                                style = MaterialTheme.typography.titleSmall,
                                fontWeight = FontWeight.Bold,
                                color = if (isQualified) SuccessEmerald else ErrorRose
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "• Bloco I (Conhecimentos Básicos ≥ 50%): $basicPct% (${basicHits.roundToInt()}/${basicQuestions.size})",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (basicPct >= 50) SuccessEmerald else ErrorRose
                            )
                            Text(
                                text = "• Bloco II (Conhecimentos Específicos ≥ 50%): $specificPct% (${specificHits.roundToInt()}/${specificQuestions.size})",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (specificPct >= 50) SuccessEmerald else ErrorRose
                            )
                            Text(
                                text = "• Língua Portuguesa (> 0 acertos): ${porHits.roundToInt()}/${porQuestions.size} • Matemática (> 0 acertos): ${matHits.roundToInt()}/${matQuestions.size}",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (porHits > 0.0 && matHits > 0.0) TextPrimary else ErrorRose
                            )
                        }
                    }

                    // Desempenho por Disciplina da Prova
                    SectionCard(title = "Desempenho por Disciplina da Prova") {
                        generatedQuestions.groupBy { it.subject }.forEach { (subj, list) ->
                            val hits = list.sumOf { feedbackMap[it.id]?.score ?: 0.0 }
                            val subjPct = ((hits / list.size) * 100).roundToInt()
                            Text(
                                text = "• $subj: $subjPct% (${hits.roundToInt()}/${list.size} questões)",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (subjPct >= 70) SuccessEmerald else if (subjPct >= 50) TextPrimary else WarningAmber,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }

                    // Desempenho por Dificuldade
                    SectionCard(title = "Desempenho por Nível de Dificuldade") {
                        QDifficulty.entries.forEach { diff ->
                            val items = generatedQuestions.filter { it.question.difficulty == diff }
                            if (items.isNotEmpty()) {
                                val hits = items.sumOf { feedbackMap[it.id]?.score ?: 0.0 }
                                val dPct = ((hits / items.size) * 100).roundToInt()
                                Text(
                                    text = "• ${diff.label}: $dPct% (${items.size} questões)",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextPrimary,
                                    modifier = Modifier.padding(vertical = 2.dp)
                                )
                            }
                        }
                    }

                    // Desempenho por Subtópico / Aula
                    SectionCard(title = "Desempenho por Subtópico e Aula") {
                        generatedQuestions.groupBy { it.lesson.subtopic }.forEach { (sub, list) ->
                            val hits = list.sumOf { feedbackMap[it.id]?.score ?: 0.0 }
                            val sPct = ((hits / list.size) * 100).roundToInt()
                            Text(
                                text = "• $sub: $sPct% (${list.size} q.)",
                                style = MaterialTheme.typography.bodySmall,
                                color = if (sPct >= 70) SuccessEmerald else TextSecondary,
                                modifier = Modifier.padding(vertical = 2.dp)
                            )
                        }
                    }

                    // Conceitos Diagnosticados para Revisão
                    val conceptsToReview = generatedQuestions.mapNotNull { bq ->
                        val fb = feedbackMap[bq.id]
                        if (fb != null && !fb.isCorrect && !fb.misunderstoodConcept.isNullOrBlank()) {
                            "${bq.lesson.subtopic}: ${fb.misunderstoodConcept}"
                        } else null
                    }.distinct()

                    if (conceptsToReview.isNotEmpty()) {
                        SectionCard(title = "Conceitos Identificados para Revisão") {
                            conceptsToReview.forEach { item ->
                                Row(
                                    verticalAlignment = Alignment.Top,
                                    modifier = Modifier.padding(vertical = 3.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Psychology,
                                        contentDescription = null,
                                        tint = WarningAmber,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Text(
                                        text = item,
                                        style = MaterialTheme.typography.bodySmall,
                                        color = TextPrimary
                                    )
                                }
                            }
                        }
                    }

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { screenState = SimuladoScreenState.RUNNING_EXAM },
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Text("Revisar Questões")
                        }
                        Button(
                            onClick = { screenState = SimuladoScreenState.CONFIGURATOR },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                            shape = RoundedCornerShape(10.dp),
                            modifier = Modifier.weight(1f)
                        ) {
                            Icon(Icons.Default.Refresh, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Novo Simulado")
                        }
                    }
                }
            }

            SimuladoScreenState.LESSON_BANK_EXPLORER -> {
                val filteredQuestions = remember(
                    selectedSubject,
                    selectedModuleId,
                    selectedSubtopic,
                    selectedFormatFilter,
                    selectedDifficultyFilter
                ) {
                    QuestionBankRepository.filterQuestions(
                        subject = selectedSubject,
                        moduleId = selectedModuleId,
                        subtopic = selectedSubtopic,
                        format = selectedFormatFilter,
                        difficulty = selectedDifficultyFilter,
                        includeLegacy = false,
                        context = context
                    )
                }

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        OutlinedButton(
                            onClick = { screenState = SimuladoScreenState.CONFIGURATOR },
                            shape = RoundedCornerShape(8.dp)
                        ) {
                            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Voltar aos Simulados")
                        }
                        Text(
                            text = "${filteredQuestions.size} questões encontradas",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = PrimaryVioletLight
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Filtros rápidos de Matéria, Subtópico, Formato e Dificuldade
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        subjects.forEach { subj ->
                            SelectableChip(
                                label = subj,
                                selected = selectedSubject == subj,
                                onClick = {
                                    selectedSubject = subj
                                    selectedModuleId = null
                                    selectedSubtopic = null
                                }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        SelectableChip(
                            label = "Todos Subtópicos",
                            selected = selectedSubtopic == null,
                            onClick = { selectedSubtopic = null }
                        )
                        availableSubtopics.forEach { sub ->
                            SelectableChip(
                                label = sub,
                                selected = selectedSubtopic == sub,
                                onClick = { selectedSubtopic = sub }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(6.dp))

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                    ) {
                        SelectableChip(
                            label = "Todos Formatos (8)",
                            selected = selectedFormatFilter == null,
                            onClick = { selectedFormatFilter = null }
                        )
                        QuestionFormat.entries.forEach { fmt ->
                            SelectableChip(
                                label = fmt.label,
                                selected = selectedFormatFilter == fmt,
                                onClick = { selectedFormatFilter = fmt }
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.weight(1f)
                    ) {
                        itemsIndexed(
                            items = filteredQuestions.take(60),
                            key = { _, item -> item.id }
                        ) { idx, bq ->
                            QuestionRendererCard(
                                bankQuestion = bq,
                                questionNumber = idx + 1,
                                pythonEngine = pythonEngine,
                                existingFeedback = feedbackMap[bq.id],
                                onEvaluated = { ans, fb ->
                                    answerMap[bq.id] = ans
                                    feedbackMap[bq.id] = fb
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    if (showExitDialog) {
        AlertDialog(
            onDismissRequest = { showExitDialog = false },
            title = {
                Text(
                    text = "Sair do Simulado?",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = TextPrimary
                )
            },
            text = {
                Text(
                    text = "Seu progresso e respostas respondidas permanecerão salvos. Deseja pausar e voltar para a tela de configuração?",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextSecondary
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showExitDialog = false
                        screenState = SimuladoScreenState.CONFIGURATOR
                        syncSessionStore()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = ErrorRose)
                ) {
                    Text("Pausar e Sair")
                }
            },
            dismissButton = {
                OutlinedButton(
                    onClick = { showExitDialog = false }
                ) {
                    Text("Continuar Simulado")
                }
            },
            containerColor = ObsidianSurface,
            shape = RoundedCornerShape(16.dp)
        )
    }
}

@Composable
private fun SectionCard(
    title: String,
    content: @Composable () -> Unit
) {
    Surface(
        color = ObsidianSurface,
        shape = RoundedCornerShape(12.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Spacer(modifier = Modifier.height(10.dp))
            content()
        }
    }
}

@Composable
private fun SelectableChip(
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(8.dp))
            .background(if (selected) PrimaryViolet.copy(alpha = 0.22f) else SurfaceCard)
            .border(
                1.dp,
                if (selected) BorderActive else BorderSubtle,
                RoundedCornerShape(8.dp)
            )
            .clickable(onClick = onClick)
            .padding(horizontal = 10.dp, vertical = 6.dp)
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
            color = if (selected) PrimaryVioletLight else TextSecondary
        )
    }
}

