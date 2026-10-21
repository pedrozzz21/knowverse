package com.example.auth

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.credentials.ClearCredentialStateRequest
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.exceptions.GetCredentialCancellationException
import androidx.credentials.exceptions.NoCredentialException
import com.example.R
import com.google.android.libraries.identity.googleid.GetSignInWithGoogleOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential.Companion.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
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
import java.security.MessageDigest
import java.security.SecureRandom
import java.util.UUID

data class AppUser(
    val uid: String,
    val email: String,
    val displayName: String,
    val photoUrl: String? = null,
    val bio: String = "",
    val isDev: Boolean = false,
    val isGoogleUser: Boolean = false,
    val lastEmailChangeTimestamp: Long = 0L
)

data class RegisteredAccountRecord(
    val uid: String,
    var email: String,
    var passwordHash: String,
    var salt: String,
    var displayName: String,
    val securityQuestion: String,
    var securityAnswerHash: String,
    var photoUrl: String? = null,
    var bio: String = "",
    var lastEmailChangeTimestamp: Long = 0L,
    var temporaryAccessToken: String? = null
)

class AuthManager(private val context: Context) {

    private val auth: FirebaseAuth = Firebase.auth
    private val credentialManager = CredentialManager.create(context)
    private val defaultScope = CoroutineScope(Dispatchers.IO)
    private val sessionPrefs: SharedPreferences =
        context.getSharedPreferences("knowverse_auth_session", Context.MODE_PRIVATE)
    private val accountsPrefs: SharedPreferences =
        context.getSharedPreferences("knowverse_accounts_vault", Context.MODE_PRIVATE)
    private val customProfilePrefs: SharedPreferences =
        context.getSharedPreferences("knowverse_user_custom_profiles", Context.MODE_PRIVATE)

    private val _currentUserState = MutableStateFlow<AppUser?>(null)
    val currentUserState: StateFlow<AppUser?> = _currentUserState.asStateFlow()

    private val _isSigningIn = MutableStateFlow(false)
    val isSigningIn: StateFlow<Boolean> = _isSigningIn.asStateFlow()

    private val _authErrorMessage = MutableStateFlow<String?>(null)
    val authErrorMessage: StateFlow<String?> = _authErrorMessage.asStateFlow()

    companion object {
        const val TWO_MONTHS_MILLIS = 60L * 24L * 60L * 60L * 1000L

        val SECURITY_QUESTIONS = listOf(
            "Qual o nome do seu primeiro animal de estimação?",
            "Qual o nome da sua escola de infância?",
            "Qual a sua comida favorita?",
            "Em que cidade seus pais se conheceram?",
            "Qual o seu livro ou filme favorito?"
        )
    }

    init {
        cleanClonedDevAccounts()
        _currentUserState.value = loadInitialSession()
        fetchAndCacheFirestoreAccounts()
        auth.addAuthStateListener { firebaseAuth ->
            val fbUser = firebaseAuth.currentUser
            if (fbUser != null) {
                val appUser = fbUser.toAppUser()
                saveSession(appUser)
                _currentUserState.value = appUser
            }
        }
    }

    private fun fetchAndCacheFirestoreAccounts() {
        defaultScope.launch {
            try {
                val db = getFirestoreDb() ?: return@launch
                val snapshot = db.collection("accounts").get().await()
                for (doc in snapshot.documents) {
                    val email = doc.id
                    val storedLocal = findAccountByEmail(email)
                    if (storedLocal == null) {
                        val uid = doc.getString("uid") ?: ("usr_" + hashString(email).take(12))
                        val name = doc.getString("displayName") ?: "Estudante"
                        val pHash = doc.getString("passwordHash") ?: ""
                        val salt = doc.getString("salt") ?: ""
                        val secQ = doc.getString("securityQuestion") ?: SECURITY_QUESTIONS[0]
                        val secAns = doc.getString("securityAnswerHash") ?: ""
                        val photo = doc.getString("photoUrl")
                        val bio = doc.getString("bio") ?: ""
                        val ts = doc.getLong("lastEmailChangeTimestamp") ?: 0L
                        val rec = RegisteredAccountRecord(
                            uid = uid,
                            email = email,
                            passwordHash = pHash,
                            salt = salt,
                            displayName = name,
                            securityQuestion = secQ,
                            securityAnswerHash = secAns,
                            photoUrl = photo,
                            bio = bio,
                            lastEmailChangeTimestamp = ts
                        )
                        saveAccountRecord(rec)
                    }
                }
            } catch (e: Exception) {
                Log.w("AuthManager", "Background fetch accounts warning: ${e.message}")
            }
        }
    }

    /**
     * Remove qualquer conta clonada dummy "dev_contatopedro21g" que tenha sido criada em versões antigas,
     * garantindo que a conta oficial do usuário nunca fique clonada ou com duplicatas.
     */
    private fun cleanClonedDevAccounts() {
        try {
            val all = loadAllAccounts().toMutableList()
            val removed = all.removeAll { it.uid == "dev_contatopedro21g" }
            if (removed) {
                val arr = JSONArray()
                for (rec in all) {
                    val obj = JSONObject().apply {
                        put("uid", rec.uid)
                        put("email", rec.email)
                        put("passwordHash", rec.passwordHash)
                        put("salt", rec.salt)
                        put("displayName", rec.displayName)
                        put("securityQuestion", rec.securityQuestion)
                        put("securityAnswerHash", rec.securityAnswerHash)
                        put("photoUrl", rec.photoUrl ?: "")
                        put("bio", rec.bio)
                        put("lastEmailChangeTimestamp", rec.lastEmailChangeTimestamp)
                        put("temporaryAccessToken", rec.temporaryAccessToken ?: "")
                    }
                    arr.put(obj)
                }
                accountsPrefs.edit().putString("registered_accounts_json", arr.toString()).apply()
            }
            defaultScope.launch {
                try {
                    val db = getFirestoreDb() ?: return@launch
                    db.collection("users").document("dev_contatopedro21g").delete().await()
                } catch (_: Exception) {}
            }
        } catch (_: Exception) {}
    }

    private fun loadInitialSession(): AppUser? {
        val fbUser = auth.currentUser
        if (fbUser != null) {
            val appUser = fbUser.toAppUser()
            fetchRemoteProfileIfAvailable(appUser.uid, appUser.email)
            return appUser
        }

        val raw = sessionPrefs.getString("active_app_user", null) ?: return null
        return try {
            val json = JSONObject(raw)
            val uid = json.getString("uid")
            val cleanEmail = json.getString("email").lowercase().trim()
            val permanentPhoto = customProfilePrefs.getString("permanent_avatar_${cleanEmail}", null)?.takeIf { it.isNotBlank() }

            val customName = customProfilePrefs.getString("${uid}_name", null)
                ?: customProfilePrefs.getString("${cleanEmail}_name", null)
            val customPhoto = customProfilePrefs.getString("${uid}_photo", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                ?: customProfilePrefs.getString("${cleanEmail}_photo", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                ?: permanentPhoto?.takeIf { it != "mascot_sealion" }
                ?: customProfilePrefs.getString("${uid}_photo", null)
                ?: json.optString("photoUrl", "").ifBlank { null }
            val customBio = customProfilePrefs.getString("${uid}_bio", null)
                ?: customProfilePrefs.getString("${cleanEmail}_bio", null)

            val appUser = AppUser(
                uid = uid,
                email = json.getString("email"),
                displayName = customName?.ifBlank { null } ?: json.getString("displayName"),
                photoUrl = customPhoto,
                bio = customBio?.ifBlank { null } ?: json.optString("bio", ""),
                isDev = json.optBoolean("isDev", false),
                isGoogleUser = json.optBoolean("isGoogleUser", false),
                lastEmailChangeTimestamp = json.optLong("lastEmailChangeTimestamp", 0L)
            )
            fetchRemoteProfileIfAvailable(uid, cleanEmail)
            appUser
        } catch (_: Exception) {
            null
        }
    }

    private fun fetchRemoteProfileIfAvailable(uid: String, email: String) {
        val cleanEmail = email.trim().lowercase()
        if (cleanEmail.isBlank()) return
        defaultScope.launch {
            try {
                val db = getFirestoreDb() ?: return@launch
                val snap = db.collection("users").whereEqualTo("email", cleanEmail).get().await()
                var bestRemotePhoto: String? = null
                var bestDisplayName: String? = null
                for (doc in snap.documents) {
                    val p = doc.getString("photoUrl")?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    if (p != null && bestRemotePhoto == null) {
                        bestRemotePhoto = p
                    }
                    val n = doc.getString("displayName")?.takeIf { it.isNotBlank() }
                    if (n != null && bestDisplayName == null) {
                        bestDisplayName = n
                    }
                }
                if (bestRemotePhoto != null) {
                    customProfilePrefs.edit()
                        .putString("${uid}_photo", bestRemotePhoto)
                        .putString("${cleanEmail}_photo", bestRemotePhoto)
                        .putString("permanent_avatar_${cleanEmail}", bestRemotePhoto)
                        .apply()

                    _currentUserState.value?.let { curr ->
                        if (curr.email.equals(cleanEmail, ignoreCase = true) &&
                            (curr.photoUrl.isNullOrBlank() || curr.photoUrl == "mascot_sealion" || curr.photoUrl != bestRemotePhoto)) {
                            _currentUserState.value = curr.copy(photoUrl = bestRemotePhoto)
                        }
                    }
                }
            } catch (_: Exception) {}
        }
    }

    private fun saveSession(user: AppUser?) {
        if (user == null) {
            sessionPrefs.edit().remove("active_app_user").apply()
        } else {
            val cleanEmail = user.email.lowercase().trim()
            val savedPermanent = customProfilePrefs.getString("permanent_avatar_${cleanEmail}", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
            val effectivePhoto = if (!user.photoUrl.isNullOrBlank() && user.photoUrl != "mascot_sealion") {
                user.photoUrl
            } else {
                savedPermanent ?: user.photoUrl
            }

            val edit = customProfilePrefs.edit()
                .putString("${user.uid}_name", user.displayName)
                .putString("${cleanEmail}_name", user.displayName)
                .putString("${user.uid}_bio", user.bio)
                .putString("${cleanEmail}_bio", user.bio)

            if (!effectivePhoto.isNullOrBlank() && effectivePhoto != "mascot_sealion") {
                edit.putString("${user.uid}_photo", effectivePhoto)
                edit.putString("${cleanEmail}_photo", effectivePhoto)
                edit.putString("permanent_avatar_${cleanEmail}", effectivePhoto)
            }
            edit.apply()

            val json = JSONObject().apply {
                put("uid", user.uid)
                put("email", user.email)
                put("displayName", user.displayName)
                put("photoUrl", effectivePhoto ?: "")
                put("bio", user.bio)
                put("isDev", user.isDev)
                put("isGoogleUser", user.isGoogleUser)
                put("lastEmailChangeTimestamp", user.lastEmailChangeTimestamp)
            }
            sessionPrefs.edit().putString("active_app_user", json.toString()).apply()
        }
    }

    private fun FirebaseUser.toAppUser(): AppUser {
        val userEmail = email ?: ""
        val cleanEmail = userEmail.lowercase().trim()
        val isDev = cleanEmail == "contatopedro21g@gmail.com"

        val permanentPhoto = if (cleanEmail.isNotBlank()) customProfilePrefs.getString("permanent_avatar_${cleanEmail}", null)?.takeIf { it.isNotBlank() } else null
        val rawGooglePhoto = photoUrl?.toString()?.takeIf { it.isNotBlank() }
        val accountPhoto = (findAccountByUid(uid)?.photoUrl ?: findAccountByEmail(userEmail)?.photoUrl)?.takeIf { it.isNotBlank() }

        val customName = customProfilePrefs.getString("${uid}_name", null)
            ?: customProfilePrefs.getString("${cleanEmail}_name", null)
            ?: findAccountByUid(uid)?.displayName
            ?: findAccountByEmail(userEmail)?.displayName

        val customPhoto = customProfilePrefs.getString("${uid}_photo", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
            ?: customProfilePrefs.getString("${cleanEmail}_photo", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
            ?: permanentPhoto?.takeIf { it != "mascot_sealion" }
            ?: rawGooglePhoto
            ?: accountPhoto?.takeIf { it != "mascot_sealion" }
            ?: customProfilePrefs.getString("${uid}_photo", null)
            ?: permanentPhoto
            ?: accountPhoto

        val customBio = customProfilePrefs.getString("${uid}_bio", null)
            ?: customProfilePrefs.getString("${cleanEmail}_bio", null)
            ?: findAccountByUid(uid)?.bio
            ?: findAccountByEmail(userEmail)?.bio
            ?: ""

        val effectiveName = customName?.ifBlank { null }
            ?: displayName?.ifBlank { null }
            ?: if (isDev) "Pedro (Dev Admin)" else "Estudante Knowverse"

        return AppUser(
            uid = uid,
            email = userEmail,
            displayName = effectiveName,
            photoUrl = customPhoto,
            bio = customBio,
            isDev = isDev,
            isGoogleUser = true
        )
    }

    // ========================================================
    // EMAIL & SENHA: CADASTRO COM CRIPTOGRAFIA E RESPOSTA SEGURA
    // ========================================================

    fun signUpWithEmail(
        email: String,
        password: String,
        displayName: String,
        securityQuestion: String,
        securityAnswer: String,
        onSuccess: (AppUser) -> Unit,
        onError: (String) -> Unit
    ) {
        val cleanEmail = email.trim().lowercase()
        val cleanName = displayName.trim().ifBlank { "Estudante" }
        val cleanAnswer = securityAnswer.trim().lowercase()

        if (cleanEmail.isBlank() || !cleanEmail.contains("@")) {
            onError("Por favor, insira um e-mail válido.")
            return
        }
        if (password.length < 6) {
            onError("A senha deve conter pelo menos 6 caracteres.")
            return
        }
        if (cleanAnswer.isBlank()) {
            onError("Por favor, responda à pergunta de segurança.")
            return
        }

        val existing = findAccountByEmail(cleanEmail)
        if (existing != null) {
            onError("Este e-mail já está cadastrado. Faça login ou use 'Esqueci a Senha'.")
            return
        }

        val salt = generateSalt()
        val passwordHash = hashString(password + salt)
        val answerHash = hashString(cleanAnswer + salt)
        val uid = "usr_" + UUID.randomUUID().toString().replace("-", "").take(12)
        val isDev = cleanEmail == "contatopedro21g@gmail.com"

        val account = RegisteredAccountRecord(
            uid = uid,
            email = cleanEmail,
            passwordHash = passwordHash,
            salt = salt,
            displayName = cleanName,
            securityQuestion = securityQuestion,
            securityAnswerHash = answerHash,
            photoUrl = "mascot_sealion",
            bio = "Estudante entusiasta de Programação e Matemática",
            lastEmailChangeTimestamp = System.currentTimeMillis()
        )
        saveAccountRecord(account)
        syncAccountToFirestore(account)

        val appUser = AppUser(
            uid = uid,
            email = cleanEmail,
            displayName = cleanName,
            photoUrl = account.photoUrl,
            bio = account.bio,
            isDev = isDev,
            isGoogleUser = false,
            lastEmailChangeTimestamp = account.lastEmailChangeTimestamp
        )

        saveSession(appUser)
        _currentUserState.value = appUser
        syncUserProfileToFirestore(appUser)
        onSuccess(appUser)
    }

    fun signInWithEmail(
        email: String,
        password: String,
        onSuccess: (AppUser) -> Unit,
        onError: (String) -> Unit
    ) {
        val cleanEmail = email.trim().lowercase()
        val localAccount = findAccountByEmail(cleanEmail)
        if (localAccount != null) {
            val inputHash = hashString(password + localAccount.salt)
            if (inputHash == localAccount.passwordHash) {
                val isDev = cleanEmail == "contatopedro21g@gmail.com"
                val savedPermanent = customProfilePrefs.getString("permanent_avatar_${cleanEmail}", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                val customPhoto = customProfilePrefs.getString("${localAccount.uid}_photo", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    ?: customProfilePrefs.getString("${cleanEmail}_photo", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    ?: savedPermanent
                    ?: localAccount.photoUrl?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                    ?: localAccount.photoUrl

                val appUser = AppUser(
                    uid = localAccount.uid,
                    email = localAccount.email,
                    displayName = localAccount.displayName,
                    photoUrl = customPhoto,
                    bio = localAccount.bio,
                    isDev = isDev,
                    isGoogleUser = false,
                    lastEmailChangeTimestamp = localAccount.lastEmailChangeTimestamp
                )

                saveSession(appUser)
                _currentUserState.value = appUser
                syncUserProfileToFirestore(appUser)
                syncAccountToFirestore(localAccount)
                onSuccess(appUser)
                return
            } else {
                onError("Senha incorreta. Verifique seus dados ou use 'Esqueci a Senha'.")
                return
            }
        }

        // Account not found locally - fetch from Firestore cloud accounts to recover across app versions/reinstalls!
        defaultScope.launch {
            try {
                val db = getFirestoreDb()
                if (db == null) {
                    withContext(Dispatchers.Main) {
                        onError("Conta não encontrada no dispositivo e banco de dados indisponível.")
                    }
                    return@launch
                }
                val snap = db.collection("accounts").document(cleanEmail).get().await()
                if (snap != null && snap.exists()) {
                    val salt = snap.getString("salt") ?: ""
                    val storedHash = snap.getString("passwordHash") ?: ""
                    val inputHash = hashString(password + salt)
                    if (inputHash == storedHash) {
                        val uid = snap.getString("uid") ?: ("usr_" + hashString(cleanEmail).take(12))
                        val name = snap.getString("displayName") ?: "Estudante"
                        val photo = snap.getString("photoUrl")
                        val bio = snap.getString("bio") ?: ""
                        val secQ = snap.getString("securityQuestion") ?: SECURITY_QUESTIONS[0]
                        val secAns = snap.getString("securityAnswerHash") ?: ""
                        val ts = snap.getLong("lastEmailChangeTimestamp") ?: 0L

                        val savedPermanent = customProfilePrefs.getString("permanent_avatar_${cleanEmail}", null)?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                        val effectivePhoto = photo?.takeIf { it.isNotBlank() && it != "mascot_sealion" }
                            ?: savedPermanent
                            ?: photo

                        val restored = RegisteredAccountRecord(
                            uid = uid,
                            email = cleanEmail,
                            passwordHash = storedHash,
                            salt = salt,
                            displayName = name,
                            securityQuestion = secQ,
                            securityAnswerHash = secAns,
                            photoUrl = effectivePhoto,
                            bio = bio,
                            lastEmailChangeTimestamp = ts
                        )
                        saveAccountRecord(restored)

                        val appUser = AppUser(
                            uid = uid,
                            email = cleanEmail,
                            displayName = name,
                            photoUrl = effectivePhoto,
                            bio = bio,
                            isDev = cleanEmail == "contatopedro21g@gmail.com",
                            isGoogleUser = false,
                            lastEmailChangeTimestamp = ts
                        )
                        saveSession(appUser)
                        _currentUserState.value = appUser
                        syncUserProfileToFirestore(appUser)
                        withContext(Dispatchers.Main) {
                            onSuccess(appUser)
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            onError("Senha incorreta. Verifique seus dados ou use 'Esqueci a Senha'.")
                        }
                    }
                } else {
                    withContext(Dispatchers.Main) {
                        onError("Conta não encontrada com este e-mail.")
                    }
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    onError("Conta não encontrada ou erro de conexão: ${e.message}")
                }
            }
        }
    }

    // ========================================================
    // ESQUECI A SENHA COM PERGUNTA PESSOAL E TOKEN DE ACESSO
    // ========================================================

    fun getSecurityQuestionForEmail(email: String): String? {
        val account = findAccountByEmail(email.trim().lowercase())
        return account?.securityQuestion
    }

    fun requestPasswordReset(
        email: String,
        securityAnswer: String,
        onSuccessToken: (token: String) -> Unit,
        onError: (String) -> Unit
    ) {
        val cleanEmail = email.trim().lowercase()
        val account = findAccountByEmail(cleanEmail)
        if (account == null) {
            onError("E-mail não encontrado no sistema.")
            return
        }

        val cleanAnswer = securityAnswer.trim().lowercase()
        val testHash = hashString(cleanAnswer + account.salt)
        if (testHash != account.securityAnswerHash) {
            onError("Resposta incorreta para a pergunta de segurança.")
            return
        }

        // Generate short, high-entropy access token
        val tokenCode = "KW-" + (1000 + (Math.random() * 9000).toInt()) + "-" +
                UUID.randomUUID().toString().take(4).uppercase()

        account.temporaryAccessToken = tokenCode
        saveAccountRecord(account)
        onSuccessToken(tokenCode)
    }

    fun signInWithAccessToken(
        email: String,
        token: String,
        onSuccess: (AppUser) -> Unit,
        onError: (String) -> Unit
    ) {
        val cleanEmail = email.trim().lowercase()
        val account = findAccountByEmail(cleanEmail)
        if (account == null) {
            onError("Conta não encontrada.")
            return
        }

        val storedToken = account.temporaryAccessToken
        if (storedToken.isNullOrBlank() || storedToken != token.trim()) {
            onError("Token de acesso inválido ou expirado.")
            return
        }

        // Clear used one-time token
        account.temporaryAccessToken = null
        saveAccountRecord(account)

        val isDev = cleanEmail == "contatopedro21g@gmail.com"
        val appUser = AppUser(
            uid = account.uid,
            email = account.email,
            displayName = account.displayName,
            photoUrl = account.photoUrl,
            bio = account.bio,
            isDev = isDev,
            isGoogleUser = false,
            lastEmailChangeTimestamp = account.lastEmailChangeTimestamp
        )

        saveSession(appUser)
        _currentUserState.value = appUser
        onSuccess(appUser)
    }

    // ========================================================
    // ALTERAÇÃO DE INFORMAÇÕES DE PERFIL, SENHA E EMAIL (60 DIAS)
    // ========================================================

    fun updateProfile(
        displayName: String,
        photoUrl: String?,
        bio: String,
        onSuccess: (AppUser) -> Unit,
        onError: (String) -> Unit
    ) {
        val current = _currentUserState.value ?: run {
            onError("Nenhum usuário logado.")
            return
        }

        val updated = current.copy(
            displayName = displayName.trim().ifBlank { current.displayName },
            photoUrl = photoUrl,
            bio = bio.trim()
        )

        val cleanEmail = current.email.lowercase().trim()
        val prefEdit = customProfilePrefs.edit()
            .putString("${current.uid}_name", updated.displayName)
            .putString("${cleanEmail}_name", updated.displayName)
            .putString("${current.uid}_bio", updated.bio)
            .putString("${cleanEmail}_bio", updated.bio)

        if (!updated.photoUrl.isNullOrBlank() && updated.photoUrl != "mascot_sealion") {
            prefEdit.putString("${current.uid}_photo", updated.photoUrl)
            prefEdit.putString("${cleanEmail}_photo", updated.photoUrl)
            prefEdit.putString("permanent_avatar_${cleanEmail}", updated.photoUrl)
        }
        prefEdit.apply()

        val account = findAccountByUid(current.uid) ?: findAccountByEmail(current.email)
        if (account != null) {
            account.displayName = updated.displayName
            account.photoUrl = updated.photoUrl
            account.bio = updated.bio
            saveAccountRecord(account)
            syncAccountToFirestore(account)
        } else {
            val salt = generateSalt()
            val newRecord = RegisteredAccountRecord(
                uid = current.uid,
                email = current.email,
                passwordHash = hashString("account_${current.uid}" + salt),
                salt = salt,
                displayName = updated.displayName,
                securityQuestion = SECURITY_QUESTIONS[0],
                securityAnswerHash = hashString("security" + salt),
                photoUrl = updated.photoUrl,
                bio = updated.bio,
                lastEmailChangeTimestamp = current.lastEmailChangeTimestamp
            )
            saveAccountRecord(newRecord)
            syncAccountToFirestore(newRecord)
        }

        saveSession(updated)
        _currentUserState.value = updated

        try {
            auth.currentUser?.let { fbUser ->
                val profileChange = com.google.firebase.auth.UserProfileChangeRequest.Builder()
                    .setDisplayName(updated.displayName)
                    .build()
                fbUser.updateProfile(profileChange)
            }
        } catch (_: Exception) {}

        syncUserProfileToFirestore(updated)
        onSuccess(updated)
    }

    fun getCustomPhotoForUser(uidOrEmail: String?): String? {
        if (uidOrEmail.isNullOrBlank()) return null
        val clean = uidOrEmail.trim().lowercase()
        val p1 = customProfilePrefs.getString("${uidOrEmail}_photo", null)?.takeIf { it.isNotBlank() }
        if (p1 != null) return p1
        val p2 = customProfilePrefs.getString("${clean}_photo", null)?.takeIf { it.isNotBlank() }
        if (p2 != null) return p2
        val acc = findAccountByUid(uidOrEmail) ?: findAccountByEmail(clean)
        if (!acc?.photoUrl.isNullOrBlank()) return acc?.photoUrl
        return null
    }

    fun updatePassword(
        newPass: String,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val current = _currentUserState.value ?: run {
            onError("Usuário não autenticado.")
            return
        }
        if (newPass.length < 6) {
            onError("A nova senha deve ter no mínimo 6 caracteres.")
            return
        }

        val account = findAccountByUid(current.uid)
        if (account == null) {
            onError("Conta não encontrada para redefinição de senha.")
            return
        }

        val newSalt = generateSalt()
        account.salt = newSalt
        account.passwordHash = hashString(newPass + newSalt)
        account.temporaryAccessToken = null
        saveAccountRecord(account)
        syncAccountToFirestore(account)
        onSuccess()
    }

    fun checkEmailChangeEligibility(user: AppUser): Pair<Boolean, Long> {
        val now = System.currentTimeMillis()
        val lastChange = user.lastEmailChangeTimestamp
        val elapsed = now - lastChange
        if (lastChange == 0L || elapsed >= TWO_MONTHS_MILLIS) {
            return Pair(true, 0L)
        }
        val remainingMillis = TWO_MONTHS_MILLIS - elapsed
        val remainingDays = (remainingMillis / (24L * 60L * 60L * 1000L)).coerceAtLeast(1L)
        return Pair(false, remainingDays)
    }

    fun updateEmail(
        newEmail: String,
        onSuccess: (AppUser) -> Unit,
        onError: (String) -> Unit
    ) {
        val current = _currentUserState.value ?: run {
            onError("Nenhum usuário logado.")
            return
        }

        val clean = newEmail.trim().lowercase()
        if (clean.isBlank() || !clean.contains("@")) {
            onError("Formato de e-mail inválido.")
            return
        }
        if (clean == current.email.lowercase()) {
            onError("O novo e-mail é idêntico ao atual.")
            return
        }

        val (canChange, daysLeft) = checkEmailChangeEligibility(current)
        if (!canChange) {
            onError("Você só pode alterar seu e-mail uma vez a cada 2 meses. Próxima alteração disponível em $daysLeft dias.")
            return
        }

        val existing = findAccountByEmail(clean)
        if (existing != null && existing.uid != current.uid) {
            onError("Este e-mail já está sendo utilizado por outra conta.")
            return
        }

        val now = System.currentTimeMillis()
        val account = findAccountByUid(current.uid)
        if (account != null) {
            account.email = clean
            account.lastEmailChangeTimestamp = now
            saveAccountRecord(account)
        }

        val updated = current.copy(
            email = clean,
            lastEmailChangeTimestamp = now
        )
        saveSession(updated)
        _currentUserState.value = updated
        syncUserProfileToFirestore(updated)
        onSuccess(updated)
    }

    // ========================================================
    // GOOGLE SIGN-IN
    // ========================================================

    fun signInWithGoogle(
        activity: Activity,
        scope: CoroutineScope,
        onSuccess: (AppUser) -> Unit = {},
        onError: (String) -> Unit = {}
    ) {
        _isSigningIn.value = true
        _authErrorMessage.value = null

        scope.launch {
            try {
                val serverClientId = context.getString(R.string.default_web_client_id)
                val googleIdOption = GetSignInWithGoogleOption.Builder(serverClientId)
                    .build()

                val request = GetCredentialRequest.Builder()
                    .addCredentialOption(googleIdOption)
                    .build()

                val result = credentialManager.getCredential(activity, request)
                val credential = result.credential

                if (credential is CustomCredential &&
                    credential.type == TYPE_GOOGLE_ID_TOKEN_CREDENTIAL
                ) {
                    val googleIdTokenCredential = GoogleIdTokenCredential.createFrom(credential.data)
                    val idToken = googleIdTokenCredential.idToken

                    val firebaseCredential = GoogleAuthProvider.getCredential(idToken, null)
                    val authResult = auth.signInWithCredential(firebaseCredential).await()
                    val fbUser = authResult.user

                    _isSigningIn.value = false
                    if (fbUser != null) {
                        val appUser = fbUser.toAppUser()
                        saveSession(appUser)
                        _currentUserState.value = appUser
                        syncUserProfileToFirestore(appUser)
                        onSuccess(appUser)
                    } else {
                        val err = "Falha ao obter credenciais do usuário autenticado."
                        _authErrorMessage.value = err
                        onError(err)
                    }
                } else {
                    _isSigningIn.value = false
                    val err = "Tipo de credencial incompatível com o dispositivo."
                    _authErrorMessage.value = err
                    onError(err)
                }
            } catch (e: NoCredentialException) {
                Log.w("AuthManager", "Nenhuma credencial encontrada: ${e.message}")
                _isSigningIn.value = false
                val err = "Nenhuma conta Google ativa selecionada no dispositivo."
                _authErrorMessage.value = err
                onError(err)
            } catch (e: GetCredentialCancellationException) {
                Log.w("AuthManager", "Google Sign-In cancelado pelo usuário: ${e.message}")
                _isSigningIn.value = false
            } catch (e: Exception) {
                Log.e("AuthManager", "Falha na autenticação Google", e)
                _isSigningIn.value = false
                val err = e.localizedMessage ?: "Erro durante o login com Google"
                _authErrorMessage.value = err
                onError(err)
            }
        }
    }

    fun signOut(scope: CoroutineScope = defaultScope, onComplete: () -> Unit = {}) {
        auth.signOut()
        saveSession(null)
        _currentUserState.value = null
        scope.launch {
            try {
                credentialManager.clearCredentialState(ClearCredentialStateRequest())
            } catch (e: Exception) {
                Log.e("AuthManager", "Falha ao limpar credenciais", e)
            } finally {
                onComplete()
            }
        }
    }

    fun deleteAccount(
        progressRepository: com.example.data.local.UserProgressRepository? = null,
        scope: CoroutineScope = defaultScope,
        onSuccess: () -> Unit,
        onError: (String) -> Unit
    ) {
        val current = _currentUserState.value
        if (current == null) {
            onError("Nenhuma conta conectada para excluir.")
            return
        }

        val targetUid = current.uid
        val targetEmail = current.email.lowercase()

        scope.launch {
            try {
                // 1. Remove from local registered accounts vault
                val all = loadAllAccounts().toMutableList()
                all.removeAll { it.uid == targetUid || it.email.equals(targetEmail, ignoreCase = true) }
                val arr = JSONArray()
                for (rec in all) {
                    val obj = JSONObject().apply {
                        put("uid", rec.uid)
                        put("email", rec.email)
                        put("passwordHash", rec.passwordHash)
                        put("salt", rec.salt)
                        put("displayName", rec.displayName)
                        put("securityQuestion", rec.securityQuestion)
                        put("securityAnswerHash", rec.securityAnswerHash)
                        put("photoUrl", rec.photoUrl ?: "")
                        put("bio", rec.bio)
                        put("lastEmailChangeTimestamp", rec.lastEmailChangeTimestamp)
                        put("temporaryAccessToken", rec.temporaryAccessToken ?: "")
                    }
                    arr.put(obj)
                }
                accountsPrefs.edit().putString("registered_accounts_json", arr.toString()).apply()

                // 2. Remove custom profile preferences
                customProfilePrefs.edit()
                    .remove("${targetUid}_name")
                    .remove("${targetEmail}_name")
                    .remove("${targetUid}_photo")
                    .remove("${targetEmail}_photo")
                    .remove("${targetUid}_bio")
                    .remove("${targetEmail}_bio")
                    .apply()

                // 3. Clear active session and state
                saveSession(null)
                _currentUserState.value = null

                // 4. Delete user progress data in repository
                progressRepository?.deleteUserData(targetUid)

                // 5. Delete from Firebase Auth if user is signed in
                try {
                    auth.currentUser?.delete()?.await()
                } catch (e: Exception) {
                    Log.w("AuthManager", "Firebase Auth delete warning: ${e.message}")
                }
                auth.signOut()

                // 6. Delete from remote Firestore document if exists
                try {
                    val dbId = try { context.getString(R.string.firestore_database_id) } catch (_: Exception) { null }
                    val firestore = if (dbId != null) FirebaseFirestore.getInstance(dbId) else FirebaseFirestore.getInstance()
                    firestore.collection("users").document(targetUid).delete().await()
                } catch (e: Exception) {
                    Log.w("AuthManager", "Firestore doc delete warning: ${e.message}")
                }

                // 7. Clear Credential Manager state
                try {
                    credentialManager.clearCredentialState(ClearCredentialStateRequest())
                } catch (_: Exception) {}

                kotlinx.coroutines.withContext(Dispatchers.Main) {
                    onSuccess()
                }
            } catch (e: Exception) {
                Log.e("AuthManager", "Erro ao excluir conta", e)
                kotlinx.coroutines.withContext(Dispatchers.Main) {
                    onError("Erro ao excluir conta: ${e.message}")
                }
            }
        }
    }

    // ========================================================
    // PERSISTÊNCIA CRIPTOGRÁFICA LOCAL DE CONTAS
    // ========================================================

    private fun findAccountByEmail(email: String): RegisteredAccountRecord? {
        val list = loadAllAccounts()
        return list.firstOrNull { it.email.equals(email, ignoreCase = true) }
    }

    private fun findAccountByUid(uid: String): RegisteredAccountRecord? {
        val list = loadAllAccounts()
        return list.firstOrNull { it.uid == uid }
    }

    fun getAllRegisteredUsers(): List<AppUser> {
        val list = loadAllAccounts()
        return list.map {
            AppUser(
                uid = it.uid,
                email = it.email,
                displayName = it.displayName,
                photoUrl = it.photoUrl,
                bio = it.bio,
                isDev = it.email.equals("contatopedro21g@gmail.com", ignoreCase = true),
                isGoogleUser = false,
                lastEmailChangeTimestamp = it.lastEmailChangeTimestamp
            )
        }
    }

    private fun saveAccountRecord(account: RegisteredAccountRecord) {
        val list = loadAllAccounts().toMutableList()
        val index = list.indexOfFirst { it.uid == account.uid }
        if (index >= 0) {
            list[index] = account
        } else {
            list.add(account)
        }

        val arr = JSONArray()
        for (rec in list) {
            val obj = JSONObject().apply {
                put("uid", rec.uid)
                put("email", rec.email)
                put("passwordHash", rec.passwordHash)
                put("salt", rec.salt)
                put("displayName", rec.displayName)
                put("securityQuestion", rec.securityQuestion)
                put("securityAnswerHash", rec.securityAnswerHash)
                put("photoUrl", rec.photoUrl ?: "")
                put("bio", rec.bio)
                put("lastEmailChangeTimestamp", rec.lastEmailChangeTimestamp)
                put("temporaryAccessToken", rec.temporaryAccessToken ?: "")
            }
            arr.put(obj)
        }
        accountsPrefs.edit().putString("registered_accounts_json", arr.toString()).apply()
    }

    private fun loadAllAccounts(): List<RegisteredAccountRecord> {
        val raw = accountsPrefs.getString("registered_accounts_json", null) ?: return emptyList()
        return try {
            val arr = JSONArray(raw)
            val list = mutableListOf<RegisteredAccountRecord>()
            for (i in 0 until arr.length()) {
                val obj = arr.getJSONObject(i)
                list.add(
                    RegisteredAccountRecord(
                        uid = obj.getString("uid"),
                        email = obj.getString("email"),
                        passwordHash = obj.getString("passwordHash"),
                        salt = obj.getString("salt"),
                        displayName = obj.getString("displayName"),
                        securityQuestion = obj.getString("securityQuestion"),
                        securityAnswerHash = obj.getString("securityAnswerHash"),
                        photoUrl = obj.optString("photoUrl", "").ifBlank { null },
                        bio = obj.optString("bio", ""),
                        lastEmailChangeTimestamp = obj.optLong("lastEmailChangeTimestamp", 0L),
                        temporaryAccessToken = obj.optString("temporaryAccessToken", "").ifBlank { null }
                    )
                )
            }
            list
        } catch (_: Exception) {
            emptyList()
        }
    }

    private fun generateSalt(): String {
        val random = SecureRandom()
        val bytes = ByteArray(16)
        random.nextBytes(bytes)
        return bytes.joinToString("") { "%02x".format(it) }
    }

    private fun hashString(input: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hash = digest.digest(input.toByteArray(Charsets.UTF_8))
        return hash.joinToString("") { "%02x".format(it) }
    }

    fun getFirestoreDb(): FirebaseFirestore? {
        return try {
            val dbId = try { context.getString(R.string.firestore_database_id) } catch (_: Exception) { null }
            if (dbId != null) FirebaseFirestore.getInstance(dbId) else FirebaseFirestore.getInstance()
        } catch (e: Exception) {
            Log.w("AuthManager", "Firestore access: ${e.message}")
            null
        }
    }

    fun syncAccountToFirestore(account: RegisteredAccountRecord) {
        defaultScope.launch {
            try {
                val db = getFirestoreDb() ?: return@launch
                val data = mapOf(
                    "uid" to account.uid,
                    "email" to account.email.lowercase().trim(),
                    "passwordHash" to account.passwordHash,
                    "salt" to account.salt,
                    "displayName" to account.displayName,
                    "securityQuestion" to account.securityQuestion,
                    "securityAnswerHash" to account.securityAnswerHash,
                    "photoUrl" to (account.photoUrl ?: ""),
                    "bio" to account.bio,
                    "lastEmailChangeTimestamp" to account.lastEmailChangeTimestamp,
                    "updatedAt" to com.google.firebase.Timestamp.now()
                )
                db.collection("accounts").document(account.email.lowercase().trim())
                    .set(data, com.google.firebase.firestore.SetOptions.merge())
            } catch (e: Exception) {
                Log.w("AuthManager", "Sync account to Firestore: ${e.message}")
            }
        }
    }

    fun syncUserProfileToFirestore(user: AppUser) {
        defaultScope.launch {
            try {
                val db = getFirestoreDb() ?: return@launch
                val userDoc = db.collection("users").document(user.uid)
                val data = mapOf(
                    "userId" to user.uid,
                    "email" to user.email,
                    "displayName" to user.displayName,
                    "photoUrl" to (user.photoUrl ?: ""),
                    "bio" to user.bio,
                    "isDev" to user.isDev,
                    "updatedAt" to com.google.firebase.Timestamp.now()
                )
                userDoc.set(data, com.google.firebase.firestore.SetOptions.merge())
            } catch (e: Exception) {
                Log.w("AuthManager", "Sync Firestore user doc: ${e.message}")
            }
        }
    }
}
