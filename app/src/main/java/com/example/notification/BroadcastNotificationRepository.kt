package com.example.notification

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Environment
import android.util.Log
import androidx.core.content.FileProvider
import com.example.auth.AppUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.File
import java.io.FileOutputStream
import java.net.HttpURLConnection
import java.net.URL

/**
 * Manages broadcast notifications and in-app APK version updates.
 * Only authenticated developer Pedro (contatopedro21g@gmail.com) can publish broadcasts and releases.
 * All students receive live broadcasts, push notifications, and in-app APK download capability.
 */
class BroadcastNotificationRepository(private val context: Context) {

    private val prefs: SharedPreferences =
        context.getSharedPreferences("knowverse_broadcast_notifications_prefs", Context.MODE_PRIVATE)

    private val scope = CoroutineScope(Dispatchers.IO)
    private val streakReminderManager = StreakReminderManager(context)

    private val firestore: FirebaseFirestore? by lazy {
        try {
            com.example.KnowverseApp.ensureFirebase(context)
            val dbId = try { context.getString(com.example.R.string.firestore_database_id) } catch (_: Exception) { null }
            if (dbId != null) FirebaseFirestore.getInstance(dbId) else FirebaseFirestore.getInstance()
        } catch (e: Exception) {
            Log.w("BroadcastRepo", "Firestore init: ${e.message}")
            null
        }
    }

    private val _notifications = MutableStateFlow<List<BroadcastNotification>>(emptyList())
    val notifications: StateFlow<List<BroadcastNotification>> = _notifications.asStateFlow()

    private val _latestUpdate = MutableStateFlow<AppReleaseUpdate?>(null)
    val latestUpdate: StateFlow<AppReleaseUpdate?> = _latestUpdate.asStateFlow()

    private val _activeBanner = MutableStateFlow<BroadcastNotification?>(null)
    val activeBanner: StateFlow<BroadcastNotification?> = _activeBanner.asStateFlow()

    private val _unreadCount = MutableStateFlow(0)
    val unreadCount: StateFlow<Int> = _unreadCount.asStateFlow()

    private val _isDownloading = MutableStateFlow(false)
    val isDownloading: StateFlow<Boolean> = _isDownloading.asStateFlow()

    private val _downloadProgress = MutableStateFlow(0f)
    val downloadProgress: StateFlow<Float> = _downloadProgress.asStateFlow()

    private var firestoreListener: ListenerRegistration? = null
    private var lastNotifiedTimestamp: Long = prefs.getLong("last_notified_timestamp", 0L)

    companion object {
        const val DEV_EMAIL = "contatopedro21g@gmail.com"
        const val DEFAULT_APK_DOWNLOAD_URL = "https://ais-dev-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/Knowverse.apk"
        const val FALLBACK_APK_DOWNLOAD_URL = "https://ais-pre-yo6mgemrmiadtta4aeltxe-590033031823.us-west2.run.app/Knowverse.apk"
        const val CURRENT_APP_VERSION = "1.0.0"
        const val CURRENT_APP_VERSION_CODE = 1
    }

    init {
        loadCachedNotifications()
        startLiveListener()
    }

    fun isDevUser(user: AppUser?): Boolean {
        if (user == null) return false
        val cleanEmail = user.email.trim().lowercase()
        return user.isDev || cleanEmail == DEV_EMAIL
    }

    private fun loadCachedNotifications() {
        try {
            val raw = prefs.getString("cached_broadcasts_json", null) ?: return
            val arr = JSONArray(raw)
            val list = mutableListOf<BroadcastNotification>()
            val readIds = prefs.getStringSet("read_notification_ids", emptySet()) ?: emptySet()

            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                val id = obj.getString("id")
                list.add(
                    BroadcastNotification(
                        id = id,
                        title = obj.getString("title"),
                        message = obj.getString("message"),
                        senderEmail = obj.optString("senderEmail", DEV_EMAIL),
                        senderName = obj.optString("senderName", "Pedro (Dev Admin)"),
                        type = obj.optString("type", BroadcastNotification.TYPE_APP_UPDATE),
                        apkUrl = obj.optString("apkUrl", "").ifBlank { null },
                        apkVersion = obj.optString("apkVersion", CURRENT_APP_VERSION),
                        timestamp = obj.optLong("timestamp", System.currentTimeMillis()),
                        isRead = readIds.contains(id),
                        actionLabel = obj.optString("actionLabel", "Baixar e Atualizar")
                    )
                )
            }
            _notifications.value = list
            updateUnreadCount(list)
            _activeBanner.value = list.firstOrNull { !it.isRead }
        } catch (e: Exception) {
            Log.w("BroadcastRepo", "loadCached: ${e.message}")
        }
    }

    private fun saveCachedNotifications(list: List<BroadcastNotification>) {
        try {
            val arr = JSONArray()
            for (n in list) {
                val obj = JSONObject().apply {
                    put("id", n.id)
                    put("title", n.title)
                    put("message", n.message)
                    put("senderEmail", n.senderEmail)
                    put("senderName", n.senderName)
                    put("type", n.type)
                    put("apkUrl", n.apkUrl ?: "")
                    put("apkVersion", n.apkVersion)
                    put("timestamp", n.timestamp)
                    put("actionLabel", n.actionLabel)
                }
                arr.put(obj)
            }
            prefs.edit().putString("cached_broadcasts_json", arr.toString()).apply()
        } catch (_: Exception) {}
    }

    fun startLiveListener() {
        val db = firestore ?: return
        try {
            firestoreListener?.remove()
            firestoreListener = db.collection("broadcast_notifications")
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .limit(25)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.w("BroadcastRepo", "Listener err: ${error.message}")
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {
                        val readIds = prefs.getStringSet("read_notification_ids", emptySet()) ?: emptySet()
                        val list = mutableListOf<BroadcastNotification>()
                        for (doc in snapshot.documents) {
                            val id = doc.getString("id") ?: doc.id
                            val title = doc.getString("title") ?: "Aviso Knowverse"
                            val message = doc.getString("message") ?: ""
                            val senderEmail = doc.getString("senderEmail") ?: DEV_EMAIL
                            val senderName = doc.getString("senderName") ?: "Pedro (Dev Admin)"
                            val type = doc.getString("type") ?: BroadcastNotification.TYPE_APP_UPDATE
                            val apkUrl = doc.getString("apkUrl")?.takeIf { it.isNotBlank() }
                            val apkVersion = doc.getString("apkVersion") ?: CURRENT_APP_VERSION
                            val ts = doc.getLong("timestamp") ?: System.currentTimeMillis()
                            val actionLabel = doc.getString("actionLabel") ?: "Baixar e Atualizar"

                            list.add(
                                BroadcastNotification(
                                    id = id,
                                    title = title,
                                    message = message,
                                    senderEmail = senderEmail,
                                    senderName = senderName,
                                    type = type,
                                    apkUrl = apkUrl,
                                    apkVersion = apkVersion,
                                    timestamp = ts,
                                    isRead = readIds.contains(id),
                                    actionLabel = actionLabel
                                )
                            )
                        }

                        _notifications.value = list
                        saveCachedNotifications(list)
                        updateUnreadCount(list)

                        // Check if there is a new broadcast to alert the user via system push
                        val newest = list.firstOrNull()
                        if (newest != null && newest.timestamp > lastNotifiedTimestamp) {
                            lastNotifiedTimestamp = newest.timestamp
                            prefs.edit().putLong("last_notified_timestamp", lastNotifiedTimestamp).apply()
                            _activeBanner.value = newest

                            // Show Android system tray notification
                            streakReminderManager.showBroadcastNotification(
                                title = newest.title,
                                message = newest.message,
                                isUpdate = newest.isUpdate,
                                downloadUrl = newest.apkUrl
                            )
                        } else if (_activeBanner.value == null) {
                            _activeBanner.value = list.firstOrNull { !it.isRead }
                        }
                    }
                }

            // Also check latest app update
            db.collection("app_updates").document("latest")
                .addSnapshotListener { doc, _ ->
                    if (doc != null && doc.exists()) {
                        val vCode = doc.getLong("versionCode")?.toInt() ?: CURRENT_APP_VERSION_CODE
                        val vName = doc.getString("versionName") ?: CURRENT_APP_VERSION
                        val title = doc.getString("releaseTitle") ?: "Atualização Knowverse"
                        val notes = doc.getString("releaseNotes") ?: ""
                        val apkUrl = doc.getString("apkUrl") ?: DEFAULT_APK_DOWNLOAD_URL
                        val pubAt = doc.getLong("publishedAt") ?: System.currentTimeMillis()
                        val pubBy = doc.getString("publishedBy") ?: DEV_EMAIL

                        val update = AppReleaseUpdate(
                            versionCode = vCode,
                            versionName = vName,
                            releaseTitle = title,
                            releaseNotes = notes,
                            apkUrl = apkUrl,
                            publishedAt = pubAt,
                            publishedBy = pubBy
                        )
                        _latestUpdate.value = update
                    }
                }
        } catch (e: Exception) {
            Log.w("BroadcastRepo", "Start listener exception: ${e.message}")
        }
    }

    private fun updateUnreadCount(list: List<BroadcastNotification>) {
        _unreadCount.value = list.count { !it.isRead }
    }

    fun markAsRead(notificationId: String) {
        val currentSet = prefs.getStringSet("read_notification_ids", emptySet())?.toMutableSet() ?: mutableSetOf()
        currentSet.add(notificationId)
        prefs.edit().putStringSet("read_notification_ids", currentSet).apply()

        val updated = _notifications.value.map {
            if (it.id == notificationId) it.copy(isRead = true) else it
        }
        _notifications.value = updated
        updateUnreadCount(updated)

        if (_activeBanner.value?.id == notificationId) {
            _activeBanner.value = updated.firstOrNull { !it.isRead }
        }
    }

    fun markAllAsRead() {
        val allIds = _notifications.value.map { it.id }.toSet()
        prefs.edit().putStringSet("read_notification_ids", allIds).apply()

        val updated = _notifications.value.map { it.copy(isRead = true) }
        _notifications.value = updated
        _unreadCount.value = 0
        _activeBanner.value = null
    }

    fun dismissBanner() {
        _activeBanner.value = null
    }

    /**
     * Send broadcast notification to all users.
     * Gated STRICTLY to developer Pedro (contatopedro21g@gmail.com).
     */
    fun sendDevBroadcast(
        senderUser: AppUser?,
        title: String,
        message: String,
        type: String = BroadcastNotification.TYPE_APP_UPDATE,
        apkUrl: String? = DEFAULT_APK_DOWNLOAD_URL,
        apkVersion: String = CURRENT_APP_VERSION,
        actionLabel: String = "Baixar e Atualizar",
        onComplete: (Boolean, String?) -> Unit
    ) {
        if (!isDevUser(senderUser)) {
            onComplete(false, "Permissão negada. Apenas o desenvolvedor Pedro tem permissão para enviar avisos a todos os usuários.")
            return
        }

        val cleanTitle = title.trim().ifBlank { "Aviso do Desenvolvedor" }
        val cleanMessage = message.trim().ifBlank { "Nova atualização do Knowverse disponível!" }
        val finalApkUrl = if (type == BroadcastNotification.TYPE_APP_UPDATE && !apkUrl.isNullOrBlank()) apkUrl.trim() else null

        val notifId = "broadcast_" + System.currentTimeMillis()
        val timestamp = System.currentTimeMillis()

        val newBroadcast = BroadcastNotification(
            id = notifId,
            title = cleanTitle,
            message = cleanMessage,
            senderEmail = senderUser?.email ?: DEV_EMAIL,
            senderName = senderUser?.displayName ?: "Pedro (Dev Admin)",
            type = type,
            apkUrl = finalApkUrl,
            apkVersion = apkVersion.trim().ifBlank { CURRENT_APP_VERSION },
            timestamp = timestamp,
            isRead = false,
            actionLabel = actionLabel
        )

        // Optimistically update local list
        val currentList = _notifications.value.toMutableList()
        currentList.add(0, newBroadcast)
        _notifications.value = currentList
        saveCachedNotifications(currentList)
        updateUnreadCount(currentList)

        scope.launch {
            try {
                val db = firestore
                if (db != null) {
                    val map = hashMapOf(
                        "id" to notifId,
                        "title" to cleanTitle,
                        "message" to cleanMessage,
                        "senderEmail" to (senderUser?.email ?: DEV_EMAIL),
                        "senderName" to (senderUser?.displayName ?: "Pedro (Dev Admin)"),
                        "type" to type,
                        "apkUrl" to (finalApkUrl ?: ""),
                        "apkVersion" to apkVersion,
                        "timestamp" to timestamp,
                        "actionLabel" to actionLabel
                    )
                    db.collection("broadcast_notifications").document(notifId).set(map).await()

                    // If it is an APK update, also update app_updates/latest document
                    if (type == BroadcastNotification.TYPE_APP_UPDATE && !finalApkUrl.isNullOrBlank()) {
                        val releaseMap = hashMapOf(
                            "versionCode" to (CURRENT_APP_VERSION_CODE + 1),
                            "versionName" to apkVersion,
                            "releaseTitle" to cleanTitle,
                            "releaseNotes" to cleanMessage,
                            "apkUrl" to finalApkUrl,
                            "publishedAt" to timestamp,
                            "publishedBy" to (senderUser?.email ?: DEV_EMAIL)
                        )
                        db.collection("app_updates").document("latest").set(releaseMap).await()
                    }
                }

                // Fire local heads-up push for testing / immediate visual feedback
                withContext(Dispatchers.Main) {
                    streakReminderManager.showBroadcastNotification(
                        title = cleanTitle,
                        message = cleanMessage,
                        isUpdate = type == BroadcastNotification.TYPE_APP_UPDATE,
                        downloadUrl = finalApkUrl
                    )
                    onComplete(true, null)
                }
            } catch (e: Exception) {
                Log.e("BroadcastRepo", "Failed to send broadcast", e)
                withContext(Dispatchers.Main) {
                    onComplete(true, "Transmitido localmente (Firestore aviso: ${e.message})")
                }
            }
        }
    }

    /**
     * Downloads the APK in-app with streaming progress and triggers the native Android package installer.
     */
    fun downloadAndInstallApk(
        apkUrl: String,
        onProgress: (Float) -> Unit,
        onSuccess: (File) -> Unit,
        onError: (String) -> Unit
    ) {
        val targetUrl = apkUrl.trim().ifBlank { DEFAULT_APK_DOWNLOAD_URL }
        _isDownloading.value = true
        _downloadProgress.value = 0f

        scope.launch {
            try {
                // Ensure target file path in external files directory or cache
                val downloadDir = context.getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS) ?: context.cacheDir
                downloadDir.mkdirs()
                val destinationFile = File(downloadDir, "Knowverse.apk")
                if (destinationFile.exists()) {
                    destinationFile.delete()
                }

                val url = URL(targetUrl)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connectTimeout = 15000
                connection.readTimeout = 30000
                connection.instanceFollowRedirects = true
                connection.connect()

                val responseCode = connection.responseCode
                if (responseCode !in 200..299) {
                    throw IllegalStateException("Servidor respondeu com código HTTP $responseCode")
                }

                val contentLength = connection.contentLength
                val inputStream = connection.inputStream
                val outputStream = FileOutputStream(destinationFile)

                val buffer = ByteArray(8 * 1024)
                var bytesRead: Int
                var totalBytesRead = 0L

                while (inputStream.read(buffer).also { bytesRead = it } != -1) {
                    outputStream.write(buffer, 0, bytesRead)
                    totalBytesRead += bytesRead
                    if (contentLength > 0) {
                        val progress = (totalBytesRead.toFloat() / contentLength.toFloat()).coerceIn(0f, 1f)
                        _downloadProgress.value = progress
                        withContext(Dispatchers.Main) {
                            onProgress(progress)
                        }
                    }
                }

                outputStream.flush()
                outputStream.close()
                inputStream.close()
                connection.disconnect()

                _isDownloading.value = false
                _downloadProgress.value = 1f

                withContext(Dispatchers.Main) {
                    onProgress(1f)
                    onSuccess(destinationFile)
                    promptInstallApk(destinationFile)
                }
            } catch (e: Exception) {
                _isDownloading.value = false
                Log.e("BroadcastRepo", "Download APK error", e)
                withContext(Dispatchers.Main) {
                    onError("Falha no download direto: ${e.message}. Você pode tentar abrir o link direto no navegador.")
                }
            }
        }
    }

    /**
     * Prompts the native Android package installer to install the downloaded APK.
     */
    fun promptInstallApk(apkFile: File) {
        try {
            val contentUri = FileProvider.getUriForFile(
                context,
                "${context.packageName}.fileprovider",
                apkFile
            )

            val installIntent = Intent(Intent.ACTION_VIEW).apply {
                setDataAndType(contentUri, "application/vnd.android.package-archive")
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            }
            context.startActivity(installIntent)
        } catch (e: Exception) {
            Log.e("BroadcastRepo", "Failed to launch package installer", e)
            openDirectDownloadInBrowser(DEFAULT_APK_DOWNLOAD_URL)
        }
    }

    /**
     * Opens the direct APK download link in the browser as an immediate alternative.
     */
    fun openDirectDownloadInBrowser(apkUrl: String) {
        try {
            val finalUrl = apkUrl.trim().ifBlank { DEFAULT_APK_DOWNLOAD_URL }
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(finalUrl)).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
            }
            context.startActivity(intent)
        } catch (_: Exception) {}
    }
}
