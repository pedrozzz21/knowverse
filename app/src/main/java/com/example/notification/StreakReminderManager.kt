package com.example.notification

import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.MainActivity
import com.example.R
import java.util.Calendar

/**
 * Manages daily study and streak push notifications.
 * Reminds learners to complete their daily study track to prevent losing their streak.
 */
class StreakReminderManager(private val context: Context) {

    private val prefs = context.getSharedPreferences("knowverse_notification_prefs", Context.MODE_PRIVATE)

    companion object {
        const val CHANNEL_ID = "knowverse_streak_reminders"
        const val CHANNEL_NAME = "Lembretes Diários de Estudo & Streak"
        const val NOTIFICATION_ID = 1001

        const val CHANNEL_BROADCAST_ID = "knowverse_dev_broadcasts"
        const val CHANNEL_BROADCAST_NAME = "Avisos e Atualizações do Knowverse"
        const val BROADCAST_NOTIFICATION_ID = 2002

        const val PREF_ENABLED = "pref_notifications_enabled"
        const val PREF_HOUR = "pref_reminder_hour"
        const val PREF_MINUTE = "pref_reminder_minute"

        const val ACTION_FIRE_REMINDER = "com.example.notification.ACTION_STREAK_REMINDER"

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

                // Streak reminder channel
                val streakChannel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                    description = "Notificações diárias do Knowverse para lembrar de praticar lições e manter a sequência (streak) ativa."
                    enableVibration(true)
                    enableLights(true)
                }
                notificationManager.createNotificationChannel(streakChannel)

                // Broadcast & updates channel
                val broadcastChannel = NotificationChannel(CHANNEL_BROADCAST_ID, CHANNEL_BROADCAST_NAME, NotificationManager.IMPORTANCE_HIGH).apply {
                    description = "Avisos do desenvolvedor, novidades de conteúdo e notificações de novas versões do Knowverse."
                    enableVibration(true)
                    enableLights(true)
                }
                notificationManager.createNotificationChannel(broadcastChannel)
            }
        }
    }

    init {
        createNotificationChannel(context)
    }

    var isReminderEnabled: Boolean
        get() = prefs.getBoolean(PREF_ENABLED, true)
        set(value) {
            prefs.edit().putBoolean(PREF_ENABLED, value).apply()
            if (value) {
                scheduleDailyReminder(reminderHour, reminderMinute)
            } else {
                cancelReminder()
            }
        }

    var reminderHour: Int
        get() = prefs.getInt(PREF_HOUR, 20) // Default 20:00 (8:00 PM)
        set(value) {
            prefs.edit().putInt(PREF_HOUR, value).apply()
            if (isReminderEnabled) {
                scheduleDailyReminder(value, reminderMinute)
            }
        }

    var reminderMinute: Int
        get() = prefs.getInt(PREF_MINUTE, 0)
        set(value) {
            prefs.edit().putInt(PREF_MINUTE, value).apply()
            if (isReminderEnabled) {
                scheduleDailyReminder(reminderHour, value)
            }
        }

    fun scheduleDailyReminder(hour: Int = reminderHour, minute: Int = reminderMinute) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return

        val intent = Intent(context, StreakReminderReceiver::class.java).apply {
            action = ACTION_FIRE_REMINDER
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 0)
            set(Calendar.MILLISECOND, 0)
            // If scheduled time has already passed today, schedule for tomorrow
            if (timeInMillis <= System.currentTimeMillis()) {
                add(Calendar.DAY_OF_YEAR, 1)
            }
        }

        try {
            alarmManager.setInexactRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
            )
        } catch (_: Exception) {
            try {
                alarmManager.set(
                    AlarmManager.RTC_WAKEUP,
                    calendar.timeInMillis,
                    pendingIntent
                )
            } catch (_: Exception) {}
        }
    }

    fun cancelReminder() {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as? AlarmManager ?: return
        val intent = Intent(context, StreakReminderReceiver::class.java).apply {
            action = ACTION_FIRE_REMINDER
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            NOTIFICATION_ID,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }

    /**
     * Dispatches the streak reminder push notification with rich mascot imagery and action.
     */
    fun showStreakNotification(
        streakDays: Int,
        userName: String,
        hasStudiedToday: Boolean
    ) {
        val contentIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        val pendingContentIntent = PendingIntent.getActivity(
            context,
            0,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val title = if (hasStudiedToday) {
            "🔥 Sequência Ativa: $streakDays dias seguidos!"
        } else {
            "🔥 Otto está te esperando: mantenha seu streak de $streakDays dias!"
        }

        val body = if (hasStudiedToday) {
            "Parabéns pelo foco de hoje, $userName! Você garantiu sua evolução no Knowverse."
        } else {
            "Olá $userName! Reserve 5 minutinhos para praticar uma lição e não deixar sua chama apagar hoje!"
        }

        val mascotBitmap = try {
            BitmapFactory.decodeResource(context.resources, R.drawable.img_mascot_otter_boy)
        } catch (_: Exception) {
            null
        }

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(R.drawable.img_mascot_otter_boy)
            .setLargeIcon(mascotBitmap)
            .setContentTitle(title)
            .setContentText(body)
            .setStyle(NotificationCompat.BigTextStyle().bigText(body))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setColor(0xFF0C439A.toInt())
            .setAutoCancel(true)
            .setContentIntent(pendingContentIntent)
            .addAction(
                R.drawable.img_mascot_otter_boy,
                "Abrir e Praticar",
                pendingContentIntent
            )
            .build()

        try {
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(NOTIFICATION_ID, notification)
        } catch (_: SecurityException) {
            // Permission POST_NOTIFICATIONS might be denied on Android 13+
        }
    }

    /**
     * Dispatches a broadcast notification sent by the developer (e.g., "Hora de estudar! Nova atualização!!").
     */
    fun showBroadcastNotification(
        title: String,
        message: String,
        isUpdate: Boolean = false,
        downloadUrl: String? = null,
        notificationId: Int = BROADCAST_NOTIFICATION_ID
    ) {
        val contentIntent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("OPEN_BROADCAST_MODAL", true)
            putExtra("BROADCAST_TITLE", title)
            putExtra("BROADCAST_MESSAGE", message)
            putExtra("BROADCAST_IS_UPDATE", isUpdate)
            downloadUrl?.let { putExtra("BROADCAST_APK_URL", it) }
        }
        val pendingContentIntent = PendingIntent.getActivity(
            context,
            notificationId,
            contentIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val mascotBitmap = try {
            BitmapFactory.decodeResource(context.resources, R.drawable.img_mascot_otter_boy)
        } catch (_: Exception) {
            null
        }

        val builder = NotificationCompat.Builder(context, CHANNEL_BROADCAST_ID)
            .setSmallIcon(R.drawable.img_mascot_otter_boy)
            .setLargeIcon(mascotBitmap)
            .setContentTitle(title)
            .setContentText(message)
            .setStyle(NotificationCompat.BigTextStyle().bigText(message))
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setColor(0xFF7C3AED.toInt()) // Violet accent
            .setAutoCancel(true)
            .setContentIntent(pendingContentIntent)

        if (isUpdate) {
            builder.addAction(
                R.drawable.img_mascot_otter_boy,
                "Baixar Atualização",
                pendingContentIntent
            )
        } else {
            builder.addAction(
                R.drawable.img_mascot_otter_boy,
                "Ver no Knowverse",
                pendingContentIntent
            )
        }

        try {
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(notificationId, builder.build())
        } catch (_: SecurityException) {
            // Notifications permission not granted
        }
    }
}
