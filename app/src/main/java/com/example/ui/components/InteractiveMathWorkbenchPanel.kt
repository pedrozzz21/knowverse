package com.example.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.math.engine.*
import com.example.ui.theme.*
import java.util.Locale
import kotlin.math.*

/**
 * Função adicional inserida pelo usuário na Calculadora Gráfica Multi-Função.
 */
data class UserPlottedFunction(
    val id: String,
    val label: String,
    val expression: String,
    val colorHex: String,
    val isVisible: Boolean = true
)

enum class MathWorkbenchTab(val label: String) {
    MULTI_FUNC("Funções & Derivada"),
    ROOTS_ASYMPTOTES("Raízes & Assíntotas"),
    INTEGRALS("Integrais"),
    GUIDED_PARAMS("Exploração Guiada"),
    COORDINATES("Polar / Param / Implícita"),
    GEOMETRY_VECTORS("Geometria & Vetores"),
    STATISTICS("Estatística & Dados")
}

private enum class NonCartesianMode(val label: String) {
    POLAR("Polar r = f(θ)"),
    PARAMETRIC("Paramétrica (x(t), y(t))"),
    IMPLICIT("Implícita F(x,y) = 0")
}

/**
 * Painel Modular de Ferramentas Matemáticas Interativas da Etapa 3.
 * Integra-se ao [GeoGebraMathGraphView] sem sobrecarregar o componente principal.
 */
@Composable
fun InteractiveMathWorkbenchPanel(
    primaryExpression: String,
    xMin: Double,
    xMax: Double,
    yMin: Double,
    yMax: Double,
    extraFunctions: List<UserPlottedFunction>,
    showDerivativeCurve: Boolean,
    onToggleDerivativeCurve: (Boolean) -> Unit,
    onAddExtraFunction: (String, String) -> Unit,
    onToggleExtraFunctionVisibility: (String) -> Unit,
    onRemoveExtraFunction: (String) -> Unit,
    onApplyExpressionToPrimary: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(false) }
    var activeTab by remember { mutableStateOf(MathWorkbenchTab.MULTI_FUNC) }

    Column(modifier = modifier.fillMaxWidth()) {
        // Barra de abertura do Laboratório Avançado (Etapa 3)
        Surface(
            color = ObsidianSurface,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, if (isExpanded) PrimaryViolet else BorderSubtle),
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isExpanded = !isExpanded }
                .testTag("math_workbench_toggle_bar")
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 9.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Box(
                        modifier = Modifier
                            .size(8.dp)
                            .clip(CircleShape)
                            .background(if (isExpanded) SuccessEmerald else PrimaryVioletLight)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = "Laboratório Matemático & Científico Interativo",
                            style = MaterialTheme.typography.labelLarge,
                            color = TextPrimary,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "Múltiplas funções, Derivada Simbólica, Integrais, Polares, Geometria e Estatística",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary,
                            fontSize = 11.sp
                        )
                    }
                }
                Text(
                    text = if (isExpanded) "Ocultar" else "Expandir",
                    style = MaterialTheme.typography.labelMedium,
                    color = PrimaryVioletLight,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        AnimatedVisibility(visible = isExpanded) {
            Card(
                colors = CardDefaults.cardColors(containerColor = ObsidianSurface),
                shape = RoundedCornerShape(10.dp),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                Column(modifier = Modifier.padding(12.dp)) {
                    // Seletor de Abas da Etapa 3
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .horizontalScroll(rememberScrollState()),
                        horizontalArrangement = Arrangement.spacedBy(6.dp)
                    ) {
                        MathWorkbenchTab.entries.forEach { tab ->
                            val selected = activeTab == tab
                            Surface(
                                color = if (selected) PrimaryViolet.copy(alpha = 0.22f) else SurfaceElevated,
                                shape = RoundedCornerShape(6.dp),
                                border = BorderStroke(1.dp, if (selected) PrimaryViolet else BorderSubtle),
                                modifier = Modifier.clickable { activeTab = tab }
                            ) {
                                Text(
                                    text = tab.label,
                                    style = MaterialTheme.typography.labelSmall,
                                    color = if (selected) TextPrimary else TextSecondary,
                                    fontWeight = if (selected) FontWeight.Bold else FontWeight.Medium,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(10.dp))

                    when (activeTab) {
                        MathWorkbenchTab.MULTI_FUNC -> MultiFunctionAndDerivativeTab(
                            primaryExpression = primaryExpression,
                            xMin = xMin,
                            xMax = xMax,
                            extraFunctions = extraFunctions,
                            showDerivativeCurve = showDerivativeCurve,
                            onToggleDerivativeCurve = onToggleDerivativeCurve,
                            onAddExtraFunction = onAddExtraFunction,
                            onToggleExtraFunctionVisibility = onToggleExtraFunctionVisibility,
                            onRemoveExtraFunction = onRemoveExtraFunction
                        )
                        MathWorkbenchTab.ROOTS_ASYMPTOTES -> RootsAndBehaviorTab(
                            primaryExpression = primaryExpression,
                            xMin = xMin,
                            xMax = xMax
                        )
                        MathWorkbenchTab.INTEGRALS -> InteractiveIntegralTab(
                            primaryExpression = primaryExpression,
                            defaultA = maxOf(xMin, -2.0),
                            defaultB = minOf(xMax, 2.0)
                        )
                        MathWorkbenchTab.GUIDED_PARAMS -> GuidedParameterExplorationTab(
                            onApplyToGraph = onApplyExpressionToPrimary
                        )
                        MathWorkbenchTab.COORDINATES -> CoordinateSystemsTab(
                            xMin = xMin,
                            xMax = xMax,
                            yMin = yMin,
                            yMax = yMax
                        )
                        MathWorkbenchTab.GEOMETRY_VECTORS -> AnalyticGeometryAndVectorsTab()
                        MathWorkbenchTab.STATISTICS -> StatisticsAndDataVisualizationTab()
                    }
                }
            }
        }
    }
}

@Composable
private fun MultiFunctionAndDerivativeTab(
    primaryExpression: String,
    xMin: Double,
    xMax: Double,
    extraFunctions: List<UserPlottedFunction>,
    showDerivativeCurve: Boolean,
    onToggleDerivativeCurve: (Boolean) -> Unit,
    onAddExtraFunction: (String, String) -> Unit,
    onToggleExtraFunctionVisibility: (String) -> Unit,
    onRemoveExtraFunction: (String) -> Unit
) {
    var newFuncInput by remember { mutableStateOf("cos(x)") }
    var inputErr by remember { mutableStateOf<String?>(null) }
    val paletteHex = listOf("#F472B6", "#A78BFA", "#34D399", "#FBBF24", "#FB7185")

    val derivResult = remember(primaryExpression) {
        SymbolicAndNumericalAnalyzer.differentiate(primaryExpression)
    }

    Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
        // 1. Derivada Simbólica e Reta/Curva Derivada
        Surface(
            color = SurfaceElevated,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(10.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = if (derivResult.isSymbolicExact) "Derivada Simbólica Exata (AST)" else "Derivada Numérica (Fallback O(h⁴))",
                            style = MaterialTheme.typography.labelSmall,
                            color = if (derivResult.isSymbolicExact) SuccessEmerald else WarningAmber,
                            fontWeight = FontWeight.Bold
                        )
                        Text(
                            text = "f'(x) = ${derivResult.derivativeExpression}",
                            style = FormulaTextStyle,
                            color = TextPrimary
                        )
                    }
                    FilterChip(
                        selected = showDerivativeCurve,
                        onClick = { onToggleDerivativeCurve(!showDerivativeCurve) },
                        label = { Text("Plotar f'(x)", style = MaterialTheme.typography.labelSmall) }
                    )
                }

                if (derivResult.steps.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(6.dp))
                    derivResult.steps.take(3).forEachIndexed { idx, step ->
                        Text(
                            text = "${idx + 1}. ${step.ruleApplied} → ${step.resultingExpression}",
                            style = MaterialTheme.typography.bodySmall,
                            color = TextSecondary,
                            fontSize = 11.sp
                        )
                    }
                }
            }
        }

        // 2. Adicionar Múltiplas Funções no Mesmo Plano Cartesiano
        Text(
            text = "Comparar Múltiplas Funções no Plano Cartesiano",
            style = MaterialTheme.typography.labelMedium,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedTextField(
                value = newFuncInput,
                onValueChange = {
                    newFuncInput = it
                    inputErr = null
                },
                label = { Text("Nova função g(x), ex: 2*x - 1, sen(x), e^x") },
                singleLine = true,
                modifier = Modifier.weight(1f)
            )
            Button(
                onClick = {
                    val validation = MathExpressionEvaluator.validateExpression(newFuncInput)
                    if (validation is ExpressionValidationResult.Valid) {
                        val colorHex = paletteHex[extraFunctions.size % paletteHex.size]
                        onAddExtraFunction(newFuncInput.trim(), colorHex)
                        inputErr = null
                    } else if (validation is ExpressionValidationResult.Invalid) {
                        inputErr = validation.errorMessage
                    }
                },
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet)
            ) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar função", modifier = Modifier.size(16.dp))
                Spacer(modifier = Modifier.width(4.dp))
                Text("Adicionar")
            }
        }

        if (inputErr != null) {
            Text(text = inputErr!!, style = MaterialTheme.typography.bodySmall, color = ErrorRose)
        }

        // Lista de funções extras com botão exibir/ocultar, cor e interseção com f(x)
        extraFunctions.forEach { fn ->
            val intersection = remember(primaryExpression, fn.expression, xMin, xMax) {
                AnalyticGeometryAndCoordinatesEngine.intersectTwoFunctions(primaryExpression, fn.expression, xMin, xMax)
            }
            Surface(
                color = SurfaceElevated,
                shape = RoundedCornerShape(6.dp),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp, vertical = 6.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column(modifier = Modifier.weight(1f)) {
                        Text(
                            text = "${fn.label}(x) = ${fn.expression}",
                            style = FormulaTextStyle,
                            fontSize = 12.5.sp,
                            color = TextPrimary
                        )
                        Text(
                            text = "Interseção com f(x): ${intersection.analyticalSummary}",
                            style = MaterialTheme.typography.bodySmall,
                            fontSize = 11.sp,
                            color = TextSecondary
                        )
                    }
                    Row {
                        IconButton(onClick = { onToggleExtraFunctionVisibility(fn.id) }, modifier = Modifier.size(32.dp)) {
                            Icon(
                                imageVector = if (fn.isVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff,
                                contentDescription = "Alternar visibilidade",
                                tint = if (fn.isVisible) SuccessEmerald else TextTertiary,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                        IconButton(onClick = { onRemoveExtraFunction(fn.id) }, modifier = Modifier.size(32.dp)) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = "Remover função",
                                tint = ErrorRose,
                                modifier = Modifier.size(16.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun RootsAndBehaviorTab(
    primaryExpression: String,
    xMin: Double,
    xMax: Double
) {
    var selectedMethod by remember { mutableStateOf(RootFindingMethod.HYBRID_SAFE) }
    val detailedRoots = remember(primaryExpression, xMin, xMax, selectedMethod) {
        SymbolicAndNumericalAnalyzer.findDetailedRoots(primaryExpression, xMin, xMax, selectedMethod)
    }
    val monotonicity = remember(primaryExpression, xMin, xMax) {
        SymbolicAndNumericalAnalyzer.analyzeMonotonicity(primaryExpression, xMin, xMax)
    }
    val asymptotes = remember(primaryExpression, xMin, xMax) {
        SymbolicAndNumericalAnalyzer.analyzeAllAsymptotes(primaryExpression, xMin, xMax)
    }
    val discontinuities = remember(primaryExpression, xMin, xMax) {
        SymbolicAndNumericalAnalyzer.detectDiscontinuities(primaryExpression, xMin, xMax)
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Método Numérico de Localização de Raízes:",
            style = MaterialTheme.typography.labelMedium,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            RootFindingMethod.entries.forEach { m ->
                FilterChip(
                    selected = selectedMethod == m,
                    onClick = { selectedMethod = m },
                    label = { Text(m.label, style = MaterialTheme.typography.labelSmall) }
                )
            }
        }

        if (detailedRoots.isEmpty()) {
            Text(
                text = "Nenhuma raiz real detectada em [${String.format(Locale.US, "%.1f", xMin)}, ${String.format(Locale.US, "%.1f", xMax)}].",
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        } else {
            detailedRoots.take(6).forEach { r ->
                val kindTag = if (r.isAnalyticalExact) {
                    "Exata (${r.exactLabel ?: String.format(Locale.US, "%.4f", r.root)})"
                } else {
                    "Aproximação Numérica (±${String.format(Locale.US, "%.1e", r.estimatedError)})"
                }
                Text(
                    text = "• x ≈ ${String.format(Locale.US, "%.5f", r.root)} | f(x) = ${String.format(Locale.US, "%.2e", r.functionValueAtRoot)} | $kindTag [${r.methodUsed.label}, ${r.iterations} iter.]",
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = TextPrimary
                )
            }
        }

        if (monotonicity.isNotEmpty()) {
            Text(
                text = "Monotonicidade (Crescimento / Decrescimento):",
                style = MaterialTheme.typography.labelMedium,
                color = AccentCyan,
                fontWeight = FontWeight.Bold
            )
            monotonicity.take(4).forEach { m ->
                val dirLabel = if (m.isIncreasing) "Crescente (f' > 0)" else "Decrescente (f' < 0)"
                Text(
                    text = String.format(Locale.US, "• [%.2f, %.2f]: %s", m.xStart, m.xEnd, dirLabel),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }

        if (asymptotes.isNotEmpty() || discontinuities.isNotEmpty()) {
            Text(
                text = "Assíntotas e Descontinuidades Detectadas:",
                style = MaterialTheme.typography.labelMedium,
                color = WarningAmber,
                fontWeight = FontWeight.Bold
            )
            asymptotes.forEach { a ->
                Text(
                    text = "• ${a.kind.label}: ${a.equation}",
                    style = MaterialTheme.typography.bodySmall,
                    color = TextPrimary
                )
            }
            discontinuities.take(4).forEach { d ->
                Text(
                    text = String.format(
                        Locale.US,
                        "• %s em x = %.2f (lim⁻ = %.2f, lim⁺ = %.2f)",
                        d.kind.label,
                        d.x,
                        d.leftLimit,
                        d.rightLimit
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun InteractiveIntegralTab(
    primaryExpression: String,
    defaultA: Double,
    defaultB: Double
) {
    var boundA by remember { mutableDoubleStateOf(defaultA) }
    var boundB by remember { mutableDoubleStateOf(defaultB) }
    var subdivisions by remember { mutableIntStateOf(20) }

    val report = remember(primaryExpression, boundA, boundB, subdivisions) {
        SymbolicAndNumericalAnalyzer.compareIntegrationMethods(primaryExpression, boundA, boundB, subdivisions)
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text(
            text = "Limites de Integração: [${String.format(Locale.US, "%.2f", boundA)}, ${String.format(Locale.US, "%.2f", boundB)}] • Subdivisões n = $subdivisions",
            style = MaterialTheme.typography.labelMedium,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )

        Slider(
            value = subdivisions.toFloat(),
            onValueChange = { subdivisions = it.toInt().coerceIn(2, 120) },
            valueRange = 2f..120f,
            modifier = Modifier.height(26.dp)
        )

        Surface(
            color = SurfaceElevated,
            shape = RoundedCornerShape(8.dp),
            border = BorderStroke(1.dp, BorderSubtle),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(4.dp)) {
                Text(
                    text = if (report.isSymbolicSupported) {
                        "Primitiva Simbólica: ∫ f(x) dx = ${report.symbolicAntiderivative}"
                    } else {
                        "Primitiva Simbólica: Não elementar / não tabelada (utilizando quadratura numérica)"
                    },
                    style = FormulaTextStyle,
                    fontSize = 12.sp,
                    color = if (report.isSymbolicSupported) SuccessEmerald else AccentCyan
                )
                Text(
                    text = String.format(
                        Locale.US,
                        "• Área Assinada (Simpson 1/3): %.5f (erro est. ±%.2e)",
                        report.signedAreaSimpson,
                        report.simpsonErrorEstimate
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Monospace,
                    color = TextPrimary
                )
                Text(
                    text = String.format(
                        Locale.US,
                        "• Área Geométrica Total ∫|f(x)|dx: %.5f (regiões abaixo do eixo X somam positivamente)",
                        report.geometricAreaSimpson
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Monospace,
                    color = GoldXp
                )
                Text(
                    text = String.format(
                        Locale.US,
                        "• Trapézios: %.5f (±%.2e) | Ponto Médio: %.5f | Esq: %.5f | Dir: %.5f",
                        report.trapezoidalValue,
                        report.trapezoidalErrorEstimate,
                        report.riemannMidpoint,
                        report.riemannLeft,
                        report.riemannRight
                    ),
                    style = MaterialTheme.typography.bodySmall,
                    fontFamily = FontFamily.Monospace,
                    fontSize = 11.sp,
                    color = TextSecondary
                )
            }
        }
    }
}

@Composable
private fun GuidedParameterExplorationTab(
    onApplyToGraph: (String) -> Unit
) {
    var familyIndex by remember { mutableIntStateOf(0) }
    var paramA by remember { mutableFloatStateOf(1f) }
    var paramB by remember { mutableFloatStateOf(1f) }
    var paramC by remember { mutableFloatStateOf(0f) }

    val families = listOf("Parábola: a*x^2 + b*x + c", "Senóide: a*sin(b*x) + c", "Afim: a*x + b")
    val builtExpr = remember(familyIndex, paramA, paramB, paramC) {
        val aStr = String.format(Locale.US, "%.2f", paramA)
        val bStr = String.format(Locale.US, "%.2f", paramB)
        val cStr = String.format(Locale.US, "%.2f", paramC)
        when (familyIndex) {
            0 -> "$aStr*x^2 + ($bStr)*x + ($cStr)"
            1 -> "$aStr*sin($bStr*x) + ($cStr)"
            else -> "$aStr*x + ($bStr)"
        }
    }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            families.forEachIndexed { idx, label ->
                FilterChip(
                    selected = familyIndex == idx,
                    onClick = { familyIndex = idx },
                    label = { Text(label, style = MaterialTheme.typography.labelSmall) }
                )
            }
        }

        Text(
            text = String.format(Locale.US, "Parâmetro a = %.2f (Concavidade / Amplitude / Inclinação)", paramA),
            style = MaterialTheme.typography.bodySmall,
            color = TextPrimary
        )
        Slider(value = paramA, onValueChange = { paramA = it }, valueRange = -4f..4f, modifier = Modifier.height(24.dp))

        Text(
            text = String.format(Locale.US, "Parâmetro b = %.2f (Frequência Angular / Termo Linear)", paramB),
            style = MaterialTheme.typography.bodySmall,
            color = TextPrimary
        )
        Slider(value = paramB, onValueChange = { paramB = it }, valueRange = -4f..4f, modifier = Modifier.height(24.dp))

        if (familyIndex != 2) {
            Text(
                text = String.format(Locale.US, "Parâmetro c = %.2f (Translação Vertical)", paramC),
                style = MaterialTheme.typography.bodySmall,
                color = TextPrimary
            )
            Slider(value = paramC, onValueChange = { paramC = it }, valueRange = -5f..5f, modifier = Modifier.height(24.dp))
        }

        Button(
            onClick = { onApplyToGraph(builtExpr) },
            colors = ButtonDefaults.buttonColors(containerColor = PrimaryViolet),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Aplicar $builtExpr no Gráfico Principal")
        }
    }
}

@Composable
private fun CoordinateSystemsTab(
    xMin: Double,
    xMax: Double,
    yMin: Double,
    yMax: Double
) {
    var mode by remember { mutableStateOf(NonCartesianMode.POLAR) }
    var polarExpr by remember { mutableStateOf("3*cos(4*theta)") }
    var paramXExpr by remember { mutableStateOf("4*sin(3*t)") }
    var paramYExpr by remember { mutableStateOf("4*sin(2*t)") }
    var implicitExpr by remember { mutableStateOf("x^2/16 + y^2/9 = 1") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Row(horizontalArrangement = Arrangement.spacedBy(6.dp)) {
            NonCartesianMode.entries.forEach { m ->
                FilterChip(
                    selected = mode == m,
                    onClick = { mode = m },
                    label = { Text(m.label, style = MaterialTheme.typography.labelSmall) }
                )
            }
        }

        when (mode) {
            NonCartesianMode.POLAR -> {
                OutlinedTextField(
                    value = polarExpr,
                    onValueChange = { polarExpr = it },
                    label = { Text("r(θ) — ex: 3*cos(4*theta), 2 + 2*cos(theta), 0.4*theta") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            NonCartesianMode.PARAMETRIC -> {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    OutlinedTextField(
                        value = paramXExpr,
                        onValueChange = { paramXExpr = it },
                        label = { Text("x(t)") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                    OutlinedTextField(
                        value = paramYExpr,
                        onValueChange = { paramYExpr = it },
                        label = { Text("y(t)") },
                        singleLine = true,
                        modifier = Modifier.weight(1f)
                    )
                }
            }
            NonCartesianMode.IMPLICIT -> {
                OutlinedTextField(
                    value = implicitExpr,
                    onValueChange = { implicitExpr = it },
                    label = { Text("Equação Implícita F(x,y) = 0, ex: x^2 + y^2 = 9, x^2 - y^2 = 4") },
                    singleLine = true,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }

        // Canvas dedicado para visualização Polar / Paramétrica / Implícita (Marching Squares)
        val polylines = remember(mode, polarExpr, paramXExpr, paramYExpr) {
            when (mode) {
                NonCartesianMode.POLAR -> AnalyticGeometryAndCoordinatesEngine.samplePolarCurve(polarExpr)
                NonCartesianMode.PARAMETRIC -> AnalyticGeometryAndCoordinatesEngine.sampleParametricCurve(paramXExpr, paramYExpr)
                NonCartesianMode.IMPLICIT -> emptyList()
            }
        }
        val implicitSegments = remember(mode, implicitExpr, xMin, xMax, yMin, yMax) {
            if (mode == NonCartesianMode.IMPLICIT) {
                AnalyticGeometryAndCoordinatesEngine.sampleImplicitCurveMarchingSquares(implicitExpr, -6.0, 6.0, -6.0, 6.0, 44)
            } else emptyList()
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(190.dp)
                .clip(RoundedCornerShape(8.dp))
                .background(Color(0xFF090D16))
                .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                val w = size.width
                val h = size.height
                val bound = 6.0
                fun toScreen(x: Double, y: Double): Offset {
                    val sx = ((x + bound) / (2 * bound) * w).toFloat()
                    val sy = ((bound - y) / (2 * bound) * h).toFloat()
                    return Offset(sx, sy)
                }

                // Eixos e círculos polares de referência
                drawLine(Color(0xFF334155), Offset(0f, h / 2f), Offset(w, h / 2f), strokeWidth = 1.2f)
                drawLine(Color(0xFF334155), Offset(w / 2f, 0f), Offset(w / 2f, h), strokeWidth = 1.2f)
                for (rGrid in listOf(2f, 4f)) {
                    drawCircle(
                        color = Color(0xFF1E293B),
                        radius = (rGrid / (2 * bound).toFloat()) * minOf(w, h),
                        center = Offset(w / 2f, h / 2f),
                        style = Stroke(width = 1f)
                    )
                }

                if (mode == NonCartesianMode.IMPLICIT) {
                    for (seg in implicitSegments) {
                        drawLine(
                            color = Color(0xFF38BDF8),
                            start = toScreen(seg.x1, seg.y1),
                            end = toScreen(seg.x2, seg.y2),
                            strokeWidth = 2.6f
                        )
                    }
                } else {
                    for (line in polylines) {
                        val path = Path()
                        line.forEachIndexed { idx, pt ->
                            val p = toScreen(pt.x, pt.y)
                            if (idx == 0) path.moveTo(p.x, p.y) else path.lineTo(p.x, p.y)
                        }
                        drawPath(path, color = Color(0xFF34D399), style = Stroke(width = 2.6f))
                    }
                }
            }
        }
    }
}

@Composable
private fun AnalyticGeometryAndVectorsTab() {
    var ux by remember { mutableFloatStateOf(3f) }
    var uy by remember { mutableFloatStateOf(4f) }
    var vx by remember { mutableFloatStateOf(4f) }
    var vy by remember { mutableFloatStateOf(-1f) }

    val vecU = remember(ux, uy) { MathVector2D(ux.toDouble(), uy.toDouble(), label = "u") }
    val vecV = remember(vx, vy) { MathVector2D(vx.toDouble(), vy.toDouble(), label = "v") }
    val lineAB = remember(ux, uy, vx, vy) {
        AnalyticGeometryAndCoordinatesEngine.run {
            AnalyticLine2D.fromTwoPoints(MathPoint2D(ux.toDouble(), uy.toDouble()), MathPoint2D(vx.toDouble(), vy.toDouble()), "r")
        }
    }
    val circleInter = remember(lineAB) {
        AnalyticGeometryAndCoordinatesEngine.intersectLineAndCircle(lineAB, 0.0, 0.0, 4.0)
    }

    Column(verticalArrangement = Arrangement.spacedBy(6.dp)) {
        Text(
            text = String.format(
                Locale.US,
                "Vetor u = (%.1f, %.1f) [|u| = %.2f] • Vetor v = (%.1f, %.1f) [|v| = %.2f]",
                vecU.dx, vecU.dy, vecU.magnitude, vecV.dx, vecV.dy, vecV.magnitude
            ),
            style = MaterialTheme.typography.labelMedium,
            color = TextPrimary,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = String.format(
                Locale.US,
                "• Produto Escalar u·v = %.2f | Ângulo θ = %.1f° | Det 2D (u×v) = %.2f",
                vecU.dot(vecV),
                vecU.angleBetweenDegrees(vecV),
                vecU.cross2D(vecV)
            ),
            style = MaterialTheme.typography.bodySmall,
            fontFamily = FontFamily.Monospace,
            color = AccentCyan
        )
        Text(
            text = "• Reta por U e V: ${lineAB.formattedGeneralEquation()} (${lineAB.formattedReducedEquation()})",
            style = MaterialTheme.typography.bodySmall,
            color = TextPrimary
        )
        Text(
            text = "• Interseção com Circunferência x² + y² = 16: ${circleInter.analyticalSummary}",
            style = MaterialTheme.typography.bodySmall,
            color = GoldXp
        )
    }
}

@Composable
private fun StatisticsAndDataVisualizationTab() {
    var rawDataset by remember { mutableStateOf("12, 15, 14, 18, 21, 15, 24, 19, 22, 16, 15, 28") }
    var rawYDataset by remember { mutableStateOf("25, 32, 29, 38, 45, 31, 50, 41, 46, 35, 30, 58") }
    var selectedChart by remember { mutableStateOf(StatisticalChartType.HISTOGRAM) }

    val xValues = remember(rawDataset) { StatisticsAndDataEngine.parseDataset(rawDataset) }
    val yValues = remember(rawYDataset) { StatisticsAndDataEngine.parseDataset(rawYDataset) }
    val stats = remember(xValues) { StatisticsAndDataEngine.analyzeUnivariate(xValues) }
    val regression = remember(xValues, yValues) { StatisticsAndDataEngine.analyzeLinearRegression(xValues, yValues) }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        OutlinedTextField(
            value = rawDataset,
            onValueChange = { rawDataset = it },
            label = { Text("Amostra X (separada por vírgulas)") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        if (selectedChart == StatisticalChartType.SCATTER) {
            OutlinedTextField(
                value = rawYDataset,
                onValueChange = { rawYDataset = it },
                label = { Text("Amostra Y (para Dispersão e Regressão Linear)") },
                singleLine = true,
                modifier = Modifier.fillMaxWidth()
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(rememberScrollState()),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            StatisticalChartType.entries.forEach { chartType ->
                FilterChip(
                    selected = selectedChart == chartType,
                    onClick = { selectedChart = chartType },
                    label = { Text(chartType.label, style = MaterialTheme.typography.labelSmall) }
                )
            }
        }

        if (stats != null) {
            // Canvas Estatístico Nativo
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFF090D16))
                    .border(1.dp, BorderSubtle, RoundedCornerShape(8.dp))
                    .padding(12.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val w = size.width
                    val h = size.height
                    when (selectedChart) {
                        StatisticalChartType.BAR -> {
                            val maxV = (stats.max.takeIf { it > 0 } ?: 1.0).toFloat()
                            val barW = (w / xValues.size.coerceAtLeast(1)) * 0.7f
                            val stepW = w / xValues.size.coerceAtLeast(1)
                            xValues.forEachIndexed { i, v ->
                                val barH = ((v.toFloat() / maxV) * (h * 0.85f)).coerceIn(2f, h)
                                drawRect(
                                    color = Color(0xFF3B82F6),
                                    topLeft = Offset(i * stepW + barW * 0.15f, h - barH),
                                    size = Size(barW, barH)
                                )
                            }
                        }
                        StatisticalChartType.LINE, StatisticalChartType.FREQUENCY -> {
                            val pts = if (selectedChart == StatisticalChartType.FREQUENCY) {
                                stats.frequencyDistribution.map { it.AbsoluteFrequencyToDouble() }
                            } else xValues
                            val minV = pts.minOrNull() ?: 0.0
                            val maxV = (pts.maxOrNull() ?: 1.0).let { if (it <= minV) minV + 1.0 else it }
                            val path = Path()
                            pts.forEachIndexed { idx, v ->
                                val px = if (pts.size > 1) (idx.toFloat() / (pts.size - 1)) * w else w * 0.5f
                                val py = (h - ((v - minV) / (maxV - minV)).toFloat() * (h * 0.82f) - h * 0.08f)
                                if (idx == 0) path.moveTo(px, py) else path.lineTo(px, py)
                                drawCircle(Color(0xFF38BDF8), radius = 4f, center = Offset(px, py))
                            }
                            drawPath(path, color = Color(0xFF38BDF8), style = Stroke(width = 2.5f))
                        }
                        StatisticalChartType.HISTOGRAM -> {
                            val bins = stats.frequencyDistribution
                            val maxFreq = (bins.maxOfOrNull { it.absoluteFrequency } ?: 1).coerceAtLeast(1)
                            val binW = w / bins.size.coerceAtLeast(1)
                            bins.forEachIndexed { idx, bin ->
                                val barH = (bin.absoluteFrequency.toFloat() / maxFreq.toFloat()) * (h * 0.85f)
                                drawRect(
                                    color = Color(0xFF10B981).copy(alpha = 0.75f),
                                    topLeft = Offset(idx * binW + 2f, h - barH),
                                    size = Size((binW - 4f).coerceAtLeast(4f), barH)
                                )
                            }
                        }
                        StatisticalChartType.BOX_PLOT -> {
                            val span = (stats.max - stats.min).takeIf { it > 1e-6 } ?: 1.0
                            fun mapX(v: Double): Float = (((v - stats.min) / span) * (w * 0.86) + w * 0.07).toFloat()
                            val cy = h * 0.5f
                            val xMinPx = mapX(stats.min)
                            val xQ1Px = mapX(stats.q1)
                            val xMedPx = mapX(stats.median)
                            val xQ3Px = mapX(stats.q3)
                            val xMaxPx = mapX(stats.max)
                            drawLine(Color(0xFF94A3B8), Offset(xMinPx, cy), Offset(xQ1Px, cy), strokeWidth = 2.5f)
                            drawLine(Color(0xFF94A3B8), Offset(xQ3Px, cy), Offset(xMaxPx, cy), strokeWidth = 2.5f)
                            drawRect(
                                color = Color(0xFF3B82F6).copy(alpha = 0.35f),
                                topLeft = Offset(xQ1Px, cy - 28f),
                                size = Size((xQ3Px - xQ1Px).coerceAtLeast(4f), 56f)
                            )
                            drawLine(GoldXp, Offset(xMedPx, cy - 28f), Offset(xMedPx, cy + 28f), strokeWidth = 3.5f)
                        }
                        StatisticalChartType.PIE_SECTOR -> {
                            val bins = stats.frequencyDistribution
                            val palette = listOf(Color(0xFF3B82F6), Color(0xFF10B981), Color(0xFFF59E0B), Color(0xFF8B5CF6), Color(0xFFEC4899))
                            var startAngle = -90f
                            val diameter = minOf(w, h) * 0.85f
                            val topLeft = Offset((w - diameter) * 0.5f, (h - diameter) * 0.5f)
                            bins.forEachIndexed { idx, bin ->
                                val sweep = (bin.relativeFrequency * 360.0).toFloat()
                                drawArc(
                                    color = palette[idx % palette.size],
                                    startAngle = startAngle,
                                    sweepAngle = sweep,
                                    useCenter = true,
                                    topLeft = topLeft,
                                    size = Size(diameter, diameter)
                                )
                                startAngle += sweep
                            }
                        }
                        StatisticalChartType.SCATTER -> {
                            val n = minOf(xValues.size, yValues.size)
                            if (n >= 2) {
                                val xs = xValues.take(n)
                                val ys = yValues.take(n)
                                val minX = xs.minOrNull() ?: 0.0
                                val maxX = (xs.maxOrNull() ?: 1.0).let { if (it <= minX) minX + 1.0 else it }
                                val minY = ys.minOrNull() ?: 0.0
                                val maxY = (ys.maxOrNull() ?: 1.0).let { if (it <= minY) minY + 1.0 else it }
                                fun sx(v: Double): Float = (((v - minX) / (maxX - minX)) * (w * 0.88) + w * 0.06).toFloat()
                                fun sy(v: Double): Float = (h - (((v - minY) / (maxY - minY)) * (h * 0.84) + h * 0.08)).toFloat()
                                for (i in 0 until n) {
                                    drawCircle(Color(0xFF38BDF8), radius = 4.5f, center = Offset(sx(xs[i]), sy(ys[i])))
                                }
                                if (regression != null) {
                                    drawLine(
                                        color = GoldXp,
                                        start = Offset(sx(minX), sy(regression.predictY(minX))),
                                        end = Offset(sx(maxX), sy(regression.predictY(maxX))),
                                        strokeWidth = 2.5f
                                    )
                                }
                            }
                        }
                    }
                }
            }

            // Resumo Estatístico Descritivo Completo (Populacional vs Amostral)
            Surface(
                color = SurfaceElevated,
                shape = RoundedCornerShape(8.dp),
                border = BorderStroke(1.dp, BorderSubtle),
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(modifier = Modifier.padding(10.dp), verticalArrangement = Arrangement.spacedBy(3.dp)) {
                    Text(
                        text = String.format(
                            Locale.US,
                            "N = %d | Média = %.2f | Mediana (Q2) = %.2f | Moda: %s",
                            stats.count,
                            stats.mean,
                            stats.median,
                            if (stats.modes.isEmpty()) stats.modeClassification else "${stats.modes.joinToString()} (${stats.modeClassification})"
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = TextPrimary
                    )
                    Text(
                        text = String.format(
                            Locale.US,
                            "Populacional: σ² = %.2f, σ = %.2f  |  Amostral: s² = %.2f, s = %.2f",
                            stats.populationVariance,
                            stats.populationStdDev,
                            stats.sampleVariance,
                            stats.sampleStdDev
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = AccentCyan
                    )
                    Text(
                        text = String.format(
                            Locale.US,
                            "Mín = %.1f | Q1 = %.2f | Q3 = %.2f | Máx = %.1f | Amplitude = %.1f | Outliers: %s",
                            stats.min,
                            stats.q1,
                            stats.q3,
                            stats.max,
                            stats.range,
                            if (stats.outliers.isEmpty()) "Nenhum" else stats.outliers.joinToString()
                        ),
                        style = MaterialTheme.typography.bodySmall,
                        fontFamily = FontFamily.Monospace,
                        fontSize = 11.sp,
                        color = TextSecondary
                    )
                    if (regression != null) {
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = String.format(
                                Locale.US,
                                "Regressão Linear: %s | r = %.3f (R² = %.3f) — %s",
                                regression.equationText,
                                regression.pearsonR,
                                regression.rSquared,
                                regression.correlationInterpretation
                            ),
                            style = MaterialTheme.typography.bodySmall,
                            color = GoldXp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Text(
                            text = regression.methodologicalNote,
                            style = MaterialTheme.typography.labelSmall,
                            color = TextTertiary,
                            fontSize = 10.5.sp
                        )
                    }
                }
            }
        }
    }
}

private fun FrequencyClassBin.AbsoluteFrequencyToDouble(): Double = absoluteFrequency.toDouble()

