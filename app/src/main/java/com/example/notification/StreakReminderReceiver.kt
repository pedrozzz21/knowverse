package com.example.notification

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.example.data.local.UserProgressRepository

/**
 * BroadcastReceiver triggered by AlarmManager or Device Reboot.
 * Checks whether user has studied today and triggers the notification if needed.
 */
class StreakReminderReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val reminderManager = StreakReminderManager(context)

        // If triggered after device reboot, reschedule alarms
        if (intent?.action == Intent.ACTION_BOOT_COMPLETED) {
            if (reminderManager.isReminderEnabled) {
                reminderManager.scheduleDailyReminder()
            }
            return
        }

        if (!reminderManager.isReminderEnabled) return

        val progressRepo = UserProgressRepository(context)
        val progress = progressRepo.progressState.value
        val hasStudiedToday = progress.hasStudiedToday()

        val spAuth = context.getSharedPreferences("knowverse_user_custom_profiles", Context.MODE_PRIVATE)
        val currentName = spAuth.getString("active_user_name", "Estudante") ?: "Estudante"

        reminderManager.showStreakNotification(
            streakDays = progress.currentStreak,
            userName = currentName,
            hasStudiedToday = hasStudiedToday
        )

        // Reschedule for next day
        reminderManager.scheduleDailyReminder()
    }
}
