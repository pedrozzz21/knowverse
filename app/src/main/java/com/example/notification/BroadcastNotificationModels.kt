package com.example.notification

/**
 * Data models for system broadcasts, announcements, and in-app APK version updates.
 */
data class BroadcastNotification(
    val id: String,
    val title: String,
    val message: String,
    val senderEmail: String = "contatopedro21g@gmail.com",
    val senderName: String = "Pedro (Dev Admin)",
    val type: String = TYPE_APP_UPDATE, // "APP_UPDATE", "STUDY_REMINDER", "GENERAL"
    val apkUrl: String? = null,
    val apkVersion: String = "1.0.0",
    val timestamp: Long = System.currentTimeMillis(),
    val isRead: Boolean = false,
    val actionLabel: String = "Baixar e Atualizar"
) {
    companion object {
        const val TYPE_APP_UPDATE = "APP_UPDATE"
        const val TYPE_STUDY_REMINDER = "STUDY_REMINDER"
        const val TYPE_GENERAL = "GENERAL"
    }

    val isUpdate: Boolean
        get() = type == TYPE_APP_UPDATE || !apkUrl.isNullOrBlank()
}

data class AppReleaseUpdate(
    val versionCode: Int = 1,
    val versionName: String = "1.0.0",
    val releaseTitle: String = "Nova Versão do Knowverse",
    val releaseNotes: String = "",
    val apkUrl: String = "",
    val publishedAt: Long = System.currentTimeMillis(),
    val publishedBy: String = "contatopedro21g@gmail.com"
)
