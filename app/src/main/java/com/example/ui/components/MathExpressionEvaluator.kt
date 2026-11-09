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
 * Nós da Árvore Sintática Abstrata (AST) para avaliação compilada de alta velocidade (Zero-Allocation).
 */
sealed interface MathAstNode {
    fun eval(x: Double): Double

    data class NumberLiteral(val value: Double) : MathAstNode {
        override fun eval(x: Double): Double = value
    }

    data object VariableX : MathAstNode {
        override fun eval(x: Double): Double = x
    }

    data class UnaryMinus(val operand: MathAstNode) : MathAstNode {
        override fun eval(x: Double): Double = -operand.eval(x)
    }

    data class BinaryOp(
        val op: BinaryOperator,
        val left: MathAstNode,
        val right: MathAstNode
    ) : MathAstNode {
        override fun eval(x: Double): Double {
            val l = left.eval(x)
            if (l.isNaN()) return Double.NaN
            // Curto-circuito para operadores lógicos
            if (op == BinaryOperator.OR) {
                if (abs(l) > 1e-12) return 1.0
                val r = right.eval(x)
                return if (!r.isNaN() && abs(r) > 1e-12) 1.0 else 0.0
            }
            if (op == BinaryOperator.AND) {
                if (abs(l) <= 1e-12) return 0.0
                val r = right.eval(x)
                return if (!r.isNaN() && abs(r) > 1e-12) 1.0 else 0.0
            }

            val r = right.eval(x)
            if (r.isNaN()) return Double.NaN

            return when (op) {
                BinaryOperator.ADD -> l + r
                BinaryOperator.SUB -> l - r
                BinaryOperator.MUL -> l * r
                BinaryOperator.DIV -> if (abs(r) < 1e-12) Double.NaN else l / r
                BinaryOperator.MOD -> if (abs(r) < 1e-12) Double.NaN else l % r
                BinaryOperator.POW -> evaluateRealPower(l, r)
                BinaryOperator.LT -> if (l < r) 1.0 else 0.0
                BinaryOperator.LE -> if (l <= r + 1e-12) 1.0 else 0.0
                BinaryOperator.GT -> if (l > r) 1.0 else 0.0
                BinaryOperator.GE -> if (l >= r - 1e-12) 1.0 else 0.0
                BinaryOperator.EQ -> if (abs(l - r) < 1e-12) 1.0 else 0.0
                BinaryOperator.NE -> if (abs(l - r) >= 1e-12) 1.0 else 0.0
                BinaryOperator.OR, BinaryOperator.AND -> 0.0
            }
        }
    }

    data class UnaryFunction(
        val func: MathFunction1,
        val argNode: MathAstNode
    ) : MathAstNode {
        override fun eval(x: Double): Double {
            val arg = argNode.eval(x)
            if (arg.isNaN()) return Double.NaN
            return when (func) {
                MathFunction1.SIN -> sin(arg)
                MathFunction1.COS -> cos(arg)
                MathFunction1.TAN -> if (abs(cos(arg)) < 1e-12) Double.NaN else tan(arg)
                MathFunction1.SEC -> if (abs(cos(arg)) < 1e-12) Double.NaN else 1.0 / cos(arg)
                MathFunction1.CSC -> if (abs(sin(arg)) < 1e-12) Double.NaN else 1.0 / sin(arg)
                MathFunction1.COT -> if (abs(sin(arg)) < 1e-12) Double.NaN else cos(arg) / sin(arg)
                MathFunction1.ASIN -> if (arg in -1.0..1.0) asin(arg) else Double.NaN
                MathFunction1.ACOS -> if (arg in -1.0..1.0) acos(arg) else Double.NaN
                MathFunction1.ATAN -> atan(arg)
                MathFunction1.SINH -> sinh(arg)
                MathFunction1.COSH -> cosh(arg)
                MathFunction1.TANH -> tanh(arg)
                MathFunction1.SQRT -> if (arg < 0.0) Double.NaN else sqrt(arg)
                MathFunction1.CBRT -> if (arg < 0.0) -(-arg).pow(1.0 / 3.0) else arg.pow(1.0 / 3.0)
                MathFunction1.ABS -> abs(arg)
                MathFunction1.LN -> if (arg <= 0.0) Double.NaN else ln(arg)
                MathFunction1.LOG10 -> if (arg <= 0.0) Double.NaN else log10(arg)
                MathFunction1.LOG2 -> if (arg <= 0.0) Double.NaN else ln(arg) / ln(2.0)
                MathFunction1.EXP -> exp(arg)
                MathFunction1.SIGN -> when {
                    arg > 1e-12 -> 1.0
                    arg < -1e-12 -> -1.0
                    else -> 0.0
                }
                MathFunction1.STEP -> if (arg >= 0.0) 1.0 else 0.0
                MathFunction1.FLOOR -> floor(arg)
                MathFunction1.CEIL -> ceil(arg)
                MathFunction1.ROUND -> round(arg)
            }
        }
    }

    data class BinaryFunction(
        val func: MathFunction2,
        val firstArg: MathAstNode,
        val secondArg: MathAstNode
    ) : MathAstNode {
        override fun eval(x: Double): Double {
            val a = firstArg.eval(x)
            val b = secondArg.eval(x)
            if (a.isNaN() || b.isNaN()) return Double.NaN
            return when (func) {
                MathFunction2.MIN -> minOf(a, b)
                MathFunction2.MAX -> maxOf(a, b)
                MathFunction2.POW -> evaluateRealPower(a, b)
                MathFunction2.ROOT -> {
                    // root(n, x) -> raiz n-ésima de b (ou a=índice, b=radicando)
                    if (abs(a) < 1e-12) Double.NaN else evaluateRealPower(b, 1.0 / a)
                }
                MathFunction2.LOG_BASE -> {
                    // log(x, base)
                    if (a <= 0.0 || b <= 0.0 || abs(b - 1.0) < 1e-12) Double.NaN else ln(a) / ln(b)
                }
            }
        }
    }

    data class ConditionalIf(
        val condition: MathAstNode,
        val trueBranch: MathAstNode,
        val falseBranch: MathAstNode
    ) : MathAstNode {
        override fun eval(x: Double): Double {
            val condVal = condition.eval(x)
            if (condVal.isNaN()) return Double.NaN
            return if (abs(condVal) > 1e-12) trueBranch.eval(x) else falseBranch.eval(x)
        }
    }

    companion object {
        /**
         * Avalia base^exponent no domínio dos números reais, preservando raízes ímpares de números negativos
         * como (-8)^(1/3) = -2 ou (-32)^(2/5) = +4.
         */
        fun evaluateRealPower(base: Double, exponent: Double): Double {
            if (base.isNaN() || exponent.isNaN()) return Double.NaN
            if (base < 0.0 && abs(exponent - round(exponent)) > 1e-12) {
                // Tenta aproximar o expoente por uma fração racional p/q com denominador ímpar q <= 15
                for (q in 3..15 step 2) {
                    val pApprox = round(exponent * q)
                    if (abs(exponent - pApprox / q.toDouble()) < 1e-5) {
                        val p = pApprox.toInt()
                        val mag = (-base).pow(exponent)
                        return if (p % 2 != 0) -mag else mag
                    }
                }
                return Double.NaN
            }
            val res = base.pow(exponent)
            return if (res.isInfinite() || res.isNaN()) Double.NaN else res
        }
    }
}

enum class BinaryOperator {
    ADD, SUB, MUL, DIV, MOD, POW,
    LT, LE, GT, GE, EQ, NE,
    AND, OR
}

enum class MathFunction1 {
    SIN, COS, TAN, SEC, CSC, COT,
    ASIN, ACOS, ATAN,
    SINH, COSH, TANH,
    SQRT, CBRT, ABS,
    LN, LOG10, LOG2, EXP,
    SIGN, STEP, FLOOR, CEIL, ROUND
}

enum class MathFunction2 {
    MIN, MAX, POW, ROOT, LOG_BASE
}

/**
 * Representação compilada e reutilizável de uma expressão matemática.
 * Uma vez compilada, pode ser avaliada milhares de vezes por segundo sem alocações nem parsing.
 */
class CompiledMathExpression internal constructor(
    val rawExpression: String,
    val normalizedExpression: String,
    internal val rootNode: MathAstNode?
) {
    val isValid: Boolean get() = rootNode != null

    fun evaluate(x: Double): Double {
        val node = rootNode ?: return Double.NaN
        return try {
            val result = node.eval(x)
            if (result.isInfinite() || result.isNaN()) Double.NaN else result
        } catch (_: Throwable) {
            Double.NaN
        }
    }
}

/**
 * Motor Matemático Analítico e Numérico Autônomo para o Knowverse (Camadas A e B).
 * Executa 100% nativamente em Kotlin (offline), com compilação para Árvore Sintática (AST)
 * e cache LRU thread-safe com limite de memória.
 */
object MathExpressionEvaluator {

    private const val EPSILON = 1e-12
    private const val MAX_CACHE_SIZE = 128

    // Cache LRU thread-safe de expressões compiladas
    private val compiledCache = object : LinkedHashMap<String, CompiledMathExpression>(64, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<String, CompiledMathExpression>?): Boolean {
            return size > MAX_CACHE_SIZE
        }
    }

    private val KNOWN_FUNCTIONS_1 = mapOf(
        "sin" to MathFunction1.SIN,
        "cos" to MathFunction1.COS,
        "tan" to MathFunction1.TAN,
        "sec" to MathFunction1.SEC,
        "csc" to MathFunction1.CSC,
        "cot" to MathFunction1.COT,
        "asin" to MathFunction1.ASIN,
        "arcsin" to MathFunction1.ASIN,
        "acos" to MathFunction1.ACOS,
        "arccos" to MathFunction1.ACOS,
        "atan" to MathFunction1.ATAN,
        "arctan" to MathFunction1.ATAN,
        "sinh" to MathFunction1.SINH,
        "cosh" to MathFunction1.COSH,
        "tanh" to MathFunction1.TANH,
        "sqrt" to MathFunction1.SQRT,
        "cbrt" to MathFunction1.CBRT,
        "abs" to MathFunction1.ABS,
        "ln" to MathFunction1.LN,
        "log" to MathFunction1.LOG10,
        "log10" to MathFunction1.LOG10,
        "log2" to MathFunction1.LOG2,
        "exp" to MathFunction1.EXP,
        "sign" to MathFunction1.SIGN,
        "sgn" to MathFunction1.SIGN,
        "step" to MathFunction1.STEP,
        "floor" to MathFunction1.FLOOR,
        "ceil" to MathFunction1.CEIL,
        "round" to MathFunction1.ROUND
    )

    private val KNOWN_FUNCTIONS_2 = mapOf(
        "min" to MathFunction2.MIN,
        "max" to MathFunction2.MAX,
        "pow" to MathFunction2.POW,
        "root" to MathFunction2.ROOT
    )

    private val ALL_FUNCTION_NAMES: Set<String> = KNOWN_FUNCTIONS_1.keys + KNOWN_FUNCTIONS_2.keys + "if"
    private val CONSTANTS = setOf("pi", "e", "tau")

    /**
     * Retorna o número atual de expressões compiladas no cache (útil para diagnósticos e testes).
     */
    fun getCacheSize(): Int = synchronized(compiledCache) { compiledCache.size }

    /**
     * Limpa o cache de expressões compiladas.
     */
    fun clearCache() = synchronized(compiledCache) { compiledCache.clear() }

    /**
     * Compila uma expressão matemática para uma AST otimizada ou retorna a instância já em cache.
     */
    fun compile(expression: String): CompiledMathExpression {
        val key = expression.trim()
        if (key.isEmpty()) {
            return CompiledMathExpression(expression, "", null)
        }
        synchronized(compiledCache) {
            compiledCache[key]?.let { return it }
        }

        val compiled = try {
            val tokens = tokenize(key)
            if (tokens.isEmpty()) {
                CompiledMathExpression(expression, key, null)
            } else {
                val parser = AstBuilderParser(tokens)
                val root = parser.parseExpression()
                if (parser.hasRemainingTokens()) {
                    CompiledMathExpression(expression, key, null)
                } else {
                    val foldedRoot = foldConstants(root)
                    CompiledMathExpression(expression, tokens.joinToString(""), foldedRoot)
                }
            }
        } catch (_: Throwable) {
            CompiledMathExpression(expression, key, null)
        }

        synchronized(compiledCache) {
            compiledCache[key] = compiled
        }
        return compiled
    }

    /**
     * Otimização de Constant Folding: subárvores que não dependem de 'x' são pré-calculadas na compilação.
     */
    private fun foldConstants(node: MathAstNode): MathAstNode {
        return when (node) {
            is MathAstNode.NumberLiteral, is MathAstNode.VariableX -> node
            is MathAstNode.UnaryMinus -> {
                val inner = foldConstants(node.operand)
                if (inner is MathAstNode.NumberLiteral) {
                    MathAstNode.NumberLiteral(-inner.value)
                } else {
                    MathAstNode.UnaryMinus(inner)
                }
            }
            is MathAstNode.BinaryOp -> {
                val l = foldConstants(node.left)
                val r = foldConstants(node.right)
                if (l is MathAstNode.NumberLiteral && r is MathAstNode.NumberLiteral) {
                    MathAstNode.NumberLiteral(MathAstNode.BinaryOp(node.op, l, r).eval(0.0))
                } else {
                    MathAstNode.BinaryOp(node.op, l, r)
                }
            }
            is MathAstNode.UnaryFunction -> {
                val arg = foldConstants(node.argNode)
                if (arg is MathAstNode.NumberLiteral) {
                    MathAstNode.NumberLiteral(MathAstNode.UnaryFunction(node.func, arg).eval(0.0))
                } else {
                    MathAstNode.UnaryFunction(node.func, arg)
                }
            }
            is MathAstNode.BinaryFunction -> {
                val a = foldConstants(node.firstArg)
                val b = foldConstants(node.secondArg)
                if (a is MathAstNode.NumberLiteral && b is MathAstNode.NumberLiteral) {
                    MathAstNode.NumberLiteral(MathAstNode.BinaryFunction(node.func, a, b).eval(0.0))
                } else {
                    MathAstNode.BinaryFunction(node.func, a, b)
                }
            }
            is MathAstNode.ConditionalIf -> {
                val c = foldConstants(node.condition)
                val t = foldConstants(node.trueBranch)
                val f = foldConstants(node.falseBranch)
                if (c is MathAstNode.NumberLiteral) {
                    if (abs(c.value) > EPSILON) t else f
                } else {
                    MathAstNode.ConditionalIf(c, t, f)
                }
            }
        }
    }

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
            val parser = AstBuilderParser(tokens)
            parser.parseExpression()
            if (parser.hasRemainingTokens()) {
                ExpressionValidationResult.Invalid("Tokens residuais não reconhecidos: '${parser.peekToken()}'")
            } else {
                ExpressionValidationResult.Valid
            }
        } catch (e: Exception) {
            ExpressionValidationResult.Invalid("Erro de sintaxe: ${e.message ?: "expressão malformada"}")
        }
    }

    /**
     * Avalia f(x) reutilizando a AST compilada em cache. Retorna Double.NaN se x estiver fora do domínio.
     */
    fun evaluate(expression: String, x: Double): Double {
        return compile(expression).evaluate(x)
    }

    /**
     * Derivada primeira f'(x) calculada via stencil centrado de 5 pontos O(h^4) sobre a AST compilada.
     */
    fun derivative(expression: String, x: Double, h: Double = 1e-4): Double {
        val compiled = compile(expression)
        if (!compiled.isValid) return Double.NaN

        val f_m2 = compiled.evaluate(x - 2 * h)
        val f_m1 = compiled.evaluate(x - h)
        val f_p1 = compiled.evaluate(x + h)
        val f_p2 = compiled.evaluate(x + 2 * h)

        if (!f_m2.isNaN() && !f_m1.isNaN() && !f_p1.isNaN() && !f_p2.isNaN()) {
            val num = -f_p2 + 8.0 * f_p1 - 8.0 * f_m1 + f_m2
            val den = 12.0 * h
            val res = num / den
            return if (res.isNaN() || res.isInfinite()) Double.NaN else res
        }

        // Fallback para diferença central O(h^2) ou diferença unilateral na borda do domínio (ex: sqrt(x) perto de 0)
        if (!f_p1.isNaN() && !f_m1.isNaN()) {
            val slope = (f_p1 - f_m1) / (2.0 * h)
            return if (slope.isNaN() || slope.isInfinite()) Double.NaN else slope
        }
        val f0 = compiled.evaluate(x)
        if (!f0.isNaN() && !f_p1.isNaN()) {
            return (f_p1 - f0) / h
        }
        if (!f0.isNaN() && !f_m1.isNaN()) {
            return (f0 - f_m1) / h
        }
        return Double.NaN
    }

    /**
     * Derivada segunda f''(x) para análise de concavidade e pontos de inflexão O(h^4).
     */
    fun secondDerivative(expression: String, x: Double, h: Double = 1e-3): Double {
        val compiled = compile(expression)
        if (!compiled.isValid) return Double.NaN

        val f_m2 = compiled.evaluate(x - 2 * h)
        val f_m1 = compiled.evaluate(x - h)
        val f_0 = compiled.evaluate(x)
        val f_p1 = compiled.evaluate(x + h)
        val f_p2 = compiled.evaluate(x + 2 * h)

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
     * Detecta singularidades/assíntotas no interior do intervalo para não produzir valores falsos.
     */
    fun definiteIntegral(expression: String, a: Double, b: Double, intervals: Int = 120): Double {
        if (abs(a - b) < EPSILON) return 0.0
        val compiled = compile(expression)
        if (!compiled.isValid) return Double.NaN

        val sign = if (b < a) -1.0 else 1.0
        val start = minOf(a, b)
        val end = maxOf(a, b)

        val n = if (intervals % 2 != 0) intervals + 1 else maxOf(2, intervals)
        val h = (end - start) / n

        // Usa deslocamento infinitesimal nas extremidades caso haja buraco removível na borda
        val fa = compiled.evaluate(start).let { if (it.isNaN()) compiled.evaluate(start + 1e-7) else it }
        val fb = compiled.evaluate(end).let { if (it.isNaN()) compiled.evaluate(end - 1e-7) else it }
        if (fa.isNaN() || fb.isNaN()) return Double.NaN

        var sumOdd = 0.0
        var sumEven = 0.0
        var prevY = fa

        for (i in 1 until n) {
            val x = start + i * h
            val fx = compiled.evaluate(x)
            if (fx.isNaN() || abs(fx) > 1e7) return Double.NaN
            // Se houver inversão abrupta de sinal típica de pólo não integrável (ex: 1/x em [-1, 1])
            if (prevY * fx < 0 && abs(fx - prevY) > 1e4) return Double.NaN
            if (i % 2 == 0) sumEven += fx else sumOdd += fx
            prevY = fx
        }

        val integral = sign * (h / 3.0) * (fa + 4.0 * sumOdd + 2.0 * sumEven + fb)
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
        if (n <= 0 || abs(a - b) < EPSILON) return 0.0
        val compiled = compile(expression)
        if (!compiled.isValid) return Double.NaN

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
                    val y1 = compiled.evaluate(xi)
                    val y2 = compiled.evaluate(xiNext)
                    if (y1.isNaN() || y2.isNaN()) return Double.NaN
                    sum += (y1 + y2) * 0.5 * dx
                    continue
                }
            }
            val y = compiled.evaluate(sampleX)
            if (y.isNaN()) return Double.NaN
            sum += y * dx
        }
        return sum
    }

    /**
     * Localiza raízes reais f(x) = 0 no intervalo [xMin, xMax].
     * Detecta:
     * 1. Raízes transversais (com mudança de sinal de f(x), via Bissecção).
     * 2. Raízes tangentes de multiplicidade par (sem mudança de sinal de f(x), ex: x^2 = 0, (x-2)^2 = 0),
     *    onde f'(x) muda de sinal e |f(x)| converge a 0.
     */
    fun findRoots(expression: String, xMin: Double, xMax: Double, steps: Int = 240): List<Double> {
        val compiled = compile(expression)
        if (!compiled.isValid || xMax <= xMin) return emptyList()

        val roots = mutableListOf<Double>()
        val span = xMax - xMin
        val dx = span / steps

        fun addRootIfUnique(r: Double) {
            val cleanR = if (abs(r) < 1e-7) 0.0 else r
            if (cleanR in (xMin - 1e-6)..(xMax + 1e-6)) {
                val yAtR = compiled.evaluate(cleanR)
                if (!yAtR.isNaN() && abs(yAtR) < 1e-4) {
                    if (roots.none { abs(it - cleanR) < 1e-3 }) {
                        roots.add(cleanR)
                    }
                }
            }
        }

        var prevX = xMin
        var prevY = compiled.evaluate(prevX)
        var prevD = derivative(expression, prevX)

        for (i in 1..steps) {
            val curX = xMin + i * dx
            val curY = compiled.evaluate(curX)
            val curD = derivative(expression, curX)

            if (!prevY.isNaN() && !curY.isNaN()) {
                // Caso 1: Ponto da malha caiu exatamente sobre a raiz
                if (abs(prevY) < 1e-9) {
                    addRootIfUnique(prevX)
                } else if (abs(curY) < 1e-9) {
                    addRootIfUnique(curX)
                }
                // Caso 2: Mudança de sinal de f(x) (Teorema de Bolzano) — filtra assíntotas verticais
                else if (prevY * curY < 0.0 && abs(curY - prevY) < span * 25.0) {
                    var left = prevX
                    var right = curX
                    var yLeft = prevY
                    var isContinuousZero = true
                    for (b in 0..28) {
                        val mid = (left + right) * 0.5
                        val yMid = compiled.evaluate(mid)
                        if (yMid.isNaN()) {
                            isContinuousZero = false
                            break
                        }
                        if (abs(yMid) < 1e-11) {
                            left = mid
                            right = mid
                            break
                        }
                        if (yLeft * yMid < 0.0) {
                            right = mid
                        } else {
                            left = mid
                            yLeft = yMid
                        }
                    }
                    if (isContinuousZero) {
                        addRootIfUnique((left + right) * 0.5)
                    }
                }
                // Caso 3: Raiz tangente (multiplicidade par, sem mudança de sinal de f(x), ex: (x-1)^2)
                // A derivada f'(x) inverte de sinal e |f(x)| está próximo de zero
                else if (!prevD.isNaN() && !curD.isNaN() && prevD * curD <= 0.0) {
                    var left = prevX
                    var right = curX
                    var dLeft = prevD
                    for (b in 0..24) {
                        val mid = (left + right) * 0.5
                        val dMid = derivative(expression, mid)
                        if (dMid.isNaN() || abs(dMid) < 1e-9) {
                            left = mid
                            right = mid
                            break
                        }
                        if (dLeft * dMid < 0.0) {
                            right = mid
                        } else {
                            left = mid
                            dLeft = dMid
                        }
                    }
                    val candidateX = (left + right) * 0.5
                    val candidateY = compiled.evaluate(candidateX)
                    if (!candidateY.isNaN() && abs(candidateY) < 1e-5) {
                        addRootIfUnique(candidateX)
                    }
                }
            }
            prevX = curX
            prevY = curY
            prevD = curD
        }
        return roots.sorted()
    }

    /**
     * Localiza extremos relativos (máximos e mínimos locais) no intervalo.
     */
    fun findExtrema(expression: String, xMin: Double, xMax: Double, steps: Int = 250): List<MathCriticalPoint> {
        val compiled = compile(expression)
        if (!compiled.isValid || xMax <= xMin) return emptyList()

        val extrema = mutableListOf<MathCriticalPoint>()
        val dx = (xMax - xMin) / steps
        var prevX = xMin
        var prevD = derivative(expression, prevX)

        for (i in 1..steps) {
            val curX = xMin + i * dx
            val curD = derivative(expression, curX)

            if (!prevD.isNaN() && !curD.isNaN() && prevD * curD <= 0.0 && (abs(prevD) > 1e-9 || abs(curD) > 1e-9)) {
                var left = prevX
                var right = curX
                var dLeft = prevD
                for (b in 0..20) {
                    val mid = (left + right) / 2.0
                    val dMid = derivative(expression, mid)
                    if (dMid.isNaN() || abs(dMid) < 1e-8) {
                        left = mid
                        right = mid
                        break
                    }
                    if (dLeft * dMid < 0) {
                        right = mid
                    } else {
                        left = mid
                        dLeft = dMid
                    }
                }
                val midX = (left + right) / 2.0
                val cleanX = if (abs(midX) < 1e-6) 0.0 else midX
                val yVal = compiled.evaluate(cleanX)
                // Confirma que a derivada realmente trocou de sinal ao redor de cleanX (evita ponto de sela x^3)
                val dBefore = derivative(expression, cleanX - dx * 0.5)
                val dAfter = derivative(expression, cleanX + dx * 0.5)
                if (!yVal.isNaN() && !dBefore.isNaN() && !dAfter.isNaN() && dBefore * dAfter < 0.0) {
                    val d2 = secondDerivative(expression, cleanX)
                    val type = when {
                        !d2.isNaN() && d2 < -1e-5 -> CriticalPointType.LOCAL_MAXIMUM
                        !d2.isNaN() && d2 > 1e-5 -> CriticalPointType.LOCAL_MINIMUM
                        else -> if (dBefore > 0) CriticalPointType.LOCAL_MAXIMUM else CriticalPointType.LOCAL_MINIMUM
                    }
                    val label = if (type == CriticalPointType.LOCAL_MAXIMUM) "Máx Local" else "Mín Local"
                    if (extrema.none { abs(it.x - cleanX) < 1e-2 }) {
                        extrema.add(MathCriticalPoint(cleanX, yVal, type, label))
                    }
                }
            }
            prevX = curX
            prevD = curD
        }
        return extrema
    }

    /**
     * Localiza pontos de inflexão onde a concavidade muda de sinal (f''(x) = 0 com troca de sinal).
     */
    fun findInflectionPoints(expression: String, xMin: Double, xMax: Double, steps: Int = 200): List<MathCriticalPoint> {
        val compiled = compile(expression)
        if (!compiled.isValid || xMax <= xMin) return emptyList()

        val inflections = mutableListOf<MathCriticalPoint>()
        val dx = (xMax - xMin) / steps
        var prevX = xMin
        var prevD2 = secondDerivative(expression, prevX)

        for (i in 1..steps) {
            val curX = xMin + i * dx
            val curD2 = secondDerivative(expression, curX)

            if (!prevD2.isNaN() && !curD2.isNaN() && prevD2 * curD2 <= 0.0 && (abs(prevD2) > 1e-5 || abs(curD2) > 1e-5)) {
                var left = prevX
                var right = curX
                var d2Left = prevD2
                for (b in 0..20) {
                    val mid = (left + right) * 0.5
                    val d2Mid = secondDerivative(expression, mid)
                    if (d2Mid.isNaN() || abs(d2Mid) < 1e-7) {
                        left = mid
                        right = mid
                        break
                    }
                    if (d2Left * d2Mid < 0.0) {
                        right = mid
                    } else {
                        left = mid
                        d2Left = d2Mid
                    }
                }
                val midX = (left + right) * 0.5
                val cleanX = if (abs(midX) < 1e-5) 0.0 else midX
                val yVal = compiled.evaluate(cleanX)
                val d2Before = secondDerivative(expression, cleanX - dx * 0.5)
                val d2After = secondDerivative(expression, cleanX + dx * 0.5)
                if (!yVal.isNaN() && abs(yVal) < 1e4 && !d2Before.isNaN() && !d2After.isNaN() && d2Before * d2After < 0.0) {
                    if (inflections.none { abs(it.x - cleanX) < 2e-2 }) {
                        inflections.add(
                            MathCriticalPoint(
                                x = cleanX,
                                y = yVal,
                                type = CriticalPointType.INFLECTION_POINT,
                                label = "Inflexão"
                            )
                        )
                    }
                }
            }
            prevX = curX
            prevD2 = curD2
        }
        return inflections
    }

    /**
     * Detecta assíntotas verticais (pólos onde |f(x)| -> ∞) no intervalo [xMin, xMax].
     */
    fun findVerticalAsymptotes(expression: String, xMin: Double, xMax: Double, steps: Int = 300): List<Double> {
        val compiled = compile(expression)
        if (!compiled.isValid || xMax <= xMin) return emptyList()

        val asymptotes = mutableListOf<Double>()
        val dx = (xMax - xMin) / steps
        var prevX = xMin
        var prevY = compiled.evaluate(prevX)

        for (i in 1..steps) {
            val curX = xMin + i * dx
            val curY = compiled.evaluate(curX)

            val jump = (!prevY.isNaN() && !curY.isNaN() && prevY * curY < 0.0 && abs(curY - prevY) > 25.0) ||
                    (prevY.isNaN() != curY.isNaN() && (abs(prevY) > 20.0 || abs(curY) > 20.0))

            if (jump) {
                // Refina a posição do pólo maximizando |f(x)| por busca ternária/bissecção
                var left = prevX
                var right = curX
                for (b in 0..24) {
                    val m1 = left + (right - left) / 3.0
                    val m2 = right - (right - left) / 3.0
                    val y1 = abs(compiled.evaluate(m1))
                    val y2 = abs(compiled.evaluate(m2))
                    if (y1.isNaN()) {
                        right = m1
                        break
                    }
                    if (y2.isNaN()) {
                        left = m2
                        break
                    }
                    if (y1 > y2) {
                        right = m2
                    } else {
                        left = m1
                    }
                }
                val poleX = (left + right) * 0.5
                val cleanPole = if (abs(poleX - round(poleX)) < 1e-3) round(poleX) else poleX
                val yNearLeft = abs(compiled.evaluate(cleanPole - 1e-4))
                val yNearRight = abs(compiled.evaluate(cleanPole + 1e-4))
                if (yNearLeft > 50.0 || yNearRight > 50.0) {
                    if (asymptotes.none { abs(it - cleanPole) < 1e-2 }) {
                        asymptotes.add(cleanPole)
                    }
                }
            }
            prevX = curX
            prevY = curY
        }
        return asymptotes.sorted()
    }

    // ---------------------------------------------------------------------------------------------
    // TOKENIZADOR ROBUSTO COM MULTIPLICAÇÃO IMPLÍCITA E SUPORTE A MÓDULOS ANINHADOS
    // ---------------------------------------------------------------------------------------------

    internal fun tokenize(rawExpr: String): List<String> {
        var s = rawExpr.trim()
            .replace(" ", "")
            .replace(Regex("^[a-zA-Z]\\(x\\)="), "")
            .replace(Regex("^y="), "")
            .replace("senh", "sinh", ignoreCase = true)
            .replace("tgh", "tanh", ignoreCase = true)
            .replace("arcsen", "asin", ignoreCase = true)
            .replace("arctg", "atan", ignoreCase = true)
            .replace("sen", "sin", ignoreCase = true)
            .replace("tg", "tan", ignoreCase = true)
            .replace("cotg", "cot", ignoreCase = true)
            .replace("cosec", "csc", ignoreCase = true)
            .replace("π", "pi")
            .replace("τ", "tau")
            .replace("²", "^2")
            .replace("³", "^3")
            .replace("√", "sqrt")

        // Converte barras verticais de módulo |expr| (inclusive aninhadas) em abs(expr)
        s = convertBarsToAbs(s)

        // 1. Passada de tokens brutos
        val rawTokens = mutableListOf<String>()
        var i = 0
        while (i < s.length) {
            val c = s[i]
            when {
                c.isDigit() || c == '.' -> {
                    val sb = StringBuilder()
                    var dotCount = 0
                    while (i < s.length && (s[i].isDigit() || s[i] == '.')) {
                        if (s[i] == '.') dotCount++
                        if (dotCount > 1) throw IllegalArgumentException("Número decimal com múltiplos pontos")
                        sb.append(s[i])
                        i++
                    }
                    // Suporte a notação científica ex: 1e-4 ou 2.5e3 se seguido de dígitos
                    if (i < s.length && (s[i] == 'e' || s[i] == 'E')) {
                        val nextIdx = i + 1
                        val hasSciDigits = (nextIdx < s.length && s[nextIdx].isDigit()) ||
                                (nextIdx + 1 < s.length && (s[nextIdx] == '+' || s[nextIdx] == '-') && s[nextIdx + 1].isDigit())
                        if (hasSciDigits) {
                            sb.append('e')
                            i++
                            if (s[i] == '+' || s[i] == '-') {
                                sb.append(s[i])
                                i++
                            }
                            while (i < s.length && s[i].isDigit()) {
                                sb.append(s[i])
                                i++
                            }
                        }
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
                    val word = sb.toString().lowercase()
                    splitImplicitIdentifier(word, rawTokens)
                }
                c in "+-*/^(),<>!=%?:|&" -> {
                    if (i < s.length - 1) {
                        val next = s[i + 1]
                        val two = "$c$next"
                        if (two in listOf("<=", ">=", "==", "!=", "||", "&&")) {
                            rawTokens.add(two)
                            i += 2
                            continue
                        }
                    }
                    if (c == '|' || c == '&') {
                        throw IllegalArgumentException("Operador lógico incompleto: '$c'")
                    }
                    rawTokens.add(c.toString())
                    i++
                }
                else -> throw IllegalArgumentException("Caractere inválido na expressão: '$c'")
            }
        }

        // 2. Inserir multiplicação implícita entre tokens
        val tokensWithMul = mutableListOf<String>()
        for (idx in rawTokens.indices) {
            val token = rawTokens[idx]
            tokensWithMul.add(token)

            if (idx < rawTokens.size - 1) {
                val nextToken = rawTokens[idx + 1]

                val isOperandEnd = isNumberToken(token) || token == "x" || token in CONSTANTS || token == ")"
                val isOperandStart = isNumberToken(nextToken) || nextToken == "x" || nextToken in CONSTANTS ||
                        nextToken in ALL_FUNCTION_NAMES || nextToken == "("

                if (isOperandEnd && isOperandStart) {
                    tokensWithMul.add("*")
                }
            }
        }

        return tokensWithMul
    }

    /**
     * Desmembra combinações coladas como "pix" -> ["pi", "x"], "ex" -> ["e", "x"], "xsin" -> ["x", "sin"]
     * sem quebrar nomes legítimos de funções como "exp", "sec", "csc", "asin", "acos", "atan".
     */
    private fun splitImplicitIdentifier(word: String, out: MutableList<String>) {
        if (word == "x" || word in CONSTANTS || word in ALL_FUNCTION_NAMES) {
            out.add(word)
            return
        }
        // Tenta casar prefixos conhecidos (funções maiores primeiro, depois constantes, depois 'x')
        val candidates = (ALL_FUNCTION_NAMES + CONSTANTS + "x").sortedByDescending { it.length }
        var remaining = word
        while (remaining.isNotEmpty()) {
            val match = candidates.firstOrNull { remaining.startsWith(it) }
            if (match != null) {
                out.add(match)
                remaining = remaining.substring(match.length)
            } else {
                // Identificador desconhecido: adiciona inteiro para que o parser lance erro claro
                out.add(remaining)
                break
            }
        }
    }

    private fun isNumberToken(token: String): Boolean {
        return token.toDoubleOrNull() != null
    }

    /**
     * Converte barras verticais |...| em chamadas abs(...), suportando módulos aninhados como ||x| - 2|.
     */
    private fun convertBarsToAbs(input: String): String {
        if (!input.contains('|')) return input
        val sb = StringBuilder()
        var barDepth = 0
        var i = 0
        while (i < input.length) {
            val c = input[i]
            if (c == '|') {
                val prevChar = if (i > 0) input[i - 1] else ' '
                val isAfterOperatorOrStart = i == 0 || prevChar in "+-*/^(,<>=!&?:"
                // Preserva o operador lógico OR '||' quando estamos fora de barras de módulo após um operando
                if (barDepth == 0 && !isAfterOperatorOrStart && i + 1 < input.length && input[i + 1] == '|') {
                    sb.append("||")
                    i += 2
                    continue
                }
                // Uma barra '|' abre um módulo se está no início ou logo após um operador/parêntese de abertura/vírgula
                val isOpening = barDepth == 0 || prevChar in "+-*/^(,<>=!&|?:"
                if (isOpening) {
                    sb.append("abs(")
                    barDepth++
                } else {
                    sb.append(")")
                    barDepth--
                }
            } else {
                sb.append(c)
            }
            i++
        }
        if (barDepth != 0) {
            throw IllegalArgumentException("Barras de módulo '|' desbalanceadas")
        }
        return sb.toString()
    }

    // ---------------------------------------------------------------------------------------------
    // PARSER CONSTRUTOR DE ÁRVORE SINTÁTICA ABSTRATA (AST BUILDER)
    // ---------------------------------------------------------------------------------------------

    private class AstBuilderParser(private val tokens: List<String>) {
        private var pos = 0

        fun hasRemainingTokens(): Boolean = pos < tokens.size
        fun peekToken(): String? = if (pos < tokens.size) tokens[pos] else null

        fun parseExpression(): MathAstNode = parseConditional()

        private fun parseConditional(): MathAstNode {
            val condition = parseLogicalOr()
            if (pos < tokens.size && tokens[pos] == "?") {
                pos++ // '?'
                val trueBranch = parseExpression()
                if (pos < tokens.size && tokens[pos] == ":") {
                    pos++ // ':'
                    val falseBranch = parseExpression()
                    return MathAstNode.ConditionalIf(condition, trueBranch, falseBranch)
                } else {
                    throw IllegalArgumentException("Operador ternário malformado: ':' esperado")
                }
            }
            return condition
        }

        private fun parseLogicalOr(): MathAstNode {
            var node = parseLogicalAnd()
            while (pos < tokens.size && tokens[pos] == "||") {
                pos++
                val right = parseLogicalAnd()
                node = MathAstNode.BinaryOp(BinaryOperator.OR, node, right)
            }
            return node
        }

        private fun parseLogicalAnd(): MathAstNode {
            var node = parseComparison()
            while (pos < tokens.size && tokens[pos] == "&&") {
                pos++
                val right = parseComparison()
                node = MathAstNode.BinaryOp(BinaryOperator.AND, node, right)
            }
            return node
        }

        private fun parseComparison(): MathAstNode {
            var node = parseAddSub()
            while (pos < tokens.size) {
                val opToken = tokens[pos]
                val op = when (opToken) {
                    "<" -> BinaryOperator.LT
                    "<=" -> BinaryOperator.LE
                    ">" -> BinaryOperator.GT
                    ">=" -> BinaryOperator.GE
                    "==" -> BinaryOperator.EQ
                    "!=" -> BinaryOperator.NE
                    else -> null
                } ?: break
                pos++
                val right = parseAddSub()
                node = MathAstNode.BinaryOp(op, node, right)
            }
            return node
        }

        private fun parseAddSub(): MathAstNode {
            var node = parseMulDiv()
            while (pos < tokens.size) {
                val opToken = tokens[pos]
                val op = when (opToken) {
                    "+" -> BinaryOperator.ADD
                    "-" -> BinaryOperator.SUB
                    else -> null
                } ?: break
                pos++
                val right = parseMulDiv()
                node = MathAstNode.BinaryOp(op, node, right)
            }
            return node
        }

        private fun parseMulDiv(): MathAstNode {
            var node = parseUnary()
            while (pos < tokens.size) {
                val opToken = tokens[pos]
                val op = when (opToken) {
                    "*" -> BinaryOperator.MUL
                    "/" -> BinaryOperator.DIV
                    "%" -> BinaryOperator.MOD
                    else -> null
                } ?: break
                pos++
                val right = parseUnary()
                node = MathAstNode.BinaryOp(op, node, right)
            }
            return node
        }

        private fun parseUnary(): MathAstNode {
            if (pos >= tokens.size) {
                throw IllegalArgumentException("Fim inesperado da expressão")
            }
            val token = tokens[pos]
            if (token == "-") {
                pos++
                return MathAstNode.UnaryMinus(parseUnary())
            }
            if (token == "+") {
                pos++
                return parseUnary()
            }
            return parsePower()
        }

        private fun parsePower(): MathAstNode {
            val base = parsePrimary()
            if (pos < tokens.size && tokens[pos] == "^") {
                pos++
                val exponent = parseUnary() // Associatividade à direita: 2^3^2 = 2^(3^2) e expoente unário: 2^-3
                return MathAstNode.BinaryOp(BinaryOperator.POW, base, exponent)
            }
            return base
        }

        private fun parsePrimary(): MathAstNode {
            if (pos >= tokens.size) {
                throw IllegalArgumentException("Termo esperado, mas a expressão terminou")
            }

            val token = tokens[pos]

            // 1. Parênteses
            if (token == "(") {
                pos++
                if (pos < tokens.size && tokens[pos] == ")") {
                    throw IllegalArgumentException("Parênteses vazios '()' não são permitidos")
                }
                val inner = parseExpression()
                if (pos < tokens.size && tokens[pos] == ")") {
                    pos++
                    return inner
                } else {
                    throw IllegalArgumentException("Parêntese de fechamento ')' ausente")
                }
            }

            // 2. Variável independente x
            if (token == "x") {
                pos++
                return MathAstNode.VariableX
            }

            // 3. Constantes matemáticas
            if (token == "pi") {
                pos++
                return MathAstNode.NumberLiteral(PI)
            }
            if (token == "e") {
                pos++
                return MathAstNode.NumberLiteral(E)
            }
            if (token == "tau") {
                pos++
                return MathAstNode.NumberLiteral(2.0 * PI)
            }

            // 4. Condicional if(cond, vTrue, vFalse)
            if (token == "if") {
                pos++
                if (pos < tokens.size && tokens[pos] == "(") {
                    pos++
                    val cond = parseExpression()
                    if (pos < tokens.size && tokens[pos] == ",") pos++ else throw IllegalArgumentException("Vírgula esperada após condição no if")
                    val vTrue = parseExpression()
                    if (pos < tokens.size && tokens[pos] == ",") pos++ else throw IllegalArgumentException("Segunda vírgula esperada no if")
                    val vFalse = parseExpression()
                    if (pos < tokens.size && tokens[pos] == ")") pos++ else throw IllegalArgumentException("Parêntese ')' ausente no if")
                    return MathAstNode.ConditionalIf(cond, vTrue, vFalse)
                }
                throw IllegalArgumentException("Parêntese '(' esperado após 'if'")
            }

            // 5. Funções de 2 argumentos: min(a, b), max(a, b), pow(a, b), root(n, x)
            val func2 = KNOWN_FUNCTIONS_2[token]
            if (func2 != null) {
                pos++
                if (pos < tokens.size && tokens[pos] == "(") {
                    pos++
                    val arg1 = parseExpression()
                    if (pos < tokens.size && tokens[pos] == ",") pos++ else throw IllegalArgumentException("Vírgula esperada na função '$token'")
                    val arg2 = parseExpression()
                    if (pos < tokens.size && tokens[pos] == ")") pos++ else throw IllegalArgumentException("Parêntese ')' esperado após argumentos de '$token'")
                    return MathAstNode.BinaryFunction(func2, arg1, arg2)
                }
                throw IllegalArgumentException("Parêntese '(' esperado após '$token'")
            }

            // 6. Funções de 1 argumento (com suporte a log(x, base) opcional)
            val func1 = KNOWN_FUNCTIONS_1[token]
            if (func1 != null) {
                pos++
                if (pos < tokens.size && tokens[pos] == "(") {
                    pos++
                    val arg1 = parseExpression()
                    // Caso especial: log(x, base) com dois argumentos
                    if ((token == "log" || token == "ln") && pos < tokens.size && tokens[pos] == ",") {
                        pos++
                        val baseArg = parseExpression()
                        if (pos < tokens.size && tokens[pos] == ")") pos++ else throw IllegalArgumentException("Parêntese ')' ausente em log(x, base)")
                        return MathAstNode.BinaryFunction(MathFunction2.LOG_BASE, arg1, baseArg)
                    }
                    if (pos < tokens.size && tokens[pos] == ")") {
                        pos++
                        return MathAstNode.UnaryFunction(func1, arg1)
                    } else {
                        throw IllegalArgumentException("Parêntese ')' ausente na função '$token'")
                    }
                } else {
                    // Permite notação curta de função aplicada a fator, ex: sin x ou -cos x
                    val arg = parseUnary()
                    return MathAstNode.UnaryFunction(func1, arg)
                }
            }

            // 7. Número literal
            val num = token.toDoubleOrNull()
            if (num != null) {
                pos++
                return MathAstNode.NumberLiteral(num)
            }

            throw IllegalArgumentException("Token inesperado ou função desconhecida: '$token'")
        }
    }
}
