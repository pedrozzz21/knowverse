package com.example

import android.app.Application
import android.content.Context
import android.util.Log
import com.example.data.security.ApiKeyManager
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions

/**
 * Aplicação Android principal do Knowverse.
 * Garante a inicialização segura e precoce do Firebase com fallback resiliente.
 */
class KnowverseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        cleanupCorruptChromiumSeeds(this)
        ensureFirebase(this)
        com.example.data.time.NetworkTimeProvider.init(this)
    }

    companion object {
        private const val TAG = "KnowverseApp"

        private fun cleanupCorruptChromiumSeeds(context: Context) {
            try {
                val webviewDir = java.io.File(context.applicationInfo.dataDir, "app_webview")
                if (webviewDir.exists()) {
                    val seed = java.io.File(webviewDir, "variations_seed")
                    val seedNew = java.io.File(webviewDir, "variations_seed_new")
                    if (seed.exists() && seed.length() == 0L) {
                        seed.delete()
                    }
                    if (seedNew.exists() && seedNew.length() == 0L) {
                        seedNew.delete()
                    }
                }
            } catch (_: Exception) {}
        }

        private fun resolveFirebaseApiKey(context: Context): String {
            // 1. Tenta obter FIREBASE_API_KEY injetada via Secrets Gradle Plugin (.env)
            val fromBuildConfig = try {
                val field = BuildConfig::class.java.getField("FIREBASE_API_KEY")
                (field.get(null) as? String)?.takeIf { it.isNotBlank() && it != "DEFAULT_API_KEY" }
            } catch (_: Exception) { null }

            if (!fromBuildConfig.isNullOrBlank()) {
                return fromBuildConfig.trim()
            }

            // 2. Tenta chave ativa provida pelo ApiKeyManager (armazenada de forma segura ou GEMINI_API_KEY)
            val fromManager = ApiKeyManager.getActiveApiKey(context).takeIf { it.isNotBlank() }
            if (!fromManager.isNullOrBlank()) {
                return fromManager.trim()
            }

            // 3. Fallback de resources caso google-services.json tenha sido processado
            val resApiKey = try {
                FirebaseOptions.fromResource(context)?.apiKey?.takeIf {
                    it.isNotBlank() && !it.contains("Dummy")
                }
            } catch (_: Exception) { null }

            return resApiKey.orEmpty().trim()
        }

        private fun initFirebaseFallback(context: Context) {
            try {
                val resolvedKey = resolveFirebaseApiKey(context)
                if (resolvedKey.isNotBlank()) {
                    val options = FirebaseOptions.Builder()
                        .setApplicationId("1:226615385467:android:17bcf942e178a597b871de")
                        .setApiKey(resolvedKey)
                        .setProjectId("gen-lang-client-0632265588")
                        .setStorageBucket("gen-lang-client-0632265588.firebasestorage.app")
                        .setGcmSenderId("226615385467")
                        .build()
                    FirebaseApp.initializeApp(context, options)
                    Log.i(TAG, "FirebaseApp inicializado com chave segura injetada via ambiente/storage")
                } else {
                    Log.w(TAG, "Nenhuma chave Firebase ativa encontrada em .env ou resources. Modo offline.")
                }
            } catch (ex2: Exception) {
                Log.e(TAG, "Falha na inicialização resiliente do Firebase: ${ex2.message}", ex2)
            }
        }

        fun ensureFirebase(context: Context) {
            try {
                if (FirebaseApp.getApps(context).isEmpty()) {
                    val app = FirebaseApp.initializeApp(context)
                    if (app == null) {
                        initFirebaseFallback(context)
                    } else {
                        Log.i(TAG, "FirebaseApp inicializado com sucesso via resources")
                    }
                }
            } catch (e: Exception) {
                Log.w(TAG, "Aviso ao inicializar FirebaseApp default: ${e.message}")
                initFirebaseFallback(context)
            }
        }
    }
}
