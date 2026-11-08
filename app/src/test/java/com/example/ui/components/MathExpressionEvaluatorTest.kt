package com.example.ui.components

import com.example.data.exercise.GeoGebraGeometryExercises
import com.example.data.math.GeoGebraCommandParser
import com.example.data.math.GeoGebraCommandPrimitive
import com.example.data.math.GeoGebraCurriculumPresets
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertSame
import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.math.*

class MathExpressionEvaluatorTest {

    private val tol = 1e-4

    @Test
    fun testPolynomialEvaluation() {
        val res1 = MathExpressionEvaluator.evaluate("x^2 - 4x + 3", 2.0)
        assertEquals(-1.0, res1, tol)

        val res2 = MathExpressionEvaluator.evaluate("2x^3 - 5x + 1", -1.0)
        assertEquals(4.0, res2, tol)

        val res3 = MathExpressionEvaluator.evaluate("(x - 1)(x + 2)", 3.0)
        assertEquals(10.0, res3, tol)
    }

    @Test
    fun testCompiledAstAndLruCacheBehavior() {
        MathExpressionEvaluator.clearCache()
        assertEquals(0, MathExpressionEvaluator.getCacheSize())

        val expr = "3x^3 - 2x^2 + 5x - 7"
        val compiled1 = MathExpressionEvaluator.compile(expr)
        val compiled2 = MathExpressionEvaluator.compile("  3x^3 - 2x^2 + 5x - 7  ")

        assertTrue("A expressão compilada deve ser válida", compiled1.isValid)
        assertSame("Deve reutilizar a mesma instância compilada do cache LRU", compiled1, compiled2)
        assertEquals(1, MathExpressionEvaluator.getCacheSize())

        for (i in -500..500) {
            val x = i * 0.01
            val expected = 3 * x.pow(3) - 2 * x.pow(2) + 5 * x - 7
            assertEquals(expected, compiled1.evaluate(x), 1e-9)
        }

        for (k in 1..150) {
            MathExpressionEvaluator.compile("x + $k")
        }
        assertTrue("Cache deve respeitar o limite máximo de 128 entradas", MathExpressionEvaluator.getCacheSize() <= 128)
    }

    @Test
    fun testImplicitMultiplicationAndPortugueseAliases() {
        assertEquals(2.0 * PI * 3.0, MathExpressionEvaluator.evaluate("2pix", 3.0), tol)
        assertEquals((PI / 2.0) * 1.0, MathExpressionEvaluator.evaluate("xsin(x)", PI / 2.0), tol)
        assertEquals(1.0, MathExpressionEvaluator.evaluate("sen(pi/2)", 0.0), tol)
        assertEquals(1.0, MathExpressionEvaluator.evaluate("tg(pi/4)", 0.0), tol)
        assertEquals(4.0 + 8.0, MathExpressionEvaluator.evaluate("x² + x³", 2.0), tol)
    }

    @Test
    fun testRationalFunctionsAndHoles() {
        val res1 = MathExpressionEvaluator.evaluate("(x^2 - 4)/(x - 2)", 3.0)
        assertEquals(5.0, res1, tol)

        val resHole = MathExpressionEvaluator.evaluate("(x^2 - 4)/(x - 2)", 2.0)
        assertTrue("Em x = 2, deve retornar NaN para o buraco", resHole.isNaN())

        val resAsymptote = MathExpressionEvaluator.evaluate("1/(x - 3)", 3.0)
        assertTrue("Em x = 3, divisão por zero deve retornar NaN", resAsymptote.isNaN())
    }

    @Test
    fun testAbsoluteValueAndNestedModulus() {
        val res1 = MathExpressionEvaluator.evaluate("|x|", -5.0)
        assertEquals(5.0, res1, tol)

        val res2 = MathExpressionEvaluator.evaluate("abs(x - 2)", 0.0)
        assertEquals(2.0, res2, tol)

        val res3 = MathExpressionEvaluator.evaluate("3|x + 1|", -4.0)
        assertEquals(9.0, res3, tol)

        val resNested = MathExpressionEvaluator.evaluate("||x| - 3|", -1.0)
        assertEquals(2.0, resNested, tol)
    }

    @Test
    fun testTrigonometricAndInverseFunctions() {
        val resSin = MathExpressionEvaluator.evaluate("sin(x)", PI / 2.0)
        assertEquals(1.0, resSin, tol)

        val resCos = MathExpressionEvaluator.evaluate("cos(x)", 0.0)
        assertEquals(1.0, resCos, tol)

        val resTan = MathExpressionEvaluator.evaluate("tan(x)", PI / 4.0)
        assertEquals(1.0, resTan, tol)

        val resTanInf = MathExpressionEvaluator.evaluate("tan(x)", PI / 2.0)
        assertTrue("tan(pi/2) deve retornar NaN", resTanInf.isNaN())

        val resAsin = MathExpressionEvaluator.evaluate("asin(1)", 0.0)
        assertEquals(PI / 2.0, resAsin, tol)

        val resAtan = MathExpressionEvaluator.evaluate("atan(1)", 0.0)
        assertEquals(PI / 4.0, resAtan, tol)
    }

    @Test
    fun testTranscendentalAndLogarithms() {
        val resExp = MathExpressionEvaluator.evaluate("exp(x)", 1.0)
        assertEquals(E, resExp, tol)

        val resLn = MathExpressionEvaluator.evaluate("ln(e)", 0.0)
        assertEquals(1.0, resLn, tol)

        val resLog10 = MathExpressionEvaluator.evaluate("log10(100)", 0.0)
        assertEquals(2.0, resLog10, tol)

        val resLog2 = MathExpressionEvaluator.evaluate("log2(8)", 0.0)
        assertEquals(3.0, resLog2, tol)

        val resLogBase = MathExpressionEvaluator.evaluate("log(81, 3)", 0.0)
        assertEquals(4.0, resLogBase, tol)

        val resLnNeg = MathExpressionEvaluator.evaluate("ln(x)", -1.0)
        assertTrue("ln de número negativo deve retornar NaN", resLnNeg.isNaN())
    }

    @Test
    fun testRootsAndOddPowersOfNegativeNumbers() {
        val resSqrt = MathExpressionEvaluator.evaluate("sqrt(4)", 0.0)
        assertEquals(2.0, resSqrt, tol)

        val resSqrtNeg = MathExpressionEvaluator.evaluate("sqrt(-4)", 0.0)
        assertTrue("sqrt de número negativo deve retornar NaN", resSqrtNeg.isNaN())

        val resCbrt = MathExpressionEvaluator.evaluate("cbrt(x)", -8.0)
        assertEquals(-2.0, resCbrt, tol)

        assertEquals(-2.0, MathExpressionEvaluator.evaluate("x^(1/3)", -8.0), tol)
        assertEquals(4.0, MathExpressionEvaluator.evaluate("x^(2/3)", -8.0), tol)

        assertEquals(-3.0, MathExpressionEvaluator.evaluate("root(3, x)", -27.0), tol)
    }

    @Test
    fun testPiecewiseAndMultiArgFunctions() {
        val resNeg = MathExpressionEvaluator.evaluate("if(x < 0, -1, 1)", -2.5)
        assertEquals(-1.0, resNeg, tol)

        val resPos = MathExpressionEvaluator.evaluate("if(x < 0, -1, 1)", 3.2)
        assertEquals(1.0, resPos, tol)

        assertEquals(-1.0, MathExpressionEvaluator.evaluate("sign(x)", -10.0), tol)
        assertEquals(1.0, MathExpressionEvaluator.evaluate("sign(x)", 5.0), tol)

        assertEquals(2.0, MathExpressionEvaluator.evaluate("min(x, 2)", 5.0), tol)
        assertEquals(5.0, MathExpressionEvaluator.evaluate("max(x, 2)", 5.0), tol)
    }

    @Test
    fun testNumericalDerivativePrecision() {
        val d1 = MathExpressionEvaluator.derivative("x^2", 3.0)
        assertEquals(6.0, d1, 1e-4)

        val d2 = MathExpressionEvaluator.derivative("sin(x)", 0.0)
        assertEquals(1.0, d2, 1e-4)

        val d3 = MathExpressionEvaluator.derivative("exp(x)", 1.0)
        assertEquals(E, d3, 1e-4)
    }

    @Test
    fun testSecondDerivativeExtremaAndInflectionPoints() {
        val d2_max = MathExpressionEvaluator.secondDerivative("x^3 - 3x", -1.0)
        assertTrue("Em x = -1, f'' deve ser negativo", d2_max < -1.0)

        val d2_min = MathExpressionEvaluator.secondDerivative("x^3 - 3x", 1.0)
        assertTrue("Em x = 1, f'' deve ser positivo", d2_min > 1.0)

        val extrema = MathExpressionEvaluator.findExtrema("x^3 - 3x", -2.5, 2.5)
        assertEquals("Deve localizar exatamente 2 pontos extremos", 2, extrema.size)
        val maxPoint = extrema.firstOrNull { it.type == CriticalPointType.LOCAL_MAXIMUM }
        val minPoint = extrema.firstOrNull { it.type == CriticalPointType.LOCAL_MINIMUM }

        assertEquals(-1.0, maxPoint?.x ?: 0.0, 0.02)
        assertEquals(1.0, minPoint?.x ?: 0.0, 0.02)

        val inflections = MathExpressionEvaluator.findInflectionPoints("x^3 - 6x^2 + 9x + 1", 0.0, 4.0)
        assertEquals(1, inflections.size)
        assertEquals(2.0, inflections[0].x, 0.03)
        assertEquals(3.0, inflections[0].y, 0.05)
    }

    @Test
    fun testDefiniteIntegralAndRiemannSums() {
        val integral = MathExpressionEvaluator.definiteIntegral("x^2", 0.0, 2.0, 100)
        assertEquals(8.0 / 3.0, integral, 1e-4)

        val divergent = MathExpressionEvaluator.definiteIntegral("1/x", -1.0, 1.0, 100)
        assertTrue("Integral cruzando pólo 1/x deve retornar NaN", divergent.isNaN())

        val rLeft = MathExpressionEvaluator.calculateRiemannSum("x^2", 0.0, 2.0, 40, RiemannType.LEFT)
        val rRight = MathExpressionEvaluator.calculateRiemannSum("x^2", 0.0, 2.0, 40, RiemannType.RIGHT)
        val rMid = MathExpressionEvaluator.calculateRiemannSum("x^2", 0.0, 2.0, 40, RiemannType.MIDPOINT)
        val rTrap = MathExpressionEvaluator.calculateRiemannSum("x^2", 0.0, 2.0, 40, RiemannType.TRAPEZOIDAL)

        // Para f(x) = x^2 crescente em [0, 2]: LEFT < Integral < RIGHT
        assertTrue(rLeft < integral && integral < rRight)
        assertEquals(8.0 / 3.0, rMid, 0.01)
        assertEquals(8.0 / 3.0, rTrap, 0.01)
    }

    @Test
    fun testRootFindingTransversalAndEvenMultiplicity() {
        val roots1 = MathExpressionEvaluator.findRoots("x^2 - 4", -3.0, 3.0)
        assertEquals(2, roots1.size)
        assertEquals(-2.0, roots1[0], 0.01)
        assertEquals(2.0, roots1[1], 0.01)

        val rootsTangent = MathExpressionEvaluator.findRoots("(x - 1.37)^2", -1.0, 3.0)
        assertEquals("Deve localizar a raiz tangente que não troca de sinal", 1, rootsTangent.size)
        assertEquals(1.37, rootsTangent[0], 0.01)

        val rootsPole = MathExpressionEvaluator.findRoots("1/(x - 1)", -2.0, 3.0)
        assertTrue("Pólo vertical não deve ser reportado como raiz", rootsPole.isEmpty())
    }

    @Test
    fun testVerticalAsymptotesDetection() {
        val poles = MathExpressionEvaluator.findVerticalAsymptotes("1 / (x - 3)", -1.0, 6.0)
        assertEquals(1, poles.size)
        assertEquals(3.0, poles[0], 0.02)
    }

    @Test
    fun testSyntaxValidationRejectsMalformedInput() {
        val valid = MathExpressionEvaluator.validateExpression("2x^2 + 5x - 1")
        assertTrue("Expressão válida deve passar", valid is ExpressionValidationResult.Valid)

        val empty = MathExpressionEvaluator.validateExpression("")
        assertTrue("Vazio deve ser inválido", empty is ExpressionValidationResult.Invalid)

        val unclosed = MathExpressionEvaluator.validateExpression("(x + 2")
        assertTrue("Parênteses desbalanceados devem ser rejeitados", unclosed is ExpressionValidationResult.Invalid)

        val trailingOp = MathExpressionEvaluator.validateExpression("x^2 + ")
        assertTrue("Operador sem operando à direita deve ser rejeitado", trailingOp is ExpressionValidationResult.Invalid)

        val unknownFunc = MathExpressionEvaluator.validateExpression("foo(x)")
        assertTrue("Função desconhecida deve ser rejeitada", unknownFunc is ExpressionValidationResult.Invalid)

        val emptyParens = MathExpressionEvaluator.validateExpression("sin()")
        assertTrue("Parênteses vazios devem ser rejeitados", emptyParens is ExpressionValidationResult.Invalid)
    }

    @Test
    fun testGeoGebraCommandParserPrimitives() {
        // 1. Testa parsing de Polígono e Segmento (geo_02 - Triângulo Cartesiano)
        val geo02 = GeoGebraGeometryExercises.exercises.first { it.id == "geo_02" }.geoGebraConfig!!
        val prims02 = GeoGebraCommandParser.parse(geo02.commands, geo02.functionExpression)
        val poly = prims02.filterIsInstance<GeoGebraCommandPrimitive.Polygon2D>().firstOrNull()
        val seg = prims02.filterIsInstance<GeoGebraCommandPrimitive.Segment2D>().firstOrNull()
        assertNotNull("Deve extrair o triângulo Polygon(A, B, C)", poly)
        assertEquals(3, poly?.vertices?.size)
        assertNotNull("Deve extrair a altura Segment((4, 1), C)", seg)
        assertTrue("A altura h deve ser tracejada (SetLineStyle(h, 2))", seg?.isDashed == true)

        // 2. Testa parsing de Circunferência (geo_03 - x^2 + y^2 = 25)
        val geo03 = GeoGebraGeometryExercises.exercises.first { it.id == "geo_03" }.geoGebraConfig!!
        val prims03 = GeoGebraCommandParser.parse(geo03.commands, geo03.functionExpression)
        val circle = prims03.filterIsInstance<GeoGebraCommandPrimitive.Circle2D>().firstOrNull()
        assertNotNull("Deve extrair circunferência de raio 5", circle)
        assertEquals(5.0, circle?.radius ?: 0.0, 1e-4)

        // 3. Testa parsing de Elipse (geo_08 - x^2/25 + y^2/9 = 1)
        val geo08 = GeoGebraGeometryExercises.exercises.first { it.id == "geo_08" }.geoGebraConfig!!
        val prims08 = GeoGebraCommandParser.parse(geo08.commands, geo08.functionExpression)
        val ellipse = prims08.filterIsInstance<GeoGebraCommandPrimitive.Ellipse2D>().firstOrNull()
        assertNotNull("Deve extrair elipse com rx=5 e ry=3", ellipse)
        assertEquals(5.0, ellipse?.rx ?: 0.0, 1e-4)
        assertEquals(3.0, ellipse?.ry ?: 0.0, 1e-4)

        // 4. Testa parsing de Assíntotas com rótulo (calc_l3: "h: y = 2", calc_l4: "v: x = 3")
        val calc3 = GeoGebraCurriculumPresets.get("calc_l3")!!
        val primsCalc3 = GeoGebraCommandParser.parse(calc3.commands, calc3.functionExpression)
        val hLine = primsCalc3.filterIsInstance<GeoGebraCommandPrimitive.HorizontalLine>().firstOrNull()
        assertNotNull("Deve extrair assíntota horizontal h: y = 2", hLine)
        assertEquals(2.0, hLine?.y ?: 0.0, 1e-4)

        val calc4 = GeoGebraCurriculumPresets.get("calc_l4")!!
        val primsCalc4 = GeoGebraCommandParser.parse(calc4.commands, calc4.functionExpression)
        val vLine = primsCalc4.filterIsInstance<GeoGebraCommandPrimitive.VerticalLine>().firstOrNull()
        assertNotNull("Deve extrair assíntota vertical v: x = 3", vLine)
        assertEquals(3.0, vLine?.x ?: 0.0, 1e-4)

        // 5. Testa parsing de Funções Extras para Confronto (calc_l7: g(x) = cos(x), h(x) = 1)
        val calc7 = GeoGebraCurriculumPresets.get("calc_l7")!!
        val primsCalc7 = GeoGebraCommandParser.parse(calc7.commands, calc7.functionExpression)
        val extraFuncs = primsCalc7.filterIsInstance<GeoGebraCommandPrimitive.ExtraFunction>()
        assertEquals("Deve extrair g(x) = cos(x) e h(x) = 1", 2, extraFuncs.size)
    }

    @Test
    fun testAllCurriculumPresetsAndGeometryExercisesCompileCleanly() {
        val presetIds = listOf(
            "calc_l1", "calc_l2", "calc_l3", "calc_l4", "calc_l5",
            "calc_l6", "calc_l7", "calc_l8", "calc_l9", "calc_l10",
            "calc_l11", "calc_l16", "calc_l17", "calc_l18", "calc_l19",
            "calc_l20", "calc_l22", "calc_l23"
        )
        for (id in presetIds) {
            val config = GeoGebraCurriculumPresets.get(id)
            assertNotNull("Preset $id deve existir", config)
            val compiled = MathExpressionEvaluator.compile(config!!.functionExpression)
            assertTrue("Expressão principal de $id ('${config.functionExpression}') deve compilar", compiled.isValid)
            config.presetFunctions.forEach { pf ->
                assertTrue(
                    "Preset function '$pf' em $id deve compilar",
                    MathExpressionEvaluator.compile(pf).isValid
                )
            }
        }

        for (ex in GeoGebraGeometryExercises.exercises) {
            val cfg = ex.geoGebraConfig
            assertNotNull("Exercício ${ex.id} deve ter geoGebraConfig", cfg)
            val compiled = MathExpressionEvaluator.compile(cfg!!.functionExpression)
            assertTrue("Expressão de ${ex.id} ('${cfg.functionExpression}') deve compilar", compiled.isValid)
        }
    }
}
