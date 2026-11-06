package com.example.data.essay

import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log
import com.example.BuildConfig
import com.example.data.security.ApiKeyManager
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
    val feedback: String,
    val identifiedEvidence: String = "",
    val limitations: String = "",
    val inepCriterionImpact: String = "",
    val improvementGuidance: String = ""
)

data class EssayCorrectionResult(
    val totalScore: Int, // 0 a 1000
    val topic: String,
    val competencies: List<CompetencyEvaluation>,
    val generalDiagnosis: String,
    val rawMarkdown: String,
    val wordCount: Int,
    val lineCount: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val isPreliminaryDiagnosis: Boolean = false,
    val evaluationType: String = "AI_SEMANTIC", // "AI_SEMANTIC" ou "OFFLINE_HEURISTIC"
    val structuralObservations: List<String> = emptyList(),
    val isInsufficientLength: Boolean = false
)

object EnemEssayService {
    private const val TAG = "EnemEssayService"
    private const val DEFAULT_MODEL = "gemini-2.0-flash"
    private const val FALLBACK_MODEL = "gemini-1.5-pro"
    private const val TERTIARY_MODEL = "gemini-1.5-flash"

    /**
     * Verifica se o dispositivo possui conexão ativa com a internet.
     * Retorna true em ambientes sem Context (como testes unitários JVM puros).
     */
    fun isNetworkAvailable(context: android.content.Context?): Boolean {
        if (context == null) return true
        return try {
            val cm = context.getSystemService(android.content.Context.CONNECTIVITY_SERVICE) as? android.net.ConnectivityManager
            if (cm != null) {
                val activeNetwork = cm.activeNetwork ?: return false
                val capabilities = cm.getNetworkCapabilities(activeNetwork) ?: return false
                capabilities.hasCapability(android.net.NetworkCapabilities.NET_CAPABILITY_INTERNET)
            } else {
                true
            }
        } catch (_: Throwable) {
            true
        }
    }
    private fun logE(tag: String, msg: String, tr: Throwable? = null) {
        try {
            android.util.Log.e(tag, msg, tr)
        } catch (_: Throwable) {}
    }

    private fun logW(tag: String, msg: String) {
        try {
            android.util.Log.w(tag, msg)
        } catch (_: Throwable) {}
    }


    // Methodology baseline used when REGRAS_CORRETOR_INEP is empty
    // Alinhado ao manual oficial do corretor do INEP e referências recomendadas (RedacaoNota1000, chatgpt-redacao-enem)
    private const val BASE_INEP_METHODOLOGY = """
Você é o Agente Especialista e Corretor Oficial do ENEM (Exame Nacional do Ensino Médio), atuando com o rigor técnico, pedagógico e metodológico da Banca Examinadora do INEP.
Sua missão é avaliar a redação dissertativo-argumentativa do estudante com base nas 5 Competências oficiais (0 a 200 pontos cada, em intervalos estritos de 40 pontos: 0, 40, 80, 120, 160, 200), totalizando de 0 a 1000 pontos.

CRITÉRIOS DE ANULAÇÃO DO EDITAL DO INEP (NOTA ZERO GERAL):
- Texto com até 7 linhas manuscritas (ou menos de 40 palavras no ambiente digital): atribua NOTA ZERO em todas as competências por extensão insuficiente.
- Fuga total ao tema ou não atendimento à estrutura dissertativo-argumentativa em prosa: NOTA ZERO.
- Trechos deliberadamente desconectados (deboche, receitas, orações desconexas): NOTA ZERO.

DIRETRIZES TÉCNICAS POR COMPETÊNCIA (CRITÉRIOS OFICIAIS DO INEP):

1. COMPETÊNCIA 1 (Domínio da Norma Padrão da Língua Escrita):
- Avalie a convenção da escrita: ortografia, acentuação, crase, pontuação, concordância verbal/nominal, regência e estrutura sintática (falhas de truncamento ou períodos justapostos).
- Identifique e liste nominalmente os desvios encontrados.
- Nível 200: Excelente domínio, no máximo 2 desvios gramaticais leves e nenhuma falha de estrutura.
- Nível 160: Bom domínio, poucos desvios gramaticais e no máximo 1 falha sintática.
- Nível 120: Domínio mediano, alguns desvios frequentes.
- Nível 80: Domínio insuficiente, muitos desvios.
- Níveis 40 e 0: Domínio precário ou nulo da modalidade formal.

2. COMPETÊNCIA 2 (Compreensão da Proposta e Repertório Sociocultural):
- Avalie se houve abordagem completa do tema, tangenciamento (máx. 40 pontos) ou fuga.
- Avalie a tipologia dissertativo-argumentativa (mínimo de introdução, desenvolvimento e conclusão).
- Classificação obrigatória do Repertório Sociocultural:
  * Não legitimado (senso comum): máx. 80 pontos.
  * Legitimado (filosofia, sociologia, história, literatura, legislação, dados com fonte), porém NÃO PRODUTIVO (mera citação sem vinculação argumentativa direta): máx. 120 pontos.
  * Legitimado, pertinente E PRODUTIVO (articulado estrategicamente com a tese e desdobramento do argumento): 160 a 200 pontos.

3. COMPETÊNCIA 3 (Projeto de Texto Estratégico e Argumentação):
- Avalie o planejamento prévio do texto (autoria, coerência e direção argumentativa).
- Verifique se os argumentos são consistentes, desdobrados e sem lacunas explicativas ou contradições.
- Nível 200: Projeto de texto estratégico consistente, autoria plena, desenvolvimento aprofundado dos dois argumentos.
- Nível 160: Projeto de texto com poucas falhas, argumentação consistente.
- Nível 120: Projeto de texto com falhas evidentes, argumentos superficiais ou previsíveis.
- Nível 80: Projeto de texto frágil ou desarticulado.

4. COMPETÊNCIA 4 (Mecanismos Linguísticos e Coesão Textual):
- Avalie a coesão interparágrafos (obrigatoriedade de conectivos interparágrafos no início de ao menos 2 parágrafos, como no D1, D2 ou Conclusão).
- Avalie a coesão intraparágrafos (presença diversificada de operadores argumentativos entre os períodos de cada parágrafo).
- Penalize repetições excessivas de vocábulos e inadequações no uso de conectores.
- Nível 200: Repertório diversificado de recursos coesivos, sem repetições e sem inadequações.
- Nível 160: Bom uso de conectivos com raras repetições.
- Nível 120: Uso mediano de conectivos, algumas repetições ou inadequações.
- Nível 80: Pouca diversidade ou repetições constantes.

5. COMPETÊNCIA 5 (Proposta de Intervenção Social e Direitos Humanos):
- A proposta deve detalhar uma intervenção para o problema abordado.
- Exige os 5 ELEMENTOS OBRIGATÓRIOS DO INEP (40 pontos por elemento válido):
  1. Agente (Quem executa?): Órgão, poder público, ONG, mídia, etc.
  2. Ação (O que fará?): Verbo de ação concreto.
  3. Meio/Modo (Como fará?): "por intermédio de", "mediante", "através de".
  4. Efeito/Finalidade (Para que fará?): "com a finalidade de", "para que", "a fim de".
  5. Detalhamento (Especificação de um dos elementos anteriores): Justificativa, exemplo ou desdobramento de agente, ação, meio ou efeito.
- Nota = quantidade de elementos válidos * 40 (0, 40, 80, 120, 160, 200).
- Desrespeito explícito aos Direitos Humanos resulta em NOTA ZERO imediata na Competência 5.

FORMATO OBRIGATÓRIO DA RESPOSTA:
Retorne sua análise estritamente neste formato Markdown:

# NOTA FINAL: [Soma exata das 5 competências, entre 0 e 1000]

## Tabela de Competências
| Competência | Nota | Justificativa Sintética |
| Competência 1 | [0, 40, 80, 120, 160 ou 200] | [Justificativa específica e desvios] |
| Competência 2 | [0, 40, 80, 120, 160 ou 200] | [Tema e classificação do repertório] |
| Competência 3 | [0, 40, 80, 120, 160 ou 200] | [Projeto de texto e coerência da argumentação] |
| Competência 4 | [0, 40, 80, 120, 160 ou 200] | [Coesão inter e intraparágrafos] |
| Competência 5 | [0, 40, 80, 120, 160 ou 200] | [Checklist dos 5 elementos da intervenção] |

## Diagnóstico Detalhado do Agente Avaliador

### Competência 1: Desvios Gramaticais e Estrutura Sintática
[Aponte nominalmente os desvios identificados de crase, regência, concordância, ortografia ou truncamento]

### Competência 2: Análise do Tema e Repertório Sociocultural
- Abordagem do Tema: [Completa / Tangenciamento / Fuga]
- Tipo Textual: [Dissertativo-argumentativo em prosa atendido / Incompleto]
- Repertório: [Legitimado, Pertinente e Produtivo / Não Produtivo / Senso Comum]
[Comentário pedagógico detalhado]

### Competência 3: Projeto de Texto e Progressão Argumentativa
[Análise da coerência, defesa da tese e desdobramento dos argumentos]

### Competência 4: Coesão Inter e Intraparágrafos
- Conectivos Interparágrafos: [Presentes no início de pelo menos 2 parágrafos / Ausentes]
- Diversidade de Conectivos Intraparágrafos: [Ampla / Mediana / Rara]
- Repetições Vocabulares: [Inexistentes / Frequentes]

### Competência 5: Checklist da Proposta de Intervenção
- Agente: [Identificado e válido / Ausente ou vago]
- Ação: [Identificada e exequível / Ausente]
- Meio/Modo: [Identificado / Ausente]
- Efeito/Finalidade: [Identificado / Ausente]
- Detalhamento: [Identificado / Ausente]
- Respeito aos Direitos Humanos: [Conforme / Violação]

## Recomendações Práticas do Agente para a Nota 1000
[3 orientações pedagógicas práticas e acionáveis para o estudante evoluir na próxima redação]
"""

    private val httpClient = OkHttpClient.Builder()
        .connectTimeout(60, TimeUnit.SECONDS)
        .readTimeout(60, TimeUnit.SECONDS)
        .writeTimeout(60, TimeUnit.SECONDS)
        .build()

    /**
     * Submete a redação para correção via Gemini API com as diretrizes do INEP.
     * Quando a API estiver indisponível ou offline e allowOfflineFallback for verdadeiro,
     * executa o diagnóstico heurístico estrutural preliminar com aviso explícito.
     */
    /**
     * Submete a redação para correção via Agente Oficial ENEM conectado à nuvem.
     * Opera exclusivamente com conexão ativa à internet, pois a avaliação das 5 competências
     * do INEP exige análise semântica e argumentativa fidedigna.
     */
    suspend fun correctEssay(
        topic: String,
        essayText: String,
        apiKeyOverride: String? = null,
        allowOfflineFallback: Boolean = false,
        context: android.content.Context? = null
    ): Result<EssayCorrectionResult> = withContext(Dispatchers.IO) {
        // Validação obrigatória de conectividade com a internet
        if (context != null && !isNetworkAvailable(context)) {
            return@withContext Result.failure(
                java.io.IOException(
                    "Sem conexão com a internet. A correção oficial da redação pelo Agente ENEM exige conexão ativa (Wi-Fi ou dados móveis) para a avaliação semântica e argumentativa das 5 competências. Conecte-se e tente novamente."
                )
            )
        }

        val apiKey = if (apiKeyOverride != null) apiKeyOverride else getApiKey()

        if (apiKey.isBlank() || apiKey == "DEFAULT_API_KEY") {
            if (allowOfflineFallback) {
                logW(TAG, "Chave de API não configurada. Ativando diagnóstico heurístico preliminar offline de contingência.")
                return@withContext Result.success(
                    evaluateHeuristicPedagogical(topic, essayText, isOfflineFallback = true)
                )
            }
            return@withContext Result.failure(
                IllegalStateException("Chave da API Gemini não configurada. Configure a variável GEMINI_API_KEY no painel de Secrets ou configurações para que o Agente Oficial avalie sua redação online.")
            )
        }

        try {
            val prompt = "Corrija a seguinte redação dissertativo-argumentativa sobre o tema:\n$topic\n\nTexto do Estudante:\n$essayText"
            val systemInstructionContent = if (REGRAS_CORRETOR_INEP.isNotBlank()) {
                REGRAS_CORRETOR_INEP
            } else {
                BASE_INEP_METHODOLOGY
            }

            val responseMarkdown = callGeminiGenerateContent(
                model = DEFAULT_MODEL,
                apiKey = apiKey,
                prompt = prompt,
                systemInstruction = systemInstructionContent
            ).getOrElse { fallbackError1 ->
                logW(TAG, "Tentando modelo de contingência após erro no $DEFAULT_MODEL: ${fallbackError1.message}")
                callGeminiGenerateContent(
                    model = FALLBACK_MODEL,
                    apiKey = apiKey,
                    prompt = prompt,
                    systemInstruction = systemInstructionContent
                ).getOrElse { fallbackError2 ->
                    logW(TAG, "Tentando modelo terciário após erro no $FALLBACK_MODEL: ${fallbackError2.message}")
                    callGeminiGenerateContent(
                        model = TERTIARY_MODEL,
                        apiKey = apiKey,
                        prompt = prompt,
                        systemInstruction = systemInstructionContent
                    ).getOrThrow()
                }
            }

            val parsedResult = parseCorrectionResult(
                topic = topic,
                essayText = essayText,
                markdown = responseMarkdown
            )

            Result.success(parsedResult)
        } catch (e: Exception) {
            logE(TAG, "Erro na correção via IA/Agente", e)
            if (allowOfflineFallback) {
                logW(TAG, "Falha na comunicação com a IA. Retornando diagnóstico heurístico preliminar offline.")
                Result.success(evaluateHeuristicPedagogical(topic, essayText, isOfflineFallback = true))
            } else {
                val rawMsg = e.message ?: ""
                val errorMsg = when {
                    e is java.net.UnknownHostException ->
                        "Sem conexão com a internet. Não foi possível conectar ao servidor do Agente Corretor do ENEM. Verifique sua conexão e tente novamente."
                    e is java.net.SocketTimeoutException ->
                        "Tempo limite de conexão esgotado. A avaliação em nuvem pelo Agente ENEM demorou mais que o previsto. Verifique a estabilidade da sua internet e tente novamente."
                    e is java.io.IOException ->
                        "Erro de rede ao comunicar com o Agente Corretor: ${e.localizedMessage ?: "Verifique sua conexão com a internet."}"
                    rawMsg.contains("HTTP 400") || rawMsg.contains("HTTP 403") || rawMsg.contains("HTTP 401") || rawMsg.contains("API_KEY_INVALID") ->
                        "Chave de acesso inválida ou sem permissão na API do Agente ENEM. Verifique suas credenciais nas configurações ou secrets."
                    rawMsg.contains("HTTP 429") ->
                        "Limite de requisições excedido no servidor do Agente ENEM. Aguarde alguns instantes e tente novamente."
                    rawMsg.isNotBlank() -> rawMsg
                    else -> "Ocorreu um erro inesperado ao conectar ao Agente Corretor do ENEM."
                }
                Result.failure(Exception(errorMsg, e))
            }
        }
    }

    private fun getApiKey(): String {
        val activeKey = ApiKeyManager.getActiveApiKey(null)
        if (activeKey.isNotBlank()) return activeKey

        return try {
            val field = BuildConfig::class.java.getField("GEMINI_API_KEY")
            val key = (field.get(null) as? String) ?: ""
            if (key.isNotBlank() && key != "DEFAULT_API_KEY") key.trim() else ""
        } catch (e: Exception) {
            ""
        }
    }

    fun callGeminiGenerateContent(
        model: String,
        apiKey: String,
        prompt: String,
        systemInstruction: String
    ): Result<String> {
        val url = "https://generativelanguage.googleapis.com/v1beta/models/$model:generateContent?key=$apiKey"

        val jsonBody = JSONObject().apply {
            put("systemInstruction", JSONObject().apply {
                put("parts", JSONArray().apply {
                    put(JSONObject().apply {
                        put("text", systemInstruction)
                    })
                })
            })

            put("contents", JSONArray().apply {
                put(JSONObject().apply {
                    put("parts", JSONArray().apply {
                        put(JSONObject().apply {
                            put("text", prompt)
                        })
                    })
                })
            })

            put("generationConfig", JSONObject().apply {
                put("temperature", 0.2)
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
                    logE(TAG, "Gemini API error: $errorMsg")
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

    /**
     * Analisa o markdown retornado pela IA e extrai notas e feedbacks.
     * Suporta respostas incompletas ou com formatação truncada, garantindo:
     * - totalScore == soma exata de C1 + C2 + C3 + C4 + C5.
     * - Todas as notas são múltiplos válidos de 40 no intervalo [0, 200].
     * - totalScore no intervalo [0, 1000].
     */
    fun parseCorrectionResult(
        topic: String,
        essayText: String,
        markdown: String
    ): EssayCorrectionResult {
        if (markdown.isBlank()) {
            throw IllegalArgumentException("A resposta da API está vazia ou não contém texto.")
        }

        val defaultCompetencyTitles = listOf(
            "C1: Domínio da Norma Culta",
            "C2: Compreensão da Proposta e Repertório",
            "C3: Organização e Projeto de Texto",
            "C4: Mecanismos Linguísticos e Coesão",
            "C5: Proposta de Intervenção e Cidadania"
        )

        val defaultDescriptions = listOf(
            "Gramática, ortografia, pontuação, concordância e regência formal.",
            "Desenvolvimento do tema, pertinência da tese e repertório sociocultural produtivo.",
            "Coerência, seleção de argumentos, dados e defesa progressiva de ponto de vista.",
            "Coesão interparágrafo e intraparágrafo, conectivos e fluidez textual.",
            "Proposta detalhada de solução para o problema com respeito aos direitos humanos."
        )

        val competencyList = mutableListOf<CompetencyEvaluation>()

        for (i in 1..5) {
            var extractedScore: Int? = null
            var extractedJustification: String? = null

            // Padrão 1: Linha de tabela Markdown | Competência 1 | 160 | Justificativa |
            val tablePattern = Pattern.compile(
                "\\|?\\s*(?:Competência|C)\\s*$i\\s*\\|\\s*(\\d{1,3})\\s*\\|\\s*([^\\|\\r\\n]+)",
                Pattern.CASE_INSENSITIVE
            )
            val tableMatcher = tablePattern.matcher(markdown)
            if (tableMatcher.find()) {
                extractedScore = tableMatcher.group(1)?.toIntOrNull()
                extractedJustification = tableMatcher.group(2)?.trim()
            }

            // Padrão 2 (Contingência): Outros formatos de pontuação caso a tabela não esteja perfeita
            if (extractedScore == null) {
                val fallbackPatterns = listOf(
                    Pattern.compile("Competência\\s*$i.*?\\|?\\s*(\\d{1,3})", Pattern.CASE_INSENSITIVE),
                    Pattern.compile("C$i\\s*[:\\|-]?\\s*(\\d{1,3})", Pattern.CASE_INSENSITIVE)
                )
                for (p in fallbackPatterns) {
                    val matcher = p.matcher(markdown)
                    if (matcher.find()) {
                        val rawNum = matcher.group(1)?.toIntOrNull()
                        if (rawNum != null) {
                            extractedScore = rawNum
                            break
                        }
                    }
                }
            }

            // Normaliza para múltiplos oficiais de 40 (0, 40, 80, 120, 160, 200)
            val score = if (extractedScore != null) {
                val clamped = extractedScore.coerceIn(0, 200)
                ((clamped + 20) / 40) * 40
            } else {
                120 // Fallback pedagógico neutro se a competência faltar na resposta
            }

            val feedbackText = extractedJustification?.takeIf { it.isNotBlank() }
                ?: if (extractedScore != null) {
                    "Avaliação semântica realizada conforme a matriz analítica oficial do INEP."
                } else {
                    "Pontuação estimada pedagogicamente por contingência (resposta parcial da IA)."
                }

            competencyList.add(
                CompetencyEvaluation(
                    number = i,
                    title = defaultCompetencyTitles[i - 1],
                    score = score.coerceIn(0, 200),
                    description = defaultDescriptions[i - 1],
                    feedback = feedbackText
                )
            )
        }

        // A pontuação final DEVE SEMPRE corresponder rigorosamente à soma das 5 competências
        val totalScore = competencyList.sumOf { it.score }.coerceIn(0, 1000)

        val words = essayText.split(Regex("\\s+")).filter { it.isNotBlank() }.size
        val explicitLines = essayText.lines().count { it.isNotBlank() }
        val estimatedLines = essayText.lines().sumOf { line ->
            if (line.isBlank()) 0 else ((line.trim().length - 1) / 75) + 1
        }
        val lines = maxOf(explicitLines, estimatedLines).coerceAtLeast(1)

        return EssayCorrectionResult(
            totalScore = totalScore,
            topic = topic,
            competencies = competencyList,
            generalDiagnosis = "Correção analítica realizada com base nos critérios pedagógicos do INEP por Inteligência Artificial.",
            rawMarkdown = markdown,
            wordCount = words,
            lineCount = lines,
            isPreliminaryDiagnosis = false,
            evaluationType = "AI_SEMANTIC"
        )
    }

    /**
     * Diagnóstico Heurístico Pedagógico Offline.
     * Analisa a estrutura observável do texto sem alegar equivalência à correção oficial do ENEM.
     * Evita conclusões precipitadas sobre semântica, repertório e argumentação profunda.
     */
    fun evaluateHeuristicPedagogical(
        topic: String,
        essayText: String,
        isOfflineFallback: Boolean = false
    ): EssayCorrectionResult {
        val trimmed = essayText.trim()
        val words = trimmed.split(Regex("\\s+")).filter { it.isNotBlank() }
        val wordCount = words.size
        val explicitLines = trimmed.lines().count { it.isNotBlank() }
        // Estimativa para folha de 30 linhas manuscritas padrão do ENEM (aprox. 75 caracteres por linha física)
        val estimatedLines = trimmed.lines().sumOf { line ->
            val l = line.trim()
            if (l.isEmpty()) 0 else ((l.length - 1) / 75) + 1
        }
        val lineCount = maxOf(explicitLines, estimatedLines)

        val paragraphs = trimmed.split(Regex("\\n\\s*\\n+")).map { it.trim() }.filter { it.isNotBlank() }
        val paragraphCount = if (paragraphs.isNotEmpty()) paragraphs.size else if (trimmed.isNotBlank()) 1 else 0

        val observations = mutableListOf<String>()

        // 1. REGRA OFICIAL DE EXTENSÃO DO INEP (Critério de Anulação)
        // Redações com até 7 linhas manuscritas na folha oficial recebem nota ZERO.
        // Em formato digital onde não há folha física, utiliza-se a contagem de linhas e palavras como estimativa.
        val isInsufficient = wordCount < 40 || lineCount <= 7

        if (isInsufficient) {
            observations.add("Extensão insuficiente (estimativa <= 7 linhas ou < 40 palavras): Critério oficial do INEP para nota ZERO.")

            val zeroCompetencies = (1..5).map { i ->
                CompetencyEvaluation(
                    number = i,
                    title = when (i) {
                        1 -> "C1: Domínio da Norma Culta"
                        2 -> "C2: Compreensão da Proposta e Repertório"
                        3 -> "C3: Organização e Projeto de Texto"
                        4 -> "C4: Mecanismos Linguísticos e Coesão"
                        else -> "C5: Proposta de Intervenção e Cidadania"
                    },
                    score = 0,
                    description = "Avaliação prejudicada por extensão insuficiente do texto.",
                    feedback = "Texto com extensão insuficiente para análise pedagógica. De acordo com o edital do ENEM (INEP), redações com até 7 linhas manuscritas recebem nota ZERO.",
                    identifiedEvidence = "Contagem identificada: $wordCount palavras e estimativa de $lineCount linhas.",
                    limitations = "A análise estrutural não pode avaliar competências em textos insuficientes.",
                    inepCriterionImpact = "Critério de anulação expressa no edital do exame: nota 0 direta.",
                    improvementGuidance = "Desenvolva uma dissertação completa com introdução, dois desenvolvimentos e conclusão (entre 20 e 30 linhas na folha oficial)."
                )
            }

            val markdownReport = buildString {
                append("# DIAGNÓSTICO ESTRUTURAL PRELIMINAR (OFFLINE)\n\n")
                append("> [!WARNING]\n")
                append("> **Texto Insuficiente (Critério Oficial do INEP)**: Redações com até 7 linhas manuscritas na folha de resposta oficial são sumariamente anuladas com **NOTA ZERO**.\n\n")
                append("## Métricas Observadas\n")
                append("- **Palavras**: $wordCount\n")
                append("- **Linhas Estimadas**: $lineCount (mínimo oficial para avaliação: mais de 7 linhas)\n")
                append("- **Parágrafos**: $paragraphCount\n\n")
                append("## Resultado\n")
                append("**Nota Final Estimada: 0 / 1000** (Anulação por extensão insuficiente)\n\n")
                append("### Orientação Pedagógica\n")
                append("Para treinar efetivamente a matriz do ENEM, escreva uma redação com ao menos 3 a 4 parágrafos bem desenvolvidos (geralmente acima de 150 a 250 palavras em meio digital).")
            }

            return EssayCorrectionResult(
                totalScore = 0,
                topic = topic,
                competencies = zeroCompetencies,
                generalDiagnosis = "Texto com extensão insuficiente. Conforme edital do INEP, redações com até 7 linhas manuscritas recebem nota ZERO.",
                rawMarkdown = markdownReport,
                wordCount = wordCount,
                lineCount = lineCount,
                isPreliminaryDiagnosis = true,
                evaluationType = "OFFLINE_HEURISTIC",
                structuralObservations = observations,
                isInsufficientLength = true
            )
        }

        // 2. AVALIAÇÃO DE PARÁGRAFOS (Bloco único / Monoparágrafo)
        val isSingleParagraph = paragraphCount <= 1
        if (isSingleParagraph) {
            observations.add("Texto estruturado em bloco único (monoparágrafo): prejudica a macroestrutura dissertativa exigida no ENEM.")
        }

        // ==========================================
        // C1: NORMA CULTA DA LÍNGUA ESCRITA
        // ==========================================
        val informalRegex = Regex("\\b(vc|vcs|pq|tb|tbm|pra|pro|pras|pros|né|ne|ta|tá|tipo assim|blz|mano|cara|dai|daí)\\b", RegexOption.IGNORE_CASE)
        val informalMatches = informalRegex.findAll(trimmed).map { it.value.lowercase() }.toList()

        val unclosedParagraphs = paragraphs.count { p ->
            !p.endsWith(".") && !p.endsWith("!") && !p.endsWith("?")
        }
        val longSentences = paragraphs.count { p ->
            val wordsInP = p.split(Regex("\\s+"))
            wordsInP.size > 50 && !p.contains(".")
        }

        val c1Score = when {
            informalMatches.size >= 4 || longSentences >= 2 -> 80
            informalMatches.size in 1..3 || unclosedParagraphs > 0 -> 120
            else -> 160 // Heurístico não atribui 200 cegamente: concordância/regência exigem semântica
        }

        val c1Evidence = if (informalMatches.isNotEmpty()) {
            "Foram detectadas marcas de coloquialismo/abreviações informais: [${informalMatches.distinct().take(4).joinToString(", ")}]."
        } else {
            "Ausência de marcas explícitas de informalidade ou abreviações de internet detectáveis."
        }

        val c1Limitations = "A verificação offline baseia-se em vocabulário e pontuação evidente. Não analisa desvios sutis de concordância nominal/verbal, regência, crase e paralelismo sintático."
        val c1Impact = "No INEP, desvios pontuais não acarretam nota zero, mas ultrapassar 2 desvios gramaticais impede a nota máxima (200 pontos)."
        val c1Improvement = "Substitua contrações e termos orais pela norma padrão formal, pontue orações subordinadas e revise a acentuação gráfica."

        val c1Evaluation = CompetencyEvaluation(
            number = 1,
            title = "C1: Domínio da Norma Culta",
            score = c1Score,
            description = "Avaliação da modalidade escrita formal e estrutura sintática.",
            feedback = "$c1Evidence $c1Limitations",
            identifiedEvidence = c1Evidence,
            limitations = c1Limitations,
            inepCriterionImpact = c1Impact,
            improvementGuidance = c1Improvement
        )

        // ==========================================
        // C2: COMPREENSÃO DA PROPOSTA E REPERTÓRIO
        // ==========================================
        val topicKeywords = topic.lowercase()
            .split(Regex("[^\\p{L}0-9]+"))
            .filter { it.length > 3 && it !in listOf("para", "como", "sobre", "pela", "pelo", "brasil", "desafios", "sociedade") }

        val topicMatchesFound = topicKeywords.filter { trimmed.lowercase().contains(it) }

        val repertoireKeywords = listOf(
            "constituição", "carta magna", "artigo", "filósofo", "sociólogo", "bauman",
            "foucault", "hannah arendt", "habermas", "hobbes", "locke", "rousseau", "kant",
            "durkheim", "weber", "marx", "onu", "oms", "unesco", "ibge", "século", "história",
            "iluminismo", "revolução industrial", "cidadania", "direitos humanos"
        )
        val repertoireMatches = repertoireKeywords.filter { trimmed.lowercase().contains(it) }

        val c2Score = when {
            isSingleParagraph -> 80 // Monoparágrafo compromete a estrutura dissertativa em prosa
            topicMatchesFound.isEmpty() -> 40 // Alto risco de tangenciamento ou fuga
            repertoireMatches.isEmpty() -> 120 // Desenvolve o tema sem repertório legitimado identificável
            else -> 160 // Menções identificadas, mas produtividade depende de validação semântica
        }

        val c2Evidence = buildString {
            if (topicMatchesFound.isNotEmpty()) {
                append("Presença de vocábulos relacionados ao tema: [${topicMatchesFound.take(3).joinToString(", ")}]. ")
            } else {
                append("Poucos ou nenhum vocábulo central da proposta temático foi identificado no texto. ")
            }
            if (repertoireMatches.isNotEmpty()) {
                append("Menções a termos comumente associados a repertório: [${repertoireMatches.take(3).joinToString(", ")}].")
            } else {
                append("Não foram identificadas menções explícitas a autoridades, filósofos, leis ou dados históricos.")
            }
        }

        val c2Limitations = "A simples citação de termos (como Bauman, ONU ou Constituição) NÃO comprova que o repertório foi legítimo e produtivo. A pertinência temática e a articulação com a tese exigem leitura semântica."
        val c2Impact = "No INEP, repertório apenas citado sem relação produtiva com a argumentação ('repertório descolado') fica retido nas faixas 120 ou 160 pontos. Fuga total ao tema zera a redação."
        val c2Improvement = "Sempre relacione o repertório diretamente ao problema discutido, explicando a conexão entre a teoria citada e a realidade brasileira contemporânea."

        val c2Evaluation = CompetencyEvaluation(
            number = 2,
            title = "C2: Compreensão da Proposta e Repertório",
            score = c2Score,
            description = "Compreensão temática, estrutura dissertativa e repertório sociocultural.",
            feedback = "$c2Evidence $c2Limitations",
            identifiedEvidence = c2Evidence,
            limitations = c2Limitations,
            inepCriterionImpact = c2Impact,
            improvementGuidance = c2Improvement
        )

        // ==========================================
        // C3: PROJETO DE TEXTO E ARGUMENTAÇÃO
        // ==========================================
        val argumentMarkers = listOf(
            "em primeiro lugar", "em primeira análise", "a princípio", "além disso",
            "ademais", "outrossim", "por outro lado", "nesse contexto", "sob essa ótica",
            "desse modo", "por conseguinte", "portanto", "dessa forma"
        )
        val markersFound = argumentMarkers.filter { trimmed.lowercase().contains(it) }

        val c3Score = when {
            isSingleParagraph -> 80 // Sem segmentação, sem projeto visível
            paragraphCount in 3..5 && markersFound.size >= 2 -> 160
            paragraphCount in 3..5 -> 120
            else -> 80
        }

        val c3Evidence = "Texto estruturado em $paragraphCount parágrafos com ${markersFound.size} marcadores de encadeamento argumentativo identificados."
        val c3Limitations = "A presença de marcadores argumentativos não comprova consistência lógica ou progressão temática. O texto pode conter conectores formais e ainda assim apresentar raciocínios circulares ou contraditórios."
        val c3Impact = "O INEP avalia o projeto de texto estratégico. Falhas na coerência, argumentos superficiais ou ideias não desdobradas reduzem a pontuação para 80 ou 120 pontos."
        val c3Improvement = "Divida o texto claramente em: Introdução com tese explícita, dois desenvolvimentos aprofundando causas/consequências distintas, e Conclusão com síntese."

        val c3Evaluation = CompetencyEvaluation(
            number = 3,
            title = "C3: Organização e Projeto de Texto",
            score = c3Score,
            description = "Seleção, interpretação e organização progressiva de argumentos.",
            feedback = "$c3Evidence $c3Limitations",
            identifiedEvidence = c3Evidence,
            limitations = c3Limitations,
            inepCriterionImpact = c3Impact,
            improvementGuidance = c3Improvement
        )

        // ==========================================
        // C4: MECANISMOS LINGUÍSTICOS E COESÃO
        // ==========================================
        // Coesão Interparágrafo: início dos parágrafos a partir do segundo
        val interparagraphConnectors = listOf(
            "além disso", "ademais", "outrossim", "em primeiro lugar", "em primeira análise",
            "por outro lado", "portanto", "desse modo", "nesse sentido", "nesse contexto",
            "sob esse prisma", "dessa forma", "sob essa ótica"
        )
        var interCount = 0
        if (paragraphs.size > 1) {
            for (pIndex in 1 until paragraphs.size) {
                val start = paragraphs[pIndex].lowercase().take(40)
                if (interparagraphConnectors.any { start.startsWith(it) || start.contains(it) }) {
                    interCount++
                }
            }
        }

        val intraparagraphConnectors = listOf(
            "pois", "porque", "contudo", "entretanto", "todavia", "visto que", "já que",
            "embora", "assim", "portanto", "desse modo", "uma vez que", "de modo que"
        )
        val intraFound = intraparagraphConnectors.filter { trimmed.lowercase().contains(it) }

        val c4Score = when {
            isSingleParagraph -> 40 // Ausência completa de coesão interparágrafos
            interCount >= 2 && intraFound.size >= 3 -> 160
            interCount >= 1 || intraFound.isNotEmpty() -> 120
            else -> 80
        }

        val c4Evidence = if (isSingleParagraph) {
            "Texto em bloco único: coesão interparágrafos inexistente."
        } else {
            "Identificados $interCount conectivo(s) interparágrafos no início de blocos e ${intraFound.size} conectores intraparágrafos."
        }

        val c4Limitations = "A quantidade bruta de conectivos não comprova a qualidade da coesão. Conectores empregados com valor inadequado ou repetidos exaustivamente prejudicam a pontuação."
        val c4Impact = "Para atingir as faixas 160 e 200 no INEP, é obrigatório demonstrar diversidade vocabular e conectivos interparágrafos expressos em pelo menos 2 transições."
        val c4Improvement = "Inicie os parágrafos de desenvolvimento e conclusão com operadores argumentativos variados (ex: 'Ademais, ...', 'Portanto, ...') e varie as conjunções internas."

        val c4Evaluation = CompetencyEvaluation(
            number = 4,
            title = "C4: Mecanismos Linguísticos e Coesão",
            score = c4Score,
            description = "Coesão inter e intraparágrafos e diversidade de conectivos.",
            feedback = "$c4Evidence $c4Limitations",
            identifiedEvidence = c4Evidence,
            limitations = c4Limitations,
            inepCriterionImpact = c4Impact,
            improvementGuidance = c4Improvement
        )

        // ==========================================
        // C5: PROPOSTA DE INTERVENÇÃO SOCIAL
        // ==========================================
        val conclusionText = if (paragraphs.size > 1) paragraphs.last().lowercase() else trimmed.lowercase()

        val agentWords = listOf("governo", "estado", "ministério", "secretaria", "escola", "mídia", "sociedade", "poder público", "ong", "família", "legislativo", "judiciário")
        val actionWords = listOf("deve", "precisa", "cabe", "implementar", "criar", "promover", "desenvolver", "garantir", "fiscalizar", "investir", "atuar", "estruturar")
        val modeWords = listOf("por meio de", "mediante", "através de", "por intermédio de", "com o apoio", "por intermédio", "com a criação")
        val effectWords = listOf("para que", "a fim de", "com o intuito de", "com o objetivo de", "com o fito de", "de modo a", "visando")
        val detailWords = listOf("como por exemplo", "a saber", "especialmente", "isto é", ",", "—", "-")

        val hasAgent = agentWords.any { conclusionText.contains(it) }
        val hasAction = actionWords.any { conclusionText.contains(it) }
        val hasMode = modeWords.any { conclusionText.contains(it) }
        val hasEffect = effectWords.any { conclusionText.contains(it) }
        val hasDetail = conclusionText.length > 120 && (conclusionText.contains("exemplo") || conclusionText.contains("como") || conclusionText.contains("a saber") || conclusionText.count { it == ',' } >= 3)

        val elementsCount = listOf(hasAgent, hasAction, hasMode, hasEffect, hasDetail).count { it }

        val c5Score = when {
            isSingleParagraph && elementsCount <= 2 -> 40
            elementsCount == 5 -> 200
            elementsCount == 4 -> 160
            elementsCount in 2..3 -> 120
            elementsCount == 1 -> 80
            else -> 40
        }

        val c5Evidence = "Identificados indícios léxicos de $elementsCount dos 5 elementos da proposta de intervenção no parágrafo conclusivo (Agente: $hasAgent, Ação: $hasAction, Meio: $hasMode, Efeito: $hasEffect, Detalhamento: $hasDetail)."
        val c5Limitations = "A presença isolada de palavras como 'governo' ou 'por meio de' NÃO comprova uma proposta completa, articulada e viável. A verificação do respeito aos direitos humanos e exequibilidade depende de interpretação semântica."
        val c5Impact = "No INEP, cada elemento válido e articulado (Agente, Ação, Modo/Meio, Efeito e Detalhamento) corresponde a 40 pontos na Competência 5."
        val c5Improvement = "Articule expressamente os 5 elementos em uma única frase completa: quem fará (Agente), o que fará (Ação), como fará (Meio/Modo com 'por meio de'), para que fará (Efeito com 'a fim de') e um detalhamento explicativo de um deles."

        val c5Evaluation = CompetencyEvaluation(
            number = 5,
            title = "C5: Proposta de Intervenção e Cidadania",
            score = c5Score,
            description = "Elaboração de proposta de intervenção articulada com os 5 elementos.",
            feedback = "$c5Evidence $c5Limitations",
            identifiedEvidence = c5Evidence,
            limitations = c5Limitations,
            inepCriterionImpact = c5Impact,
            improvementGuidance = c5Improvement
        )

        val competencies = listOf(c1Evaluation, c2Evaluation, c3Evaluation, c4Evaluation, c5Evaluation)
        val totalScore = competencies.sumOf { it.score }.coerceIn(0, 1000)

        // Geração do relatório Markdown Estruturado Offline
        val rawMarkdown = buildString {
            append("# DIAGNÓSTICO ESTRUTURAL PRELIMINAR (MODO OFFLINE)\n\n")
            append("> [!IMPORTANT]\n")
            append("> **Aviso Pedagógico**: Este é um diagnóstico estrutural preliminar gerado offline com base em métricas observáveis (extensão, parágrafos, conectivos e indícios lexicais). Ele **NÃO equivale à correção oficial do ENEM** e não possui a precisão analítica de uma avaliação semântica realizada por inteligência artificial conectada ou por avaliadores humanos do INEP.\n\n")

            append("## Pontuação Estimada Preliminar: $totalScore / 1000\n\n")

            append("## Tabela de Competências (Estimativa Estrutural)\n")
            append("| Competência | Nota Estimada | Indicador Observado |\n")
            append("| Competência 1 | ${c1Evaluation.score} | ${c1Evidence.take(60)}... |\n")
            append("| Competência 2 | ${c2Evaluation.score} | ${c2Evidence.take(60)}... |\n")
            append("| Competência 3 | ${c3Evaluation.score} | ${c3Evidence.take(60)}... |\n")
            append("| Competência 4 | ${c4Evaluation.score} | ${c4Evidence.take(60)}... |\n")
            append("| Competência 5 | ${c5Evaluation.score} | ${c5Evidence.take(60)}... |\n\n")

            append("## Diagnóstico Detalhado por Competência\n\n")

            competencies.forEach { comp ->
                append("### ${comp.title} — ${comp.score}/200 pts\n")
                append("- **O que foi identificado no texto**: ${comp.identifiedEvidence}\n")
                append("- **Limitação da análise estrutural**: ${comp.limitations}\n")
                append("- **Critério oficial do INEP**: ${comp.inepCriterionImpact}\n")
                append("- **Como melhorar**: ${comp.improvementGuidance}\n\n")
            }

            append("## Métricas do Texto\n")
            append("- **Total de Palavras**: $wordCount\n")
            append("- **Linhas Estimadas**: $lineCount (contagem estimada para folha de redação oficial de 30 linhas)\n")
            append("- **Divisão de Parágrafos**: $paragraphCount parágrafo(s)\n")
        }

        return EssayCorrectionResult(
            totalScore = totalScore,
            topic = topic,
            competencies = competencies,
            generalDiagnosis = "Diagnóstico estrutural preliminar gerado em modo offline. Aponta tendências formais observáveis e não substitui a correção semântica oficial do ENEM.",
            rawMarkdown = rawMarkdown,
            wordCount = wordCount,
            lineCount = lineCount,
            isPreliminaryDiagnosis = true,
            evaluationType = "OFFLINE_HEURISTIC",
            structuralObservations = observations,
            isInsufficientLength = false
        )
    }
}
