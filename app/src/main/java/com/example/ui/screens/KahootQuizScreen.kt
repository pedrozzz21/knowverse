package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Timer
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.audio.DynamicAudioEngine
import com.example.data.local.UserProgressRepository
import com.example.data.model.KahootModule
import com.example.ui.components.KnowverseIcons
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CodeTextStyle
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
import kotlinx.coroutines.delay

// Refined, corporate Kahoot card colors (no garish neon)
private val CardRed = Color(0xFFDC2626)     // Triangle
private val CardBlue = Color(0xFF2563EB)    // Diamond
private val CardAmber = Color(0xFFD97706)   // Circle
private val CardGreen = Color(0xFF16A34A)   // Square

@Composable
fun KahootQuizScreen(
    module: KahootModule,
    progressRepository: UserProgressRepository,
    onNavigateBack: () -> Unit
) {
    val audioEngine = remember { DynamicAudioEngine() }

    DisposableEffect(Unit) {
        onDispose {
            audioEngine.release()
        }
    }

    BackHandler {
        audioEngine.stopMusic()
        onNavigateBack()
    }

    val shuffledQuestions = remember(module.id) { module.questions.shuffled() }

    var currentQuestionIndex by remember { mutableIntStateOf(0) }
    var selectedOptionIndex by remember { mutableStateOf<Int?>(null) }
    var isAnswerSubmitted by remember { mutableStateOf(false) }
    var remainingSeconds by remember { mutableIntStateOf(30) }
    var totalScore by remember { mutableIntStateOf(0) }
    var correctCount by remember { mutableIntStateOf(0) }
    var isQuizCompleted by remember { mutableStateOf(false) }

    val currentQuestion = shuffledQuestions.getOrNull(currentQuestionIndex)

    // 30 seconds timer loop per question
    LaunchedEffect(currentQuestionIndex, isAnswerSubmitted, isQuizCompleted) {
        if (!isAnswerSubmitted && !isQuizCompleted && currentQuestion != null) {
            remainingSeconds = 30
            audioEngine.startTensionMusic()
            while (remainingSeconds > 0 && !isAnswerSubmitted) {
                delay(1000L)
                remainingSeconds--
                audioEngine.updateRemainingTime(remainingSeconds)
            }
            if (remainingSeconds == 0 && !isAnswerSubmitted) {
                // Time up! Count as wrong
                isAnswerSubmitted = true
                selectedOptionIndex = -1
                audioEngine.stopMusic()
                audioEngine.playWrongSound()
            }
        }
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
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = {
                                audioEngine.stopMusic()
                                onNavigateBack()
                            },
                            modifier = Modifier.size(36.dp).testTag("final_test_back_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Voltar",
                                tint = TextPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Text(
                                text = module.title,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                            )
                            Text(
                                text = if (!isQuizCompleted) "Questão ${currentQuestionIndex + 1} de ${shuffledQuestions.size}" else "Resultados Finais",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = TextSecondary
                                )
                            )
                        }
                    }

                    // Score Badge
                    Surface(
                        color = SurfaceCard,
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        ) {
                            Icon(
                                imageVector = KnowverseIcons.Trophy,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text(
                                text = "$totalScore pts",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                            )
                        }
                    }
                }
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (isQuizCompleted) {
                // ==================== PODIUM SCREEN ====================
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(SurfaceElevated)
                            .border(1.dp, BorderSubtle, CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = KnowverseIcons.Trophy,
                            contentDescription = null,
                            tint = GoldXp,
                            modifier = Modifier.size(32.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    val isPerfect = correctCount == shuffledQuestions.size
                    val percentage = if (shuffledQuestions.isNotEmpty()) ((correctCount.toFloat() / shuffledQuestions.size) * 100).toInt() else 0

                    Text(
                        text = if (isPerfect) "Gabaritado! 100% de Acertos" else "Desafio Concluído!",
                        style = MaterialTheme.typography.headlineMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (isPerfect) GoldXp else TextPrimary
                        )
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = if (isPerfect)
                            "Parabéns! Você alcançou 100% e desbloqueou o próximo nível!"
                        else
                            "Você acertou $percentage% das questões do teste.",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = if (isPerfect) SuccessEmerald else TextSecondary
                        )
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    // 100% requirement status card
                    if (!isPerfect) {
                        Surface(
                            color = ErrorRose.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, ErrorRose.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = "Requisito para o Próximo Nível",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = ErrorRose,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Para liberar o nível superior, é obrigatório obter 100% de acertos ($correctCount/${shuffledQuestions.size} obtidos). Tente novamente para gabaritar!",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextSecondary,
                                        fontSize = 11.sp
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(14.dp))
                    }

                    // Stats Card
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                    ) {
                        Column(
                            modifier = Modifier.padding(16.dp),
                            verticalArrangement = Arrangement.spacedBy(12.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Pontuação Final", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                                Text("$totalScore pontos", color = TextPrimary, fontWeight = FontWeight.SemiBold)
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("Acertos", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                                Text(
                                    text = "$correctCount de ${module.questions.size}",
                                    color = if (isPerfect) SuccessEmerald else GoldXp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text("XP Ganho", color = TextSecondary, style = MaterialTheme.typography.bodyMedium)
                                Text("+${totalScore / 10} XP", color = GoldXp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(24.dp))

                    if (!isPerfect) {
                        Button(
                            onClick = {
                                currentQuestionIndex = 0
                                selectedOptionIndex = null
                                isAnswerSubmitted = false
                                totalScore = 0
                                correctCount = 0
                                isQuizCompleted = false
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryViolet,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(44.dp)
                                .testTag("retry_final_test_btn")
                        ) {
                            Text("Tentar Novamente (Refazer Teste)", fontWeight = FontWeight.SemiBold)
                        }

                        Spacer(modifier = Modifier.height(10.dp))
                    }

                    Button(
                        onClick = {
                            audioEngine.stopMusic()
                            onNavigateBack()
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (isPerfect) PrimaryViolet else SurfaceElevated,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(44.dp)
                            .testTag("finish_final_test_btn")
                    ) {
                        Text(
                            text = if (isPerfect) "Continuar para a Trilha Desbloqueada" else "Retornar à Trilha",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            } else if (currentQuestion != null) {
                // ==================== QUIZ QUESTION SCREEN ====================
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        // 30s Countdown Timer Bar
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Icon(
                                imageVector = Icons.Default.Timer,
                                contentDescription = null,
                                tint = if (remainingSeconds <= 10) ErrorRose else TextSecondary,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "${remainingSeconds}s",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (remainingSeconds <= 10) ErrorRose else TextPrimary
                                )
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            LinearProgressIndicator(
                                progress = { remainingSeconds / 30f },
                                modifier = Modifier
                                    .weight(1f)
                                    .height(4.dp)
                                    .clip(RoundedCornerShape(2.dp)),
                                color = if (remainingSeconds <= 10) ErrorRose else PrimaryViolet,
                                trackColor = SurfaceElevated
                            )
                        }

                        Spacer(modifier = Modifier.height(16.dp))

                        // Question Text
                        Text(
                            text = currentQuestion.question,
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary,
                                lineHeight = 22.sp
                            )
                        )

                        // Optional Code Snippet Card
                        if (!currentQuestion.codeSnippet.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(10.dp))
                            Surface(
                                color = SurfaceCard,
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Text(
                                    text = currentQuestion.codeSnippet,
                                    style = CodeTextStyle.copy(fontSize = 12.sp, lineHeight = 18.sp),
                                    modifier = Modifier.padding(10.dp)
                                )
                            }
                        }

                        // Explanation sheet if answered
                        if (isAnswerSubmitted) {
                            Spacer(modifier = Modifier.height(12.dp))
                            val isCorrect = selectedOptionIndex == currentQuestion.correctIndex
                            Surface(
                                color = if (isCorrect) SuccessEmerald.copy(alpha = 0.12f) else ErrorRose.copy(alpha = 0.12f),
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(
                                    1.dp,
                                    if (isCorrect) SuccessEmerald.copy(alpha = 0.4f) else ErrorRose.copy(alpha = 0.4f)
                                ),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.padding(10.dp),
                                    verticalAlignment = Alignment.Top
                                ) {
                                    Icon(
                                        imageVector = if (isCorrect) Icons.Default.Check else Icons.Default.Close,
                                        contentDescription = null,
                                        tint = if (isCorrect) SuccessEmerald else ErrorRose,
                                        modifier = Modifier.size(16.dp).padding(top = 2.dp)
                                    )
                                    Spacer(modifier = Modifier.width(8.dp))
                                    Column {
                                        Text(
                                            text = if (isCorrect) "Correto! Resposta rápida registrada." else "Incorreto!",
                                            style = MaterialTheme.typography.labelMedium.copy(
                                                fontWeight = FontWeight.SemiBold,
                                                color = if (isCorrect) SuccessEmerald else ErrorRose
                                            )
                                        )
                                        Text(
                                            text = currentQuestion.explanation,
                                            style = MaterialTheme.typography.bodySmall.copy(
                                                color = TextSecondary,
                                                lineHeight = 16.sp
                                            ),
                                            modifier = Modifier.padding(top = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }

                    // 4 Kahoot-Style Alternative Buttons
                    Column(
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .navigationBarsPadding()
                    ) {
                        val cardColors = listOf(CardRed, CardBlue, CardAmber, CardGreen)
                        val geometricSymbols = listOf("▲", "◆", "●", "■")

                        currentQuestion.options.forEachIndexed { idx, optText ->
                            val isSelected = selectedOptionIndex == idx
                            val isCorrectAnswer = currentQuestion.correctIndex == idx

                            val bgColor = when {
                                !isAnswerSubmitted -> cardColors[idx % 4]
                                isCorrectAnswer -> SuccessEmerald
                                isSelected -> ErrorRose
                                else -> SurfaceCard
                            }

                            val alpha = if (isAnswerSubmitted && !isCorrectAnswer && !isSelected) 0.4f else 1.0f

                            Surface(
                                color = bgColor.copy(alpha = alpha),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(54.dp)
                                    .clickable(enabled = !isAnswerSubmitted) {
                                        selectedOptionIndex = idx
                                        isAnswerSubmitted = true
                                        audioEngine.stopMusic()

                                        if (idx == currentQuestion.correctIndex) {
                                            correctCount++
                                            val speedBonus = ((remainingSeconds / 30f) * 500).toInt()
                                            val points = 500 + speedBonus
                                            totalScore += points
                                            audioEngine.playCorrectSound()
                                        } else {
                                            audioEngine.playWrongSound()
                                        }
                                    }
                                    .testTag("final_test_opt_$idx")
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 14.dp)
                                ) {
                                    Text(
                                        text = geometricSymbols[idx % 4],
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Text(
                                        text = optText,
                                        style = MaterialTheme.typography.bodyMedium.copy(
                                            color = Color.White,
                                            fontWeight = FontWeight.Medium
                                        ),
                                        modifier = Modifier.weight(1f)
                                    )
                                }
                            }
                        }

                        // Next Question / Finish Button
                        if (isAnswerSubmitted) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Button(
                                onClick = {
                                    if (currentQuestionIndex < module.questions.size - 1) {
                                        currentQuestionIndex++
                                        selectedOptionIndex = null
                                        isAnswerSubmitted = false
                                    } else {
                                        isQuizCompleted = true
                                        val isPerfect = (correctCount == shuffledQuestions.size)
                                        progressRepository.recordTestScore(
                                            moduleId = module.id,
                                            score = totalScore,
                                            xpEarned = totalScore / 10,
                                            isPerfectScore = isPerfect
                                        )
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = PrimaryViolet,
                                    contentColor = Color.White
                                ),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(44.dp)
                                    .testTag("final_test_next_btn")
                            ) {
                                Text(
                                    text = if (currentQuestionIndex < module.questions.size - 1) "Próxima Questão" else "Ver Resultados Finais",
                                    fontWeight = FontWeight.SemiBold
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
