package com.example.data.essay

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
     * Caso a chave da API Gemini não esteja configurada ou ocorra falha de rede,
     * executa uma avaliação heurística pedagógica transparente e honesta,
     * baseada estritamente na matriz analítica das 5 competências do INEP,
     * com apontamento explícito de suas limitações.
     */
    suspend fun correctEssay(
        topic: String,
        essayText: String,
        apiKeyOverride: String? = null
    ): Result<EssayCorrectionResult> = withContext(Dispatchers.IO) {
        try {
            val apiKey = apiKeyOverride?.takeIf { it.isNotBlank() } ?: getApiKey()

            if (apiKey.isNotBlank() && apiKey != "DEFAULT_API_KEY") {
                val prompt = "Corrija a seguinte redação sobre o tema:\n$topic\n\nRedação:\n$essayText"
                val systemInstructionContent = if (REGRAS_CORRETOR_INEP.isNotBlank()) {
                    REGRAS_CORRETOR_INEP
                } else {
                    BASE_INEP_METHODOLOGY
                }

                val aiResult = runCatching {
                    callGeminiGenerateContent(
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
                }

                if (aiResult.isSuccess) {
                    val parsed = parseCorrectionResult(
                        topic = topic,
                        essayText = essayText,
                        markdown = aiResult.getOrThrow()
                    )
                    return@withContext Result.success(parsed)
                } else {
                    Log.w(TAG, "Falha na chamada da API Gemini (${aiResult.exceptionOrNull()?.message}). Recorrendo à avaliação heurística transparente.")
                }
            }

            // Avaliação heurística honesta e transparente com critérios pedagógicos calibrados
            val heuristicResult = evaluateHeuristicPedagogical(topic, essayText)
            Result.success(heuristicResult)
        } catch (e: Exception) {
            Log.e(TAG, "Erro ao processar correção da redação", e)
            Result.failure(e)
        }
    }

    /**
     * Avaliação diagnóstica heurística transparente e honesta.
     * Não infla notas artificialmente por simples contagem de palavras ou conectivos isolados.
     * Segue os intervalos oficiais de 40 pontos da matriz do ENEM (0, 40, 80, 120, 160, 200).
     */
    fun evaluateHeuristicPedagogical(topic: String, essayText: String): EssayCorrectionResult {
        val trimmed = essayText.trim()
        val paragraphs = trimmed.split(Regex("\\n\\s*\\n")).map { it.trim() }.filter { it.isNotBlank() }
        val allLines = trimmed.lines().map { it.trim() }.filter { it.isNotBlank() }
        val words = trimmed.split(Regex("\\s+")).filter { it.isNotBlank() }
        val wordCount = words.size
        val lineEstimate = maxOf(allLines.size, (wordCount / 9.5).toInt())

        // 1. Regra de anulação por extensão insuficiente (menos de 7 linhas / menos de 50 palavras)
        if (wordCount < 40 || lineEstimate < 7) {
            val zeroCompetencies = (1..5).map { i ->
                CompetencyEvaluation(
                    number = i,
                    title = getCompetencyTitle(i),
                    score = 0,
                    maxScore = 200,
                    description = getCompetencyDescription(i),
                    feedback = "Texto com extensão insuficiente (inferior a 7 linhas / 40 palavras). De acordo com o edital do ENEM, redações nessa extensão recebem nota zero."
                )
            }
            val zeroReport = """
# NOTA FINAL: 0 / 1000

## Avaliação Diagnóstica Preliminar (Regulamento INEP)
- **Extensão apurada:** $wordCount palavras (~$lineEstimate linhas).
- **Critério de Anulação:** O Edital do ENEM estabelece que textos com até 7 linhas são considerados "Em Branco/Insuficientes", resultando em nota zero sumária.
- **Orientação:** Para obter uma avaliação formativa válida, redija um texto dissertativo-argumentativo completo contendo entre 20 e 30 linhas (mínimo de 150 a 200 palavras), dividido em introdução, 2 parágrafos de desenvolvimento e proposta de intervenção.
            """.trimIndent()

            return EssayCorrectionResult(
                totalScore = 0,
                topic = topic,
                competencies = zeroCompetencies,
                generalDiagnosis = "Redação com extensão insuficiente (anulada pelo critério de menos de 7 linhas do edital do ENEM).",
                rawMarkdown = zeroReport,
                wordCount = wordCount,
                lineCount = lineEstimate
            )
        }

        // =========================================================================
        // COMPETÊNCIA 1: Domínio da modalidade escrita formal
        // Critérios: Estruturação frasal, pontuação de períodos, repetições excessivas,
        // concordância nominal/verbal básica e ausência de gírias/internetês evidente.
        // =========================================================================
        val c1Score: Int
        val c1Feedback: String
        val sentences = trimmed.split(Regex("[.!?]+\\s+")).filter { it.isNotBlank() }
        val avgSentenceLength = if (sentences.isNotEmpty()) wordCount.toDouble() / sentences.size else 0.0

        val informalPatterns = listOf("pra", "vc", "tb", "tbm", "qdo", "pq", "tipo assim", "né", "tá", "tou")
        val foundInformal = informalPatterns.filter { trimmed.contains(Regex("\\b$it\\b", RegexOption.IGNORE_CASE)) }

        val hasTooLongSentences = sentences.any { it.split(Regex("\\s+")).size > 45 }
        val sentencePunctuationRatio = if (wordCount > 0) sentences.size.toDouble() / (wordCount / 20.0) else 0.0

        if (foundInformal.isNotEmpty() || avgSentenceLength > 50 || sentencePunctuationRatio < 0.4) {
            c1Score = 80
            c1Feedback = "Presença de marcas de informalidade (${foundInformal.joinToString()}) ou períodos truncados e excessivamente longos sem pontuação adequada."
        } else if (hasTooLongSentences || avgSentenceLength > 35) {
            c1Score = 120
            c1Feedback = "Estrutura sintática regular com alguns desvios de pontuação e períodos longos que prejudicam a fluidez formal."
        } else if (paragraphs.size >= 3 && wordCount >= 180) {
            c1Score = 160
            c1Feedback = "Bom domínio da modalidade formal, com pontuação adequada dos períodos e vocabulário acadêmico pertinente."
        } else {
            c1Score = 120
            c1Feedback = "Demonstra conhecimento mediano da norma padrão com estrutura sintática razoável."
        }

        // =========================================================================
        // COMPETÊNCIA 2: Compreensão da proposta e tipo dissertativo-argumentativo
        // Critérios: tangenciamento temático, estrutura de introdução/desenvolvimento/conclusão,
        // número de parágrafos e presença de repertório legitimado identificável.
        // =========================================================================
        val topicKeywords = topic.lowercase().split(Regex("[^a-záéíóúâêîôûãõç0-9]+")).filter { it.length > 3 }
        val matchesTopicCount = topicKeywords.count { kw -> trimmed.lowercase().contains(kw) }
        val topicCoverage = if (topicKeywords.isNotEmpty()) matchesTopicCount.toDouble() / topicKeywords.size else 1.0

        val repertoryKeywords = listOf(
            "constituição", "artigo", "filósofo", "sociólogo", "teoria", "ibge",
            "século", "história", "bauman", "foucault", "habermas", "aristóteles",
            "kant", "rousseau", "pesquisa", "dado", "estatística", "organização mundial",
            "onu", "unesco", "declaração universal", "cidadania", "democracia"
        )
        val foundRepertory = repertoryKeywords.filter { trimmed.lowercase().contains(it) }

        val c2Score: Int
        val c2Feedback: String

        if (topicCoverage < 0.25) {
            // Tangenciamento severo do tema
            c2Score = 40
            c2Feedback = "Tangenciamento temático: o texto praticamente não dialoga com as palavras-chave do tema proposto."
        } else if (paragraphs.size < 3) {
            // Texto em bloco único ou apenas dois parágrafos
            c2Score = 80
            c2Feedback = "Estrutura deficitária: o texto não apresenta a divisão canônica tripartite (introdução, desenvolvimento e conclusão em parágrafos distintos)."
        } else if (foundRepertory.isEmpty()) {
            c2Score = 120
            c2Feedback = "Compreende a proposta e desenvolve o tipo dissertativo, mas limita-se ao senso comum sem repertório sociocultural legitimado e produtivo."
        } else if (foundRepertory.size >= 2 && wordCount >= 220 && paragraphs.size in 3..5) {
            c2Score = 160
            c2Feedback = "Desenvolve bem o tema com estrutura dissertativa consolidada e articulação de repertório sociocultural pertinente (menção a: ${foundRepertory.take(2).joinToString()})."
        } else {
            c2Score = 120
            c2Feedback = "Abordagem completa do tema nos moldes dissertativos, com repertório legitimado incipiente ou de pouca produtividade comprovada."
        }

        // =========================================================================
        // COMPETÊNCIA 3: Projeto de texto, coerência e argumentação
        // Critérios: Progressão temática, presença de operadores argumentativos e
        // equilíbrio entre os parágrafos de desenvolvimento (evita parágrafo de 1 frase).
        // =========================================================================
        val hasTinyParagraphs = paragraphs.any { it.split(Regex("\\s+")).size < 20 }
        val hasMonolithicDevelopment = paragraphs.size >= 3 && paragraphs.drop(1).dropLast(1).any { it.split(Regex("\\s+")).size > 140 }

        val argumentMarkers = listOf(
            "evidencia", "comprova", "resulta", "consequentemente", "devido a",
            "haja vista", "torna-se evidente", "fundamental", "urgente", "grave",
            "problema", "obstáculo", "desafio", "impacto", "notoriamente"
        )
        val foundArgMarkers = argumentMarkers.count { trimmed.lowercase().contains(it) }

        val c3Score: Int
        val c3Feedback: String

        if (hasTinyParagraphs && paragraphs.size < 4) {
            c3Score = 80
            c3Feedback = "Projeto de texto com falhas visíveis: parágrafos embrionários que interrompem o fluxo argumentativo e a defesa da tese."
        } else if (foundArgMarkers < 3 || wordCount < 160) {
            c3Score = 120
            c3Feedback = "Projeto de texto com defesa de ponto de vista razoável, porém com desenvolvimento dos argumentos previsível e pouco articulado."
        } else if (paragraphs.size in 3..5 && foundArgMarkers >= 4 && !hasTinyParagraphs && !hasMonolithicDevelopment) {
            c3Score = 160
            c3Feedback = "Projeto de texto estratégico perceptível, com argumentos organizados de forma progressiva e coerente entre desenvolvimento 1 e 2."
        } else {
            c3Score = 120
            c3Feedback = "Argumentação aceitável, apresentando ideias pertinentes ao tema, porém necessitando de maior encadeamento de causa e efeito."
        }

        // =========================================================================
        // COMPETÊNCIA 4: Mecanismos de coesão (inter e intraparágrafo)
        // Critérios: Não basta ter conectivos soltos; verifica presença no início
        // dos parágrafos de desenvolvimento/conclusão e variedade de operadores.
        // =========================================================================
        val interConnectives = listOf(
            "além disso", "ademais", "outrossim", "nesse sentido", "nesse contexto",
            "sob essa ótica", "diante desse cenário", "por conseguinte", "portanto",
            "em suma", "assim sendo", "desse modo", "logo", "destarte"
        )
        val paragraphsStartingWithConnective = paragraphs.drop(1).count { p ->
            val pLower = p.lowercase()
            interConnectives.any { pLower.startsWith(it) }
        }

        val c4Score: Int
        val c4Feedback: String

        if (paragraphsStartingWithConnective == 0) {
            c4Score = 80
            c4Feedback = "Raras conexões interparágrafos: os parágrafos iniciam sem conectivos de transição argumentativa explícitos."
        } else if (paragraphsStartingWithConnective >= 2 && wordCount >= 180) {
            c4Score = 160
            c4Feedback = "Presença expressiva de recursos coesivos interparágrafos e intraparágrafos com adequada diversificação de articuladores."
        } else {
            c4Score = 120
            c4Feedback = "Utilização regular de mecanismos coesivos com algumas repetições ou elos de transição mecânicos."
        }

        // =========================================================================
        // COMPETÊNCIA 5: Proposta de intervenção e respeito aos direitos humanos
        // Critérios Oficiais do INEP: 5 Elementos (Agente, Ação, Meio/Modo, Efeito, Detalhamento)
        // Cada elemento comprovado agrega 40 pontos na grade oficial do INEP.
        // =========================================================================
        val lastParagraph = paragraphs.lastOrNull()?.lowercase() ?: ""

        val agentKeywords = listOf(
            "governo", "ministério", "estado", "escola", "mídia", "sociedade",
            "família", "poder público", "legislativo", "judiciário", "ongs", "cidadãos"
        )
        val hasAgent = agentKeywords.any { lastParagraph.contains(it) }

        val actionKeywords = listOf(
            "deve criar", "deve implementar", "deve promover", "cabe ao", "precisa",
            "desenvolver", "fiscalizar", "investir", "garantir", "elaborar"
        )
        val hasAction = actionKeywords.any { lastParagraph.contains(it) }

        val meansKeywords = listOf(
            "por meio de", "mediante", "através de", "com o auxílio de", "a partir de",
            "por intermédio", "com ações de"
        )
        val hasMeans = meansKeywords.any { lastParagraph.contains(it) }

        val effectKeywords = listOf(
            "a fim de", "para que", "com o objetivo de", "com o intuito de", "visando",
            "de modo a", "para combater", "com o propósito"
        )
        val hasEffect = effectKeywords.any { lastParagraph.contains(it) }

        val detailKeywords = listOf(
            "como por exemplo", "especialmente", "notadamente", "tais como", "isto é",
            "a exemplo de", "com foco em"
        )
        val hasDetail = detailKeywords.any { lastParagraph.contains(it) }

        var c5ElementsFound = 0
        val missingElements = mutableListOf<String>()

        if (hasAgent) c5ElementsFound++ else missingElements.add("Agente (quem executa)")
        if (hasAction) c5ElementsFound++ else missingElements.add("Ação (o que deve ser feito)")
        if (hasMeans) c5ElementsFound++ else missingElements.add("Modo/Meio (como executar)")
        if (hasEffect) c5ElementsFound++ else missingElements.add("Efeito/Finalidade (para que)")
        if (hasDetail) c5ElementsFound++ else missingElements.add("Detalhamento (especificação)")

        // Mapeamento estrito na tabela INEP: 0 elementos = 0, 1 = 40, 2 = 80, 3 = 120, 4 = 160, 5 = 200
        val c5Score = (c5ElementsFound * 40).coerceIn(0, 200)
        val c5Feedback = when {
            c5ElementsFound == 5 -> "Proposta de intervenção completa contendo todos os 5 elementos obrigatórios do INEP (Agente, Ação, Meio, Efeito e Detalhamento)."
            c5ElementsFound in 3..4 -> "Proposta de intervenção consistente ($c5ElementsFound de 5 elementos identificados). Ausências detectadas: ${missingElements.joinToString()}."
            c5ElementsFound in 1..2 -> "Proposta de intervenção incipiente ($c5ElementsFound de 5 elementos). Faltam: ${missingElements.joinToString()}."
            else -> "Proposta de intervenção ausente ou sem os elementos canônicos no parágrafo conclusivo."
        }

        val competencies = listOf(
            CompetencyEvaluation(1, getCompetencyTitle(1), c1Score, 200, getCompetencyDescription(1), c1Feedback),
            CompetencyEvaluation(2, getCompetencyTitle(2), c2Score, 200, getCompetencyDescription(2), c2Feedback),
            CompetencyEvaluation(3, getCompetencyTitle(3), c3Score, 200, getCompetencyDescription(3), c3Feedback),
            CompetencyEvaluation(4, getCompetencyTitle(4), c4Score, 200, getCompetencyDescription(4), c4Feedback),
            CompetencyEvaluation(5, getCompetencyTitle(5), c5Score, 200, getCompetencyDescription(5), c5Feedback)
        )

        val totalScore = competencies.sumOf { it.score }.coerceIn(0, 1000)

        val generalDiagnosis = "Diagnóstico heurístico estruturado baseado na matriz analítica das 5 competências do INEP."

        val rawMarkdown = buildString {
            appendLine("# NOTA ESTIMADA (DIAGNÓSTICO HEURÍSTICO FORMATIVO): $totalScore / 1000")
            appendLine()
            appendLine("> **Aviso Metodológico & Transparência:** Esta avaliação foi gerada pelo motor analítico offline do Knowverse seguindo as rubricas das 5 Competências do INEP. Diferentemente de uma correção humana por banca de corretores ou de análise semântica generativa profunda, este diagnóstico baseia-se em indicadores estruturais, coesivos, de distribuição parágrafo-frasal e presença dos 5 elementos da proposta de intervenção.")
            appendLine()
            appendLine("## Tabela de Desempenho por Competência")
            appendLine("| Competência | Nota | Diagnóstico Sintético |")
            appendLine("| :--- | :---: | :--- |")
            competencies.forEach { c ->
                appendLine("| ${c.title} | ${c.score} / 200 | ${c.feedback} |")
            }
            appendLine()
            appendLine("## Diagnóstico Detalhado")
            appendLine("### Métricas Estruturais da Redação")
            appendLine("- **Contagem total de palavras:** $wordCount palavras")
            appendLine("- **Linhas estimadas:** ~$lineEstimate linhas (distribuídas em ${paragraphs.size} parágrafos)")
            appendLine("- **Equilíbrio estrutural:** ${if (paragraphs.size in 3..5) "Adequado (Introdução, Desenvolvimento e Conclusão demarcados)" else "Atenção: texto fora da divisão recomendada de 4 parágrafos"}")
            appendLine()
            appendLine("### Análise da Proposta de Intervenção (Competência 5)")
            appendLine("- **Agente:** ${if (hasAgent) "Identificado ✓" else "Ausente ou genérico ✗"}")
            appendLine("- **Ação:** ${if (hasAction) "Identificada ✓" else "Ausente ou vaga ✗"}")
            appendLine("- **Modo/Meio:** ${if (hasMeans) "Identificado ✓ (conectivo modal)" else "Ausente ✗"}")
            appendLine("- **Efeito/Finalidade:** ${if (hasEffect) "Identificado ✓" else "Ausente ✗"}")
            appendLine("- **Detalhamento:** ${if (hasDetail) "Identificado ✓" else "Ausente ✗"}")
            appendLine()
            appendLine("### Orientações Pedagógicas para Evolução")
            appendLine("1. **Expansão de Repertório (C2):** Busque conectar alusões históricas, conceitos sociológicos ou marcos constitucionais de forma produtiva ao tema.")
            appendLine("2. **Conexões Interparágrafos (C4):** Inicie o segundo parágrafo de desenvolvimento e a conclusão com operadores argumentativos explícitos (*Ademais*, *Por conseguinte*, *Portanto*).")
            appendLine("3. **Fechamento da Proposta (C5):** Certifique-se de explicitar os 5 elementos na intervenção, detalhando o modo de execução ou o agente com exemplos práticos.")
        }

        return EssayCorrectionResult(
            totalScore = totalScore,
            topic = topic,
            competencies = competencies,
            generalDiagnosis = generalDiagnosis,
            rawMarkdown = rawMarkdown,
            wordCount = wordCount,
            lineCount = lineEstimate
        )
    }

    private fun getCompetencyTitle(num: Int): String = when (num) {
        1 -> "C1: Domínio da Norma Culta"
        2 -> "C2: Compreensão da Proposta e Repertório"
        3 -> "C3: Organização e Projeto de Texto"
        4 -> "C4: Mecanismos Linguísticos e Coesão"
        5 -> "C5: Proposta de Intervenção Social"
        else -> "Competência $num"
    }

    private fun getCompetencyDescription(num: Int): String = when (num) {
        1 -> "Gramática, ortografia, pontuação, concordância e regência."
        2 -> "Desenvolvimento do tema, pertinência da tese e repertório sociocultural."
        3 -> "Coerência, seleção de argumentos, dados e defesa do ponto de vista."
        4 -> "Coesão interparágrafo e intraparágrafo, conectivos e fluidez textual."
        5 -> "Proposta detalhada de solução para o problema com respeito aos direitos humanos."
        else -> ""
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

    private fun callGeminiGenerateContent(
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

        val competencyList = mutableListOf<CompetencyEvaluation>()

        for (i in 1..5) {
            val cPattern = Pattern.compile("Competência\\s*$i.*?\\|?\\s*(\\d{1,3})", Pattern.CASE_INSENSITIVE)
            val matcher = cPattern.matcher(markdown)
            val score = if (matcher.find()) {
                val found = matcher.group(1)?.toIntOrNull() ?: 120
                // Força intervalo oficial do ENEM (múltiplos de 40)
                val rounded = (Math.round(found / 40.0) * 40).toInt()
                rounded.coerceIn(0, 200)
            } else {
                120
            }

            competencyList.add(
                CompetencyEvaluation(
                    number = i,
                    title = getCompetencyTitle(i),
                    score = score,
                    description = getCompetencyDescription(i),
                    feedback = "Avaliação realizada de acordo com a matriz analítica do INEP."
                )
            )
        }

        if (totalScore == 0) {
            totalScore = competencyList.sumOf { it.score }
        }
        totalScore = totalScore.coerceIn(0, 1000)

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

