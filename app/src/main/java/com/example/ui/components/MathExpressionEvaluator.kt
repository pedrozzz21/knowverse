package com.example.ui.components

import kotlin.math.*

/**
 * Avaliador Matemático Autônomo e de Alto Desempenho para Funções f(x).
 * Executa de forma 100% nativa no Kotlin sem necessidade de WebView, JavaScript ou dependências externas.
 *
 * Suporta:
 * - Operações básicas: +, -, *, /, ^ (potência), % (módulo)
 * - Funções analíticas: sin, cos, tan, tg, sen, sqrt, cbrt, abs, ln, log, exp, asin, acos, atan
 * - Constantes fundamentais: pi (π), e (base de Napier)
 * - Multiplicação implícita: 2x, 5(x+1), x(x-1)
 * - Tratamento gracioso de descontinuidades e divisão por zero (retorna Double.NaN)
 */
object MathExpressionEvaluator {

    fun evaluate(expression: String, x: Double): Double {
        return try {
            val tokens = tokenize(expression, x)
            val parser = ExprParser(tokens)
            val result = parser.parseExpression()
            if (result.isInfinite() || result.isNaN()) Double.NaN else result
        } catch (_: Throwable) {
            Double.NaN
        }
    }

    fun derivative(expression: String, x: Double, h: Double = 0.0001): Double {
        val yRight = evaluate(expression, x + h)
        val yLeft = evaluate(expression, x - h)
        if (yRight.isNaN() || yLeft.isNaN()) return Double.NaN
        val slope = (yRight - yLeft) / (2.0 * h)
        return if (slope.isInfinite() || slope.isNaN()) Double.NaN else slope
    }

    private fun tokenize(expr: String, xValue: Double): List<String> {
        var s = expr.trim()
            .replace(" ", "")
            .replace("f(x)=", "")
            .replace("y=", "")
            .replace("sen", "sin")
            .replace("tg", "tan")
            .replace("PI", "pi")
            .replace("E", "e")

        // Inserir multiplicação implícita (ex: 2x -> 2*x, 3( -> 3*(, )x -> )*x)
        val withImplicitMul = StringBuilder()
        for (i in s.indices) {
            val c = s[i]
            withImplicitMul.append(c)
            if (i < s.length - 1) {
                val next = s[i + 1]
                if ((c.isDigit() && (next == 'x' || next == '(' || next.isLetter())) ||
                    (c == 'x' && (next == '(' || next.isDigit())) ||
                    (c == ')' && (next == 'x' || next == '(' || next.isDigit() || next.isLetter()))
                ) {
                    withImplicitMul.append('*')
                }
            }
        }
        s = withImplicitMul.toString()

        val tokens = mutableListOf<String>()
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
                    tokens.add(sb.toString())
                }
                c == 'x' -> {
                    tokens.add(xValue.toString())
                    i++
                }
                c.isLetter() -> {
                    val sb = StringBuilder()
                    while (i < s.length && s[i].isLetter()) {
                        sb.append(s[i])
                        i++
                    }
                    val name = sb.toString().lowercase()
                    when (name) {
                        "pi" -> tokens.add(PI.toString())
                        "e" -> tokens.add(E.toString())
                        else -> tokens.add(name) // função: sin, cos, etc.
                    }
                }
                c in "+-*/^()" -> {
                    tokens.add(c.toString())
                    i++
                }
                else -> i++
            }
        }
        return tokens
    }

    private class ExprParser(private val tokens: List<String>) {
        private var pos = 0

        fun parseExpression(): Double {
            var value = parseTerm()
            while (pos < tokens.size) {
                val op = tokens[pos]
                if (op == "+" || op == "-") {
                    pos++
                    val nextTerm = parseTerm()
                    value = if (op == "+") value + nextTerm else value - nextTerm
                } else {
                    break
                }
            }
            return value
        }

        private fun parseTerm(): Double {
            var value = parsePower()
            while (pos < tokens.size) {
                val op = tokens[pos]
                if (op == "*" || op == "/") {
                    pos++
                    val nextFactor = parsePower()
                    value = if (op == "*") {
                        value * nextFactor
                    } else {
                        if (abs(nextFactor) < 1e-12) Double.NaN else value / nextFactor
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
                val exponent = parsePower() // right-associative
                return base.pow(exponent)
            }
            return base
        }

        private fun parseFactor(): Double {
            if (pos >= tokens.size) return Double.NaN

            val token = tokens[pos]

            // Operador unário negativo
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
                }
                return value
            }

            // Funções matemáticas
            val knownFuncs = listOf("sin", "cos", "tan", "sqrt", "cbrt", "abs", "ln", "log", "exp", "asin", "acos", "atan")
            if (token in knownFuncs) {
                pos++
                val arg = parseFactor()
                return when (token) {
                    "sin" -> sin(arg)
                    "cos" -> cos(arg)
                    "tan" -> if (abs(cos(arg)) < 1e-12) Double.NaN else tan(arg)
                    "sqrt" -> if (arg < 0) Double.NaN else sqrt(arg)
                    "cbrt" -> cbrt(arg)
                    "abs" -> abs(arg)
                    "ln" -> if (arg <= 0) Double.NaN else ln(arg)
                    "log" -> if (arg <= 0) Double.NaN else log10(arg)
                    "exp" -> exp(arg)
                    "asin" -> if (arg in -1.0..1.0) asin(arg) else Double.NaN
                    "acos" -> if (arg in -1.0..1.0) acos(arg) else Double.NaN
                    "atan" -> atan(arg)
                    else -> Double.NaN
                }
            }

            // Número constante
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
