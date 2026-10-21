package com.example.ui.screens

import android.app.Activity
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import java.io.ByteArrayOutputStream
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import kotlinx.coroutines.launch
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AdminPanelSettings
import androidx.compose.material.icons.filled.CloudUpload
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.EmojiEvents
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import android.os.Build
import android.Manifest
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.NotificationsActive
import androidx.compose.material.icons.filled.CloudDone
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import com.example.notification.StreakReminderManager
import com.example.data.local.room.KnowverseDatabase
import com.example.data.local.room.TrailCacheRepository
import androidx.compose.material.icons.filled.Security
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.ContentCopy
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.R
import com.example.auth.AuthManager
import com.example.util.ShareUtil
import com.example.data.model.TrackBadgesCatalog
import com.example.data.model.UserProgress
import com.example.ui.components.KnowverseIcons
import com.example.ui.components.TopUserStatsBar
import com.example.ui.components.UserAvatarDisplay
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.DeepNavy
import com.example.ui.theme.ErrorRose
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
import java.io.File
import java.io.FileOutputStream

@Composable
fun ProfileScreen(
    userProgress: UserProgress,
    authManager: AuthManager,
    onOpenDevDashboard: () -> Unit,
    progressRepository: com.example.data.local.UserProgressRepository? = null,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val currentUser by authManager.currentUserState.collectAsState()

    var showSignOutDialog by remember { mutableStateOf(false) }
    var showDeleteAccountDialog by remember { mutableStateOf(false) }
    var isDeletingAccount by remember { mutableStateOf(false) }
    var showEditProfileDialog by remember { mutableStateOf(false) }
    var showChangePasswordDialog by remember { mutableStateOf(false) }
    var showChangeEmailDialog by remember { mutableStateOf(false) }
    var actionFeedbackMessage by remember { mutableStateOf<String?>(null) }
    var actionErrorMessage by remember { mutableStateOf<String?>(null) }

    // Edit profile state
    var editName by remember { mutableStateOf(currentUser?.displayName ?: "") }
    var editBio by remember { mutableStateOf(currentUser?.bio ?: "") }
    var selectedAvatarKey by remember { mutableStateOf(currentUser?.photoUrl ?: "mascot_sealion") }

    // Sync input fields whenever currentUser updates
    LaunchedEffect(currentUser) {
        currentUser?.let { u ->
            editName = u.displayName
            editBio = u.bio
            selectedAvatarKey = u.photoUrl ?: "mascot_sealion"
        }
    }

    // Password change state
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    // Email change state
    var newEmailText by remember { mutableStateOf("") }

    val userEmail = currentUser?.email ?: "usuario@knowverse.com"
    val userName = currentUser?.displayName ?: "Estudante Knowverse"
    val isDevAccount = (currentUser?.isDev == true) || userEmail.equals("contatopedro21g@gmail.com", ignoreCase = true)

    val streakReminderManager = remember { StreakReminderManager(context) }
    var isNotificationsEnabled by remember { mutableStateOf(streakReminderManager.isReminderEnabled) }
    val scope = androidx.compose.runtime.rememberCoroutineScope()
    val db = remember { KnowverseDatabase.getInstance(context) }
    val trailCacheRepo = remember { TrailCacheRepository(db.trailCacheDao(), context) }
    val cachedLessonCount by trailCacheRepo.cachedLessonCount.collectAsState(initial = 0)
    var isSyncingCache by remember { mutableStateOf(false) }

    val notificationPermissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            streakReminderManager.isReminderEnabled = true
            isNotificationsEnabled = true
            streakReminderManager.showStreakNotification(
                streakDays = userProgress.currentStreak,
                userName = userName,
                hasStudiedToday = userProgress.hasStudiedToday()
            )
            actionFeedbackMessage = "Lembrete diário ativado e notificação de teste enviada!"
        } else {
            actionErrorMessage = "Permissão de notificações não concedida nas configurações do sistema."
        }
    }

    // Photo picker launcher (Android Photo Picker - Zero Permission)
    val photoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { uri ->
        if (uri != null) {
            try {
                val inputStream = context.contentResolver.openInputStream(uri)
                val rawBitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                if (rawBitmap != null) {
                    val userUid = currentUser?.uid ?: "user"
                    // Scale down to max 160x160 px for optimal quality while keeping data size small (~4-7KB)
                    val maxDim = maxOf(rawBitmap.width, rawBitmap.height)
                    val scale = if (maxDim > 160) 160f / maxDim else 1f
                    val scaledWidth = maxOf(1, (rawBitmap.width * scale).toInt())
                    val scaledHeight = maxOf(1, (rawBitmap.height * scale).toInt())
                    val scaledBitmap = Bitmap.createScaledBitmap(rawBitmap, scaledWidth, scaledHeight, true)

                    val baos = ByteArrayOutputStream()
                    scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, baos)
                    val jpegBytes = baos.toByteArray()
                    val base64DataUrl = "data:image/jpeg;base64," + Base64.encodeToString(jpegBytes, Base64.NO_WRAP)

                    // Also save local cache file
                    context.filesDir.listFiles { file -> file.name.startsWith("avatar_${userUid}") }?.forEach { oldFile ->
                        try { oldFile.delete() } catch (_: Exception) {}
                    }
                    val newFileName = "avatar_${userUid}_${System.currentTimeMillis()}.jpg"
                    val targetFile = File(context.filesDir, newFileName)
                    val outputStream = FileOutputStream(targetFile)
                    outputStream.write(jpegBytes)
                    outputStream.close()

                    selectedAvatarKey = base64DataUrl
                    authManager.updateProfile(
                        displayName = editName.ifBlank { currentUser?.displayName ?: "Estudante" },
                        photoUrl = base64DataUrl,
                        bio = editBio,
                        onSuccess = { updatedUser ->
                            progressRepository?.syncUserAccount(
                                userId = updatedUser.uid,
                                email = updatedUser.email,
                                displayName = updatedUser.displayName,
                                photoUrl = base64DataUrl
                            )
                            progressRepository?.updateUserProfile(
                                displayName = updatedUser.displayName,
                                photoUrl = base64DataUrl
                            )
                            actionFeedbackMessage = "Foto de perfil atualizada e visível no ranking global!"
                        },
                        onError = { actionErrorMessage = it }
                    )
                } else {
                    actionErrorMessage = "Não foi possível carregar a imagem selecionada."
                }
            } catch (e: Exception) {
                actionErrorMessage = "Erro ao processar imagem: ${e.message}"
            }
        }
    }

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(DeepNavy)
            .statusBarsPadding()
            .verticalScroll(scrollState)
    ) {
        TopUserStatsBar(
            userProgress = userProgress,
            userPhotoUrl = currentUser?.photoUrl
        )

        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            // Success or Error Feedback
            if (actionFeedbackMessage != null) {
                Surface(
                    color = SuccessEmerald.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, SuccessEmerald.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = actionFeedbackMessage ?: "",
                        style = MaterialTheme.typography.labelSmall.copy(color = SuccessEmerald),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            if (actionErrorMessage != null) {
                Surface(
                    color = ErrorRose.copy(alpha = 0.15f),
                    shape = RoundedCornerShape(6.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, ErrorRose.copy(alpha = 0.4f)),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = actionErrorMessage ?: "",
                        style = MaterialTheme.typography.labelSmall.copy(color = ErrorRose),
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }

            // User Profile Card
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Avatar (Supports uploaded photo, male coder, female coder, or sea lion mascot)
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(SurfaceElevated)
                                .border(1.5.dp, PrimaryViolet.copy(alpha = 0.6f), CircleShape)
                                .clickable {
                                    editName = currentUser?.displayName ?: ""
                                    editBio = currentUser?.bio ?: ""
                                    selectedAvatarKey = currentUser?.photoUrl ?: "mascot_otter_boy"
                                    showEditProfileDialog = true
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            UserAvatarDisplay(
                                photoKeyOrUrl = currentUser?.photoUrl,
                                sizeDp = 50
                            )
                        }

                        Spacer(modifier = Modifier.width(12.dp))

                        Column(modifier = Modifier.weight(1f)) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text(
                                    text = userName,
                                    style = MaterialTheme.typography.titleMedium.copy(
                                        fontWeight = FontWeight.SemiBold,
                                        color = TextPrimary
                                    )
                                )
                                if (isDevAccount) {
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = PrimaryViolet.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "Dev Admin",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = PrimaryViolet,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 9.sp
                                            ),
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                                        )
                                    }
                                }
                            }
                            Text(
                                text = userEmail,
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                            )
                        }

                        IconButton(
                            onClick = {
                                editName = currentUser?.displayName ?: ""
                                editBio = currentUser?.bio ?: ""
                                selectedAvatarKey = currentUser?.photoUrl ?: "mascot_otter_boy"
                                showEditProfileDialog = true
                            },
                            modifier = Modifier.size(34.dp).testTag("edit_profile_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = "Editar Perfil",
                                tint = PrimaryViolet,
                                modifier = Modifier.size(18.dp)
                            )
                        }
                    }

                    if (!currentUser?.bio.isNullOrBlank()) {
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = currentUser?.bio ?: "",
                            style = MaterialTheme.typography.bodySmall.copy(
                                color = TextTertiary,
                                fontSize = 11.sp
                            )
                        )
                    }
                }
            }

            // Account Security & Management Section
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
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Security,
                            contentDescription = null,
                            tint = PrimaryViolet,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Segurança da Conta",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.SemiBold,
                                color = TextPrimary
                            )
                        )
                    }

                    // Change Password Button
                    OutlinedButton(
                        onClick = {
                            newPassword = ""
                            confirmPassword = ""
                            actionErrorMessage = null
                            showChangePasswordDialog = true
                        },
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth().height(38.dp).testTag("change_password_btn")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Lock, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Alterar Senha", style = MaterialTheme.typography.labelSmall)
                        }
                    }

                    // Change Email Button (with 60-day rate limit)
                    OutlinedButton(
                        onClick = {
                            newEmailText = ""
                            actionErrorMessage = null
                            showChangeEmailDialog = true
                        },
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth().height(38.dp).testTag("change_email_btn")
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.Email, contentDescription = null, modifier = Modifier.size(14.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Alterar E-mail (Limite 1x a cada 2 meses)", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }

            // Dev Admin Access Banner (Only for authenticated Pedro)
            if (isDevAccount) {
                Card(
                    shape = RoundedCornerShape(8.dp),
                    colors = CardDefaults.cardColors(containerColor = SurfaceCard),
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, PrimaryViolet.copy(alpha = 0.4f), RoundedCornerShape(8.dp))
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(
                                text = "Painel do Desenvolvedor",
                                style = MaterialTheme.typography.titleSmall.copy(
                                    fontWeight = FontWeight.SemiBold,
                                    color = TextPrimary
                                )
                            )
                            Spacer(modifier = Modifier.height(2.dp))
                            Text(
                                text = "Acesso exclusivo dev: monitore o progresso dos alunos via Firestore.",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                            )
                        }

                        Button(
                            onClick = onOpenDevDashboard,
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.testTag("open_dev_dashboard_btn")
                        ) {
                            Icon(
                                imageVector = Icons.Default.AdminPanelSettings,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("Abrir Painel", style = MaterialTheme.typography.labelSmall)
                        }
                    }
                }
            }

            // ========================================================
            // EMBLEMAS E CONQUISTAS (OCULTÁVEL E COMPACTO)
            // ========================================================
            var areBadgesExpanded by remember { mutableStateOf(false) }
            var filterOnlyUnlocked by remember { mutableStateOf(false) }

            val allBadges = TrackBadgesCatalog.ALL_BADGES
            val earnedCount = allBadges.count { userProgress.unlockedBadges.contains(it.id) }
            val displayBadges = if (filterOnlyUnlocked) allBadges.filter { userProgress.unlockedBadges.contains(it.id) } else allBadges

            Card(
                shape = RoundedCornerShape(12.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, BorderSubtle, RoundedCornerShape(12.dp))
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    // Header com Botão de Recolher/Expandir
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { areBadgesExpanded = !areBadgesExpanded }
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(32.dp)
                                    .clip(RoundedCornerShape(8.dp))
                                    .background(GoldXp.copy(alpha = 0.16f))
                                    .border(1.dp, GoldXp.copy(alpha = 0.4f), RoundedCornerShape(8.dp)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.EmojiEvents,
                                    contentDescription = null,
                                    tint = GoldXp,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Conquistas e Emblemas",
                                        style = MaterialTheme.typography.titleSmall.copy(
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
                                            text = "$earnedCount/${allBadges.size}",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = PrimaryViolet,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 10.sp
                                            ),
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = if (areBadgesExpanded) "Toque para ocultar lista de conquistas" else "$earnedCount de ${allBadges.size} desbloqueadas • Toque para expandir",
                                    style = MaterialTheme.typography.bodySmall.copy(
                                        color = TextSecondary,
                                        fontSize = 11.sp
                                    )
                                )
                            }
                        }

                        IconButton(
                            onClick = { areBadgesExpanded = !areBadgesExpanded },
                            modifier = Modifier.size(32.dp)
                        ) {
                            Icon(
                                imageVector = if (areBadgesExpanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                                contentDescription = if (areBadgesExpanded) "Recolher" else "Expandir",
                                tint = GoldXp
                            )
                        }
                    }

                    // Seção Expandida (Totalmente Ocultável - Ocupa espaço zero quando recolhida)
                    AnimatedVisibility(
                        visible = areBadgesExpanded,
                        enter = expandVertically() + fadeIn(),
                        exit = shrinkVertically() + fadeOut()
                    ) {
                        Column(
                            modifier = Modifier.padding(top = 10.dp),
                            verticalArrangement = Arrangement.spacedBy(10.dp)
                        ) {
                            // Linha de Mini-Emblemas de acesso rápido
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .horizontalScroll(rememberScrollState()),
                                horizontalArrangement = Arrangement.spacedBy(8.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                allBadges.forEach { badge ->
                                    val isUnlocked = userProgress.unlockedBadges.contains(badge.id)
                                    Box(
                                        modifier = Modifier
                                            .size(38.dp)
                                            .clip(RoundedCornerShape(8.dp))
                                            .background(if (isUnlocked) SurfaceElevated else DeepNavy.copy(alpha = 0.5f))
                                            .border(
                                                1.dp,
                                                if (isUnlocked) GoldXp.copy(alpha = 0.8f) else BorderSubtle.copy(alpha = 0.5f),
                                                RoundedCornerShape(8.dp)
                                            ),
                                        contentAlignment = Alignment.Center
                                    ) {
                                        Icon(
                                            painter = painterResource(id = if (isUnlocked) badge.drawableRes else R.drawable.ic_badge_locked),
                                            contentDescription = badge.title,
                                            tint = Color.Unspecified,
                                            modifier = Modifier.size(28.dp)
                                        )
                                    }
                                }
                            }

                            // Filtro rápido
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Módulos Conquistados",
                                    style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontSize = 11.sp)
                                )
                                Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
                                    FilterChip(
                                        selected = !filterOnlyUnlocked,
                                        onClick = { filterOnlyUnlocked = false },
                                        label = { Text("Todos (${allBadges.size})", fontSize = 10.sp) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = PrimaryViolet.copy(alpha = 0.3f),
                                            containerColor = SurfaceElevated
                                        )
                                    )
                                    FilterChip(
                                        selected = filterOnlyUnlocked,
                                        onClick = { filterOnlyUnlocked = true },
                                        label = { Text("Conquistados ($earnedCount)", fontSize = 10.sp) },
                                        colors = FilterChipDefaults.filterChipColors(
                                            selectedContainerColor = GoldXp.copy(alpha = 0.3f),
                                            containerColor = SurfaceElevated
                                        )
                                    )
                                }
                            }

                            // Lista de Emblemas Detalhada
                            displayBadges.forEach { badge ->
                                val isUnlocked = userProgress.unlockedBadges.contains(badge.id)
                                Surface(
                                    color = if (isUnlocked) SurfaceElevated else DeepNavy.copy(alpha = 0.4f),
                                    shape = RoundedCornerShape(8.dp),
                                    border = androidx.compose.foundation.BorderStroke(
                                        1.dp,
                                        if (isUnlocked) GoldXp.copy(alpha = 0.3f) else BorderSubtle
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Row(
                                        modifier = Modifier.padding(10.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(42.dp)
                                                .clip(RoundedCornerShape(8.dp))
                                                .background(if (isUnlocked) SurfaceElevated else DeepNavy)
                                                .border(
                                                    1.dp,
                                                    if (isUnlocked) GoldXp.copy(alpha = 0.6f) else BorderSubtle,
                                                    RoundedCornerShape(8.dp)
                                                ),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(
                                                painter = painterResource(id = if (isUnlocked) badge.drawableRes else R.drawable.ic_badge_locked),
                                                contentDescription = badge.title,
                                                tint = Color.Unspecified,
                                                modifier = Modifier.size(32.dp)
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(10.dp))

                                        Column(modifier = Modifier.weight(1f)) {
                                            Row(verticalAlignment = Alignment.CenterVertically) {
                                                Text(
                                                    text = badge.title,
                                                    style = MaterialTheme.typography.titleSmall.copy(
                                                        fontWeight = FontWeight.SemiBold,
                                                        color = if (isUnlocked) TextPrimary else TextTertiary,
                                                        fontSize = 13.sp
                                                    )
                                                )
                                                Spacer(modifier = Modifier.width(6.dp))
                                                Surface(
                                                    color = if (isUnlocked) SuccessEmerald.copy(alpha = 0.2f) else SurfaceElevated,
                                                    shape = RoundedCornerShape(4.dp)
                                                ) {
                                                    Text(
                                                        text = if (isUnlocked) "+${badge.xpBonus} XP" else "${badge.xpBonus} XP",
                                                        style = MaterialTheme.typography.labelSmall.copy(
                                                            color = if (isUnlocked) SuccessEmerald else TextTertiary,
                                                            fontWeight = FontWeight.Bold,
                                                            fontSize = 10.sp
                                                        ),
                                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 1.dp)
                                                    )
                                                }
                                            }
                                            Text(
                                                text = badge.moduleTitle,
                                                style = MaterialTheme.typography.labelSmall.copy(
                                                    color = if (isUnlocked) GoldXp else TextTertiary,
                                                    fontSize = 10.sp
                                                )
                                            )
                                            Text(
                                                text = badge.description,
                                                style = MaterialTheme.typography.bodySmall.copy(
                                                    color = if (isUnlocked) TextSecondary else TextTertiary,
                                                    fontSize = 11.sp,
                                                    lineHeight = 15.sp
                                                )
                                            )
                                        }
                                    }
                                }
                            }

                            // Botão para Ocultar / Recolher
                            TextButton(
                                onClick = { areBadgesExpanded = false },
                                modifier = Modifier.align(Alignment.CenterHorizontally)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.KeyboardArrowUp,
                                    contentDescription = null,
                                    tint = PrimaryViolet,
                                    modifier = Modifier.size(16.dp)
                                )
                                Spacer(modifier = Modifier.width(4.dp))
                                Text("Ocultar Detalhes", color = PrimaryViolet, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }

            // Performance Stats
            Text(
                text = "Desempenho Geral",
                style = MaterialTheme.typography.titleSmall.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = TextPrimary
                )
            )

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
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Dias Ativos de Estudo:", color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                        Text("${userProgress.activeStudyDaysCount} dias", color = GoldXp, fontWeight = FontWeight.SemiBold)
                    }
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderSubtle))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Sequência de Foco:", color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = KnowverseIcons.StreakFire,
                                contentDescription = null,
                                tint = StreakFire,
                                modifier = Modifier.size(14.dp)
                            )
                            Spacer(modifier = Modifier.width(4.dp))
                            Text("${userProgress.currentStreak} dias", color = StreakFire, fontWeight = FontWeight.SemiBold)
                        }
                    }
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderSubtle))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Nível do Aluno:", color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                        Text("Nível ${userProgress.level}", color = PrimaryViolet, fontWeight = FontWeight.SemiBold)
                    }
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderSubtle))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Total de XP Acumulado:", color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                        Text("${userProgress.totalXp} XP", color = GoldXp, fontWeight = FontWeight.SemiBold)
                    }
                    Box(modifier = Modifier.fillMaxWidth().height(1.dp).background(BorderSubtle))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text("Lições Concluídas:", color = TextSecondary, style = MaterialTheme.typography.bodySmall)
                        Text("${userProgress.completedLessonIds.size} aulas", color = SuccessEmerald, fontWeight = FontWeight.SemiBold)
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ========================================================
            // NOTIFICAÇÕES PUSH: LEMBRETES DIÁRIOS & STREAK
            // ========================================================
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, PrimaryViolet.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                    .testTag("push_notifications_card")
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.weight(1f)) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryViolet.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.NotificationsActive,
                                    contentDescription = null,
                                    tint = PrimaryViolet,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Text(
                                    text = "Lembretes Diários & Streak",
                                    style = MaterialTheme.typography.titleSmall.copy(
                                        fontWeight = FontWeight.Bold,
                                        color = TextPrimary
                                    )
                                )
                                Text(
                                    text = "Notificações push diárias para não perder a sequência",
                                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                                )
                            }
                        }

                        Switch(
                            checked = isNotificationsEnabled,
                            onCheckedChange = { enable ->
                                if (enable) {
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                        notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                    } else {
                                        streakReminderManager.isReminderEnabled = true
                                        isNotificationsEnabled = true
                                        actionFeedbackMessage = "Lembrete diário de estudos ativado!"
                                    }
                                } else {
                                    streakReminderManager.isReminderEnabled = false
                                    isNotificationsEnabled = false
                                    actionFeedbackMessage = "Lembretes diários desativados."
                                }
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = PrimaryViolet,
                                uncheckedThumbColor = TextTertiary,
                                uncheckedTrackColor = SurfaceElevated
                            )
                        )
                    }

                    Surface(
                        color = SurfaceElevated,
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 10.dp, vertical = 8.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Horário agendado:",
                                style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 12.sp)
                            )
                            Text(
                                text = "20:00 (todas as noites)",
                                style = MaterialTheme.typography.bodySmall.copy(color = GoldXp, fontWeight = FontWeight.Bold, fontSize = 12.sp)
                            )
                        }
                    }

                    // Test notification button
                    OutlinedButton(
                        onClick = {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                notificationPermissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                            } else {
                                streakReminderManager.showStreakNotification(
                                    streakDays = userProgress.currentStreak,
                                    userName = userName,
                                    hasStudiedToday = userProgress.hasStudiedToday()
                                )
                                actionFeedbackMessage = "Notificação de teste disparada com sucesso!"
                            }
                        },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = PrimaryViolet),
                        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.5f)),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(38.dp)
                            .testTag("test_notification_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.Notifications,
                            contentDescription = null,
                            tint = PrimaryViolet,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = "Testar Notificação Agora",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ========================================================
            // CACHE LOCAL USANDO ROOM (ESTUDOS 100% OFFLINE)
            // ========================================================
            Card(
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, SuccessEmerald.copy(alpha = 0.5f), RoundedCornerShape(10.dp))
                    .testTag("room_cache_card")
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(SuccessEmerald.copy(alpha = 0.15f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.CloudDone,
                                    contentDescription = null,
                                    tint = SuccessEmerald,
                                    modifier = Modifier.size(20.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Column {
                                Row(verticalAlignment = Alignment.CenterVertically) {
                                    Text(
                                        text = "Cache Local (Room SQLite)",
                                        style = MaterialTheme.typography.titleSmall.copy(
                                            fontWeight = FontWeight.Bold,
                                            color = TextPrimary
                                        )
                                    )
                                    Spacer(modifier = Modifier.width(6.dp))
                                    Surface(
                                        color = SuccessEmerald.copy(alpha = 0.2f),
                                        shape = RoundedCornerShape(4.dp)
                                    ) {
                                        Text(
                                            text = "$cachedLessonCount lições",
                                            style = MaterialTheme.typography.labelSmall.copy(
                                                color = SuccessEmerald,
                                                fontWeight = FontWeight.Bold,
                                                fontSize = 9.sp
                                            ),
                                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                        )
                                    }
                                }
                                Text(
                                    text = "Trilhas e aulas salvas para visualização offline",
                                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                                )
                            }
                        }
                    }

                    Text(
                        text = "Permite abrir módulos de Python, Matemática Básica e Cálculo mesmo sem rede. O progresso é sincronizado automaticamente assim que a conexão retornar.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            fontSize = 11.sp,
                            lineHeight = 16.sp
                        )
                    )

                    OutlinedButton(
                        onClick = {
                            isSyncingCache = true
                            actionFeedbackMessage = null
                            actionErrorMessage = null
                            scope.launch {
                                try {
                                    val count = trailCacheRepo.forceRefreshCache()
                                    isSyncingCache = false
                                    actionFeedbackMessage = "Cache Room sincronizado com sucesso ($count aulas salvas)!"
                                } catch (e: Exception) {
                                    isSyncingCache = false
                                    actionErrorMessage = "Falha ao sincronizar cache: ${e.message}"
                                }
                            }
                        },
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = SuccessEmerald),
                        border = androidx.compose.foundation.BorderStroke(1.dp, SuccessEmerald.copy(alpha = 0.5f)),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(38.dp)
                            .testTag("refresh_cache_btn"),
                        enabled = !isSyncingCache
                    ) {
                        Icon(
                            imageVector = Icons.Default.Refresh,
                            contentDescription = null,
                            tint = SuccessEmerald,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(6.dp))
                        Text(
                            text = if (isSyncingCache) "Sincronizando Banco de Dados..." else "Atualizar / Sincronizar Cache",
                            style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.SemiBold)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // ========================================================
            // COMPARTILHAR O KNOWVERSE
            // ========================================================
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                modifier = Modifier
                    .fillMaxWidth()
                    .border(1.dp, PrimaryViolet.copy(alpha = 0.5f), RoundedCornerShape(8.dp))
            ) {
                Column(
                    modifier = Modifier.padding(14.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = null,
                            tint = PrimaryViolet,
                            modifier = Modifier.size(18.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = "Compartilhar o Knowverse",
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                    }

                    Text(
                        text = "Convide amigos ou compartilhe seu aplicativo diretamente via WhatsApp, redes sociais ou copiando o link de acesso:",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            lineHeight = 18.sp
                        )
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Button(
                            onClick = { ShareUtil.shareApp(context) },
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .weight(1.3f)
                                .height(42.dp)
                                .testTag("share_app_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.Share,
                                contentDescription = null,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Compartilhar", style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold))
                        }

                        OutlinedButton(
                            onClick = { ShareUtil.copyShareLink(context) },
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = GoldXp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.5f)),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier
                                .weight(1f)
                                .height(42.dp)
                                .testTag("copy_app_link_button")
                        ) {
                            Icon(
                                imageVector = Icons.Default.ContentCopy,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(16.dp)
                            )
                            Spacer(modifier = Modifier.width(6.dp))
                            Text("Copiar Link", style = MaterialTheme.typography.labelMedium.copy(color = GoldXp))
                        }
                    }
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Sign out button
            OutlinedButton(
                onClick = { showSignOutDialog = true },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = TextSecondary),
                shape = RoundedCornerShape(6.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .testTag("sign_out_button")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.ExitToApp,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = TextSecondary
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Sair da Conta", style = MaterialTheme.typography.labelMedium.copy(color = TextSecondary))
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Delete Account button
            OutlinedButton(
                onClick = { showDeleteAccountDialog = true },
                colors = ButtonDefaults.outlinedButtonColors(contentColor = ErrorRose),
                shape = RoundedCornerShape(6.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, ErrorRose.copy(alpha = 0.6f)),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(42.dp)
                    .testTag("delete_account_button")
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = null,
                        modifier = Modifier.size(16.dp),
                        tint = ErrorRose
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = "Excluir Conta Permanentemente",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = ErrorRose,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            }

            Spacer(modifier = Modifier.height(14.dp))

            // ========================================================
            // INFORMAÇÕES DA VERSÃO DO APP & BANCO DE DADOS
            // ========================================================
            Surface(
                color = SurfaceElevated.copy(alpha = 0.5f),
                shape = RoundedCornerShape(8.dp),
                border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .testTag("app_version_footer_card")
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 14.dp, vertical = 10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(
                            text = "Knowverse App",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                        Text(
                            text = "Base ENEM: 273 Questões • 14 Módulos Ativos",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = TextSecondary,
                                fontSize = 10.5.sp
                            )
                        )
                    }

                    Surface(
                        color = PrimaryViolet.copy(alpha = 0.15f),
                        shape = RoundedCornerShape(4.dp)
                    ) {
                        Text(
                            text = "v1.6.0",
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = PrimaryViolet,
                                fontWeight = FontWeight.Bold,
                                fontSize = 11.sp
                            ),
                            modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }

    // ========================================================
    // DIALOG: EDITAR INFORMAÇÕES DO PERFIL (FOTO, NOME, BIO)
    // ========================================================
    if (showEditProfileDialog) {
        AlertDialog(
            onDismissRequest = { showEditProfileDialog = false },
            containerColor = ObsidianSurface,
            title = {
                Text(
                    text = "Editar Informações do Perfil",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
                    Text(
                        text = "Foto de Perfil:",
                        style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary, fontWeight = FontWeight.Bold)
                    )

                    // Button to upload custom photo from device
                    Button(
                        onClick = {
                            photoPickerLauncher.launch(
                                PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly)
                            )
                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = PrimaryViolet,
                            contentColor = Color.White
                        ),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth().height(38.dp).testTag("upload_photo_btn")
                    ) {
                        Icon(
                            imageVector = Icons.Default.CloudUpload,
                            contentDescription = null,
                            modifier = Modifier.size(16.dp)
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Carregar Foto do Dispositivo", fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
                    }

                    Text(
                        text = "Ou escolha um avatar predefinido:",
                        style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary, fontSize = 11.sp)
                    )

                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Preset 1: Otter Boy Mascot (Default)
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .border(
                                    2.dp,
                                    if (selectedAvatarKey == "mascot_otter_boy" || selectedAvatarKey == "mascot_sealion" || selectedAvatarKey.isBlank()) PrimaryViolet else BorderSubtle,
                                    CircleShape
                                )
                                .clickable { selectedAvatarKey = "mascot_otter_boy" },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_mascot_otter_boy),
                                contentDescription = "Mascote Lontra Estudante",
                                modifier = Modifier.size(46.dp).clip(CircleShape)
                            )
                        }

                        // Preset 2: Otter Girl Mascot (Girls option requested by user)
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .border(
                                    2.dp,
                                    if (selectedAvatarKey == "mascot_otter_girl" || selectedAvatarKey == "avatar_girl") PrimaryViolet else BorderSubtle,
                                    CircleShape
                                )
                                .clickable { selectedAvatarKey = "mascot_otter_girl" },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.img_mascot_otter_girl),
                                contentDescription = "Mascote Lontra Menina",
                                modifier = Modifier.size(46.dp).clip(CircleShape)
                            )
                        }

                        // Preset 3: Male Coder Avatar
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .border(
                                    2.dp,
                                    if (selectedAvatarKey == "avatar_male") PrimaryViolet else BorderSubtle,
                                    CircleShape
                                )
                                .clickable { selectedAvatarKey = "avatar_male" },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_avatar_coder_male),
                                contentDescription = "Programador",
                                modifier = Modifier.size(46.dp).clip(CircleShape)
                            )
                        }

                        // Preset 4: Female Coder Avatar
                        Box(
                            modifier = Modifier
                                .size(50.dp)
                                .clip(CircleShape)
                                .border(
                                    2.dp,
                                    if (selectedAvatarKey == "avatar_female") PrimaryViolet else BorderSubtle,
                                    CircleShape
                                )
                                .clickable { selectedAvatarKey = "avatar_female" },
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.ic_avatar_coder_female),
                                contentDescription = "Programadora",
                                modifier = Modifier.size(46.dp).clip(CircleShape)
                            )
                        }
                    }

                    OutlinedTextField(
                        value = editName,
                        onValueChange = { editName = it },
                        label = { Text("Nome de exibição") },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryViolet,
                            unfocusedBorderColor = BorderSubtle,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = editBio,
                        onValueChange = { editBio = it },
                        label = { Text("Bio / Sobre você") },
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryViolet,
                            unfocusedBorderColor = BorderSubtle,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        actionErrorMessage = null
                        authManager.updateProfile(
                            displayName = editName,
                            photoUrl = selectedAvatarKey,
                            bio = editBio,
                            onSuccess = { updatedUser ->
                                progressRepository?.syncUserAccount(
                                    userId = updatedUser.uid,
                                    email = updatedUser.email,
                                    displayName = updatedUser.displayName,
                                    photoUrl = updatedUser.photoUrl
                                )
                                progressRepository?.updateUserProfile(
                                    displayName = updatedUser.displayName,
                                    photoUrl = updatedUser.photoUrl
                                )
                                editName = updatedUser.displayName
                                actionFeedbackMessage = "Perfil atualizado e sincronizado na nuvem!"
                                showEditProfileDialog = false
                            },
                            onError = { actionErrorMessage = it }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet)
                ) {
                    Text("Salvar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showEditProfileDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }

    // ========================================================
    // DIALOG: ALTERAR SENHA
    // ========================================================
    if (showChangePasswordDialog) {
        AlertDialog(
            onDismissRequest = { showChangePasswordDialog = false },
            containerColor = ObsidianSurface,
            title = {
                Text(
                    text = "Alterar Senha",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    Text(
                        text = "Digite sua nova senha abaixo (mínimo de 6 caracteres):",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                    )

                    OutlinedTextField(
                        value = newPassword,
                        onValueChange = { newPassword = it },
                        label = { Text("Nova senha") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryViolet,
                            unfocusedBorderColor = BorderSubtle,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )

                    OutlinedTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = { Text("Confirmar nova senha") },
                        singleLine = true,
                        visualTransformation = PasswordVisualTransformation(),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = PrimaryViolet,
                            unfocusedBorderColor = BorderSubtle,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        actionErrorMessage = null
                        if (newPassword != confirmPassword) {
                            actionErrorMessage = "As senhas não coincidem."
                            return@Button
                        }
                        authManager.updatePassword(
                            newPass = newPassword,
                            onSuccess = {
                                actionFeedbackMessage = "Senha alterada com sucesso!"
                                showChangePasswordDialog = false
                            },
                            onError = { actionErrorMessage = it }
                        )
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet)
                ) {
                    Text("Atualizar Senha")
                }
            },
            dismissButton = {
                TextButton(onClick = { showChangePasswordDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }

    // ========================================================
    // DIALOG: ALTERAR E-MAIL (LIMITE 1X A CADA 2 MESES)
    // ========================================================
    if (showChangeEmailDialog) {
        val (canChangeEmail, daysUntilNextChange) = currentUser?.let {
            authManager.checkEmailChangeEligibility(it)
        } ?: Pair(true, 0L)

        AlertDialog(
            onDismissRequest = { showChangeEmailDialog = false },
            containerColor = ObsidianSurface,
            title = {
                Text(
                    text = "Alterar E-mail",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                )
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
                    if (!canChangeEmail) {
                        Surface(
                            color = ErrorRose.copy(alpha = 0.15f),
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, ErrorRose.copy(alpha = 0.4f)),
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Text(
                                text = "Você só pode alterar seu e-mail uma vez a cada 2 meses (60 dias). Aguarde mais $daysUntilNextChange dias para poder alterar novamente.",
                                style = MaterialTheme.typography.bodySmall.copy(color = ErrorRose),
                                modifier = Modifier.padding(10.dp)
                            )
                        }
                    } else {
                        Text(
                            text = "Atenção: A alteração de e-mail é limitada a uma vez a cada 2 meses.",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                        )

                        OutlinedTextField(
                            value = newEmailText,
                            onValueChange = { newEmailText = it },
                            label = { Text("Novo endereço de e-mail") },
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = PrimaryViolet,
                                unfocusedBorderColor = BorderSubtle,
                                focusedTextColor = TextPrimary,
                                unfocusedTextColor = TextPrimary
                            ),
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            },
            confirmButton = {
                if (canChangeEmail) {
                    Button(
                        onClick = {
                            actionErrorMessage = null
                            authManager.updateEmail(
                                newEmail = newEmailText,
                                onSuccess = {
                                    actionFeedbackMessage = "E-mail alterado com sucesso para $newEmailText!"
                                    showChangeEmailDialog = false
                                },
                                onError = { actionErrorMessage = it }
                            )
                        },
                        colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet)
                    ) {
                        Text("Confirmar Novo E-mail")
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = { showChangeEmailDialog = false }) {
                    Text("Fechar", color = TextSecondary)
                }
            }
        )
    }

    // Sign Out Dialog
    if (showSignOutDialog) {
        AlertDialog(
            onDismissRequest = { showSignOutDialog = false },
            containerColor = ObsidianSurface,
            title = {
                Text(
                    text = "Encerrar Sessão",
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.SemiBold,
                        color = TextPrimary
                    )
                )
            },
            text = {
                Text(
                    text = "Deseja realmente sair da sua conta? Todo o seu progresso permanece salvo e criptografado.",
                    style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary)
                )
            },
            confirmButton = {
                Button(
                    onClick = {
                        showSignOutDialog = false
                        authManager.signOut()
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet)
                ) {
                    Text("Sair")
                }
            },
            dismissButton = {
                TextButton(onClick = { showSignOutDialog = false }) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }

    // Delete Account Dialog
    if (showDeleteAccountDialog) {
        AlertDialog(
            onDismissRequest = { if (!isDeletingAccount) showDeleteAccountDialog = false },
            containerColor = ObsidianSurface,
            title = {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Warning,
                        contentDescription = null,
                        tint = ErrorRose,
                        modifier = Modifier.size(22.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "Excluir Conta Permanentemente?",
                        style = MaterialTheme.typography.titleMedium.copy(
                            fontWeight = FontWeight.Bold,
                            color = ErrorRose
                        )
                    )
                }
            },
            text = {
                Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Atenção: Esta ação é definitiva e não poderá ser desfeita!",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = "Ao confirmar, todo o seu progresso de aprendizado, histórico de exercícios, XP acumulado, lições concluídas, medalhas e conquistas serão permanentemente excluídos do dispositivo e da nuvem.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = TextSecondary,
                            lineHeight = 18.sp
                        )
                    )
                    Text(
                        text = "Deseja realmente excluir sua conta e perder todos os seus dados?",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = ErrorRose.copy(alpha = 0.9f),
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        isDeletingAccount = true
                        authManager.deleteAccount(
                            progressRepository = progressRepository,
                            onSuccess = {
                                isDeletingAccount = false
                                showDeleteAccountDialog = false
                            },
                            onError = { err ->
                                isDeletingAccount = false
                                showDeleteAccountDialog = false
                                actionErrorMessage = err
                            }
                        )
                    },
                    enabled = !isDeletingAccount,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = ErrorRose,
                        contentColor = Color.White
                    ),
                    modifier = Modifier.testTag("confirm_delete_account_btn")
                ) {
                    Text(if (isDeletingAccount) "Excluindo..." else "Sim, Excluir Minha Conta")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDeleteAccountDialog = false },
                    enabled = !isDeletingAccount
                ) {
                    Text("Cancelar", color = TextSecondary)
                }
            }
        )
    }
}
