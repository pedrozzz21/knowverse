package com.example.ui.components.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.ui.components.KnowverseIcons
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.StreakFire
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

/**
 * Cabeçalho consolidado e minimalista da Home.
 * Integra saudação ao estudante, o tutor Otto e o indicador de sequência diária
 * em uma barra horizontal de altura compacta, eliminando o visual genérico de cards empilhados.
 */
@Composable
fun HeroHeader(
    userName: String,
    currentStreak: Int,
    hasStudiedToday: Boolean,
    modifier: Modifier = Modifier,
    onStreakClick: (() -> Unit)? = null
) {
    var greetingIndex by remember { mutableIntStateOf(0) }
    val greetings = remember {
        listOf(
            "Foco e consistência geram maestria.",
            "Pratique no terminal e acompanhe sua evolução.",
            "Cada desafio concluído eleva seu raciocínio.",
            "Mantenha sua sequência ativa hoje!",
            "Pronto para dominar os exercícios de hoje?"
        )
    }

    Surface(
        color = ObsidianSurface,
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, BorderSubtle),
        modifier = modifier
            .fillMaxWidth()
            .testTag("hero_header_compact")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            // Seção Esquerda: Avatar do Otto + Boas-vindas
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .weight(1f)
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = ripple(bounded = false, radius = 24.dp)
                    ) {
                        greetingIndex = (greetingIndex + 1) % greetings.size
                    }
            ) {
                // Avatar circular com microborda de precisão
                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .clip(CircleShape)
                        .background(SurfaceElevated)
                        .border(1.dp, PrimaryViolet.copy(alpha = 0.5f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.img_mascot_otter_boy),
                        contentDescription = "Otto, seu Mentor",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                    )
                }

                Spacer(modifier = Modifier.width(12.dp))

                Column {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Text(
                            text = "Olá, $userName",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary,
                                letterSpacing = (-0.2).sp
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        // Pill identificadora discreta
                        Surface(
                            color = PrimaryViolet.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(4.dp)
                        ) {
                            Text(
                                text = "Otto IA",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = PrimaryViolet,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 9.sp
                                ),
                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(2.dp))

                    Text(
                        text = greetings[greetingIndex % greetings.size],
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            fontSize = 11.5.sp,
                            lineHeight = 15.sp
                        ),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            // Seção Direita: Streak Pill de Alto Contraste Funcional
            val streakBg = if (hasStudiedToday) StreakFire.copy(alpha = 0.12f) else SurfaceElevated
            val streakBorder = if (hasStudiedToday) StreakFire.copy(alpha = 0.4f) else BorderSubtle
            val streakColor = if (hasStudiedToday) StreakFire else TextTertiary

            Surface(
                color = streakBg,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, streakBorder),
                modifier = Modifier.clickable(
                    enabled = onStreakClick != null,
                    onClick = { onStreakClick?.invoke() }
                )
            ) {
                Row(
                    modifier = Modifier.padding(horizontal = 9.dp, vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                    Icon(
                        imageVector = if (hasStudiedToday) KnowverseIcons.StreakFire else KnowverseIcons.FlameInactive,
                        contentDescription = "Sequência Diária",
                        tint = streakColor,
                        modifier = Modifier.size(15.dp)
                    )
                    Text(
                        text = "${currentStreak}d",
                        style = MaterialTheme.typography.labelSmall.copy(
                            fontWeight = FontWeight.Bold,
                            color = if (hasStudiedToday) TextPrimary else TextSecondary,
                            fontSize = 11.sp
                        )
                    )
                }
            }
        }
    }
}
