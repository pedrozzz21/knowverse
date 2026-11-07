package com.example.data.assessment

import com.squareup.moshi.Moshi
import java.text.Normalizer
import kotlin.math.abs
import kotlin.math.max

/**
 * Motor de correção com feedback pedagógico.
 *
 * Princípios:
 *  - Nenhuma aprovação por critério frágil: alternativas e números são comparados exatamente
 *    (numérico com tolerância declarada), discursivas por rubrica explícita e Python por testes.
 *  - Todo erro devolve (1) o raciocínio correto, (2) o conceito provavelmente não compreendido e
 *    (3) uma orientação para resolver novamente.
 */
object AnswerEvaluator {

    // ---------------------------------------------------------------- normalização

    private val diacritics = Regex("\\p{Mn}+")
    private val spaces = Regex("\\s+")

    /** Mesma normalização do validador Python (`norm`): minúsculas, sem acentos, espaços colapsados. */
    fun normalize(text: String): String =
        Normalizer.normalize(text.lowercase(), Normalizer.Form.NFD)
            .replace(diacritics, "")
            .replace(spaces, " ")
            .trim()

    const val DEFAULT_RELATIVE_TOLERANCE = 0.01 // Tolerância relativa padrão de 1% (0,01)

    /**
     * Converte texto digitado em números candidatos com validação estrita.
     * Suporta:
     * - Vírgula decimal ("0,5") e ponto decimal ("0.5")
     * - Frações irredutíveis e equivalentes ("1/2", "2/4", "-7/2")
     * - Notação científica ("1.5e-3", "1,5 * 10^-3", "3e8")
     * - Números negativos e com sinais explícitos
     * - Rejeita com segurança divisões por zero ("1/0") e entradas inválidas.
     */
    fun parseNumberCandidates(raw: String): List<Double> {
        val t = raw.replace('−', '-').replace("\u2212", "-").trim()
        if (t.isBlank()) return emptyList()

        // 1. Frações: a/b, -a/b, a/-b (ex.: "1/2", "2/4", "-7/2", "3 / 6")
        val fractionRegex = Regex("""^([+-]?\d+(?:[.,]\d+)?)\s*/\s*([+-]?\d+(?:[.,]\d+)?)$""")
        fractionRegex.find(t)?.let { m ->
            val num = m.groupValues[1].replace(',', '.').toDoubleOrNull()
            val den = m.groupValues[2].replace(',', '.').toDoubleOrNull()
            if (num != null && den != null) {
                // Divisão por zero: entrada matematicamente inválida, rejeita sem fallback para numerador
                if (abs(den) < 1e-12) return emptyList()
                val result = num / den
                if (!result.isNaN() && !result.isInfinite()) return listOf(result)
            }
            return emptyList()
        }
        // Se a entrada contém '/' mas não casou com fração válida (ex.: "1/0/2" ou texto), não prossegue
        if (t.contains('/')) return emptyList()

        // 2. Notação científica padrão: 1.5e-3, -2.4E4, 3e8, 1,5e-3
        val sciRegex = Regex("""^([+-]?\d+(?:[.,]\d+)?)\s*[eE]\s*([+-]?\d+)$""")
        sciRegex.find(t)?.let { m ->
            val base = m.groupValues[1].replace(',', '.').toDoubleOrNull()
            val exp = m.groupValues[2].toIntOrNull()
            if (base != null && exp != null) {
                val result = base * Math.pow(10.0, exp.toDouble())
                if (!result.isNaN() && !result.isInfinite()) return listOf(result)
            }
            return emptyList()
        }

        // 3. Notação científica com potência de 10: 1,5 * 10^-3, 2 x 10^4, 3 × 10^8
        val powerRegex = Regex("""^([+-]?\d+(?:[.,]\d+)?)\s*(?:x|×|\*)\s*10\s*\^?\s*\(?\s*([+-]?\d+)\)?$""")
        powerRegex.find(t)?.let { m ->
            val base = m.groupValues[1].replace(',', '.').toDoubleOrNull()
            val exp = m.groupValues[2].toIntOrNull()
            if (base != null && exp != null) {
                val result = base * Math.pow(10.0, exp.toDouble())
                if (!result.isNaN() && !result.isInfinite()) return listOf(result)
            }
            return emptyList()
        }

        // 4. Extração de número decimal simples ou com unidade
        val tok = Regex("""^[+-]?\d[\d.,]*$""").find(t)?.value?.trimEnd('.', ',')
        val targetToken = if (tok != null) {
            tok
        } else {
            // Permite número com unidade no final (ex.: "62,5 m/s", "100 km/h", "R$ 50,00")
            val tokenWithUnit = Regex("""^([+-]?\d[\d.,]*)\s*([a-zA-Z/°%Ωμ\^0-9\s]*)$""").find(t)
            tokenWithUnit?.groupValues?.getOrNull(1)?.trimEnd('.', ',') ?: return emptyList()
        }

        val out = mutableListOf<Double>()
        val hasDot = targetToken.contains('.')
        val hasComma = targetToken.contains(',')
        when {
            hasDot && hasComma -> {
                val decimalIsComma = targetToken.lastIndexOf(',') > targetToken.lastIndexOf('.')
                val cleaned = if (decimalIsComma) targetToken.replace(".", "").replace(',', '.')
                else targetToken.replace(",", "")
                cleaned.toDoubleOrNull()?.let(out::add)
            }
            hasComma -> {
                if (targetToken.count { it == ',' } > 1) targetToken.replace(",", "").toDoubleOrNull()?.let(out::add)
                else targetToken.replace(',', '.').toDoubleOrNull()?.let(out::add)
            }
            hasDot -> {
                targetToken.toDoubleOrNull()?.let(out::add)
                if (Regex("""^[+-]?\d{1,3}(\.\d{3})+$""").matches(targetToken)) {
                    targetToken.replace(".", "").toDoubleOrNull()?.let(out::add)
                }
            }
            else -> targetToken.toDoubleOrNull()?.let(out::add)
        }
        return out.filter { !it.isNaN() && !it.isInfinite() }.distinct()
    }

    // ---------------------------------------------------------------- entrada principal

    /** Corrige questões que não dependem de execução de código. */
    fun evaluate(q: Question, answer: UserAnswer?): Feedback {
        if (answer == null) return unanswered(q)
        return when (q.format) {
            QuestionFormat.MULTIPLE_CHOICE, QuestionFormat.CHART -> evaluateChoice(q, answer)
            QuestionFormat.TRUE_FALSE -> evaluateTrueFalse(q, answer)
            QuestionFormat.MATCHING -> evaluateMatching(q, answer)
            QuestionFormat.FILL_BLANK -> evaluateFill(q, answer)
            QuestionFormat.NUMERIC -> evaluateNumeric(q, answer)
            QuestionFormat.ESSAY -> evaluateEssay(q, answer)
            QuestionFormat.CODE -> Feedback(
                Verdict.INCORRECT, 0.0, "Execute o código",
                q.explanation, q.concept, "Questões de programação são corrigidas executando os testes automatizados."
            )
        }
    }

    private fun unanswered(q: Question) = Feedback(
        verdict = Verdict.UNANSWERED,
        score = 0.0,
        headline = "Sem resposta",
        reasoning = q.explanation,
        misunderstoodConcept = q.concept,
        retryTip = q.retryGuidance
    )

    private fun correct(q: Question, extra: List<String> = emptyList()) = Feedback(
        verdict = Verdict.CORRECT,
        score = 1.0,
        headline = "Correto!",
        reasoning = q.explanation,
        details = extra
    )

    private fun wrong(q: Question, concept: String?, headline: String, details: List<String> = emptyList(), tip: String? = null) =
        Feedback(
            verdict = Verdict.INCORRECT,
            score = 0.0,
            headline = headline,
            reasoning = q.explanation,
            misunderstoodConcept = concept?.takeIf { it.isNotBlank() } ?: q.concept,
            retryTip = tip ?: q.retryGuidance,
            details = details
        )

    // ---------------------------------------------------------------- múltipla escolha / gráfico

    private fun evaluateChoice(q: Question, a: UserAnswer): Feedback {
        val choice = (a as? UserAnswer.Choice)?.index ?: return unanswered(q)
        if (choice == q.answerIndex) return correct(q)
        val chosenText = q.options.getOrNull(choice).orEmpty()
        val concept = q.distractorConcepts.getOrNull(choice)
        val right = q.options.getOrNull(q.answerIndex).orEmpty()
        return wrong(
            q, concept,
            headline = "Incorreto",
            details = listOf(
                "Você marcou: $chosenText",
                "Resposta correta: $right"
            )
        )
    }

    // ---------------------------------------------------------------- verdadeiro/falso

    private fun evaluateTrueFalse(q: Question, a: UserAnswer): Feedback {
        val v = (a as? UserAnswer.Bool)?.value ?: return unanswered(q)
        val expected = q.answerBool ?: return unanswered(q)
        if (v == expected) return correct(q)
        return wrong(
            q, q.concept, "Incorreto",
            details = listOf(
                "Você julgou a afirmação como ${if (v) "verdadeira" else "falsa"}; ela é ${if (expected) "verdadeira" else "falsa"}."
            )
        )
    }

    // ---------------------------------------------------------------- associação

    private fun evaluateMatching(q: Question, a: UserAnswer): Feedback {
        val mapping = (a as? UserAnswer.Pairs)?.mapping ?: return unanswered(q)
        if (mapping.isEmpty()) return unanswered(q)
        val wrongLeft = mutableListOf<String>()
        var hits = 0
        q.pairs.forEach { (l, r) ->
            if (mapping[l]?.let { normalize(it) == normalize(r) } == true) hits++ else wrongLeft += l
        }
        val score = hits.toDouble() / q.pairs.size
        if (hits == q.pairs.size) return correct(q)
        return Feedback(
            verdict = if (hits > 0) Verdict.PARTIAL else Verdict.INCORRECT,
            score = score,
            headline = "$hits de ${q.pairs.size} associações corretas",
            reasoning = q.explanation,
            misunderstoodConcept = q.concept,
            retryTip = q.retryGuidance,
            details = wrongLeft.map { l -> "Revise: \"$l\" → ${q.pairs.first { it.first == l }.second}" }
        )
    }

    // ---------------------------------------------------------------- preenchimento

    private fun evaluateFill(q: Question, a: UserAnswer): Feedback {
        val text = (a as? UserAnswer.Text)?.value?.takeIf { it.isNotBlank() } ?: return unanswered(q)
        val given = normalize(text).trim('.', ' ')
        val accepted = q.answers.map { normalize(it).trim('.', ' ') }
        if (given in accepted) return correct(q)
        // números equivalentes (12,5 == 12.5)
        val gNums = parseNumberCandidates(text)
        if (gNums.isNotEmpty() && q.answers.any { ans ->
                val aNums = parseNumberCandidates(ans)
                aNums.any { x -> gNums.any { y -> abs(x - y) <= 1e-9 * max(1.0, abs(x)) } }
            } && !given.any { it.isLetter() && it != 'e' }
        ) return correct(q)
        val near = accepted.any { it.length >= 5 && editDistance(it, given) <= 1 }
        val details = mutableListOf("Resposta esperada: ${q.answers.first()}")
        if (near) details += "Sua resposta está quase certa: confira a grafia exata do termo."
        return wrong(q, q.concept, "Incorreto", details)
    }

    // ---------------------------------------------------------------- numérica

    private fun evaluateNumeric(q: Question, a: UserAnswer): Feedback {
        val text = (a as? UserAnswer.Text)?.value?.takeIf { it.isNotBlank() } ?: return unanswered(q)
        val expected = q.answerNumber ?: return unanswered(q)
        val candidates = parseNumberCandidates(text)
        if (candidates.isEmpty()) {
            return wrong(q, q.concept, "Resposta não reconhecida",
                listOf("Digite apenas um número (ex.: 62,5)."), "Informe um valor numérico; a unidade é opcional.")
        }
        // Fórmula de tolerância:
        // Se a questão declara tolerância explícita > 0, usa q.tolerance;
        // caso contrário, aplica a tolerância relativa padrão de 1% (0,01 * |esperado|), com piso de 1e-6 para o zero.
        val allowedTol = if (q.tolerance > 0.0) {
            q.tolerance
        } else {
            max(1e-6, abs(expected) * DEFAULT_RELATIVE_TOLERANCE)
        }
        if (candidates.any { abs(it - expected) <= allowedTol }) return correct(q)
        val v = candidates.first()
        val diagnostics = mutableListOf("Você respondeu ${formatNum(v)}${unitSuffix(q)}; o valor correto é ${formatNum(expected)}${unitSuffix(q)}.")
        var concept: String? = null
        var tip: String? = null
        if (expected != 0.0 && v != 0.0) {
            val ratio = v / expected
            when {
                abs(ratio + 1) < 0.01 -> {
                    concept = "Sinal do resultado (positivo × negativo)"
                    tip = "Resultado com módulo certo e sinal trocado: reveja a convenção de sinais/sentido adotada."
                }
                listOf(10.0, 100.0, 1000.0, 0.1, 0.01, 0.001, 60.0, 3.6, 1.0 / 3.6, 1.0 / 60.0).any { abs(ratio - it) / it < 0.01 } -> {
                    concept = "Conversão de unidades ou ordem de grandeza"
                    tip = "Seu valor difere por um fator de ${formatNum(ratio)}: confira conversões de unidade e potências de 10."
                }
                abs(ratio - 2) < 0.01 || abs(ratio - 0.5) < 0.01 -> {
                    concept = "Fator 2 no cálculo (dobro/metade)"
                    tip = "Você ficou com o dobro/metade do valor: verifique coeficientes como 1/2 ou 2 na fórmula."
                }
            }
        }
        if (concept != null) diagnostics += "Diagnóstico: $concept."
        return wrong(q, concept ?: q.concept, "Incorreto", diagnostics, tip)
    }

    private fun unitSuffix(q: Question) = if (q.unit.isBlank()) "" else " ${q.unit}"

    private fun formatNum(v: Double): String =
        if (v == Math.rint(v) && abs(v) < 1e12) v.toLong().toString()
        else String.format(java.util.Locale("pt", "BR"), "%.4f", v).trimEnd('0').trimEnd(',')

    // ---------------------------------------------------------------- discursiva (rubrica)

    data class RubricResult(val earned: Int, val total: Int, val met: List<RubricCriterion>, val missed: List<RubricCriterion>)

    fun scoreRubric(q: Question, text: String): RubricResult {
        val t = normalize(text)
        var earned = 0
        val met = mutableListOf<RubricCriterion>()
        val missed = mutableListOf<RubricCriterion>()
        q.rubric.forEach { c ->
            val ok = c.keywords.all { group -> group.any { normalize(it) in t } }
            if (ok) { earned += c.points; met += c } else missed += c
        }
        return RubricResult(earned, q.rubric.sumOf { it.points }, met, missed)
    }

    private fun evaluateEssay(q: Question, a: UserAnswer): Feedback {
        val text = (a as? UserAnswer.Text)?.value?.takeIf { it.isNotBlank() } ?: return unanswered(q)
        val r = scoreRubric(q, text)
        val score = if (r.total == 0) 0.0 else r.earned.toDouble() / r.total
        val details = buildList {
            r.met.forEach { add("✔ ${it.criterion} (+${it.points})") }
            r.missed.forEach { add("✘ ${it.criterion} (0/${it.points})") }
            add("Resposta-modelo: ${q.modelAnswer}")
        }
        val verdict = when {
            score >= 0.999 -> Verdict.CORRECT
            score >= q.passScore -> Verdict.PARTIAL
            score > 0 -> Verdict.PARTIAL
            else -> Verdict.INCORRECT
        }
        val approved = score >= q.passScore
        return Feedback(
            verdict = verdict,
            score = score,
            headline = "Rubrica: ${r.earned}/${r.total} pontos" + if (approved) " — aprovado" else " — abaixo do mínimo (${(q.passScore * 100).toInt()}%)",
            reasoning = q.explanation,
            misunderstoodConcept = if (r.missed.isEmpty()) null else r.missed.joinToString("; ") { it.criterion },
            retryTip = if (r.missed.isEmpty()) null else q.retryGuidance,
            details = details
        )
    }

    // ---------------------------------------------------------------- programação (testes automatizados)

    data class CodeRunResult(val success: Boolean, val stdout: String, val error: String?)

    data class TestOutcome(val test: CodeTestCase, val passed: Boolean, val got: String)

    private const val MARK = "@@KV@@"

    /** Gera o programa Python: código do aluno + execução de cada `call` comparando `repr` com o esperado. */
    fun buildCodeHarness(userCode: String, tests: List<CodeTestCase>): String = buildString {
        appendLine(userCode)
        appendLine()
        appendLine("import json as __kv_json")
        appendLine("__kv_out = []")
        tests.forEach { t ->
            appendLine("try:")
            appendLine("    __kv_r = repr(${t.call})")
            appendLine("except BaseException as __kv_e:")
            appendLine("    __kv_r = 'EXC:' + type(__kv_e).__name__")
            appendLine("try:")
            appendLine("    __kv_x = repr(${t.expected})")
            appendLine("except BaseException:")
            appendLine("    __kv_x = '?'")
            appendLine("__kv_out.append([__kv_r, __kv_x])")
        }
        appendLine("print('$MARK' + __kv_json.dumps(__kv_out))")
    }

    @Suppress("UNCHECKED_CAST")
    fun parseHarnessOutput(stdout: String, tests: List<CodeTestCase>): List<TestOutcome>? {
        val line = stdout.lineSequence().lastOrNull { it.startsWith(MARK) } ?: return null
        val parsed = try {
            Moshi.Builder().build().adapter(Any::class.java).fromJson(line.removePrefix(MARK)) as? List<Any?>
        } catch (e: Exception) { null } ?: return null
        if (parsed.size != tests.size) return null
        return tests.mapIndexed { i, t ->
            val pair = parsed[i] as List<Any?>
            val got = pair[0].toString()
            val exp = pair[1].toString()
            TestOutcome(t, got == exp && exp != "?", got)
        }
    }

    fun feedbackFromOutcomes(q: Question, outcomes: List<TestOutcome>): Feedback {
        val passed = outcomes.count { it.passed }
        val total = outcomes.size
        val details = outcomes.mapIndexed { i, o ->
            when {
                o.passed -> "✔ Teste ${i + 1}${if (o.test.hidden) " (oculto)" else ": ${o.test.call}"} passou"
                o.test.hidden -> "✘ Teste ${i + 1} (oculto) falhou — pense em casos de borda (vazio, zero, negativos, repetidos)."
                o.got.startsWith("EXC:") -> "✘ Teste ${i + 1}: ${o.test.call} lançou ${o.got.removePrefix("EXC:")}"
                else -> "✘ Teste ${i + 1}: ${o.test.call} retornou ${o.got}, esperado ${o.test.expected}"
            }
        }
        if (passed == total) {
            return Feedback(Verdict.CORRECT, 1.0, "Todos os testes passaram ($passed/$total)", q.explanation, details = details)
        }
        val nameErr = outcomes.any { it.got == "EXC:NameError" }
        return Feedback(
            verdict = if (passed > 0) Verdict.PARTIAL else Verdict.INCORRECT,
            score = passed.toDouble() / total,
            headline = "$passed de $total testes passaram",
            reasoning = q.explanation,
            misunderstoodConcept = if (nameErr) "Definição de função com o nome pedido" else q.concept,
            retryTip = if (nameErr) "Mantenha o nome e os parâmetros da função exatamente como no código inicial." else q.retryGuidance,
            details = details
        )
    }

    /** Corrige uma questão de código executando os testes via [runner] (PythonEngine no app). */
    suspend fun evaluateCode(q: Question, userCode: String, runner: suspend (String) -> CodeRunResult): Feedback {
        val spec = q.code ?: return wrong(q, q.concept, "Questão de código sem testes")
        if (userCode.isBlank()) return unanswered(q)
        val result = runner(buildCodeHarness(userCode, spec.tests))
        if (!result.success) {
            return Feedback(
                verdict = Verdict.INCORRECT,
                score = 0.0,
                headline = "Erro ao executar o código",
                reasoning = q.explanation,
                misunderstoodConcept = "Sintaxe/execução do programa",
                retryTip = "Leia a mensagem de erro, corrija a linha indicada e execute novamente.",
                details = listOfNotNull(result.error?.take(400))
            )
        }
        val outcomes = parseHarnessOutput(result.stdout, spec.tests)
            ?: return Feedback(
                Verdict.INCORRECT, 0.0, "Não foi possível avaliar os testes",
                q.explanation, q.concept,
                "Não use input() nem encerre o programa antes do fim; defina apenas a função pedida.",
                listOfNotNull(result.stdout.takeIf { it.isNotBlank() }?.take(300))
            )
        return feedbackFromOutcomes(q, outcomes)
    }

    // ---------------------------------------------------------------- util

    internal fun editDistance(a: String, b: String): Int {
        if (a == b) return 0
        val dp = IntArray(b.length + 1) { it }
        for (i in 1..a.length) {
            var prev = dp[0]
            dp[0] = i
            for (j in 1..b.length) {
                val tmp = dp[j]
                dp[j] = minOf(dp[j] + 1, dp[j - 1] + 1, prev + if (a[i - 1] == b[j - 1]) 0 else 1)
                prev = tmp
            }
        }
        return dp[b.length]
    }
}

