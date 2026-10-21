package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Code
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.auth.AuthManager
import com.example.data.model.DifficultyTier
import com.example.data.model.KahootModule
import com.example.data.model.Lesson
import com.example.data.model.UserProgress
import com.example.data.python.PythonCurriculum
import com.example.ui.components.KnowverseIcons
import com.example.ui.components.TopUserStatsBar
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import com.example.util.ShareUtil

@Composable
fun HomeScreen(
    userProgress: UserProgress,
    userName: String = "Estudante",
    userPhotoUrl: String? = null,
    authManager: AuthManager? = null,
    progressRepository: com.example.data.local.UserProgressRepository? = null,
    onContinueLearningClick: (Lesson) -> Unit,
    onStartNewLearning: () -> Unit,
    onNavigateToTrail: () -> Unit,
    onNavigateToRanking: () -> Unit = {},
    onNavigateToExercises: () -> Unit = {},
    onNavigateToEssayLab: () -> Unit = {},
    onStartFinalTest: (KahootModule) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val onlineUsers by (progressRepository?.onlineLeaderboardState?.collectAsState() ?: remember { mutableStateOf(emptyList()) })

    // Localiza a próxima lição incompleta no currículo
    val allLessons = PythonCurriculum.getAllLessons()
    val nextIncomplete = allLessons.firstOrNull { !userProgress.completedLessonIds.contains(it.id) }
    val nextLesson = PythonCurriculum.findLesson(userProgress.currentLessonId)?.takeIf { !userProgress.completedLessonIds.contains(it.id) }
        ?: nextIncomplete
        ?: allLessons.first()

    val hasStudiedToday = userProgress.hasStudiedToday()

    // Cálculo da posição no ranking do usuário atual
    val currentUserId = authManager?.currentUserState?.value?.uid ?: "user_current"
    val currentUserEmail = authManager?.currentUserState?.value?.email ?: ""
    val rankingList = remember(onlineUsers, userProgress.totalXp) {
        val list = mutableListOf<Pair<String, Int>>()
        if (onlineUsers.isNotEmpty()) {
            for (u in onlineUsers) {
                val isMe = (u.userId == currentUserId) || u.email.equals(currentUserEmail, ignoreCase = true)
                val xp = if (isMe) maxOf(u.totalXp, userProgress.totalXp) else u.totalXp
                list.add(Pair(u.userId, xp))
            }
            if (list.none { it.first == currentUserId }) {
                list.add(Pair(currentUserId, userProgress.totalXp))
            }
        } else {
            list.add(Pair(currentUserId, userProgress.totalXp))
            val registeredAccounts = authManager?.getAllRegisteredUsers().orEmpty()
            for (acc in registeredAccounts) {
                if (acc.uid != currentUserId) {
                    val accXp = progressRepository?.getProgressForUser(acc.uid)?.totalXp ?: 0
                    list.add(Pair(acc.uid, accXp))
                }
            }
        }
        list.sortByDescending { it.second }
        list
    }
    val userRankPosition = (rankingList.indexOfFirst { it.first == currentUserId }.takeIf { it >= 0 } ?: 0) + 1

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        // Barra global de estatísticas de topo (XP, Sequência, Moedas, Perfil)
        TopUserStatsBar(
            userProgress = userProgress,
            userPhotoUrl = userPhotoUrl,
            onStatsClick = onNavigateToRanking
        )

        Column(
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 12.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 1. Cabeçalho Minimalista Consolidado (Otto + Saudação + Sequência)
            com.example.ui.components.home.HeroHeader(
                userName = userName,
                currentStreak = userProgress.currentStreak,
                hasStudiedToday = hasStudiedToday,
                onStreakClick = onNavigateToRanking
            )

            // 2. Painel de Ações Assimétrico estilo "Bento Box"
            com.example.ui.components.home.ActionBentoGrid(
                nextLesson = nextLesson,
                userRankPosition = userRankPosition,
                totalXp = userProgress.totalXp,
                onContinueLearningClick = onContinueLearningClick,
                onNavigateToTrail = onStartNewLearning,
                onNavigateToExercises = onNavigateToExercises,
                onNavigateToEssayLab = onNavigateToEssayLab,
                onNavigateToRanking = onNavigateToRanking
            )

            // 3. Widgets Secundários (Teste Final do Módulo + Convite / Compartilhar)
            com.example.ui.components.home.GamificationWidgets(
                userProgress = userProgress,
                onStartFinalTest = onStartFinalTest,
                context = context
            )

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}
