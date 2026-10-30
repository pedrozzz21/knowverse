package com.example.data.time

import android.content.Context
import android.content.SharedPreferences
import android.os.SystemClock
import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

/**
 * Provedor Oficial de Horário em GMT-3 integrado ao TimeAPI.world (https://timeapi.world).
 * Fuso horário: America/Sao_Paulo (Horário Padrão de Brasília, GMT-3).
 *
 * Garante que a contagem e expiração diária do streak (virada às 00h00 GMT-3)
 * opere com horário de servidor imune a alterações manuais no relógio do aparelho,
 * com fallback gracioso para relógio local em GMT-3 quando offline.
 */
object NetworkTimeProvider {

    private const val TAG = "NetworkTimeProvider"
    private const val PREFS_NAME = "knowverse_time_api_prefs"
    private const val KEY_LAST_KNOWN_SERVER_TIME_MS = "last_known_server_time_ms"
    private const val KEY_BOOT_ELAPSED_REALTIME = "boot_elapsed_realtime"
    private const val KEY_CACHED_GMT3_DATE = "cached_gmt3_date"

    val ZONE_GMT_MINUS_3: ZoneId = ZoneId.of("America/Sao_Paulo")
    private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(3, TimeUnit.SECONDS)
        .readTimeout(3, TimeUnit.SECONDS)
        .build()

    // Endpoints do timeapi.world para GMT-3
    private const val PRIMARY_URL = "https://gateway.timeapi.world/timezone/America/Sao_Paulo"
    private const val FALLBACK_URL = "https://timeapi.world/timezone/America/Sao_Paulo"

    private var prefs: SharedPreferences? = null

    // Offset calibrado: serverUnixTimeMs - SystemClock.elapsedRealtime()
    @Volatile
    private var serverTimeOffsetMs: Long? = null

    private val _isSyncedWithTimeApi = MutableStateFlow(false)
    val isSyncedWithTimeApi: StateFlow<Boolean> = _isSyncedWithTimeApi.asStateFlow()

    fun init(context: Context) {
        if (prefs == null) {
            prefs = context.applicationContext.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
            val savedServerMs = prefs?.getLong(KEY_LAST_KNOWN_SERVER_TIME_MS, 0L) ?: 0L
            val savedElapsedMs = prefs?.getLong(KEY_BOOT_ELAPSED_REALTIME, 0L) ?: 0L
            if (savedServerMs > 0L && savedElapsedMs > 0L) {
                val delta = SystemClock.elapsedRealtime() - savedElapsedMs
                if (delta in 0L..86_400_000L) {
                    serverTimeOffsetMs = (savedServerMs + delta) - SystemClock.elapsedRealtime()
                }
            }
        }
        syncTimeAsync()
    }

    /**
     * Sincroniza o horário do app com a API oficial timeapi.world (GMT-3).
     */
    fun syncTimeAsync(scope: CoroutineScope = CoroutineScope(Dispatchers.IO), onComplete: (() -> Unit)? = null) {
        scope.launch {
            val success = fetchFromTimeApi(PRIMARY_URL) || fetchFromTimeApi(FALLBACK_URL)
            if (success) {
                _isSyncedWithTimeApi.value = true
                Log.i(TAG, "Horário sincronizado com sucesso com TimeAPI.world (GMT-3): ${getTodayGmt3()}")
            } else {
                Log.w(TAG, "TimeAPI.world offline/inacessível. Utilizando horário local calibrado em GMT-3: ${getTodayGmt3()}")
            }
            onComplete?.invoke()
        }
    }

    private fun fetchFromTimeApi(url: String): Boolean {
        return try {
            val request = Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("User-Agent", "Knowverse-Android-Streak/1.7.0")
                .build()

            httpClient.newCall(request).execute().use { response ->
                if (!response.isSuccessful) return false
                val body = response.body?.string() ?: return false
                val json = JSONObject(body)

                // Extrai o datetime ISO-8601 (ex: "2026-10-27T14:23:40.837-03:00") ou unixtime
                val dateTimeStr = json.optString("datetime", "")
                val unixtimeSec = json.optLong("unixtime", 0L)

                val serverMs = if (unixtimeSec > 0L) {
                    unixtimeSec * 1000L
                } else if (dateTimeStr.isNotBlank()) {
                    ZonedDateTime.parse(dateTimeStr).toInstant().toEpochMilli()
                } else {
                    return false
                }

                val currentElapsed = SystemClock.elapsedRealtime()
                serverTimeOffsetMs = serverMs - currentElapsed

                prefs?.edit()
                    ?.putLong(KEY_LAST_KNOWN_SERVER_TIME_MS, serverMs)
                    ?.putLong(KEY_BOOT_ELAPSED_REALTIME, currentElapsed)
                    ?.putString(KEY_CACHED_GMT3_DATE, getTodayGmt3())
                    ?.apply()

                true
            }
        } catch (e: Exception) {
            Log.w(TAG, "Falha na requisição para $url: ${e.message}")
            false
        }
    }

    /**
     * Retorna a data atual no fuso GMT-3 no formato 'YYYY-MM-DD'.
     */
    fun getTodayGmt3(): String {
        val currentGmt3DateTime = getCurrentGmt3DateTime()
        return currentGmt3DateTime.format(DATE_FORMATTER)
    }

    /**
     * Retorna a data de ontem no fuso GMT-3 no formato 'YYYY-MM-DD'.
     */
    fun getYesterdayGmt3(): String {
        val currentGmt3DateTime = getCurrentGmt3DateTime()
        return currentGmt3DateTime.minusDays(1).format(DATE_FORMATTER)
    }

    /**
     * Retorna o objeto ZonedDateTime atual em GMT-3 (America/Sao_Paulo).
     */
    fun getCurrentGmt3DateTime(): ZonedDateTime {
        val offset = serverTimeOffsetMs
        return if (offset != null) {
            val estimatedServerEpochMs = SystemClock.elapsedRealtime() + offset
            Instant.ofEpochMilli(estimatedServerEpochMs).atZone(ZONE_GMT_MINUS_3)
        } else {
            // Fallback robusto usando o relógio do sistema ajustado diretamente para GMT-3
            ZonedDateTime.now(ZONE_GMT_MINUS_3)
        }
    }

    /**
     * Verifica se passou da meia-noite (00h00 GMT-3) desde uma data de referência.
     */
    fun hasDayChangedSince(referenceDateGmt3: String): Boolean {
        if (referenceDateGmt3.isBlank()) return true
        val today = getTodayGmt3()
        return referenceDateGmt3 != today
    }
}
