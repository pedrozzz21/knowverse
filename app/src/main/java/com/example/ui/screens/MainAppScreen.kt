package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.border
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.EmojiEvents
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.MenuBook
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.auth.AuthManager
import com.example.data.local.UserProgressRepository
import com.example.data.model.DifficultyTier
import com.example.data.model.KahootModule
import com.example.data.model.Lesson
import com.example.data.python.CodeEvaluator
import com.example.data.python.PythonEngine
import com.example.ui.components.KnowverseIcons
import com.example.ui.components.UserAvatarDisplay
import com.example.ui.components.InAppAchievementNotificationBanner
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary

enum class NavigationTab {
    HOME,
    TRAIL,
    EXERCISES,
    LEADERBOARD,
    PROFILE
}

@Composable
fun MainAppScreen(
    progressRepository: UserProgressRepository,
    pythonEngine: PythonEngine,
    evaluator: CodeEvaluator,
    authManager: AuthManager,
    trailCacheRepository: com.example.data.local.room.TrailCacheRepository? = null,
    streakReminderManager: com.example.notification.StreakReminderManager? = null
) {
    val currentUser by authManager.currentUserState.collectAsState()
    val userProgress by progressRepository.progressState.collectAsState()

    // Sync user progress with Firestore whenever currentUser is present
    LaunchedEffect(currentUser) {
        val user = currentUser
        if (user != null) {
            progressRepository.syncUserAccount(
                userId = user.uid,
                email = user.email,
                displayName = user.displayName,
                photoUrl = user.photoUrl
            )
        } else {
            progressRepository.clearUserSession()
        }
    }

    // Auth Gate: If user is not logged in, enforce AuthGateScreen
    if (currentUser == null) {
        AuthGateScreen(authManager = authManager)
        return
    }

    var currentTab by remember { mutableStateOf(NavigationTab.HOME) }
    var selectedTrailTier by remember { mutableStateOf(DifficultyTier.BASIC) }
    var activeLesson by remember { mutableStateOf<Lesson?>(null) }
    var activeKahoot by remember { mutableStateOf<KahootModule?>(null) }
    var showDevDashboard by remember { mutableStateOf(false) }
    var showEssayLab by remember { mutableStateOf(false) }
    var isExerciseFocusMode by remember { mutableStateOf(false) }

    LaunchedEffect(currentTab) {
        isExerciseFocusMode = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        when {
            showEssayLab -> {
                EssayLabScreen(
                    progressRepository = progressRepository,
                    onNavigateBack = { showEssayLab = false }
                )
            }

            showDevDashboard -> {
                DevDashboardScreen(
                    progressRepository = progressRepository,
                    currentDevEmail = currentUser?.email ?: "contatopedro21g@gmail.com",
                    onNavigateBack = { showDevDashboard = false }
                )
            }

            activeKahoot != null -> {
                KahootQuizScreen(
                    module = activeKahoot!!,
                    progressRepository = progressRepository,
                    onNavigateBack = { activeKahoot = null }
                )
            }

            activeLesson != null -> {
                val currentLesson = activeLesson!!
                if (currentLesson.id.startsWith("calc_") || currentLesson.id.startsWith("mb_") || currentLesson.mathExercises.isNotEmpty()) {
                    CalculusLessonScreen(
                        lesson = currentLesson,
                        progressRepository = progressRepository,
                        onNavigateBack = { activeLesson = null },
                        onNavigateToNextLesson = { next -> activeLesson = next }
                    )
                } else {
                    LessonActivityScreen(
                        lesson = currentLesson,
                        pythonEngine = pythonEngine,
                        evaluator = evaluator,
                        progressRepository = progressRepository,
                        onNavigateBack = { activeLesson = null },
                        onNavigateToLesson = { next -> activeLesson = next }
                    )
                }
            }

            else -> {
                // Main Tab Navigation with BackHandler
                BackHandler(enabled = currentTab != NavigationTab.HOME) {
                    currentTab = NavigationTab.HOME
                }

                Scaffold(
                    containerColor = DeepNavy,
                    contentWindowInsets = WindowInsets(0, 0, 0, 0),
                    bottomBar = {
                        if (!isExerciseFocusMode) {
                            NavigationBar(
                                containerColor = ObsidianSurface,
                            tonalElevation = 4.dp,
                            windowInsets = NavigationBarDefaults.windowInsets,
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(width = 1.dp, color = BorderSubtle)
                        ) {
                            // Tab 1: Início
                            NavigationBarItem(
                                selected = currentTab == NavigationTab.HOME,
                                onClick = { currentTab = NavigationTab.HOME },
                                icon = {
                                    Icon(
                                        imageVector = if (currentTab == NavigationTab.HOME) Icons.Filled.Home else Icons.Outlined.Home,
                                        contentDescription = "Início",
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Início",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = if (currentTab == NavigationTab.HOME) FontWeight.Bold else FontWeight.Medium
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = PrimaryViolet,
                                    selectedTextColor = TextPrimary,
                                    indicatorColor = PrimaryViolet.copy(alpha = 0.25f),
                                    unselectedIconColor = TextTertiary,
                                    unselectedTextColor = TextTertiary
                                ),
                                modifier = Modifier.testTag("tab_home")
                            )

                            // Tab 2: Trilha
                            NavigationBarItem(
                                selected = currentTab == NavigationTab.TRAIL,
                                onClick = { currentTab = NavigationTab.TRAIL },
                                icon = {
                                    Icon(
                                        imageVector = KnowverseIcons.CodeTerminal,
                                        contentDescription = "Trilha",
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Trilha",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = if (currentTab == NavigationTab.TRAIL) FontWeight.Bold else FontWeight.Medium
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = PrimaryViolet,
                                    selectedTextColor = TextPrimary,
                                    indicatorColor = PrimaryViolet.copy(alpha = 0.25f),
                                    unselectedIconColor = TextTertiary,
                                    unselectedTextColor = TextTertiary
                                ),
                                modifier = Modifier.testTag("tab_trail")
                            )

                            // Tab 3: Exercícios (Aba dedicada exclusivamente para resolução de exercícios)
                            NavigationBarItem(
                                selected = currentTab == NavigationTab.EXERCISES,
                                onClick = { currentTab = NavigationTab.EXERCISES },
                                icon = {
                                    Icon(
                                        imageVector = if (currentTab == NavigationTab.EXERCISES) Icons.Filled.MenuBook else Icons.Outlined.MenuBook,
                                        contentDescription = "Exercícios",
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Exercícios",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = if (currentTab == NavigationTab.EXERCISES) FontWeight.Bold else FontWeight.Medium
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = PrimaryViolet,
                                    selectedTextColor = TextPrimary,
                                    indicatorColor = PrimaryViolet.copy(alpha = 0.25f),
                                    unselectedIconColor = TextTertiary,
                                    unselectedTextColor = TextTertiary
                                ),
                                modifier = Modifier.testTag("tab_exercises")
                            )

                            // Tab 4: Ranking / Leaderboard
                            NavigationBarItem(
                                selected = currentTab == NavigationTab.LEADERBOARD,
                                onClick = { currentTab = NavigationTab.LEADERBOARD },
                                icon = {
                                    Icon(
                                        imageVector = if (currentTab == NavigationTab.LEADERBOARD) Icons.Filled.EmojiEvents else Icons.Outlined.EmojiEvents,
                                        contentDescription = "Ranking",
                                        modifier = Modifier.size(24.dp)
                                    )
                                },
                                label = {
                                    Text(
                                        text = "Ranking",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = if (currentTab == NavigationTab.LEADERBOARD) FontWeight.Bold else FontWeight.Medium
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = PrimaryViolet,
                                    selectedTextColor = TextPrimary,
                                    indicatorColor = PrimaryViolet.copy(alpha = 0.25f),
                                    unselectedIconColor = TextTertiary,
                                    unselectedTextColor = TextTertiary
                                ),
                                modifier = Modifier.testTag("tab_leaderboard")
                            )

                            // Tab 5: Perfil (Shows user's live profile avatar)
                            NavigationBarItem(
                                selected = currentTab == NavigationTab.PROFILE,
                                onClick = { currentTab = NavigationTab.PROFILE },
                                icon = {
                                    Box(
                                        modifier = Modifier
                                            .size(26.dp)
                                            .clip(CircleShape)
                                            .border(
                                                width = if (currentTab == NavigationTab.PROFILE) 2.dp else 1.dp,
                                                color = if (currentTab == NavigationTab.PROFILE) PrimaryViolet else BorderSubtle,
                                                shape = CircleShape
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        UserAvatarDisplay(
                                            photoKeyOrUrl = currentUser?.photoUrl,
                                            sizeDp = 26
                                        )
                                    }
                                },
                                label = {
                                    Text(
                                        text = "Perfil",
                                        style = MaterialTheme.typography.labelSmall,
                                        fontWeight = if (currentTab == NavigationTab.PROFILE) FontWeight.Bold else FontWeight.Medium
                                    )
                                },
                                colors = NavigationBarItemDefaults.colors(
                                    selectedIconColor = PrimaryViolet,
                                    selectedTextColor = TextPrimary,
                                    indicatorColor = PrimaryViolet.copy(alpha = 0.25f),
                                    unselectedIconColor = TextTertiary,
                                    unselectedTextColor = TextTertiary
                                ),
                                modifier = Modifier.testTag("tab_profile")
                            )
                        }
                    }
                }
                ) { paddingValues ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    ) {
                        AnimatedContent(
                            targetState = currentTab,
                            transitionSpec = { fadeIn() togetherWith fadeOut() },
                            label = "tab_transition"
                        ) { tab ->
                            when (tab) {
                                NavigationTab.HOME -> {
                                    HomeScreen(
                                        userProgress = userProgress,
                                        userName = currentUser?.displayName ?: "Estudante",
                                        userPhotoUrl = currentUser?.photoUrl,
                                        authManager = authManager,
                                        progressRepository = progressRepository,
                                        onContinueLearningClick = { lesson -> activeLesson = lesson },
                                        onStartNewLearning = { currentTab = NavigationTab.TRAIL },
                                        onNavigateToTrail = { currentTab = NavigationTab.TRAIL },
                                        onNavigateToRanking = { currentTab = NavigationTab.LEADERBOARD },
                                        onNavigateToExercises = { currentTab = NavigationTab.EXERCISES },
                                        onNavigateToEssayLab = { showEssayLab = true },
                                        onStartFinalTest = { module -> activeKahoot = module }
                                    )
                                }

                                NavigationTab.TRAIL -> {
                                    LearnTrailScreen(
                                        userProgress = userProgress,
                                        initialTier = selectedTrailTier,
                                        userPhotoUrl = currentUser?.photoUrl,
                                        onSelectLesson = { lesson -> activeLesson = lesson },
                                        onStartFinalTest = { module -> activeKahoot = module }
                                    )
                                }

                                NavigationTab.EXERCISES -> {
                                    ExerciseSolverScreen(
                                        progressRepository = progressRepository,
                                        userPhotoUrl = currentUser?.photoUrl,
                                        onNavigateToEssayLab = { showEssayLab = true },
                                        isFocusModeActive = isExerciseFocusMode,
                                        onFocusModeChange = { isExerciseFocusMode = it }
                                    )
                                }

                                NavigationTab.LEADERBOARD -> {
                                    LeaderboardScreen(
                                        userProgress = userProgress,
                                        authManager = authManager,
                                        progressRepository = progressRepository
                                    )
                                }

                                NavigationTab.PROFILE -> {
                                    ProfileScreen(
                                        userProgress = userProgress,
                                        authManager = authManager,
                                        onOpenDevDashboard = { showDevDashboard = true },
                                        progressRepository = progressRepository
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }

        // Floating In-App Achievement Notification Banner across all screens
        InAppAchievementNotificationBanner()
    }
}
