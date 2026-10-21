package com.example.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.LocalFireDepartment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.auth.AuthManager
import com.example.data.local.UserProgressRepository
import com.example.data.model.UserProgress
import com.example.ui.components.TopUserStatsBar
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
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

data class LeaderboardEntry(
    val id: String,
    val name: String,
    val email: String,
    val photoUrl: String? = null,
    val totalXp: Int,
    val level: Int,
    val streak: Int,
    val activeDays: Int,
    val isCurrentUser: Boolean = false
)

@Composable
fun LeaderboardScreen(
    userProgress: UserProgress,
    authManager: AuthManager,
    progressRepository: UserProgressRepository,
    modifier: Modifier = Modifier
) {
    val currentUser by authManager.currentUserState.collectAsState()
    val onlineUsers by progressRepository.onlineLeaderboardState.collectAsState()
    val registeredAccounts = authManager.getAllRegisteredUsers()

    val currentUserId = currentUser?.uid ?: "user_current"
    val currentUserName = currentUser?.displayName ?: "Estudante"
    val currentUserEmail = (currentUser?.email ?: "estudante@knowverse.com").trim().lowercase()
    val currentUserPhoto = currentUser?.photoUrl
        ?: progressRepository.getActivePhotoForUser(currentUserId)
        ?: authManager.getCustomPhotoForUser(currentUserId)
        ?: authManager.getCustomPhotoForUser(currentUserEmail)

    val entryMap = mutableMapOf<String, LeaderboardEntry>()

    if (onlineUsers.isNotEmpty()) {
        // Real-time Firestore users across all devices with strict deduplication!
        for (u in onlineUsers) {
            val normEmail = u.email.trim().lowercase()
            val isMe = (u.userId == currentUserId) || (normEmail.isNotBlank() && normEmail == currentUserEmail)

            val effectivePhoto = when {
                isMe -> currentUserPhoto?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    ?: u.photoUrl?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    ?: currentUserPhoto
                    ?: u.photoUrl
                else -> u.photoUrl
                    ?: authManager.getCustomPhotoForUser(u.userId)
                    ?: authManager.getCustomPhotoForUser(u.email)
                    ?: progressRepository.getActivePhotoForUser(u.userId)
            }

            val candidate = LeaderboardEntry(
                id = if (isMe) currentUserId else u.userId,
                name = if (isMe) currentUserName else u.displayName,
                email = if (isMe) currentUserEmail else u.email,
                photoUrl = effectivePhoto,
                totalXp = if (isMe) maxOf(u.totalXp, userProgress.totalXp) else u.totalXp,
                level = if (isMe) maxOf(userProgress.level, u.level) else u.level,
                streak = if (isMe) maxOf(userProgress.currentStreak, u.streak) else u.streak,
                activeDays = if (isMe) maxOf(userProgress.activeStudyDaysCount, u.activeDays) else u.activeDays,
                isCurrentUser = isMe
            )

            val key = if (normEmail.isNotBlank()) normEmail else u.userId
            val existing = entryMap[key]
            if (existing == null) {
                entryMap[key] = candidate
            } else {
                // Merge duplicate: keep highest XP, best photo, and isCurrentUser flag
                val mergedPhoto = candidate.photoUrl?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    ?: existing.photoUrl?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    ?: candidate.photoUrl
                    ?: existing.photoUrl

                val isNowMe = existing.isCurrentUser || candidate.isCurrentUser
                entryMap[key] = existing.copy(
                    id = if (isNowMe) currentUserId else existing.id,
                    name = if (isNowMe) currentUserName else existing.name,
                    photoUrl = mergedPhoto,
                    totalXp = maxOf(existing.totalXp, candidate.totalXp),
                    level = maxOf(existing.level, candidate.level),
                    streak = maxOf(existing.streak, candidate.streak),
                    activeDays = maxOf(existing.activeDays, candidate.activeDays),
                    isCurrentUser = isNowMe
                )
            }
        }
    } else {
        // Fallback for initial load / local offline mode
        entryMap[currentUserEmail] = LeaderboardEntry(
            id = currentUserId,
            name = currentUserName,
            email = currentUserEmail,
            photoUrl = currentUserPhoto,
            totalXp = userProgress.totalXp,
            level = userProgress.level,
            streak = userProgress.currentStreak,
            activeDays = userProgress.activeStudyDaysCount,
            isCurrentUser = true
        )

        for (acc in registeredAccounts) {
            val accEmail = acc.email.trim().lowercase()
            if (acc.uid != currentUserId && accEmail != currentUserEmail) {
                val accProgress = progressRepository.getProgressForUser(acc.uid)
                val accPhoto = acc.photoUrl
                    ?: authManager.getCustomPhotoForUser(acc.uid)
                    ?: authManager.getCustomPhotoForUser(acc.email)
                    ?: progressRepository.getActivePhotoForUser(acc.uid)
                val key = if (accEmail.isNotBlank()) accEmail else acc.uid
                if (!entryMap.containsKey(key)) {
                    entryMap[key] = LeaderboardEntry(
                        id = acc.uid,
                        name = acc.displayName,
                        email = acc.email,
                        photoUrl = accPhoto,
                        totalXp = accProgress.totalXp,
                        level = accProgress.level,
                        streak = accProgress.currentStreak,
                        activeDays = accProgress.activeStudyDaysCount,
                        isCurrentUser = false
                    )
                }
            }
        }
    }

    // Ensure current active user is always represented with the highest verified values
    val currentInMap = entryMap[currentUserEmail]
    if (currentInMap == null) {
        entryMap[currentUserEmail] = LeaderboardEntry(
            id = currentUserId,
            name = currentUserName,
            email = currentUserEmail,
            photoUrl = currentUserPhoto,
            totalXp = userProgress.totalXp,
            level = userProgress.level,
            streak = userProgress.currentStreak,
            activeDays = userProgress.activeStudyDaysCount,
            isCurrentUser = true
        )
    } else {
        entryMap[currentUserEmail] = currentInMap.copy(
            id = currentUserId,
            name = currentUserName,
            photoUrl = currentUserPhoto?.takeIf { it.isNotBlank() && it != "mascot_sealion" } ?: currentInMap.photoUrl,
            totalXp = maxOf(currentInMap.totalXp, userProgress.totalXp),
            level = maxOf(currentInMap.level, userProgress.level),
            streak = maxOf(currentInMap.streak, userProgress.currentStreak),
            activeDays = maxOf(currentInMap.activeDays, userProgress.activeStudyDaysCount),
            isCurrentUser = true
        )
    }

    val entries = entryMap.values.toMutableList()

    // Sort primarily by XP descending, then by active study days
    entries.sortWith(
        compareByDescending<LeaderboardEntry> { it.totalXp }
            .thenByDescending { it.activeDays }
    )

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
    ) {
        TopUserStatsBar(
            userProgress = userProgress,
            userPhotoUrl = currentUser?.photoUrl
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 14.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(6.dp))
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.EmojiEvents,
                        contentDescription = null,
                        tint = GoldXp,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Ranking de Dedicação aos Estudos",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = TextPrimary
                        )
                    )
                }

                Spacer(modifier = Modifier.height(2.dp))

                Text(
                    text = "Competição saudável baseada em dias ativos de estudo e aprendizado contínuo.",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )

                Spacer(modifier = Modifier.height(6.dp))

                // Real-time Cloud Connection Indicator
                Surface(
                    color = if (onlineUsers.isNotEmpty()) SuccessEmerald.copy(alpha = 0.12f) else GoldXp.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(4.dp),
                    border = androidx.compose.foundation.BorderStroke(
                        1.dp,
                        if (onlineUsers.isNotEmpty()) SuccessEmerald.copy(alpha = 0.4f) else GoldXp.copy(alpha = 0.4f)
                    )
                ) {
                    Row(
                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(7.dp)
                                .clip(CircleShape)
                                .background(if (onlineUsers.isNotEmpty()) SuccessEmerald else GoldXp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (onlineUsers.isNotEmpty()) {
                                "Ranking Global ao Vivo • ${entries.size} estudantes conectados"
                            } else {
                                "Modo Offline • Exibindo ranking salvo no dispositivo"
                            },
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = if (onlineUsers.isNotEmpty()) SuccessEmerald else GoldXp,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 10.sp
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Highlighted Card: Dias Ativos de Estudo & Check-In
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PrimaryViolet.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                        .testTag("check_in_status_card")
                ) {
                    Column(modifier = Modifier.padding(14.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    imageVector = Icons.Default.CheckCircle,
                                    contentDescription = null,
                                    tint = SuccessEmerald,
                                    modifier = Modifier.size(20.dp)
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "Check-In Diário: Realizado!",
                                    style = MaterialTheme.typography.labelMedium.copy(
                                        color = SuccessEmerald,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }

                            Surface(
                                color = StreakFire.copy(alpha = 0.15f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Row(
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.LocalFireDepartment,
                                        contentDescription = null,
                                        tint = StreakFire,
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${userProgress.currentStreak} dias seguidos",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = StreakFire,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }

                        Spacer(modifier = Modifier.height(10.dp))

                        // Large Callout for "Quantidade de Dias Ativos de Estudo"
                        Surface(
                            color = SurfaceElevated,
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(horizontal = 14.dp, vertical = 10.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(
                                        text = "Quantidade de Dias Ativos de Estudo:",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TextSecondary,
                                            fontSize = 11.sp
                                        )
                                    )
                                    Text(
                                        text = "${userProgress.activeStudyDaysCount} Dias de Estudo Registrados",
                                        style = MaterialTheme.typography.titleMedium.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = GoldXp
                                        )
                                    )
                                }

                                Box(
                                    modifier = Modifier
                                        .size(36.dp)
                                        .clip(CircleShape)
                                        .background(GoldXp.copy(alpha = 0.2f)),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.WorkspacePremium,
                                        contentDescription = null,
                                        tint = GoldXp,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Tabela de Classificação",
                    style = MaterialTheme.typography.titleSmall.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                )
            }

            itemsIndexed(entries) { index, student ->
                val rankNumber = index + 1
                val isMe = student.isCurrentUser

                val rankBadgeColor = when (rankNumber) {
                    1 -> GoldXp
                    2 -> Color(0xFFC0C0C0) // Silver
                    3 -> Color(0xFFCD7F32) // Bronze
                    else -> TextTertiary
                }

                Card(
                    shape = RoundedCornerShape(6.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = if (isMe) PrimaryViolet.copy(alpha = 0.12f) else ObsidianSurface
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = if (isMe) PrimaryViolet.copy(alpha = 0.6f) else BorderSubtle,
                            shape = RoundedCornerShape(6.dp)
                        )
                        .testTag("leaderboard_item_$rankNumber")
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Rank Position Indicator
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(rankBadgeColor.copy(alpha = 0.15f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "#$rankNumber",
                                style = MaterialTheme.typography.labelMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = rankBadgeColor
                                )
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        // Avatar Display
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(SurfaceElevated)
                                .border(
                                    width = if (isMe) 1.5.dp else 1.dp,
                                    color = if (isMe) PrimaryViolet else BorderSubtle,
                                    shape = CircleShape
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            val rowPhoto = student.photoUrl
                                ?: (if (isMe) currentUserPhoto else null)
                                ?: progressRepository.getActivePhotoForUser(student.id)
                                ?: authManager.getCustomPhotoForUser(student.id)
                                ?: authManager.getCustomPhotoForUser(student.email)

                            com.example.ui.components.UserAvatarDisplay(
                                photoKeyOrUrl = rowPhoto,
                                sizeDp = 36
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        // User Info
                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = student.name,
                                    style = MaterialTheme.typography.bodyMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = TextPrimary
                                    )
                                )
                                if (isMe) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = PrimaryViolet,
                                        shape = RoundedCornerShape(3.dp)
                                    ) {
                                        Text(
                                            text = "Você",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = Color.White,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 9.sp
                                            ),
                                            modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                        )
                                    }
                                }
                            }

                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Text(
                                    text = "${student.activeDays} dias ativos",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = GoldXp,
                                        fontWeight = FontWeight.Medium
                                    )
                                )
                                Text(
                                    text = "•",
                                    style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary)
                                )
                                Text(
                                    text = "Nível ${student.level}",
                                    style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                )
                            }
                        }

                        // Total XP
                        Text(
                            text = "${student.totalXp} XP",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldXp
                            )
                        )
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(14.dp))
                Surface(
                    color = SurfaceElevated,
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "Dica: Todo dia que você estuda e acessa uma lição, um Check-In automático é efetuado e seus dias ativos aumentam!",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            fontSize = 11.sp,
                            lineHeight = 15.sp
                        ),
                        modifier = Modifier.padding(12.dp)
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
