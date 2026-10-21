package com.example.data.security

import android.content.Context
import android.util.Base64
import com.example.BuildConfig
import java.nio.charset.StandardCharsets
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

/**
 * Gerenciador de Chave de API com Criptografia e Proteção para Repositórios Open-Source.
 *
 * Estratégia de Segurança para Código Aberto no GitHub:
 * 1. O arquivo `.env` com a chave real é estritamente ignorado no `.gitignore`.
 * 2. O arquivo `.env.example` contém apenas o placeholder público (`DEFAULT_API_KEY`).
 * 3. Nenhuma chave de API fica escrita diretamente no código-fonte no GitHub.
 * 4. No dispositivo, a chave pode ser provida via BuildConfig (Secrets do AI Studio)
 *    ou armazenada no sandbox privado do aplicativo de forma criptografada (AES-128).
 */
object ApiKeyManager {

    private const val PREFS_NAME = "knowverse_secure_prefs"
    private const val KEY_ENCRYPTED_API_KEY = "enc_gemini_api_key"

    // Chave de derivação interna para criptografia local no dispositivo
    private val AES_KEY = byteArrayOf(
        0x4B, 0x6E, 0x6F, 0x77, 0x76, 0x65, 0x72, 0x73,
        0x65, 0x41, 0x49, 0x53, 0x65, 0x63, 0x75, 0x72
    )
    private val AES_IV = byteArrayOf(
        0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
        0x09, 0x0A, 0x0B, 0x0C, 0x0D, 0x0E, 0x0F, 0x10
    )

    /**
     * Obtém a chave da API Gemini ativa.
     * Prioridade:
     * 1. Chave criptografada salva localmente no sandbox do aplicativo pelo usuário
     * 2. Chave injetada pelo BuildConfig através dos Secrets do AI Studio
     */
    fun getActiveApiKey(context: Context?): String {
        // 1. Verifica armazenamento criptografado local
        if (context != null) {
            val localDecrypted = getStoredEncryptedKey(context)
            if (localDecrypted.isNotBlank()) {
                return localDecrypted
            }
        }

        // 2. Verifica BuildConfig injetado pelo Secrets Gradle Plugin
        return try {
            val field = BuildConfig::class.java.getField("GEMINI_API_KEY")
            val buildConfigKey = (field.get(null) as? String) ?: ""
            if (buildConfigKey.isNotBlank() && buildConfigKey != "DEFAULT_API_KEY") {
                buildConfigKey.trim()
            } else {
                ""
            }
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Salva a chave fornecida pelo usuário de forma criptografada no SharedPreferences privado.
     */
    fun saveEncryptedApiKey(context: Context, rawApiKey: String): Boolean {
        return try {
            val trimmed = rawApiKey.trim()
            if (trimmed.isBlank()) {
                clearApiKey(context)
                return true
            }

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val keySpec = SecretKeySpec(AES_KEY, "AES")
            val ivSpec = IvParameterSpec(AES_IV)
            cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec)

            val encryptedBytes = cipher.doFinal(trimmed.toByteArray(StandardCharsets.UTF_8))
            val base64Encoded = Base64.encodeToString(encryptedBytes, Base64.NO_WRAP)

            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            prefs.edit().putString(KEY_ENCRYPTED_API_KEY, base64Encoded).apply()
            true
        } catch (e: Exception) {
            false
        }
    }

    /**
     * Recupera e descriptografa a chave armazenada localmente.
     */
    fun getStoredEncryptedKey(context: Context): String {
        return try {
            val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val base64Encoded = prefs.getString(KEY_ENCRYPTED_API_KEY, null) ?: return ""

            val cipher = Cipher.getInstance("AES/CBC/PKCS5Padding")
            val keySpec = SecretKeySpec(AES_KEY, "AES")
            val ivSpec = IvParameterSpec(AES_IV)
            cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec)

            val encryptedBytes = Base64.decode(base64Encoded, Base64.NO_WRAP)
            val decryptedBytes = cipher.doFinal(encryptedBytes)
            String(decryptedBytes, StandardCharsets.UTF_8).trim()
        } catch (e: Exception) {
            ""
        }
    }

    /**
     * Remove a chave salva localmente.
     */
    fun clearApiKey(context: Context) {
        val prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        prefs.edit().remove(KEY_ENCRYPTED_API_KEY).apply()
    }

    /**
     * Verifica se existe uma chave configurada (via BuildConfig ou localmente).
     */
    fun isKeyConfigured(context: Context?): Boolean {
        return getActiveApiKey(context).isNotBlank()
    }
}
