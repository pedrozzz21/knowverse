package com.example.ui.components

import kotlin.math.*

/**
 * Tipo de Soma de Riemann para visualização e cálculo pedagógico.
 */
enum class RiemannType(val label: String) {
    LEFT("Soma à Esquerda"),
    RIGHT("Soma à Direita"),
    MIDPOINT("Ponto Médio"),
    TRAPEZOIDAL("Regra dos Trapézios")
}

/**
 * Ponto Notável detectado na curva da função.
 */
data class MathCriticalPoint(
    val x: Double,
    val y: Double,
    val type: CriticalPointType,
    val label: String
)

enum class CriticalPointType {
    ROOT,               // f(x) = 0
    Y_INTERCEPT,        // (0, f(0))
    LOCAL_MAXIMUM,      // Máximo Local
    LOCAL_MINIMUM,      // Mínimo Local
    INFLECTION_POINT,   // Ponto de Inflexão (f''(x) = 0)
    REMOVABLE_HOLE,     // Descontinuidade Removível (0/0)
    VERTICAL_ASYMPTOTE  // Assíntota Vertical (Pólo)
}

/**
 * Resultado da validação de uma expressão matemática.
 */
sealed class ExpressionValidationResult {
    data object Valid : ExpressionValidationResult()
    data class Invalid(val errorMessage: String, val position: Int = -1) : ExpressionValidationResult()
}

/**
 * Motor Matemático Analítico e Numérico Autônomo para o Knowverse.
 * Executa 100% nativamente em Kotlin (offline), sem qualquer dependência de WebView ou bibliotecas externas.
 *
 * Suporta:
 * - Aritmética completa (+, -, *, /, ^, %)
 * - Funções algébricas, racionais e modulares: abs, sqrt, cbrt, sign, step
 * - Funções transcendentes: exp, ln, log (base 10), log2, log10
 * - Trigonometria direta e inversa: sin, cos, tan, sec, csc, cot, asin, acos, atan
 * - Funções hiperbólicas: sinh, cosh, tanh
 * - Funções por partes (Piecewise / Condicional): if(cond, vTrue, vFalse)
 * - Constantes fundamentais: pi (π), e, tau (τ = 2π)
 * - Multiplicação implícita universal via análise de tokens: 2x, 5(x+1), x(x-1), 2pi*x, x*sin(x), 3|x|
 * - Sintaxe de módulo com barras verticais: |x - 2| -> abs(x - 2)
 * - Cálculo numérico de alta precisão: Derivada de 5 pontos, 2ª Derivada, Integral de Simpson 1/3, Somas de Riemann
 * - Localização automática de raízes, extremos e pontos de inflexão.
 */
object MathExpressionEvaluator {

    private const val EPSILON = 1e-12

    private val KNOWN_FUNCTIONS = setOf(
        "sin", "cos", "tan", "sec", "csc", "cot",
        "asin", "acos", "atan",
        "sinh", "cosh", "tanh",
        "sqrt", "cbrt", "abs",
        "ln", "log", "log10", "log2",
        "exp", "if", "sign", "step"
    )

    private val CONSTANTS = setOf("pi", "e", "tau")

    /**
     * Valida sintaticamente uma expressão sem depender de um x específico.
     */
    fun validateExpression(expression: String): ExpressionValidationResult {
        val s = expression.trim()
        if (s.isEmpty()) {
            return ExpressionValidationResult.Invalid("A expressão não pode estar vazia.")
        }
        return try {
            val tokens = tokenize(s)
            if (tokens.isEmpty()) {
                return ExpressionValidationResult.Invalid("Nenhum termo matemático reconhecido.")
            }
            val parser = ExprParser(tokens, 1.0)
            parser.parseExpression()
            if (parser.hasRemainingTokens()) {
                ExpressionValidationResult.Invalid("Tokens residuais não reconhecidos no final da expressão.")
            } else {
                ExpressionValidationResult.Valid
            }
        } catch (e: Exception) {
            ExpressionValidationResult.Invalid("Erro ao analisar fórmula: ${e.message ?: "sintaxe inválida"}")
        }
    }

    /**
     * Avalia f(x) para um dado valor de x. Retorna Double.NaN se x estiver fora do domínio.
     */
    fun evaluate(expression: String, x: Double): Double {
        return try {
            val tokens = tokenize(expression)
            val parser = ExprParser(tokens, x)
            val result = parser.parseExpression()
            if (parser.hasRemainingTokens() || result.isInfinite() || result.isNaN()) Double.NaN else result
        } catch (_: Throwable) {
            Double.NaN
        }
    }

    /**
     * Derivada primeira f'(x) calculada via stencil centrado de 5 pontos O(h^4).
     */
    fun derivative(expression: String, x: Double, h: Double = 1e-4): Double {
        val f_m2 = evaluate(expression, x - 2 * h)
        val f_m1 = evaluate(expression, x - h)
        val f_p1 = evaluate(expression, x + h)
        val f_p2 = evaluate(expression, x + 2 * h)

        if (!f_m2.isNaN() && !f_m1.isNaN() && !f_p1.isNaN() && !f_p2.isNaN()) {
            val num = -f_p2 + 8.0 * f_p1 - 8.0 * f_m1 + f_m2
            val den = 12.0 * h
            val res = num / den
            return if (res.isNaN() || res.isInfinite()) Double.NaN else res
        }

        // Fallback para diferença central padrão O(h^2)
        val yRight = evaluate(expression, x + h)
        val yLeft = evaluate(expression, x - h)
        if (!yRight.isNaN() && !yLeft.isNaN()) {
            val slope = (yRight - yLeft) / (2.0 * h)
            return if (slope.isNaN() || slope.isInfinite()) Double.NaN else slope
        }
        return Double.NaN
    }

    /**
     * Derivada segunda f''(x) para análise de concavidade e pontos de inflexão O(h^4).
     */
    fun secondDerivative(expression: String, x: Double, h: Double = 1e-3): Double {
        val f_m2 = evaluate(expression, x - 2 * h)
        val f_m1 = evaluate(expression, x - h)
        val f_0  = evaluate(expression, x)
        val f_p1 = evaluate(expression, x + h)
        val f_p2 = evaluate(expression, x + 2 * h)

        if (!f_m2.isNaN() && !f_m1.isNaN() && !f_0.isNaN() && !f_p1.isNaN() && !f_p2.isNaN()) {
            val num = -f_p2 + 16.0 * f_p1 - 30.0 * f_0 + 16.0 * f_m1 - f_m2
            val den = 12.0 * h * h
            val res = num / den
            return if (res.isNaN() || res.isInfinite()) Double.NaN else res
        }
        return Double.NaN
    }

    /**
     * Integral Definida ∫[a, b] f(x) dx calculada pela Regra de Simpson Composta (1/3).
     */
    fun definiteIntegral(expression: String, a: Double, b: Double, intervals: Int = 120): Double {
        if (a == b) return 0.0
        val n = if (intervals % 2 != 0) intervals + 1 else intervals
        val h = (b - a) / n

        val fa = evaluate(expression, a)
        val fb = evaluate(expression, b)
        if (fa.isNaN() || fb.isNaN()) return Double.NaN

        var sumOdd = 0.0
        var sumEven = 0.0

        for (i in 1 until n) {
            val x = a + i * h
            val fx = evaluate(expression, x)
            if (fx.isNaN()) return Double.NaN
            if (i % 2 == 0) sumEven += fx else sumOdd += fx
        }

        val integral = (h / 3.0) * (fa + 4.0 * sumOdd + 2.0 * sumEven + fb)
        return if (integral.isNaN() || integral.isInfinite()) Double.NaN else integral
    }

    /**
     * Cálculo de Somas de Riemann para partições didáticas da integral.
     */
    fun calculateRiemannSum(
        expression: String,
        a: Double,
        b: Double,
        n: Int,
        type: RiemannType
    ): Double {
        if (n <= 0 || a == b) return 0.0
        val dx = (b - a) / n
        var sum = 0.0

        for (i in 0 until n) {
            val xi = a + i * dx
            val xiNext = xi + dx
            val sampleX = when (type) {
                RiemannType.LEFT -> xi
                RiemannType.RIGHT -> xiNext
                RiemannType.MIDPOINT -> (xi + xiNext) / 2.0
                RiemannType.TRAPEZOIDAL -> {
                    val y1 = evaluate(expression, xi)
                    val y2 = evaluate(expression, xiNext)
                    if (y1.isNaN() || y2.isNaN()) return Double.NaN
                    sum += (y1 + y2) * 0.5 * dx
                    continue
                }
            }
            val y = evaluate(expression, sampleX)
            if (y.isNaN()) return Double.NaN
            sum += y * dx
        }
        return sum
    }

    /**
     * Localiza raízes f(x) = 0 no intervalo com refinamento de bissecção.
     */
    fun findRoots(expression: String, xMin: Double, xMax: Double, steps: Int = 200): List<Double> {
        val roots = mutableListOf<Double>()
        val dx = (xMax - xMin) / steps
        var prevX = xMin
        var prevY = evaluate(expression, prevX)

        for (i in 1..steps) {
            val curX = xMin + i * dx
            val curY = evaluate(expression, curX)

            if (!prevY.isNaN() && !curY.isNaN()) {
                if (abs(prevY) < EPSILON) {
                    if (roots.none { abs(it - prevX) < 1e-4 }) roots.add(prevX)
                } else if (prevY * curY < 0 && abs(prevY - curY) < (xMax - xMin) * 20) {
                    // Refinamento de Bissecção
                    var left = prevX
                    var right = curX
                    for (b in 0..24) {
                        val mid = (left + right) / 2.0
                        val yMid = evaluate(expression, mid)
                        if (abs(yMid) < 1e-8) {
                            left = mid
                            right = mid
                            break
                        }
                        if (evaluate(expression, left) * yMid < 0) {
                            right = mid
                        } else {
                            left = mid
                        }
                    }
                    val found = (left + right) / 2.0
                    if (roots.none { abs(it - found) < 1e-3 }) {
                        roots.add(found)
                    }
                }
            }
            prevX = curX
            prevY = curY
        }
        return roots.sorted()
    }

    /**
     * Localiza extremos relativos (máximos e mínimos locais) no intervalo.
     */
    fun findExtrema(expression: String, xMin: Double, xMax: Double, steps: Int = 250): List<MathCriticalPoint> {
        val extrema = mutableListOf<MathCriticalPoint>()
        val dx = (xMax - xMin) / steps
        var prevX = xMin
        var prevD = derivative(expression, prevX)

        for (i in 1..steps) {
            val curX = xMin + i * dx
            val curD = derivative(expression, curX)

            if (!prevD.isNaN() && !curD.isNaN() && prevD * curD < 0) {
                // Refinamento de bissecção na derivada para encontrar x exato onde f'(x) = 0
                var left = prevX
                var right = curX
                for (b in 0..16) {
                    val mid = (left + right) / 2.0
                    val dMid = derivative(expression, mid)
                    if (abs(dMid) < 1e-7) {
                        left = mid
                        right = mid
                        break
                    }
                    if (derivative(expression, left) * dMid < 0) {
                        right = mid
                    } else {
                        left = mid
                    }
                }
                val midX = (left + right) / 2.0
                val yVal = evaluate(expression, midX)
                if (!yVal.isNaN()) {
                    val d2 = secondDerivative(expression, midX)
                    val type = when {
                        d2 < -1e-6 -> CriticalPointType.LOCAL_MAXIMUM
                        d2 > 1e-6 -> CriticalPointType.LOCAL_MINIMUM
                        else -> if (prevD > 0) CriticalPointType.LOCAL_MAXIMUM else CriticalPointType.LOCAL_MINIMUM
                    }
                    val label = if (type == CriticalPointType.LOCAL_MAXIMUM) "Máx Local" else "Mín Local"
                    if (extrema.none { abs(it.x - midX) < 1e-2 }) {
                        extrema.add(MathCriticalPoint(midX, yVal, type, label))
                    }
                }
            }
            prevX = curX
            prevD = curD
        }
        return extrema
    }

    // ---------------------------------------------------------------------------------------------
    // TOKENIZADOR ROBUSTO COM MULTIPLICAÇÃO IMPLÍCITA
    // ---------------------------------------------------------------------------------------------

    private fun tokenize(rawExpr: String): List<String> {
        var s = rawExpr.trim()
            .replace(" ", "")
            .replace("f(x)=", "")
            .replace("y=", "")
            .replace("sen", "sin")
            .replace("tg", "tan")
            .replace("PI", "pi")
            .replace("π", "pi")
            .replace("TAU", "tau")
            .replace("τ", "tau")
            .replace("E", "e")
            .replace("cotg", "cot")
            .replace("cosec", "csc")

        // Converte barras verticais de módulo |expr| em abs(expr)
        s = convertBarsToAbs(s)

        // 1. Passada de tokens brutos
        val rawTokens = mutableListOf<String>()
        var i = 0
        while (i < s.length) {
            val c = s[i]
            when {
                c.isDigit() || c == '.' -> {
                    val sb = StringBuilder()
                    while (i < s.length && (s[i].isDigit() || s[i] == '.')) {
                        sb.append(s[i])
                        i++
                    }
                    rawTokens.add(sb.toString())
                }
                s.startsWith("log10", i, ignoreCase = true) -> {
                    rawTokens.add("log10")
                    i += 5
                }
                s.startsWith("log2", i, ignoreCase = true) -> {
                    rawTokens.add("log2")
                    i += 4
                }
                c.isLetter() -> {
                    val sb = StringBuilder()
                    while (i < s.length && s[i].isLetter()) {
                        sb.append(s[i])
                        i++
                    }
                    rawTokens.add(sb.toString().lowercase())
                }
                c in "+-*/^(),<>!=%?:" -> {
                    if (i < s.length - 1) {
                        val next = s[i + 1]
                        val two = "$c$next"
                        if (two in listOf("<=", ">=", "==", "!=", "||", "&&")) {
                            rawTokens.add(two)
                            i += 2
                            continue
                        }
                    }
                    rawTokens.add(c.toString())
                    i++
                }
                else -> i++
            }
        }

        // 2. Inserir multiplicação implícita entre tokens
        val tokensWithMul = mutableListOf<String>()
        for (idx in rawTokens.indices) {
            val token = rawTokens[idx]
            tokensWithMul.add(token)

            if (idx < rawTokens.size - 1) {
                val nextToken = rawTokens[idx + 1]

                // Pode ser o fim de um operando?
                val isOperandEnd = isNumberToken(token) || token == "x" || token in CONSTANTS || token == ")"

                // Pode ser o início de um operando?
                val isOperandStart = isNumberToken(nextToken) || nextToken == "x" || nextToken in CONSTANTS ||
                        nextToken in KNOWN_FUNCTIONS || nextToken == "("

                // Se token anterior termina operando e próximo inicia operando, insere '*'
                if (isOperandEnd && isOperandStart) {
                    tokensWithMul.add("*")
                }
            }
        }

        return tokensWithMul
    }

    private fun isNumberToken(token: String): Boolean {
        return token.toDoubleOrNull() != null
    }

    private fun convertBarsToAbs(input: String): String {
        if (!input.contains('|')) return input
        val sb = StringBuilder()
        var insideAbs = false
        var depth = 0
        for (i in input.indices) {
            val c = input[i]
            if (c == '|') {
                if (!insideAbs) {
                    sb.append("abs(")
                    insideAbs = true
                    depth++
                } else {
                    sb.append(")")
                    insideAbs = false
                    depth--
                }
            } else {
                sb.append(c)
            }
        }
        while (depth > 0) {
            sb.append(")")
            depth--
        }
        return sb.toString()
    }

    // ---------------------------------------------------------------------------------------------
    // PARSER DESCENDENTE RECURSIVO (RECURSIVE DESCENT PARSER)
    // ---------------------------------------------------------------------------------------------

    private class ExprParser(private val tokens: List<String>, private val xValue: Double) {
        private var pos = 0

        fun hasRemainingTokens(): Boolean = pos < tokens.size

        fun parseExpression(): Double = parseConditional()

        private fun parseConditional(): Double {
            val value = parseLogicalOr()
            if (pos < tokens.size && tokens[pos] == "?") {
                pos++ // consome '?'
                val trueExpr = parseExpression()
                if (pos < tokens.size && tokens[pos] == ":") {
                    pos++ // consome ':'
                    val falseExpr = parseExpression()
                    return if (abs(value) > EPSILON) trueExpr else falseExpr
                } else {
                    throw IllegalArgumentException("Operador ternário malformado: ':' esperado")
                }
            }
            return value
        }

        private fun parseLogicalOr(): Double {
            var value = parseLogicalAnd()
            while (pos < tokens.size && tokens[pos] == "||") {
                pos++
                val next = parseLogicalAnd()
                value = if (abs(value) > EPSILON || abs(next) > EPSILON) 1.0 else 0.0
            }
            return value
        }

        private fun parseLogicalAnd(): Double {
            var value = parseComparison()
            while (pos < tokens.size && tokens[pos] == "&&") {
                pos++
                val next = parseComparison()
                value = if (abs(value) > EPSILON && abs(next) > EPSILON) 1.0 else 0.0
            }
            return value
        }

        private fun parseComparison(): Double {
            var value = parseAddSub()
            while (pos < tokens.size) {
                val op = tokens[pos]
                if (op in listOf("<", "<=", ">", ">=", "==", "!=")) {
                    pos++
                    val next = parseAddSub()
                    value = when (op) {
                        "<" -> if (value < next) 1.0 else 0.0
                        "<=" -> if (value <= next + EPSILON) 1.0 else 0.0
                        ">" -> if (value > next) 1.0 else 0.0
                        ">=" -> if (value >= next - EPSILON) 1.0 else 0.0
                        "==" -> if (abs(value - next) < EPSILON) 1.0 else 0.0
                        "!=" -> if (abs(value - next) >= EPSILON) 1.0 else 0.0
                        else -> 0.0
                    }
                } else {
                    break
                }
            }
            return value
        }

        private fun parseAddSub(): Double {
            var value = parseMulDiv()
            while (pos < tokens.size) {
                val op = tokens[pos]
                if (op == "+" || op == "-") {
                    pos++
                    val next = parseMulDiv()
                    value = if (op == "+") value + next else value - next
                } else {
                    break
                }
            }
            return value
        }

        private fun parseMulDiv(): Double {
            var value = parsePower()
            while (pos < tokens.size) {
                val op = tokens[pos]
                if (op == "*" || op == "/" || op == "%") {
                    pos++
                    val next = parsePower()
                    value = when (op) {
                        "*" -> value * next
                        "/" -> if (abs(next) < EPSILON) Double.NaN else value / next
                        "%" -> if (abs(next) < EPSILON) Double.NaN else value % next
                        else -> value
                    }
                } else {
                    break
                }
            }
            return value
        }

        private fun parsePower(): Double {
            val base = parseFactor()
            if (pos < tokens.size && tokens[pos] == "^") {
                pos++
                val exponent = parsePower() // Associatividade à direita
                if (base < 0 && abs(exponent - round(exponent)) > EPSILON) {
                    val denomApprox = round(1.0 / exponent)
                    if (abs(1.0 / denomApprox - exponent) < 1e-4 && denomApprox.toInt() % 2 != 0) {
                        return -(-base).pow(exponent)
                    }
                    return Double.NaN
                }
                return base.pow(exponent)
            }
            return base
        }

        private fun parseFactor(): Double {
            if (pos >= tokens.size) return Double.NaN

            val token = tokens[pos]

            // Operador unário positivo/negativo
            if (token == "-") {
                pos++
                return -parseFactor()
            }
            if (token == "+") {
                pos++
                return parseFactor()
            }

            // Parênteses
            if (token == "(") {
                pos++
                val value = parseExpression()
                if (pos < tokens.size && tokens[pos] == ")") {
                    pos++
                } else {
                    throw IllegalArgumentException("Parêntese de fechamento ')' ausente")
                }
                return value
            }

            // Variável independente x
            if (token == "x") {
                pos++
                return xValue
            }

            // Constantes numéricas matemáticas
            if (token == "pi") {
                pos++
                return PI
            }
            if (token == "e") {
                pos++
                return E
            }
            if (token == "tau") {
                pos++
                return 2.0 * PI
            }

            // Função Condicional if(cond, vTrue, vFalse)
            if (token == "if") {
                pos++
                if (pos < tokens.size && tokens[pos] == "(") {
                    pos++
                    val cond = parseExpression()
                    if (pos < tokens.size && tokens[pos] == ",") pos++ else throw IllegalArgumentException("Vírgula esperada no if")
                    val vTrue = parseExpression()
                    if (pos < tokens.size && tokens[pos] == ",") pos++ else throw IllegalArgumentException("Vírgula esperada no if")
                    val vFalse = parseExpression()
                    if (pos < tokens.size && tokens[pos] == ")") pos++ else throw IllegalArgumentException("Parêntese de fechamento ')' ausente no if")
                    return if (abs(cond) > EPSILON) vTrue else vFalse
                }
                throw IllegalArgumentException("Parêntese '(' esperado após 'if'")
            }

            // Funções Matemáticas analíticas
            if (token in KNOWN_FUNCTIONS) {
                pos++
                val arg = parseFactor()
                if (arg.isNaN()) return Double.NaN
                return when (token) {
                    "sin" -> sin(arg)
                    "cos" -> cos(arg)
                    "tan" -> if (abs(cos(arg)) < EPSILON) Double.NaN else tan(arg)
                    "sec" -> if (abs(cos(arg)) < EPSILON) Double.NaN else 1.0 / cos(arg)
                    "csc" -> if (abs(sin(arg)) < EPSILON) Double.NaN else 1.0 / sin(arg)
                    "cot" -> if (abs(sin(arg)) < EPSILON) Double.NaN else cos(arg) / sin(arg)
                    "asin" -> if (arg in -1.0..1.0) asin(arg) else Double.NaN
                    "acos" -> if (arg in -1.0..1.0) acos(arg) else Double.NaN
                    "atan" -> atan(arg)
                    "sinh" -> sinh(arg)
                    "cosh" -> cosh(arg)
                    "tanh" -> tanh(arg)
                    "sqrt" -> if (arg < 0.0) Double.NaN else sqrt(arg)
                    "cbrt" -> if (arg < 0.0) -(-arg).pow(1.0 / 3.0) else arg.pow(1.0 / 3.0)
                    "abs" -> abs(arg)
                    "ln" -> if (arg <= 0.0) Double.NaN else ln(arg)
                    "log", "log10" -> if (arg <= 0.0) Double.NaN else log10(arg)
                    "log2" -> if (arg <= 0.0) Double.NaN else ln(arg) / ln(2.0)
                    "exp" -> exp(arg)
                    "sign" -> when {
                        arg > EPSILON -> 1.0
                        arg < -EPSILON -> -1.0
                        else -> 0.0
                    }
                    "step" -> if (arg >= 0.0) 1.0 else 0.0
                    else -> Double.NaN
                }
            }

            // Número literal
            val num = token.toDoubleOrNull()
            if (num != null) {
                pos++
                return num
            }

            pos++
            return Double.NaN
        }
    }
}
