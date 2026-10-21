package com.example.ui.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import com.example.data.local.UserProgressRepository
import com.example.data.model.TrackBadgesCatalog
import com.example.data.model.UserProgressAdminRecord
import com.example.ui.components.KnowverseIcons
import com.example.ui.components.UserAvatarDisplay
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.CodeTextStyle
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

@Composable
fun DevDashboardScreen(
    progressRepository: UserProgressRepository,
    currentDevEmail: String,
    onNavigateBack: () -> Unit
) {
    BackHandler {
        onNavigateBack()
    }

    var isLoading by remember { mutableStateOf(true) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var usersList by remember { mutableStateOf<List<UserProgressAdminRecord>>(emptyList()) }
    var searchQuery by remember { mutableStateOf("") }

    fun refreshUsers() {
        isLoading = true
        errorMessage = null
        progressRepository.fetchAllUsersForDev(
            onSuccess = { list ->
                usersList = list
                isLoading = false
            },
            onError = { err ->
                errorMessage = err
                isLoading = false
            }
        )
    }

    DisposableEffect(Unit) {
        isLoading = true
        errorMessage = null
        val listener = progressRepository.listenAllUsersForDev(
            onUpdate = { list ->
                usersList = list
                isLoading = false
            },
            onError = { err ->
                errorMessage = err
                isLoading = false
            }
        )
        onDispose {
            listener?.remove()
        }
    }

    val filteredUsers = usersList.filter {
        it.displayName.contains(searchQuery, ignoreCase = true) ||
        it.email.contains(searchQuery, ignoreCase = true)
    }

    Scaffold(
        containerColor = DeepNavy,
        topBar = {
            Surface(
                color = ObsidianSurface,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = BorderSubtle)
                    .statusBarsPadding()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        IconButton(
                            onClick = onNavigateBack,
                            modifier = Modifier.size(36.dp).testTag("dev_back_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Voltar",
                                tint = TextPrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                        Spacer(modifier = Modifier.width(6.dp))
                        Column {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = "Painel Dev",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = TextPrimary
                                    )
                                )
                                Spacer(modifier = Modifier.width(6.dp))
                                Surface(
                                    color = PrimaryViolet.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(4.dp)
                                ) {
                                    Text(
                                        text = "Admin",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = PrimaryViolet,
                                            fontWeight = FontWeight.Bold
                                        ),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                                    )
                                }
                            }
                            Text(
                                text = currentDevEmail,
                                style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                            )
                        }
                    }

                    IconButton(
                        onClick = { refreshUsers() },
                        modifier = Modifier.size(36.dp).testTag("refresh_dev_users_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = "Recarregar",
                            tint = TextSecondary,
                            modifier = Modifier.size(18.dp)
                        )
                    }
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .navigationBarsPadding()
        ) {
            // Metrics Overview (Linear style 3-column stats)
            Surface(
                color = SurfaceCard,
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = BorderSubtle)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text("Usuários Registrados", style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary))
                        Text(
                            text = "${usersList.size}",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                        )
                    }
                    Column {
                        Text("Média XP", style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary))
                        val avgXp = if (usersList.isNotEmpty()) usersList.map { it.totalXp }.average().toInt() else 0
                        Text(
                            text = "$avgXp pts",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = GoldXp
                            )
                        )
                    }
                    Column {
                        Text("Média Ofensiva", style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary))
                        val avgStreak = if (usersList.isNotEmpty()) usersList.map { it.currentStreak }.average().toInt() else 0
                        Text(
                            text = "$avgStreak dias",
                            style = MaterialTheme.typography.titleMedium.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = StreakFire
                            )
                        )
                    }
                }
            }

            // Search Bar
            Box(modifier = Modifier.padding(horizontal = 16.dp, vertical = 10.dp)) {
                OutlinedTextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    placeholder = {
                        Text(
                            "Buscar aluno por nome ou email...",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextTertiary)
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = null,
                            tint = TextSecondary,
                            modifier = Modifier.size(16.dp)
                        )
                    },
                    singleLine = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = ObsidianSurface,
                        unfocusedContainerColor = ObsidianSurface,
                        focusedBorderColor = PrimaryViolet,
                        unfocusedBorderColor = BorderSubtle,
                        focusedTextColor = TextPrimary,
                        unfocusedTextColor = TextPrimary
                    ),
                    shape = RoundedCornerShape(6.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(46.dp)
                        .testTag("dev_search_user_input")
                )
            }

            // Users List
            when {
                isLoading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(color = PrimaryViolet, strokeWidth = 2.dp)
                    }
                }

                errorMessage != null -> {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Erro ao carregar telemetria: $errorMessage",
                            style = MaterialTheme.typography.bodyMedium.copy(color = TextSecondary)
                        )
                    }
                }

                filteredUsers.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize().padding(20.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = if (searchQuery.isEmpty()) "Nenhum usuário registrado ainda." else "Nenhum usuário corresponde à busca.",
                            style = MaterialTheme.typography.bodyMedium.copy(color = TextTertiary)
                        )
                    }
                }

                else -> {
                    LazyColumn(
                        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 4.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(filteredUsers) { user ->
                            Card(
                                shape = RoundedCornerShape(6.dp),
                                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .border(1.dp, BorderSubtle, RoundedCornerShape(6.dp))
                                    .testTag("user_admin_card_${user.userId}")
                            ) {
                                Column(modifier = Modifier.padding(12.dp)) {
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        verticalAlignment = Alignment.CenterVertically,
                                        horizontalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Row(verticalAlignment = Alignment.CenterVertically) {
                                            Box(
                                                modifier = Modifier
                                                    .size(34.dp)
                                                    .clip(CircleShape)
                                                    .background(SurfaceElevated)
                                                    .border(1.dp, BorderSubtle, CircleShape),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                UserAvatarDisplay(
                                                    photoKeyOrUrl = user.photoUrl,
                                                    sizeDp = 34
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Column {
                                                Row(verticalAlignment = Alignment.CenterVertically) {
                                                    Text(
                                                        text = user.displayName,
                                                        style = MaterialTheme.typography.bodyMedium.copy(
                                                            fontWeight = FontWeight.SemiBold,
                                                            color = TextPrimary
                                                        )
                                                    )
                                                    if (user.isDev) {
                                                        Spacer(modifier = Modifier.width(6.dp))
                                                        Surface(
                                                            color = PrimaryViolet.copy(alpha = 0.2f),
                                                            shape = RoundedCornerShape(4.dp)
                                                        ) {
                                                            Text(
                                                                text = "Dev",
                                                                style = MaterialTheme.typography.labelSmall.copy(
                                                                    fontSize = 9.sp,
                                                                    color = PrimaryViolet
                                                                ),
                                                                modifier = Modifier.padding(horizontal = 4.dp, vertical = 1.dp)
                                                            )
                                                        }
                                                    }
                                                }
                                                Text(
                                                    text = user.email,
                                                    style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                                )
                                            }
                                        }

                                        // Total XP badge
                                        Surface(
                                            color = SurfaceElevated,
                                            shape = RoundedCornerShape(4.dp),
                                            border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle)
                                        ) {
                                            Text(
                                                text = "${user.totalXp} XP",
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    fontWeight = FontWeight.Bold,
                                                    color = GoldXp
                                                ),
                                                modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                            )
                                        }
                                    }

                                    Spacer(modifier = Modifier.height(10.dp))

                                    // User Telemetry Details
                                    Row(
                                        modifier = Modifier.fillMaxWidth(),
                                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                                    ) {
                                        Text(
                                            text = "Nível ${user.level}",
                                            style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                        )
                                        Text(
                                            text = "• ${user.currentStreak} dias seguidos",
                                            style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                        )
                                        Text(
                                            text = "• ${user.completedLessonsCount} lições concluídas",
                                            style = MaterialTheme.typography.labelSmall.copy(color = SuccessEmerald)
                                        )
                                    }

                                    // Badges earned by user
                                    if (user.badges.isNotEmpty()) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Text(
                                                text = "Emblemas:",
                                                style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary)
                                            )
                                            user.badges.forEach { badgeId ->
                                                val badge = TrackBadgesCatalog.findBadge(badgeId)
                                                Surface(
                                                    color = PrimaryViolet.copy(alpha = 0.15f),
                                                    shape = RoundedCornerShape(4.dp),
                                                    border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.4f))
                                                ) {
                                                    Row(
                                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp),
                                                        verticalAlignment = Alignment.CenterVertically
                                                    ) {
                                                        Icon(
                                                            painter = painterResource(id = badge?.drawableRes ?: R.drawable.ic_badge_locked),
                                                            contentDescription = null,
                                                            tint = Color.Unspecified,
                                                            modifier = Modifier.size(12.dp)
                                                        )
                                                        Spacer(modifier = Modifier.width(4.dp))
                                                        Text(
                                                            text = badge?.title ?: badgeId,
                                                            style = MaterialTheme.typography.labelSmall.copy(
                                                                fontSize = 10.sp,
                                                                color = TextPrimary,
                                                                fontWeight = FontWeight.SemiBold
                                                            )
                                                        )
                                                    }
                                                }
                                            }
                                        }
                                    }

                                    // Completed lessons tags
                                    if (user.completedLessons.isNotEmpty()) {
                                        Spacer(modifier = Modifier.height(4.dp))
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                                        ) {
                                            Text(
                                                text = "Aulas Concluídas:",
                                                style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary)
                                            )
                                            Text(
                                                text = user.completedLessons.take(5).joinToString(", ") +
                                                        if (user.completedLessons.size > 5) " (+${user.completedLessons.size - 5})" else "",
                                                style = MaterialTheme.typography.labelSmall.copy(color = SuccessEmerald, fontSize = 10.sp)
                                            )
                                        }
                                    }

                                    // Kahoot record stats
                                    if (user.kahootScores.isNotEmpty()) {
                                        Spacer(modifier = Modifier.height(6.dp))
                                        Row(
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.spacedBy(6.dp)
                                        ) {
                                            Text(
                                                text = "Testes Finais:",
                                                style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary)
                                            )
                                            user.kahootScores.forEach { (modId, score) ->
                                                val cleanName = when {
                                                    modId.contains("basic") -> "Básico"
                                                    modId.contains("intermediate") -> "Intermed."
                                                    modId.contains("advanced") -> "Avançado"
                                                    modId.contains("calculus") || modId.contains("limits") -> "Limites"
                                                    else -> modId
                                                }
                                                Surface(
                                                    color = SurfaceElevated,
                                                    shape = RoundedCornerShape(4.dp)
                                                ) {
                                                    Text(
                                                        text = "$cleanName: $score pts",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            fontSize = 10.sp,
                                                            color = TextPrimary
                                                        ),
                                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
