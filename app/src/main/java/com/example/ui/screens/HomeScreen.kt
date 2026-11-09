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
import androidx.compose.foundation.layout.widthIn
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
    onNavigateToSimulados: () -> Unit = {},
    onStartFinalTest: (KahootModule) -> Unit,
    unreadNotificationsCount: Int = 0,
    onNotificationsClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val onlineUsers by (progressRepository?.onlineLeaderboardState?.collectAsState() ?: remember { mutableStateOf(emptyList()) })

    // Localiza a próxima lição incompleta real através de todos os currículos do KnowVerse
    val (nextLesson, activeSubjectName, subjectProgressMap) = remember(
        userProgress.currentLessonId,
        userProgress.completedLessonIds,
        userProgress.favoriteAreaId
    ) {
        val subjectPairs = listOf(
            "Python" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "python").flatMap { it.lessons },
            "Matemática" to (
                com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "basic_math").flatMap { it.lessons } +
                    com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "enem_math").flatMap { it.lessons } +
                    com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "calculus").flatMap { it.lessons }
                ),
            "Física" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "physics").flatMap { it.lessons },
            "Biologia" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "biology").flatMap { it.lessons },
            "Química" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "chemistry").flatMap { it.lessons },
            "Humanas" to (
                com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "history").flatMap { it.lessons } +
                    com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "geography").flatMap { it.lessons }
                ),
            "Linguagens" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "languages").flatMap { it.lessons }
        )
        resolveContinueLearningTarget(userProgress, subjectPairs)
    }

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

    // Container Raiz Centralizado Adaptativo (Mobile e Tablet/Desktop)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(DeepNavy),
        contentAlignment = Alignment.TopCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .widthIn(max = 600.dp)
                .statusBarsPadding()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Barra global de estatísticas de topo (XP, Sequência, Nível, Perfil)
            TopUserStatsBar(
                userProgress = userProgress,
                userPhotoUrl = userPhotoUrl,
                onStatsClick = onNavigateToRanking
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 14.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // 1. Cabeçalho Consolidado com Otto Mentor + Boas-vindas + Notificações
                com.example.ui.components.home.HeroHeader(
                    userName = userName,
                    currentStreak = userProgress.currentStreak,
                    hasStudiedToday = hasStudiedToday,
                    unreadNotificationsCount = unreadNotificationsCount,
                    onNotificationsClick = onNotificationsClick,
                    onStreakClick = onNavigateToRanking
                )

                // 2. Painel Central de Atividades & Disciplinas do Knowverse
                com.example.ui.components.home.ActionBentoGrid(
                    nextLesson = nextLesson,
                    activeSubjectName = activeSubjectName,
                    subjectProgressMap = subjectProgressMap,
                    dailyXp = userProgress.dailyXp,
                    dailyGoalXp = userProgress.dailyGoalXp,
                    reviewLessonCount = userProgress.reviewLessonIds.size,
                    userRankPosition = userRankPosition,
                    totalXp = userProgress.totalXp,
                    hasStudiedToday = hasStudiedToday,
                    currentStreak = userProgress.currentStreak,
                    onContinueLearningClick = onContinueLearningClick,
                    onNavigateToTrail = onStartNewLearning,
                    onNavigateToExercises = onNavigateToExercises,
                    onNavigateToEssayLab = onNavigateToEssayLab,
                    onNavigateToRanking = onNavigateToRanking,
                    onNavigateToSimulados = onNavigateToSimulados
                )

                // 3. Widgets Secundários (Teste Final do Módulo + Convidar Colegas)
                com.example.ui.components.home.GamificationWidgets(
                    userProgress = userProgress,
                    onStartFinalTest = onStartFinalTest,
                    context = context
                )

                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}

internal fun defaultSubjectPairs(context: android.content.Context? = null): List<Pair<String, List<Lesson>>> = listOf(
    "Transpetro" to com.example.data.transpetro.TranspetroCurriculum.getAllLessons().map { it.toAppLesson() },
    "Python" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "python").flatMap { it.lessons },
    "Matemática" to (
        com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "basic_math").flatMap { it.lessons } +
            com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "enem_math").flatMap { it.lessons } +
            com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "calculus").flatMap { it.lessons }
        ),
    "Física" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "physics").flatMap { it.lessons },
    "Biologia" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "biology").flatMap { it.lessons },
    "Química" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "chemistry").flatMap { it.lessons },
    "Humanas" to (
        com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "history").flatMap { it.lessons } +
            com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "geography").flatMap { it.lessons }
        ),
    "Linguagens" to com.example.data.local.assets.CurriculumAssetLoader.loadAllUnitsForSubject(context, "languages").flatMap { it.lessons }
)

internal fun resolveContinueLearningTarget(
    userProgress: UserProgress,
    subjectPairs: List<Pair<String, List<Lesson>>> = defaultSubjectPairs(null)
): Triple<Lesson, String, Map<String, Pair<Int, Int>>> {
    val completedSet = userProgress.completedLessonIds
    val progressMap = subjectPairs.associate { (subjName, lessons) ->
        val done = lessons.count { it.id in completedSet }
        subjName to (done to lessons.size)
    }

    val currentId = userProgress.currentLessonId
    val isUntouchedDefault = currentId == "py_b1_l1" &&
        "py_b1_l1" !in completedSet &&
        userProgress.favoriteAreaId != "python"

    // 1. Se o usuário tem uma aula atual definida explicitamente (ou concluída), prioriza a disciplina dela
    if (currentId.isNotBlank() && !isUntouchedDefault) {
        val currentMatch = subjectPairs.firstNotNullOfOrNull { (subjName, lessons) ->
            val idx = lessons.indexOfFirst { it.id == currentId }
            if (idx >= 0) Triple(subjName, lessons, idx) else null
        }
        if (currentMatch != null) {
            val (subjName, lessons, idx) = currentMatch
            val currentLesson = lessons[idx]
            if (currentLesson.id !in completedSet) {
                return Triple(currentLesson, subjName, progressMap)
            }
            // Se a aula atual já foi concluída, avança para a próxima aula não concluída da mesma disciplina
            val nextInSameSubj = lessons.drop(idx + 1).firstOrNull { it.id !in completedSet }
                ?: lessons.firstOrNull { it.id !in completedSet }
            if (nextInSameSubj != null) {
                return Triple(nextInSameSubj, subjName, progressMap)
            }
        }
    }

    // 2. Prioriza a área favorita do usuário, se definida
    val favoriteSubjectName = when (userProgress.favoriteAreaId.lowercase()) {
        "transpetro", "transpetro_adm", "adm_controle" -> "Transpetro"
        "python" -> "Python"
        "math", "matematica", "matematica_enem", "matematica_basica", "calculus" -> "Matemática"
        "physics", "fisica" -> "Física"
        "biology", "biologia" -> "Biologia"
        "chemistry", "quimica" -> "Química"
        "humanities", "humanas", "geografia", "historia" -> "Humanas"
        "languages", "linguagens" -> "Linguagens"
        else -> null
    }
    if (favoriteSubjectName != null) {
        val favLessons = subjectPairs.firstOrNull { it.first == favoriteSubjectName }?.second.orEmpty()
        val nextInFav = favLessons.firstOrNull { it.id !in completedSet }
        if (nextInFav != null) {
            return Triple(nextInFav, favoriteSubjectName, progressMap)
        }
    }

    // 3. Prioriza qualquer disciplina em que o usuário já tenha concluído ao menos 1 aula e ainda tenha aulas pendentes
    val activeStartedPair = subjectPairs.firstOrNull { (_, lessons) ->
        lessons.any { it.id in completedSet } && lessons.any { it.id !in completedSet }
    }
    if (activeStartedPair != null) {
        val (subjName, lessons) = activeStartedPair
        val lastCompletedIdx = lessons.indexOfLast { it.id in completedSet }
        val nextAfterCompleted = lessons.drop(lastCompletedIdx + 1).firstOrNull { it.id !in completedSet }
            ?: lessons.firstOrNull { it.id !in completedSet }
        if (nextAfterCompleted != null) {
            return Triple(nextAfterCompleted, subjName, progressMap)
        }
    }

    // 4. Fallback inicial para novos usuários sem histórico
    val defaultPair = subjectPairs.firstOrNull { (_, lessons) ->
        lessons.any { it.id !in completedSet }
    }
    val next = defaultPair?.second?.firstOrNull { it.id !in completedSet }
        ?: subjectPairs.firstOrNull()?.second?.firstOrNull()
        ?: PythonCurriculum.getAllLessons().first()
    val subjName = defaultPair?.first ?: "Python"
    return Triple(next, subjName, progressMap)
}

