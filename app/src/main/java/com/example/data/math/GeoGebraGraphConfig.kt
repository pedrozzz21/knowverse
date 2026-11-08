package com.example.data.math

/**
 * Ponto notável ou de interesse matemático a ser plotado no gráfico (ex: buracos de descontinuidade removível,
 * raízes, máximos/mínimos, pontos de inflexão ou ponto de tangência).
 */
data class GeoGebraPoint(
    val name: String,
    val x: Double,
    val y: Double,
    val label: String,
    val isOpenCircle: Boolean = false,
    val colorHex: String = "#EF4444"
)

/**
 * Primitivas geométricas e analíticas extraídas de GeoGebraGraphConfig.commands.
 * Permite renderizar polígonos, segmentos, retas gerais, assíntotas, cônicas fechadas e funções extras
 * declaradas nos presets de Cálculo e nos exercícios de Geometria Analítica.
 */
sealed interface GeoGebraCommandPrimitive {
    data class NamedPoint(val id: String, val x: Double, val y: Double, val colorHex: String = "#38BDF8") : GeoGebraCommandPrimitive
    data class VerticalLine(val id: String, val x: Double, val colorHex: String = "#EF4444", val isDashed: Boolean = true) : GeoGebraCommandPrimitive
    data class HorizontalLine(val id: String, val y: Double, val colorHex: String = "#EAB308", val isDashed: Boolean = true) : GeoGebraCommandPrimitive
    data class GeneralLine(val id: String, val a: Double, val b: Double, val c: Double, val colorHex: String = "#38BDF8", val isDashed: Boolean = false) : GeoGebraCommandPrimitive
    data class Segment2D(val id: String, val x1: Double, val y1: Double, val x2: Double, val y2: Double, val colorHex: String = "#EAB308", val isDashed: Boolean = false) : GeoGebraCommandPrimitive
    data class Polygon2D(val id: String, val vertices: List<Pair<Double, Double>>, val colorHex: String = "#10B981") : GeoGebraCommandPrimitive
    data class Circle2D(val id: String, val centerX: Double, val centerY: Double, val radius: Double, val colorHex: String = "#38BDF8", val isDashed: Boolean = false) : GeoGebraCommandPrimitive
    data class Ellipse2D(val id: String, val centerX: Double, val centerY: Double, val rx: Double, val ry: Double, val colorHex: String = "#3B82F6") : GeoGebraCommandPrimitive
    data class ExtraFunction(val id: String, val expression: String, val colorHex: String = "#C084FC", val isDashed: Boolean = false) : GeoGebraCommandPrimitive
}

/**
 * Interpretador determinístico dos comandos declarados em GeoGebraCurriculumPresets e GeoGebraGeometryExercises.
 */
object GeoGebraCommandParser {

    fun parse(commands: List<String>, primaryExpression: String): List<GeoGebraCommandPrimitive> {
        if (commands.isEmpty()) return emptyList()

        val pointsMap = mutableMapOf<String, Pair<Double, Double>>()
        val colorsMap = mutableMapOf<String, String>()
        val dashedSet = mutableSetOf<String>()
        val rawPrimitives = mutableListOf<GeoGebraCommandPrimitive>()

        val normalizedPrimary = primaryExpression.replace(" ", "")

        // 1ª Passada: Coletar estilos SetColor(id, r, g, b), SetLineStyle(id, style) e pontos nomeados P = (x, y)
        for (rawCmd in commands) {
            val cmd = rawCmd.trim()
            if (cmd.isEmpty()) continue

            // SetColor(obj, r, g, b)
            val colorMatch = Regex("""^SetColor\(\s*([A-Za-z0-9_]+)\s*,\s*(\d+)\s*,\s*(\d+)\s*,\s*(\d+)\s*\)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (colorMatch != null) {
                val id = colorMatch.groupValues[1]
                val r = colorMatch.groupValues[2].toIntOrNull()?.coerceIn(0, 255) ?: 56
                val g = colorMatch.groupValues[3].toIntOrNull()?.coerceIn(0, 255) ?: 189
                val b = colorMatch.groupValues[4].toIntOrNull()?.coerceIn(0, 255) ?: 248
                colorsMap[id] = String.format("#%02X%02X%02X", r, g, b)
                continue
            }

            // SetLineStyle(obj, style) -> style > 0 é tracejado
            val styleMatch = Regex("""^SetLineStyle\(\s*([A-Za-z0-9_]+)\s*,\s*(\d+)\s*\)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (styleMatch != null) {
                val id = styleMatch.groupValues[1]
                val st = styleMatch.groupValues[2].toIntOrNull() ?: 0
                if (st > 0) dashedSet.add(id)
                continue
            }

            // Ponto nomeado: A = (2, 4) ou P1 = (pi/4, 3)
            val ptMatch = Regex("""^([A-Za-z][A-Za-z0-9_]*)\s*=\s*\(\s*([^,]+)\s*,\s*([^)]+)\s*\)$""").find(cmd)
            if (ptMatch != null) {
                val id = ptMatch.groupValues[1]
                val xVal = parseScalarValue(ptMatch.groupValues[2])
                val yVal = parseScalarValue(ptMatch.groupValues[3])
                if (xVal != null && yVal != null) {
                    pointsMap[id] = xVal to yVal
                }
            }
        }

        // 2ª Passada: Construir primitivas geométricas e analíticas
        for (rawCmd in commands) {
            val cmd = rawCmd.trim()
            if (cmd.startsWith("Set", ignoreCase = true) || cmd.startsWith("Integral(", ignoreCase = true)) continue

            // 1. Polygon(A, B, C, ...)
            val polyMatch = Regex("""^(?:([A-Za-z0-9_]+)\s*=\s*)?Polygon\(\s*([^)]+)\s*\)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (polyMatch != null) {
                val id = polyMatch.groupValues[1].ifEmpty { "poly" }
                val ptNames = polyMatch.groupValues[2].split(",").map { it.trim() }
                val vertices = ptNames.mapNotNull { pointsMap[it] }
                if (vertices.size >= 3) {
                    rawPrimitives.add(
                        GeoGebraCommandPrimitive.Polygon2D(
                            id = id,
                            vertices = vertices,
                            colorHex = colorsMap[id] ?: "#10B981"
                        )
                    )
                }
                continue
            }

            // 2. Segment(P1, P2) onde P1/P2 podem ser nomes de pontos ou coordenadas (x, y)
            val segMatch = Regex("""^(?:([A-Za-z0-9_]+)\s*=\s*)?Segment\(\s*(.+)\s*\)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (segMatch != null) {
                val id = segMatch.groupValues[1].ifEmpty { "seg" }
                val ends = splitTopLevelArgs(segMatch.groupValues[2])
                if (ends.size == 2) {
                    val p1 = resolvePointArg(ends[0], pointsMap)
                    val p2 = resolvePointArg(ends[1], pointsMap)
                    if (p1 != null && p2 != null) {
                        rawPrimitives.add(
                            GeoGebraCommandPrimitive.Segment2D(
                                id = id,
                                x1 = p1.first,
                                y1 = p1.second,
                                x2 = p2.first,
                                y2 = p2.second,
                                colorHex = colorsMap[id] ?: "#EAB308",
                                isDashed = id in dashedSet
                            )
                        )
                    }
                }
                continue
            }

            // 3. Line(P1, P2) -> reta infinita passando por P1 e P2
            val lineMatch = Regex("""^(?:([A-Za-z0-9_]+)\s*=\s*)?Line\(\s*(.+)\s*\)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (lineMatch != null) {
                val id = lineMatch.groupValues[1].ifEmpty { "line" }
                val ends = splitTopLevelArgs(lineMatch.groupValues[2])
                if (ends.size == 2) {
                    val p1 = resolvePointArg(ends[0], pointsMap)
                    val p2 = resolvePointArg(ends[1], pointsMap)
                    if (p1 != null && p2 != null) {
                        val dx = p2.first - p1.first
                        val dy = p2.second - p1.second
                        if (kotlin.math.abs(dx) < 1e-7) {
                            rawPrimitives.add(
                                GeoGebraCommandPrimitive.VerticalLine(
                                    id = id,
                                    x = p1.first,
                                    colorHex = colorsMap[id] ?: "#EF4444",
                                    isDashed = id in dashedSet || true
                                )
                            )
                        } else if (kotlin.math.abs(dy) < 1e-7) {
                            rawPrimitives.add(
                                GeoGebraCommandPrimitive.HorizontalLine(
                                    id = id,
                                    y = p1.second,
                                    colorHex = colorsMap[id] ?: "#EAB308",
                                    isDashed = id in dashedSet || true
                                )
                            )
                        } else {
                            // a*x + b*y + c = 0 => dy*x - dx*y + (dx*y1 - dy*x1) = 0
                            val a = dy
                            val b = -dx
                            val c = dx * p1.second - dy * p1.first
                            rawPrimitives.add(
                                GeoGebraCommandPrimitive.GeneralLine(
                                    id = id,
                                    a = a,
                                    b = b,
                                    c = c,
                                    colorHex = colorsMap[id] ?: "#38BDF8",
                                    isDashed = id in dashedSet
                                )
                            )
                        }
                    }
                }
                continue
            }

            // 4. Circunferência canônica: c: x^2 + y^2 = R2 ou circ = x^2 + y^2 = R2
            val circleMatch = Regex("""^(?:([A-Za-z0-9_]+)\s*[:=]\s*)?x\^2\s*\+\s*y\^2\s*=\s*([0-9.]+)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (circleMatch != null) {
                val id = circleMatch.groupValues[1].ifEmpty { "circle" }
                val rSq = circleMatch.groupValues[2].toDoubleOrNull()
                if (rSq != null && rSq > 0) {
                    rawPrimitives.add(
                        GeoGebraCommandPrimitive.Circle2D(
                            id = id,
                            centerX = 0.0,
                            centerY = 0.0,
                            radius = kotlin.math.sqrt(rSq),
                            colorHex = colorsMap[id] ?: "#38BDF8",
                            isDashed = id in dashedSet
                        )
                    )
                }
                continue
            }

            // 5. Elipse canônica: el: x^2/A2 + y^2/B2 = 1 ou elip = x^2/25 + y^2/9 = 1
            val ellipseMatch = Regex("""^(?:([A-Za-z0-9_]+)\s*[:=]\s*)?x\^2\s*/\s*([0-9.]+)\s*\+\s*y\^2\s*/\s*([0-9.]+)\s*=\s*1$""", RegexOption.IGNORE_CASE).find(cmd)
            if (ellipseMatch != null) {
                val id = ellipseMatch.groupValues[1].ifEmpty { "ellipse" }
                val aSq = ellipseMatch.groupValues[2].toDoubleOrNull()
                val bSq = ellipseMatch.groupValues[3].toDoubleOrNull()
                if (aSq != null && bSq != null && aSq > 0 && bSq > 0) {
                    rawPrimitives.add(
                        GeoGebraCommandPrimitive.Ellipse2D(
                            id = id,
                            centerX = 0.0,
                            centerY = 0.0,
                            rx = kotlin.math.sqrt(aSq),
                            ry = kotlin.math.sqrt(bSq),
                            colorHex = colorsMap[id] ?: "#3B82F6"
                        )
                    )
                }
                continue
            }

            // 6. Reta vertical com ou sem rótulo: "v: x = 3", "av: x = 1", "x = 3"
            val vLineMatch = Regex("""^(?:([A-Za-z0-9_]+)\s*:\s*)?x\s*=\s*([-+]?[0-9]*\.?[0-9]+)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (vLineMatch != null) {
                val id = vLineMatch.groupValues[1].ifEmpty { "vline" }
                val xVal = vLineMatch.groupValues[2].toDoubleOrNull()
                if (xVal != null) {
                    rawPrimitives.add(
                        GeoGebraCommandPrimitive.VerticalLine(
                            id = id,
                            x = xVal,
                            colorHex = colorsMap[id] ?: "#EF4444",
                            isDashed = true
                        )
                    )
                }
                continue
            }

            // 7. Reta horizontal com ou sem rótulo: "h: y = 2", "eLine: y = 2.71828", "y = 2"
            val hLineMatch = Regex("""^(?:([A-Za-z0-9_]+)\s*:\s*)?y\s*=\s*([-+]?[0-9]*\.?[0-9]+)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (hLineMatch != null) {
                val id = hLineMatch.groupValues[1].ifEmpty { "hline" }
                val yVal = hLineMatch.groupValues[2].toDoubleOrNull()
                if (yVal != null) {
                    rawPrimitives.add(
                        GeoGebraCommandPrimitive.HorizontalLine(
                            id = id,
                            y = yVal,
                            colorHex = colorsMap[id] ?: "#EAB308",
                            isDashed = true
                        )
                    )
                }
                continue
            }

            // 8. Reta explícita com rótulo: "t: y = 9*x - 16"
            val explicitLineMatch = Regex("""^([A-Za-z0-9_]+)\s*:\s*y\s*=\s*(.+)$""", RegexOption.IGNORE_CASE).find(cmd)
            if (explicitLineMatch != null) {
                val id = explicitLineMatch.groupValues[1]
                val expr = explicitLineMatch.groupValues[2].trim()
                if (expr.replace(" ", "") != normalizedPrimary) {
                    rawPrimitives.add(
                        GeoGebraCommandPrimitive.ExtraFunction(
                            id = id,
                            expression = expr,
                            colorHex = colorsMap[id] ?: "#10B981",
                            isDashed = id in dashedSet
                        )
                    )
                }
                continue
            }

            // 9. Equação linear geral: "r: 3*x + 4*y - 12 = 0"
            val generalLineMatch = Regex("""^(?:([A-Za-z0-9_]+)\s*:\s*)?([-+]?[0-9.]*)\*?x\s*([+-]\s*[0-9.]*)\*?y\s*([+-]\s*[0-9.]+)\s*=\s*0$""", RegexOption.IGNORE_CASE).find(cmd)
            if (generalLineMatch != null) {
                val id = generalLineMatch.groupValues[1].ifEmpty { "gline" }
                val aStr = generalLineMatch.groupValues[2].replace(" ", "")
                val bStr = generalLineMatch.groupValues[3].replace(" ", "")
                val cStr = generalLineMatch.groupValues[4].replace(" ", "")
                val a = if (aStr.isEmpty() || aStr == "+") 1.0 else if (aStr == "-") -1.0 else aStr.toDoubleOrNull() ?: 0.0
                val b = if (bStr == "+") 1.0 else if (bStr == "-") -1.0 else bStr.toDoubleOrNull() ?: 0.0
                val c = cStr.toDoubleOrNull() ?: 0.0
                if (kotlin.math.abs(a) > 1e-7 || kotlin.math.abs(b) > 1e-7) {
                    rawPrimitives.add(
                        GeoGebraCommandPrimitive.GeneralLine(
                            id = id,
                            a = a,
                            b = b,
                            c = c,
                            colorHex = colorsMap[id] ?: "#38BDF8",
                            isDashed = id in dashedSet
                        )
                    )
                }
                continue
            }

            // 10. Função nomeada: "g(x) = cos(x)", "h(x) = 1", "S(x) = 3*x + 5"
            val funcMatch = Regex("""^([A-Za-z][A-Za-z0-9_]*)\(x\)\s*=\s*(.+)$""").find(cmd)
            if (funcMatch != null) {
                val id = funcMatch.groupValues[1]
                val expr = funcMatch.groupValues[2].trim()
                if (expr.replace(" ", "") != normalizedPrimary && id != "f") {
                    rawPrimitives.add(
                        GeoGebraCommandPrimitive.ExtraFunction(
                            id = id,
                            expression = expr,
                            colorHex = colorsMap[id] ?: "#C084FC",
                            isDashed = id in dashedSet
                        )
                    )
                }
            }
        }

        return rawPrimitives
    }

    private fun parseScalarValue(raw: String): Double? {
        val s = raw.trim().lowercase()
            .replace("pi", kotlin.math.PI.toString())
            .replace("π", kotlin.math.PI.toString())
        s.toDoubleOrNull()?.let { return it }
        // Suporta frações simples como 3*pi/4 ou pi/4
        if (s.contains("/")) {
            val parts = s.split("/")
            if (parts.size == 2) {
                val num = parseProductValue(parts[0])
                val den = parseProductValue(parts[1])
                if (num != null && den != null && kotlin.math.abs(den) > 1e-12) return num / den
            }
        }
        return parseProductValue(s)
    }

    private fun parseProductValue(raw: String): Double? {
        val parts = raw.split("*")
        var prod = 1.0
        for (p in parts) {
            val v = p.trim().toDoubleOrNull() ?: return null
            prod *= v
        }
        return prod
    }

    private fun splitTopLevelArgs(argsStr: String): List<String> {
        val result = mutableListOf<String>()
        var depth = 0
        var lastStart = 0
        for (i in argsStr.indices) {
            when (argsStr[i]) {
                '(' -> depth++
                ')' -> depth--
                ',' -> if (depth == 0) {
                    result.add(argsStr.substring(lastStart, i).trim())
                    lastStart = i + 1
                }
            }
        }
        if (lastStart < argsStr.length) {
            result.add(argsStr.substring(lastStart).trim())
        }
        return result
    }

    private fun resolvePointArg(arg: String, pointsMap: Map<String, Pair<Double, Double>>): Pair<Double, Double>? {
        val trimmed = arg.trim()
        pointsMap[trimmed]?.let { return it }
        val inlineMatch = Regex("""^\(\s*([^,]+)\s*,\s*([^)]+)\s*\)$""").find(trimmed)
        if (inlineMatch != null) {
            val x = parseScalarValue(inlineMatch.groupValues[1])
            val y = parseScalarValue(inlineMatch.groupValues[2])
            if (x != null && y != null) return x to y
        }
        return null
    }
}

/**
 * Configuração analítica do Visualizador Matemático Interativo do Knowverse.
 * Fornece parâmetros completos para renderização vetorial de alta precisão em Jetpack Compose Canvas,
 * permitindo manipulação dinâmica de funções, zoom contínuo, tangentes, integrais e descontinuidades.
 */
data class GeoGebraGraphConfig(
    val title: String,
    val functionExpression: String,
    val commands: List<String> = emptyList(),
    val xMin: Double = -5.0,
    val xMax: Double = 5.0,
    val yMin: Double = -5.0,
    val yMax: Double = 5.0,
    val didacticExplanation: String,
    val mathInsight: String = "",
    val analysisPoints: List<GeoGebraPoint> = emptyList(),
    val presetFunctions: List<String> = emptyList(),
    val showTangentSlider: Boolean = false,
    val showAsymptotes: Boolean = false,
    val showAreaUnderCurve: Boolean = false,
    val integralA: Double = 0.0,
    val integralB: Double = 2.0,
    // Recursos Avançados Expandidos (100% retrocompatíveis)
    val secondaryFunctionExpression: String? = null,
    val showRiemannSum: Boolean = false,
    val riemannPartitions: Int = 8,
    val showRoots: Boolean = false,
    val showExtrema: Boolean = false
)
