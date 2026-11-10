package com.example.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.example.ui.theme.BorderSubtle
import com.example.ui.theme.ErrorRose
import com.example.ui.theme.GoldXp
import com.example.ui.theme.ObsidianSurface
import com.example.ui.theme.PrimaryViolet
import com.example.ui.theme.SuccessEmerald
import com.example.ui.theme.SurfaceCard
import com.example.ui.theme.SurfaceElevated
import com.example.ui.theme.TextPrimary
import com.example.ui.theme.TextSecondary
import com.example.ui.theme.TextTertiary
import org.json.JSONObject

/**
 * Utilitários de normalização de expressões matemáticas para LaTeX padrão (KaTeX).
 */
object MathNotationHelper {

    /**
     * Converte notações informais, códigos ASCII e termos de cálculo em LaTeX estrito
     * para renderização de alta fidelidade idêntica ao Gemini e livros universitários.
     */
    fun toStandardLatex(raw: String): String {
        if (raw.isEmpty()) return raw
        return try {
            var s = raw.trim()

        // Remove aspas externas (duplas, simples ou crases) que causem erros de sintaxe no KaTeX
        s = s.replace(Regex("""^["'`]+|["'`]+$"""), "").trim()

        // Remove marcadores de bloco de código markdown e delimitadores
        s = s.replace(Regex("""```(?:latex|math)?\s*([\s\S]*?)\s*```"""), "$1").trim()
        s = s.removePrefix("\\[").removeSuffix("\\]").trim()
        s = s.removePrefix("\\\\[").removeSuffix("\\\\]").trim()
        s = s.removePrefix("$$").removeSuffix("$$").trim()
        s = s.removePrefix("\\(").removeSuffix("\\)").trim()
        s = s.removePrefix("$").removeSuffix("$").trim()
        s = s.replace(Regex("""^["'`]+|["'`]+$"""), "").trim()

        // Normalização de contra-barras duplicadas vindas de strings literais ou JSON (\\frac -> \frac)
        s = s.replace(Regex("""\\\\+([a-zA-Z]+)"""), """\\$1""")
        s = s.replace(Regex("""\\\\+([{}])"""), """\\$1""")

        // Normalização de Limites: lim_{x -> a} ou lim(x -> a) -> \lim_{x \to a}
        s = s.replace(Regex("""(?<!\\)lim_\{"""), """\\lim_{""")
        s = s.replace(Regex("""(?<!\\)lim\(([^)]+)\)"""), """\\lim_{$1}""")
        s = s.replace("->", """\to """)
        s = s.replace("-->", """\to """)
        s = s.replace("\\rightarrow", """\to """)

        // Notações de limites laterais
        s = s.replace("a⁻", "a^-")
        s = s.replace("a⁺", "a^+")
        s = s.replace("0⁻", "0^-")
        s = s.replace("0⁺", "0^+")
        s = s.replace(Regex("""\\to\s*([a-zA-Z0-9]+)\^-"""), """\\to $1^-""")
        s = s.replace(Regex("""\\to\s*([a-zA-Z0-9]+)\^\+"""), """\\to $1^+""")

        // Subscritos numéricos e de variáveis
        s = s.replace("L₁", "L_1")
        s = s.replace("L₂", "L_2")
        s = s.replace("L₃", "L_3")
        s = s.replace("x₀", "x_0")
        s = s.replace("x₁", "x_1")
        s = s.replace("x₂", "x_2")

        // Símbolos especiais
        s = s.replace("!=", """\neq """)
        s = s.replace("≠", """\neq """)
        s = s.replace("<=", """\le """)
        s = s.replace("≤", """\le """)
        s = s.replace(">=", """\ge """)
        s = s.replace("≥", """\ge """)
        s = s.replace("±", """\pm """)
        s = s.replace("∞", """\infty """)
        s = s.replace("+∞", """+\\infty """)
        s = s.replace("-∞", """-\\infty """)
        s = s.replace("ℝ", """\mathbb{R}""")
        s = s.replace("ℤ", """\mathbb{Z}""")
        s = s.replace("ℕ", """\mathbb{N}""")
        s = s.replace("∈", """\in """)
        s = s.replace("∉", """\notin """)
        s = s.replace("·", """\cdot """)
        s = s.replace("≈", """\approx """)
        s = s.replace("<=>", """\iff """)
        s = s.replace("=>", """\implies """)
        s = s.replace(" | ", """ \quad\Big|\quad """)

        // Trigonometria e Radicais
        s = s.replace("sen(", """\sin(""")
        s = s.replace("sen ", """\sin """)
        s = s.replace("tg(", """\tan(""")
        s = s.replace("tg ", """\tan """)
        s = s.replace("cos(", """\cos(""")
        s = s.replace("cos ", """\cos """)
        s = s.replace("√", """\sqrt""")
        s = s.replace("∛", """\sqrt[3]""")

        // Expoentes e potências comuns
        s = s.replace("ˣ", "^x")
        s = s.replace("ᵏ", "^k")
        s = s.replace("²", "^2")
        s = s.replace("³", "^3")
        s = s.replace("ⁿ", "^n")
        s = s.replace("⁻¹", "^{-1}")

        // Formatação refinada de Limites Fundamentais
        s = s.replace("(1 + 1/x)^x", """\left(1 + \frac{1}{x}\right)^x""")
        s = s.replace("(1 + 1/x)ˣ", """\left(1 + \frac{1}{x}\right)^x""")
        s = s.replace("(1 + k/x)^x", """\left(1 + \frac{k}{x}\right)^x""")
        s = s.replace("(1 + k/x)ˣ", """\left(1 + \frac{k}{x}\right)^x""")
        s = s.replace("[sen(u) / u]", """\frac{\sin(u)}{u}""")
        s = s.replace("[u / sen(u)]", """\frac{u}{\sin(u)}""")
        s = s.replace("[tg(u) / u]", """\frac{\tan(u)}{u}""")

        // Notação de Fração comum ASCII: (num) / (den) -> \frac{num}{den}
        if (!s.contains("\\frac") && s.contains("/")) {
            s = convertAsciiFractions(s)
        }

            s.trim()
        } catch (_: Throwable) {
            raw.trim()
        }
    }

    private fun convertAsciiFractions(input: String): String {
        if (input.contains(" = ")) {
            val parts = input.split(" = ")
            return parts.joinToString(" = ") { convertSingleExprFractions(it) }
        }
        return convertSingleExprFractions(input)
    }

    private fun convertSingleExprFractions(expr: String): String {
        var res = expr.trim()
        val regex = Regex("""(?:\(([^)]+)\)|([a-zA-Z0-9^_{}+·*-]+))\s*/\s*(?:\(([^)]+)\)|([a-zA-Z0-9^_{}+·*-]+))""")
        return regex.replace(res) { mr ->
            val num = (mr.groups[1]?.value ?: mr.groups[2]?.value ?: "").trim()
            val den = (mr.groups[3]?.value ?: mr.groups[4]?.value ?: "").trim()
            if (num.isNotBlank() && den.isNotBlank()) {
                "\\frac{$num}{$den}"
            } else {
                mr.value
            }
        }
    }

    private val SUPERSCRIPT_MAP = mapOf(
        '0' to '⁰', '1' to '¹', '2' to '²', '3' to '³', '4' to '⁴',
        '5' to '⁵', '6' to '⁶', '7' to '⁷', '8' to '⁸', '9' to '⁹',
        '+' to '⁺', '-' to '⁻', '=' to '⁼', '(' to '⁽', ')' to '⁾',
        'n' to 'ⁿ', 'i' to 'ⁱ', 'x' to 'ˣ', 'y' to 'ʸ', 'k' to 'ᵏ',
        't' to 'ᵗ', 'm' to 'ᵐ', 'a' to 'ᵃ', 'b' to 'ᵇ', 'c' to 'ᶜ',
        'd' to 'ᵈ', 'e' to 'ᵉ', 'f' to 'ᶠ', 'g' to 'ᵍ', 'h' to 'ʰ',
        'j' to 'ʲ', 'l' to 'ˡ', 'o' to 'ᵒ', 'p' to 'ᵖ', 'r' to 'ʳ',
        's' to 'ˢ', 'u' to 'ᵘ', 'v' to 'ᵛ', 'w' to 'ʷ', 'z' to 'ᶻ'
    )

    private val SUBSCRIPT_MAP = mapOf(
        '0' to '₀', '1' to '₁', '2' to '₂', '3' to '₃', '4' to '₄',
        '5' to '₅', '6' to '₆', '7' to '₇', '8' to '₈', '9' to '₉',
        '+' to '₊', '-' to '₋', '=' to '₌', '(' to '₍', ')' to '₎',
        'a' to 'ₐ', 'e' to 'ₑ', 'o' to 'ₒ', 'x' to 'ₓ', 'i' to 'ᵢ',
        'j' to 'ⱼ', 'n' to 'ₙ', 'm' to 'ₘ', 't' to 'ₜ', 'k' to 'ₖ',
        'p' to 'ₚ', 's' to 'ₛ', 'u' to 'ᵤ', 'v' to 'ᵥ', 'r' to 'ᵣ',
        'h' to 'ₕ', 'l' to 'ₗ', 'y' to 'ᵧ'
    )

    private fun toSuperscript(str: String): String {
        val sb = StringBuilder()
        for (ch in str) {
            val sup = SUPERSCRIPT_MAP[ch]
            if (sup != null) {
                sb.append(sup)
            } else {
                return "^($str)"
            }
        }
        return sb.toString()
    }

    private fun toSubscript(str: String): String {
        val sb = StringBuilder()
        for (ch in str) {
            val sub = SUBSCRIPT_MAP[ch]
            if (sub != null) {
                sb.append(sub)
            } else {
                return "($str)"
            }
        }
        return sb.toString()
    }

    private fun findMatchingBrace(text: String, openIndex: Int): Int {
        var depth = 0
        for (i in openIndex until text.length) {
            if (text[i] == '{') depth++
            else if (text[i] == '}') {
                depth--
                if (depth == 0) return i
            }
        }
        return -1
    }

    private fun formatFractions(input: String): String {
        var s = input
        // Normaliza contra-barras extras antes de processar: \\frac -> \frac
        s = s.replace(Regex("""\\\\+frac"""), "\\frac")
        val fracMarker = "\\frac"
        var safetyLimit = 20
        while (s.contains(fracMarker) && safetyLimit > 0) {
            safetyLimit--
            val startIdx = s.indexOf(fracMarker)
            val afterFrac = startIdx + fracMarker.length
            val firstBrace = s.indexOf('{', afterFrac)
            if (firstBrace == -1) break
            val numEnd = findMatchingBrace(s, firstBrace)
            if (numEnd == -1) break
            val numerator = s.substring(firstBrace + 1, numEnd)

            val secondBrace = s.indexOf('{', numEnd + 1)
            if (secondBrace == -1) break
            val denEnd = findMatchingBrace(s, secondBrace)
            if (denEnd == -1) break
            val denominator = s.substring(secondBrace + 1, denEnd)

            val formattedNum = formatInlineMath(numerator).trim()
            val formattedDen = formatInlineMath(denominator).trim()

            val numNeedsParens = formattedNum.contains(" ") || formattedNum.contains("+") || formattedNum.contains("-") || formattedNum.contains("/") || formattedNum.contains("×") || formattedNum.contains("·")
            val denNeedsParens = formattedDen.contains(" ") || formattedDen.contains("+") || formattedDen.contains("-") || formattedDen.contains("/") || formattedDen.contains("×") || formattedDen.contains("·")

            val nStr = if (numNeedsParens && !formattedNum.startsWith("(") && !formattedNum.endsWith(")")) "($formattedNum)" else formattedNum
            val dStr = if (denNeedsParens && !formattedDen.startsWith("(") && !formattedDen.endsWith(")")) "($formattedDen)" else formattedDen

            val replacement = "$nStr / $dStr"

            // Consumir também aspas imediatamente adjacentes (ex: "\frac{...}{...}")
            var actualStart = startIdx
            var actualEnd = denEnd + 1
            if (actualStart > 0 && (s[actualStart - 1] == '"' || s[actualStart - 1] == '\'' || s[actualStart - 1] == '`')) {
                actualStart--
            }
            if (actualEnd < s.length && (s[actualEnd] == '"' || s[actualEnd] == '\'' || s[actualEnd] == '`')) {
                actualEnd++
            }

            s = s.substring(0, actualStart) + replacement + s.substring(actualEnd)
        }
        return s
    }

    private fun formatRadicals(input: String): String {
        var s = input
        val sqrtMarker = "\\sqrt"
        var safetyLimit = 20
        while (s.contains(sqrtMarker) && safetyLimit > 0) {
            safetyLimit--
            val startIdx = s.indexOf(sqrtMarker)
            val afterSqrt = startIdx + sqrtMarker.length
            if (afterSqrt < s.length && s[afterSqrt] == '[') {
                val closeBracket = s.indexOf(']', afterSqrt)
                if (closeBracket != -1 && closeBracket + 1 < s.length && s[closeBracket + 1] == '{') {
                    val rootIdx = s.substring(afterSqrt + 1, closeBracket)
                    val openBrace = closeBracket + 1
                    val closeBrace = findMatchingBrace(s, openBrace)
                    if (closeBrace != -1) {
                        val radicand = formatInlineMath(s.substring(openBrace + 1, closeBrace)).trim()
                        val rootSymbol = if (rootIdx == "3") "∛" else if (rootIdx == "4") "∜" else "√[$rootIdx]"
                        val replacement = "$rootSymbol($radicand)"
                        s = s.substring(0, startIdx) + replacement + s.substring(closeBrace + 1)
                        continue
                    }
                }
            }
            if (afterSqrt < s.length && s[afterSqrt] == '{') {
                val closeBrace = findMatchingBrace(s, afterSqrt)
                if (closeBrace != -1) {
                    val radicand = formatInlineMath(s.substring(afterSqrt + 1, closeBrace)).trim()
                    val replacement = if (radicand.length <= 4 && !radicand.contains(" ") && !radicand.contains("+") && !radicand.contains("-")) {
                        "√$radicand"
                    } else {
                        "√($radicand)"
                    }
                    s = s.substring(0, startIdx) + replacement + s.substring(closeBrace + 1)
                    continue
                }
            }
            s = s.replaceFirst(sqrtMarker, "√")
        }
        return s
    }

    private val STRIP_QUOTED_MATH_DOUBLE = Regex("\"([^\"\\n]*\\\\[a-zA-Z]+[^\"\\n]*)\"")
    private val STRIP_QUOTED_MATH_SINGLE = Regex("'([^'\\n]*\\\\[a-zA-Z]+[^'\\n]*)'")
    private val STRIP_QUOTED_MATH_BACKTICK = Regex("`([^`\\n]*\\\\[a-zA-Z]+[^`\\n]*)`")
    private val DOUBLE_BACKSLASH = Regex("""\\\\+([a-zA-Z]+)""")

    /**
     * Limpeza e enriquecimento de expressões matemáticas para exibição inline autêntica.
     */
    fun formatInlineMath(raw: String): String {
        if (raw.isEmpty()) return raw
        return try {
            // Se a string não contiver nenhum caractere ou comando matemático especial, retorne-a diretamente
            // Isso evita overhead e qualquer falha em textos puros de Linguagens, História e Filosofia
            if (!raw.contains('\\') && !raw.contains('^') && !raw.contains('_') && 
                !raw.contains("$$") && !raw.contains("\\[") && !raw.contains("\\(") &&
                !raw.contains("lim") && !raw.contains("->") && !raw.contains("√") && 
                !raw.contains("∫") && !raw.contains("∑") && !raw.contains("≠") && 
                !raw.contains("≤") && !raw.contains("≥") && !raw.contains("±") &&
                !raw.contains("frac") && !raw.contains("sqrt")
            ) {
                return raw
            }

            var s = raw.trim()

            // Remove aspas ou crases envolventes em termos com comandos LaTeX
            s = STRIP_QUOTED_MATH_DOUBLE.replace(s, "$1")
            s = STRIP_QUOTED_MATH_SINGLE.replace(s, "$1")
            s = STRIP_QUOTED_MATH_BACKTICK.replace(s, "$1")

            // Normalização de contra-barras duplicadas
            s = DOUBLE_BACKSLASH.replace(s, """\\$1""")

            // Passo 1: Frações completas com suporte a chaves aninhadas
            s = formatFractions(s)

            // Passo 2: Raízes quadradas e enésimas
            s = formatRadicals(s)

            // Passo 3: Operadores e símbolos matemáticos comuns
            s = s.replace("\\times", "×")
        s = s.replace("\\div", "÷")
        s = s.replace("\\cdot", "·")
        s = s.replace("\\approx", "≈")
        s = s.replace("\\neq", "≠")
        s = s.replace("!=", "≠")
        s = s.replace("\\le", "≤")
        s = s.replace("\\leq", "≤")
        s = s.replace("<=", "≤")
        s = s.replace("\\ge", "≥")
        s = s.replace("\\geq", "≥")
        s = s.replace(">=", "≥")
        s = s.replace("\\pm", "±")
        s = s.replace("\\mp", "∓")
        s = s.replace("\\infty", "∞")
        s = s.replace("\\to", "→")
        s = s.replace("->", "→")
        s = s.replace("-->", "→")
        s = s.replace("\\rightarrow", "→")
        s = s.replace("\\iff", "⇔")
        s = s.replace("<=>", "⇔")
        s = s.replace("\\implies", "⇒")
        s = s.replace("=>", "⇒")
        s = s.replace("\\in", "∈")
        s = s.replace("\\notin", "∉")
        s = s.replace("\\subset", "⊂")
        s = s.replace("\\cap", "∩")
        s = s.replace("\\cup", "∪")
        s = s.replace("\\forall", "∀")
        s = s.replace("\\exists", "∃")
        s = s.replace("\\dots", "...")
        s = s.replace("\\ldots", "...")
        s = s.replace("\\cdots", "···")
        s = s.replace("\\circ", "°")
        s = s.replace("\\degree", "°")
        s = s.replace("\\partial", "∂")
        s = s.replace("\\nabla", "∇")
        s = s.replace("\\int", "∫")
        s = s.replace("\\sum", "∑")
        s = s.replace("\\prod", "∏")

        // Conjuntos numéricos
        s = s.replace("\\mathbb{R}", "ℝ")
        s = s.replace("\\mathbb{Z}", "ℤ")
        s = s.replace("\\mathbb{N}", "ℕ")
        s = s.replace("\\mathbb{Q}", "ℚ")
        s = s.replace("\\mathbb{C}", "ℂ")

        // Letras gregas
        s = s.replace("\\pi", "π")
        s = s.replace("\\alpha", "α")
        s = s.replace("\\beta", "β")
        s = s.replace("\\theta", "θ")
        s = s.replace("\\delta", "δ")
        s = s.replace("\\Delta", "Δ")
        s = s.replace("\\lambda", "λ")
        s = s.replace("\\mu", "μ")
        s = s.replace("\\sigma", "σ")
        s = s.replace("\\omega", "ω")

        // Funções trigonométricas e limites
        s = s.replace("\\sin", "sen")
        s = s.replace("\\cos", "cos")
        s = s.replace("\\tan", "tg")
        s = s.replace("\\ln", "ln")
        s = s.replace("\\log", "log")
        s = s.replace(Regex("""\\lim_\{([a-zA-Z0-9]+)\s*→\s*([^}]+)\}"""), "lim[$1 → $2]")
        s = s.replace(Regex("""\\lim_\{([^}]+)\}"""), "lim[$1]")
        s = s.replace("\\lim", "lim")

        // Remoção de tags de formatação LaTeX
        s = s.replace(Regex("""\\(?:text|mathrm|mathbf|operatorname)\{([^}]+)\}"""), "$1")
        s = s.replace("\\left(", "(")
        s = s.replace("\\right)", ")")
        s = s.replace("\\left[", "[")
        s = s.replace("\\right]", "]")
        s = s.replace("\\left\\{", "{")
        s = s.replace("\\right\\}", "}")
        s = s.replace("\\{", "{")
        s = s.replace("\\}", "}")
        s = s.replace("\\,", " ")
        s = s.replace("\\;", " ")
        s = s.replace("\\:", " ")
        s = s.replace("\\!", "")
        s = s.replace("\\quad", " ")
        s = s.replace("\\qquad", "  ")

        // Potências superscritas com chaves: ^{...}
        s = s.replace(Regex("""\^\{([^}]+)\}""")) { matchResult ->
            toSuperscript(matchResult.groupValues[1])
        }

        // Potências superscritas de um caractere ou dígito simples: ^2, ^-1, ^n, etc.
        s = s.replace(Regex("""\^([0-9nxyktmabc+-])""")) { matchResult ->
            toSuperscript(matchResult.groupValues[1])
        }

        // Subscritos com chaves: _{...}
        s = s.replace(Regex("""_\{([^}]+)\}""")) { matchResult ->
            toSubscript(matchResult.groupValues[1])
        }

        // Subscritos de um caractere simples: _1, _n, etc.
        s = s.replace(Regex("""_([0-9aeoxijntkp+-])""")) { matchResult ->
            toSubscript(matchResult.groupValues[1])
        }

        // Limpeza de comandos LaTeX residuais com contra-barra
        s = s.replace(Regex("""\\([a-zA-Z]+)""")) { matchResult ->
            val cmd = matchResult.groupValues[1]
            when (cmd) {
                "to" -> "→"
                "cdot" -> "·"
                "neq" -> "≠"
                "le", "leq" -> "≤"
                "ge", "geq" -> "≥"
                "pm" -> "±"
                "infty" -> "∞"
                "sin" -> "sen"
                "cos" -> "cos"
                "tan" -> "tg"
                "lim" -> "lim"
                "int" -> "∫"
                "sqrt" -> "√"
                else -> cmd
            }
        }
        s = s.replace("\\", "")
        s = s.trim().removePrefix("\"").removeSuffix("\"").removePrefix("'").removeSuffix("'")

        s.trim()
    } catch (_: Throwable) {
        raw
    }
}
}

/**
 * Renderizador de fórmulas matemáticas KaTeX de alta fidelidade usando WebView local (offline).
 * Produz limites com subscrito alinhado diretamente abaixo do termo `lim`, frações verticais com barra
 * horizontal, parênteses escaláveis e tipografia matemática com serifa itálica idêntica ao Gemini.
 */
@Composable
fun KaTeXFormulaView(
    formula: String,
    modifier: Modifier = Modifier,
    isDisplayMode: Boolean = true,
    fontSizeEm: Float = 1.25f,
    accentColorHex: String = "#67E8F9"
) {
    val normalizedFormula = remember(formula) {
        MathNotationHelper.toStandardLatex(formula)
    }

    val formattedMath = remember(normalizedFormula) {
        try {
            MathNotationHelper.formatInlineMath(normalizedFormula)
        } catch (_: Throwable) {
            normalizedFormula
        }
    }

    val formulaColor = remember(accentColorHex) {
        try {
            Color(android.graphics.Color.parseColor(accentColorHex))
        } catch (_: Throwable) {
            Color(0xFF67E8F9)
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = if (isDisplayMode) 8.dp else 4.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = formattedMath,
            style = (if (isDisplayMode) MaterialTheme.typography.titleMedium else MaterialTheme.typography.bodyMedium).copy(
                fontFamily = FontFamily.Serif,
                fontWeight = FontWeight.SemiBold,
                fontSize = if (isDisplayMode) 17.5.sp else 15.sp,
                color = formulaColor,
                letterSpacing = 0.5.sp,
                lineHeight = if (isDisplayMode) 26.sp else 22.sp
            ),
            textAlign = TextAlign.Center
        )
    }
}

private fun buildKaTeXHtml(
    formula: String,
    isDisplayMode: Boolean,
    fontSizeEm: Float,
    accentColorHex: String
): String {
    val escapedFormula = JSONObject.quote(formula)
    return """
        <!DOCTYPE html>
        <html>
        <head>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
        <link rel="stylesheet" href="file:///android_asset/katex/katex.min.css">
        <script src="file:///android_asset/katex/katex.min.js"></script>
        <style>
          * { box-sizing: border-box; }
          html, body {
            margin: 0;
            padding: 8px 12px;
            background-color: transparent;
            color: #F1F5F9;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            overflow-x: auto;
            overflow-y: visible;
            -webkit-overflow-scrolling: touch;
          }
          #katex-wrapper {
            display: inline-flex;
            justify-content: center;
            align-items: center;
            min-width: 100%;
            width: max-content;
            margin: 0 auto;
          }
          #katex-target {
            display: inline-block;
            padding: 4px 6px;
            white-space: nowrap;
          }
          .katex-display {
            margin: 4px 0 !important;
            text-align: center;
          }
          .katex {
            font-size: ${fontSizeEm}em;
            color: ${accentColorHex};
          }
          .katex .mop {
            font-weight: 700;
            color: #FFFFFF;
          }
          .katex .mord.mathnormal {
            font-style: italic;
          }
          .katex .minner {
            color: #E2E8F0;
          }
          .katex .mfrac .frac-line {
            border-bottom-width: 1.5px !important;
            border-bottom-color: #67E8F9 !important;
          }
        </style>
        </head>
        <body>
          <div id="katex-wrapper">
            <div id="katex-target"></div>
          </div>
          <script>
            function sendHeight() {
              if (window.AndroidInterface) {
                var el = document.getElementById('katex-target');
                var h = el ? el.offsetHeight : 0;
                var bodyH = document.body.scrollHeight || document.documentElement.scrollHeight;
                var finalH = Math.max(h, bodyH, 44);
                window.AndroidInterface.onHeight(finalH);
              }
            }
            function renderMath() {
              var el = document.getElementById('katex-target');
              if (!el) return;
              var raw = $escapedFormula;
              if (typeof raw === 'string') {
                raw = raw.trim().replace(/^["'`]+|["'`]+$/g, '');
                raw = raw.replace(/\\\\+([a-zA-Z]+)/g, '\\$1');
                raw = raw.replace(/\\\\+([{}])/g, '\\$1');
                raw = raw.replace(/\\r\\n|\\r|\\n/g, ' ');
              }
              try {
                katex.render(raw, el, {
                  displayMode: $isDisplayMode,
                  throwOnError: false
                });
              } catch(e) {
                console.error("KaTeX parse error", e);
                var fallback = (raw || '')
                  .replace(/\\frac\{([^}]+)\}\{([^}]+)\}/g, '($1) / ($2)')
                  .replace(/\\lim_\{([^}]+)\}/g, 'lim[$1]')
                  .replace(/\\to/g, '→')
                  .replace(/\\cdot/g, '·')
                  .replace(/\\sqrt\{([^}]+)\}/g, '√($1)')
                  .replace(/\\/g, '');
                el.innerText = fallback;
              }
              sendHeight();
              setTimeout(sendHeight, 60);
              setTimeout(sendHeight, 250);
            }
            document.addEventListener("DOMContentLoaded", renderMath);
            window.addEventListener("load", sendHeight);
          </script>
        </body>
        </html>
    """.trimIndent()
}

/**
 * Card destacado para fórmulas e equações analíticas com KaTeX idêntico ao Gemini.
 */
@Composable
fun MathFormulaCard(
    formula: String,
    modifier: Modifier = Modifier,
    label: String? = null,
    rawLatex: String? = null
) {
    val cleanLatex = MathNotationHelper.toStandardLatex(formula)

    val (categoryLabel, categoryIcon, categoryColor) = when {
        cleanLatex.contains("\\int") || cleanLatex.contains("∫") -> Triple("∫ Integral & Primitiva", Icons.Default.Functions, GoldXp)
        cleanLatex.contains("\\lim") || cleanLatex.contains("lim") -> Triple("lim Limite & Continuidade", Icons.Default.Calculate, Color(0xFF38BDF8))
        cleanLatex.contains("d/dx") || cleanLatex.contains("\\frac{d") || cleanLatex.contains("′") -> Triple("d/dx Derivada & Taxa", Icons.Default.Functions, Color(0xFFA78BFA))
        cleanLatex.contains("sin") || cleanLatex.contains("cos") || cleanLatex.contains("tan") || cleanLatex.contains("π") -> Triple("f(x) Trigonometria", Icons.Default.SquareFoot, Color(0xFF34D399))
        cleanLatex.contains("cases") || cleanLatex.contains("⎧") -> Triple("f(x) Função por Partes", Icons.Default.Functions, GoldXp)
        else -> Triple("f(x) Equação Analítica", Icons.Default.Functions, PrimaryViolet)
    }

    Surface(
        color = SurfaceElevated,
        shape = RoundedCornerShape(10.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, BorderSubtle),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = categoryIcon,
                        contentDescription = null,
                        tint = GoldXp,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(6.dp))
                    Text(
                        text = label ?: "Equação Analítica",
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = GoldXp,
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp
                        )
                    )
                }

                Surface(
                    color = categoryColor.copy(alpha = 0.18f),
                    shape = RoundedCornerShape(4.dp),
                    border = androidx.compose.foundation.BorderStroke(0.8.dp, categoryColor.copy(alpha = 0.4f))
                ) {
                    Text(
                        text = categoryLabel,
                        style = MaterialTheme.typography.labelSmall.copy(
                            color = categoryColor,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 10.sp
                        ),
                        modifier = Modifier.padding(horizontal = 7.dp, vertical = 2.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            // Box com renderização visual KaTeX de alta resolução
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(ObsidianSurface)
                    .border(1.dp, BorderSubtle.copy(alpha = 0.8f), RoundedCornerShape(8.dp))
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.Center
            ) {
                KaTeXFormulaView(
                    formula = cleanLatex,
                    isDisplayMode = true,
                    fontSizeEm = 1.25f,
                    accentColorHex = "#67E8F9"
                )
            }
        }
    }
}

/**
 * Renderizador de parágrafos com suporte completo a Markdown (**negrito**, *itálico*)
 * e notações matemáticas inline com serifa itálica e símbolos matemáticos autênticos.
 * Elimina vazamentos de asteriscos (**...) e códigos crus.
 */
private val MARKDOWN_IMAGE_REGEX = Regex("""!\[(.*?)\]\((.*?)\)""")

private sealed class FormattedContentPart {
    data class Text(val content: String) : FormattedContentPart()
    data class Image(val alt: String, val url: String) : FormattedContentPart()
}

private fun splitFormattedContentParts(text: String): List<FormattedContentPart> {
    if (!text.contains("![")) return listOf(FormattedContentPart.Text(text))
    val matches = MARKDOWN_IMAGE_REGEX.findAll(text).toList()
    if (matches.isEmpty()) return listOf(FormattedContentPart.Text(text))

    val result = mutableListOf<FormattedContentPart>()
    var cursor = 0
    for (match in matches) {
        if (match.range.first > cursor) {
            val chunk = text.substring(cursor, match.range.first).trim()
            if (chunk.isNotEmpty()) {
                result.add(FormattedContentPart.Text(chunk))
            }
        }
        val alt = match.groupValues[1].trim()
        val url = match.groupValues[2].trim()
        if (url.isNotEmpty()) {
            result.add(FormattedContentPart.Image(alt = alt, url = url))
        }
        cursor = match.range.last + 1
    }
    if (cursor < text.length) {
        val remaining = text.substring(cursor).trim()
        if (remaining.isNotEmpty()) {
            result.add(FormattedContentPart.Text(remaining))
        }
    }
    return result
}

@Composable
fun OptionImageView(
    imageUrl: String,
    altText: String = "",
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var isExpanded by remember { mutableStateOf(false) }

    val cleanUrl = remember(imageUrl) {
        val m = MARKDOWN_IMAGE_REGEX.find(imageUrl)
        if (m != null) m.groupValues[2].trim() else imageUrl.trim()
    }

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(8.dp))
            .background(ObsidianSurface)
            .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
            .clickable { isExpanded = true }
            .padding(6.dp),
        contentAlignment = Alignment.Center
    ) {
        SubcomposeAsyncImage(
            model = ImageRequest.Builder(context)
                .data(cleanUrl)
                .setHeader("User-Agent", "Knowverse/2.0 (Android; Mobile)")
                .crossfade(true)
                .build(),
            contentDescription = altText.ifBlank { "Figura da alternativa" },
            contentScale = ContentScale.Fit,
            loading = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(60.dp),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = GoldXp,
                        strokeWidth = 2.dp,
                        modifier = Modifier.size(18.dp)
                    )
                }
            },
            error = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(4.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Image,
                        contentDescription = null,
                        tint = TextSecondary,
                        modifier = Modifier.size(16.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = altText.ifBlank { "Ver imagem" },
                        style = MaterialTheme.typography.bodySmall.copy(color = TextSecondary, fontSize = 11.sp)
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(min = 40.dp, max = 110.dp)
        )
    }

    if (isExpanded) {
        Dialog(
            onDismissRequest = { isExpanded = false },
            properties = DialogProperties(usePlatformDefaultWidth = false)
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = Color.Black.copy(alpha = 0.94f)
            ) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    IconButton(
                        onClick = { isExpanded = false },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(16.dp)
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(SurfaceElevated.copy(alpha = 0.7f))
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Fechar",
                            tint = Color.White
                        )
                    }

                    SubcomposeAsyncImage(
                        model = ImageRequest.Builder(context)
                            .data(cleanUrl)
                            .setHeader("User-Agent", "Knowverse/2.0 (Android; Mobile)")
                            .crossfade(true)
                            .build(),
                        contentDescription = altText,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier
                            .fillMaxWidth(0.92f)
                            .heightIn(max = 500.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(ObsidianSurface)
                            .padding(8.dp)
                    )
                }
            }
        }
    }
}

@Composable
fun MathFormattedText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = TextPrimary,
    lineHeightSp: Float = 21f,
    allowBlockCards: Boolean = true
) {
    val mathColor = Color(0xFF67E8F9)

    // Se o texto inteiro for envolto em aspas com comandos LaTeX ("\frac{...}"), removemos as aspas externas
    val trimmed = text.trim()
    val cleanText = if ((trimmed.startsWith("\"") && trimmed.endsWith("\"") && trimmed.length >= 2) ||
        (trimmed.startsWith("'") && trimmed.endsWith("'") && trimmed.length >= 2) ||
        (trimmed.startsWith("`") && trimmed.endsWith("`") && trimmed.length >= 2)
    ) {
        val inner = trimmed.substring(1, trimmed.length - 1).trim()
        if (inner.contains("\\") || inner.contains("/")) inner else trimmed
    } else {
        trimmed
    }

    val parts = remember(cleanText) { splitFormattedContentParts(cleanText) }

    if (parts.size == 1 && parts[0] is FormattedContentPart.Text) {
        RenderMathFormattedBlock(
            text = (parts[0] as FormattedContentPart.Text).content,
            modifier = modifier,
            style = style,
            color = color,
            lineHeightSp = lineHeightSp,
            allowBlockCards = allowBlockCards,
            mathColor = mathColor
        )
    } else {
        Column(
            modifier = modifier,
            verticalArrangement = Arrangement.spacedBy(if (allowBlockCards) 12.dp else 6.dp)
        ) {
            parts.forEach { part ->
                when (part) {
                    is FormattedContentPart.Text -> {
                        RenderMathFormattedBlock(
                            text = part.content,
                            style = style,
                            color = color,
                            lineHeightSp = lineHeightSp,
                            allowBlockCards = allowBlockCards,
                            mathColor = mathColor
                        )
                    }
                    is FormattedContentPart.Image -> {
                        if (allowBlockCards) {
                            DidacticImageView(
                                imagePath = part.url,
                                altText = part.alt.ifBlank { "Figura da questão" },
                                modifier = Modifier.fillMaxWidth()
                            )
                        } else {
                            OptionImageView(
                                imageUrl = part.url,
                                altText = part.alt.ifBlank { "Figura da alternativa" }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RenderMathFormattedBlock(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = TextPrimary,
    lineHeightSp: Float = 21f,
    allowBlockCards: Boolean = true,
    mathColor: Color = Color(0xFF67E8F9)
) {
    // Se o texto contiver blocos em destaque delimitados por \[ ... \] ou $$ ... $$,
    // quebramos para exibir os blocos via KaTeXFormulaView apenas se allowBlockCards for true
    val blockMarker = "@@MATH_BLOCK@@"
    val hasBlocks = allowBlockCards && (
        text.contains("$$") || text.contains("\\[") || text.contains("\\\\[") ||
        text.contains("```latex") || text.contains("```math")
    )

    if (hasBlocks) {
        val normalizedBlocks = text
            .replace(Regex("""```(?:latex|math)?\s*([\s\S]*?)\s*```"""), "$blockMarker$1$blockMarker")
            .replace("\\\\[", blockMarker)
            .replace("\\\\]", blockMarker)
            .replace("\\[", blockMarker)
            .replace("\\]", blockMarker)
            .replace("$$", blockMarker)

        val blockParts = normalizedBlocks.split(blockMarker)
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            blockParts.forEachIndexed { index, part ->
                val partTrimmed = part.trim()
                if (index % 2 == 1 && partTrimmed.isNotBlank()) {
                    MathFormulaCard(formula = partTrimmed, label = "Fórmula")
                } else if (partTrimmed.isNotBlank()) {
                    MarkdownInlineMathText(
                        text = partTrimmed,
                        style = style,
                        color = color,
                        lineHeightSp = lineHeightSp,
                        mathColor = mathColor
                    )
                }
            }
        }
    } else {
        MarkdownInlineMathText(
            text = text,
            modifier = modifier,
            style = style,
            color = color,
            lineHeightSp = lineHeightSp,
            mathColor = mathColor
        )
    }
}

private val INLINE_MATH_TOKEN_REGEX = Regex(
    """(\*\*[^*]+\*\*)|""" +
    """(\"[^"\n]*\\[a-zA-Z]+[^"\n]*\")|""" +
    """('[^'\n]*\\[a-zA-Z]+[^'\n]*')|""" +
    """(`[^`\n]*\\[a-zA-Z]+[^`\n]*`)|""" +
    """(\\\([\s\S]*?\\\))|""" +
    """(\$[^\$\n\r]+?\$)|""" +
    """(\\[a-zA-Z]+(?:\{[^{}]*\}|_[a-zA-Z0-9^{}]+|\^[a-zA-Z0-9^{}]+)+)"""
)

@Composable
private fun MarkdownInlineMathText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = TextPrimary,
    lineHeightSp: Float = 21f,
    mathColor: Color = Color(0xFF67E8F9)
) {
    val needsParsing = text.contains("**") || text.contains('\\') || text.contains('$') || 
                       text.contains('"') || text.contains('\'') || text.contains('`')

    if (!needsParsing) {
        Text(
            text = text,
            style = style.copy(lineHeight = lineHeightSp.sp, color = color),
            modifier = modifier
        )
        return
    }

    val annotated = remember(text, color, mathColor) {
        try {
            buildAnnotatedString {
                var lastIndex = 0

                for (match in INLINE_MATH_TOKEN_REGEX.findAll(text)) {
                    // Texto anterior ao match
                    if (match.range.first > lastIndex) {
                        val rawSlice = text.substring(lastIndex, match.range.first)
                        append(MathNotationHelper.formatInlineMath(rawSlice))
                    }

                    val matchedString = match.value
                    when {
                        // 1. Negrito do Markdown (**Texto**)
                        matchedString.startsWith("**") && matchedString.endsWith("**") && matchedString.length >= 4 -> {
                            val innerBold = matchedString.removePrefix("**").removeSuffix("**")
                            withStyle(
                                SpanStyle(
                                    fontWeight = FontWeight.Bold,
                                    color = TextPrimary
                                )
                            ) {
                                append(MathNotationHelper.formatInlineMath(innerBold))
                            }
                        }

                        // 2. Termo LaTeX entre aspas ("\frac{...}" ou '\frac{...}' ou `\frac{...}`)
                        (matchedString.startsWith("\"") && matchedString.endsWith("\"") && matchedString.length >= 2) ||
                        (matchedString.startsWith("'") && matchedString.endsWith("'") && matchedString.length >= 2) ||
                        (matchedString.startsWith("`") && matchedString.endsWith("`") && matchedString.length >= 2) -> {
                            val inner = matchedString.substring(1, matchedString.length - 1).trim()
                            val cleanInline = MathNotationHelper.formatInlineMath(inner)
                            withStyle(
                                SpanStyle(
                                    color = mathColor,
                                    fontFamily = FontFamily.Serif,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(cleanInline)
                            }
                        }

                        // 3. Notação Matemática Inline \( ... \) ou $ ... $
                        (matchedString.startsWith("\\(") && matchedString.endsWith("\\)")) ||
                        (matchedString.startsWith("$") && matchedString.endsWith("$")) -> {
                            val innerMath = if (matchedString.startsWith("\\(")) {
                                matchedString.removePrefix("\\(").removeSuffix("\\)")
                            } else {
                                matchedString.removePrefix("$").removeSuffix("$")
                            }
                            val cleanInline = MathNotationHelper.formatInlineMath(innerMath.trim())
                            withStyle(
                                SpanStyle(
                                    color = mathColor,
                                    fontFamily = FontFamily.Serif,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(cleanInline)
                            }
                        }

                        // 4. Comandos LaTeX explícitos (\frac{...}{...}, \lim_{...}, etc.)
                        matchedString.startsWith("\\") -> {
                            val cleanInline = MathNotationHelper.formatInlineMath(matchedString)
                            withStyle(
                                SpanStyle(
                                    color = mathColor,
                                    fontFamily = FontFamily.Serif,
                                    fontStyle = FontStyle.Italic,
                                    fontWeight = FontWeight.SemiBold
                                )
                            ) {
                                append(cleanInline)
                            }
                        }

                        else -> {
                            append(MathNotationHelper.formatInlineMath(matchedString))
                        }
                    }

                    lastIndex = match.range.last + 1
                }

                // Resto do texto após o último match
                if (lastIndex < text.length) {
                    val remaining = text.substring(lastIndex)
                    append(MathNotationHelper.formatInlineMath(remaining))
                }
            }
        } catch (_: Throwable) {
            androidx.compose.ui.text.AnnotatedString(text)
        }
    }

    Text(
        text = annotated,
        style = style.copy(lineHeight = lineHeightSp.sp, color = color),
        modifier = modifier
    )
}

/**
 * Renderizador didático completo para o conteúdo da aula de Cálculo (Teoria, Fórmulas e Intuição).
 * Interpreta títulos (#), subtítulos (###), listas numeradas (1., 2., 3.), marcadores com negrito (• **Título:**),
 * citações (>) e blocos de equações matemáticas, produzindo a mesma experiência visual do Gemini.
 */
@Composable
fun CalculusTheoryRenderer(
    markdown: String,
    modifier: Modifier = Modifier
) {
    val lines = markdown.split("\n")
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        var i = 0
        while (i < lines.size) {
            val line = lines[i]
            val trimmed = line.trim()

            when {
                trimmed.isBlank() -> {
                    i++
                }

                trimmed == "---" -> {
                    HorizontalDivider(
                        color = BorderSubtle.copy(alpha = 0.6f),
                        thickness = 1.dp,
                        modifier = Modifier.padding(vertical = 6.dp)
                    )
                    i++
                }

                // Título Principal (# Título)
                trimmed.startsWith("# ") -> {
                    val title = trimmed.removePrefix("# ").trim()
                    Surface(
                        color = SurfaceCard,
                        shape = RoundedCornerShape(8.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.4f)),
                        modifier = Modifier.fillMaxWidth().padding(top = 6.dp)
                    ) {
                        Row(
                            modifier = Modifier.padding(horizontal = 12.dp, vertical = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryViolet.copy(alpha = 0.25f)),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MenuBook,
                                    contentDescription = null,
                                    tint = PrimaryViolet,
                                    modifier = Modifier.size(16.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = title,
                                style = MaterialTheme.typography.titleMedium.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    fontSize = 15.sp
                                )
                            )
                        }
                    }
                    i++
                }

                // Subtítulo (### Subtítulo ou ## Subtítulo)
                trimmed.startsWith("### ") || trimmed.startsWith("## ") -> {
                    val subtitle = trimmed.removePrefix("### ").removePrefix("## ").trim()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .width(3.dp)
                                .height(16.dp)
                                .background(GoldXp, RoundedCornerShape(2.dp))
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = subtitle,
                            style = MaterialTheme.typography.titleSmall.copy(
                                fontWeight = FontWeight.Bold,
                                color = GoldXp,
                                fontSize = 13.5.sp
                            )
                        )
                    }
                    i++
                }

                // Destaque / Citação (> Ponto fundamental:)
                trimmed.startsWith("> ") -> {
                    val quote = trimmed.removePrefix("> ").trim()
                    Surface(
                        color = GoldXp.copy(alpha = 0.08f),
                        shape = RoundedCornerShape(6.dp),
                        border = androidx.compose.foundation.BorderStroke(1.dp, GoldXp.copy(alpha = 0.35f)),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Icon(
                                imageVector = Icons.Default.Lightbulb,
                                contentDescription = null,
                                tint = GoldXp,
                                modifier = Modifier.size(18.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            MathFormattedText(
                                text = quote,
                                style = MaterialTheme.typography.bodySmall.copy(
                                    color = TextPrimary,
                                    lineHeight = 18.sp
                                )
                            )
                        }
                    }
                    i++
                }

                // Imagem Markdown: ![Alt](url_ou_asset)
                trimmed.startsWith("![") && trimmed.contains("](") && trimmed.endsWith(")") -> {
                    val alt = trimmed.substringAfter("![").substringBefore("]")
                    val src = trimmed.substringAfter("](").substringBeforeLast(")")
                    DidacticImageView(
                        imagePath = src,
                        caption = alt.takeIf { it.isNotBlank() },
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                    i++
                }

                // Bloco de equação matemática com delimitadores ou código
                trimmed.startsWith("```") || trimmed.startsWith("\\[") || trimmed.startsWith("\\\\[") || trimmed.startsWith("$$") ||
                trimmed.startsWith("\\frac") || trimmed.startsWith("\"\\frac") || trimmed.startsWith("'\\frac") ||
                trimmed.startsWith("\\lim") || trimmed.startsWith("\"\\lim") ||
                trimmed.startsWith("\\int") || trimmed.startsWith("\"\\int") -> {
                    val codeBuilder = StringBuilder()
                    if (trimmed.startsWith("```")) {
                        i++
                        while (i < lines.size && !lines[i].trim().startsWith("```")) {
                            codeBuilder.appendLine(lines[i])
                            i++
                        }
                        if (i < lines.size) i++
                    } else if (trimmed.startsWith("\\[") || trimmed.startsWith("\\\\[")) {
                        val prefix = if (trimmed.startsWith("\\\\[")) "\\\\[" else "\\["
                        val suffix = if (prefix == "\\\\[") "\\\\]" else "\\]"
                        if (trimmed.endsWith(suffix) && trimmed.length > prefix.length) {
                            codeBuilder.append(trimmed.removePrefix(prefix).removeSuffix(suffix))
                            i++
                        } else {
                            codeBuilder.appendLine(trimmed.removePrefix(prefix))
                            i++
                            while (i < lines.size && !lines[i].trim().endsWith(suffix)) {
                                codeBuilder.appendLine(lines[i])
                                i++
                            }
                            if (i < lines.size) {
                                codeBuilder.append(lines[i].trim().removeSuffix(suffix))
                                i++
                            }
                        }
                    } else if (trimmed.startsWith("$$")) {
                        if (trimmed.endsWith("$$") && trimmed.length > 2) {
                            codeBuilder.append(trimmed.removePrefix("$$").removeSuffix("$$"))
                            i++
                        } else {
                            codeBuilder.appendLine(trimmed.removePrefix("$$"))
                            i++
                            while (i < lines.size && !lines[i].trim().endsWith("$$")) {
                                codeBuilder.appendLine(lines[i])
                                i++
                            }
                            if (i < lines.size) {
                                codeBuilder.append(lines[i].trim().removeSuffix("$$"))
                                i++
                            }
                        }
                    } else {
                        // Linha isolada com fórmula direta ou entre aspas
                        val unquoted = trimmed.removePrefix("\"").removeSuffix("\"").removePrefix("'").removeSuffix("'").trim()
                        codeBuilder.append(unquoted)
                        i++
                    }

                    MathFormulaCard(
                        formula = codeBuilder.toString().trim(),
                        label = "Equação Analítica"
                    )
                }

                // Lista numerada (1., 2., 3., etc. - Exemplo Continuidade no Gemini)
                trimmed.matches(Regex("""^\d+\.\s+.*""")) -> {
                    val match = Regex("""^(\d+)\.\s+(.*)""").find(trimmed)
                    val number = match?.groupValues?.get(1) ?: "1"
                    val content = match?.groupValues?.get(2) ?: trimmed

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Surface(
                            color = PrimaryViolet.copy(alpha = 0.2f),
                            shape = CircleShape,
                            modifier = Modifier.size(22.dp),
                            border = androidx.compose.foundation.BorderStroke(1.dp, PrimaryViolet.copy(alpha = 0.5f))
                        ) {
                            Box(contentAlignment = Alignment.Center) {
                                Text(
                                    text = number,
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        color = PrimaryViolet,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp
                                    )
                                )
                            }
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Box(modifier = Modifier.weight(1f)) {
                            MathFormattedText(
                                text = content,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextPrimary,
                                    lineHeight = 21.sp
                                )
                            )
                        }
                    }
                    i++
                }

                // Item de Lista com marcador (• ou - ou *)
                trimmed.startsWith("• ") || trimmed.startsWith("- ") || trimmed.startsWith("* ") -> {
                    val content = trimmed.substring(2).trim()
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 3.dp),
                        verticalAlignment = Alignment.Top
                    ) {
                        Text(
                            text = "•",
                            color = PrimaryViolet,
                            fontWeight = FontWeight.Bold,
                            fontSize = 14.sp,
                            modifier = Modifier.padding(end = 8.dp)
                        )
                        Box(modifier = Modifier.weight(1f)) {
                            MathFormattedText(
                                text = content,
                                style = MaterialTheme.typography.bodyMedium.copy(
                                    color = TextPrimary,
                                    lineHeight = 21.sp
                                )
                            )
                        }
                    }
                    i++
                }

                // Parágrafo Comum
                else -> {
                    MathFormattedText(
                        text = trimmed,
                        style = MaterialTheme.typography.bodyMedium.copy(
                            color = TextPrimary,
                            lineHeight = 22.sp
                        )
                    )
                    i++
                }
            }
        }
    }
}

/**
 * Componente estruturado para Solução Comentada Passo a Passo.
 */
@Composable
fun StepByStepSolutionView(
    solutionText: String,
    modifier: Modifier = Modifier
) {
    Surface(
        color = SurfaceElevated,
        shape = RoundedCornerShape(8.dp),
        border = androidx.compose.foundation.BorderStroke(1.dp, SuccessEmerald.copy(alpha = 0.4f)),
        modifier = modifier.fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(14.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .size(24.dp)
                        .clip(CircleShape)
                        .background(SuccessEmerald.copy(alpha = 0.2f)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.CheckCircle,
                        contentDescription = null,
                        tint = SuccessEmerald,
                        modifier = Modifier.size(15.dp)
                    )
                }
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Solução Comentada Passo a Passo",
                    style = MaterialTheme.typography.titleSmall.copy(
                        color = SuccessEmerald,
                        fontWeight = FontWeight.Bold
                    )
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            val rawLines = solutionText.split("\n").map { it.trim() }.filter { it.isNotBlank() }
            if (rawLines.size > 1) {
                var stepCounter = 1
                rawLines.forEach { line ->
                    val isDivider = line.all { it == '—' || it == '-' } && line.length >= 3
                    val isSuccessHeader = line.startsWith("✅") || line.contains("[CORRETA", ignoreCase = true)
                    val isErrorHeader = line.startsWith("❌") || line.contains("[INCORRETA", ignoreCase = true)
                    val isConceptHeader = line.startsWith("🧠") || line.startsWith("#") || line.startsWith("###")
                    val isTipHeader = line.startsWith("🎯")
                    val isNumberedStep = line.startsWith("Passo", ignoreCase = true) ||
                            line.startsWith("Etapa", ignoreCase = true)

                    if (isDivider) {
                        Spacer(modifier = Modifier.height(6.dp))
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(BorderSubtle.copy(alpha = 0.5f))
                        )
                        Spacer(modifier = Modifier.height(6.dp))
                    } else if (isSuccessHeader || isErrorHeader || isConceptHeader || isTipHeader) {
                        Spacer(modifier = Modifier.height(6.dp))
                        val calloutColor = when {
                            isSuccessHeader -> SuccessEmerald
                            isErrorHeader -> ErrorRose
                            isTipHeader -> GoldXp
                            else -> PrimaryViolet
                        }
                        val calloutIcon = when {
                            isSuccessHeader -> Icons.Default.CheckCircle
                            isErrorHeader -> Icons.Default.Cancel
                            isTipHeader -> Icons.Default.Lightbulb
                            else -> Icons.Default.Info
                        }
                        val cleanCalloutText = line
                            .removePrefix("✅").removePrefix("❌").removePrefix("🧠").removePrefix("🎯")
                            .removePrefix("### ").removePrefix("# ").trim()

                        Surface(
                            color = calloutColor.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(6.dp),
                            border = androidx.compose.foundation.BorderStroke(
                                1.dp,
                                calloutColor.copy(alpha = 0.35f)
                            ),
                            modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(10.dp),
                                verticalAlignment = Alignment.Top,
                                horizontalArrangement = Arrangement.spacedBy(8.dp)
                            ) {
                                Icon(
                                    imageVector = calloutIcon,
                                    contentDescription = null,
                                    tint = calloutColor,
                                    modifier = Modifier.size(16.dp).padding(top = 2.dp)
                                )
                                Column(modifier = Modifier.weight(1f)) {
                                    MathFormattedText(
                                        text = cleanCalloutText,
                                        style = MaterialTheme.typography.bodySmall.copy(fontWeight = FontWeight.SemiBold),
                                        color = calloutColor,
                                        lineHeightSp = 19f
                                    )
                                }
                            }
                        }
                    } else if (line.startsWith("•") || line.startsWith("-")) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 3.dp, horizontal = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Text(
                                text = "•",
                                color = PrimaryViolet,
                                fontSize = 14.sp,
                                modifier = Modifier.padding(end = 8.dp)
                            )
                            Column(modifier = Modifier.weight(1f)) {
                                MathFormattedText(
                                    text = line.removePrefix("•").removePrefix("-").trim(),
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextPrimary,
                                    lineHeightSp = 19f
                                )
                            }
                        }
                    } else if (isNumberedStep) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 4.dp),
                            verticalAlignment = Alignment.Top
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(20.dp)
                                    .clip(CircleShape)
                                    .background(PrimaryViolet)
                                    .border(1.dp, PrimaryViolet, CircleShape),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = "$stepCounter",
                                    style = MaterialTheme.typography.labelSmall.copy(
                                        fontSize = 10.sp,
                                        color = Color.White,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            stepCounter++
                            Spacer(modifier = Modifier.width(10.dp))
                            Column(modifier = Modifier.weight(1f)) {
                                MathFormattedText(
                                    text = line,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = TextPrimary,
                                    lineHeightSp = 19f
                                )
                            }
                        }
                    } else {
                        Box(modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp)) {
                            MathFormattedText(
                                text = line,
                                style = MaterialTheme.typography.bodySmall,
                                color = TextSecondary,
                                lineHeightSp = 19f
                            )
                        }
                    }
                }
            } else {
                MathFormattedText(
                    text = solutionText,
                    style = MaterialTheme.typography.bodySmall,
                    color = TextPrimary,
                    lineHeightSp = 19f
                )
            }
        }
    }
}
