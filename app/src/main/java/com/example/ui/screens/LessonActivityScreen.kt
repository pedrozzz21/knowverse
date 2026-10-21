package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Terminal
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.UserProgressRepository
import com.example.data.model.Lesson
import com.example.data.python.CodeEvaluator
import com.example.data.python.EvaluationReport
import com.example.data.python.PythonCurriculum
import com.example.data.python.PythonDidacticContent
import com.example.data.python.PythonEngine
import com.example.data.python.PythonErrorDiagnostic
import com.example.ui.components.CodeEditorView
import com.example.ui.components.ErrorDiagnosticDialog
import com.example.ui.components.TerminalView
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import kotlinx.coroutines.launch

enum class PythonLessonStage(val label: String, val icon: ImageVector) {
    THEORY("1. Matéria", Icons.Default.MenuBook),
    SYNTAX("2. Sintaxe & Funções", Icons.Default.Code),
    EXAMPLES("3. Exemplos", Icons.Default.Lightbulb),
    PRACTICE("4. Prática", Icons.Default.Terminal)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LessonActivityScreen(
    lesson: Lesson,
    pythonEngine: PythonEngine,
    evaluator: CodeEvaluator,
    progressRepository: UserProgressRepository,
    onNavigateBack: () -> Unit,
    onNavigateToLesson: (Lesson) -> Unit
) {
    BackHandler {
        onNavigateBack()
    }

    val coroutineScope = rememberCoroutineScope()

    val didacticBundle = remember(lesson.id) {
        PythonDidacticContent.getContentForLesson(lesson.id)
    }

    var currentStage by remember(lesson.id) { mutableStateOf(PythonLessonStage.THEORY) }

    var userCode by remember(lesson.id) {
        val saved = progressRepository.getSavedUserCode(lesson.id)
        mutableStateOf(saved ?: lesson.initialCode)
    }

    var isRunning by remember { mutableStateOf(false) }
    var stdoutOutput by remember { mutableStateOf("") }
    var terminalError by remember { mutableStateOf<String?>(null) }
    var activeDiagnostic by remember { mutableStateOf<PythonErrorDiagnostic?>(null) }
    var showDiagnosticDialog by remember { mutableStateOf(false) }
    var executionTimeMs by remember { mutableStateOf(0L) }
    var highlightedLine by remember { mutableIntStateOf(-1) }
    var evaluationReport by remember { mutableStateOf<EvaluationReport?>(null) }

    var showHintSheet by remember { mutableStateOf(false) }
    val hintSheetState = rememberModalBottomSheetState()

    val progress = progressRepository.progressState.value
    val isInitiallyCompleted = progress.completedLessonIds.contains(lesson.id)

    // Automatically trigger daily check-in upon starting a lesson
    LaunchedEffect(lesson.id) {
        progressRepository.recordCheckIn()
    }

    // Save user code snapshot as they type
    LaunchedEffect(userCode) {
        progressRepository.saveUserCode(lesson.id, userCode)
    }

    Scaffold(
        containerColor = DeepNavy,
        topBar = {
            Surface(
                color = ObsidianSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = BorderSubtle)
                    .statusBarsPadding()
            ) {
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.weight(1f)
                        ) {
                            IconButton(
                                onClick = onNavigateBack,
                                modifier = Modifier.testTag("python_back_btn")
                            ) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = "Voltar",
                                    tint = TextPrimary
                                )
                            }
                            Spacer(modifier = Modifier.width(6.dp))
                            Column {
                                Text(
                                    text = "Python • ${lesson.title}",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Text(
                                    text = lesson.conceptTitle,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    ),
                                    maxLines = 1
                                )
                            }
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            // Diagnostic Pop-up Shortcut if error exists
                            if (activeDiagnostic != null) {
                                IconButton(
                                    onClick = { showDiagnosticDialog = true },
                                    modifier = Modifier.size(34.dp).testTag("open_diagnostic_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Warning,
                                        contentDescription = "Diagnóstico do Erro",
                                        tint = ErrorRose,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            // Hint Sheet Button
                            if (lesson.hints.isNotEmpty()) {
                                IconButton(
                                    onClick = { showHintSheet = true },
                                    modifier = Modifier.size(34.dp).testTag("open_hints_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Lightbulb,
                                        contentDescription = "Dicas",
                                        tint = GoldXp,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }

                            // Reset Code Button
                            IconButton(
                                onClick = {
                                    userCode = lesson.initialCode
                                    stdoutOutput = ""
                                    terminalError = null
                                    activeDiagnostic = null
                                    highlightedLine = -1
                                    evaluationReport = null
                                },
                                modifier = Modifier.size(34.dp).testTag("reset_code_btn")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Restaurar",
                                    tint = TextSecondary,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }

                    // 4-Stage Pedagogical Navigation Bar
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(SurfaceElevated)
                            .padding(horizontal = 8.dp, vertical = 4.dp),
                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        PythonLessonStage.values().forEach { stage ->
                            val isSelected = currentStage == stage
                            Surface(
                                color = if (isSelected) PrimaryViolet else Color.Transparent,
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .clickable { currentStage = stage }
                            ) {
                                Row(
                                    modifier = Modifier.padding(vertical = 7.dp, horizontal = 4.dp),
                                    horizontalArrangement = Arrangement.Center,
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = stage.icon,
                                        contentDescription = stage.label,
                                        tint = if (isSelected) Color.White else TextTertiary,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = stage.label,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = if (isSelected) Color.White else TextSecondary,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            fontSize = 11.sp
                                        ),
                                        maxLines = 1
                                    )
                                }
                            }
                        }
                    }
                }
            }
        },
        bottomBar = {
            Surface(
                color = ObsidianSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = BorderSubtle)
                    .navigationBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    when (currentStage) {
                        PythonLessonStage.THEORY -> {
                            Button(
                                onClick = { currentStage = PythonLessonStage.SYNTAX },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth().height(44.dp)
                            ) {
                                Text("Avançar para 2. Sintaxe & Funções", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                        PythonLessonStage.SYNTAX -> {
                            OutlinedButton(
                                onClick = { currentStage = PythonLessonStage.THEORY },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f).height(44.dp)
                            ) {
                                Text("Teoria", color = TextSecondary)
                            }
                            Button(
                                onClick = { currentStage = PythonLessonStage.EXAMPLES },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1.5f).height(44.dp)
                            ) {
                                Text("Ver Exemplos", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                        PythonLessonStage.EXAMPLES -> {
                            OutlinedButton(
                                onClick = { currentStage = PythonLessonStage.SYNTAX },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f).height(44.dp)
                            ) {
                                Text("Sintaxe", color = TextSecondary)
                            }
                            Button(
                                onClick = { currentStage = PythonLessonStage.PRACTICE },
                                colors = ButtonDefaults.buttonColors(containerColor = GoldXp, contentColor = Color.Black),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1.5f).height(44.dp)
                            ) {
                                Text("Ir para a Prática", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                        PythonLessonStage.PRACTICE -> {
                            // Quick Run button
                            OutlinedButton(
                                onClick = {
                                    coroutineScope.launch {
                                        isRunning = true
                                        terminalError = null
                                        activeDiagnostic = null
                                        highlightedLine = -1
                                        evaluationReport = null
                                        progressRepository.recordExecution()

                                        val res = pythonEngine.executeCode(userCode)
                                        isRunning = false
                                        stdoutOutput = res.stdout
                                        executionTimeMs = res.executionTimeMs

                                        if (!res.isSuccess) {
                                            terminalError = res.friendlyError ?: res.rawError
                                            activeDiagnostic = res.diagnostic
                                            highlightedLine = res.errorLine
                                        }
                                    }
                                },
                                enabled = !isRunning,
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier
                                    .weight(1f)
                                    .height(42.dp)
                                    .testTag("run_code_button")
                            ) {
                                if (isRunning) {
                                    CircularProgressIndicator(
                                        modifier = Modifier.size(16.dp),
                                        strokeWidth = 2.dp,
                                        color = PrimaryViolet
                                    )
                                } else {
                                    Icon(
                                        imageVector = Icons.Default.PlayArrow,
                                        contentDescription = null,
                                        modifier = Modifier.size(16.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text("Executar", style = MaterialTheme.typography.labelMedium)
                                }
                            }

                            // Evaluate and Submit Solution
                            Button(
                                onClick = {
                                    coroutineScope.launch {
                                        isRunning = true
                                        progressRepository.recordExecution()
                                        val report = evaluator.evaluate(lesson, userCode)
                                        isRunning = false
                                        evaluationReport = report
                                        stdoutOutput = report.stdout
                                        executionTimeMs = report.executionTimeMs

                                        if (!report.isPassed) {
                                            terminalError = report.feedbackMessage
                                            highlightedLine = report.errorLine
                                            activeDiagnostic = report.diagnostic
                                            if (report.diagnostic != null) {
                                                showDiagnosticDialog = true
                                            }
                                            progressRepository.markLessonForReview(lesson.id)
                                        } else {
                                            terminalError = null
                                            activeDiagnostic = null
                                            highlightedLine = -1
                                            // Award XP only once (0 XP on repeats)
                                            progressRepository.completeLesson(lesson.id, report.xpEarned)
                                        }
                                    }
                                },
                                enabled = !isRunning,
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryViolet,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier
                                    .weight(1.3f)
                                    .height(42.dp)
                                    .testTag("evaluate_code_button")
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Avaliar Solução",
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                                )
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            when (currentStage) {
                // ========================================================
                // ETAPA 1: MATÉRIA (TEORIA & CONCEITOS)
                // ========================================================
                PythonLessonStage.THEORY -> {
                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(10.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Column(modifier = Modifier.padding(14.dp)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(PrimaryViolet.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.MenuBook,
                                        contentDescription = null,
                                        tint = PrimaryViolet,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                Column {
                                    Text(
                                        text = lesson.conceptTitle,
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = TextPrimary
                                        )
                                    )
                                    Text(
                                        text = "Fundamentação Teórica da Linguagem",
                                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            Text(
                                text = didacticBundle.theoryMarkdown,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextPrimary,
                                    lineHeight = 22.sp
                                )
                            )
                        }
                    }

                    // Gold Tip Card
                    Surface(
                        color = GoldXp.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.3f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(20.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Dica de Mestre Python:",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = didacticBundle.goldTip,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        lineHeight = 17.sp
                                    )
                                )
                            }
                        }
                    }
                }

                // ========================================================
                // ETAPA 2: SINTAXE & O QUE CADA FUNÇÃO FAZ
                // ========================================================
                PythonLessonStage.SYNTAX -> {
                    Text(
                        text = "Guia de Sintaxe & Detalhamento das Funções",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = "Saiba exatamente o que cada função, método e palavra-chave faz nesta aula:",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                    )

                    didacticBundle.functionsAndSyntax.forEach { funcItem ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = funcItem.termOrFunction,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Surface(
                                    color = SurfaceElevated,
                                    shape = RoundedCornerShape(6.dp),
                                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = funcItem.syntax,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.SemiBold,
                                            color = Color(0xFF67E8F9)
                                        ),
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = funcItem.description,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        lineHeight = 18.sp
                                    )
                                )

                                if (funcItem.parametersInfo.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "• Parâmetros & Detalhes: ${funcItem.parametersInfo}",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TextSecondary,
                                            fontSize = 11.sp,
                                            lineHeight = 16.sp
                                        )
                                    )
                                }
                            }
                        }
                    }
                }

                // ========================================================
                // ETAPA 3: EXEMPLOS COMENTADOS PASSO A PASSO
                // ========================================================
                PythonLessonStage.EXAMPLES -> {
                    Text(
                        text = "Exemplos Comentados Passo a Passo",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = "Entenda o que acontece em cada linha durante a execução pelo interpretador:",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                    )

                    didacticBundle.codeExamples.forEach { ex ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = ex.title,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = PrimaryViolet,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                Surface(
                                    color = SurfaceElevated,
                                    shape = RoundedCornerShape(6.dp),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(
                                        text = ex.codeOrFormula,
                                        style = MaterialTheme.typography.bodySmall.copy(
                                            fontFamily = FontFamily.Monospace,
                                            fontWeight = FontWeight.Bold,
                                            color = GoldXp
                                        ),
                                        modifier = Modifier.padding(10.dp)
                                    )
                                }

                                Spacer(modifier = Modifier.height(8.dp))

                                Text(
                                    text = ex.explanation,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextSecondary,
                                        lineHeight = 19.sp
                                    )
                                )
                            }
                        }
                    }
                }

                // ========================================================
                // ETAPA 4: PRÁTICA, EDITOR DE CÓDIGO E TERMINAL
                // ========================================================
                PythonLessonStage.PRACTICE -> {
                    // Mission Goal Card
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                    ) {
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = "🎯 Objetivo da Missão:",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    color = GoldXp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = lesson.explanation,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextPrimary,
                                    lineHeight = 18.sp
                                )
                            )
                        }
                    }

                    // Code Editor
                    CodeEditorView(
                        codeValue = userCode,
                        onCodeChange = { userCode = it },
                        highlightLine = highlightedLine,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Terminal View
                    TerminalView(
                        stdout = stdoutOutput,
                        errorText = terminalError,
                        executionTimeMs = executionTimeMs,
                        isRunning = isRunning,
                        modifier = Modifier.fillMaxWidth()
                    )

                    // Evaluation Feedback Banner
                    if (evaluationReport != null) {
                        val rep = evaluationReport!!
                        Surface(
                            color = if (rep.isPassed) SuccessEmerald.copy(alpha = 0.12f) else ErrorRose.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                if (rep.isPassed) SuccessEmerald.copy(alpha = 0.4f) else ErrorRose.copy(alpha = 0.4f)
                            ),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Icon(
                                            imageVector = if (rep.isPassed) Icons.Default.CheckCircle else Icons.Default.Warning,
                                            contentDescription = null,
                                            tint = if (rep.isPassed) SuccessEmerald else ErrorRose,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = rep.feedbackTitle,
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                fontWeight = FontWeight.SemiBold,
                                                color = if (rep.isPassed) SuccessEmerald else ErrorRose
                                            )
                                        )
                                    }

                                    if (rep.isPassed) {
                                        Text(
                                            text = if (isInitiallyCompleted) "Aula já concluída (+0 XP)" else "+${rep.xpEarned} XP",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = if (isInitiallyCompleted) TextSecondary else GoldXp
                                            )
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.height(4.dp))

                                Text(
                                    text = rep.feedbackMessage,
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextPrimary,
                                        lineHeight = 16.sp
                                    )
                                )

                                if (rep.isPassed) {
                                    val next = PythonCurriculum.findNextLesson(lesson.id)
                                    if (next != null) {
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Button(
                                            onClick = { onNavigateToLesson(next) },
                                            colors = ButtonDefaults.buttonColors(
                                                containerColor = PrimaryViolet,
                                                contentColor = Color.White
                                            ),
                                            shape = RoundedCornerShape(6.dp),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Text("Próxima Aula: ${next.title}", fontWeight = FontWeight.Bold)
                                            Spacer(modifier = Modifier.width(6.dp))
                                            Icon(
                                                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                                contentDescription = null,
                                                modifier = Modifier.size(16.dp)
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    // Modal BottomSheet for Hints (Clues without revealing direct code)
    if (showHintSheet) {
        ModalBottomSheet(
            onDismissRequest = { showHintSheet = false },
            sheetState = hintSheetState,
            containerColor = ObsidianSurface
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp, vertical = 12.dp)
                    .navigationBarsPadding(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = GoldXp,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Dicas e Orientações para a Solução",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                }

                Text(
                    text = "As dicas abaixo ajudam você a raciocinar sobre a lógica sem estragar o aprendizado com a resposta pronta:",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )

                lesson.hints.forEachIndexed { idx, hintText ->
                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(modifier = Modifier.padding(10.dp)) {
                            Text(
                                text = "${idx + 1}.",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = GoldXp,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = hintText,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextPrimary,
                                    lineHeight = 17.sp
                                )
                            )
                        }
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }

    // Diagnostic Dialog for Errors
    if (showDiagnosticDialog && activeDiagnostic != null) {
        ErrorDiagnosticDialog(
            diagnostic = activeDiagnostic!!,
            onDismiss = { showDiagnosticDialog = false }
        )
    }
}
