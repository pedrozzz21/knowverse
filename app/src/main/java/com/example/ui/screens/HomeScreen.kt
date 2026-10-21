package com.example.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.auth.AuthManager
import com.example.util.ShareUtil
import com.example.data.math.CalculusCurriculum
import com.example.data.model.DifficultyTier
import com.example.data.model.KahootModule
import com.example.data.model.Lesson
import com.example.data.model.UserProgress
import com.example.data.python.PythonCurriculum
import com.example.ui.components.KnowverseIcons
import com.example.ui.components.TopUserStatsBar
import com.example.ui.components.UserAvatarDisplay
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
    onStartFinalTest: (KahootModule) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val scrollState = rememberScrollState()
    val onlineUsers by (progressRepository?.onlineLeaderboardState?.collectAsState() ?: remember { mutableStateOf(emptyList()) })
    var selectedTier by remember { mutableStateOf(DifficultyTier.BASIC) }
    var greetingIndex by remember { mutableIntStateOf(0) }

    val mascotGreetings = listOf(
        "Olá, $userName! Vamos dominar mais uma lição hoje?",
        "Que bom ter você aqui, $userName! Foco e consistência geram maestria.",
        "Preparado para programar em Python real, $userName?",
        "Cada desafio concluído no editor eleva seu raciocínio computacional, $userName!",
        "Muito bem, $userName! Pratique no terminal e acompanhe seu progresso."
    )

    // Locate next incomplete lesson in Python track
    val allLessons = PythonCurriculum.getAllLessons()
    val nextIncomplete = allLessons.firstOrNull { !userProgress.completedLessonIds.contains(it.id) }
    val nextLesson = PythonCurriculum.findLesson(userProgress.currentLessonId)?.takeIf { !userProgress.completedLessonIds.contains(it.id) }
        ?: nextIncomplete
        ?: allLessons.first()

    // Daily study state
    val hasStudiedToday = userProgress.hasStudiedToday()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        TopUserStatsBar(
            userProgress = userProgress,
            userPhotoUrl = userPhotoUrl,
            onStatsClick = onNavigateToRanking
        )

        Column(
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // ========================================================
            // 0. CABEÇALHO DO MENU PRINCIPAL COM O MASCOTE OFICIAL
            // ========================================================
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
                    .testTag("main_menu_brand_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(RoundedCornerShape(14.dp))
                            .background(Color(0xFF0C439A))
                            .border(1.5.dp, PrimaryViolet.copy(alpha = 0.5f), RoundedCornerShape(14.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_mascot_otter_boy),
                            contentDescription = "Mascote Oficial Knowverse",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(RoundedCornerShape(14.dp))
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = "Knowverse",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Surface(
                                color = PrimaryViolet.copy(alpha = 0.2f),
                                shape = RoundedCornerShape(4.dp)
                            ) {
                                Text(
                                    text = "Oficial",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = PrimaryViolet,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 9.sp
                                    ),
                                    modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = "Bem-vindo(a), $userName! Domine Programação & Matemática.",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary,
                                fontSize = 12.sp,
                                lineHeight = 16.sp
                            )
                        )
                    }
                }
            }

            // ========================================================
            // 1. STATUS DE ESTUDO DIÁRIO (FOGO ACESO / FOGO APAGADO EM .SVG)
            // ========================================================
            val flameBg = if (hasStudiedToday) {
                Brush.horizontalGradient(
                    colors = listOf(Color(0xFF2E1065), Color(0xFF431407), Color(0xFF1E1B4B))
                )
            } else {
                Brush.horizontalGradient(
                    colors = listOf(ObsidianSurface, SurfaceCard)
                )
            }

            val flameBorderColor = if (hasStudiedToday) Color(0xFFF97316) else Color(0xFF475569)

            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.Transparent),
                modifier = Modifier
                    .fillMaxWidth()
                    .background(flameBg, RoundedCornerShape(10.dp))
                    .border(1.5.dp, flameBorderColor.copy(alpha = 0.7f), RoundedCornerShape(10.dp))
                    .testTag("daily_study_flame_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.weight(1f)
                    ) {
                        // Custom SVG Flame Icon (Active vibrant vs Inactive cinza)
                        Box(
                            modifier = Modifier
                                .size(46.dp)
                                .clip(CircleShape)
                                .background(if (hasStudiedToday) Color(0xFF7C2D12).copy(alpha = 0.5f) else SurfaceElevated),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = if (hasStudiedToday) KnowverseIcons.FlameActive else KnowverseIcons.FlameInactive,
                                contentDescription = if (hasStudiedToday) "Fogo Aceso" else "Fogo Apagado",
                                tint = Color.Unspecified,
                                modifier = Modifier.size(30.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = if (hasStudiedToday) "Estudando em dia!" else "Vamos começar os estudos?",
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (hasStudiedToday) Color(0xFFFED7AA) else TextPrimary
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = if (hasStudiedToday)
                                    "Sequência ativa: ${userProgress.currentStreak} dias seguidos (${userProgress.activeStudyDaysCount} dias totais)"
                                else
                                    "Realize uma aula hoje para manter sua chama acesa!",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = if (hasStudiedToday) Color(0xFFFDBA74) else TextSecondary,
                                    fontSize = 12.sp
                                )
                            )
                        }
                    }

                    if (!hasStudiedToday) {
                        Button(
                            onClick = { onContinueLearningClick(nextLesson) },
                            colors = ButtonDefaults.buttonColors(
                                containerColor = PrimaryViolet,
                                contentColor = Color.White
                            ),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.height(34.dp)
                        ) {
                            Text("Estudar", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                        }
                    }
                }
            }

            // ========================================================
            // 2. MASCOTE LEÃO MARINHO (LEO O TUTOR)
            // ========================================================
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                    .testTag("mascot_greeting_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(SurfaceElevated)
                            .border(1.5.dp, PrimaryViolet.copy(alpha = 0.5f), CircleShape)
                            .clickable {
                                greetingIndex = (greetingIndex + 1) % mascotGreetings.size
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.img_mascot_otter_boy),
                            contentDescription = "Mascote Lontra Tutor",
                            contentScale = androidx.compose.ui.layout.ContentScale.Crop,
                            modifier = Modifier.size(60.dp).clip(CircleShape)
                        )
                    }

                    Spacer(modifier = Modifier.width(12.dp))

                    Column(modifier = Modifier.weight(1f)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Otto, seu Tutor",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = PrimaryViolet,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.WorkspacePremium,
                                    contentDescription = null,
                                    tint = GoldXp,
                                    modifier = Modifier.size(12.dp)
                                )
                            }

                            IconButton(
                                onClick = {
                                    greetingIndex = (greetingIndex + 1) % mascotGreetings.size
                                },
                                modifier = Modifier.size(24.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Refresh,
                                    contentDescription = "Outra mensagem",
                                    tint = TextTertiary,
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }

                        Spacer(modifier = Modifier.height(2.dp))

                        Text(
                            text = mascotGreetings[greetingIndex % mascotGreetings.size],
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextPrimary,
                                fontWeight = FontWeight.Medium,
                                lineHeight = 17.sp
                            )
                        )
                    }
                }
            }

            // ========================================================
            // 3. INICIAR UM NOVO APRENDIZADO (PROMINENTE)
            // ========================================================
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = PrimaryViolet),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onStartNewLearning() }
                    .testTag("start_new_learning_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "Iniciar um novo aprendizado",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        )
                        Spacer(modifier = Modifier.height(3.dp))
                        Text(
                            text = "Matérias: Programação (Python: Introdução, Intermediário, Avançado) e Matemática (Cálculo I: Limites, Derivadas, Integrais).",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = Color.White.copy(alpha = 0.9f),
                                lineHeight = 16.sp
                            )
                        )
                    }

                    Spacer(modifier = Modifier.width(10.dp))

                    Box(
                        modifier = Modifier
                            .size(38.dp)
                            .clip(CircleShape)
                            .background(Color.White.copy(alpha = 0.2f)),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Iniciar",
                            tint = Color.White,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }

            // Atalho Prominente: Banco de Resolução de Exercícios
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, PrimaryViolet.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
                    .clickable { onNavigateToExercises() }
                    .testTag("home_exercise_solver_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(PrimaryViolet.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.MenuBook,
                                contentDescription = null,
                                tint = PrimaryViolet,
                                modifier = Modifier.size(20.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Text(
                                text = "Banco de Resolução de Exercícios",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                            Text(
                                text = "Questões originais codificadas (10101, 20302, 50101, etc.) com resolução passo a passo.",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }

                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Ir para exercícios",
                        tint = PrimaryViolet,
                        modifier = Modifier.size(18.dp)
                    )
                }
            }

            // ========================================================
            // 4. CAIXA MENOR: CONTINUAR APRENDIZADO RECENTE
            // ========================================================
            Card(
                shape = RoundedCornerShape(6.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(6.dp))
                    .clickable { onContinueLearningClick(nextLesson) }
                    .testTag("continue_recent_learning_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        modifier = Modifier.weight(1f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(32.dp)
                                .clip(RoundedCornerShape(4.dp))
                                .background(SurfaceElevated),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = null,
                                tint = PrimaryViolet,
                                modifier = Modifier.size(18.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Column {
                            Text(
                                text = "Continuar aprendizado recente",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = TextSecondary,
                                    fontSize = 10.sp
                                )
                            )
                            Text(
                                text = nextLesson.title,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                            )
                        }
                    }

                    Surface(
                        color = PrimaryViolet.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "+${nextLesson.xpReward} XP",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = GoldXp,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }

            // ========================================================
            // 5. RANKING ATUAL NO MENU (SOLICITADO)
            // ========================================================
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = KnowverseIcons.Trophy,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Ranking Atual dos Estudantes",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                            )
                        }

                        Surface(
                            color = PrimaryViolet.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(4.dp),
                            modifier = Modifier.clickable { onNavigateToRanking() }
                        ) {
                            Text(
                                text = "Ver Todos",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = PrimaryViolet,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 11.sp
                                ),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp)
                            )
                        }
                    }

                    // User's current rank position highlight with Photo and prominent Position Badge
                    val currentUserId = authManager?.currentUserState?.value?.uid ?: "user_current"
                    val currentUserEmail = authManager?.currentUserState?.value?.email ?: ""
                    val rankingList = mutableListOf<Pair<String, Int>>()

                    if (onlineUsers.isNotEmpty()) {
                        for (u in onlineUsers) {
                            val isMe = (u.userId == currentUserId) || u.email.equals(currentUserEmail, ignoreCase = true)
                            val xp = if (isMe) maxOf(u.totalXp, userProgress.totalXp) else u.totalXp
                            rankingList.add(Pair(u.userId, xp))
                        }
                        if (rankingList.none { it.first == currentUserId }) {
                            rankingList.add(Pair(currentUserId, userProgress.totalXp))
                        }
                    } else {
                        rankingList.add(Pair(currentUserId, userProgress.totalXp))
                        val registeredAccounts = authManager?.getAllRegisteredUsers().orEmpty()
                        for (acc in registeredAccounts) {
                            if (acc.uid != currentUserId) {
                                val accXp = progressRepository?.getProgressForUser(acc.uid)?.totalXp ?: 0
                                rankingList.add(Pair(acc.uid, accXp))
                            }
                        }
                    }
                    rankingList.sortByDescending { it.second }
                    val userRankPosition = (rankingList.indexOfFirst { it.first == currentUserId }.takeIf { it >= 0 } ?: 0) + 1

                    Surface(
                        color = ObsidianSurface,
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.5f)),
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { onNavigateToRanking() }
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(12.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically,
                                modifier = Modifier.weight(1f)
                            ) {
                                // Prominent Highlighted Rank Position Number Badge
                                Surface(
                                    color = when (userRankPosition) {
                                        1 -> GoldXp.copy(alpha = 0.2f)
                                        2 -> Color(0xFFC0C0C0).copy(alpha = 0.2f)
                                        3 -> Color(0xFFCD7F32).copy(alpha = 0.2f)
                                        else -> PrimaryViolet.copy(alpha = 0.2f)
                                    },
                                    shape = RoundedCornerShape(6.dp),
                                    border = androidx.compose.foundation.BorderStroke(
                                        width = 1.dp,
                                        color = when (userRankPosition) {
                                            1 -> GoldXp
                                            2 -> Color(0xFFC0C0C0)
                                            3 -> Color(0xFFCD7F32)
                                            else -> PrimaryViolet
                                        }
                                    )
                                ) {
                                    Row(
                                        modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            imageVector = Icons.Default.EmojiEvents,
                                            contentDescription = null,
                                            tint = when (userRankPosition) {
                                                1 -> GoldXp
                                                2 -> Color(0xFFC0C0C0)
                                                3 -> Color(0xFFCD7F32)
                                                else -> PrimaryViolet
                                            },
                                            modifier = Modifier.size(16.dp)
                                        )
                                        Spacer(modifier = Modifier.width(4.dp))
                                        Text(
                                            text = "${userRankPosition}º",
                                            style = MaterialTheme.typography.titleMedium.copy(
                                                color = when (userRankPosition) {
                                                    1 -> GoldXp
                                                    2 -> Color(0xFFC0C0C0)
                                                    3 -> Color(0xFFCD7F32)
                                                    else -> PrimaryViolet
                                                },
                                                fontWeight = FontWeight.ExtraBold,
                                                fontSize = 16.sp
                                            )
                                        )
                                    }
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                // User Photo Avatar
                                Box(
                                    modifier = Modifier
                                        .size(42.dp)
                                        .clip(CircleShape)
                                        .background(SurfaceElevated)
                                        .border(1.5.dp, PrimaryViolet.copy(alpha = 0.7f), CircleShape),
                                    contentAlignment = Alignment.Center
                                ) {
                                    UserAvatarDisplay(
                                        photoKeyOrUrl = userPhotoUrl,
                                        sizeDp = 42
                                    )
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                // Name, "Você" badge, and XP info
                                Column(modifier = Modifier.weight(1f)) {
                                    Row(verticalAlignment = Alignment.CenterVertically) {
                                        Text(
                                            text = userName,
                                            style = MaterialTheme.typography.bodyMedium.copy(
                                                fontWeight = FontWeight.Bold,
                                                color = TextPrimary
                                            ),
                                            maxLines = 1
                                        )
                                        Spacer(modifier = Modifier.width(6.dp))
                                        Surface(
                                            color = SuccessEmerald.copy(alpha = 0.2f),
                                            shape = RoundedCornerShape(3.dp)
                                        ) {
                                            Text(
                                                text = "Você",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    color = SuccessEmerald,
                                                    fontSize = 9.sp,
                                                    fontWeight = FontWeight.Bold
                                                ),
                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                            )
                                        }
                                    }
                                    Spacer(modifier = Modifier.height(2.dp))
                                    Text(
                                        text = "${userProgress.totalXp} XP • Nível ${userProgress.level}",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TextSecondary,
                                            fontSize = 11.sp
                                        )
                                    )
                                }
                            }

                            // Streak Counter
                            Surface(
                                color = SurfaceElevated,
                                shape = RoundedCornerShape(6.dp),
                                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle)
                            ) {
                                Row(
                                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    Icon(
                                        imageVector = KnowverseIcons.StreakFire,
                                        contentDescription = null,
                                        tint = Color(0xFFFB923C),
                                        modifier = Modifier.size(14.dp)
                                    )
                                    Spacer(modifier = Modifier.width(4.dp))
                                    Text(
                                        text = "${userProgress.currentStreak}d",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = TextPrimary,
                                            fontWeight = FontWeight.Bold
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // ========================================================
            // CONVITE & COMPARTILHAMENTO DO APP
            // ========================================================
            Surface(
                color = SurfaceElevated,
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { ShareUtil.shareApp(context) }
                    .testTag("home_share_app_card")
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
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(RoundedCornerShape(6.dp))
                                .background(PrimaryViolet.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = "Compartilhar",
                                tint = PrimaryViolet,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column {
                            Text(
                                text = "Compartilhar o Knowverse",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                            Text(
                                text = "Envie o link do app para estudar com amigos!",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }

                    Button(
                        onClick = { ShareUtil.shareApp(context) },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                        shape = RoundedCornerShape(6.dp),
                        contentPadding = PaddingValues(horizontal = 10.dp, vertical = 4.dp),
                        modifier = Modifier.height(32.dp)
                    ) {
                        Text(
                            text = "Enviar",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }

            // ========================================================
            // 6. TESTE FINAL DO MÓDULO (30S POR QUESTÃO)
            // ========================================================
            Text(
                text = "Teste Final do Módulo",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
            )

            // Tier Switcher: Introdução, Intermediário, Avançado
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(6.dp))
                    .background(ObsidianSurface)
                    .border(1.dp, BorderSubtle, RoundedCornerShape(6.dp))
                    .padding(2.dp)
            ) {
                TierTabItem(
                    title = "1. Introdução",
                    isSelected = selectedTier == DifficultyTier.BASIC,
                    onClick = { selectedTier = DifficultyTier.BASIC },
                    modifier = Modifier.weight(1f)
                )
                TierTabItem(
                    title = "2. Intermediário",
                    isSelected = selectedTier == DifficultyTier.INTERMEDIATE,
                    onClick = { selectedTier = DifficultyTier.INTERMEDIATE },
                    modifier = Modifier.weight(1f)
                )
                TierTabItem(
                    title = "3. Avançado",
                    isSelected = selectedTier == DifficultyTier.ADVANCED,
                    onClick = { selectedTier = DifficultyTier.ADVANCED },
                    modifier = Modifier.weight(1f)
                )
            }

            val activeTest = when (selectedTier) {
                DifficultyTier.BASIC -> PythonCurriculum.basicKahoot
                DifficultyTier.INTERMEDIATE -> PythonCurriculum.intermediateKahoot
                DifficultyTier.ADVANCED -> PythonCurriculum.advancedKahoot
            }

            val savedScore = userProgress.completedKahootScores[activeTest.id] ?: 0
            val isTierUnlocked = userProgress.isTierUnlocked(selectedTier)
            val tierLessons = PythonCurriculum.getUnitsForTier(selectedTier).flatMap { it.lessons }
            val allTierLessonsDone = tierLessons.isNotEmpty() && tierLessons.all { userProgress.completedLessonIds.contains(it.id) }
            val isTestPlayable = isTierUnlocked && allTierLessonsDone

            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        1.dp,
                        if (isTestPlayable) GoldXp.copy(alpha = 0.5f) else BorderSubtle,
                        RoundedCornerShape(8.dp)
                    )
                    .clickable(enabled = isTestPlayable) { onStartFinalTest(activeTest) }
                    .testTag("final_test_banner")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(14.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = activeTest.title,
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isTestPlayable) TextPrimary else TextTertiary
                                )
                            )
                            if (savedScore > 0) {
                                Spacer(modifier = Modifier.width(8.dp))
                                Surface(
                                    color = SuccessEmerald.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "$savedScore pts",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = SuccessEmerald,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = when {
                                !isTierUnlocked -> "Bloqueado: Conclua o módulo anterior com 100% no Teste Final para liberar."
                                !allTierLessonsDone -> "Bloqueado: Conclua todas as aulas do módulo para liberar este Teste Final."
                                else -> "Disponível! Responda o desafio e acerte 100% para conquistar o Emblema e liberar o próximo módulo."
                            },
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = if (isTestPlayable) TextSecondary else TextTertiary,
                                fontSize = 11.sp
                            )
                        )
                    }

                    Box(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(RoundedCornerShape(6.dp))
                            .background(if (isTestPlayable) PrimaryViolet else SurfaceElevated),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            imageVector = Icons.Default.WorkspacePremium,
                            contentDescription = "Jogar Teste Final",
                            tint = if (isTestPlayable) Color.White else TextTertiary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun TierTabItem(
    title: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(4.dp))
            .background(if (isSelected) SurfaceElevated else Color.Transparent)
            .clickable(onClick = onClick)
            .padding(vertical = 7.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.labelSmall.copy(
                fontWeight = if (isSelected) FontWeight.SemiBold else FontWeight.Normal,
                color = if (isSelected) TextPrimary else TextTertiary
            )
        )
    }
}
