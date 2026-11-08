package com.example.ui.components

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.*

class MathExpressionEvaluatorTest {

    private val tol = 1e-4

    @Test
    fun testPolynomialEvaluation() {
        // f(x) = x^2 - 4x + 3 em x = 2: 4 - 8 + 3 = -1
        val res1 = MathExpressionEvaluator.evaluate("x^2 - 4x + 3", 2.0)
        assertEquals(-1.0, res1, tol)

        // f(x) = 2x^3 - 5x + 1 em x = -1: 2(-1) - 5(-1) + 1 = -2 + 5 + 1 = 4
        val res2 = MathExpressionEvaluator.evaluate("2x^3 - 5x + 1", -1.0)
        assertEquals(4.0, res2, tol)

        // f(x) = (x - 1)(x + 2) em x = 3: (2)(5) = 10
        val res3 = MathExpressionEvaluator.evaluate("(x - 1)(x + 2)", 3.0)
        assertEquals(10.0, res3, tol)
    }

    @Test
    fun testRationalFunctionsAndHoles() {
        // f(x) = (x^2 - 4)/(x - 2) em x = 3 -> 9 - 4 / 1 = 5
        val res1 = MathExpressionEvaluator.evaluate("(x^2 - 4)/(x - 2)", 3.0)
        assertEquals(5.0, res1, tol)

        // No ponto do buraco x = 2, f(2) = 0/0 -> NaN
        val resHole = MathExpressionEvaluator.evaluate("(x^2 - 4)/(x - 2)", 2.0)
        assertTrue("Em x = 2, deve retornar NaN para o buraco", resHole.isNaN())

        // Assíntota vertical 1/(x - 3) em x = 3 -> NaN
        val resAsymptote = MathExpressionEvaluator.evaluate("1/(x - 3)", 3.0)
        assertTrue("Em x = 3, divisão por zero deve retornar NaN", resAsymptote.isNaN())
    }

    @Test
    fun testAbsoluteValueAndModulus() {
        // |x|
        val res1 = MathExpressionEvaluator.evaluate("|x|", -5.0)
        assertEquals(5.0, res1, tol)

        // abs(x - 2)
        val res2 = MathExpressionEvaluator.evaluate("abs(x - 2)", 0.0)
        assertEquals(2.0, res2, tol)

        // 3|x + 1| em x = -4: 3 * 3 = 9
        val res3 = MathExpressionEvaluator.evaluate("3|x + 1|", -4.0)
        assertEquals(9.0, res3, tol)
    }

    @Test
    fun testTrigonometricAndInverseFunctions() {
        // sin(pi / 2) = 1
        val resSin = MathExpressionEvaluator.evaluate("sin(x)", PI / 2.0)
        assertEquals(1.0, resSin, tol)

        // cos(0) = 1
        val resCos = MathExpressionEvaluator.evaluate("cos(x)", 0.0)
        assertEquals(1.0, resCos, tol)

        // tan(pi / 4) = 1
        val resTan = MathExpressionEvaluator.evaluate("tan(x)", PI / 4.0)
        assertEquals(1.0, resTan, tol)

        // tan(pi / 2) -> descontinuidade vertical (cos = 0) -> NaN
        val resTanInf = MathExpressionEvaluator.evaluate("tan(x)", PI / 2.0)
        assertTrue("tan(pi/2) deve retornar NaN", resTanInf.isNaN())

        // asin(1) = pi/2
        val resAsin = MathExpressionEvaluator.evaluate("asin(1)", 0.0)
        assertEquals(PI / 2.0, resAsin, tol)

        // atan(1) = pi/4
        val resAtan = MathExpressionEvaluator.evaluate("atan(1)", 0.0)
        assertEquals(PI / 4.0, resAtan, tol)
    }

    @Test
    fun testTranscendentalAndLogarithms() {
        // exp(1) = e ≈ 2.71828
        val resExp = MathExpressionEvaluator.evaluate("exp(x)", 1.0)
        assertEquals(E, resExp, tol)

        // ln(e) = 1
        val resLn = MathExpressionEvaluator.evaluate("ln(e)", 0.0)
        assertEquals(1.0, resLn, tol)

        // log10(100) = 2
        val resLog10 = MathExpressionEvaluator.evaluate("log10(100)", 0.0)
        assertEquals(2.0, resLog10, tol)

        // ln(-1) -> fora do domínio real -> NaN
        val resLnNeg = MathExpressionEvaluator.evaluate("ln(x)", -1.0)
        assertTrue("ln de número negativo deve retornar NaN", resLnNeg.isNaN())
    }

    @Test
    fun testRootsAndOddPowersOfNegativeNumbers() {
        // sqrt(4) = 2
        val resSqrt = MathExpressionEvaluator.evaluate("sqrt(4)", 0.0)
        assertEquals(2.0, resSqrt, tol)

        // sqrt(-4) -> fora do domínio real -> NaN
        val resSqrtNeg = MathExpressionEvaluator.evaluate("sqrt(-4)", 0.0)
        assertTrue("sqrt de número negativo deve retornar NaN", resSqrtNeg.isNaN())

        // cbrt(-8) = -2 (raiz cúbica de base negativa é matematicamente válida nos reais)
        val resCbrt = MathExpressionEvaluator.evaluate("cbrt(x)", -8.0)
        assertEquals(-2.0, resCbrt, tol)
    }

    @Test
    fun testPiecewiseAndConditionalFunctions() {
        // if(x < 0, -1, 1) - função degrau/salto
        val resNeg = MathExpressionEvaluator.evaluate("if(x < 0, -1, 1)", -2.5)
        assertEquals(-1.0, resNeg, tol)

        val resPos = MathExpressionEvaluator.evaluate("if(x < 0, -1, 1)", 3.2)
        assertEquals(1.0, resPos, tol)

        // sign(x)
        assertEquals(-1.0, MathExpressionEvaluator.evaluate("sign(x)", -10.0), tol)
        assertEquals(1.0, MathExpressionEvaluator.evaluate("sign(x)", 5.0), tol)
    }

    @Test
    fun testNumericalDerivativePrecision() {
        // d/dx [x^2] em x = 3 -> 2(3) = 6
        val d1 = MathExpressionEvaluator.derivative("x^2", 3.0)
        assertEquals(6.0, d1, 1e-3)

        // d/dx [sin(x)] em x = 0 -> cos(0) = 1
        val d2 = MathExpressionEvaluator.derivative("sin(x)", 0.0)
        assertEquals(1.0, d2, 1e-3)

        // d/dx [e^x] em x = 1 -> e ≈ 2.71828
        val d3 = MathExpressionEvaluator.derivative("exp(x)", 1.0)
        assertEquals(E, d3, 1e-3)
    }

    @Test
    fun testSecondDerivativeAndExtrema() {
        // f(x) = x^3 - 3x
        // f'(x) = 3x^2 - 3 => raízes x = -1 (máx local) e x = 1 (mín local)
        // f''(x) = 6x => f''(-1) = -6 (côncava para baixo), f''(1) = 6 (côncava para cima)
        val d2_max = MathExpressionEvaluator.secondDerivative("x^3 - 3x", -1.0)
        assertTrue("Em x = -1, f'' deve ser negativo", d2_max < -1.0)

        val d2_min = MathExpressionEvaluator.secondDerivative("x^3 - 3x", 1.0)
        assertTrue("Em x = 1, f'' deve ser positivo", d2_min > 1.0)

        val extrema = MathExpressionEvaluator.findExtrema("x^3 - 3x", -2.5, 2.5)
        assertTrue("Deve localizar pelo menos 2 pontos extremos", extrema.size >= 2)
        val maxPoint = extrema.firstOrNull { it.type == CriticalPointType.LOCAL_MAXIMUM }
        val minPoint = extrema.firstOrNull { it.type == CriticalPointType.LOCAL_MINIMUM }

        assertEquals(-1.0, maxPoint?.x ?: 0.0, 0.05)
        assertEquals(1.0, minPoint?.x ?: 0.0, 0.05)
    }

    @Test
    fun testDefiniteIntegralAndRiemannSums() {
        // ∫[0, 2] x^2 dx = [x^3 / 3]_0^2 = 8 / 3 ≈ 2.6667
        val integral = MathExpressionEvaluator.definiteIntegral("x^2", 0.0, 2.0, 100)
        assertEquals(8.0 / 3.0, integral, 1e-3)

        // Somas de Riemann aproximam a integral conforme n cresce
        val r8 = MathExpressionEvaluator.calculateRiemannSum("x^2", 0.0, 2.0, 8, RiemannType.MIDPOINT)
        val r40 = MathExpressionEvaluator.calculateRiemannSum("x^2", 0.0, 2.0, 40, RiemannType.MIDPOINT)

        assertTrue(abs(r40 - (8.0 / 3.0)) < abs(r8 - (8.0 / 3.0)))
        assertEquals(8.0 / 3.0, r40, 0.01)
    }

    @Test
    fun testRootFinding() {
        // f(x) = x^2 - 4 -> raízes em x = -2 e x = 2
        val roots = MathExpressionEvaluator.findRoots("x^2 - 4", -3.0, 3.0)
        assertEquals(2, roots.size)
        assertEquals(-2.0, roots[0], 0.01)
        assertEquals(2.0, roots[1], 0.01)
    }

    @Test
    fun testSyntaxValidationRejectsMalformedInput() {
        val valid = MathExpressionEvaluator.validateExpression("2x^2 + 5x - 1")
        assertTrue("Expressão válida deve passar", valid is ExpressionValidationResult.Valid)

        val empty = MathExpressionEvaluator.validateExpression("")
        assertTrue("Vazio deve ser inválido", empty is ExpressionValidationResult.Invalid)

        val unclosed = MathExpressionEvaluator.validateExpression("(x + 2")
        assertTrue("Parênteses desbalanceados devem ser rejeitados", unclosed is ExpressionValidationResult.Invalid)
    }
}
