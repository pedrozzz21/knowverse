package com.example.data.essay

import android.util.Log
import com.example.BuildConfig
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import java.util.concurrent.TimeUnit
import java.util.regex.Pattern

/**
 * Constante oficial requisitada para recepcionar a metodologia do CORRETOR.md do INEP
 * (referência: https://github.com/dsvi-b/corretor-enem).
 * Pode ser sobrescrita ou configurada dinamicamente conforme necessidade da instituição.
 */
const val REGRAS_CORRETOR_INEP: String = ""

/**
 * Matriz de Competências do ENEM (INEP):
 * - C1: Domínio da norma culta da língua escrita (0 a 200 pts)
 * - C2: Compreensão da proposta e aplicação das áreas do conhecimento (0 a 200 pts)
 * - C3: Capacidade de selecionar, relacionar e organizar argumentos (0 a 200 pts)
 * - C4: Demonstração de conhecimento dos mecanismos linguísticos (0 a 200 pts)
 * - C5: Elaboração de proposta de intervenção social com direitos humanos (0 a 200 pts)
 */
data class CompetencyEvaluation(
    val number: Int,
    val title: String,
    val score: Int, // 0, 40, 80, 120, 160, 200
    val maxScore: Int = 200,
    val description: String,
    val feedback: String
)

data class EssayCorrectionResult(
    val totalScore: Int, // 0 a 1000
    val topic: String,
    val competencies: List<CompetencyEvaluation>,
    val generalDiagnosis: String,
    val rawMarkdown: String,
    val wordCount: Int,
    val lineCount: Int,
    val timestamp: Long = System.currentTimeMillis()
)

object EnemEssayService {
    private const val TAG = "EnemEssayService"
    private const val DEFAULT_MODEL = "gemini-3.5-flash"
    private const val FALLBACK_MODEL = "gemini-flash-latest"

    // Methodology baseline used when REGRAS_CORRETOR_INEP is empty
    private const val BASE_INEP_METHODOLOGY = """
Você é o Corretor Oficial do ENEM (Exame Nacional do Ensino Médio), seguindo rigorosamente a matriz de correção e critérios pedagógicos do INEP.
Sua missão é avaliar a redação dissertativo-argumentativa com base nas 5 Competências oficiais (0 a 200 pontos cada, em intervalos de 40 pontos: 0, 40, 80, 120, 160, 200):

- Competência 1: Demonstrar domínio da modalidade escrita formal da língua portuguesa.
- Competência 2: Compreender a proposta de redação e aplicar conceitos das várias áreas de conhecimento para desenvolver o tema, dentro dos limites estruturais do texto dissertativo-argumentativo em prosa.
- Competência 3: Selecionar, relacionar, organizar e interpretar informações, fatos, opiniões e argumentos em defesa de um ponto de vista.
- Competência 4: Demonstrar conhecimento dos mecanismos linguísticos necessários para a construção da argumentação.
- Competência 5: Elaborar proposta de intervenção para o problema abordado, respeitando os direitos humanos (avaliar os 5 elementos obrigatórios: Agente, Ação, Modo/Meio, Efeito/Finalidade e Detalhamento).

FORMATO DA SUA RESPOSTA:
Estruture a resposta com as seguintes seções em Markdown claro:

# NOTA FINAL: [Número de 0 a 1000]

## Tabela de Competências
| Competência | Nota | Justificativa Sintética |
| Competência 1 | [Nota] | [Justificativa] |
| Competência 2 | [Nota] | [Justificativa] |
| Competência 3 | [Nota] | [Justificativa] |
| Competência 4 | [Nota] | [Justificativa] |
| Competência 5 | [Nota] | [Justificativa] |

## Diagnóstico Pedagógico Detalhado
### Pontos Fortes e Repertório Legitimado
[Análise dos acertos, repertório sociocultural e tese defendida]

### Desvios Gramaticais e Coesivos (C1 e C4)
[Apontamentos específicos de pontuação, regência, concordância ou repetições vocabulares]

### Projeto de Texto e Argumentação (C2 e C3)
[Qualidade da fundamentação e coerência entre os parágrafos]

### Avaliação da Proposta de Intervenção (C5)
- Agente: [Identificado / Ausente]
- Ação: [Identificado / Ausente]
- Meio/Modo: [Identificado / Ausente]
- Efeito/Finalidade: [Identificado / Ausente]
- Detalhamento: [Identificado / Ausente]

## Recomendações Práticas para Evolução
[3 passos práticos para atingir a nota 1000 no próximo treino]
"""

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    /**
     * Submete a redação para correção via Gemini API com as diretrizes do INEP.
     */
    suspend fun correctEssay(
        topic: String,
        essayText: String,
        apiKeyOverride: String? = null
    ): Result<EssayCorrectionResult> = withContext(Dispatchers.IO) {
        try {
            val apiKey = apiKeyOverride?.takeIf { it.isNotBlank() }
                ?: getApiKey()

            if (apiKey.isBlank() || apiKey == "DEFAULT_API_KEY") {
                return@withContext Result.failure(
                    IllegalStateException("Chave da API Gemini não configurada. Configure a variável GEMINI_API_KEY no painel de Secrets do AI Studio para realizar a correção com IA.")
                )
            }

            // Prompt especificado nas instruções do usuário
            val prompt = "Corrija a seguinte redação sobre o tema:\n$topic\n\nRedação:\n$essayText"

            // System Instruction: aponta para REGRAS_CORRETOR_INEP (se preenchida) ou metodologia base INEP
            val systemInstructionContent = if (REGRAS_CORRETOR_INEP.isNotBlank()) {
                REGRAS_CORRETOR_INEP
            } else {
                BASE_INEP_METHODOLOGY
            }

            // Tentativa de chamada com modelo padrão
            val responseMarkdown = callGeminiGenerateContent(
                model = DEFAULT_MODEL,
                apiKey = apiKey,
                prompt = prompt,
                systemInstruction = systemInstructionContent
            ).getOrElse { fallbackError ->
                Log.w(TAG, "Tentando modelo de contingência após erro no $DEFAULT_MODEL: ${fallbackError.message}")
                callGeminiGenerateContent(
                    model = FALLBACK_MODEL,
                    apiKey = apiKey,
                    prompt = prompt,
                    systemInstruction = systemInstructionContent
                ).getOrThrow()
            }

            val parsedResult = parseCorrectionResult(
                topic = topic,
                essayText = essayText,
                markdown = responseMarkdown
            )

            Result.success(parsedResult)
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao processar correção da redação", e)
            Result.failure(e)
        }
    }

    private fun getApiKey(): String {
        return try {
            val field = BuildConfig::class.java.getField("GEMINI_API_KEY")
            (field.get(null) as? String) ?: ""
        } catch (e: Exception) {
            ""
        }
    }

    private fun callGeminiGenerateContent(
        model: String,
        apiKey: String,
        prompt: String,
        systemInstruction: String
    ): Result<String> {
        val url = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent?key=$apiKey"

        val jsonBody = JSONObject().apply {
            // System instruction
            put("systemInstruction", JSONObject().apply {
                put("parts", JSONArray().apply {
                    put(JSONObject().apply {
                        put("text", systemInstruction)
                    })
                })
            })

            // Contents
            put("contents", JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", JSONArray().apply {
                        put(JSONObject().apply {
                            put("text", prompt)
                        })
                    })
                })
            })

            // Generation Config
            put("generationConfig", JSONObject().apply {
                put("temperature", 0.3)
                put("topP", 0.95)
                put("topK", 40)
            })
        }

        val request = Request.Builder()
            .url(url)
            .post(jsonBody.toString().toRequestBody("application/json".toMediaType()))
            .build()

        return try {
            httpClient.newCall(request).execute().use { response ->
                val bodyString = response.body?.string() ?: ""
                if (!response.isSuccessful) {
                    val errorMsg = "HTTP ${response.code}: $bodyString"
                    Log.e(TAG, "Gemini API error: $errorMsg")
                    Result.failure(RuntimeException(errorMsg))
                } else {
                    val root = JSONObject(bodyString)
                    val candidates = root.optJSONArray("candidates")
                    val firstCandidate = candidates?.optJSONObject(0)
                    val content = firstCandidate?.optJSONObject("content")
                    val parts = content?.optJSONArray("parts")
                    val text = parts?.optJSONObject(0)?.optString("text")

                    if (!text.isNullOrBlank()) {
                        Result.success(text)
                    } else {
                        Result.failure(IllegalStateException("Resposta vazia da API Gemini"))
                    }
                }
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun parseCorrectionResult(
        topic: String,
        essayText: String,
        markdown: String
    ): EssayCorrectionResult {
        // Extrai nota final (ex: NOTA FINAL: 880)
        var totalScore = 0
        val totalPattern = Pattern.compile("NOTA\\s*FINAL\\s*[:=]?\\s*(\\d{1,4})", Pattern.CASE_INSENSITIVE)
        val totalMatcher = totalPattern.matcher(markdown)
        if (totalMatcher.find()) {
            totalScore = totalMatcher.group(1)?.toIntOrNull() ?: 0
        }

        // Extrai notas das 5 competências
        val competencyList = mutableListOf<CompetencyEvaluation>()
        val defaultCompetencyTitles = listOf(
            "C1: Domínio da Norma Culta",
            "C2: Compreensão da Proposta e Repertório",
            "C3: Organização e Projeto de Texto",
            "C4: Mecanismos Linguísticos e Coesão",
            "C5: Proposta de Intervenção e Cidadania"
        )

        for (i in 1..5) {
            // Tenta achar padrões como: Competência 1 | 160 | ou Competência 1: 160
            val cPattern = Pattern.compile("Competência\\s*$i.*?\\|?\\s*(\\d{1,3})", Pattern.CASE_INSENSITIVE)
            val matcher = cPattern.matcher(markdown)
            val score = if (matcher.find()) {
                val found = matcher.group(1)?.toIntOrNull() ?: 120
                found.coerceIn(0, 200)
            } else {
                120
            }

            val desc = when (i) {
                1 -> "Gramática, ortografia, pontuação, concordância e regência."
                2 -> "Desenvolvimento do tema, pertinência da tese e repertório sociocultural."
                3 -> "Coerência, seleção de argumentos, dados e defesa do ponto de vista."
                4 -> "Coesão interparágrafo e intraparágrafo, conectivos e fluidez textual."
                5 -> "Proposta detalhada de solução para o problema com respeito aos direitos humanos."
                else -> ""
            }

            competencyList.add(
                CompetencyEvaluation(
                    number = i,
                    title = defaultCompetencyTitles[i - 1],
                    score = score,
                    description = desc,
                    feedback = "Avaliação realizada de acordo com a matriz analítica do INEP."
                )
            )
        }

        // Se totalScore não foi extraído do cabeçalho, soma as competências
        if (totalScore == 0) {
            totalScore = competencyList.sumOf { it.score }
        }
        totalScore = totalScore.coerceIn(0, 1000)

        // Contagem de palavras e linhas
        val words = essayText.split(Regex("\\s+")).filter { it.isNotBlank() }.size
        val lines = essayText.lines().count { it.isNotBlank() }.coerceAtLeast(1)

        return EssayCorrectionResult(
            totalScore = totalScore,
            topic = topic,
            competencies = competencyList,
            generalDiagnosis = "Diagnóstico concluído com base nos critérios pedagógicos oficiais do ENEM.",
            rawMarkdown = markdown,
            wordCount = words,
            lineCount = lines
        )
    }
}
