package com.example.ui.screens

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Campaign
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.OpenInBrowser
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.SystemUpdate
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.auth.AppUser
import com.example.notification.BroadcastNotification
import com.example.notification.BroadcastNotificationRepository
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.StreakFire
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun DevBroadcastTab(
    broadcastRepository: BroadcastNotificationRepository,
    currentUser: AppUser?,
    currentDevEmail: String
) {
    val context = LocalContext.current
    val notifications by broadcastRepository.notifications.collectAsState()
    val isDownloading by broadcastRepository.isDownloading.collectAsState()
    val downloadProgress by broadcastRepository.downloadProgress.collectAsState()

    var broadcastTitle by remember { mutableStateOf("Hora de estudar! Nova atualização!!") }
    var broadcastMessage by remember {
        mutableStateOf("Nova versão do Knowverse disponível! Baixe agora diretamente pelo app para ter as últimas lições e correções.")
    }
    var broadcastType by remember { mutableStateOf(BroadcastNotification.TYPE_APP_UPDATE) }
    var apkVersion by remember { mutableStateOf("1.8.0") }
    var apkDownloadUrl by remember { mutableStateOf(BroadcastNotificationRepository.DEFAULT_APK_DOWNLOAD_URL) }

    var isSending by remember { mutableStateOf(false) }
    var sendStatusMessage by remember { mutableStateOf<String?>(null) }
    var testDownloadStatus by remember { mutableStateOf<String?>(null) }

    val effectiveUser = remember(currentUser, currentDevEmail) {
        currentUser ?: AppUser(
            uid = "dev_admin_pedro",
            email = currentDevEmail,
            displayName = "Pedro (Dev Admin)",
            isDev = true
        )
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().testTag("dev_broadcast_tab"),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 12.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ) {
        // Dev Access Notice
        item {
            Surface(
                color = PrimaryViolet.copy(alpha = 0.1f),
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.35f)),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier.padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Campaign,
                        contentDescription = null,
                        tint = PrimaryViolet,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Column {
                        Text(
                            text = "Central de Transmissão Oficial (Apenas Desenvolvedor)",
                            style = MaterialTheme.typography.labelMedium.copy(
                                fontWeight = FontWeight.Bold,
                                color = TextPrimary
                            )
                        )
                        Text(
                            text = "Dispare notificações push em tempo real e disponibilize o arquivo .apk para atualização direta.",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                        )
                    }
                }
            }
        }

        // Form Card: Disparar Nova Notificação
        item {
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text(
                        text = "Nova Notificação / Atualização",
                        style = MaterialTheme.typography.titleSmall.copy(
                            fontWeight = FontWeight.SemiBold,
                            color = TextPrimary
                        )
                    )
                    Spacer(modifier = Modifier.height(10.dp))

                    // Quick Template Chips
                    Text(
                        text = "Modelos Rápidos:",
                        style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        Surface(
                            color = SurfaceElevated,
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.clickable {
                                broadcastTitle = "Hora de estudar! Nova atualização!!"
                                broadcastMessage = "Nova versão disponível do Knowverse! Clique para atualizar agora e conferir as novidades."
                                broadcastType = BroadcastNotification.TYPE_APP_UPDATE
                            }
                        ) {
                            Text(
                                text = "Hora de estudar! Nova atualização!!",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = TextPrimary),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }

                        Surface(
                            color = SurfaceElevated,
                            shape = RoundedCornerShape(4.dp),
                            border = BorderStroke(1.dp, BorderSubtle),
                            modifier = Modifier.clickable {
                                broadcastTitle = "🔥 Não perca sua sequência hoje!"
                                broadcastMessage = "Falta pouco para meia-noite! Faça uma aula ou resolva um exercício para manter seu streak ativo."
                                broadcastType = BroadcastNotification.TYPE_STUDY_REMINDER
                            }
                        ) {
                            Text(
                                text = "Lembrete de Streak",
                                style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = StreakFire),
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(14.dp))

                    // Title Input
                    Text(
                        text = "Título da Notificação",
                        style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = broadcastTitle,
                        onValueChange = { broadcastTitle = it },
                        singleLine = true,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SurfaceElevated,
                            unfocusedContainerColor = SurfaceElevated,
                            focusedBorderColor = PrimaryViolet,
                            unfocusedBorderColor = BorderSubtle,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth().testTag("dev_broadcast_title_input")
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    // Message Input
                    Text(
                        text = "Mensagem / Detalhes",
                        style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    OutlinedTextField(
                        value = broadcastMessage,
                        onValueChange = { broadcastMessage = it },
                        maxLines = 3,
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedContainerColor = SurfaceElevated,
                            unfocusedContainerColor = SurfaceElevated,
                            focusedBorderColor = PrimaryViolet,
                            unfocusedBorderColor = BorderSubtle,
                            focusedTextColor = TextPrimary,
                            unfocusedTextColor = TextPrimary
                        ),
                        shape = RoundedCornerShape(6.dp),
                        modifier = Modifier.fillMaxWidth().testTag("dev_broadcast_message_input")
                    )

                    Spacer(modifier = Modifier.height(12.dp))

                    // Type Selector
                    Text(
                        text = "Tipo de Envio:",
                        style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                    )
                    Spacer(modifier = Modifier.height(6.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        FilterChip(
                            selected = broadcastType == BroadcastNotification.TYPE_APP_UPDATE,
                            onClick = { broadcastType = BroadcastNotification.TYPE_APP_UPDATE },
                            label = { Text("Atualização de App (.APK)", style = MaterialTheme.typography.labelSmall) },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.SystemUpdate,
                                    contentDescription = null,
                                    modifier = Modifier.size(14.dp)
                                )
                            },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = PrimaryViolet.copy(alpha = 0.25f),
                                selectedLabelColor = PrimaryViolet
                            )
                        )

                        FilterChip(
                            selected = broadcastType == BroadcastNotification.TYPE_STUDY_REMINDER,
                            onClick = { broadcastType = BroadcastNotification.TYPE_STUDY_REMINDER },
                            label = { Text("Aviso de Estudo", style = MaterialTheme.typography.labelSmall) },
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = StreakFire.copy(alpha = 0.25f),
                                selectedLabelColor = StreakFire
                            )
                        )
                    }

                    // APK Update Fields (Shown when TYPE_APP_UPDATE)
                    AnimatedVisibility(visible = broadcastType == BroadcastNotification.TYPE_APP_UPDATE) {
                        Column(modifier = Modifier.padding(top = 12.dp)) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(10.dp)
                            ) {
                                Column(modifier = Modifier.weight(1f)) {
                                    Text(
                                        text = "Versão do APK",
                                        style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    OutlinedTextField(
                                        value = apkVersion,
                                        onValueChange = { apkVersion = it },
                                        singleLine = true,
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedContainerColor = SurfaceElevated,
                                            unfocusedContainerColor = SurfaceElevated,
                                            focusedBorderColor = PrimaryViolet,
                                            unfocusedBorderColor = BorderSubtle,
                                            focusedTextColor = TextPrimary,
                                            unfocusedTextColor = TextPrimary
                                        ),
                                        shape = RoundedCornerShape(6.dp),
                                        modifier = Modifier.fillMaxWidth().testTag("dev_broadcast_version_input")
                                    )
                                }

                                Column(modifier = Modifier.weight(2f)) {
                                    Text(
                                        text = "URL do APK para Download",
                                        style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                    )
                                    Spacer(modifier = Modifier.height(4.dp))
                                    OutlinedTextField(
                                        value = apkDownloadUrl,
                                        onValueChange = { apkDownloadUrl = it },
                                        singleLine = true,
                                        colors = OutlinedTextFieldDefaults.colors(
                                            focusedContainerColor = SurfaceElevated,
                                            unfocusedContainerColor = SurfaceElevated,
                                            focusedBorderColor = PrimaryViolet,
                                            unfocusedBorderColor = BorderSubtle,
                                            focusedTextColor = TextPrimary,
                                            unfocusedTextColor = TextPrimary
                                        ),
                                        shape = RoundedCornerShape(6.dp),
                                        modifier = Modifier.fillMaxWidth().testTag("dev_broadcast_apk_url_input")
                                    )
                                }
                            }

                            Spacer(modifier = Modifier.height(6.dp))

                            // Quick preset buttons for URL
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp)
                            ) {
                                Surface(
                                    color = SurfaceElevated,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.clickable {
                                        apkDownloadUrl = BroadcastNotificationRepository.DEFAULT_APK_DOWNLOAD_URL
                                    }
                                ) {
                                    Text(
                                        text = "Link Dev (Padrão)",
                                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = TextSecondary),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                                Surface(
                                    color = SurfaceElevated,
                                    shape = RoundedCornerShape(4.dp),
                                    modifier = Modifier.clickable {
                                        apkDownloadUrl = BroadcastNotificationRepository.FALLBACK_APK_DOWNLOAD_URL
                                    }
                                ) {
                                    Text(
                                        text = "Link Pre/Prod",
                                        style = MaterialTheme.typography.labelSmall.copy(fontSize = 10.sp, color = TextSecondary),
                                        modifier = Modifier.padding(horizontal = 6.dp, vertical = 2.dp)
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Action buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Button(
                            onClick = {
                                isSending = true
                                sendStatusMessage = null
                                broadcastRepository.sendDevBroadcast(
                                    senderUser = effectiveUser,
                                    title = broadcastTitle,
                                    message = broadcastMessage,
                                    type = broadcastType,
                                    apkUrl = if (broadcastType == BroadcastNotification.TYPE_APP_UPDATE) apkDownloadUrl else null,
                                    apkVersion = apkVersion,
                                    actionLabel = if (broadcastType == BroadcastNotification.TYPE_APP_UPDATE) "Baixar e Atualizar" else "Ver no App",
                                    onComplete = { success, msg ->
                                        isSending = false
                                        if (success) {
                                            sendStatusMessage = "Notificação transmitida com sucesso para todos os usuários!"
                                            Toast.makeText(context, "Notificação enviada a todos os alunos!", Toast.LENGTH_LONG).show()
                                        } else {
                                            sendStatusMessage = msg ?: "Falha ao enviar."
                                            Toast.makeText(context, msg ?: "Erro", Toast.LENGTH_LONG).show()
                                        }
                                    }
                                )
                            },
                            enabled = !isSending && broadcastTitle.isNotBlank() && broadcastMessage.isNotBlank(),
                            colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
                            shape = RoundedCornerShape(6.dp),
                            modifier = Modifier.weight(1f).testTag("send_dev_broadcast_btn")
                        ) {
                            if (isSending) {
                                CircularProgressIndicator(modifier = Modifier.size(16.dp), color = Color.White, strokeWidth = 2.dp)
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Transmitindo...", style = MaterialTheme.typography.labelSmall)
                            } else {
                                Icon(imageVector = Icons.Default.Send, contentDescription = null, modifier = Modifier.size(16.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Disparar para Todos", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Bold))
                            }
                        }

                        // Test on current device button
                        OutlinedButton(
                            onClick = {
                                val streakMgr = com.example.notification.StreakReminderManager(context)
                                streakMgr.showBroadcastNotification(
                                    title = broadcastTitle,
                                    message = broadcastMessage,
                                    isUpdate = broadcastType == BroadcastNotification.TYPE_APP_UPDATE,
                                    downloadUrl = apkDownloadUrl
                                )
                                Toast.makeText(context, "Notificação de teste disparada na barra do sistema!", Toast.LENGTH_SHORT).show()
                            },
                            shape = RoundedCornerShape(6.dp),
                            border = BorderStroke(1.dp, BorderSubtle),
                            colors = ButtonDefaults.outlinedButtonColors(contentColor = TextPrimary),
                            modifier = Modifier.testTag("test_push_device_btn")
                        ) {
                            Text("Testar Push", style = MaterialTheme.typography.labelSmall)
                        }
                    }

                    if (sendStatusMessage != null) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(imageVector = Icons.Default.CheckCircle, contentDescription = null, tint = SuccessEmerald, modifier = Modifier.size(16.dp))
                            Spacer(modifier = Modifier.width(6.dp))
                            Text(text = sendStatusMessage!!, style = MaterialTheme.typography.labelSmall.copy(color = SuccessEmerald))
                        }
                    }
                }
            }
        }

        // Test APK In-App Downloader
        item {
            Card(
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(14.dp)) {
                    Text(
                        text = "Arquivo .APK e Teste de Download",
                        style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold, color = TextPrimary)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Verifique o download e instalação direta do arquivo Knowverse.apk.",
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.5.sp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    if (isDownloading) {
                        Column {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = testDownloadStatus ?: "Baixando Knowverse.apk...",
                                    style = MaterialTheme.typography.labelSmall.copy(color = TextSecondary)
                                )
                                Text(
                                    text = "${(downloadProgress * 100).toInt()}%",
                                    style = MaterialTheme.typography.labelSmall.copy(color = PrimaryViolet, fontWeight = FontWeight.Bold)
                                )
                            }
                            Spacer(modifier = Modifier.height(6.dp))
                            LinearProgressIndicator(
                                progress = { downloadProgress },
                                modifier = Modifier.fillMaxWidth().height(4.dp).clip(RoundedCornerShape(2.dp)),
                                color = PrimaryViolet
                            )
                        }
                    } else {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            Button(
                                onClick = {
                                    testDownloadStatus = "Baixando..."
                                    broadcastRepository.downloadAndInstallApk(
                                        apkUrl = apkDownloadUrl,
                                        onProgress = { p ->
                                            testDownloadStatus = "Baixando: ${(p * 100).toInt()}%"
                                        },
                                        onSuccess = { file ->
                                            testDownloadStatus = "Baixado! Instalador aberto."
                                            Toast.makeText(context, "APK baixado com sucesso!", Toast.LENGTH_LONG).show()
                                        },
                                        onError = { err ->
                                            testDownloadStatus = null
                                            Toast.makeText(context, err, Toast.LENGTH_LONG).show()
                                        }
                                    )
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = SurfaceElevated),
                                border = BorderStroke(1.dp, BorderSubtle),
                                shape = RoundedCornerShape(6.dp),
                                modifier = Modifier.weight(1f).testTag("test_download_apk_btn")
                            ) {
                                Icon(imageVector = Icons.Default.Download, contentDescription = null, tint = TextPrimary, modifier = Modifier.size(15.dp))
                                Spacer(modifier = Modifier.width(6.dp))
                                Text("Testar Download In-App", style = MaterialTheme.typography.labelSmall.copy(color = TextPrimary))
                            }

                            OutlinedButton(
                                onClick = {
                                    broadcastRepository.openDirectDownloadInBrowser(apkDownloadUrl)
                                },
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, BorderSubtle),
                                modifier = Modifier.testTag("test_browser_download_btn")
                            ) {
                                Icon(imageVector = Icons.Default.OpenInBrowser, contentDescription = "Abrir Link Direto", tint = TextSecondary, modifier = Modifier.size(15.dp))
                            }
                        }
                    }
                }
            }
        }

        // Broadcast History List
        item {
            Text(
                text = "Histórico de Transmissões Recentes (${notifications.size})",
                style = MaterialTheme.typography.titleSmall.copy(fontWeight = FontWeight.SemiBold, color = TextPrimary)
            )
        }

        if (notifications.isEmpty()) {
            item {
                Surface(
                    color = ObsidianSurface,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Box(modifier = Modifier.padding(20.dp), contentAlignment = Alignment.Center) {
                        Text(
                            text = "Nenhuma notificação transmitida ainda.",
                            style = MaterialTheme.typography.bodySmall.copy(color = TextTertiary)
                        )
                    }
                }
            }
        } else {
            items(notifications, key = { it.id }) { notif ->
                val dateFormat = remember { SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault()) }
                val formattedDate = remember(notif.timestamp) { dateFormat.format(Date(notif.timestamp)) }

                Surface(
                    color = ObsidianSurface,
                    shape = RoundedCornerShape(6.dp),
                    border = BorderStroke(1.dp, BorderSubtle),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(12.dp)) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Surface(
                                    color = if (notif.isUpdate) PrimaryViolet.copy(alpha = 0.2f) else SuccessEmerald.copy(alpha = 0.2f),
                                    shape = RoundedCornerShape(3.dp)
                                ) {
                                    Text(
                                        text = if (notif.isUpdate) "Atualização ${notif.apkVersion}" else "Aviso Geral",
                                        style = MaterialTheme.typography.labelSmall.copy(
                                            color = if (notif.isUpdate) PrimaryViolet else SuccessEmerald,
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 9.sp
                                        ),
                                        modifier = Modifier.padding(horizontal = 4.dp, vertical = 2.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.width(6.dp))
                                Text(
                                    text = notif.title,
                                    style = MaterialTheme.typography.labelMedium.copy(fontWeight = FontWeight.Bold, color = TextPrimary)
                                )
                            }
                            Text(
                                text = formattedDate,
                                style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary, fontSize = 9.5.sp)
                            )
                        }

                        Spacer(modifier = Modifier.height(4.dp))
                        Text(
                            text = notif.message,
                            style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.5.sp)
                        )

                        if (!notif.apkUrl.isNullOrBlank()) {
                            Spacer(modifier = Modifier.height(4.dp))
                            Text(
                                text = "Link APK: ${notif.apkUrl}",
                                style = MaterialTheme.typography.labelSmall.copy(color = TextTertiary, fontSize = 10.sp),
                                maxLines = 1
                            )
                        }
                    }
                }
            }
        }
    }
}
