package com.example.data.math.engine

import org.junit.Assert.*
import org.junit.Test
import kotlin.math.*

class Etapa3MathEnginesTest {

    // =========================================================================
    // 1. DERIVAÇÃO SIMBÓLICA POR AST E REGRAS DE DERIVAÇÃO
    // =========================================================================

    @Test
    fun `symbolic differentiation of polynomials and chain rule matches analytical derivative`() {
        val poly = SymbolicAndNumericalAnalyzer.differentiate("3*x^4 - 5*x^2 + 7*x - 10")
        assertTrue(poly.isSymbolicExact)
        // Em x = 2: f'(2) = 12*(8) - 10*(2) + 7 = 96 - 20 + 7 = 83
        assertEquals(83.0, poly.evaluateAt(2.0), 1e-9)

        val chainTrig = SymbolicAndNumericalAnalyzer.differentiate("sin(2*x^2)")
        assertTrue(chainTrig.isSymbolicExact)
        // f'(x) = 4*x*cos(2*x^2)
        val x0 = 1.2
        assertEquals(4.0 * x0 * cos(2.0 * x0 * x0), chainTrig.evaluateAt(x0), 1e-9)

        val prodQuot = SymbolicAndNumericalAnalyzer.differentiate("(x * exp(x)) / (x + 1)")
        assertTrue(prodQuot.isSymbolicExact)
        // d/dx [x e^x / (x+1)] = e^x (x^2 + x + 1) / (x+1)^2
        val x1 = 1.5
        val expected = exp(x1) * (x1 * x1 + x1 + 1.0) / ((x1 + 1.0).pow(2))
        assertEquals(expected, prodQuot.evaluateAt(x1), 1e-8)
    }

    @Test
    fun `symbolic differentiation reports numerical fallback on discrete functions`() {
        val discrete = SymbolicAndNumericalAnalyzer.differentiate("floor(x) + 2*x")
        assertFalse(discrete.isSymbolicExact)
        assertNotNull(discrete.fallbackReason)
        // Em x = 1.5 (longe do degrau), a derivada numérica de floor(x) + 2x é 2.0
        assertEquals(2.0, discrete.evaluateAt(1.5), 1e-5)
    }

    // =========================================================================
    // 2. MÉTODOS NUMÉRICOS DE RAÍZES (BISSEÇÃO, NEWTON, SECANTE, HÍBRIDO)
    // =========================================================================

    @Test
    fun `root finding methods converge on transcendental and even-multiplicity roots`() {
        for (method in RootFindingMethod.entries) {
            val roots = SymbolicAndNumericalAnalyzer.findDetailedRoots(
                expression = "cos(x) - x",
                xMin = 0.0,
                xMax = 1.5,
                preferredMethod = method
            )
            assertEquals("Method $method must find Dottie number root", 1, roots.size)
            assertTrue(roots[0].converged)
            assertEquals(0.739085133, roots[0].root, 1e-5)
        }

        // Raiz tangente de multiplicidade par: (x - 2)^2 = 0
        val evenRoot = SymbolicAndNumericalAnalyzer.findDetailedRoots("(x - 2)^2", 0.0, 4.0)
        assertEquals(1, evenRoot.size)
        assertEquals(2.0, evenRoot[0].root, 1e-3)
    }

    // =========================================================================
    // 3. ASSÍNTOTAS (VERTICAIS, HORIZONTAIS, OBLÍQUAS) E DESCONTINUIDADES
    // =========================================================================

    @Test
    fun `asymptote and discontinuity analyzer detects vertical oblique and removable holes`() {
        // f(x) = (2*x^2 + 3*x + 1) / x = 2x + 3 + 1/x -> assíntota vertical x=0 e oblíqua y=2x+3
        val asymp = SymbolicAndNumericalAnalyzer.analyzeAllAsymptotes("(2*x^2 + 3*x + 1)/x", -5.0, 5.0)
        assertTrue(asymp.any { it.kind == AsymptoteKind.VERTICAL && abs((it.xPosition ?: 99.0) - 0.0) < 1e-2 })
        assertTrue(asymp.any { it.kind == AsymptoteKind.OBLIQUE && abs((it.slope ?: 0.0) - 2.0) < 0.05 && abs((it.intercept ?: 0.0) - 3.0) < 0.1 })

        // Descontinuidade removível em x = 1 para (x^2 - 1)/(x - 1)
        val disc = SymbolicAndNumericalAnalyzer.detectDiscontinuities("(x^2 - 1)/(x - 1)", -2.0, 4.0)
        val hole = disc.firstOrNull { it.kind == DiscontinuityKind.REMOVABLE && abs(it.x - 1.0) < 1e-3 }
        assertNotNull("Removable discontinuity at x=1 must be detected", hole)
        assertEquals(2.0, hole!!.leftLimit, 1e-3)
        assertEquals(2.0, hole.rightLimit, 1e-3)
    }

    // =========================================================================
    // 4. INTEGRAIS: ÁREA ASSINADA VS ÁREA GEOMÉTRICA E PRIMITIVAS SIMBÓLICAS
    // =========================================================================

    @Test
    fun `integration engine distinguishes signed area from geometric area and finds symbolic primitives`() {
        val report = SymbolicAndNumericalAnalyzer.compareIntegrationMethods("sin(x)", -PI, PI, subdivisions = 40)
        // Integral assinada de sin(x) em [-π, π] é 0; área geométrica ∫|sin(x)|dx é 4
        assertEquals(0.0, report.signedAreaSimpson, 1e-7)
        assertEquals(4.0, report.geometricAreaSimpson, 1e-3)
        assertTrue(report.isSymbolicSupported)
        assertEquals("-cos(x) + C", report.symbolicAntiderivative)

        val polyAnti = SymbolicAndNumericalAnalyzer.trySymbolicAntiderivative("3*x^2 + 2/x")
        assertNotNull(polyAnti)
    }

    // =========================================================================
    // 5. GEOMETRIA ANALÍTICA, VETORES E INTERSEÇÕES
    // =========================================================================

    @Test
    fun `analytic geometry engine computes vectors lines and intersections accurately`() {
        val u = MathVector2D(3.0, 4.0)
        val v = MathVector2D(-4.0, 3.0)
        assertEquals(5.0, u.magnitude, 1e-9)
        assertEquals(0.0, u.dot(v), 1e-9)
        assertEquals(90.0, u.angleBetweenDegrees(v), 1e-7)

        // Interseção de duas retas: x + y - 4 = 0 e x - y = 0 -> (2, 2)
        val l1 = AnalyticLine2D(1.0, 1.0, -4.0)
        val l2 = AnalyticLine2D(1.0, -1.0, 0.0)
        val interLines = AnalyticGeometryAndCoordinatesEngine.intersectTwoLines(l1, l2)
        assertEquals(IntersectionStatus.SINGLE_INTERSECTION, interLines.status)
        assertEquals(2.0, interLines.points[0].x, 1e-7)
        assertEquals(2.0, interLines.points[0].y, 1e-7)

        // Reta tangente à circunferência x^2 + y^2 = 25 em y = 5 (0*x + 1*y - 5 = 0)
        val tangentLine = AnalyticLine2D(0.0, 1.0, -5.0)
        val interTang = AnalyticGeometryAndCoordinatesEngine.intersectLineAndCircle(tangentLine, 0.0, 0.0, 5.0)
        assertEquals(IntersectionStatus.TANGENT, interTang.status)
        assertEquals(1, interTang.points.size)
        assertEquals(0.0, interTang.points[0].x, 1e-6)
        assertEquals(5.0, interTang.points[0].y, 1e-6)

        // Duas circunferências secantes
        val interCircles = AnalyticGeometryAndCoordinatesEngine.intersectTwoCircles(0.0, 0.0, 5.0, 6.0, 0.0, 5.0)
        assertEquals(IntersectionStatus.MULTIPLE_INTERSECTIONS, interCircles.status)
        assertEquals(2, interCircles.points.size)
        assertEquals(3.0, interCircles.points[0].x, 1e-6)
        assertEquals(4.0, abs(interCircles.points[0].y), 1e-6)
    }

    // =========================================================================
    // 6. COORDENADAS POLARES, PARAMÉTRICAS E IMPLÍCITAS (MARCHING SQUARES)
    // =========================================================================

    @Test
    fun `polar parametric and implicit marching squares generators produce valid geometry`() {
        val polarRose = AnalyticGeometryAndCoordinatesEngine.samplePolarCurve("4*cos(2*theta)")
        assertTrue(polarRose.isNotEmpty())
        assertTrue(polarRose.first().size > 50)

        val paramCircle = AnalyticGeometryAndCoordinatesEngine.sampleParametricCurve("3*cos(t)", "3*sin(t)")
        assertTrue(paramCircle.isNotEmpty())
        for (pt in paramCircle.first()) {
            assertEquals(3.0, hypot(pt.x, pt.y), 1e-6)
        }

        val implicitCircle = AnalyticGeometryAndCoordinatesEngine.sampleImplicitCurveMarchingSquares(
            implicitEquation = "x^2 + y^2 = 9",
            xMin = -5.0,
            xMax = 5.0,
            yMin = -5.0,
            yMax = 5.0,
            gridResolution = 40
        )
        assertTrue("Marching squares must extract contour segments for circle", implicitCircle.size > 40)
        for (seg in implicitCircle) {
            assertEquals(3.0, hypot(seg.x1, seg.y1), 0.15)
        }
    }

    // =========================================================================
    // 7. ESTATÍSTICA DESCRITIVA (POPULACIONAL VS AMOSTRAL) E REGRESSÃO LINEAR
    // =========================================================================

    @Test
    fun `statistics engine differentiates population vs sample variance and computes linear regression`() {
        val sample = listOf(2.0, 4.0, 4.0, 4.0, 5.0, 5.0, 7.0, 9.0)
        val summary = StatisticsAndDataEngine.analyzeUnivariate(sample)
        assertNotNull(summary)
        summary!!

        assertEquals(8, summary.count)
        assertEquals(5.0, summary.mean, 1e-9)
        assertEquals(4.5, summary.median, 1e-9)
        assertEquals(listOf(4.0), summary.modes)
        // Soma dos quadrados dos desvios = (9 + 1 + 1 + 1 + 0 + 0 + 4 + 16) = 32
        // Variância populacional = 32 / 8 = 4.0 (σ = 2.0)
        // Variância amostral = 32 / 7 ≈ 4.571428 (s ≈ 2.13809)
        assertEquals(4.0, summary.populationVariance, 1e-9)
        assertEquals(2.0, summary.populationStdDev, 1e-9)
        assertEquals(32.0 / 7.0, summary.sampleVariance, 1e-9)
        assertEquals(sqrt(32.0 / 7.0), summary.sampleStdDev, 1e-9)

        // Regressão linear exata y = 2x + 5
        val xs = listOf(1.0, 2.0, 3.0, 4.0, 5.0)
        val ys = listOf(7.0, 9.0, 11.0, 13.0, 15.0)
        val reg = StatisticsAndDataEngine.analyzeLinearRegression(xs, ys)
        assertNotNull(reg)
        assertEquals(1.0, reg!!.pearsonR, 1e-9)
        assertEquals(1.0, reg.rSquared, 1e-9)
        assertEquals(2.0, reg.slope, 1e-9)
        assertEquals(5.0, reg.intercept, 1e-9)
        assertTrue(reg.methodologicalNote.contains("causalidade", ignoreCase = true))
    }
}

