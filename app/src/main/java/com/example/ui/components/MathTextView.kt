package com.example.ui.components

import android.annotation.SuppressLint
import android.content.Context
import android.os.Handler
import android.os.Looper
import android.webkit.JavascriptInterface
import android.webkit.WebView
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Functions
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.MenuBook
import androidx.compose.material.icons.filled.SquareFoot
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.ui.theme.BorderSubtle
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
        var s = raw.trim()

        // Remove marcadores de bloco de código markdown
        s = s.replace(Regex("""```(?:latex|math)?\s*([\s\S]*?)\s*```"""), "$1").trim()
        s = s.removePrefix("\\[").removeSuffix("\\]").trim()
        s = s.removePrefix("$$").removeSuffix("$$").trim()

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
        if (!s.contains("\\frac") && s.contains(" / ")) {
            val parts = s.split(" / ", limit = 2)
            if (parts.size == 2) {
                val num = parts[0].trim().removePrefix("(").removeSuffix(")")
                val rest = parts[1].trim()
                if (rest.contains(" = ")) {
                    val denAndEquals = rest.split(" = ", limit = 2)
                    val den = denAndEquals[0].trim().removePrefix("(").removeSuffix(")")
                    s = "\\frac{$num}{$den} = ${denAndEquals[1]}"
                } else {
                    val den = rest.removePrefix("(").removeSuffix(")")
                    s = "\\frac{$num}{$den}"
                }
            }
        }

        return s
    }

    private val SUPERSCRIPT_MAP = mapOf(
        '0' to '⁰', '1' to '¹', '2' to '²', '3' to '³', '4' to '⁴',
        '5' to '⁵', '6' to '⁶', '7' to '⁷', '8' to '⁸', '9' to '⁹',
        '+' to '⁺', '-' to '⁻', '=' to '⁼', '(' to '⁽', ')' to '⁾',
        'n' to 'ⁿ', 'i' to 'ⁱ', 'x' to 'ˣ', 'y' to 'ʸ', 'k' to 'ᵏ',
        't' to 'ᵗ', 'm' to 'ᵐ', 'a' to 'ᵃ', 'b' to 'ᵇ', 'c' to 'ᶜ'
    )

    private val SUBSCRIPT_MAP = mapOf(
        '0' to '₀', '1' to '₁', '2' to '₂', '3' to '₃', '4' to '₄',
        '5' to '₅', '6' to '₆', '7' to '₇', '8' to '₈', '9' to '₉',
        '+' to '₊', '-' to '₋', '=' to '₌', '(' to '₍', ')' to '₎',
        'a' to 'ₐ', 'e' to 'ₑ', 'o' to 'ₒ', 'x' to 'ₓ', 'i' to 'ᵢ',
        'j' to 'ⱼ', 'n' to 'ₙ', 'm' to 'ₘ', 't' to 'ₜ', 'k' to 'ₖ', 'p' to 'ₚ'
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
                return "_$str"
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
            s = s.substring(0, startIdx) + replacement + s.substring(denEnd + 1)
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

    /**
     * Limpeza e enriquecimento de expressões matemáticas para exibição inline autêntica.
     */
    fun formatInlineMath(raw: String): String {
        var s = raw

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

        return s.trim()
    }
}

/**
 * Renderizador de fórmulas matemáticas KaTeX de alta fidelidade usando WebView local (offline).
 * Produz limites com subscrito alinhado diretamente abaixo do termo `lim`, frações verticais com barra
 * horizontal, parênteses escaláveis e tipografia matemática com serifa itálica idêntica ao Gemini.
 */
@SuppressLint("SetJavaScriptEnabled")
@Composable
fun KaTeXFormulaView(
    formula: String,
    modifier: Modifier = Modifier,
    isDisplayMode: Boolean = true,
    fontSizeEm: Float = 1.25f,
    accentColorHex: String = "#67E8F9"
) {
    val context = LocalContext.current
    val density = LocalDensity.current

    val normalizedFormula = remember(formula) {
        MathNotationHelper.toStandardLatex(formula)
    }

    var contentHeightDp by remember(normalizedFormula) {
        mutableStateOf(if (isDisplayMode) 64.dp else 42.dp)
    }

    val html = remember(normalizedFormula, isDisplayMode, fontSizeEm, accentColorHex) {
        buildKaTeXHtml(normalizedFormula, isDisplayMode, fontSizeEm, accentColorHex)
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(contentHeightDp),
        contentAlignment = Alignment.Center
    ) {
        AndroidView(
            factory = { ctx ->
                WebView(ctx).apply {
                    setBackgroundColor(0) // 100% transparente
                    isVerticalScrollBarEnabled = false
                    isHorizontalScrollBarEnabled = false
                    settings.javaScriptEnabled = true
                    settings.allowFileAccess = true
                    settings.domStorageEnabled = true
                    settings.loadWithOverviewMode = true
                    settings.useWideViewPort = false

                    addJavascriptInterface(object {
                        @JavascriptInterface
                        fun onHeight(heightPx: Int) {
                            if (heightPx > 0) {
                                Handler(Looper.getMainLooper()).post {
                                    val dpVal = (heightPx / ctx.resources.displayMetrics.density).dp
                                    contentHeightDp = maxOf(48.dp, dpVal + 12.dp)
                                }
                            }
                        }
                    }, "AndroidInterface")

                    loadDataWithBaseURL(
                        "file:///android_asset/katex/",
                        html,
                        "text/html",
                        "UTF-8",
                        null
                    )
                }
            },
            update = { webView ->
                webView.loadDataWithBaseURL(
                    "file:///android_asset/katex/",
                    html,
                    "text/html",
                    "UTF-8",
                    null
                )
            },
            modifier = Modifier.fillMaxWidth()
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
            padding: 4px 8px;
            background-color: transparent;
            color: #F1F5F9;
            font-family: -apple-system, BlinkMacSystemFont, "Segoe UI", Roboto, "Helvetica Neue", Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            overflow-x: auto;
            overflow-y: hidden;
            text-align: center;
          }
          #katex-target {
            display: inline-block;
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
          <div id="katex-target"></div>
          <script>
            function renderMath() {
              try {
                var el = document.getElementById('katex-target');
                var raw = $escapedFormula;
                katex.render(raw, el, {
                  displayMode: $isDisplayMode,
                  throwOnError: false
                });
              } catch(e) {
                document.getElementById('katex-target').innerText = $escapedFormula;
              }
              setTimeout(function() {
                if (window.AndroidInterface) {
                  var h = document.getElementById('katex-target').offsetHeight || document.body.scrollHeight;
                  window.AndroidInterface.onHeight(h);
                }
              }, 40);
            }
            document.addEventListener("DOMContentLoaded", renderMath);
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
@Composable
fun MathFormattedText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = TextPrimary,
    lineHeightSp: Float = 21f
) {
    val mathColor = Color(0xFF67E8F9)

    // Se o texto contiver blocos em destaque delimitados por \[ ... \] ou $$ ... $$,
    // quebramos para exibir os blocos via KaTeXFormulaView
    val blockMarker = "@@MATH_BLOCK@@"
    val normalizedBlocks = text
        .replace(Regex("""```(?:latex|math)?\s*([\s\S]*?)\s*```"""), "$blockMarker$1$blockMarker")
        .replace("\\[", blockMarker)
        .replace("\\]", blockMarker)
        .replace("$$", blockMarker)

    if (normalizedBlocks.contains(blockMarker)) {
        val parts = normalizedBlocks.split(blockMarker)
        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(8.dp)) {
            parts.forEachIndexed { index, part ->
                val trimmed = part.trim()
                if (index % 2 == 1 && trimmed.isNotBlank()) {
                    MathFormulaCard(formula = trimmed, label = "Fórmula")
                } else if (trimmed.isNotBlank()) {
                    MarkdownInlineMathText(
                        text = trimmed,
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

@Composable
private fun MarkdownInlineMathText(
    text: String,
    modifier: Modifier = Modifier,
    style: TextStyle = MaterialTheme.typography.bodyMedium,
    color: Color = TextPrimary,
    lineHeightSp: Float = 21f,
    mathColor: Color = Color(0xFF67E8F9)
) {
    val annotated = remember(text, color, mathColor) {
        buildAnnotatedString {
            // Tokenização mista: Markdown Bold (**...**), Math inline (\(...\) ou $...$)
            // Regex detecta:
            // 1) **negrito**
            // 2) \( math \) ou $ math $
            val tokenRegex = Regex("""(\*\*[^*]+\*\*)|(\\\([\s\S]*?\\\))|(\$[^\$]+\$)""")
            var lastIndex = 0

            for (match in tokenRegex.findAll(text)) {
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

                    // 2. Notação Matemática Inline \( ... \) ou $ ... $
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
                            append(" $cleanInline ")
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

                // Bloco de equação matemática com delimitadores ou código
                trimmed.startsWith("```") || trimmed.startsWith("\\[") || trimmed.startsWith("$$") -> {
                    val codeBuilder = StringBuilder()
                    if (trimmed.startsWith("```")) {
                        i++
                        while (i < lines.size && !lines[i].trim().startsWith("```")) {
                            codeBuilder.appendLine(lines[i])
                            i++
                        }
                        if (i < lines.size) i++
                    } else if (trimmed.startsWith("\\[") && trimmed.endsWith("\\]")) {
                        codeBuilder.append(trimmed.removePrefix("\\[").removeSuffix("\\]"))
                        i++
                    } else if (trimmed.startsWith("$$") && trimmed.endsWith("$$") && trimmed.length > 2) {
                        codeBuilder.append(trimmed.removePrefix("$$").removeSuffix("$$"))
                        i++
                    } else {
                        codeBuilder.append(trimmed)
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

            val lines = solutionText.split("\n").filter { it.isNotBlank() }
            if (lines.size > 1) {
                lines.forEachIndexed { index, line ->
                    val isStepHeader = line.startsWith("Passo", ignoreCase = true) ||
                            line.startsWith("Etapa", ignoreCase = true) ||
                            line.startsWith("Conclusão", ignoreCase = true) ||
                            line.startsWith("Resultado", ignoreCase = true)

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
                                .background(if (isStepHeader) PrimaryViolet else SurfaceElevated)
                                .border(1.dp, if (isStepHeader) PrimaryViolet else BorderSubtle, CircleShape),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "${index + 1}",
                                style = MaterialTheme.typography.labelSmall.copy(
                                    fontSize = 10.sp,
                                    color = if (isStepHeader) Color.White else TextSecondary,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            MathFormattedText(
                                text = line,
                                style = MaterialTheme.typography.bodySmall,
                                color = if (isStepHeader) TextPrimary else TextSecondary,
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
