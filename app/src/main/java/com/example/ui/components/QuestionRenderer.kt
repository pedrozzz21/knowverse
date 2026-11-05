package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.assessment.AnswerEvaluator
import com.example.data.assessment.BankQuestion
import com.example.data.assessment.ChartData
import com.example.data.assessment.Feedback
import com.example.data.assessment.QDifficulty
import com.example.data.assessment.QuestionFormat
import com.example.data.assessment.UserAnswer
import com.example.data.assessment.Verdict
import com.example.data.python.PythonEngine
import com.example.ui.theme.AccentCyan
import com.example.ui.theme.BorderActive
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CodeTextStyle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.ErrorRoseContainer
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.PrimaryVioletLight
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SuccessEmeraldContainer
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TerminalBg
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.ui.theme.WarningAmber
import com.example.ui.theme.WarningAmberContainer
import kotlinx.coroutines.launch

/**
 * Renderizador interativo unificado para os 8 formatos de questão (Etapa 5),
 * incluindo gráficos nativos, associação interativa, rubrica discursiva,
 * execução de testes automatizados em Python e Feedback Inteligente.
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun QuestionRendererCard(
    bankQuestion: BankQuestion,
    questionNumber: Int? = null,
    pythonEngine: PythonEngine? = null,
    existingFeedback: Feedback? = null,
    onEvaluated: (UserAnswer, Feedback) -> Unit = { _, _ -> }
) {
    val q = bankQuestion.question
    val lesson = bankQuestion.lesson
    val scope = rememberCoroutineScope()

    var selectedOption by remember(q.id) { mutableIntStateOf(-1) }
    var selectedBool by remember(q.id) { mutableStateOf<Boolean?>(null) }
    var textAnswer by remember(q.id) { mutableStateOf(q.code?.starter ?: "") }
    val pairSelections = remember(q.id) { mutableStateMapOf<String, String>() }
    var activeLeftKey by remember(q.id) { mutableStateOf<String?>(null) }
    val shuffledRights = remember(q.id) {
        q.pairs.map { it.second }.shuffled()
    }

    var feedback by remember(q.id, existingFeedback) { mutableStateOf(existingFeedback) }
    var isRunningCode by remember(q.id) { mutableStateOf(false) }

    LaunchedEffect(q.id) {
        if (q.format == QuestionFormat.CODE && textAnswer.isBlank()) {
            textAnswer = q.code?.starter.orEmpty()
        }
    }

    val diffColor = when (q.difficulty) {
        QDifficulty.EASY -> SuccessEmerald
        QDifficulty.MEDIUM -> WarningAmber
        QDifficulty.HARD -> ErrorRose
    }

    Surface(
        color = ObsidianSurface,
        shape = RoundedCornerShape(14.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderSubtle, RoundedCornerShape(14.dp))
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            // Cabeçalho de Metadados (Disciplina, Subtópico, Formato, Dificuldade, Fonte)
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                if (questionNumber != null) {
                    MetadataBadge(text = "Questão $questionNumber", color = PrimaryViolet)
                }
                MetadataBadge(text = lesson.subject, color = AccentCyan)
                MetadataBadge(text = lesson.subtopic, color = PrimaryVioletLight)
                MetadataBadge(text = q.format.label, color = TextSecondary)
                MetadataBadge(text = q.difficulty.label, color = diffColor)
                MetadataBadge(text = q.source.label, color = TextTertiary)
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Habilidade e Aula Relacionada
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Default.School,
                    contentDescription = null,
                    tint = TextTertiary,
                    modifier = Modifier.size(14.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "${lesson.title} • Habilidade: ${q.skill}",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextTertiary
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // Enunciado com suporte a notação matemática
            MathFormattedText(
                text = q.statement,
                style = MaterialTheme.typography.bodyLarge.copy(
                    color = TextPrimary,
                    lineHeight = 23.sp
                )
            )

            // Se for formato CHART, renderiza o gráfico/tabela de dados
            if (q.chart != null) {
                Spacer(modifier = Modifier.height(14.dp))
                ChartVisualBox(chart = q.chart)
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Corpo de interação específico para cada um dos 8 formatos
            when (q.format) {
                QuestionFormat.MULTIPLE_CHOICE, QuestionFormat.CHART -> {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        q.options.forEachIndexed { idx, optText ->
                            val isSelected = selectedOption == idx
                            val isSubmitted = feedback != null
                            val isCorrectOpt = idx == q.answerIndex

                            val borderColor = when {
                                isSubmitted && isCorrectOpt -> SuccessEmerald
                                isSubmitted && isSelected && !isCorrectOpt -> ErrorRose
                                isSelected -> BorderActive
                                else -> BorderSubtle
                            }
                            val bgColor = when {
                                isSubmitted && isCorrectOpt -> SuccessEmeraldContainer.copy(alpha = 0.35f)
                                isSubmitted && isSelected && !isCorrectOpt -> ErrorRoseContainer.copy(alpha = 0.35f)
                                isSelected -> PrimaryViolet.copy(alpha = 0.14f)
                                else -> SurfaceCard
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(bgColor)
                                    .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                                    .clickable(enabled = feedback == null) { selectedOption = idx }
                                    .padding(12.dp)
                            ) {
                                Box(
                                    contentAlignment = Alignment.Center,
                                    modifier = Modifier
                                        .size(26.dp)
                                        .clip(CircleShape)
                                        .background(if (isSelected) PrimaryViolet else SurfaceElevated)
                                ) {
                                    Text(
                                        text = ('A' + idx).toString(),
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                }
                                Spacer(modifier = Modifier.width(10.dp))
                                MathFormattedText(
                                    text = optText,
                                    style = MaterialTheme.typography.bodyMedium.copy(color = TextPrimary),
                                    modifier = Modifier.weight(1f)
                                )
                            }
                        }
                    }
                }

                QuestionFormat.TRUE_FALSE -> {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        listOf(true to "Verdadeiro (V)", false to "Falso (F)").forEach { (boolVal, label) ->
                            val isSelected = selectedBool == boolVal
                            val borderColor = if (isSelected) BorderActive else BorderSubtle
                            val bgColor = if (isSelected) PrimaryViolet.copy(alpha = 0.18f) else SurfaceCard
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .weight(1f)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(bgColor)
                                    .border(1.dp, borderColor, RoundedCornerShape(10.dp))
                                    .clickable(enabled = feedback == null) { selectedBool = boolVal }
                                    .padding(vertical = 14.dp, horizontal = 12.dp)
                            ) {
                                Text(
                                    text = label,
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isSelected) PrimaryVioletLight else TextPrimary
                                )
                            }
                        }
                    }
                }

                QuestionFormat.MATCHING -> {
                    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                        Text(
                            text = "Toque em um item da Coluna A e em seguida no seu par correspondente na Coluna B:",
                            style = MaterialTheme.typography.labelMedium,
                            color = TextSecondary
                        )
                        q.pairs.forEachIndexed { idx, (leftItem, _) ->
                            val isCurrent = activeLeftKey == leftItem
                            val chosenRight = pairSelections[leftItem]
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(SurfaceCard)
                                    .border(
                                        1.dp,
                                        if (isCurrent) BorderActive else BorderSubtle,
                                        RoundedCornerShape(10.dp)
                                    )
                                    .clickable(enabled = feedback == null) {
                                        activeLeftKey = if (isCurrent) null else leftItem
                                    }
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = "${idx + 1}. $leftItem",
                                    style = MaterialTheme.typography.bodyMedium,
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = if (chosenRight != null) "Associado a: $chosenRight" else "Toque para selecionar a associação abaixo",
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (chosenRight != null) PrimaryVioletLight else TextTertiary
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = "Opções da Coluna B (toque para vincular ao item selecionado):",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                        shuffledRights.forEach { rightOption ->
                            val usedBy = pairSelections.entries.firstOrNull { it.value == rightOption }?.key
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(if (usedBy != null) PrimaryViolet.copy(alpha = 0.12f) else SurfaceElevated)
                                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                                    .clickable(enabled = feedback == null) {
                                        val targetKey = activeLeftKey
                                            ?: q.pairs.firstOrNull { !pairSelections.containsKey(it.first) }?.first
                                            ?: q.pairs.firstOrNull()?.first
                                        if (targetKey != null) {
                                            pairSelections[targetKey] = rightOption
                                            val nextUnassigned = q.pairs.firstOrNull { !pairSelections.containsKey(it.first) }?.first
                                            activeLeftKey = nextUnassigned
                                        }
                                    }
                                    .padding(10.dp)
                            ) {
                                Text(
                                    text = rightOption,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextPrimary,
                                    modifier = Modifier.weight(1f)
                                )
                                if (usedBy != null) {
                                    Spacer(modifier = Modifier.width(8.dp))
                                    MetadataBadge(text = usedBy.take(18), color = PrimaryViolet)
                                }
                            }
                        }
                    }
                }

                QuestionFormat.FILL_BLANK -> {
                    OutlinedTextField(
                        value = textAnswer,
                        onValueChange = { if (feedback == null) textAnswer = it },
                        label = { Text("Preencha o termo da lacuna") },
                        singleLine = true,
                        colors = textFieldColors(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                QuestionFormat.NUMERIC -> {
                    val unitHint = if (q.unit.isNotBlank()) " (Unidade esperada: ${q.unit})" else ""
                    OutlinedTextField(
                        value = textAnswer,
                        onValueChange = { if (feedback == null) textAnswer = it },
                        label = { Text("Resposta numérica$unitHint") },
                        placeholder = { Text("Ex.: 12,5") },
                        singleLine = true,
                        colors = textFieldColors(),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                QuestionFormat.ESSAY -> {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        if (q.rubric.isNotEmpty()) {
                            Surface(
                                color = SurfaceCard,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = "Rubrica explícita de avaliação:",
                                        style = MaterialTheme.typography.labelMedium,
                                        fontWeight = FontWeight.Bold,
                                        color = PrimaryVioletLight
                                    )
                                    q.rubric.forEach { crit ->
                                        Text(
                                            text = "• ${crit.criterion} (${crit.points} pts)",
                                            style = MaterialTheme.typography.labelSmall,
                                            color = TextSecondary,
                                            modifier = Modifier.padding(top = 3.dp)
                                        )
                                    }
                                }
                            }
                        }
                        OutlinedTextField(
                            value = textAnswer,
                            onValueChange = { if (feedback == null) textAnswer = it },
                            label = { Text("Redija sua justificativa conceitual e analítica") },
                            minLines = 4,
                            maxLines = 8,
                            colors = textFieldColors(),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }

                QuestionFormat.CODE -> {
                    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.Code,
                                    contentDescription = null,
                                    tint = AccentCyan,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Editor Python • ${q.code?.tests?.size ?: 0} casos de teste automatizados",
                                    style = MaterialTheme.typography.labelMedium,
                                    color = AccentCyan
                                )
                            }
                        }
                        OutlinedTextField(
                            value = textAnswer,
                            onValueChange = { if (feedback == null) textAnswer = it },
                            textStyle = CodeTextStyle.copy(fontSize = 13.sp, color = TextPrimary),
                            minLines = 6,
                            maxLines = 14,
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = TerminalBg,
                                unfocusedContainerColor = TerminalBg,
                                focusedBorderColor = BorderActive,
                                unfocusedBorderColor = BorderSubtle,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                        val visibleTests = q.code?.tests?.filter { !it.hidden }.orEmpty()
                        if (visibleTests.isNotEmpty()) {
                            Surface(
                                color = SurfaceCard,
                                shape = RoundedCornerShape(8.dp),
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Column(modifier = Modifier.padding(10.dp)) {
                                    Text(
                                        text = "Exemplos de casos de teste:",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = FontWeight.Bold,
                                        color = TextSecondary
                                    )
                                    visibleTests.forEach { tc ->
                                        Text(
                                            text = "${tc.call}  ->  ${tc.expected}",
                                            style = CodeTextStyle.copy(fontSize = 11.sp, color = TextTertiary),
                                            modifier = Modifier.padding(top = 2.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // Botões de Ação (Validar Resposta / Tentar Novamente)
            if (feedback == null) {
                Button(
                    onClick = {
                        if (q.format == QuestionFormat.CODE && q.code != null && pythonEngine != null) {
                            isRunningCode = true
                            scope.launch {
                                val fb = AnswerEvaluator.evaluateCode(q, textAnswer) { harness ->
                                    val exec = pythonEngine.executeCode(harness)
                                    AnswerEvaluator.CodeRunResult(
                                        success = exec.isSuccess,
                                        stdout = exec.stdout,
                                        error = if (!exec.isSuccess) (exec.friendlyError ?: exec.rawError) else null
                                    )
                                }
                                isRunningCode = false
                                feedback = fb
                                onEvaluated(UserAnswer.Text(textAnswer), fb)
                            }
                        } else {
                            val ans: UserAnswer? = when (q.format) {
                                QuestionFormat.MULTIPLE_CHOICE, QuestionFormat.CHART ->
                                    if (selectedOption >= 0) UserAnswer.Choice(selectedOption) else null
                                QuestionFormat.TRUE_FALSE ->
                                    selectedBool?.let { UserAnswer.Bool(it) }
                                QuestionFormat.MATCHING ->
                                    if (pairSelections.isNotEmpty()) UserAnswer.Pairs(pairSelections.toMap()) else null
                                QuestionFormat.FILL_BLANK,
                                QuestionFormat.NUMERIC,
                                QuestionFormat.ESSAY,
                                QuestionFormat.CODE -> UserAnswer.Text(textAnswer)
                            }
                            val fb = AnswerEvaluator.evaluate(q, ans)
                            feedback = fb
                            if (ans != null) {
                                onEvaluated(ans, fb)
                            }
                        }
                    },
                    enabled = !isRunningCode,
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                    shape = RoundedCornerShape(10.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (isRunningCode) {
                        CircularProgressIndicator(
                            color = TextPrimary,
                            strokeWidth = 2.dp,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Executando casos de teste Python...")
                    } else {
                        Icon(
                            imageVector = if (q.format == QuestionFormat.CODE) Icons.Default.PlayArrow else Icons.Default.Check,
                            contentDescription = null,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = if (q.format == QuestionFormat.CODE) "Executar Testes e Avaliar" else "Verificar e Avaliar Resposta",
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            } else {
                IntelligentFeedbackPanel(
                    feedback = feedback!!,
                    onRetry = {
                        feedback = null
                    }
                )
            }
        }
    }
}

@Composable
private fun IntelligentFeedbackPanel(
    feedback: Feedback,
    onRetry: () -> Unit
) {
    val accent = when (feedback.verdict) {
        Verdict.CORRECT -> SuccessEmerald
        Verdict.PARTIAL -> WarningAmber
        Verdict.INCORRECT, Verdict.UNANSWERED -> ErrorRose
    }
    val containerBg = when (feedback.verdict) {
        Verdict.CORRECT -> SuccessEmeraldContainer.copy(alpha = 0.28f)
        Verdict.PARTIAL -> WarningAmberContainer.copy(alpha = 0.28f)
        Verdict.INCORRECT, Verdict.UNANSWERED -> ErrorRoseContainer.copy(alpha = 0.28f)
    }

    AnimatedVisibility(visible = true) {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(containerBg)
                .border(1.dp, accent, RoundedCornerShape(12.dp))
                .padding(14.dp)
        ) {
            // Título + Pontuação
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.weight(1f)
                ) {
                    Icon(
                        imageVector = if (feedback.isCorrect) Icons.Default.Check else Icons.Default.Close,
                        contentDescription = null,
                        tint = accent,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = feedback.headline,
                        style = MaterialTheme.typography.titleSmall,
                        fontWeight = FontWeight.Bold,
                        color = accent
                    )
                }
                MetadataBadge(
                    text = "Score: ${(feedback.score * 100).toInt()}%",
                    color = accent
                )
            }

            // Raciocínio completo
            Surface(
                color = DeepNavy.copy(alpha = 0.65f),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp)) {
                    Text(
                        text = "Explicação do Raciocínio:",
                        style = MaterialTheme.typography.labelMedium,
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    MathFormattedText(
                        text = feedback.reasoning,
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, lineHeight = 19.sp)
                    )
                }
            }

            // Diagnóstico do Conceito Não Compreendido (quando erra ou acerta parcialmente)
            if (!feedback.misunderstoodConcept.isNullOrBlank()) {
                Surface(
                    color = DeepNavy.copy(alpha = 0.75f),
                    shape = RoundedCornerShape(8.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, WarningAmber.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                ) {
                    Row(
                        verticalAlignment = Alignment.Top,
                        modifier = Modifier.padding(10.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Psychology,
                            contentDescription = null,
                            tint = WarningAmber,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Column {
                            Text(
                                text = "Conceito a revisar (Diagnóstico):",
                                style = MaterialTheme.typography.labelMedium,
                                fontWeight = FontWeight.Bold,
                                color = WarningAmber
                            )
                            Text(
                                text = feedback.misunderstoodConcept,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextPrimary
                            )
                        }
                    }
                }
            }

            // Detalhes de Rubrica / Testes / Associações
            if (feedback.details.isNotEmpty()) {
                Column(verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    feedback.details.forEach { line ->
                        Text(
                            text = "• $line",
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                    }
                }
            }

            // Orientação para resolver novamente
            if (!feedback.retryTip.isNullOrBlank()) {
                Row(verticalAlignment = Alignment.Top) {
                    Icon(
                        imageVector = Icons.Default.Lightbulb,
                        contentDescription = null,
                        tint = AccentCyan,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Orientação: ${feedback.retryTip}",
                        style = MaterialTheme.typography.bodySmall,
                        color = AccentCyan
                    )
                }
            }

            OutlinedButton(
                onClick = onRetry,
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.align(Alignment.End)
            ) {
                Icon(
                    imageVector = Icons.Default.Refresh,
                    contentDescription = null,
                    modifier = Modifier.size(16.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text("Tentar Novamente")
            }
        }
    }
}

@Composable
private fun ChartVisualBox(chart: ChartData) {
    val series = chart.series.firstOrNull()
    val values = series?.values.orEmpty()
    val maxVal = (values.maxOrNull()?.let { if (it == 0.0) 1.0 else kotlin.math.abs(it) } ?: 1.0)

    Surface(
        color = SurfaceCard,
        shape = RoundedCornerShape(10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, BorderSubtle, RoundedCornerShape(10.dp))
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = chart.title,
                style = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold,
                color = PrimaryVioletLight
            )
            if (series != null && series.name.isNotBlank()) {
                Text(
                    text = series.name + if (chart.unit.isNotBlank()) " (${chart.unit})" else "",
                    style = MaterialTheme.typography.labelSmall,
                    color = TextTertiary
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            chart.labels.forEachIndexed { i, label ->
                val v = values.getOrNull(i) ?: 0.0
                val fraction = (kotlin.math.abs(v) / maxVal).toFloat().coerceIn(0.06f, 1f)
                Column(modifier = Modifier.padding(vertical = 4.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.labelSmall,
                            color = TextSecondary
                        )
                        Text(
                            text = if (v % 1.0 == 0.0) "${v.toLong()} ${chart.unit}".trim() else "$v ${chart.unit}".trim(),
                            style = MaterialTheme.typography.labelSmall,
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    }
                    Spacer(modifier = Modifier.height(3.dp))
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp))
                            .background(DeepNavy)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth(fraction)
                                .height(8.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(if (v >= 0) PrimaryViolet else ErrorRose)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun MetadataBadge(text: String, color: Color) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(6.dp))
            .background(color.copy(alpha = 0.14f))
            .border(1.dp, color.copy(alpha = 0.4f), RoundedCornerShape(6.dp))
            .padding(horizontal = 8.dp, vertical = 3.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Medium,
            color = color
        )
    }
}

@Composable
private fun textFieldColors() = OutlinedTextFieldDefaults.colors(
    focusedContainerColor = SurfaceCard,
    unfocusedContainerColor = SurfaceCard,
    focusedBorderColor = BorderActive,
    unfocusedBorderColor = BorderSubtle,
    focusedTextColor = TextPrimary,
    unfocusedTextColor = TextPrimary,
    focusedLabelColor = PrimaryVioletLight,
    unfocusedLabelColor = TextSecondary
)
