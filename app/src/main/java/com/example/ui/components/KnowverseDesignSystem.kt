package com.example.ui.components

import androidx.annotation.DrawableRes
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

/**
 * Estados semânticos unificados para trilhas, módulos e aulas do KnowVerse.
 * Sempre combina cor, ícone vetorial e rótulo textual para acessibilidade.
 */
enum class KvLearningStatus(
    val label: String,
    val accentColor: Color,
    val containerColor: Color
) {
    COMPLETED("Concluído", SuccessEmerald, Color(0xFF064E3B)),
    IN_PROGRESS("Em andamento", PrimaryVioletLight, Color(0xFF1E3A8A)),
    AVAILABLE("Disponível", AccentCyan, Color(0xFF0C4A6E)),
    NEEDS_REVIEW("Revisão sugerida", WarningAmber, Color(0xFF451A03)),
    LOCKED("Bloqueado", TextTertiary, SurfaceElevated)
}

@Composable
fun KvSectionHeader(
    title: String,
    modifier: Modifier = Modifier,
    subtitle: String? = null,
    accentColor: Color = PrimaryViolet,
    badgeText: String? = null,
    trailingActionText: String? = null,
    onTrailingActionClick: (() -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.weight(1f)
        ) {
            Box(
                modifier = Modifier
                    .width(3.dp)
                    .height(if (subtitle != null) 28.dp else 16.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(accentColor)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column {
                Text(
                    text = title.uppercase(),
                    style = MaterialTheme.typography.labelSmall,
                    color = TextSecondary,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 0.8.sp
                )
                if (!subtitle.isNullOrBlank()) {
                    Text(
                        text = subtitle,
                        style = MaterialTheme.typography.bodySmall,
                        color = TextTertiary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }

        if (!badgeText.isNullOrBlank()) {
            Surface(
                color = accentColor.copy(alpha = 0.14f),
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, accentColor.copy(alpha = 0.35f))
            ) {
                Text(
                    text = badgeText,
                    style = MaterialTheme.typography.labelSmall,
                    color = accentColor,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                )
            }
        } else if (!trailingActionText.isNullOrBlank() && onTrailingActionClick != null) {
            TextButton(
                onClick = onTrailingActionClick,
                contentPadding = PaddingValues(horizontal = 8.dp, vertical = 2.dp),
                modifier = Modifier.height(30.dp)
            ) {
                Text(
                    text = trailingActionText,
                    style = MaterialTheme.typography.labelMedium,
                    color = PrimaryVioletLight,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
fun KvStatusBadge(
    status: KvLearningStatus,
    modifier: Modifier = Modifier,
    compact: Boolean = false
) {
    val icon: ImageVector = when (status) {
        KvLearningStatus.COMPLETED -> Icons.Default.Check
        KvLearningStatus.IN_PROGRESS -> Icons.Default.PlayArrow
        KvLearningStatus.AVAILABLE -> Icons.Default.PlayArrow
        KvLearningStatus.NEEDS_REVIEW -> Icons.Default.Refresh
        KvLearningStatus.LOCKED -> Icons.Default.Lock
    }

    Surface(
        modifier = modifier,
        color = status.accentColor.copy(alpha = 0.14f),
        shape = RoundedCornerShape(6.dp),
        border = BorderStroke(1.dp, status.accentColor.copy(alpha = 0.35f))
    ) {
        Row(
            modifier = Modifier.padding(
                horizontal = if (compact) 6.dp else 8.dp,
                vertical = if (compact) 2.dp else 4.dp
            ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Icon(
                imageVector = icon,
                contentDescription = status.label,
                tint = status.accentColor,
                modifier = Modifier.size(if (compact) 11.dp else 13.dp)
            )
            Text(
                text = status.label,
                style = MaterialTheme.typography.labelSmall,
                fontSize = if (compact) 10.sp else 11.sp,
                color = status.accentColor,
                fontWeight = FontWeight.SemiBold
            )
        }
    }
}

@Composable
fun KvSubjectProgressRow(
    subjectName: String,
    icon: ImageVector,
    accentColor: Color,
    completedCount: Int,
    totalCount: Int,
    modifier: Modifier = Modifier,
    onClick: (() -> Unit)? = null
) {
    val safeTotal = totalCount.coerceAtLeast(1)
    val safeCompleted = completedCount.coerceIn(0, safeTotal)
    val fraction = (safeCompleted.toFloat() / safeTotal.toFloat()).coerceIn(0f, 1f)
    val animatedFraction by animateFloatAsState(
        targetValue = fraction,
        animationSpec = tween(durationMillis = 550, easing = FastOutSlowInEasing),
        label = "subject_progress_$subjectName"
    )
    val percentage = (fraction * 100).toInt()

    val status = when {
        safeCompleted >= safeTotal && totalCount > 0 -> KvLearningStatus.COMPLETED
        safeCompleted > 0 -> KvLearningStatus.IN_PROGRESS
        else -> KvLearningStatus.AVAILABLE
    }

    val rowModifier = if (onClick != null) {
        modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() }
            .padding(vertical = 6.dp, horizontal = 4.dp)
    } else {
        modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    }

    Column(modifier = rowModifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Box(
                    modifier = Modifier
                        .size(32.dp)
                        .clip(RoundedCornerShape(7.dp))
                        .background(accentColor.copy(alpha = 0.14f))
                        .border(1.dp, accentColor.copy(alpha = 0.3f), RoundedCornerShape(7.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = subjectName,
                        tint = accentColor,
                        modifier = Modifier.size(17.dp)
                    )
                }
                Spacer(modifier = Modifier.width(10.dp))
                Column {
                    Text(
                        text = subjectName,
                        style = MaterialTheme.typography.titleSmall,
                        color = TextPrimary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "$safeCompleted de $totalCount aulas concluídas",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                KvStatusBadge(status = status, compact = true)
                Text(
                    text = "$percentage%",
                    style = MaterialTheme.typography.labelLarge,
                    color = if (safeCompleted > 0) accentColor else TextSecondary,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(6.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp))
                .background(SurfaceElevated)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(animatedFraction.coerceAtLeast(if (safeCompleted > 0) 0.04f else 0f))
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(3.dp))
                    .background(if (safeCompleted >= safeTotal && totalCount > 0) SuccessEmerald else accentColor)
            )
        }
    }
}

/**
 * Calendário visual dos últimos 28 dias (4 semanas) baseado nas datas reais de estudo
 * (`userProgress.activeStudyDates` no formato `yyyy-MM-dd`).
 */
@Composable
fun KvActivityCalendarGrid(
    activeStudyDates: List<String>,
    currentStreak: Int,
    modifier: Modifier = Modifier
) {
    val activeSet = remember(activeStudyDates) { activeStudyDates.toSet() }
    val days = remember(activeStudyDates) {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dayLabelFormat = SimpleDateFormat("dd/MM", Locale.getDefault())
        val cal = Calendar.getInstance()
        // Gera os últimos 28 dias em ordem cronológica (do mais antigo ao dia atual)
        cal.add(Calendar.DAY_OF_YEAR, -27)
        List(28) { idx ->
            val dateIso = sdf.format(cal.time)
            val shortLabel = dayLabelFormat.format(cal.time)
            val isToday = idx == 27
            cal.add(Calendar.DAY_OF_YEAR, 1)
            Triple(dateIso, shortLabel, isToday)
        }
    }

    val activeLast28 = days.count { (iso, _, _) -> iso in activeSet }

    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        border = BorderStroke(1.dp, BorderSubtle)
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Consistência de Estudo (28 dias)",
                        style = MaterialTheme.typography.titleSmall,
                        color = TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "$activeLast28 dias ativos nas últimas 4 semanas • Ofensiva: $currentStreak d",
                        style = MaterialTheme.typography.bodySmall,
                        color = TextSecondary
                    )
                }

                Surface(
                    color = if (activeLast28 > 0) SuccessEmerald.copy(alpha = 0.14f) else SurfaceElevated,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(
                        1.dp,
                        if (activeLast28 > 0) SuccessEmerald.copy(alpha = 0.35f) else BorderSubtle
                    )
                ) {
                    Text(
                        text = "${((activeLast28 / 28f) * 100).toInt()}% frequência",
                        style = MaterialTheme.typography.labelSmall,
                        color = if (activeLast28 > 0) SuccessEmerald else TextSecondary,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            // 4 linhas de 7 dias cada
            Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
                for (week in 0 until 4) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        for (dayIdx in 0 until 7) {
                            val (iso, shortLabel, isToday) = days[week * 7 + dayIdx]
                            val isActive = iso in activeSet
                            val bgColor = when {
                                isActive -> SuccessEmerald.copy(alpha = 0.25f)
                                isToday -> PrimaryViolet.copy(alpha = 0.14f)
                                else -> SurfaceElevated
                            }
                            val borderColor = when {
                                isActive -> SuccessEmerald
                                isToday -> PrimaryVioletLight
                                else -> BorderSubtle
                            }

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .height(28.dp)
                                    .clip(RoundedCornerShape(5.dp))
                                    .background(bgColor)
                                    .border(1.dp, borderColor, RoundedCornerShape(5.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = shortLabel.take(2),
                                    style = MaterialTheme.typography.labelSmall,
                                    fontSize = 10.sp,
                                    color = when {
                                        isActive -> SuccessEmerald
                                        isToday -> PrimaryVioletLight
                                        else -> TextTertiary
                                    },
                                    fontWeight = if (isActive || isToday) FontWeight.Bold else FontWeight.Normal
                                )
                            }
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Legenda acessível
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(SuccessEmerald.copy(alpha = 0.35f))
                            .border(1.dp, SuccessEmerald, RoundedCornerShape(2.dp))
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Dia com meta atingida", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(10.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(PrimaryViolet.copy(alpha = 0.16f))
                            .border(1.dp, PrimaryVioletLight, RoundedCornerShape(2.dp))
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text("Hoje", style = MaterialTheme.typography.bodySmall, color = TextSecondary)
                }
            }
        }
    }
}

/**
 * Cartão de orientação contextual com mascote oficial do KnowVerse.
 */
@Composable
fun KvMascotCalloutCard(
    title: String,
    message: String,
    @DrawableRes mascotResId: Int,
    modifier: Modifier = Modifier,
    accentColor: Color = PrimaryViolet,
    actionLabel: String? = null,
    onActionClick: (() -> Unit)? = null
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
        border = BorderStroke(1.dp, accentColor.copy(alpha = 0.35f))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(14.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape)
                    .background(accentColor.copy(alpha = 0.12f))
                    .border(1.dp, accentColor.copy(alpha = 0.35f), CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = mascotResId),
                    contentDescription = title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(48.dp)
                        .clip(CircleShape)
                )
            }

            Spacer(modifier = Modifier.width(12.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleSmall,
                    color = TextPrimary,
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(3.dp))
                Text(
                    text = message,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
                if (!actionLabel.isNullOrBlank() && onActionClick != null) {
                    Spacer(modifier = Modifier.height(8.dp))
                    FilledTonalButton(
                        onClick = onActionClick,
                        shape = RoundedCornerShape(6.dp),
                        colors = ButtonDefaults.filledTonalButtonColors(
                            containerColor = accentColor.copy(alpha = 0.18f),
                            contentColor = accentColor
                        ),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = actionLabel,
                            style = MaterialTheme.typography.labelMedium,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}

/**
 * Barra horizontal de etapas da aula (1. Teoria -> 2. Exemplos & Gráficos -> 3. Prática).
 */
@Composable
fun KvLessonStageBar(
    stages: List<String>,
    currentStageIndex: Int,
    onStageSelected: ((Int) -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(ObsidianSurface)
            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
            .padding(6.dp),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        stages.forEachIndexed { index, stageTitle ->
            val isCompleted = index < currentStageIndex
            val isActive = index == currentStageIndex
            val bgColor = when {
                isActive -> PrimaryViolet.copy(alpha = 0.2f)
                isCompleted -> SuccessEmerald.copy(alpha = 0.14f)
                else -> SurfaceElevated
            }
            val borderColor = when {
                isActive -> PrimaryViolet
                isCompleted -> SuccessEmerald.copy(alpha = 0.45f)
                else -> Color.Transparent
            }
            val textColor = when {
                isActive -> TextPrimary
                isCompleted -> SuccessEmerald
                else -> TextSecondary
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(6.dp))
                    .background(bgColor)
                    .border(1.dp, borderColor, RoundedCornerShape(6.dp))
                    .then(
                        if (onStageSelected != null) {
                            Modifier.clickable { onStageSelected(index) }
                        } else {
                            Modifier
                        }
                    )
                    .padding(vertical = 7.dp, horizontal = 8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    if (isCompleted) {
                        Icon(
                            imageVector = Icons.Default.Check,
                            contentDescription = "Etapa concluída",
                            tint = SuccessEmerald,
                            modifier = Modifier.size(13.dp)
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                    }
                    Text(
                        text = stageTitle,
                        style = MaterialTheme.typography.labelSmall,
                        color = textColor,
                        fontWeight = if (isActive || isCompleted) FontWeight.Bold else FontWeight.Medium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

