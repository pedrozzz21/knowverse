package com.example

import android.app.Application
import android.content.Context
import android.util.Log
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

        fun ensureFirebase(context: Context) {
            try {
                if (FirebaseApp.getApps(context).isEmpty()) {
                    val app = FirebaseApp.initializeApp(context)
                    if (app == null) {
                        val options = FirebaseOptions.Builder()
                            .setApplicationId("1:226615385467:android:17bcf942e178a597b871de")
                            .setApiKey("AIzaSyCl_X-wXTAkOv4mUSdBVLKc8-Jx2BTmj5c")
                            .setProjectId("gen-lang-client-0632265588")
                            .setStorageBucket("gen-lang-client-0632265588.firebasestorage.app")
                            .setGcmSenderId("226615385467")
                            .build()
                        FirebaseApp.initializeApp(context, options)
                        Log.i(TAG, "FirebaseApp inicializado com opções explícitas de fallback")
                    } else {
                        Log.i(TAG, "FirebaseApp inicializado com sucesso via resources")
                    }
                }
            } catch (e: Exception) {
                Log.e(TAG, "Erro ao inicializar FirebaseApp default: ${e.message}", e)
                try {
                    val options = FirebaseOptions.Builder()
                        .setApplicationId("1:226615385467:android:17bcf942e178a597b871de")
                        .setApiKey("AIzaSyCl_X-wXTAkOv4mUSdBVLKc8-Jx2BTmj5c")
                        .setProjectId("gen-lang-client-0632265588")
                        .setStorageBucket("gen-lang-client-0632265588.firebasestorage.app")
                        .setGcmSenderId("226615385467")
                        .build()
                    FirebaseApp.initializeApp(context, options)
                    Log.i(TAG, "FirebaseApp recuperado com opções manuais")
                } catch (ex2: Exception) {
                    Log.e(TAG, "Falha crítica na inicialização do Firebase: ${ex2.message}", ex2)
                }
            }
        }
    }
}
