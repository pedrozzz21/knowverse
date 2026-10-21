package com.example.ui.components

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.model.Lesson
import com.example.data.model.UserProgress
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.StreakFire
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

@Composable
fun TopUserStatsBar(
    userProgress: UserProgress,
    userPhotoUrl: String? = null,
    onStatsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    TopUserStatsBar(
        streakDays = userProgress.currentStreak,
        totalXp = userProgress.totalXp,
        level = userProgress.level,
        userPhotoUrl = userPhotoUrl,
        onStatsClick = onStatsClick,
        modifier = modifier
    )
}

@Composable
fun TopUserStatsBar(
    streakDays: Int,
    totalXp: Int,
    level: Int,
    userPhotoUrl: String? = null,
    onStatsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        color = ObsidianSurface,
        modifier = modifier
            .fillMaxWidth()
            .border(width = 1.dp, color = BorderSubtle)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable { onStatsClick() },
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Streak Stat
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(SurfaceElevated)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .testTag("streak_pill")
            ) {
                Icon(
                    imageVector = KnowverseIcons.StreakFire,
                    contentDescription = "Sequência",
                    tint = StreakFire,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "$streakDays d",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = TextPrimary,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            // XP Stat
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .clip(RoundedCornerShape(4.dp))
                    .background(SurfaceElevated)
                    .padding(horizontal = 8.dp, vertical = 4.dp)
                    .testTag("xp_pill")
            ) {
                Icon(
                    imageVector = KnowverseIcons.XpGem,
                    contentDescription = "Experiência",
                    tint = GoldXp,
                    modifier = Modifier.size(15.dp)
                )
                Spacer(modifier = Modifier.width(6.dp))
                Text(
                    text = "$totalXp XP",
                    style = MaterialTheme.typography.labelMedium.copy(
                        color = GoldXp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }

            // Level Stat & Avatar
            Row(verticalAlignment = Alignment.CenterVertically) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(PrimaryViolet.copy(alpha = 0.15f))
                        .border(1.dp, PrimaryViolet.copy(alpha = 0.35f), RoundedCornerShape(4.dp))
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .testTag("level_pill")
                ) {
                    Text(
                        text = "Nível $level",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = PrimaryViolet,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }

                Spacer(modifier = Modifier.width(8.dp))
                Box(
                    modifier = Modifier
                        .size(28.dp)
                        .clip(CircleShape)
                        .border(1.5.dp, PrimaryViolet.copy(alpha = 0.6f), CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    UserAvatarDisplay(
                        photoKeyOrUrl = userPhotoUrl,
                        sizeDp = 28
                    )
                }
            }
        }
    }
}

@Composable
fun TrailLessonNode(
    lesson: Lesson,
    isCompleted: Boolean,
    isActive: Boolean,
    isLocked: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(6.dp),
        colors = CardDefaults.cardColors(
            containerColor = when {
                isActive -> SurfaceCard
                isCompleted -> ObsidianSurface
                else -> ObsidianSurface.copy(alpha = 0.6f)
            }
        ),
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = when {
                    isActive -> PrimaryViolet
                    isCompleted -> BorderSubtle
                    else -> BorderSubtle.copy(alpha = 0.5f)
                },
                shape = RoundedCornerShape(6.dp)
            )
            .clickable(enabled = !isLocked) { onClick() }
            .testTag("lesson_node_${lesson.id}")
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp, vertical = 10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                // Status Indicator
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(
                            when {
                                isCompleted -> SuccessEmerald.copy(alpha = 0.15f)
                                isActive -> PrimaryViolet.copy(alpha = 0.15f)
                                else -> SurfaceElevated
                            }
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = when {
                            isCompleted -> Icons.Default.Check
                            isActive -> Icons.Default.PlayArrow
                            else -> Icons.Default.Lock
                        },
                        contentDescription = null,
                        tint = when {
                            isCompleted -> SuccessEmerald
                            isActive -> PrimaryViolet
                            else -> TextTertiary
                        },
                        modifier = Modifier.size(13.dp)
                    )
                }

                Spacer(modifier = Modifier.width(10.dp))

                Column {
                    Text(
                        text = lesson.title,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = if (isActive) FontWeight.SemiBold else FontWeight.Medium,
                            color = if (isLocked) TextTertiary else TextPrimary
                        )
                    )
                    Text(
                        text = lesson.conceptTitle,
                        style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                    )
                }
            }

            Text(
                text = "+${lesson.xpReward} XP",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Medium,
                    color = if (isCompleted) TextTertiary else GoldXp
                )
            )
        }
    }
}
