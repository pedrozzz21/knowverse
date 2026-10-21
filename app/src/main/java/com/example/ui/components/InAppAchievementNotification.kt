package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.hapticfeedback.HapticFeedbackType
import androidx.compose.ui.platform.LocalHapticFeedback
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.R
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import java.util.UUID

/**
 * Modelo de dados para alertas de conquistas e emblemas desbloqueados em tempo real.
 */
data class InAppAchievementAlert(
    val id: String = UUID.randomUUID().toString(),
    val title: String,
    val description: String,
    val xpBonus: Int,
    val drawableRes: Int? = null,
    val category: String = "CONQUISTA DESBLOQUEADA!",
    val timestamp: Long = System.currentTimeMillis()
)

/**
 * Gerenciador reativo central de notificações in-app de conquistas do Knowverse.
 */
object AchievementAlertManager {
    private val _currentAlert = MutableStateFlow<InAppAchievementAlert?>(null)
    val currentAlert: StateFlow<InAppAchievementAlert?> = _currentAlert

    fun postAlert(alert: InAppAchievementAlert) {
        _currentAlert.value = alert
    }

    fun dismiss() {
        _currentAlert.value = null
    }
}

/**
 * Banner flutuante no topo da tela com animação fluida, feedback tátil e estética de celebração.
 */
@Composable
fun InAppAchievementNotificationBanner(
    modifier: Modifier = Modifier
) {
    val alert by AchievementAlertManager.currentAlert.collectAsState()
    val haptic = LocalHapticFeedback.current

    // Auto-dismiss após 4.5 segundos e feedback háptico ao disparar
    LaunchedEffect(alert) {
        if (alert != null) {
            try {
                haptic.performHapticFeedback(HapticFeedbackType.LongPress)
            } catch (_: Exception) {}
            delay(4500)
            AchievementAlertManager.dismiss()
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 14.dp, vertical = 8.dp)
            .zIndex(9999f),
        contentAlignment = Alignment.TopCenter
    ) {
        AnimatedVisibility(
            visible = alert != null,
            enter = slideInVertically(initialOffsetY = { -it }) + fadeIn(),
            exit = slideOutVertically(targetOffsetY = { -it }) + fadeOut()
        ) {
            alert?.let { item ->
                Card(
                    shape = RoundedCornerShape(14.dp),
                    colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(16.dp, RoundedCornerShape(14.dp), ambientColor = GoldXp, spotColor = GoldXp)
                        .border(
                            1.5.dp,
                            Brush.linearGradient(
                                listOf(
                                    GoldXp,
                                    PrimaryViolet.copy(alpha = 0.8f),
                                    GoldXp
                                )
                            ),
                            RoundedCornerShape(14.dp)
                        )
                        .clickable { AchievementAlertManager.dismiss() }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 14.dp, vertical = 12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Ícone da Conquista com fundo brilhante
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(GoldXp.copy(alpha = 0.16f))
                                .border(1.dp, GoldXp.copy(alpha = 0.5f), RoundedCornerShape(10.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            if (item.drawableRes != null) {
                                Icon(
                                    painter = painterResource(id = item.drawableRes),
                                    contentDescription = item.title,
                                    tint = Color.Unspecified,
                                    modifier = Modifier.size(36.dp)
                                )
                            } else {
                                Icon(
                                    imageVector = Icons.Default.EmojiEvents,
                                    contentDescription = null,
                                    tint = GoldXp,
                                    modifier = Modifier.size(28.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "✨ ${item.category} ✨",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.8.sp
                                    )
                                )
                            }

                            Spacer(modifier = Modifier.height(2.dp))

                            Text(
                                text = item.title,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary,
                                    fontSize = 14.sp
                                )
                            )

                            Text(
                                text = item.description,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 11.sp,
                                    lineHeight = 15.sp
                                ),
                                maxLines = 2
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Column(horizontalAlignment = Alignment.End) {
                            // Chip de Recompensa em XP
                            Surface(
                                color = SuccessEmerald.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, SuccessEmerald.copy(alpha = 0.6f))
                            ) {
                                Text(
                                    text = "+${item.xpBonus} XP",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = SuccessEmerald,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }

                            Spacer(modifier = Modifier.height(4.dp))

                            IconButton(
                                onClick = { AchievementAlertManager.dismiss() },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Fechar notificação",
                                    tint = TextSecondary,
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
