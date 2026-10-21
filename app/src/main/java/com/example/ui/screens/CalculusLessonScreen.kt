package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.HelpOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import com.example.ui.components.MathFormattedText
import com.example.ui.components.MathFormulaCard
import com.example.ui.components.StepByStepSolutionView
import com.example.ui.components.CalculusTheoryRenderer
import com.example.ui.components.GeoGebraMathGraphView
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.style.TextOverflow
import kotlinx.coroutines.launch
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.local.UserProgressRepository
import com.example.data.math.CalculusCurriculum
import com.example.data.math.CalculusDidacticContent
import com.example.data.model.Lesson
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

enum class CalculusStage(val label: String, val icon: ImageVector) {
    THEORY("1. Matéria", Icons.Default.MenuBook),
    FORMULAS("2. Fórmulas", Icons.Default.Functions),
    EXAMPLES("3. Exemplos", Icons.Default.Lightbulb),
    EXERCISES("4. Exercícios", Icons.Default.CheckCircle)
}

@Composable
fun CalculusLessonScreen(
    lesson: Lesson,
    progressRepository: UserProgressRepository,
    onNavigateBack: () -> Unit,
    onNavigateToNextLesson: (Lesson) -> Unit
) {
    BackHandler {
        onNavigateBack()
    }

    val nextLesson = remember(lesson.id) {
        CalculusCurriculum.findNextLesson(lesson.id)
    }

    val didacticBundle = remember(lesson.id) {
        CalculusDidacticContent.getContentForLesson(lesson.id)
    }

    var currentStage by remember(lesson.id) { mutableStateOf(CalculusStage.THEORY) }

    val userProgress = progressRepository.progressState.value
    val isInitiallyCompleted = userProgress.completedLessonIds.contains(lesson.id)
    var isLessonCompletedLocal by remember(lesson.id) { mutableStateOf(isInitiallyCompleted) }

    // Lista de exercícios com alternativas geradas e embaralhadas aleatoriamente a cada sessão
    val exercises = remember(lesson.id) {
        lesson.mathExercises.map { it.randomized() }
    }

    // Map of exercise index to selected option index
    val selectedOptions = remember(lesson.id) { mutableStateMapOf<Int, Int>() }
    // Map of exercise index to verification state: null = not checked, true = correct, false = wrong
    val exerciseResults = remember(lesson.id) { mutableStateMapOf<Int, Boolean>() }
    // Contagem de tentativas incorretas por exercício (libera solução comentada com >= 2 erros)
    val wrongAttempts = remember(lesson.id) { mutableStateMapOf<Int, Int>() }
    // Map of exercise index to showing hint
    val showHints = remember(lesson.id) { mutableStateMapOf<Int, Boolean>() }
    // Map of exercise index to showing full solution
    val showSolutions = remember(lesson.id) { mutableStateMapOf<Int, Boolean>() }
    // Map of exercise index to expanded state (primeiro exercício aberto inicialmente)
    val expandedExercises = remember(lesson.id) { mutableStateMapOf<Int, Boolean>(0 to true) }
    val coroutineScope = rememberCoroutineScope()

    val scrollState = rememberScrollState()

    // Automatically record daily check-in if not checked in yet today
    LaunchedEffect(lesson.id) {
        val prog = progressRepository.progressState.value
        if (!prog.hasCheckedInToday()) {
            progressRepository.recordCheckIn(awardBonusXp = true)
        }
    }

    val isBasicMath = lesson.id.startsWith("mb_") || lesson.unitId.contains("basic_math")
    val isIntegralsUnit = lesson.unitId.contains("integral") || 
        lesson.id.startsWith("calc_l19") || lesson.id.startsWith("calc_l20") ||
        lesson.id.startsWith("calc_l21") || lesson.id.startsWith("calc_l22") ||
        lesson.id.startsWith("calc_l23") || lesson.id.startsWith("calc_l24") ||
        lesson.id.startsWith("calc_l25") || lesson.id.startsWith("calc_l26") ||
        lesson.id.startsWith("calc_l27") || lesson.id.startsWith("calc_l28")

    val isDerivativesUnit = lesson.unitId.contains("derivat") || 
        lesson.id.startsWith("calc_l11") || lesson.id.startsWith("calc_l12") ||
        lesson.id.startsWith("calc_l13") || lesson.id.startsWith("calc_l14") ||
        lesson.id.startsWith("calc_l15") || lesson.id.startsWith("calc_l16") ||
        lesson.id.startsWith("calc_l17") || lesson.id.startsWith("calc_l18")

    val disciplineTitle = if (isBasicMath) "Matemática Básica" else "Cálculo I"
    val moduleSubtitle = when {
        isBasicMath -> "Ciclo de Nivelamento & ENEM"
        isIntegralsUnit -> "Módulo 3: Integrais e Teorema Fundamental"
        isDerivativesUnit -> "Módulo 2: Derivadas e Aplicações"
        else -> "Módulo 1: Limites e Continuidade"
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
                                modifier = Modifier.testTag("calculus_back_btn")
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
                                    text = "$disciplineTitle • $moduleSubtitle",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                                Text(
                                    text = lesson.title,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    ),
                                    maxLines = 1
                                )
                            }
                        }

                        // XP Badge
                        Surface(
                            color = GoldXp.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(12.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.4f))
                        ) {
                            Row(
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Star,
                                    contentDescription = null,
                                    tint = GoldXp,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text(
                                    text = if (isLessonCompletedLocal) "Concluída" else "+${lesson.xpReward} XP",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.Bold
                                    )
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
                        CalculusStage.values().forEach { stage ->
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
                        CalculusStage.THEORY -> {
                            Button(
                                onClick = { currentStage = CalculusStage.FORMULAS },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth().height(44.dp)
                            ) {
                                Text("Avançar para 2. Fórmulas & Regras", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(8.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                        CalculusStage.FORMULAS -> {
                            OutlinedButton(
                                onClick = { currentStage = CalculusStage.THEORY },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f).height(44.dp)
                            ) {
                                Text("Voltar", color = TextSecondary)
                            }
                            Button(
                                onClick = { currentStage = CalculusStage.EXAMPLES },
                                colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1.5f).height(44.dp)
                            ) {
                                Text("Ver Exemplos", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                        CalculusStage.EXAMPLES -> {
                            OutlinedButton(
                                onClick = { currentStage = CalculusStage.FORMULAS },
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1f).height(44.dp)
                            ) {
                                Text("Fórmulas", color = TextSecondary)
                            }
                            Button(
                                onClick = { currentStage = CalculusStage.EXERCISES },
                                colors = ButtonDefaults.buttonColors(containerColor = GoldXp, contentColor = Color.Black),
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.weight(1.5f).height(44.dp)
                            ) {
                                Text("Ir para os Exercícios", fontWeight = FontWeight.Bold)
                                Spacer(modifier = Modifier.width(6.dp))
                                Icon(Icons.AutoMirrored.Filled.ArrowForward, contentDescription = null, modifier = Modifier.size(16.dp))
                            }
                        }
                        CalculusStage.EXERCISES -> {
                            if (!isLessonCompletedLocal) {
                                Button(
                                    onClick = {
                                        isLessonCompletedLocal = true
                                        progressRepository.completeLesson(lesson.id, lesson.xpReward)
                                    },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = SuccessEmerald,
                                        contentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(46.dp)
                                        .testTag("calculus_complete_lesson_btn")
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.CheckCircle,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Text(
                                        text = "Concluir Aula (+${lesson.xpReward} XP)",
                                        fontWeight = FontWeight.Bold
                                    )
                                }
                            } else if (nextLesson != null) {
                                Button(
                                    onClick = { onNavigateToNextLesson(nextLesson) },
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = GoldXp,
                                        contentColor = Color.Black
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(46.dp)
                                        .testTag("calculus_next_lesson_btn")
                                ) {
                                    Text(
                                        text = "Próxima Aula: ${nextLesson.title.take(20)}...",
                                        fontWeight = FontWeight.Bold
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Icon(
                                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                        contentDescription = null,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            } else {
                                Button(
                                    onClick = onNavigateBack,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = PrimaryViolet,
                                        contentColor = Color.White
                                    ),
                                    shape = RoundedCornerShape(8.dp),
                                    modifier = Modifier
                                        .weight(1f)
                                        .height(46.dp)
                                ) {
                                    Text("Módulo Finalizado! Voltar à Trilha", fontWeight = FontWeight.Bold)
                                }
                            }
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            when (currentStage) {
                // ========================================================
                // ETAPA 1: MATÉRIA (TEORIA & INTUIÇÃO)
                // ========================================================
                CalculusStage.THEORY -> {
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
                                        text = "Fundamentação Teórica e Intuição",
                                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(12.dp))

                            CalculusTheoryRenderer(
                                markdown = didacticBundle.theoryMarkdown
                            )
                        }
                    }

                    // GeoGebra Interactive Mathematical Visualizer (API Oficial GeoGebra)
                    val geoConfig = didacticBundle.geoGebraConfig
                    if (geoConfig != null) {
                        GeoGebraMathGraphView(
                            config = geoConfig,
                            modifier = Modifier.fillMaxWidth()
                        )
                    } else {
                        val imgRes = didacticBundle.imageResId ?: lesson.imageResId
                        if (imgRes != null) {
                            Card(
                                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                                shape = RoundedCornerShape(10.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        modifier = Modifier.padding(bottom = 8.dp)
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.Calculate,
                                            contentDescription = null,
                                            tint = GoldXp,
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Text(
                                            text = "Gráfico Diagnóstico em Alta Resolução",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                color = GoldXp,
                                                fontWeight = FontWeight.Bold
                                            )
                                        )
                                    }

                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(230.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(Color(0xFF0D1322)),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Image(
                                            painter = painterResource(id = imgRes),
                                            contentDescription = "Gráfico Explicativo",
                                            modifier = Modifier.fillMaxSize(),
                                            contentScale = ContentScale.Fit
                                        )
                                    }
                                }
                            }
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
                                    text = "Dica de Mestre:",
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
                // ETAPA 2: FÓRMULAS & REGRAS MATEMÁTICAS
                // ========================================================
                CalculusStage.FORMULAS -> {
                    Text(
                        text = "Quadro de Fórmulas e Regras Operatórias",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = "Entenda cada termo matemático e o papel de cada variável nas equações:",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                    )

                    didacticBundle.formulas.forEach { formulaItem ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = formulaItem.termOrFunction,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                // Formula Box com suporte a Símbolos Matemáticos Visuais
                                MathFormulaCard(
                                    formula = formulaItem.syntax,
                                    label = formulaItem.termOrFunction
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                MathFormattedText(
                                    text = formulaItem.description,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextPrimary,
                                    lineHeightSp = 18f
                                )

                                if (formulaItem.parametersInfo.isNotBlank()) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    MathFormattedText(
                                        text = "• Termos e Parâmetros: ${formulaItem.parametersInfo}",
                                        style = MaterialTheme.typography.labelSmall,
                                        color = TextSecondary,
                                        lineHeightSp = 16f
                                    )
                                }
                            }
                        }
                    }
                }

                // ========================================================
                // ETAPA 3: EXEMPLOS RESOLVIDOS PASSO A PASSO
                // ========================================================
                CalculusStage.EXAMPLES -> {
                    Text(
                        text = "Exemplos Resolvidos e Comentados",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = "Veja como aplicar as fórmulas e o raciocínio analítico na prática:",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                    )

                    didacticBundle.solvedExamples.forEach { example ->
                        Card(
                            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                            shape = RoundedCornerShape(8.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                Text(
                                    text = example.title,
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        color = PrimaryViolet,
                                        fontWeight = FontWeight.Bold
                                    )
                                )

                                Spacer(modifier = Modifier.height(6.dp))

                                MathFormulaCard(
                                    formula = example.codeOrFormula,
                                    label = "Passo Analítico"
                                )

                                Spacer(modifier = Modifier.height(8.dp))

                                MathFormattedText(
                                    text = example.explanation,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextSecondary,
                                    lineHeightSp = 19f
                                )
                            }
                        }
                    }
                }

                // ========================================================
                // ETAPA 4: EXERCÍCIOS DE FIXAÇÃO
                // ========================================================
                CalculusStage.EXERCISES -> {
                    Text(
                        text = "Exercícios de Fixação da Aula",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                    Text(
                        text = "Resolva os problemas abaixo para validar seu domínio do conteúdo:",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                    )

                    // Aviso explicativo obrigatório conforme diretrizes pedagógicas
                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.35f)),
                        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(12.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Info,
                                contentDescription = "Aviso pedagógico",
                                tint = GoldXp,
                                modifier = Modifier.size(20.dp).padding(top = 1.dp)
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Regras dos Exercícios & Solução Comentada",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = GoldXp
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "• Cada questão possui 5 alternativas (A, B, C, D, E) com a posição da resposta correta gerada de forma aleatória.\n• A Solução Comentada detalhada com notação matemática passo a passo estará disponível após 2 tentativas incorretas ou assim que você acertar o exercício.",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextSecondary,
                                        lineHeight = 17.sp,
                                        fontSize = 11.5.sp
                                    )
                                )
                            }
                        }
                    }

                    // Controles de expandir e recolher todos os exercícios da aula
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "${exercises.size} Exercícios Disponíveis",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextSecondary,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                            TextButton(
                                onClick = {
                                    exercises.indices.forEach { expandedExercises[it] = true }
                                },
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Expandir todos", fontSize = 11.sp, color = PrimaryViolet)
                            }
                            TextButton(
                                onClick = {
                                    exercises.indices.forEach { expandedExercises[it] = false }
                                },
                                contentPadding = PaddingValues(horizontal = 6.dp, vertical = 2.dp)
                            ) {
                                Text("Recolher todos", fontSize = 11.sp, color = TextTertiary)
                            }
                        }
                    }

                    val optionLetters = listOf("A", "B", "C", "D", "E")

                    exercises.forEachIndexed { index, ex ->
                        val selectedOpt = selectedOptions[index]
                        val isChecked = exerciseResults[index]
                        val wrongCount = wrongAttempts[index] ?: 0
                        val isSolutionUnlocked = (isChecked == true) || (wrongCount >= 2) || isLessonCompletedLocal
                        val showHint = showHints[index] == true
                        val showSolution = showSolutions[index] == true && isSolutionUnlocked
                        val isExpanded = expandedExercises[index] ?: (index == 0)

                        Card(
                            shape = RoundedCornerShape(10.dp),
                            colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                when (isChecked) {
                                    true -> SuccessEmerald
                                    false -> ErrorRose
                                    null -> BorderSubtle
                                }
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .testTag("calculus_exercise_$index")
                        ) {
                            Column(modifier = Modifier.padding(14.dp)) {
                                // Cabeçalho clicável para expandir/recolher
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .clickable { expandedExercises[index] = !isExpanded },
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(6.dp),
                                        modifier = Modifier.weight(1f, fill = false)
                                    ) {
                                        Text(
                                            text = "Exercício ${index + 1}: ${ex.title}",
                                            style = MaterialTheme.typography.titleSmall.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = TextPrimary
                                            )
                                        )

                                        if (ex.geoGebraConfig != null) {
                                            Surface(
                                                color = Color(0xFF1E3A8A).copy(alpha = 0.35f),
                                                shape = RoundedCornerShape(4.dp),
                                                border = androidx.compose.foundation.BorderStroke(0.6.dp, Color(0xFF60A5FA).copy(alpha = 0.6f))
                                            ) {
                                                Text(
                                                    text = "GeoGebra",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = Color(0xFF93C5FD),
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 9.sp
                                                    ),
                                                    modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                                                )
                                            }
                                        }
                                    }

                                    Row(
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.spacedBy(4.dp)
                                    ) {
                                        if (isChecked == true) {
                                            Surface(
                                                color = SuccessEmerald.copy(alpha = 0.2f),
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Row(
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Icon(
                                                        imageVector = Icons.Default.CheckCircle,
                                                        contentDescription = null,
                                                        tint = SuccessEmerald,
                                                        modifier = Modifier.size(12.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Text(
                                                        text = "Correto!",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            color = SuccessEmerald,
                                                            fontWeight = FontWeight.Bold
                                                        )
                                                    )
                                                }
                                            }
                                        } else if (isChecked == false) {
                                            Surface(
                                                color = ErrorRose.copy(alpha = 0.2f),
                                                shape = RoundedCornerShape(4.dp)
                                            ) {
                                                Text(
                                                    text = "Tentativa $wrongCount/2",
                                                    style = MaterialTheme.typography.labelSmall.copy(
                                                        color = ErrorRose,
                                                        fontWeight = FontWeight.Bold,
                                                        fontSize = 11.sp
                                                    ),
                                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                                )
                                            }
                                        }

                                        IconButton(
                                            onClick = { expandedExercises[index] = !isExpanded },
                                            modifier = Modifier.size(24.dp)
                                        ) {
                                            Icon(
                                                imageVector = if (isExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                                contentDescription = if (isExpanded) "Recolher exercício" else "Expandir exercício",
                                                tint = if (isExpanded) PrimaryViolet else TextSecondary,
                                                modifier = Modifier.size(20.dp)
                                            )
                                        }
                                    }
                                }

                                AnimatedVisibility(
                                    visible = isExpanded,
                                    enter = fadeIn() + expandVertically(),
                                    exit = fadeOut() + shrinkVertically()
                                ) {
                                    Column {
                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Renderização interativa do GeoGebra se configurado
                                        if (ex.geoGebraConfig != null) {
                                            GeoGebraMathGraphView(
                                                config = ex.geoGebraConfig,
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(230.dp)
                                            )
                                            Spacer(modifier = Modifier.height(10.dp))
                                        }

                                        // Enunciado com notação matemática e LaTeX
                                        Surface(
                                            color = SurfaceElevated,
                                            shape = RoundedCornerShape(6.dp),
                                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                            modifier = Modifier.fillMaxWidth()
                                        ) {
                                            Column(modifier = Modifier.padding(12.dp)) {
                                                MathFormattedText(
                                                    text = ex.problemStatement,
                                                    style = MaterialTheme.typography.bodyMedium,
                                                    color = TextPrimary,
                                                    lineHeightSp = 21f
                                                )
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(12.dp))

                                        // 5 Alternativas (A, B, C, D, E) com notação matemática formatada
                                        ex.options.forEachIndexed { optIdx, optText ->
                                            val isSelected = selectedOpt == optIdx
                                            val letter = optionLetters.getOrElse(optIdx) { "${optIdx + 1}" }

                                            Surface(
                                                color = if (isSelected) PrimaryViolet.copy(alpha = 0.15f) else Color.Transparent,
                                                shape = RoundedCornerShape(6.dp),
                                                border = androidx.compose.foundation.BorderStroke(
                                                    1.dp,
                                                    if (isSelected) PrimaryViolet else BorderSubtle.copy(alpha = 0.7f)
                                                ),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 3.dp)
                                                    .clickable {
                                                        selectedOptions[index] = optIdx
                                                        exerciseResults.remove(index)
                                                    }
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .padding(horizontal = 10.dp, vertical = 8.dp),
                                                    verticalAlignment = Alignment.CenterVertically
                                                ) {
                                                    Surface(
                                                        color = if (isSelected) PrimaryViolet else SurfaceElevated,
                                                        shape = CircleShape,
                                                        modifier = Modifier.size(24.dp),
                                                        border = androidx.compose.foundation.BorderStroke(
                                                            1.dp,
                                                            if (isSelected) PrimaryViolet else BorderSubtle
                                                        )
                                                    ) {
                                                        Box(contentAlignment = Alignment.Center) {
                                                            Text(
                                                                text = letter,
                                                                style = MaterialTheme.typography.labelSmall.copy(
                                                                    fontWeight = FontWeight.Bold,
                                                                    color = if (isSelected) Color.White else TextSecondary,
                                                                    fontSize = 11.sp
                                                                )
                                                            )
                                                        }
                                                    }

                                                    Spacer(modifier = Modifier.width(10.dp))

                                                    Column(modifier = Modifier.weight(1f)) {
                                                        MathFormattedText(
                                                            text = optText,
                                                            style = MaterialTheme.typography.bodySmall,
                                                            color = if (isSelected) TextPrimary else TextSecondary,
                                                            lineHeightSp = 18f
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        Spacer(modifier = Modifier.height(10.dp))

                                        // Botões de Ação (Dica, Solução Comentada e Conferir)
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.spacedBy(6.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            // Botão de Dica
                                            TextButton(
                                                onClick = { showHints[index] = !showHint },
                                                shape = RoundedCornerShape(6.dp)
                                            ) {
                                                Icon(
                                                    imageVector = Icons.Default.HelpOutline,
                                                    contentDescription = null,
                                                    tint = GoldXp,
                                                    modifier = Modifier.size(16.dp)
                                                )
                                                Spacer(modifier = Modifier.width(4.dp))
                                                Text(
                                                    text = if (showHint) "Ocultar Dica" else "Dica",
                                                    style = MaterialTheme.typography.labelSmall.copy(color = GoldXp)
                                                )
                                            }

                                            // Botão de Solução Comentada (liberada após 2 erros ou acerto)
                                            if (isSolutionUnlocked) {
                                                TextButton(
                                                    onClick = { showSolutions[index] = !showSolution },
                                                    shape = RoundedCornerShape(6.dp)
                                                ) {
                                                    Icon(
                                                        imageVector = if (showSolution) Icons.Default.VisibilityOff else Icons.Default.Visibility,
                                                        contentDescription = null,
                                                        tint = SuccessEmerald,
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(4.dp))
                                                    Text(
                                                        text = if (showSolution) "Ocultar Solução" else "Solução Comentada",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            color = SuccessEmerald,
                                                            fontWeight = FontWeight.SemiBold
                                                        )
                                                    )
                                                }
                                            } else {
                                                Surface(
                                                    color = SurfaceElevated.copy(alpha = 0.5f),
                                                    shape = RoundedCornerShape(6.dp),
                                                    border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle.copy(alpha = 0.5f))
                                                ) {
                                                    Row(
                                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Icon(
                                                            imageVector = Icons.Default.Lock,
                                                            contentDescription = "Bloqueado",
                                                            tint = TextTertiary,
                                                            modifier = Modifier.size(13.dp)
                                                        )
                                                        Spacer(modifier = Modifier.width(4.dp))
                                                        Text(
                                                            text = if (wrongCount == 1) "Solução (1/2 erros)" else "Solução (bloqueada)",
                                                            style = MaterialTheme.typography.labelSmall.copy(
                                                                color = TextTertiary,
                                                                fontSize = 10.5.sp
                                                            )
                                                        )
                                                    }
                                                }
                                            }

                                            Spacer(modifier = Modifier.weight(1f))

                                            // Botão Conferir Resposta
                                            Button(
                                                onClick = {
                                                    val choice = selectedOptions[index]
                                                    if (choice != null) {
                                                        val isCorrect = (choice == ex.correctIndex)
                                                        exerciseResults[index] = isCorrect
                                                        if (!isCorrect) {
                                                            val nextWrong = (wrongAttempts[index] ?: 0) + 1
                                                            wrongAttempts[index] = nextWrong
                                                            progressRepository.markLessonForReview(lesson.id)
                                                            if (nextWrong >= 2) {
                                                                showSolutions[index] = true
                                                            }
                                                        } else {
                                                            showSolutions[index] = true
                                                        }
                                                    }
                                                },
                                                enabled = selectedOpt != null,
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = PrimaryViolet,
                                                    contentColor = Color.White
                                                ),
                                                shape = RoundedCornerShape(6.dp),
                                                modifier = Modifier.height(36.dp)
                                            ) {
                                                Text("Conferir", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                                            }
                                        }

                                        // Dica
                                        AnimatedVisibility(visible = showHint, enter = fadeIn(), exit = fadeOut()) {
                                            Surface(
                                                color = GoldXp.copy(alpha = 0.12f),
                                                shape = RoundedCornerShape(6.dp),
                                                border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.3f)),
                                                modifier = Modifier.fillMaxWidth().padding(top = 8.dp)
                                            ) {
                                                Row(modifier = Modifier.padding(10.dp)) {
                                                    Icon(
                                                        imageVector = Icons.Default.Lightbulb,
                                                        contentDescription = null,
                                                        tint = GoldXp,
                                                        modifier = Modifier.size(16.dp)
                                                    )
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                    Column {
                                                        MathFormattedText(
                                                            text = ex.hint,
                                                            style = MaterialTheme.typography.bodySmall,
                                                            color = TextPrimary,
                                                            lineHeightSp = 18f
                                                        )
                                                    }
                                                }
                                            }
                                        }

                                        // Solução Comentada com LaTeX e passos didáticos
                                        AnimatedVisibility(visible = showSolution, enter = fadeIn(), exit = fadeOut()) {
                                            Box(modifier = Modifier.padding(top = 10.dp)) {
                                                StepByStepSolutionView(solutionText = ex.stepByStepSolution)
                                            }
                                        }

                                        // BOTÃO PROSSEGUIR PARA O PRÓXIMO EXERCÍCIO AO ACERTAR
                                        if (isChecked == true) {
                                            Spacer(modifier = Modifier.height(12.dp))
                                            Button(
                                                onClick = {
                                                    expandedExercises[index] = false
                                                    if (index + 1 < exercises.size) {
                                                        expandedExercises[index + 1] = true
                                                        coroutineScope.launch {
                                                            scrollState.animateScrollTo(scrollState.value + 400)
                                                        }
                                                    }
                                                },
                                                colors = ButtonDefaults.buttonColors(
                                                    containerColor = SuccessEmerald,
                                                    contentColor = Color.White
                                                ),
                                                shape = RoundedCornerShape(8.dp),
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .height(44.dp)
                                                    .testTag("btn_calculus_next_exercise_$index")
                                            ) {
                                                Row(
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.Center
                                                ) {
                                                    Text(
                                                        text = if (index + 1 < exercises.size) {
                                                            "Prosseguir para o Próximo Exercício (${index + 2}/${exercises.size})"
                                                        } else {
                                                            "🎉 Todos os Exercícios da Aula Concluídos!"
                                                        },
                                                        style = MaterialTheme.typography.labelMedium.copy(
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 12.5.sp
                                                        )
                                                    )
                                                    Spacer(modifier = Modifier.width(8.dp))
                                                    Icon(
                                                        imageVector = if (index + 1 < exercises.size) Icons.AutoMirrored.Filled.ArrowForward else Icons.Default.CheckCircle,
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
        }
    }
}
