package com.example.ui.screens

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
import com.example.data.assessment.BankQuestion
import com.example.data.assessment.Feedback
import com.example.data.assessment.QDifficulty
import com.example.data.assessment.QuestionBankRepository
import com.example.data.assessment.QuestionFormat
import com.example.data.assessment.SimuladoConfig
import com.example.data.assessment.SimuladoMode
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
import kotlin.math.roundToInt

private enum class SimuladoScreenState {
    CONFIGURATOR,
    RUNNING_EXAM,
    DIAGNOSTIC_REPORT,
    LESSON_BANK_EXPLORER
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
    userPhotoUrl: String? = null
) {
    val context = LocalContext.current
    val userProgress by progressRepository.progressState.collectAsState()

    LaunchedEffect(Unit) {
        QuestionBankRepository.ensureLoaded(context)
    }

    val subjects = remember { listOf("Todas") + QuestionBankRepository.getSubjects(context) }

    var screenState by remember { mutableStateOf(SimuladoScreenState.CONFIGURATOR) }
    var selectedSubject by remember { mutableStateOf("Todas") }
    var selectedMode by remember { mutableStateOf(SimuladoMode.BY_SUBJECT) }
    var selectedModuleId by remember { mutableStateOf<String?>(null) }
    var selectedSubtopic by remember { mutableStateOf<String?>(null) }
    var questionCountSlider by remember { mutableFloatStateOf(15f) }
    var selectedFormatFilter by remember { mutableStateOf<QuestionFormat?>(null) }
    var selectedDifficultyFilter by remember { mutableStateOf<QDifficulty?>(null) }

    val availableModules = remember(selectedSubject) {
        QuestionBankRepository.getModulesForSubject(selectedSubject, context)
    }
    val availableSubtopics = remember(selectedSubject, selectedModuleId) {
        QuestionBankRepository.getSubtopicsForSubject(selectedSubject, selectedModuleId, context)
    }

    LaunchedEffect(selectedSubject) {
        selectedModuleId = availableModules.firstOrNull()?.moduleId
        selectedSubtopic = availableSubtopics.firstOrNull()
    }

    var generatedQuestions by remember { mutableStateOf<List<BankQuestion>>(emptyList()) }
    var currentQuestionIdx by remember { mutableIntStateOf(0) }
    val feedbackMap = remember { mutableStateMapOf<String, Feedback>() }
    val answerMap = remember { mutableStateMapOf<String, UserAnswer>() }

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
                            onClick = { screenState = SimuladoScreenState.LESSON_BANK_EXPLORER },
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

                    // 1. Seleção da Matéria / Disciplina
                    SectionCard(title = "1. Escolha a Matéria") {
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

                    // 2. Modalidade de Avaliação (Geral, Módulo, Cumulativa, Subtópico)
                    SectionCard(title = "2. Modalidade da Avaliação") {
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

                    // 3. Seleção de Módulo (para Avaliação de Módulo ou Cumulativa)
                    if (selectedMode == SimuladoMode.BY_MODULE || selectedMode == SimuladoMode.CUMULATIVE) {
                        val sectionTitle = if (selectedMode == SimuladoMode.CUMULATIVE) {
                            "3. Selecione até qual Módulo cobrar (Avaliação Cumulativa)"
                        } else {
                            "3. Selecione o Módulo da Avaliação"
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

                    // 3b. Seleção de Subtópico (quando modo = BY_SUBTOPIC)
                    if (selectedMode == SimuladoMode.BY_SUBTOPIC) {
                        SectionCard(title = "3. Escolha o Subtópico da Aula") {
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

                    // 4. Quantidade de Questões (1 a 45 — Máximo 45)
                    val targetCount = questionCountSlider.roundToInt().coerceIn(1, QuestionBankRepository.MAX_SIMULADO_QUESTIONS)
                    SectionCard(title = "4. Quantidade de Questões: $targetCount (Máx. 45)") {
                        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                listOf(5, 10, 15, 25, 35, 45).forEach { preset ->
                                    SelectableChip(
                                        label = "$preset",
                                        selected = targetCount == preset,
                                        onClick = { questionCountSlider = preset.toFloat() }
                                    )
                                }
                            }
                            Slider(
                                value = questionCountSlider,
                                onValueChange = { questionCountSlider = it },
                                valueRange = 1f..45f,
                                steps = 43,
                                colors = SliderDefaults.colors(
                                    thumbColor = PrimaryViolet,
                                    activeTrackColor = PrimaryViolet
                                )
                            )
                            Text(
                                text = "Distribuição orientativa automática: ~30% Fácil • ~45% Média • ~25% Difícil",
                                style = MaterialTheme.typography.labelSmall,
                                color = TextTertiary
                            )
                        }
                    }

                    // Botão Gerar Simulado Aleatório
                    Button(
                        onClick = {
                            val cfg = SimuladoConfig(
                                subject = selectedSubject,
                                mode = selectedMode,
                                moduleId = selectedModuleId,
                                subtopic = selectedSubtopic,
                                questionCount = targetCount
                            )
                            val questions = QuestionBankRepository.generateSimulado(cfg, context)
                            if (questions.isNotEmpty()) {
                                generatedQuestions = questions
                                currentQuestionIdx = 0
                                feedbackMap.clear()
                                answerMap.clear()
                                screenState = SimuladoScreenState.RUNNING_EXAM
                            }
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
                            text = "Gerar Simulado Aleatório ($targetCount questões)",
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
                            onClick = { screenState = SimuladoScreenState.CONFIGURATOR },
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

                        Text(
                            text = "Questão ${currentQuestionIdx + 1} de $total • Respondidas: ${feedbackMap.size}/$total",
                            style = MaterialTheme.typography.labelLarge,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )

                        Button(
                            onClick = { screenState = SimuladoScreenState.DIAGNOSTIC_REPORT },
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

                    // Navegador Horizontal de Questões (1..N)
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState())
                            .padding(vertical = 4.dp)
                    ) {
                        generatedQuestions.forEachIndexed { idx, bq ->
                            val fb = feedbackMap[bq.id]
                            val isCurrent = idx == currentQuestionIdx
                            val pillColor = when (fb?.verdict) {
                                Verdict.CORRECT -> SuccessEmerald
                                Verdict.PARTIAL -> WarningAmber
                                Verdict.INCORRECT -> ErrorRose
                                else -> if (isCurrent) PrimaryViolet else SurfaceCard
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
                                    .clickable { currentQuestionIdx = idx }
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
                                existingFeedback = feedbackMap[currentQ.id],
                                onEvaluated = { ans, fb ->
                                    answerMap[currentQ.id] = ans
                                    feedbackMap[currentQ.id] = fb
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
                            onClick = { if (currentQuestionIdx > 0) currentQuestionIdx-- },
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
                                } else {
                                    screenState = SimuladoScreenState.DIAGNOSTIC_REPORT
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

