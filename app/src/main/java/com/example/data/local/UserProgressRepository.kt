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

    private fun getTodayDateString(): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(Date())
    }

    private fun getYesterdayDateString(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -1)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(cal.time)
    }

    private fun getDateDaysAgoString(daysAgo: Int): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DAY_OF_YEAR, -daysAgo)
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        return sdf.format(cal.time)
    }

    private fun checkAndUpdateDailyStreak() {
        val today = getTodayDateString()
        val yesterday = getYesterdayDateString()
        val current = _progressState.value
        val lastDate = current.lastActiveDate

        if (lastDate.isEmpty() || lastDate == today) {
            return
        }

        val preservedDates = current.activeStudyDates.toMutableSet()
        if (lastDate.isNotBlank() && lastDate != today) {
            preservedDates.add(lastDate)
        }

        val preservedStreak = if (lastDate == yesterday || preservedDates.contains(yesterday)) {
            current.currentStreak
        } else {
            maxOf(1, current.currentStreak)
        }

        val updated = current.copy(
            lastActiveDate = today,
            activeStudyDates = preservedDates,
            currentStreak = preservedStreak,
            dailyXp = 0
        )
        saveProgress(updated, pushToRemote = false)
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
                    val remoteStreak = snapshot.getLong("currentStreak")?.toInt() ?: 1
                    val remoteActiveDays = snapshot.getLong("activeDays")?.toInt() ?: 1
                    val remoteLessons = (snapshot.get("completedLessonIds") as? List<*>)
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

                    val remotePhoto = snapshot.getString("photoUrl")?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    if (remotePhoto != null) {
                        activePhotoUrl = remotePhoto
                        prefs.edit().putString("active_user_photo_${userId}", remotePhoto)
                            .putString("permanent_avatar_${cleanEmail}", remotePhoto).apply()
                    }

                    val current = _progressState.value
                    val unionDates = (current.activeStudyDates + remoteDates).toMutableSet()
                    val bestStreak = maxOf(current.currentStreak, remoteStreak, remoteActiveDays)
                    if (unionDates.size < bestStreak) {
                        for (d in 1..bestStreak) {
                            unionDates.add(getDateDaysAgoString(d))
                        }
                    }

                    val mergedCheckIn = if (current.lastCheckInDate == getTodayDateString() || remoteLastCheckIn == getTodayDateString()) {
                        getTodayDateString()
                    } else {
                        current.lastCheckInDate.ifBlank { remoteLastCheckIn }
                    }

                    val mergedVerifiedCheckIn = if (current.verifiedCheckInDate == getTodayDateString() || remoteVerifiedCheckIn == getTodayDateString()) {
                        getTodayDateString()
                    } else {
                        current.verifiedCheckInDate.ifBlank { remoteVerifiedCheckIn }
                    }

                    val merged = current.copy(
                        totalXp = maxOf(current.totalXp, remoteXp),
                        currentStreak = bestStreak,
                        activeStudyDates = unionDates,
                        lastCheckInDate = mergedCheckIn,
                        verifiedCheckInDate = mergedVerifiedCheckIn,
                        completedLessonIds = current.completedLessonIds + remoteLessons,
                        completedKahootScores = current.completedKahootScores + remoteKahoot,
                        perfectTestModules = current.perfectTestModules + remotePerfect,
                        unlockedBadges = current.unlockedBadges + remoteBadges
                    )
                    saveProgress(merged, pushToRemote = false)
                    // Trigger asynchronous cleanup of any cloned/duplicate documents
                    cleanupDuplicateUserDocuments(userId, cleanEmail)
                } else {
                    // Document does not exist with this ID yet: check by email to recover past version progress!
                    if (cleanEmail.isNotBlank()) {
                        firestore.collection("users").whereEqualTo("email", cleanEmail)
                            .get()
                            .addOnSuccessListener { querySnap ->
                                var highestRemoteXp = 0
                                var highestRemoteStreak = 1
                                var highestRemoteActiveDays = 1
                                val allRemoteLessons = mutableSetOf<String>()
                                val allRemoteBadges = mutableSetOf<String>()
                                val allRemotePerfect = mutableSetOf<String>()
                                val allRemoteDates = mutableSetOf<String>()
                                var bestRemotePhoto: String? = null
                                var remoteLastCheckIn = ""
                                var remoteVerifiedCheckIn = ""
                                val duplicateDocsToDelete = mutableListOf<String>()

                                for (doc in querySnap.documents) {
                                    val rx = doc.getLong("totalXp")?.toInt() ?: 0
                                    if (rx > highestRemoteXp) highestRemoteXp = rx
                                    val rs = doc.getLong("currentStreak")?.toInt() ?: 1
                                    if (rs > highestRemoteStreak) highestRemoteStreak = rs
                                    val rad = doc.getLong("activeDays")?.toInt() ?: 1
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
                                val bestStreak = maxOf(current.currentStreak, highestRemoteStreak, highestRemoteActiveDays)
                                val unionDates = (current.activeStudyDates + allRemoteDates).toMutableSet()
                                if (unionDates.size < bestStreak) {
                                    for (d in 1..bestStreak) {
                                        unionDates.add(getDateDaysAgoString(d))
                                    }
                                }

                                val mergedCheckIn = if (current.lastCheckInDate == getTodayDateString() || remoteLastCheckIn == getTodayDateString()) {
                                    getTodayDateString()
                                } else {
                                    current.lastCheckInDate.ifBlank { remoteLastCheckIn }
                                }

                                val mergedVerifiedCheckIn = if (current.verifiedCheckInDate == getTodayDateString() || remoteVerifiedCheckIn == getTodayDateString()) {
                                    getTodayDateString()
                                } else {
                                    current.verifiedCheckInDate.ifBlank { remoteVerifiedCheckIn }
                                }

                                val merged = current.copy(
                                    totalXp = maxOf(current.totalXp, highestRemoteXp),
                                    currentStreak = bestStreak,
                                    activeStudyDates = unionDates,
                                    lastCheckInDate = mergedCheckIn,
                                    verifiedCheckInDate = mergedVerifiedCheckIn,
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
            "completedKahootScores" to current.completedKahootScores,
            "perfectTestModules" to current.perfectTestModules.toList(),
            "unlockedBadges" to current.unlockedBadges.toList(),
            "totalSolvedExercises" to current.totalSolvedExercises,
            "lastActiveDate" to current.lastActiveDate,
            "updatedAt" to FieldValue.serverTimestamp()
        )
        if (!resolvedPhoto.isNullOrBlank()) {
            docData["photoUrl"] = resolvedPhoto
        }

        scope.launch {
            try {
                db?.collection("users")?.document(userId)
                    ?.set(docData, SetOptions.merge())
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
                        streak = obj.optInt("streak", 1),
                        activeDays = obj.optInt("activeDays", 1)
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
                                val strk = doc.getLong("currentStreak")?.toInt() ?: 1
                                val days = doc.getLong("activeDays")?.toInt() ?: 1
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
        // Automatically perform daily check-in if not done yet today (without double-adding XP on top of the lesson reward)
        if (!_progressState.value.hasCheckedInToday()) {
            recordCheckIn(awardBonusXp = false)
        }
        val current = _progressState.value
        val today = getTodayDateString()
        val isFirstTime = !current.completedLessonIds.contains(lessonId)
        val newCompleted = current.completedLessonIds + lessonId
        val newReviews = current.reviewLessonIds - lessonId
        // XP is ONLY awarded once per lesson
        val actualXp = if (isFirstTime) xpEarned else 0

        val updated = current.copy(
            totalXp = current.totalXp + actualXp,
            dailyXp = current.dailyXp + actualXp,
            lastActiveDate = today,
            activeStudyDates = current.activeStudyDates + today,
            lastCheckInDate = today,
            verifiedCheckInDate = today,
            completedLessonIds = newCompleted,
            reviewLessonIds = newReviews,
            totalSolvedExercises = if (isFirstTime) current.totalSolvedExercises + 1 else current.totalSolvedExercises
        )
        saveProgress(updated)
        updateAchievements(updated)
        return actualXp
    }

    fun recordCheckIn(awardBonusXp: Boolean = true): CheckInResult {
        val current = _progressState.value
        val today = getTodayDateString()
        val yesterday = getYesterdayDateString()
        val isNewToday = current.verifiedCheckInDate != today
        val hadPreviousActivity = current.activeStudyDates.any { it != today } ||
                (current.lastActiveDate.isNotBlank() && current.lastActiveDate != today) ||
                (current.lastCheckInDate.isNotBlank() && current.lastCheckInDate != today) ||
                current.completedLessonIds.isNotEmpty() ||
                current.totalXp > 0 ||
                current.totalSolvedExercises > 0 ||
                current.completedKahootScores.isNotEmpty()

        val newDates = current.activeStudyDates.toMutableSet()
        if (hadPreviousActivity && newDates.none { it != today }) {
            newDates.add(yesterday)
        }
        newDates.add(today)

        val newStreak = if (isNewToday) {
            if (hadPreviousActivity) {
                maxOf(current.currentStreak + 1, newDates.size)
            } else {
                maxOf(1, current.currentStreak, newDates.size)
            }
        } else {
            maxOf(1, current.currentStreak, newDates.size)
        }

        if (newDates.size < newStreak) {
            for (d in 0 until newStreak) {
                newDates.add(getDateDaysAgoString(d))
            }
        }

        val xpBonus = if (isNewToday && awardBonusXp) 20 else 0

        val updated = current.copy(
            activeStudyDates = newDates,
            lastCheckInDate = today,
            verifiedCheckInDate = today,
            lastActiveDate = today,
            currentStreak = newStreak,
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
        if (!_progressState.value.hasCheckedInToday()) {
            recordCheckIn()
        }
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

        val updatedBadges = if (isNewBadge && badgeToAward != null) current.unlockedBadges + badgeToAward else current.unlockedBadges
        val totalXpToAdd = xpEarned + badgeXpBonus

        val updated = current.copy(
            totalXp = current.totalXp + totalXpToAdd,
            dailyXp = current.dailyXp + totalXpToAdd,
            completedKahootScores = updatedScores,
            perfectTestModules = updatedPerfect,
            unlockedBadges = updatedBadges
        )
        saveProgress(updated)

        if (isNewBadge && badgeToAward != null) {
            val badgeObj = TrackBadgesCatalog.findBadge(badgeToAward)
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

    fun addBonusXp(xp: Int) {
        val current = _progressState.value
        val updated = current.copy(
            totalXp = current.totalXp + xp,
            dailyXp = current.dailyXp + xp,
            totalSolvedExercises = current.totalSolvedExercises + 1
        )
        saveProgress(updated)
        updateAchievements(updated)
    }

    fun markLessonForReview(lessonId: String) {
        val current = _progressState.value
        if (!current.reviewLessonIds.contains(lessonId)) {
            val updated = current.copy(reviewLessonIds = current.reviewLessonIds + lessonId)
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
        prefs.edit().putString(key, json.toString()).apply()

        if (pushToRemote && activeUserId != null) {
            pushCurrentProgressToFirestore()
        }
    }

    fun getProgressForUser(userId: String): UserProgress {
        val key = "user_progress_data_$userId"
        var raw = prefs.getString(key, null)

        // Legacy compatibility: check by email key or single-user legacy keys if userId key is missing
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
                    // Migrate legacy data to current userId key
                    prefs.edit().putString(key, legacySingle).apply()
                    return parsedLegacy
                }
            }
        }

        if (raw.isNullOrBlank()) {
            return UserProgress(
                lastActiveDate = getTodayDateString(),
                activeStudyDates = emptySet(),
                lastCheckInDate = ""
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
            val completed = mutableSetOf<String>()
            val completedArr = json.optJSONArray("completedLessonIds")
            if (completedArr != null) {
                for (i in 0 until completedArr.length()) {
                    completed.add(completedArr.getString(i))
                }
            }

            val savedStreak = maxOf(1, json.optInt("currentStreak", 1))
            val savedLastActive = json.optString("lastActiveDate", "")
            val savedLastCheckIn = json.optString("lastCheckInDate", "")
            val savedVerifiedCheckIn = json.optString("verifiedCheckInDate", "")

            val activeDates = mutableSetOf<String>()
            val activeDatesArr = json.optJSONArray("activeStudyDates")
            if (activeDatesArr != null) {
                for (i in 0 until activeDatesArr.length()) {
                    activeDates.add(activeDatesArr.getString(i))
                }
            }
            if (savedLastCheckIn.isNotBlank()) {
                activeDates.add(savedLastCheckIn)
            }
            if (savedVerifiedCheckIn.isNotBlank()) {
                activeDates.add(savedVerifiedCheckIn)
            }
            // Legacy compatibility: if activeDates had fewer entries than savedStreak from an older app version, backfill past days
            if (activeDates.size < savedStreak && (json.optInt("totalXp", 0) > 0 || completed.isNotEmpty())) {
                for (d in 1..savedStreak) {
                    activeDates.add(getDateDaysAgoString(d))
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
                    // Legacy compatibility: in older app versions, passing scores were stored in completedKahootScores without perfectTestModules
                    if (scoreVal >= 80 || (scoreVal in 5..10)) {
                        perfectTests.add(k)
                    }
                }
            }

            // Legacy compatibility: if badges were unlocked in older app versions, ensure corresponding perfectTests are populated
            if (badges.contains("badge_python_basic")) perfectTests.add("kahoot_basic")
            if (badges.contains("badge_python_intermediate")) perfectTests.add("kahoot_intermediate")
            if (badges.contains("badge_python_advanced")) perfectTests.add("kahoot_advanced")

            // Auto-grant badges if module final test is completed with 100%
            if (perfectTests.contains("kahoot_basic") || perfectTests.contains("test_basic")) {
                badges.add("badge_python_basic")
            }
            if (perfectTests.contains("kahoot_intermediate") || perfectTests.contains("test_intermediate")) {
                badges.add("badge_python_intermediate")
            }
            if (perfectTests.contains("kahoot_advanced") || perfectTests.contains("test_advanced")) {
                badges.add("badge_python_advanced")
            }
            if (perfectTests.contains("test_math_basic")) {
                badges.add("badge_math_basic")
            }
            if (perfectTests.contains("test_calculus_limits") || kahootScores.containsKey("test_calculus_limits")) {
                badges.add("badge_calculus_limits")
            }
            if (perfectTests.contains("test_calculus_derivatives") || kahootScores.containsKey("test_calculus_derivatives")) {
                badges.add("badge_calculus_derivatives")
            }
            if (perfectTests.contains("test_calculus_integrals") || kahootScores.containsKey("test_calculus_integrals")) {
                badges.add("badge_calculus_integrals")
            }

            val reviews = mutableSetOf<String>()
            val reviewArr = json.optJSONArray("reviewLessonIds")
            if (reviewArr != null) {
                for (i in 0 until reviewArr.length()) {
                    reviews.add(reviewArr.getString(i))
                }
            }

            UserProgress(
                totalXp = json.optInt("totalXp", 0),
                currentStreak = maxOf(savedStreak, activeDates.size),
                lastActiveDate = savedLastActive.ifBlank { getTodayDateString() },
                activeStudyDates = activeDates,
                lastCheckInDate = savedLastCheckIn,
                verifiedCheckInDate = savedVerifiedCheckIn,
                dailyXp = json.optInt("dailyXp", 0),
                dailyGoalXp = json.optInt("dailyGoalXp", 50),
                totalExecutions = json.optInt("totalExecutions", 0),
                totalSolvedExercises = json.optInt("totalSolvedExercises", completed.size),
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
                            val streak = doc.getLong("currentStreak")?.toInt() ?: 1
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
                                    activeStudyDaysCount = maxOf(1, streak),
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
                    val streak = doc.getLong("currentStreak")?.toInt() ?: 1
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
                            activeStudyDaysCount = maxOf(1, streak),
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
        return listOf(
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
                isUnlocked = progress.completedLessonIds.isNotEmpty(),
                progress = (progress.completedLessonIds.size.toFloat() / 1f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_exercises_5",
                title = "Praticante Dedicado",
                description = "Resolva 5 exercícios no Banco Universal.",
                xpBonus = 75,
                isUnlocked = progress.totalSolvedExercises >= 5,
                progress = (progress.totalSolvedExercises.toFloat() / 5f).coerceIn(0f, 1f),
                iconKey = "CHECK"
            ),
            Achievement(
                id = "ach_streak_3",
                title = "Consistência (3 Dias)",
                description = "Mantenha uma sequência de estudos por 3 dias seguidos.",
                xpBonus = 100,
                isUnlocked = progress.currentStreak >= 3,
                progress = (progress.currentStreak.toFloat() / 3f).coerceIn(0f, 1f),
                iconKey = "STREAK"
            ),
            Achievement(
                id = "ach_streak_7",
                title = "Semana de Ouro (7 Dias)",
                description = "Mantenha uma sequência de estudos por 7 dias seguidos.",
                xpBonus = 200,
                isUnlocked = progress.currentStreak >= 7,
                progress = (progress.currentStreak.toFloat() / 7f).coerceIn(0f, 1f),
                iconKey = "STREAK"
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
                id = "ach_exercises_20",
                title = "Sábio dos Exercícios",
                description = "Resolva 20 exercícios com gabarito no Knowverse.",
                xpBonus = 250,
                isUnlocked = progress.totalSolvedExercises >= 20,
                progress = (progress.totalSolvedExercises.toFloat() / 20f).coerceIn(0f, 1f),
                iconKey = "CHECK"
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
