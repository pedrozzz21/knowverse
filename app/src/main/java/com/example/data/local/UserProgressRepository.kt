package com.example.data.local

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.example.data.model.Achievement
import com.example.data.model.CheckInResult
import com.example.data.model.TrackBadgesCatalog
import com.example.data.model.UserProgress
import com.example.data.model.UserProgressAdminRecord
import com.example.ui.components.AchievementAlertManager
import com.example.ui.components.InAppAchievementAlert
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

data class GlobalLeaderboardUser(
    val userId: String,
    val displayName: String,
    val email: String,
    val photoUrl: String?,
    val totalXp: Int,
    val level: Int,
    val streak: Int,
    val activeDays: Int
)

class UserProgressRepository(context: Context) {

    private val appContext: Context = context.applicationContext

    private val prefs: SharedPreferences =
        appContext.getSharedPreferences("knowverse_user_progress", Context.MODE_PRIVATE)

    private val db: FirebaseFirestore? by lazy {
        try {
            val dbId = try { context.getString(com.example.R.string.firestore_database_id) } catch (_: Exception) { null }
            if (dbId != null) {
                FirebaseFirestore.getInstance(dbId)
            } else {
                FirebaseFirestore.getInstance()
            }
        } catch (e: Exception) {
            Log.w("UserProgressRepo", "Firebase Firestore offline/unavailable: ${e.message}")
            null
        }
    }
    private var firestoreListener: ListenerRegistration? = null
    private var activeUserId: String? = null
    private var activeUserEmail: String? = null
    private var activeDisplayName: String? = null
    private var activePhotoUrl: String? = null

    private val _progressState = MutableStateFlow(loadProgress())
    val progressState: StateFlow<UserProgress> = _progressState.asStateFlow()

    private val _achievementsState = MutableStateFlow(loadAchievements())
    val achievementsState: StateFlow<List<Achievement>> = _achievementsState.asStateFlow()

    private val _onlineLeaderboardState = MutableStateFlow<List<GlobalLeaderboardUser>>(emptyList())
    val onlineLeaderboardState: StateFlow<List<GlobalLeaderboardUser>> = _onlineLeaderboardState.asStateFlow()

    private var leaderboardListener: ListenerRegistration? = null

    private val scope = CoroutineScope(Dispatchers.IO)

    init {
        checkAndUpdateDailyStreak()
        startGlobalLeaderboardListener()
    }

    fun getTodayDateString(): String {
        return com.example.data.time.NetworkTimeProvider.getTodayGmt3()
    }

    fun getYesterdayDateString(): String {
        return com.example.data.time.NetworkTimeProvider.getYesterdayGmt3()
    }

    private fun getDateDaysAgoString(daysAgo: Int): String {
        val cal = Calendar.getInstance(java.util.TimeZone.getTimeZone("America/Sao_Paulo"))
        cal.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        sdf.timeZone = java.util.TimeZone.getTimeZone("America/Sao_Paulo")
        return sdf.format(cal.time)
    }

    /**
     * Verifica a expiração do streak de acordo com a virada das 00h00 GMT-3 (Horário de Brasília):
     * - Se o usuário realizou estudo HOJE: streak mantido e aceso.
     * - Se o usuário realizou estudo ONTEM: streak mantido (ex: 1d, 2d), porém apagado até estudar hoje.
     * - Se o usuário NÃO realizou estudo nem hoje nem ontem: streak zera para 0d.
     * - Se o usuário é novo / sem XP / sem atividades concluídas: streak permanece em 0d.
     */
    fun checkStreakExpiration(): Boolean {
        val today = getTodayDateString()
        val yesterday = getYesterdayDateString()
        val current = _progressState.value
        val lastQualifying = current.lastQualifyingStudyDate

        // Correção de reset para novos usuários ou usuários sem atividade
        if (current.totalXp == 0 && current.completedLessonIds.isEmpty() && current.totalSolvedExercises == 0) {
            if (current.currentStreak > 0 || current.activeStudyDates.isNotEmpty() || current.lastQualifyingStudyDate.isNotBlank()) {
                val updated = current.copy(
                    currentStreak = 0,
                    lastQualifyingStudyDate = "",
                    activeStudyDates = emptySet()
                )
                saveProgress(updated, pushToRemote = true)
                updateAchievements(updated)
                return true
            }
        }

        val hasLostStreak = current.currentStreak > 0 &&
                (lastQualifying.isBlank() || (lastQualifying != today && lastQualifying != yesterday))

        if (hasLostStreak) {
            Log.i("UserProgressRepo", "Streak zerado para 0d: virada das 00h00 GMT-3 sem atividade qualificadora (última: '$lastQualifying', hoje: '$today').")
            val updated = current.copy(
                currentStreak = 0,
                lastActiveDate = today,
                dailyXp = if (current.lastActiveDate != today) 0 else current.dailyXp
            )
            saveProgress(updated, pushToRemote = true)
            updateAchievements(updated)
            return true
        } else if (current.lastActiveDate != today && current.dailyXp > 0) {
            // Virou o dia (00h00 GMT-3): reseta o XP diário
            val updated = current.copy(
                lastActiveDate = today,
                dailyXp = 0
            )
            saveProgress(updated, pushToRemote = true)
        }
        return false
    }

    private fun checkAndUpdateDailyStreak() {
        checkStreakExpiration()
    }

    fun syncUserAccount(userId: String, email: String?, displayName: String?, photoUrl: String? = null) {
        activeUserId = userId
        activeUserEmail = email
        activeDisplayName = displayName
        val cleanEmail = email?.trim()?.lowercase() ?: ""

        val permanentPhoto = if (cleanEmail.isNotBlank()) prefs.getString("permanent_avatar_${cleanEmail}", null) else null
        val cachedPhoto = prefs.getString("active_user_photo_${userId}", null)

        val resolvedPhoto = when {
            !photoUrl.isNullOrBlank() && photoUrl != "mascot_sealion" -> photoUrl
            !permanentPhoto.isNullOrBlank() && permanentPhoto != "mascot_sealion" -> permanentPhoto
            !cachedPhoto.isNullOrBlank() && cachedPhoto != "mascot_sealion" -> cachedPhoto
            !photoUrl.isNullOrBlank() -> photoUrl
            !permanentPhoto.isNullOrBlank() -> permanentPhoto
            !cachedPhoto.isNullOrBlank() -> cachedPhoto
            else -> getActivePhotoForUser(userId)
        }

        if (!resolvedPhoto.isNullOrBlank()) {
            activePhotoUrl = resolvedPhoto
            prefs.edit().putString("active_user_photo_${userId}", resolvedPhoto).apply()
            if (cleanEmail.isNotBlank() && resolvedPhoto != "mascot_sealion") {
                prefs.edit().putString("permanent_avatar_${cleanEmail}", resolvedPhoto).apply()
            }
        }

        val userProgress = loadProgressForUser(userId)
        _progressState.value = userProgress
        checkAndUpdateDailyStreak()
        updateAchievements(_progressState.value)

        val firestore = db ?: return
        firestoreListener?.remove()
        firestoreListener = firestore.collection("users").document(userId)
            .addSnapshotListener { snapshot, error ->
                if (error != null) {
                    Log.w("UserProgressRepo", "Firestore user listener inactive: ${error.message}")
                    return@addSnapshotListener
                }

                if (snapshot != null && snapshot.exists()) {
                    val remoteXp = snapshot.getLong("totalXp")?.toInt() ?: 0
                    val remoteStreak = snapshot.getLong("currentStreak")?.toInt() ?: 0
                    val remoteActiveDays = snapshot.getLong("activeDays")?.toInt() ?: 0
                    val remoteExercises = snapshot.getLong("totalSolvedExercises")?.toInt() ?: 0
                    val remoteLessons = (snapshot.get("completedLessonIds") as? List<*>)
                        ?.filterIsInstance<String>()?.toSet() ?: emptySet()
                    val remoteReviews = (snapshot.get("reviewLessonIds") as? List<*>)
                        ?.filterIsInstance<String>()?.toSet() ?: emptySet()

                    @Suppress("UNCHECKED_CAST")
                    val remoteKahoot = (snapshot.get("completedKahootScores") as? Map<String, *>)
                        ?.mapValues { (it.value as? Number)?.toInt() ?: 0 } ?: emptyMap()

                    val remoteBadges = (snapshot.get("unlockedBadges") as? List<*>)
                        ?.filterIsInstance<String>()?.toSet() ?: emptySet()

                    val remotePerfect = (snapshot.get("perfectTestModules") as? List<*>)
                        ?.filterIsInstance<String>()?.toSet() ?: emptySet()

                    val remoteDates = (snapshot.get("activeStudyDates") as? List<*>)
                        ?.filterIsInstance<String>()?.toSet() ?: emptySet()

                    val remoteLastCheckIn = snapshot.getString("lastCheckInDate") ?: ""
                    val remoteVerifiedCheckIn = snapshot.getString("verifiedCheckInDate") ?: ""
                    val remoteLastQualifying = snapshot.getString("lastQualifyingStudyDate") ?: ""
                    val remoteCurrentLessonId = snapshot.getString("currentLessonId")?.takeIf { it.isNotBlank() }
                    val remoteFavoriteAreaId = snapshot.getString("favoriteAreaId")?.takeIf { it.isNotBlank() }

                    val remotePhoto = snapshot.getString("photoUrl")?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    if (remotePhoto != null) {
                        activePhotoUrl = remotePhoto
                        prefs.edit().putString("active_user_photo_${userId}", remotePhoto)
                            .putString("permanent_avatar_${cleanEmail}", remotePhoto).apply()
                    }

                    val current = _progressState.value
                    val unionDates = (current.activeStudyDates + remoteDates).toMutableSet()
                    val today = getTodayDateString()
                    val yesterday = getYesterdayDateString()

                    val mergedLastQualifying = when {
                        current.lastQualifyingStudyDate == today || remoteLastQualifying == today -> today
                        current.lastQualifyingStudyDate == yesterday || remoteLastQualifying == yesterday -> yesterday
                        current.lastQualifyingStudyDate.isNotBlank() -> current.lastQualifyingStudyDate
                        else -> remoteLastQualifying
                    }

                    val rawBestStreak = maxOf(current.currentStreak, remoteStreak)
                    val validatedStreak = if (mergedLastQualifying == today || mergedLastQualifying == yesterday) {
                        rawBestStreak
                    } else {
                        0
                    }

                    val mergedCheckIn = if (current.lastCheckInDate == today || remoteLastCheckIn == today) {
                        today
                    } else {
                        current.lastCheckInDate.ifBlank { remoteLastCheckIn }
                    }

                    val mergedVerifiedCheckIn = if (current.verifiedCheckInDate == today || remoteVerifiedCheckIn == today) {
                        today
                    } else {
                        current.verifiedCheckInDate.ifBlank { remoteVerifiedCheckIn }
                    }

                    val mergedExercises = maxOf(current.totalSolvedExercises, remoteExercises)
                    val isZeroActivityUser = maxOf(current.totalXp, remoteXp) == 0 &&
                            (current.completedLessonIds + remoteLessons).isEmpty() &&
                            mergedExercises == 0

                    val finalStreak = if (isZeroActivityUser) 0 else validatedStreak
                    val finalDates = if (isZeroActivityUser) emptySet() else unionDates
                    val finalLastQualifying = if (isZeroActivityUser) "" else mergedLastQualifying
                    val finalCheckIn = if (isZeroActivityUser) "" else mergedCheckIn
                    val finalVerifiedCheckIn = if (isZeroActivityUser) "" else mergedVerifiedCheckIn

                    val merged = current.copy(
                        totalXp = maxOf(current.totalXp, remoteXp),
                        currentStreak = finalStreak,
                        activeStudyDates = finalDates,
                        lastCheckInDate = finalCheckIn,
                        verifiedCheckInDate = finalVerifiedCheckIn,
                        lastQualifyingStudyDate = finalLastQualifying,
                        totalSolvedExercises = mergedExercises,
                        completedLessonIds = current.completedLessonIds + remoteLessons,
                        reviewLessonIds = current.reviewLessonIds + remoteReviews,
                        completedKahootScores = current.completedKahootScores + remoteKahoot,
                        perfectTestModules = current.perfectTestModules + remotePerfect,
                        unlockedBadges = current.unlockedBadges + remoteBadges,
                        currentLessonId = if (current.currentLessonId != "py_b1_l1" || remoteCurrentLessonId == null) current.currentLessonId else remoteCurrentLessonId,
                        favoriteAreaId = if (current.favoriteAreaId != "py_b1" && current.favoriteAreaId.isNotBlank()) current.favoriteAreaId else (remoteFavoriteAreaId ?: current.favoriteAreaId)
                    )
                    val hasPendingSync = prefs.getBoolean("pending_remote_sync_${userId}", false)
                    val hasLocalOfflineGain = current.totalXp > remoteXp ||
                            current.completedLessonIds.size > remoteLessons.size ||
                            current.totalSolvedExercises > remoteExercises
                    val needsRemoteCorrection = (isZeroActivityUser && (remoteStreak > 0 || remoteActiveDays > 0)) ||
                            hasPendingSync || hasLocalOfflineGain
                    saveProgress(merged, pushToRemote = needsRemoteCorrection)
                    // Trigger asynchronous cleanup of any cloned/duplicate documents
                    cleanupDuplicateUserDocuments(userId, cleanEmail)
                } else {
                    // Document does not exist with this ID yet: check by email to recover past version progress!
                    if (cleanEmail.isNotBlank()) {
                        firestore.collection("users").whereEqualTo("email", cleanEmail)
                            .get()
                            .addOnSuccessListener { querySnap ->
                                var highestRemoteXp = 0
                                var highestRemoteStreak = 0
                                var highestRemoteActiveDays = 0
                                val allRemoteLessons = mutableSetOf<String>()
                                val allRemoteBadges = mutableSetOf<String>()
                                val allRemotePerfect = mutableSetOf<String>()
                                val allRemoteDates = mutableSetOf<String>()
                                var bestRemotePhoto: String? = null
                                var remoteLastCheckIn = ""
                                var remoteVerifiedCheckIn = ""
                                var remoteLastQualifying = ""
                                val duplicateDocsToDelete = mutableListOf<String>()

                                for (doc in querySnap.documents) {
                                    val rx = doc.getLong("totalXp")?.toInt() ?: 0
                                    if (rx > highestRemoteXp) highestRemoteXp = rx
                                    val rs = doc.getLong("currentStreak")?.toInt() ?: 0
                                    if (rs > highestRemoteStreak) highestRemoteStreak = rs
                                    val rad = doc.getLong("activeDays")?.toInt() ?: 0
                                    if (rad > highestRemoteActiveDays) highestRemoteActiveDays = rad

                                    (doc.get("completedLessonIds") as? List<*>)?.filterIsInstance<String>()?.let { allRemoteLessons.addAll(it) }
                                    (doc.get("unlockedBadges") as? List<*>)?.filterIsInstance<String>()?.let { allRemoteBadges.addAll(it) }
                                    (doc.get("perfectTestModules") as? List<*>)?.filterIsInstance<String>()?.let { allRemotePerfect.addAll(it) }
                                    (doc.get("activeStudyDates") as? List<*>)?.filterIsInstance<String>()?.let { allRemoteDates.addAll(it) }

                                    val p = doc.getString("photoUrl")?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                                    if (p != null && (bestRemotePhoto == null || bestRemotePhoto == "mascot_sealion")) {
                                        bestRemotePhoto = p
                                    }
                                    val lc = doc.getString("lastCheckInDate") ?: ""
                                    if (lc.isNotBlank()) remoteLastCheckIn = lc
                                    val vc = doc.getString("verifiedCheckInDate") ?: ""
                                    if (vc.isNotBlank()) remoteVerifiedCheckIn = vc
                                    val lq = doc.getString("lastQualifyingStudyDate") ?: ""
                                    if (lq.isNotBlank()) remoteLastQualifying = lq

                                    if (doc.id != userId) {
                                        duplicateDocsToDelete.add(doc.id)
                                    }
                                }

                                if (bestRemotePhoto != null) {
                                    activePhotoUrl = bestRemotePhoto
                                    prefs.edit().putString("active_user_photo_${userId}", bestRemotePhoto)
                                        .putString("permanent_avatar_${cleanEmail}", bestRemotePhoto).apply()
                                }

                                val current = _progressState.value
                                val today = getTodayDateString()
                                val yesterday = getYesterdayDateString()

                                val mergedLastQualifying = when {
                                    current.lastQualifyingStudyDate == today || remoteLastQualifying == today -> today
                                    current.lastQualifyingStudyDate == yesterday || remoteLastQualifying == yesterday -> yesterday
                                    current.lastQualifyingStudyDate.isNotBlank() -> current.lastQualifyingStudyDate
                                    else -> remoteLastQualifying
                                }

                                val rawBestStreak = maxOf(current.currentStreak, highestRemoteStreak)
                                val validatedStreak = if (mergedLastQualifying == today || mergedLastQualifying == yesterday) {
                                    rawBestStreak
                                } else {
                                    0
                                }

                                val unionDates = (current.activeStudyDates + allRemoteDates).toMutableSet()

                                val mergedCheckIn = if (current.lastCheckInDate == today || remoteLastCheckIn == today) {
                                    today
                                } else {
                                    current.lastCheckInDate.ifBlank { remoteLastCheckIn }
                                }

                                val mergedVerifiedCheckIn = if (current.verifiedCheckInDate == today || remoteVerifiedCheckIn == today) {
                                    today
                                } else {
                                    current.verifiedCheckInDate.ifBlank { remoteVerifiedCheckIn }
                                }

                                val isZeroActivity = maxOf(current.totalXp, highestRemoteXp) == 0 &&
                                        (current.completedLessonIds + allRemoteLessons).isEmpty() &&
                                        current.totalSolvedExercises == 0

                                val finalStreak = if (isZeroActivity) 0 else validatedStreak
                                val finalDates = if (isZeroActivity) emptySet() else unionDates
                                val finalLastQualifying = if (isZeroActivity) "" else mergedLastQualifying
                                val finalCheckIn = if (isZeroActivity) "" else mergedCheckIn
                                val finalVerifiedCheckIn = if (isZeroActivity) "" else mergedVerifiedCheckIn

                                val merged = current.copy(
                                    totalXp = maxOf(current.totalXp, highestRemoteXp),
                                    currentStreak = finalStreak,
                                    activeStudyDates = finalDates,
                                    lastCheckInDate = finalCheckIn,
                                    verifiedCheckInDate = finalVerifiedCheckIn,
                                    lastQualifyingStudyDate = finalLastQualifying,
                                    completedLessonIds = current.completedLessonIds + allRemoteLessons,
                                    unlockedBadges = current.unlockedBadges + allRemoteBadges,
                                    perfectTestModules = current.perfectTestModules + allRemotePerfect
                                )
                                saveProgress(merged, pushToRemote = true)

                                // Permanently delete duplicate cloned documents
                                for (dupId in duplicateDocsToDelete) {
                                    firestore.collection("users").document(dupId).delete()
                                        .addOnSuccessListener {
                                            Log.i("UserProgressRepo", "Deleted duplicate account document: $dupId")
                                        }
                                }
                                if (userId != "dev_contatopedro21g" && cleanEmail == "contatopedro21g@gmail.com") {
                                    firestore.collection("users").document("dev_contatopedro21g").delete()
                                }
                            }
                            .addOnFailureListener {
                                pushCurrentProgressToFirestore(userId, email, displayName)
                            }
                    } else {
                        pushCurrentProgressToFirestore(userId, email, displayName)
                    }
                }
            }
    }

    private fun cleanupDuplicateUserDocuments(activeId: String, email: String) {
        val firestore = db ?: return
        val cleanEmail = email.trim().lowercase()
        if (cleanEmail.isBlank()) return

        firestore.collection("users").whereEqualTo("email", cleanEmail)
            .get()
            .addOnSuccessListener { snap ->
                var highestXp = _progressState.value.totalXp
                var bestPhoto: String? = activePhotoUrl?.takeIf { it.isNotBlank() && it != "mascot_sealion" }

                for (doc in snap.documents) {
                    val rx = doc.getLong("totalXp")?.toInt() ?: 0
                    if (rx > highestXp) highestXp = rx
                    val p = doc.getString("photoUrl")?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    if (p != null && (bestPhoto == null || bestPhoto == "mascot_sealion")) {
                        bestPhoto = p
                    }
                    if (doc.id != activeId) {
                        firestore.collection("users").document(doc.id).delete()
                            .addOnSuccessListener {
                                Log.i("UserProgressRepo", "Removido clone do usuário no Firestore: ${doc.id}")
                            }
                    }
                }

                if (activeId != "dev_contatopedro21g" && cleanEmail == "contatopedro21g@gmail.com") {
                    firestore.collection("users").document("dev_contatopedro21g").delete()
                }

                var needSave = false
                var current = _progressState.value
                if (bestPhoto != null && bestPhoto != activePhotoUrl) {
                    activePhotoUrl = bestPhoto
                    prefs.edit().putString("active_user_photo_${activeId}", bestPhoto)
                        .putString("permanent_avatar_${cleanEmail}", bestPhoto).apply()
                    needSave = true
                }
                if (highestXp > current.totalXp) {
                    current = current.copy(totalXp = highestXp)
                    needSave = true
                }
                if (needSave) {
                    saveProgress(current, pushToRemote = true)
                }
            }
    }

    fun updateUserProfile(displayName: String?, photoUrl: String?) {
        if (!displayName.isNullOrBlank()) activeDisplayName = displayName
        if (!photoUrl.isNullOrBlank()) {
            activePhotoUrl = photoUrl
            activeUserId?.let { uid ->
                prefs.edit().putString("active_user_photo_${uid}", photoUrl).apply()
            }
            activeUserEmail?.let { em ->
                val clean = em.trim().lowercase()
                if (clean.isNotBlank()) {
                    prefs.edit().putString("permanent_avatar_${clean}", photoUrl).apply()
                }
            }
        }
        pushCurrentProgressToFirestore()

        // Immediately update local leaderboard state for instant UI responsiveness
        val currentList = _onlineLeaderboardState.value.toMutableList()
        val uid = activeUserId
        if (uid != null) {
            val idx = currentList.indexOfFirst { it.userId == uid }
            if (idx >= 0) {
                currentList[idx] = currentList[idx].copy(
                    displayName = activeDisplayName ?: currentList[idx].displayName,
                    photoUrl = photoUrl ?: currentList[idx].photoUrl
                )
            } else {
                currentList.add(
                    GlobalLeaderboardUser(
                        userId = uid,
                        displayName = activeDisplayName ?: "Estudante",
                        email = activeUserEmail ?: "",
                        photoUrl = photoUrl,
                        totalXp = _progressState.value.totalXp,
                        level = _progressState.value.level,
                        streak = _progressState.value.currentStreak,
                        activeDays = _progressState.value.activeStudyDaysCount
                    )
                )
            }
            currentList.sortByDescending { it.totalXp }
            _onlineLeaderboardState.value = currentList
            saveCachedLeaderboard(currentList)
        }
    }

    fun getActivePhotoForUser(userId: String?): String? {
        if (userId.isNullOrBlank()) return null
        if (userId == activeUserId && !activePhotoUrl.isNullOrBlank()) return activePhotoUrl
        val cached = prefs.getString("active_user_photo_${userId}", null)?.takeIf { it.isNotBlank() }
        if (cached != null) return cached

        val emailCached = activeUserEmail?.let { em ->
            prefs.getString("permanent_avatar_${em.trim().lowercase()}", null)?.takeIf { it.isNotBlank() }
        }
        if (emailCached != null) return emailCached

        // Check user_custom_profiles and knowverse_user_custom_profiles
        try {
            val sp1 = appContext.getSharedPreferences("knowverse_user_custom_profiles", Context.MODE_PRIVATE)
            val p1 = sp1.getString("${userId}_photo", null)?.takeIf { it.isNotBlank() }
            if (p1 != null) return p1
            val p1Email = activeUserEmail?.let { em -> sp1.getString("${em.trim().lowercase()}_photo", null)?.takeIf { it.isNotBlank() } }
            if (p1Email != null) return p1Email

            val sp2 = appContext.getSharedPreferences("user_custom_profiles", Context.MODE_PRIVATE)
            val p2 = sp2.getString("${userId}_photo", null)?.takeIf { it.isNotBlank() }
            if (p2 != null) return p2

            // Check if an avatar file exists for this user in filesDir
            val avatarFiles = appContext.filesDir.listFiles { f ->
                (f.name.startsWith("avatar_${userId}") || f.name.startsWith("avatar_")) && f.length() > 0
            }
            val latest = avatarFiles?.maxByOrNull { it.lastModified() }
            if (latest != null && latest.exists()) {
                return latest.absolutePath
            }
        } catch (_: Exception) {}

        return null
    }

    fun clearUserSession() {
        firestoreListener?.remove()
        firestoreListener = null
        activeUserId = null
        activeUserEmail = null
        activeDisplayName = null
        activePhotoUrl = null
        val fresh = UserProgress(
            lastActiveDate = getTodayDateString(),
            activeStudyDates = emptySet(),
            lastCheckInDate = "",
            verifiedCheckInDate = ""
        )
        _progressState.value = fresh
        updateAchievements(fresh)
    }

    fun deleteUserData(userId: String) {
        val key = "user_progress_data_$userId"
        prefs.edit().remove(key).apply()
        if (activeUserId == userId) {
            val fresh = UserProgress(
                lastActiveDate = getTodayDateString(),
                activeStudyDates = emptySet(),
                lastCheckInDate = "",
                verifiedCheckInDate = ""
            )
            _progressState.value = fresh
            updateAchievements(fresh)
            activeUserId = null
            activeUserEmail = null
            activeDisplayName = null
            activePhotoUrl = null
        }
        scope.launch {
            try {
                db?.collection("users")?.document(userId)?.delete()
            } catch (e: Exception) {
                Log.w("UserProgressRepo", "Error deleting Firestore data: ${e.message}")
            }
        }
    }

    private fun pushCurrentProgressToFirestore(
        userId: String? = activeUserId,
        email: String? = activeUserEmail,
        displayName: String? = activeDisplayName
    ) {
        if (userId.isNullOrBlank()) return

        val current = _progressState.value
        val isDev = email?.equals("contatopedro21g@gmail.com", ignoreCase = true) == true
        val resolvedPhoto = activePhotoUrl ?: prefs.getString("active_user_photo_${userId}", null)

        val docData = hashMapOf<String, Any>(
            "userId" to userId,
            "email" to (email ?: "usuario@knowverse.com"),
            "displayName" to (displayName ?: "Estudante"),
            "isDev" to isDev,
            "totalXp" to current.totalXp,
            "level" to current.level,
            "currentStreak" to current.currentStreak,
            "activeDays" to current.activeStudyDaysCount,
            "activeStudyDates" to current.activeStudyDates.toList(),
            "lastCheckInDate" to current.lastCheckInDate,
            "verifiedCheckInDate" to current.verifiedCheckInDate,
            "completedLessonIds" to current.completedLessonIds.toList(),
            "reviewLessonIds" to current.reviewLessonIds.toList(),
            "completedKahootScores" to current.completedKahootScores,
            "perfectTestModules" to current.perfectTestModules.toList(),
            "unlockedBadges" to current.unlockedBadges.toList(),
            "totalSolvedExercises" to current.totalSolvedExercises,
            "currentLessonId" to current.currentLessonId,
            "favoriteAreaId" to current.favoriteAreaId,
            "lastActiveDate" to current.lastActiveDate,
            "lastQualifyingStudyDate" to current.lastQualifyingStudyDate,
            "updatedAt" to FieldValue.serverTimestamp()
        )
        if (!resolvedPhoto.isNullOrBlank()) {
            docData["photoUrl"] = resolvedPhoto
        }

        prefs.edit().putBoolean("pending_remote_sync_${userId}", true).apply()
        scope.launch {
            try {
                db?.collection("users")?.document(userId)
                    ?.set(docData, SetOptions.merge())
                    ?.addOnSuccessListener {
                        prefs.edit().putBoolean("pending_remote_sync_${userId}", false).apply()
                    }
            } catch (e: Exception) {
                Log.w("UserProgressRepo", "Failed to sync user doc to Firestore: ${e.message}")
            }
        }
    }

    private fun loadCachedLeaderboard() {
        try {
            val raw = prefs.getString("cached_online_leaderboard", null) ?: return
            val arr = JSONArray(raw)
            val list = mutableListOf<GlobalLeaderboardUser>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                list.add(
                    GlobalLeaderboardUser(
                        userId = obj.getString("userId"),
                        displayName = obj.getString("displayName"),
                        email = obj.optString("email", ""),
                        photoUrl = obj.optString("photoUrl", "").ifBlank { null },
                        totalXp = obj.getInt("totalXp"),
                        level = obj.optInt("level", 1),
                        streak = obj.optInt("streak", 0),
                        activeDays = obj.optInt("activeDays", 0)
                    )
                )
            }
            if (list.isNotEmpty()) {
                _onlineLeaderboardState.value = list
            }
        } catch (_: Exception) {}
    }

    private fun saveCachedLeaderboard(list: List<GlobalLeaderboardUser>) {
        try {
            val arr = JSONArray()
            for (u in list) {
                val obj = JSONObject().apply {
                    put("userId", u.userId)
                    put("displayName", u.displayName)
                    put("email", u.email)
                    put("photoUrl", u.photoUrl ?: "")
                    put("totalXp", u.totalXp)
                    put("level", u.level)
                    put("streak", u.streak)
                    put("activeDays", u.activeDays)
                }
                arr.put(obj)
            }
            prefs.edit().putString("cached_online_leaderboard", arr.toString()).apply()
        } catch (_: Exception) {}
    }

    private fun startGlobalLeaderboardListener() {
        loadCachedLeaderboard()
        val firestore = db ?: return
        leaderboardListener?.remove()
        try {
            leaderboardListener = firestore.collection("users")
                .limit(100)
                .addSnapshotListener { snapshot, error ->
                    if (error != null) {
                        Log.w("UserProgressRepo", "Global leaderboard listener warning: ${error.message}")
                        return@addSnapshotListener
                    }
                    if (snapshot != null) {
                        val userMap = mutableMapOf<String, GlobalLeaderboardUser>()
                        for (doc in snapshot.documents) {
                            try {
                                val uid = doc.getString("userId") ?: doc.id
                                val name = doc.getString("displayName") ?: "Estudante"
                                val em = (doc.getString("email") ?: "").trim().lowercase()
                                val photo = doc.getString("photoUrl")?.takeIf { it.isNotBlank() }
                                val xp = doc.getLong("totalXp")?.toInt() ?: 0
                                val lvl = doc.getLong("level")?.toInt() ?: ((xp / 100) + 1)
                                val strk = doc.getLong("currentStreak")?.toInt() ?: 0
                                val days = doc.getLong("activeDays")?.toInt() ?: 0
                                val candidate = GlobalLeaderboardUser(uid, name, em, photo, xp, lvl, strk, days)

                                val key = if (em.isNotBlank()) em else uid
                                val existing = userMap[key]
                                if (existing == null) {
                                    userMap[key] = candidate
                                } else {
                                    val bestPhoto = candidate.photoUrl?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                                        ?: existing.photoUrl?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                                        ?: candidate.photoUrl
                                        ?: existing.photoUrl

                                    userMap[key] = existing.copy(
                                        photoUrl = bestPhoto,
                                        totalXp = maxOf(existing.totalXp, candidate.totalXp),
                                        level = maxOf(existing.level, candidate.level),
                                        streak = maxOf(existing.streak, candidate.streak),
                                        activeDays = maxOf(existing.activeDays, candidate.activeDays)
                                    )

                                    val activeId = activeUserId
                                    val activeEm = activeUserEmail?.trim()?.lowercase()
                                    if (activeId != null && em == activeEm) {
                                        val staleId = if (candidate.userId != activeId) candidate.userId else existing.userId
                                        if (staleId != activeId) {
                                            firestore.collection("users").document(staleId).delete()
                                        }
                                    }
                                }
                            } catch (_: Exception) {}
                        }

                        val list = userMap.values.sortedWith(
                            compareByDescending<GlobalLeaderboardUser> { it.totalXp }
                                .thenByDescending { it.activeDays }
                        )

                        if (list.isNotEmpty()) {
                            val mergedList = list.toMutableList()
                            val activeId = activeUserId
                            val activeEm = activeUserEmail?.trim()?.lowercase()
                            if (activeId != null) {
                                val localXp = _progressState.value.totalXp
                                val localPhoto = activePhotoUrl ?: prefs.getString("active_user_photo_${activeId}", null)
                                val idx = mergedList.indexOfFirst {
                                    it.userId == activeId || (activeEm != null && activeEm.isNotBlank() && it.email.trim().lowercase() == activeEm)
                                }
                                if (idx >= 0) {
                                    val item = mergedList[idx]
                                    mergedList[idx] = item.copy(
                                        userId = activeId,
                                        displayName = activeDisplayName ?: item.displayName,
                                        totalXp = maxOf(item.totalXp, localXp),
                                        photoUrl = localPhoto?.takeIf { it != "mascot_sealion" } ?: item.photoUrl
                                    )
                                } else {
                                    mergedList.add(
                                        GlobalLeaderboardUser(
                                            userId = activeId,
                                            displayName = activeDisplayName ?: "Estudante",
                                            email = activeUserEmail ?: "",
                                            photoUrl = localPhoto,
                                            totalXp = localXp,
                                            level = _progressState.value.level,
                                            streak = _progressState.value.currentStreak,
                                            activeDays = _progressState.value.activeStudyDaysCount
                                        )
                                    )
                                }
                                mergedList.sortWith(
                                    compareByDescending<GlobalLeaderboardUser> { it.totalXp }
                                        .thenByDescending { it.activeDays }
                                )
                            }
                            saveCachedLeaderboard(mergedList)
                            _onlineLeaderboardState.value = mergedList
                        }
                    }
                }
        } catch (e: Exception) {
            Log.w("UserProgressRepo", "Error starting global leaderboard listener: ${e.message}")
        }
    }

    fun completeLesson(lessonId: String, xpEarned: Int): Int {
        val current = _progressState.value
        val today = getTodayDateString()
        val isFirstTime = !current.completedLessonIds.contains(lessonId)
        val newCompleted = current.completedLessonIds + lessonId
        val newReviews = current.reviewLessonIds - lessonId
        // XP is ONLY awarded once per lesson
        val actualXp = if (isFirstTime) xpEarned else 0

        val updatedBadges = current.unlockedBadges.toMutableSet()
        var bonusBadgeXp = 0
        fun checkSubjectBadge(badgeId: String, prefixes: List<String>, required: Int, bonus: Int) {
            val matchCount = newCompleted.count { id ->
                prefixes.any { p -> id.startsWith(p) || id.contains(p) }
            }
            if (!updatedBadges.contains(badgeId) && matchCount >= required) {
                updatedBadges.add(badgeId)
                bonusBadgeXp += bonus
                val badgeObj = TrackBadgesCatalog.findBadge(badgeId)
                AchievementAlertManager.postAlert(
                    InAppAchievementAlert(
                        title = badgeObj?.title ?: "Emblema de Trilha!",
                        description = badgeObj?.description ?: "Maestria na disciplina!",
                        xpBonus = bonus,
                        drawableRes = badgeObj?.drawableRes,
                        category = "NOVO EMBLEMA DE TRILHA!"
                    )
                )
            }
        }

        checkSubjectBadge("badge_enem_chemistry", listOf("quim_", "ch_", "quimica"), 3, 400)
        checkSubjectBadge("badge_enem_geography", listOf("geo_", "geografia"), 3, 350)
        checkSubjectBadge("badge_enem_history", listOf("hist_", "historia"), 3, 350)
        checkSubjectBadge("badge_enem_languages", listOf("ling_", "linguagens"), 3, 350)
        checkSubjectBadge("badge_enem_physics", listOf("fis_", "fisica"), 3, 400)
        checkSubjectBadge("badge_enem_biology", listOf("bio_", "biologia"), 3, 400)
        checkSubjectBadge("badge_enem_math", listOf("enem_", "mb_", "mat_", "matematica"), 4, 500)

        val inferredArea = inferAreaIdFromLessonId(lessonId)
        val updated = current.copy(
            totalXp = current.totalXp + actualXp + bonusBadgeXp,
            dailyXp = current.dailyXp + actualXp + bonusBadgeXp,
            lastActiveDate = today,
            activeStudyDates = current.activeStudyDates + today,
            lastCheckInDate = today,
            verifiedCheckInDate = today,
            completedLessonIds = newCompleted,
            reviewLessonIds = newReviews,
            unlockedBadges = updatedBadges,
            currentLessonId = lessonId,
            favoriteAreaId = inferredArea ?: current.favoriteAreaId,
            totalSolvedExercises = if (isFirstTime) current.totalSolvedExercises + 1 else current.totalSolvedExercises
        )
        saveProgress(updated)

        // REGRA ESTRITA: Fazer uma aula em 'Trilhas' avança a sequência (streak) em 1 dia
        recordQualifyingStudyActivity(source = "Trilha: $lessonId", xpEarned = actualXp + bonusBadgeXp)

        updateAchievements(_progressState.value)
        return actualXp
    }

    fun setCurrentLesson(lessonId: String, favoriteAreaId: String? = null) {
        val cleanId = lessonId.trim()
        if (cleanId.isBlank()) return
        val current = _progressState.value
        val inferredArea = favoriteAreaId ?: inferAreaIdFromLessonId(cleanId) ?: current.favoriteAreaId
        if (current.currentLessonId != cleanId || current.favoriteAreaId != inferredArea) {
            val updated = current.copy(
                currentLessonId = cleanId,
                favoriteAreaId = inferredArea
            )
            saveProgress(updated)
        }
    }

    private fun inferAreaIdFromLessonId(lessonId: String): String? {
        return when {
            lessonId.startsWith("py_") -> "python"
            lessonId.startsWith("mb_") || lessonId.startsWith("enem_") || lessonId.startsWith("calc_") -> "matematica"
            lessonId.startsWith("fis_") -> "fisica"
            lessonId.startsWith("bio_") -> "biologia"
            lessonId.startsWith("quim_") -> "quimica"
            lessonId.startsWith("geo_") || lessonId.startsWith("hist_") -> "humanas"
            lessonId.startsWith("ling_") -> "linguagens"
            else -> null
        }
    }

    /**
     * REGRA ESTRITA DE STREAK:
     * SE E SOMENTE SE o usuário fizer uma aula em 'Trilhas' ou um exercício, a sequência sobe 1 dia.
     * Caso o usuário não faça nada disso até as 00h00 GMT-3 do dia, o streak zera (0d).
     */
    fun recordQualifyingStudyActivity(source: String, xpEarned: Int = 0): Int {
        checkStreakExpiration()
        val current = _progressState.value
        val today = getTodayDateString()
        val yesterday = getYesterdayDateString()
        val lastQualifying = current.lastQualifyingStudyDate

        val newStreak = when {
            lastQualifying == today -> {
                // Já qualificou hoje: mantém a sequência atual (não incrementa duas vezes no mesmo dia)
                current.currentStreak
            }
            lastQualifying == yesterday && current.currentStreak > 0 -> {
                // Consecutivo perfeito: avança exatamente 1 dia de sequência!
                current.currentStreak + 1
            }
            else -> {
                // Começando uma nova sequência ou após expiração/0d
                1
            }
        }

        val updatedBadges = current.unlockedBadges.toMutableSet()
        var bonusBadgeXp = 0
        fun checkStreakBadge(badgeId: String, required: Int, bonus: Int) {
            if (newStreak >= required && !updatedBadges.contains(badgeId)) {
                updatedBadges.add(badgeId)
                bonusBadgeXp += bonus
                val badgeObj = TrackBadgesCatalog.findBadge(badgeId)
                AchievementAlertManager.postAlert(
                    InAppAchievementAlert(
                        title = badgeObj?.title ?: "Emblema de Foco!",
                        description = badgeObj?.description ?: "Parabéns pela sequência de $required dias!",
                        xpBonus = bonus,
                        drawableRes = badgeObj?.drawableRes,
                        category = "EMBLEMA DE SEQUÊNCIA!"
                    )
                )
            }
        }
        checkStreakBadge("badge_streak_3", 3, 150)
        checkStreakBadge("badge_streak_7", 7, 300)
        checkStreakBadge("badge_streak_14", 14, 500)
        checkStreakBadge("badge_streak_30", 30, 1000)

        val updated = current.copy(
            totalXp = current.totalXp + bonusBadgeXp,
            dailyXp = current.dailyXp + bonusBadgeXp,
            currentStreak = newStreak,
            lastQualifyingStudyDate = today,
            lastActiveDate = today,
            lastCheckInDate = today,
            verifiedCheckInDate = today,
            activeStudyDates = current.activeStudyDates + today,
            unlockedBadges = updatedBadges
        )
        saveProgress(updated)
        updateAchievements(updated)

        Log.i("UserProgressRepo", "Atividade qualificadora ($source): streak atualizado para $newStreak d (hoje: $today GMT-3)")
        return newStreak
    }

    fun recordCheckIn(awardBonusXp: Boolean = true): CheckInResult {
        checkStreakExpiration()
        val current = _progressState.value
        val today = getTodayDateString()
        val isNewToday = current.verifiedCheckInDate != today
        val xpBonus = if (isNewToday && awardBonusXp) 10 else 0

        val updated = current.copy(
            lastCheckInDate = today,
            verifiedCheckInDate = today,
            lastActiveDate = today,
            totalXp = current.totalXp + xpBonus,
            dailyXp = current.dailyXp + xpBonus
        )
        saveProgress(updated)
        updateAchievements(updated)
        return CheckInResult(
            isNewCheckInToday = isNewToday,
            streak = updated.currentStreak,
            totalActiveDays = updated.activeStudyDaysCount,
            xpAwarded = xpBonus
        )
    }

    fun recordTestScore(moduleId: String, score: Int, xpEarned: Int, isPerfectScore: Boolean = false) {
        recordKahootScore(moduleId, score, xpEarned, isPerfectScore)
    }

    fun recordKahootScore(moduleId: String, score: Int, xpEarned: Int, isPerfectScore: Boolean = false) {
        // REGRA ESTRITA: Finalizar teste/quiz de módulo avança o streak!
        recordQualifyingStudyActivity(source = "Teste: $moduleId", xpEarned = xpEarned)

        val current = _progressState.value
        val updatedScores = current.completedKahootScores.toMutableMap()
        val previous = updatedScores[moduleId] ?: 0
        if (score > previous) {
            updatedScores[moduleId] = score
        }

        val updatedPerfect = if (isPerfectScore) {
            current.perfectTestModules + moduleId
        } else {
            current.perfectTestModules
        }

        // Award Module Badges & generous XP when a module's final test is completed with perfect score
        val badgeToAward: String? = when {
            moduleId == "test_math_basic" || moduleId.contains("math_basic") -> "badge_math_basic"
            moduleId.contains("basic") || moduleId == "kahoot_basic" -> "badge_python_basic"
            moduleId.contains("intermediate") || moduleId == "kahoot_intermediate" -> "badge_python_intermediate"
            moduleId.contains("advanced") || moduleId == "kahoot_advanced" -> "badge_python_advanced"
            moduleId.contains("derivative") || moduleId == "test_calculus_derivatives" -> "badge_calculus_derivatives"
            moduleId.contains("integral") || moduleId == "test_calculus_integrals" -> "badge_calculus_integrals"
            moduleId.contains("calculus") || moduleId.contains("limits") || moduleId == "test_calculus_limits" -> "badge_calculus_limits"
            else -> null
        }

        val isNewBadge = isPerfectScore && badgeToAward != null && !current.unlockedBadges.contains(badgeToAward)
        val badgeXpBonus = if (isNewBadge) {
            when (badgeToAward) {
                "badge_math_basic" -> 250
                "badge_python_basic" -> 250
                "badge_python_intermediate" -> 350
                "badge_python_advanced" -> 500
                "badge_calculus_limits" -> 300
                "badge_calculus_derivatives" -> 400
                "badge_calculus_integrals" -> 450
                else -> 100
            }
        } else 0

        val updatedBadges = if (isNewBadge) current.unlockedBadges + badgeToAward!! else current.unlockedBadges
        val totalXpToAdd = xpEarned + badgeXpBonus

        val updated = current.copy(
            totalXp = current.totalXp + totalXpToAdd,
            dailyXp = current.dailyXp + totalXpToAdd,
            completedKahootScores = updatedScores,
            perfectTestModules = updatedPerfect,
            unlockedBadges = updatedBadges
        )
        saveProgress(updated)

        if (isNewBadge) {
            val badgeObj = TrackBadgesCatalog.findBadge(badgeToAward!!)
            AchievementAlertManager.postAlert(
                InAppAchievementAlert(
                    title = badgeObj?.title ?: "Emblema Conquistado!",
                    description = badgeObj?.description ?: "Parabéns pelo gabarito no Teste Final!",
                    xpBonus = badgeXpBonus,
                    drawableRes = badgeObj?.drawableRes,
                    category = "NOVO EMBLEMA DE MESTRE!"
                )
            )
        }
    }

    fun awardModuleBadge(badgeId: String, xpAmount: Int) {
        val current = _progressState.value
        if (!current.unlockedBadges.contains(badgeId)) {
            val updated = current.copy(
                unlockedBadges = current.unlockedBadges + badgeId,
                totalXp = current.totalXp + xpAmount,
                dailyXp = current.dailyXp + xpAmount
            )
            saveProgress(updated)
            val badgeObj = TrackBadgesCatalog.findBadge(badgeId)
            AchievementAlertManager.postAlert(
                InAppAchievementAlert(
                    title = badgeObj?.title ?: "Emblema Desbloqueado!",
                    description = badgeObj?.description ?: "Você concluiu este módulo com maestria!",
                    xpBonus = xpAmount,
                    drawableRes = badgeObj?.drawableRes,
                    category = "EMBLEMA DE MÓDULO!"
                )
            )
        }
    }

    fun addBonusXp(xp: Int, source: String = "Exercício") {
        val current = _progressState.value
        val newExerciseCount = current.totalSolvedExercises + 1
        val updatedBadges = current.unlockedBadges.toMutableSet()
        var bonusBadgeXp = 0

        fun checkExerciseBadge(badgeId: String, required: Int, bonus: Int) {
            if (newExerciseCount >= required && !updatedBadges.contains(badgeId)) {
                updatedBadges.add(badgeId)
                bonusBadgeXp += bonus
                val badgeObj = TrackBadgesCatalog.findBadge(badgeId)
                AchievementAlertManager.postAlert(
                    InAppAchievementAlert(
                        title = badgeObj?.title ?: "Emblema Desbloqueado!",
                        description = badgeObj?.description ?: "Parabéns por atingir $required exercícios!",
                        xpBonus = bonus,
                        drawableRes = badgeObj?.drawableRes,
                        category = "EMBLEMA DE EXERCÍCIOS!"
                    )
                )
            }
        }

        checkExerciseBadge("badge_exercises_10", 10, 150)
        checkExerciseBadge("badge_exercises_25", 25, 250)
        checkExerciseBadge("badge_exercises_50", 50, 450)
        checkExerciseBadge("badge_exercises_100", 100, 1000)
        checkExerciseBadge("badge_exercises_250", 250, 1500)
        checkExerciseBadge("badge_exercises_500", 500, 2500)
        checkExerciseBadge("badge_exercises_1000", 1000, 5000)
        checkExerciseBadge("badge_exercises_2000", 2000, 10000)

        val updated = current.copy(
            totalXp = current.totalXp + xp + bonusBadgeXp,
            dailyXp = current.dailyXp + xp + bonusBadgeXp,
            totalSolvedExercises = newExerciseCount,
            unlockedBadges = updatedBadges
        )
        saveProgress(updated)

        // REGRA ESTRITA: Resolver exercício avança a sequência (streak) em 1 dia
        recordQualifyingStudyActivity(source = source, xpEarned = xp + bonusBadgeXp)

        updateAchievements(_progressState.value)
    }

    fun recordSolvedExercise(exerciseId: String, xpEarned: Int): Boolean {
        val cleanId = exerciseId.trim()
        if (cleanId.isNotEmpty()) {
            clearWrongQuestion(cleanId)
            val userKey = activeUserId ?: "guest"
            val solvedKey = "solved_exercise_ids_$userKey"
            val solvedSet = (prefs.getStringSet(solvedKey, emptySet()) ?: emptySet()).toMutableSet()
            if (!solvedSet.add(cleanId)) {
                // Exercício já contabilizado anteriormente para este usuário: evita duplicação de XP
                return false
            }
            prefs.edit().putStringSet(solvedKey, solvedSet).apply()
        }
        addBonusXp(xpEarned, "Exercício: $cleanId")
        return true
    }

    fun recordWrongQuestion(questionId: String, lessonId: String? = null) {
        val cleanQId = questionId.trim()
        if (cleanQId.isNotEmpty()) {
            val userKey = activeUserId ?: "guest"
            val wrongKey = "wrong_question_ids_$userKey"
            val wrongSet = (prefs.getStringSet(wrongKey, emptySet()) ?: emptySet()).toMutableSet()
            if (wrongSet.add(cleanQId)) {
                prefs.edit().putStringSet(wrongKey, wrongSet).apply()
            }
        }
        if (!lessonId.isNullOrBlank()) {
            markLessonForReview(lessonId.trim())
        }
    }

    fun clearWrongQuestion(questionId: String) {
        val cleanQId = questionId.trim()
        if (cleanQId.isEmpty()) return
        val userKey = activeUserId ?: "guest"
        val wrongKey = "wrong_question_ids_$userKey"
        val wrongSet = (prefs.getStringSet(wrongKey, emptySet()) ?: emptySet()).toMutableSet()
        if (wrongSet.remove(cleanQId)) {
            prefs.edit().putStringSet(wrongKey, wrongSet).apply()
        }
    }

    fun getWrongQuestionIds(): Set<String> {
        val userKey = activeUserId ?: "guest"
        val wrongKey = "wrong_question_ids_$userKey"
        return prefs.getStringSet(wrongKey, emptySet()) ?: emptySet()
    }

    fun markLessonForReview(lessonId: String) {
        val current = _progressState.value
        if (!current.reviewLessonIds.contains(lessonId)) {
            val updated = current.copy(reviewLessonIds = current.reviewLessonIds + lessonId)
            saveProgress(updated)
        }
    }

    fun clearLessonReview(lessonId: String) {
        val current = _progressState.value
        if (current.reviewLessonIds.contains(lessonId)) {
            val updated = current.copy(reviewLessonIds = current.reviewLessonIds - lessonId)
            saveProgress(updated)
        }
    }

    fun recordExecution() {
        val current = _progressState.value
        val updated = current.copy(totalExecutions = current.totalExecutions + 1)
        saveProgress(updated)
        updateAchievements(updated)
    }

    fun saveUserCode(lessonId: String, code: String) {
        prefs.edit().putString("saved_code_$lessonId", code).apply()
    }

    fun getSavedUserCode(lessonId: String): String? {
        return prefs.getString("saved_code_$lessonId", null)
    }

    private fun saveProgress(progress: UserProgress, pushToRemote: Boolean = true) {
        _progressState.value = progress
        val json = serializeProgressToJson(progress)
        val key = if (!activeUserId.isNullOrBlank()) "user_progress_data_${activeUserId}" else "user_progress_data_guest"
        val editor = prefs.edit().putString(key, json.toString())

        // PERSISTÊNCIA REFORÇADA: Salva campos vitais em chaves de backup independentes
        // para que atualizações do app nunca percam o streak, XP ou emblemas conquistados
        val backupPrefix = if (!activeUserId.isNullOrBlank()) "backup_user_${activeUserId}_" else "backup_guest_"
        editor.putInt("${backupPrefix}streak", progress.currentStreak)
            .putString("${backupPrefix}last_qualifying", progress.lastQualifyingStudyDate)
            .putInt("${backupPrefix}total_xp", progress.totalXp)
            .putInt("${backupPrefix}exercises", progress.totalSolvedExercises)
            .putStringSet("${backupPrefix}unlocked_badges", progress.unlockedBadges)
            .putStringSet("${backupPrefix}completed_lessons", progress.completedLessonIds)
            .apply()

        if (pushToRemote && activeUserId != null) {
            pushCurrentProgressToFirestore()
        }
    }

    fun getProgressForUser(userId: String): UserProgress {
        val key = "user_progress_data_$userId"
        var raw = prefs.getString(key, null)

        // Compatibilidade legada e persistência pós-atualização: recupera de chaves alternativas
        if (raw.isNullOrBlank() && !activeUserEmail.isNullOrBlank()) {
            raw = prefs.getString("user_progress_data_${activeUserEmail!!.trim().lowercase()}", null)
        }
        if (raw.isNullOrBlank()) {
            val legacySingle = prefs.getString("user_progress_data", null)
                ?: prefs.getString("user_progress_data_guest", null)
                ?: appContext.getSharedPreferences("knowverse_prefs", Context.MODE_PRIVATE).getString("user_progress_data", null)
            if (!legacySingle.isNullOrBlank()) {
                val parsedLegacy = parseProgressJson(legacySingle)
                if (parsedLegacy.totalXp > 0 || parsedLegacy.completedLessonIds.isNotEmpty()) {
                    prefs.edit().putString(key, legacySingle).apply()
                    return parsedLegacy
                }
            }
        }

        // Se o JSON estiver ausente, tenta restaurar das chaves de backup reforçadas
        if (raw.isNullOrBlank()) {
            val backupPrefix = if (userId.isNotBlank()) "backup_user_${userId}_" else "backup_guest_"
            val backupStreak = prefs.getInt("${backupPrefix}streak", -1)
            if (backupStreak >= 0) {
                val backupLastQualifying = prefs.getString("${backupPrefix}last_qualifying", "") ?: ""
                val backupXp = prefs.getInt("${backupPrefix}total_xp", 0)
                val backupExercises = prefs.getInt("${backupPrefix}exercises", 0)
                val backupBadges = prefs.getStringSet("${backupPrefix}unlocked_badges", emptySet()) ?: emptySet()
                val backupLessons = prefs.getStringSet("${backupPrefix}completed_lessons", emptySet()) ?: emptySet()

                val isZeroActivityBackup = backupXp == 0 && backupLessons.isEmpty() && backupExercises == 0
                val cleanStreak = if (isZeroActivityBackup) 0 else backupStreak
                val cleanLastQualifying = if (isZeroActivityBackup) "" else backupLastQualifying

                val restored = UserProgress(
                    totalXp = backupXp,
                    currentStreak = cleanStreak,
                    lastQualifyingStudyDate = cleanLastQualifying,
                    totalSolvedExercises = backupExercises,
                    unlockedBadges = backupBadges,
                    completedLessonIds = backupLessons,
                    lastActiveDate = getTodayDateString()
                )
                val json = serializeProgressToJson(restored)
                prefs.edit().putString(key, json.toString()).apply()
                return restored
            }

            return UserProgress(
                lastActiveDate = getTodayDateString(),
                activeStudyDates = emptySet(),
                lastCheckInDate = "",
                verifiedCheckInDate = "",
                lastQualifyingStudyDate = "",
                currentStreak = 0
            )
        }
        return parseProgressJson(raw)
    }

    private fun loadProgressForUser(userId: String?): UserProgress {
        if (userId.isNullOrBlank()) {
            return getProgressForUser("guest")
        }
        return getProgressForUser(userId)
    }

    private fun loadProgress(): UserProgress {
        return loadProgressForUser(activeUserId)
    }

    private fun serializeProgressToJson(progress: UserProgress): JSONObject {
        return JSONObject().apply {
            put("totalXp", progress.totalXp)
            put("currentStreak", progress.currentStreak)
            put("lastActiveDate", progress.lastActiveDate)
            put("dailyXp", progress.dailyXp)
            put("dailyGoalXp", progress.dailyGoalXp)
            put("totalExecutions", progress.totalExecutions)
            put("totalSolvedExercises", progress.totalSolvedExercises)
            put("currentLessonId", progress.currentLessonId)
            put("favoriteAreaId", progress.favoriteAreaId)

            val completedArr = JSONArray()
            progress.completedLessonIds.forEach { completedArr.put(it) }
            put("completedLessonIds", completedArr)

            val activeDatesArr = JSONArray()
            progress.activeStudyDates.forEach { activeDatesArr.put(it) }
            put("activeStudyDates", activeDatesArr)
            put("lastCheckInDate", progress.lastCheckInDate)
            put("verifiedCheckInDate", progress.verifiedCheckInDate)
            put("lastQualifyingStudyDate", progress.lastQualifyingStudyDate)

            val perfectArr = JSONArray()
            progress.perfectTestModules.forEach { perfectArr.put(it) }
            put("perfectTestModules", perfectArr)

            val badgesArr = JSONArray()
            progress.unlockedBadges.forEach { badgesArr.put(it) }
            put("unlockedBadges", badgesArr)

            val kahootObj = JSONObject()
            progress.completedKahootScores.forEach { (k, v) -> kahootObj.put(k, v) }
            put("completedKahootScores", kahootObj)

            val reviewArr = JSONArray()
            progress.reviewLessonIds.forEach { reviewArr.put(it) }
            put("reviewLessonIds", reviewArr)
        }
    }

    private fun parseProgressJson(raw: String): UserProgress {
        return try {
            val json = JSONObject(raw)
            val totalXp = json.optInt("totalXp", 0)
            val totalExercises = json.optInt("totalSolvedExercises", 0)

            val completed = mutableSetOf<String>()
            val completedArr = json.optJSONArray("completedLessonIds")
            if (completedArr != null) {
                for (i in 0 until completedArr.length()) {
                    completed.add(completedArr.getString(i))
                }
            }

            val rawStreak = json.optInt("currentStreak", 0)
            val savedLastActive = json.optString("lastActiveDate", "")
            val savedLastCheckIn = json.optString("lastCheckInDate", "")
            val savedVerifiedCheckIn = json.optString("verifiedCheckInDate", "")
            val savedLastQualifying = json.optString("lastQualifyingStudyDate", "")

            val todayGmt3 = com.example.data.time.NetworkTimeProvider.getTodayGmt3()
            val yesterdayGmt3 = com.example.data.time.NetworkTimeProvider.getYesterdayGmt3()

            // Validação estrita do Streak:
            // 1. Usuário ocioso / sem XP / sem lições completadas => sempre 0d
            // 2. Não estudou hoje nem ontem => expirado para 0d
            // 3. Estudou ontem => mantém sequência acumulada (aguardando atividade de hoje)
            // 4. Estudou hoje => mantém sequência ativa com fogo aceso
            val isZeroActivity = totalXp == 0 && completed.isEmpty() && totalExercises == 0
            val savedStreak = when {
                isZeroActivity -> 0
                rawStreak <= 0 -> 0
                savedLastQualifying.isBlank() -> 0
                savedLastQualifying != todayGmt3 && savedLastQualifying != yesterdayGmt3 -> 0
                else -> rawStreak
            }

            val activeDates = mutableSetOf<String>()
            if (!isZeroActivity) {
                val activeDatesArr = json.optJSONArray("activeStudyDates")
                if (activeDatesArr != null) {
                    for (i in 0 until activeDatesArr.length()) {
                        activeDates.add(activeDatesArr.getString(i))
                    }
                }
                if (savedLastCheckIn.isNotBlank() && (totalXp > 0 || completed.isNotEmpty())) {
                    activeDates.add(savedLastCheckIn)
                }
                if (savedVerifiedCheckIn.isNotBlank() && (totalXp > 0 || completed.isNotEmpty())) {
                    activeDates.add(savedVerifiedCheckIn)
                }
            }

            val perfectTests = mutableSetOf<String>()
            val perfectArr = json.optJSONArray("perfectTestModules")
            if (perfectArr != null) {
                for (i in 0 until perfectArr.length()) {
                    perfectTests.add(perfectArr.getString(i))
                }
            }

            val badges = mutableSetOf<String>()
            val badgesArr = json.optJSONArray("unlockedBadges")
            if (badgesArr != null) {
                for (i in 0 until badgesArr.length()) {
                    badges.add(badgesArr.getString(i))
                }
            }

            val kahootScores = mutableMapOf<String, Int>()
            val kahootObj = json.optJSONObject("completedKahootScores")
            if (kahootObj != null) {
                val keys = kahootObj.keys()
                while (keys.hasNext()) {
                    val k = keys.next()
                    val scoreVal = kahootObj.optInt(k, 0)
                    kahootScores[k] = scoreVal
                    if (scoreVal >= 80 || (scoreVal in 5..10)) {
                        perfectTests.add(k)
                    }
                }
            }

            // Auto-desbloqueio retroativo de emblemas por testes e marcos
            if (perfectTests.contains("kahoot_basic") || perfectTests.contains("test_basic")) badges.add("badge_python_basic")
            if (perfectTests.contains("kahoot_intermediate") || perfectTests.contains("test_intermediate")) badges.add("badge_python_intermediate")
            if (perfectTests.contains("kahoot_advanced") || perfectTests.contains("test_advanced")) badges.add("badge_python_advanced")
            if (perfectTests.contains("test_math_basic")) badges.add("badge_math_basic")
            if (perfectTests.contains("test_calculus_limits") || kahootScores.containsKey("test_calculus_limits")) badges.add("badge_calculus_limits")
            if (perfectTests.contains("test_calculus_derivatives") || kahootScores.containsKey("test_calculus_derivatives")) badges.add("badge_calculus_derivatives")
            if (perfectTests.contains("test_calculus_integrals") || kahootScores.containsKey("test_calculus_integrals")) badges.add("badge_calculus_integrals")

            // Auto-desbloqueio por marcos de exercícios resolvidos
            if (totalExercises >= 10) badges.add("badge_exercises_10")
            if (totalExercises >= 25) badges.add("badge_exercises_25")
            if (totalExercises >= 50) badges.add("badge_exercises_50")
            if (totalExercises >= 100) badges.add("badge_exercises_100")
            if (totalExercises >= 250) badges.add("badge_exercises_250")
            if (totalExercises >= 500) badges.add("badge_exercises_500")
            if (totalExercises >= 1000) badges.add("badge_exercises_1000")
            if (totalExercises >= 2000) badges.add("badge_exercises_2000")

            // Auto-desbloqueio por marcos de sequência
            if (savedStreak >= 3) badges.add("badge_streak_3")
            if (savedStreak >= 7) badges.add("badge_streak_7")
            if (savedStreak >= 14) badges.add("badge_streak_14")
            if (savedStreak >= 30) badges.add("badge_streak_30")

            // Auto-desbloqueio por matérias concluídas
            if (completed.count { it.startsWith("quim_") || it.startsWith("ch_") || it.contains("quimica") } >= 3) badges.add("badge_enem_chemistry")
            if (completed.count { it.startsWith("geo_") || it.contains("geografia") } >= 3) badges.add("badge_enem_geography")
            if (completed.count { it.startsWith("hist_") || it.contains("historia") } >= 3) badges.add("badge_enem_history")
            if (completed.count { it.startsWith("ling_") || it.contains("linguagens") } >= 3) badges.add("badge_enem_languages")
            if (completed.count { it.startsWith("fis_") || it.contains("fisica") } >= 3) badges.add("badge_enem_physics")
            if (completed.count { it.startsWith("bio_") || it.contains("biologia") } >= 3) badges.add("badge_enem_biology")
            if (completed.count { it.startsWith("enem_") || it.startsWith("mb_") || it.startsWith("mat_") || it.contains("matematica") } >= 4) badges.add("badge_enem_math")

            val reviews = mutableSetOf<String>()
            val reviewArr = json.optJSONArray("reviewLessonIds")
            if (reviewArr != null) {
                for (i in 0 until reviewArr.length()) {
                    reviews.add(reviewArr.getString(i))
                }
            }

            val cleanLastQualifying = if (isZeroActivity) "" else savedLastQualifying
            val cleanLastCheckIn = if (isZeroActivity) "" else savedLastCheckIn
            val cleanVerifiedCheckIn = if (isZeroActivity) "" else savedVerifiedCheckIn

            UserProgress(
                totalXp = totalXp,
                currentStreak = savedStreak,
                lastActiveDate = savedLastActive.ifBlank { getTodayDateString() },
                activeStudyDates = activeDates,
                lastCheckInDate = cleanLastCheckIn,
                verifiedCheckInDate = cleanVerifiedCheckIn,
                lastQualifyingStudyDate = cleanLastQualifying,
                dailyXp = json.optInt("dailyXp", 0),
                dailyGoalXp = json.optInt("dailyGoalXp", 50),
                totalExecutions = json.optInt("totalExecutions", 0),
                totalSolvedExercises = totalExercises,
                currentLessonId = json.optString("currentLessonId", "py_b1_l1"),
                favoriteAreaId = json.optString("favoriteAreaId", "py_b1"),
                completedLessonIds = completed,
                completedKahootScores = kahootScores,
                perfectTestModules = perfectTests,
                unlockedBadges = badges,
                reviewLessonIds = reviews
            )
        } catch (_: Exception) {
            UserProgress(
                lastActiveDate = getTodayDateString(),
                activeStudyDates = emptySet(),
                lastCheckInDate = "",
                verifiedCheckInDate = ""
            )
        }
    }

    // Developer telemetry real-time listener: updates dev dashboard instantly
    fun listenAllUsersForDev(
        onUpdate: (List<UserProgressAdminRecord>) -> Unit,
        onError: (String) -> Unit
    ): ListenerRegistration? {
        val firestore = db
        if (firestore == null) {
            onUpdate(getSampleTelemetryRecords())
            return null
        }

        return try {
            firestore.collection("users")
                .addSnapshotListener { querySnapshot, error ->
                    if (error != null) {
                        onError("Erro no listener em tempo real: ${error.message}")
                        return@addSnapshotListener
                    }
                    if (querySnapshot != null) {
                        val list = mutableListOf<UserProgressAdminRecord>()
                        for (doc in querySnapshot.documents) {
                            val uid = doc.getString("userId") ?: doc.id
                            val email = doc.getString("email") ?: "sem-email"
                            val name = doc.getString("displayName") ?: "Estudante"
                            val photo = doc.getString("photoUrl")?.takeIf { it.isNotBlank() }
                            val xp = doc.getLong("totalXp")?.toInt() ?: 0
                            val lvl = doc.getLong("level")?.toInt() ?: ((xp / 100) + 1)
                            val streak = doc.getLong("currentStreak")?.toInt() ?: 0
                            val isDev = doc.getBoolean("isDev") ?: (email.equals("contatopedro21g@gmail.com", ignoreCase = true))

                            val lessons = (doc.get("completedLessonIds") as? List<*>)
                                ?.filterIsInstance<String>() ?: emptyList()

                            val badges = (doc.get("unlockedBadges") as? List<*>)
                                ?.filterIsInstance<String>() ?: emptyList()

                            @Suppress("UNCHECKED_CAST")
                            val kahootMap = (doc.get("completedKahootScores") as? Map<String, *>)
                                ?.mapValues { (it.value as? Number)?.toInt() ?: 0 } ?: emptyMap()

                            val lastDate = doc.getString("lastActiveDate") ?: ""

                            list.add(
                                UserProgressAdminRecord(
                                    userId = uid,
                                    email = email,
                                    displayName = name,
                                    photoUrl = photo,
                                    totalXp = xp,
                                    level = lvl,
                                    currentStreak = streak,
                                    activeStudyDaysCount = maxOf(0, streak),
                                    completedLessonsCount = lessons.size,
                                    completedLessons = lessons,
                                    kahootScores = kahootMap,
                                    testScores = kahootMap,
                                    badges = badges,
                                    lastActiveDate = lastDate,
                                    isDev = isDev
                                )
                            )
                        }

                        // Merge active user record if not present in Firestore snapshot
                        val currentUid = activeUserId
                        if (currentUid != null) {
                            val idx = list.indexOfFirst { it.userId == currentUid }
                            val myPhoto = activePhotoUrl ?: prefs.getString("active_user_photo_${currentUid}", null)
                            if (idx >= 0) {
                                val current = list[idx]
                                list[idx] = current.copy(
                                    photoUrl = myPhoto ?: current.photoUrl,
                                    totalXp = maxOf(current.totalXp, _progressState.value.totalXp)
                                )
                            } else {
                                list.addAll(getSampleTelemetryRecords())
                            }
                        } else if (list.isEmpty()) {
                            list.addAll(getSampleTelemetryRecords())
                        }

                        list.sortByDescending { it.totalXp }
                        onUpdate(list)
                    }
                }
        } catch (e: Exception) {
            onError("Erro ao iniciar monitoramento em tempo real: ${e.message}")
            null
        }
    }

    // Developer telemetry query: fetch all active users
    fun fetchAllUsersForDev(
        onSuccess: (List<UserProgressAdminRecord>) -> Unit,
        onError: (String) -> Unit
    ) {
        val firestore = db

        if (firestore == null) {
            onSuccess(getSampleTelemetryRecords())
            return
        }

        firestore.collection("users")
            .get()
            .addOnSuccessListener { querySnapshot ->
                val list = mutableListOf<UserProgressAdminRecord>()
                for (doc in querySnapshot.documents) {
                    val uid = doc.getString("userId") ?: doc.id
                    val email = doc.getString("email") ?: "sem-email"
                    val name = doc.getString("displayName") ?: "Estudante"
                    val photo = doc.getString("photoUrl")?.takeIf { it.isNotBlank() }
                    val xp = doc.getLong("totalXp")?.toInt() ?: 0
                    val lvl = doc.getLong("level")?.toInt() ?: ((xp / 100) + 1)
                    val streak = doc.getLong("currentStreak")?.toInt() ?: 0
                    val isDev = doc.getBoolean("isDev") ?: (email.equals("contatopedro21g@gmail.com", ignoreCase = true))

                    val lessons = (doc.get("completedLessonIds") as? List<*>)
                        ?.filterIsInstance<String>() ?: emptyList()

                    val badges = (doc.get("unlockedBadges") as? List<*>)
                        ?.filterIsInstance<String>() ?: emptyList()

                    @Suppress("UNCHECKED_CAST")
                    val kahootMap = (doc.get("completedKahootScores") as? Map<String, *>)
                        ?.mapValues { (it.value as? Number)?.toInt() ?: 0 } ?: emptyMap()

                    val lastDate = doc.getString("lastActiveDate") ?: ""

                    list.add(
                        UserProgressAdminRecord(
                            userId = uid,
                            email = email,
                            displayName = name,
                            photoUrl = photo,
                            totalXp = xp,
                            level = lvl,
                            currentStreak = streak,
                            activeStudyDaysCount = maxOf(0, streak),
                            completedLessonsCount = lessons.size,
                            completedLessons = lessons,
                            kahootScores = kahootMap,
                            testScores = kahootMap,
                            badges = badges,
                            lastActiveDate = lastDate,
                            isDev = isDev
                        )
                    )
                }

                // Merge active user record if not present in Firestore snapshot
                val currentUid = activeUserId
                if (currentUid != null) {
                    val idx = list.indexOfFirst { it.userId == currentUid }
                    val myPhoto = activePhotoUrl ?: prefs.getString("active_user_photo_${currentUid}", null)
                    if (idx >= 0) {
                        val current = list[idx]
                        list[idx] = current.copy(
                            photoUrl = myPhoto ?: current.photoUrl,
                            totalXp = maxOf(current.totalXp, _progressState.value.totalXp)
                        )
                    } else {
                        list.addAll(getSampleTelemetryRecords())
                    }
                } else if (list.isEmpty()) {
                    list.addAll(getSampleTelemetryRecords())
                }

                list.sortByDescending { it.totalXp }
                onSuccess(list)
            }
            .addOnFailureListener {
                val fallbackList = getSampleTelemetryRecords()
                onSuccess(fallbackList)
            }
    }

    private fun getSampleTelemetryRecords(): List<UserProgressAdminRecord> {
        val current = _progressState.value
        val name = activeDisplayName ?: "Estudante"
        val email = activeUserEmail ?: "usuario@knowverse.com"
        val uid = activeUserId ?: "usuario_atual"
        val isDev = email.equals("contatopedro21g@gmail.com", ignoreCase = true)
        val photo = activePhotoUrl ?: prefs.getString("active_user_photo_${uid}", null)

        return listOf(
            UserProgressAdminRecord(
                userId = uid,
                email = email,
                displayName = name,
                photoUrl = photo,
                totalXp = current.totalXp,
                level = current.level,
                currentStreak = current.currentStreak,
                activeStudyDaysCount = current.activeStudyDaysCount,
                completedLessonsCount = current.completedLessonIds.size,
                completedLessons = current.completedLessonIds.toList(),
                kahootScores = current.completedKahootScores,
                testScores = current.completedKahootScores,
                badges = current.unlockedBadges.toList(),
                lastActiveDate = getTodayDateString(),
                isDev = isDev
            )
        )
    }

    private val previouslyUnlockedAchievementIds = mutableSetOf<String>()

    private fun buildAchievementsList(progress: UserProgress): List<Achievement> {
        val ex = progress.totalSolvedExercises
        val lessons = progress.completedLessonIds
        val streak = progress.currentStreak
        val xp = progress.totalXp

        return listOf(
            // ========================================================
            // ESCALA COMPLETA DE EXERCÍCIOS RESOLVIDOS
            // ========================================================
            Achievement(
                id = "ach_exercises_1",
                title = "Primeiro Acerto",
                description = "Resolva seu 1º exercício com gabarito no Knowverse.",
                xpBonus = 30,
                isUnlocked = ex >= 1,
                progress = (ex.toFloat() / 1f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_exercises_5",
                title = "Início dos Treinos",
                description = "Resolva 5 exercícios com gabarito.",
                xpBonus = 75,
                isUnlocked = ex >= 5,
                progress = (ex.toFloat() / 5f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_exercises_10",
                title = "Atirador de Elite",
                description = "Resolva 10 exercícios no Banco Universal.",
                xpBonus = 120,
                isUnlocked = ex >= 10,
                progress = (ex.toFloat() / 10f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_exercises_20",
                title = "Sábio dos Exercícios",
                description = "Resolva 20 exercícios com gabarito oficial.",
                xpBonus = 200,
                isUnlocked = ex >= 20,
                progress = (ex.toFloat() / 20f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_exercises_35",
                title = "Ritmo de Ferro",
                description = "Supere 35 exercícios de vestibular e ENEM.",
                xpBonus = 300,
                isUnlocked = ex >= 35,
                progress = (ex.toFloat() / 35f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_exercises_50",
                title = "Gabaritador Implacável",
                description = "Resolva 50 exercícios no Banco Universal.",
                xpBonus = 450,
                isUnlocked = ex >= 50,
                progress = (ex.toFloat() / 50f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_exercises_75",
                title = "Especialista em Simulado",
                description = "Supere 75 questões oficiais com persistência.",
                xpBonus = 600,
                isUnlocked = ex >= 75,
                progress = (ex.toFloat() / 75f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_exercises_100",
                title = "Centurião das Questões",
                description = "Resolva 100 exercícios no Knowverse!",
                xpBonus = 1000,
                isUnlocked = ex >= 100,
                progress = (ex.toFloat() / 100f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_exercises_150",
                title = "Muralha do ENEM",
                description = "Supere 150 questões de vestibulares oficiais.",
                xpBonus = 1300,
                isUnlocked = ex >= 150,
                progress = (ex.toFloat() / 150f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_exercises_200",
                title = "Veterano dos Gabaritos",
                description = "Atinja a expressiva marca de 200 exercícios resolvidos.",
                xpBonus = 1750,
                isUnlocked = ex >= 200,
                progress = (ex.toFloat() / 200f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_exercises_300",
                title = "Titã da Resolução",
                description = "Resolva 300 questões no Banco Universal.",
                xpBonus = 2200,
                isUnlocked = ex >= 300,
                progress = (ex.toFloat() / 300f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_exercises_500",
                title = "Lenda dos 500 Acertos",
                description = "Meio milhar de questões resolvidas com excelência!",
                xpBonus = 3500,
                isUnlocked = ex >= 500,
                progress = (ex.toFloat() / 500f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_exercises_750",
                title = "Grão-Mestre Imparável",
                description = "750 exercícios conquistados através da dedicação diária.",
                xpBonus = 5000,
                isUnlocked = ex >= 750,
                progress = (ex.toFloat() / 750f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_exercises_1000",
                title = "O Imperador do ENEM",
                description = "1.000 questões dominadas! Uma conquista monumental e histórica.",
                xpBonus = 7500,
                isUnlocked = ex >= 1000,
                progress = (ex.toFloat() / 1000f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_exercises_1500",
                title = "Conhecimento Infinito",
                description = "1.500 exercícios resolvidos. Sua bagagem é incomparável.",
                xpBonus = 10000,
                isUnlocked = ex >= 1500,
                progress = (ex.toFloat() / 1500f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_exercises_2000",
                title = "Soberano Supremo do Knowverse",
                description = "2.000 questões superadas! Você zerou praticamente todas as questões do sistema.",
                xpBonus = 15000,
                isUnlocked = ex >= 2000,
                progress = (ex.toFloat() / 2000f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),

            // ========================================================
            // SEQUÊNCIA DE FOCO (STREAK)
            // ========================================================
            Achievement(
                id = "ach_streak_3",
                title = "Chama Sagrada (3 Dias)",
                description = "Mantenha uma sequência de estudos por 3 dias seguidos.",
                xpBonus = 100,
                isUnlocked = streak >= 3,
                progress = (streak.toFloat() / 3f).coerceIn(0f, 1f),
                iconKey = "STREAK"
            ),
            Achievement(
                id = "ach_streak_7",
                title = "Semana de Ouro (7 Dias)",
                description = "Mantenha uma sequência de estudos por 7 dias seguidos.",
                xpBonus = 250,
                isUnlocked = streak >= 7,
                progress = (streak.toFloat() / 7f).coerceIn(0f, 1f),
                iconKey = "STREAK"
            ),
            Achievement(
                id = "ach_streak_14",
                title = "Guardião da Disciplina (14 Dias)",
                description = "Mantenha a chama acesa por duas semanas consecutivas.",
                xpBonus = 500,
                isUnlocked = streak >= 14,
                progress = (streak.toFloat() / 14f).coerceIn(0f, 1f),
                iconKey = "STREAK"
            ),
            Achievement(
                id = "ach_streak_30",
                title = "Centurião do Foco (30 Dias)",
                description = "Um mês inteiro ininterrupto de aprendizado diário!",
                xpBonus = 1200,
                isUnlocked = streak >= 30,
                progress = (streak.toFloat() / 30f).coerceIn(0f, 1f),
                iconKey = "STREAK"
            ),

            // ========================================================
            // DOMÍNIO DAS DISCIPLINAS
            // ========================================================
            Achievement(
                id = "ach_chemistry_explorer",
                title = "Alquimista do ENEM",
                description = "Conclua aulas e exercícios da Trilha Oficial de Química.",
                xpBonus = 350,
                isUnlocked = lessons.any { it.startsWith("quim_") || it.startsWith("ch_") || it.contains("quimica") },
                progress = if (lessons.any { it.startsWith("quim_") || it.startsWith("ch_") || it.contains("quimica") }) 1f else 0f,
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_geography_explorer",
                title = "Cartógrafo Global",
                description = "Conclua aulas e exercícios da Trilha de Geografia.",
                xpBonus = 300,
                isUnlocked = lessons.any { it.startsWith("geo_") || it.contains("geografia") },
                progress = if (lessons.any { it.startsWith("geo_") || it.contains("geografia") }) 1f else 0f,
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_history_explorer",
                title = "Cronista das Eras",
                description = "Conclua aulas e exercícios da Trilha de História.",
                xpBonus = 300,
                isUnlocked = lessons.any { it.startsWith("hist_") || it.contains("historia") },
                progress = if (lessons.any { it.startsWith("hist_") || it.contains("historia") }) 1f else 0f,
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_languages_explorer",
                title = "Mestre dos Discursos",
                description = "Conclua aulas e exercícios da Trilha de Linguagens.",
                xpBonus = 300,
                isUnlocked = lessons.any { it.startsWith("ling_") || it.contains("linguagens") },
                progress = if (lessons.any { it.startsWith("ling_") || it.contains("linguagens") }) 1f else 0f,
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_physics_explorer",
                title = "Newtoniano Moderno",
                description = "Conclua aulas e exercícios da Trilha de Física.",
                xpBonus = 350,
                isUnlocked = lessons.any { it.startsWith("fis_") || it.contains("fisica") },
                progress = if (lessons.any { it.startsWith("fis_") || it.contains("fisica") }) 1f else 0f,
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_biology_explorer",
                title = "Naturalista da Vida",
                description = "Conclua aulas e exercícios da Trilha de Biologia.",
                xpBonus = 350,
                isUnlocked = lessons.any { it.startsWith("bio_") || it.contains("biologia") },
                progress = if (lessons.any { it.startsWith("bio_") || it.contains("biologia") }) 1f else 0f,
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_math_explorer",
                title = "Estrategista dos Números",
                description = "Conclua aulas e exercícios da Trilha de Matemática ENEM.",
                xpBonus = 400,
                isUnlocked = lessons.any { it.startsWith("enem_") || it.startsWith("mb_") || it.startsWith("mat_") || it.contains("matematica") },
                progress = if (lessons.any { it.startsWith("enem_") || it.startsWith("mb_") || it.startsWith("mat_") || it.contains("matematica") }) 1f else 0f,
                iconKey = "TROPHY"
            ),

            // ========================================================
            // MARCOS GERAIS E NÍVEL
            // ========================================================
            Achievement(
                id = "ach_first_code",
                title = "Primeira Execução",
                description = "Execute seu primeiro programa Python no Knowverse.",
                xpBonus = 50,
                isUnlocked = progress.totalExecutions >= 1,
                progress = (progress.totalExecutions.toFloat() / 1f).coerceIn(0f, 1f),
                iconKey = "CODE"
            ),
            Achievement(
                id = "ach_first_lesson",
                title = "Primeiro Módulo",
                description = "Conclua sua primeira lição completa.",
                xpBonus = 50,
                isUnlocked = lessons.isNotEmpty(),
                progress = (lessons.size.toFloat() / 1f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_final_test_master",
                title = "Mestre do Teste Final",
                description = "Vença um Teste Final com mais de 700 pontos.",
                xpBonus = 150,
                isUnlocked = progress.completedKahootScores.values.any { it >= 700 },
                progress = if (progress.completedKahootScores.values.any { it >= 700 }) 1f else 0f,
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_level_5",
                title = "Explorador Veterano",
                description = "Atinja o Nível 5 de Maestria no Knowverse.",
                xpBonus = 150,
                isUnlocked = progress.level >= 5,
                progress = (progress.level.toFloat() / 5f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            ),
            Achievement(
                id = "ach_level_10",
                title = "Grão-Mestre Acadêmico",
                description = "Atinja o Nível 10 de Maestria no Knowverse.",
                xpBonus = 500,
                isUnlocked = progress.level >= 10,
                progress = (progress.level.toFloat() / 10f).coerceIn(0f, 1f),
                iconKey = "TROPHY"
            )
        )
    }

    private fun updateAchievements(progress: UserProgress) {
        val list = buildAchievementsList(progress)

        // Se for a primeira carga, apenas armazena as já desbloqueadas sem disparar alertas retrativos
        if (previouslyUnlockedAchievementIds.isEmpty()) {
            list.filter { it.isUnlocked }.forEach { previouslyUnlockedAchievementIds.add(it.id) }
        } else {
            // Disparar notificação in-app em tempo real para nova conquista
            list.forEach { ach ->
                if (ach.isUnlocked && !previouslyUnlockedAchievementIds.contains(ach.id)) {
                    previouslyUnlockedAchievementIds.add(ach.id)
                    AchievementAlertManager.postAlert(
                        InAppAchievementAlert(
                            title = ach.title,
                            description = ach.description,
                            xpBonus = ach.xpBonus,
                            category = "CONQUISTA DESBLOQUEADA!"
                        )
                    )
                }
            }
        }

        _achievementsState.value = list
    }

    private fun loadAchievements(): List<Achievement> {
        val progress = _progressState.value
        val list = buildAchievementsList(progress)
        list.filter { it.isUnlocked }.forEach { previouslyUnlockedAchievementIds.add(it.id) }
        return list
    }
}
