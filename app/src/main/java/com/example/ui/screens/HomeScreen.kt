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
    var selectedTier by remember { mutableStateOf(DifficultyTier.BASIC) }
    var greetingIndex by remember { mutableIntStateOf(0) }

    val mascotGreetings = listOf(
        "Foco e consistência geram maestria.",
        "Pratique no terminal e acompanhe seu progresso.",
        "Cada desafio concluído eleva seu raciocínio.",
        "Mantenha sua sequência diária ativa!",
        "Pronto para resolver exercícios e aprender?"
    )

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
            // ========================================================
            // 1. CABEÇALHO COMPACTO MINIMALISTA
            // Mescla boas-vindas e Otto, seu Tutor, sem fundos pesados em bloco
            // ========================================================
            Surface(
                color = ObsidianSurface,
                shape = RoundedCornerShape(12.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("compact_minimal_header")
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
                        // Avatar sutil do Otto com badge
                        Box(
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(SurfaceElevated)
                                .border(1.dp, PrimaryViolet.copy(alpha = 0.6f), CircleShape)
                                .clickable {
                                    greetingIndex = (greetingIndex + 1) % mascotGreetings.size
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_mascot_otter_boy),
                                contentDescription = "Otto, seu Tutor",
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .size(42.dp)
                                    .clip(CircleShape)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Olá, $userName",
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = "• Otto",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = PrimaryViolet,
                                        fontWeight = FontWeight.SemiBold
                                    )
                                )
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = mascotGreetings[greetingIndex % mascotGreetings.size],
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 12.sp,
                                    lineHeight = 16.sp
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    }

                    // Status de Sequência Compacto
                    Surface(
                        color = if (hasStudiedToday) Color(0xFF7C2D12).copy(alpha = 0.25f) else SurfaceElevated,
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(
                            1.dp,
                            if (hasStudiedToday) Color(0xFFF97316).copy(alpha = 0.6f) else BorderSubtle
                        ),
                        modifier = Modifier.clickable {
                            greetingIndex = (greetingIndex + 1) % mascotGreetings.size
                        }
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 6.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Icon(
                                imageVector = if (hasStudiedToday) KnowverseIcons.StreakFire else KnowverseIcons.FlameInactive,
                                contentDescription = "Sequência",
                                tint = if (hasStudiedToday) Color(0xFFFB923C) else TextTertiary,
                                modifier = Modifier.size(16.dp)
                            )
                            Text(
                                text = "${userProgress.currentStreak}d",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = if (hasStudiedToday) Color(0xFFFED7AA) else TextSecondary,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }
                }
            }

            // ========================================================
            // 2. DESTAQUE PARA O RESUMO: CONTINUAR APRENDIZADO RECENTE
            // Ação principal (Call to Action) mais evidente logo abaixo do topo
            // ========================================================
            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, PrimaryViolet.copy(alpha = 0.5f), RoundedCornerShape(12.dp))
                    .clickable { onContinueLearningClick(nextLesson) }
                    .testTag("continue_recent_learning_card")
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
                                .size(40.dp)
                                .clip(RoundedCornerShape(8.dp))
                                .background(PrimaryViolet.copy(alpha = 0.2f)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                imageVector = Icons.Default.PlayArrow,
                                contentDescription = "Continuar",
                                tint = PrimaryViolet,
                                modifier = Modifier.size(24.dp)
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "CONTINUAR APRENDIZADO",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = PrimaryViolet,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 10.sp,
                                        letterSpacing = 0.5.sp
                                    )
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    color = GoldXp.copy(alpha = 0.15f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "+${nextLesson.xpReward} XP",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = GoldXp,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 10.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 1.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = nextLesson.title,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                ),
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(
                                text = "Próxima lição sugerida do seu plano de estudo",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextSecondary,
                                    fontSize = 11.sp
                                )
                            )
                        }
                    }

                    Button(
                        onClick = { onContinueLearningClick(nextLesson) },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                        shape = RoundedCornerShape(8.dp),
                        contentPadding = PaddingValues(horizontal = 12.dp, vertical = 6.dp),
                        modifier = Modifier.height(36.dp)
                    ) {
                        Text(
                            text = "Estudar",
                            style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold)
                        )
                    }
                }
            }

            // ========================================================
            // 3. GRID DE MÓDULOS (2 COLUNAS)
            // Substitui os blocos gigantes por cards compactos e limpos
            // ========================================================
            Text(
                text = "Módulos de Estudo",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary,
                    letterSpacing = 0.5.sp
                ),
                modifier = Modifier.padding(top = 2.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Card 1: Trilha de Programação & Matemática
                ModuleGridCard(
                    title = "Trilha de Aulas",
                    subtitle = "Python & Matemática",
                    tag = "Teoria & Código",
                    tagColor = PrimaryViolet,
                    icon = Icons.Default.Code,
                    onClick = onStartNewLearning,
                    modifier = Modifier.weight(1f)
                )

                // Card 2: Banco de Exercícios
                ModuleGridCard(
                    title = "Exercícios",
                    subtitle = "Banco Adaptativo",
                    tag = "Modo Foco",
                    tagColor = Color(0xFFF59E0B),
                    icon = Icons.Default.MenuBook,
                    onClick = onNavigateToExercises,
                    modifier = Modifier.weight(1f)
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                // Card 3: Laboratório de Redação
                ModuleGridCard(
                    title = "Redação ENEM",
                    subtitle = "Correção via IA",
                    tag = "Matriz INEP",
                    tagColor = SuccessEmerald,
                    icon = Icons.Default.Edit,
                    onClick = onNavigateToEssayLab,
                    modifier = Modifier.weight(1f)
                )

                // Card 4: Ranking & Comunidade
                ModuleGridCard(
                    title = "Ranking Global",
                    subtitle = "Posição: #${userRankPosition}",
                    tag = "${userProgress.totalXp} XP",
                    tagColor = GoldXp,
                    icon = Icons.Default.EmojiEvents,
                    onClick = onNavigateToRanking,
                    modifier = Modifier.weight(1f)
                )
            }

            // ========================================================
            // 4. ATALHOS SECUNDÁRIOS COMPACTOS (Limpeza visual)
            // Teste Final e Compartilhar em formato enxuto
            // ========================================================
            Text(
                text = "Avaliação de Módulo & Conquistas",
                style = MaterialTheme.typography.labelSmall.copy(
                    fontWeight = FontWeight.Bold,
                    color = TextSecondary,
                    letterSpacing = 0.5.sp
                ),
                modifier = Modifier.padding(top = 2.dp)
            )

            // Seletor enxuto de Tier e Teste Final
            Surface(
                color = ObsidianSurface,
                shape = RoundedCornerShape(10.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.WorkspacePremium,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(
                                text = "Teste Final do Módulo",
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            )
                        }

                        // Seletor horizontal compacto de módulos
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(4.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            listOf(
                                DifficultyTier.BASIC to "Básico",
                                DifficultyTier.INTERMEDIATE to "Médio",
                                DifficultyTier.ADVANCED to "Avançado"
                            ).forEach { (tier, label) ->
                                val isSelected = selectedTier == tier
                                Surface(
                                    color = if (isSelected) PrimaryViolet else SurfaceElevated,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.clickable { selectedTier = tier }
                                ) {
                                    Text(
                                        text = label,
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            fontSize = 10.sp,
                                            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                                            color = if (isSelected) Color.White else TextSecondary
                                        ),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 3.dp)
                                    )
                                }
                            }
                        }
                    }

                    val activeTest = when (selectedTier) {
                        DifficultyTier.BASIC -> PythonCurriculum.basicKahoot
                        DifficultyTier.INTERMEDIATE -> PythonCurriculum.intermediateKahoot
                        DifficultyTier.ADVANCED -> PythonCurriculum.advancedKahoot
                    }
                    val isTierUnlocked = userProgress.isTierUnlocked(selectedTier)
                    val tierLessons = PythonCurriculum.getUnitsForTier(selectedTier).flatMap { it.lessons }
                    val allTierLessonsDone = tierLessons.isNotEmpty() && tierLessons.all { userProgress.completedLessonIds.contains(it.id) }
                    val isTestPlayable = isTierUnlocked && allTierLessonsDone

                    Spacer(modifier = Modifier.height(8.dp))

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(6.dp))
                            .background(SurfaceCard)
                            .clickable(enabled = isTestPlayable) { onStartFinalTest(activeTest) }
                            .padding(horizontal = 10.dp, vertical = 8.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = activeTest.title,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = if (isTestPlayable) TextPrimary else TextTertiary
                                )
                            )
                            Text(
                                text = if (isTestPlayable) "Disponível para testar seus conhecimentos!" else "Conclua as aulas do módulo para liberar.",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    color = TextSecondary,
                                    fontSize = 10.sp
                                )
                            )
                        }

                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                            contentDescription = "Abrir teste",
                            tint = if (isTestPlayable) PrimaryViolet else TextTertiary,
                            modifier = Modifier.size(16.dp)
                        )
                    }
                }
            }

            // Atalho minimalista de Compartilhar
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
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Compartilhar",
                            tint = PrimaryViolet,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Convidar amigos para estudar no Knowverse",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextSecondary,
                                fontSize = 11.5.sp
                            )
                        )
                    }
                    Text(
                        text = "Compartilhar ➔",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = PrimaryViolet,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

/**
 * Card compacto e limpo para o grid de 2 colunas da tela inicial.
 */
@Composable
private fun ModuleGridCard(
    title: String,
    subtitle: String,
    tag: String,
    tagColor: Color,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        color = ObsidianSurface,
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Box(
                    modifier = Modifier
                        .size(34.dp)
                        .clip(RoundedCornerShape(6.dp))
                        .background(tagColor.copy(alpha = 0.15f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = icon,
                        contentDescription = null,
                        tint = tagColor,
                        modifier = Modifier.size(18.dp)
                    )
                }

                Surface(
                    color = tagColor.copy(alpha = 0.12f),
                    shape = RoundedCornerShape(4.dp)
                ) {
                    Text(
                        text = tag,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = tagColor,
                            fontWeight = FontWeight.Bold,
                            fontSize = 9.5.sp
                        ),
                        modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
                    )
                }
            }

            Column {
                Text(
                    text = title,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Bold,
                        color = TextPrimary
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = subtitle,
                    style = MaterialTheme.typography.bodySmall.copy(
                        color = TextSecondary,
                        fontSize = 11.sp
                    ),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
