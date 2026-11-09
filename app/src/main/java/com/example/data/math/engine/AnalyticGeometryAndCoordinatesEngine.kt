package com.example.data.math.engine

import com.example.ui.components.MathExpressionEvaluator
import java.util.Locale
import kotlin.math.*

/**
 * Ponto 2D no plano cartesiano.
 */
data class MathPoint2D(
    val x: Double,
    val y: Double,
    val label: String = ""
) {
    fun distanceTo(other: MathPoint2D): Double = hypot(other.x - x, other.y - y)
    fun midpointTo(other: MathPoint2D): MathPoint2D = MathPoint2D((x + other.x) * 0.5, (y + other.y) * 0.5, "M")
}

/**
 * Vetor 2D com suporte a produto escalar, produto vetorial 2D (determinante), ângulo e projeção ortogonal.
 */
data class MathVector2D(
    val dx: Double,
    val dy: Double,
    val originX: Double = 0.0,
    val originY: Double = 0.0,
    val label: String = "v"
) {
    val magnitude: Double get() = hypot(dx, dy)
    val angleRadians: Double get() = atan2(dy, dx)
    val angleDegrees: Double get() = Math.toDegrees(angleRadians)

    fun normalized(): MathVector2D {
        val m = magnitude
        return if (m < 1e-12) MathVector2D(0.0, 0.0, originX, originY, label) else MathVector2D(dx / m, dy / m, originX, originY, label)
    }

    fun dot(other: MathVector2D): Double = dx * other.dx + dy * other.dy
    fun cross2D(other: MathVector2D): Double = dx * other.dy - dy * other.dx

    fun angleBetweenDegrees(other: MathVector2D): Double {
        val denom = magnitude * other.magnitude
        if (denom < 1e-12) return Double.NaN
        val cosTheta = (dot(other) / denom).coerceIn(-1.0, 1.0)
        return Math.toDegrees(acos(cosTheta))
    }

    fun projectOnto(target: MathVector2D): MathVector2D {
        val targetMagSq = target.dot(target)
        if (targetMagSq < 1e-12) return MathVector2D(0.0, 0.0, originX, originY, "proj")
        val scalar = dot(target) / targetMagSq
        return MathVector2D(target.dx * scalar, target.dy * scalar, originX, originY, "proj")
    }
}

/**
 * Equação analítica da reta a*x + b*y + c = 0.
 */
data class AnalyticLine2D(
    val a: Double,
    val b: Double,
    val c: Double,
    val id: String = "r"
) {
    val isDegenerate: Boolean get() = hypot(a, b) < 1e-10
    val isVertical: Boolean get() = abs(b) < 1e-10 && abs(a) >= 1e-10
    val isHorizontal: Boolean get() = abs(a) < 1e-10 && abs(b) >= 1e-10

    val slope: Double get() = if (isVertical || isDegenerate) Double.NaN else -a / b
    val yIntercept: Double get() = if (isVertical || isDegenerate) Double.NaN else -c / b
    val xIntercept: Double get() = if (isHorizontal || isDegenerate) Double.NaN else -c / a

    fun distanceToPoint(p: MathPoint2D): Double {
        val norm = hypot(a, b)
        if (norm < 1e-10) return Double.NaN
        return abs(a * p.x + b * p.y + c) / norm
    }

    fun parallelThrough(p: MathPoint2D, newId: String = "${id}_par"): AnalyticLine2D {
        val newC = -(a * p.x + b * p.y)
        return AnalyticLine2D(a, b, newC, newId)
    }

    fun perpendicularThrough(p: MathPoint2D, newId: String = "${id}_perp"): AnalyticLine2D {
        // Reta perpendicular a (a, b) possui vetor normal (-b, a) ou (b, -a)
        val perpA = -b
        val perpB = a
        val perpC = -(perpA * p.x + perpB * p.y)
        return AnalyticLine2D(perpA, perpB, perpC, newId)
    }

    fun formattedGeneralEquation(): String {
        if (isDegenerate) return "Reta degenerada (0 = 0)"
        return String.format(Locale.US, "%.2fx %+.2fy %+.2f = 0", a, b, c)
    }

    fun formattedReducedEquation(): String {
        if (isDegenerate) return "Indefinida"
        if (isVertical) return String.format(Locale.US, "x = %.2f", -c / a)
        return String.format(Locale.US, "y = %.2fx %+.2f", slope, yIntercept)
    }

    companion object {
        fun fromTwoPoints(p1: MathPoint2D, p2: MathPoint2D, id: String = "r"): AnalyticLine2D {
            val dx = p2.x - p1.x
            val dy = p2.y - p1.y
            val a = dy
            val b = -dx
            val c = dx * p1.y - dy * p1.x
            return AnalyticLine2D(a, b, c, id)
        }
    }
}

/**
 * Classificação do resultado de interseção geométrica/analítica.
 */
enum class IntersectionStatus(val label: String) {
    NO_INTERSECTION("Ausência de interseção"),
    TANGENT("Tangência (1 ponto)"),
    SINGLE_INTERSECTION("Interseção única (1 ponto)"),
    MULTIPLE_INTERSECTIONS("Interseções múltiplas"),
    COINCIDENT_INFINITE("Objetos coincidentes (infinitos pontos)"),
    DEGENERATE("Caso degenerado")
}

data class IntersectionResult(
    val status: IntersectionStatus,
    val points: List<MathPoint2D>,
    val analyticalSummary: String
)

/**
 * Segmento 2D extraído por Marching Squares para curvas implícitas F(x, y) = 0.
 */
data class ImplicitContourSegment(
    val x1: Double,
    val y1: Double,
    val x2: Double,
    val y2: Double
)

/**
 * Motor de Geometria Analítica, Vetores, Interseções e Sistemas de Coordenadas (Subetapas 3.4 e 3.5).
 */
object AnalyticGeometryAndCoordinatesEngine {

    // =========================================================================
    // 1. INTERSEÇÕES ANALÍTICAS EXATAS E NUMÉRICAS (SUBETAPA 5.1)
    // =========================================================================

    /**
     * Calcula a interseção entre duas retas r1: a1*x + b1*y + c1 = 0 e r2: a2*x + b2*y + c2 = 0.
     */
    fun intersectTwoLines(r1: AnalyticLine2D, r2: AnalyticLine2D): IntersectionResult {
        if (r1.isDegenerate || r2.isDegenerate) {
            return IntersectionResult(IntersectionStatus.DEGENERATE, emptyList(), "Uma das retas é degenerada (coeficientes nulos).")
        }
        val det = r1.a * r2.b - r2.a * r1.b
        if (abs(det) < 1e-10) {
            // Verifica se são coincidentes (mesma distância à origem / proporcionais)
            val norm1 = hypot(r1.a, r1.b)
            val norm2 = hypot(r2.a, r2.b)
            val sign = if (r1.a * r2.a + r1.b * r2.b >= 0) 1.0 else -1.0
            val cDiff = abs(r1.c / norm1 - sign * (r2.c / norm2))
            return if (cDiff < 1e-7) {
                IntersectionResult(
                    status = IntersectionStatus.COINCIDENT_INFINITE,
                    points = emptyList(),
                    analyticalSummary = "Retas coincidentes (mesmo lugar geométrico, infinitas soluções)."
                )
            } else {
                IntersectionResult(
                    status = IntersectionStatus.NO_INTERSECTION,
                    points = emptyList(),
                    analyticalSummary = "Retas estritamente paralelas (determinante = 0, nenhuma interseção)."
                )
            }
        }
        val x = (r1.b * r2.c - r2.b * r1.c) / det
        val y = (r2.a * r1.c - r1.a * r2.c) / det
        val cleanX = if (abs(x) < 1e-10) 0.0 else x
        val cleanY = if (abs(y) < 1e-10) 0.0 else y
        val pt = MathPoint2D(cleanX, cleanY, "P")
        return IntersectionResult(
            status = IntersectionStatus.SINGLE_INTERSECTION,
            points = listOf(pt),
            analyticalSummary = String.format(Locale.US, "Retas concorrentes em P(%.3f, %.3f).", cleanX, cleanY)
        )
    }

    /**
     * Calcula a interseção entre uma reta a*x + b*y + c = 0 e uma circunferência (x - cx)^2 + (y - cy)^2 = r^2.
     */
    fun intersectLineAndCircle(
        line: AnalyticLine2D,
        centerX: Double,
        centerY: Double,
        radius: Double
    ): IntersectionResult {
        if (line.isDegenerate || radius <= 0.0) {
            return IntersectionResult(IntersectionStatus.DEGENERATE, emptyList(), "Reta degenerada ou raio não positivo.")
        }
        val normSq = line.a * line.a + line.b * line.b
        val norm = sqrt(normSq)
        // Translada para o centro da circunferência: a*(u + cx) + b*(v + cy) + c = 0 => a*u + b*v + d0 = 0
        val d0 = line.a * centerX + line.b * centerY + line.c
        val dist = abs(d0) / norm

        if (dist > radius + 1e-7) {
            return IntersectionResult(
                status = IntersectionStatus.NO_INTERSECTION,
                points = emptyList(),
                analyticalSummary = String.format(Locale.US, "Reta exterior à circunferência (distância d = %.3f > r = %.3f).", dist, radius)
            )
        }

        // Ponto mais próximo do centro sobre a reta
        val u0 = -line.a * d0 / normSq
        val v0 = -line.b * d0 / normSq

        if (abs(dist - radius) <= 1e-7) {
            val pt = MathPoint2D(centerX + u0, centerY + v0, "T")
            return IntersectionResult(
                status = IntersectionStatus.TANGENT,
                points = listOf(pt),
                analyticalSummary = String.format(Locale.US, "Reta tangente à circunferência em T(%.3f, %.3f).", pt.x, pt.y)
            )
        }

        val h = sqrt(maxOf(0.0, radius * radius - d0 * d0 / normSq))
        val mult = sqrt(h * h / normSq)
        val p1 = MathPoint2D(centerX + u0 + line.b * mult, centerY + v0 - line.a * mult, "I1")
        val p2 = MathPoint2D(centerX + u0 - line.b * mult, centerY + v0 + line.a * mult, "I2")
        val sorted = listOf(p1, p2).sortedWith(compareBy<MathPoint2D> { it.x }.thenBy { it.y })
        return IntersectionResult(
            status = IntersectionStatus.MULTIPLE_INTERSECTIONS,
            points = sorted,
            analyticalSummary = String.format(
                Locale.US,
                "Reta secante em 2 pontos: I₁(%.3f, %.3f) e I₂(%.3f, %.3f).",
                sorted[0].x, sorted[0].y, sorted[1].x, sorted[1].y
            )
        )
    }

    /**
     * Calcula a interseção entre duas circunferências C1(x1, y1, r1) e C2(x2, y2, r2).
     */
    fun intersectTwoCircles(
        x1: Double, y1: Double, r1: Double,
        x2: Double, y2: Double, r2: Double
    ): IntersectionResult {
        if (r1 <= 0.0 || r2 <= 0.0) {
            return IntersectionResult(IntersectionStatus.DEGENERATE, emptyList(), "Raios devem ser estritamente positivos.")
        }
        val dx = x2 - x1
        val dy = y2 - y1
        val d = hypot(dx, dy)

        if (d < 1e-9 && abs(r1 - r2) < 1e-9) {
            return IntersectionResult(
                status = IntersectionStatus.COINCIDENT_INFINITE,
                points = emptyList(),
                analyticalSummary = "Circunferências concêntricas e de mesmo raio (infinitos pontos em comum)."
            )
        }
        if (d < 1e-9) {
            return IntersectionResult(
                status = IntersectionStatus.NO_INTERSECTION,
                points = emptyList(),
                analyticalSummary = "Circunferências concêntricas de raios distintos (nenhuma interseção)."
            )
        }
        if (d > r1 + r2 + 1e-7 || d < abs(r1 - r2) - 1e-7) {
            return IntersectionResult(
                status = IntersectionStatus.NO_INTERSECTION,
                points = emptyList(),
                analyticalSummary = String.format(Locale.US, "Sem interseção (distância entre centros d = %.3f).", d)
            )
        }

        val a = (r1 * r1 - r2 * r2 + d * d) / (2.0 * d)
        val hSq = r1 * r1 - a * a
        val xm = x1 + a * dx / d
        val ym = y1 + a * dy / d

        if (abs(hSq) <= 1e-7) {
            val pt = MathPoint2D(xm, ym, "T")
            return IntersectionResult(
                status = IntersectionStatus.TANGENT,
                points = listOf(pt),
                analyticalSummary = String.format(Locale.US, "Circunferências tangentes em T(%.3f, %.3f).", pt.x, pt.y)
            )
        }

        val h = sqrt(maxOf(0.0, hSq))
        val rx = -dy * (h / d)
        val ry = dx * (h / d)
        val p1 = MathPoint2D(xm + rx, ym + ry, "I1")
        val p2 = MathPoint2D(xm - rx, ym - ry, "I2")
        val sorted = listOf(p1, p2).sortedWith(compareBy<MathPoint2D> { it.x }.thenBy { it.y })
        return IntersectionResult(
            status = IntersectionStatus.MULTIPLE_INTERSECTIONS,
            points = sorted,
            analyticalSummary = String.format(
                Locale.US,
                "Circunferências secantes em I₁(%.3f, %.3f) e I₂(%.3f, %.3f).",
                sorted[0].x, sorted[0].y, sorted[1].x, sorted[1].y
            )
        )
    }

    /**
     * Encontra interseções numéricas entre duas funções f(x) e g(x) no intervalo [xMin, xMax].
     */
    fun intersectTwoFunctions(
        fExpr: String,
        gExpr: String,
        xMin: Double,
        xMax: Double
    ): IntersectionResult {
        val diffExpr = "($fExpr) - ($gExpr)"
        val roots = MathExpressionEvaluator.findRoots(diffExpr, xMin, xMax)
        val compiledF = MathExpressionEvaluator.compile(fExpr)
        val points = roots.mapIndexedNotNull { idx, rx ->
            val ry = compiledF.evaluate(rx)
            if (ry.isFinite()) MathPoint2D(rx, ry, "I${idx + 1}") else null
        }
        return when {
            points.isEmpty() -> IntersectionResult(
                status = IntersectionStatus.NO_INTERSECTION,
                points = emptyList(),
                analyticalSummary = "Nenhuma interseção real encontrada no intervalo investigado."
            )
            points.size == 1 -> IntersectionResult(
                status = IntersectionStatus.SINGLE_INTERSECTION,
                points = points,
                analyticalSummary = String.format(Locale.US, "1 ponto de interseção em (%.3f, %.3f).", points[0].x, points[0].y)
            )
            else -> IntersectionResult(
                status = IntersectionStatus.MULTIPLE_INTERSECTIONS,
                points = points,
                analyticalSummary = "${points.size} pontos de interseção encontrados no intervalo."
            )
        }
    }

    // =========================================================================
    // 2. SISTEMAS DE COORDENADAS: POLARES, PARAMÉTRICAS E IMPLÍCITAS (SEÇÃO 6)
    // =========================================================================

    /**
     * Normaliza expressões em função de θ (theta) ou t para a variável padrão 'x' do compilador AST.
     */
    fun normalizeParameterVariable(rawExpr: String): String {
        return rawExpr
            .replace("theta", "x", ignoreCase = true)
            .replace("θ", "x")
            .replace(Regex("""\bt\b"""), "x")
    }

    /**
     * Gera polilinhas cartesianas (x, y) = (r(θ) cos θ, r(θ) sin θ) para uma curva polar r = f(θ).
     * Respeita descontinuidades quebrando em sub-trajetórias quando r(θ) não é finito.
     */
    fun samplePolarCurve(
        rExpressionOfTheta: String,
        thetaMin: Double = 0.0,
        thetaMax: Double = 2.0 * PI,
        steps: Int = 360
    ): List<List<MathPoint2D>> {
        val normalized = normalizeParameterVariable(rExpressionOfTheta)
        val compiled = MathExpressionEvaluator.compile(normalized)
        if (!compiled.isValid || thetaMax <= thetaMin || steps <= 2) return emptyList()

        val polylines = mutableListOf<MutableList<MathPoint2D>>()
        var currentLine = mutableListOf<MathPoint2D>()
        val dTheta = (thetaMax - thetaMin) / steps

        for (i in 0..steps) {
            val theta = thetaMin + i * dTheta
            val r = compiled.evaluate(theta)
            if (r.isFinite() && abs(r) < 1e4) {
                val x = r * cos(theta)
                val y = r * sin(theta)
                currentLine.add(MathPoint2D(x, y))
            } else if (currentLine.isNotEmpty()) {
                if (currentLine.size >= 2) polylines.add(currentLine)
                currentLine = mutableListOf()
            }
        }
        if (currentLine.size >= 2) polylines.add(currentLine)
        return polylines
    }

    /**
     * Gera polilinhas cartesianas para uma curva paramétrica x = f(t), y = g(t) em t ∈ [tMin, tMax].
     */
    fun sampleParametricCurve(
        xExpressionOfT: String,
        yExpressionOfT: String,
        tMin: Double = 0.0,
        tMax: Double = 2.0 * PI,
        steps: Int = 360
    ): List<List<MathPoint2D>> {
        val compiledX = MathExpressionEvaluator.compile(normalizeParameterVariable(xExpressionOfT))
        val compiledY = MathExpressionEvaluator.compile(normalizeParameterVariable(yExpressionOfT))
        if (!compiledX.isValid || !compiledY.isValid || tMax <= tMin || steps <= 2) return emptyList()

        val polylines = mutableListOf<MutableList<MathPoint2D>>()
        var currentLine = mutableListOf<MathPoint2D>()
        val dt = (tMax - tMin) / steps

        for (i in 0..steps) {
            val t = tMin + i * dt
            val x = compiledX.evaluate(t)
            val y = compiledY.evaluate(t)
            if (x.isFinite() && y.isFinite() && abs(x) < 1e4 && abs(y) < 1e4) {
                currentLine.add(MathPoint2D(x, y))
            } else if (currentLine.isNotEmpty()) {
                if (currentLine.size >= 2) polylines.add(currentLine)
                currentLine = mutableListOf()
            }
        }
        if (currentLine.size >= 2) polylines.add(currentLine)
        return polylines
    }

    /**
     * Extrai segmentos de contorno para uma curva implícita F(x, y) = 0 (ou "lhs = rhs" -> lhs - rhs = 0)
     * utilizando o algoritmo de Marching Squares com interpolação linear nas arestas da malha.
     */
    fun sampleImplicitCurveMarchingSquares(
        implicitEquation: String,
        xMin: Double,
        xMax: Double,
        yMin: Double,
        yMax: Double,
        gridResolution: Int = 48
    ): List<ImplicitContourSegment> {
        val safeRes = gridResolution.coerceIn(12, 96)
        if (xMax <= xMin || yMax <= yMin) return emptyList()

        // Converte "x^2 + y^2 = 9" em "(x^2 + y^2) - (9)"
        val cleanEq = implicitEquation.trim()
        val fxyTemplate = if (cleanEq.contains("=") && !cleanEq.contains("==") && !cleanEq.contains("<=") && !cleanEq.contains(">=")) {
            val parts = cleanEq.split("=", limit = 2)
            "(${parts[0]}) - (${parts[1]})"
        } else {
            cleanEq
        }

        val dx = (xMax - xMin) / safeRes
        val dy = (yMax - yMin) / safeRes

        // Avalia a malha (safeRes + 1) x (safeRes + 1) substituindo y por cada valor da linha
        val grid = Array(safeRes + 1) { DoubleArray(safeRes + 1) }
        for (row in 0..safeRes) {
            val yVal = yMin + row * dy
            val yStr = String.format(Locale.US, "(%.8f)", yVal)
            val exprForRow = fxyTemplate.replace(Regex("""\by\b"""), yStr)
            val compiledRow = MathExpressionEvaluator.compile(exprForRow)
            for (col in 0..safeRes) {
                val xVal = xMin + col * dx
                grid[row][col] = compiledRow.evaluate(xVal)
            }
        }

        val segments = mutableListOf<ImplicitContourSegment>()

        fun interp(v1: Double, v2: Double): Double {
            val denom = v1 - v2
            if (abs(denom) < 1e-12) return 0.5
            return (v1 / denom).coerceIn(0.0, 1.0)
        }

        for (row in 0 until safeRes) {
            val y0 = yMin + row * dy
            val y1 = y0 + dy
            for (col in 0 until safeRes) {
                val x0 = xMin + col * dx
                val x1 = x0 + dx

                val v00 = grid[row][col]         // bottom-left
                val v10 = grid[row][col + 1]     // bottom-right
                val v11 = grid[row + 1][col + 1] // top-right
                val v01 = grid[row + 1][col]     // top-left

                if (!v00.isFinite() || !v10.isFinite() || !v11.isFinite() || !v01.isFinite()) continue

                var mask = 0
                if (v00 >= 0.0) mask = mask or 1
                if (v10 >= 0.0) mask = mask or 2
                if (v11 >= 0.0) mask = mask or 4
                if (v01 >= 0.0) mask = mask or 8
                if (mask == 0 || mask == 15) continue

                // Pontos interpolados nas 4 arestas: bottom (e0), right (e1), top (e2), left (e3)
                val eBottomX = x0 + interp(v00, v10) * dx
                val eBottomY = y0

                val eRightX = x1
                val eRightY = y0 + interp(v10, v11) * dy

                val eTopX = x0 + interp(v01, v11) * dx
                val eTopY = y1

                val eLeftX = x0
                val eLeftY = y0 + interp(v00, v01) * dy

                when (mask) {
                    1, 14 -> segments.add(ImplicitContourSegment(eLeftX, eLeftY, eBottomX, eBottomY))
                    2, 13 -> segments.add(ImplicitContourSegment(eBottomX, eBottomY, eRightX, eRightY))
                    3, 12 -> segments.add(ImplicitContourSegment(eLeftX, eLeftY, eRightX, eRightY))
                    4, 11 -> segments.add(ImplicitContourSegment(eTopX, eTopY, eRightX, eRightY))
                    5 -> {
                        segments.add(ImplicitContourSegment(eLeftX, eLeftY, eTopX, eTopY))
                        segments.add(ImplicitContourSegment(eBottomX, eBottomY, eRightX, eRightY))
                    }
                    6, 9 -> segments.add(ImplicitContourSegment(eBottomX, eBottomY, eTopX, eTopY))
                    7, 8 -> segments.add(ImplicitContourSegment(eLeftX, eLeftY, eTopX, eTopY))
                    10 -> {
                        segments.add(ImplicitContourSegment(eLeftX, eLeftY, eBottomX, eBottomY))
                        segments.add(ImplicitContourSegment(eTopX, eTopY, eRightX, eRightY))
                    }
                }
            }
        }
        return segments
    }
}

